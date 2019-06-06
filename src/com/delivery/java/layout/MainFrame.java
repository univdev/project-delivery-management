package com.delivery.java.layout;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.delivery.java.db.DB;
import com.delivery.java.encrypt.Encrypt;
import com.delivery.java.session.AccountSession;
import com.delivery.java.session.StoreSession;

import oracle.net.ns.SessionAtts;

public class MainFrame extends JFrame implements ActionListener{

	public JLabel label;
	public JLabel Loginlabel;
	public JButton LoginButton;
	public JLabel CustomerSignuplabel;
	public JButton CustomerSignupButton;
	public JLabel CompanySignuplabel;
	public JButton CompanySignupButton;
	public int DuplicationChecked;


	public MainFrame(String title) {
		this.setTitle(title);
		this.setSize(380,380);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.getContentPane().setBackground(new Color(49, 220, 215));

//		label = new JLabel("배달의 백성");
//		label.setFont(new Font("맑은 고딕",Font.BOLD,30));
//		label.setBounds(105, 30, 200, 35);
		
		ImageIcon logo = new ImageIcon("assets/logo.png");
		label = new JLabel(logo);
		label.setLocation(65, 25);
		label.setSize(240, 54);

		LoginButton = new JButton("로그인");
		LoginButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		LoginButton.setBounds(30, 125, 300, 35);
		LoginButton.addActionListener(this);
		
		Loginlabel = new JLabel("이미 아이디를 가지고 있습니다.");
		Loginlabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		Loginlabel.setBounds(30, 97, 300, 35);

		CustomerSignuplabel = new JLabel("배달을 시켜야하는데 아이디가 없습니다.");
		CustomerSignuplabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		CustomerSignuplabel.setBounds(30, 172, 300, 35);

		CustomerSignupButton = new JButton("고객 회원가입");
		CustomerSignupButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		CustomerSignupButton.setBounds(30, 200, 300, 35);
		CustomerSignupButton.addActionListener(this);

		CompanySignuplabel = new JLabel("업소를 등록하려고 합니다.");
		CompanySignuplabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		CompanySignuplabel.setBounds(30, 247, 300, 35);

		CompanySignupButton = new JButton("사장 회원가입");
		CompanySignupButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		CompanySignupButton.setBounds(30, 275, 300, 35);
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
			LoginFrame login = new LoginFrame("로그인");

			login.setVisible(true);

			login.LoginButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DB db = new DB();

					String account = login.LoginTextField.getText();
					String password = Encrypt.SHA256(login.PasswordTextField.getText());

					String sql = String.format("SELECT * FROM ACCOUNTS WHERE ACCOUNT='%s' AND PASSWORD='%s'", account, password);
					int count = db.mn(sql);

					if (count <= 0) {
						JOptionPane.showMessageDialog(null, "일치하는 계정이 없습니다.");
						return;
					}

					try {
						ResultSet rs = db.mfs(sql);
						rs.first();
						int grade = rs.getInt("grade");

						AccountSession.setIdx_a(rs.getInt("idx_a"));
						AccountSession.setAccount(account);
						AccountSession.setPassword(password);
						AccountSession.setCompany(rs.getString("company"));
						AccountSession.setAddress(rs.getString("address"));
						AccountSession.setPhone(rs.getString("phone"));
						AccountSession.setPoint(rs.getInt("point"));
						AccountSession.setCreated_at(rs.getTimestamp("created_at"));
						AccountSession.setUpdated_at(rs.getTimestamp("updated_at"));

						if (grade == 2) {
							String companySQL = String.format("SELECT * FROM stores WHERE idx_a='%d'", rs.getInt("idx_a"));
							ResultSet companyRows = db.mfs(companySQL);
							companyRows.first();

							StoreSession.setIdx_a(companyRows.getInt("idx_a"));
							StoreSession.setIdx_s(companyRows.getInt("idx_s"));
							StoreSession.setName(companyRows.getString("name"));
							StoreSession.setCreated_at(companyRows.getTimestamp("created_at"));
							StoreSession.setUpdated_at(companyRows.getTimestamp("updated_at"));
						}

						if (grade == 1) {
							StoreListFrame customerFrame = new StoreListFrame();
							customerFrame.setVisible(true);
						} else if (grade == 2) {
							CompanyUIFrame companyFrame = new CompanyUIFrame("업체 관리");
							companyFrame.setVisible(true);
						}

						login.setVisible(false);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			login.BackButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					login.setVisible(false);
					setVisible(true);
				}
			});

		}

		else if(obj == CustomerSignupButton) {
			this.setVisible(false);
			CustomerSignupFrame signup = new CustomerSignupFrame("고객 회원가입");
			DuplicationChecked = 0;	// 고객 회원가입 창을 띄울 때, 중복체크 관련 변수 초기화

			signup.SignupButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DB db = new DB();

					int grade = 1;
					String account = signup.IDTextField.getText();
					String password = signup.PWTextField.getText();
					String passwordConfirm = signup.PWRTextField.getText();
					String address = signup.AddressTextField.getText();
					String phone = signup.PhoneNumburTextField.getText();

					int point = 0;

					if (account.isEmpty()) {
						JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
						return;
					}
					
					if (DuplicationChecked == 0) {
						JOptionPane.showMessageDialog(null, "아이디 중복 확인을 해주세요.", "Message", JOptionPane.INFORMATION_MESSAGE);
					}
					
					if (password.isEmpty()) {
						JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
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

					password = Encrypt.SHA256(password);
					
					if (DuplicationChecked == 1) {
						String accountSQL = String.format("INSERT INTO ACCOUNTS"
							+ " (idx_a, grade, account, password, address, phone, point)"
							+ " VALUES (sq_a.NEXTVAL, '%d', '%s', '%s', '%s', '%s', '%d')", grade, account, password, address, phone, point);

						db.mq(accountSQL);
					
						String checkSQL = String.format("SELECT * FROM ACCOUNTS WHERE"
								+ " GRADE='%d' AND ACCOUNT='%s' AND PASSWORD='%s' AND ADDRESS='%s'"
								+ "  AND PHONE='%s' AND POINT='%d'", grade, account, password, address, phone, point);

						int isComplete = db.mn(checkSQL);

						if (isComplete == 1) {	// 성공적으로 가입됐을 때
							JOptionPane.showMessageDialog(null, "가입이 완료되었습니다!");
							signup.setVisible(false);
							setVisible(true);
							return;
						}
						
						else {
							JOptionPane.showMessageDialog(null, "알 수 없는 이유로 가입에 실패하였습니다. 다시 시도해 주세요.", "Message", JOptionPane.ERROR_MESSAGE);
							return;
						}

					}
				}
			});
			
			signup.BackButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					signup.setVisible(false);
					setVisible(true);
				}
			});
			
			signup.DuplicationCheckButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DB db = new DB();

					String account = signup.IDTextField.getText();

					String sql = String.format("SELECT * FROM ACCOUNTS WHERE ACCOUNT='%s'", account);
					int isDuplicated = db.mn(sql);

					if (isDuplicated == 1) {	// 중복된 ID일 경우
						JOptionPane.showMessageDialog(null, "중복된 ID입니다. 다른 아이디를 입력해 주세요.", "중복확인", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					else {
						JOptionPane.showMessageDialog(null, "사용 가능한 ID입니다!", "중복확인", JOptionPane.INFORMATION_MESSAGE);
						DuplicationChecked = 1;
						return;
					}
				}
			});
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

					if (!signup.Card.isSelected() && !signup.Cash.isSelected() && !signup.Kakao.isSelected() && !signup.Point.isSelected()) {
						JOptionPane.showMessageDialog(null, "적어도 하나의 결제 방식을 선택해 주세요.", "사장 회원가입", JOptionPane.ERROR_MESSAGE);
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

					setVisible(true);
				}
			});
			
			signup.BackButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					signup.setVisible(false);
					setVisible(true);
				}
			});
		}

	}

}
