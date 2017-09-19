package py.com.mabpg.ruidospeckle.main;

/**
 *
 * @author daasalbion Clase que reprenta los pixeles dentro de la imagen Util
 * puesto que imagej los manejas de esta manera
 */
public class Pixel {

    private int x;
    private int y;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Pixel{" + "x=" + x + ", y=" + y + '}';
    }

}
