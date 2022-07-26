package top.werls.novel.crawl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.werls.novel.common.ResultData;
import top.werls.novel.crawl.service.CrawlService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.print.Book;

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

    @Resource
    private CrawlService crawlService;


    @RequestMapping("/s/{word}/{page:\\d+}")
    public ResultData<?> search (HttpServletRequest request, @PathVariable String word, @PathVariable int page){

        return  ResultData.success();
    }

}
