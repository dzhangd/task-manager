package connection;

public class Session {
	private int id;
	private String name;
	private enum UserType
	{
		superUser, manager, developer, qa, client
	}
	private UserType type;
	
	public Session(String name, int id, UserType type) {
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
	
	public UserType getType() {
		return type;
	}
}