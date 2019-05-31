package com.delivery.java.layout;

import java.awt.Dimension;

import javax.swing.JFrame;

public class CompanyUIFrame extends JFrame {

	public CompanyUIFrame(String title, Dimension d) {
		this.setTitle(title);
		this.setSize(d);
		this.setLocationRelativeTo(null);
	}
	
	public void visible(boolean flag) {
		this.setVisible(flag);
	}
}
