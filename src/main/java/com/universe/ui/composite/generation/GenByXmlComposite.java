package com.universe.ui.composite.generation;

import java.util.ArrayDeque;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.universe.common.constant.ButtonTypeConsts;
import com.universe.common.constant.SystemConsts;
import com.universe.service.listener.key.StyledTextKeyListener;
import com.universe.service.listener.key.StyledTextVerifyKeyListener;
import com.universe.service.listener.modify.GenerateCodeModifyListener;
import com.universe.service.listener.selection.ButtonSelectionListener;

public class GenByXmlComposite extends Composite {

  private Text txFilePath;
  private StyledText txFileContent;
  private Button btnChooseConfigFile;
  private Button btnGen;
  private Button btnChooseTemplate;

  private ArrayDeque<String> arrayDeque = new ArrayDeque<>();

  public GenByXmlComposite(Composite parent) {
    super(parent, SWT.NONE);

    super.setLayout(new FillLayout(SWT.HORIZONTAL));

    SashForm sashForm = new SashForm(this, SWT.VERTICAL);

    Composite compUp = new Composite(sashForm, SWT.BORDER);

    txFilePath = new Text(compUp, SWT.BORDER);
    txFilePath.setBounds(10, 10, 392, 23);

    btnChooseConfigFile = new Button(compUp, SWT.NONE);
    btnChooseConfigFile.setLocation(431, 8);
    btnChooseConfigFile.setSize(105, 26);
    btnChooseConfigFile.setText("请选择配置文件");
    btnChooseConfigFile.setData(SystemConsts.BUTTON_TYPE, ButtonTypeConsts.CHOOSE_CONFIG_FILE);

    btnGen = new Button(compUp, SWT.NONE);
    btnGen.setLocation(558, 8);
    btnGen.setSize(69, 27);
    btnGen.setText("生成代码");
    btnGen.setData(SystemConsts.BUTTON_TYPE, ButtonTypeConsts.AUTO_GENERATE_CODE);

    btnChooseTemplate = new Button(compUp, SWT.NONE);
    btnChooseTemplate.setBounds(731, 8, 80, 27);
    btnChooseTemplate.setText("下载配置模板");
    btnChooseTemplate.setData(SystemConsts.BUTTON_TYPE, ButtonTypeConsts.DOWNLOAD_CONFIG_TEMPLATE);

    Composite compDown = new Composite(sashForm, SWT.NONE);
    compDown.setLayout(new FillLayout(SWT.HORIZONTAL));

    txFileContent = new StyledText(compDown, SWT.BORDER | SWT.FULL_SELECTION | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);
    txFileContent.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_BLUE));
    // 快捷键
    txFileContent.addKeyListener(new StyledTextKeyListener(arrayDeque));
    // 全选撤销
    txFileContent.addVerifyKeyListener(new StyledTextVerifyKeyListener(arrayDeque));
    // 关键字着色
    txFileContent.addExtendedModifyListener(new GenerateCodeModifyListener());

    // 按钮添加选择监听器
    btnChooseConfigFile.addSelectionListener(new ButtonSelectionListener(txFilePath, txFileContent));
    btnGen.addSelectionListener(new ButtonSelectionListener(txFileContent));
    btnChooseTemplate.addSelectionListener(new ButtonSelectionListener());

    sashForm.setWeights(new int[] { 40, 479 });
  }

  @Override
  protected void checkSubclass() {

  }
}
