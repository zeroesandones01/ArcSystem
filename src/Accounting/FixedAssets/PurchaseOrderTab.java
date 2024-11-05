package Accounting.FixedAssets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Database.pgSelect;
import DateChooser._JDateChooser;
import Functions.FncGlobal;
import Functions.FncSystem;
import Functions.FncTables;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import Lookup._JLookupTable;
import components.JTBorderFactory;
import components._JTableMain;
import components._JTagLabel;
import interfaces._GUI;
import tablemodel.modelpurchase_order;

public class PurchaseOrderTab extends JPanel implements _GUI, ActionListener, MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;
	public static _JLookup lookupterms;
	public static _JDateChooser date_PO;
	private JScrollPane scrollPO;
	public static modelpurchase_order modelPO;
	public static _JTableMain tblPO;
	public static _JLookup lookuppono;
	private static JTextField txtrplfno;
	public static _JLookup lookupsupplier;
	public static _JTagLabel tagsupplier;
	public static JComboBox cmbtype;
	public static JList rowheaderPO;
	public static JButton btnAddAcct;

	public PurchaseOrderTab() {
		initGUI();
		lookuppono.setLookupSQL(get_po(0, PurchaseOrder.lookupcompany.getValue(), PurchaseOrder.lookuprequester.getValue()));
	}

	public PurchaseOrderTab(LayoutManager layout) {
		super(layout);
		initGUI();
	}

	public PurchaseOrderTab(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		initGUI();
	}

	public PurchaseOrderTab(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		initGUI();
	}

	@Override
	public void initGUI() {

		this.setLayout(new BorderLayout(5, 5));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 3, 3));
		{
			JPanel panelpurchaseorder= new JPanel(new BorderLayout(5, 5));
			this.add(panelpurchaseorder, BorderLayout.CENTER);
			
			{
				 JPanel pnlPO_north = new JPanel(new GridLayout(4, 1, 5, 5));
				 panelpurchaseorder.add(pnlPO_north, BorderLayout.NORTH);
				 pnlPO_north.setBorder(JTBorderFactory.createTitleBorder(""));
				 pnlPO_north.setPreferredSize(new Dimension(0,130));
				 {
					 JPanel pnlcombo = new JPanel(new BorderLayout(5, 5));
					 pnlPO_north.add(pnlcombo);
					 {
						 JLabel lblcombo = new JLabel("Type");
						 pnlcombo.add(lblcombo, BorderLayout.WEST);
						 lblcombo.setPreferredSize(new Dimension(70, 0));
					 }
					 {
						 String [] types = {"Supplies","Fixed Asset"};
						 cmbtype = new JComboBox(types);
						 cmbtype.setSelectedIndex(0);
						 pnlcombo.add(cmbtype, BorderLayout.CENTER);
						 cmbtype.addItemListener(new ItemListener() {
							
							@Override
							public void itemStateChanged(ItemEvent e) {
								// TODO Auto-generated method stub
								System.out.println("Index: "+cmbtype.getSelectedIndex());
								lookuppono.setLookupSQL(get_po(cmbtype.getSelectedIndex(), PurchaseOrder.lookupcompany.getValue(), PurchaseOrder.lookuprequester.getValue()));
								
								tblPO.setEditable(false);
								tblPO.setEnabled(false);
								FncTables.clearTable(PurchaseOrderTab.modelPO);
								cleartable_rowheader();
								btnAddAcct.setEnabled(false);
								lookuppono.setValue("");
								PurchaseOrder.enable_buttons(true, true, false, false, true, false);
								
								PurchaseOrder.lookupcompany.setValue("");
								PurchaseOrder.txtcompany.setText("");
								PurchaseOrder.lookuprequester.setValue("");
								PurchaseOrder.txtrequester_name.setText("");
								
								lookupsupplier.setValue("");
								tagsupplier.setTag("");
								lookupterms.setValue("");
								txtrplfno.setText("");
								date_PO.setDate(null);
								
							}
						});
					 }
					 {
						 JPanel pnlcombo_east = new JPanel(new BorderLayout(5, 5));
						 pnlcombo.add(pnlcombo_east, BorderLayout.EAST);
						 pnlcombo_east.setPreferredSize(new Dimension(480, 0));
						 {
							 JLabel lbldate = new JLabel("Date   ", JLabel.TRAILING);
							 pnlcombo_east.add(lbldate, BorderLayout.CENTER);
						 }
						 {
							 date_PO = new _JDateChooser("MM/dd/yyyy", "##/##/#####", '_');
							 pnlcombo_east.add(date_PO, BorderLayout.EAST);
							 date_PO.setPreferredSize(new Dimension(110, 0));
						 }
						 
					 }
				 }
				 {
					 JPanel pnlpono= new JPanel(new BorderLayout(5, 5));
					 pnlPO_north.add(pnlpono);
					 {
						 JLabel lblpono = new JLabel("PO NO.");
						 pnlpono.add(lblpono, BorderLayout.WEST);
						 lblpono.setPreferredSize(new Dimension(70, 0));
					 }
					 {
						 lookuppono = new _JLookup();
						 lookuppono.setReturnColumn(0);
						 pnlpono.add(lookuppono, BorderLayout.CENTER);
						 lookuppono.addLookupListener(new LookupListener() {
							public void lookupPerformed(LookupEvent event) {
								Object [] data = ((_JLookup)event.getSource()).getDataSet();
								
								if(data !=null) {
									
									display_po(modelPO, rowheaderPO, data[0].toString(), cmbtype.getSelectedIndex());
									PurchaseOrder.enable_buttons(false, false, true, false, true, true);
									PurchaseOrder.lookupcompany.setValue(data[7].toString());
									PurchaseOrder.txtcompany.setText((String)data[8]);
									PurchaseOrder.lookuprequester.setValue((String)data[1]);
									PurchaseOrder.txtrequester_name.setText((String)data[2]);
									
									lookupsupplier.setValue((String)data[3]);
									tagsupplier.setTag((String)data[4]);
									lookupterms.setValue((String)data[5]);
									txtrplfno.setText((String)data[9]);
									date_PO.setDate((Date) data[6]);
									
								}
							}
						});
					 }
					 { 
						  JPanel pnlcanvassid_east = new JPanel(new BorderLayout(5, 5));
						  pnlpono.add(pnlcanvassid_east, BorderLayout.EAST);
						  pnlcanvassid_east.setPreferredSize(new Dimension(500, 0)); 
						  {
							  	
							  JPanel pnlcanvassid_east_west = new JPanel(new BorderLayout(5, 5));
							  pnlcanvassid_east.add(pnlcanvassid_east_west, BorderLayout.WEST);
							  pnlcanvassid_east_west.setPreferredSize(new Dimension(330, 0)); 
						  } 
						  { 
							  JLabel lblrplfno = new JLabel("RPLF NO."); 
							  pnlcanvassid_east.add(lblrplfno,BorderLayout.CENTER); 
						  } 
						  {
								 txtrplfno = new JTextField();
								 txtrplfno.setEnabled(false);
								 pnlcanvassid_east.add(txtrplfno, BorderLayout.EAST);
								 txtrplfno.setPreferredSize(new Dimension(110, 0)); 
						  }
					}
				 }
				 {
					 JPanel pnlsupplier = new JPanel(new BorderLayout(5, 5));
					 pnlPO_north.add(pnlsupplier);
					 {
						 JLabel lblsupplier = new JLabel("Supplier");
						 pnlsupplier.add(lblsupplier, BorderLayout.WEST);
						 lblsupplier.setPreferredSize(new Dimension(70, 0));
					 }
					 {
						 lookupsupplier = new _JLookup();
						 pnlsupplier.add(lookupsupplier, BorderLayout.CENTER);
					 }
					 {
						 JPanel pnlsupplier_east = new JPanel(new BorderLayout(5, 5));
						 pnlsupplier.add(pnlsupplier_east, BorderLayout.EAST);
						 pnlsupplier_east.setPreferredSize(new Dimension(500, 0));
						 {
							 tagsupplier = new _JTagLabel("[ ]");
							 pnlsupplier_east.add(tagsupplier, BorderLayout.CENTER);
						 }
					 }
				 }
				 {
					 JPanel pnlterm = new JPanel(new BorderLayout(5, 5));
					 pnlPO_north.add(pnlterm);
					 {
						 JLabel lblPR_No = new JLabel("Terms   ", JLabel.LEADING);
						 pnlterm.add(lblPR_No, BorderLayout.WEST);
						 lblPR_No.setPreferredSize(new Dimension(70, 0));
					 }
					 {
						 lookupterms = new _JLookup();
						 lookupterms.setReturnColumn(0);
						 lookupterms.setEditable(false);
						 pnlterm.add(lookupterms, BorderLayout.CENTER);
						 lookupterms.addLookupListener(new LookupListener() {
							public void lookupPerformed(LookupEvent event) {
								Object [] data = ((_JLookup)event.getSource()).getDataSet();
								
								if(data != null) {
									
								}
							}
						});
					 }
					 {
						 JPanel pnlsupplier_east = new JPanel(new BorderLayout(5, 5));
						 pnlterm.add(pnlsupplier_east, BorderLayout.EAST);
						 pnlsupplier_east.setPreferredSize(new Dimension(500, 0));
//						 {
//							 JLabel lbldate = new JLabel("Date   ", JLabel.TRAILING);
//							 pnlsupplier_east.add(lbldate, BorderLayout.CENTER);
//						 }
//						 {
//							 date_PO = new _JDateChooser("MM/dd/yyyy", "##/##/#####", '_');
//							 pnlsupplier_east.add(date_PO, BorderLayout.EAST);
//							 date_PO.setPreferredSize(new Dimension(110, 0));
//						 }
					 }
				}
			}
			{
				JPanel pnlPO_center = new JPanel(new BorderLayout(5,5));
				panelpurchaseorder.add(pnlPO_center, BorderLayout.CENTER);
				{
					scrollPO = new JScrollPane();
					pnlPO_center.add(scrollPO, BorderLayout.CENTER);
					scrollPO.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					{
						modelPO = new modelpurchase_order();
						tblPO = new _JTableMain(modelPO);
						scrollPO.setViewportView(tblPO);
						tblPO.getTableHeader().setEnabled(false);
						tblPO.setHorizontalScrollEnabled(true);
						tblPO.setEditable(false);
						tblPO.setEnabled(false);
						tblPO.addMouseListener(new MouseAdapter() {
							public void mousePressed(MouseEvent e) {
								if ((e.getClickCount() >= 2)) {	
									int column 	= tblPO.getSelectedColumn();
									System.out.println("column:: "+ column);
									if (column == 1) {selectsupplier();}
									if( column == 2) { selectitem(); }
									if( column == 6) {selectunit();}
									tblPO.packAll();
								}
							}
							public void mouseReleased(MouseEvent e) {
								if ((e.getClickCount() >= 2)) {	
									int column 	= tblPO.getSelectedColumn();
									if (column == 1) { selectsupplier();}
									if( column == 2) {selectitem();}
									if( column == 6) {selectunit();}
									tblPO.packAll();
								}
							}
						});
						
						tblPO.getColumnModel().getColumn(1).setPreferredWidth(100);
						tblPO.getColumnModel().getColumn(2).setPreferredWidth(75);
						tblPO.getColumnModel().getColumn(3).setPreferredWidth(250);
						tblPO.getColumnModel().getColumn(4).setPreferredWidth(150);
						tblPO.getColumnModel().getColumn(5).setPreferredWidth(150);
						tblPO.getColumnModel().getColumn(6).setPreferredWidth(100);
						tblPO.getColumnModel().getColumn(7).setPreferredWidth(50);
						tblPO.getColumnModel().getColumn(8).setPreferredWidth(100);
						tblPO.getColumnModel().getColumn(9).setPreferredWidth(100);
					}
					{
						rowheaderPO = tblPO.getRowHeader();
						scrollPO.setRowHeaderView(rowheaderPO);
						scrollPO.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
					}
					{
						scrollPO.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, displayTableNavigation());
					}
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Add Acct")) {
			add_row(); 
		}
	}
	
//	public static boolean checkdetails () {
//		if (date_PO.getDate() == null){
//			return false;
//		}else {
//			return true;
//		}
//	}
	
	public void display_po( DefaultTableModel modelMain, JList rowHeader, String po_no, Integer class_type) {
		FncTables.clearTable(modelMain);
		DefaultListModel listModel = new DefaultListModel();
		rowHeader.setModel(listModel);
		
		String sql = "select true, b.entity_name, \n"
				+ "a.item_id,\n"
				+ "a.description,\n"
				+ "nullif(a.model,'null') as model, NULLIF(a.brand,'null') as brand, \n"
				+ "NULLIF(a.unit,'null') as unit, NULLIF(a.quantity,'null'), NULLIF(a.purpose,'null') as purpose, a.unit_price, \n"
				+ "NULLIF(a.supplier,'null') as supplier, a.rec_id \n"
				+ "from rf_purchase_order a\n"
				+ "left join rf_entity b on a.supplier = b.entity_id\n"
				+ "where a.status_id = 'A' \n"
				+ "and a.po_no = '"+po_no+"' and a.classification_type = '"+class_type+"' \n"
				+ "order by rec_id ";
		
		System.out.printf("display_po: %s", sql);
		pgSelect db = new pgSelect();
		db.select(sql);
		
		if (db.isNotNull()) {
			for (int x = 0; x < db.getRowCount(); x++) {
				modelMain.addRow(db.getResult()[x]);
				listModel.addElement(modelMain.getRowCount());
			}
		}	
		tblPO.packAll();
	}
	public static void add_row() {
		
		modelPO.addRow(new Object [] {true,null,null,null,});
		((DefaultListModel) PurchaseOrderTab.rowheaderPO.getModel()).addElement(PurchaseOrderTab.modelPO.getRowCount());
	}
	
	public static void cleartable_rowheader() {
		 
		FncTables.clearTable(modelPO);//Code to clear model.
		DefaultListModel listModel = new DefaultListModel();//Creating listModel for rowHeader.
		rowheaderPO.setModel(listModel);//Setting of listModel into rowHeader.
	}

	private JPanel displayTableNavigation() {
		
		
		  btnAddAcct = new JButton(new
		  ImageIcon(this.getClass().getClassLoader().getResource(
		  "Images/Science-Plus2-Math-icon.png")));
		  btnAddAcct.setActionCommand("Add Acct");
		  btnAddAcct.setToolTipText("For multiple entry");
		  btnAddAcct.setEnabled(false); 
		  btnAddAcct.addActionListener(this);
		  
//		  btnRemoveAcct = new JButton(new
//		  ImageIcon(this.getClass().getClassLoader().getResource(
//		  "Images/Science-Minus2-Math-icon.png")));
//		  btnRemoveAcct.setActionCommand("Minus Acct");
//		  btnRemoveAcct.setToolTipText("For multiple entry");
//		  btnRemoveAcct.setEnabled(false); 
//		  btnRemoveAcct.addActionListener(this);
		  
		  
		  JPanel pnl = new JPanel(new GridLayout(1, 2)); 
		  pnl.add(btnAddAcct);
		  //pnl.add(btnRemoveAcct);
		 

		return pnl;
	}
	
	public void selectsupplier() {
		int column = tblPO.getSelectedColumn();
		int row = tblPO.getSelectedRow();
		
		if(column == 1) {
			_JLookupTable dlg = new _JLookupTable(FncGlobal.homeMDI, null, "Items", getsupplier(), false);
			dlg.setLocationRelativeTo(FncGlobal.homeMDI);
			dlg.setVisible(true);
			
			Object[] data = dlg.getReturnDataSet();
			if (data != null) {
				
				System.out.println("");
				System.out.println("row: "+row);
				System.out.println("column: "+column);
				System.out.println("item_id:"+data[0]);
				System.out.println("item_name:"+data[1]);
				
				modelPO.setValueAt(data[1], row, column);
				modelPO.setValueAt(data[0], row, column+9);
			}
			tblPO.packAll();
		}
		
	}
	
	public void selectitem() {
		int column = tblPO.getSelectedColumn();
		int row = tblPO.getSelectedRow();
		
		System.out.println("Type: "+ cmbtype.getSelectedIndex());
		if(cmbtype.getSelectedIndex() == 0) {
			if(column == 2) {
				_JLookupTable dlg = new _JLookupTable(FncGlobal.homeMDI, null, "Items", getitem(), false);
				dlg.setLocationRelativeTo(FncGlobal.homeMDI);
				dlg.setVisible(true);
				
				Object[] data = dlg.getReturnDataSet();
				if (data != null) {
					
					System.out.println("");
					System.out.println("row: "+row);
					System.out.println("column: "+column);
					System.out.println("item_id:"+data[0]);
					System.out.println("item_name:"+data[1]);
					
					modelPO.setValueAt(data[0], row, column);
					modelPO.setValueAt(data[1], row, column+1);
				}
				tblPO.packAll();
			}
			
		}else if (cmbtype.getSelectedIndex() == 1) {
			if(column == 2) {
				_JLookupTable dlg = new _JLookupTable(FncGlobal.homeMDI, null, "Items", getasset_item(), false);
				dlg.setLocationRelativeTo(FncGlobal.homeMDI);
				dlg.setVisible(true);
				
				Object[] data = dlg.getReturnDataSet();
				if (data != null) {
					
					System.out.println("");
					System.out.println("row: "+row);
					System.out.println("column: "+column);
					System.out.println("item_id:"+data[0]);
					System.out.println("item_name:"+data[1]);
					
					modelPO.setValueAt(data[0], row, column);
					modelPO.setValueAt(data[1], row, column+1);
				}
				tblPO.packAll();
			}
		}else {}
		
		
	}
	
	private void selectunit() {
		int column = tblPO.getSelectedColumn();
		int row = tblPO.getSelectedRow();
		if (column == 6){
			_JLookupTable dlg = new _JLookupTable(FncGlobal.homeMDI, null, "Items", getunit(), false);
			dlg.setLocationRelativeTo(FncGlobal.homeMDI);
			dlg.setVisible(true);
			
			Object[] data = dlg.getReturnDataSet();
			if (data != null) {
				modelPO.setValueAt(data[0], row, 6);
			}
		}
		
	}
	
	public static String get_po ( Integer class_type, String co_id, String requester) {
		String sql = "select distinct on(a.po_no)  a.po_no, a.requester, c.entity_name,  NULLIF(a.supplier,'null') as supplier, c.entity_name, NULLIF(a.terms,'null') as terms, a.po_date, a.co_id, e.company_name, a.rplf_no \n"
				+ "from rf_purchase_order a\n"
				+ "left join rf_employee b on a.requester = b.emp_code\n"
				+ "left join rf_entity c on b.entity_id = c.entity_id\n"
				+ "left join rf_entity d on a.supplier = d.entity_id \n" 
				+ "left join mf_company e on a.co_id = e.co_id\n"
				+ "where a.classification_type = '"+class_type+"' \n"
				+ "and case when '"+co_id+"'= '' or '"+co_id+"' = 'null' then true else a.co_id = '"+co_id+"' end \n"
				+ "and case when '"+requester+"'= '' or '"+requester+"' = 'null'  then true else a.requester = '"+requester+"' end\n"
						+ "";
		
		System.out.printf("get_po: %s", sql);
		return sql;
	}
	
	private String getunit() {
		String sql = "select 'PER PIECE'   union all\n" + 
				"select 'PER BOX'  union all\n" + 
				"select 'PER SET' union all\n" + 
				"select 'PER REAM' ";
		return sql;
	}
	
	private String getsupplier() {
		String sql = " select  entity_id, entity_name from rf_entity where status_id = 'A' ";
		
		System.out.printf("getsupplier: %s", sql);
		return sql;
	}
	
	private String getitem() {
		String sql = " select ofc_supply_id, ofc_supply_name \n"
				+ " from mf_office_supplies \n"
				+ " where status_id = 'A'";
		
		System.out.printf("getitem: %s", sql);
		return sql;
	}
	
	private String getasset_item() {
		String sql = "select item_id, item_name from mf_asset_item\n"
				+ "where status_id = 'A'";
		
		System.out.printf("getasset_item: %s", sql);
		return sql;
	
	}
	
	public static void save_purchase_order() {
		
		int row = tblPO.convertRowIndexToModel(tblPO.getSelectedRow());
//		Integer asset_no = (Integer) modelPO.getValueAt(row, 0);
		System.out.println("rowcount:"+modelPO.getRowCount());		
		for(int x = 0; x < modelPO.getRowCount(); x++) {
			
			
			Boolean selected = (Boolean) modelPO.getValueAt(x, 0);
			
			if(selected) {
				
				String supplier_name = (String) modelPO.getValueAt(x, 1);
				Integer item_id = (Integer) modelPO.getValueAt(x, 2);
				String item_name = (String) modelPO.getValueAt(x, 3);
				String model = (String) modelPO.getValueAt(x, 4);
				String brand = (String) modelPO.getValueAt(x, 5);
				String unit = (String) modelPO.getValueAt(x, 6);
				Integer qty = (Integer) modelPO.getValueAt(x, 7);
				String purpose = (String) modelPO.getValueAt(x, 8);
				Double price = Double.parseDouble (modelPO.getValueAt(x, 9).toString());
				String supp_id = (String) modelPO.getValueAt(x, 10);
				
				String comp_id = PurchaseOrder.lookupcompany.getValue();
				String comp_name = PurchaseOrder.txtcompany.getText();
				String requested_id = PurchaseOrder.lookuprequester.getValue();
				String requested_name = PurchaseOrder.txtrequester_name.getText();
				
				String supplier_id =lookupsupplier.getValue();
				//String supplier_name =tagsupplier.setTag("");
				String term =lookupterms.getValue();
				String rplf_no =txtrplfno.getText();
				Date po_date =date_PO.getDate();
				
				System.out.println("row_save: "+x);
				System.out.println("selected: "+selected);
				System.out.println("supplier_name: "+supplier_name);
				System.out.println("item_id: "+item_id);
				System.out.println("item_name: "+item_name);
				System.out.println("model: "+model);
				System.out.println("brand: "+brand);
				System.out.println("unit: "+unit);
				System.out.println("qty: "+qty);
				System.out.println("purpose: "+purpose);
				System.out.println("price: "+price);
				System.out.println("supp_id: "+supp_id);
				
				String sql = " select sp_save_purchase_order('"+comp_id+"', '"+requested_id+"', '"+cmbtype.getSelectedIndex()+"','"+item_id+"', '"+item_name+"',  NULLIF('"+model+"','null'), NULLIF('"+brand+"','null'), NULLIF('"+unit+"','null'), '"+qty+"', NULLIF('"+purpose+"','null'), '"+price+"', NULLIF('"+supp_id+"','null'), NULLIF('"+term+"','null'), null)\n"
						+ " ";
				
				FncSystem.out("save_purchase_order: ", sql);
				pgSelect db = new pgSelect();
				db.select(sql);
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
