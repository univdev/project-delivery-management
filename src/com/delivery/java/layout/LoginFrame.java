package com.delivery.java.layout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
	
	public JLabel label;
	public JTextField LoginTextField;
	public JPasswordField PasswordTextField;
	public JButton LoginButton;
	public JButton BackButton;
	public JLabel IDlabel;
	public JLabel PWlabel;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LoginFrame("로그인");
	}
	
	public LoginFrame(String title) {
		this.setTitle(title);
		this.setSize(374,350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		label = new JLabel("배달의 백성");
		label.setFont(new Font("맑은 고딕",Font.BOLD,30));
		label.setBounds(30, 30, 200, 35);
		
		IDlabel = new JLabel("아이디");
		IDlabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		IDlabel.setBounds(30, 77, 300, 35);
		
		LoginTextField = new JTextField();
		LoginTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		LoginTextField.setBounds(30, 110, 300, 30);
		
		PWlabel = new JLabel("비밀번호");
		PWlabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		PWlabel.setBounds(30, 152, 300, 35);
		
		PasswordTextField = new JPasswordField();
		PasswordTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		PasswordTextField.setBounds(30, 185, 300, 30);
		
		LoginButton = new JButton("로그인");
		LoginButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		LoginButton.setBounds(30, 250, 140, 35);
		
		BackButton = new JButton("뒤로가기");
		BackButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		BackButton.setBounds(190, 250, 140, 35);
		
		add(label);
		add(IDlabel);
		add(LoginTextField);
		add(PWlabel);
		add(PasswordTextField);
		add(LoginButton);
		add(BackButton);
		
		this.setVisible(true);
	}
	
	public void visible(boolean flag) {
		this.setVisible(flag);
	}

}
