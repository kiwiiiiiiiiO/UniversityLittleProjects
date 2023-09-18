package N410977008_HW2;

public class Client {

	public static void main(String[] args) {
		Target target = new Target();
		new Putter(target).start();
		new Getter(target).start();
	}

}
