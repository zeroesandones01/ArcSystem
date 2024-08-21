package Accounting.OfficeSupplies;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTextField;

import Database.pgSelect;
import Functions.FncTables;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import components.JTBorderFactory;
import components._JButton;
import components._JInternalFrame;
import components._JScrollPaneMain;
import components._JTableMain;
import components._JTagLabel;
import interfaces._GUI;
import tablemodel.model_officesupplies;

public class OfficeSupplies extends _JInternalFrame implements _GUI, ActionListener {

	private static final long serialVersionUID = -951363391837678660L;
	static String title = "OFFICE SUPPLIES";
	public static Dimension frameSize = new Dimension(900, 500);
	private _JScrollPaneMain scrollofc_supply;
	private _JLookup lookupcategory;
	private _JTagLabel tagcategory;
	private _JButton btngen_order;
	private _JButton btnnew;
	private _JButton btnsave;
	private _JButton btncancel;
	private JXTextField txtsearch;
	private model_officesupplies modelofc_supply;
	private _JTableMain tblofc_supply;
	private JList rowheaderofc_supply;

	public OfficeSupplies() {
		super(title, true, true, true, true);
		initGUI();
	}

	public OfficeSupplies(String title) {
		super(title);
	}

	public OfficeSupplies(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
	}

	@Override
	public void initGUI() {
		this.setTitle(title);
		this.setSize(frameSize);
		this.setPreferredSize(frameSize);
		this.setLayout(new BorderLayout());
		{
			JPanel pnlmain = new JPanel(new BorderLayout(5, 5));
			this.add(pnlmain, BorderLayout.CENTER);
			pnlmain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			{
				JPanel pnlnorth = new JPanel(new GridLayout(1, 2, 3, 3));
				pnlmain.add(pnlnorth, BorderLayout.NORTH);
				pnlnorth.setPreferredSize(new Dimension(0, 100));
				//pnlnorth.setBorder(JTBorderFactory.createTitleBorder("S"));
				{
					JPanel pnlnorth_left = new JPanel(new GridLayout(2, 1, 3, 3));
					pnlnorth.add(pnlnorth_left);
					pnlnorth_left.setBorder(JTBorderFactory.createTitleBorder("Category"));
					{
						JPanel pnlnorth_left_1 = new JPanel(new BorderLayout(3, 3));
						pnlnorth_left.add(pnlnorth_left_1);
						{
							JLabel lblcategory = new JLabel("<html><font color='red'>Category</font></html>");
							pnlnorth_left_1.add(lblcategory, BorderLayout.WEST);
							lblcategory.setFont(new Font("Serif", Font.BOLD, 12));
							lblcategory.setPreferredSize(new Dimension(70, 0));
						}
						{
							lookupcategory = new _JLookup();
							pnlnorth_left_1.add(lookupcategory, BorderLayout.CENTER);
							lookupcategory.setReturnColumn(0);
							lookupcategory.setLookupSQL(getcategory());
							lookupcategory.addLookupListener(new LookupListener() {
								public void lookupPerformed(LookupEvent event) {
									Object[] data = ((_JLookup) event.getSource()).getDataSet();
									if(data != null) {
										Integer cat_id = (Integer) data[0];
										String category_name = (String) data[1];
										tagcategory.setTag(category_name);
										displaysupplies(modelofc_supply, rowheaderofc_supply, cat_id);
										txtsearch.setEditable(true);
										btnstate(false, true, false, true);
									}
								}
							});
						}
						{
							tagcategory = new _JTagLabel("[ ]");
							pnlnorth_left_1.add(tagcategory, BorderLayout.EAST);
							tagcategory.setPreferredSize(new Dimension(300, 0));
							tagcategory.setEditable(false);
						}
					}
					{
						JPanel pnlnorth_left_2 = new JPanel(new BorderLayout(3, 3));
						pnlnorth_left.add(pnlnorth_left_2);
//						{
//							JLabel lbltest = new JLabel("Testt");
//							lbltest.setFont(new Font("Serif", Font.BOLD, 14));
//							pnlnorth_left_2.add(lbltest, BorderLayout.CENTER);
//						}
					}
				}
				{
					JPanel pnlnorth_rigth = new JPanel(new GridLayout(2, 1, 3, 3));
					pnlnorth.add(pnlnorth_rigth);
					pnlnorth_rigth.setBorder(JTBorderFactory.createTitleBorder(""));
					{
						JPanel pnlnorth_rigth_1 = new JPanel(new BorderLayout(3, 3));
						pnlnorth_rigth.add(pnlnorth_rigth_1);
						{
							JLabel lblsearch = new JLabel("<html><font color='red'>Search</font></html>");
							pnlnorth_rigth_1.add(lblsearch, BorderLayout.WEST);
							lblsearch.setFont( new Font("Serif", Font.BOLD, 12) );
							lblsearch.setPreferredSize(new Dimension(70, 0));
						}
						{
							txtsearch = new JXTextField();
							pnlnorth_rigth_1.add(txtsearch, BorderLayout.CENTER);
							txtsearch.setHorizontalAlignment(JTextField.CENTER);
							txtsearch.setEditable(false);
							txtsearch.addKeyListener(new KeyAdapter() {
								public void keyReleased(KeyEvent e) {
									checkAllsupplies();
								}
							});
						}
					}
					{
						JPanel pnlnorth_rigth_2 = new JPanel(new BorderLayout(3, 3));
						pnlnorth_rigth.add(pnlnorth_rigth_2);
					}
				}
			}
			{
				JPanel pnlcenter = new JPanel(new BorderLayout(5, 5));
				pnlmain.add(pnlcenter, BorderLayout.CENTER);
				pnlcenter.setBorder(JTBorderFactory.createTitleBorder("Select Supplies"));
				{
					scrollofc_supply = new _JScrollPaneMain();
					pnlcenter.add(scrollofc_supply);
					scrollofc_supply.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					{
						modelofc_supply = new model_officesupplies();
						tblofc_supply = new _JTableMain(modelofc_supply);
						scrollofc_supply.setViewportView(tblofc_supply);
						
						tblofc_supply.getColumnModel().getColumn(1).setPreferredWidth(100);
						tblofc_supply.getColumnModel().getColumn(2).setPreferredWidth(300);
						tblofc_supply.getColumnModel().getColumn(3).setPreferredWidth(100);
						tblofc_supply.getColumnModel().getColumn(4).setPreferredWidth(100);
						tblofc_supply.getColumnModel().getColumn(5).setPreferredWidth(100);
						tblofc_supply.getColumnModel().getColumn(6).setPreferredWidth(300);
					}
				}
				{
					rowheaderofc_supply = tblofc_supply.getRowHeader(); 
					scrollofc_supply.setRowHeaderView(rowheaderofc_supply);
					scrollofc_supply.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
				}
			}
			{
				JPanel pnlsouth = new JPanel(new GridLayout(1, 4, 3, 3));
				pnlmain.add(pnlsouth, BorderLayout.SOUTH);
				pnlsouth.setPreferredSize(new Dimension(0, 45));
				{
					btngen_order = new _JButton("Generate");
					pnlsouth.add(btngen_order);
					btngen_order.setActionCommand("generate");
					btngen_order.addActionListener(this);
				}
				{
					btnnew = new _JButton("New");
					pnlsouth.add(btnnew);
					btnnew.setActionCommand("new");
					btnnew.addActionListener(this);
				}
				{
					btnsave = new _JButton("Save");
					pnlsouth.add(btnsave);
					btnsave.setActionCommand("save");
					btnsave.addActionListener(this);
				}
				{
					btncancel = new _JButton("Cancel");
					pnlsouth.add(btncancel);
					btncancel.setActionCommand("cancel");
					btncancel.addActionListener(this);
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("generate")) {
			
		}
		if (e.getActionCommand().equals("new")) {
			lookupcategory.setEditable(true);
			modelofc_supply.setEditable(true);
			btnstate(false, false, true, true);
		}
		if (e.getActionCommand().equals("save")) {
			btnstate(true, true, false, true);
		}
		if (e.getActionCommand().equals("cancel")) {
			lookupcategory.setValue(null);
			lookupcategory.setEditable(true);
			tagcategory.setTag("");
			txtsearch.setText("");
			txtsearch.setEditable(false);
			FncTables.clearTable(modelofc_supply);
			rowheaderofc_supply = tblofc_supply.getRowHeader(); 
			scrollofc_supply.setRowHeaderView(rowheaderofc_supply);
			modelofc_supply.setEditable(false);
			btnstate(true, true, false, true);
		}
	}
	
	private void btnstate (Boolean gen, Boolean addnew, Boolean save, Boolean cancel) {
		btngen_order.setEnabled(gen);
		btnnew.setEnabled(addnew);
		btnsave.setEnabled(save);
		btncancel.setEnabled(cancel);
		
	}
	
	private String getcategory () {
		return " select ofc_supply_category_id as \"ID\",\n"
				+ " ofc_supply_category_name as \"Name\",\n"
				+ " status_id as \"Status\"\n"
				+ " from mf_office_supplies_category \n"
				+ " where status_id = 'A'";
	}
	
	public void displaysupplies ( DefaultTableModel modelMain, JList rowHeader,Integer category ) {
		FncTables.clearTable(modelMain);
		DefaultListModel listModel = new DefaultListModel();
		rowHeader.setModel(listModel);
		
		String sql = "select false,ofc_supply_id, ofc_supply_name,'' as unit, supply_count,0 as quatity, '' as purpose \n"
				+ "from mf_office_supplies\n"
				+ " where ofc_supply_category = "+category+"\n"
				+ " and status_id = 'A'";
		
		System.out.printf("displaysupplies: %s", sql);
		pgSelect db = new pgSelect();
		db.select(sql);
		
		if (db.isNotNull()) {
			for (int x = 0; x < db.getRowCount(); x++) {
				modelMain.addRow(db.getResult()[x]);
				listModel.addElement(modelMain.getRowCount());
			}
		}
		tblofc_supply.packAll();
	}
	
	private void checkAllsupplies() {

		int rw = tblofc_supply.getModel().getRowCount();
		int x = 0;

		while (x < rw) {

			String name = "";

			try {
				name = tblofc_supply.getValueAt(x, 2).toString().toUpperCase();
			} catch (NullPointerException e) {
				name = "";
			}

			String acct_name = txtsearch.getText().trim().toUpperCase();
			Boolean match = name.indexOf(acct_name) > 0;
			Boolean start = name.startsWith(acct_name);
			Boolean end = name.endsWith(acct_name);

			if (match == true || start == true || end == true) {
				tblofc_supply.setRowSelectionInterval(x, x);
				tblofc_supply.changeSelection(x, 2, false, false);
				break;
			} else {
				tblofc_supply.setRowSelectionInterval(0, 0);
				tblofc_supply.changeSelection(0, 2, false, false);
			}

			x++;

		}
	}
}
