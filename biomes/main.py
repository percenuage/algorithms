import sys, os
import numpy as np
from perlin_noise import PerlinNoise
from termcolor import colored, cprint

# https://medium.com/@yvanscher/playing-with-perlin-noise-generating-realistic-archipelagos-b59f004d8401
# https://pypi.org/project/perlin-noise/
# https://www.youtube.com/watch?v=qNZ-0-7WuS8&t=1s
# https://www.redblobgames.com/x/1843-planet-generation/
# https://www.redblobgames.com/
# https://gamedev.stackexchange.com/questions/186194/how-to-randomly-generate-biome-with-perlin-noise
# https://github.com/bradykieffer/SimplexNoise
# https://stackoverflow.com/questions/46525981/how-to-plot-x-y-z-coordinates-in-the-shape-of-a-hexagonal-grid

print("Biomes Generator")


ROWS = 30
COLS = 60
SCALE = 50
OCTAVES = 6
SEED = 42

world = []

def initWorld():
    global world
    noise = PerlinNoise(octaves=OCTAVES, seed=SEED)
    noises = [[noise([i / SCALE, j / SCALE]) for j in range(COLS)] for i in range(ROWS)]
    world = np.array(noises)


def normalizedWorld(max = 4, type = int):
    global world
    world = (max * (world - np.min(world)) / np.ptp(world)).astype(type)

def showWorld():
#     print('\n'.join([''.join(['{:2}'.format(cell) for cell in row]) for row in world]))
    for i in range(ROWS):
        print()
        for j in range(COLS):
            cell = world[i][j]
            cprint('{:2}'.format(cell), "grey", getColorGradient(cell), end="")
    print()

def getColorGradient(cell):
    if cell == 0:
        return 'on_red'
    elif cell == 1:
        return 'on_yellow'
    elif cell == 2:
        return 'on_green'
    elif cell == 3:
        return 'on_cyan'
    elif cell == 4:
        return 'on_white'



# CALL

initWorld()

normalizedWorld(4, int)

# compute()
showWorld()

