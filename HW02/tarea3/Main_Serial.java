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

public class Main_Serial{
  public static void main(String args[]) throws Exception {
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

    GrayScale_Serial e = new GrayScale_Serial(src, dest, w, h);

    for (int i = 0; i < 10; i++) {
			startTime = System.currentTimeMillis();
			e.doMagic();
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
