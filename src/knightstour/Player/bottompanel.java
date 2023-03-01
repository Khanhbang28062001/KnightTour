/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knightstour.Player;

import knightstour.giaodien.Play;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;



/**
 *
 * @author My Pc
 */
public class bottompanel extends JPanel {
        private static Timer timer = new Timer();
        private static JLabel jlbtime;
        private static bottompanel demo;
        private static int sec = 0;
        private int point = 0;
        private JFrame main_Frame;
    private PopupMenu jPanel1;
        
   
        public bottompanel(){
         
//          //thiết lập kích thước bàn cờ
       
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
            JFrame mainFrame = null;
        jbthoat.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //quay lại trang trước
                Play b = new Play();
                b.setVisible(true);

//                main_Frame.setVisible(false);
            }

 
        });

//         GameMode m = new GameMode();
//         jbthoat.add(m).setVisible(true);
        jpanel.add(jPanel1);
        jpanel.add(bottomPanel);
        bottomPanel.add(jlbtime);
//        bottomPanel.add(jbStart);
        bottomPanel.add(jbthoat);

        mainFrame = new JFrame("KnightStour"); //thiết lập bàn cờ, đặt tên jframe

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// thiết lập bàn cờ
        mainFrame.setResizable(true);
        mainFrame.add(jpanel);
        //Đặt jframe giữa màn hình
//        int x = (int) dimension.getWidth() / 4 - (mainFrame.getWidth() / 8);
//        int y = (int) dimension.getHeight() / 8 - (mainFrame.getHeight() / 4);
//        mainFrame.setLocation(x, y);
        mainFrame.pack();
        //show ra frame
        mainFrame.setVisible(true);
       
    }
        public static void main(String[] args) {

//        gọi và chạy 
        demo = new bottompanel();;
       
        demo.setVisible(true);
    }

  

}