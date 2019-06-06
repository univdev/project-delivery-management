package com.delivery.java.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.delivery.java.session.StoreSession;

public class PaymentUIFrame extends JFrame {
	
	// 결제 Radio
	public JRadioButton cashRadio = null;
	public JRadioButton cardRadio = null;
	public JRadioButton kakaoRadio = null;
	public JRadioButton pointRadio = null;
	public ButtonGroup group = null;
	
	public JButton confirmButton = null;
	public JButton denyButton = null;
	
	/* 400 x 160 */

	public PaymentUIFrame(String title, Dimension d) {
		String[] payments = StoreSession.getMethods().split(",");
		
		this.setTitle(title);
		this.setSize(d);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(49, 220, 215));
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("결제 방식을 선택해주세요.");
		label.setHorizontalAlignment(JLabel.CENTER);
		
		cashRadio = new JRadioButton("현금 결제");
		cashRadio.setActionCommand("cash");
		cardRadio = new JRadioButton("카드 결제");
		cardRadio.setActionCommand("card");
		kakaoRadio = new JRadioButton("카카오페이");
		kakaoRadio.setActionCommand("kakao");
		pointRadio = new JRadioButton("포인트 결제");
		pointRadio.setActionCommand("point");
		group = new ButtonGroup();
		
		JPanel paymentPanel = new JPanel();
		
		group.add(cashRadio);
		group.add(cardRadio);
		group.add(kakaoRadio);
		group.add(pointRadio);
		
		if (Arrays.asList(payments).contains("Cash")) paymentPanel.add(cashRadio);
		if (Arrays.asList(payments).contains("Card")) paymentPanel.add(cardRadio);
		if (Arrays.asList(payments).contains("Kakao")) paymentPanel.add(kakaoRadio);
		if (Arrays.asList(payments).contains("Point")) paymentPanel.add(pointRadio);
		
		confirmButton = new JButton("결정");
		denyButton = new JButton("뒤로가기");
		denyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(confirmButton);
		buttonPanel.add(denyButton);
		
		panel.add(label, BorderLayout.NORTH);
		panel.add(paymentPanel, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		
		this.add(panel);
	}
	
	public void addConfirmEvent(ActionListener listener) {
		confirmButton.addActionListener(listener);
	}
	
	public void addDenyEvent(ActionListener listener) {
		denyButton.addActionListener(listener);
	}
	
	public void visible(boolean flag) {
		this.setVisible(flag);
	}
}
