package bean;

public class UserBean {
	
	private int id;
	private String email;
	private String fname;
	private String lname;
	
	public UserBean(int id, String email, String fname, String lname) {
		super();
		this.id = id;
		this.email = email;
		this.fname = fname;
		this.lname = lname;
	}
	public int getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	public String getFname() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	
}
