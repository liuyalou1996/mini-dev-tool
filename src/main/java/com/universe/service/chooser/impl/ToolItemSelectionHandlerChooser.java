package com.universe.service.chooser.impl;

import java.util.HashMap;
import java.util.Map;

import com.universe.common.constant.ToolItemTypeConsts;
import com.universe.service.chooser.SelectionHandlerChooser;
import com.universe.service.hanlder.SelectionHandler;
import com.universe.service.hanlder.ToolItemSelectionHandler;
import com.universe.service.hanlder.impl.json.CompressJsonStrHandlerImpl;
import com.universe.service.hanlder.impl.json.EliminateMeaningHandlerImpl;
import com.universe.service.hanlder.impl.json.JsonToBeanHandlerImpl;
import com.universe.service.hanlder.impl.json.TransferMeaningHanlderImpl;
import com.universe.service.hanlder.impl.json.VerifyJsonFormatHandlerImpl;

public class ToolItemSelectionHandlerChooser implements SelectionHandlerChooser {

  private static Map<String, ToolItemSelectionHandler> handlerMap = new HashMap<>();

  static {
    handlerMap.put(ToolItemTypeConsts.VERIFY_JSON_FORMAT, new VerifyJsonFormatHandlerImpl());
    handlerMap.put(ToolItemTypeConsts.COMPRESS_JSONSTR, new CompressJsonStrHandlerImpl());
    handlerMap.put(ToolItemTypeConsts.TRANSFER_MEANING, new TransferMeaningHanlderImpl());
    handlerMap.put(ToolItemTypeConsts.ELIMINATE_MEANING, new EliminateMeaningHandlerImpl());
    handlerMap.put(ToolItemTypeConsts.JSON_TO_BEAN, new JsonToBeanHandlerImpl());
  }

  @Override
  public SelectionHandler chooseSelectionHandler(String toolItemType) {
    return handlerMap.get(toolItemType);
  }
}
