package CreateInetAddressObject;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class _4_getByAddress {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		byte ip[] = new byte[] { (byte) 141, (byte) 146, 8, 66 };
		byte[] ip2 = new byte[] { (byte) 141, (byte) 146, 8, 66 };
		//getByAddress 不验证这个ip是否存在，只是简单创建一个InetAddress对象
		//addr 4位或16位（IPv6）
		try {
			InetAddress address1=InetAddress.getByAddress(ip);
			System.out.println(address1);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		InetAddress address2=InetAddress.getByAddress("Oracle 官网",ip2);
		System.out.println(address2);

	}

}
