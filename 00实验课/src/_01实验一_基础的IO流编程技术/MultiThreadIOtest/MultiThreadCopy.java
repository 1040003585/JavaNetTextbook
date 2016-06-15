package _01ʵ��һ_������IO����̼���.MultiThreadIOtest;
import java.io.*;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-14/����09:52:46
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
            //����������
            FileInputStream fis
                    = new FileInputStream(sourceName);
            BufferedInputStream bis
                    = new BufferedInputStream(fis);
            //���������
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
