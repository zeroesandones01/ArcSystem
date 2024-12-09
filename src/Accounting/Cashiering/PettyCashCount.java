package Accounting.Cashiering;

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
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import Database.pgSelect;
import Database.pgUpdate;
import FormattedTextField._JXFormattedTextField;
import Functions.FncGlobal;
import Functions.FncReport;
import Functions.FncTables;
import Functions.UserInfo;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import components._JInternalFrame;
import components._JScrollPaneMain;
import components._JScrollPaneTotal;
import components._JTableMain;
import components._JTableTotal;
import interfaces._GUI;
import tablemodel.modelPettyCashCount;


/**
 * @author Monique Boriga
 */

public class PettyCashCount extends _JInternalFrame implements _GUI, ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private static String title = "Petty Cash Count";
	static Dimension SIZE = new Dimension(1000, 600);
	Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); 
	Border raisedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY); 
	Border customBorder = BorderFactory.createCompoundBorder(raisedBorder, emptyBorder); 
	protected static Font dialog12Bold = new Font("DIALOG", Font.BOLD, 12);
	static NumberFormat nf = new DecimalFormat("###,###,###,##0.00"); 

	private JPanel pnlMain;
	private JPanel pnlNorth;
	private JPanel pnlCenter;
	private JPanel pnlCashCount;
	private JPanel pnlCashFundSummary;
	private JPanel pnlSouth;

	private JButton btnAddNew;
	private JButton btnEdit;
	private JButton btnSave;
	private JButton btnPreview;
	private JButton btnCancel;
	private JButton btnPost;
	private _JLookup lookupCompany;
	private JDateChooser dtePCCDate;
	private JTextField txtStatus;
	private _JLookup lookupPCC_No;
	private _JScrollPaneMain scrollCashCount;
	private modelPettyCashCount modelCashCount;
	private _JTableMain tblCashCount;
	private JList rowHeaderCashCount;
	private _JScrollPaneTotal scrollCashCountTotal;
	private modelPettyCashCount modelCashCountTotal;
	private _JTableTotal tblCashCountTotal;
	private static _JXFormattedTextField ftxtCashReturned;
	private static _JXFormattedTextField ftxtCashAdvance;
	private static _JXFormattedTextField ftxtReimbursement;
	private static _JXFormattedTextField ftxtAddtlCashReleased;
	private static _JXFormattedTextField ftxtBegBalance;
	private static _JXFormattedTextField ftxtTotalCashOnHand;
	private static _JXFormattedTextField ftxtCOHShouldBe;
	private static _JXFormattedTextField ftxtCOHCounted;
	private static _JXFormattedTextField ftxtShortOverage;
	private String co_id;
	private String pcc_status;
	private String user;
	private JPanel pnlInputs;
	private Object company_logo;
	private static _JXFormattedTextField ftxtCashInToday;
	private static _JXFormattedTextField ftxtCashReleasedToday;
	private static _JXFormattedTextField ftxtCheckEncashment; 

	public PettyCashCount() {
		super(title, true, true, true, true); 
		initGUI();

	}

	@Override
	public void initGUI() {
		this.setSize(SIZE);

		pnlMain = new JPanel(new BorderLayout(5, 5)); 
		getContentPane().add(pnlMain, BorderLayout.CENTER); 
		pnlMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		{
			pnlNorth = new JPanel(new GridLayout(1, 2, 5, 5)); 
			pnlMain.add(pnlNorth, BorderLayout.NORTH);
			pnlNorth.setPreferredSize(new Dimension(0, 60));
			{
				JPanel pnlNorthwest = new JPanel(new BorderLayout(5, 5)); 
				pnlNorth.add(pnlNorthwest); 
				{
					JPanel pnlNWLabels = new JPanel(new GridLayout(2, 1, 5, 5)); 
					pnlNorthwest.add(pnlNWLabels, BorderLayout.WEST);
					pnlNWLabels.setPreferredSize(new Dimension(80, 0));
					{
						JLabel lblCompany = new JLabel("Company: ", JLabel.TRAILING); 
						pnlNWLabels.add(lblCompany); 
					}
					{
						JLabel lblPCCDate = new JLabel("Date: ", JLabel.TRAILING);
						pnlNWLabels.add(lblPCCDate);
					}

				}
				{
					JPanel pnlNWComponents = new JPanel(new GridLayout(2, 1, 5, 5)); 
					pnlNorthwest.add(pnlNWComponents, BorderLayout.CENTER);
					{
						lookupCompany = new _JLookup(null, "Company", 1);
						pnlNWComponents.add(lookupCompany); 
						lookupCompany.setFilterName(true);
						lookupCompany.setLookupSQL(FncGlobal.getCompany());
						lookupCompany.addLookupListener(new LookupListener() {

							@Override
							public void lookupPerformed(LookupEvent event) {
								// TODO Auto-generated method stub

							}
						});
					}
					{
						dtePCCDate = new JDateChooser("MM/dd/yy", "##/##/##", '_');
						pnlNWComponents.add(dtePCCDate); 
						dtePCCDate.setMaxSelectableDate(FncGlobal.getDateToday()); 
						dtePCCDate.setDate(FncGlobal.getDateToday());
					}
				}
				{
					JPanel pnlExtra = new JPanel(); 
					pnlNorthwest.add(pnlExtra, BorderLayout.EAST);
					pnlExtra.setPreferredSize(new Dimension(150, 0)); 
				}
			}
			{
				JPanel pnlNorthCenter = new JPanel(new BorderLayout(5, 5)); 
				pnlNorth.add(pnlNorthCenter); 
				{
					JPanel pnlNCLabels = new JPanel(new GridLayout(2, 1, 5, 5)); 
					pnlNorthCenter.add(pnlNCLabels, BorderLayout.WEST); 
					pnlNCLabels.setPreferredSize(new Dimension(100, 0)); 
					{
						JLabel lblStatus = new JLabel("Status: ", JLabel.TRAILING); 
						pnlNCLabels.add(lblStatus); 
					}
					{
						JLabel lblPCCNo = new JLabel("Cash Count No.: ", JLabel.TRAILING); 
						pnlNCLabels.add(lblPCCNo); 
					}

				}
				{
					JPanel pnlNCCompnents = new JPanel(new GridLayout(2, 1, 5, 5)); 
					pnlNorthCenter.add(pnlNCCompnents); 
					{
						txtStatus = new JTextField(); 
						pnlNCCompnents.add(txtStatus);
						txtStatus.setEditable(false); 
						txtStatus.setHorizontalAlignment(JTextField.CENTER);
					}
					{
						lookupPCC_No = new _JLookup(null, "Petty Cash Count No.", 0); 
						pnlNCCompnents.add(lookupPCC_No);
						lookupPCC_No.setFont(new Font("Segoe UI", Font.BOLD, 12));
						lookupPCC_No.addLookupListener(new LookupListener() {

							private String pcc_no;

							@Override
							public void lookupPerformed(LookupEvent event) {
								Object [] data = ((_JLookup)event.getSource()).getDataSet();

								if (data != null) {
									pcc_no = (String) data[0]; 
									pcc_status = (String) data[2]; 
								}

								createCashCountList(modelCashCount, rowHeaderCashCount, modelCashCountTotal);
								displayCashCount(pcc_no);
								displayCashFundSummary(pcc_no);
								setComponentsEnabled(pnlNorth, true);
								setCompEditable(pnlNorth, false);
								setCompEditable(pnlCashCount, false);
								setCompEditable(pnlCashFundSummary, false);
								setComponentsEnabled(pnlCashCount, true);
								setComponentsEnabled(pnlCashFundSummary, true);

								lookupPCC_No.setEnabled(true);
								lookupPCC_No.setEditable(true);

								if(pcc_status.equals("ACTIVE")) {
									btnState(false, true, false, true, true, true);
								} else {
									btnState(false, false, false, true, true, false); 
								}		
							}
						});

					}
				}
				{
					JPanel pnlExtra = new JPanel(); 
					pnlNorthCenter.add(pnlExtra, BorderLayout.EAST);
					pnlExtra.setPreferredSize(new Dimension(150, 0)); 
				}
			}
		}
		{
			pnlCenter = new JPanel(new GridLayout(1, 2, 5, 5));
			pnlMain.add(pnlCenter, BorderLayout.CENTER); 
			{
				pnlCashCount = new JPanel(new BorderLayout(5, 5)); 
				pnlCenter.add(pnlCashCount); 
				pnlCashCount.setBorder(customBorder);
				{
					JLabel lblCashCount = new JLabel("A. Cash Count"); 
					pnlCashCount.add(lblCashCount, BorderLayout.NORTH); 
					lblCashCount.setPreferredSize(new Dimension(0, 30));
					lblCashCount.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
				}
				{
					scrollCashCount = new _JScrollPaneMain(); 
					pnlCashCount.add(scrollCashCount, BorderLayout.CENTER);
					{
						modelCashCount = new modelPettyCashCount(); 
						tblCashCount = new _JTableMain(modelCashCount); 
						scrollCashCount.setViewportView(tblCashCount); 
						tblCashCount.setSortable(false); 
						tblCashCount.addKeyListener(this); 

						// Set editor for the "Count" column
						JTextField countEditor = new JTextField();
						countEditor.addFocusListener(new java.awt.event.FocusAdapter() {
							@Override
							public void focusGained(java.awt.event.FocusEvent e) {
								if (countEditor.getText().equals("0")) {
									countEditor.setText(""); // Clear default value
								}
								countEditor.setCaretPosition(countEditor.getText().length()); // Move caret to the end
							}
						});

						tblCashCount.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(countEditor));

						{
							rowHeaderCashCount = tblCashCount.getRowHeader();
							scrollCashCount.setRowHeaderView(rowHeaderCashCount);
							scrollCashCount.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
							scrollCashCount.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(modelCashCount.getRowCount())));
						}
					}
					{
						scrollCashCountTotal = new _JScrollPaneTotal(scrollCashCount);
						pnlCashCount.add(scrollCashCountTotal, BorderLayout.SOUTH);
						{
							modelCashCountTotal = new modelPettyCashCount();
							modelCashCountTotal.addRow(new Object[] { "Cash on Hand", null,  new BigDecimal(0.00) });

							tblCashCountTotal = new _JTableTotal(modelCashCountTotal, tblCashCount);
							scrollCashCountTotal.setViewportView(tblCashCountTotal);
							tblCashCountTotal.setFont(dialog12Bold);
						}
					}
				}
			}
			{
				pnlCashFundSummary = new JPanel(new BorderLayout(5, 5)); 
				pnlCenter.add(pnlCashFundSummary); 
				pnlCashFundSummary.setBorder(customBorder);

				{
					JLabel lblCashFundSummary = new JLabel("B. Petty Cash Fund Summary"); 
					pnlCashFundSummary.add(lblCashFundSummary, BorderLayout.NORTH); 
					lblCashFundSummary.setPreferredSize(new Dimension(0, 30));
					lblCashFundSummary.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
				}
				{
					JPanel pnlCFSumCenter = new JPanel(new BorderLayout(5, 5)); 
					pnlCashFundSummary.add(pnlCFSumCenter, BorderLayout.CENTER); 
					{
						JPanel pnlLabels = new JPanel(new GridLayout(12, 1, 5, 5)); 
						pnlCFSumCenter.add(pnlLabels, BorderLayout.WEST); 
						pnlLabels.setPreferredSize(new Dimension(250, 0)); 
						pnlLabels.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
						{
							JLabel lblBegBal = new JLabel("Beginning Balance"); 
							pnlLabels.add(lblBegBal); 
						}
						{
							JLabel lblCashIn = new JLabel("Add: Cash In Today"); 
							pnlLabels.add(lblCashIn); 
						}
						{
							JLabel lblCashReturned = new JLabel("     Cash Returned"); 
							pnlLabels.add(lblCashReturned); 
						}
						{
							JLabel lblCheckEncashment = new JLabel("     Check Encashment"); 
							pnlLabels.add(lblCheckEncashment); 
						}
						{
							JLabel lblTotCashOnHand = new JLabel("Total Cash on Hand For the Day"); 
							pnlLabels.add(lblTotCashOnHand); 
							lblTotCashOnHand.setFont(new Font("Segoe UI", Font.BOLD, 12)); 

						}
						{
							JLabel lblCashReleased = new JLabel("Less: Cash Released Today"); 
							pnlLabels.add(lblCashReleased); 
						}
						{
							JLabel lblCashAdvance = new JLabel("     Cash Advance(s)"); 
							pnlLabels.add(lblCashAdvance); 
						}
						{
							JLabel lblReimbursement = new JLabel("     Reimbursement(s)"); 
							pnlLabels.add(lblReimbursement); 
						}
						{
							JLabel lblAddtlCashRel = new JLabel("     Addt'l Cash Released"); 
							pnlLabels.add(lblAddtlCashRel); 
						}
						{
							JLabel lblCOHShouldBe = new JLabel("Cash on Hand - Should Be"); 
							pnlLabels.add(lblCOHShouldBe); 
							lblCOHShouldBe.setFont(new Font("Segoe UI", Font.BOLD, 12)); 
						}
						{
							JLabel lblShortOverage = new JLabel("(Shortage) / Overage"); 
							pnlLabels.add(lblShortOverage); 
						}
						{
							JLabel lblCOHCounted = new JLabel("Cash on Hand Counted"); 
							pnlLabels.add(lblCOHCounted); 
							lblCOHCounted.setFont(new Font("Segoe UI", Font.BOLD, 12)); 

						}

					}
					{
						pnlInputs = new JPanel(new GridLayout(12, 1, 5, 5)); 
						pnlCFSumCenter.add(pnlInputs, BorderLayout.CENTER); 
						pnlInputs.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

						{
							pnlInputs.add(Box.createHorizontalBox()); 
							pnlInputs.add(Box.createHorizontalBox()); 
						}
						{
							ftxtCashReturned = new _JXFormattedTextField(_JXFormattedTextField.CENTER);
							pnlInputs.add(ftxtCashReturned); 
							ftxtCashReturned.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							ftxtCashReturned.addKeyListener(new KeyAdapter() {
								public void keyReleased(KeyEvent e) {
									try{
										updateCashInToday(); 
										updateTotalCashReleasedToday();
									} catch(NumberFormatException a) {}
								}

								public void keyPressed(KeyEvent e) {
									if (e.getKeyCode() == KeyEvent.VK_ENTER) {
										ftxtCheckEncashment.requestFocus();
									}
								}
							}); 
						}
						{
							ftxtCheckEncashment = new _JXFormattedTextField(_JXFormattedTextField.CENTER);
							pnlInputs.add(ftxtCheckEncashment); 
							ftxtCheckEncashment.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							ftxtCheckEncashment.addKeyListener(new KeyAdapter() {
								public void keyReleased(KeyEvent e) {
									try{
										updateCashInToday(); 
										updateTotalCashReleasedToday();
									} catch(NumberFormatException a) {}
								}

								public void keyPressed(KeyEvent e) {
									if (e.getKeyCode() == KeyEvent.VK_ENTER) {
										ftxtCashAdvance.requestFocus();
									}
								}
							}); 
						}
						{
							pnlInputs.add(Box.createHorizontalBox()); 
							pnlInputs.add(Box.createHorizontalBox()); 
						}
						{
							ftxtCashAdvance = new _JXFormattedTextField(_JXFormattedTextField.CENTER);
							pnlInputs.add(ftxtCashAdvance); 
							ftxtCashAdvance.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							ftxtCashAdvance.addKeyListener(new KeyAdapter() {
								public void keyReleased(KeyEvent e) {
									try{
										updateTotalCashReleasedToday();; 
									} catch(NumberFormatException a) {}
								}

								public void keyPressed(KeyEvent e) {
									if (e.getKeyCode() == KeyEvent.VK_ENTER) {
										ftxtReimbursement.requestFocus();
									}
								}
							}); 
						}
						{
							ftxtReimbursement = new _JXFormattedTextField(_JXFormattedTextField.CENTER);
							pnlInputs.add(ftxtReimbursement); 
							ftxtReimbursement.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							ftxtReimbursement.addKeyListener(new KeyAdapter() {
								public void keyReleased(KeyEvent e) {
									try{
										updateTotalCashReleasedToday();; 
									} catch(NumberFormatException a) {}
								}

								public void keyPressed(KeyEvent e) {
									if (e.getKeyCode() == KeyEvent.VK_ENTER) {
										ftxtAddtlCashReleased.requestFocus();
									}
								}
							}); 
						}
						{
							ftxtAddtlCashReleased = new _JXFormattedTextField(_JXFormattedTextField.CENTER);
							pnlInputs.add(ftxtAddtlCashReleased); 
							ftxtAddtlCashReleased.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							ftxtAddtlCashReleased.addKeyListener(new KeyAdapter() {
								public void keyReleased(KeyEvent e) {
									try{
										updateTotalCashReleasedToday();; 
									} catch(NumberFormatException a) {}
								}

							}); 
						}
						{
							pnlInputs.add(Box.createHorizontalBox()); 

							ftxtShortOverage = new _JXFormattedTextField(_JXFormattedTextField.CENTER);
							pnlInputs.add(ftxtShortOverage); 
							ftxtShortOverage.setFormatterFactory(_JXFormattedTextField.DECIMAL); 
							ftxtShortOverage.setEditable(false);

						}
						{
							pnlInputs.add(Box.createHorizontalBox()); 
						}
					}
					{
						JPanel pnlAmounts = new JPanel(new GridLayout(12, 1, 5, 5)); 
						pnlCFSumCenter.add(pnlAmounts, BorderLayout.EAST); 
						pnlAmounts.setPreferredSize(new Dimension(100, 0)); 
						{
							ftxtBegBalance = new _JXFormattedTextField(_JXFormattedTextField.CENTER);
							pnlAmounts.add(ftxtBegBalance); 
							ftxtBegBalance.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							ftxtBegBalance.setEditable(false);
							ftxtBegBalance.setFont(dialog12Bold);
						}
						{
							ftxtCashInToday = new _JXFormattedTextField(_JXFormattedTextField.CENTER); 
							pnlAmounts.add(ftxtCashInToday); 
							ftxtCashInToday.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							ftxtCashInToday.setEditable(false);
							ftxtCashInToday.setFont(dialog12Bold);
						}
						{
							pnlAmounts.add(Box.createHorizontalBox()); 
							pnlAmounts.add(Box.createHorizontalBox()); 
						}
						{
							ftxtTotalCashOnHand = new _JXFormattedTextField(_JXFormattedTextField.CENTER);
							pnlAmounts.add(ftxtTotalCashOnHand); 
							ftxtTotalCashOnHand.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							ftxtTotalCashOnHand.setEditable(false); 
							ftxtTotalCashOnHand.setFont(dialog12Bold);
						}
						{
							ftxtCashReleasedToday = new _JXFormattedTextField(_JXFormattedTextField.CENTER);
							pnlAmounts.add(ftxtCashReleasedToday); 
							ftxtCashReleasedToday.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							ftxtCashReleasedToday.setEditable(false); 
							ftxtCashReleasedToday.setFont(dialog12Bold);
						}
						{
							pnlAmounts.add(Box.createHorizontalBox()); 
							pnlAmounts.add(Box.createHorizontalBox()); 
							pnlAmounts.add(Box.createHorizontalBox()); 
						}
						{
							ftxtCOHShouldBe = new _JXFormattedTextField(_JXFormattedTextField.CENTER);
							pnlAmounts.add(ftxtCOHShouldBe); 
							ftxtCOHShouldBe.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							ftxtCOHShouldBe.setEditable(false); 
							ftxtCOHShouldBe.setFont(dialog12Bold);
						}
						{
							pnlAmounts.add(Box.createHorizontalBox()); 
						}
						{
							ftxtCOHCounted = new _JXFormattedTextField(_JXFormattedTextField.CENTER);
							pnlAmounts.add(ftxtCOHCounted); 
							ftxtCOHCounted.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							ftxtCOHCounted.setEditable(false);
							ftxtCOHCounted.setFont(dialog12Bold);
						}
					}
				}
			}

		}
		{
			pnlSouth = new JPanel(new GridLayout(1, 6, 5, 5)); 
			pnlMain.add(pnlSouth, BorderLayout.SOUTH); 
			pnlSouth.setPreferredSize(new Dimension(0, 30));
			{
				btnAddNew = new JButton("Add New"); 
				pnlSouth.add(btnAddNew); 
				btnAddNew.setActionCommand(btnAddNew.getText());
				btnAddNew.addActionListener(this);
			}
			{
				btnEdit = new JButton("Edit");
				pnlSouth.add(btnEdit);
				btnEdit.setActionCommand(btnEdit.getText());
				btnEdit.addActionListener(this);
			}
			{
				btnSave = new JButton("Save");
				pnlSouth.add(btnSave);
				btnSave.setActionCommand(btnSave.getText());
				btnSave.addActionListener(this);
			}
			{
				btnPreview = new JButton("Preview");
				pnlSouth.add(btnPreview);
				btnPreview.setActionCommand(btnPreview.getText());
				btnPreview.addActionListener(this);
			}
			{
				btnCancel = new JButton("Cancel");
				pnlSouth.add(btnCancel);
				btnCancel.setActionCommand(btnCancel.getText());
				btnCancel.addActionListener(this);
			}
			{
				btnPost = new JButton("Post");
				pnlSouth.add(btnPost);
				btnPost.setActionCommand(btnPost.getText());
				btnPost.addActionListener(this);
			}

			initializeComponents(); 
		}

	} // XXX END OF INIT GUI 


	private void initializeComponents() {
		//DEFAULT VALUE OF COMPANY
		co_id = "01"; 
		company_logo = getCompanyLogo(co_id);
		lookupCompany.setValue("ACERLAND REALTY CORPORATION");
		user = UserInfo.EmployeeCode;
		dtePCCDate.setEnabled(false);
		dtePCCDate.getCalendarButton().setVisible(false); 
		txtStatus.setEnabled(false);
		FncTables.clearTable(modelCashCount);
		FncTables.clearTable(modelCashCountTotal);	
		setCompDisabled(pnlCashCount);
		setCompDisabled(pnlCashFundSummary);
		lookupPCC_No.setEnabled(true);
		lookupPCC_No.setEditable(true);
		lookupPCC_No.setLookupSQL(getPCC_no(co_id));
		btnState(true, false, false, false, false, false);
	
		System.out.println("Cash Count No.: " + lookupPCC_No.getText());
		System.out.println(""); 
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Add New")) {
			
			if(pendingPettyCashCount() != null) {
				showWarningMessage("Petty Cash Count No. " +pendingPettyCashCount()+ " is not yet posted. \n Please post it before creating a new one.", "Add New");
				return;
			} else {
				setFTxtDefaultValue();
				createCashCountList(modelCashCount, rowHeaderCashCount, modelCashCountTotal);
				setComponentsEnabled(pnlNorth, true);
				lookupPCC_No.setEnabled(false);
				lookupCompany.setEditable(false);
				dtePCCDate.getCalendarButton().setVisible(true);
				txtStatus.setText("ACTIVE");
				pcc_status = "A";
				setCompEnabled(pnlCashCount);
				tblCashCount.setEditable(true);
				modelCashCount.setEditable(true);
				setCompEnabled(pnlCashFundSummary);
				setComponentsEditable(pnlInputs, true);
				ftxtShortOverage.setEditable(false);
				btnState(false, false, true, false, true, false);
				updateCashInToday(); 
				updateTotalCashReleasedToday();
			}
			
		}

		if(e.getActionCommand().equals("Save")) {

			// Validate value of Total Amount of Petty Cash Count 
			String strTotalCashCount = modelCashCountTotal.getValueAt(0, 2).toString().trim().replace(",",""); 
			BigDecimal totalCashCount = new BigDecimal(strTotalCashCount); 

			if(totalCashCount.compareTo(BigDecimal.ZERO) <= 0) {
				showWarningMessage("Please input cash count denomination(s).", "Cash Count");
				return;
			} 
			
		    // Check if cash count discrepancy exists (Short or Over)
		    if (isShortOrOver(totalCashCount)) {
		        // If there's a discrepancy and user declines to proceed, stop the process
		        return;
		    }

		    // Proceed with saving if no discrepancy or user confirms all details are correct
		    if (JOptionPane.showConfirmDialog(getContentPane(), "Are all details correct?", "Confirmation",
		            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
		        executeSave(totalCashCount);
		    }
		    
		}

		if(e.getActionCommand().equals("Edit")) {
			lookupPCC_No.setEditable(false);
			setCompEditable(pnlCashCount, true);
			setComponentsEditable(pnlInputs, true);
			ftxtShortOverage.setEditable(false);
			modelCashCount.setEditable(true);
			btnState(false, false, true, false, true, false);
		}

		if(e.getActionCommand().equals("Cancel")) {
			lookupPCC_No.clearLookup();
			txtStatus.setText("");
			this.setComponentsClear(pnlCashCount);
			this.setComponentsClear(pnlCashFundSummary);
			setFTxtDefaultValue();
			initializeComponents();
		}

		if(e.getActionCommand().equals("Post")) {
			post(lookupPCC_No.getText().trim()); 
		}
		
		if(e.getActionCommand().equals("Preview")) {
			preview(); 
		}

	}

	private void btnState(Boolean add, Boolean edit, Boolean save, Boolean preview, Boolean cancel, Boolean post) {
		btnAddNew.setEnabled(add);
		btnEdit.setEnabled(edit);
		btnSave.setEnabled(save);
		btnPreview.setEnabled(preview);
		btnCancel.setEnabled(cancel);
		btnPost.setEnabled(post);

	}

	private void createCashCountList(DefaultTableModel modelMain, JList<Integer> rowHeader, DefaultTableModel modelTotal) {
		FncTables.clearTable(modelMain); 		
		DefaultListModel<Integer> listModel = new DefaultListModel<Integer>(); 
		rowHeader.setModel(listModel); 

		String sql = "Select * from view_cashcount_denomination;";

		pgSelect db = new pgSelect();
		db.select(sql);
		if(db.isNotNull()){
			for(int x=0; x < db.getRowCount(); x++){
				modelMain.addRow(db.getResult()[x]);
				listModel.addElement(modelMain.getRowCount());				
			}	
			totalCashCount(modelMain, modelTotal);			
		}		

		tblCashCount.getColumnModel().getColumn(0).setPreferredWidth(200);
		tblCashCount.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblCashCount.getColumnModel().getColumn(2).setPreferredWidth(100);
		adjustRowHeight(tblCashCount);
	}


	private void totalCashCount(DefaultTableModel modelMain, DefaultTableModel modelTotal) {
		BigDecimal total_cash_amt = new BigDecimal(0.00);
		FncTables.clearTable(modelTotal);

		System.out.println("");

		for (int x=0; x < modelMain.getRowCount(); x++) {
			try {
				total_cash_amt = total_cash_amt.add(((BigDecimal) modelMain.getValueAt(x, 2)));
			} catch (NullPointerException e) {
				total_cash_amt = total_cash_amt.add(new BigDecimal(0.00));
			}

			System.out.println("Row: "+total_cash_amt);
		}

		System.out.println("total_cash_amt: "+total_cash_amt);
		System.out.println(""); 

		modelTotal.addRow(new Object[] { "Cash on Hand" , null, nf.format(total_cash_amt)});

		// Set the value of Total Cash on Hand Counted at Cash Fund Summary
		ftxtCOHCounted.setValue(total_cash_amt);
	}

	public static void updateCashInToday() {
		Double beg_bal = Double.valueOf(ftxtBegBalance.getText().trim().replace(",","")).doubleValue();
		Double cash_ret = Double.valueOf(ftxtCashReturned.getText().trim().replace(",","")).doubleValue();
		Double check_encash = Double.valueOf(ftxtCheckEncashment.getText().trim().replace(",","")).doubleValue();

		try { 
//			System.out.println("Cash Returned: " + cash_ret);
//			System.out.println("Check Encashment: " + check_encash);
//			System.out.println(""); 

			// Calculate the sum
			Double sum = (cash_ret + check_encash); 
//			System.out.println("Cash on Hand for the day: " + sum);
//			System.out.println(""); 

			// Display the sum in the result field
			ftxtCashInToday.setText(nf.format(sum));  

			// Calculate Total Cash On Hand For the Day
			Double total_coh_for_the_day = (beg_bal+ sum);  
//			System.out.println("Total Cash On hand: " + total_coh_for_the_day);
//			System.out.println(""); 

			// Display the value of Total Cash On Hand For the Day
			ftxtTotalCashOnHand.setText(nf.format(total_coh_for_the_day));

		} catch (Exception e) {
			ftxtCashInToday.setValue(BigDecimal.ZERO);  // In case of any error, set sum to 0
		}
	}

	public static void updateTotalCashReleasedToday() {

		Double total_cash_on_hand = Double.valueOf(ftxtTotalCashOnHand.getText().trim().replace(",","")).doubleValue();
		Double cash_adv = Double.valueOf(ftxtCashAdvance.getText().trim().replace(",","")).doubleValue();
		Double reimbursement = Double.valueOf(ftxtReimbursement.getText().trim().replace(",","")).doubleValue();
		Double addtl_cash_rel = Double.valueOf(ftxtAddtlCashReleased.getText().trim().replace(",","")).doubleValue();

		try { 
//			System.out.println("Cash Advance(s): " + cash_adv);
//			System.out.println("Reimbursement(s): " + reimbursement);
//			System.out.println("Addt'l Cash Released: " + addtl_cash_rel);
//			System.out.println(""); 

			// Calculate the sum
			Double sum = (cash_adv + reimbursement + addtl_cash_rel); 
//			System.out.println("Total Cash Released Today: " + sum);
//			System.out.println(""); 

			// Display the sum in the result field
			ftxtCashReleasedToday.setText(nf.format(sum));  

			// Calculate value of Cash on hand - Should be
			Double total_coh_should_be = (total_cash_on_hand - sum);  
//			System.out.println("Total Cash On hand - Should Be: " + total_coh_should_be);
//			System.out.println(""); 

			// Display  Cash on hand - Should be in the result field
			ftxtCOHShouldBe.setText(nf.format(total_coh_should_be));

			// Calculate Shortage or Overage
			updateShortageOrOverage(); 

		} catch (Exception e) {
			ftxtCashReleasedToday.setValue(BigDecimal.ZERO);  // In case of any error, set sum to 0
		}
	}

	public static void updateShortageOrOverage() {
		Double cashOnHand_should_be = Double.valueOf(ftxtCOHShouldBe.getText().trim().replace(",","")).doubleValue();
		Double cashOnHand_counted = Double.valueOf(ftxtCOHCounted.getText().trim().replace(",","")).doubleValue();

		try { 
//			System.out.println("Cash On hand - Should Be: " + cashOnHand_should_be);
//			System.out.println("Cash On hand Counted: " + cashOnHand_counted);
//			System.out.println(""); 

			// Calculate the value of Shortage / Overage
			Double shortageOrOverage = (cashOnHand_counted - cashOnHand_should_be); 
//			System.out.println("Total Shortage Or Overage: " + shortageOrOverage);
//			System.out.println(""); 

			// Format the value and check if it's negative
			String formattedValue;
			if (shortageOrOverage < 0) {
				// For negative values, format inside parentheses
				formattedValue = "(" + nf.format(Math.abs(shortageOrOverage)) + ")";
			} else {
				// Positive values
				formattedValue = nf.format(shortageOrOverage);
			}

			// Display the value in the text field
			ftxtShortOverage.setValue(formattedValue);


		} catch (Exception e) {
			ftxtShortOverage.setValue(BigDecimal.ZERO);  // In case of any error, set sum to 0
		}
	}

	public void executeSave(BigDecimal total_cash_count) {

		String pcc_no = lookupPCC_No.getText().trim();

		// SAVE NEW PETTY CASH COUNT
		if(pcc_no.equals("")) {

			pcc_no = savePettyCashCount(pcc_no, total_cash_count); 
			JOptionPane.showMessageDialog(getContentPane(), "Petty Cash Count No. " + pcc_no + " was successfully saved."  , "Information",
					JOptionPane.INFORMATION_MESSAGE);
		} 

	
		else { // SAVE FROM EDIT

			savePettyCashCount(pcc_no, total_cash_count); 
			JOptionPane.showMessageDialog(getContentPane(), "Petty Cash Count No. " + pcc_no + " was successfully updated."  , "Information",
					JOptionPane.INFORMATION_MESSAGE);
		}

		lookupPCC_No.setValue(pcc_no);
		lookupPCC_No.setEnabled(true);
		lookupPCC_No.setEditable(true);
		modelCashCount.setEditable(false);
		displayCashCount(pcc_no);
		btnState(false, true, false, true, true, true);
		setCompEditable(pnlCashCount, false);
		setCompEditable(pnlCashFundSummary, false);
		dtePCCDate.getCalendarButton().setEnabled(false);
		dtePCCDate.setEnabled(false);

	}

	private String savePettyCashCount(String pcc_no, BigDecimal total_cash_count) {

		String pcc_date = dtePCCDate.getDate().toString();

		double[] denominations = new double[13];

		for (int i = 0; i < denominations.length; i++) {
			try {
				denominations[i] = Double.parseDouble(tblCashCount.getValueAt(i, 2).toString());
			} catch (NullPointerException | NumberFormatException e) {
				denominations[i] = 0.00;
			}
		}

		// Assign values to variables (if necessary)
		double denom_1000 = denominations[0];
		double denom_500 = denominations[1];
		double denom_200 = denominations[2];
		double denom_100 = denominations[3];
		double denom_50 = denominations[4];
		double denom_20 = denominations[5];
		double denom_10 = denominations[6];
		double denom_5 = denominations[7];
		double denom_1 = denominations[8];
		double denom_50c = denominations[9];
		double denom_25c = denominations[10];
		double denom_10c = denominations[11];
		double denom_1c = denominations[12];

		try {

			String SQL = "Select * from fn_save_petty_cash_count('"+co_id+"', '"+pcc_no+"', '"+pcc_date+"'::TIMESTAMP, "+total_cash_count+" \n"
					+ ", "+denom_1000+", "+denom_500+", "+denom_200+", "+denom_100+", "+denom_50+", "+denom_20+" \n"
					+ ", "+denom_10+", "+denom_5+", "+denom_1+", "+denom_50c+", "+denom_25c+", "+denom_10c+", "+denom_1c+", '"+user+"')"; 

			pgSelect db = new pgSelect(); 
			db.select(SQL);

			System.out.println(SQL);
			System.out.println(""); 

			if(db.isNotNull()) {
				pcc_no =  (String) db.getResult()[0][0];	
			}		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Something went wrong.", "Save", JOptionPane.INFORMATION_MESSAGE);
		}

		System.out.println("Petty Cash No: " + pcc_no);
		System.out.println(""); 

		// SAVING OF PETTY CASH FUND SUMMARY
		savePettyCashFundSummary(pcc_no);

		return pcc_no;
	}

	private void savePettyCashFundSummary(String pcc_no) {

		Double beg_bal = Double.valueOf(ftxtBegBalance.getText().trim().replace(",","")).doubleValue();
		BigDecimal cash_returned = (BigDecimal) ftxtCashReturned.getValued(); 
		BigDecimal check_encashment = (BigDecimal) ftxtCheckEncashment.getValued(); 
		Double total_cash_on_hand = Double.valueOf(ftxtTotalCashOnHand.getText().trim().replace(",","")).doubleValue();
		BigDecimal cash_advance = (BigDecimal) ftxtCashAdvance.getValued();
		BigDecimal reimbursement = (BigDecimal) ftxtReimbursement.getValued();
		BigDecimal addtl_cash_released = (BigDecimal) ftxtAddtlCashReleased.getValued();
		Double cash_on_hand_should_be = Double.valueOf(ftxtCOHShouldBe.getText().trim().replace(",","")).doubleValue();
		BigDecimal shortage_or_overage = parseAmount(ftxtShortOverage); 
		BigDecimal cash_on_hand_counted = (BigDecimal) ftxtCOHCounted.getValued();

		logCashFundSummaryValues(beg_bal, cash_returned, check_encashment, total_cash_on_hand, cash_advance, reimbursement, addtl_cash_released, cash_on_hand_should_be, shortage_or_overage, cash_on_hand_counted);

		try {

			String SQL = "Select * from fn_save_petty_cash_fund_summary('"+co_id+"', '"+pcc_no+"', "+beg_bal+", "+cash_returned+", "+check_encashment+", "+total_cash_on_hand+" \n"
					+ ", "+cash_advance+", "+reimbursement+", "+addtl_cash_released+" \n"
					+ ", "+cash_on_hand_should_be+", "+shortage_or_overage+", "+cash_on_hand_counted+", '"+user+"')"; 

			pgSelect db = new pgSelect(); 
			db.select(SQL);

			System.out.println(SQL);
			System.out.println(""); 

			if(db.isNotNull()) {
				pcc_no =  (String) db.getResult()[0][0];	
			}		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Something went wrong.", "Save", JOptionPane.INFORMATION_MESSAGE);
		}

		System.out.println("Petty Cash Fund Summary with PCC No: " + pcc_no);
		System.out.println(""); 
	}

	private void post(String pcc_no) {

		if (JOptionPane.showConfirmDialog(getContentPane(), "Are you sure to post?", "Confirmation",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

			pgUpdate db = new pgUpdate(); 

			String sql = "UPDATE public.rf_petty_cash_count\n"
					+ "	SET status_id = 'P' \n"
					+ "	, post_date = now()\n"
					+ "	, posted_by = '"+user+"' \n"
					+ "	WHERE co_id = '"+co_id+"' \n"
					+ "	AND petty_cash_count_no = '"+pcc_no+"' \n"
					+ "	AND status_id = 'A' ;"; 

			db.executeUpdate(sql, true, true);
			JOptionPane.showMessageDialog(getContentPane(), "Petty Cash Count has been successfully posted.", "Information", JOptionPane.INFORMATION_MESSAGE);

			displayCashCount(pcc_no);
			displayCashFundSummary(pcc_no);
			btnState(false, false, false, true, true, false);
			lookupPCC_No.setEditable(true);
			modelCashCount.setEditable(false);

		} else {
			return; 
		}

	}

	private void displayCashCount(String pcc_no) {

		int rowCount = tblCashCount.getModel().getRowCount();
		String[] denominations = {"1000", "500", "200", "100", "50", "20",
				"10", "5", "1", "0.50", "0.25", "0.10", "0.01"};
		String[] denomSql = {"denom_1000", "denom_500", "denom_200", "denom_100", "denom_50", "denom_20",
				"denom_10", "denom_5", "denom_1", "denom_50c", "denom_25c", "denom_10c", "denom_1c"};

		for (int i = 0; i < rowCount; i++) {
			BigDecimal denominationValue = BigDecimal.ZERO;
			int pieceCount = 0;

			String valueStr = getDenomValue(denomSql[i], pcc_no);
			if (!valueStr.isEmpty()) {
				try {
					denominationValue = new BigDecimal(valueStr);
				} catch (NumberFormatException e) {
					System.err.println("Invalid number format for denomination value: " + valueStr);
				}
			}

			String pieceStr = getDenomPieceNo(denomSql[i], denominations[i], pcc_no);
			if (!pieceStr.isEmpty()) {
				try {
					pieceCount = Integer.parseInt(pieceStr);
				} catch (NumberFormatException e) {
					System.err.println("Invalid number format for piece count: " + pieceStr);
				}
			}

			modelCashCount.setValueAt(new BigDecimal(denominations[i]), i, 0); // Denomination
			modelCashCount.setValueAt(pieceCount, i, 1); // Pieces
			modelCashCount.setValueAt(denominationValue, i, 2); // Value
		}

		try {
			double total = Double.parseDouble(sql_getTotalCashCount(pcc_no));
			modelCashCountTotal.setValueAt(nf.format(total), 0, 2);
		} catch (NumberFormatException e) {
			System.err.println("Invalid number format for total: " + sql_getTotalCashCount(pcc_no));
			modelCashCountTotal.setValueAt(nf.format(0.00), 0, 2); // Default to 0.00
		}
	}

	private void displayCashFundSummary(String pcc_no) {
		pgSelect db = new pgSelect();

		String SQL = "SELECT fn_get_company_name(a.co_id) AS \"Company\"\n"
				+ ", b.petty_cash_count_date::DATE AS \"PCC Date\"\n"
				+ ", c.status_desc AS \"Status\"\n"
				+ ", a.beg_bal\n"
				+ ", (a.cash_returned + a.check_encashment) as \"Cash In Today\"\n"
				+ ", a.cash_returned\n"
				+ ", a.check_encashment\n"
				+ ", a.total_cash_on_hand\n"
				+ ", (a.cash_advance + a.reimbursement + a.addtl_cash_released) as \"Cash Released Today\"\n"
				+ ", a.cash_advance\n"
				+ ", a.reimbursement\n"
				+ ", a.addtl_cash_released\n"
				+ ", a.cash_on_hand_should_be\n"
				+ ", CASE \n"
				+ "    WHEN a.shortage_or_overage < 0 THEN \n"
				+ "        '(' || TO_CHAR(ABS(a.shortage_or_overage), 'FM9,999,999,990.00') || ')'\n"
				+ "    ELSE \n"
				+ "        TO_CHAR(a.shortage_or_overage, 'FM9,999,999,990.00')\n"
				+ "	END AS \"Shortage or Overage\" \n"
				+ ", cash_on_hand_counted\n"
				+ "FROM rf_petty_cash_fund_summary a\n"
				+ "LEFT JOIN rf_petty_cash_count b ON b.co_id = a.co_id AND b.petty_cash_count_no = a.pcc_no AND b.status_id != 'I'\n"
				+ "LEFT JOIN mf_record_status c ON c.status_id = b.status_id \n"
				+ "WHERE a.co_id = '"+co_id+"'\n"
				+ "AND a.pcc_no = '"+pcc_no+"'\n"
				+ "AND a.status_id = 'A'\n"
				+ "";

		db.select(SQL);

		System.out.println("displayCashFundSummary: "+ SQL);	

		if(db.isNotNull()) {
			
			
			lookupCompany.setValue((String) db.getResult()[0][0]); 
			dtePCCDate.setDate((Date)db.getResult()[0][1]); 
			txtStatus.setText((String) db.getResult()[0][2]);
			ftxtBegBalance.setValue((BigDecimal) db.getResult()[0][3]);
			ftxtCashInToday.setValue((BigDecimal) db.getResult()[0][4]);
			ftxtCashReturned.setValue((BigDecimal) db.getResult()[0][5]); 
			ftxtCheckEncashment.setValue((BigDecimal) db.getResult()[0][6]); 
			ftxtTotalCashOnHand.setValue((BigDecimal) db.getResult()[0][7]); 
			ftxtCashReleasedToday.setValue((BigDecimal) db.getResult()[0][8]); 
			ftxtCashAdvance.setValue((BigDecimal) db.getResult()[0][9]); 
			ftxtReimbursement.setValue((BigDecimal) db.getResult()[0][10]); 
			ftxtAddtlCashReleased.setValue((BigDecimal) db.getResult()[0][11]); 
			ftxtCOHShouldBe.setValue((BigDecimal) db.getResult()[0][12]); 
			ftxtShortOverage.setText((String) db.getResult()[0][13]); 
			ftxtCOHCounted.setValue((BigDecimal) db.getResult()[0][14]); 
		}

	}

	private void setFTxtDefaultValue(){
		
		ftxtBegBalance.setValue(getBegBalance()); 
		ftxtCashReturned.setValue(BigDecimal.ZERO); 
		ftxtCashAdvance.setValue(BigDecimal.ZERO); 
		ftxtReimbursement.setValue(BigDecimal.ZERO); 
		ftxtAddtlCashReleased.setValue(BigDecimal.ZERO); 
		ftxtTotalCashOnHand.setValue(BigDecimal.ZERO); 
		ftxtCOHShouldBe.setValue(BigDecimal.ZERO); 
		ftxtCOHCounted.setValue(BigDecimal.ZERO); 
		ftxtShortOverage.setValue(BigDecimal.ZERO); 
		ftxtCashInToday.setValue(BigDecimal.ZERO); 
		ftxtCashReleasedToday.setValue(BigDecimal.ZERO); 
		ftxtCheckEncashment.setValue(BigDecimal.ZERO); 
	}

	private void adjustRowHeight(_JTableMain table) {
		int rowCount = table.getRowCount();
		if (rowCount > 0) {
			// Set all rows to the same height
			table.setRowHeight(22);
		}
	}

	private void setCompEnabled(JPanel panel) {
		this.setComponentsEnabled(panel, true);
	}

	private void setCompDisabled(JPanel panel) {
		this.setComponentsEnabled(panel, false);
	}

	private void setCompEditable(JPanel panel, Boolean editable) {
		this.setComponentsEditable(panel, editable);
	}

	public void showWarningMessage(String message, String title) {
		JOptionPane.showMessageDialog(getContentPane(), message, title, JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		totalCashCount(modelCashCount, modelCashCountTotal);
		updateShortageOrOverage();

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		totalCashCount(modelCashCount, modelCashCountTotal);
		updateShortageOrOverage();

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		totalCashCount(modelCashCount, modelCashCountTotal);
		updateShortageOrOverage();

	}

	private boolean isShortOrOver(BigDecimal totalCashCount) {
		
	    // Parse the short/over amount entered by the user
	    BigDecimal discrepancyAmount = parseAmount(ftxtShortOverage);

	    // If discrepancy exists (either short or over), prompt the user for confirmation
	    if (discrepancyAmount.compareTo(BigDecimal.ZERO) != 0) {
	        String message = (discrepancyAmount.compareTo(BigDecimal.ZERO) < 0) ? 
	                          "Cash count discrepancy: Short by " + nf.format(discrepancyAmount) : 
	                          "Cash count discrepancy: Over by " + nf.format(discrepancyAmount);

	        int response = JOptionPane.showConfirmDialog(getContentPane(),
	                message + ".\nAre you sure you want to continue saving?", 
	                "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

	        // If user selects "No", return true, indicating the discrepancy exists but they didn't proceed
	        if (response == JOptionPane.NO_OPTION) {
	            return true;
	        }

	     // If user selects "Yes", proceed with saving immediately (bypass next prompt)
	        if (response == JOptionPane.YES_OPTION) {
	            executeSave(totalCashCount);
	            return true; // Saving is already done, no need to proceed to next check
	        }
	    }

	    // No discrepancy (amount is zero)
	    return false;
	}
	
	private String pendingPettyCashCount() {
		String active_pcc = ""; 
		
		pgSelect db = new pgSelect(); 
		db.select("SELECT petty_cash_count_no\n"
				+ "FROM rf_petty_cash_count \n"
				+ "WHERE status_id = 'A' \n"
				+ "AND rec_status = 'A'\n"
				+ "ORDER BY date_created\n"
				+ "LIMIT 1;");
		
		if(db.isNotNull()) {
			active_pcc = (String) db.getResult()[0][0]; 	
		} else {
			active_pcc = null; 
		}
		
		return active_pcc; 
	}

	/**
	 * Parses the value from the JTextField, handling parentheses for negative numbers.
	 * @param field JTextField containing the amount.
	 * @return BigDecimal representing the amount (negative if in parentheses).
	 */
	public static BigDecimal parseAmount(JFormattedTextField shortage_or_overage) {
		String value = shortage_or_overage.getText().trim();

//		System.out.println("Value ng Shortage/Overage before i-format: " + value);
		
		// Check if the text is wrapped in parentheses (indicating a negative number)
		if (value.startsWith("(") && value.endsWith(")")) {
			
			// Remove the parentheses and parse the value
			String numberText = value.substring(1, value.length() - 1).trim().replace(",", "");
			
			try {

				System.out.println("Formatted amount: " + new BigDecimal("-" + numberText));
				// Convert the number to a negative BigDecimal
				return new BigDecimal("-" + numberText);
			} catch (NumberFormatException e) {
				// invalid number format
				JOptionPane.showMessageDialog(null, "Invalid amount format.", "Error", JOptionPane.ERROR_MESSAGE);
				return BigDecimal.ZERO; 
			}
		} else {
			try {
	
				return new BigDecimal(value.trim().replace(",", ""));
			} catch (NumberFormatException e) {
				// invalid number format
				JOptionPane.showMessageDialog(null, "Invalid amount format.", "Error", JOptionPane.ERROR_MESSAGE);
				return BigDecimal.ZERO; 
			}
		}
	}

	//SQL
	public static String getPCC_no(String co_id) {
		String SQL = "SELECT a.petty_cash_count_no AS \"Petty Cash Count No.\"\n"
				+ ", a.petty_cash_count_date::DATE AS \"Date\"\n"
				+ ", b.status_desc AS \"Status\"\n"
				+ "FROM rf_petty_cash_count a\n"
				+ "LEFT JOIN mf_record_status b ON a.status_id = b.status_id \n"
				+ "WHERE co_id = '"+co_id+"'\n"
				+ "AND rec_status = 'A'\n"
				+ "ORDER by a.date_created DESC";	

		System.out.println("getPCR_no: "+ SQL);
		System.out.println(""); 

		return SQL; 
	}

	private BigDecimal getBegBalance() {

		String SQL = "SELECT COALESCE(total_pcc_amount, 0.00)\n"
				+ "FROM rf_petty_cash_count \n"
				+ "WHERE status_id = 'P'\n"
				+ "AND rec_status = 'A'\n"
				+ "ORDER BY date_created\n"
				+ "DESC LIMIT 1";

		System.out.printf("SQL Query (getBegBalance): %s%n", SQL);

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull() && db.getResult()[0][0] != null) {
			return (BigDecimal) db.getResult()[0][0];
		}

		return BigDecimal.ZERO;
	}

	private String getDenomValue(String denom, String pcc_no) {
		String SQL = "SELECT " + denom + " FROM rf_petty_cash_count "
				+ "WHERE co_id = '"+co_id+"' \n"
				+ "AND petty_cash_count_no = '"+pcc_no+"' \n"
				+ " AND rec_status = 'A'";

		System.out.printf("SQL Query (getDenomValue): %s%n", SQL);

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull() && db.getResult()[0][0] != null) {
			return db.getResult()[0][0].toString();
		}
		return "";
	}

	private String getDenomPieceNo(String denom, String x, String pcc_no) {
		String SQL = "SELECT (" + denom + "/" + x + ")::int \n"
				+ "FROM rf_petty_cash_count \n"
				+ "WHERE co_id = '"+co_id+"' \n"
				+ "AND petty_cash_count_no = '"+pcc_no+"' \n"
				+ "AND rec_status = 'A'";

		System.out.printf("SQL Query (getDenomPieceNo): %s%n", SQL);

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull() && db.getResult()[0][0] != null) {
			return db.getResult()[0][0].toString();
		}
		return "";
	}

	private String sql_getTotalCashCount(String pcc_no) {
		
		String SQL = "SELECT total_pcc_amount::VARCHAR \n"
				+ "FROM rf_petty_cash_count \n"
				+ "WHERE co_id = '"+co_id+"' \n"
				+ "AND petty_cash_count_no = '"+pcc_no+"' \n"
				+ "AND rec_status = 'A'";

		System.out.printf("SQL Query (getTotalCashCount): %s%n", SQL);

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull() && db.getResult()[0][0] != null) {
			return db.getResult()[0][0].toString();
		}
		return "0.00";
	}

	// Log Cash Fund Summary values 
	private void logCashFundSummaryValues(Double beg_bal,  BigDecimal cash_ret, BigDecimal check_encash, double total_coh, BigDecimal cash_advance, BigDecimal reimbursement, BigDecimal addtl_cash_rel, double coh_should_be, BigDecimal short_over, BigDecimal coh_counted) {
		System.out.println("");
		System.out.println("Beggining Balance: " + beg_bal);
		System.out.println("Cash In Today: " + cash_ret.add(check_encash));
		System.out.println("Cash Returned: " + cash_ret);
		System.out.println("Check Encashment: " +  check_encash);
		System.out.println("Total Cash on Hand For the Day: " + total_coh);
		System.out.println("Cash Released Today: " + cash_advance.add(reimbursement).add(addtl_cash_rel));
		System.out.println("Cash Advance(s): " + cash_advance);
		System.out.println("Reimbursement(s): " + reimbursement);
		System.out.println("Addt'l Cash Released: " + addtl_cash_rel);
		System.out.println("Cash on hand Should Be: " + coh_should_be);
		System.out.println("Shortage / Overage: " + short_over);
		System.out.println("Cash on Hand Counted: " + coh_counted);
		System.out.println("");

	}
	
	public static String getCompanyLogo(String co_id) {
		pgSelect db = new pgSelect(); 
		String company_logo = "";

		db.select("SELECT company_logo FROM mf_company where co_id = '"+co_id+"';");

		if (db.isNotNull()) {
			company_logo = (String) db.getResult()[0][0];
		}

		return company_logo;
	}
	
	public void preview() {

		Map<String, Object> mapParameters = new HashMap<String, Object>();
		mapParameters.put("co_id", co_id);
		mapParameters.put("logo", this.getClass().getClassLoader().getResourceAsStream("Images/" + company_logo));
		mapParameters.put("user", UserInfo.FullName);
		mapParameters.put("pcc_no", lookupPCC_No.getText().trim());

		System.out.println("");
		System.out.println("Value of co_id:" +  co_id);
		System.out.println("Value of logo:" +  company_logo);
		System.out.println("Value of user:" +  UserInfo.FullName);
		System.out.println("Value of pcc_no:" +  lookupPCC_No.getText().trim());
		
		FncReport.generateReport("/Reports/rptPettyCashCountSheet.jasper", title, mapParameters);
		
	}

}
