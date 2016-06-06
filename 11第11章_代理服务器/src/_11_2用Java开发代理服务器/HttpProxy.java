package _11_2��Java�������������;

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
 * Date/Time: 2016-6-6/����10:29:01
 * Description: һ�������Ĵ����������
 * һ������������Ļ�����ƣ� 
 * ��1���ȴ����Կͻ���Web������������� 
 * ��2������һ���µ��̣߳��Ѵ���ͻ���������
 * ��3����ȡ���������ĵ�һ�У��������ݰ����������Ŀ��URL�� 
 * ��4����������ĵ�һ�����ݣ��õ�Ŀ������������ֺͶ˿�
 * ��5����һ��ͨ��Ŀ�������������һ���������������Socket 
 * ��6��������ĵ�һ�з��͵����Socket 
 * ��7���������ʣ�ಿ�ַ��͵����Socket
 * ��8����Ŀ��Web���������ص����ݷ��͸���������������
 */
public class HttpProxy extends Thread{

	public static final int CONNECT_RETRIES = 5;	//�ڷ���֮ǰ��������Զ�������Ĵ���
	public static final int CONNECT_PAUSE = 5;		//���������ӳ���֮�����ͣʱ��
	public static final int TIME_OUT = 50;			//�ȴ�Socket����ĵȴ�ʱ��
	public static final int BUFFSIZ = 1024;			//Socket����Ļ����С
	public static boolean logging = false;			//�Ƿ�Ҫ��������������־�м�¼�����Ѵ��������
	//һ��OutputStream����Ĭ����־���̽����OutputStream���������־��Ϣ	
	public static OutputStream log_s = null;		// Web���������
	public static OutputStream log_b = null;		// ����������
	
	//���������õ�Socket
	protected Socket socket;
	
	//�ϼ��������������ѡ��
	private static String parent=null;
	private static int parentPort=-1;
	public static void setParentProxy(String name,int port){
		parent=name;
		parentPort=port;
	}
	
	/**
	 * ���췽�����ڸ���Socket�ϴ���һ�������߳�
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
     * Ĭ������£���־��Ϣ�������׼����豸����������Ը�������
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
     * ִ�в������߳�
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
				//��ȡ�����е�����
				line="";
				host="";
				int state=0;
				boolean isspace = false;
				while(true){
					int c=is.read();				//��ȡһ���ַ�
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
								//ֻ�����������Ʋ���
								String host0=host;
								int n=0;
								n=host.indexOf("//");
								if(n!=-1)host.substring(n+2);
								n=host.indexOf('/');
								if(n!=-1)host=host.substring(0,n);
								//�������ܴ��ڵĶ˿ں�
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
			System.out.println("Pipe�쳣" + e);
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
					cons.newInstance(arg);		//����HttpProxy�����������ʵ��
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
	 * �����õļ�main����
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("�ڶ˿�808�������������");
		HttpProxy.log_b=System.out;
		HttpProxy.log_s=System.out;
		HttpProxy.logging=false;
		HttpProxy.startProxy(808, HttpProxy.class);
	}

}
