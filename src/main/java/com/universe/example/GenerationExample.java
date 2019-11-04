package com.universe.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * @author 刘亚楼
 * @date 2019/11/4
 */
public class GenerationExample {

  public static void generate() throws Exception {
    List<String> warnings = new ArrayList<String>();

    String path = GenerationExample.class.getResource("/").getFile();
    File configFile = new File(path, "mybatis-generator.xml");
    ConfigurationParser cp = new ConfigurationParser(warnings);
    Configuration config = cp.parseConfiguration(configFile);
    DefaultShellCallback callback = new DefaultShellCallback(true);
    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
    myBatisGenerator.generate(null);
  }

  public static void main(String[] args) throws Exception {
    generate();
  }

}
