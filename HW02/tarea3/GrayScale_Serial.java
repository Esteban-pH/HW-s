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

public class GrayScale_Serial{
  private int src[], dest[], width, height;

  public GrayScale_Serial(int src[], int dest[], int width, int height) {
		this.src = src;
		this.dest = dest;
		this.width = width;
		this.height = height;
	}

  private void gray_pixel(int ren, int col) {
		int i, j;
		int pixel, dpixel;
		float r=0, g=0, b=0;

    pixel = src[(ren*width)+col];
    r = (float) ((pixel & 0x00ff0000) >> 16);
    g = (float) ((pixel & 0x0000ff00) >> 8);
    b = (float) ((pixel & 0x000000ff) >> 0);

    r*=.299;
    g*=.587;
    b*=.114;

		dpixel = (0xff000000)
				| (((int) (r + g + b)) << 16)
				| (((int) (r + g + b)) << 8)
				| (((int) (r + g + b)) << 0);
		dest[(ren * width) + col] = dpixel;
	}

  void doMagic() {
    int index, size;
    int ren, col;

    size = width * height;
    for (index = 0; index < size; index++) {
      ren = index / width;
      col = index % width;
      gray_pixel(ren, col);
    }
  }

}
