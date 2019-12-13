package com.universe.mbg.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.IntrospectedTable.TargetRuntime;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 
 * @author: liuyalou
 * @date: 2019年12月13日
 */
public class CommonsLangToStringPlugin extends PluginAdapter {

  @Override
  public boolean validate(List<String> warnings) {
    return true;
  }

  @Override
  public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    Method method = new Method("toString");
    method.setVisibility(JavaVisibility.PUBLIC);
    method.setReturnType(FullyQualifiedJavaType.getStringInstance());
    method.addAnnotation("@Override");

    if (introspectedTable.getTargetRuntime() == TargetRuntime.MYBATIS3_DSQL) {
      context.getCommentGenerator().addGeneralMethodAnnotation(method, introspectedTable, topLevelClass.getImportedTypes());
    } else {
      context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
    }

    method.addBodyLine("return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);");
    topLevelClass.addMethod(method);
    return true;
  }

}
