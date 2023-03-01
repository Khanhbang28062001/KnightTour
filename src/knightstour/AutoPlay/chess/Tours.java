package knightstour.AutoPlay.chess;

import knightstour.AutoPlay.ChessBoard;
import knightstour.AutoPlay.Knight;
import knightstour.AutoPlay.SquareB;

//Sử dụng bàn cờ vua và một knight để chơi trò chơi Knight's Tour.

public class Tours {
	private ChessBoard chess_Board; //bàn cờ.
	private Knight Knight_Moves; // Knight di chuyển.
	private boolean MoveSquare = false; //true nếu knight có thể di chuyển, 
                                            //không thì fale

	public Tours(ChessBoard chesBoard, Knight knight) {
		Knight_Moves = knight;
		this.chess_Board = chesBoard;
		this.chess_Board.addKnight(Knight_Moves);
	}

	public Tours() {
		Knight_Moves = new Knight();
		chess_Board = new ChessBoard(Knight_Moves);
	}

	public Tours(int dimension) {
		Knight_Moves = new Knight();
		chess_Board = new ChessBoard(Knight_Moves, dimension);
	}

	public Tours(int startRow, int startCol) {
		Knight_Moves = new Knight(startRow, startCol);
		chess_Board = new ChessBoard(Knight_Moves);
	}

	public Tours(int startRow, int startCol, int dimension) {
		Knight_Moves = new Knight(startRow, startCol);
		chess_Board = new ChessBoard(Knight_Moves, dimension);
	}

	public void resetKnightTour() {
		Knight_Moves = null;
		chess_Board = null;
	}

	//Đặt vị trí bắt đầu của knight trên bảng cờ vua.
	public void setStartPositionKnight(int startHang, int startCot) {
		Knight_Moves.setCurrenttRowS(startHang);
		Knight_Moves.setCurrenttPillar(startCot);
		Knight_Moves.setPreviousRowS(startHang);
		Knight_Moves.setPreviousPillar(startCot);
	}

	public ChessBoard getChesBoard() {
		return chess_Board;
	}

	public Knight getKnights() {
		return Knight_Moves;
	}

	public SquareB getSquareInBoard(int hang, int cot) {
		return chess_Board.getAtSquare(hang, cot);
	}

        //Nếu knight không có một động thái, sau đó các tour kết thúc.
	public boolean Move() {
		return MoveSquare;
	}

	//
	public void playGameKnightTours() {
		do {
			moveKnightTour();

		} while (MoveSquare == true);

		System.out.println("Game Board");
		chess_Board.showGameBoardB();

		System.out.printf("Number of moves: %d\n", Knight_Moves.getMoveCounterB());

		System.out.println();
	}

	public void moveKnightTour() {
		int numPossibleKnightMoves, bestKnightMoves;
		int[] possibleKnightsMoves;

		MoveSquare = false;
		//Đánh dấu trạng thái đã truy cập và di chuyển tại vị trí hiện tại
		chess_Board.markBoardSquareB(Knight_Moves.getCurrentRowS(),
                                           Knight_Moves.getCurrentColB(),
                                           Knight_Moves.getMoveCounterB());

		//Giá trị tiếp cận thấp hơn của các ô vuông xung quanh.
		chess_Board.lowerAcessibility();

		//Tìm số di chuyển có thể từ vị trí hiện tại.
		numPossibleKnightMoves = Knight_Moves.find_Num_Moves(chess_Board);

		//Nếu số lượng di chuyển có thể >0, có ít nhất một di chuyển có thể.
		if (numPossibleKnightMoves > 0) {
			MoveSquare = true;

			//Tìm các chuyển động có thể từ vị trí hiện tại và trở lại thành một mảng
			possibleKnightsMoves = Knight_Moves.find_Moves(chess_Board,
					numPossibleKnightMoves);

			//Sử dụng mảng di chuyển có thể, tìm động thái tốt nhất dựa trên khả năng tiếp cận heuristic
			bestKnightMoves = Knight_Moves.findBestMoveOfKnight(chess_Board, possibleKnightsMoves);

			//Di chuyển knight tốt nhất
			Knight_Moves.move(bestKnightMoves);
		}
	}
}
