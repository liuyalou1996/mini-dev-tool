package com.universe.service.hanlder.impl.autogen;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Text;

import com.universe.service.hanlder.ButtonSelectionHandler;
import com.universe.util.DialogUtils;

public class ChooseConfigFileHandlerImpl implements ButtonSelectionHandler {

  @Override
  public void onButtonSelected(Text txConfigFilePath, StyledText txConfigContent) {
    String[] filterExtensios = new String[] { "*.xml" };
    String[] filterNames = new String[] { "Xml Files(*.xml)" };
    String filePath = DialogUtils.showOpenFileDialog(txConfigFilePath.getShell(), null, filterExtensios, filterNames);
    if (StringUtils.isBlank(filePath)) {
      return;
    }

    // 展示配置文件路径
    txConfigFilePath.setText(filePath);
    // 展示配置文件内容
    try {
      String configFileContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
      txConfigContent.setText(configFileContent);
    } catch (IOException e) {
      DialogUtils.showErrorDialog(txConfigFilePath.getShell(), "错误提示", "配置文件读取失败!");
    }
  }

}
