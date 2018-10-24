/*----------------------------------------------------------------

*

* Actividad de programación: Calculando Pi con threads en Java

* Fecha: 11-Sep-2015

* Autor: A01204739 Esteban Pérez Herrera

*

*--------------------------------------------------------------*/
public class PI_thread extends Thread {
  private int start, end;
  private int NUM_RECTS;
  private double result;

  public PI_thread(int start, int end, int NUM_RECTS){
    super();
    this.start=start;
    this.end=end;
    this.NUM_RECTS=NUM_RECTS;
    this.result=0;
  }

  public double getResult(){
    return result;
  }

  public void run(){
    result=0;

    double mid, height, sum=0, i;
    double width=1.0 / (double)NUM_RECTS;

    for (i = start; i < end; i++) {
			mid = (i + 0.5) * width;
			height = 4.0 / (1.0 + (mid * mid));
			sum += height;
		}
    result = width * sum;
  }
}
