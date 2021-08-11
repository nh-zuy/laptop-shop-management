package laptopshop.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import laptopshop.entities.Category;
import laptopshop.entities.Database;

public class BrandService implements BrandDAO {
	
	private Connection connection;

	public BrandService() {
		this.connection = Database.getConnection();
	}

	@Override
	public void save(Category brand) {
		try {
			String sql = "INSERT INTO brand(name, country) VALUES(?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, brand.name());
			ps.setString(2, brand.country());
			
			ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void update(Category brand) {
		try {
			String sql = "UPDATE brand SET name = ?, country = ? WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, brand.name());
			ps.setString(2, brand.country());
			ps.setInt(3, brand.id());
			ps.executeUpdate();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		try {
			String sql = "DELETE FROM brand WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Category findById(int id) {
		Category brand = null;

		try {
			String sql = "SELECT * FROM brand WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				brand = new Category();
				brand.setID(rs.getInt("id"));
				brand.setName(rs.getString("name"));
				brand.setCountry(rs.getString("country"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return brand;
	}

	@Override
	public List<Category> all() {
		List<Category> brands = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM brand";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				Category brand = new Category();
				brand.setID(rs.getInt("id"));
				brand.setName(rs.getString("name"));
				brand.setCountry(rs.getString("country"));
				
				brands.add(brand);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return brands;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Category findByName(String name) {
		Category brand = null;
		
		try {
			String sql = "SELECT * FROM brand WHERE name = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				brand = new Category();
				brand.setID(rs.getInt("id"));
				brand.setName(rs.getString("name"));
				brand.setCountry(rs.getString("country"));
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return brand;
	}

}
