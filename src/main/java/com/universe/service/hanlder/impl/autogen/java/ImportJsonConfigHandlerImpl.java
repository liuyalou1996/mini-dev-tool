package com.universe.service.hanlder.impl.autogen.java;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.widgets.Display;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.universe.common.util.DialogUtils;
import com.universe.common.util.JsonUtils;
import com.universe.pojo.dto.GenByJavaDto;
import com.universe.pojo.dto.JsonConfigDto;
import com.universe.service.hanlder.GenByJavaButtonSelectionHandler;

public class ImportJsonConfigHandlerImpl implements GenByJavaButtonSelectionHandler {

  private static Logger logger = LoggerFactory.getLogger(ImportJsonConfigHandlerImpl.class);

  @Override
  public void onButtonSelected(GenByJavaDto dto) {
    String[] filterExtensios = new String[] { "*.json" };
    String[] filterNames = new String[] { "Json Files(*.json)" };
    String filePath = DialogUtils.showOpenFileDialog(Display.getCurrent().getActiveShell(), null, filterExtensios, filterNames);
    if (StringUtils.isBlank(filePath)) {
      return;
    }

    JsonConfigDto jsonConfigDto = importJsonConfig(filePath);
    if (jsonConfigDto == null) {
      DialogUtils.showInformationDialog(Display.getCurrent().getActiveShell(), "提示", "导入配置文件(.json)失败!");
      return;
    }

    dto.getTxDriverPath().setText(jsonConfigDto.getDriverPath());
    dto.getComboDriver().setText(jsonConfigDto.getDriverClassName());
    dto.getTxUrl().setText(jsonConfigDto.getUrl());
    dto.getTxUsername().setText(jsonConfigDto.getUsername());
    dto.getTxPassword().setText(jsonConfigDto.getPassword());

    dto.getTxTargetProject().setText(jsonConfigDto.getTargetProject());
    dto.getTxModelTargetPackage().setText(jsonConfigDto.getDomainObjectPackage());
    dto.getTxClientTargetPackage().setText(jsonConfigDto.getMapperInterfacePackage());
    dto.getTxXmlTargetPackage().setText(jsonConfigDto.getMapperXmlPackage());

    dto.getBtnAllTables().setSelection(jsonConfigDto.isGenAllTables());
    if (jsonConfigDto.isGenInputTables()) {
      dto.getBtnInputTables().setSelection(jsonConfigDto.isGenInputTables());
      dto.getTxInputTables().setEnabled(true);
      dto.getTxInputTables().setText(jsonConfigDto.getInputTables());
    } else {
      dto.getBtnInputTables().setSelection(false);
      dto.getTxInputTables().setEnabled(false);
    }

    dto.getBtnEnableSelectByExample().setSelection(jsonConfigDto.isEnableSelectByExample());
    dto.getBtnEnableUpdateByExample().setSelection(jsonConfigDto.isEnableUpdateByExample());
    dto.getBtnEnableDeleteByExample().setSelection(jsonConfigDto.isEnableDeleteByExample());
    dto.getBtnEnableCountByExample().setSelection(jsonConfigDto.isEnableCountByExample());
    dto.getBtnEnableSelectByPrimaryKey().setSelection(jsonConfigDto.isEnableSelectByPrimaryKey());
    dto.getBtnEnableUpdateByPrimaryKey().setSelection(jsonConfigDto.isEnableUpdateByPrimaryKey());
    dto.getBtnEnableDeleteByPrimaryKey().setSelection(jsonConfigDto.isEnableDeleteByPrimaryKey());
    dto.getBtnEnableInsert().setSelection(jsonConfigDto.isEnableInsert());
    dto.getBtnEnableToString().setSelection(jsonConfigDto.isEnableToString());
    dto.getBtnUseActualColumnNames().setSelection(jsonConfigDto.isUseActualColumnNames());
    dto.getBtnUseColumnIndexes().setSelection(jsonConfigDto.isUseColumnIndexes());
    dto.getBtnTrimStrings().setSelection(jsonConfigDto.isTrimStrings());
    dto.getBtnForceBigDecimal().setSelection(jsonConfigDto.isForceBigDecimal());

    if (jsonConfigDto.isEnableCamelCase()) {
      dto.getBtnEnableCamelCase().setSelection(jsonConfigDto.isEnableCamelCase());
      dto.getTxRemovedTablePrefix().setEnabled(true);
      dto.getTxClassSuffix().setEnabled(true);
      dto.getTxMapperSuffix().setEnabled(true);
      dto.getTxRemovedTablePrefix().setText(jsonConfigDto.getRemovedTablePrefix());
      dto.getTxClassSuffix().setText(jsonConfigDto.getClassSuffix());
      dto.getTxMapperSuffix().setText(jsonConfigDto.getMapperSuffix());
    } else {
      dto.getBtnEnableCamelCase().setSelection(false);
      dto.getTxRemovedTablePrefix().setEnabled(false);
      dto.getTxClassSuffix().setEnabled(false);
      dto.getTxMapperSuffix().setEnabled(false);
    }
  }

  private JsonConfigDto importJsonConfig(String filePath) {
    JsonConfigDto jsonConfigDto = null;
    try {
      String jsonStr = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
      jsonConfigDto = JsonUtils.toJavaBean(jsonStr, JsonConfigDto.class);
    } catch (Exception e) {
      logger.error("导入配置文件失败(.json)：{}", e.getMessage(), e);
    }

    return jsonConfigDto;
  }

}
