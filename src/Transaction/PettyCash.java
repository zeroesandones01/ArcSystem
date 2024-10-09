/**
 * 
 */
package Transaction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.plaf.DimensionUIResource;

import com.toedter.calendar.JDateChooser;

import Database.pgSelect;
import DateChooser._JDateChooser;
import FormattedTextField._JXFormattedTextField;
import Functions.FncGlobal;
import Functions.FncTables;
import Functions.UserInfo;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import components._JButton;
import components._JFormattedTextField;
import components._JInternalFrame;
import components._JTableMain;
import components._JTagLabel;
import interfaces._GUI;
import tablemodel.modelPettyCashRequest;

/**
 * 
 */
public class PettyCash extends _JInternalFrame implements _GUI, ActionListener {

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

	private _JLookup lookupCompany;

	private _JLookup lookupPCRNo;

	private JTextField txtPRCStatus;

	private _JLookup lookupPCRType;

	private JDateChooser dteDateCreated;

	private JTextField txtPCRNo;

	private JTextField txtPCRStatus;

	private JDateChooser dteTransDate;

	private JTextField txtPayee;

	private JTextField txtDiv;

	private JScrollPane scrollTable;

	private modelPettyCashRequest modelPettyCashReq;

	private _JTableMain tblPettyCash;

	private _JLookup lookupPCRLiq;

	private _JFormattedTextField txtCashReturned;

	private _JFormattedTextField txtAmtToBeReimbursed;

	private _JFormattedTextField txtTotalAmtOfCA;

	private JButton btnAddRow;

	private JButton btnRemoveRow;

	private JList rowHeaderScroll;
	
	private String co_id; 
	private String pcr_type_id; 

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
				JPanel pnlMainNorth = new JPanel(new BorderLayout(5, 5)); 
				pnlMain.add(pnlMainNorth, BorderLayout.NORTH); 
				pnlMainNorth.setPreferredSize(new Dimension(0, 180));
				pnlMainNorth.setBorder(components._JTBorderFactory.createTitleBorder("Petty Cash Details"));

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
									dteTransDate = new JDateChooser(FncGlobal.getDateToday(), "MM/dd/yy");
									pnlCEComp.add(dteTransDate); 
								}
								{
									txtPayee = new JTextField(); 
									pnlCEComp.add(txtPayee); 
									txtPayee.setHorizontalAlignment(JTextField.CENTER);
								}
								{
									txtDiv = new JTextField();
									pnlCEComp.add(txtDiv); 
									txtDiv.setHorizontalAlignment(JTextField.CENTER);

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
						scrollTable = new JScrollPane(); 
						pnlCenterTable.add(scrollTable, BorderLayout.CENTER); 
						scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					}
					{
						modelPettyCashReq = new modelPettyCashRequest(); 
						tblPettyCash = new _JTableMain(modelPettyCashReq); 
						tblPettyCash.hideColumns("Rec ID"); 
						tblPettyCash.getColumnModel().getColumn(1).setPreferredWidth(200);
						tblPettyCash.getColumnModel().getColumn(4).setPreferredWidth(280);
						scrollTable.setViewportView(tblPettyCash);
						tblPettyCash.setSortable(false); 
						{
							rowHeaderScroll = tblPettyCash.getRowHeader();
							rowHeaderScroll.setModel(new DefaultListModel());
							scrollTable.setRowHeaderView(rowHeaderScroll);
							scrollTable.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
							scrollTable.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, displayTableNavigation());
						}
			
					}
				}
				{
					JPanel pnlCenterSouth = new JPanel(new BorderLayout(5, 5)); 
					pnlMainCenter.add(pnlCenterSouth, BorderLayout.SOUTH); 
					pnlCenterSouth.setPreferredSize(new Dimension(0, 120)); 
					pnlCenterSouth.setBorder(components._JTBorderFactory.createTitleBorder("In Case of Liquidation"));

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
								txtCashReturned = new _JFormattedTextField();
								pnlCSWComp.add(txtCashReturned); 
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
								txtAmtToBeReimbursed = new _JFormattedTextField();
								pnlCSEComp.add(txtAmtToBeReimbursed); 
							}
							{
								txtTotalAmtOfCA = new _JFormattedTextField();
								pnlCSEComp.add(txtTotalAmtOfCA); 
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
		co_id = "01"; 
		lookupCompany.setValue("ACERLAND REALTY CORPORATION"); //default value
		txtPCRStatus.setText("ACTIVE"); //initial value
		lookupPCRType.setLookupSQL(getPCR_type());
		txtPayee.setText(UserInfo.FullName);
		txtDiv.setText(String.format("%s / %s", UserInfo.Department_Alias, UserInfo.Division_Alias));
	}

	private void btnState(Boolean add, Boolean edit, Boolean save, Boolean preview, Boolean pay, Boolean process) {
		btnAddNew.setEnabled(add);
		btnEdit.setEnabled(edit);
		btnSave.setEnabled(save);
		btnPreview.setEnabled(preview);
		btnPay.setEnabled(pay);
		btnProcess.setEnabled(process);
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
	
	private void enable_header_comp(Boolean x) {
		lookupCompany.setEnabled(x);
		lookupPCRNo.setEnabled(x);
		dteDateCreated.setEnabled(x);
		dteTransDate.setEnabled(x);
		lookupPCRType.setEnabled(x);
	}
	
	private void enable_liquidation_comp(Boolean x) {
		lookupPCRLiq.setEnabled(x);
		txtCashReturned.setEnabled(x);
		txtCashReturned.setEditable(x);
		txtAmtToBeReimbursed.setEnabled(x);
		txtAmtToBeReimbursed.setEditable(x);
		txtTotalAmtOfCA.setEnabled(x);
	}
	
	public static String getPCR_no(String co_id) {
		String SQL = "";
		
		System.out.println("getPCR_no: "+ SQL);

		return SQL; 
	}

	public static String getPCR_type() {
		String SQL = "SELECT pc_req_id AS \"PCR ID\"\n"
				+ ", pc_req_type_desc AS \"Description\"\n"
				+ ", status_id \n"
				+ "FROM \n"
				+ "mf_petty_cash_req_type\n"
				+ "WHERE status_id = 'A'; ";
		
		System.out.println("getPCR_type: "+ SQL);

		return SQL; 
	}





}
