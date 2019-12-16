package com.universe.service.hanlder.impl.autogen.xml;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Text;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.universe.common.util.DialogUtils;
import com.universe.mbg.runnable.MbgProgressRunnable;
import com.universe.service.hanlder.GenByXmlButtonSelectionHandler;

public class GenByXmlHanlderImpl implements GenByXmlButtonSelectionHandler {

  private static Logger LOGGER = LoggerFactory.getLogger(GenByXmlHanlderImpl.class);

  @Override
  public void onButtonSelected(Text txFilePath, StyledText txFileContent) {
    String configContent = txFileContent.getText();
    if (StringUtils.isBlank(configContent)) {
      return;
    }

    ByteArrayInputStream bis = new ByteArrayInputStream(configContent.getBytes(StandardCharsets.UTF_8));
    List<String> warnings = new ArrayList<String>();
    try {
      ConfigurationParser cp = new ConfigurationParser(warnings);
      Configuration config = cp.parseConfiguration(bis);
      DefaultShellCallback callback = new DefaultShellCallback(true);
      MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
      // 展示进度条对话框
      ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(txFileContent.getShell());
      progressDialog.run(true, true, new MbgProgressRunnable(myBatisGenerator));
    } catch (Exception e) {
      LOGGER.error("生成代码异常：{}", e.getMessage(), e);
      DialogUtils.showErrorDialog(txFileContent.getShell(), "错误提示", e.getMessage());
    }
  }

}
