package laptopshop.services;

import java.util.List;
import laptopshop.entities.Order;
import laptopshop.entities.OrderDetail;

public interface OrderDAO {
	public void save(Order order);
	public void update(Order order);
	public void delete(int id);
	public Order findById(int id);
	public List<Order> all();
	public int count();
	public List<OrderDetail> getOrderDetail(int orderId);
	public void deleteOrder(int id);
	public void updateOrder(OrderDetail detail);
}
