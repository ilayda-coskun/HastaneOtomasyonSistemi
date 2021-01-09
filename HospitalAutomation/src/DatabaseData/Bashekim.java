package DatabaseData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bashekim extends User {
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Bashekim() {
	}

	public Bashekim(int id, String userName, String userPass, String name, String type) {
		super(id, userName, userPass, name, type);
	}

	public ArrayList<User> getDoctorList() throws SQLException {
		ArrayList<User> list = new ArrayList<>();

		User obj;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = conn.connDb();
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE type='doktor'");
			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("UserName"), rs.getString("UserPass"),
						rs.getString("name"), rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<User> getDoctorListele(String doctorName) throws SQLException {
		ArrayList<User> list = new ArrayList<>();

		User obj;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = conn.connDb();
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE name like '" + doctorName + "%' AND type='doktor'");
			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("UserName"), rs.getString("UserPass"),
						rs.getString("name"), rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<User> getClinicDoctorList(int clinic_id) throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = conn.connDb();
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT u.id,u.UserName,u.type,u.name,u.UserPass FROM worker w LEFT JOIN user u ON w.user_id = u.id WHERE clinic_id="
							+ clinic_id);
			while (rs.next()) {
				obj = new User(rs.getInt("u.id"), rs.getString("u.UserName"), rs.getString("u.UserPass"),
						rs.getString("u.name"), rs.getString("u.type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public boolean addDoctor(String UserName, String UserPass, String name) throws SQLException {

		String query = "INSERT INTO user" + "(UserName,UserPass,name,type) VALUES" + "(?,?,?,?)";
		boolean key = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = conn.connDb();
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, UserName);
			preparedStatement.setString(2, UserPass);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "doktor");
			preparedStatement.executeUpdate();
			key = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}

	public boolean deleteDoctor(int id) throws SQLException {

		String query = "DELETE FROM user WHERE id = ?";
		boolean key = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = conn.connDb();
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}

	public boolean updateDoctor(int id, String UserName, String UserPass, String name) throws SQLException {

		String query = "UPDATE user SET name = ?, UserName = ?, UserPass = ? WHERE id = ?";
		boolean key = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = conn.connDb();
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, UserName);
			preparedStatement.setString(3, UserPass);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}

	public boolean addWorker(int user_id, int clinic_id) throws SQLException {

		String query = "INSERT INTO worker" + "(user_id,clinic_id) VALUES" + "(?,?)";
		boolean key = false;
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = conn.connDb();
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM worker WHERE clinic_id=" + clinic_id + " AND user_id=" + user_id);
			while (rs.next()) {
				count++;
			}
			if (count == 0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, user_id);
				preparedStatement.setInt(2, clinic_id);
				preparedStatement.executeUpdate();
			}
			key = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}

}
