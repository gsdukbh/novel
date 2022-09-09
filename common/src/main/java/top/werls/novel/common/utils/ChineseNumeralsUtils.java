package top.werls.novel.common.utils;

import java.util.*;

/**
 * @author Jiawei Lee
 * @version TODO
 * @date Date: 2022/9/9
 * @since on
 */
public class ChineseNumeralsUtils {
  /** 中文数字与阿拉伯数字对应 */
  private static final Map<String, Integer> CN_NUM =
      Map.of("零",0,"一", 1, "二", 2, "三", 3, "四", 4, "五", 5, "六", 6, "七", 7, "八", 8, "九", 9);

  private static final Map<String, Integer> CN_UNIT =
      Map.of("十", 10, "百", 100, "千", 1000, "万", 10000, "亿", 1000000000);

  public static long ChineseNumeralsToNum(String ChineseNumerals) {
    Deque<Long> stack = new ArrayDeque<>();
    for (int i = 0; i < ChineseNumerals.length(); i++) {
      String str = String.valueOf(ChineseNumerals.charAt(i));
      // 是否时单位符号
      if (CN_UNIT.containsKey(str)) {
        long temp = CN_UNIT.get(str);
        long num = 0;
        //当单位大小小于前者时, 累计前面的和
        while (!stack.isEmpty() && stack.peek() < temp) {
          num += stack.pop();
        }
        // 对零和十 单位处理
        if (stack.isEmpty() && num == 0 && "十".equals(str)) {
          stack.push(10L);
        } else {
          stack.push(num * temp);
        }
      } else {
        //入栈
        int num = CN_NUM.get(str);
        stack.push((long) num);
      }
    }
    long ans = 0;
    while (!stack.isEmpty()) {
      ans += stack.pop();
    }

    return ans;
  }

}
