package com.universe.ui.composite.json;

import java.util.ArrayDeque;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.universe.common.constant.SystemConsts;
import com.universe.common.constant.ToolItemTypeConsts;
import com.universe.service.listener.key.StyledTextKeyListener;
import com.universe.service.listener.key.StyledTextVerifyKeyListener;
import com.universe.service.listener.modify.JsonFormatExtendedModifyListener;
import com.universe.service.listener.selection.JsonFormatToolItemSelectionListener;

public class JsonFormatComposite extends Composite {

  private ArrayDeque<String> arrayDeque = new ArrayDeque<>();

  private Composite compTop;
  private Composite compDown;
  private SashForm sashForm;
  private ToolBar toolBar;
  private StyledText styledText;

  public JsonFormatComposite(Composite parent) {
    super(parent, SWT.NONE);
    setLayout(new FillLayout(SWT.HORIZONTAL));

    sashForm = new SashForm(this, SWT.VERTICAL);

    // 初始化文本框
    initTextBox(sashForm);
    // 初始化工具条
    initToolBar(sashForm);
  }

  private void initTextBox(SashForm sashForm) {
    compTop = new Composite(sashForm, SWT.NONE);
    compTop.setLayout(new FillLayout(SWT.HORIZONTAL));

    styledText = new StyledText(compTop, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);

    // 快捷键
    styledText.addKeyListener(new StyledTextKeyListener(arrayDeque));
    // 全选撤销
    styledText.addVerifyKeyListener(new StyledTextVerifyKeyListener(arrayDeque));
    // 关键字符着色
    styledText.addExtendedModifyListener(new JsonFormatExtendedModifyListener());

  }

  private void initToolBar(SashForm sashForm) {
    compDown = new Composite(sashForm, SWT.BORDER);
    compDown.setLayout(new FillLayout(SWT.HORIZONTAL));

    toolBar = new ToolBar(compDown, SWT.FLAT | SWT.WRAP);

    JsonFormatToolItemSelectionListener listener = new JsonFormatToolItemSelectionListener(styledText);

    ToolItem tiVerify = new ToolItem(toolBar, SWT.NONE);
    tiVerify.setToolTipText("校验是否是json格式");
    tiVerify.setText("格式化校验");
    tiVerify.setData(SystemConsts.TOOL_ITEM_TYPE, ToolItemTypeConsts.VERIFY_JSON_FORMAT);
    tiVerify.addSelectionListener(listener);

    new ToolItem(toolBar, SWT.SEPARATOR);

    ToolItem tiCompress = new ToolItem(toolBar, SWT.NONE);
    tiCompress.setToolTipText("压缩json字符串");
    tiCompress.setText("压缩");
    tiCompress.setData(SystemConsts.TOOL_ITEM_TYPE, ToolItemTypeConsts.COMPRESS_JSONSTR);
    tiCompress.addSelectionListener(listener);

    new ToolItem(toolBar, SWT.SEPARATOR);

    ToolItem tiTransferMeaning = new ToolItem(toolBar, SWT.NONE);
    tiTransferMeaning.setToolTipText("在json字符串添加转义字符");
    tiTransferMeaning.setText("转义");
    tiTransferMeaning.setData(SystemConsts.TOOL_ITEM_TYPE, ToolItemTypeConsts.TRANSFER_MEANING);
    tiTransferMeaning.addSelectionListener(listener);

    new ToolItem(toolBar, SWT.SEPARATOR);

    ToolItem tiEliminateMeaning = new ToolItem(toolBar, SWT.NONE);
    tiEliminateMeaning.setToolTipText("去除json字符串中的转义字符");
    tiEliminateMeaning.setText("去除转义");
    tiEliminateMeaning.setData(SystemConsts.TOOL_ITEM_TYPE, ToolItemTypeConsts.ELIMINATE_MEANING);
    tiEliminateMeaning.addSelectionListener(listener);

    new ToolItem(toolBar, SWT.SEPARATOR);

    ToolItem tiJsonToBean = new ToolItem(toolBar, SWT.NONE);
    tiJsonToBean.setText("Json转Bean");
    tiJsonToBean.setToolTipText("将json字符串转成java bean");
    tiJsonToBean.setData(SystemConsts.TOOL_ITEM_TYPE, ToolItemTypeConsts.JSON_TO_BEAN);
    tiJsonToBean.addSelectionListener(listener);

    sashForm.setWeights(new int[] { 16, 1 });
  }

  @Override
  protected void checkSubclass() {

  }
}
