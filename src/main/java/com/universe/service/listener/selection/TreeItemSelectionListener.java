package com.universe.service.listener.selection;

import java.util.Optional;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;

import com.universe.common.constant.CompTypeConsts;
import com.universe.common.constant.SystemConsts;
import com.universe.ui.composite.generation.GenByJavaComposite;
import com.universe.ui.composite.generation.GenByXmlComposite;
import com.universe.ui.composite.json.JsonFormatComposite;

public class TreeItemSelectionListener extends SelectionAdapter {

  private CTabFolder tabFolder;

  public TreeItemSelectionListener(CTabFolder tabFolder) {
    this.tabFolder = tabFolder;
  }

  @Override
  public void widgetSelected(SelectionEvent e) {
    TreeItem treeItem = (TreeItem) e.item;
    String compType = (String) treeItem.getData(SystemConsts.COMP_TYPE);

    // 已存在的面板不重复打开
    for (CTabItem ctabItem : tabFolder.getItems()) {
      if (ctabItem.getData(SystemConsts.COMP_TYPE).equals(compType)) {
        tabFolder.setSelection(ctabItem);
        return;
      }
    }

    Composite composite = determineCompByCompType(compType, tabFolder);
    Optional.ofNullable(composite).ifPresent(comp -> {
      CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
      tabItem.setText(treeItem.getText());
      tabItem.setControl(comp);
      // 设置面板类型用于判断是否已经打开过
      tabItem.setData(SystemConsts.COMP_TYPE, compType);
      tabFolder.setSelection(tabItem);
      // 绑定选项卡
      composite.setData(SystemConsts.ATTACHED_TAB_ITEM, tabItem);
    });
  }

  /**
   * 根据面板类型获取面板
   * @param compType
   * @param parent
   * @return
   */
  private Composite determineCompByCompType(String compType, Composite parent) {
    if (CompTypeConsts.JSON_FORMAT.equals(compType)) {
      return new JsonFormatComposite(parent);
    }

    if (CompTypeConsts.GENERATE_BY_XML.equals(compType)) {
      return new GenByXmlComposite(parent);
    }

    if (CompTypeConsts.GENERATE_BY_JAVA.equals(compType)) {
      return new GenByJavaComposite(parent);
    }

    return null;
  }

}
