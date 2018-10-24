/*----------------------------------------------------------------

*

* Actividad de programación: Resolviendo problemas con OpenMP

* Fecha: 12-Oct-2018

* Autor: Esteban Pérez Herrera A01204739

*

*--------------------------------------------------------------*/
#include <stdio.h>
#include <stdlib.h>
#include "utils/cheader.h"

int main(){
  double number_in_circle;
  double ms=0;
  double x, y, distance_squared, pi_estimate;
  long long int toss, number_of_tosses = 1e8;
  int j;
  srand(0);
  for (j = 0; j <=N; j++) {
		start_timer();
    #pragma parallel ford private(x,y,distance_squared) reduction(+:number_in_circle)
    for (toss = 0, number_in_circle = 0; toss < number_of_tosses; toss++) {
     x = (((double)rand()/(double)(RAND_MAX)) * 2.0)-1.0;
     y = (((double)rand()/(double)(RAND_MAX)) * 2.0)-1.0;
     distance_squared = x*x + y*y;
     if (distance_squared <= 1)
      number_in_circle++;
    }
    pi_estimate = 4 * number_in_circle/((double) number_of_tosses);
		ms += stop_timer();
	}
  printf("avg time = %.5lf\n", (ms/N));
  printf("PI value: %f\n", pi_estimate);
}
