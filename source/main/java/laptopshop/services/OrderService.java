package laptopshop.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import laptopshop.entities.Database;
import laptopshop.entities.Order;
import laptopshop.entities.OrderDetail;

public class OrderService implements OrderDAO {
	
	private Connection connection;

	public OrderService() {
		this.connection = Database.getConnection();
	}

	@Override
	public void save(Order order) {
		try {
			String sql = "INSERT INTO tbl_order(customer_name, total, status, created_at) VALUES(?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, order.customerName());
			ps.setFloat(2, order.total());
			ps.setInt(3, order.status());
			ps.setString(4,  String.valueOf(order.createdAt()));
			
			ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void update(Order order) {
		try {
			String sql = "UPDATE tbl_order SET customer_name = ?, total = ?, status = ?, created_at = ? WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, order.customerName());
			ps.setFloat(2, order.total());
			ps.setInt(3, order.status());
			ps.setString(4, String.valueOf(order.createdAt()));
			ps.setInt(5, order.id());
			
			ps.executeUpdate();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		try {
			String sql = "DELETE FROM tbl_order WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			ps.executeUpdate();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Order findById(int id) {
		Order order = null;

		try {
			String sql = "SELECT * FROM tbl_order WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				order = new Order();
				order.setId(rs.getInt("id"));
				order.setCustomerName(rs.getString("customer_name"));
				order.setTotal(rs.getFloat("total"));
				order.setStatus(rs.getInt("status"));
				order.setCreatedAt(rs.getString("created_at"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return order;
	}

	@Override
	public List<Order> all() {
		List<Order> orders = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM tbl_order;";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setCustomerName(rs.getString("customer_name"));
				order.setTotal(rs.getFloat("total"));
				order.setStatus(rs.getInt("status"));
				order.setCreatedAt(rs.getString("created_at"));
				
				orders.add(order);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return orders;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<OrderDetail> getOrderDetail(int orderId) {
		List<OrderDetail> laptops = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM laptop_order WHERE order_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, orderId);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				OrderDetail detail = new OrderDetail();
				detail.setId(rs.getInt("id"));
				detail.setOrderId(rs.getInt("order_id"));
				detail.setProductId(rs.getInt("product_id"));
				detail.setQuantity(rs.getInt("quantity"));
				
				laptops.add(detail);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return laptops;
	}

	@Override
	public void deleteOrder(int id) {
		try {
			String sql = "DELETE FROM laptop_order WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			ps.executeUpdate();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void updateOrder(OrderDetail detail) {
		try {
			String sql = "UPDATE laptop_order SET order_id = ?, product_id = ?, quantity = ? WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, detail.orderId());
			ps.setInt(2, detail.productId());
			ps.setInt(3, detail.quantity());
			ps.setInt(4, detail.id());
			
			ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
