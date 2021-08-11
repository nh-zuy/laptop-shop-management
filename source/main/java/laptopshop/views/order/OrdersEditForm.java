package laptopshop.views.order;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import laptopshop.entities.Laptop;
import laptopshop.entities.Order;
import laptopshop.entities.OrderDetail;
import laptopshop.entities.border.RadiusBorder;
import laptopshop.services.LaptopService;
import laptopshop.services.OrderDAO;
import laptopshop.services.OrderService;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.BorderLayout;

public class OrdersEditForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private OrderDAO service;
	private Order order;
	private JPanel contentPane;
	private JButton btnUpdate;
	private JButton btnCancel;
	private JTextArea textID;
	private JTextArea textName;
	private JLabel lblNewLabel_1_2;
	private JTextArea textTotal;

	private JTextArea textTime;

	private JPanel detail;

	private JTable table;

	private JTextArea textStatus;

	private JButton btnAddO, btnUpdateO, btnDeleteO;
	
	/**
	 * Create the frame.
	 */
	public OrdersEditForm(Order order) {
		this.service = new OrderService();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(250, 150, 600, 800);
		
		this.order = order;
		this.initialView();
		this.setEventButton();
		this.setData();
		
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void initialView() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119, 165, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit laptop");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(142, 12, 249, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(26, 109, 96, 52);
		contentPane.add(lblNewLabel_1);
		
		textID = new JTextArea();
		textID.setFont(new Font("Dialog", Font.PLAIN, 25));
		textID.setEditable(false);
		textID.setBounds(160, 116, 96, 38);
		contentPane.add(textID);
		
		JLabel lblNewLabel_1_1 = new JLabel("Customer");
		lblNewLabel_1_1.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(26, 173, 134, 52);
		contentPane.add(lblNewLabel_1_1);
		
		textName = new JTextArea();
		textName.setFont(new Font("Dialog", Font.PLAIN, 25));
		textName.setBounds(160, 180, 358, 38);
		contentPane.add(textName);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBorder(null);
		btnUpdate.setFocusPainted(false);
		btnUpdate.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
		btnUpdate.setBorder(new RadiusBorder(Color.white, 2, 16, 16, true));
		btnUpdate.setBackground(Color.GREEN);
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUpdate.setBounds(142, 683, 134, 62);
		contentPane.add(btnUpdate);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBorder(null);
		btnCancel.setFocusPainted(false);
		btnCancel.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnCancel.setBackground(new Color(204, 0, 51));
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBounds(298, 688, 117, 52);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_2 = new JLabel("Please fill all the blank!");
		lblNewLabel_2.setForeground(Color.GREEN);
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(107, 70, 321, 21);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_1_2 = new JLabel("Total");
		lblNewLabel_1_2.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_2.setBounds(26, 237, 96, 52);
		contentPane.add(lblNewLabel_1_2);
		
		textTotal = new JTextArea();
		textTotal.setFont(new Font("Dialog", Font.PLAIN, 25));
		textTotal.setBounds(160, 244, 358, 38);
		contentPane.add(textTotal);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Time");
		lblNewLabel_1_2_1.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_2_1.setBounds(26, 311, 96, 52);
		contentPane.add(lblNewLabel_1_2_1);
		
		textTime = new JTextArea();
		textTime.setEditable(false);
		textTime.setText("0.0");
		textTime.setFont(new Font("Dialog", Font.PLAIN, 25));
		textTime.setBounds(160, 318, 358, 38);
		contentPane.add(textTime);
		
		detail = new JPanel();
		detail.setBounds(12, 423, 576, 252);
		contentPane.add(detail);
		detail.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Status");
		lblNewLabel_1_2_1_1.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_2_1_1.setBounds(26, 368, 96, 52);
		contentPane.add(lblNewLabel_1_2_1_1);
		
		textStatus = new JTextArea();
		textStatus.setText("0.0");
		textStatus.setFont(new Font("Dialog", Font.PLAIN, 25));
		textStatus.setEditable(false);
		textStatus.setBounds(160, 375, 96, 38);
		contentPane.add(textStatus);
		
		JToolBar toolBar = new JToolBar();
		detail.add(toolBar, BorderLayout.NORTH);
		
		btnAddO = new JButton("");
		btnAddO.setIcon(new ImageIcon(OrdersEditForm.class.getResource("/images/icons8-add-24.png")));
		toolBar.add(btnAddO);
		
		btnUpdateO = new JButton("");
		btnUpdateO.setIcon(new ImageIcon(OrdersEditForm.class.getResource("/images/icons8-update-24.png")));
		toolBar.add(btnUpdateO);
		
		btnDeleteO = new JButton("");
		btnDeleteO.setIcon(new ImageIcon(OrdersEditForm.class.getResource("/images/icons8-delete-24.png")));
		toolBar.add(btnDeleteO);
	}
	
	private void setData() {
		textID.setText(String.valueOf(order.id()));
		textName.setText(order.customerName());
		textTotal.setText(String.format("%.0f", order.total()));
		textTime.setText(String.valueOf(order.createdAt()));
		textStatus.setText(String.valueOf(order.status()));
		
		List<OrderDetail> details = this.service.getOrderDetail(order.id());
		
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<String> row = new Vector<String>();
		Vector<String> headers = new Vector<String>();
		headers.add("#ID");
		headers.add("Product ID");
		headers.add("Name");
		headers.add("Price");
		headers.add("Quantity");
		
		for (OrderDetail detail: details) {
			Laptop laptop = new LaptopService().findById(detail.productId());
			row.add(String.valueOf(detail.id()));
			row.add(String.valueOf(detail.productId()));
			row.add(laptop.name());
			row.add(String.format("%.0f", laptop.price()));
			row.add(String.valueOf(detail.quantity()));
			
			data.add(new Vector<String>(row));
			row.clear();
		}


		table = new JTable(data, headers);
		table.setRowHeight(28);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(10);
		table.getColumnModel().getColumn(2).setPreferredWidth(16);
		table.getTableHeader().setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		
		JScrollPane sp = new JScrollPane(table);
		detail.add(sp);
	}
	

	
	private void setEventButton() {
		btnAddO.addActionListener(e -> {
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			String[] row = {"", "", "", "", ""};
			model.addRow(row);
		});
		btnUpdateO.addActionListener(e -> {
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			int rows = model.getRowCount();
			
			for (int i = 0; i < rows; ++i) {
				String tID = model.getValueAt(i, 0).toString();
				String tQuantity = model.getValueAt(i, 4).toString();
				String tProductId = model.getValueAt(i, 1).toString();
				
				if (tID.isBlank() || tQuantity.isEmpty() || tProductId.isEmpty()) {
					continue;
				}
				
				int id = Integer.valueOf(tID);
				int orderId = Integer.valueOf(order.id());
				int productId = Integer.valueOf(tProductId);
				int quantity = Integer.valueOf(tQuantity);
				OrderDetail detail = new OrderDetail(id, orderId, productId, quantity);
				
				this.service.updateOrder(detail);
			}
		});
		btnDeleteO.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			String tID = model.getValueAt(selectedRow, 0).toString();
			
			if (tID.equals("")) {
				model.removeRow(selectedRow);
			} else {
				int id = Integer.valueOf(tID);
				this.service.deleteOrder(id);
				model.removeRow(selectedRow);
			}
		});
		
		btnUpdate.addActionListener(e -> {
			String tID = textID.getText().trim();
			String name = textName.getText().trim();
			String tTotal = textTotal.getText().trim();
			String tTime = textTime.getText().trim();
			String tStatus = textStatus.getText().trim();
			
			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(new JPanel(), "Please fill all the blank!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				float total = 0;
				int id = 0;
				int status = 0;
				
				try {
					id = Integer.valueOf(tID);
					total = Float.valueOf(tTotal);
					status = Integer.valueOf(tStatus);
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
				}
				
				if (total < 0) {
					JOptionPane.showMessageDialog(new JPanel(), "Invalid value!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Order order = new Order();
				order.setId(id);
				order.setCustomerName(name);
				order.setTotal(total);
				order.setCreatedAt(tTime);
				order.setStatus(status);
				
				this.service.update(order);
				dispose();
			}
		});
		
		btnCancel.addActionListener(e -> {
			dispose();
		});
	}
}
