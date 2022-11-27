package top.werls.novel.common.utils;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    var s = ChineseNumeralsUtils.ChineseNumeralsToNum(n);
    var  tem =ChineseNumeralsUtils.numToChineseNumerals(s.toString());
    assertEquals(10030050,s.longValue(),"err ChineseNumeralsUtils.ChineseNumeralsToNum fail");
    assertEquals(tem[1],"一千零三万零五十","err ");
    assertEquals(tem[0],"壹千零叁万零伍十","err ");
  }
}
