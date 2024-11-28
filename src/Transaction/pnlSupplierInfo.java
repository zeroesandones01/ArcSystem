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
import javax.swing.JOptionPane;
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
import Functions.UserInfo;
import components.JTBorderFactory;
import components._JTableMain;
import components._JXTextField;
import tablemodel.modelCI_EntityTypes;
import tablemodel.modelSuppInfoEntityTypes;

public class pnlSupplierInfo extends JXPanel implements ActionListener {

	private static final long serialVersionUID = 4256723533785577913L;
	private JPanel pnlCorporation;
	private JPanel pnlCorpMainInfo;
	private JSplitPane splitRegisteredName;
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
	private modelSuppInfoEntityTypes modelEntityTypes;
	private JScrollPane scrollEntityTypes;
	private _JTableMain tblEntityTypes;
	private JList rowHeaderEntityTypes;

	private JComboBox cmbCorpType;
	private _JXTextField txtCorpType;
	private _JXTextField txtPosition;
	private _JXTextField txtAuthorizedPerson;
	private _JXTextField txtCorpAlias;
	private _JXTextField txtCorpName;
	private _JXTextField txtFName;
	private _JXTextField txtMName;
	private _JXTextField txtLName;
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
	private String entity_id;

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
						splitRegisteredName = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
						pnlCorpInfoComponents.add(splitRegisteredName, BorderLayout.CENTER);
						splitRegisteredName.setOneTouchExpandable(false);
						splitRegisteredName.setDividerLocation(0);
						{
							txtCorpName = new _JXTextField("Registered Name");
							splitRegisteredName.add(txtCorpName, JSplitPane.LEFT);
						}
						{
							JPanel pnlFullName = new JPanel(new GridLayout(1, 3, 3, 3));
							splitRegisteredName.add(pnlFullName, JSplitPane.RIGHT);
							{
								txtFName = new _JXTextField("First Name");
								pnlFullName.add(txtFName);
							}
							{
								txtMName = new _JXTextField("MIddle Name");
								pnlFullName.add(txtMName);
							}
							{
								txtLName = new _JXTextField("Last Name");
								pnlFullName.add(txtLName);
							}
						}
					}
					{
						JPanel pnlCorpAlias = new JPanel(new BorderLayout(3, 3));
						pnlCorpInfoComponents.add(pnlCorpAlias);
						{
							txtCorpAlias = new _JXTextField("Trade Name");
							pnlCorpAlias.add(txtCorpAlias, BorderLayout.WEST);
							txtCorpAlias.setPreferredSize(new Dimension(380, 0));
						}
						{
							JPanel pnlEntityKind = new JPanel(new BorderLayout(3, 3));
							pnlCorpAlias.add(pnlEntityKind, BorderLayout.CENTER);
							{
								JLabel lblEntityKind = new JLabel("Entity Kind", JLabel.TRAILING);
								pnlEntityKind.add(lblEntityKind);
							}
							{
								cmbEntityKind = new JComboBox(new String[] { "Individual", "Corporation"});
								pnlEntityKind.add(cmbEntityKind, BorderLayout.EAST);
								cmbEntityKind.setEnabled(false);
								cmbEntityKind.setPreferredSize(new Dimension(200, 0));
								cmbEntityKind.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										String entity_kind = (String) ((JComboBox)e.getSource()).getSelectedItem();
										
										if(entity_kind.equals("Individual")) {
											splitRegisteredName.getLeftComponent().setVisible(false);
											splitRegisteredName.getRightComponent().setVisible(true);
//											txtAuthorizedPerson.setEditable(false);
//											txtPosition.setEditable(false);
										}
										
										if(entity_kind.equals("Corporation")) {
											splitRegisteredName.getLeftComponent().setVisible(true);
											splitRegisteredName.getRightComponent().setVisible(false);
//											txtAuthorizedPerson.setEditable(true);
//											txtPosition.setEditable(true);
										}
										
									}
								});
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
					modelEntityTypes = new modelSuppInfoEntityTypes();

					tblEntityTypes = new _JTableMain(modelEntityTypes);
					scrollEntityTypes.setViewportView(tblEntityTypes);
					tblEntityTypes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					tblEntityTypes.setSortable(false);
					tblEntityTypes.hideColumns("ID", "WTAX ID");

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
		displaySuppEntityTypes("");
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
		cancelSuppInfo(entity_id);
		txtCorpName.setEditable(true);
		txtFName.setEditable(true);
		txtMName.setEditable(true);
		txtLName.setEditable(true);
		txtCorpAlias.setEditable(true);
		cmbEntityKind.setEnabled(true);
		txtAuthorizedPerson.setEditable(true);
		txtPosition.setEditable(true);
		txtTIN_No.setEditable(true);
		chkCorpVAT.setEnabled(true);
		txtEmail.setEditable(true);
		txtTelNo.setEditable(true);
		cmbCorpBusinessClass.setEnabled(true);
		cmbCorpBusinessNature.setEnabled(true);
		displaySuppEntityTypes(entity_id);
		modelEntityTypes.setEditable(true);
	}
	
	public void editSupplierInfo() {
		txtCorpName.setEditable(true);
		txtFName.setEditable(true);
		txtMName.setEditable(true);
		txtLName.setEditable(true);
		txtCorpAlias.setEditable(true);
		cmbEntityKind.setEnabled(true);
		txtAuthorizedPerson.setEditable(true);
		txtPosition.setEditable(true);
		txtTIN_No.setEditable(true);
		chkCorpVAT.setEnabled(true);
		txtEmail.setEditable(true);
		txtTelNo.setEditable(true);
		cmbCorpBusinessClass.setEnabled(true);
		cmbCorpBusinessNature.setEnabled(true);
		modelEntityTypes.setEditable(true);
	}
	
	public void cancelSuppInfo(String entity_id) {
		
		txtCorpName.setEditable(false);
		txtCorpName.setText("");
		txtFName.setEditable(false);
		txtFName.setText("");
		txtMName.setEditable(false);
		txtMName.setText("");
		txtLName.setEditable(false);
		txtLName.setText("");
		txtCorpAlias.setEditable(false);
		txtCorpAlias.setText("");
		cmbEntityKind.setEnabled(false);
		cmbEntityKind.setSelectedIndex(0);
		txtAuthorizedPerson.setEditable(false);
		txtAuthorizedPerson.setText("");
		txtPosition.setEditable(false);
		txtPosition.setText("");
		txtTIN_No.setEditable(false);
		txtTIN_No.setText("");
		chkCorpVAT.setEnabled(false);
		chkCorpVAT.setSelected(false);
		txtEmail.setEditable(false);
		txtEmail.setText("");
		txtTelNo.setEditable(false);
		txtTelNo.setText("");
		cmbCorpBusinessClass.setEnabled(false);
		cmbCorpBusinessNature.setEnabled(false);
		modelEntityTypes.clear();
		scrollEntityTypes.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(""));
		modelEntityTypes.setEditable(false);
	}
	
	public void displaySuppEntityTypes(String entity_id) {
		modelEntityTypes.clear();
		pgSelect db = new pgSelect();
		String SQL = "select * from view_suppinfo_entity_types('"+entity_id+"');";
		db.select(SQL);
		
		if(db.isNotNull()) {
			for(int x=0; x<db.getRowCount(); x++) {
				modelEntityTypes.addRow(db.getResult()[x]);
			}
		}
		scrollEntityTypes.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblEntityTypes.getRowCount())));
		tblEntityTypes.packAll();
		
	}
	
	//CHECKING OF REQUIRED FIELDS BEFORE SAVING
	public boolean isRequiredComplete() {
		String required_fields = "";
		Boolean toSave = true;
		
		//INDIVIDUAL REQUIRED FIELDS
		if(cmbEntityKind.getSelectedIndex() == 0) {
			if(txtFName.getText().equals("")){
				required_fields = required_fields + "• First Name\n";
				toSave = false;
			}
			if(txtLName.getText().equals("")){
				required_fields = required_fields + "• Last Name\n";
				toSave = false;
			}
		}
		
		//CORPORATION REQUIRED FIELDS
		if(cmbEntityKind.getSelectedIndex() == 1) {
			if(txtCorpName.getText().equals("")){
				required_fields = required_fields + "• Registered Name\n";
				toSave = false;
			}
			if(txtAuthorizedPerson.getText().equals("")){
				required_fields = required_fields + "• Authorized Person\n";
				toSave = false;
			}
			if(txtPosition.getText().equals("")){
				required_fields = required_fields + "• Position\n";
				toSave = false;
			}
			
		}
		
		if(txtCorpAlias.getText().equals("")) {
			required_fields = required_fields + "• Trade Name\n";
			toSave = false;
		}
		
		if(txtTIN_No.getText().equals("")) {
			required_fields = required_fields + "• TIN\n";
			toSave = false;
		}
		if(txtTelNo.getText().equals("")) {
			required_fields = required_fields + "• Tel. No\n";
			toSave = false;
		}
		if(txtEmail.getText().equals("")) {
			required_fields = required_fields + "• Email\n";
			toSave = false;
		}
		
		if(hasSelected() == false) {
			required_fields = required_fields + "• Entity Types\n";
			toSave = false;
		}
		
		if(toSave == false){
			JOptionPane.showMessageDialog(null, "Please fill up all required fields:\n"+required_fields,"Save",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	private Boolean hasSelected() {
		ArrayList<Boolean> listSelected = new ArrayList<Boolean>();
		for (int x = 0; x < modelEntityTypes.getRowCount(); x++) {
			listSelected.add((Boolean) modelEntityTypes.getValueAt(x, 0));
		}
		return listSelected.contains(true);
	}
	
	public void saveSupplierInfo(String entity_id) {
		String entity_kind = (cmbEntityKind.getSelectedIndex() == 0 ? "I":"C");
		String entity_name = (cmbEntityKind.getSelectedIndex() == 0 ? String.format("%s, %s %s", txtLName.getText().replace("'", "''"), txtFName.getText().replace("'", "''"), txtMName.getText().replace("'", "''")).toUpperCase().trim():txtCorpName.getText().toUpperCase().trim().replace("'", "''"));
		String fname = txtFName.getText().toUpperCase().trim().replace("'", "''");
		String mname = txtMName.getText().toUpperCase().trim().replace("'", "''");
		String lname = txtLName.getText().toUpperCase().trim().replace("'", "''");
		String trade_name = txtCorpAlias.getText().toUpperCase().trim();
		String authorized_person = txtAuthorizedPerson.getText().toUpperCase().trim();
		String position = txtPosition.getText().toUpperCase().trim();
		String tin = txtTIN_No.getText().trim();
		Boolean vat_registered = chkCorpVAT.isSelected();
		String business_nature = ((String) cmbCorpBusinessNature.getSelectedItem()).split("-")[1].trim();
		String business_class = ((String) cmbCorpBusinessClass.getSelectedItem()).split("-")[1].trim();
		String telephone = txtTelNo.getText().trim();
		String email = txtEmail.getText().trim();
		
		
		pgSelect db = new pgSelect();
		String SQL = "SELECT sp_save_supplier_info('"+entity_id+"', '"+entity_kind+"', '"+entity_name+"', '"+fname+"', '"+mname+"', '"+lname+"', '"+trade_name+"', '"+authorized_person+"', "
				+ "'"+position+"', '"+tin+"', "+vat_registered+", '"+business_nature+"', '"+business_class+"', '"+telephone+"', '"+email+"', '"+UserInfo.EmployeeCode+"');";
		db.select(SQL);
	}
	
	public boolean saveEntityTypes(String entity_id) {
		Boolean saved = false;
		
			ArrayList<Boolean> isTagged = new ArrayList<Boolean>();
			
			for(int x=0; x<modelEntityTypes.getRowCount(); x++){
				Boolean isSelected = (Boolean) modelEntityTypes.getValueAt(x, 0);
				if(isSelected){
					isTagged.add(isSelected);
				}
			}
			
			if(isTagged.contains(true)){
				for(int x=0; x < modelEntityTypes.getRowCount(); x++){
					Boolean selected = (Boolean) modelEntityTypes.getValueAt(x, 0);
					String entity_type_id = (String) modelEntityTypes.getValueAt(x, 1);

					String SQL = "SELECT sp_update_entity_types('"+ entity_id +"', '"+ entity_type_id +"', "+ selected +", '"+ UserInfo.EmployeeCode +"');";

					pgSelect db = new pgSelect();
					db.select(SQL);
					
					FncSystem.out("Display ", SQL);
					saved = true;
				}
			}else{
				JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Please select entity type", "Save", JOptionPane.WARNING_MESSAGE);
				saved = false;
			}
		
		return saved;
	}
	
	public void displayDetails(String entity_id) {
		this.entity_id = entity_id;
		
		displaySupplierInfo(entity_id);
		displaySuppEntityTypes(entity_id);
		scrollEntityTypes.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblEntityTypes.getRowCount())));
		tblEntityTypes.packAll();
		
		
	}
	
	public void displaySupplierInfo(String entity_id) {
		pgSelect db = new pgSelect();
		String SQL = "select * from view_supplier_info('"+entity_id+"');";
		db.select(SQL);
		
		if(db.isNotNull()) {
			String entity_name = (String) db.getResult()[0][0];
			String first_name= (String) db.getResult()[0][1];
			String middle_name = (String) db.getResult()[0][2];
			String last_name = (String) db.getResult()[0][3];
			String trade_name = (String) db.getResult()[0][4];
			String entity_kind = (String) db.getResult()[0][5];
			String authorized_person = (String) db.getResult()[0][6];
			String position = (String) db.getResult()[0][7];
			String tin = (String) db.getResult()[0][8];
			Boolean vat_registered = (Boolean) db.getResult()[0][9];
			String business_nature = (String) db.getResult()[0][10];
			String business_class = (String) db.getResult()[0][11];
			String tel_no = (String) db.getResult()[0][12];
			String email = (String) db.getResult()[0][13];
			
			if(entity_kind.equals("I")) {
				cmbEntityKind.setSelectedIndex(0);
			}else {
				cmbEntityKind.setSelectedIndex(1);
			}
			txtCorpName.setText(entity_name);
			txtFName.setText(first_name);
			txtMName.setText(middle_name);
			txtLName.setText(last_name);
			txtCorpAlias.setText(trade_name);
			txtAuthorizedPerson.setText(authorized_person);
			txtPosition.setText(position);
			txtTIN_No.setText(tin);
			cmbCorpBusinessNature.setSelectedItem(business_nature);
			cmbCorpBusinessClass.setSelectedItem(business_class);
			txtTelNo.setText(tel_no);
			txtEmail.setText(email);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
