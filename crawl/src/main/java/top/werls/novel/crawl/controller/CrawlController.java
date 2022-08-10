package top.werls.novel.crawl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.werls.novel.common.ResultData;
import top.werls.novel.common.entity.BookChapter;
import top.werls.novel.crawl.service.CrawlService;
import top.werls.novel.crawl.vo.BookChapterVo;
import top.werls.novel.crawl.vo.SearchVO;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * date created 2022/7/24
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
@RestController
@Slf4j
@Tag(name = "爬虫主要接口", description = "获取小说数据")
public class CrawlController {

  @Resource private CrawlService crawlService;

  @Operation(summary = "搜索小说")
  @RequestMapping("/s/{word}/{page:\\d+}")
  public ResultData<List<SearchVO>> search(@PathVariable String word, @PathVariable int page)
      throws IOException {
    List<SearchVO> res = crawlService.getSearch(word, page);
    return ResultData.success(res);
  }

  @Operation(summary = "解析图书", description = "返回小说，以及章节信息，不包含内容")
  @RequestMapping("/b/{url}")
  public ResultData<BookChapterVo> getBookInfo(@PathVariable String url) throws IOException {
    var res = crawlService.getBookInfo(url);
    return ResultData.success(res);
  }

  @Operation(summary = "解析章节", description = "返回章节内容， 需要单个章节的url")
  @RequestMapping("/c/{url}")
  public  ResultData<BookChapter> getChapter(@PathVariable String url){
    return ResultData.success();
  }

}
