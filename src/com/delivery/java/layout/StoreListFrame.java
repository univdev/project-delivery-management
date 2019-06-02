package com.delivery.java.layout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.delivery.java.db.schema.CompanySchema;

public class StoreListFrame extends JFrame{
	public JList<CompanySchema> list = null;
	
	public static void main(String [] args)
	{
		new StoreListFrame();
	}
	
	public StoreListFrame()
	{
		setTitle("업소 목록");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(250,400);
		setLocation(400,300);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("업소 목록");
		label.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		titlePanel.add(label);
		panel.add(titlePanel, BorderLayout.NORTH);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		
		JButton selectButton = new JButton("입장");
		selectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				join();
			}
		});
		
		buttonPanel.add(selectButton);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		
		list = new JList<CompanySchema>();
		
		JScrollPane pane = new JScrollPane(list);
		panel.add(pane, BorderLayout.CENTER);
		
		this.add(panel);
	}
	
	private void join() {
		CustomerUIFrame customerFrame = new CustomerUIFrame("고객 UI");
		customerFrame.visible(true);
	}
	
	public void visible(boolean flag) {
		this.setVisible(flag);
	}
}