package com.universe.service.hanlder.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.custom.StyledText;

import com.universe.service.hanlder.ToolItemSelectionHandler;
import com.universe.util.DialogUtils;
import com.universe.util.JsonUtils;

public class VerifyJsonFormatHandlerImpl implements ToolItemSelectionHandler {

  @Override
  public void onToolItemSelected(StyledText text) {
    String jsonStr = StringUtils.trim(text.getText());

    if (StringUtils.isBlank(jsonStr)) {
      return;
    }

    try {
      Map<String, Object> jsonMap = JsonUtils.toMap(jsonStr);
      text.setText(JsonUtils.toPrettyJsonString(jsonMap));
    } catch (Exception e) {
      DialogUtils.showErrorDialog(text.getShell(), "格式化错误", e.getMessage());
    }
  }

}
