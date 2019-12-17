package com.universe.service.hanlder.impl.autogen.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Display;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.universe.common.util.DialogUtils;
import com.universe.mbg.runnable.MbgProgressRunnable;
import com.universe.pojo.dto.GenByJavaDto;
import com.universe.service.hanlder.GenByJavaButtonSelectionHandler;

public class GenByJavaHandlerImpl implements GenByJavaButtonSelectionHandler {

  private static Logger logger = LoggerFactory.getLogger(GenByJavaHandlerImpl.class);

  private static final String JAVA_PATH = "src" + File.separator + "main" + File.separator + "java";
  private static final String RESOURCES_PATH = "src" + File.separator + "main" + File.separator + "resources";

  @Override
  public void onButtonSelected(GenByJavaDto dto) {
    Context context = buildContext();
    try {
      // 添加插件配置
      addPluginConfiguration(context, dto);
      // 添加注释配置，默认不生成
      setCommentGeneratorConfiguration(context, dto);
      // 添加数据库连接配置
      setJdbcConnectionConfiguration(context, dto);
      // 添加Java类型解析器配置
      setJavaTypeResolverConfiguration(context, dto);
      // 添加实体(领域对象)生成配置
      setJavaModelGeneratorConfiguration(context, dto);
      // 添加sql映射xml文件生成配置
      setSqlMapGeneratorConfiguration(context, dto);
      // 添加Mapper接口生成配置
      setJavaClientGeneratorConfiguration(context, dto);
      // 添加表配置
      addTableConfiguration(context, dto);
      // 生成代码
      generateCode(context, dto);
    } catch (Exception e) {
      logger.error("生成代码异常：{}", e.getMessage(), e);
      DialogUtils.showErrorDialog(Display.getCurrent().getActiveShell(), "错误提示", e.getMessage());
    }
  }

  private void generateCode(Context context, GenByJavaDto dto) throws Exception {
    String driverPath = StringUtils.trim(dto.getTxDriverPath().getText());
    if (StringUtils.isBlank(driverPath)) {
      DialogUtils.showErrorDialog(Display.getCurrent().getActiveShell(), "错误提示", "JDBC驱动包名不能为空!");
    }

    Configuration config = new Configuration();
    config.addClasspathEntry(driverPath);
    config.addContext(context);
    DefaultShellCallback callback = new DefaultShellCallback(true);
    List<String> warnings = new ArrayList<String>();
    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
    // 展示进度条对话框
    ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
    progressDialog.run(true, true, new MbgProgressRunnable(myBatisGenerator));

  }

  private void addTableConfiguration(Context context, GenByJavaDto dto) {
    boolean genAllTables = dto.getBtnAllTables().getSelection();
    if (genAllTables) {
      TableConfiguration tableConfiguration = initTableConfiguration(context, dto);
      // 支持sql通配符
      tableConfiguration.setTableName("%");
      context.addTableConfiguration(tableConfiguration);
    }

    boolean genInputTables = dto.getBtnInputTables().getSelection();
    if (genInputTables) {
      // 英文逗号分隔
      String inputTables = StringUtils.trim(dto.getTxInputTables().getText());
      if (StringUtils.isBlank(inputTables)) {
        return;
      }

      for (String tableName : inputTables.split(",")) {
        TableConfiguration tableConfiguration = initTableConfiguration(context, dto);
        tableConfiguration.setTableName(tableName);
        context.addTableConfiguration(tableConfiguration);
      }
    }

  }

  private TableConfiguration initTableConfiguration(Context context, GenByJavaDto dto) {
    boolean enableSelectByExample = dto.getBtnEnableSelectByExample().getSelection();
    boolean enableUpdateByExample = dto.getBtnEnableUpdateByExample().getSelection();
    boolean enableDeleteByExample = dto.getBtnEnableDeleteByExample().getSelection();
    boolean enableCountByExample = dto.getBtnEnableCountByExample().getSelection();

    boolean enableSelectByPrimaryKey = dto.getBtnEnableSelectByPrimaryKey().getSelection();
    boolean enableUpdateByPrimaryKey = dto.getBtnEnableUpdateByPrimaryKey().getSelection();
    boolean enableDeleteBYPrimaryKey = dto.getBtnEnableDeleteByPrimaryKey().getSelection();
    boolean enableInsert = dto.getBtnEnableInsert().getSelection();

    boolean useActualColumnNames = dto.getBtnUseActualColumnNames().getSelection();
    boolean useColumnIndexes = dto.getBtnUseColumnIndexes().getSelection();
    boolean trimString = dto.getBtnTrimStrings().getSelection();

    TableConfiguration tableConfiguration = new TableConfiguration(context);
    tableConfiguration.setSelectByExampleStatementEnabled(enableSelectByExample);
    tableConfiguration.setUpdateByExampleStatementEnabled(enableUpdateByExample);
    tableConfiguration.setDeleteByExampleStatementEnabled(enableDeleteByExample);
    tableConfiguration.setCountByExampleStatementEnabled(enableCountByExample);
    tableConfiguration.setSelectByPrimaryKeyStatementEnabled(enableSelectByPrimaryKey);
    tableConfiguration.setUpdateByPrimaryKeyStatementEnabled(enableUpdateByPrimaryKey);
    tableConfiguration.setDeleteByPrimaryKeyStatementEnabled(enableDeleteBYPrimaryKey);
    tableConfiguration.setInsertStatementEnabled(enableInsert);

    String url = StringUtils.trim(dto.getTxUrl().getText());
    String username = StringUtils.trim(dto.getTxUsername().getText());
    String schema = parseSchemaFromUrl(url, username);
    if (StringUtils.isNotBlank(schema)) {
      tableConfiguration.setSchema(schema);
      if (url.startsWith("jdbc:mysql")) {
        // mysql数据库还需设置catalog，只设置schema无法区分
        tableConfiguration.setCatalog(schema);
      }
    }

    // 表名前缀不添加模式
    tableConfiguration.addProperty("ignoreQualifiersAtRuntime", "true");
    tableConfiguration.addProperty("useActualColumnNames", String.valueOf(useActualColumnNames));
    tableConfiguration.addProperty("useColumnIndexes", String.valueOf(useColumnIndexes));
    tableConfiguration.addProperty("trimStrings", String.valueOf(trimString));
    return tableConfiguration;
  }

  /**
   * mysql数据库模式就是数据库名，oracle数据模式就是用户名
   * @param url
   * @param username
   * @return
   */
  private String parseSchemaFromUrl(String url, String username) {
    if (url.startsWith("jdbc:mysql")) {
      // mysql高版本可能会带?
      if (url.indexOf("?") > -1) {
        return url.substring(url.lastIndexOf("/") + 1, url.indexOf("?"));
      }
      return url.substring(url.lastIndexOf("/") + 1);
    }

    if (url.startsWith("jdbc:oracle")) {
      return username.toUpperCase();
    }

    throw new IllegalArgumentException("不支持的驱动类名，目前只支持Oracle和Mysql!");
  }

  private void setJavaClientGeneratorConfiguration(Context context, GenByJavaDto dto) {
    String targetProject = StringUtils.trim(dto.getTxTargetProject().getText());
    String clientTargetPackage = StringUtils.trim(dto.getTxClientTargetPackage().getText());

    if (StringUtils.isBlank(clientTargetPackage)) {
      throw new IllegalArgumentException("Mapper接口包名不能为空!");
    }

    JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
    javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");

    javaClientGeneratorConfiguration.setTargetProject(targetProject + File.separator + JAVA_PATH);
    javaClientGeneratorConfiguration.setTargetPackage(clientTargetPackage);
    javaClientGeneratorConfiguration.addProperty("enableSubPackages", "true");

    context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
  }

  private void setSqlMapGeneratorConfiguration(Context context, GenByJavaDto dto) {
    String targetProject = StringUtils.trim(dto.getTxTargetProject().getText());
    String xmlTargetPackage = StringUtils.trim(dto.getTxXmlTargetPackage().getText());

    if (StringUtils.isBlank(xmlTargetPackage)) {
      throw new IllegalArgumentException("Sql映射文件包名不能为空!");
    }

    SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
    sqlMapGeneratorConfiguration.setTargetProject(targetProject + File.separator + RESOURCES_PATH);
    sqlMapGeneratorConfiguration.setTargetPackage(xmlTargetPackage);

    context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
  }

  private void setJavaModelGeneratorConfiguration(Context context, GenByJavaDto dto) {
    String targetProject = StringUtils.trim(dto.getTxTargetProject().getText());
    String modelTargetPackage = StringUtils.trim(dto.getTxModelTargetPackage().getText());
    if (StringUtils.isBlank(targetProject)) {
      throw new IllegalArgumentException("项目路径不能为空!");
    }

    if (StringUtils.isBlank(modelTargetPackage)) {
      throw new IllegalArgumentException("实体包名不能为空!");
    }

    JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
    javaModelGeneratorConfiguration.setTargetProject(targetProject + File.separator + JAVA_PATH);
    javaModelGeneratorConfiguration.setTargetPackage(modelTargetPackage);
    javaModelGeneratorConfiguration.addProperty("enableSubPackages", "false");

    context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
  }

  private void setJavaTypeResolverConfiguration(Context context, GenByJavaDto dto) {
    JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
    javaTypeResolverConfiguration.addProperty("forceBigDecimals", "false");

    context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
  }

  private void setJdbcConnectionConfiguration(Context context, GenByJavaDto dto) {
    String driver = StringUtils.trim(dto.getComboDriver().getText());
    String url = StringUtils.trim(dto.getTxUrl().getText());
    String username = StringUtils.trim(dto.getTxUsername().getText());
    String password = StringUtils.trim(dto.getTxPassword().getText());

    if (StringUtils.isBlank(driver)) {
      throw new IllegalArgumentException("jdbc驱动名不能为空!");
    }

    if (StringUtils.isBlank(url)) {
      throw new IllegalArgumentException("url不能为空!");
    }

    if (StringUtils.isBlank(username)) {
      throw new IllegalArgumentException("用户名不能为空!");
    }

    if (StringUtils.isBlank(password)) {
      throw new IllegalArgumentException("密码不能为空!");
    }

    JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
    jdbcConnectionConfiguration.setDriverClass(driver);
    jdbcConnectionConfiguration.setConnectionURL(url);
    jdbcConnectionConfiguration.setUserId(username);
    jdbcConnectionConfiguration.setPassword(password);

    context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
  }

  private void setCommentGeneratorConfiguration(Context context, GenByJavaDto dto) {
    CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
    commentGeneratorConfiguration.addProperty("suppressAllComments", "true");
    commentGeneratorConfiguration.addProperty("suppressDate", "true");
    context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);
  }

  private void addPluginConfiguration(Context context, GenByJavaDto dto) {
    boolean enableToString = dto.getBtnEnableToString().getSelection();
    if (enableToString) {
      PluginConfiguration toStringPluginConfig = new PluginConfiguration();
      toStringPluginConfig.setConfigurationType("com.universe.mbg.plugin.CommonsLangToStringPlugin");
      context.addPluginConfiguration(toStringPluginConfig);
    }

    boolean enableCamelCase = dto.getBtnEnableCamelCase().getSelection();
    if (enableCamelCase) {
      String removedTablePrefix = StringUtils.trim(dto.getTxRemovedTablePrefix().getText());
      String domainObjectNameSuffix = StringUtils.trim(dto.getTxClassSuffix().getText());
      String mapperSuffix = StringUtils.trim(dto.getTxMapperSuffix().getText());

      PluginConfiguration underscoreToCamelCasePluginConfig = new PluginConfiguration();
      underscoreToCamelCasePluginConfig.setConfigurationType("com.universe.mbg.plugin.UnderscoreToCamelCaseRenamingPlugin");
      underscoreToCamelCasePluginConfig.addProperty("removedTablePrefix", removedTablePrefix);
      underscoreToCamelCasePluginConfig.addProperty("domainObjectNameSuffix", domainObjectNameSuffix);
      underscoreToCamelCasePluginConfig.addProperty("mapperSuffix", mapperSuffix);
      context.addPluginConfiguration(underscoreToCamelCasePluginConfig);
    }
  }

  private Context buildContext() {
    Context context = new Context(ModelType.CONDITIONAL);
    context.setId("autoGeneration");
    context.setTargetRuntime("MyBatis3");
    return context;
  }

}
