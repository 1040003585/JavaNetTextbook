package MyIOtest;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * http://www.cnblogs.com/qqzy168/p/3670915.html
 * 
 * @author adminWu_Being�� Date&Time��2016-4-22 ����01:51:19��
 */
public class IOCloseOrder {

		public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream("d:\\a.txt");
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write("java IO close test");

		// ���ڴ���˳��˳��ᱨ�쳣
		// fos.close();
		// osw.close();
		// bw.close();
		// ���⵽��˳��ر�ok
		bw.close();
		osw.close();
		fos.close();

	}
}
/**
 * һ��������ǣ��ȴ򿪵ĺ�رգ���򿪵��ȹر�
 * 
 * ��һ���������������ϵ�������a������b��Ӧ���ȹر���a���ٹر���b
 * 
 * ���紦����a�����ڵ���b��Ӧ���ȹرմ�����a���ٹرսڵ���b
 * 
 * ��Ȼ��ȫ����ֻ�رմ����������ùرսڵ������������رյ�ʱ�򣬻�����䴦��Ľڵ����Ĺرշ���
 * 
 * ������ڵ����ر��Ժ��ٹرմ����������׳�IO�쳣��
 */
