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

import bean.JobChange;
import biz.BizException;
import biz.JobChangeBiz;
import util.SwtHelper;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class JobChangeWin extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text textEmpno;
	private Text textOldJob;
	private Text textNewJob;
	private Text textOldSal;
	private Text textNewSal;
	private Text textCause;
	private Text textAllow;

	private JobChangeBiz jBiz = new JobChangeBiz();

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public JobChangeWin(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
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
		SwtHelper.center(shell);
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
		shell.setSize(459, 460);
		shell.setText("岗位调整");
		shell.setLayout(new FormLayout());

		Label lblNewLabel = new Label(shell, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.left = new FormAttachment(0, 76);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("员工编号:");

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		fd_lblNewLabel.bottom = new FormAttachment(100, -366);
		lblNewLabel_1.setText("原岗位:");
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.top = new FormAttachment(lblNewLabel, 20);
		fd_lblNewLabel_1.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);

		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setText("新岗位:");
		FormData fd_lblNewLabel_2 = new FormData();
		fd_lblNewLabel_2.top = new FormAttachment(lblNewLabel_1, 23);
		fd_lblNewLabel_2.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		lblNewLabel_2.setLayoutData(fd_lblNewLabel_2);

		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setText("原工资:");
		FormData fd_lblNewLabel_3 = new FormData();
		fd_lblNewLabel_3.top = new FormAttachment(lblNewLabel_2, 25);
		fd_lblNewLabel_3.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		lblNewLabel_3.setLayoutData(fd_lblNewLabel_3);

		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setText("新工资:");
		FormData fd_lblNewLabel_4 = new FormData();
		fd_lblNewLabel_4.top = new FormAttachment(lblNewLabel_3, 27);
		fd_lblNewLabel_4.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		lblNewLabel_4.setLayoutData(fd_lblNewLabel_4);

		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setText("调整原因:");
		FormData fd_lblNewLabel_5 = new FormData();
		fd_lblNewLabel_5.top = new FormAttachment(lblNewLabel_4, 23);
		fd_lblNewLabel_5.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		lblNewLabel_5.setLayoutData(fd_lblNewLabel_5);

		Label lblNewLabel_6 = new Label(shell, SWT.NONE);
		lblNewLabel_6.setText("审核人:");
		FormData fd_lblNewLabel_6 = new FormData();
		fd_lblNewLabel_6.top = new FormAttachment(lblNewLabel_5, 21);
		fd_lblNewLabel_6.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		lblNewLabel_6.setLayoutData(fd_lblNewLabel_6);

		textEmpno = new Text(shell, SWT.BORDER);
		FormData fd_textEmpno = new FormData();
		fd_textEmpno.right = new FormAttachment(100, -91);
		fd_textEmpno.left = new FormAttachment(lblNewLabel, 6);
		fd_textEmpno.top = new FormAttachment(lblNewLabel, -3, SWT.TOP);
		textEmpno.setLayoutData(fd_textEmpno);

		textOldJob = new Text(shell, SWT.BORDER);
		FormData fd_textOldJob = new FormData();
		fd_textOldJob.top = new FormAttachment(lblNewLabel_1, -3, SWT.TOP);
		fd_textOldJob.left = new FormAttachment(textEmpno, 0, SWT.LEFT);
		fd_textOldJob.right = new FormAttachment(100, -91);
		textOldJob.setLayoutData(fd_textOldJob);

		textNewJob = new Text(shell, SWT.BORDER);
		FormData fd_textNewJob = new FormData();
		fd_textNewJob.top = new FormAttachment(lblNewLabel_2, -3, SWT.TOP);
		fd_textNewJob.left = new FormAttachment(textEmpno, 0, SWT.LEFT);
		fd_textNewJob.right = new FormAttachment(100, -91);
		textNewJob.setLayoutData(fd_textNewJob);

		textOldSal = new Text(shell, SWT.BORDER);
		FormData fd_textOldSal = new FormData();
		fd_textOldSal.top = new FormAttachment(lblNewLabel_3, -3, SWT.TOP);
		fd_textOldSal.left = new FormAttachment(textEmpno, 0, SWT.LEFT);
		fd_textOldSal.right = new FormAttachment(100, -91);
		textOldSal.setLayoutData(fd_textOldSal);

		textNewSal = new Text(shell, SWT.BORDER);
		FormData fd_textNewSal = new FormData();
		fd_textNewSal.top = new FormAttachment(lblNewLabel_4, -3, SWT.TOP);
		fd_textNewSal.left = new FormAttachment(textEmpno, 0, SWT.LEFT);
		fd_textNewSal.right = new FormAttachment(100, -91);
		textNewSal.setLayoutData(fd_textNewSal);

		textCause = new Text(shell, SWT.BORDER);
		FormData fd_textCause = new FormData();
		fd_textCause.top = new FormAttachment(lblNewLabel_5, -3, SWT.TOP);
		fd_textCause.left = new FormAttachment(lblNewLabel_5, 6);
		fd_textCause.right = new FormAttachment(100, -91);
		textCause.setLayoutData(fd_textCause);

		textAllow = new Text(shell, SWT.BORDER);
		FormData fd_textAllow = new FormData();
		fd_textAllow.top = new FormAttachment(lblNewLabel_6, -3, SWT.TOP);
		fd_textAllow.left = new FormAttachment(lblNewLabel_6, 21);
		fd_textAllow.right = new FormAttachment(textEmpno, 0, SWT.RIGHT);
		textAllow.setLayoutData(fd_textAllow);

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JobChange jc = new JobChange();
				// 从界面元素取值设置到jc中
				jc.setEmpno(Integer.parseInt(textEmpno.getText()));
				jc.setOldjob(textOldJob.getText());
				jc.setNewjob(textNewJob.getText());
				jc.setOldsal(Float.parseFloat(textOldSal.getText()));
				jc.setNewsal(Float.parseFloat(textNewSal.getText()));
				jc.setCause(textCause.getText());
				jc.setAllow(Integer.parseInt(textAllow.getText()));
				try {
					jBiz.create(jc);
					SwtHelper.message("岗位调整成功!", shell);
					shell.dispose();
				} catch (BizException e1) {
					SwtHelper.message(e1.getMessage(), shell);
				}
			}
		});
		FormData fd_button = new FormData();
		fd_button.left = new FormAttachment(0, 76);
		fd_button.bottom = new FormAttachment(100, -34);
		button.setLayoutData(fd_button);
		button.setText("提交");

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		fd_button.right = new FormAttachment(button_1, -113);
		button_1.setText("取消");
		FormData fd_button_1 = new FormData();
		fd_button_1.top = new FormAttachment(button, 0, SWT.TOP);
		fd_button_1.right = new FormAttachment(100, -84);
		fd_button_1.left = new FormAttachment(100, -174);
		button_1.setLayoutData(fd_button_1);

	}
}
