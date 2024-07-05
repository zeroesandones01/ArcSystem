package tablemodel;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Functions.FncTables;

public class modelBankAccounts extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8738810102880371204L;

	public modelBankAccounts() {
		initThis();
	}

	public modelBankAccounts(int rowCount, int columnCount) {
		super(rowCount, columnCount);
	}

	public modelBankAccounts(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public modelBankAccounts(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public modelBankAccounts(Vector data, Vector columnNames) {
		super(data, columnNames);
	}

	public modelBankAccounts(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	public String[] COLUMNS = new String[] {
			"Rec. ID", // 0
			"Bank ID", //1
			"Bank Name", //2
			"Branch ID", //3 
			"Branch Name", // 4
			"Endorsed", // 5
			"Account Type ID", //6
			"Account Type", // 7
			"Account No.", // 8
			"Date Opened", // 9
			"Average Balance", //10
			"Status", //11
			"RTD Rec. ID" //12
			
	};
	
	public Class[] CLASS_TYPES = new Class[] {
			Integer.class, //Rec ID
			String.class, //Bank ID
			String.class, //Bank Name
			String.class, //Branch ID
			String.class, //Branch Name
			Boolean.class, //Endorsed
			String.class, // Account Type ID
			String.class, //Account Type
			String.class, //Account No.
			Timestamp.class, //Date Opened
			BigDecimal.class, //Average Balance
			String.class, //Status
			Integer.class //RTD Rec. ID
			
	};

	private void initThis() {
		setColumnIdentifiers(COLUMNS);
	}

	public Class getColumnClass(int columnIndex) {
		return CLASS_TYPES[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	public void clear(){
		FncTables.clearTable(this);
	}

}
