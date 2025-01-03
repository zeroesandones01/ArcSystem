package Reports.ClientServicing;

import interfaces._GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTextField;

import Functions.FncFocusTraversalPolicy;
import Functions.FncGlobal;
import Functions.FncReport;
import Functions.UserInfo;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;

import components._JCheckBox;
import components._JInternalFrame;

public class OpenUnits extends _JInternalFrame implements _GUI, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5159650933602942626L;

	static String title = "Open Units";
	Dimension frameSize = new Dimension(510, 250);
	Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
	Cursor handCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

	private JXPanel pnlMain;

	private JXPanel pnlCenter;

	private JXLabel lblCompany;
	private _JLookup lookupCompany;
	private JTextField txtCompany;

	private _JCheckBox chkProject;
	private _JLookup lookupProject;
	private JTextField txtProject;

	private _JCheckBox chkPhase;
	private _JLookup lookupPhase;
	private JXTextField txtPhase;

	private JCheckBox chkOpenHouse;
	
	private JXPanel pnlSouth;
	private JButton btnPreview;

	private KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();

	public OpenUnits() {
		super(title, false, true, false, true);
		initGUI();
	}

	public OpenUnits(String title) {
		super(title);
		initGUI();
	}

	public OpenUnits(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
		initGUI();
	}

	@Override
	public void initGUI() {
		this.setTitle(title);
		this.setSize(frameSize);
		this.setPreferredSize(frameSize);
		{	
			pnlMain = new JXPanel();
			getContentPane().add(pnlMain, BorderLayout.CENTER);
			pnlMain.setLayout(new BorderLayout(5, 5));
			pnlMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			{
				pnlCenter = new JXPanel();
				pnlMain.add(pnlCenter, BorderLayout.CENTER);
				pnlCenter.setLayout(null);
				pnlCenter.setBorder(FncGlobal.lineBorder);
				{
					lblCompany = new JXLabel("Company");
					pnlCenter.add(lblCompany);
					lblCompany.setBounds(10, 10, 75, 25);
				}
				{
					lookupCompany = new _JLookup(null, "Company");
					pnlCenter.add(lookupCompany);
					lookupCompany.setReturnColumn(0);
					lookupCompany.setLookupSQL(_JInternalFrame.SQL_COMPANY());
					lookupCompany.setBounds(90, 10, 50, 25);
					lookupCompany.addLookupListener(new LookupListener() {
						public void lookupPerformed(LookupEvent event) {//XXX Project
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
					txtCompany = new JTextField();
					pnlCenter.add(txtCompany);
					txtCompany.setEditable(false);
					txtCompany.setBounds(144, 10, 341, 25);
				}
				{
					JXLabel lblProject = new JXLabel("Project");
					pnlCenter.add(lblProject);
					lblProject.setBounds(10, 40, 75, 25);
				}
				{
					lookupProject = new _JLookup(null, "Project", "Please select company.");
					pnlCenter.add(lookupProject);
					lookupProject.setReturnColumn(0);
					lookupProject.setBounds(90, 40, 50, 25);
					lookupProject.addLookupListener(new LookupListener() {
						public void lookupPerformed(LookupEvent event) {//XXX Project
							Object[] data = ((_JLookup)event.getSource()).getDataSet();
							if(data != null){
								txtProject.setText(data[1].toString());
								//lookupPhase.setLookupSQL(SQL_PHASE_ALL(data[0].toString()));
								lookupPhase.setLookupSQL(sqlPhase(data[0].toString()));
								manager.focusNextComponent();
							}else{
								txtProject.setText("");
							}
						}
					});
				}
				{
					txtProject = new JTextField();
					pnlCenter.add(txtProject);
					txtProject.setEditable(false);
					txtProject.setBounds(144, 40, 251, 25);
				}
				{
					JXLabel lblPhase = new JXLabel("Phase");
					pnlCenter.add(lblPhase);
					lblPhase.setBounds(10, 70, 75, 25);
				}
				{
					lookupPhase = new _JLookup(null, "Phase", "Please select project.");
					pnlCenter.add(lookupPhase);
					lookupPhase.setReturnColumn(0);
					lookupPhase.setBounds(90, 70, 50, 25);
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
					txtPhase = new JXTextField("");
					pnlCenter.add(txtPhase);
					txtPhase.setEditable(false);
					txtPhase.setBounds(144, 70, 120, 25);
				}
				{
					chkOpenHouse = new JCheckBox("Open House");
					pnlCenter.add(chkOpenHouse);
					chkOpenHouse.setBounds(90, 100, 174, 25);
				}
			}
			{
				pnlSouth = new JXPanel();
				pnlMain.add(pnlSouth, BorderLayout.SOUTH);
				pnlSouth.setLayout(new OverlayLayout(pnlSouth));
				pnlSouth.setPreferredSize(new Dimension(388, 30));
				{
					btnPreview = new JButton("Preview");
					pnlSouth.add(btnPreview);
					btnPreview.setAlignmentX(0.5f);
					btnPreview.setAlignmentY(0.5f);
					btnPreview.setMaximumSize(new Dimension(100, 30));
					btnPreview.setMnemonic(KeyEvent.VK_P);
					
				}
			}
		}
	

		this.setFocusTraversalPolicy(new FncFocusTraversalPolicy(lookupCompany, lookupProject, lookupPhase, btnPreview));
	}
	
	private String sqlPhase(String proj_id) {
		return "SELECT a.phase as \"Phase\", a.sub_proj_name as \"Description\", \n" + 
				"a.proj_id as \"Proj. ID\", b.proj_name as \"Project Name\", b.proj_alias as \"Alias\"\n" + 
				"from mf_sub_project a\n" + 
				"LEFT JOIN mf_project b on b.proj_id = a.proj_id\n" + 
				"where (CASE WHEN NULLIF('"+proj_id+"', 'null') IS NOT NULL THEN  a.proj_id = '"+proj_id+"' ELSE TRUE END)";
	}

}	

