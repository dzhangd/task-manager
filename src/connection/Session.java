package connection;

import database.TeamMemberType;

public class Session {
	private int id;
	private String name;
	TeamMemberType type;;	
	
	public Session(String name, int id, TeamMemberType type) {
		this.name = name;
		this.id = id;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public TeamMemberType getType() {
		return type;
	}
}
