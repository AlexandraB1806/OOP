package lab10.builder;

public class Main {
	public static void main(String[] args) {
		House house = new House.HouseBuilder("Str Dacia", 5, 2, true)
				.wallsColor("White")
				.hasDogHouse(true)
				.security(true)
				.pool(true)
				.build();
		System.out.println(house);
	}
}
