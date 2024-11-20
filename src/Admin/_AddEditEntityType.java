
package Admin;

import tablemodel.modelAddEditEntityType_EntityTypes;

import javax.swing.table.DefaultTableModel;

import Database.pgSelect;
import Database.pgUpdate;
import Functions.FncSystem;
import Functions.UserInfo;

public class _AddEditEntityType {

	public _AddEditEntityType() {
		// TODO Auto-generated constructor stub
	}
	
	public static String getEntitySQL() {
		String SQL = 
			"SELECT TRIM(a.entity_id) AS \"ID\", \n"
			+ "	TRIM(a.entity_name) AS \"Name\", \n"
			+ "	TRIM(CASE WHEN tin_no = '' OR tin_no IS NULL THEN '' ELSE \n"
			+ "	CONCAT(substr(trim(replace(a.tin_no, '-', '')), 1, 3), '-',\n"
			+ "			substr(trim(replace(a.tin_no,'-','')), 4, 3), '-',\n"
			+ "			substr(trim(replace(a.tin_no,'-','')), 7, 3), '-',\n"
			+ "			substr(trim(replace(a.tin_no,'-','')), 10, 3),\n"
			+ "	(CASE WHEN substr(trim(replace(a.tin_no,'-','')), 10, 3) = '' OR \n"
			+ "			substr(trim(replace(a.tin_no,'-','')), 10, 3) IS NULL THEN '000' ELSE '' END)\n"
			+ "			) END) AS \"TIN\",\n"
			+ "	vat_registered AS \"VAT\"\n"
			+ "	FROM rf_entity a\n"
			+ "	ORDER BY a.entity_name;";
		
		FncSystem.out("Get Entity List: ", SQL);
		return SQL;
	}
	
	public static Object[] getEntityDetails(String entity_id) {
		String SQL = "SELECT TRIM(entity_name) as entity_namae\n"
				+ ", tin_no\n"
				+ ", COALESCE(vat_registered, FALSE) as vat_registered\n"
				+ "FROM rf_entity a \n"
				+ "WHERE entity_id = '"+entity_id+"'\n"
				+ "AND status_id = 'A'";

		pgSelect db = new pgSelect();
		db.select(SQL);
		if(db.isNotNull()){
			return db.getResult()[0];
		}else{
			return null;
		}
	}
	
	public static void displayEntityTypes(modelAddEditEntityType_EntityTypes model, String entity_id) {
		model.clear();

		String SQL = "select exists\n"
				+ "(select * from rf_entity_assigned_type \n"
				+ "where entity_id = '"+entity_id+"'\n"
				+ "and status_id = 'A' \n"
				+ "and entity_type_id = a.entity_type_id) as tag, \n"
				+ "trim(entity_type_id) as type_id, \n"
				+ "trim(entity_type_desc) as type_desc,\n"
				+ "trim(a.wtax_id) as wtax_id,\n"
				+ "trim(b.atc_desc) as wtax_desc,\n"
				+ "b.wtax_rate, \n"
				+ "b.atc_code \n"
				+ "from mf_entity_type a \n"
				+ "left join mf_withholding_tax b on b.wtax_id = a.wtax_id \n"
				+ "where a.entity_type_id not in ('20', '21', '22', '23', '28', '29', '32', '33') \n"
				+ "and a.status_id = 'A'\n"
				+ "ORDER BY entity_type_desc; ";

		pgSelect db = new pgSelect();
		db.select(SQL);
		if(db.isNotNull()){
			for(int x=0; x < db.getRowCount(); x++){
				model.addRow(db.getResult()[x]);
			}
		}
	}

	public static void displayCSEntityTypes(modelAddEditEntityType_EntityTypes model, String entity_id, String co_id) {
		model.clear();

		String SQL = "select exists\n"
				+ "(select * from rf_entity_assigned_type \n"
				+ "where entity_id = '"+entity_id+"'\n"
				+ "and status_id = 'A' \n"
				+ "and entity_type[array_position(co_id, '"+co_id+"')] = a.entity_type_id) as tag,  \n"
				+ "trim(entity_type_id) as type_id, \n"
				+ "trim(entity_type_desc) as type_desc,\n"
				+ "trim(a.wtax_id) as wtax_id,\n"
				+ "trim(b.atc_desc) as wtax_desc,\n"
				+ "b.wtax_rate, \n"
				+ "b.atc_code \n"
				+ "from mf_entity_type a \n"
				+ "left join mf_withholding_tax b on b.wtax_id = a.wtax_id \n"
				+ "where a.entity_type_id not in ('20', '21', '22', '23', '28', '29', '32', '33') \n"
				+ "and a.status_id = 'A'\n"
				+ "ORDER BY entity_type_desc; ";

		pgSelect db = new pgSelect();
		db.select(SQL);
		if(db.isNotNull()){
			for(int x=0; x < db.getRowCount(); x++){
				model.addRow(db.getResult()[x]);
			}
		}
	}
	
	public static void saveEntityTypes(modelAddEditEntityType_EntityTypes model, String entity_id) {
		for(int x=0; x < model.getRowCount(); x++){
			Boolean selected = (Boolean) model.getValueAt(x, 0);
			String entity_type_id = (String) model.getValueAt(x, 1);

			String SQL = "SELECT fn_update_entity_types('"+ entity_id +"', '"+ entity_type_id +"', "+ selected +", '"+ UserInfo.EmployeeCode +"');";

			pgSelect db = new pgSelect();
			db.select(SQL);
		}
	}

	public static void saveCSEntityTypes(DefaultTableModel model, String id, String company) {
		for (int x=0; x < model.getRowCount(); x++) {
			if ((Boolean) model.getValueAt(x, 0)) {
				pgUpdate dbExec = new pgUpdate();
				dbExec.executeUpdate("call sp_update_cs_entity_type('"+id+"', '"+model.getValueAt(x, 1).toString()+"', '"+company+"', '"+UserInfo.EmployeeCode+"'); ", true);
				dbExec.commit();
			}
		}
	}
}
