package com.delivery.java.layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CustomerSignupFrame extends JFrame {
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
	
	public CustomerSignupFrame(String title) {
		this.setTitle(title);
		this.setSize(374,660);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(49, 220, 215));
		
		label = new JLabel("고객 회원가입");
		label.setFont(new Font("맑은 고딕",Font.BOLD,30));
		label.setBounds(85, 30, 250, 35);
		
		IDlabel = new JLabel("아이디");
		IDlabel.setFont(new Font("맑은 고딕",Font.PLAIN, 15));
		IDlabel.setBounds(30, 80, 150, 35);
		
		IDTextField = new JTextField();
		IDTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		IDTextField.setBounds(30, 113, 300, 30);
		
		PWlabel = new JLabel("비밀번호");
		PWlabel.setFont(new Font("맑은 고딕",Font.PLAIN, 15));
		PWlabel.setBounds(30, 155, 150, 35);
		
		PWTextField = new JPasswordField();
		PWTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		PWTextField.setBounds(30, 188, 300, 30);
		
		PWRlabel = new JLabel("비밀번호 재확인");
		PWRlabel.setFont(new Font("맑은 고딕",Font.PLAIN, 15));
		PWRlabel.setBounds(30, 230, 150, 35);
		
		PWRTextField = new JPasswordField();
		PWRTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		PWRTextField.setBounds(30, 263, 300, 30);
		
		Namelabel = new JLabel("이름");
		Namelabel.setFont(new Font("맑은 고딕",Font.PLAIN, 15));
		Namelabel.setBounds(30, 305, 150, 35);
		
		NameTextField = new JTextField();
		NameTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		NameTextField.setBounds(30, 338, 300, 30);
		
		PhoneNumburlabel = new JLabel("휴대전화");
		PhoneNumburlabel.setFont(new Font("맑은 고딕",Font.PLAIN, 15));
		PhoneNumburlabel.setBounds(30, 380, 150, 35);
		
		PhoneNumburTextField = new JTextField();
		PhoneNumburTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		PhoneNumburTextField.setBounds(30, 413, 300, 30);
		
		Addresslabel = new JLabel("주소");
		Addresslabel.setFont(new Font("맑은 고딕",Font.PLAIN, 15));
		Addresslabel.setBounds(30, 455, 150, 35);
		
		AddressTextField = new JTextField();
		AddressTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		AddressTextField.setBounds(30, 488, 300, 30);
		
		SignupButton = new JButton("가입하기");
		SignupButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		SignupButton.setBounds(30, 555, 140, 35);
		
		BackButton = new JButton("뒤로가기");
		BackButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		BackButton.setBounds(190, 555, 140, 35);
		
		
		add(label);
		add(IDlabel);
		add(IDTextField);
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
		add(SignupButton);
		add(BackButton);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CustomerSignupFrame("고객 회원가입");

	}

}
