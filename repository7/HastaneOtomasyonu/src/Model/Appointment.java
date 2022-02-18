package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DbConnection;

public class Appointment {

	DbConnection conn = new DbConnection();

	private int id, doctorID, hastaID;
	private String doctorName, hastaName, appDate;
	Connection con = conn.connDb();
	PreparedStatement prepareStatement = null;
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public Appointment(int id, int doctorID, int hastaID, String doctorName, String hastaName, String appDate) {
		this.id = id;
		this.doctorID = doctorID;
		this.hastaID = hastaID;
		this.doctorName = doctorName;
		this.hastaName = hastaName;
		this.appDate = appDate;
	}

	public ArrayList<Appointment> getHastalist(int hasta_id) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		// Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM appoinment WHERE hasta_id=" + hasta_id);

			while (rs.next()) {

				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorID(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("docctor_name"));
							obj.setHastaName(rs.getString("hasta_name"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return list;
	}
	public ArrayList<Appointment> getDoctorlist(int doctorID) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		// Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Appointment WHERE doctor_id=" + doctorID);

			while (rs.next()) {

				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorID(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setHastaID(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppDate(rs.getString("app_date"));

				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return list;
	}

	public Appointment() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	public int getHastaID() {
		return hastaID;
	}

	public void setHastaID(int hastaID) {
		this.hastaID = hastaID;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getHastaName() {
		return hastaName;
	}

	public void setHastaName(String hastaName) {
		this.hastaName = hastaName;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

}
