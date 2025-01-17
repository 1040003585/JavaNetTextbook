package _01实验一_基础的IO流编程技术;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class IOTest3_Shell {
	protected Shell shell;
	private Text text;

	public static void main(String[] args) {
		try {
			IOTest3_Shell window = new IOTest3_Shell();
			window.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hello .");
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shell = new Shell();
		shell.setBounds(300, 300, 300, 300);
		shell.setText("Wu_Being——操作界面");
		// ///////////////////////////////////////////////////////////////////////
		{
			Button getButton = new Button(shell, SWT.NONE);
			getButton.addMouseListener(new MouseAdapter() {
				public void mouseDown(MouseEvent e) {
					FileDialog dialog = new FileDialog(shell, SWT.NONE);
					dialog.setFilterExtensions(new String[] { "*.txt", "*.doc",
							"*.*" }); // 设置过滤的文件扩展名
					dialog // 设置文件路径
							.setFilterPath("E:\\gitfile\\javanettextbook\\javanettextbook\\0实验课\\01实验一\\IOTestFiles\\");
					String fileName = dialog.open();
					System.out.println(fileName);// test
					if (fileName != null) {
						try {
							FileReader fileReader = new FileReader(fileName);
							BufferedReader bufferedReader = new BufferedReader(
									fileReader);
							while (bufferedReader.readLine() != null) {
								String lineString = bufferedReader.readLine();
								text.setText(lineString + '\n');
								System.out.println(lineString);// test
							}
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						} finally {
						}
					} else {
						return;
					}

				}
			});
			getButton.setBounds(10, 100, 130, 130);
			getButton.setText("请选择要打开的文件");
		}

		// //////////////////////////////////////////////////////////////////////
		{
			Button setButton = new Button(shell, SWT.NONE);
			setButton.addMouseListener(new MouseAdapter() {
				public void mouseDown(MouseEvent e) {
					FileDialog dialog = new FileDialog(shell, SWT.SAVE);
					dialog.setFilterExtensions(new String[] { "*.txt", "*.doc",
							"*.*" }); // 设置过滤的文件扩展名
					dialog // 设置文件路径
							.setFilterPath("E:\\gitfile\\javanettextbook\\javanettextbook\\0实验课\\01实验一\\IOTestFiles\\");
					String fileName = dialog.open();
					System.out.println(fileName);// test
					if (fileName != null) {
						BufferedReader bufferedReader = new BufferedReader(
								new StringReader(text.getText()));
						try {
							FileWriter fileWriter = new FileWriter(fileName,
									true);
							BufferedWriter bufferedWriter = new BufferedWriter(
									fileWriter);
							String lineString = "";
							while ((lineString = bufferedReader.readLine()) != null) {
								bufferedWriter.write(lineString);
								bufferedWriter.newLine();
								bufferedWriter.flush();
								text.setText("");
							}

							bufferedReader.close();
							fileWriter.close();
							bufferedWriter.close();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						} finally {
						}
					} else {
						return;
					}

				}
			});
			setButton.setBounds(150, 100, 130, 130);
			setButton.setText("请选择要保存的路径");
		}
		// //////////////////////////////////////////////////////////////////////
		{
			text = new Text(shell, SWT.BORDER);
			text.setBounds(10, 40, 270, 40);
		}
	}

}