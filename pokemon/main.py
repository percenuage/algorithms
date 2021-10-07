import pandas as pd
import numpy as np
import itertools

print("> Pokemon Type List <\n")

data = pd.read_csv("types.csv")


def append_pair_types():
    position = len(data.columns)
    combinations = itertools.combinations(data.columns[1:], 2)
    for tuple in combinations:
        head = tuple[0] + '-' + tuple[1]
        values = np.array(data[tuple[0]]) * np.array(data[tuple[1]])
        data.insert(position, head, values)
        position += 1


def compute_scores():
    scores = []
    for t in data.columns[1:]:
        values = data[t]
        score = compute_score(values)
        scores.append({'type': t, 'score': score})
    scores.sort(key=lambda e: e['score'], reverse=True)
    return scores


def compute_score(values):
    score = 0
    for val in values:
        score += get_score(val)
    return score


def get_score(value):
    switcher = {
        0: 4,
        0.25: 2,
        0.5: 1,
        1: 0,
        2: -1,
        4: -2,
    }
    return switcher.get(value)


def show_best(scores, limit = 6):
    print('Count: ' + str(len(scores)))
    for index, e in enumerate(scores):
        print(str(index + 1) + ' -> ' + e.get('type') + ': ' + str(e.get('score')))
        if index == limit - 1:
            break


append_pair_types()
# print(data)
scores = compute_scores()
show_best(scores, 100)