package _3_3URL���Ӧ��;
import java.net.MalformedURLException;
import java.net.URL;


public class _3_1Myurl {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			URL url = new URL("http://www.ycit.edu.cn/cn/index.htm");
			System.out.println("the Protocol	:"+url.getProtocol());
			System.out.println("the hostname	:"+url.getHost());
			System.out.println("the port		:"+url.getPort());
			System.out.println("the file		:"+url.getFile());
			System.out.println(url.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
