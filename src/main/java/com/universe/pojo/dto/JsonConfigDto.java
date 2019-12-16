package com.universe.pojo.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class JsonConfigDto {

  private String driverPath;
  private String driverClassName;
  private String url;
  private String username;
  private String password;

  private String targetProject;
  private String domainObjectPackage;
  private String mapperInterfacePackage;
  private String mapperXmlPackage;

  private boolean genAllTables;
  private boolean genInputTables;
  private String inputTables;

  private boolean enableSelectByExample;
  private boolean enableUpdateByExample;
  private boolean enableDeleteByExample;
  private boolean enableCountByExample;

  private boolean enableSelectByPrimaryKey;
  private boolean enableUpdateByPrimaryKey;
  private boolean enableDeleteByPrimaryKey;
  private boolean enableInsert;

  private boolean enableToString;
  private boolean useActualColumnNames;
  private boolean useColumnIndexes;
  private boolean trimStrings;
  private boolean forceBigDecimal;

  private boolean enableCamelCase;
  private String removedTablePrefix;
  private String classSuffix;
  private String mapperSuffix;

  public String getDriverPath() {
    return driverPath;
  }

  public void setDriverPath(String driverPath) {
    this.driverPath = driverPath;
  }

  public String getDriverClassName() {
    return driverClassName;
  }

  public void setDriverClassName(String driverClassName) {
    this.driverClassName = driverClassName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTargetProject() {
    return targetProject;
  }

  public void setTargetProject(String targetProject) {
    this.targetProject = targetProject;
  }

  public String getDomainObjectPackage() {
    return domainObjectPackage;
  }

  public void setDomainObjectPackage(String domainObjectPackage) {
    this.domainObjectPackage = domainObjectPackage;
  }

  public String getMapperInterfacePackage() {
    return mapperInterfacePackage;
  }

  public void setMapperInterfacePackage(String mapperInterfacePackage) {
    this.mapperInterfacePackage = mapperInterfacePackage;
  }

  public String getMapperXmlPackage() {
    return mapperXmlPackage;
  }

  public void setMapperXmlPackage(String mapperXmlPackage) {
    this.mapperXmlPackage = mapperXmlPackage;
  }

  public boolean isGenAllTables() {
    return genAllTables;
  }

  public void setGenAllTables(boolean genAllTables) {
    this.genAllTables = genAllTables;
  }

  public boolean isGenInputTables() {
    return genInputTables;
  }

  public void setGenInputTables(boolean genInputTables) {
    this.genInputTables = genInputTables;
  }

  public String getInputTables() {
    return inputTables;
  }

  public void setInputTables(String inputTables) {
    this.inputTables = inputTables;
  }

  public boolean isEnableSelectByExample() {
    return enableSelectByExample;
  }

  public void setEnableSelectByExample(boolean enableSelectByExample) {
    this.enableSelectByExample = enableSelectByExample;
  }

  public boolean isEnableUpdateByExample() {
    return enableUpdateByExample;
  }

  public void setEnableUpdateByExample(boolean enableUpdateByExample) {
    this.enableUpdateByExample = enableUpdateByExample;
  }

  public boolean isEnableDeleteByExample() {
    return enableDeleteByExample;
  }

  public void setEnableDeleteByExample(boolean enableDeleteByExample) {
    this.enableDeleteByExample = enableDeleteByExample;
  }

  public boolean isEnableCountByExample() {
    return enableCountByExample;
  }

  public void setEnableCountByExample(boolean enableCountByExample) {
    this.enableCountByExample = enableCountByExample;
  }

  public boolean isEnableSelectByPrimaryKey() {
    return enableSelectByPrimaryKey;
  }

  public void setEnableSelectByPrimaryKey(boolean enableSelectByPrimaryKey) {
    this.enableSelectByPrimaryKey = enableSelectByPrimaryKey;
  }

  public boolean isEnableUpdateByPrimaryKey() {
    return enableUpdateByPrimaryKey;
  }

  public void setEnableUpdateByPrimaryKey(boolean enableUpdateByPrimaryKey) {
    this.enableUpdateByPrimaryKey = enableUpdateByPrimaryKey;
  }

  public boolean isEnableDeleteByPrimaryKey() {
    return enableDeleteByPrimaryKey;
  }

  public void setEnableDeleteByPrimaryKey(boolean enableDeleteByPrimaryKey) {
    this.enableDeleteByPrimaryKey = enableDeleteByPrimaryKey;
  }

  public boolean isEnableInsert() {
    return enableInsert;
  }

  public void setEnableInsert(boolean enableInsert) {
    this.enableInsert = enableInsert;
  }

  public boolean isEnableToString() {
    return enableToString;
  }

  public void setEnableToString(boolean enableToString) {
    this.enableToString = enableToString;
  }

  public boolean isUseActualColumnNames() {
    return useActualColumnNames;
  }

  public void setUseActualColumnNames(boolean useActualColumnNames) {
    this.useActualColumnNames = useActualColumnNames;
  }

  public boolean isUseColumnIndexes() {
    return useColumnIndexes;
  }

  public void setUseColumnIndexes(boolean useColumnIndexes) {
    this.useColumnIndexes = useColumnIndexes;
  }

  public boolean isTrimStrings() {
    return trimStrings;
  }

  public void setTrimStrings(boolean trimStrings) {
    this.trimStrings = trimStrings;
  }

  public boolean isForceBigDecimal() {
    return forceBigDecimal;
  }

  public void setForceBigDecimal(boolean forceBigDecimal) {
    this.forceBigDecimal = forceBigDecimal;
  }

  public boolean isEnableCamelCase() {
    return enableCamelCase;
  }

  public void setEnableCamelCase(boolean enableCamelCase) {
    this.enableCamelCase = enableCamelCase;
  }

  public String getRemovedTablePrefix() {
    return removedTablePrefix;
  }

  public void setRemovedTablePrefix(String removedTablePrefix) {
    this.removedTablePrefix = removedTablePrefix;
  }

  public String getClassSuffix() {
    return classSuffix;
  }

  public void setClassSuffix(String classSuffix) {
    this.classSuffix = classSuffix;
  }

  public String getMapperSuffix() {
    return mapperSuffix;
  }

  public void setMapperSuffix(String mapperSuffix) {
    this.mapperSuffix = mapperSuffix;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}
