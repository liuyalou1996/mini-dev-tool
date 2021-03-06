package com.universe.service.hanlder.impl.json;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.custom.StyledText;

import com.universe.service.hanlder.JsonFormatToolItemSelectionHandler;

/**
 * 消除转义处理器
 * @author: liuyalou
 * @date: 2019年12月10日
 */
public class EliminateMeaningHandlerImpl implements JsonFormatToolItemSelectionHandler {

  @Override
  public void onToolItemSelected(StyledText styledText) {
    String jsonStr = styledText.getText();
    if (StringUtils.isBlank(jsonStr)) {
      return;
    }

    styledText.setText(jsonStr.replace("\\\"", "\""));
  }

}
