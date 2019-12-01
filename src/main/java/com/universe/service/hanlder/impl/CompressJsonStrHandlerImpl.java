package com.universe.service.hanlder.impl;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.custom.StyledText;

import com.universe.service.hanlder.ToolItemSelectionHandler;

public class CompressJsonStrHandlerImpl implements ToolItemSelectionHandler {

  @Override
  public void onToolItemSelected(StyledText text) {
    String jsonStr = text.getText();
    if (StringUtils.isBlank(jsonStr)) {
      return;
    }

    text.setText(jsonStr.replaceAll("\\s+", ""));
  }

}
