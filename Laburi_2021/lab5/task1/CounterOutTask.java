package lab5.task1;

public class CounterOutTask implements Task {
	private static int count;

	public static int getCount() {
		return count;
	}
	public static void setCount(int count) {
		CounterOutTask.count = count;
	}

	public CounterOutTask() {}

	@Override
	public void execute() {
		count++;
		System.out.println("Functia a fost apelata de " + count + " ori");
	}
}
