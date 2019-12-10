package com.universe.service.hanlder.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Shell;

import com.universe.service.hanlder.ToolItemSelectionHandler;
import com.universe.util.CollectionUtils;
import com.universe.util.DialogUtils;
import com.universe.util.JsonUtils;

/**
 * Json转Bean处理器
 * @author: liuyalou
 * @date: 2019年12月10日
 */
public class JsonToBeanHandlerImpl implements ToolItemSelectionHandler {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void onToolItemSelected(StyledText styledText) {
    String jsonStr = styledText.getText();
    if (StringUtils.isBlank(jsonStr)) {
      return;
    }

    try {
      Map<String, String> fieldMap = resolveJsonStr(jsonStr);
      String beanName = openInputDialog(styledText.getShell());
      if (StringUtils.isBlank(beanName)) {
        return;
      }

      String javaBeanStr = generateJavaBean(beanName, fieldMap);
      String targetPath = DialogUtils.showDirectoryDialog(styledText.getShell());
      writeToTargetFile(javaBeanStr, targetPath, beanName);

      DialogUtils.showInformationDialog(styledText.getShell(), "提示", "保存成功!");
    } catch (IOException e) {
      DialogUtils.showErrorDialog(styledText.getShell(), "错误", "写入目标文件发生错误!");
    } catch (Exception e) {
      DialogUtils.showErrorDialog(styledText.getShell(), "错误", "json字符串格式不正确!");
    }
  }

  private Map<String, String> resolveJsonStr(String jsonStr) {
    Map<String, String> fieldMap = new LinkedHashMap<>();
    Map<String, Object> jsonMap = JsonUtils.toLinkedHashMap(jsonStr);
    jsonMap.forEach((field, value) -> {
      fieldMap.put(field, resolveReturnType(value));
    });

    return fieldMap;
  }

  private void writeToTargetFile(String javaBeanStr, String targetPath, String beanName) throws IOException {
    if (StringUtils.isBlank(targetPath)) {
      return;
    }

    String fileName = targetPath + File.separator + beanName + ".java";
    FileUtils.write(new File(fileName), javaBeanStr, StandardCharsets.UTF_8);
  }

  private String openInputDialog(Shell shell) {
    IInputValidator validator = (text) -> {
      if (StringUtils.isBlank(text)) {
        return "输入的bean名不能为空格!";
      }

      String first = text.substring(0, 1);
      if (!first.equals(first.toUpperCase())) {
        return "类名首字母必须大写!";
      }

      return null;
    };

    return DialogUtils.showInputDialog(shell, "提示", "请输入bean名称:", validator);
  }

  private String generateJavaBean(String beanName, Map<String, String> fieldMap) {
    StringBuilder builder = new StringBuilder("public class ");
    builder.append(beanName);
    builder.append(" {");
    builder.append("\n");

    fieldMap.forEach((fieldName, fieldType) -> {
      builder.append("\t");
      builder.append("private " + fieldType + " " + fieldName + ";");
      builder.append("\n\n");
    });

    // 生成get方法
    fieldMap.forEach((fieldName, fieldType) -> {
      String getterMethod = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
      builder.append("\t");
      builder.append("public " + fieldType + " " + getterMethod + "(){");
      builder.append("\n");
      builder.append("\t\t");
      builder.append("return " + fieldName + ";");
      builder.append("\n");
      builder.append("\t}");
      builder.append("\n\n");
    });

    // 生成set方法
    fieldMap.forEach((fieldName, fieldType) -> {
      String setterMethod = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
      builder.append("\tpublic void " + setterMethod + "(" + fieldType + " " + fieldName + ") {");
      builder.append("\n");
      builder.append("\t\t");
      builder.append("this." + fieldName + " = " + fieldName + ";");
      builder.append("\n");
      builder.append("\t}");
      builder.append("\n\n");
    });

    // 生成toString方法
    builder.append("\t@Override\n");
    builder.append("\tpublic String toString(){\n");
    builder.append("\t\treturn ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);");
    builder.append("\n\t}");
    builder.append("\n}");

    return builder.toString();
  }

  private String resolveReturnType(Object value) {
    Class<?> clazz = value.getClass();
    if (List.class.isInstance(value)) {
      List<?> list = List.class.cast(value);
      if (CollectionUtils.isEmpty(list)) {
        return "List<Object>";
      }

      Object obj = list.get(0);
      if (Map.class.isInstance(obj)) {
        return "List<Map<String, Object>>";
      }

      return "List<" + obj.getClass().getSimpleName() + ">";
    }

    if (Map.class.isInstance(value)) {
      return "Map<String, Object>";
    }

    return clazz.getSimpleName();
  }

}
