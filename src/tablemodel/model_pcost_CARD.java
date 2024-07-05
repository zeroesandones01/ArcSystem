package tablemodel;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Functions.FncTables;

public class model_pcost_CARD extends DefaultTableModel {

	public model_pcost_CARD() {
		initThis();
	}

	public model_pcost_CARD(int rowCount, int columnCount) {
		super(rowCount, columnCount);
	}

	public model_pcost_CARD(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public model_pcost_CARD(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public model_pcost_CARD(Vector data, Vector columnNames) {
		super(data, columnNames);
	}

	public model_pcost_CARD(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}
	private void initThis() {
		setColumnIdentifiers(new String[] {
			"Tag", 			// 0	
			"Description", 	// 1
			"Trans Date", 	// 2
			"Amount", 		// 3
			"OR no", 		// 12
			"Year", 		//
			"Remarks", 		// 4
			"Doc No", 		// 5
			"RPLF No", 		// 6
			"JV No", 		// 7
			"Trans Type", 	// 8
			"DV No", 		//9
			"Date Paid", 	// 10
			"Batch no",		// 11
			"OR No. Reference"// 13
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
			Boolean.class,
			Object.class, 		//Description
			Timestamp.class, 	//Trans Date
			BigDecimal.class, 	//Amount
			String.class, 		//OR No
			String.class, 		//OR Year
			Object.class, 		//Remarks
			String.class, 		//Doc. No
			String.class, 		//RPLF No
			String.class, 		//JV No
			String.class, 		//Trans Type
			String.class, 		//DV No
			Timestamp.class, 	//Date Paid
			String.class, 		//Batch No
			String.class, 		//or_no_reference
			
	};
	Boolean[] COLUMN_EDITABLE = new Boolean[] {
			false,
			false, //Description
			false, //Trans Date
			false, //Amount
			false, //OR No
			false, //OR Year
			false, //Remarks
			false, //Doc. No
			false, //RPLF No
			false, //JV No
			false, //Trans Type
			false, //DV No
			false, //Date Paid
			false, //Batch No
			false //or_no_reference
			
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
