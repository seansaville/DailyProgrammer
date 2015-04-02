/*
 * /r/DailyProgrammer Challenge #197 Easy - ISBN Validator
 * Takes an ISBN number (without hyphens) from the command line and validates it.
 */
#include <stdio.h>

int main(int argc, char **argv) {
    if (argc != 2) {
        printf("Usage: validator <ISBN number>\n");
        printf("Note: the number may not include hyphens\n");
        return 1;
    }

    char *isbn = argv[1];
    int multiplier = 10;
    int sum = 0;

    do {
        if (*isbn == 'X' || *isbn == 'x') {
            sum += multiplier * 10;
        } else {
            sum += multiplier * (*isbn - 48);
        }
        multiplier--;
        isbn++;
    } while (multiplier);

    if (sum % 11 == 0) {
        printf("Valid\n");
    } else {
        printf("Invalid\n");
    }

    return 0;
}