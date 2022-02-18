package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Helper.DbConnection;

public class wHour extends doctor {

	int id, doctor_id;
	String name, wdate, status;

	public wHour(int id, String name, String tcno, String password, String type) {
		super(id, name, tcno, password, type);
		// TODO Auto-generated constructor stub
	}

	DbConnection conn = new DbConnection();
	Connection con = conn.connDb();
	PreparedStatement prepareStatement = null;
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public wHour() {
	}

	public wHour(int id, int doctor_id, String name, String wdate, String status) {
		super();
		this.id = id;
		this.doctor_id = doctor_id;
		this.name = name;
		this.wdate = wdate;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
