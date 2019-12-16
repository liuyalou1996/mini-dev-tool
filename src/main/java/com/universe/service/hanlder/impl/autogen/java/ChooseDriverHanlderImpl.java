package com.universe.service.hanlder.impl.autogen.java;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.universe.common.util.DialogUtils;
import com.universe.pojo.dto.GenByJavaDto;
import com.universe.service.hanlder.GenByJavaButtonSelectionHandler;

public class ChooseDriverHanlderImpl implements GenByJavaButtonSelectionHandler {

  @Override
  public void onButtonSelected(GenByJavaDto dto) {
    String[] filterExtensions = new String[] { "*.jar" };
    String[] filterNames = new String[] { "Jar Files(*.jar)" };

    String driverPath = DialogUtils.showOpenFileDialog(Display.getCurrent().getActiveShell(), null, filterExtensions, filterNames);
    if (StringUtils.isBlank(driverPath)) {
      return;
    }

    Text txDriverPath = dto.getTxDriverPath();
    txDriverPath.setText(driverPath);
  }

}
