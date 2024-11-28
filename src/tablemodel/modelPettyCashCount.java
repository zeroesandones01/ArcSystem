package tablemodel;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class modelPettyCashCount extends DefaultTableModel {
	
	private static final long serialVersionUID = -8738810102880371204L;
	
	private boolean editable = false;
	boolean[] COLUMN_EDITABLE;
	
	
	public modelPettyCashCount() {
		initThis();
	}

	public modelPettyCashCount(int rowCount, int columnCount) {
		super(rowCount, columnCount);
	}

	public modelPettyCashCount(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public modelPettyCashCount(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public modelPettyCashCount(Vector data, Vector columnNames) {
		super(data, columnNames);
	}

	public modelPettyCashCount(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	public String[] COLUMNS = new String[] {
			"Denomination",		// 1
			"No. of Pieces",	// 2
			"Amount"			// 3
			};

	public Class[] CLASS_TYPES = new Class[] {
			BigDecimal.class, 	//Denomination
			Integer.class, 		//No. of Pieces
			BigDecimal.class	//Amount
	};

	private void initThis() {
		setColumnIdentifiers(COLUMNS);
		COLUMN_EDITABLE = new boolean[] {
				false, 		//Denomination
				false, 		//Count
				false		//Amount
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
					false, 		//Denomination
					true, 		//Count
					false		//Amount
			};
		}else{
			COLUMN_EDITABLE = new boolean[] {
					false, 		//Denomination
					false, 		//Count
					false		//Amount
			};
		}
	}
	    
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    if (columnIndex == 1) { // Count column (No. of Pieces)
	        try {
	            // Validate and parse the input value
	            int count = (aValue instanceof Integer) ? (Integer) aValue : Integer.parseInt(aValue.toString());

	            // Update the Count column
	            super.setValueAt(count, rowIndex, columnIndex);

	            // Get the Denomination value
	            BigDecimal denomination = (BigDecimal) getValueAt(rowIndex, 0);

	            // Calculate the Amount and update the Amount column
	            BigDecimal amount = denomination.multiply(BigDecimal.valueOf(count));
	            super.setValueAt(amount, rowIndex, 2);

	        } catch (NumberFormatException e) {
	            // Handle invalid input gracefully
	            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer for the count.", 
	                                          "Input Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        // For other columns, update as usual
	        super.setValueAt(aValue, rowIndex, columnIndex);
	    }
	}


}

