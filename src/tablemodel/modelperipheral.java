
package tablemodel;

import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Functions.FncTables;

public class modelperipheral extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	private boolean editable = false;

	public modelperipheral() {
		initThis();
	}

	public modelperipheral(boolean editable) {
		initThis();
	}

	public modelperipheral(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		initThis();
	}

	public modelperipheral(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
		initThis();
	}

	public modelperipheral(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		initThis();
	}

	public modelperipheral(Vector data, Vector columnNames) {
		super(data, columnNames);
		initThis();
	}

	public modelperipheral(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		initThis();
	}
	
	String[] COLUMNS = new String[] {
			"Selected",
			"Asset No.", 
			"Asset Name",
			"Custodian",
			"Date Acquired"
	};
	
	Class[] CLASS_TYPES = new Class[] {
			Boolean.class,	//checkbox
			String.class, 	//Asset No.
			Object.class, 	//Asset Name
			Object.class, 	//Custodian
			Timestamp.class,//Date Acquired
			
	};
	
	private void initThis() {
		setColumnIdentifiers(COLUMNS);
	}
	
	Boolean[] COLUMNS_EDITABLE = new Boolean[] {
			false, //0 checkbox
			false, //1 Asset No.
			false, //2 Asset Name
			false, //3 Custodian
			false, //4 /Date Acquired
			
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
					true,  //0 checkbox
					false, //1 Asset No.
					false, //2 Asset Name
					false, //3 Custodian
					false, //4 Date Acquired
			};
		}else{
			COLUMNS_EDITABLE = new Boolean[]{
					false,  //0 checkbox
					false, //1 Asset No.
					false, //2 Asset Name
					false, //3 Custodian
					false, //4 Date Acquired
					
			};
		}
	}
}
