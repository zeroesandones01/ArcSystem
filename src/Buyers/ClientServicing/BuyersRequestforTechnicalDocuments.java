
package Buyers.ClientServicing;

import interfaces._GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTextField;

import tablemodel.modelReleasedDocs;
import tablemodel.modelDoc_ForRelease;
import tablemodel.modelPurpose_ForRelease;
import tablemodel.modelDocPurpose_Request;
import tablemodel.modelRequest_ForRelease;
import tablemodel.modelDocuments_Request;
import Accounting.Disbursements.DocsProcessing;
import Database.pgSelect;
import Database.pgUpdate;
import DateChooser._JDateChooser;
import Functions.FncGlobal;
import Functions.FncReport;
import Functions.FncSystem;
import Functions.FncTables;
import Functions.SpringUtilities;
import Functions.UserInfo;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import Lookup._JLookupTable;
import components.JTBorderFactory;
import components._JInternalFrame;
import components._JTabbedPane;
import components._JTableMain;
import components._JXTextField;

/**
 * @author John Lester Fatallo
 */
public class BuyersRequestforTechnicalDocuments extends _JInternalFrame implements _GUI,
ActionListener {

	private static final long serialVersionUID = 3635607964718046777L;
	private static String title = "Buyers Request for Technical Documents";
	static Dimension SIZE = new Dimension(1000, 550);
	Border lineBorder = BorderFactory.createLineBorder(Color.GRAY);

	private JPanel pnlNorth;

	private JPanel pnlNorthWest;

	private JPanel pnlNorthWestLabel;
	private JLabel lblClient;
	private JLabel lblUnit;
	private JLabel lblRequestNo;
	private JLabel lblReceivedBy;

	private JPanel pnlNorthWestCenter;
	private JPanel pnlClient;
	private _JLookup lookupClient;
	private _JXTextField txtClientName;
	private JPanel pnlUnit;
	private _JXTextField txtUnitID;
	private JTextField txtUnitDesc;
	private _JXTextField txtRequestNo;
	private _JXTextField txtReceivedBy;

	private JPanel pnlNorthEast;
	private JPanel pnlNorthEastLabel;
	private JLabel lblProject;
	private JLabel lblSeqNo;
	private JLabel lblRequestDate;
	private JLabel lblRelation;

	private JPanel pnlNorthEastCenter;
	private JPanel pnlProject;
	private _JXTextField txtProject;
	private _JXTextField txtProjID;

	private JPanel pnlModel;
	private _JXTextField txtSeqNo;
	private JLabel lblModel;
	private _JXTextField txtModel;

	private JPanel pnlRequestDate;
	private _JDateChooser requestdate;
	private _JXTextField txtRelation;
	private JLabel lblRequestStatus;
	private _JXTextField txtReqStat;

	private _JTabbedPane tabCenter;
	private JPanel pnlCenter;
	private JPanel pnlRequestRelease;
	private JLabel lblReqforRelease;
	private JPanel pnlRequestReleaseNorth;
	private JPanel pnlTechDoc;
	private JPanel pnlTechDocNorth;
	private JPanel pnlTechDocNorthLabel;
	private JPanel pnlTechDocNorthComponents;
	private JPanel pnlTechDocCenter;
	private modelDocuments_Request modelDocsRequest;
	private JList rowHeaderDocsRequest;
	private _JTableMain tblDocsRequest;
	private JScrollPane scrollDocsRequest;
	private JLabel lblPurposeofReq;
	private JPanel pnlRequestPurpose;
	private JPanel pnlRequestPurposeNorth;
	private JPanel pnlRequestPurposeNorthLabel;
	private JPanel pnlRequestPurposeNorthComponents;
	private JPanel pnlRequestPurposeCenter;
	private modelDocPurpose_Request modelDocsPurposeReq;
	private JList rowHeaderDocsPurpose_Request;
	private _JTableMain tblDocsPurpose_Request;
	private JScrollPane scrollDocsPurpose_Request;

	private JPanel pnlForRelease;
	private JPanel pnlFRWest;
	private JScrollPane scrollRequest_ForRelease;
	private _JTableMain tblRequest_ForRelease;
	private JList rowHeaderRequest_ForRelease;
	private modelRequest_ForRelease modelRequest_ForRelease;
	
	private JPanel pnlFREast;
	private JPanel pnlFREastUpper;
	private JScrollPane scrollDocs_ForRelease;
	private _JTableMain tblDocs_ForRelease;
	private JList rowHeaderDocs_ForRelease;
	private modelDoc_ForRelease modelDocs_ForRelease;
	
	private JPanel pnlFREastLower;
	private JScrollPane scrollPurpose_ForRelease;
	private _JTableMain tblPurpose_ForRelease;
	private JList rowHeaderPurpose_ForRelease;
	private modelPurpose_ForRelease modelPurpose_Release;
	
	private JPanel pnlCenterEast;
	private JPanel pnlReleasedDocs;
	private modelReleasedDocs modelReleasedDocs;
	private _JTableMain tblReleasedDocs;
	private JScrollPane scrollReleasedDocs;
	private JList rowHeaderReleasedDocs;
	private JPanel pnlRemarks;
	private JScrollPane scrollRemarks;
	private JTextArea txtAreaRemarks;
	private JPanel pnlForReleaseCenter;
	private JPanel pnlForReleaseNorth;

	private JPanel pnlSouth;
	private JButton btnNew;
	private JButton btnUntag;
	private JButton btnSearch;
	private JButton btnSave;
	private JButton btnRelease;
	private JButton btnClear;
	private JButton btnPreview;
	private JButton btnEdit;
	
	private String selected_request_no;
	private boolean active_edit = false;
	private boolean from_for_release = false;

	public BuyersRequestforTechnicalDocuments() {
		super(title, true, true, true, true);
		initGUI();
	}

	public BuyersRequestforTechnicalDocuments(String title) {
		super(title, true, true, true, true);
		initGUI();
	}

	public BuyersRequestforTechnicalDocuments(String entity_id, String entity_name, String proj_id, String proj_name, String unit_id, String unit_desc, String seq_no, String model_desc, Boolean from_card){
		super(title, true, true, true, true);
		initGUI();
		displayDetails(entity_id, entity_name, proj_id, proj_name, unit_id, unit_desc, seq_no, model_desc, from_card);
	}

	@Override
	public void initGUI() {
		this.setSize(SIZE);
		this.setPreferredSize(SIZE);

		JXPanel pnlMain = new JXPanel(new BorderLayout(5, 5));
		getContentPane().add(pnlMain, BorderLayout.CENTER);
		pnlMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pnlMain.setLayout(new SpringLayout());
		{
			pnlNorth = new JPanel(new GridLayout(1, 2, 5, 5));
			pnlMain.add(pnlNorth);
			pnlNorth.setBorder(components.JTBorderFactory.createTitleBorder("Client Details"));
			{
				pnlNorthWest = new JPanel(new BorderLayout(5, 5));
				pnlNorth.add(pnlNorthWest);
				{
					pnlNorthWestLabel = new JPanel(new BorderLayout(5, 5));
					pnlNorthWest.add(pnlNorthWestLabel, BorderLayout.WEST);
					pnlNorthWestLabel.setLayout(new GridLayout(2, 1, 3, 3));
					{
						lblClient = new JLabel("Client");
						pnlNorthWestLabel.add(lblClient);
					}
					{
						lblUnit = new JLabel("Unit");
						pnlNorthWestLabel.add(lblUnit);
					}
					/*{
						lblRequestNo = new JLabel("Request No.");
						pnlNorthWestLabel.add(lblRequestNo);
					}*/
				}
				{
					pnlNorthWestCenter = new JPanel(new GridLayout(2, 1, 3, 3));
					pnlNorthWest.add(pnlNorthWestCenter, BorderLayout.CENTER);
					{
						pnlClient = new JPanel(new BorderLayout(5, 5));
						pnlNorthWestCenter.add(pnlClient, BorderLayout.CENTER);
						{
							lookupClient = new _JLookup(null, "Select Client", 0);
							pnlClient.add(lookupClient, BorderLayout.WEST);
							lookupClient.setLookupSQL(sqlClients());
							lookupClient.setPreferredSize(new Dimension(100, 0));
							lookupClient.setFilterName(true);
							lookupClient.addLookupListener(new LookupListener() {
								public void lookupPerformed(LookupEvent event) {
									Object [] data = ((_JLookup)event.getSource()).getDataSet();

									FncSystem.out("Display sql for lookup of client", lookupClient.getLookupSQL());

									if(data != null){

										String entity_id = (String) data[0];
										String client_name = (String) data[1];
										String unit_id = (String) data[2];
										Integer seq_no = (Integer) data[3];
										String proj_id = (String) data[4];
										String proj_name = (String) data[5];
										String description = (String) data[6];
										String house_model = (String) data[9];

										txtClientName.setText(client_name);
										txtProject.setText(proj_name);
										txtUnitID.setText(unit_id);
										txtUnitDesc.setText(description);
										txtProjID.setText(proj_id);
										txtModel.setText(house_model);
										txtSeqNo.setText(seq_no.toString());

										btnState(true, false, false, false, false, false);
										pnlState(true, true, true);
										
										/*_TechnicalDocumentRequest.displayReleasedDocs(modelReleasedDocs, rowHeaderReleasedDocs, entity_id, unit_id, proj_id, seq_no.toString());
										_TechnicalDocumentRequest.displayDocsRequest(entity_id, proj_id, unit_id, seq_no.toString(), modelTechDoc, rowHeaderTagDocsReq);
										_TechnicalDocumentRequest.displayRequestPurpose(modelReqPurpose, rowHeaderReqPurpose);

										_TechnicalDocumentRequest.displayDocsForRelease(entity_id, proj_id, unit_id, seq_no.toString(), modelDocs_ForRelease, rowHeaderForRelease);

										tblTagDocsReq.packAll();
										tblRequestPurpose.packAll();
										tblForRelease.packAll();
										tblReleasedDocs.packAll();
										scrollTagDocsReq.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblTagDocsReq.getRowCount())));
										scrollReqPurpose.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblRequestPurpose.getRowCount())));
										scrollForRelease.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblForRelease.getRowCount())));
										scrollReleasedDocs.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblReleasedDocs.getRowCount())));*/
									}
								}
							});
						}
						{
							txtClientName = new _JXTextField("Client Name");
							pnlClient.add(txtClientName, BorderLayout.CENTER);
						}
					}
					{
						pnlUnit = new JPanel(new BorderLayout(5, 5));
						pnlNorthWestCenter.add(pnlUnit, BorderLayout.CENTER);
						{
							txtUnitID = new _JXTextField("Unit ID");
							pnlUnit.add(txtUnitID, BorderLayout.WEST);
							txtUnitID.setPreferredSize(new Dimension(100, 0));
							txtUnitID.setHorizontalAlignment(JXTextField.CENTER);
						}
						{
							txtUnitDesc = new _JXTextField("Unit Desc");
							pnlUnit.add(txtUnitDesc, BorderLayout.CENTER);
						}
					}
					/*{
						txtRequestNo = new _JXTextField("Request No.");//XXX TRY TO PUT ME IN THE REQUEST FOR RELEASE TAB IN THE TABCENTER
						pnlNorthWestCenter.add(txtRequestNo, BorderLayout.CENTER);
					}*/
				}
			}
			{
				pnlNorthEast = new JPanel(new BorderLayout(5, 5));
				pnlNorth.add(pnlNorthEast);
				{
					pnlNorthEastLabel = new JPanel(new BorderLayout(5, 5));
					pnlNorthEast.add(pnlNorthEastLabel, BorderLayout.WEST);
					pnlNorthEastLabel.setLayout(new GridLayout(2, 1, 3, 3));
					{
						lblProject = new JLabel("Project");
						pnlNorthEastLabel.add(lblProject);
					}
					{
						lblSeqNo = new JLabel("Seq. No");
						pnlNorthEastLabel.add(lblSeqNo);
					}
					/*{
						lblRequestDate = new JLabel("Request Date");
						pnlNorthEastLabel.add(lblRequestDate);
					}*/
				}
				{
					pnlNorthEastCenter = new JPanel(new GridLayout(2, 1, 3, 3));
					pnlNorthEast.add(pnlNorthEastCenter, BorderLayout.CENTER);
					{
						pnlProject = new JPanel(new BorderLayout(5, 5));
						pnlNorthEastCenter.add(pnlProject, BorderLayout.CENTER);
						{
							txtProjID = new _JXTextField("ID");
							pnlProject.add(txtProjID, BorderLayout.WEST);
							txtProjID.setPreferredSize(new Dimension(50, 0));;
							txtProjID.setHorizontalAlignment(JXTextField.CENTER);
						}
						{
							txtProject = new _JXTextField("Project Name");
							pnlProject.add(txtProject, BorderLayout.CENTER);
						}
					}
					{
						pnlModel = new JPanel(new BorderLayout(5, 5));
						pnlNorthEastCenter.add(pnlModel, BorderLayout.CENTER);
						{
							txtSeqNo = new _JXTextField("Seq.");
							pnlModel.add(txtSeqNo, BorderLayout.WEST);
							txtSeqNo.setPreferredSize(new Dimension(50, 0));
							txtSeqNo.setHorizontalAlignment(JXTextField.CENTER);
						}
						{
							lblModel = new JLabel("Model", JLabel.TRAILING);
							pnlModel.add(lblModel);
						}
						{
							txtModel = new _JXTextField("House Model");
							pnlModel.add(txtModel, BorderLayout.EAST);
							txtModel.setPreferredSize(new Dimension(250, 0));
						}
					}
				}
			}
		}
		{
			pnlCenter = new JPanel(new BorderLayout(3, 3));
			pnlMain.add(pnlCenter);
			//pnlCenter.setBorder(lineBorder);
			//pnlCenter.setLayout(new SpringLayout());
			{
				tabCenter = new _JTabbedPane();
				//ADDED BY JARI CRUZ ASOF FEB 16 2023
				tabCenter.addChangeListener(new ChangeListener() {
					
					@Override
					public void stateChanged(ChangeEvent e) {
						// TODO Auto-generated method stub
						if(tabCenter.getTitleAt(tabCenter.getSelectedIndex()).equals("Request for Release") && from_for_release) {
							btnEdit.setEnabled(false);
						}
					}
				});
				pnlCenter.add(tabCenter, BorderLayout.CENTER);
				{
					pnlRequestRelease = new JPanel(new GridLayout(2, 1, 3, 3));
					tabCenter.add("Request for Release", pnlRequestRelease);
					{
						pnlRequestReleaseNorth = new JPanel(new GridLayout(1, 2, 2, 2));
						pnlRequestRelease.add(pnlRequestReleaseNorth);
						{
							pnlTechDoc = new JPanel(new BorderLayout(5, 5));
							pnlRequestReleaseNorth.add(pnlTechDoc);
							/*{
								pnlTechDocNorth = new JPanel(new BorderLayout(3, 3));
								pnlTechDoc.add(pnlTechDocNorth, BorderLayout.NORTH);
								pnlTechDocNorth.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
								{
									pnlTechDocNorthLabel = new JPanel(new GridLayout(1, 1, 3, 3));
									pnlTechDocNorth.add(pnlTechDocNorthLabel, BorderLayout.WEST);
									{
										lblRequestNo = new JLabel("Request No");
										pnlTechDocNorthLabel.add(lblRequestNo);
									}
									{
										lblReceivedBy = new JLabel("Received By");
										pnlTechDocNorthLabel.add(lblReceivedBy);
									}
								}
								{
									pnlTechDocNorthComponents = new JPanel(new GridLayout(1, 1, 3, 3));
									pnlTechDocNorth.add(pnlTechDocNorthComponents, BorderLayout.CENTER);
									{
										txtRequestNo = new _JXTextField();
										pnlTechDocNorthComponents.add(txtRequestNo);
									}
									{
										txtReceivedBy = new _JXTextField();
										pnlTechDocNorthComponents.add(txtReceivedBy);
									}
								}
							}*/
							{
								pnlTechDocCenter = new JPanel(new BorderLayout(3, 3));
								pnlTechDoc.add(pnlTechDocCenter, BorderLayout.CENTER);
								pnlTechDocCenter.setBorder(components.JTBorderFactory.createTitleBorder("Documents"));
								{
									scrollDocsRequest = new JScrollPane();
									pnlTechDocCenter.add(scrollDocsRequest, BorderLayout.CENTER);
									scrollDocsRequest.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
									{
										modelDocsRequest = new modelDocuments_Request();
										tblDocsRequest = new _JTableMain(modelDocsRequest);
										scrollDocsRequest.setViewportView(tblDocsRequest);
										tblDocsRequest.getColumnModel().getColumn(0).setPreferredWidth(50); //Select *
										tblDocsRequest.getColumnModel().getColumn(1).setPreferredWidth(35); //ID
										tblDocsRequest.getColumnModel().getColumn(2).setPreferredWidth(100); //Doc Alias
										tblDocsRequest.getColumnModel().getColumn(3).setPreferredWidth(50); //Doc Name
										tblDocsRequest.getColumnModel().getColumn(4).setPreferredWidth(50); //Dept Alias

										tblDocsRequest.hideColumns("ID");
										//COMMENTED BY JOHN LESTER FATALLO 
										modelDocsRequest.addTableModelListener(new TableModelListener() {

											public void tableChanged(TableModelEvent arg0) {

												if(modelDocsRequest.getRowCount() == 0){
													rowHeaderDocsRequest.setModel(new DefaultListModel());
												}

												/*if(modelDocsRequest != null){

													if(getSelectedDocuments() != 0){
														if(checkDocuments() && _TechnicalDocumentRequest.checkDept() || UserInfo.ADMIN){//FOR RELEASING OF TCT CHECK THE DEPT OF THE LOGGED USER
															btnState(false, false, false, true, true, true);
														}else if(_TechnicalDocumentRequest.checkDept()){//IF DOCUMENTS IS NOT TCT
															btnState(false, false, false, true, true, true);
														}else{
															btnState(false, false, false, false, true, true);
														}
													}
													if(getSelectedDocuments() == 0){
														//btnState(true, true, false, false, false, false);
														btnState(false, false, false, false, true, false);
													}
												}*/
											}
										});
									}
									{
										rowHeaderDocsRequest = tblDocsRequest.getRowHeader();
										rowHeaderDocsRequest.setModel(new DefaultListModel());
										scrollDocsRequest.setRowHeaderView(rowHeaderDocsRequest);
										scrollDocsRequest.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
									}
								}
							}
						}
						{
							pnlRequestPurpose = new JPanel(new BorderLayout(5, 5));
							pnlRequestReleaseNorth.add(pnlRequestPurpose);
							/*{
								pnlRequestPurposeNorth = new JPanel(new BorderLayout(3, 3));
								pnlRequestPurpose.add(pnlRequestPurposeNorth, BorderLayout.NORTH);
								pnlRequestPurposeNorth.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
								{
									pnlRequestPurposeNorthLabel = new JPanel(new GridLayout(1, 1, 3, 3));
									pnlRequestPurposeNorth.add(pnlRequestPurposeNorthLabel, BorderLayout.WEST);
									{
										lblRequestDate = new JLabel("Request Date");
										pnlRequestPurposeNorthLabel.add(lblRequestDate);
									}
									{
										lblRelation = new JLabel("Relation to Principal Applicant");
										pnlRequestPurposeNorthLabel.add(lblRelation);
									}
								}
								{
									pnlRequestPurposeNorthComponents = new JPanel(new GridLayout(1, 1, 3, 3));
									pnlRequestPurposeNorth.add(pnlRequestPurposeNorthComponents, BorderLayout.CENTER);
									{
										requestdate = new _JDateChooser("MM/dd/yy", "##/##/##", '_');
										pnlRequestPurposeNorthComponents.add(requestdate, BorderLayout.CENTER);
										requestdate.getCalendarButton().setVisible(false);
										requestdate.setEditable(false);
									}
									{
										txtRelation = new _JXTextField();
										pnlRequestPurposeNorthComponents.add(txtRelation);
									}
								}
							}*/
							{
								pnlRequestPurposeCenter = new JPanel(new BorderLayout(3, 3));
								pnlRequestPurpose.add(pnlRequestPurposeCenter, BorderLayout.CENTER);
								pnlRequestPurposeCenter.setBorder(components.JTBorderFactory.createTitleBorder("Purpose of Request"));
								{
									scrollDocsPurpose_Request = new JScrollPane();
									pnlRequestPurposeCenter.add(scrollDocsPurpose_Request, BorderLayout.CENTER);
									scrollDocsPurpose_Request.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
									{
										modelDocsPurposeReq = new modelDocPurpose_Request();
										tblDocsPurpose_Request = new _JTableMain(modelDocsPurposeReq);
										scrollDocsPurpose_Request.setViewportView(tblDocsPurpose_Request);
										
										tblDocsPurpose_Request.getColumnModel().getColumn(0).setPreferredWidth(50);
										tblDocsPurpose_Request.getColumnModel().getColumn(1).setPreferredWidth(35);
										tblDocsPurpose_Request.getColumnModel().getColumn(2).setPreferredWidth(200);
										tblDocsPurpose_Request.hideColumns("ID");
									}
									{
										rowHeaderDocsPurpose_Request = tblDocsPurpose_Request.getRowHeader();
										rowHeaderDocsPurpose_Request.setModel(new DefaultListModel());
										scrollDocsPurpose_Request.setRowHeaderView(rowHeaderDocsPurpose_Request);
										scrollDocsPurpose_Request.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
									}
								}
							}
						}
					}
					{
						pnlRemarks = new JPanel(new BorderLayout(3, 3));
						pnlRequestRelease.add(pnlRemarks);
						pnlRemarks.setBorder(components.JTBorderFactory.createTitleBorder("Remarks"));
						{
							scrollRemarks = new JScrollPane();
							pnlRemarks.add(scrollRemarks);
							{
								txtAreaRemarks = new JTextArea();
								scrollRemarks.setViewportView(txtAreaRemarks);
								txtAreaRemarks.setLineWrap(true);
							}
						}
					}
				}
				{
					pnlForRelease = new JPanel(new BorderLayout(3, 3));
					tabCenter.add("For Release", pnlForRelease);
					{
						pnlForReleaseNorth = new JPanel(new GridLayout(1, 2, 3, 3));
						pnlForRelease.add(pnlForReleaseNorth, BorderLayout.NORTH);
						{
							JPanel pnlReceivedBy = new JPanel(new BorderLayout(3, 3));
							pnlForReleaseNorth.add(pnlReceivedBy);
							{
								lblReceivedBy = new JLabel("Received By");
								pnlReceivedBy.add(lblReceivedBy, BorderLayout.WEST);
							}
							{
								txtReceivedBy = new _JXTextField();
								pnlReceivedBy.add(txtReceivedBy, BorderLayout.CENTER);
							}
						}
						{
							JPanel pnlRelation = new JPanel(new BorderLayout(3, 3));
							pnlForReleaseNorth.add(pnlRelation);
							{
								lblRelation = new JLabel("Relation to Principal Applicant");
								pnlRelation.add(lblRelation, BorderLayout.WEST);
							}
							{
								txtRelation = new _JXTextField();
								pnlRelation.add(txtRelation, BorderLayout.CENTER);
							}
						}
					}
					{
						pnlForReleaseCenter = new JPanel(new GridLayout(1, 2, 3, 3));
						pnlForRelease.add(pnlForReleaseCenter, BorderLayout.CENTER);
						{
							pnlFRWest = new JPanel(new BorderLayout(3, 3));
							pnlForReleaseCenter.add(pnlFRWest, BorderLayout.WEST);
							pnlFRWest.setBorder(JTBorderFactory.createTitleBorder("Request Details"));
							//pnlFRWest.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
							{
								scrollRequest_ForRelease = new JScrollPane();
								pnlFRWest.add(scrollRequest_ForRelease, BorderLayout.CENTER);
								scrollRequest_ForRelease.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
								{
									modelRequest_ForRelease = new modelRequest_ForRelease();
									tblRequest_ForRelease = new _JTableMain(modelRequest_ForRelease);
									scrollRequest_ForRelease.setViewportView(tblRequest_ForRelease);
									tblRequest_ForRelease.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
									
									modelRequest_ForRelease.addTableModelListener(new TableModelListener() {
										
										@Override
										public void tableChanged(TableModelEvent e) {
											
											if(modelRequest_ForRelease.getRowCount() == 0){
												rowHeaderRequest_ForRelease.setModel(new DefaultListModel());
											}
										}
									});
									
									tblRequest_ForRelease.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
										
										@Override
										public void valueChanged(ListSelectionEvent e) {
											
											if(!e.getValueIsAdjusting()){
												if(tblRequest_ForRelease.getSelectedRows().length == 1){
													int selected_row = tblRequest_ForRelease.convertRowIndexToModel(tblRequest_ForRelease.getSelectedRow());
													
													txtReceivedBy.setText("");
													txtRelation.setText("");
													txtReceivedBy.setEditable(true);
													txtRelation.setEditable(true);
													
													
													String request_no = (String) modelRequest_ForRelease.getValueAt(selected_row, 0);
													
													_TechnicalDocumentRequest.displayDocsForRelease(request_no, modelDocs_ForRelease, rowHeaderDocs_ForRelease);
													scrollDocs_ForRelease.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblDocs_ForRelease.getRowCount())));
													tblDocs_ForRelease.packAll();
													
													System.out.println("displayRequestPurposeDocuments");
													_TechnicalDocumentRequest.displayRequestPurposeDocuments(request_no, modelPurpose_Release, rowHeaderPurpose_ForRelease);
													scrollPurpose_ForRelease.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblPurpose_ForRelease.getRowCount())));
													tblPurpose_ForRelease.packAll();
													tblPurpose_ForRelease.setEditable(false);
													
													//ADDED BY JARI CRUZ ASOF FEB 7 2023
													btnEdit.setEnabled(true);
													from_for_release = true;
												}
											}
										}
									});
								}
								{
									rowHeaderRequest_ForRelease = tblRequest_ForRelease.getRowHeader();
									rowHeaderRequest_ForRelease.setModel(new DefaultListModel());
									scrollRequest_ForRelease.setRowHeaderView(rowHeaderRequest_ForRelease);
									scrollRequest_ForRelease.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
								}
							}
						}
						{
							pnlFREast = new JPanel(new GridLayout(2, 1, 3, 3));
							pnlForReleaseCenter.add(pnlFREast, BorderLayout.EAST);
							pnlFREast.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
							//pnlFREast.setBorder(JTBorderFactory.createTitleBorder("Purpose of Request"));
							{
								pnlFREastUpper = new JPanel(new BorderLayout(3, 3));
								pnlFREast.add(pnlFREastUpper, BorderLayout.NORTH);
								pnlFREastUpper.setBorder(JTBorderFactory.createTitleBorder("Requested Documents"));
								{
									scrollDocs_ForRelease = new JScrollPane();
									pnlFREastUpper.add(scrollDocs_ForRelease);
									scrollDocs_ForRelease.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
									{
										modelDocs_ForRelease = new modelDoc_ForRelease();
										tblDocs_ForRelease = new _JTableMain(modelDocs_ForRelease);
										scrollDocs_ForRelease.setViewportView(tblDocs_ForRelease);
										tblDocs_ForRelease.hideColumns("ID", "Dept. Alias" ,"Rec. ID", "Request No");
										tblDocs_ForRelease.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

										modelDocs_ForRelease.addTableModelListener(new TableModelListener() {

											@Override
											public void tableChanged(TableModelEvent e) {

												if(modelDocs_ForRelease.getRowCount() == 0){
													rowHeaderDocs_ForRelease.setModel(new DefaultListModel());
												}
											}
										});

										tblDocs_ForRelease.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

											@Override
											public void valueChanged(ListSelectionEvent e) {

												if(e.getValueIsAdjusting()){
													if(tblDocs_ForRelease.getSelectedRows().length == 1){
														btnState(false, true, false, true, false, true);
													}
												}
											}
										});
									}
									{
										rowHeaderDocs_ForRelease = tblDocs_ForRelease.getRowHeader();
										rowHeaderDocs_ForRelease.setModel(new DefaultListModel());
										scrollDocs_ForRelease.setRowHeaderView(rowHeaderDocs_ForRelease);
										scrollDocs_ForRelease.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
									}
								}
								
							}
							{
								pnlFREastLower = new JPanel(new BorderLayout(3, 3));
								pnlFREast.add(pnlFREastLower, BorderLayout.SOUTH);
								pnlFREastLower.setBorder(JTBorderFactory.createTitleBorder("Document Purpose"));
								{
									scrollPurpose_ForRelease = new JScrollPane();
									pnlFREastLower.add(scrollPurpose_ForRelease, BorderLayout.CENTER);
									scrollPurpose_ForRelease.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
									{
										modelPurpose_Release = new modelPurpose_ForRelease();
										tblPurpose_ForRelease = new _JTableMain(modelPurpose_Release);
										scrollPurpose_ForRelease.setViewportView(tblPurpose_ForRelease);
										tblPurpose_ForRelease.hideColumns("ID");
									}
									{
										rowHeaderPurpose_ForRelease = tblPurpose_ForRelease.getRowHeader();
										rowHeaderPurpose_ForRelease.setModel(new DefaultListModel());
										scrollPurpose_ForRelease.setRowHeaderView(rowHeaderPurpose_ForRelease);
										scrollPurpose_ForRelease.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
									}
								}
							}
						}
					}
					
				}
				{
					pnlReleasedDocs = new JPanel(new BorderLayout(5, 5));
					tabCenter.add("Released Documents", pnlReleasedDocs);
					{
						scrollReleasedDocs = new JScrollPane();
						pnlReleasedDocs.add(scrollReleasedDocs);
						scrollReleasedDocs.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						{
							modelReleasedDocs = new modelReleasedDocs();
							tblReleasedDocs = new _JTableMain(modelReleasedDocs);
							scrollReleasedDocs.setViewportView(tblReleasedDocs);

							tblReleasedDocs.hideColumns("ID");

							modelReleasedDocs.addTableModelListener(new TableModelListener() {

								@Override
								public void tableChanged(TableModelEvent arg0) {
									/*if(arg0.getType() == TableModelEvent.INSERT){
										((DefaultListModel)rowHeaderReleasedDocs.getModel()).addElement(modelReleasedDocs.getRowCount());
									}*/

									if(modelReleasedDocs.getRowCount() == 0){
										rowHeaderReleasedDocs.setModel(new DefaultListModel());
									}
								}
							});
						}
						{
							rowHeaderReleasedDocs = tblReleasedDocs.getRowHeader();
							rowHeaderReleasedDocs.setModel(new DefaultListModel());
							scrollReleasedDocs.setRowHeaderView(rowHeaderReleasedDocs);
							scrollReleasedDocs.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, FncTables.getRowHeader_Header());
						}
					}
				}
				tabCenter.addChangeListener(new ChangeListener() {

					@Override
					public void stateChanged(ChangeEvent e) {
						int selected_tab = ((_JTabbedPane) e.getSource()).getSelectedIndex();

						if(selected_tab == 0){
							/*lookupClient.setEditable(true);
							btnState(false, false, false, false, false, false);*/
							
							if(lookupClient.getValue()!= null){
								lookupClient.setEditable(true);
								btnState(true, false, false, false, true, false);
							}
						}

						if(selected_tab == 1){
							if(lookupClient.getValue() != null){
								lookupClient.setEditable(false);
								txtReceivedBy.setText("");
								txtRelation.setText("");
								displayForReleaseTab();
								
								btnState(false, false, false, true, false, true);
							}
						}

						if(selected_tab == 2){
							if(lookupClient.getValue() != null){
								lookupClient.setEditable(false);
								_TechnicalDocumentRequest.displayReleasedDocs(modelReleasedDocs, rowHeaderReleasedDocs, lookupClient.getValue(), txtUnitID.getText(), txtProjID.getText(), txtSeqNo.getText());
								scrollReleasedDocs.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblReleasedDocs.getRowCount())));
								tblReleasedDocs.packAll();
								btnState(false, false, false, false, false, true);
							}
						}
					}
				});
			}
		}
		{
			pnlSouth = new JPanel(new BorderLayout(5, 5));
			pnlMain.add(pnlSouth);
			pnlSouth.setLayout(new GridLayout(1, 7));
			{
				btnNew = new JButton("New");
				pnlSouth.add(btnNew);
				btnNew.setActionCommand(btnNew.getText());
				btnNew.setMnemonic(KeyEvent.VK_N);
				btnNew.addActionListener(this);
				
			}
			//ADDED BY JARI CRUZ ASOF FEB 7 2023; EDIT REMARKS
			{
				btnEdit = new JButton("Edit");
				pnlSouth.add(btnEdit);
				btnEdit.setActionCommand(btnEdit.getText());
				btnEdit.setMnemonic(KeyEvent.VK_N);
				btnEdit.addActionListener(this);
				btnEdit.setEnabled(false);
			}
			/*{
				btnSearch = new JButton("Search");
				pnlSouth.add(btnSearch);
				btnSearch.setActionCommand(btnSearch.getText());
				btnSearch.setMnemonic(KeyEvent.VK_S);
				btnSearch.addActionListener(this);
			}*/
			{
				btnUntag = new JButton("Untag Document");
				pnlSouth.add(btnUntag);
				btnUntag.setActionCommand(btnUntag.getText());
				btnUntag.setMnemonic(KeyEvent.VK_U);
				btnUntag.addActionListener(this);
			}
			{
				btnSave = new JButton("Save");
				pnlSouth.add(btnSave);
				btnSave.setActionCommand(btnSave.getText());
				btnSave.setMnemonic(KeyEvent.VK_V);
				btnSave.addActionListener(this);
			}
			{
				btnRelease = new JButton("Release");
				pnlSouth.add(btnRelease);
				btnRelease.setActionCommand(btnRelease.getText());
				btnRelease.setMnemonic(KeyEvent.VK_R);
				btnRelease.addActionListener(this);
			}
			{
				btnClear = new JButton("Clear");
				pnlSouth.add(btnClear);
				btnClear.setActionCommand(btnClear.getText());
				btnClear.setMnemonic(KeyEvent.VK_E);
				btnClear.addActionListener(this);
			}
			{
				btnPreview = new JButton("Preview");
				pnlSouth.add(btnPreview);
				btnPreview.setActionCommand(btnPreview.getText());
				btnPreview.setMnemonic(KeyEvent.VK_V);
				btnPreview.addActionListener(this);
			}
		}
		SpringUtilities.makeCompactGrid(pnlMain, 3, 1, 3, 3, 3, 3, false);
		clearReqTechDoc();
	}//END OF INIT GUI

	public void btnState(Boolean sNew, Boolean sUntag, Boolean sSave, Boolean sRelease, Boolean sClear, Boolean sPreview){
		btnNew.setEnabled(sNew);
		//btnSearch.setEnabled(sSearch);
		btnUntag.setEnabled(sUntag);
		btnSave.setEnabled(sSave);
		btnRelease.setEnabled(sRelease);
		btnClear.setEnabled(sClear);
		btnPreview.setEnabled(sPreview);
	}
	
	private void pnlState(Boolean sRequest, Boolean sForRelease, Boolean sReleased){
		
		tabCenter.setEnabledAt(0, sRequest);
		tabCenter.setEnabledAt(1, sForRelease);
		tabCenter.setEnabledAt(2, sReleased);
		
	}

	public void newReqTechDoc(){//NEW TECHNICAL DOCUMENTS
		//lookupClient.setEditable(true);

		this.setComponentsEnabled(pnlCenter, true);
		//requestdate.setEditable(false);
		scrollDocsPurpose_Request.setEnabled(false);
		tblDocsPurpose_Request.setEnabled(true);
		tblDocsPurpose_Request.setEditable(true);
		txtAreaRemarks.setEditable(true);
		/*txtReceivedBy.setEditable(true);
		txtRelation.setEditable(true);*/
		
		/*lblReceivedBy.setText("*Received by");
		lblRelation.setText("*Relation to Principal Applicant");*/

		_TechnicalDocumentRequest.displayDocsRequest(lookupClient.getValue(), txtProjID.getText(), txtUnitID.getText(), txtSeqNo.getText(), modelDocsRequest, rowHeaderDocsRequest);
		scrollDocsRequest.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblDocsRequest.getRowCount())));
		tblDocsRequest.packAll();

		_TechnicalDocumentRequest.displayRequestPurpose(modelDocsPurposeReq, rowHeaderDocsPurpose_Request);
		scrollDocsPurpose_Request.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblDocsPurpose_Request.getRowCount())));
		tblDocsPurpose_Request.packAll();
		
		pnlState(true, false, false);
		
		if(_TechnicalDocumentRequest.checkDept() || UserInfo.ADMIN){
			btnState(false, false, true, false, true, false);
		}
	}

	public void clearReqTechDoc(){
		this.setComponentsEditable(pnlNorth, false);
		this.setComponentsEnabled(pnlCenter, false);
		btnState(false, false, false, false, false, false);
		lookupClient.setEditable(true);
		lookupClient.setValue(null);
		txtClientName.setText("");
		txtUnitID.setText("");
		txtUnitDesc.setText("");
		txtProjID.setText("");
		txtProject.setText("");
		txtSeqNo.setText("");
		txtModel.setText("");

		txtAreaRemarks.setText("");
		txtAreaRemarks.setEditable(false);
		txtReceivedBy.setText("");
		txtRelation.setText("");
		txtReceivedBy.setEditable(false);
		txtRelation.setEditable(false);

		scrollDocsRequest.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(""));
		rowHeaderDocsRequest = tblDocsRequest.getRowHeader();
		scrollDocsRequest.setRowHeaderView(rowHeaderDocsRequest);

		scrollDocsPurpose_Request.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(""));
		rowHeaderDocsPurpose_Request = tblDocsPurpose_Request.getRowHeader();
		scrollDocsPurpose_Request.setRowHeaderView(rowHeaderDocsPurpose_Request);

		scrollReleasedDocs.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(""));
		rowHeaderReleasedDocs = tblReleasedDocs.getRowHeader();
		scrollReleasedDocs.setRowHeaderView(rowHeaderReleasedDocs);

		modelDocsRequest.clear();
		modelDocsPurposeReq.clear();
		modelReleasedDocs.clear();
		
		btnEdit.setEnabled(false);
		pnlState(false, false, false);
		
	}

	public boolean checkRequiredFields(){//CHECKS FOR THE REQUIRED FIELDS BEFORE SAVING
		String required_fields = "";
		Boolean toSave = true;
		/*if(txtReceivedBy.getText().isEmpty()){
			required_fields = required_fields + "Received By \n";
			toSave = false;
		}
		
		if(txtRelation.getText().isEmpty()){
			required_fields = required_fields + "Relation to Principal Applicant";
			toSave = false;
		}*/
		
		if(getSelectedDocuments() == 0){
			required_fields = required_fields + "Documents \n";
			toSave = false;
		}

		if(getSelectedPurpose() == 0){
			required_fields = required_fields + "Purpose of Request\n";
			toSave = false;
		}

		if(txtAreaRemarks.getText().isEmpty() || txtAreaRemarks.getText().equals("")){
			required_fields = required_fields + "Remarks\n";
			toSave = false;
		}
		
		if(active_edit) {
			toSave = true;
		}

		if(toSave == false){
			JOptionPane.showMessageDialog(null, "Please fill out all required fields:\n"+required_fields,"Save",JOptionPane.WARNING_MESSAGE);
			return false;
		}

		return true;
	}

	private String sqlClients(){//SQL FOR THE LOOKUP IN THE CLIENT ID

		return  "select trim(a.entity_id) as \"Entity ID\", trim(get_client_name(a.entity_id)) as  \"Name\", \n" + 
		"trim(b.unit_id) as \"Unit ID\", a.seq_no as \"Seq. No.\", \n" + 
		"trim(a.projcode) as \"Proj. ID\", trim(d.proj_name) as \"Project Name\", \n" + 
		"trim(b.description) as \"Description\", trim(c.status_desc) as \"Unit Status\", \n" + 
		"trim(e.pmt_scheme_desc) as \"Payment Scheme\", trim(f.model_desc) as \"Model\"\n" + 
		"from rf_sold_unit a\n" + 
		"left join mf_unit_info b on b.proj_id = a.projcode and b.pbl_id = a.pbl_id\n" + 
		"left join mf_buyer_status c on c.byrstatus_id = a.currentstatus AND COALESCE(c.server_id, '') = coalesce(a.server_id, '')\n" + 
		"left join mf_project d on d.proj_id = a.projcode\n" + 
		"left join mf_payment_scheme e on e.pmt_scheme_id = a.pmt_scheme_id\n" + 
		"left join mf_product_model f on f.model_id = a.model_id and coalesce(f.server_id, '') = COALESCE(a.server_id, '') and coalesce(f.proj_server, '') = coalesce(a.proj_server, '')\n" + 
		"where c.status_desc != 'Cancelled' \n";
		//"and get_group_id(a.buyertype) != '04'";
	}

	private String sqlSearch(){//SQL FOR THE SEARCH BUTTON

		return "select a.request_no as \"Request No.\",\n" + 
		"trim(a.entity_id) as \"Entity ID\", trim(get_client_name(a.entity_id)) as \"Name\", \n" + 
		"trim(a.unit_id) as \"Unit ID\", a.seq_no as \"Seq. No\", \n" + 
		"trim(a.projcode) as \"Proj. ID\",  trim(d.proj_name) as \"Project Name\" , \n" + 
		"trim(b.description) as \"Description\", trim(c.req_status_desc) as \"Status\",\n" + 
		"trim(f.status_desc) as \"Unit Status\", trim(g.model_desc) as \"Model\" ,\n" + 
		"a.request_date as \"Request Date\"  --,trim(a.remarks) as \"Remarks\" \n" + 
		"from rf_buyer_docs_hd a\n" + 
		"left join mf_unit_info b on b.unit_id = a.unit_id and b.proj_id = a.projcode\n" + 
		"left join mf_request_status c on c.req_status_id = a.status_id\n" + 
		"left join mf_project d on d.proj_id = a.projcode\n" + 
		"left join rf_sold_unit e on e.entity_id = a.entity_id and e.pbl_id = a.pbl_id and e.projcode = a.projcode and e.seq_no = a.seq_no\n" + 
		"left join mf_buyer_status f on f.byrstatus_id = e.currentstatus\n" + 
		"left join mf_product_model g on g.model_id = e.model_id \n" + 
		"where a.status_id = 'S' \n" + 
		"order by a.request_no desc";
	}

	public String getRequestNo(){//Generates new request_no
		String req_no = "select substring (extract(year from current_date)::text from 3 for 2)|| \n" + 
				"trim(to_char(extract(month from current_date),'00'))||trim(to_char(extract(day from current_date),'00'))||'-'||\n" + 
				"trim(to_char(count(request_no)+1,'00000')) from req_rt_request_header where request_date::date  = current_date";

		return req_no;
	}
	
	private void displayForReleaseTab(){
		txtReceivedBy.setText("");
		txtRelation.setText("");
		
		_TechnicalDocumentRequest.displayTechDocsRequest(lookupClient.getValue(), txtProjID.getText(), txtUnitID.getText(), txtSeqNo.getText(), modelRequest_ForRelease, rowHeaderRequest_ForRelease);
		scrollRequest_ForRelease.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblRequest_ForRelease.getRowCount())));
		tblRequest_ForRelease.packAll();
		
		modelDocs_ForRelease.clear();
		scrollDocs_ForRelease.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(""));
		rowHeaderDocs_ForRelease.setModel(new DefaultListModel());
		
		modelPurpose_Release.clear();
		scrollPurpose_ForRelease.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(""));
		rowHeaderPurpose_ForRelease.setModel(new DefaultListModel());
		
	}

	public int getSelectedDocuments() {
		int count = 0;
		try {
			for (int x  = 0;x<tblDocsRequest.getRowCount();x++){
				if (tblDocsRequest.getValueAt(x, 0).toString().equals("true")){
					count++;
				}
			}		
		} catch (ArrayIndexOutOfBoundsException e) { }
		return count;
	}

	public int getSelectedPurpose(){
		int count = 0;
		try{
			for (int x= 0; x<tblDocsPurpose_Request.getRowCount(); x++){
				if(tblDocsPurpose_Request.getValueAt(x, 0).toString().equals("true")){
					count ++;
				}
			}
		}catch (ArrayIndexOutOfBoundsException e) {}
		return count;
	}
	
	private int getSelectedDocs_ForRelease(){
		int count = 0;
		
		try{
		for(int x= 0; x<tblDocs_ForRelease.getRowCount(); x++){
			if(tblDocs_ForRelease.getValueAt(x, 0).toString().equals("true")){
				count ++;
			}
		}
		}catch (ArrayIndexOutOfBoundsException e){}
		return count;
	}

	public boolean checkDocuments(){

		for (int x=0; x<tblDocsPurpose_Request.getRowCount(); x++){
			Boolean isSelected = (Boolean) tblDocsPurpose_Request.getValueAt(x, 0);

			if(isSelected){
				if(tblDocsPurpose_Request.getValueAt(x, 1).toString().equals("02")){
					System.out.println("Dumaan dito");
					return true;
				}
			}
		}
		return false;
	}
	
	

	public void displayDetails(String entity_id, String entity_name , String proj_id, String proj_name , String pbl_id, String unit_desc, String seq_no, String model_desc, Boolean from_card){
		pgSelect db = new pgSelect();

		String sql = "select trim(unit_id) from mf_unit_info where pbl_id = '"+pbl_id+"' and proj_id = '"+proj_id+"'";
		db.select(sql);

		String unit_id = (String) db.getResult()[0][0];

		lookupClient.setValue(entity_id);
		txtClientName.setText(entity_name);
		txtProjID.setText(proj_id);
		txtProject.setText(proj_name);
		txtUnitID.setText(unit_id);
		txtUnitDesc.setText(unit_desc);
		txtSeqNo.setText(seq_no);
		txtModel.setText(model_desc);

		newReqTechDoc();

		_TechnicalDocumentRequest.displayDocsRequest(entity_id, proj_id, unit_id, seq_no, modelDocsRequest, rowHeaderDocsRequest);
		
		_TechnicalDocumentRequest.displayRequestPurpose(modelDocsPurposeReq, rowHeaderDocsPurpose_Request);

		tblDocsRequest.packAll();
		tblDocsPurpose_Request.packAll();

		scrollDocsRequest.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblDocsRequest.getRowCount())));
		scrollDocsPurpose_Request.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblDocsPurpose_Request.getRowCount())));

		if(from_card){
			pnlState(true, true, true);
			tabCenter.setSelectedIndex(1);
			_TechnicalDocumentRequest.displayReleasedDocs(modelReleasedDocs, rowHeaderReleasedDocs, entity_id, unit_id, proj_id, seq_no.toString());
			tblReleasedDocs.packAll();	
			scrollReleasedDocs.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblReleasedDocs.getRowCount())));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		if(actionCommand.equals("New")){//Execute if new button is clicked
			newReqTechDoc();
		}
		//ADDED BY JARI CRUZ ASOF FEB 7 2023; EDIT REMARKS
		if(actionCommand.equals("Edit")){//Execute if Edit button is clicked
			int selected_row = tblRequest_ForRelease.getSelectedRow();
			String selected_remarks = (String) tblRequest_ForRelease.getValueAt(selected_row, 2);
			selected_request_no = (String) tblRequest_ForRelease.getValueAt(selected_row, 0);
			if(checkWithinTheDay(selected_request_no)) {
				if (tabCenter.getTitleAt(tabCenter.getSelectedIndex()).equals("For Release")) {
					txtAreaRemarks.setText(selected_remarks);
					txtAreaRemarks.setEditable(true);
					tabCenter.setSelectedIndex(0);
					tabCenter.setEnabledAt(1, false);
					tabCenter.setEnabledAt(2, false);
					btnNew.setEnabled(false);
					btnEdit.setEnabled(false);
					btnSave.setEnabled(true);
					active_edit = true;
				}
			} else {
				System.out.println("selected_request_no: "+selected_request_no);
				JOptionPane.showMessageDialog(null, "Remarks can only be edited within the day");
			}
		}
		
		if(actionCommand.equals("Untag Document")){
			if(modelDocs_ForRelease.getRowCount() == 1){
				JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "This Request has only 1 document", actionCommand, JOptionPane.WARNING_MESSAGE);
			}else{
				if(JOptionPane.showConfirmDialog(this.getTopLevelAncestor(), "Are you sure to untag Document(s)?", actionCommand, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					_TechnicalDocumentRequest.unTagDocument(modelDocs_ForRelease);
				}
			}
		}

		if(actionCommand.equals("Search")){//Execute if Search button is clicked
			_JLookupTable dlg = new _JLookupTable(FncGlobal.homeMDI, null , "Request", sqlSearch(), false);
			dlg.setLocationRelativeTo(FncGlobal.homeMDI);
			dlg.setVisible(true);
			Object[] data = dlg.getReturnDataSet();

			if(data != null){
				FncSystem.out("Display sql for Search", sqlSearch());

				try{
					String req_no = ((String) data[0]);
					String entity_id = ((String) data[1]);
					String entity_name = ((String) data[2]);
					String unit_id = ((String) data[3]);
					Integer seq_no = (Integer) data[4];
					String proj_id = (String) data[5];
					String proj_name = ((String) data[6]);
					String description = ((String) data[7]);
					String house_model = ((String) data[10]);
					Date req_date = ((Date) data[11]);
					//String remarks = ((String) data[12]);

					//txtRequestNo.setText(req_no);
					lookupClient.setValue(entity_id);
					txtClientName.setText(entity_name);
					txtUnitID.setText(unit_id);
					txtUnitDesc.setText(description);
					txtProjID.setText(proj_id);
					txtProject.setText(proj_name);
					txtSeqNo.setText(seq_no.toString());
					txtModel.setText(house_model);
					//requestdate.setDate(req_date);

				} catch (NullPointerException a) {}

				_TechnicalDocumentRequest.displayDocsList(modelDocsRequest, rowHeaderDocsRequest, txtRequestNo.getText());
				tblDocsRequest.packAll();
				scrollDocsRequest.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblDocsRequest.getRowCount())));
				//_TechnicalDocumentRequest.displayRequestPurpose(modelReqPurpose, rowHeaderReqPurpose);
				_TechnicalDocumentRequest.displayReqList(modelDocsPurposeReq, rowHeaderDocsPurpose_Request, txtRequestNo.getText());
				tblDocsPurpose_Request.packAll();
				scrollDocsPurpose_Request.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblDocsPurpose_Request.getRowCount())));
				//this.setComponentsEditable(pnlRequestPurpose, false);

				_TechnicalDocumentRequest.displayReleasedDocs(modelReleasedDocs, rowHeaderReleasedDocs, lookupClient.getValue(), txtUnitID.getText(), txtProjID.getText(), txtSeqNo.getText());
				tblReleasedDocs.packAll();
				scrollReleasedDocs.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblReleasedDocs.getRowCount())));

				//tblTagDocsReq.setEnabled(true);
				tblDocsRequest.setEditable(true);
				tblReleasedDocs.setEnabled(true);
				tblDocsPurpose_Request.setEditable(false);

			}
		}

		if(actionCommand.equals("Save")){//Execute if Save button is clicked
			if(checkRequiredFields()){
				if(JOptionPane.showConfirmDialog(this.getTopLevelAncestor(), "Continue Saving?", actionCommand, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					//ADDED BY JARI CRUZ ASOF FEB 16 2023
					if(active_edit) {
						pgUpdate db = new pgUpdate();
						String entity_id = lookupClient.getValue();
						String proj_id = txtProjID.getText();
						String pbl_id = txtUnitID.getText();
						String seq_no = txtSeqNo.getText();
						String SQL = "update\n"
								+ "rf_buyer_docs_hd \n"
								+ "set remarks = '"+txtAreaRemarks.getText().replace("'", "''")+"'\n"
								+ "WHERE entity_id = '"+entity_id+"' \n"
								+ "AND projcode = '"+proj_id+"' \n"
								+ "AND pbl_id = getinteger('"+pbl_id+"')::VARCHAR \n"
								+ "AND seq_no = getinteger('"+seq_no+"') \n"
								+ "AND request_no = '"+selected_request_no+"'";
						db.executeUpdate(SQL, false);
						db.commit();
						active_edit = false;
						clearReqTechDoc();
					} else {
						_TechnicalDocumentRequest.saveRfBuyerDocsHD(getRequestNo(), lookupClient.getValue(), txtUnitID.getText(), txtProjID.getText() ,txtSeqNo.getText(), txtClientName.getText() ,txtAreaRemarks.getText().replace("'", "''"), txtReceivedBy.getText(), txtRelation.getText());
						//System.out.printf("Display request no: %s%n", getRequestNo());
						_TechnicalDocumentRequest.saveRfBuyerDocsDL(modelDocsRequest, getRequestNo(), modelDocsPurposeReq, txtReceivedBy.getText());
						//System.out.printf("Display request no: %s%n", getRequestNo());
						_TechnicalDocumentRequest.saveReqHeader(getRequestNo(), txtClientName.getText());
						//System.out.printf("Display request no: %s%n", getRequestNo());
						pnlState(true, true, true);
						btnState(false, false, false, false, false, false);
						clearReqTechDoc();
					}
					
					JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Client Request has been Saved", actionCommand, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}

		if(actionCommand.equals("Release")){//Execute if Release button is clicked
			/*if(JOptionPane.showConfirmDialog(this.getTopLevelAncestor(), "Release Tagged Documents?", actionCommand, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
				//_TechnicalDocumentRequest.releaseReqBuyerDocsHD(txtRequestNo.getText());
				_TechnicalDocumentRequest.releaseReqBuyerDocsDL(modelTechDoc, txtRequestNo.getText());
				//_TechnicalDocumentRequest.releaseReqHeader(txtRequestNo.getText());
				JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Client Documents has been Released", actionCommand, JOptionPane.INFORMATION_MESSAGE);

				_TechnicalDocumentRequest.displayDocsList(modelTechDoc, rowHeaderTagDocsReq, txtRequestNo.getText());
				scrollTagDocsReq.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, FncTables.getRowHeader_Footer(Integer.toString(tblTagDocsReq.getRowCount())));				
				tblTagDocsReq.packAll();

				//clearReqTechDoc();
			}*/
			
			if(tblRequest_ForRelease.getSelectedRows().length == 1){
				if(getSelectedDocs_ForRelease() != 0){
					if(txtReceivedBy.getText().equals("") == false || txtRelation.getText().equals("") == false){
						if(JOptionPane.showConfirmDialog(this.getTopLevelAncestor(), "Release Tagged Document(s)?", actionCommand, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
							int selected_row = tblRequest_ForRelease.convertRowIndexToModel(tblRequest_ForRelease.getSelectedRow());
							String request_no = (String) modelRequest_ForRelease.getValueAt(selected_row, 0);
							
							_TechnicalDocumentRequest.releaseDocuments(request_no, modelDocs_ForRelease, txtReceivedBy.getText(), txtRelation.getText());
							displayForReleaseTab();
							
							JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Request Posted", actionCommand, JOptionPane.INFORMATION_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Please input Received By and Relation", actionCommand, JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Please select Document to Release", actionCommand, JOptionPane.WARNING_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Please select Request to release documents", actionCommand, JOptionPane.WARNING_MESSAGE);
			}
		}

		if(actionCommand.equals("Clear")){//Execute if Clear button is clicked
			clearReqTechDoc();
		}

		if(actionCommand.equals("Preview")){//For preview of the Technical Documents to be printed
			if(tabCenter.getTitleAt(tabCenter.getSelectedIndex()).equals("For Release")) {
				int selected_row = tblRequest_ForRelease.convertRowIndexToModel(tblRequest_ForRelease.getSelectedRow());
				
				String request_no = (String) modelRequest_ForRelease.getValueAt(selected_row, 0);;
				
				ArrayList<TD_RequestfortheReleased> listReleaseDocs = new ArrayList<TD_RequestfortheReleased>();
				
				for(int x =0; x < modelDocs_ForRelease.getRowCount(); x++){
					Boolean isSelected = (Boolean) modelDocs_ForRelease.getValueAt(x, 0);
					
					if(isSelected){
						String doc_name = (String) modelDocs_ForRelease.getValueAt(x, 3);
						
						listReleaseDocs.add(new TD_RequestfortheReleased(doc_name));
					}
				}

				ArrayList<TD_RequestPurpose> listDocsPurpose = new ArrayList<TD_RequestPurpose>();

				for(int x = 0; x<modelPurpose_Release.getRowCount(); x++){
					Boolean isSelected = (Boolean) modelPurpose_Release.getValueAt(x, 0);
					
					if(isSelected){
						String docs_purpose = (String) modelPurpose_Release.getValueAt(x, 2);
						
						listDocsPurpose.add(new TD_RequestPurpose(docs_purpose));
					}
				}
				
				String prepared_by = UserInfo.FirstName.concat(UserInfo.MiddleName).concat(UserInfo.LastName);
				System.out.printf("Display prepared by: %s%n", prepared_by);
				
				Map<String, Object> mapParameters = new HashMap<String, Object>();
				/*mapParameters.put("client_id", lookupClient.getValue()); //Client ID
				mapParameters.put("client_name", txtClientName.getText()); //Client Name
				mapParameters.put("project", txtProject.getText()); //Project
				mapParameters.put("description", txtUnitDesc.getText()); //Description*/			
				mapParameters.put("request_no", request_no); //Request No
				//mapParameters.put("request_date", requestdate.getDate());
				/*mapParameters.put("house_model", txtModel.getText());
				mapParameters.put("remarks", txtAreaRemarks.getText().replace("'", "")); //Remarks*/			
				mapParameters.put("prepared_by", UserInfo.FullName2);
				
				mapParameters.put("listRequestfortheReleased", listReleaseDocs);
				mapParameters.put("listPurposeofDocs", listDocsPurpose);
				//mapParameters.put("connection", arg1)
				mapParameters.put("SUBREPORT_DIR", this.getClass().getResourceAsStream("/Reports/subrptTechnicalDocuments.jasper"));
				//mapParameters.put("SUBREPORT_DIR", this.getClass().getResourceAsStream("/Reports.subrptPreviewSchedule.jasper"));
				mapParameters.put("SUBREPORT_DocsPurpose", this.getClass().getResourceAsStream("/Reports/subrptTechnicalDocuments_DocsPurpose.jasper"));

				FncReport.generateReport("/Reports/rptTechnicalDocuments.jasper", "Technical Documents", mapParameters);	
			//added by jari cruz dtd march 14 2023	 
			} else if (tabCenter.getTitleAt(tabCenter.getSelectedIndex()).equals("Released Documents")) {
				//modelReleasedDocs
				int selected_row = tblReleasedDocs.convertRowIndexToModel(tblReleasedDocs.getSelectedRow());
				System.out.println("Released Documents: "+selected_row);
				
				String request_no = (String) modelReleasedDocs.getValueAt(selected_row, 0);
				
				System.out.println("request_no: "+request_no);
				
				_TechnicalDocumentRequest.displayRequestPurposeDocuments(request_no, modelPurpose_Release, rowHeaderPurpose_ForRelease);
				_TechnicalDocumentRequest.displayDocsReleased(request_no, modelDocs_ForRelease, rowHeaderDocs_ForRelease);
				
				ArrayList<TD_RequestfortheReleased> listReleaseDocs = new ArrayList<TD_RequestfortheReleased>();
				
				for(int x =0; x < modelDocs_ForRelease.getRowCount(); x++){
					Boolean isSelected = (Boolean) modelDocs_ForRelease.getValueAt(x, 0);
					
					if(isSelected){
						String doc_name = (String) modelDocs_ForRelease.getValueAt(x, 3);
						System.out.println("docs_purpose: "+doc_name);
						
						listReleaseDocs.add(new TD_RequestfortheReleased(doc_name));
					}
				}

				ArrayList<TD_RequestPurpose> listDocsPurpose = new ArrayList<TD_RequestPurpose>();

				for(int x = 0; x<modelPurpose_Release.getRowCount(); x++){
					Boolean isSelected = (Boolean) modelPurpose_Release.getValueAt(x, 0);
					
					if(isSelected){
						String docs_purpose = (String) modelPurpose_Release.getValueAt(x, 2);
						System.out.println("docs_purpose: "+docs_purpose);
						listDocsPurpose.add(new TD_RequestPurpose(docs_purpose));
					}
				}
				
				String prepared_by = UserInfo.FirstName.concat(UserInfo.MiddleName).concat(UserInfo.LastName);
				System.out.printf("Display prepared by: %s%n", prepared_by);
				
				Map<String, Object> mapParameters = new HashMap<String, Object>();		
				mapParameters.put("request_no", request_no); //Request No		
				mapParameters.put("prepared_by", UserInfo.FullName2);
				
				mapParameters.put("listRequestfortheReleased", listReleaseDocs);
				mapParameters.put("listPurposeofDocs", listDocsPurpose);
				mapParameters.put("SUBREPORT_DIR", this.getClass().getResourceAsStream("/Reports/subrptTechnicalDocuments.jasper"));
				mapParameters.put("SUBREPORT_DocsPurpose", this.getClass().getResourceAsStream("/Reports/subrptTechnicalDocuments_DocsPurpose.jasper"));		
				
				FncReport.generateReport("/Reports/rptTechnicalDocuments.jasper", "Technical Documents", mapParameters);
			}
		}
	}
	
	private boolean checkWithinTheDay(String request_no) {
		pgSelect db = new pgSelect();
		
		String entity_id = lookupClient.getValue();
		String proj_id = txtProjID.getText();
		String pbl_id = txtUnitID.getText();
		String seq_no = txtSeqNo.getText();
		
		String SQL = "select * from rf_buyer_docs_hd\n"
				+ "WHERE entity_id = '"+entity_id+"' \n"
				+ "AND projcode = '"+proj_id+"' \n"
				+ "AND pbl_id = getinteger('"+pbl_id+"')::VARCHAR \n"
				+ "AND seq_no = getinteger('"+seq_no+"') \n"
				+ "AND request_no = '"+selected_request_no+"'\n"
				+ "and request_date::DATE = now()::DATE";
		db.select(SQL);
		
		if (db.isNotNull()) {
			return true;
		} else {
			return false;
		}
	}
}
