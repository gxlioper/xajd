package com.zfsoft.xgxt.hdgl.hdxx;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

/**
 * ��ʱ����
 */
public class HdListener implements ServletContextListener {
    HdxxService service = new HdxxService();


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Timer ppTimer = new Timer(); // ������ƱTimer����
        ppTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                service.pp();
            }
        },0, 10*60 * 1000);//10����ִ��һ��

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
