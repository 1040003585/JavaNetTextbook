package _6_001���߳�����;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-8/����11:05:29
 * Description:
 */
public class Launch {
	public static void main(String[] args) {
		Son son = new Son("son");
		// son.start();
		son.startup();
		/**
		 * ��Ȼ���õ���super.start(),���ǵ������������������son���������� �߳�ִ�е���son��������run()������
		 * ����son�����Ѿ���д��run�������ԣ�ִ�еľ���son��������run()�����ˡ�
		 * 
		 * ���ӡ��mother�������super.run();
		 */
		// Mother mother = new Mother("mother");
		// mother.start();
	}
}