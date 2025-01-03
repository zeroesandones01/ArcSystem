
 package tablemodel;
 
import java.math.BigDecimal;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Functions.FncTables;
import Lookup._JLookup;

public class modelpurchase_order extends DefaultTableModel {
private static final long serialVersionUID = 1L;
	
	private boolean editable = false;
	//boolean[] COLUMN_EDITABLE;

	public modelpurchase_order() {
		initThis();
	}

	public modelpurchase_order(boolean editable) {
		initThis();
	}

	public modelpurchase_order(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		initThis();
	}

	public modelpurchase_order(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
		initThis();
	}

	public modelpurchase_order(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		initThis();
	}

	public modelpurchase_order(Vector data, Vector columnNames) {
		super(data, columnNames);
		initThis();
	}

	public modelpurchase_order(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		initThis();
	}
	
	String[] COLUMNS = new String[] {
			"Selected",
			"SUPPLIER NAME",
			"ITEM ID",
			"DESCRIPTION", 
			"MODEL",
			"BRAND",
			"UNIT",
			"QTY",
			"PURPOSE",
			"UNIT PRICE",
			"SUPPLIER ID",
			"REC ID"
			
	};
	
	Class[] CLASS_TYPES = new Class[] {
			
			Boolean.class, //Selected
			_JLookup.class,  //Supplier name
			_JLookup.class, //ITEM ID
			String.class, //DESCRIPTION
			String.class, //MODEL
			String.class, //BRAND
			String.class, //UNIT
			Integer.class, //QTY
			String.class, //PURPOSE
			Double.class, //UNIT PRICE
			String.class, //Supplier ID
			Integer.class, //Rec ID
	};
	
	private void initThis() {
		setColumnIdentifiers(COLUMNS);
	}
	
	Boolean[] COLUMNS_EDITABLE = new Boolean[] {
			false, //0
			false, //1
			false, //2
			false, //3
			false, //4
			false, //5
			false, //6
			false, //7
			false, //8
			false, //9
			false, //10
			false, //11
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
					true, //0
					false, //1
					false, //2
					false, //3
					true, //4
					true, //5
					false, //6
					true, //7
					true, //8
					true, //9
					false, //10
					false, //11
			};
		}else{
			COLUMNS_EDITABLE = new Boolean[]{
					false, //0
					false, //1
					false, //2
					false, //3
					false, //4
					false, //5
					false, //6
					false, //7
					false, //8
					false, //9
					false, //10
					false, //11
			};
	}
		
	}
}
