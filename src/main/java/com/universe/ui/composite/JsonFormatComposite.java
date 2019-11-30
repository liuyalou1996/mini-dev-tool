package com.universe.ui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class JsonFormatComposite extends Composite {

  public JsonFormatComposite(Composite parent) {
    super(parent, SWT.NONE);
    setLayout(new FillLayout(SWT.HORIZONTAL));

    SashForm sashForm = new SashForm(this, SWT.VERTICAL);

    // 初始化文本框
    initTextBox(sashForm);
    // 初始化工具条
    initToolBar(sashForm);
  }

  private void initTextBox(SashForm sashForm) {
    Composite compTop = new Composite(sashForm, SWT.H_SCROLL | SWT.V_SCROLL);
    compTop.setLayout(new FillLayout(SWT.HORIZONTAL));
    new Text(compTop, SWT.BORDER | SWT.WRAP);
  }

  private void initToolBar(SashForm sashForm) {
    Composite compDown = new Composite(sashForm, SWT.BORDER);
    compDown.setLayout(new FillLayout(SWT.HORIZONTAL));

    ToolBar toolBar = new ToolBar(compDown, SWT.FLAT | SWT.WRAP);

    ToolItem tiVerify = new ToolItem(toolBar, SWT.NONE);
    tiVerify.setToolTipText("校验是否是json格式");
    tiVerify.setText("格式化校验");

    new ToolItem(toolBar, SWT.SEPARATOR);

    ToolItem tiCompress = new ToolItem(toolBar, SWT.NONE);
    tiCompress.setToolTipText("压缩json字符串");
    tiCompress.setText("压缩");

    new ToolItem(toolBar, SWT.SEPARATOR);

    ToolItem tiTransferMeaning = new ToolItem(toolBar, SWT.NONE);
    tiTransferMeaning.setToolTipText("在json字符串添加转义字符");
    tiTransferMeaning.setText("转义");

    new ToolItem(toolBar, SWT.SEPARATOR);

    ToolItem tiUnTranserMeaning = new ToolItem(toolBar, SWT.NONE);
    tiUnTranserMeaning.setToolTipText("去除json字符串中的转义字符");
    tiUnTranserMeaning.setText("去除转义");

    sashForm.setWeights(new int[] { 16, 1 });
  }

  @Override
  protected void checkSubclass() {

  }
}
