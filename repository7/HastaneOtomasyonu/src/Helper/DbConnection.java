package Helper;

import java.sql.*;

public class DbConnection {

	Connection c = null;

	public DbConnection() {
	}

	public Connection connDb() {

		try {
			this.c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/hospital?user=root&password=123123");
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

}
