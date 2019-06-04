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
import javax.swing.table.TableColumnModel;

import com.delivery.java.db.DB;
import com.delivery.java.db.schema.FoodSchema;
import com.delivery.java.notification.NotificationManager;
import com.delivery.java.session.StoreSession;

public class CompanyUIFrame extends JFrame {
	
	private JPanel gridPanel = null;
	private JList<String> foodList = null;
	private ArrayList<FoodSchema> foods = null;
	private DefaultListModel<String> foodsModel = null;
	
	private JLabel priceLabel = null;
	
	private PaymentUIFrame paymentFrame = null;
	
	private JComboBox<Integer> durationCombobox = null;
	private JTable orderListTable;
	
	private int price = 0;
	
	public static void main(String args[]) {
		new CompanyUIFrame("");
	}
	
	/* 600 x 250 */
	
	public CompanyUIFrame(String title) {
		this.setTitle(title);
		this.setSize(new Dimension(600, 270));
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// 음식 데이터 변수에 삽입
		this.setFoodsData();
		// 실제 보여지는 음싣 데이터 초기화
		this.setFoodsList();
		
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
		String columnNames[] = { "번호", "주문자", "내역", "할 말" };
		Object data[][] = { { 1, "주문자", "내역", "할 말" }, { 1, "주문자", "내역", "할 말" } };
		orderListTable = new JTable(data, columnNames);
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
			}
		});
		panel.add(confirmButton);
		
		return panel;
	}
	
	private void setFoodsList() {
		int index = 0;
		foodsModel = new DefaultListModel<String>();
		foodList = new JList<String>(foodsModel);
		
		for (FoodSchema schema : foods) {
			String name = schema.getName();
			int price = schema.getPrice();
			foodsModel.add(index, String.format("%s - %d원", name, price));
			
			index += 1;
		}
	}
	
	private void setFoodsData() {
		int storeIndex = StoreSession.getIdx_s();
		DB db = new DB();
		String sql = String.format("SELECT * FROM foods WHERE idx_s='%d'", storeIndex);
		System.out.println(sql);
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
				DB db = new DB();
				int idx_s = StoreSession.getIdx_s();
				String name = frame.FoodNameTf.getText();
				String sql = String.format("INSERT INTO foods (idx_f, idx_s, name, price) VALUES (sq_f.NEXTVAL, '%d', '%s', '%d')", idx_s, name, frame.FoodPrice);
				db.mq(sql);
				
				JOptionPane.showMessageDialog(null, "음식이 추가되었습니다.");
				
				frame.setVisible(false);
				
				setFoodsData();
				foodsModel.addElement(String.format("%s - %d원", name, frame.FoodPrice));
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
		
		DB db = new DB();
		String sql = String.format("DELETE FROM foods WHERE idx_f='%d'", idx_f);
		db.mq(sql);
		
		JOptionPane.showMessageDialog(null, "데이터가 삭제되었습니다.");
		
		foodsModel.remove(index);
	}
}
