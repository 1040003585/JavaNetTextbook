import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: URLDome.java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-6/下午09:09:59
 * Description: 获取网页Html内容提要
 */
public class URLDome2read {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("starting...");
		int c;
		try {
			URL url=new URL("https://www.baidu.com");
			InputStream is=url.openStream();
			while((c=is.read())!=-1){
				System.out.print((char)c);
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
