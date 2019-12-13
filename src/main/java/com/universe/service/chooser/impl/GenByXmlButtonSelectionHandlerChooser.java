package com.universe.service.chooser.impl;

import java.util.HashMap;
import java.util.Map;

import com.universe.common.constant.ButtonTypeConsts;
import com.universe.service.chooser.SelectionHandlerChooser;
import com.universe.service.hanlder.GenByXmlButtonSelectionHandler;
import com.universe.service.hanlder.impl.autogen.xml.ChooseConfigFileHandlerImpl;
import com.universe.service.hanlder.impl.autogen.xml.DownloadConfigTemplateHandlerImpl;
import com.universe.service.hanlder.impl.autogen.xml.ExportConfigHandlerImpl;
import com.universe.service.hanlder.impl.autogen.xml.GenByXmlHanlderImpl;

public class GenByXmlButtonSelectionHandlerChooser implements SelectionHandlerChooser<GenByXmlButtonSelectionHandler> {

  private static Map<String, GenByXmlButtonSelectionHandler> handlerMap = new HashMap<>();

  static {
    handlerMap.put(ButtonTypeConsts.GenByXml.CHOOSE_CONFIG_FILE, new ChooseConfigFileHandlerImpl());
    handlerMap.put(ButtonTypeConsts.GenByXml.AUTO_GENERATE_BY_XML, new GenByXmlHanlderImpl());
    handlerMap.put(ButtonTypeConsts.GenByXml.DOWNLOAD_CONFIG_TEMPLATE, new DownloadConfigTemplateHandlerImpl());
    handlerMap.put(ButtonTypeConsts.GenByXml.EXPORT_CONFIG, new ExportConfigHandlerImpl());
  }

  @Override
  public GenByXmlButtonSelectionHandler chooseSelectionHandler(String buttonType) {
    return handlerMap.get(buttonType);
  }
}
