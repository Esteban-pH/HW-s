/*----------------------------------------------------------------

*

* Actividad de programación: Calculando Pi con TBB en C++

* Fecha: 26/10/18

* Autor: A01204739 Esteban Pérez Herrera

*

*--------------------------------------------------------------*/
#include <iostream>
#include "utils/cppheader.h"
#include <ctime>

using namespace std;

int main(){
  Timer t;
  double ms = 0;
  long num_rects = 1e8;
  double mid, height, width, area;
  double sum;

  width = 1.0 / (double) num_rects;
  cout << "Starting..." << endl;
  for (int i = 0; i < N; i++) {
    t.start();
    sum = 0;
    for (long i = 0; i < num_rects; i++) {
        mid = (i + 0.5) * width;
        height = 4.0 / (1.0 + mid * mid);
        sum += height;
    }
    ms += t.stop();
  }
  area = width * sum;
  cout << "PI = " << area << endl;
  cout << "Avg time = " << (ms/N) << " ms" << endl;
  return 0;
}
