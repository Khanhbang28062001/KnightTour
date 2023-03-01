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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
public class KnightTous_Player extends javax.swing.JFrame {

  
    public KnightTous_Player() {
        initComponents();
        Board2();
    }
    private void Board2(){
         jpanel_big = new javax.swing.JPanel();
//         knightstour_nc.Board board = new knightstour_nc.Board();
        jpanel_small = new Board();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
         getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpanel_big.setBackground(new java.awt.Color(51, 255, 204));
        jpanel_big.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        

        javax.swing.GroupLayout jpanel_smallLayout = new javax.swing.GroupLayout(jpanel_small);
        jpanel_small.setLayout(jpanel_smallLayout);
        jpanel_smallLayout.setHorizontalGroup(
            jpanel_smallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(10, 800, Short.MAX_VALUE)
        );
        jpanel_smallLayout.setVerticalGroup(
            jpanel_smallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpanel_bigLayout = new javax.swing.GroupLayout(jpanel_big);
        jpanel_big.setLayout(jpanel_bigLayout);
        jpanel_bigLayout.setHorizontalGroup(
            jpanel_bigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel_bigLayout.createSequentialGroup()
                .addContainerGap(461, Short.MAX_VALUE)
                .addComponent(jpanel_small, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jpanel_bigLayout.setVerticalGroup(
            jpanel_bigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_bigLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jpanel_small, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
        );
        
        getContentPane().add(jpanel_big, new org.netbeans.lib.awtextra.AbsoluteConstraints(36,38,815,800));

        pack();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanel_bot = new javax.swing.JPanel();
        jpanel_number = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jpanel_text = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jpanel_goc = new javax.swing.JPanel();

        jpanel_bot.setBackground(new java.awt.Color(225, 204, 204));
        jpanel_bot.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jpanel_botLayout = new javax.swing.GroupLayout(jpanel_bot);
        jpanel_bot.setLayout(jpanel_botLayout);
        jpanel_botLayout.setHorizontalGroup(
            jpanel_botLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 846, Short.MAX_VALUE)
        );
        jpanel_botLayout.setVerticalGroup(
            jpanel_botLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("KnightStour");
        setName("Board_New"); // NOI18N
        setPreferredSize(new java.awt.Dimension(870, 930));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpanel_number.setBackground(new java.awt.Color(225, 204, 204));
        jpanel_number.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpanel_number.setPreferredSize(new java.awt.Dimension(800, 30));
        jpanel_number.setLayout(new java.awt.GridLayout(1, 0));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("1");
        jpanel_number.add(jLabel17);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("2");
        jpanel_number.add(jLabel18);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("3");
        jpanel_number.add(jLabel19);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("4");
        jpanel_number.add(jLabel20);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("5");
        jpanel_number.add(jLabel21);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("6");
        jpanel_number.add(jLabel22);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("7");
        jpanel_number.add(jLabel23);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("8");
        jpanel_number.add(jLabel24);

        getContentPane().add(jpanel_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 800, 40));

        jpanel_text.setBackground(new java.awt.Color(225, 204, 204));
        jpanel_text.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpanel_text.setPreferredSize(new java.awt.Dimension(30, 800));
        jpanel_text.setLayout(new java.awt.GridLayout(8, 1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("A");
        jpanel_text.add(jLabel9);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("B");
        jpanel_text.add(jLabel10);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("C");
        jpanel_text.add(jLabel11);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("D");
        jpanel_text.add(jLabel12);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("E");
        jpanel_text.add(jLabel13);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("F");
        jpanel_text.add(jLabel14);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("G");
        jpanel_text.add(jLabel15);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("H");
        jpanel_text.add(jLabel16);

        getContentPane().add(jpanel_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 50, 800));

        jpanel_goc.setBackground(new java.awt.Color(225, 204, 204));
        jpanel_goc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jpanel_gocLayout = new javax.swing.GroupLayout(jpanel_goc);
        jpanel_goc.setLayout(jpanel_gocLayout);
        jpanel_gocLayout.setHorizontalGroup(
            jpanel_gocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );
        jpanel_gocLayout.setVerticalGroup(
            jpanel_gocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        getContentPane().add(jpanel_goc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 40));

        getAccessibleContext().setAccessibleDescription("");
        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new KnightTous_Player().setVisible(true);
//            }
//        });
//    }
//    
    private javax.swing.JPanel Board;
    private javax.swing.JPanel jpanel_small;
    private javax.swing.JPanel jpanel_big;
    private javax.swing.JPanel jpanel_bot_s;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jpanel_bot;
    private javax.swing.JPanel jpanel_goc;
    private javax.swing.JPanel jpanel_number;
    private javax.swing.JPanel jpanel_text;
    // End of variables declaration//GEN-END:variables
}
