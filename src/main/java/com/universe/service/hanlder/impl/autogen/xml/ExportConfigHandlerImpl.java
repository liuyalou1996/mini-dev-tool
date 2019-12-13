package com.universe.service.hanlder.impl.autogen.xml;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.universe.common.util.DialogUtils;
import com.universe.service.hanlder.GenByXmlButtonSelectionHandler;

public class ExportConfigHandlerImpl implements GenByXmlButtonSelectionHandler {

  @Override
  public void onButtonSelected(Text txFilePath, StyledText txFileContent) {
    String filterPath = txFilePath.getText();
    String[] filterExtensions = new String[] { "*.xml" };;
    String[] filterNames = new String[] { "Xml Files(*.xml)" };

    String targetPath = DialogUtils.showSaveFileDialog(txFilePath.getShell(), filterPath, filterExtensions, filterNames);
    if (StringUtils.isBlank(targetPath)) {
      return;
    }

    String fileContent = txFileContent.getText();
    if (StringUtils.isBlank(fileContent)) {
      return;
    }

    File target = new File(targetPath);
    if (target.exists()) {
      int result = DialogUtils.showQuestionDialog(txFilePath.getShell(), "提示", "当前文件已存在，确认覆盖?");
      if (SWT.YES == result) {
        exportCofigFile(target, fileContent);
      }
    }

  }

  private void exportCofigFile(File target, String fileContent) {
    try {
      FileUtils.write(target, fileContent, StandardCharsets.UTF_8);
      DialogUtils.showInformationDialog(Display.getCurrent().getActiveShell(), "提示", "导出配置文件成功!");
    } catch (IOException e) {
      DialogUtils.showErrorDialog(Display.getCurrent().getActiveShell(), "错误提示", "导出配置文件失败!");
    }

  }
}
