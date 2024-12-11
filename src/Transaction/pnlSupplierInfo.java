package Transaction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import org.jdesktop.swingx.JXPanel;

import com.toedter.calendar.JDateChooser;

import Database.pgSelect;
import FormattedTextField._JXFormattedTextField;
import Functions.FncSystem;
import Functions.FncTables;
import Functions.UserInfo;
import Lookup._JLookup;
import components.JTBorderFactory;
import components._JInternalFrame;
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
	private _JXTextField txtSuffixName;
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
	private SupplierInfo si;

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
						JLabel lblEntityKind = new JLabel("Entity Kind");
						pnlCorpInfoLabel.add(lblEntityKind);
					}
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
						JLabel lblTelNo = new JLabel("Tel. No");
						pnlCorpInfoLabel.add(lblTelNo);
					}
					
					//					{
					//						lblCorpBusinessNature = new JLabel("Business Nature");
					//						pnlCorpInfoLabel.add(lblCorpBusinessNature);
					//					}
					//					{
					//						lblCorpBusinessClass = new JLabel("Business Class");
					//						pnlCorpInfoLabel.add(lblCorpBusinessClass);
					//					}


				}
				{
					pnlCorpInfoComponents = new JPanel(new GridLayout(7, 1, 5, 5));
					pnlCorpInfoLeft.add(pnlCorpInfoComponents, BorderLayout.CENTER);
					{
						JPanel pnlEntityKind = new JPanel(new BorderLayout(3, 3));
						pnlCorpInfoComponents.add(pnlEntityKind, BorderLayout.CENTER);
						
						{
							cmbEntityKind = new JComboBox(new String[] { "Individual", "Corporation"});
							pnlEntityKind.add(cmbEntityKind, BorderLayout.WEST);
							cmbEntityKind.setEnabled(false);
							cmbEntityKind.setPreferredSize(new Dimension(200, 0));
							cmbEntityKind.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									String entity_kind = (String) ((JComboBox)e.getSource()).getSelectedItem();

									if(entity_kind.equals("Individual")) {
										splitRegisteredName.getLeftComponent().setVisible(false);
										splitRegisteredName.getRightComponent().setVisible(true);
										txtAuthorizedPerson.setPromptForegroundColor(null);
										txtPosition.setPromptForegroundColor(null);
										//											txtAuthorizedPerson.setEditable(false);
										//											txtPosition.setEditable(false);
									}

									if(entity_kind.equals("Corporation")) {
										splitRegisteredName.getLeftComponent().setVisible(true);
										splitRegisteredName.getRightComponent().setVisible(false);
										txtAuthorizedPerson.setPromptForegroundColor(Color.RED);
										txtPosition.setPromptForegroundColor(Color.RED);
										txtCorpName.setPromptForegroundColor(Color.RED);
										//											txtAuthorizedPerson.setEditable(true);
										//											txtPosition.setEditable(true);
									}

								}
							});
						}
					}
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
								JPanel pnlLastName = new JPanel(new BorderLayout(3, 3));
								pnlFullName.add(pnlLastName);
								{
									txtLName = new _JXTextField("Last Name", Color.RED);
									pnlLastName.add(txtLName, BorderLayout.CENTER);
									
								}
								{
									txtSuffixName = new _JXTextField("Ext Name");
									pnlLastName.add(txtSuffixName, BorderLayout.EAST);
									txtSuffixName.setPreferredSize(new Dimension(70, 0));
								}
							}
							{
								txtFName = new _JXTextField("First Name", Color.RED);
								pnlFullName.add(txtFName);
							}
							{
								txtMName = new _JXTextField("Middle Name", Color.RED);
								pnlFullName.add(txtMName);
							}


						}
					}
					{
						JPanel pnlCorpAlias = new JPanel(new BorderLayout(3, 3));
						pnlCorpInfoComponents.add(pnlCorpAlias);
						{
							txtCorpAlias = new _JXTextField("Trade Name", Color.RED);
							pnlCorpAlias.add(txtCorpAlias, BorderLayout.WEST);
							txtCorpAlias.setPreferredSize(new Dimension(380, 0));
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
							txtTIN_No = new _JXTextField("TIN", Color.RED);
							pnlCorpTIN.add(txtTIN_No, BorderLayout.WEST);
							txtTIN_No.setPreferredSize(new Dimension(150, 0));
							((AbstractDocument)txtTIN_No.getDocument()).setDocumentFilter(new NumericFilter());
						}
						{
							chkCorpVAT = new JCheckBox("VAT Registered");
							pnlCorpTIN.add(chkCorpVAT, BorderLayout.EAST);
							chkCorpVAT.setEnabled(false);
						}
					}

					{
						JPanel pnlTelNo = new JPanel(new BorderLayout(3, 3));
						pnlCorpInfoComponents.add(pnlTelNo);

						{
							txtTelNo = new _JXTextField("Telephone No", Color.RED);
							pnlTelNo.add(txtTelNo, BorderLayout.WEST);
							txtTelNo.setPreferredSize(new Dimension(150, 0));
						}
						
						{
							JPanel pnlEmail = new JPanel(new BorderLayout(3,3));
							pnlTelNo.add(pnlEmail, BorderLayout.EAST);
							pnlEmail.setPreferredSize(new Dimension(250, 0));
							{
								JLabel lblEmail = new JLabel("Email");
								pnlEmail.add(lblEmail, BorderLayout.WEST);
							}
							{
								txtEmail = new _JXTextField("Email", Color.RED);
								pnlEmail.add(txtEmail, BorderLayout.CENTER);
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

	public void clearComponentValues(Container panel) {
		System.out.println("Dumaan dito bago for loop");

		for (Component comp : panel.getComponents()) {
			System.out.println("Processing component: " + comp.getClass().getName());
			if (comp instanceof JTextField) {
				clearTextField((JTextField) comp);
			} else if (comp instanceof _JXTextField) {
				clearTextField((_JXTextField) comp);
			} else if (comp instanceof _JLookup) {
				((_JLookup) comp).setValue(null);
			} else if (comp instanceof JTextArea) {
				((JTextArea) comp).setText("");
			} else if (comp instanceof _JXFormattedTextField) {
				((_JXFormattedTextField) comp).setValue(new BigDecimal(0.00));
			} else if (comp instanceof JComboBox) {
				((JComboBox) comp).setSelectedIndex(0);
			} else if (comp instanceof JDateChooser) {
				((JDateChooser) comp).setDate(null);
			} else if (comp instanceof JCheckBox) {
					((JCheckBox) comp).setSelected(false);
			} else if (comp instanceof Container) {
				// Recursively clear nested containers
				clearComponentValues((Container) comp);
			}
		}
	}

	private void clearTextField(JTextField textField) {
		if (textField.getDocument() instanceof AbstractDocument) {
			AbstractDocument doc = (AbstractDocument) textField.getDocument();
			DocumentFilter filter = doc.getDocumentFilter();
			doc.setDocumentFilter(null);
			textField.setText("");
			doc.setDocumentFilter(filter);
		} else {
			textField.setText("");
		}
	}

	public void setComponentsEnabled(Container panel, boolean editable) {
		for (Component comp : panel.getComponents()) {
			if(comp instanceof JTextField){
				((JTextField)comp).setEditable(editable);
			} else if (comp instanceof _JXTextField) {
				((_JXTextField)comp).setEditable(editable);
			} else if (comp instanceof _JLookup) {
				((_JLookup) comp).setEditable(editable);
			} else if (comp instanceof JTextArea) {
				((JTextArea) comp).setEditable(editable);
			} else if (comp instanceof _JXFormattedTextField) {
				((_JXFormattedTextField) comp).setEditable(editable);
			} else if (comp instanceof JComboBox) {
				((JComboBox) comp).setEnabled(editable);
			} else if (comp instanceof JDateChooser) {
				((JDateChooser) comp).setEnabled(editable);
			} else if (comp instanceof JCheckBox) {
				((JCheckBox) comp).setEnabled(editable);
			} else if (comp instanceof Container) {
				setComponentsEnabled((Container) comp, editable);
			}
		}
	}

	public void newSupplier(String entity_id) {
		cancelSuppInfo(entity_id);
		setComponentsEnabled(pnlCorpMainInfo, true);
//		txtCorpName.setEditable(true);
//		txtFName.setEditable(true);
//		txtMName.setEditable(true);
//		txtLName.setEditable(true);
//		txtCorpAlias.setEditable(true);
//		cmbEntityKind.setEnabled(true);
//		txtAuthorizedPerson.setEditable(true);
//		txtPosition.setEditable(true);
//		txtTIN_No.setEditable(true);
//		chkCorpVAT.setEnabled(true);
//		txtEmail.setEditable(true);
//		txtTelNo.setEditable(true);
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

		clearComponentValues(pnlCorpMainInfo);
		setComponentsEnabled(pnlCorpMainInfo, false);
		//		txtCorpName.setEditable(false);
		//		txtCorpName.setText("");
		//		txtFName.setEditable(false);
		//		txtFName.setText("");
		//		txtMName.setEditable(false);
		//		txtMName.setText("");
		//		txtLName.setEditable(false);
		//		txtLName.setText("");
		//		txtCorpAlias.setEditable(false);
		//		txtCorpAlias.setText("");
		//		cmbEntityKind.setEnabled(false);
		//		cmbEntityKind.setSelectedIndex(0);
		//		txtAuthorizedPerson.setEditable(false);
		//		txtAuthorizedPerson.setText("");
		//		txtPosition.setEditable(false);
		//		txtPosition.setText("");
		//		txtTIN_No.setEditable(false);
		//		txtTIN_No.setText("");
		//		chkCorpVAT.setEnabled(false);
		//		chkCorpVAT.setSelected(false);
		//		txtEmail.setEditable(false);
		//		txtEmail.setText("");
		//		txtTelNo.setEditable(false);
		//		txtTelNo.setText("");
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
			JOptionPane.showMessageDialog(null, "Please fill out all required fields:\n"+required_fields,"Save",JOptionPane.WARNING_MESSAGE);
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

	public class NumericFilter extends DocumentFilter {
		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
			if (string != null && string.matches("\\d+")) {
				super.insertString(fb, offset, string, attr);
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
			if (text != null && text.matches("\\d+")) {
				super.replace(fb, offset, length, text, attrs);
			}
		}
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
