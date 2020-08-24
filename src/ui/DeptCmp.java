package ui;

import org.eclipse.swt.widgets.Composite;
import swing2swt.layout.BorderLayout;
import util.SwtHelper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import bean.Dept;
import dao.DeptDao;

import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class DeptCmp extends Composite {
	private Text text;
	private Table table;

	private DeptDao dDao = new DeptDao();

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public DeptCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		RowLayout rl_composite = new RowLayout(SWT.HORIZONTAL);
		rl_composite.center = true;
		composite.setLayout(rl_composite);

		Label label = new Label(composite, SWT.NONE);
		label.setText("部门名:");

		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new RowData(91, SWT.DEFAULT));

		Button button = new Button(composite, SWT.NONE);
		button.setLayoutData(new RowData(68, SWT.DEFAULT));
		button.setText("查询");

		Button buttonAdd = new Button(composite, SWT.NONE);
		buttonAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean res = (boolean) new DeptWin(getShell(), SWT.NONE).open();
				if (res) {
					query();
				}
			}
		});
		buttonAdd.setLayoutData(new RowData(68, SWT.DEFAULT));
		buttonAdd.setText("新增");

		Button buttonMod = new Button(composite, SWT.NONE);
		buttonMod.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(table.getSelectionCount() == 0) {
					SwtHelper.message("请选择要修改的记录", getShell());
					return;
				}else {
					TableItem ti = table.getSelection()[0];
					String deptno = ti.getText(0);
					String dname = ti.getText(1);
					String loc= ti.getText(2);
					int iDeptno = Integer.parseInt(deptno);
					//创建实体对象，传入表格当前选择行的数据
					Dept dept = new Dept(iDeptno, dname, loc);
					boolean res = (boolean) new DeptWin(getShell(), SWT.NONE,dept).open();
					if (res) {
						query();
					}
				}
			}
		});
		buttonMod.setLayoutData(new RowData(68, SWT.DEFAULT));
		buttonMod.setText("修改");

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(BorderLayout.CENTER);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("部门编号");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("部门名称");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("部门地址");

		query();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void query() {
		try {
			List<Map<String, Object>> list = dDao.selectAll();
			// 删除表格中原有的数据
			table.removeAll();
			for (Map<String, Object> dept : list) {
				TableItem tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(
						new String[] { "" + dept.get("DEPTNO"), "" + dept.get("DNAME"), "" + dept.get("LOC") });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
