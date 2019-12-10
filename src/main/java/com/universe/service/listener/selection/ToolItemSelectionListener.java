package com.universe.service.listener.selection;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolItem;

import com.universe.constant.SystemConsts;
import com.universe.service.chooser.ToolItemSelectionHandlerChooser;
import com.universe.service.hanlder.ToolItemSelectionHandler;

/**
 * 工具条选择监听器
 * @author liuyalou
 * @date 2019年12月1日
 */
public class ToolItemSelectionListener extends SelectionAdapter {

  private static final ToolItemSelectionHandlerChooser HANLDER_CHOOSER = new ToolItemSelectionHandlerChooser();

  private StyledText text;

  public ToolItemSelectionListener(StyledText text) {
    this.text = text;
  }

  @Override
  public void widgetSelected(SelectionEvent e) {
    ToolItem toolItem = (ToolItem) e.widget;
    String toolItemType = (String) toolItem.getData(SystemConsts.TOOL_ITEM_TYPE);

    ToolItemSelectionHandler handler = HANLDER_CHOOSER.chooseSelectionHandler(toolItemType);
    handler.onToolItemSelected(text);
  }

}
