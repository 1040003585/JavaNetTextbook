
import java.io.*;
import java.net.*;
import java.util.*;


public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Swait for connect......");
		try {
			ServerSocket serverSocket = new ServerSocket(9955);
			Socket socket=serverSocket.accept();
			System.out.println("S connet from:"+socket.getInetAddress().getHostAddress());
			
			//DataInputStream dis=new DataInputStream(socket.getInputStream());//dis.readUTF();// un success!!!!!!!!!1
			//DataOutputStream dos=new DataOutputStream(socket.getOutputStream());//dos.writeUTF(string);dos.flush();

			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));//br.readLine();// success............
			PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);//pw.println(string);
			
			boolean goon=true;
			while(goon){		
				//String string=dis.readUTF();
				String string=br.readLine();
				if(string==null)continue;
				System.out.print("S:receive data:("+string+")");	
				if(string.equals("end")==false){
					string=dealWith(string);
					//dos.writeUTF(string);	
					//dos.flush();	
					pw.println(string);
					System.out.println(" ,S:计算结果("+string+") sent...");
				}else{
					goon=false;
					//dos.writeUTF("end");
					//dos.flush();
					pw.println("end");
					System.out.println("\n"); 					
				}

			}

			serverSocket.close();
			//dis.close();
			//dos.close();
			br.close();
			pw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public static String dealWith(String string){
		double radius=0.0;
		try {
			radius=Double.parseDouble(string);			
		} catch (NumberFormatException e) {
			return "NumberFormatException";
		}
		if(radius<0)return "data can't less 0";
		double area=radius*radius*Math.PI;
		return Double.toString(area);
	}

}
