package laptopshop;

import java.awt.EventQueue;

import javax.swing.JButton;

import laptopshop.services.UserService;
import laptopshop.views.LoginForm;
import laptopshop.views.MainView;

public class App {

	private static LoginForm loginForm;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginForm = new LoginForm();
					loginForm.setVisible(true);

					login();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void login() {
		JButton click = loginForm.getBtnLogin();

		click.addActionListener(e -> {
			String username = loginForm.getUsername().trim();
			String password = loginForm.getPassword().trim();

			loginForm.getErrorUn().setText("");
			loginForm.getErrorPw().setText("");

			if (username.isEmpty()) {
				loginForm.getErrorUn().setText("Please fill all the blank !");
			} else if (password.isEmpty()) {
				loginForm.getErrorPw().setText("Please fill all the blank !");
			} else {
				UserService model = new UserService();
				boolean isLogin = model.login(username, password);

				if (!isLogin) {
					loginForm.getErrorUn().setText("Incorrect !");
				} else {
					new MainView().setVisible(true);
				}
			}
		});
	}

}
