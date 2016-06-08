package MyIOtest;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * http://www.cnblogs.com/qqzy168/p/3670915.html
 * 
 * @author adminWu_Being； Date&Time：2016-4-22 下午01:51:19；
 */
public class IOCloseOrder {

		public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream("d:\\a.txt");
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write("java IO close test");

		// 从内带外顺序顺序会报异常
		// fos.close();
		// osw.close();
		// bw.close();
		// 从外到内顺序关闭ok
		bw.close();
		osw.close();
		fos.close();

	}
}
/**
 * 一般情况下是：先打开的后关闭，后打开的先关闭
 * 
 * 另一种情况：看依赖关系，如果流a依赖流b，应该先关闭流a，再关闭流b
 * 
 * 例如处理流a依赖节点流b，应该先关闭处理流a，再关闭节点流b
 * 
 * 当然完全可以只关闭处理流，不用关闭节点流。处理流关闭的时候，会调用其处理的节点流的关闭方法
 * 
 * 如果将节点流关闭以后再关闭处理流，会抛出IO异常；
 */
