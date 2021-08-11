package laptopshop.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import laptopshop.entities.Database;

public class UserService {
	
	private Connection connection;

	public UserService() {
		this.connection = Database.getConnection();
	}
	
	public boolean login(String username, String password) {
		try {
			String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		};
		
		return false;
	}

}
