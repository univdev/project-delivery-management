package com.delivery.java.layout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class OrderViewFrame extends JFrame {
	
	public JTable table = null;
	public JButton cancelButton;
	public JButton returnButton;
	
	public static void main(String args[]) {
		new OrderViewFrame("주문 내역", new Dimension(550, 450));
	}
	
	public OrderViewFrame(String title, Dimension d) {
		this.setTitle(title);
		this.setSize(d);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
		panel.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		
		JLabel labelTitle = new JLabel("주문 내역");
		labelTitle.setHorizontalAlignment(JLabel.CENTER);
		labelTitle.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		
		titlePanel.add(labelTitle);
		panel.add(titlePanel, BorderLayout.NORTH);
		
		String columnNames[] = { "번호", "업체", "음식", "배송 여부", "도착 예정시간" };
		Object data[][] = { { 1, "교촌치킨", "간장치킨, 양념치킨", "배송 중", "20분" }, { 2, "본죽", "전복죽", "배송 중", "30분" } };
		
		table = new JTable(data, columnNames);
		table.setRowSelectionAllowed(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		
		cancelButton = new JButton("취소");
		returnButton = new JButton("목록");
		buttonPanel.add(cancelButton);
		buttonPanel.add(returnButton);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		
		this.add(panel);
		
		this.setVisible(true);
	}
	
	public void visible(boolean flag) {
		this.setVisible(flag);
	}
}
