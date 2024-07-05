package Utilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.border.Border;

import org.jdesktop.swingx.JXPanel;

import Database.pgSelect;
import Functions.FncSystem;
import components._ButtonGroup;
import components._JInternalFrame;
import components._JXTextField;
import interfaces._GUI;

public class ZipCodes extends _JInternalFrame implements _GUI, ActionListener {
	
	private static final long serialVersionUID = 6819425244806487520L;
	static String title = "Zip Codes";
	static Dimension frameSize = new Dimension(500, 200);
	Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
	

	private JPanel pnlNorth;
	private JPanel pnlNorthLabel;
	private JLabel lblRegion;
	private JLabel lblProvince;
	private JLabel lblCityMunicipality;
	private JLabel lblZipCode;
	private JLabel lblBranchName;
	private JPanel pnlNorthComponents;
	private JComboBox cmbRegion;
	private JComboBox cmbProvince;
	private JComboBox cmbCtyMncplty;
	private _JXTextField txtZipCode;
	private _JXTextField txtBranchName;
	private JPanel pnlSouth;
	private JButton btnNew;
	private JButton btnSave;
	private JButton btnCancel;
	private _ButtonGroup grpNewEdit = new _ButtonGroup();
	
	
	public ZipCodes(){
		super(title, true, true, false, true);
		initGUI();
	}
	
	public ZipCodes(String title){
		super(title, true, true, true, true);
		initGUI();
	}
	
	public ZipCodes(String title, boolean resizable, boolean closable,
			boolean maximizable, boolean iconifiable) {
		super(title, true, true, true, true);
		initGUI();
	}
	
	@Override
	public void initGUI() {
		this.setSize(frameSize);
		this.setPreferredSize(frameSize);
		
		JXPanel pnlMain = new JXPanel(new BorderLayout(5, 5));
		getContentPane().add(pnlMain, BorderLayout.CENTER);
		pnlMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		{
			pnlNorth = new JPanel(new BorderLayout(3, 3));
			pnlMain.add(pnlNorth, BorderLayout.CENTER);
			{
				pnlNorthLabel = new JPanel(new GridLayout(5, 1, 3, 3));
				pnlNorth.add(pnlNorthLabel, BorderLayout.WEST);
				{
					lblRegion = new JLabel("Region");
					pnlNorthLabel.add(lblRegion);
				}
				{
					lblProvince = new JLabel("Province");
					pnlNorthLabel.add(lblProvince);
				}
				{
					lblCityMunicipality = new JLabel("City/Municipality");
					pnlNorthLabel.add(lblCityMunicipality);
				}
				{
					lblZipCode = new JLabel("Zip Code");
					pnlNorthLabel.add(lblZipCode);
				}
				{
					lblBranchName = new JLabel("Branch");
					pnlNorthLabel.add(lblBranchName);
				}
			}
			{
				pnlNorthComponents = new JPanel(new GridLayout(5, 1, 3, 3));
				pnlNorth.add(pnlNorthComponents, BorderLayout.CENTER);
				{
					cmbRegion = new JComboBox();
					pnlNorthComponents.add(cmbRegion, BorderLayout.WEST);
					cmbRegion.setPreferredSize(new Dimension(250, 0));
					cmbRegion.setEnabled(false);
					cmbRegion.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							String region_name = (String) ((JComboBox) e.getSource()).getSelectedItem();
							String region_id = getRegionID(region_name);
							
							mapProvince(region_id);
							cmbProvince.setModel(new DefaultComboBoxModel(listProvince(region_id)));
							
							String province_name = (String) cmbProvince.getSelectedItem();
							String province_id = getProvinceID(region_id, province_name);
							
							mapCtyMncplty(province_id);
							cmbCtyMncplty.setModel(new DefaultComboBoxModel(listCtyMncplty(province_id)));
							
						}
					});
				}
				{
					cmbProvince = new JComboBox();
					pnlNorthComponents.add(cmbProvince);
					cmbProvince.setPreferredSize(new Dimension(200, 0));
					cmbProvince.setEnabled(false);
					cmbProvince.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							String region_name = (String) cmbRegion.getSelectedItem();
							String region_id = getRegionID(region_name);
							
							String province_name = (String) ((JComboBox) e.getSource()).getSelectedItem();
							String province_id = getProvinceID(region_id, province_name);
							
							System.out.printf("Display region name: %s%n", region_name);
							System.out.printf("Display region id: %s%n", region_id);
							
							System.out.printf("Display province name: %s%n", province_name);
							System.out.printf("Display province id: %s%n", province_id);
							
							mapCtyMncplty(province_id);
							cmbCtyMncplty.setModel(new DefaultComboBoxModel(listCtyMncplty(province_id)));
							
						}
					});
				}
				{
					cmbCtyMncplty = new JComboBox();
					pnlNorthComponents.add(cmbCtyMncplty);
					cmbCtyMncplty.setPreferredSize(new Dimension(200, 0));
					cmbCtyMncplty.setEnabled(false);
				}
				{
					txtZipCode = new _JXTextField();
					pnlNorthComponents.add(txtZipCode, BorderLayout.WEST);
					txtZipCode.setPreferredSize(new Dimension(150, 0));
				}
				{
					txtBranchName = new _JXTextField();
					pnlNorthComponents.add(txtBranchName, BorderLayout.WEST);
					txtBranchName.setPreferredSize(new Dimension(150, 0));
				}
			}
		}
		{
			pnlSouth = new JPanel(new GridLayout(1, 3, 3, 3));
			pnlMain.add(pnlSouth, BorderLayout.SOUTH);
			pnlSouth.setPreferredSize(new Dimension(0, 35));
			{
				btnNew = new JButton("New");
				pnlSouth.add(btnNew);
				btnNew.setActionCommand(btnNew.getText());
				btnNew.setMnemonic(KeyEvent.VK_N);
				grpNewEdit.add(btnNew);
			}
			{
				btnSave = new JButton("Save");
				pnlSouth.add(btnSave);
				btnSave.setActionCommand(btnSave.getText());
				btnSave.setMnemonic(KeyEvent.VK_S);
			}
			{
				btnCancel = new JButton("Cancel");
				pnlSouth.add(btnCancel);
				btnCancel.setActionCommand(btnCancel.getText());
				btnCancel.setMnemonic(KeyEvent.VK_C);
			}
		}
		
		mapRegion();
		
		btnState(true, false, false, false);
	}
	
	private void btnState(Boolean sNew, Boolean sEdit, Boolean sSave, Boolean sCancel){
		btnNew.setEnabled(sNew);
		btnSave.setEnabled(sSave);
		btnCancel.setEnabled(sCancel);
	}
		
	

	private String getRegionID(String region_name){
		String region_id = "";
		
		for(Entry<String, String> entry: mapRegion().entrySet()){
			if(entry.getValue().equals(region_name)){
				region_id = entry.getKey();
			}
		}
		return region_id;
	}
	
	private LinkedHashMap<String, String> mapRegion(){
		LinkedHashMap<String, String> mapRegion = new LinkedHashMap<String, String>();
		
		pgSelect db = new pgSelect();
		String SQL = "SELECT region_id, FORMAT('%s (%s)', region_name, region_desc) \n"+
					 "FROM mf_region WHERE status_id = 'A' order by region_id;\n";
		
		db.select(SQL);
		
		FncSystem.out("Display SQL Hashmap Region", SQL);
		
		if(db.isNotNull()){
			for(int x= 0; x<db.getRowCount(); x++){
				String region_id = (String) db.getResult()[x][0];
				String region_desc = (String) db.getResult()[x][1];
				
				mapRegion.put(region_id, region_desc);
			}
			return mapRegion;
		}else{
			return null;
		}
	}

	private Object [] listCtyMncplty(String province_id){
		ArrayList<String> listCityMunicipality = new ArrayList<String>();

		try{
			for(Entry<String, String> entry: mapCtyMncplty(province_id).entrySet()){
				String city_municipality_name = entry.getValue();

				listCityMunicipality.add(city_municipality_name);
			}
		}catch (NullPointerException e){}
		
		return listCityMunicipality.toArray();
	}
	
	private LinkedHashMap<String, String> mapCtyMncplty(String province_id){
		LinkedHashMap<String, String> mapCityMunicipality = new LinkedHashMap<String, String>();
		
		pgSelect db = new pgSelect();
		String SQL = "select TRIM(a.city_id) AS city_id, TRIM(a.city_name) as city_name, TRIM(a.province_id) AS province_id, TRIM(b.province_name) AS province_name, a.cm\n" + 
					 "from (select city_id, city_name, province_id, 'City' as cm from mf_city\n" + 
					 "union\n" + 
					 "select municipality_id, municipality_name, province_id, 'Municipality' from mf_municipality) a\n" + 
					 "left join mf_province b on b.province_id = a.province_id\n" + 
					 "where "+ (province_id.equals("NONE") ? "a.province_id is null":"a.province_id = '"+ province_id +"'") +"\n" + 
					 "order by city_name;";
		db.select(SQL);
		
		FncSystem.out("Display SQL For City Municipality", SQL);
		
		if(db.isNotNull()){
			for(int x = 0; x<db.getRowCount(); x++){
				String city_id = (String) db.getResult()[x][0];
				String city_name = (String) db.getResult()[x][1];
				
				mapCityMunicipality.put(city_id, city_name);
			}
			return mapCityMunicipality;
		}else{
			return null;
		}
	}
	
	private Object [] listProvince(String region_id){
		ArrayList<String> listProvince = new ArrayList<String>();
		
		if(region_id.equals("15")){
			listProvince.add("NONE");
		}
		
		try{
			for(Entry<String, String> entry: mapProvince(region_id).entrySet()){

				String province_name = entry.getValue();
				listProvince.add(province_name);
			}
		}catch (NullPointerException e){}

		return listProvince.toArray();
	}
	
	private String getProvinceID(String region_id,String province_name){
		String province_id = "";
		
		try{
		
		if(province_name.equals("NONE")){
			System.out.printf("Display province name %s%n", province_name);
			System.out.printf("Display Province ID %s%n", province_id);
			province_id = province_name;
		}
		
			for(Entry<String, String> entry: mapProvince(region_id).entrySet()){
				if(entry.getValue().equals(province_name)){
					province_id = entry.getKey();
				}
			}
		}catch (NullPointerException e){}
		
		return province_id;
	}
	
	private LinkedHashMap<String, String> mapProvince(String region_id){
		LinkedHashMap<String, String> mapProvince = new LinkedHashMap<String, String>();
		
		pgSelect db = new pgSelect();
		
		String SQL = "SELECT TRIM(province_id), trim(province_name) \n"+
					 "FROM mf_province \n"+
					 "WHERE region_id = '"+region_id+"' \n"+
					 "AND status_id = 'A' \n"+
					 "ORDER BY province_name; \n";
		db.select(SQL);
		
		FncSystem.out("Display SQL Hashmap for Province", SQL);
		
		if(db.isNotNull()){
			for(int x =0; x<db.getRowCount(); x++){
				String province_id = (String) db.getResult()[x][0];
				String province_name = (String) db.getResult()[x][1];
				
				mapProvince.put(province_id, province_name);
			}
			return mapProvince;
		}else{
			return null;
		}
	}
	
	
}
	