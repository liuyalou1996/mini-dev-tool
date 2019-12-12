package com.universe.service.hanlder;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Text;

public interface ButtonSelectionHandler extends SelectionHandler {

  void onButtonSelected(Text txFilePath, StyledText txFileContent);
}
