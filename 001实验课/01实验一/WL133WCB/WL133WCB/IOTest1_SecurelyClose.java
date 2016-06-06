import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @author Wu_Being 2016.3.22
 */
public class IOTest1_SecurelyClose {
	/**
	 * 
	 * @param filepathAndFilenameIn
	 * @param filepathAndFilenameOut
	 */
	public static void Input(String filepathAndFilenameIn,
			String filepathAndFilenameOut) {
		String filenameFrom = filepathAndFilenameIn;// 输入文件路径
		String filenameTo = filepathAndFilenameOut; // 输出文件路径
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		DataOutputStream dos = null;
		try {
			System.out.println("path metheds!");
			// 定义输入流
			fis = new FileInputStream(filenameFrom);
			bis = new BufferedInputStream(fis);
			// 定义输出流
			fos = new FileOutputStream(filenameTo);
			bos = new BufferedOutputStream(fos);
			dos = new DataOutputStream(bos);
			// 进行读写
			int b;
			while ((b = bis.read()) != -1) {
				dos.write(b);
			}
			bis.close();
			dos.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bis = null;
			}
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dos = null;
			}
		
		}

	}

	public static void main(String[] args) {
		IOTest1_SecurelyClose.Input("d:/test.txt", "d:/test1.txt");
		IOTest1_SecurelyClose.Input("D:/test1.txt", "d:/test.txt");
	}
}
