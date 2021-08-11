package laptopshop.entities.table;

import java.util.List;

public interface PaginatedDataProvider<T> {
	int getTotalRowCount();
    List<T> getRows(int startIndex, int endIndex);
}
