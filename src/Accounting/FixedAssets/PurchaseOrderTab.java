package Accounting.FixedAssets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

import DateChooser._JDateChooser;
import Functions.FncGlobal;
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
	private JTextField txtrplfno;
	private _JLookup lookupsupplier;
	private _JTagLabel tagsupplier;
	public static JComboBox cmbtype;
	public static JList rowheaderPO;
	public static JButton btnAddAcct;

	public PurchaseOrderTab() {
		initGUI();
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
									if( column == 2) { selectitem(); }
									tblPO.packAll();
								}
							}
							public void mouseReleased(MouseEvent e) {
								if ((e.getClickCount() >= 2)) {	
									int column 	= tblPO.getSelectedColumn();
									if( column == 2) {selectitem();}
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
	
	public static boolean checkdetails () {
		if (date_PO.getDate().equals("null")){
			return false;
		}else {
			return true;
		}
	}
	
	public static void add_row() {
		
		modelPO.addRow(new Object [] {null,null,null,null,});
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
	
	public void selectitem() {
		int column = tblPO.getSelectedColumn();
		int row = tblPO.getSelectedRow();
		
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
		
	}
	
	private String getitem() {
		String sql = " select ofc_supply_id, ofc_supply_name \n"
				+ " from mf_office_supplies \n"
				+ " where status_id = 'A'";
		return sql;
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
