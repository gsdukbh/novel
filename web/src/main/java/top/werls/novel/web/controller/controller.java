package top.werls.novel.web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Li JiaWei
 * @version TODO
 * @date 2023/3/14
 * @since on
 */
@Controller
@RequestMapping("/web")
@Slf4j
public class controller {


  @RequestMapping()
  public String index(Model model) {
    log.info("  zrun  ");
    model.addAttribute("google","google");
    return "index";
  }

  @RequestMapping("/search/{novelName}")
  public String search(@PathVariable(value = "novelName") String novelName){
    log.info( novelName);
    return "index";
  }
}
