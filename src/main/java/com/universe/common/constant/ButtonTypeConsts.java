package com.universe.common.constant;

public interface ButtonTypeConsts {

  interface GenByXml {

    /**
     * 选择配置文件
     */
    String CHOOSE_CONFIG_FILE = "1";

    /**
     * 根据xml自动生成
     */
    String AUTO_GENERATE_BY_XML = "2";

    /**
     * 下载配置模板
     */
    String DOWNLOAD_CONFIG_TEMPLATE = "3";

    /**
     * 导出配置
     */
    String EXPORT_CONFIG = "4";
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

  }

}
