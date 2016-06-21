
import java.io.*;
import java.net.*;

public class Client {
	
	public static void main(String[] args) {
		
		try {

			Socket socket = new Socket("localhost",9955);
			//DataInputStream dis = new DataInputStream(socket.getInputStream());
			//DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));//br.readLine();// success............
			PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);//pw.println(string);
			
			System.out.println("C input data (with 'end' for exit the proge)");
			boolean goon=true;
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			while(goon){
				String outString=bf.readLine();	
				//dos.writeUTF(outString);	
				//dos.flush();
				pw.println(outString);
				//String inString=dis.readUTF();
				String inString=br.readLine();
				
				if(!inString.equals("end")){
					System.out.println("C receive result:"+inString);
				}else{
					goon=false;
					System.out.println("C end...");
				}
			}
			socket.close();
			//dis.close();
			//dos.close();
			br.close();
			pw.close();
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
