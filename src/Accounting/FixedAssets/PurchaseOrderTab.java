package Accounting.FixedAssets;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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
import components._JInternalFrame;
import components._JTableMain;
import components._JTagLabel;
import interfaces._GUI;
import tablemodel.modelpurchase_order;

public class PurchaseOrderTab extends JPanel implements _GUI, ActionListener {

	private static final long serialVersionUID = 1L;
	private _JLookup lookcanvassid;
	private JTextField txtPR_No;
	private _JLookup lookupterms;
	private _JDateChooser date_PO;
	private JScrollPane scrollPO;
	private modelpurchase_order modelPO;
	private _JTableMain tblPO;
	private JList rowheaderPO;

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
						 JLabel lblcanvassid = new JLabel("Canvass ID");
						 pnlcanvassid.add(lblcanvassid, BorderLayout.WEST);
						 lblcanvassid.setPreferredSize(new Dimension(70, 0));
					 }
					 {
						 lookcanvassid = new _JLookup();
						 //lookcanvassid.setLookupSQL(getcanvass_id());
						 lookcanvassid.setReturnColumn(0);
						 pnlcanvassid.add(lookcanvassid, BorderLayout.CENTER);
						 lookcanvassid.addLookupListener(new LookupListener() {
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
							  JLabel lblPO_No = new JLabel("PR No."); 
							  pnlcanvassid_east.add(lblPO_No,BorderLayout.CENTER); 
						  } 
						  {
								 txtPR_No = new JTextField();
								 txtPR_No.setEnabled(false);
								 pnlcanvassid_east.add(txtPR_No, BorderLayout.EAST);
								 txtPR_No.setPreferredSize(new Dimension(110, 0)); 
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
						 //lookupterms.setLookupSQL(get_terms());
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
						tblPO.getColumnModel().getColumn(10).setPreferredWidth(100);
					}
					{
						rowheaderPO = tblPO.getRowHeader();
						scrollPO.setRowHeaderView(rowheaderPO);
						scrollPO.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
					}
				}
			}
		}

	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
