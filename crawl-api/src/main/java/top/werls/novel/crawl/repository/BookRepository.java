package top.werls.novel.crawl.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import top.werls.novel.crawl.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer>,
    JpaSpecificationExecutor<Book> {

}