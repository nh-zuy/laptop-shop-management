package laptopshop.views.statistic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import laptopshop.entities.Laptop;
import laptopshop.entities.Order;
import laptopshop.entities.OrderDetail;
import laptopshop.services.LaptopDAO;
import laptopshop.services.LaptopService;
import laptopshop.services.OrderDAO;
import laptopshop.services.OrderService;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Statistic extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LaptopDAO laptopService;
	private OrderDAO orderService;

	/* View component */
	private JTable tableLaptop;
	private JPanel product;
	private JLabel message;
	private JPanel order;
	private JLabel lblNewLabel;
	private JButton btnFilter;
	private JLabel lblNewLabel_2;

	private JTable tableOrder;
	private JButton btnBestSale, btnRefreshL;
	private JButton btnBestProduct;

	/**
	 * Create the panel.
	 */
	public Statistic() {
		this.laptopService = new LaptopService();
		this.orderService = new OrderService();

		this.initialView();

		/* Manipulating data */
		this.resetTextField();
		this.setData();
		this.setEventButton();
	}

	private void initialView() {
		setBorder(new LineBorder(Color.WHITE, 3));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));

		product = new JPanel();
		product.setBorder(new LineBorder(Color.WHITE, 5));
		product.setBackground(Color.WHITE);
		product.setPreferredSize(new Dimension(100, 250));
		add(product, BorderLayout.CENTER);
		product.setLayout(new BorderLayout(0, 0));
		JToolBar toolBar = new JToolBar();
		product.add(toolBar, BorderLayout.NORTH);

		lblNewLabel = new JLabel("Laptop");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		toolBar.add(lblNewLabel);

		btnFilter = new JButton("Out of Stock");
		btnFilter.setFocusPainted(false);
		btnFilter.setIcon(new ImageIcon(Statistic.class.getResource("/images/icons8-out-of-stock-24.png")));
		toolBar.add(btnFilter);

		btnBestProduct = new JButton("Best seller");
		btnBestProduct.setIcon(new ImageIcon(Statistic.class.getResource("/images/icons8-best-seller-24.png")));
		toolBar.add(btnBestProduct);

		btnRefreshL = new JButton("Refresh");
		btnRefreshL.setIcon(new ImageIcon(Statistic.class.getResource("/images/icons8-update-24.png")));
		btnRefreshL.setFocusPainted(false);
		toolBar.add(btnRefreshL);

		JPanel form = new JPanel();
		form.setBackground(new Color(119, 165, 251));
		add(form, BorderLayout.NORTH);
		form.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel title_1 = new JPanel();
		title_1.setLayout(null);
		title_1.setPreferredSize(new Dimension(1000, 100));
		title_1.setBackground(new Color(119, 165, 251));
		form.add(title_1);

		JLabel lblNewLabel_1 = new JLabel("Storehouse");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 45));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(323, 0, 363, 69);
		title_1.add(lblNewLabel_1);

		message = new JLabel("* Select row to handle data !");
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setForeground(Color.ORANGE);
		message.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		message.setBounds(401, 70, 229, 30);
		title_1.add(message);

		order = new JPanel();
		order.setBorder(new LineBorder(Color.WHITE, 5));
		order.setBackground(Color.WHITE);
		order.setPreferredSize(new Dimension(100, 250));
		add(order, BorderLayout.SOUTH);
		order.setLayout(new BorderLayout(0, 0));
		JToolBar toolBar2 = new JToolBar();
		order.add(toolBar2, BorderLayout.NORTH);

		lblNewLabel_2 = new JLabel("Order");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		toolBar2.add(lblNewLabel_2);

		btnBestSale = new JButton("Revenue Per Month");
		toolBar2.add(btnBestSale);
		btnBestSale.setFocusPainted(false);
		btnBestSale.setIcon(new ImageIcon(Statistic.class.getResource("/images/icons8-best-seller-24.png")));
	}

	/**
	 * Reset data in textfield
	 */
	private void resetTextField() {
		message.setForeground(Color.BLACK);
		message.setText("* Select row to handle data !");

		this.validate();
		this.repaint();
	}

	/**
	 * Show information account
	 */
	private void setData() {
		/* Table for product inventory */
		List<Laptop> laptops = this.laptopService.all();
		Vector<Vector<String>> data1 = new Vector<Vector<String>>();
		Vector<String> row1 = new Vector<String>();
		Vector<String> headers1 = new Vector<String>();
		headers1.add("#ID");
		headers1.add("Product name");
		headers1.add("Quantity");

		for (Laptop laptop : laptops) {
			row1.add(String.valueOf(laptop.id()));
			row1.add(laptop.name());
			row1.add(String.valueOf(laptop.inventory()));

			data1.add(new Vector<String>(row1));
			row1.clear();
		}

		tableLaptop = new JTable(data1, headers1);
		tableLaptop.setRowHeight(28);
		tableLaptop.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableLaptop.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableLaptop.getTableHeader().setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tableLaptop.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tableLaptop.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tableLaptop.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		JScrollPane sp1 = new JScrollPane(tableLaptop);
		product.add(sp1, BorderLayout.CENTER);

		/* Table for order */
		List<Order> orders = this.orderService.all();
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<String> row = new Vector<String>();
		Vector<String> headers = new Vector<String>();
		headers.add("#ID");
		headers.add("Customer name");
		headers.add("Total");
		headers.add("Time");
		headers.add("Status");

		for (Order order : orders) {
			row.add(String.valueOf(order.id()));
			row.add(order.customerName());
			row.add(String.format("%.0f", order.total()));
			row.add(order.createdAt());
			row.add((order.status() == 0) ? "Pending" : "Done");

			data.add(new Vector<String>(row));
			row.clear();
		}

		tableOrder = new JTable(data, headers);
		tableOrder.setRowHeight(28);
		tableOrder.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableOrder.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableOrder.getTableHeader().setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
		centerRenderer2.setHorizontalAlignment(JLabel.CENTER);
		tableOrder.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tableOrder.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tableOrder.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tableOrder.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tableOrder.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

		JScrollPane sp2 = new JScrollPane(tableOrder);
		order.add(sp2, BorderLayout.CENTER);
	}

	/**
	 * 
	 */
	private void setEventButton() {
		btnFilter.addActionListener(e -> {
			DefaultTableModel model = (DefaultTableModel) tableLaptop.getModel();
			model.setRowCount(0);

			List<Laptop> laptops = this.laptopService.all();
			Vector<String> row = new Vector<String>();
			for (Laptop laptop : laptops) {
				if (laptop.inventory() < 10) {
					row.add(String.valueOf(laptop.id()));
					row.add(laptop.name());
					row.add(String.valueOf(laptop.inventory()));

					model.addRow(new Vector<String>(row));
					row.clear();
				}
			}
		});

		btnBestProduct.addActionListener(e -> {
			DefaultTableModel model = (DefaultTableModel) tableLaptop.getModel();
			model.setRowCount(0);

			List<OrderDetail> allOrderDetails = new ArrayList<>();
			List<OrderDetail> orderDetails = new ArrayList<>();
			List<Order> orders = this.orderService.all();

			for (Order order : orders) {
				orderDetails = this.orderService.getOrderDetail(order.id());

				for (OrderDetail detail : orderDetails) {
					allOrderDetails.add(new OrderDetail(detail));
				}

				orderDetails.clear();
			}

			HashMap<Integer, Integer> soldProductQuantities = new HashMap<>();
			for (OrderDetail detail : allOrderDetails) {
				if (soldProductQuantities.containsKey(detail.productId())) {
					int quantity = soldProductQuantities.get(detail.productId());
					quantity += detail.quantity();
					soldProductQuantities.put(detail.productId(), quantity);
				} else {
					soldProductQuantities.put(detail.productId(), detail.quantity());
				}
			}
			
			Set<Integer> keys = soldProductQuantities.keySet();
			int maxSoldProductQuantity = -1;
			int maxKey = -1;
			for (Integer key: keys) {
				Integer value = soldProductQuantities.get(key);
				if (value > maxSoldProductQuantity) {
					maxKey = key;
					maxSoldProductQuantity = value;
				}
			}
			
			Vector<String> row = new Vector<String>();
			Laptop laptop = this.laptopService.findById(maxKey);
			row.add(String.valueOf(laptop.id()));
			row.add(laptop.name());
			row.add(String.valueOf(laptop.inventory()));
			model.addRow(row);
		});

		btnRefreshL.addActionListener(e -> {
			DefaultTableModel model = (DefaultTableModel) tableLaptop.getModel();
			model.setRowCount(0);

			List<Laptop> laptops = this.laptopService.all();
			Vector<String> row = new Vector<String>();
			for (Laptop laptop : laptops) {
				row.add(String.valueOf(laptop.id()));
				row.add(laptop.name());
				row.add(String.valueOf(laptop.inventory()));

				model.addRow(new Vector<String>(row));
				row.clear();
			}
		});

		btnBestSale.addActionListener(e -> {
			new FilterForm();
		});
	}
}
