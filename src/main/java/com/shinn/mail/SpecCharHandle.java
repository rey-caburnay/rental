package com.shinn.mail;

import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SpecCharHandle {
  private static final Log LOG = LogFactory.getLog(SpecCharHandle.class);

  private SpecCharHandle() {
    // static class
  }

  /**
   * Provide a same functionality of CodeFusion function HtmlEditFormat
   * 
   * @param inString
   * @param forWhat
   * @return result string
   */
  public static String htmlEditFormat(String inString, String forWhat) {
    if (inString == null)
      return "";
    if ("HTML".equals(forWhat) || "INPUT".equals(forWhat)) {
      StringBuilder thisText = new StringBuilder(11 + Math.round((float) inString.length() * 1.5F));
      escapeXML(inString, thisText, false);
      return thisText.toString();
    } else if ("URL".equals(forWhat)) {
      return urlEncode(inString);
    }
    if ("XML".equals(forWhat)) {
      StringBuilder thisText = new StringBuilder(11 + Math.round((float) inString.length() * 1.5F));
      escapeXML(inString, thisText, true);
      return thisText.toString();
    } else {
      return inString;
    }

  }

  /**
   * Processing special character.
   * 
   * @param inString
   * @param forWhat
   * @return
   */
  public static String htmlEditFormat(String inString1, String[] forWhat) {
    String inString = inString1;
    if (inString == null)
      return "";
    for (String encodeType : forWhat) {
      inString = htmlEditFormat(inString, encodeType);
    }

    return inString;
  }

  /**
   * 
   * Encode String with UTF-8 charset.
   *
   * @param str String
   * @return
   */
  public static String urlEncode(String str) {
    String result;
    try {
      result = java.net.URLEncoder.encode(str, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      LOG.error(e);
      result = str;
    }
    return result;
  }

  /**
   * 
   * Decode String with UTF-8 charset..
   *
   * @param str String
   * @return
   */
  public static String urlDecode(String str) {
    String result;
    try {
      result = java.net.URLDecoder.decode(str, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      LOG.error(e);
      result = str;
    }
    return result;
  }

  private static void escapeXML(String in, StringBuilder out, boolean xml) {
    int length = in.length();
    for (int i = 0; i < length; i++) {
      char c = in.charAt(i);
      switch (c) {
        case 60: // '<'
          out.append("&lt;");
          break;

        case 62: // '>'
          out.append("&gt;");
          break;

        case 38: // '&'
          out.append("&amp;");
          break;

        case 34: // '"'
          out.append("&quot;");
          break;

        case 39: // '\''
          out.append(xml ? "&apos;" : "&#39;");
          break;

        case 13: // '\r'
          break;

        default:
          out.append(c);
          break;
      }
    }
  }

  public static void encodeForJS(String in, StringBuilder out) {
    int length = in.length();
    for (int i = 0; i < length; i++) {
      char c = in.charAt(i);
      switch (c) {
        case 10: // '\n'
          out.append("\\n");
          break;

        case 13: // '\r'
          out.append("\\r");
          break;

        case 9: // '\t'
          out.append("\\t");
          break;

        case 8: // '\b'
          out.append("\\b");
          break;

        case 12: // '\f'
          out.append("\\f");
          break;

        case 34: // '"'
          out.append("\\\"");
          break;

        case 39: // '''
          out.append("\\'");
          break;

        case 92: // '\'
          out.append("\\\\");
          break;

        default:
          out.append(c);
          break;
      }
    }
  }
}

