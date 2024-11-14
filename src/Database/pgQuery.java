package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Functions.FncGlobal;

public class pgQuery {
	
	private Connection con;
	private Statement st;
	private PreparedStatement ps;

	public pgQuery() {
		try {
			con = DriverManager.getConnection(FncGlobal.connectionURL, FncGlobal.connectionUsername, FncGlobal.connectionPassword);
			con.setAutoCommit(false);
		} catch (SQLException e) {
			showException(e);
		}
	}
	
	 public static void setParameters(PreparedStatement pstmt, Object[] parameters) throws SQLException {
	        for (int i = 0; i < parameters.length; i++) {
	            Object param = parameters[i];
	            if (param instanceof Integer) {
	                pstmt.setInt(i + 1, (Integer) param);
	            } else if (param instanceof String) {
	                pstmt.setString(i + 1, (String) param);
	            } else if (param instanceof Double) {
	                pstmt.setDouble(i + 1, (Double) param);
	            } else if (param instanceof Boolean) {
	                pstmt.setBoolean(i + 1, (Boolean) param);
	            } else if (param instanceof Date) {
	                pstmt.setDate(i + 1, (Date) param);
	            } else {
	                pstmt.setObject(i + 1, param);
	            }
	        }
	    }
	
	public void executeUpdate(String strSQL, Boolean toPrint){
		try {
			if(toPrint)
				System.out.println(strSQL);

			ps = con.prepareStatement(strSQL);
			st.executeUpdate(strSQL);
			st.close();

		} catch (SQLException e) {
			System.out.println(strSQL);
			
			e.printStackTrace();
			showException(e);
		}
	}

	private void showException(SQLException e) {
		// TODO Auto-generated method stub
		
	}

}
