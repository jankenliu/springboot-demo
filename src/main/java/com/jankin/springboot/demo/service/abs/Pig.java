package com.jankin.springboot.demo.service.abs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * @author lyy
 * @date 2020/9/1 6:52 下午
 */
@RefreshScope
@Service
@Slf4j
public class Pig extends AbstractAnimal{
    @Override
    public String name() {
        return "pig:"+envDirName();
    }
}
