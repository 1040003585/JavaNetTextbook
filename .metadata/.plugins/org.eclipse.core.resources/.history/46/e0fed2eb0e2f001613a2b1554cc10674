import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


/**
 * 3.3URL类的应用————浏览器的设计
 * @author skyward
 *
 */
public class _3_2MyBrowser2 implements ActionListener,HyperlinkListener{
//本类需要实现HyperlinkListener接口，以响应用户点击超链接事件
	
	JLabel msgLbl;
	JTextField urlText;			//给用户输入URL
	JEditorPane content;		//显示网页内容
	JScrollPane JSPanel;
	JPanel panel;
	Container con;
	JFrame mainJFrame;
	//构造方法，用于程序界面的布局
	public _3_2MyBrowser2() {
		mainJFrame = new JFrame("我的浏览器");
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		con = mainJFrame.getContentPane();
		msgLbl = new JLabel("输入地址： ");
		urlText = new JTextField();
		urlText.setColumns(20);
		urlText.addActionListener(this);
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(msgLbl);
		panel.add(urlText);
		content = new JEditorPane();
		content.setEditable(false);
		content.addHyperlinkListener(this);			//为content添加超链接监听器
		JSPanel = new JScrollPane(content);
		con.add(panel, BorderLayout.NORTH);
		con.add(JSPanel, BorderLayout.CENTER);
		mainJFrame.setSize(1600, 900);
		mainJFrame.setVisible(true);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new _3_2MyBrowser2();
	}

	//实现hyperlinkUpdate方法，当用户点击网页上的链接时，系统将调用此方法
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			try {				
				URL url = e.getURL();
				content.setPage(url);
				urlText.setText(e.getURL().toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	//当用户按下回车键后，调用此方法
	public void actionPerformed(ActionEvent e) {
		try {
			URL url = new URL(urlText.getText());
			content.setPage(url);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}