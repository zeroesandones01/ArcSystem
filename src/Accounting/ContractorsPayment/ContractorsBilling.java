package Accounting.ContractorsPayment;

import interfaces._GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXFormattedTextField;
import org.jdesktop.swingx.JXTextField;
//import org.omg.CORBA.REBIND;

import tablemodel.modelContractorBackcharges;
import tablemodel.modelContractorOtherDeductions;
import tablemodel.modelContractorProgressBilling;
import tablemodel.model_BC_table;
import Accounting.Disbursements.CheckVoucher;
import Accounting.Disbursements.PayableVoucher;
import Accounting.Disbursements.RequestForPayment;
import Database.pgSelect;
import Database.pgUpdate;
import DateChooser._JDateChooser;
import FormattedTextField._JXFormattedTextField;
import Functions.FncAcounting;
import Functions.FncGlobal;
import Functions.FncReport;
import Functions.FncSystem;
import Functions.FncTables;
import Functions.UserInfo;
import Home.Home_ArcSystem;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import Lookup._JLookupTable;
import Renderer.NumberRenderer;
//import Utilities.Comm_Sched_Transfer;

import com.toedter.calendar.JTextFieldDateEditor;
import components.JTBorderFactory;
import components._JInternalFrame;
import components._JScrollPaneMain;
import components._JScrollPaneTotal;
import components._JTabbedPane;
import components._JTableMain;
import components._JTableTotal;
import components._JTagLabel;

public class ContractorsBilling extends _JInternalFrame implements _GUI, ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3061284418918863916L;
	static String title = "Contractors Progress Billing";
	static Dimension SIZE = new Dimension(1000, 600);

	private JPanel pnlMain;
	private JPanel pnlNorth;
	private JPanel pnlBillingTable;
	private JPanel pnlSouth;
	private JPanel pnlContractDtl;
	private JPanel pnlContractDtl_1;
	private JPanel pnlContractDtl_2;
	private JPanel pnlContractDtl_1a;
	private JPanel pnlContractDtl_1b;
	private JPanel pnlTable;
	private JPanel pnlTableBC_Ded;
	private JPanel pnlContractDtl_3;
	private JPanel pnlContractDtl_3a;
	private JPanel pnlB0_3;
	private JPanel pnlSurety;
	private JPanel pnlComp;
	private JPanel pnlComp_a;
	private JPanel pnlComp_a1;
	private JPanel pnlComp_a2;
	private JPanel pnlContract;
	private JPanel pnlContract_a;
	private JPanel pnlContract_a1;
	private JPanel pnlContract_a2;
	private JPanel pnlContract_a2_1;
	private JPanel pnlContract_a2_3;
	private JPanel pnlContract_a2_3b;
	private JPanel pnlTableBackcharges;
	private JPanel pnlTableDeduction;
	private JPanel pnlProgBill;
	private JPanel pnlTblBC;
	private JPanel pnlTblOD;
	private JPanel pnlContractDtl_3b;
	private JPanel pnlEditAmount;
	private JPanel pnlAddDeductions;
	private static JPanel pnlEditBType;
	private JPanel pnlEditDate;
	private JPanel pnlBackChargeTbl;
	private JPanel pnlCreateRPLF;

	private JLabel lblCompany;
	private static JLabel lblProject;
	private static JLabel lblContractor;
	private static JLabel lblEntityType;
	private JLabel lblEditAmount;
	private JLabel lblEditBType;
	private JLabel lblAvailerType;
	private JLabel lblEditDAmount;

	private _JScrollPaneMain scrollProgBillMain;
	private _JScrollPaneMain scrollBC;
	private _JScrollPaneMain scrollOD;
	private _JScrollPaneMain scrollViewBC_tbl;

	private static _JScrollPaneTotal scrollProgBillTotal;
	private static _JScrollPaneTotal scrollBC_total;
	private static _JScrollPaneTotal scrollOD_total;

	private static modelContractorProgressBilling modelProgBillMain;
	private static modelContractorProgressBilling modelProgBillTotal;
	private static modelContractorBackcharges modelBC;
	private static modelContractorBackcharges modelBC_total;
	private static modelContractorOtherDeductions modelOD;
	private static modelContractorOtherDeductions modelOD_total;
	private model_BC_table modelView_BCrplf;

	private static _JTableTotal tblProgBillTotal;
	private static _JTableTotal tblBC_total;
	private static _JTableTotal tblOD_total;

	private static _JTableMain tblProgBillMain;
	private static _JTableMain tblBC;
	private static _JTableMain tblOD;
	private _JTableMain tblBC_rplf;

	private JSplitPane splitSchedule;
	private JSplitPane splitPanel;

	static _JLookup lookupCompany;
	static _JLookup lookupContractNo;
	static _JLookup lookupAvailerType;
	static _JLookup lookupAvailer;

	static _JTagLabel tagCompany;
	static _JTagLabel tagContractNo;

	private static JList rowHeaderProgBillMain;
	private static JList rowHeaderBC;
	private static JList rowHeaderOD;
	private JList rowHeaderBC_tbl;

	private static JButton btnSave;
	private static JButton btnCancel;
	private static JButton btnAddNew;
	private static JButton btnRefresh;
	private static JButton btnOK;
	private static JButton btnPreview;
	private static JButton btnAddDeductives;
	private static JButton btnOK_DAmount;

	Border lineBorder = BorderFactory.createLineBorder(Color.GRAY);

	private static JXTextField txtEntity;
	private static JXTextField txtNTP_no;
	private static JXTextField txtSuretyNo;
	private static JXTextField txtContractPriceNew;
	private static JXTextField txtContractPriceOrig;
	private static JXTextField txtDP;
	private static JXTextField txtProject;
	private static JXTextField txtContractor;

	static JLabel lblContractNo;
	private static JLabel lblContractor2;
	private static JLabel lblEntity2;
	private static JLabel lblNTP_co;
	private static JLabel lblContractPriceOrig;
	private static JLabel lblSuretyNo;
	private static JLabel lblContractPriceNew;
	private static JLabel lblProject2;
	private JButton btnViewNote;
	private static JLabel lblContractDP;
	private JLabel lblAvailer;
	private JLabel lblRequestType;

	private _JDateChooser dateSched;
	private static JComboBox cmbBillingType;

	private JPopupMenu menu2;
	private JPopupMenu menu;
	private JMenuItem mniopenRPLF;
	private JMenuItem mniopenPV;
	private JMenuItem mniopenCV;
	private JMenuItem mnieditperc;
	private JMenuItem mnieditBType;
	private JMenuItem mnieditamt;
	private JMenuItem mniviewBC_rplf;
	private JMenuItem mnieditDate;
	private JMenuItem mniOD;

	private _JXFormattedTextField txteditamount;
	private static _JXFormattedTextField txtDAmount;

	static NumberFormat nf = new DecimalFormat("###,###,###,##0.00");
	protected static DecimalFormat df = new DecimalFormat("#,##0.00");
	protected static DecimalFormat df2 = new DecimalFormat("#,##0.00000");
	protected static Font dialog11Bold = new Font("DIALOG", Font.BOLD, 11);
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	public static String company = "";
	public static String company_logo;

	String next_billing_type = "";
	String tko_billing_type = "";
	String edited_btype = "";

	String rplf_no = "";
	String jv_no = "";
	String bill_no_expanded = "";
	static String contr_name = "";
	static String co_id = "02";
	String part_desc = "";
	String billing_date = "";
	static String ntp_no = "";
	static String acct_id = "";

	Integer selected_billing_type;

	static Double new_price = null;
	static Double dp_amount = null;
	static Double wtax_rate = 0.00;
	static Double vat_rate = 0.00;
	static Double billed_ret_amt = 0.00;
	static Double accomp_perc, gr_amt, vat_amt, wtx_amt, dpr_amt, ret_amt, bc_amt, od_amt, net_amt, exp_amt,
			deduction_amt;
	static Double less_deductives;

	static int row_num = 0;
	static int row_num_bc = 0;
	static int row_num_od = 0;
	Integer bill_no = 0;
	Integer next_seqno = 0;
	String billing_remarks = "";
	public Double for_liq_bc_jv = 0.00;
	public String remarks = "";
	public static BigDecimal ret_bal;

	Date billing_dte = FncGlobal.dateFormat(FncGlobal.getDateSQL());
	private JMenuItem mniviewDeleteBilling;
	private _JTabbedPane tabCenter;
	private JPanel pnlremarks;
	private JTextField txtremarks;
	private JButton btnOK_remarks;
	private JMenuItem mniaddremarks;
	private JLabel lblEditremarks;
	private JPanel pnlremarks_center;
	private JMenuItem mnipostajustment;

	public ContractorsBilling() {
		super(title, true, true, true, true);
		initGUI();
	}

	public ContractorsBilling(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public ContractorsBilling(String title, boolean resizable, boolean closable, boolean maximizable,
			boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces._GUI#initGUI()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces._GUI#initGUI()
	 */
	@Override
	public void initGUI() {
		this.setLayout(new BorderLayout(5, 5));
		this.setSize(SIZE);
		this.setPreferredSize(new java.awt.Dimension(935, 625));
		this.setBounds(0, 0, 935, 625);

		{
			menu2 = new JPopupMenu("Popup");
			mniopenRPLF = new JMenuItem("Open Payment Request");
			mniopenPV = new JMenuItem("Open Payable Voucher");
			mniopenCV = new JMenuItem("Open Disbursement Voucher");
			mnieditperc = new JMenuItem("Enter Percentage");
			mnieditBType = new JMenuItem("Edit Billing Type");
			mnieditamt = new JMenuItem("Override Amount");
			mniviewBC_rplf = new JMenuItem("View Backcharge RPLF");
			mnieditDate = new JMenuItem("Edit Date");
			mniviewDeleteBilling = new JMenuItem("Delete Billing");
			mniaddremarks = new JMenuItem("Add Billing Remarks");
			mnipostajustment = new JMenuItem("Post Adjustment");
			menu2.add(mniopenRPLF);
			menu2.add(mniopenPV);
			menu2.add(mniopenCV);
			menu2.add(mnieditDate);
			menu2.add(mniviewDeleteBilling);
			menu2.add(mniaddremarks);
			menu2.add(mnipostajustment);
			mniopenRPLF.setEnabled(false);
			mniopenPV.setEnabled(false);
			mniopenCV.setEnabled(false);
			mniviewBC_rplf.setEnabled(false);
			mnieditDate.setEnabled(false);
			mniviewDeleteBilling.setEnabled(false);
			mnieditBType.setEnabled(false);
			mniaddremarks.setEnabled(false);
			mnipostajustment.setEnabled(false);
			JSeparator sp = new JSeparator();
			menu2.add(sp);
			menu2.add(mnieditperc);
			menu2.add(mnieditamt);
			JSeparator sp2 = new JSeparator();
			menu2.add(sp2);
			menu2.add(mniviewBC_rplf);
			menu2.add(mnieditDate);
			menu2.add(mniviewDeleteBilling);
			menu2.add(mniaddremarks);
			menu2.add(mnipostajustment);

			mniopenRPLF.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					openDRF();
				}
			});
			mniopenPV.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					openPV();
				}
			});
			mniopenCV.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					openCV();
				}
			});
			mnieditperc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					// modelProgBillMain.setEditable(true);
					editAmount();
				}
			});

			mnieditamt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					// modelProgBillMain.setEditable(true);
					overrideAmount();
				}
			});
			mniviewBC_rplf.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					// modelProgBillMain.setEditable(true);
					viewBackchargeRPLF();
				}
			});
			mnieditDate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					editDate();
				}
			});
			mniviewDeleteBilling.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					deleteBilling();
				}
			});
			mniaddremarks.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					addbilling_remarks();
				}
			});

			mnipostajustment.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					post_adjustment();

				}
			});

		}

		pnlMain = new JPanel();
		getContentPane().add(pnlMain, BorderLayout.CENTER);
		pnlMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pnlMain.setLayout(new BorderLayout(5, 5));
		pnlMain.setPreferredSize(new java.awt.Dimension(1134, 645));
		{

			pnlCreateRPLF = new JPanel();
			pnlCreateRPLF.setLayout(null);
			pnlCreateRPLF.setPreferredSize(new java.awt.Dimension(500, 125));
			JLabel lblNorth;
			{
				lblNorth = new JLabel("Request Type");
				pnlCreateRPLF.add(lblNorth);
				lblNorth.setHorizontalAlignment(JLabel.LEFT);
				lblNorth.setBounds(10, 10, 120, 22);
			}
			{
				_JLookup lookupRequestType = new _JLookup(null, "Request Type");
				pnlCreateRPLF.add(lookupRequestType);
				lookupRequestType.setReturnColumn(0);
				// lookupRequestType.setLookupSQL(SQL_REQUESTTYPE());
				lookupRequestType.setBounds(110, 10, 90, 22);
				lookupRequestType.addLookupListener(new LookupListener() {
					public void lookupPerformed(LookupEvent event) {
						Object[] data = ((_JLookup) event.getSource()).getDataSet();
						if (data != null) {

							lblRequestType.setText(String.format("[ %s ]", (String) data[1]));

							KEYBOARD_MANAGER.focusNextComponent();
						}
					}
				});
			}
			{
				lblRequestType = new JLabel("[ ]");
				pnlCreateRPLF.add(lblRequestType);
				lblRequestType.setHorizontalAlignment(JLabel.LEFT);
				lblRequestType.setBounds(205, 10, 200, 22);
			}

			{
				lblNorth = new JLabel("Availer");
				pnlCreateRPLF.add(lblNorth);
				lblNorth.setHorizontalAlignment(JLabel.LEFT);
				lblNorth.setBounds(10, 35, 120, 22);
			}
			{
				lookupAvailer = new _JLookup(null, "Availer");
				pnlCreateRPLF.add(lookupAvailer);
				lookupAvailer.setReturnColumn(0);
				// lookupAvailer.setLookupSQL(SQL_AVAILER());
				lookupAvailer.setBounds(110, 35, 90, 22);
				lookupAvailer.addLookupListener(new LookupListener() {
					public void lookupPerformed(LookupEvent event) {
						Object[] data = ((_JLookup) event.getSource()).getDataSet();
						FncSystem.out("Display SQL for Availer", lookupAvailer.getLookupSQL());
						if (data != null) {

							lblAvailer.setText(String.format("[ %s ]", (String) data[1]));
							// lookupAvailerType.setLookupSQL(SQL_AVAILERTYPE((String) data[0]));

							KEYBOARD_MANAGER.focusNextComponent();
						}
					}
				});
			}
			{
				lblAvailer = new JLabel("[ ]");
				pnlCreateRPLF.add(lblAvailer);
				lblAvailer.setHorizontalAlignment(JLabel.LEFT);
				lblAvailer.setBounds(205, 35, 255, 22);
			}

			{
				lblNorth = new JLabel("Availer Type");
				pnlCreateRPLF.add(lblNorth);
				lblNorth.setHorizontalAlignment(JLabel.LEFT);
				lblNorth.setBounds(10, 60, 120, 22);
			}
			{
				lookupAvailerType = new _JLookup(null, "Availer Type");
				pnlCreateRPLF.add(lookupAvailerType);
				lookupAvailerType.setReturnColumn(0);
				lookupAvailerType.setBounds(110, 60, 90, 22);
				lookupAvailerType.addLookupListener(new LookupListener() {
					public void lookupPerformed(LookupEvent event) {
						Object[] data = ((_JLookup) event.getSource()).getDataSet();
						if (data != null) {

							lblAvailerType.setText(String.format("[ %s ]", (String) data[1]));

							KEYBOARD_MANAGER.focusNextComponent();
						}
					}
				});
			}
			{
				lblAvailerType = new JLabel("[ ]");
				pnlCreateRPLF.add(lblAvailerType);
				lblAvailerType.setHorizontalAlignment(JLabel.LEFT);
				lblAvailerType.setBounds(205, 60, 200, 22);
			}
			{
				btnSave = new JButton("Create RPLF");
				pnlCreateRPLF.add(btnSave);
				btnSave.setActionCommand("Create RPLF");
				btnSave.setBounds(130, 90, 100, 30);
				btnSave.addActionListener(this);
			}
			{
				JButton btnCancelRPLF = new JButton("Cancel");
				pnlCreateRPLF.add(btnCancelRPLF);
				btnCancelRPLF.setActionCommand("Cancel RPLF");
				btnCancelRPLF.setBounds(235, 90, 100, 30);
				btnCancelRPLF.addActionListener(this);
			}

		}
		{
			pnlEditAmount = new JPanel();
			pnlEditAmount.setLayout(null);
			pnlEditAmount.setPreferredSize(new java.awt.Dimension(265, 80));
			{
				lblEditAmount = new JLabel();
				pnlEditAmount.add(lblEditAmount);
				lblEditAmount.setBounds(5, 15, 160, 20);
				lblEditAmount.setText("Amount / Percent :");
			}
			{
				txteditamount = new _JXFormattedTextField(JXFormattedTextField.CENTER);
				pnlEditAmount.add(txteditamount);
				txteditamount.setFormatterFactory(_JXFormattedTextField.DECIMAL2);
				txteditamount.setText("0.0000");
				txteditamount.setEnabled(true);
				txteditamount.setEditable(true);
				txteditamount.setBounds(130, 15, 125, 21);
				// txteditamount.setDefaultRenderer(BigDecimal.class,
				// NumberRenderer.getMoneyRenderer2());
			}
			{
				btnOK = new JButton();
				pnlEditAmount.add(btnOK);
				btnOK.setBounds(95, 58, 69, 22);
				btnOK.setText("OK");
				btnOK.setFocusable(false);
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Window optionPaneWindow = SwingUtilities.getWindowAncestor(pnlEditAmount);
						optionPaneWindow.dispose();
						// computeBillAmounts2();

					}
				});
			}
		}
		{
			pnlremarks = new JPanel(new BorderLayout(5, 5));
			// pnlremarks.setLayout(null);
			pnlremarks.setPreferredSize(new java.awt.Dimension(265, 80));
			{
				pnlremarks_center = new JPanel(new BorderLayout(3, 3));
				pnlremarks.add(pnlremarks_center, BorderLayout.CENTER);
				{
					lblEditremarks = new JLabel();
					pnlremarks.add(lblEditremarks, BorderLayout.WEST);
					// lblEditremarks.setBounds(5, 15, 160, 20);
					lblEditremarks.setText("Remarks:");
				}
				{
					txtremarks = new JTextField();
					pnlremarks.add(txtremarks, BorderLayout.CENTER);
					txtremarks.setEnabled(true);
					txtremarks.setEditable(true);
					// txtremarks.setBounds(130, 15, 125, 21);
				}
			}

			{
				btnOK_remarks = new JButton();
				pnlremarks.add(btnOK_remarks, BorderLayout.SOUTH);
				btnOK_remarks.setBounds(95, 58, 69, 22);
				btnOK_remarks.setText("OK");
				btnOK_remarks.setFocusable(false);
				// btnOK_remarks.setPreferredSize(new Dimension(0, 25));
				btnOK_remarks.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Window optionPaneWindow = SwingUtilities.getWindowAncestor(pnlremarks);
						optionPaneWindow.dispose();

					}
				});
			}
		}
		{
			pnlEditBType = new JPanel();
			pnlEditBType.setLayout(null);
			pnlEditBType.setPreferredSize(new java.awt.Dimension(265, 85));
			{
				lblEditBType = new JLabel();
				pnlEditBType.add(lblEditBType);
				lblEditBType.setBounds(5, 3, 160, 20);
				lblEditBType.setText("Billing Type:");
			}
			{
				cmbBillingType = new JComboBox(new DefaultComboBoxModel(
						new String[] { "02  -  PROGRESS BILLING", "03  -  RETENTION PAYMENT", "04 - ADJUSTMENT" }));
				pnlEditBType.add(cmbBillingType, BorderLayout.CENTER);
				cmbBillingType.setBounds(30, 30, 210, 20);
				cmbBillingType.setSelectedIndex(0);
			}
			{
				btnOK = new JButton();
				pnlEditBType.add(btnOK);
				btnOK.setBounds(95, 60, 69, 20);
				btnOK.setText("OK");
				btnOK.setFocusable(false);
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Window optionPaneWindow = SwingUtilities.getWindowAncestor(pnlEditBType);
						optionPaneWindow.dispose();

					}
				});
			}
		}

		{
			pnlEditDate = new JPanel();
			pnlEditDate.setLayout(null);
			pnlEditDate.setPreferredSize(new java.awt.Dimension(265, 80));
			{
				JLabel lblEditDate = new JLabel();
				pnlEditDate.add(lblEditDate);
				lblEditDate.setBounds(30, 15, 160, 20);
				lblEditDate.setText("Date :");
			}
			{
				dateSched = new _JDateChooser("MM/dd/yyyy", "##/##/#####", '_');
				pnlEditDate.add(dateSched);
				dateSched.setBounds(100, 15, 125, 21);
				dateSched.setDate(null);
				((JTextFieldDateEditor) dateSched.getDateEditor()).setEditable(false);
				dateSched.setDate(Calendar.getInstance().getTime());
			}
			{
				btnOK = new JButton();
				pnlEditDate.add(btnOK);
				btnOK.setBounds(95, 58, 69, 22);
				btnOK.setText("OK");
				btnOK.setFocusable(false);
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Window optionPaneWindow = SwingUtilities.getWindowAncestor(pnlEditDate);
						optionPaneWindow.dispose();
						// computeBillAmounts2();

					}
				});
			}
		}
		{
			pnlAddDeductions = new JPanel();
			pnlAddDeductions.setLayout(null);
			pnlAddDeductions.setPreferredSize(new java.awt.Dimension(265, 80));
			{
				lblEditDAmount = new JLabel();
				pnlAddDeductions.add(lblEditDAmount);
				lblEditDAmount.setBounds(35, 15, 160, 20);
				lblEditDAmount.setText("Amount :");
			}
			{
				txtDAmount = new _JXFormattedTextField(JXFormattedTextField.CENTER);
				pnlAddDeductions.add(txtDAmount);
				txtDAmount.setFormatterFactory(_JXFormattedTextField.DECIMAL2);
				txtDAmount.setText("0.0000");
				txtDAmount.setEnabled(true);
				txtDAmount.setEditable(true);
				txtDAmount.setBounds(100, 15, 125, 21);
			}
			{
				btnOK_DAmount = new JButton();
				pnlAddDeductions.add(btnOK_DAmount);
				btnOK_DAmount.setBounds(95, 58, 69, 22);
				btnOK_DAmount.setText("Save");
				btnOK_DAmount.setFocusable(false);
				btnOK_DAmount.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Window optionPaneWindow = SwingUtilities.getWindowAncestor(pnlAddDeductions);
						optionPaneWindow.dispose();

					}
				});
			}
		}

		{
			pnlNorth = new JPanel();
			pnlMain.add(pnlNorth, BorderLayout.NORTH);
			pnlNorth.setLayout(new BorderLayout(5, 5));
			pnlNorth.setBorder(lineBorder);
			pnlNorth.setPreferredSize(new java.awt.Dimension(923, 205));

			pnlComp = new JPanel(new BorderLayout(5, 5));
			pnlNorth.add(pnlComp, BorderLayout.NORTH);
			pnlComp.setPreferredSize(new java.awt.Dimension(921, 32));

			pnlComp_a = new JPanel(new BorderLayout(5, 5));
			pnlComp.add(pnlComp_a, BorderLayout.WEST);
			pnlComp_a.setPreferredSize(new java.awt.Dimension(610, 30));
			pnlComp_a.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

			pnlComp_a1 = new JPanel(new GridLayout(1, 2, 5, 5));
			pnlComp_a.add(pnlComp_a1, BorderLayout.WEST);
			pnlComp_a1.setPreferredSize(new java.awt.Dimension(204, 32));
			pnlComp_a1.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

			{
				lblCompany = new JLabel("   COMPANY");
				pnlComp_a1.add(lblCompany);
				lblCompany.setBounds(8, 11, 62, 12);
				lblCompany.setPreferredSize(new java.awt.Dimension(88, 31));
				lblCompany.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12));
			}
			{
				lookupCompany = new _JLookup(null, "Company", 0, 2);
				pnlComp_a1.add(lookupCompany);
				lookupCompany.setLookupSQL(SQL_COMPANY());
				lookupCompany.setReturnColumn(0);
				lookupCompany.addLookupListener(new LookupListener() {
					public void lookupPerformed(LookupEvent event) {
						Object[] data = ((_JLookup) event.getSource()).getDataSet();
						if (data != null) {

							co_id = (String) data[0];
							company = (String) data[1];
							tagCompany.setTag(company);
							company_logo = (String) data[3];

							KEYBOARD_MANAGER.focusNextComponent();

							lblContractNo.setEnabled(true);
							lookupContractNo.setEnabled(true);
							tagContractNo.setEnabled(true);
							lookupContractNo.setLookupSQL(getLookupContractNo(co_id));
							FncSystem.out("Lookup for Contract no!", getLookupContractNo(co_id));

						}
					}
				});
			}

			pnlComp_a2 = new JPanel(new GridLayout(1, 1, 5, 5));
			pnlComp_a.add(pnlComp_a2, BorderLayout.CENTER);
			pnlComp_a2.setPreferredSize(new java.awt.Dimension(423, 20));
			pnlComp_a2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

			{
				tagCompany = new _JTagLabel("[ ]");
				pnlComp_a2.add(tagCompany);
				tagCompany.setBounds(209, 27, 700, 22);
				tagCompany.setEnabled(true);
				tagCompany.setPreferredSize(new java.awt.Dimension(27, 33));
			}

			pnlContract = new JPanel(new BorderLayout(5, 5));
			pnlNorth.add(pnlContract, BorderLayout.CENTER);
			pnlContract.setPreferredSize(new java.awt.Dimension(921, 169));
			pnlContract.setBorder(JTBorderFactory.createTitleBorder("Contract Details"));
			pnlContract.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

			pnlContract_a = new JPanel(new BorderLayout(5, 5));
			pnlContract.add(pnlContract_a, BorderLayout.NORTH);
			pnlContract_a.setPreferredSize(new java.awt.Dimension(900, 42));
			pnlContract_a.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
			pnlContract_a.setBorder(lineBorder);

			pnlContract_a1 = new JPanel(new GridLayout(1, 1, 5, 10));
			pnlContract_a.add(pnlContract_a1, BorderLayout.WEST);
			pnlContract_a1.setPreferredSize(new java.awt.Dimension(90, 40));
			pnlContract_a1.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));

			{
				lblContractNo = new JLabel("Contract No.", JLabel.TRAILING);
				pnlContract_a1.add(lblContractNo);
				lblContractNo.setEnabled(false);
				lblContractNo.setPreferredSize(new java.awt.Dimension(92, 25));
				lblContractNo.setFont(new java.awt.Font("Segoe UI", Font.ITALIC, 12));
			}

			pnlContract_a2 = new JPanel(new BorderLayout(5, 5));
			pnlContract_a.add(pnlContract_a2, BorderLayout.CENTER);
			pnlContract_a2.setPreferredSize(new java.awt.Dimension(639, 41));
			pnlContract_a2.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));
			// pnlContract_a2.setBorder(BorderFactory.createLineBorder(Color.green));

			pnlContract_a2_1 = new JPanel(new GridLayout(1, 2, 5, 10));
			pnlContract_a2.add(pnlContract_a2_1, BorderLayout.WEST);
			pnlContract_a2_1.setPreferredSize(new java.awt.Dimension(630, 41));
			// pnlContract_a2_1.setPreferredSize(new java.awt.Dimension(197, 22));
			// pnlContract_a2_1.setBorder(BorderFactory.createLineBorder(Color.red));

			{
				lookupContractNo = new _JLookup(null, "Contract", 2, 2);
				pnlContract_a2_1.add(lookupContractNo);
				lookupContractNo.setBounds(20, 27, 20, 25);
				lookupContractNo.setReturnColumn(0);
				lookupContractNo.setEnabled(false);
				lookupContractNo.setPreferredSize(new java.awt.Dimension(157, 22));
				lookupContractNo.addLookupListener(new LookupListener() {
					public void lookupPerformed(LookupEvent event) {
						Object[] data = ((_JLookup) event.getSource()).getDataSet();
						if (data != null) {

							String cont_no = (String) data[0];
							lookupContractNo.setValue(cont_no);
							ntp_no = (String) data[1];
							popupNotesFromNTP(ntp_no); // added by jari cruz asod july 29, 2022 pop up of notes
							// added by Erick Bituen DP was cancelled. DCRF 1307
							// System.out.println("Deduction_amt=
							// "+Double.valueOf(txtDAmount.getText().trim().replace(",","")).doubleValue());
							if (ntp_no.equals("001290")) {
								JOptionPane.showMessageDialog(getContentPane(),
										"Downpayment of TV-2019-PH4-0062H was cancelled. Reversal entry JV# 19120207.",
										"", JOptionPane.INFORMATION_MESSAGE);
							} else if (ntp_no.equals("000340")) {
								JOptionPane.showMessageDialog(getContentPane(),
										"This contract was taken-over by Miguel Arvin(TV-2018-PH1-193H)", "", // ADDED
																												// BY
																												// ERICK
																												// BITUEN
																												// SEE
																												// DCRF
																												// 1355
										JOptionPane.INFORMATION_MESSAGE);
							}

							setContractDetails((String) data[5], ntp_no);
							enableButtons(true, false, true, true, true, true);
							tblProgBillMain.packAll();
						}
					}
				});
			}
			{
				tagContractNo = new _JTagLabel("[ ]");
				pnlContract_a2_1.add(tagContractNo);
				// tagContractNo.setBounds(209, 27, 500, 22);
				tagContractNo.setEnabled(false);
				// tagContractNo.setPreferredSize(new java.awt.Dimension(500, 22));
			}

			// pnlContract_a2_2 = new JPanel(new GridLayout(1, 1, 5, 10));
			// pnlContract_a2.add(pnlContract_a2_2, BorderLayout.CENTER);
			// pnlContract_a2_2.setPreferredSize(new java.awt.Dimension(900, 42));
			// pnlContract_a2_2.setBorder(BorderFactory.createLineBorder(Color.cyan));

			// {
			// tagContractNo = new _JTagLabel("[ ]");
			// pnlContract_a2_2.add(tagContractNo);
			// tagContractNo.setBounds(209, 27, 500, 22);
			// tagContractNo.setEnabled(false);
			// tagContractNo.setPreferredSize(new java.awt.Dimension(500, 22));
			// }

			pnlContract_a2_3 = new JPanel(new BorderLayout(5, 5));
			pnlContract_a.add(pnlContract_a2_3, BorderLayout.EAST);
			pnlContract_a2_3.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));
			// pnlContract_a2_3.setBorder(BorderFactory.createLineBorder(Color.blue));

			pnlContract_a2_3b = new JPanel(new GridLayout(1, 1, 15, 10));
			pnlContract_a2_3.add(pnlContract_a2_3b, BorderLayout.CENTER);
			pnlContract_a2_3b.setPreferredSize(new Dimension(170, 25));
			{
				btnAddDeductives = new JButton("Deductives");
				pnlContract_a2_3b.add(btnAddDeductives);
				btnAddDeductives.setActionCommand("Add Deductives");
				btnAddDeductives.addActionListener(this);
				btnAddDeductives.setEnabled(false);
				// btnAddDeductives.setPreferredSize(new java.awt.Dimension(650, 22));
			}

			pnlB0_3 = new JPanel(new GridLayout(1, 4, 5, 10));
			pnlContract_a2.add(pnlB0_3, BorderLayout.EAST);
			pnlB0_3.setPreferredSize(new java.awt.Dimension(344, 41));
			pnlB0_3.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));

			{
				pnlContractDtl = new JPanel(new BorderLayout(5, 5));
				pnlContract.add(pnlContractDtl, BorderLayout.WEST);
				pnlContractDtl.setPreferredSize(new java.awt.Dimension(902, 123));

				pnlContractDtl_1 = new JPanel(new BorderLayout(5, 5));
				pnlContractDtl.add(pnlContractDtl_1, BorderLayout.WEST);
				pnlContractDtl_1.setPreferredSize(new java.awt.Dimension(207, 135));
				pnlContractDtl_1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));

				pnlContractDtl_1a = new JPanel(new GridLayout(4, 1, 5, 5));
				pnlContractDtl_1.add(pnlContractDtl_1a, BorderLayout.WEST);
				pnlContractDtl_1a.setPreferredSize(new java.awt.Dimension(87, 104));
				pnlContractDtl_1a.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
				// pnlContractDtl_1a.setBorder(BorderFactory.createLineBorder(Color.pink));

				{
					lblProject = new JLabel("Project", JLabel.TRAILING);
					pnlContractDtl_1a.add(lblProject);
					lblProject.setEnabled(false);
				}
				{
					lblContractor = new JLabel("Contractor", JLabel.TRAILING);
					pnlContractDtl_1a.add(lblContractor);
					lblContractor.setEnabled(false);
				}
				{
					lblEntityType = new JLabel("Entity Type", JLabel.TRAILING);
					pnlContractDtl_1a.add(lblEntityType);
					lblEntityType.setEnabled(false);
				}
				{
					lblNTP_co = new JLabel("NTP No.", JLabel.TRAILING);
					pnlContractDtl_1a.add(lblNTP_co);
					lblNTP_co.setEnabled(false);
				}

				pnlContractDtl_1b = new JPanel(new GridLayout(4, 1, 5, 5));
				pnlContractDtl_1.add(pnlContractDtl_1b, BorderLayout.CENTER);
				pnlContractDtl_1b.setPreferredSize(new java.awt.Dimension(190, 125));
				pnlContractDtl_1b.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
				// pnlContractDtl_1b.setBorder(BorderFactory.createLineBorder(Color.BLUE));

				{
					txtProject = new JXTextField("");
					pnlContractDtl_1b.add(txtProject);
					txtProject.setEnabled(false);
					txtProject.setEditable(false);
					txtProject.setBounds(120, 25, 300, 22);
					txtProject.setHorizontalAlignment(JTextField.CENTER);
				}
				{
					txtContractor = new JXTextField("");
					pnlContractDtl_1b.add(txtContractor);
					txtContractor.setEnabled(false);
					txtContractor.setEditable(false);
					txtContractor.setBounds(120, 25, 300, 22);
					txtContractor.setHorizontalAlignment(JTextField.CENTER);
				}
				{
					txtEntity = new JXTextField("");
					pnlContractDtl_1b.add(txtEntity);
					txtEntity.setEnabled(false);
					txtEntity.setEditable(false);
					txtEntity.setBounds(120, 25, 300, 22);
					txtEntity.setHorizontalAlignment(JTextField.CENTER);
				}
				{
					txtNTP_no = new JXTextField("");
					pnlContractDtl_1b.add(txtNTP_no);
					txtNTP_no.setEnabled(false);
					txtNTP_no.setEditable(false);
					txtNTP_no.setBounds(120, 25, 300, 22);
					txtNTP_no.setHorizontalAlignment(JTextField.CENTER);
				}

				pnlContractDtl_2 = new JPanel(new GridLayout(4, 1, 5, 5));
				pnlContractDtl.add(pnlContractDtl_2, BorderLayout.CENTER);
				pnlContractDtl_2.setPreferredSize(new java.awt.Dimension(350, 123));
				pnlContractDtl_2.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
				// pnlContractDtl_2.setBorder(BorderFactory.createLineBorder(Color.ORANGE));

				{
					lblProject2 = new JLabel("[ ]");
					pnlContractDtl_2.add(lblProject2);
					lblProject2.setEnabled(false);
				}
				{
					lblContractor2 = new JLabel("[ ]");
					pnlContractDtl_2.add(lblContractor2);
					lblContractor2.setEnabled(false);
				}
				{
					lblEntity2 = new JLabel("[ ]");
					pnlContractDtl_2.add(lblEntity2);
					lblEntity2.setEnabled(false);
				}
				{
					JPanel pnlNote = new JPanel(new BorderLayout());
					pnlContractDtl_2.add(pnlNote);
					{
						btnViewNote = new JButton("VIEW");
						pnlNote.add(btnViewNote, BorderLayout.WEST);
						btnViewNote.setPreferredSize(new Dimension(60, 25));
						btnViewNote.setVisible(false);
						btnViewNote.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								popupNotesFromNTP(ntp_no);
							}
						});
					}
				}

				pnlContractDtl_3 = new JPanel(new BorderLayout(5, 5));
				pnlContractDtl.add(pnlContractDtl_3, BorderLayout.EAST);
				pnlContractDtl_3.setPreferredSize(new java.awt.Dimension(350, 135));
				pnlContractDtl_3.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));
				// pnlContractDtl_3.setBorder(BorderFactory.createLineBorder(Color.CYAN));

				pnlContractDtl_3a = new JPanel(new GridLayout(4, 1, 5, 5));
				pnlContractDtl_3.add(pnlContractDtl_3a, BorderLayout.WEST);
				pnlContractDtl_3a.setPreferredSize(new java.awt.Dimension(160, 135));
				pnlContractDtl_3a.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
				// pnlContractDtl_3a.setBorder(BorderFactory.createLineBorder(Color.GREEN));

				{
					lblContractPriceNew = new JLabel("Contract Price (New)", JLabel.TRAILING);
					pnlContractDtl_3a.add(lblContractPriceNew);
					lblContractPriceNew.setEnabled(false);
				}
				{
					lblContractPriceOrig = new JLabel("Contract Price (Orig)", JLabel.TRAILING);
					pnlContractDtl_3a.add(lblContractPriceOrig);
					lblContractPriceOrig.setEnabled(false);
				}
				{
					lblContractDP = new JLabel("DP %", JLabel.TRAILING);
					pnlContractDtl_3a.add(lblContractDP);
					lblContractDP.setEnabled(false);
				}
				{
					lblSuretyNo = new JLabel("Surety Bond No.", JLabel.TRAILING);
					pnlContractDtl_3a.add(lblSuretyNo);
					lblSuretyNo.setEnabled(false);
				}

				pnlContractDtl_3b = new JPanel(new GridLayout(4, 1, 0, 5));
				pnlContractDtl_3.add(pnlContractDtl_3b, BorderLayout.CENTER);
				pnlContractDtl_3b.setPreferredSize(new java.awt.Dimension(260, 135));
				pnlContractDtl_3b.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
				// pnlContractDtl_3b.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));

				{
					txtContractPriceNew = new JXTextField("");
					pnlContractDtl_3b.add(txtContractPriceNew);
					txtContractPriceNew.setEnabled(false);
					txtContractPriceNew.setEditable(false);
					txtContractPriceNew.setBounds(120, 25, 315, 22);
					txtContractPriceNew.setHorizontalAlignment(JTextField.CENTER);
				}
				{
					txtContractPriceOrig = new JXTextField("");
					pnlContractDtl_3b.add(txtContractPriceOrig);
					txtContractPriceOrig.setEnabled(false);
					txtContractPriceOrig.setEditable(false);
					txtContractPriceOrig.setBounds(120, 25, 315, 22);
					txtContractPriceOrig.setHorizontalAlignment(JTextField.CENTER);
				}
				{
					txtDP = new JXTextField("");
					pnlContractDtl_3b.add(txtDP);
					txtDP.setEnabled(false);
					txtDP.setBounds(120, 25, 315, 22);
					txtDP.setEditable(false);
					txtDP.setHorizontalAlignment(JTextField.CENTER);
				}
				{
					txtSuretyNo = new JXTextField("");
					pnlContractDtl_3b.add(txtSuretyNo);
					txtSuretyNo.setEnabled(false);
					txtSuretyNo.setEditable(false);
					txtSuretyNo.setBounds(120, 25, 315, 22);
					txtSuretyNo.setHorizontalAlignment(JTextField.CENTER);
				}
			}

			pnlSurety = new JPanel(new BorderLayout(5, 5));
			pnlContract.add(pnlSurety, BorderLayout.EAST);
			pnlSurety.setPreferredSize(new java.awt.Dimension(130, 127));
			pnlSurety.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		}
		{
			pnlTable = new JPanel(new BorderLayout(5, 5));
			pnlMain.add(pnlTable, BorderLayout.CENTER);
			pnlTable.setPreferredSize(new java.awt.Dimension(610, 30));

			splitSchedule = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			pnlTable.add(splitSchedule);
			splitSchedule.setOneTouchExpandable(true);
			splitSchedule.setResizeWeight(.7d);

			pnlBillingTable = new JPanel();
			splitSchedule.add(pnlBillingTable, JSplitPane.LEFT);
			// pnlTable.add(pnlBillingTable, BorderLayout.CENTER);
			pnlBillingTable.setLayout(new BorderLayout(5, 5));
			pnlBillingTable.setPreferredSize(new java.awt.Dimension(923, 199));
			pnlBillingTable.setBorder(lineBorder);

			tabCenter = new _JTabbedPane();
			pnlBillingTable.add(tabCenter, BorderLayout.CENTER);

			// Progress Billing
			{
				pnlProgBill = new JPanel(new BorderLayout());
				// pnlBillingTable.add(pnlProgBill, BorderLayout.CENTER);
				tabCenter.addTab("Progress Billing", null, pnlProgBill, null);
				pnlProgBill.setPreferredSize(new java.awt.Dimension(921, 178));
				{
					scrollProgBillMain = new _JScrollPaneMain();
					pnlProgBill.add(scrollProgBillMain, BorderLayout.CENTER);
					{
						modelProgBillMain = new modelContractorProgressBilling();

						tblProgBillMain = new _JTableMain(modelProgBillMain);
						scrollProgBillMain.setViewportView(tblProgBillMain);
						tblProgBillMain.addMouseListener(new PopupTriggerListener_panel2());

						tblProgBillMain.addMouseListener(this);
						tblProgBillMain.setSortable(false);
						tblProgBillMain.getColumnModel().getColumn(1).setPreferredWidth(80);
						tblProgBillMain.getColumnModel().getColumn(2).setPreferredWidth(80);
						tblProgBillMain.getColumnModel().getColumn(3).setPreferredWidth(80);
						tblProgBillMain.getColumnModel().getColumn(4).setPreferredWidth(80);
						tblProgBillMain.getColumnModel().getColumn(5).setPreferredWidth(80);
						tblProgBillMain.getColumnModel().getColumn(6).setPreferredWidth(80);
						tblProgBillMain.getColumnModel().getColumn(7).setPreferredWidth(80);
						tblProgBillMain.getColumnModel().getColumn(8).setPreferredWidth(80);
						tblProgBillMain.getColumnModel().getColumn(9).setPreferredWidth(80);
						tblProgBillMain.getColumnModel().getColumn(10).setPreferredWidth(80);
						tblProgBillMain.getColumnModel().getColumn(15).setPreferredWidth(200);
						tblProgBillMain.addKeyListener(new KeyAdapter() {
							public void keyReleased(KeyEvent evt) {
								// computeBillAmounts(evt);
								// computeBC_liquidation();
							}

							public void keyPressed(KeyEvent e) {
								// computeBillAmounts(e);
								// computeBC_liquidation();
							}

						});
						// tblProgBillMain.setDefaultRenderer(BigDecimal.class,
						// NumberRenderer.getMoneyRenderer2());
						tblProgBillMain.getColumnModel().getColumn(2)
								.setCellRenderer(NumberRenderer.getMoneyRenderer2());

						tblProgBillMain.addMouseListener(new MouseAdapter() {
							int row = tblProgBillMain.getSelectedRow();
							int column = tblProgBillMain.getSelectedColumn();

							public void mousePressed(MouseEvent e) {
								if (tblProgBillMain.rowAtPoint(e.getPoint()) == -1) {
									tblProgBillTotal.clearSelection();
								} else {
									tblProgBillMain.setCellSelectionEnabled(true);
								}
								if (txtNTP_no.getText().equals("000115") && row == 1 && column == 12) {
									rowHeaderProgBillMain.setToolTipText("test");
									tblProgBillMain.setToolTipText("test");
								}
							}

							public void mouseReleased(MouseEvent e) {
								if (tblProgBillMain.rowAtPoint(e.getPoint()) == -1) {
									tblProgBillTotal.clearSelection();
								} else {
									tblProgBillMain.setCellSelectionEnabled(true);
								}
								if (txtNTP_no.getText().equals("000115") && row == 1 && column == 12) {
									rowHeaderProgBillMain.setToolTipText("test");
									tblProgBillMain.setToolTipText("test");
								}
							}

						});

						tblProgBillMain.addPropertyChangeListener("tableCellEditor", new PropertyChangeListener() {
							public void propertyChange(PropertyChangeEvent arg0) {

								totalProgBill(modelProgBillMain, modelProgBillTotal);
							}
						});

					}
					{
						rowHeaderProgBillMain = tblProgBillMain.getRowHeader();
						scrollProgBillMain.setRowHeaderView(rowHeaderProgBillMain);
						scrollProgBillMain.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER,
								FncTables.getRowHeader_Header());
					}
				}
				{
					scrollProgBillTotal = new _JScrollPaneTotal(scrollProgBillMain);
					pnlBillingTable.add(scrollProgBillTotal, BorderLayout.SOUTH);
					scrollProgBillTotal.setPreferredSize(new java.awt.Dimension(921, 58));
					{
						modelProgBillTotal = new modelContractorProgressBilling();

						modelProgBillTotal.addRow(new Object[] { null, "Total", "Released >>", new BigDecimal(0.00),
								new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
								new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), null });

						modelProgBillTotal.addRow(new Object[] { null, "Total", "Balance >>", new BigDecimal(0.00),
								null, null, new BigDecimal(0.00), null, null, null, null, null });

						tblProgBillTotal = new _JTableTotal(modelProgBillTotal, tblProgBillMain);
						tblProgBillTotal.setFont(dialog11Bold);
						scrollProgBillTotal.setViewportView(tblProgBillTotal);
						((_JTableTotal) tblProgBillTotal).setTotalLabel(1);
					}
				}

			}

			pnlTableBC_Ded = new JPanel(new GridLayout(1, 1, 0, 0));
			splitSchedule.add(pnlTableBC_Ded, JSplitPane.RIGHT);
			// pnlTable.add(pnlTableBC_Ded, BorderLayout.SOUTH);
			pnlTableBC_Ded.setPreferredSize(new java.awt.Dimension(923, 166));
			pnlTableBC_Ded.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

			splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			pnlTableBC_Ded.add(splitPanel);
			splitPanel.setOneTouchExpandable(true);
			splitPanel.setResizeWeight(.7d);

			pnlTableBackcharges = new JPanel(new GridLayout(1, 1, 0, 0));
			// splitPanel.add(pnlTableBackcharges, BorderLayout.EAST);
			splitPanel.add(pnlTableBackcharges, JSplitPane.LEFT);
			pnlTableBackcharges.setPreferredSize(new java.awt.Dimension(416, 99));
			pnlTableBackcharges.setBorder(lineBorder);

			{
				pnlTblBC = new JPanel(new BorderLayout());
				pnlTableBackcharges.add(pnlTblBC, "Center");
				pnlTblBC.setPreferredSize(new java.awt.Dimension(1183, 365));

				{
					scrollBC = new _JScrollPaneMain();
					pnlTblBC.add(scrollBC, BorderLayout.CENTER);
					{
						modelBC = new modelContractorBackcharges();

						tblBC = new _JTableMain(modelBC);
						scrollBC.setViewportView(tblBC);
						tblBC.addMouseListener(this);
						tblBC.getColumnModel().getColumn(0).setPreferredWidth(80);
						tblBC.getColumnModel().getColumn(1).setPreferredWidth(80);
						tblBC.getColumnModel().getColumn(2).setPreferredWidth(80);
						tblBC.getColumnModel().getColumn(3).setPreferredWidth(80);
						tblBC.getColumnModel().getColumn(4).setPreferredWidth(80);
						tblBC.getColumnModel().getColumn(5).setPreferredWidth(80);
						tblBC.getColumnModel().getColumn(6).setPreferredWidth(80);
						tblBC.getColumnModel().getColumn(7).setPreferredWidth(80);
						tblBC.addKeyListener(new KeyAdapter() {
							public void keyReleased(KeyEvent evt) {
								computeTotalBackcharges();
								computeBackcharges(evt);
							}

							public void keyPressed(KeyEvent e) {
								computeTotalBackcharges();
								computeBackcharges(e);
							}

						});
						tblBC.addMouseListener(new MouseAdapter() {
							public void mousePressed(MouseEvent e) {
								if (tblBC.rowAtPoint(e.getPoint()) == -1) {
									tblBC_total.clearSelection();
								} else {
									tblBC.setCellSelectionEnabled(true);
								}
							}

							public void mouseReleased(MouseEvent e) {
								if (tblBC.rowAtPoint(e.getPoint()) == -1) {
									tblBC_total.clearSelection();
								} else {
									tblBC.setCellSelectionEnabled(true);
								}
							}
						});

					}
					{
						rowHeaderBC = tblBC.getRowHeader();
						rowHeaderBC.setToolTipText("test");
						scrollBC.setRowHeaderView(rowHeaderBC);
						scrollBC.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
					}
				}
				{
					scrollBC_total = new _JScrollPaneTotal(scrollBC);
					pnlTblBC.add(scrollBC_total, BorderLayout.SOUTH);
					{
						modelBC_total = new modelContractorBackcharges();
						modelBC_total.addRow(new Object[] { null, "Total", new BigDecimal(0.00), new BigDecimal(0.00),
								new BigDecimal(0.00), new BigDecimal(0.00), null, null });

						tblBC_total = new _JTableTotal(modelBC_total, tblBC);
						tblBC_total.setFont(dialog11Bold);
						scrollBC_total.setViewportView(tblBC_total);
						((_JTableTotal) tblBC_total).setTotalLabel(1);
					}
				}
			}
			pnlTableBackcharges.setBorder(JTBorderFactory.createTitleBorder("Backcharges"));

			// Backcharges

			pnlTableDeduction = new JPanel(new GridLayout(1, 1, 0, 0));
			splitPanel.add(pnlTableDeduction, JSplitPane.RIGHT);
			// pnlTableBC_Ded.add(pnlTableDeduction, BorderLayout.CENTER);
			pnlTableDeduction.setPreferredSize(new java.awt.Dimension(416, 99));
			pnlTableDeduction.setBorder(lineBorder);
			pnlTableDeduction.setBorder(JTBorderFactory.createTitleBorder("Other Deductions"));

			// Deduction
			{
				pnlTblOD = new JPanel(new BorderLayout());
				pnlTableDeduction.add(pnlTblOD, BorderLayout.CENTER);
				pnlTblOD.setPreferredSize(new java.awt.Dimension(1183, 365));

				{
					scrollOD = new _JScrollPaneMain();
					pnlTblOD.add(scrollOD, BorderLayout.CENTER);
					{
						modelOD = new modelContractorOtherDeductions();

						tblOD = new _JTableMain(modelOD);
						scrollOD.setViewportView(tblOD);
						tblOD.addMouseListener(new PopupTriggerListener_panel3());
						tblOD.addMouseListener(new MouseAdapter() {
							public void mousePressed(MouseEvent e) {

								if ((e.getClickCount() >= 2)) {
									int column = tblOD.getSelectedColumn();
									if (column == 1) {
										displayAddUnitsOD();
									}
									if (column == 0) {
										displayReferenceNo();
									}
									if (column == 7) {
										displayContractNo();
									}
									if (column == 6) {
										displayType();
									}
									// System.out.println("Click mo to");
								}
							}

							public void mouseReleased(MouseEvent e) {
								if ((e.getClickCount() >= 2)) {
									int column = tblOD.getSelectedColumn();
									if (column == 1) {
										displayAddUnitsOD();
									}
									if (column == 0) {
										displayReferenceNo();
									}
									if (column == 7) {
										getODcontractno();
									}
									if (column == 6) {
										displayType();
									}
									if (column == 7) {
										displayContractNo();
									}
								}
							}
						});

						tblOD.addKeyListener(new KeyAdapter() {
							public void keyReleased(KeyEvent evt) {
								System.out.println("Keyreleased");							
								computeOtherDeductions(evt);
								totalProgBill(modelProgBillMain, modelProgBillTotal);
							}

							public void keyPressed(KeyEvent e) {
								System.out.println("Keypressed");	
								computeOtherDeductions(e);
								totalProgBill(modelProgBillMain, modelProgBillTotal);
							}

						});

					}
					{
						rowHeaderOD = tblOD.getRowHeader();
						scrollOD.setRowHeaderView(rowHeaderOD);
						scrollOD.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
					}
				}
				{
					scrollOD_total = new _JScrollPaneTotal(scrollOD);
					pnlTblOD.add(scrollOD_total, BorderLayout.SOUTH);
					{
						modelOD_total = new modelContractorOtherDeductions();
						modelOD_total.addRow(new Object[] { null, "Total", new BigDecimal(0.00), null, null });

						tblOD_total = new _JTableTotal(modelOD_total, tblOD);
						tblOD_total.setFont(dialog11Bold);
						scrollOD_total.setViewportView(tblOD_total);
						((_JTableTotal) tblOD_total).setTotalLabel(1);
					}
				}
			}
		}
		{
			pnlSouth = new JPanel();
			pnlMain.add(pnlSouth, BorderLayout.SOUTH);
			pnlSouth.setLayout(new BorderLayout());
			pnlSouth.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			pnlSouth.setPreferredSize(new Dimension(55, 30));
			{
				JPanel pnlSouthCenter = new JPanel(new GridLayout(1, 2, 5, 5));
				pnlSouth.add(pnlSouthCenter, BorderLayout.CENTER);
				pnlSouthCenter.setPreferredSize(new Dimension(1000, 30));

				{
					btnAddNew = new JButton("Add");
					pnlSouthCenter.add(btnAddNew);
					btnAddNew.setActionCommand("Add");
					btnAddNew.addActionListener(this);
					btnAddNew.setEnabled(false);
				}
				{
					btnSave = new JButton("Save");
					pnlSouthCenter.add(btnSave);
					btnSave.setActionCommand("Save");
					btnSave.addActionListener(this);
					btnSave.setEnabled(false);
				}
				{
					btnPreview = new JButton("Preview");
					pnlSouthCenter.add(btnPreview);
					btnPreview.setActionCommand("Preview");
					btnPreview.setEnabled(false);
					btnPreview.addActionListener(this);
				}
				{
					btnRefresh = new JButton("Refresh");
					pnlSouthCenter.add(btnRefresh);
					btnRefresh.setActionCommand("Refresh");
					btnRefresh.setEnabled(false);
					btnRefresh.addActionListener(this);
				}
				{
					btnCancel = new JButton("Cancel");
					pnlSouthCenter.add(btnCancel);
					btnCancel.setActionCommand("Cancel");
					btnCancel.addActionListener(this);
					btnCancel.setEnabled(false);
				}
			}
		}

		FncTables.bindColumnTables(tblProgBillMain, tblProgBillTotal);

		// added 01/26/2016 - purpose - set CENQHOMES as default company
		initialize_comp();

		{
			pnlBackChargeTbl = new JPanel();
			pnlBackChargeTbl.setLayout(new BorderLayout(5, 5));
			pnlBackChargeTbl.setBorder(lineBorder);
			pnlBackChargeTbl.setPreferredSize(new java.awt.Dimension(600, 300));

			{
				{
					scrollViewBC_tbl = new _JScrollPaneMain();
					pnlBackChargeTbl.add(scrollViewBC_tbl, null);
					{
						modelView_BCrplf = new model_BC_table();

						tblBC_rplf = new _JTableMain(modelView_BCrplf);
						scrollViewBC_tbl.setViewportView(tblBC_rplf);
						tblBC_rplf.addMouseListener(this);
						tblBC_rplf.getColumnModel().getColumn(0).setPreferredWidth(50);
						tblBC_rplf.getColumnModel().getColumn(1).setPreferredWidth(240);
						tblBC_rplf.getColumnModel().getColumn(2).setPreferredWidth(80);
						tblBC_rplf.getColumnModel().getColumn(3).setPreferredWidth(80);
						tblBC_rplf.addMouseListener(new PopupTriggerListener_panel());
						tblBC_rplf.addKeyListener(new KeyAdapter() {
							public void keyReleased(KeyEvent evt) {
								tblBC_rplf.packAll();
							}

							public void keyPressed(KeyEvent e) {
								tblBC_rplf.packAll();
							}

						});
						tblBC_rplf.addMouseListener(new MouseAdapter() {
							public void mousePressed(MouseEvent e) {
								if (tblBC_rplf.rowAtPoint(e.getPoint()) == -1) {
								} else {
									tblBC_rplf.setCellSelectionEnabled(true);
								}

							}

							public void mouseReleased(MouseEvent e) {
								if (tblBC_rplf.rowAtPoint(e.getPoint()) == -1) {
								} else {
									tblBC_rplf.setCellSelectionEnabled(true);
								}
							}
						});
					}
					{
						rowHeaderBC_tbl = tblBC_rplf.getRowHeader22();
						scrollViewBC_tbl.setRowHeaderView(rowHeaderBC_tbl);
						scrollViewBC_tbl.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER,
								FncTables.getRowHeader_Header());
						scrollViewBC_tbl.setPreferredSize(new java.awt.Dimension(505, 220));
					}
				}
			}
		}
		{
			menu = new JPopupMenu("Popup");
			mniOD = new JMenuItem("Add Other Deduction");
			// mniView = new JMenuItem("View PV Details");
			menu.add(mniOD);
			// menu.add(mniView);

			mniOD.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {

					modelOD.addRow(new Object[] { "", "", "", new BigDecimal(0.00), "", "" });
					// totalJV(modelOD, modelOD_total);
					((DefaultListModel) rowHeaderOD.getModel()).addElement(modelOD.getRowCount());
					totalOD(modelOD, modelOD_total);
					totalProgBill(modelProgBillMain, modelProgBillTotal);
					// adjustRowHeight_account();
				}
			});

			/*
			 * mniView.addActionListener(new ActionListener(){ public void
			 * actionPerformed(ActionEvent evt){ Integer x = tblBC_rplf.getSelectedRow();
			 * String bc_rplf_no = modelView_BCrplf.getValueAt(x,0).toString();
			 * openBC_PV(bc_rplf_no); Window optionPaneWindow =
			 * SwingUtilities.getWindowAncestor(pnlBackChargeTbl);
			 * optionPaneWindow.dispose(); } });
			 */
		}

	}

	// display
	private static void displayProgressBilling(DefaultTableModel modelMain, JList rowHeader,
			DefaultTableModel modelTotal, String ntp_no) {

		FncTables.clearTable(modelMain);// Code to clear modelMain.
		DefaultListModel listModel = new DefaultListModel();// Creating DefaultListModel for rowHeader.
		rowHeader.setModel(listModel);// Setting of DefaultListModel into rowHeader.

		String sql = "select * from display_ProgressBilling('" + co_id + "','" + ntp_no + "')";
		/*
		 * "(" + "select \r\n" + "a.billing_type, \r\n" +
		 * "to_char(a.billing_date,  'MM/dd/yyyy'),\r\n" + "a.accomp_pctg, \r\n" +
		 * "a.gross_amt, \r\n" + "a.vat_amt,\r\n" + "a.wtax_amt,\r\n" +
		 * "a.dp_recoupment,\r\n" + "a.retention_amt,\r\n" + "a.ca_liquidation,\r\n" +
		 * "a.other_deductions,\r\n" + "a.net_amt,\r\n" + "a.billing_no,\r\n" +
		 * "a.rplf_no,\r\n" + "to_char(d.date_paid,  'MM/dd/yyyy'),\r\n" +
		 * "(case when d.date_paid is not null and a.status_id != 'I'  then 'RELEASED' else "
		 * +
		 * "	(case when c.status_id = 'F' and a.status_id != 'I' then 'TAGGED' else \r\n"
		 * +
		 * "	(case when c.status_id = 'P' and a.status_id != 'I' then 'POSTED' else \r\n"
		 * + "	(case when c.status_id = 'I' then 'INACTIVE' else \r\n" +
		 * "	(case when cc.status_id = 'I' then 'INACTIVE' else \r\n" +
		 * "	(case when a.status_id = 'I' then 'INACTIVE' else \r\n" +
		 * "	(case when c.status_id = 'A' and a.status_id != 'I' then 'ACTIVE' else 'ACTIVE' end) end) end) end) end) end) end) as status \r\n"
		 * + "\r\n" + "from (select * from co_billing_detail where ntp_no='"+ntp_no+"'"
		 * + "	order by rec_id ) a\r\n" + //and status_id != 'I' I removed this as per
		 * DCRF No. 468 "left join mf_record_status b on a.status_id = b.status_id " +
		 * "left join rf_pv_header c on a.rplf_no = c.pv_no and a.co_id = c.co_id  \n" +
		 * "left join rf_request_header cc on a.rplf_no = cc.rplf_no and a.co_id = cc.co_id  \n"
		 * + "left join rf_cv_header d on c.cv_no = d.cv_no and c.co_id =d.co_id  \n" +
		 * "order by a.billing_type, a.billing_date, a.accomp_pctg " +
		 * ") a where (billing_type, status) not in (select '02'::text, 'INACTIVE'::text) "
		 * ;
		 */

		System.out.printf("SQL #1 prog billing: %s", sql);
		pgSelect db = new pgSelect();
		db.select(sql);
		if (db.isNotNull()) {
			for (int x = 0; x < db.getRowCount(); x++) {
				// Adding of row in table
				modelMain.addRow(db.getResult()[x]);
				listModel.addElement(modelMain.getRowCount());
			}

			totalProgBill(modelProgBillMain, modelProgBillTotal);
		}

		else {
			modelProgBillTotal = new modelContractorProgressBilling();
			modelProgBillTotal.addRow(new Object[] { null, "Total", "released >>", new BigDecimal(0.00),
					new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
					new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), null, null, null, null, null });
			modelProgBillTotal.addRow(new Object[] { null, "Total", "balance >>", new BigDecimal(0.00), null, null,
					new BigDecimal(0.00), null, null, null, null, null, null, null, null });

			tblProgBillTotal = new _JTableTotal(modelProgBillTotal, tblProgBillMain);
			scrollProgBillTotal.setViewportView(tblProgBillTotal);
			((_JTableTotal) tblProgBillTotal).setTotalLabel(1);
		}

		tblProgBillMain.packAll();
		tblProgBillMain.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblProgBillMain.getColumnModel().getColumn(2).setPreferredWidth(80);//
		tblProgBillMain.getColumnModel().getColumn(3).setPreferredWidth(80);//
		tblProgBillMain.getColumnModel().getColumn(4).setPreferredWidth(80);//
		tblProgBillMain.getColumnModel().getColumn(5).setPreferredWidth(80);//
		tblProgBillMain.getColumnModel().getColumn(6).setPreferredWidth(80);//
		tblProgBillMain.getColumnModel().getColumn(7).setPreferredWidth(80);//
		tblProgBillMain.getColumnModel().getColumn(8).setPreferredWidth(80);//
		tblProgBillMain.getColumnModel().getColumn(9).setPreferredWidth(80);//
		tblProgBillMain.getColumnModel().getColumn(10).setPreferredWidth(80);//
		tblProgBillMain.getColumnModel().getColumn(15).setPreferredWidth(150);//
		row_num = tblProgBillMain.getModel().getRowCount();
		tblProgBillTotal.setFont(dialog11Bold);
		tblProgBillMain.packAll();

	}

	private static void displayBackCharges(DefaultTableModel modelMain, JList rowHeader, DefaultTableModel modelTotal,
			String entity_id) {

		FncTables.clearTable(modelMain);// Code to clear modelMain.
		DefaultListModel listModel = new DefaultListModel();// Creating DefaultListModel for rowHeader.
		rowHeader.setModel(listModel);// Setting of DefaultListModel into rowHeader.

		String sql =

				// Regular Backcharges
				"select * from get_contractor_backcharge('" + entity_id + "', '" + lookupCompany.getValue() + "')";

		/*
		 * "select distinct on (a.rplf_no::integer) \r\n" + "	a.rplf_no, \r\n" +
		 * "	to_char(a.repair_date, 'mm/dd/yy'), \r\n" + "	b.amount, \r\n" +
		 * "	(select coalesce(sum(liq_amt), 0.00) from co_backcharge_liquidation where bc_rplf_no=a.rplf_no) as liquidated, \r\n"
		 * + "	0.00 as for_liquidated, \r\n" +
		 * "	coalesce(b.amount - (select coalesce(sum(liq_amt), 0.00) from co_backcharge_liquidation where bc_rplf_no=a.rplf_no), 0.00) as ob, \r\n"
		 * + "	''::text as type\r\n" + "	\r\n" + "\r\n" +
		 * "from co_house_repair a \r\n" +
		 * "left join co_house_repair_backcharges b on b.repair_no=a.repair_no \r\n" +
		 * "left join mf_project c on c.proj_id=a.proj_id \r\n" +
		 * "left join co_backcharge_liquidation d on d.bc_rplf_no=a.rplf_no \r\n" +
		 * "left join co_house_repair e on b.repair_no=e.repair_no \r\n" +
		 * "left join rf_pv_header f on e.rplf_no=f.pv_no \r\n" +
		 * "left join rf_cv_header g on f.cv_no=g.cv_no \r\n" +
		 * "where b.entity_id='"+entity_id+"' \r\n" +
		 * "--and case when d.liq_amt is not null then b.amount - coalesce(d.liq_amt, 0.00) > 0.00 else d.liq_amt is null end \r\n"
		 * +
		 * "--and b.amount > (select coalesce(sum(liq_amt), 0.00) from co_backcharge_liquidation where bc_rplf_no=a.rplf_no)\r\n"
		 * +
		 * "--and a.rplf_no not in (select bc_rplf_no from co_backcharge_liquidation where bc_rplf_no is not null order by bc_rplf_no::integer) \r\n"
		 * + "--and d.liq_amt!=b.amount \r\n" + "and g.date_paid is not null \r\n" +
		 * "order by a.rplf_no::integer ) a " + "where a.ob > 0 " +
		 * 
		 * //JV Backcharges "union all\n" + "\n" +
		 * "select a.jv_no, to_char(b.jv_date,'MM/dd/yyyy') as jv_date, a.tran_amt, " +
		 * "	(select coalesce(sum(liq_amt), 0.00) from co_backcharge_liquidation where bc_rplf_no = a.jv_no) as liquidated,\n"
		 * + "	0::numeric, \n" +
		 * "	coalesce(a.tran_amt - (select coalesce(sum(liq_amt), 0.00) from co_backcharge_liquidation where bc_rplf_no=a.jv_no), 0.00) as ob, "
		 * + "	''::text\n" + "from (select * from rf_jv_detail a \n" +
		 * "	where acct_id in ('01-02-07-002', '01-02-07-003') \n" +
		 * "	and bal_side = 'D'\n" + "	and status_id != 'I'" +
		 * "	and entity_id = '"+entity_id+"') a \n" +
		 * "join (select * from rf_jv_header where status_id = 'P') b on a.jv_no = b.jv_no \n"
		 * + "left join co_backcharge_liquidation c on c.bc_rplf_no= a.jv_no \n " +
		 * "where coalesce(a.tran_amt - (select coalesce(sum(liq_amt), 0.00) from co_backcharge_liquidation where bc_rplf_no=a.jv_no), 0.00) > 0 \n"
		 * +
		 * 
		 * //PV Backcharges "union all \n" + "\n" +
		 * "select a.pv_no, to_char(b.pv_date,'MM/dd/yyyy') as pv_date, a.tran_amt, \n"
		 * +
		 * "	(select coalesce(sum(liq_amt), 0.00) from co_backcharge_liquidation where bc_rplf_no = a.pv_no) as liquidated,\n"
		 * + "	0::numeric, \n" +
		 * "	coalesce(a.tran_amt - (select coalesce(sum(liq_amt), 0.00) from co_backcharge_liquidation where bc_rplf_no=a.pv_no), 0.00) as ob, \n"
		 * + "	''::text\n" + "from (select * from rf_pv_detail a \n" +
		 * "	where acct_id in ('01-02-07-002', '01-02-07-003') \n" +
		 * "	and bal_side = 'D'\n" + "	and status_id != 'I' \n" +
		 * "	and pv_no not in (select rplf_no from co_house_repair where status_id = 'A')\n"
		 * + "	) a\n" +
		 * "join (select * from rf_pv_header where cv_no in (select cv_no from rf_cv_header \n"
		 * + "	where status_id = 'P' and entity_id2 = '"
		 * +entity_id+"' )) b on a.pv_no = b.pv_no	\n" +
		 * "where coalesce(a.tran_amt - (select coalesce(sum(liq_amt), 0.00) from co_backcharge_liquidation where bc_rplf_no=a.pv_no), 0.00 > 0 \n\n"
		 * ;
		 */
		System.out.printf("SQL #1 backcharges: %s", sql);
		pgSelect db = new pgSelect();
		db.select(sql);
		if (db.isNotNull()) {
			for (int x = 0; x < db.getRowCount(); x++) {
				// Adding of row in table
				modelMain.addRow(db.getResult()[x]);
				listModel.addElement(modelMain.getRowCount());
			}

			totalBC(modelMain, modelTotal);
		}

		else {
			modelBC_total = new modelContractorBackcharges();
			modelBC_total.addRow(new Object[] { null, "Total", new BigDecimal(0.00), new BigDecimal(0.00),
					new BigDecimal(0.00), new BigDecimal(0.00), null, null });

			tblBC_total = new _JTableTotal(modelBC_total, tblBC);
			tblBC_total.setFont(dialog11Bold);
			scrollBC_total.setViewportView(tblBC_total);
			((_JTableTotal) tblBC_total).setTotalLabel(1);
		}

		tblBC.packAll();
		tblBC.getColumnModel().getColumn(0).setPreferredWidth(80);
		tblBC.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblBC.getColumnModel().getColumn(2).setPreferredWidth(80);
		tblBC.getColumnModel().getColumn(3).setPreferredWidth(80);
		tblBC.getColumnModel().getColumn(4).setPreferredWidth(80);
		tblBC.getColumnModel().getColumn(5).setPreferredWidth(80);
		tblBC.getColumnModel().getColumn(6).setPreferredWidth(80);
		tblBC.getColumnModel().getColumn(7).setPreferredWidth(80);

	}

	private static void displayOtherDeductions(DefaultTableModel modelMain, JList rowHeader,
			DefaultTableModel modelTotal, String entity_id) {

		FncTables.clearTable(modelMain);// Code to clear modelMain.
		DefaultListModel listModel = new DefaultListModel();// Creating DefaultListModel for rowHeader.
		rowHeader.setModel(listModel);// Setting of DefaultListModel into rowHeader.

		String sql = "select a.ref_pvjv_no as ref_no, a.acct_id, b.acct_name, a.ded_amt, a.remarks, a.bill_no, a.type_id, a.contract_no, '' \r\n"
				+ "\r\n" + "from co_other_deduction a\r\n"
				+ "left join mf_boi_chart_of_accounts b on a.acct_id = b.acct_id \n"
				+ "left join (select * from co_billing_detail where status_id != 'I') c \n"
				+ "	on a.bill_no = c.billing_no \r\n" +

				"where trim(a.entity_id) = '" + entity_id + "'    \r\n" + "and c.ntp_no = '" + ntp_no + "'   \r\n";

		System.out.printf("SQL #1 other ded'n: %s", sql);
		pgSelect db = new pgSelect();
		db.select(sql);
		if (db.isNotNull()) {
			for (int x = 0; x < db.getRowCount(); x++) {
				// Adding of row in table
				modelMain.addRow(db.getResult()[x]);
				listModel.addElement(modelMain.getRowCount());
			}

			totalOD(modelMain, modelTotal);
		}

		else {
			modelOD_total = new modelContractorOtherDeductions();
			modelOD_total.addRow(new Object[] { null, "Total", new BigDecimal(0.00), null, null });

			tblOD_total = new _JTableTotal(modelOD_total, tblOD);
			tblOD_total.setFont(dialog11Bold);
			scrollOD_total.setViewportView(tblOD_total);
			((_JTableTotal) tblOD_total).setTotalLabel(1);
		}

		tblOD.packAll();

	}

	public static void setContractDetails(String s_bond, String ntp_no) {

		enable_fields(true);

		Object[] contract = getContractDtls(ntp_no);

		txtNTP_no.setText((String) contract[0]);
		txtProject.setText((String) contract[1]);
		lblProject2.setText("[ " + (String) contract[2] + " ]");
		txtContractor.setText((String) contract[3]);
		deduction_amt = (Double.valueOf(txtDAmount.getText().trim().replace(",", "")).doubleValue());

		contr_name = (String) contract[4];

		lblContractor2.setText("[ " + contr_name.replace("'", "''") + " ( " + (String) contract[11] + " )" + " ]");
		txtContractPriceOrig.setText(nf.format(Double.parseDouble((String) contract[5].toString())));

		txtContractPriceNew.setText(nf.format(Double.parseDouble((String) contract[6].toString())));

		new_price = Double.parseDouble(contract[5].toString());

		txtDP.setText(nf.format(Double.parseDouble((String) contract[7].toString())));

		// System.out.printf("new contract price : %s", Double.parseDouble((String)
		// contract[6])).toString();

		// set VAT Rate
		if (contract[11].toString().equals("VAT")) {
			vat_rate = 0.12;
		} else {
			vat_rate = 0.00;
		}

		if (new_price == 0.00) {
			dp_amount = Double.parseDouble((String) contract[5].toString()) * Double.parseDouble(contract[7].toString())
					/ 100;
		} else {
			dp_amount = new_price * Double.parseDouble(contract[7].toString()) / 100;
		}

		System.out.printf("dp_amount: " + dp_amount);

		if (contract[8] == null) {
			txtSuretyNo.setText("-");
		} else {
			txtSuretyNo.setText((String) contract[8]);
		}

		txtEntity.setText((String) contract[10]);
		wtax_rate = getWtaxRate(txtEntity.getText().trim());

		// System.out.printf("wtax_rate: %s" + wtax_rate);

		lblEntity2.setText("[ " + (String) contract[9] + " ( " + wtax_rate + "% )" + " ]");

		row_num = 0;
		row_num_bc = 0;
		row_num_od = 0;

		billed_ret_amt = 0.00;
		displayProgressBilling(modelProgBillMain, rowHeaderProgBillMain, modelProgBillTotal, (String) contract[0]);
		displayBackCharges(modelBC, rowHeaderBC, modelBC_total, (String) contract[3]);
		displayOtherDeductions(modelOD, rowHeaderOD, modelOD_total, (String) contract[3]);

		modelProgBillMain.setEditable(false);
		modelBC.setEditable(false);
		modelOD.setEditable(false);

		if (modelProgBillTotal.getValueAt(1, 3).toString().equals("0.00")
				&& modelProgBillTotal.getValueAt(1, 6).toString().equals("0")
				&& modelProgBillTotal.getValueAt(1, 7).toString().equals("0")) {
			enableButtons(false, false, true, true, true, false);
		} else if (isRetentionPaid() == true) {
			enableButtons(false, false, true, true, true, false);
		} else {
			enableButtons(true, false, true, true, true, false);
		}

		KEYBOARD_MANAGER.focusNextComponent();

		// Added on 05/10/2018 : see DCRF No.565
		if (((String) contract[13]).equals("A")) {
			btnAddNew.setEnabled(true);
			btnAddDeductives.setEnabled(true);

			String surety_bond = s_bond;
			Boolean is_taken_over = (Boolean) contract[17]; // new contract
			Boolean taken_over_contract = (Boolean) contract[20]; // old contract
			// previous
			String tko_contractor = (String) contract[19];
			String tko_ntp = (String) contract[16];
			// current
			String current_tko_contractor = (String) contract[23];
			String current_tko_ntp = (String) contract[22];
			String current_tko_contractno = (String) contract[21];

			if (is_taken_over.equals(true)) {
				JOptionPane.showMessageDialog(null,
						" THIS CONTRACT IS TAKEN OVER FROM  " + "\n" + "Previous Contractor   : " + tko_contractor
								+ "\n" + "Previous NTP No.   : " + tko_ntp,
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}

			if (taken_over_contract.equals(true)) {

				JOptionPane.showMessageDialog(null,
						" THIS CONTRACT IS TAKEN OVER BY  " + "\n" + "New Contractor  : " + current_tko_contractor
								+ "\n" + "New NTP No. : " + current_tko_ntp + "\n" + "New Contract No.  : "
								+ current_tko_contractno,
						"Information", JOptionPane.INFORMATION_MESSAGE);

				int l = tblProgBillMain.getRowCount() - 1;
				Object re = tblProgBillMain.getValueAt(l, 0).toString();
				System.out.printf("Display value of l: %s%n", l);
				System.out.printf("Display value of re: %s%n", re);

				if ((re).equals("03")) {
					enableButtons(false, false, true, true, true, false);
					Double tko_retention_bal = ret_bal.doubleValue();
					System.out.println("Dumaan dito check lr");
					System.out.println("Retention Balance: " + ret_bal);
					System.out.println("tko_retention_bal: " + tko_retention_bal);
					// if(txtNTP_no.getText().equals("001516")) {
					// Comment by erick 2024-02-14 to process adjustments as per Rochelle of FAD
//					if (tko_retention_bal == 0.00 ) { // Added by Erick 2021-08-25 For accounts with processed retention
//														// but still have remaining retentions
//
//						cmbBillingType.setEnabled(false);
//
//					} else {
//						cmbBillingType.setEnabled(true);
//					}
//					;
					cmbBillingType.setEnabled(true);

				} else {
					enableButtons(true, false, true, true, true, false);
					pnlEditBType.setEnabled(true);
					cmbBillingType.setEnabled(true);
				}
				;

			}
			FncSystem.out("TKO CURRENT NTP", (String) contract[0]);
			FncSystem.out("TKO PREVIOUS NTP", (String) contract[16]);

			if (surety_bond.equals("YES")) {
				tagContractNo.setText("[ With Surety Bond ]");
			} else {
				tagContractNo.setText("[ No Surety Bond ]");
				tagContractNo.setForeground(Color.blue);
			}

		} else {
			String deleted_by = (String) contract[14];
			String deleted_date = ((Date) contract[15]).toString();
			JOptionPane.showMessageDialog(null, "    CANCELED CONTRACT  " + "\n" + "Canceled By : " + deleted_by + "\n"
					+ "Canceled Date : " + deleted_date, "Information", JOptionPane.INFORMATION_MESSAGE);
			btnAddNew.setEnabled(false);
			btnAddDeductives.setEnabled(false);
			String surety_bond = s_bond;
			if (surety_bond.equals("YES")) {
				tagContractNo.setText("[ CANCELED CONTRACT  ]");
				tagContractNo.setForeground(Color.red);
			}
		}
		;

	}

	private void displayBackChargeRPLF(DefaultTableModel modelMain, JList rowHeader, String row_bill_no) {//

		FncTables.clearTable(modelMain);// Code to clear modelMain.
		DefaultListModel listModel = new DefaultListModel();// Creating DefaultListModel for rowHeader.
		rowHeader.setModel(listModel);// Setting of DefaultListModel into rowHeader.

		String sql = "select \r\n" + "a.bc_rplf_no, \r\n" +
		// "to_char(b.rplf_date,'MM-dd-yyyy'), \r\n" +
		// "trim(b.remarks), \r\n" +
				"(case when to_char(b.rplf_date,'MM-dd-yyyy') is null then to_char(e.jv_date,'MM-dd-yyyy') else to_char(b.rplf_date,'MM-dd-yyyy') end),  \n"
				+ "(case when trim(b.remarks) is null then trim(e.remarks) else trim(b.remarks) end), \n"
				+ "(case when d.date_paid is not null then 'Released' else\r\n"
				+ "   case when c.cv_no is not null then 'Processed' else\r\n"
				+ "   case when b.status_id = 'A' then 'Active' else \r\n"
				+ "   case when b.status_id = 'I' then 'Inactive' end end end end),"
				+ "to_char(d.date_paid,'MM-dd-yyyy') \r\n" + " \r\n" + "\r\n" + "from co_backcharge_liquidation a\r\n"
				+ "left join (select * from rf_request_header where status_id = 'A') b on a.bc_rplf_no = b.rplf_no and a.bc_co_id = b.co_id\r\n"
				+ "left join rf_pv_header c on b.rplf_no = c.pv_no \r\n"
				+ "left join rf_cv_header d on c.cv_no = d.cv_no \n\n"
				+ "left join rf_jv_header e on a.bc_rplf_no = e.jv_no and e.status_id = 'P' \n" + "where a.bill_no = '"
				+ row_bill_no + "' \n" + "order by a.bc_rplf_no";

		System.out.printf("SQL BackCharge: %s", sql);
		pgSelect db = new pgSelect();
		db.select(sql);
		if (db.isNotNull()) {
			for (int x = 0; x < db.getRowCount(); x++) {
				// Adding of row in table
				modelMain.addRow(db.getResult()[x]);
				listModel.addElement(modelMain.getRowCount());
			}
		}

		else {
			/*
			 * JOptionPane.showMessageDialog(getContentPane(),"Selected unit does not \n" +
			 * "have client request.","Warning",JOptionPane.WARNING_MESSAGE);
			 */
		}

		tblBC_rplf.packAll();
		//Comm_Sched_Transfer.adjustRowHeight_account(tblBC_rplf);
	}

	// Enable/Disable all components inside JPanel
	/*
	 * private void setComponentEnabled(JPanel panel, boolean enable) {
	 * for(Component comp : panel.getComponents()){ comp.setEnabled(enable); } }
	 */

	private static void enable_fields(Boolean x) {

		lblContractNo.setEnabled(x);
		lookupContractNo.setEnabled(x);
		tagContractNo.setEnabled(x);

		lblProject.setEnabled(x);
		lblContractor.setEnabled(x);
		lblEntityType.setEnabled(x);
		lblNTP_co.setEnabled(x);
		lblContractPriceNew.setEnabled(x);
		lblContractPriceOrig.setEnabled(x);
		lblContractDP.setEnabled(x);
		lblSuretyNo.setEnabled(x);

		txtProject.setEnabled(x);
		txtContractor.setEnabled(x);
		txtEntity.setEnabled(x);
		txtNTP_no.setEnabled(x);
		txtContractPriceNew.setEnabled(x);
		txtContractPriceOrig.setEnabled(x);
		txtDP.setEnabled(x);
		txtSuretyNo.setEnabled(x);

		lblProject2.setEnabled(x);
		lblContractor2.setEnabled(x);
		lblEntity2.setEnabled(x);

		// btnAddNew.setEnabled(x);
		// btnRefresh.setEnabled(x);
		// btnCancel.setEnabled(x);

	}

	private void refresh_fields() {

		lookupCompany.setValue("");
		tagCompany.setText("[ ]");

		lookupContractNo.setValue("");
		tagContractNo.setText("");

		txtProject.setText("");
		txtContractor.setText("");
		txtEntity.setText("");
		txtNTP_no.setText("");
		txtContractPriceNew.setText("");
		txtContractPriceOrig.setText("");
		txtDP.setText("");
		txtSuretyNo.setText("");

		lblProject2.setText("[ ]");
		lblContractor2.setText("[ ]");
		lblEntity2.setText("[ ]");

	}

	private void refresh_tablesMain() {

		// reset table 1
		FncTables.clearTable(modelProgBillMain);
		FncTables.clearTable(modelProgBillTotal);
		rowHeaderProgBillMain = tblProgBillMain.getRowHeader();
		scrollProgBillMain.setRowHeaderView(rowHeaderProgBillMain);
		modelProgBillTotal.addRow(new Object[] { null, "Total", null, null, null, null, new BigDecimal(0.00), null });

	}

	private void refresh_tables() {

		// reset table 2
		FncTables.clearTable(modelBC);
		FncTables.clearTable(modelBC_total);
		rowHeaderBC = tblBC.getRowHeader();
		scrollBC.setRowHeaderView(rowHeaderBC);
		modelBC_total.addRow(new Object[] { null, "Total", new BigDecimal(0.00), new BigDecimal(0.00),
				new BigDecimal(0.00), new BigDecimal(0.00), null, null });

		// reset table 3
		FncTables.clearTable(modelOD);
		FncTables.clearTable(modelOD_total);
		rowHeaderOD = tblOD.getRowHeader();
		scrollOD.setRowHeaderView(rowHeaderOD);
		modelOD_total.addRow(new Object[] { null, "Total", new BigDecimal(0.00), null, null });
	}

	private static void enableButtons(Boolean a, Boolean b, Boolean c, Boolean d, Boolean e, Boolean f) {

		btnAddNew.setEnabled(a);
		btnSave.setEnabled(b);
		btnPreview.setEnabled(c);
		btnRefresh.setEnabled(d);
		btnCancel.setEnabled(e);
		btnAddDeductives.setEnabled(f);

	}

	// action performed
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Refresh")) {
			refresh();
		}

		if (e.getActionCommand().equals("Cancel")) {
			cancel();
		}

		if (e.getActionCommand().equals("Add Deductives")) {
			pgUpdate db = new pgUpdate();
			adddeduction(db);
		}

		if (e.getActionCommand().equals("Add") && FncAcounting.EmpCanAddNew(UserInfo.EmployeeCode, "5") == true) {
			addnew();
		} else if (e.getActionCommand().equals("Add")
				&& FncAcounting.EmpCanAddNew(UserInfo.EmployeeCode, "5") == false) {
			JOptionPane.showMessageDialog(getContentPane(),
					"Sorry, you are not authorized to process contractor's billing.", "Information",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (e.getActionCommand().equals("Save") && FncAcounting.EmpCanAddNew(UserInfo.EmployeeCode, "5") == true) {
			save();
		} else if (e.getActionCommand().equals("Save")
				&& FncAcounting.EmpCanAddNew(UserInfo.EmployeeCode, "5") == false) {
			JOptionPane.showMessageDialog(getContentPane(),
					"Sorry, you are not authorized to process contractor's billing.", "Information",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (e.getActionCommand().equals("Preview") && FncAcounting.EmpCanPreview(UserInfo.EmployeeCode, "5") == true) {
			preview();
		} else if (e.getActionCommand().equals("Preview")
				&& FncAcounting.EmpCanPreview(UserInfo.EmployeeCode, "5") == false) {
			JOptionPane.showMessageDialog(getContentPane(),
					"Sorry, you are not authorized to preview Contractors Billing Ledger.", "Information",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (e.getActionCommand().equals("ODeductions")
				&& FncAcounting.EmpCanPreview(UserInfo.EmployeeCode, "5") == true) {

		} else if (e.getActionCommand().equals("ODeductions")
				&& FncAcounting.EmpCanPreview(UserInfo.EmployeeCode, "5") == false) {
			JOptionPane.showMessageDialog(getContentPane(),
					"Sorry, you are not authorized to preview Contractors Billing Ledger.", "Information",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void mouseClicked(MouseEvent evt) {
		if ((evt.getClickCount() >= 2)) {
			displayAddUnits();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (lookupContractNo.getValue().equals("TV-2017-PH2-002L")
				&& modelProgBillMain.getValueAt(1, 12).equals("000004266")) {
			tblProgBillMain.setToolTipText(
					"For 1st Progress Billing - Actual amt released: 4,070,862 Addt'l check prepared: 11,239.71 PV no: 4485 JV no: 1706083");
		} else {
			tblProgBillMain.setToolTipText("");
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (lookupContractNo.getValue().equals("TV-2017-PH2-002L")
				|| modelProgBillMain.getValueAt(tblProgBillMain.getSelectedRow(), 12).equals("000004266")) {
			tblProgBillMain.setToolTipText(
					"For 1st Progress Billing - Actual amt released: 4,070,862 Addt'l check prepared: 11,239.71 PV no: 4485 JV no: 1706083");
		} else {
			tblProgBillMain.setToolTipText("");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (lookupContractNo.getValue().equals("TV-2017-PH2-002L")
				|| modelProgBillMain.getValueAt(tblProgBillMain.getSelectedRow(), 12).equals("000004266")) {
			tblProgBillMain.setToolTipText(
					"For 1st Progress Billing - Actual amt released: 4,070,862 Addt'l check prepared: 11,239.71 PV no: 4485 JV no: 1706083");
		} else {
			tblProgBillMain.setToolTipText("");
		}
	}

	public class PopupTriggerListener_panel2 extends MouseAdapter {
		public void mousePressed(MouseEvent ev) {
			if (ev.isPopupTrigger()) {

				int column = tblProgBillMain.getSelectedColumn();
				System.out.println("Column :" + column);
				int row = tblProgBillMain.getSelectedRow();
				System.out.println("Row :" + row);

				rplf_no = modelProgBillMain.getValueAt(row, 12).toString().trim();
				String status = modelProgBillMain.getValueAt(row, 14).toString().trim();
				double backcharge_amt = Double.parseDouble(modelProgBillMain.getValueAt(row, 8).toString());
				String billtype = modelProgBillMain.getValueAt(row, 0).toString().trim();

				System.out.println("rplf_no: " + rplf_no);
				System.out.println("status: " + status);
				System.out.println("billtype: " + billtype);

				if (column == 12) {
					menu2.show(ev.getComponent(), ev.getX(), ev.getY());
					mniopenRPLF.setEnabled(true);
					mniopenPV.setEnabled(false);
					mnieditperc.setEnabled(false);
					mnieditamt.setEnabled(false);
					mnieditDate.setEnabled(false);
					String cv_no = RequestForPayment.sql_getCVno(rplf_no, co_id).trim();
					if (!cv_no.equals("")) {
						mniopenCV.setEnabled(true);
					} else {
						mniopenCV.setEnabled(false);
						System.out.printf("CV_No : " + cv_no);
					}
				} else if (column == 1) {
					menu2.show(ev.getComponent(), ev.getX(), ev.getY());
					mniopenRPLF.setEnabled(false);
					mniopenPV.setEnabled(false);
					mniopenCV.setEnabled(false);
					mnieditperc.setEnabled(false);
					mnieditamt.setEnabled(false);
					mnieditDate.setEnabled(true);
				} else if (column == 2 && rplf_no.equals("") && billtype.equals("02")) {
					menu2.show(ev.getComponent(), ev.getX(), ev.getY());
					mniopenRPLF.setEnabled(false);
					mniopenPV.setEnabled(false);
					mniopenCV.setEnabled(false);
					mnieditperc.setEnabled(true);
					mnieditamt.setEnabled(false);
					mnieditDate.setEnabled(false);
				} else if (column == 3 || column == 4 || column == 5 || column == 6 || column == 7 || column == 8
						|| column == 9 || column == 10) {
					menu2.show(ev.getComponent(), ev.getX(), ev.getY());
					if (rplf_no.equals("")) {
						mniopenRPLF.setEnabled(false);
						mniopenPV.setEnabled(false);
						mniopenCV.setEnabled(false);
						mnieditperc.setEnabled(false);
						mnieditamt.setEnabled(true);
						mnieditDate.setEnabled(false);
					} else {
						mniopenRPLF.setEnabled(false);
						mniopenPV.setEnabled(false);
						mniopenCV.setEnabled(false);
						mnieditperc.setEnabled(false);
						mnieditamt.setEnabled(false);
					}

				} else if (column == 15) {

					System.out.println("Dumaan sa 15");
					System.out.println("status: " + status);

					if (status.equals("POSTED") || status.equals("INACTIVE") || status.equals("RELEASED")) {
						System.out.println("Dumaan sa false");
						menu2.show(ev.getComponent(), ev.getX(), ev.getY());
						mniopenRPLF.setEnabled(false);
						mniopenPV.setEnabled(false);
						mniopenCV.setEnabled(false);
						mnieditperc.setEnabled(false);
						mnieditamt.setEnabled(false);
						mnieditDate.setEnabled(false);
						mniaddremarks.setEnabled(false);
					} else {
						System.out.println("Dumaan sa true");
						menu2.show(ev.getComponent(), ev.getX(), ev.getY());
						mniopenRPLF.setEnabled(false);
						mniopenPV.setEnabled(false);
						mniopenCV.setEnabled(false);
						mnieditperc.setEnabled(false);
						mnieditamt.setEnabled(false);
						mnieditDate.setEnabled(false);
						mniaddremarks.setEnabled(true);
					}
				} else if (rplf_no.equals("") && billtype.equals("04") && status.equals("")) {

					menu2.show(ev.getComponent(), ev.getX(), ev.getY());
					mniopenRPLF.setEnabled(false);
					mniopenPV.setEnabled(false);
					mniopenCV.setEnabled(false);
					mnieditperc.setEnabled(true);
					mnieditamt.setEnabled(true);
					mnieditDate.setEnabled(false);
					mniaddremarks.setEnabled(true);
					mnipostajustment.setEnabled(false);

				} else if (rplf_no.equals("") && billtype.equals("04") && status.equals("For Approval")
						&& column == 14) {

					System.out.println("Post!!");
					menu2.show(ev.getComponent(), ev.getX(), ev.getY());
					mniopenRPLF.setEnabled(false);
					mniopenPV.setEnabled(false);
					mniopenCV.setEnabled(false);
					mnieditperc.setEnabled(false);
					mnieditamt.setEnabled(false);
					mnieditDate.setEnabled(false);
					mniaddremarks.setEnabled(false);
					mnipostajustment.setEnabled(true);
				} else // (column==0||column==1||column==10||column==11||column==12||column==13||column==14)
				{
					System.out.println("Else!!!");
					menu2.show(ev.getComponent(), ev.getX(), ev.getY());
					mniopenRPLF.setEnabled(false);
					mniopenPV.setEnabled(false);
					mniopenCV.setEnabled(false);
					mnieditperc.setEnabled(false);
					mnieditamt.setEnabled(false);
					mnieditDate.setEnabled(false);
					mniaddremarks.setEnabled(false);
				}
				if (backcharge_amt == 0) {
					mniviewBC_rplf.setEnabled(false);
				} else {
					mniviewBC_rplf.setEnabled(true);
				}
				if (status.equals("ACTIVE")) {

					if (status.equals("ACTIVE") && rplf_no.equals("") && billtype.equals("04")) {// Addedby Erick
																									// adjustment cannt
																									// be deleted if the
																									// status displayed
																									// is active
						mniviewDeleteBilling.setEnabled(false);
					} else {
						mniviewDeleteBilling.setEnabled(true);
					}

				} else {
					if (rplf_no.equals("") && billtype.equals("04") && status.equals("For Approval")) {
						mniviewDeleteBilling.setEnabled(true);
					} else {
						mniviewDeleteBilling.setEnabled(false);
					}

				}
			}
		}

		public void mouseReleased(MouseEvent ev) {
			if (ev.isPopupTrigger()) {
				int column = tblProgBillMain.getSelectedColumn();
				int row = tblProgBillMain.getSelectedRow();
				rplf_no = modelProgBillMain.getValueAt(row, 12).toString().trim();
				String status = modelProgBillMain.getValueAt(row, 14).toString().trim();
				String billtype = modelProgBillMain.getValueAt(row, 0).toString().trim();
				double backcharge_amt = Double.parseDouble(modelProgBillMain.getValueAt(row, 8).toString());
				if (column == 12) {
					menu2.show(ev.getComponent(), ev.getX(), ev.getY());
					mniopenRPLF.setEnabled(true);
					mniopenPV.setEnabled(true);
					mnieditperc.setEnabled(false);
					mnieditamt.setEnabled(false);
					mnieditDate.setEnabled(false);
					String cv_no = RequestForPayment.sql_getCVno(rplf_no, co_id).trim();
					if (!cv_no.equals("")) {
						mniopenCV.setEnabled(true);
					} else {
						mniopenCV.setEnabled(false);
						System.out.printf("CV_No : " + cv_no);
					}
				} else if (column == 1) {
					menu2.show(ev.getComponent(), ev.getX(), ev.getY());
					mniopenRPLF.setEnabled(false);
					mniopenPV.setEnabled(false);
					mniopenCV.setEnabled(false);
					mnieditperc.setEnabled(false);
					mnieditamt.setEnabled(false);
					mnieditDate.setEnabled(true);
				} else if (column == 2 && rplf_no.equals("") && billtype.equals("02")) {
					menu2.show(ev.getComponent(), ev.getX(), ev.getY());
					mniopenRPLF.setEnabled(false);
					mniopenPV.setEnabled(false);
					mniopenCV.setEnabled(false);
					mnieditperc.setEnabled(true);
					mnieditamt.setEnabled(false);
					mnieditDate.setEnabled(false);
				} else if (column == 3 || column == 4 || column == 5 || column == 6 || column == 7 || column == 8
						|| column == 9 || column == 10) {
					menu2.show(ev.getComponent(), ev.getX(), ev.getY());
					if (!rplf_no.equals("")) {
						mniopenRPLF.setEnabled(false);
						mniopenPV.setEnabled(false);
						mniopenCV.setEnabled(false);
						mnieditperc.setEnabled(false);
						mnieditamt.setEnabled(false);
						mnieditDate.setEnabled(false);
					} else {
						mniopenRPLF.setEnabled(false);
						mniopenPV.setEnabled(false);
						mniopenCV.setEnabled(false);
						mnieditperc.setEnabled(false);
						mnieditamt.setEnabled(true);
						mnieditDate.setEnabled(false);
					}
				} else if (column == 15) {

					System.out.println("Dumaan sa 15");
					System.out.println("status: " + status);

					if (status.equals("POSTED") || status.equals("INACTIVE") || status.equals("RELEASED")) {
						menu2.show(ev.getComponent(), ev.getX(), ev.getY());
						mniopenRPLF.setEnabled(false);
						mniopenPV.setEnabled(false);
						mniopenCV.setEnabled(false);
						mnieditperc.setEnabled(false);
						mnieditamt.setEnabled(false);
						mnieditDate.setEnabled(false);
						mniaddremarks.setEnabled(false);

					} else {
						menu2.show(ev.getComponent(), ev.getX(), ev.getY());
						mniopenRPLF.setEnabled(false);
						mniopenPV.setEnabled(false);
						mniopenCV.setEnabled(false);
						mnieditperc.setEnabled(false);
						mnieditamt.setEnabled(false);
						mnieditDate.setEnabled(false);
						mniaddremarks.setEnabled(true);
					}

				} else if (rplf_no.equals("") && billtype.equals("04") && status.equals("For Approval")
						&& column == 14) {

					System.out.println("Post release");
					menu2.show(ev.getComponent(), ev.getX(), ev.getY());
					mniopenRPLF.setEnabled(false);
					mniopenPV.setEnabled(false);
					mniopenCV.setEnabled(false);
					mnieditperc.setEnabled(false);
					mnieditamt.setEnabled(false);
					mnieditDate.setEnabled(false);
					mniaddremarks.setEnabled(false);
					mnipostajustment.setEnabled(true);
				} else // (column==0||column==1||column==10||column==11||column==12||column==13||column==14)
				{
					menu2.show(ev.getComponent(), ev.getX(), ev.getY());
					mniopenRPLF.setEnabled(false);
					mniopenPV.setEnabled(false);
					mniopenCV.setEnabled(false);
					mnieditperc.setEnabled(false);
					mnieditamt.setEnabled(false);
					mnieditDate.setEnabled(false);
					mniaddremarks.setEnabled(false);
					System.out.println("Mouse Released");
					// if (bc_amt.equals(0)){mniviewBC_rplf.setEnabled(false);}else
					// {mniviewBC_rplf.setEnabled(true);}
				}

				if (backcharge_amt == 0) {
					mniviewBC_rplf.setEnabled(false);
				} else {
					mniviewBC_rplf.setEnabled(true);
				}
				if (status.equals("ACTIVE")) {
					if (status.equals("ACTIVE") && rplf_no.equals("") && billtype.equals("04")) {// Addedby Erick
																									// adjustment cannt
																									// be deleted if the
																									// status displayed
																									// is active
						mniviewDeleteBilling.setEnabled(false);
					} else {
						mniviewDeleteBilling.setEnabled(true);
					}

				} else {
					if (rplf_no.equals("") && billtype.equals("04") && status.equals("For Approval")) {
						mniviewDeleteBilling.setEnabled(true);
					} else {
						mniviewDeleteBilling.setEnabled(false);
					}

				}

			}
		}
	}

	class PopupTriggerListener_panel extends MouseAdapter {
		public void mousePressed(MouseEvent ev) {
			if (ev.isPopupTrigger()) {
				menu.show(ev.getComponent(), ev.getX(), ev.getY());
			}
		}

		public void mouseReleased(MouseEvent ev) {
			if (ev.isPopupTrigger()) {
				menu.show(ev.getComponent(), ev.getX(), ev.getY());
			}
		}
	}

	public class PopupTriggerListener_panel3 extends MouseAdapter {

		public void mousePressed(MouseEvent ev) {
			if (ev.isPopupTrigger()) {
				menu.show(ev.getComponent(), ev.getX(), ev.getY());
			}
		}

		public void mouseReleased(MouseEvent ev) {
			if (ev.isPopupTrigger()) {
				menu.show(ev.getComponent(), ev.getX(), ev.getY());
			}
		}
	}

	private void refresh() {
		enable_fields(true);
		Object[] x = getSurety_NTP_no(lookupContractNo.getText().trim());
		// setContractDetails((String) x[1],(String) x[0]);
		refresh_fields();
		refresh_tablesMain();
		refresh_tables();
		enableButtons(false, false, false, true, true, false);
		JOptionPane.showMessageDialog(getContentPane(), "Contract details refreshed.", "Information",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void cancel() {
		enable_fields(false);
		refresh_fields();
		refresh_tablesMain();
		refresh_tables();

		new_price = null;
		dp_amount = null;
		wtax_rate = 0.00;
		row_num = 0;
		row_num_bc = 0;
		row_num_od = 0;
		next_billing_type = "";
		billed_ret_amt = 0.00;

		enableButtons(true, false, true, true, true, false);
	}

	private void addnew() {

		// if (checkTransDate()==false) --removed condition : see DCRF No. 600

		// {
		acct_id = "";
		billing_remarks = "";
		System.out.println("Company ID: " + lookupCompany.getValue());
		// modelProgBillMain.setEditable(true);
		modelBC.setEditable(true);
		modelOD.setEditable(true);

		String x = "";

		next_billing_type = getNextBillingType(txtNTP_no.getText().trim());
		selected_billing_type = (Integer) cmbBillingType.getSelectedIndex();

		Object[] contract = getContractDtls(ntp_no);

		Boolean taken_over_contract = (Boolean) contract[20]; // old contracts
		Boolean old_ntp = (Boolean) contract[25];// migrated contracts from itsreal

		// String contract_no = (String) contract[18];

		int c = tblProgBillMain.getSelectedColumn();
		int r = tblProgBillMain.getSelectedRow();
		System.out.println("old_ntp " + old_ntp);
		System.out.println("taken_over_contract: " + taken_over_contract);
		// for taken over contracts --DCRF #716
		if ((taken_over_contract).equals(true) || ntp_no.equals("000764") || old_ntp.equals(true)
				|| ntp_no.equals("002304") || ntp_no.equals("000311") // DCRF 2893
				|| ntp_no.equals("000327") // DCRF 2891
				|| ntp_no.equals("000798") //DCRF2918
				|| ntp_no.equals("000139") //DCRF2921
				|| ntp_no.equals("000572") //DCRF2921
				|| ntp_no.equals("001827") //DCRF2940
				|| ntp_no.equals("000272") //DCRF2940
				|| ntp_no.equals("000261") //DCRF2940
				|| ntp_no.equals("000263") //DCRF2940
				|| ntp_no.equals("000807") //DCRF2940
				|| ntp_no.equals("000535") //DCRF2940
				|| ntp_no.equals("000287") //DCRF2940
		// || ntp_no.equals("000772")
		) {// Edited by erick 2023/01/24 to manually select next billing type for
			// migrated contracts from itsreal
			System.out.println("Taken over-Add");
			pnlEditBType.setEnabled(true);
			int scanOption = JOptionPane.showOptionDialog(getContentPane(), pnlEditBType, "Select Billing Type",
					JOptionPane.CLOSED_OPTION, JOptionPane.CLOSED_OPTION, null, new Object[] {}, null);

			if (scanOption == JOptionPane.CLOSED_OPTION) {
				try {
					selected_billing_type = (Integer) cmbBillingType.getSelectedIndex();
					if ((selected_billing_type.equals(0))) {
						next_billing_type = "02";
						System.out.println("Next Billing Type is 02");
						System.out.printf("Display value of next billing type: %s%n", next_billing_type);
						modelProgBillMain.setValueAt(next_billing_type, r, c);
						getNextBillingType(ntp_no);

					} else if (selected_billing_type.equals(2)) {
						next_billing_type = "04";
						modelProgBillMain.setValueAt(next_billing_type, r, c);
					} else {
						next_billing_type = "03";
						System.out.println("Next Billing Type is 03");
						System.out.printf("Display value of next billing type: %s%n", next_billing_type);
						modelProgBillMain.setValueAt(next_billing_type, r, c);
						getNextBillingType(ntp_no);
					}
					;

				} catch (java.lang.ArrayIndexOutOfBoundsException e) {
				}
			}

		} else {

		}
		;

		if (row_num > 0) {
			x = next_billing_type;
			// System.out.println("Dumaan dito 0");

		} else {
			if (row_num == 0 && dp_amount == 0) {
				x = "02";
			} else {
				x = "01";
			}

		}

		/*
		 * if(lookupCompany.getValue().equals("05") &&
		 * (txtNTP_no.getText().equals("002261") ||
		 * txtNTP_no.getText().equals("002262"))) { next_billing_type = "03"; x = "03";
		 * }
		 * 
		 * if(lookupCompany.getValue().equals("01") &&
		 * txtNTP_no.getText().equals("002266")) { next_billing_type = "03"; x = "03"; }
		 * 
		 * if(lookupCompany.getValue().equals("02")) {
		 * if(txtNTP_no.getText().equals("002258") ||
		 * txtNTP_no.getText().equals("002259")) { next_billing_type = "03"; x = "03"; }
		 * }
		 */

		System.out.printf("Display value of row_num: %s%n", row_num);
		System.out.printf("Display value of x: %s%n", x);

		if (next_billing_type == null && dp_amount != 0 /* || ntp_no.equals("001948") */) {
			modelProgBillMain.addRow(new Object[] { "01", dateFormat.format(billing_dte), new BigDecimal(0.00),
					new BigDecimal(dp_amount), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
					new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(dp_amount), "", "",
					null, "", "" });
			next_billing_type = "01";

			computeBC_liquidation(0);
			// computeBillAmounts_fromBC();
			System.out.println("Dumaan dito 1");

		} else if (next_billing_type == null && dp_amount == 0) {
			modelProgBillMain.addRow(new Object[] { "02", dateFormat.format(billing_dte), null, new BigDecimal(0.00),
					new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
					new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), "", "", null, "", "" });
			next_billing_type = "02";
			System.out.println("Dumaan dito 2");

		} else if (next_billing_type.equals("02")) {

			modelProgBillMain.addRow(
					new Object[] { x, dateFormat.format(billing_dte), new BigDecimal(0.00), new BigDecimal(0.00),
							new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
							new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), "", "", null, "", "" });
			next_billing_type = "02";
			System.out.println("Dumaan dito 3");
			System.out.println("Dumaan dito sa 02");

		} else if (next_billing_type.equals("03")) {
			Double ret_amt = Double.parseDouble(modelProgBillTotal.getValueAt(0, 7).toString());
			modelProgBillMain.addRow(new Object[] { x, dateFormat.format(billing_dte), new BigDecimal(0.00),
					new BigDecimal(ret_amt), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
					new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(ret_amt), "", "",
					null, "", "" });
			totalProgBill(modelProgBillMain, modelProgBillTotal);
			next_billing_type = "03";

			pnlEditBType.setEnabled(false);
			cmbBillingType.setEnabled(false);
			enableButtons(false, true, true, true, true, false);
			((DefaultListModel) rowHeaderProgBillMain.getModel()).addElement(modelProgBillMain.getRowCount());
			createOtherDeductionTable(modelOD, rowHeaderOD, modelOD_total);
			System.out.println("Dumaan dito 4");

		} else if (next_billing_type.equals("04")) {// Added by erick DCRF 2749
			modelProgBillMain.addRow(
					new Object[] { x, dateFormat.format(billing_dte), new BigDecimal(0.00), new BigDecimal(0.00),
							new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
							new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), "", "", null, "", "" });
			totalProgBill(modelProgBillMain, modelProgBillTotal);
			next_billing_type = "03";

			pnlEditBType.setEnabled(false);
			cmbBillingType.setEnabled(false);
			enableButtons(false, true, true, true, true, false);
			((DefaultListModel) rowHeaderProgBillMain.getModel()).addElement(modelProgBillMain.getRowCount());
			System.out.println("Dumaan dito 5");

		} else {
			pnlEditBType.setEnabled(true);
			cmbBillingType.setEnabled(true);
			enableButtons(false, true, true, true, true, false);
		}
		;

		enableButtons(false, true, true, true, true, false);
		((DefaultListModel) rowHeaderProgBillMain.getModel()).addElement(modelProgBillMain.getRowCount());
		createOtherDeductionTable(modelOD, rowHeaderOD, modelOD_total);
		// computeBC_liquidation();

		/*
		 * }
		 * 
		 * 
		 * else { JOptionPane.showMessageDialog(getContentPane()
		 * ,"A transaction already exists for the given date.","Warning",JOptionPane.
		 * WARNING_MESSAGE); }
		 */
		System.out.println("***end");
	}

	private void save() {
		// Commented by Erick 2021-07-01 to prevent from creating progress billing when
		// adding a deductives
		/*
		 * pgUpdate db = new pgUpdate(); //insertNewContractPrice(ntp_no, db);
		 * //deduction_amt =
		 * (Double.valueOf(txtDAmount.getText().trim().replace(",","")).doubleValue());
		 * System.out.println(""); System.out.println("deduction_amt: "+deduction_amt);
		 * db.commit();
		 */

		if (checkNetAmount() == false) {
			JOptionPane.showMessageDialog(getContentPane(), "Net amount must be greater than zero.", "Warning",
					JOptionPane.WARNING_MESSAGE);
		} else {

			int rw = tblProgBillMain.getModel().getRowCount();
			double net_amt = Double.parseDouble(modelProgBillMain.getValueAt(rw - 1, 10).toString());

			if (net_amt == 0) {
				if (JOptionPane.showConfirmDialog(getContentPane(), "Net amount is zero, would you like to proceed?",
						"Zero Net Amount", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {

					if (JOptionPane.showConfirmDialog(getContentPane(), "Are all entries correct?", "Save",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						executeSave();
					}
				}
			}

			else {
				if (JOptionPane.showConfirmDialog(getContentPane(), "Are all entries correct?", "Confirmation",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

					executeSave();
				}
			}

		}
	}

	private void preview() {

		String criteria = "";
		String reportTitle = String.format("%s (%s)", title.replace(" Report", ""), criteria.toUpperCase());

		Map<String, Object> mapParameters = new HashMap<String, Object>();
		mapParameters.put("co_id", co_id);
		mapParameters.put("proj_id", txtProject.getText());
		mapParameters.put("company", company);
		mapParameters.put("logo", this.getClass().getClassLoader().getResourceAsStream("Images/" + company_logo));
		mapParameters.put("prepared_by", UserInfo.Alias);
		mapParameters.put("prepared_name", UserInfo.FullName);
		mapParameters.put("ntp_no", ntp_no);

		FncReport.generateReport("/Reports/rptContractorBillingLedger.jasper", reportTitle, "", mapParameters);
	}

	private void initialize_comp() {

		co_id = "02";
		company = "CENQHOMES DEVELOPMENT CORPORATION";
		tagCompany.setTag(company);
		company_logo = sql_getCompanyLogo();

		KEYBOARD_MANAGER.focusNextComponent();

		lblContractNo.setEnabled(true);
		lookupContractNo.setEnabled(true);
		tagContractNo.setEnabled(true);
		lookupContractNo.setLookupSQL(getLookupContractNo(co_id));

		lookupCompany.setValue(co_id);
	}

	private void executeSave() {

		pgUpdate db = new pgUpdate();

		int rw = tblProgBillMain.getModel().getRowCount() - 1;
		Double gross_amount =Double.parseDouble(modelProgBillMain.getValueAt(rw, 3).toString());
		

		if (modelProgBillMain.getValueAt(rw, 0) == "04") {
			rplf_no = "";
		} else {
			rplf_no = sql_getNextRPLFno();
		}

		/*
		 * if (rplf_no==null||rplf_no.equals("null")) { rplf_no="000000001"; } else
		 * if(modelProgBillMain.getValueAt(rw, 0) == "04") { //rplf_no = ""; COMMENTED
		 * BY LESTER TO AVOID EMPTY STRING FOR RPLF CREATION 2023-11-28 } else { }
		 */
		System.out.println("Billing type" + modelProgBillMain.getValueAt(rw, 0));

		if (modelProgBillMain.getValueAt(rw, 0).equals("04")) { // MOVED BY LESTER TO EXECUTE FIRST IF CONDITION IS
																// SATISFIED 2023-11-28
			System.out.println("Pasok sa 04*******");
			
			if (gross_amount == 0.00) {
				JOptionPane.showMessageDialog(getTopLevelAncestor(), "Adjustment with zero gross amount is not allowed to save.", "Saving", JOptionPane.INFORMATION_MESSAGE);
			}else {
				setBillingAmountParticulars();
				insertBilling(db, rplf_no);
				insertAudit_trail("(Contractors Billing 04)", null, db);
				db.commit();
			}
		} else {
			if (next_billing_type.equals("02")) {
				setBillingAmountParticulars();
				// insertNewContractPrice(ntp_no, db);
				insertBilling(db, rplf_no);
				insertRPLF_header(db);
				insertRPLF_detail(db);
				insertAudit_trail("Add-RPLF(Contractors Billing)", rplf_no, db);
				// insertPV_header(db);
				// insertPV_detail_billing(db);
				if (bc_amt == 0.00) {
				} else {
					insertBackchargesLiq(db);
				}
				insertOtherDeductions(db);
				db.commit();
				JOptionPane.showMessageDialog(getContentPane(), "New progress billing details saved.", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			} else if (next_billing_type.equals("01")) {
				jv_no = "";
				setBillingAmountParticulars();
				// insertNewContractPrice(ntp_no, db);
				insertBilling(db, rplf_no);
				insertRPLF_header(db);
				insertRPLF_detail(db);
				insertAudit_trail("Add-RPLF(Contractors Billing)", rplf_no, db);
				// insertPV_header(db);
				// insertPV_detail_dp(db);
				if (bc_amt == 0.00) {
				} else {
					insertBackchargesLiq(db);
				}
				insertOtherDeductions(db);
				db.commit();
				JOptionPane.showMessageDialog(getContentPane(), "Contractor's DP details saved.", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			} else if (next_billing_type.equals("03")) {
				jv_no = "";
				setBillingAmountParticulars();
				insertBilling(db, rplf_no);
				insertRPLF_header(db);
				insertRPLF_detail(db);
				insertAudit_trail("Add-RPLF(Contractors Billing)", rplf_no, db);
				// insertPV_header(db);
				// insertPV_detail_ret(db);
				if (bc_amt == 0.00) {
				} else {
					insertBackchargesLiq(db);
				}
				insertOtherDeductions(db);
				db.commit();
				JOptionPane.showMessageDialog(getContentPane(), "Contractor's retentions payment details saved.",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		// refresh data
		Object[] x = getSurety_NTP_no(lookupContractNo.getText().trim());
		setContractDetails((String) x[1], (String) x[0]);
		enableButtons(true, false, true, true, true, false);

	}

	private void execute_post_adjustment(Integer rec_id, String ntp_no, String billing_type, String co_id) {
		pgUpdate db = new pgUpdate();

		String strsql = "update co_billing_detail set status_id = 'A' where ntp_no = '" + ntp_no
				+ "' and billing_type = '" + billing_type + "'  \n" + "and rec_id = " + rec_id
				+ " and status_id = '' and billing_no = '0000000000' and rplf_no = '' and co_id = '" + co_id + "' ";

		System.out.println("execute_post_adjustment= " + strsql);
		db.executeUpdate(strsql, false);
		db.commit();

	}

	private void executeDelete() {

		pgUpdate db = new pgUpdate();

		int rw = tblProgBillMain.getSelectedRow();
		String drf = modelProgBillMain.getValueAt(rw, 12).toString();
		String bill = modelProgBillMain.getValueAt(rw, 11).toString(); // for inactive_bc and billing
		Double backcharge_amt = Double.parseDouble(modelProgBillMain.getValueAt(rw, 8).toString());
		String rec_id = modelProgBillMain.getValueAt(rw, 16).toString();
		String status_id = modelProgBillMain.getValueAt(rw, 14).toString();

		if (bill.equals("0000000000") && drf.equals("") && status_id.equals("For Approval")) {
			System.out.println("executeDelete adjustment");
			inactivateBilling(bill, db, rec_id);
			insertAudit_trail_billing("Delete Contractors Billing ", db, bill, rec_id);
		} else {
			inactivateRPLF_header(drf, db);
			insertAudit_trail_rplf_header("Delete RPLF_request(Contractors Billing)", drf, db);
			inactivatePV_header(drf, db);
			insertAudit_trail_rplf_header("Delete PV(Contractors Billing)", drf, db);
			inactivateBilling(bill, db, rec_id);
			insertAudit_trail_billing("Delete Contractors Billing ", db, bill, rec_id);
			if (backcharge_amt == 0.00) {
			} else {
				inactive_bc(bill, db);
			}
		}

		db.commit();
		JOptionPane.showMessageDialog(getContentPane(), "Transaction deleted.", "Information",
				JOptionPane.INFORMATION_MESSAGE);

		// refresh data
		Object[] x = getSurety_NTP_no(lookupContractNo.getText().trim());
		setContractDetails((String) x[1], (String) x[0]);
		enableButtons(true, false, true, true, true, false);

	}

	private void viewBackchargeRPLF() {

		int row = tblProgBillMain.getSelectedRow();
		String row_bill_no = modelProgBillMain.getValueAt(row, 11).toString().trim();

		displayBackChargeRPLF(modelView_BCrplf, rowHeaderBC_tbl, row_bill_no);

		int scanOption = JOptionPane.showOptionDialog(getContentPane(), pnlBackChargeTbl, "Backcharge RPLF",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, new Object[] {}, null);

		if (scanOption == JOptionPane.CLOSED_OPTION) {

		} // CLOSED_OPTION

	}

	// select, lookup and get statements
	/*
	 * private Boolean getCompletionStatus(String ntp_no) {
	 * 
	 * String strSQL =
	 * "select a.rec_id from ( select * from co_billing_detail where billing_type = '03' ) a "
	 * + "where trim(ntp_no) = '"+ntp_no+"' ";
	 * 
	 * pgSelect db = new pgSelect(); db.select(strSQL);
	 * 
	 * if(db.isNotNull()){ return true; }else{ return false; } }
	 */

	private String getLookupContractNo(String co_id) {
		return

		"select \r\n" + "a.contract_no as Contract_No, \r\n" + "a.ntp_no as NTP_No, \r\n"
				+ "a.entity_id as Contractor_ID, \r\n" + "b.entity_name as Contractor_Name, \r\n"
				+ "c.proj_alias as Project,"
				+ "(case when a.with_surety_bond = true then 'YES' else 'NO' end) as with_surety_bond  \r\n" + " \r\n" +

				"from co_ntp_header a\r\n" +

				"left join rf_entity b on a.entity_id = b.entity_id\r\n" +

				"left join mf_project c on a.proj_id = c.proj_id\r\n" +

				"where a.co_id = '" + co_id + "' \r\n" + // and a.status_id = 'A' (removed - see DCRF No. 565)

				"order by ntp_no::integer desc \r\n";

	}

	private String getChartofAccount() {

		String sql = "select " + "acct_id as \"Acct. ID\", " + "trim(acct_name) as \"Acct. Name\", "
				+ "bs_is as \"Balance\" " + "from \n" + "mf_boi_chart_of_accounts "
				+ "where status_id = 'A' and w_subacct is null ";
		return sql;

	}

	private String getODcontractno() {

		String sql = "select  \n" + "a.contract_no as Contract_No,  \n" + "a.ntp_no as NTP_No,  \n"
				+ "a.entity_id as Contractor_ID,  \n" + "b.entity_name as Contractor_Name,  \n"
				+ "c.proj_alias as Project,\n" + "a.status_id  \n" + "from co_ntp_header a \n"
				+ "left join rf_entity b on a.entity_id = b.entity_id \n"
				+ "left join mf_project c on a.proj_id = c.proj_id \n" + "where a.co_id = '02'   \n"
				+ "order by ntp_no::integer desc   ";
		return sql;
	}

	private static String getType() {
		return "select '01' as Type_ID,'DP Recoupment' as Type_Description union all\n"
				+ "select '02' as Type_ID, 'Retention Payable- With amount retained as budget for possible repairs' as Type_Description  union all\n"
				+ "select '03' as Type_ID, 'Retention Payable- DP recoupment was deducted to Retention Payable' as Type_Description union all\n"
				+ "select '04' as Type_ID, 'Utilities Backcharges' as Type_Description ";
	}

	private String getBillingType(String ntp_no) {

		return

		"select \n" + "trim(billingtype_id) as \"Billing ID\", \n"
				+ "trim(billingtype_desc) as \"Billing Description\", \n"
				+ "trim(billingtype_alias) as \"Billing Alias\" \n" + "from mf_billing_type ";

	}

	private String getNextBillingType(String ntp_no) {

		String strSQL =

				"select trim(billingtype_id) \n" + "from mf_billing_type \n" + "where billingtype_id in \n" + "	( \n"
						+ "	select \n" + "	case when billing_type='01' \n" + "	then '02' \n"
						+ "	when billing_type='02' and accomp_pctg <= 100 and ntp_no in('000219','000210','000124') then '03'\n"
						+ // DCRF # 1929 //uncomment this portion and set ntp_no to process retention
							// billing
						"	when billing_type='02' and accomp_pctg !=100 then '02' \n" + // comment this portion to
																							// process retention billing
																							// even if it is not 100%
						"	when billing_type='02' and accomp_pctg = 100 then '03' \n"
						+ "   when billing_type = '03' and accomp_pctg =  0.000000  then '03' end \n"
						+ "	from co_billing_detail where ntp_no='" + ntp_no
						+ "' and status_id != 'I' order by date_created desc limit 1 \n" + "	) \n";

		System.out.println("getNextBillingType= " + strSQL);
		pgSelect db = new pgSelect();
		db.select(strSQL);

		FncSystem.out("Display SQL for Next Billing Type for Regular Contracts ", strSQL);

		if (db.isNotNull()) {
			return (String) db.getResult()[0][0];
		} else {
			return null;
		}

	}

	private static Double getWtaxRate(String entity_type_id) {

		String strSQL =

				"select wtax_rate \r\n" + "from rf_withholding_tax a\r\n"
						+ "left join mf_entity_type b on a.wtax_id = b.wtax_id\r\n" + "where b.entity_type_id = '"
						+ entity_type_id + "'  \n";

		pgSelect db = new pgSelect();
		db.select(strSQL);

		if (db.isNotNull()) {
			return (Double) db.getResult()[0][0];
		} else {
			return null;

		}
	}

	private static Object[] getContractDtls(String ntp_no) {

		deduction_amt = (Double.valueOf(txtDAmount.getText().trim().replace(",", "")).doubleValue());

		String strSQL = "select \r\n" + "a.ntp_no, \r\n" + "a.proj_id, \r\n" + "trim(b.proj_name), \r\n"
				+ "a.entity_id, \r\n" + "trim(c.entity_name),\r\n" + "a.orig_contract_price,\r\n"
				+ "(case when a.new_contract_price is null then (a.orig_contract_price - '" + deduction_amt
				+ "') else a.new_contract_price end)as new_cont_price, " + "a.dp_percentage,\r\n" + "trim(d.bond_no),"
				+ "trim(f.entity_type_desc)," + "f.entity_type_id,"
				+ "(case when c.vat_registered = true then 'VAT' else 'Non-VAT' end)as vat,"
				+ "(case when c.vat_registered = true then 0.02 else 0.00 end)as vat_rate," + "a.status_id, "
				+ "h.entity_name, "
				+ "(case when a.deleted_date is null then a.date_created else a.deleted_date end)::date as deleted_date, "
				+ "i.takenover_ntpno, " + // takenover_ntp --16
				"coalesce(i.is_takeover_ntp,false) as is_takeover_ntp, " + // 17
				"i.contract_no, " + // 18
				"j.contractor," + // 19
				"(CASE WHEN k.takenover_ntpno is not null then true else false end) as old_contract, " + // 20
				" k.contract_no, " + // 21
				" k.ntp_no," + // 22
				" l.contractor, " + // 23
				"m.deduction_amt, case when a.old_ntp_2_1 is not null then true else false end as old_ntp_2_1 \r\n" + // 24
				" \n" + " \r\n" + "\r\n" + "from (select * from co_ntp_header where trim(ntp_no)='" + ntp_no
				+ "') a\r\n" + // and status_id = 'A' (see DCRF No. 565)
				"\r\n" + "left join mf_project b on a.proj_id = b.proj_id \r\n" + "\r\n"
				+ "left join rf_entity c on a.entity_id = c.entity_id \r\n" + "\r\n"
				+ "left join (select * from co_surety_bond where status_id = 'A' order by date_created desc)  d on trim(a.contract_no)=trim(d.contract_no) \n"
				+ "\r\n"
				+ "left join (select * from rf_entity_assigned_type where entity_type_id != '25' and status_id != 'I' ) e on a.entity_id = e.entity_id \n"
				+ "left join mf_entity_type f on e.entity_type_id = f.entity_type_id \r\n"
				+ "left join em_employee g on (case when a.deleted_by is null then a.created_by else a.deleted_by end) = g.emp_code \n "
				+ "left join rf_entity h on g.entity_id = h.entity_id \r\n"
				+ "left join (select * from co_ntp_header where status_id = 'A' and trim(ntp_no)='" + ntp_no
				+ "') i on a.entity_id = i.entity_id \n "
				+ "left join (select * from co_ntp_accomplishment where status_id = 'A') j on j.ntp_no = i.takenover_ntpno  \r\n"
				+ "left join co_ntp_header k on k.takenover_ntpno = a.ntp_no and a.ntp_type_id = k.ntp_type_id \r\n"
				+ "left join (select ntp_no, contractor from co_ntp_accomplishment ) l on l.ntp_no = k.ntp_no \r\n"
				+ "left join (select ntp_no, deduction_amt from co_billing_detail) m on m.ntp_no = a.ntp_no \r\n"
				+ "where a.ntp_no = '" + ntp_no + "' limit 1";

		pgSelect db = new pgSelect();
		db.select(strSQL);

		FncSystem.out("Display SQL for Contract Details", strSQL);

		if (db.isNotNull()) {
			return db.getResult()[0];
		} else {
			return null;
		}
	}

	private Object[] getSurety_NTP_no(String contract_no) {

		String strSQL = "select a.ntp_no, " + "coalesce(b.bond_no,'')  " +

				"from (select * from co_ntp_header where trim(contract_no)='" + contract_no + "') a\r\n" + "\r\n"
				+ "left join co_surety_bond b on trim(a.contract_no)=trim(b.contract_no) \n";

		pgSelect db = new pgSelect();
		db.select(strSQL);

		if (db.isNotNull()) {
			return db.getResult()[0];
		} else {
			return null;
		}
	}

	private Integer sql_getProgBillNo() {

		Integer next_no = 0;
		String SQL = "select nextval('tf_co_prog_billing_rec_id') ";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			next_no = (Integer) Integer.parseInt(db.getResult()[0][0].toString());
		} else {
			next_no = null;
		}

		return next_no;
	}

	private String sql_getNextRPLFno() {// EDITED BY JED 2021-05-17 : CHANGE THE GENERATION OF RPLF INSIDE THE FUNCTION

		String rplf = "";
		// String SQL =
		// "select trim(to_char(max(coalesce(rplf_no::int,0))+1,'000000000')) from
		// rf_request_header where co_id = '"+co_id+"' " ;
		String SQL = "select * from fn_get_rplf_no('" + lookupCompany.getValue() + "')";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if ((String) db.getResult()[0][0] == null || db.getResult()[0][0].equals("null")) {
				rplf = "000000001";
			} else {
				rplf = (String) db.getResult()[0][0];
			}
		} else {
			rplf = "000000001";
		}

		return rplf;
	}

	private String sql_getAcctID() {
		String acct_id = "";

		if (next_billing_type.equals("01")) {
			acct_id = "01-02-07-001"; // Advances to Contractors - Down Payment
		} else if (next_billing_type.equals("03")) {
			acct_id = "03-01-13-000"; // Retentions Payable
		} else {
			String SQL = "select b.acct_id from " + "(select * from co_ntp_header where trim(ntp_no) = '"
					+ txtNTP_no.getText().trim() + "' ) a \n" + "left join mf_ntp_type b on a.ntp_type_id=b.type_id   ";

			pgSelect db = new pgSelect();
			db.select(SQL);

			if (db.isNotNull()) {
				acct_id = (String) db.getResult()[0][0];
			} else {
				acct_id = null;
			}
		}
		return acct_id;
	}

	/*
	 * private Object [] getProjectDtls(String proj_id) {
	 * 
	 * String strSQL = "select a.vatable,   " +
	 * 
	 * "from (select * from mf_project where trim(proj_id)='"+proj_id+"') a \n" ;
	 * 
	 * 
	 * pgSelect db = new pgSelect(); db.select(strSQL);
	 * 
	 * if(db.isNotNull()){ return db.getResult()[0]; }else{ return null; } }
	 */

	private Integer getBillingNo() {

		Integer x;

		String SQL = "select count(rec_id)::int + 1 as count from co_billing_detail " + "where ntp_no = '"
				+ txtNTP_no.getText() + "' " + "and billing_type = '02' \r\n and status_id != 'I'";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			x = (Integer) db.getResult()[0][0];
		} else {
			x = 1;
		}

		return x;

	}

	private static Boolean isRetentionPaid() {

		Boolean retention_pd = false;

		String SQL = "select count(rec_id) as count from co_billing_detail " + "where ntp_no = '" + txtNTP_no.getText()
				+ "' " + "and billing_type = '03' \r\n" + "and status_id != 'I'  \n\n";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if ((Long) db.getResult()[0][0] > 0) {
				retention_pd = true;
			} else if ((Long) db.getResult()[0][0] == 0) {
				retention_pd = false;
			} else {
				retention_pd = false;
			}
		} else {
			retention_pd = false;
		}

		return retention_pd;

	}

	/*
	 * private String getJVno(Integer fiscal_yr, String period_id, Integer
	 * next_srlno){
	 * 
	 * String SQL = "select ('"+fiscal_yr+"'||'"+period_id+"'||trim(to_char("+
	 * next_srlno+",'0000'))) as next_jvno " ;
	 * 
	 * pgSelect db = new pgSelect(); db.select(SQL);
	 * 
	 * if(db.isNotNull()){ jv_no = (String) db.getResult()[0][0]; }else{ jv_no =
	 * null; }
	 * 
	 * return jv_no;
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

	private String getReferenceNo() {// used

		String sql = "\n"
				+ "select * from (select distinct on (jv_no, co_id) 'JV' as Doc, jv_no, tran_amt  from rf_jv_detail a  \n"
				+ "	where acct_id in ('01-02-07-003') \n" + "	and bal_side = 'D' \n" + "	and status_id != 'I'\n"
				+ "	and co_id = '" + co_id + "'" + "\n" + "union \n" + "\n"
				+ "select distinct on (pv_no, co_id) 'PV' as Doc, pv_no, tran_amt  from rf_pv_detail a  \n"
				+ "	where acct_id in ('01-02-07-003') \n" + "	and bal_side = 'D' \n" + "	and status_id != 'I'"
				+ "	and co_id = '" + co_id + "' ) a " + "   order by doc ";

		return sql;

	}

	private static Double getPrevAccomp() {

		String strSQL =

				"select max(accomp_pctg)::text from co_billing_detail where ntp_no= '" + ntp_no
						+ "' and status_id != 'I' group by ntp_no";

		pgSelect db = new pgSelect();
		db.select(strSQL);

		System.out.printf("SQL #1: %s", strSQL);
		if (db.isNotNull()) {
			return Double.valueOf(db.getResult()[0][0].toString()).doubleValue();
		} else {
			return 0.00;

		}

	}

	private static String getSubProject(String projID) {
		String strSQL = null;

		if (checkNTP_type()) {// Added by Erick 2021/08/31 to get sub_proj_id of land dev and facilities
			strSQL = "select sub_proj_id from mf_sub_project \n" + "where phase in (select phase from mf_sub_project\n"
					+ "						where proj_id = '" + txtProject.getText() + "'\n"
					+ "						and sub_proj_alias = (select case when split_part(contract_no,'-',1) = 'ER'  \n"
					+ "												then split_part(contract_no,'-',3) || '-' || split_part(contract_no,'-',4) \n"
					+ "													else  split_part(contract_no,'-',3) end \n"
					+ "												from co_ntp_header \n"
					+ "												where contract_no = '" + lookupContractNo.getValue()
					+ "') AND status_id = 'A')\n" + "and proj_id = '" + projID + "' AND status_id = 'A'";// ADDED STATUS
																											// ID BY
																											// LESTER
																											// DCRF 2718

		} else {
			strSQL =

					"select sub_proj_id from mf_sub_project where \n"
							+ "	phase in (select distinct on (phase) phase from mf_unit_info "
							+ "		where pbl_id in (select pbl_id from co_ntp_detail where ntp_no = '" + ntp_no
							+ "'))\n" + "	and proj_id = '" + projID + "' and status_id = 'A'\n";// ADDED STATUS ID BY
																									// LESTER DCRF 2718
		}

		pgSelect db = new pgSelect();
		db.select(strSQL);

		if (db.isNotNull()) {
			return (String) db.getResult()[0][0];
		} else {
			return "";

		}

	}

	public static Boolean checkNTP_type() {

		Boolean LD = false;

		String SQL = "select ntp_type_id \n" + "from co_ntp_header \n" + "where ntp_no = '" + txtNTP_no.getText()
				+ "' \n" + "and co_id = '" + lookupCompany.getValue() + "' \n" + "and status_id = 'A'";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if (db.getResult()[0][0].equals("01")) {
				LD = true;
			} // LAND DEVELOPMENT //Requested by: annalyn of FAD
			else if (db.getResult()[0][0].equals("03")) {
				LD = true;
			} // REPAIR //Requested by: annalyn of FAD
			else if (db.getResult()[0][0].equals("04")) {
				LD = true;
			} // FACILITIES//Requested by: annalyn of FAD
			else if (db.getResult()[0][0].equals("06")) {
				LD = true;
			} // MERALCO FEES - MIS //Requested by: annalyn of FAD
			else {
				LD = false;
			}
		}
		return LD;
	}

	// table operations
	public static void totalBC(DefaultTableModel modelMain, DefaultTableModel modelTotal) {

		FncTables.clearTable(modelTotal);// Code to clear modelMain.

		BigDecimal bc_amt = new BigDecimal(0.00);
		BigDecimal liq_amt = new BigDecimal(0.00);
		BigDecimal for_liq = new BigDecimal(0.00);
		BigDecimal ob_amt = new BigDecimal(0.00);

		for (int x = 0; x < modelMain.getRowCount(); x++) {

			try {
				bc_amt = bc_amt.add(((BigDecimal) modelMain.getValueAt(x, 2)));
			} catch (NullPointerException e) {
				bc_amt = bc_amt.add(new BigDecimal(0.00));
			}

			try {
				liq_amt = liq_amt.add(((BigDecimal) modelMain.getValueAt(x, 3)));
			} catch (NullPointerException e) {
				liq_amt = liq_amt.add(new BigDecimal(0.00));
			}

			try {
				for_liq = for_liq.add(((BigDecimal) modelMain.getValueAt(x, 4)));
			} catch (NullPointerException e) {
				for_liq = for_liq.add(new BigDecimal(0.00));
			}

			try {
				ob_amt = ob_amt.add(((BigDecimal) modelMain.getValueAt(x, 5)));
			} catch (NullPointerException e) {
				ob_amt = ob_amt.add(new BigDecimal(0.00));
			}

		}

		modelTotal.addRow(new Object[] { null, "Total", bc_amt, liq_amt, for_liq, ob_amt, null, null });
	}

	public static void totalOD(DefaultTableModel modelMain, DefaultTableModel modelTotal) {

		FncTables.clearTable(modelTotal);// Code to clear modelMain.

		BigDecimal for_liq = new BigDecimal(0.00);

		for (int x = 0; x < modelMain.getRowCount(); x++) {

			try {
				for_liq = for_liq.add((BigDecimal) (modelMain.getValueAt(x, 3)));
			} catch (NullPointerException e) {
				for_liq = for_liq.add(new BigDecimal(0.00));
			}

		}

		modelTotal.addRow(new Object[] { null, "Total", null, for_liq, null, null });
	}

	public static void totalProgBill(DefaultTableModel modelMain, DefaultTableModel modelTotal) {
		System.out.println("totalProgBill");

		BigDecimal gross_amt = new BigDecimal(0.00);
		BigDecimal vat_amt = new BigDecimal(0.00);
		BigDecimal wtax_amt = new BigDecimal(0.00);
		BigDecimal dp_recoup = new BigDecimal(0.00);
		BigDecimal ret_amt = new BigDecimal(0.00);
		BigDecimal bc_liq = new BigDecimal(0.00);
		BigDecimal other_liq = new BigDecimal(0.00);
		BigDecimal net_amt = new BigDecimal(0.00);
		BigDecimal gross_amt_bal = new BigDecimal((new_price) * -1);
		BigDecimal dp = new BigDecimal(dp_amount * -1);
		Double billed_ret_amt_tot = 0.00;
		// String billing_status = "";
		System.out.println("gross_amt_bal1: " + gross_amt_bal);
		FncTables.clearTable(modelTotal);// Code to clear modelMain.

		for (int x = 0; x < modelMain.getRowCount(); x++) {
			System.out.println("");
			System.out.println("x = " + x);

			if (modelMain.getValueAt(x, 12) == null
					&& !modelMain.getValueAt(x, 15).toString().equals("Migrated from itsreal")) {

				System.out.println("The RPLF No. is " + modelMain.getValueAt(x, 12));
				String remarks = modelMain.getValueAt(x, 15).toString();

			} else {

				System.out.println("Statusss: " + modelMain.getValueAt(x, 14));
				if (is_reverse(lookupCompany.getValue(), (String) modelMain.getValueAt(x, 12))
						|| modelMain.getValueAt(x, 14).toString().equals("INACTIVE")) {
				} // Added by Erick 2022-10-18 DCRF 2134 To exclude reverse rplf and inactive in
					// the computation of releases. //comment muna para di bumagal 2022-01-18

				else { // comment muna para di bumagal 2022-01-18

					if ((modelMain.getValueAt(x, 0).toString().equals("02")
							|| modelMain.getValueAt(x, 0).toString().equals("01")
							|| modelMain.getValueAt(x, 0).toString().equals("03")
							|| modelMain.getValueAt(x, 0).toString().equals("04")
							|| modelMain.getValueAt(x, 0).toString().equals("")// ADDED BY ERICK DATE 2021-05-11 DCRF NO
																				// 1622
							|| modelMain.getValueAt(x, 15).toString().equals("Migrated from itsreal"))
							&& !modelMain.getValueAt(x, 14).toString().equals("INACTIVE")
							&& !modelMain.getValueAt(x, 14).toString().equals("For Approval")) {

						try {

							gross_amt = gross_amt.add(((BigDecimal) modelMain.getValueAt(x, 3)));

							if (!modelMain.getValueAt(x, 0).toString().equals("01")
									&& !modelMain.getValueAt(x, 0).toString().equals("03")) { // added by Erick Bituen
																								// dated 2021-02-01 as
																								// per request by Maam
																								// rochelle of FAD
								if (lookupContractNo.getValue().equals("EX-2014-COMFAC-013CF")) { // Added by Erick DCRF
																									// 2697
									gross_amt_bal = new BigDecimal(165000.00);
								} else {
									gross_amt_bal = gross_amt_bal.add(((BigDecimal) modelMain.getValueAt(x, 3)));
								}

							}

						} catch (NullPointerException e) {
							gross_amt = gross_amt.add(new BigDecimal(0.00));
						}

						try {

							vat_amt = vat_amt.add(((BigDecimal) modelMain.getValueAt(x, 4)));
						} catch (NullPointerException e) {
							vat_amt = vat_amt.add(new BigDecimal(0.00));
						}

						try {
							wtax_amt = wtax_amt.add(((BigDecimal) modelMain.getValueAt(x, 5)));

						} catch (NullPointerException e) {
							wtax_amt = wtax_amt.add(new BigDecimal(0.00));
						}

						try {
							System.out.println("DP_RECOUP: " + (BigDecimal) modelMain.getValueAt(x, 6));
							dp_recoup = dp_recoup.add(((BigDecimal) modelMain.getValueAt(x, 6)));

							if (dp_amount == 0) {
							} else {
								dp = dp.add(((BigDecimal) modelMain.getValueAt(x, 6)));
							}
						} catch (NullPointerException e) {

							dp_recoup = dp_recoup.add(new BigDecimal(0.00));
						}

						try {
							ret_amt = ret_amt.add(((BigDecimal) modelMain.getValueAt(x, 7)));
						} catch (NullPointerException e) {
							ret_amt = ret_amt.add(new BigDecimal(0.00));
						}

						try {
							bc_liq = bc_liq.add(((BigDecimal) modelMain.getValueAt(x, 8)));
						} catch (NullPointerException e) {
							bc_liq = bc_liq.add(new BigDecimal(0.00));
						}

						try {
							other_liq = other_liq.add(((BigDecimal) modelMain.getValueAt(x, 9)));
						} catch (NullPointerException e) {
							other_liq = other_liq.add(new BigDecimal(0.00));
						}
						try {
							net_amt = net_amt.add(((BigDecimal) modelMain.getValueAt(x, 10)));
						} catch (NullPointerException e) {
							net_amt = net_amt.add(new BigDecimal(0.00));
						}

						System.out.println("vat_amt_total : " + vat_amt);
					}
					if (modelMain.getValueAt(x, 0).toString().equals("03")
							&& !modelMain.getValueAt(x, 14).toString().equals("INACTIVE")) {

						billed_ret_amt = Double.parseDouble(modelProgBillMain.getValueAt(x, 3).toString());
						billed_ret_amt_tot = (billed_ret_amt_tot + billed_ret_amt); // added by Erick Bituen dated
																					// 2021-02-01 as per request by Maam
																					// rochelle of FAD: To compute all
																					// billed retentions
						System.out.println("billed_ret_amt_tot: " + billed_ret_amt_tot);
						System.out.println("billed_ret_amt: " + billed_ret_amt);

					} else {
					}
				} // comment muna para di bumagal 2022-01-18
			}
			// System.out.println("X: "+x);
		}

		System.out.println("dp_recoup1: " + dp_recoup);
		System.out.println("other_liq: " + other_liq);
		modelTotal.addRow(new Object[] { null, "Total", "released >>", gross_amt, vat_amt, wtax_amt, dp_recoup, ret_amt,
				bc_liq, other_liq, net_amt, null, null, null, null, null });

		double ret_amt_total = Double.parseDouble(modelProgBillTotal.getValueAt(0, 7).toString());
		// Added by Erick dated 2023/01/10 DCRF no 2353
		if (lookupContractNo.getValue().equals("TV-2019-PH4A-0056H")) {
			ret_bal = new BigDecimal(0.00);
		} else {
			if (lookupContractNo.getValue().equals("EX-2014-COMFAC-013CF")) {// Added by Erick DCRF 2697
				ret_bal = new BigDecimal(33000.00);
			} else {
				ret_bal = new BigDecimal(billed_ret_amt_tot - ret_amt_total);
			}

		}
		// ret_bal = new BigDecimal(billed_ret_amt_tot -ret_amt_total);

		System.out.println("billed_ret_amt_tot: " + billed_ret_amt_tot);
		System.out.println("ret_amt_total: " + ret_amt_total);
		System.out.println("ret_bal: " + ret_bal);

		modelTotal.addRow(new Object[] { null, "Total", "balance >>", df.format(gross_amt_bal), null, null, dp, ret_bal,
				null, null, null, null, null, null, null, null });

	}

	public static Boolean is_reverse(String co_id, String rplf_no) {
		pgSelect db = new pgSelect();

		String SQL = "SELECT EXISTS (select * from rf_pv_header where rplf_no = '" + rplf_no
				+ "' and reversal_jv_no is not null and co_id = '" + co_id + "' and status_id = 'P')";
		db.select(SQL);

		// System.out.println("Rplf_no: "+rplf_no);
		return (Boolean) db.getResult()[0][0];

	}

	private void displayAddUnitsOD() {

		int column = tblOD.getSelectedColumn();
		int row = tblOD.getSelectedRow();

		if (column == 1 && modelOD.isEditable()) {
			_JLookupTable dlg = new _JLookupTable(FncGlobal.homeMDI, null, "Accounts", getChartofAccount(), false);
			dlg.setLocationRelativeTo(FncGlobal.homeMDI);
			dlg.setVisible(true);

			Object[] data = dlg.getReturnDataSet();
			if (data != null) {
				modelOD.setValueAt(data[0], row, column);
				modelOD.setValueAt(data[1], row, column + 1);
			}
			tblOD.packAll();

			System.out.println("Dumaan sa displayAddUnitsOD ");
		}
	}

	private void displayAddUnits() {

		int column = tblProgBillMain.getSelectedColumn();
		int row = tblProgBillMain.getSelectedRow();

		if (column == 0 && modelProgBillMain.isEditable()) {
			_JLookupTable dlg = new _JLookupTable(FncGlobal.homeMDI, null, "Billing Type",
					getBillingType(txtNTP_no.getText().trim()), false);
			dlg.setLocationRelativeTo(FncGlobal.homeMDI);
			dlg.setVisible(true);

			Object[] data = dlg.getReturnDataSet();
			if (data != null) {
				modelProgBillMain.setValueAt(data[0], row, column);
			}

			modelProgBillMain.setValueAt(dateFormat.format(FncGlobal.dateFormat(FncGlobal.getDateSQL())), row, 1);
		}
	}

	private void displayReferenceNo() {

		int column = tblOD.getSelectedColumn();
		int row = tblOD.getSelectedRow();

		if (column == 7 && modelOD.isEditable()) {
			_JLookupTable dlg = new _JLookupTable(FncGlobal.homeMDI, null, "Reference No.", getReferenceNo(), false);
			dlg.setLocationRelativeTo(FncGlobal.homeMDI);
			dlg.setVisible(true);

			Object[] data = dlg.getReturnDataSet();
			if (data != null) {
				modelOD.setValueAt(data[1], row, column);
			}
			tblOD.packAll();

		}
	}

	private void displayContractNo() {

		int column = tblOD.getSelectedColumn();
		int row = tblOD.getSelectedRow();

		if (column == 7 && modelOD.isEditable()) {
			_JLookupTable dlg = new _JLookupTable(FncGlobal.homeMDI, null, "Contract No.", getODcontractno(), false);
			dlg.setLocationRelativeTo(FncGlobal.homeMDI);
			dlg.setVisible(true);

			Object[] data = dlg.getReturnDataSet();
			if (data != null) {
				modelOD.setValueAt(data[0], row, column);
				System.out.println("Dumaan sa data");
			}
			tblOD.packAll();

		}
	}

	private void displayType() {

		int column = tblOD.getSelectedColumn();
		int row = tblOD.getSelectedRow();

		if (column == 6 && modelOD.isEditable()) {
			_JLookupTable dlg = new _JLookupTable(FncGlobal.homeMDI, null, "Contract No.", getType(), false);
			dlg.setLocationRelativeTo(FncGlobal.homeMDI);
			dlg.setVisible(true);

			Object[] data = dlg.getReturnDataSet();
			if (data != null) {
				modelOD.setValueAt(data[0], row, column);
				System.out.println("Dumaan sa data");
			}
			tblOD.packAll();

		}
	}

	/*
	 * private void computeBillAmounts (KeyEvent evt){//old :this method computes
	 * when a ROW is edited
	 * 
	 * int c = tblProgBillMain.getSelectedColumn(); int r =
	 * tblProgBillMain.getSelectedRow(); int rw =
	 * tblProgBillMain.getModel().getRowCount();
	 * 
	 * if (row_num == rw || rw == 0) {} else {
	 * 
	 * String bill_type = modelProgBillMain.getValueAt(r,0).toString(); double
	 * accomp_new =
	 * Double.parseDouble(modelProgBillMain.getValueAt(r,2).toString());
	 * 
	 * if (evt.getKeyChar() == KeyEvent.VK_ENTER && bill_type.equals("01") &&
	 * accomp_new != 0) { JOptionPane.showMessageDialog(getContentPane(),
	 * "Accomp. Perc. is not applicable for Downpayment", "Not Applicable",
	 * JOptionPane.WARNING_MESSAGE); modelProgBillMain.setValueAt(0, r, c); }
	 * 
	 * else if (evt.getKeyChar() == KeyEvent.VK_ENTER && bill_type.equals("03") &&
	 * accomp_new != 0) { JOptionPane.showMessageDialog(getContentPane(),
	 * "Accomp. Perc. is not applicable for Retention", "Not Applicable",
	 * JOptionPane.WARNING_MESSAGE); modelProgBillMain.setValueAt(0, r, c); }
	 * 
	 * else if (evt.getKeyChar() == KeyEvent.VK_ENTER && bill_type.equals("02")) {
	 * double accomp_old = 0;
	 * 
	 * if (rw==1){accomp_old = 0;} else { accomp_old =
	 * Double.parseDouble(modelProgBillMain.getValueAt(r-1,2).toString()); }
	 * 
	 * double grossAmt = new_price * ((accomp_new - accomp_old) / 100); double
	 * vatAmt = (grossAmt / 1.12) * .12; double wtaxAmt = (grossAmt / 1.12) *
	 * (wtax_rate/100); double dpRec = dp_amount * ((accomp_new - accomp_old) /
	 * 100); double retentionAmt = grossAmt * .10; double bc_amt =
	 * Double.parseDouble(modelProgBillMain.getValueAt(r,8).toString()); double
	 * od_amt = Double.parseDouble(modelProgBillMain.getValueAt(r,9).toString());
	 * double netAmt = grossAmt - (wtaxAmt + dpRec + retentionAmt + bc_amt +
	 * od_amt);
	 * 
	 * BigDecimal accomp_old_bd = new BigDecimal(accomp_old); BigDecimal grossAmt_bd
	 * = new BigDecimal(grossAmt); BigDecimal vatAmt_bd = new BigDecimal(vatAmt);
	 * BigDecimal wtaxAmt_bd = new BigDecimal(wtaxAmt); BigDecimal dpRec_bd = new
	 * BigDecimal(dpRec); BigDecimal retentionAmt_bd = new BigDecimal(retentionAmt);
	 * BigDecimal netAmt_bd = new BigDecimal(netAmt);
	 * 
	 * if (evt.getKeyChar() == KeyEvent.VK_ENTER) { if (c==2 && accomp_new > 100 &&
	 * bill_type.equals("02")) { modelProgBillMain.setValueAt(accomp_old_bd, r, c);
	 * JOptionPane.showMessageDialog(getContentPane(), "Must be less than 100",
	 * "Accomplishment %", JOptionPane.WARNING_MESSAGE); } if (c==2 && accomp_new <
	 * accomp_old && bill_type.equals("02")) {
	 * modelProgBillMain.setValueAt(accomp_old_bd, r, c);
	 * JOptionPane.showMessageDialog(getContentPane(), "Must be greater than " +
	 * accomp_old, "Accomplishment %", JOptionPane.WARNING_MESSAGE); } }
	 * 
	 * else { if (c==2 && accomp_new > 100 && bill_type.equals("02")) {
	 * modelProgBillMain.setValueAt(accomp_old_bd, r, c);
	 * JOptionPane.showMessageDialog(getContentPane(), "Must be less than 100",
	 * "Accomplishment %", JOptionPane.WARNING_MESSAGE); } if (c==2 && accomp_new <
	 * accomp_old && bill_type.equals("02")) {
	 * modelProgBillMain.setValueAt(accomp_old_bd, r, c);
	 * JOptionPane.showMessageDialog(getContentPane(), "Must be greater than " +
	 * accomp_old, "Accomplishment %", JOptionPane.WARNING_MESSAGE); } }
	 * 
	 * modelProgBillMain.setValueAt(grossAmt_bd, r, 3);
	 * modelProgBillMain.setValueAt(vatAmt_bd, r, 4);
	 * modelProgBillMain.setValueAt(wtaxAmt_bd, r, 5);
	 * modelProgBillMain.setValueAt(dpRec_bd, r, 6);
	 * modelProgBillMain.setValueAt(retentionAmt_bd, r, 7);
	 * modelProgBillMain.setValueAt(netAmt_bd, r, 10);
	 * 
	 * totalProgBill(modelProgBillMain, modelProgBillTotal); } } }
	 */

	/*
	 * private void computeBillAmounts2 (){//this method computes when a ROW is
	 * added
	 * 
	 * int c = tblProgBillMain.getSelectedColumn(); int r =
	 * tblProgBillMain.getSelectedRow(); int rw =
	 * tblProgBillMain.getModel().getRowCount();
	 * 
	 * if (row_num == rw || rw == 0) {} else {
	 * 
	 * String bill_type = modelProgBillMain.getValueAt(r,0).toString(); double
	 * accomp_new =
	 * Double.parseDouble(modelProgBillMain.getValueAt(r,2).toString());
	 * 
	 * if (bill_type.equals("01") && accomp_new != 0) {
	 * JOptionPane.showMessageDialog(getContentPane(),
	 * "Accomp. Perc. is not applicable for Downpayment", "Not Applicable",
	 * JOptionPane.WARNING_MESSAGE); modelProgBillMain.setValueAt(0, r, c); }
	 * 
	 * else if (bill_type.equals("03") && accomp_new != 0) {
	 * JOptionPane.showMessageDialog(getContentPane(),
	 * "Accomp. Perc. is not applicable for Rentention", "Not Applicable",
	 * JOptionPane.WARNING_MESSAGE); modelProgBillMain.setValueAt(0, r, c); }
	 * 
	 * else if (bill_type.equals("02")) { double accomp_old = 0;
	 * 
	 * if (rw==1){accomp_old = 0;} else { accomp_old =
	 * Double.parseDouble(modelProgBillMain.getValueAt(r-1,2).toString()); }
	 * 
	 * double grossAmt = new_price * ((accomp_new - accomp_old) / 100); double
	 * vatAmt = (grossAmt / 1.12) * .12; double wtaxAmt = (grossAmt / 1.12) *
	 * (wtax_rate/100); double dpRec = dp_amount * ((accomp_new - accomp_old) /
	 * 100); double retentionAmt = grossAmt * .10; double bc_amt =
	 * Double.parseDouble(modelProgBillMain.getValueAt(r,8).toString()); double
	 * od_amt = Double.parseDouble(modelProgBillMain.getValueAt(r,9).toString());
	 * double netAmt = grossAmt - (wtaxAmt + dpRec + retentionAmt + bc_amt +
	 * od_amt);
	 * 
	 * //BigDecimal accomp_old_bd = new BigDecimal(accomp_old); BigDecimal
	 * grossAmt_bd = new BigDecimal(grossAmt); BigDecimal vatAmt_bd = new
	 * BigDecimal(vatAmt); BigDecimal wtaxAmt_bd = new BigDecimal(wtaxAmt);
	 * BigDecimal dpRec_bd = new BigDecimal(dpRec); BigDecimal retentionAmt_bd = new
	 * BigDecimal(retentionAmt); BigDecimal netAmt_bd = new BigDecimal(netAmt);
	 * 
	 * modelProgBillMain.setValueAt(grossAmt_bd, r, 3);
	 * modelProgBillMain.setValueAt(vatAmt_bd, r, 4);
	 * modelProgBillMain.setValueAt(wtaxAmt_bd, r, 5);
	 * modelProgBillMain.setValueAt(dpRec_bd, r, 6);
	 * modelProgBillMain.setValueAt(retentionAmt_bd, r, 7);
	 * modelProgBillMain.setValueAt(netAmt_bd, r, 10);
	 * 
	 * totalProgBill(modelProgBillMain, modelProgBillTotal); } } }
	 */

	private void computeBillAmounts_fromBC(Integer r) {// this method computes when a ROW is added from BC liquidation

		if (next_billing_type.equals("01")) {
			double grossAmt = Double.parseDouble(modelProgBillMain.getValueAt(0, 3).toString());
			double wtaxAmt = Double.parseDouble(modelProgBillMain.getValueAt(0, 5).toString());
			double dpRec = Double.parseDouble(modelProgBillMain.getValueAt(0, 6).toString());
			double retentionAmt = Double.parseDouble(modelProgBillMain.getValueAt(0, 7).toString());
			double bc_amt = Double.parseDouble(modelProgBillMain.getValueAt(0, 8).toString());
			double od_amt = Double.parseDouble(modelProgBillMain.getValueAt(0, 9).toString());
			// double netAmt = grossAmt - (wtaxAmt + dpRec + retentionAmt + bc_amt +
			// od_amt);
			double netAmt = getNetAmount(grossAmt, wtaxAmt, dpRec, retentionAmt, bc_amt, od_amt).doubleValue();
			BigDecimal netAmt_bd = new BigDecimal(netAmt);
			modelProgBillMain.setValueAt(netAmt_bd, 0, 10);
		} else {
			// int r = tblProgBillMain.getSelectedRow();
			double grossAmt = Double.parseDouble(modelProgBillMain.getValueAt(r, 3).toString());
			double wtaxAmt = Double.parseDouble(modelProgBillMain.getValueAt(r, 5).toString());
			double dpRec = Double.parseDouble(modelProgBillMain.getValueAt(r, 6).toString());
			double retentionAmt = Double.parseDouble(modelProgBillMain.getValueAt(r, 7).toString());
			double bc_amt = Double.parseDouble(modelProgBillMain.getValueAt(r, 8).toString());
			double od_amt = Double.parseDouble(modelProgBillMain.getValueAt(r, 9).toString());
			// double netAmt = grossAmt - (wtaxAmt + dpRec + retentionAmt + bc_amt +
			// od_amt);
			double netAmt = getNetAmount(grossAmt, wtaxAmt, dpRec, retentionAmt, bc_amt, od_amt).doubleValue();
			BigDecimal netAmt_bd = new BigDecimal(netAmt);
			modelProgBillMain.setValueAt(netAmt_bd, r, 10);
		}

		totalProgBill(modelProgBillMain, modelProgBillTotal);
	}

	private void computeBillAmounts() {// this method computes when a COLUMN is edited

		// This is needed since some progress billing have inactive first_row
		Integer dp_firstrow_inactive_status = 0;
		String dp_firstrow_status = modelProgBillMain.getValueAt(0, 14).toString();
		if (dp_firstrow_status.equals("INACTIVE")) {
			dp_firstrow_inactive_status = 1;
		} else {
		}

		int c = tblProgBillMain.getSelectedColumn();
		int r = tblProgBillMain.getSelectedRow();
		int rw = tblProgBillMain.getModel().getRowCount() - dp_firstrow_inactive_status;

		String bill_type = modelProgBillMain.getValueAt(r, 0).toString();
		double accomp_new = Double.parseDouble(modelProgBillMain.getValueAt(r, 2).toString());
		// String billStatus = modelProgBillMain.getValueAt(r,14).toString();

		double accomp_old = 0;

		if (c == 2) {
			double gsp = (Double.valueOf(txtContractPriceOrig.getText().trim().replace(",", "")).doubleValue());
			if (gsp == 0.00) {
				gsp = (Double.valueOf(txtContractPriceOrig.getText().trim().replace(",", "")).doubleValue());
			}

			if (rw == 1) {
				accomp_old = 0;
			} else {
				// accomp_old =
				// Double.parseDouble(modelProgBillMain.getValueAt(r-1,2).toString());
				accomp_old = Double.valueOf(getPrevAccomp().doubleValue());
			}

			new_price = gsp;
			double tax_rate = wtax_rate / 100.00;
			System.out.println("vat_rate: "+vat_rate);
			System.out.println("wtax_rate: "+ wtax_rate);
			System.out.println("tax_rate: "+tax_rate);

			// **EDITED BY JED 2019-04-30 : TO CONTROL ROUNDING OFF OF DECIMAL VALUES INTO 2
			// PLACES**//
			// double grossAmt = new_price * ((accomp_new - accomp_old) / 100);
			double grossAmt = getGrossAmount(new_price, accomp_new, accomp_old).doubleValue();
			System.out.printf("gross amt is: %s\n", grossAmt);
			// double vatAmt = (grossAmt / (1 + vat_rate) * vat_rate);
			double vatAmt = getVatAmount(grossAmt, vat_rate).doubleValue();
			System.out.printf("vat amt is: %s\n", vatAmt);
			// double wtaxAmt = (grossAmt / (1 + vat_rate)) * (wtax_rate/100);
			double wtaxAmt = getWtaxAmount(grossAmt, vat_rate, tax_rate, txtNTP_no.getText()).doubleValue();
			System.out.printf("wtax amt is: %s\n", wtaxAmt);
			// double dpRec = dp_amount * ((accomp_new - accomp_old) / 100);
			double dpRec = getDpRecoupAmount(dp_amount, accomp_new, accomp_old, txtNTP_no.getText()).doubleValue();
			System.out.printf("dpRec is: %s\n", dpRec);
			// double retentionAmt = grossAmt * .10;
			double retentionAmt = getRetAmount(grossAmt).doubleValue();
			System.out.printf("retentionAmt is: %s\n", retentionAmt);
			double bc_amt = Double.parseDouble(modelProgBillMain.getValueAt(r, 8).toString());
			double od_amt = Double.parseDouble(modelProgBillMain.getValueAt(r, 9).toString());
			// double netAmt = grossAmt - (wtaxAmt + dpRec + retentionAmt + bc_amt +
			// od_amt);
			double netAmt = getNetAmount(grossAmt, wtaxAmt, dpRec, retentionAmt, bc_amt, od_amt).doubleValue();
			System.out.printf("netAmt is: %s\n", netAmt);

			BigDecimal accomp_old_bd = new BigDecimal(accomp_old);
			BigDecimal grossAmt_bd = new BigDecimal(grossAmt);
			BigDecimal vatAmt_bd = new BigDecimal(vatAmt);
			BigDecimal wtaxAmt_bd = new BigDecimal(wtaxAmt);
			BigDecimal dpRec_bd = new BigDecimal(dpRec);
			BigDecimal retentionAmt_bd = new BigDecimal(retentionAmt);
			BigDecimal netAmt_bd = new BigDecimal(netAmt);

			// Exceptions
			if (accomp_new > 100 && bill_type.equals("02")) {
				modelProgBillMain.setValueAt(accomp_old_bd, r, c);
				JOptionPane.showMessageDialog(getContentPane(), "Must be less than 100", "Accomplishment %",
						JOptionPane.WARNING_MESSAGE);
				clearRowAmount(r);
			} else if (accomp_new < accomp_old && bill_type.equals("02") && accomp_new != 0) {
				if (txtNTP_no.getText().equals("001827")) {// Added by Erick 2021-11-10
					modelProgBillMain.setValueAt(accomp_new, r, c);
				} else {
					modelProgBillMain.setValueAt(accomp_old_bd, r, c);
					JOptionPane.showMessageDialog(getContentPane(), "Must be greater than " + accomp_old,
							"Accomplishment %", JOptionPane.WARNING_MESSAGE);
					clearRowAmount(r);
				}

			} else if (accomp_new == 0 && bill_type.equals("02")) {
				clearRowAmount(r);
			}
			// End-Exceptions

			else {
				modelProgBillMain.setValueAt(grossAmt_bd, r, 3);
				modelProgBillMain.setValueAt(vatAmt_bd, r, 4);
				modelProgBillMain.setValueAt(wtaxAmt_bd, r, 5);
				modelProgBillMain.setValueAt(dpRec_bd, r, 6);
				modelProgBillMain.setValueAt(retentionAmt_bd, r, 7);
				modelProgBillMain.setValueAt(netAmt_bd, r, 10);

				totalProgBill(modelProgBillMain, modelProgBillTotal);
				computeBC_liquidation(r);
			}
		} else if (c == 2 && bill_type == "04") { // Added by erick DCRF 2749

			totalProgBill(modelProgBillMain, modelProgBillTotal);

		}

		else if (c != 2 && c != 10) {

			if (rw == 1) {
				accomp_old = 0;
			} else {
				accomp_old = Double.parseDouble(modelProgBillMain.getValueAt(r - 1, 2).toString());
			}

			double grossAmt = Double.parseDouble(modelProgBillMain.getValueAt(r, 3).toString());
			double vatAmt = Double.parseDouble(modelProgBillMain.getValueAt(r, 4).toString());
			double wtaxAmt = Double.parseDouble(modelProgBillMain.getValueAt(r, 5).toString());
			double dpRec = Double.parseDouble(modelProgBillMain.getValueAt(r, 6).toString());
			double retentionAmt = Double.parseDouble(modelProgBillMain.getValueAt(r, 7).toString());
			double bc_amt = Double.parseDouble(modelProgBillMain.getValueAt(r, 8).toString());
			double od_amt = Double.parseDouble(modelProgBillMain.getValueAt(r, 9).toString());
			// double netAmt = grossAmt - wtaxAmt - dpRec - retentionAmt - bc_amt - od_amt;
			double netAmt = getNetAmount(grossAmt, wtaxAmt, dpRec, retentionAmt, bc_amt, od_amt).doubleValue();

			BigDecimal grossAmt_bd = new BigDecimal(grossAmt);
			BigDecimal vatAmt_bd = new BigDecimal(vatAmt);
			BigDecimal wtaxAmt_bd = new BigDecimal(wtaxAmt);
			BigDecimal dpRec_bd = new BigDecimal(dpRec);
			BigDecimal retentionAmt_bd = new BigDecimal(retentionAmt);
			BigDecimal bc_amt_bd = new BigDecimal(bc_amt);
			BigDecimal od_amt_bd = new BigDecimal(od_amt);
			BigDecimal netAmt_bd = new BigDecimal(netAmt);

			modelProgBillMain.setValueAt(grossAmt_bd, r, 3);
			modelProgBillMain.setValueAt(vatAmt_bd, r, 4);
			modelProgBillMain.setValueAt(wtaxAmt_bd, r, 5);
			modelProgBillMain.setValueAt(dpRec_bd, r, 6);
			modelProgBillMain.setValueAt(retentionAmt_bd, r, 7);
			modelProgBillMain.setValueAt(bc_amt_bd, r, 8);
			modelProgBillMain.setValueAt(od_amt_bd, r, 9);
			modelProgBillMain.setValueAt(netAmt_bd, r, 10);

			totalProgBill(modelProgBillMain, modelProgBillTotal);
		}

		else if (c == 10) {

			double netAmt = Double.parseDouble(modelProgBillMain.getValueAt(r, 10).toString());
			BigDecimal netAmt_bd = new BigDecimal(netAmt);
			modelProgBillMain.setValueAt(netAmt_bd, r, 10);

			totalProgBill(modelProgBillMain, modelProgBillTotal);
		}
	}

	private void computeOtherDeductions(KeyEvent evt) {
		System.out.println("computeOtherDeductions");

		int c = tblOD.getSelectedColumn();
		int r = tblOD.getSelectedRow();
		int rw = tblOD.getModel().getRowCount();
		acct_id = modelOD.getValueAt(r, 1).toString();

		if (acct_id.equals("") || acct_id == null) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter an account ID first.", "Warning",
					JOptionPane.WARNING_MESSAGE);
			_JLookupTable dlg = new _JLookupTable(FncGlobal.homeMDI, null, "Accounts", getChartofAccount(), false);
			dlg.setLocationRelativeTo(FncGlobal.homeMDI);
			dlg.setVisible(true);

			Object[] data = dlg.getReturnDataSet();

			if (data != null) {
				tblOD.getColumnModel().getColumn(3).setPreferredWidth(200);
				modelOD.setValueAt(data[0], r, 1);
				modelOD.setValueAt(data[1], r, 2);
				tblOD.packAll();
			}
		}

		else {

			if (row_num_od == rw && c == 3 || rw == 0 && c == 3) {
			} else {

				double odl_amt = Double.parseDouble(modelOD.getValueAt(r, 3).toString());
				modelOD.setValueAt(new BigDecimal(odl_amt), r, 3);
				totalOD(modelOD, modelOD_total);
				totalProgBill(modelProgBillMain, modelProgBillTotal);

				// for remarks
				String remarks = modelOD.getValueAt(r, 4).toString();
				if (billing_remarks.equals("")) {
					billing_remarks = remarks;
				} else {
					billing_remarks = billing_remarks + " ; " + remarks;
				}

				int tbl_main_rw = tblProgBillMain.getModel().getRowCount() - 1;

				double grs_amt = 0.00;
				double wtx_amt = 0.00;
				double dpr_amt = 0.00;
				double ret_amt = 0.00;
				double bcl_amt = 0.00;
				double net_amt = 0.00;

				double odl_amt_tot = Double.parseDouble(modelOD_total.getValueAt(0, 3).toString());

				try {
					grs_amt = Double.parseDouble(modelProgBillMain.getValueAt(tbl_main_rw, 3).toString());
				} catch (NullPointerException e) {
					grs_amt = 0;
				}
				try {
					wtx_amt = Double.parseDouble(modelProgBillMain.getValueAt(tbl_main_rw, 5).toString());
				} catch (NullPointerException e) {
					wtx_amt = 0;
				}
				try {
					dpr_amt = Double.parseDouble(modelProgBillMain.getValueAt(tbl_main_rw, 6).toString());
				} catch (NullPointerException e) {
					dpr_amt = 0;
				}
				try {
					ret_amt = Double.parseDouble(modelProgBillMain.getValueAt(tbl_main_rw, 7).toString());
				} catch (NullPointerException e) {
					ret_amt = 0;
				}
				try {
					bcl_amt = Double.parseDouble(modelProgBillMain.getValueAt(tbl_main_rw, 8).toString());
				} catch (NullPointerException e) {
					bcl_amt = 0;
				}
				try {
					net_amt = Double.parseDouble(modelProgBillMain.getValueAt(tbl_main_rw, 10).toString());
				} catch (NullPointerException e) {
					net_amt = 0;
				}

				double net_amt_new = 0;

				if (evt.getKeyChar() == KeyEvent.VK_ENTER && odl_amt < 0) {
					JOptionPane.showMessageDialog(getContentPane(), "Amount should be greater than zero",
							"Not Applicable", JOptionPane.WARNING_MESSAGE);
					modelOD.setValueAt(new BigDecimal(0.00), r, c);
					totalOD(modelOD, modelOD_total);
					tblOD.packAll();
				}

				else if (evt.getKeyChar() == KeyEvent.VK_ENTER && odl_amt > net_amt)// Edited by Erick 02-26-2020 as
																					// requested by Pam of FAD with DCRF
																					// no 1386
				// else if (evt.getKeyChar() == KeyEvent.VK_ENTER && odl_amt_tot > net_amt)
				{
					System.out.println("odl_amt_tot= " + odl_amt_tot);
					// System.out.println("net_amt= "+net_amt );
					System.out.println("net_amt= " + grs_amt);
					JOptionPane.showMessageDialog(getContentPane(),
							"Total deduction amount should be less than billing's net amount", "Not Applicable",
							JOptionPane.WARNING_MESSAGE);
					modelOD.setValueAt(new BigDecimal(0.00), r, c);
					totalOD(modelOD, modelOD_total);
					tblOD.packAll();

				}

				else {
					System.out.println("odl_amt_tot: "+odl_amt_tot);
					net_amt_new = grs_amt - wtx_amt - dpr_amt - ret_amt - bcl_amt - odl_amt_tot;
					if(btnSave.isEnabled()) {//Added by Erick 2024-05-22 to display other deduction amount in progress billing when adding other deduction.
						modelProgBillMain.setValueAt(new BigDecimal(odl_amt_tot), tbl_main_rw, 9);
						modelProgBillMain.setValueAt(new BigDecimal(net_amt_new), tbl_main_rw, 10);
					}
					//modelProgBillMain.setValueAt(new BigDecimal(odl_amt_tot), tbl_main_rw, 9);//Comment by erick 2024-05-09 it overide and  display wrong value to the main table.
					//modelProgBillMain.setValueAt(new BigDecimal(net_amt_new), tbl_main_rw, 10);//Comment by erick 2024-05-09 it overide and  display wrong value to the main table.
					tblOD.packAll();
				}

				tblOD.getColumnModel().getColumn(3).setPreferredWidth(200);
			}
		}
	}

	private void computeBackcharges(KeyEvent evt) {

		int c = tblBC.getSelectedColumn();
		int r = tblBC.getSelectedRow();
		int rw = tblBC.getModel().getRowCount();

		if (row_num_bc == rw || rw == 0) {
		} else {

			double ob_amt = Double.parseDouble(modelBC.getValueAt(r, 5).toString());
			int tbl_main_rw = tblProgBillMain.getModel().getRowCount() - 1;
			double grs_amt = 0.00;
			double wtx_amt = 0.00;
			double dpr_amt = 0.00;
			double ret_amt = 0.00;
			double bcl_amt = 0.00;
			double odl_amt = 0.00;
			double bcl_amt_tot = 0.00;

			try {
				grs_amt = Double.parseDouble(modelProgBillMain.getValueAt(tbl_main_rw, 3).toString());
			} catch (NullPointerException e) {
				grs_amt = 0;
			}
			try {
				wtx_amt = Double.parseDouble(modelProgBillMain.getValueAt(tbl_main_rw, 5).toString());
			} catch (NullPointerException e) {
				wtx_amt = 0;
			}
			try {
				dpr_amt = Double.parseDouble(modelProgBillMain.getValueAt(tbl_main_rw, 6).toString());
			} catch (NullPointerException e) {
				dpr_amt = 0;
			}
			try {
				ret_amt = Double.parseDouble(modelProgBillMain.getValueAt(tbl_main_rw, 7).toString());
			} catch (NullPointerException e) {
				ret_amt = 0;
			}
			try {
				odl_amt = Double.parseDouble(modelProgBillMain.getValueAt(tbl_main_rw, 9).toString());
			} catch (NullPointerException e) {
				odl_amt = 0;
			}

			double net_amt_new = 0;
			bcl_amt = Double.parseDouble(modelBC.getValueAt(r, 4).toString());
			bcl_amt_tot = Double.parseDouble(modelBC_total.getValueAt(0, 4).toString());

			if (bcl_amt < 0) {
				JOptionPane.showMessageDialog(getContentPane(), "Amount should be greater than zero", "Not Applicable",
						JOptionPane.WARNING_MESSAGE);
				modelBC.setValueAt(0, r, c);
			}

			else if (bcl_amt > ob_amt) {
				JOptionPane.showMessageDialog(getContentPane(), "Amount should be less than or equal to OB amount",
						"Not Applicable", JOptionPane.WARNING_MESSAGE);
				modelBC.setValueAt(0, r, c);
			}

			else {
				net_amt_new = grs_amt - wtx_amt - dpr_amt - ret_amt - bcl_amt_tot - odl_amt;
				modelProgBillMain.setValueAt(new BigDecimal(bcl_amt_tot), tbl_main_rw, 8);
				modelProgBillMain.setValueAt(new BigDecimal(net_amt_new), tbl_main_rw, 10);
			}

		}

	}

	private void computeBackcharges2() {

		int rw = tblBC.getModel().getRowCount();

		if (row_num_bc == rw || rw == 0) {
		} else {

			int tbl_main_rw = tblProgBillMain.getModel().getRowCount() - 1;
			double grs_amt = 0.00;
			double wtx_amt = 0.00;
			double dpr_amt = 0.00;
			double ret_amt = 0.00;
			double odl_amt = 0.00;
			double bcl_amt_tot = 0.00;

			try {
				grs_amt = Double.parseDouble(modelProgBillMain.getValueAt(tbl_main_rw, 3).toString());
			} catch (NullPointerException e) {
				grs_amt = 0;
			}
			try {
				wtx_amt = Double.parseDouble(modelProgBillMain.getValueAt(tbl_main_rw, 5).toString());
			} catch (NullPointerException e) {
				wtx_amt = 0;
			}
			try {
				dpr_amt = Double.parseDouble(modelProgBillMain.getValueAt(tbl_main_rw, 6).toString());
			} catch (NullPointerException e) {
				dpr_amt = 0;
			}
			try {
				ret_amt = Double.parseDouble(modelProgBillMain.getValueAt(tbl_main_rw, 7).toString());
			} catch (NullPointerException e) {
				ret_amt = 0;
			}
			try {
				odl_amt = Double.parseDouble(modelProgBillMain.getValueAt(tbl_main_rw, 9).toString());
			} catch (NullPointerException e) {
				odl_amt = 0;
			}

			double net_amt_new = 0;
			// bcl_amt = Double.parseDouble(modelBC_total.getValueAt(0,4).toString());
			bcl_amt_tot = Double.parseDouble(modelBC_total.getValueAt(0, 4).toString());

			net_amt_new = grs_amt - wtx_amt - dpr_amt - ret_amt - bcl_amt_tot - odl_amt;
			modelProgBillMain.setValueAt(new BigDecimal(bcl_amt_tot), tbl_main_rw, 8);
			modelProgBillMain.setValueAt(new BigDecimal(net_amt_new), tbl_main_rw, 10);

		}

	}

	public void createOtherDeductionTable(DefaultTableModel modelMain, JList rowHeader, DefaultTableModel modelTotal) {

		FncTables.clearTable(modelMain);// Code to clear modelMain.
		DefaultListModel listModel = new DefaultListModel();// Creating DefaultListModel for rowHeader.
		rowHeader.setModel(listModel);// Setting of DefaultListModel into rowHeader.

		String sql = "select '', '', '', 0.00, '', '' ";

		pgSelect db = new pgSelect();
		db.select(sql);
		if (db.isNotNull()) {
			for (int x = 0; x < db.getRowCount(); x++) {
				// Adding of row in table
				modelMain.addRow(db.getResult()[x]);
				listModel.addElement(modelMain.getRowCount());
			}

			tblOD.getColumnModel().getColumn(3).setPreferredWidth(200);
			totalOD(modelMain, modelTotal);
		}

		else {
			modelOD_total = new modelContractorOtherDeductions();
			modelOD_total.addRow(new Object[] { null, "Total", null, new BigDecimal(0.00), null, null });

			tblOD_total = new _JTableTotal(modelOD_total, tblOD);
			tblOD_total.setFont(dialog11Bold);
			scrollOD_total.setViewportView(tblOD_total);
			((_JTableTotal) tblOD_total).setTotalLabel(1);
		}

	}

	private void computeTotalBackcharges() {

		int rw = tblBC.getModel().getRowCount();
		int x = 0;

		while (x < rw) {

			Double bc_amount_bc = 0.00;
			Double liq_amt_bc = 0.00;
			Double for_liq_bc = 0.00;
			Double ob_amt_bc = 0.00;

			try {
				bc_amount_bc = Double.parseDouble(modelBC.getValueAt(x, 2).toString());
			} catch (NullPointerException e) {
				bc_amount_bc = 0.00;
			}
			try {
				liq_amt_bc = Double.parseDouble(modelBC.getValueAt(x, 3).toString());
			} catch (NullPointerException e) {
				liq_amt_bc = 0.00;
			}
			try {
				for_liq_bc = Double.parseDouble(modelBC.getValueAt(x, 4).toString());
			} catch (NullPointerException e) {
				for_liq_bc = 0.00;
			}
			try {
				ob_amt_bc = Double.parseDouble(modelBC.getValueAt(x, 5).toString());
			} catch (NullPointerException e) {
				ob_amt_bc = 0.00;
			}

			BigDecimal bc_amount_bc_bd = new BigDecimal(bc_amount_bc);
			BigDecimal liq_amt_bc_bd = new BigDecimal(liq_amt_bc);
			BigDecimal for_liq_bc_bd = new BigDecimal(for_liq_bc);
			BigDecimal ob_amt_bc_bd = new BigDecimal(ob_amt_bc);

			modelBC.setValueAt(bc_amount_bc_bd, x, 2);
			modelBC.setValueAt(liq_amt_bc_bd, x, 3);
			modelBC.setValueAt(for_liq_bc_bd, x, 4);
			modelBC.setValueAt(ob_amt_bc_bd, x, 5);

			x++;
		}

		totalBC(modelBC, modelBC_total);
	}

	private void computeBC_liquidation(Integer r) {

		// int r = tblProgBillMain.getModel().getRowCount()-1;
		double netAmt = 0;
		double tot_bcAmt = Double.parseDouble(modelBC_total.getValueAt(0, 5).toString());

		System.out.printf("row no.:" + r);
		System.out.printf("netAmt:" + netAmt);
		System.out.printf("tot_bcAmt:" + tot_bcAmt);

		if (tot_bcAmt == 0) {
		}

		else {

			int c = 0;
			int rw = tblBC.getModel().getRowCount();
			double bcAmt = 0;
			System.out.printf("bcAmt: " + bcAmt);

			while (c < rw)

			{
				netAmt = Double.parseDouble(modelProgBillMain.getValueAt(r, 10).toString());
				double cur_bcAmt = Double.parseDouble(modelBC.getValueAt(c, 4).toString());
				bcAmt = bcAmt + cur_bcAmt;
				// System.out.printf("\n" + "bcAmt: " + bcAmt + "\n" );

				if (netAmt == 0) {
				} else {
					if (netAmt >= bcAmt) {
						// set Billing details
						BigDecimal bcAmt_bd = new BigDecimal(bcAmt);
						modelProgBillMain.setValueAt(bcAmt_bd, r, 8);

						computeBillAmounts_fromBC(r);

						// set Liquidation details
						BigDecimal newForliquiAmt_bd = new BigDecimal(cur_bcAmt);
						modelBC.setValueAt(newForliquiAmt_bd, c, 4);
						totalBC(modelBC, modelBC_total);
					}

					else

					if (netAmt < cur_bcAmt) {
						// set Billing details
						BigDecimal netAmt_bd = new BigDecimal(netAmt);
						modelProgBillMain.setValueAt(netAmt_bd, r, 8);

						computeBillAmounts_fromBC(r);

						// set Liquidation details
						BigDecimal newForliquiAmt_bd = new BigDecimal(netAmt);
						modelBC.setValueAt(newForliquiAmt_bd, c, 4);
						totalBC(modelBC, modelBC_total);
					}
				}

				c++;
			}

			computeBackcharges2();
		}
	}

	private void clearRowAmount(Integer r) {
		modelProgBillMain.setValueAt(new BigDecimal(0.00), r, 3);
		modelProgBillMain.setValueAt(new BigDecimal(0.00), r, 4);
		modelProgBillMain.setValueAt(new BigDecimal(0.00), r, 5);
		modelProgBillMain.setValueAt(new BigDecimal(0.00), r, 6);
		modelProgBillMain.setValueAt(new BigDecimal(0.00), r, 7);
		modelProgBillMain.setValueAt(new BigDecimal(0.00), r, 8);
		modelProgBillMain.setValueAt(new BigDecimal(0.00), r, 9);
		modelProgBillMain.setValueAt(new BigDecimal(0.00), r, 10);
	}

	// save and insert
	private void insertBilling(pgUpdate db, String rplf) {

		int rw = tblProgBillMain.getModel().getRowCount() - 1;
		billing_date = modelProgBillMain.getValueAt(rw, 1).toString();

		String billint_type = "";
		String entity_type_id = "";
		Date date_rlsd = null;
		String remarks = "";
		String status = "";

		billint_type = modelProgBillMain.getValueAt(rw, 0).toString();
		if (billint_type == "04") {
			bill_no = 0000000000;
			status = "";

		} else {
			bill_no = sql_getProgBillNo();
			status = "A";
		}
		entity_type_id = txtEntity.getText().trim();
		deduction_amt = (Double.valueOf(txtDAmount.getText().trim().replace(",", "")).doubleValue());
		remarks = modelProgBillMain.getValueAt(rw, 15).toString();

		String sqlDetail = "INSERT into co_billing_detail (ntp_no, billing_date, billing_no, billing_type, entity_type_id, accomp_pctg, gross_amt, vat_amt, wtax_amt, dp_recoupment, retention_amt, ca_liquidation, other_deductions, net_amt, co_id, busunit_id, rplf_no, jv_no, date_released, status_id, created_by, date_created, edited_by, date_edited, other_liqui_acct_id, deduction_amt, billing_remarks) "
				+ "values (" + "'" + txtNTP_no.getText().trim() + "',  \n" + // 2
				"'" + billing_date + "',  \n" + // 3
				"trim(to_char(" + bill_no + ",'0000000000')),  \n" + // 4
				"'" + billint_type + "',  \n" + // 5
				"'" + entity_type_id + "',  \n" + // 6
				"" + accomp_perc + ",  \n" + // 7
				"" + gr_amt + " , \n" + // 8
				"" + vat_amt + ",  \n" + // 9
				"" + wtx_amt + ",  \n" + // 10
				"" + dpr_amt + ",  \n" + // 11
				"" + ret_amt + ",  \n" + // 12
				"" + bc_amt + " , \n" + // 13
				"" + od_amt + ",  \n" + // 14
				"" + net_amt + ", \n" + // 15
				"'" + lookupCompany.getValue() + "',  \n" + // 16
				"'" + lookupCompany.getValue() + "' , \n" + // 17
				"'" + rplf + "' , \n" + // 18
				"'',  \n" + // 19 jv_no = null
				"" + date_rlsd + " , \n" + // 20
				"'" + status + "', \n " + // 21
				"'" + UserInfo.EmployeeCode + "' , \n" + // 22
				"'" + dateFormat.format(FncGlobal.dateFormat(FncGlobal.getDateSQL())) + "' , \n" + // 23
				"''," + // 24
				"null,  \n" + // 25
				"'" + acct_id + "', \n" + // 26
				"" + deduction_amt + ", \n" + // 27
				"'" + remarks + "'\n" + ")   ";

		System.out.printf("SQL #1: %s", sqlDetail);
		db.executeUpdate(sqlDetail, false);
	}

	private void insertRPLF_header(pgUpdate db) {

		int rw = tblProgBillMain.getModel().getRowCount() - 1;
		String billingremarks = "";
		billingremarks = modelProgBillMain.getValueAt(rw, 15).toString();

		Date date_liq_ext = null;
		String rplf_type_id = "";
		String entity_id1 = "";
		// String entity_id2 = "";
		String ent_type_id = "";
		String sd_no = "";
		String doc_id = "";
		Integer proc_id = null;
		String branch_id = "";
		String justif = "";
		// String remarks = "";
		String status_id = "";
		String created_by = "";
		String edited_by = "";
		Date edited_date = null;

		date_liq_ext = null;
		rplf_type_id = "01";
		entity_id1 = txtContractor.getText().trim();
		// entity_id2 = "";
		ent_type_id = txtEntity.getText().trim();
		sd_no = null;
		doc_id = "09";
		proc_id = 1;
		branch_id = null;
		justif = null;
		// remarks = "Backcharge Utilities:"+ "\n" +billing_remarks;
		// remarks = null;
		status_id = "A";
		created_by = UserInfo.EmployeeCode;
		edited_by = "";
		edited_date = null;

		String sqlDetail = "INSERT into rf_request_header values ( \n" + "'" + lookupCompany.getValue() + "',  \n" + // 1
				"'" + lookupCompany.getValue() + "',  \n" + // 2
				"'" + rplf_no + "',  \n" + // 3
				"now(),  \n" + // 4
				"'" + billing_date + "',  \n" + // 5
				"" + date_liq_ext + ",  \n" + // 6
				"'" + rplf_type_id + "' , \n" + // 7
				"'" + entity_id1 + "',  \n" + // 8
				"'" + entity_id1 + "',  \n" + // 9
				"'" + ent_type_id + "',  \n" + // 10
				"" + sd_no + ",  \n" + // 11
				"'" + doc_id + "' , \n" + // 12
				"" + proc_id + ",  \n" + // 13
				"" + branch_id + " , \n" + // 14
				"" + justif + ",  \n" + // 15
				// "'"+remarks+"' , \n" + //16
				" '" + billingremarks + "', \n" + // 16
				"'" + status_id + "' , \n" + // 17
				"'" + created_by + "',  \n" + // 18
				"'" + billing_date + "' , \n" + // 19
				"'" + edited_by + "' , \n" + // 20
				"" + edited_date + ", \n" + // 21
				"'B', \n" + // 22
				"null " + // 23
				")   ";

		System.out.printf("SQL #1: %s", sqlDetail);
		db.executeUpdate(sqlDetail, false);
	}

	private void insertRPLF_detail(pgUpdate db) {

		int rw = tblProgBillMain.getModel().getRowCount() - 1;
		Double accomp_perc_old = 0.00;
		Double accomp_perc_new = Double.parseDouble(modelProgBillMain.getValueAt(rw, 2).toString());
		String billing_type = modelProgBillMain.getValueAt(rw, 0).toString();
		if (rw == 0) {
			accomp_perc_old = 0.00;
		} else {
			accomp_perc_old = getPrevAccomp();
		}

		int rw_bc = tblBC.getModel().getRowCount();

		int x = 0;
		while (x < rw_bc) {

			try {
				for_liq_bc_jv = Double.parseDouble(modelBC.getValueAt(x, 4).toString());
			} catch (NullPointerException e) {
				for_liq_bc_jv = 0.00;
			}
			String bc_rplf_no = modelBC.getValueAt(x, 0).toString();

			if (for_liq_bc_jv != 0.00) {

				if (checkbackcharge(bc_rplf_no) && bc_amt > 0) {

					remarks = "Backcharge Utilities:";

					break;
				} else {
					remarks = "";
					break;
				}
			}
			x++;
			System.out.println("bc_rplf_no: " + bc_rplf_no);

		}

		Integer line_no = null;
		String ref_doc_id = "";
		Date ref_doc_date = null;
		Boolean with_budget = false;
		String part_desc = "";
		String acct_id = "";
		// String remarks ="";
		Double amount = 0.00;
		String entity_id = "";
		String entity_type_id = "";
		String proj_id = "";
		String div_id = "";
		String dept_id = "";
		String sec_id = "";
		String inter_bus_id = "";
		String inter_co_id = "";
		String sd_no = "";
		String asset_no = "";
		String old_acct_id = "";
		String subProj = "";

		line_no = 1;
		ref_doc_id = "75"; // 75 - Contractor's Billing Statement
		ref_doc_date = FncGlobal.dateFormat(FncGlobal.getDateSQL());
		with_budget = false;
		part_desc = setPartDesc(billing_type, accomp_perc_new, accomp_perc_old, ret_amt, dpr_amt, wtx_amt);
		acct_id = sql_getAcctID();
		// remarks = "Backcharge Utilities:";
		amount = Double.parseDouble(modelProgBillMain.getValueAt(rw, 3).toString());
		entity_id = txtContractor.getText().trim();
		entity_type_id = txtEntity.getText().trim();
		proj_id = txtProject.getText().trim();
		div_id = "";
		dept_id = "";
		sec_id = "";
		inter_bus_id = "";
		inter_co_id = "";
		sd_no = null;
		asset_no = null;
		old_acct_id = null;
		subProj = getSubProject(proj_id);
		System.out.println("remarks: " + remarks);

		String sqlDetail = "INSERT into rf_request_detail values ( \n" + "'" + lookupCompany.getValue() + "',  \n" + // 1
				"'" + lookupCompany.getValue() + "',  \n" + // 2
				"'" + rplf_no + "',  \n" + // 3
				"" + line_no + ",  \n" + // 4
				"'" + ref_doc_id + "',  \n" + // 5
				"trim(to_char(" + bill_no + ",'0000000000')),  \n" + // 6
				"'" + ref_doc_date + "',  \n" + // 7
				"" + with_budget + " , \n" + // 8
				"'" + part_desc + "',  \n" + // 9
				"'" + acct_id + "',  \n" + // 10
				"'" + remarks + "',  \n" + // 11
				"" + amount + ",  \n" + // 12
				"'" + entity_id + "',  \n" + // 13
				"'" + entity_type_id + "' , \n" + // 14
				"'" + proj_id + "',  \n" + // 15
				"'" + subProj + "' , \n" + // 16
				"'" + div_id + "',  \n" + // 17
				"'" + dept_id + "' , \n" + // 18
				"'" + sec_id + "',  \n" + // 19
				"'" + inter_bus_id + "' , \n" + // 20
				"'" + inter_co_id + "' , \n" + // 21
				"get_proj_isvatable('" + proj_id + "'), \n" + // 22
				"get_ent_isvatable('" + entity_id + "') , \n" + // 23
				"false, \n" + // 24
				"false, \n" + // 25
				"get_wtaxid_given_entitytype('" + entity_type_id + "') , \n" + // 26
				"" + wtax_rate + ", \n" + // 27
				"" + wtx_amt + ", \n" + // 28
				"null, \n" + // 29
				"(case when get_ent_isvatable('" + entity_id + "') = true then 12 else 0 end), \n" + // 30
				"" + vat_amt + ", \n" + // 31
				"" + exp_amt + ", \n" + // 32
				"" + net_amt + ", \n" + // 33
				"" + sd_no + ", \n" + // 34
				"null, \n" + // 35
				"" + asset_no + ", \n" + // 36
				"" + old_acct_id + ", \n" + // 37
				"'A',  \n" + // 38
				"'" + UserInfo.EmployeeCode + "',  \n" + // 39
				"'" + billing_date + "' , \n" + // 40
				"'' , \n" + // 41
				"null, \n" + // 42
				"" + ret_amt + ", \n" + // 43
				"" + dpr_amt + ", \n" + // 44
				"" + bc_amt + ", \n" + // 45
				"" + od_amt + " \n" + // 46

				")   ";

		System.out.printf("SQL #1: %s", sqlDetail);
		db.executeUpdate(sqlDetail, false);
	}

	/*
	 * private void insertPV_header(pgUpdate db){
	 * 
	 * String entity_id1 = txtContractor.getText().trim(); String entity_id2 = "";
	 * String entity_type_id = txtEntity.getText().trim(); String created_by =
	 * UserInfo.EmployeeCode; String edited_by = null; Date date_edited = null;
	 * 
	 * String sqlDetail = "INSERT into rf_pv_header values (\n" + "'"+co_id+"',  \n"
	 * + //1 "'"+co_id+"',  \n" + //2 "'"+rplf_no+"',  \n" + //3 "'"+co_id+"',  \n"
	 * + //4 "'"+co_id+"',  \n" + //5 "'"+rplf_no+"',  \n" + //6 "'',  \n" + //7
	 * "'"+billing_date+"',  \n" + //8 "'B',  \n" + //9 "'"+entity_id1+"',  \n" +
	 * //10 "'"+entity_id2+"',  \n" + //11 "'"+entity_type_id+"',  \n" + //12
	 * "'',  \n" + //13 "'"+billing_date+"',  \n" + //14 "null,  \n" + //15
	 * "null,  \n" + //16 "'',  \n" + //17 "null,  \n" + //18 "'"+part_desc+"',  \n"
	 * + //19 "'12',  \n" + //20 "0,  \n" + //21 "false,  \n" + //22 "false,  \n" +
	 * //23 "null,  \n" + //24 "null,  \n" + //25 "'A',  \n" + //26
	 * "'"+created_by+"',  \n" + //27 "'"+billing_date+"',  \n" + //28
	 * "'"+edited_by+"',  \n" + //29 ""+date_edited+"  \n" + //30
	 * 
	 * ")   " ;
	 * 
	 * System.out.printf("SQL #1: %s", sqlDetail); db.executeUpdate(sqlDetail,
	 * false); }
	 * 
	 * private void insertPV_detail_dp(pgUpdate db){
	 * 
	 * Double amt_payable = gr_amt - wtx_amt - ret_amt - bc_amt ;
	 * 
	 * int x=0 ,y = 0; // advances accts_payable wtax retentions backcharges String
	 * acct_id [] =
	 * {"01-02-07-001","03-01-01-001","03-01-06-002","03-01-13-000","01-02-07-002"};
	 * String bal_side [] = {"D","C","C","C","C" }; Double amount [] = {gr_amt,
	 * amt_payable, wtx_amt, ret_amt, bc_amt };
	 * 
	 * while(x <= 4 ) {
	 * 
	 * if (amount[x] == 0.00) {} else {
	 * 
	 * String proj_id = txtProject.getText().trim();
	 * 
	 * String sqlDetail = "INSERT into rf_pv_detail values ( \n" +
	 * "'"+co_id+"',  \n" + //1 "'"+co_id+"',  \n" + //2 "'"+rplf_no+"',  \n" + //3
	 * ""+y+",  \n" + //4 "'"+acct_id [x]+"',  \n" + //5 ""+amount [x]+",  \n" + //6
	 * "'"+bal_side[x]+"',  \n" + //8 "'"+proj_id+"',  \n" + //9 "'', \n" + //10
	 * "'',  \n" + //11 "'',  \n" + //12 "'',  \n" + //13 "'',  \n" + //14 "'',  \n"
	 * + //15 "'',  \n" + //15 "'A',  \n" + //16 "'"+UserInfo.EmployeeCode+"',  \n"
	 * + //17 "'"+billing_date+"' , \n" + //18 "'' , \n" + //19 "null \n" + //20
	 * ")   " ;
	 * 
	 * System.out.printf("SQL #1: %s", sqlDetail); db.executeUpdate(sqlDetail,
	 * false);
	 * 
	 * y++; }
	 * 
	 * x++; }
	 * 
	 * 
	 * }
	 * 
	 * private void insertPV_detail_ret(pgUpdate db){
	 * 
	 * Double amt_payable = gr_amt - bc_amt - od_amt ; String od_acct = "";
	 * 
	 * if (tblProgBillMain.getModel().getRowCount()==0) {} else {od_acct =
	 * modelOD.getValueAt(0,0).toString();}
	 * 
	 * int x=0 ,y = 0; // retentions act_payable backcharges String acct_id [] =
	 * {"03-01-13-000","03-01-01-001","01-02-07-002", od_acct}; String bal_side [] =
	 * {"D","C","C","C" }; Double amount [] = {gr_amt, amt_payable, bc_amt, od_amt
	 * };
	 * 
	 * while(x <= 3 ) {
	 * 
	 * if (amount[x] == 0.00) {} else {
	 * 
	 * String proj_id = txtProject.getText().trim();
	 * 
	 * String sqlDetail = "INSERT into rf_pv_detail values (" + "'"+co_id+"',  \n" +
	 * //1 "'"+co_id+"',  \n" + //2 "'"+rplf_no+"',  \n" + //3 ""+y+",  \n" + //4
	 * "'"+acct_id [x]+"',  \n" + //5 ""+amount [x]+",  \n" + //6
	 * "'"+bal_side[x]+"',  \n" + //8 "'"+proj_id+"',  \n" + //9 "'', \n" + //10
	 * "'',  \n" + //11 "'',  \n" + //12 "'',  \n" + //13 "'',  \n" + //14 "'',  \n"
	 * + //15 "'',  \n" + //15 "'A',  \n" + //16 "'"+UserInfo.EmployeeCode+"',  \n"
	 * + //17 "'"+billing_date+"' , \n" + //18 "'' , \n" + //19 "null \n" + //20
	 * ")   " ;
	 * 
	 * System.out.printf("SQL #1: %s", sqlDetail); db.executeUpdate(sqlDetail,
	 * false);
	 * 
	 * y++; }
	 * 
	 * x++; }
	 * 
	 * 
	 * }
	 * 
	 * private void insertPV_detail_billing(pgUpdate db){
	 * 
	 * Double net_vat_amt = gr_amt - vat_amt ;
	 * 
	 * String od_acct = "";
	 * 
	 * if (tblProgBillMain.getModel().getRowCount()==0) {} else {od_acct =
	 * modelOD.getValueAt(0,0).toString();}
	 * 
	 * int x = 0; int y = 1;
	 * 
	 * // cip vat payable wtax advances-dp retentions backcharge String acct_id [] =
	 * {sql_getAcctID(), "01-99-03-000",
	 * "03-01-01-001","03-01-06-002","01-02-07-001","03-01-13-000","01-02-07-002",
	 * od_acct };
	 * 
	 * String bal_side [] = {"D","D","C","C","C","C","C","C" };
	 * 
	 * Double amount [] =
	 * {net_vat_amt,vat_amt,net_amt,wtx_amt,dpr_amt,ret_amt,bc_amt,od_amt};
	 * 
	 * while(x <= 7 ) {
	 * 
	 * if (amount[x] == 0.00) {} else {
	 * 
	 * String proj_id = txtProject.getText().trim();
	 * 
	 * String sqlDetail = "INSERT into rf_pv_detail values (" + "'"+co_id+"',  \n" +
	 * //1 "'"+co_id+"',  \n" + //2 "'"+rplf_no+"',  \n" + //3 ""+y+",  \n" + //4
	 * "'"+acct_id [x]+"',  \n" + //5 ""+amount[x]+",  \n" + //6
	 * "'"+bal_side[x]+"',  \n" + //8 "'"+proj_id+"',  \n" + //9 "'', \n" + //10
	 * "'',  \n" + //11 "'',  \n" + //12 "'',  \n" + //13 "'',  \n" + //14 "'',  \n"
	 * + //15 "'',  \n" + //15 "'A',  \n" + //16 "'"+UserInfo.EmployeeCode+"',  \n"
	 * + //17 "'"+billing_date+"' , \n" + //18 "'' , \n" + //19 "null \n" + //20
	 * ")   " ;
	 * 
	 * System.out.printf("SQL #1: %s", sqlDetail); db.executeUpdate(sqlDetail,
	 * false);
	 * 
	 * y++; }
	 * 
	 * x++; }
	 * 
	 * 
	 * }
	 */

	private void insertAudit_trail_billing(String activity, pgUpdate db, String bill, String rec_id) {

		String user_code = UserInfo.EmployeeCode;

		String sqlDetail = "INSERT INTO mf_audit_trail(\n"
				+ "	              system_id, activity, user_code, date_upd, entity_id, \n"
				+ "	              client_seqno, projcode, pbl_id, doc_id, doc_no, remarks)\n"
				// + " VALUES ('PV', '" + activity + "', '" + user_code + "', NOW(), NULL, \n"
				// // Comment by Erick 2024-03-01 wrong system_id
				+ "	      VALUES ('CB', '" + activity + "', '" + user_code + "', NOW(), NULL, \n" // Added by Erick
																									// 2024-03-01
				+ "	              NULL, NULL, NULL, NULL, '" + bill + "', 'Billing_no: ' || '" + bill
				+ "'|| ' ' || 'Rec_id: '|| '" + rec_id + "'|| ' ' || 'Ntp_no: '||'" + txtNTP_no.getText() + "');";

		System.out.printf("SQL #1: %s", sqlDetail);
		db.executeUpdate(sqlDetail, false);

	}

	private void insertAudit_trail_post_adjustment(String activity, Integer rec_id, String ntp_no, String bill_no) {

		System.out.println("bill_no:" + bill_no);
		String user_code = UserInfo.EmployeeCode;
		pgUpdate db = new pgUpdate();

		String strsql = "INSERT INTO mf_audit_trail(\n"
				+ "	              system_id, activity, user_code, date_upd, entity_id, \n"
				+ "	              client_seqno, projcode, pbl_id, doc_id, doc_no, remarks)\n"
				+ "	      VALUES ('PB', '" + activity + "', '" + user_code + "', NOW(), NULL, \n"
				+ "	              NULL, NULL, NULL, NULL, '" + bill_no + "','ntp_no:' ||'" + ntp_no
				+ "' || ' ' || 'bill_no:'||'" + bill_no + "'|| ' ' || 'rec_id:' || " + rec_id
				+ "|| ' ' || 'Posted by: ' || '" + user_code + "' );";

		db.executeUpdate(strsql, false);
		db.commit();

	}

	private void insertAudit_trail_rplf_header(String activity, String remarks, pgUpdate db) {

		String user_code = UserInfo.EmployeeCode;

		String sqlDetail = "INSERT INTO mf_audit_trail(\n"
				+ "	              system_id, activity, user_code, date_upd, entity_id, \n"
				+ "	              client_seqno, projcode, pbl_id, doc_id, doc_no, remarks)\n"
				+ "	      VALUES ('PV', '" + activity + "', '" + user_code + "', NOW(), NULL, \n"
				+ "	              NULL, NULL, NULL, NULL, '" + remarks + "', '" + remarks + "');";

		System.out.printf("SQL #1: %s", sqlDetail);
		db.executeUpdate(sqlDetail, false);

	}

	private void insertBackchargesLiq(pgUpdate db) {

		String entity_id1 = txtContractor.getText().trim();
		int rw = tblBC.getModel().getRowCount();

		int x = 0;

		while (x < rw) {

			Double for_liq_bc = 0.00;
			try {
				for_liq_bc = Double.parseDouble(modelBC.getValueAt(x, 4).toString());
			} catch (NullPointerException e) {
				for_liq_bc = 0.00;
			}
			String bc_rplf_no = modelBC.getValueAt(x, 0).toString();

			/*
			 * pgSelect jv = new pgSelect(); String sql
			 * ="select jv_no from co_house_repair_backcharges \n" +
			 * "where repair_no ='' \n" + "and  jv_no !=''  \n" +
			 * "and entity_id ='"+entity_id1+"' \n" +
			 * "and jv_no in (select jv_no from rf_jv_header where status_id ='P' and co_id ='"
			 * +co_id+"')"; jv.select(sql); if(jv != null) { jv_no = (String)
			 * jv.getResult()[0][0]; }
			 */

			if (for_liq_bc == 0.00) {
			} else

			{
				String sqlDetail = "INSERT into co_backcharge_liquidation values ( \n" + "'" + entity_id1 + "',  \n" + // 1
						"'" + co_id + "',  \n" + // 2
						"'" + co_id + "',  \n" + // 3
						"'" + bc_rplf_no + "',  \n" + // 4
						"'" + bc_rplf_no + "',  \n" + // 5
						"null,  \n" + // 6
						"'" + jv_no + "',  \n" + // 7
						"'" + for_liq_bc + "',  \n" + // 8
						"'" + billing_date + "',  \n" + // 9
						"null,  \n" + // 10
						"trim(to_char(" + bill_no + ",'0000000000')),  \n" + // 11
						"null,  \n" + // 12
						"null,  \n" + // 13
						"null,  \n" + // 14
						"'A',  \n" + // 15
						"'" + UserInfo.EmployeeCode + "',  \n" + // 16
						"'" + billing_date + "' , \n" + // 17
						"'' , \n" + // 18
						"null \n" + // 19

						")   ";

				System.out.printf("SQL #1: %s", sqlDetail);
				db.executeUpdate(sqlDetail, false);

			}

			x++;
		}
	}

	private void insertOtherDeductions(pgUpdate db) {

		String entity_id1 = txtContractor.getText().trim();
		int rw = tblOD.getModel().getRowCount() - 1;
		int x = 0;

		while (x <= rw) {
			String type_id = "";
			String contract_no = "";

			Double od_amt = Double.parseDouble(modelOD.getValueAt(x, 3).toString());
			String acct_id = modelOD.getValueAt(x, 1).toString();
			String od_remarks = modelOD.getValueAt(x, 4).toString();
			String ref_no = modelOD.getValueAt(x, 0).toString();
			try {
				type_id = modelOD.getValueAt(x, 6).toString().toString().trim();
			} catch (NullPointerException e) {
				type_id = "";
			}
			try {
				contract_no = modelOD.getValueAt(x, 7).toString().toString().trim();
			} catch (NullPointerException e) {
				contract_no = "";
			}
			if (od_amt == 0.00) {
				x++;
			} else {

				String sqlDetail = "INSERT into co_other_deduction values (" + "nextval('tf_co_other_ded_no'),  \n" + // 1
						"'" + entity_id1 + "',  \n" + // 2
						"" + od_amt + ",  \n" + // 3
						"'" + billing_date + "',  \n" + // 4
						"'" + od_remarks + "',  \n" + // 5
						"'" + jv_no + "',  \n" + // 6
						"trim(to_char(" + bill_no + ",'0000000000')),  \n" + // 7
						"'" + UserInfo.EmployeeCode + "',  \n" + // 8
						"'A',  \n" + // 9
						"'" + acct_id + "',  \n" + // 10
						"'" + ref_no + "',  \n" + // 11
						"'" + type_id + "',\n" + "'" + contract_no + "'" +

						")   ";
				System.out.println("Dumaan dito");
				System.out.printf("SQL #1: %s", sqlDetail);
				db.executeUpdate(sqlDetail, false);

			}

			x++;
		}

	}

	private void insertNewContractPrice(String ntp_no, pgUpdate db) {
		// pgUpdate db = new pgUpdate;

		Double tcp = Double.valueOf(txtContractPriceOrig.getText().trim().replace(",", "")).doubleValue();
		deduction_amt = (Double.valueOf(txtDAmount.getText().trim().replace(",", "")).doubleValue());
		double new_tcp = tcp - deduction_amt;
		// System.out.println("deduction_amt: "+deduction_amt);

		String sqlDetail = "UPDATE co_ntp_header set new_contract_price = " + new_tcp + " " + "where ntp_no = '"
				+ ntp_no + "' ";

		System.out.printf("SQL #1: %s", sqlDetail);
		System.out.printf("New TCP: %s", new_tcp);

		db.executeUpdate(sqlDetail, false);

	}

	private void inactivateRPLF_header(String drf, pgUpdate db) {

		String sqlDetail = "update rf_request_header " + "set status_id = 'I'," + "edited_by = '"
				+ UserInfo.EmployeeCode + "'," + "date_edited = now()   " + "where trim(rplf_no) = '" + drf + "' "
				+ "and co_id = '" + co_id + "' ";

		System.out.printf("SQL #1: %s", sqlDetail);
		db.executeUpdate(sqlDetail, false);
	}

	private void inactivatePV_header(String drf, pgUpdate db) {

		String sqlDetail = "update rf_pv_header " + "set status_id = 'I'," + "edited_by = '" + UserInfo.EmployeeCode
				+ "'," + "date_edited = now()   " + "where trim(pv_no) = '" + drf + "' " + "and co_id = '" + co_id
				+ "' ";

		System.out.printf("SQL #1: %s", sqlDetail);
		db.executeUpdate(sqlDetail, false);
	}

	private void inactivateBilling(String bill, pgUpdate db, String rec_id) {

		String sqlDetail = "update co_billing_detail \n" + "		set \n" + "		status_id = 'I', edited_by ='"
				+ UserInfo.EmployeeCode + "',\n" + "		date_edited = '"
				+ dateFormat.format(FncGlobal.dateFormat(FncGlobal.getDateSQL())) + "'\n"
				+ "		where case when status_id = '' and billing_type = '04' and billing_no = '0000000000'\n"
				+ "						then rec_id = '" + rec_id + "' and ntp_no = '" + txtNTP_no.getText() + "'\n"
				+ "					else trim(billing_no) = '" + bill + "' and co_id = '" + co_id + "' and ntp_no = '"
				+ txtNTP_no.getText() + "'\n" + "			  end";

		System.out.printf("inactivateBilling: %s", sqlDetail);
		db.executeUpdate(sqlDetail, false);
	}

	private void inactive_bc(String bill, pgUpdate db) {
		String strsql = "---inactive_bc---\n" + "update co_backcharge_liquidation  \n" + "set status_id='I', \n"
				+ "edited_by = '" + UserInfo.EmployeeCode + "'," + "date_edited = '"
				+ dateFormat.format(FncGlobal.dateFormat(FncGlobal.getDateSQL())) + "'" + "where bill_no='" + bill
				+ "' \n" + "and status_id='A' and bc_co_id = '" + co_id + "'";

		System.out.println("inactive_bc= " + strsql);
		db.executeUpdate(strsql, false);

	}

	// set values of variables
	private String setPartDesc(String billing_type, Double accomp_perc_new, Double accomp_perc_old, Double ret_fee,
			Double dp_reco, Double wtax) {

		if (billing_type.equals("01")) {
			part_desc = "DOWNPAYMENT for Contract# " + "" + lookupContractNo.getText().trim() + " " + "with RPLF No "
					+ "" + rplf_no + " \n" + "" + contr_name.replace("'", "''") + "  \n" + "re:Contract Amt " + "P"
					+ txtContractPriceOrig.getText().trim() + "  \n" + "";
		} else if (billing_type.equals("02")) {

			Double perc_now = accomp_perc_new - accomp_perc_old;

			part_desc = "Payment for " + getBillingNo();

			//
			if (getBillingNo() == 1 && accomp_perc_new != 100 && dp_amount != 0) {
				part_desc = part_desc + "st Progress Billing for Contract# ";
			} else if (getBillingNo() == 1 && accomp_perc_new != 100 && dp_amount == 0) {
				part_desc = part_desc + "st Progress Billing for Contract# ";
			} // added by erick bituen
			else if (getBillingNo() == 2 && accomp_perc_new != 100) {
				part_desc = part_desc + "nd Progress Billing for Contract# ";
			} else if (getBillingNo() == 3 && accomp_perc_new != 100) {
				part_desc = part_desc + "rd Progress Billing for Contract# ";
			} else if (getBillingNo() > 3 && accomp_perc_new != 100) {
				part_desc = part_desc + "th Progress Billing for Contract# ";
			} else if (accomp_perc_new == 100.00) {
				part_desc = "Final Progress Billing for Contract# ";
			}

			part_desc = part_desc + "" + lookupContractNo.getText().trim() + " " + "with RPLF No " + "" + rplf_no + " "
					+ "" + contr_name.replace("'", "''") + "  \n" + "re:Contract Amt 		 " + "P"
					+ txtContractPriceOrig.getText().trim() + "  \n" + "%completed to date : " + "" + accomp_perc_new
					+ "  \n" + "%completed this period : " + "" + df2.format(perc_now) + "  \n" + "Retention fee : "
					+ "" + df.format(ret_fee) + "  \n" + "DP Recoupment : " + "" + df.format(dp_reco) + "  \n"
					+ "WTax : " + "" + df.format(wtax) + "\n" + "";
		} else if (billing_type.equals("03")) {
			part_desc = "RETENTION PAYMENT for Contract# " + "" + lookupContractNo.getText().trim() + " "
					+ "with RPLF No " + "" + rplf_no + " \n" + "" + contr_name.replace("'", "''") + "  \n"
					+ "re:Contract Amt  " + "P" + txtContractPriceOrig.getText().trim() + "  \n" + "";
		}

		// PV remark on Backcharge
		// double backcharge_amt =
		// Double.parseDouble(modelProgBillMain.getValueAt(row,8).toString());
		if (bc_amt > 0) {
			part_desc = part_desc + "Backcharge Liq. Amount : " + nf.format(bc_amt);

			int row = tblBC.getRowCount();
			int r = 0;

			while (r < row) {
				String bc_rplf_no = modelBC.getValueAt(r, 0).toString();
				double bc_liq_amt = Double.parseDouble(modelBC.getValueAt(r, 4).toString());
				if (bc_liq_amt == 0) {
				} else {
					part_desc = part_desc + "\n" + "     RPLF No/JV N0. : " + bc_rplf_no + "   BackCharge Amt.: "
							+ nf.format(bc_liq_amt) + "\n";
				}

				r++;
			}
		}

		return part_desc;

	}

	private void setBillingAmountParticulars() {

		int rw = tblProgBillMain.getModel().getRowCount() - 1;

		accomp_perc = Double.parseDouble(modelProgBillMain.getValueAt(rw, 2).toString());
		gr_amt = Double.parseDouble(modelProgBillMain.getValueAt(rw, 3).toString());
		vat_amt = Double.parseDouble(modelProgBillMain.getValueAt(rw, 4).toString());
		wtx_amt = Double.parseDouble(modelProgBillMain.getValueAt(rw, 5).toString());
		dpr_amt = Double.parseDouble(modelProgBillMain.getValueAt(rw, 6).toString());
		ret_amt = Double.parseDouble(modelProgBillMain.getValueAt(rw, 7).toString());
		bc_amt = Double.parseDouble(modelProgBillMain.getValueAt(rw, 8).toString());
		od_amt = Double.parseDouble(modelProgBillMain.getValueAt(rw, 9).toString());
		net_amt = Double.parseDouble(modelProgBillMain.getValueAt(rw, 10).toString());
		exp_amt = gr_amt - vat_amt;

		System.out.printf("accomp_perc: " + accomp_perc + "\n");
		System.out.printf("gr_amt: " + gr_amt + "\n");
		System.out.printf("vat_amt: " + vat_amt + "\n");
		System.out.printf("wtx_amt: " + wtx_amt + "\n");
		System.out.printf("dpr_amt: " + dpr_amt + "\n");
		System.out.printf("ret_amt: " + ret_amt + "\n");
		System.out.printf("bc_amt: " + bc_amt + "\n");
		System.out.printf("od_amt: " + od_amt + "\n");
		System.out.printf("net_amt: " + net_amt + "\n");
		System.out.printf("exp_amt: " + exp_amt + "\n");

	}

	// Right-click operations
	public void openDRF() {

		RequestForPayment drf = new RequestForPayment();
		Home_ArcSystem.addWindow(drf);

		if (co_id.equals("")) {

		} else {
			RequestForPayment.co_id = co_id;
			RequestForPayment.company = company;
			RequestForPayment.lookupCompany.setValue(co_id);
			RequestForPayment.tagCompany.setTag(company);
			RequestForPayment.company_logo = company_logo;

			RequestForPayment.lblDRF_no.setEnabled(true);
			RequestForPayment.lookupDRF_no.setEnabled(true);
			RequestForPayment.lookupDRF_no.setLookupSQL(RequestForPayment.getDRF_no());

			RequestForPayment.btnAddNew.setEnabled(true);
			RequestForPayment.btnCancel.setEnabled(true);

			int column = tblProgBillMain.getSelectedColumn();
			int row = tblProgBillMain.getSelectedRow();
			String rplf = modelProgBillMain.getValueAt(row, column).toString().trim();

			if (rplf.equals("")) {

			} else {
				RequestForPayment.drf_no = rplf;
				RequestForPayment.lookupDRF_no.setValue(RequestForPayment.drf_no);

				RequestForPayment.setDRF_header(RequestForPayment.drf_no);
				RequestForPayment.displayDRF_details(RequestForPayment.modelDRF_part, RequestForPayment.rowHeaderDRF,
						RequestForPayment.modelDRF_part_total, RequestForPayment.drf_no);
				RequestForPayment.btnAddNew.setEnabled(false);
				RequestForPayment.btnRefresh.setEnabled(true);
				RequestForPayment.btnPreview.setEnabled(true);

				if (RequestForPayment.isPVcreated() == true) {
					RequestForPayment.btnEdit.setEnabled(false);
				} else {
					RequestForPayment.btnEdit.setEnabled(true);
				}

				RequestForPayment.mnidelete.setEnabled(false);
				RequestForPayment.mniaddrow.setEnabled(false);
				RequestForPayment.mnisetupPV.setEnabled(true);
			}
		}
	}

	public void openCV() {

		CheckVoucher chk_vchr = new CheckVoucher();
		Home_ArcSystem.addWindow(chk_vchr);

		if (co_id.equals("")) {
		} else {
			CheckVoucher.co_id = co_id;
			CheckVoucher.tagCompany.setTag(company);
			CheckVoucher.company = company;
			CheckVoucher.company_logo = company_logo;
			CheckVoucher.lookupCompany.setValue(co_id);

			CheckVoucher.lblDV_no.setEnabled(true);
			CheckVoucher.lookupDV_no.setEnabled(true);
			CheckVoucher.lookupDV_no.setLookupSQL(CheckVoucher.getDV_no(co_id));
			CheckVoucher.enableButtons(true, false, false, false, false, false, false, false, false, false);

			int column = tblProgBillMain.getSelectedColumn();
			int row = tblProgBillMain.getSelectedRow();
			String rplf = modelProgBillMain.getValueAt(row, column).toString().trim();

			if (!RequestForPayment.sql_getCVno(rplf, co_id).equals("")) {
				String cv_no = RequestForPayment.sql_getCVno(rplf, co_id);
				CheckVoucher.cv_no = cv_no;
				CheckVoucher.refresh_fields();
				CheckVoucher.refresh_tablesMain();
				CheckVoucher.setDV_header(cv_no);
				CheckVoucher.lookupDV_no.setValue(cv_no);

				String status = RequestForPayment.sql_getCVstatus(cv_no, co_id);
				if (status.equals("A")) {
					CheckVoucher.enableButtons(false, true, true, true, true, false, true, false, false, true);
					CheckVoucher.displayDV_account_entries(CheckVoucher.modelDV_acct_entries,
							CheckVoucher.rowHeaderDV_acct_entries, CheckVoucher.modelDV_accttotal, cv_no);
					CheckVoucher.displayDV_pv(CheckVoucher.modelDV_pv, CheckVoucher.rowHeaderDV_pv,
							CheckVoucher.modelDV_pvtotal, cv_no);
				} else if (status.equals("F")) {
					CheckVoucher.enableButtons(false, false, true, true, true, false, false, true, true, true);
					CheckVoucher.displayDV_account_entries(CheckVoucher.modelDV_acct_entries,
							CheckVoucher.rowHeaderDV_acct_entries, CheckVoucher.modelDV_accttotal, cv_no);
					CheckVoucher.displayDV_pv(CheckVoucher.modelDV_pv, CheckVoucher.rowHeaderDV_pv,
							CheckVoucher.modelDV_pvtotal, cv_no);
				} else if (status.equals("P")) {
					CheckVoucher.enableButtons(false, false, true, true, true, false, false, false, false, false);
					CheckVoucher.displayDV_account_entries(CheckVoucher.modelDV_acct_entries,
							CheckVoucher.rowHeaderDV_acct_entries, CheckVoucher.modelDV_accttotal, cv_no);
					CheckVoucher.displayDV_pv(CheckVoucher.modelDV_pv, CheckVoucher.rowHeaderDV_pv,
							CheckVoucher.modelDV_pvtotal, cv_no);
				} else {
					CheckVoucher.enableButtons(false, false, false, true, true, false, false, false, false, false);
				}

				CheckVoucher.mnidelete.setEnabled(false);
				CheckVoucher.mniaddrow.setEnabled(false);
			}
		}

	}

	public void openPV() {

		if (Home_ArcSystem.isNotExisting("PayableVoucher")) {
			PayableVoucher pv = new PayableVoucher();
			Home_ArcSystem.addWindow(pv);
		}

		if (co_id.equals("")) {

		} else {
			PayableVoucher.co_id = co_id;
			PayableVoucher.company = company;
			PayableVoucher.lookupCompany.setValue(co_id);
			PayableVoucher.tagCompany.setTag(company);
			PayableVoucher.company_logo = company_logo;
			PayableVoucher.btnAddNew.setEnabled(true);
			PayableVoucher.btnCancel.setEnabled(true);

			PayableVoucher.lblPV_no.setEnabled(true);
			PayableVoucher.lookupPV_no.setEnabled(true);
			// PayableVoucher.lookupPV_no.setEditable(true);
			PayableVoucher.lookupPV_no.setLookupSQL(PayableVoucher.getPV_no());
			PayableVoucher.lookupDRF_no.setLookupSQL(PayableVoucher.getDRF_no());
			PayableVoucher.insertAudit_trail2("Open-PV from Contractors", PayableVoucher.pv_no);

			int column = tblProgBillMain.getSelectedColumn();
			int row = tblProgBillMain.getSelectedRow();
			String rplf = modelProgBillMain.getValueAt(row, column).toString().trim();

			if (!rplf.equals("")) {

				PayableVoucher.refresh_fields();

				PayableVoucher.pv_no = rplf;
				PayableVoucher.lookupPV_no.setValue(rplf);
				PayableVoucher.setPV_header(rplf);
				PayableVoucher.displayPV_details(PayableVoucher.modelPV_account, PayableVoucher.rowHeaderPV_account,
						PayableVoucher.modelPV_accounttotal, rplf);
				PayableVoucher.displayPV_subsidiaryledgers(PayableVoucher.modelPV_SL, PayableVoucher.rowHeaderPV_SL,
						PayableVoucher.modelPV_SL_total, rplf);
				PayableVoucher.btnAddNew.setEnabled(false);
				PayableVoucher.btnRefresh.setEnabled(true);

				// set particulars
				Integer w = 1;
				Integer line_count = PayableVoucher.sql_getDRF_line_count(rplf);
				String part_desc;
				String particulars = "";

				// COMMENTED BY LESTER 2023-05-25 FOR REPEAT DISPLAY OF REMARKS

				// while (w<=line_count){
				// part_desc = PayableVoucher.getDRF_particulars(w, rplf).toString().trim();
				// particulars = ""+particulars+" "+part_desc+" \n" ;
				//
				// w++;
				// }
				// PayableVoucher.txtDRFRemark.setText(particulars);

				if (PayableVoucher.getPV_status(rplf).equals("P") && PayableVoucher.lookupPV_no.isEnabled()) {
					PayableVoucher.btnSave.setText("Post");
					PayableVoucher.btnSave.setActionCommand("Post");
					PayableVoucher.enable_buttons(false, false, false, true, true, true);
					PayableVoucher.mniInactivate.setEnabled(false);
				} else if (PayableVoucher.getPV_status(rplf).equals("F")) {
					PayableVoucher.btnSave.setText("Post");
					PayableVoucher.btnSave.setActionCommand("Post");
					PayableVoucher.enable_buttons(false, false, true, true, true, true);
					PayableVoucher.mniInactivate.setEnabled(false);
				} else if (PayableVoucher.getPV_status(rplf).equals("A")) {
					PayableVoucher.btnSave.setText("Tag");
					PayableVoucher.btnSave.setActionCommand("Tag");
					PayableVoucher.enable_buttons(false, true, true, true, true, true);
					PayableVoucher.mniInactivate.setEnabled(true);
				}

				PayableVoucher.modelPV_account.setEditable(false);
				PayableVoucher.tblPV_account.setEditable(false);
				PayableVoucher.btnPreview.setEnabled(true);
			}
		}
	}

	public void editAmount() {

		int c = tblProgBillMain.getSelectedColumn();
		int r = tblProgBillMain.getSelectedRow();

		clearRowAmount(r);

		int scanOption = JOptionPane.showOptionDialog(getContentPane(), pnlEditAmount, "Edit Amount / Percent",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, new Object[] {}, null);

		System.out.printf("ScanOption: %s", scanOption);

		if (scanOption == JOptionPane.CLOSED_OPTION) {
			try {
				double net_amt = Double.valueOf(txteditamount.getText().trim().replace(",", "")).doubleValue();
				modelProgBillMain.setValueAt(net_amt, r, c);
				computeBillAmounts();
				validatePerc();
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			}

			System.out.printf("Value of txteditamount : %s", txteditamount.getText());
		} // CLOSED_OPTION
	}

	public void adddeduction(pgUpdate db) {

		int scanOption = JOptionPane.showOptionDialog(getContentPane(), pnlAddDeductions, "Add Deductives",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, new Object[] {}, null);

		System.out.printf("ScanOption: %s", scanOption);

		if (scanOption == JOptionPane.CLOSED_OPTION) {

			try {
				deduction_amt = (Double.valueOf(txtDAmount.getText().trim().replace(",", "")).doubleValue());
				less_deductives = (Double.valueOf(txtContractPriceOrig.getText().trim().replace(",", "")).doubleValue())
						- deduction_amt; // Double.parseDouble(txtDAmount.getValue().toString());
				txtContractPriceNew.setText(nf.format(less_deductives));

			} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			}
			if (deduction_amt == 0.0) {
			} else {
				if (JOptionPane.showConfirmDialog(getTopLevelAncestor(), "Are you sure you want to save deduction?",
						"Save", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					Double tcp = Double.valueOf(txtContractPriceOrig.getText().trim().replace(",", "")).doubleValue();
					// deduction_amt =
					// (Double.valueOf(txtDAmount.getText().trim().replace(",","")).doubleValue());
					double new_tcp = tcp - deduction_amt;
					String sqlDetail = "UPDATE co_ntp_header set new_contract_price = " + new_tcp + " "
							+ "where ntp_no = '" + ntp_no + "' ";

					System.out.printf("SQL #1: %s", sqlDetail);
					System.out.printf("New TCP: %s", new_tcp);
					System.out.println("Dumaan dito*******");
					db.executeUpdate(sqlDetail, false);
					db.commit();
				}
			}

			enableButtons(true, false, true, true, true, false);

			System.out.printf("Value of Deductives : %s", deduction_amt);
			System.out.printf("TCP less Deductives : %s", less_deductives);

		} // CLOSED_OPTION
	}
	/*
	 * public void editBillingType(){
	 * 
	 * int c = tblProgBillMain.getSelectedColumn(); int r =
	 * tblProgBillMain.getSelectedRow();
	 * 
	 * clearRowAmount(r);
	 * 
	 * int scanOption = JOptionPane.showOptionDialog(getContentPane(), pnlEditBType,
	 * "Edit Billing Type", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
	 * null, new Object[] {}, null);
	 * 
	 * if ( scanOption == JOptionPane.CLOSED_OPTION ) { try { next_billing_type =
	 * getNextBillingType(txtNTP_no.getText().trim()); String next_billing_type =
	 * String.valueOf(txtNTP_no.getText().trim().replace(",","")).doubleValue();
	 * modelProgBillMain.setValueAt(net_amt, r, c); computeBillAmounts();
	 * validatePerc(); } catch ( java.lang.ArrayIndexOutOfBoundsException e ) {} }
	 * // CLOSED_OPTION }
	 */

	public void overrideAmount() {

		int c = tblProgBillMain.getSelectedColumn();
		int r = tblProgBillMain.getSelectedRow();

		int scanOption = JOptionPane.showOptionDialog(getContentPane(), pnlEditAmount, "Edit Amount / Percent",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, new Object[] {}, null);

		if (scanOption == JOptionPane.CLOSED_OPTION) {
			try {
				double net_amt = Double.valueOf(txteditamount.getText().trim().replace(",", "")).doubleValue();
				modelProgBillMain.setValueAt(net_amt, r, c);
				computeBillAmounts();
				validatePerc();
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			}
		} // CLOSED_OPTION
	}

	/*
	 * public void editBillingType(){
	 * 
	 * int c = tblProgBillMain.getSelectedColumn(); int r =
	 * tblProgBillMain.getSelectedRow();
	 * 
	 * clearRowAmount(r);
	 * 
	 * int scanOption = JOptionPane.showOptionDialog(getContentPane(), pnlEditBType,
	 * "Select Billing Type", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
	 * null, new Object[] {}, null);
	 * 
	 * if ( scanOption == JOptionPane.OK_OPTION ) { try { selected_billing_type =
	 * (Integer) cmbBillingType.getSelectedIndex();
	 * modelProgBillMain.setValueAt(selected_billing_type, r, c);
	 * getNextBillingTypeTKO(ntp_no); } catch (
	 * java.lang.ArrayIndexOutOfBoundsException e ) {} } // CLOSED_OPTION }
	 */
	public void openBC_PV(String bc_rplf_no) {

		PayableVoucher pv = new PayableVoucher();
		Home_ArcSystem.addWindow(pv);

		if (co_id.equals("")) {

		} else {
			PayableVoucher.co_id = co_id;
			PayableVoucher.company = company;
			PayableVoucher.lookupCompany.setValue(co_id);
			PayableVoucher.tagCompany.setTag(company);
			PayableVoucher.company_logo = company_logo;
			PayableVoucher.btnAddNew.setEnabled(true);
			PayableVoucher.btnCancel.setEnabled(true);

			PayableVoucher.lblPV_no.setEnabled(true);
			PayableVoucher.lookupPV_no.setEnabled(true);
			PayableVoucher.lookupPV_no.setLookupSQL(PayableVoucher.getPV_no());
			PayableVoucher.lookupDRF_no.setLookupSQL(PayableVoucher.getDRF_no());

			if (!bc_rplf_no.equals("")) {

				PayableVoucher.refresh_fields();

				PayableVoucher.pv_no = bc_rplf_no;
				PayableVoucher.lookupPV_no.setValue(bc_rplf_no);

				PayableVoucher.setPV_header(bc_rplf_no);
				PayableVoucher.displayPV_details(PayableVoucher.modelPV_account, PayableVoucher.rowHeaderPV_account,
						PayableVoucher.modelPV_accounttotal, bc_rplf_no);
				PayableVoucher.displayPV_subsidiaryledgers(PayableVoucher.modelPV_SL, PayableVoucher.rowHeaderPV_SL,
						PayableVoucher.modelPV_SL_total, bc_rplf_no);
				PayableVoucher.btnAddNew.setEnabled(false);
				PayableVoucher.btnRefresh.setEnabled(true);

				// set particulars
				Integer w = 1;
				Integer line_count = PayableVoucher.sql_getDRF_line_count(bc_rplf_no);
				String part_desc;
				String particulars = "";

				// COMMENTED BY LESTER 2023-05-25 FOR REPEAT DISPLAY OF REMARKS
				// while (w<=line_count){
				// part_desc = PayableVoucher.getDRF_particulars(w,
				// bc_rplf_no).toString().trim();
				// particulars = ""+particulars+" "+part_desc+" \n" ;
				//
				// w++;
				// }
				// PayableVoucher.txtDRFRemark.setText(particulars);

				if (PayableVoucher.getPV_status(bc_rplf_no).equals("P") && PayableVoucher.lookupPV_no.isEnabled()) {
					PayableVoucher.btnSave.setText("Post");
					PayableVoucher.btnSave.setActionCommand("Post");
					PayableVoucher.enable_buttons(false, false, false, true, true, true);
					PayableVoucher.mniInactivate.setEnabled(false);
				} else if (PayableVoucher.getPV_status(bc_rplf_no).equals("F")) {
					PayableVoucher.btnSave.setText("Post");
					PayableVoucher.btnSave.setActionCommand("Post");
					PayableVoucher.enable_buttons(false, false, true, true, true, true);
					PayableVoucher.mniInactivate.setEnabled(false);
				} else if (PayableVoucher.getPV_status(bc_rplf_no).equals("A")) {
					PayableVoucher.btnSave.setText("Tag");
					PayableVoucher.btnSave.setActionCommand("Tag");
					PayableVoucher.enable_buttons(false, true, true, true, true, true);
					PayableVoucher.mniInactivate.setEnabled(true);
				}

				PayableVoucher.modelPV_account.setEditable(false);
				PayableVoucher.tblPV_account.setEditable(false);
				PayableVoucher.btnPreview.setEnabled(true);
			}
		}
	}

	public void editDate() {

		// int c = tblProgBillMain.getSelectedColumn();
		int r = tblProgBillMain.getSelectedRow();

		clearRowAmount(r);

		int scanOption = JOptionPane.showOptionDialog(getContentPane(), pnlEditDate, "Edit Date",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, new Object[] {}, null);

		if (scanOption == JOptionPane.CLOSED_OPTION) {
			try {
				Date editdate = dateSched.getDate();
				modelProgBillMain.setValueAt(dateFormat.format(editdate), r, 1);
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			}
		} // CLOSED_OPTION
	}

	public void addbilling_remarks() {

		// int c = tblProgBillMain.getSelectedColumn();
		int r = tblProgBillMain.getSelectedRow();

		// clearRowAmount(r);

		int scanOption = JOptionPane.showOptionDialog(getContentPane(), pnlremarks, "Add Remakrs",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, new Object[] {}, null);

		if (scanOption == JOptionPane.CLOSED_OPTION) {
			try {
				String bill_remarks = txtremarks.getText();
				modelProgBillMain.setValueAt(bill_remarks, r, 15);
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			}
		} // CLOSED_OPTION
	}

	private void post_adjustment() {
		int rw = tblProgBillMain.getSelectedRow();
		String bill_no = modelProgBillMain.getValueAt(rw, 11).toString();
		String bill_type = modelProgBillMain.getValueAt(rw, 0).toString();
		Integer rec_id = (Integer) modelProgBillMain.getValueAt(rw, 16);

		if (JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to post the adjustment?",
				"Post Adjustment", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

			execute_post_adjustment(rec_id, txtNTP_no.getText(), bill_type, lookupCompany.getValue());

			insertAudit_trail_post_adjustment("Post Adjustment - Progress Billing", rec_id, txtNTP_no.getText(),
					bill_no);
			displayProgressBilling(modelProgBillMain, rowHeaderProgBillMain, modelProgBillTotal, txtNTP_no.getText());
		}
	}

	private void deleteBilling() {

		if (JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to delete this transaction?",
				"Delete", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			executeDelete();
		}

		else {
		}

	}

	// check, validate
	private Boolean checkNetAmount() {

		boolean x = false;

		int rw = tblProgBillMain.getModel().getRowCount();
		double net_amt = Double.parseDouble(modelProgBillMain.getValueAt(rw - 1, 10).toString());
		if (net_amt >= 0) {
			x = true;
		} // 03-08-2016 - I included 0.00 for billing whose net amount is completely
			// offset by BC/Other liquidation
		else if (net_amt < 0) {
			x = false;
		} else {
			x = false;
		}

		return x;

	}

	private Boolean checkbackcharge(String jv_no) {
		/*
		 * boolean x = false; pgSelect db = new pgSelect();
		 * 
		 * String sql =
		 * "select jv_no from rf_jv_header where jv_no = '"+jv_no+"' and status_id ='P'"
		 * ; db.select(sql);
		 * 
		 * if(db != null) { x=true; }
		 */
		return FncGlobal.GetBoolean(
				"select exists(select jv_no from rf_jv_header where jv_no = '" + jv_no + "' and status_id ='P'); ");// returns
																													// true
																													// if
																													// there
																													// is
																													// returned
																													// row
	}

	private void validatePerc() {

		int c = tblProgBillMain.getSelectedColumn();
		int r = tblProgBillMain.getSelectedRow();
		int rw = tblProgBillMain.getModel().getRowCount();

		double accomp_old = 0;
		if (rw == 1) {
			accomp_old = Double.valueOf(txteditamount.getText().trim().replace(",", "")).doubleValue();
		} else {
			if (txtNTP_no.getText().equals("001281")) {// added by lester 2020-06-30
				accomp_old = Double.parseDouble(modelProgBillMain.getValueAt(r - 2, 2).toString());
			} else {
				accomp_old = Double.parseDouble(modelProgBillMain.getValueAt(r - 1, 2).toString()); // original code
			}
		}

		String bill_type = modelProgBillMain.getValueAt(r, 0).toString();
		double accomp_new = Double.parseDouble(modelProgBillMain.getValueAt(r, 2).toString());
		BigDecimal accomp_old_bd = new BigDecimal(accomp_old);

		if (c == 2 && accomp_new > 100 && bill_type.equals("02")) {
			modelProgBillMain.setValueAt(accomp_old_bd, r, c);
			JOptionPane.showMessageDialog(getContentPane(), "Must be less than 100", "Accomplishment %",
					JOptionPane.WARNING_MESSAGE);
		}

		if (c == 2 && accomp_new < accomp_old && bill_type.equals("02") && accomp_new != 0) {
			if (txtNTP_no.getText().equals("001827")) {// Added by Erick 2021-11-10
				modelProgBillMain.setValueAt(accomp_new, r, c);
			} else {
				modelProgBillMain.setValueAt(accomp_old_bd, r, c);
				JOptionPane.showMessageDialog(getContentPane(), "Must be greater than " + accomp_old,
						"Accomplishment %", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private Boolean checkTransDate() {

		Boolean b = false;

		int rw = tblProgBillMain.getModel().getRowCount();
		int x = 0;
		String rw_date = null;

		while (x < rw) {

			try {
				rw_date = modelProgBillMain.getValueAt(x, 1).toString();
			} catch (NullPointerException e) {
				rw_date = null;
			}

			if (dateFormat.format(billing_dte).equals(rw_date)) {
				b = true;
				break;
			} else {
				b = false;
			}

			System.out.printf("billing_date :" + dateFormat.format(billing_dte));
			System.out.printf("rw_date :" + rw_date);

			x++;
		}

		return b;

	}

	public static String sql_getCompanyLogo() {

		String a = "";

		String SQL = "select company_logo from mf_company \n" + "where co_id = '" + co_id + "' ";

		System.out.printf("SQL #1: sql_getCompanyLogo", SQL);
		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if ((String) db.getResult()[0][0] == null || db.getResult()[0][0].equals("null")) {
				a = "";
			} else {
				a = (String) db.getResult()[0][0];
			}

		} else {
			a = "";
		}

		return a;
	}

	private static BigDecimal getGrossAmount(Double new_price, Double accomp_new, Double accomp_old) {// compute gross
																										// amt

		BigDecimal gross_amt = BigDecimal.valueOf(0.00);

		String SQL = "SELECT * FROM compute_gross_amount('" + new_price + "', '" + accomp_new + "', '" + accomp_old
				+ "')";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if ((BigDecimal) db.getResult()[0][0] == BigDecimal.valueOf(0.00)) {
				gross_amt = BigDecimal.valueOf(0.00);
			} else {
				gross_amt = (BigDecimal) db.getResult()[0][0];
			}

		} else {
			gross_amt = BigDecimal.valueOf(0.00);
		}

		return gross_amt;

	}

	private static BigDecimal getVatAmount(Double grossAmt, Double vat_rate) {// compute vat amount

		BigDecimal vat_amt = BigDecimal.valueOf(0.00);

		String SQL = "SELECT * FROM compute_vat_amount('" + grossAmt + "', '" + vat_rate + "')";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if ((BigDecimal) db.getResult()[0][0] == BigDecimal.valueOf(0.00)) {
				vat_amt = BigDecimal.valueOf(0.00);
			} else {
				vat_amt = (BigDecimal) db.getResult()[0][0];
			}

		} else {
			vat_amt = BigDecimal.valueOf(0.00);
		}

		return vat_amt;

	}

	private static BigDecimal getWtaxAmount(Double grossAmt, Double vat_rate, Double tax_rate, String ntp_no) {// compute
																												// wtax
																												// amount

		BigDecimal wtax_amt = BigDecimal.valueOf(0.00);

		String SQL = "SELECT * FROM compute_wtax_amount('" + grossAmt + "','" + vat_rate + "', (case when '" + ntp_no
				+ "'='001175' then 0.15 else '" + tax_rate + "' end))";
		System.out.printf("grossAmt= %s", grossAmt);
		System.out.printf("tax_rate= %s", tax_rate);
		System.out.printf("vat_rate= %s", grossAmt);
		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if ((BigDecimal) db.getResult()[0][0] == BigDecimal.valueOf(0.00)) {
				wtax_amt = BigDecimal.valueOf(0.00);
			} else {
				wtax_amt = (BigDecimal) db.getResult()[0][0];
			}

		} else {
			wtax_amt = BigDecimal.valueOf(0.00);
		}

		return wtax_amt;

	}

	private static BigDecimal getNetAmount(Double grossAmt, Double wtaxAmt, Double dpRec, Double retentionAmt,
			Double bc_amt, Double od_amt) {// compute net amount

		BigDecimal net_amt = BigDecimal.valueOf(0.00);

		String SQL = "SELECT * FROM compute_pv_amount('" + grossAmt + "', '" + wtaxAmt + "', '" + retentionAmt + "', '"
				+ dpRec + "', '" + bc_amt + "', '" + od_amt + "')";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if ((BigDecimal) db.getResult()[0][0] == BigDecimal.valueOf(0.00)) {
				net_amt = BigDecimal.valueOf(0.00);
			} else {
				net_amt = (BigDecimal) db.getResult()[0][0];
			}

		} else {
			net_amt = BigDecimal.valueOf(0.00);
		}

		return net_amt;

	}

	private static BigDecimal getRetAmount(Double grossAmt) {// compute retention amt

		BigDecimal ret_amt = BigDecimal.valueOf(0.00);

		String SQL = "SELECT * FROM compute_retention_amount('" + grossAmt + "')";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if ((BigDecimal) db.getResult()[0][0] == BigDecimal.valueOf(0.00)) {
				ret_amt = BigDecimal.valueOf(0.00);
			} else {
				ret_amt = (BigDecimal) db.getResult()[0][0];
			}

		} else {
			ret_amt = BigDecimal.valueOf(0.00);
		}

		return ret_amt;

	}

	private void insertAudit_trail(String activity, String remarks, pgUpdate db) {

		String user_code = UserInfo.EmployeeCode;

		String sqlDetail = "INSERT INTO mf_audit_trail(\n"
				+ "	              system_id, activity, user_code, date_upd, entity_id, \n"
				+ "	              client_seqno, projcode, pbl_id, doc_id, doc_no, remarks)\n"
				+ "	      VALUES ('CB', '" + activity + "', '" + user_code + "', NOW(), NULL, \n"
				+ "	              NULL, NULL, NULL, NULL, NULL, '" + remarks + "');";

		System.out.printf("SQL #1: %s", sqlDetail);
		db.executeUpdate(sqlDetail, false);

	}

	private static BigDecimal getDpRecoupAmount(Double dp_amount, Double accomp_new, Double accomp_old, String ntp) {// compute
																														// dp
																														// recoupment
																														// amt

		BigDecimal dpRec = BigDecimal.valueOf(0.00);
		double not_released_dp = 0.00;

		if (ntp.equals("001290") // added by Erick Bituen DP was cancelled. DCRF 1307
				|| ntp.equals("001328") // added by Erick Bituen DP was not released. DCRF 1358
				|| ntp.equals("001446")) {// added by Erick Bituen DP was not released. DCRF 1390

			String SQL = "SELECT * FROM compute_dprecoupment_amount('" + not_released_dp + "', '" + accomp_new + "', '"
					+ accomp_old + "')";
			pgSelect db = new pgSelect();
			db.select(SQL);

			if (db.isNotNull()) {
				if ((BigDecimal) db.getResult()[0][0] == BigDecimal.valueOf(0.00)) {
					dpRec = BigDecimal.valueOf(0.00);
				} else {
					dpRec = (BigDecimal) db.getResult()[0][0];
				}

			} else {
				dpRec = BigDecimal.valueOf(0.00);
			}

			return dpRec;

		} else {
			String SQL = "SELECT * FROM compute_dprecoupment_amount('" + dp_amount + "', '" + accomp_new + "', '"
					+ accomp_old + "')";
			pgSelect db = new pgSelect();
			db.select(SQL);

			if (db.isNotNull()) {
				if ((BigDecimal) db.getResult()[0][0] == BigDecimal.valueOf(0.00)) {
					dpRec = BigDecimal.valueOf(0.00);
				} else {
					dpRec = (BigDecimal) db.getResult()[0][0];
				}

			} else {
				dpRec = BigDecimal.valueOf(0.00);
			}

			return dpRec;

		}

		/*
		 * pgSelect db = new pgSelect(); db.select(SQL);
		 * 
		 * if(db.isNotNull()){ if((BigDecimal) db.getResult()[0][0]==
		 * BigDecimal.valueOf(0.00)) {dpRec = BigDecimal.valueOf(0.00);} else {dpRec =
		 * (BigDecimal) db.getResult()[0][0]; }
		 * 
		 * }else{ dpRec = BigDecimal.valueOf(0.00); }
		 * 
		 * return dpRec;
		 */

	}

	private void popupNotesFromNTP(String ntp_no) {
		pgSelect db = new pgSelect();
		String sql = "select coalesce(trim(remarks),'') from co_ntp_header where trim(ntp_no) = trim('" + ntp_no + "')";
		db.select(sql);

		if (db.isNotNull()) {
			String note = (String) db.getResult()[0][0];
			if (!note.equals("")) {
				JOptionPane.showMessageDialog(null, note, "NOTES!", JOptionPane.PLAIN_MESSAGE);
				btnViewNote.setVisible(true);
			}
		}

	}

}