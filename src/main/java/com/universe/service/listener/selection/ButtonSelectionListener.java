package com.universe.service.listener.selection;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import com.universe.common.constant.SystemConsts;
import com.universe.service.chooser.SelectionHandlerChooser;
import com.universe.service.chooser.impl.ButtonSelectionHandlerChooser;
import com.universe.service.hanlder.ButtonSelectionHandler;

public class ButtonSelectionListener extends SelectionAdapter {

  private static final SelectionHandlerChooser HANDLER_CHOOSER = new ButtonSelectionHandlerChooser();

  private Text txFilePath;
  private StyledText txFileContent;

  public ButtonSelectionListener() {
  }

  public ButtonSelectionListener(StyledText txFileContent) {
    this.txFileContent = txFileContent;
  }

  public ButtonSelectionListener(Text txFilePath, StyledText txFileContent) {
    this.txFilePath = txFilePath;
    this.txFileContent = txFileContent;
  }

  @Override
  public void widgetSelected(SelectionEvent e) {
    Button button = (Button) e.widget;
    String btnType = (String) button.getData(SystemConsts.BUTTON_TYPE);

    ButtonSelectionHandler hanlder = (ButtonSelectionHandler) HANDLER_CHOOSER.chooseSelectionHandler(btnType);
    hanlder.onButtonSelected(txFilePath, txFileContent);
  }

}
