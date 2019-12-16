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
import com.universe.service.listener.modify.GenByXmlExtendedModifyListener;
import com.universe.service.listener.modify.StyledTextModifyListener;
import com.universe.service.listener.selection.GenByXmlButtonSelectionListener;

public class GenByXmlComposite extends Composite {

  private Text txFilePath;
  private StyledText txFileContent;
  private Button btnChooseConfigFile;
  private Button btnGen;
  private Button btnChooseTemplate;
  private Button btnExport;

  private ArrayDeque<String> arrayDeque = new ArrayDeque<>();

  public GenByXmlComposite(Composite parent) {
    super(parent, SWT.NONE);

    super.setLayout(new FillLayout(SWT.HORIZONTAL));

    SashForm sashForm = new SashForm(this, SWT.VERTICAL);

    Composite compUp = new Composite(sashForm, SWT.BORDER);

    txFilePath = new Text(compUp, SWT.BORDER | SWT.READ_ONLY);
    txFilePath.setBounds(10, 10, 392, 23);

    btnChooseConfigFile = new Button(compUp, SWT.NONE);
    btnChooseConfigFile.setLocation(431, 8);
    btnChooseConfigFile.setSize(105, 26);
    btnChooseConfigFile.setText("请选择配置文件");
    btnChooseConfigFile.setData(SystemConsts.BUTTON_TYPE, ButtonTypeConsts.GenByXml.IMPORT_XML_CONFIG);

    btnGen = new Button(compUp, SWT.NONE);
    btnGen.setLocation(549, 8);
    btnGen.setSize(69, 27);
    btnGen.setText("生成代码");
    btnGen.setData(SystemConsts.BUTTON_TYPE, ButtonTypeConsts.GenByXml.AUTO_GENERATE_BY_XML);

    btnChooseTemplate = new Button(compUp, SWT.NONE);
    btnChooseTemplate.setBounds(667, 8, 80, 27);
    btnChooseTemplate.setText("下载配置模板");
    btnChooseTemplate.setData(SystemConsts.BUTTON_TYPE, ButtonTypeConsts.GenByXml.DOWNLOAD_XML_CONFIG_TEMPLATE);

    btnExport = new Button(compUp, SWT.NONE);
    btnExport.setBounds(756, 8, 80, 27);
    btnExport.setText("导出配置");
    btnExport.setData(SystemConsts.BUTTON_TYPE, ButtonTypeConsts.GenByXml.EXPORT_XML_CONFIG);

    Composite compDown = new Composite(sashForm, SWT.NONE);
    compDown.setLayout(new FillLayout(SWT.HORIZONTAL));

    txFileContent = new StyledText(compDown, SWT.BORDER | SWT.FULL_SELECTION | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);
    txFileContent.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_BLUE));
    // 快捷键
    txFileContent.addKeyListener(new StyledTextKeyListener(arrayDeque));
    // 全选撤销
    txFileContent.addVerifyKeyListener(new StyledTextVerifyKeyListener(arrayDeque));
    // 关键字着色
    txFileContent.addExtendedModifyListener(new GenByXmlExtendedModifyListener());
    // 文本改变加*号
    txFileContent.addModifyListener(new StyledTextModifyListener(this));

    // 按钮添加选择监听器
    GenByXmlButtonSelectionListener selectionListener = new GenByXmlButtonSelectionListener(txFilePath, txFileContent);
    btnChooseConfigFile.addSelectionListener(selectionListener);
    btnGen.addSelectionListener(selectionListener);
    btnChooseTemplate.addSelectionListener(selectionListener);
    btnExport.addSelectionListener(selectionListener);

    sashForm.setWeights(new int[] { 40, 479 });
  }

  @Override
  protected void checkSubclass() {

  }
}
