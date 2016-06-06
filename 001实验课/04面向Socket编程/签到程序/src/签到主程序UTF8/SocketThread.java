package 签到主程序UTF8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 
 * @author K01
 */
public class SocketThread extends Thread {

	private static Set<String> ipSet; // 存储IP地址的集合，防止同一个IP进行多次签到
	String a = "IP地址:端口号:班级:姓名学号";
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private static FileWriter fileWriter;
	private static int count = 1;

	static {
		try {
			fileWriter = new FileWriter("C:\\temp.txt", true);
			ipSet = new HashSet<String>();
		} catch (IOException ex) {
			Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	public SocketThread(Socket socket) {
		try {
			this.socket = socket;
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream())), true);
			in = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			start();//////////////////////////////////////
		} catch (IOException ex) {
			Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	private boolean checkserver(String str) {
		Socket socket1 = null;
		PrintWriter pw = null;
		try {
			System.out.println(str);
			int num = str.indexOf(":");
			String ip = str.substring(0, num);
			// System.out.println(ip);
			int num1 = str.indexOf(":", num + 1);
			Integer port = Integer.parseInt(str.substring(num + 1, num1));
			// System.out.println(port);
			// 创建套接字，通过去连接服务器端，测试服务器端是否可用。若可用，则签到成功。否则签到失败
			socket1 = new Socket(ip, port);
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket1.getOutputStream())), true);
			// 检验该ip地址是否已经进行过签到
			if (ipSet.contains(ip)) {
				pw.println("该ip地址已经进行过签到，无法再次签到reply...no...");
			} else {
				ipSet.add(ip);
				pw.println(str.substring(num1) + "--你的签到已经完成success...");
				System.out.println("--签到成功！");
			}
			pw.println("END");
			
			} catch (StringIndexOutOfBoundsException e) {
			return false;
		} catch (IOException ex) {
			return false;
		} finally {
			if (socket1 != null) {
				try {
					socket1.close();
				} catch (IOException ex) {
					Logger.getLogger(SocketThread.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
			if (pw != null) {
				pw.close();
			}
		}

		return true;
	}

	
	public void run() {
		try {
			String str = in.readLine();
			if (str.length() > 0) {
				System.out.print(count + ":" + str + socket.getInetAddress());
				// check server
				if (!checkserver(str)) {
					System.out.println("签到不成功");
					out.println("签到不成功！数据发送格式应为     " + a);
				} else {
					fileWriter.write(count + "-");
					fileWriter.write(str);
					fileWriter.write("\r\n");
					count++;
				}
				fileWriter.flush();

			}
		} catch (IOException ex) {
			Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {

		}
	}
}
