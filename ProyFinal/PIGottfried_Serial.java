public class PIGottfried_Serial{
  private double sum, flag;
  private double PI;
  private int SIZE;

  public PIGottfried_Serial(int SIZE){
    this.SIZE = SIZE;
  }

  public double getPI(){
    return PI;
  }

  public void calculatePI(){
    sum  = 0;
    flag = 1;
    for(int i = 1; i < SIZE; i+=2, flag++){
      if(flag%2==1)
        sum+=1.0/(double)i;
      else
        sum-=1.0/(double)i;
    }
    PI=4.0*sum;
  }
}
