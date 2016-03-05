package inet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyInetAddress2_getByName {

	/**
	 * @param args
	 *  java inet.MyInetAddress2_getByName www.baidu.com
	 *  java inet.MyInetAddress2_getByName localhost
	 *  java inet.MyInetAddress2_getByName wubeing-EC18-Series
	 *  java inet.MyInetAddress2_getByName 192.168.1.1
	 *  
	 *  C:\WINDOWS\system32\drivers\etc 的文件hosts中添加：192.168.1.100 www.mysite.com
	 *  java inet.MyInetAddress2_getByName www.mysite.com
	 * 
	 *  output:
	 *  www.baidu.com/119.75.217.109
	 */
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.print("please input args");
			return;
		}
		String host = args[0];
		try {
			InetAddress address = InetAddress.getByName(host);
			System.out.println(address);
		} catch (UnknownHostException el) {
			// TODO Auto-generated catch block
			el.printStackTrace();
		}
	}

}
