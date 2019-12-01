package com.universe.service.listener.modify;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ExtendedModifyEvent;
import org.eclipse.swt.custom.ExtendedModifyListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;

public class StyledTextModifyListener implements ExtendedModifyListener {

  private static final String EXCLUDED_REGEX = "[\\d\\{\\}\\[\\]:;/\\\\]";

  private static final List<StyleRange> NUMBER_RANGE_LIST = new ArrayList<>();
  private static final List<StyleRange> QUOTATION_RANGE_LIST = new ArrayList<>();

  private StyledText styledText;

  public StyledTextModifyListener(StyledText styledText) {
    this.styledText = styledText;
  }

  @Override
  public void modifyText(ExtendedModifyEvent e) {
    // 每次输入进行清空
    NUMBER_RANGE_LIST.clear();
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
      if (StringUtils.isNumeric(str)) {
        styledText.setStyleRange(new StyleRange(start + count, 1, e.display.getSystemColor(SWT.COLOR_DARK_GREEN), null));
      }

      if (StringUtils.isNotBlank(str) && !Pattern.matches(EXCLUDED_REGEX, str)) {
        styledText.setStyleRange(new StyleRange(start + count, 1, e.display.getSystemColor(SWT.COLOR_DARK_RED), null));
      }

    }
  }

}
