package top.werls.novel.crawl.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import top.werls.novel.crawl.entity.CrawlEncode;


import java.util.Optional;

@Repository
public interface CrawlEncodeRepository extends ListPagingAndSortingRepository<CrawlEncode, Integer>,
    JpaSpecificationExecutor<CrawlEncode>, CrudRepository<CrawlEncode, Integer> {

  /**
   * 获取网站的解析模板数据
   *
   * @param site 网站url
   * @return CrawlEncode Optional
   */
  @Query("select c from CrawlEncode c where c.site = ?1")
  Optional<CrawlEncode> findBySite(String site);
}