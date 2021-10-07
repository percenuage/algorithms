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

# MAIN

channels = []
for i in range(131):
    channels.extend(extract_channels_at(i))

print(len(channels))

channels.sort()

f = open("channels.txt", "w")
for channel in channels:
#     print(channel)
    f.write(channel + "\n")
f.close()
