package laptopshop.entities;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import laptopshop.settings.Config;

public class Database {

	private Database() {}
	
	public static Connection getConnection() {
		return SingletonDatabase.INSTANCE;
	}
	
	private static class SingletonDatabase {
		private static final Connection INSTANCE = makeConnection();
		
		private static Connection makeConnection() {
			Connection conn = null;
			try {
				String host = Config.host;
				File databaseFile = new File(host);
				
				if (!databaseFile.exists()) {
					host = "data/database";
				};
				
				String url = "jdbc:sqlite:" + host;
				conn = DriverManager.getConnection(url);
				Statement st = conn.createStatement();
				st.execute("PRAGMA foreign_keys = ON;");
				System.out.println("Connect successfully!");
			} catch(SQLException ex) {
				ex.printStackTrace();
			};
			
			return conn;
		}
	}
}
