/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-19 ����10:46:23 
 */  
package xsgzgl.qgzx.zjdx.cjff;

import java.util.HashMap;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ڹ���ѧ��ʱ�ύִ������
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-19 ����10:46:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class AutoSubmitTask extends TimerTask {
	private static Log log = LogFactory.getLog(AutoSubmitTask.class);       
	 private static boolean isRunning = false;
	 private CjffService service = new CjffService();
	/*
	      ����: @see java.util.TimerTask#run()
	 */

	@Override
	public void run() {
		// TODO �Զ����ɷ������
		 if (!isRunning) {
			   isRunning = true;
			   log.debug("��ʼ��ʱ�ύִ������..."); //��ʼ����
			 try {
				//��֤�Ƿ��ڳ�𷢷ſ���ʱ����ڣ��Ӷ��õ���������
				HashMap<String, String> csszMap = service.getCsszMap();
				int kssj = Integer.parseInt(csszMap.get("kssj"));
				int jssj = Integer.parseInt(csszMap.get("jssj"));
				boolean sfkq = service.checkIsInKfsjd(kssj, jssj);
				if(!sfkq){
					new CjffDao().updateWtjsj();
				}
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				log.debug("�ύ��¼ʧ�ܣ��ύʱ�������ݳ���..."); 
				e.printStackTrace();
			}
			   log.debug("��ʱ�ύִ���������..."); //�������
			   isRunning = false;
	    } else {
			   log.debug("��һ������ִ�л�δ����..."); //��һ������ִ�л�δ����
	     }
	}

}
