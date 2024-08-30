package Accounting.FixedAssets;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	private JButton btncancel;
	private JButton btnpreview;
	private JButton btnsave;
	private JButton btndelete;
	private JButton btnedit;
	private JButton btnadd;
	private JTabbedPane tabcenter;
	private JTextField txtdivdept;
	private JTextField txtrequester_name;
	private _JLookup lookuprequester;
	private JTextField txtcompany;
	private _JLookup lookupcompany;
	private PurchaseOrderTab pnlpurchaseordertab;

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
							lookupcompany.setLookupSQL(SQL_COMPANY());
							lookupcompany.setReturnColumn(0);
							lookupcompany.setEditable(false);
							lookupcompany.addLookupListener(new LookupListener() {
								public void lookupPerformed(LookupEvent event) {
									Object[] data = ((_JLookup)event.getSource()).getDataSet();
									if(data != null) {
										
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
							lookuprequester.setReturnColumn(1);
							lookuprequester.setEnabled(false);
							lookuprequester.addLookupListener(new LookupListener() {
								public void lookupPerformed(LookupEvent event) {
									Object [] data = ((_JLookup)event.getSource()).getDataSet();
									
									if(data != null) {
										
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
							JLabel lbldivdept= new JLabel("Div/Dept");
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
				
				{
					pnlpurchaseordertab = new PurchaseOrderTab();
					tabcenter.addTab("Purchase Order", null, pnlpurchaseordertab, null);
				}
				
				
				
				tabcenter.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						int selectedTab = ((JTabbedPane)arg0.getSource()).getSelectedIndex();
						
						if (selectedTab == 0) {
							
							
							
						}
					}
				});
				
				
			}
			{
				JPanel pnlsouth=new JPanel(new GridLayout(1, 6, 3, 3));
				pnlmain.add(pnlsouth, BorderLayout.SOUTH);
				pnlsouth.setPreferredSize(new Dimension(0, 30));
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
					btnedit.setActionCommand("EDIT");
					btnedit.setEnabled(false);
					btnedit.addActionListener(this);
				}
				{
					btndelete = new JButton("Delete");
					pnlsouth.add(btndelete);
					btndelete.setActionCommand("DELETE");
					btndelete.setEnabled(false);
					btndelete.addActionListener(this);
				}
				{
					btnsave = new JButton("Submit");
					pnlsouth.add(btnsave);
					btnsave.setActionCommand("Save");
					btnsave.setEnabled(false);
					btnsave.addActionListener(this);
				}
				{
					btncancel = new JButton("Cancel");
					pnlsouth.add(btncancel);
					btncancel.setActionCommand("Cancel");
					btncancel.setEnabled(false);
					btncancel.addActionListener(this);
				}
				{
					btnpreview = new JButton("Preview");
					pnlsouth.add(btnpreview);
					btnpreview.setActionCommand("PREVIEW");
					btnpreview.setEnabled(false);
					btnpreview.addActionListener(this);
				}
			}
		}
		
	}

}
