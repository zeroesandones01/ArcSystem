package Accounting.FixedAssets;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DateChooser._JDateChooser;
import Functions.FncTables;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import components._JTableMain;
import interfaces._GUI;
import tablemodel.modelpurchase_order;

public class PurchaseOrderTab extends JPanel implements _GUI, ActionListener {

	private static final long serialVersionUID = 1L;
	private _JLookup lookupterms;
	private _JDateChooser date_PO;
	private JScrollPane scrollPO;
	public static modelpurchase_order modelPO;
	private _JTableMain tblPO;
	public static _JLookup lookuppono;
	private JTextField txtrplfno;
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
				 JPanel pnlPO_north = new JPanel(new GridLayout(3, 1, 5, 5));
				 panelpurchaseorder.add(pnlPO_north, BorderLayout.NORTH);
				 pnlPO_north.setPreferredSize(new Dimension(0,90));
				 {
					 JPanel pnlcanvassid= new JPanel(new BorderLayout(5, 5));
					 pnlPO_north.add(pnlcanvassid);
					 {
						 JLabel lblpono = new JLabel("PO NO.");
						 pnlcanvassid.add(lblpono, BorderLayout.WEST);
						 lblpono.setPreferredSize(new Dimension(70, 0));
					 }
					 {
						 lookuppono = new _JLookup();
						 lookuppono.setReturnColumn(0);
						 pnlcanvassid.add(lookuppono, BorderLayout.CENTER);
						 lookuppono.addLookupListener(new LookupListener() {
							public void lookupPerformed(LookupEvent event) {
								Object [] data = ((_JLookup)event.getSource()).getDataSet();
								
								if(data !=null) {
									
								}
							}
						});
					 }
					 { JPanel pnlcanvassid_east = new JPanel(new BorderLayout(5, 5));
					  pnlcanvassid.add(pnlcanvassid_east, BorderLayout.EAST);
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
						 JLabel lblPR_No = new JLabel("Terms   ", JLabel.LEADING);
						 pnlsupplier.add(lblPR_No, BorderLayout.WEST);
						 lblPR_No.setPreferredSize(new Dimension(70, 0));
					 }
					 {
						 lookupterms = new _JLookup();
						 lookupterms.setReturnColumn(0);
						 lookupterms.setEditable(false);
						 pnlsupplier.add(lookupterms, BorderLayout.CENTER);
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
						 pnlsupplier.add(pnlsupplier_east, BorderLayout.EAST);
						 pnlsupplier_east.setPreferredSize(new Dimension(500, 0));
						 {
							 JLabel lbldate = new JLabel("Date   ", JLabel.TRAILING);
							 pnlsupplier_east.add(lbldate, BorderLayout.CENTER);
						 }
						 {
							 date_PO = new _JDateChooser("MM/dd/yyyy", "##/##/#####", '_');
							 pnlsupplier_east.add(date_PO, BorderLayout.EAST);
							 date_PO.setPreferredSize(new Dimension(110, 0));
						 }
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
						tblPO.packAll();
						tblPO.setHorizontalScrollEnabled(true);
						tblPO.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent e) {
								
								if(!e.getValueIsAdjusting()) {
									
									if(tblPO.getSelectedRows().length > 0) {
										
//									try {
//											int row= tblPO.getSelectedRow();
//											
//											print_po = (String) modelPO.getValueAt(row, 10);
//											
//										}catch (ArrayIndexOutOfBoundsException ex) { }
//										
//										if(po_no != null ) {
//											 procurement.enable_buttons(false, false, false, false, true, true);
//											 
//										 }else {
//											 procurement.enable_buttons(false, false, false, true, true, false);
//										 }
									}
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
						//tblPO.getColumnModel().getColumn(10).setPreferredWidth(100);
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
}
