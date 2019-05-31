package com.delivery.java.notification;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionListener;

public class NotificationManager {
	
	private static SystemTray tray = null;
	private static TrayIcon icon = null;
	
	public static void push(String title, String description) {
		makeMessage();
		show(title, description);
	}
	
	public static void push(String title, String description, ActionListener listener) {
		makeMessage();
		bindEvent(listener);
		show(title, description);
	}
	
	private static void makeMessage() {
		tray = SystemTray.getSystemTray();
		
		Image image = java.awt.Toolkit.getDefaultToolkit().createImage("image.png");
		icon = new TrayIcon(image, "Tray Demo");
		
		try {
			tray.add(icon);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void bindEvent(ActionListener listener) {
		if (tray == null || icon == null) return;
		
		icon.addActionListener(listener);
	}
	
	private static void show(String title, String description) {
		if (tray == null || icon == null) return;
		
		icon.displayMessage(title, description, MessageType.INFO);
	}
}
