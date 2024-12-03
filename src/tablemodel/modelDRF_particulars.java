package tablemodel;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

import DateChooser._JDateChooser;
import Lookup._JLookup;

public class modelDRF_particulars extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8738810102880371204L;
	
	private boolean editable = false;
	boolean[] COLUMN_EDITABLE;
	
	
	public modelDRF_particulars() {
		initThis();
	}

	public modelDRF_particulars(int rowCount, int columnCount) {
		super(rowCount, columnCount);
	}

	public modelDRF_particulars(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public modelDRF_particulars(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public modelDRF_particulars(Vector data, Vector columnNames) {
		super(data, columnNames);
	}

	public modelDRF_particulars(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	public String[] COLUMNS = new String[] {
			"Account ID",		// 0
			"<html><center>Account<html><br><html><center>Description<html>", // 1
			"Proj. Cost ID",	// 2
			"<html><center>Project Cost<html><br><html><center>Description<html>", // 3
			"Div",				// 4	
			"Proj",				// 5			
			"  Amount  ",		// 6	
			"Within", 			// 7
			"Payee ID",			// 8			
			"Payee Type",		// 9
			"Payee Name",		// 10	
			"Ref ID", 			// 11
			"Ref No", 			// 12 
			"Ref Date", 		// 13
			"Item ID", 			// 14
			"Item Description", // 15
			"Invoice No.",		// 16
			"Invoice Date", 	// 17
			"SOA/Bill No.",		// 18
			"SOA/Bill Date", 	// 19
			"Asset No.",		// 20	
			"Vatable Project", 	// 21
			"<html><center>Vatable<html><br><html><center>Entity<html>",  // 22	
			"Taxable", 			// 23
			"Gross Amt.",		// 24
			"Net Amt",			// 25
			"VAT Rate (%)",		// 26
			"VAT Amount",		// 27
			"WTax ID",			// 28			
			"WTax Rate (%)",	// 29			
			"WTax Amount",		// 30
			"<html><center>Expense<html><br><html><center>Amount<html>",  	// 31
			"<html><center>Retention<html><br><html><center>Amount<html>",  // 32			
			"<html><center>DP Recoup.<html><br><html><center>Amount<html>", // 33					
			"<html><center>BC Liquidation<html><br><html><center>Amount<html>", // 34		
			"<html><center>Other Liqui.<html><br><html><center>Amount<html>",  	// 35
			"<html><center>Payable<html><br><html><center>Amount<html>",  	// 36
			"Rec ID" //37
			};

	public Class[] CLASS_TYPES = new Class[] {
			
			_JLookup.class,		// 0 Account ID
			String.class,		// 1 Account Desc	
			_JLookup.class,		// 2 Proj. Cost ID
			String.class,		// 3 Proj. Cost Desc		
			String.class,		// 4 Div		
			_JLookup.class,		// 5 Proj
			BigDecimal.class,	// 6 Amount	
			Boolean.class, 		// 7 Within
			_JLookup.class,		// 8 Payee ID
			_JLookup.class,		// 9 Payee Type
			String.class,		// 10 Payee Name
			String.class,		// 11 Ref ID
			String.class,		// 12 Ref No
			_JDateChooser.class,// 13 Ref Date 
			String.class,		// 14 Item ID
			String.class,		// 15 Item Description
			String.class, 		// 16 Invoice No.
			_JDateChooser.class, // 17 Invoice Date
			String.class, 		// 18 SOA/Bill No.
			_JDateChooser.class, // 19 SOA/Bill Date
			String.class,		// 20 Asset No.	
			Boolean.class,		// 21 Vatable Project
			Boolean.class,		// 22 Vatable Entity
			Boolean.class,		// 23 Taxable
			BigDecimal.class,	// 24 Gross Amt.
			BigDecimal.class,	// 25 Net Amt
			BigDecimal.class,	// 26 VAT Rate (%)
			BigDecimal.class,	// 27 VAT Amount
			_JLookup.class,		// 28 WTax ID			
			BigDecimal.class,	// 29 WTax Rate (%)
			BigDecimal.class,	// 30 WTax Amount
			BigDecimal.class,	// 31 Expense Amount
			BigDecimal.class,	// 32 Retention Amount			
			BigDecimal.class,	// 33 DP Recoup Amount
			BigDecimal.class,	// 34 BC Liqui. Amount
			BigDecimal.class,	// 35 Other Liqui. Amount
			BigDecimal.class,	// 36 Payable Amount
			String.class		// 37 Rec ID
			
	};

	private void initThis() {
		setColumnIdentifiers(COLUMNS);
		COLUMN_EDITABLE = new boolean[] {
			
				false,	// 0 Account ID
				false, 	// 1 Account Desc
				false,	// 2 Proj. Cost ID
				false, 	// 3 Proj. Cost Desc
				false,	// 4 Div							
				false,	// 5 Proj
				false,	// 6 Amount	
				false,	// 7 Within
				false,	// 8 Payee ID
				false,	// 9 Payee Type			
				false,	// 10 Payee Name
				false,	// 11 Ref ID*
				false,	// 12 Ref No*
				false,	// 13 Ref Date*
				false,	// 14 Item ID*
				false,	// 15 Item Description*
				true, 	// 16 Invoice No.
				true,	// 17 Invoice Date
				true, 	// 18 SOA/Bill No.
				true, 	// 19 SOA/Bill Date
				false,	// 20 Asset No.
				false,	// 21 Vatable Project* 			
				true,	// 22 Vatable Entity
				true,	// 23 Taxable
				false,	// 24 Gross Amt.
				false, 	// 25 Net Amt. 
				false,	// 26 VAT Rate (%)
				false,	// 27 VAT Amount
				false,	// 28 WTax ID				
				false,	// 29 WTax Rate (%)
				false,	// 30 WTax Amount
				false,	// 31 Expense Amount
				false,	// 32 Retention Amount				
				false,	// 33 DP Recoup Amount
				false,	// 34 BC Liqui Amount
				false,	// 35 Other Liqui Amount
				false, 	// 36 Payable Amount
				false 	// 37 Rec ID
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
					
					false,	// 0 Account ID
					false, 	// 1 Account Desc
					false,	// 2 Proj. Cost ID
					false, 	// 3 Proj. Cost Desc
					false,	// 4 Div
					false,	// 5 Proj
					true,	// 6 Amount
					false,	// 7 Within
					false,	// 8 Payee ID
					false,	// 9 Payee Type			
					false,	// 10 Payee Name
					true,	// 11 Ref ID*
					true,	// 12 Ref No*
					true,	// 13 Ref Date*
					true,	// 14 Item ID*
					true,	// 15 Item Description*
					false, 	// 16 Invoice No.
					false,	// 17 Invoice Date
					false, 	// 18 SOA/Bill No.
					false, 	// 19 SOA/Bill Date
					true,	// 20 Asset No.	
					true,	// 21 Vatable Project*
					true,	// 22 Vatable Entity
					true,	// 23 Taxable
					false,	// 24 Gross Amt.
					false, 	// 25 Net Amt. 
					false,	// 26 VAT Rate (%)
					false,	// 27 VAT Amount
					false,	// 28 WTax ID				
					false,	// 29 WTax Rate (%)
					false,	// 30 WTax Amount
					false,	// 31 Expense Amount
					false,	// 32 Retention Amount				
					false,	// 33 DP Recoup Amount
					false,	// 34 BC Liqui Amount
					false,	// 35 Other Liqui Amount
					false,	// 36 Payable Amount
					false 	// 37 Rec ID
			};
		}else{
			COLUMN_EDITABLE = new boolean[] {
					false,	// Account ID
					false, 	// Account Desc
					false,	// Proj. Cost ID
					false, 	// Proj. Cost Desc
					false,	// Div							
					false,	// Proj
					false,	// Amount	
					false,	// Within
					false,	// Payee ID
					false,	// Payee Type			
					false,	// Payee Name
					false,	// Ref ID*
					false,	// Ref No*
					false,	// Ref Date*
					false,	// Item ID*
					false, 	// Invoice No.
					false,	// Invoice Date
					false, 	// SOA/Bill No.
					false, 	// SOA/Bill Date
					false,	// Asset No.	
					false,	// Vatable Project*
					false,	// Vatable Entity
					false,	// Taxable
					false,	// Gross Amt.
					false, 	// Net Amt. 
					false,	// VAT Rate (%)
					false,	// VAT Amount
					false,	// WTax ID				
					false,	// WTax Rate (%)
					false,	// WTax Amount
					false,	// Expense Amount
					false,	// Retention Amount				
					false,	// DP Recoup Amount
					false,	// BC Liqui Amount
					false,	// Other Liqui Amount
					false,	// Payable Amount
					false 	// Rec ID
			};
		}
		
	}
	
	@Override
	public Object getValueAt(int row, int column) {
	    Object value = super.getValueAt(row, column);
	    
	    // Ensure that BigDecimal columns return as BigDecimal
	    if (column == 6 || column == 24 || column == 25 || column == 26 || column == 27 || column == 28 ||
	    	column == 29 ||column == 30 ||column == 31 || column == 32 || column == 33 || column == 34 ||
	        column == 35 || column == 36) { // Adjust indices as needed
	        if (value instanceof Number) {
	            return BigDecimal.valueOf(((Number) value).doubleValue());
	        } else if (value instanceof Long) {
	        	 return BigDecimal.valueOf(((Long) value).doubleValue());
			}
	     
	    // Converting null cells into empty string
	    } else if (column == 0 || column == 2 || column == 5) {
	    	return value == null ? "" : value; 
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
	
	public void setEditableForInvoice(boolean editable) {
		
	    COLUMN_EDITABLE[16] = editable;  // Invoice No.
	    COLUMN_EDITABLE[17] = editable;  // Invoice Date
	    
	    fireTableDataChanged();  // Trigger to update the table and and reflect the changes made
	}
	
	public void setEditableForBilling(boolean editable) {

	    COLUMN_EDITABLE[18] = editable;  // SOA/Bill No.
	    COLUMN_EDITABLE[19] = editable;  // SOA/Bill Date
	    
	    fireTableDataChanged();  //  Trigger to update the table and and reflect the changes made
	}
	
    // Method to count non-empty "Acct ID" rows (column 0)
    public int countRowsWithNonEmptyAcctID() {
        int count = 0;
        for (int i = 0; i < getRowCount(); i++) {
            Object value = getValueAt(i, 0); // Acct ID
            if (value != null && !value.toString().trim().isEmpty()) {
                count++;
            }
        }
        return count;
    }
	
}