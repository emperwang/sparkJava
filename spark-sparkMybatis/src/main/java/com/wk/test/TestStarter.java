package com.wk.test;

import com.wk.Mapper.TestMapper;
import com.wk.entity.TestBean;
import com.wk.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TestStarter {
    public static void main(String[] args) {
        insertOne();
        GetAll();
    }

    public static void insertOne(){
        SqlSession session = SessionUtil.getSession();
        TestMapper mapper = session.getMapper(TestMapper.class);
        TestBean bean = new TestBean();
        bean.setName("emper1");
        bean.setDescription("this is first record");
        mapper.insertOne(bean);
        session.commit();
        session.close();
    }

    public static void GetAll(){
        SqlSession session = SessionUtil.getSession();
        TestMapper mapper = session.getMapper(TestMapper.class);
        List<TestBean> testBeans = mapper.selectAll();
        System.out.println(testBeans.toString());
        session.close();
    }
}
