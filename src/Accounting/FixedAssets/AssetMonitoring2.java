package Accounting.FixedAssets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Accounting.FixedAssets.panelAssetInformation2;
import Database.pgSelect;
import Functions.FncSystem;
import Functions.FncTables;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import components.JTBorderFactory;
import components._JInternalFrame;
import components._JTableMain;
import interfaces._GUI;
import tablemodel.modelAssetMonitoring;
import tablemodel.modelMovement;
import tablemodel.modelasset_peripheral;

public class AssetMonitoring2 extends _JInternalFrame implements _GUI, ActionListener {
	
	private static final long serialVersionUID = 1L;
	public static String title = "Asset Monitoring";
	public static Dimension frameSize = new Dimension(922, 630);
	public static Border lineBorder = BorderFactory.createLineBorder(Color.GRAY);
	private JTabbedPane tabAssets;
	public static _JLookup lookupselectcompany;
	public static JTextField txtcompany;
	public static _JLookup lookupCustodianid;
	public static JTextField txtCustodianid;
	public static JCheckBox chkinactiveemp;
	public static JCheckBox chkinactiveassets;
	private JScrollPane scrollAssets;
	public static modelAssetMonitoring modelAssets;
	public static _JTableMain tblAssets;
	private static JList rowheaderAssets;
	private panelAssetInformation2 pnlInformation;
	private JPanel pnlTransfer;
	private JPanel pnlNorth;
	private JTextField txtmovementno;
	private _JLookup lookupnewCustodian;
	private JTextField txtnewCustodian;
	public static _JLookup lookupLocation;
	public static JTextField txtLocation;
	protected String loc_id;
	private JTextField jtxtRemarks;
	private JScrollPane scrollMovement;
	private JPanel pnlsouthtransfer;
	private JButton btn1;
	private JButton btnDispose;
	private JButton btnRetire;
	private modelMovement modelmovement;
	private _JTableMain tblmovement;
	private JList rowheaderMovement;
	public static String co_id = "01";
	public static String co_name = "ACERLAND DEVELOPMENT CORPORATION";
	public static String co_logo = "cenqlogo.png";
	protected DefaultTableCellRenderer rendererCenterAlign = new DefaultTableCellRenderer();
	

	public AssetMonitoring2() {
		super(title, true, true, true, true);
		initGUI();
		displayAllAssets(false, false);
	}

	public AssetMonitoring2(String title) {
		super(title, true, true, true, true);
		initGUI();
	}

	public AssetMonitoring2(String title, boolean resizable, boolean closable, boolean maximizable,
			boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
		initGUI();
	}

	@Override
	public void initGUI() {

		this.setTitle(title);
		this.setSize(frameSize);
		this.setPreferredSize(frameSize);
		this.setLayout(new BorderLayout());

		{
			JPanel pnlMain = new JPanel(new BorderLayout(5, 5));
			getContentPane().add(pnlMain, BorderLayout.CENTER);
			pnlMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			{
				JPanel pnlNorth = new JPanel(new BorderLayout(5, 5));
				pnlMain.add(pnlNorth, BorderLayout.NORTH);
				pnlNorth.setBorder(new EmptyBorder(5, 5, 5, 5));
				pnlNorth.setPreferredSize(new Dimension(0, 60));
				{
					JPanel pnlfilters = new JPanel(new GridLayout(2, 1, 5, 5));
					pnlNorth.add(pnlfilters);
					{
						JPanel pnlcomp_filter = new JPanel(new BorderLayout(5, 5));
						pnlfilters.add(pnlcomp_filter, BorderLayout.WEST);
						{
							JLabel lblselectcompany = new JLabel("Company");
							pnlcomp_filter.add(lblselectcompany, BorderLayout.WEST);
							lblselectcompany.setPreferredSize(new Dimension(76, 0));
						}
						{
							lookupselectcompany = new _JLookup();
							pnlcomp_filter.add(lookupselectcompany, BorderLayout.CENTER); 
							lookupselectcompany.setValue(co_id);
							//lookupselectcompany.setLookupSQL(_AssetMonitoring.getCompany());
							lookupselectcompany.setLookupSQL(panelAssetInformation2.getcompany());
							lookupselectcompany.addLookupListener(new LookupListener() {
								public void lookupPerformed(LookupEvent event) {
									Object[] data = ((_JLookup) event.getSource()).getDataSet();
									if(data != null) {
										co_id = (String) data[0];
										co_name = (String) data[1];
										
										lookupselectcompany.setValue(co_id);
										txtcompany.setText(co_name);
										
										//panelAssetInformation2.lookupCustodian.setLookupSQL("select '900767', 'BITUEN, JOHN ERICK RESPICIO'");
										panelAssetInformation2.lookupCustodian.setLookupSQL(panelAssetInformation2.getCustodian());
									}
								}
							});
						}
						{
							JPanel pnlcomp_filter_names = new JPanel(new BorderLayout(5, 5));
							pnlcomp_filter.add(pnlcomp_filter_names, BorderLayout.EAST);
							pnlcomp_filter_names.setPreferredSize(new Dimension(725, 0));
							{
								txtcompany = new JTextField();
								pnlcomp_filter_names.add(txtcompany, BorderLayout.WEST);
								txtcompany.setPreferredSize(new Dimension(358, 0));
								txtcompany.setText(co_name);
							}
						}
					}
					{
						JPanel pnlcustodian = new JPanel(new BorderLayout(3, 3));
						pnlfilters.add(pnlcustodian, BorderLayout.WEST);
	 				    
						{
							JLabel lblcustodian = new JLabel("Custodian");
							pnlcustodian.add(lblcustodian, BorderLayout.WEST);
							lblcustodian.setPreferredSize(new Dimension(78, 0));
						}
						{
							JPanel pnlcustodian_filter = new JPanel(new BorderLayout(5, 5));
							pnlcustodian.add(pnlcustodian_filter, BorderLayout.CENTER);
							{
								lookupCustodianid = new _JLookup();
								pnlcustodian_filter.add(lookupCustodianid, BorderLayout.WEST);
								lookupCustodianid.setReturnColumn(0);
								lookupCustodianid.setLookupSQL(panelAssetInformation2.getCustodian());
								lookupCustodianid.setPreferredSize(new Dimension(80, 0));
								lookupCustodianid.addLookupListener(new LookupListener() {
									public void lookupPerformed(LookupEvent event) {
										Object[] data = ((_JLookup) event.getSource()).getDataSet();
										if (data != null) {
											String emp_code = (String) data[0];
											String cust_name = (String) data[1];
											txtCustodianid.setText(cust_name);
											
											displayIndividualAssets(modelAssets, rowheaderAssets,emp_code);
										}
									}
								});
							}
							{
								txtCustodianid = new JTextField();
								pnlcustodian_filter.add(txtCustodianid, BorderLayout.CENTER);
								txtCustodianid.setEditable(false);
							}
							{
								JPanel pnlcheckbox = new JPanel(new GridLayout(1, 2, 3, 3));
								pnlcustodian_filter.add(pnlcheckbox, BorderLayout.EAST);
								pnlcheckbox.setPreferredSize(new Dimension(365, 0));
								{
									chkinactiveemp = new JCheckBox("Include Inactive Employees");
									pnlcheckbox.add(chkinactiveemp, BorderLayout.WEST);
									chkinactiveemp.addItemListener(new ItemListener() {
										public void itemStateChanged(ItemEvent e) {
											
										}
									});
								}
								{
									chkinactiveassets = new JCheckBox("Include Inactive Assets");
									pnlcheckbox.add(chkinactiveassets, BorderLayout.EAST);
									chkinactiveassets.addItemListener(new ItemListener() {
										public void itemStateChanged(ItemEvent e) {
											
										}
									});
								}
							}
						}
					}
				}
			}
			{
				JPanel pnlCenter = new JPanel(new BorderLayout(5, 5));
				pnlMain.add(pnlCenter, BorderLayout.CENTER);
				pnlCenter.setPreferredSize(new Dimension(788, 50));
				pnlCenter.setBorder(lineBorder);
				{

					scrollAssets = new JScrollPane();
					pnlCenter.add(scrollAssets, BorderLayout.CENTER);
					scrollAssets.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					{
						modelAssets = new modelAssetMonitoring();
						tblAssets = new _JTableMain(modelAssets);
						scrollAssets.setViewportView(tblAssets);
						tblAssets.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent e) {
								if (!e.getValueIsAdjusting()) {
									try {
										int row = tblAssets.getSelectedRow();
										String asset_no = (String) modelAssets.getValueAt(row, 1);
										String rec_id = (String) modelAssets.getValueAt(row, 9);
										
										panelAssetInformation2.displayAssetDetail(asset_no);
										buttontagging(true, true, true);
										

									} catch (ArrayIndexOutOfBoundsException ex) {
									}
								}
							}
						});

						tblAssets.getTableHeader().setEnabled(false);
						tblAssets.setFillsViewportHeight(false);
						tblAssets.getColumnModel().getColumn(0).setPreferredWidth(50);// checkbox
						tblAssets.getColumnModel().getColumn(1).setPreferredWidth(120);
						tblAssets.getColumnModel().getColumn(2).setPreferredWidth(130);// asset code
						tblAssets.getColumnModel().getColumn(3).setPreferredWidth(200);// asset name
						tblAssets.getColumnModel().getColumn(4).setPreferredWidth(100);// date acquired
						tblAssets.getColumnModel().getColumn(6).setPreferredWidth(230);// Custodian
						tblAssets.getColumnModel().getColumn(8).setPreferredWidth(70);// status
						tblAssets.hideColumns("Custodian ID", "Reference No.");

					}
					{
						rowheaderAssets = tblAssets.getRowHeader();
						scrollAssets.setRowHeaderView(rowheaderAssets);
						scrollAssets.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER,
								FncTables.getRowHeader_Header());
					}
				}
			}
			{
				tabAssets = new JTabbedPane();
				pnlMain.add(tabAssets, BorderLayout.SOUTH);
				tabAssets.setPreferredSize(new Dimension(500, 350));
				{
					pnlInformation = new panelAssetInformation2();
					tabAssets.addTab(" Asset Information ", null, pnlInformation, null);
				}
				{
					pnlTransfer = new JPanel(new BorderLayout(5, 5));
					tabAssets.addTab("  Tagging  ", null, pnlTransfer, null);
					{

						pnlNorth = new JPanel(new GridLayout(4, 1, 5, 5));
						pnlTransfer.add(pnlNorth, BorderLayout.NORTH);
						pnlNorth.setBorder(JTBorderFactory.createTitleBorder(""));
						pnlNorth.setPreferredSize(new Dimension(400, 150));
						{
							JPanel pnl1 = new JPanel(new BorderLayout(5, 5));
							pnlNorth.add(pnl1);
							{
								JLabel lblMovementno = new JLabel("Movement No.");
								pnl1.add(lblMovementno, BorderLayout.WEST);
								lblMovementno.setPreferredSize(new Dimension(115, 0));
							}
							{
								txtmovementno = new JTextField();
								pnl1.add(txtmovementno, BorderLayout.CENTER);
							}
							{
								JLabel lblextra = new JLabel("");
								pnl1.add(lblextra, BorderLayout.EAST);
								lblextra.setPreferredSize(new Dimension(650, 0));
							}
						}
						{
							JPanel pnl2 = new JPanel(new BorderLayout(5, 5));
							pnlNorth.add(pnl2);
							setPreferredSize(new Dimension(115, 30));
							{
								JLabel lblNewcustodian = new JLabel("*New Custodian");
								pnl2.add(lblNewcustodian, BorderLayout.WEST);
								lblNewcustodian.setPreferredSize(new Dimension(115, 0));

							}
							{
								lookupnewCustodian = new _JLookup();
								pnl2.add(lookupnewCustodian, BorderLayout.CENTER);
								lookupnewCustodian.setEditable(true);
								lookupnewCustodian.setLookupSQL(panelAssetInformation2.getCustodian());
								lookupnewCustodian.addLookupListener(new LookupListener() {
									public void lookupPerformed(LookupEvent event) {
										Object[] setCustodian = ((_JLookup) event.getSource()).getDataSet();
										FncSystem.out("Display SQL for Client", lookupnewCustodian.getLookupSQL());
										if (setCustodian != null) {
											String emp_code = (String) setCustodian[0];
											String emp_name = (String) setCustodian[1];
											lookupnewCustodian.setValue(emp_code);
											txtnewCustodian.setText(emp_name);
											txtmovementno.setText(getMoveNo());

										}
									}
								});
							}
							{
								txtnewCustodian = new JTextField();
								pnl2.add(txtnewCustodian, BorderLayout.EAST);
								txtnewCustodian.setPreferredSize(new Dimension(650, 0));
								txtnewCustodian.setEditable(true);
							}
						}
						{
							JPanel pnl3 = new JPanel(new BorderLayout(5, 5));
							pnlNorth.add(pnl3);
							setPreferredSize(new Dimension(115, 30));
							{
								JLabel lblLocation = new JLabel("*Location");
								pnl3.add(lblLocation, BorderLayout.WEST);
								lblLocation.setPreferredSize(new Dimension(115, 0));

							}
							{
								lookupLocation = new _JLookup();
								pnl3.add(lookupLocation, BorderLayout.CENTER);
								lookupLocation.setEditable(true);
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
								txtLocation.setPreferredSize(new Dimension(650, 0));
								txtLocation.setEditable(true);
							}
						}
						{
							JPanel pnl4 = new JPanel(new BorderLayout(5, 5));
							pnlNorth.add(pnl4);
							{
								JLabel lblRemarks = new JLabel("*Remarks");
								pnl4.add(lblRemarks, BorderLayout.WEST);
								lblRemarks.setPreferredSize(new Dimension(115, 0));
							}
							{
								jtxtRemarks = new JTextField();
								pnl4.add(jtxtRemarks, BorderLayout.CENTER);
								jtxtRemarks.setEditable(true);
							}
						}
					}
					{
						JPanel pnlCentertransfer = new JPanel(new BorderLayout(5, 5));
						pnlTransfer.add(pnlCentertransfer, BorderLayout.CENTER);
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
						pnlTransfer.add(pnlsouthtransfer, BorderLayout.SOUTH);
						pnlsouthtransfer.setPreferredSize(new Dimension(0, 35));
						{
							JPanel pnlbuttons = new JPanel(new GridLayout(1, 3, 5, 5));
							pnlsouthtransfer.add(pnlbuttons);
							{
								btn1 = new JButton("Transfer asset");
								pnlbuttons.add(btn1, BorderLayout.WEST);
								btn1.setEnabled(false);
								btn1.addActionListener(this);
							}
							{
								btnDispose = new JButton("Dispose");
								pnlbuttons.add(btnDispose);
								btnDispose.setEnabled(false);
								btnDispose.addActionListener(this);
							}
							{
								btnRetire = new JButton("Retire");
								pnlbuttons.add(btnRetire);
								btnRetire.setEnabled(false);
								btnRetire.addActionListener(this);
							}
						}
					}
				}
			}
			tabAssets.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					int selectedTab = ((JTabbedPane) e.getSource()).getSelectedIndex();
					if(selectedTab == 0) {
						clearcheckbox();
					}
					if(selectedTab == 1) {
						modelAssets.setEditable(true);
						clearcheckbox();
						
					}
				}
			});
		}
	}
	
	protected void clearcheckbox() {

		for (int x = 0; x < tblAssets.getRowCount(); x++) {
			tblAssets.setValueAt(false, x, 0);
		}
	}
	
	private static String getMoveNo(){
		pgSelect db = new pgSelect();
		
		String strSQL = "select trim(to_char(coalesce(max(move_no), 0) + 1, '0000000000')) from rf_asset_history";
		db.select(strSQL);
		return db.getResult()[0][0].toString();
	}
	
	public static void displayAllAssets(Boolean emp, Boolean assts) {
		modelAssets.clear();

		DefaultListModel listModel = new DefaultListModel();// Creating listModel for rowHeader.
		rowheaderAssets.setModel(listModel);// Setting of listModel into rowHeader.
		System.out.println(lookupselectcompany.getValue());
		String strSQL = " select * from view_allassetv3('" +co_id+ "'," + assts + "," + emp+ ")";
		
		FncSystem.out("Display All Assets", strSQL);
		// System.out.println(co_id);

		pgSelect db = new pgSelect();
		db.select(strSQL);

		if (db.isNotNull()) {
			for (int x = 0; x < db.getRowCount(); x++) {

				// You can only use this kind of adding row in model when you're query and model
				// has the same and exact unmber of columns and column types.
				modelAssets.addRow(db.getResult()[x]);

				// For every row added in model, the table header will also add the row number.
				listModel.addElement(modelAssets.getRowCount());
			}
		}

		tblAssets.packAll();
	}
	
	//Tagging
	public static String getassetlocation() {

		return "select loc_id,loc_name from rf_asset_location ";
	}
	
	private void buttontagging(Boolean transfer, Boolean dispose, Boolean retire) {
		btn1.setEnabled(transfer);
		btnDispose.setEnabled(dispose);
		btnRetire.setEnabled(retire);
	}
	
	public static void displayIndividualAssets(DefaultTableModel model, JList rowHeader, String emp_code){
		
		FncTables.clearTable(model);//Code to clear model.
		DefaultListModel listModel = new DefaultListModel();//Creating listModel for rowHeader.
		rowHeader.setModel(listModel);//Setting of listModel into rowHeader.
		
		//Individual display of assets
		String strSQL = "select false, to_char(a.asset_no,'FM00000000'),\n" +
				"'' as asset_code, \n" + 
				"a.asset_name, \n" + 
				"a.date_Acquired,\n" + 
				"lpad(a.current_cust::text, 6, '0'::text),\n" + 
				"get_employee_name(a.current_cust::varchar), \n" + 
				"a.reference_no, \n" + 
				"format('%s',left(a.status,1)) as status \n"+
				"from rf_asset a \n" + 
				"where  a.current_cust='"+emp_code+"'\n" + 
				"and a.status not in ('I')\n" + 
				"order by asset_no ";
		
		FncSystem.out("displayIndividualAssets", strSQL);	
		 
		pgSelect db = new pgSelect();
		db.select(strSQL);

		if(db.isNotNull()){
			for(int x=0; x < db.getRowCount(); x++)
			{
				//You can only use this kind of adding row in model when you're query and model has the same and exact unmber of columns and column types.
				model.addRow(db.getResult()[x]);
				
				//For every row added in model, the table header will also add the row number.
				listModel.addElement(model.getRowCount());
			}
		}
	}
	
}
