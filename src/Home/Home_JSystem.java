package Home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JInternalFrame.JDesktopIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.InternalFrameUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXSearchField;

import com.cloudgarden.layout.AnchorLayout;
import com.lowagie.text.Rectangle;

import Accounting.Cashiering.CashReceiptBook;
import Accounting.Collections.CheckStatusMonitoring;
import Accounting.Commissions.Commission_Schedule_Generator;
import Accounting.ContractorsPayment.BackchargeUtilities;
import Accounting.ContractorsPayment.ContractorsBilling;
import Accounting.Disbursements.CheckVoucher;
import Accounting.Disbursements.DocsProcessing;
import Accounting.Disbursements.RequestForPayment;
import Accounting.Disbursements.reversalProccessing;
import Accounting.FixedAssets.AssetMonitoring;
import Accounting.FixedAssets.AssetMonitoring2;
import Accounting.FixedAssets.PurchaseOrder;
import Accounting.FixedAssets.addassetperipheral;
import Accounting.FixedAssets.addassetperipheral2;
import Accounting.GeneralLedger.GeneralLedger;
import Accounting.GeneralLedger.JournalVoucher;
import Accounting.OfficeSupplies.OfficeSupplies;
import Accounting.OfficeSupplies.additem_supplies;
import Admin.AddEditEntityType;
import Buyers.ClientServicing.BuyersRequestforTechnicalDocuments;
import Buyers.ClientServicing.CARD;
import Buyers.ClientServicing.ClientFeedback;
import Buyers.ClientServicing.ClientFollowUp;
import Buyers.ClientServicing.ClientInformation;
import Buyers.ClientServicing.ClientRequestOldDetails;
import Buyers.ClientServicing.DocumentsMonitoring;
import Buyers.ClientServicing.HoldingAndReservation;
import Buyers.ClientServicing.OrderOfPayment;
import Buyers.ClientServicing.PreDocsEvaluation;
import Buyers.CreditandCollections.RegularBillingAndNotices;

//import Buyers.CreditandCollections.RealTimeDebitPiso;

import Database.pgSelect;
import Database.pgUpdate;
import DateChooser._JDateChooser;
import File.ChangePassword;
import Functions.FncGlobal;
import Functions.FncLookAndFeel;
import Functions.FncReport;
import Functions.FncSystem;
import Functions.ImagePanel;
import Functions.UserInfo;
import Lookup._JLookupTable;
import Projects.BiddingandAwarding.NoticeToProceed;
import Projects.SalesandMarketing.PaymentScheme;
import Projects.SalesandMarketing.Pricelist;
import Projects.SalesandMarketing.Tripping_Cost;
import Reports.Accounting.AssetCard;
import Reports.Accounting.PrintAssetSticker;
import Reports.ClientServicing.HoldUnits;
import Reports.ClientServicing.OpenUnits;
import Utilities.ChartofAccounts;
import Utilities.Comm_Scheme;
import Utilities.SalesAgent;
import Utilities.TemporaryCheckVoucher;
import Utilities.ZipCodes;
import Utilities.fixedasset_peripheral;
import System.Add_Edit_Holidays;

//import System.DcrfInactivePayments;

//import Utilities.techDescEntry;
import Window.DesktopScrollPane;
import Window.MDIDesktopPane;
import Window.WindowMenu;
import components.JTBorderFactory;
import components._BorderFactory;
import components._JButton;
import components._JInternalFrame;
import components._JMenuToolbarButton;

@SuppressWarnings("unused")
public class Home_JSystem extends JXFrame implements ActionListener, WindowListener {

	private static final long serialVersionUID = 1L;

	private static MDIDesktopPane DesktopPane;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JXPanel pnlNorth;
	private JToolBar toolbar;
	private JMenuBar menuBar;

	private JXSearchField searchField;

	private ImageIcon MINIMIZE_ICON = new ImageIcon(this.getClass().getClassLoader().getResource("Images/minimize.png"));
	private ImageIcon MAXIMIZE_ICON = new ImageIcon(this.getClass().getClassLoader().getResource("Images/maximized.png"));
	private ImageIcon CLOSE_ICON = new ImageIcon(this.getClass().getClassLoader().getResource("Images/close.png"));
	//private ImageIcon DELETE_TAB_ICON = new ImageIcon(this.getClass().getClassLoader().getResource("Images/delete-16.png"));

	private _JMenuToolbarButton btnMinimize;
	private _JMenuToolbarButton btnMaximize;
	private _JMenuToolbarButton btnClose;

	private JXPanel pnlSouthCenter;
	private JProgressBar progressBar;
	private JMenu menuBookmark;

	private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMMMM dd, yyyy (EEEEE - hh:mm a) ");
	private JColorChooser colorChooser = new JColorChooser(FncLookAndFeel.defaultColor);

	private String Image;
	private JXPanel panCutOff; 
	private _JDateChooser[] dteDate; 

	private TimerTask tmr;
	private Timer timerDCRF;

	private JXLabel lblDCRFList; 
	private JXLabel lblDateTime; 
	private Boolean dept_code;

	private static Integer intDCRFCount = 0; 

	private Integer intHomeWidth;
	private Integer intHomeHeight; 

	final Toolkit toolkit = Toolkit.getDefaultToolkit();

	private static JLabel lblDCRF; 

	//private User_Queuing pnlQueue;

	public Home_JSystem() throws HeadlessException {
		initGUI();
		DesktopPane.requestFocus();
	}

	public Home_JSystem(GraphicsConfiguration gc) {
		super(gc);
		initGUI();
		DesktopPane.requestFocus();
	}

	public Home_JSystem(String title,String Image) throws HeadlessException {
		super(title);
		this.Image = Image;
		initGUI();
	}

	public Home_JSystem(String title) throws HeadlessException {
		super(title);
		initGUI();
	}

	public Home_JSystem(String title, GraphicsConfiguration gc) {
		super(title, gc);
		initGUI();
		DesktopPane.requestFocus();
	}

	private void initGUI() {
		//System.out.println("Test");
		try {
			this.getContentPane().setLayout(new BorderLayout());
			this.setIconImage(FncLookAndFeel.iconSystem);
			this.setExtendedState(MAXIMIZED_BOTH);

			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(800, 600);

			this.addWindowListener(this);
			this.getRootPane().registerKeyboardAction(this, "Console", KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
			{
				ImagePanel pnlMain = new ImagePanel(Image);
				getContentPane().add(pnlMain, BorderLayout.CENTER);
				pnlMain.setLayout(new BorderLayout());
				pnlMain.setBorder( BorderFactory.createEmptyBorder());
				{
					pnlNorth = new JXPanel(new BorderLayout());
					pnlMain.add(pnlNorth, BorderLayout.NORTH);
					pnlNorth.setPreferredSize(new Dimension(0, 30));
					{
						toolbar = new JToolBar(/*"Pwede", JToolBar.HORIZONTAL*/); 
						pnlNorth.add(toolbar, BorderLayout.CENTER);
						toolbar.setFloatable(false);
						toolbar.setRollover(true);
					}
					{
						searchField = new JXSearchField("Search..."); 
						pnlNorth.add(searchField, BorderLayout.EAST);
						searchField.setBorder(new _BorderFactory(Color.DARK_GRAY, 8));
						searchField.setPreferredSize(new Dimension(250, 0));
						searchField.setMinimumSize(new Dimension(250, 0));
						searchField.setMaximumSize(new Dimension(250, 0));
						searchField.setFindAction(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								String text = searchField.getText();

								if(UserInfo.EmployeeCode.equals("901100") == false) {

									_JLookupTable dlg = new _JLookupTable(FncGlobal.homeMDI, null, "Clients", "SELECT * FROM view_jsearch('"+ text +"');", false);
									dlg.setPreferredSize(new Dimension(800, 420));
									dlg.setSize(new Dimension(800, 420));
									dlg.setLocationRelativeTo(FncGlobal.homeMDI);
									dlg.setVisible(true);

									Object[] data = dlg.getReturnDataSet();
									if (data != null) {
										String entity_id = (String) data[0];
										String proj_id = (String) data[6];
										String pbl_id = (String) data[3];
										Integer seq_no = (Integer) data[4];

										
									}
								}
							}
						});

						searchField.addKeyListener(new KeyAdapter() {
							public void keyReleased(KeyEvent arg0) {
								if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
									JXSearchField field = ((JXSearchField) arg0.getSource());
									field.getFindButton().doClick();
								}
							}
						});
					}
				}
				{
					{
						DesktopPane = new MDIDesktopPane();
						DesktopPane.setLayout(new AnchorLayout());
						DesktopPane.setOpaque(false);
						DesktopPane.registerKeyboardAction(this, KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); 

						{
							{
								final JPanel panCard = new JPanel(new GridLayout(3, 1, 5, 10)); 
								DesktopPane.add(panCard);
								panCard.setOpaque(false);
								move(panCard, screenSize.width-25, screenSize.height-25, 150, 400); 
								{
									{
										final JLabel lblCard = new JLabel(FncLookAndFeel.iconCARD); 
										panCard.add(lblCard, BorderLayout.CENTER);
										lblCard.setToolTipText("<html><body><h3>Customers Account Record Details</h3></body></html>");
										lblCard.addMouseListener(new MouseListener() {
											public void mouseReleased(MouseEvent e) {

											}

											public void mousePressed(MouseEvent e) {
												lblCard.setBorder(BorderFactory.createLoweredBevelBorder());
												redo();
											}

											public void mouseExited(MouseEvent e) {
												lblCard.setBorder(null);
												redo();
											}

											public void mouseEntered(MouseEvent e) {
												lblCard.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
												redo();
											}

											public void mouseClicked(MouseEvent e) {
												
											}

											void redo() {
												lblCard.repaint();
												lblCard.revalidate();
											}
										});
									}
									{
										final JLabel lblSMS = new JLabel(FncLookAndFeel.iconSMS); 
										panCard.add(lblSMS, BorderLayout.CENTER);
										lblSMS.setToolTipText("<html><body><h3>SMS</h3></body></html>");
										lblSMS.addMouseListener(new MouseListener() {
											public void mouseReleased(MouseEvent e) {

											}

											public void mousePressed(MouseEvent e) {
												lblSMS.setBorder(BorderFactory.createLoweredBevelBorder());
												redo();
											}

											public void mouseExited(MouseEvent e) {
												lblSMS.setBorder(null);
												redo();
											}

											public void mouseEntered(MouseEvent e) {
												lblSMS.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
												redo();
											}

											public void mouseClicked(MouseEvent e) {
												
											}

											void redo() {
												lblSMS.repaint();
												lblSMS.revalidate();
											}
										});
									}
									{
										lblDCRF = new JLabel(FncLookAndFeel.iconDCRF); 
										panCard.add(lblDCRF, BorderLayout.CENTER);
										lblDCRF.setToolTipText("<html><body><h3>Data Change Request</h3></body></html>");
										lblDCRF.addMouseListener(new MouseListener() {
											public void mouseReleased(MouseEvent e) {

											}

											public void mousePressed(MouseEvent e) {
												lblDCRF.setBorder(BorderFactory.createLoweredBevelBorder());
												redo();
											}

											public void mouseExited(MouseEvent e) {
												lblDCRF.setBorder(null);
												redo();
											}

											public void mouseEntered(MouseEvent e) {
												lblDCRF.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
												redo();
											}

											public void mouseClicked(MouseEvent e) {
												JOptionPane.showMessageDialog(null, "Quick DCRF feature not yet ready");

												lblDCRF.setBorder(null);
												redo();
											}

											void redo() {
												lblDCRF.repaint();
												lblDCRF.revalidate();
											}
										});
										withPending(); 
									}

							
								}

							}

							
						}

						DesktopPane.addComponentListener(new ComponentListener() {

							public void componentShown(ComponentEvent e) {

							}

							public void componentResized(ComponentEvent e) {
								intHomeWidth = ((MDIDesktopPane) e.getSource()).getWidth(); 
								intHomeHeight = ((MDIDesktopPane) e.getSource()).getHeight();

								/*if (pnlAnnouncement!=null) {
									move(pnlAnnouncement, 470, intHomeHeight, 470, 400);
								}*/


								/*if (pnlQueue!=null) {
									//move(pnlQueue, 1290, 330, 600, 300);
									move(pnlQueue, screenSize.width-100, 330, 600, 300);
								}*/

							}

							public void componentMoved(ComponentEvent e) {

							}

							public void componentHidden(ComponentEvent e) {

							}
						});
					}

					DesktopScrollPane scrollCenter = new DesktopScrollPane(DesktopPane);
					pnlMain.add(scrollCenter, BorderLayout.CENTER);
					scrollCenter.setOpaque(false);
					scrollCenter.getViewport().setOpaque(false);
				}
				{
					JXPanel pnlSouth = new JXPanel();
					pnlMain.add(pnlSouth, BorderLayout.SOUTH);
					pnlSouth.setLayout(new BorderLayout(1, 1));
					pnlSouth.setPreferredSize(new Dimension(790, 22));
					{
						{
							JXPanel panDivider = new JXPanel(new BorderLayout(5, 5)); 
							pnlSouth.add(panDivider, BorderLayout.LINE_START);

							try {
								panDivider.setPreferredSize(new Dimension(((UserInfo.Department_Alias.equals("JST") || UserInfo.Department_Alias.equals("RDT"))?400:200), 0));
							} catch (NullPointerException ex) {
								panDivider.setPreferredSize(new Dimension(200, 0));
							}

							{
								{
									JXLabel lblUser = new JXLabel(String.format(" %s, %s | %s ", UserInfo.LastName, UserInfo.FirstName, UserInfo.Department_Alias));
									panDivider.add(lblUser, BorderLayout.CENTER);
									lblUser.setFont(FncLookAndFeel.systemFont_Bold.deriveFont(10f));
									lblUser.setBorder(BorderFactory.createLoweredSoftBevelBorder());
									lblUser.setToolTipText("User | Department");

									lblUser.setHorizontalAlignment(JLabel.CENTER);
								}
								{
									lblDCRFList = new JXLabel("");



									try {
										System.out.println("UserInfo.Department_Alias: "+UserInfo.Department_Alias.equals("JST"));

										System.out.println("UserInfo.Department_Alias: "+UserInfo.Department_Alias.equals("JST"));
										if (UserInfo.Department_Alias.equals("JST") || UserInfo.Department_Alias.equals("RDT")) {
											panDivider.add(lblDCRFList, BorderLayout.LINE_END);
										}
									} catch (NullPointerException ex) {

									}

									lblDCRFList.setFont(FncLookAndFeel.systemFont_Bold.deriveFont(10f));
									lblDCRFList.setHorizontalAlignment(JLabel.CENTER);
									lblDCRFList.setBorder(BorderFactory.createLoweredSoftBevelBorder());
									lblDCRFList.setToolTipText("Server | Date");
									Blink(lblDCRFList);
									lblDCRFList.setPreferredSize(new Dimension(200, 0));
									lblDCRFList.addMouseListener(new MouseListener() {
										public void mouseReleased(MouseEvent e) {

										}

										public void mousePressed(MouseEvent e) {

										}

										public void mouseExited(MouseEvent e) {

										}

										public void mouseEntered(MouseEvent e) {

										}

										public void mouseClicked(MouseEvent e) {
											JDialog dialog = new JDialog(FncGlobal.homeMDI, "DCRF Monitoring", false); 
											dialog.setLayout(new BorderLayout(0, 0));

											final Toolkit toolkit = Toolkit.getDefaultToolkit();
											final Dimension screenSize = toolkit.getScreenSize();


//											if(UserInfo.EmployeeCode.equals("900876")) {
//												dialog.setSize(screenSize.width-1500, screenSize.height-50);
//												dialog.setResizable(true);
//											}else {
												dialog.setSize(screenSize.width-100, screenSize.height-100);
												dialog.setResizable(false);
											//}


											/*
											DCRFMonitoring2 dcrfMon = new DCRFMonitoring2();
											JScrollPane scr = new JScrollPane(dcrfMon); 
											dialog.add(scr, BorderLayout.CENTER);
											 */
										}
									});
								}
							}
						}

						{
							pnlSouthCenter = new JXPanel();
							pnlSouth.add(pnlSouthCenter, BorderLayout.CENTER);
							pnlSouthCenter.setLayout(new BorderLayout());
							//pnlSouthCenter.setOpaque(false);
							pnlSouthCenter.setBorder(BorderFactory.createLoweredSoftBevelBorder());
							{
								progressBar = new JProgressBar(); 
								pnlSouthCenter.add(progressBar, BorderLayout.CENTER);
								progressBar.setVisible(false);

								ActionMap mapAction = new ActionMap();
								mapAction.put(JProgressBar.UNDEFINED_CONDITION, new AbstractAction() {
									private static final long serialVersionUID = 1L;

									public void actionPerformed(ActionEvent e) {
										System.out.println("**********Sample ActionMap!");
									}
								});
							}
						}
						{
							JXPanel panDateAndCutOff = new JXPanel(new BorderLayout(5, 5)); 
							pnlSouth.add(panDateAndCutOff, BorderLayout.LINE_END);
							panDateAndCutOff.setPreferredSize(new Dimension(425, 0));
							{
								{
									lblDateTime = new JXLabel(String.format(" %s ", dateTimeFormat.format(Calendar.getInstance().getTime())));
									panDateAndCutOff.add(lblDateTime, BorderLayout.CENTER);
									lblDateTime.setFont(FncLookAndFeel.systemFont_Bold.deriveFont(10f));
									lblDateTime.setHorizontalAlignment(JLabel.CENTER);
									lblDateTime.setBorder(BorderFactory.createLoweredSoftBevelBorder());
									lblDateTime.setToolTipText("Server | Date");
									setTime(lblDateTime);
								}
								{
									JXPanel panCutOffControl = new JXPanel(new BorderLayout(5, 5)); 
									panDateAndCutOff.add(panCutOffControl, BorderLayout.LINE_END);
									panCutOffControl.setBorder(BorderFactory.createLoweredSoftBevelBorder());
									panCutOffControl.setPreferredSize(new Dimension(100, 0));
									{
										JLabel lblCutOff = new JLabel("View Cut-Off"); 
										panCutOffControl.add(lblCutOff, BorderLayout.CENTER); 
										lblCutOff.setFont(FncLookAndFeel.systemFont_Bold.deriveFont(10f));
										lblCutOff.setHorizontalAlignment(JLabel.CENTER);
										lblCutOff.addMouseListener(new MouseListener() {
											public void mouseReleased(MouseEvent e) {

											}

											public void mousePressed(MouseEvent e) {

											}

											public void mouseExited(MouseEvent e) {

											}

											public void mouseEntered(MouseEvent e) {

											}

											public void mouseClicked(MouseEvent e) {
												CreateCutOffControl();
												JOptionPane.showOptionDialog(getContentPane(), panCutOff, "Cut-Off", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[] {"			CONFIRM			"}, JOptionPane.OK_OPTION);
											}
										});
									}
								}
							}
						}
					}
				}
			}
			{
				menuBar = new JMenuBar(); 
				setJMenuBar(menuBar);
				{
					JMenu menuFile = new JMenu("File");
					menuBar.add(menuFile);
					menuFile.setMnemonic(KeyEvent.VK_F);
					{
						JMenuItem menuitemOptions = new JMenuItem("Options");
						menuFile.add(menuitemOptions);
						menuitemOptions.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
									public void stateChanged(ChangeEvent e) {
										//pnlMain.setBackgroundPainter(FncPanelPainter.paint(colorChooser.getColor()));
										//pnlMain.repaint();
									}
								});

								ActionListener actionOK = new ActionListener() {
									public void actionPerformed(ActionEvent event) {
										//FncLookAndFeel.windowString_Dark = colorChooser.getColor();
										Color selectedColor = colorChooser.getColor();

										if(selectedColor == FncLookAndFeel.defaultColor){
											FncLookAndFeel.setDefaultColor();
											return;
										}
										int red = selectedColor.getRed();
										int green = selectedColor.getGreen();
										int blue = selectedColor.getBlue();

										FncLookAndFeel.setColor(red, green, blue);
									}
								};

								ActionListener actionCANCEL = new ActionListener() {
									public void actionPerformed(ActionEvent event) {
										//pnlMain.setBackgroundPainter(FncPanelPainter.paint(FncLookAndFeel.grayColor));
										//pnlMain.repaint();
									}
								};

								JDialog dialog = JColorChooser.createDialog(FncGlobal.homeMDI, "Options", true, colorChooser, actionOK, actionCANCEL);
								dialog.setVisible(true);
							}
						});
					}
					
					{
						JMenuItem menuitemUserManual = new JMenuItem("User Manual");
						menuFile.add(menuitemUserManual);
						menuitemUserManual.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								if (Desktop.isDesktopSupported()) {
									InputStream jarPdf = getClass().getClassLoader().getResourceAsStream("File/JSystem Beginners Guide.pdf");

									try {
										File pdfTemp = File.createTempFile("User_Guide", ".pdf");
										pdfTemp.deleteOnExit();

										FileOutputStream fos = new FileOutputStream(pdfTemp);
										while (jarPdf.available() > 0) {
											fos.write(jarPdf.read());
										} // while (pdfInJar.available() > 0)
										fos.close();
										Desktop.getDesktop().open(pdfTemp);
									} // try

									catch (IOException ex) {
									} // catch (IOException e)
								}
							}
						});
					}
					{
						menuFile.add(new JSeparator());
					}
					{
						JMenuItem menuitemLogout = new JMenuItem("Logout");
						menuFile.add(menuitemLogout);
						menuitemLogout.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								for(JInternalFrame comp : DesktopPane.getAllFrames()){
									System.out.println(comp.getClass().getSimpleName());
								}
								System.out.println("Dumaan dito sa Logout");
								if(JOptionPane.showConfirmDialog(Home_JSystem.this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null) == JOptionPane.YES_OPTION){
									pgSelect db = new pgSelect();
									String SQL = "SELECT sp_audit_log_details('"+UserInfo.EmployeeCode+"', false)";
									db.select(SQL);
									System.exit(0);
									System.out.println("Dumaan dito sa System Exit");
								}
							}
						});
					}
					{
						JMenuItem menuitemExit = new JMenuItem("Exit");
						menuFile.add(menuitemExit);
						menuitemExit.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								pgSelect db = new pgSelect();
								String SQL = "SELECT sp_audit_log_details('"+UserInfo.EmployeeCode+"', false)";
								db.select(SQL);
								System.exit(0);
							}
						});
					}
				}
				{
					JMenu menuTransaction = new JMenu("Transaction");
					menuBar.add(menuTransaction);
					menuTransaction.setMnemonic(KeyEvent.VK_T);
					{
						JMenu menuAccounting = new JMenu("Accounting");
						menuTransaction.add(menuAccounting);
						{
							JMenu menuCashiering = new JMenu("Cashiering");
							menuAccounting.add(menuCashiering);
							{
								JMenuItem menuitemReceiptDetails = new JMenuItem("Receipt Details");
								menuCashiering.add(menuitemReceiptDetails);
								menuitemReceiptDetails.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("CashReceiptBook")){
											CashReceiptBook crb = new CashReceiptBook();
											addWindow(crb, e);
										}
									}
								});
							}
						}
						{
							JMenu menuCollections = new JMenu("Collections");
							menuAccounting.add(menuCollections);
							{
								JMenuItem menuitemCheckMonitoring = new JMenuItem("Check Status Monitoring");
								menuCollections.add(menuitemCheckMonitoring);
								menuitemCheckMonitoring.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("CheckStatusMonitoring")){
											CheckStatusMonitoring csm = new CheckStatusMonitoring();
											addWindow(csm, e);
										}
									}
								});
							}
						}
						{
							JMenu menuCommission = new JMenu("Commission");
							menuAccounting.add(menuCommission);
							{
								JMenuItem menuitemCommSchedGenerator = new JMenuItem("Commission Schedule Generator");
								menuCommission.add(menuitemCommSchedGenerator);
								menuitemCommSchedGenerator.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("Commission_Sample_Schedule_Generator")){
											Commission_Schedule_Generator comm_sched = new Commission_Schedule_Generator();
											addWindow(comm_sched, e);
										}
									}
								});
							}
						}
						{
							JMenu menuContractors = new JMenu("Contractors");
							menuAccounting.add(menuContractors);
							{
								JMenuItem menuitemContractorsBilling = new JMenuItem("Contractors Progress Billing");
								menuContractors.add(menuitemContractorsBilling);
								menuitemContractorsBilling.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("ContractorsBilling")){
											ContractorsBilling cont_bill = new ContractorsBilling();
											addWindow(cont_bill, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemBachargeUtilities = new JMenuItem("BackCharge Utilities");
								menuContractors.add(menuitemBachargeUtilities);
								menuitemBachargeUtilities.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("BackchargeUtilities")){
											BackchargeUtilities bu = new BackchargeUtilities();
											addWindow(bu, e);
										}
									}
								});
							}
						}
						{
							JMenu menuDisbursement = new JMenu("Disbursements");
							menuAccounting.add(menuDisbursement);
							{
								JMenuItem menuitemRequestForPayment = new JMenuItem("Request for Payments");
								menuDisbursement.add(menuitemRequestForPayment);
								menuitemRequestForPayment.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("RequestForPayment")){
											RequestForPayment drf = new RequestForPayment();
											addWindow(drf, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemCheckVoucher = new JMenuItem("Check Voucher");
								menuDisbursement.add(menuitemCheckVoucher);
								menuitemCheckVoucher.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {										
										if(isNotExisting("CheckVoucher")){
											CheckVoucher chk_vchr = new CheckVoucher();
											addWindow(chk_vchr, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemDocsProcessing = new JMenuItem("Documents Processing");
								menuDisbursement.add(menuitemDocsProcessing);
								menuitemDocsProcessing.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {										
										if(isNotExisting("DocsProcessing")){
											DocsProcessing doc_proc = new DocsProcessing();
											addWindow(doc_proc, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemreversal_proc = new JMenuItem("Reversal Processing");
								menuDisbursement.add(menuitemreversal_proc);
								menuitemreversal_proc.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {										
										if(isNotExisting("DocsProcessing")){
											reversalProccessing rev_proc = new reversalProccessing();
											addWindow(rev_proc, e);
										}
									}
								});
							}
						}
						{
							JMenu menuFixedAssets = new JMenu("Fixed Assets");
							menuAccounting.add(menuFixedAssets);
							{
								JMenuItem menuitemAssetMonitoring = new JMenuItem("Asset Monitoring");
								menuFixedAssets.add(menuitemAssetMonitoring);
								menuitemAssetMonitoring.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("AssetMonitoring")){
											AssetMonitoring2 am = new AssetMonitoring2();
											addWindow(am, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemaddperipheral = new JMenuItem("Add Asset Peripherals");
								menuFixedAssets.add(menuitemaddperipheral);
								menuitemaddperipheral.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("Add Asset Peripherals")){
											addassetperipheral2 ap = new addassetperipheral2();
											addWindow(ap, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemaddperipheral = new JMenuItem("Purchase Order");
								menuFixedAssets.add(menuitemaddperipheral);
								menuitemaddperipheral.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("Purchase Order")){
											PurchaseOrder po = new PurchaseOrder();
											addWindow(po, e);
										}
									}
								});
							}
						}
						{
							JMenu menuGL = new JMenu("General Ledger");
							menuAccounting.add(menuGL);
							{
								JMenuItem menuitemGL = new JMenuItem("General Ledger");
								menuGL.add(menuitemGL);
								menuitemGL.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("GeneralLedger")){
											GeneralLedger gl = new GeneralLedger();
											addWindow(gl, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemJV = new JMenuItem("Journal Voucher");
								menuGL.add(menuitemJV);
								menuitemJV.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("JournalVoucher")){
											JournalVoucher jv = new JournalVoucher();
											addWindow(jv, e);
										}
									}
								});
							}
						}
						{
							JMenu menusupplies = new JMenu("Supplies");
							menuAccounting.add(menusupplies);
							{
								JMenuItem menuitemofficesupplies = new JMenuItem("Office Supplies");
								menusupplies.add(menuitemofficesupplies);
								menuitemofficesupplies.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("Office Supplies")) {
											OfficeSupplies os = new OfficeSupplies();
											addWindow(os, e);
										}
									}
								});
							}
							{
								JMenuItem menuitem_additemsupplies = new JMenuItem("Add Item Supplies");
								menusupplies.add(menuitem_additemsupplies);
								menuitem_additemsupplies.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
									if(isNotExisting("Add Item Supplies")) {
										additem_supplies ais = new additem_supplies();
										addWindow(ais,e);
									}
									}
								});
							}
						}
					}
					{
						JMenu menuBuyers = new JMenu("Buyers");
						menuTransaction.add(menuBuyers);
						{
							JMenu menuClientServicing = new JMenu("Client Servicing");
							menuBuyers.add(menuClientServicing);
							{ //ADDED BY JOHN LESTER FATALLO 11-27-14
								JMenuItem menuitemClientFeedback = new JMenuItem("Client Feedback");
								menuClientServicing.add(menuitemClientFeedback);
								menuitemClientFeedback.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("ClientFeedback")){
											ClientFeedback cf = new ClientFeedback();
											addWindow(cf, e);
										}
									}
								});
							}
							{
								JMenuItem menuItemClientFollowUp = new JMenuItem("Client Follow Up");
								menuClientServicing.add(menuItemClientFollowUp);
								menuItemClientFollowUp.addActionListener(new ActionListener() {

									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("ClientFollowUp")){
											ClientFollowUp cfu = new ClientFollowUp();
											addWindow(cfu, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemClientInformation = new JMenuItem("Client Information");
								menuClientServicing.add(menuitemClientInformation);
								menuitemClientInformation.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("ClientInformation")){
											ClientInformation ci = new ClientInformation();
											addWindow(ci, e);
										}
									}
								});
							}
							{
								JMenu menuClientRequest = new JMenu("Client Request");
								menuClientServicing.add(menuClientRequest);
								
								{ 
									JMenuItem menuitemBuyersRequestforTechDoc = new JMenuItem("Technical Documents");
									menuClientRequest.add(menuitemBuyersRequestforTechDoc);
									menuitemBuyersRequestforTechDoc.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											if(isNotExisting("BuyersRequestforTechnicalDocuments")){
												BuyersRequestforTechnicalDocuments brt = new BuyersRequestforTechnicalDocuments();
												addWindow(brt, e);
											}
										}
									});
								}
							}
							{
								JMenuItem menuitemHoldingReservation = new JMenuItem("Holding / Reservation");
								menuClientServicing.add(menuitemHoldingReservation);
								menuitemHoldingReservation.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("HoldingAndReservation")){
											HoldingAndReservation har = new HoldingAndReservation();
											addWindow(har, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemOrderOfPayments = new JMenuItem("Order of Payments");
								menuClientServicing.add(menuitemOrderOfPayments);
								menuitemOrderOfPayments.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("OrderOfPayment")){
											OrderOfPayment oop = new OrderOfPayment();
											addWindow(oop, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemDocumentsMonitoring = new JMenuItem("Documents Monitoring");
								menuClientServicing.add(menuitemDocumentsMonitoring);
								menuitemDocumentsMonitoring.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("DocumentsMonitoring")){
											DocumentsMonitoring dm = new DocumentsMonitoring();
											addWindow(dm, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemRegularBillingandNotices = new JMenuItem("Regular Billing and Notices_");
								menuClientServicing.add(menuitemRegularBillingandNotices);
								menuitemRegularBillingandNotices.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("RegularBillingAndNotices")){
											RegularBillingAndNotices rbn = new RegularBillingAndNotices();
											addWindow(rbn, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemClientRequestOldDetails = new JMenuItem("Client Request Old Details");
								menuClientServicing.add(menuitemClientRequestOldDetails);
								menuitemClientRequestOldDetails.addActionListener(new ActionListener() {

									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("ClientRequestOldDetails")){
											ClientRequestOldDetails crod = new ClientRequestOldDetails();
											addWindow(crod, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemPreDocsEvaluation = new JMenuItem("Pre-Docs Evaluation");
								menuClientServicing.add(menuitemPreDocsEvaluation);
								menuitemPreDocsEvaluation.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("PreDocsEvaluation")){
											PreDocsEvaluation de = new PreDocsEvaluation();
											addWindow(de, e);
										}
									}
								});
							}
						}
					}
					{//Projects-Transaction
						JMenu menuProjects = new JMenu("Projects");
						menuTransaction.add(menuProjects);
						{
							JMenu menuBiddingAndAwarding = new JMenu("Bidding & Awarding");
							menuProjects.add(menuBiddingAndAwarding);
							{
								JMenuItem menuitemNoticeToProceed = new JMenuItem("Notice to Proceed");
								menuBiddingAndAwarding.add(menuitemNoticeToProceed);
								menuitemNoticeToProceed.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("NoticeToProceed")){
											NoticeToProceed np = new NoticeToProceed();
											addWindow(np, e);
										}
									}
								});
							}
						}
						{
							JMenu menuSalesAndMarketing = new JMenu("Sales and Marketing");
							menuProjects.add(menuSalesAndMarketing);
							{
								JMenuItem menuitemPricelist = new JMenuItem("Pricelist");
								menuSalesAndMarketing.add(menuitemPricelist);
								menuitemPricelist.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("Pricelist")){
											Pricelist pi = new Pricelist();
											addWindow(pi, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemPaymentScheme = new JMenuItem("Payment Scheme");
								menuSalesAndMarketing.add(menuitemPaymentScheme);
								menuitemPaymentScheme.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("PaymentScheme")){
											PaymentScheme ps = new PaymentScheme();
											addWindow(ps, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemCommAgentModule = new JMenuItem("Sales Agent/Broker");
								menuSalesAndMarketing.add(menuitemCommAgentModule);
								menuitemCommAgentModule.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("SalesAgent")){
											SalesAgent agent_brkr_mod = new SalesAgent();
											addWindow(agent_brkr_mod, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemCommScheme = new JMenuItem("Commission Scheme");
								menuSalesAndMarketing.add(menuitemCommScheme);
								menuitemCommScheme.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("Comm_Scheme")){
											Comm_Scheme comm_sch = new Comm_Scheme();
											addWindow(comm_sch, e);
										}
									}
								});
							}
							{
								JMenu menuTripping_ = new JMenu("Tripping");
								menuSalesAndMarketing.add(menuTripping_);
								menuTripping_.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										
									}
								});
								{
									JMenuItem menuitemTripping_Cost = new JMenuItem("Tripping Cost");
									menuTripping_.add(menuitemTripping_Cost);
									menuitemTripping_Cost.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											if(isNotExisting("Tripping_Cost")){
												Tripping_Cost tc = new Tripping_Cost();
												addWindow(tc, e);
											}
										}
									});
								}
							}
						}
					}
					{
						JMenuItem menuitemCARD = new JMenuItem("CARD");
						menuTransaction.add(menuitemCARD);
						menuitemCARD.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(isNotExisting("CARD")){
									CARD c = new CARD();
									addWindow(c, e);
								}
							}
						});
					}
				}
				{
					JMenu menuReports = new JMenu("Reports");
					menuBar.add(menuReports);
					menuReports.setMnemonic(KeyEvent.VK_R);
					{
						JMenu menuAccountingRpt = new JMenu("Accounting");
						menuReports.add(menuAccountingRpt);
						{
							JMenu menuFixedAssets = new JMenu("Fixed Assets");
							menuAccountingRpt.add(menuFixedAssets);
							{
								JMenuItem menuitemAssetCardPrinting = new JMenuItem("Asset Card Printing");
								menuFixedAssets.add(menuitemAssetCardPrinting);
								menuitemAssetCardPrinting.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("AssetCard")){
											AssetCard as = new AssetCard();
											addWindow(as, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemPrintAssetSticker= new JMenuItem("Print Asset Sticker");
								menuFixedAssets.add(menuitemPrintAssetSticker);
								menuitemPrintAssetSticker.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("PrintAssetSticker")){
											PrintAssetSticker pas = new PrintAssetSticker();
											addWindow(pas, e);
										}
									}
								});
							}
						}
					}
					{
						JMenu menuReportBuyers = new JMenu("Buyers");
						menuReports.add(menuReportBuyers);
						{
							JMenu menuReportClientServicing = new JMenu("Client Servicing");
							menuReportBuyers.add(menuReportClientServicing);
							{

								JMenuItem menuitemListOfOpenUnits = new JMenuItem("Open Units");
								menuReportClientServicing.add(menuitemListOfOpenUnits);
								menuitemListOfOpenUnits.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("OpenUnits")){
											OpenUnits ou = new OpenUnits();
											addWindow(ou, e);
										}
									}
								});
							}
							{
								JMenuItem menuitemHoldUnits = new JMenuItem("Hold Units");
								menuReportClientServicing.add(menuitemHoldUnits);
								menuitemHoldUnits.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("HoldUnits")){
											HoldUnits hu = new HoldUnits();
											addWindow(hu, e);
										}
									}
								});
							}
						}
					}
				}
				{
					JMenu menuUtilities = new JMenu("Utilities"); 
					menuBar.add(menuUtilities);
					menuUtilities.setMnemonic(KeyEvent.VK_U);
					{
						JMenu menuAccountingUtil	 = new JMenu("Accounting");
						menuUtilities.add(menuAccountingUtil);
						{
							JMenu menuAccounts = new JMenu("Accounts");
							menuAccountingUtil.add(menuAccounts);

							{
								JMenuItem menuitemAddAccount= new JMenuItem("Chart of Accounts");
								menuAccounts.add(menuitemAddAccount);
								menuitemAddAccount.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("ChartofAccounts")){
											ChartofAccounts chart_accts = new ChartofAccounts();
											addWindow(chart_accts, e);
										}
									}
								});
							}
						}
						{
							JMenu menuAccounts = new JMenu("Check Voucher");
							menuAccountingUtil.add(menuAccounts);

							{
								JMenuItem menuitemAddAccount= new JMenuItem("Temporary Check Voucher");
								menuAccounts.add(menuitemAddAccount);
								menuitemAddAccount.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("TemporaryCheckVoucher")){
											TemporaryCheckVoucher tcv = new TemporaryCheckVoucher();
											addWindow(tcv, e);
										}
									}
								});
							}
						}
					}
					
					{
						JMenu menuUtilitiesBuyers = new JMenu("Buyers");
						menuUtilities.add(menuUtilitiesBuyers);
						{
							JMenu menuUtilitiesClientServicing = new JMenu("Client Servicing");
							menuUtilitiesBuyers.add(menuUtilitiesClientServicing);
							{
								JMenuItem menuitemAddEditEntityType = new JMenuItem("Add/Edit Entity Type");
								menuUtilitiesClientServicing.add(menuitemAddEditEntityType);
								menuitemAddEditEntityType.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("AddEditEntityType")){
											AddEditEntityType add_edit_entity_type = new AddEditEntityType();
											addWindow(add_edit_entity_type, e);
										}
									}
								});
							}
							{
								JMenuItem menuItemZipCodes = new JMenuItem("Zip Codes");
								menuUtilitiesClientServicing.add(menuItemZipCodes);
								menuItemZipCodes.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("ZipCodes")){
											ZipCodes zip_codes = new ZipCodes();
											addWindow(zip_codes, e);
										}
									}
								});
							}
						}
					}
					{
						JMenu menuAdminUtil = new JMenu("Admin");
						menuUtilities.add(menuAdminUtil);
						{
							JMenu menuFixedassetUtil	 = new JMenu("Fixed Asset");
							menuAdminUtil.add(menuFixedassetUtil);
							{
								JMenuItem menuitemAddperipheral= new JMenuItem("Asset Peripheral");
								menuFixedassetUtil.add(menuitemAddperipheral);
								menuitemAddperipheral.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										if(isNotExisting("Asset Peripheral")) {
											fixedasset_peripheral fp = new fixedasset_peripheral();
											addWindow(fp,e);
										}
										
									}
								});					
							}
						}
					}
				}
				{
					WindowMenu menuWindow = new WindowMenu(DesktopPane, pnlNorth);
					menuBar.add(menuWindow);
				}
				{
					JMenu menuSystem = new JMenu("System");
					menuBar.add(menuSystem);
					menuSystem.setMnemonic(KeyEvent.VK_S);
					{

						JMenuItem menuitemHolidaySetter = new JMenuItem("Add/Edit Holidays");
						menuSystem.add(menuitemHolidaySetter);
						menuitemHolidaySetter.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(isNotExisting("Add_Edit_Holidays")){
									Add_Edit_Holidays holiday_setter = new Add_Edit_Holidays();
									addWindow(holiday_setter, e);
								}
							}
						});
					}
				}
				{
					menuBookmark = new JMenu("Bookmark");
					menuBar.add(menuBookmark);
					menuBookmark.setMnemonic(KeyEvent.VK_B);
				}
				{
					JMenu menuHelp = new JMenu("Help");
					menuBar.add(menuHelp);
					menuHelp.setMnemonic(KeyEvent.VK_H);
					{
						JMenuItem menuitemConsole = new JMenuItem("Console");
						menuHelp.add(menuitemConsole);
						menuitemConsole.addActionListener(this);
					}
					{
						JMenuItem menuitemAboutBOIModule = new JMenuItem("About BOI Module");
						menuHelp.add(menuitemAboutBOIModule);
						menuitemAboutBOIModule.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									Class clsJiFrame1 = Class.forName("Buyers.ClientServicing.CARD");
									_JInternalFrame JiFrame1 = (_JInternalFrame)(clsJiFrame1.newInstance());

									if(isNotExisting("CARD")){
										addWindow(JiFrame1, e);
									}
								} catch (ClassNotFoundException e1) {
									e1.printStackTrace();
								} catch (InstantiationException e1) {
									e1.printStackTrace();
								} catch (IllegalAccessException e1) {
									e1.printStackTrace();
								}
							}
						});
					}
				}
				{
					JMenu menuAdmin = new JMenu("Admin");
					//menuBar.add(menuAdmin);
					menuAdmin.setMnemonic(KeyEvent.VK_A);
					/*{
						JMenuItem menuitemAddEditUser = new JMenuItem("Add/Edit User");
						menuAdmin.add(menuitemAddEditUser);
						menuitemAddEditUser.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								if(isNotExisting("AddEditUser")){
									AddEditUser aeu = new AddEditUser();
									addWindow(aeu);
								}
							}
						});
					}*/
					/*{
						JMenuItem menuitemUserAccess = new JMenuItem("User Access");
						menuAdmin.add(menuitemUserAccess);
						menuitemUserAccess.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								if(isNotExisting("UserAccess")){
									UserAccess ua = new UserAccess();
									addWindow(ua);
								}
							}
						});
					}*/
					{
						menuAdmin.add(new JSeparator());
					}
					/*{
						JMenuItem menuitemAddDivision = new JMenuItem("Add Division");
						menuAdmin.add(menuitemAddDivision);
						menuitemAddDivision.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(isNotExisting("AddDivision")){
									AddDivision add_div = new AddDivision();
									addWindow(add_div, e);
								}
							}
						});
					}*/
					/*{
						JMenuItem menuitemAddDepartment = new JMenuItem("Add Department");
						menuAdmin.add(menuitemAddDepartment);
						menuitemAddDepartment.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(isNotExisting("AddDepartment")){
									AddDepartment add_dept = new AddDepartment();
									addWindow(add_dept, e);
								}
							}
						});
					}*/
					/*{
						JMenuItem menuitemAddProject = new JMenuItem("Add Project");
						menuAdmin.add(menuitemAddProject);
						menuitemAddProject.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(isNotExisting("AddProject")){
									AddProject add_proj = new AddProject();
									addWindow(add_proj, e);
								}
							}
						});
					}
					{
						JMenuItem menuitemAddSubProject = new JMenuItem("Add Sub Project");
						menuAdmin.add(menuitemAddSubProject);
						menuitemAddSubProject.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(isNotExisting("AddSubProject")){
									AddSubProject add_sub_proj = new AddSubProject();
									addWindow(add_sub_proj, e);
								}
							}
						});
					}
					{
						JMenuItem menuitemAddCompany = new JMenuItem("Add Company");
						menuAdmin.add(menuitemAddCompany);
						menuitemAddCompany.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(isNotExisting("AddCompany")){
									AddCompany add_comp = new AddCompany();
									addWindow(add_comp, e);
								}

							}
						});
					}*/
					/*{
						menuAdmin.add(new JSeparator());
					}
					{
						JMenuItem menuitemAddEditPF = new JMenuItem("Add/ Edit PF Entries");
						menuAdmin.add(menuitemAddEditPF);
						menuitemAddEditPF.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								if(isNotExisting("AddEditPF_Entries")){
									AddEditPF_Entries add_pf = new AddEditPF_Entries();
									addWindow(add_pf);
								}

							}
						});
					}*/
					{
						menuAdmin.add(new JSeparator());
					}					
					{
						menuAdmin.add(new JSeparator());
					}
				}
				{
					menuBar.add(Box.createGlue());
				}
				{
					//menuBar.add(searchField);
				}
				{
					btnMinimize = new _JMenuToolbarButton(MINIMIZE_ICON);
					menuBar.add(btnMinimize);
					btnMinimize.setActionCommand("Minimize");
					btnMinimize.setToolTipText("Minimize");
					btnMinimize.setFocusable(false);
					btnMinimize.addActionListener(this);
				}
				{
					btnMaximize = new _JMenuToolbarButton(MAXIMIZE_ICON);
					menuBar.add(btnMaximize);
					btnMaximize.setActionCommand("Maximize");
					btnMaximize.setToolTipText("Restore Window");
					btnMaximize.setFocusable(false);
					btnMaximize.addActionListener(this);
				}
				{
					btnClose = new _JMenuToolbarButton(CLOSE_ICON);
					menuBar.add(btnClose);
					btnClose.setActionCommand("Close");
					btnClose.setToolTipText("Close");
					btnClose.setFocusable(false);
					btnClose.addActionListener(this);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*ArrayList<String> listAdmin = new ArrayList<String>();
		listAdmin.add("900668");//aagonzales
		listAdmin.add("900449");//eagonzales
		listAdmin.add("900834");//ccpaquibot
		listAdmin.add("900523");//eeesto
		listAdmin.add("900876");//jffatallo
		listAdmin.add("900462");//jffatallo

		if(!listAdmin.contains(UserInfo.EmployeeCode)){
			_Home_JSystem.menuAccess(menuBar);
		}*/

		//for testing purposes - remove comment when deployed; (purpose - this automatically displays a newly-created menuitem upon log-in)
		_Home_JSystem.menuAccess(menuBar);
		FncGlobal.menuBar = menuBar;

		/**
		 * 
		 */
		menuBookmark.setVisible(true);
		reloadBookmark();
		//setTime();
		//menuitemOtherRequest.doClick();
		//menuitemOtherRequest2.doClick();


	} 

	public static void addWindow(_JInternalFrame internalFrame) {
		internalFrame.setTitleMenu(null);
		DesktopPane.add(internalFrame);
		internalFrame.moveToFront();
		try {
			internalFrame.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	public static void addWindow(_JInternalFrame internalFrame, ActionEvent event) {
		if(internalFrame.getTitleMenu() == null){
			if(event != null){

				if((event.getSource() instanceof JButton)){
					internalFrame.setTitleMenu(((JButton)event.getSource()).getText());
					FncGlobal.AuditLogs(UserInfo.EmployeeCode, ((JButton)event.getSource()).getText(), "Opened Module");
				}
				if((event.getSource() instanceof JMenuItem)){
					internalFrame.setTitleMenu(((JMenuItem)event.getSource()).getText());
					FncGlobal.AuditLogs(UserInfo.EmployeeCode, ((JMenuItem)event.getSource()).getText(), "Opened Module");
				}
			}
		}

		DesktopPane.add(internalFrame);
		internalFrame.moveToFront();

		try {
			internalFrame.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	public static void addWindowMouse(_JInternalFrame internalFrame, MouseEvent event) {
		if(internalFrame.getTitleMenu() == null){
			if(event != null){
				if((event.getSource() instanceof JButton)){
					internalFrame.setTitleMenu(((JButton)event.getSource()).getText());
				}
				if((event.getSource() instanceof JMenuItem)){
					internalFrame.setTitleMenu(((JMenuItem)event.getSource()).getText());
				}
			}
		}

		DesktopPane.add(internalFrame);
		internalFrame.moveToFront();
		try {
			internalFrame.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	public static Boolean isNotExisting(String simpleName) {
		boolean isExisting = true;
		for(JInternalFrame frame : DesktopPane.getAllFrames()){
			if(frame.getClass().getSimpleName().equals(simpleName)){
				frame.moveToFront();
				try {
					frame.setSelected(true);
				} catch (PropertyVetoException e) { }
				isExisting = false;
				break;
			}
		}
		return isExisting;
	}

	public void maximizeAllFrames(_JInternalFrame selectedFrame) {
		for(Component comp : DesktopPane.getComponents()){
			if(comp instanceof _JInternalFrame){
				_JInternalFrame frame = (_JInternalFrame) comp;
				if(frame.isMaximizable()){
					if(selectedFrame != frame){
						//System.out.printf("JInternalFrame: %s%n", frame.getName());

						InternalFrameUI ui = frame.getUI();
						if (ui instanceof BasicInternalFrameUI){
							((BasicInternalFrameUI) ui).setNorthPane(null);
							frame.setBorder(BorderFactory.createEmptyBorder());
						}
						//frame.setPreferredSize(selectedFrame.getPreferredSize());
						frame.setSize(selectedFrame.getSize());
					}
				}
			}
		}
		setMenubarButtonVisible(true);
	}

	public void startProgress(String caption) {
		pnlSouthCenter.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		progressBar.setVisible(true);
		progressBar.setStringPainted(true);
		progressBar.setString(caption);
		progressBar.setIndeterminate(true);
	}

	public void stopProgress() {
		progressBar.setIndeterminate(false);
		progressBar.setVisible(false);
		pnlSouthCenter.setBorder(BorderFactory.createLoweredSoftBevelBorder());
	}

	public void setMenubarButtonVisible(Boolean isVisible) {
		btnMinimize.setVisible(isVisible);
		btnMaximize.setVisible(isVisible);
		btnClose.setVisible(isVisible);
	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		if(JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Close", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION){
			return;
		}
		pgSelect db = new pgSelect();
		String SQL = "SELECT sp_audit_log_details('"+UserInfo.EmployeeCode+"', false)";
		db.select(SQL);

		FncSystem.out("Logged Out of System", SQL);

		pgUpdate dbExec = new pgUpdate();
		dbExec.executeUpdate("UPDATE rf_queuing_system SET status_id = 'A', processed_by = null WHERE status_id = 'P' and processed_by = '"+UserInfo.EmployeeCode+"' and date_created::date = current_date", true);
		dbExec.commit();

		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) { }

	@Override
	public void windowIconified(WindowEvent e) { }

	@Override
	public void windowDeiconified(WindowEvent e) { }

	@Override
	public void windowActivated(WindowEvent e) { }

	@Override
	public void windowDeactivated(WindowEvent e) { }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() != null){
			/*if(e.getActionCommand().equals("Console")){
			if(FncGlobal.lpsOut != null){
				Console console  = new Console(FncGlobal.homeMDI, "Console");
				console.setLocationRelativeTo(null);
				console.setVisible(true);
			}
		}*/

			if(e.getActionCommand().equals("Minimize")){
				try {
					_JInternalFrame oldFrame = (_JInternalFrame) DesktopPane.getSelectedFrame();
					oldFrame.setIcon(true);
					oldFrame.requestFocus();

					setTitle(FncGlobal.ORIGINAL_TITLE);
					setMenubarButtonVisible(DesktopPane.hasMaximizeFrame());
				} catch (PropertyVetoException e1) {

				}
			}

			if(e.getActionCommand().equals("Maximize")){
				System.out.println("Dumaan dito sa maximize");
				List<_JInternalFrame> listFrame = new ArrayList<_JInternalFrame>();
				for(int x = DesktopPane.getComponents().length -1; x >= 0; x--){
					Component comp = DesktopPane.getComponents()[x];
					if(comp instanceof _JInternalFrame){

						if(comp.getClass().getSimpleName().equals("CARD") || comp.getClass().getSimpleName().equals("ClientInformation")){

							

						}else{
							System.out.println("Maximize");
							_JInternalFrame oldFrame = (_JInternalFrame) comp;
							DesktopPane.remove(oldFrame);
							if(oldFrame.isMaximizable()){

								_JInternalFrame newFrame = new _JInternalFrame(oldFrame.getTitle(), true, true, true, true);
								newFrame.setContentPane(oldFrame.getContentPane());
								newFrame.setPrimaryTitle(oldFrame.getPrimaryTitle());
								newFrame.setSecondaryTitle(oldFrame.getSecondaryTitle());

								listFrame.add(newFrame);

								try {
									newFrame.setSize(oldFrame.getSIZE_OLD());
									newFrame.setPreferredSize(oldFrame.getSIZE_OLD());
								} catch (NullPointerException e1) { }
							}
						}
					}
				}
				setTitle(FncGlobal.ORIGINAL_TITLE);
				setMenubarButtonVisible(false);

				for(_JInternalFrame frame : listFrame){
					addWindow(frame, null);
					frame.requestFocus();
				}

				DesktopPane.revalidate();
				DesktopPane.repaint();
			}

			if(e.getActionCommand().equals("Close")){
				_JInternalFrame oldFrame = (_JInternalFrame) DesktopPane.getSelectedFrame();
				oldFrame.dispose();

				pgSelect db = new pgSelect();
				String SQL = "SELECT sp_audit_log_details('"+UserInfo.EmployeeCode+"', false)";
				db.select(SQL);

				FncSystem.out("Logged Out of System", SQL);

				setTitle(FncGlobal.ORIGINAL_TITLE);
				setMenubarButtonVisible(DesktopPane.hasMaximizeFrame());
			}
		}
	}

	/**
	 * @return the desktopPane
	 */
	public MDIDesktopPane getDesktopPane() {
		return DesktopPane;
	}

	/**
	 * @param desktopPane the desktopPane to set
	 */
	public void setDesktopPane(MDIDesktopPane desktopPane) {
		DesktopPane = desktopPane;
	}

	public void reloadBookmark() {
		menuBookmark.removeAll();
		toolbar.removeAll();

		pgSelect db = new pgSelect();
		db.select("SELECT * FROM rf_bookmarks WHERE emp_code = '"+ UserInfo.EmployeeCode +"' ORDER BY module_name;");

		//if(UserInfo.Branch.trim().equals("10") == false){

		if(db.isNotNull()){
			for(int x=0; x < db.getRowCount(); x++){
				final String class_location = (String) db.getResult()[x][1];
				final String module_name = (String) db.getResult()[x][2];
				final String class_name = class_location.split("\\.")[class_location.split("\\.").length - 1];

				JMenuItem menuitemAboutBOIModule = new JMenuItem(module_name);
				menuBookmark.add(menuitemAboutBOIModule);
				menuitemAboutBOIModule.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addWindowFromBookmark(class_location, module_name, class_name, e);
					}
				});

				_JButton btnToolbar = new _JButton(module_name);//module_name
				btnToolbar.setToolTipText(module_name);
				btnToolbar.setAdditionalInfo(class_location);
				btnToolbar.setForeground(Color.BLACK);
				btnToolbar.setFont(UIManager.getFont("MenuItem.font").deriveFont(10f));
				btnToolbar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//addWindowFromBookmark(class_location, module_name, class_name, e);
					}
				});

				btnToolbar.addMouseListener(new MouseListener() {

					public void mouseReleased(MouseEvent e) {

					}

					public void mousePressed(MouseEvent e) {

					}

					public void mouseExited(MouseEvent e) {

					}

					public void mouseEntered(MouseEvent e) {

					}

					public void mouseClicked(MouseEvent e) {
						if(e.getClickCount() == 1){
							System.out.println("Dumaan dito");

							if(isNotExisting(class_name)){
								System.out.printf("Display class location: %s%n", class_location);
								System.out.printf("Display module name: %s%n", module_name);
								System.out.printf("Display class_name: %s%n", class_name);
								addWindowFromBookmark(class_location, module_name, class_name, e);
							}else{
								JOptionPane.showMessageDialog(Home_JSystem.this, String.format("%s is already open.", module_name), module_name, JOptionPane.INFORMATION_MESSAGE);
							}

						}
					}
				});

				btnToolbar.addMouseListener(new MouseAdapter() {
					public void mouseReleased(MouseEvent e) {
						if(e.isPopupTrigger()){
							try {
								initializeMenu(e).show((_JButton)e.getSource(), e.getX(), e.getY());
							} catch (NullPointerException e1) { }
						}
					}
					public void mousePressed(MouseEvent e) {
						if(e.isPopupTrigger()){
							try {
								initializeMenu(e).show((_JButton)e.getSource(), e.getX(), e.getY());
							} catch (NullPointerException e1) { }
						}
					}

					public JPopupMenu initializeMenu(final MouseEvent e) {
						JMenuItem menuViewRequests = new JMenuItem("Delete");
						menuViewRequests.setFont(menuViewRequests.getFont().deriveFont(10f));
						menuViewRequests.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								_JButton btn = (_JButton) e.getSource();

								String module = btn.getText();
								String class_name = btn.getAdditionalInfo();

								System.out.printf("Module: %s (%s)%n", btn.getText(), btn.getAdditionalInfo());

								pgUpdate db = new pgUpdate();
								db.executeUpdate("DELETE FROM rf_bookmarks WHERE TRIM(emp_code) = '"+ UserInfo.EmployeeCode +"' AND TRIM(module_name) = '"+ module +"' AND TRIM(class_name) = '"+ class_name +"';", false);
								db.commit();
								FncGlobal.homeMDI.reloadBookmark();
							}
						});

						JPopupMenu menu = new JPopupMenu();
						menu.add(menuViewRequests);
						return menu;
					}
				});

				toolbar.add(btnToolbar);
				toolbar.setLayout(new GridLayout(1, 0));
			}
		}
		//}
	}

	private void addWindowFromBookmark(String class_location, String module_name, String class_name, ActionEvent e) {
		try {
			Class classInternalFrame = Class.forName(class_location);
			_JInternalFrame internalFrame = (_JInternalFrame)(classInternalFrame.newInstance());

			if(isNotExisting(class_name)){
				addWindow(internalFrame, e);
			}else{
				JOptionPane.showMessageDialog(Home_JSystem.this, String.format("%s is already open.", module_name), module_name, JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}

	private void addWindowFromBookmark(String class_location, String module_name, String class_name, MouseEvent e) {
		try {
			Class classInternalFrame = Class.forName(class_location);
			_JInternalFrame internalFrame = (_JInternalFrame)(classInternalFrame.newInstance());

			System.out.println("Mouseevent");
			System.out.printf("Display value of is existing: %s", isNotExisting(class_name));


			if(isNotExisting(class_name)){
				addWindowMouse(internalFrame, e);
			}else{
				JOptionPane.showMessageDialog(Home_JSystem.this, String.format("%s is already open.", module_name), module_name, JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}

	private void CreateCutOffControl() {
		final JLabel[] lblBranch;
		final JLabel[] lblActive; 

		final pgSelect dbExec = new pgSelect(); 
		dbExec.select("select y.branch_alias, now()::date::varchar || ' ' || (cut_off_time + interval '1 second')::varchar \n" + 
				"from mf_cut_off x\n" + 
				"inner join mf_office_branch y on x.branch = y.branch_id\n" + 
				"where dow::int = extract(dow from now())::int\n" + 
				"order by y.branch_id"); 

		panCutOff = new JXPanel(new BorderLayout(5, 5)); 
		panCutOff.setPreferredSize(new Dimension(500, 150));
		{	
			{
				JXPanel panBranch = new JXPanel(new GridLayout(dbExec.getRowCount(), 1, 5, 5)); 
				panCutOff.add(panBranch, BorderLayout.LINE_START); 
				panBranch.setPreferredSize(new Dimension(100, 0));
				{
					lblBranch = new JLabel[dbExec.getRowCount()]; 
					for (int x=0; x<dbExec.getRowCount(); x++) {
						lblBranch[x] = new JLabel(dbExec.getResult()[x][0].toString());
						panBranch.add(lblBranch[x]); 
						lblBranch[x].setHorizontalAlignment(JLabel.LEFT);
						lblBranch[x].setFont(FncLookAndFeel.systemFont_Bold.deriveFont(10f));
					}
				}
			}
			{
				JXPanel panStatus = new JXPanel(new GridLayout(1, 2, 5, 5)); 
				panCutOff.add(panStatus, BorderLayout.CENTER);
				{
					{
						JXPanel panTime = new JXPanel(new GridLayout(dbExec.getRowCount(), 1, 5, 5));
						panStatus.add(panTime);
						{
							dteDate = new _JDateChooser[dbExec.getRowCount()];
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

							for (int x=0; x<dbExec.getRowCount(); x++) { 
								dteDate[x] = new _JDateChooser("hh:mm:ss", "##:##:##", '_');
								panTime.add(dteDate[x]); 
								try {
									if (FncGlobal.GetBoolean("select cut_off_active \n" + 
											"from mf_cut_off x \n" + 
											"inner join mf_office_branch y on x.branch = y.branch_id \n" + 
											"where dow::int = extract(dow from now())::int and y.branch_alias = '"+dbExec.getResult()[x][0].toString()+"'")) {
										dteDate[x].setDate(formatter.parse(dbExec.getResult()[x][1].toString()));
									} else {
										dteDate[x].setDate(null);
									}

								} catch (ParseException e) {
									e.printStackTrace();
								}
								dteDate[x].getCalendarButton().setVisible(false);
								dteDate[x].setEditable(FncGlobal.GetBoolean("select exists(select * from (select distinct unnest(x.accessed_by) as \"user\" from mf_cut_off x) a where a.\"user\" = '"+UserInfo.EmployeeCode+"')"));
								dteDate[x].getDateEditor().getUiComponent().setFocusable(false);
							}
						}
					}
					{
						JXPanel panDivActive = new JXPanel(new GridLayout(dbExec.getRowCount(), 1, 5, 5));
						panStatus.add(panDivActive);
						{
							final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							lblActive = new JLabel[dbExec.getRowCount()]; 
							for (int x=0; x<dbExec.getRowCount(); x++) {
								final Integer intIdx = x; 
								lblActive[x] = new JLabel("Active");
								panDivActive.add(lblActive[x]);
								lblActive[x].setToolTipText(dbExec.getResult()[x][0].toString());
								if (FncGlobal.GetBoolean("select cut_off_active \n" + 
										"from mf_cut_off x \n" + 
										"inner join mf_office_branch y on x.branch = y.branch_id \n" + 
										"where dow::int = extract(dow from now())::int and y.branch_alias = '"+dbExec.getResult()[intIdx][0].toString()+"'")) {
									lblActive[x].setBorder(BorderFactory.createLoweredSoftBevelBorder());
									lblActive[intIdx].setText("Active");
								} else {
									lblActive[x].setBorder(BorderFactory.createRaisedBevelBorder());
									lblActive[intIdx].setText("Inactive");
								} 
								lblActive[x].setHorizontalAlignment(JLabel.CENTER);
								lblActive[x].setToolTipText("Click the date field to change date. Allowed only when cut-off is active.");
								lblActive[x].addMouseListener(new MouseListener() {
									public void mouseReleased(MouseEvent e) {

									}

									public void mousePressed(MouseEvent e) {

									}

									public void mouseExited(MouseEvent e) {

									}

									public void mouseEntered(MouseEvent e) {

									}

									public void mouseClicked(MouseEvent e) {
										if (FncGlobal.GetBoolean("select exists(select * from (select distinct unnest(x.accessed_by) as \"user\" from mf_cut_off x) a where a.\"user\" = '"+UserInfo.EmployeeCode+"')")) {
											pgUpdate dbUpdate = new pgUpdate(); 
											if (lblActive[intIdx].getBorder()==BorderFactory.createLoweredSoftBevelBorder()) {
												lblActive[intIdx].setBorder(BorderFactory.createRaisedBevelBorder());
												lblActive[intIdx].setText("Inactive");

												dbUpdate.executeUpdate("update mf_cut_off z \n" + 
														"set cut_off_active = false \n" + 
														"from mf_cut_off x \n" + 
														"inner join mf_office_branch y on x.branch = y.branch_id \n" + 
														"where x.dow::int = extract(dow from now())::int and y.branch_alias = '"+dbExec.getResult()[intIdx][0].toString()+"' \n" + 
														"and x.dow = z.dow and x.branch = z.branch", true);

												dteDate[intIdx].setDate(null);

												dbUpdate.executeUpdate("insert into mf_audit_trail (system_id, activity, user_code, date_upd, remarks)\n" + 
														"values ('HOME', 'CUT-OFF CHANGE', '"+UserInfo.EmployeeCode+"', now(), 'cut-off deactivated; branch: "+lblBranch[intIdx].getText()+"; dow: ' || extract(dow from now())::int::varchar || '; ')", true); 
											} else {
												lblActive[intIdx].setBorder(BorderFactory.createLoweredSoftBevelBorder());
												lblActive[intIdx].setText("Active");

												dbUpdate.executeUpdate("update mf_cut_off z \n" + 
														"set cut_off_active = true \n" + 
														"from mf_cut_off x \n" + 
														"inner join mf_office_branch y on x.branch = y.branch_id \n" + 
														"where x.dow::int = extract(dow from now())::int and y.branch_alias = '"+dbExec.getResult()[intIdx][0].toString()+"' \n" + 
														"and x.dow = z.dow and x.branch = z.branch", true);

												try {
													dteDate[intIdx].setDate(formatter.parse(dbExec.getResult()[intIdx][1].toString()));
												} catch (ParseException e1) {
													e1.printStackTrace();
												}

												dbUpdate.executeUpdate("insert into mf_audit_trail (system_id, activity, user_code, date_upd, remarks)\n" + 
														"values ('HOME', 'CUT-OFF CHANGE', '"+UserInfo.EmployeeCode+"', now(), 'cut-off activated; branch: "+lblBranch[intIdx].getText()+"; dow: ' || extract(dow from now())::int::varchar || '; ')", true);
											}

											dbUpdate.commit();	
										}
									}
								});
							}
						}
					}
				}
			}
		}
	}

	private void setTime(final JXLabel label) {

		ActionListener timerListener = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String time = String.format(" %s ", dateTimeFormat.format(Calendar.getInstance().getTime()));
				label.setText(time);
			}
		};

		Timer timer = new Timer(1000, timerListener);
		timer.setInitialDelay(0);
		timer.start();

	}

	private void Blink(final JXLabel label){

		ActionListener timerListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (intDCRFCount>0) {
					if (label.getForeground()==Color.RED) {
						label.setForeground(lblDateTime.getForeground());
					} else {
						label.setForeground(Color.RED);
					}

					label.setText(intDCRFCount+" DCRF(s) need(s) attention");
				} else {
					try {
						label.setForeground(lblDateTime.getForeground());							
					} catch (NullPointerException ex) {

					}

					label.setText("---");
				}
			}

		};

		Timer timer = new Timer(1000, timerListener);
		timer.setInitialDelay(0);
		timer.start();
	}



	public static boolean deptHead(String emp_code){
		pgSelect db = new pgSelect();
		String query = "select a.dept_alias,c.entity_name from mf_department a \n"+
				"left join em_employee b on  a.dept_head_code = b.emp_code \n"+
				"left join rf_entity c on  b.entity_id = c.entity_id where dept_head_code ='"+emp_code+"'";
		db.select(query);

		return db.getRowCount()>0; 
	}
	private boolean DivHead(String emp_code){
		pgSelect db = new pgSelect();
		String query = "select a.division_alias,c.entity_name from mf_division a "
				+ "left join em_employee b on  a.div_head_code = b.emp_code "
				+ "left join rf_entity c on  b.entity_id = c.entity_id where div_head_code = '"+UserInfo.EmployeeCode+"'";
		db.select(query);

		return db.getRowCount()>0; 
	}

	private void move(JXPanel panel, Integer x, Integer y, Integer intPrefWidth, Integer intPrefHeight) {
		intHomeWidth = (int) ((intHomeWidth==null)?screenSize.getWidth():intHomeWidth); 
		intHomeHeight = (int) ((intHomeHeight==null)?screenSize.getHeight():intHomeHeight); 

		try {
			panel.setBounds(intHomeWidth-x, intHomeHeight-y, intPrefWidth, intPrefHeight);
			panel.repaint();
			panel.revalidate();   
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
	}

	private void move(JPanel panel, Integer x, Integer y, Integer intPrefWidth, Integer intPrefHeight) {
		intHomeWidth = (int) ((intHomeWidth==null)?screenSize.getWidth():intHomeWidth); 
		intHomeHeight = (int) ((intHomeHeight==null)?screenSize.getHeight():intHomeHeight); 

		try {
			panel.setBounds(intHomeWidth-x, intHomeHeight-y, intPrefWidth, intPrefHeight);
			panel.repaint();
			panel.revalidate();   
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
	}

	private void withPending(){

		ActionListener timerListener = new ActionListener() {

			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				switchIcon(); 
			}

		};

		Timer timer = new Timer(500, timerListener);
		timer.setInitialDelay(0);
		timer.start();
	}

	private void switchIcon() {
		if (lblDCRF.getIcon()==FncLookAndFeel.iconDCRF2) {
			lblDCRF.setIcon(FncLookAndFeel.iconDCRF);
		} else {
			lblDCRF.setIcon(FncLookAndFeel.iconDCRF2);
		}
	}
}
