package laptopshop.entities;

public class Category {
	int _id;
	String _name;
	String _country;
	
	public Category() {
	}

	public Category(int id, String _name, String _country) {
		this._id = id;
		this._name = _name;
		this._country = _country;
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

	public String country() {
		return this._country;
	}
	
	public void setCountry(String country) {
		this._country = country;
	}
	
}
