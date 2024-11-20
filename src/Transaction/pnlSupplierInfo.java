package Transaction;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.jdesktop.swingx.JXPanel;

import Database.pgSelect;
import Functions.FncSystem;
import Functions.FncTables;
import components.JTBorderFactory;
import components._JTableMain;
import components._JXTextField;
import tablemodel.modelCI_EntityTypes;

public class pnlSupplierInfo extends JXPanel implements ActionListener {

	private static final long serialVersionUID = 4256723533785577913L;
	private JPanel pnlCorporation;
	private JPanel pnlCorpMainInfo;
	private JSplitPane splitCorpInfo;
	private JPanel pnlCorpInfoLeft;

	private JPanel pnlCorpInfoLabel;
	private JLabel lblCorpType;
	private JLabel lblCorporationName;
	private JLabel lblCorpContactPerson;
	private JLabel lblCorpPosition;
	private JLabel lblCorpTIN;
	private JLabel lblCorpBusinessNature;
	private JLabel lblCorpBusinessClass;

	private JPanel pnlCorpInfoComponents;
	private JPanel pnlCorpTypes;
	private JPanel pnlCorpInfoRight;
	private modelCI_EntityTypes modelEntityTypes;
	private JScrollPane scrollEntityTypes;
	private _JTableMain tblEntityTypes;
	private JList rowHeaderEntityTypes;

	private JComboBox cmbCorpType;
	private _JXTextField txtCorpType;
	private _JXTextField txtCorpPosition;
	private _JXTextField txtCorpContactPerson;
	private _JXTextField txtCorpAlias;
	private _JXTextField txtCorpName;
	private _JXTextField txtCorpTIN;
	private JCheckBox chkCorpVAT;
	private JComboBox cmbCorpBusinessNature;
	private JComboBox cmbCorpBusinessClass;

	private JPanel pnlCorpContact;
	private JPanel pnlCorpContactLabel;
	private JLabel lblCorpBusinessTelNo;
	private JLabel lblCorpMobileNo;
	private JLabel lblCorpFaxNo;
	private JLabel lblCorpEmail;
	private JLabel lblCorpFacebookAcct;

	private JPanel pnlCorpContactComponents;
	private _JXTextField txtCorpBusinessTelNo;
	private _JXTextField txtCorpMobileNo;
	private _JXTextField txtCorpFaxNo;
	private _JXTextField txtCorpEmail;
	private _JXTextField txtCorpFacebookAcct;
	private JButton btnAddCorpContactInfo;

	public pnlSupplierInfo() {
		initGUI();
	}

	public pnlSupplierInfo(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		initGUI();
	}

	public pnlSupplierInfo(LayoutManager layout) {
		super(layout);
		initGUI();
	}

	public pnlSupplierInfo(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout(3, 3));
		pnlCorporation = new JPanel(new GridLayout(2, 1, 3, 3));
		this.add(pnlCorporation, BorderLayout.CENTER);
		{
			pnlCorpMainInfo = new JPanel(new BorderLayout(3, 3));
			pnlCorporation.add(pnlCorpMainInfo, BorderLayout.NORTH);
			pnlCorpMainInfo.setBorder(JTBorderFactory.createTitleBorder("Main Info"));
			{
				splitCorpInfo = new JSplitPane();
				pnlCorpMainInfo.add(splitCorpInfo, BorderLayout.CENTER);
				splitCorpInfo.setOneTouchExpandable(true);
				splitCorpInfo.setResizeWeight(.3d);
				{
					JPanel pnlCorpInfoLeft = new JPanel(new BorderLayout(3, 3));
					splitCorpInfo.add(pnlCorpInfoLeft, JSplitPane.LEFT);
					{
						pnlCorpInfoLabel = new JPanel(new GridLayout(7, 1, 5, 5));
						pnlCorpInfoLeft.add(pnlCorpInfoLabel, BorderLayout.WEST);
						/*{
								lblCorpType = new JLabel("Corporation Type");
								pnlCorpInfoLabel.add(lblCorpType);
							}*/
						{
							lblCorporationName = new JLabel("*Corporation Name");
							pnlCorpInfoLabel.add(lblCorporationName);
						}
						{
							JLabel lblCorpAlias = new JLabel("Corp Alias");
							pnlCorpInfoLabel.add(lblCorpAlias);
						}
						{
							lblCorpContactPerson = new JLabel("Authorized Person");
							pnlCorpInfoLabel.add(lblCorpContactPerson);
						}
						{
							lblCorpPosition = new JLabel("Position");
							pnlCorpInfoLabel.add(lblCorpPosition);
						}
						{
							lblCorpTIN = new JLabel("TIN");
							pnlCorpInfoLabel.add(lblCorpTIN);
						}
						{
							lblCorpBusinessNature = new JLabel("Business Nature");
							pnlCorpInfoLabel.add(lblCorpBusinessNature);
						}
						{
							lblCorpBusinessClass = new JLabel("Business Class");
							pnlCorpInfoLabel.add(lblCorpBusinessClass);
						}
					}
					{
						pnlCorpInfoComponents = new JPanel(new GridLayout(7, 1, 5, 5));
						pnlCorpInfoLeft.add(pnlCorpInfoComponents, BorderLayout.CENTER);
						{
							txtCorpName = new _JXTextField();
							pnlCorpInfoComponents.add(txtCorpName);
						}
						{
							txtCorpAlias = new _JXTextField();
							pnlCorpInfoComponents.add(txtCorpAlias);
						}
						{
							txtCorpContactPerson = new _JXTextField();
							pnlCorpInfoComponents.add(txtCorpContactPerson);
						}
						{
							txtCorpPosition = new _JXTextField();
							pnlCorpInfoComponents.add(txtCorpPosition);
						}
						{
							JPanel pnlCorpTIN = new JPanel(new BorderLayout(3, 3));
							pnlCorpInfoComponents.add(pnlCorpTIN);
							{
								txtCorpTIN = new _JXTextField();
								pnlCorpTIN.add(txtCorpTIN, BorderLayout.WEST);
								txtCorpTIN.setPreferredSize(new Dimension(150, 0));
							}
							{
								chkCorpVAT = new JCheckBox("VAT Registered");
								pnlCorpTIN.add(chkCorpVAT, BorderLayout.EAST);
								chkCorpVAT.setEnabled(false);
							}
						}
						{
							JPanel pnlCorpBusinessNature = new JPanel(new BorderLayout(3, 3));
							pnlCorpInfoComponents.add(pnlCorpBusinessNature);
							{
								cmbCorpBusinessNature = new JComboBox(getBusinessNature());
								pnlCorpBusinessNature.add(cmbCorpBusinessNature, BorderLayout.WEST);
								cmbCorpBusinessNature.setPreferredSize(new Dimension(150, 0));
								cmbCorpBusinessNature.setEnabled(false);
							}
						}
						{
							JPanel pnlCorpBusinessClass = new JPanel(new BorderLayout(3, 3));
							pnlCorpInfoComponents.add(pnlCorpBusinessClass);
							{
								cmbCorpBusinessClass = new JComboBox(getBusinessClass());
								pnlCorpBusinessClass.add(cmbCorpBusinessClass, BorderLayout.WEST);
								cmbCorpBusinessClass.setPreferredSize(new Dimension(150, 0));
								cmbCorpBusinessClass.setEnabled(false);
							}
						}
					}
				}
				{
					pnlCorpTypes = new JPanel(new BorderLayout(3, 3));
					splitCorpInfo.add(pnlCorpTypes, JSplitPane.RIGHT);

					{
						scrollEntityTypes = new JScrollPane();
						pnlCorpTypes.add(scrollEntityTypes, BorderLayout.CENTER);
						scrollEntityTypes.setBorder(JTBorderFactory.createTitleBorder("Entity Types"));
						scrollEntityTypes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						{
							modelEntityTypes = new modelCI_EntityTypes();

							tblEntityTypes = new _JTableMain(modelEntityTypes);
							scrollEntityTypes.setViewportView(tblEntityTypes);
							tblEntityTypes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							tblEntityTypes.hideColumns("WTAX ID", "WTAX Description", "WTAX Rate");
							tblEntityTypes.setSortable(false);

							//Process after row add in tables
							modelEntityTypes.addTableModelListener(new TableModelListener() {
								public void tableChanged(TableModelEvent e) {

									((DefaultListModel)rowHeaderEntityTypes.getModel()).addElement(modelEntityTypes.getRowCount());

									if(modelEntityTypes.getRowCount() == 0){
										rowHeaderEntityTypes.setModel(new DefaultListModel());
									}
								}
							});
						}

						{
							rowHeaderEntityTypes = tblEntityTypes.getRowHeader();
							rowHeaderEntityTypes.setModel(new DefaultListModel());
							scrollEntityTypes.setRowHeaderView(rowHeaderEntityTypes);
							scrollEntityTypes.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
						}
					}
				}
			}
		}
		{
			pnlCorpContact = new JPanel(new BorderLayout(5, 5));
			pnlCorporation.add(pnlCorpContact, BorderLayout.SOUTH);
			pnlCorpContact.setBorder(JTBorderFactory.createTitleBorder("Contact Details"));
			{
				pnlCorpContactLabel = new JPanel(new GridLayout(6, 1, 5, 5));
				pnlCorpContact.add(pnlCorpContactLabel, BorderLayout.WEST);
				{
					lblCorpBusinessTelNo = new JLabel("Business Tel. No");
					pnlCorpContactLabel.add(lblCorpBusinessTelNo);
				}
				{
					lblCorpMobileNo = new JLabel("Cellphone No.");
					pnlCorpContactLabel.add(lblCorpMobileNo);
				}
				{
					lblCorpFaxNo = new JLabel("Fax No.");
					pnlCorpContactLabel.add(lblCorpFaxNo);
				}
				{
					lblCorpEmail = new JLabel("Email");
					pnlCorpContactLabel.add(lblCorpEmail);
				}
				{
					lblCorpFacebookAcct = new JLabel("Facebook Acct");
					pnlCorpContactLabel.add(lblCorpFacebookAcct);
				}
				{
					pnlCorpContactLabel.add(Box.createHorizontalBox());
				}
			}
			{
				pnlCorpContactComponents = new JPanel(new GridLayout(6, 1, 5, 5));
				pnlCorpContact.add(pnlCorpContactComponents, BorderLayout.CENTER);
				{
					txtCorpBusinessTelNo = new _JXTextField();
					pnlCorpContactComponents.add(txtCorpBusinessTelNo);
				}
				{
					txtCorpMobileNo = new _JXTextField();
					pnlCorpContactComponents.add(txtCorpMobileNo);
				}
				{
					txtCorpFaxNo = new _JXTextField();
					pnlCorpContactComponents.add(txtCorpFaxNo);
				}
				{
					txtCorpEmail = new _JXTextField();
					pnlCorpContactComponents.add(txtCorpEmail);
				}
				{
					txtCorpFacebookAcct = new _JXTextField();
					pnlCorpContactComponents.add(txtCorpFacebookAcct);
				}
				{
					JPanel pnlAddCorpContact = new JPanel(new BorderLayout(5, 5));
					pnlCorpContactComponents.add(pnlAddCorpContact);
					{
						btnAddCorpContactInfo = new JButton("Add Corp. Contact Info");
						pnlAddCorpContact.add(btnAddCorpContactInfo, BorderLayout.EAST);
						btnAddCorpContactInfo.setPreferredSize(new Dimension(250, 0));
						btnAddCorpContactInfo.setActionCommand("Add Corp Contact Info");
						btnAddCorpContactInfo.addActionListener(this);
						btnAddCorpContactInfo.setEnabled(false);
					}
				}
			}
		}
	}//XXX END OF INIT GUI

	private Object[] getBusinessNature() {//ARRAYLIST FOR THE BUSINESS NATURE
		ArrayList<Object> civilstatus = new ArrayList<Object>();

		pgSelect db = new pgSelect();
		String SQL = "select FORMAT('%s - %s', TRIM(initcap(nob_desc)), TRIM(nob_code)) from mf_nature_business where status_id = 'A' order by nob_desc";
		db.select(SQL);

		FncSystem.out("Display SQL FOr Business Nature", SQL);
		if(db.isNotNull()){
			for(int x=0; x < db.getRowCount(); x++){
				civilstatus.add(db.getResult()[x][0]);
			}
		}

		return civilstatus.toArray();
	}
	
	private Object[] getBusinessClass() {//ARRAYLIST FOR THE BUSINESS CLASS
		ArrayList<Object> civilstatus = new ArrayList<Object>();

		pgSelect db = new pgSelect();
		db.select("SELECT FORMAT('%s - %s', TRIM(class_name), TRIM(class_id)) from mf_business_class where status_id = 'A' ORDER BY TRIM(class_name)");
		if(db.isNotNull()){
			for(int x=0; x < db.getRowCount(); x++){
				civilstatus.add(db.getResult()[x][0]);
			}
		}
		return civilstatus.toArray();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
