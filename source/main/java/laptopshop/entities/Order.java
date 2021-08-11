package laptopshop.entities;

import java.util.ArrayList;

public class Order {
	
	private int id;
	private String customer_name;
	private float total;
	private int status;
	private String created_at;
	private ArrayList<Laptop> order_details;

	public Order() {}

	public Order(int id, String name, float value, int status) {
		this.id = id;
		this.customer_name = name;
		this.total = value;
		this.status = status;
	}

	public int id() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String customerName() {
		return customer_name;
	}

	public void setCustomerName(String customer_name) {
		this.customer_name = customer_name;
	}

	public float total() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int status() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String createdAt() {
		return created_at;
	}
	
	public void setCreatedAt(String time) {
		this.created_at = time;
	}

	public ArrayList<Laptop> orderDetails() {
		return order_details;
	}

	public void setOrdeDetails(ArrayList<Laptop> order_details) {
		this.order_details = order_details;
	}
}
