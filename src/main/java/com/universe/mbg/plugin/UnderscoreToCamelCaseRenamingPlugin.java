package com.universe.mbg.plugin;

import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.config.Context;

/**
 * 数据库表名下划线转驼峰成实体类名
 * @author: liuyalou
 * @date: 2019年12月13日
 */
public class UnderscoreToCamelCaseRenamingPlugin extends PluginAdapter {

  private static final String REMOVED_TABLE_PREFIX = "removedTablePrefix";
  private static final String DOMAIN_OBJECT_SUFFIX = "domainObjectNameSuffix";
  private static final String MAPPER_SUFFIX = "mapperSuffix";

  @Override
  public boolean validate(List<String> warnings) {
    return true;
  }

  @SuppressWarnings("unused")
  @Override
  public void initialized(IntrospectedTable introspectedTable) {
    // 父类自带属性holder
    Properties properties = super.getProperties();
    String modelName = converToModelName(introspectedTable, (String) properties.get(REMOVED_TABLE_PREFIX));
    if (StringUtils.isBlank(modelName)) {
      return;
    }

    Context contenxt = introspectedTable.getContext();
    String javaModelTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
    String javaClientTargetPackage = context.getJavaClientGeneratorConfiguration().getTargetPackage();

    String baseRecordType = getBaseRecordType(javaModelTargetPackage, modelName, (String) properties.get(DOMAIN_OBJECT_SUFFIX));
    String javaMapperType = getMapperType(javaClientTargetPackage, modelName, (String) properties.get(MAPPER_SUFFIX));
    String mapperFileName = getMapperFileName(modelName, (String) properties.get(MAPPER_SUFFIX));

    // java实体全限定类名
    introspectedTable.setBaseRecordType(baseRecordType);
    // Mapper接口全限定类名
    introspectedTable.setMyBatis3JavaMapperType(javaMapperType);
    // sql映射文件完整文件名
    introspectedTable.setMyBatis3XmlMapperFileName(mapperFileName);
  }

  /**
   * 数据库表名下划线转驼峰获取实体名
   * @param introspectedTable
   * @param removedTablePrefix
   * @return
   */
  private String converToModelName(IntrospectedTable introspectedTable, String removedTablePrefix) {
    String rawTableName = introspectedTable.getFullyQualifiedTableNameAtRuntime();
    if (StringUtils.isBlank(rawTableName)) {
      return null;
    }

    StringBuilder modelNameBuilder = new StringBuilder();
    for (String item : rawTableName.split("_")) {
      if (!item.equalsIgnoreCase(removedTablePrefix)) {
        modelNameBuilder.append(item.substring(0, 1).toUpperCase());
        modelNameBuilder.append(item.substring(1).toLowerCase());
      }
    }

    return modelNameBuilder.toString();
  }

  public String getBaseRecordType(String javaModelTargetPackage, String modelName, String domainObjectSuffix) {
    String realModelName = modelName + (StringUtils.isBlank(domainObjectSuffix) ? "" : domainObjectSuffix);
    return javaModelTargetPackage + "." + realModelName;
  }

  private String getMapperType(String javaClientTargetPackage, String modelName, String sqlMapperSuffix) {
    String realMapperName = modelName + (StringUtils.isBlank(sqlMapperSuffix) ? "" : sqlMapperSuffix);
    return javaClientTargetPackage + "." + realMapperName;
  }

  private String getMapperFileName(String modelName, String sqlMapperSuffix) {
    return modelName + (StringUtils.isBlank(sqlMapperSuffix) ? "" : sqlMapperSuffix) + ".xml";
  }

}
