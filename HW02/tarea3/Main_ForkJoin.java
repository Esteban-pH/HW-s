/*----------------------------------------------------------------

*

* Actividad de programación: Fork-join framework

* Fecha: 19-Sep-2015

* Autor: A01204739 Esteban Pérez Herrera

*

*--------------------------------------------------------------*/
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.concurrent.ForkJoinPool;

public class Main_ForkJoin{
  private static final int MAXTHREADS = Runtime.getRuntime().availableProcessors();

  public static void main(String args[]) throws Exception {
    ForkJoinPool pool;
    long startTime, stopTime;
    double acum = 0;

    if (args.length != 1) {
			System.out.println("usage: java image_file");
			System.exit(-1);
		}

    final String fileName = args[0];
		File srcFile = new File(fileName);
        final BufferedImage source = ImageIO.read(srcFile);

		int w = source.getWidth();
		int h = source.getHeight();
		int src[] = source.getRGB(0, 0, w, h, null, 0, w);
		int dest[] = new int[src.length];

    for (int i = 0; i < 10; i++) {
			startTime = System.currentTimeMillis();

      pool = new ForkJoinPool(MAXTHREADS);
			pool.invoke(new GrayScale_ForkJoin(src, dest, w, h, 0, src.length));

			stopTime = System.currentTimeMillis();
			acum += (stopTime - startTime);
		}

    System.out.printf("avg time = %.5f\n", (acum / 10));
		final BufferedImage destination = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		destination.setRGB(0, 0, w, h, dest, 0, w);

    javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               ImageFrame.showImage("Original - " + fileName, source);
            }
        });

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               ImageFrame.showImage("GrayScale- " + fileName, destination);
            }
        });
  }
}
