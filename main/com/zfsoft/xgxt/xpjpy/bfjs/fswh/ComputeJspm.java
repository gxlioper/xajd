/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-26 ����02:28:09 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.fswh;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������:���㾺����
 * @���ߣ� xiaxia [���ţ�1104]
 * @ʱ�䣺 2016-4-19 ����02:28:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ComputeJspm implements Runnable {

	private String xn;
	private String xq;
	private String bjdm;
	private Log logger = LogFactory.getLog(ComputeJspm.class);
	
	
	public ComputeJspm(String xn , String xq ,String bjdm){
		this.xn = xn;
		this.xq = xq;
		this.bjdm = bjdm;
	}
	
	/*
	      ����: @see java.lang.Runnable#run()
	 */

	public void run() {
		BfjsFswhDao jsfDao = new BfjsFswhDao();
		
		try {

			logger.debug("��ʼ���㾺���ּ��������༶����"+bjdm+"����......");
			
//				//��ѧԺ����
				jsfDao.computeJspmByXy(xn, xq, bjdm);
		
			logger.debug("�����ּ�����������ɣ��༶����"+bjdm+"����......");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("���㾺���ּ�����ʧ�ܣ��༶����"+bjdm+"����......");
		}
	}

}
