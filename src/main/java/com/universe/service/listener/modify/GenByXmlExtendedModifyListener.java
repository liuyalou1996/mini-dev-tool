package com.universe.service.listener.modify;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ExtendedModifyEvent;
import org.eclipse.swt.custom.ExtendedModifyListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;

public class GenByXmlExtendedModifyListener implements ExtendedModifyListener {

  private static final List<StyleRange> QUOTATION_RANGE_LIST = new ArrayList<>();

  @Override
  public void modifyText(ExtendedModifyEvent e) {
    StyledText styledText = (StyledText) e.widget;

    // 每次输入进行清空
    QUOTATION_RANGE_LIST.clear();

    int start = e.start;
    int end = start + e.length - 1;
    if (start > end) {
      return;
    }

    String text = styledText.getText(start, end);
    if (StringUtils.isBlank(text)) {
      return;
    }

    for (int count = 0; count < text.length(); count++) {
      String str = String.valueOf(text.charAt(count));
      if ("\"".equals(str)) {
        styledText.setStyleRange(new StyleRange(start + count, 1, e.display.getSystemColor(SWT.COLOR_DARK_RED), null));
      }
    }
  }
}
