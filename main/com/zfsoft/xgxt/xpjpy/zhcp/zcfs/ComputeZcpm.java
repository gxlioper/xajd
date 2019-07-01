/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-26 ����02:28:09 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcfs;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xgxt.action.Base;

import com.zfsoft.xgxt.comm.task.TaskHandler;
import com.zfsoft.xgxt.xpjpy.cpmd.CpmdDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.CheckConditionsTask;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������:�����۲��
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-26 ����02:28:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ComputeZcpm implements Runnable {

	private String xn;
	private String xq;
	private String bjdm;
	private Log logger = LogFactory.getLog(ComputeZcpm.class);
	
	
	public ComputeZcpm(String xn , String xq ,String bjdm){
		this.xn = xn;
		this.xq = xq;
		this.bjdm = bjdm;
	}
	
	/*
	      ����: @see java.lang.Runnable#run()
	 */

	public void run() {
		ZcfsDao zcfDao = new ZcfsDao();
		CsszDao csszDao = new CsszDao();
		
		try {
			CsszModel csszModel = csszDao.getModel();

			logger.debug("��ʼ�����۲�ּ��������༶����"+bjdm+"����......");
			//���꼶���������
			if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())){
				zcfDao.computeZcpmByNj(xn, xq, bjdm);
			} else if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())){
				//��ѧԺ���������
				zcfDao.computeZcpmByXy(xn, xq, bjdm);
			} else {
				//�����꼶 ��ѧԺ��ȫ�����һ��
				zcfDao.computeZcpm(xn, xq, bjdm);
			}
			
			//����ѧ���۲�
			if(CsszService.getZczq()){
				zcfDao.computeXnpm(xn,bjdm);
				logger.debug("ѧ��ּ�����������ɣ��༶����"+bjdm+"����......");
			}
			
			logger.debug("�۲�ּ�����������ɣ��༶����"+bjdm+"����......");
			
			// ����ƽ�������Σ��㽭����ְҵ����ѧԺ��
			if("12861".equals(Base.xxdm)){
				zcfDao.computeZYPDB_ZJJDZYJSXY(xn, xq, bjdm);
				logger.debug("�㽭����ְҵ����ѧԺ:ƽ�������μ�����ɣ��༶����"+bjdm+"����......");
			}
			
			//�����������
			CpmdDao cpmdDao = new CpmdDao();
			String[] bjdmArr = bjdm.split(",");
			List<HashMap<String,String>> cpmdList = cpmdDao.getCpmdList(bjdmArr, csszModel.getXn(), csszModel.getXq());
			if (cpmdList != null && !cpmdList.isEmpty()){
				CheckConditionsTask task = new CheckConditionsTask(cpmdList);
				TaskHandler handler = TaskHandler.getInstance();
				handler.startup("pjpy");
				handler.addTask("pjpy", bjdm, task);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("�����۲�ּ�����ʧ�ܣ��༶����"+bjdm+"����......");
		}
	}

}
