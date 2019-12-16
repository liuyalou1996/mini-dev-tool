package com.universe.service.chooser.impl;

import java.util.HashMap;
import java.util.Map;

import com.universe.common.constant.ButtonTypeConsts;
import com.universe.service.chooser.SelectionHandlerChooser;
import com.universe.service.hanlder.GenByXmlButtonSelectionHandler;
import com.universe.service.hanlder.impl.autogen.xml.ImportXmlConfigHandlerImpl;
import com.universe.service.hanlder.impl.autogen.xml.DownloadXmlConfigTemplateHandlerImpl;
import com.universe.service.hanlder.impl.autogen.xml.ExportXmlConfigHandlerImpl;
import com.universe.service.hanlder.impl.autogen.xml.GenByXmlHanlderImpl;

public class GenByXmlButtonSelectionHandlerChooser implements SelectionHandlerChooser<GenByXmlButtonSelectionHandler> {

  private static Map<String, GenByXmlButtonSelectionHandler> handlerMap = new HashMap<>();

  static {
    handlerMap.put(ButtonTypeConsts.GenByXml.IMPORT_XML_CONFIG, new ImportXmlConfigHandlerImpl());
    handlerMap.put(ButtonTypeConsts.GenByXml.AUTO_GENERATE_BY_XML, new GenByXmlHanlderImpl());
    handlerMap.put(ButtonTypeConsts.GenByXml.DOWNLOAD_XML_CONFIG_TEMPLATE, new DownloadXmlConfigTemplateHandlerImpl());
    handlerMap.put(ButtonTypeConsts.GenByXml.EXPORT_XML_CONFIG, new ExportXmlConfigHandlerImpl());
  }

  @Override
  public GenByXmlButtonSelectionHandler chooseSelectionHandler(String buttonType) {
    return handlerMap.get(buttonType);
  }
}
