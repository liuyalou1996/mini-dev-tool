package com.universe.service.hanlder.impl.json;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.custom.StyledText;

import com.universe.service.hanlder.JsonFormatToolItemSelectionHandler;

/**
 * 转义json字符串处理器
 * @author: liuyalou
 * @date: 2019年12月10日
 */
public class TransferMeaningHanlderImpl implements JsonFormatToolItemSelectionHandler {

  @Override
  public void onToolItemSelected(StyledText styledText) {
    String jsonStr = styledText.getText();
    if (StringUtils.isBlank(jsonStr)) {
      return;
    }

    styledText.setText(jsonStr.replace("\"", "\\\""));

  }

}
