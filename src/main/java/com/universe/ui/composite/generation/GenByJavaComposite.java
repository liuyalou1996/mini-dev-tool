package com.universe.ui.composite.generation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class GenByJavaComposite extends Composite {

  private Text txUrl;
  private Text txUsername;
  private Text txPassword;

  public GenByJavaComposite(Composite parent) {
    super(parent, SWT.NONE);
    setLayout(new FillLayout(SWT.HORIZONTAL));

    SashForm sashForm = new SashForm(this, SWT.VERTICAL);

    Composite compDb = new Composite(sashForm, SWT.BORDER);
    compDb.setLayout(new FillLayout(SWT.HORIZONTAL));

    Group gpDbConfig = new Group(compDb, SWT.NONE);
    gpDbConfig.setText("数据库连接配置");

    Label lbUrl = new Label(gpDbConfig, SWT.NONE);
    lbUrl.setBounds(398, 22, 71, 17);
    lbUrl.setText("请输入URL：");

    txUrl = new Text(gpDbConfig, SWT.BORDER);
    txUrl.setBounds(475, 19, 244, 23);

    Label lbUsername = new Label(gpDbConfig, SWT.NONE);
    lbUsername.setBounds(20, 57, 84, 17);
    lbUsername.setText("请输入用户名：");

    txUsername = new Text(gpDbConfig, SWT.BORDER);
    txUsername.setBounds(128, 54, 244, 23);

    Label lbPassword = new Label(gpDbConfig, SWT.NONE);
    lbPassword.setBounds(398, 57, 71, 17);
    lbPassword.setText("请输入密码：");

    txPassword = new Text(gpDbConfig, SWT.BORDER);
    txPassword.setBounds(475, 54, 244, 23);

    Label lbDriver = new Label(gpDbConfig, SWT.NONE);
    lbDriver.setBounds(20, 22, 97, 17);
    lbDriver.setText("请选择JDBC驱动：");

    Combo comboDriver = new Combo(gpDbConfig, SWT.NONE);
    comboDriver.setBounds(127, 19, 245, 17);

    Composite composite_1 = new Composite(sashForm, SWT.NONE);

    Composite composite_2 = new Composite(sashForm, SWT.NONE);
    sashForm.setWeights(new int[] { 87, 275, 193 });

  }

  @Override
  protected void checkSubclass() {
  }
}
