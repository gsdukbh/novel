package top.werls.novel.crawl.service;

import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import top.werls.novel.crawl.entity.CrawlEncode;
import top.werls.novel.crawl.repository.CrawlEncodeRepository;

/**
 * @author Li JiaWei
 * @version TODO
 * @date 2023/3/7
 * @since on
 */
@Service
@Slf4j
public class CrawlEncodeServiceImpl implements CrawlEncodeService {

  @Resource
  private CrawlEncodeRepository crawlEncodeRepository;

  @Override
  public void save(CrawlEncode crawlEncode) {
    crawlEncodeRepository.save(crawlEncode);
  }

  @Override
  public Page<CrawlEncode> list(int page, int pageSize) {
    Pageable pageable = PageRequest.of(page, pageSize, Direction.DESC);
    return crawlEncodeRepository.findAll(pageable);
  }


}
