package tablemodel;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class model_officesupplies extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private boolean editable = false;
	boolean[] COLUMN_EDITABLE;
	
	
	public model_officesupplies() {
		initThis();
	}

	public model_officesupplies(int rowCount, int columnCount) {
		super(rowCount, columnCount);
	}

	public model_officesupplies(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public model_officesupplies(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public model_officesupplies(Vector data, Vector columnNames) {
		super(data, columnNames);
	}

	public model_officesupplies(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	public String[] COLUMNS = new String[] {
			"Select",			//1
			"ID",				//2
			"Description",		//3
			"Unit",				//4
			"Remaining",		//5
			"Quantity",			//6
			"Purpose"			//7
			
			};

	
	/**
	 * String.class = Alignment.CENTER
	 * Object.class = Alignment.LEFT
	 * BigDecimal.class = Alignment.RIGHT
	 * Integer.class = Alignment.CENTER
	 * Timestamp.class
	 */
	public Class[] CLASS_TYPES = new Class[] {
			Boolean.class, 		//Select
			Integer.class,		//ID
			String.class,		//Description
			String.class, 		//Unit
			Integer.class, 		//Remaining
			Integer.class,		//Quantity
			String.class		//Purpose
	};

	private void initThis() {
		setColumnIdentifiers(COLUMNS);
		COLUMN_EDITABLE = new boolean[] {
				false, 	//Select
				false,	//ID
				false, 	//Description
				false,	//Unit
				false, 	//Remaining
				false, 	//Quantity
				false	//Purpose
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
					true, 	//Select
					false,	//ID
					false, 	//Description
					false,	//Unit
					false, 	//Remaining
					true, 	//Quantity
					true	//Purpose
			};
		}else{
			COLUMN_EDITABLE = new boolean[] {
					false, 	//Select
					false,	//ID
					false, 	//Description
					false,	//Unit
					false, 	//Remaining
					false, 	//Quantity
					false	//Purpose
			};
		}
	}

}
