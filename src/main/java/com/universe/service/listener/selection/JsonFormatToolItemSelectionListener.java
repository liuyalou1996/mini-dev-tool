package com.universe.service.listener.selection;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolItem;

import com.universe.common.constant.SystemConsts;
import com.universe.service.chooser.SelectionHandlerChooser;
import com.universe.service.chooser.impl.JsonFormatToolItemSelectionHandlerChooser;
import com.universe.service.hanlder.JsonFormatToolItemSelectionHandler;

/**
 * 工具条选择监听器
 * @author liuyalou
 * @date 2019年12月1日
 */
public class JsonFormatToolItemSelectionListener extends SelectionAdapter {

  private static final SelectionHandlerChooser<JsonFormatToolItemSelectionHandler> HANLDER_CHOOSER =
      new JsonFormatToolItemSelectionHandlerChooser();

  private StyledText text;

  public JsonFormatToolItemSelectionListener(StyledText text) {
    this.text = text;
  }

  @Override
  public void widgetSelected(SelectionEvent e) {
    ToolItem toolItem = (ToolItem) e.widget;
    String toolItemType = (String) toolItem.getData(SystemConsts.TOOL_ITEM_TYPE);

    JsonFormatToolItemSelectionHandler handler = HANLDER_CHOOSER.chooseSelectionHandler(toolItemType);
    handler.onToolItemSelected(text);
  }

}
