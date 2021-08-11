package laptopshop.services;

import java.util.List;
import laptopshop.entities.Category;

public interface BrandDAO {
	public void save(Category brand);
	public void update(Category brand);
	public void delete(int id);
	public Category findById(int id);
	public Category findByName(String name);
	public List<Category> all();
	public int count();
}
