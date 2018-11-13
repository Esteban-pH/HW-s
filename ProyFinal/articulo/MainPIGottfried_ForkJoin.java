import java.util.concurrent.ForkJoinPool;

public class MainPIGottfried_ForkJoin{
  private static int SIZE = 1_000_000_000;
	private static final int MAXTHREADS = Runtime.getRuntime().availableProcessors();

  public static void main(String args[]){
    ForkJoinPool pool;
    long startTime, stopTime;
		double ms, PI, acum=0;

    System.out.printf("Starting...\n");
    startTime = System.currentTimeMillis();
    pool = new ForkJoinPool(MAXTHREADS);
    PI   = pool.invoke(new PIGottfried_ForkJoin(1, SIZE));
    stopTime = System.currentTimeMillis();
    acum += (stopTime - startTime);

    System.out.printf("PI: %f\n", PI);
    System.out.printf("Avg time: %.5f ms\n", (acum / Utils.N));
  }
}
