package com.universe.mbg.runnable;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.mybatis.generator.api.MyBatisGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.universe.mbg.callback.MbgProgressCallback;
import com.universe.util.DialogUtils;

public class MbgProgressRunnable implements IRunnableWithProgress {

  private static final Logger LOGGER = LoggerFactory.getLogger(MbgProgressRunnable.class);

  private MyBatisGenerator myBatisGenerator;

  public MbgProgressRunnable(MyBatisGenerator myBatisGenerator) {
    this.myBatisGenerator = myBatisGenerator;
  }

  @Override
  public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
    monitor.beginTask("代码生成中...", 100);
    try {
      myBatisGenerator.generate(new MbgProgressCallback(monitor));
    } catch (Exception e) {
      LOGGER.error("生成模板异常：{}", e.getMessage(), e);
      Display.getDefault().asyncExec(() -> {
        DialogUtils.showErrorDialog(Display.getCurrent().getActiveShell(), "错误提示", e.getMessage());
      });
    }
  }

}
