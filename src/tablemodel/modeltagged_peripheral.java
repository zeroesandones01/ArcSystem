
package tablemodel;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Functions.FncTables;
import Lookup._JLookup;

public class modeltagged_peripheral extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	private boolean editable = false;

	public modeltagged_peripheral() {
		initThis();
	}

	public modeltagged_peripheral(boolean editable) {
		initThis();
	}

	public modeltagged_peripheral(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		initThis();
	}

	public modeltagged_peripheral(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
		initThis();
	}

	public modeltagged_peripheral(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		initThis();
	}

	public modeltagged_peripheral(Vector data, Vector columnNames) {
		super(data, columnNames);
		initThis();
	}

	public modeltagged_peripheral(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		initThis();
	}
	
	String[] COLUMNS = new String[] {
			"Selected",//0
			"Asset No.", //1
			"Peripheral ID",//2
			"Peripheral Name",//3
			"Custodian",//4
			"Amount",//5
			"Brand",//6
			"Model",//7
			"Description",//8
			"Serial",//9
			"License Key",//10
			"Status",//11
			"Prev Custodian",//12
			"Date Retired",//13
			"Date Disposed"//14
			
	};
	
	Class[] CLASS_TYPES = new Class[] {
			Boolean.class,	//checkbox
			String.class, 	//Asset No.
			Integer.class, 	//Peripheral ID
			Object.class, 	//Peripheral Name
			_JLookup.class,//"Custodian",
			BigDecimal.class,//"Amount",
			String.class,//"Brand",
			String.class,//"Model",
			String.class,//"Description",
			String.class,//"Serial",
			String.class,//"License Key",
			String.class,//"Status",
			_JLookup.class,//"Prev Custodian",
			Timestamp.class,//"Date Retired",
			Timestamp.class,//"Date Disposed"
			
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
			false,//"Status",
			false,//"Prev Custodian",
			false,//"Date Retired",
			false,//"Date Disposed"
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
					false,//"Status",
					false,//"Prev Custodian",
					false,//"Date Retired",
					false,//"Date Disposed"
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
					false,//"Status",
					false,//"Prev Custodian",
					false,//"Date Retired",
					false,//"Date Disposed"
					
			};
		}
	}
}

