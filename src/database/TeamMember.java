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
			stmt = con.prepareStatement("SELECT * " +
					   					"FROM TeamMember " +
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
		ResultSet rs;
		try {
			// Check for QualityAssurance
			stmt = con.prepareStatement("SELECT COUNT(tmid) " +
										"FROM QualityAssurance " + 
					                    "WHERE tmid = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.getInt(1) == 1)
				return TeamMemberType.QUALITY_ASSURANCE;
			
			// Check for Developer
			stmt = con.prepareStatement("SELECT COUNT(tmid) " +
										"FROM Developer " + 
					                    "WHERE tmid = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.getInt(1) == 1)
				return TeamMemberType.DEVELOPER;
			
			// Check for Manager
			stmt = con.prepareStatement("SELECT COUNT(tmid) " +
					                    "FROM Manager " + 
                                        "WHERE tmid = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.getInt(1) == 1)
				return TeamMemberType.MANAGER;
			
			// Check for Client
			stmt = con.prepareStatement("SELECT COUNT(tmid) " +
					"FROM Manager " + 
                    "WHERE tmid = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.getInt(1) == 1)
				return TeamMemberType.CLIENT;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean validate(String email, String password) {
		try {
			String query = "SELECT password " +
                           "FROM TeamMember " +
                           "WHERE email = \'" + email + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				String validation = rs.getString(1);
				return password.equals(validation.trim());
			}
			else return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
