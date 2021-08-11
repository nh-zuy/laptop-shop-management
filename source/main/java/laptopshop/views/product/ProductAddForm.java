package laptopshop.views.product;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import laptopshop.entities.Category;
import laptopshop.entities.Laptop;
import laptopshop.entities.border.RadiusBorder;
import laptopshop.services.BrandService;
import laptopshop.services.LaptopDAO;
import laptopshop.services.LaptopService;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class ProductAddForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LaptopDAO service;
	private JPanel contentPane;
	private JButton btnAdd;
	private JButton btnCancel;
	private JSpinner inventSpinner;
	private JComboBox<String> brandBox;
	private JTextArea textName;
	private JTextArea textImage;
	private JLabel lblNewLabel_1_2;
	private JTextArea textPrice;
	
	/**
	 * Create the frame.
	 */
	public ProductAddForm() {
		this.service = new LaptopService();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(250, 150, 600, 550);
		
		this.initialView();
		this.setData();
		this.setEventButton();
		
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void initialView() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119, 165, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New laptop");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(142, 12, 249, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name");
		lblNewLabel_1_1.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(28, 103, 96, 52);
		contentPane.add(lblNewLabel_1_1);
		
		textName = new JTextArea();
		textName.setFont(new Font("Dialog", Font.PLAIN, 25));
		textName.setBounds(162, 110, 358, 38);
		contentPane.add(textName);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Inventory");
		lblNewLabel_1_1_1.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_1_1.setBounds(28, 235, 134, 52);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Image");
		lblNewLabel_1_1_2.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_1_2.setBounds(28, 299, 96, 52);
		contentPane.add(lblNewLabel_1_1_2);
		
		textImage = new JTextArea();
		textImage.setFont(new Font("Dialog", Font.PLAIN, 25));
		textImage.setBounds(162, 306, 358, 38);
		contentPane.add(textImage);
		
		btnAdd = new JButton("Add");
		btnAdd.setBorder(null);
		btnAdd.setFocusPainted(false);
		btnAdd.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
		btnAdd.setBorder(new RadiusBorder(Color.white, 2, 16, 16, true));
		btnAdd.setBackground(Color.GREEN);
		btnAdd.setForeground(Color.BLACK);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAdd.setBounds(142, 428, 134, 62);
		contentPane.add(btnAdd);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBorder(null);
		btnCancel.setFocusPainted(false);
		btnCancel.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnCancel.setBackground(new Color(204, 0, 51));
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBounds(298, 433, 117, 52);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_2 = new JLabel("Please fill all the blank!");
		lblNewLabel_2.setForeground(Color.GREEN);
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(107, 70, 321, 21);
		contentPane.add(lblNewLabel_2);
		
		inventSpinner = new JSpinner();
		inventSpinner.setAlignmentX(SwingConstants.CENTER);
		inventSpinner.setAlignmentY(SwingConstants.CENTER);
		inventSpinner.setFont(new Font("Arial", Font.PLAIN, 20));
		inventSpinner.setBounds(162, 245, 134, 32);
		contentPane.add(inventSpinner);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Brand");
		lblNewLabel_1_1_2_1.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_1_2_1.setBounds(28, 360, 96, 52);
		contentPane.add(lblNewLabel_1_1_2_1);
		
		brandBox = new JComboBox<String>();
		brandBox.setEditable(true);
		brandBox.setBounds(162, 366, 134, 41);
		contentPane.add(brandBox);
		
		lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_2.setBounds(28, 167, 96, 52);
		contentPane.add(lblNewLabel_1_2);
		
		textPrice = new JTextArea();
		textPrice.setFont(new Font("Dialog", Font.PLAIN, 25));
		textPrice.setBounds(162, 174, 358, 38);
		contentPane.add(textPrice);
	}
	
	private void setData() {
		BrandService service = new BrandService();
		List<Category> brands = service.all();
		
		for (Category brand: brands) {
			brandBox.addItem(brand.name());
		}
	}
	
	private void setEventButton() {
		btnAdd.addActionListener(e -> {
			String name = textName.getText().trim();
			String prices = textPrice.getText().trim();
			String invent = inventSpinner.getValue().toString().trim();
			String image = textImage.getText().trim();
			String brand = brandBox.getSelectedItem().toString().trim();
			
			if (name.isEmpty() || prices.isEmpty() || invent.isEmpty() || brand.isEmpty()) {
				JOptionPane.showMessageDialog(new JPanel(), "Please fill all the blank!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				float price = Float.valueOf(prices);
				int inventory = Integer.valueOf(invent);
				
				if (inventory < 0) {
					JOptionPane.showMessageDialog(new JPanel(), "Invalid value @@ Try again!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Category brandNews = new BrandService().findByName(brand);
				
				System.out.println(brandNews.id());
				
				Laptop product = new Laptop();
				product.setName(name);
				product.setPrice(price);
				product.setInventory(inventory);
				product.setImage(image);
				product.setBrand(brandNews.id());
				
				this.service.save(product);
				dispose();
			}
		});
		
		btnCancel.addActionListener(e -> {
			dispose();
		});
	}
}
