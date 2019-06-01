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

public class StoreList extends JFrame{
	public JList<CompanySchema> list = null;
	
	public StoreList()
	{
		setTitle("¾÷¼Ò¸ñ·Ï¸®½ºÆ®");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(250,400);
		setLocation(400,300);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel("업소 목록");
		JButton selectButton = new JButton("입장");
		selectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		label.setHorizontalAlignment(JLabel.CENTER);
		
		list = new JList<CompanySchema>();
		
		JScrollPane pane = new JScrollPane(list);
		
		panel.add(label, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);
		panel.add(selectButton, BorderLayout.SOUTH);
		add(panel);
		
		setVisible(true);
	}
	
	public static void main(String [] args)
	{
		new StoreList();
	}
}