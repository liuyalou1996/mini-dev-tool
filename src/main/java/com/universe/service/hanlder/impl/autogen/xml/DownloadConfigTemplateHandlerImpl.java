package com.universe.service.hanlder.impl.autogen.xml;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.universe.common.util.DialogUtils;
import com.universe.common.util.IoUtils;
import com.universe.service.hanlder.GenByXmlButtonSelectionHandler;

public class DownloadConfigTemplateHandlerImpl implements GenByXmlButtonSelectionHandler {

  private static final String TEMPLATE_NAME = "mybatis-generator-template.xml";

  @Override
  public void onButtonSelected(Text txFilePath, StyledText txFileContent) {
    Shell shell = Display.getCurrent().getActiveShell();
    String targetPath = DialogUtils.showDirectoryDialog(shell);
    if (StringUtils.isBlank(targetPath)) {
      return;
    }

    String templatePath = IoUtils.getProjectPath("template", TEMPLATE_NAME);
    try {
      String content = FileUtils.readFileToString(new File(templatePath), StandardCharsets.UTF_8);
      FileUtils.write(new File(targetPath, TEMPLATE_NAME), content, StandardCharsets.UTF_8);
      DialogUtils.showInformationDialog(shell, "提示", "模板下载成功!");
    } catch (IOException e) {
      DialogUtils.showErrorDialog(shell, "错误提示", "下载模板失败!");
    }
  }

}
