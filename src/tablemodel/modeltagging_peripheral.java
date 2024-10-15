

package tablemodel;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Functions.FncTables;
import Lookup._JLookup;

public class modeltagging_peripheral extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	private boolean editable = false;

	public modeltagging_peripheral() {
		initThis();
	}

	public modeltagging_peripheral(boolean editable) {
		initThis();
	}

	public modeltagging_peripheral(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		initThis();
	}

	public modeltagging_peripheral(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
		initThis();
	}

	public modeltagging_peripheral(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		initThis();
	}

	public modeltagging_peripheral(Vector data, Vector columnNames) {
		super(data, columnNames);
		initThis();
	}

	public modeltagging_peripheral(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		initThis();
	}
	
	String[] COLUMNS = new String[] {
			"Selected",
			"Asset No.", 
			"Peripheral ID",
			"Peripheral Name",
			"Custodian",
			"Amount",
			"Brand",
			"Model",
			"Description",
			"Serial",
			"License Key",
			"Supplier ID",
			"Status",
			"Prev Custodian",
			"Date Retired",
			"Date Disposed",
			"Supplier Name"
			
	};
	
	Class[] CLASS_TYPES = new Class[] {
			Boolean.class,	//checkbox
			String.class, 	//Asset No.
			Integer.class, 	//Peripheral ID
			Object.class, 	//Peripheral Name
			String.class,//"Custodian",
			Double.class,//"Amount",
			String.class,//"Brand",
			String.class,//"Model",
			String.class,//"Description",
			String.class,//"Serial",
			String.class,//"License Key",
			_JLookup.class,//Supplier
			String.class,//"Status",
			String.class,//"Prev Custodian",
			Date.class,//"Date Retired",
			Date.class,//"Date Disposed"
			String.class,//Supplier name
	};
	
	private void initThis() {
		setColumnIdentifiers(COLUMNS);
	}
	
	Boolean[] COLUMNS_EDITABLE = new Boolean[] {
			false,//"Selected",
			false,//"Asset No.", 
			false,//"Peripheral ID",
			false,//"Peripheral Name",
			false,//"Custodian",
			false,//"Amount",
			false,//"Brand",
			false,//"Model",
			false,//"Description",
			false,//"Serial",
			false,//"License Key",
			false,//Supplier
			false,//"Status",
			false,//"Prev Custodian",
			false,//"Date Retired",
			false,//"Date Disposed"
			false,//Supplier name
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
					true,//"Selected",
					false,//"Asset No.", 
					false,//"Peripheral ID",
					false,//"Peripheral Name",
					false,//"Custodian",
					true,//"Amount",
					true,//"Brand",
					true,//"Model",
					true,//"Description",
					true,//"Serial",
					true,//"License Key",
					false,//Supplier
					false,//"Status",
					false,//"Prev Custodian",
					false,//"Date Retired",
					false,//"Date Disposed"
					false,//Supplier name
			};
		}else{
			COLUMNS_EDITABLE = new Boolean[]{
					false,//"Selected",
					false,//"Asset No.", 
					false,//"Peripheral ID",
					false,//"Peripheral Name",
					false,//"Custodian",
					false,//"Amount",
					false,//"Brand",
					false,//"Model",
					false,//"Description",
					false,//"Serial",
					false,//"License Key",
					false,//Supplier
					false,//"Status",
					false,//"Prev Custodian",
					false,//"Date Retired",
					false,//"Date Disposed"
					false,//Supplier name
			};
		}
	}
}

