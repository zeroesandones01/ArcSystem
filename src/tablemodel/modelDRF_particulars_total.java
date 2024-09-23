package tablemodel;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import DateChooser._JDateChooser;

public class modelDRF_particulars_total extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8738810102880371204L;
	
	private boolean editable = false;
	boolean[] COLUMN_EDITABLE;
	
	
	public modelDRF_particulars_total() {
		initThis();
	}

	public modelDRF_particulars_total(int rowCount, int columnCount) {
		super(rowCount, columnCount);
	}

	public modelDRF_particulars_total(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public modelDRF_particulars_total(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public modelDRF_particulars_total(Vector data, Vector columnNames) {
		super(data, columnNames);
	}

	public modelDRF_particulars_total(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	public String[] COLUMNS = new String[] {
		
			"Account ID",		// 0
			"Div",				// 1
			"Proj",				// 2
			"Account Desc", 	// 3
			"Amount",			// 4
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
			"Asset No.", 		// 18
			"Vatable Project",	// 19
			"Vatable Entity",	// 20
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
			"<html><center>Payable<html><br><html><center>Amount<html>",  	// 32
			"<html><center>BC Liquidation<html><br><html><center>Amount<html>", // 33		
			"<html><center>Other Liqui.<html><br><html><center>Amount<html>"  	// 34
			};


	public Class[] CLASS_TYPES = new Class[] {
		
			String.class,		// 0 Account ID
			String.class,		// 1 Div
			String.class,		// 2 Proj
			Object.class, 		// 3 Account Desc
			BigDecimal.class,	// 4 Amount
			String.class,		// 5 Within
			String.class,		// 6 Payee ID
			String.class,		// 7 Payee Type	
			String.class,		// 8 Payee Name
			String.class, 		// 9 Ref ID
			String.class, 		// 10 Ref No
			Timestamp.class, 	// 11 Ref Date
			String.class, 		// 12 Item ID
			String.class, 		// 13 Item Description
			String.class, 		// 14 Invoice No.
			Timestamp.class, 	// 15 Invoice Date
			String.class, 		// 16 SOA/Bill No.
			Timestamp.class, 	// 17 SOA/Bill Date	
			String.class,		// 18 Asset No.
			String.class, 		// 19 Vatable Project
			String.class,		// 20 Vatable Entity
			String.class,		// 21 Taxable
			BigDecimal.class,	// 22 Gross Amt.
			BigDecimal.class,	// 23 Net Amt.
			BigDecimal.class,	// 24 VAT Rate (%)
			BigDecimal.class,	// 25 VAT Amount
			String.class,		// 26 WTax ID
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
				false,	// 5 Within
				false,	// 6 Payee ID
				false,	// 7 Payee Type			
				false,	// 8 Payee Name
				false, 	// 9 Ref ID
				false, 	// 10 Ref No
				false, 	// 11 Ref Date
				false, 	// 12 Item ID
				false, 	// 13 Item Description
				true, 	// 14 Invoice No.
				true,	// 15 Invoice Date
				true, 	// 16 SOA/Bill No.
				true, 	// 17 SOA/Bill Date
				false,	// 18 Asset No.	
				false, 	// 19 Vatable Project	
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
					false,	// Account ID
					false,	// Div
					false,	// Proj
					false, 	// Account Desc
					false,	// Amount
					false,	// Payee ID
					false,	// Payee Type			
					false,	// Payee Name
					false, 	// Ref ID
					false, 	// Ref No
					false, 	// Ref Date
					false, 	// Item ID
					false, 	// Item Description
					false, 	// Invoice No.
					false,	// Invoice Date
					false, 	// SOA/Bill No.
					false, 	// SOA/Bill Date
					false,	// Asset No.	
					false, 	// Vatable Project		
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
		}else{
			COLUMN_EDITABLE = new boolean[] {
					false,	// Account ID
					false,	// Div							
					false,	// Proj
					false, 	// Account Desc
					false,	// Amount		
					false,	// Payee ID
					false,	// Payee Type			
					false,	// Payee Name
					false, 	// Ref ID
					false, 	// Ref No
					false, 	// Ref Date
					false, 	// Item ID
					false, 	// Item Description
					false, 	// Invoice No.
					false,	// Invoice Date
					false, 	// SOA/Bill No.
					false, 	// SOA/Bill Date
					false,	// Asset No.
					false, 	// Vatable Project			
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
	
}
