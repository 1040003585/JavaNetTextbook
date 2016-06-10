package _3_2URL¿‡;
import java.net.MalformedURLException;

public class URL {

	public static void main(String[] args) throws MalformedURLException {
		// 1
		java.net.URL url = new java.net.URL("http://www.baidu.com");

		// 2
		java.net.URL url2 = new java.net.URL("http", "www.abc.com",
				"/local/searchresult.html");
		//3
		java.net.URL base = new java.net.URL("http://www.abc.com:80/x-file/1112.html");
		java.net.URL loc = new java.net.URL(base,"#change");
		
		
	}
	

}