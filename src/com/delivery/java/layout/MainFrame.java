package com.delivery.java.layout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame implements ActionListener{
	
	public JLabel label;
	public JLabel Loginlabel;
	public JButton LoginButton;
	public JLabel CustomerSignuplabel;
	public JButton CustomerSignupButton;
	public JLabel CompanySignuplabel;
	public JButton CompanySignupButton;
	
	
	public MainFrame(String title) {
		this.setTitle(title);
		this.setSize(400,380);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		label = new JLabel("배달의 백성");
		label.setFont(new Font("맑은 고딕",Font.BOLD,30));
		label.setBounds(115, 30, 200, 35);
		
		Loginlabel = new JLabel("이미 아이디를 가지고 있습니다.");
		Loginlabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		Loginlabel.setBounds(30, 77, 300, 35);
		
		LoginButton = new JButton("로그인");
		LoginButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		LoginButton.setBounds(30, 105, 300, 35);
		LoginButton.addActionListener(this);
		
		CustomerSignuplabel = new JLabel("배달을 시켜야하는데 아이디가 없습니다.");
		CustomerSignuplabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		CustomerSignuplabel.setBounds(30, 152, 300, 35);
		
		CustomerSignupButton = new JButton("고객 회원가입");
		CustomerSignupButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		CustomerSignupButton.setBounds(30, 180, 300, 35);
		CustomerSignupButton.addActionListener(this);
		
		CompanySignuplabel = new JLabel("업소를 등록하려고 합니다.");
		CompanySignuplabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		CompanySignuplabel.setBounds(30, 227, 300, 35);
		
		CompanySignupButton = new JButton("사장 회원가입");
		CompanySignupButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		CompanySignupButton.setBounds(30, 255, 300, 35);
		CompanySignupButton.addActionListener(this);
		

		add(label);
		add(Loginlabel);
		add(LoginButton);
		add(CustomerSignuplabel);
		add(CustomerSignupButton);
		add(CompanySignuplabel);
		add(CompanySignupButton);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainFrame("배달의 백성");

	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == LoginButton) {
			this.setVisible(false);
			new LoginFrame("로그인");
			
		}
		
		else if(obj == CustomerSignupButton) {
			this.setVisible(false);
			new CustomerSignupFrame("고객 회원가입");
			
		}
		
		else if(obj == CompanySignupButton) {
			this.setVisible(false);
			new CompanySignupFrame("사장 회원가입");
			
		}
		
	}

}
