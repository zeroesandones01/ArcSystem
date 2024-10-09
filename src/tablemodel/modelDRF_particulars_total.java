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
			"Cost ID",			// 1
			"Div",				// 2
			"Proj",				// 3
			"Account Desc", 	// 4
			"Amount",			// 5
			"Within", 			// 6
			"Payee ID",			// 7
			"Payee Type",		// 8
			"Payee Name",		// 9
			"Ref ID", 			// 10
			"Ref No", 			// 11 
			"Ref Date", 		// 12
			"Item ID", 			// 13
			"Item Description", // 14
			"Invoice No.",		// 15
			"Invoice Date", 	// 16
			"SOA/Bill No.",		// 17
			"SOA/Bill Date", 	// 18
			"Asset No.", 		// 19
			"Vatable Project",	// 20
			"Vatable Entity",	// 21
			"Taxable", 			// 22
			"Gross Amt.",		// 23
			"Net Amt",			// 24
			"VAT Rate (%)",		// 25
			"VAT Amount",		// 26
			"WTax ID",			// 27
			"WTax Rate (%)",	// 28
			"WTax Amount",		// 29
			"<html><center>Expense<html><br><html><center>Amount<html>",  	// 30
			"<html><center>Retention<html><br><html><center>Amount<html>",  // 31			
			"<html><center>DP Recoup.<html><br><html><center>Amount<html>", // 32			
			"<html><center>Payable<html><br><html><center>Amount<html>",  	// 33
			"<html><center>BC Liquidation<html><br><html><center>Amount<html>", // 34		
			"<html><center>Other Liqui.<html><br><html><center>Amount<html>",  	// 35
			"Rec ID" //36
			};


	public Class[] CLASS_TYPES = new Class[] {
		
			String.class,		// 0 Account ID
			String.class,		// 1 Cost ID
			String.class,		// 2 Div
			String.class,		// 3 Proj
			Object.class, 		// 4 Account Desc
			BigDecimal.class,	// 5 Amount
			String.class,		// 6 Within
			String.class,		// 7 Payee ID
			String.class,		// 8 Payee Type	
			String.class,		// 9 Payee Name
			String.class, 		// 10 Ref ID
			String.class, 		// 11 Ref No
			Timestamp.class, 	// 12 Ref Date
			String.class, 		// 13 Item ID
			String.class, 		// 14 Item Description
			String.class, 		// 15 Invoice No.
			Timestamp.class, 	// 16 Invoice Date
			String.class, 		// 17 SOA/Bill No.
			Timestamp.class, 	// 18 SOA/Bill Date	
			String.class,		// 19 Asset No.
			String.class, 		// 20 Vatable Project
			String.class,		// 21 Vatable Entity
			String.class,		// 22 Taxable
			BigDecimal.class,	// 23 Gross Amt.
			BigDecimal.class,	// 24 Net Amt.
			BigDecimal.class,	// 25 VAT Rate (%)
			BigDecimal.class,	// 26 VAT Amount
			String.class,		// 27 WTax ID
			BigDecimal.class,	// 28 WTax Rate (%)
			BigDecimal.class,	// 29 WTax Amount
			BigDecimal.class,	// 30 Expense Amount
			BigDecimal.class,	// 31 Retention Amount
			BigDecimal.class,	// 32 DP Recoup Amount
			BigDecimal.class,	// 33 BC Liqui. Amount
			BigDecimal.class,	// 34 Other Liqui. Amount
			BigDecimal.class,	// 35 Payable Amount
			String.class 		// 36 Rec ID
			
	};

	private void initThis() {
		setColumnIdentifiers(COLUMNS);
		COLUMN_EDITABLE = new boolean[] {
				false,	// 0 Account ID
				false,	// 1 Cost ID
				false,	// 2 Div							
				false,	// 3 Proj
				false, 	// 4 Account Desc
				false,	// 5 Amount	
				false,	// 6 Within
				false,	// 7 Payee ID
				false,	// 8 Payee Type			
				false,	// 9 Payee Name
				false, 	// 10 Ref ID
				false, 	// 11 Ref No
				false, 	// 12 Ref Date
				false, 	// 13 Item ID
				false, 	// 14 Item Description
				true, 	// 15 Invoice No.
				true,	// 16 Invoice Date
				true, 	// 17 SOA/Bill No.
				true, 	// 18 SOA/Bill Date
				false,	// 19 Asset No.	
				false, 	// 20 Vatable Project	
				true,	// 21 Vatable Entity
				true,	// 22 Taxable
				false,	// 23 Gross Amt.
				false, 	// 24 Net Amt. 
				false,	// 25 VAT Rate (%)
				false,	// 26 VAT Amount
				false,	// 27 WTax ID				
				false,	// 28 WTax Rate (%)
				false,	// 29 WTax Amount
				false,	// 30 Expense Amount
				false,	// 31 Retention Amount				
				false,	// 32 DP Recoup Amount
				false,	// 33 BC Liqui Amount
				false,	// 34 Other Liqui Amount
				false,	// 35 Payable Amount
				false	// 36 Rec ID
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
					false,	// Cost ID
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
					false,	// Payable Amount
					false	// Rec ID
			};
		}else{
			COLUMN_EDITABLE = new boolean[] {
					false,	// Account ID
					false,	// Cost ID
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
					false,	// Payable Amount
					false	// Rec ID
			};
		}
	}
	
}
