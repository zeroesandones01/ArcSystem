package Reports.ClientServicing;

import interfaces._GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTextField;

import Database.pgSelect;
import DateChooser._JDateChooser;
import Functions.FncFocusTraversalPolicy;
import Functions.FncGlobal;
import Functions.FncReport;
import Functions.UserInfo;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;

import components._JInternalFrame;

public class HoldUnits extends _JInternalFrame implements _GUI, ActionListener {

	private static final long serialVersionUID = 5159650933602942626L;

	static String title = "Hold Units";
	Dimension frameSize = new Dimension(500, 250);
	Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
	Cursor handCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

	private JXPanel pnlMain;

	private JXPanel pnlCenter;

	private JXLabel lblCompany;
	private _JLookup lookupCompany;
	private JTextField txtCompany;

	private JXLabel lblProject;
	private _JLookup lookupProject;
	private JTextField txtProject;

	private JXLabel lblPhase;
	private _JLookup lookupPhase;
	private JXTextField txtPhase;

	private JCheckBox chkIncludeMgmtHoldAcc;

	private JXPanel pnlSouth;
	private JButton btnPreview;

	private KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();

	private _JDateChooser dteDate;
	private JCheckBox chkIncludeFltng ;
	private JCheckBox chkIncludeHldng;
	private JCheckBox chkIncludeCmmtmnt;
	
	private Boolean bln_floating = true;
	private Boolean bln_holding = true;
	private Boolean bln_commit = true;
	private Boolean bln_mgmt_hold = true;

	private JXPanel pnlNorth;

	private JXPanel pnlNorthLabel;

	private JXPanel pnlNorthComponents;

	private JXPanel pnlSouthCheckBox;

	private JXPanel pnlNorthLookUp;

	private JXPanel pnlNorthTxtField;


	public HoldUnits() {
		super(title, false, true, true, true);
		initGUI();
	}

	public HoldUnits(String title) {
		super(title);
		initGUI();
	}

	public HoldUnits(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
		initGUI();
	}

	@Override
	public void initGUI() {
		this.setTitle(title);
		this.setSize(frameSize);
		this.setPreferredSize(frameSize);
		//XXX CHANGE TO GRIDLAYOUT AND REPLACE SETBOUNDS CODE REVIEW 2024-05-24
		{
			pnlMain = new JXPanel(new BorderLayout(5, 5));
			getContentPane().add(pnlMain);
			pnlMain.setBorder(new EmptyBorder(5, 5, 5, 5));
			{
				pnlNorth = new JXPanel(new BorderLayout(5, 5));
				pnlMain.add(pnlNorth, BorderLayout.NORTH);
				pnlNorth.setBorder(FncGlobal.lineBorder);
				pnlNorth.setPreferredSize(new Dimension(0, 170));
				{
					pnlNorthLabel = new JXPanel (new GridLayout(4, 1, 3, 3));
					pnlNorth.add(pnlNorthLabel, BorderLayout.WEST);
					pnlNorthLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
					pnlNorthLabel.setPreferredSize(new Dimension(100, 0));
					{
						lblCompany = new JXLabel("Company");
						pnlNorthLabel.add(lblCompany);
					}
					{
						lblProject = new JXLabel("Project");
						pnlNorthLabel.add(lblProject);
					}
					{
						lblPhase = new JXLabel("Phase");
						pnlNorthLabel.add(lblPhase);
					}
					{
						JXLabel lblDate = new JXLabel("Date");
						pnlNorthLabel.add(lblDate);
					}
				}
				{
					pnlNorthComponents = new JXPanel(new BorderLayout(5, 5));
					pnlNorth.add(pnlNorthComponents, BorderLayout.CENTER);
					{
						pnlNorthLookUp = new JXPanel(new GridLayout(4, 1, 3, 3));
						pnlNorthComponents.add(pnlNorthLookUp, BorderLayout.WEST);
						pnlNorthLookUp.setBorder(new EmptyBorder(5, 5, 5, 5));
						pnlNorthLookUp.setPreferredSize(new Dimension(130, 0));
						
						{
							lookupCompany = new _JLookup(null, "Company");
							pnlNorthLookUp.add(lookupCompany);
							lookupCompany.setReturnColumn(0);
							lookupCompany.setLookupSQL(_JInternalFrame.SQL_COMPANY());
							lookupCompany.addLookupListener(new LookupListener() {
								public void lookupPerformed(LookupEvent event) {
									Object[] data = ((_JLookup)event.getSource()).getDataSet();

									if(data != null){
										txtCompany.setText(data[1].toString());
										txtCompany.setToolTipText(data[2].toString());

										lookupProject.setLookupSQL(SQL_PROJECT_ALL(data[0].toString()));

										manager.focusNextComponent();
									}else{
										txtCompany.setText("");
									}
								}
							});
						}
						
						{
							lookupProject = new _JLookup(null, "Project","Please select company.");
							pnlNorthLookUp.add(lookupProject);
							//lookupProject.setReturnColumn(0);
							lookupProject.addLookupListener(new LookupListener() {
								public void lookupPerformed(LookupEvent event) {
									Object[] data = ((_JLookup)event.getSource()).getDataSet();

									if(data != null){

										txtProject.setText(data[1].toString());
										lookupPhase.setLookupSQL(sqlPhase(data[0].toString()));
										manager.focusNextComponent();
									}else{
										txtProject.setText("");
									}
								}
							});
						}
						
						{
							lookupPhase = new _JLookup(null, "Phase", "Please select project.");
							pnlNorthLookUp.add(lookupPhase);
							lookupPhase.setReturnColumn(0);
							lookupPhase.addLookupListener(new LookupListener() {
								public void lookupPerformed(LookupEvent event) {//XXX Phase
									Object[] data = ((_JLookup)event.getSource()).getDataSet();
									if(data != null){
										txtPhase.setText(data[1].toString());
										manager.focusNextComponent();
									}else{
										JOptionPane.showMessageDialog(FncGlobal.homeMDI, "Please select project first.", "Phase", JOptionPane.WARNING_MESSAGE);
									}
								}
							});
						}
						
						{
							dteDate = new _JDateChooser("MM/dd/yyyy", "##/##/##", '_');
							pnlNorthLookUp.add(dteDate);	
							dteDate.setDate(null);
							dteDate.setEnabled(true);
							dteDate.setDate(null);
						}
						
						
					}
					
					pnlNorthTxtField = new JXPanel(new GridLayout(4, 1, 3, 3));
					pnlNorthComponents.add(pnlNorthTxtField, BorderLayout.CENTER);
					pnlNorthTxtField.setBorder(new EmptyBorder(5, 5, 5, 5));

				
					{
						txtCompany = new JTextField();
						pnlNorthTxtField.add(txtCompany);
						txtCompany.setEditable(false);
					}
					{
						txtProject = new JTextField();
						pnlNorthTxtField.add(txtProject);
						txtProject.setEditable(false);
					}

					
					{
						txtPhase = new JXTextField("");
						pnlNorthTxtField.add(txtPhase);
						txtPhase.setEditable(false);
					}
					
				}
				{
					pnlSouthCheckBox = new JXPanel (new GridLayout(1, 4, 5, 5));
					pnlNorth.add(pnlSouthCheckBox, BorderLayout.SOUTH);
				
					{
						chkIncludeFltng = new JCheckBox("Floating");
						pnlSouthCheckBox.add(chkIncludeFltng );
						chkIncludeFltng .setSelected(true);
						chkIncludeFltng .addItemListener(new ItemListener() {
							public void itemStateChanged(ItemEvent e) {
								if(chkIncludeFltng.isSelected()){
									bln_floating = true;
								}else{
									bln_floating = false;
								}											
							}
						});

					}
					{
						chkIncludeHldng = new JCheckBox("Holding");
						pnlSouthCheckBox.add(chkIncludeHldng);
						chkIncludeHldng.setSelected(true);
						chkIncludeHldng.addItemListener(new ItemListener() {
							public void itemStateChanged(ItemEvent e) {


								if(chkIncludeHldng.isSelected()==true){
									bln_holding = true;
								}else{
									bln_holding = false;
								}											
							}
						});

					}
					{
						chkIncludeCmmtmnt = new JCheckBox("Commitment");
						pnlSouthCheckBox.add(chkIncludeCmmtmnt);
						chkIncludeCmmtmnt.setSelected(true);
						chkIncludeCmmtmnt.addItemListener(new ItemListener() {
							public void itemStateChanged(ItemEvent e) {

								if(chkIncludeCmmtmnt.isSelected()==true){
									bln_commit = true;
								}else{
									bln_commit = false;
								}											
							}
						});

					}
					{
						chkIncludeMgmtHoldAcc = new JCheckBox("Mgmt. Hold");
						pnlSouthCheckBox.add(chkIncludeMgmtHoldAcc);
						chkIncludeMgmtHoldAcc.setSelected(true);
						chkIncludeMgmtHoldAcc.addItemListener(new ItemListener() {
							public void itemStateChanged(ItemEvent e) {

								if(chkIncludeMgmtHoldAcc.isSelected()==true){
									bln_mgmt_hold = true;
								}else{
									bln_mgmt_hold = false;
								}											
							}
						});

					}
				}
			}
			{
				pnlSouth = new JXPanel(new GridLayout(1,7,3,3));
				pnlSouth = new JXPanel(new BorderLayout(5, 5));
				pnlMain.add(pnlSouth, BorderLayout.SOUTH);
				pnlSouth.setBorder(new EmptyBorder(5, 5, 5, 5));
				pnlSouth.setPreferredSize(new Dimension (0,30));
				
				{
					JPanel pnlSouthWest = new JPanel(new BorderLayout(5, 5)); 
					pnlSouth.add(pnlSouthWest, BorderLayout.WEST); 
					pnlSouthWest.setPreferredSize(new Dimension(250, 80)); 
				}
				{
					JPanel pnlSouthCenter = new JPanel(new BorderLayout(5, 5)); 
					pnlSouth.add(pnlSouthCenter, BorderLayout.CENTER); 
					
					{
						btnPreview = new JButton("Preview");
						pnlSouth.add(btnPreview);
						btnPreview.setAlignmentX(0.5f);
						btnPreview.setAlignmentY(0.5f);
						btnPreview.setMaximumSize(new Dimension(100, 80));
						btnPreview.setMnemonic(KeyEvent.VK_P);
						btnPreview.addActionListener(this);
					}
					
				}
				{
					JPanel pnlSoutEast = new JPanel(new BorderLayout(5, 5)); 
					pnlSouth.add(pnlSoutEast, BorderLayout.EAST); 
					pnlSoutEast.setPreferredSize(new Dimension(250, 80)); 
				}
			}
		}

		this.setFocusTraversalPolicy(new FncFocusTraversalPolicy(lookupCompany, lookupProject, lookupPhase, chkIncludeMgmtHoldAcc, btnPreview));
		this.setBounds(0, 0, 650, 250);
	}


	private String sqlPhase(String proj_id) {
		String phase = "SELECT * FROM view_lookup_phase('"+ proj_id +"');";
		return phase;
	}


	private Object [] sql_getDivId() {

		String SQL = 
				"select " +
						"a.div_code, " +
						"trim(b.division_name) as div_name,  " +
						"a.dept_code, " +
						"trim(c.dept_name) as dept_name  " +
						"from em_employee a \n" +
						"left join mf_division b on a.div_code = b.division_code " +
						"left join mf_department c on a.dept_code = c.dept_code " +
						"where a.emp_code = '"+UserInfo.EmployeeCode+"'  " ;

		System.out.printf("SQL #1 sql_getDivId: %s", SQL);
		pgSelect db = new pgSelect();
		db.select(SQL);		

		if(db.isNotNull()){
			return db.getResult()[0];
		}else{
			return null;
		}
	}

}