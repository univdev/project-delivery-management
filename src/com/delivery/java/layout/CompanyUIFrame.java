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

import com.delivery.java.db.schema.FoodSchema;
import com.delivery.java.notification.NotificationManager;

public class CompanyUIFrame extends JFrame {
	
	private JPanel gridPanel = null;
	private JList<FoodSchema> foodList = null;
	private JLabel priceLabel = null;
	private int price = 0;
	private PaymentUIFrame paymentFrame = null;
	private JComboBox<Integer> durationCombobox = null;
	private JTable orderListTable;
	
	public static void main(String args[]) {
		new CompanyUIFrame("", new Dimension(600, 270));
	}
	
	/* 600 x 250 */
	
	public CompanyUIFrame(String title, Dimension d) {
		this.setTitle(title);
		this.setSize(d);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		foodList = new JList<FoodSchema>();
		JScrollPane foodListPane = new JScrollPane(foodList);
		
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(1, 2, 10, 10));
		
		JPanel foodButtonPanel = new JPanel();
		JButton foodAddButton = new JButton("추가");
		JButton foodDeleteButton = new JButton("삭제");
		foodButtonPanel.add(foodAddButton);
		foodButtonPanel.add(foodDeleteButton);
		
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
		leftPanel.add(foodButtonPanel);
		
		JPanel rightPanel = new JPanel();
		JLabel selectedFoodListLabel = new JLabel("주문 리스트");
		selectedFoodListLabel.setHorizontalAlignment(JLabel.LEFT);
		rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		rightPanel.add(selectedFoodListLabel);
		rightPanel.add(orderListTable());
		rightPanel.add(orderListOptionPanel());
		
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
	
	private void setPriceLabel(int price) {
		priceLabel.setText(String.format("총 금액: %d원", price));
	}
}
