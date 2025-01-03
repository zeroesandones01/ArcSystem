
package Buyers.ClientServicing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.jdesktop.swingx.JXFormattedTextField;
import org.jdesktop.swingx.JXTextField;
import org.jdesktop.swingx.table.NumberEditorExt;

import Database.pgSelect;
import Dialogs.dlg_OtherUnit;
import FormattedTextField._JXFormattedTextField;
import Functions.FncBigDecimal;
import Functions.FncGlobal;
import Functions.FncSystem;
import Functions.FncTables;
import Functions.SpringUtilities;
import Functions.UserInfo;
import Home.Home_ArcSystem;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import Lookup._JLookupTable;
import components.JTBorderFactory;
import components._JInternalFrame;
import components._JTableMain;
import components._JXTextField;
import interfaces._GUI;
import tablemodel.modelHoldingAndReservation_PaySchemeDetails;

public class pnlTemporaryReservation extends JPanel implements _GUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5794811027205869911L;
	private JPanel pnlNorth;

	private JLabel lblProject;
	private _JLookup lookupProject;
	private _JXTextField txtProjectName;

	private JLabel lblUnit;
	private _JLookup lookupUnit;
	private _JXTextField txtUnit;

	private JLabel lblSequence;
	private _JXTextField txtSequence;

	private JLabel lblModel;
	private _JXTextField txtModelID;
	private _JXTextField txtModelName;

	private JLabel lblGSP;
	private _JXFormattedTextField txtGSP;

	private JLabel lblNSP;
	private _JXFormattedTextField txtNSP;

	private JLabel lblVAT;
	private _JXFormattedTextField txtVAT;

	/*
	 * private JPanel pnlCenterNorthCenter; private JPanel pnlCenterNorthWest;
	 */
	private JPanel pnlCenterNorth;

	private JLabel lblSalesAgent;
	private _JLookup lookupSalesAgentID;
	private _JXTextField txtSalesAgentName;

	private JLabel lblCluster;
	private _JLookup lookupCluster;
	private _JXTextField txtCluster;

	private JLabel lblBuyerType;
	private _JLookup lookupBuyerType;
	private _JXTextField txtBuyerType;

	private JLabel lblPmtScheme;
	private _JLookup lookupPmtScheme;
	private _JXTextField txtPmtScheme;

	private JPanel pnlCenterNorthEast;

	private JLabel lblDiscRate;
	private _JXFormattedTextField txtDiscRate;

	private JLabel lblDiscAmount;
	private _JXFormattedTextField txtDiscAmount;

	private JLabel lblDPEquity;
	private _JXFormattedTextField txtDPEquity;

	private JLabel lblAvailedAmount;
	private _JXFormattedTextField txtAvailedAmount;

	private JScrollPane scrollPaymentDetails;
	private _JTableMain tblPaymentDetails;
	private modelHoldingAndReservation_PaySchemeDetails modelPaymentDetails;
	private JList rowHeaderPaymentDetails;

	private String entity_id;
	private String entity_name;
	private Boolean isDirectTR = false;
	private Boolean withOtherUnit = false;
	private String other_unit = "";
	private String other_model_id = "";

	private String sub_proj_id = "";

	private String previous_unit = null;
	private String new_unit = null;

	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private JCheckBox cboxChangeLA;
	private JLabel lblChangeLA;

	public pnlTemporaryReservation() {
		initGUI();
	}

	public pnlTemporaryReservation(LayoutManager layout) {
		super(layout);
		initGUI();
	}

	public pnlTemporaryReservation(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		initGUI();
	}

	public pnlTemporaryReservation(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		initGUI();
	}

	@Override
	public void initGUI() {
		this.setLayout(new BorderLayout(5, 5));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.setPreferredSize(new Dimension(600, 400));
		{
			pnlNorth = new JPanel(new BorderLayout(3, 0));
			this.add(pnlNorth, BorderLayout.NORTH);
			pnlNorth.setBorder(components.JTBorderFactory.createTitleBorder("Details"));
			pnlNorth.setPreferredSize(new Dimension(590, 105));
			{
				JPanel pnlNorthWest = new JPanel(new BorderLayout(5, 0));
				pnlNorth.add(pnlNorthWest, BorderLayout.WEST);
				pnlNorthWest.setPreferredSize(new Dimension(140, 0));
				{
					JPanel pnlLabel = new JPanel(new GridLayout(3, 1, 3, 3));
					pnlNorthWest.add(pnlLabel, BorderLayout.WEST);
					{
						lblProject = new JLabel("*Project");
						pnlLabel.add(lblProject);
					}
					{
						lblUnit = new JLabel("*Unit");
						pnlLabel.add(lblUnit);
					}
					{
						lblModel = new JLabel("Model");
						pnlLabel.add(lblModel);
					}
				}
				{
					JPanel pnlLookup = new JPanel(new GridLayout(3, 1, 3, 3));
					pnlNorthWest.add(pnlLookup, BorderLayout.CENTER);
					{
						lookupProject = new _JLookup(null, "Project", 0);
						pnlLookup.add(lookupProject);
						lookupProject.setPrompt("Project ID");
						lookupProject.addFocusListener(new FocusListener() {

							@Override
							public void focusLost(FocusEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void focusGained(FocusEvent e) {
								// TODO Auto-generated method stub
								if (isDirectTR) {
									lookupProject.setLookupSQL(_JInternalFrame.SQL_PROJECT());
								} else {
									lookupProject.setLookupSQL(_HoldingAndReservation.sqlProject(entity_id));
								}
							}
						});
						lookupProject.addLookupListener(new LookupListener() {
							public void lookupPerformed(LookupEvent event) {
								Object[] data = ((_JLookup) event.getSource()).getDataSet();

								FncSystem.out("Display SQL for Lookup of Project", lookupProject.getLookupSQL());

								if (data != null) {
									String proj_id = (String) data[0];
									txtProjectName.setText((String) data[1]);
									if (proj_id.equals("017") || proj_id.equals("019") || proj_id.equals("018")
											|| proj_id.equals("015")) {
										lookupCluster.setEditable(true);
									} else {
										lookupCluster.setEditable(false);
									}
								}
							}
						});
					}
					{
						lookupUnit = new _JLookup(null, "Unit", "Please select project.");
						pnlLookup.add(lookupUnit);
						lookupUnit.setPrompt("Unit ID");
						lookupUnit.setFilterName(true);
						lookupUnit.addFocusListener(new FocusAdapter() {
							public void focusGained(FocusEvent e) {
								String proj_id = lookupProject.getValue();

								if (isDirectTR) {
									lookupUnit.setLookupSQL(_HoldingAndReservation.getUnits(proj_id));
								} else {
									lookupUnit.setLookupSQL(_HoldingAndReservation.sqlUnits(entity_id, proj_id));
								}

								FncSystem.out("SQL For Lookup of Units", lookupUnit.getLookupSQL());
							}
						});
						lookupUnit.addLookupListener(new LookupListener() {
							public void lookupPerformed(LookupEvent event) {// XXX Units
								Object[] data = ((_JLookup) event.getSource()).getDataSet();

								FncSystem.out("Display SQL for Lookup of Unit", lookupUnit.getLookupSQL());

								if (data != null) {
									String unit_id = (String) data[0];

									if (isDirectTR) {

										if (_HoldingAndReservation.isOpen(lookupProject.getValue(), unit_id)) {

											lookupUnit.setValue(unit_id);
											txtUnit.setText((String) data[1]);
											txtSequence.setText("");
											txtModelID.setText((String) data[2]);
											txtModelName.setText((String) data[3]);
											txtGSP.setValue((BigDecimal) data[6]);
											txtNSP.setValue((BigDecimal) data[10]);
											txtVAT.setValue((BigDecimal) data[11]);
										//	txtVAT.setValue(null);

											previous_unit = unit_id;
											new_unit = unit_id;

											sub_proj_id = data[9].toString();
											System.out.println(sub_proj_id);
											getUnitDesc(lookupProject.getValue(), unit_id, "");
											System.out.printf("Display value of proj_id: %s", lookupProject.getValue());
											tagAnotherUnit(lookupProject.getValue(), unit_id);

										} else {
											lookupUnit.setValue(null);
											JOptionPane.showMessageDialog(null, "Unit has been reserved already",
													"Unit", JOptionPane.WARNING_MESSAGE);
										}
									} else {

										lookupUnit.setValue(unit_id);
										txtUnit.setText((String) data[1]);
										if (data[2] != null) {
											// txtSequence.setText(Integer.toString(data[2].toInteger()));
											txtSequence.setText(data[2].toString());
										}
										txtModelID.setText((String) data[5]);
										txtModelName.setText((String) data[6]);
										txtGSP.setValue((BigDecimal) data[9]);
										txtNSP.setValue((BigDecimal) data[9]);
										txtVAT.setValue((BigDecimal) data[10]);

										// txtDPEquity.setValue((BigDecimal) data[12]);
										// txtAvailedAmount.setValue((BigDecimal) data[11]);

										lookupSalesAgentID.setValue((String) data[7]);
									txtSalesAgentName.setText((String) data[8]);

										sub_proj_id = data[13].toString();

									}
								}
							}
						});
					}
					{
						txtModelID = new _JXTextField("Model ID");
						pnlLookup.add(txtModelID);
						txtModelID.setHorizontalAlignment(JXTextField.CENTER);
					}
				}
			}
			{
				JPanel pnlNorthCenter = new JPanel(new GridLayout(3, 1, 3, 3));
				pnlNorth.add(pnlNorthCenter, BorderLayout.CENTER);
				{
					txtProjectName = new _JXTextField("Project Name");
					pnlNorthCenter.add(txtProjectName);
				}
				{
					JPanel pnlUnit = new JPanel(new BorderLayout());
					pnlNorthCenter.add(pnlUnit);
					{
						txtUnit = new _JXTextField("Description");
						pnlUnit.add(txtUnit, BorderLayout.CENTER);
					}
					{
						JPanel pnlSequence = new JPanel(new BorderLayout(5, 5));
						pnlUnit.add(pnlSequence, BorderLayout.EAST);
						pnlSequence.setPreferredSize(new Dimension(150, 0));
						{
							lblSequence = new JLabel("Sequence No.", JLabel.TRAILING);
							pnlSequence.add(lblSequence, BorderLayout.WEST);
							lblSequence.setPreferredSize(new Dimension(100, 0));
						}
						{
							txtSequence = new _JXTextField();
							pnlSequence.add(txtSequence, BorderLayout.CENTER);
							txtSequence.setHorizontalAlignment(JXTextField.CENTER);
						}
					}
				}
				{
					txtModelName = new _JXTextField("Model Name");
					pnlNorthCenter.add(txtModelName);
				}
			}
			{
				JPanel pnlNorthEast = new JPanel(new BorderLayout(3, 3));
				pnlNorth.add(pnlNorthEast, BorderLayout.EAST);
				pnlNorthEast.setPreferredSize(new Dimension(170, 0));
				{
					JPanel pnlLabel = new JPanel(new GridLayout(3, 1, 3, 3));
					pnlNorthEast.add(pnlLabel, BorderLayout.WEST);
					{
						lblGSP = new JLabel(" GSP");
						pnlLabel.add(lblGSP);
					}
					{
						lblNSP = new JLabel(" NSP");
						pnlLabel.add(lblNSP);
					}
					{
						lblVAT = new JLabel(" VAT");
						pnlLabel.add(lblVAT);
					}
				}
				{
					JPanel pnlFormattedField = new JPanel(new GridLayout(3, 1, 3, 3));
					pnlNorthEast.add(pnlFormattedField, BorderLayout.CENTER);
					{
						txtGSP = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
						pnlFormattedField.add(txtGSP);
						txtGSP.setFormatterFactory(_JXFormattedTextField.DECIMAL);
						txtGSP.setEditable(false);
					}
					{
						txtNSP = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
						pnlFormattedField.add(txtNSP);
						txtNSP.setFormatterFactory(_JXFormattedTextField.DECIMAL);
						txtNSP.setEditable(false);
					}
					{
						txtVAT = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
						pnlFormattedField.add(txtVAT);
						txtVAT.setFormatterFactory(_JXFormattedTextField.DECIMAL);
						txtVAT.setEditable(false);
					}
				}
			}
		}
		{
			JPanel pnlCenter = new JPanel(new BorderLayout(5, 5));
			this.add(pnlCenter, BorderLayout.CENTER);
			pnlCenter.setBorder(components.JTBorderFactory.createTitleBorder("Reservation"));
			{
				pnlCenterNorth = new JPanel(new BorderLayout(5, 0));
				pnlCenter.add(pnlCenterNorth, BorderLayout.NORTH);
				pnlCenterNorth.setPreferredSize(new Dimension(575, 100));
//				{
//					JPanel pnlCenterNorthWest = new JPanel(new SpringLayout());
//					pnlCenterNorth.add(pnlCenterNorthWest, BorderLayout.WEST);
//					// pnlCenterNorthWest.setBorder(_LINE_BORDER);
//					pnlCenterNorthWest.setPreferredSize(new Dimension(190, 0));
//					{
//						lblSalesAgent = new JLabel("Sales Agent");
//						pnlCenterNorthWest.add(lblSalesAgent);
//					}
//					{
//						lookupSalesAgentID = new _JLookup(null, "Agent", 0);
//						pnlCenterNorthWest.add(lookupSalesAgentID);
//						lookupSalesAgentID.setPrompt("Agent ID");
//						lookupSalesAgentID.setFilterName(true);
//						lookupSalesAgentID.addLookupListener(new LookupListener() {
//							public void lookupPerformed(LookupEvent event) {
//								Object[] data = ((_JLookup) event.getSource()).getDataSet();
//								if (data != null) {
//									txtSalesAgentName.setText((String) data[1]);
//									FncSystem.out("Display Lookup SQL", lookupSalesAgentID.getLookupSQL());
//								}
//							}
//						});
//						lookupSalesAgentID.addFocusListener(new FocusAdapter() {
//							public void focusGained(FocusEvent e) {
//								String proj_id = lookupProject.getValue();
//								String unit_id = lookupUnit.getValue();
//
//								// ((_JLookup)e.getSource()).setLookupSQL(_HoldingAndReservation.sqlAgents(entity_id,
//								// proj_id, unit_id));
//
//								// 2016-09-26 Edited by John Lester Fatallo to Replace Sales Division
//								((_JLookup) e.getSource())
//										.setLookupSQL(_HoldingAndReservation.sqlAgents(entity_id, proj_id, ""));
//							}
//						});
//					}
//					{
//						lblCluster = new JLabel("Cluster");
//						pnlCenterNorthWest.add(lblCluster);
//					}
//					{
//						lookupCluster = new _JLookup("Cluster");
//						pnlCenterNorthWest.add(lookupCluster);
//						lookupCluster.setPrompt("Cluster ID");
//						/*
//						 * if(isIncomeEncoded(entity_id)) {
//						 * lookupCluster.setLookupSQL(sqlIncomeCluster()); }
//						 */
//						lookupCluster.setLookupSQL(sqlIncomeCluster());
//						lookupCluster.addFocusListener(new FocusAdapter() {
//							public void focusGained(FocusEvent e) {
//								// lookupBuyerType.setLookupSQL(_HoldingAndReservation.sqlBuyertype(sub_proj_id,
//								// _HoldingAndReservation.entityKind(entity_id)));
//							}
//						});
//						lookupCluster.addMouseListener(new MouseListener() {
//
//							@Override
//							public void mouseReleased(MouseEvent e) {
//								// TODO Auto-generated method stub
//
//							}
//
//							@Override
//							public void mousePressed(MouseEvent e) {
//								// TODO Auto-generated method stub
//
//							}
//
//							@Override
//							public void mouseExited(MouseEvent e) {
//								// TODO Auto-generated method stub
//
//							}
//
//							@Override
//							public void mouseEntered(MouseEvent e) {
//								// TODO Auto-generated method stub
//
//							}
//
//							@Override
//							public void mouseClicked(MouseEvent e) {
//								if (e.getClickCount() >= 2) {
//									/*
//									 * if(isIncomeEncoded(entity_id)) { _JLookupTable dlg = new
//									 * _JLookupTable(FncGlobal.homeMDI, null , "Cluster", sqlIncomeCluster(),
//									 * false); dlg.setLocationRelativeTo(FncGlobal.homeMDI); dlg.setVisible(true);
//									 * 
//									 * Object [] data = dlg.getReturnDataSet(); String cluster_id = (String)
//									 * data[0]; String cluster_desc = (String) data[1];
//									 * lookupCluster.setValue(cluster_id); txtCluster.setText(cluster_desc);
//									 * 
//									 * }
//									 */
//
//									if (isIncomeEncoded(entity_id) == false && (lookupProject.getValue().equals("017")
//											|| lookupProject.getValue().equals("019")
//											|| lookupProject.getValue().equals("018")
//											|| lookupProject.getValue().equals("015"))) {
//										lookupCluster.setValue(null);
//										JOptionPane.showMessageDialog(pnlTemporaryReservation.this,
//												"Please encode client income before selecting cluster", "Income",
//												JOptionPane.WARNING_MESSAGE);
//									}
//
//									/*
//									 * if(clientIncome(entity_id) == new BigDecimal("0.00")) {
//									 * JOptionPane.showMessageDialog(pnlTemporaryReservation.this,
//									 * "Please encode client income before selecting cluster", "Income",
//									 * JOptionPane.WARNING_MESSAGE); }
//									 */
//								}
//							}
//						});
//
//						lookupCluster.addLookupListener(new LookupListener() {
//							public void lookupPerformed(LookupEvent event) {
//								Object[] data = ((_JLookup) event.getSource()).getDataSet();
//								if (data != null) {
//									if (isIncomeEncoded(entity_id)) {
//										String cluster_id = (String) data[0];
//										String cluster_desc = (String) data[1];
//										lookupCluster.setValue(cluster_id);
//										txtCluster.setText(cluster_desc);
//									}
//								}
//							}
//						});
//					}
//					{
//						lblBuyerType = new JLabel("*Buyer Type");
//						pnlCenterNorthWest.add(lblBuyerType);
//					}
//
//					{
//						lookupBuyerType = new _JLookup(null, "Buyer Type", 0);
//						pnlCenterNorthWest.add(lookupBuyerType);
//						lookupBuyerType.setPrompt("Type ID");
//						lookupBuyerType.addFocusListener(new FocusAdapter() {
//							public void focusGained(FocusEvent e) {
//								lookupBuyerType.setLookupSQL(_HoldingAndReservation.sqlBuyertype(sub_proj_id,
//										_HoldingAndReservation.entityKind(entity_id)));
//							}
//						});
//						lookupBuyerType.addLookupListener(new LookupListener() {
//							public void lookupPerformed(LookupEvent event) {
//								Object[] data = ((_JLookup) event.getSource()).getDataSet();
//								if (data != null) {
//									FncSystem.out("Display Lookup for Buyer Type:", lookupBuyerType.getLookupSQL());
//
//									txtBuyerType.setText((String) data[1]);
//
//									lookupPmtScheme.setValue(null);
//									txtPmtScheme.setText("");
//
//									txtDiscRate.setValue(null);
//									txtDiscAmount.setValue(null);
//									txtDPEquity.setValue(null);
//									txtAvailedAmount.setValue(null);
//
//									modelPaymentDetails.clear();
//									rowHeaderPaymentDetails.setModel(new DefaultListModel());
//									scrollPaymentDetails.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables
//											.getRowHeader_Footer(Integer.toString(tblPaymentDetails.getRowCount())));
//									tblPaymentDetails.packAll();
//								}
//							}
//						});
//					}
//					{
//						lblPmtScheme = new JLabel("*Pmt. Scheme");
//						pnlCenterNorthWest.add(lblPmtScheme);
//					}
//					{
//						lookupPmtScheme = new _JLookup(null, "Payment Scheme");
//						pnlCenterNorthWest.add(lookupPmtScheme);
//						lookupPmtScheme.setPrompt("Scheme ID");
//						lookupPmtScheme.addFocusListener(new FocusAdapter() {
//							public void focusGained(FocusEvent e) {
//								lookupPmtScheme.setLookupSQL(_HoldingAndReservation.sqlPaymentScheme(entity_id,
//										lookupProject.getValue(), lookupUnit.getValue(), lookupBuyerType.getValue(),
//										txtModelID.getText(), sub_proj_id, lookupCluster.getValue()));
//								System.out.printf("Display value of Pmt Scheme 0: %s%n", lookupPmtScheme.getValue());
//							}
//						});
//						lookupPmtScheme.addLookupListener(new LookupListener() {
//							public void lookupPerformed(LookupEvent event) {
//								Object[] data = ((_JLookup) event.getSource()).getDataSet();
//								lookupPmtScheme.setValue(null);
//								// FncSystem.out("Display SQL for Payment Scheme: ",
//								// lookupPmtScheme.getLookupSQL());
//								System.out.printf("Display value of Pmt Scheme 1: %s%n", lookupPmtScheme.getValue());
//								if (data != null) {
//									String pmt_scheme_id = (String) data[0];
//									String pmt_scheme = (String) data[1];
//									BigDecimal selling_price = (BigDecimal) txtGSP.getValued();
//									BigDecimal disc_rate = (BigDecimal) data[2];
//									String buyer_type_id = (String) data[5];
//									String buyer_type = (String) data[6];
//									String type_group_id = (String) data[7];
//
//									lookupPmtScheme.setValue(pmt_scheme_id);
//									txtPmtScheme.setText((String) data[1]);
//									txtPmtScheme.setCaretPosition(0);
//									txtDiscRate.setValue(disc_rate);
//									txtDiscRate.setEditable(disc_rate != null);
//
//									lookupBuyerType.setValue(buyer_type_id);
//									txtBuyerType.setText(buyer_type);
//
//									if (isEmployee(entity_id) || entity_id.equals("0973502931")) {
//										txtDiscAmount.setEditable(true);
//									}
//
//									System.out.printf("Display value of Pmt Scheme 2: %s%n",
//											lookupPmtScheme.getValue());
//
//									setTRAmtDetails(entity_id, lookupProject.getValue(), lookupUnit.getValue(),
//											other_unit, (BigDecimal) txtGSP.getValued(), disc_rate, pmt_scheme_id);
//									displayPaymentSchemeDetails((String) data[0], true, lookupUnit.getValue());
//
//									if (type_group_id.equals("04")) {
//										txtAvailedAmount.setEditable(true);
//									} else {
//										txtAvailedAmount.setEditable(false);
//									}
//								}
//							}
//						});
//					}
//					{
//						JXTextField txtBox = new JXTextField();
//						pnlCenterNorthWest.add(txtBox);
//						txtBox.setOpaque(false);
//						txtBox.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
//						txtBox.setEditable(false);
//					}
//					{
//						JXTextField txtBox = new JXTextField();
//						pnlCenterNorthWest.add(txtBox);
//						txtBox.setOpaque(false);
//						txtBox.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
//						txtBox.setEditable(false);
//					}
//					{
//						pnlCenterNorthWest.add(Box.createHorizontalBox());
//					}
//					
//					// SpringUtilities.makeCompactGrid(pnlCenterNorthWest, 3, 2, 5, 0, 3, 3, false);
//					SpringUtilities.makeCompactGrid(pnlCenterNorthWest, 4, 2, 5, 0, 3, 3, 0, 90, false);
//				}

				// Updated to change the layout - 09/22/2021 Monique Boriga
				{
					JPanel pnlCenterNorthWest = new JPanel(new GridLayout(4, 2, 3, 3));
					pnlCenterNorth.add(pnlCenterNorthWest, BorderLayout.WEST);
					// pnlCenterNorthWest.setBorder(_LINE_BORDER);
					pnlCenterNorthWest.setPreferredSize(new Dimension(190, 0));
					{
						lblSalesAgent = new JLabel("Sales Agent");
						pnlCenterNorthWest.add(lblSalesAgent);
					}
					{
						lookupSalesAgentID = new _JLookup(null, "Agent", 0);
						pnlCenterNorthWest.add(lookupSalesAgentID);
						lookupSalesAgentID.setPrompt("Agent ID");
						lookupSalesAgentID.setFilterName(true);
						lookupSalesAgentID.addLookupListener(new LookupListener() {
							public void lookupPerformed(LookupEvent event) {
								Object[] data = ((_JLookup) event.getSource()).getDataSet();
								if (data != null) {
									txtSalesAgentName.setText((String) data[1]);
									FncSystem.out("Display Lookup SQL", lookupSalesAgentID.getLookupSQL());
								}
							}
						});
						lookupSalesAgentID.addFocusListener(new FocusAdapter() {
							public void focusGained(FocusEvent e) {
								String proj_id = lookupProject.getValue();
								String unit_id = lookupUnit.getValue();

								// ((_JLookup)e.getSource()).setLookupSQL(_HoldingAndReservation.sqlAgents(entity_id,
								// proj_id, unit_id));

								// 2016-09-26 Edited by John Lester Fatallo to Replace Sales Division
								((_JLookup) e.getSource())
										.setLookupSQL(_HoldingAndReservation.sqlAgents(entity_id, proj_id, ""));
							}
						});
					}
					{
						lblCluster = new JLabel("Cluster");
						pnlCenterNorthWest.add(lblCluster);
					}
					{
						lookupCluster = new _JLookup("Cluster");
						pnlCenterNorthWest.add(lookupCluster);
						lookupCluster.setPrompt("Cluster ID");
						/*
						 * if(isIncomeEncoded(entity_id)) {
						 * lookupCluster.setLookupSQL(sqlIncomeCluster()); }
						 */
						lookupCluster.setLookupSQL(sqlIncomeCluster());
						lookupCluster.addFocusListener(new FocusAdapter() {
							public void focusGained(FocusEvent e) {
								// lookupBuyerType.setLookupSQL(_HoldingAndReservation.sqlBuyertype(sub_proj_id,
								// _HoldingAndReservation.entityKind(entity_id)));
							}
						});
						lookupCluster.addMouseListener(new MouseListener() {

							@Override
							public void mouseReleased(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mousePressed(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mouseExited(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mouseEntered(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mouseClicked(MouseEvent e) {
								if (e.getClickCount() >= 2) {
									/*
									 * if(isIncomeEncoded(entity_id)) { _JLookupTable dlg = new
									 * _JLookupTable(FncGlobal.homeMDI, null , "Cluster", sqlIncomeCluster(),
									 * false); dlg.setLocationRelativeTo(FncGlobal.homeMDI); dlg.setVisible(true);
									 * 
									 * Object [] data = dlg.getReturnDataSet(); String cluster_id = (String)
									 * data[0]; String cluster_desc = (String) data[1];
									 * lookupCluster.setValue(cluster_id); txtCluster.setText(cluster_desc);
									 * 
									 * }
									 */

									if (isIncomeEncoded(entity_id) == false && (lookupProject.getValue().equals("017")
											|| lookupProject.getValue().equals("019")
											|| lookupProject.getValue().equals("018")
											|| lookupProject.getValue().equals("015"))) {
										lookupCluster.setValue(null);
										JOptionPane.showMessageDialog(pnlTemporaryReservation.this,
												"Please encode client income before selecting cluster", "Income",
												JOptionPane.WARNING_MESSAGE);
									}

									/*
									 * if(clientIncome(entity_id) == new BigDecimal("0.00")) {
									 * JOptionPane.showMessageDialog(pnlTemporaryReservation.this,
									 * "Please encode client income before selecting cluster", "Income",
									 * JOptionPane.WARNING_MESSAGE); }
									 */
								}
							}
						});

						lookupCluster.addLookupListener(new LookupListener() {
							public void lookupPerformed(LookupEvent event) {
								Object[] data = ((_JLookup) event.getSource()).getDataSet();
								if (data != null) {
									if (isIncomeEncoded(entity_id)) {
										String cluster_id = (String) data[0];
										String cluster_desc = (String) data[1];
										lookupCluster.setValue(cluster_id);
										txtCluster.setText(cluster_desc);
									}
								}
							}
						});
					}
					{
						lblBuyerType = new JLabel("*Buyer Type");
						pnlCenterNorthWest.add(lblBuyerType);
					}

					{
						lookupBuyerType = new _JLookup(null, "Buyer Type", 0);
						pnlCenterNorthWest.add(lookupBuyerType);
						lookupBuyerType.setPrompt("Type ID");
						lookupBuyerType.addFocusListener(new FocusAdapter() {
							public void focusGained(FocusEvent e) {
								lookupBuyerType.setLookupSQL(_HoldingAndReservation.sqlBuyertype(sub_proj_id,
										_HoldingAndReservation.entityKind(entity_id)));
							}
						});
						lookupBuyerType.addLookupListener(new LookupListener() {
							public void lookupPerformed(LookupEvent event) {
								Object[] data = ((_JLookup) event.getSource()).getDataSet();
								if (data != null) {
									FncSystem.out("Display Lookup for Buyer Type:", lookupBuyerType.getLookupSQL());

									txtBuyerType.setText((String) data[1]);
									
									lookupPmtScheme.setValue(null);
									txtPmtScheme.setText("");

									txtDiscRate.setValue(null);
									txtDiscAmount.setValue(null);
									txtDPEquity.setValue(null);
									txtAvailedAmount.setValue(null);

									modelPaymentDetails.clear();
									rowHeaderPaymentDetails.setModel(new DefaultListModel());
									scrollPaymentDetails.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables
											.getRowHeader_Footer(Integer.toString(tblPaymentDetails.getRowCount())));
									tblPaymentDetails.packAll();
								}
							}
						});
					}
					{
						lblPmtScheme = new JLabel("*Pmt. Scheme");
						pnlCenterNorthWest.add(lblPmtScheme);
					}
					{
						lookupPmtScheme = new _JLookup(null, "Payment Scheme");
						pnlCenterNorthWest.add(lookupPmtScheme);
						lookupPmtScheme.setPrompt("Scheme ID");

						lookupPmtScheme.addFocusListener(new FocusAdapter() {
							public void focusGained(FocusEvent e) {

								lookupPmtScheme.setLookupSQL(_HoldingAndReservation.sqlPaymentScheme(entity_id,
										lookupProject.getValue(), lookupUnit.getValue(), lookupBuyerType.getValue(),
										txtModelID.getText(), sub_proj_id, lookupCluster.getValue()));
								System.out.printf("Display value of Pmt Scheme 0: %s%n", lookupPmtScheme.getValue());

								/*
								 * System.out.printf("Display value of Pmt Scheme 0: %s%n",
								 * lookupPmtScheme.getValue());
								 * lookupPmtScheme.setLookupSQL(_HoldingAndReservation.sqlPaymentScheme(
								 * entity_id, lookupProject.getValue(), lookupUnit.getValue(),
								 * lookupBuyerType.getValue(), txtModelID.getText(), sub_proj_id,
								 * lookupCluster.getValue()));
								 */

							}
						});
						lookupPmtScheme.addLookupListener(new LookupListener() {
							public void lookupPerformed(LookupEvent event) {
								Object[] data = ((_JLookup) event.getSource()).getDataSet();
								lookupPmtScheme.setValue(null);
								// FncSystem.out("Display SQL for Payment Scheme: ",
								// lookupPmtScheme.getLookupSQL());
								System.out.printf("Display value of Pmt Scheme 1: %s%n", lookupPmtScheme.getValue());
								if (data != null) {
									String pmt_scheme_id = (String) data[0];
									String pmt_scheme = (String) data[1];
									BigDecimal selling_price = (BigDecimal) txtGSP.getValued();
									BigDecimal disc_rate = (BigDecimal) data[2];
									String buyer_type_id = (String) data[5];
									String buyer_type = (String) data[6];
									String type_group_id = (String) data[7];

									lookupPmtScheme.setValue(pmt_scheme_id);
									txtPmtScheme.setText((String) data[1]);
									txtPmtScheme.setCaretPosition(0);
									txtDiscRate.setValue(disc_rate);
									txtDiscRate.setEditable(disc_rate != null);
									lookupBuyerType.setValue(buyer_type_id);
									txtBuyerType.setText(buyer_type);

									if (isEmployee(entity_id) || entity_id.equals("0973502931") || entity_id.equals("9903083844") || entity_id.equals("2445540159")) {
										txtDiscAmount.setEditable(true);
									}

									System.out.printf("Display value of Pmt Scheme 2: %s%n",
											lookupPmtScheme.getValue());

									setTRAmtDetails(entity_id, lookupProject.getValue(), lookupUnit.getValue(),
											other_unit, (BigDecimal) txtGSP.getValued(), disc_rate, pmt_scheme_id);
									displayPaymentSchemeDetails((String) data[0], true, lookupUnit.getValue());

									if (type_group_id.equals("04")) {
										cboxChangeLA.setEnabled(true);
									} else {
										cboxChangeLA.setEnabled(false);
									}

									cboxChangeLA.addItemListener(new ItemListener() {

										@Override
										public void itemStateChanged(ItemEvent arg0) {
											if (cboxChangeLA.isSelected()) {
												txtAvailedAmount.setEditable(true);
											} else {
												txtAvailedAmount.setEditable(false);
											}

										}
									});
								}
							}
						});
					}
				}
//				{
//					JPanel pnlCenterNorthCenter = new JPanel(new SpringLayout());
//					pnlCenterNorth.add(pnlCenterNorthCenter, BorderLayout.CENTER);
//					{
//						txtSalesAgentName = new _JXTextField("Agent Name");
//						pnlCenterNorthCenter.add(txtSalesAgentName);
//					}
//					{
//						txtCluster = new _JXTextField("Cluster");
//						pnlCenterNorthCenter.add(txtCluster);
//					}
//					{
//						txtBuyerType = new _JXTextField("Type Name");
//						pnlCenterNorthCenter.add(txtBuyerType);
//					}
//					{
//						txtPmtScheme = new _JXTextField("Scheme Name");
//						pnlCenterNorthCenter.add(txtPmtScheme);
//					}
//					{
//						JXTextField txtBox = new JXTextField();
//						pnlCenterNorthCenter.add(txtBox);
//						txtBox.setOpaque(false);
//						txtBox.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
//						txtBox.setEditable(false);
//					}
//					{
//						pnlCenterNorthCenter.add(Box.createHorizontalBox());
//					}
//					SpringUtilities.makeCompactGrid(pnlCenterNorthCenter, 4, 1, 0, 0, 5, 3, false);
//				}

				// Updated to change the layout - 09/22/2021 Monique Boriga
				{
					JPanel pnlCenterNorthCenter = new JPanel(new GridLayout(4, 1, 3, 3));
					pnlCenterNorth.add(pnlCenterNorthCenter, BorderLayout.CENTER);
					{
						txtSalesAgentName = new _JXTextField("Agent Name");
						pnlCenterNorthCenter.add(txtSalesAgentName);
					}
					{
						txtCluster = new _JXTextField("Cluster");
						pnlCenterNorthCenter.add(txtCluster);
					}
					{
						txtBuyerType = new _JXTextField("Type Name");
						pnlCenterNorthCenter.add(txtBuyerType);
					}
					{
						txtPmtScheme = new _JXTextField("Scheme Name");
						pnlCenterNorthCenter.add(txtPmtScheme);
					}
				}
//				{
//					pnlCenterNorthEast = new JPanel(new SpringLayout());
//					pnlCenterNorth.add(pnlCenterNorthEast, BorderLayout.EAST);
//					pnlCenterNorthEast.setPreferredSize(new Dimension(225, 0));
//					{
//						lblDiscRate = new JLabel("Disc. Rate");
//						pnlCenterNorthEast.add(lblDiscRate);
//					}
//					{
//						txtDiscRate = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
//						pnlCenterNorthEast.add(txtDiscRate);
//						txtDiscRate.setFormatterFactory(_JXFormattedTextField.DECIMAL);
//						txtDiscRate.setEditable(false);
//						/*
//						 * txtDiscRate.setEditable(true); txtDiscRate.setEnabled(true);
//						 */
//						txtDiscRate.addKeyListener(new KeyAdapter() {
//							public void keyReleased(KeyEvent e) {// XXX Discount Rate
//								BigDecimal disc_rate = (BigDecimal) ((_JXFormattedTextField) e.getSource()).getValued();
//
//								BigDecimal selling_price = (BigDecimal) txtGSP.getValued();
//
//								txtDiscAmount.setValue(selling_price.multiply(disc_rate.divide(new BigDecimal("100"))));
//
//								BigDecimal gsp = (BigDecimal) txtGSP.getValued();
//								BigDecimal nsp = gsp
//										.subtract(selling_price.multiply(disc_rate.divide(new BigDecimal("100"))));
//								txtNSP.setValue(nsp);
//								txtDPEquity.setValue(nsp.multiply(getDPRate().divide(new BigDecimal("100"))));
//								txtAvailedAmount.setValue(nsp.multiply(getMARate().divide(new BigDecimal("100"))));
//							}
//						});
//					}
//					{
//						lblDiscAmount = new JLabel("Disc. Amount");
//						pnlCenterNorthEast.add(lblDiscAmount);
//					}
//					{
//						txtDiscAmount = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
//						pnlCenterNorthEast.add(txtDiscAmount);
//						txtDiscAmount.setFormatterFactory(_JXFormattedTextField.DECIMAL);
//						txtDiscAmount.setEditable(false);
//						txtDiscAmount.addKeyListener(new KeyAdapter() {
//							public void keyReleased(KeyEvent e) {
//								BigDecimal selling_price = (BigDecimal) txtGSP.getValued();
//								BigDecimal disc_amt = (BigDecimal) ((_JXFormattedTextField) e.getSource()).getValued();
//								BigDecimal dp = (BigDecimal) txtDPEquity.getValued();
//								BigDecimal nsp = selling_price.subtract(disc_amt);
//								txtDiscRate.setValue((disc_amt.doubleValue() / selling_price.doubleValue()) * 100);
//								txtNSP.setValue(nsp);
//								txtAvailedAmount.setValue(nsp.subtract(dp));
//							}
//						});
//					}
//					{
//						lblDPEquity = new JLabel("DP Equity");
//						pnlCenterNorthEast.add(lblDPEquity);
//					}
//					{
//						txtDPEquity = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
//						pnlCenterNorthEast.add(txtDPEquity);
//						txtDPEquity.setFormatterFactory(_JXFormattedTextField.DECIMAL);
//						txtDPEquity.setEditable(false);
//					}
//					{
//						lblChangeLA = new JLabel("Change LA");
//						pnlCenterNorthEast.add(lblChangeLA);
//					}
//					{
//						cboxChangeLA = new JCheckBox();
//						pnlCenterNorthEast.add(cboxChangeLA);
//						cboxChangeLA.setEnabled(false);
//					}
//					{
//						lblAvailedAmount = new JLabel("Availed Amount");
//						pnlCenterNorthEast.add(lblAvailedAmount);
//					}
//					{
//						txtAvailedAmount = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
//						pnlCenterNorthEast.add(txtAvailedAmount);
//						txtAvailedAmount.setFormatterFactory(_JXFormattedTextField.DECIMAL);
//						txtAvailedAmount.setEditable(false);
//						txtAvailedAmount.addKeyListener(new KeyAdapter() {
//							public void keyReleased(KeyEvent e) {
//								try {
//									computeEquity();
//								} catch (NumberFormatException a) {
//								}
//							}
//						});
//					}
//			
//					/*
//					 * { pnlCenterNorthEast.add(Box.createHorizontalBox()); }
//					 */
//					SpringUtilities.makeCompactGrid(pnlCenterNorthEast, 5, 2, 5, 0, 5, 3, false);
//				}

				// Updated to change the layout and added checkbox component for Change of Loan
				// amount
				// 09/22/2021 Monique Boriga
				{
					pnlCenterNorthEast = new JPanel(new GridLayout(5, 2, 3, 3));
					pnlCenterNorth.add(pnlCenterNorthEast, BorderLayout.EAST);
					pnlCenterNorthEast.setPreferredSize(new Dimension(225, 0));
					{
						lblDiscRate = new JLabel("Disc. Rate");
						pnlCenterNorthEast.add(lblDiscRate);
					}
					{
						txtDiscRate = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
						pnlCenterNorthEast.add(txtDiscRate);
						txtDiscRate.setFormatterFactory(_JXFormattedTextField.DECIMAL);
						txtDiscRate.setEditable(false);
						/*
						 * txtDiscRate.setEditable(true); txtDiscRate.setEnabled(true);
						 */
						txtDiscRate.addKeyListener(new KeyAdapter() {
							public void keyReleased(KeyEvent e) {// XXX Discount Rate
								BigDecimal disc_rate = (BigDecimal) ((_JXFormattedTextField) e.getSource()).getValued();

								BigDecimal selling_price = (BigDecimal) txtGSP.getValued();

								txtDiscAmount.setValue(selling_price.multiply(disc_rate.divide(new BigDecimal("100"))));

								BigDecimal gsp = (BigDecimal) txtGSP.getValued();
								BigDecimal nsp = gsp
										.subtract(selling_price.multiply(disc_rate.divide(new BigDecimal("100"))));
								txtNSP.setValue(nsp);
								txtDPEquity.setValue(nsp.multiply(getDPRate().divide(new BigDecimal("100"))));
								txtAvailedAmount.setValue(nsp.multiply(getMARate().divide(new BigDecimal("100"))));
							}
						});
					}
					{
						lblDiscAmount = new JLabel("Disc. Amount");
						pnlCenterNorthEast.add(lblDiscAmount);
					}
					{
						txtDiscAmount = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
						pnlCenterNorthEast.add(txtDiscAmount);
						txtDiscAmount.setFormatterFactory(_JXFormattedTextField.DECIMAL);
						txtDiscAmount.setEditable(false);
						txtDiscAmount.addKeyListener(new KeyAdapter() {
							public void keyReleased(KeyEvent e) {
								BigDecimal selling_price = (BigDecimal) txtGSP.getValued();
								BigDecimal disc_amt = (BigDecimal) ((_JXFormattedTextField) e.getSource()).getValued();
								BigDecimal dp = (BigDecimal) txtDPEquity.getValued();
								BigDecimal nsp = selling_price.subtract(disc_amt);
								txtDiscRate.setValue((disc_amt.doubleValue() / selling_price.doubleValue()) * 100);
								txtNSP.setValue(nsp);
								
								if (lookupBuyerType.getValue().equals("04")) {
									txtDPEquity.setValue(selling_price.subtract(disc_amt));
									txtAvailedAmount.setValue(0.00);
								} else {
									txtAvailedAmount.setValue(nsp.subtract(dp));
								}			
							}
						});
					}
					{
						lblDPEquity = new JLabel("DP Equity");
						pnlCenterNorthEast.add(lblDPEquity);
					}
					{
						txtDPEquity = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
						pnlCenterNorthEast.add(txtDPEquity);
						txtDPEquity.setFormatterFactory(_JXFormattedTextField.DECIMAL);
						txtDPEquity.setEditable(false);
					}
					{
						lblChangeLA = new JLabel("Change LA");
						pnlCenterNorthEast.add(lblChangeLA);
					}
					{
						cboxChangeLA = new JCheckBox();
						pnlCenterNorthEast.add(cboxChangeLA);
						cboxChangeLA.setEnabled(false);
					}
					{
						lblAvailedAmount = new JLabel("Availed Amount");
						pnlCenterNorthEast.add(lblAvailedAmount);
					}
					{
						txtAvailedAmount = new _JXFormattedTextField(JXFormattedTextField.RIGHT);
						pnlCenterNorthEast.add(txtAvailedAmount);
						txtAvailedAmount.setFormatterFactory(_JXFormattedTextField.DECIMAL);
						txtAvailedAmount.setEditable(false);
						txtAvailedAmount.addKeyListener(new KeyAdapter() {
							public void keyReleased(KeyEvent e) {
								try {
									
									if (entity_id.equals("9903083844") && lookupUnit.getValue().equals("TV0006494")) {
										txtDPEquity.setValue(354461.60);	//VAT unit, with Discount, with Change LA and Pag-ibig Reg Scheme 10/05/21
									}
									else
										
									computeEquity();
								
									
								} catch (NumberFormatException a) {
								}
							}
						});
					}
				}
			}
			{
				scrollPaymentDetails = new JScrollPane();
				pnlCenter.add(scrollPaymentDetails, BorderLayout.CENTER);
				scrollPaymentDetails.setBorder(components.JTBorderFactory.createTitleBorder("Payment Scheme Details"));
				scrollPaymentDetails.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scrollPaymentDetails.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						tblPaymentDetails.clearSelection();
					}
				});
				{
					modelPaymentDetails = new modelHoldingAndReservation_PaySchemeDetails();
					modelPaymentDetails.addTableModelListener(new TableModelListener() {
						public void tableChanged(TableModelEvent e) {
							// Addition of rows
							if (e.getType() == 1) {
								((DefaultListModel) rowHeaderPaymentDetails.getModel())
										.addElement(modelPaymentDetails.getRowCount());

								if (modelPaymentDetails.getRowCount() == 0) {
									rowHeaderPaymentDetails.setModel(new DefaultListModel());
								}
							}
						}
					});

					tblPaymentDetails = new _JTableMain(modelPaymentDetails);
					scrollPaymentDetails.setViewportView(tblPaymentDetails);

					tblPaymentDetails.addPropertyChangeListener(new PropertyChangeListener() {// XXX Work Items
						public void propertyChange(PropertyChangeEvent arg0) {
							_JTableMain table = (_JTableMain) arg0.getSource();
							String propertyName = arg0.getPropertyName();

							if (propertyName.equals("tableCellEditor")) {
								int column = table.convertColumnIndexToModel(table.getEditingColumn());
								int row = table.getEditingRow();

								if (column != -1
										&& modelPaymentDetails.getColumnClass(column).equals(BigDecimal.class)) {
									int idxPartID = table.convertColumnIndexToModel(
											table.getColumnModel().getColumnIndex("Part. ID"));
									String part_id = (String) table.getValueAt(row, idxPartID);
									// System.out.printf("Part ID: %s%n", part_id);

									Object oldValue = null;
									try {
										oldValue = ((NumberEditorExt) arg0.getOldValue()).getCellEditorValue();
									} catch (NullPointerException e) {
									}

									if (oldValue != null) {
										BigDecimal nsp = (BigDecimal) txtNSP.getValued();
										BigDecimal rate = null;

										if (oldValue instanceof Long) {
											rate = new BigDecimal((Long) oldValue);
										}
										if (oldValue instanceof Double) {
											rate = new BigDecimal((Double) oldValue);
										}

										if (part_id.equals("013")) {
											BigDecimal twenty = new BigDecimal("20.00");

											// DP Rate must be greater than 20%
											if (rate.compareTo(twenty) <= 0) {
												JOptionPane.showMessageDialog(
														pnlTemporaryReservation.this.getTopLevelAncestor(),
														"DP Rate must be greater than 20%.", "Rate",
														JOptionPane.WARNING_MESSAGE);
												rate = twenty;
												table.setValueAt(rate, row, column);
											}
										}

										_HoldingAndReservation.updatePerRate(modelPaymentDetails, row, part_id, rate,
												nsp, txtDPEquity, txtAvailedAmount);
									}
								}

								if (column != -1 && modelPaymentDetails.getColumnClass(column).equals(Integer.class)) {
									int idxPartID = table.convertColumnIndexToModel(
											table.getColumnModel().getColumnIndex("Part. ID"));
									String part_id = (String) table.getValueAt(row, idxPartID);

									Object newValue = null;
									try {
										newValue = ((NumberEditorExt) arg0.getNewValue()).getCellEditorValue();
									} catch (NullPointerException e) {
									}

									if (newValue != null) {
										System.out.printf("Old Term: %s%n", newValue);
									}

									Object oldValue = null;
									try {
										oldValue = ((NumberEditorExt) arg0.getOldValue()).getCellEditorValue();
									} catch (NullPointerException e) {
									}

									if (oldValue != null) {

										// Monthly Amortization
										if (part_id.equals("014")) {
											System.out.printf("Term: %s (%s)%n", oldValue, (((Integer) oldValue) % 12));
											if ((((Integer) oldValue) % 12) > 0) {
												JOptionPane.showMessageDialog(
														pnlTemporaryReservation.this.getTopLevelAncestor(),
														"MA Term must be divisible by 12.", "Term",
														JOptionPane.WARNING_MESSAGE);
											}
										}
									}
								}
							}
						}
					});
					// tblPaymentDetails.putClientProperty("terminateEditOnFocusLost", true);
				}
				{
					rowHeaderPaymentDetails = tblPaymentDetails.getRowHeader();
					rowHeaderPaymentDetails.setModel(new DefaultListModel());
					scrollPaymentDetails.setRowHeaderView(rowHeaderPaymentDetails);
					scrollPaymentDetails.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER,
							FncTables.getRowHeader_Header());
					scrollPaymentDetails.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER,
							FncTables.getRowHeader_Footer(Integer.toString(tblPaymentDetails.getRowCount())));
				}
			}
		}

		cancelTR();
	}// End of initGUI

	public void clearFields() {
		lookupProject.setValue(null);
		txtProjectName.setText("");
		lookupUnit.setValue(null);
		txtUnit.setText("");
		txtModelID.setText("");
		txtModelName.setText("");
		txtGSP.setValue(null);
		txtNSP.setValue(null);
		txtVAT.setValue(null);

		lookupSalesAgentID.setValue(null);
		txtSalesAgentName.setText("");
		lookupBuyerType.setValue(null);
		txtBuyerType.setText("");
		lookupPmtScheme.setValue(null);
		txtPmtScheme.setText("");
		txtDiscRate.setValue(null);
		txtDiscRate.setEditable(false);
		txtDiscRate.setEditable(true);
		txtDiscAmount.setValue(null);
		txtDPEquity.setValue(null);
		txtAvailedAmount.setValue(null);
		sub_proj_id = null;
		other_unit = "";
		other_model_id = "";
		withOtherUnit = false;

		rowHeaderPaymentDetails.setModel(new DefaultListModel());
		modelPaymentDetails.clear();
		scrollPaymentDetails.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER,
				FncTables.getRowHeader_Footer(Integer.toString(tblPaymentDetails.getRowCount())));
	}

	public Boolean validateClient(String entity_id) {
		String SQL = "SELECT date_of_birth from rf_entity where entity_id = '" + entity_id + "';";
		pgSelect db = new pgSelect();
		db.select(SQL);

		return db.isNotNull();
	}

	public Boolean newTR(String entity_id, String entity_name) {
		this.entity_id = entity_id.intern();
		this.entity_name = entity_name.intern();
		if (isIncomeEncoded(entity_id)) {
			lookupCluster.setLookupSQL(sqlIncomeCluster());
		}

		pgSelect db = new pgSelect();
		db.select("SELECT is_qualified_for_tr('" + entity_id + "');", "Temporary Reservation", true);

		if ((Boolean) db.getResult()[0][0]) {
			Object[] options = new Object[] { "Hold", "Open" };
			int index = JOptionPane.showOptionDialog(this.getTopLevelAncestor(), "Tag unit for reservation.", "New",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

			System.out.printf("Selected: %s%n", index);
			if (index >= 0) {
				if (index == JOptionPane.YES_OPTION) {
					isDirectTR = false;
					pnlNorth.setBorder(components.JTBorderFactory
							.createTitleBorder(String.format("Details (%s)", options[index])));
					lookupProject.setLookupSQL(_HoldingAndReservation.sqlProject(entity_id));
				} else {
					isDirectTR = true;
					pnlNorth.setBorder(components.JTBorderFactory
							.createTitleBorder(String.format("Details (%s)", options[index])));
					lookupProject.setLookupSQL(_JInternalFrame.SQL_PROJECT());
				}

				clearFields();
				lookupProject.requestFocus();
				setComponentsEnabled(this, true);
				lookupProject.setEditable(true);
				lookupUnit.setEditable(true);
				lookupSalesAgentID.setEditable(true);
				lookupBuyerType.setEditable(false);
				lookupPmtScheme.setEditable(true);
				cboxChangeLA.setEnabled(false);
				tblPaymentDetails.setEditable(true);

				if (isEmployee(entity_id) || entity_id.equals("0973502931")) {
					lookupSalesAgentID.setValue("1212121212");
					txtSalesAgentName.setText("CENQHOMES DEVELOPMENT CORPORATION");
				} else {
					lookupSalesAgentID.setValue(null);
					txtSalesAgentName.setText("");
				}
			}
			return index >= 0;
		} else {
			if (FncGlobal.homeMDI.isNotExisting("ClientInformation")) {
				ClientInformation ci = new ClientInformation("Client Information", entity_id, entity_name);
				Home_ArcSystem.addWindow(ci, null);
			}
			return false;
		}
	}

	public Boolean isIncomeEncoded(String entity_id) {
		this.entity_id = entity_id.intern();

		Boolean isEncoded = false;

		pgSelect db = new pgSelect();
		String SQL = "SELECT EXISTS (SELECT * FROM rf_client_employer WHERE entity_id = '" + entity_id
				+ "' AND COALESCE(totincomepermth, 0) != 0 AND status_id = 'A')";
		db.select(SQL);

		if (db.isNotNull()) {
			isEncoded = (Boolean) db.getResult()[0][0];
		}
		return isEncoded;
	}

	private BigDecimal clientIncome(String entity_id) {

		BigDecimal income = new BigDecimal("0.00");

		pgSelect db = new pgSelect();
		String SQL = "SELECT COALESCE(totincomepermth, 0) FROM rf_client_employer WHERE entity_id = '" + entity_id
				+ "' AND status_id = 'A'";

		if (db.isNotNull()) {
			income = (BigDecimal) db.getResult()[0][0];
		}

		return income;
	}

	private String sqlIncomeCluster() {
		return "SELECT cluster_id as \"ID\", cluster_description as \"Description\", Income as \"Income\" \n"
				+ "FROM mf_income_cluster\n" + "WHERE status_id = 'A';";
	}

	public void cancelTR() {
		clearFields();
		setComponentsEnabled(pnlNorth, false);
		setComponentsEnabled(pnlCenterNorth, false);
		cboxChangeLA.setSelected(false);
		pnlNorth.setBorder(components.JTBorderFactory.createTitleBorder("Details"));

		/*
		 * if(lookupProject.getValue() != null && lookupUnit.getValue() != null){
		 * _HoldingAndReservation.updateStatus(lookupProject.getValue(),
		 * lookupUnit.getValue(), "A"); }
		 */

	}

	private void displayDP_MA(String pmt_scheme_id) {
		String proj_id = lookupProject.getValue();
		String pbl_id = lookupUnit.getValue();
		String byrtype_id = lookupBuyerType.getValue();

		/*
		 * txtDPEquity.setValue(_HoldingAndReservation.getDP(proj_id, pbl_id,
		 * byrtype_id, pmt_scheme_id));
		 * txtAvailedAmount.setValue(_HoldingAndReservation.getMA(proj_id, pbl_id,
		 * byrtype_id, pmt_scheme_id));
		 */

		txtDPEquity.setValue(_HoldingAndReservation.getDP(proj_id, pbl_id, other_unit, byrtype_id, pmt_scheme_id));
		txtAvailedAmount.setValue(_HoldingAndReservation.getMA(proj_id, pbl_id, other_unit, byrtype_id, pmt_scheme_id));
	}

	private void setTRAmtDetails(String entity_id, String proj_id, String unit_id1, String unit_id2, BigDecimal gsp,
			BigDecimal disc_rate, String pmt_scheme_id) {
		BigDecimal disc_amt = new BigDecimal("0");
		BigDecimal disc_rate_value = new BigDecimal("0");
		BigDecimal nsp = new BigDecimal("0");
		BigDecimal dp_equity = new BigDecimal("0");
		BigDecimal loanable_amt = new BigDecimal("0");

		pgSelect db = new pgSelect();
		String SQL = "SELECT * FROM compute_tr_details('" + entity_id + "', '" + proj_id + "', '" + unit_id1 + "', '"
				+ unit_id2 + "', " + gsp + ", " + disc_rate + ", '" + pmt_scheme_id + "');";
		db.select(SQL);
		FncSystem.out("Display TR Details", SQL);

		if (db.isNotNull()) {
			disc_amt = (BigDecimal) db.getResult()[0][0];
			disc_rate_value = (BigDecimal) db.getResult()[0][1];
			nsp = (BigDecimal) db.getResult()[0][2];
			dp_equity = (BigDecimal) db.getResult()[0][3];
			loanable_amt = (BigDecimal) db.getResult()[0][4];
		}

		txtDiscAmount.setValue(disc_amt);
		txtDiscRate.setValue(disc_rate_value);
		txtNSP.setValue(nsp);
		txtDPEquity.setValue(dp_equity);
		txtAvailedAmount.setValue(loanable_amt);
	}

	private void displayPaymentSchemeDetails(String pmt_scheme_id, Boolean fromLookup, String unit_id) {
		modelPaymentDetails.clear();
		rowHeaderPaymentDetails.setModel(new DefaultListModel());

		pgSelect db = new pgSelect();

		String SQL2 = "SELECT sp_compute_payment_term('" + entity_id + "', '" + lookupBuyerType.getValue()
				+ "')::int * 12;";
		db.select(SQL2);
		FncSystem.out("Dsiaply SQL for Compute Payment Terms", SQL2);

		if (lookupBuyerType.getValue().equals("")) {

		} else {

		}
		if (db.isNotNull() && db.getResult()[0][0] != null) {
			Integer term = (Integer) db.getResult()[0][0];
			if (fromLookup && term != null) {
				String buyertype = lookupBuyerType.getValue();
				String SQL3 = "SELECT get_group_id('" + buyertype + "')";
				db.select(SQL3);
				String group_id = (String) db.getResult()[0][0];
				/*
				 * if(group_id.equals("05")){//ADJUSTED BASED ON DCRF 234 term = 1; }
				 */
				if (group_id.equals("03") == false) {
					String message = String.format("Possible Monthly Amortization Term is %s years or %s months.",
							(term / 12), term);
					JOptionPane.showMessageDialog(this.getTopLevelAncestor(), message, "Payment Scheme",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

			String SQL = "SELECT a.part_sequence, a.part_id, b.part_desc, a.rate, a.amount, a.term\n"
					+ "FROM mf_pay_scheme_detail a\n" + "LEFT JOIN mf_client_ledger_part b ON b.part_id = a.part_id\n"
					+ "WHERE a.pmt_scheme_id = '" + pmt_scheme_id + "' AND a.part_id != '019'\n"
					+ "ORDER BY a.part_sequence;";
			db.select(SQL);

			FncSystem.out("Display SQL For Pay Scheme Detials", SQL);

			if (db.isNotNull()) {
				for (int x = 0; x < db.getRowCount(); x++) {
					Integer seq = (Integer) db.getResult()[x][0];
					String part_id = (String) db.getResult()[x][1];

					modelPaymentDetails.addRow(db.getResult()[x]);
					if (part_id.equals("012")) {
						// modelPaymentDetails.setValueAt(getResFeeOtherUnit(txtModelID.getText(), seq,
						// withOtherUnit), x, 4);
						modelPaymentDetails.setValueAt(getResFee(seq, txtModelID.getText(), other_model_id, unit_id), x,
								4);
					}
					if (part_id.equals("014")) {
						modelPaymentDetails.setValueAt(term, x, 5);
					}
				}
				scrollPaymentDetails.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER,
						FncTables.getRowHeader_Footer(Integer.toString(tblPaymentDetails.getRowCount())));
				tblPaymentDetails.packAll();
			}
		} else {
			JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Please input the birth date of the client.",
					"Payment Scheme", JOptionPane.WARNING_MESSAGE);
		}
	}

	@SuppressWarnings("unused")
	private void displayDocumentsMonitoring() {
		if (FncGlobal.homeMDI.isNotExisting("DocumentsMonitoring")) {
			String buyer_type_id = lookupBuyerType.getValue();
			String buyer_type_name = txtBuyerType.getText();
			String current_status_id = null;
			String current_status_name = "HOLD";
			String company_id = null;
			String company_name = null;
			String project_id = lookupProject.getValue();
			String project_name = txtProjectName.getText();
			String unit_id = lookupUnit.getValue();
			String unit_description = txtUnit.getText();
			String model_id = txtModelID.getText();
			String model_name = txtModelName.getText();
			Integer seq_no = Integer.parseInt(txtSequence.getText());

			Pattern pattern = Pattern.compile("\\d+(?:\\.\\d+)?"); // Match int or float
			Matcher matcher = pattern.matcher(unit_id);
			String pbl_id = "";
			if (matcher.find()) {
				pbl_id = Integer.toString(Integer.parseInt(matcher.group()));
			}

			Object[] data = new Object[] { entity_id, entity_name, buyer_type_id, buyer_type_name, current_status_id,
					current_status_name, company_id, company_name, project_id, project_name, unit_id, unit_description,
					model_id, model_name, pbl_id, seq_no, null };

			DocumentsMonitoring dm = new DocumentsMonitoring(data);
			Home_ArcSystem.addWindow(dm);
		}
	}

	public Boolean toSave() {
		if (lookupProject.getValue() == null) {
			JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Please select project.", "Save",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (lookupUnit.getValue() == null) {
			JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Please select unit.", "Save",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (lookupSalesAgentID.getValue() == null && entity_id.equals("6278286977") == false) {
			JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Please select agent", "Save",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (lookupBuyerType.getValue() == null) {
			JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Please select buyer type.", "Save",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (lookupPmtScheme.getValue() == null) {
			JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Please select payment scheme.", "Save",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (tblPaymentDetails.getRowCount() == 0) {
			JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Please input the birth date of the client.",
					"Save", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if ((getMATerm() % 12) > 0) {
			JOptionPane.showMessageDialog(pnlTemporaryReservation.this.getTopLevelAncestor(),
					"MA Term must be divisible by 12.", "Term", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		return true;
	}

	private BigDecimal getResFee(String model_id, Integer seq) {
		BigDecimal res_fee = new BigDecimal("0.00");

		pgSelect db = new pgSelect();
		String SQL = "select case when " + seq
				+ " = 1 then res_fee1 else res_fee2 end from mf_product_model where model_id = '" + model_id + "';";
		db.select(SQL);

		if (db.isNotNull()) {
			res_fee = (BigDecimal) db.getResult()[0][0];
		}

		return res_fee;
	}

	private BigDecimal getResFeeOtherUnit(String model_id, Integer seq, Boolean with_other_unit) {
		BigDecimal res_fee = FncBigDecimal.zeroValue();

		if (with_other_unit) {
			if (seq == 1) {
				res_fee = new BigDecimal("5000");
			} else {
				res_fee = new BigDecimal("15000");
			}
		} else {
			pgSelect db = new pgSelect();
			String SQL = "SELECT case when " + seq
					+ " = 1 then res_fee1 else res_fee2 end from mf_product_model where model_id = '" + model_id + "'";
			db.select(SQL);

			if (db.isNotNull()) {
				res_fee = (BigDecimal) db.getResult()[0][0];
			}
		}

		return res_fee;
	}

	// XXX Check Reservation Fee for Combined Units with Same House Model
	private BigDecimal getResFee(Integer seq, String model_id1, String model_id2, String unit_id) {
		BigDecimal res_fee = FncBigDecimal.zeroValue();

		pgSelect db = new pgSelect();
		String SQL = "SELECT get_res_fee(" + seq + ", NULLIF('" + model_id1 + "', ''), NULLIF('" + model_id2
				+ "', ''), '" + unit_id + "')";

		/*
		 * if(model_id2.equals("")){ SQL = "SELECT case when "
		 * +seq+" = 1 then res_fee1 else res_fee2 end from mf_product_model where model_id = '"
		 * +model_id1+"'"; db.select(SQL);
		 * 
		 * if(db.isNotNull()){ res_fee = (BigDecimal) db.getResult()[0][0]; } }else{
		 * if(seq == 1){ SQL =
		 * "SELECT (select res_fee1 from mf_product_model where model_id = '"
		 * +model_id1+"') + (select res_fee1 from mf_product_model where model_id = '"
		 * +model_id2+"');"; }else{ SQL =
		 * "SELECT (select res_fee2 from mf_product_model where model_id = '"
		 * +model_id2+"') + (select res_fee2 from mf_product_model where model_id = '"
		 * +model_id2+"');"; } db.select(SQL);
		 * 
		 * FncSystem.out("SQL For Res Fee", SQL); if(db.isNotNull()){ res_fee =
		 * (BigDecimal) db.getResult()[0][0]; } }
		 */

		db.select(SQL);
		FncSystem.out("Display Res Fee", SQL);
		if (db.isNotNull()) {
			res_fee = (BigDecimal) db.getResult()[0][0];
		}

		return res_fee;
	}

	/*
	 * private BigDecimal getDPEquity(String pmt_scheme_id) { BigDecimal
	 * selling_price = (BigDecimal) txtGSP.getValued();
	 * 
	 * pgSelect db = new pgSelect(); db.select("select ("+ selling_price
	 * +" * (rate / 100)) as dp_equity\n" + "from mf_pay_scheme_detail\n" +
	 * "where pmt_scheme_id = '"+ pmt_scheme_id +"'\n" + "and part_id = '013'\n" +
	 * "order by part_sequence;"); if(db.isNotNull()){ return (BigDecimal)
	 * db.getResult()[0][0]; }else{ return FncBigDecimal.zeroValue(); } }
	 */

	/*
	 * private BigDecimal getAvailedAmount(String pmt_scheme_id) { BigDecimal
	 * selling_price = (BigDecimal) txtGSP.getValued();
	 * 
	 * pgSelect db = new pgSelect(); db.select("select ("+ selling_price
	 * +" * (rate / 100)) as dp_equity\n" + "from mf_pay_scheme_detail\n" +
	 * "where pmt_scheme_id = '"+ pmt_scheme_id +"'\n" + "and part_id = '014'\n" +
	 * "order by part_sequence;"); if(db.isNotNull()){ return (BigDecimal)
	 * db.getResult()[0][0]; }else{ return FncBigDecimal.zeroValue(); } }
	 */

	public Boolean checkDocuments(String entity_id) {
		String proj_id = lookupProject.getValue();
		String unit_id = lookupUnit.getValue();
		String seq_no = txtSequence.getText();
		String buyer_type_id = lookupBuyerType.getValue();

		pgSelect db = new pgSelect();
		db.select(
				"select false as select, 1 as copies, trim(doc_id) as doc_id, trim(doc_desc) as doc_desc, trim(doc_alias) as doc_alias,\n"
						+ "doc_id::int in ('81', '42', '47', '79', '181', '122', '43', '182', '19', '18', '51', '120', '40', '28') as required, null as details, null as additional_info\n"
						+ "from mf_doc_details\n" + "where modules = 'DM'\n"
						+ "and doc_id::int in ('81', '42', '47', '79', '181', '122', '43', '182', '19', '18', '51', '120', '40', '28')\n"
						+ "and doc_id::int not in (select doc_id::int from dm_docs_inout where entity_id = '"
						+ entity_id + "' and proj_id = '" + proj_id + "' and pbl_id = getinteger('" + unit_id
						+ "')::text and seq_no = " + seq_no + ")\n"
						+ "and client_class @> (select array[type_group_id]::varchar[] from mf_buyer_type where type_id = '"
						+ buyer_type_id + "')\n" + "order by doc_desc;");
		return db.isNotNull();
	}

	public Boolean saveTR(String entity_id, String entity_name) {// XXX Save
		pgSelect dbSelect = new pgSelect();
		dbSelect.select(
				"SELECT sp_compute_payment_term('" + entity_id + "', '" + lookupBuyerType.getValue() + "')::int * 12;");

		if (dbSelect.isNotNull() && dbSelect.getResult()[0][0] != null) {
			Integer term = ((Integer) dbSelect.getResult()[0][0]);
			if (getMATerm() <= term) {

				/*
				 * dbSelect.select("SELECT is_tr_docs_complete('"+ entity_id +"', '"+
				 * lookupProject.getValue() +"', getinteger('"+ lookupUnit.getValue()
				 * +"')::VARCHAR, "+ txtSequence.getText() +", '"+ lookupBuyerType.getValue()
				 * +"');"); if((Boolean) dbSelect.getResult()[0][0]){
				 * JOptionPane.showMessageDialog(this.getTopLevelAncestor(),
				 * "Please submit all required documents first before tagging.",
				 * "Temporary Reservation", JOptionPane.INFORMATION_MESSAGE);
				 * displayDocumentsMonitoring();
				 * 
				 * return false; }else{
				 */

				String branch_id = UserInfo.Branch;
				String proj_id = lookupProject.getValue();
				String unit_id = lookupUnit.getValue();
				String seq_no = txtSequence.getText();
				BigDecimal selling_price = (BigDecimal) txtGSP.getValued();
				String selling_agent = lookupSalesAgentID.getValue();
				String income_cluster = lookupCluster.getValue();
				String type_id = lookupBuyerType.getValue();
				String pmt_scheme_id = lookupPmtScheme.getValue();
				String model_id = txtModelID.getText();
				String createdby = UserInfo.EmployeeCode;

				BigDecimal dpRate = getDPRate();
				Integer dpTerm = getDPTerm();
				BigDecimal dp_amount = (BigDecimal) txtDPEquity.getValued();
				BigDecimal maRate = getMARate();
				Integer maTerm = getMATerm();
				Boolean checkVal = cboxChangeLA.isSelected();
				BigDecimal loan_availed = (BigDecimal) txtAvailedAmount.getValued();
				BigDecimal disc_rate = (BigDecimal) txtDiscRate.getValued();
				BigDecimal disc_amount = (BigDecimal) txtDiscAmount.getValued();

				if (dpTerm <= 0) {
					JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "DP Term must be greater than 0.", "Save",
							JOptionPane.WARNING_MESSAGE);
					return false;
				}

				if (maTerm % 12 != 0) {
					JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "MA Term must be divisible by 12.",
							"Save", JOptionPane.WARNING_MESSAGE);
					return false;
				}

				if (JOptionPane.showConfirmDialog(this.getTopLevelAncestor(), "Are all entries correct?", "Save",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					// String tr_type = ((components.JTBorderFactory.TitleBorder)
					// pnlNorth.getBorder()).getTitle().split("[\\(\\)]")[1];

					String client_seqno = ((JTBorderFactory.TitleBorder) pnlNorth.getBorder()).getTitle();
					try {
						client_seqno = client_seqno.split(":")[1].replaceAll("\\[|\\]", "").trim();
						// System.out.printf("Client Seq #: %s%n",
						// client_seqno.split(":")[1].replaceAll("\\[|\\]", "").trim());
					} catch (IndexOutOfBoundsException e) {
						client_seqno = null;
					}

					// System.out.printf("Display client seq no: %", client_seqno);

					if (client_seqno == null || client_seqno.trim().equals("")) {

						System.out.printf("Display if with Other Unit: %s%n", withOtherUnit);

						String SQL = "";
						// OLD SAVING WITHOUT INCOME CLUSTER
						/*
						 * if(withOtherUnit){ SQL = "SELECT sp_tag_tr_with_other_unit('"+ branch_id
						 * +"', '"+ entity_id +"', '"+ proj_id +"', '"+ unit_id +"', "+
						 * (seq_no.equals("") ? "null":seq_no) +",\n" + "   "+ selling_price +", '"+
						 * selling_agent +"', '"+ type_id +"', '"+ pmt_scheme_id +"', '"+ model_id
						 * +"', "+ dpRate +", \n" + "   "+ dpTerm +", "+ dp_amount +", "+ maRate +", "+
						 * maTerm +", "+ loan_availed +", "+ disc_rate +", "+ disc_amount +",\n" +
						 * "   "+withOtherUnit+", '"+other_unit+"', '"+ createdby +"');";
						 * FncSystem.out("Reservation", SQL); }else{ SQL = "SELECT sp_tag_tr('"+
						 * branch_id +"', '"+ entity_id +"', '"+ proj_id +"', '"+ unit_id +"', "+
						 * (seq_no.equals("") ? "null":seq_no) +",\n" + "   "+ selling_price +", '"+
						 * selling_agent +"', '"+ type_id +"', '"+ pmt_scheme_id +"', '"+ model_id
						 * +"', "+ dpRate +", \n" + "   "+ dpTerm +", "+ dp_amount +", "+ maRate +", "+
						 * maTerm +", "+ loan_availed +", "+ disc_rate +", "+ disc_amount +",\n" +
						 * "   '"+ createdby +"');"; FncSystem.out("Reservation", SQL);
						 * 
						 * }
						 */

						if (withOtherUnit) {
							SQL = "SELECT sp_tag_tr_with_other_unit_v2('" + branch_id + "', '" + entity_id + "', '"
									+ proj_id + "', '" + unit_id + "', " + (seq_no.equals("") ? "null" : seq_no) + ",\n"
									+ "   " + selling_price + ", '" + selling_agent + "', '" + type_id + "', '"
									+ pmt_scheme_id + "', '" + model_id + "', " + dpRate + ", \n" + "   " + dpTerm
									+ ", " + dp_amount + ", " + maRate + ", " + maTerm + ", " + loan_availed + ", "
									+ disc_rate + ", " + disc_amount + ",\n" + "   " + withOtherUnit + ", '"
									+ other_unit + "', '" + income_cluster + "' ,'" + createdby + "');";
							FncSystem.out("Reservation", SQL);
						} else {
							SQL = "SELECT sp_tag_tr_v2('" + branch_id + "', '" + entity_id + "', '" + proj_id + "', '"
									+ unit_id + "', " + (seq_no.equals("") ? "null" : seq_no) + ",\n" + "   "
									+ selling_price + ", '" + selling_agent + "', '" + type_id + "', '" + pmt_scheme_id
									+ "', '" + model_id + "', " + dpRate + ", \n" + "   " + dpTerm + ", " + dp_amount
									+ ", " + maRate + ", " + maTerm + ", " + checkVal + "," + loan_availed + ", "
									+ disc_rate + ", " + disc_amount + ",\n" + "   '" + income_cluster + "','"
									+ createdby + "');";
							FncSystem.out("Reservation", SQL);

						}

						dbSelect.select(SQL);

						if (dbSelect.isNotNull() && (Boolean) dbSelect.getResult()[0][0]) {
							// Return Successfully
							JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Client has been reserved.",
									"Save", JOptionPane.INFORMATION_MESSAGE);

							// clearFields();
							setComponentsEditable(pnlNorth, false);
							setComponentsEditable(pnlCenterNorth, false);
							tblPaymentDetails.setEditable(false);

							return (Boolean) dbSelect.getResult()[0][0];
						} else {
							return false;
						}
					} else {
						String SQL = "SELECT sp_update_tagged_tr('" + client_seqno + "','" + branch_id + "', '"
								+ entity_id + "', '" + proj_id + "', '" + unit_id + "', "
								+ (seq_no.equals("") ? "null" : seq_no) + ",\n" + "   " + selling_price + ", '"
								+ selling_agent + "', '" + type_id + "', '" + pmt_scheme_id + "', '" + model_id + "', "
								+ dpRate + ", \n" + "   " + dpTerm + ", " + dp_amount + ", " + maRate + ", " + maTerm
								+ ", " + loan_availed + ", " + disc_rate + ", " + disc_amount + ",\n" + "   '"
								+ createdby + "');";
						FncSystem.out("Reservation", SQL);
						;
						dbSelect.select(SQL);

						if (dbSelect.isNotNull() && (Boolean) dbSelect.getResult()[0][0]) {
							// Return Successfully
							JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Details has been updated.",
									"Save", JOptionPane.INFORMATION_MESSAGE);

							// clearFields();
							setComponentsEditable(pnlNorth, false);
							setComponentsEditable(pnlCenterNorth, false);
							tblPaymentDetails.setEditable(false);

							return (Boolean) dbSelect.getResult()[0][0];
						} else {
							return false;
						}
					}
				} else {
					return false;
				}
				// }
			} else {
				JOptionPane.showMessageDialog(this.getTopLevelAncestor(), String.format(
						"Monthly Amortization Term is not suitable based on the age of client. (Supposed to be %s months)",
						term), "Save", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		} else {
			return false;
		}
	}

	public BigDecimal getDPRate() {
		for (int x = 0; x < modelPaymentDetails.getRowCount(); x++) {
			String part_id = (String) modelPaymentDetails.getValueAt(x, 1);
			if (part_id.equals("013")) {
				if (modelPaymentDetails.getValueAt(x, 3) instanceof Long) {
					return new BigDecimal((Long) modelPaymentDetails.getValueAt(x, 3));
				}
				if (modelPaymentDetails.getValueAt(x, 3) instanceof BigDecimal) {
					return (BigDecimal) modelPaymentDetails.getValueAt(x, 3);
				}
			}
		}
		return new BigDecimal("0");
	}

	public Integer getDPTerm() {
		for (int x = 0; x < modelPaymentDetails.getRowCount(); x++) {
			String part_id = (String) modelPaymentDetails.getValueAt(x, 1);
			if (part_id.equals("013")) {
				return (Integer) modelPaymentDetails.getValueAt(x, 5);
			}
		}
		return 0;
	}

	public BigDecimal getMARate() {
		for (int x = 0; x < modelPaymentDetails.getRowCount(); x++) {
			String part_id = (String) modelPaymentDetails.getValueAt(x, 1);
			if (part_id.equals("014")) {
				if (modelPaymentDetails.getValueAt(x, 3) instanceof Long) {
					return new BigDecimal((Long) modelPaymentDetails.getValueAt(x, 3));
				}
				if (modelPaymentDetails.getValueAt(x, 3) instanceof BigDecimal) {
					return (BigDecimal) modelPaymentDetails.getValueAt(x, 3);
				}
			}
		}
		return new BigDecimal("0");
	}

	public Integer getMATerm() {
		for (int x = 0; x < modelPaymentDetails.getRowCount(); x++) {
			String part_id = (String) modelPaymentDetails.getValueAt(x, 1);
			if (part_id.equals("014")) {
				return (Integer) modelPaymentDetails.getValueAt(x, 5);
			}
		}
		return 0;
	}

	/**
	 * Added 2014-11-06 by Alvin Gonzales
	 *
	 */
	public void setComponentsEnabled(JPanel panel, boolean enable) {
		panelLooper(panel, enable);
	}

	public void panelLooper(Container panel, boolean enable) {
		for (Component comp : panel.getComponents()) {
			if (comp instanceof JPanel) {
				panelLooper((JPanel) comp, enable);
			} else if (comp instanceof JScrollPane) {
				panelLooper((JScrollPane) comp, enable);
			} else {
				comp.setEnabled(enable);
			}
		}
	}

	/**
	 * Added 2014-11-07 by Alvin Gonzales
	 *
	 */
	public void setComponentsEditable(JPanel panel, boolean editable) {
		panelLooperEditable(panel, editable);
	}

	public void panelLooperEditable(Container panel, boolean editable) {
		for (Component comp : panel.getComponents()) {
			if (comp instanceof JPanel) {
				panelLooperEditable((JPanel) comp, editable);
			} else if (comp instanceof JScrollPane) {
				panelLooperEditable((JScrollPane) comp, editable);
			} else {
				if (comp instanceof JTextField) {
					((JTextField) comp).setEditable(editable);
				}
			}
		}
	}

	public void edit() {
		setComponentsEnabled(this, true);
		/*
		 * lookupProject.setEditable(true); lookupUnit.setEditable(true);
		 */
		lookupSalesAgentID.setEditable(true);
		lookupBuyerType.setEditable(true);
		lookupPmtScheme.setEditable(true);
		tblPaymentDetails.setEditable(true);
		txtAvailedAmount.setEditable(true);
	}

	public Boolean view(String entity_id) {
		this.entity_id = entity_id;

		_JLookupTable dlg = new _JLookupTable(FncGlobal.homeMDI, null, "Transactions",
				_HoldingAndReservation.getSQLReservationUnits(entity_id), false);
		dlg.setFilterClientName(true);
		dlg.setSize(new Dimension(800, 420));
		dlg.setLocationRelativeTo(FncGlobal.homeMDI);
		dlg.setVisible(true);

		Object[] data = dlg.getReturnDataSet();
		if (data != null) {
			clearFields();

			String client_seqno = (String) data[0];
			String status_id = (String) data[1];

			lookupProject.setValue((String) data[6]);
			txtProjectName.setText((String) data[7]);
			lookupUnit.setValue((String) data[3]);
			txtUnit.setText((String) data[2]);
			txtModelID.setText((String) data[4]);
			txtModelName.setText((String) data[5]);
			txtGSP.setValue((BigDecimal) data[8]);
			txtNSP.setValue((BigDecimal) data[9]);

			lookupSalesAgentID.setValue((String) data[10]);
			txtSalesAgentName.setText((String) data[11]);
			lookupBuyerType.setValue((String) data[12]);
			txtBuyerType.setText((String) data[13]);
			lookupPmtScheme.setValue((String) data[14]);
			txtPmtScheme.setText((String) data[15]);
			txtDiscRate.setValue((BigDecimal) data[16]);
			txtDiscAmount.setValue((BigDecimal) data[17]);
			txtDPEquity.setValue((BigDecimal) data[18]);
			txtAvailedAmount.setValue((BigDecimal) data[19]);
			sub_proj_id = (String) data[20];

			setComponentsEnabled(this, true);
			setComponentsEditable(this, false);

			// EDIT THIS METHOD TO VIEW NEW DETAILS WHEN SEQUENCE NUMBER IS EDITED
			displayPaymentSchemeDetails((String) data[14], false, lookupUnit.getValue());

			pnlNorth.setBorder(components.JTBorderFactory
					.createTitleBorder(String.format("Details [ Client Seq. #: %s ]", client_seqno)));

			tblPaymentDetails.setEditable(false);

			System.out.printf("%s (%s)%n", status_id, !status_id.equals("P"));

			System.out.printf("Display client seq no: %s%n",
					((JTBorderFactory.TitleBorder) pnlNorth.getBorder()).getTitle().split(":")[1]
							.replaceAll("\\[|\\]", "").trim());

			return !status_id.equals("P");
		} else {
			return false;
		}
	}

	private void tagAnotherUnit(String proj_id, String unit_id) {
		if (JOptionPane.showConfirmDialog(this, "Tag Another Unit?", "Unit", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

			System.out.printf("Display value of Proj. ID: %s", proj_id);

			dlg_OtherUnit dlgOther = new dlg_OtherUnit(proj_id, unit_id, this);
			dlgOther.setLocationRelativeTo(this);
			dlgOther.setVisible(true);

		}
	}

	private void getUnitDesc(String proj_id, String unit_id1, String unit_id2) {
		String unit_desc = "";

		pgSelect db = new pgSelect();
		String SQL = "";

		if (unit_id2.equals("")) {
			SQL = "SELECT description FROM mf_unit_info where proj_id = '" + proj_id + "' AND unit_id = '" + unit_id1
					+ "'";
		} else {
			SQL = "SELECT FORMAT('%s-%s', get_unit_description('" + unit_id1
					+ "'), lot) FROM mf_unit_info WHERE proj_id = '" + proj_id + "' AND unit_id = '" + unit_id2 + "'";
		}

		db.select(SQL);

		if (db.isNotNull()) {
			unit_desc = (String) db.getResult()[0][0];
		}

		txtUnit.setText(unit_desc);
	}

	public void computeCombinedUnit(String unit_id2, String model_id2, String model_desc2) {
		String proj_id = lookupProject.getValue();
		String unit_id1 = lookupUnit.getValue();
		String model_desc = txtModelName.getText();

		withOtherUnit = true;

		pgSelect db = new pgSelect();
		String SQL = "SELECT * FROM view_combined_pricelist('" + proj_id + "', '" + unit_id1 + "', '" + unit_id2 + "')";
		db.select(SQL);

		FncSystem.out("Display SQL for Combined Pricelist", SQL);

		if (db.isNotNull()) {
			BigDecimal nsp = (BigDecimal) db.getResult()[0][16];

			txtGSP.setValue(nsp);
			txtNSP.setValue(nsp);
		}

		String SQL2 = "SELECT model_id FROM mf_unit_info WHERE proj_id = '" + proj_id + "' and unit_id = '" + unit_id2
				+ "'";
		db.select(SQL2);

		getUnitDesc(proj_id, unit_id1, unit_id2);
		txtModelName.setText(String.format("%s / %s", model_desc, model_desc2));
		other_model_id = model_id2;
		other_unit = unit_id2;

	}

	private void computeEquity() {

//		
//		if (lookupUnit.getValue().equals("TV0006494") {
//			txtDPEquity.setValue(354461.60);		
//		}
//		else {
//		Double nsp = Double.valueOf(txtNSP.getText().trim().replace(",", "")).doubleValue();
//		Double availed_amt = Double.valueOf(txtAvailedAmount.getText().trim().replace(",", "")).doubleValue();
//
//		Double dp_equity = nsp - availed_amt;
//		txtDPEquity.setValue(BigDecimal.valueOf(dp_equity));
//		}
		
		Double nsp = Double.valueOf(txtNSP.getText().trim().replace(",", "")).doubleValue();
		Double availed_amt = Double.valueOf(txtAvailedAmount.getText().trim().replace(",", "")).doubleValue();

		Double dp_equity = nsp - availed_amt;
		txtDPEquity.setValue(BigDecimal.valueOf(dp_equity));
		
	}

	private boolean isClientOFW(String entity_id) {
		pgSelect db = new pgSelect();
		String SQL = "select * from rf_corp_type where entity_id = '" + entity_id
				+ "' and business_class_id = '03' and status_id = 'A'; \n";
		;
		db.select(SQL);

		if (db.isNotNull()) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isEmployee(String entity_id) {
		pgSelect db = new pgSelect();
		String SQL = "SELECT * FROM em_employee WHERE entity_id = '" + entity_id + "'";
		db.select(SQL);

		if (db.isNotNull()) {
			return true;
		} else {
			return false;
		}
	}

	// Commented by Alvin Gonzales due to unused (2015-05-05)
	/*
	 * public void displayPopups(String entity_id) { String proj_id =
	 * lookupProject.getValue(); String unit_id = lookupUnit.getValue(); String
	 * seq_no = txtSequence.getText();
	 * 
	 * new dlgRequiredDocuments((Frame) this.getTopLevelAncestor(), entity_id,
	 * proj_id, unit_id, seq_no).showDialog();
	 * 
	 * //JOptionPane.showOptionDialog(this.getTopLevelAncestor(), new
	 * pnlRequiredDocuments(new BorderLayout()), "Required Documents",
	 * JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, 0); }
	 */

	/**
	 * 
	 * @author Alvin Gonzales
	 *
	 */
	// Commented by Alvin Gonzales due to unused (2015-05-05)
	/*
	 * public class dlgRequiredDocuments extends JDialog implements ActionListener {
	 * 
	 * private static final long serialVersionUID = 7764725698247226411L;
	 * 
	 * Dimension size = new Dimension(500, 400);
	 * 
	 * JPanel pnlCenter; JScrollPane scrollAllDocuments; _JTableMain
	 * tblAllDocuments; modelDM_AllDocuments modelAllDocuments; JList
	 * rowHeaderAllDocuments;
	 * 
	 * JPanel pnlFinding; JScrollPane scrollFindings; JXTextArea txtFindings;
	 * 
	 * JPanel pnlSouth; JPanel pnlButtons; JButton btnSave; JButton btnCancel;
	 * 
	 * ArrayList<Object[]> listData; ArrayList<String> listSelected = new
	 * ArrayList<String>();
	 * 
	 * String entity_id; String proj_id; String unit_id; String seq_no;
	 * 
	 * public dlgRequiredDocuments(Frame owner, String entity_id, String proj_id,
	 * String unit_id, String seq_no) { super(owner);
	 * 
	 * this.entity_id = entity_id; this.proj_id = proj_id; this.unit_id = unit_id;
	 * this.seq_no = seq_no;
	 * 
	 * initGUI(); }
	 * 
	 * private void initGUI() { this.setTitle("Required Documents");
	 * this.setLayout(new BorderLayout()); this.setPreferredSize(size);
	 * this.setSize(size); this.setModal(true);
	 * this.setModalityType(ModalityType.APPLICATION_MODAL);
	 * this.getContentPane().setLayout(new BorderLayout());
	 * this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); { pnlCenter
	 * = new JPanel(new BorderLayout(5, 5)); add(pnlCenter, BorderLayout.CENTER);
	 * pnlCenter.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); {
	 * scrollAllDocuments = new JScrollPane(); pnlCenter.add(scrollAllDocuments,
	 * BorderLayout.CENTER);
	 * scrollAllDocuments.setHorizontalScrollBarPolicy(JScrollPane.
	 * HORIZONTAL_SCROLLBAR_ALWAYS); { modelAllDocuments = new
	 * modelDM_AllDocuments(); modelAllDocuments.addTableModelListener(new
	 * TableModelListener() { public void tableChanged(TableModelEvent e) {
	 * //Addition of rows if(e.getType() == 1){
	 * ((DefaultListModel)rowHeaderAllDocuments.getModel()).addElement(
	 * modelAllDocuments.getRowCount());
	 * 
	 * if(modelAllDocuments.getRowCount() == 0){ rowHeaderAllDocuments.setModel(new
	 * DefaultListModel()); } } } });
	 * 
	 * tblAllDocuments = new _JTableMain(modelAllDocuments);
	 * scrollAllDocuments.setViewportView(tblAllDocuments);
	 * tblAllDocuments.hideColumns("ID");
	 * 
	 * tblAllDocuments.addPropertyChangeListener(new PropertyChangeListener() {//XXX
	 * Work Items public void propertyChange(PropertyChangeEvent arg0) { _JTableMain
	 * table = (_JTableMain) arg0.getSource(); String propertyName =
	 * arg0.getPropertyName();
	 * 
	 * if (propertyName.equals("tableCellEditor")) { int column =
	 * table.convertColumnIndexToModel(table.getEditingColumn()); int row =
	 * table.getEditingRow();
	 * 
	 * //setting of selected documents when selecting if (column != -1 &&
	 * modelAllDocuments.getColumnClass(column).equals(Boolean.class)) { Object
	 * oldValue = null; try { oldValue = ((BooleanEditor)
	 * arg0.getOldValue()).getCellEditorValue(); } catch (NullPointerException e) {
	 * }
	 * 
	 * if (oldValue != null) { String docID = (String)
	 * modelAllDocuments.getValueAt(row, 2); listSelected.add(docID); } }
	 * 
	 * //setting of number of copies when editing if (column != -1 &&
	 * modelAllDocuments.getColumnClass(column).equals(Integer.class)) { Object
	 * oldValue = null; try { oldValue = ((NumberEditorExt)
	 * arg0.getOldValue()).getCellEditorValue(); if (oldValue != null) { String
	 * docID = (String) modelAllDocuments.getValueAt(row, 2); for(int x=0; x<
	 * listData.size(); x++){ if(listData.get(x)[2].equals(docID)){
	 * listData.get(x)[1] = oldValue; } } } } catch (NullPointerException e) { } } }
	 * } }); } { rowHeaderAllDocuments = tblAllDocuments.getRowHeader();
	 * rowHeaderAllDocuments.setModel(new DefaultListModel());
	 * scrollAllDocuments.setRowHeaderView(rowHeaderAllDocuments);
	 * scrollAllDocuments.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER,
	 * FncTables.getRowHeader_Header());
	 * scrollAllDocuments.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER,
	 * FncTables.getRowHeader_Footer(Integer.toString(tblAllDocuments.getRowCount())
	 * )); } } { pnlFinding = new JPanel(new BorderLayout());
	 * pnlCenter.add(pnlFinding, BorderLayout.SOUTH);
	 * pnlFinding.setBorder(components.JTBorderFactory.createTitleBorder("Findings")
	 * ); pnlFinding.setPreferredSize(new Dimension(0, 100)); { scrollFindings = new
	 * JScrollPane(); pnlFinding.add(scrollFindings, BorderLayout.CENTER); {
	 * txtFindings = new JXTextArea("Please enter findings here.");
	 * scrollFindings.setViewportView(txtFindings); txtFindings.setLineWrap(true);
	 * txtFindings.setWrapStyleWord(true); } } } } { pnlSouth = new JPanel();
	 * add(pnlSouth, BorderLayout.SOUTH);
	 * pnlSouth.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
	 * pnlSouth.setLayout(new OverlayLayout(pnlSouth));
	 * pnlSouth.setPreferredSize(new Dimension(788, 35)); { pnlButtons = new
	 * JPanel(new GridLayout(1, 2, 5, 5)); pnlSouth.add(pnlButtons,
	 * BorderLayout.EAST); pnlButtons.setAlignmentX(0.5f);
	 * pnlButtons.setAlignmentY(0.5f); pnlButtons.setMaximumSize(new Dimension(155,
	 * 40)); { btnSave = new JButton("Save"); pnlButtons.add(btnSave);
	 * btnSave.setActionCommand("Save"); btnSave.addActionListener(this); } {
	 * btnCancel = new JButton("Cancel"); pnlButtons.add(btnCancel);
	 * btnCancel.setActionCommand("Cancel"); btnCancel.addActionListener(this); } }
	 * }
	 * 
	 * displayAllDocuments(); }
	 * 
	 * public void showDialog() { setLocationRelativeTo(null); setVisible(true); }
	 * 
	 * private void displayAllDocuments() { listData =
	 * _DocumentsMonitoring.displayRequiredDocuments(modelAllDocuments, "09",
	 * entity_id, proj_id, unit_id, seq_no);
	 * scrollAllDocuments.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER,
	 * FncTables.getRowHeader_Footer(Integer.toString(tblAllDocuments.getRowCount())
	 * )); tblAllDocuments.packAll(); }
	 * 
	 * private Boolean hasSelected() { ArrayList<Boolean> listSelected = new
	 * ArrayList<Boolean>(); for(int x=0; x < modelAllDocuments.getRowCount(); x++){
	 * listSelected.add((Boolean) modelAllDocuments.getValueAt(x, 0)); } return
	 * listSelected.contains(true); }
	 * 
	 * private Boolean saveDocuments() { ArrayList<Integer> listCopies = new
	 * ArrayList<Integer>(); ArrayList<String> listDocuments = new
	 * ArrayList<String>();
	 * 
	 * for(int x=0; x < modelAllDocuments.getRowCount(); x++){ Boolean isSelected =
	 * (Boolean) modelAllDocuments.getValueAt(x, 0); Integer copies = (Integer)
	 * modelAllDocuments.getValueAt(x, 1); String doc_id = (String)
	 * modelAllDocuments.getValueAt(x, 2); if(isSelected){ listCopies.add(copies);
	 * listDocuments.add(String.format("'%s'", doc_id)); } }
	 * 
	 * String documents = listDocuments.toString().replaceAll("\\[|\\]", ""); String
	 * copies = listCopies.toString().replaceAll("\\[|\\]", ""); String user =
	 * UserInfo.EmployeeCode;
	 * 
	 * pgSelect db = new pgSelect(); db.select("SELECT sp_save_documents('"+
	 * entity_id +"', '"+ proj_id +"', getinteger('"+ unit_id +"')::text, "+ seq_no
	 * +", '"+ unit_id +"', ARRAY["+ documents +"]::VARCHAR[], ARRAY["+ copies
	 * +"]::INT[], '"+ user +"');");
	 * 
	 * return (Boolean) db.getResult()[0][0]; }
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {//XXX ACTION String
	 * action = e.getActionCommand();
	 * 
	 * if(action.equals("Save")){ if(hasSelected()){ if
	 * (JOptionPane.showConfirmDialog(getOwner(),
	 * "Are you sure you want to delete selected contract?", "Save",
	 * JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) ==
	 * JOptionPane.YES_OPTION) { if(saveDocuments()){
	 * JOptionPane.showMessageDialog(getOwner(), "Documents has been tagged.",
	 * action, JOptionPane.INFORMATION_MESSAGE); dispose(); } } }else{
	 * JOptionPane.showMessageDialog(getOwner(),
	 * "Please select document(s) to tag.", action, JOptionPane.WARNING_MESSAGE);
	 * this.requestFocus(); } } if(action.equals("Cancel")){ dispose(); } } }
	 */

}
