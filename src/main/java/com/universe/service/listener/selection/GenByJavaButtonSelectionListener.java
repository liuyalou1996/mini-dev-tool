package com.universe.service.listener.selection;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

import com.universe.common.constant.SystemConsts;
import com.universe.pojo.dto.GenByJavaDto;
import com.universe.service.chooser.SelectionHandlerChooser;
import com.universe.service.chooser.impl.GenByJavaButtonSelectionHandlerChooser;
import com.universe.service.hanlder.GenByJavaButtonSelectionHandler;

public class GenByJavaButtonSelectionListener extends SelectionAdapter {

  private static final SelectionHandlerChooser<GenByJavaButtonSelectionHandler> HANDLER_CHOOSER =
      new GenByJavaButtonSelectionHandlerChooser();

  private GenByJavaDto dto;

  public GenByJavaButtonSelectionListener(GenByJavaDto dto) {
    this.dto = dto;
  }

  @Override
  public void widgetSelected(SelectionEvent e) {
    Button button = (Button) e.widget;
    String buttonType = (String) button.getData(SystemConsts.BUTTON_TYPE);

    GenByJavaButtonSelectionHandler hanlder = HANDLER_CHOOSER.chooseSelectionHandler(buttonType);
    hanlder.onButtonSelected(dto);
  }

}
