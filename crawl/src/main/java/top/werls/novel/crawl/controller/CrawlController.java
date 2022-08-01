package top.werls.novel.crawl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.werls.novel.common.ResultData;
import top.werls.novel.crawl.service.CrawlService;
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
public class CrawlController {

  @Resource private CrawlService crawlService;

  @RequestMapping("/s/{word}/{page:\\d+}")
  public ResultData<List<SearchVO>> search(@PathVariable String word, @PathVariable int page)
      throws IOException {
    List<SearchVO> res = crawlService.getSearch(word, page);
    return ResultData.success(res);
  }
}
