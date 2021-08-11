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

public class BrandEditForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BrandDAO service;
	private Category brand;
	private JPanel contentPane;
	private JButton btnUpdate;
	private JButton btnCancel;
	private JTextArea textID;
	private JTextArea textName;
	private JLabel lblNewLabel_1_2;
	private JTextArea textCountry;
	
	/**
	 * Create the frame.
	 */
	public BrandEditForm(Category brand) {
		this.service = new BrandService();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(250, 150, 600, 450);
		
		this.brand = brand;
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
		btnUpdate.setBounds(142, 312, 134, 62);
		contentPane.add(btnUpdate);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBorder(null);
		btnCancel.setFocusPainted(false);
		btnCancel.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnCancel.setBackground(new Color(204, 0, 51));
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBounds(298, 317, 117, 52);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_2 = new JLabel("Please fill all the blank!");
		lblNewLabel_2.setForeground(Color.GREEN);
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(107, 70, 321, 21);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_1_2 = new JLabel("Country");
		lblNewLabel_1_2.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_1_2.setBounds(26, 237, 96, 52);
		contentPane.add(lblNewLabel_1_2);
		
		textCountry = new JTextArea();
		textCountry.setFont(new Font("Dialog", Font.PLAIN, 25));
		textCountry.setBounds(160, 244, 358, 38);
		contentPane.add(textCountry);
	}
	
	private void setData() {
		textID.setText(String.valueOf(brand.id()));
		textName.setText(brand.name());
		textCountry.setText(brand.country());
	}
	
	private void setEventButton() {
		btnUpdate.addActionListener(e -> {
			String tID = textID.getText().trim();
			String name = textName.getText().trim();
			String country = textCountry.getText().trim();
			
			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(new JPanel(), "Please fill all the blank!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				int id = Integer.valueOf(tID);
				
				Category brand = new Category(id, name, country);
				
				this.service.update(brand);
				dispose();
			}
		});
		
		btnCancel.addActionListener(e -> {
			dispose();
		});
	}
}
