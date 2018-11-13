public class MainPIGottfried_threads{
  private static int SIZE = 1_000_000_000;
  private static final int MAXTHREADS = Runtime.getRuntime().availableProcessors();

  public static void main(String args[]){
    PIGottfried_threads threads[];
    int block;
		long startTime, stopTime;
		double acum = 0, PI = 0;

    block =  SIZE/MAXTHREADS;
    threads = new PIGottfried_threads[MAXTHREADS];

    for (int j = 1; j <= Utils.N; j++){
      for (int i = 0; i < threads.length; i++) {
				if (i != threads.length - 1)
					threads[i] = new PIGottfried_threads((i * block) + 1, ((i + 1) * block) + 1);
				 else
					threads[i] = new PIGottfried_threads((i * block) + 1, SIZE);
			}

      startTime = System.currentTimeMillis();
      for (int i = 0; i < threads.length; i++)
        threads[i].start();

        for (int i = 0; i < threads.length; i++) {
  				try{
  					threads[i].join();}
  				catch (InterruptedException e){
  					e.printStackTrace();}
  			}
      stopTime = System.currentTimeMillis();
			acum +=  (stopTime - startTime);

      if (j == Utils.N){
				PI = 0;
				for (int i = 0; i < threads.length; i++)
					PI += threads[i].getPI();
				System.out.printf("PI: %f\n", PI);
			}
    }
    System.out.printf("Avg time: %.5f ms\n", (acum / Utils.N));
  }
}
