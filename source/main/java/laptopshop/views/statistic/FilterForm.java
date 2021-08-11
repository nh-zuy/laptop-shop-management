package laptopshop.views.statistic;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import laptopshop.entities.Order;
import laptopshop.services.OrderDAO;
import laptopshop.services.OrderService;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class FilterForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OrderDAO service;
	private JPanel contentPane;
	private JButton btnCancel;
	private JTextArea textYear;
	private JTextArea textTotal;

	private JPanel detail;

	private JTable table;

	private JTextArea textMonth;

	private JButton btnSearch;

	/**
	 * Create the frame.
	 */
	public FilterForm() {
		this.service = new OrderService();

		this.initialView();
		this.setEventButton();
		this.resetData();
		this.setData();

		this.setVisible(true);
		this.setResizable(false);
	}

	public void initialView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(250, 150, 600, 620);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(119, 165, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Best Seller Month");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(88, 12, 358, 46);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Year");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(26, 109, 96, 52);
		contentPane.add(lblNewLabel_1);

		textYear = new JTextArea();
		textYear.setFont(new Font("Dialog", Font.PLAIN, 25));
		textYear.setBounds(140, 121, 107, 38);
		contentPane.add(textYear);

		JLabel lblNewLabel_1_1 = new JLabel("Total revenue");
		lblNewLabel_1_1.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(26, 173, 184, 52);
		contentPane.add(lblNewLabel_1_1);

		textTotal = new JTextArea();
		textTotal.setFont(new Font("Dialog", Font.PLAIN, 25));
		textTotal.setBounds(228, 180, 290, 38);
		contentPane.add(textTotal);

		btnCancel = new JButton("Cancel");
		btnCancel.setBorder(null);
		btnCancel.setFocusPainted(false);
		btnCancel.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnCancel.setBackground(new Color(204, 0, 51));
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBounds(311, 514, 117, 52);
		contentPane.add(btnCancel);

		JLabel lblNewLabel_2 = new JLabel("Please fill all the blank!");
		lblNewLabel_2.setForeground(Color.GREEN);
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(107, 70, 321, 21);
		contentPane.add(lblNewLabel_2);

		detail = new JPanel();
		detail.setBounds(12, 250, 576, 252);
		contentPane.add(detail);
		detail.setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		detail.add(toolBar, BorderLayout.NORTH);

		JLabel lblNewLabel_3 = new JLabel("Detail");
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		toolBar.add(lblNewLabel_3);

		btnSearch = new JButton("Search");
		btnSearch.setForeground(Color.BLACK);
		btnSearch.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnSearch.setFocusPainted(false);
		btnSearch.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSearch.setBackground(Color.GREEN);
		btnSearch.setBounds(182, 514, 117, 52);
		contentPane.add(btnSearch);

		JLabel lblNewLabel_1_2 = new JLabel("Year");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_2.setBounds(259, 109, 96, 52);
		contentPane.add(lblNewLabel_1_2);

		textMonth = new JTextArea();
		textMonth.setFont(new Font("Dialog", Font.PLAIN, 25));
		textMonth.setBounds(373, 121, 107, 38);
		contentPane.add(textMonth);
	}

	private void resetData() {
		textYear.setText("");
		textMonth.setText("");
		textTotal.setText("");
	}

	private void setData() {
		List<Order> orders = this.service.all();
		float revenue = 0;

		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<String> row = new Vector<String>();
		Vector<String> headers = new Vector<String>();

		headers.add("#ID");
		headers.add("Customer name");
		headers.add("Total");
		headers.add("Created Time");

		for (Order order : orders) {
			revenue += order.total();

			row.add(String.valueOf(order.id()));
			row.add(order.customerName());
			row.add(String.format("%.0f", order.total()));
			row.add(String.valueOf(order.createdAt()));

			data.add(new Vector<String>(row));
			row.clear();
		}
		textTotal.setText(String.format("%.0f", revenue));

		table = new JTable(data, headers);
		table.setRowHeight(28);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(10);
		table.getColumnModel().getColumn(2).setPreferredWidth(16);
		table.getTableHeader().setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

		JScrollPane sp = new JScrollPane(table);
		detail.add(sp);
	}

	private void setEventButton() {
		btnSearch.addActionListener(e -> {
			String tYear = textYear.getText().trim();
			String tMonth = textMonth.getText().trim();

			if (tYear.isEmpty() && tMonth.isEmpty()) {
				this.resetData();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);

				List<Order> orders = this.service.all();
				Vector<String> row = new Vector<String>();
				float revenue = 0;
				for (Order order : orders) {

					revenue += order.total();

					row.add(String.valueOf(order.id()));
					row.add(order.customerName());
					row.add(String.format("%.0f", order.total()));
					row.add(String.valueOf(order.createdAt()));

					model.addRow(new Vector<String>(row));
					row.clear();
				}
				textTotal.setText(String.format("%.0f", revenue));
			} else {
				int year = -1;
				int month = -1;
				if (tYear.isEmpty()) {
					month = Integer.valueOf(tMonth);
				} else {
					year = Integer.valueOf(tYear);
				}
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);

				List<Order> orders = this.service.all();
				Vector<String> row = new Vector<String>();
				float revenue = 0;
				for (Order order : orders) {
					String createdTime = order.createdAt();
					LocalDate date = LocalDate.parse(createdTime, DateTimeFormatter.ISO_LOCAL_DATE);

					if (date.getYear() == year || date.getMonthValue() == month) {
						revenue += order.total();

						row.add(String.valueOf(order.id()));
						row.add(order.customerName());
						row.add(String.format("%.0f", order.total()));
						row.add(String.valueOf(order.createdAt()));

						model.addRow(new Vector<String>(row));
						row.clear();
					}
				}
				textTotal.setText(String.format("%.0f", revenue));
			}
		});

		btnCancel.addActionListener(e -> {
			dispose();
		});
	}
}
