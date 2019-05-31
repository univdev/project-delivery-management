package com.delivery.java.layout;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	public static void main(String args[]) {
		new MainFrame("메인 프레임", new Dimension(400, 200));
	}
	
	public MainFrame(String title, Dimension d) {
		this.setTitle(title);
		this.setSize(d);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
