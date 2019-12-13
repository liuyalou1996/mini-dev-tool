package com.universe.service.hanlder.impl.autogen.java;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.universe.common.util.DialogUtils;
import com.universe.pojo.dto.GenByJavaDto;
import com.universe.service.hanlder.GenByJavaButtonSelectionHandler;

public class ChooseTargetProjectHandlerImpl implements GenByJavaButtonSelectionHandler {

  @Override
  public void onButtonSelected(GenByJavaDto dto) {
    String targetProject = DialogUtils.showDirectoryDialog(Display.getCurrent().getActiveShell());
    if (StringUtils.isBlank(targetProject)) {
      return;
    }

    Text txTargetProject = dto.getTxTargetProject();
    txTargetProject.setText(targetProject);
  }
}
