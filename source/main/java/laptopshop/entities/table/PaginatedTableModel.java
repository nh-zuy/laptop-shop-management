package laptopshop.entities.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class PaginatedTableModel<T> extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private List<T> rows = new ArrayList<T>();
	
	public List<T> getRows() {
		return this.rows;
	}
	
	public void setRows(List<T> rows2) {
		this.rows = rows2;
	}

	@Override
	public int getRowCount() {
		return this.rows.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		T value = this.rows.get(row);
		return this.getValueAt(value, column);
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	@Override
	public abstract int getColumnCount();
	public abstract Object getValueAt(T t, int colomn);
	@Override
	public abstract String getColumnName(int column);
}
