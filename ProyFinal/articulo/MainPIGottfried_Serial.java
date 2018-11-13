public class MainPIGottfried_Serial{
  private static int SIZE = 1_000_000_000;
  public static void main(String arg[]){
    long startTime, stopTime;
    double acum = 0;
    System.out.printf("Starting...\n");
    PIGottfried_Serial x = new PIGottfried_Serial(SIZE);
    for (int i = 0; i < Utils.N; i++) {
      startTime = System.currentTimeMillis();
      x.calculatePI();
      stopTime = System.currentTimeMillis();
			acum += (stopTime - startTime);
    }
    System.out.printf("PI: %f\n",  x.getPI());
    System.out.printf("Avg time: %.5f ms\n", (acum / Utils.N));
  }
}
