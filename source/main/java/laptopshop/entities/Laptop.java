package laptopshop.entities;

public class Laptop {
	int _id;
	String _name;
	float _price;
	int _inventory;
	String _image;
	int _brand;
	
	

	public Laptop() {
	}
	
	public Laptop(int id, String _name, float _price, int _inventory, String _image) {
		this._id = id;
		this._name = _name;
		this._price = _price;
		this._inventory = _inventory;
		this._image = _image;
	}

	public Laptop(int id, String _name, float _price, int _inventory, String _image, int brand) {
		this._id = id;
		this._name = _name;
		this._price = _price;
		this._inventory = _inventory;
		this._image = _image;
		this._brand = brand;
	}
	
	public int id() {
		return _id;
	}
	
	public void setID(int id) {
		this._id = id;
	}

	public String name() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}

	public float price() {
		return _price;
	}

	public void setPrice(float _price) {
		this._price = _price;
	}

	public int inventory() {
		return _inventory;
	}

	public void setInventory(int _inventory) {
		this._inventory = _inventory;
	}

	public String image() {
		return _image;
	}

	public void setImage(String _image) {
		this._image = _image;
	}
	
	public int brand() {
		return _brand;
	}

	public void setBrand(int _brand) {
		this._brand = _brand;
	}
}
