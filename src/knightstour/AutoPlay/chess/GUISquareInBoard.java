package knightstour.AutoPlay.chess;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.X;
import javax.imageio.ImageIO;
import javax.swing.*;
import knightstour.AutoPlay.ChessBoard;

//JPanel đại diện cho một hình vuông trên một bảng cờ vua. 
//Panel chứa một nhãn có văn bản có thể được thay đổi theo chương trình.

public class GUISquareInBoard extends JPanel {
        private BufferedImage image;
	private JLabel KnightNumber;//JLabel trong Panel.
        private JPanel jpanel;
        private float row;
    private int boardSize;
        
	//khởi tạo hình vuông.
	public GUISquareInBoard() {
             
          
		//tạo đối tượng JLabel
		KnightNumber = new JLabel();

		//đặt màu nhãn trong ô.
		KnightNumber.setForeground(Color.GREEN);
                
                this.setLayout(new GridBagLayout());
                // Add KnightNumber vào Panel.
		add(KnightNumber);
                this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                
          
		
        }


      
	//đặt nhãn ô bằng tham số chuỗi.
	public void setText(String text) {
            
		KnightNumber.setText(text);
                KnightNumber.setFont(new Font("Times New Roman", Font.BOLD, 25));

	}

	//Đặt nhãn của ô không hợp lệ, nhãn này sẽ xóa.
	public void resetText() {
		KnightNumber.setText(null);
	}


}
