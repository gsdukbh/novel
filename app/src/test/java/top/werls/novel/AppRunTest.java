package top.werls.novel;

import jakarta.annotation.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.springframework.boot.test.context.SpringBootTest;
import top.werls.novel.systemapi.repository.SysRoleRepository;

/**
 * @author Jiawei Lee
 * @version TODO
 * @since on 2022/6/30
 */
@SpringBootTest
class AppRunTest {
  @Resource
  private SysRoleRepository sysRoleRepository;
  @Test
  void  t(){
    var  a = sysRoleRepository.findByCode("ROLE_ADMIN");
  }
}
