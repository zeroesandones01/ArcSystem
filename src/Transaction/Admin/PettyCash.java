/**
 * 
 */
package Transaction.Admin;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import Database.pgSelect;
import Database.pgUpdate;
import Dialogs.dlg_CR_PW_Entry;
import FormattedTextField._JXFormattedTextField;
import Functions.FncAcounting;
import Functions.FncGlobal;
import Functions.FncReport;
import Functions.FncSystem;
import Functions.FncTables;
import Functions.UserInfo;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import Lookup._JLookupTable;
import components._JInternalFrame;
import components._JScrollPaneMain;
import components._JScrollPaneTotal;
import components._JTableMain;
import components._JTableTotal;
import interfaces._GUI;
import tablemodel.modelPettyCashRequest;
import tablemodel.modelDRF_particulars.BigDecimalCellEditor;


/**
 * @author Monique Boriga
 */
public class PettyCash extends _JInternalFrame implements _GUI, ActionListener, MouseListener {

	private static final long serialVersionUID = -4306362386582966144L;
	private static String title = "PETTY CASH";
	Border lineBorder = BorderFactory.createLineBorder(Color.GRAY);
	protected static Font dialog11Bold = new Font("DIALOG", Font.BOLD, 11);
	private static BigDecimal total_pcr_amt;
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	private JPanel pnlMain;
	private JPanel pnlMainNorth;
	private JPanel pnlCenterSouth;

	private JButton btnAddNew;
	private JButton btnEdit;
	private JButton btnSave;
	private JButton btnPreview;
	private JButton btnPay;
	private JButton btnProcess;
	private JButton btnCancel;
	private JButton btnAddRow;
	private JButton btnRemoveRow;

	private _JLookup lookupCompany;
	private _JLookup lookupPCRNo;
	private _JLookup lookupPCRType;
	private _JLookup lookupPCRLiq;

	private JDateChooser dteDateCreated;
	private JDateChooser dteTransDate;

	private JTextField txtPCRStatus;
	private JTextField txtPayee;
	private JTextField txtDiv;

	private _JScrollPaneMain scrollTable;	
	private modelPettyCashRequest modelPettyCashReq;	
	private _JTableMain tblPettyCash;
	private JList<Integer> rowHeaderScroll;
	private _JScrollPaneTotal scrollTableTotal;
	private modelPettyCashRequest modelPettyCashReqTotal;
	private _JTableTotal tblPettyCashTotal;

	private _JXFormattedTextField ftxtAmtToBeReimbursed;
	private _JXFormattedTextField ftxtTotalAmtOfCA;
	private _JXFormattedTextField ftxtCashReturned;

	private JLabel lblCompany;
	private JLabel lblPCRNo;

	private String co_id; 
	private String pcr_type_id;
	private String div_id;
	private String user;
	private String payee;
	private String pcr_no;
	private String pcr_no_liq;
	private String pcr_status;
	private String process_id;

	protected BigDecimal pcr_liq_amt;
	private BigDecimal totalPCRAmt;
	private BigDecimal cashRetamt;
	private BigDecimal reimbursementAmt;

	private Boolean cash_liq = false;
	private Object company_logo;
	private JCheckBox chkCurrYear;
	protected String curr_year;

	/**
	 * 
	 */
	public PettyCash() {
		super(title, true, true, true, true);
		initGUI();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initGUI() {
		this.setLayout(new BorderLayout(5, 5));
		this.setPreferredSize(new Dimension(935, 540));
		this.setTitle(title);
		{
			pnlMain = new JPanel(new BorderLayout(5, 5));
			getContentPane().add(pnlMain, BorderLayout.CENTER);
			pnlMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

			{
				pnlMainNorth = new JPanel(new BorderLayout(5, 5)); 
				pnlMain.add(pnlMainNorth, BorderLayout.NORTH); 
				pnlMainNorth.setPreferredSize(new Dimension(0, 180));
				pnlMainNorth.setBorder(components.JTBorderFactory.createTitleBorder("Petty Cash Details"));

				{
					JPanel pnlNorth = new JPanel(new BorderLayout(10, 10)); 
					pnlMainNorth.add(pnlNorth, BorderLayout.NORTH); 
					pnlNorth.setPreferredSize(new Dimension(0, 50));
					{
						JPanel pnlNorthWest = new JPanel(new BorderLayout(5, 5));
						pnlNorth.add(pnlNorthWest, BorderLayout.WEST); 
						pnlNorthWest.setPreferredSize(new Dimension(320, 0));
						{
							JPanel pnlNWLabel = new JPanel(new BorderLayout(5, 5)); 
							pnlNorthWest.add(pnlNWLabel, BorderLayout.WEST); 
							pnlNWLabel.setPreferredSize(new Dimension(70, 0));
							{
								lblCompany = new JLabel("Company: ", JLabel.TRAILING);
								pnlNWLabel.add(lblCompany); 
							}
						}
						{
							JPanel pnlNWComp = new JPanel(new BorderLayout(5, 5)); 
							pnlNorthWest.add(pnlNWComp, BorderLayout.CENTER); 
							{
								lookupCompany = new _JLookup(null, "Company", 1);
								pnlNWComp.add(lookupCompany); 
								lookupCompany.setFilterName(true);
								lookupCompany.setLookupSQL(FncGlobal.getCompany());
								lookupCompany.setEditable(false);
								lookupCompany.addLookupListener(new LookupListener() {

									@Override
									public void lookupPerformed(LookupEvent event) {
										Object [] data = ((_JLookup) event.getSource()).getDataSet(); 

										if(data != null) {
											co_id = (String) data[0]; 
										}
									}
								});
							}						

						}
					}
					{
						JPanel pnlNorthCenter = new JPanel(new BorderLayout(5, 5)); 
						pnlNorth.add(pnlNorthCenter, BorderLayout.CENTER); 
						{
							JPanel pnlPCR = new JPanel(new BorderLayout(3, 3)); 
							pnlNorthCenter.add(pnlPCR, BorderLayout.WEST); 
							pnlPCR.setPreferredSize(new Dimension(220, 0));
							{
								lblPCRNo = new JLabel("PCR No.: ", JLabel.TRAILING);
								pnlPCR.add(lblPCRNo, BorderLayout.WEST); 
							}
							{
								lookupPCRNo = new _JLookup(null, "Petty Cash No.", 0); 
								pnlPCR.add(lookupPCRNo, BorderLayout.CENTER);
								lookupPCRNo.setFont(new Font("Segoe UI", Font.BOLD, 12));
								lookupPCRNo.addLookupListener(new LookupListener() {

									@Override
									public void lookupPerformed(LookupEvent event) {
										Object [] data = ((_JLookup)event.getSource()).getDataSet();

										if (data != null) {
											pcr_no = (String) data[0]; 
											pcr_type_id = (String) data[3]; 
											pcr_status = (String) data[2]; 
										}

										displayPCR_header(co_id, pcr_no);
										displayPCR_details(modelPettyCashReq, rowHeaderScroll, modelPettyCashReqTotal, co_id, pcr_no);
										lookupPCRNo.setEnabled(true);

										setCompEnabled(pnlMainNorth);
										setCompEditable(pnlMainNorth, false);
										lookupPCRNo.setEditable(true);
										dteDateCreated.getCalendarButton().setVisible(false);
										dteTransDate.getCalendarButton().setVisible(false);

										if(pcr_type_id.equals("03")) {
											setCompEnabled(pnlCenterSouth);
											JPanel pnlPCR = new JPanel(new BorderLayout(3, 3)); 
											pnlNorthCenter.add(pnlPCR, BorderLayout.WEST); 
											pnlPCR.setPreferredSize(new Dimension(220, 0));
											{
												lblPCRNo = new JLabel("PCR No.: ", JLabel.TRAILING);
												pnlPCR.add(lblPCRNo, BorderLayout.WEST); 
											}
											{
												lookupPCRNo = new _JLookup(null, "Petty Cash No.", 0); 
												pnlPCR.add(lookupPCRNo, BorderLayout.CENTER);
												lookupPCRNo.setFont(new Font("Segoe UI", Font.BOLD, 12));
												lookupPCRNo.addLookupListener(new LookupListener() {

													@Override
													public void lookupPerformed(LookupEvent event) {
														Object [] data = ((_JLookup)event.getSource()).getDataSet();

														if (data != null) {
															pcr_no = (String) data[0]; 
															pcr_type_id = (String) data[3]; 
															pcr_status = (String) data[2]; 
														}

														displayPCR_header(co_id, pcr_no);
														displayPCR_details(modelPettyCashReq, rowHeaderScroll, modelPettyCashReqTotal, co_id, pcr_no);
														lookupPCRNo.setEnabled(true);
														setCompEnabled(pnlMainNorth);
														setCompEditable(pnlMainNorth, false);
														lookupPCRNo.setEditable(true);
														dteDateCreated.getCalendarButton().setVisible(false);
														dteTransDate.getCalendarButton().setVisible(false);

														if(pcr_type_id.equals("03")) {
															setCompEnabled(pnlCenterSouth);
															setCompEditable(pnlCenterSouth, false);
														}

														if(FncAcounting.EmpPettyCashCustodian(UserInfo.EmployeeCode, "17") == true) {

															// AUTO DATE PAID / PROCESSED as CURRENT DATE
															dteTransDate.setEnabled(true); 
															dteTransDate.setDate(FncGlobal.getDateToday());
															dteTransDate.getCalendarButton().setVisible(false);

															if(pcr_status.equals("ACTIVE")) { 
																// TO ENABLE EDIT BUTTON FOR REQUESTS CREATED BY THE CUSTODIAN
																if(isCustodianRequest(co_id, pcr_no, user)) {

																	if(isToPay(pcr_type_id)) { // TO PAY	
																		btnState(false, true, false, true, true, false, true, false, false);

																	} else { // TO PROCESS
																		btnState(false, true, false, true, false, true, true, false, false);
																	}
																} else {
																	if(isToPay(pcr_type_id)) { // TO PAY	
																		btnState(false, false, false, true, true, false, true, false, false);

																	} else { // TO PROCESS
																		btnState(false, false, false, true, false, true, true, false, false);
																	}
																}

															} else if(pcr_status.equals("PAID") && pcr_type_id.equals("04")) { // TO PROCESS PAID SET-UP/ REPLENISHMENT REQUEST
																btnState(false, false, false, true, false, true, true, false, false); 

															} else { // TO PREVIEW REQUEST ONLY
																btnState(false, false, false, true, false, false, true, false, false); 

																// ENABLE THIS COMPONENT FOR DISPLAY OF DATE PAID OR PROCESSED DATE
																dteTransDate.setEnabled(true); 
																dteTransDate.getCalendarButton().setVisible(false);
															}	

														} else if(pcr_status.equals("ACTIVE")) {
															btnState(false, true, false, true, false, false, true, false, false); 
														} else {
															btnState(false, false, false, true, false, false, true, false, false); 
														}
													}
												});
											}

											setCompEditable(pnlCenterSouth, false);
										}

										if(FncAcounting.EmpPettyCashCustodian(UserInfo.EmployeeCode, "17") == true) {

											// AUTO DATE PAID / PROCESSED as CURRENT DATE
											dteTransDate.setEnabled(true); 
											dteTransDate.setDate(FncGlobal.getDateToday());
											dteTransDate.getCalendarButton().setVisible(false);

											if(pcr_status.equals("ACTIVE")) { 
												// TO ENABLE EDIT BUTTON FOR REQUESTS CREATED BY THE CUSTODIAN
												if(isCustodianRequest(co_id, pcr_no, user)) {

													if(isToPay(pcr_type_id)) { // TO PAY	
														btnState(false, true, false, true, true, false, true, false, false);

													} else { // TO PROCESS
														btnState(false, true, false, true, false, true, true, false, false);
													}
												} else {
													if(isToPay(pcr_type_id)) { // TO PAY	
														btnState(false, false, false, true, true, false, true, false, false);

													} else { // TO PROCESS
														btnState(false, false, false, true, false, true, true, false, false);
													}
												}

											} else if(pcr_status.equals("PAID") && pcr_type_id.equals("04")) { // TO PROCESS PAID SET-UP/ REPLENISHMENT REQUEST
												btnState(false, false, false, true, false, true, true, false, false); 

											} else { // TO PREVIEW REQUEST ONLY
												btnState(false, false, false, true, false, false, true, false, false); 

												// ENABLE THIS COMPONENT FOR DISPLAY OF DATE PAID OR PROCESSED DATE
												dteTransDate.setEnabled(true); 
												dteTransDate.getCalendarButton().setVisible(false);
											}	

										} else if(pcr_status.equals("ACTIVE")) {
											btnState(false, true, false, true, false, false, true, false, false); 
										} else {
											btnState(false, false, false, true, false, false, true, false, false); 
										}
									}
								});
							}
						}
						{
							JPanel pnlPCRStatus = new JPanel(new BorderLayout(3, 3)); 
							pnlNorthCenter.add(pnlPCRStatus, BorderLayout.CENTER); 
							{
								JLabel lblPCRStatus = new JLabel("Status: ", JLabel.TRAILING);
								pnlPCRStatus.add(lblPCRStatus, BorderLayout.WEST); 
							}
							{
								txtPCRStatus = new JTextField(); 
								pnlPCRStatus.add(txtPCRStatus, BorderLayout.CENTER);
								txtPCRStatus.setHorizontalAlignment(JTextField.CENTER);
								txtPCRStatus.setEditable(false);
								txtPCRStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));
							}
						}
					}
					{
						JPanel pnlCheckBox = new JPanel(new BorderLayout(5, 5)); 
						pnlNorth.add(pnlCheckBox, BorderLayout.SOUTH); 
						pnlCheckBox.setPreferredSize(new Dimension(0, 15));
						{
							JPanel pnlExtra = new JPanel();
							pnlCheckBox.add(pnlExtra, BorderLayout.WEST); 
							pnlExtra.setPreferredSize(new Dimension(380, 0));
						}
						{
							chkCurrYear = new JCheckBox("current year only?"); 
							pnlCheckBox.add(chkCurrYear, BorderLayout.CENTER);
							chkCurrYear.addItemListener(new ItemListener() {

								@Override
								public void itemStateChanged(ItemEvent arg0) {
									if(chkCurrYear.isSelected()){
										curr_year = getCurrentYear(); 
										System.out.println(curr_year);
									} else {
										curr_year = ""; 
										System.out.println(curr_year);
									}
								}
							});

						}
						{
							JPanel pnlExtra = new JPanel();
							pnlCheckBox.add(pnlExtra, BorderLayout.EAST);
							pnlExtra.setPreferredSize(new Dimension(200, 0));
						}
					}
				}
				{
					JPanel pnlCenter = new JPanel(new BorderLayout(5, 5)); 
					pnlMainNorth.add(pnlCenter, BorderLayout.CENTER); 
					{
						{
							JPanel pnlCenterWest = new JPanel(new BorderLayout(3, 3)); 
							pnlCenter.add(pnlCenterWest, BorderLayout.WEST); 
							pnlCenterWest.setPreferredSize(new Dimension(350, 0));
							{
								JPanel pnlCWLabel = new JPanel(new GridLayout(3, 1, 3, 3)); 
								pnlCenterWest.add(pnlCWLabel, BorderLayout.WEST); 
								pnlCWLabel.setPreferredSize(new Dimension(90, 0));
								{
									JLabel lblDateCreated = new JLabel("Date Created: ", JLabel.TRAILING);
									pnlCWLabel.add(lblDateCreated); 
								}
								{
									JLabel lblPCRType = new JLabel("Request Type: ", JLabel.TRAILING);
									pnlCWLabel.add(lblPCRType); 
								}
								{
									pnlCWLabel.add(Box.createHorizontalBox()); //EXTRA SPACE
								}								
							}
							{
								JPanel pnlCWComp = new JPanel(new GridLayout(3, 1, 3, 3)); 
								pnlCenterWest.add(pnlCWComp, BorderLayout.CENTER); 
								{
									dteDateCreated = new JDateChooser(FncGlobal.getDateToday(), "MM/dd/yy");
									pnlCWComp.add(dteDateCreated); 
									dteDateCreated.setMaxSelectableDate(FncGlobal.getDateToday()); 

								}
								{
									lookupPCRType= new _JLookup(null, "Petty Cash Request", 1);
									pnlCWComp.add(lookupPCRType); 
									lookupPCRType.addLookupListener(new LookupListener() {

										@Override
										public void lookupPerformed(LookupEvent event) {
											Object [] data = ((_JLookup) event.getSource()).getDataSet();

											if (data != null) {
												pcr_type_id = (String) data[0];

												System.out.println("");
												System.out.println("Value of pcr_type_id: " + pcr_type_id);
											}

											modelPettyCashReq.setEditable(true);
											setDiv();

											if(pcr_type_id.equals("03")){ //CASH FUND LIQUIDATION
												cash_liq = true;
												addNewLiquidation(); 
												tblPettyCash.setEnabled(false);
												modelPettyCashReq.setEditable(false);
												lookupPCRLiq.setLookupSQL(getPCRLiq_no(co_id, user)); 
											}

										}
									});
								}
								{
									pnlCWComp.add(Box.createHorizontalBox()); //EXTRA SPACE
								}								
							}
						}
						{
							JPanel pnlCenterEast = new JPanel(new BorderLayout(3, 3)); 
							pnlCenter.add(pnlCenterEast, BorderLayout.EAST); 
							pnlCenterEast.setPreferredSize(new Dimension(350, 0));
							{
								JPanel pnlCELabel = new JPanel(new GridLayout(3, 1, 3, 3)); 
								pnlCenterEast.add(pnlCELabel, BorderLayout.WEST); 
								pnlCELabel.setPreferredSize(new Dimension(120, 0));
								{
									JLabel lblTransDate = new JLabel("Transaction Date: ", JLabel.TRAILING); 
									pnlCELabel.add(lblTransDate); 
								}
								{
									JLabel lblPayee = new JLabel("Payee: ", JLabel.TRAILING); 
									pnlCELabel.add(lblPayee); 
								}
								{
									JLabel lblDiv = new JLabel("Division: ", JLabel.TRAILING);
									pnlCELabel.add(lblDiv);
								}								
							}
							{
								JPanel pnlCEComp = new JPanel(new GridLayout(3, 1, 3, 3)); 
								pnlCenterEast.add(pnlCEComp, BorderLayout.CENTER); 
								{
									dteTransDate = new JDateChooser("MM/dd/yy", "##/##/##", '_');
									pnlCEComp.add(dteTransDate); 
								}
								{
									txtPayee = new JTextField(); 
									pnlCEComp.add(txtPayee); 
									txtPayee.setHorizontalAlignment(JTextField.CENTER);
									txtPayee.setEditable(false);
								}
								{
									txtDiv = new JTextField();
									pnlCEComp.add(txtDiv); 
									txtDiv.setHorizontalAlignment(JTextField.CENTER);
									txtDiv.setEditable(false);
								}								
							}
						}
					}
				}
			}
			{
				JPanel pnlMainCenter = new JPanel(new BorderLayout(5, 5)); 
				pnlMain.add(pnlMainCenter, BorderLayout.CENTER); 

				{
					JPanel pnlCenterTable = new JPanel(new BorderLayout(5, 5)); 
					pnlMainCenter.add(pnlCenterTable, BorderLayout.CENTER); 
					{
						scrollTable = new _JScrollPaneMain(); 
						pnlCenterTable.add(scrollTable, BorderLayout.CENTER); 
					}
					{
						modelPettyCashReq = new modelPettyCashRequest(); 
						tblPettyCash = new _JTableMain(modelPettyCashReq); 			
						tblPettyCash.hideColumns("Rec ID", "Acct. ID", "Proj. ID"); 
						tblPettyCash.getColumnModel().getColumn(0).setPreferredWidth(200);	
						tblPettyCash.getColumnModel().getColumn(3).setPreferredWidth(280);
						scrollTable.setViewportView(tblPettyCash);
						tblPettyCash.setSortable(false); 
						tblPettyCash.getColumnModel().getColumn(4).setCellEditor(modelPettyCashReq.new BigDecimalCellEditor());
						tblPettyCash.addMouseListener(this); 
						tblPettyCash.addKeyListener(new KeyListener() {

							@Override
							public void keyTyped(KeyEvent e) {
								if(e.getKeyCode() == KeyEvent.VK_ENTER) {
									computeTotal();
								}
							}

							@Override
							public void keyReleased(KeyEvent e) {
								if(e.getKeyCode() == KeyEvent.VK_ENTER) {
									computeTotal();
								}

							}

							@Override
							public void keyPressed(KeyEvent e) {
								if(e.getKeyCode() == KeyEvent.VK_ENTER) {
									computeTotal();
								} 
							}
						});
						{
							rowHeaderScroll = tblPettyCash.getRowHeader();
							rowHeaderScroll.setModel(new DefaultListModel());
							scrollTable.setRowHeaderView(rowHeaderScroll);
							scrollTable.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
						}
					}
					{
						scrollTableTotal = new _JScrollPaneTotal(scrollTable); 
						pnlCenterTable.add(scrollTableTotal, BorderLayout.SOUTH);
						scrollTableTotal.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						{
							modelPettyCashReqTotal = new modelPettyCashRequest(); 
							tblPettyCashTotal = new _JTableTotal(modelPettyCashReqTotal, tblPettyCash); 
							tblPettyCashTotal.setFont(dialog11Bold);
							scrollTableTotal.setViewportView(tblPettyCashTotal);
							scrollTableTotal.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, displayTableNavigation()); 
							tblPettyCashTotal.setTotalLabel(2);
							tblPettyCashTotal.hideColumns("Rec ID", "Acct. ID", "Proj. ID"); 
						}
					}
				}
				{
					pnlCenterSouth = new JPanel(new BorderLayout(5, 5)); 
					pnlMainCenter.add(pnlCenterSouth, BorderLayout.SOUTH); 
					pnlCenterSouth.setPreferredSize(new Dimension(0, 120)); 
					pnlCenterSouth.setBorder(components.JTBorderFactory.createTitleBorder("In Case of Liquidation"));

					{
						JPanel pnlCSWest = new JPanel (new BorderLayout(5, 5)); 
						pnlCenterSouth.add(pnlCSWest, BorderLayout.WEST);
						pnlCSWest.setPreferredSize(new Dimension(300, 0)); 
						{
							JPanel pnlCSWLabel = new JPanel(new GridLayout(2, 1, 3, 3)); 
							pnlCSWest.add(pnlCSWLabel, BorderLayout.WEST); 
							pnlCSWLabel.setPreferredSize(new Dimension(180, 0));
							{
								JLabel lblPCRLiqNo = new JLabel("PCR No. to Liquidate", JLabel.TRAILING); 
								pnlCSWLabel.add(lblPCRLiqNo); 
							}
							{
								JLabel lblCashReturned = new JLabel("Cash Returned", JLabel.TRAILING); 
								pnlCSWLabel.add(lblCashReturned); 
							}
						}
						{
							JPanel pnlCSWComp = new JPanel(new GridLayout(2, 1, 3, 3)); 
							pnlCSWest.add(pnlCSWComp, BorderLayout.CENTER); 
							{
								lookupPCRLiq = new _JLookup(null, "PCR Liquidation", 0); 
								pnlCSWComp.add(lookupPCRLiq); 
								lookupPCRLiq.setFont(new Font("Segoe UI", Font.BOLD, 12));
								lookupPCRLiq.addLookupListener(new LookupListener() {

									@Override
									public void lookupPerformed(LookupEvent event) {
										Object [] data = ((_JLookup)event.getSource()).getDataSet(); 

										if(data != null) {
											pcr_no_liq = (String) data[0]; 
											pcr_liq_amt = (BigDecimal) data[2];
										}
										displayPCR_details(modelPettyCashReq, rowHeaderScroll, modelPettyCashReqTotal, co_id, pcr_no_liq);
										ftxtTotalAmtOfCA.setValue(total_pcr_amt);										
										tblPettyCash.setEnabled(true);
										modelPettyCashReq.setEditable(true);
									}
								});
							}
							{
								ftxtCashReturned = new _JXFormattedTextField("0.00");
								pnlCSWComp.add(ftxtCashReturned); 
								ftxtCashReturned.setHorizontalAlignment(_JXFormattedTextField.CENTER);
								ftxtCashReturned.setFont(new Font("Segoe UI", Font.BOLD, 12));
								ftxtCashReturned.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							}
						}
					}
					{
						JPanel pnlCSCenter = new JPanel (new BorderLayout(5, 5)); 
						pnlCenterSouth.add(pnlCSCenter, BorderLayout.CENTER);
						{
							JPanel pnlCSELabel = new JPanel(new GridLayout(2, 1, 3, 3)); 
							pnlCSCenter.add(pnlCSELabel, BorderLayout.WEST); 
							pnlCSELabel.setPreferredSize(new Dimension(180, 0));
							{
								JLabel lblAmtToBeReimbursed = new JLabel("Amount to be Reimbursed: ", JLabel.TRAILING); 
								pnlCSELabel.add(lblAmtToBeReimbursed); 
							}
							{
								JLabel lblTotalAmtOfCA = new JLabel("Total Amount of CA:", JLabel.TRAILING); 
								pnlCSELabel.add(lblTotalAmtOfCA); 
							}
						}
						{
							JPanel pnlCSEComp = new JPanel(new GridLayout(2, 1, 3, 3)); 
							pnlCSCenter.add(pnlCSEComp, BorderLayout.CENTER); 
							{
								ftxtAmtToBeReimbursed = new _JXFormattedTextField("0.00");
								pnlCSEComp.add(ftxtAmtToBeReimbursed); 
								ftxtAmtToBeReimbursed.setHorizontalAlignment(_JXFormattedTextField.CENTER);
								ftxtAmtToBeReimbursed.setFont(new Font("Segoe UI", Font.BOLD, 12));
								ftxtAmtToBeReimbursed.setFormatterFactory(_JXFormattedTextField.DECIMAL);

							}
							{
								ftxtTotalAmtOfCA = new _JXFormattedTextField("0.00");
								pnlCSEComp.add(ftxtTotalAmtOfCA); 
								ftxtTotalAmtOfCA.setHorizontalAlignment(_JXFormattedTextField.CENTER);
								ftxtTotalAmtOfCA.setFont(new Font("Segoe UI", Font.BOLD, 12));
								ftxtTotalAmtOfCA.setFormatterFactory(_JXFormattedTextField.DECIMAL);
								ftxtTotalAmtOfCA.setEditable(false);

							}
						}
					}	
					{
						JPanel pnlExtra = new JPanel(new BorderLayout(5, 5)); 
						pnlCenterSouth.add(pnlExtra, BorderLayout.EAST); 
						pnlExtra.setPreferredSize(new Dimension(150, 0));
					}
					{
						JPanel pnlExtra = new JPanel(new BorderLayout(5, 5)); 
						pnlCenterSouth.add(pnlExtra, BorderLayout.SOUTH); 
						pnlExtra.setPreferredSize(new Dimension(0, 10));
					}
				}
			}
			{
				JPanel pnlMainSouth = new JPanel(new GridLayout(1, 7, 5, 5)); 
				pnlMain.add(pnlMainSouth, BorderLayout.SOUTH);
				pnlMainSouth.setPreferredSize(new Dimension(0, 50));
				{
					btnAddNew = new JButton("Add New");
					pnlMainSouth.add(btnAddNew);
					btnAddNew.setActionCommand(btnAddNew.getText());
					btnAddNew.addActionListener(this);
				}
				{
					btnEdit = new JButton("Edit");
					pnlMainSouth.add(btnEdit);
					btnEdit.setActionCommand(btnEdit.getText());
					btnEdit.addActionListener(this);
				}
				{
					btnSave = new JButton("Save");
					pnlMainSouth.add(btnSave);
					btnSave.setActionCommand(btnSave.getText());
					btnSave.addActionListener(this);
				}
				{
					btnPreview = new JButton("Preview");
					pnlMainSouth.add(btnPreview);
					btnPreview.setActionCommand(btnPreview.getText());
					btnPreview.addActionListener(this);
				}
				{
					btnPay = new JButton("Pay");
					pnlMainSouth.add(btnPay);
					btnPay.setActionCommand(btnPay.getText());
					btnPay.addActionListener(this);
				}
				{
					btnProcess = new JButton("Process");
					pnlMainSouth.add(btnProcess);
					btnProcess.setActionCommand(btnProcess.getText());
					btnProcess.addActionListener(this);
				}
				{
					btnCancel = new JButton("Cancel");
					pnlMainSouth.add(btnCancel);
					btnCancel.setActionCommand(btnCancel.getText());
					btnCancel.addActionListener(this);
				}
			}
		}

		initialize_components(); 

	}//XXX END OF INIT GUI

	private void initialize_components() {
		this.setComponentsEnabled(pnlMainNorth, false);
		lookupPCRNo.setEnabled(true);
		lookupPCRNo.setEditable(true);
		chkCurrYear.setEnabled(true);
		chkCurrYear.setSelected(true);
		this.setComponentsEnabled(pnlCenterSouth, false);
		lblCompany.setEnabled(true);
		lblPCRNo.setEnabled(true);
		lookupCompany.setEnabled(true);
		lookupCompany.setEditable(true);
		co_id = "01"; 
		process_id = "17"; 
		lookupCompany.setValue("ACERLAND REALTY CORPORATION"); //default value
		btnState(true, false, false, false, false, false, false, false, false);
		lookupPCRType.setLookupSQL(getPCR_type());
		tblPettyCash.setEnabled(false);
		tblPettyCashTotal.setEnabled(false);
		FncTables.clearTable(modelPettyCashReq);
		FncTables.clearTable(modelPettyCashReqTotal);
		user = UserInfo.EmployeeCode;
		company_logo = getCompanyLogo(co_id);
		dteDateCreated.getCalendarButton().setVisible(true);
		dteTransDate.getCalendarButton().setVisible(true);

		if(FncAcounting.EmpPettyCashCustodian(user, process_id)) {
			lookupPCRNo.setLookupSQL(getPCR_no_Custodian(co_id, curr_year));
		} else {
			lookupPCRNo.setLookupSQL(getPCR_no(co_id, user, curr_year));
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Add New")) {
			addNew();
		}

		if(e.getActionCommand().equals("Edit")) {

			lookupPCRNo.setEnabled(true);
			lookupPCRNo.setEditable(false);
			btnState(false, false, true, false, false, false, true, true, true);
			lookupPCRType.setEditable(false);
			tblPettyCash.setEnabled(true);
			modelPettyCashReq.setEditable(true);
			tblPettyCashTotal.setEditable(false);
			dteDateCreated.getCalendarButton().setVisible(true);
			dteTransDate.setEnabled(false);
			dteTransDate.getCalendarButton().setVisible(false);

		}

		if (e.getActionCommand().equals("Preview")) {
			preview();
		}

		if(e.getActionCommand().equals("Add Row")) {
			addRow();
		} else if (e.getActionCommand().equals("Remove Row")) {
			if (tblPettyCash.getSelectedRows().length == 1) {
				removeRow();
			} else {
				showWarningMessage( "Please select row to remove", "Remove");
			}
		}

		if(e.getActionCommand().equals("Cancel")) {
			this.setComponentsClear(pnlMainNorth);
			this.setComponentsClear(pnlCenterSouth);
			initialize_components();
		}

		if(e.getActionCommand().equals("Save")) {
			executeSave();

		}

		if(e.getActionCommand().equals("Pay")) {
			pay_process_PCR(pcr_status);
			displayPCR_header(co_id, pcr_no);
		}

		if(e.getActionCommand().equals("Process")) {
			pay_process_PCR(pcr_status);
			displayPCR_header(co_id, pcr_no);
		}
	}

	private void btnState(Boolean add, Boolean edit, Boolean save, Boolean preview, Boolean pay, Boolean process, Boolean cancel, Boolean AddRow, Boolean RemoveRow) {
		btnAddNew.setEnabled(add);
		btnEdit.setEnabled(edit);
		btnSave.setEnabled(save);
		btnPreview.setEnabled(preview);
		btnPay.setEnabled(pay);
		btnProcess.setEnabled(process);
		btnCancel.setEnabled(cancel);
		btnAddRow.setEnabled(AddRow);
		btnRemoveRow.setEnabled(RemoveRow);
	}

	private JPanel displayTableNavigation() {
		btnAddRow = new JButton(
				new ImageIcon(this.getClass().getClassLoader().getResource("Images/Science-Plus2-Math-icon.png")));
		btnAddRow.setActionCommand("Add Row");
		btnAddRow.setToolTipText("Add Row");
		btnAddRow.setEnabled(false);
		btnAddRow.addActionListener(this);

		btnRemoveRow = new JButton(
				new ImageIcon(this.getClass().getClassLoader().getResource("Images/Science-Minus2-Math-icon.png")));
		btnRemoveRow.setActionCommand("Remove Row");
		btnRemoveRow.setToolTipText("Remove Row");
		btnRemoveRow.setEnabled(false);
		btnRemoveRow.addActionListener(this);

		JPanel pnl = new JPanel(new GridLayout(1, 2));
		pnl.add(btnAddRow);
		pnl.add(btnRemoveRow);

		return pnl;
	}
	public void addNew() {

		this.setComponentsEnabled(pnlMainNorth, true);
		lookupCompany.setEditable(false);
		lookupPCRType.setEditable(true);
		createPCRTable(modelPettyCashReq, rowHeaderScroll);
		lookupPCRNo.setEnabled(false);
		txtPCRStatus.setText("ACTIVE"); //initial value
		txtPayee.setText(UserInfo.FullName);
		payee = getPayeeID(UserInfo.EmployeeCode); 
		txtDiv.setText(String.format("%s / %s", UserInfo.Department_Alias, UserInfo.Division_Alias));
		div_id = UserInfo.Division; 
		tblPettyCash.setEnabled(true);
		modelPettyCashReq.setEditable(false);
		tblPettyCashTotal.setEnabled(true);
		tblPettyCashTotal.setEditable(false); 
		dteTransDate.setEnabled(true); 
		dteDateCreated.setDate(FncGlobal.getDateToday());
		dteDateCreated.setMaxSelectableDate(FncGlobal.getDateToday()); 
		dteTransDate.setEnabled(false); 
		dteTransDate.getCalendarButton().setVisible(false);
		chkCurrYear.setEnabled(false);
		chkCurrYear.setSelected(false);
		btnState(false, false, true, false, false, false, true, true, true); 
	}

	public void addNewLiquidation() {
		this.setComponentsEnabled(pnlCenterSouth, true);
		this.setCompEditable(pnlCenterSouth, true);		
		ftxtCashReturned.setEnabled(false);
		ftxtAmtToBeReimbursed.setEnabled(false);
		ftxtTotalAmtOfCA.setEditable(false);
		pcr_type_id = "03"; //Req. Type ID for Cash Fund Liquidation
		lookupPCRType.setEditable(false);
		modelPettyCashReq.setEditable(true);
		setDiv();
	}

	public static String getPCR_no(String co_id, String user, String curr_year) {
		String SQL = "SELECT a.pcr_no AS \"PCR No\"\n"
				+ ", b.pc_req_type_desc as \"Description\"\n"
				+ ", c.status_desc AS \"Status\"\n"
				+ ", a.pcr_type AS \"PCR Type ID\" \n"
				+ "FROM rf_petty_cash_header a\n"
				+ "LEFT JOIN mf_petty_cash_req_type b ON b.pc_req_id = a.pcr_type AND b.status_id = 'A'\n"
				+ "LEFT JOIN mf_record_status c ON c.status_id = a.status_id \n"
				+ "WHERE a.rec_status = 'A'\n"
				+ "AND a.co_id = '"+co_id+"' \n"
				+ "AND a.created_by = '"+user+"' \n"
				+ "AND ('"+curr_year+"' IS NULL OR '"+curr_year+"' = '' OR date_part('year', a.date_created::DATE)::VARCHAR = '"+curr_year+"')\n"
				+ "ORDER by a.date_created DESC";	

		System.out.println("getPCR_no: "+ SQL);

		return SQL; 
	}

	public static String getPCR_no_Custodian(String co_id, String curr_year) {
		String SQL = "SELECT a.pcr_no AS \"PCR No\"\n"
				+ ", b.pc_req_type_desc as \"Description\"\n"
				+ ", c.status_desc AS \"Status\"\n"
				+ ", a.pcr_type AS \"PCR Type ID\" \n"
				+ "FROM rf_petty_cash_header a\n"
				+ "LEFT JOIN mf_petty_cash_req_type b ON b.pc_req_id = a.pcr_type AND b.status_id = 'A'\n"
				+ "LEFT JOIN mf_record_status c ON c.status_id = a.status_id \n"
				+ "WHERE a.rec_status = 'A'\n"
				+ "AND a.co_id = '"+co_id+"' \n"
				+ "AND ('"+curr_year+"' IS NULL OR '"+curr_year+"' = '' OR date_part('year', a.date_created::DATE)::VARCHAR = '"+curr_year+"')\n"
				+ "ORDER by a.date_created DESC";	

		System.out.println("getPCR_no_Custodian: "+ SQL);

		return SQL; 
	}

	public static String getPCRLiq_no(String co_id, String user) {
		String SQL = "SELECT a.pcr_no AS \"PCR No.\"\n"
				+ ", fn_get_entity_name(a.payee) as \"Payee\"\n"
				+ ", a.pcr_total_amt AS \"Amount\"\n"
				+ ", b.status_desc AS \"Status\"\n"
				+ "FROM rf_petty_cash_header a\n"
				+ "LEFT JOIN mf_record_status b ON b.status_id = a.status_id AND a.rec_status = 'A'\n"
				+ "WHERE a.status_id = 'B'\n" 
				+ "AND a.pcr_type = '01'\n" 
				+ "AND NOT a.is_ca_liquidated \n"
				+ "AND a.co_id = '"+co_id+"' \n"
				+ "AND a.created_by = '"+user+"' \n"
				+ "AND a.rec_status = 'A'";	

		System.out.println("getPCRLiq_no: "+ SQL);

		return SQL; 
	}

	public static String getPCR_type() {
		String SQL = "SELECT pc_req_id AS \"PCR ID\"\n"
				+ ", pc_req_type_desc AS \"Description\"\n"
				+ ", status_id AS \"STATUS\"\n"
				+ "FROM mf_petty_cash_req_type\n"
				+ "WHERE status_id = 'A'";

		System.out.println("getPCR_type: "+ SQL);

		return SQL; 
	}

	public static String getPCR_type_id(String pcr_type_desc) {
		pgSelect db = new pgSelect(); 
		String pcr_type_id = ""; 

		String SQL = "SELECT pc_req_id\n"
				+ "FROM mf_petty_cash_req_type \n"
				+ "WHERE TRIM(pc_req_type_desc) = '"+pcr_type_desc+"'\n"
				+ "AND status_id = 'A';";

		System.out.println("getPCR_type_ID: "+ SQL);
		db.select(SQL);

		if(db.isNotNull()) {
			pcr_type_id = (String) db.getResult()[0][0];
		} else {
			pcr_type_id = null; 
		}

		return pcr_type_id;	
	}

	public static String getProject() {
		String SQL = "SELECT proj_id AS \"PROJECT ID\"\n"
				+ ", proj_name AS \"PROJECT NAME\"\n"
				+ ", proj_alias AS \"PROJ ALIAS\"\n"
				+ "FROM mf_project\n"
				+ "WHERE status_id = 'A';";

		System.out.println("getProject: "+ SQL);

		return SQL; 
	}

	public static String getProjCostID() {
		String SQL = "SELECT a.proj_cost_accnt_desc AS \"Description\"\n"
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
				+ "				AND a.rec_status = 'A')";

		System.out.println("getProjCostID: "+ SQL);	

		return SQL; 
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


	public void createPCRTable(DefaultTableModel mainModel, JList<Integer> rowHeader) {
		FncTables.clearTable(mainModel);
		DefaultListModel<Integer> listModel = new DefaultListModel<Integer>(); 
		rowHeader.setModel(listModel);

		String sql = "Select '', '', '', '', '', '', '', 0.00 union all \n"
				+ "Select '', '', '', '', '', '', '', 0.00 union all \n"
				+ "Select '', '', '', '', '', '', '', 0.00 union all \n"
				+ "Select '', '', '', '', '', '', '', 0.00"; 

		pgSelect db = new pgSelect();
		db.select(sql);
		if (db.isNotNull()) {
			for (int x = 0; x < db.getRowCount(); x++) {
				// Adding of row in table
				mainModel.addRow(db.getResult()[x]);
				listModel.addElement(mainModel.getRowCount());
			}
		}
		modelPettyCashReqTotal.addRow(new Object [] {null, null, "Total", null, null, null, null, new BigDecimal(0.00)}); 

	}

	private void addRow() {
		modelPettyCashReq.addRow(
				new Object[] { null, null, "", "", null, "", "", 0.00 });
		((DefaultListModel<Integer>) rowHeaderScroll.getModel()).addElement(modelPettyCashReq.getRowCount());
		setDiv();
	}

	private void removeRow() {
		modelPettyCashReq.removeRow(tblPettyCash.getSelectedRow());
		rowHeaderScroll.setModel(new DefaultListModel<Integer>());
		for (int x = 0; x < modelPettyCashReq.getRowCount(); x++) {

			((DefaultListModel<Integer>) rowHeaderScroll.getModel()).addElement(x + 1);
		}
		computeTotal();
	}

	private void AddPCRDetails() {
		int selected_row = tblPettyCash.convertRowIndexToModel(tblPettyCash.getSelectedRow());
		int selected_column = tblPettyCash.convertColumnIndexToModel(tblPettyCash.getSelectedColumn()); 

		System.out.println("Selected Row: " + selected_row);
		System.out.println("Selected Column: " + selected_column);

		if (selected_row == -1 || selected_column == -1) {
			JOptionPane.showMessageDialog(FncGlobal.homeMDI, "Please select a valid row and column.");
			return;
		}

		Integer x[] = { 0, 1, 2, 3, 4, 5, 6, 7};
		String sql[] = { "", "", getProjCostID(), "", getProject(), "", "", "" };
		String lookup_name[] = { "", "", "Petty Cash Account", "", "Project", "", "", "" };

		if (selected_column >= 0 && selected_column < x.length && selected_column == x[selected_column]) {
			_JLookupTable dlg = new _JLookupTable(FncGlobal.homeMDI, null, lookup_name[selected_column], sql[selected_column], false); 
			dlg.setLocationRelativeTo(FncGlobal.homeMDI);
			dlg.setVisible(true);

			Object [] data = dlg.getReturnDataSet(); 

			if(data != null) {
				if(selected_column == 2) { // Acct. Description 
					modelPettyCashReq.setValueAt(data[0], selected_row, selected_column);
					modelPettyCashReq.setValueAt(data[1], selected_row, selected_column -1); 
					
				}else if (selected_column == 4) { // Project
					modelPettyCashReq.setValueAt(data[2], selected_row, selected_column);
					modelPettyCashReq.setValueAt(data[0], selected_row, selected_column -1); 
				}
			} 
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int selected_column = tblPettyCash.convertColumnIndexToModel(tblPettyCash.getSelectedColumn()); 

		System.out.println("Value ng clicked count: " + e.getClickCount());
		System.out.println("Value ng selected_column: " + selected_column);

		if(!lookupPCRType.getText().equals("")) {

			if((selected_column == 2 || selected_column == 4) && (e.getClickCount() >= 2)) {
				AddPCRDetails();
			}
		} else {
			showWarningMessage("Please select request type first.", "Message");
		}

	}

	private void setDiv() {

		String pcr_req_type = lookupPCRType.getValue(); 
		div_id = UserInfo.Department; 

		if (pcr_req_type.equals("")) {
		} else {
			int rw = tblPettyCash.getRowCount();
			int x = 0;

			while (x < rw) {
				modelPettyCashReq.setValueAt(div_id, x, 5);
				x++;
			}
		}
	}

	public void computeTotal() {
		BigDecimal totalAmt = new BigDecimal("0.00"); 
		BigDecimal pcr_liq_amt = (BigDecimal) ftxtTotalAmtOfCA.getValued(); 

		for (int x = 0; x < modelPettyCashReq.getRowCount(); x++) {
			BigDecimal pcr_amt = (BigDecimal) modelPettyCashReq.getValueAt(x, 7);

			try {
				totalAmt = totalAmt.add(pcr_amt); 
			} catch (Exception e) {

			}
		}

		modelPettyCashReqTotal.setValueAt(totalAmt, 0, 7);

		// Checker if liquidation requires cash return or amount to be reimbursed
		if(pcr_type_id.equals("03")) {
			
			BigDecimal totalPCRAmt = (BigDecimal) modelPettyCashReqTotal.getValueAt(0, 7);

			//Liquidation Amount is less than CA Amount
			if(totalAmt.compareTo(pcr_liq_amt) < 0) {
				BigDecimal cashReturnAmt_should_be = pcr_liq_amt.subtract(totalPCRAmt);
				ftxtCashReturned.setValue(cashReturnAmt_should_be);
				ftxtCashReturned.setEnabled(true);
				ftxtCashReturned.setEditable(false);
				ftxtAmtToBeReimbursed.setEnabled(false);
				ftxtAmtToBeReimbursed.setValue(BigDecimal.ZERO);

				//Liquidation Amount is greater than CA Amount
			} else if(totalAmt.compareTo(pcr_liq_amt) > 0) {
				BigDecimal reimbursementAmt_should_be = totalPCRAmt.subtract(pcr_liq_amt); 
				ftxtAmtToBeReimbursed.setValue(reimbursementAmt_should_be);
				ftxtCashReturned.setEnabled(false);
				ftxtAmtToBeReimbursed.setEnabled(true);
				ftxtAmtToBeReimbursed.setEditable(false);
				ftxtCashReturned.setValue(BigDecimal.ZERO);

			} else { //Liquidation Amount is equal CA Amount
				ftxtCashReturned.setEnabled(false);
				ftxtCashReturned.setEditable(false);
				ftxtCashReturned.setValue(BigDecimal.ZERO);
				ftxtAmtToBeReimbursed.setEnabled(false);
				ftxtAmtToBeReimbursed.setEditable(false);
				ftxtAmtToBeReimbursed.setValue(BigDecimal.ZERO);
			}
		}
	}

	private Boolean checkPCRAmount() {
		boolean isValid = false; 

		int rowCount = tblPettyCash.getModel().getRowCount();  
		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) { 
			String acctId = modelPettyCashReq.getValueAt(rowIndex, 2).toString().trim();  

			// Only proceed if the account ID is not empty
			if (!acctId.isEmpty()) {

				try {

					String pcrAmtStr = modelPettyCashReq.getValueAt(rowIndex, 7).toString().trim();

					if (!pcrAmtStr.isEmpty()) {
						double pcrAmt = Double.parseDouble(pcrAmtStr);

						// Check if the Petty Cash amount is greater than 0
						if (pcrAmt > 0) {
							System.out.println("Petty Cash Amount is Greater than 0!");
							isValid = true;  
						} else {
							isValid = false; 
						}
					} else {
						System.out.println("Petty Cash Amount is empty or invalid.");
						isValid = false;  
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid PCR amount format at row " + rowIndex + ": " + e.getMessage());
					isValid = false;  // Invalid if parsing fails
				}
			}
		}
		return isValid;
	}

	private Boolean checkAcctID_ifcomplete() {
		boolean isComplete = true; 

		int rowCount = tblPettyCash.getModel().getRowCount(); 
		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {

			try {
				// Get the Petty Cash amount and check if it's greater than 0
				Double pcrAmt = Double.parseDouble(modelPettyCashReq.getValueAt(rowIndex, 7).toString().trim());

				if (pcrAmt > 0) {
					// Get the account ID from the table and check if it's empty
					String acctId = modelPettyCashReq.getValueAt(rowIndex, 2).toString().trim();
					if (acctId.isEmpty()) {
						isComplete = false; 
						break;  		              
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid Petty Cash amount at row " + rowIndex + ": " + e.getMessage());
				isComplete = false; 
				break;
			}
		}

		return isComplete;
	}

	private Boolean isValidDate() {
		Boolean isValid = true; 

		Date req_date = dteDateCreated.getDate();
		Date curr_date = FncGlobal.getDateToday(); 

		if(req_date.after(curr_date)) {
			isValid = false;
		} else {
			isValid = true; 
		}

		return isValid;
	}

//	private void validateCashReturned(BigDecimal pcr_liq_amt) {
//
//		System.out.println("");
//		System.out.println("Value of Cash Adv Amount to Liquidate: " + pcr_liq_amt);
//		System.out.println("Value of Cash Returned: " + ftxtCashReturned.getValued());
//		System.out.println("Value of PCR Liquidation: " + totalPCRAmt);
//		System.out.println(pcr_liq_amt + " = " + totalPCRAmt.add(cashRetamt));
//		System.out.println("");
//
//		BigDecimal cashReturnAmt_should_be = pcr_liq_amt.subtract(totalPCRAmt); 
//		String formattedValue = String.format("%,.2f", cashReturnAmt_should_be);
//
//		if((totalPCRAmt.add(cashRetamt)).compareTo(pcr_liq_amt) < 0) {
//			showWarningMessage("Insufficient cash returned amount. \nThe amount shoud be " + formattedValue , "Liquidation");
//			return; 
//		} else if ((totalPCRAmt.add(cashRetamt)).compareTo(pcr_liq_amt) > 0){
//			showWarningMessage("Excess cash returned amount. \nThe amount shoud be " + formattedValue, "Liquidation");
//			return; 
//		} else {
//			// If total PCR amount equals amount of CA , ask for confirmation to save
//			confirmAndSaveRequest();
//		}
//	}
//
//	private void validateReimbursement(BigDecimal pcr_liq_amt) {
//
//		System.out.println("");
//		System.out.println("Value of Cash Adv Amount to Liquidate: " + pcr_liq_amt);
//		System.out.println("Value of Amount to be Reimburse: " + ftxtAmtToBeReimbursed.getValued());
//		System.out.println("Value of PCR Liquidation: " + totalPCRAmt);
//		System.out.println(pcr_liq_amt.add(reimbursementAmt) + " = " + totalPCRAmt);
//		System.out.println("");
//
//		BigDecimal reimbursementAmt_should_be = totalPCRAmt.subtract(pcr_liq_amt); 
//		String formattedValue = String.format("%,.2f", reimbursementAmt_should_be);
//
//
//		if((pcr_liq_amt.add(reimbursementAmt)).compareTo(totalPCRAmt) < 0) {
//			showWarningMessage("Insufficient reimbursement amount. \nThe amount shoud be " + formattedValue, "Liquidation");
//			return;
//		} else if ((pcr_liq_amt.add(reimbursementAmt)).compareTo(totalPCRAmt) > 0) {
//			showWarningMessage("Excess reimbursement amount. \nThe amount shoud be " + formattedValue, "Liquidation");
//			return;
//		}	else {
//			// If total PCR amount equals amount of CA , ask for confirmation to save
//			confirmAndSaveRequest();
//		}
//	}

	private static String getPayeeID(String emp_code){
		pgSelect db = new pgSelect();
		db.select("SELECT entity_id\n"
				+ "FROM rf_employee\n"
				+ "WHERE emp_code = '"+emp_code+"'\n"
				+ "AND status_id = 'A'; ");

		return (String) db.getResult()[0][0];
	}

	public void executeSave() {

		// Auto compute total before saving
		computeTotal();

		// Validate request date 
		if(!isValidDate()) {
			showWarningMessage("Request date is invalid.", "Invalid Date");
			return;
		}

		// Check if an account ID is selected
		if (!checkAcctID_ifcomplete()) {
			showWarningMessage("Please select account ID.", "Account ID");
			return; 
		}

		// Check if the PCR amount is valid
		if (!checkPCRAmount()) {
			showWarningMessage("Amount must be greater than zero.", "Petty Cash Amount");
			return;
		}

		confirmAndSaveRequest();
	}

	public void saveRequest() {

		pcr_no = lookupPCRNo.getValue();
		System.out.println("Value of PCR No: " + pcr_no);

		//SAVING OF NEW DRF
		if (pcr_no == null) { 

			pcr_no =  savePCR(co_id, modelPettyCashReq, pcr_no, div_id, payee, cash_liq, pcr_no_liq); 
			System.out.println("Value of pcr_no: " + pcr_no);
			lookupPCRNo.setValue(pcr_no);
			JOptionPane.showMessageDialog(getContentPane(), lookupPCRType.getValue().trim() + " request saved.", "Information",
					JOptionPane.INFORMATION_MESSAGE);
			displayPCR_details(modelPettyCashReq, rowHeaderScroll, modelPettyCashReqTotal, co_id, pcr_no);	
		}

		else { //SAVING FROM EDIT 
			pcr_type_id = getPCR_type_id(lookupPCRType.getValue().trim());
			payee = getPayeeID(UserInfo.EmployeeCode);
			div_id = UserInfo.Department; 
			savePCR(co_id, modelPettyCashReq, pcr_no, div_id, payee, cash_liq, pcr_no_liq); 
			displayPCR_details(modelPettyCashReq, rowHeaderScroll, modelPettyCashReqTotal, co_id, pcr_no);
			JOptionPane.showMessageDialog(getContentPane(), "Petty cash request updated.", "Information",
					JOptionPane.INFORMATION_MESSAGE);
		}

		this.setComponentsEditable(pnlMainNorth, false);
		btnState(false, true, false, true, false, false, true, false, false);

		lookupPCRNo.setEnabled(true);
		dteDateCreated.getCalendarButton().setVisible(false);
		dteTransDate.getCalendarButton().setVisible(false);
		tblPettyCash.setEnabled(false);
		modelPettyCashReq.setEditable(false);
		lookupPCRLiq.setEnabled(false);
		this.setCompEditable(pnlCenterSouth, false);

	}

	public String savePCR(String co_id, modelPettyCashRequest model, String pcr_no, String div_id, String payee, Boolean cash_liq, String pcr_no_liq) {
		// FOR PCR DETAIL
		ArrayList<String> listAcctIDs = new ArrayList<String>();
		ArrayList<String> listProjIDs = new ArrayList<String>();
		ArrayList<String> listDivIDs = new ArrayList<String>();
		ArrayList<String> listPurposeOfExpenditure = new ArrayList<String>();
		ArrayList<BigDecimal> listPCRAmount = new ArrayList<BigDecimal>(); 

		for(int x = 0; x < model.getRowCount(); x++){

			String account_id = (String) model.getValueAt(x, 2);

			if (account_id != null && !account_id.isEmpty()) {

				String acct_id =  (String) model.getValueAt(x, 1);
				String proj_id = (String) model.getValueAt(x, 3);
				String div_ID = (String) model.getValueAt(x, 5);
				String purpose_of_exp = (String) model.getValueAt(x, 6); 
				BigDecimal amt = (BigDecimal) model.getValueAt(x, 7);

				listAcctIDs.add(String.format("'%s'", acct_id));
				listProjIDs.add(String.format("'%s'", proj_id));
				listDivIDs.add(String.format("'%s'", div_ID));
				listPurposeOfExpenditure.add(String.format("'%s'", purpose_of_exp.trim().replace("'", "''")));
				listPCRAmount.add(amt);
			}
		}

		String acct_id = listAcctIDs.toString().replaceAll("\\[|\\]", "");
		String proj_id = listProjIDs.toString().replaceAll("\\[|\\]", "");
		String div_IDs = listDivIDs.toString().replaceAll("\\[|\\]", "");
		String purpose_of_exp = listPurposeOfExpenditure.toString().replaceAll("\\[|\\]", "");
		String amt = listPCRAmount.toString().replaceAll("\\[|\\]", "");

		BigDecimal totalPCRAmt = (BigDecimal) modelPettyCashReqTotal.getValueAt(0, 7);

		System.out.println("");
		System.out.println("PCR Amt: "+ amt);
		System.out.println("PCR No: "+ pcr_no);
		System.out.println("Total PCR Amt: "+ totalPCRAmt);

		try {

			String SQL = "Select fn_save_petty_cash_req('"+pcr_no+"', '"+co_id+"', '"+dateFormat.format(dteDateCreated.getDate())+"' \n"
					+ ", '"+pcr_type_id+"', '"+payee+"', '"+div_id+"', "+totalPCRAmt+", "+cash_liq+", '"+pcr_no_liq+"','"+user+"', ARRAY["+acct_id+"]::VARCHAR[] \n"
					+ ", ARRAY["+proj_id+"]::VARCHAR[], ARRAY["+div_IDs+"]::VARCHAR[], ARRAY["+purpose_of_exp+"]::VARCHAR[], ARRAY["+amt+"]::NUMERIC[] \n"
					+ ", "+ftxtAmtToBeReimbursed.getValue()+", "+ftxtCashReturned.getValue()+", "+totalPCRAmt+");"; 

			pgSelect db = new pgSelect(); 
			db.select(SQL);

			System.out.println(SQL);

			if(db.isNotNull()) {
				pcr_no =  (String) db.getResult()[0][0];

			}		
		} catch (Exception e) {
			showWarningMessage("Something went wrong.", "Save");
		}

		FncSystem.out("PCR No: ", pcr_no);
		return pcr_no;
	}

	public void displayPCR_header(String co_id, String pcr_no) {
		pgSelect db = new pgSelect();

		String SQL = "SELECT fn_get_company_name(a.co_id) as company\n"
				+ ", c.status_desc as status\n"
				+ ", a.pcr_date_created::DATE\n"
				+ ", b.pc_req_type_desc\n"
				+ ", COALESCE(a.pcr_date_processed, a.pcr_date_paid) as trans_date"
				+ ", fn_get_entity_name(a.payee) as payee\n"
				+ ", FORMAT('%s / %s', fn_get_div_alias(a.div_id), fn_get_exec_ofc_alias_div(a.div_id)) as division\n"
				+ ", a.is_ca_liquidated \n"
				+ ", a.liquidated_pcr_no\n"
				+ ", a.cash_returned \n"
				+ ", a.amount_to_be_reimbursed \n"
				+ ", a.liquidated_pcr_total_amt as ca_total_amt \n"
				+ "FROM rf_petty_cash_header a\n"
				+ "LEFT JOIN mf_petty_cash_req_type b ON b.pc_req_id = a.pcr_type AND b.status_id = 'A'\n"
				+ "LEFT JOIN mf_record_status c ON c.status_id = a.status_id\n"
				+ "WHERE a.co_id = '"+co_id+"'\n"
				+ "AND a.pcr_no = '"+pcr_no+"'\n"
				+ "AND a.rec_status = 'A'; ";

		db.select(SQL);

		System.out.println("displayPCR_header: "+ SQL);	

		if(db.isNotNull()) {
			lookupCompany.setValue((String) db.getResult()[0][0]); 
			txtPCRStatus.setText((String) db.getResult()[0][1]);
			dteDateCreated.setDate((Date) db.getResult()[0][2]);
			lookupPCRType.setValue((String) db.getResult()[0][3]);
			dteTransDate.setDate((Date) db.getResult()[0][4]);
			txtPayee.setText((String) db.getResult()[0][5]);
			txtDiv.setText((String) db.getResult()[0][6]);
			lookupPCRLiq.setValue((String) db.getResult()[0][8]);
			ftxtCashReturned.setValue((BigDecimal) db.getResult()[0][9]); 
			ftxtAmtToBeReimbursed.setValue((BigDecimal) db.getResult()[0][10]); 
			ftxtTotalAmtOfCA.setValue((BigDecimal) db.getResult()[0][11]); 
		}

	}

	public static void displayPCR_details(modelPettyCashRequest model, JList <Integer> rowHeader, modelPettyCashRequest modelTotal,
			String co_id, String pcr_no) {

		FncTables.clearTable(model);// 
		DefaultListModel <Integer> listModel = new DefaultListModel<>();
		rowHeader.setModel(listModel);

		String sql = "SELECT a.rec_id \n"
				+ ", a.pcr_acct_id\n"
				+ ", b.proj_cost_accnt_desc\n"
				+ ", a.proj_id\n"
				+ ", fn_get_proj_alias(a.proj_id)\n"
				+ ", a.div_id\n"
				+ ", a.nature_or_purpose_of_expenditure\n"
				+ ", a.amount\n"
				+ "FROM rf_petty_cash_detail a\n"
				+ "LEFT JOIN mf_project_cost_accnts b ON b.proj_cost_accnt_id = a.pcr_acct_id\n"
				+ "WHERE a.status_id = 'A'"
				+ "AND a.rec_status = 'A'"
				+ "AND pcr_no = '"+pcr_no+"'\n"
				+ "AND co_id = '"+co_id+"'\n"
				+ "ORDER by line_no; ";

		FncSystem.out("Petty Cash Details", sql);

		pgSelect db = new pgSelect();
		db.select(sql);
		if (db.isNotNull()) {
			for (int x = 0; x < db.getRowCount(); x++) {
				model.addRow(db.getResult()[x]);
				listModel.addElement(model.getRowCount());
			}

			totalPCR(model, modelTotal);
		}

	}

	public static void totalPCR(DefaultTableModel model, DefaultTableModel modelTotal) {
		FncTables.clearTable(modelTotal);

		total_pcr_amt = new BigDecimal(0.00); 

		for (int x = 0; x < model.getRowCount(); x++) {

			try {
				total_pcr_amt = total_pcr_amt.add((BigDecimal) model.getValueAt(x, 7));
			} catch (Exception e) {
				total_pcr_amt = total_pcr_amt.add(new BigDecimal(0.00));
			} 
		}

		modelTotal.addRow(new Object [] {null, null, "Total", null, null, null, null, total_pcr_amt}); 

	}

	private void setCompEnabled(JPanel panel) {
		this.setComponentsEnabled(panel, true);
	}

	private void setCompEditable(JPanel panel, Boolean editable) {
		this.setComponentsEditable(panel, editable);
	}

	public static Boolean isToPay(String pcr_type) {

		Boolean isToPay = false;

		String SQL = "Select EXISTS (\n"
				+ "	Select 1 FROM mf_petty_cash_req_type a\n"
				+ " LEFT JOIN rf_petty_cash_header b ON b.pcr_type = a.pc_req_id\n"
				+ "	WHERE a.pc_req_id IN ('01', '02', '04')\n"
				+ "	AND a.status_id = 'A'\n"
				+ "	AND a.pc_req_id = '"+pcr_type+"'\n"
				+ "	AND b.status_id = 'A' \n"
				+ ")";

		pgSelect db = new pgSelect();
		db.select(SQL);

		if (db.isNotNull()) {
			isToPay = (Boolean) db.getResult()[0][0];
		}

		System.out.println("Is to pay? " + isToPay);

		return isToPay;
	}

	private static Boolean isCustodianRequest(String co_id, String pcr_no, String user){

		boolean isCustodianRequest = false; 

		pgSelect db = new pgSelect();

		String sql = "SELECT * FROM rf_petty_cash_header \n"
				+ "WHERE co_id = '"+co_id+"' \n"
				+ "AND pcr_no = '"+pcr_no+"'\n"
				+ "AND created_by = '"+user+"'\n"
				+ "AND rec_status = 'A'; "; 
		db.select(sql); 

		System.out.println("SQL-isSameUser: "+ sql);

		if (db.isNotNull()) {
			isCustodianRequest = true; 
		} else {
			isCustodianRequest = false; 
		}

		System.out.println("Is same user? :" + isCustodianRequest);

		return isCustodianRequest; 
	}

	private static String getCurrentYear() {
		pgSelect db = new pgSelect();
		db.select("SELECT EXTRACT(YEAR FROM now())::VARCHAR");

		if (db.getResult() != null) {
			return db.Result[0][0].toString();
		} else {
			return String.format("%s", getCurrentYear());
		}
	}

	public void preview() {

		Map<String, Object> mapParameters = new HashMap<String, Object>();
		mapParameters.put("co_id", co_id);
		mapParameters.put("logo", this.getClass().getClassLoader().getResourceAsStream("Images/" + company_logo));
		mapParameters.put("user", UserInfo.FullName);
		mapParameters.put("pcr_no", pcr_no);

		System.out.println("");
		System.out.println("Value of co_id:" +  co_id);
		System.out.println("Value of logo:" +  company_logo);
		System.out.println("Value of user:" +  UserInfo.FullName);
		System.out.println("Value of pcr_no:" +  pcr_no);
		System.out.println("Request Type: " +  lookupPCRType.getText().trim());
		

		if(lookupPCRType.getText().trim().equals("Cash Fund Advance")) {
			FncReport.generateReport("/Reports/rptPettyCashRequest_with_Consent.jasper", title, mapParameters);
		} else {
			FncReport.generateReport("/Reports/rptPettyCashRequest.jasper", title, mapParameters);
		}
	}

	private void pay_process_PCR(String pcr_status) {

		if (JOptionPane.showConfirmDialog(getContentPane(), "Are you sure to pay / process this request?", "Confirmation",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

			// Password validation for Custodian
			dlg_CR_PW_Entry pw = new dlg_CR_PW_Entry(FncGlobal.homeMDI, "Password");
			pw.setLocationRelativeTo(null);
			pw.setVisible(true);

			String pw_entered = pw.getPassword();

			if(FncGlobal.connectionPassword.equals(pw_entered)) {

				pgUpdate db = new pgUpdate();
				String query = "";

				System.out.println("Value of pcr_type_id: " + pcr_type_id);

				// Applicable to Cash Advance, Reimbursement and Set-Up/Replenishment Requests 
				if (!pcr_type_id.equals("03") && !pcr_type_id.equals("05")) {
					if(pcr_status.equals("ACTIVE")) {
						System.out.println("TO PAY PCR!");
						query = "UPDATE rf_petty_cash_header \n"
								+ "SET status_id = 'B'\n"
								+ ", pcr_date_paid = '"+dateFormat.format(dteTransDate.getDate())+"' \n"
								+ ", pcr_paid_by = '"+user+"'\n"
								+ "WHERE co_id = '"+co_id+"'\n"
								+ "AND pcr_no = '"+pcr_no+"'\n"
								+ "AND rec_status = 'A';"; 

						pcr_status = "PAID";

					} else { // To process PAID - Set-Up/Replenishment Request

						query = "UPDATE rf_petty_cash_header \n"
								+ "SET status_id = 'G'\n"
								+ ", pcr_date_processed = '"+dateFormat.format(dteTransDate.getDate())+"' \n"
								+ ", pcr_processed_by = '"+user+"'\n"
								+ "WHERE co_id = '"+co_id+"'\n"
								+ "AND pcr_no = '"+pcr_no+"'\n"
								+ "AND rec_status = 'A';"; 

						pcr_status = "PROCESSED";
					}

				} else { // Applicable to Cash Fund Liquidation & Closing Request 	

					query = "UPDATE rf_petty_cash_header \n"
							+ "SET status_id = 'G'\n"
							+ ", pcr_date_processed = '"+dateFormat.format(dteTransDate.getDate())+"' \n"
							+ ", pcr_processed_by = '"+user+"'\n"
							+ "WHERE co_id = '"+co_id+"'\n"
							+ "AND pcr_no = '"+pcr_no+"'\n"
							+ "AND rec_status = 'A';"; 

					pcr_status = "PROCESSED";
				}

				db.executeUpdate(query, true, true);

				JOptionPane.showMessageDialog(getContentPane(), "PCR No. " + pcr_no + " was successfully " + pcr_status + "."  , "Information",
						JOptionPane.INFORMATION_MESSAGE);

				btnState(false, false, false, true, false, false, true, false, false);

			} else {
				showWarningMessage("Please input correct password.", "Incorrect Password");
				return; 
			}
		}
	}

	private void showWarningMessage(String message, String title) {
		JOptionPane.showMessageDialog(getContentPane(), message, title, JOptionPane.WARNING_MESSAGE);
	}

	private void confirmAndSaveRequest() {
		int response = JOptionPane.showConfirmDialog(getContentPane(), 
				"Are all entries correct?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			saveRequest();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
