package Model;

import Helper.DbConnection;

public class user {
	private int id;
	private String name, tcno, password, type;
	DbConnection conn = new DbConnection();

	public user(int id, String name, String tcno, String password, String type) {

		this.id = id;
		this.name = name;
		this.tcno = tcno;
		this.password = password;
		this.type = type;

	}

	public user() {
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

	public String getTcno() {
		return tcno;
	}

	public void setTcno(String tcno) {
		this.tcno = tcno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
