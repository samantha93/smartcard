package efrei.asyria.m1a.model;

public class Card {

	private int id;
	private User user;
	private String urlImg;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUrlImg() {
		return urlImg;
	}

	public Card(int id, User user, String urlImg) {
		super();
		this.id = id;
		this.user = user;
		this.urlImg = urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}
}
