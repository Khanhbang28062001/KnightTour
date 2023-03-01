package knightstour.AutoPlay.chess;

import knightstour.giaodien.GameMode;
import com.sun.prism.Graphics;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.paint.Color.color;
import javax.swing.*;
import javax.imageio.ImageIO;
import knightstour.Player.Board;
import knightstour.Player.knightsmove;
// Application cho Knight's tour.

public class KnightTours_Automatic extends JFrame {

    private Tours QuanMa; //đối tượng đi tuần.
    private int Size_Board; //kích thước của bàn cờ.
    private GUISquareInBoard[][] Gui_Square; //mảng của JPanels đại diện cho ô.
    private boolean TourKnightRunning = false; //true nếu tour đang chạy, không thì fale
    private boolean TourKnightFinished = false; //true nếu tour đã hoàn thành, không thì fale.
    private boolean New_Tour = true; //true nếu người dùng muốn tham số tour mới, 

    private BufferedImage image;
    private Image imgx;// lấy ảnh 
    private JLabel knight;
    private Timer timer; //số lần di chuyển của knight.
    private final int MaxBoard = 9;//kích thước lớn nhất của bàn cờ.
    private final int MinBoard = 2;//kích thước nhỏ nhất của bàn cờ.
    private JPanel panel;
    //cài đặt hoạt động đóng cửa mặc định cho cửa sổ và khởi chạy một tour mới.
    public KnightTours_Automatic() {

        setTitle("Mã đi tuần");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        newGameKnightStour();
        taoTime();
        

    }
    
    //Thiết lập các thông số cho tour mới của knight.
    private void newGameKnightStour() {
        //Nếu kích thước của bàn cờ >0, thì bàn cờ phải được xóa trước khi bắt đầu lại.
        if (Size_Board > 0) {
            //Loại bỏ các ô hiện tại khỏi bàn cờ.
            removeSquaresInBoard();

            //khởi động lại cửa sổ.
            repaint();
        }

        //Nếu người dùng lựa chọn để nhập các thông số trò chơi mới 
        //nhắc người sử dụng cho các kích thước bàn cờ mới 
        if (New_Tour == true) {
            //Nhắc nhở người sử dụng kích thước bàn cờ.
            Size_Board = askBoardSize();
        }

        //Nếu người dùng muốn tham số trò chơi mới,
        //hãy thay đổi kích thước cửa sổ 
        //nếu không để cửa sổ có cùng kích thước
        if (New_Tour == true) {
            // Set size of window
            setSize(Size_Board * 100, Size_Board * 100);
        }

        //Đặt bố trí lưới, các hàng và cột bằng kích thước của bảng, 
        setLayout(new GridLayout(Size_Board, Size_Board, Size_Board / Size_Board,
                Size_Board / Size_Board));

        //tạo đối tượng mới.
        QuanMa = new Tours(Size_Board);

        //tạo các ô
        DrawSquaresInBoard();
    }

    //tạo các ô lên màn hình.
    public void DrawSquaresInBoard() {
        //Tạo mảng mới với kích thước của bảng
        Gui_Square = new GUISquareInBoard[Size_Board][Size_Board];
        

        //Lồng nhau lặp đi lặp lại qua tất cả các ô vuông
        for (int Hang = 0; Hang < Gui_Square.length; Hang++) {
            for (int Cot = 0; Cot < Gui_Square.length; Cot++) {
                //tạo mới đối tượng GUISquareInBoard
                Gui_Square[Hang][Cot] = new GUISquareInBoard();

                //Thêm sự kiện Mousse vào trong mảng
                Gui_Square[Hang][Cot].addMouseListener(new SquareBoardClickHandler());

                //màu các ô vuông.
                setColorInSquare(Hang, Cot);

                //thêm ô vuông vào Frame
                add(Gui_Square[Hang][Cot]);
            }
        }
        //Nên được viện dẫn khi các thành phần con của vùng chứa này được sửa đổi 
        //(thêm vào hoặc xoá khỏi vùng chứa, hoặc thông tin về bố cục đã thay đổi) 
        //sau khi vùng chứa được hiển thị.
        validate();
    }

    //Xác định màu của ô dựa trên vị trí của nó trên bàn cờ.
    private void setColorInSquare(int Hang, int Cot) {
//                Gui_Square[Hang][Cot].removeAll();
        if ((Hang + Cot) % 2 == 0) {
            Gui_Square[Hang][Cot].setBackground(new Color(105, 51, 0));
        } else {
            Gui_Square[Hang][Cot].setBackground(Color.WHITE);
        }

    }

//    private void removeKnight(int Hang, int Cot) {
//
//        Gui_Square[Hang][Cot].removeAll();
//
//    }
    //Loại bỏ các ô vuông khỏi container.
    private void removeSquaresInBoard() {
        for (int row = 0; row < Gui_Square.length; row++) {
            for (int col = 0; col < Gui_Square.length; col++) {
                //gọi hàm xóa các ô khỏi Frame
                remove(Gui_Square[row][col]);
            }
        }
    }

    private int askBoardSize() {
        String Board_size; //Chuỗi giá trị để giữ đầu vào của người dùng
        int boardSize = 0; //Giá trị số nguyên cần trả
        boolean isUserInputInvalid = true; // True cho biết đầu vào không hợp lệ

        //Thực hiện các thao tác sau khi đầu vào của người dùng không hợp lệ
        do {
            //Nhắc người dùng nhập và lưu trữ trong biến String
            Board_size = JOptionPane.showInputDialog("Nhập một số nguyên để đặt số hàng và số cột trên bảng "
                    + "\n" + "( Kích thước bàn cờ phải lớn hơn 2 và nhỏ hơn 9 ):");

            //Nếu đầu vào của người dùng là một giá trị null,
            //người dùng nhấp vào hủy bỏ hoặc đóng cửa sổ thoát khỏi chương trình.
            if (Board_size == null) {
                System.exit(0);

            } else {
                try {
                    //Kiểm tra nếu chuỗi trống và gán giá trị boolean cho chuỗi không hợp lệ
                    isUserInputInvalid = Board_size.isEmpty();

                    boardSize = Integer.parseInt(Board_size);

                    //Nếu không ngoại lệ, chương trình sẽ tiếp tục ở đây. 
                    //Nếu kích thước bảng <= 0, chuỗi vẫn không hợp lệ do đó hiển thị lỗi
                    if (boardSize <= MinBoard || boardSize >= MaxBoard) {
                        String Message;

                        isUserInputInvalid = true;

                        Message = boardSize <= MinBoard ? "Kích thước bàn cờ quá nhỏ!!!"
                                : String.format("Kích thước của bàn cờ không được vượt qua %d!!!",
                                        MaxBoard);

                        JOptionPane.showMessageDialog(this, Message,
                                "Giá trị không hợp lệ", JOptionPane.WARNING_MESSAGE);
                    }
                } //xử lý NumberFormatException từ parseInt //xử lý NumberFormatException từ parseInt //xử lý NumberFormatException từ parseInt //xử lý NumberFormatException từ parseInt //xử lý NumberFormatException từ parseInt //xử lý NumberFormatException từ parseInt //xử lý NumberFormatException từ parseInt //xử lý NumberFormatException từ parseInt
                catch (NumberFormatException numberFormatException) {
                    //hiện thị thông báo lỗi
                    JOptionPane.showMessageDialog(this,
                            "Bạn phải nhập một giá trị nguyên hợp lệ!!!",
                            "Giá trị không hợp lệ!!!", JOptionPane.WARNING_MESSAGE);
                }
            }

        } while (isUserInputInvalid == true);

        //đầu vào là hợp lệ, thiết lập kích thước bàn cờ.
        boardSize = Integer.parseInt(Board_size);

        return boardSize;
    }

    //Tạo hành động thực hiện di chuyển của một knight ở một khoảng thời gian
    private void taoTime() {
        int delay = 50; //thời gian trễ.

        //thiết lập tour đã kết thúc thành false.
        TourKnightFinished = false;

        //Tạo biến danh sách tác vụ vô danh và 
        //ghi đè lên phương thức actionPerformed để gọi setMoveKnight
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
//                knight = new JLabel(new ImageIcon(imgx)); 
                //đặt màu cho ô.
                setColorInSquare(QuanMa.getKnights().getCurrentRowS(),
                        QuanMa.getKnights().getCurrentColB());

                setKnightImage();

                setMoveKnight();

            }
        };
        timer = new Timer(delay, taskPerformer);
    }

//}
    //Thay đổi màu sắc của ô hiện tại
    private void setKnightImage() {
//             Graphics2D graphic2d = (Graphics2D) g;
        int currentHang = QuanMa.getKnights().getCurrentRowS();
        int currentCot = QuanMa.getKnights().getCurrentColB();
        GUISquareInBoard currentSquare = Gui_Square[currentHang][currentCot];

//              
        try {
            imgx = ImageIO.read(getClass().getResource("knight.png")).getScaledInstance(70, 70, Image.SCALE_DEFAULT);
            JLabel knight = new JLabel(new ImageIcon(imgx));
            currentSquare.add(knight);

        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
//        catch (InterruptedException ex) {
//            Logger.getLogger(AppWindow.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    //bắt đầu bộ đếm thời gian.
    private void startTime() {
        timer.start();
    }

    //Ngừng bộ đếm thời gian đang chạy
    private void stopTime() {
        timer.stop();
    }
//    private void before_removeknight(){
//       knightsmove[][] matrix= new knightsmove[currentHang][currentCot];
//        
//    }
    //Di chuyển knight và đặt text trên nhãn của ô hiện tại

    private void setMoveKnight() {
        GUISquareInBoard currentSquareInBoard; //lưu vị trí hiện tại của knight
        int currenttHang; // Vị trí hàng hiện tại của knight
        int currenttCot; // Vị trí cột hiện tại của knight

        //nhận giá trị hàng và cột hiện tại của knight.
        currenttHang = QuanMa.getKnights().getCurrentRowS();
        currenttCot = QuanMa.getKnights().getCurrentColB();

        //đặt ô hiện tại.
        currentSquareInBoard = Gui_Square[currenttHang][currenttCot];
        try {
            Thread.sleep(500);
//            Thread.currentThread().join();

        } catch (InterruptedException ex) {
            Logger.getLogger(KnightTours_Automatic.class.getName()).log(Level.SEVERE, null, ex);
        }
//        currentSquareInBoard.remove(1);
//        currentSquareInBoard.remove(1);

        //đặt nhãn trên ô.
        currentSquareInBoard.setText(Integer.toString(QuanMa.getKnights()
                .getMoveCounterB()));

        //di chuyển knights
        QuanMa.moveKnightTour();

        //Nếu hiệp sĩ không có động tác nào khác, dừng đồng hồ đếm ngược
        //thiết lập tour đang chạy thành fale và tour kết thúc thành true.
        if (QuanMa.Move() == false) {
            timer.stop();
            TourKnightRunning = false;
            TourKnightFinished = true;
        }
    }

    //Yêu cầu người dùng nếu họ muốn có các thông số mới cho tour tiếp theo.
    public void askNewBoard() {
        int newBoardAnswer; //Giá trị số nguyên của câu trả lời hộp thoại 
        //(Có = 0, Không = 1, Hủy = 3)

        //Lưu phản hồi từ hộp thoại
        newBoardAnswer = JOptionPane.showConfirmDialog(this,
                "Bạn có muốn sử dụng cùng một bàn cờ?", "Tạm dừng",
                JOptionPane.YES_NO_CANCEL_OPTION);

        //Nếu có, người dùng không nhận được thông số tour mới
        if (newBoardAnswer == 0) {
            New_Tour = false;
        } //Nếu không, người dùng sẽ có thông số tour mới
        else if (newBoardAnswer == 1) {
            New_Tour = true;
        } //nếu hủy, thoát chương trình
        else {
            System.exit(0);
        }
    }

    //Yêu cầu người dùng nếu họ muốn có một tour mới 
    //nếu người dùng nhấp vào một hình vuông trong khi tour đang chạy.
    public void setKnightTourPaused() {
        int newGameKinghtTourAnswer;

        //Ngừng thời gian, tạm dừng tour
        stopTime();

        //thiết lập TourKnightRunning là fale.
        TourKnightRunning = false;

        //phản hồi từ hộp thoại
        newGameKinghtTourAnswer = JOptionPane.showConfirmDialog(this, "Bắt đầu một tour mới?", "Tạm dừng",
                JOptionPane.YES_NO_CANCEL_OPTION);

        // nếu có.
        if (newGameKinghtTourAnswer == 0) {
            askNewBoard();

            //đặt lại knightstour, xóa trạng thái hiện tại.
            QuanMa.resetKnightTour();

            //tạo tour mới.
            newGameKnightStour();
        } else if (newGameKinghtTourAnswer == 1) {
            //Nếu người dùng không muốn một trò chơi mới đặt tour lại
            //và khởi động lại bộ đếm thời gian.

            TourKnightRunning = true;
            startTime();
        } //nếu hủy, thoát chương trình
        else {
            System.exit(0);
        }
    }

    public void showKnightHeuristics() {
        for (int Hang_Number = 0; Hang_Number < Size_Board; Hang_Number++) {
            for (int Cot_Number = 0; Cot_Number < Size_Board; Cot_Number++) {
                Gui_Square[Hang_Number][Cot_Number].setText(Integer
                        .toString(QuanMa
                                .getSquareInBoard(Hang_Number, Cot_Number)
                                .getNumberSTour()));
            }
        }
    }

    //sự kiện kích chuột.
    class SquareBoardClickHandler extends MouseAdapter {

        //xử lý sự kiện kích chuột.
        @Override
        public void mouseClicked(MouseEvent meEvent) {
            //nếu TourKnightRunning = fale, chạy khi chuột được kích.
            if (TourKnightRunning == false) {
                //Nếu rour đã kết thúc, 
                //nghĩa là người dùng đã không làm gián đoạn tour
                //với các nhấp chuột tiếp theo và 
                //chuyến tham quan được phép hoàn tất
                if (TourKnightFinished == true) {
                    //đặt tourfinished trở lại fale
                    TourKnightFinished = false;

                    askNewBoard();
                    newGameKnightStour();
                }

                for (int Hang_Number = 0; Hang_Number < Size_Board; Hang_Number++) {
                    for (int Cot_Number = 0; Cot_Number < Size_Board; Cot_Number++) {
                        //So sánh ô với nguồn sự kiện, để tìm ra ô nào đã được nhấp vào
                        if (meEvent.getSource() == Gui_Square[Hang_Number][Cot_Number]) {

                            //đặt vị trí bắt đầu
                            QuanMa.setStartPositionKnight(Hang_Number,
                                    Cot_Number);
                            TourKnightRunning = true;
                            startTime();
                            break;
                        }
                    }
                }
            } else {
                setKnightTourPaused();
            }
        }
    }
}
