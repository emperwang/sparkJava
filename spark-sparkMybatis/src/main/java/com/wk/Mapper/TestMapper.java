package com.wk.Mapper;

import com.wk.entity.TestBean;

import java.util.List;

public interface TestMapper {
    int insertOne(TestBean bean);

    List<TestBean> selectAll();
}
