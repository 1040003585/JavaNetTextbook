package _2_3����IP��ַ��������._2_3_3getCanonicalHostName����;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetCanonicalHostName {

	public static void outHostName(InetAddress address,String s){
		System.out.println("ͨ����"+s+"����InetAdress����");
		System.out.println("��������"+address.getCanonicalHostName());
		System.out.println("����������"+address.getHostAddress());
		System.out.println("");
		
	}
	
	public static void main(String[] args) throws UnknownHostException {
		//1
		outHostName(InetAddress.getLocalHost(), "getLocalHost����");
		//2 ��dns�й�
		outHostName(InetAddress.getByName("www.ibm.com"), "www.ibm.com");
		outHostName(InetAddress.getByName("www.123.com"), "www.123.com");
		outHostName(InetAddress.getByName("www.ycit.com"), "www.ycit.com");
		//3
		outHostName(InetAddress.getByName("202.192.133.1"), "202.192.133.1");
		outHostName(InetAddress.getByName("202.192.133.1"), "202.192.133.1");
		outHostName(InetAddress.getByName("202.192.133.1"), "202.192.133.1");
		
			
	}
}
