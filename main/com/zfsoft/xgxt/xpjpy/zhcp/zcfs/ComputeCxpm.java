/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-26 ����02:28:09 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcfs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������:�����۲��
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-26 ����02:28:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ComputeCxpm implements Runnable {

	private String xn;
	private String xq;
	private Log logger = LogFactory.getLog(ComputeCxpm.class);
	
	
	public ComputeCxpm(String xn , String xq){
		this.xn = xn;
		this.xq = xq;
	}
	
	/*
	      ����: @see java.lang.Runnable#run()
	 */

	public void run() {
		ZcfsDao zcfDao = new ZcfsDao();
		
		try {
			logger.debug("��ʼ����������......");
			
			zcfDao.cxJspm(xn, xq);
			
			logger.debug("����������ɣ�......");
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("��������ʧ�ܣ�......");
		}
	}

}
