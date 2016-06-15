package JavaMultiThreadHttpProxy;

import java.io.*;
import java.net.*;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 * 
 * FileName: .java
 * 
 * @author : Wu_Being <1040003585@qq.comb> Date/Time: 2016-6-15/下午01:31:49
 *         Description: http://ethen.iteye.com/blog/783338
 */
public class MyHttpProxy extends Thread {
	private static final String LOGFILENAME_S = null;
	private static final String LOGFILENAME_C = null;
	static public int CONNECT_RETRIES = 5; // 尝试与目标主机连接次数
	static public int CONNECT_PAUSE = 5; // 每次建立连接的间隔时间
	static public int TIMEOUT = 50; // 每次尝试连接的最大时间
	static public int BUFSIZ = 1024; // 缓冲区最大字节数
	static public boolean logging = false; // 是否记录日志
	static public OutputStream log_S = null; // 日志输出流
	static public OutputStream log_C = null; // 日志输出流
	// static public String LOGFILENAME_S="log_S.txt";
	// static public String LOGFILENAME_C="log_C.txt";
	// 与客户端相连的Socket
	protected Socket csocket;

	public MyHttpProxy(Socket cs) {
		csocket = cs;
		start();
	}

	public void writeLog(int c, boolean browser) throws IOException {
		if (browser)
			log_C.write((char) c);
		else
			log_S.write((char) c);
	}

	public void writeLog(byte[] bytes, int offset, int len, boolean browser)
			throws IOException {
		for (int i = 0; i < len; i++)
			writeLog((int) bytes[offset + i], browser);
	}

	public void run() {
		String buffer = ""; // 读取请求头
		String URL = ""; // 读取请求URL
		String host = ""; // 读取目标主机host
		int port = 80; // 默认端口80
		Socket ssocket = null;
		// cis为客户端输入流，sis为目标主机输入流
		InputStream cis = null, sis = null;
		// cos为客户端输出流，sos为目标主机输出流
		OutputStream cos = null, sos = null;
		try {
			csocket.setSoTimeout(TIMEOUT);
			cis = csocket.getInputStream();
			cos = csocket.getOutputStream();
			while (true) {
				int c = cis.read();
				if (c == -1)
					break; // -1为结尾标志
				if (c == '\r' || c == '\n')
					break;// 读入第一行数据
				buffer = buffer + (char) c;
				if (logging)
					writeLog(c, true);
			}
			// 抽取URL(<a href="http://www.baidu.com/">http://www.baidu.com/</a>)
			URL = getRequestURL(buffer);

			int n;
			// 抽取host
			n = URL.indexOf("//");
			if (n != -1)
				host = URL.substring(n + 2); // www.baidu.com/
			n = host.indexOf('/');
			if (n != -1)
				host = host.substring(0, n);// www.baidu.com

			// 分析可能存在的端口号
			n = host.indexOf(':');
			if (n != -1) {
				port = Integer.parseInt(host.substring(n + 1));
				host = host.substring(0, n);
			}
			int retry = CONNECT_RETRIES;
			while (retry-- != 0) {
				try {
					ssocket = new Socket(host, port); // 尝试建立与目标主机的连接
					break;
				} catch (Exception e) {
				}
				// 等待
				Thread.sleep(CONNECT_PAUSE);
			}
			if (ssocket != null) {
				ssocket.setSoTimeout(TIMEOUT);
				sis = ssocket.getInputStream();
				sos = ssocket.getOutputStream();
				sos.write(buffer.getBytes()); // 将请求头写入
				pipe(cis, sis, sos, cos); // 建立通信管道
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				csocket.close();
				cis.close();
				cos.close();
			} catch (Exception e1) {
				System.out.println("\nClient Socket Closed Exception:");
				e1.printStackTrace();
			}
			try {
				ssocket.close();
				sis.close();
				sos.close();
			} catch (Exception e2) {
				System.out.println("\nServer Socket Closed Exception:");
				e2.printStackTrace();
			}
		}
	}

	public String getRequestURL(String buffer) {
		String[] tokens = buffer.split(" ");
		String URL = "";
		for (int index = 0; index < tokens.length; index++) {
			if (tokens[index].startsWith("http://")) {
				URL = tokens[index];
				break;
			}
		}
		return URL;
	}

	public void pipe(InputStream cis,InputStream sis,OutputStream sos,OutputStream cos){
        	try {
        	    int length;
        	    byte bytes[]=new byte[BUFSIZ];
        	    while (true) {
        	    	try {
        	    		if ((length=cis.read(bytes))>0) {
        	    			sos.write(bytes,0,length);
        	    			if (logging) writeLog(bytes,0,length,true); 	    			
        	    		}
        	    		else if (length<0)
        	    			break;
        	    	}
        	    	catch(SocketTimeoutException e){}
        	    	catch (InterruptedIOException e) { 
        	    		System.out.println("\nRequest Exception:");
        	    		e.printStackTrace();
        	    	}
        	    	try {
        	    		if ((length=sis.read(bytes))>0) {
        	    			cos.write(bytes,0,length);
        	    			if (logging) writeLog(bytes,0,length,false);
        	    		}
        	    		else if (length<0) 
        	    			break;
        	    	}
        	    	catch(SocketTimeoutException e){}
        	    	catch (InterruptedIOException e) {
        	    		System.out.println("\nResponse Exception:");
        		    	e.printStackTrace();
        	    	}
        	    }
        	} catch (Exception e0) {
        	    System.out.println("Pipe异常: " + e0);
        	}
        }

	public static void startProxy(int port, Class clobj) {
		try {
			ServerSocket ssock = new ServerSocket(port);
			while (true) {
				Class[] sarg = new Class[1];
				Object[] arg = new Object[1];
				sarg[0] = Socket.class;
				try {
					java.lang.reflect.Constructor cons = clobj
							.getDeclaredConstructor(sarg);
					arg[0] = ssock.accept();
					cons.newInstance(arg); // 创建HttpProxy或其派生类的实例
				} catch (Exception e) {
					Socket esock = (Socket) arg[0];
					try {
						esock.close();
					} catch (Exception ec) {
					}
				}
			}
		} catch (IOException e) {
			System.out.println("\nStartProxy Exception:");
			e.printStackTrace();
		}
	}

	// 测试用的简单main方法
	static public void main(String args[]) throws FileNotFoundException {
		System.out.println("在端口808启动代理服务器\n");
		OutputStream file_S = new FileOutputStream(new File(LOGFILENAME_S));
		OutputStream file_C = new FileOutputStream(new File(LOGFILENAME_C));
		MyHttpProxy.log_S = file_S;
		MyHttpProxy.log_C = file_C;
		MyHttpProxy.logging = true;
		MyHttpProxy.startProxy(808, MyHttpProxy.class);
	}

}
