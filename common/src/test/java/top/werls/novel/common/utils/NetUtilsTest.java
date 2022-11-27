package top.werls.novel.common.utils;

import org.junit.jupiter.api.Test;

/**
 * date created 2022/8/2
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
class NetUtilsTest {

  @Test
  void getDomainUrl() {
    var domain = NetUtils.getDomainUrl("https://www.b520.cc/52_52542/");
    assert  domain.equalsIgnoreCase("www.b520.cc");
  }
}
