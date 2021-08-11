package laptopshop.services;

import java.util.List;

import laptopshop.entities.Laptop;

public interface LaptopDAO {
	public void save(Laptop product);
	public void update(Laptop product);
	public void delete(int id);
	public Laptop findById(int id);
	public List<Laptop> all();
	public int count();
}
