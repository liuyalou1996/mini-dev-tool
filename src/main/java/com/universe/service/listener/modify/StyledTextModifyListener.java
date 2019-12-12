package com.universe.service.listener.modify;

import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;

import com.universe.common.constant.SystemConsts;

/**
 * 文本改变时添加*号
 * @author: liuyalou
 * @date: 2019年12月12日
 */
public class StyledTextModifyListener implements ModifyListener {

  private Composite composite;

  public StyledTextModifyListener(Composite composite) {
    this.composite = composite;
  }

  @Override
  public void modifyText(ModifyEvent e) {
    CTabItem tabItem = (CTabItem) composite.getData(SystemConsts.ATTACHED_TAB_ITEM);
    String tabItemName = tabItem.getText();
    if (!tabItemName.startsWith("*")) {
      tabItem.setText("* " + tabItem.getText());
    }
  }

}
