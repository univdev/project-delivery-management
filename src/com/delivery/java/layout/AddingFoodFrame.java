package com.delivery.java.layout;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddingFoodFrame extends JFrame implements KeyListener, ActionListener {

	public JLabel lbl1;
	public JLabel lbl2;
	public JTextField FoodNameTf;
	public JLabel lbl3;
	public JTextField FoodPriceTf;
	public JLabel lbl4;
	public JButton OkBtn;
	public JButton CancelBtn;
	public int FoodPrice;
	public DecimalFormat df;
	public JLabel lbl5;
	public JLabel lbl7;

	public AddingFoodFrame (String title) {

		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(350, 270);
		setLocation(1000, 250);
		this.getContentPane().setBackground(new Color(49, 220, 215));

		setLayout(null);

		lbl1 = new JLabel("음식 추가");
		lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lbl1.setBounds(15, 15, 150, 30);
		
		lbl7 = new JLabel("| Adding Food");
		lbl7.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lbl7.setForeground(Color.GRAY);
		lbl7.setBounds(155, 20, 150, 30);


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
		
		lbl5 = new JLabel("설정 가능한 가격의 범위는 (0 ~ 999,999,999) 입니다.");
		lbl5.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		lbl5.setForeground(Color.RED);
		lbl5.setBounds(50, 140, 300, 15);
		lbl5.setVisible(false);


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
		add(lbl7);
		add(lbl2);
		add(FoodNameTf);
		add(lbl3);
		add(FoodPriceTf);
		add(lbl4);
		add(OkBtn);
		add(CancelBtn);
		add(lbl5);

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
		
		// 9자리를 초과하여 입력 시 더이상 입력 불가, lbl visible(true)
		if(((JTextField)e.getSource()).getText().length() >= 9) {
			e.consume();
			lbl5.setVisible(true);
		}
		
		if(((JTextField)e.getSource()).getText().length() < 9) {
			lbl5.setVisible(false);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == OkBtn) {
//			if(!FoodNameTf.getText().equals("") && !FoodPriceTf.getText().equals("")) {	// 음식명, 가격 != null
//				
//				// INSERT문을 통해 FoodPrice (int) 값을 전달하기 위한 Cast
//				FoodPrice = Integer.parseInt(FoodPriceTf.getText());
//
//				df = new DecimalFormat("###,###");
//				
//				if((JOptionPane.showConfirmDialog(null,
//						"음식명 : " + FoodNameTf.getText() + ", \n" +
//								"가격 : " + df.format(FoodPrice) + " 원 \n" +
//								"위 입력사항이 맞습니까?",
//								"음식 추가", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
//
//						== JOptionPane.YES_OPTION)) {
//					// Dialog에서 'Yes' 버튼을 눌렀을 때
//					// 여기에 INSERT문(DB) 추가
//
//
//
//				} else {
//					// Dialog에서 'No' 버튼을 눌렀을 때
//
//				} 
//
//			} else if (FoodNameTf.getText().equals("") && !FoodPriceTf.getText().equals("")) {
//				JOptionPane.showMessageDialog(null, "음식명을 입력해 주세요.", "음식 추가", JOptionPane.ERROR_MESSAGE);
//			} else if (!FoodNameTf.getText().equals("") && FoodPriceTf.getText().equals("")) {
//				JOptionPane.showMessageDialog(null, "가격을 입력해 주세요.", "음식 추가", JOptionPane.ERROR_MESSAGE);
//			} else if (FoodNameTf.getText().equals("") && FoodPriceTf.getText().equals("")) {
//				JOptionPane.showMessageDialog(null, "음식명, 가격을 입력해 주세요.", "음식 추가", JOptionPane.ERROR_MESSAGE);
//			}

		} else if (obj == CancelBtn) {

			dispose();

		}


	}

}
