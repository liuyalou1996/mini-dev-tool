package com.universe.service.chooser.impl;

import java.util.HashMap;
import java.util.Map;

import com.universe.common.constant.ButtonTypeConsts;
import com.universe.service.chooser.SelectionHandlerChooser;
import com.universe.service.hanlder.GenByJavaButtonSelectionHandler;
import com.universe.service.hanlder.impl.autogen.java.ChooseDriverHanlderImpl;
import com.universe.service.hanlder.impl.autogen.java.ChooseTargetProjectHandlerImpl;
import com.universe.service.hanlder.impl.autogen.java.ExportJsonConfigHandlerImpl;
import com.universe.service.hanlder.impl.autogen.java.GenByJavaHandlerImpl;
import com.universe.service.hanlder.impl.autogen.java.ImportJsonConfigHandlerImpl;

public class GenByJavaButtonSelectionHandlerChooser implements SelectionHandlerChooser<GenByJavaButtonSelectionHandler> {

  private static Map<String, GenByJavaButtonSelectionHandler> handlerMap = new HashMap<>();

  static {
    handlerMap.put(ButtonTypeConsts.GenByJava.CHOOSE_DRIVER, new ChooseDriverHanlderImpl());
    handlerMap.put(ButtonTypeConsts.GenByJava.CHOOSE_TARGET_PROJECT, new ChooseTargetProjectHandlerImpl());
    handlerMap.put(ButtonTypeConsts.GenByJava.AUTO_GENERATE_BY_JAVA, new GenByJavaHandlerImpl());
    handlerMap.put(ButtonTypeConsts.GenByJava.IMPORT_JSON_COFIG, new ImportJsonConfigHandlerImpl());
    handlerMap.put(ButtonTypeConsts.GenByJava.EXPORT_JSON_CONFIG, new ExportJsonConfigHandlerImpl());
  }

  @Override
  public GenByJavaButtonSelectionHandler chooseSelectionHandler(String type) {
    return handlerMap.get(type);
  }

}
