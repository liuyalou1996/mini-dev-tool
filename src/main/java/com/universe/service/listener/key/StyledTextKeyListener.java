package com.universe.service.listener.key;

import java.util.ArrayDeque;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import com.universe.common.util.CollectionUtils;

public class StyledTextKeyListener extends KeyAdapter {

  private ArrayDeque<String> arrayDeque;

  public StyledTextKeyListener(ArrayDeque<String> arrayDeque) {
    this.arrayDeque = arrayDeque;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    StyledText styledText = (StyledText) e.widget;
    // ctrl+a全选
    if (e.stateMask == SWT.CTRL && e.keyCode == 97) {
      styledText.selectAll();
    }

    // ctrl+z撤销
    if (e.stateMask == SWT.CTRL && e.keyCode == 122) {
      if (CollectionUtils.isNotEmpty(arrayDeque)) {
        String str = arrayDeque.pop();
        styledText.setText(str);
        styledText.setSelection(str.length());
      }
    }
  }

}
