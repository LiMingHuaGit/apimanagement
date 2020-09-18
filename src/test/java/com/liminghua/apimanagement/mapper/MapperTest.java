package com.liminghua.apimanagement.mapper;

import cn.hutool.core.collection.CollUtil;
import com.liminghua.apimanagement.ApimanagementApplicationTests;
import com.liminghua.apimanagement.entity.Api;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description: 测试Mapper
 * @Author LiMinghua
 * @Date 2020/5/7
 * @Version V1.0
 **/
@Slf4j
public class MapperTest extends ApimanagementApplicationTests {

    @Autowired
    private ApiMapper apiMapper;

    @Test
    public void selectAll() {
        List<Api> allInterface = apiMapper.getAllInterface();
        Assert.assertTrue(CollUtil.isNotEmpty(allInterface));
        log.debug("【allInterface】= {}", allInterface);
    }
}
