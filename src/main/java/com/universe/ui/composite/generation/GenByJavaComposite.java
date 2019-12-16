package com.universe.ui.composite.generation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.universe.common.constant.ButtonTypeConsts;
import com.universe.common.constant.SystemConsts;
import com.universe.pojo.dto.GenByJavaDto;
import com.universe.service.listener.selection.GenByJavaButtonSelectionListener;

public class GenByJavaComposite extends Composite {

  private Combo comboDriver;
  private Text txDriverPath;
  private Text txUrl;
  private Text txUsername;
  private Text txPassword;

  private Text txTargetProject;
  private Text txModelTargetPackage;
  private Text txClientTargetPackage;
  private Text txXmlTargetPackage;

  private Text txInputTables;

  private Text txRemovedTablePrefix;
  private Text txClassSuffix;
  private Text txMapperSuffix;

  private Button btnChooseProject;
  private Button btnChooseDriverPath;

  private Button btnAllTables;
  private Button btnInputTables;

  private Button btnEnableSelectByExample;
  private Button btnEnableUpdateByExample;
  private Button btnEnableDeleteByExample;
  private Button btnEnableCountByExample;

  private Button btnEnableSelectByPrimaryKey;
  private Button btnEnableUpdateByPrimaryKey;
  private Button btnEnableDeleteByPrimaryKey;
  private Button btnEnableInsert;

  private Button btnEnableToString;
  private Button btnUseActualColumnNames;
  private Button btnUseColumnIndexes;
  private Button btnTrimStrings;
  private Button btnForceBigDecimal;

  private Button btnEnableCamelCase;

  private Button btnGenerate;
  private Button btnImportConfig;
  private Button btnExportConfig;

  public GenByJavaComposite(Composite parent) {
    super(parent, SWT.NONE);
    setLayout(new FillLayout(SWT.HORIZONTAL));

    SashForm sashForm = new SashForm(this, SWT.VERTICAL);

    // 初始化数据库连接信息相关组件
    initConnInfoComponent(sashForm);
    // 初始化项目信息相关组件
    initProjectInfoComponent(sashForm);
    // 初始化表数据配置相关组件
    initTableCountConfigComponent(sashForm);
    // 初始化自定义文件内容相关组件
    initCustomizedFileContentComponent(sashForm);
    // 初始化自定义文件名相关组件
    initCustomizedFileNameComponent(sashForm);
    // 初始化生成按钮
    initGenComponent(sashForm);

    sashForm.setWeights(new int[] { 156, 122, 67, 132, 129, 50 });

  }

  private void initConnInfoComponent(SashForm sashForm) {
    Composite compConnInfo = new Composite(sashForm, SWT.BORDER);
    compConnInfo.setLayout(new FillLayout(SWT.HORIZONTAL));

    Group gpConnInfo = new Group(compConnInfo, SWT.BORDER);
    gpConnInfo.setText("数据库连接信息");

    Label lbDriver = new Label(gpConnInfo, SWT.NONE);
    lbDriver.setBounds(20, 22, 110, 17);
    lbDriver.setText("请选择JDBC驱动名：");

    comboDriver = new Combo(gpConnInfo, SWT.NONE);
    comboDriver.setBounds(175, 19, 164, 25);
    comboDriver.setItems("com.mysql.cj.jdbc.Driver", "oracle.jdbc.OracleDriver");

    Label lbDriverPath = new Label(gpConnInfo, SWT.NONE);
    lbDriverPath.setBounds(20, 50, 136, 17);
    lbDriverPath.setText("请选择JDBC驱动包(.jar)：");

    txDriverPath = new Text(gpConnInfo, SWT.BORDER);
    txDriverPath.setBounds(175, 50, 551, 23);

    btnChooseDriverPath = new Button(gpConnInfo, SWT.NONE);
    btnChooseDriverPath.setBounds(732, 50, 71, 23);
    btnChooseDriverPath.setText("请选择");
    btnChooseDriverPath.setData(SystemConsts.BUTTON_TYPE, ButtonTypeConsts.GenByJava.CHOOSE_DRIVER);

    GenByJavaDto dto = new GenByJavaDto();
    dto.setTxDriverPath(txDriverPath);
    btnChooseDriverPath.addSelectionListener(new GenByJavaButtonSelectionListener(dto));

    Label lbUrl = new Label(gpConnInfo, SWT.NONE);
    lbUrl.setBounds(20, 81, 71, 17);
    lbUrl.setText("请输入URL：");

    txUrl = new Text(gpConnInfo, SWT.BORDER);
    txUrl.setBounds(175, 81, 551, 23);

    Label lbUsername = new Label(gpConnInfo, SWT.NONE);
    lbUsername.setBounds(20, 113, 84, 17);
    lbUsername.setText("请输入用户名：");

    txUsername = new Text(gpConnInfo, SWT.BORDER);
    txUsername.setBounds(175, 110, 164, 23);

    Label lbPassword = new Label(gpConnInfo, SWT.NONE);
    lbPassword.setBounds(367, 113, 71, 17);
    lbPassword.setText("请输入密码：");

    txPassword = new Text(gpConnInfo, SWT.BORDER);
    txPassword.setBounds(444, 110, 159, 23);
  }

  private void initProjectInfoComponent(SashForm sashForm) {
    Composite compProjectInfo = new Composite(sashForm, SWT.BORDER);
    compProjectInfo.setLayout(new FillLayout(SWT.HORIZONTAL));

    Group gpProjectInfo = new Group(compProjectInfo, SWT.BORDER);
    gpProjectInfo.setText("项目路径信息");

    Label lbTargetProject = new Label(gpProjectInfo, SWT.NONE);
    lbTargetProject.setBounds(22, 20, 90, 17);
    lbTargetProject.setText("项目路径：");
    lbTargetProject.setToolTipText("项目根目录");

    txTargetProject = new Text(gpProjectInfo, SWT.BORDER);
    txTargetProject.setBounds(142, 17, 545, 23);
    txTargetProject.setToolTipText("项目根目录");

    btnChooseProject = new Button(gpProjectInfo, SWT.NONE);
    btnChooseProject.setBounds(693, 17, 66, 23);
    btnChooseProject.setText("请选择");
    btnChooseProject.setData(SystemConsts.BUTTON_TYPE, ButtonTypeConsts.GenByJava.CHOOSE_TARGET_PROJECT);

    GenByJavaDto dto = new GenByJavaDto();
    dto.setTxTargetProject(txTargetProject);
    btnChooseProject.addSelectionListener(new GenByJavaButtonSelectionListener(dto));

    Label lbModelTargetPackage = new Label(gpProjectInfo, SWT.NONE);
    lbModelTargetPackage.setBounds(23, 49, 61, 17);
    lbModelTargetPackage.setText("实体包名：");
    lbModelTargetPackage.setToolTipText("src/main/java目录");

    txModelTargetPackage = new Text(gpProjectInfo, SWT.BORDER);
    txModelTargetPackage.setBounds(142, 46, 240, 23);
    txModelTargetPackage.setToolTipText("src/main/java目录");

    Label lbClientTargetPackage = new Label(gpProjectInfo, SWT.NONE);
    lbClientTargetPackage.setBounds(22, 78, 103, 17);
    lbClientTargetPackage.setText("Mapper接口包名：");
    lbClientTargetPackage.setToolTipText("src/main/java目录");

    txClientTargetPackage = new Text(gpProjectInfo, SWT.BORDER);
    txClientTargetPackage.setBounds(142, 75, 240, 23);
    txClientTargetPackage.setToolTipText("src/main/java目录");

    Label lbXmlTargetPackage = new Label(gpProjectInfo, SWT.NONE);
    lbXmlTargetPackage.setBounds(406, 78, 102, 17);
    lbXmlTargetPackage.setText("Sql映射文件包名：");
    lbXmlTargetPackage.setToolTipText("src/main/resources目录");

    txXmlTargetPackage = new Text(gpProjectInfo, SWT.BORDER);
    txXmlTargetPackage.setBounds(519, 75, 240, 23);
    txXmlTargetPackage.setToolTipText("src/main/resources目录");
  }

  private void initTableCountConfigComponent(SashForm sashForm) {
    Composite compTableCount = new Composite(sashForm, SWT.BORDER);
    compTableCount.setLayout(new FillLayout(SWT.HORIZONTAL));

    Group gpTableCount = new Group(compTableCount, SWT.BORDER);
    gpTableCount.setText("自动生成表数量配置");

    btnAllTables = new Button(gpTableCount, SWT.RADIO);
    btnAllTables.setSelection(true);
    btnAllTables.setBounds(25, 28, 84, 17);
    btnAllTables.setText(" 所有表");

    btnInputTables = new Button(gpTableCount, SWT.RADIO);
    btnInputTables.setBounds(115, 28, 70, 17);
    btnInputTables.setText(" 自定义");

    Label lbInputTables = new Label(gpTableCount, SWT.NONE);
    lbInputTables.setBounds(191, 28, 152, 17);
    lbInputTables.setText("请输入表名(英文逗号分隔)：");

    txInputTables = new Text(gpTableCount, SWT.BORDER);
    txInputTables.setBounds(353, 25, 487, 23);
    txInputTables.setEnabled(false);

    // 当选中所有表时表输入文本框置灰
    btnAllTables.addSelectionListener(new SelectionAdapter() {

      @Override
      public void widgetSelected(SelectionEvent e) {
        txInputTables.setEnabled(false);
      }

    });

    // 当自定义按钮不被选中启用表输入文本框
    btnInputTables.addSelectionListener(new SelectionAdapter() {

      @Override
      public void widgetSelected(SelectionEvent e) {
        txInputTables.setEnabled(true);
      }
    });

  }

  private void initCustomizedFileContentComponent(SashForm sashForm) {
    Composite compGenContentConfig = new Composite(sashForm, SWT.BORDER);
    compGenContentConfig.setLayout(new FillLayout(SWT.HORIZONTAL));

    Group gpGenInfo = new Group(compGenContentConfig, SWT.BORDER);
    gpGenInfo.setText("自动生成内容配置");

    btnEnableSelectByExample = new Button(gpGenInfo, SWT.CHECK);
    btnEnableSelectByExample.setBounds(21, 28, 161, 17);
    btnEnableSelectByExample.setText(" EnableSelectByExample");

    btnEnableUpdateByExample = new Button(gpGenInfo, SWT.CHECK);
    btnEnableUpdateByExample.setBounds(221, 28, 170, 17);
    btnEnableUpdateByExample.setText(" EnableUpdateByExample");

    btnEnableDeleteByExample = new Button(gpGenInfo, SWT.CHECK);
    btnEnableDeleteByExample.setBounds(426, 28, 161, 17);
    btnEnableDeleteByExample.setText(" EnableDeleteByExample");

    btnEnableCountByExample = new Button(gpGenInfo, SWT.CHECK);
    btnEnableCountByExample.setBounds(613, 28, 170, 17);
    btnEnableCountByExample.setText(" EnableCountByExample");

    btnEnableSelectByPrimaryKey = new Button(gpGenInfo, SWT.CHECK);
    btnEnableSelectByPrimaryKey.setSelection(true);
    btnEnableSelectByPrimaryKey.setBounds(21, 61, 182, 17);
    btnEnableSelectByPrimaryKey.setText(" EnableSelectByPrimaryKey");

    btnEnableUpdateByPrimaryKey = new Button(gpGenInfo, SWT.CHECK);
    btnEnableUpdateByPrimaryKey.setSelection(true);
    btnEnableUpdateByPrimaryKey.setBounds(221, 61, 186, 17);
    btnEnableUpdateByPrimaryKey.setText(" EnableUpdateByPrimaryKey");

    btnEnableDeleteByPrimaryKey = new Button(gpGenInfo, SWT.CHECK);
    btnEnableDeleteByPrimaryKey.setSelection(true);
    btnEnableDeleteByPrimaryKey.setBounds(426, 61, 180, 17);
    btnEnableDeleteByPrimaryKey.setText(" EnableDeleteByPrimaryKey");

    btnEnableInsert = new Button(gpGenInfo, SWT.CHECK);
    btnEnableInsert.setSelection(true);
    btnEnableInsert.setBounds(613, 61, 98, 17);
    btnEnableInsert.setText(" EnableInsert");

    btnUseActualColumnNames = new Button(gpGenInfo, SWT.CHECK);
    btnUseActualColumnNames.setToolTipText("是否使用表字段名作为类字段名");
    btnUseActualColumnNames.setBounds(221, 94, 170, 17);
    btnUseActualColumnNames.setText(" UseActualColumnNames");

    btnUseColumnIndexes = new Button(gpGenInfo, SWT.CHECK);
    btnUseColumnIndexes.setToolTipText("是否使用表列的索引");
    btnUseColumnIndexes.setBounds(426, 94, 170, 17);
    btnUseColumnIndexes.setText(" UseColumnIndexes");

    btnTrimStrings = new Button(gpGenInfo, SWT.CHECK);
    btnTrimStrings.setToolTipText("是否去除字段空格");
    btnTrimStrings.setBounds(613, 94, 98, 17);
    btnTrimStrings.setText(" TrimStrings");

    btnForceBigDecimal = new Button(gpGenInfo, SWT.CHECK);
    btnForceBigDecimal.setToolTipText("是否强制转字段类型为BigDecimal");
    btnForceBigDecimal.setBounds(721, 94, 122, 17);
    btnForceBigDecimal.setText(" Force BigDecimal");

    btnEnableToString = new Button(gpGenInfo, SWT.CHECK);
    btnEnableToString.setToolTipText("是否使用commons lang的ToStringBuilder生成toString方法");
    btnEnableToString.setSelection(true);
    btnEnableToString.setBounds(21, 94, 149, 17);
    btnEnableToString.setText(" EnableToStringBuilder");
  }

  private void initCustomizedFileNameComponent(SashForm sashForm) {
    Composite compFileNameConfig = new Composite(sashForm, SWT.BORDER);
    compFileNameConfig.setLayout(new FillLayout(SWT.HORIZONTAL));

    Group gpFileNameConfig = new Group(compFileNameConfig, SWT.BORDER);
    gpFileNameConfig.setToolTipText("");
    gpFileNameConfig.setText("自动生成文件名配置");

    btnEnableCamelCase = new Button(gpFileNameConfig, SWT.CHECK);
    btnEnableCamelCase.setToolTipText("根据数据库表名下划线转驼峰生成类名");
    btnEnableCamelCase.setBounds(22, 29, 169, 17);
    btnEnableCamelCase.setText(" 是否启用表名下划线转驼峰");
    btnEnableCamelCase.setSelection(true);

    Label lbRemovedTablePrefix = new Label(gpFileNameConfig, SWT.NONE);
    lbRemovedTablePrefix.setToolTipText("生成类名时去除前缀，需开启下划线转驼峰");
    lbRemovedTablePrefix.setBounds(22, 59, 115, 17);
    lbRemovedTablePrefix.setText("需去除的表名前缀：");

    txRemovedTablePrefix = new Text(gpFileNameConfig, SWT.BORDER);
    txRemovedTablePrefix.setBounds(143, 56, 124, 23);

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

    btnEnableCamelCase.addSelectionListener(new SelectionAdapter() {

      @Override
      public void widgetSelected(SelectionEvent e) {
        if (btnEnableCamelCase.getSelection()) {
          txRemovedTablePrefix.setEnabled(true);
          txClassSuffix.setEnabled(true);
          txMapperSuffix.setEnabled(true);
          return;
        }

        txRemovedTablePrefix.setEnabled(false);
        txClassSuffix.setEnabled(false);
        txMapperSuffix.setEnabled(false);
      }
    });
  }

  private void initGenComponent(SashForm sashForm) {
    Composite compGen = new Composite(sashForm, SWT.BORDER);
    compGen.setLayout(new FillLayout(SWT.HORIZONTAL));

    GenByJavaButtonSelectionListener listener = new GenByJavaButtonSelectionListener(buildNecessaryParams());

    btnGenerate = new Button(compGen, SWT.NONE);
    btnGenerate.setText("自动生成");
    btnGenerate.setToolTipText("自动生成代码");
    btnGenerate.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
    btnGenerate.setData(SystemConsts.BUTTON_TYPE, ButtonTypeConsts.GenByJava.AUTO_GENERATE_BY_JAVA);
    btnGenerate.addSelectionListener(listener);

    btnImportConfig = new Button(compGen, SWT.NONE);
    btnImportConfig.setToolTipText("导入配置(.json文件)");
    btnImportConfig.setText("导入配置");
    btnImportConfig.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
    btnImportConfig.setData(SystemConsts.BUTTON_TYPE, ButtonTypeConsts.GenByJava.IMPORT_JSON_COFIG);
    btnImportConfig.addSelectionListener(listener);

    btnExportConfig = new Button(compGen, SWT.NONE);
    btnExportConfig.setToolTipText("导出为.json文件");
    btnExportConfig.setText("导出配置");
    btnExportConfig.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
    btnExportConfig.setData(SystemConsts.BUTTON_TYPE, ButtonTypeConsts.GenByJava.EXPORT_JSON_CONFIG);
    btnExportConfig.addSelectionListener(listener);

  }

  private GenByJavaDto buildNecessaryParams() {
    GenByJavaDto dto = new GenByJavaDto();
    dto.setComboDriver(comboDriver);
    dto.setTxDriverPath(txDriverPath);
    dto.setTxUrl(txUrl);
    dto.setTxUsername(txUsername);
    dto.setTxPassword(txPassword);
    dto.setTxTargetProject(txTargetProject);
    dto.setTxModelTargetPackage(txModelTargetPackage);
    dto.setTxClientTargetPackage(txClientTargetPackage);
    dto.setTxXmlTargetPackage(txXmlTargetPackage);
    dto.setTxInputTables(txInputTables);
    dto.setTxRemovedTablePrefix(txRemovedTablePrefix);
    dto.setTxClassSuffix(txClassSuffix);
    dto.setTxMapperSuffix(txMapperSuffix);

    dto.setBtnAllTables(btnAllTables);
    dto.setBtnInputTables(btnInputTables);
    dto.setBtnEnableSelectByExample(btnEnableSelectByExample);
    dto.setBtnEnableUpdateByExample(btnEnableUpdateByExample);
    dto.setBtnEnableDeleteByExample(btnEnableDeleteByExample);
    dto.setBtnEnableCountByExample(btnEnableCountByExample);
    dto.setBtnEnableSelectByPrimaryKey(btnEnableSelectByPrimaryKey);
    dto.setBtnEnableUpdateByPrimaryKey(btnEnableUpdateByPrimaryKey);
    dto.setBtnEnableDeleteByPrimaryKey(btnEnableDeleteByPrimaryKey);
    dto.setBtnEnableInsert(btnEnableInsert);
    dto.setBtnEnableToString(btnEnableToString);
    dto.setBtnUseActualColumnNames(btnUseActualColumnNames);
    dto.setBtnUseColumnIndexes(btnUseColumnIndexes);
    dto.setBtnTrimStrings(btnTrimStrings);
    dto.setBtnForceBigDecimal(btnForceBigDecimal);
    dto.setBtnEnableCamelCase(btnEnableCamelCase);
    return dto;
  }

  @Override
  protected void checkSubclass() {
  }
}
