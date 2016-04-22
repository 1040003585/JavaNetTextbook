package MyIOtest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @author adminWu_Being�� Date&Time��2016-4-22 ����01:36:06��
 *         http://blog.csdn.net/woshixuye/article/details/23546081
 */
public class IOtestClose {
	/**
	 * 
	 * @param filepathAndFilenameIn
	 * @param filepathAndFilenameOut
	 */
	public static void Input(String filepathAndFilenameIn,
			String filepathAndFilenameOut) {
		String filenameFrom = filepathAndFilenameIn;// �����ļ�·��
		String filenameTo = filepathAndFilenameOut; // ����ļ�·��
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		DataOutputStream dos = null;
		try {
			System.out.println("path metheds!");
			// ����������
			fis = new FileInputStream(filenameFrom);
			bis = new BufferedInputStream(fis);
			// ���������
			fos = new FileOutputStream(filenameTo);
			bos = new BufferedOutputStream(fos);
			dos = new DataOutputStream(bos);
			// ���ж�д
			int b;
			while ((b = bis.read()) != -1) {
				dos.write(b);
			}
			bis.close();// in������رվͰ������������ص��ˣ�����Ҫ�ٶ���ȥ�ر���
			// fis.close();
			dos.close();// out������رվͰ������������ص��ˣ�����Ҫ�ٶ���ȥ�ر���
			// bos.close();//order2
			// fos.close();//order3
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					throw new RuntimeException("�ر�ʧ��");
				}
				bis = null;
			}
			if (dos != null) {
				try {
					dos.flush();
					dos.close();
				} catch (IOException e) {
					throw new RuntimeException("�ر�ʧ��");
				}
				dos = null;
			}
		}

	}

	public static void main(String[] args) {
		IOtestClose.Input("d:/test.txt", "d:/test1.txt");
		IOtestClose.Input("D:/test1.txt", "d:/test.txt");
	}
}
