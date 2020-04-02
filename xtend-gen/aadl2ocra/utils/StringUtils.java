package aadl2ocra.utils;

@SuppressWarnings("all")
public class StringUtils {
  public static String convert(final String str) {
    return str.toLowerCase().replace(".", "_");
  }
  
  public static String convertPoint(final String str) {
    return str.replace(".", "_");
  }
  
  public static String clear(final String str) {
    return "";
  }
  
  public static String convertNo2Low(final String str) {
    return str.replace(".", "_");
  }
  
  public static String removeImpl(final String str) {
    final int length = str.length();
    if ((length > 0)) {
      return str.substring(0, (length - 5));
    } else {
      return str;
    }
  }
  
  public static String removeFalse(final String str) {
    final int length = str.length();
    if ((length > 0)) {
      return str.substring(0, (length - 6));
    } else {
      return str;
    }
  }
  
  public static String removeLastChar(final String str) {
    final int length = str.length();
    if ((length > 0)) {
      return str.substring(0, (length - 1));
    } else {
      return str;
    }
  }
  
  public static String remove5Char(final String str) {
    final int length = str.length();
    if ((length > 0)) {
      return str.substring(0, (length - 5));
    } else {
      return str;
    }
  }
  
  public static String clearspace(final String str) {
    return str.replaceAll("\r|\n", "");
  }
  
  public static String clearblank(final String str) {
    return str.replaceAll(" ", "");
  }
  
  public static String clearTab(final String str) {
    return str.replaceAll("\\t", "");
  }
  
  public static String dealMultipleSpace(final String str) {
    return str.replaceAll("\\s+", " ");
  }
  
  public static String formatParam(final String str) {
    String temp = str;
    temp = temp.replaceAll("(; )+", "; ");
    boolean _endsWith = temp.endsWith(", ");
    if (_endsWith) {
      int _length = temp.length();
      int _minus = (_length - 2);
      temp = temp.substring(0, _minus);
    }
    boolean _startsWith = temp.startsWith(", ");
    if (_startsWith) {
      temp = temp.substring(2);
    }
    boolean _startsWith_1 = temp.startsWith("; ");
    if (_startsWith_1) {
      temp = temp.substring(2);
    }
    boolean _endsWith_1 = temp.endsWith("; ");
    if (_endsWith_1) {
      int _length_1 = temp.length();
      int _minus_1 = (_length_1 - 2);
      temp = temp.substring(0, _minus_1);
    }
    return temp;
  }
}
