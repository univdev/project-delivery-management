package com.delivery.java.layout;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

public class AddingFoodFrame extends JFrame implements KeyListener, ActionListener {

	public JLabel lbl1;
	public JLabel lbl2;
	public JTextField FoodNameTf;
	public JLabel lbl3;
	public JTextField FoodPriceTf;
	public MaskFormatter tst;
	public JLabel lbl4;
	public JButton OkBtn;
	public JButton CancelBtn;
	public int FoodPrice;

	public AddingFoodFrame (String title) {

		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(350, 270);
		setLocation(1000, 250);

		setLayout(null);

		lbl1 = new JLabel("음식 추가");
		lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lbl1.setBounds(15, 15, 150, 30);


		// 중단부 
		lbl2 = new JLabel("음식명");
		lbl2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lbl2.setHorizontalAlignment(JLabel.RIGHT);
		lbl2.setBounds(50, 80, 50, 15);

		FoodNameTf = new JTextField();
		FoodNameTf.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		FoodNameTf.setBounds(115, 75, 165, 25);

		lbl3 = new JLabel("가격");
		lbl3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lbl3.setHorizontalAlignment(JLabel.RIGHT);
		lbl3.setBounds(50, 115, 50, 15);

		FoodPriceTf = new JTextField();
		FoodPriceTf.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		FoodPriceTf.setHorizontalAlignment(JTextField.RIGHT);
		FoodPriceTf.setBounds(115, 110, 145, 25);
		FoodPriceTf.addKeyListener(this);

		lbl4 = new JLabel("원");
		lbl4.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lbl4.setHorizontalAlignment(JLabel.RIGHT);
		lbl4.setBounds(265, 115, 15, 15);


		// 하단부 확인 / 취소 버튼
		OkBtn = new JButton("확인");
		OkBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		OkBtn.setBounds(70, 165, 80, 35);
		OkBtn.addActionListener(this);

		CancelBtn = new JButton("취소");
		CancelBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		CancelBtn.setBounds(190, 165, 80, 35);
		CancelBtn.addActionListener(this);



		add(lbl1);
		add(lbl2);
		add(FoodNameTf);
		add(lbl3);
		add(FoodPriceTf);
		add(lbl4);
		add(OkBtn);
		add(CancelBtn);

		setVisible(true);

	}

	// 다 만든 후 main 삭제할 것
	public static void main(String[] args) {
		new AddingFoodFrame("음식 추가");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// FoodPriceTf에는 숫자만 입력 가능하도록 함
		char c = e.getKeyChar();

		if(!Character.isDigit(c)) {
			e.consume();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if (obj == OkBtn) {
			
			if((JOptionPane.showConfirmDialog(null,
					"음식명 : " + FoodNameTf.getText() + ", \n" +
					"가격 : " + FoodPriceTf.getText() + " 원 \n" +
					"위 입력사항이 맞습니까?",
					"음식 추가", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
					
					== JOptionPane.YES_OPTION)) {
				// Dialog에서 'Yes' 버튼을 눌렀을 때
				// INSERT문을 통해 FoodPrice (int) 값을 전달하기 위한 Cast
				FoodPrice = Integer.parseInt(FoodPriceTf.getText());
				// 여기에 INSERT문(DB) 추가

				
				
			} else {
				// Dialog에서 'No' 버튼을 눌렀을 때
				
			}
			
		} else if (obj == CancelBtn) {
			
			dispose();
			
		}
		
		
	}

}
