import requests
from bs4 import BeautifulSoup

def extract_channels_at(index):
    URL = "https://www.xvideos.com/channels-index/from/worldwide/" + str(index)
    print(URL)
    page = requests.get(URL)
    soup = BeautifulSoup(page.content, "html.parser")

    results = soup.find(id="main")

    job_elements = results.find_all("div", class_="thumb")

    channels = []

    for job_element in job_elements:
        name = job_element.find("span", class_="profile-name")
#         print(name.text.strip())
        channels.append(name.text.strip())

    return channels

def filter_channel(channel):
    words = channel.split()
    return true

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

def extract_all_channels():
    channels = []
    for i in range(131):
        channels.extend(extract_channels_at(i))

    print(len(channels))

    channels.sort()

    f = open("channels.txt", "w")
    for channel in channels:
        f.write(channel + "\n")
    f.close()

def extract_all_videos_from_all_channels():
    f = open("channels.txt")
    channels = f.read().splitlines()
    f.close()

    it = filter(filter_channel, channels)
    channels_filtered = list(it)
    print(len(channels_filtered))

    titles = []

    count = 0
    for channel in channels_filtered:
        count = count + 1
        titles.extend(extract_videos(channel))
        print(str(count))

    f = open("titles.txt", "w", encoding='utf8')
    for title in titles:
        f.write(title + "\n")
    f.close()


extract_all_channels()
extract_all_videos_from_all_channels()


