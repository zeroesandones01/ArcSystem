package tablemodel;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

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
			"Div",				// 1	
			"Proj",				// 2			
			"<html><center>Account<html><br><html><center>Description<html>", // 3
			"  Amount  ",		// 4	
			"Within", 			// 5
			"Payee ID",			// 6			
			"Payee Type",		// 7
			"Payee Name",		// 8	
			"Ref ID", 			// 9
			"Ref No", 			// 10 
			"Ref Date", 		// 11
			"Item ID", 			// 12
			"Item Description", // 13
			"Invoice No.",		// 14
			"Invoice Date", 	// 15
			"SOA/Bill No.",		// 16
			"SOA/Bill Date", 	// 17
			"Asset No.",		// 18	
			"Vatable Project", 	// 19
			"<html><center>Vatable<html><br><html><center>Entity<html>",  // 20	
			"Taxable", 			// 21	
			"Gross Amt.",		// 22
			"Net Amt",			// 23
			"VAT Rate (%)",		// 24
			"VAT Amount",		// 25
			"WTax ID",			// 26			
			"WTax Rate (%)",	// 27			
			"WTax Amount",		// 28
			"<html><center>Expense<html><br><html><center>Amount<html>",  	// 29
			"<html><center>Retention<html><br><html><center>Amount<html>",  // 30			
			"<html><center>DP Recoup.<html><br><html><center>Amount<html>", // 31					
			"<html><center>BC Liquidation<html><br><html><center>Amount<html>", // 32		
			"<html><center>Other Liqui.<html><br><html><center>Amount<html>",  	// 33
			"<html><center>Payable<html><br><html><center>Amount<html>"  	// 34
			};

	public Class[] CLASS_TYPES = new Class[] {
			
			_JLookup.class,		// 0 Account ID
			String.class,		// 1 Div		
			_JLookup.class,		// 2 Proj
			Object.class, 		// 3 Account Desc
			BigDecimal.class,	// 4 Amount	
			Boolean.class, 		// 5 Within
			_JLookup.class,		// 6 Payee ID
			_JLookup.class,		// 7 Payee Type
			String.class,		// 8 Payee Name
			String.class,		// 9 Ref ID
			String.class,		// 10 Ref No
			_JDateChooser.class,// 11 Ref Date 
			String.class,		// 12 Item ID
			String.class,		// 13 Item Description
			String.class, 		// 14 Invoice No.
			_JDateChooser.class, // 15 Invoice Date
			String.class, 		// 16 SOA/Bill No.
			_JDateChooser.class, // 17 SOA/Bill Date
			String.class,		// 18 Asset No.	
			Boolean.class,		// 19 Vatable Project
			Boolean.class,		// 20 Vatable Entity
			Boolean.class,		// 21 Taxable
			BigDecimal.class,	// 22 Gross Amt.
			BigDecimal.class,	// 23 Net Amt
			BigDecimal.class,	// 24 VAT Rate (%)
			BigDecimal.class,	// 25 VAT Amount
			_JLookup.class,		// 26 WTax ID			
			BigDecimal.class,	// 27 WTax Rate (%)
			BigDecimal.class,	// 28 WTax Amount
			BigDecimal.class,	// 29 Expense Amount
			BigDecimal.class,	// 30 Retention Amount			
			BigDecimal.class,	// 31 DP Recoup Amount
			BigDecimal.class,	// 32 BC Liqui. Amount
			BigDecimal.class,	// 33 Other Liqui. Amount
			BigDecimal.class	// 34 Payable Amount
			
	};

	private void initThis() {
		setColumnIdentifiers(COLUMNS);
		COLUMN_EDITABLE = new boolean[] {
			
				false,	// 0 Account ID
				false,	// 1 Div							
				false,	// 2 Proj
				false, 	// 3 Account Desc
				false,	// 4 Amount	
				true,	// 5 Within
				false,	// 6 Payee ID
				false,	// 7 Payee Type			
				false,	// 8 Payee Name
				false,	// 9 Ref ID*
				false,	// 10 Ref No*
				false,	// 11 Ref Date*
				false,	// 12 Item ID*
				false,	// 13 Item Description*
				true, 	// 14 Invoice No.
				true,	// 15 Invoice Date
				true, 	// 16 SOA/Bill No.
				true, 	// 17 SOA/Bill Date
				false,	// 18 Asset No.
				false,	// 19 Vatable Project* 			
				true,	// 20 Vatable Entity
				true,	// 21 Taxable
				false,	// 22 Gross Amt.
				false, 	// 23 Net Amt. 
				false,	// 24 VAT Rate (%)
				false,	// 25 VAT Amount
				false,	// 26 WTax ID				
				false,	// 27 WTax Rate (%)
				false,	// 28 WTax Amount
				false,	// 29 Expense Amount
				false,	// 30 Retention Amount				
				false,	// 31 DP Recoup Amount
				false,	// 32 BC Liqui Amount
				false,	// 33 Other Liqui Amount
				false	// 34 Payable Amount
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
					true,	// 1 Div
					true,	// 2 Proj
					false, 	// 3 Account Desc
					true,	// 4 Amount
					true,	// 5 Within
					false,	// 6 Payee ID
					false,	// 7 Payee Type			
					false,	// 8 Payee Name
					true,	// 9 Ref ID*
					true,	// 10 Ref No*
					true,	// 11 Ref Date*
					true,	// 12 Item ID*
					true,	// 13 Item Description*
					true, 	// 14 Invoice No.
					true,	// 15 Invoice Date
					true, 	// 16 SOA/Bill No.
					true, 	// 17 SOA/Bill Date
					true,	// 18 Asset No.	
					true,	// 19 Vatable Project*
					true,	// 20 Vatable Entity
					true,	// 21 Taxable
					false,	// 22 Gross Amt.
					false, 	// 23 Net Amt. 
					false,	// 24 VAT Rate (%)
					false,	// 25 VAT Amount
					false,	// 26 WTax ID				
					false,	// 27 WTax Rate (%)
					false,	// 28 WTax Amount
					false,	// 29 Expense Amount
					false,	// 30 Retention Amount				
					false,	// 31 DP Recoup Amount
					false,	// 32 BC Liqui Amount
					false,	// 33 Other Liqui Amount
					false	// 34 Payable Amount
			};
		}else{
			COLUMN_EDITABLE = new boolean[] {
					false,	// Account ID
					false,	// Div							
					false,	// Proj
					false, 	// Account Desc
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
					false	// Payable Amount
			};
		}
	}
	
	@Override
	public Object getValueAt(int row, int column) {
	    Object value = super.getValueAt(row, column);
	    
	    // Ensure that BigDecimal columns return as BigDecimal
	    if (column == 4 || column == 22 || column == 23 || column == 24 || column == 25 ||
	    	column == 27 ||column == 28 ||column == 29 || column == 30 || column == 31 || column == 32 ||
	        column == 33 || column == 34) { // Adjust indices as needed
	        if (value instanceof Number) {
	            return BigDecimal.valueOf(((Number) value).doubleValue());
	        }
	    }
	    
//	    // Format date columns
//	    if (column == 15 || column == 17) {
//	    	if (value instanceof Timestamp) {
//	   	        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy"); // Adjust format as needed
//	   	        return sdf.format((Timestamp) value);
//	   	    } else if (value instanceof Date) {
//	   	     SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy"); // Adjust format as needed
//		        return sdf.format((Date) value);
//	   	    }
//	    }

	    return value;
	}


	
}