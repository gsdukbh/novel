package top.werls.novel.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文本工具
 *
 * @author Jiawei Lee
 * @version TODO
 * @date Date: 2022/9/13
 * @since on
 */
public class TextUtils {
  public static String chapterNumber(String text) {
    String pattern = "第(.*)章";
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(text);
    if (m.find()) {
      return m.group(1);
    }
    return "";
  }
}
