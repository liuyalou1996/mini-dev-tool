package com.universe.service.chooser.impl;

import java.util.HashMap;
import java.util.Map;

import com.universe.common.constant.ToolItemTypeConsts;
import com.universe.service.chooser.SelectionHandlerChooser;
import com.universe.service.hanlder.JsonFormatToolItemSelectionHandler;
import com.universe.service.hanlder.impl.json.CompressJsonStrHandlerImpl;
import com.universe.service.hanlder.impl.json.EliminateMeaningHandlerImpl;
import com.universe.service.hanlder.impl.json.JsonToBeanHandlerImpl;
import com.universe.service.hanlder.impl.json.TransferMeaningHanlderImpl;
import com.universe.service.hanlder.impl.json.VerifyJsonFormatHandlerImpl;

public class JsonFormatToolItemSelectionHandlerChooser implements SelectionHandlerChooser<JsonFormatToolItemSelectionHandler> {

  private static Map<String, JsonFormatToolItemSelectionHandler> handlerMap = new HashMap<>();

  static {
    handlerMap.put(ToolItemTypeConsts.VERIFY_JSON_FORMAT, new VerifyJsonFormatHandlerImpl());
    handlerMap.put(ToolItemTypeConsts.COMPRESS_JSONSTR, new CompressJsonStrHandlerImpl());
    handlerMap.put(ToolItemTypeConsts.TRANSFER_MEANING, new TransferMeaningHanlderImpl());
    handlerMap.put(ToolItemTypeConsts.ELIMINATE_MEANING, new EliminateMeaningHandlerImpl());
    handlerMap.put(ToolItemTypeConsts.JSON_TO_BEAN, new JsonToBeanHandlerImpl());
  }

  @Override
  public JsonFormatToolItemSelectionHandler chooseSelectionHandler(String toolItemType) {
    return handlerMap.get(toolItemType);
  }
}
