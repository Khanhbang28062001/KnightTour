
package knightstour_nc;

import static knightstour.chess.Knight.NUM_ALLOWED_MOVES;

/**
 *
 * @author My Pc
 */
public class knightsmove {
    private int[] horizontalMovesAvail; //Giá trị di chuyển ngang có sẵn cho knight.
    private int[] verticalMovesAvail; 
    private int currentRow; //Vị trí hàng hiện tại của knight.
    private int currentCol; //Vị trí cột hiện tại củaknight
    private int previousRow; //Hàng của knight trước khi di chuyển.
    private int previousCol; //Cột của knight trước khi di chuyển.
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
    private void fillMoves() {
		horizontalMovesAvail[0] = 2; //phải
		horizontalMovesAvail[1] = 1; //phải
		horizontalMovesAvail[2] = -1; //trái
		horizontalMovesAvail[3] = -2; //trái
		horizontalMovesAvail[4] = -2; //trái
		horizontalMovesAvail[5] = -1; //trái
		horizontalMovesAvail[6] = 1; //phải
		horizontalMovesAvail[7] = 2; //phải

		verticalMovesAvail[0] = -1; //lên
		verticalMovesAvail[1] = -2; //lên
		verticalMovesAvail[2] = -2; //lên
		verticalMovesAvail[3] = -1; //lên
		verticalMovesAvail[4] = 1; //xuống
		verticalMovesAvail[5] = 2; //xuống
		verticalMovesAvail[6] = 2; //xuống
		verticalMovesAvail[7] = 1; //xuống
	}
 
}
