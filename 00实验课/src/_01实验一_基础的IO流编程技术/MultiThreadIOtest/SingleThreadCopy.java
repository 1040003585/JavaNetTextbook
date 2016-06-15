package _01实验一_基础的IO流编程技术.MultiThreadIOtest;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-14/下午09:52:53
 * Description:
 */
public class SingleThreadCopy implements Runnable {
	private String sourceFileName;
	private String targetFileName;
	private int blockCount;
	private int blockNo;
	private int maxBuffSize = 1024 * 1024;

	public SingleThreadCopy(String sourceFileName, String targetFileName,
			int blockCount, int blockNo) {
		this.sourceFileName = sourceFileName;
		this.targetFileName = targetFileName;
		this.blockCount = blockCount;
		this.blockNo = blockNo;
	}

	public void run() {
		File file = new File(sourceFileName);
		long size = file.length();
		long blockLenth = size / blockCount;
		long startPosition = blockLenth * blockNo;
		byte[] buff = new byte[maxBuffSize];
		try {
			InputStream inputStream = new FileInputStream(sourceFileName);
			RandomAccessFile raf = new RandomAccessFile(targetFileName, "rw");
			raf.seek(startPosition);
			int curRedLength;
			int totalRedLength = 0;
			inputStream.skip(startPosition);
			while ((curRedLength = inputStream.read(buff)) > 0
					&& totalRedLength < blockLenth) {
				raf.write(buff, 0, curRedLength);
				totalRedLength += curRedLength;
			}
			raf.close();
			inputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}