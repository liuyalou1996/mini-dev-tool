package com.universe.listener;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;

public class TreeSelectionListener extends SelectionAdapter {

  private Composite comp;

  public TreeSelectionListener(Composite comp) {
    this.comp = comp;
  }

  @Override
  public void widgetSelected(SelectionEvent e) {
    StackLayout layout = (StackLayout) comp.getLayout();
    TreeItem treeItem = (TreeItem) e.item;
  }

}
