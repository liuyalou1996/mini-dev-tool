package com.universe.service.hanlder.impl.json;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.custom.StyledText;

import com.universe.service.hanlder.JsonFormatToolItemSelectionHandler;

/**
 * 压缩json字符串处理器
 * @author: liuyalou
 * @date: 2019年12月10日
 */
public class CompressJsonStrHandlerImpl implements JsonFormatToolItemSelectionHandler {

  @Override
  public void onToolItemSelected(StyledText text) {
    String jsonStr = text.getText();
    if (StringUtils.isBlank(jsonStr)) {
      return;
    }

    text.setText(jsonStr.replaceAll("\\s+", ""));
  }

}
