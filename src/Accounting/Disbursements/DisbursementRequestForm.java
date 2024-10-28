
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
	private JLabel lblDate;
	private static JLabel lblPaymentType;
	private JLabel lblDateEdited;
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
	private static _JTagLabel tagDetail;
	private static _JTagLabel tagAvailerDiv;

	private static JButton btnSave;
	public static JButton btnCancel;
	public static JButton btnAddNew;
	public static JButton btnRefresh;
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
	private static JMenuItem mnicopy;
	private static JMenuItem mnipaste;
	private JMenuItem mnisetup2307;

	public static String drf_no = ""; // this is used to denote the number upon displaying of existing/saved RPLF No.
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
	private JMenuItem mniEditAmount;
	private JPanel pnlEditAmount;
	private JLabel lblEditAmount;
	private _JXFormattedTextField txteditamount;

	private JPanel pnlDRF_Attachments;
	private JScrollPane scpDRFAttachments;



	public static JButton btnPreview;

	public DisbursementRequestForm() {
		super(title, true, true, true, true);
		initGUI();
	}

	public DisbursementRequestForm(String title) {
		super(title);
	}

	public DisbursementRequestForm(String title, boolean resizable, boolean closable, boolean maximizable,
			boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
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
		pnlMain.setPreferredSize(new java.awt.Dimension(1134, 645));

		{
			menu = new JPopupMenu("Popup");
			mnidelete = new JMenuItem("Remove Row");
			mniaddrow = new JMenuItem("Add Row");
			mnicopy = new JMenuItem("Copy");
			mnipaste = new JMenuItem("Paste");
			mniEditAmount = new JMenuItem("Edit Amount");
			menu.add(mnidelete);
			menu.add(mniaddrow);
			JSeparator sp = new JSeparator();
			menu.add(sp);
			menu.add(mnicopy);
			menu.add(mnipaste);
			menu.add(mniEditAmount);

			mnipaste.setEnabled(false);
			mniEditAmount.setEnabled(true);

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
			mnicopy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					copy();
				}
			});
			mnipaste.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					paste();
				}
			});
			mniEditAmount.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					editAmount(); 
				}
			});
		}

		{
			menu2 = new JPopupMenu("Popup");
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
							lookupDRF_no.setLookupSQL(getDRF_no(lookupCompany.getValue()));
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
							btnAddNew.setEnabled(false);
							btnRefresh.setEnabled(true);
							tagDetail.setText(null);
							lookupCompany.setEnabled(false);

							
							btnEdit.setEnabled(true);
							mnisetupPV.setEnabled(true);
							mniInactivate.setEnabled(true);
							btnPreview.setEnabled(true);
							
							mnidelete.setEnabled(false);
							mniaddrow.setEnabled(false);
							mniwriteoff.setEnabled(true);
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
					dteDRFDate.setDateFormatString("yyyy-MM-dd");
					((JTextFieldDateEditor) dteDRFDate.getDateEditor()).setEditable(false);
					dteDRFDate.setDate(FncGlobal.dateFormat(FncGlobal.getDateSQL()));
				}
				{

					dteDueDate = new _JDateChooser("MM/dd/yy", "##/##/##", '_');
					pnlDRFDtl_1b.add(dteDueDate);
					dteDueDate.setBounds(485, 7, 125, 21);
					dteDueDate.setEnabled(false);
					dteDueDate.setDateFormatString("yyyy-MM-dd");
					((JTextFieldDateEditor) dteDueDate.getDateEditor()).setEditable(false);
					dteDueDate.setDate(getDueDate()); 
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
					lookupRequestType.setEnabled(false);
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

								if (lookupRequestType.getText().equals("02")
										|| lookupRequestType.getText().equals("07")) {
									setColumnNonTaxable();
								} else {
									setColumnTaxable();
								}
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

//								if (entityhasUnliquidatedCA((String) data[3]) == false) { // XXX CONDITION NEED TO MODIFY

								String entity_name = (String) data[4];
								payee_id = (String) data[3]; 
								tagPayee.setTag(entity_name);
								lookupPayeeType.setLookupSQL(getPayee_type(payee_id));
								//									lookupAvailer.setValue((String) data[3]); //COMMENTED BY MONIQUE; DISABLED AUTO TAG TO MANUALLY SELECT AVAILER
								//									tagAvailer.setTag(entity_name);
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
								//								} else {
								//									if (JOptionPane.showConfirmDialog(getContentPane(),
								//											"The selected check payee has unliquidated CA, proceed anyway?",
								//											"Confirmation", JOptionPane.YES_NO_CANCEL_OPTION,
								//											JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
								//										String entity_name = (String) data[4];
								//
								//										tagPayee.setTag(entity_name);
								//										lookupPayeeType.setLookupSQL(getEntity_type((String) data[3]));
								//										lookupAvailer.setValue((String) data[3]);
								//										tagAvailer.setTag(entity_name);
								//										is_payee_vatable = (Boolean) data[1];
								//										tax_rate = 0.00;
								//
								//										setColumnIfVatableEntity();
								//										setPayee();
								//
								//										lblPayeeType.setEnabled(true);
								//										lookupPayeeType.setEnabled(true);
								//										tagPayeeType.setEnabled(true);
								//
								//										lookupPayeeType.setValue("");
								//										tagPayeeType.setText("[ ]");
								//									} 
								//								}
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

//								if (entityhasUnliquidatedCA((String) data[3]) == false) { XXX CONDITION NEED TO MODIFY
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

								//								} else {
								//									if (JOptionPane.showConfirmDialog(getContentPane(),
								//											"The selected availer has unliquidated CA, proceed anyway?", "Confirmation",
								//											JOptionPane.YES_NO_CANCEL_OPTION,
								//											JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
								//										String entity_name = (String) data[4];
								//										tagAvailer.setTag(entity_name);
								//										setPayee();
								//									}
								//								}
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

								if (lookupRequestType.getText().equals("02")
										|| lookupRequestType.getText().equals("07")) {
									tax_rate = 0.00; 
									setColumnNonTaxable();
								} else {
									tax_rate = Double.parseDouble(data[3].toString());
									setColumnTaxable();

								}
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
					tagDetail = new _JTagLabel("");
					pnlDRF_particular.add(tagDetail, BorderLayout.NORTH);
					tagDetail.getDocument().addDocumentListener(new DocumentListener() {
						public void changedUpdate(DocumentEvent e) {
							warn();
						}

						public void removeUpdate(DocumentEvent e) {
							warn();
						}

						public void insertUpdate(DocumentEvent e) {
							warn();
						}

						public void warn() {
							if (tagDetail.getText().contains("null")) {
								tagDetail.setForeground(Color.RED);
							} else {
								tagDetail.setForeground(Color.BLACK);
							}
						}
					});

				}

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
									computeDRF_amount_fromPayee2();
//									checkCostCenter_manual(evt);
								}

							}

							public void keyReleased(KeyEvent evt) {
								if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
									tblDRF_part.packAll();
									computeDRF_amount_fromPayee2();
									if (tblDRF_part.getSelectedColumn() != 5 || tblDRF_part.getSelectedColumn() != 6) {
//										checkCostCenter_manual(evt);
									}
								}

							}

							public void keyPressed(KeyEvent e) {
								if (e.getKeyCode() == KeyEvent.VK_ENTER) {
									tblDRF_part.packAll();
									computeDRF_amount_fromPayee2();
//									checkCostCenter_manual(e);
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

						tblDRF_part.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent arg0) {
								try {
									if (!arg0.getValueIsAdjusting()) {

										lineno = (String) modelDRF_part.getValueAt(tblDRF_part.getSelectedRow(), 1);
										generateDetail(lineno);
									}
								} catch (ArrayIndexOutOfBoundsException e) {
								}
							}
						});
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

				}

				{
					scrollDRF_part_total = new _JScrollPaneTotal(scrollDRF_part);
					pnlDRF_particular.add(scrollDRF_part_total, BorderLayout.SOUTH);
					{
						modelDRF_part_total = new modelDRF_particulars_total();
						modelDRF_part_total.addRow(new Object[] { "Total", null, null, null, new BigDecimal(0.00), null, null, null,
								null, null, null, null, null, null, null, null, null, null, null, null, null, null, new BigDecimal(0.00), new BigDecimal(0.00), null, new BigDecimal(0.00), null, null, new BigDecimal(0.00),
								new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
								new BigDecimal(0.00), new BigDecimal(0.00)});

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
					txtDRFParticulars.setText("");
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
					txtDRFOtherDetails.setText("");
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
					txtDRFReqRemarks.setText("");
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
		
		String sql = "select \n"
				+ "a.acct_id\n"
				+ ", COALESCE(d.proj_cost_accnt_id, '') \n"
				+ ", a.div_id\n"
				+ ", a.proj_id\n"
				+ ", trim(b.acct_name) as Account_Description\n"
				+ ", a.amount\n"
				+ ", NULL as withinBudget\n"
				+ ", a.payee_id\n"
				+ ", a.payee_type_id\n"
				+ ", trim(c.entity_name) as payee_name\n"
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
				+ ", a.rec_id::VARCHAR 		\n"
				+ ", fn_get_div_alias(a.div_id) as division\n"
				+ ", (Select proj_alias from mf_project where proj_id = a.proj_id and status_id = 'A') as proj_id\n"
				+ "from rf_drf_detail a\n"
				+ "left join mf_boi_chart_of_accounts b on a.acct_id = b.acct_id\n"
				+ "left join rf_entity c on a.payee_id = c.entity_id\n"
				+ "left join mf_project_cost_accnts d on d.boi_acct_id = b.acct_id and d.status_id ='A' \n"
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
			modelDRF_part_total.addRow(new Object[] { "Total", null, null, null, null, new BigDecimal(0.00), null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, new BigDecimal(0.00), new BigDecimal(0.00), null, new BigDecimal(0.00),
					null, null, new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
					new BigDecimal(0.00), new BigDecimal(0.00), null});

			tblDRF_part_total = new _JTableTotal(modelDRF_part_total, tblDRF_part);
			tblDRF_part_total.setFont(dialog11Bold);
			scrollDRF_part_total.setViewportView(tblDRF_part_total);
			((_JTableTotal) tblDRF_part_total).setTotalLabel(0);
		}

		enable_fields(false);
		btnCancel.setEnabled(true);
		btnRefresh.setEnabled(true);
		lblDRF_no.setEnabled(true);
		lookupDRF_no.setEnabled(true);
		modelDRF_part.setEditable(false);
		adjustRowHeight();
		mnidelete.setEnabled(false);
		mniaddrow.setEnabled(false);

		tblDRF_part.getColumnModel().getColumn(4).setPreferredWidth(250);
		tblDRF_part.getColumnModel().getColumn(8).setPreferredWidth(250);


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
		lookupPV_no.setText((String) drf_hdr[3]);
		lookupDiv.setText((String) drf_hdr[20]);
		tagAvailerDiv.setText(div_name);

		lookupRequestType.setText((String) drf_hdr[4]);
		tagReqType.setTag((String) drf_hdr[5]);

		lookupPayee.setText((String) drf_hdr[6]);
		lookupAvailer.setText((String) drf_hdr[7]);
		tagPayee.setTag((String) drf_hdr[8]);
		tagAvailer.setTag((String) drf_hdr[9]);

		lookupPayeeType.setText((String) drf_hdr[10]);
		tagPayeeType.setTag((String) drf_hdr[11]);

		txtStatus.setText((String) drf_hdr[12]);
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
			modelDRF_part_total.addRow(new Object[] { "Total", null, null, null, null, new BigDecimal(0.00), null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, new BigDecimal(0.00), new BigDecimal(0.00), null, new BigDecimal(0.00),
					null, null, new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
					new BigDecimal(0.00), new BigDecimal(0.00), null});

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
		mnicopy.setEnabled(x);

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
		dteDueDate.setDate(FncGlobal.dateFormat(FncGlobal.getDateSQL()));
		dteEdited.setDate(null);

		txtDRFParticulars.setText("");
		txtDRFOtherDetails.setText("");
		txtDRFReqRemarks.setText("");
		txtDRFAttachments.setText("");

	}

	public static void refresh_tablesMain() {
		// reset table 1
		FncTables.clearTable(modelDRF_part);
		FncTables.clearTable(modelDRF_part_total);
		rowHeaderDRF = tblDRF_part.getRowHeader22();
		scrollDRF_part.setRowHeaderView(rowHeaderDRF);
		modelDRF_part_total.addRow(new Object[] { "Total", null, null, null, null, new BigDecimal(0.00), null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, null, new BigDecimal(0.00), new BigDecimal(0.00), null, new BigDecimal(0.00),
				null, null, new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
				new BigDecimal(0.00), new BigDecimal(0.00), null});;

	}

	public void initialize_comp() {

		lookupCompany.setValue("01");
		company = FncGlobal.GetString("Select company_name from mf_company where co_id = '"+lookupCompany.getText().trim()+"';");
		tagCompany.setTag(company);
		company_logo = sql_getCompanyLogo(lookupCompany.getText().trim());

		lblDRF_no.setEnabled(true);
		lookupDRF_no.setEnabled(true);
		lookupDRF_no.setLookupSQL(getDRF_no(lookupCompany.getValue()));
		btnCancel.setEnabled(true);
		btnAddNew.setEnabled(true);
		
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
			if (UserInfo.Department == "010") {
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
			} else {
				JOptionPane.showMessageDialog(getContentPane(), "Sorry, you are not authorized to edit request.",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	
		if (e.getActionCommand().equals("Save")) {
			save();
		}

		if (e.getActionCommand().equals("Preview") && FncAcounting.EmpCanPreview(UserInfo.EmployeeCode, "1") == true) {
			preview();
		} else if (e.getActionCommand().equals("Preview")
				&& FncAcounting.EmpCanPreview(UserInfo.EmployeeCode, "1") == false) {
			JOptionPane.showMessageDialog(getContentPane(), "Sorry, you are not authorized to preview/print RPLF.",
					"Information", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void mouseClicked(MouseEvent evt) {

		int row = tblDRF_part.convertRowIndexToModel(tblDRF_part.getSelectedRow());
		int column = tblDRF_part.getSelectedColumn();

		if ((evt.getClickCount() >= 2)) {
			clickTableColumn();
		}

		else if ((evt.getClickCount() >= 2) && row == 5) {
			computeDRF_amount_fromPayee2();
		}

		else if ((evt.getClickCount() == 1) && row == 21) {

			for (int x = 0; x < modelDRF_part.getRowCount(); x++) {
				Boolean isSelected1 = (Boolean) modelDRF_part.getValueAt(x, 21);

				if (isSelected1) {
					if (tax_rate >= 0.00) {
						modelDRF_part.setValueAt(new BigDecimal(tax_rate), x, 28);
					}
				} else {
					modelDRF_part.setValueAt(new BigDecimal(0.00), x, 28);
				}
			}
		}
		if (evt.getSource().equals(tblDRF_part)
				&& (column != 2 && column != 3 && column != 4 && column != 6)) {

			computeDRF_amount_fromPayee2();

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
		btnRefresh.setEnabled(true);
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

		tagDetail.setText(null);

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
			btnRefresh.setEnabled(false);
			btnCancel.setEnabled(false);
			lblDRF_no.setEnabled(true);
			lookupDRF_no.setEnabled(true);
			mnidelete.setEnabled(false);
			mniaddrow.setEnabled(false);
			tabCenter.setSelectedIndex(0);
			mnipaste.setEnabled(false);
			mniwriteoff.setEnabled(false);
			lookupCompany.setEnabled(true);
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
				btnRefresh.setEnabled(false);
				btnCancel.setEnabled(false);
				lblDRF_no.setEnabled(true);
				lookupDRF_no.setEnabled(true);
				mnipaste.setEnabled(false);
				mniwriteoff.setEnabled(false);
				lookupCompany.setEnabled(true);
			}
		}

		tagDetail.setText(null);
	}

	public void add() {

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
		tagDetail.setText(null);
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
		btnRefresh.setEnabled(false);

		lblDRF_no.setEnabled(false);
		lookupDRF_no.setEnabled(false);
		lblPV_no.setEnabled(false);
		lookupPV_no.setEnabled(false);
		
		lookupPayee.setLookupSQL(getEntityList());
		lookupAvailer.setLookupSQL(getAvailerList());
		lookupPaymentType.setLookupSQL(getPayment_type());
		mnidelete.setEnabled(true);
		mniaddrow.setEnabled(true);
		modelDRF_part.setEditable(true);

		lblDRFDate.setEnabled(false);
		dteDRFDate.setEnabled(false);
		mnisetupPV.setEnabled(true);
		mniwriteoff.setEnabled(false);
		tagDetail.setText(null);

	}

	public void save() {

		if (checkCompleteDetails() == false) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter complete request details.",
					"Incomplete Detail", JOptionPane.WARNING_MESSAGE);
		} else {
			
			executeSave();
		}
	}

	public void updateRequest_status(String status) {
		String remark = "";
		if (status.equals("I")) {
			remark = "delete";
		} else {
			remark = "activate";
		}
		if (JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to " + remark + " this request?",
				"Confirmation", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			String rplf = lookupDRF_no.getText().trim();
			pgUpdate db = new pgUpdate();
			updateRPLF_header_status(rplf, db, status);
			db.commit();
			JOptionPane.showMessageDialog(getContentPane(), "Payment request " + remark + "d.", "Information",
					JOptionPane.INFORMATION_MESSAGE);
			refresh();
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

		FncReport.generateReport("/Reports/rptDisbursementRequestForm.jasper", title, mapParameters);
	}

	public void executeSave() {

		if (checkDRFAmount() == false) {
			JOptionPane.showMessageDialog(getContentPane(), "Net amount must be greater than zero.", "Amount",
					JOptionPane.WARNING_MESSAGE);
		} else {

			if (checkAcctID_ifcomplete() == false) {
				JOptionPane.showMessageDialog(getContentPane(), "Please enter account ID.", "Account ID",
						JOptionPane.WARNING_MESSAGE);
			} else {
				
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

				}
			}
		}
	}

	// select, lookup and get statements
	public static String getDRF_no(String co_id) {
		String SQL = "SELECT a.drf_no as \"DRF No.\" \n"
				+ ", a.drf_date as \"DRF Date\"\n"
				+ ", trim(b.entity_name) as \"Payee\"\n"
				+ ", d.status_desc as \"Status\" \n"
				+ "from rf_drf_header a\n"
				+ "left join rf_entity b on a.payee = b.entity_id\n"
				+ "left join mf_record_status d on a.status_id = d.status_id\n"
				+ "where a.co_id = '"+co_id+"'\n"
				+ "order by a.drf_no desc ";
		
		System.out.println("SQL-getDRFNo: "+ SQL);

		return SQL; 
	}
	
	public static String getProjCostAccount(String boi_acct_id) {
		String SQL = " SELECT a.proj_cost_accnt_desc AS \"Description\"\n"
				+ ", a.proj_cost_accnt_id AS \"Proj. Cost ID\"\n"
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
				+ "//AND boi_acct_id = '"+boi_acct_id+"'";
		
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

	// SAAN GINAMIT?
	public String getExecOfc() {

		String sql = "select \n" + "trim(exec_office_code) as \"Office Code\",\n" + "trim(status_id) as \"Status\",\n"
				+ "trim(exec_office_name) as \"Office Name\",\n" + "trim(exec_office_alias) as \"Office Alias\"\n"
				+ "from mf_exec_office \n" + "where status_id = 'A' \n" + "AND rec_status != 'D' ";

		FncSystem.out("SQL-getExecOfc: ", sql);
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
				+ "where a.co_id = '\"+co_id+\"' \n"
				+ "and a.status_id ='A'";
		FncSystem.out("SQL-getProject", sql);
		return sql;

	}

	public static String getReferenceDoc() {

		String sql = "select trim(doc_id) as \"Doc ID\"\n"
				+ ", trim(doc_desc) as \"Doc Name\"\n"
				+ ", trim(status_id) as \"Status\"\n"
				+ ", trim(doc_alias) as \"Doc Alias\"\n"
				+ "from mf_system_doc\n"
				+ "where status_id = 'A'\n"
				+ "order by doc_id";
		
		FncSystem.out("SQL-getReferenceDoc", sql);
		return sql;

	}

	public String getWTaxID() {

		String sql = "select wtax_id as \"WTax ID\"\n"
				+ ", trim(wtax_desc) as \"Description\"\n"
				+ ", wtax_rate as \"Rate (%)\"\n"
				+ ", wtax_bir_code as \"Code\"\n"
				+ "from rf_withholding_tax \n"
				+ "where status_id = 'A'\n"
				+ "order by wtax_id";

		FncSystem.out("SQL-getWTaxID", sql);
		return sql;

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

		FncSystem.out("Batch No", sql);
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
				+ "left join rf_withholding_tax c on b.wtax_id = c.wtax_id";
		
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

	// check and validate
	private Boolean checkCompleteDetails() {

		boolean x = false;
		String payee, request_type, payee2;

		payee = lookupPayee.getText();
		payee2 = lookupAvailer.getText();
		request_type = lookupPayeeType.getText();
		pay_type_id = lookupPaymentType.getText();

		if (payee.equals("") || payee2.equals("") || request_type.equals("") || pay_type_id.equals("")) {
			x = false;
		} else {
			x = true;
		}
		
		

		return x;
	}

	private Boolean checkDRFAmount() {

		boolean x = false;

		Double drf_amt = Double.parseDouble(modelDRF_part_total.getValueAt(0, 5).toString());
		
		System.out.println("");
		System.out.println("Value of DRF Amount: "+ drf_amt);
		
		if (drf_amt > 0) {
			System.out.println("DRF Amt is Greater than 0!");
			x = true;
		} else if (drf_amt < 0) {
			x = false;
		} else {
			x = false;
		}

		return x;

	}

	private Boolean checkAcctID_ifcomplete() {

		boolean x = true;

		int rw = tblDRF_part.getModel().getRowCount();
		int w = 0;

		while (w < rw) {
			Double drf_amt = Double.parseDouble(modelDRF_part.getValueAt(w, 5).toString());
			if (drf_amt > 0) {

				String acct_id = modelDRF_part.getValueAt(w, 0).toString().trim();
				if (acct_id.equals("")) {
					x = false;
					break;
				} else {
				}

			} else {
			}
			w++;
		}	
		return x;

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
	public static void totalDRF(DefaultTableModel modelMain, DefaultTableModel modelTotal) {

		FncTables.clearTable(modelTotal);// Code to clear modelMain.

		BigDecimal amt_bd = new BigDecimal(0.00);
		BigDecimal gr_amt_bd = new BigDecimal(0.00);
		BigDecimal net_amt_bd = new BigDecimal(0.00);
		BigDecimal wtax_amt_bd = new BigDecimal(0.00);
		BigDecimal vat_amt_bd = new BigDecimal(0.00);
		BigDecimal exp_amt_bd = new BigDecimal(0.00);
		BigDecimal pv_amt_bd = new BigDecimal(0.00);
		BigDecimal ret_amt_bd = new BigDecimal(0.00);
		BigDecimal bc_liq_amt_bd = new BigDecimal(0.00);
		BigDecimal other_liq_amt_bd = new BigDecimal(0.00);
		BigDecimal dp_recoup_amt_bd = new BigDecimal(0.00);

		for (int x = 0; x < modelMain.getRowCount(); x++) {

			try {
				amt_bd = amt_bd.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 5).toString()))));
			} catch (NullPointerException e) {
				amt_bd = amt_bd.add(new BigDecimal(0.00));
			}

			try {
				gr_amt_bd = gr_amt_bd
						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 23).toString()))));
			} catch (NullPointerException e) {
				gr_amt_bd = gr_amt_bd.add(new BigDecimal(0.00));
			}

			try {
				net_amt_bd = net_amt_bd
						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 24).toString()))));
			} catch (NullPointerException e) {
				net_amt_bd = net_amt_bd.add(new BigDecimal(0.00));
			}

			try {
				wtax_amt_bd = wtax_amt_bd
						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 29).toString()))));
			} catch (NullPointerException e) {
				wtax_amt_bd = wtax_amt_bd.add(new BigDecimal(0.00));
			}

			try {
				vat_amt_bd = vat_amt_bd
						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 26).toString()))));
			} catch (NullPointerException e) {
				vat_amt_bd = vat_amt_bd.add(new BigDecimal(0.00));
			}

			try {
				exp_amt_bd = exp_amt_bd
						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 30).toString()))));
			} catch (NullPointerException e) {
				exp_amt_bd = exp_amt_bd.add(new BigDecimal(0.00));
			}

			try {
				ret_amt_bd = ret_amt_bd
						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 31).toString()))));
			} catch (NullPointerException e) {
				ret_amt_bd = ret_amt_bd.add(new BigDecimal(0.00));
			}

			try {
				dp_recoup_amt_bd = dp_recoup_amt_bd
						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 32).toString()))));
			} catch (NullPointerException e) {
				dp_recoup_amt_bd = dp_recoup_amt_bd.add(new BigDecimal(0.00));
			}

			try {
				bc_liq_amt_bd = bc_liq_amt_bd
						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 33).toString()))));
			} catch (NullPointerException e) {
				bc_liq_amt_bd = bc_liq_amt_bd.add(new BigDecimal(0.00));
			}

			try {
				other_liq_amt_bd = other_liq_amt_bd
						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 34).toString()))));
			} catch (NullPointerException e) {
				other_liq_amt_bd = other_liq_amt_bd.add(new BigDecimal(0.00));
			}

			try {
				pv_amt_bd = pv_amt_bd
						.add((BigDecimal.valueOf(Double.parseDouble(modelMain.getValueAt(x, 35).toString()))));
			} catch (NullPointerException e) {
				pv_amt_bd = pv_amt_bd.add(new BigDecimal(0.00));
			}

		}

		System.out.printf("amt_bd:%s\n", amt_bd);
		System.out.printf("gr_amt_bd:%s\n", gr_amt_bd);
		System.out.printf("net_amt_bd:%s\n", net_amt_bd);
		System.out.printf("vat_amt_bd:%s\n", vat_amt_bd);
		System.out.printf("wtax_amt_bd:%s\n", wtax_amt_bd);
		System.out.printf("exp_amt_bd:%s\n", exp_amt_bd);
		System.out.printf("ret_amt_bd:%s\n", ret_amt_bd);
		System.out.printf("dp_recoup_amt_bd:%s\n", dp_recoup_amt_bd);
		System.out.printf("bc_liq_amt_bd:%s\n", bc_liq_amt_bd);
		System.out.printf("other_liq_amt_bd:%s\n", other_liq_amt_bd);
		System.out.printf("pv_amt_bd:%s\n", pv_amt_bd);

		modelTotal.addRow(new Object[] { "Total", null, null, null, null, amt_bd, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, null,
				gr_amt_bd, net_amt_bd, null, vat_amt_bd, null, null, wtax_amt_bd,
				exp_amt_bd, ret_amt_bd, dp_recoup_amt_bd, bc_liq_amt_bd, other_liq_amt_bd, pv_amt_bd, null});
	}

	private void clickTableColumn() {
		int column = tblDRF_part.getSelectedColumn();
		int row = tblDRF_part.convertRowIndexToModel(tblDRF_part.getSelectedRow());

		System.out.printf("column : " + column);
		System.out.printf("row : " + row);

		String entity_id = modelDRF_part.getValueAt(row, 6).toString().trim();
		String boi_acct_id = modelDRF_part.getValueAt(row, 0).toString().trim();

		Integer x[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
				26, 27, 28, 29, 30, 31, 32, 33, 34, 35};
		String sql[] = {getChartofAccount(), getProjCostAccount(boi_acct_id), getDivision(), getProject(co_id), "", "", "", getEntityList(), getPayee_type(entity_id),
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", getWTaxID(), "", "", "", "", "", "", "", "" };
		String lookup_name[] = { "Chart of Account", "Project Cost Account", "Division", "Project", "", "", "Entity", "Entity Type",
				"", "", "", "", "", "", "", "", "", "", "","Withholding Tax", "", "", "", "", "", "",  "", "" };

		System.out.println("column : " + column);
		System.out.println("row : " + row);

		if (column == x[column] && modelDRF_part.isEditable() && sql[column] != "") {
			_JLookupTable dlg = new _JLookupTable(FncGlobal.homeMDI, null, lookup_name[column], sql[column], false);
			dlg.setLocationRelativeTo(FncGlobal.homeMDI);
			if (column == 7) {// payee id
				dlg.setFilterIndex(4);//
			} else if (column == 0) {// acct id
				dlg.setFilterIndex(3);// 
			} else {
				dlg.setFilterIndex(0);
			}

			dlg.setVisible(true);

			Object[] data = dlg.getReturnDataSet();
			//			if (data != null && column == 0) {
			//				tblDRF_part.packAll();
			//			} else 
			if (data != null && column == 0) { // AcctID
				modelDRF_part.setValueAt(data[2], row, column);
				modelDRF_part.setValueAt(data[3], row, column + 4);//Accnt Desc
				
			} else if (data != null && column == 7) { //PayeeID
				modelDRF_part.setValueAt(data[3], row, column);
				modelDRF_part.setValueAt(data[4], row, column + 2); //PayeeName
				modelDRF_part.setValueAt(data[1], row, column + 14);
				computeDRF_amount_fromPayee2();
				System.out.println("Dumaan dito sa clicktable column 7");
				
			} else if (data != null && column == 8) { //PayeeType
				modelDRF_part.setValueAt(data[0], row, column);
				modelDRF_part.setValueAt(data[2], row, column - 1);
				
			} else if (data != null && column == 2) { //Div 
				modelDRF_part.setValueAt(data[0], row, column);
				
			} else if (data != null && column == 1) { //Proj Cost ID
				modelDRF_part.setValueAt(data[0], row, column);
				
			} else if (data != null && column == 7) { // XXX TODO NEED TO CHECK AND MODIFY IF APPLICABLE
				is_payee_vatable = (Boolean) data[1];
				if (entityhasUnliquidatedCA((String) data[0]) == false) {
					modelDRF_part.setValueAt(data[3], row, column); // **EDITED BY JED DCRF NO. 1930 2022-01-26 :
					// INCLUDE TIN NUMBER IN LOOKUP**//
					modelDRF_part.setValueAt(data[4], row, column + 5);// **EDITED BY JED DCRF NO. 1930 2022-01-26 :
					// INCLUDE TIN NUMBER IN LOOKUP**//
				} else {
					if (JOptionPane.showConfirmDialog(getContentPane(),
							"The selected availer has unliquidated CA, proceed anyway?", "Confirmation",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						modelDRF_part.setValueAt(data[0], row, column);
						modelDRF_part.setValueAt(data[1], row, column + 5);
					} else {
						modelDRF_part.setValueAt("", row, column);
					}
				}

			} else if (data != null && column == 27 || modelDRF_part.getValueAt(row, 27) != null) {
				modelDRF_part.setValueAt(data[0], row, column);
				modelDRF_part.setValueAt(data[2], row, column + 1);
				computeDRF_amount_fromPayee2();
			}
			
			else if (data != null && column == 5) {
				computeDRF_amount_fromPayee2();
			}
		}

		else {
		}
		tblDRF_part.packAll();
	}

	private void computeDRF_amount_fromPayee2() {
		
		int selected_row = tblDRF_part.convertRowIndexToModel(tblDRF_part.getSelectedRow());

		Double amount = 0.00;
		Double vat_rate = 0.00;
		Double tax_rate = 0.00;
		Double retention_amt = 0.00;
		Double dp_recoup_amt = 0.00;
		Double bc_liqui_amt = 0.00;
		Double other_liqui_amt = 0.00;

		try {
			amount = Double.parseDouble(modelDRF_part.getValueAt(selected_row, 5).toString());
		} catch (NullPointerException e) {
			amount = 0.00;
		}
		try {
			vat_rate = Double.parseDouble(modelDRF_part.getValueAt(selected_row, 25).toString()) / 100;
		} catch (NullPointerException e) {
			vat_rate = 0.00;
		}
		try {
			tax_rate = Double.parseDouble(modelDRF_part.getValueAt(selected_row, 28).toString()) / 100;
		} catch (NullPointerException e) {
			tax_rate = 0.00;
		}
		try {
			retention_amt = Double.parseDouble(modelDRF_part.getValueAt(selected_row, 31).toString());
		} catch (NullPointerException e) {
			retention_amt = 0.00;
		}
		try {
			dp_recoup_amt = Double.parseDouble(modelDRF_part.getValueAt(selected_row, 32).toString());
		} catch (NullPointerException e) {
			dp_recoup_amt = 0.00;
		}
		try {
			bc_liqui_amt = Double.parseDouble(modelDRF_part.getValueAt(selected_row, 33).toString());
		} catch (NullPointerException e) {
			bc_liqui_amt = 0.00;
		}
		try {
			other_liqui_amt = Double.parseDouble(modelDRF_part.getValueAt(selected_row, 34).toString());
		} catch (NullPointerException e) {
			other_liqui_amt = 0.00;
		}

		System.out.printf("amount is: %s\n", amount);
		double gr_amt = amount;
		double net_amt = gr_amt; 
		System.out.printf("gross amt is: %s\n", gr_amt);

		Boolean isSelected = (Boolean) modelDRF_part.getValueAt(selected_row, 21); //VATABLE ENTITY
		if (isSelected) {

			net_amt = getNetAmount(gr_amt).doubleValue(); 
			System.out.printf("net amt is: %s\n", net_amt);

			modelDRF_part.setValueAt(new BigDecimal(12.00), selected_row, 25); // Vat Rate

			double vatAmt = getVatAmount(gr_amt).doubleValue();
			System.out.printf("vat amt is: %s\n", vatAmt);

			modelDRF_part.setValueAt(vatAmt, selected_row, 26); // Vat Amt
			modelDRF_part.setValueAt(net_amt, selected_row, 24); // Net Amt

			double expAmt = getExpAmount(gr_amt, vatAmt).doubleValue(); 
			System.out.printf("expense amt is: %s\n", expAmt);
			modelDRF_part.setValueAt(expAmt, selected_row, 30);//Exp Amt

		} else {

			net_amt = gr_amt; 
			modelDRF_part.setValueAt(gr_amt, selected_row, 23);
			modelDRF_part.setValueAt(net_amt, selected_row, 24);
			modelDRF_part.setValueAt(new BigDecimal(0.00), selected_row, 25); //VAT RATE
			modelDRF_part.setValueAt(new BigDecimal(0.00), selected_row, 26); // VAT AMT
			modelDRF_part.setValueAt(new BigDecimal(0.00), selected_row, 30); // EXP AMT

		}

		double wtaxAmt = getWtaxAmount(gr_amt, vat_rate, tax_rate).doubleValue();
		System.out.printf("tax_rate is: %s\n", tax_rate);
		System.out.printf("vat_rate is: %s\n", vat_rate);
		System.out.printf("gr_amt is: %s\n", gr_amt);
		System.out.printf("wtax amt is: %s\n", wtaxAmt);

		double pvAmt = getPVAmount(gr_amt, wtaxAmt, dp_recoup_amt, retention_amt, bc_liqui_amt, other_liqui_amt)
				.doubleValue();
		System.out.printf("pv amt is: %s\n", pvAmt);

		BigDecimal grossAmt_bd = new BigDecimal(gr_amt);
		BigDecimal wtaxAmt_bd = new BigDecimal(wtaxAmt);
		BigDecimal pvAmt_bd = new BigDecimal(pvAmt);
		BigDecimal retAmt_bd = new BigDecimal(retention_amt);
		BigDecimal dpRecoupAmt_bd = new BigDecimal(dp_recoup_amt);
		BigDecimal bc_liqui_amt_bd = new BigDecimal(bc_liqui_amt);
		BigDecimal other_liqui_amt_bd = new BigDecimal(other_liqui_amt);

		modelDRF_part.setValueAt(grossAmt_bd, selected_row, 5);
		modelDRF_part.setValueAt(grossAmt_bd, selected_row, 23);
		modelDRF_part.setValueAt(wtaxAmt_bd, selected_row, 29);

		modelDRF_part.setValueAt(retAmt_bd, selected_row, 31);
		modelDRF_part.setValueAt(dpRecoupAmt_bd, selected_row, 32);
		modelDRF_part.setValueAt(bc_liqui_amt_bd, selected_row, 33);
		modelDRF_part.setValueAt(other_liqui_amt_bd, selected_row, 34);
		modelDRF_part.setValueAt(pvAmt_bd, selected_row, 35);

		System.out.println("Dumaan dito computeDRF_amount_fromPayee2");
		totalDRF(modelDRF_part, modelDRF_part_total);
	}

	private void computeDRF_amount_fromPayee() {

		int rw = tblDRF_part.getModel().getRowCount();
		int x = 0;

		while (x < rw) {

			Double amount = 0.00;
			Double vat_rate = 0.00;
			Double tax_rate = 0.00;
			Double retention_amt = 0.00;
			Double dp_recoup_amt = 0.00;
			Double bc_liqui_amt = 0.00;
			Double other_liqui_amt = 0.00;

			try {
				amount = Double.parseDouble(modelDRF_part.getValueAt(x, 5).toString());
			} catch (NullPointerException e) {
				amount = 0.00;
			}
			try {
				vat_rate = Double.parseDouble(modelDRF_part.getValueAt(x, 25).toString()) / 100;
			} catch (NullPointerException e) {
				vat_rate = 0.00;
			}
			try {
				tax_rate = Double.parseDouble(modelDRF_part.getValueAt(x, 28).toString()) / 100;
			} catch (NullPointerException e) {
				tax_rate = 0.00;
			}
			try {
				retention_amt = Double.parseDouble(modelDRF_part.getValueAt(x, 31).toString());
			} catch (NullPointerException e) {
				retention_amt = 0.00;
			}
			try {
				dp_recoup_amt = Double.parseDouble(modelDRF_part.getValueAt(x, 32).toString());
			} catch (NullPointerException e) {
				dp_recoup_amt = 0.00;
			}
			try {
				bc_liqui_amt = Double.parseDouble(modelDRF_part.getValueAt(x, 33).toString());
			} catch (NullPointerException e) {
				bc_liqui_amt = 0.00;
			}
			try {
				other_liqui_amt = Double.parseDouble(modelDRF_part.getValueAt(x, 34).toString());
			} catch (NullPointerException e) {
				other_liqui_amt = 0.00;
			}

			double gr_amt = amount;
			System.out.printf("gross amt is: %s\n", gr_amt);

			double netAmt = getNetAmount(gr_amt).doubleValue();
			System.out.printf("vat amt is: %s\n", netAmt);

			double vatAmt = getVatAmount(gr_amt).doubleValue();
			System.out.printf("vat amt is: %s\n", vatAmt);

			double wtaxAmt = getWtaxAmount(gr_amt, vat_rate, tax_rate).doubleValue();
			System.out.printf("wtax amt is: %s\n", wtaxAmt);

			double expAmt = getExpAmount(gr_amt, vatAmt).doubleValue();
			System.out.printf("expense amt is: %s\n", expAmt);

			double pvAmt = getPVAmount(gr_amt, wtaxAmt, dp_recoup_amt, retention_amt, bc_liqui_amt, other_liqui_amt)
					.doubleValue();
			System.out.printf("pv amt is: %s\n", pvAmt);

			BigDecimal grossAmt_bd = new BigDecimal(gr_amt);
			BigDecimal vatAmt_bd = new BigDecimal(vatAmt);
			BigDecimal wtaxAmt_bd = new BigDecimal(wtaxAmt);
			BigDecimal expAmt_bd = new BigDecimal(expAmt);
			BigDecimal pvAmt_bd = new BigDecimal(pvAmt);
			BigDecimal retAmt_bd = new BigDecimal(retention_amt);
			BigDecimal dpRecoupAmt_bd = new BigDecimal(dp_recoup_amt);
			BigDecimal bc_liqui_amt_bd = new BigDecimal(bc_liqui_amt);
			BigDecimal other_liqui_amt_bd = new BigDecimal(other_liqui_amt);

			modelDRF_part.setValueAt(grossAmt_bd, x, 5);
			modelDRF_part.setValueAt(grossAmt_bd, x, 23);
			modelDRF_part.setValueAt(wtaxAmt_bd, x, 29);
			modelDRF_part.setValueAt(vatAmt_bd, x, 26);
			modelDRF_part.setValueAt(expAmt_bd, x, 30);
			modelDRF_part.setValueAt(retAmt_bd, x, 31);
			modelDRF_part.setValueAt(dpRecoupAmt_bd, x, 32);
			modelDRF_part.setValueAt(bc_liqui_amt_bd, x, 33);
			modelDRF_part.setValueAt(other_liqui_amt_bd, x, 34);
			modelDRF_part.setValueAt(pvAmt_bd, x, 35);

			x++;
		}
		System.out.println("Dumaan dito sa computeDRF_amount_fromPayee");
		totalDRF(modelDRF_part, modelDRF_part_total);
	}

	private static BigDecimal getVatAmount(Double gr_amt) {// compute vat amount

		BigDecimal vat_amt = BigDecimal.valueOf(0.00);

		String SQL = "SELECT * FROM fn_compute_vat_amount('" + gr_amt + "')";

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

	private static BigDecimal getWtaxAmount(Double gr_amt, Double vat_rate, Double tax_rate) {// compute wtax amount

		BigDecimal wtax_amt = BigDecimal.valueOf(0.00);

		String SQL = "SELECT * FROM fn_compute_wtax_amount('" + gr_amt + "', '" + vat_rate + "', '" + tax_rate + "')";

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

	private static BigDecimal getExpAmount(Double gr_amt, Double vatAmt) {// compute expense amount

		BigDecimal exp_amt = BigDecimal.valueOf(0.00);

		String SQL = "SELECT * FROM fn_compute_expense_amount('" + gr_amt + "', '" + vatAmt + "')";

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
		computeDRF_amount_fromPayee();
	}

	private void AddRow() { 

		Boolean isprojVatable = (Boolean) modelDRF_part.getValueAt(0, 20);
		Boolean entityVatable = (Boolean) modelDRF_part.getValueAt(0, 21);
		Double vat_rate = Double.parseDouble(modelDRF_part.getValueAt(0, 25).toString());

		modelDRF_part 
		.addRow(new Object[] { "", "", "", "", "", new BigDecimal(0.00), false, sql_getAvailer(),
				"", "", "", "", null, "", "", "", null, "", null, "", isprojVatable, entityVatable, 
				false, new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(vat_rate), new BigDecimal(0.00), 
				sql_getAvailer_wtaxID(), new BigDecimal(sql_getAvailer_wtaxRate()), new BigDecimal(0.00),
				new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00), 
				new BigDecimal(0.00), new BigDecimal(0.00), null });
		
		computeDRF_amount_fromPayee();
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
		String drf_type_id = lookupRequestType.getText().trim();
		String div_id = lookupDiv.getText().trim();
		String payee = lookupPayee.getText().trim();
		String availer = lookupAvailer.getText().trim();
		String payee_type_id = lookupPayeeType.getText().trim();
		String payment_type_id = lookupPaymentType.getText();
		String particulars = txtDRFParticulars.getText().trim().replace("'", "''").trim();
		String other_details = txtDRFOtherDetails.getText().trim().replace("'", "''").trim();
		String req_remarks = txtDRFReqRemarks.getText().trim().replace("'", "''").trim();
		String attachments = txtDRFAttachments.getText().trim().replace("'", "''").trim();
		String user = UserInfo.EmployeeCode;

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
					String proj_cost_id = (String) model.getValueAt(x, 1);
					String proj_id = (String) model.getValueAt(x, 3);
					BigDecimal amt = (BigDecimal) model.getValueAt(x, 5);
					String payeeID = (String) model.getValueAt(x, 7);
					String payeeType = (String) model.getValueAt(x, 8); 
					String invoiceNo = (String) model.getValueAt(x, 15); 
					String invoiceDate = model.getValueAt(x, 16) != null ? model.getFormattedDate(x, 16) : null;
					String SOABillNo = (String) model.getValueAt(x, 17); 
					String SOABillDate =  model.getValueAt(x, 18) != null ? model.getFormattedDate(x, 18) : null;
					String assetNo = (String) model.getValueAt(x, 19); 
					Boolean vatable = (Boolean) model.getValueAt(x, 21); 
					BigDecimal grossAmt = (BigDecimal) model.getValueAt(x, 23); 
					BigDecimal netAmt = (BigDecimal) model.getValueAt(x, 24);
					BigDecimal vatRate = (BigDecimal) model.getValueAt(x, 25);
					BigDecimal vatAmt = (BigDecimal) model.getValueAt(x, 26);
					String WTaxID = (String) model.getValueAt(x, 27); 
					BigDecimal WTaxRate = (BigDecimal) model.getValueAt(x, 28);
					BigDecimal WTaxAmt = (BigDecimal) model.getValueAt(x, 29);
					BigDecimal ExpAmt = (BigDecimal) model.getValueAt(x, 30);
					BigDecimal RetAmt = (BigDecimal) model.getValueAt(x, 31);
					BigDecimal DPRecoupAmt = (BigDecimal) model.getValueAt(x, 32);
					BigDecimal BCLiqAmt = (BigDecimal) model.getValueAt(x, 33);
					BigDecimal OtherLiqAmt = (BigDecimal) model.getValueAt(x, 34);
					BigDecimal PayableAmt = (BigDecimal) model.getValueAt(x, 35);
					String RecID = (String) model.getValueAt(x, 36) != null ? model.getValueAt(x, 36).toString() : "";

					listAcctIDs.add(String.format("'%s'", acct_id));
					listProjCostAcctIDs.add(String.format("'%s'", proj_cost_id));
					listProjIDs.add(String.format("'%s'", proj_id));
					listDRFAmount.add(amt);
					listPayee.add(String.format("'%s'", payeeID));
					listPayeeTypeID.add(String.format("'%s'", payeeType));
					listInvoiceNo.add(String.format("'%s'", invoiceNo));
					listInvoiceDate.add(String.format("'%s'", invoiceDate));
					listSoaBillNo.add(String.format("'%s'", SOABillNo));
					listSoaBillDate.add(String.format("'%s'", SOABillDate));
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
		String invoiceDate = listSoaBillDate.toString().replaceAll("\\[|\\]", "");
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
		
		BigDecimal totalDRFAmt = (BigDecimal) modelDRF_part_total.getValueAt(0, 5);
		
		System.out.println("");
		System.out.println("DRF Amt: "+ amt);
		System.out.println("DRF_No: "+ drf_no);
		System.out.println("Total DRF Amt: "+ totalDRFAmt);
	
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

	public void updateRPLF_detail(String drf_no, pgUpdate db) {

		String sqlDetail = "update rf_request_detail set status_id = 'I' where trim(drf_no) = '" + drf_no
				+ "' and co_id = '" + co_id + "'   ";

		System.out.printf("SQL-updateRPLF_detail: %s", sqlDetail);
		db.executeUpdate(sqlDetail, false);
	}

	public void updateRPLF_header(String drf_no, pgUpdate db) {

		Date date_liq_ext = null;
		String rplf_type_id = "";
		String entity_id1 = "";
		String entity_id2 = "";
		String ent_type_id = "";
		String sd_no = "";
		String doc_id = "";
		Integer proc_id = null;
		String branch_id = "";
		String justif = "";
		String remarks = "";
		String status_id = "";
		String edited_by = "";
		String pay_type = "";

		date_liq_ext = null;
		rplf_type_id = lookupRequestType.getText().trim();
		entity_id1 = lookupPayee.getText().trim();
		entity_id2 = lookupAvailer.getText().trim();
		ent_type_id = lookupPayeeType.getText().trim();
		pay_type = lookupPaymentType.getText();
		sd_no = null;
		doc_id = "09";
		proc_id = 1;
		branch_id = null;
		justif = txtDRFReqRemarks.getText().trim().replace("'", "''");
		;
		remarks = txtDRFOtherDetails.getText().trim().replace("'", "''").trim();
		;
		status_id = "A";
		edited_by = UserInfo.EmployeeCode;

		String sqlDetail = "update rf_request_header set " + "co_id = '" + co_id + "',  \n" + // 1
				"busunit_id = '" + co_id + "',  \n" + // 2
				"drf_no = '" + drf_no + "',  \n" + // 3
				"rplf_date = '" + dateFormat.format(dteDRFDate.getDate()) + "',  \n" + // 4
				"date_needed = '" + dateFormat.format(dteDueDate.getDate()) + "',  \n" + // 5
				"date_liq_ext = " + date_liq_ext + ",  \n" + // 6
				"rplf_type_id = '" + rplf_type_id + "' , \n" + // 7
				"entity_id1 = '" + entity_id1 + "',  \n" + // 8
				"entity_id2 = '" + entity_id2 + "',  \n" + // 9
				"entity_type_id = '" + ent_type_id + "',  \n" + // 10
				"sd_no = " + sd_no + ",  \n" + // 11
				"doc_id = '" + doc_id + "' , \n" + // 12
				"proc_id = " + proc_id + ",  \n" + // 13
				"branch_id = " + branch_id + " , \n" + // 14
				"justification = '" + justif + "',  \n" + // 15
				"remarks = '" + remarks + "' , \n" + // 16
				"status_id = '" + status_id + "' , \n" + // 17
				"edited_by = '" + edited_by + "' , \n" + // 20
				"date_edited = now(), \n" + // 21
				"pay_type_id = '" + pay_type + "' \n" + // 21
				"where drf_no = '" + drf_no + "' and co_id = '" + co_id + "'   \n";

		System.out.printf("SQL #HEADER: %s", sqlDetail);
		db.executeUpdate(sqlDetail, false);

	}

	public void updateRPLF_header_status(String drf_no, pgUpdate db, String status) {

		String sqlDetail = "update rf_request_header set " + "status_id = '" + status + "',  \n" + "edited_by = '"
				+ UserInfo.EmployeeCode + "' , \n" + // 20
				"date_edited = now() \n" + // 21
				"where drf_no = '" + drf_no + "' and co_id = '" + co_id + "'   ";

		System.out.printf("SQL-updateRPLF_header_status: %s", sqlDetail);
		db.executeUpdate(sqlDetail, false);

	}

	// set values of variables
	private void setColumnIfVatableEntity() {

		int rw = tblDRF_part.getRowCount();
		int x = 0;

		while (x < rw) {

			modelDRF_part.setValueAt(is_payee_vatable, x, 21);
			if (is_payee_vatable == true) {
				modelDRF_part.setValueAt(new BigDecimal(12.0), x, 25);
			} else {
				modelDRF_part.setValueAt(new BigDecimal(0.00), x, 25);
			}

			x++;

		}
	}

	private void setColumnNonTaxable() {

		int rw = tblDRF_part.getRowCount();
		int x = 0;

		while (x < rw) {

			modelDRF_part.setValueAt(new BigDecimal(0.00), x, 28);
			x++;

		}
	}

	private void setColumnTaxable() {

		int rw = tblDRF_part.getRowCount();
		int x = 0;

		while (x < rw) {

			modelDRF_part.setValueAt(new BigDecimal(tax_rate), x, 28);

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
				modelDRF_part.setValueAt(div, x, 2);
				modelDRF_part.setValueAt(payee_id, x, 7);
				modelDRF_part.setValueAt(payee_type, x, 8);
				modelDRF_part.setValueAt(payee_name, x, 9);
				modelDRF_part.setValueAt(tax_id, x, 27);
				modelDRF_part.setValueAt(tax_rate, x, 28);

				x++;
			}

			tblDRF_part.getColumnModel().getColumn(10).setPreferredWidth(80);
		}

	}

	public void editAmount() {

		int c = tblDRF_part.getSelectedColumn();
		int r = tblDRF_part.getSelectedRow();
		double wtax_orig_amt = Double.parseDouble(modelDRF_part.getValueAt(r, c).toString());
		double pay_orig_amt = Double.parseDouble(modelDRF_part.getValueAt(r, c + 7).toString());

		txteditamount.setValue(wtax_orig_amt);

		int scanOption = JOptionPane.showOptionDialog(getContentPane(), pnlEditAmount, "Edit Amount",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, new Object[] {}, null);

		if (scanOption == JOptionPane.CLOSED_OPTION) {
			try {
				double wtax_new_amt = Double.valueOf(txteditamount.getText().trim().replace(",", "")).doubleValue();
				double exp_new_amt = pay_orig_amt + wtax_orig_amt - wtax_new_amt;
				BigDecimal wtax_new_amt_bd = new BigDecimal(wtax_new_amt);
				BigDecimal pay_new_amt_bd = new BigDecimal(exp_new_amt);
				modelDRF_part.setValueAt(wtax_new_amt_bd, r, c);
				modelDRF_part.setValueAt(pay_new_amt_bd, r, c + 7);
				totalDRF(modelDRF_part, modelDRF_part_total);
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			}
		} // CLOSED_OPTION
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

	public static void openPV() {

		if (Home.Home_ArcSystem.isNotExisting("PayableVoucher")) {
			PayableVoucher pv = new PayableVoucher();
			Home.Home_ArcSystem.addWindow(pv);
		}

		if (sql_getPV(lookupDRF_no.getText().trim()).equals("")
				|| sql_getPV(lookupDRF_no.getText().trim()).equals(null)) {
			{
				JOptionPane.showMessageDialog(null, "This request has no Payable Voucher yet.", "Warning",
						JOptionPane.WARNING_MESSAGE);
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
		mnipaste.setEnabled(true);
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
			JOptionPane.showMessageDialog(getContentPane(),
					"This request has been inactive. Write-off is not applicable.", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}

		else if (txtStatus.getText().trim().equals("DELETED")) {
			JOptionPane.showMessageDialog(getContentPane(),
					"This request has been deleted. Write-off is not applicable.", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}

		else {

			if (isRPLFalreadywroteoff() == true) {
				JOptionPane.showMessageDialog(getContentPane(),
						"This request has been written off. Re-writeoff is not applicable.", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {

				if (isCApaidout() == false) {
					JOptionPane.showMessageDialog(getContentPane(),
							"This request is not yet paid out. Write-off is not applicable.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} else {
					if (isCAfullyliquidated() == true) {
						JOptionPane.showMessageDialog(getContentPane(),
								"This request is already liquidated. Write-off is not applicable.", "Warning",
								JOptionPane.WARNING_MESSAGE);
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
	private void generateDetail(String lineno) {
		Object[] listCode = new Object[5];
		int row = tblDRF_part.convertRowIndexToModel(tblDRF_part.getSelectedRow());

		listCode[0] = modelDRF_part.getValueAt(row, 2).equals(null) ? null : modelDRF_part.getValueAt(row, 35);
		listCode[1] = modelDRF_part.getValueAt(row, 3).equals(null) ? null : modelDRF_part.getValueAt(row, 36);
		listCode[2] = modelDRF_part.getValueAt(row, 5).equals(null) ? null : modelDRF_part.getValueAt(row, 37);
		listCode[3] = modelDRF_part.getValueAt(row, 6).equals(null) ? null : modelDRF_part.getValueAt(row, 38);
		listCode[4] = modelDRF_part.getValueAt(row, 10).equals(null) ? null : modelDRF_part.getValueAt(row, 39);

		Object[] newListCode = new Object[5];
		newListCode[0] = listCode[0];
		newListCode[1] = listCode[1];
		newListCode[2] = listCode[2];
		newListCode[3] = listCode[3];
		newListCode[4] = listCode[4];

		tagDetail.setText(String.format("[ Div: %s - Dep: %s - Proj: %s - Sub: %s - Availer: %s ]", newListCode));
	}

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
		btnRefresh.setEnabled(bRef);
		btnCancel.setEnabled(bCancel);	
	}

	private static Date getDueDate(){
		pgSelect db = new pgSelect();
		db.select("SELECT CURRENT_DATE + INTERVAL '3 days'; ");
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
	
}