package Accounting.OfficeSupplies;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
import Database.pgUpdate;
import Functions.FncTables;
import Functions.UserInfo;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import components.JTBorderFactory;
import components._JInternalFrame;
import components._JTableMain;
import components._JTagLabel;
import interfaces._GUI;
import tablemodel.modeladditem_supplies;

public class additem_supplies extends _JInternalFrame implements _GUI, ActionListener {

	private static final long serialVersionUID = 1L;
	
	public static String title = "Add Item";
	public static Dimension framesize= new Dimension(600, 500);

	private JButton btnnew;

	private JButton btnsave;

	private JButton btnedit;

	private JButton btncancel;

	private JTextField txtitemname;

	private JTextField txtitemid;

	private JTextField txtcategoryid;

	private _JLookup lookupcategory;

	private JScrollPane scrollitem_supplies;

	private modeladditem_supplies modeladditemsupplies;

	private _JTableMain tbladditemsupplies;

	private JList rowheaderadditemsupplies;
	
	protected ButtonGroup grpNE = new ButtonGroup();

	protected Integer cat_id;

	public additem_supplies() {
		super(title, true, true, true, true);
		initGUI();
		displayitemsupplies(modeladditemsupplies, rowheaderadditemsupplies);
	}

	public additem_supplies(String title) {
		super(title);
	}

	public additem_supplies(String title, boolean resizable, boolean closable, boolean maximizable,
			boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
	}

	@Override
	public void initGUI() {
		this.setTitle(title);
		this.setSize(framesize);
		this.setPreferredSize(framesize);
		this.setLayout(new BorderLayout());
		{
			JPanel pnlmain= new JPanel(new BorderLayout(10, 10));
			getContentPane().add(pnlmain);
			{
				JPanel pnlnorth = new JPanel(new BorderLayout(5, 5));
				pnlmain.add(pnlnorth, BorderLayout.NORTH);		
				pnlnorth.setPreferredSize(new Dimension(0, 260));
				pnlnorth.setBorder(JTBorderFactory.createTitleBorder(""));
				pnlnorth.setBackground(Color.DARK_GRAY);
				{
					scrollitem_supplies = new JScrollPane();
					pnlnorth.add(scrollitem_supplies);
					scrollitem_supplies.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					{
						modeladditemsupplies = new modeladditem_supplies();
						tbladditemsupplies = new _JTableMain(modeladditemsupplies);
						scrollitem_supplies.setViewportView(tbladditemsupplies);
						modeladditemsupplies.setEditable(false);
						tbladditemsupplies.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent e) {
								if(!e.getValueIsAdjusting()) {
									int row = tbladditemsupplies.getSelectedRow();
									Integer supply_id = (Integer) modeladditemsupplies.getValueAt(row, 0);
									displayitemdetails(supply_id);
									buttonstate(true, false, true, true);
									System.out.println("supply_id: "+supply_id);
								}
							}
						});
					}
					{
						rowheaderadditemsupplies = tbladditemsupplies.getRowHeader();
						scrollitem_supplies.setRowHeaderView(rowheaderadditemsupplies);
						scrollitem_supplies.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
					}
				}
			}
			{
				JPanel pnlcenter = new JPanel(new GridLayout(3, 1, 3, 3));
				pnlmain.add(pnlcenter, BorderLayout.CENTER);
				pnlcenter.setBorder(JTBorderFactory.createTitleBorder("Item Detail"));
				{
					JPanel pnlcenter1 = new JPanel(new BorderLayout(5, 5));
					pnlcenter.add(pnlcenter1);
					//pnlcenter1.setBackground(Color.blue);
					{
						JLabel lblcategory = new JLabel("Category");
						pnlcenter1.add(lblcategory, BorderLayout.WEST);
						lblcategory.setPreferredSize(new Dimension(70, 0));
					}
					{
						lookupcategory = new _JLookup();
						lookupcategory.setEditable(false);
						lookupcategory.setReturnColumn(0);
						lookupcategory.setLookupSQL(getcategory());
						pnlcenter1.add(lookupcategory, BorderLayout.CENTER);
						lookupcategory.addLookupListener(new LookupListener() {
							public void lookupPerformed(LookupEvent event) {
								Object[] data = ((_JLookup) event.getSource()).getDataSet();
								if (data != null) {
									cat_id = (Integer) data[0];
									String category_name = (String) data[1];
									txtcategoryid.setText(category_name);
								}
							}
						});
					}
					{
						txtcategoryid = new JTextField();
						pnlcenter1.add(txtcategoryid, BorderLayout.EAST);
						txtcategoryid.setPreferredSize(new Dimension(400, 0));
						txtcategoryid.setEditable(false);
					}
				}
				{
					JPanel pnlcenter2 = new JPanel(new BorderLayout(5, 5));
					pnlcenter.add(pnlcenter2);
					//pnlcenter2.setBackground(Color.BLACK);
					{
						JLabel lblitemid = new JLabel("Item ID");
						pnlcenter2.add(lblitemid, BorderLayout.WEST);
						lblitemid.setPreferredSize(new Dimension(70, 0));
					}
					{
						txtitemid = new JTextField();
						pnlcenter2.add(txtitemid, BorderLayout.CENTER);
						txtitemid.setHorizontalAlignment(JTextField.CENTER);
						txtitemid.setPreferredSize(new Dimension(100, 0));
						txtitemid.setEditable(false);
					}
					{
						JLabel lbl = new JLabel("");
						pnlcenter2.add(lbl, BorderLayout.EAST);
						lbl.setPreferredSize(new Dimension(400, 0));
					}
				}
				{
					JPanel pnlcenter3 = new JPanel(new BorderLayout(5, 5));
					pnlcenter.add(pnlcenter3);
					{
						JLabel lblitemname = new JLabel("Item Name");
						pnlcenter3.add(lblitemname, BorderLayout.WEST);
						lblitemname.setPreferredSize(new Dimension(70, 0));
					}
					{
						txtitemname = new JTextField();
						pnlcenter3.add(txtitemname, BorderLayout.CENTER);
						txtitemname.setEditable(false);
					}
				}
			}
			{
				JPanel pnlsouth = new JPanel(new GridLayout(1, 4, 5, 5));
				pnlmain.add(pnlsouth, BorderLayout.SOUTH);
				pnlsouth.setPreferredSize(new Dimension(0, 45));
				//pnlsouth.setBackground(Color.cyan);
				{
					btnnew = new JButton("New");
					pnlsouth.add(btnnew);
					btnnew.setActionCommand("new");
					btnnew.addActionListener(this);
				}
				{
					btnsave = new JButton("Save");
					pnlsouth.add(btnsave);
					btnsave.setActionCommand("save");
					btnsave.addActionListener(this);
					grpNE.add(btnsave);
				}
				{
					btnedit = new JButton("Edit");
					pnlsouth.add(btnedit);
					btnedit.setActionCommand("edit");
					btnedit.addActionListener(this);
					grpNE.add(btnedit);
				}
				{
					btncancel = new JButton("Cancel");
					pnlsouth.add(btncancel);
					btncancel.setActionCommand("cancel");
					btncancel.addActionListener(this);
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("new")) {
			lookupcategory.setValue(null);
			txtcategoryid.setText("");
			txtitemid.setText("");
			txtitemname.setText("");
			lookupcategory.setEditable(true);
			txtitemname.setEditable(true);
			buttonstate(false, true, false, true);
			tbladditemsupplies.setEnabled(false);
			grpNE.setSelected(btnnew.getModel(), true);
			
		}
		
		if(e.getActionCommand().equals("save")) {
			if(grpNE.isSelected(btnnew.getModel())) {
				System.out.println("selected new");
				addItem(txtitemname.getText(), cat_id);
			}
			
			if(grpNE.isSelected(btnedit.getModel())) {
				System.out.println("selected edit");
			}
			buttonstate(true, false, false, true);
			displayitemsupplies(modeladditemsupplies, rowheaderadditemsupplies);
		}
		
		if(e.getActionCommand().equals("edit")) {
			grpNE.setSelected(btnedit.getModel(), true);
			buttonstate(false, true, false, true);
		}	
		
		if(e.getActionCommand().equals("cancel")) {
			lookupcategory.setValue(null);
			txtcategoryid.setText("");
			txtitemid.setText("");
			txtitemname.setText("");
			lookupcategory.setEditable(false);
			txtitemname.setEditable(false);
			buttonstate(true, false, false, true);
			tbladditemsupplies.setEnabled(true);
		}
	}
	
	public void buttonstate (Boolean addnew, Boolean save, Boolean edit, Boolean cancel) {
		btnnew.setEnabled(addnew);
		btnsave.setEnabled(save);
		btnedit.setEnabled(edit);
		btncancel.setEnabled(cancel);
	}
	
	public  String getcategory() {
		return "select ofc_supply_category_id, ofc_supply_category_name, status_id\n"
				+ "from mf_office_supplies_category\n"
				+ "where status_id = 'A'";
	}
	
	public void displayitemsupplies(DefaultTableModel modelMain, JList rowHeader) {
		
		FncTables.clearTable(modelMain);
		DefaultListModel listModel = new DefaultListModel();
		rowHeader.setModel(listModel);
		
		String sql = "select a.ofc_supply_id, a.ofc_supply_name, b.ofc_supply_category_name, b.ofc_supply_category_id\n"
				+ "from mf_office_supplies a\n"
				+ "left join mf_office_supplies_category b on a.ofc_supply_category = b.ofc_supply_category_id\n"
				+ "where a.status_id = 'A'";
		
		System.out.printf("displayitemsupplies: %s", sql);
		pgSelect db = new pgSelect();
		db.select(sql);
		
		if (db.isNotNull()) {
			for (int x = 0; x < db.getRowCount(); x++) {
				modelMain.addRow(db.getResult()[x]);
				listModel.addElement(modelMain.getRowCount());
			}
		}
		tbladditemsupplies.packAll();
	}
	
	public   void displayitemdetails(Integer item_id){
		String sql = "select a.ofc_supply_id::varchar, a.ofc_supply_name, b.ofc_supply_category_name, b.ofc_supply_category_id::varchar \n"
				+ "from mf_office_supplies a\n"
				+ "left join mf_office_supplies_category b on a.ofc_supply_category = b.ofc_supply_category_id\n"
				+ "where a.status_id = 'A' and a.ofc_supply_id = "+item_id+"";
		
		System.out.printf("displayitemdetails: %s", sql);
		pgSelect db = new pgSelect();
		db.select(sql);
		
		if (db.isNotNull()) {
			lookupcategory.setValue((String) db.getResult()[0][3]);
			txtcategoryid.setText((String) db.getResult()[0][2]);
			txtitemid.setText((String) db.getResult()[0][0]);
			txtitemname.setText((String) db.getResult()[0][1]);
		}
	}
	
	public static void addItem( String item_name, Integer category_id){
		pgUpdate db= new pgUpdate();
		String strSQL =
			"INSERT INTO mf_office_supplies( \n" +
			"ofc_supply_name, \n" +
			"ofc_supply_category, min_supply_count, supply_count, status_id, created_by, date_created) \n" +
			"VALUES (" +
			"'"+item_name+"', \n" +
			""+category_id+", 1, 0, 'A', '"+UserInfo.EmployeeCode+"', now()) ";
		
		db.executeUpdate(strSQL,false);
		db.commit();
	}
}
