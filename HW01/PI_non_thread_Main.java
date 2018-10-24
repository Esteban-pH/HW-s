/*----------------------------------------------------------------

*

* Actividad de programación: Calculando Pi con threads en Java

* Fecha: 11-Sep-2015

* Autor: A01204739 Esteban Pérez Herrera

*

*--------------------------------------------------------------*/
public class  PI_non_thread_Main{
  private static int NUM_RECTS =  100_000_000;

  public static void main(String[] args) {
    long startTime, stopTime;
    double acum = 0;

    PI_non_thread x = new PI_non_thread(NUM_RECTS);

    for (int i = 0; i < 10; i++) {
			startTime = System.currentTimeMillis();
			x.calculatePI();
			stopTime = System.currentTimeMillis();
			acum += (stopTime - startTime);
		}

    System.out.printf("PI = %.15f\n", x.getPI_value());
		System.out.printf("avg time = %.5f ms\n", (acum / 10));
  }
}
