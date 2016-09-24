import random
import string
import sys


def breed_strings(string1, string2):
    """
    Takes two strings and breeds them together to produce a new child string.

    Each character in the child string has an equal chance of coming from
    either of the parents.

    """
    child = ""

    for c1, c2 in zip(string1, string2):
        if random.random() < 0.5:
            child += c1
        else:
            child += c2

    return child


def calculate_fitness(pop, target):
    """
    Takes a population (list of candidate strings) and calculates the fitness
    of each, returning a dictionary mapping candidate strings to their
    fitness.

    """
    fitdict = {}

    for str in pop:
        fitdict[str] = len(target) - hamming_distance(str, target)

    return fitdict


def generate_initial_pop(popsize, length):
    """
    Generates an initial population of random strings of the specified length.
    """
    charset = string.ascii_letters + string.digits + string.punctuation + " "
    pop = []

    for i in range(0, popsize):
        rand = ''.join([random.choice(charset) for n in range(length)])
        pop.append(rand)

    return pop


def hamming_distance(string1, string2):
    """
    Returns the Hamming distance between two strings, i.e. the number of
    positions with differing characters.

    """
    distance = 0

    for c1, c2 in zip(string1, string2):
        if c1 != c2:
            distance += 1

    return distance


def mutate(str, prob):
    """
    Randomly mutates a given string with a probability.

    """
    out = ""
    charset = string.ascii_letters + string.digits + string.punctuation + " "

    if random.random() <= prob:
        pos = random.randint(0, len(str) - 1)
        for i in range(len(str)):
            if i == pos:
                out += random.choice(charset)
            else:
                out += str[i]
    else:
        out += str

    return out


def sel_breeding_pair(fitdict):
    """
    Uses fitness-proportionate selection to a select a breeding pair of
    strings from a dictionary mapping candidate strings to their fitness.

    """
    total_fitness = 0
    for val in fitdict.values():
        total_fitness += val

    probs = {}
    for str in fitdict:
        probs[str] = fitdict[str] / total_fitness

    candidates = ["", ""]
    for i in range(0, 2):
        chosen = random.random()
        for str in probs:
            chosen -= probs[str]
            if chosen <= 0:
                candidates[i] = str
                break

    return candidates


def genetic_string_gen(target, popsize, mutchance):
    """
    Uses a genetic algorithm to produce a target string, starting from an
    initial random population of strings.

    """
    pop = generate_initial_pop(popsize, len(target))

    gen = 0
    done = False
    while not done:
        popfitness = calculate_fitness(pop, target)
        nextgen = []
        fittestcandidate = ""
        fittestcandidatescore = sys.maxsize

        for i in range(popsize):
            # Generate a new member of the population
            pair = sel_breeding_pair(popfitness)
            child = mutate(breed_strings(pair[0], pair[1]), mutchance)
            # Keep track of the best-scoring child so far
            if hamming_distance(child, target) < fittestcandidatescore:
                fittestcandidate = child
                fittestcandidatescore = hamming_distance(child, target)
            nextgen.append(child)

        if fittestcandidate == target:
            done = True

        print("Generation {} | {} | Score: {}"
              .format(gen, fittestcandidate, fittestcandidatescore))

        pop = nextgen
        gen += 1


def main():
    try:
        target = sys.argv[1]
        popsize = int(sys.argv[2])
        mutchance = float(sys.argv[3])
        genetic_string_gen(target, popsize, mutchance)
    except:
        print("Usage: python3 genetic_string.py <target string> "
              "<population size> <mutation chance [0 - 1]>")

if __name__ == '__main__':
    main()
