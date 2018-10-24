/*----------------------------------------------------------------

*

* Actividad de programación: Calculando Pi con threads en Java

* Fecha: 11-Sep-2015

* Autor: A01204739 Esteban Pérez Herrera

*

*--------------------------------------------------------------*/
public class PI_non_thread{
  private int NUM_RECTS;
  private double PI_value;

  public PI_non_thread(int NUM_RECTS){
    this.NUM_RECTS = NUM_RECTS;
    this.PI_value = 0;
  }

  public double getPI_value(){
    return PI_value;
  }

  public void calculatePI(){
    double mid, height, sum=0, i;
    double width=1.0 / (double)NUM_RECTS;

    for (i = 0; i < NUM_RECTS; i++) {
			mid = (i + 0.5) * width;
			height = 4.0 / (1.0 + (mid * mid));
			sum += height;
		}
    PI_value = width * sum;
  }
}
