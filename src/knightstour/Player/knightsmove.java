
package knightstour.Player;

import static knightstour.AutoPlay.Knight.NUMBER_Knight_Max;

/**
 *
 * @author My Pc
 */
public class knightsmove {
    private int x ;
    private int y ;
    private int w ;
    private int h ;
    private String value;
    
    public static final String x_value = "";
    public static final String y_value = "1";
    
  public knightsmove() {
    this.value = y_value;
    }
  public knightsmove(int x, int y, int w, int h, String value) {
        this();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

 
}
