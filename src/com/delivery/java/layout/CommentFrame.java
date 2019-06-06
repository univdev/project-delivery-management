package com.delivery.java.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CommentFrame extends JFrame {
	
	public JTextField commentField;
	public JButton confirmButton;
	public JButton denyButton;
	
	public CommentFrame() {
		this.setTitle("할 말 입력");
		this.setSize(450, 150);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(49, 220, 215));
		
		JPanel labelPanel = new JPanel();
		labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		JLabel label = new JLabel("할 말씀이 있다면 입력해주세요. (최대 66자까지 입력 가능)");
		label.setHorizontalAlignment(JLabel.CENTER);
		labelPanel.add(label);
		
		JPanel commentPanel = new JPanel();
		commentPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		commentField = new JTextField();
		commentField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(((JTextField)e.getSource()).getText().length() >= 66) {
					JOptionPane.showMessageDialog(null, "최대 66자까지 입력 가능합니다.", "할 말 입력", JOptionPane.ERROR_MESSAGE);
					e.consume();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		commentPanel.add(commentField);
		
		JPanel buttonPanel = new JPanel();
		
		confirmButton = new JButton("최종 결정");
		denyButton = new JButton("뒤로가기");
		
		denyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		
		buttonPanel.add(confirmButton);
		buttonPanel.add(denyButton);
		
		this.add(labelPanel, BorderLayout.NORTH);
		this.add(commentField, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
}
