import requests
from bs4 import BeautifulSoup

f = open("channels.txt")
channels = f.read().splitlines()
f.close()

def filter_channel(channel):
    words = channel.split()
    return true

it = filter(filter_channel, channels)

channels_filtered = list(it)

print(len(channels_filtered))

def extract_videos(channel):
    URL = f'https://www.xvideos.com/channels/{channel.lower()}/videos/new/0'
    print(URL)
    page = requests.get(URL)
    soup = BeautifulSoup(page.content, "html.parser")

    job_elements = soup.find_all("div", class_="thumb-block")

    titles = []

    for job_element in job_elements:
        el = job_element.find("p", class_="title")
#         print(el.text.strip())
        title = f'[{channel}] {el.text.strip()}'
        titles.append(title)

    return titles

titles = []

count = 0
for channel in channels_filtered:
    count = count + 1
    titles.extend(extract_videos(channel))
    print(str(count))
#     print(channel)

f = open("titles.txt", "w", encoding='utf8')
for title in titles:
    print(title)
    f.write(title + "\n")
f.close()
