
package Buyers.ClientServicing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.jdesktop.swingx.JXFormattedTextField;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTextField;

import com.vdc.glasspane.JGlassLoading;

import Database.pgSelect;
import DateChooser.DateEvent;
import DateChooser.DateListener;
import DateChooser._JDateChooser;
import Dialogs.dlg_ClientRequestDP1;
import FormattedTextField._JXFormattedTextField;
import Functions.FncGlobal;
import Functions.FncReport;
import Functions.FncSystem;
import Functions.FncTables;
import Functions.UserInfo;
import components.JTBorderFactory;
import components._JDialog;
import components._JTabbedPane;
import components._JTableMain;
import components._JXTextField;
import interfaces._GUI;
import tablemodel.modelChangeSchedule;
import tablemodel.modelMASchedNew;
import tablemodel.modelMASchedOld;
import tablemodel.modelNewSchedule;
import tablemodel.modelOldSchedule;

/**
 * @author John Lester Fatallo
 */

public class pnlOtherReq_PreviewSchedule extends JDialog implements _GUI, ActionListener, MouseListener {
	// CHANGE TO JDIALOG INSTEAD OF JINTERALFRAME

	private static final long serialVersionUID = -1622716097453191349L;
	private static String title = "Change Schedule";

	private Dimension size = new Dimension(800, 600);
	Border LINE_BORDER = BorderFactory.createLineBorder(Color.GRAY);
	Border EMPTY_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);

	private JPanel pnlNorth;

	private JPanel pnlNorthEast;
	private JPanel pnlNELabel;
	private JPanel pnlNECenter;

	private JPanel pnlNorthWest;
	private JPanel pnlNWLabel;
	private JPanel pnlNWCenter;
	private JPanel pnlDownpayment;

	private JPanel pnlLoanAvailed;

	private JPanel pnlMASched;
	private JPanel pnlMASchedOld;
	private _JTableMain tblMASchedOld;
	private modelMASchedOld modelMAOld;
	private JScrollPane scrollMASchedOld;
	private JList rowHeaderMASchedOld;

	private JPanel pnlMASchedNew;
	private modelMASchedNew modelMANew;
	private _JTableMain tblMASchedNew;
	private JScrollPane scrollMASchedNew;
	private JList rowHeaderMASchedNew;

	private _JTabbedPane tabCenter;

	private JPanel pnlChangeSched;
	private modelChangeSchedule modelChangeSched;
	private JScrollPane scrollChangeSched;
	private _JTableMain tblChangeSched;
	private JList rowHeaderChangeSched;

	private JSplitPane splitSchedule;
	private JPanel pnlNewSchedule;
	private modelNewSchedule modelNewSched;
	private JScrollPane scrollNewSchedule;
	private _JTableMain tblNewSchedule;
	private JList rowHeaderNewSchedule;

	private JPanel pnlOldSchedule;
	private modelOldSchedule modelOldSched;
	private JScrollPane scrollOldSchedule;
	private _JTableMain tblOldSchedule;
	private JList rowHeaderOldSchedule;

	private JPanel pnlSouth;
	private JButton btnPrintPreview;
	private JButton btnSave;

	private JPanel pnlClient;
	private JPanel pnlClientWest;
	private JLabel lblEntityID;
	private JLabel lblEntityName;
	private JLabel lblUnitID;
	private JLabel lblUnitDesc;

	private JPanel pnlEntityID;
	private _JXTextField txtEntityID;
	private JLabel lblProject;
	private _JXTextField txtProject;

	private JPanel pnlEntityName;
	private _JXTextField txtEntityName;
	private JLabel lblHouseModel;
	private _JXTextField txtHouseModel;

	private JPanel pnlUnitID;
	private _JXTextField txtUnitID;
	private JLabel lblBuyerType;
	private _JXTextField txtBuyerType;

	private JPanel pnlUnitDesc;
	private _JXTextField txtUnitDesc;
	private JLabel lblIntRate;

	private JPanel pnlIntRate;
	private _JXFormattedTextField txtIntRate;
	private JLabel lblLumpsum;
	private _JXFormattedTextField txtProcFee;

	private JPanel pnlSellingPrice;

	private JPanel pnlSellPriceWest;
	private JLabel lblGSP;
	private JLabel lblVAT;
	private JLabel lblDiscount;
	private JLabel lblNSP;

	private JPanel pnlSellingPriceCenter;

	private JPanel pnlGSP;
	private _JXFormattedTextField txtGSP;
	private JLabel lblDPAmt;
	private _JXFormattedTextField txtDPAmt;

	private JPanel pnlVAT;
	private _JXFormattedTextField txtVAT;
	private JLabel lblLoanableAmt;
	private _JXFormattedTextField txtMAAmt;

	private JPanel pnlDiscount;
	private _JXFormattedTextField txtDiscount;
	private JLabel lblDPTerms;
	private _JXTextField txtDPTerms;

	private JPanel pnlNSP;
	private _JXFormattedTextField txtNSP;
	private JLabel lblMATerms;
	private _JXTextField txtMATerms;

	private JPanel pnlReqType;
	private JLabel lblReqType;
	private _JXTextField txtReqType;
	private String old_entity_id;
	private String old_proj_id;
	private String old_unit_id;
	private String old_seq_no;
	private String final_entity_id;
	private String final_proj_id;
	private String final_unit_id;
	private String final_seq_no;
	private String final_model_id;

	private String pmt_scheme;
	//private pnlOtherRequest_OldData od;
	private JLabel lblNewSchedDate;
	private _JDateChooser dteNewSchedule;
	private String request_no;
	private String final_buyer_type;
	private String final_pmt_scheme;
	private JPopupMenu menuSched;
	private JMenuItem menuRemove;
	private JGlassLoading glass;

	/*public pnlOtherReq_PreviewSchedule() {
		super(title, true, true, true, true);
		initGUI();
	}

	public pnlOtherReq_PreviewSchedule(String title) {
		super(title, true, true, true, true);
		initGUI();
	}

	public pnlOtherReq_PreviewSchedule(Object[] data3, Boolean change_sched, String req_no){
		super(title, true, true, true, true);
		initGUI();
		displaySchedDetails(req_no ,data3);
		//displayOldSchedule(data3);
		displayChangeSchedule(data3, change_sched, req_no);
	}*/

	public pnlOtherReq_PreviewSchedule() {
		initGUI();
	}

	public pnlOtherReq_PreviewSchedule(Frame owner) {
		super(owner);
		initGUI();
	}

	public pnlOtherReq_PreviewSchedule(Dialog owner) {
		super(owner);
		initGUI();
	}

	public pnlOtherReq_PreviewSchedule(Window owner) {
		super(owner);
		initGUI();
	}

	public pnlOtherReq_PreviewSchedule(Frame owner, boolean modal) {
		super(owner, modal);
		initGUI();
	}

	public pnlOtherReq_PreviewSchedule(Frame owner, String title, Object [] data, Boolean change_sched, String req_no) {
		super(owner, title);
		initGUI();
		displaySchedDetails(req_no ,data);
		//displayOldSchedule(data3);
		displayChangeSchedule(data, change_sched, req_no);
	}

	public pnlOtherReq_PreviewSchedule(Dialog owner, boolean modal) {
		super(owner, modal);
		initGUI();
	}

	public pnlOtherReq_PreviewSchedule(Dialog owner, String title) {
		super(owner, title);
		initGUI();
	}

	public pnlOtherReq_PreviewSchedule(Window owner, ModalityType modalityType) {
		super(owner, modalityType);
		initGUI();
	}

	public pnlOtherReq_PreviewSchedule(Window owner, String title) {
		super(owner, title);
		initGUI();
	}

	public pnlOtherReq_PreviewSchedule(Frame arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
		initGUI();
	}

	public pnlOtherReq_PreviewSchedule(Dialog owner, String title, boolean modal) {
		super(owner, title, modal);
		initGUI();
	}

	public pnlOtherReq_PreviewSchedule(Window owner, String title, ModalityType modalityType) {
		super(owner, title, modalityType);
		initGUI();
	}

	public pnlOtherReq_PreviewSchedule(Frame arg0, String arg1, boolean arg2, GraphicsConfiguration arg3) {
		super(arg0, arg1, arg2, arg3);
		initGUI();
	}

	public pnlOtherReq_PreviewSchedule(Dialog owner, String title, boolean modal, GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
		initGUI();
	}

	public pnlOtherReq_PreviewSchedule(Window owner, String title, ModalityType modalityType, GraphicsConfiguration gc) {
		super(owner, title, modalityType, gc);
		initGUI();
	}

	@Override
	public void initGUI() {
		this.setPreferredSize(size);
		this.setSize(size);
		this.setModal(true);
		this.setModalityType(ModalityType.DOCUMENT_MODAL);
		this.getContentPane().setLayout(new BorderLayout());
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.getRootPane().registerKeyboardAction(this, "Escape", KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		glass = new JGlassLoading(getJMenuBar(), getContentPane(), this.getWidth(), this.getWidth());
		this.setGlassPane(glass);

		JXPanel pnlMain = new JXPanel(new BorderLayout(5, 5));
		getContentPane().add(pnlMain, BorderLayout.CENTER);
		pnlMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		{
			pnlNorth = new JPanel(new BorderLayout(5, 5));
			pnlMain.add(pnlNorth, BorderLayout.NORTH);
			//pnlNorth.setBorder(EMPTY_BORDER);
			pnlNorth.setBorder(components.JTBorderFactory.createTitleBorder("Request Details"));
			pnlNorth.setLayout(new GridLayout(1, 2, 3, 3));
			{
				pnlNorthEast = new JPanel(new BorderLayout(3, 3));
				pnlNorth.add(pnlNorthEast, BorderLayout.WEST);
				{
					pnlNELabel = new JPanel(new GridLayout(7, 1, 3, 3));
					pnlNorthEast.add(pnlNELabel,BorderLayout.WEST);
					{
						lblEntityID = new JLabel("Entity ID");
						pnlNELabel.add(lblEntityID);
					}
					{
						lblEntityName = new JLabel("Entity Name");
						pnlNELabel.add(lblEntityName);
					}
					{
						lblUnitID = new JLabel("Unit ID");
						pnlNELabel.add(lblUnitID);
					}
					{
						lblUnitDesc = new JLabel("Unit Desc");
						pnlNELabel.add(lblUnitDesc);
					}
					{
						lblProject = new JLabel("Project");
						pnlNELabel.add(lblProject);
					}
					{
						lblHouseModel = new JLabel("House Model");
						pnlNELabel.add(lblHouseModel);
					}
					{
						lblBuyerType = new JLabel("Buyer Type");
						pnlNELabel.add(lblBuyerType);
					}
				}
				{
					pnlNECenter = new JPanel(new GridLayout(7, 1, 3, 3));
					pnlNorthEast.add(pnlNECenter, BorderLayout.CENTER);
					{
						txtEntityID = new _JXTextField();
						pnlNECenter.add(txtEntityID);
						txtEntityID.setHorizontalAlignment(JXTextField.LEFT);
					}
					{
						txtEntityName = new _JXTextField();
						pnlNECenter.add(txtEntityName);
						txtEntityName.setHorizontalAlignment(JXTextField.LEFT);
					}
					{
						txtUnitID = new _JXTextField();
						pnlNECenter.add(txtUnitID);
						txtUnitID.setHorizontalAlignment(JXTextField.LEFT);
					}
					{
						txtUnitDesc = new _JXTextField();
						pnlNECenter.add(txtUnitDesc);
						txtUnitDesc.setHorizontalAlignment(JXTextField.LEFT);
					}
					{
						txtProject = new _JXTextField();
						pnlNECenter.add(txtProject);
						txtProject.setHorizontalAlignment(JXTextField.LEFT);
					}
					{
						txtHouseModel = new _JXTextField();
						pnlNECenter.add(txtHouseModel);
						txtHouseModel.setHorizontalAlignment(JXTextField.LEFT);
					}
					{
						txtBuyerType = new _JXTextField();
						pnlNECenter.add(txtBuyerType);
						txtBuyerType.setHorizontalAlignment(JXTextField.LEFT);
					}
				}
			}
			{
				pnlNorthWest = new JPanel(new BorderLayout(3, 3));
				pnlNorth.add(pnlNorthWest, BorderLayout.EAST);
				{
					pnlNWLabel = new JPanel(new GridLayout(8, 1, 3, 3));
					pnlNorthWest.add(pnlNWLabel, BorderLayout.WEST);
					{
						lblGSP = new JLabel("GSP");
						pnlNWLabel.add(lblGSP);
					}
					{
						lblVAT = new JLabel("VAT");
						pnlNWLabel.add(lblVAT);
					}
					{
						lblDiscount = new JLabel("Discount");
						pnlNWLabel.add(lblDiscount);
					}
					{
						lblNSP = new JLabel("NSP");
						pnlNWLabel.add(lblNSP);
					}
					{
						lblDPAmt = new JLabel("DP / Equity");
						pnlNWLabel.add(lblDPAmt);
					}
					{
						lblLoanableAmt = new JLabel("Loanable Amt");
						pnlNWLabel.add(lblLoanableAmt);
					}
					{
						lblIntRate = new JLabel("Int Rate");
						pnlNWLabel.add(lblIntRate);
					}
					{
						lblNewSchedDate = new JLabel("New Scheddate");
						pnlNWLabel.add(lblNewSchedDate);
					}
				}
				{
					pnlNWCenter = new JPanel(new GridLayout(8, 1, 3, 3));
					pnlNorthWest.add(pnlNWCenter, BorderLayout.CENTER);
					{
						txtGSP = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
						pnlNWCenter.add(txtGSP);
						txtGSP.setFormatterFactory(_JXFormattedTextField.DECIMAL);
						txtGSP.setEditable(false);
					}
					{
						txtVAT = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
						pnlNWCenter.add(txtVAT);
						txtVAT.setFormatterFactory(_JXFormattedTextField.DECIMAL);
						txtVAT.setEditable(false);
					}
					{
						txtDiscount = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
						pnlNWCenter.add(txtDiscount);
						txtDiscount.setFormatterFactory(_JXFormattedTextField.DECIMAL);
						txtDiscount.setEditable(false);
					}
					{
						txtNSP = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
						pnlNWCenter.add(txtNSP);
						txtNSP.setFormatterFactory(_JXFormattedTextField.DECIMAL);
						txtNSP.setEditable(false);
					}
					{
						pnlDownpayment = new JPanel(new BorderLayout(3, 3));
						pnlNWCenter.add(pnlDownpayment);
						{
							txtDPAmt = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
							pnlDownpayment.add(txtDPAmt, BorderLayout.WEST);
							txtDPAmt.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							txtDPAmt.setEditable(false);
							txtDPAmt.setPreferredSize(new Dimension(100, 0));
						}
						{
							lblDPTerms = new JLabel("DP Terms", JLabel.TRAILING);
							pnlDownpayment.add(lblDPTerms);
						}
						{
							txtDPTerms = new _JXTextField();
							pnlDownpayment.add(txtDPTerms, BorderLayout.EAST);
							txtDPTerms.setHorizontalAlignment(JXTextField.CENTER);
							txtDPTerms.setPreferredSize(new Dimension(50, 0));
						}
					}
					{
						pnlLoanAvailed = new JPanel(new BorderLayout(3, 3));
						pnlNWCenter.add(pnlLoanAvailed);
						{
							txtMAAmt = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
							pnlLoanAvailed.add(txtMAAmt, BorderLayout.WEST);
							txtMAAmt.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							txtMAAmt.setEditable(false);
							txtMAAmt.setPreferredSize(new Dimension(100, 0));
						}
						{
							lblMATerms = new JLabel("MA Terms", JLabel.TRAILING);
							pnlLoanAvailed.add(lblMATerms);
						}
						{
							txtMATerms = new _JXTextField();
							pnlLoanAvailed.add(txtMATerms, BorderLayout.EAST);
							txtMATerms.setHorizontalAlignment(JXTextField.CENTER);
							txtMATerms.setPreferredSize(new Dimension(50, 0));
						}
					}
					{
						pnlIntRate = new JPanel(new BorderLayout(3, 3));
						pnlNWCenter.add(pnlIntRate);
						{
							txtIntRate = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
							pnlIntRate.add(txtIntRate, BorderLayout.WEST);
							txtIntRate.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							txtIntRate.setEditable(false);
							txtIntRate.setPreferredSize(new Dimension(100, 0));
						}
						{
							lblLumpsum = new JLabel("Proc Fee", JLabel.TRAILING);
							pnlIntRate.add(lblLumpsum);
						}
						{
							txtProcFee = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
							pnlIntRate.add(txtProcFee, BorderLayout.EAST);
							txtProcFee.setFormatterFactory(_JXFormattedTextField.DECIMAL);
							txtProcFee.setEditable(false);
							txtProcFee.setPreferredSize(new Dimension(100, 0));
						}
					}
					{
						dteNewSchedule = new _JDateChooser("MM/dd/yy", "##/##/##", '_');
						pnlNWCenter.add(dteNewSchedule);
						dteNewSchedule.setDate(Calendar.getInstance().getTime());
						dteNewSchedule.setEnabled(false);
						dteNewSchedule.addDateListener(new DateListener() {

							@Override
							public void datePerformed(DateEvent event) {
								if(tblNewSchedule.getSelectedRows().length == 1){

									new Thread(new Runnable() {
										@Override
										public void run() {
											glass.start("Setting Schedule Dates...");
											int selected_row = tblNewSchedule.convertRowIndexToModel(tblNewSchedule.getSelectedRow());
											Date start_date = dteNewSchedule.getDate();/*((_JDateChooser) event.getSource()).getDate()*/;

											for(int x= selected_row; x<modelNewSched.getRowCount(); x++){
												String part_id = (String) modelNewSched.getValueAt(x, 0);

												if(x == selected_row){
													modelNewSched.setValueAt(start_date, x, 2);
												}else{
													modelNewSched.setValueAt(getNextSchedDate(part_id, start_date), x, 2);
												}
												start_date = (Date) modelNewSched.getValueAt(x, 2);
											}
											tblNewSchedule.clearSelection();
											tblNewSchedule.packAll();
											refreshTable();
											glass.stop();

										}
									}).start();
								}else{
									JOptionPane.showMessageDialog(null, "Select Schedule to Change", "Change Schedule", JOptionPane.WARNING_MESSAGE);
								}
							}
						});
					}
				}
			}
		}
		{
			splitSchedule = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			pnlMain.add(splitSchedule, BorderLayout.CENTER);
			splitSchedule.setOneTouchExpandable(true);
			splitSchedule.setResizeWeight(.5d);
			//splitSchedule.setBorder(new GridLayout(1, 2, 3, 3));
			{
				pnlNewSchedule = new JPanel(new BorderLayout(3, 3));
				splitSchedule.add(pnlNewSchedule, JSplitPane.LEFT);
				pnlNewSchedule.setBorder(JTBorderFactory.createTitleBorder("New Schedule"));
				{
					scrollNewSchedule = new JScrollPane();
					pnlNewSchedule.add(scrollNewSchedule);
					scrollNewSchedule.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					{
						modelNewSched = new modelNewSchedule();
						tblNewSchedule = new _JTableMain(modelNewSched);
						scrollNewSchedule.setViewportView(tblNewSchedule);
						tblNewSchedule.hideColumns("Sched ID", "Part ID", "UPICO" ,"Int. Rate");
						tblNewSchedule.setSortable(false);
						modelNewSched.addTableModelListener(new TableModelListener() {

							@Override
							public void tableChanged(TableModelEvent e) {
								if(modelNewSched.getRowCount() == 0){
									rowHeaderNewSchedule.setModel(new DefaultListModel());
								}
							}
						});
					}
					{
						menuSched = new JPopupMenu("Popup");
						menuRemove = new JMenuItem("For Lumpsum");
						menuRemove.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								if(tblNewSchedule.getSelectedRows().length == 1){
									int x= tblNewSchedule.getSelectedRow();
									DefaultListModel listNewSched = new DefaultListModel();

									if(modelNewSched.getRowCount() != 0){
										if(modelNewSched.getRowCount() != 0){
											listNewSched.removeAllElements();
										}
										modelNewSched.removeRow(x);
										for(int y = 1; y<=modelNewSched.getRowCount(); y++){
											((DefaultListModel) rowHeaderNewSchedule.getModel()).addElement(y);
										}
									}
									refreshTable();

									menuSched.remove(menuRemove);
									scrollNewSchedule.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblNewSchedule.getRowCount())));
									tblNewSchedule.packAll();
								}
							}
						});
					}
					{
						tblNewSchedule.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

							@Override
							public void valueChanged(ListSelectionEvent e) {
								if(!e.getValueIsAdjusting()){
									if(tblNewSchedule.getSelectedRows().length == 1 && btnSave.isEnabled()){
										int selected_row = tblNewSchedule.getSelectedRow();

										String part_id = (String) modelNewSched.getValueAt(selected_row, 0);
										Date sched_date = (Date) modelNewSched.getValueAt(selected_row, 2);

										Integer res_count = 0;

										for(int x = 0; x<modelNewSched.getRowCount(); x++){
											String res_part_id = (String) modelNewSched.getValueAt(x, 0);

											if(res_part_id.equals("012")){
												res_count++;
											}
										}
										System.out.printf("Display res count: %s%n", res_count);

										if(part_id.equals("012") && res_count > 1){
											menuSched.add(menuRemove);
											System.out.println("Dumaan dito sa Add");
										}else{
											menuSched.remove(menuRemove);
											System.out.println("Dumaan dito sa remove");
										}
									}
								}
							}
						});
					}
					{
						tblNewSchedule.addMouseListener(new PopupTriggerListener_panel());
					}
					{
						rowHeaderNewSchedule = tblNewSchedule.getRowHeader();
						rowHeaderNewSchedule.setModel(new DefaultListModel());
						scrollNewSchedule.setRowHeaderView(rowHeaderNewSchedule);
						scrollNewSchedule.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
					}
				}
			}
			{
				pnlOldSchedule = new JPanel(new BorderLayout(3, 3));
				splitSchedule.add(pnlOldSchedule, JSplitPane.RIGHT);
				pnlOldSchedule.setBorder(JTBorderFactory.createTitleBorder("Old Schedule"));
				{
					scrollOldSchedule = new JScrollPane();
					pnlOldSchedule.add(scrollOldSchedule);
					scrollOldSchedule.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					{
						modelOldSched = new modelOldSchedule();
						tblOldSchedule = new _JTableMain(modelOldSched);
						scrollOldSchedule.setViewportView(tblOldSchedule);
						tblOldSchedule.hideColumns("Part ID", "UPICO" ,"Int. Rate");
						tblOldSchedule.setSortable(false);
						modelOldSched.addTableModelListener(new TableModelListener() {

							public void tableChanged(TableModelEvent arg0) {
								((DefaultListModel) rowHeaderOldSchedule.getModel()).addElement(modelOldSched.getRowCount());

								if(modelOldSched.getRowCount() == 0){
									rowHeaderOldSchedule.setModel(new DefaultListModel());
								}
							}
						});
					}
					{
						rowHeaderOldSchedule = tblOldSchedule.getRowHeader();
						rowHeaderOldSchedule.setModel(new DefaultListModel());
						scrollOldSchedule.setRowHeaderView(rowHeaderOldSchedule);
						scrollOldSchedule.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
					}
				}
			}
		}
		{
			pnlSouth = new JPanel(new BorderLayout(5, 5));
			this.add(pnlSouth, BorderLayout.SOUTH);
			pnlSouth.setBorder(EMPTY_BORDER);
			pnlSouth.setLayout(new GridLayout(1, 2, 5, 5));
			{
				btnPrintPreview = new JButton("Print Preview");
				pnlSouth.add(btnPrintPreview);
				btnPrintPreview.setActionCommand(btnPrintPreview.getText());
				btnPrintPreview.setMnemonic(KeyEvent.VK_R);
				btnPrintPreview.addActionListener(this);
			}
			{
				btnSave = new JButton("Save");
				pnlSouth.add(btnSave);
				btnSave.setActionCommand(btnSave.getText());
				btnSave.setMnemonic(KeyEvent.VK_V);
				btnSave.addActionListener(this);
			}
			/*{
				btnCancel = new JButton("Cancel");
				pnlSouth.add(btnCancel);
				btnCancel.setActionCommand(btnCancel.getText());
				btnCancel.setMnemonic(KeyEvent.VK_C);
				btnCancel.addActionListener(this);
			}*/
		}
		this.pack();
	}//XXX END OF INIT GUI

	public void displaySchedDetails(String req_no ,Object [] data3){
		String final_entity_id = (String) data3[0];
		String final_unit_id = (String) data3[3];
		final_model_id = (String) data3[6];
		String final_buyer_type = (String) data3[8];
		String final_pmt_scheme = (String) data3[9];
		BigDecimal final_disc_amt = (BigDecimal) data3[11];
		BigDecimal final_vat_amt = (BigDecimal) data3[13];
		BigDecimal final_net_sell_price = (BigDecimal) data3[15];
		BigDecimal final_dp_amt = (BigDecimal) data3[16];
		BigDecimal final_availed_amt = (BigDecimal) data3[20];
		final_proj_id = (String) data3[2];
		BigDecimal final_sprice = (BigDecimal) data3[10];
		String final_dpterm = (String) data3[18];
		String final_materm = (String) data3[22];
		BigDecimal final_int_rate = (BigDecimal) data3[24];

		pgSelect db = new pgSelect();

		String sql = "SELECT TRIM(get_client_name('"+final_entity_id+"')), \n"+
				"TRIM(get_unit_description('"+final_unit_id+"')), \n"+
				"TRIM(get_project_name('"+final_proj_id+"')), \n"+
				"TRIM(get_model_desc('"+final_model_id+"')),  \n"+
				"(SELECT TRIM(type_desc) FROM mf_buyer_type WHERE type_id = '"+final_buyer_type+"'), \n"+
				//"sp_compute_request_proc_fee('"+req_no+"', "+_OtherRequest.getFireHouseAPV_Rounded(final_model_id)+"), \n"+ //XXX check proc_fee here for bank finance pmt scheme
				"(SELECT c_misc_fees FROM view_client_request_pricelist('"+req_no+"', false)), \n"+   
				"(SELECT int_rate FROM mf_payment_scheme WHERE pmt_scheme_id = '"+final_pmt_scheme+"'),\n"+
				"(SELECT c_vat FROM view_client_request_pricelist('"+req_no+"', false))";
		//"(select TRIM(pmt_scheme_desc) from mf_payment_scheme where pmt_scheme_id = '"+final_pmt_scheme+"') \n";
		//"(select TRIM(request_desc) from mf_request_type where request_id = '"+req_id+"')";
		db.select(sql);

		FncSystem.out("Display sql for Sched Details", sql);

		txtEntityID.setText(final_entity_id);
		txtUnitID.setText(final_unit_id);
		txtGSP.setValue(final_sprice);
		txtDiscount.setValue(final_disc_amt);
		//txtVAT.setValue(final_vat_amt);
		txtMAAmt.setValue(final_availed_amt);
		txtDPAmt.setValue(final_dp_amt);
		

		if(_OtherRequest.getGroupID(final_buyer_type).equals("05")){
			txtNSP.setValue(final_sprice);
		}else{
			txtNSP.setValue(final_net_sell_price);
		}
		
		if(req_no.equals("220124-00005")) {
			txtDPAmt.setValue(new BigDecimal("164650.00"));
			txtMAAmt.setValue(new BigDecimal("2408000.00"));
			txtGSP.setValue(new BigDecimal("2572650"));
			txtNSP.setValue(new BigDecimal("2572650"));
		
		}
		
		if(req_no.equals("221018-00004")) {
			txtDPAmt.setValue(new BigDecimal("443200"));
			txtMAAmt.setValue(new BigDecimal("2136000.00"));
			txtGSP.setValue(new BigDecimal("2579200.00"));
			txtNSP.setValue(new BigDecimal("2579200.00"));
		
		}
		
		if(req_no.equals("221018-00004")) {
			txtDPAmt.setValue(new BigDecimal("443200"));
			txtMAAmt.setValue(new BigDecimal("2136000.00"));
			txtGSP.setValue(new BigDecimal("2579200.00"));
			txtNSP.setValue(new BigDecimal("2579200.00"));
		}	

		txtDPTerms.setText(final_dpterm);
		txtMATerms.setText(final_materm);
		txtIntRate.setValue(final_int_rate);

		txtEntityName.setText((String) db.getResult()[0][0]);
		txtUnitDesc.setText((String) db.getResult()[0][1]);
		txtProject.setText((String) db.getResult()[0][2]);
		txtHouseModel.setText((String) db.getResult()[0][3]);
		txtBuyerType.setText((String) db.getResult()[0][4]);
		txtProcFee.setValue(db.getResult()[0][5]);
		txtIntRate.setValue(db.getResult()[0][6]);
		txtVAT.setValue(db.getResult()[0][7]);
		pmt_scheme = final_pmt_scheme;

		if(_OtherRequest.getGroupID(final_buyer_type).equals("03")){
			txtProcFee.setValue(new BigDecimal("0.00"));
		}
		//txtReqType.setText((String) db.getResult()[0][5]);

	}

	private void displayNewSchedule(String req_no){

		DefaultListModel listModel = new DefaultListModel();
		modelNewSched.clear();
		rowHeaderNewSchedule.setModel(listModel);

		pgSelect db = new pgSelect();

		/*String sql = "SELECT  a.part_id, b.part_desc,\n" + 
					 "a.scheddate, a.amount, \n"+
					 "(CASE WHEN a.part_id = '013' THEN a.proc_fee ELSE 0.00 END) as proc_fee, \n"+
					 "(CASE WHEN a.part_id = '014' THEN COALESCE(a.proc_fee, 0.00) ELSE 0.00 END) as upico, \n" + 
					 "a.mri, a.fire, a.interest, a.principal, \n" + 
					 "coalesce(a.vat, 0.00) ,a.balance, coalesce(a.interest_rate, 0.00)\n" + 
					 "from rf_req_client_schedule a\n" + 
					 "left join mf_client_ledger_part b on b.part_id = a.part_id\n" + 
					 "where a.request_no = '"+req_no+"'\n" + 
					 "and a.request_status = 'S'\n" + 
					 "order by a.client_sched_id\n";*/

		String sql = "SELECT a.part_id, b.part_desc,\n" + 
				"a.scheddate, a.amount, \n" + 
				"(CASE WHEN a.part_id = '013' THEN (CASE WHEN a.proc_fee < 0 THEN FORMAT('(%s)', abs(proc_fee)) \n" + 
				"					ELSE a.proc_fee::VARCHAR END) \n" + 
				"	ELSE 0.00::VARCHAR END) as proc_fee, \n" +
				"(CASE WHEN a.part_id = '013' THEN COALESCE(a.rpt_amt, 0.00) else 0.00 end) as rpt_amt, \n"+
				"(CASE WHEN a.part_id = '014' THEN COALESCE(a.proc_fee, 0.00) ELSE 0.00 END) as upico, \n" + 
				"a.mri, a.fire, a.interest, a.principal, \n" + 
				"coalesce(a.vat, 0.00) ,a.balance, coalesce(a.interest_rate, 0.00)\n" + 
				"from rf_req_client_schedule a\n" + 
				"left join mf_client_ledger_part b on b.part_id = a.part_id\n" + 
				"where a.request_no = '"+req_no+"'\n" + 
				"and a.request_status = 'S'\n" + 
				"order by a.client_sched_id, scheddate;";			 
		db.select(sql);

		FncSystem.out("Display the Saved New Schedule", sql);

		if(db.isNotNull()){

			for(int x= 0; x<db.getRowCount(); x++){
				modelNewSched.addRow(db.getResult()[x]);
				listModel.addElement(modelNewSched.getRowCount());
			}
			scrollNewSchedule.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblNewSchedule.getRowCount())));
			tblNewSchedule.packAll();
		}
	}

	private void displayOldSchedule(Object data3[]){
		pgSelect db = new pgSelect();

		old_entity_id = (String) data3[62];
		old_proj_id = (String) data3[65];
		old_unit_id = (String) data3[66];
		old_seq_no = (String) data3[67];
		String req_id = (String) data3[28];

		String sql = "";

		/*if(req_id.contains("07") || req_id.contains("12")){
			sql = "select trim(part_id), trim(get_particular(part_id)), \n" + 
					"scheddate, amount, \n"+
					"(CASE WHEN part_id = '013' THEN proc_fee::VARCHAR ELSE 0.00::VARCHAR END) as proc_fee, \n"+
					"(CASE WHEN part_id = '014' THEN proc_fee ELSE 0.00 END) as upico ,\n"+
					"coalesce(mri, 0.00), coalesce(fire, 0.00), \n" + 
					"interest, principal, coalesce(vat, 0.00), balance, \n" + 
					"coalesce(interest_rate, 0.00)\n" + 
					"from rf_req_schedule_old \n" + 
					"where entity_id = '"+old_entity_id+"' \n"+
					"and unit_id = '"+old_unit_id+"' \n"+
					"and proj_id = '"+old_proj_id+"' \n"+
					"and seq_no = "+old_seq_no+" \n"+
					"and date_created = (select max(date_created) \n"+
					"					 from rf_req_schedule_old \n"+
					"					 where entity_id = '"+old_entity_id+"' \n"+
					"	  				 and proj_id = '"+old_proj_id+"' \n"+
					"					 and unit_id = '"+old_unit_id+"') \n"+
					"					 and seq_no = "+old_seq_no+"\n"+
					"order by scheddate";
		}else{*/

		sql =   "SELECT TRIM(part_id) AS part_id, TRIM(get_particular(part_id)) AS part_desc, \n" + 
				"scheddate, amount, (CASE WHEN part_id = '013' THEN proc_fee::VARCHAR ELSE 0.00::VARCHAR END) AS proc_fee, \n" +
				"(CASE WHEN a.part_id = '013' THEN COALESCE(a.rpt_amt, 0.00) else 0.00 end) as rpt_amt, \n"+
				"(CASE WHEN part_id = '014' THEN proc_fee ELSE 0.00 END) AS upico,\n" + 
				"COALESCE(mri, 0.00) AS mri, COALESCE(fire, 0.00) as fire, \n" + 
				"interest, principal, COALESCE(vat, 0.00) as vat, balance, \n" + 
				"COALESCE(interest_rate, 0.00)\n" + 
				"FROM rf_client_schedule a\n" + 
				"WHERE entity_id = '"+old_entity_id+"' \n" + 
				//"AND unit_id = '"+old_unit_id+"' \n" + 
				"AND proj_id = '"+old_proj_id+"' \n" +
				"AND pbl_id = getinteger('"+old_unit_id+"')::VARCHAR \n"+
				"AND seq_no = "+old_seq_no+" \n"+
				"AND (CASE WHEN '"+req_id+"' ~*'(12|07)' THEN status_id = 'I' ELSE status_id = 'A' END)\n" + 
				"ORDER BY client_sched_id, scheddate";
		//}

		db.select(sql);
		FncSystem.out("Display Old Schedule", sql);
		if(db.isNotNull()){
			for(int x= 0; x<db.getRowCount(); x++){
				modelOldSched.addRow(db.getResult()[x]);
			}
			scrollOldSchedule.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblOldSchedule.getRowCount())));
			tblOldSchedule.packAll();
		}
	}

	private void displayChangeOfSchedule(String req_no){
		pgSelect db = new pgSelect();

		DefaultListModel listModel = new DefaultListModel();
		rowHeaderNewSchedule.setModel(listModel);

		String sql = "SELECT * FROM sp_generate_change_sched('"+req_no+"')";
		db.select(sql);

		FncSystem.out("Display sql for Generation of Change Schedule", sql);

		if(db.isNotNull()){
			for(int x= 0; x<db.getRowCount(); x++){
				modelNewSched.addRow(db.getResult()[x]);
				listModel.addElement(modelNewSched.getRowCount());
			}
		}
		scrollNewSchedule.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblNewSchedule.getRowCount())));
		tblNewSchedule.packAll();
	}

	private void displayChangeSchedule(Object [] data3, Boolean editable, String req_no){
		final_buyer_type = (String) data3[8];
		final_pmt_scheme = (String) data3[9];

		modelNewSched.clear();
		request_no = req_no;

		displayNewSchedule(req_no);
		displayOldSchedule(data3);
		btnSave.setEnabled(false);

		if(editable){
			menuSched.remove(menuRemove);
			dteNewSchedule.setEnabled(true);
			dteNewSchedule.setEditable(true);
			btnSave.setEnabled(true);
			System.out.println("New Sched Dater Editable");
		}
	}

	private Date getNextSchedDate(String part_id ,Date start_date){
		pgSelect db = new pgSelect();
		String int_unit = "";
		Integer interval = null;
		Date scheddate = null;

		String sql1 = "SELECT int_unit, interval::INT FROM mf_pay_scheme_detail WHERE pmt_scheme_id = '"+final_pmt_scheme+"' AND int_unit != '' AND part_id = '"+part_id+"'";
		db.select(sql1);
		//FncSystem.out("Display sql for mf_pay_scheme_detail", sql1);

		if(db.isNotNull()){
			int_unit = (String) db.getResult()[0][0];
			interval = (Integer) db.getResult()[0][1];
		}

		String sql = "SELECT get_schedule_date('"+final_proj_id+"', '"+start_date+"'::DATE::TIMESTAMP, '"+int_unit+"', \n"+
				"(CASE WHEN '"+part_id+"' = '014' THEN 1 ELSE "+interval+" END), '"+final_buyer_type+"')";
		db.select(sql);

		FncSystem.out("Display new scheddate", sql);

		if(db.isNotNull()){
			scheddate = (Date) db.getResult()[0][0];
		}
		return scheddate;
	}

	private void refreshTable(){
		for(int x = 0; x<modelNewSched.getRowCount(); x++){
			modelNewSched.setValueAt(null, x, 3);
			modelNewSched.setValueAt(null, x, 4);
			modelNewSched.setValueAt(null, x, 5);
			modelNewSched.setValueAt(null, x, 6);
			modelNewSched.setValueAt(null, x, 7);
			modelNewSched.setValueAt(null, x, 8);
			modelNewSched.setValueAt(null, x, 9);
			modelNewSched.setValueAt(null, x, 10);
			modelNewSched.setValueAt(null, x, 11);
			modelNewSched.setValueAt(null, x, 12);
		}
	}

	private void saveNewSchedule(String request_no, BigDecimal dp1, BigDecimal proc_fee1, BigDecimal rpt_amt1){
		pgSelect db = new pgSelect();

		ArrayList<String> listPartID = new ArrayList<String>();
		ArrayList<String> listSchedDate = new ArrayList<String>();

		for(int x = 0; x<modelNewSched.getRowCount(); x++){
			String part_id = (String) modelNewSched.getValueAt(x, 0);
			Date sched_date = (Date) modelNewSched.getValueAt(x, 2);

			System.out.printf("Display SchedDate: %s%n", sched_date);
			listPartID.add(String.format("'%s'", part_id));
			listSchedDate.add(String.format("'%s'", sched_date));

		}

		String part_id = listPartID.toString().replaceAll("\\[|\\]", "");
		String sched_date = listSchedDate.toString().replaceAll("\\[|\\]", "");

		//String sql = "SELECT sp_req_change_schedule('"+request_no+"', ARRAY["+part_id+"]::VARCHAR[], ARRAY["+sched_date+"]::TIMESTAMP WITHOUT TIME ZONE[], "+_OtherRequest.getFireHouseAPV_Rounded(final_model_id)+", "+dp1+", "+proc_fee1+" ,'"+UserInfo.EmployeeCode+"')";
		//String sql = "SELECT sp_req_change_schedule('"+request_no+"', ARRAY["+part_id+"]::VARCHAR[], ARRAY["+sched_date+"]::TIMESTAMP WITHOUT TIME ZONE[], "+_OtherRequest.getFireHouseAPV_Rounded(final_model_id)+",'"+UserInfo.EmployeeCode+"')";
		String sql = "SELECT sp_req_change_schedule_v2('"+request_no+"', ARRAY["+part_id+"]::VARCHAR[],ARRAY["+sched_date+"]::TIMESTAMP WITHOUT TIME ZONE[], "+dp1+", "+proc_fee1+", "+rpt_amt1+", false, '"+UserInfo.EmployeeCode+"')";
		db.select(sql);

		FncSystem.out("Display sql for Change Schedule", sql);

	}

	private Boolean toSave(){

		for(int x= 0; x<modelNewSched.getRowCount(); x++){
			Date scheddate = (Date) modelNewSched.getValueAt(x, 2);

			if(scheddate == null){
				JOptionPane.showMessageDialog(null, "Please input new Schedule Date", "Save", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}

		return true;
	}

	private void previewOldSchedule(){

		ArrayList<TD_OtherRequest_PreviewSchedSearched> listOldSchedule = new ArrayList<TD_OtherRequest_PreviewSchedSearched>();

		for(int x= 0; x<modelOldSched.getRowCount(); x++){

			String part_desc = (String) modelOldSched.getValueAt(x, 1);
			Date sched_date = (Date) modelOldSched.getValueAt(x, 2);
			BigDecimal amount = (BigDecimal) modelOldSched.getValueAt(x, 3);
			String proc_fee = (String) modelOldSched.getValueAt(x, 4);
			BigDecimal rpt_amt = (BigDecimal) modelOldSched.getValueAt(x, 5);
			BigDecimal mri = (BigDecimal) modelOldSched.getValueAt(x, 6);
			BigDecimal fire = (BigDecimal) modelOldSched.getValueAt(x, 7);
			BigDecimal interest = (BigDecimal) modelOldSched.getValueAt(x, 8);
			BigDecimal principal = (BigDecimal) modelOldSched.getValueAt(x, 9);
			BigDecimal vat = (BigDecimal) modelOldSched.getValueAt(x, 10);
			BigDecimal balance = (BigDecimal) modelOldSched.getValueAt(x, 11);
			BigDecimal int_rate = (BigDecimal) modelOldSched.getValueAt(x, 12);

			listOldSchedule.add(new TD_OtherRequest_PreviewSchedSearched(part_desc, sched_date, amount, proc_fee, rpt_amt ,mri, fire, interest, principal, vat, balance, int_rate));
		}

		Map<String, Object> mapParameters = new HashMap<String, Object>();

		mapParameters.put("entity_id", old_entity_id);
		mapParameters.put("proj_id", old_proj_id);
		mapParameters.put("unit_id", old_unit_id);
		mapParameters.put("seq_no", old_seq_no);
		mapParameters.put("connection", FncGlobal.connection);
		mapParameters.put("listOldSchedule", listOldSchedule);

		mapParameters.put("SUBREPORT_DIR", this.getClass().getResourceAsStream("/Reports/subrptOldSchedule.jasper"));

		//this.dispose();
		//this.setAlwaysOnTop(false);
		this.setModalityType(ModalityType.MODELESS);
		FncReport.generateReport("/Reports/rptClientReqOldSchedule.jasper", "Preview Schedule", mapParameters);

	}

	private void previewNewSchedule(){
		ArrayList<TD_OtherRequest_PreviewSchedSearched> listSchedule = new ArrayList<TD_OtherRequest_PreviewSchedSearched>();
		for(int x = 0; x<modelNewSched.getRowCount(); x++){
			String proc_fee = "";

			String part_id =  (String) modelNewSched.getValueAt(x, 0);
			String part_desc = (String) modelNewSched.getValueAt(x, 1);
			Date sched_date = (Date) modelNewSched.getValueAt(x, 2);
			BigDecimal amount = (BigDecimal) modelNewSched.getValueAt(x, 3);

			if(part_id.equals("014")){
				proc_fee = ((String) modelNewSched.getValueAt(x, 6).toString());
			}else{
				proc_fee = (String) modelNewSched.getValueAt(x, 4);
			}

			BigDecimal rpt = (BigDecimal) modelNewSched.getValueAt(x, 5);
			BigDecimal mri = (BigDecimal) modelNewSched.getValueAt(x, 7);
			BigDecimal fire = (BigDecimal) modelNewSched.getValueAt(x, 8);
			BigDecimal interest = (BigDecimal) modelNewSched.getValueAt(x, 9);
			BigDecimal principal = (BigDecimal) modelNewSched.getValueAt(x, 10);
			BigDecimal vat = (BigDecimal) modelNewSched.getValueAt(x, 11);
			BigDecimal balance = (BigDecimal) modelNewSched.getValueAt(x, 12);
			BigDecimal int_rate = (BigDecimal) modelNewSched.getValueAt(x, 13);

			//System.out.println("*************Dumaan dito");
			listSchedule.add(new TD_OtherRequest_PreviewSchedSearched(part_desc, sched_date, amount, proc_fee, rpt ,mri, fire, interest, principal, vat, balance, int_rate));
		}

		Map<String, Object> mapParameters = new HashMap<String, Object>();
		mapParameters.put("entity_name", txtEntityName.getText());
		mapParameters.put("entity_id", txtEntityID.getText());
		mapParameters.put("unit_id", txtUnitID.getText());
		mapParameters.put("unit_desc", txtUnitDesc.getText());
		mapParameters.put("project", txtProject.getText());
		mapParameters.put("house_model", txtHouseModel.getText());
		mapParameters.put("buyer_type", txtBuyerType.getText());
		mapParameters.put("pmt_scheme", pmt_scheme);
		mapParameters.put("selling_price", (BigDecimal) txtGSP.getValued());
		mapParameters.put("vat", (BigDecimal) txtVAT.getValued());
		mapParameters.put("discount", (BigDecimal) txtDiscount.getValued());
		mapParameters.put("nsp", (BigDecimal) txtNSP.getValued());
		mapParameters.put("dp_amt", (BigDecimal) txtDPAmt.getValued());
		mapParameters.put("ma_amt", (BigDecimal) txtMAAmt.getValued());
		mapParameters.put("dp_terms", txtDPTerms.getText());
		mapParameters.put("ma_terms", txtMATerms.getText());
		mapParameters.put("connection", FncGlobal.connection);
		mapParameters.put("listSchedule", listSchedule);

		mapParameters.put("SUBREPORT_DIR", this.getClass().getResourceAsStream("/Reports/subrptPreviewSchedule.jasper"));


		FncReport.generateReport("/Reports/rptPreviewSchedNew.jasper", "Preview Schedule", mapParameters);

	}

	public static void previewOldSchedulePosted(String request_no){

		pgSelect db = new pgSelect();

		String sql = "SELECT * FROM view_client_request_old_details('"+request_no+"')";
		db.select(sql);

		FncSystem.out("Display sql for Old Schedule Posted", sql);

		if(db.isNotNull()){

			String entity_id = (String) db.getResult()[0][0];
			String entity_name = (String) db.getResult()[0][1];
			String unit_id = (String) db.getResult()[0][2];
			String unit_desc = (String) db.getResult()[0][3];
			String proj_name = (String) db.getResult()[0][4];
			String model_desc = (String) db.getResult()[0][5];
			String buyer_type = (String) db.getResult()[0][6];
			String pmt_scheme = (String) db.getResult()[0][7];
			BigDecimal gsp = (BigDecimal) db.getResult()[0][8];
			BigDecimal vat = (BigDecimal) db.getResult()[0][9];
			BigDecimal discount = (BigDecimal) db.getResult()[0][10];
			BigDecimal nsp = (BigDecimal) db.getResult()[0][11];
			BigDecimal dp_amt = (BigDecimal) db.getResult()[0][12];
			BigDecimal ma_amt = (BigDecimal) db.getResult()[0][13];
			String dp_term = (String) db.getResult()[0][14];
			String ma_term = (String) db.getResult()[0][15];

			Map<String, Object>mapParameters = new HashMap<String, Object>();

			mapParameters.put("entity_id", entity_id);
			mapParameters.put("entity_name", entity_name);
			mapParameters.put("unit_id", unit_id);
			mapParameters.put("unit_desc", unit_desc);
			mapParameters.put("proj_name", proj_name);
			mapParameters.put("model_desc", model_desc);
			mapParameters.put("buyer_type", buyer_type);
			mapParameters.put("pmt_scheme", pmt_scheme);
			mapParameters.put("gsp", gsp);
			mapParameters.put("vat", vat);
			mapParameters.put("discount", discount);
			mapParameters.put("nsp", nsp);
			mapParameters.put("dp_amt", dp_amt);
			mapParameters.put("ma_amt", ma_amt);
			mapParameters.put("dp_term", dp_term);
			mapParameters.put("ma_term", ma_term);
			mapParameters.put("request_no", request_no);

			FncReport.generateReport("/Reports/rptClientRequest_OldSched.jasper", "Old Schedule", mapParameters);
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		String actionCommand = arg0.getActionCommand();

		if(actionCommand.equals("Print Preview")){
			Object[] options = new Object[]{"New Schedule", "Old Schedule"};
			int index = JOptionPane.showOptionDialog(null, "Select Schedule to Preview", "Preview Schedule", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			System.out.printf("Display index: %s%n", index);

			if(index == 0){
				if(toSave()){
					previewNewSchedule();
				}
			}
			if(index == 1){
				previewOldSchedule();
			}
		}

		if(actionCommand.equals("Save")){
			if(toSave()){
				if(JOptionPane.showConfirmDialog(pnlOtherReq_PreviewSchedule.this, "Are you sure you want to save?", actionCommand, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					new Thread(new Runnable() {

						@Override
						public void run() {
							glass.start("Saving Schedule...");

							BigDecimal dp1 = new BigDecimal("0.00");
							BigDecimal proc_fee1 = new BigDecimal("0.00");
							BigDecimal rpt_amt1 = new BigDecimal("0.00");

							if(txtDPTerms.getText().equals("1")) {
								saveNewSchedule(request_no, new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"));
							}else {
								if(JOptionPane.showConfirmDialog(pnlOtherReq_PreviewSchedule.this, "Do you want to input 1st DP Amount?", "Save", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
									dlg_ClientRequestDP1 dp_amt = new dlg_ClientRequestDP1(FncGlobal.homeMDI, "DP 1 Amount");
									dp_amt.setLocationRelativeTo(null);
									dp_amt.setVisible(true);

									dp1 = dp_amt.getDP1();
									proc_fee1 = dp_amt.getProc_fee1();
									rpt_amt1 = dp_amt.getRpt_amt1();

									saveNewSchedule(request_no, dp1, proc_fee1, rpt_amt1);
								}else{
									saveNewSchedule(request_no, new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"));
								}
							}

							//saveNewSchedule(request_no, new BigDecimal("0.00"));

							displayNewSchedule(request_no);
							//XXX CHECK IF DISABLE BUTTON HERE

							glass.stop();
							JOptionPane.showMessageDialog(null, "New Schedule has been saved");

						}
					}).start();
					//this.dispose(); //XXX CHECK IF NEED TO DISPOSE HERE
				}
			}
		}

		if(actionCommand.equals("Cancel")){
			if(JOptionPane.showConfirmDialog(pnlOtherReq_PreviewSchedule.this, "Are you sure you want to cancel?", actionCommand, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
				this.dispose();
			}
		}
	}

	class PopupTriggerListener_panel extends MouseAdapter {
		public void mousePressed(MouseEvent ev) {
			if (ev.isPopupTrigger()) {
				menuSched.show(ev.getComponent(), ev.getX(), ev.getY());
			}
		}
		public void mouseReleased(MouseEvent ev) {
			if (ev.isPopupTrigger()) {
				menuSched.show(ev.getComponent(), ev.getX(), ev.getY());
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
