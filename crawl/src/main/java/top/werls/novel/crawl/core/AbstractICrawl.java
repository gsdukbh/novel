package top.werls.novel.crawl.core;

import java.io.Serial;
import java.io.Serializable;

/**
 * 抽象类
 * date created 2022/8/1
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
public abstract class AbstractICrawl implements ICrawl {
    @Serial
    private static final long serialVersionUID = 3180820087507254L;

    /**
     * 网址
     */
    protected  String url;

    /**
     * User-Agent
     */
    protected  String ua;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }
}
