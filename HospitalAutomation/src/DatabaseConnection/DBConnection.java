package DatabaseConnection;

import java.sql.*;

public class DBConnection {
	Connection c = null;

	public DBConnection() {
	}

	public Connection connDb() {
		try {

			this.c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
}
