import sys
import locale
import re
import requests
import pandas as pd
import numpy as np
from bs4 import BeautifulSoup
from tabulate import tabulate

from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as ec
from selenium.webdriver.common.by import By
from selenium.common.exceptions import TimeoutException
from selenium.webdriver.support.ui import Select

locale.setlocale(locale.LC_ALL, '')

def parse_float(text):
    try:
        return float(re.sub('[€,m%]', '', text))
    except ValueError:
        return 'NaN'

def get_dict_coins():
    URL = f'https://api.coingecko.com/api/v3/coins/list'
    res = requests.get(URL).json()
    symbols = list(map(lambda x: x['symbol'], res))
    ids = list(map(lambda x: x['id'], res))
    names = list(map(lambda x: x['name'].lower(), res))
    dict_symbols = dict(zip(symbols, ids))
    dict_names = dict(zip(names, ids))
    coins = { **dict_symbols, **dict_names }
    return coins

def get_markets(coin):
    coin = coin.lower()
    match = re.search('(.*) \((.*)\)', coin)
    name = match.group(1)
    symbol = match.group(2)
    if symbol in coins:
        currency = coins[symbol]
    elif name in coins:
        currency = coins[name]
    else:
       return 'N/A'
    URL = f'https://api.coingecko.com/api/v3/coins/{currency}'
    res = requests.get(URL).json()
    tickers = res['tickers']
    markets = map(lambda x: x['market']['name'], tickers)
    markets = sorted(set(markets))
    return ','.join(markets)


def extract():
    options = webdriver.ChromeOptions()
    options.add_argument('--headless')
    browser = webdriver.Chrome(options=options, executable_path='./chromedriver.exe')

    df = None
    try:
        browser.get('https://masternodes.online/?convert=EUR')
        timeout_in_seconds = 10
        WebDriverWait(browser, timeout_in_seconds)

        select = Select(browser.find_element_by_name('masternodes_table_length'))
        select.select_by_value('-1') # ALL

        html = browser.page_source
        soup = BeautifulSoup(html, features='html.parser')
        table = soup.find(id='masternodes_table')

        df = pd.DataFrame(columns=['Coin', 'Price', 'Age', 'Volume', 'MarketCap', 'ROI', 'Nodes', 'Required', 'Cost'])

        for row in table.tbody.find_all('tr'):
            columns = row.find_all('td')
            if(columns != []):
                df = df.append({
                    'Coin': columns[1].text.strip(),
                    'Price': parse_float(columns[2].text.strip()),
                    'Age': parse_float(columns[3].text.strip()),
                    'Volume': parse_float(columns[4].text.strip()),
                    'MarketCap': parse_float(columns[5].text.strip()),
                    'ROI': parse_float(columns[6].text.strip()),
                    'Nodes': parse_float(columns[7].text.strip()),
                    'Required': parse_float(columns[8].text.strip()),
                    'Cost': parse_float(columns[9].text.strip()),
                }, ignore_index=True)

        # Save DF to csv
        df.to_csv(index=False, path_or_buf='masternodes.csv')
    except TimeoutException:
        print('I give up...')
    finally:
        browser.quit()

def transform():
    df = pd.read_csv('masternodes.csv')

    # Filter DF
    df.dropna(subset = ['Volume', 'MarketCap'], inplace=True)
    df.drop(df[df['MarketCap'] < 100000].index, inplace=True)
#     df.drop(df[df['Volume'] < 50000].index, inplace=True)
    df.drop(df[df['Cost'] > 20000].index, inplace=True)
    df.drop(df[df['ROI'] < 50].index, inplace=True)

    # Add Earnings columns
    df['Yearly'] = (df['ROI'] / 100) * df['Cost'] * np.floor(INITIAL_INVESTMENT / df['Cost'])
    df['Monthly'] = df['Yearly'] / 12
    df['Daily'] = df['Monthly'] / 30.5

    # Add Break Even in month
    df['BE'] = (100 * 12) / df['ROI']

    # Apply algorithm to find the best
    df['Weight'] = (df['ROI'] / df['Cost']) * 100

    # Sort
    df.sort_values(['ROI'], ascending=(False), inplace=True)

    # Reset index
    df.reset_index(drop=True, inplace=True)

    # Append Exchange
    if '-e' in sys.argv:
        df['Exchange'] = df.apply(lambda x: get_markets(x['Coin']), axis=1)

    # Print
    print_data(df)

def print_data(df):
    pd.set_option('display.max_colwidth', -1)

    float_format=lambda x: locale.format_string('%.0f', x, grouping=True, monetary=True)
    columns=['Coin', 'Volume', 'MarketCap', 'ROI', 'Nodes', 'Cost', 'BE', 'Daily', 'Monthly']

    if '-e' in sys.argv:
        columns.append('Exchange')

    df = df[columns]

    print(f'> Initial Investment: {INITIAL_INVESTMENT} EUR')
#     print(df.to_string(float_format=float_format))
    print(tabulate(df, headers='keys', tablefmt='github', floatfmt=",.0f"))

INITIAL_INVESTMENT = 10000
coins = get_dict_coins()

extract()
transform()
# load()
