/**
 * @部门:学工产品事业部
 * @日期：2017-1-19 上午10:46:23 
 */  
package xsgzgl.qgzx.zjdx.cjff;

import java.util.HashMap;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 浙大勤工助学定时提交执行任务
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-1-19 上午10:46:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class AutoSubmitTask extends TimerTask {
	private static Log log = LogFactory.getLog(AutoSubmitTask.class);       
	 private static boolean isRunning = false;
	 private CjffService service = new CjffService();
	/*
	      描述: @see java.util.TimerTask#run()
	 */

	@Override
	public void run() {
		// TODO 自动生成方法存根
		 if (!isRunning) {
			   isRunning = true;
			   log.debug("开始定时提交执行任务..."); //开始任务
			 try {
				//验证是否在酬金发放开放时间段内，从而得到开发开关
				HashMap<String, String> csszMap = service.getCsszMap();
				int kssj = Integer.parseInt(csszMap.get("kssj"));
				int jssj = Integer.parseInt(csszMap.get("jssj"));
				boolean sfkq = service.checkIsInKfsjd(kssj, jssj);
				if(!sfkq){
					new CjffDao().updateWtjsj();
				}
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				log.debug("提交记录失败，提交时更新数据出错..."); 
				e.printStackTrace();
			}
			   log.debug("定时提交执行任务完成..."); //任务完成
			   isRunning = false;
	    } else {
			   log.debug("上一次任务执行还未结束..."); //上一次任务执行还未结束
	     }
	}

}
