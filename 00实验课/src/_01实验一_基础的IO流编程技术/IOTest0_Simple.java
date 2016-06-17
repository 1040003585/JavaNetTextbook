package _01实验一_基础的IO流编程技术;

import java.io.*;
/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-17/下午05:22:11
 * Description:
 */
public class IOTest0_Simple {
	public static void main(String args[]) {
		String s_FileName = "d:/test1.txt";
		String s_DFileName = "d:/test2.txt";
		try {
			// 定义输入流
			FileInputStream fis = new FileInputStream(s_FileName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			// 定义输出流
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