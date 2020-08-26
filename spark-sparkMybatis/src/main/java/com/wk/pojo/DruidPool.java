package com.wk.pojo;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class DruidPool extends UnpooledDataSourceFactory {

    public DruidPool() {
        this.dataSource = dataSource();
    }

    public DataSource dataSource() {
        DruidDataSource source = new DruidDataSource();
        WallFilter wallFilter = new WallFilter();
        WallConfig wallConfig = new WallConfig();
        // 允许执行多个语句
        wallConfig.setMultiStatementAllow(true);
        // 允许一次执行多条语句
        wallConfig.setNoneBaseStatementAllow(true);
        wallFilter.setConfig(wallConfig);

        List<Filter> filters = new ArrayList<>();
        filters.add(wallFilter);
        source.setProxyFilters(filters);
        return source;
    }
}
