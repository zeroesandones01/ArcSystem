package Functions;

import Database.pgSelect;

public class FncAcounting {

	public static Boolean EmpCanAddNew(String emp_code, String process_no){

		Boolean EmpCanAddNew = false;

		String SQL = 
				"select status_id " +
						"from rf_fad_process_access " +
						"where emp_code = '"+emp_code+"' " +
						"and process_no = "+process_no+" " +
						"and authority_no = 1 " ;

		System.out.printf("Check Access: " + SQL );
		pgSelect db = new pgSelect();
		db.select(SQL);

		if(db.isNotNull()){

			if (((String) db.getResult()[0][0]).equals("A")) {EmpCanAddNew = true;}
			else {EmpCanAddNew = false;}		

		}else{
			EmpCanAddNew = false;
		}

		return EmpCanAddNew;

	}

	public static Boolean EmpCanApprove(String emp_code, String process_no){

		Boolean EmpCanAddNew = false;

		String SQL = 
				"select status_id " +
						"from rf_fad_process_access " +
						"where emp_code = '"+emp_code+"' " +
						"and process_no = "+process_no+" " +
						"and authority_no = 2 " ;

		System.out.printf("Check Access: " + SQL );
		pgSelect db = new pgSelect();
		db.select(SQL);

		if(db.isNotNull()){

			if (((String) db.getResult()[0][0]).equals("A")) {EmpCanAddNew = true;}
			else {EmpCanAddNew = false;}		

		}else{
			EmpCanAddNew = false;
		}

		return EmpCanAddNew;

	}

	public static Boolean EmpCanDelete(String emp_code, String process_no){

		Boolean EmpCanAddNew = false;

		String SQL = 
				"select status_id " +
						"from rf_fad_process_access " +
						"where emp_code = '"+emp_code+"' " +
						"and process_no = "+process_no+" " +
						"and authority_no = 3 " ;

		System.out.printf("Check Access: " + SQL );
		pgSelect db = new pgSelect();
		db.select(SQL);

		if(db.isNotNull()){

			if (((String) db.getResult()[0][0]).equals("A")) {EmpCanAddNew = true;}
			else {EmpCanAddNew = false;}		

		}else{
			EmpCanAddNew = false;
		}

		return EmpCanAddNew;

	}

	public static Boolean EmpCanPreview(String emp_code, String process_no){

		Boolean EmpCanAddNew = false;

		String SQL = 
				"select status_id " +
						"from rf_fad_process_access " +
						"where emp_code = '"+emp_code+"' " +
						"and process_no = "+process_no+" " +
						"and authority_no = 4 " ;

		System.out.printf("Check Access: " + SQL );
		pgSelect db = new pgSelect();
		db.select(SQL);

		if(db.isNotNull()){

			if (((String) db.getResult()[0][0]).equals("A")) {EmpCanAddNew = true;}
			else {EmpCanAddNew = false;}		

		}else{
			EmpCanAddNew = false;
		}

		return EmpCanAddNew;

	}

	/*ADDED BY JED 2021-12-02 DCRF NO. 1875 : UNTAGGING OF PAYMENTS WILL BE NOW CONTROLLED*/
	public static Boolean EmpCanUntag(String emp_code, String process_no){

		Boolean EmpCanAddNew = false;

		String SQL = 
				"select status_id " +
						"from rf_fad_process_access " +
						"where emp_code = '"+emp_code+"' " +
						"and process_no = "+process_no+" " +
						"and authority_no = 5 " ;

		System.out.printf("Check Access: " + SQL );
		pgSelect db = new pgSelect();
		db.select(SQL);

		if(db.isNotNull()){

			if (((String) db.getResult()[0][0]).equals("A")) {EmpCanAddNew = true;}
			else {EmpCanAddNew = false;}		

		}else{
			EmpCanAddNew = false;
		}

		return EmpCanAddNew;

	}

	public static Boolean EmpPettyCashCustodian(String emp_code, String process_no){

		Boolean EmpPettyCashCustodian = false;

		String SQL = 
				"select status_id " 
				+ "from rf_fad_process_access " 
				+ "where emp_code = '"+emp_code+"' " 
				+ "and process_no = "+process_no+" " 
				+ "and authority_no in (6,7) " ;

		System.out.printf("Check Access: " + SQL );
		pgSelect db = new pgSelect();
		db.select(SQL);

		if(db.isNotNull()){

			if (((String) db.getResult()[0][0]).equals("A")) {
				EmpPettyCashCustodian = true;
			} else {
				EmpPettyCashCustodian = false;
			}		

		}else{
			EmpPettyCashCustodian = false;
		}

		return EmpPettyCashCustodian;
	}



}
