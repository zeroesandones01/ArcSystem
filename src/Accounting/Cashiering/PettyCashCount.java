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
	private JList<Integer> rowHeaderCashCount;
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
							rowHeaderCashCount = tblCashCount.getRowHeader22();
							scrollCashCount.setRowHeaderView(rowHeaderCashCount);
							scrollCashCount.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
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
							((_JTableTotal) tblCashCountTotal).setTotalLabel(0);
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
							ftxtCashReturned.setText("0.00"); 
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
							ftxtCheckEncashment.setText("0.00"); 
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
							ftxtCashAdvance.setText("0.00"); 
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
							ftxtReimbursement.setText("0.00"); 
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
							ftxtAddtlCashReleased.setText("0.00"); 
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
							ftxtShortOverage.setText("0.00"); 
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
							ftxtBegBalance.setText("0.00"); 
							ftxtBegBalance.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							ftxtBegBalance.setEditable(false);
							ftxtBegBalance.setFont(dialog12Bold);
						}
						{
							ftxtCashInToday = new _JXFormattedTextField(_JXFormattedTextField.CENTER); 
							pnlAmounts.add(ftxtCashInToday); 
							ftxtCashInToday.setText("0.00"); 
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
							ftxtCashInToday.setText("0.00"); 
							ftxtTotalCashOnHand.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							ftxtTotalCashOnHand.setEditable(false); 
							ftxtTotalCashOnHand.setFont(dialog12Bold);
						}
						{
							ftxtCashReleasedToday = new _JXFormattedTextField(_JXFormattedTextField.CENTER);
							pnlAmounts.add(ftxtCashReleasedToday); 
							ftxtCashReleasedToday.setText("0.00"); 
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
							ftxtCOHShouldBe.setText("0.00"); 
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
							ftxtCOHCounted.setText("0.00"); 
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
			setFTxtDefaultValue();
			btnState(false, false, true, false, true, false);

		}

		if(e.getActionCommand().equals("Save")) {
			
			// Validate value of Total Amount of Petty Cash Count 
			String str_total_cash_count = modelCashCountTotal.getValueAt(0, 2).toString().trim().replace(",",""); 
			BigDecimal total_cash_count = new BigDecimal(str_total_cash_count); 

			if(total_cash_count.compareTo(BigDecimal.ZERO) <= 0) {
				showWarningMessage("Please input cash count denomination(s).", "Cash Count");
				return;
			} 
			
			System.out.println("");
			System.out.println("Value of Shortage/Overage: " + parseAmount(ftxtAddtlCashReleased));
			
			if(isShortOrOver()) {
				executeSave(total_cash_count);
			} else {
				if (JOptionPane.showConfirmDialog(getContentPane(), "Are all details correct?", "Confirmation",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					executeSave(total_cash_count);
				} else {
					return;
				}
				
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
			System.out.println("Cash Returned: " + cash_ret);
			System.out.println("Check Encashment: " + check_encash);
			System.out.println(""); 

			// Calculate the sum
			Double sum = (cash_ret + check_encash); 
			System.out.println("Cash on Hand for the day: " + sum);
			System.out.println(""); 

			// Display the sum in the result field
			ftxtCashInToday.setText(nf.format(sum));  

			// Calculate Total Cash On Hand For the Day
			Double total_coh_for_the_day = (beg_bal+ sum);  
			System.out.println("Total Cash On hand: " + total_coh_for_the_day);
			System.out.println(""); 

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
			System.out.println("Cash Advance(s): " + cash_adv);
			System.out.println("Reimbursement(s): " + reimbursement);
			System.out.println("Addt'l Cash Released: " + addtl_cash_rel);
			System.out.println(""); 

			// Calculate the sum
			Double sum = (cash_adv + reimbursement + addtl_cash_rel); 
			System.out.println("Total Cash Released Today: " + sum);
			System.out.println(""); 

			// Display the sum in the result field
			ftxtCashReleasedToday.setText(nf.format(sum));  

			// Calculate value of Cash on hand - Should be
			Double total_coh_should_be = (total_cash_on_hand - sum);  
			System.out.println("Total Cash On hand - Should Be: " + total_coh_should_be);
			System.out.println(""); 

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
			System.out.println("Cash On hand - Should Be: " + cashOnHand_should_be);
			System.out.println("Cash On hand Counted: " + cashOnHand_counted);
			System.out.println(""); 

			// Calculate the value of Shortage / Overage
			Double shortageOrOverage = (cashOnHand_counted - cashOnHand_should_be); 
			System.out.println("Total Shortage Or Overage: " + shortageOrOverage);
			System.out.println(""); 

			// Format the value and check if it's negative
			String formattedValue;
			if (shortageOrOverage < 0) {
				// For negative values, format inside parentheses
				formattedValue = "(" + nf.format(Math.abs(shortageOrOverage)) + ")";
			} else {
				// For positive values, just format normally
				formattedValue = nf.format(shortageOrOverage);
			}

			// Display the value in the text field
			ftxtShortOverage.setText(formattedValue);


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

			// SAVE FROM EDIT
			else { 

				savePettyCashCount(pcc_no, total_cash_count); 
				JOptionPane.showMessageDialog(getContentPane(), "Petty Cash Count No. " + pcc_no + " was successfully updated."  , "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
			lookupPCC_No.setValue(pcc_no);
			lookupPCC_No.setEditable(true);
			modelCashCount.setEditable(false);
			displayCashCount(pcc_no);
			btnState(false, true, false, true, true, true);
			setCompEditable(pnlCashCount, false);
			setCompEditable(pnlCashFundSummary, false);
			
		
	}

	private String savePettyCashCount(String pcc_no, BigDecimal total_cash_count) {

		String pcc_date = dtePCCDate.getDate().toString();

		double[] denominations = new double[13];
//		String[] denominationLabels = {
//				"denom_1000", "denom_500", "denom_200", "denom_100",
//				"denom_50", "denom_20", "denom_10", "denom_5",
//				"denom_1", "denom_50c", "denom_25c", "denom_10c", "denom_1c"
//		};

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
		savePettyFundSummary(pcc_no);

		return pcc_no;
	}

	private void savePettyFundSummary(String pcc_no) {
		//Save to Petty Cash Fund Summary

		Double beg_bal = Double.valueOf(ftxtBegBalance.getText().trim().replace(",","")).doubleValue();
		BigDecimal cash_returned = (BigDecimal) ftxtCashReturned.getValued(); 
		BigDecimal check_encashment = (BigDecimal) ftxtCheckEncashment.getValued(); 
		Double total_cash_on_hand = Double.valueOf(ftxtTotalCashOnHand.getText().trim().replace(",","")).doubleValue();
		BigDecimal cash_advance = (BigDecimal) ftxtCashAdvance.getValued();
		BigDecimal reimbursement = (BigDecimal) ftxtReimbursement.getValued();
		BigDecimal addtl_cash_released = (BigDecimal) ftxtAddtlCashReleased.getValued();
		Double cash_on_hand_should_be = Double.valueOf(ftxtCOHShouldBe.getText().trim().replace(",","")).doubleValue();
		Double shortage_or_overage = Double.valueOf(ftxtShortOverage.getText().trim().replace(",","")).doubleValue();
		BigDecimal cash_on_hand_counted = (BigDecimal) ftxtCOHCounted.getValued();

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

			btnState(false, false, false, true, true, false);
			lookupPCC_No.setEditable(true);

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
				+ ", a.shortage_or_overage\n"
				+ ", cash_on_hand_counted\n"
				+ "FROM rf_petty_cash_fund_summary a\n"
				+ "LEFT JOIN rf_petty_cash_count b ON b.co_id = a.co_id AND b.petty_cash_count_no = a.pcc_no AND b.status_id != 'I'\n"
				+ "LEFT JOIN mf_record_status c ON c.status_id = b.status_id \n"
				+ "WHERE a.co_id = '"+co_id+"'\n"
				+ "AND a.pcc_no = '"+pcc_no+"'\n"
				+ "AND a.status_id = 'A'\n"
				+ "";

		db.select(SQL);

		System.out.println("displayPCR_header: "+ SQL);	

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
			ftxtShortOverage.setValue((BigDecimal) db.getResult()[0][13]); 
			ftxtCOHCounted.setValue((BigDecimal) db.getResult()[0][14]); 
		}

	}

	private void setFTxtDefaultValue(){

		ftxtCashReturned.setText("0.00");
		ftxtCashAdvance.setText("0.00");
		ftxtReimbursement.setText("0.00");
		ftxtAddtlCashReleased.setText("0.00");
		ftxtBegBalance.setText("0.00");
		ftxtTotalCashOnHand.setText("0.00");
		ftxtCOHShouldBe.setText("0.00");
		ftxtCOHCounted.setText("0.00");
		ftxtShortOverage.setText("0.00");
		ftxtCashInToday.setText("0.00");
		ftxtCashReleasedToday.setText("0.00");
		ftxtCheckEncashment.setText("0.00");
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

	private Boolean isShortOrOver() {
		Boolean isShortOrOver = false; 

		BigDecimal amount = parseAmount(ftxtShortOverage);

		// Check if the amount is less than or greater than zero
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			if (JOptionPane.showConfirmDialog(getContentPane(), "Cash count discrepancy: Short by " + amount + ". Are you sure you want to continue saving?", "Confirmation",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				isShortOrOver = true; 
			} else {
				isShortOrOver = false; 
			}
			
		} else if (amount.compareTo(BigDecimal.ZERO) > 0) {
			if (JOptionPane.showConfirmDialog(getContentPane(), "Cash count discrepancy: Over by " + amount + ". Are you sure you want to continue saving?", "Confirmation",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				isShortOrOver = true; 
			} else {
				isShortOrOver = false; 
			}
		} else {
			// amout is ZERO
			isShortOrOver = false ; 
		}
		return isShortOrOver;
	}

	/**
	 * Parses the value from the JTextField, handling parentheses for negative numbers.
	 * @param field JTextField containing the amount.
	 * @return BigDecimal representing the amount (negative if in parentheses).
	 */
	public static BigDecimal parseAmount(JFormattedTextField shortage_or_overage) {
		String value = shortage_or_overage.getText().trim();

		System.out.println("Value ng Shortage/Overage before i-format: " + value);
		// Check if the text is wrapped in parentheses (indicating a negative number)
		if (value.startsWith("(") && value.endsWith(")")) {
			// Remove the parentheses and parse the value
			String numberText = value.substring(1, value.length() - 1).trim().replace(",", "");
//			numberText = numberText.trim().replace(",", ""); 

			try {
				
				System.out.println("Formatted amount: " + new BigDecimal("-" + numberText));
				// Convert the number to a negative BigDecimal
				return new BigDecimal("-" + numberText);
			} catch (NumberFormatException e) {
				// Handle invalid number format
				System.out.println("Dumaan dito sa Invalid Format with Paretheses!");
				JOptionPane.showMessageDialog(null, "Invalid amount format.", "Error", JOptionPane.ERROR_MESSAGE);
				return BigDecimal.ZERO; // Return zero if invalid input
			}
		} else {
			try {
				// If it's not in parentheses, parse it normally
				return new BigDecimal(value.trim().replace(",", ""));
			} catch (NumberFormatException e) {
				// Handle invalid number format
				JOptionPane.showMessageDialog(null, "Invalid amount format.", "Error", JOptionPane.ERROR_MESSAGE);
				return BigDecimal.ZERO; // Return zero if invalid input
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
	
	private String getBegBalance() {
		String beg_bal = "";

		String SQL = "SELECT total_pcc_amount\n"
				+ "FROM rf_petty_cash_count \n"
				+ "WHERE status_id = 'P'\n"
				+ "AND rec_status = 'A'\n"
				+ "ORDER BY date_created::DATE \n"
				+ "DESC LIMIT 1";

		System.out.printf("SQL Query (getBegBalance): %s%n", SQL);

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull() && db.getResult()[0][0] != null) {
			return db.getResult()[0][0].toString();
		}
		
		return "0.00";
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
		String total_pcc_amount = "";

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

}
