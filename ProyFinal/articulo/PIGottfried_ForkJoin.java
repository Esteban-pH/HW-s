import java.util.concurrent.RecursiveTask;

public class PIGottfried_ForkJoin extends RecursiveTask<Double>{
  private static final long MIN = 100_000;
  private int start, end;

  public PIGottfried_ForkJoin(int start, int end){
    this.start = start;
    this.end   = end;
  }

  public Double computeDirectly(){
    double sum = 0, PI;
    int flag = 1;

    for(int i = start; i < end; i+=2, flag++){
      if(flag%2==1)
        sum+=1.0/(double)i;
      else
        sum-=1.0/(double)i;
    }
    PI=4.0*sum;
    return PI;
  }

  @Override
  protected Double compute(){
    if((end - start) <= PIGottfried_ForkJoin.MIN)
      return computeDirectly();
    else{
      int mid = (end + start) / 2;
      PIGottfried_ForkJoin lowerMid = new PIGottfried_ForkJoin(start, mid);
      lowerMid.fork();
      PIGottfried_ForkJoin upperMid = new PIGottfried_ForkJoin(mid, end);
      return (upperMid.compute() + lowerMid.join());
    }
  }
}
