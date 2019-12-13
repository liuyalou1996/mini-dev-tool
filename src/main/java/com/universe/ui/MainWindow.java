package com.universe.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.universe.common.constant.CompTypeConsts;
import com.universe.common.constant.SystemConsts;
import com.universe.common.util.UiUtils;
import com.universe.service.listener.selection.TreeItemSelectionListener;

public class MainWindow {

  private static Logger logger = LoggerFactory.getLogger(MainWindow.class);

  private Tree treeMenu;
  private CTabFolder tabFolder;
  private Shell shell;

  public static void main(String[] args) {
    try {
      MainWindow window = new MainWindow();
      window.open();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
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
    shell.setSize(1126, 705);
    shell.setText("Mini Dev Tool");
    shell.setLayout(new FillLayout(SWT.HORIZONTAL));

    UiUtils.centerShell(shell.getDisplay(), shell);

    SashForm sashform = new SashForm(shell, SWT.NONE);
    // 左边树面板
    initTree(sashform);
    // 初始化选项卡
    initTabFolder(sashform);
    sashform.setWeights(new int[] { 202, 741 });

  }

  /**
   * 初始化右边的选项卡
   * @param sashform
   */
  private void initTabFolder(SashForm sashform) {
    Composite compContent = new Composite(sashform, SWT.NONE);
    compContent.setLayout(new FillLayout(SWT.HORIZONTAL));

    tabFolder = new CTabFolder(compContent, SWT.BORDER | SWT.CLOSE);
    tabFolder.setTouchEnabled(true);
    tabFolder.setTabHeight(25);
    tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

    // 选项卡初始化后为树添加选择监听器
    treeMenu.addSelectionListener(new TreeItemSelectionListener(tabFolder));

  }

  /**
   * 初始化左边的树形菜单
   * @param compTree
   */
  private void initTree(SashForm sashform) {
    Composite compTree = new Composite(sashform, SWT.BORDER | SWT.H_SCROLL);
    compTree.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
    compTree.setLayout(new FillLayout(SWT.HORIZONTAL));

    treeMenu = new Tree(compTree, SWT.BORDER | SWT.FULL_SELECTION);
    treeMenu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
    treeMenu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

    TreeItem tiJsonTransformation = new TreeItem(treeMenu, SWT.NONE);
    tiJsonTransformation.setText("Json转换");

    TreeItem tiJsonFormat = new TreeItem(tiJsonTransformation, SWT.NONE);
    tiJsonFormat.setText("Json字符串格式化");
    tiJsonFormat.setData(SystemConsts.COMP_TYPE, CompTypeConsts.JSON_FORMAT);

    // 初始化子item后树打开
    tiJsonTransformation.setExpanded(true);

    TreeItem tiMybatisGenerator = new TreeItem(treeMenu, SWT.NONE);
    tiMybatisGenerator.setText("Mybatis Generator");

    TreeItem tiGenByXml = new TreeItem(tiMybatisGenerator, SWT.NONE);
    tiGenByXml.setText("根据配置文件生成");
    tiGenByXml.setData(SystemConsts.COMP_TYPE, CompTypeConsts.GENERATE_BY_XML);

    TreeItem tiGenByJava = new TreeItem(tiMybatisGenerator, SWT.NONE);
    tiGenByJava.setText("自定义配置生成");
    tiGenByJava.setData(SystemConsts.COMP_TYPE, CompTypeConsts.GENERATE_BY_JAVA);

    // 初始化子item后树打开
    tiMybatisGenerator.setExpanded(true);
  }
}
