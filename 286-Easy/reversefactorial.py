import sys


def reverse_factorial(n):
    """Returns either 0 or the number x such that x! = n."""
    i = 1
    while n > 1:
        i += 1
        n /= i

    if n == 1:
        return i
    else:
        return 0


def main():
    if len(sys.argv) != 2:
        print("Usage: python3 reverse_factorial.py <file>")
        return

    with open(sys.argv[1], 'r') as f:
        for line in f:
            try:
                n = int(line)
                fact = reverse_factorial(n)
                if fact != 0:
                    print("{} = {}!".format(n, fact))
                else:
                    print("{} NONE".format(n))
            except ValueError:
                print("{} is not a valid input.".format(line[:-1]))


if __name__ == "__main__":
    main()
