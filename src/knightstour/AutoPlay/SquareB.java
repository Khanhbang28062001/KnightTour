package knightstour.AutoPlay;

//Biểu thị một hình vuông trên bảng cờ vua

public class SquareB {
	private boolean isVisited; //true nếu ô đã được truy cập, nếu không thì fale.
	private int NumBer_move_tour; //số lượng di chuyển đã được thực hiện.
	private int number_tour; //Số lượng di chuyển có thể từ ô.

        
	public SquareB() {
		isVisited = false;
		NumBer_move_tour = 0;
	}

	//Nhận giá trị boolean và đặt thuộc tính truy cập của ô.
	public void setVisitedB(boolean visited) {
		isVisited = visited;
	}

	//Trả về giá trị của thuộc tính đã truy cập.
	public boolean isVisitedB() {
		return isVisited;
	}

	//số di chuyển hiện tại của người chơi và đánh dấu ô.
	public void setMoveNumberB(int moveNumber) {
		this.NumBer_move_tour = moveNumber;
	}

	//Trả lại giá trị của số di chuyển của ô.
	public int getMoveNumberB() {
		return NumBer_move_tour;
	}

	//đặt giá trị khả năng tiếp cận của ô.
	public void setNumberSTour(int accessibility) {
		this.number_tour = accessibility;
	}

	//Trả lại giá trị khả năng truy cập của ô.
	public int getNumberSTour() {
		return number_tour;
	}

	//Giảm giá trị khả năng truy cập của ô.
	public void decrNumberSTour() {
		number_tour--;
	}
}
