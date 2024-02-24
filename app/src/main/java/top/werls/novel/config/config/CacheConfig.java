package top.werls.novel.config.config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.cache2k.extra.spring.SpringCache2kCacheManager;
import org.springframework.boot.autoconfigure.cache.Cache2kBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 内存缓存设置
 *
 * @author JiaWei Lee
 * @version 1
 * @date 2024/2/24
 * @since on   2024/2/24
 */

@Configuration
@EnableCaching
public class CacheConfig {

 private static final int maxSize = 1000 * 1000;

  @Bean
  public CacheManager cacheManager() {
    return new SpringCache2kCacheManager()
        .defaultSetup(b -> b.entryCapacity(maxSize).expireAfterWrite(5, TimeUnit.HOURS));
  }
}
