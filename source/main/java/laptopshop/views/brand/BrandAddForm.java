package laptopshop.views.brand;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import laptopshop.entities.Category;
import laptopshop.entities.border.RadiusBorder;
import laptopshop.services.BrandDAO;
import laptopshop.services.BrandService;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BrandAddForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BrandDAO service;
	private JPanel contentPane;
	private JButton btnAdd;
	private JButton btnCancel;
	private JTextArea textName;
	private JTextArea textCountry;
	
	/**
	 * Create the frame.
	 */
	public BrandAddForm() {
		this.service = new BrandService();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(250, 150, 600, 360);
		
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
		
		JLabel lblNewLabel = new JLabel("New brand");
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
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Country");
		lblNewLabel_1_1_2.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_1_2.setBounds(28, 167, 96, 52);
		contentPane.add(lblNewLabel_1_1_2);
		
		textCountry = new JTextArea();
		textCountry.setFont(new Font("Dialog", Font.PLAIN, 25));
		textCountry.setBounds(162, 174, 358, 38);
		contentPane.add(textCountry);
		
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
		btnAdd.setBounds(142, 224, 134, 62);
		contentPane.add(btnAdd);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBorder(null);
		btnCancel.setFocusPainted(false);
		btnCancel.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnCancel.setBackground(new Color(204, 0, 51));
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBounds(298, 229, 117, 52);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_2 = new JLabel("Please fill all the blank!");
		lblNewLabel_2.setForeground(Color.GREEN);
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(107, 70, 321, 21);
		contentPane.add(lblNewLabel_2);
	}
	
	private void setData() {
	}
	
	private void setEventButton() {
		btnAdd.addActionListener(e -> {
			String name = textName.getText().trim();
			String country = textCountry.getText().trim();
			
			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(new JPanel(), "Please fill all the blank!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				Category brand = new Category();
				brand.setName(name);
				brand.setCountry(country);
				
				this.service.save(brand);
				dispose();
			}
		});
		
		btnCancel.addActionListener(e -> {
			dispose();
		});
	}
}
