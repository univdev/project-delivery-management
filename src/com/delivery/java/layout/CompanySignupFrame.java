package com.delivery.java.layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.delivery.java.db.DB;

import oracle.net.jdbc.TNSAddress.Address;

public class CompanySignupFrame extends JFrame {
	public JLabel label;
	public JLabel IDlabel;
	public JLabel PWlabel;
	public JLabel PWRlabel;
	public JLabel Namelabel;
	public JLabel PhoneNumburlabel;
	public JLabel Addresslabel;
	public JTextField IDTextField;
	public JPasswordField PWTextField;
	public JPasswordField PWRTextField;
	public JTextField NameTextField;
	public JTextField PhoneNumburTextField;
	public JButton SignupButton;
	public JButton BackButton;

	public JTextField AddressTextField;
	public JLabel Companylabel;
	public JTextField CompanyTextField;
	public JLabel Paymentlabel;

	public JCheckBox Cash;
	public JCheckBox Card;
	public JCheckBox Point;
	public JCheckBox Kakao;
	public JButton uniqueCheckButton;

	public String uniqueCheck = "";

	public CompanySignupFrame(String title) {
		this.setTitle(title);
		this.setSize(374, 890);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(49, 220, 215));

		label = new JLabel("사장님 회원가입");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		label.setBounds(65, 30, 250, 35);

		IDlabel = new JLabel("아이디");
		IDlabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		IDlabel.setBounds(30, 80, 150, 35);

		IDTextField = new JTextField();
		IDTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		IDTextField.setBounds(30, 113, 300, 30);

		uniqueCheckButton = new JButton("중복 확인");
		uniqueCheckButton.setBounds(30, 153, 300, 30);

		PWlabel = new JLabel("비밀번호");
		PWlabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		PWlabel.setBounds(30, 195, 150, 35);

		PWTextField = new JPasswordField();
		PWTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		PWTextField.setBounds(30, 228, 300, 30);

		PWRlabel = new JLabel("비밀번호 재확인");
		PWRlabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		PWRlabel.setBounds(30, 270, 150, 35);

		PWRTextField = new JPasswordField();
		PWRTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		PWRTextField.setBounds(30, 303, 300, 30);

		Namelabel = new JLabel("이름");
		Namelabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		Namelabel.setBounds(30, 345, 150, 35);

		NameTextField = new JTextField();
		NameTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		NameTextField.setBounds(30, 378, 300, 30);

		PhoneNumburlabel = new JLabel("휴대전화");
		PhoneNumburlabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		PhoneNumburlabel.setBounds(30, 420, 150, 35);

		PhoneNumburTextField = new JTextField();
		PhoneNumburTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		PhoneNumburTextField.setBounds(30, 453, 300, 30);

		Addresslabel = new JLabel("주소");
		Addresslabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		Addresslabel.setBounds(30, 495, 150, 35);

		AddressTextField = new JTextField();
		AddressTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		AddressTextField.setBounds(30, 528, 300, 30);

		Companylabel = new JLabel("업소명");
		Companylabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		Companylabel.setBounds(30, 570, 150, 35);

		CompanyTextField = new JTextField();
		CompanyTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		CompanyTextField.setBounds(30, 603, 300, 30);

		Paymentlabel = new JLabel("가능한 결제 방식");
		Paymentlabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		Paymentlabel.setBounds(30, 645, 150, 35);

		Cash = new JCheckBox("현금");
		Cash.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		Cash.setBounds(30, 678, 100, 35);
		Cash.setActionCommand("Cash");
		Cash.setBackground(new Color(49, 220, 215));

		Card = new JCheckBox("카드");
		Card.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		Card.setBounds(180, 678, 100, 35);
		Card.setActionCommand("Card");
		Card.setBackground(new Color(49, 220, 215));

		Point = new JCheckBox("포인트");
		Point.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		Point.setBounds(30, 711, 100, 35);
		Point.setActionCommand("Point");
		Point.setBackground(new Color(49, 220, 215));

		Kakao = new JCheckBox("카카오페이");
		Kakao.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		Kakao.setBounds(180, 711, 110, 35);
		Kakao.setActionCommand("Kakao");
		Kakao.setBackground(new Color(49, 220, 215));

		SignupButton = new JButton("가입하기");
		SignupButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		SignupButton.setBounds(30, 785, 140, 35);

		BackButton = new JButton("뒤로가기");
		BackButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		BackButton.setBounds(190, 785, 140, 35);

		add(label);
		add(IDlabel);
		add(IDTextField);
		add(uniqueCheckButton);
		add(PWlabel);
		add(PWTextField);
		add(PWRlabel);
		add(PWRTextField);
		add(Namelabel);
		add(NameTextField);
		add(PhoneNumburlabel);
		add(PhoneNumburTextField);
		add(Addresslabel);
		add(AddressTextField);
		add(Companylabel);
		add(CompanyTextField);
		add(SignupButton);
		add(BackButton);
		add(Paymentlabel);
		add(Cash);
		add(Card);
		add(Point);
		add(Kakao);

		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CompanySignupFrame("사장 회원가입");

	}

}
