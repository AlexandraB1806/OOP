package lab10.builder;

public class House {

	// Optional and mandatory facilities to have in a house
	private String location; // mandatory
	private int numberOfRooms; // mandatory
	private int numberOfFloors;	// mandatory
	private boolean heat; // mandatory
	private String wallsColor;	// optional
	private boolean hasDogHouse; // optional
	private boolean security; // optional
	private boolean pool; // optional

	// Completed the private constructor
	private House(HouseBuilder builder) {
		this.location = builder.location;
		this.numberOfRooms = builder.numberOfRooms;
		this.numberOfFloors = builder.numberOfFloors;
		this.heat = builder.heat;
		this.wallsColor = builder.wallsColor;
		this.hasDogHouse = builder.hasDogHouse;
		this.security = builder.security;
		this.pool = builder.pool;
	}

	// Generated getters
	public String getLocation() {
		return location;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public int getNumberOfFloors() {
		return numberOfFloors;
	}

	public boolean isHeat() {
		return heat;
	}

	public String getWallsColor() {
		return wallsColor;
	}

	public boolean isHasDogHouse() {
		return hasDogHouse;
	}

	public boolean isSecurity() {
		return security;
	}

	public boolean isPool() {
		return pool;
	}

	@Override
	public String toString() {
		return "House{" +
				"location='" + location + '\'' +
				", numberOfRooms=" + numberOfRooms +
				", numberOfFloors=" + numberOfFloors +
				", heat=" + heat +
				", wallsColor='" + wallsColor + '\'' +
				", hasDogHouse=" + hasDogHouse +
				", security=" + security +
				", pool=" + pool +
				'}';
	}

	public static class HouseBuilder {

		// Written some facilities
		private String location; // mandatory
		private int numberOfRooms; // mandatory
		private int numberOfFloors;	// mandatory
		private boolean heat; // mandatory
		private String wallsColor = "";	// optional
		private boolean hasDogHouse = false; // optional
		private boolean security = false; // optional
		private boolean pool = false; // optional

		// Completed the house builder constructor only with the mandatory facilities
		public HouseBuilder(String location, int numberOfRooms,
							int numberOfFloors, boolean heat) {
			this.location = location;
			this.numberOfRooms = numberOfRooms;
			this.numberOfFloors = numberOfFloors;
			this.heat = heat;
		}

		// Added the optional facilities in a builder design
		public HouseBuilder wallsColor(String wallsColor) {
			this.wallsColor = wallsColor;
			return this;
		}

		public HouseBuilder hasDogHouse(boolean hasDogHouse) {
			this.hasDogHouse = hasDogHouse;
			return this;
		}

		public HouseBuilder security(boolean security) {
			this.security = security;
			return this;
		}

		public HouseBuilder pool(boolean pool) {
			this.pool = pool;
			return this;
		}

		// Completed the final build method
		public House build() {
			return new House(this);
		}
	}
}
