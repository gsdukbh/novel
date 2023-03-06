package top.werls.novel.crawl.repository;

import org.springframework.data.geo.GeoResult;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import top.werls.novel.crawl.entity.BookChapter;

public interface BookChapterRepository extends CrudRepository<BookChapter, Integer>,
    JpaSpecificationExecutor<BookChapter> {

  @Query("select b from BookChapter b ,Book a  where b.bid = ?1 and a.name=''")
  GeoResult<BookChapter> findByBid(Integer bid);



}