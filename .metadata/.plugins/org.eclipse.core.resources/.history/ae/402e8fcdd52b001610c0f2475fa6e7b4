package _11_2用Java开发代理服务器;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: HttpProxy.java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-6/上午10:29:01
 * Description: 一个基础的代理服务器类
 * 一个代理服务器的基本设计： 
 * （1）等待来自客户（Web浏览器）的请求 
 * （2）启动一个新的线程，已处理客户连接请求
 * （3）读取浏览器请求的第一行（该行内容包含了请求的目标URL） 
 * （4）分析请求的第一行内容，得到目标服务器的名字和端口
 * （5）打开一个通向目标服务器（或下一个代理服务器）的Socket 
 * （6）把请求的第一行发送到输出Socket 
 * （7）把请求的剩余部分发送到输出Socket
 * （8）把目标Web服务器返回的数据发送给发出请求的浏览器
 */
public class HttpProxy extends Thread{

	public static final int CONNECT_RETRIES = 5;	//在放弃之前尝试连接远程主机的次数
	public static final int CONNECT_PAUSE = 5;		//在两次连接尝试之间的暂停时间
	public static final int TIME_OUT = 50;			//等待Socket输入的等待时间
	public static final int BUFFSIZ = 1024;			//Socket输入的缓冲大小
	public static boolean logging = false;			//是否要求代理服务器在日志中记录所有已传输的数据
	//一个OutputStream对象，默认日志例程将向该OutputStream对象输出日志信息	
	public static OutputStream log_s = null;		// Web主机输出流
	public static OutputStream log_b = null;		// 浏览器输出流
	
	//传入数据用的Socket
	protected Socket socket;
	
	//上级代理服务器（可选）
	private static String parent=null;
	private static int parentPort=-1;
	public static void setParentProxy(String name,int port){
		parent=name;
		parentPort=port;
	}
	
	/**
	 * 构造方法：在给定Socket上创建一个代理线程
	 */
    public HttpProxy(Socket s) {
		// TODO Auto-generated constructor stub
    	socket=s;
    	start();
	}

    /**
     * 
     * @param c
     * @param browser
     * @throws IOException
     */
    public void writeLog(int c,boolean browser) throws IOException{
    	if(browser){
    		log_b.write(c);
    	}else{
    		log_s.write(c);
    	}
    }
    /**
     * 
     * @param bytes
     * @param offset
     * @param len
     * @param browser
     * @throws IOException
     */
    public void writeLog(byte[] bytes,int offset,int len,boolean browser) throws IOException{
    	for (int i = 0; i < len; i++) {
			writeLog((int)bytes[offset+i],browser);
		}
    }
    
    /**
     * 默认情况下，日志信息输出到标准输出设备，派生类可以覆盖它。
     * @param url 
     * @param host
     * @param port
     * @param sock
     * @return
     */
    public String proccessHostName(String url,String host,int port,Socket sock){
    	java.text.DateFormat cal=java.text.DateFormat.getDateTimeInstance();
    	System.out.println(cal.format(new java.util.Date()) + " - " + url 
    			+ " - " + sock.getInetAddress() + "<BR>");
    	return host;
    }
 
    
    /**
     * 执行操作的线程
     */
    //@Override 
    public void run(){
    	String line;
    	String host;
    	int port=80;
    	Socket outboundsocket=null;
    	try {
			socket.setSoTimeout(TIME_OUT);
			InputStream is=socket.getInputStream();
			OutputStream os=null;
			
			try {
				//获取请求行的内容
				line="";
				host="";
				int state=0;
				boolean isspace = false;
				while(true){
					int c=is.read();				//读取一个字符
					if(c==-1)break;					//
					if(logging)writeLog(c, true);
					switch (state) {
						case 0:	
							if(isspace)continue;
							state=1;
						case 1:
							if(isspace){
								state=2;
								continue;
							}
							line+=(char)c;
							break;
						case 2:
							if(isspace)continue;
							state=3;
						case 3:
							if(isspace){
								state=4;
								//只分析主机名称部分
								String host0=host;
								int n=0;
								n=host.indexOf("//");
								if(n!=-1)host.substring(n+2);
								n=host.indexOf('/');
								if(n!=-1)host=host.substring(0,n);
								//分析可能存在的端口号
								n=host.indexOf(":");
								if(n!=-1){
									port=Integer.parseInt(host.substring(n+1));
									host=host.substring(0,n);
								}
								//
								host=proccessHostName(host0, host, port, socket);
								//
								if(parent!=null){
									host=parent;
									port=parentPort;
								}
								int retry=CONNECT_RETRIES;
								while (retry--!=0) {
									try {
										outboundsocket=new Socket(host,port);
										break;
									} catch (Exception e) {	}
									Thread.sleep(CONNECT_PAUSE);
								}
								if(outboundsocket==null)break;
								outboundsocket.setSoTimeout(TIME_OUT);
								os=outboundsocket.getOutputStream();
								os.write(line.getBytes());
								os.write(' ');
								os.write(host0.getBytes());
								os.write(' ');
								//
								pipe(is,outboundsocket.getInputStream(),os,socket.getOutputStream());
								break;
							}//if(isspace)
//						default:
//							break;
						//
						host=host+(char)c;
						break;
					}//switch(state)
				}//while(true)
				
			} catch (IOException e) {}
		} catch (Exception e) {
		}finally{
			try {	socket.close();		} catch (Exception e2) {}
			try {outboundsocket.close();} catch (Exception e2) {}
		}
    	
    }
    
    /**
     * 
     * @param cis
     * @param sis
     * @param cos
     * @param sos
     */
    public void pipe(InputStream cis, InputStream sis, OutputStream cos,
			OutputStream sos) {
		try {
			int length;
			byte[] bytes = new byte[BUFFSIZ];
			while (true) {
				try {
					if ((length = cis.read(bytes)) > 0) {
						sos.write(bytes, 0, length);
						if (logging)
							writeLog(bytes, 0, length, true);
					} else if (length < 0) {
						break;
					}
				} catch (Exception e) {
					System.out.println("Request Exception");
				}
				try {
					if ((length = sis.read(bytes)) > 0) {
						cos.write(bytes, 0, length);
						if (logging)
							writeLog(bytes, 0, length, false);
					} else if (length < 0) {
						break;
					}
				} catch (Exception e) {
					System.out.println("Response Exception");
				}
			}
		} catch (Exception e) {
			System.out.println("Pipe异常" + e);
		}
	}
    
    
    @SuppressWarnings("unchecked")
	public static void startProxy(int port,Class clobj){
    	ServerSocket ssock;
    	//Socket socket;
    	try {
			ssock=new ServerSocket(port);
			while(true){
				Class[] sarg=new Class[1];
				Object[] arg=new Class[1];
				sarg[0]=Socket.class;
				try {
					java.lang.reflect.Constructor cons=clobj.getDeclaredConstructor(sarg);
					arg[0]=ssock.accept();
					cons.newInstance(arg);		//创建HttpProxy或其派生类的实例
				} catch (Exception e) {
					Socket esock=(Socket)arg[0];
					try {
						esock.close();
					} catch (Exception e2) {
					}
				}	
				
			}
		} catch (Exception e) {
		}
    }
	/**
	 * 测试用的简单main方法
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("在端口808启动代理服务器");
		HttpProxy.log_b=System.out;
		HttpProxy.log_s=System.out;
		HttpProxy.logging=false;
		HttpProxy.startProxy(808, HttpProxy.class);
	}

}
