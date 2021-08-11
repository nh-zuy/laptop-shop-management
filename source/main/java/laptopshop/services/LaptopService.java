package laptopshop.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import laptopshop.entities.Database;
import laptopshop.entities.Laptop;

public class LaptopService implements LaptopDAO {
	
	private Connection connection;
	
	public LaptopService() {
		this.connection = Database.getConnection();
	}

	@Override
	public void save(Laptop product) {
		try {
			String sql = "INSERT INTO laptop(name, price, inventory, image, brand) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, product.name());
			ps.setFloat(2, product.price());
			ps.setInt(3, product.inventory());
			ps.setString(4, product.image());
			ps.setInt(5, product.brand());
			
			ps.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void update(Laptop product) {
		try {
			String sql = "UPDATE laptop SET name = ?, price = ?, inventory = ?, image = ?, brand = ? WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, product.name());
			ps.setFloat(2, product.price());
			ps.setInt(3, product.inventory());
			ps.setString(4, product.image());
			ps.setInt(5, product.brand());
			ps.setInt(6, product.id());
			ps.executeUpdate();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		try {
			String sql = "DELETE FROM laptop WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Laptop findById(int id) {
		Laptop laptop = null;

		try {
			String sql = "SELECT * FROM laptop WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				laptop = new Laptop();

				laptop.setID(rs.getInt("id"));
				laptop.setName(rs.getString("name"));
				laptop.setPrice(rs.getFloat("price"));
				laptop.setInventory(rs.getInt("inventory"));
				laptop.setImage(rs.getString("image"));
				laptop.setBrand(rs.getInt("brand"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return laptop;
	}

	@Override
	public List<Laptop> all() {
		List<Laptop> laptops = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM laptop";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				Laptop laptop = new Laptop();
				
				laptop.setID(rs.getInt("id"));
				laptop.setName(rs.getString("name"));
				laptop.setPrice(rs.getFloat("price"));
				laptop.setInventory(rs.getInt("inventory"));
				laptop.setImage(rs.getString("image"));
				laptop.setBrand(rs.getInt("brand"));
				
				laptops.add(laptop);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return laptops;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
