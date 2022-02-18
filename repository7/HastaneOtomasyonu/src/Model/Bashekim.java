package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextField;

public class Bashekim extends user {

	Connection con = conn.connDb();
	PreparedStatement prepareStatement = null;
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	public Bashekim(int id, String name, String tcno, String password, String type) {
		super(id, name, tcno, password, type);
	}

	public Bashekim() {
	}

	public ArrayList<user> getDoctorlist() throws SQLException {
		ArrayList<user> list = new ArrayList<>();
		user obj;
		// Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM user WHERE type='doctor'");

			while (rs.next()) {
				obj = new user(rs.getInt("id"), rs.getString("name"), rs.getString("tcno"), rs.getString("password"),
						rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return list;
	}

	public boolean addDoctor(String tcno, String password, String name) {

		String query = "insert into user" + "(tcno,password,name,type) values" + "(?,?,?,?) ";
		boolean result = false;

		try {
			Statement st = con.createStatement();
			prepareStatement = con.prepareStatement(query);
			prepareStatement.setString(1, tcno);
			prepareStatement.setString(2, password);
			prepareStatement.setString(3, name);
			prepareStatement.setString(4, "doctor");
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

	public boolean deleteDoctor(int id) throws SQLException {

		boolean result = false;
		try {
			String query = "DELETE FROM user WHERE id=? ";
			Statement st = con.createStatement();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			result = true;
		}

		catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (result)
			return true;
		else
			return false;

	}

	public boolean updateDoctor(int id, String tcno, String pass, String name) throws SQLException {

		String query = "UPDATE user SET name=?,tcno=?,password=? WHERE id=?";
		boolean result = false;
		try {
			Statement st = con.createStatement();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, tcno);
			ps.setString(3, pass);
			ps.setInt(4, id);
			ps.executeUpdate();
			result = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result)
			return true;
		else
			return false;

	}

	public boolean addWorker(int user_id, int clinic_id) {
		String query = "insert into worker" + "(user_id,clinic_id) values" + "(?,?) ";
		boolean result = false;
		int count = 0;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT  * FROM worker WHERE clinic_id=" + clinic_id + " AND user_id=" + user_id + "");
			while (rs.next()) {
				count++;
			}
			if (count == 0) {
				prepareStatement = con.prepareStatement(query);
				prepareStatement.setInt(1, user_id);
				prepareStatement.setInt(2, clinic_id);
				prepareStatement.executeUpdate();
			}
			result = true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (result)
			return true;
		else
			return false;
	}

	public ArrayList<user> getClinicDoctor(int clinic_ID) throws SQLException {

		ArrayList<user> list = new ArrayList();
		user obj = null;
		String query = "SELECT  u.id,u.tcno,u.`type`,u.name,u.password FROM worker w LEFT JOIN  user u ON w.user_id=u.id "
				+ " WHERE  clinic_id=" + clinic_ID + " ";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				obj = new user(rs.getInt("u.id"), rs.getString("u.tcno"), rs.getString("u.name"),
						rs.getString("u.password"), rs.getString("u.type"));
				list.add(obj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
