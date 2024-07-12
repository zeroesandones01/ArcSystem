package Accounting.FixedAssets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
//import javax.swing.border.LineBorder;
//import javax.swing.table.TableColumn;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.vdc.glasspane.JGlassLoading;

import Database.pgSelect;
import Database.pgUpdate;
import Functions.FncGlobal;
import Functions.FncReport;
import Functions.FncSystem;
import Functions.FncTables;
import Functions.UserInfo;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import Renderer.DateRenderer;
import components.JTBorderFactory;
import components._JInternalFrame;
import components._JTableMain;
import interfaces._GUI;
import tablemodel.modelAssetMonitoring;
import tablemodel.modelRepair;
import tablemodel.modelapproval;

public class AssetMonitoring extends _JInternalFrame implements ActionListener, _GUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 652923134846245300L;

	public static String title = "Asset Monitoring";
	public static Dimension frameSize = new Dimension(922, 630);// 510, 250
	public static Border lineBorder = BorderFactory.createLineBorder(Color.GRAY);
	public static Cursor handCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

	private JButton btnDispose;
	private JButton btnRetire;
	private JButton btnPullout;

	private JPanel pnlMain;
	private JPanel pnlNorth;
	private JPanel pnlCenter;
	private JLabel lblRemarks;
	private JLabel lblMovementno;
	private JLabel lblNewcustodian;
	private _JLookup lookupRequestedby;
	public static _JLookup lookupCustodianid;
	private JPanel pnlTransfer;

	private JTable tblAsset;

	public static JTextField txtmovementno;
	public static JTextField txtCustodianid;
	private JTextField txtdepartment1;
	private JTextField txtdepartment2;
	private JTextField txtdivision1;
	private JTextField txtdivision2;
	public JTextField jtxttransfer;

	public static JTabbedPane tabAssets;
	private JScrollPane scrollAssets;
	public static _JTableMain tblAssets;
	private _JTableMain tblMovement;

	public static modelAssetMonitoring modelAssets;

	public static JList rowheaderAssets;
	private JList rowheaderMovement;

	private JScrollPane scrollMovement;
	private DefaultTableModel modelMovement;
	private panelAssetInformation pnlInformation;

	private JTextField txtCustodian;
	private JTextField jtRequestedby;
	protected DefaultTableModel modelRepair;
	private JCheckBox chkMyAssets;
	private JGlassLoading glass;
	private ListSelectionModel rowSM;
	private ListSelectionListener rowLSL;
	public static Thread loadAllAssets;
	private JTabbedPane tabbedAssets;
	private DefaultTableModel modelAsset;
	protected JCheckBox chkPrintAssetStickerInfo;
	protected DefaultTableCellRenderer rendererCenterAlign = new DefaultTableCellRenderer();
	protected DefaultTableCellRenderer rendererCenterRight = new DefaultTableCellRenderer();

	private JTextField jtxtRemarks;
	public static _JLookup lookupnewCustodian;
	private JTextField txtnewCustodian;
	private JTextField jtxtReplacement;
	private JButton btnRepsave;
	private JTextArea jtAreason;
	private JTextField txtsearchby;
	private JPanel pnlcompany;
	public static JTextField txtselectcompany;
	private JLabel txtcompany;
	private JPanel pnlButtton;
	public static JCheckBox chkinactiveemp;
	public static JCheckBox chkinactiveassets;
	private JPanel pnlNorthextra;
	private JPanel pnl1;
	private JLabel lblselectcompany;
	private JPanel pnlcheckbox;
	public static _JLookup lookupselectcompany;
	private JPanel pnlselectcompanyxtra;
	public static Boolean incl_Inc_emp = false;
	public Boolean incl_Inc_assets = false;

	private Runnable run;

	private Runnable runnable;

	protected Thread chkinactiveemprun;

	protected Thread chkinactiveassetsrun;

	private JPanel pnlsouthtransfer;

	private JPanel pnlbuttons;

	private JButton btn1;

	private JButton btn2;

	private JButton btn3;

	private JPanel pnlapproval;

	private JPanel pnlforapprovalNorth;

	private JScrollPane scrollapproval;

	public static modelapproval modelapproval;

	private _JTableMain tblapproval;

	public static JList rowheaderapproval;

	private JPanel pnlapprovalbuttons;

	private JPanel pnlapprovalbuttons1;

	private JPanel pnlforapprovalcenter;

	private JPanel pnlapprovalnorth;

	private JLabel lblcurrent_cust;

	private _JLookup lookupcurrent_cust;

	private JTextField txtpnlapprovalnorth;

	private JPanel pnlapprovalnorth1;

	private JPanel pnlcurrent_cust;

	private JTextField txtpnlapprovalnorthxtra;

	private JPanel pnlRefurbish;

	private JPanel pnlRefurbishnorth;

	private JPanel pnlRefurbishcenter;

	private JPanel pnlRefurbishsouth;

	private JLabel lblrecon;

	private JPanel pnlRefurbishnorth1;

	private _JLookup lookuprecon;

	private JTextField txtrecon1;

	private JTextField txtrecon2;

	private JPanel pnlRefurbishnorth2;

	private JTextField txtassetnorfrbsh1;

	private JLabel lblassetnorfrbsh;

	private JPanel pnlRefurbishnorth3;

	private JLabel lblcompanyrfrbsh;

	private _JLookup lookupcompanyrfrbsh;

	private JPanel pnlRefurbishnorth3_1;

	private JTextField txtcompanyrfrbsh1; 

	private JTextField txtcompanyrfrbsh2;

	public static String dept_code = null;

	public static String co_id = "01";
	public static String co_name = "Acerland DEVELOPMENT CORPORATION";
	public static String co_logo = "cenqlogo.png";
	private static boolean incl_Inc_asset;

	private JPopupMenu menu2;
	private JPopupMenu menu;
	private JMenuItem mniopenTag;

	protected static String loc_id = null;

	private JLabel lblLocation;

	public static _JLookup lookupLocation;

	public static JTextField txtLocation;

	public AssetMonitoring() {
		super(title, true, true, true, true);
		initGUI();
		loadAllAsset();
		// displayAllAssets(false,false);
		panelAssetInformation.btnState(true, true, false, true, false);
		// FncGlobal.startProgress("Please wait while loading assets.");
		/*
		 * runnable= new Runnable() { public void run() { loadAllAsset();
		 * FncGlobal.stopProgress(); } };
		 */
		// SwingUtilities.invokeLater(runnable);
	}

	public AssetMonitoring(String title) {
		super(title, true, true, true, true);
		initGUI();
	}

	public AssetMonitoring(String title, boolean resizable, boolean closable, boolean maximizable,
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

		{// Main Panel
			pnlMain = new JPanel(new BorderLayout(5, 5));
			getContentPane().add(pnlMain, BorderLayout.CENTER);
			pnlMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			{
				pnlNorth = new JPanel(new BorderLayout(5, 5));
				pnlMain.add(pnlNorth, BorderLayout.NORTH);
				pnlNorth.setBorder(new EmptyBorder(5, 5, 5, 5));
				pnlNorth.setPreferredSize(new Dimension(0, 60));
				{
					pnlNorthextra = new JPanel(new GridLayout(2, 1, 5, 5));
					pnlNorth.add(pnlNorthextra);
					{
						pnl1 = new JPanel(new BorderLayout(5, 5));
						pnlNorthextra.add(pnl1, BorderLayout.WEST);
						{
							lblselectcompany = new JLabel("*Company");
							pnl1.add(lblselectcompany, BorderLayout.WEST);
							lblselectcompany.setPreferredSize(new Dimension(76, 0));

						}
						{
							lookupselectcompany = new _JLookup();
							pnl1.add(lookupselectcompany, BorderLayout.CENTER);
							lookupselectcompany.setPreferredSize(new Dimension(80, 0));
							lookupselectcompany.setValue(co_id);
							//lookupselectcompany.setLookupSQL(_AssetMonitoring.getCompany());
							lookupselectcompany.setLookupSQL("Select '01', 'Acerland DEVELOPMENT CORPORATION' ");
							lookupselectcompany.addLookupListener(new LookupListener() {

								public void lookupPerformed(LookupEvent event) {
									Object[] data = ((_JLookup) event.getSource()).getDataSet();

									FncSystem.out("Display SQL", lookupselectcompany.getLookupSQL());
									if (data != null) {
										co_id = (String) data[0];
										co_name = (String) data[1];
										//co_logo = (String) data[3];
										
										lookupselectcompany.setValue(co_id);
										txtselectcompany.setText(co_name);
										lookupCustodianid.setValue("");
										txtCustodianid.setText("");
										//lookupCustodianid.setLookupSQL(_AssetMonitoring.pnlinformationgetCustodian(co_id));
										panelAssetInformation.co_id = co_id;
										panelAssetInformation.co_name = co_name;
										panelAssetInformation.co_logo = co_logo;

										//panelAssetInformation.lookupCustodian.setLookupSQL(_AssetMonitoring.pnlinformationgetCustodian(co_id));
										panelAssetInformation.lookupCustodian.setLookupSQL("select '900767', 'BITUEN, JOHN ERICK RESPICIO'");

										new Thread(new Runnable() {

											@Override
											public void run() {
												FncGlobal.startProgress("Please wait while loading assets.");
												// TODO Auto-generated method stub
												if (chkinactiveemp.isSelected() == true) {
													if (chkinactiveassets.isSelected() == true) {
														displayAllAssets(true, true);
													} else {
														displayAllAssets(true, false);
													}
												} else {
													if (chkinactiveassets.isSelected() == true) {
														displayAllAssets(false, true);
													} else {
														displayAllAssets(false, false);
													}
												}
												FncGlobal.stopProgress();
											}
										}).start();
									}

								}
							});

						}

						{
							pnlselectcompanyxtra = new JPanel(new BorderLayout(5, 5));
							pnl1.add(pnlselectcompanyxtra, BorderLayout.EAST);
							pnlselectcompanyxtra.setPreferredSize(new Dimension(725, 0));
							{
								txtselectcompany = new JTextField();
								pnlselectcompanyxtra.add(txtselectcompany, BorderLayout.WEST);
								txtselectcompany.setPreferredSize(new Dimension(358, 0));
								txtselectcompany.setText(co_name);
							}

						}
					}
					{
						pnlcheckbox = new JPanel(new BorderLayout(2, 2));
						pnlNorthextra.add(pnlcheckbox, BorderLayout.WEST);
						pnlcheckbox.setPreferredSize(new Dimension(100, 0));
						{
							chkMyAssets = new JCheckBox("All asset");
							pnlcheckbox.add(chkMyAssets, BorderLayout.WEST);
							chkMyAssets.setSelected(true);
							chkMyAssets.addItemListener(new ItemListener() {
								public void itemStateChanged(ItemEvent e) {

								}
							});
						}
						
					}
					{
						JPanel pnllookuptext = new JPanel(new BorderLayout(5, 5));
						pnlcheckbox.add(pnllookuptext, BorderLayout.CENTER);
						pnllookuptext.setPreferredSize(new Dimension(150, 10));
						{
							lookupCustodianid = new _JLookup("Custodian ID");
							pnllookuptext.add(lookupCustodianid, BorderLayout.WEST);
							lookupCustodianid.setPreferredSize(new Dimension(80, 0));
							lookupCustodianid.setLookupSQL(_AssetMonitoring.pnlinformationgetCustodian(co_id));
							//lookupCustodianid.setLookupSQL("select '900767', 'BITUEN, JOHN ERICK RESPICIO'");
							lookupCustodianid.addLookupListener(new LookupListener() {

								public void lookupPerformed(LookupEvent event) {
									Object[] data = ((_JLookup) event.getSource()).getDataSet();
									FncSystem.out("Display SQL for Client", lookupCustodianid.getLookupSQL());
									if (data != null) {
										String emp_code = (String) data[0];
										System.out.printf("Display value of entity_id: %s%n", emp_code);
										String emp_name = (String) data[1];
										dept_code = (String) data[6];
										lookupCustodianid.setValue(emp_code);
										txtCustodianid.setText(emp_name);
										_AssetMonitoring.displayIndividualAssets(modelAssets, rowheaderAssets,
												emp_code);
									}

								}
							});
						}
						{
							txtCustodianid = new JTextField();
							pnllookuptext.add(txtCustodianid, BorderLayout.CENTER);
							txtCustodianid.setEditable(false);
						}
					}
					{
						JPanel pnlxtra = new JPanel(new BorderLayout(20, 20));
						pnlcheckbox.add(pnlxtra, BorderLayout.EAST);
						pnlxtra.setPreferredSize(new Dimension(365, 10));
						{
							chkinactiveemp = new JCheckBox("Include Inactive Employees");
							pnlxtra.add(chkinactiveemp, BorderLayout.WEST);
							chkinactiveemp.addItemListener(new ItemListener() {

								public void itemStateChanged(ItemEvent e) {
									new Thread(new Runnable() {

										@Override
										public void run() {
											FncGlobal.startProgress("Please wait while loading assets.");
											if (chkinactiveemp.isSelected() == true) {
												if (chkinactiveassets.isSelected() == true)

												{
													displayAllAssets(true, true);
												} else {
													displayAllAssets(true, false);
													lookupCustodianid.setValue("");
													txtCustodianid.setText("");
												}
											} else {
												if (chkinactiveassets.isSelected() == true) {
													displayAllAssets(false, true);
												} else {
													displayAllAssets(false, false);
													lookupCustodianid.setValue("");
													txtCustodianid.setText("");
												}
											}
											FncGlobal.stopProgress();
										}
									}).start();
								}
							});
						}
						{
							chkinactiveassets = new JCheckBox("Include Inactive Assets");
							pnlxtra.add(chkinactiveassets, BorderLayout.EAST);
							chkinactiveassets.addItemListener(new ItemListener() {
								public void itemStateChanged(ItemEvent e) {
									new Thread(new Runnable() {

										@Override
										public void run() {
											FncGlobal.startProgress("Please wait while loading assets.");
											if (chkinactiveassets.isSelected() == true) {
												if (chkinactiveemp.isSelected() == true) {
													displayAllAssets(true, true);

												} else {
													displayAllAssets(false, true);
												}
											} else {
												if (chkinactiveemp.isSelected() == true) {
													displayAllAssets(true, false);
												} else {
													displayAllAssets(false, false);
												}
											}
											FncGlobal.stopProgress();
										}
									}).start();
								}
							});
						}
					}
				}

			}
			{

			}
			{ // Center Panel
				pnlCenter = new JPanel(new BorderLayout(5, 5));
				pnlMain.add(pnlCenter, BorderLayout.CENTER);
				pnlCenter.setPreferredSize(new Dimension(788, 50));
				pnlCenter.setBorder(lineBorder);

				{
					JPanel pnlscroll = new JPanel(new BorderLayout(5, 5));
					pnlCenter.add(pnlscroll, BorderLayout.CENTER);
					{
						{
							scrollAssets = new JScrollPane();
							pnlscroll.add(scrollAssets, BorderLayout.CENTER);
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
												_AssetMonitoring.displayMovementHistory(modelMovement,
														rowheaderMovement, asset_no);
												panelAssetInformation.displayAssetDetail(asset_no);
												panelAssetInformation.btnPreview.setEnabled(true);
												btn1.setEnabled(true);
												btnDispose.setEnabled(true);
												btnPullout.setEnabled(true);
												btnRetire.setEnabled(true);

											} catch (ArrayIndexOutOfBoundsException ex) {
											}
										}
									}
								});

								tblAssets.getTableHeader().setEnabled(false);
								tblAssets.setFillsViewportHeight(false);
								tblAssets.getColumnModel().getColumn(0).setPreferredWidth(50);// checkbox
								tblAssets.getColumnModel().getColumn(1).setPreferredWidth(120);
								;// asset_no
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
				}
			}
			{
				tabAssets = new JTabbedPane();
				pnlMain.add(tabAssets, BorderLayout.SOUTH);
				tabAssets.setPreferredSize(new Dimension(500, 350));
				{
					pnlInformation = new panelAssetInformation();
					tabAssets.addTab(" Information ", null, pnlInformation, null);
					panelAssetInformation.co_id = co_id;
					panelAssetInformation.co_name = co_name;

				}
				{
					pnlTransfer = new JPanel(new BorderLayout(5, 5));
					tabAssets.addTab("  Tagging  ", null, pnlTransfer, null);
					{
						pnlNorth = new JPanel(new GridLayout(4, 1, 5, 5));
						pnlTransfer.add(pnlNorth, BorderLayout.NORTH);
						pnlNorth.setBorder(JTBorderFactory.createTitleBorder(""));
						pnlNorth.setPreferredSize(new Dimension(400, 120));
						{
							JPanel pnl1 = new JPanel(new BorderLayout(5, 5));
							pnlNorth.add(pnl1);
							pnl1.setPreferredSize(new Dimension(100, 30));
							{
								lblMovementno = new JLabel("Movement No.");
								pnl1.add(lblMovementno, BorderLayout.WEST);
								lblMovementno.setPreferredSize(new Dimension(115, 0));
							}
							{
								txtmovementno = new JTextField();
								pnl1.add(txtmovementno, BorderLayout.CENTER);
							}
						}
						{
							JPanel pnl2 = new JPanel(new BorderLayout(5, 5));
							pnlNorth.add(pnl2);
							setPreferredSize(new Dimension(115, 30));
							{
								lblNewcustodian = new JLabel("*New Custodian");
								pnl2.add(lblNewcustodian, BorderLayout.WEST);
								lblNewcustodian.setPreferredSize(new Dimension(115, 0));

							}
							{
								lookupnewCustodian = new _JLookup();
								pnl2.add(lookupnewCustodian, BorderLayout.CENTER);
								lookupnewCustodian.setEditable(true);
								lookupnewCustodian.addLookupListener(new LookupListener() {
									@Override
									public void lookupPerformed(LookupEvent event) {
										Object[] setCustodian = ((_JLookup) event.getSource()).getDataSet();
										FncSystem.out("Display SQL for Client", lookupnewCustodian.getLookupSQL());
										if (setCustodian != null) {
											String emp_code = (String) setCustodian[0];
											String emp_name = (String) setCustodian[1];
											lookupnewCustodian.setValue(emp_code);
											txtnewCustodian.setText(emp_name);

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
								lblLocation = new JLabel("*Location");
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
								lblRemarks = new JLabel("*Remarks");
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
						// setPreferredSize(new Dimension(115,30));
						{
							scrollMovement = new JScrollPane();
							pnlCentertransfer.add(scrollMovement);
							{
								SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

								modelMovement = new tablemodel.modelMovement();
								tblMovement = new _JTableMain(modelMovement);
								scrollMovement.setViewportView(tblMovement);

								tblMovement.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
								tblMovement.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
								tblMovement.getColumnModel().getColumn(0).setPreferredWidth(100);// move_no
								tblMovement.getColumnModel().getColumn(1).setPreferredWidth(125);// old asset
								tblMovement.getColumnModel().getColumn(2).setPreferredWidth(135);// prev_cust
								tblMovement.getColumnModel().getColumn(3).setPreferredWidth(135);// current_cust
								tblMovement.getColumnModel().getColumn(4).setPreferredWidth(110);// trans_date
								tblMovement.getColumnModel().getColumn(5).setPreferredWidth(95);// reason
								tblMovement.getColumnModel().getColumn(6).setPreferredWidth(150);// remarks
								tblMovement.getColumnModel().getColumn(6).setPreferredWidth(150);// old location added
																									// by jari cruz as
																									// of july 8 2022
								tblMovement.setFont(new Font("DejaVu Sans", 0, 12));

								tblMovement.getColumnModel().getColumn(0).setCellRenderer(rendererCenterAlign);
								tblMovement.getColumnModel().getColumn(1).setCellRenderer(rendererCenterAlign);
								tblMovement.getColumnModel().getColumn(4).setCellRenderer(rendererCenterAlign);


							}
							{
								rowheaderMovement = tblMovement.getRowHeader();
								scrollMovement.setRowHeaderView(rowheaderMovement);
								scrollMovement.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER,
										FncTables.getRowHeader_Header());
							}
						}
					}
					{
						pnlsouthtransfer = new JPanel(new BorderLayout(5, 5));
						pnlTransfer.add(pnlsouthtransfer, BorderLayout.SOUTH);
						pnlsouthtransfer.setPreferredSize(new Dimension(0, 30));
						{
							pnlbuttons = new JPanel(new GridLayout(1, 4, 5, 5));
							pnlsouthtransfer.add(pnlbuttons);
							{
								btn1 = new JButton("Transfer asset");
								pnlbuttons.add(btn1, BorderLayout.WEST);
								btn1.setEnabled(false);
								btn1.addActionListener(new ActionListener() {

									public void actionPerformed(ActionEvent e) {
										int move_no = Integer.valueOf(txtmovementno.getText()) - 1;
										if (tblAssets.getSelectedRows().length > 0) {

											if (hasCheckedAssets()) {
												int toSave = JOptionPane.showConfirmDialog(getTopLevelAncestor(),
														"Are all entries correct?", btn1.getText(),
														JOptionPane.YES_NO_OPTION);
												if (toSave == JOptionPane.YES_OPTION) {
													// for direct transfer
													JOptionPane.showMessageDialog(getTopLevelAncestor(),
															"Asset is already transferd.", "Transfer",
															JOptionPane.INFORMATION_MESSAGE);
													int row = tblAssets.getSelectedRow();
													String custodian_id = modelAssets.getValueAt(row, 5).toString();

													System.out.println(row);
													System.out.println();
													transferAsset(move_no, custodian_id, lookupnewCustodian.getValue(),
															jtxtRemarks.getText(), txtnewCustodian.getText(),
															dept_code);
													resetMovement();
													panelAssetInformation.resetInformation();
													panelAssetInformation.co_id = co_id;
													panelAssetInformation.co_name = co_name;
													jtxtRemarks.setEditable(true);

												}
											} else {
												JOptionPane.showMessageDialog(getTopLevelAncestor(),
														"Please fill up the required fields", btn1.getText(),
														JOptionPane.WARNING_MESSAGE);
											}

										} else {
											JOptionPane.showMessageDialog(getTopLevelAncestor(),
													"Please select asset to transfer", "Transfer",
													JOptionPane.WARNING_MESSAGE);
										}
									}
								});
							}
							{
								btnDispose = new JButton("Dispose");
								pnlbuttons.add(btnDispose);
								btnDispose.setEnabled(false);
								btnDispose.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {

										if (hasCheckedAssets()) {
											if (UserInfo.EmployeeCode.equals("901250")) {
												Object[] options = { "OK", "CANCEL" };
												int todispose = JOptionPane.showOptionDialog(getTopLevelAncestor(),
														"Click OK to continue", "Dispose Asset?",
														JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
														null, options, options);

												if (todispose == JOptionPane.YES_OPTION) {
													JOptionPane.showMessageDialog(getTopLevelAncestor(),
															"Asset has been disposed.", btnDispose.getText(),
															JOptionPane.INFORMATION_MESSAGE);
													_AssetMonitoring.disposeAsset(tblAssets);
													panelAssetInformation.resetInformation();
												}

											} else {
												JOptionPane.showMessageDialog(getTopLevelAncestor(),
														"You are not allowed to Dispose asset.", "Save",
														JOptionPane.PLAIN_MESSAGE);
											}
										} else {
											JOptionPane.showMessageDialog(getTopLevelAncestor(),
													"Please select asset to Dispose.", btnDispose.getText(),
													JOptionPane.INFORMATION_MESSAGE);
										}
									}
								});
							}
							{
								btnRetire = new JButton("Retire");
								pnlbuttons.add(btnRetire);
								btnRetire.setEnabled(false);
								btnRetire.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {

										if (hasCheckedAssets()) {
											if (UserInfo.EmployeeCode.equals("901250")) {
												Object[] options = { "OK", "CANCEL" };
												int toRetire = JOptionPane.showOptionDialog(getTopLevelAncestor(),
														"Click OK to continue.", "Retire Asset?",
														JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
														options, options);

												if (toRetire == JOptionPane.YES_OPTION) {
													JOptionPane.showMessageDialog(getTopLevelAncestor(),
															"Asset has been retired.", "Retire",
															JOptionPane.INFORMATION_MESSAGE);
													_AssetMonitoring.retireAsset(tblAssets);
													panelAssetInformation.resetInformation();
												}

											} else {
												JOptionPane.showMessageDialog(getTopLevelAncestor(),
														"You are not allowed to Retire asset.", "Save",
														JOptionPane.PLAIN_MESSAGE);
											}

										} else {
											JOptionPane.showMessageDialog(getTopLevelAncestor(),
													"Please select asset to retire.", btnRetire.getText(),
													JOptionPane.WARNING_MESSAGE);

										}
									}
								});
							}
//							{ Comment by erick 2024-07-04 - not needed
//								btnPullout = new JButton("Pull-out");
//								pnlbuttons.add(btnPullout);
//								btnPullout.setEnabled(false);
//								btnPullout.addActionListener(new ActionListener() {
//									public void actionPerformed(ActionEvent e) {
//
//										if (hasCheckedAssets()) {
//											Object[] options = { "OK", "CANCEL" };
//											int toPulloutAsset = JOptionPane.showOptionDialog(getTopLevelAncestor(),
//													"Click OK to continue", "Pullout asset for Repair?",
//													JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
//													options, options);
//
//											if (toPulloutAsset == JOptionPane.YES_OPTION) {
//												JOptionPane.showMessageDialog(getTopLevelAncestor(),
//														"Asset has been pullout for repair.", "Pull-out",
//														JOptionPane.INFORMATION_MESSAGE);
//												int row = tblAssets.getSelectedRow();
//												String asset_no = modelAssets.getValueAt(row, 1).toString();
//												System.out.println(row);
//												System.out.println();
//												panelAssetInformation.pulloutAsset(asset_no);
//												clearcheckbox();
//
//											}
//										} else {
//											JOptionPane.showMessageDialog(getTopLevelAncestor(),
//													"Please select asset to pull-out.", btnPullout.getText(),
//													JOptionPane.WARNING_MESSAGE);
//										}
//									}
//								});
//
//							}
						}
					}
				}

				tabAssets.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						int selectedTab = ((JTabbedPane) e.getSource()).getSelectedIndex();
						if (selectedTab == 0) {
							modelAssets.setEditable(true);
							panelAssetInformation.btnState(true, true, false, true, false);
							clearcheckbox();

						}
						if (selectedTab == 1) {
							txtmovementno.setText(_AssetMonitoring.getMoveNo());
							lookupnewCustodian.setLookupSQL(_AssetMonitoring.getCustodian());
							tblAssets.setEnabled(true);
							modelAssets.setEditable(true);
							btn1.setEnabled(false);
							btnDispose.setEnabled(false);
							btnRetire.setEnabled(false);
							btnPullout.setEnabled(false);
							clearcheckbox();
							lookupnewCustodian.setValue("");
							txtnewCustodian.setText("");
							jtxtRemarks.setText("");
						}


						if // (selectedTab == 3){
						(selectedTab == 2) {
							tblAssets.setEnabled(true);
							modelAssets.setEditable(true);
							// btnRepsave.setEnabled(false);
							btnDispose.setEnabled(false);
							btnRetire.setEnabled(false);
							btnPullout.setEnabled(false);
							clearcheckbox();
						}

					}
				});
			}
		}
	}

	protected void clearcheckbox() {

		for (int x = 0; x < tblAssets.getRowCount(); x++) {
			tblAssets.setValueAt(false, x, 0);
		}

	}

	protected void clearmodelaprvlchkbox() {

		for (int x = 0; x < tblapproval.getRowCount(); x++) {
			tblapproval.setValueAt(false, x, 0);
		}
	}

	protected void resetReplacement() {
		clearcheckbox();
		jtxtReplacement.setText(_AssetMonitoring.getNewReplacementNo());
		lookupRequestedby.setText("");
		jtRequestedby.setText("");
		txtdepartment1.setText("");
		txtdepartment2.setText("");
		txtdivision1.setText("");
		txtdivision2.setText("");

		jtAreason.setText("");
	}

	protected Boolean checkReplacement() {
		if (lookupRequestedby.getText().equals("") || jtAreason.getText().trim().equals(""))
			return false;
		else
			return true;
	}

	protected Boolean hasCheckedAssets() {
		ArrayList<Boolean> checkTable = new ArrayList<Boolean>();
		for (int x = 0; x < tblAssets.getRowCount(); x++) {
			if (tblAssets.getValueAt(x, 0).equals(true))
				checkTable.add(true);
		}
		return checkTable.contains(true);

	}

	public void loadIndividualAsset(final String emp_id, final Boolean fromDelete) {
		new Thread(new Runnable() {
			public void run() {
				glass.start("Loading assets of " + txtCustodian.getText().trim() + ". Please wait.");

				rowSM.removeListSelectionListener(rowLSL);

				_AssetMonitoring.displayIndividualAssets(modelAssets, rowheaderAssets, emp_id);
				if (tblAsset.getRowCount() > 0) {
					tblAsset.setRowSelectionInterval(0, 0);

					rowSM.addListSelectionListener(rowLSL);
					displayAssetRepairs(tblAsset.getValueAt(0, 1).toString(), fromDelete);
				}

				glass.stop();
			}
		}).start();
	}

	protected void btnRetireState(Boolean sTransfer, Boolean sDispose, Boolean sRetire, Boolean sPullout) {
		btn1.setEnabled(sTransfer);
		btnDispose.setEnabled(sDispose);
		btnRetire.setEnabled(sRetire);
		btnPullout.setEnabled(sPullout);
	}

	protected class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
		private static final long serialVersionUID = -7913993723934191067L;
		Boolean isDisabled;

		CheckBoxRenderer(Boolean isDisabled) {
			setHorizontalAlignment(JLabel.CENTER);
			this.isDisabled = isDisabled;
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(table.getBackground());
			}
			setSelected((value != null && ((Boolean) value).booleanValue()));
			setEnabled(isDisabled);
			return this;
		}
	}

	protected void displayAssetRepairs(String asset_no, Boolean fromDelete) {
		FncTables.clearTable(modelRepair);
		_AssetMonitoring.displayRepairHistory(asset_no, modelRepair);
		if (UserInfo.Department.equals("01") || UserInfo.Department.equals("82") || UserInfo.Department.equals("97")
				|| UserInfo.Department.equals("98")) {
			if (panelAssetInformation.txtStatus.equals("MISSING")
					|| panelAssetInformation.txtStatus.equals("INACTIVE")) {
				if (jtxtRemarks.getText().equals("UNDER REPAIR")) {
					btnRetireState(true, true, true, false);
				} else if (jtxtRemarks.getText().equals("RETIRED")) {
					btnRetireState(false, true, false, false);
				} else if (jtxtRemarks.getText().equals("DISPOSED")) {
					btnRetireState(false, false, false, false);
				} else {
					btnRetireState(false, false, false, false);
				}
			} else {
				if (jtxtRemarks.getText().equals("UNDER REPAIR")) {
					btnRetireState(true, true, true, false);
				} else if (jtxtRemarks.getText().equals("RETIRED")) {
					btnRetireState(false, true, false, false);
				} else if (jtxtRemarks.getText().equals("DISPOSED")) {
					btnRetireState(false, false, false, false);
				} else {
					btnRetireState(true, true, true, true);
				}
			}
		} else {
			btnRetireState(false, false, false, false);
		}

		if (fromDelete)
			btnRetireState(true, false, false, false);
	}

	public void loadAllAsset() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				FncGlobal.startProgress("Please wait while loading assets.");
				modelAssets.isCellEditable(0, 0);
				displayAllAssets(false, false);
				FncGlobal.stopProgress();
			}
		}).start();
	}

	protected void resetMovement() {
		txtmovementno.setText(_AssetMonitoring.getMoveNo());
		lookupnewCustodian.setText("");
		lblNewcustodian.setFont(null);

		txtnewCustodian.setText("");

		jtxtRemarks.setText("");
		lblRemarks.setFont(null);
	}

	public static void displayAllAssets(Boolean emp, Boolean assts) {
		modelAssets.clear();

		DefaultListModel listModel = new DefaultListModel();// Creating listModel for rowHeader.
		rowheaderAssets.setModel(listModel);// Setting of listModel into rowHeader.
		System.out.println(lookupselectcompany.getValue());
		String strSQL = " select * from view_allassetv2('" + lookupselectcompany.getValue() + "'," + assts + "," + emp
				+ ")";
		
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

	public static void disbletableAssets(Boolean isEnabled) {
		tblAssets.setEnabled(isEnabled);
	}

	public static void resettable(Boolean fromdelete) {
		modelAssets.clear();
		displayAllAssets(false, false);
	}

	public static void transferAsset(Integer move_no, String prev_cust, String current_cust, String remarks,
			String new_cust_name, String dept_code) {
		pgUpdate db = new pgUpdate();
		ArrayList<String> listAsset_no = new ArrayList<String>();
		ArrayList<Boolean> isSaved = new ArrayList<Boolean>();
		for (int x = 0; x < modelAssets.getRowCount(); x++) {

			Boolean selected = (Boolean) modelAssets.getValueAt(x, 0);

			if (selected) {
				move_no += 1;
				String asset_no = ((String) modelAssets.getValueAt(x, 1)).trim();
				String custodian_id = (String) modelAssets.getValueAt(x, 5).toString();
				String strSQL = null;
				if (loc_id == null) {
					strSQL = "INSERT INTO rf_asset_history( \n" + "asset_code, " + "prev_cust, " + "current_cust, "
							+ "trans_date, " + "reason, " + "remarks, \n" + "status, " + "move_no, " + "asset_no, "
							+ "trans_by,\n" +
							"dept_code) \n" + "VALUES (" + "(select asset_code from rf_asset where asset_no='"
							+ asset_no + "' ), " + // asset_code
							"'" + custodian_id + "', \n" + // prev_cust
							"'" + current_cust + "', \n" + // current_cust
							"current_date, \n" + // trans_date
							"'TRANSFER', \n" + // reason
							"'" + remarks + "', \n" + // remarks
							"'A', \n" + // status
							"(select max(move_no)+1 from rf_asset_history), \n" + // move_no
							"'" + asset_no + "', \n" + // asset_no
							"'" + UserInfo.EmployeeCode + "',\n" +
							"'" + dept_code + "') \n";// trans_by
				} else {
					strSQL = "INSERT INTO rf_asset_history( \n" + "asset_code, " + "prev_cust, " + "current_cust, "
							+ "trans_date, " + "reason, " + "remarks, \n" + "status, " + "move_no, " + "asset_no, "
							+ "trans_by,\n" +
							"old_location,\n" + "dept_code) \n" + "VALUES ("
							+ "(select asset_code from rf_asset where asset_no='" + asset_no + "' ), " + // asset_code
							"'" + custodian_id + "', \n" + // prev_cust
							"'" + current_cust + "', \n" + // current_cust
							"current_date, \n" + // trans_date
							"'TRANSFER', \n" + // reason
							"'" + remarks + "', \n" + // remarks
							"'A', \n" + // status
							"(select max(move_no)+1 from rf_asset_history), \n" + // move_no
							"'" + asset_no + "', \n" + // asset_no
							"'" + UserInfo.EmployeeCode + "',\n" +
							"(select loc_id from rf_asset where asset_no='" + asset_no + "' ),\n" + "'" + dept_code
							+ "') \n";// trans_by
				}
				System.out.println("move_no" + move_no);
				System.out.println("prev_cust" + prev_cust);
				System.out.println("current_cust" + current_cust);
				System.out.println("remarks" + remarks);
				System.out.println("new_cust_name" + new_cust_name);
				System.out.println("dept_code " + dept_code);
				System.out.println();
				db.executeUpdate(strSQL, true);
				String strSQL2 = null;
				if (loc_id == null) {
					strSQL2 = "update rf_asset set current_cust='" + current_cust
							+ "',status='A',item_found='t'  where asset_no='" + asset_no + "'::int";
				} else {
					strSQL2 = "update rf_asset set current_cust='" + current_cust
							+ "',status='A',item_found='t',loc_id='" + loc_id + "'  where asset_no='" + asset_no
							+ "'::int";
				}
				db.executeUpdate(strSQL2, false);
				listAsset_no.add(asset_no);
				isSaved.add(true);
				FncSystem.out("transfer asset", strSQL);
				FncSystem.out("transfer asset", strSQL2);

			}
		}
		db.commit();
	}

	public static String getassetlocation() {

		return "select loc_id,loc_name from rf_asset_location ";
	}

}
