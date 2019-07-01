/**
 * @部门:学工产品事业部
 * @日期：2018-3-21 上午09:53:57 
 */  
package xgxt.action.yktyj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import xsgzgl.qgzx.zjdx.cjff.AutoSubmitTask;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称:系统设置—系统初始化—一卡通预警（杭州师范个性化）
 * 自动执行设置
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： lgx[工号:1553]
 * @时间： 2018-3-21 上午09:53:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class Task  implements ServletContextListener {

	/**                               
	  * 每天的毫秒数                               
	  */                               
	 public static final long PERIOD_DAY = 60*1000*60*24; //初始化24小时 
	//public static final long PERIOD_DAY = 60*1000*2; //初始化24小时             
	 /**                               
	  * 无延迟                               
	  */                               
	 public static final long NO_DELAY = 0;
	 /**                               
	  * 定时器                               
	  */                               
	 private Timer timer;         
	
	 /**                               
	  * 停止定时器                               
	  */ 
	public void contextDestroyed(ServletContextEvent arg0) {
		timer.cancel();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		timer = new Timer("杭州师范一卡通预警定时任务",true);
		//timer.schedule(new AutoTask(), NO_DELAY, PERIOD_DAY);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd 02:00:00");  
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
		
		String  s = formatter.format(new Date());
		Calendar c = Calendar.getInstance();  
		int hour = c.get(Calendar.HOUR_OF_DAY);
		System.out.println("hour:"+hour);
		Date startDate = null;
		try {
			
			c.setTime(dateFormatter.parse(s));
			//当前时间超过2点，就以下一天的2点作为开始时间
			if(hour > 2){
				 c.add(Calendar.DAY_OF_MONTH, 1);
			}
			startDate = c.getTime();
			System.out.println("start:"+startDate);
			timer.scheduleAtFixedRate(new AutoTask(), startDate, PERIOD_DAY);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
}
