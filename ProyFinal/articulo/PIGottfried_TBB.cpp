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

const int SIZE  = 1e9;
const int GRAIN = 1e6;

class PIGottfried{
private:
  int i, flag;
public:
  double sum;
  PIGottfried() : sum(0), flag(1){}
   PIGottfried(PIGottfried &x, split) : sum(0), flag(1){}

  void operator()(const blocked_range<int> &r){
    for(i = r.begin(); i < r.end(); i+=2, flag++){
      if(flag%2)
        sum+=1.0/(double)i;
      else
        sum-=1.0/(double)i;
    }
  }

  void join(const PIGottfried &x){
    sum += x.sum;
  }
};

int main(){
  Timer t;
  double ms = 0;
  double PI;

  cout << "Starting..." << endl;
  for (int i = 0; i < N; i++){
    t.start();
    PIGottfried obj;
    parallel_reduce(blocked_range<int>(1, SIZE, GRAIN),obj);
    PI = 4*(obj.sum);
    ms  += t.stop();
  }

  cout << "PI: " << PI << endl;
  cout << "Avg time: " << (ms/N) << " ms" << endl;
  return 0;
}
