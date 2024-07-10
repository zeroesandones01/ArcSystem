package Accounting.FixedAssets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Database.pgSelect;
import Functions.FncSystem;
import Functions.FncTables;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import components.JTBorderFactory;
import components._JInternalFrame;
import components._JTableMain;
import components._JTagLabel;
import interfaces._GUI;
import tablemodel.modelasset_peripheral;
import tablemodel.modeltagging_peripheral;

public class addassetperipheral2 extends _JInternalFrame implements _GUI, ActionListener {

	private static final long serialVersionUID = 1L;
	static String title = "Add Asset Peripherals";
	public static Dimension frameSize = new Dimension(900, 500);
	private _JLookup lookupcomp;
	private _JTagLabel tagcomp;
	private JScrollPane scrollassetperipheral;
	private static modelasset_peripheral modelasset;
	private _JTableMain tblasset;
	protected String asset_no;
	private static JList rowheaderassetperipheral;
	private JScrollPane scrolltagperipheral;
	private static modeltagging_peripheral modeltagging;
	private _JTableMain tbltagperipheral;
	private JList rowheadertagging;
	private JButton btnnew;
	private JButton btnsave;
	private JButton btnedit;
	private JButton btncancel;
	protected String co_name;

	public addassetperipheral2() {
		super(title, true, true, true, true);
		initGUI();
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
						//lookupcomp.setEditable(false);
						lookupcomp.setLookupSQL("select '01', 'Acerland'");
						lookupcomp.addLookupListener(new LookupListener() {


							public void lookupPerformed(LookupEvent event) {
								Object[] data = ((_JLookup) event.getSource()).getDataSet();
								if(data != null) {
									co_name = (String)data[1];
									
									tagcomp.setTag(co_name);
									display_assets();
									modelasset.setEditable(true);
									tblasset.setEnabled(true);
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
								tblasset.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
									@Override
									public void valueChanged(ListSelectionEvent e) {
										if(!e.getValueIsAdjusting()) {
											try {
												int row = tblasset.getSelectedRow();
												asset_no = (String) modelasset.getValueAt(row, 1);
												//display_peripherals(modeltagging, rowheadertagging, asset_no);
												System.out.println("Value of Asset No.: "+asset_no);
												
											} catch (ArrayIndexOutOfBoundsException ex) {
												
											}
										}
										buttonstate(true, false, false, true);
									}
								});
								
								tblasset.getColumnModel().getColumn(0).setPreferredWidth(50);//Select
								tblasset.getColumnModel().getColumn(1).setPreferredWidth(150);//Asset No.
								tblasset.getColumnModel().getColumn(2).setPreferredWidth(250);//Asset Name
								tblasset.getColumnModel().getColumn(3).setPreferredWidth(350);//Custodian
								tblasset.getColumnModel().getColumn(4).setPreferredWidth(150);//Date Acquired
								
							}
							{
								rowheaderassetperipheral = tblasset.getRowHeader22(); 
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
									tbltagperipheral.hideColumns("Asset No.");
								}
								{
									rowheadertagging= tbltagperipheral.getRowHeader22();
									scrolltagperipheral.setRowHeaderView(rowheadertagging);
									scrolltagperipheral.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
								}
							}
						}
						{
							JPanel pnlselected_peripheral = new JPanel(new BorderLayout(5, 5));
							pnltagged.add(pnlselected_peripheral);
							pnlselected_peripheral.setBorder(JTBorderFactory.createTitleBorder("Tagged Peripheral"));
							
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
		if (e.getActionCommand().equals("new")) {
			if(asset_no != null) {
				
				display_peripherals(modeltagging, rowheadertagging, asset_no);
				modeltagging.setEditable(true);
				modelasset.setEditable(false);
				tblasset.setEnabled(false);
				buttonstate(false, true, false, true);
				
			}
		}
		
		if (e.getActionCommand().equals("save")) {
			
		}
		if(e.getActionCommand().equals("edit")) {
			
		}
		
		if(e.getActionCommand().equals("cancel")) {
			lookupcomp.setValue("");
			tagcomp.setTag("");
			FncTables.clearTable(modelasset);
			FncTables.clearTable(modeltagging);
			rowheaderassetperipheral = tblasset.getRowHeader22(); 
			scrollassetperipheral.setRowHeaderView(rowheaderassetperipheral);
			rowheadertagging= tbltagperipheral.getRowHeader22();
			scrolltagperipheral.setRowHeaderView(rowheadertagging);
			buttonstate(false, false, false, false);
			
		}
		
	}
	public void buttonstate(Boolean addnew, Boolean save,  Boolean edit,  Boolean cancel) {
		btnnew.setEnabled(addnew);
		btnsave.setEnabled(save);
		btnedit.setEnabled(edit);
		btncancel.setEnabled(cancel);
	}
	public static void display_assets() {
		FncTables.clearTable(modelasset);
		DefaultListModel listModel = new DefaultListModel();// Creating listModel for rowHeader.
		rowheaderassetperipheral.setModel(listModel);// Setting of listModel into rowHeader.
		
		String strSQL = "select false, a.asset_no::varchar, a.asset_name, 'Erick Bituen', a.date_acquired \n"
				+ "from tbl_asset a\n"
				+ "where a.status = 'A' \n"
				+ "and a.with_peripheral = true";
		
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
	
	public void display_peripherals(DefaultTableModel modelMain, JList rowHeader,String asset_no) {
		
		FncTables.clearTable(modelMain);
		DefaultListModel listModel = new DefaultListModel();
		rowheadertagging.setModel(listModel);
		
		String sql = "select false, '', category_id, category_name, '', 0.00, '','','','','','','',null,null  \n"
				+ "from rf_asset_peripheral_category where status_id = 'A'";
		
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
	public static void save_peripherals() {
		
		for(int x = 0; x < modeltagging.getRowCount(); x++) {
			
			Boolean selected = (Boolean) modeltagging.getValueAt(x, 0);
			String cat_id = (String) modeltagging.getValueAt(x, 2);
			String brand = (String) modeltagging.getValueAt(x, 6);
			String model = (String) modeltagging.getValueAt(x, 7);
			String description = (String) modeltagging.getValueAt(x, 8);
			String serial_no = (String) modeltagging.getValueAt(x, 9);
			String lic_key = (String) modeltagging.getValueAt(x, 10);
			if(selected) {
				
			}
		}
	}
}
