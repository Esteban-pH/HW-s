/*----------------------------------------------------------------

*

* Actividad de programación: Calculando Pi con threads en Java

* Fecha: 11-Sep-2015

* Autor: A01204739 Esteban Pérez Herrera

*

*--------------------------------------------------------------*/
public class PI_thread_Main{
  private static int NUM_RECTS =  100_000_000;
  private static final int MAXTHREADS = Runtime.getRuntime().availableProcessors();

  public static void main(String args []){
    PI_thread threads[];
    int block;
		double startTime, stopTime, total = 0;
		double acum = 0;

    block = NUM_RECTS/MAXTHREADS;
    threads =  new PI_thread[MAXTHREADS];

    for (int j=1; j<=10 ; j++ ) {
      for (int i=0; i<threads.length; i++) {
        if (i != threads.length - 1) {
					threads[i] = new PI_thread((i * block), ((i + 1) * block), NUM_RECTS);
				} else {
					threads[i] = new PI_thread((i * block), NUM_RECTS, NUM_RECTS);
				}
      }

      startTime = System.currentTimeMillis();

      for (int i = 0; i < threads.length; i++) {
				threads[i].start();
			}

      for (int i = 0; i < threads.length; i++) {
				try {
					threads[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

      stopTime = System.currentTimeMillis();
			acum +=  (stopTime - startTime);

      if (j == 10) {
				total = 0;
				for (int i = 0; i < threads.length; i++) {
					total += threads[i].getResult();
				}
			}
    }
    System.out.printf("sum = %.15f\n", total);
		System.out.printf("avg time = %.5f ms\n", (acum / 10));
  }
}
