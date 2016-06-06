import java.io.*;

public class IOTest0_Simple {
	public static void main(String args[]) {
		String s_FileName = "d:/test1.txt";
		String s_DFileName = "d:/test2.txt";
		try {
			// ����������
			FileInputStream fis = new FileInputStream(s_FileName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			// ���������
			FileOutputStream fos = new FileOutputStream(s_DFileName);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			DataOutputStream dos = new DataOutputStream(bos);
			int b;
			while ((b = bis.read()) != -1) {
				dos.write(b);
			}
			bis.close();
			dos.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}