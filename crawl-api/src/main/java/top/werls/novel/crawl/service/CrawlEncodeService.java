package top.werls.novel.crawl.service;

import java.util.List;
import org.springframework.data.domain.Page;
import top.werls.novel.crawl.entity.CrawlEncode;

/**
 *  CrawlEncode  服务
 * @author Li JiaWei
 * @version TODO
 * @date 2022/11/29
 * @since on
 */
public interface CrawlEncodeService {
  void save(CrawlEncode crawlEncode);

  Page<CrawlEncode> list(int page,int pageSize);
}
