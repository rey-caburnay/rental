package com.shinn.mail;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataFormat {
  private static final Log LOG = LogFactory.getLog(DataFormat.class);

  private DataFormat() {
    // static class
  }

  public static String convertMessage(String content, String varName1, String varValue1) {
    try {
      String uppString = content.toUpperCase();
      String varName = varName1.toUpperCase();
      String varValue = varValue1;
      int start = uppString.indexOf(varName);
      if (start == -1)
        return content;
      int end = start + varName.length();
      StringBuilder strBuffer = new StringBuilder(content);
      if (varValue == null) {
        varValue = "";
      }
      strBuffer.replace(start, end, varValue);
      return convertMessage(strBuffer.toString(), varName, varValue);
    } catch (Exception e) {
      LOG.error(e);
      return content;
    }

  }

  /**
   * Replace all varName to varValue in a string.
   * 
   * @param content - string need to be filter
   * @param Hashtable - contains the varName and varValue
   * @return result string
   */
  public static String convertAllMessage(String content, Map variables) {
    return convertAllMessage(content, variables, true);
  }

  /**
   * 
   * Replace all varName to varValue in a string.
   * 
   * @param content
   * @param variables
   * @param isHtmlContentType : true-The conent type is text/html; false- The content is
   * @return
   */
  public static String convertAllMessage(String content1, Map variables,
      boolean isHtmlContentType) {
    String content = content1;
    Set variableKeys = variables.keySet();
    Iterator keysetIte = variableKeys.iterator();
    while (keysetIte.hasNext()) {
      String varName = (String) keysetIte.next();
      String varValue = "";
      Object paramValue = variables.get(varName);
      if (paramValue != null) {
        varValue = String.valueOf(paramValue);
      }

      if (isHtmlContentType) {
        varValue = SpecCharHandle.htmlEditFormat(varValue, "HTML");
      }

      content = convertMessage(content, varName, varValue);
    }
    return content;
  }
}
