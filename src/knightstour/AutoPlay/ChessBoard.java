/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knightstour.AutoPlay;

import knightstour.giaodien.GameMode;
import knightstour.AutoPlay.Knight;
import knightstour.AutoPlay.SquareB;



// Biểu diễn bàn cờ vua.
public class ChessBoard extends GameMode {
	private SquareB[][] Board_playing; //Mảng 2 chiều, đại diện cho bàn cờ.									
	private Knight Knight_Current; //Tham chiếu đến Knight hiện tại trên 
                                      //bàn cờ.
	private final int Size_board ; //Kích thước của bàn cờ, (2x2, 3x3, ... ).

        //Tạo bàn cờ vua.
	public ChessBoard(Knight knight) {
            
		Size_board = 8; // bàn cờ 8x8
		Board_playing = new SquareB[Size_board][Size_board];
		Knight_Current = knight;
		screateSquareScreate();
		createHeuristicsB();
	}

	public ChessBoard(Knight knight, int dimension) {
                
		Size_board = dimension; //bàn cờ kích thước bất kì.
		Board_playing = new SquareB[Size_board][Size_board];
		Knight_Current = knight;
		screateSquareScreate();
		createHeuristicsB();
	}

        //tạo các ô trong bàn cờ.
	public void screateSquareScreate() {
		for (int row = 0; row < Size_board; row++) {
			for (int col = 0; col < Size_board; col++) {
				Board_playing[row][col] = new SquareB();
			}
		}
	}

	public void addKnight(Knight knight) {
		Knight_Current = knight;
	}

	public int getSizeBoard() {
		return Size_board;
	}

        //Trả về đối tượng hình vuông tại một hàng và cột xác định.
	public SquareB getAtSquare(int row, int col) {
		return Board_playing[row][col];
	}

        //Đặt giá trị 'true' trên một hình vuông để chỉ ra rằng nó đã được 
        //truy cập.
	public void setVisitedSquare(int row, int col) {
		Board_playing[row][col].setVisitedB(true);
	}

        //Trả lại giá trị 'true' trên một hình vuông để chỉ ra rằng nó đã 
        //được truy cập.
	public boolean isVisitedSquare(int row, int col) {
		return Board_playing[row][col].isVisitedB();
	}

        //Thiết lập mức truy cập của một hình vuông nằm ở các tham số 
        // hàng và cột.
	public void setSquareAcessibility(int row, int col, int accessibility) {
		Board_playing[row][col].setNumberSTour(accessibility);
	}

        //Trả lại mức truy cập của một hình vuông nằm ở hàng và cột 
        //được xác định bởi các tham số.
	public int getSquareAcessibility(int row, int col) {
		return Board_playing[row][col].getNumberSTour();
	}

        //Giảm khả năng tiếp cận của hình vuông bằng 1 hàng và cột 
        //được xác định bởi các tham số.
	public void decrSquareAcessibility(int row, int col) {
		Board_playing[row][col].decrNumberSTour();
	}

        //Thiết lập một hình vuông cho giá trị truy cập di chuyển hiện tại 
        //của Knight tại vị trí được chỉ định bởi các tham số.
	public void setSquareMoveNumbers(int row, int col, int moveCounter) {
		Board_playing[row][col].setMoveNumberB(moveCounter);
	}

        //Trả lại giá trị bộ đếm truy cập tại vị trí 
        //được chỉ định bởi các tham số.
	public int getSquareMoveNumbers(int row, int col) {
		return Board_playing[row][col].getMoveNumberB();
	}

        //Đánh dấu hình vuông của bảng ở hàng và cột được cung cấp 
        //bởi các tham số với số biến số truy cập của Knight.
	public void markBoardSquareB(int curRow, int curCol, int moveCounter) {
		setVisitedSquare(curRow, curCol);
		setSquareMoveNumbers(curRow, curCol, moveCounter);
	}
        
        //In bảng trò chơi tất cả các trạng thái có thể.
	public void showGameBoardB() {
		for (int row = 0; row < Size_board; row++) {
			for (int col = 0; col < Size_board; col++) {
		            System.out.printf("%3d", Board_playing[row][col].getMoveNumberB());
				if (col == Size_board - 1) {
					System.out.println();
				}
			}
		}
	}

        //In các heuristics có khả năng truy cập.
	public void showHeuristicsB() {
		for (int row = 0; row < Size_board; row++) {
			for (int col = 0; col < Size_board; col++) {
				System.out.printf("%3d", getSquareAcessibility(row, col));
				if (col == Size_board - 1) {
					System.out.println();
				}
			}
		}
	}
        
        //Sử dụng một đối tượng knight dummy để chơi qua tất cả các di chuyển 
        //có thể cho mỗi hình vuông trên bàn cờ.
        //Số lượng di chuyển có thể từ mỗi hình vuông được ghi lại trong mảng.
	private void createHeuristicsB() {
		int linkCount = 0, //Lưu trữ số ô liên kết cho một hình vuông cụ thể
		testRow, //Hàng hiện tại của Knight.
		testCol; //Cột hiện tại của Knight.

		//Thêm giá trị số di chuyển vào hàng hiện tại của Knight
                //và kiểm tra xem đó có phải là ứng cử viên di chuyển hay không.
                //Nếu vị trí nằm trên bàn cờ, nó sẽ tăng số liên kết.
		for (int row = 0; row < Size_board; row++) {
			for (int col = 0; col < Size_board; col++) {
				for (int moveNum = 0; moveNum < Knight.NUMBER_Knight_Max; moveNum++) {
					testRow = row + Knight_Current.getVerticalMoveValueB(moveNum);
					testCol = col
							+ Knight_Current.getHorizontalMoveValueb(moveNum);

					if (checkSquaresExists(testRow, testCol) == true) {
						linkCount++;
					}
				}

				//Đặt tính truy cập và đặt lại số liên kết cho cột tiếp theo.
				setSquareAcessibility(row, col, linkCount);
				linkCount = 0;
			}
		}
	}

        //Sử dụng một hàng và cột hiện tại của bàn cờ để xác định xem hàng 
        //và cột nằm trong giới hạn của bàn cờ và chưa được truy cập. 
        //Trả về true, nếu không gian là hợp lệ, nếu không thì trả về false.
	public boolean checkSquaresExists(int testRow, int testCol) {
		if ((testRow >= 0 && testRow < Size_board)
				&& (testCol >= 0 && testCol < Size_board)) {
			if (isVisitedSquare(testRow, testCol) == false) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
        
        //Kiểm tra tất cả các ô vuông có thể truy cập từ ô vuông hiện tại 
        //và giảm khả năng tiếp cận của chúng xuống 1.
	public void lowerAcessibility() {
		int[] possibleMoves_possible;
		int So_luot_di_chuyen, curRow = Knight_Current.getCurrentRowS(), curCol = Knight_Current
				.getCurrentColB(), changeRow, changeCol;

		//số  trạng thái di chuyển có thể.
		So_luot_di_chuyen = Knight_Current.find_Num_Moves(this);

		//Tạo một mảng với kích thước của số di chuyển có thể
		possibleMoves_possible = new int[So_luot_di_chuyen];

		//số di chuyển của các trạng thái có thể.
		possibleMoves_possible = Knight_Current.find_Moves(this, So_luot_di_chuyen);

		for (int moveNum = 0; moveNum < possibleMoves_possible.length; moveNum++) {
			changeRow = curRow + Knight_Current.getVerticalMoveValueB(possibleMoves_possible[moveNum]);
			changeCol = curCol + Knight_Current.getHorizontalMoveValueb(possibleMoves_possible[moveNum]);

			if (isVisitedSquare(changeRow, changeCol) == false) {
				decrSquareAcessibility(changeRow, changeCol);
			}
		}
	}
}
