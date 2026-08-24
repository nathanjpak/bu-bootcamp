#include <stdio.h>

void swap(int *a, int *b);
void broken_swap(int a, int b);

int main() {
  int x = 10;
  int y = 20;

  printf("Before swap: x = %d, y = %d\n", x, y);

  broken_swap(x, y);

  printf("After broken swap: x = %d, y = %d\n", x, y);

  swap(&x, &y);

  printf("After swap: x = %d, y = %d\n", x, y);

  return 0;
}

void swap(int *a, int *b) {
  int temp = *a;
  *a = *b;
  *b = temp;
}

// This function will not work because it takes the values instead of the pointers
void broken_swap(int a, int b) {
  int temp = a;
  a = b;
  b = temp;
}
