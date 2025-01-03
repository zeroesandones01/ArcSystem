package tablemodel;

import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Functions.FncTables;

public class modelAssetMonitoring extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	private boolean editable = false;
	//boolean[] COLUMN_EDITABLE;

	public modelAssetMonitoring() {
		initThis();
	}

	public modelAssetMonitoring(boolean editable) {
		initThis();
	}

	public modelAssetMonitoring(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		initThis();
	}

	public modelAssetMonitoring(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
		initThis();
	}

	public modelAssetMonitoring(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		initThis();
	}

	public modelAssetMonitoring(Vector data, Vector columnNames) {
		super(data, columnNames);
		initThis();
	}

	public modelAssetMonitoring(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		initThis();
	}
	
	String[] COLUMNS = new String[] {
			"Selected",
			"Asset No.", 
			"Asset Name",
			"Date Acquired",
			"Custodian ID",
			"Custodian",
			"Reference No.",
			"Status",
			"Rec ID"
	};
	
	Class[] CLASS_TYPES = new Class[] {
			Boolean.class,//checkbox
			String.class, //Asset No.
			Object.class, //Asset Name
			Timestamp.class, //Date Acquired
			String.class, //Custodian ID
			Object.class, //Custodian
			Object.class, //Reference No.
			String.class, //status
			String.class, //rec_id
	};
	
	private void initThis() {
		setColumnIdentifiers(COLUMNS);
	}
	
	Boolean[] COLUMNS_EDITABLE = new Boolean[] {
			false, //0
			false, //1
			false, //3
			false, //4
			false, //5
			false, //6
			false, //7
			false, //8
			false, //9
			
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
					false, //3
					false, //4
					false, //5
					false, //6
					false, //7
					false, //8
					false, //9
			};
		}else{
			COLUMNS_EDITABLE = new Boolean[]{
					false, //0
					false, //1
					false, //3
					false, //4
					false, //5
					false, //6
					false, //7
					false, //8
					false, //9
			};
	}
		
	}
}
