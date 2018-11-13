public class PIGottfried_threads extends Thread{
  private double sum, flag;
  private double PI;
  private int start, end;

  public PIGottfried_threads(int start, int end){
    this.start =  start;
    this.end   = end;
  }

  public double getPI(){
    return PI;
  }

  public void run(){
    this.sum  = 0;
    this.flag = 1;
    for(int i = start; i < end; i+=2, flag++){
      if(flag%2==1)
        sum+=1.0/(double)i;
      else
        sum-=1.0/(double)i;
    }
    PI=4.0*sum;
  }
}
