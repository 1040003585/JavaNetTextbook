import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * seting:File->Properties->Resource->Text File encoding->Other:select "utf-8"
 * 
 * @author Wu_Being
 * @version 0.01 time:2016.3.26
 * 
 */
public class GetHZU_EmptyClassroom {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 方法二：
		try {
//			URL hzuPeopleAPPUrl = new URL(
//					"http://1.zooo.vipsinaapp.com/miapp/index.php?aid=ks&uid=olOSijlybNLFRSBN7ub12yQQSkxY&svr=1.wechatlab.vipsinaapp.com");
			 URL hzuPeopleAPPUrl = new URL("https://www.baidu.com");//ok no
//			 URL hzuPeopleAPPUrl = new URL("http://202.192.224.137/xsxx.aspx?xh=1314080903134");//ok
				 
			// // 方法一：
			// try {
			// URLConnection urlConnection = hzuPeopleAPPUrl.openConnection();
			//
			// BufferedReader bufferedReader2 = new BufferedReader(
			// new InputStreamReader(urlConnection.getInputStream()));
			// //urlConnection.getOutputStream()//
			// } catch (IOException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }

			BufferedReader bufferedReader = null;
			try {
				InputStreamReader inputStreamReader = new InputStreamReader(hzuPeopleAPPUrl.openStream());
				bufferedReader = new BufferedReader(inputStreamReader);
//				bufferedReader = new BufferedReader(new InputStreamReader(
//						hzuPeopleAPPUrl.openStream()));
				String readline = null;
				while ((readline = bufferedReader.readLine()) != null) {
					/**
					 * deal with from this function
					 */
					//DealWithString(readline);
					System.out.println(readline);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void DealWithString(String readline) {
		// TODO Auto-generated method stub
		// if (readline.length() > 2) {
		// String subString = readline.substring(0, 2);
		// // System.out.println(subString);
		// if ("kb".equals(subString)) {
		// System.out.println(readline);
		// }
		// }
		if (readline.startsWith("kb")) {
			System.out.println(readline);
		}
	}
}
