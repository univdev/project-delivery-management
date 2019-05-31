package com.delivery.java.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.delivery.java.db.schema.FoodSchema;
import com.delivery.java.notification.NotificationManager;

public class CustomerUIFrame extends JFrame {
	
	private JPanel gridPanel = null;
	private JList<FoodSchema> foodList = null;
	private JList<FoodSchema> selectedFoodList = null;
	private JLabel priceLabel = null;
	private int price = 0;
	private PaymentUIFrame paymentFrame = null;
	
	public static void main(String args[]) {
		new CustomerUIFrame("", new Dimension(600, 250));
	}
	
	/* 600 x 250 */
	
	public CustomerUIFrame(String title, Dimension d) {
		this.setTitle(title);
		this.setSize(d);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		foodList = new JList<FoodSchema>();
		selectedFoodList = new JList<FoodSchema>();
		JScrollPane foodListPane = new JScrollPane(foodList);
		JScrollPane selectedFoodListPane = new JScrollPane(selectedFoodList);
		
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(1, 2, 10, 10));
		
		priceLabel = new JLabel("");
		this.setPriceLabel(price);
		
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
				String actionCommand = model.getActionCommand();
				paymentFrame.visible(false);
				
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
		
		JPanel leftPanel = new JPanel();
		JLabel foodListLabel = new JLabel("음식 리스트");
		foodListLabel.setHorizontalAlignment(JLabel.LEFT);
		leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		leftPanel.add(foodListLabel);
		leftPanel.add(foodListPane);
		leftPanel.add(priceLabel);
		
		JPanel rightPanel = new JPanel();
		JLabel selectedFoodListLabel = new JLabel("선택 리스트");
		selectedFoodListLabel.setHorizontalAlignment(JLabel.LEFT);
		rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		rightPanel.add(selectedFoodListLabel);
		rightPanel.add(selectedFoodListPane);
		rightPanel.add(confirmButton);
		
		gridPanel.add(leftPanel);
		gridPanel.add(rightPanel);
		
		this.add(gridPanel);
		
		this.setVisible(true);
	}
	
	private void setPriceLabel(int price) {
		priceLabel.setText(String.format("총 금액: %d원", price));
	}
}
