package _6_001���߳�����;


public class Son extends Mother {
	String name="...";

	public Son(String name) {
		super(name);
		this.name = name;
	}

//	@Override
	public void run() {
		System.out.println(name);
	}

	public void startup() {
		super.start();//son��start()
		super.run();
	}
}