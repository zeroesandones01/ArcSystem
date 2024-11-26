package Accounting.FixedAssets;

import interfaces._GUI;
import Accounting.FixedAssets.AssetMonitoring2;
import tablemodel.modelAssetMonitoring;
import tablemodel.modelMovement;
import tablemodel.model_accesories;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
//import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
//import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
//import java.text.ParseException;
//import java.util.Calendar;
//import java.util.Date;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.naming.ldap.Rdn;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXFormattedTextField;
import org.jdesktop.swingx.JXTextField;
//import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import com.mysql.jdbc.NdbLoadBalanceExceptionChecker;
import com.vdc.glasspane.JGlassLoading;

import Database.pgSelect;
import Database.pgUpdate;
import DateChooser._JDateChooser;
import Dialogs.dlg_accesories_v1;
import Dialogs.dlg_accesories_v2;
import Dialogs.dlg_todo_assetmonitoring;
import FormattedTextField._JXFormattedTextField;
import Functions.FncGlobal;
import Functions.FncReport;
import Functions.FncSystem;
import Functions.FncTables;
import Functions.UserInfo;
//import Functions.FncTables;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import Lookup._JLookupTable;


public class panelAssetInformation2 extends JPanel implements ActionListener, _GUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 902058080178823569L;
	
	protected JTable tblAsset;

	
	private JPanel pnlInfomationCenter;

	private JPanel pnlInfo1;
	private JPanel pnl1 = new JPanel();
	private JPanel jPanel1;
	private JLabel lblAssetNo;
	public static JTextField txtAssetNo;
	private JLabel lblAcquired;
	public static _JDateChooser dateAcquired;

	private JPanel pnl2 = new JPanel();
	private JPanel jPanel2;
	private static JLabel lblCompany;
	public static _JLookup lookupCompany;
	public static JTextField txtCompany;

	private JPanel pnl3 = new JPanel();
	private JPanel jPanel3;
	private static JLabel lblCustodian;
	public static _JLookup lookupCustodian;
	private static JTextField txtCustodian;

	private JPanel pnl4 = new JPanel();
	private JPanel jPanel4;
	private static JLabel lblCategory;
	public static _JLookup lookupCategory;
	public static JTextField txtCategory;

	private JPanel pnl5 = new JPanel();
	private JPanel jPanel5;
	private static JLabel lblItem;
	public static _JLookup lookupItem;
	public static JTextField txtItem;

	private JPanel pnl6 = new JPanel();
	private static JLabel lblBrand;
	public static JTextField txtBrand;

	private JPanel pnl7 = new JPanel();
	private static JLabel lblDescription;
	public static JTextField txtDescription;

	private JPanel pnl8 = new JPanel();
	private JPanel jPanel8;
	private static JLabel lblSupplier;
	public static _JLookup lookupSupplier;
	public static JTextField txtSupplier;

	private JPanel pnl9 = new JPanel();
	private JLabel lblRemarks;
	public static JTextField txtRemarks;

	private JPanel pnlNavigation;
	private JPanel pnlNavigationEast;
	private static JButton btnNew;
	private static JButton btnEdit;
	public static JButton btnSave; 
	private static JButton btnReset;

	private JPanel pnlInfo2;

	private JPanel pnl10;
	private JPanel pnl10_1;
	private JLabel lblAssetCode;
	//public static JXTextField txtAssetCode;
	private JPanel pnl10_2;
	private JLabel lblMonthlyDep;
	public static _JXFormattedTextField txtMonthlyDep;

	private JPanel pnl11;
	private JPanel pnl11_1;
	private JLabel lblBookValue;
	public static _JXFormattedTextField txtBookValue;
	private JPanel pnl11_2;
	private JLabel lblFromDepDate;
	public static _JDateChooser dateFromDep;

	private JPanel pnl12;
	private JPanel pnl12_1;
	private JLabel lblRetCost;
	public static _JXFormattedTextField txtRetCost;
	private JPanel pnl12_2;
	private JLabel lblToDepDate;
	public static _JDateChooser dateToDep;

	private JPanel pnl13;
	private JLabel lblStatus;
	public static  JTextField txtStatus;

	private JPanel pnl14;
	private JPanel pnl14_1;
	private static JLabel lblNetCost;
	public static _JXFormattedTextField txtNetCost;
	private JPanel pnl14_2;
	private JLabel lblUsefulLife;
	public static _JXFormattedTextField txtUsefulLife;

	private JPanel pnl15;
	private JPanel pnl15_1;
	private JLabel lblSerialNo;
	public static JXTextField txtSerialNo;
	private JPanel pnl15_2;
	private JLabel lblModelNo;
	public static JXTextField txtModelNo;

	private JPanel pnl16;
	private JPanel pnl16_1;
	private JLabel lblOwned;
	private JPanel pnlOwned;
	public static ButtonGroup grpOwned = new ButtonGroup();
	public static JRadioButton rbOwnedYes;
	public static JRadioButton rbOwnedNo;
	private JPanel pnl16_2;
	private JLabel lblCapitalized;
	private JPanel pnlCapitalized;
	public static ButtonGroup grpCapitalized = new ButtonGroup();
	public static JRadioButton rbCapitalizedYes;
	public static JRadioButton rbCapitalizedNo;

	private JPanel pnl17;
	private JPanel pnl17_1;
	private static JLabel lblInsured;
	private JPanel pnlInsured;
	public static ButtonGroup grpInsured = new ButtonGroup();
	public static JRadioButton rbInsuredYes;
	public static JRadioButton rbInsuredNo;
	private JPanel pnl17_2;
	private static JLabel lblInsuredValue;
	public static _JXFormattedTextField txtInsuredValue;

	private JPanel pnl18;
	private JPanel pnl18_1;
	private static JLabel lblReferenceNo;
	public static JXTextField txtReferenceNo;
	private JPanel pnl18_2;
	//private static JLabel lblNoOfAssets;
	//public static JTextField txtNoOfAssets;
	
	public static JCheckBox chkMyAssets;
	protected JGlassLoading glass;
	protected ListSelectionModel rowSM;
	protected ListSelectionListener rowLSL;
	protected Thread loadAllAssets;
	protected JTabbedPane tabbedAssets;
	protected static ButtonGroup grpNE = new ButtonGroup();
	protected DefaultTableModel modelAsset;
	public static String dept_code;
	protected Thread savingAssets;
	private DefaultComboBoxModel jComboBox1Model;
	public static JButton btnPreview;
	public static JLabel lblUser;
	public static _JLookup lookupUser;
	public static JTextField txtUser;
	private JPanel pnl19;
	private JPanel pnl19_1;
	private JLabel lblFound;
	private JPanel pnlFound;
	public static JRadioButton rbFoundYes;
	public static JRadioButton rbFoundNo;
	public static ButtonGroup grpFound = new ButtonGroup();
	private JPanel pnl19_2;
	public static JComboBox cmbstatus;
	public static String co_id;
	public static String co_name;
	public static String co_logo;
	public static String division_code;
	public String Item;
	private JPanel pnl13_1;
	private JPanel pnl13_2;
	private JLabel lblrplf;
	private _JXFormattedTextField txtrplf;
	public static JComboBox cmbremarks;
	private JPanel pnl9_1;
	public static JButton btnaccesories;
	public static String co_alias="VDC";
	public static modelAssetMonitoring modelAssets;
	public static Integer assettoreplace;
	private JLabel lblLocation;

	protected String location_id;

	public static JDialog dialog;
	public static JButton btnattachment;
	public static JButton btnviewattachment;
	public static _JLookup lookupLocation;
	
	public static String sticker_count="";
	public static String emp_code="";
	public static String loc_id;

	public panelAssetInformation2() {
		initGUI();
	}

	public panelAssetInformation2(LayoutManager layout) {
		super(layout);
		initGUI();
	}

	public panelAssetInformation2(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		initGUI();
	}

	public panelAssetInformation2(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		initGUI();
	}
	

	@Override
	public void initGUI() {
		this.setLayout(new BorderLayout(5, 5));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 3, 3));
		{
			pnlInfomationCenter = new JPanel(new GridLayout(1, 2, 5, 5));
			this.add(pnlInfomationCenter, BorderLayout.CENTER);
			{
				pnlInfo1 = new JPanel();
				pnlInfomationCenter.add(pnlInfo1);
				pnlInfo1.setLayout(new GridLayout(9, 1, 3, 3));
				{
					pnl1 = new JPanel(new BorderLayout(3, 3));
					pnlInfo1.add(pnl1);
					{
						jPanel1 = new JPanel(new GridLayout(1, 2));
						pnl1.add(jPanel1, BorderLayout.WEST);
						jPanel1.setPreferredSize(new Dimension(160, 25));
						{
							lblAssetNo = new JLabel("Asset No.");
							jPanel1.add(lblAssetNo);
						}
						{
							txtAssetNo = new JTextField();
							jPanel1.add(txtAssetNo);
							txtAssetNo.setEditable(false);
							txtAssetNo.addKeyListener(new KeyAdapter(){
								public void keyReleased(KeyEvent arg0) {
								}
							});
						}
					}
					{
						dateAcquired = new _JDateChooser("MM/dd/yyyy", "##/##/#####", '_');
						pnl1.add(dateAcquired, BorderLayout.EAST);
						dateAcquired.setPreferredSize(new java.awt.Dimension(120, 29));
						dateAcquired.setEnabled(false);
						
					}
					{
						lblAcquired = new JLabel("Date Acquired", JLabel.TRAILING);
						pnl1.add(lblAcquired, BorderLayout.CENTER);
					}
				}
				{
					pnl2 = new JPanel(new BorderLayout(3, 3));
					pnlInfo1.add(pnl2);
					{
						jPanel2 = new JPanel(new GridLayout(1, 2));
						pnl2.add(jPanel2, BorderLayout.WEST);
						jPanel2.setPreferredSize(new Dimension(160, 25));
						{
							lblCompany = new JLabel("Company");
							jPanel2.add(lblCompany);
						}
						{
							lookupCompany = new _JLookup(null , "Company",0);
							jPanel2.add(lookupCompany);
							lookupCompany.setEditable(false);
							lookupCompany.setLookupSQL(getcompany());
							lookupCompany.addLookupListener(new LookupListener() {
								public void lookupPerformed(LookupEvent event) {
									Object[] data = ((_JLookup) event.getSource()).getDataSet();
									if(data != null) {
										txtCompany.setText((String)data[1]);
										
									}
								}
							});
						}
					}
					{
						txtCompany = new JTextField();
						pnl2.add(txtCompany, BorderLayout.CENTER);
						txtCompany.setEditable(false);
					}
				}
				{
					pnl3 = new JPanel(new BorderLayout(3, 3));
					pnlInfo1.add(pnl3);
					{
						jPanel3 = new JPanel(new GridLayout(1, 2));
						pnl3.add(jPanel3, BorderLayout.WEST);
						jPanel3.setPreferredSize(new Dimension(160, 25));
						{
							lblCustodian = new JLabel("*Custodian");
							jPanel3.add(lblCustodian);
						}
						{
							lookupCustodian = new _JLookup(null , "Custodian", 0);
							jPanel3.add(lookupCustodian);
							lookupCustodian.setEditable(false);
							//lookupCustodian.setLookupSQL("select '900767', 'BITUEN, JOHN ERICK RESPICIO'");
							lookupCustodian.setLookupSQL(getCustodian());
							lookupCustodian.addLookupListener(new LookupListener() {
								@Override
								public void lookupPerformed(LookupEvent event) {
									Object[] db = ((_JLookup) event.getSource()).getDataSet();
									if(db != null) {
										String emp_code= (String) db[0];
										String emp_name= (String) db[1];
										
										lookupCustodian.setValue(emp_code);
										txtCustodian.setText(emp_name);
										lookupCategory.requestFocus();
									}
								}
								
							});
							
						}
					}
					{
						txtCustodian = new JTextField();
						pnl3.add(txtCustodian, BorderLayout.CENTER);
						txtCustodian.setEditable(false);
					}
				}
				{
					pnl4 = new JPanel(new BorderLayout(3, 3));
					pnlInfo1.add(pnl4);
					{
						jPanel4 = new JPanel(new GridLayout(1, 2));
						pnl4.add(jPanel4, BorderLayout.WEST);
						jPanel4.setPreferredSize(new Dimension(160, 25));
						{
							lblCategory = new JLabel("*Category");
							jPanel4.add(lblCategory);
						}
						{
							lookupCategory = new _JLookup(null , "Category");
							jPanel4.add(lookupCategory);
							lookupCategory.setReturnColumn(0);
							lookupCategory.setEditable(false);
							lookupCategory.setLookupSQL(getCategory());
							lookupCategory.addLookupListener(new LookupListener() {
								@Override
								public void lookupPerformed(LookupEvent event) {
									Object[] data = ((_JLookup) event.getSource()).getDataSet();
									if(data != null){
										txtCategory.setText((String)data[1]);
									}
								}
							});
						}
					}
					{
						txtCategory = new JTextField();
						pnl4.add(txtCategory, BorderLayout.CENTER);
						txtCategory.setEditable(false);
					}
				}
				{
					pnl5 = new JPanel(new BorderLayout(3, 3));
					pnlInfo1.add(pnl5);
					{
						jPanel5 = new JPanel(new GridLayout(1, 2));
						pnl5.add(jPanel5, BorderLayout.WEST);
						jPanel5.setPreferredSize(new Dimension(160, 25));
						{
							lblItem = new JLabel("*Item");
							jPanel5.add(lblItem);
						}
						{
							lookupItem = new _JLookup(null , "Item" );
							jPanel5.add(lookupItem);
							lookupItem.setReturnColumn(0);
							lookupItem.setEnabled(false);
							//lookupItem.setLookupSQL(_AssetMonitoring.getItem(lookupCategory.getValue()));
							lookupItem.setLookupSQL("select '01', 'Car'");
							lookupItem.addLookupListener(new LookupListener() {
								public void lookupPerformed(LookupEvent event) {
									Object[] data = ((_JLookup) event.getSource()).getDataSet();
									if (data != null) {
										String item_name = (String) data[1];
										txtItem.setText(item_name);
									}
								}
							});
						}
					}
					{
						txtItem = new JTextField();
						pnl5.add(txtItem, BorderLayout.CENTER);
						txtItem.setEditable(false);
					}
//					{
//						btnaccesories= new JButton("...");
//						pnl5.add(btnaccesories, BorderLayout.EAST);
//						btnaccesories.setEnabled(false);
//						btnaccesories.setPreferredSize(new Dimension(30, 0));
//						btnaccesories.addActionListener(new ActionListener() {
//							public void actionPerformed(ActionEvent arg0) 
//							{
//							}
//						});
//					}
				}
				{
					pnl6 = new JPanel(new BorderLayout());
					pnlInfo1.add(pnl6);
					{
						lblBrand = new JLabel("*Brand");
						pnl6.add(lblBrand, BorderLayout.WEST);
						lblBrand.setPreferredSize(new Dimension(80, 25));
					}
					{
						txtBrand = new JTextField();
						pnl6.add(txtBrand, BorderLayout.CENTER);
						txtBrand.setEditable(false);
						txtBrand.setToolTipText("Please do not input comma.");
						txtBrand.addKeyListener(new KeyListener() {
							
							@Override
							public void keyTyped(KeyEvent e) {
								
							}
							
							@Override
							public void keyReleased(KeyEvent e) {

							}
							
							@Override
							public void keyPressed(KeyEvent e) {
								if (e.getKeyCode()==44) {
									e.consume();
									
									JOptionPane.showMessageDialog(null, "Please remove comma.");
								}
							}
						});
					}
				}
				{
					pnl7 = new JPanel(new BorderLayout());
					pnlInfo1.add(pnl7);
					{
						lblDescription = new JLabel("*Description");
						pnl7.add(lblDescription, BorderLayout.WEST);
						lblDescription.setPreferredSize(new Dimension(80, 25));
					}
					{
						txtDescription = new JTextField();
						pnl7.add(txtDescription, BorderLayout.CENTER);
						txtDescription.setEditable(false);
						txtDescription.setToolTipText("Please do not input comma.");
						txtDescription.addKeyListener(new KeyListener() {
							
							@Override
							public void keyTyped(KeyEvent e) {
								
							}
							@Override
							public void keyReleased(KeyEvent e) {
								if(e.getKeyCode()==KeyEvent.VK_COMMA){
									e.consume();
									
									JOptionPane.showMessageDialog(getTopLevelAncestor(), "Please remove comma.");
								}
							}
							@Override
							public void keyPressed(KeyEvent e) {
							}
						});
					}
				}
				{
					pnl8 = new JPanel(new BorderLayout(3, 3));
					pnlInfo1.add(pnl8);
					{
						jPanel8 = new JPanel(new GridLayout(1, 2));
						pnl8.add(jPanel8, BorderLayout.WEST);
						jPanel8.setPreferredSize(new Dimension(160, 25));
						{
							lblSupplier = new JLabel("*Supplier");
							jPanel8.add(lblSupplier);
						}
						{
							lookupSupplier = new _JLookup(null , "Supplier", 0);
							jPanel8.add(lookupSupplier);
							lookupSupplier.setEditable(false);
							lookupSupplier.setLookupSQL(getsupplier());
							//lookupSupplier.setLookupSQL("Select '01', 'Test Supplier'");
							lookupSupplier.addLookupListener(new LookupListener() {
								
								@Override
								public void lookupPerformed(LookupEvent event) {
									Object [] supplier= ((_JLookup) event.getSource()).getDataSet();
									if (supplier !=null){
										txtSupplier.setText((String)supplier[1]);
									}
								}
							});
						}
					}
					{
						txtSupplier = new JTextField();
						pnl8.add(txtSupplier, BorderLayout.CENTER);
						txtSupplier.setEditable(false);
					}
				}
				{
					pnl9 = new JPanel(new BorderLayout(3,3));
					pnlInfo1.add(pnl9);
					{
						lblRemarks = new JLabel("Remarks1");
						pnl9.add(lblRemarks, BorderLayout.WEST);
						lblRemarks.setPreferredSize(new Dimension(77, 25));
					}
					{
						txtRemarks = new JTextField();
						pnl9.add(txtRemarks, BorderLayout.CENTER);
						txtRemarks.setEditable(false);
					}
					{
						pnl9_1 = new JPanel(new BorderLayout(3,3));
						pnl9.add(pnl9_1, BorderLayout.EAST);
						pnl9_1.setPreferredSize(new Dimension(170,0));
//						{
//							lblremarks2 = new JLabel("Remarks2");
//							pnl9_1.add(lblremarks2, BorderLayout.WEST);
//							lblremarks2.setPreferredSize(new Dimension(65,0));
//						}
					}
		
				}
			}
			{
				pnlInfo2 = new JPanel(new GridLayout(10, 1, 3, 3));
				pnlInfomationCenter.add(pnlInfo2);
				{
					pnl10 = new JPanel(new GridLayout(1, 2, 3, 3));
					pnlInfo2.add(pnl10);
					{
						pnl10_1 = new JPanel(new BorderLayout());
						pnl10.add(pnl10_1);
						{
							lblMonthlyDep = new JLabel("Monthly Dep.", JLabel.TRAILING);
							pnl10_1.add(lblMonthlyDep, BorderLayout.WEST);
							lblMonthlyDep.setPreferredSize(new Dimension(75, 0));
						}
						{
							txtMonthlyDep = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
							pnl10_1.add(txtMonthlyDep, BorderLayout.CENTER);
							txtMonthlyDep.setEditable(false);
							txtMonthlyDep.setFormatterFactory(_JXFormattedTextField.DECIMAL);
						}
					}
//					{
//						pnl10_2 = new JPanel(new BorderLayout(5, 5));
//						pnl10.add(pnl10_2);
//						{
//							lblMonthlyDep = new JLabel("Monthly Dep.", JLabel.TRAILING);
//							pnl10_2.add(lblMonthlyDep, BorderLayout.WEST);
//							lblMonthlyDep.setPreferredSize(new Dimension(100, 0));
//						}
//						{
//							txtMonthlyDep = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
//							pnl10_2.add(txtMonthlyDep, BorderLayout.CENTER);
//							txtMonthlyDep.setEditable(false);
//							txtMonthlyDep.setFormatterFactory(_JXFormattedTextField.DECIMAL);
//						}
//					}
				}
				{
					pnl11 = new JPanel(new GridLayout(1, 2, 3, 3));
					pnlInfo2.add(pnl11);
					{
						pnl11_1 = new JPanel(new BorderLayout());
						pnl11.add(pnl11_1);
						{
							lblBookValue = new JLabel("Book Value");
							pnl11_1.add(lblBookValue, BorderLayout.WEST);
							lblBookValue.setPreferredSize(new Dimension(75, 0));
						}
						{
							txtBookValue = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
							pnl11_1.add(txtBookValue, BorderLayout.CENTER);
							txtBookValue.setEditable(false);
							txtBookValue.setFormatterFactory(_JXFormattedTextField.DECIMAL);
						}
					}
					{
						pnl11_2 = new JPanel(new BorderLayout(5, 5));
						pnl11.add(pnl11_2);
						{
							lblFromDepDate = new JLabel("From Dep. Date", JLabel.TRAILING);
							pnl11_2.add(lblFromDepDate, BorderLayout.WEST);
							lblFromDepDate.setPreferredSize(new Dimension(100, 0));
							
						}
						{
							dateFromDep = new _JDateChooser("MM/dd/yyyy", "##/##/#####", '_');
							pnl11_2.add(dateFromDep, BorderLayout.CENTER);
							dateFromDep.setEditable(false);
							dateFromDep.setEnabled(false);
							
							
						}
					}
				}
				{
					pnl12 = new JPanel(new GridLayout(1, 2, 3, 3));
					pnlInfo2.add(pnl12);
					{
						pnl12_1 = new JPanel(new BorderLayout());
						pnl12.add(pnl12_1);
						{
							lblRetCost = new JLabel("Ret. Cost");
							pnl12_1.add(lblRetCost, BorderLayout.WEST);
							lblRetCost.setPreferredSize(new Dimension(75, 0));
						}
						{
							txtRetCost = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
							pnl12_1.add(txtRetCost, BorderLayout.CENTER);
							txtRetCost.setEditable(false);
							txtRetCost.setFormatterFactory(_JXFormattedTextField.DECIMAL);
						}
					}
					{
						pnl12_2 = new JPanel(new BorderLayout(5, 5));
						pnl12.add(pnl12_2);
						{
							lblToDepDate = new JLabel("To Dep. Date", JLabel.TRAILING);
							pnl12_2.add(lblToDepDate, BorderLayout.WEST);
							lblToDepDate.setPreferredSize(new Dimension(100, 0));
							
						}
						{
							dateToDep = new _JDateChooser("MM/dd/yyyy", "##/##/#####", '_');
							pnl12_2.add(dateToDep, BorderLayout.CENTER);
							dateToDep.setEditable(false);
							dateToDep.setEnabled(false);
						}
					}
				}
				{
					pnl13 = new JPanel(new GridLayout(1, 2, 3, 3));
					pnlInfo2.add(pnl13);
					{
						pnl13_1 = new JPanel(new BorderLayout());
						pnl13.add(pnl13_1);
						{
							lblStatus = new JLabel("Status");
							pnl13_1.add(lblStatus, BorderLayout.WEST);
							lblStatus.setPreferredSize(new Dimension(75, 0));
						}
						{
							jComboBox1Model = new DefaultComboBoxModel(new String[] {"A","I","FOR REPAIR"});
							cmbstatus= new JComboBox();
							cmbstatus.setModel(jComboBox1Model);
							cmbstatus.setEditable(false);
							cmbstatus.setEnabled(false);
							pnl13_1.add(cmbstatus,BorderLayout.CENTER);
						}
					}
					{
						pnl13_2 = new JPanel(new BorderLayout(5,5));
						pnl13.add(pnl13_2);
						
						{
							lblrplf = new JLabel("RPLF No..", JLabel.TRAILING);
							pnl13_2.add(lblrplf, BorderLayout.WEST);
							lblrplf.setPreferredSize(new Dimension(100, 0));
						}
						{
							txtrplf = new _JXFormattedTextField(JXFormattedTextField.CENTER);
							pnl13_2.add(txtrplf, BorderLayout.CENTER);
							txtrplf.setEditable(false);
							txtrplf.setFormatterFactory(_JXFormattedTextField.INTEGER);
						}
					}
					
					
				}
				{
					pnl14 = new JPanel(new GridLayout(1, 2, 3, 3));
					pnlInfo2.add(pnl14);
					{
						pnl14_1 = new JPanel(new BorderLayout());
						pnl14.add(pnl14_1);
						{
							lblNetCost = new JLabel("*Net Cost");
							pnl14_1.add(lblNetCost, BorderLayout.WEST);
							lblNetCost.setPreferredSize(new Dimension(75, 0));
						}
						{
							txtNetCost = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
							pnl14_1.add(txtNetCost, BorderLayout.CENTER);
							txtNetCost.setFormatterFactory(_JXFormattedTextField.DECIMAL);
						}
					}
					{
						pnl14_2 = new JPanel(new BorderLayout(5, 5));
						pnl14.add(pnl14_2);
						{
							lblUsefulLife = new JLabel("Useful Life Mos.", JLabel.TRAILING);
							pnl14_2.add(lblUsefulLife, BorderLayout.WEST);
							lblUsefulLife.setPreferredSize(new Dimension(100, 0));
						}
						{
							txtUsefulLife = new _JXFormattedTextField(JXFormattedTextField.CENTER);
							pnl14_2.add(txtUsefulLife, BorderLayout.CENTER);
							txtUsefulLife.setEditable(false);
							txtUsefulLife.setFormatterFactory(_JXFormattedTextField.INTEGER);
						}
					}
				}
				{
					pnl15 = new JPanel(new GridLayout(1, 2, 3, 3));
					pnlInfo2.add(pnl15);
					{
						pnl15_1 = new JPanel(new BorderLayout());
						pnl15.add(pnl15_1);
						{
							lblSerialNo = new JLabel("Serial No.");
							pnl15_1.add(lblSerialNo, BorderLayout.WEST);
							lblSerialNo.setPreferredSize(new Dimension(75, 0));
						}
						{
							txtSerialNo = new JXTextField();
							txtSerialNo.setEditable(false);
							pnl15_1.add(txtSerialNo, BorderLayout.CENTER);
						}
					}
					{
						pnl15_2 = new JPanel(new BorderLayout(5, 5));
						pnl15.add(pnl15_2);
						{
							lblModelNo = new JLabel("Model No.", JLabel.TRAILING);
							pnl15_2.add(lblModelNo, BorderLayout.WEST);
							lblModelNo.setPreferredSize(new Dimension(100, 0));
						}
						{
							txtModelNo = new JXTextField();
							txtModelNo.setEditable(false);
							pnl15_2.add(txtModelNo, BorderLayout.CENTER);
						}
					}
				}
				{
					pnl16 = new JPanel(new GridLayout(1, 2, 3, 3));
					pnlInfo2.add(pnl16);
					{
						pnl16_1 = new JPanel(new BorderLayout());
						pnl16.add(pnl16_1);
						{
							lblOwned = new JLabel("*Owned");
							pnl16_1.add(lblOwned, BorderLayout.WEST);
							lblOwned.setPreferredSize(new Dimension(75, 0));
						}
						{
							pnlOwned = new JPanel(new GridLayout(1, 2));
							pnl16_1.add(pnlOwned, BorderLayout.CENTER);
							pnlOwned.setBorder(BorderFactory.createTitledBorder(_LINE_BORDER));;
							{
								rbOwnedYes = new JRadioButton("Yes");
								rbOwnedYes.setEnabled(false);
								pnlOwned.add(rbOwnedYes);
								grpOwned.add(rbOwnedYes);
							}
							{
								rbOwnedNo = new JRadioButton("No");
								rbOwnedNo.setEnabled(false);
								pnlOwned.add(rbOwnedNo);
								grpOwned.add(rbOwnedNo);
							}
						}
					}
					{
						pnl16_2 = new JPanel(new BorderLayout(5, 5));
						pnl16.add(pnl16_2);
						{
							lblCapitalized = new JLabel("*Capitalized", JLabel.TRAILING);
							pnl16_2.add(lblCapitalized, BorderLayout.WEST);
							lblCapitalized.setPreferredSize(new Dimension(100, 0));
						}
						{
							pnlCapitalized = new JPanel(new GridLayout(1, 2));
							pnl16_2.add(pnlCapitalized, BorderLayout.CENTER);
							pnlCapitalized.setBorder(BorderFactory.createTitledBorder(_LINE_BORDER));
							{
								rbCapitalizedYes = new JRadioButton("Yes");
								rbCapitalizedYes.setEnabled(false);
								pnlCapitalized.add(rbCapitalizedYes);
								grpCapitalized.add(rbCapitalizedYes);
							}
							{
								rbCapitalizedNo = new JRadioButton("No");
								rbCapitalizedNo.setEnabled(false);
								pnlCapitalized.add(rbCapitalizedNo);
								grpCapitalized.add(rbCapitalizedNo);
							}
						}
					}
				}
				{
					pnl17 = new JPanel(new GridLayout(1, 2, 3, 3));
					pnlInfo2.add(pnl17);
					{
						pnl17_1 = new JPanel(new BorderLayout());
						pnl17.add(pnl17_1);
						{
							lblInsured = new JLabel("*Insured");
							pnl17_1.add(lblInsured, BorderLayout.WEST);
							lblInsured.setPreferredSize(new Dimension(75, 0));
						}
						{
							pnlInsured = new JPanel(new GridLayout(1, 2));
							pnl17_1.add(pnlInsured, BorderLayout.CENTER);
							pnlInsured.setBorder(BorderFactory.createTitledBorder(_LINE_BORDER));;
							{
								rbInsuredYes = new JRadioButton("Yes");
								pnlInsured.add(rbInsuredYes);
								rbInsuredYes.setEnabled(false);
								grpInsured.add(rbInsuredYes);
								rbInsuredYes.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										
									}
								});
							}
							{
								rbInsuredNo = new JRadioButton("No");
								pnlInsured.add(rbInsuredNo);
								rbInsuredNo.setEnabled(false);
								grpInsured.add(rbInsuredNo);
								rbInsuredNo.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
									}
								});
							}
						}
					}
					{
						pnl17_2 = new JPanel(new BorderLayout(5, 5));
						pnl17.add(pnl17_2);
						{
							lblInsuredValue = new JLabel("Insured Value", JLabel.TRAILING);
							pnl17_2.add(lblInsuredValue, BorderLayout.WEST);
							lblInsuredValue.setPreferredSize(new Dimension(100, 0));
						}
						{
							txtInsuredValue = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
							pnl17_2.add(txtInsuredValue, BorderLayout.CENTER);
							txtInsuredValue.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							txtInsuredValue.setEditable(false);
							
						}
					}
				}
				{
					pnl18 = new JPanel(new GridLayout(1, 2, 3, 3));
					pnlInfo2.add(pnl18);
					{
						pnl18_1 = new JPanel(new BorderLayout());
						pnl18.add(pnl18_1);
						{
							lblReferenceNo = new JLabel("Ref. No.");
							pnl18_1.add(lblReferenceNo, BorderLayout.WEST);
							lblReferenceNo.setPreferredSize(new Dimension(75, 0));
						}
						{
							txtReferenceNo = new JXTextField();
							pnl18_1.add(txtReferenceNo, BorderLayout.CENTER);
							txtReferenceNo.setEditable(false);
						}
					}
//					{
//						pnl18_2 = new JPanel(new BorderLayout(5, 5));
//						pnl18.add(pnl18_2);
//						{
//							lblNoOfAssets = new JLabel("No. of Assets", JLabel.TRAILING);
//							pnl18_2.add(lblNoOfAssets, BorderLayout.WEST);
//							lblNoOfAssets.setPreferredSize(new Dimension(100, 0));
//						}
//						{
//							txtNoOfAssets = new JTextField();
//							pnl18_2.add(txtNoOfAssets, BorderLayout.CENTER);
//						}
//					}
					{
						pnl19 = new JPanel(new GridLayout(1, 2,3,3));
						pnlInfo2.add(pnl19);
						{
							pnl19_1= new JPanel(new BorderLayout(3,3));
							pnl19.add(pnl19_1);
							{
								lblFound= new JLabel("Item Found");
								pnl19_1.add(lblFound, BorderLayout.WEST);
								lblFound.setPreferredSize(new Dimension(75, 0));
							}
							{
								pnlFound=new JPanel(new GridLayout(1, 2));
								pnl19_1.add(pnlFound, BorderLayout.CENTER);
								pnlFound.setPreferredSize(new Dimension(150, 0));
								{
									rbFoundYes= new JRadioButton("Yes");
									pnlFound.add(rbFoundYes);
									grpFound.add(rbFoundYes);
									
								}
								{
									rbFoundNo= new JRadioButton("No");
									pnlFound.add(rbFoundNo);
									grpFound.add(rbFoundNo);
									
								}
							}
						}
						{
							pnl19_2= new JPanel(new BorderLayout(3,3));
							pnl19.add(pnl19_2);
							{
								lblLocation= new JLabel("Location", JLabel.TRAILING);
								pnl19_2.add(lblLocation, BorderLayout.WEST);
								lblLocation.setPreferredSize(new Dimension(100, 0));
							}
							{
								lookupLocation= new _JLookup("", 1);
								pnl19_2.add(lookupLocation,BorderLayout.CENTER);
								lookupLocation.setEditable(false);
								lookupLocation.setLookupSQL("select * from rf_asset_location ");
								lookupLocation.addLookupListener(new LookupListener() {

									public void lookupPerformed(LookupEvent event) {
										Object [] db = ((_JLookup)event.getSource()).getDataSet();
										
										if(db !=null){
											location_id = (String)db[0];
									   }
									}
								});
							}
						}
					}
				}
			}
		}
		{
			pnlNavigation = new JPanel(new BorderLayout());
			this.add(pnlNavigation, BorderLayout.SOUTH);
			pnlNavigation.setPreferredSize(new Dimension(0, 28));
			{
				pnlNavigationEast = new JPanel(new GridLayout(1, 7, 3, 3));
				pnlNavigation.add(pnlNavigationEast, BorderLayout.CENTER);
//				{
//					btnPreview= new JButton("Preview Sticker");
//					pnlNavigationEast.add(btnPreview);
//					btnPreview.setEnabled(false);
//					btnPreview.addActionListener(this);
//				}
//				{
//					btnattachment = new JButton("Attachments");
//					pnlNavigationEast.add(btnattachment);
//					btnattachment.addActionListener(this);
//					
//				}
				{
					btnNew = new JButton("New");
					pnlNavigationEast.add(btnNew);
					grpNE.add(btnNew);
					btnNew.setActionCommand("new");
					btnNew.addActionListener(this);
				}
				{
					btnEdit = new JButton("Edit");
					pnlNavigationEast.add(btnEdit);
					grpNE.add(btnEdit);
					btnEdit.setActionCommand("edit");
					btnEdit.addActionListener(this);
				}
				{
					btnSave = new JButton("Save");
					pnlNavigationEast.add(btnSave);
					btnSave.setEnabled(false);
					btnSave.setActionCommand("save");
					btnSave.addActionListener(this);

				}
				{
					btnReset = new JButton("Reset");
					pnlNavigationEast.add(btnReset);
					btnReset.setEnabled(true);
					btnReset.setActionCommand("reset");
					btnReset.addActionListener(this);
				}	
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("new")) {
			grpNE.setSelected(btnNew.getModel(), true);
			newState();
		}
		
		if(e.getActionCommand().equals("reset")) {
			resetInformation();
		}
		if(e.getActionCommand().equals("edit")) {
			grpNE.setSelected(btnEdit.getModel(), true);
			btnState(false, false, true, true);
		}
		if(e.getActionCommand().equals("save")) {
			System.out.println("Save");
			System.out.println("toAdd: "+toAdd());
			if(toAdd()) {
				
				int toSave = JOptionPane.showConfirmDialog(getTopLevelAncestor(), "Are all entries correct?", "Save", JOptionPane.YES_NO_OPTION);
				if (toSave==JOptionPane.YES_OPTION){
					if(grpNE.isSelected(btnNew.getModel())) {
						System.out.println("btnNew is selected");
						addAsset(
						lookupItem.getText(), 					//item_id
						txtItem.getText()+", "+txtBrand.getText().replace("'", "''")+", "+txtDescription.getText().replace("'", "''"),//asset_name
						txtNetCost.getText().replace(",", ""),	//asset_cost
						Integer.parseInt(txtUsefulLife.getText()),//asset_ulm
						txtAssetNo.getText(),					//asset_no
						txtSerialNo.getText(),					//asset_serial
						txtModelNo.getText(),					//asset_model
						txtNetCost.getText().replace(",", ""),	//asset_bk_val
						dateAcquired.getDate().toString(),		//date_acquired
						(rbOwnedYes.isSelected() ? "true":"false"),//owned
						(rbInsuredYes.isSelected() ? "true":"false"),//insured
						txtInsuredValue.getText().replace(",", ""),//insured_value
						txtRemarks.getText(),					//remarks
						lookupSupplier.getValue(),				//supp_id
						lookupCustodian.getValue(),				//current_cust
						txtReferenceNo.getText(),				//reference_no
						txtNetCost.getText().replace(",", ""), //original_cost
						(rbCapitalizedYes.isSelected() ? "true":"false"),//capitalized
			 			//panelAssetInformation2.lookupCompany.getValue(),//comp_id
						txtCustodian.getText().trim(),			//custodian_name
						//Integer.valueOf(txtNoOfAssets.getText()),//noofAsset
					    true,									//toPrint
					    panelAssetInformation2.lookupCompany.getValue(),//co_id
						(rbFoundYes.isSelected()?"true":"false"),//item_found
						Item,									//item_name
						txtBrand.getText(),						//brand
						txtDescription.getText(),				//description
						dept_code,								//dept_code
						location_id								//location_id
						);
						JOptionPane.showMessageDialog(getTopLevelAncestor(), "New Asset has been Added", "Save", JOptionPane.INFORMATION_MESSAGE);
						resetInformation();
						btnState(true, false, false, true);
					}else {
						System.out.println("elseee");
						if(grpNE.isSelected(btnEdit.getModel())) {
							System.out.println("btnEdit is selected");
						}
					}
					
				}
			}else {
				System.out.println("Else save");
				if(txtCustodian.getText().equals("")){
					JOptionPane.showConfirmDialog(getTopLevelAncestor(), "Please select custodian", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				if(lookupCompany.getText().equals("")){
					JOptionPane.showConfirmDialog(getTopLevelAncestor(), "Please select company", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				if(lookupItem.getText().equals("")){
					JOptionPane.showConfirmDialog(getTopLevelAncestor(), "Please select item", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				if (txtBrand.getText().equals("")){
					JOptionPane.showConfirmDialog(getTopLevelAncestor(), "Please input item brand", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				if(txtDescription.getText().equals("")){
					JOptionPane.showConfirmDialog(getTopLevelAncestor(), "Please input item description", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				if(txtUsefulLife.getText().equals("")||txtUsefulLife.getText().equals(0.00)){
					JOptionPane.showConfirmDialog(getTopLevelAncestor(), "Please input item ULM", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				if(txtNetCost.getText().equals("")||txtNetCost.getText().equals("0.00")){
					//JOptionPane.showConfirmDialog(getTopLevelAncestor(), "Please input item net cost", "Warning", JOptionPane.WARNING_MESSAGE);
					txtNetCost.setText(JOptionPane.showInputDialog(getTopLevelAncestor(), "Please input net cost."));
				}
				if (txtSerialNo.getText().equals("")){
					JOptionPane.showConfirmDialog(getTopLevelAncestor(), "Please put serial number of the asset.", "Warning",JOptionPane.WARNING_MESSAGE);
				}
				if (txtModelNo.getText().equals("")){
					JOptionPane.showConfirmDialog(getTopLevelAncestor(), "Please put model of the asset.", "Warning",JOptionPane.WARNING_MESSAGE);
				}
				if(txtReferenceNo.getText().equals("")){
					JOptionPane.showConfirmDialog(getTopLevelAncestor(), "Please input item reference no.", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				if(rbInsuredYes.isSelected()){
					if (txtInsuredValue.getText().equals("0.00")||txtInsuredValue.getText().equals("")){
						//JOptionPane.showConfirmDialog(getTopLevelAncestor(), "Please input item insured value.", "Warning", JOptionPane.WARNING_MESSAGE);
						txtInsuredValue.setText(JOptionPane.showInputDialog("Please input insured amount."));
					}
				}
				if(lookupLocation.getText().equals("")){
					JOptionPane.showConfirmDialog(getTopLevelAncestor(), "Please select asset location.", "Warning", JOptionPane.WARNING_MESSAGE);
				}
//				if( Double.parseDouble(txtNetCost.getText().replace(",", "")) <= 5000.00){
//					JOptionPane.showConfirmDialog(getTopLevelAncestor(), "Asset cost is below 5,000.00", "Error", JOptionPane.ERROR_MESSAGE);
//				}
			}
		}
	}
	
	public static String getCategory(){
		return "select category_id as \"ID\", category_name as \"Category Name\" from mf_asset_category order by category_name";
	}
	
	private String getsupplier () {
		return "select entity_id, entity_name from rf_entity where status_id = 'A' ";
	}
	
	private void buttonstate (Boolean addnew, Boolean edit, Boolean save, Boolean reset) {
		btnNew.setEnabled(addnew);
		btnEdit.setEnabled(edit);
		btnSave.setEnabled(save);
		btnReset.setEnabled(reset);
		
	}
	public static void addAsset(
			
			String item_id,
			String asset_name,
			String asset_cost,
			Integer asset_ulm,
			String asset_no,
			String asset_serial, 
			String asset_model,
			String asset_bk_val,
			String date_acquired,
			String owned,
			String insured,
			String insured_value,
			String remarks,
			String supp_id,
			String current_cust, 
			String reference_no, 
			String original_cost, 
			String capitalized, 
			//String comp_id,
			String custodian_name,
			//int noofAsset,
			boolean toPrint,
			String co_id,
			String item_found,
			String item_name,
			String brand,
			String description,
			String dept_code,
			String location_id
			){
		pgUpdate db=new pgUpdate();
		
//		int num = Integer.parseInt(asset_no)-1;
//		
//		ArrayList<Boolean> toPrints = new ArrayList<Boolean>();
//		int first=1;
//		String assetToPrint = "";
//		for(int x=0; x < noofAsset; x++)
//		{
//			int asset_number = num+=1;
//			first -= 1;
//			assetToPrint = first==0 ? assetToPrint + asset_number:assetToPrint + ", "+asset_number;
			//toPrints.add(true);
			
			String strSQL =
					"INSERT INTO rf_asset( \n" +
							//"asset_code, \n" +
							"item_id, \n" +
							"asset_name, \n" +
							"asset_cost, \n" +
							"asset_ulm, \n" +
							"asset_mon_dep, \n" +
							"asset_no, \n" +
							"asset_serial, \n" +
							"asset_model, \n" +
							"asset_bk_val, \n" +
							"asset_ret_cost, \n" +
							"date_acquired, \n" +
							"from_dep, \n" +
							"to_dep, \n" +
							"date_retired, \n" +
							"owned, \n" +
							"insured, \n" +
							"insured_value, \n" +
							"remarks, \n" +
							"status, \n" +
							"supp_id, \n" +
							"current_cust, \n" +
							"added_by, \n" +
							"date_added, \n" +
							"reference_no, \n" +
							"original_cost, \n" +							
							"capitalized, \n" +
							//"comp_id, \n" +
							"co_id, \n" +
							"item_found, \n" +
							"item_name, \n"+
							"brand, \n"+
							"description, \n"+
							"dept_code, \n"+
							//"remarks2, \n"+
							"loc_id,\n"+
							//"replaced_assetno,\n"+
							"date_disposed,\n"+
							"rec_status \n"+
							")\n"+
							"VALUES (\n" +
							//"'"+panelAssetInformation.txtAssetCode.getText()+"', \n" +	//asset_code
							""+item_id+", \n" +									//item_id
							"'"+asset_name+"', \n" +							//asset_name
							""+asset_cost+", \n" +								//asset_cost
							""+asset_ulm+", \n" +								//asset_ulm
							"("+asset_cost+" -1)/"+asset_ulm+", \n" +			//asset_mon_dep
							""+asset_no+", \n" +							//asset_no
							"'"+asset_serial+"', \n" +							//asset_serial
							"'"+asset_model+"', \n" +							//asset_model
							""+asset_bk_val+", \n" +							//asset_bk_val
							"1.00, \n" +										//asset_ret_cost
							"'"+date_acquired+"', \n" +							//date_acquired
							"(select LAST_DAY('"+date_acquired+"'::date) + 1), \n" +//from_dep
							"(select ( date '"+date_acquired+"' + INTERVAL '"+((asset_ulm) + 1)+" MONTH' )), \n" +	//to_dep
							"null, \n" +										//date_retired
							""+owned+", \n" +									//owned
							""+insured+", \n" +									//insured
							""+insured_value+", \n" +							//insured_value
							"'"+remarks+"', \n" +								//remarks
							"'A', \n" +											//status
							""+supp_id+", \n" +									//supp_id
							"'"+current_cust+"', \n" +							//current_cust
							"'"+UserInfo.EmployeeCode+"', \n" +					//added_by
							"now(), \n" +										//date_added
							"'"+reference_no+"', \n" +							//reference_no
							""+original_cost+", \n" +							//original_cost
							""+capitalized+", \n" +								//capitalized
							//""+comp_id+", \n"+									//comp_id
							"'"+co_id+"', \n"+									//co_id
							""+item_found+",\n"+								//item_found
							"'"+item_name+"',\n"+								//item_name
							"'"+brand+"',\n"+									//item_brand
							"'"+description+"',\n"+								//Description
							"'"+dept_code+"', \n"+ 								//dept_code
							"'"+location_id+"',\n"+								//location id of the asset
							"null, \n"+								//asset number replace by the new item
							"true)";
			
			db.executeUpdate(strSQL, true);

		//}
		db.commit();
		
//		assetToPrint = assetToPrint + "";
//			
//			String strSQL = "\n" + 
//					"select \n" + 
//					"a.asset_code,\n" + 
//					"a.asset_no, \n" + 
//					"a.asset_name, \n" + 
//					"to_char(a.asset_no, 'FM00000000') as anum, \n" + 
//					"c.entity_name as nme,\n" + 
//					"current_date,\n" + 
//					"lower(format('%s%s%s',left(c.first_name,1),left(c.middle_name,1),c.last_name))\n" + 
//					"--lower(substr(c.first_name, left,1)||substr(c.middle_name, left,1)||c.last_name) as cuser \n" + 
//					"from rf_asset  a \n" + 
//					"left join rf_employee b on a.current_cust::varchar=b.emp_code\n" + 
//					"left join rf_entity as c on b.entity_id=c.entity_id\n" + 
//					"where a.asset_no='"+assetToPrint+"' and b.emp_code= '"+UserInfo.EmployeeCode+"'\n" + 
//					"order by a.asset_no";
//			FncSystem.out("assetToPrint", strSQL);
//			System.out.println("");
//			System.out.println("asset_no" + asset_no);
	}
	
	public static String getCustodian(){
		
		String strsql="select  a.emp_code, b.entity_name\n"
				+ "from  rf_employee a\n"
				+ "left join rf_entity b ON a.entity_id=b.entity_id\n"
				+ "left join mf_division as c on a.div_code=c.div_code\n"
				+ "left join mf_exec_office d on a.exec_off_code = d.exec_office_code\n"
				+ "order by b.entity_name";
		
		System.out.println("getCustodian: "+strsql);
		return strsql;
		
	}
	
	
	
	public static void pulloutAsset(String asset_no){
		pgUpdate db=new pgUpdate();
		
		String strSQL="update rf_asset set status = 'FOR REPAIR' where asset_no ="+asset_no+" ";
		
		db.executeUpdate(strSQL, true);
		db.commit();
	}
	 public static String replaceasset(){
		 
		 String strsql="select asset_no,asset_name,status,current_cust from rf_asset  where status='A'";
		 
		 return strsql;
	 }
	
	
	public static  void newState(){
		
		cmbstatus.setEnabled(true);
		txtInsuredValue.setEditable(false);
		//AssetMonitoring2.disbletableAssets(false);
		AssetMonitoring2.tblAssets.setEnabled(false);
		dateAcquired.setDate(FncGlobal.getDateToday());
		lookupCustodian.setEnabled(true);
		lookupCustodian.setEditable(true);
		txtAssetNo.setText(_AssetMonitoring.getAssetNo());
		grpNE.setSelected(btnNew.getModel(), true);
		dateAcquired.getCalendarButton().setEnabled(true);
		
		//lblCompany.setFont(null);
//		lookupCompany.setEnabled(false);
//		lookupCompany.setValue(AssetMonitoring2.lookupselectcompany.getValue());
//		lookupCompany.setEditable(false);
//		txtCompany.setText(AssetMonitoring2.txtcompany.getText());
		
		System.out.println("Company:: "+ AssetMonitoring2.lookupselectcompany.getValue());
		
		lblCustodian.setName("*Custodian");
		if(AssetMonitoring2.lookupselectcompany.getValue() == null) {
			lookupCompany.setEnabled(true);
			lookupCompany.setEditable(true);
		}else {
			lookupCompany.setEnabled(false); 
			lookupCompany.setValue(AssetMonitoring2.lookupselectcompany.getValue());
			lookupCompany.setEditable(false);
			txtCompany.setText(AssetMonitoring2.txtcompany.getText());
		}
		
		lookupCustodian.setText("");
		txtCustodian.setText("");
		
		lblCategory.setFont(null);
		lookupCategory.setEnabled(true);
		lookupCategory.setEditable(true);
		lookupCategory.setText("");
		txtCategory.setText("");
		
		lblItem.setFont(null);
		lookupItem.setEnabled(true);
		lookupItem.setEditable(true);
		lookupItem.setText("");
		txtItem.setText("");
		
		lblBrand.setFont(null);
		txtBrand.setText("");
		txtBrand.setEditable(true);
		

		lblDescription.setFont(null);
		txtDescription.setText("");
		txtDescription.setEditable(true);

		lblSupplier.setFont(null);
		lookupSupplier.setEnabled(true);
		lookupSupplier.setEditable(true);
		lookupSupplier.setText("");
		txtSupplier.setText("");

		//txtAssetCode.setText("");

		txtRemarks.setText("NEW ITEM");
		txtRemarks.setEditable(true);

		lblNetCost.setFont(null);
		txtNetCost.setText("");
		txtNetCost.setEditable(true);

		txtUsefulLife.setText("36");
		txtUsefulLife.setEditable(true);

		txtSerialNo.setEditable(true);

		txtModelNo.setEditable(true);

		
		grpOwned.clearSelection();
		rbOwnedYes.setEnabled(true);
		rbOwnedYes.setSelected(true);
		rbOwnedNo.setEnabled(true);

		
		grpCapitalized.clearSelection();
		rbCapitalizedYes.setEnabled(true);
		rbCapitalizedNo.setEnabled(true);
		rbCapitalizedNo.setSelected(true);

		lblInsured.setFont(null);
		grpInsured.clearSelection();
		rbInsuredYes.setEnabled(true);
		rbInsuredNo.setEnabled(true);
		rbInsuredNo.setSelected(true);
		
		grpFound.clearSelection();
		rbFoundNo.setEnabled(true);
		rbFoundYes.setEnabled(true);
		rbFoundYes.setSelected(true);
		
		lblInsuredValue.setFont(null);
		txtInsuredValue.setText("0.00");

		lblReferenceNo.setFont(null);
		txtReferenceNo.setText("No Reference #");
		txtReferenceNo.setEditable(true);

		//lblNoOfAssets .setFont(null);
		//txtNoOfAssets .setText("1");
		//lblNoOfAssets .setEnabled(true);
		

		txtMonthlyDep.setText("0.00");
		
		txtBookValue.setText("0.00");
		txtBookValue.setEditable(false);
		
		txtRetCost.setText("0.00");
		lookupLocation.setEditable(true);
		lookupLocation.setText("");
		
		btnState(false, false, true, true);
	}
	
public static void resetInformation(){
		new Thread(new  Runnable() {
			public void run() {
				FncGlobal.startProgress("Refreshing fields, please wait.");
				AssetMonitoring2.lookupselectcompany.setValue(co_id);
				AssetMonitoring2.txtcompany.setText(co_name);
				AssetMonitoring2.chkinactiveassets.setSelected(false);
				AssetMonitoring2.chkinactiveemp.setSelected(false);
				AssetMonitoring2.modelAssets.clear();
				AssetMonitoring2.displayAllAssets(false,false);
				AssetMonitoring2.tblAssets.setEnabled(true);
				AssetMonitoring2.lookupCustodianid.setValue("");
				AssetMonitoring2.txtCustodianid.setText("");
				AssetMonitoring2.lookupLocation.setValue("");
				AssetMonitoring2.txtLocation.setText("");
				//txtAssetNo.setText("");
				dateAcquired.getCalendarButton().setEnabled(false);
				
				lookupCompany.setEditable(false);
				lookupCompany.setValue("");
				txtCompany.setText("");
				
				lookupCustodian.setEditable(false);
				lookupCustodian.setValue("");
				txtCustodian.setText("");
				
				lookupCategory.setEditable(false);
				lookupCategory.setValue("");
				txtCategory.setText("");
				
				lookupItem.setEnabled(false);
				lookupItem.setValue("");
				txtItem.setText("");
				
				txtBrand.setText("");
				txtBrand.setEditable(false);
				txtDescription.setText("");
				txtDescription.setEditable(false);
				
				lookupSupplier.setValue("");
				lookupSupplier.setEditable(false);
				
				txtSupplier.setText("");
				txtSupplier.setEditable(false);
				
				txtRemarks.setText("");
				txtRemarks.setEditable(false);
				
				
//				txtAssetCode.setText("");
//				txtAssetCode.setEditable(false);
				
				txtBookValue.setText("0.00");
				txtBookValue.setEditable(false);
				
				txtRetCost.setText("0.00");
				txtRetCost.setEditable(false);
				
				txtMonthlyDep.setText("0.00");
				txtMonthlyDep.setEditable(false);
				
				dateFromDep.setText("");
				dateFromDep.setEditable(false);
				dateFromDep.setEnabled(false);
				
				dateToDep.setText("");
				dateToDep.setEditable(false);
				dateToDep.setEnabled(false);
				
				txtNetCost.setText("0.00");
				txtNetCost.setEditable(false);
				
				txtUsefulLife.setText("");
				txtUsefulLife.setEditable(false);
				
				txtSerialNo.setText("");
				txtSerialNo.setEditable(false);
				
				txtModelNo.setText("");
				txtModelNo.setEditable(false);
				
				grpOwned.clearSelection();
				rbOwnedNo.setEnabled(false);
				rbOwnedYes.setEnabled(false);
				
				grpCapitalized.clearSelection();
				rbCapitalizedNo.setEnabled(false);
				rbCapitalizedYes.setEnabled(false);
				
				grpInsured.clearSelection();
				rbInsuredNo.setEnabled(false);
				rbInsuredYes.setEnabled(false);
				
				grpFound.clearSelection();
				rbFoundYes.setEnabled(false);
				rbFoundNo.setEnabled(false);
				
				txtReferenceNo.setText("");
				txtReferenceNo.setEditable(false);
				
				txtInsuredValue.setText("0.00");
				txtInsuredValue.setEditable(false);
				
//				txtNoOfAssets.setText("");
//				txtNoOfAssets.setEditable(false);
				
				lookupLocation.setText("");
				lookupLocation.setEditable(false);
				
				btnState(true, true, false, true);
				FncGlobal.stopProgress();
			}
		}).start();
	}
	
	public static  void displayAssetDetail(String asset_no){
		
		String SQL="select \n" + 
				"to_char(a.asset_no,'FM00000000') as asset_no,\n" + 
				"a.date_acquired as date_acquired,\n" + 
				"a.co_id,\n" + 
				"c.company_name,\n" + 
				"lpad(a.current_cust::text,6,'0'::text) as custodian_id, \n"+
				"d.entity_name as custodian_name,\n" + 
				"f.category_id::varchar,\n" + 
				"f.category_name, \n" + 
				"to_char(e.item_id,'FM000000') as item_id,\n" + 
				"trim(split_part(asset_name, ',', 1)) as item_name,\n" + 
				"trim(split_part(asset_name, ',', 2)) as brand,\n" + 
				"trim(split_part(asset_name, ',', 3)) as description,\n" + 
				"a.supp_id,\n" + 
				"i.entity_name,\n" + 
				//"a.asset_code,\n" + 
				"a.status,\n" + 
				"a.remarks,\n" + 
				"to_char(a.asset_cost, 'FM999,999,999.00') as net_cost,\n" + 
				"a.asset_ulm::varchar,\n" + 
				
				"a.asset_serial::varchar as serial_no,\n" + 
				"a.asset_model as model_no, \n" + 
				"a.owned as \"owned\",\n" + 
				"a.capitalized as capitalized, \n" + 
				"a.insured as insured, \n" + 
				"a.insured_value as insured_value, \n" + 
				"a.reference_no::varchar as reference_no, \n" + 
				"round(a.asset_mon_dep,2)::numeric as monthly_depreciation, \n" + 
				"round(a.asset_bk_val,2)::numeric as book_value,\n" + 
				"a.asset_ret_cost as retirement_cost, \n" + 
				"a.from_dep as from_depreciation, \n" + 
				"a.to_dep as to_depreciation,\n" + 
				"a.item_found, \n"+
				//"a.remarks2, \n"+
				"(case when a.loc_id != '' then (select loc_name from rf_asset_location  where loc_id=a.loc_id) else 'Not yet scanned' end) as loc_name \n"+
				"from rf_asset a \n" + 
				"left join rf_employee  b on a.current_cust=b.emp_code::int\n" + 
				"left join mf_company  c on a.co_id=c.co_id\n" + 
				"left join rf_entity  d on b.entity_id=d.entity_id\n" + 
				"left join mf_asset_item  e on e.item_id=a.item_id and e.status_id = 'A' \n" + 
				"left join mf_asset_category  f on f.category_id::varchar = e.category_id\n" + 
				//"left join rf_asset_supplier g on g.supp_id=a.supp_id\n" + 
				"left join rf_entity i on a.supp_id::varchar = i.entity_id\n"+
				"where a.asset_no="+asset_no+";";
		FncSystem.out("display Asset Detail", SQL);
		pgSelect db = new pgSelect();
		db.select(SQL);

		if(db.isNotNull()){
			txtAssetNo.setText((String) db.getResult()[0][0]);
			dateAcquired.setDate((Date) db.getResult()[0][1]);
			lookupCompany.setValue((String) db.getResult()[0][2]);
			txtCompany.setText((String) db.getResult()[0][3]);
			lookupCustodian.setValue((String) db.getResult()[0][4]);
			txtCustodian.setText((String)db.getResult()[0][5]);
			lookupCategory.setValue((String) db.getResult()[0][6]);
			txtCategory.setText((String) db.getResult()[0][7]);
			lookupItem.setValue((String) db.getResult()[0][8]);
			txtItem.setText((String) db.getResult()[0][9]);
			txtBrand.setText((String) db.getResult()[0][10]);
			txtDescription.setText((String) db.getResult()[0][11]);
			lookupSupplier.setText((db.getResult()[0][12].toString()));
			//lookupSupplier.setValue( db.getResult()[0][12]);
			txtSupplier.setText((String) db.getResult()[0][13]);
			//txtAssetCode.setText((String) db.getResult()[0][14]);
			cmbstatus.setSelectedItem((String)db.getResult()[0][14]);
			
			txtRemarks.setText((String) db.getResult()[0][15]);
			txtNetCost.setText((String) db.getResult()[0][16]);
			txtUsefulLife.setText((String) db.getResult()[0][17]);
			txtSerialNo.setText((String) db.getResult()[0][18]);
			txtModelNo.setText((String) db.getResult()[0][19]);
			
			if ((boolean) db.getResult()[0][20]){
				rbOwnedNo.setSelected(false);
				rbOwnedYes .setSelected(true);
			}else{
				rbOwnedNo.setSelected(true);
				rbOwnedYes .setSelected(false);
			}

			rbCapitalizedYes.setSelected((boolean) db.getResult()[0][21]);
			rbCapitalizedNo.setSelected(!(boolean) db.getResult()[0][21]);

			if((boolean)db.getResult()[0][22]){
				rbInsuredYes.setSelected(true);
				rbInsuredNo .setSelected(false);
				
			}else{
				rbInsuredYes.setSelected(false);
				rbInsuredNo .setSelected(true);
			}
			txtInsuredValue.setValue(db.getResult()[0][23]);
			txtReferenceNo.setText((String) db.getResult()[0][24]);
			txtMonthlyDep.setValue(db.getResult()[0][25]);			
			txtBookValue.setValue(db.getResult()[0][26]);
			txtRetCost.setValue(db.getResult()[0][27]);
			dateFromDep.setDate((Date) db.getResult()[0][28]);
			dateToDep.setDate((Date) db.getResult()[0][29]);
			
			if ((boolean) db.getResult()[0][30]) {
				rbFoundNo.setSelected(false);
				rbFoundYes.setSelected(true);
			} else {
				rbFoundNo.setSelected(true);
				rbFoundYes.setSelected(false);	
			}
			lookupLocation.setValue((String) db.getResult()[0][31]);
			//txtNoOfAssets
		}
	}
	
	private boolean checksttachment(String assetno) {
		pgSelect db = new pgSelect();
		String sql = "select asset_no from tbl_asset_warranty_attachments where asset_no = '"+assetno+"' and status_id = 'A' ";
		System.out.println("checksttachment"+sql);
		db.select(sql);
		if(db.isNotNull()) {
			return true;
		}else {
			return false;
		}
			
	}
	
	private boolean checkaccessories(String assetno) {
		pgSelect db  = new pgSelect();
		String sql = " select asset_no from tbl_accesories where asset_no = '"+assetno+"' and status_id = 'A'  ";
		db.select(sql);
		if(db.isNotNull()) {
			return true;
		}else {
			return false;
		}
	}
	
	public static  void btnState(Boolean sNew, Boolean sEdit, Boolean sSave, Boolean sReset){
		btnNew.setEnabled(sNew);
		btnEdit.setEnabled(sEdit);
		btnSave.setEnabled(sSave);
		btnReset.setEnabled(sReset);
		
	}

	protected void editState(){
		cmbremarks.setEnabled(true);
		cmbremarks.setEditable(true);
		AssetMonitoring.disbletableAssets(false);
		lookupCustodian.setEditable(false);
		grpNE.setSelected(btnEdit.getModel(), true);
		lookupCategory.setEditable(true);
		lookupCategory.setEnabled(true);
		lookupItem.setEnabled(true);
		lookupItem.setEditable(true);
		txtBrand.setEditable(true);
		txtDescription.setEditable(true);
		lookupSupplier.setEditable(true);
		txtRemarks.setEditable(true);
		txtNetCost.setEditable(true);
		txtUsefulLife.setEditable(true);
		txtSerialNo.setEditable(true);
		txtInsuredValue.setEditable(true);
		txtModelNo.setEditable(true);
		rbOwnedYes.setEnabled(true);
		rbOwnedNo.setEnabled(true);
		rbCapitalizedYes.setEnabled(true);
		rbCapitalizedNo.setEnabled(true);
		rbInsuredYes.setEnabled(true);
		rbInsuredNo.setEnabled(true);
		rbFoundYes.setEnabled(true);
		rbFoundNo.setEnabled(true);
		txtReferenceNo.setEditable(true);	
//		txtNoOfAssets.setEditable(true);
//		txtNoOfAssets.setText("1");
		lookupLocation.setEditable(true);
		
		
		btnState(false, false, true, true);
	}
	
	private void previewSticker(String asset_no, String current_cust, String co_name){
		
		Object[] option= {"Big Sticker","Small Sticker"};
		int Option=JOptionPane.showOptionDialog(getTopLevelAncestor(), "Please select sticker size.", "Sticker size option", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
		sticker_count = JOptionPane.showInputDialog(getTopLevelAncestor(), "How many stickers would you like to print?", '1');
		
	Map<String, Object> mapParameters = new HashMap<String, Object>();
	
	mapParameters.put("assetToPrint", Integer.valueOf(asset_no));
	mapParameters.put("custodian",current_cust);
	mapParameters.put("company_name", co_name);
	mapParameters.put("co_logo", this.getClass().getClassLoader().getResourceAsStream("Images/"+ AssetMonitoring.co_logo));
	mapParameters.put("no_sticker", sticker_count);
	
	FncSystem.out("co_name", co_name);
	FncSystem.out("co_logo", co_logo);
	FncSystem.out("current_cust", current_cust);
	FncSystem.out("asset_no", asset_no);
	FncSystem.out("sticker_count", sticker_count);
	
	if(Option == JOptionPane.YES_OPTION){

		
		FncReport.generateReport("/Reports/rptAssetSticker.jasper", "Assetcode Sticker", mapParameters);
		System.out.println("Big");
	}
	if(Option == JOptionPane.NO_OPTION){
		System.out.println("Small");
		FncReport.generateReport("/Reports/rptAssetSticker_small2.jasper", "Assetcode Sticker", mapParameters);
	}
	
	
	
}

	public boolean toAdd(){
		int ulm = 0, asset_num = 0;
		double netcost = 0.00, insured_value = 0.00;
		System.out.println("toAdd");

		try {
			netcost = Double.parseDouble(txtNetCost.getText().replace(",", ""));
		} catch (java.lang.NumberFormatException e){
		}

		try {
			insured_value = Double.parseDouble(txtInsuredValue.getText().replace(",", ""));
		} catch (java.lang.NumberFormatException e) {
		}

		try {
			ulm = Integer.parseInt(txtUsefulLife.getText().replace(",", ""));
		} catch (java.lang.NumberFormatException e) {
		}

//		try {
//			asset_num = Integer.parseInt(txtNoOfAssets.getText().replace(",", ""));
//		} catch (java.lang.NumberFormatException e) {
//		}

		if(lookupCompany.getText().equals("")
				|| lookupCompany.getText() == null
				|| lookupCustodian.getText().equals("")
				|| lookupCategory.getText().equals("")
				|| lookupItem.getText().equals("")
				|| txtBrand.getText().trim().equals("")
				|| txtDescription.getText().trim().equals("")
				|| lookupSupplier.getText().equals("")
				|| txtRemarks.getText().trim().equals("")
				|| netcost == 0.0
				|| ulm == 0
				|| txtSerialNo.getText().trim().equals("")
				|| txtModelNo.getText().trim().equals("")
				|| !rbOwnedYes.isSelected()
				&& !rbOwnedNo.isSelected()
				|| !rbCapitalizedYes.isSelected()
				&& !rbCapitalizedNo.isSelected()
				|| !rbInsuredYes.isSelected()
				&& !rbInsuredNo.isSelected()
				|| txtReferenceNo.getText().trim().equals("")
				//|| asset_num == 0
				|| lookupLocation.getValue()==null
				 ){
			System.out.println("lookupCompany: "+lookupCompany.getText());
			System.out.println("lookupCustodian: "+lookupCustodian.getText());
			System.out.println("lookupCategory: "+lookupCategory.getText());
			System.out.println("lookupItem: "+lookupItem.getText());
			System.out.println("txtBrand: "+txtBrand.getText().trim());
			System.out.println("txtDescription: "+txtDescription.getText().trim());
			System.out.println("lookupSupplier: "+lookupSupplier.getText());
			System.out.println("txtRemarks: "+txtRemarks.getText().trim());
			System.out.println("txtSerialNo: "+txtSerialNo.getText().trim());
			System.out.println("txtModelNo: "+ txtModelNo.getText().trim());
			System.out.println("txtReferenceNo: "+ txtReferenceNo.getText().trim());
			System.out.println("lookupLocation: "+ lookupLocation.getValue());
			System.out.println("netcost: "+ netcost);
			System.out.println("ulm: "+ ulm);
			System.out.println("toadd_false");
			return false;
			
		}else{
			System.out.println("toadd_true");
			if(rbInsuredYes.isSelected()){
				if(insured_value == 0.0){
					return false;
				}else{
					return true;
				}
			}else{
				return true;
			}
		}
	}
	public  void previewAssetCard(String emp_code,  String co_logo, String co_name, String co_alias,Boolean item_found ){
		Map<String, Object> mapParameters = new HashMap<String, Object>();
		
		mapParameters.put("item_found", item_found);
		mapParameters.put("emp_code", Integer.valueOf(emp_code));
		mapParameters.put("company_name", co_name);
		mapParameters.put("co_alias", co_alias);
		mapParameters.put("co_logo", this.getClass().getClassLoader().getResourceAsStream("Images/"+ co_logo));
		
		System.out.println(item_found);
		System.out.println(co_logo);
		System.out.println(emp_code);
		System.out.println(co_name);
		System.out.println(co_alias);
		
		
		FncReport.generateReport("/Reports/AssetCard.jasper", "Asset Card", mapParameters);
		
	}
	
	public static String assetforreplacement(){
		
		return "select asset_no,asset_code,asset_name from rf_asset  where status='A' ";
		
	}
	
	protected Boolean hasCheckedAssets(){
		ArrayList<Boolean> checkTable = new ArrayList<Boolean>();
		for(int x=0; x<AssetMonitoring.tblAssets.getRowCount(); x++){
			if(AssetMonitoring.tblAssets.getValueAt(x, 0).equals(true))
				checkTable.add(true);
		}
		return checkTable.contains(true);
		
	}

	public static String getassetlocation(){
		
		return"select loc_id,loc_name from rf_asset_location ";
	}
	public static String getcompany() {
		String sql = "select co_id as \"Company ID\", \n"
				+ "company_name as \"Company Name\",\n"
				+ "company_address as \"Company Address\",\n"
				+ "company_logo as \"Company Logo\"\n"
				+ "from mf_company";
		return sql;
	}

}
