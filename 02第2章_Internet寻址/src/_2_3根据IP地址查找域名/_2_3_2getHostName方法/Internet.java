package _2_3����IP��ַ��������._2_3_2getHostName����;

import java.net.InetAddress;

public class Internet {

	public static void main(String[] args) {
		InetAddress address;
		try {
//			address=InetAddress.getLoopbackAddress();
			address=InetAddress.getLocalHost();

			System.out.println(address.toString());	
			System.out.println("hostname="+address.getHostName());
			System.out.println("hostaddress="+address.getHostAddress());
			System.out.println("hostaddress="+address.getAddress());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
