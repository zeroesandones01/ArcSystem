package tablemodel;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Functions.FncTables;

public class modeladditem_supplies extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	private boolean editable = false;

	public modeladditem_supplies() {
		initThis();
	}

	public modeladditem_supplies(boolean editable) {
		initThis();
	}

	public modeladditem_supplies(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		initThis();
	}

	public modeladditem_supplies(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
		initThis();
	}

	public modeladditem_supplies(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		initThis();
	}

	public modeladditem_supplies(Vector data, Vector columnNames) {
		super(data, columnNames);
		initThis();
	}

	public modeladditem_supplies(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		initThis();
	}
	
	String[] COLUMNS = new String[] {
			"Item ID",
			"Item Name.", 
			"Category Name",
			"Category ID"
	};
	
	Class[] CLASS_TYPES = new Class[] {
			Integer.class,	//Item ID
			String.class, 	//Item Name
			String.class, 	//Category Name
			Integer.class, 	//Category ID
			
	};
	
	private void initThis() {
		setColumnIdentifiers(COLUMNS);
	}
	
	Boolean[] COLUMNS_EDITABLE = new Boolean[] {
			false, //0 Item Name
			false, //1 Item Name
			false, //2 Category Name
			false, //3 Category ID
			
	};
	
	
	public Class getColumnClass(int columnIndex) {
		return CLASS_TYPES[columnIndex];
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return COLUMNS_EDITABLE[columnIndex];
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
			COLUMNS_EDITABLE = new Boolean[]{
					false, //1 Item Name
					false, //2 Item Name
					false, //3 Category Name
					false, //4 Category ID
			};
		}else{
			COLUMNS_EDITABLE = new Boolean[]{
					false, //1 Item Name
					false, //2 Item Name
					false, //3 Category Name
					false, //4 Category ID
					
			};
		}
	}
}
