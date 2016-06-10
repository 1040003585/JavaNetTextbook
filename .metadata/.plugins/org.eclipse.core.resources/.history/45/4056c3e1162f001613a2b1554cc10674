package _3_5URLConnection类的应用;
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
public class URLDome4readLine_wu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("starting...");
		try {
			URL url=new URL("http://www.baidu.com");
			InputStream is=url.openStream();
			int i;
			String string="";
			while((i=is.read())!=-1){
				char c=(char)i;
				string+=c;
				if(c=='\n'){
					System.out.print(string);
					string="";
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
