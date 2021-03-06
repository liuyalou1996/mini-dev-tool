package com.universe.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * @author 刘亚楼
 * @date 2019/11/4
 */
public class GenerationExample {

  public static void generateByXml() throws Exception {
    List<String> warnings = new ArrayList<String>();

    String path = GenerationExample.class.getResource("/").getFile();
    File configFile = new File(path, "mybatis-generator-template.xml");
    ConfigurationParser cp = new ConfigurationParser(warnings);
    Configuration config = cp.parseConfiguration(configFile);
    DefaultShellCallback callback = new DefaultShellCallback(true);
    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
    myBatisGenerator.generate(null);
  }

  public static void generateByJava() throws Exception {
    String userHome = System.getProperty("user.home");
    String projectPath = userHome + File.separator + "test" + File.separator + "minidevtool" + File.separator;
    List<String> warnings = new ArrayList<String>();

    Context context = new Context(ModelType.CONDITIONAL);
    context.setId("autoGeneration");
    context.setTargetRuntime("MyBatis3");

    PluginConfiguration pluginConfiguration = new PluginConfiguration();
    pluginConfiguration.setConfigurationType("com.universe.mbg.plugin.UnderscoreToCamelCaseRenamingPlugin");
    pluginConfiguration.addProperty("domainObjectNameSuffix", "Do");
    pluginConfiguration.addProperty("removedTablePrefix", "tbl");
    pluginConfiguration.addProperty("mapperSuffix", "Mapper");

    PluginConfiguration toStringPluginConfig = new PluginConfiguration();
    toStringPluginConfig.setConfigurationType("com.universe.mbg.plugin.CommonsLangToStringPlugin");

    CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
    commentGeneratorConfiguration.addProperty("suppressAllComments", "true");
    commentGeneratorConfiguration.addProperty("suppressDate", "true");

    JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
    jdbcConnectionConfiguration.setDriverClass("com.mysql.cj.jdbc.Driver");
    jdbcConnectionConfiguration.setConnectionURL("jdbc:mysql://localhost:3306/shiro?serverTimezone=UTC");
    jdbcConnectionConfiguration.setUserId("root");
    jdbcConnectionConfiguration.setPassword("Lyl14786632348");

    JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
    javaTypeResolverConfiguration.addProperty("forceBigDecimals", "false");

    JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
    javaModelGeneratorConfiguration.setTargetProject(projectPath + "src\\main\\java");
    javaModelGeneratorConfiguration.setTargetPackage("com.universe.pojo.domain");
    javaModelGeneratorConfiguration.addProperty("enableSubPackages", "false");

    SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
    sqlMapGeneratorConfiguration.setTargetProject(projectPath + "src\\main\\resources");
    sqlMapGeneratorConfiguration.setTargetPackage("mapper");

    JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
    javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
    javaClientGeneratorConfiguration.setTargetProject(projectPath + "src\\main\\java");
    javaClientGeneratorConfiguration.setTargetPackage("com.universe.mapper");
    javaClientGeneratorConfiguration.addProperty("enableSubPackages", "true");

    TableConfiguration tableConfiguration = new TableConfiguration(context);
    tableConfiguration.setTableName("%");
    tableConfiguration.setSchema("shiro");
    tableConfiguration.setCatalog("shiro");
    tableConfiguration.setCountByExampleStatementEnabled(false);
    tableConfiguration.setDeleteByExampleStatementEnabled(false);
    tableConfiguration.setSelectByExampleStatementEnabled(false);
    tableConfiguration.setUpdateByExampleStatementEnabled(false);
    tableConfiguration.addProperty("ignoreQualifiersAtRuntime", "true");

    context.addPluginConfiguration(pluginConfiguration);
    context.addPluginConfiguration(toStringPluginConfig);
    context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);
    context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
    context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
    context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
    context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
    context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
    context.addTableConfiguration(tableConfiguration);

    Configuration config = new Configuration();
    config.addClasspathEntry("D:\\MavenRepository\\mysql\\mysql-connector-java\\8.0.17\\mysql-connector-java-8.0.17.jar");
    config.addContext(context);
    DefaultShellCallback callback = new DefaultShellCallback(true);
    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
    myBatisGenerator.generate(null);
  }

  public static void main(String[] args) throws Exception {
    generateByJava();
  }

}
