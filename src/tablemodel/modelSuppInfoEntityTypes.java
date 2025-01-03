package tablemodel;

import java.math.BigDecimal;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Functions.FncTables;

public class modelSuppInfoEntityTypes extends DefaultTableModel {
	
	private static final long serialVersionUID = 1L;

	private boolean editable = false;

	public modelSuppInfoEntityTypes() {
		initThis();
	}

	public modelSuppInfoEntityTypes(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		initThis();
	}

	public modelSuppInfoEntityTypes(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
		initThis();
	}

	public modelSuppInfoEntityTypes(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		initThis();
	}

	public modelSuppInfoEntityTypes(Vector data, Vector columnNames) {
		super(data, columnNames);
		initThis();
	}

	public modelSuppInfoEntityTypes(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		initThis();
	}
	
	String[] COLUMNS = new String[] {
			"Exist", // 0
			"ID", // 1
			"Name", // 2
			"WTAX ID", // 3
			"WTAX Description", // 4
			"WTAX Rate", // 5
			"BIR Code" // 6
	};

	Class[] CLASS_TYPES = new Class[] {
			Boolean.class, // Client ID
			String.class, // Client Name
			Object.class, // Project ID
			String.class, // WTAX ID
			Object.class, // WTAX Description
			BigDecimal.class, // WTAX Rate
			String.class, // BIR Code
	};
	
	boolean[] COLUMN_EDITABLE = new boolean[] { false, false, false, false, false, false, false	};

	private void initThis() {
		setColumnIdentifiers(COLUMNS);
	}

	public Class getColumnClass(int columnIndex) {
		return CLASS_TYPES[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return COLUMN_EDITABLE[columnIndex];
	}

	public void clear() {
		FncTables.clearTable(this);
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
		if(editable){
			COLUMN_EDITABLE = new boolean[] { true, false, false, false, false, false };
		}else{
			COLUMN_EDITABLE = new boolean[] { false, false, false, false, false, false  };
		}
	}

}
