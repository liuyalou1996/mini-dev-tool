package com.universe.mbg.callback;

import org.eclipse.core.runtime.IProgressMonitor;
import org.mybatis.generator.internal.NullProgressCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 进度回调
 * @author: liuyalou
 * @date: 2019年12月12日
 */
public class MbgProgressCallback extends NullProgressCallback {

  private static final Logger LOGGER = LoggerFactory.getLogger(MbgProgressCallback.class);

  private IProgressMonitor monitor;

  public MbgProgressCallback() {

  }

  public MbgProgressCallback(IProgressMonitor monitor) {
    this.monitor = monitor;
  }

  @Override
  public void startTask(String taskName) {
    monitor.subTask(taskName);
    monitor.worked(10);
    LOGGER.info("开始任务，任务名为：{}", taskName);
  }

  @Override
  public void generationStarted(int totalTasks) {
    LOGGER.info("开始生成文件内容，总任务数为：{}", totalTasks);
  }

  @Override
  public void introspectionStarted(int totalTasks) {
    LOGGER.info("开始检索表元数据，总任务数为：{}", totalTasks);
  }

  @Override
  public void saveStarted(int totalTasks) {
    LOGGER.info("开始文件写入，总任务数为：{}", totalTasks);

  }

  @Override
  public void done() {
    monitor.done();
    LOGGER.info("生成成功!");
  }

}
