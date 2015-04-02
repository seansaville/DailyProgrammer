#
# /r/DailyProgrammer Challenge #207 Easy - DNA Replication
# Takes a list of DNA bases and prints it, followed by a list of the other parts of the base pair
#

import string, sys

dic = { 'A': 'T', 'C': 'G', 'T': 'A', 'G': 'C' }

bases = ""

for arg in sys.argv:
    if arg != "dnareplication.py":
        bases += arg + " "

print (bases)

print (bases.translate(string.maketrans("ATCG", "TAGC")))
