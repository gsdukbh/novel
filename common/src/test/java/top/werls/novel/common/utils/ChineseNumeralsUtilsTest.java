package top.werls.novel.common.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jiawei Lee
 * @version TODO
 * @date Date: 2022/9/9
 * @since on
 */
class ChineseNumeralsUtilsTest {

  @Test
  void chineseNumeralsToNum() {
    var n = "一千零三万五十";
    var s= ChineseNumeralsUtils.ChineseNumeralsToNum(n);
    System.out.println(s);
  }
}
