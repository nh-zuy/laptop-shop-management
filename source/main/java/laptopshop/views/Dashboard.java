package laptopshop.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Dashboard extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Dashboard() {
		this.initialView();
	}
	
	private void initialView() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		JPanel header = new JPanel();
		header.setBackground(Color.WHITE);
		header.setPreferredSize(new Dimension(100, 100));
		add(header, BorderLayout.NORTH);
		header.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewStudent = new JLabel("Dashboard");
		lblNewStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewStudent.setPreferredSize(new Dimension(500, 50));
		lblNewStudent.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		header.add(lblNewStudent);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(1000, 40));
		header.add(panel);
		
		JPanel form = new JPanel();
		form.setBackground(new Color(119, 165, 251));
		add(form, BorderLayout.CENTER);
		form.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel forminput = new JPanel();
		forminput.setBackground(new Color(119, 165, 251));
		forminput.setPreferredSize(new Dimension(1000, 400));
		form.add(forminput);
		forminput.setLayout(null);
		
		JPanel forminput_1 = new JPanel();
		forminput_1.setLayout(null);
		forminput_1.setPreferredSize(new Dimension(1000, 150));
		forminput_1.setBackground(new Color(119, 165, 251));
		form.add(forminput_1);
	}
}
