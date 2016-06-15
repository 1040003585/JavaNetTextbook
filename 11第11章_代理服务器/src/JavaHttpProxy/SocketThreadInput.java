package JavaHttpProxy;


import java.io.InputStream;
import java.io.OutputStream;
/**
 * 3.  读取线程，负责外面读数据，写入到请求端
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-15/下午01:09:58
 * Description:
 */
public class SocketThreadInput extends Thread {
	private InputStream isOut;
	private OutputStream osIn;

	public SocketThreadInput(InputStream isOut, OutputStream osIn) {
		this.isOut = isOut;
		this.osIn = osIn;
	}

	private byte[] buffer = new byte[409600];

	public void run() {
		try {
			int len;
			while ((len = isOut.read(buffer)) != -1) {
				if (len > 0) {
					System.out.println(new String(buffer, 0, len));
					osIn.write(buffer, 0, len);
					osIn.flush();
				}
			}
		} catch (Exception e) {
			System.out.println("SocketThreadInput leave");
		}
	}
}
