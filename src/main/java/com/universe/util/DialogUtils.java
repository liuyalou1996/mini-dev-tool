package com.universe.util;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DialogUtils {

  /**
   * 显示对话框
   * @param shell
   * @param title
   * @param content
   */
  public static void showErrorDialog(Shell shell, String title, String message) {
    MessageBox mb = new MessageBox(shell, SWT.OK | SWT.ICON_ERROR);
    setDialog(title, message, mb);
  }

  public static void showInformationDialog(Shell shell, String title, String message) {
    MessageBox mb = new MessageBox(shell, SWT.OK | SWT.ICON_INFORMATION);
    setDialog(title, message, mb);
  }

  public static int showQuestionDialog(Shell shell, String title, String message) {
    MessageBox mb = new MessageBox(shell, SWT.YES | SWT.NO | SWT.CANCEL | SWT.ICON_QUESTION);
    int result = setDialog(title, message, mb);
    return result;
  }

  public static String showInputDialog(Shell shell, String title, String message, IInputValidator validator) {
    InputDialog dialog = new InputDialog(shell, title, message, "", validator);
    int result = dialog.open();
    return result == Window.OK ? dialog.getValue() : null;
  }

  private static int setDialog(String title, String message, MessageBox mb) {
    mb.setText(title);
    mb.setMessage(message);
    int result = mb.open();
    return result;
  }

  /**
   * 设置字体对话框
   *
   * @param shell 窗体
   * @param text  文本
   */
  public static void showFontDialog(Shell shell, StyledText text) {
    FontDialog fd = new FontDialog(shell, SWT.NONE);
    FontData fontData = fd.open();
    if (fontData != null) {
      Font font = new Font(shell.getDisplay(), fontData);
      text.setFont(font);
      Color color = new Color(shell.getDisplay(), fd.getRGB().red, fd.getRGB().green, fd.getRGB().blue);
      text.setForeground(color);

      // 设置完后关闭资源
      font.dispose();
      color.dispose();
    }
  }

  /**
   * 设置颜色对话框
   *
   * @param shell 窗体
   * @param text  文本框
   */
  public static void showColorDialog(Shell shell, Text text) {
    ColorDialog cd = new ColorDialog(shell, SWT.NONE);
    RGB rgb = cd.open();
    if (rgb != null) {
      Color color = new Color(shell.getDisplay(), rgb);
      text.setForeground(color);
    }
  }

  /**
   * 打印对话框
   *
   * @param shell 窗体
   */
  public static void showPrintDialog(Shell shell) {
    PrintDialog dialog = new PrintDialog(shell, SWT.NONE);
    PrinterData pd = dialog.open();
    if (pd != null) {
      Printer printer = new Printer(pd);
      printer.dispose();
    }
  }

  public static String showOpenFileDialog(Shell shell, String filterPath, String[] filterExtensions, String[] filterNames) {
    return showFileDialog(shell, SWT.OPEN, filterPath, filterExtensions, filterNames);
  }

  public static String showSaveFileDialog(Shell shell, String filterPath, String[] filterExtensions, String[] filterNames) {
    return showFileDialog(shell, SWT.SAVE, filterPath, filterExtensions, filterNames);
  }

  /**
   * 打开文件对话框
   * @param shell
   * @param style SWT.OPEN：打开对话框      SWT.SAVE：保存对话框
   * @param filterPath 默认对话框打开路径
   * @param filterExtensions 过滤的文件扩展名
   * @param filterNames 显示到下拉框中的扩展名的名称
   * @return
   */
  public static String showFileDialog(Shell shell, int style, String filterPath, String[] filterExtensions, String[] filterNames) {
    FileDialog fd = new FileDialog(shell, style);

    // 设置打开时文件的默认路径
    fd.setFilterPath(filterPath);
    if (StringUtils.isBlank(filterPath)) {
      fd.setFilterPath(System.getProperty("user.home"));
    }
    
    // 设置所打开文件的扩展名
    fd.setFilterExtensions(filterExtensions);
    // 设置显示到下拉框中的扩展名的名称
    fd.setFilterNames(filterNames);
    // 文件的抽象目录
    String file = fd.open();
    return file;
  }

  /**
   * 保存文件，目录选择对话框
   *
   * @param shell 窗体
   * @return
   */
  public static String showDirectoryDialog(Shell shell) {
    DirectoryDialog dd = new DirectoryDialog(shell, SWT.NONE);
    // 设置显示在窗口上方的提示信息
    dd.setMessage("请选择要保存的文件夹");
    // 设置对话框的标题
    dd.setText("请选择文件目录");
    // 设置打开时默认的文件目录
    dd.setFilterPath(System.getProperty("user.home"));
    // 打开窗口，返回用户选择的文件目录
    String fileDir = dd.open();
    return fileDir;
  }

  /**
   * 进度条对话框
   * @param shell
   * @param fork
   * @param cancelable
   * @param runnbale
   * @return
   * @throws Exception
   */
  public static ProgressMonitorDialog showProgressDialog(Shell shell, boolean fork, boolean cancelable, IRunnableWithProgress runnbale)
      throws Exception {
    ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(shell);
    progressDialog.run(fork, cancelable, runnbale);
    return progressDialog;
  }
}
