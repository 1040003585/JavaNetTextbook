package _01实验一_基础的IO流编程技术;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * gai.java
 *
 * Created on 2009-6-4, 16:13:41
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 * 
 * @author sys
 */
@SuppressWarnings("serial")
public class IOTest4_Windows extends javax.swing.JFrame {

	/** Creates new form gai */
	public IOTest4_Windows() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {

		jButton1 = new javax.swing.JButton();
		jTextField1 = new javax.swing.JTextField();
		jButton2 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jButton1.setText("请选择你要保存的文件");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jTextField1.setText("jTextField1");

		jButton2.setText("请选择文件的保存地址");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addGap(39, 39, 39).addGroup(
						layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING,
								false).addComponent(jButton2,
								javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addComponent(jButton1,
								javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)).addGap(41, 41, 41)
						.addComponent(jTextField1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 176,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(98, Short.MAX_VALUE)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(25, 25, 25)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton1)
														.addComponent(
																jTextField1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(60, 60, 60).addComponent(
												jButton2).addContainerGap(169,
												Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(this);
		String thispath = chooser.getSelectedFile().getAbsolutePath();
		jTextField1.setText(thispath);// TODO add your handling code here:
	}// GEN-LAST:event_jButton1ActionPerformed

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		String s_DFileName = null;
		String s_FileName = jTextField1.getText();
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showSaveDialog(chooser);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			s_DFileName = chooser.getSelectedFile().getAbsolutePath();

		}

		try {
			// 定义输入流
			FileInputStream fis = new FileInputStream(s_FileName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			// 定义输出流
			FileOutputStream fos = new FileOutputStream(s_DFileName);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			DataOutputStream dos = new DataOutputStream(bos);
			int b;
			while ((b = bis.read()) != -1) {
				dos.write(b);
			}
			bis.close();
			dos.close();
		} catch (IOException e) {
			System.err.println(e);
		}

		/*
		 * if(s_FileName==null)return; try{ FileWriter fw=new
		 * FileWriter(s_FileName,true); BufferedWriter bw=new
		 * BufferedWriter(fw); bw.write(filename); bw.newLine(); bw.close(); }
		 * catch(IOException e) {}
		 */// TODO add your handling code here:
		/*
		 * JFileChooser chooser = new JFileChooser();
		 * chooser.showOpenDialog(this); String thispath=
		 * chooser.getSelectedFile().getAbsolutePath();
		 * jTextField1.setText(thispath);
		 */
	}// GEN-LAST:event_jButton2ActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new IOTest4_Windows().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JTextField jTextField1;
	// End of variables declaration//GEN-END:variables

}
