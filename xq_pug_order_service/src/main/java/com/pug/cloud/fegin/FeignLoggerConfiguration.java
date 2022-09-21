package com.pug.cloud.fegin;

import feign.Logger;
import org.springframework.context.annotation.Bean;

//@Configuration
//这里切记，不要去加@Configuration否则就会出现，重复扫描的问题。如果加了就必须放在主类以外的包下即可。
//否则就是全局，会影响所有的feign接口
public class FeignLoggerConfiguration {

    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }
}
