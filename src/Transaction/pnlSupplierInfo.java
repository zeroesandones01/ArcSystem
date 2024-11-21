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
	private _JXTextField txtPosition;
	private _JXTextField txtAuthorizedPerson;
	private _JXTextField txtCorpAlias;
	private _JXTextField txtCorpName;
	private _JXTextField txtTIN_No;
	private JCheckBox chkCorpVAT;
	private JComboBox cmbCorpBusinessNature;
	private JComboBox cmbCorpBusinessClass;
	private JComboBox cmbEntityKind;

	private JPanel pnlCorpContact;
	private JPanel pnlCorpContactLabel;
	private JLabel lblCorpBusinessTelNo;
	private JLabel lblCorpMobileNo;
	private JLabel lblCorpFaxNo;
	private JLabel lblCorpEmail;
	private JLabel lblCorpFacebookAcct;

	private JPanel pnlCorpContactComponents;
	private _JXTextField txtTelNo;
	private _JXTextField txtCorpMobileNo;
	private _JXTextField txtCorpFaxNo;
	private _JXTextField txtEmail;
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
				JPanel pnlCorpInfoLeft = new JPanel(new BorderLayout(3, 3));
				pnlCorpMainInfo.add(pnlCorpInfoLeft);
				{
					pnlCorpInfoLabel = new JPanel(new GridLayout(7, 1, 5, 5));
					pnlCorpInfoLeft.add(pnlCorpInfoLabel, BorderLayout.WEST);
					{
						lblCorporationName = new JLabel("*Registered Name");
						pnlCorpInfoLabel.add(lblCorporationName);
					}
					{
						JLabel lblCorpAlias = new JLabel("Trade Name");
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
						txtCorpName = new _JXTextField("Registered Name");
						pnlCorpInfoComponents.add(txtCorpName);
					}
					{
						JPanel pnlCorpAlias = new JPanel(new BorderLayout(3, 3));
						pnlCorpInfoComponents.add(pnlCorpAlias);
						{
							txtCorpAlias = new _JXTextField("Trade Name");
							pnlCorpAlias.add(txtCorpAlias, BorderLayout.WEST);
							txtCorpAlias.setPreferredSize(new Dimension(225, 0));
						}
						{
							JPanel pnlEntityKind = new JPanel(new BorderLayout(3, 3));
							pnlCorpAlias.add(pnlEntityKind, BorderLayout.CENTER);
							{
								JLabel lblEntityKind = new JLabel("Entity Kind", JLabel.TRAILING);
								pnlEntityKind.add(lblEntityKind);
							}
							{
								cmbEntityKind = new JComboBox(new String[] { "Corporation", "Individual" });
								pnlEntityKind.add(cmbEntityKind, BorderLayout.EAST);
								cmbEntityKind.setEnabled(false);
								cmbEntityKind.setSelectedItem(null);
								cmbEntityKind.setPreferredSize(new Dimension(200, 0));
							}
						}
					}

					{
						txtAuthorizedPerson = new _JXTextField("Authorized Person");
						pnlCorpInfoComponents.add(txtAuthorizedPerson);
					}
					{
						txtPosition = new _JXTextField("Position");
						pnlCorpInfoComponents.add(txtPosition);
					}
					{
						JPanel pnlCorpTIN = new JPanel(new BorderLayout(3, 3));
						pnlCorpInfoComponents.add(pnlCorpTIN);
						{
							txtTIN_No = new _JXTextField("TIN");
							pnlCorpTIN.add(txtTIN_No, BorderLayout.WEST);
							txtTIN_No.setPreferredSize(new Dimension(150, 0));
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
						{
							JPanel pnlTelNo = new JPanel(new BorderLayout(3, 3));
							pnlCorpBusinessNature.add(pnlTelNo, BorderLayout.CENTER);
							{
								JLabel lblTelNo = new JLabel("Tel. No", JLabel.TRAILING);
								pnlTelNo.add(lblTelNo);
							}
							{
								txtTelNo = new _JXTextField("Telephone No");
								pnlTelNo.add(txtTelNo, BorderLayout.EAST);
								txtTelNo.setPreferredSize(new Dimension(150, 0));
							}
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

						{
							JPanel pnlEmail = new JPanel(new BorderLayout(3, 3));
							pnlCorpBusinessClass.add(pnlEmail, BorderLayout.CENTER);
							{
								JLabel lblEmail = new JLabel("Email", JLabel.TRAILING);
								pnlEmail.add(lblEmail);
							}
							{
								txtEmail = new _JXTextField("Email");
								pnlEmail.add(txtEmail, BorderLayout.EAST);
								txtEmail.setPreferredSize(new Dimension(150, 0));
							}
						}
					}
				}
			}
			//				
		}
		{
			pnlCorpTypes = new JPanel(new BorderLayout(3, 3));
			pnlCorporation.add(pnlCorpTypes);
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
	
	public void newSupplier(String entity_id) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
