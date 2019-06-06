package com.delivery.java.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.delivery.java.db.DB;
import com.delivery.java.db.schema.FoodSchema;
import com.delivery.java.db.schema.OrderSchema;
import com.delivery.java.session.AccountSession;

import oracle.sql.DATE;

public class OrderViewFrame extends JFrame {
	
	public JTable table = null;
	public DefaultTableModel tableModel = null;
	public JButton cancelButton;
	public JButton returnButton;
	public ArrayList<OrderSchema> orders;
	
	private DB db = null;
	
	public static void main(String args[]) {
		new OrderViewFrame("주문 내역", new Dimension(550, 450));
	}
	
	public OrderViewFrame(String title, Dimension d) {
		this.setTitle(title);
		this.setSize(d);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(49, 220, 215));
		
		db = new DB();
		orders = new ArrayList<OrderSchema>();
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
		panel.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		
		JLabel labelTitle = new JLabel("주문 내역");
		labelTitle.setHorizontalAlignment(JLabel.CENTER);
		labelTitle.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		
		titlePanel.add(labelTitle);
		panel.add(titlePanel, BorderLayout.NORTH);
		
		tableModel = new DefaultTableModel();
		tableModel.addColumn("번호");
		tableModel.addColumn("업체");
		tableModel.addColumn("음식");
		tableModel.addColumn("배송 여부");
		tableModel.addColumn("도착 예정시간");
		
		table = new JTable(tableModel);
		table.setRowSelectionAllowed(true);
		
		this.getOrder();
		this.drawOrderTable();
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		
		cancelButton = new JButton("취소");
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = table.getSelectedRow();
				String idx_o = tableModel.getValueAt(index, 0).toString();
				String status = tableModel.getValueAt(index, 3).toString();
				
				if (!status.equals("배송 전")) {
					JOptionPane.showMessageDialog(null, "배송 전인 상품만 취소하실 수 있습니다.");
					return;
				}
				
				String sql = String.format("DELETE FROM orders WHERE idx_o='%s'", idx_o);
				
				db.mfs(sql);
				
				JOptionPane.showMessageDialog(null, "상품을 취소했습니다.");
				
				tableModel.removeRow(index);
			}
		});
		
		returnButton = new JButton("목록");
		buttonPanel.add(cancelButton);
		buttonPanel.add(returnButton);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		
		this.add(panel);
	}
	
	private void getOrder() {
		int idx_a = AccountSession.getIdx_a();
		String sql = String.format("SELECT orders.*, stores.name FROM orders, stores" + 
				" WHERE orders.idx_s = stores.idx_s AND orders.idx_a = '%d'", idx_a);
		
		System.out.println(sql);
		ResultSet rs = db.mfs(sql);
		
		try {
			while (rs.next()) {
				int idx_o = rs.getInt("idx_o");
				int idx_s = rs.getInt("idx_s");
				String storeName = rs.getString("name");
				String foods = rs.getString("foods");
				int duration = rs.getInt("duration");
				String comments = rs.getString("comments");
				String method = rs.getString("method");
				Timestamp created_at = rs.getTimestamp("created_at");
				Timestamp updated_at = rs.getTimestamp("updated_at");
				
				OrderSchema schema = new OrderSchema(idx_o, idx_a, idx_s, foods, duration, comments, method, created_at, updated_at);
				schema.setStoreName(storeName);
				
				orders.add(schema);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawOrderTable() {
		for (OrderSchema order : orders) {
			String status = "배송 전";
			
			Timestamp updated_at = order.getUpdated_at();
			int durationToMinute = 1000 * 60 * order.getDuration();
			
			long destination = updated_at.getTime() + durationToMinute;
			long currentTime = System.currentTimeMillis();
			
			if (order.getDuration() > 0 && destination > currentTime)
				status = "배송 중";
			else if (order.getDuration() > 0 && destination <= currentTime)
				status = "배송 완료";
			
			Object[] obj = { order.getIdx_o(), order.getStoreName(), order.getFoods(), status, String.format("%d분", order.getDuration()) };
			tableModel.addRow(obj);
		}
	}
}
