package DatabaseData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DatabaseConnection.Mesaj;

public class Hasta extends User {

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Hasta() {
	}

	public Hasta(int id, String userName, String userPass, String name, String type) {
		super(id, userName, userPass, name, type);
	}

	public boolean register(String userName, String userPass, String name) throws SQLException {
		int key = 0;
		boolean duplicate = false;
		String query = "INSERT INTO user" + "(userName, userPass ,name , type) VALUES" + "(?,?,?,?)";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = conn.connDb();
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE userName = '" + userName + "'");

			while (rs.next()) {
				duplicate = true;
				Mesaj.showMsg("Bu T.C. Numarasýna ait kayýt bulunmaktadýr !");
				break;
			}

			if (!duplicate) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, userName);
				preparedStatement.setString(2, userPass);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "hasta");
				preparedStatement.executeUpdate();
				key = 1;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (key == 1)
			return true;
		else
			return false;
	}

	public boolean addRandevu(int doctor_id, int hasta_id, String doctor_name, String hasta_name, String appDate)
			throws SQLException {
		int key = 0;
		String query = "INSERT INTO randevu" + "(doctor_id, doctor_name ,hasta_id , hasta_name, app_date) VALUES"
				+ "(?,?,?,?,?)";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = conn.connDb();
			// st = con.createStatement();

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doctor_id);
			preparedStatement.setString(2, doctor_name);
			preparedStatement.setInt(3, hasta_id);
			preparedStatement.setString(4, hasta_name);
			preparedStatement.setString(5, appDate);
			preparedStatement.executeUpdate();
			key = 1;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (key == 1)
			return true;
		else
			return false;
	}

	public boolean updateWhourStatus(int doctor_id, String wDate) throws SQLException {
		int key = 0;
		String query = "UPDATE whour SET status = ? WHERE doctor_id = ? AND wdate = ?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = conn.connDb();
			// st = con.createStatement();

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "p");
			preparedStatement.setInt(2, doctor_id);
			preparedStatement.setString(3, wDate);
			preparedStatement.executeUpdate();
			key = 1;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (key == 1)
			return true;
		else
			return false;
	}
}
