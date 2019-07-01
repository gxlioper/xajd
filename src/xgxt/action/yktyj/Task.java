/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-3-21 ����09:53:57 
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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:ϵͳ���á�ϵͳ��ʼ����һ��ͨԤ��������ʦ�����Ի���
 * �Զ�ִ������
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� lgx[����:1553]
 * @ʱ�䣺 2018-3-21 ����09:53:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class Task  implements ServletContextListener {

	/**                               
	  * ÿ��ĺ�����                               
	  */                               
	 public static final long PERIOD_DAY = 60*1000*60*24; //��ʼ��24Сʱ 
	//public static final long PERIOD_DAY = 60*1000*2; //��ʼ��24Сʱ             
	 /**                               
	  * ���ӳ�                               
	  */                               
	 public static final long NO_DELAY = 0;
	 /**                               
	  * ��ʱ��                               
	  */                               
	 private Timer timer;         
	
	 /**                               
	  * ֹͣ��ʱ��                               
	  */ 
	public void contextDestroyed(ServletContextEvent arg0) {
		timer.cancel();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		timer = new Timer("����ʦ��һ��ͨԤ����ʱ����",true);
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
			//��ǰʱ�䳬��2�㣬������һ���2����Ϊ��ʼʱ��
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
