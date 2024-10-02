/**
 * 
 */
package Transaction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Database.pgSelect;
import DateChooser._JDateChooser;
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
	
	private JLabel lblCompany;
	private _JLookup lookupCompany;
	private _JTagLabel tagLabelCompany;
	private JComboBox cmbCompany;
	
	private JLabel lblPCRNo;
	private _JLookup lookupPCRNo;
	
	private JLabel lblStatus;
	private _JTagLabel tagLabelStatus;
	
	private JLabel lblDateCreated;
	private _JDateChooser dteDateCreated;
	
	private JLabel lblRequestType;
	private _JLookup lookupRequestType;
	private _JTagLabel tagLabelRequestType;
	
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
				pnlMainNorth = new JPanel(new BorderLayout(5, 5));
				pnlMain.add(pnlMainNorth, BorderLayout.NORTH);
				pnlMainNorth.setPreferredSize(new Dimension(0, 200));
				pnlMainNorth.setBorder(lineBorder);
			}
			{
				pnlMainCenter = new JPanel(new BorderLayout(5, 5));
				pnlMain.add(pnlMainCenter);
				
			}
			{
				pnlMainSouth = new JPanel(new GridLayout(1, 6, 5, 5));
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
			}
		}
	}//XXX END OF INIT GUI
	
	
	
	
	private void btnState(Boolean add, Boolean edit, Boolean save, Boolean preview, Boolean pay, Boolean process) {
		btnAddNew.setEnabled(add);
		btnEdit.setEnabled(edit);
		btnSave.setEnabled(save);
		btnPreview.setEnabled(preview);
		btnPay.setEnabled(pay);
		btnProcess.setEnabled(process);
	}
	
	
	
	
	
}
