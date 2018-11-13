#include <stdlib.h>
#include <stdio.h>
#include "utils/cheader.h"
#define SIZE 1e9

int main(){
  int i, flag;
  double sum, PI, ms;

  printf("Starting...\n");
  for(int j = 0; j < N; j++){
    start_timer();
    sum = 0;
    flag = 1;
    for(i = 1; i<SIZE; i+=2, flag++){
      if(flag%2)
        sum+=1.0/(double)i;
      else
        sum-=1.0/(double)i;
    }
    PI = 4*sum;
    ms += stop_timer();
  }
  printf("PI: %f\n", PI);
  printf("Avg time: %.5lf ms\n", (ms/N));
  return 0;
}
