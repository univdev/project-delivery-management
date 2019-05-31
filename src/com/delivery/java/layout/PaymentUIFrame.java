package com.delivery.java.layout;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PaymentUIFrame extends JFrame {
	
	public JRadioButton cashRadio = null;
	public JRadioButton cardRadio = null;
	public JRadioButton kakaoRadio = null;
	public JRadioButton pointRadio = null;
	public ButtonGroup group = null;
	
	public static void main(String args[]) {
		new PaymentUIFrame("결제 방식 선택", new Dimension(400, 160));
	}

	public PaymentUIFrame(String title, Dimension d) {
		this.setTitle(title);
		this.setSize(d);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JLabel label = new JLabel("결제 방식을 선택해주세요.");
		panel.add(label);
		
		cashRadio = new JRadioButton("현금 결제");
		cardRadio = new JRadioButton("카드 결제");
		kakaoRadio = new JRadioButton("카카오페이");
		pointRadio = new JRadioButton("포인트 결제");
		group = new ButtonGroup();
		
		JPanel paymentPanel = new JPanel();
		
		group.add(cashRadio);
		group.add(cardRadio);
		group.add(kakaoRadio);
		group.add(pointRadio);
		
		paymentPanel.add(cashRadio);
		paymentPanel.add(cardRadio);
		paymentPanel.add(kakaoRadio);
		paymentPanel.add(pointRadio);
		
		panel.add(paymentPanel);
		
		JButton confirmButton = new JButton("최종 결정");
		JButton denyButton = new JButton("뒤로가기");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(confirmButton);
		buttonPanel.add(denyButton);
		
		panel.add(buttonPanel);
		
		this.add(panel);
	}
}
