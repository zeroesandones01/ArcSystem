package Accounting.FixedAssets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Database.pgSelect;
import Database.pgUpdate;
import Functions.FncSystem;
import Functions.FncTables;
import Functions.UserInfo;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import components.JTBorderFactory;
import components._JButton;
import components._JTableMain;
import interfaces._GUI;
import tablemodel.modelMovement;


public class panelAssetTagging extends JPanel implements ActionListener, _GUI {

	private static final long serialVersionUID = 7987392080467633997L;
	private JPanel pnlTransfer;
	public static JTextField txtmovementno;
	public static _JLookup lookupnewCustodian;
	public static JTextField txtnewCustodian;
	public static _JLookup lookupLocation;
	public static JTextField txtLocation;
	protected static String loc_id = null ;
	public static String div_code = null;
	public static JTextField jtxtRemarks;
	private JScrollPane scrollMovement;
	private JPanel pnlsouthtransfer;
	private static JButton btnDispose;
	private static JButton btnRetire;
	public static modelMovement modelmovement;
	private _JTableMain tblmovement;
	public static JList rowheaderMovement;
	protected DefaultTableCellRenderer rendererCenterAlign = new DefaultTableCellRenderer();
	private static JButton btntransfer;
	private static _JButton btnnew;
	private static _JButton btnsave;
	private static _JButton btncancel;
	private static JButton btnpreview;
	public static _JLookup lookupmove_no;
	
	public panelAssetTagging() {
		initGUI();
	}

	@Override
	public void initGUI() {
		this.setLayout(new BorderLayout(5, 5));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 3, 3));
		{
			JPanel pnltagging = new JPanel(new BorderLayout(5, 5));
			this.add(pnltagging, BorderLayout.CENTER);
			{
				JPanel pnlnorthdetails = new JPanel(new BorderLayout(5, 5));
				pnltagging.add(pnlnorthdetails, BorderLayout.NORTH);
				pnlnorthdetails.setPreferredSize(new Dimension(400, 150));
				{
					JPanel pnlnorthdetails_north = new JPanel(new GridLayout(4, 1,5,5));
					pnlnorthdetails.add(pnlnorthdetails_north, BorderLayout.WEST);
					pnlnorthdetails_north.setPreferredSize(new Dimension(100, 0));
					pnlnorthdetails_north.setBorder(JTBorderFactory.createTitleBorder(""));
					{
						btnnew = new _JButton("New");
						pnlnorthdetails_north.add(btnnew);
						btnnew.setActionCommand("new");
						btnnew.setEnabled(false);
						btnnew.addActionListener(this);
					}
					{
						btnsave = new _JButton("Save");
						pnlnorthdetails_north.add(btnsave);
						btnsave.setActionCommand("save");
						btnsave.setEnabled(false);
						btnsave.addActionListener(this);
					}
					{
						btncancel = new _JButton("Cancel");
						pnlnorthdetails_north.add(btncancel);
						btncancel.setActionCommand("cancel");
						btncancel.setEnabled(false);
						btncancel.addActionListener(this);
					}
					{
						btnpreview = new JButton("Preview");
						pnlnorthdetails_north.add(btnpreview);
						btnpreview.setActionCommand("preview");
						btnpreview.setEnabled(false);
						btnpreview.addActionListener(this);
					}
				}
				{

					JPanel pnlnorthdetails_center = new JPanel(new GridLayout(4, 1, 5, 5));
					pnlnorthdetails.add(pnlnorthdetails_center, BorderLayout.CENTER);
					pnlnorthdetails_center.setBorder(JTBorderFactory.createTitleBorder(""));
					{
						JPanel pnl1 = new JPanel(new BorderLayout(5, 5));
						pnlnorthdetails_center.add(pnl1);
						{
							JLabel lblMovementno = new JLabel("Movement No.");
							pnl1.add(lblMovementno, BorderLayout.WEST);
							lblMovementno.setPreferredSize(new Dimension(100, 0));
						}
						{
							lookupmove_no = new _JLookup();
							pnl1.add(lookupmove_no, BorderLayout.CENTER);
							lookupmove_no.setReturnColumn(0);
							lookupmove_no.setEnabled(true);
							lookupmove_no.setLookupSQL(get_tagging_trans());
							lookupmove_no.addLookupListener(new LookupListener() {
								
								public void lookupPerformed(LookupEvent event) {
									
									panelAssetTagging.buttontagging(true, true, true, false, false, true, false );
								}
							});
							
						}
//						{
//							txtmovementno = new JTextField();
//							pnl1.add(txtmovementno, BorderLayout.CENTER);
//						}
						{
							JLabel lblextra = new JLabel("");
							pnl1.add(lblextra, BorderLayout.EAST);
							lblextra.setPreferredSize(new Dimension(550, 0));
						}
					}
					{
						JPanel pnl2 = new JPanel(new BorderLayout(5, 5));
						pnlnorthdetails_center.add(pnl2);
						setPreferredSize(new Dimension(115, 30));
						{
							JLabel lblNewcustodian = new JLabel("*New Custodian");
							pnl2.add(lblNewcustodian, BorderLayout.WEST);
							lblNewcustodian.setPreferredSize(new Dimension(100, 0));

						}
						{
							lookupnewCustodian = new _JLookup();
							pnl2.add(lookupnewCustodian, BorderLayout.CENTER);
							lookupnewCustodian.setEnabled(false);
							lookupnewCustodian.setLookupSQL(panelAssetInformation2.getCustodian());
							lookupnewCustodian.addLookupListener(new LookupListener() {
								public void lookupPerformed(LookupEvent event) {
									Object[] setCustodian = ((_JLookup) event.getSource()).getDataSet();
									FncSystem.out("Display SQL for Client", lookupnewCustodian.getLookupSQL());
									if (setCustodian != null) {
										String emp_code = (String) setCustodian[0];
										String emp_name = (String) setCustodian[1];
										div_code = (String) setCustodian[2];
										lookupnewCustodian.setValue(emp_code);
										txtnewCustodian.setText(emp_name);
										//txtmovementno.setText(getMoveNo());
										//lookupmove_no.setText(getMoveNo());

									}
								}
							});
						}
						{
							txtnewCustodian = new JTextField();
							pnl2.add(txtnewCustodian, BorderLayout.EAST);
							txtnewCustodian.setPreferredSize(new Dimension(550, 0));
							txtnewCustodian.setEnabled(false);
						}
					}
					{
						JPanel pnl3 = new JPanel(new BorderLayout(5, 5));
						pnlnorthdetails_center.add(pnl3);
						setPreferredSize(new Dimension(115, 30));
						{
							JLabel lblLocation = new JLabel("*Location");
							pnl3.add(lblLocation, BorderLayout.WEST);
							lblLocation.setPreferredSize(new Dimension(100, 0));

						}
						{
							lookupLocation = new _JLookup();
							pnl3.add(lookupLocation, BorderLayout.CENTER);
							lookupLocation.setEnabled(false);
							lookupLocation.setLookupSQL(getassetlocation());
							lookupLocation.addLookupListener(new LookupListener() {

								@Override
								public void lookupPerformed(LookupEvent event) {
									Object[] setLocation = ((_JLookup) event.getSource()).getDataSet();
									FncSystem.out("Display SQL for Location", getassetlocation());
									if (setLocation != null) {
										loc_id = (String) setLocation[0];
										String loc_name = (String) setLocation[1];
										lookupLocation.setValue(loc_id);
										txtLocation.setText(loc_name);

									}
								}
							});
						}
						{
							txtLocation = new JTextField();
							pnl3.add(txtLocation, BorderLayout.EAST);
							txtLocation.setPreferredSize(new Dimension(550, 0));
							txtLocation.setEnabled(false);
						}
					}
					{
						JPanel pnl4 = new JPanel(new BorderLayout(5, 5));
						pnlnorthdetails_center.add(pnl4);
						{
							JLabel lblRemarks = new JLabel("*Remarks");
							pnl4.add(lblRemarks, BorderLayout.WEST);
							lblRemarks.setPreferredSize(new Dimension(100, 0));
						}
						{
							jtxtRemarks = new JTextField();
							pnl4.add(jtxtRemarks, BorderLayout.CENTER);
							jtxtRemarks.setEnabled(false);
						}
					}
				}
			}
			{
				JPanel pnlCentertransfer = new JPanel(new BorderLayout(5, 5));
				pnltagging.add(pnlCentertransfer, BorderLayout.CENTER);
				{
					scrollMovement = new JScrollPane();
					pnlCentertransfer.add(scrollMovement);
					{
						modelmovement = new modelMovement();
						tblmovement = new _JTableMain(modelmovement);
						scrollMovement.setViewportView(tblmovement);
						
						tblmovement.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						tblmovement.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
						tblmovement.getColumnModel().getColumn(0).setPreferredWidth(100);// move_no
						tblmovement.getColumnModel().getColumn(1).setPreferredWidth(125);// old asset
						tblmovement.getColumnModel().getColumn(2).setPreferredWidth(135);// prev_cust
						tblmovement.getColumnModel().getColumn(3).setPreferredWidth(135);// current_cust
						tblmovement.getColumnModel().getColumn(4).setPreferredWidth(110);// trans_date
						tblmovement.getColumnModel().getColumn(5).setPreferredWidth(95);// reason
						tblmovement.getColumnModel().getColumn(6).setPreferredWidth(150);// remarks
						tblmovement.getColumnModel().getColumn(6).setPreferredWidth(150);// old location 
						tblmovement.setFont(new Font("DejaVu Sans", 0, 12));
						tblmovement.getColumnModel().getColumn(0).setCellRenderer(rendererCenterAlign);
						tblmovement.getColumnModel().getColumn(1).setCellRenderer(rendererCenterAlign);
						tblmovement.getColumnModel().getColumn(4).setCellRenderer(rendererCenterAlign);
					}
					{
						rowheaderMovement = tblmovement.getRowHeader();
						scrollMovement.setRowHeaderView(rowheaderMovement);
						scrollMovement.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER,
								FncTables.getRowHeader_Header());
					}
				}
			}
			{
				pnlsouthtransfer = new JPanel(new BorderLayout(5, 5));
				pnltagging.add(pnlsouthtransfer, BorderLayout.SOUTH);
				pnlsouthtransfer.setPreferredSize(new Dimension(0, 35));
				{
					JPanel pnlbuttons = new JPanel(new GridLayout(1, 4, 5, 5));
					pnlsouthtransfer.add(pnlbuttons);
					{
						btntransfer = new JButton("Transfer asset");
						btntransfer.setActionCommand("transfer");
						pnlbuttons.add(btntransfer, BorderLayout.WEST);
						btntransfer.setEnabled(false);
						btntransfer.addActionListener(this);
					}
					{
						btnDispose = new JButton("Dispose");
						pnlbuttons.add(btnDispose);
						btnDispose.setActionCommand("dispose");
						btnDispose.setEnabled(false);
						btnDispose.addActionListener(this);
					}
					{
						btnRetire = new JButton("Retire");
						pnlbuttons.add(btnRetire);
						btnRetire.setActionCommand("retire");
						btnRetire.setEnabled(false);
						btnRetire.addActionListener(this);
					}
					
				}
			}
		}
		buttontagging(false, false, false, true, false, false, false );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("new")) {
			//lookupmove_no.setText(getMoveNo());
			AssetMonitoring2.modelAssets.setEditable(true);
			lookupmove_no.setEnabled(false);
			lookupnewCustodian.setEnabled(true);
			lookupLocation.setEnabled(true);
			jtxtRemarks.setEnabled(true);
			AssetMonitoring2.clearcheckbox();
			
			buttontagging(true, false, false, false, true, true, false );
			
			System.out.println("New........");
		}
		
		if(e.getActionCommand().equals("save")) {
			
		}
		
		if(e.getActionCommand().equals("cancel")) {
			
			lookupmove_no.setEnabled(true);
			lookupmove_no.setValue(null);
			
			lookupnewCustodian.setEnabled(false);
			lookupnewCustodian.setValue(null);
			txtnewCustodian.setText("");
			
			lookupLocation.setEnabled(false);
			lookupLocation.setValue(null);
			txtLocation.setText("");
			
			jtxtRemarks.setEnabled(false);
			jtxtRemarks.setText("");
			
			AssetMonitoring2.clearcheckbox();
			AssetMonitoring2.modelAssets.setEditable(false);
			
			
			
			buttontagging(false, false, false, true, false, false, false );
			
			System.out.println("Cancel........");
		}
		
		if(e.getActionCommand().equals("preview")) {
			
		}
		
		if(e.getActionCommand().equals("transfer")) {
			
			if (AssetMonitoring2.tblAssets.getSelectedRows().length > 0) {
				if (hasCheckedAssets()) {
					if(lookupnewCustodian.getValue() != null) {
						
						int toSave = JOptionPane.showConfirmDialog(getTopLevelAncestor(),"Are all entries correct?", btntransfer.getText(),JOptionPane.YES_NO_OPTION);
						if (toSave == JOptionPane.YES_OPTION) {
							int row = AssetMonitoring2.tblAssets.getSelectedRow();
							String custodian_id = AssetMonitoring2.modelAssets.getValueAt(row, 4).toString();
							//System.out.println("move_no: "+move_no);
							transferAsset( custodian_id, lookupnewCustodian.getValue(),jtxtRemarks.getText(), txtnewCustodian.getText());
							panelAssetInformation2.resetInformation();
							JOptionPane.showMessageDialog(getTopLevelAncestor(),"Asset is already transferred.", "Transfer",JOptionPane.INFORMATION_MESSAGE);
							
							System.out.println("Transfer asssssettt");
							buttontagging(false, false, false, true, false, false, false );
							
					}else {
						JOptionPane.showMessageDialog(getTopLevelAncestor(), "Please select new custodian.", "Transfer Assets", JOptionPane.ERROR_MESSAGE);
					}
				}
				}else {
					JOptionPane.showMessageDialog(getTopLevelAncestor(),"Please fill up the required fields", btntransfer.getText(),JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(getTopLevelAncestor(),"Please select asset to transfer", "Transfer",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(e.getActionCommand().equals("dispose")) {
			if (hasCheckedAssets()) {
				int todispose = JOptionPane.showConfirmDialog(getTopLevelAncestor(), "Dispose selected asset?", "Dispose", JOptionPane.YES_NO_OPTION);
				if (todispose == JOptionPane.YES_OPTION) {
					disposeAsset(AssetMonitoring2.tblAssets);
					panelAssetInformation2.resetInformation();
					JOptionPane.showMessageDialog(getTopLevelAncestor(),"Asset has been disposed.", "Dispose",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		if(e.getActionCommand().equals("retire")) {
			if (hasCheckedAssets()) {
				int todispose = JOptionPane.showConfirmDialog(getTopLevelAncestor(), "Retire selected asset?", "Retire", JOptionPane.YES_NO_OPTION);
				if (todispose == JOptionPane.YES_OPTION) {
					retireAsset(AssetMonitoring2.tblAssets);
					panelAssetInformation2.resetInformation();
					JOptionPane.showMessageDialog(getTopLevelAncestor(),"Asset has been Retired.", "Retire",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}


	}
	
	public static void disposeAsset(JTable table){
		pgUpdate db= new pgUpdate();
		for(int x=0; x<table.getRowCount(); x++){
			
		Boolean selected = (Boolean) table.getValueAt(x, 0);
		if( selected ){
			String strSQL = "update rf_asset set status='I', remarks='DISPOSED', date_disposed = now()::date where asset_no = "+table.getValueAt(x, 1)+" " ;
			FncSystem.out("update rf_asset: ", strSQL);
			db.executeUpdate(strSQL, true);
			
			String strSQL2 = "INSERT INTO rf_asset_history( \n" +
						"prev_cust, \n" +
						"current_cust, \n" +
						"trans_date, \n" +
						"reason, \n" +
						"remarks, \n" +
						"status, \n" +
						"move_no, \n" +
						"asset_no, \n" +
						"trans_by) \n" +
						"VALUES (\n" +
						"(select current_cust from rf_asset where asset_no ="+table.getValueAt(x, 1)+"), \n" +//prev_cust
						"(select current_cust from rf_asset where asset_no ="+table.getValueAt(x, 1)+"), \n" +//current_cust
						"now()::date, \n" +//trans_date
						"'Disposed', \n" +//reason
						"'Disposed by "+UserInfo.FullName+"', \n" +//remarks
						"'I', \n" +//status
						"(select coalesce(max(move_no),0) + 1 from rf_asset_history), \n" +//move_no
						""+table.getValueAt(x, 1)+", \n" +//asset_no
						"'" +UserInfo.EmployeeCode + "') \n";//trans_by
				
			FncSystem.out("INSERT INTO rf_asset_history: ", strSQL2);
			db.executeUpdate(strSQL2, true);
			}
		}
		db.commit();
	}
	
	public static void retireAsset(JTable table){
		pgUpdate db=new pgUpdate();	
		
		for(int x=0; x<table.getRowCount(); x++) {
			Boolean selected = (Boolean) table.getValueAt(x, 0);
			if( selected) {
				
				System.out.println("Ass. "+ AssetMonitoring2.tblAssets.getValueAt(x, 1));
				String strSQL = "update rf_asset set status='I', remarks='RETIRED', date_retired=current_date where asset_no = "+table.getValueAt(x, 1)+" " ;
				FncSystem.out("update rf_asset: ", strSQL);
				db.executeUpdate(strSQL, true);
				String strSQL2 = "INSERT INTO rf_asset_history( \n" +
							"prev_cust, \n" +
							"current_cust, \n" +
							"trans_date, \n" +
							"reason, \n" +
							"remarks, \n" +
							"status, \n" +
							"move_no, \n" +
							"asset_no, \n" +
							"trans_by) \n" +
							"VALUES (\n" +
							"(select current_cust from rf_asset where asset_no ="+table.getValueAt(x, 1)+"), \n" +//prev_cust
							"(select current_cust from rf_asset where asset_no ="+table.getValueAt(x, 1)+"), \n" +//current_cust
							"now()::date, \n" +//trans_date
							"'Retired', \n" +//reason
							"'Retired by "+UserInfo.FullName+"', \n" +//remarks
							"'A', \n" +//status
							"(select coalesce(max(move_no),0) + 1 from rf_asset_history), \n" +//move_no
							""+table.getValueAt(x, 1)+", \n" +//asset_no
							"'" + UserInfo.EmployeeCode+ "') \n";//trans_by
				FncSystem.out("INSERT INTO rf_asset_history: ", strSQL2);
				db.executeUpdate(strSQL2, true);
			}
		}
		db.commit();
	}
	
	public static void transferAsset( String prev_cust, String current_cust, String remarks,String new_cust_name) {
		//pgUpdate db = new pgUpdate();
		//ArrayList<String> listAsset_no = new ArrayList<String>();
		//ArrayList<Boolean> isSaved = new ArrayList<Boolean>();
		for (int x = 0; x < AssetMonitoring2.modelAssets.getRowCount(); x++) {

			Boolean selected = (Boolean) AssetMonitoring2.modelAssets.getValueAt(x, 0);
			if (selected) {
				
				String asset_no = ((String) AssetMonitoring2.modelAssets.getValueAt(x, 1)).trim();
				System.out.println("asset_no" + asset_no);
				
				String sql = "select save_transfer_asset('"+asset_no+"', '"+current_cust+"', '"+prev_cust+"', '"+remarks+"', NULLIF('"+lookupLocation.getValue()+"', 'null'), '"+UserInfo.EmployeeCode+"')";
				
				FncSystem.out("transferAsset: ", sql);
				pgSelect db = new pgSelect();
				db.select(sql);
			}
		}	
//			if (selected) {
//				String asset_no = ((String) AssetMonitoring2.modelAssets.getValueAt(x, 1)).trim();
//				String custodian_id = (String) AssetMonitoring2.modelAssets.getValueAt(x, 4).toString();
//				String strSQL = null;
//				if (loc_id == null) {
//					strSQL = "INSERT INTO rf_asset_history( \n" + "prev_cust, " + "current_cust, "
//							+ "trans_date, " + "reason, " + "remarks, \n" + "status, " + "move_no, " + "asset_no, "
//							+ "trans_by,\n" +
//							"div_code) \n" + "VALUES ( " + 
//							"'" + custodian_id + "', \n" + // prev_cust
//							"'" + current_cust + "', \n" + // current_cust
//							"current_date, \n" + // trans_date
//							"'TRANSFER', \n" + // reason
//							"'" + remarks + "', \n" + // remarks
//							"'A', \n" + // status
//							"'"+move_no+"', \n" + // move_no
//							"'" + asset_no + "', \n" + // asset_no
//							"'" + UserInfo.EmployeeCode + "',\n" +
//							"'" + div_code + "') \n";// trans_by
//				} else {
//					strSQL = "INSERT INTO rf_asset_history( \n" + "prev_cust, " + "current_cust, "
//							+ "trans_date, " + "reason, " + "remarks, \n" + "status, " + "move_no, " + "asset_no, "
//							+ "trans_by,\n" +
//							"old_location,\n" + "div_code) \n" + "VALUES ("+
//							"'" + custodian_id + "', \n" + // prev_cust
//							"'" + current_cust + "', \n" + // current_cust
//							"current_date, \n" + // trans_date
//							"'TRANSFER', \n" + // reason
//							"'" + remarks + "', \n" + // remarks
//							"'A', \n" + // status
//							"'"+move_no+"', \n" + // move_no
//							"'" + asset_no + "', \n" + // asset_no
//							"'" + UserInfo.EmployeeCode + "',\n" +
//							"(select loc_id from rf_asset where asset_no='" + asset_no + "' ),\n" + "'" + div_code
//							+ "') \n";// trans_by
//				}
//				System.out.println("move_no" + move_no);
//				System.out.println("prev_cust" + prev_cust);
//				System.out.println("current_cust" + current_cust);
//				System.out.println("remarks" + remarks);
//				System.out.println("new_cust_name" + new_cust_name);
//				System.out.println("dept_code " + div_code);
//				System.out.println();
//				db.executeUpdate(strSQL, true);
//				String strSQL2 = null;
//				if (loc_id == null) {
//					strSQL2 = "update rf_asset set current_cust='" + current_cust
//							+ "',status='A',item_found='t'  where asset_no='" + asset_no + "'::int";
//				} else {
//					strSQL2 = "update rf_asset set current_cust='" + current_cust
//							+ "',status='A',item_found='t',loc_id='" + loc_id + "'  where asset_no='" + asset_no
//							+ "'::int";
//				}
//				db.executeUpdate(strSQL2, false);
//				//listAsset_no.add(asset_no);
//				//isSaved.add(true);
//				FncSystem.out("transfer asset", strSQL);
//				FncSystem.out("transfer asset", strSQL2);
//
//			}
//		}
//		db.commit();
	}
	
	protected Boolean hasCheckedAssets() {
		ArrayList<Boolean> checkTable = new ArrayList<Boolean>();
		for (int x = 0; x < AssetMonitoring2.tblAssets.getRowCount(); x++) {
			if (AssetMonitoring2.tblAssets.getValueAt(x, 0).equals(true))
				checkTable.add(true);
		}
		return checkTable.contains(true);

	}
	
	private static String getMoveNo(){
		pgSelect db = new pgSelect();
		
		String strSQL = "select trim(to_char(coalesce(max(move_no), 0) + 1, '0000000000')) from rf_asset_history";
		db.select(strSQL);
		return db.getResult()[0][0].toString();
	}
	
	public static String getassetlocation() {

		return "select loc_id,loc_name from rf_asset_location ";
	}
	
	private String get_tagging_trans () {
		String sql = "select distinct on (a.move_no)a.move_no, c.entity_name, a.trans_date, a.status \n"
				+ "from rf_asset_history a\n"
				+ "left join rf_employee b on a.trans_by = b.emp_code::int\n"
				+ "left join rf_entity c on b.entity_id = c.entity_id\n"
				+ "where a.status = 'A'";
		
		System.out.println("get_tagging_trans: "+ sql);
		return sql;
	}
	
	public static  void buttontagging(Boolean transfer, Boolean dispose, Boolean retire, Boolean add, Boolean save, Boolean cancel, Boolean preview ) {
		btntransfer.setEnabled(transfer);
		btnDispose.setEnabled(dispose);
		btnRetire.setEnabled(retire);
		btnnew.setEnabled(add);
		btncancel.setEnabled(cancel);
		btnsave.setEnabled(save);
		btnpreview.setEnabled(preview);
	}
	
	public static void displayMovementHistory( String asset_no){
	
		FncTables.clearTable(modelmovement);//Code to clear model.
		DefaultListModel listModel = new DefaultListModel();//Creating listModel for rowHeader.
		rowheaderMovement.setModel(listModel);//Setting of listModel into rowHeader.
		
		String strSQL ="\n" + 
				"select  to_char(a.move_no,'FM00000000') as move_no,\n" + 
				"format('%s, %s %s.', c.last_name, c.first_name, left(c.middle_name, 1)),\n" + 
				"format('%s, %s %s.', d.last_name, d.first_name, left(d.middle_name, 1)),\n" + 
				"a.trans_date,\n" + 
				"a.reason,\n" + 
				"a.remarks,\n" + 
				"coalesce((select loc_name from rf_asset_location where loc_id = a.old_location), 'NOT CHANGED') \n"+
				"from rf_asset_history a  \n" + 
				"left join rf_employee b on a.prev_cust=b.emp_code::int\n" + 
				"left join rf_employee e on a.current_cust=e.emp_code::int\n" + 
				"--left join tbl_asset_history  on b.emp_code=a.current_cust::varchar\n" + 
				"left join rf_entity c on b.entity_id=c.entity_id\n" + 
				"left join rf_entity d on e.entity_id=d.entity_id \n" + 
				"where a.asset_no="+asset_no+" \n" + 
				"order by a.move_no desc, a.trans_date desc ";
		
		pgSelect db= new pgSelect(); 
		db.select(strSQL);
		
		FncSystem.out("displayMovementHistory", strSQL);
		
		if(db.isNotNull()){
			for(int x=0; x < db.getRowCount(); x++){
	
				//You can only use this kind of adding row in model when you're query and model has the same and exact unmber of columns and column types.
				modelmovement.addRow(db.getResult()[x]);
	
				//For every row added in model, the table header will also add the row number.
				listModel.addElement(modelmovement.getRowCount());
			}
		}
	}
	
}
