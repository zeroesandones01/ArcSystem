package tablemodel;

import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Functions.FncTables;

public class modelTTOAccounts extends DefaultTableModel {
	
	private static final long serialVersionUID = 1L;
	
	public modelTTOAccounts() {
		initThis();
	}

	public modelTTOAccounts(int rowCount, int columnCount) {
		super(rowCount, columnCount);
	}

	public modelTTOAccounts(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public modelTTOAccounts(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public modelTTOAccounts(Vector data, Vector columnNames) {
		super(data, columnNames);
	}

	public modelTTOAccounts(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}
	private void initThis() {
		setColumnIdentifiers(new String[] {
			"Particulars", // 3
			"Date", // 1
			"Doc No", // 7
			"Status" // 7
	});
	}
	
	/**
	 * String.class = Alignment.CENTER
	 * Object.class = Alignment.LEFT
	 * BigDecimal.class = Alignment.RIGHT
	 * Integer.class = Alignment.CENTER
	 * 
	 */
	Class[] CLASS_TYPES = new Class[] {
			Object.class, //Phase-Block-Lot
			Timestamp.class, //Remarks
			String.class, //Remarks
			String.class //Unit ID
	};
	
	Boolean[] COLUMN_EDITABLE = new Boolean[] {
			false, //Trans Date
			false, //Remarks
			false, //Remarks
			false //Unit ID
	};


	public Class getColumnClass(int columnIndex) {
		return CLASS_TYPES[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return COLUMN_EDITABLE[columnIndex];
	}

	public void clear() {
		FncTables.clearTable(this);
		
	}

}
