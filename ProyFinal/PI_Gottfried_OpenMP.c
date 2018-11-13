#include <stdlib.h>
#include <stdio.h>
#include "utils/cheader.h"

int main(){
  int i, flag;
  double sum, PI, ms;
  int SIZE = 1e9;
  printf("Starting...\n");

  for(int j = 0; j < N; j++){
    start_timer();
    sum = 0;
    flag = 1;
    #pragma omp parallel for private(i, flag) reduction(+:sum)
    for(i = 1; i<SIZE; i+=2){
      if(flag%2)
        sum-=1.0/(double)i;
      else
        sum+=1.0/(double)i;
      flag++;
    }
    PI = 4*sum;
    ms += stop_timer();
  }
  printf("PI: %lf\n", PI);
  printf("Avg time: %.5lf ms\n", (ms/N));
  return 0;
}
