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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Database.pgSelect;
import DateChooser._JDateChooser;
import Functions.FncGlobal;
import Lookup._JLookup;
import components._JButton;
import components._JInternalFrame;
import components._JTagLabel;
import interfaces._GUI;

/**
 * 
 */
public class PettyCash extends _JInternalFrame implements _GUI, ActionListener {

	private static final long serialVersionUID = -4306362386582966144L;

	private static String title = "Petty Cash";
	private JPanel pnlMain;
	
	private JPanel pnlMainNorth;
	
	private JPanel pnlMainNorthCenter;
	private JPanel pnlMainNorthCenterLabel;
	private JPanel pnlMainNorthCenterComponent;
	private JLabel lblCompany;
	private _JLookup lookupCompany;
	private _JTagLabel tagLabelCompany;
	private JComboBox cmbCompany;
	
	private JLabel lblDateCreated;
	private _JDateChooser dteDateCreated;
	
	private JLabel lblRequestType;
	private JComboBox cmbRequestType;
	private _JLookup lookupRequestType;
	private _JTagLabel tagLabelRequestType;
	
	private JPanel pnlMainNorthEast;
	private JLabel lblPCRNo;
	private _JLookup lookupPCRNo;
	
	private JLabel lblStatus;
	private _JTagLabel tagLabelStatus;
	
	private JLabel lblTransactionDate;
	private _JDateChooser dteTransDate;
	
	private JLabel lblPayee;
	private _JLookup lookupPayee;
	private _JTagLabel taglblPayee;
	
	private _JTagLabel taglblDivision;
	
	
	private JPanel pnlMainCenter;
	
	private JPanel pnlMainSouth;
	private JButton btnAddNew;
	private JButton btnEdit;
	private JButton btnSave;
	private JButton btnPreview;
	private JButton btnPay;
	private JButton btnProcess;
	private JButton btnCancel;
	
	Border lineBorder = BorderFactory.createLineBorder(Color.GRAY);

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

	@Override
	public void initGUI() {
		this.setLayout(new BorderLayout(5, 5));
		this.setSize(SIZE);
		this.setPreferredSize(new Dimension(935, 538));
		this.setTitle(title);
		{
			pnlMain = new JPanel(new BorderLayout(5, 5));
			getContentPane().add(pnlMain, BorderLayout.CENTER);
			pnlMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			{
				pnlMainNorth = new JPanel(new GridLayout(1, 2, 5, 5));
				pnlMain.add(pnlMainNorth, BorderLayout.NORTH);
				pnlMainNorth.setPreferredSize(new Dimension(0, 100));
				//pnlMainNorth.setBorder(lineBorder);
				{
					pnlMainNorthCenter = new JPanel(new BorderLayout(5, 5));
					pnlMainNorth.add(pnlMainNorthCenter);
					{
						pnlMainNorthCenterLabel = new JPanel(new GridLayout(4, 1, 5, 5));
						pnlMainNorthCenter.add(pnlMainNorthCenterLabel, BorderLayout.WEST);
						{
							lblCompany = new JLabel("COMPANY");
							pnlMainNorthCenterLabel.add(lblCompany);
							lblCompany.setFont(new Font("Segoe UI", Font.BOLD, 12));
						}
						{
							pnlMainNorthCenterLabel.add(Box.createHorizontalBox());
						}
						{
							lblDateCreated = new JLabel("Date Created");
							pnlMainNorthCenterLabel.add(lblDateCreated);
						}
						{
							lblRequestType = new JLabel("Request Type");
							pnlMainNorthCenterLabel.add(lblRequestType);
						}
					}
					{
						pnlMainNorthCenterComponent = new JPanel(new GridLayout(4, 1, 5, 5));
						pnlMainNorthCenter.add(pnlMainNorthCenterComponent, BorderLayout.CENTER);
						{
							cmbCompany = new JComboBox();
							pnlMainNorthCenterComponent.add(cmbCompany);
						}
						{
							pnlMainNorthCenterComponent.add(Box.createHorizontalBox());
						}
						{
							dteDateCreated = new _JDateChooser("MM/dd/yy", "##/##/##", '_');
							pnlMainNorthCenterComponent.add(dteDateCreated);
						}
						{
							cmbRequestType = new JComboBox();
							pnlMainNorthCenterComponent.add(cmbRequestType);
						}
					}
				}
				{
					pnlMainNorthEast = new JPanel(new BorderLayout(5, 5));
					pnlMainNorth.add(pnlMainNorthEast);
					//pnlMainNorthEast.setPreferredSize(new Dimension(350, 0));
					//pnlMainNorthEast.setBorder(lineBorder);
					{
						
					}
				}
			}
			{
				pnlMainCenter = new JPanel(new BorderLayout(5, 5));
				pnlMain.add(pnlMainCenter);
				
			}
			{
				pnlMainSouth = new JPanel(new GridLayout(1, 7, 5, 5));
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
		cancel();
	}//XXX END OF INIT GUI
	
	
	public LinkedHashMap<String, String> mapCompany() {//HASHMAP FOR COMPANY
		LinkedHashMap<String, String> mapCompany = new LinkedHashMap<String, String>();

		String sql = "select co_id, company_name from mf_company WHERE status_id = 'A' and rec_status = 'A'";
		pgSelect db = new pgSelect();
		db.select(sql);
		if(db.isNotNull()){
			for(int x = 0; x < db.getRowCount(); x++){
				String co_id = (String) db.getResult()[x][0];
				String co_name = (String) db.getResult()[x][1];

				mapCompany.put(co_id, co_name);
			}
			return mapCompany;
		}else{
			return null;
		}
	}
	
	public Object[] listCompany() {
		ArrayList<String> arrayCompany = new ArrayList<String>();

		for(Entry<String, String> entry : mapCompany().entrySet()) {
			String company_name = entry.getValue();

			arrayCompany.add(company_name);
		}
		return arrayCompany.toArray();
	}
	
	private String getCompanyID(String company_name) {
		String co_id = "";
		for(Entry<String, String> entry : mapCompany().entrySet()){
			if(entry.getValue().equals(company_name)){
				co_id = entry.getKey();
			}
		}
		
		return co_id;
	}
	
	private void cancel() {
		mapCompany();
		cmbCompany.setModel(new DefaultComboBoxModel(listCompany()));
		
		
	}
	
	private void btnState(Boolean add, Boolean edit, Boolean save, Boolean preview, Boolean pay, Boolean process) {
		btnAddNew.setEnabled(add);
		btnEdit.setEnabled(edit);
		btnSave.setEnabled(save);
		btnPreview.setEnabled(preview);
		btnPay.setEnabled(pay);
		btnProcess.setEnabled(process);
	}
	
	
	
	
	
}
