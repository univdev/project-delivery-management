package com.delivery.java.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.delivery.java.db.DB;
import com.delivery.java.db.schema.FoodSchema;
import com.delivery.java.db.schema.OrderSchema;
import com.delivery.java.notification.NotificationManager;
import com.delivery.java.session.AccountSession;
import com.delivery.java.session.StoreSession;

import oracle.sql.TIMESTAMP;

public class CompanyUIFrame extends JFrame {
	
	private JPanel gridPanel = null;
	private JList<FoodSchema> foodList = null;
	private ArrayList<FoodSchema> foods = null;
	private DefaultListModel<FoodSchema> foodsModel = null;
	
	private JLabel priceLabel = null;
	
	private PaymentUIFrame paymentFrame = null;
	
	private JComboBox<Integer> durationCombobox = null;
	private JTable orderListTable;
	private DefaultTableModel tableModel;
	
	private DB db = null;
	
	public ArrayList<OrderSchema> orders;
	
	private int price = 0;
	
	public static void main(String args[]) {
		new CompanyUIFrame("");
	}
	
	/* 600 x 250 */
	
	public CompanyUIFrame(String title) {
		this.setTitle(title);
		this.setSize(new Dimension(900, 560));
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(49, 220, 215));
		
		db = new DB();
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		foodsModel = new DefaultListModel<FoodSchema>();
		foodList = new JList<FoodSchema>(foodsModel);
		
		orders = new ArrayList<OrderSchema>();
		
		tableModel = new DefaultTableModel();
		tableModel.addColumn("번호");
		tableModel.addColumn("주문자");
		tableModel.addColumn("내역");
		tableModel.addColumn("할 말");
		orderListTable = new JTable(tableModel);
		
		// 음식 데이터 변수에 삽입
		this.setFoodsData();
		// 실제 보여지는 음싣 데이터 초기화
		this.setFoodsList();
		
		this.getOrder();
		this.drawOrderTable();
		
		JScrollPane foodListPane = new JScrollPane(foodList);
		
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(1, 2, 10, 10));
		
		JPanel foodButtonPanel = new JPanel();
		JButton foodAddButton = new JButton("추가");
		JButton foodDeleteButton = new JButton("삭제");
		foodButtonPanel.add(foodAddButton);
		foodButtonPanel.add(foodDeleteButton);
		
		foodAddButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddingFoodFrame addFrame = new AddingFoodFrame("음식 추가");
				addFrame.setVisible(true);
				
				addFrame.OkBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						addFoodConfirm(addFrame);
					}
				});
			}
		});
		
		foodDeleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteFoodConfirm();
			}
		});
		
		JButton confirmButton = new JButton("결제");
		confirmButton.addActionListener(new ActionListener() {
			// 결제 취소 관련 로직을 아래에 작성
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				paymentFrame.visible(true);
			}
		});
		
		JPanel leftPanel = new JPanel();
		JPanel foodListLabelPanel = new JPanel();
		JLabel foodListLabel = new JLabel("음식 리스트");
		foodListLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		foodListLabelPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		foodListLabelPanel.add(foodListLabel);
		foodListLabel.setHorizontalAlignment(JLabel.LEFT);
		leftPanel.setLayout(new BorderLayout());
		leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		leftPanel.add(foodListLabelPanel, BorderLayout.NORTH);
		leftPanel.add(foodListPane, BorderLayout.CENTER);
		leftPanel.add(foodButtonPanel, BorderLayout.SOUTH);
		
		JPanel rightPanel = new JPanel();
		JPanel selectedFoodLabelPanel = new JPanel();
		JLabel selectedFoodListLabel = new JLabel("주문 리스트");
		selectedFoodListLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		selectedFoodLabelPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		selectedFoodLabelPanel.add(selectedFoodListLabel);
		selectedFoodListLabel.setHorizontalAlignment(JLabel.LEFT);
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		rightPanel.add(selectedFoodLabelPanel, BorderLayout.NORTH);
		rightPanel.add(orderListTable(), BorderLayout.CENTER);
		rightPanel.add(orderListOptionPanel(), BorderLayout.SOUTH);
		
		gridPanel.add(leftPanel);
		gridPanel.add(rightPanel);
		
		this.add(gridPanel);
	}
	
	private JScrollPane orderListTable() {
		TableColumnModel columns = orderListTable.getColumnModel();
		
		orderListTable.setMinimumSize(new Dimension(150, 250));
		orderListTable.setRowHeight(20);
		columns.getColumn(0).setPreferredWidth(50);
		columns.getColumn(1).setPreferredWidth(50);
		columns.getColumn(2).setPreferredWidth(50);
		columns.getColumn(3).setPreferredWidth(50);
		
		orderListTable.setRowSelectionAllowed(true);
		JScrollPane pane = new JScrollPane(orderListTable);
		pane.setPreferredSize(new Dimension(250, 130));
		
		return pane;
	}
	
	private JPanel orderListOptionPanel() {
		JPanel panel = new JPanel();
		durationCombobox = new JComboBox<Integer>();
		panel.add(durationCombobox);
		
		for (int i = 10; i <= 60; i += 10) {
			durationCombobox.addItem(i);
		}
		
		JButton confirmButton = new JButton("주문 수락");
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = orderListTable.getSelectedRow();
				if (index < 0) {
					JOptionPane.showMessageDialog(null, "주문을 선택해주세요.");
					return;
				}
				
				Date today = new Date();
				String idx_o = tableModel.getValueAt(index, 0).toString();
				int duration = durationCombobox.getItemAt(durationCombobox.getSelectedIndex());
				
				String sql = String.format("UPDATE orders SET"
						+ " duration='%d', updated_at=systimestamp WHERE idx_o='%s'", duration, idx_o);
				db.mfs(sql);
				System.out.println(sql);
				
				NotificationManager.push("주문을 수락했습니다.", String.format("해당 주문은 %d분 뒤에 종료됩니다.", duration));
			}
		});
		panel.add(confirmButton);
		
		return panel;
	}
	
	private void setFoodsList() {
		int index = 0;
		
		for (FoodSchema schema : foods) {
			foodsModel.add(index, schema);
			index += 1;
		}
	}
	
	private void setFoodsData() {
		int storeIndex = StoreSession.getIdx_s();
		DB db = new DB();
		String sql = String.format("SELECT * FROM foods WHERE idx_s='%d' ORDER BY created_at ASC", storeIndex);
		ResultSet rs = db.mfs(sql);
		foods = new ArrayList<FoodSchema>();
		
		try {
			while (rs.next()) {
				int idx_f = rs.getInt("idx_f");
				int idx_s = rs.getInt("idx_s");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				Timestamp created_at = rs.getTimestamp("created_at");
				Timestamp updated_at = rs.getTimestamp("updated_at");
				
				FoodSchema schema = new FoodSchema(idx_f, idx_s, name, price, created_at, updated_at);
				foods.add(schema);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setPriceLabel(int price) {
		priceLabel.setText(String.format("총 금액: %d원", price));
	}
	
	private void addFoodConfirm(AddingFoodFrame frame) {
		if(!frame.FoodNameTf.getText().equals("") && !frame.FoodPriceTf.getText().equals("")) {	// 음식명, 가격 != null
			
			// INSERT문을 통해 FoodPrice (int) 값을 전달하기 위한 Cast
			frame.FoodPrice = Integer.parseInt(frame.FoodPriceTf.getText());

			frame.df = new DecimalFormat("###,###");
			
			if((JOptionPane.showConfirmDialog(null,
					"음식명 : " + frame.FoodNameTf.getText() + ", \n" +
							"가격 : " + frame.df.format(frame.FoodPrice) + " 원 \n" +
							"위 입력사항이 맞습니까?",
							"음식 추가", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)

					== JOptionPane.YES_OPTION)) {
				// Dialog에서 'Yes' 버튼을 눌렀을 때
				// 여기에 INSERT문(DB) 추가
				int idx_s = StoreSession.getIdx_s();
				String name = frame.FoodNameTf.getText();
				String sql = String.format("INSERT INTO foods (idx_f, idx_s, name, price) VALUES (sq_f.NEXTVAL, '%d', '%s', '%d')", idx_s, name, frame.FoodPrice);
				db.mq(sql);
				
				frame.setVisible(false);
				
				setFoodsData();
				
				FoodSchema insertSchema;
				try {
					ResultSet rs = db.mfs(String.format("SELECT * FROM foods WHERE idx_s='%d' ORDER BY created_at DESC", StoreSession.getIdx_s()));
					rs.first();
					
					insertSchema = new FoodSchema(
							rs.getInt("idx_f"),
							rs.getInt("idx_s"),
							rs.getString("name"),
							rs.getInt("price"),
							rs.getTimestamp("created_at"),
							rs.getTimestamp("updated_at"));
					
					foodsModel.addElement(insertSchema);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "음식이 추가되었습니다.");
			}
		} else if (frame.FoodNameTf.getText().equals("") && !frame.FoodPriceTf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "음식명을 입력해 주세요.", "음식 추가", JOptionPane.ERROR_MESSAGE);
		} else if (!frame.FoodNameTf.getText().equals("") && frame.FoodPriceTf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "가격을 입력해 주세요.", "음식 추가", JOptionPane.ERROR_MESSAGE);
		} else if (frame.FoodNameTf.getText().equals("") && frame.FoodPriceTf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "음식명, 가격을 입력해 주세요.", "음식 추가", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void deleteFoodConfirm() {
		int index = foodList.getSelectedIndex();
		if (index < 0) {
			JOptionPane.showMessageDialog(null, "데이터를 선택해주세요!");
			return;
		}
		FoodSchema schema = foods.get(index);
		int idx_f = schema.getIdx_f();
		
		String sql = String.format("DELETE FROM foods WHERE idx_f='%d'", idx_f);
		db.mq(sql);
		
		JOptionPane.showMessageDialog(null, "데이터가 삭제되었습니다.");
		
		foodsModel.remove(index);
	}
	
	private void getOrder() {
		int idx_s = StoreSession.getIdx_s();
		String sql = String.format("SELECT orders.*, accounts.account FROM orders, accounts"
				+ " WHERE orders.idx_a = accounts.idx_a AND orders.idx_s = '%d'", idx_s);
		
		ResultSet rs = db.mfs(sql);
		
		try {
			while (rs.next()) {
				int idx_o = rs.getInt("idx_o");
				int idx_a = rs.getInt("idx_a");
				String foods = rs.getString("foods");
				int duration = rs.getInt("duration");
				String comments = rs.getString("comments");
				String method = rs.getString("method");
				Timestamp created_at = rs.getTimestamp("created_at");
				Timestamp updated_at = rs.getTimestamp("updated_at");
				String account = rs.getString("account");
				
				OrderSchema orderSchema = new OrderSchema(idx_o, idx_a, idx_s, foods, duration, comments, method, created_at, updated_at);
				orderSchema.setAccount(account);
				
				orders.add(orderSchema);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawOrderTable() {
		for (OrderSchema order : orders) {
			String status = "배송 전";
			
			Timestamp updated_at = order.getUpdated_at();
			int durationToMinute = 1000 * 60 * order.getDuration();
			
			long destination = updated_at.getTime() + durationToMinute;
			long currentTime = System.currentTimeMillis();
			
			if (order.getDuration() > 0 && destination > currentTime)
				status = "배송 중";
			else if (order.getDuration() > 0 && destination <= currentTime)
				status = "배송 완료";
			
			Object[] obj = { order.getIdx_o(), order.getAccount(), order.getFoods(), order.getComments() };
			tableModel.addRow(obj);
		}
	}
}
