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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.delivery.java.db.DB;
import com.delivery.java.db.schema.FoodSchema;
import com.delivery.java.notification.NotificationManager;
import com.delivery.java.session.AccountSession;
import com.delivery.java.session.StoreSession;

public class CustomerUIFrame extends JFrame {
	
	private JPanel gridPanel = null;
	private JList<FoodSchema> foodList = null;
	private DefaultListModel<FoodSchema> foodListModel = null;
	private ArrayList<FoodSchema> foods = null;
	
	private JList<FoodSchema> selectedFoodList = null;
	private DefaultListModel<FoodSchema> selectedFoodListModel = null;
	
	private JLabel priceLabel = null;
	private PaymentUIFrame paymentFrame = null;
	
	public static void main(String args[]) {
		new CustomerUIFrame("");
	}
	
	/* 600 x 250 */
	
	public CustomerUIFrame(String title) {
		this.setTitle(title);
		this.setSize(new Dimension(600, 250));
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		foodListModel = new DefaultListModel<FoodSchema>();
		foodList = new JList<FoodSchema>(foodListModel);
		
		selectedFoodListModel = new DefaultListModel<FoodSchema>();
		selectedFoodList = new JList<FoodSchema>(selectedFoodListModel);
		
		priceLabel = new JLabel("");
		this.setPriceLabel(this.getPrice());
		
		foods = new ArrayList<FoodSchema>();
		
		this.getFoodsData();
		
		int foodsIndex = 0;
		
		for (FoodSchema schema : foods) {
			foodListModel.add(foodsIndex, schema);
			foodsIndex += 1;
		}
		
		foodList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				selectedFoodListModel.addElement(foodList.getSelectedValue());
				setPriceLabel(getPrice());
			}
		});
		
		JScrollPane foodListPane = new JScrollPane(foodList);
		
		JScrollPane selectedFoodListPane = new JScrollPane(selectedFoodList);
		
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(1, 2, 10, 10));
		
		/*
		 * 지불 방식 관련 설정
		 * TODO: paymentFrame을 통해서 선택된 결제방식을 갖고온다.
		 */
		paymentFrame = new PaymentUIFrame("지불 방식", new Dimension(400, 160));
		paymentFrame.addConfirmEvent(new ActionListener() {
			// 결제 관련 로직을 아래에 작성
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ButtonModel model = paymentFrame.group.getSelection();
				if (model == null) {
					JOptionPane.showMessageDialog(null, "결제 수단을 입력해주세요.");
					return;
				}
				DB db = new DB();
				String actionCommand = model.getActionCommand();
				paymentFrame.visible(false);
				
				int idx_a = AccountSession.getIdx_a();
				int idx_s = StoreSession.getIdx_s();
				
				ArrayList<String> foods = new ArrayList<String>();
				String method = paymentFrame.group.getSelection().getActionCommand();
				
				for (int i = 0; i < selectedFoodListModel.getSize(); i += 1) {
					foods.add(selectedFoodListModel.get(i).getName());
				}
				
				String sql = String.format("INSERT INTO orders (idx_o, idx_a, idx_s, foods, method)"
						+ "VALUES (sq_o.NEXTVAL, '%d', '%d', '%s', '%s')", idx_a, idx_s, String.join(",", foods), method);
				
				db.mq(sql);
				
				NotificationManager.push("주문이 완료되었습니다.", "사장님이 확인하실 때 까지 기다리는 중입니다.");
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
		
		OrderViewFrame orderListFrame = new OrderViewFrame("주문 내역", new Dimension(550, 450));
		
		JButton orderCheckButton = new JButton("나의 주문 확인");
		orderCheckButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				orderListFrame.visible(true);
			}
		});
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		JLabel foodListLabel = new JLabel("음식 리스트");
		foodListLabel.setHorizontalAlignment(JLabel.LEFT);
		leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		leftPanel.add(foodListLabel, BorderLayout.NORTH);
		leftPanel.add(foodListPane, BorderLayout.CENTER);
		leftPanel.add(priceLabel, BorderLayout.SOUTH);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		
		JPanel buttonPanel = new JPanel();
		JLabel selectedFoodListLabel = new JLabel("선택 리스트");
		selectedFoodListLabel.setHorizontalAlignment(JLabel.LEFT);
		
		rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		rightPanel.add(selectedFoodListLabel, BorderLayout.NORTH);
		rightPanel.add(selectedFoodListPane, BorderLayout.CENTER);
		buttonPanel.add(confirmButton);
		buttonPanel.add(orderCheckButton);
		rightPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		gridPanel.add(leftPanel);
		gridPanel.add(rightPanel);
		
		this.add(gridPanel);
		
		this.setVisible(true);
	}
	
	private void getFoodsData() {
		int storeIndex = StoreSession.getIdx_s();
		String sql = String.format("SELECT * FROM foods WHERE idx_s='%d' ORDER BY created_at ASC", storeIndex);
		DB db = new DB();
		
		ResultSet rs = db.mfs(sql);
		
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
	
	private int getPrice() {
		int price = 0;
		
		for (int i = 0; i < selectedFoodListModel.getSize(); i += 1) {
			price += selectedFoodListModel.get(i).getPrice();
		}
		
		return price;
	}
}
