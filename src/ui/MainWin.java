package ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import help.SwtLabelPaintListner;

public class MainWin {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWin window = new MainWin();
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
		shell.setImage(SWTResourceManager.getImage(MainWin.class, "/ui/imges/57e9fc6804e31e7e5a537217b73306f1.jpg"));
		shell.setSize(663, 559);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setLocation(Display.getCurrent().getClientArea().width / 2 - shell.getShell().getSize().x / 2,
				Display.getCurrent().getClientArea().height / 2 - shell.getSize().y / 2);
		/**
		 * 将tab容器定义剪切到这里
		 */
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.CASCADE);
		menuItem.setText("系统");

		Menu menu_1 = new Menu(menuItem);
		menuItem.setMenu(menu_1);

		MenuItem menuItem_1 = new MenuItem(menu_1, SWT.NONE);
		menuItem_1.setText("数据备份");

		MenuItem menuItem_2 = new MenuItem(menu_1, SWT.NONE);
		menuItem_2.setText("数据恢复");

		new MenuItem(menu_1, SWT.SEPARATOR);

		MenuItem menuItem_6 = new MenuItem(menu_1, SWT.NONE);
		menuItem_6.setText("退出");

		MenuItem menuItem_3 = new MenuItem(menu, SWT.CASCADE);
		menuItem_3.setText("基础数据");

		Menu menu_2 = new Menu(menuItem_3);
		menuItem_3.setMenu(menu_2);

		MenuItem menuItem_7 = new MenuItem(menu_2, SWT.NONE);
		menuItem_7.setText("员工信息");

		// 部门管理的菜单事件
		MenuItem menuItem_8 = new MenuItem(menu_2, SWT.NONE);
		menuItem_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 判断是否已经打开
				for (TabItem ti : tabFolder.getItems()) {
					if ("部门管理".equals(ti.getText())) {
						tabFolder.setSelection(ti);
						return;
					}
				}
				// 如果已开，那么选中该页
				// 如果未开，则新建
				TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
				tabItem.setText("部门管理");

				DeptCmp dc = new DeptCmp(tabFolder, SWT.NONE);
				tabItem.setControl(dc);
				tabFolder.setSelection(tabItem);
			}
		});
		menuItem_8.setText("部门管理");

		MenuItem menuItem_4 = new MenuItem(menu, SWT.CASCADE);
		menuItem_4.setText("管理功能");

		Menu menu_3 = new Menu(menuItem_4);
		menuItem_4.setMenu(menu_3);

		MenuItem menuItem_5 = new MenuItem(menu_3, SWT.NONE);
		menuItem_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * 打开岗位调整窗口
				 */
				new JobChangeWin(shell,SWT.NONE).open();
			}
		});
		menuItem_5.setText("岗位调整");

		MenuItem menuItem_9 = new MenuItem(menu_3, SWT.NONE);
		menuItem_9.setText("员工加薪");

		MenuItem menuItem_10 = new MenuItem(menu_3, SWT.NONE);
		menuItem_10.setText("部门调整");

		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("首页");

		Label label_1 = new Label(tabFolder, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(MainWin.class, "/ui/imges/img-7327d8721b2634a27e3fad5355ad5dba.jpg"));
		label_1.addPaintListener(new SwtLabelPaintListner());
		tabItem_1.setControl(label_1);

	}
}
