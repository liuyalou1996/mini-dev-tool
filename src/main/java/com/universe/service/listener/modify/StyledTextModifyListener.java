package com.universe.service.listener.modify;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.universe.common.constant.SystemConsts;

/**
 * 文本改变时添加*号
 * @author: liuyalou
 * @date: 2019年12月12日
 */
public class StyledTextModifyListener implements ModifyListener {

  private Composite composite;

  private Button btnGen;

  private Button btnExport;

  public StyledTextModifyListener(Composite composite, Button btnGen, Button btnExport) {
    this.composite = composite;
    this.btnGen = btnGen;
    this.btnExport = btnExport;
  }

  @Override
  public void modifyText(ModifyEvent e) {
    CTabItem tabItem = (CTabItem) composite.getData(SystemConsts.ATTACHED_TAB_ITEM);
    String tabItemName = tabItem.getText();
    if (!tabItemName.startsWith("*")) {
      tabItem.setText("* " + tabItem.getText());
    }

    StyledText styledText = (StyledText) e.widget;
    if (StringUtils.isNotBlank(styledText.getText())) {
      btnGen.setEnabled(true);
      btnExport.setEnabled(true);
    } else {
      btnGen.setEnabled(false);
      btnExport.setEnabled(false);
    }
  }

}
