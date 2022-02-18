package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class doctor extends user {
	Connection con = conn.connDb();
	PreparedStatement prepareStatement = null;
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public doctor() {
		super();
	}

	public doctor(int id, String name, String tcno, String password, String type) {
		super(id, name, tcno, password, type);

	}

	public ArrayList<wHour> getWhourlist(int doctor_id) throws SQLException {
		ArrayList<wHour> list = new ArrayList<>();
		wHour obj;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM wHour WHERE status='a'  and doctor_id=" + doctor_id + " ");

			while (rs.next()) {
				obj = new wHour();
				obj.setId(rs.getInt("id"));
				obj.setDoctor_id(rs.getInt("doctor_id"));
				obj.setName(rs.getString("doctor_name"));
				obj.setStatus(rs.getString("status"));
				obj.setWdate(rs.getString("wdate"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return list;
	}

	public int addDoctor(int d_id, String d_name, String wdate) throws SQLException {
		int result = 0;
		int count = 0;
		String query = "INSERT  INTO whour (doctor_id,doctor_name,wdate) VALUES (?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT * FROM whour WHERE status='a' and  doctor_id=" + d_id + " AND wdate='" + wdate + "' ");
			while (rs.next()) {
				count++;
				break;
			}
			if (count == 0) {
				ps = con.prepareStatement(query);
				ps.setInt(1, d_id);
				ps.setString(2, d_name);
				ps.setString(3, wdate);
				ps.executeUpdate();
			}
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result == 1)
			return result;
		else
			return result;

	}

	public boolean deleteWhur(int id) throws SQLException {

		boolean result = false;
		try {
			String query = "DELETE FROM whour WHERE id=? " + id;
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

}
