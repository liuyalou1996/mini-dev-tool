package com.universe.service.chooser;

import java.util.HashMap;
import java.util.Map;

import com.universe.common.constant.ToolItemTypeConsts;
import com.universe.service.hanlder.ToolItemSelectionHandler;
import com.universe.service.hanlder.impl.CompressJsonStrHandlerImpl;
import com.universe.service.hanlder.impl.EliminateMeaningHandlerImpl;
import com.universe.service.hanlder.impl.JsonToBeanHandlerImpl;
import com.universe.service.hanlder.impl.TransferMeaningHanlderImpl;
import com.universe.service.hanlder.impl.VerifyJsonFormatHandlerImpl;

public class ToolItemSelectionHandlerChooser {

  private static Map<String, ToolItemSelectionHandler> handlerMap = new HashMap<>();

  static {
    handlerMap.put(ToolItemTypeConsts.VERIFY_JSON_FORMAT, new VerifyJsonFormatHandlerImpl());
    handlerMap.put(ToolItemTypeConsts.COMPRESS_JSONSTR, new CompressJsonStrHandlerImpl());
    handlerMap.put(ToolItemTypeConsts.TRANSFER_MEANING, new TransferMeaningHanlderImpl());
    handlerMap.put(ToolItemTypeConsts.ELIMINATE_MEANING, new EliminateMeaningHandlerImpl());
    handlerMap.put(ToolItemTypeConsts.JSON_TO_BEAN, new JsonToBeanHandlerImpl());
  }

  public ToolItemSelectionHandler chooseSelectionHandler(String toolItemType) {
    return handlerMap.get(toolItemType);
  }
}
