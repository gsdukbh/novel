package top.werls.novel.web.controller;


import jakarta.annotation.Resource;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.werls.novel.crawl.service.CrawlService;
import top.werls.novel.crawl.vo.BookChapterVo;
import top.werls.novel.crawl.vo.SearchVO;

/**
 * @author Li JiaWei
 * @version TODO
 * @date 2023/3/14
 * @since on
 */
@Controller
@RequestMapping("/web")
@Slf4j
public class WebController {

  @Resource
  private CrawlService crawlService;

  @RequestMapping()
  public String index(Model model) {
    log.info("  zrun  ");
    model.addAttribute("google", "google");
    return "index";
  }

  @RequestMapping("/search/{novelName}/{page:\\d+}")
  public String search(@PathVariable(value = "novelName") String novelName,
      @PathVariable(value = "page") int page, Model model)
      throws Exception {

    List<SearchVO> res = crawlService.getSearch(novelName, page);

    model.addAttribute("list", res);
    model.addAttribute("page", page);
    model.addAttribute("novelName", novelName);
    return "index";
  }

  @RequestMapping("/b/{url}")
  public String bookInfo(@PathVariable String url,Model model)  {

    try {
      BookChapterVo res = crawlService.getBookInfo(url);

      model.addAttribute("book", res);

    } catch (IOException e) {
      log.info( " 解析失败");
      model.addAttribute("info",false);
    }
    return "book";
  }
}
