package tablemodel;

import javax.swing.table.DefaultTableModel;

public class model_HeadDcrf_Checker extends DefaultTableModel {


	private static final long serialVersionUID = -7835384778059543340L;
	public model_HeadDcrf_Checker (){
		initthis();
		
	}
	
	String[] Columns = new String[]{
		"DCRF #",
		"DEPARTMENT",
		"REQUESTED BY",
		"STATUS"
	};
	Boolean[] COLUMNS_EDITABLE = new Boolean[]{
		false,
		false,
		false,
		false
	};
	Class[] classType = new Class[]{
			Integer.class,
			String.class,
			String.class,
			String.class
	};
	

	private void initthis() {
		setColumnIdentifiers(Columns);
	}
	public Class getColumnClass(int columnIndex){
		return classType[columnIndex];
		
	}
	public boolean isCellEditable(int rowIndex,int columnIndex){
		return COLUMNS_EDITABLE[columnIndex];
		
	}
	
	
	

	

}
