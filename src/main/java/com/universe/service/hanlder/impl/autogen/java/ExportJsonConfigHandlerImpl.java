package com.universe.service.hanlder.impl.autogen.java;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import com.universe.common.util.DialogUtils;
import com.universe.common.util.JsonUtils;
import com.universe.pojo.dto.GenByJavaDto;
import com.universe.pojo.dto.JsonConfigDto;
import com.universe.service.hanlder.GenByJavaButtonSelectionHandler;

public class ExportJsonConfigHandlerImpl implements GenByJavaButtonSelectionHandler {

  @Override
  public void onButtonSelected(GenByJavaDto dto) {
    String[] filterExtensions = new String[] { "*.json" };;
    String[] filterNames = new String[] { "Json Files(*.json)" };
    String targetPath = DialogUtils.showSaveFileDialog(Display.getCurrent().getActiveShell(), null, filterExtensions, filterNames);
    if (StringUtils.isBlank(targetPath)) {
      return;
    }

    JsonConfigDto jsonConfigDto = buildExportContent(dto);
    String fileContent = JsonUtils.toPrettyJsonStringWithNullValue(jsonConfigDto);
    File target = new File(targetPath);
    if (target.exists()) {
      int result = DialogUtils.showQuestionDialog(Display.getCurrent().getActiveShell(), "提示", "当前文件已存在，确认覆盖?");
      if (SWT.OK == result) {
        exportJsonConfigFile(fileContent, target);
      }
      return;
    }

    exportJsonConfigFile(fileContent, target);
  }

  private void exportJsonConfigFile(String fileContent, File target) {
    try {
      FileUtils.write(target, fileContent, StandardCharsets.UTF_8);
      DialogUtils.showInformationDialog(Display.getCurrent().getActiveShell(), "提示", "导出配置文件(.json)成功!");
    } catch (IOException e) {
      DialogUtils.showErrorDialog(Display.getCurrent().getActiveShell(), "错误提示", "导出配置文件(.json)失败!");
    }
  }

  private JsonConfigDto buildExportContent(GenByJavaDto dto) {
    JsonConfigDto jsonConfigDto = new JsonConfigDto();

    jsonConfigDto.setDriverPath(StringUtils.trim(dto.getTxDriverPath().getText()));
    jsonConfigDto.setDriverClassName(StringUtils.trim(dto.getComboDriver().getText()));
    jsonConfigDto.setUrl(StringUtils.trim(dto.getTxUrl().getText()));
    jsonConfigDto.setUsername(StringUtils.trim(dto.getTxUsername().getText()));
    jsonConfigDto.setPassword(StringUtils.trim(dto.getTxPassword().getText()));

    jsonConfigDto.setTargetProject(StringUtils.trim(dto.getTxTargetProject().getText()));
    jsonConfigDto.setDomainObjectPackage(StringUtils.trim(dto.getTxModelTargetPackage().getText()));
    jsonConfigDto.setMapperInterfacePackage(StringUtils.trim(dto.getTxClientTargetPackage().getText()));
    jsonConfigDto.setMapperXmlPackage(StringUtils.trim(dto.getTxXmlTargetPackage().getText()));

    jsonConfigDto.setGenAllTables(dto.getBtnAllTables().getSelection());
    jsonConfigDto.setGenInputTables(dto.getBtnInputTables().getSelection());
    jsonConfigDto.setInputTables(StringUtils.trim(dto.getTxInputTables().getText()));

    jsonConfigDto.setEnableSelectByExample(dto.getBtnEnableSelectByExample().getSelection());
    jsonConfigDto.setEnableUpdateByExample(dto.getBtnEnableUpdateByExample().getSelection());
    jsonConfigDto.setEnableDeleteByExample(dto.getBtnEnableDeleteByExample().getSelection());
    jsonConfigDto.setEnableCountByExample(dto.getBtnEnableCountByExample().getSelection());
    jsonConfigDto.setEnableSelectByPrimaryKey(dto.getBtnEnableSelectByPrimaryKey().getSelection());
    jsonConfigDto.setEnableUpdateByPrimaryKey(dto.getBtnEnableUpdateByPrimaryKey().getSelection());
    jsonConfigDto.setEnableDeleteByPrimaryKey(dto.getBtnEnableDeleteByPrimaryKey().getSelection());
    jsonConfigDto.setEnableInsert(dto.getBtnEnableInsert().getSelection());
    jsonConfigDto.setEnableToString(dto.getBtnEnableToString().getSelection());
    jsonConfigDto.setUseActualColumnNames(dto.getBtnUseActualColumnNames().getSelection());
    jsonConfigDto.setUseColumnIndexes(dto.getBtnUseColumnIndexes().getSelection());
    jsonConfigDto.setTrimStrings(dto.getBtnTrimStrings().getSelection());
    jsonConfigDto.setForceBigDecimal(dto.getBtnForceBigDecimal().getSelection());

    jsonConfigDto.setEnableCamelCase(dto.getBtnEnableCamelCase().getSelection());
    jsonConfigDto.setRemovedTablePrefix(dto.getTxRemovedTablePrefix().getText());
    jsonConfigDto.setClassSuffix(dto.getTxClassSuffix().getText());
    jsonConfigDto.setMapperSuffix(dto.getTxMapperSuffix().getText());
    return jsonConfigDto;
  }

}
