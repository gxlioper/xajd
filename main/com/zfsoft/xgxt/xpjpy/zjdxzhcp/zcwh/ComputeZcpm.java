/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-7-13 ����09:20:47 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.zcwh;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zfsoft.xgxt.comm.task.TaskHandler;
import com.zfsoft.xgxt.xpjpy.wdpj.CheckConditionsTask;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������
 * @�๦������: �����۲�����
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-7-13 ����09:20:47 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ComputeZcpm implements Runnable {
	
	private String xn;
	private String xydm;

	private Log logger = LogFactory.getLog(ComputeZcpm.class);
	
	public ComputeZcpm(String xn,String xydm){
		this.xn = xn;
		this.xydm = xydm;
	}
	
	public void run() {
		ZcwhDao zcwhDao = new ZcwhDao();
		CsszDao csszDao = new CsszDao();
		try {
			CsszForm csszForm = csszDao.getModel();
			/*��������*/
			zcwhDao.computeZcpm(xn,xydm);
			
			logger.debug("�۲�ּ�����������ɣ��༶����"+xydm+"����......");
			
			/*�����������*/
			String[] xydmArr = xydm.split(",");
			List<HashMap<String,String>> cpmdList = zcwhDao.getCpmdList(xydmArr, csszForm.getXn());
			if (cpmdList != null && !cpmdList.isEmpty()){
				CheckConditionsTask task = new CheckConditionsTask(cpmdList);
				TaskHandler handler = TaskHandler.getInstance();
				handler.startup("pjpy");
				handler.addTask("pjpy", xydm, task);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.debug("�����۲�ּ�����ʧ�ܣ��༶����"+xydm+"����......");
		}
	}
}
