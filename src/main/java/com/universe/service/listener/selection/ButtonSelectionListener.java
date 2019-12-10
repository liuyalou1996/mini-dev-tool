package com.universe.service.listener.selection;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.universe.constant.ButtonTypeConsts;
import com.universe.constant.SystemConsts;
import com.universe.util.DialogUtils;
import com.universe.util.IoUtils;

public class ButtonSelectionListener extends SelectionAdapter {

  private static final Logger LOGGER = LoggerFactory.getLogger(ButtonSelectionListener.class);

  private static final String TEMPLATE_NAME = "mybatis-generator-template.xml";

  private Text txFilePath;
  private StyledText txFileContent;

  public ButtonSelectionListener() {
  }

  public ButtonSelectionListener(StyledText txFileContent) {
    this.txFileContent = txFileContent;
  }

  public ButtonSelectionListener(Text txFilePath, StyledText txFileContent) {
    this.txFilePath = txFilePath;
    this.txFileContent = txFileContent;
  }

  @Override
  public void widgetSelected(SelectionEvent e) {
    Button button = (Button) e.widget;
    String btnType = (String) button.getData(SystemConsts.BUTTON_TYPE);

    if (ButtonTypeConsts.CHOOSE_CONFIG_FILE.equals(btnType)) {
      onChooseConfigFileBtnSelected(txFilePath, txFileContent);
    } else if (ButtonTypeConsts.AUTO_GENERATE_CODE.equals(btnType)) {
      onAutoGenerateCodeBtnSelected(txFileContent);
    } else if (ButtonTypeConsts.DOWNLOAD_CONFIG_TEMPLATE.equals(btnType)) {
      onDownloadConfigTemplateSelected(button.getShell());
    }
  }

  /**
   * 选择mybatis自动生成配置文件事件处理
   * @param txConfigPath
   * @param txConfigContent
   */
  private void onChooseConfigFileBtnSelected(Text txConfigPath, StyledText txConfigContent) {
    String[] filterExtensios = new String[] { "*.xml" };
    String[] filterNames = new String[] { "Xml Files(*.xml)" };
    String filePath = DialogUtils.showOpenFileDialog(txConfigPath.getShell(), filterExtensios, filterNames);
    if (StringUtils.isBlank(filePath)) {
      return;
    }

    // 展示配置文件路径
    txConfigPath.setText(filePath);
    // 展示配置文件内容
    try {
      String configFileContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
      txConfigContent.setText(configFileContent);
    } catch (IOException e) {
      DialogUtils.showErrorDialog(txConfigPath.getShell(), "错误提示", "配置文件读取失败!");
    }

  }

  /**
   * 自动生成代码事件处理
   * @param styledText
   */
  private void onAutoGenerateCodeBtnSelected(StyledText styledText) {
    new Thread(() -> {
      Display.getDefault().asyncExec(() -> {
        autoGenerate(styledText);
      });
    }).start();
  }

  private void autoGenerate(StyledText styledText) {
    String configContent = styledText.getText();
    if (StringUtils.isBlank(configContent)) {
      return;
    }

    ByteArrayInputStream bis = new ByteArrayInputStream(configContent.getBytes(StandardCharsets.UTF_8));
    List<String> warnings = new ArrayList<String>();

    ConfigurationParser cp = new ConfigurationParser(warnings);
    try {
      Configuration config = cp.parseConfiguration(bis);
      DefaultShellCallback callback = new DefaultShellCallback(true);
      MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
      myBatisGenerator.generate(null);
      DialogUtils.showInformationDialog(styledText.getShell(), "错误提示", "代码自动生成成功!");
    } catch (Exception e) {
      LOGGER.error("生成模板异常：{}", e.getMessage(), e);
      DialogUtils.showErrorDialog(styledText.getShell(), "错误提示", e.getMessage());
    }
  }

  /**
   * 下载mybatis配置文件模板事件处理
   * @param shell
   */
  private void onDownloadConfigTemplateSelected(Shell shell) {
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
