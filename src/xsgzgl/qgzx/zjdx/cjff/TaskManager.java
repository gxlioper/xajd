/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-19 ����10:13:26 
 */  
package xsgzgl.qgzx.zjdx.cjff;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zfsoft.xgxt.qgzx.xsgw.XsgwsqTimeOutTask;
import org.jfree.util.Log;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-19 ����10:13:26 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TaskManager implements ServletContextListener {
	 /**                               
	  * ÿ��ĺ�����                               
	  */                               
	 public static final long PERIOD_DAY = 10*60* 1000; //��ʼ��10����                               
	 /**                               
	  * ���ӳ�                               
	  */                               
	 public static final long NO_DELAY = 0;
	 /**                               
	  * ��ʱ��                               
	  */                               
	 private Timer timer;

	 private Timer timer2;

	/*
	      ����: @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO �Զ����ɷ������
//		timer.cancel();
		timer2.cancel();
	}

	/*
	      ����: @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO �Զ����ɷ������
		
//		timer = new Timer("����ڹ���ѧ�Զ��ύ��ʱ����",true);
//		timer.schedule(new AutoSubmitTask(), NO_DELAY, PERIOD_DAY);

		timer2 = new Timer("������ѧ���ڹ���λ����ʧЧ��ʱ����",true);
		timer2.schedule(new XsgwsqTimeOutTask(),NO_DELAY,1*60*1000);
	}

}
