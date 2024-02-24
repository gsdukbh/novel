package top.werls.novel.crawl.data;

import java.util.Set;

/**
 * 配置爬取规则，过滤站点等
 *
 * @author JiaWei Lee
 * @version 1
 * @date 2024/2/24
 * @since on   2024/2/24
 */

public class Rules {

  private static final String[] BLACK_DOMAIN1 = {
      "www.17k.com", "mm.17k.com", "www.xs8.cn", "www.zongheng.com", "yunqi.qq.com",
      "chuangshi.qq.com",
      "book.qidian.com", "www.soduso.com", "pages.book.qq.com", "book.km.com", "www.lread.net",
      "www.0dsw.com", "www.5200xsb.com", "www.80txt.com", "www.sodu.tw", "www.shuquge.com",
      "www.shenmanhua.com", "xiaoshuo.sogou.com", "www.999wx.com", "zetianji8.com",
      "www.bookso.net",
      "m.23us.com", "www.qbxsw.com", "www.zhuzhudao.com", "www.shengyan.org", "www.360doc.com",
      "www.ishuo.cn", "read.qidian.com", "www.yunlaige.com", "www.qidian.com", "www.sodu888.com",
      "www.siluke.cc", "read.10086.cn", "www.pbtxt.com", "c4txt.com", "www.bokon.net",
      "www.sikushu.net",
      "www.is028.cn", "www.tadu.com", "www.kudu8.com", "www.bmwen.com", "www.5858xs.com",
      "www.yiwan.com",
      "www.x81zw.com", "www.123du.cc", "www.chashu.cc", "20xs.com", "www.haxwx.net",
      "www.dushiwenxue.com",
      "www.yxdown.com", "www.jingcaiyuedu.com", "www.zhetian.org", "www.xiaoshuo02.com",
      "www.xiaoshuo77.com",
      "www.868xh.com", "dp.changyou.com", "www.iyouman.com", "www.qq717.com", "www.yznn.com",
      "www.69w.cc",
      "www.doupocangqiong1.com", "www.manhuatai.com", "www.5wxs.com", "www.ggshuji.com",
      "www.msxf.net",
      "www.mianhuatang.la", "www.boluoxs.com", "www.lbiquge.top", "www.69shu.com",
      "www.qingkan520.com",
      "book.douban.com", "movie.douban.com", "www.txshuku.com", "lz.book.sohu.com",
      "www.3gsc.com.cn",
      "www.txtshu365.com", "www.517yuedu.com", "www.baike.com", "read.jd.com", "www.zhihu.com",
      "wshuyi.com",
      "www.19lou.tw", "www.chenwangbook.com", "www.aqtxt.com", "book.114la.com", "www.niepo.net",
      "me.qidian.com", "www.gengd.com", "www.77l.com", "www.geilwx.com", "www.97xiao.com",
      "www.anqu.com",
      "www.wuxiaxs.com", "yuedu.163.com", "b.faloo.com", "bbs.qidian.com", "jingji.qidian.com",
      "www.sodu.cc",
      "forum.qdmm.com", "www.qdmm.com", "game.91.com", "www.11773.com", "mt.sohu.com",
      "book.dajianet.com",
      "haokan.17k.com", "www.qmdsj.com", "www.jjwxc.net", "ishare.iask.sina.com.cn",
      "www.cmread.com",
      "www.52ranwen.net", "www.dingdianzw.com", "www.topber.com", "www.391k.com", "www.qqxzb.com",
      "www.zojpw.com", "www.pp8.com", "www.bxwx.org", "www.hrsxb.com", "www.497.com",
      "www.d8qu.com",
      "www.duwanjuan.com", "www.05935.com", "book.zongheng.com", "www.55x.cn", "www.freexs.cn",
      "xiaoshuo.360.cn", "www.3kw.cc", "www.gzbpi.com", "book.sina.com.cn", "www.vodtw.com",
      "wenda.so.com",
      "product.dangdang.com", "www.chuiyao.com", "novel.slieny.com", "www.bilibili.com",
      "donghua.dmzj.com",
      "www.yaojingweiba.com", "www.qb5200.com", "www.520tingshu.com", "www.567zw.com",
      "www.zjrxz.com",
      "v.qq.com", "blog.sina.com.cn", "www.hackhome.com", "news.fznews.com.cn", "www.jingyu.com",
      "news.so.com", "www.sodu3.com", "vipreader.qidian.com", "www.mozhua9.com", "www.iqiyi.com",
      "xs.sogou.com"
  };
  public static final Set<String> BLACK_DOMAIN= Set.of(BLACK_DOMAIN1);


}
