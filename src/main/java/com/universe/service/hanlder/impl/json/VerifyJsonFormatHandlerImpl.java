package com.universe.service.hanlder.impl.json;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.custom.StyledText;

import com.universe.common.util.DialogUtils;
import com.universe.common.util.JsonUtils;
import com.universe.service.hanlder.JsonFormatToolItemSelectionHandler;

/**
 * json格式校验处理器
 * @author: liuyalou
 * @date: 2019年12月10日
 */
public class VerifyJsonFormatHandlerImpl implements JsonFormatToolItemSelectionHandler {

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
