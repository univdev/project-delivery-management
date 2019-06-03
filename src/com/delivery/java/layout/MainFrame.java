package com.delivery.java.layout;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.delivery.java.db.DB;
import com.delivery.java.encrypt.Encrypt;

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
			CompanySignupFrame signup = new CompanySignupFrame("사장 회원가입");
			signup.SignupButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DB db = new DB();
					
					int grade = 2;
					String account = signup.IDTextField.getText();
					String password = signup.PWTextField.getText();
					String passwordConfirm = signup.PWRTextField.getText();
					String address = signup.AddressTextField.getText();
					String company = signup.CompanyTextField.getText();
					String phone = signup.PhoneNumburTextField.getText();
					ArrayList<String> methods = new ArrayList<String>();
					
					if (signup.Card.isSelected()) methods.add(signup.Card.getActionCommand());
					if (signup.Cash.isSelected()) methods.add(signup.Cash.getActionCommand());
					if (signup.Kakao.isSelected()) methods.add(signup.Kakao.getActionCommand());
					if (signup.Point.isSelected()) methods.add(signup.Point.getActionCommand());
					
					String methodsString = String.join(",", methods);
					
					System.out.println(methodsString);
					
					int point = 0;
					
					if (account.isEmpty()) {
						JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
						return;
					}
					
					if (!password.equals(passwordConfirm)) {
						JOptionPane.showMessageDialog(null, "비밀번호를 다시 입력해주세요.");
						return;
					}
					
					if (address.isEmpty()) {
						JOptionPane.showMessageDialog(null, "주소를 입력해주세요.");
						return;
					}
					
					if (company.isEmpty()) {
						JOptionPane.showMessageDialog(null, "회사명을 입력해주세요.");
						return;
					}
					
					password = Encrypt.SHA256(password);
					
					String accountSQL = String.format("INSERT INTO ACCOUNTS"
							+ " (idx_a, grade, account, password, address, company, phone, point)"
							+ " VALUES (sq_a.NEXTVAL, '%d', '%s', '%s', '%s', '%s', '%s', '%d')", grade, account, password, address, company, phone, point);
					
					String storeSQL = String.format("INSERT INTO STORES"
							+ " (idx_s, idx_a, name, methods)"
							+ " VALUES (sq_s.NEXTVAL, sq_a.CURRVAL, '%s', '%s')", company, methodsString);
					
					db.mq(accountSQL);
					db.mq(storeSQL);
					
					JOptionPane.showMessageDialog(null, "가입이 완료되었습니다!");
					
					signup.setVisible(false);
				}
			});
		}
		
	}

}
