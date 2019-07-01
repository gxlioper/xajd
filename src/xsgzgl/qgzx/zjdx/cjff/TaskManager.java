/**
 * @部门:学工产品事业部
 * @日期：2017-1-19 上午10:13:26 
 */  
package xsgzgl.qgzx.zjdx.cjff;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zfsoft.xgxt.qgzx.xsgw.XsgwsqTimeOutTask;
import org.jfree.util.Log;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-1-19 上午10:13:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TaskManager implements ServletContextListener {
	 /**                               
	  * 每天的毫秒数                               
	  */                               
	 public static final long PERIOD_DAY = 10*60* 1000; //初始化10分钟                               
	 /**                               
	  * 无延迟                               
	  */                               
	 public static final long NO_DELAY = 0;
	 /**                               
	  * 定时器                               
	  */                               
	 private Timer timer;

	 private Timer timer2;

	/*
	      描述: @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO 自动生成方法存根
//		timer.cancel();
		timer2.cancel();
	}

	/*
	      描述: @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO 自动生成方法存根
		
//		timer = new Timer("浙大勤工助学自动提交定时任务",true);
//		timer.schedule(new AutoSubmitTask(), NO_DELAY, PERIOD_DAY);

		timer2 = new Timer("西交大学生勤工岗位申请失效定时任务",true);
		timer2.schedule(new XsgwsqTimeOutTask(),NO_DELAY,1*60*1000);
	}

}
