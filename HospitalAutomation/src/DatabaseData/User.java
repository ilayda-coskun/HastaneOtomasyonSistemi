package DatabaseData;

import java.sql.SQLException;
import DatabaseConnection.DBConnection;

public class User {

	private int id;
	private String UserName, UserPass, name, type;

	DBConnection conn = new DBConnection();

	public User() {
	}

	public User(int id, String userName, String userPass, String name, String type) {
		this.id = id;
		UserName = userName;
		UserPass = userPass;
		this.name = name;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserPass() {
		return UserPass;
	}

	public void setUserPass(String userPass) {
		UserPass = userPass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
