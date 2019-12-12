package com.universe.service.chooser.impl;

import java.util.HashMap;
import java.util.Map;

import com.universe.common.constant.ButtonTypeConsts;
import com.universe.service.chooser.SelectionHandlerChooser;
import com.universe.service.hanlder.ButtonSelectionHandler;
import com.universe.service.hanlder.impl.autogen.AutoGenerateCodeHanlderImpl;
import com.universe.service.hanlder.impl.autogen.ChooseConfigFileHandlerImpl;
import com.universe.service.hanlder.impl.autogen.DownloadConfigTemplateHandlerImpl;
import com.universe.service.hanlder.impl.autogen.ExportConfigHandlerImpl;

public class ButtonSelectionHandlerChooser implements SelectionHandlerChooser {

  private static Map<String, ButtonSelectionHandler> handlerMap = new HashMap<>();

  static {
    handlerMap.put(ButtonTypeConsts.CHOOSE_CONFIG_FILE, new ChooseConfigFileHandlerImpl());
    handlerMap.put(ButtonTypeConsts.AUTO_GENERATE_CODE, new AutoGenerateCodeHanlderImpl());
    handlerMap.put(ButtonTypeConsts.DOWNLOAD_CONFIG_TEMPLATE, new DownloadConfigTemplateHandlerImpl());
    handlerMap.put(ButtonTypeConsts.EXPORT_CONFIG, new ExportConfigHandlerImpl());
  }

  @Override
  public ButtonSelectionHandler chooseSelectionHandler(String buttonType) {
    return handlerMap.get(buttonType);
  }
}
