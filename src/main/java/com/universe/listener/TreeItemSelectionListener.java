package com.universe.listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;

public class TreeItemSelectionListener extends SelectionAdapter {

  private CTabFolder tabFolder;

  public TreeItemSelectionListener(CTabFolder tabFolder) {
    this.tabFolder = tabFolder;
  }

  @Override
  public void widgetSelected(SelectionEvent e) {
    TreeItem treeItem = (TreeItem) e.item;
    Composite comp = (Composite) treeItem.getData();

    // 设置tab选项卡名称以及内容
    CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
    tabItem.setText(treeItem.getText());
    tabItem.setControl(comp);

  }

}
