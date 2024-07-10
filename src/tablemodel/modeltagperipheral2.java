package tablemodel;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Lookup._JLookup;

public class modeltagperipheral2	 extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private boolean editable = false;
	boolean[] COLUMN_EDITABLE;
	
	
	public modeltagperipheral2() {
		initThis();
	}

	public modeltagperipheral2(int rowCount, int columnCount) {
		super(rowCount, columnCount);
	}

	public modeltagperipheral2(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public modeltagperipheral2(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public modeltagperipheral2(Vector data, Vector columnNames) {
		super(data, columnNames);
	}

	public modeltagperipheral2(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	public String[] COLUMNS = new String[] {
			"Selected",
			"Asset No.", 
			"Peripheral_ID",
			"Peripheral Name",
			"Custodian",
			"Amount",
			"Brand",
			"Model",
			"Description",
			"Serial",
			"License_Key",
			"Status_ID",
			"Prev Custodian",
			"Date Retired",
			"Date Diposed"
			
			};

	
	/**
	 * String.class = Alignment.CENTER
	 * Object.class = Alignment.LEFT
	 * BigDecimal.class = Alignment.RIGHT
	 * Integer.class = Alignment.CENTER
	 * Timestamp.class
	 */
	public Class[] CLASS_TYPES = new Class[] {
			Boolean.class,		//checkbox
			String.class, 		//Asset No.
			String.class, 		//Peripheral_Id
			_JLookup.class, 	//Peripheral Name
			_JLookup.class, 	//Custodian
			BigDecimal.class,	//Amount
			String.class,		//Brand
			String.class,		//Model
			String.class,		//Description
			String.class,		//Serial
			String.class,		//License_Key
			String.class,		//Status_ID
			_JLookup.class,		//Prev Custodian
			Timestamp.class,	//Date Retired
			Timestamp.class,	//Date Diposed
	};

	private void initThis() {
		setColumnIdentifiers(COLUMNS);
		COLUMN_EDITABLE = new boolean[] {
				false, //0 checkbox
				false, //1 Asset No.
				false, //2 Peripheral_Id
				false, //3 Peripheral Name
				false, //4 Custodian
				false, //5 Amount
				false, //6 Brand
				false, //7 Model
				false, //8 Description
				false, //9 Serial
				false, //10 License_Key
				false, //11 Status_ID
				false, //12 Prev Custodian
				false, //13 Date Retired
				false, //14 Date Diposed
		};
	}

	public Class getColumnClass(int columnIndex) {
		return CLASS_TYPES[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return COLUMN_EDITABLE[columnIndex];
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
		if(editable){
			COLUMN_EDITABLE = new boolean[] {
					true, //0 checkbox
					false, //1 Asset No.
					false, //2 Peripheral_Id
					false, //3 Peripheral Name
					false, //4 Custodian
					false, //5 Amount
					false, //6 Brand
					false, //7 Model
					false, //8 Description
					false, //9 Serial
					false, //10 License_Key
					false, //11 Status_ID
					false, //12 Prev Custodian
					false, //13 Date Retired
					false, //14 Date Diposed
			};
		}else{
			COLUMN_EDITABLE = new boolean[] {
					false, //0 checkbox
					false, //1 Asset No.
					false, //2 Peripheral_Id
					false, //3 Peripheral Name
					false, //4 Custodian
					false, //5 Amount
					false, //6 Brand
					false, //7 Model
					false, //8 Description
					false, //9 Serial
					false, //10 License_Key
					false, //11 Status_ID
					false, //12 Prev Custodian
					false, //13 Date Retired
					false, //14 Date Diposed
			};
		}
	}
}