package com.universe.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

public class MainWindow {

  protected Shell shell;

  public static void main(String[] args) {
    try {
      MainWindow window = new MainWindow();
      window.open();
    } catch (Exception e) {
      e.printStackTrace();
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
    shell.setSize(962, 632);
    shell.setText("Mini Dev Tool");
    shell.setLayout(new FillLayout(SWT.HORIZONTAL));

    SashForm sashform = new SashForm(shell, SWT.NONE);

    Composite compTree = new Composite(sashform, SWT.BORDER | SWT.H_SCROLL);
    compTree.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
    compTree.setLayout(new FillLayout(SWT.HORIZONTAL));

    Tree treeMenu = new Tree(compTree, SWT.BORDER | SWT.FULL_SELECTION);
    treeMenu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
    treeMenu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

    TreeItem tiJsonTransformation = new TreeItem(treeMenu, SWT.NONE);
    tiJsonTransformation.setText("Json转换");

    TreeItem tiJsonFormat = new TreeItem(tiJsonTransformation, SWT.NONE);
    tiJsonFormat.setText("Json字符串格式化");

    TreeItem tiBeanToJson = new TreeItem(tiJsonTransformation, SWT.NONE);
    tiBeanToJson.setText("Bean转Json");

    TreeItem tiJsonToBean = new TreeItem(tiJsonTransformation, SWT.NONE);
    tiJsonToBean.setText("Json转Bean");
    tiJsonTransformation.setExpanded(true);

    TreeItem tiMybatisGenerator = new TreeItem(treeMenu, SWT.NONE);
    tiMybatisGenerator.setText("Mybatis Generator");

    TreeItem tiGenByXml = new TreeItem(tiMybatisGenerator, SWT.NONE);
    tiGenByXml.setText("根据配置文件生成");

    TreeItem tiGenByJava = new TreeItem(tiMybatisGenerator, SWT.NONE);
    tiGenByJava.setText("自定义配置生成");

    Composite compContent = new Composite(sashform, SWT.NONE);
    compContent.setLayout(new FillLayout(SWT.HORIZONTAL));

    CTabFolder tabFolder = new CTabFolder(compContent, SWT.BORDER | SWT.CLOSE);
    tabFolder.setTouchEnabled(true);
    tabFolder.setTabHeight(25);
    tabFolder.setSimple(false);
    tabFolder.setMinimizeVisible(true);
    tabFolder.setMaximizeVisible(true);
    tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

    CTabItem tbtmNewItem = new CTabItem(tabFolder, SWT.NONE);
    tbtmNewItem.setText("New Item");

    CTabItem tbtmNewItem_1 = new CTabItem(tabFolder, SWT.NONE);
    tbtmNewItem_1.setText("New Item");
    sashform.setWeights(new int[] { 202, 741 });
  }
}
