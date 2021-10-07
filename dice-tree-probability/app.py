import re
import itertools
from collections import Counter
import json

print('DICE PROBABILITY')

def generate_all_combinations(sided, dices):
    return list(itertools.product(list(range(1, sided + 1)), repeat=dices))

def generate_occurrences(combinations):
    occurrences = {}
    for count in map(Counter, combinations):
        id = get_poker_combination(count)
        if id in occurrences:
            occurrences[id] += 1
        else:
            occurrences[id] = 1
    occurrences['all'] = len(combinations)
    return occurrences

def get_poker_combination(count):
    id = ''
    for side in count:
        occurrence = count[side]
        if occurrence == 2:
          id += 'pair+'
        elif occurrence == 3:
          id += 'brelan+'
        elif occurrence == 4:
          id += 'four+'
        elif occurrence == 5:
          id += 'five+'
        elif occurrence > 5:
          id += occurrence
        id = re.sub('brelan\+pair|pair\+brelan', 'full', id)
    return id[:-1] or 'NA'


def generate_probabilities(occurrences):
    total = occurrences['all']
    return {k: round(v / total, 3) for k, v in occurrences.items()}

# ------

sided = 6
dices = 5
rolls = 5

combinations = generate_all_combinations(sided, dices)

occurrences = generate_occurrences(combinations)
print(json.dumps(occurrences, indent=2))

probabilities = generate_probabilities(occurrences)
print(json.dumps(probabilities, indent=2))
