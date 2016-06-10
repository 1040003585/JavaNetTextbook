package _3_5URLConnection类的应用._3_5_1从Web服务器中读取文件的信息;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: URLDome3readLine2.java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-7/下午02:17:16
 * Description: 获取网页Html内容提要,DataInputStream效率比较快
 */
public class URLDome3readLine2 {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("starting...");
		try {
			URL url=new URL("http://www.baidu.com");
			InputStream is=url.openStream();
			DataInputStream dis = new DataInputStream(is);
			String string=null;
			while((string=dis.readLine())!=null){
				System.out.println(string);
			}
		} catch (MalformedURLException e) {
			//URL构造参数字符不符合URL，或传输协议不是Java所能接受
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
