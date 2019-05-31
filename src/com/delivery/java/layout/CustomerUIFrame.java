package com.delivery.java.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.delivery.java.db.schema.FoodSchema;

public class CustomerUIFrame extends JFrame {
	
	private JPanel gridPanel = null;
	private JList<FoodSchema> foodList = null;
	private JList<FoodSchema> selectedFoodList = null;
	private JLabel priceLabel = null;
	private int price = 0;
	
	public CustomerUIFrame(String title, Dimension d) {
		this.setTitle(title);
		this.setSize(d);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		JButton confirmButton = new JButton("결제");
		
		JPanel leftPanel = new JPanel();
		JLabel foodListLabel = new JLabel("음식 리스트");
		foodListLabel.setHorizontalAlignment(JLabel.LEFT);
//		leftPanel.setBackground(Color.RED);
		leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		leftPanel.add(foodListLabel);
		leftPanel.add(foodListPane);
		leftPanel.add(priceLabel);
		
		JPanel rightPanel = new JPanel();
		JLabel selectedFoodListLabel = new JLabel("선택 리스트");
		selectedFoodListLabel.setHorizontalAlignment(JLabel.LEFT);
//		rightPanel.setBackground(Color.GREEN);
		rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		rightPanel.add(selectedFoodListLabel);
		rightPanel.add(selectedFoodListPane);
		rightPanel.add(confirmButton);
//		rightPanel.add();
		
		gridPanel.add(leftPanel);
		gridPanel.add(rightPanel);
		
		this.add(gridPanel);
		
		this.setVisible(true);
	}
	
	private void setPriceLabel(int price) {
		priceLabel.setText(String.format("총 금액: %d원", price));
	}
}
