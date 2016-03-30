package connection;

public class Session {
	private int id;
	private String name;
	private String type;	
	
	public Session(String name, int id, String type) {
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
	
	public String getType() {
		return type;
	}
}
