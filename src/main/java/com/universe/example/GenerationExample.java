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
    System.err.println(configFile);
  }

  public static void generateByCode() throws Exception {
    List<String> warnings = new ArrayList<String>();

    Context context = new Context(ModelType.CONDITIONAL);
    context.setId("autoGeneration");
    context.setTargetRuntime("MyBatis3");

    PluginConfiguration pluginConfiguration = new PluginConfiguration();
    pluginConfiguration.setConfigurationType("com.universe.common.plugin.UnderscoreToCamelCaseRenamingPlugin");

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
    javaModelGeneratorConfiguration.setTargetProject("C:\\Users\\liuyalou\\test\\minidevtool\\src\\main\\java");
    javaModelGeneratorConfiguration.setTargetPackage("com.universe.pojo.domain");
    javaModelGeneratorConfiguration.addProperty("enableSubPackages", "false");

    SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
    sqlMapGeneratorConfiguration.setTargetProject("C:\\Users\\liuyalou\\test\\minidevtool\\src\\main\\resources");
    sqlMapGeneratorConfiguration.setTargetPackage("mapper");

    JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
    javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
    javaClientGeneratorConfiguration.setTargetProject("C:\\Users\\liuyalou\\test\\minidevtool\\src\\main\\java");
    javaClientGeneratorConfiguration.setTargetPackage("com.universe.mapper");
    javaClientGeneratorConfiguration.addProperty("enableSubPackages", "true");

    TableConfiguration tableConfiguration = new TableConfiguration(context);
    tableConfiguration.setTableName("tbl_user");
    // tableConfiguration.setDomainObjectName("UserDo");
    // tableConfiguration.setMapperName("UserMapper");
    tableConfiguration.setCountByExampleStatementEnabled(false);
    tableConfiguration.setDeleteByExampleStatementEnabled(false);
    tableConfiguration.setSelectByExampleStatementEnabled(false);
    tableConfiguration.setUpdateByExampleStatementEnabled(false);

    context.addPluginConfiguration(pluginConfiguration);
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
    generateByCode();
  }

}
