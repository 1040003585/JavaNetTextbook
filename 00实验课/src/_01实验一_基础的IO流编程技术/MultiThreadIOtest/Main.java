package _01实验一_基础的IO流编程技术.MultiThreadIOtest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * 多线程拷贝文件
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-14/下午09:23:45
 * Description:
 */
@SuppressWarnings("serial")
public class Main extends javax.swing.JFrame {

	private JButton jButtonSelectFile;
    private JButton jButtonSelectPath;
    private JButton jButtonCopy;
    private JCheckBox jCheckBox1;
    private JLabel jLabelTitle;
    private JLabel jLabelSourceFileName;
    private JLabel jLabelTargePathName;
    private JTextField jTextFieldSource;
    private JTextField jTextFieldTarge;

    private String sourceName;
    private String targetName;
    private String fileName;
    private boolean flag;

    JFileChooser chooser;
    
    /**
     * Creates new form CopyFile
     */
    public Main() {

    	jLabelTitle = new JLabel();
        jTextFieldSource = new JTextField();
        jLabelSourceFileName = new JLabel();
        jLabelTargePathName = new JLabel();
        jTextFieldTarge = new JTextField();
        jButtonSelectFile = new JButton();
        jButtonSelectPath = new JButton();
        jButtonCopy = new JButton();
        jCheckBox1 = new JCheckBox();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabelTitle.setText("文件拷贝");

        jTextFieldSource.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabelSourceFileName.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabelSourceFileName.setText("源文件名字：");

        jLabelTargePathName.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabelTargePathName.setText("目标路径名字：");

        jButtonSelectFile.setText("选择文件");
        jButtonSelectFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonSelectPath.setText("选择目录");
        jButtonSelectPath.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonSelectFileActionPerformed(evt);
            }
        });

        jButtonCopy.setText("拷贝");
        jButtonCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("是否开启多线程");
        jCheckBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jLabelTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonCopy,GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabelSourceFileName)
                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldSource, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelTargePathName)
                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldTarge)))
                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonSelectFile)
                                    .addComponent(jButtonSelectPath))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(jTextFieldSource, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSourceFileName)
                    .addComponent(jButtonSelectFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(jLabelTargePathName)
                    .addComponent(jTextFieldTarge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSelectPath))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(jButtonCopy,GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addGap(125, 125, 125))
        );

        pack();
    }//Main()



    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showDialog(new JLabel(), "确定");
        File file = chooser.getSelectedFile();
        if (file != null) {
            sourceName = file.getAbsolutePath();
            jTextFieldSource.setText(sourceName);
            fileName = file.getName();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (flag) {
            Integer blockCount = new Integer(3);
            for (int i = 0; i < blockCount; i++) {
                //实例化文件复制对象
                SingleThreadCopy copyFile = new SingleThreadCopy(sourceName, targetName, blockCount, i);
                //实例化线程
                Thread thread = new Thread(copyFile);
                //开始线程
                thread.start();
                try {
                    //加入线程
                    thread.join();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        } else {
            new MultiThreadCopy(sourceName, targetName).copy();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            //开启多线程下载
            flag = true;
        } else {
            //单线程下载
            flag = false;
        }
    }

    private void jButtonSelectFileActionPerformed(ActionEvent evt) {
        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showDialog(new JLabel(), "确定");
        File file = chooser.getSelectedFile();
        targetName = file.getAbsolutePath();
        targetName = targetName + "\\" + fileName;
        jTextFieldTarge.setText(targetName);
    }

    private void jTextField3ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    
    public static void main(String args[]) {

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }


    
}
