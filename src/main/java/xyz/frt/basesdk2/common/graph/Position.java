package xyz.frt.basesdk2.common.graph;

public class Position implements Identify {

	private long id;
	
	private String name;
	
	private int x;
	
	private int y;
	
	private String description;
	
	private String icon;
	
	private String image;
	
	public Position() { }

	public Position(long id) {
		this.id = id;
	}

	public Position(long id, String name, int x, int y) {
		super();
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;
	}


	public Position(long id, String name, int x, int y, String description, String icon, String image) {
		super();
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;
		this.description = description;
		this.icon = icon;
		this.image = image;
	}


	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	

	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return id + "";
	}
}
