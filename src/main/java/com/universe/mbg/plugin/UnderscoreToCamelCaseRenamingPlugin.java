package com.universe.mbg.plugin;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.config.Context;

public class UnderscoreToCamelCaseRenamingPlugin extends PluginAdapter {

  /**
   * sql映射xml文件名后缀
   */
  private static final String DEFAULT_SQL_MAPPER_NAME_SUFFIX = "Mapper";
  /**
   * 需要去除的表名前缀
   */
  private static final String DEFAULT_EXCLUDED_TABLE_NAME_PREFIX = "tbl";
  /**
   * 需要添加的领域对象名后缀
   */
  private static final String DEFAULT_DOMAIN_OBJECT_NAME_SUFFIX = "Do";

  @Override
  public boolean validate(List<String> warnings) {
    return true;
  }

  @SuppressWarnings("unused")
  @Override
  public void initialized(IntrospectedTable introspectedTable) {
    String modelName = converToModelName(introspectedTable);
    if (StringUtils.isBlank(modelName)) {
      return;
    }

    // 重新初始化
    Context contenxt = introspectedTable.getContext();
    String javaModelTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
    String javaClientTargetPackage = context.getJavaClientGeneratorConfiguration().getTargetPackage();

    // java实体全限定类名
    introspectedTable.setBaseRecordType(javaModelTargetPackage + "." + modelName + DEFAULT_DOMAIN_OBJECT_NAME_SUFFIX);
    // Mapper接口全限定类名
    introspectedTable.setMyBatis3JavaMapperType(javaClientTargetPackage + "." + modelName + DEFAULT_SQL_MAPPER_NAME_SUFFIX);
    // sql映射文件完整文件名
    introspectedTable.setMyBatis3XmlMapperFileName(modelName + DEFAULT_SQL_MAPPER_NAME_SUFFIX + ".xml");
  }

  /**
   * 数据库表名下划线转驼峰获取实体名
   * @param rawTableName
   * @return
   */
  private String converToModelName(IntrospectedTable introspectedTable) {
    String rawTableName = introspectedTable.getFullyQualifiedTableNameAtRuntime();
    if (StringUtils.isBlank(rawTableName)) {
      return null;
    }

    StringBuilder modelNameBuilder = new StringBuilder();
    for (String item : rawTableName.split("_")) {
      if (!item.equalsIgnoreCase(DEFAULT_EXCLUDED_TABLE_NAME_PREFIX)) {
        modelNameBuilder.append(item.substring(0, 1).toUpperCase());
        modelNameBuilder.append(item.substring(1).toLowerCase());
      }
    }

    return modelNameBuilder.toString();
  }

}
