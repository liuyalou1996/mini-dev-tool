package com.universe.service.listener.key;

import java.util.ArrayDeque;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;

public class StyledTextVerifyKeyListener implements VerifyKeyListener {

  private ArrayDeque<String> arrayDeque;

  public StyledTextVerifyKeyListener(ArrayDeque<String> arrayDeque) {
    this.arrayDeque = arrayDeque;
  }

  @Override
  public void verifyKey(VerifyEvent e) {
    StyledText styledText = (StyledText) e.widget;

    int keyCode = e.keyCode;
    if (keyCode == SWT.CTRL || keyCode == SWT.ALT || keyCode == SWT.SHIFT) {
      return;
    }

    // 实现简单撤销功能
    int bits = SWT.CTRL | SWT.ALT | SWT.SHIFT;
    if ((e.stateMask & bits) == 0) {
      String str = styledText.getText();
      arrayDeque.push(str);
    }
  }
}
