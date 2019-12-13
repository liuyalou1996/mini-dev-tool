package com.universe.service.listener.selection;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import com.universe.common.constant.SystemConsts;
import com.universe.service.chooser.SelectionHandlerChooser;
import com.universe.service.chooser.impl.GenByXmlButtonSelectionHandlerChooser;
import com.universe.service.hanlder.GenByXmlButtonSelectionHandler;

public class GenByXmlButtonSelectionListener extends SelectionAdapter {

  private static final SelectionHandlerChooser<GenByXmlButtonSelectionHandler> HANDLER_CHOOSER =
      new GenByXmlButtonSelectionHandlerChooser();

  private Text txFilePath;
  private StyledText txFileContent;

  public GenByXmlButtonSelectionListener(Text txFilePath, StyledText txFileContent) {
    this.txFilePath = txFilePath;
    this.txFileContent = txFileContent;
  }

  @Override
  public void widgetSelected(SelectionEvent e) {
    Button button = (Button) e.widget;
    String btnType = (String) button.getData(SystemConsts.BUTTON_TYPE);

    GenByXmlButtonSelectionHandler hanlder = HANDLER_CHOOSER.chooseSelectionHandler(btnType);
    hanlder.onButtonSelected(txFilePath, txFileContent);
  }

}
