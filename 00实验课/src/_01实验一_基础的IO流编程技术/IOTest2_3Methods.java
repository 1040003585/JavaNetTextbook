package _01实验一_基础的IO流编程技术;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOTest2_3Methods {

	/**
	 * @param args
	 */
	static final String file1 = "D:/w1.txt";
	static final String file2 = "D:/w2.txt";
	static final String file3 = "D:/w3.txt";

	public static void main(String[] args) {
//		//关于System.in
//		int data;
//		try {
//			while ((data = System.in.read()) != -1) {
//				// Ctrl+c结束输入
//				System.out.print(data+" ");
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		// 下面的程序段用以向file1文件写入,把其内容的一部分复制到file2
		// 再把file2文件完整地复制到file3,并打印出来
			try {
				FileOutputStream fos1 = new FileOutputStream(file1);
				FileOutputStream fos2 = new FileOutputStream(file2);
				FileOutputStream fos3 = new FileOutputStream(file3);
				//向file1文件写入	//fos1.write!!!!
				fos1.write("下下个周未就是2016年4月4日清明节了，我又要回去祭祖了".getBytes());
				fos1.close();
				//把其内容的一部分复制到file2
				FileInputStream fis1=new FileInputStream(file1);
				fis1.skip(20);//跳过20个字节
				byte[] buf = new byte[fis1.available()];
				fis1.read(buf);//fis1->buf
				fis1.close();
				System.out.println(new String(buf));
				fos2.write(buf);
				fos2.close();
				//再把file2文件完整地复制到file3
				FileInputStream fis2 = new FileInputStream(file2);
				while(fis2.available()>0){
					byte[] b = new byte[fis2.available()];
					int let=fis2.read(b);
					if(let==-1)break;
					fos3.write(b, 0, let);
				}
				System.out.println("复制成功！");
				fis2.close();
				fos3.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
}
