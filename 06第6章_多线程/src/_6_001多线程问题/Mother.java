package _6_001���߳�����;

public class Mother extends Thread {
	String name;

	public Mother(String name) {
		this.name = name;
		System.out.println("M"+name);
	}

	@Override
	public void run() {
		System.out.println("M1"+name);
	}
}