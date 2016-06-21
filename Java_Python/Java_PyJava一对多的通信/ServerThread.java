
import java.io.*;
import java.net.*;



public class ServerThread extends Thread {

	private Socket connectToClientSocket;
	private BufferedReader inFromClient;
	private PrintWriter outToClient;
	private int clientname=1;	

	public ServerThread(Socket socket) throws IOException {
		super();
		connectToClientSocket=socket;

		inFromClient=new BufferedReader(new InputStreamReader(socket.getInputStream()));// success............
		outToClient=new PrintWriter(socket.getOutputStream(),true);

		System.out.println("S:connect from:"+connectToClientSocket.getInetAddress().getHostAddress());
		start();	
	}

	public ServerThread(Socket socket,int cname) throws IOException {
		super();
		connectToClientSocket=socket;
		clientname=cname;

		inFromClient=new BufferedReader(new InputStreamReader(socket.getInputStream()));// success............
		outToClient=new PrintWriter(socket.getOutputStream(),true);
	
		System.out.println("S:connect from:"+clientname+":"+connectToClientSocket.getInetAddress().getHostAddress());
		start();		
	}


	@Override
	public void run() {
		super.run();
		System.out.println("S: connect Clinet"+clientname+"......");
		try {
			boolean goon=true;
			while(goon){		
				String string=inFromClient.readLine();
				System.out.print("SClinet"+clientname+": receive data:("+string+")");	
				if(string.equals("end")==false){
					string=dealWith(string,clientname);
					outToClient.println(string);
					System.out.println(",SClinet"+clientname+": sent("+string+")...");
				}else{
					goon=false;
					outToClient.println("end");
					System.out.println("SClinet"+clientname+": server end...");
				}

			}

			connectToClientSocket.close();
			inFromClient.close();
			outToClient.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String dealWith(String string,int ci){
		double radius=0.0;
		try {
			radius=Double.parseDouble(string);			
		} catch (NumberFormatException e) {
			return "NumberFormatException";
		}
		if(radius<0)return "input data can't less 0";
		double area=radius*radius*Math.PI;
		return Double.toString(area);
	}

}


