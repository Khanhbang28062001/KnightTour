/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knightstour.Player;

import knightstour.giaodien.GameMode;
import knightstour.giaodien.Play;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.Timer;
import java.util.TimerTask;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javafx.scene.control.Cell;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.MouseInputAdapter;
import knightstour.AutoPlay.SquareB;
import knightstour.AutoPlay.chess.GUISquareInBoard;

/**
 *
 * @author My Pc
 */
public class Board extends JPanel {

    private JFrame mainFrame;
    private static final int n = 8;
    private static final int m = 8;
    private Image imgx;

    private knightsmove[][] matrix = new knightsmove[n][n];
    private String currentPlayer = knightsmove.x_value;

    private static Timer timer = new Timer();
    private static JLabel jlbtime;
    private static Board demo;
    private static int sec = 0;
    private int point = 0;
    private int i ;
    private int j ;
   
//    public static void main(String[] args) {
//
////        gọi và chạy 
//        demo = new Board();
//        demo.prepareGUI();
////        demo.bottomPanel();
//    }

  
    public Board() {


        this.initMatrix();
        
        // Bỏ icon quân mã vào bàn cờ
        try {
            imgx = ImageIO.read(getClass().getResource("knight.png"));
        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        addMouseListener(new MouseAdapter() {
            private int y1;
            private int x1;
//lấy tọa dộ click chuột
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();
//                int x1 = 
//               System.out.println(" x :"+ x + " y : " + y);
                System.out.println(" x1 ngoai :" + x + " y1 ngoai : " + y);
                
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
//                       
                        knightsmove cell = matrix[i][j];
                        
                        x1 = e.getX() - 200;
                        y1 = e.getY() - 100;

                        int cxstart = cell.getX();
                        int cystart = cell.getY();

                        int cxend = cxstart + cell.getW();
                        int cyend = cystart + cell.getH();
                        
                        int movex1Start = cxstart - 200;
                        int movey1Start = cystart - 100;
                        int movex1End = cxend - 200;
                        int movey1End = cyend - 100;
                        
                        if (x >= cxstart && x <= cxend && y >= cystart && y <= cyend  ) {

                            System.out.println("click vao i: " + x1 + " j:" + y1);
                              
                            // dat dieu kien dat quan ma
                            cell.setValue(currentPlayer);
                            currentPlayer = currentPlayer.equals(knightsmove.x_value)
                                    ? knightsmove.x_value : knightsmove.x_value;
                       
//                            System.out.println(" movex1 :" + movex1 + " movey1 : " + movey1);                             
                           
//                                System.out.println(" x1 if :" + movex1 + " y1 if : " + movey1);                                        
                            repaint();
                            point++;
                            System.out.println("diem :" + point );
                            System.out.println(" movex1Start :" + movex1Start + " movey1Start : " + movey1Start);
                            System.out.println(" movex1End :" + movex1End + " movey1End : " + movey1End);
                            System.out.println(" x :" + x + " y : " + y+ "\n\n");
                            
                            if (x >= movex1Start && x <= movex1End && y >= movey1Start && y <= movey1End ) {
                                System.out.println(" x1 trong :" + movex1Start + " y1 trong : " + movey1Start+ "\n"+ "\n");
                                repaint();
                            }
                             

                        }
                    
                    }
                }

            }
//            }
        });

    }

    private void initMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                knightsmove cell = new knightsmove();
                matrix[i][j] = cell;
            }
        }
    }

    public void paint(Graphics g) {
        int w = getWidth() / n;
        int h = getHeight() / m;
        Graphics2D graphic2d = (Graphics2D) g;

// Đặt mau  ô trong bàn cờ
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = i * h;
                int y = j * w;
                knightsmove cell = matrix[i][j];
                cell.setX(x);
                cell.setY(y);
                cell.setW(w);
                cell.setH(h);

                Color color = k % 2 == 0 ? new Color(105, 51, 0) : Color.WHITE;
                graphic2d.setColor(color);
                graphic2d.fillRect(x, y, w, h);
//              Image img = k%2 == 0? imgx : imgx;

//                      graphic2d.drawImage(img,x,y,w,h, this)
                if (cell.getValue().equals(knightsmove.x_value)) {
//                  System.out.println("click vao i: " + i + " j:"+ j );
                    Image img = imgx;
                    graphic2d.drawImage(img, x, y, w, h, this);

                }

                k++;
            }
            k++;
        }

    }
    public void bottomPanel(){
         Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
         Board board = new Board();
         board.setPreferredSize(new Dimension(800, 800));
         JPanel jpanel  = new JPanel();
         BoxLayout boxLayout = new BoxLayout(jpanel, BoxLayout.Y_AXIS);
        jpanel.setLayout(boxLayout);
        jpanel.setSize(dimension);
        //kế thừa từ các layout
        FlowLayout flo = new FlowLayout(FlowLayout.CENTER, 0, 0);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(flo);
        bottomPanel.setBackground(new Color(225, 204, 204));   

        jlbtime = new JLabel("       0:0"); // them label thoi gian
        jlbtime.setText("   0:0"); // dinh dang thoi gian
       
        //Set thoi gian
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sec++;
                String value = sec / 60 + ":" + sec % 60;
                jlbtime.setText(value);
               

            }
        }, 1000, 1000);

//       
        JButton jbthoat = new JButton("Thoat");
        jbthoat.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //quay lại trang trước
                Play b = new Play();
                b.setVisible(true);

                mainFrame.setVisible(false);
            }

        });

//         GameMode m = new GameMode();
//         jbthoat.add(m).setVisible(true);
        jpanel.add(board);
        jpanel.add(bottomPanel);
        bottomPanel.add(jlbtime);
        bottomPanel.add(jbthoat);
        
        mainFrame.add(jpanel);
    }
    public void prepareGUI() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//          //thiết lập kích thước bàn cờ
         Board board = new Board();
        board.setPreferredSize(new Dimension(800, 800));
//        //tạo một jpanel
        JPanel jpanel  = new JPanel();
//        //tạo thanh công cụ bên phải
////        
        BoxLayout boxLayout = new BoxLayout(jpanel, BoxLayout.Y_AXIS);
        jpanel.setLayout(boxLayout);
        jpanel.setSize(800, 100);
//        //kế thừa từ các layout
        FlowLayout flo = new FlowLayout(FlowLayout.CENTER, 0, 0);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(flo);
        bottomPanel.setBackground(new Color(225, 204, 204));   
//
        jlbtime = new JLabel("       0:0"); // them label thoi gian
        jlbtime.setText("   0:0"); // dinh dang thoi gian
       
        //Set thoi gian
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sec++;
                String value = sec / 60 + ":" + sec % 60;
                jlbtime.setText(value);
               

            }
        }, 1000, 1000);

//       
        JButton jbthoat = new JButton("Thoat");
        jbthoat.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //quay lại trang trước
                Play b = new Play();
                b.setVisible(true);

                mainFrame.setVisible(false);
            }

        });

//         GameMode m = new GameMode();
//         jbthoat.add(m).setVisible(true);
        jpanel.add(board);
        jpanel.add(bottomPanel);
        bottomPanel.add(jlbtime);
//        bottomPanel.add(jbStart);
        bottomPanel.add(jbthoat);

        mainFrame = new JFrame("KnightStour"); //thiết lập bàn cờ, đặt tên jframe

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// thiết lập bàn cờ
        mainFrame.setResizable(true);
        mainFrame.add(jpanel);
        //Đặt jframe giữa màn hình
        int x = (int) dimension.getWidth() / 4 - (mainFrame.getWidth() / 8);
        int y = (int) dimension.getHeight() / 8 - (mainFrame.getHeight() / 4);
        mainFrame.setLocation(x, y);
        mainFrame.pack();
        //show ra frame
        mainFrame.setVisible(true);
 

    }
}
