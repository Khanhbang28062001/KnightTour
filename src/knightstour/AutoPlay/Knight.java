package knightstour.AutoPlay;

public class Knight {
	private int[] horizontallMoveAvail; //Giá trị di chuyển ngang có sẵn cho knight.
	private int[] verticallMoveAvail; //Giá trị di chuyển dọc có sẵn cho knight.
	private int currentHang; //Vị trí hàng hiện tại của knight.
	private int currentCot; //Vị trí cột hiện tại củaknight
	private int previousHang; //Hàng của knight trước khi di chuyển.
	private int previousCot; //cột của knight trước khi di chuyển.
	private int moveCounterKnight; //Theo dõi số lần di chuyển của hiệp sỹ (1-64)
	public static final int NUMBER_Knight_Max = 8; //Số di chuyển mà hiệp sĩ được phép thực hiện

	public Knight() {
		horizontallMoveAvail = new int[NUMBER_Knight_Max];
		verticallMoveAvail = new int[NUMBER_Knight_Max];
		moveCounterKnight = 1;
		setCurrenttRowS(0);
		setCurrenttPillar(0);
		setPreviousRowS(0);
		setPreviousPillar(0);
		fillMovess();
	}

	public Knight(int startRow, int startCol) {
		horizontallMoveAvail = new int[NUMBER_Knight_Max];
		verticallMoveAvail = new int[NUMBER_Knight_Max];
		moveCounterKnight = 1;
		setCurrenttRowS(startRow);
		setCurrenttPillar(startCol);
		setPreviousRowS(startRow);
		setPreviousPillar(startCol);
		fillMovess();
	}

        //Đặt hàng và cột trước đó, và tăng bộ đếm di chuyển.
	public void move(int moveNumber) {
		if (moveNumber < 0 || moveNumber > NUMBER_Knight_Max - 1) {
			System.out.println("ERROR: Invalid move: " + moveNumber);
			return;
		} else {
			setPreviousRowS(currentHang);
			setPreviousPillar(currentCot);
			setCurrenttRowS(currentHang + verticallMoveAvail[moveNumber]);
			setCurrenttPillar(currentCot + horizontallMoveAvail[moveNumber]);
			incMoveCounterB();
		}
	}
        
        //Tìm thấy số lần di chuyển có thể trên bàn cờ từ hàng và cột bắt đầu.
	public int find_Num_Moves(ChessBoard board) {
		int possibleMovesCounter = 0, //Đếm số lần di chuyển có thể
		startRow = getCurrentRowS(), startCol = getCurrentColB(), testH, testC;

                //thêm một giá trị số di chuyển vào hàng hiện tại của knight và 
                //kiểm tra xem nó có phải là một ứng cử viên di chuyển hay không
                //nếu vị trí nằm trên bàn cờ và không được báo trước, 
                //bộ đếm di chuyển có thể được tăng lên.
		for (int moveNum = 0; moveNum < Knight.NUMBER_Knight_Max; moveNum++) {
			testH = startRow + getVerticalMoveValueB(moveNum);
			testC = startCol + getHorizontalMoveValueb(moveNum);

			if (board.checkSquaresExists(testH, testC) == true) {
				possibleMovesCounter++;
			}
		}

		return possibleMovesCounter;
	}

        //Tìm các chuyển động trên bàn cờ từ vị trí hiện tại của knight
        //và trả về một loạt các trạng thái có thể.
	public int[] find_Moves(ChessBoard chessBoard, int numPossibleMoves) {
		int[] possibleMovesknight = new int[numPossibleMoves];
		int storeCurentsRow = getCurrentRowS(), //lưu hàng hiện tại của knight
		storeCurCol = getCurrentColB(), //lưu cột hiện tại của knight
		testH,
		testC,
		goodMoveCount = 0;
                
		for (int movesNumber = 0; movesNumber < Knight.NUMBER_Knight_Max; movesNumber++) {
			testH = storeCurentsRow + getVerticalMoveValueB(movesNumber);
			testC = storeCurCol + getHorizontalMoveValueb(movesNumber);

			if (chessBoard.checkSquaresExists(testH, testC) == true) {
				possibleMovesknight[goodMoveCount++] = movesNumber;
			}
		}

		return possibleMovesknight;
	}
        
	public int findBestMoveOfKnight(ChessBoard board, int[] possibleMovesKnight) {
		int storeCurRow = getCurrentRowS(),
		storeCurCol = getCurrentColB(),
		testRow,
		testCol,
		lowestAccessibility, testAccessibility, moveNumWithLowest;

		//Nếu mảng di chuyển có thể lớn hơn 1, thì có ít nhất 2 di 
                //chuyển để so sánh khả năng truy cập.
		if (possibleMovesKnight.length > 1) {
			// Give iLowestAccessibility a starting value to compare the rest to
			testRow = storeCurRow + getVerticalMoveValueB(possibleMovesKnight[0]);
			testCol = storeCurCol + getHorizontalMoveValueb(possibleMovesKnight[0]);
			lowestAccessibility = board.getSquareAcessibility(testRow,
					testCol);
			moveNumWithLowest = possibleMovesKnight[0];

			//Kiểm tra mỗi lần di chuyển trong mảng so với giá trị thấp nhất
                        //iMoveNum in loop header giá trị ban đầu thay đổi từ 0 đến 1 
                        //Lý do: Giá trị thấp nhất ban đầu được thiết lập để di chuyển 0, 
                        //nếu iMoveNum còn lại ở 0, thì nó không cần thiết phải 
                        //so sánh nó trong vòng lặp
			for (int moves_Number = 1; moves_Number < possibleMovesKnight.length; moves_Number++) {
				testRow = storeCurRow
						+ getVerticalMoveValueB(possibleMovesKnight[moves_Number]);
				testCol = storeCurCol
						+ getHorizontalMoveValueb(possibleMovesKnight[moves_Number]);

				testAccessibility = board.getSquareAcessibility(testRow,
						testCol);

				//Nếu giá trị được kiểm tra thấp hơn giá trị 
                                //thấp nhất hiện thời lưu trữ giá trị trợ năng 
                                //và lưu trữ số di chuyển với giá trị thấp nhất 
                                //được tìm thấy trong vị trí mảng đó

				if (testAccessibility < lowestAccessibility
						|| lowestAccessibility < 1) {
					lowestAccessibility = testAccessibility;
					moveNumWithLowest = possibleMovesKnight[moves_Number];
				}
			}

			return moveNumWithLowest;
		}
		//Trả lại giá trị đầu tiên trong mảng nếu chiều dài mảng là 1, 
                //bởi vì không có chuyển động nào khác để so sánh
		else
			return possibleMovesKnight[0];
	}
        
        //đặt hàng hiện tại.
	public void setCurrenttRowS(int row) {
		currentHang = row;
	}

	//Trả lại giá trị của hàng hiện tại.
	public int getCurrentRowS() {
		return currentHang;
	}

        //đặt cột hiện tại.
	public void setCurrenttPillar(int col) {
		currentCot = col;
	}

        //Trả lại giá trị của cột hiện tại
	public int getCurrentColB() {
		return currentCot;
	}

        //đặt hàng trước đó.
	public void setPreviousRowS(int row) {
		previousHang = row;
	}

	//trả lại giá trị hàng trước đó.
	public int getPreviousRowB() {
		return previousHang;
	}

        //đặt cột trước đó.
	public void setPreviousPillar(int col) {
		previousCot = col;
	}

        //trả lại giá trị cột trước đó.
	public int getPreviousColB() {
		return previousCot;
	}

	//Tăng biến thành viên truy cập di chuyển bằng 1.
	public void incMoveCounterB() {
		moveCounterKnight++;
	}

	//Trả lại giá trị của bộ đếm di chuyển
	public int getMoveCounterB() {
		return moveCounterKnight;
	}

	//số di chuyển và trả về giá trị của hàng đang di chuyển.
	public int getHorizontalMoveValueb(int moveNumber) {
		return horizontallMoveAvail[moveNumber];
	}

	//số di chuyển và trả về giá trị của cột đang di chuyển.
	public int getVerticalMoveValueB(int moveNumber) {
		return verticallMoveAvail[moveNumber];
	}

	//Lập bản đồ di chuyển theo chiều ngang và chiều dọc của knight.
	private void fillMovess() {
		horizontallMoveAvail[0] = 2; //phải
		horizontallMoveAvail[1] = 1; //phải
		horizontallMoveAvail[2] = -1; //trái
		horizontallMoveAvail[3] = -2; //trái
		horizontallMoveAvail[4] = -2; //trái
		horizontallMoveAvail[5] = -1; //trái
		horizontallMoveAvail[6] = 1; //phải
		horizontallMoveAvail[7] = 2; //phải

		verticallMoveAvail[0] = -1; //lên
		verticallMoveAvail[1] = -2; //lên
		verticallMoveAvail[2] = -2; //lên
		verticallMoveAvail[3] = -1; //lên
		verticallMoveAvail[4] = 1; //xuống
		verticallMoveAvail[5] = 2; //xuống
		verticallMoveAvail[6] = 2; //xuống
		verticallMoveAvail[7] = 1; //xuống
	}
}
