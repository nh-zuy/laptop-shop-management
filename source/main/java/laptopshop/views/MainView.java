package laptopshop.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import laptopshop.settings.Size;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

import laptopshop.views.brand.Brand;
import laptopshop.views.order.Orders;
import laptopshop.views.product.Product;
import laptopshop.views.statistic.Statistic;


public class MainView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* Current selection */
	private JButton _choice;
	
	/* View component */
	private JPanel contentPane;
	private JButton btnBrand;
	private JButton btnOrder;
	private JButton btnProduct;
	private JButton btnStatistic;
	private JPanel user;
	private JPanel notification;
	private JPanel task;
	private JPanel workspace;
	private JLabel lblTitle;
	private JTextField search;
	private JLabel avatar;
	private JLabel lblHiAdmin;
	private JPanel panel_1;
	private JLabel lblNotices;

	private JButton btnExit;

	private JButton btnLogout;
	

	/**
	 * Create the frame.
	 */
	public MainView() {
		/* Load setting information */
		this._choice = btnBrand;
		/* Initial view and event */
		this.initialView();
		this.setDashboard();
		this.setEventButton();
		this.setHoverButton();
		this.setCloseApp();
	}
	
	private void initialView() {
		/**
		 *  Common view 
		 **/
		setBounds(250, 20, Size.WIDTH_APP, Size.HEIGHT_APP);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 4, 0, 0));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		/**
		 *  Navigation left bar 
		 **/
		JPanel nav = new JPanel();
		nav.setPreferredSize(new Dimension(150, 100));
		nav.setBackground(new Color(23, 33,53));
		contentPane.add(nav, BorderLayout.WEST);
		nav.setLayout(null);
		
		btnBrand = new JButton("Brand");
		btnBrand.setHorizontalAlignment(SwingConstants.LEADING);
		btnBrand.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8-dashboard-layout-24.png")));
		btnBrand.setBounds(0, 86, 150, 54);
		btnBrand.setFocusPainted(false);
		btnBrand.setForeground(Color.WHITE);
		btnBrand.setBackground(new Color(39, 55, 80));
		btnBrand.setBorder(new MatteBorder(0, 5, 0, 0, new Color(233, 229, 238)));
		btnBrand.setFont(new Font("AnjaliOldLipi", Font.BOLD, 16));
		nav.add(btnBrand);
		
		btnOrder = new JButton(" Order");
		btnOrder.setHorizontalAlignment(SwingConstants.LEADING);
		btnOrder.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8-purchase-order-24.png")));
		btnOrder.setForeground(Color.WHITE);
		btnOrder.setFocusPainted(false);
		btnOrder.setFont(new Font("AnjaliOldLipi", Font.PLAIN, 16));
		btnOrder.setBorder(new EmptyBorder(0, 10, 0, 0));
		btnOrder.setBackground(new Color(23, 33, 53));
		btnOrder.setBounds(0, 218, 150, 53);
		nav.add(btnOrder);
		
		btnProduct = new JButton(" Product");
		btnProduct.setHorizontalAlignment(SwingConstants.LEADING);
		btnProduct.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8-laptop-24.png")));
		btnProduct.setForeground(Color.WHITE);
		btnProduct.setFocusPainted(false);
		btnProduct.setFont(new Font("AnjaliOldLipi", Font.PLAIN, 16));
		btnProduct.setBorder(new EmptyBorder(0, 10, 0, 0));
		btnProduct.setBackground(new Color(23, 33, 53));
		btnProduct.setBounds(0, 152, 150, 53);
		nav.add(btnProduct);
		
		btnStatistic = new JButton(" Statistic");
		btnStatistic.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8-graph-24.png")));
		btnStatistic.setHorizontalAlignment(SwingConstants.LEADING);
		btnStatistic.setForeground(Color.WHITE);
		btnStatistic.setFocusPainted(false);
		btnStatistic.setFont(new Font("AnjaliOldLipi", Font.PLAIN, 16));
		btnStatistic.setBorder(new EmptyBorder(0, 10, 0, 0));
		btnStatistic.setBackground(new Color(23, 33, 53));
		btnStatistic.setBounds(0, 281, 150, 53);
		nav.add(btnStatistic);
		
		btnLogout = new JButton("Logout");
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("AnjaliOldLipi", Font.PLAIN, 16));
		btnLogout.setFocusPainted(false);
		btnLogout.setBorder(null);
		btnLogout.setBackground(new Color(23, 33, 53));
		btnLogout.setBounds(0, 346, 150, 53);
		nav.add(btnLogout);

		/**
		 * The top of the frame: Header
		 */
		JPanel header = new JPanel();
		header.setBackground(new Color(67, 119, 202));
		header.setPreferredSize(new Dimension(70, 70));
		contentPane.add(header, BorderLayout.NORTH);
		header.setLayout(null);
		
		lblTitle = new JLabel(" Laptop Shop");
		lblTitle.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8-menu-48.png")));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("DejaVu Sans Light", Font.PLAIN, 30));
		lblTitle.setBounds(23, 12, 270, 46);
		lblTitle.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblTitle.setFont(new Font("DejaVu Sans Light", Font.BOLD, 30));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblTitle.setFont(new Font("DejaVu Sans Light", Font.PLAIN, 30));
			}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		header.add(lblTitle);
		
		search = new JTextField();
		search.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		search.setText("Search ...");
		search.setBounds(730, 24, 202, 25);
		search.setBorder(null);
		header.add(search);
		search.setColumns(10);
		
		JLabel dotquestion = new JLabel("");
		dotquestion.setBackground(Color.WHITE);
		dotquestion.setForeground(Color.WHITE);
		dotquestion.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8-search-property-32.png")));
		dotquestion.setBounds(940, 12, 48, 46);
		header.add(dotquestion);
		
		/**
		 * Main content
		 */
		JPanel main = new JPanel();
		contentPane.add(main, BorderLayout.CENTER);
		main.setLayout(new BorderLayout(0, 0));
		
		/* Status zone */
		JPanel status = new JPanel();
		status.setPreferredSize(new Dimension(300, 300));
		main.add(status, BorderLayout.WEST);
		status.setLayout(new BorderLayout(0, 0));
		
		user = new JPanel();
		user.setBackground(new Color(119, 165, 251));
		user.setPreferredSize(new Dimension(120, 120));
		status.add(user, BorderLayout.NORTH);
		user.setLayout(null);
		
		avatar = new JLabel("");
		avatar.setHorizontalAlignment(SwingConstants.CENTER);
		avatar.setBounds(12, 22, 103, 65);
		avatar.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8-male-user-64.png")));
		user.add(avatar);
		
		lblHiAdmin = new JLabel("Hello, admin @");
		lblHiAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblHiAdmin.setForeground(Color.WHITE);
		lblHiAdmin.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblHiAdmin.setBounds(0, 86, 142, 22);
		user.add(lblHiAdmin);
		
		btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8-safe-out-32.png")));
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(new Color(119, 165, 251));
		btnExit.setBounds(210, 45, 78, 31);
		btnExit.setBorder(null);
		btnExit.setFocusPainted(false);
		user.add(btnExit);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(192, 32, 6, 55);
		user.add(panel);
		
		notification = new JPanel();
		notification.setPreferredSize(new Dimension(200, 340));
		notification.setBackground(new Color(67, 119, 202));
		status.add(notification, BorderLayout.SOUTH);
		notification.setLayout(null);
		
		lblNotices = new JLabel("Notifications");
		lblNotices.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblNotices.setForeground(Color.GREEN);
		lblNotices.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotices.setBounds(0, 0, 300, 38);
		notification.add(lblNotices);
		
		/* Task zone */
		task = new JPanel();
		task.setBackground(new Color(81,126,211));
		task.setPreferredSize(new Dimension(50, 50));
		status.add(task, BorderLayout.CENTER);
		task.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(145, 20, 6, 130);
		task.add(panel_1);
		
		/**
		 * Main working space
		 */
		workspace = new JPanel();
		main.add(workspace, BorderLayout.CENTER);
		workspace.setLayout(new BorderLayout(5, 5));
	}
	
	/**
	 * 
	 */
	private void setCloseApp() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
	}
	
	private void setDashboard() {
		workspace.removeAll();
		workspace.add(new Dashboard());
		workspace.validate();
		workspace.repaint();
	}
	
	/**
	 * Set event for button
	 * 1. Menu button
	 * 2. Task button
	 */
	private void setEventButton() {
		/* Dashboard */
		btnBrand.addActionListener(e -> {
			workspace.removeAll();
			workspace.add(new Brand());
			workspace.validate();
			workspace.repaint();
		});
		
		/* Menu button */
		btnStatistic.addActionListener(e -> {
			workspace.removeAll();
			workspace.add(new Statistic());
			workspace.validate();
			workspace.repaint();
		});
		
		/* Semester button */
		btnProduct.addActionListener(e -> {
			workspace.removeAll();
			workspace.add(new Product());
			workspace.validate();
			workspace.repaint();
		});
		
		/* Course button */
		btnOrder.addActionListener(e -> {
			workspace.removeAll();
			workspace.add(new Orders());
			workspace.validate();
			workspace.repaint();
		});
		
		/* Exit */
		btnExit.addActionListener(e -> {
			dispose();
		});
		
		/* Exit */
		btnLogout.addActionListener(e -> {
			dispose();
		});
	}
	
	private void setHoverButton() {
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		buttons.add(btnBrand);
		buttons.add(btnOrder);
		buttons.add(btnProduct);
		buttons.add(btnStatistic);
		buttons.add(btnLogout);
		
		for (JButton btn: buttons) {
			btn.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					_choice = btn;
					setSelection();
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
					if (_choice == btn) {
						return;
					}
					btn.setBackground(new Color(39, 55, 80));
					btn.setBorder(new MatteBorder(0, 5, 0, 0, new Color(233, 229, 238)));
					btn.setFont(new Font("AnjaliOldLipi", Font.BOLD, 16));
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
					if (_choice == btn) {
						return;
					}
					btn.setBackground(new Color(23,33,53));
					btn.setBorder(new EmptyBorder(0, 10, 0, 0));
					btn.setFont(new Font("AnjaliOldLipi", Font.PLAIN, 16));
				}
				@Override
				public void mousePressed(MouseEvent arg0) {}
				@Override
				public void mouseReleased(MouseEvent arg0) {}
			});
		}
	}
	
	private void setSelection() {
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		buttons.add(btnBrand);
		buttons.add(btnOrder);
		buttons.add(btnProduct);
		buttons.add(btnStatistic);
		
		for (JButton btn: buttons){
			if (_choice == btn) {
				btn.setBackground(new Color(39, 55, 80));
				btn.setBorder(new MatteBorder(0, 5, 0, 0, new Color(233, 229, 238)));
				btn.setFont(new Font("AnjaliOldLipi", Font.BOLD, 16));
			} else {
				btn.setBackground(new Color(23,33,53));
				btn.setBorder(new EmptyBorder(0, 10, 0, 0));
				btn.setFont(new Font("AnjaliOldLipi", Font.PLAIN, 16));
			}
		};
		
		this.validate();
		this.repaint();
	}
}
