package laptopshop.entities;

public class OrderDetail {
	private int id;
	private int order_id;
	private int product_id;
	private int quantity;

	public OrderDetail() {}
	
	public OrderDetail(int id, int orderId, int productId, int quantity) {
		this.id = id;
		this.order_id = orderId;
		this.product_id = productId;
		this.quantity = quantity;
	}
	
	public OrderDetail(OrderDetail detail) {
		this.id = detail.id;
		this.order_id = detail.order_id;
		this.product_id = detail.product_id;
		this.quantity = detail.quantity;
	}

	public int id() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int orderId() {
		return order_id;
	}

	public void setOrderId(int order_id) {
		this.order_id = order_id;
	}

	public int productId() {
		return product_id;
	}

	public void setProductId(int product_id) {
		this.product_id = product_id;
	}

	public int quantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
