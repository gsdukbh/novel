package top.werls.novel.system.service.impl;

import org.springframework.stereotype.Component;
import top.werls.novel.demo.Mmo;

/**
 * @author Jiawei Lee
 * @version TODO
 * @date created 2022/7/7
 * @since on
 */
@Component
public class Mi implements Mmo {
    @Override
    public String getName() {
        return "holle ";
    }
}
