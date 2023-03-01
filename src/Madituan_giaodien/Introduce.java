/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Madituan_giaodien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author My Pc
 */
public class Introduce extends javax.swing.JFrame {

    /**
     * Creates new form Introduce
     */
    public Introduce() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Playgame = new javax.swing.JButton();
        Quit = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mã đi tuần");
        setPreferredSize(new java.awt.Dimension(620, 900));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Playgame.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        Playgame.setForeground(new java.awt.Color(102, 51, 0));
        Playgame.setText("Chơi Game");
        Playgame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlaygameActionPerformed(evt);
            }
        });
        getContentPane().add(Playgame, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 600, 190, 60));

        Quit.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        Quit.setForeground(new java.awt.Color(102, 51, 0));
        Quit.setText("Giới Thiệu");
        Quit.setActionCommand("Giới Thiêu");
        Quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitActionPerformed(evt);
            }
        });
        getContentPane().add(Quit, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 600, 170, 60));

        Exit.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        Exit.setForeground(new java.awt.Color(102, 51, 0));
        Exit.setText("Thoát");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        getContentPane().add(Exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 600, 180, 60));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Bernard MT Condensed", 1, 85)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 236, 236));
        jLabel2.setText("     KNIGHT'S TOUR");
        jLabel2.setToolTipText("");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-100, 240, 810, 260));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/z3758228332941_2dc68cefbeff8d1a5a29e03ccca59803.jpg"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jLabel1.setMaximumSize(new java.awt.Dimension(620, 900));
        jLabel1.setMinimumSize(new java.awt.Dimension(620, 900));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -90, -1, -1));

        getAccessibleContext().setAccessibleName("Introduce");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PlaygameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlaygameActionPerformed
        // TODO add your handling code here:
        GameMode G = new GameMode();
        G.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_PlaygameActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:
        Exit.addActionListener(new ActionListener() {
    
    public void actionPerformed(ActionEvent event) {
        System.exit(0);
    }
});
       
    }//GEN-LAST:event_ExitActionPerformed

    private void QuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitActionPerformed
        // TODO add your handling code here:
        Guide G = new Guide();
        G.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_QuitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Introduce.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Introduce.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Introduce.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Introduce.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Introduce().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Exit;
    private javax.swing.JButton Playgame;
    private javax.swing.JButton Quit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
