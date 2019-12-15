package com.universe.service.hanlder.impl.autogen.java;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PluginConfiguration;

import com.universe.pojo.dto.GenByJavaDto;
import com.universe.service.hanlder.GenByJavaButtonSelectionHandler;

public class GenByJavaHandlerImpl implements GenByJavaButtonSelectionHandler {

  @Override
  public void onButtonSelected(GenByJavaDto dto) {
    String driver = StringUtils.trim(dto.getComboDriver().getText());
    String driverJarPath = StringUtils.trim(dto.getTxDrivePath().getText());
    String url = StringUtils.trim(dto.getTxUrl().getText());
    String username = StringUtils.trim(dto.getTxUsername().getText());
    String password = StringUtils.trim(dto.getTxPassword().getText());

    String targetProject = StringUtils.trim(dto.getTxTargetProject().getText());
    String modelTargetPackage = StringUtils.trim(dto.getTxModelTargetPackage().getText());
    String clientTargetPackage = StringUtils.trim(dto.getTxClientTargetPackage().getText());
    String xmlTargetPackage = StringUtils.trim(dto.getTxXmlTargetPackage().getText());

    boolean genAllTables = dto.getBtnAllTables().getSelection();
    boolean genInputTables = dto.getBtnInputTables().getSelection();
    // 逗号分隔
    String inputTables = StringUtils.trim(dto.getTxInputTables().getText());
    boolean enableSelectByExample = dto.getBtnEnableSelectByExample().getSelection();
    boolean enableUpdateByExample = dto.getBtnEnableUpdateByExample().getSelection();
    boolean enableDeleteByExample = dto.getBtnEnableDeleteByExample().getSelection();
    boolean enableCountByExample = dto.getBtnEnableCountByExample().getSelection();

    boolean enableSelectByPrimaryKey = dto.getBtnEnableSelectByPrimaryKey().getSelection();
    boolean enableUpdateByPrimaryKey = dto.getBtnEnableUpdateByPrimaryKey().getSelection();
    boolean enableDeleteBYPrimaryKey = dto.getBtnEnableDeleteByPrimaryKey().getSelection();
    boolean enableInsert = dto.getBtnEnableInsert().getSelection();

    boolean enableToString = dto.getBtnEnableToString().getSelection();
    boolean useActualColumnNames = dto.getBtnUseActualColumnNames().getSelection();
    boolean useColumnIndexes = dto.getBtnUseColumnIndexes().getSelection();
    boolean trimString = dto.getBtnTrimStrings().getSelection();
    boolean forceBigdecimal = dto.getBtnForceBigDecimal().getSelection();

    boolean enableCamelCase = dto.getBtnEnableCamelCase().getSelection();

    Context context = buildContext();
    addPluginConfiguration(context, dto);
  }

  private void addPluginConfiguration(Context context, GenByJavaDto dto) {
    boolean enableToString = dto.getBtnEnableToString().getSelection();
    boolean enableCamelCase = dto.getBtnEnableCamelCase().getSelection();

    if (enableToString) {
      PluginConfiguration toStringPluginConfig = new PluginConfiguration();
      toStringPluginConfig.setConfigurationType("com.universe.mbg.plugin.CommonsLangToStringPlugin");
      context.addPluginConfiguration(toStringPluginConfig);
    }

    if (enableCamelCase) {
      PluginConfiguration underscoreToCamelCasePluginConfig = new PluginConfiguration();
      underscoreToCamelCasePluginConfig.setConfigurationType("com.universe.mbg.plugin.UnderscoreToCamelCaseRenamingPlugin");
      underscoreToCamelCasePluginConfig.addProperty("domainObjectNameSuffix", "Dao");
      underscoreToCamelCasePluginConfig.addProperty("removedTablePrefix", "tbl");
    }
  }

  private Context buildContext() {
    Context context = new Context(ModelType.CONDITIONAL);
    context.setId("autoGeneration");
    context.setTargetRuntime("MyBatis3");
    return context;
  }

}
