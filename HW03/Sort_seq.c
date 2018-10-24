/*----------------------------------------------------------------

*

* Actividad de programación: Resolviendo problemas con OpenMP

* Fecha: 12-Oct-2018

* Autor: Esteban Pérez Herrera A01204739

*

*--------------------------------------------------------------*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "utils/cheader.h"

#define SIZE 1e4

void count_sort(int a[], int n) {
  int i, j, count;
  int* temp = malloc(n * sizeof(int));
  for (i = 0; i < n; i++) {
   count = 0;
   for (j = 0; j < n; j++)
    if (a[j] < a[i])
      count++;
    else if (a[i] == a[j] && j < i)
      count++;
    temp[count] = a[i];
  }
  memcpy(a, temp, n * sizeof(int));
  free(temp);
}

int main(){
  int i, *array, j;
	double ms=0;

	array = (int*) malloc(sizeof(int) * SIZE);
	random_array(array, SIZE);
	display_array("array", array);

	printf("Starting...\n");

  for (j = 0; j <= N; j++) {
		start_timer();
		count_sort(array, SIZE);
		ms += stop_timer();
	}
  display_array("array", array);
	printf("avg time = %.5lf\n", (ms/N));

	free(array);
	return 0;
}
