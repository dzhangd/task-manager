package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.DatabaseConnection;

public class TeamMember {
	static Connection con = DatabaseConnection.getConnection();
	
	public static ResultSet findTeamMemberByEmail(String email) {
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT *" +
					   					"FROM TeamMember" +
					 					"WHERE email = ?");
			stmt.setString(1, email);
			return stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static TeamMemberType findTypeById(int id) {
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT COUNT(?)" +
										"FROM QualityAssurance")
		}
	}
	
}
