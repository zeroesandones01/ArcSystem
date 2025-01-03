package Accounting.FixedAssets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Database.pgSelect;
import Database.pgUpdate;
import Functions.FncGlobal;
import Functions.FncSystem;
import Functions.FncTables;
import Functions.UserInfo;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import Lookup._JLookupTable;
import components.JTBorderFactory;
import components._JInternalFrame;
import components._JScrollPane;
import components._JTableMain;
import components._JTagLabel;
import interfaces._GUI;
import tablemodel.modelasset_peripheral;
import tablemodel.modeltagged_peripheral;
import tablemodel.modeltagging_peripheral;

public class addassetperipheral2 extends _JInternalFrame implements _GUI, ActionListener {

	private static final long serialVersionUID = 1L;
	static String title = "Add Asset Peripherals";
	public static Dimension frameSize = new Dimension(900, 700);
	private _JLookup lookupcomp;
	private _JTagLabel tagcomp;
	private JScrollPane scrollassetperipheral;
	private static modelasset_peripheral modelasset;
	private static _JTableMain tblasset;
	private static JList rowheaderassetperipheral;
	private JScrollPane scrolltagperipheral;
	private static modeltagging_peripheral modeltagging;
	private _JTableMain tbltagperipheral;
	private JList rowheadertagging;
	private JButton btnnew;
	private JButton btnsave;
	private JButton btnedit;
	private JButton btncancel;
	protected String co_name = "ACERLAND REALTY CORPORATION";
	private _JScrollPane scrolltagged_peripheral;
	private modeltagged_peripheral modeltagged;
	private _JTableMain tbltagged;
	private JList rowheadertagged;
	protected String co_id = "01";
	protected static ButtonGroup grpNE = new ButtonGroup();
	private String to_do = "";

	public addassetperipheral2() {
		super(title, true, true, true, true);
		initGUI();
		lookupcomp.setValue(co_id);
		tagcomp.setTag(co_name);
		modelasset.setEditable(true);
		tblasset.setEnabled(true);
		display_assets(co_id);
	}

	public addassetperipheral2(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public addassetperipheral2(String title, boolean resizable, boolean closable, boolean maximizable,
			boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initGUI() {
		this.setTitle(title);
		this.setSize(frameSize);
		this.setPreferredSize(frameSize);
		this.setLayout(new BorderLayout());
		{
			JPanel pnlmain = new JPanel(new BorderLayout(5, 5));
			getContentPane().add(pnlmain, BorderLayout.CENTER);
			pnlmain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			{
				JPanel pnlnorth = new JPanel(new BorderLayout(5, 5));
				pnlmain.add(pnlnorth,BorderLayout.NORTH);
				pnlnorth.setPreferredSize(new Dimension(0, 50));
				{
					JPanel pnlnorth_west = new JPanel(new GridLayout(2, 1, 3, 3));
					pnlnorth.add(pnlnorth_west, BorderLayout.WEST);
					{
						JLabel lblcomp = new JLabel("Company");
						pnlnorth_west.add(lblcomp);
					}
					pnlnorth_west.add(Box.createHorizontalBox());
				}
				{
					JPanel pnlnorth_center = new JPanel(new GridLayout(2, 1, 3, 3) );
					pnlnorth.add(pnlnorth_center, BorderLayout.CENTER);
					{
						lookupcomp = new _JLookup(null, "Company", 0);
						pnlnorth_center.add(lookupcomp);
						lookupcomp.setLookupSQL(getcompany());
						lookupcomp.addLookupListener(new LookupListener() {



							public void lookupPerformed(LookupEvent event) {
								Object[] data = ((_JLookup) event.getSource()).getDataSet();
								if(data != null) {
									co_id = (String)data[0];
									co_name = (String)data[1];
									
									tagcomp.setTag(co_name);
									lookupcomp.setValue(co_id);
									display_assets(lookupcomp.getValue());
									modelasset.setEditable(true);
									tblasset.setEnabled(true);
									buttonstate(false, false, false, true);
								}
							}
						});
					}
					pnlnorth_center.add(Box.createHorizontalBox());
				}
				{
					JPanel pnlnorth_east = new JPanel(new GridLayout(2, 1, 3, 3) );
					pnlnorth.add(pnlnorth_east, BorderLayout.EAST);
					pnlnorth_east.setPreferredSize(new Dimension(670, 0));
					{
						tagcomp = new _JTagLabel("[ ]");
						pnlnorth_east.add(tagcomp);
					}
					pnlnorth_east.add(Box.createHorizontalBox());
				}
			}
			{
				JPanel pnlcenter = new JPanel(new BorderLayout(5, 5));
				pnlmain.add(pnlcenter, BorderLayout.CENTER);
				pnlcenter.setBorder(JTBorderFactory.createTitleBorder(""));
				{
					JPanel pnltagging = new JPanel(new GridLayout(2, 1, 5, 5));
					pnlcenter.add(pnltagging, BorderLayout.CENTER);
					{
						JPanel pnlfortag = new JPanel(new BorderLayout());
						pnltagging.add(pnlfortag);
						pnlfortag.setBorder(JTBorderFactory.createTitleBorder("Select Asset"));
						{
							scrollassetperipheral = new JScrollPane();
							pnlfortag.add(scrollassetperipheral);
							scrollassetperipheral.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
							{
								modelasset = new modelasset_peripheral();
								tblasset = new _JTableMain(modelasset);
								modelasset.setEditable(false);
								tblasset.getTableHeader().setEnabled(false);
								scrollassetperipheral.setViewportView(tblasset);
								tblasset.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
								tblasset.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
									public void valueChanged(ListSelectionEvent e) {
										if(!e.getValueIsAdjusting()) {
											if(tblasset.getSelectedRows().length ==1){
												int row = tblasset.convertRowIndexToModel(tblasset.getSelectedRow());
												Integer asset_no = (Integer) modelasset.getValueAt(row, 0);
												System.out.println("Value of Asset No.: "+asset_no);
												displaytagged_peripherals(modeltagged, rowheadertagged, asset_no);
											}
												
												
										}
										buttonstate(true, false, true, true);
									}
								});
								
								//tblasset.getColumnModel().getColumn(0).setPreferredWidth(50);//Select
								tblasset.getColumnModel().getColumn(0).setPreferredWidth(150);//Asset No.
								tblasset.getColumnModel().getColumn(1).setPreferredWidth(250);//Asset Name
								tblasset.getColumnModel().getColumn(2).setPreferredWidth(350);//Custodian
								tblasset.getColumnModel().getColumn(3).setPreferredWidth(150);//Date Acquired
								
							}
							{
								rowheaderassetperipheral = tblasset.getRowHeader(); 
								scrollassetperipheral.setRowHeaderView(rowheaderassetperipheral);
								scrollassetperipheral.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
							}
						}
					}
					{
						JPanel pnltagged = new JPanel((new GridLayout(1, 2)));
						pnltagging.add(pnltagged);
						{
							JPanel pnlselect_peripheral = new JPanel(new BorderLayout(5, 5));
							pnltagged.add(pnlselect_peripheral);
							pnlselect_peripheral.setBorder(JTBorderFactory.createTitleBorder("Select Peripheral"));
							{
								scrolltagperipheral = new JScrollPane();
								pnlselect_peripheral.add(scrolltagperipheral);
								scrolltagperipheral.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
								{
									modeltagging = new modeltagging_peripheral();
									tbltagperipheral = new _JTableMain(modeltagging);
									modeltagging.setEditable(false);
									scrolltagperipheral.setViewportView(tbltagperipheral);
									tbltagperipheral.getColumnModel().getColumn(0).setPreferredWidth(50);
									tbltagperipheral.hideColumns("Asset No.", "Custodian", "Status", "Prev Custodian","Date Retired", "Date Disposed");
									tbltagperipheral.addMouseListener(new MouseAdapter() {
										@Override
										public void mousePressed(MouseEvent e) {
											if  ((e.getClickCount() >= 2)) {
												int column 	= tbltagperipheral.getSelectedColumn();
												if(column == 9) {setsupplier();}
											}
										}
										
										@Override
										public void mouseReleased(MouseEvent e) {
											if  ((e.getClickCount() >= 2)) {
												int column 	= tbltagperipheral.getSelectedColumn();
												System.out.println("column: "+column);
												if(column == 9) {setsupplier();}
											}
										}
									});
								}
								{
									rowheadertagging= tbltagperipheral.getRowHeader();
									scrolltagperipheral.setRowHeaderView(rowheadertagging);
									scrolltagperipheral.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
								}
							}
						}
						{
							JPanel pnlselected_peripheral = new JPanel(new BorderLayout(5, 5));
							pnltagged.add(pnlselected_peripheral);
							pnlselected_peripheral.setBorder(JTBorderFactory.createTitleBorder("Tagged Peripheral"));
							{
								scrolltagged_peripheral = new _JScrollPane();
								pnlselected_peripheral.add(scrolltagged_peripheral);
								scrolltagged_peripheral.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
								{
									modeltagged = new modeltagged_peripheral();
									tbltagged = new _JTableMain(modeltagged);
									modeltagged.setEditable(false);
									scrolltagged_peripheral.setViewportView(tbltagged);
								}
								{
									rowheadertagged = tbltagged.getRowHeader();
									scrolltagged_peripheral.setRowHeaderView(rowheadertagged);
									scrolltagged_peripheral.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
									
								}
							}
						}
					}
				}
			}	
			{
				JPanel pnlsouth = new JPanel(new GridLayout(1, 6, 5, 5));
				pnlmain.add(pnlsouth, BorderLayout.SOUTH);
				pnlsouth.setPreferredSize(new Dimension(0, 35));
				pnlsouth.add(Box.createHorizontalBox());
				{
					btnnew = new JButton("New"); 
					btnnew.setActionCommand("new");
					btnnew.setEnabled(false);
					grpNE.add(btnnew);
					pnlsouth.add(btnnew);
					btnnew.addActionListener(this);
				}
				{
					btnsave = new JButton("Save");
					btnsave.setActionCommand("save");
					btnsave.setEnabled(false);
					pnlsouth.add(btnsave);
					btnsave.addActionListener(this);
				}
				{
					btnedit = new JButton("Edit");
					btnedit.setActionCommand("edit");
					btnedit.setEnabled(false);
					grpNE.add(btnedit);
					pnlsouth.add(btnedit);
					btnedit.addActionListener(this);
				}
				{
					btncancel = new JButton("Cancel");
					btncancel.setActionCommand("cancel");
					btncancel.setEnabled(false);
					pnlsouth.add(btncancel);
					btncancel.addActionListener(this);
				}
				pnlsouth.add(Box.createHorizontalBox());
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		int row = tblasset.convertRowIndexToModel(tblasset.getSelectedRow());
		Integer asset_no = (Integer) modelasset.getValueAt(row, 0);
		
		if (e.getActionCommand().equals("new")) {
			
			display_peripherals(modeltagging, rowheadertagging, asset_no);
			modeltagging.setEditable(true);
			modelasset.setEditable(false);
			tblasset.setEnabled(false);
			buttonstate(false, true, false, true);
			to_do = "new";
			grpNE.setSelected(btnnew.getModel(), false);
			//System.out.println("button:  "+grpNE.getSelection().equals(btnnew.getModel()));
			
			
			
//			if(hasCheckedAssets()) {
//				
//				display_peripherals(modeltagging, rowheadertagging, asset_no);
//				modeltagging.setEditable(true);
//				modelasset.setEditable(false);
//				tblasset.setEnabled(false);
//				buttonstate(false, true, false, true);
//				
//			}else {
//				JOptionPane.showMessageDialog(this, "Please check the box of selected asset.", "", JOptionPane.PLAIN_MESSAGE);
//			}
		}
		
		if (e.getActionCommand().equals("save")) {
			
			System.out.println("row: "+row);
			System.out.println("asset_no: "+asset_no);
			
			System.out.println(""+grpNE.getSelection());
			if( to_do == "new") {
				if(checkdetails()) {
					if(JOptionPane.showConfirmDialog(this, "Are you sure you want to save new peripheral?", "Save", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						
						save_peripherals();
						displaytagged_peripherals(modeltagged, rowheadertagged, asset_no);
						FncTables.clearTable(modeltagging);
						modelasset.setEditable(true);
						tblasset.setEnabled(true);
						buttonstate(true, false, false, true);
						to_do = "";
						
						JOptionPane.showMessageDialog(this, "New peripheral is saved.", "", JOptionPane.PLAIN_MESSAGE);
					}
				}else {
					System.out.println("Check all details");
					JOptionPane.showMessageDialog(getTopLevelAncestor(), "Please check all details.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}else if(to_do == "edit") {
			
				System.out.println("Edit");
				if(JOptionPane.showConfirmDialog(this, "Are all entries correct?", "Save", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					pgUpdate db = new pgUpdate();
					updateperipheral(db);
					save_edit();
					db.commit();
					
					displaytagged_peripherals(modeltagged, rowheadertagged, asset_no);
					buttonstate(true, false, false, true);
					JOptionPane.showMessageDialog(this, "New peripheral is saved.", "", JOptionPane.PLAIN_MESSAGE);
					System.out.println("Save Edit Peripheral");
					to_do = "";
				}
			}
			
			
			/*if(grpNE.isSelected(btnnew.getModel())) {
				System.out.println("hasCheckedAssets_tagging(): "+hasCheckedAssets_tagging());
				if(hasCheckedAssets_tagging()) {
					
					if(checkdetails()) {
						if(JOptionPane.showConfirmDialog(this, "Are you sure you want to save new peripheral?", "Save", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							
							save_peripherals();
							displaytagged_peripherals(modeltagged, rowheadertagged, asset_no);
							FncTables.clearTable(modeltagging);
							modelasset.setEditable(true);
							tblasset.setEnabled(true);
							buttonstate(true, false, false, true);
							
							JOptionPane.showMessageDialog(this, "New peripheral is saved.", "", JOptionPane.PLAIN_MESSAGE);
						}
					}else {
						System.out.println("Check all details");
						JOptionPane.showMessageDialog(getTopLevelAncestor(), "Please check all details.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}else {
					JOptionPane.showMessageDialog(this, "Please check the box of the peripheral that you want to add.", "", JOptionPane.PLAIN_MESSAGE);
				}
			}else {
				
				if(JOptionPane.showConfirmDialog(this, "Are all entries correct?", "Save", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					pgUpdate db = new pgUpdate();
					updateperipheral(db);
					save_edit();
					db.commit();
					
					displaytagged_peripherals(modeltagged, rowheadertagged, asset_no);
					buttonstate(true, false, false, true);
					JOptionPane.showMessageDialog(this, "New peripheral is saved.", "", JOptionPane.PLAIN_MESSAGE);
					System.out.println("Save Edit Peripheral");
				}
			}*/
		}
		
		if(e.getActionCommand().equals("edit")) {
			to_do = "edit";
			
			modeltagged.setEditable(true);
			buttonstate(false, true, false, true);
			
		}
		
		if(e.getActionCommand().equals("cancel")) {
			if(JOptionPane.showConfirmDialog(this, "Are you sure to cancel unsave data?", "Cancel", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				cancelstate();
			}
		}
	}
	public void setsupplier () {
		int column = tbltagperipheral.getSelectedColumn();
		int row = tbltagperipheral.getSelectedRow();
		
		
		if (column == 9 ) {
			_JLookupTable dlg = new _JLookupTable(FncGlobal.homeMDI, null, "Select Supplier",getsupplier(), false);
			dlg.setLocationRelativeTo(FncGlobal.homeMDI);
			dlg.setVisible(true);
			
			Object[] data = dlg.getReturnDataSet();
			if (data != null) {
				
				modeltagging.setValueAt(data[0], row, 11);
				modeltagging.setValueAt(data[1], row, 16);
				
			}		
			tbltagperipheral.packAll();
		}
	}
	private boolean checkdetails() {
		
		for(int x = 0; x < modeltagging.getRowCount(); x++) {
			
			Boolean selected = (Boolean) modeltagging.getValueAt(x, 0);
			Double amount = Double.parseDouble( modeltagging.getValueAt(x, 5).toString());
			String brand = (String) modeltagging.getValueAt(x, 6);
			String model = (String) modeltagging.getValueAt(x, 7);
			String description = (String) modeltagging.getValueAt(x, 8);
			String serial_no = (String) modeltagging.getValueAt(x, 9);
			String lic_key = (String) modeltagging.getValueAt(x, 10);
			String supp_id = (String) modeltagging.getValueAt(x, 11);
			
			
			if(selected) {
				System.out.println("valueof amount: "+amount);
				System.out.println("brand: "+ brand);
				System.out.println("model: "+ model);
				System.out.println("description: "+ description);
				System.out.println("serial_no: "+ serial_no);
				System.out.println("lic_key: "+ lic_key);
				System.out.println("supp_id: "+ supp_id);
				
				if(amount == 0.00
					|| 	brand.equals("")
					||	model.equals("")
					||	description.equals("")
					||	serial_no.equals("")
					||	lic_key.equals("")
					||	supp_id.equals("")
				) {
					return false;
				}
			}
		}
		return true;
	}
	private String getsupplier() {
		String sql = " select  entity_id, entity_name from rf_entity where status_id = 'A' ";
		return sql;
	}
	private String getcompany() {
		String sql = "select co_id as \"Company ID\", \n"
				+ "company_name as \"Company Name\",\n"
				+ "company_address as \"Company Address\",\n"
				+ "company_logo as \"Company Logo\"\n"
				+ "from mf_company";
		return sql;
	}
	public void buttonstate(Boolean addnew, Boolean save,  Boolean edit,  Boolean cancel) {
		btnnew.setEnabled(addnew);
		btnsave.setEnabled(save);
		btnedit.setEnabled(edit);
		btncancel.setEnabled(cancel);
	}
	public  void display_assets(String co_id) {
		FncTables.clearTable(modelasset);
		DefaultListModel listModel = new DefaultListModel();// Creating listModel for rowHeader.
		rowheaderassetperipheral.setModel(listModel);// Setting of listModel into rowHeader.
		
		String strSQL = "select /*false,*/ a.asset_no, a.asset_name, c.entity_name, a.date_acquired \n"
				+ "from rf_asset a\n"
				+"left join rf_employee b on a.current_cust::varchar = b.emp_code \n"
				+"left join rf_entity c on b.entity_id = c.entity_id \n"
				+ "where a.status = 'A' \n"
				+ "and a.with_peripheral = true and a.co_id ='"+co_id+"' ";
		
		FncSystem.out("Display All Assets", strSQL);

		pgSelect db = new pgSelect();
		db.select(strSQL);

		if (db.isNotNull()) {
			for (int x = 0; x < db.getRowCount(); x++) {
				// You can only use this kind of adding row in model when you're query and model
				// has the same and exact unmber of columns and column types.
				modelasset.addRow(db.getResult()[x]);

				// For every row added in model, the table header will also add the row number.
				listModel.addElement(modelasset.getRowCount());
			
			}
		}
	}
	
	public void display_peripherals(DefaultTableModel modelMain, JList rowHeader, Integer asset_no) {
		
		FncTables.clearTable(modelMain);
		DefaultListModel listModel = new DefaultListModel();
		rowheadertagging.setModel(listModel);
		
		String sql = "select false, '', a.category_id, a.category_name, '', 0.00, '','','','','','','',null,null  \n"
				+ "from mf_asset_peripheral_category a where status_id = 'A'\n"+
				"and not exists(select asset_no from rf_asset_peripheral where asset_no = "+asset_no+" and category_id = a.category_id and status_id = 'A')";
		
		System.out.printf("display_peripherals: %s", sql);
		pgSelect db = new pgSelect();
		db.select(sql);
		
		if (db.isNotNull()) {
			for (int x = 0; x < db.getRowCount(); x++) {
				modeltagging.addRow(db.getResult()[x]);
				listModel.addElement(modeltagging.getRowCount());
			}
		}
	}
	
	public void displaytagged_peripherals(DefaultTableModel modelMain, JList rowHeader,Integer asset_no) {
		
		FncTables.clearTable(modelMain);
		DefaultListModel listModel = new DefaultListModel();
		rowHeader.setModel(listModel);
		
		String sql = "select false,a.asset_no, a.peripheral_id, b.category_name,d.entity_name,a.amount, a.brand, a.model, a.description,a.serial_no, a.license_key, a.supp_id, a.status_id, a.prev_cust, a.date_retired, a.date_disposed,e.entity_name,a.category_id  \n"
				+ "from rf_asset_peripheral a \n"
				+ "left join  mf_asset_peripheral_category b on a.category_id = b.category_id and a.status_id = b.status_id\n"
				+ "left join rf_employee c on a.current_cust = emp_code \n"
				+ "left join rf_entity d on c.entity_id = d.entity_id \n"
				+ "left join rf_entity e on a.supp_id = e.entity_id \n"
				+ "where a.asset_no = "+asset_no+" and a.status_id = 'A'";
		
		System.out.printf("displaytagged_peripherals: %s", sql);
		pgSelect db = new pgSelect();
		db.select(sql);
		
		if (db.isNotNull()) {
			for (int x = 0; x < db.getRowCount(); x++) {
				modelMain.addRow(db.getResult()[x]);
				listModel.addElement(modelMain.getRowCount());
			}
			
		}
		tbltagged.packAll();
	}
	private static void save_peripherals() {
		int row = tblasset.convertRowIndexToModel(tblasset.getSelectedRow());
		Integer asset_no = (Integer) modelasset.getValueAt(row, 0);
		
		
		for(int x = 0; x < modeltagging.getRowCount(); x++) {
			
			System.out.println("rowcount: "+ modeltagging.getRowCount());
			Boolean selected = (Boolean) modeltagging.getValueAt(x, 0);
			Integer cat_id = (Integer) modeltagging.getValueAt(x, 2);
			String brand = (String) modeltagging.getValueAt(x, 6);
			String model = (String) modeltagging.getValueAt(x, 7);
			String description = (String) modeltagging.getValueAt(x, 8);
			String serial_no = (String) modeltagging.getValueAt(x, 9);
			String lic_key = (String) modeltagging.getValueAt(x, 10);
			String supp_id = (String) modeltagging.getValueAt(x, 11);
			String created_by = UserInfo.EmployeeCode;
			Double amount = Double.parseDouble( modeltagging.getValueAt(x, 5).toString());
			
			if(selected) {
				System.out.println("cat_id: "+ cat_id);
				System.out.println("brand: "+ brand);
				System.out.println("model: "+ model);
				System.out.println("description: "+ description);
				System.out.println("serial_no: "+ serial_no);
				System.out.println("lic_key: "+ lic_key);
				System.out.println("supp_id: "+ supp_id);
				System.out.println("valueof amount: "+amount);
				
				String sql = "select sp_save_asset_peripherals("+asset_no+", "+cat_id+", '"+brand+"', '"+model+"', '"+description+"', '"+serial_no+"', '"+lic_key+"', '"+supp_id+"', '"+created_by+"',"+amount+")";
				
				FncSystem.out("save_peripherals: ", sql);
				pgSelect db = new pgSelect();
				db.select(sql);
			}
		}
	}
	
	private void save_edit() {
		
		for(int x = 0; x < modeltagged.getRowCount(); x++) {
			Boolean selected = (Boolean) modeltagged.getValueAt(x, 0);
			Integer asset_no = (Integer) modeltagged.getValueAt(x, 1);
			String current_cust = (String) modeltagged.getValueAt(x, 4);
			String brand = (String) modeltagged.getValueAt(x, 6);
			String model = (String) modeltagged.getValueAt(x, 7);
			String desc = (String) modeltagged.getValueAt(x, 8);
			String serial = (String) modeltagged.getValueAt(x, 9);
			String lic_key = (String) modeltagged.getValueAt(x, 10);
			String supp_id = (String) modeltagged.getValueAt(x, 11);
			Integer category_id = (Integer) modeltagged.getValueAt(x, 17);
			Double amount = Double.parseDouble( modeltagged.getValueAt(x, 5).toString());
			
			if(selected) {
				System.out.println("asset_no: "+asset_no);
				System.out.println("current_cust: "+current_cust);
				System.out.println("brand: "+brand);
				System.out.println("model: "+model);
				System.out.println("desc: "+desc);
				System.out.println("serial: "+serial);
				System.out.println("lic_key: "+lic_key);
				System.out.println("supp_id: "+supp_id);
				System.out.println("category_id: "+category_id);
				System.out.println("amount: "+amount);
				
				String sql = "select sp_save_edit_peripherals("+asset_no+", "+category_id+", "+amount+", \n"
						+ " '"+brand+"', '"+model+"', '"+desc+"', '"+serial+"', '"+lic_key+"', '"+supp_id+"', '"+UserInfo.EmployeeCode+"')\n"
						+ "";
				
				FncSystem.out("save_edit: ", sql);
				pgSelect db = new pgSelect();
				db.select(sql);
			}
		}
	}
	
	private void updateperipheral(pgUpdate db) {
		
		for(int x = 0; x < modeltagged.getRowCount(); x++) {
			Boolean selected = (Boolean) modeltagged.getValueAt(x, 0);
			Integer perip_id = (Integer) modeltagged.getValueAt(x, 2);
			if(selected) {
				String Sql = "update rf_asset_peripheral set status_id = 'I', edited_by = '"+UserInfo.EmployeeCode+"', date_edited = now() where peripheral_id = "+perip_id+"";
				db.executeUpdate(Sql, false);
				//db.commit();
			}
		}
	}
	public Boolean hasCheckedAssets(){
		
		ArrayList<Boolean> checkTable = new ArrayList<Boolean>();
		for(int x=0; x<modeltagged.getRowCount(); x++){
			if(modeltagged.getValueAt(x, 0).equals(true))
				checkTable.add(true);
		}
		return checkTable.contains(true);
		
	}
	
	public Boolean hasCheckedAssets_tagging(){
		
		ArrayList<Boolean> checkTable = new ArrayList<Boolean>();
		for(int x=0; x<modeltagging.getRowCount(); x++){
			if(modeltagging.getValueAt(x, 0).equals(true))
				checkTable.add(true);
		}
		return checkTable.contains(true);
		
	}
	
	public void cancelstate() {
		
		lookupcomp.setValue("");
		tagcomp.setTag("");
		FncTables.clearTable(modelasset);
		FncTables.clearTable(modeltagging);
		FncTables.clearTable(modeltagged);
		rowheaderassetperipheral = tblasset.getRowHeader(); 
		scrollassetperipheral.setRowHeaderView(rowheaderassetperipheral);
		rowheadertagging= tbltagperipheral.getRowHeader();
		scrolltagperipheral.setRowHeaderView(rowheadertagging);
		rowheadertagged = tbltagged.getRowHeader();
		scrolltagged_peripheral.setRowHeaderView(rowheadertagged);
		to_do = "";
		buttonstate(false, false, false, false);
	}
}
