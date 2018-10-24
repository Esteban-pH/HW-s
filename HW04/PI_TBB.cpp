/*----------------------------------------------------------------

*

* Actividad de programación: Calculando Pi con TBB en C++

* Fecha: 26/10/18

* Autor: A01204739 Esteban Pérez Herrera

*

*--------------------------------------------------------------*/
#include <iostream>
#include <cstdlib>
#include <ctime>
#include <tbb/task_scheduler_init.h>
#include <tbb/parallel_for.h>
#include <tbb/parallel_reduce.h>
#include <tbb/blocked_range.h>
#include "utils/cppheader.h"

using namespace std;
using namespace tbb;

const int GRAIN = 100000;

class ParallelPI {
private:
  double mid, height, width, sum;

public:
  int    num_rects;
  double area;

  ParallelPI(int num_rects) : num_rects(num_rects), sum(0){}
  ParallelPI(ParallelPI &x, split) : num_rects(x.num_rects), sum(0){}

  void operator()(const blocked_range<int> &r){
    width = 1.0 / (double) num_rects;
    for (long i = r.begin(); i < r.end(); i++) {
        mid = (i + 0.5) * width;
        height = 4.0 / (1.0 + mid * mid);
        sum += height;
    }
    area = sum*width;
  }

  void join(const ParallelPI &x){
    area += x.area;
  }
};

int main(){
  Timer t;
  double ms = 0;
  long   num_rects = 1e8;
  double area, sum;
  double width = 1.0 / (double) num_rects;

  cout << "Starting..." << endl;

  for (int i = 0; i < N; i++) {
    t.start();
    ParallelPI obj(num_rects);
    parallel_reduce(blocked_range<int>(0, num_rects, GRAIN),obj);
    area = obj.area;
    ms  += t.stop();
  }

  cout << "PI = " << area << endl;
  cout << "Avg time = " << (ms/N) << " ms" << endl;
  return 0;
}
