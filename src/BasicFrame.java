import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class BasicFrame extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BasicFrame("Delivery", new Dimension(700, 350));
	}
	
	public BasicFrame(String title, Dimension d) {
		this.setTitle(title);
		this.setSize(d);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(new JLabel("create-layout �귣ġ ����"));
		
		this.add(new JLabel("create-input �귣ġ ���߻���"));
	}

}
