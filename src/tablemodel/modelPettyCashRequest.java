package tablemodel;

import java.math.BigDecimal;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Functions.FncTables;
import Lookup._JLookup;

public class modelPettyCashRequest extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	private boolean editable = false;

	public modelPettyCashRequest() {
		initThis();
	}
	
	public modelPettyCashRequest(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		// TODO Auto-generated constructor stub
	}

	public modelPettyCashRequest(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
		// TODO Auto-generated constructor stub
	}

	public modelPettyCashRequest(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		// TODO Auto-generated constructor stub
	}

	public modelPettyCashRequest(Vector data, Vector columnNames) {
		super(data, columnNames);
		// TODO Auto-generated constructor stub
	}

	public modelPettyCashRequest(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		// TODO Auto-generated constructor stub52
	}
	
	String [] COLUMNS = new String [] {
			"Rec ID", 
			"Acct. Description", 	
			"Proj.", 	
			"Div.",
			"Nature / Purpose of Expenditure", 
			"Amount"
		};
			
	Class [] CLASS_TYPES = new Class []{
			Integer.class,	// Rec ID
			_JLookup.class,	// Acct. Description
			_JLookup.class,	// Proj.
			String.class,	// Div.
			String.class,	// Nature / Purpose of Expenditure
			BigDecimal.class// Amount
			
	};
	
	Boolean [] COLUMNS_EDITABLE = new Boolean [] {
			false,
			false, 
			false,
			true, 
			true,
			true			
	};

	private void initThis(){
		setColumnIdentifiers(COLUMNS);
	}
	
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
	}
	
	@Override
	public Object getValueAt(int row, int column) {
	    Object value = super.getValueAt(row, column);
	    
	    // Ensure that BigDecimal columns return as BigDecimal
	    if (column == 5) {
	        if (value instanceof Number) {
	            return BigDecimal.valueOf(((Number) value).doubleValue());
	        } else if (value instanceof Long) {
	        	 return BigDecimal.valueOf(((Long) value).doubleValue());
			}
	    }
	    
	    return value;
	}
}
