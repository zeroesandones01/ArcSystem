
package System;
import interfaces._GUI;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Database.pgSelect;
import Dialogs.dlgUserAccess;
import Dialogs.dlgUserPassword;
import Functions.FncGlobal;
import Functions.FncTables;
import components._JTableMain;

public class pnlPassword extends JPanel implements ActionListener, _GUI, ListSelectionListener {

	/**
	 * @author Jessa Herrera (2016-07-13)
	 */
	private static final long serialVersionUID = 150375306279366723L;

	private JPanel pnlEast;
	private JButton btnNew;
	private JButton btnEdit;

	private JScrollPane scrollUsers;
	private _JTableMain tblUsers;
	private modelUsers model;
	private JList rowHeaderUsers;

	public pnlPassword() {
		initGUI();
	}

	public pnlPassword(LayoutManager layout) {
		super(layout);
		initGUI();
	}

	public pnlPassword(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		initGUI();
	}

	public pnlPassword(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		initGUI();
	}

	@Override
	public void initGUI() {
		this.setLayout(new BorderLayout(5, 5));
		this.setPreferredSize(new Dimension(0, 0));
		this.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		//this.setBorder(components.JTBorderFactory.createTitleBorder("Users"));
		{
			scrollUsers = new JScrollPane();
			this.add(scrollUsers, BorderLayout.CENTER);
			{
				model = new modelUsers();

				tblUsers = new _JTableMain(model);
				scrollUsers.setViewportView(tblUsers);
				tblUsers.hideColumns("Status");
				tblUsers.getSelectionModel().addListSelectionListener(this);
			}
			{
				rowHeaderUsers = tblUsers.getRowHeader();
				scrollUsers.setRowHeaderView(rowHeaderUsers);
				scrollUsers.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
			}
		}
		{
			pnlEast = new JPanel();
			this.add(pnlEast, BorderLayout.EAST);
			pnlEast.setLayout(new BorderLayout());
			pnlEast.setPreferredSize(new Dimension(70, 0));
			{
				JPanel pnlEastNorth = new JPanel();
				pnlEast.add(pnlEastNorth, BorderLayout.NORTH);
				pnlEastNorth.setLayout(new GridLayout(5, 1, 3, 3));
				pnlEastNorth.setPreferredSize(new Dimension(0, 150));
				{
					btnEdit = new JButton("Edit");
					pnlEastNorth.add(btnEdit);
					btnEdit.setEnabled(false);
					btnEdit.addActionListener(this);
				}
			}
		}

		displayUsers();
	}

	private void displayUsers() {
		model.clear(); //Code to clear model.
		DefaultListModel listModel = new DefaultListModel(); //Creating listModel for rowHeader.
		rowHeaderUsers.setModel(listModel); //Setting of listModel into rowHeader.

		String SQL = "SELECT trim(b.emp_code) as \"ID\", trim(a.login_id) as \"User Name\", trim(c.entity_name)::varchar as \"Proper Name\", (CASE WHEN a.status_id = 'A' THEN 'ACTIVE' ELSE 'INACTIVE' END) as \"Status\"--, a.access_level\n" + 
				"FROM rf_system_user a\n" + 
				"LEFT JOIN em_employee b ON b.emp_code = a.emp_code\n" + 
				"LEFT JOIN rf_entity c ON c.entity_id = b.entity_id\n" + 
				"WHERE b.emp_status not in ('I', 'E')\n" + 
				"ORDER BY trim(c.entity_name);";

		//System.out.println(SQL);
		pgSelect db = new pgSelect();
		db.select(SQL);

		if(db.isNotNull()){
			for(int x=0; x < db.getRowCount(); x++){

				//You can only use this kind of adding row in model when you're query and model has the same and exact number of columns and column types.
				model.addRow(db.getResult()[x]);

				try {
					String new_proper_name = ((String) model.getValueAt(x, 2)).toUpperCase();
					model.setValueAt(new_proper_name, x, 2);
				} catch (NullPointerException e) { }

				//For every row added in model, the table header will also add the row number.
				listModel.addElement(model.getRowCount());
			}

			scrollUsers.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblUsers.getRowCount())));
			tblUsers.packAll();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if(arg0.getValueIsAdjusting()){
			btnEdit.setEnabled(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String action = arg0.getActionCommand();
		if(action.equals("Edit")){
			String username = (String) model.getValueAt(tblUsers.convertRowIndexToModel(tblUsers.getSelectedRow()), 1);
			String proper_name = (String) model.getValueAt(tblUsers.convertRowIndexToModel(tblUsers.getSelectedRow()), 2);

			dlgUserPassword dlg_accesspassword = new dlgUserPassword(FncGlobal.homeMDI, String.format("Change User Password - %s", proper_name), ModalityType.APPLICATION_MODAL, username);
			dlg_accesspassword.setLocationRelativeTo(null);
			dlg_accesspassword.setVisible(true);
		}
	}

	class modelUsers extends DefaultTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1913338929974623205L;

		public modelUsers() {
			initThis();
		}

		public modelUsers(boolean editable) {
			initThis();
		}

		public modelUsers(int rowCount, int columnCount) {
			super(rowCount, columnCount);
			initThis();
		}

		public modelUsers(Vector columnNames, int rowCount) {
			super(columnNames, rowCount);
			initThis();
		}

		public modelUsers(Object[] columnNames, int rowCount) {
			super(columnNames, rowCount);
			initThis();
		}

		public modelUsers(Vector data, Vector columnNames) {
			super(data, columnNames);
			initThis();
		}

		public modelUsers(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
			initThis();
		}

		String[] COLUMNS = new String[] {
				"ID",
				"User Name", 
				"Proper Name",
				"Status"
		};

		Class[] CLASS_TYPES = new Class[] {
				String.class,
				Object.class, //No.
				Object.class, //Work Item
				String.class //UOM
		};

		private void initThis() {
			setColumnIdentifiers(COLUMNS);
		}

		public Class getColumnClass(int columnIndex) {
			return CLASS_TYPES[columnIndex];
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		public void clear() {
			FncTables.clearTable(this);
		}
	}

}
