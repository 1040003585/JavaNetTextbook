package _2_2������������IP��ַ._2_2_1getHostAddress����;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class GetIP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InetAddress ad=InetAddress.getByName("www.nju.edu.cn");
			System.out.println("IP��ַΪ:"+ad.getHostAddress());
				
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
