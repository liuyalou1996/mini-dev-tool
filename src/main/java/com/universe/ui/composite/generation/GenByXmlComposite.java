package com.universe.ui.composite.generation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class GenByXmlComposite extends Composite {

  private Text txFilePath;
  private Text txConfigFileContent;

  public GenByXmlComposite(Composite parent) {
    super(parent, SWT.NONE);
    setLayout(new FillLayout(SWT.HORIZONTAL));

    SashForm sashForm = new SashForm(this, SWT.VERTICAL);

    Composite compUp = new Composite(sashForm, SWT.BORDER);

    Button btnChooseConfigFile = new Button(compUp, SWT.NONE);
    btnChooseConfigFile.setLocation(353, 8);
    btnChooseConfigFile.setSize(105, 26);
    btnChooseConfigFile.setText("请选择配置文件");

    Button btnGen = new Button(compUp, SWT.NONE);
    btnGen.setLocation(472, 8);
    btnGen.setSize(105, 27);
    btnGen.setText("生成mybatis配置");

    Button btnChooseTemplate = new Button(compUp, SWT.NONE);
    btnChooseTemplate.setBounds(632, 8, 80, 27);
    btnChooseTemplate.setText("下载配置模板");

    txFilePath = new Text(compUp, SWT.BORDER);
    txFilePath.setBounds(10, 10, 329, 23);

    Composite compDown = new Composite(sashForm, SWT.NONE);
    compDown.setLayout(new FillLayout(SWT.HORIZONTAL));

    txConfigFileContent = new Text(compDown, SWT.NONE);
    sashForm.setWeights(new int[] { 44, 452 });

  }

  @Override
  protected void checkSubclass() {

  }
}
