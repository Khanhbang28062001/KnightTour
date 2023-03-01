package knightstour.chess.madituan_kb;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.X;
import javax.imageio.ImageIO;
import javax.swing.*;
import knightstour.chess.ChessBoard;

//JPanel đại diện cho một hình vuông trên một bảng cờ vua. 
//Panel chứa một nhãn có văn bản có thể được thay đổi theo chương trình.

public class GUISquare extends JPanel {
        private BufferedImage image;
	private JLabel Label;//JLabel trong Panel.
        private JPanel jpanel;
        private float row;
    private int boardSize;
        
	//khởi tạo hình vuông.
	public GUISquare() {
             
          
		//tạo đối tượng JLabel
		Label = new JLabel();

		//đặt màu nhãn trong ô.
		Label.setForeground(Color.GREEN);
                
                this.setLayout(new GridBagLayout());
                // Add Label vào Panel.
		add(Label);
                this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                
          
		
        }


      
	//đặt nhãn ô bằng tham số chuỗi.
	public void setText(String text) {
            
		Label.setText(text);
                Label.setFont(new Font("Times New Roman", Font.BOLD, 25));

	}

	//Đặt nhãn của ô không hợp lệ, nhãn này sẽ xóa.
	public void resetText() {
		Label.setText(null);
	}


}
