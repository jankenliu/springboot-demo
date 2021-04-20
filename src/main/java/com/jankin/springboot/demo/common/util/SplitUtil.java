package com.jankin.springboot.demo.common.util;

import com.google.common.collect.Lists;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 分页工具
 *
 * @author xiaoyue
 * @version 1.0
 * @date 2020/6/30 3:20 下午
 */
public class SplitUtil {

    public static  <T> List<List<T>> splitByPage(int batchSize, List<T> param) {
        List<List<T>> res = Lists.newArrayList();
        int size = param.size();
        Assert.isTrue(size > 0 && batchSize > 0);
        int count = (size - 1) / batchSize + 1;
        for (int i = 0; i < count; i++) {
            int fromIndex = i * batchSize;
            int toIndex = Math.min(fromIndex + batchSize, size);
            res.add(param.subList(fromIndex, toIndex));
        }
        return res;
    }
}
