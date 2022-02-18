package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.Helper;

public class hasta extends user {

	Connection con = conn.connDb();
	PreparedStatement prepareStatement = null;
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public hasta() {
	}

	public hasta(int id, String name, String tcno, String password, String type) {
		super(id, name, tcno, password, type);
	}

	public boolean register(String name, String tcno, String pass) throws SQLException {
		boolean result = false;
		String query = "INSERT INTO user(tcno,passwors,name,type) values(?,?,?,?) ";
		boolean dualicte = false;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from user where tcno='" + tcno + "'  ");
			while (rs.next()) {
				dualicte = true;

			}
			if (dualicte) {
				Helper.showMsg("Bu TC numarasi Kayitli zaten.");
			} else {
				ps = con.prepareStatement(query);
				ps.setString(1, tcno);
				ps.setString(2, pass);
				ps.setString(3, name);
				ps.setString(4, "hasta");
				ps.executeUpdate();

				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result)
			return true;
		else
			return false;
	}

	public boolean updateStatus(int doctorId, String appDate) {
		String query = "update whour set status=? where doctor_id=? and wdate=?";
		boolean result = false;

		try {
			prepareStatement = con.prepareStatement(query);
			prepareStatement.setString(1, "p");
			prepareStatement.setInt(2, doctorId);
			prepareStatement.setString(3, appDate);
			prepareStatement.executeUpdate();

			result = true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (result)
			return true;
		else
			return false;
	}

	public boolean addApp(int doctorId, String doctorName, int hastaID, String hastaName, String appDate) {
		String query = "insert into appoinment" + "(doctor_id,docctor_name,hasta_id,hasta_name,app_date) values"
				+ "(?,?,?,?,?) ";
		boolean result = false;

		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, doctorId);
			ps.setString(2, doctorName);
			ps.setInt(3, hastaID);
			ps.setString(4, hastaName);
			ps.setString(5, appDate);
			ps.executeUpdate();

			result = true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (result)
			return true;
		else
			return false;
	}

}
