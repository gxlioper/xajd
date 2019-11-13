package com.zfsoft.xgxt.hdgl.hdxx;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

/**
 * 定时任务
 */
public class HdListener implements ServletContextListener {
    HdxxService service = new HdxxService();


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Timer ppTimer = new Timer(); // 建立派票Timer对象
        ppTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                service.pp();
            }
        },0, 10*60 * 1000);//10分钟执行一次

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
