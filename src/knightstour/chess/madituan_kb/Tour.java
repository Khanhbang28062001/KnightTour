package knightstour.chess.madituan_kb;

import knightstour.chess.ChessBoard;
import knightstour.chess.Knight;
import knightstour.chess.Square;

//Sử dụng bàn cờ vua và một knight để chơi trò chơi Knight's Tour.

public class Tour {
	private ChessBoard chess_Board; //bàn cờ.
	private Knight Knight_Moves; // Knight di chuyển.
	private boolean foundMove = false; //true nếu knight có thể di chuyển, 
                                            //không thì fale

	public Tour(ChessBoard chessBoard, Knight knight) {
		Knight_Moves = knight;
		this.chess_Board = chessBoard;
		this.chess_Board.addKnight(Knight_Moves);
	}

	public Tour() {
		Knight_Moves = new Knight();
		chess_Board = new ChessBoard(Knight_Moves);
	}

	public Tour(int dimension) {
		Knight_Moves = new Knight();
		chess_Board = new ChessBoard(Knight_Moves, dimension);
	}

	public Tour(int startRow, int startCol) {
		Knight_Moves = new Knight(startRow, startCol);
		chess_Board = new ChessBoard(Knight_Moves);
	}

	public Tour(int startRow, int startCol, int dimension) {
		Knight_Moves = new Knight(startRow, startCol);
		chess_Board = new ChessBoard(Knight_Moves, dimension);
	}

	public void resetTour() {
		Knight_Moves = null;
		chess_Board = null;
	}

	//Đặt vị trí bắt đầu của knight trên bảng cờ vua.
	public void setStartPosition(int startRow, int startCol) {
		Knight_Moves.setCurrentRow(startRow);
		Knight_Moves.setCurrentCol(startCol);
		Knight_Moves.setPreviousRow(startRow);
		Knight_Moves.setPreviousCol(startCol);
	}

	public ChessBoard getChessBoard() {
		return chess_Board;
	}

	public Knight getKnight() {
		return Knight_Moves;
	}

	public Square getSquare(int row, int col) {
		return chess_Board.getSquareAt(row, col);
	}

        //Nếu knight không có một động thái, sau đó các tour kết thúc.
	public boolean hasMove() {
		return foundMove;
	}

	//
	public void playGame() {
		do {
			move();

		} while (foundMove == true);

		System.out.println("Game Board");
		chess_Board.showGameBoard();

		System.out.printf("Number of moves: %d\n", Knight_Moves.getMoveCounter());

		System.out.println();
	}

	public void move() {
		int numPossibleMoves, bestMove;
		int[] possibleMoves;

		foundMove = false;
		//Đánh dấu trạng thái đã truy cập và di chuyển tại vị trí hiện tại
		chess_Board.markBoardSquare(Knight_Moves.getCurrentRow(),
                                           Knight_Moves.getCurrentCol(),
                                           Knight_Moves.getMoveCounter());

		//Giá trị tiếp cận thấp hơn của các ô vuông xung quanh.
		chess_Board.lowerAccessibility();

		//Tìm số di chuyển có thể từ vị trí hiện tại.
		numPossibleMoves = Knight_Moves.find_Num_Moves(chess_Board);

		//Nếu số lượng di chuyển có thể >0, có ít nhất một di chuyển có thể.
		if (numPossibleMoves > 0) {
			foundMove = true;

			//Tìm các chuyển động có thể từ vị trí hiện tại và trở lại thành một mảng
			possibleMoves = Knight_Moves.find_Moves(chess_Board,
					numPossibleMoves);

			//Sử dụng mảng di chuyển có thể, tìm động thái tốt nhất dựa trên khả năng tiếp cận heuristic
			bestMove = Knight_Moves.findBestMove(chess_Board, possibleMoves);

			//Di chuyển knight tốt nhất
			Knight_Moves.move(bestMove);
		}
	}
}
