package JavaHttpProxy;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 4. 写入线程，负责读取请求端数据，写入到目标端
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-15/下午01:11:23
 * Description:
 */
public class SocketThreadOutput extends Thread {
    private InputStream isIn;
    private OutputStream osOut;

    public SocketThreadOutput(InputStream isIn, OutputStream osOut) {
        this.isIn = isIn;
        this.osOut = osOut;
    }

    private byte[] buffer = new byte[409600];

    public void run() {
        try {
            int len;
            while ((len = isIn.read(buffer)) != -1) {
                if (len > 0) {
                    System.out.println(new String(buffer, 0, len));
                    osOut.write(buffer, 0, len);
                    osOut.flush();
                }
            }
        } catch (Exception e) {
            System.out.println("SocketThreadOutput leave");
        }
    }
}