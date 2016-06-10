package _01实验一_基础的IO流编程技术.MyIOtest;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyFile {
	public static void main(String[] args) {
		FileReader fr = null;
		FileWriter fw = null;
		try {
			fr = new FileReader("D:\\test.txt");
			fw = new FileWriter("D:\\xy2.txt");
			char[] charBuffer = new char[1024];
			int len = 0;
			while ((len = fr.read(charBuffer)) != -1) {
				fw.write(charBuffer, 0, len);
				System.out.println(len);
			}
			System.out.println("文件复制成功");
		} catch (IOException e) {
			throw new RuntimeException("文件拷贝操作失败");
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("关闭失败");
			}

			try {
				if (null != fw) {
					fw.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("关闭失败");
			}
		}
	}
}
