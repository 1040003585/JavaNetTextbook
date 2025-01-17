package _5_2DatagramSocket编程示例._5_2_3用UDP实现的聊天程序;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class UDPChat implements ActionListener, Runnable {

	JTextArea showArea;
	JLabel lbl1, lbl2, lbl3;
	JTextField msgTextField, sendPortTextField, receivePorttTextField, IPAddressTextField;
	JFrame mainFrame;
	JButton sendButton, startButton;//,resetbButton;
	JScrollPane JSPane;
	JPanel panel1, panel2;
	Container container;

	Thread thread = null;
	DatagramSocket sendSocket, receiveSocket;
	DatagramPacket sendPacket, receivePacket;

	private InetAddress sendIP;
	private int sendPort, receivePort;// 存储发送端口和接收端口
	private byte inbuf[], outbuf[];

	public static final int BUFSIZE = 1024;

	public UDPChat() {
		mainFrame = new JFrame("聊天——UDP协议");
		container = mainFrame.getContentPane();

		showArea = new JTextArea();
		showArea.setEditable(false);
		showArea.setLineWrap(true);

		lbl1 = new JLabel("接收端口号：");
		lbl2 = new JLabel("发送端口号：");
		lbl3 = new JLabel("对方的地址：");

		sendPortTextField = new JTextField();
		sendPortTextField.setColumns(4);
		receivePorttTextField = new JTextField();
		receivePorttTextField.setColumns(4);
		IPAddressTextField = new JTextField();
		IPAddressTextField.setColumns(8);

		startButton = new JButton("开始");
		startButton.addActionListener(this);/**/

//		resetbButton=new JButton("重置");
//		resetbButton.addActionListener(this);/**/
		
		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.add(lbl1);
		panel1.add(receivePorttTextField);
		panel1.add(lbl2);
		panel1.add(sendPortTextField);
		panel1.add(lbl3);
		panel1.add(IPAddressTextField);
		panel1.add(startButton);
//		panel1.add(resetbButton);

		JSPane = new JScrollPane(showArea);

		msgTextField = new JTextField();
		msgTextField.setColumns(40);
		msgTextField.setEditable(false);
		msgTextField.addActionListener(this);/**/

		sendButton = new JButton("发送");
		sendButton.setEnabled(false);
		sendButton.addActionListener(this);/**/

		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.add(msgTextField);
		panel2.add(sendButton);

		container.add(panel1, BorderLayout.NORTH);
		container.add(JSPane, BorderLayout.CENTER);
		container.add(panel2, BorderLayout.SOUTH);

		mainFrame.setSize(600, 400);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == startButton) {/* 按下了开始按键 */
				inbuf = new byte[BUFSIZE];

				receivePort = Integer.parseInt(receivePorttTextField.getText());
				sendPort = Integer.parseInt(sendPortTextField.getText());
				sendIP = InetAddress.getByName(IPAddressTextField.getText());
		
				//创建发送和接收DatagramSocket和DatagramPacket
				sendSocket = new DatagramSocket();
				receiveSocket = new DatagramSocket(receivePort);
				receivePacket = new DatagramPacket(inbuf, BUFSIZE);

				thread = new Thread(this);
				thread.setPriority(Thread.MIN_PRIORITY);
				thread.start();

				startButton.setEnabled(false);
				sendButton.setEnabled(true);
				msgTextField.setEditable(true);

//			} else if (e.getSource() == resetbButton) {
//				
//				sendPortTextField.setText(null);
//				receivePorttTextField.setText(null);
//				IPAddressTextField.setText(null);
//				msgTextField.setText(null);
//				showArea.setText(null);
//				
//				startButton.setEnabled(true);
//				sendButton.setEnabled(false);
//				msgTextField.setEditable(false);
//				
//				thread.interrupt();
//				if(receivePacket!=null)	{
//					receiveSocket.close();
//				}
				
			} else {/* 按下了发送按键或回车键 */

				outbuf = msgTextField.getText().getBytes();
				// 组装要发送的的数据包
				sendPacket = new DatagramPacket(outbuf, outbuf.length, sendIP, sendPort);
				// 发送数据
				sendSocket.send(sendPacket);
				showArea.append("我说（" + msgTextField.getText() + "）"	+ getDateTime() + "\n");
				msgTextField.setText(null);
			}
		} catch (UnknownHostException e1) {
			showArea.append("getByName无法连接到指定地址！！！" + getDateTime() + "\n");
		} catch (SocketException e1) {
			showArea.append("DatagramSocket无法打开指定端口！！！" + getDateTime() + "\n");
		} catch (IOException e1) {
			showArea.append("send数据数据失败！！！" + getDateTime() + "\n");
		} catch (NumberFormatException e1) {
			showArea.append("设置端口数据格式不对！！！" + getDateTime() + "\n");
		}

	}

	@Override
	public void run() {
		String msgstr;
		while (true) {
			try {// 注意try的位置
				receiveSocket.receive(receivePacket);
				msgstr = new String(receivePacket.getData(), 0, receivePacket.getLength());
				showArea.append("对方说（" + msgstr + "）" + getDateTime() + "\n");
			} catch (Exception e) {
				showArea.append("receive接收数据出错！！！" + getDateTime() + "\n");
			}
		}
	}

	public static void main(String[] args) {
		new UDPChat();
	}

	/**
	 * Java代码中获得当前时间
	 * 
	 * @return 当前时期时间
	 */
	private String getDateTime() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}
}
