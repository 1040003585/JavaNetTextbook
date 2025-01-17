package _01实验一_基础的IO流编程技术.MultiThreadIOtest;
import java.io.*;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-14/下午09:52:46
 * Description:
 */
public class MultiThreadCopy {

    private String sourceName;
    private String targetName;

    public MultiThreadCopy(String sourceName, String targetName) {
        this.sourceName = sourceName;
        this.targetName = targetName;
    }

    public void copy() {
        try {
            //定义输入流
            FileInputStream fis
                    = new FileInputStream(sourceName);
            BufferedInputStream bis
                    = new BufferedInputStream(fis);
            //定义输出流
            FileOutputStream fos
                    = new FileOutputStream(targetName);
            BufferedOutputStream bos
                    = new BufferedOutputStream(fos);
            DataOutputStream dos
                    = new DataOutputStream(bos);
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
