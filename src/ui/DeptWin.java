package ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;

import bean.Dept;
import biz.BizException;
import biz.DeptBiz;
import util.SwtHelper;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class DeptWin extends Dialog {

	protected Object result = false;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;

	private DeptBiz dBiz = new DeptBiz();

	/**
	 * 定义一个对象接收被修改的部门 ==> 实体对象(C语言结构体)
	 */
	private Dept dept;

	/**
	 * Create the dialog. 新增的部门方法
	 * 
	 * @param parent
	 * @param style
	 */
	public DeptWin(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * 修改的构造方法
	 * 
	 * @param parent
	 * @param style
	 * @param dept
	 */
	public DeptWin(Shell parent, int style, Dept dept) {
		super(parent, style);
		this.dept = dept;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
		shell.setSize(413, 338);
		shell.setText(getText());
		shell.setLayout(new FormLayout());
		SwtHelper.center(shell);

		Label label = new Label(shell, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0, 57);
		label.setLayoutData(fd_label);
		label.setText("部门编号:");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("部门名称:");
		FormData fd_label_1 = new FormData();
		fd_label_1.top = new FormAttachment(label, 42);
		fd_label_1.left = new FormAttachment(label, 0, SWT.LEFT);
		label_1.setLayoutData(fd_label_1);

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("部门地址:");
		FormData fd_label_2 = new FormData();
		fd_label_2.top = new FormAttachment(label_1, 42);
		fd_label_2.right = new FormAttachment(label, 0, SWT.RIGHT);
		label_2.setLayoutData(fd_label_2);

		text = new Text(shell, SWT.BORDER);
		fd_label.right = new FormAttachment(100, -295);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(label, -3, SWT.TOP);
		fd_text.left = new FormAttachment(label, 22);
		fd_text.right = new FormAttachment(100, -74);
		text.setLayoutData(fd_text);

		text_1 = new Text(shell, SWT.BORDER);
		FormData fd_text_1 = new FormData();
		fd_text_1.top = new FormAttachment(label_1, -3, SWT.TOP);
		fd_text_1.left = new FormAttachment(label_1, 22);
		fd_text_1.right = new FormAttachment(text, 0, SWT.RIGHT);
		text_1.setLayoutData(fd_text_1);

		text_2 = new Text(shell, SWT.BORDER);
		FormData fd_text_2 = new FormData();
		fd_text_2.top = new FormAttachment(label_2, -3, SWT.TOP);
		fd_text_2.left = new FormAttachment(label_2, 22);
		fd_text_2.right = new FormAttachment(text, 0, SWT.RIGHT);
		text_2.setLayoutData(fd_text_2);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String deptno = text.getText();
				String dname = text_1.getText();
				String loc = text_2.getText();
				try {
					if (dept == null) {
						dBiz.create(deptno, dname, loc);
						SwtHelper.message("添加部门成功!", shell);
					}else {
						dBiz.modify(deptno, dname, loc);
						SwtHelper.message("修改部门成功!", shell);
					}
					shell.dispose();
					// 设置返回值
					result = true;
				} catch (BizException e1) {
					e1.printStackTrace();
					SwtHelper.message(e1.getMessage(), shell);
				}
			}
		});
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.bottom = new FormAttachment(100, -32);
		fd_btnNewButton.left = new FormAttachment(0, 77);
		fd_btnNewButton.right = new FormAttachment(0, 163);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("保存");

		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.top = new FormAttachment(btnNewButton, 0, SWT.TOP);
		fd_btnNewButton_1.left = new FormAttachment(btnNewButton, 95);
		fd_btnNewButton_1.right = new FormAttachment(100, -63);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.setText("取消");
		
		if(dept!=null) {
			text.setText(""+dept.getDeptno());
			text_1.setText(""+dept.getDname());
			text_2.setText(""+dept.getLoc());
		}

	}
}
