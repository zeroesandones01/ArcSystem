/**
 * 
 */
package Transaction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import Database.pgSelect;
import FormattedTextField._JXFormattedTextField;
import Functions.FncGlobal;
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
import tablemodel.modelDRF_particulars;
import tablemodel.modelDRF_particulars_total;
import tablemodel.modelPettyCashRequest;
import tablemodel.modelPettyCashRequest_total;

/**
 * @author Monique Boriga
 */
public class PettyCash extends _JInternalFrame implements _GUI, ActionListener, MouseListener {

	private static final long serialVersionUID = -4306362386582966144L;

	private static String title = "PETTY CASH";

	private JPanel pnlMain;

	private JButton btnAddNew;
	private JButton btnEdit;
	private JButton btnSave;
	private JButton btnPreview;
	private JButton btnPay;
	private JButton btnProcess;
	private JButton btnCancel;

	Border lineBorder = BorderFactory.createLineBorder(Color.GRAY);
	protected static Font dialog11Bold = new Font("DIALOG", Font.BOLD, 11);
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	private _JLookup lookupCompany;
	private _JLookup lookupPCRNo;
	private _JLookup lookupPCRType;

	private JDateChooser dteDateCreated;

	private JTextField txtPCRStatus;

	private JDateChooser dteTransDate;

	private JTextField txtPayee;

	private JTextField txtDiv;

	private _JScrollPaneMain scrollTable;

	private modelPettyCashRequest modelPettyCashReq;

	private _JTableMain tblPettyCash;

	private _JLookup lookupPCRLiq;

	private JButton btnAddRow;

	private JButton btnRemoveRow;

	private JList<Integer> rowHeaderScroll;

	private String co_id; 
	private String pcr_type_id;
	private String pcr_acct_id; 

	private _JXFormattedTextField ftxtAmtToBeReimbursed;
	private _JXFormattedTextField ftxtTotalAmtOfCA;
	private _JXFormattedTextField ftxtCashReturned;

	private String div_id;
	private _JScrollPaneTotal scrollTableTotal;
	private modelPettyCashRequest modelPettyCashReqTotal;
	private _JTableTotal tblPettyCashTotal;
	private String user;
	private String payee;
	private Boolean cash_liq = false;
	private JPanel pnlCenterSouth;
	protected String pcr_no;

	private JPanel pnlMainNorth; 

	/**
	 * 
	 */
	public PettyCash() {
		super(title, true, true, true, true);
		initGUI();
	}

	/**
	 * @param title
	 */
	public PettyCash(String title) {
		super(title);
		initGUI();
	}

	/**
	 * @param title
	 * @param resizable
	 * @param closable
	 * @param maximizable
	 * @param iconifiable
	 */
	public PettyCash(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
		initGUI();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initGUI() {
		this.setLayout(new BorderLayout(5, 5));
		this.setSize(SIZE);
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
								JLabel lblCompany = new JLabel("Company: ", JLabel.TRAILING);
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
								JLabel lblPCRNo = new JLabel("PCR No.: ", JLabel.TRAILING);
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
										}
										displayPCR_header(co_id, pcr_no);
										displayPCR_details(modelPettyCashReq, rowHeaderScroll, modelPettyCashReqTotal, co_id, pcr_no);
										btnState(false, true, false, true, false, false, false, false, false); 
										lookupPCRNo.setEnabled(false);
										setCompEnabled(pnlMainNorth);
										setCompEditable(pnlMainNorth, false);
								        dteDateCreated.getCalendarButton().setVisible(false);
								        dteTransDate.getCalendarButton().setVisible(false);

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
						JPanel pnlExtra = new JPanel(new BorderLayout(5, 5)); 
						pnlNorth.add(pnlExtra, BorderLayout.SOUTH); 
						pnlExtra.setPreferredSize(new Dimension(0, 10));
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

											tblPettyCash.setEditable(true);
											setDiv();

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
									dteTransDate = new JDateChooser(FncGlobal.getDateToday(), "MM/dd/yy");
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
								JLabel lblInCaseOfLiq = new JLabel("PCR No.", JLabel.TRAILING); 
								pnlCSWLabel.add(lblInCaseOfLiq); 
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
		this.setComponentsEnabled(pnlCenterSouth, false);
		lookupCompany.setEnabled(true);
		lookupPCRNo.setEnabled(true);
		co_id = "01"; 
		lookupCompany.setValue("ACERLAND REALTY CORPORATION"); //default value
		btnState(true, false, false, false, false, false, false, false, false);
		lookupPCRNo.setLookupSQL(getPCR_no(lookupCompany.getValue()));
		lookupPCRType.setLookupSQL(getPCR_type());
		tblPettyCash.setEnabled(false);
		tblPettyCashTotal.setEnabled(false);
		user = UserInfo.EmployeeCode;

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Add New")) {

			if(JOptionPane.showConfirmDialog(getContentPane(), "Is Cash Fund Liquidation?", "Petty Cash Request", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

				cash_liq = true;
				addNew();
				addNewLiquidation(); 
			} else {
				addNew();
			}	
		}

		if(e.getActionCommand().equals("Add Row")) {
			addRow();
		} else if (e.getActionCommand().equals("Remove Row")) {
			if (tblPettyCash.getSelectedRows().length == 1) {
				removeRow();
			} else {
				JOptionPane.showMessageDialog(getContentPane(), "Please select row to remove", "Remove",
						JOptionPane.WARNING_MESSAGE);
			}
		}

		if(e.getActionCommand().equals("Cancel")) {
			this.setComponentsClear(pnlMainNorth);
			this.setComponentsEnabled(pnlMainNorth, false);
			this.setComponentsClear(pnlCenterSouth);
			this.setComponentsEnabled(pnlCenterSouth, false);
			FncTables.clearTable(modelPettyCashReq);
			FncTables.clearTable(modelPettyCashReqTotal);
			btnState(true, false, false, false, false, false, false, false, false);
		}

		if(e.getActionCommand().equals("Save")) {
			executeSave();
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
		createPCRTable(modelPettyCashReq, rowHeaderScroll);
		lookupPCRNo.setEnabled(false);
		txtPCRStatus.setText("ACTIVE"); //initial value
		txtPayee.setText(UserInfo.FullName);
		payee = getPayeeID(UserInfo.EmployeeCode); 
		txtDiv.setText(String.format("%s / %s", UserInfo.Department_Alias, UserInfo.Division_Alias));
		div_id = UserInfo.Division; 
		tblPettyCash.setEnabled(true);
		tblPettyCash.setEditable(false); 
		tblPettyCashTotal.setEnabled(true);
		tblPettyCashTotal.setEditable(false); 
		btnState(false, false, true, false, false, false, true, true, true); 
	}

	public void addNewLiquidation() {
		this.setComponentsEnabled(pnlCenterSouth, true);
		lookupPCRType.setValue("Cash Fund Liquidation");
		pcr_type_id = "03"; //Req. Type ID for Cash Fund Liquidation
		lookupPCRType.setEditable(false);
		tblPettyCash.setEditable(true);
		setDiv();
	}


	public static String getPCR_no(String co_id) {
		String SQL = "SELECT a.pcr_no AS \"PCR No\"\n"
				+ ", b.pc_req_type_desc as \"Description\"\n"
				+ ", c.status_desc AS \"Status\"\n"
				+ "FROM rf_petty_cash_header a\n"
				+ "LEFT JOIN mf_petty_cash_req_type b ON b.pc_req_id = a.pcr_type AND b.status_id = 'A'\n"
				+ "LEFT JOIN mf_record_status c ON c.status_id = a.status_id \n"
				+ "WHERE a.rec_status = 'A'\n"
				+ "ORDER by a.date_created";	

		System.out.println("getPCR_no: "+ SQL);

		return SQL; 
	}

	public static String getPCR_type() {
		String SQL = "SELECT pc_req_id AS \"PCR ID\"\n"
				+ ", pc_req_type_desc AS \"Description\"\n"
				+ ", status_id AS \"STATUS\"\n"
				+ "FROM mf_petty_cash_req_type\n"
				+ "WHERE status_id = 'A'"
				+ "AND pc_req_id != '03'; ";

		System.out.println("getPCR_type: "+ SQL);

		return SQL; 
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
		String SQL = "SELECT proj_cost_accnt_desc AS \"Description\"\n"
				+ ", proj_cost_accnt_id AS \"Project Cost ID\"\n"
				+ ", status_id AS \"Status \"\n"
				+ "FROM mf_project_cost_accnts\n"
				+ "WHERE status_id = 'A'";

		System.out.println("getProjCostID: "+ SQL);	

		return SQL; 
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
				new Object[] { null, null, null, null, null, div_id, null, 0.00 });
		((DefaultListModel<Integer>) rowHeaderScroll.getModel()).addElement(modelPettyCashReq.getRowCount());
	}

	private void removeRow() {
		modelPettyCashReq.removeRow(tblPettyCash.getSelectedRow());
		rowHeaderScroll.setModel(new DefaultListModel<Integer>());
		for (int x = 0; x < modelPettyCashReq.getRowCount(); x++) {

			((DefaultListModel<Integer>) rowHeaderScroll.getModel()).addElement(x + 1);
		}
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
				if(selected_column == 2) {
					modelPettyCashReq.setValueAt(data[0], selected_row, selected_column);
					modelPettyCashReq.setValueAt(data[1], selected_row, selected_column -1); 
				}else if (selected_column == 4) {
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

		if(!lookupPCRType.getText().equals("")) {
			if((selected_column == 2 || selected_column == 4) && (e.getClickCount() >= 2)) {
				AddPCRDetails();
			}
		} else {
			JOptionPane.showMessageDialog(getContentPane(), "Please select request type first.", "Message", JOptionPane.WARNING_MESSAGE);
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

		for (int x = 0; x < modelPettyCashReq.getRowCount(); x++) {
			BigDecimal pcr_amt = (BigDecimal) modelPettyCashReq.getValueAt(x, 7);

			try {
				totalAmt = totalAmt.add(pcr_amt); 
			} catch (Exception e) {

			}
		}

		modelPettyCashReqTotal.setValueAt(totalAmt, 0, 7);
	}

	private Boolean checkPCRAmount() {

		boolean x = false;

		Double pcr_amt = Double.parseDouble(modelPettyCashReq.getValueAt(0, 7).toString());

		System.out.println("");
		System.out.println("Value of PCR Amount: "+ pcr_amt);

		if (pcr_amt > 0) {
			System.out.println("Petty Cash Amt is Greater than 0!");
			x = true;
		} else if (pcr_amt < 0) {
			x = false;
		} else {
			x = false;
		}

		return x;

	}

	private Boolean checkAcctID_ifcomplete() {

		boolean x = true;

		int rw = tblPettyCash.getModel().getRowCount();
		int w = 0;

		while (w < rw) {
			Double pcr_amt = Double.parseDouble(modelPettyCashReq.getValueAt(w, 7).toString());
			if (pcr_amt > 0) {

				String acct_id = modelPettyCashReq.getValueAt(w, 2).toString().trim();
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

	private static String getPayeeID(String emp_code){
		pgSelect db = new pgSelect();
		db.select("SELECT entity_id\n"
				+ "FROM rf_employee\n"
				+ "WHERE emp_code = '"+emp_code+"'\n"
				+ "AND status_id = 'A'; ");

		return (String) db.getResult()[0][0];
	}

	public void executeSave() {

		if (checkPCRAmount() == false) {
			JOptionPane.showMessageDialog(getContentPane(), "Amount must be greater than zero.", "Petty Cash Amount",
					JOptionPane.WARNING_MESSAGE);
		} else {

			if (checkAcctID_ifcomplete() == false) {
				JOptionPane.showMessageDialog(getContentPane(), "Please select account ID.", "Account ID",
						JOptionPane.WARNING_MESSAGE);
			} else {

				if (JOptionPane.showConfirmDialog(getContentPane(), "Are all entries correct?", "Confirmation",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

					pcr_no = lookupPCRNo.getText().trim();

					//SAVING OF NEW DRF
					if (pcr_no.equals("")) { 

						pcr_no =  savePCR(co_id, modelPettyCashReq, pcr_no, div_id, payee, cash_liq); 
						System.out.println("Value of pcr_no: " + pcr_no);
						lookupPCRNo.setText(pcr_no);
						JOptionPane.showMessageDialog(getContentPane(), "New payment request saved.", "Information",
								JOptionPane.INFORMATION_MESSAGE);
//XXX TODO				insertAudit_trail
						displayPCR_details(modelPettyCashReq, rowHeaderScroll, modelPettyCashReqTotal, co_id, pcr_no);	
					}

					//SAVING FROM EDIT 
					else {
						savePCR(co_id, modelPettyCashReq, pcr_no, div_id, payee, cash_liq); 
//XXX TODO				insertAudit_trail
						lookupPCRNo.setText(pcr_no);
						displayPCR_details(modelPettyCashReq, rowHeaderScroll, modelPettyCashReqTotal, lookupCompany.getValue(), pcr_no);
						JOptionPane.showMessageDialog(getContentPane(), "Payment reqsuest updated.", "Information",
								JOptionPane.INFORMATION_MESSAGE);
					}

					this.setComponentsEditable(pnlMainNorth, false);
					btnState(false, false, false, true, false, false, false, false, false);

				}
			}
		}
	}

	public String savePCR(String co_id, modelPettyCashRequest model, String pcr_no, String div_id, String payee, Boolean cash_liq) {
		// FOR PCR DETAIL
		ArrayList<String> listAcctIDs = new ArrayList<String>();
		ArrayList<String> listProjIDs = new ArrayList<String>();
		ArrayList<String> listDivIDs = new ArrayList<String>();
		ArrayList<String> listPurposeOfExpenditure = new ArrayList<String>();
		ArrayList<BigDecimal> listPCRAmount = new ArrayList<BigDecimal>(); 
		ArrayList<Integer> listRecIds = new ArrayList<Integer>(); 

		for(int x = 0; x < model.getRowCount(); x++){

			String account_id = (String) model.getValueAt(x, 2);

			if (account_id != null && !account_id.isEmpty()) {

				String acct_id =  (String) model.getValueAt(x, 1);
				String proj_id = (String) model.getValueAt(x, 3);
				String div_ID = (String) model.getValueAt(x, 5);
				String purpose_of_exp = (String) model.getValueAt(x, 6); 
				BigDecimal amt = (BigDecimal) model.getValueAt(x, 7);
				Integer RecID = (Integer) model.getValueAt(x, 0);

				listAcctIDs.add(String.format("'%s'", acct_id));
				listProjIDs.add(String.format("'%s'", proj_id));
				listDivIDs.add(String.format("'%s'", div_ID));
				listPurposeOfExpenditure.add(String.format("'%s'", purpose_of_exp));
				listPCRAmount.add(amt);
				listRecIds.add(RecID);
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

			String SQL = "Select fn_save_petty_cash_req('"+pcr_no+"', '"+co_id+"', '"+dateFormat.format(dteDateCreated.getDate())+"', '"+dateFormat.format(dteTransDate.getDate())+"'\n"
					+ ", '"+pcr_type_id+"', '"+payee+"', '"+div_id+"', "+totalPCRAmt+", "+cash_liq+", '"+user+"', ARRAY["+acct_id+"]::VARCHAR[] \n"
					+ ", ARRAY["+proj_id+"]::VARCHAR[], ARRAY["+div_IDs+"]::VARCHAR[], ARRAY["+purpose_of_exp+"]::VARCHAR[], ARRAY["+amt+"]::NUMERIC[], ARRAY["+listRecIds+"]::INT[] \n"
					+ ", "+ftxtAmtToBeReimbursed.getValue()+", "+ftxtCashReturned.getValue()+", "+totalPCRAmt+");"; 

			pgSelect db = new pgSelect(); 
			db.select(SQL);

			System.out.println(SQL);

			if(db.isNotNull()) {
				pcr_no =  (String) db.getResult()[0][0];

			}		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Something went wrong.", "Save", JOptionPane.INFORMATION_MESSAGE);
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
				+ ", a.pcr_trans_date::DATE\n"
				+ ", fn_get_entity_name(a.payee) as payee\n"
				+ ", FORMAT('%s / %s', fn_get_div_alias(a.div_id), fn_get_exec_ofc_alias_div(a.div_id)) as division\n"
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
				+ "WHERE a.rec_status = 'A'"
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

		BigDecimal total_pcr_amt = new BigDecimal(0.00); 

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
