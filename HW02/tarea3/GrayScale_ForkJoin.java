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
import java.util.concurrent.RecursiveAction;

public class GrayScale_ForkJoin extends RecursiveAction{
  private int src[], dest[], width, height, start, end;
  private static final long MIN = 10_000;

  public GrayScale_ForkJoin(int src[], int dest[], int width, int height, int start, int end) {
		this.src = src;
		this.dest = dest;
		this.width = width;
		this.height = height;
    this.start = start;
    this.end = end;
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

  void computeDirectly(){
    int index;
    int ren, col;

    for (index = start; index < end; index++) {
      ren = index / width;
      col = index % width;
      gray_pixel(ren, col);
    }
  }

  @Override
	protected void compute() {
		if ( (this.end - this.start) <= GrayScale_ForkJoin.MIN ) {
			computeDirectly();
		} else {
			int middle = (end + start) / 2;
			invokeAll(new GrayScale_ForkJoin(src, dest, width, height, start, middle),
					  new GrayScale_ForkJoin(src, dest, width, height, middle, end));
		}
	}

}
