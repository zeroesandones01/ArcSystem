
package Accounting.Disbursements;

import interfaces._GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXFormattedTextField;
import org.jdesktop.swingx.JXTextField;

import tablemodel.modelDRF_particulars;
import tablemodel.modelDRF_particulars_total;
import Accounting.GeneralLedger.JournalVoucher;
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
import Reports.Accounting.DRFprooflist;
import TaxesAndPermits.Form2307_Monitoring;

import com.toedter.calendar.JTextFieldDateEditor;
import components.JTBorderFactory;
import components._JInternalFrame;
import components._JScrollPaneMain;
import components._JScrollPaneTotal;
import components._JTabbedPane;
import components._JTableMain;
import components._JTableTotal;
import components._JTagLabel;

public class DisbursementRequestForm extends _JInternalFrame implements _GUI, ActionListener, MouseListener, KeyListener {

	private static final long serialVersionUID = -3061284418918863916L;
	protected static final Home_ArcSystem Home_ArcSystem = null;
	static String title = "Disbursement Request Form (Request for Payment)";
	static Dimension SIZE = new Dimension(1000, 600);
	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");

	private JPanel pnlMain;
	private JPanel pnlNorth;
	private JPanel pnlSouth;
	private JPanel pnlDRFDtl_1;
	private JPanel pnlDRFDtl_2;
	private JPanel pnlDRFDtl_1a;
	private JPanel pnlDRFDtl_1b;
	private JPanel pnlTable;
	private JPanel pnlComp;
	private JPanel pnlComp_a;
	private JPanel pnlComp_a1;
	private JPanel pnlComp_a2;
	private JPanel pnlDRF;
	private JPanel pnlDRF_a;
	private JPanel pnlDRF_a1;
	private JPanel pnlDRF_a2;
	private JPanel pnlDRF_a2_1;
	private JPanel pnlDRF_a2_2;
	private JPanel pnlDRF_particular;
	private JPanel pnlDRFDtl;
	private JPanel pnlDRF_a2_3;
	private JPanel pnlDRFInfo;
	private JPanel pnlDRFInfo_1;
	private JPanel pnlDRFDtl_2a;
	private JPanel pnlDRFDtl_2b;
	private JPanel pnlDate;
	private JPanel pnlDRF_Particulars;
	private JPanel pnlDRF_OtherDetails;
	private JPanel pnlDRF_ReqRemarks;


	public static JLabel lblCompany;
	private static JLabel lblDRFDate;
	private static JLabel lblDueDate;
	private static JLabel lblPV_no;
	public static JLabel lblDRF_no;
	private static JLabel lblStatus;
	private static JLabel lblRequestType;
	private static JLabel lblPayee;
	private static JLabel lblAvailer;
	private static JLabel lblPayeeType;
	private static JLabel lblDate;
	private static JLabel lblPaymentType;
	private static JLabel lblDateEdited;
	private static JLabel lblDiv;


	public static _JLookup lookupCompany;
	private static _JLookup lookupPV_no;
	public static _JLookup lookupDRF_no;
	private static _JLookup lookupRequestType;
	private static _JLookup lookupPayee;
	private static _JLookup lookupAvailer;
	private static _JLookup lookupPayeeType;
	private static _JLookup lookupPaymentType;
	private static _JLookup lookupDiv;

	private static _JScrollPaneMain scrollDRF_part;
	private static _JScrollPaneTotal scrollDRF_part_total;
	private static JScrollPane scpDRFOtherDetails;
	private static JScrollPane scpDRFParticulars;
	private static JScrollPane scpDRFReqRemarks;

	public static modelDRF_particulars modelDRF_part;
	public static modelDRF_particulars_total modelDRF_part_total;
	public static _JTableTotal tblDRF_part_total;
	public static _JTableMain tblDRF_part;
	public static JList<Integer> rowHeaderDRF;
	private static _JTabbedPane tabCenter;

	public static _JTagLabel tagCompany;
	private static _JTagLabel tagReqType;
	private static _JTagLabel tagPayee;
	private static _JTagLabel tagAvailer;
	private static _JTagLabel tagPayeeType;
	private static _JTagLabel tagPayType;
	//	private static _JTagLabel tagDetail;
	private static _JTagLabel tagAvailerDiv;

	private static JButton btnSave;
	public static JButton btnCancel;
	public static JButton btnAddNew;
	public static JButton btnSubmitRet;
	public static JButton btnEdit;
	private JButton btnOK;

	Border lineBorder = BorderFactory.createLineBorder(Color.GRAY);

	private static _JDateChooser dteDueDate;
	private static _JDateChooser dteRefDate;
	private static _JDateChooser dteEdited;
	private static _JDateChooser dteDRFDate;

	private static JXTextField txtStatus;
	private static JTextArea txtDRFParticulars;
	private static JTextArea txtDRFReqRemarks;
	private static JTextArea txtDRFAttachments;
	private static JTextArea txtDRFOtherDetails;


	static NumberFormat nf = new DecimalFormat("###,###,###,##0.00");
	protected static DecimalFormat df = new DecimalFormat("#,##0.00");
	protected static Font dialog11Bold = new Font("DIALOG", Font.BOLD, 11);
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	private JPopupMenu menu;
	private JPopupMenu menu2;
	public static JMenuItem mnidelete;
	public static JMenuItem mniaddrow;
	public static JMenuItem mniopenPV;
	private JMenuItem mniopenCV;
	private JMenuItem mniopenDP;
	public static JMenuItem mnisetupPV;
	private JMenuItem mnisetupDRFprooflist;
	//	private static JMenuItem mnicopy;
	//	private static JMenuItem mnipaste;
	private JMenuItem mnisetup2307;

	public static String drf_no = ""; 
	public static String co_id = "";
	public static String payee_id = ""; 
	public static String user_id = UserInfo.EmployeeCode; 
	String availer_id = "";
	Double tax_rate = 0.00;
	String tax_id = "";
	String pay_type_id = "";
	String lineno = "";
	static String pay_type_name = "";
	public static String company = "";
	public static String company_logo;
	private static String item = "";
	Boolean is_payee_vatable = false;
	private static JMenuItem mniwriteoff;
	private JMenuItem mniInactivate;
	//	private JMenuItem mniEditAmount;
	private JPanel pnlEditAmount;
	private JLabel lblEditAmount;
	private _JXFormattedTextField txteditamount;

	private JPanel pnlDRF_Attachments;
	private JScrollPane scpDRFAttachments;
	protected String[] wtax;
	private JMenuItem mniRefresh;
	private static String allowedAccess;

	private static final double DEFAULT_VAT_RATE = 12.00;
	private static final double DEFAULT_TAX_RATE = 0.00;
	private static final double DEFAULT_AMOUNT = 0.00;

	//	static String selectedAccess = "User";
	private static String user; 

	public static JButton btnPreview;
	
	private static String placeholder_particulars = "Input a short description of the request or transaction.";
	private static String placeholder_other_details = "Input other details of the request or transaction.";
	private static String placeholder_req_remarks = "Input remarks for the request or transaction.";
	private static String placeholder_attachments = "PR# / PO# / RR# / Quotation, etc.";

	
	

	public DisbursementRequestForm() {
		super(title, true, true, true, true);

		if (UserInfo.Department.equals("009") || UserInfo.Department.equals("010") || UserInfo.Department.equals("002")) {
			allowedAccess = "Accounting"; 
		} else {
			allowedAccess = "User"; 
		}

		//		// Check if the user belongs to a Accounting / Finance / Admin Dept
		//		if (UserInfo.Department.equals("009") || UserInfo.Department.equals("010") || UserInfo.Department.equals("002")) {
		//		    
		//		    // Retrieve the selected access for the user
		//		    selectedAccess = getSelectedAccess(); 
		//		    
		//		    // If no access has been selected, apply default access
		//		    if (selectedAccess.equals("No access selected")) {
		//		        // Show a warning message indicating that no access was selected
		//		        showWarningMessage("No access selected. Default access will be applied", "Access");
		//		        
		//		        // Set default access to "User" and initialize the GUI
		//		        selectedAccess = "User"; 
		//		        initGUI();
		//		    } else {
		//		        // If access is selected, simply initialize the GUI
		//		        initGUI();
		//		    }
		//		    
		//		// For users who are not part of the specified departments (Non-Accounting and Non-Admin)
		//		} else {
		//		    // Initialize the GUI directly
		//		    initGUI();
		//		}

		initGUI();

	}

	@Override
	public void initGUI() {
		this.setLayout(new BorderLayout(5, 5));
		this.setSize(SIZE);
		this.setPreferredSize(new java.awt.Dimension(935, 538));
		this.setBounds(0, 0, 935, 538);

		pnlMain = new JPanel();
		getContentPane().add(pnlMain, BorderLayout.CENTER);
		pnlMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pnlMain.setLayout(new BorderLayout(5, 5));

		{
			menu = new JPopupMenu("Popup");
			mnidelete = new JMenuItem("Remove Row");
			mniaddrow = new JMenuItem("Add Row");
			//			mnicopy = new JMenuItem("Copy");
			//			mnipaste = new JMenuItem("Paste");
			menu.add(mnidelete);
			menu.add(mniaddrow);
			JSeparator sp = new JSeparator();
			menu.add(sp);
			//			menu.add(mnicopy);
			//			menu.add(mnipaste);
			//			menu.add(mniEditAmount);

			//			mnipaste.setEnabled(false);
			//			mniEditAmount.setEnabled(true);

			mnidelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					removeRow();
				}
			});
			mniaddrow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					AddRow();
				}
			});
			//			mnicopy.addActionListener(new ActionListener() {
			//				public void actionPerformed(ActionEvent evt) {
			//					copy();
			//				}
			//			});
			//			mnipaste.addActionListener(new ActionListener() {
			//				public void actionPerformed(ActionEvent evt) {
			//					paste();
			//				}
			//			});
		}

		{
			menu2 = new JPopupMenu("Popup");
			mniRefresh = new JMenuItem("Refresh");
			mniopenPV = new JMenuItem("Open Payable Voucher");
			mniopenCV = new JMenuItem("Open Disbursement Voucher");
			mniopenDP = new JMenuItem("Open Docs Processing");
			mnisetupPV = new JMenuItem("Set-up Payable Voucher");
			mnisetup2307 = new JMenuItem("Open Form 2307 Monitoring");
			mnisetupDRFprooflist = new JMenuItem("Open DRF Prooflist Report");
			mniwriteoff = new JMenuItem("Write-off CA");
			mniInactivate = new JMenuItem("Delete this Request");
			mnisetupPV.setEnabled(false);
			mniwriteoff.setEnabled(false);
			mniInactivate.setEnabled(false);

			// DISABLE TEMPORARILY FOR DRF TESTING XXX
			mniopenPV.setEnabled(false);
			mniopenDP.setEnabled(false);
			mnisetupDRFprooflist.setEnabled(false);
			mnisetup2307.setEnabled(false);

			menu2.add(mnisetupPV);
			JSeparator sp = new JSeparator();
			JSeparator sp2 = new JSeparator();
			menu2.add(sp);
			menu2.add(mniopenPV);
			menu2.add(mniopenCV);
			menu2.add(mniopenDP);
			menu2.add(sp);
			menu2.add(mnisetupDRFprooflist);
			menu2.add(mnisetup2307);
			menu2.add(mniwriteoff);
			menu2.add(sp2);
			menu2.add(mniInactivate);

			mniRefresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					refresh();
				}
			});

			mnisetupPV.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					setupPV();
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
			mniopenDP.addActionListener(new ActionListener() {
				@SuppressWarnings("static-access")
				public void actionPerformed(ActionEvent evt) {
					if (FncGlobal.homeMDI.isNotExisting("DocsProcessing")) {
						DocsProcessing doc_proc = new DocsProcessing();
						Home_ArcSystem.addWindow(doc_proc);
					}
				}
			});
			mnisetupDRFprooflist.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					openDRFprooflist();
				}
			});
			mnisetup2307.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					open2307();
				}
			});
			mniwriteoff.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					confirmWriteOff();
				}
			});
			mniInactivate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					updateRequest_status("I");
				}
			});
		}

		{
			pnlNorth = new JPanel();
			pnlMain.add(pnlNorth, BorderLayout.NORTH);
			pnlNorth.setLayout(new BorderLayout(5, 5));
			pnlNorth.setBorder(lineBorder);
			pnlNorth.setPreferredSize(new java.awt.Dimension(923, 235));
			pnlNorth.addMouseListener(new PopupTriggerListener_panel2());

			pnlComp = new JPanel(new BorderLayout(5, 0));
			pnlNorth.add(pnlComp, BorderLayout.NORTH);

			pnlComp_a = new JPanel(new BorderLayout(5, 5));
			pnlComp.add(pnlComp_a, BorderLayout.NORTH);
			pnlComp_a.setPreferredSize(new java.awt.Dimension(610, 30));

			pnlComp_a1 = new JPanel(new GridLayout(1, 2, 5, 5));
			pnlComp_a.add(pnlComp_a1, BorderLayout.WEST);
			pnlComp_a1.setPreferredSize(new java.awt.Dimension(209, 30));
			pnlComp_a1.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

			{
				lblCompany = new JLabel("        COMPANY", JLabel.TRAILING);
				pnlComp_a1.add(lblCompany);
				lblCompany.setBounds(8, 11, 62, 12);
				lblCompany.setPreferredSize(new java.awt.Dimension(105, 25));
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

							lblDRF_no.setEnabled(true);
							lookupDRF_no.setEnabled(true);
							btnCancel.setEnabled(true);
							btnAddNew.setEnabled(true);
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

			pnlDRF = new JPanel(new BorderLayout(5, 5));
			pnlNorth.add(pnlDRF, BorderLayout.CENTER);
			pnlDRF.setPreferredSize(new java.awt.Dimension(921, 189));
			pnlDRF.setBorder(JTBorderFactory.createTitleBorder("Contract Details"));
			pnlDRF.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

			pnlDRF_a = new JPanel(new BorderLayout(5, 5));
			pnlDRF.add(pnlDRF_a, BorderLayout.NORTH);
			pnlDRF_a.setPreferredSize(new java.awt.Dimension(911, 42));
			pnlDRF_a.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
			pnlDRF_a.setBorder(lineBorder);

			pnlDRF_a1 = new JPanel(new GridLayout(1, 1, 5, 10));
			pnlDRF_a.add(pnlDRF_a1, BorderLayout.WEST);
			pnlDRF_a1.setPreferredSize(new java.awt.Dimension(92, 40));
			pnlDRF_a1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

			{
				lblDRF_no = new JLabel("DRF No.", JLabel.TRAILING);
				pnlDRF_a1.add(lblDRF_no);
				lblDRF_no.setEnabled(false);
				lblDRF_no.setPreferredSize(new java.awt.Dimension(86, 40));
				lblDRF_no.setFont(new java.awt.Font("Segoe UI", Font.ITALIC, 12));
				lblDRF_no.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12));
			}

			pnlDRF_a2 = new JPanel(new BorderLayout(5, 5));
			pnlDRF_a.add(pnlDRF_a2, BorderLayout.CENTER);
			pnlDRF_a2.setPreferredSize(new java.awt.Dimension(814, 40));
			pnlDRF_a2.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));

			pnlDRF_a2_1 = new JPanel(new GridLayout(1, 1, 5, 10));
			pnlDRF_a2.add(pnlDRF_a2_1, BorderLayout.WEST);
			pnlDRF_a2_1.setPreferredSize(new java.awt.Dimension(197, 22));

			{
				lookupDRF_no = new _JLookup(null, "DRF No.", 2, 2);
				pnlDRF_a2_1.add(lookupDRF_no);
				lookupDRF_no.setBounds(20, 27, 20, 25);
				lookupDRF_no.setReturnColumn(0);
				lookupDRF_no.setEnabled(false);
				lookupDRF_no.setPreferredSize(new java.awt.Dimension(157, 22));
				lookupDRF_no.addLookupListener(new LookupListener() {
					public void lookupPerformed(LookupEvent event) {
						Object[] data = ((_JLookup) event.getSource()).getDataSet();
						if (data != null) {

							drf_no = (String) data[0];
							FncSystem.out("Display SQL", lookupDRF_no.getLookupSQL());
							setDRF_header(lookupCompany.getValue(), drf_no);
							displayDRF_details(modelDRF_part, rowHeaderDRF, modelDRF_part_total, lookupCompany.getValue(), drf_no);

							setCompEnabled(pnlNorth);
							setCompEditable(pnlNorth, false);
							lookupDRF_no.setEditable(true);
							dteDRFDate.getCalendarButton().setEnabled(false);
							dteDueDate.getCalendarButton().setEnabled(false);
							dteEdited.getCalendarButton().setEnabled(false);

							// Get this tax data as default value in case of editing / adding row
							String [] wtax = getWTaxIdAndRate(lookupPayeeType.getValue()); 
							if (wtax != null) {
								tax_id = wtax[0];
								tax_rate = Double.parseDouble(wtax[1]);
							} else {
								tax_id = "";
								tax_rate = 0.00;		
							}

							System.out.println("WTax ID: " + tax_id);
							System.out.println("WTax Rate: " + tax_rate);

							btnAddNew.setEnabled(false);
							lookupCompany.setEnabled(false);

							mnisetupPV.setEnabled(true);
							mniInactivate.setEnabled(true);
							btnPreview.setEnabled(true);

							mnidelete.setEnabled(false);
							mniaddrow.setEnabled(false);
							mniwriteoff.setEnabled(true);

							if(allowedAccess.equals("User")) {
								if (txtStatus.getText().trim().equals("ACTIVE")) {
									btnEdit.setEnabled(true);
									btnSubmitRet.setEnabled(true);
								} else {
									btnSubmitRet.setEnabled(false);
								}
								
							// ACCOUNTING ACCESS	
							} else { 
								if (txtStatus.getText().trim().equals("ACTIVE")) {
									btnEdit.setEnabled(true);
									btnSubmitRet.setEnabled(true);
									btnSubmitRet.setText("Submit");
									btnSubmitRet.setActionCommand("Submit"); 
									
								} else if(txtStatus.getText().trim().equals("SUBMITTED")) {
									btnEdit.setEnabled(true);
									btnSubmitRet.setText("Return");
									btnSubmitRet.setActionCommand("Return"); 
									
								} else { //PROCESSED
									btnEdit.setEnabled(false);
									btnSubmitRet.setText("Return");
									btnSubmitRet.setEnabled(false);; 
								}
							}
						}
					}
				});
			}

			pnlDRF_a2_2 = new JPanel(new GridLayout(1, 2, 5, 0));
			pnlDRF_a2.add(pnlDRF_a2_2, BorderLayout.CENTER);
			pnlDRF_a2_2.setPreferredSize(new java.awt.Dimension(357, 25));

			pnlDRF_a2_3 = new JPanel(new GridLayout(1, 2, 5, 0));
			pnlDRF_a2.add(pnlDRF_a2_3, BorderLayout.EAST);
			pnlDRF_a2_3.setPreferredSize(new java.awt.Dimension(310, 20));
			pnlDRF_a2_3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

			{
				lblStatus = new JLabel("Status", JLabel.TRAILING);
				pnlDRF_a2_3.add(lblStatus);
				lblStatus.setEnabled(false);
			}
			{
				txtStatus = new JXTextField("");
				pnlDRF_a2_3.add(txtStatus);
				txtStatus.setEnabled(false);
				txtStatus.setEditable(false);
				txtStatus.setBounds(120, 25, 300, 22);
				txtStatus.setHorizontalAlignment(JTextField.CENTER);
				txtStatus.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12));
			}
			{
				pnlDRFDtl = new JPanel(new BorderLayout(0, 5));
				pnlDRF.add(pnlDRFDtl, BorderLayout.WEST);
				pnlDRFDtl.setPreferredSize(new java.awt.Dimension(911, 187));

				pnlDRFDtl_1 = new JPanel(new BorderLayout(0, 5));
				pnlDRFDtl.add(pnlDRFDtl_1, BorderLayout.WEST);
				pnlDRFDtl_1.setPreferredSize(new java.awt.Dimension(238, 116));
				pnlDRFDtl_1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

				pnlDRFDtl_1a = new JPanel(new GridLayout(5, 1, 0, 5));
				pnlDRFDtl_1.add(pnlDRFDtl_1a, BorderLayout.WEST);
				pnlDRFDtl_1a.setPreferredSize(new java.awt.Dimension(108, 116));
				pnlDRFDtl_1a.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
				pnlDRFDtl_1a.addMouseListener(new PopupTriggerListener_panel());

				{
					lblDRFDate = new JLabel("Date", JLabel.TRAILING);
					pnlDRFDtl_1a.add(lblDRFDate);
					lblDRFDate.setEnabled(false);
				}
				{
					lblDueDate = new JLabel("Due Date", JLabel.TRAILING);
					pnlDRFDtl_1a.add(lblDueDate);
					lblDueDate.setEnabled(false);
				}
				{
					lblDateEdited = new JLabel("Date Edited", JLabel.TRAILING);
					pnlDRFDtl_1a.add(lblDateEdited);
					lblDateEdited.setEnabled(false);
				}
				{
					lblPV_no = new JLabel("PV No.", JLabel.TRAILING);
					pnlDRFDtl_1a.add(lblPV_no);
					lblPV_no.setEnabled(false);
				}

				pnlDRFDtl_1b = new JPanel(new GridLayout(5, 1, 5, 5));
				pnlDRFDtl_1.add(pnlDRFDtl_1b, BorderLayout.CENTER);
				pnlDRFDtl_1b.setPreferredSize(new java.awt.Dimension(135, 119));
				pnlDRFDtl_1b.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

				{
					dteDRFDate = new _JDateChooser("MM/dd/yy", "##/##/##", '_');
					pnlDRFDtl_1b.add(dteDRFDate);
					dteDRFDate.setBounds(485, 7, 125, 21);
					dteDRFDate.setDate(null);
					dteDRFDate.setEnabled(false);
					((JTextFieldDateEditor) dteDRFDate.getDateEditor()).setEditable(false);
					dteDRFDate.setDate(FncGlobal.dateFormat(FncGlobal.getDateSQL()));
					dteDRFDate.setMaxSelectableDate(FncGlobal.getDateToday()); 
					dteDRFDate.setMinSelectableDate(getMinDRFDate()); 
					
				}
				{

					dteDueDate = new _JDateChooser("MM/dd/yy", "##/##/##", '_');
					pnlDRFDtl_1b.add(dteDueDate);
					dteDueDate.setBounds(485, 7, 125, 21);
					dteDueDate.setEnabled(false);
					dteDueDate.setDateFormatString("yyyy-MM-dd");
					((JTextFieldDateEditor) dteDueDate.getDateEditor()).setEditable(false);
					dteDueDate.setDate(getDueDate()); 
					dteDueDate.setMinSelectableDate(FncGlobal.getDateToday());
				}
				{
					dteEdited = new _JDateChooser("MM/dd/yy", "##/##/##", '_');
					pnlDRFDtl_1b.add(dteEdited);
					dteEdited.setBounds(485, 7, 125, 21);
					dteEdited.setDate(null);
					dteEdited.setEnabled(false);
					dteEdited.setDateFormatString("yyyy-MM-dd");
					((JTextFieldDateEditor) dteEdited.getDateEditor()).setEditable(false);
					dteEdited.setDate(null);
				}
				{
					lookupPV_no = new _JLookup(null, "PV No.", 2, 2);
					pnlDRFDtl_1b.add(lookupPV_no);
					lookupPV_no.setBounds(20, 27, 20, 25);
					lookupPV_no.setReturnColumn(0);
					lookupPV_no.setEnabled(false);
					lookupPV_no.setPreferredSize(new java.awt.Dimension(157, 22));
				}

				// Start of Left Panel
				pnlDRFInfo = new JPanel(new BorderLayout(0, 0));
				pnlDRFDtl.add(pnlDRFInfo, BorderLayout.EAST);
				pnlDRFInfo.setPreferredSize(new java.awt.Dimension(675, 116));
				pnlDRFInfo.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

				pnlDRFInfo_1 = new JPanel(new GridLayout(6, 1, 5, 5));
				pnlDRFInfo.add(pnlDRFInfo_1, BorderLayout.WEST);
				pnlDRFInfo_1.setPreferredSize(new java.awt.Dimension(112, 146));
				pnlDRFInfo_1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

				{
					lblRequestType = new JLabel("Request Type", JLabel.TRAILING);
					pnlDRFInfo_1.add(lblRequestType);
					lblRequestType.setEnabled(false);
				}
				{
					lblPayee = new JLabel("Payee", JLabel.TRAILING);
					pnlDRFInfo_1.add(lblPayee);
					lblPayee.setEnabled(false);
				}
				{
					lblAvailer = new JLabel("Availer", JLabel.TRAILING);
					pnlDRFInfo_1.add(lblAvailer);
					lblAvailer.setEnabled(false);
				}
				{
					lblDiv = new JLabel("Division", JLabel.TRAILING);
					pnlDRFInfo_1.add(lblDiv);
					lblDiv.setEnabled(false);
				}
				{
					lblPayeeType = new JLabel("Payee Type", JLabel.TRAILING);
					pnlDRFInfo_1.add(lblPayeeType);
					lblPayeeType.setEnabled(false);
				}
				{
					lblPaymentType = new JLabel("Payment Type", JLabel.TRAILING);
					pnlDRFInfo_1.add(lblPaymentType);
					lblPaymentType.setEnabled(false);
				}

				pnlDRFDtl_2 = new JPanel(new BorderLayout(5, 0));
				pnlDRFInfo.add(pnlDRFDtl_2, BorderLayout.CENTER);
				pnlDRFDtl_2.setPreferredSize(new java.awt.Dimension(203, 118));
				pnlDRFDtl_2.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

				pnlDRFDtl_2a = new JPanel(new GridLayout(6, 1, 0, 5));
				pnlDRFDtl_2.add(pnlDRFDtl_2a, BorderLayout.WEST);
				pnlDRFDtl_2a.setPreferredSize(new java.awt.Dimension(119, 119));
				pnlDRFDtl_2a.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

				{
					lookupRequestType = new _JLookup(null, "Request Type", 2, 2);
					pnlDRFDtl_2a.add(lookupRequestType);
					lookupRequestType.setBounds(20, 27, 20, 25);
					lookupRequestType.setReturnColumn(0);
					lookupRequestType.setPreferredSize(new java.awt.Dimension(157, 22));
					lookupRequestType.addLookupListener(new LookupListener() {	
						
						public void lookupPerformed(LookupEvent event) {
							Object[] data = ((_JLookup) event.getSource()).getDataSet();
							if (data != null) {
								String drf_type = (String) data[1];
								tagReqType.setTag(drf_type);
								tagReqType.setEnabled(true);
								setPayee();
								lblPayee.setEnabled(true);
								lblAvailer.setEnabled(true);
								lookupPayee.setEnabled(true);
								lookupAvailer.setEnabled(true);
								tagPayee.setEnabled(true);
								tagAvailer.setEnabled(true);
								lblPaymentType.setEnabled(true);
								lookupPaymentType.setEnabled(true);
							}
						}
					});
				}
				{
					lookupPayee = new _JLookup(null, "Payee", 2, 2);
					pnlDRFDtl_2a.add(lookupPayee);
					lookupPayee.setFilterName(true);
					lookupPayee.setBounds(20, 27, 20, 25);
					lookupPayee.setReturnColumn(3);
					lookupPayee.setFilterIndex(4);
					lookupPayee.setEnabled(false);
					lookupPayee.setPreferredSize(new java.awt.Dimension(157, 22));
					lookupPayee.addLookupListener(new LookupListener() {
						public void lookupPerformed(LookupEvent event) {
							Object[] data = ((_JLookup) event.getSource()).getDataSet();
							if (data != null) {

								String entity_name = (String) data[4];
								payee_id = (String) data[3]; 
								tagPayee.setTag(entity_name);
								lookupPayeeType.setLookupSQL(getPayee_type(payee_id));
								is_payee_vatable = (Boolean) data[1];
								tax_rate = 0.00;

								setColumnIfVatableEntity();
								System.out.println("Payee ID : " + payee_id);
								setPayee();

								lblPayeeType.setEnabled(true);
								lookupPayeeType.setEnabled(true);
								tagPayeeType.setEnabled(true);

								lookupPayeeType.setValue("");
								tagPayeeType.setText("[ ]");
								
							}
						}
					});
				}
				{
					lookupAvailer = new _JLookup(null, "Availer", 2, 2);
					pnlDRFDtl_2a.add(lookupAvailer);
					lookupAvailer.setBounds(20, 27, 20, 25);
					lookupAvailer.setReturnColumn(0);
					lookupAvailer.setEnabled(false);
					lookupAvailer.setFilterName(true);
					lookupAvailer.setPreferredSize(new java.awt.Dimension(157, 22));
					lookupAvailer.addLookupListener(new LookupListener() {
						public void lookupPerformed(LookupEvent event) {
							Object[] data = ((_JLookup) event.getSource()).getDataSet();
							if (data != null) {

								String entity_name = (String) data[1];
								String div_id = (String) data[4]; 
								String division = (String) data[5];

								tagAvailer.setTag(entity_name);
								lblDiv.setEnabled(true);
								lookupDiv.setEnabled(true);
								lookupDiv.setEditable(false);
								tagAvailerDiv.setEnabled(true);
								lookupDiv.setValue(div_id); 
								tagAvailerDiv.setTag(division);
								setPayee();

							}
						}
					});
				}
				{
					lookupDiv = new _JLookup(null, "Division", 2, 2);
					pnlDRFDtl_2a.add(lookupDiv);
					lookupDiv.setReturnColumn(0);
					lookupDiv.setEnabled(false);
					lookupDiv.setPreferredSize(new java.awt.Dimension(157, 22));

				}
				{
					lookupPayeeType = new _JLookup(null, "Payee Type", 2, 2);
					pnlDRFDtl_2a.add(lookupPayeeType);
					lookupPayeeType.setBounds(20, 27, 20, 25);
					lookupPayeeType.setReturnColumn(0);
					lookupPayeeType.setEnabled(false);
					lookupPayeeType.setPreferredSize(new java.awt.Dimension(157, 22));
					lookupPayeeType.addLookupListener(new LookupListener() {
						public void lookupPerformed(LookupEvent event) {
							Object[] data = ((_JLookup) event.getSource()).getDataSet();
							if (data != null) {
								String payee_type = (String) data[1];
								tagPayeeType.setTag(payee_type);
								tax_id = data[2].toString();
								tax_rate = Double.parseDouble(data[3].toString());

								setPayee();

								FncSystem.out("Display SQL of Payee Type", lookupPayeeType.getLookupSQL());

							}
						}
					});
				}
				{
					lookupPaymentType = new _JLookup(null, "Payment Type", 2, 2);
					pnlDRFDtl_2a.add(lookupPaymentType);
					lookupPaymentType.setBounds(20, 27, 20, 25);
					lookupPaymentType.setReturnColumn(0);
					lookupPaymentType.setEnabled(false);
					lookupPaymentType.setPreferredSize(new java.awt.Dimension(157, 22));
					lookupPaymentType.addLookupListener(new LookupListener() {
						public void lookupPerformed(LookupEvent event) {
							Object[] data = ((_JLookup) event.getSource()).getDataSet();
							if (data != null) {
								pay_type_name = (String) data[1];
								tagPayType.setTag(pay_type_name);
								tagPayType.setEnabled(true);
							}
						}
					});
				}

				pnlDRFDtl_2b = new JPanel(new GridLayout(6, 1, 0, 5));
				pnlDRFDtl_2.add(pnlDRFDtl_2b, BorderLayout.CENTER);
				pnlDRFDtl_2b.setPreferredSize(new java.awt.Dimension(140, 118));
				pnlDRFDtl_2b.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

				{
					tagReqType = new _JTagLabel("[ ]");
					pnlDRFDtl_2b.add(tagReqType);
					tagReqType.setBounds(209, 27, 700, 22);
					tagReqType.setEnabled(false);
					tagReqType.setPreferredSize(new java.awt.Dimension(27, 33));
					tagReqType.addMouseListener(new PopupTriggerListener_panel2());
				}
				{
					tagPayee = new _JTagLabel("[ ]");
					pnlDRFDtl_2b.add(tagPayee);
					tagPayee.setBounds(209, 27, 700, 22);
					tagPayee.setEnabled(false);
					tagPayee.setPreferredSize(new java.awt.Dimension(27, 33));
					tagPayee.addMouseListener(new PopupTriggerListener_panel2());
				}
				{
					tagAvailer = new _JTagLabel("[ ]");
					pnlDRFDtl_2b.add(tagAvailer);
					tagAvailer.setBounds(209, 27, 700, 22);
					tagAvailer.setEnabled(false);
					tagAvailer.setPreferredSize(new java.awt.Dimension(27, 33));
					tagAvailer.addMouseListener(new PopupTriggerListener_panel2());
				}
				{
					tagAvailerDiv = new _JTagLabel("[ ]");
					pnlDRFDtl_2b.add(tagAvailerDiv);
					tagAvailerDiv.setBounds(209, 27, 700, 22);
					tagAvailerDiv.setEnabled(false);
					tagAvailerDiv.setPreferredSize(new java.awt.Dimension(27, 33));
					tagAvailerDiv.addMouseListener(new PopupTriggerListener_panel2());
				}
				{
					tagPayeeType = new _JTagLabel("[ ]");
					pnlDRFDtl_2b.add(tagPayeeType);
					tagPayeeType.setBounds(209, 27, 700, 22);
					tagPayeeType.setEnabled(false);
					tagPayeeType.setPreferredSize(new java.awt.Dimension(27, 33));
					tagPayeeType.addMouseListener(new PopupTriggerListener_panel2());
				}
				{
					tagPayType = new _JTagLabel("[ ]");
					pnlDRFDtl_2b.add(tagPayType);
					tagPayType.setBounds(209, 27, 700, 22);
					tagPayType.setEnabled(false);
					tagPayType.setPreferredSize(new java.awt.Dimension(27, 33));
					tagPayType.addMouseListener(new PopupTriggerListener_panel2());
				}
			}
		}
		{
			pnlDate = new JPanel();
			pnlDate.setLayout(null);
			pnlDate.setPreferredSize(new java.awt.Dimension(265, 80));
			{
				lblDate = new JLabel();
				pnlDate.add(lblDate);
				lblDate.setBounds(5, 15, 160, 20);
				lblDate.setText("Reference Doc. Date :");
			}
			{
				dteRefDate = new _JDateChooser("MM/dd/yy", "##/##/##", '_');
				pnlDate.add(dteRefDate);
				dteRefDate.setBounds(130, 15, 125, 21);
				dteRefDate.setDate(null);
				dteRefDate.setEnabled(true);
				dteRefDate.setDateFormatString("yyyy-MM-dd");
				((JTextFieldDateEditor) dteRefDate.getDateEditor()).setEditable(false);
				dteRefDate.setDate(FncGlobal.dateFormat(FncGlobal.getDateSQL()));
				dteRefDate.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent e) {
					}
				});
			}
			{
				btnOK = new JButton();
				pnlDate.add(btnOK);
				btnOK.setBounds(95, 58, 69, 22);
				btnOK.setText("OK");
				btnOK.setFocusable(false);
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Window optionPaneWindow = SwingUtilities.getWindowAncestor(pnlDate);
						optionPaneWindow.dispose();
					}
				});
			}
		}
		{
			pnlTable = new JPanel(new BorderLayout(5, 5));
			pnlMain.add(pnlTable, BorderLayout.CENTER);
			pnlTable.setPreferredSize(new java.awt.Dimension(610, 30));

			pnlDRF = new JPanel();
			pnlTable.add(pnlDRF, BorderLayout.CENTER);
			pnlDRF.setLayout(new BorderLayout(5, 5));
			pnlDRF.setPreferredSize(new java.awt.Dimension(923, 199));
			pnlDRF.setBorder(lineBorder);
			pnlDRF.addMouseListener(new PopupTriggerListener_panel());

			tabCenter = new _JTabbedPane();
			pnlDRF.add(tabCenter, BorderLayout.CENTER);

			{
				pnlDRF_particular = new JPanel(new BorderLayout());
				tabCenter.addTab("Request Details", null, pnlDRF_particular, null);
				pnlDRF_particular.setPreferredSize(new java.awt.Dimension(1183, 365));

				{
					scrollDRF_part = new _JScrollPaneMain();//
					pnlDRF_particular.add(scrollDRF_part, BorderLayout.CENTER);
					{
						modelDRF_part = new modelDRF_particulars();

						tblDRF_part = new _JTableMain(modelDRF_part);
						scrollDRF_part.setViewportView(tblDRF_part);
						tblDRF_part.addMouseListener(this);
						tblDRF_part.addMouseListener(new PopupTriggerListener_panel());
						tblDRF_part.setSortable(false);
						tblDRF_part.addKeyListener(new KeyAdapter() {
							public void keyTyped(KeyEvent evt) {
								if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
									tblDRF_part.packAll();
									computeDRF();
									checkCostCenter_manual(evt);
								}

							}

							public void keyReleased(KeyEvent evt) {
								if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
									tblDRF_part.packAll();
									computeDRF();
									if (tblDRF_part.getSelectedColumn() != 6) {
										checkCostCenter_manual(evt);
									}
								}

							}

							public void keyPressed(KeyEvent e) {
								if (e.getKeyCode() == KeyEvent.VK_ENTER) {
									tblDRF_part.packAll();
									computeDRF();
									checkCostCenter_manual(e);
								}

							}

						});
						tblDRF_part.addMouseListener(new MouseAdapter() {
							public void mousePressed(MouseEvent e) {
								if (tblDRF_part.rowAtPoint(e.getPoint()) == -1) {
									tblDRF_part_total.clearSelection();
								} else {

								}
							}

							public void mouseReleased(MouseEvent e) {
								if (tblDRF_part.rowAtPoint(e.getPoint()) == -1) {
									tblDRF_part_total.clearSelection();
								} else {

								}
							}
						});

						tblDRF_part.addKeyListener(this);
						tblDRF_part.putClientProperty("terminateEditOnFocusLost", true);
					}
					{
						rowHeaderDRF = tblDRF_part.getRowHeader22(); 
						scrollDRF_part.setRowHeaderView(rowHeaderDRF);
						scrollDRF_part.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER,
								FncTables.getRowHeader_Header());
					}

					// HIDDEN TABLE COLUMNS
					tblDRF_part.hideColumns("Within"); 
					tblDRF_part.hideColumns("Ref ID"); 
					tblDRF_part.hideColumns("Ref No"); 
					tblDRF_part.hideColumns("Ref Date"); 
					tblDRF_part.hideColumns("Within"); 
					tblDRF_part.hideColumns("Item ID"); 
					tblDRF_part.hideColumns("Item Description");
					tblDRF_part.hideColumns("Vatable Project");
					tblDRF_part.hideColumns("Taxable");
					tblDRF_part.hideColumns("Rec ID");

					tblDRF_part.getColumnModel().getColumn(6).setCellEditor(modelDRF_part.new BigDecimalCellEditor());
				}

				{
					scrollDRF_part_total = new _JScrollPaneTotal(scrollDRF_part);
					pnlDRF_particular.add(scrollDRF_part_total, BorderLayout.SOUTH);
					{
						modelDRF_part_total = new modelDRF_particulars_total();
						modelDRF_part_total.addRow(new Object[] { "Total", null, null, null, null, null, new BigDecimal(0.00), null, null, null, null,
								null, null, null, null, null, null, null, null, null, null, null, null, null, 
								new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
								null, null, new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
								new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00)});

						tblDRF_part_total = new _JTableTotal(modelDRF_part_total, tblDRF_part);
						tblDRF_part_total.setFont(dialog11Bold);
						scrollDRF_part_total.setViewportView(tblDRF_part_total);
						((_JTableTotal) tblDRF_part_total).setTotalLabel(0);

						// HIDDEN TABLE COLUMNS
						tblDRF_part_total.hideColumns("Within"); 
						tblDRF_part_total.hideColumns("Ref ID"); 
						tblDRF_part_total.hideColumns("Ref No"); 
						tblDRF_part_total.hideColumns("Ref Date"); 
						tblDRF_part_total.hideColumns("Within"); 
						tblDRF_part_total.hideColumns("Item ID"); 
						tblDRF_part_total.hideColumns("Item Description");
						tblDRF_part_total.hideColumns("Vatable Project");
						tblDRF_part_total.hideColumns("Taxable");
						tblDRF_part_total.hideColumns("Rec ID");
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
					btnAddNew = new JButton("Add New");
					pnlSouthCenter.add(btnAddNew);
					btnAddNew.setActionCommand(btnAddNew.getText());
					btnAddNew.addActionListener(this);
					btnAddNew.setEnabled(false);
				}
				{
					btnEdit = new JButton("Edit");
					pnlSouthCenter.add(btnEdit);
					btnEdit.setActionCommand("Edit");
					btnEdit.addActionListener(this);
					btnEdit.setEnabled(false);
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
					btnSubmitRet = new JButton("Submit");
					pnlSouthCenter.add(btnSubmitRet);
					btnSubmitRet.setActionCommand("Submit");
					btnSubmitRet.setEnabled(false);
					btnSubmitRet.addActionListener(this);
				}
				{
					btnCancel = new JButton("Cancel");
					pnlSouthCenter.add(btnCancel);
					btnCancel.setActionCommand("Cancel");
					btnCancel.addActionListener(this);
					btnCancel.setEnabled(false);
				}
			}
			{
				pnlDRF_Particulars = new JPanel(new BorderLayout());
				tabCenter.addTab("Particulars", null, pnlDRF_Particulars, null);
				pnlDRF_Particulars.setPreferredSize(new java.awt.Dimension(100, 365));

				scpDRFParticulars = new JScrollPane();
				pnlDRF_Particulars.add(scpDRFParticulars);
				scpDRFParticulars.setBounds(82, 7, 150, 61);
				scpDRFParticulars.setOpaque(true);
				scpDRFParticulars.setPreferredSize(new java.awt.Dimension(100, 285));

				{
					txtDRFParticulars = new JTextArea();
					scpDRFParticulars.add(txtDRFParticulars);
					scpDRFParticulars.setViewportView(txtDRFParticulars);
					txtDRFParticulars.setBounds(77, 3, 250, 81);
					txtDRFParticulars.setLineWrap(true);
					txtDRFParticulars.setPreferredSize(new java.awt.Dimension(100, 1000));
					txtDRFParticulars.setEditable(false);
					txtDRFParticulars.setEnabled(true);
					applyPlaceholder(txtDRFParticulars, placeholder_particulars, Color.GRAY);
				}

			}
			{
				pnlDRF_OtherDetails = new JPanel(new BorderLayout());
				tabCenter.addTab("Other Details", null, pnlDRF_OtherDetails, null);
				pnlDRF_OtherDetails.setPreferredSize(new java.awt.Dimension(100, 365));

				scpDRFOtherDetails = new JScrollPane();
				pnlDRF_OtherDetails.add(scpDRFOtherDetails);
				scpDRFOtherDetails.setBounds(82, 7, 150, 61);
				scpDRFOtherDetails.setOpaque(true);
				scpDRFOtherDetails.setPreferredSize(new java.awt.Dimension(100, 285));

				{
					txtDRFOtherDetails = new JTextArea();
					scpDRFOtherDetails.add(txtDRFOtherDetails);
					scpDRFOtherDetails.setViewportView(txtDRFOtherDetails);
					txtDRFOtherDetails.setBounds(77, 3, 250, 81);
					txtDRFOtherDetails.setLineWrap(true);
					txtDRFOtherDetails.setPreferredSize(new java.awt.Dimension(100, 1000));
					txtDRFOtherDetails.setEditable(false);
					txtDRFOtherDetails.setEnabled(true);
					applyPlaceholder(txtDRFOtherDetails, placeholder_other_details, Color.GRAY);

				}

			}
			{
				pnlDRF_ReqRemarks = new JPanel(new BorderLayout());
				tabCenter.addTab("Requester's Remarks", null, pnlDRF_ReqRemarks, null);
				pnlDRF_ReqRemarks.setPreferredSize(new java.awt.Dimension(1183, 365));

				scpDRFReqRemarks = new JScrollPane();
				pnlDRF_ReqRemarks.add(scpDRFReqRemarks);
				scpDRFReqRemarks.setBounds(82, 7, 150, 61);
				scpDRFReqRemarks.setOpaque(true);
				scpDRFReqRemarks.setPreferredSize(new java.awt.Dimension(100, 285));

				{
					txtDRFReqRemarks = new JTextArea();
					scpDRFReqRemarks.add(txtDRFReqRemarks);
					scpDRFReqRemarks.setViewportView(txtDRFReqRemarks);
					txtDRFReqRemarks.setBounds(77, 3, 250, 81);
					txtDRFReqRemarks.setLineWrap(true);
					txtDRFReqRemarks.setPreferredSize(new java.awt.Dimension(100, 1000));
					txtDRFReqRemarks.setEditable(false);
					txtDRFReqRemarks.setEnabled(true);
					applyPlaceholder(txtDRFReqRemarks, placeholder_req_remarks, Color.GRAY);

				}
			}
			{
				pnlDRF_Attachments = new JPanel(new BorderLayout());
				tabCenter.addTab("Attachments", null, pnlDRF_Attachments, null);
				pnlDRF_Attachments.setPreferredSize(new java.awt.Dimension(1183, 365));

				scpDRFAttachments = new JScrollPane();
				pnlDRF_Attachments.add(scpDRFAttachments);
				scpDRFAttachments.setBounds(82, 7, 150, 61);
				scpDRFAttachments.setOpaque(true);
				scpDRFAttachments.setPreferredSize(new java.awt.Dimension(100, 285));

				{
					txtDRFAttachments = new JTextArea();
					scpDRFAttachments.add(txtDRFAttachments);
					scpDRFAttachments.setViewportView(txtDRFAttachments);
					txtDRFAttachments.setBounds(77, 3, 250, 81);
					txtDRFAttachments.setLineWrap(true);
					txtDRFAttachments.setPreferredSize(new java.awt.Dimension(100, 1000));
					txtDRFAttachments.setEditable(false);
					txtDRFAttachments.setEnabled(true);
					txtDRFAttachments.setText("");
					applyPlaceholder(txtDRFAttachments, placeholder_attachments, Color.GRAY);

				}
			}
		}

		initialize_comp();

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

					}
				});
			}
		}
	}	//XXX END OF GUI

	// display tables
	public static void displayDRF_details(modelDRF_particulars model, JList <Integer> rowHeader, modelDRF_particulars_total modelTotal,
			String co_id, String drf_no) {

		FncTables.clearTable(model);// 
		DefaultListModel <Integer> listModel = new DefaultListModel<>();
		rowHeader.setModel(listModel);

		String sql = "Select \n"
				+ "a.acct_id \n"
				+ ", d.acct_name as main_acct_desc\n"
				+ ", COALESCE(a.proj_cost_accnt_id, '') as proj_cost_accnt_id\n"
				+ ", COALESCE(c.proj_cost_accnt_desc, '') as proj_cost_accnt_desc\n"
				+ ", a.div_id\n"
				+ ", COALESCE(a.proj_id, '') as proj_id\n"
				+ ", a.amount\n"
				+ ", NULL as withinBudget\n"
				+ ", a.payee_id\n"
				+ ", a.payee_type_id\n"
				+ ", trim(b.entity_name) as payee_name\n"
				+ ", NULL as ref_id\n"
				+ ", NULL as ref_no\n"
				+ ", NULL as ref_date\n"
				+ ", NULL as item_id\n"
				+ ", NULL as item_desc\n"
				+ ", COALESCE(a.invoice_no, '') as invoice_no\n"
				+ ", a.invoice_date\n"
				+ ", COALESCE(a.soa_bill_no, '') as soa_bill_no\n"
				+ ", a.soa_bill_date\n"
				+ ", a.asset_no\n"
				+ ", NULL as vatable_proj\n"
				+ ", a.is_vat_entity\n"
				+ ", NULL as taxable\n"
				+ ", a.gross_amt\n"
				+ ", a.net_amt\n"
				+ ", a.vat_rate\n"
				+ ", a.vat_amt\n"
				+ ", a.wtax_id\n"
				+ ", a.wtax_rate\n"
				+ ", a.wtax_amt\n"
				+ ", a.expense_amt\n"
				+ ", coalesce(a.retention_amt,0) as retention_amt\n"
				+ ", coalesce(a.dp_recoup_amt,0) as dp_recoup_amt\n"
				+ ", coalesce(a.bc_liq_amt,0) as bc_liq_amt\n"
				+ ", coalesce(a.other_liq_amt,0) as other_liq_amt\n"
				+ ", a.payable_amt\n"
				+ ", a.rec_id::VARCHAR \n"
				+ ", fn_get_div_alias(a.div_id) as division\n"
				+ ", (Select proj_alias from mf_project where proj_id = a.proj_id and status_id = 'A') as proj_id\n"
				+ "from rf_drf_detail a\n"
				+ "left join rf_entity b on a.payee_id = b.entity_id and b.status_id = 'A'\n"
				+ "left join mf_project_cost_accnts c on trim(c.proj_cost_accnt_id) = trim(a.proj_cost_accnt_id) and c.status_id ='A' \n"
				+ "left join mf_boi_chart_of_accounts d on trim(d.acct_id) = trim(a.acct_id) and d.status_id = 'A'\n"
				+ "where drf_no = '"+drf_no+"' \n"
				+ "and a.status_id = 'A'\n"
				+ "and a.co_id = '"+co_id+"'\n"
				+ "order by a.line_no ";

		FncSystem.out("DRF details", sql);

		pgSelect db = new pgSelect();
		db.select(sql);
		if (db.isNotNull()) {
			for (int x = 0; x < db.getRowCount(); x++) {
				// Adding of row in table
				model.addRow(db.getResult()[x]);
				listModel.addElement(model.getRowCount());
			}

			totalDRF(model, modelTotal);
		}

		else {
			modelDRF_part_total = new modelDRF_particulars_total();
			modelDRF_part_total.addRow(new Object[] { "Total", null, null, null, null, null, new BigDecimal(0.00), null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, 
					new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
					null, null, new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
					new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00)});

			tblDRF_part_total = new _JTableTotal(modelDRF_part_total, tblDRF_part);
			tblDRF_part_total.setFont(dialog11Bold);
			scrollDRF_part_total.setViewportView(tblDRF_part_total);
			((_JTableTotal) tblDRF_part_total).setTotalLabel(0);
		}

		enable_fields(false);
		btnCancel.setEnabled(true);
		btnSubmitRet.setEnabled(true);
		lblDRF_no.setEnabled(true);
		lookupDRF_no.setEnabled(true);
		modelDRF_part.setEditable(false);
		adjustRowHeight();
		mnidelete.setEnabled(false);
		mniaddrow.setEnabled(false);
	}

	public static void setDRF_header(String co_id, String drf_no) {
		Object[] drf_hdr = getDRFdetails(co_id, drf_no);

		//XXX NEED TO BALIKAN
		String mc_no = "";
		String div_id = ((String) drf_hdr[20]); 
		String div_name = FncGlobal.GetString("Select div_name from mf_division where div_code = '"+div_id+"' and status_id = 'A';"); 

		//		mc_no = FncGlobal.GetString(
		//				"select case when coalesce(string_agg(c.mc_no, ','), '') != '' then 'Mc No.: ' else '' end  || string_agg(c.mc_no, ',') as mc_no\n"
		//				+ "from ( select distinct on (pv_no) pv_no, co_id  from rf_pv_detail\n"
		//				+ "where co_id = '\"+ co_id + \"' \n"
		//				+ "and pv_no in ( select pv_no from rf_pv_header where pv_no = '\" + drf_no+ \"' and co_id = '\" + co_id + \"'  and status_id != 'I' and NULLIF(pv_no, '') is not null) \n"
		//				+ "and status_id = 'A' \n"
		//				+ "and bal_side = 'D' \n"
		//				+ "group by pv_no, co_id) a\n"
		//				+ "left join rf_pv_header b on a.pv_no = b.pv_no and a.co_id = b.co_id\n"
		//				+ "left join rf_mc_detail c on a.pv_no = c.pv_no and a.co_id = c.co_id and a.co_id = b.co_id");

		dteDRFDate.setDate((Date) drf_hdr[1]);
		dteDueDate.setDate((Date) drf_hdr[2]);
		lookupPV_no.setValue((String) drf_hdr[3]);
		lookupDiv.setValue((String) drf_hdr[20]);
		tagAvailerDiv.setTag(div_name);

		lookupRequestType.setValue((String) drf_hdr[4]);
		tagReqType.setTag((String) drf_hdr[5]);

		lookupPayee.setValue((String) drf_hdr[6]);
		lookupAvailer.setValue((String) drf_hdr[7]);
		tagPayee.setTag((String) drf_hdr[8]);
		tagAvailer.setTag((String) drf_hdr[9]);

		lookupPayeeType.setValue((String) drf_hdr[10]);
		tagPayeeType.setTag((String) drf_hdr[11]);

		txtStatus.setText((String) drf_hdr[12]);
		
		txtDRFParticulars.setForeground(Color.BLACK);
		txtDRFOtherDetails.setForeground(Color.BLACK);
		txtDRFReqRemarks.setForeground(Color.BLACK);
		txtDRFAttachments.setForeground(Color.BLACK);
		
		txtDRFParticulars.setText((String) drf_hdr[13]);
		txtDRFOtherDetails.setText((String) drf_hdr[14]);
		txtDRFReqRemarks.setText((String) drf_hdr[15]);
		txtDRFAttachments.setText((String) drf_hdr[16]);
		dteEdited.setDate((Date) drf_hdr[17]);

		lookupPaymentType.setValue((String) drf_hdr[18]);
		tagPayType.setTag((String) drf_hdr[19]);

	}

	public void createDRFtable(DefaultTableModel modelMain, JList<Integer> rowHeader, DefaultTableModel modelTotal) {

		FncTables.clearTable(modelMain);
		DefaultListModel<Integer> listmodel = new DefaultListModel<Integer>();
		rowHeader.setModel(listmodel);

		String sql = "Select * from fn_create_drf_table();";

		pgSelect db = new pgSelect();
		db.select(sql);
		if (db.isNotNull()) {
			for (int x = 0; x < db.getRowCount(); x++) {
				// Adding of row in table
				modelMain.addRow(db.getResult()[x]);
				listmodel.addElement(modelMain.getRowCount());
			}

			totalDRF(modelMain, modelTotal);
		}

		else {
			modelDRF_part_total = new modelDRF_particulars_total();
			modelDRF_part_total.addRow(new Object[] { "Total", null, null, null, null, null, new BigDecimal(0.00), null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, 
					new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
					null, null, new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
					new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00)});

			tblDRF_part_total = new _JTableTotal(modelDRF_part_total, tblDRF_part);
			tblDRF_part_total.setFont(dialog11Bold);
			scrollDRF_part_total.setViewportView(tblDRF_part_total);
			((_JTableTotal) tblDRF_part_total).setTotalLabel(0);
		}

		tblDRF_part.packAll();
		modelDRF_part.setEditable(true);
		adjustRowHeight();
	}

	// Enable/Disable all components inside JPanel
	public static void setComponentEnabled(JPanel panel, boolean enable) {
		for (Component comp : panel.getComponents()) {
			comp.setEnabled(enable);
		}
	}

	public static void enable_fields(Boolean x) {

		lblDRF_no.setEnabled(x);
		lookupDRF_no.setEnabled(x);
		lblStatus.setEnabled(x);
		txtStatus.setEnabled(x);

		lblDRFDate.setEnabled(x);
		dteDRFDate.setEnabled(x);
		lblDueDate.setEnabled(x);
		dteDueDate.setEnabled(x);
		lblDateEdited.setEnabled(x);
		dteEdited.setEnabled(x);
		lblPV_no.setEnabled(x);
		lookupPV_no.setEnabled(x);

		lblRequestType.setEnabled(x);
		lookupRequestType.setEnabled(x);
		tagReqType.setEnabled(x);

		lblPayee.setEnabled(x);
		lookupPayee.setEnabled(x);
		tagPayee.setEnabled(x);

		lblAvailer.setEnabled(x);
		lookupAvailer.setEnabled(x);
		tagAvailer.setEnabled(x);

		lblDiv.setEnabled(x);
		lookupDiv.setEnabled(x);
		tagAvailerDiv.setEnabled(x);

		lblPaymentType.setEnabled(x);
		lookupPaymentType.setEnabled(x);
		tagPayType.setEnabled(x);

		lblPayeeType.setEnabled(x);
		lookupPayeeType.setEnabled(x);
		tagPayeeType.setEnabled(x);
		txtDRFParticulars.setEditable(x);
		txtDRFOtherDetails.setEditable(x);
		txtDRFReqRemarks.setEditable(x);
		txtDRFAttachments.setEditable(x);
	}

	public static void refresh_fields() {

		lookupDRF_no.setValue("");
		lookupPV_no.setValue("");
		lookupRequestType.setValue("");
		lookupPayee.setValue("");
		lookupAvailer.setValue("");
		lookupPayeeType.setValue("");
		lookupPaymentType.setValue("");
		lookupDiv.setValue("");

		txtStatus.setText("");
		tagReqType.setText("[ ]");
		tagPayee.setText("[ ]");
		tagAvailer.setText("[ ]");
		tagAvailerDiv.setText("[ ]");
		tagPayeeType.setText("[ ]");
		tagPayType.setText("[ ]");
		dteDRFDate.setDate(FncGlobal.dateFormat(FncGlobal.getDateSQL()));
		dteDueDate.setDate(getDueDate());
		dteEdited.setDate(null);

		applyPlaceholder(txtDRFParticulars, "Input a short description of the request or transaction.", Color.GRAY);
		applyPlaceholder(txtDRFOtherDetails, "Input other details of the request or transaction.", Color.GRAY);
		applyPlaceholder(txtDRFReqRemarks, "Input remarks for the request or transaction.", Color.GRAY);
		applyPlaceholder(txtDRFAttachments, "PR# / PO# / RR# / Quotation, etc.", Color.GRAY);

	}

	public static void refresh_tablesMain() {
		// reset table 1
		FncTables.clearTable(modelDRF_part);
		FncTables.clearTable(modelDRF_part_total);
		rowHeaderDRF = tblDRF_part.getRowHeader22();
		scrollDRF_part.setRowHeaderView(rowHeaderDRF);
		modelDRF_part_total.addRow(new Object[] { "Total", null, null, null, null, null, new BigDecimal(0.00), null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, 
				new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
				null, null, new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
				new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00)});

	}

	public static void initialize_comp() {

		user = UserInfo.EmployeeCode;
		lookupCompany.setValue("01");
		company = FncGlobal.GetString("Select company_name from mf_company where co_id = '"+lookupCompany.getText().trim()+"';");
		tagCompany.setTag(company);
		company_logo = sql_getCompanyLogo(lookupCompany.getText().trim());
		lblDRF_no.setEnabled(true);
		lookupDRF_no.setEnabled(true);
		btnCancel.setEnabled(true);
		btnAddNew.setEnabled(true);
		lookupRequestType.setEnabled(false);

		if(allowedAccess.equals("Accounting")){
			lookupDRF_no.setLookupSQL(getDRF_no_accntg(lookupCompany.getValue()));
		} else {
			lookupDRF_no.setLookupSQL(getDRF_no(lookupCompany.getValue(), user));
		}	
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

		if (e.getActionCommand().equals("Add New")) {
			if (allowedAccess.equals("Accounting")) {
				lookupRequestType.setLookupSQL(getRequestTypeAcctng());
				add();
			} else {
				lookupRequestType.setLookupSQL(getRequestTypeNonAcctng());
				add();
			}
		}

		if (e.getActionCommand().equals("Edit")) {
			if(isSameUser(lookupCompany.getValue(), lookupDRF_no.getText().trim(), user_id)) {
				edit();
			} else if(allowedAccess.equals("Accounting")) {
				edit();
			} else {
				JOptionPane.showMessageDialog(getContentPane(), "Sorry, you are not authorized to edit this request.",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (e.getActionCommand().equals("Submit")) {
			co_id = lookupCompany.getText(); 
			drf_no = lookupDRF_no.getText().trim();

			if(isSameUser(co_id, drf_no, user_id)) {
				submit(co_id, drf_no);
				setDRF_header(co_id, drf_no);
				btnEdit.setEnabled(false);
				btnSubmitRet.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(getContentPane(), "Sorry, you are not authorized to submit this request.",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (e.getActionCommand().equals("Return")) {
			co_id = lookupCompany.getText(); 
			drf_no = lookupDRF_no.getText().trim();

			returned(co_id, drf_no);
			setDRF_header(co_id, drf_no);
			btnSubmitRet.setEnabled(false);

		}

		if (e.getActionCommand().equals("Save")) {
			save();
		}

		if (e.getActionCommand().equals("Preview") && FncAcounting.EmpCanPreview(UserInfo.EmployeeCode, "1") == true) {
			preview();
		} else if (e.getActionCommand().equals("Preview")
				&& FncAcounting.EmpCanPreview(UserInfo.EmployeeCode, "1") == false) {
			JOptionPane.showMessageDialog(getContentPane(), "Sorry, you are not authorized to preview/print this DRF.",
					"Information", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void mouseClicked(MouseEvent evt) {

		int row = tblDRF_part.convertRowIndexToModel(tblDRF_part.getSelectedRow());
		int column = tblDRF_part.convertColumnIndexToModel(tblDRF_part.getSelectedColumn());

		System.out.println("Value ng ClickCount: " + evt.getClickCount());
		System.out.println("Value ng column: " + column);

		BigDecimal amount = (BigDecimal) modelDRF_part.getValueAt(row, 6); 
		System.out.println("Value ng amount: " + amount);

		if ((evt.getClickCount() >= 2)) {
			clickTableColumn();
		}

		// Amount Column
		else if ((evt.getClickCount() >= 1) && column == 6) {
			computeDRF();
		}

		// Vatable Entity Column
		else if ((evt.getClickCount() == 1) && column == 22) {
			System.out.println("Vatable Entity Clicked");
			computeDRF();
		}	

		//		else if ((evt.getClickCount() == 1) && (column == 16 || column == 18)) {
		//			System.out.println("Click sa Invoice Date / SOA/Bill Date");
		//			modelDRF_part.setValueAt(sdf.format(Calendar.getInstance().getTime()), row, column);
		//		}

		// Invoice Date / SOA or Bill Date Column
		else if ((evt.getClickCount() == 2) && (column == 17 || column == 19)) {
			tblDRF_part.editCellAt(row, column); 
			System.out.println("Dumaan dito!");
		}
		
		else if((evt.getClickCount() == 1) && (column == 16 || column == 17 || column == 18 || column == 19)) {
			
			String drf_type = lookupRequestType.getValue().trim(); 
			System.out.println("drf_type value: " + drf_type);
			
			if(drf_type.equals("01") || drf_type.equals("17")) {
				System.out.println("Enable editable for Invoice!");
				modelDRF_part.setEditableForInvoice(true);
			} else if(drf_type.equals("14") || drf_type.equals("16")) {
				System.out.println("Enable editable for Billing!");
				modelDRF_part.setEditableForBilling(true);
				modelDRF_part.setEditableForInvoice(false);
				
			} else {
				modelDRF_part.setEditableForInvoice(false);
				modelDRF_part.setEditableForBilling(false);
			}
			
		}


	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

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

	public class PopupTriggerListener_panel2 extends MouseAdapter {
		public void mousePressed(MouseEvent ev) {
			if (ev.isPopupTrigger()) {
				menu2.show(ev.getComponent(), ev.getX(), ev.getY());
				if (lookupRequestType.getText().equals("02") || lookupRequestType.getText().equals("05")
						|| lookupRequestType.getText().equals("12")) {
					mniwriteoff.setEnabled(true);
				} else {
					mniwriteoff.setEnabled(false);
				}
			}
		}

		public void mouseReleased(MouseEvent ev) {
			if (ev.isPopupTrigger()) {
				menu2.show(ev.getComponent(), ev.getX(), ev.getY());
				if (lookupRequestType.getText().equals("02") || lookupRequestType.getText().equals("05")
						|| lookupRequestType.getText().equals("12")) {
					mniwriteoff.setEnabled(true);
				} else {
					mniwriteoff.setEnabled(false);
				}
			}
		}
	}

	public void refresh() {

		setDRF_header(lookupCompany.getValue(), lookupDRF_no.getText().trim());
		displayDRF_details(modelDRF_part, rowHeaderDRF, modelDRF_part_total, lookupCompany.getValue(), lookupDRF_no.getText().trim());
		btnSubmitRet.setEnabled(true);
		btnCancel.setEnabled(true);
		btnSave.setEnabled(false);
		mnidelete.setEnabled(false);
		mniaddrow.setEnabled(false);
		tabCenter.setSelectedIndex(0);

		btnEdit.setEnabled(true);
		mnisetupPV.setEnabled(true);
		mniInactivate.setEnabled(true);
		mniwriteoff.setEnabled(true);
		JOptionPane.showMessageDialog(getContentPane(), "Data refreshed.", "Information",
				JOptionPane.INFORMATION_MESSAGE);
		lookupDRF_no.setEnabled(true);
		lookupDRF_no.setEditable(true);

	}

	public static void cancel() {

		if (!btnSave.isEnabled()) {
			enable_fields(false);
			refresh_tablesMain();
			refresh_fields();
			btnAddNew.setEnabled(true);
			mnidelete.setEnabled(false);
			mnisetupPV.setEnabled(false);
			btnEdit.setEnabled(false);
			btnSave.setEnabled(false);
			btnSubmitRet.setEnabled(false);
			btnCancel.setEnabled(false);
			lblDRF_no.setEnabled(true);
			lookupDRF_no.setEnabled(true);
			lookupDRF_no.setEditable(true);
			mnidelete.setEnabled(false);
			mniaddrow.setEnabled(false);
			tabCenter.setSelectedIndex(0);
			//			mnipaste.setEnabled(false);
			mniwriteoff.setEnabled(false);
			lookupCompany.setEnabled(true);
			btnPreview.setEnabled(false);
		} else {
			if (JOptionPane.showConfirmDialog(null, "Cancel unsaved data?", "Cancel", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				enable_fields(false);
				refresh_tablesMain();
				refresh_fields();
				btnAddNew.setEnabled(true);
				mnidelete.setEnabled(false);
				mnisetupPV.setEnabled(false);
				btnEdit.setEnabled(false);
				btnSave.setEnabled(false);
				btnSubmitRet.setEnabled(false);
				btnCancel.setEnabled(false);
				lblDRF_no.setEnabled(true);
				lookupDRF_no.setEnabled(true);
				lookupDRF_no.setEditable(true);
				//				mnipaste.setEnabled(false);
				mniwriteoff.setEnabled(false);
				lookupCompany.setEnabled(true);

			}
		}

		initialize_comp();
	}

	public void add() {

		setComponentsEditable(pnlNorth, true);
		dteDRFDate.setEnabled(true);
		dteDueDate.setEnabled(true);
		lblRequestType.setEnabled(true);
		lookupRequestType.setEnabled(true);
		btnState(false, false, true, false, false, true);

		lblDRF_no.setEnabled(false);
		lblPV_no.setEnabled(false);
		lookupPV_no.setEnabled(false);
		lookupDRF_no.setEnabled(false);
		txtStatus.setText("ACTIVE"); //default
		lookupPayee.setLookupSQL(getEntityList());
		lookupAvailer.setLookupSQL(getAvailerList());
		lookupPaymentType.setLookupSQL(PayableVoucher.getPayment_type());
		createDRFtable(modelDRF_part, rowHeaderDRF, modelDRF_part_total);
		mnidelete.setEnabled(true);
		mniaddrow.setEnabled(true);
		dteEdited.setDate(null);

		lblPayee.setEnabled(false);
		lblAvailer.setEnabled(false);
		lookupPayee.setEnabled(false);
		lookupAvailer.setEnabled(false);
		tagPayee.setEnabled(false);
		tagAvailer.setEnabled(false);

		lblPayeeType.setEnabled(false);
		lookupPayeeType.setEnabled(false);
		tagPayeeType.setEnabled(false);
		mnisetupPV.setEnabled(false);
		mniwriteoff.setEnabled(false);
		lookupDRF_no.setEnabled(false);
		lookupCompany.setEnabled(false);

		lookupRequestType.requestFocus();
		//		tagDetail.setText(null);
		txtDRFOtherDetails.setEditable(true);
		txtDRFReqRemarks.setEditable(true);
		txtDRFParticulars.setEditable(true);
		txtDRFAttachments.setEditable(true);
	}

	public void edit() {

		enable_fields(true);

		btnEdit.setEnabled(false);
		btnCancel.setEnabled(true);
		btnSave.setEnabled(true);
		btnPreview.setEnabled(false);
		btnSubmitRet.setEnabled(false);

		lblDRF_no.setEnabled(false);
		lookupDRF_no.setEnabled(false);
		lblPV_no.setEnabled(false);
		lookupPV_no.setEnabled(false);

		lookupPayee.setEditable(true);
		lookupPayeeType.setEditable(true);
		lookupPaymentType.setEditable(true);
		lookupPayee.setLookupSQL(getEntityList());
		lookupPayeeType.setLookupSQL(getPayee_type(lookupPayee.getText().trim()));
		lookupPaymentType.setLookupSQL(getPayment_type());
		mnidelete.setEnabled(true);
		mniaddrow.setEnabled(true);
		modelDRF_part.setEditable(true);
		tblDRF_part.setEnabled(true);

		lblDRFDate.setEnabled(false);
		dteDRFDate.setEnabled(false);
		mnisetupPV.setEnabled(true);
		mniwriteoff.setEnabled(false);
		dteEdited.getCalendarButton().setEnabled(false);

	}

	public void save() {

		String missingHeaderMessge = checkCompleteHeaderDetails(); 
		//		String missingFieldMessage = checkCompleteRequestDetails(); 

		if (missingHeaderMessge != null) {
			showWarningMessage(missingHeaderMessge, "Incomplete Header Details");
			return; 
			//		} if (missingFieldMessage != null) {
			//			showWarningMessage(missingFieldMessage, "Incomplete Request Details");
			//			return;
		} else  {
			executeSave();
		}
	}

	public void updateRequest_status(String status) {
		//		String remark = "";
		//		if (status.equals("I")) {
		//			remark = "delete";
		//		} else {
		//			remark = "activate";
		//		}
		//		if (JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to " + remark + " this request?",
		//				"Confirmation", JOptionPane.YES_NO_CANCEL_OPTION,
		//				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
		//			String rplf = lookupDRF_no.getText().trim();
		//			pgUpdate db = new pgUpdate();
		//			updateRPLF_header_status(rplf, db, status);
		//			db.commit();
		//			JOptionPane.showMessageDialog(getContentPane(), "Payment request " + remark + "d.", "Information",
		//					JOptionPane.INFORMATION_MESSAGE);
		//			refresh();
		//		}
	}

	public void submit(String co_id, String drf_no) {

		if(JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to submit this request?", "Confirmation",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_NO_OPTION) {

			pgUpdate db = new pgUpdate(); 

			String sql = "UPDATE rf_drf_header \n"
					+ "SET status_id = 'S'\n"
					+ ", submitted_by = '"+user+"'\n"
					+ ", date_submitted = now()\n"
					+ "WHERE co_id = '"+co_id+"'\n"
					+ "AND drf_no = '"+drf_no+"'\n"
					+ "AND status_id = 'A'";

			db.executeUpdate(sql, true, true);
			JOptionPane.showMessageDialog(getContentPane(), "DRF was submitted successfully.", "Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void returned(String co_id, String drf_no) {

		if(JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to return this request?", "Confirmation",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_NO_OPTION) {

			pgUpdate db = new pgUpdate(); 

			String sql = "UPDATE rf_drf_header \n"
					+ "SET status_id = 'L'\n"
					+ ", returned_by = '"+user+"'\n"
					+ ", date_returned = now()\n"
					+ "WHERE co_id = '"+co_id+"'\n"
					+ "AND drf_no = '"+drf_no+"'\n"
					+ "AND status_id = 'S'";

			db.executeUpdate(sql, true, true);
			JOptionPane.showMessageDialog(getContentPane(), "DRF has been successfully returned.", "Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void preview() {

		Map<String, Object> mapParameters = new HashMap<String, Object>();
		mapParameters.put("co_id", lookupCompany.getText().trim());
		mapParameters.put("logo", this.getClass().getClassLoader().getResourceAsStream("Images/" + company_logo));
		mapParameters.put("user", UserInfo.FullName);
		mapParameters.put("drf_no", lookupDRF_no.getText().trim());

		System.out.println("");
		System.out.println("Value of co_id:" +  lookupCompany.getText().trim());
		System.out.println("Value of logo:" +  company_logo);
		System.out.println("Value of user:" +  UserInfo.FullName);
		System.out.println("Value of drf_no:" +  drf_no);

		// ATD REQUIRED FOR PAYEE(EMPLOYEE)
		if(lookupRequestType.getText().equals("02") && isPayeeEmployee(lookupPayee.getText().trim())) { 
			FncReport.generateReport("/Reports/rptDisbursementRequestForm_with_ATD.jasper", title, mapParameters);
		} else {
			FncReport.generateReport("/Reports/rptDisbursementRequestForm.jasper", title, mapParameters);
		}
	}

	public void executeSave() {

		// INITIAL CHECKING FOR FIRST ROW OF REQUEST DETAILS 
		BigDecimal drf_req_total = (BigDecimal) modelDRF_part_total.getValueAt(0, 6); 
		String init_main_acct_id = (String) modelDRF_part.getValueAt(0, 0); 
		System.out.println("drf_req_total: " + drf_req_total);

		if(init_main_acct_id.isEmpty() && drf_req_total.compareTo(BigDecimal.ZERO) <= 0) {
			showWarningMessage("Input request details", "Incomplete Request");
			return;
		}

		// Check if an account ID is selected
		if (!checkMainAcctID_ifcomplete()) {
			showWarningMessage("Please select an account ID.", "Account ID");
			return; 
		}

		// Check if the DRF amount is valid
		if (!checkDRFAmount()) {
			showWarningMessage("Amount must be greater than zero.", "Amount");
			return;
		}

		if(getTextIfNotPlaceholder(txtDRFParticulars, placeholder_particulars).isEmpty()) {
			showWarningMessage("Please input particulars.", "Particulars");
			return; 
		}

		if(!validateInvoiceSOABill()) {
			return;
		}

		if (JOptionPane.showConfirmDialog(getContentPane(), "Are all entries correct?", "Confirmation",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

			String drf_no = lookupDRF_no.getText().trim();
			String co_id = lookupCompany.getValue(); 

			//SAVING OF NEW DRF
			if (drf_no.equals("")) { 

				drf_no = saveDRF(co_id, modelDRF_part, drf_no); 
				System.out.println("Value of drf_no: " + drf_no);

				lookupDRF_no.setText(drf_no);
				//XXX TODO				insertAudit_trail("Add-Request for Payment", drf_no, db); 
				setDRF_header(co_id, drf_no);
				displayDRF_details(modelDRF_part, rowHeaderDRF, modelDRF_part_total, co_id, drf_no);
				tabCenter.setSelectedIndex(0);
				JOptionPane.showMessageDialog(getContentPane(), "New payment request saved.", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}

			//SAVING FROM EDIT 
			else {
				saveDRF(co_id, modelDRF_part, drf_no); 
				//XXX TODO				insertAudit_trail("Edit-Request for Payment", drf_no, db);
				lookupDRF_no.setText(drf_no);
				setDRF_header(co_id, drf_no);
				displayDRF_details(modelDRF_part, rowHeaderDRF, modelDRF_part_total, lookupCompany.getValue(), drf_no);
				tabCenter.setSelectedIndex(0);
				JOptionPane.showMessageDialog(getContentPane(), "Payment request updated.", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}

			lblDRF_no.setEnabled(true);
			lookupDRF_no.setEnabled(true);
			btnEdit.setEnabled(true);
			btnSave.setEnabled(false);
			btnPreview.setEnabled(true);
			mnisetupPV.setEnabled(true);
			lookupCompany.setEditable(true);
			setCompEnabled(pnlNorth);
			setCompEditable(pnlNorth, false);
			dteDRFDate.getCalendarButton().setEnabled(false);
			dteDueDate.getCalendarButton().setEnabled(false);
			dteEdited.getCalendarButton().setEnabled(false);

		}
	}

	// select, lookup and get statements
	public static String getDRF_no(String co_id, String user) {
		String SQL = "SELECT a.drf_no as \"DRF No.\" \n"
				+ ", a.drf_date as \"DRF Date\"\n"
				+ ", trim(b.entity_name) as \"Payee\"\n"
				+ ", d.status_desc as \"Status\" \n"
				+ "from rf_drf_header a\n"
				+ "left join rf_entity b on a.payee = b.entity_id\n"
				+ "left join mf_record_status d on a.status_id = d.status_id\n"
				+ "where a.co_id = '"+co_id+"'\n"
				+ "and a.created_by = '"+user+"'\n"
				+ "and a.rec_status = 'A'\n"
				+ "order by a.drf_no desc ";

		System.out.println("SQL-getDRFNo: "+ SQL);

		return SQL; 
	}

	// TO RETRIEVE SUBMITTED REQUESTS ONLY
	public static String getDRF_no_accntg(String co_id) { 
		String SQL = "SELECT a.drf_no as \"DRF No.\" \n"
				+ ", a.drf_date as \"DRF Date\"\n"
				+ ", trim(b.entity_name) as \"Payee\"\n"
				+ ", d.status_desc as \"Status\" \n"
				+ "from rf_drf_header a\n"
				+ "left join rf_entity b on a.payee = b.entity_id\n"
				+ "left join mf_record_status d on a.status_id = d.status_id\n"
				+ "where a.co_id = '"+co_id+"'\n"
				+ "and a.status_id in ('A', 'S') \n"
				+ "and a.rec_status = 'A'\n"
				+ "order by a.drf_no desc ";

		System.out.println("SQL-getDRF_no_accntg: "+ SQL);

		return SQL; 
	}


	public static String getProjCostAccount(String boi_acct_id) {
		String SQL = "SELECT a.proj_cost_accnt_desc AS \"Description\"\n"
				+ ", a.proj_cost_accnt_id AS \"Proj. Cost ID\"\n"
				+" , a.boi_acct_id AS \"Main Acct ID\"\n"
				+", (Select acct_name from mf_boi_chart_of_accounts where acct_id = a.boi_acct_id and status_id = 'A') as \"Main Acct Desc\" \n"
				+ ", a.status_id AS \"Status\"\n"
				+ "FROM mf_project_cost_accnts a\n"
				+ "WHERE a.mother_acct_id IS NOT NULL\n"
				+ "AND a.status_id = 'A'\n"
				+ "AND a.rec_status = 'A'\n"
				+ "AND NOT EXISTS (SELECT *\n"
				+ "				FROM mf_project_cost_accnts\n"
				+ "			    WHERE mother_acct_id = a.proj_cost_accnt_id\n"
				+ "			   	AND a.status_id = 'A'\n"
				+ "				AND a.rec_status = 'A')\n"
				+ "AND ('"+boi_acct_id+"' IS NULL OR '"+boi_acct_id+"' = '' OR a.boi_acct_id = '"+boi_acct_id+"')";

		System.out.println("SQL-getProjCostAccount: "+ SQL);

		return SQL; 
	}

	public String getRequestTypeAcctng() {

		String sql = "select drf_type_id as \"Type ID\"\n"
				+ ", trim(drf_type_desc) as \"Description\" \n"
				+ "from mf_drf_type \n"
				+ "where status_id = 'A' \n"
				+ "order by drf_type_id::INT";

		System.out.println("");
		System.out.println("Get DRF Type: " + sql);

		return sql;

	}

	public String getRequestTypeNonAcctng() {

		String sql = "select drf_type_id as \"Type ID\"\n"
				+ ", trim(drf_type_desc) as \"Description\"\n"
				+ "from mf_drf_type \n"
				+ "where status_id = 'A'\n"
				+ "and non_acctng_access\n"
				+ "order by drf_type_id::INT;";

		System.out.println("");
		System.out.println("Get DRF Type: " + sql);

		return sql;

	}

	public static String getEntityList() {

		String sql = "Select entity_kind as \"KIND\", vat_registered as \"VAT\"\n"
				+ "	, TRIM(CASE WHEN tin_no = '' OR tin_no IS NULL THEN '' ELSE \n"
				+ "		CONCAT(substr(trim(replace(tin_no, '-', '')), 1, 3), '-',\n"
				+ "				substr(trim(replace(tin_no,'-','')), 4, 3), '-',\n"
				+ "				substr(trim(replace(tin_no,'-','')), 7, 3), '-',\n"
				+ "				substr(trim(replace(tin_no,'-','')), 10, 3),\n"
				+ "				(CASE WHEN substr(trim(replace(tin_no,'-','')), 10, 3) = '' OR \n"
				+ "					substr(trim(replace(tin_no,'-','')), 10, 3) IS NULL THEN '000' ELSE '' END)\n"
				+ "		) END) AS \"TIN\", TRIM(entity_id) as \"ENTITY ID\", TRIM(entity_name) as \"ENTITY NAME\"\n" 
				+ "	FROM rf_entity \n" 
				+ "	WHERE status_id = 'A' \n"
				+ "	ORDER by \"ENTITY NAME\"";

		FncSystem.out("Get Payee List: ", sql);
		return sql;

	}

	public static String getAvailerList() {

		String sql = "SELECT TRIM(a.entity_id) AS \"ENTITY ID\"\n"
				+ ", TRIM(b.entity_name) AS \"NAME\"\n"
				+ ", b.entity_kind AS \"KIND\"\n"
				+ ", b.vat_registered AS \"VAT\"\n"
				+ ", a.div_code AS \"DIV ID\"\n"
				+ ", c.div_name AS \"DIVISION\"\n"
				+ "FROM rf_employee a \n"
				+ "INNER JOIN rf_entity b ON b.entity_id = a.entity_id and b.status_id = 'A'\n"
				+ "INNER JOIN mf_division c ON c.div_code = a.div_code 	\n"
				+ "WHERE a.status_id = 'A';";

		FncSystem.out("Get Availer: ", sql);
		return sql;

	}


	public String getDivision() {

		String sql = "select div_code as \"Div Code\"\n"
				+ ", status_id as \"Status\"\n"
				+ ", trim(div_name) as \"Division Name\"\n"
				+ ", trim(div_alias) as \"Div Alias\"\n"
				+ "from mf_division \n"
				+ "where status_id = 'A'; ";

		FncSystem.out("SQL-Get Division: ", sql);
		return sql;

	}

	public String getProject(String co_id) {

		String sql = "select a.proj_id as \"Project ID\"\n"
				+ ", trim(a.proj_name) as \"Project Name\"\n"
				+ ", a.proj_alias as \"Project Alias\"\n"
				+ ", a.vatable as \"Vatable\" \n"
				+ "from mf_project a \n"
				+ "where a.co_id = '"+co_id+"' \n"
				+ "and a.status_id ='A'";
		FncSystem.out("SQL-getProject", sql);
		return sql;

	}

	//	public static String getReferenceDoc() { //XXX NOT USED
	//
	//		String sql = "select trim(doc_id) as \"Doc ID\"\n"
	//				+ ", trim(doc_desc) as \"Doc Name\"\n"
	//				+ ", trim(status_id) as \"Status\"\n"
	//				+ ", trim(doc_alias) as \"Doc Alias\"\n"
	//				+ "from mf_system_doc\n"
	//				+ "where status_id = 'A'\n"
	//				+ "order by doc_id";
	//
	//		FncSystem.out("SQL-getReferenceDoc", sql);
	//		return sql;
	//
	//	}

	public String getWTaxID(String entity_id) {

		String sql = "select a.wtax_id as \"WTax ID\"\n"
				+ ", trim(b.entity_type_desc) as \"Description\"\n"
				+ ", a.wtax_rate as \"Rate (%)\"\n"
				+ ", a.atc_code as \"Code\"\n"
				+ "from mf_withholding_tax a\n"
				+ "left join mf_entity_type b On b.wtax_id = a.wtax_id and b.status_id = 'A'\n"
				+ "left join rf_entity_assigned_type c on c.entity_type_id = b.entity_type_id and c.status_id = 'A'\n"
				+ "where a.status_id = 'A'\n"
				+ "and c.entity_id = '"+entity_id+"'\n"
				+ "order by a.wtax_id";

		FncSystem.out("SQL-getWTaxID", sql);
		return sql;

	}

	public static String[] getWTaxIdAndRate(String payee_type) {
		String wtax_id = "";
		Double wtax_rate = 0.00;

		String SQL = "SELECT a.wtax_id, b.wtax_rate " +
				"FROM mf_entity_type a " +
				"LEFT JOIN mf_withholding_tax b ON b.wtax_id = a.wtax_id AND b.status_id = 'A' " +
				"WHERE a.entity_type_id = ? AND a.status_id = 'A'";

		System.out.println("SQL-getWTaxIdAndRate : " + SQL);

		Object[] params = new Object[] { payee_type };  

		pgSelect db = new pgSelect();
		db.select(SQL, params);

		if (db.isNotNull()) {
			wtax_id = (String) db.getResult()[0][0];
			wtax_rate = (Double) db.getResult()[0][1];
		} 

		return new String[] { wtax_id, String.valueOf(wtax_rate) };
	}


	public String getChartofAccount() {

		String sql = "select trim(status_id) as \"Status\"\n"
				+ ", trim(acct_type) as \"Type\"\n"
				+ ", trim(acct_id) as \"Acct ID\"\n"
				+ ", trim(acct_name) as \"Acct Name\"\n"
				+ ", bs_is as \"Balance\"\n"
				+ "from mf_boi_chart_of_accounts\n"
				+ "where status_id = 'A' \n"
				+ "and (w_subacct is null OR filtered = false)\n"
				+ "order by acct_id ";

		System.out.println("SQL-getChartofAccount : " + sql);
		return sql;

	}

	public static String getPayee_type(String entity_id) {

		String sql = "select a.entity_type_id as \"Entity Type ID\"\n"
				+ ", trim(b.entity_type_desc) as \"Description\"\n"
				+ ", c.wtax_id as \"WTAX ID\"\n"
				+ ", c.wtax_rate as \"WTAX Rate\"\n"
				+ "from (select * from rf_entity_assigned_type \n"
				+ "	  where trim(entity_id) =  '"+entity_id+"' and status_id = 'A' )a\n"
				+ "left join mf_entity_type b on a.entity_type_id = b.entity_type_id\n"
				+ "left join mf_withholding_tax c on b.wtax_id = c.wtax_id";

		System.out.println("SQL-getPayee_type : " + sql);

		return sql;

	}

	public static Object[] getDRFdetails(String co_id, String drf_no) {

		String strSQL = "select \n"
				+ "a.drf_no\n"
				+ ", a.drf_date\n"
				+ ", a.drf_due_date\n"
				+ ", '' as pv_no\n"
				+ ", a.drf_type_id\n"
				+ ", trim(c.drf_type_desc) as drf_desc\n"
				+ ", a.payee\n"
				+ ", a.availer\n"
				+ ", trim(d.entity_name) as payee_name	\n"
				+ ", trim(coalesce(e.entity_name,'')) as availer_name	\n"
				+ ", a.payee_type_id		\n"
				+ ", trim(f.entity_type_desc) as payee_type_desc\n"
				+ ", g.status_desc\n"
				+ ", trim(a.particulars) as particulars	\n"
				+ ", trim(a.other_details) as other_details\n"
				+ ", trim(a.requesters_remarks) as requesters_remarks\n"
				+ ", trim(a.attachments) as attachments\n"
				+ ", a.date_edited\n"
				+ ", a.payment_type_id \n"
				+ ", h.payment_type_desc\n"
				+ ", (Select div_id from rf_drf_detail where co_id = a.co_id and drf_no = a.drf_no and status_id = 'A' limit 1) as div_id\n"
				+ ", a.created_by\n"
				+ "from rf_drf_header a\n"
				+ "-- left join rf_pv_header b on a.rplf_no = b.rplf_no and a.co_id = b.co_id where b.status_id != 'I'\n"
				+ "left join mf_drf_type c on a.drf_type_id = c.drf_type_id\n"
				+ "left join rf_entity d on a.payee = d.entity_id\n"
				+ "left join rf_entity e on a.availer = e.entity_id\n"
				+ "left join mf_entity_type f on a.payee_type_id = f.entity_type_id\n"
				+ "left join mf_record_status g on a.status_id = g.status_id\n"
				+ "left join mf_payment_type h on a.payment_type_id = h.payment_type_id and h.status_id = 'A'\n"
				+ "where a.co_id = '"+co_id+"' "
				+ "and a.drf_no = '"+drf_no+"' ";

		System.out.printf("SQL-getDRFdetails: %s", strSQL);
		pgSelect db = new pgSelect();
		db.select(strSQL);

		if (db.isNotNull()) {
			return db.getResult()[0];
		} else {
			return null;
		}


	}

	public static String getPayment_type() {

		String sql = "Select payment_type_id, payment_type_desc\n"
				+ "from mf_payment_type\n"
				+ "where status_id = 'A';";

		return sql; 
	}

	public static String sql_getCVno(String pv_no, String comp) {

		String cv_no = "";

		String SQL = "select cv_no from rf_pv_header where pv_no = '" + pv_no + "' and co_id = '" + comp
				+ "' and pv_no is not null \n";

		System.out.printf("SQL-getCV_no : %s", SQL);
		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if ((String) db.getResult()[0][0] == null || db.getResult()[0][0].equals("null")) {
				cv_no = "";
			} else {
				cv_no = (String) db.getResult()[0][0];
			}
		} else {
			cv_no = "";
		}

		return cv_no;
	}

	public static String sql_getCVstatus(String cv_no, String comp) {

		String status = "";

		String SQL = "select status_id from rf_cv_header where cv_no = '" + cv_no + "' and co_id = '" + comp + "'";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if ((String) db.getResult()[0][0] == null || db.getResult()[0][0].equals("null")) {
				status = "";
			} else {
				status = (String) db.getResult()[0][0];
			}
		} else {
			status = "";
		}

		return status;
	}

	public static String sql_getDiv(String dept_id) {

		String div = "";

		String SQL = "select division_code from mf_department where dept_code = '" + dept_id + "' ";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if ((String) db.getResult()[0][0] == null || db.getResult()[0][0].equals("null")) {
				div = "";
			} else {
				div = (String) db.getResult()[0][0];
			}
		} else {
			div = "";
		}

		return div;
	}

	public static String sql_Acctname(String acct_id) {

		String acct_name = "";

		String SQL = "select acct_name from mf_boi_chart_of_accounts where trim(acct_id) = '" + acct_id + "' ";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if ((String) db.getResult()[0][0] == null || db.getResult()[0][0].equals("null")) {
				acct_name = "";
			} else {
				acct_name = (String) db.getResult()[0][0];
			}
		} else {
			acct_name = "";
		}

		return acct_name;
	}

	public static Integer sql_getNextWriteoffno() {

		Integer nxt_writeoff_no = 0;

		String SQL = "select max(coalesce(writeoff_no,0)) +1  from rf_ca_writeoff where co_id = '" + co_id + "' ";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if ((Integer) db.getResult()[0][0] == null || db.getResult()[0][0].equals("null")) {
				nxt_writeoff_no = 1;
			} else {
				nxt_writeoff_no = (Integer) db.getResult()[0][0];
			}

		} else {
			nxt_writeoff_no = 1;
		}

		return nxt_writeoff_no;
	}

	public static String sql_getLiquidatedAmt(Integer line_no, String drf_no) {

		String liquidated_amt = "";

		String SQL = "select " + "sum(coalesce(b.exp_amt)) \n" + "from rf_liq_header a  \n"
				+ "left join rf_liq_detail b on a.liq_no = b.liq_no \n" + "" + "where a.drf_no = '" + drf_no + "' \n"
				+ "and b.line_no = " + line_no + "  \n" + "and a.co_id = '" + co_id + "' \n\n";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if (db.getResult()[0][0] == null || db.getResult()[0][0].equals("null")) {
				liquidated_amt = "0.00";
			} else {
				liquidated_amt = db.getResult()[0][0].toString();
			}
		} else {
			liquidated_amt = "0.00";
		}

		return liquidated_amt;
	}

	public static String sql_getAvailer() {

		String a = "";

		String SQL = "select entity_id from rf_request_detail where drf_no = '" + lookupDRF_no.getText() + "'\r\n"
				+ "and co_id = '" + co_id + "' ";

		System.out.printf("SQL-sql_getAvailer", SQL);
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

	public static String sql_getAvailer_wtaxID() {

		String a = "";

		String SQL = "select wtax_id from rf_request_detail where drf_no = '" + lookupDRF_no.getText() + "'\r\n"
				+ "and co_id = '" + co_id + "' ";

		System.out.printf("SQL-sql_getAvailer_wtaxID", SQL);
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

	public static Double sql_getAvailer_wtaxRate() {

		double a = 0.00;

		String SQL = "select wtax_rate from rf_request_detail where drf_no = '" + lookupDRF_no.getText() + "'\r\n"
				+ "and co_id = '" + co_id + "' ";

		System.out.printf("SQL-ql_getAvailer_wtaxRate", SQL);
		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if (db.getResult()[0][0].toString() == null || db.getResult()[0][0].toString().equals("null")) {
				a = 0.00;
			} else {
				a = Double.parseDouble(db.getResult()[0][0].toString());
			}

		} else {
			a = 0.00;
		}

		return a;
	}

	public static String sql_getCompanyLogo(String co_id) {

		String a = "";

		String SQL = "select company_logo from mf_company where co_id = '"+co_id+"'";

		System.out.printf("SQL-sql_getCompanyLogo", SQL);
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

	public static String sql_getProj(String sub_proj) {

		String proj_id = "";

		String SQL = "select proj_id from mf_sub_project where sub_proj_id = '" + sub_proj + "' ";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if ((String) db.getResult()[0][0] == null || db.getResult()[0][0].equals("null")) {
				proj_id = "";
			} else {
				proj_id = (String) db.getResult()[0][0];
			}
		} else {
			proj_id = "";
		}

		return proj_id;
	}

	public static String sql_getPV(String req) {

		String pvNO = "";

		String SQL = "select pv_no from rf_pv_header where pv_no = '" + req + "' ";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if ((String) db.getResult()[0][0] == null || db.getResult()[0][0].equals("null")) {
				pvNO = "";
			} else {
				pvNO = (String) db.getResult()[0][0];
			}
		} else {
			pvNO = "";
		}

		return pvNO;
	}

	// Validation of Request Header Details
	private String checkCompleteHeaderDetails() {

		if(lookupRequestType.getText().isEmpty()) {
			return "Please select request type."; 
		}

		if(lookupPayee.getText().isEmpty()) {
			return "Please select payee."; 
		} 

		if(lookupAvailer.getText().isEmpty()) {
			return "Please select availer."; 
		} 

		if(lookupPayeeType.getText().isEmpty()) {
			return "Please select payee type."; 
		} 

		if(lookupPaymentType.getText().isEmpty()) {
			return "Please select payment type."; 
		} 

		return null;
	}

	// Validation of Invoice / SOA Bill Data
	private Boolean validateInvoiceSOABill() {
		boolean isValid = true;

		int rowCount = modelDRF_part.countRowsWithNonEmptyAcctID();
		System.out.println("Row count: " + rowCount);

		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
			int rowNum = (rowIndex+1); 
			System.out.println("Row num: " + rowNum);

			String invoice_no = (String) modelDRF_part.getValueAt(rowIndex, 16);
			String invoice_date =  modelDRF_part.getValueAt(rowIndex, 17) != null ? (String) modelDRF_part.getValueAt(rowIndex, 17).toString() : "null";
			
			System.out.println("");
			System.out.println("Values before saving");
			System.out.println("Invoice No: " + invoice_no);
			System.out.println("Invoice Date: " + invoice_date);
			
			String soa_bill_no = (String) modelDRF_part.getValueAt(rowIndex, 18); 
			String soa_bill_date =  modelDRF_part.getValueAt(rowIndex, 19) != null ? (String) modelDRF_part.getValueAt(rowIndex, 19).toString() : "null";
			
			System.out.println("SOA/Bill No: " + soa_bill_no);
			System.out.println("SOA/Bill Date: " + soa_bill_date);
			
			if(lookupRequestType.getText().trim().equals("01") || lookupRequestType.getText().trim().equals("17")) {
				if((invoice_no == null || invoice_no.isEmpty()) && invoice_date.equals("null")) {
					showWarningMessage("Please input Invoice Details at row #" + rowNum, "Lacking Invoice Details");
					isValid = false; 
				}
				
				// Check for incomplete Invoice details
				if ((invoice_no != null && !invoice_no.isEmpty()) && invoice_date.equals("null")) {
					showWarningMessage("Please input Invoice Date at row #" + rowNum, "Incomplete detail");
					isValid = false;
				}

				if (!invoice_date.equals("null") && (invoice_no == null || invoice_no.isEmpty())) {
					showWarningMessage("Please input Invoice No at row #" + rowNum, "Incomplete detail");
					isValid = false;
				}
			}
			
			if(lookupRequestType.getText().trim().equals("14") || lookupRequestType.getText().trim().equals("16")) {
				if((soa_bill_no == null || soa_bill_no.isEmpty()) && soa_bill_date.equals("null")) {
					showWarningMessage("Please input SOA/Bill Details at row #" +rowNum, "Lacking SOA / Bill Details");
					isValid = false; 
				}
				
				// Check for incomplete SOA/Bill details
				if ((soa_bill_no != null && !soa_bill_no.isEmpty()) && soa_bill_date.equals("null")) {
					showWarningMessage("Please input SOA / Bill Date at row #" + rowNum, "Incomplete detail");
					isValid = false;
				}

				if (!soa_bill_date.equals("null") && (soa_bill_no == null || soa_bill_no.isEmpty())) {
					showWarningMessage("Please input SOA / Bill No at row #" + rowNum, "Incomplete detail");
					isValid = false;
				}
			}
		}

		return isValid;
	}

	//	// Validation of Request Details
	//	private String checkCompleteRequestDetails() {
	//
	//		// Check for each field and return an appropriate message if empty
	//		if (txtDRFParticulars.getText().isEmpty()) {
	//			return "DRF Particulars is empty.";
	//		}
	//		if (txtDRFOtherDetails.getText().isEmpty()) {
	//			return "DRF Other Details is empty.";
	//		}
	//		if (txtDRFReqRemarks.getText().isEmpty()) {
	//			return "DRF Request Remarks is empty.";
	//		}
	//		if (txtDRFAttachments.getText().isEmpty()) {
	//			return "DRF Attachments is empty.";
	//		}
	//
	//		// If all fields are filled, return null (indicating no issues)
	//		return null;
	//	}

	//	private Boolean checkDRFAmount() {
	//		boolean isValid = true; 
	//
	//		int rowCount = tblDRF_part.getModel().getRowCount();  
	//		System.out.println("Row count:" + rowCount);
	//		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) { 
	//			System.out.println("Row num:" + rowIndex);
	//			String main_acct_id = (String) modelDRF_part.getValueAt(rowIndex, 0);  
	//			System.out.println("Value of main_acct_id: " + main_acct_id);
	//			System.out.println("Value of main_acct_id: " + main_acct_id.length());
	//			
	//			String drfAmtStr = modelDRF_part.getValueAt(rowIndex, 5).toString().trim();
	//			System.out.println("drfAmtStr: " + drfAmtStr);
	//
	//			// Only proceed if the account ID is not empty
	//			if (!main_acct_id.isEmpty() || main_acct_id == null ) {
	//				System.out.println("Is main_acct_id empty? " + main_acct_id.isEmpty());
	//				
	//				try {
	//					if (!drfAmtStr.isEmpty()) {
	//						double drf_amt = Double.parseDouble(drfAmtStr);
	//						System.out.println("drf_amt: " + drf_amt);
	//
	//						// Check if the DRF amount is greater than 0
	//						if (drf_amt <= 0) {
	//							System.out.println("Invalid DRF amount at row " + rowIndex + ". Amount must be greater than 0.");
	//							isValid = false;  
	//							break; 
	//						}
	//					}	
	//				} catch (NumberFormatException e) {
	//					System.out.println("Invalid DRF amount format at row " + rowIndex + ": " + e.getMessage());
	//					isValid = false;  
	//				}
	//			} else { 
	//				System.out.println("pumasok dito sa else!");
	//				double drf_amt = Double.parseDouble(drfAmtStr);
	//				if(drf_amt >= 0) {
	//					System.out.println("Main Acct ID is Empty!");
	//					isValid = false; 
	//				}
	//			
	//			}
	//		}
	//
	//		System.out.println(isValid);
	//
	//		return isValid;
	//	}

	private Boolean checkDRFAmount() {
		boolean isValid = true;

		int rowCount = tblDRF_part.getModel().getRowCount();
		System.out.println("Row count: " + rowCount);

		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
			System.out.println("Row num: " + rowIndex);

			// Get the value of main_acct_id and check for null/empty
			String main_acct_id = (String) modelDRF_part.getValueAt(rowIndex, 0);
			System.out.println("Value of main_acct_id: " + main_acct_id);

			// Get the DRF amount string and trim it
			String drfAmtStr = modelDRF_part.getValueAt(rowIndex, 6).toString().trim();
			System.out.println("drfAmtStr: " + drfAmtStr);

			// Only proceed if the main_acct_id is valid (not null or empty)
			if (main_acct_id != null && !main_acct_id.isEmpty()) {
				System.out.println("main_acct_id is valid.");

				// Check if drfAmtStr is not empty before parsing
				if (!drfAmtStr.isEmpty()) {
					try {
						double drf_amt = Double.parseDouble(drfAmtStr);
						System.out.println("Parsed DRF amount: " + drf_amt);

						// Check if DRF amount is greater than zero
						if (drf_amt <= 0) {
							System.out.println("Invalid DRF amount at row " + rowIndex + ". Amount must be greater than 0.");
							isValid = false;
							break;  // Exit loop on first invalid row
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid DRF amount format at row " + rowIndex + ": " + e.getMessage());
						isValid = false;
						break;  // Exit loop on invalid format
					}
				} else {
					System.out.println("DRF amount is empty at row " + rowIndex);
					isValid = false;  // DRF amount is empty, mark as invalid
					break;  // Exit loop on empty amount
				}
			} 

			else {
				double drf_amt = Double.parseDouble(drfAmtStr);
				if(main_acct_id.isEmpty() && drf_amt == 0.00)// Main account ID is invalid (null or empty)
					isValid = true;
				//	            System.out.println("Main Acct ID is empty or null at row " + rowIndex);

				//	            // If main_acct_id is empty, the DRF amount should not be checked
				//	            if (!drfAmtStr.isEmpty()) {
				//	                System.out.println("Invalid DRF amount provided when Main Acct ID is empty at row " + rowIndex);
				//	                isValid = false;
				//	                break;  // Exit loop on invalid entry
				//	            }
			}
		}

		System.out.println("");
		System.out.println("Valid DRF Amount: " + isValid);
		return isValid;
	}

	private Boolean checkMainAcctID_ifcomplete() {
		boolean isComplete = true; 

		int rowCount = tblDRF_part.getModel().getRowCount(); 
		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {

			try {
				// Get the DRF amount and check if it's greater than 0
				Double drf_amt = Double.parseDouble(modelDRF_part.getValueAt(rowIndex, 6).toString().trim());

				if (drf_amt > 0) {
					// Get the Main Acct ID from the table and check if it's empty
					String main_acct_id = (String) modelDRF_part.getValueAt(rowIndex, 0);
					if (main_acct_id.isEmpty() || main_acct_id == null ) {
						isComplete = false;  // If Main Acct ID is missing, return false
						break;  		                }
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid DRF amount at row " + rowIndex + ": " + e.getMessage());
				isComplete = false;  // If Petty Cash amount is invalid, return false
				break;
			}
		}
		
		System.out.println("");
		System.out.println("Complete Acct ID: " + isComplete);
		return isComplete;
	}


	public static Boolean isPVcreated() {

		Boolean isPVcreated = false;

		String SQL = "select trim(status_id) from rf_pv_header where pv_no = '" + lookupDRF_no.getText()
		+ "' and status_id in ( 'A', 'F', 'P' ) and co_id = '" + co_id + "' ";

		System.out.printf("SQL-isPVcreated: %s", SQL);
		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			isPVcreated = true;
		} else {
			isPVcreated = false;
		}

		return isPVcreated;

	}

	public static Boolean isPVcreated_but_inactive() {

		Boolean isPVcreated = false;

		String SQL = "select trim(status_id) from rf_pv_header " + "where pv_no = '" + lookupDRF_no.getText()
		+ "' and status_id in ( 'I' ) and co_id = '" + co_id + "' ";

		System.out.printf("SQL-isPVcreated_but_inactive: %s", SQL);
		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			isPVcreated = true;
		} else {
			isPVcreated = false;
		}

		return isPVcreated;

	}

	public static Boolean isItemAnAcct(String item) {

		Boolean isItemAnAcct = false;

		String SQL = "select acct_id from mf_boi_chart_of_accounts where acct_id = '" + item + "' and status_id = 'A' ";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			isItemAnAcct = true;
		} else {
			isItemAnAcct = false;
		}

		return isItemAnAcct;

	}

	public static Boolean isItemOffice(String exec_off_code) {

		Boolean isItemOffice = false;

		String SQL = "select trim(exec_office_code) from mf_exec_office where exec_office_code = '"
				+ exec_off_code.trim() + "' and status_id = 'A' AND rec_status != 'D'";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			isItemOffice = true;
		} else {
			isItemOffice = false;
		}

		return isItemOffice;

	}

	public static Boolean isItemDivision(String div_code) {

		Boolean isItemDept = false;

		String SQL = "select dept_code from mf_department where dept_code = '" + item.trim() + "'  ";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			isItemDept = true;
		} else {
			isItemDept = false;
		}

		return isItemDept;

	}

	public static Boolean isItemProject(String item) {

		Boolean isItemProj = false;

		String SQL = "select proj_id from mf_project where proj_id = '" + item.trim() + "'  ";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			isItemProj = true;
		} else {
			isItemProj = false;
		}

		return isItemProj;

	}

	public static Boolean isItemSubProject(String item) {

		Boolean isItemSubProj = false;

		String SQL = "select sub_proj_id from mf_unit_info where sub_proj_id = '" + item.trim() + "'  ";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			isItemSubProj = true;
		} else {
			isItemSubProj = false;
		}

		return isItemSubProj;

	}

	public static Boolean entityhasUnliquidatedCA(String entity_id) {

		Boolean entityhasUnliquidatedCA = false;

		String SQL = "select\r\n" + "a.drf_no\r\n" + "		\r\n" + "from rf_request_header a \r\n"
				+ "left join rf_request_detail b on trim(a.drf_no) = trim(b.drf_no) and a.co_id = b.co_id \r\n"
				+ "left join rf_pv_header c on trim(a.drf_no) = trim(c.pv_no) and a.co_id = c.co_id \r\n"
				+ "left join rf_cv_header d on trim(c.cv_no) = trim(d.cv_no) and a.co_id = d.co_id \r\n"
				+ "left join rf_liq_header e on trim(a.drf_no) = trim(e.drf_no) and a.co_id = e.co_id \r\n"
				+ "left join (\r\n" + "				select  a.drf_no, a.co_id, a.days_overdue from (\r\n"
				+ "				select\r\n" + "					a.drf_no,\r\n"
				+ "					((now() - d.date_paid - interval '3 day' - ((((now()- d.date_paid))/7)*2)  ) -\r\n"
				+ "					( case when acct_id in ( '03-02-04-000', '03-02-05-000', '01-03-06-001', '01-03-06-000', '04007001' ) then interval '30 day'\r\n"
				+ "					else ( case when acct_id like  '08-01-05%' or acct_id like  '08-02-05%' then interval '60 day' else interval '0 day' end ) end )\r\n"
				+ "					) as days_overdue,\r\n" + "					a.co_id\r\n"
				+ "					from (select * from rf_request_detail where status_id != 'I') a\r\n"
				+ "					join (select * from rf_request_header where status_id != 'I' and rplf_type_id in ( '02', '07', '12', '13' ) ) aa on a.drf_no = aa.drf_no and a.co_id = aa.co_id\r\n"
				+ "					left join (select * from rf_pv_header where status_id != 'I') c on trim(a.drf_no) = trim(c.pv_no) and a.co_id = c.co_id\r\n"
				+ "					left join (select * from rf_cv_header where status_id != 'I') d on trim(c.cv_no) = trim(d.cv_no) and a.co_id = d.co_id\r\n"
				+ "					) a\r\n"
				+ "			) f on trim(a.drf_no) = trim(f.drf_no) and a.co_id = f.co_id" + "\r\n"
				+ "where a.rplf_type_id in ( '02', '07', '12', '13' )\r\n"
				+ "and trim(a.drf_no) not in ( select trim(drf_no) from rf_liq_header where status_id in ( 'G', 'D' ) )\r\n"
				+ "and d.status_id = 'P' \r\n" + "and d.proc_id in (6,7) \r\n" +
				// "and e.liq_no is null \r\n" +
				"and a.entity_id2 = '" + entity_id + "' \n"
				+ "and (round((extract(epoch from f.days_overdue)/86400)::int)) >= 0 \n\n";

		System.out.printf("SQL-entityhasUnliquidatedCA: %s", SQL);
		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			entityhasUnliquidatedCA = true;
		} else {
			entityhasUnliquidatedCA = false;
		}

		return entityhasUnliquidatedCA;

	}

	public static Boolean isRPLFalreadywroteoff() {

		Boolean isRPLFalreadywroteoff = false;

		String SQL = "select writeoff_no from rf_ca_writeoff " + "where drf_no = '" + drf_no + "' and co_id = '"
				+ co_id + "' ";

		System.out.printf("SQL-isRPLFalreadywroteoff: %s", SQL);
		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			isRPLFalreadywroteoff = true;
		} else {
			isRPLFalreadywroteoff = false;
		}

		return isRPLFalreadywroteoff;

	}

	public static Boolean isCApaidout() {

		Boolean isCApaidout = false;

		String SQL = "select b.date_paid\r\n" + "\r\n" + "from rf_pv_header a \r\n"
				+ "left join rf_cv_header b on a.cv_no = b.cv_no and a.co_id = b.co_id\r\n"
				+ "where b.status_id = 'P'\r\n" + "and b.proc_id = 5 " + "and a.pv_no = '"
				+ lookupDRF_no.getText().trim() + "'";

		System.out.printf("SQL-isCApaidout: %s", SQL);
		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			isCApaidout = true;
		} else {
			isCApaidout = false;
		}

		return isCApaidout;

	}

	public static Boolean isCAfullyliquidated() {

		Boolean isCAfullyliquidated = false;

		String SQL = "select \r\n" + "\r\n" + "sum(coalesce(c.tran_amt) - coalesce(exp_amt)) \r\n" + "\r\n"
				+ "from rf_request_header a \r\n" + "left join rf_liq_header b on a.drf_no = b.drf_no\r\n"
				+ "left join rf_liq_detail c on b.liq_no = c.liq_no\r\n" + "\r\n" + "where c.status_id = 'A'"
				+ "and a.drf_no = '" + lookupDRF_no.getText().trim() + "'";

		System.out.printf("SQL-isCAfullyliquidated: %s", SQL);
		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if (db.getResult()[0][0] == null || db.getResult()[0][0].equals("null")) {
				isCAfullyliquidated = false;
			} else if (db.getResult()[0][0].toString().equals("0.00") || db.getResult()[0][0].toString().equals("0")) {
				isCAfullyliquidated = true;
			} else {
				isCAfullyliquidated = false;
			}
		} else {
			isCAfullyliquidated = false;
		}

		return isCAfullyliquidated;

	}

	// table operations
//	public static void totalDRF(DefaultTableModel modelMain, DefaultTableModel modelTotal) {
//
//		FncTables.clearTable(modelTotal);// Code to clear modelMain.
//
//		BigDecimal amt_bd = new BigDecimal(0.00);
//		BigDecimal gr_amt_bd = new BigDecimal(0.00);
//		BigDecimal net_amt_bd = new BigDecimal(0.00);
//		BigDecimal wtax_amt_bd = new BigDecimal(0.00);
//		BigDecimal vat_amt_bd = new BigDecimal(0.00);
//		BigDecimal exp_amt_bd = new BigDecimal(0.00);
//		BigDecimal pv_amt_bd = new BigDecimal(0.00);
//		BigDecimal ret_amt_bd = new BigDecimal(0.00);
//		BigDecimal bc_liq_amt_bd = new BigDecimal(0.00);
//		BigDecimal other_liq_amt_bd = new BigDecimal(0.00);
//		BigDecimal dp_recoup_amt_bd = new BigDecimal(0.00);
//
//		for (int x = 0; x < modelMain.getRowCount(); x++) {
//
//			try {
//				amt_bd = amt_bd.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 6).toString()))));
//			} catch (NullPointerException e) {
//				amt_bd = amt_bd.add(new BigDecimal(0.00));
//			}
//
//			try {
//				gr_amt_bd = gr_amt_bd
//						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 24).toString()))));
//			} catch (NullPointerException e) {
//				gr_amt_bd = gr_amt_bd.add(new BigDecimal(0.00));
//			}
//
//			try {
//				net_amt_bd = net_amt_bd
//						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 25).toString()))));
//			} catch (NullPointerException e) {
//				net_amt_bd = net_amt_bd.add(new BigDecimal(0.00));
//			}
//
//			try {
//				wtax_amt_bd = wtax_amt_bd
//						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 30).toString()))));
//			} catch (NullPointerException e) {
//				wtax_amt_bd = wtax_amt_bd.add(new BigDecimal(0.00));
//			}
//
//			try {
//				vat_amt_bd = vat_amt_bd
//						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 27).toString()))));
//			} catch (NullPointerException e) {
//				vat_amt_bd = vat_amt_bd.add(new BigDecimal(0.00));
//			}
//
//			try {
//				exp_amt_bd = exp_amt_bd
//						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 31).toString()))));
//			} catch (NullPointerException e) {
//				exp_amt_bd = exp_amt_bd.add(new BigDecimal(0.00));
//			}
//
//			try {
//				ret_amt_bd = ret_amt_bd
//						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 32).toString()))));
//			} catch (NullPointerException e) {
//				ret_amt_bd = ret_amt_bd.add(new BigDecimal(0.00));
//			}
//
//			try {
//				dp_recoup_amt_bd = dp_recoup_amt_bd
//						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 33).toString()))));
//			} catch (NullPointerException e) {
//				dp_recoup_amt_bd = dp_recoup_amt_bd.add(new BigDecimal(0.00));
//			}
//
//			try {
//				bc_liq_amt_bd = bc_liq_amt_bd
//						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 34).toString()))));
//			} catch (NullPointerException e) {
//				bc_liq_amt_bd = bc_liq_amt_bd.add(new BigDecimal(0.00));
//			}
//
//			try {
//				other_liq_amt_bd = other_liq_amt_bd
//						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 35).toString()))));
//			} catch (NullPointerException e) {
//				other_liq_amt_bd = other_liq_amt_bd.add(new BigDecimal(0.00));
//			}
//
//			try {
//				pv_amt_bd = pv_amt_bd
//						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 36).toString()))));
//			} catch (NullPointerException e) {
//				pv_amt_bd = pv_amt_bd.add(new BigDecimal(0.00));
//			}
//
//		}
//
//		System.out.printf("amt_bd:%s\n", amt_bd);
//		System.out.printf("gr_amt_bd:%s\n", gr_amt_bd);
//		System.out.printf("net_amt_bd:%s\n", net_amt_bd);
//		System.out.printf("vat_amt_bd:%s\n", vat_amt_bd);
//		System.out.printf("wtax_amt_bd:%s\n", wtax_amt_bd);
//		System.out.printf("exp_amt_bd:%s\n", exp_amt_bd);
//		System.out.printf("ret_amt_bd:%s\n", ret_amt_bd);
//		System.out.printf("dp_recoup_amt_bd:%s\n", dp_recoup_amt_bd);
//		System.out.printf("bc_liq_amt_bd:%s\n", bc_liq_amt_bd);
//		System.out.printf("other_liq_amt_bd:%s\n", other_liq_amt_bd);
//		System.out.printf("pv_amt_bd:%s\n", pv_amt_bd);
//
//		modelTotal.addRow(new Object[] { "Total", null, null, null, null, null, amt_bd, null, null, null,
//				null, null, null, null, null, null, null, null, null, null, null, null, null, null,
//				gr_amt_bd, net_amt_bd, null, vat_amt_bd, null, null, wtax_amt_bd,
//				exp_amt_bd, ret_amt_bd, dp_recoup_amt_bd, bc_liq_amt_bd, other_liq_amt_bd, pv_amt_bd, null});
//	}
	
	public static void totalDRF(DefaultTableModel modelMain, DefaultTableModel modelTotal) {

	    // Clear the total model table before updating it.
	    FncTables.clearTable(modelTotal);

	    // Initialize BigDecimal variables for each column we need to sum.
	    BigDecimal amt_bd = BigDecimal.ZERO;
	    BigDecimal gr_amt_bd = BigDecimal.ZERO;
	    BigDecimal net_amt_bd = BigDecimal.ZERO;
	    BigDecimal wtax_amt_bd = BigDecimal.ZERO;
	    BigDecimal vat_amt_bd = BigDecimal.ZERO;
	    BigDecimal exp_amt_bd = BigDecimal.ZERO;
	    BigDecimal pv_amt_bd = BigDecimal.ZERO;
	    BigDecimal ret_amt_bd = BigDecimal.ZERO;
	    BigDecimal bc_liq_amt_bd = BigDecimal.ZERO;
	    BigDecimal other_liq_amt_bd = BigDecimal.ZERO;
	    BigDecimal dp_recoup_amt_bd = BigDecimal.ZERO;

	    // Iterate through each row in the main model to sum the amounts.
	    for (int x = 0; x < modelMain.getRowCount(); x++) {

	        // Sum each relevant column using the helper method
	        amt_bd = addToBigDecimal(amt_bd, modelMain, x, 6);
	        gr_amt_bd = addToBigDecimal(gr_amt_bd, modelMain, x, 24);
	        net_amt_bd = addToBigDecimal(net_amt_bd, modelMain, x, 25);
	        wtax_amt_bd = addToBigDecimal(wtax_amt_bd, modelMain, x, 30);
	        vat_amt_bd = addToBigDecimal(vat_amt_bd, modelMain, x, 27);
	        exp_amt_bd = addToBigDecimal(exp_amt_bd, modelMain, x, 31);
	        ret_amt_bd = addToBigDecimal(ret_amt_bd, modelMain, x, 32);
	        dp_recoup_amt_bd = addToBigDecimal(dp_recoup_amt_bd, modelMain, x, 33);
	        bc_liq_amt_bd = addToBigDecimal(bc_liq_amt_bd, modelMain, x, 34);
	        other_liq_amt_bd = addToBigDecimal(other_liq_amt_bd, modelMain, x, 35);
	        pv_amt_bd = addToBigDecimal(pv_amt_bd, modelMain, x, 36);
	    }

	    // Log the total amounts for debugging (optional).
	    logTotalAmounts(amt_bd, gr_amt_bd, net_amt_bd, vat_amt_bd, wtax_amt_bd, exp_amt_bd, ret_amt_bd, 
	                    dp_recoup_amt_bd, bc_liq_amt_bd, other_liq_amt_bd, pv_amt_bd);

	    // Add the row with the totals to the total model.
	    modelTotal.addRow(new Object[] {
	        "Total", null, null, null, null, null, amt_bd, null, null, null,
	        null, null, null, null, null, null, null, null, null, null, null, null, null, null,
	        gr_amt_bd, net_amt_bd, null, vat_amt_bd, null, null, wtax_amt_bd,
	        exp_amt_bd, ret_amt_bd, dp_recoup_amt_bd, bc_liq_amt_bd, other_liq_amt_bd, pv_amt_bd, null
	    });
	}

	// Helper method to add the value from the table to a BigDecimal.
	private static BigDecimal addToBigDecimal(BigDecimal total, DefaultTableModel model, int row, int col) {
	    try {
	        // Get the value from the table, parse it to double, then convert it to BigDecimal and add to the total.
	        double value = Double.parseDouble(model.getValueAt(row, col).toString());
	        return total.add(BigDecimal.valueOf(value));
	    } catch (NumberFormatException | NullPointerException e) {
	        // If there is any error in parsing (including null values), return the total as is.
	        return total.add(BigDecimal.ZERO);
	    }
	}

	// Method to log the total amounts
	private static void logTotalAmounts(BigDecimal amt_bd, BigDecimal gr_amt_bd, BigDecimal net_amt_bd, 
	                                     BigDecimal vat_amt_bd, BigDecimal wtax_amt_bd, BigDecimal exp_amt_bd,
	                                     BigDecimal ret_amt_bd, BigDecimal dp_recoup_amt_bd, BigDecimal bc_liq_amt_bd,
	                                     BigDecimal other_liq_amt_bd, BigDecimal pv_amt_bd) {
		
	    System.out.printf("amt_bd: %s\n", amt_bd);
	    System.out.printf("gr_amt_bd: %s\n", gr_amt_bd);
	    System.out.printf("net_amt_bd: %s\n", net_amt_bd);
	    System.out.printf("vat_amt_bd: %s\n", vat_amt_bd);
	    System.out.printf("wtax_amt_bd: %s\n", wtax_amt_bd);
	    System.out.printf("exp_amt_bd: %s\n", exp_amt_bd);
	    System.out.printf("ret_amt_bd: %s\n", ret_amt_bd);
	    System.out.printf("dp_recoup_amt_bd: %s\n", dp_recoup_amt_bd);
	    System.out.printf("bc_liq_amt_bd: %s\n", bc_liq_amt_bd);
	    System.out.printf("other_liq_amt_bd: %s\n", other_liq_amt_bd);
	    System.out.printf("pv_amt_bd: %s\n", pv_amt_bd);
	}


	private void clickTableColumn() {
		int column = tblDRF_part.convertColumnIndexToModel(tblDRF_part.getSelectedColumn()); 
		int row = tblDRF_part.convertRowIndexToModel(tblDRF_part.getSelectedRow());

		System.out.println("column : " + column);
		System.out.printf("row : " + row);

		String entity_id = (String) modelDRF_part.getValueAt(row, 8);
		String boi_acct_id = (String) modelDRF_part.getValueAt(row, 0);
		co_id = lookupCompany.getValue(); 

		Integer x[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
				26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36};
		String sql[] = {getChartofAccount(), "", getProjCostAccount(boi_acct_id), "", getDivision(), getProject(co_id), "", "", getEntityList(), getPayee_type(entity_id),
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",  "", getWTaxID(entity_id), "", "", "", "", "", "", "" };
		String lookup_name[] = { "Chart of Account", "Project Cost Account", "Division", "Project", "", "", "Entity", "Entity Type",
				"", "", "", "", "", "", "", "", "", "", "","Withholding Tax", "", "", "", "", "", "",  "", "" };

		System.out.println("column : " + column);
		System.out.println("row : " + row);

		if (column == x[column] && modelDRF_part.isEditable() && sql[column] != "") {
			_JLookupTable dlg = new _JLookupTable(FncGlobal.homeMDI, null, lookup_name[column], sql[column], false);
			dlg.setLocationRelativeTo(FncGlobal.homeMDI);
			if (column == 8) {// payee id
				dlg.setFilterIndex(4);//
			} else if (column == 0) {// acct id
				dlg.setFilterIndex(3);// 
			} else if (column == 2) {// proj cost id
				dlg.setFilterIndex(0);// 	
			} else {
				dlg.setFilterIndex(0);
			}

			dlg.setVisible(true);

			Object[] data = dlg.getReturnDataSet();

			if (data != null && column == 0) { // Main Acct ID
				modelDRF_part.setValueAt(data[2], row, column);
				modelDRF_part.setValueAt(data[3], row, column + 1);

			} else if (data != null && column == 8) { // PayeeID
				modelDRF_part.setValueAt(data[3], row, column);
				modelDRF_part.setValueAt(data[4], row, column + 2); // Payee Name
				modelDRF_part.setValueAt(data[1], row, column + 14);
				computeDRF();

			} else if (data != null && column == 9) { // PayeeType
				modelDRF_part.setValueAt(data[0], row, column);
				modelDRF_part.setValueAt(data[2], row, column + 19);
				modelDRF_part.setValueAt(data[3], row, column + 20);
				computeDRF();

			} else if (data != null && column == 4) { //Div 
				modelDRF_part.setValueAt(data[0], row, column);

			} else if (data != null && column == 2) { // Proj Cost ID
				modelDRF_part.setValueAt(data[1], row, column);
				modelDRF_part.setValueAt(data[2], row, column -2);
				modelDRF_part.setValueAt(data[3], row, column -1);
				modelDRF_part.setValueAt(data[0], row, column + 1);

			} else if (data != null && column == 5) { // Proj ID
				modelDRF_part.setValueAt(data[0], row, column);

			} else if (data != null && column == 6) { // Amount 
				computeDRF();

			} else if (data != null && column == 28) { // WTAX ID
				modelDRF_part.setValueAt(data[0], row, column);
				modelDRF_part.setValueAt(data[2], row, column + 1); // WTAX RATE
				computeDRF();
			}

		}

		else {
		}
		tblDRF_part.packAll();
	}

	private void computeDRF() {

		int selectedRow = tblDRF_part.convertRowIndexToModel(tblDRF_part.getSelectedRow());

		Double amount = parseDoubleWithDefault(modelDRF_part.getValueAt(selectedRow, 6), DEFAULT_AMOUNT);
		Double vatRate = parseDoubleWithDefault(modelDRF_part.getValueAt(selectedRow, 26), DEFAULT_TAX_RATE) / 100;
		Double taxRate = parseDoubleWithDefault(modelDRF_part.getValueAt(selectedRow, 29), DEFAULT_TAX_RATE) / 100;
		Double retentionAmount = parseDoubleWithDefault(modelDRF_part.getValueAt(selectedRow, 32), DEFAULT_AMOUNT);
		Double dpRecoupAmount = parseDoubleWithDefault(modelDRF_part.getValueAt(selectedRow, 33), DEFAULT_AMOUNT);
		Double bcLiquiAmount = parseDoubleWithDefault(modelDRF_part.getValueAt(selectedRow, 34), DEFAULT_AMOUNT);
		Double otherLiquiAmount = parseDoubleWithDefault(modelDRF_part.getValueAt(selectedRow, 35), DEFAULT_AMOUNT);

		// Gross amount is simply the amount (may be adjusted in VATABLE case)
		double grossAmount = amount;
		double netAmount = grossAmount; 

		// VATABLE entity check
		Boolean isVatableEntity = (Boolean) modelDRF_part.getValueAt(selectedRow, 22);

		if (isVatableEntity != null) {
			if (isVatableEntity ) {
				vatRate = DEFAULT_VAT_RATE / 100;
				netAmount = getNetAmount(grossAmount).doubleValue();
				updateVatAndTax(selectedRow, vatRate, grossAmount, netAmount);
			} else {
				resetNonVatable(selectedRow, grossAmount, vatRate, taxRate);
			}
		}

		// Calculate WTax amount
		double wTaxAmount = getWtaxAmount(netAmount, vatRate, taxRate).doubleValue();
		modelDRF_part.setValueAt(wTaxAmount, selectedRow, 30);

		// Calculate PV Amount
		double pvAmount = getPVAmount(grossAmount, wTaxAmount, dpRecoupAmount, retentionAmount, bcLiquiAmount, otherLiquiAmount)
				.doubleValue();

		// Update all required values in the table
		updateValues(selectedRow, grossAmount, netAmount, wTaxAmount, retentionAmount, dpRecoupAmount, bcLiquiAmount, otherLiquiAmount, pvAmount);

		// Log outputs
		logCalculatedValues(grossAmount, vatRate, wTaxAmount, pvAmount);

		// Recalculate total DRF
		totalDRF(modelDRF_part, modelDRF_part_total);
	}

	private Double parseDoubleWithDefault(Object value, double defaultValue) {
		try {
			return value != null ? Double.parseDouble(value.toString()) : defaultValue;
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	private void updateVatAndTax(int selectedRow, double vatRate, double grossAmount, double netAmount) {
		// Set VAT rate and calculate VAT amount
		modelDRF_part.setValueAt(vatRate * 100, selectedRow, 26); // Vat Rate in percentage
		double vatAmount = getVatAmount(netAmount).doubleValue();
		modelDRF_part.setValueAt(vatAmount, selectedRow, 27); // Vat Amount
		modelDRF_part.setValueAt(netAmount, selectedRow, 25); // Net Amount
		double expenseAmount = getExpAmount(grossAmount, vatAmount).doubleValue();
		modelDRF_part.setValueAt(expenseAmount, selectedRow, 31); // Expense Amount
	}


	private void resetNonVatable(int selectedRow, double grossAmount, double vatRate, double taxRate) {
		modelDRF_part.setValueAt(grossAmount, selectedRow, 24); // Set gross amount
		modelDRF_part.setValueAt(grossAmount, selectedRow, 25); // Set net amount (no VAT)
		modelDRF_part.setValueAt(0.00, selectedRow, 26); // VAT rate = 0
		modelDRF_part.setValueAt(0.00, selectedRow, 27); // VAT amount = 0
		modelDRF_part.setValueAt(0.00, selectedRow, 31); // Expense amount = 0
		double wTaxAmount = getWtaxAmount(grossAmount, vatRate, taxRate).doubleValue();
		modelDRF_part.setValueAt(wTaxAmount, selectedRow, 30); // Set WTax amount
	}

	// Method to update all values after calculations
	private void updateValues(int selectedRow, double grossAmount, double netAmount, double wTaxAmount, double retentionAmount, 
			double dpRecoupAmount, double bcLiquiAmount, double otherLiquiAmount, double pvAmount) {
		modelDRF_part.setValueAt(new BigDecimal(grossAmount), selectedRow, 6);
		modelDRF_part.setValueAt(new BigDecimal(grossAmount), selectedRow, 24);
		modelDRF_part.setValueAt(new BigDecimal(netAmount), selectedRow, 25);
		modelDRF_part.setValueAt(new BigDecimal(wTaxAmount), selectedRow, 30);
		modelDRF_part.setValueAt(new BigDecimal(retentionAmount), selectedRow, 32);
		modelDRF_part.setValueAt(new BigDecimal(dpRecoupAmount), selectedRow, 33);
		modelDRF_part.setValueAt(new BigDecimal(bcLiquiAmount), selectedRow, 34);
		modelDRF_part.setValueAt(new BigDecimal(otherLiquiAmount), selectedRow, 35);
		modelDRF_part.setValueAt(new BigDecimal(pvAmount), selectedRow, 36);
	}

	// Method to log calculated values
	private void logCalculatedValues(double grossAmount, double vatRate, double wTaxAmount, double pvAmount) {
		System.out.printf("Gross amount: %.2f\n", grossAmount);
		System.out.printf("VAT rate: %.2f\n", vatRate);
		System.out.printf("WTax amount: %.2f\n", wTaxAmount);
		System.out.printf("PV amount: %.2f\n", pvAmount);
	}

	private static BigDecimal getVatAmount(Double net_amt) {// compute vat amount

		BigDecimal vat_amt = BigDecimal.valueOf(0.00);

		String SQL = "SELECT * FROM fn_compute_vat_amount('" + net_amt + "')";

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

	private static BigDecimal getNetAmount(Double gr_amt) {// compute net amount

		BigDecimal net_amt = BigDecimal.valueOf(0.00);

		String SQL = "SELECT * FROM fn_compute_net_amount('" + gr_amt + "')";

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

	private static BigDecimal getWtaxAmount(Double net_amt, Double vat_rate, Double tax_rate) {// compute wtax amount

		BigDecimal wtax_amt = BigDecimal.valueOf(0.00);

		String SQL = "SELECT * FROM fn_compute_wtax_amount('" + net_amt + "', '" + vat_rate + "', '" + tax_rate + "')";

		FncSystem.out("Display valie of getWtaxAmount SQL", SQL);
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

	private static BigDecimal getExpAmount(Double net_amt, Double vatAmt) {// compute expense amount

		BigDecimal exp_amt = BigDecimal.valueOf(0.00);

		String SQL = "SELECT * FROM fn_compute_expense_amount('" + net_amt + "', '" + vatAmt + "')";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if ((BigDecimal) db.getResult()[0][0] == BigDecimal.valueOf(0.00)) {
				exp_amt = BigDecimal.valueOf(0.00);
			} else {
				exp_amt = (BigDecimal) db.getResult()[0][0];
			}

		} else {
			exp_amt = BigDecimal.valueOf(0.00);
		}

		return exp_amt;

	}

	private static BigDecimal getPVAmount(Double gr_amt, Double wtaxAmt, Double dp_recoup_amt, Double retention_amt,
			Double bc_liqui_amt, Double other_liqui_amt) {// compute net amount

		BigDecimal pv_amt = BigDecimal.valueOf(0.00);

		String SQL = "SELECT * FROM fn_compute_pv_amount('" + gr_amt + "', '" + wtaxAmt + "', '" + retention_amt + "', '"
				+ dp_recoup_amt + "', '" + bc_liqui_amt + "', '" + other_liqui_amt + "')";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			if ((BigDecimal) db.getResult()[0][0] == BigDecimal.valueOf(0.00)) {
				pv_amt = BigDecimal.valueOf(0.00);
			} else {
				pv_amt = (BigDecimal) db.getResult()[0][0];
			}

		} else {
			pv_amt = BigDecimal.valueOf(0.00);
		}

		return pv_amt;

	}

	private void removeRow() {

		int[] selectedRows = tblDRF_part.getSelectedRows();

		if (selectedRows.length == 0) {
			JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Remove row?", "Confirmation",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		for (int x = selectedRows.length - 1; x > -1; x--) {
			int row = selectedRows[x];

			modelDRF_part.removeRow(row);
			((DefaultListModel) rowHeaderDRF.getModel()).removeElementAt(row);
		}

		adjustRowHeight();
		computeDRF();
	}

	private void AddRow() { 

		Boolean entityVatable = (Boolean) modelDRF_part.getValueAt(0, 21);

		modelDRF_part.addRow(new Object[] { null, "", null, "", "", "", new BigDecimal(0.00), null, "", "", "",
				null, null, null, null, null, "", null, "", null, "", null, entityVatable, null, new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), "", new BigDecimal(0.00),
				new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), null});

		setPayee();
		((DefaultListModel) rowHeaderDRF.getModel()).addElement(modelDRF_part.getRowCount());
		adjustRowHeight();
	}

	private static void adjustRowHeight() {

		scrollDRF_part_total
		.setRowHeaderView(FncTables.getRowHeader_Footer(Integer.toString(tblDRF_part.getRowCount())));
		tblDRF_part.packAll();
		if (tblDRF_part.getColumnModel().getColumn(1).getPreferredWidth() >= 200) {
			tblDRF_part.getColumnModel().getColumn(1).setPreferredWidth(200);
		}

		for (int row = 0; row < tblDRF_part.getRowCount(); row++) {
			tblDRF_part.setRowHeight(row, 23);
			((DefaultListModel) rowHeaderDRF.getModel()).setElementAt(row + 1, row);
		}
	}

	//XXX
	public String saveDRF(String co_id, modelDRF_particulars model, String drf_no) {
		// FOR DRF HEADER
		String other_details, req_remarks, attachments = ""; 
		String user = UserInfo.EmployeeCode;
		String drf_type_id = lookupRequestType.getText().trim();
		String div_id = lookupDiv.getText().trim();
		String payee = lookupPayee.getText().trim();
		String availer = lookupAvailer.getText().trim();
		String payee_type_id = lookupPayeeType.getText().trim();
		String payment_type_id = lookupPaymentType.getText();
		String particulars = txtDRFParticulars.getText().trim().replace("'", "''").trim();
		
		if(!getTextIfNotPlaceholder(txtDRFOtherDetails, placeholder_other_details).isEmpty()) {
			 other_details = txtDRFOtherDetails.getText().trim().replace("'", "''").trim();
		} else {
			other_details = "";
		}
		
		if(!getTextIfNotPlaceholder(txtDRFReqRemarks, placeholder_req_remarks).isEmpty()) {
			req_remarks = txtDRFReqRemarks.getText().trim().replace("'", "''").trim();
		} else {
			req_remarks = "";
		}
		
		if(!getTextIfNotPlaceholder(txtDRFAttachments, placeholder_attachments).isEmpty()) {
			attachments = txtDRFAttachments.getText().trim().replace("'", "''").trim();
		} else {
			attachments = "";
		}

		// FOR DRF DETAIL
		ArrayList<String> listAcctIDs = new ArrayList<String>();
		ArrayList<String> listProjCostAcctIDs = new ArrayList<String>();
		ArrayList<String> listProjIDs = new ArrayList<String>();
		ArrayList<BigDecimal> listDRFAmount = new ArrayList<BigDecimal>(); 
		ArrayList<String> listPayee = new ArrayList<String>();
		ArrayList<String> listPayeeTypeID = new ArrayList<String>();
		ArrayList<String> listInvoiceNo = new ArrayList<String>();
		ArrayList<String> listInvoiceDate = new ArrayList<String>(); 
		ArrayList<String> listSoaBillNo = new ArrayList<String>();
		ArrayList<String> listSoaBillDate = new ArrayList<String>();
		ArrayList<String> listAssetNo = new ArrayList<String>();
		ArrayList<Boolean> listVatable = new ArrayList<Boolean>();
		ArrayList<BigDecimal> listGrossAmt = new ArrayList<BigDecimal>();
		ArrayList<BigDecimal> listNetAmt = new ArrayList<BigDecimal>();
		ArrayList<BigDecimal> listVatRate = new ArrayList<BigDecimal>();
		ArrayList<BigDecimal> listVatAmt = new ArrayList<BigDecimal>();
		ArrayList<String> listWTaxID = new ArrayList<String>();
		ArrayList<BigDecimal> listWTaxRate = new ArrayList<BigDecimal>();
		ArrayList<BigDecimal> listWTaxAmt = new ArrayList<BigDecimal>();
		ArrayList<BigDecimal> listExpAmt = new ArrayList<BigDecimal>();
		ArrayList<BigDecimal> listRetAmt = new ArrayList<BigDecimal>();
		ArrayList<BigDecimal> listDPRecoupAmt = new ArrayList<BigDecimal>();
		ArrayList<BigDecimal> listBCLiqAmt = new ArrayList<BigDecimal>();
		ArrayList<BigDecimal> listOtherLiqAmt = new ArrayList<BigDecimal>();
		ArrayList<BigDecimal> listPayableAmt = new ArrayList<BigDecimal>();
		ArrayList<String> listRecIds = new ArrayList<String>(); 

		for(int x = 0; x < model.getRowCount(); x++){

			String account_id = (String) model.getValueAt(x, 0);

			if (account_id != null && !account_id.isEmpty()) {

				String acct_id =  (String) model.getValueAt(x, 0);
				String proj_cost_id = (String) model.getValueAt(x, 2);
				String proj_id = (String) model.getValueAt(x, 5);
				BigDecimal amt = (BigDecimal) model.getValueAt(x, 6);
				String payeeID = (String) model.getValueAt(x, 8);
				String payeeType = (String) model.getValueAt(x, 9); 
				String invoiceNo = (String) model.getValueAt(x, 16); 
				//				String invoiceDate =  model.getValueAt(x, 16) != null ? model.getFormattedDate(x, 16) : null;
				String invoiceDate =  model.getValueAt(x, 17) != null ? (String) model.getValueAt(x, 17).toString() : "null";

				System.out.println("Value of invoice date: " + invoiceDate);
				System.out.println("Value type: " + invoiceDate.getClass().getName());  // Log the type

				String SOABillNo = (String) model.getValueAt(x, 18); 
				//				String SOABillDate = (String) model.getValueAt(x, 18).toString();
				//				String SOABillDate =  model.getValueAt(x, 18) != null ? model.getFormattedDate(x, 18) : null;
				String SOABillDate =  model.getValueAt(x, 19) != null ? (String) model.getValueAt(x, 19).toString() : "null";

				System.out.println("Value of SOA/Bill date: " + SOABillDate);

				String assetNo = (String) model.getValueAt(x, 20); 
				Boolean vatable = (Boolean) model.getValueAt(x, 22); 
				BigDecimal grossAmt = (BigDecimal) model.getValueAt(x, 24); 
				BigDecimal netAmt = (BigDecimal) model.getValueAt(x, 25);
				BigDecimal vatRate = (BigDecimal) model.getValueAt(x, 26);
				BigDecimal vatAmt = (BigDecimal) model.getValueAt(x, 27);
				String WTaxID = (String) model.getValueAt(x, 28); 
				BigDecimal WTaxRate = (BigDecimal) model.getValueAt(x, 29);
				BigDecimal WTaxAmt = (BigDecimal) model.getValueAt(x, 30);
				BigDecimal ExpAmt = (BigDecimal) model.getValueAt(x, 31);
				BigDecimal RetAmt = (BigDecimal) model.getValueAt(x, 32);
				BigDecimal DPRecoupAmt = (BigDecimal) model.getValueAt(x, 33);
				BigDecimal BCLiqAmt = (BigDecimal) model.getValueAt(x, 34);
				BigDecimal OtherLiqAmt = (BigDecimal) model.getValueAt(x, 35);
				BigDecimal PayableAmt = (BigDecimal) model.getValueAt(x, 36);
				String RecID = (String) model.getValueAt(x, 37) != null ? model.getValueAt(x, 37).toString() : "";

				listAcctIDs.add(String.format("'%s'", acct_id));
				listProjCostAcctIDs.add(String.format("'%s'", proj_cost_id));
				listProjIDs.add(String.format("'%s'", proj_id));
				listDRFAmount.add(amt);
				listPayee.add(String.format("'%s'", payeeID));
				listPayeeTypeID.add(String.format("'%s'", payeeType));
				listInvoiceNo.add(String.format("'%s'", invoiceNo.trim().replace("'", "''").trim()));
				listInvoiceDate.add(String.format("'%s'", invoiceDate.trim().replace("'", "''").trim()));
				listSoaBillNo.add(String.format("'%s'", SOABillNo.trim().replace("'", "''").trim()));
				listSoaBillDate.add(String.format("'%s'", SOABillDate.trim().replace("'", "''").trim()));
				listAssetNo.add(String.format("'%s'", assetNo));
				listVatable.add(vatable);
				listGrossAmt.add(grossAmt); 
				listNetAmt.add(netAmt); 
				listVatRate.add(vatRate); 
				listVatAmt.add(vatAmt); 
				listWTaxID.add(String.format("'%s'", WTaxID));
				listWTaxRate.add(WTaxRate); 
				listWTaxAmt.add(WTaxAmt); 
				listExpAmt.add(ExpAmt); 
				listRetAmt.add(RetAmt); 
				listDPRecoupAmt.add(DPRecoupAmt); 
				listBCLiqAmt.add(BCLiqAmt); 
				listOtherLiqAmt.add(OtherLiqAmt); 
				listPayableAmt.add(PayableAmt); 
				listRecIds.add(String.format("'%s'", RecID));
				
			}
		}

		String acct_id = listAcctIDs.toString().replaceAll("\\[|\\]", "");
		String proj_cost_id = listProjCostAcctIDs.toString().replaceAll("\\[|\\]", "");
		String proj_id = listProjIDs.toString().replaceAll("\\[|\\]", "");
		String amt = listDRFAmount.toString().replaceAll("\\[|\\]", "");
		String payeeID = listPayee.toString().replaceAll("\\[|\\]", "");
		String payeeType = listPayeeTypeID.toString().replaceAll("\\[|\\]", "");
		String invoiceNo = listInvoiceNo.toString().replaceAll("\\[|\\]", "");
		String invoiceDate = listInvoiceDate.toString().replaceAll("\\[|\\]", "");
		String SOABillNo = listSoaBillNo.toString().replaceAll("\\[|\\]", "");
		String SOABillDate = listSoaBillDate.toString().replaceAll("\\[|\\]", "");
		String assetNo = listAssetNo.toString().replaceAll("\\[|\\]", "");
		String vatable = listVatable.toString().replaceAll("\\[|\\]", "");
		String grossAmt = listGrossAmt.toString().replaceAll("\\[|\\]", "");
		String netAmt = listNetAmt.toString().replaceAll("\\[|\\]", "");
		String vatRate = listVatRate.toString().replaceAll("\\[|\\]", "");
		String vatAmt = listVatAmt.toString().replaceAll("\\[|\\]", "");
		String WTaxID = listWTaxID.toString().replaceAll("\\[|\\]", "");
		String WTaxRate = listWTaxRate.toString().replaceAll("\\[|\\]", "");
		String WTaxAmt = listWTaxAmt.toString().replaceAll("\\[|\\]", "");
		String ExpAmt = listExpAmt.toString().replaceAll("\\[|\\]", "");
		String RetAmt = listRetAmt.toString().replaceAll("\\[|\\]", "");
		String DPRecoupAmt = listDPRecoupAmt.toString().replaceAll("\\[|\\]", "");
		String BCLiqAmt = listBCLiqAmt.toString().replaceAll("\\[|\\]", "");
		String OtherLiqAmt = listOtherLiqAmt.toString().replaceAll("\\[|\\]", "");
		String PayableAmt = listPayableAmt.toString().replaceAll("\\[|\\]", "");
		String RecId = listRecIds.toString().replace("\\[|\\]", "");

		BigDecimal totalDRFAmt = (BigDecimal) modelDRF_part_total.getValueAt(0, 6);

		System.out.println("");
		System.out.println("DRF Amt: "+ amt);
		System.out.println("DRF_No: "+ drf_no);
		System.out.println("Total DRF Amt: "+ totalDRFAmt);
		System.out.println("Invoide Date b4 saving:" +invoiceDate);

		try {

			String SQL = "SELECT fn_save_drf('"+drf_no+"','"+co_id+"', '"+div_id+"', '"+dateFormat.format(dteDRFDate.getDate())+"', '"+dateFormat.format(dteDueDate.getDate())+"', '"+drf_type_id+"', '"+payee+"', '"+payee_type_id+"' \n"
					+ ", '"+availer+"', '"+payment_type_id+"', '"+particulars+"', '"+other_details+"', '"+req_remarks+"', '"+attachments+"', '"+user+"', ARRAY["+acct_id+"]::VARCHAR[], ARRAY["+proj_cost_id+"]::VARCHAR[] \n"
					+ ", ARRAY["+proj_id+"]::VARCHAR[], ARRAY["+amt+"]::NUMERIC[], ARRAY["+payeeID+"]::VARCHAR[], ARRAY["+payeeType+"]::VARCHAR[], ARRAY["+invoiceNo+"]::VARCHAR[], ARRAY["+invoiceDate+"]::VARCHAR[], ARRAY["+SOABillNo+"]::VARCHAR[], ARRAY["+SOABillDate+"]::VARCHAR[] \n"
					+ ", ARRAY["+assetNo+"]::VARCHAR[], ARRAY["+vatable+"]::BOOLEAN[], ARRAY["+grossAmt+"]::NUMERIC[], ARRAY["+netAmt+"]::NUMERIC[], ARRAY["+vatRate+"]::NUMERIC[], ARRAY["+vatAmt+"]::NUMERIC[], ARRAY["+WTaxID+"]::VARCHAR[], ARRAY["+WTaxRate+"]::NUMERIC[], ARRAY["+WTaxAmt+"]::NUMERIC[], ARRAY["+ExpAmt+"]::NUMERIC[]\n"
					+ ", ARRAY["+RetAmt+"]::NUMERIC[], ARRAY["+DPRecoupAmt+"]::NUMERIC[], ARRAY["+BCLiqAmt+"]::NUMERIC[], ARRAY["+OtherLiqAmt+"]::NUMERIC[], ARRAY["+PayableAmt+"]::NUMERIC[], ARRAY["+RecId+"]::VARCHAR[], "+totalDRFAmt+")"; 

			pgSelect db = new pgSelect(); 
			db.select(SQL);

			System.out.println(SQL);

			if(db.isNotNull()) {
				drf_no =  (String) db.getResult()[0][0];	
			}		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Something went wrong.", "Save", JOptionPane.INFORMATION_MESSAGE);
		}

		FncSystem.out("DRF No: ", drf_no);
		return drf_no;
	}

	private void insertAudit_trail(String activity, String remarks, pgUpdate db) {

		String user_code = UserInfo.EmployeeCode;

		String sqlDetail = "INSERT INTO mf_audit_trail(\n"
				+ "	              system_id, activity, user_code, date_upd, entity_id, \n"
				+ "	              client_seqno, projcode, pbl_id, doc_id, doc_no, remarks)\n"
				+ "	      VALUES ('RP', '" + activity + "', '" + user_code + "', NOW(), NULL, \n"
				+ "	              NULL, NULL, NULL, NULL, NULL, '" + remarks + "');";

		System.out.printf("SQL #DETAIL: %s", sqlDetail);
		db.executeUpdate(sqlDetail, false);

	}

	// set values of variables
	private void setColumnIfVatableEntity() {

		int rw = tblDRF_part.getRowCount();
		int x = 0;

		while (x < rw) {

			modelDRF_part.setValueAt(is_payee_vatable, x, 22);
			if (is_payee_vatable == true) {
				modelDRF_part.setValueAt(new BigDecimal(12.0), x, 26);
			} else {
				modelDRF_part.setValueAt(new BigDecimal(0.00), x, 26);
			}

			x++;

		}
	}

	private void setColumnNonTaxable() {

		int rw = tblDRF_part.getRowCount();
		int x = 0;

		while (x < rw) {

			modelDRF_part.setValueAt(new BigDecimal(0.00), x, 29);
			x++;

		}
	}

	private void setColumnTaxable() {

		int rw = tblDRF_part.getRowCount();
		int x = 0;

		while (x < rw) {

			modelDRF_part.setValueAt(new BigDecimal(tax_rate), x, 29);

			x++;

		}
	}

	private void setPayee() {

		String request_type = lookupRequestType.getText().trim();
		String payee_id = lookupPayee.getText().trim();
		String payee_type = lookupPayeeType.getText().trim();
		String payee_name = tagPayee.getText().trim().replace("[", "").replace("]", "");
		String div = lookupDiv.getText().trim(); 

		if (request_type.equals("")) {
		} else {
			int rw = tblDRF_part.getRowCount();
			int x = 0;

			while (x < rw) {
				modelDRF_part.setValueAt(div, x, 4);
				modelDRF_part.setValueAt(payee_id, x, 8);
				modelDRF_part.setValueAt(payee_type, x, 9);
				modelDRF_part.setValueAt(payee_name, x, 10);
				modelDRF_part.setValueAt(tax_id, x, 28);
				modelDRF_part.setValueAt(tax_rate, x, 29);

				x++;
			}

			tblDRF_part.getColumnModel().getColumn(10).setPreferredWidth(80);
		}

	}

	public void setupPV() {

		if (Home.Home_ArcSystem.isNotExisting("PayableVoucher")) {
			PayableVoucher pv = new PayableVoucher();
			Home.Home_ArcSystem.addWindow(pv);

			if (FncAcounting.EmpCanAddNew(UserInfo.EmployeeCode, "2") == true) {

				if (isPVcreated() == true) {
					JOptionPane.showMessageDialog(getContentPane(), "PV is already created for this request.",
							"Information", JOptionPane.INFORMATION_MESSAGE);
					refresh();
				}

				else if (isPVcreated_but_inactive() == true) {

					if (JOptionPane.showConfirmDialog(getContentPane(),
							"An inactive PV already exists. " + "\n" + "Would you like to overwrite details?",
							"Confirmation", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

						PayableVoucher.refresh_fields();

						PayableVoucher.co_id = co_id;
						PayableVoucher.company = company;
						PayableVoucher.payee1 = lookupPayee.getText();
						PayableVoucher.availer_id = lookupAvailer.getText();
						PayableVoucher.lookupCompany.setValue(co_id);
						PayableVoucher.tagCompany.setTag(company);
						PayableVoucher.company_logo = company_logo;
						PayableVoucher.btnAddNew.setEnabled(true);
						PayableVoucher.btnCancel.setEnabled(true);

						PayableVoucher.rplf_no = lookupDRF_no.getText().trim();
						drf_no = lookupDRF_no.getText().trim();
						PayableVoucher.lookupPV_no.setText(drf_no);

						PayableVoucher.lblDateTrans.setEnabled(true);
						PayableVoucher.lookupCompany.setEnabled(false);
						PayableVoucher.lblDateNeeded.setEnabled(true);
						PayableVoucher.dteRequest.setEnabled(true);
						PayableVoucher.dteNeeded.setEnabled(true);
						PayableVoucher.lblPaymentType.setEnabled(true);
						PayableVoucher.lookupPaymentType.setEnabled(true);
						PayableVoucher.tagReqType.setEnabled(true);
						PayableVoucher.rplf_type_id = PayableVoucher.getRPLF_typeID(drf_no);

						PayableVoucher.setDRF_header();
						PayableVoucher.displayDRF_toPVdetails(PayableVoucher.modelPV_account,
								PayableVoucher.rowHeaderPV_account, PayableVoucher.modelPV_accounttotal);
						PayableVoucher.displayPV_subsidiaryledgers(PayableVoucher.modelPV_SL,
								PayableVoucher.rowHeaderPV_SL, PayableVoucher.modelPV_SL_total, drf_no);

						PayableVoucher.btnSave.setEnabled(true);
						PayableVoucher.btnAddNew.setEnabled(false);

						PayableVoucher.lookupPaymentType.setValue(lookupPaymentType.getText());

						if (lookupPaymentType.getText().equals("B")) {
							pay_type_name = "Check";
						} else {
							pay_type_name = "Cash";
						}
						PayableVoucher.tagReqType.setTag(pay_type_name);

						PayableVoucher.lookupPaymentType.setLookupSQL(PayableVoucher.getPayment_type());
						PayableVoucher.lookupDRF_no.setText(drf_no);

						PayableVoucher.lookupPV_no.setLookupSQL(PayableVoucher.getPV_no());
						PayableVoucher.lookupDRF_no.setLookupSQL(PayableVoucher.getDRF_no());
						PayableVoucher.to_do = "set-up";

						if (FncAcounting.EmpCanAddNew(UserInfo.EmployeeCode, "15") == true) {
							PayableVoucher.dteRequest.setEnabled(true);
							PayableVoucher.lblDateTrans.setEnabled(true);
						} else {
							PayableVoucher.dteRequest.setEnabled(false);
							PayableVoucher.lblDateTrans.setEnabled(false);
						}
					}
				}

				else {

					System.out.println("Else Setup PV");

					PayableVoucher.refresh_fields();
					PayableVoucher.co_id = co_id;
					PayableVoucher.company = company;
					PayableVoucher.payee1 = lookupPayee.getText();
					PayableVoucher.availer_id = lookupAvailer.getText();
					PayableVoucher.lookupCompany.setValue(co_id);
					PayableVoucher.tagCompany.setTag(company);
					PayableVoucher.company_logo = company_logo;
					PayableVoucher.btnAddNew.setEnabled(true);
					PayableVoucher.btnCancel.setEnabled(true);

					PayableVoucher.rplf_no = lookupDRF_no.getText().trim();
					drf_no = lookupDRF_no.getText().trim();
					PayableVoucher.lookupPV_no.setText(drf_no);

					PayableVoucher.lblDateTrans.setEnabled(true);
					PayableVoucher.lookupCompany.setEnabled(false);
					PayableVoucher.lblDateNeeded.setEnabled(true);
					PayableVoucher.dteRequest.setEnabled(true);
					PayableVoucher.dteNeeded.setEnabled(true);
					PayableVoucher.lblPaymentType.setEnabled(true);
					PayableVoucher.lookupPaymentType.setEnabled(true);
					PayableVoucher.tagReqType.setEnabled(true);
					PayableVoucher.rplf_type_id = PayableVoucher.getRPLF_typeID(drf_no);

					PayableVoucher.setDRF_header();
					PayableVoucher.displayDRF_toPVdetails(PayableVoucher.modelPV_account,
							PayableVoucher.rowHeaderPV_account, PayableVoucher.modelPV_accounttotal);
					PayableVoucher.displayPV_subsidiaryledgers(PayableVoucher.modelPV_SL, PayableVoucher.rowHeaderPV_SL,
							PayableVoucher.modelPV_SL_total, drf_no);

					PayableVoucher.btnSave.setEnabled(true);
					PayableVoucher.btnAddNew.setEnabled(false);

					PayableVoucher.lookupPaymentType.setValue(lookupPaymentType.getText());

					if (lookupPaymentType.getText().equals("B")) {
						pay_type_name = "Check";
					} else {
						pay_type_name = "Cash";
					}
					PayableVoucher.tagReqType.setTag(pay_type_name);

					PayableVoucher.lookupPaymentType.setLookupSQL(PayableVoucher.getPayment_type());
					PayableVoucher.lookupDRF_no.setText(drf_no);

					PayableVoucher.lookupPV_no.setLookupSQL(PayableVoucher.getPV_no());
					PayableVoucher.lookupDRF_no.setLookupSQL(PayableVoucher.getDRF_no());
					PayableVoucher.to_do = "addnew";

					if (FncAcounting.EmpCanAddNew(UserInfo.EmployeeCode, "15") == true) {
						PayableVoucher.dteRequest.setEnabled(true);
						PayableVoucher.lblDateTrans.setEnabled(true);
					} else {
						PayableVoucher.dteRequest.setEnabled(false);
						PayableVoucher.lblDateTrans.setEnabled(false);
					}

					PayableVoucher.getTransMonthYear();
					PayableVoucher.checkRequestDate();

				}
				this.dispose();
			}

			else if (FncAcounting.EmpCanAddNew(UserInfo.EmployeeCode, "2") == false) {
				JOptionPane.showMessageDialog(getContentPane(), "Sorry, you are not authorized to setup a new PV.",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(getContentPane(), "Payable Voucher is already open.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void openPV() {

		if (Home.Home_ArcSystem.isNotExisting("PayableVoucher")) {
			PayableVoucher pv = new PayableVoucher();
			Home.Home_ArcSystem.addWindow(pv);
		}

		if (sql_getPV(lookupDRF_no.getText().trim()).equals("")
				|| sql_getPV(lookupDRF_no.getText().trim()).equals(null)) {
			{
				showWarningMessage("This request has no Payable Voucher yet.", "Warning");
			}

			PayableVoucher.cancel();
			PayableVoucher.refresh_fields();

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
			PayableVoucher.insertAudit_trail2("Open-PV from RPLF", PayableVoucher.pv_no);

			if (!lookupPV_no.getText().equals("")) {
				String pv_no = lookupPV_no.getText();
				PayableVoucher.refresh_fields();

				PayableVoucher.pv_no = pv_no;
				PayableVoucher.lookupPV_no.setValue(pv_no);

				PayableVoucher.setPV_header(pv_no);
				PayableVoucher.displayPV_details(PayableVoucher.modelPV_account, PayableVoucher.rowHeaderPV_account,
						PayableVoucher.modelPV_accounttotal, pv_no);
				PayableVoucher.displayPV_subsidiaryledgers(PayableVoucher.modelPV_SL, PayableVoucher.rowHeaderPV_SL,
						PayableVoucher.modelPV_SL_total, pv_no);
				PayableVoucher.btnAddNew.setEnabled(false);
				PayableVoucher.btnRefresh.setEnabled(true);

				// set particulars
				Integer w = 1;
				Integer line_count = PayableVoucher.sql_getDRF_line_count(pv_no);
				String part_desc;
				String particulars = "";

				if (PayableVoucher.getPV_status(pv_no).equals("P") && PayableVoucher.lookupPV_no.isEnabled()) {
					PayableVoucher.btnEdit.setEnabled(false);
					PayableVoucher.btnSave.setText("Save");
					PayableVoucher.btnSave.setEnabled(false);
					PayableVoucher.mniInactivate.setEnabled(false);
				} else if (PayableVoucher.getPV_status(pv_no).equals("F")) {
					PayableVoucher.btnEdit.setEnabled(false);
					PayableVoucher.btnSave.setText("Post");
					PayableVoucher.btnSave.setEnabled(true);
					PayableVoucher.mniInactivate.setEnabled(false);
				} else if (PayableVoucher.getPV_status(pv_no).equals("A")) {
					PayableVoucher.btnEdit.setEnabled(true);
					PayableVoucher.btnSave.setText("Post");
					PayableVoucher.btnSave.setEnabled(false);
					PayableVoucher.mniInactivate.setEnabled(true);
				}

				PayableVoucher.modelPV_account.setEditable(false);
				PayableVoucher.tblPV_account.setEditable(false);
				PayableVoucher.btnPreview.setEnabled(true);
			}
		}
	}

	public void openCV() {

		if (Home.Home_ArcSystem.isNotExisting("CheckVoucher")) {
			CheckVoucher chk_vchr = new CheckVoucher();
			Home.Home_ArcSystem.addWindow(chk_vchr);
		}

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
			CheckVoucher.payee = "";

			String pv_no = lookupPV_no.getText();

			if (!sql_getCVno(pv_no, co_id).equals("")) {
				String cv_no = sql_getCVno(pv_no, co_id);
				CheckVoucher.cv_no = cv_no;
				CheckVoucher.refresh_fields();
				CheckVoucher.refresh_tablesMain();
				CheckVoucher.setDV_header(cv_no);
				CheckVoucher.lookupDV_no.setValue(cv_no);

				String status = sql_getCVstatus(cv_no, co_id);
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

	public static void open2307() {

		if (Home.Home_ArcSystem.isNotExisting("Form2307_Monitoring")) {
			Form2307_Monitoring cwt = new Form2307_Monitoring();
			Home.Home_ArcSystem.addWindow(cwt);
		}

		if (co_id.equals("")) {

		} else {

			if (!lookupDRF_no.getText().equals("")) {

				Form2307_Monitoring.co_id = co_id;
				Form2307_Monitoring.lookupCompany.setValue(co_id);
				Form2307_Monitoring.tagCompany.setTag(company);
				Form2307_Monitoring.company_logo = company_logo;
				Form2307_Monitoring.lookupCompany.setValue(co_id);
				Form2307_Monitoring.tagCompany.setTag(company);

				// set enable/disable fields
				Form2307_Monitoring.rbtnAllPayee.setEnabled(true);
				Form2307_Monitoring.rbtnAllPayee.setSelected(false);
				Form2307_Monitoring.lblPayeeType.setEnabled(true);
				Form2307_Monitoring.lblPayee.setEnabled(true);
				Form2307_Monitoring.lblPV_no.setEnabled(true);
				Form2307_Monitoring.lblRequestType.setEnabled(true);
				Form2307_Monitoring.lookupPayeeType.setEnabled(true);
				Form2307_Monitoring.lookupPayee.setEnabled(true);
				Form2307_Monitoring.lookupPV_no.setEnabled(true);
				Form2307_Monitoring.cmbPmtType.setEnabled(true);
				Form2307_Monitoring.tagPayeeType.setEnabled(true);
				Form2307_Monitoring.tagPayee.setEnabled(true);
				Form2307_Monitoring.tagPV_no.setEnabled(true);
				Form2307_Monitoring.lblYear.setEnabled(true);
				Form2307_Monitoring.lblPeriod.setEnabled(true);
				Form2307_Monitoring.lblMonth.setEnabled(true);
				Form2307_Monitoring.cmbYear.setEnabled(true);
				Form2307_Monitoring.cmbPeriod.setEnabled(true);
				Form2307_Monitoring.cmbMonth.setEnabled(false);
				Form2307_Monitoring.btnGenerate.setEnabled(true);
				Form2307_Monitoring.btnCancel.setEnabled(true);
				Form2307_Monitoring.setButtonsStatus(true, true, false, true);

				// set PV No.
				Form2307_Monitoring.lookupPV_no.setText(lookupDRF_no.getText());
				Form2307_Monitoring.PV_num = lookupDRF_no.getText();

				Form2307_Monitoring.displayEWT_for2307Sending(Form2307_Monitoring.modelFor_sending,
						Form2307_Monitoring.rowHeaderFor_sending, Form2307_Monitoring.modelFor_sending_total, "", "",
						"", "", "", "");
				Form2307_Monitoring.displayEWT_for2307Received(Form2307_Monitoring.modelReceived,
						Form2307_Monitoring.rowHeaderReceived, Form2307_Monitoring.modelReceived_total, "", "", "", "",
						"", "");

				Form2307_Monitoring.month_from = "01";
				Form2307_Monitoring.month_to = "12";
				Form2307_Monitoring.day_from = "01";
				Form2307_Monitoring.day_to = "31";

				Form2307_Monitoring.lookupPayeeType.setLookupSQL(Form2307_Monitoring.getAvailerType());
				Form2307_Monitoring.lookupPayee.setLookupSQL(Form2307_Monitoring.getEntityList());
				Form2307_Monitoring.lookupPV_no.setLookupSQL(Form2307_Monitoring.getPV_no());

			}
		}

	}

	public static void openDRFprooflist() {

		if (Home.Home_ArcSystem.isNotExisting("DRFprooflist")) {
			DRFprooflist drf_proof = new DRFprooflist();
			Home.Home_ArcSystem.addWindow(drf_proof);
		}

		if (co_id.equals("")) {

		} else {
			DRFprooflist.lookupCompany.setValue(co_id);
			DRFprooflist.txtCompany.setText(company);
			DRFprooflist.enabledFields(true);
			DRFprooflist.company_logo = company_logo;
		}

	}

	public static void copy() {

		int column = tblDRF_part.getSelectedColumn();
		int row = tblDRF_part.getSelectedRow();

		item = modelDRF_part.getValueAt(row, column).toString().trim();
		//		mnipaste.setEnabled(true);
	}

	public void paste() {

		int column = tblDRF_part.getSelectedColumn();
		int row = tblDRF_part.getSelectedRow();

		if (column == 1) {
			if (isItemAnAcct(item) == true) {
				modelDRF_part.setValueAt(item, row, column);
				tblDRF_part.packAll();
			} else {
				JOptionPane.showMessageDialog(getContentPane(), "Pasted item is not a valid Account ID.", "Information",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		else if (column == 2) {
			if (isItemOffice(item) == true) {
				modelDRF_part.setValueAt(item, row, column);
				tblDRF_part.packAll();
			} else {
				JOptionPane.showMessageDialog(getContentPane(), "Pasted item is not a valid Division ID.",
						"Information", JOptionPane.ERROR_MESSAGE);
			}
		}

		else if (column == 3) {
			if (DisbursementRequestForm.isItemDivision(item) == true) {
				modelDRF_part.setValueAt(item, row, column);
				tblDRF_part.packAll();
			} else {
				JOptionPane.showMessageDialog(getContentPane(), "Pasted item is not a valid Department ID.",
						"Information", JOptionPane.ERROR_MESSAGE);
			}
		}

		else if (column == 5) {
			if (DisbursementRequestForm.isItemProject(item) == true) {
				modelDRF_part.setValueAt(item, row, column);
				tblDRF_part.packAll();
			} else {
				JOptionPane.showMessageDialog(getContentPane(), "Pasted item is not a valid Project ID.", "Information",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		else if (column == 6) {
			if (DisbursementRequestForm.isItemSubProject(item) == true) {
				modelDRF_part.setValueAt(item, row, column);
				tblDRF_part.packAll();
			} else {
				JOptionPane.showMessageDialog(getContentPane(), "Pasted item is not a valid Sub-project ID.",
						"Information", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public void confirmWriteOff() {

		if (txtStatus.getText().trim().equals("INACTIVE")) {
			showWarningMessage("This request has been inactive. Write-off is not applicable.", "Warning");
		}

		else if (txtStatus.getText().trim().equals("DELETED")) {
			showWarningMessage("This request has been deleted. Write-off is not applicable.", "Warning");
		}

		else {

			if (isRPLFalreadywroteoff() == true) {
				showWarningMessage("This request has been written off. Re-writeoff is not applicable.", "Warning");
			} else {

				if (isCApaidout() == false) {
					showWarningMessage("This request is not yet paid out. Write-off is not applicable.", "Warning");

				} else {
					if (isCAfullyliquidated() == true) {
						showWarningMessage("This request is already liquidated. Write-off is not applicable.", "Warning");

					} else {
						if (JOptionPane.showConfirmDialog(getContentPane(),
								"Are you sure you want to writeoff this CA?", "Confirmation",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

							pgUpdate db = new pgUpdate();
							insertCAwriteoff_detail(drf_no, db);
							db.commit();
							JOptionPane.showMessageDialog(getContentPane(), "Cash Advance payment write-off saved.",
									"Information", JOptionPane.INFORMATION_MESSAGE);
							openJV();
						}
					}
				}
			}
		}
	}

	public void openJV() {

		if (Home.Home_ArcSystem.isNotExisting("JournalVoucher")) {
			JournalVoucher jv = new JournalVoucher();
			Home.Home_ArcSystem.addWindow(jv);
		}

		if (co_id.equals("")) {

		} else {

			JournalVoucher.co_id = co_id;
			JournalVoucher.company = company;
			JournalVoucher.lookupCompany.setValue(co_id);
			JournalVoucher.tagCompany.setTag(company);
			JournalVoucher.company_logo = company_logo;

			JournalVoucher.lblJV_no.setEnabled(true);
			JournalVoucher.lookupJV.setEnabled(true);

			JournalVoucher.add();

			int rw = tblDRF_part.getModel().getRowCount();
			int x = 0;
			int y = 0;

			while (x < rw) {

				String payee_name = "";
				String acct_id_exp = "";
				String acct_id_ca = "";
				String acct_name_ca = "";
				String proj_id = "";
				String subproj_id = "";
				String div_id = "";
				String dept_id = "";
				Double exp_amount = 0.00;
				Double liquidated_amount = 0.00;
				Double writeoff_amount = 0.00;

				try {
					payee_name = modelDRF_part.getValueAt(x, 9).toString().trim();
				} catch (NullPointerException e) {
					payee_name = "";
				}
				try {
					proj_id = modelDRF_part.getValueAt(x, 3).toString().trim();
				} catch (NullPointerException e) {
					proj_id = "";
				}
				//				try {
				//					subproj_id = modelDRF_part.getValueAt(x, 6).toString().trim();
				//				} catch (NullPointerException e) {
				//					subproj_id = "";
				//				}
				try {
					div_id = modelDRF_part.getValueAt(x, 2).toString().trim();
				} catch (NullPointerException e) {
					div_id = "";
				}
				//				try {
				//					dept_id = modelDRF_part.getValueAt(x, 3).toString().trim();
				//				} catch (NullPointerException e) {
				//					dept_id = "";
				//				}
				try {
					acct_id_exp = modelDRF_part.getValueAt(x, 0).toString().trim();
				} catch (NullPointerException e) {
					acct_id_exp = "";
				}

				try {
					exp_amount = Double.parseDouble(modelDRF_part.getValueAt(x, 30).toString());
				} catch (NullPointerException e) {
					exp_amount = 0.00;
				}

				liquidated_amount = Double.parseDouble(sql_getLiquidatedAmt(x + 1, drf_no));
				writeoff_amount = exp_amount - liquidated_amount;

				if (lookupRequestType.getText().equals("02") || lookupRequestType.getText().equals("02")) {
					acct_id_ca = "01-02-04-000";
					acct_name_ca = "Advances to Officers and Employees";
				} else if (lookupRequestType.getText().equals("05")) {
					acct_id_ca = "01-02-09-001";
					acct_name_ca = "Advances to Brokers";
				} else if (lookupRequestType.getText().equals("12")) {
					acct_id_ca = "01-02-07-001";
					acct_name_ca = "Advances to Contractors - Downpayment";
				} else if (lookupRequestType.getText().equals("13")) {
					acct_id_ca = "01-02-05-003";
					acct_name_ca = "Advances to OTB";
				}

				// debit
				JournalVoucher.modelJV_account.setValueAt(acct_id_exp, y, 0);
				JournalVoucher.modelJV_account.setValueAt(sql_Acctname(acct_id_exp), y, 7);
				JournalVoucher.modelJV_account.setValueAt(new BigDecimal(writeoff_amount), y, 8);
				JournalVoucher.modelJV_account.setValueAt(div_id, y, 1);
				JournalVoucher.modelJV_account.setValueAt(dept_id, y, 2);
				JournalVoucher.modelJV_account.setValueAt(proj_id, y, 4);
				JournalVoucher.modelJV_account.setValueAt(subproj_id, y, 5);

				// credit
				JournalVoucher.modelJV_account.setValueAt(acct_id_ca, y + 1, 0);
				JournalVoucher.modelJV_account.setValueAt(acct_name_ca, y + 1, 7);
				JournalVoucher.modelJV_account.setValueAt(new BigDecimal(writeoff_amount), y + 1, 9);
				JournalVoucher.modelJV_account.setValueAt(div_id, y + 1, 1);
				JournalVoucher.modelJV_account.setValueAt(dept_id, y + 1, 2);
				JournalVoucher.modelJV_account.setValueAt(proj_id, y + 1, 4);
				JournalVoucher.modelJV_account.setValueAt(subproj_id, y + 1, 5);

				JournalVoucher.totalJV(JournalVoucher.modelJV_account, JournalVoucher.modelJV_accounttotal);
				JournalVoucher.setTableWidth();

				JournalVoucher.lookupTranType.setText("00007");
				JournalVoucher.tagTranType.setTag("ADJUSTMENTS");
				JournalVoucher.txtJV_Remark.setText("Cash Advance (" + acct_name_ca + ") write-off of " + payee_name
						+ ", with RPLF No. " + drf_no + ".");

				JournalVoucher.writeoff_jv = true;
				JournalVoucher.drf_no = drf_no;

				x++;
				y = y + 2;
			}
		}
	}

	public void insertCAwriteoff_detail(String drf_no, pgUpdate db) {

		int rw = tblDRF_part.getModel().getRowCount();
		int next_writeoff_no = sql_getNextWriteoffno();
		int x = 0;
		int y = 1;

		while (x < rw) {

			Double amount = Double.parseDouble(modelDRF_part.getValueAt(x, 8).toString());

			if (amount == 0) {
			} else {
				String acct_id = "";
				String entity_id = "";
				String dept_id = "";

				try {
					acct_id = modelDRF_part.getValueAt(x, 1).toString().trim();
				} catch (NullPointerException e) {
					acct_id = "";
				}
				try {
					entity_id = modelDRF_part.getValueAt(x, 10).toString().trim();
				} catch (NullPointerException e) {
					entity_id = "";
				}
				try {
					dept_id = modelDRF_part.getValueAt(x, 3).toString().trim();
				} catch (NullPointerException e) {
					dept_id = "";
				}

				Double exp_amount = 0.00;
				Double liquidated_amount = 0.00;
				Double writeoff_amount = 0.00;

				try {
					exp_amount = Double.parseDouble(modelDRF_part.getValueAt(x, 28).toString());
				} catch (NullPointerException e) {
					exp_amount = 0.00;
				}
				try {
					liquidated_amount = Double.parseDouble(sql_getLiquidatedAmt(x + 1, drf_no));
				} catch (NullPointerException e) {
					liquidated_amount = 0.00;
				}
				writeoff_amount = exp_amount - liquidated_amount;

				String sqlDetail = "INSERT into rf_ca_writeoff values (" + "" + next_writeoff_no + ",  \n" + // 1
						"now(),  \n" + // 2
						"'" + co_id + "',  \n" + // 3
						"'" + drf_no + "',  \n" + // 4
						"'',  \n" + // 5
						"" + y + ",  \n" + // 6
						"'" + entity_id + "',  \n" + // 7
						"'" + dept_id + "' , \n" + // 8
						"" + exp_amount + ", \n" + // 9
						"" + liquidated_amount + ", \n" + // 10
						"" + writeoff_amount + ", \n" + // 11
						"'" + acct_id + "',  \n" + // 12
						"false,  \n" + // 13
						"'" + UserInfo.EmployeeCode + "',  \n" + // 14
						"now(),  \n" + // 15
						"'' , \n" + // 16
						"null,  \n" + // 17
						"'A'   \n" + // 18
						")   ";

				System.out.printf("SQL-insertCAwriteoff_detail: %s", sqlDetail);

				db.executeUpdate(sqlDetail, false);

				y++;
			}

			x++;
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		if (arg0.getKeyCode() == KeyEvent.VK_F2) {
			clickTableColumn();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	// XXX TODO NEED TO CHECK AND UPDATE
	//	private void generateDetail(String lineno) {
	//		Object[] listCode = new Object[5];
	//		int row = tblDRF_part.convertRowIndexToModel(tblDRF_part.getSelectedRow());
	//
	//		listCode[0] = modelDRF_part.getValueAt(row, 2).equals(null) ? null : modelDRF_part.getValueAt(row, 35);
	//		listCode[1] = modelDRF_part.getValueAt(row, 3).equals(null) ? null : modelDRF_part.getValueAt(row, 36);
	//		listCode[2] = modelDRF_part.getValueAt(row, 5).equals(null) ? null : modelDRF_part.getValueAt(row, 37);
	//		listCode[3] = modelDRF_part.getValueAt(row, 6).equals(null) ? null : modelDRF_part.getValueAt(row, 38);
	//		listCode[4] = modelDRF_part.getValueAt(row, 10).equals(null) ? null : modelDRF_part.getValueAt(row, 39);
	//
	//		Object[] newListCode = new Object[5];
	//		newListCode[0] = listCode[0];
	//		newListCode[1] = listCode[1];
	//		newListCode[2] = listCode[2];
	//		newListCode[3] = listCode[3];
	//		newListCode[4] = listCode[4];
	//
	//		tagDetail.setText(String.format("[ Div: %s - Dep: %s - Proj: %s - Sub: %s - Availer: %s ]", newListCode));
	//	}

	private static String[] getDiv(String entity_id) {
		String [] divdetails = new String[2]; 
		pgSelect db = new pgSelect(); 
		String SQL = "SELECT a.div_code as \"DIV CODE\""
				+ ", b.div_name as \"DIVISION\" \n"
				+ "FROM rf_employee a\n"
				+ "INNER JOIN mf_division b ON b.div_code = a.div_code\n"
				+ "where entity_id = '"+entity_id+"'";
		db.select(SQL);
		FncSystem.out("Get Division:", SQL);

		if (db.isNotNull()) {
			divdetails [0] = (String) db.getResult()[0][0];
			divdetails [1] = (String) db.getResult()[0][1];
		} else {
			divdetails = null; 
		}
		return divdetails; 
	}

	private void btnState(Boolean bNew, Boolean bEdit, Boolean bSave, Boolean bPrev, Boolean bRef, Boolean bCancel) {
		btnAddNew.setEnabled(bNew);
		btnSave.setEnabled(bSave);
		btnPreview.setEnabled(bPrev);
		btnSubmitRet.setEnabled(bRef);
		btnCancel.setEnabled(bCancel);	
	}

	private static Date getDueDate(){
		pgSelect db = new pgSelect();
		db.select("SELECT CURRENT_DATE + INTERVAL '3 days'; ");
		return (Date) db.getResult()[0][0];
	}
	
	private static Date getMinDRFDate(){
		Integer validity = 5; 
		
		pgSelect db = new pgSelect();
		db.select("Select fn_get_last_valid_working_day("+validity+"); ");
		return (Date) db.getResult()[0][0];
	}

	public ArrayList<String> formatDateList(ArrayList<Date> dateList) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		ArrayList<String> formattedList = new ArrayList<>();

		for (Date date : dateList) {
			formattedList.add(sdf.format(date));
		}

		return formattedList;
	}

	private static Boolean isSameUser(String co_id, String drf_no, String created_by){

		boolean sameUser = false; 

		pgSelect db = new pgSelect();
		String sql = "Select * from rf_drf_header where co_id = '"+co_id+"' and drf_no = '"+drf_no+"' and created_by = '"+created_by+"' and status_id = 'A';"; 
		db.select(sql); 

		System.out.println("SQL-isSameUser: "+ sql);

		if (db.isNotNull()) {
			sameUser = true; 
		} else {
			sameUser = false; 
		}

		System.out.println("Is same user? :" + sameUser);
		return sameUser; 
	}

	private void checkCostCenter_manual(KeyEvent evt) {

		int row = 0;
		int column = 0;

		if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
			column = tblDRF_part.convertColumnIndexToModel(tblDRF_part.getSelectedColumn()) - 1;
			row = tblDRF_part.convertRowIndexToModel(tblDRF_part.getSelectedRow());
		} else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
			column = tblDRF_part.convertColumnIndexToModel(tblDRF_part.getSelectedColumn()) + 1;
			row = tblDRF_part.convertRowIndexToModel(tblDRF_part.getSelectedRow());
		} else if (evt.getKeyCode() == KeyEvent.VK_UP) {
			column = tblDRF_part.convertColumnIndexToModel(tblDRF_part.getSelectedColumn());
			row = tblDRF_part.convertRowIndexToModel(tblDRF_part.getSelectedRow()) + 1;
		} else if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_ENTER) {
			column = tblDRF_part.convertColumnIndexToModel(tblDRF_part.getSelectedColumn());
			row = tblDRF_part.convertRowIndexToModel(tblDRF_part.getSelectedRow()) - 1;
		}

		String div_id = "";
		//		String dept_id = "";
		String proj_id = "";
		//		String subproj_id = "";

		if (row == -1) {
		} else {
			try {
				div_id = modelDRF_part.getValueAt(row, 4).toString().trim();
			} catch (NullPointerException e) {
				div_id = "";
			}
			//			try {
			//				dept_id = modelDRF_part.getValueAt(row, 3).toString().trim();
			//			} catch (NullPointerException e) {
			//				dept_id = "";
			//			}
			try {
				proj_id = modelDRF_part.getValueAt(row, 5).toString().trim();
			} catch (NullPointerException e) {
				proj_id = "";
			}
			//			try {
			//				subproj_id = modelDRF_part.getValueAt(row, 6).toString().trim();
			//			} catch (NullPointerException e) {
			//				subproj_id = "";
			//			}

			//			if (column == 2 || column == 3) {
			//				if (div_id.equals("") || dept_id.equals("")) {
			//				} else {
			//					if (sql_getDiv(dept_id).equals(div_id)) {
			//
			//					} else {
			//						JOptionPane.showMessageDialog(getContentPane(), "Dept ID / Div ID not matched.", "ERROR",
			//								JOptionPane.ERROR_MESSAGE);
			//						modelDRF_part.setValueAt("", row, column);
			//					}
			//				}
			//			}
			//
			//			else if (column == 5 || column == 6) {
			//				if (proj_id.equals("") || subproj_id.equals("")) {
			//				} else {
			//					if (sql_getProj(subproj_id).equals(proj_id)) {
			//
			//					} else {
			//						JOptionPane.showMessageDialog(getContentPane(), "Proj ID / Phase ID not matched.", "ERROR",
			//								JOptionPane.ERROR_MESSAGE);
			//						modelDRF_part.setValueAt("", row, column);
			//					}
			//				}
			//			}
		}

	}

	public void showWarningMessage(String message, String title) {
		JOptionPane.showMessageDialog(getContentPane(), message, title, JOptionPane.WARNING_MESSAGE);
	}

	private void setCompEnabled(JPanel panel) {
		this.setComponentsEnabled(panel, true);
	}

	private void setCompEditable(JPanel panel, Boolean editable) {
		this.setComponentsEditable(panel, editable);
	}

	/*	
	public String getSelectedAccess() {

		String[] options = {"USER", "ACCTNG"};

		// Show the option dialog
		int choice = JOptionPane.showOptionDialog(
				null, // Parent component (null means no parent)
				"Please select the type of access", // Message
				"Select Access Type", // Title of the dialog
				JOptionPane.DEFAULT_OPTION, // Option type (no default option)
				JOptionPane.QUESTION_MESSAGE, // Message type (question)
				null, // Icon (null means use default icon)
				options, // The options the user can select
				options[0] // Default selection (User Access)
				);

		// Declare the variable to hold the selected access type
		String selectedAccess = "";

		// Handle the user's selection and assign the selected access type to the variable
		if (choice == 0) {
			selectedAccess = "User";
		} else if (choice == 1) {
			selectedAccess = "Accounting";
		} else {
			selectedAccess = "No access selected";
		}

		// You can now use the selectedAccess variable later in your program as needed
		System.out.println("Selected access type: " + selectedAccess);

		return selectedAccess;
	}
	 */	

	private Boolean isPayeeEmployee(String entity_id) {
		Boolean isPayeeEmployee = false; 

		pgSelect db = new pgSelect(); 

		db.select("SELECT 1 FROM rf_employee WHERE entity_id = '"+entity_id+"' AND status_id = 'A';"); 

		if(db.isNotNull()) {
			isPayeeEmployee = true; 
		} else {
			isPayeeEmployee = false; 
		}
		System.out.println("");
		System.out.println("Is Payee an employee? " + isPayeeEmployee);
		return isPayeeEmployee;

	}

	/**
	 * Applies a placeholder to the specified JTextArea.
	 *
	 * @param textArea    The JTextArea to apply the placeholder to.
	 * @param placeholder The placeholder text to display.
	 * @param color       The color of the placeholder text.
	 */
	public static void applyPlaceholder(JTextArea textArea, String placeholder, Color color) {
		textArea.setText(placeholder);
		textArea.setForeground(color);

		textArea.addMouseListener(new MouseAdapter() {
			private boolean clicked = false;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (!clicked && textArea.getText().equals(placeholder)) {
					textArea.setText("");
					textArea.setForeground(Color.BLACK);
					clicked = true; // Prevents resetting placeholder after the first click
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (textArea.getText().isEmpty()) {
					textArea.setText(placeholder);
					textArea.setForeground(color);
					clicked = false; // Resets placeholder if the text area is left empty
				}
			}
		});
	}
	
	  // Method to return an empty string if the JTextArea value is still equal to the placeholder
    public static String getTextIfNotPlaceholder(JTextArea textArea, String placeholder) {
        // Get the current text in the JTextArea
        String currentText = textArea.getText();

        // If the current text is equal to the placeholder, return an empty string
        if (currentText.equals(placeholder)) {
            return "";
        } else {
            return currentText;
        }
    }
}