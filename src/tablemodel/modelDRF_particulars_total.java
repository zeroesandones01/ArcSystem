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
			"Account Desc", 	// 1
			"Cost ID",			// 2
			"Cost Desc", 		// 3
			"Div",				// 4
			"Proj",				// 5
			"Amount",			// 6
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
			"Asset No.", 		// 20
			"Vatable Project",	// 21
			"Vatable Entity",	// 22
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
			"<html><center>Payable<html><br><html><center>Amount<html>",  	// 34
			"<html><center>BC Liquidation<html><br><html><center>Amount<html>", // 35		
			"<html><center>Other Liqui.<html><br><html><center>Amount<html>",  	// 36
			"Rec ID" //37
			};


	public Class[] CLASS_TYPES = new Class[] {
		
			String.class,		// 0 Account ID
			Object.class, 		// 1 Account Desc
			String.class,		// 2 Cost ID
			Object.class, 		// 3 Cost Desc
			String.class,		// 4 Div
			String.class,		// 5 Proj
			BigDecimal.class,	// 6 Amount
			String.class,		// 7 Within
			String.class,		// 8 Payee ID
			String.class,		// 9 Payee Type	
			String.class,		// 10 Payee Name
			String.class, 		// 11 Ref ID
			String.class, 		// 12 Ref No
			Timestamp.class, 	// 13 Ref Date
			String.class, 		// 14 Item ID
			String.class, 		// 15 Item Description
			String.class, 		// 16 Invoice No.
			Timestamp.class, 	// 17 Invoice Date
			String.class, 		// 18 SOA/Bill No.
			Timestamp.class, 	// 19 SOA/Bill Date	
			String.class,		// 20 Asset No.
			String.class, 		// 21 Vatable Project
			String.class,		// 22 Vatable Entity
			String.class,		// 23 Taxable
			BigDecimal.class,	// 24 Gross Amt.
			BigDecimal.class,	// 25 Net Amt.
			BigDecimal.class,	// 26 VAT Rate (%)
			BigDecimal.class,	// 27 VAT Amount
			String.class,		// 28 WTax ID
			BigDecimal.class,	// 29 WTax Rate (%)
			BigDecimal.class,	// 30 WTax Amount
			BigDecimal.class,	// 31 Expense Amount
			BigDecimal.class,	// 32 Retention Amount
			BigDecimal.class,	// 33 DP Recoup Amount
			BigDecimal.class,	// 34 BC Liqui. Amount
			BigDecimal.class,	// 35 Other Liqui. Amount
			BigDecimal.class,	// 36 Payable Amount
			String.class 		// 37 Rec ID
			
	};

	private void initThis() {
		setColumnIdentifiers(COLUMNS);
		COLUMN_EDITABLE = new boolean[] {
				false,	// 0 Account ID
				false, 	// 1 Account Desc
				false,	// 2 Cost ID
				false, 	// 3 Account Desc
				false,	// 4 Div							
				false,	// 5 Proj
				false,	// 6 Amount	
				false,	// 7 Within
				false,	// 8 Payee ID
				false,	// 9 Payee Type			
				false,	// 10 Payee Name
				false, 	// 11 Ref ID
				false, 	// 12 Ref No
				false, 	// 13 Ref Date
				false, 	// 14 Item ID
				false, 	// 15 Item Description
				true, 	// 16 Invoice No.
				true,	// 17 Invoice Date
				true, 	// 18 SOA/Bill No.
				true, 	// 19 SOA/Bill Date
				false,	// 20 Asset No.	
				false, 	// 21 Vatable Project	
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
				false	// 37 Rec ID
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
					false, 	// Account Desc
					false,	// Cost ID
					false, 	// Cost Desc
					false,	// Div
					false,	// Proj
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
					false, 	// Account Desc
					false,	// Cost ID
					false, 	// Cost Desc
					false,	// Div							
					false,	// Proj
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
