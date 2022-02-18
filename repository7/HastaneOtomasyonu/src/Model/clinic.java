package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DbConnection;

public class clinic {
	private int id;
	private String name;
	DbConnection conn = new DbConnection();
	Connection con = conn.connDb();

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public clinic() {
	}

	public clinic(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public ArrayList<clinic> getClinicList() throws SQLException {

		ArrayList<clinic> list = new ArrayList<clinic>();
		clinic obj;
		String query = "SELECT * FROM clinic ";
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				obj = new clinic(rs.getInt("id"), rs.getString("name"));
				list.add(obj);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {

		}
		return list;
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
	
	
	public boolean updateClinic(int id, String name) throws SQLException {

		String query = "UPDATE clinic SET name=?s WHERE id=?";
		boolean result = false;
		try {
			Statement st = con.createStatement();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, name);
			ps.setInt(2, id);
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

	public boolean deleteClinic(int id) throws SQLException {

		boolean result = false;
		String query = "DELETE FROM clinic WHERE id=?";

		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
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

	public boolean clinicAdd(String polName) throws SQLException {
		boolean result = false;
		String query = "INSERT INTO clinic (NAME) VALUES (?)";
//insert into user" + "(tcno,password,name,type) values" + "(?,?,?,?)
		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setString(1, polName);
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

	public clinic getFetch(int id) throws SQLException {
		clinic c = new clinic();

		String query = "SELECT * FROM clinic WHERE id=" + id;

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
			break;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
