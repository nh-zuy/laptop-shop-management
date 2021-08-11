package laptopshop.views.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;
import laptopshop.entities.Category;
import laptopshop.entities.Laptop;
import laptopshop.entities.border.RadiusBorder;
import laptopshop.entities.table.PaginatedTableModel;
import laptopshop.services.BrandService;
import laptopshop.services.LaptopDAO;
import laptopshop.services.LaptopService;
import laptopshop.entities.table.PaginatedTableDecorator;
import laptopshop.entities.table.PaginatedDataProvider;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.util.List;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class Product extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LaptopDAO service;

	/* View component */
	private JTable table;
	private JPanel header;
	private JLabel message;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;

	private PaginatedDataProvider<Laptop> dataProvider;

	private PaginatedTableDecorator<Laptop> paginatedDecorator;
	private JButton btnRefresh;

	/**
	 * Create the panel.
	 */
	public Product() {
		this.service = new LaptopService();
		
		setBorder(null);
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));

		header = new JPanel();
		header.setBorder(new LineBorder(Color.WHITE, 5));
		header.setBackground(Color.WHITE);
		header.setPreferredSize(new Dimension(100, 500));
		add(header, BorderLayout.NORTH);
		header.setLayout(new BorderLayout(0, 0));

		JPanel form = new JPanel();
		form.setBackground(new Color(119, 165, 251));
		add(form, BorderLayout.CENTER);
		form.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel title_1 = new JPanel();
		title_1.setLayout(null);
		title_1.setPreferredSize(new Dimension(1000, 100));
		title_1.setBackground(new Color(119, 165, 251));
		form.add(title_1);

		JLabel lblNewLabel_1 = new JLabel("Laptop");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 45));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(141, 12, 363, 57);
		title_1.add(lblNewLabel_1);

		message = new JLabel("* Select row to handle data !");
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setForeground(Color.ORANGE);
		message.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		message.setBounds(210, 70, 229, 30);
		title_1.add(message);
		
		btnAdd = new JButton("Add");
		btnAdd.setBackground(new Color(0, 204, 102));
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setBorder(new RadiusBorder(Color.white, 2, 16, 16, true));
		btnAdd.setFont(new Font("Arial", Font.BOLD, 20));
		btnAdd.setBounds(471, 0, 97, 57);
		btnAdd.setFocusPainted(false);
		title_1.add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBackground(Color.YELLOW);
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 20));
		btnUpdate.setBorder(new RadiusBorder(Color.white, 2, 16, 16, true));
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBounds(580, 0, 110, 57);
		title_1.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(new Color(255, 0, 102));
		btnDelete.setFont(new Font("Arial", Font.BOLD, 20));
		btnDelete.setBorder(new RadiusBorder(Color.white, 2, 16, 16, true));
		btnDelete.setFocusPainted(false);
		btnDelete.setBounds(702, 0, 110, 57);
		title_1.add(btnDelete);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnRefresh.setFocusPainted(false);
		btnRefresh.setFont(new Font("Arial", Font.BOLD, 20));
		btnRefresh.setBounds(471, 75, 110, 25);
		title_1.add(btnRefresh);

		/* Manipulating data */
		this.resetTextField();
		this.setData();
		this.setEventButton();
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
        table = new JTable(new PaginatedTableModel<Laptop>() {
			private static final long serialVersionUID = 1L;
			
			@Override
            public Object getValueAt(Laptop product, int columnIndex) {
                switch (columnIndex) {
	                case 0:
	                	return product.id();
                    case 1:
                        return product.name();
                    case 2:
                        return String.format("%.0f", product.price());
                    case 3:
                        return product.inventory();
                    case 4:
                    	return product.image();
                    case 5:
                    	return new BrandService().findById(product.brand()).name();
                }
                return null;
            }

            @Override
            public int getColumnCount() {
                return 6;
            }
            
            @Override
            public String getColumnName(int column) {
                switch (column) {
                	case 0:
                		return "ID";
                    case 1:
                        return "Name";
                    case 2:
                        return "Price";
                    case 3:
                        return "Inventory";
                    case 4:
                        return "Image";
                    case 5:
                    	return "Brand";
                }
                return null;
            }
        });
        table.setAutoCreateRowSorter(true);
        table.setRowHeight(28);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
        JTableHeader headerTable = table.getTableHeader();
        headerTable.setFont(new Font("Arial", Font.BOLD, 18));
        dataProvider = this.createDataProvider();
        paginatedDecorator = PaginatedTableDecorator.decorate(table, dataProvider, new int[]{5, 15, 25, 50, 75, 100}, 15);
        JScrollPane sp = new JScrollPane();
        sp.setViewportView(paginatedDecorator.getContentPanel());
		header.add(sp);
	}
	
	private PaginatedDataProvider<Laptop> createDataProvider() {
		List<Laptop> list = this.service.all();

        return new PaginatedDataProvider<Laptop>() {
            @Override
            public int getTotalRowCount() {
                return list.size();
            }

            @Override
            public List<Laptop> getRows(int startIndex, int endIndex) {
                return list.subList(startIndex, endIndex);
            }
        };
    }

	/**
	 * 
	 */
	private void setEventButton() {
		
		btnRefresh.addActionListener(e -> {
			header.removeAll();
			this.setData();
			this.validate();
			this.repaint();
		});
		
		btnAdd.addActionListener(e -> {
			new ProductAddForm();
		});
		
		btnUpdate.addActionListener(e -> {
			if (table.getSelectedRowCount() == 0) {
				JOptionPane.showMessageDialog(new JPanel(), "Please select ONE row!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (table.getSelectedRowCount() > 1) {
				JOptionPane.showMessageDialog(new JPanel(), "Only ONE row can be updated on time!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int id = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
			String name = table.getValueAt(table.getSelectedRow(), 1).toString();
			float price = Float.valueOf(table.getValueAt(table.getSelectedRow(), 2).toString());
			int inventory = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 3).toString());
			String image = table.getValueAt(table.getSelectedRow(), 4).toString();
			String brand = table.getValueAt(table.getSelectedRow(), 5).toString();
			Category brandNews = new BrandService().findByName(brand);
			Laptop product = new Laptop(id, name, price, inventory, image, brandNews.id());
			
			new ProductEditForm(product);
		});
		
		btnDelete.addActionListener(e -> {
			if (table.getSelectedRowCount() == 0) {
				JOptionPane.showMessageDialog(new JPanel(), "Please select ONE row!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (table.getSelectedRowCount() > 1) {
				JOptionPane.showMessageDialog(new JPanel(), "Only ONE row can be delete on time!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int id = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
			new LaptopService().delete(id);
			header.removeAll();
			this.setData();
			this.validate();
			this.repaint();
		});
	}
}
