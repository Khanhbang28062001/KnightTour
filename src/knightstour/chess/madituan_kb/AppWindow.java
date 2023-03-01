package knightstour.chess.madituan_kb;

import Madituan_giaodien.GameMode;
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
import knightstour_nc.Board;
import knightstour_nc.knightsmove;
// Application cho Knight's tour.

public class AppWindow extends JFrame {

    private Tour Knights_Tour; //đối tượng đi tuần.
    private int Board_Size; //kích thước của bàn cờ.
    private GUISquare[][] Gui_Square; //mảng của JPanels đại diện cho ô.
    private boolean TourRunning = false; //true nếu tour đang chạy, không thì fale
    private boolean TourFinished = false; //true nếu tour đã hoàn thành, không thì fale.
    private boolean New_Tour = true; //true nếu người dùng muốn tham số tour mới, 

    private BufferedImage image;
    private Image imgx;// lấy ảnh 
    private JLabel knight;
    private Timer timer; //số lần di chuyển của knight.
    private final int MaxBoard = 9;//kích thước lớn nhất của bàn cờ.
    private final int MinBoard = 2;//kích thước nhỏ nhất của bàn cờ.
    private JPanel jpanel;
    //cài đặt hoạt động đóng cửa mặc định cho cửa sổ và khởi chạy một tour mới.
    public AppWindow() {

        setTitle("Mã đi tuần");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        newGame();
        createTimer();
        

    }
    
    //Thiết lập các thông số cho tour mới của knight.
    private void newGame() {
        //Nếu kích thước của bàn cờ >0, thì bàn cờ phải được xóa trước khi bắt đầu lại.
        if (Board_Size > 0) {
            //Loại bỏ các ô hiện tại khỏi bàn cờ.
            removeSquares();

            //khởi động lại cửa sổ.
            repaint();
        }

        //Nếu người dùng lựa chọn để nhập các thông số trò chơi mới 
        //nhắc người sử dụng cho các kích thước bàn cờ mới 
        if (New_Tour == true) {
            //Nhắc nhở người sử dụng kích thước bàn cờ.
            Board_Size = promptBoardSize();
        }

        //Nếu người dùng muốn tham số trò chơi mới,
        //hãy thay đổi kích thước cửa sổ 
        //nếu không để cửa sổ có cùng kích thước
        if (New_Tour == true) {
            // Set size of window
            setSize(Board_Size * 100, Board_Size * 100);
        }

        //Đặt bố trí lưới, các hàng và cột bằng kích thước của bảng, 
        setLayout(new GridLayout(Board_Size, Board_Size, Board_Size / Board_Size,
                Board_Size / Board_Size));

        //tạo đối tượng mới.
        Knights_Tour = new Tour(Board_Size);

        //tạo các ô
        Draw_Squares();
    }

    //tạo các ô lên màn hình.
    public void Draw_Squares() {
        //Tạo mảng mới với kích thước của bảng
        Gui_Square = new GUISquare[Board_Size][Board_Size];
        

        //Lồng nhau lặp đi lặp lại qua tất cả các ô vuông
        for (int row = 0; row < Gui_Square.length; row++) {
            for (int col = 0; col < Gui_Square.length; col++) {
                //tạo mới đối tượng GUISquare
                Gui_Square[row][col] = new GUISquare();

                //Thêm sự kiện Mousse vào trong mảng
                Gui_Square[row][col].addMouseListener(new SquareClickHandler());

                //màu các ô vuông.
                setSquareColor(row, col);

                //thêm ô vuông vào Frame
                add(Gui_Square[row][col]);
            }
        }
        //Nên được viện dẫn khi các thành phần con của vùng chứa này được sửa đổi 
        //(thêm vào hoặc xoá khỏi vùng chứa, hoặc thông tin về bố cục đã thay đổi) 
        //sau khi vùng chứa được hiển thị.
        validate();
    }

    //Xác định màu của ô dựa trên vị trí của nó trên bàn cờ.
    private void setSquareColor(int row, int col) {
//                Gui_Square[row][col].removeAll();
        if ((row + col) % 2 == 0) {
            Gui_Square[row][col].setBackground(new Color(105, 51, 0));
        } else {
            Gui_Square[row][col].setBackground(Color.WHITE);
        }

    }

//    private void removeKnight(int row, int col) {
//
//        Gui_Square[row][col].removeAll();
//
//    }
    //Loại bỏ các ô vuông khỏi container.
    private void removeSquares() {
        for (int row = 0; row < Gui_Square.length; row++) {
            for (int col = 0; col < Gui_Square.length; col++) {
                //gọi hàm xóa các ô khỏi Frame
                remove(Gui_Square[row][col]);
            }
        }
    }

    private int promptBoardSize() {
        String userBoardSize; //Chuỗi giá trị để giữ đầu vào của người dùng
        int boardSize = 0; //Giá trị số nguyên cần trả
        boolean isUserInputInvalid = true; // True cho biết đầu vào không hợp lệ

        //Thực hiện các thao tác sau khi đầu vào của người dùng không hợp lệ
        do {
            //Nhắc người dùng nhập và lưu trữ trong biến String
            userBoardSize = JOptionPane.showInputDialog("Nhập một số nguyên để đặt số hàng và số cột trên bảng "
                    + "\n" + "( Kích thước bàn cờ phải lớn hơn 2 và nhỏ hơn 9 ):");

            //Nếu đầu vào của người dùng là một giá trị null,
            //người dùng nhấp vào hủy bỏ hoặc đóng cửa sổ thoát khỏi chương trình.
            if (userBoardSize == null) {
                System.exit(0);

            } else {
                try {
                    //Kiểm tra nếu chuỗi trống và gán giá trị boolean cho chuỗi không hợp lệ
                    isUserInputInvalid = userBoardSize.isEmpty();

                    boardSize = Integer.parseInt(userBoardSize);

                    //Nếu không ngoại lệ, chương trình sẽ tiếp tục ở đây. 
                    //Nếu kích thước bảng <= 0, chuỗi vẫn không hợp lệ do đó hiển thị lỗi
                    if (boardSize <= MinBoard || boardSize >= MaxBoard) {
                        String message;

                        isUserInputInvalid = true;

                        message = boardSize <= MinBoard ? "Kích thước bàn cờ quá nhỏ!!!"
                                : String.format("Kích thước của bàn cờ không được vượt qua %d!!!",
                                        MaxBoard);

                        JOptionPane.showMessageDialog(this, message,
                                "Giá trị không hợp lệ", JOptionPane.WARNING_MESSAGE);
                    }
                } //xử lý NumberFormatException từ parseInt //xử lý NumberFormatException từ parseInt //xử lý NumberFormatException từ parseInt //xử lý NumberFormatException từ parseInt
                catch (NumberFormatException numberFormatException) {
                    //hiện thị thông báo lỗi
                    JOptionPane.showMessageDialog(this,
                            "Bạn phải nhập một giá trị nguyên hợp lệ!!!",
                            "Giá trị không hợp lệ!!!", JOptionPane.WARNING_MESSAGE);
                }
            }

        } while (isUserInputInvalid == true);

        //đầu vào là hợp lệ, thiết lập kích thước bàn cờ.
        boardSize = Integer.parseInt(userBoardSize);

        return boardSize;
    }

    //Tạo hành động thực hiện di chuyển của một knight ở một khoảng thời gian
    private void createTimer() {
        int delay = 50; //thời gian trễ.

        //thiết lập tour đã kết thúc thành false.
        TourFinished = false;

        //Tạo biến danh sách tác vụ vô danh và 
        //ghi đè lên phương thức actionPerformed để gọi moveKnight
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
//                knight = new JLabel(new ImageIcon(imgx)); 
                //đặt màu cho ô.
                setSquareColor(Knights_Tour.getKnight().getCurrentRow(),
                        Knights_Tour.getKnight().getCurrentCol());

                flashColor();

                moveKnight();

            }
        };
        timer = new Timer(delay, taskPerformer);
    }

//}
    //Thay đổi màu sắc của ô hiện tại
    private void flashColor() {
//             Graphics2D graphic2d = (Graphics2D) g;
        int currentRow = Knights_Tour.getKnight().getCurrentRow();
        int currentCol = Knights_Tour.getKnight().getCurrentCol();
        GUISquare currentSquare = Gui_Square[currentRow][currentCol];

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
    private void startTimer() {
        timer.start();
    }

    //Ngừng bộ đếm thời gian đang chạy
    private void stopTimer() {
        timer.stop();
    }
//    private void before_removeknight(){
//       knightsmove[][] matrix= new knightsmove[currentRow][currentCol];
//        
//    }
    //Di chuyển knight và đặt text trên nhãn của ô hiện tại

    private void moveKnight() {
        GUISquare currentSquare; //lưu vị trí hiện tại của knight
        int currentRow; // Vị trí hàng hiện tại của knight
        int currentCol; // Vị trí cột hiện tại của knight

        //nhận giá trị hàng và cột hiện tại của knight.
        currentRow = Knights_Tour.getKnight().getCurrentRow();
        currentCol = Knights_Tour.getKnight().getCurrentCol();

        //đặt ô hiện tại.
        currentSquare = Gui_Square[currentRow][currentCol];
        try {
            Thread.sleep(500);
//            Thread.currentThread().join();

        } catch (InterruptedException ex) {
            Logger.getLogger(AppWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
//        currentSquare.remove(1);

        //đặt nhãn trên ô.
        currentSquare.setText(Integer.toString(Knights_Tour.getKnight()
                .getMoveCounter()));

        //di chuyển knights
        Knights_Tour.move();

        //Nếu hiệp sĩ không có động tác nào khác, dừng đồng hồ đếm ngược
        //thiết lập tour đang chạy thành fale và tour kết thúc thành true.
        if (Knights_Tour.hasMove() == false) {
            timer.stop();
            TourRunning = false;
            TourFinished = true;
        }
    }

    //Yêu cầu người dùng nếu họ muốn có các thông số mới cho tour tiếp theo.
    public void askNewParam() {
        int newParamAnswer; //Giá trị số nguyên của câu trả lời hộp thoại 
        //(Có = 0, Không = 1, Hủy = 3)

        //Lưu phản hồi từ hộp thoại
        newParamAnswer = JOptionPane.showConfirmDialog(this,
                "Bạn có muốn sử dụng cùng một bàn cờ?", "Tạm dừng",
                JOptionPane.YES_NO_CANCEL_OPTION);

        //Nếu có, người dùng không nhận được thông số tour mới
        if (newParamAnswer == 0) {
            New_Tour = false;
        } //Nếu không, người dùng sẽ có thông số tour mới
        else if (newParamAnswer == 1) {
            New_Tour = true;
        } //nếu hủy, thoát chương trình
        else {
            System.exit(0);
        }
    }

    //Yêu cầu người dùng nếu họ muốn có một tour mới 
    //nếu người dùng nhấp vào một hình vuông trong khi tour đang chạy.
    public void tourPaused() {
        int newGameAnswer;

        //Ngừng thời gian, tạm dừng tour
        stopTimer();

        //thiết lập TourRunning là fale.
        TourRunning = false;

        //phản hồi từ hộp thoại
        newGameAnswer = JOptionPane.showConfirmDialog(this, "Bắt đầu một tour mới?", "Tạm dừng",
                JOptionPane.YES_NO_CANCEL_OPTION);

        // nếu có.
        if (newGameAnswer == 0) {
            askNewParam();

            //đặt lại knightstour, xóa trạng thái hiện tại.
            Knights_Tour.resetTour();

            //tạo tour mới.
            newGame();
        } else if (newGameAnswer == 1) {
            //Nếu người dùng không muốn một trò chơi mới đặt tour lại
            //và khởi động lại bộ đếm thời gian.

            TourRunning = true;
            startTimer();
        } //nếu hủy, thoát chương trình
        else {
            System.exit(0);
        }
    }

    public void showHeuristics() {
        for (int rowNumber = 0; rowNumber < Board_Size; rowNumber++) {
            for (int columnNumber = 0; columnNumber < Board_Size; columnNumber++) {
                Gui_Square[rowNumber][columnNumber].setText(Integer
                        .toString(Knights_Tour
                                .getSquare(rowNumber, columnNumber)
                                .getAccessibility()));
            }
        }
    }

    //sự kiện kích chuột.
    class SquareClickHandler extends MouseAdapter {

        //xử lý sự kiện kích chuột.
        @Override
        public void mouseClicked(MouseEvent meEvent) {
            //nếu TourRunning = fale, chạy khi chuột được kích.
            if (TourRunning == false) {
                //Nếu rour đã kết thúc, 
                //nghĩa là người dùng đã không làm gián đoạn tour
                //với các nhấp chuột tiếp theo và 
                //chuyến tham quan được phép hoàn tất
                if (TourFinished == true) {
                    //đặt tourfinished trở lại fale
                    TourFinished = false;

                    askNewParam();
                    newGame();
                }

                for (int rowNumber = 0; rowNumber < Board_Size; rowNumber++) {
                    for (int columnNumber = 0; columnNumber < Board_Size; columnNumber++) {
                        //So sánh ô với nguồn sự kiện, để tìm ra ô nào đã được nhấp vào
                        if (meEvent.getSource() == Gui_Square[rowNumber][columnNumber]) {

                            //đặt vị trí bắt đầu
                            Knights_Tour.setStartPosition(rowNumber,
                                    columnNumber);
                            TourRunning = true;
                            startTimer();
                            break;
                        }
                    }
                }
            } else {
                tourPaused();
            }
        }
    }
}
