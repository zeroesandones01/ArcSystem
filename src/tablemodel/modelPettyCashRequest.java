package tablemodel;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
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
			"Rec ID", 							// 0
			"Acct. ID", 						// 1
			"Acct. Description", 				// 2
			"Proj. ID", 						// 3
			"Proj.", 							// 4
			"Div.",								// 5
			"Nature / Purpose of Expenditure", 	// 6
			"Amount"							// 7
		};
			
	Class [] CLASS_TYPES = new Class []{
			Integer.class,	// Rec ID
			String.class, 	// Acct. ID
			_JLookup.class,	// Acct. Description
			String.class,	// Proj_ID
			_JLookup.class,	// Proj.
			String.class,	// Div.
			String.class,	// Nature / Purpose of Expenditure
			BigDecimal.class// Amount
			
	};
	
	Boolean [] COLUMNS_EDITABLE = new Boolean [] {
			false,
			false,
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
	    if (column == 7) {
	        if (value instanceof Number) {
	            return BigDecimal.valueOf(((Number) value).doubleValue());
	        } else if (value instanceof Long) {
	        	 return BigDecimal.valueOf(((Long) value).doubleValue());
			}
	    } else if (column == 0 && value instanceof String) {
	        try {
	            return Integer.valueOf((String) value); 
	        } catch (NumberFormatException e) {
	            // Return null if the string cannot be converted to an Integer
	            return null;
	        }
	    }
	    return value;
	}
	
	// Custom cell editor for BigDecimal (used for the "Amount" column)
		public class BigDecimalCellEditor extends DefaultCellEditor {
		     public BigDecimalCellEditor() {
		         super(new JTextField()); // Use JTextField as the editor
		         JTextField textField = (JTextField) getComponent();

		         // Add a focus listener to move the cursor to the end when editing starts
		         textField.addFocusListener(new FocusAdapter() {
		             @Override
		             public void focusGained(FocusEvent e) {
		                 // Get the current value of the cell being edited
		                 BigDecimal value = (BigDecimal) getCellEditorValue();

		                 // If the value is greater than 0, move the cursor to the end of the text
		                 if (value != null) {
		                	 if(value.compareTo(BigDecimal.ZERO) > 0) {
		                		 
		                		 textField.setSelectionStart(textField.getText().length());
		 	                     textField.setSelectionEnd(textField.getText().length());
		 	                     textField.setText(String.valueOf(value.stripTrailingZeros().toPlainString()));
		                	 } else {
		                		 textField.setText(null);
		                	 }
		                   
		                 }
		             }
		         });
		     }

		     @Override
		     public Object getCellEditorValue() {
		         JTextField textField = (JTextField) getComponent();
		         try {
		             // Try to convert the text field value back to BigDecimal
		             return new BigDecimal(textField.getText());
		         } catch (NumberFormatException ex) {
		             // In case of an invalid value, return BigDecimal.ZERO
		             return BigDecimal.ZERO;
		         }
		     }
		 }
}
