package laptopshop.entities.table;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

public class PaginatedTableDecorator<T> {
	private JTable table;
	private PaginatedDataProvider<T> dataProvider;
	private int[] pageSizes;
	private JPanel contentPanel;
	private int currentPageSize;
	private int currentPage = 1;
	private JPanel pageLinkPanel;
	private PaginatedTableModel<T> objectTableModel;
	private static final int MaxPagingCompToShow = 7;
	private static final String Ellipses = "...";

	private PaginatedTableDecorator(JTable table, PaginatedDataProvider<T> dataProvider, int[] pageSizes,
			int defaultPageSize) {
		this.table = table;
		this.dataProvider = dataProvider;
		this.pageSizes = pageSizes;
		this.currentPageSize = defaultPageSize;
	}

	public static <T> PaginatedTableDecorator<T> decorate(JTable table, PaginatedDataProvider<T> dataProvider,
			int[] pageSizes, int defaultPageSize) {
		PaginatedTableDecorator<T> decorator = new PaginatedTableDecorator<>(table, dataProvider, pageSizes,
				defaultPageSize);
		decorator.init();
		return decorator;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	private void init() {
		initDataModel();
		initPaginationComponents();
		initListeners();
		paginate();
	}

	private void initListeners() {
		objectTableModel.addTableModelListener(this::refreshPageButtonPanel);
	}

	@SuppressWarnings("unchecked")
	private void initDataModel() {
		TableModel model = table.getModel();
		if (!(model instanceof PaginatedTableModel)) {
			throw new IllegalArgumentException("TableModel must be a subclass of ObjectTableModel");
		}
		objectTableModel = (PaginatedTableModel<T>)model;
	}

	private void initPaginationComponents() {
		contentPanel = new JPanel(new BorderLayout());
		JPanel paginationPanel = createPaginationPanel();
		contentPanel.add(paginationPanel, BorderLayout.NORTH);
		contentPanel.add(new JScrollPane(table));
	}

	private JPanel createPaginationPanel() {
		JPanel paginationPanel = new JPanel();
		pageLinkPanel = new JPanel(new GridLayout(1, MaxPagingCompToShow, 3, 3));
		paginationPanel.add(pageLinkPanel);

		if (pageSizes != null) {
			JComboBox<Integer> pageComboBox = new JComboBox<>(Arrays.stream(pageSizes).boxed().toArray(Integer[]::new));
			pageComboBox.addActionListener((ActionEvent e) -> {
				// to preserve current rows position
				int currentPageStartRow = ((currentPage - 1) * currentPageSize) + 1;
				currentPageSize = (Integer) pageComboBox.getSelectedItem();
				currentPage = ((currentPageStartRow - 1) / currentPageSize) + 1;
				paginate();
			});
			paginationPanel.add(Box.createHorizontalStrut(15));
			paginationPanel.add(new JLabel("Page Size: "));
			paginationPanel.add(pageComboBox);
			pageComboBox.setSelectedItem(currentPageSize);
		};
		
		JTextField search = new JTextField();
		search.setPreferredSize(new Dimension(100, 30));
		paginationPanel.add(Box.createHorizontalStrut(15));
		paginationPanel.add(search);
		JButton btnSearch = new JButton("Search");
		paginationPanel.add(btnSearch);
		
		return paginationPanel;
	}

	private void refreshPageButtonPanel(TableModelEvent tme) {
		pageLinkPanel.removeAll();
		int totalRows = dataProvider.getTotalRowCount();
		int pages = (int) Math.ceil((double) totalRows / currentPageSize);
		ButtonGroup buttonGroup = new ButtonGroup();
		if (pages > MaxPagingCompToShow) {
			addPageButton(pageLinkPanel, buttonGroup, 1);
			if (currentPage > (pages - ((MaxPagingCompToShow + 1) / 2))) {
				// case: 1 ... n->lastPage
				pageLinkPanel.add(createEllipsesComponent());
				addPageButtonRange(pageLinkPanel, buttonGroup, pages - MaxPagingCompToShow + 3, pages);
			} else if (currentPage <= (MaxPagingCompToShow + 1) / 2) {
				// case: 1->n ...lastPage
				addPageButtonRange(pageLinkPanel, buttonGroup, 2, MaxPagingCompToShow - 2);
				pageLinkPanel.add(createEllipsesComponent());
				addPageButton(pageLinkPanel, buttonGroup, pages);
			} else {// case: 1 .. x->n .. lastPage
				pageLinkPanel.add(createEllipsesComponent());// first ellipses
				// currentPage is approx mid point among total max-4 center links
				int start = currentPage - (MaxPagingCompToShow - 4) / 2;
				int end = start + MaxPagingCompToShow - 5;
				addPageButtonRange(pageLinkPanel, buttonGroup, start, end);
				pageLinkPanel.add(createEllipsesComponent());// last ellipsis
				addPageButton(pageLinkPanel, buttonGroup, pages);// last page link
			}
		} else {
			addPageButtonRange(pageLinkPanel, buttonGroup, 1, pages);
		}
		pageLinkPanel.getParent().validate();
		pageLinkPanel.getParent().repaint();
	}

	private Component createEllipsesComponent() {
		return new JLabel(Ellipses, SwingConstants.CENTER);
	}

	private void addPageButtonRange(JPanel parentPanel, ButtonGroup buttonGroup, int start, int end) {
		for (; start <= end; start++) {
			addPageButton(parentPanel, buttonGroup, start);
		}
	}

	private void addPageButton(JPanel parentPanel, ButtonGroup buttonGroup, int pageNumber) {
		JToggleButton toggleButton = new JToggleButton(Integer.toString(pageNumber));
		toggleButton.setMargin(new Insets(1, 3, 1, 3));
		buttonGroup.add(toggleButton);
		parentPanel.add(toggleButton);
		if (pageNumber == currentPage) {
			toggleButton.setSelected(true);
		}
		toggleButton.addActionListener(ae -> {
			currentPage = Integer.parseInt(ae.getActionCommand());
			paginate();
		});
	}

	private void paginate() {
		int startIndex = (currentPage - 1) * currentPageSize;
		int endIndex = startIndex + currentPageSize;
		if (endIndex > dataProvider.getTotalRowCount()) {
			endIndex = dataProvider.getTotalRowCount();
		}
		List<T> rows = dataProvider.getRows(startIndex, endIndex);
		objectTableModel.setRows(rows);
		objectTableModel.fireTableDataChanged();
	}
}
