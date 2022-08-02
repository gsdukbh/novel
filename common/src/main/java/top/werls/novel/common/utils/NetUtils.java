package top.werls.novel.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 网络工具 date created 2022/7/28
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
public class NetUtils {

  /**
   * 匹配 https://exe.com 地址
   *
   * @param str 文本
   * @return http/https:// 地址
   */
  public static String getDomainUrl(String str) {
    String pattern = "http(s)?://(([\\w-]+\\.)+\\w+(:\\d{1,5})?)";
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(str);
    if (m.find()) {
      return m.group(2);
    }
    return "";
  }
}
