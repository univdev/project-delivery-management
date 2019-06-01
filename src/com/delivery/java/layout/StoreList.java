package com.delivery.java.layout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class StoreList extends JFrame{
	public JList<String> list = null;
	
	public StoreList()
	{
		setTitle("업소목록리스트");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(250,400);
		setLocation(400,300);
		
		JPanel jp = new JPanel();
		jp.setLayout(null);
		JLabel jl = new JLabel("업소목록");
		JButton btn = new JButton("이동");
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		jl.setHorizontalAlignment(JLabel.CENTER);
		list = new JList<String>();
		jl.setBounds(67, 30, 100, 30);
		list.setBounds(67, 80, 100, 160);
		btn.setBounds(67, 275, 100, 40);
		jp.add(jl);
		
		jp.add(list);
		jp.add(btn);
		add(jp);
		
		setVisible(true);
	}
	
	public static void main(String [] args)
	{
		new StoreList();
	}
}