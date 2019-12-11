package com.universe.common.plugin;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;
import static org.mybatis.generator.internal.util.StringUtility.stringContainsSpace;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.config.TableConfiguration;

public class UnderscoreToCamelCaseRenamingPlugin extends PluginAdapter {
  
  /**
   * sql映射xml文件名后缀
   */
  private String sqlMapperNameSuffix = "Mapper";
  /**
   * 需要去除的表名前缀
   */
  private String excludedTableNamePrefix = "tbl";
  /**
   * 需要添加的领域对象名后缀
   */
  private String domainObjectNameSuffix = "Do";

  public String getSqlMapperNameSuffix() {
    return sqlMapperNameSuffix;
  }

  public String getExcludedTableNamePrefix() {
    return excludedTableNamePrefix;
  }

  public String getDomainObjectNameSuffix() {
    return domainObjectNameSuffix;
  }

  public void setSqlMapperNameSuffix(String sqlMapperNameSuffix) {
    this.sqlMapperNameSuffix = sqlMapperNameSuffix;
  }

  public void setExcludedTableNamePrefix(String excludedTableNamePrefix) {
    this.excludedTableNamePrefix = excludedTableNamePrefix;
  }

  public void setDomainObjectNameSuffix(String domainObjectNameSuffix) {
    this.domainObjectNameSuffix = domainObjectNameSuffix;
  }

  @Override
  public boolean validate(List<String> warnings) {
    return true;
  }

  @Override
  public void initialized(IntrospectedTable introspectedTable) {
    String rawTableName = introspectedTable.getFullyQualifiedTableNameAtRuntime();
    if (StringUtils.isBlank(rawTableName)) {
      return;
    }

    String modelName = converToModelName(rawTableName);
    // sql映射文件完整文件名
    introspectedTable.setMyBatis3XmlMapperFileName(modelName + sqlMapperNameSuffix + ".xml");
    
    TableConfiguration tc = introspectedTable.getTableConfiguration();
    tc.setDomainObjectName(modelName + domainObjectNameSuffix);
    
    FullyQualifiedTable newTable = rebuildFullyQualifiedTable(tc, introspectedTable.getFullyQualifiedTable());
    // 重新初始化
    introspectedTable.setFullyQualifiedTable(newTable);
  }
  
  /**
   * 数据库表名下划线转驼峰获取model名
   * @param rawTableName
   * @return
   */
  private String converToModelName(String rawTableName) {
    StringBuilder modelNameBuilder = new StringBuilder();
    for (String item : rawTableName.split("_")) {
      if (!item.equalsIgnoreCase(excludedTableNamePrefix)) {
        modelNameBuilder.append(item.substring(0, 1).toUpperCase());
        modelNameBuilder.append(item.substring(1).toLowerCase());
      }
    }
    
    return modelNameBuilder.toString();
  }

  private FullyQualifiedTable rebuildFullyQualifiedTable(TableConfiguration tc, FullyQualifiedTable rawTable) {
    boolean delimitIdentifiers = tc.isDelimitIdentifiers() 
        || stringContainsSpace(tc.getCatalog()) 
        || stringContainsSpace(tc.getSchema())
        || stringContainsSpace(tc.getTableName());
    
    FullyQualifiedTable table = new FullyQualifiedTable(
        rawTable.getIntrospectedCatalog(),
        rawTable.getIntrospectedSchema(),
        rawTable.getIntrospectedTableName(),
        tc.getDomainObjectName(),
        rawTable.getAlias(),
        isTrue(tc.getProperty(PropertyRegistry.TABLE_IGNORE_QUALIFIERS_AT_RUNTIME)),
        tc.getProperty(PropertyRegistry.TABLE_RUNTIME_CATALOG),
        tc.getProperty(PropertyRegistry.TABLE_RUNTIME_SCHEMA),
        tc.getProperty(PropertyRegistry.TABLE_RUNTIME_TABLE_NAME),
        delimitIdentifiers,
        tc.getDomainObjectRenamingRule(),
        context);
    return table;
  }

}
