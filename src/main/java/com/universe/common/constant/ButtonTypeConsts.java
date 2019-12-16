package com.universe.common.constant;

public interface ButtonTypeConsts {

  interface GenByXml {

    /**
     * 导入xml配置
     */
    String IMPORT_XML_CONFIG = "1";

    /**
     * 根据xml自动生成
     */
    String AUTO_GENERATE_BY_XML = "2";

    /**
     * 下载配置模板
     */
    String DOWNLOAD_XML_CONFIG_TEMPLATE = "3";

    /**
     * 导出xml配置
     */
    String EXPORT_XML_CONFIG = "4";
  }

  interface GenByJava {

    /**
     * 选择jdbc驱动包
     */
    String CHOOSE_DRIVER = "5";

    /**
     * 选择项目路径
     */
    String CHOOSE_TARGET_PROJECT = "6";

    /**
     * 根据自定义配置生成
     */
    String AUTO_GENERATE_BY_JAVA = "7";

    /**
     * 导入配置
     */
    String IMPORT_JSON_COFIG = "8";

    /**
     * 导出配置
     */
    String EXPORT_JSON_CONFIG = "9";

  }

}
