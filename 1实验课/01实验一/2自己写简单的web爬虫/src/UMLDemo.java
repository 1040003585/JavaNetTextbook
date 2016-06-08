import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;


public class UMLDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("starting.....");
		int c;
		try {
			URL url = new URL("https://www.baidu.com");
			URLConnection urlConnection=url.openConnection();
			System.out.println("the data is:" +new Date(urlConnection.getDate()));
			System.out.println("content_type:"+urlConnection.getContentType());
			InputStream inputStream=urlConnection.getInputStream();
			while((c=inputStream.read())!=-1){
				System.out.print((char)c);
			}
			inputStream.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
