package JavaLightWeightHttpProxy;

import java.io.IOException;

public class HttpProxyServer {

	public static void main(String[] args) {
		try {
			new HttpProxy(8888);
		} catch (IOException ioe) {
			System.err.println("Couldn't start server:\n" + ioe);
			System.exit(-1);
		}
		System.out.println("start!");
		try {
			System.in.read();
		} catch (Throwable t) {
		}
		System.out.println("stop!");
	}
}
