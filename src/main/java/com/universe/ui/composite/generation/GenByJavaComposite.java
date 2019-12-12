package com.universe.ui.composite.generation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class GenByJavaComposite extends Composite {

  private Text txUrl;
  private Text txUsername;
  private Text txPassword;
  private Text txTargetProject;
  private Text txModelTargetPackage;
  private Text txClientTargetPackage;
  private Text txXmlTargetPackage;
  private Text txDrivePath;
  private Text txTablePrefix;
  private Text txClassSuffix;
  private Text txMapperSuffix;

  public GenByJavaComposite(Composite parent) {
    super(parent, SWT.NONE);
    setLayout(new FillLayout(SWT.HORIZONTAL));

    SashForm sashForm = new SashForm(this, SWT.VERTICAL);

    // 初始化数据库连接信息相关组件
    initConnInfoComponent(sashForm);
    // 初始化项目信息相关组件
    initProjectInfoComponent(sashForm);
    // 初始化自定义文件内容相关组件
    initCustomizedFileContentComponent(sashForm);
    // 初始化自定义文件名相关组件
    initCustomizedFileNameComponent(sashForm);

    sashForm.setWeights(new int[] { 187, 149, 145, 164 });

  }

  private void initCustomizedFileNameComponent(SashForm sashForm) {
    Composite compFileNameConfig = new Composite(sashForm, SWT.BORDER);
    compFileNameConfig.setLayout(new FillLayout(SWT.HORIZONTAL));

    Group gpFileNameConfig = new Group(compFileNameConfig, SWT.BORDER);
    gpFileNameConfig.setToolTipText("");
    gpFileNameConfig.setText("自定生成文件名配置");

    Button btnEnableCamelCase = new Button(gpFileNameConfig, SWT.CHECK);
    btnEnableCamelCase.setToolTipText("根据数据库表名下划线转驼峰生成类名");
    btnEnableCamelCase.setBounds(22, 29, 169, 17);
    btnEnableCamelCase.setText(" 是否启用表名下划线转驼峰");

    Label lbTablePrefix = new Label(gpFileNameConfig, SWT.NONE);
    lbTablePrefix.setToolTipText("生成类名时去除前缀，需开启下划线转驼峰");
    lbTablePrefix.setBounds(22, 59, 115, 17);
    lbTablePrefix.setText("需去除的表名前缀：");

    txTablePrefix = new Text(gpFileNameConfig, SWT.BORDER);
    txTablePrefix.setBounds(143, 56, 124, 23);

    Label lbClassSuffix = new Label(gpFileNameConfig, SWT.NONE);
    lbClassSuffix.setToolTipText("生成类名时添加后缀，需开启下划线转驼峰");
    lbClassSuffix.setBounds(22, 88, 115, 17);
    lbClassSuffix.setText("需添加的类名后缀：");

    txClassSuffix = new Text(gpFileNameConfig, SWT.BORDER);
    txClassSuffix.setBounds(143, 85, 124, 23);

    Label lbMapperSuffix = new Label(gpFileNameConfig, SWT.NONE);
    lbMapperSuffix.setToolTipText("Mapper接口名或Mapper映射文件名后缀，需开启下划线转驼峰");
    lbMapperSuffix.setBounds(290, 88, 233, 17);
    lbMapperSuffix.setText("需添加的Mapper接口或映射文件名后缀：");

    txMapperSuffix = new Text(gpFileNameConfig, SWT.BORDER);
    txMapperSuffix.setBounds(529, 85, 124, 23);
  }

  private void initCustomizedFileContentComponent(SashForm sashForm) {
    Composite compGenContentConfig = new Composite(sashForm, SWT.BORDER);
    compGenContentConfig.setLayout(new FillLayout(SWT.HORIZONTAL));

    Group gpGenInfo = new Group(compGenContentConfig, SWT.BORDER);
    gpGenInfo.setText("自动生成内容配置");

    Button btnEnableSelectByExample = new Button(gpGenInfo, SWT.CHECK);
    btnEnableSelectByExample.setBounds(21, 28, 161, 17);
    btnEnableSelectByExample.setText(" EnableSelectByExample");

    Button btnEnableUpdateByExample = new Button(gpGenInfo, SWT.CHECK);
    btnEnableUpdateByExample.setBounds(221, 28, 170, 17);
    btnEnableUpdateByExample.setText(" EnableUpdateByExample");

    Button btnEnableDeleteByExample = new Button(gpGenInfo, SWT.CHECK);
    btnEnableDeleteByExample.setBounds(426, 28, 161, 17);
    btnEnableDeleteByExample.setText(" EnableDeleteByExample");

    Button btnEnableCountByExample = new Button(gpGenInfo, SWT.CHECK);
    btnEnableCountByExample.setBounds(613, 28, 170, 17);
    btnEnableCountByExample.setText(" EnableCountByExample");

    Button btnEnableSelectByPrimaryKey = new Button(gpGenInfo, SWT.CHECK);
    btnEnableSelectByPrimaryKey.setBounds(21, 61, 182, 17);
    btnEnableSelectByPrimaryKey.setText(" EnableSelectByPrimaryKey");

    Button btnEnableUpdateByPrimaryKey = new Button(gpGenInfo, SWT.CHECK);
    btnEnableUpdateByPrimaryKey.setBounds(221, 61, 186, 17);
    btnEnableUpdateByPrimaryKey.setText(" EnableUpdateByPrimaryKey");

    Button btnEnableDeleteByPrimaryKey = new Button(gpGenInfo, SWT.CHECK);
    btnEnableDeleteByPrimaryKey.setBounds(426, 61, 180, 17);
    btnEnableDeleteByPrimaryKey.setText(" EnableDeleteByPrimaryKey");

    Button btnEnableInsert = new Button(gpGenInfo, SWT.CHECK);
    btnEnableInsert.setBounds(613, 61, 98, 17);
    btnEnableInsert.setText(" EnableInsert");

    Button btnUseActualColumnNames = new Button(gpGenInfo, SWT.CHECK);
    btnUseActualColumnNames.setBounds(21, 94, 170, 17);
    btnUseActualColumnNames.setText(" UseActualColumnNames");

    Button btnUseColumnIndexes = new Button(gpGenInfo, SWT.CHECK);
    btnUseColumnIndexes.setBounds(221, 94, 170, 17);
    btnUseColumnIndexes.setText(" UseColumnIndexes");

    Button btnTrimStrings = new Button(gpGenInfo, SWT.CHECK);
    btnTrimStrings.setBounds(425, 94, 98, 17);
    btnTrimStrings.setText(" TrimStrings");

    Button btnForceBigDecimal = new Button(gpGenInfo, SWT.CHECK);
    btnForceBigDecimal.setBounds(612, 94, 122, 17);
    btnForceBigDecimal.setText(" Force BigDecimal");
  }

  private void initProjectInfoComponent(SashForm sashForm) {
    Composite compProjectInfo = new Composite(sashForm, SWT.BORDER);
    compProjectInfo.setLayout(new FillLayout(SWT.HORIZONTAL));

    Group gpProjectInfo = new Group(compProjectInfo, SWT.BORDER);
    gpProjectInfo.setText("项目路径信息");

    Label lbTargetProject = new Label(gpProjectInfo, SWT.NONE);
    lbTargetProject.setBounds(22, 20, 90, 17);
    lbTargetProject.setText("项目路径：");

    txTargetProject = new Text(gpProjectInfo, SWT.BORDER);
    txTargetProject.setBounds(142, 17, 240, 23);

    Button btnChooseProject = new Button(gpProjectInfo, SWT.NONE);
    btnChooseProject.setBounds(388, 17, 66, 23);
    btnChooseProject.setText("请选择");

    Label lbModelTargetPackage = new Label(gpProjectInfo, SWT.NONE);
    lbModelTargetPackage.setBounds(23, 49, 61, 17);
    lbModelTargetPackage.setText("实体包名：");

    txModelTargetPackage = new Text(gpProjectInfo, SWT.BORDER);
    txModelTargetPackage.setBounds(142, 46, 240, 23);

    Label lbClientTargetPackage = new Label(gpProjectInfo, SWT.NONE);
    lbClientTargetPackage.setBounds(22, 78, 103, 17);
    lbClientTargetPackage.setText("Mapper接口包名：");

    txClientTargetPackage = new Text(gpProjectInfo, SWT.BORDER);
    txClientTargetPackage.setBounds(142, 75, 240, 23);

    Label lbXmlTargetPackage = new Label(gpProjectInfo, SWT.NONE);
    lbXmlTargetPackage.setBounds(23, 110, 102, 17);
    lbXmlTargetPackage.setText("Sql映射文件包名：");

    txXmlTargetPackage = new Text(gpProjectInfo, SWT.BORDER);
    txXmlTargetPackage.setBounds(142, 104, 240, 23);
  }

  private void initConnInfoComponent(SashForm sashForm) {
    Composite compConnInfo = new Composite(sashForm, SWT.BORDER);
    compConnInfo.setLayout(new FillLayout(SWT.HORIZONTAL));

    Group gpConnInfo = new Group(compConnInfo, SWT.BORDER);
    gpConnInfo.setText("数据库连接信息");

    Label lbDriver = new Label(gpConnInfo, SWT.NONE);
    lbDriver.setBounds(20, 22, 110, 17);
    lbDriver.setText("请选择JDBC驱动名：");

    Combo comboDriver = new Combo(gpConnInfo, SWT.NONE);
    comboDriver.setBounds(175, 19, 333, 25);

    Label lbDriverPath = new Label(gpConnInfo, SWT.NONE);
    lbDriverPath.setBounds(20, 50, 136, 17);
    lbDriverPath.setText("请选择JDBC驱动包(.jar)：");

    txDrivePath = new Text(gpConnInfo, SWT.BORDER);
    txDrivePath.setBounds(175, 50, 333, 23);

    Button btnChooseDrivePath = new Button(gpConnInfo, SWT.NONE);
    btnChooseDrivePath.setBounds(514, 50, 71, 23);
    btnChooseDrivePath.setText("请选择");

    Label lbUrl = new Label(gpConnInfo, SWT.NONE);
    lbUrl.setBounds(20, 81, 71, 17);
    lbUrl.setText("请输入URL：");

    txUrl = new Text(gpConnInfo, SWT.BORDER);
    txUrl.setBounds(175, 81, 266, 23);

    Label lbUsername = new Label(gpConnInfo, SWT.NONE);
    lbUsername.setBounds(20, 113, 84, 17);
    lbUsername.setText("请输入用户名：");

    txUsername = new Text(gpConnInfo, SWT.BORDER);
    txUsername.setBounds(175, 110, 266, 23);

    Label lbPassword = new Label(gpConnInfo, SWT.NONE);
    lbPassword.setBounds(20, 142, 71, 17);
    lbPassword.setText("请输入密码：");

    txPassword = new Text(gpConnInfo, SWT.BORDER);
    txPassword.setBounds(175, 139, 266, 23);
  }

  @Override
  protected void checkSubclass() {
  }
}
