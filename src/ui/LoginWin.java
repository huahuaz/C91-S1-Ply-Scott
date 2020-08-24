package ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import biz.BizException;
import biz.UserBiz;
import help.SwtLabelPaintListner;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class LoginWin {

	protected Shell shell;
	private Text textName;
	private Text textPwd;
	private Button btnNewButton;
	private Button btnNewButton_1;

	private UserBiz uBiz = new UserBiz();
	private Label label_2;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginWin window = new LoginWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
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

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(LoginWin.class, "/ui/imges/57e9fc6804e31e7e5a537217b73306f1.jpg"));
		shell.setSize(414, 313);
		shell.setText("SWT Application");
		shell.setLocation(Display.getCurrent().getClientArea().width / 2 - shell.getShell().getSize().x / 2,
				Display.getCurrent().getClientArea().height / 2 - shell.getSize().y / 2);
		

		Label label = new Label(shell, SWT.NONE);
		label.setBounds(56, 37, 49, 20);
		label.setText("用户名:"); 

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("密  码:");
		label_1.setBounds(56, 122, 49, 20);

		textName = new Text(shell, SWT.BORDER);
		textName.setBounds(111, 34, 200, 26);

		textPwd = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		textPwd.setBounds(111, 119, 200, 26);

		btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name = textName.getText();
				String pwd = textPwd.getText();
				try {
					uBiz.login(name, pwd);
					// 关闭当前窗口
					LoginWin.this.shell.close();
					// 打开主窗口
					new MainWin().open();
				} catch (BizException e1) {
					e1.getMessage();
					// 消息提示框
					MessageBox mb = new MessageBox(shell);
					mb.setText("系统提示");
					mb.setMessage(e1.getMessage());
					mb.open();
				}
			}
		});
		btnNewButton.setBounds(56, 198, 98, 30);
		btnNewButton.setText("登录");

		btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setBounds(233, 198, 98, 30);
		btnNewButton_1.setText("取消");
		
		label_2 = new Label(shell, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage(LoginWin.class, "/ui/imges/download_20200727090927.jpg"));
		label_2.setBounds(0, 0, 396, 266);
		label_2.addPaintListener(new SwtLabelPaintListner());
	}
}
