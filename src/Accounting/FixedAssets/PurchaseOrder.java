package Accounting.FixedAssets;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import Database.pgSelect;
import Database.pgUpdate;
import Functions.FncReport;
import Functions.FncTables;
import Functions.UserInfo;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import components.JTBorderFactory;
import components._JInternalFrame;
import interfaces._GUI;

public class PurchaseOrder extends _JInternalFrame implements _GUI, ActionListener {

	private static final long serialVersionUID = 1L;
	public static String title="Purchase Order Module";
	public static Dimension framesize= new Dimension(700,500);
	private static JButton btncancel;
	private static JButton btnsave;
	private static JButton btnedit;
	private static JButton btnadd;
	private JTabbedPane tabcenter;
	public static JTextField txtdivdept;
	public static JTextField txtrequester_name;
	public static _JLookup lookuprequester;
	public static JTextField txtcompany;
	public static _JLookup lookupcompany;
	private PurchaseOrderTab pnlpurchaseordertab;
	private static JButton btnpreview;
	private static JButton btngenerate;

	public PurchaseOrder() {
		super(title, false, true, true, true);
		initGUI();
	}

	public PurchaseOrder(String title) {
		super(title, true, true, true, true);
		initGUI();
	}

	public PurchaseOrder(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
	}

	@Override
	public void initGUI() {

		this.setTitle(title);
		this.setSize(framesize );
		this.setPreferredSize(framesize );
		this.setLayout(new BorderLayout());
		{
			JPanel pnlmain = new JPanel(new BorderLayout(5, 5));
			getContentPane().add(pnlmain);
			pnlmain.setBorder(_EMPTY_BORDER);
			{
				JPanel pnlnorth = new JPanel(new BorderLayout(5, 5));
				pnlmain.add(pnlnorth, BorderLayout.NORTH);
				pnlnorth.setPreferredSize(new Dimension(0, 90));
				pnlnorth.setBorder(JTBorderFactory.createTitleBorder(""));
				{
					JPanel pnlnorthwest= new JPanel(new BorderLayout(3, 3));
					pnlnorth.add(pnlnorthwest, BorderLayout.WEST);
					pnlnorthwest.setPreferredSize(new Dimension(450, 0));
					{
						JPanel pnlcompany = new JPanel(new BorderLayout(3, 3));
						pnlnorthwest.add(pnlcompany, BorderLayout.NORTH);
						pnlcompany.setPreferredSize(new Dimension(0, 27));
						{
							JLabel lblcompany= new JLabel("Company");
							pnlcompany.add(lblcompany,BorderLayout.WEST);
							lblcompany.setPreferredSize(new Dimension(70, 0));
						}
						{
							lookupcompany = new _JLookup();
							pnlcompany.add(lookupcompany, BorderLayout.CENTER);
							lookupcompany.setLookupSQL( get_company());
							lookupcompany.setReturnColumn(0);
							//lookupcompany.setEditable(true);
							lookupcompany.addLookupListener(new LookupListener() {
								public void lookupPerformed(LookupEvent event) {
									Object[] data = ((_JLookup)event.getSource()).getDataSet();
									if(data != null) {
										txtcompany.setText((String) data [1]);
										lookuprequester.setLookupSQL(get_requester());
										PurchaseOrderTab.lookuppono.setLookupSQL(PurchaseOrderTab.get_po(PurchaseOrderTab.cmbtype.getSelectedIndex(),lookupcompany.getValue(), lookuprequester.getValue()));
									}
								}
							});
						} 
						{
							txtcompany = new JTextField();
							pnlcompany.add(txtcompany, BorderLayout.EAST);
							txtcompany.setPreferredSize(new Dimension(300, 0));
							txtcompany.setEditable(false);
						}
					}
					{
						JPanel pnlrequester= new JPanel(new BorderLayout(3,3));
						pnlnorthwest.add(pnlrequester, BorderLayout.SOUTH);
						pnlrequester.setPreferredSize(new Dimension(0, 27));
						{
							JLabel lblrequester = new JLabel("Requester");
							pnlrequester.add(lblrequester, BorderLayout.WEST);
							lblrequester.setPreferredSize(new Dimension(70, 0));
						}
						{
							lookuprequester = new _JLookup();
							pnlrequester.add(lookuprequester, BorderLayout.CENTER);
							lookuprequester.setReturnColumn(0);
							//lookuprequester.setEnabled(false);
							lookuprequester.setLookupSQL(get_requester());
							lookuprequester.addLookupListener(new LookupListener() {
								public void lookupPerformed(LookupEvent event) {
									Object [] data = ((_JLookup)event.getSource()).getDataSet();
									if(data != null) {
										txtrequester_name.setText((String) data[1]);
										txtdivdept.setText((String)data[4]);
										
										PurchaseOrderTab.lookuppono.setLookupSQL(PurchaseOrderTab.get_po(PurchaseOrderTab.cmbtype.getSelectedIndex(),lookupcompany.getValue(), lookuprequester.getValue()));
									}
								}
							});
						}
						{
							txtrequester_name = new JTextField();
							pnlrequester.add(txtrequester_name, BorderLayout.EAST);
							txtrequester_name.setEditable(false);
							txtrequester_name.setPreferredSize(new Dimension(300, 0));
						}
					}
				}
				{
					JPanel pnlnortheast = new JPanel(new BorderLayout(3, 3));
					pnlnorth.add(pnlnortheast, BorderLayout.EAST);
					pnlnortheast.setPreferredSize(new Dimension(170, 0));
					{
						JPanel pnldivdept= new JPanel(new BorderLayout(3, 3));
						pnlnortheast.add(pnldivdept, BorderLayout.NORTH);
						pnldivdept.setPreferredSize(new Dimension(0, 27));
						{
							JLabel lbldivdept= new JLabel("Eo/Div");
							pnldivdept.add(lbldivdept, BorderLayout.WEST);
							lbldivdept.setPreferredSize(new Dimension(60, 0));
						}
						{
							txtdivdept= new JTextField();
							pnldivdept.add(txtdivdept, BorderLayout.CENTER);
							txtdivdept.setEditable(false);
						}
					}
					{
						JPanel pnldate= new JPanel(new BorderLayout(3, 3));
						pnlnortheast.add(pnldate, BorderLayout.SOUTH);
						pnldate.setPreferredSize(new Dimension(0, 27));
					}
				}
			}
			{
				tabcenter = new JTabbedPane();
				pnlmain.add(tabcenter,BorderLayout.CENTER);
				tabcenter.setEnabled(false);
				
				{
					pnlpurchaseordertab = new PurchaseOrderTab();
					tabcenter.addTab("Purchase Order", null, pnlpurchaseordertab, null);
				}
				
				
				
//				tabcenter.addChangeListener(new ChangeListener() {
//					public void stateChanged(ChangeEvent arg0) {
//						int selectedTab = ((JTabbedPane)arg0.getSource()).getSelectedIndex();
//						
//						if (selectedTab == 0) {
//							
//						}
//					}
//				});
				
				
			}
			{
				JPanel pnlsouth=new JPanel(new GridLayout(1, 6, 3, 3));
				pnlmain.add(pnlsouth, BorderLayout.SOUTH);
				pnlsouth.setPreferredSize(new Dimension(0, 30));
				{
					btngenerate = new JButton("Generate");
					pnlsouth.add(btngenerate);
					btngenerate.setActionCommand("generate");
					btngenerate.addActionListener(this);
				}
				{
					btnadd = new JButton("Add New");
					btnadd.setActionCommand("Add");
					pnlsouth.add(btnadd);
					btnadd.setEnabled(true);
					btnadd.addActionListener(this);
				}
				{
					btnedit= new JButton("Edit");
					pnlsouth.add(btnedit);
					btnedit.setActionCommand("edit");
					btnedit.setEnabled(false);
					btnedit.addActionListener(this);
				}
//				{
//					btndelete = new JButton("Delete");
//					pnlsouth.add(btndelete);
//					btndelete.setActionCommand("DELETE");
//					btndelete.setEnabled(false);
//					btndelete.addActionListener(this);
//				}
				{
					btnsave = new JButton("Save");
					pnlsouth.add(btnsave);
					btnsave.setActionCommand("save");
					btnsave.setEnabled(false);
					btnsave.addActionListener(this);
				}
				{
					btncancel = new JButton("Cancel");
					pnlsouth.add(btncancel);
					btncancel.setActionCommand("cancel");
					btncancel.setEnabled(false);
					btncancel.addActionListener(this);
				}
				{
					btnpreview = new JButton("Preview");
					pnlsouth.add(btnpreview);
					btnpreview.setActionCommand("preview");
					btnpreview.setEnabled(false);
					btnpreview.addActionListener(this);
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if ( e.getActionCommand().equals("generate")){
			
			if ( PurchaseOrderTab.cmbtype.getSelectedIndex() == 0) {// For Supplies
				
				generate_supplies_po(PurchaseOrderTab.modelPO, PurchaseOrderTab.rowheaderPO);
				PurchaseOrderTab.tblPO.setEditable(true);
				PurchaseOrderTab.tblPO.setEnabled(true);
				PurchaseOrderTab.modelPO.setEditable(true);
				
				lookupcompany.setEnabled(true);
				lookupcompany.setEditable(true);
				lookuprequester.setEnabled(true);
				
				PurchaseOrderTab.lookupterms.setEditable(true);
				PurchaseOrderTab.txtqoute.setEditable(true);
				
			}else {// For Fixed Assets
				
			}
			enable_buttons(false, false, false, true, true, true);
		}
		
		if ( e.getActionCommand().equals("Add")){
			
			PurchaseOrderTab.tblPO.setEditable(true);
			PurchaseOrderTab.tblPO.setEnabled(true);
			PurchaseOrderTab.modelPO.setEditable(true);
			PurchaseOrderTab.btnAddAcct.setEnabled(true);
			FncTables.clearTable(PurchaseOrderTab.modelPO);
			PurchaseOrderTab.cleartable_rowheader();
			PurchaseOrderTab.add_row(); 
			
			lookupcompany.setEnabled(true);
			lookupcompany.setEditable(true);
			lookuprequester.setEnabled(true);
			
			//PurchaseOrderTab.lookuppono.setText(getpo_no());
			PurchaseOrderTab.lookupsupplier.setValue(null);
			PurchaseOrderTab.date_PO.setEnabled(true);
			PurchaseOrderTab.date_PO.setEditable(true);
			PurchaseOrderTab.date_PO.setDate(null);
			PurchaseOrderTab.txtrplfno.setText("");
			PurchaseOrderTab.txtqoute.setText("");
			
			PurchaseOrderTab.cmbtype.setEditable(false);
			PurchaseOrderTab.lookuppono.setEditable(false);
			PurchaseOrderTab.lookupterms.setEditable(true);
			PurchaseOrderTab.lookupsupplier.setEditable(true);
			PurchaseOrderTab.txtrplfno.setEditable(true);
			PurchaseOrderTab.txtqoute.setEditable(true);
			
			enable_buttons(false, false, false, true, true, false);
			
			
			
		}
		
		if ( e.getActionCommand().equals("edit")){
			
			PurchaseOrderTab.tblPO.setEditable(true);
			PurchaseOrderTab.tblPO.setEnabled(true);
			PurchaseOrderTab.modelPO.setEditable(true);
			PurchaseOrderTab.btnAddAcct.setEnabled(true);
			
			enable_buttons(false, false, false, true, true, false);
			
		}
		
		if ( e.getActionCommand().equals("save")){
			
			if (PurchaseOrderTab.checkdetails ()) {
				
				System.out.println("Date: "+PurchaseOrderTab.date_PO.getDate());
				System.out.println("Co_id: "+lookupcompany.getValue());
				System.out.println("Requester: "+lookuprequester.getValue());
				System.out.println("terms: "+ PurchaseOrderTab.lookupterms.getValue());
				System.out.println("Rplf: "+ PurchaseOrderTab.txtrplfno.getText());
				
				JOptionPane.showMessageDialog(getTopLevelAncestor(), "Please check details.");
				
				if( PurchaseOrderTab.date_PO.getDate() == null) {
					JOptionPane.showMessageDialog(getTopLevelAncestor(), "Please check purchase order date.");
				}
				if(lookupcompany.getValue() == null) {
					JOptionPane.showMessageDialog(getTopLevelAncestor(), "Please select company.");
				}
				if(lookuprequester.getValue() == null) {
					JOptionPane.showMessageDialog(getTopLevelAncestor(), "Please select requester.");
				}
				if(PurchaseOrderTab.lookupterms.getValue() == null) {
					JOptionPane.showMessageDialog(getTopLevelAncestor(), "Please select terms.");
				}
				
			}else {
				
				String po_no = PurchaseOrderTab.lookuppono.getText();
				System.out.println("po_no: "+po_no);
				
				if( po_no.equals("") || po_no.equals(null)) {
					
					System.out.println("Add new P.O.");
					String set_po_no = getpo_no();
					PurchaseOrderTab.save_purchase_order( set_po_no );
					JOptionPane.showMessageDialog(getTopLevelAncestor(), "New purchase order saved.", "Saving", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("New Po no after saving: "+set_po_no);
					PurchaseOrderTab.lookuppono.setValue(set_po_no);
					PurchaseOrderTab.display_po(PurchaseOrderTab.modelPO, PurchaseOrderTab.rowheaderPO, set_po_no, PurchaseOrderTab.cmbtype.getSelectedIndex());
					
				}else {
					
					System.out.println("Edit P.O.");
					pgUpdate db = new pgUpdate();
					updatePO(PurchaseOrderTab.lookuppono.getValue(), db);
					PurchaseOrderTab.save_purchase_order( PurchaseOrderTab.lookuppono.getValue() );
					
					db.commit();
					JOptionPane.showMessageDialog(getTopLevelAncestor(), "Edited purchase order saved.", "Saving", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("Po no after edit: "+po_no);
					PurchaseOrderTab.lookuppono.setValue(po_no);
					PurchaseOrderTab.display_po(PurchaseOrderTab.modelPO, PurchaseOrderTab.rowheaderPO, po_no, PurchaseOrderTab.cmbtype.getSelectedIndex());
					
				}
				
				//PurchaseOrderTab.save_purchase_order(set_po_no);
				PurchaseOrderTab.tblPO.setEditable(false);
				PurchaseOrderTab.tblPO.setEnabled(false);
				//FncTables.clearTable(PurchaseOrderTab.modelPO);
				//PurchaseOrderTab.cleartable_rowheader();
				PurchaseOrderTab.btnAddAcct.setEnabled(false);
				//PurchaseOrderTab.lookuppono.setValue("");
				PurchaseOrderTab.lookuppono.setEditable(true);
				PurchaseOrderTab.txtqoute.setEditable(false);
				PurchaseOrderTab.lookupterms.setEditable(false);
				PurchaseOrder.enable_buttons(true, true, false, false, true, false);
				enable_buttons(true, true, false, false, true, true);
			}
			
		}
		
		if ( e.getActionCommand().equals("cancel")){
			
			
			
			PurchaseOrderTab.tblPO.setEditable(false);
			PurchaseOrderTab.tblPO.setEnabled(false);
			
			FncTables.clearTable(PurchaseOrderTab.modelPO);
			PurchaseOrderTab.cleartable_rowheader();
			PurchaseOrderTab.btnAddAcct.setEnabled(false);
			
			lookupcompany.setValue(null);
			txtcompany.setText("");
			lookuprequester.setValue(null);
			txtrequester_name.setText("");
			txtdivdept.setText("");
			
			PurchaseOrderTab.cmbtype.setSelectedIndex(0);
			PurchaseOrderTab.cmbtype.setEditable(true);
			PurchaseOrderTab.lookuppono.setValue(null);
			PurchaseOrderTab.lookuppono.setEditable(true);
			PurchaseOrderTab.lookupsupplier.setEditable(false);
			PurchaseOrderTab.lookupsupplier.setValue(null);
			PurchaseOrderTab.tagsupplier.setTag("");
			PurchaseOrderTab.lookupterms.setValue(null);
			PurchaseOrderTab.lookupterms.setEditable(false);
			PurchaseOrderTab.date_PO.setEnabled(false);
			PurchaseOrderTab.date_PO.setEditable(false);
			PurchaseOrderTab.date_PO.setDate(null);
			PurchaseOrderTab.txtrplfno.setText("");
			PurchaseOrderTab.txtrplfno.setEditable(false);
			PurchaseOrderTab.txtqoute.setText("");
			PurchaseOrderTab.txtqoute.setEditable(false);
			
			enable_buttons(true, true, false, false, true, false);
			
		}
		
		if(e.getActionCommand().equals("preview")) {
			preview_supplies();
			enable_buttons(true, true, false, false, true,true);
		}
	}
	
	private void updatePO(String po_no, pgUpdate db) {

		String sqlDetail = "update rf_purchase_order set status_id = 'I', edited_by = '"+UserInfo.EmployeeCode+"', date_edited = now() where status_id = 'A' and po_no = '"+po_no+"' and co_id = '"+lookupcompany.getValue()+"' ";
		
		System.out.println("updatePO: " + sqlDetail);
		db.executeUpdate(sqlDetail, false);

	}
	
	public static void enable_buttons(Boolean bgenerate,Boolean badd, Boolean bedit, Boolean bsave, Boolean bcancel, Boolean bpreview ) {
		
		btngenerate.setEnabled(bgenerate);
		btnadd.setEnabled(badd);
		btnedit.setEnabled(bedit);
		btnsave.setEnabled(bsave);
		btncancel.setEnabled(bcancel);
		btnpreview.setEnabled(bpreview);
	}
	
	public static String get_requester() {
		 String sql="select a.emp_code, b.entity_name, c.exec_office_alias, d.div_alias, c.exec_office_alias||'/'||d.div_alias\n"
				+ "from rf_employee a \n"
				+ "left join rf_entity b on a.entity_id = b.entity_id\n"
				+ "left join mf_exec_office c on a.exec_off_code = exec_office_code \n"
				+ "left join mf_division d on a.div_code = d.div_code";
		 return sql;
	}
	
	public static  String getpo_no () {
		pgSelect db = new pgSelect();
		String sql = "select  trim(to_char(coalesce(max(po_no)::int,0) +1,'000000'))  from  rf_purchase_order";
		db.select(sql);
		return db.getResult()[0][0].toString();
	}
	
	public static String get_company() {//XXX Company
		String SQL = "SELECT TRIM(co_id)::VARCHAR as \"ID\", TRIM(company_name) as \"Company Name\", " +
				"/*TRIM(company_alias)::VARCHAR as \"Alias\",*/ company_logo as \"Logo\" FROM mf_company order by co_id ";
		return SQL;
	}
	
	public void generate_supplies_po( DefaultTableModel modelMain, JList rowHeader ) {
		FncTables.clearTable(modelMain);
		DefaultListModel listModel = new DefaultListModel();
		rowHeader.setModel(listModel);
		
		String sql = "select true,null,ofc_supply_id, ofc_supply_name, null, null, null, null, null, null \n"
				+ "from mf_office_supplies a \n"
				+ "where status_id = 'A' and min_supply_count <= supply_count ";
		
		System.out.printf("generate_supplies_po: %s", sql);
		pgSelect db = new pgSelect();
		db.select(sql);
		
		if (db.isNotNull()) {
			for (int x = 0; x < db.getRowCount(); x++) {
				modelMain.addRow(db.getResult()[x]);
				listModel.addElement(modelMain.getRowCount());
			}
		}
	}
	
	private void preview_supplies () {
		
		System.out.println("preview_supplies");
		
		Map<String, Object> mapParameters = new HashMap<String, Object>();
		mapParameters.put("co_logo", this.getClass().getClassLoader().getResourceAsStream("Images/arc-logo.png"));
		mapParameters.put("co_name", txtcompany.getText());
		mapParameters.put("req_id", "");
		mapParameters.put("po_no", PurchaseOrderTab.lookuppono.getValue());
		mapParameters.put("po_date", PurchaseOrderTab.date_PO.getDate());
		mapParameters.put("prepared_by",UserInfo.FullName);
		mapParameters.put("printed_by", UserInfo.FullName);
		
		System.out.println("co_name: "+ txtcompany.getText());
		System.out.println("po_no: "+ PurchaseOrderTab.lookuppono.getValue());
		System.out.println("po_date: "+ PurchaseOrderTab.date_PO.getDate());
		System.out.println("Printed by: "+ UserInfo.FullName);
		
		//FncReport.generateReport("/Reports/rptPO.jasper", "Purchase Order Supplies", mapParameters);
		FncReport.generateReport("/Reports/rptPurchaseOrder.jasper", "Purchase Order Supplies", mapParameters);
	}
}
