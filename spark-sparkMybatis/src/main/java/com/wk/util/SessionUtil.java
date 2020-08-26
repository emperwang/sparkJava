package com.wk.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class SessionUtil {
    private static Reader reader;
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis-postgresql.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getCause());
        }
    }

    public static SqlSession getSession(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

}
