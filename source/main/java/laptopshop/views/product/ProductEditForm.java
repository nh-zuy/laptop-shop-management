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

public class ProductEditForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LaptopDAO service;
	private Laptop laptop;
	private JPanel contentPane;
	private JButton btnUpdate;
	private JButton btnCancel;
	private JSpinner inventSpinner;
	private JComboBox<String> brandBox;
	private JTextArea textID;
	private JTextArea textName;
	private JTextArea textImage;
	private JLabel lblNewLabel_1_2;
	private JTextArea textPrice;
	
	/**
	 * Create the frame.
	 */
	public ProductEditForm(Laptop laptop) {
		this.service = new LaptopService();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(250, 150, 600, 600);
		
		this.laptop = laptop;
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
		
		JLabel lblNewLabel_1_1 = new JLabel("Name");
		lblNewLabel_1_1.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(26, 173, 96, 52);
		contentPane.add(lblNewLabel_1_1);
		
		textName = new JTextArea();
		textName.setFont(new Font("Dialog", Font.PLAIN, 25));
		textName.setBounds(160, 180, 358, 38);
		contentPane.add(textName);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Inventory");
		lblNewLabel_1_1_1.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_1_1.setBounds(26, 305, 134, 52);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Image");
		lblNewLabel_1_1_2.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_1_2.setBounds(26, 369, 96, 52);
		contentPane.add(lblNewLabel_1_1_2);
		
		textImage = new JTextArea();
		textImage.setFont(new Font("Dialog", Font.PLAIN, 25));
		textImage.setBounds(160, 376, 358, 38);
		contentPane.add(textImage);
		
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
		btnUpdate.setBounds(142, 494, 134, 62);
		contentPane.add(btnUpdate);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBorder(null);
		btnCancel.setFocusPainted(false);
		btnCancel.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnCancel.setBackground(new Color(204, 0, 51));
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBounds(298, 499, 117, 52);
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
		inventSpinner.setBounds(160, 315, 134, 32);
		contentPane.add(inventSpinner);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Brand");
		lblNewLabel_1_1_2_1.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_1_2_1.setBounds(26, 430, 96, 52);
		contentPane.add(lblNewLabel_1_1_2_1);
		
		brandBox = new JComboBox<String>();
		brandBox.setEditable(true);
		brandBox.setBounds(160, 436, 134, 41);
		contentPane.add(brandBox);
		
		lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_2.setBounds(26, 237, 96, 52);
		contentPane.add(lblNewLabel_1_2);
		
		textPrice = new JTextArea();
		textPrice.setFont(new Font("Dialog", Font.PLAIN, 25));
		textPrice.setBounds(160, 244, 358, 38);
		contentPane.add(textPrice);
	}
	
	private void setData() {
		textID.setText(String.valueOf(laptop.id()));
		textName.setText(laptop.name());
		textPrice.setText(String.format("%.0f", laptop.price()));
		inventSpinner.setValue(laptop.inventory());
		textImage.setText(laptop.image());
		
		BrandService service = new BrandService();
		List<Category> brands = service.all();
		
		for (Category brand: brands) {
			brandBox.addItem(brand.name());
		}
		
		Category brandNews = new BrandService().findById(laptop.brand());
		brandBox.setSelectedItem(brandNews.name());
	}
	
	private void setEventButton() {
		btnUpdate.addActionListener(e -> {
			String tID = textID.getText().trim();
			String name = textName.getText().trim();
			String prices = textPrice.getText().trim();
			String invent = inventSpinner.getValue().toString().trim();
			String image = textImage.getText().trim();
			String brand = brandBox.getSelectedItem().toString().trim();
			
			if (name.isEmpty() || prices.isEmpty() || invent.isEmpty() || brand.isEmpty()) {
				JOptionPane.showMessageDialog(new JPanel(), "Please fill all the blank!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				int id = Integer.valueOf(tID);
				float price = Float.valueOf(prices);
				int inventory = Integer.valueOf(invent);
				
				if (inventory < 0) {
					JOptionPane.showMessageDialog(new JPanel(), "Invalid value @@ Try again!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Category brandNews = new BrandService().findByName(brand);
				
				System.out.println(brandNews.id());
				
				Laptop product = new Laptop(id, name, price, inventory, image, brandNews.id());
				
				this.service.update(product);
				dispose();
			}
		});
		
		btnCancel.addActionListener(e -> {
			dispose();
		});
	}
}
