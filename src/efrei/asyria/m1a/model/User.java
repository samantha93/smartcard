package efrei.asyria.m1a.model;

public class User {

	private int id;
	private String surname;
	private String name;
	private String adress;
	private int cp;
	private String city;
	private String phoneF;
	private String phoneM;
	private String email;
	private String cname;
	private String job;

	public User(int id, String surname, String name, String adress, int cp,
			String city, String phoneF, String phoneM, String email, String cname, String job) {
		super();
		this.id = id;
		this.surname = surname;
		this.name = name;
		this.adress = adress;
		this.cp = cp;
		this.city = city;
		this.phoneF = phoneF;
		this.phoneM = phoneM;
		this.email = email;
		this.job = job;
		this.cname = cname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneF() {
		return phoneF;
	}

	public void setPhoneF(String phoneF) {
		this.phoneF = phoneF;
	}

	public String getPhoneM() {
		return phoneM;
	}

	public void setPhoneM(String phoneM) {
		this.phoneM = phoneM;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
