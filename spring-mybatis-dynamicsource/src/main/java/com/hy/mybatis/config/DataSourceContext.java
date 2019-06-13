package com.hy.mybatis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceContext {

    private static Logger logger = LoggerFactory.getLogger(DataSourceContext.class);

    private static final ThreadLocal<String> source = new ThreadLocal<String>();

    public static String getSource() {
        if(logger.isDebugEnabled()){
            logger.debug("get datasource ====>" + source.get());
        }
        return source.get();
    }

    public static void setSource(String datasource){
        if(logger.isDebugEnabled()){
            logger.debug("set datasource ====>" + datasource);
        }
        source.set(datasource);
    }
}
