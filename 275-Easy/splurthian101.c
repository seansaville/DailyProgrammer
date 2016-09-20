#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>

bool chk_ele_sym (char *element, char *symbol)
{
    bool first = false;
    bool result = false;

    for ( ; *element; element++) {
        if (tolower(*element) == tolower(*symbol)) {
            if (!first) {
                first = true;
                symbol++;
            } else {
                result = true;
            }
        }        
    }

    return result;
}

int main (int argc, char **argv)
{
    int result = chk_ele_sym(argv[1], argv[2]);
    printf("%s\n", result ? "true" : "false");

    return 0;
}