package com.delivery.java.layout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.delivery.java.db.DB;
import com.delivery.java.db.schema.StoreSchema;
import com.delivery.java.session.AccountSession;

public class StoreListFrame extends JFrame{
	public JList<String> list = null;
	
	private ArrayList<StoreSchema> stores = null;
	private DefaultListModel<String> model = null;
	
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
		
		this.setStoreData();
		this.setStoreList();
		
		JScrollPane pane = new JScrollPane(list);
		panel.add(pane, BorderLayout.CENTER);
		
		this.add(panel);
	}
	
	private void join() {
		CustomerUIFrame customerFrame = new CustomerUIFrame("고객 UI");
		customerFrame.visible(true);
	}
	
	private void setStoreData() {
		DB db = new DB();
		String sql = "SELECT * FROM stores";
		stores = new ArrayList<StoreSchema>();
		
		ResultSet rows = db.mfs(sql);
		
		try {
			while (rows.next()) {
				int idx_s = rows.getInt("idx_s");
				int idx_a = rows.getInt("idx_a");
				String name = rows.getString("name");
				Timestamp created_at = rows.getTimestamp("created_at");
				Timestamp updated_at = rows.getTimestamp("updated_at");
				String methods = rows.getString("methods");
				StoreSchema store = new StoreSchema(idx_s, idx_a, name, created_at, updated_at, methods);
				
				stores.add(store);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setStoreList() {
		int index = 0;
		model = new DefaultListModel<String>();
		for (StoreSchema store : stores) {
			list = new JList<String>(model);
			
			model.add(index, store.getName());
			index += 1;
		}
	}
	
	public void visible(boolean flag) {
		this.setVisible(flag);
	}
}