package top.werls.novel.crawl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.werls.novel.common.ResultData;

import top.werls.novel.crawl.entity.BookChapter;
import top.werls.novel.crawl.entity.CrawlEncode;
import top.werls.novel.crawl.service.CrawlEncodeService;
import top.werls.novel.crawl.service.CrawlService;
import top.werls.novel.crawl.vo.BookChapterVo;
import top.werls.novel.crawl.vo.SearchVO;


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

  private final String admin = "/admin/crawl";

  @Resource
  private CrawlService crawlService;

  @Resource
  private CrawlEncodeService crawlEncodeService;

  @Operation(summary = "搜索小说")
  @GetMapping("/s/{word}/{page:\\d+}")
  public ResultData<List<SearchVO>> search(@PathVariable String word, @PathVariable int page)
      throws IOException {
    List<SearchVO> res = crawlService.getSearch(word);
    return ResultData.success(res);
  }

  @Operation(summary = "解析图书", description = "返回小说，以及章节信息，不包含内容")
  @GetMapping("/b/{url}")
  public ResultData<BookChapterVo> getBookInfo(@PathVariable String url) throws IOException {
    var res = crawlService.getBookInfo(url);
    return ResultData.success(res);
  }

  @Operation(summary = "解析章节", description = "返回章节内容， 需要单个章节的url")
  @GetMapping("/c/{url}")
  public ResultData<BookChapter> getChapter(@PathVariable String url) throws IOException {
    var res = crawlService.getChapter(url);
    return ResultData.success(res);
  }

  @Operation(summary = "保存CrawEncode", description = "保存CrawEncode")
  @PostMapping(admin + "/save")
  public ResultData<String> saveCrawEncode(@Validated @RequestBody CrawlEncode crawlEncode) {
    crawlEncodeService.save(crawlEncode);
    return ResultData.success();
  }
  @Operation(summary = "获取CrawlEncode列表", description = "获取CrawlEncode列表")
  @GetMapping(admin+"/list/{size:[\\d+]}/{page:[\\d+]}")
  public ResultData<Page<CrawlEncode>> list( @PathVariable("page") Integer page,@PathVariable("size") Integer pageSize){

   var tesm= crawlEncodeService.list(page,pageSize );
    return  ResultData.success(tesm);
  }
}
