/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014��6��9�� ����1:53:50 
 */  
package com.zfsoft.xgxt.comm.shlc.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.CsszDao;
import com.zfsoft.xgxt.comm.shlc.model.CsszModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ͨ���������-��������
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014��6��9�� ����1:53:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszService extends SuperServiceImpl<CsszModel, CsszDao> {

	//Ĭ�����뿪��
	private static final boolean DEFAULT_SQKG = false;
	//Ĭ����˿���
	private static final boolean DEFAULT_SHKG = false;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
	
	
	/**
	 * 
	 * @����: �ж��Ƿ��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��9�� ����3:03:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public  boolean isAllowApply(String id){
		
		try {
			CsszModel model = dao.getModel(id);
			
			//δ���ã���Ĭ�Ͽ��ش���
			if (model == null){
				return DEFAULT_SQKG;
			}
			
			//δ��������
			if (Constants.CLOSE.equals(model.getSqkg())){
				return false;
			}
			
			
			//���뿪ʼʱ�䲻Ϊ�գ�����ʱ��Ϊ��
			if (!StringUtil.isNull(model.getSqkssj()) && StringUtil.isNull(model.getSqjssj())){
				Date kssj = sdf.parse(model.getSqkssj());
				return new Date().after(kssj);
			}
			
			//�������ʱ�䲻Ϊ�գ���ʼʱ��Ϊ��
			if (!StringUtil.isNull(model.getSqjssj()) && StringUtil.isNull(model.getSqkssj())){
				Date jssj = sdf.parse(model.getSqjssj());
				return new Date().before(jssj);
			}
			
			//�Ƚϵ�ǰ�����Ƿ��ڿ�ʼ������ʱ����
			Date kssj = sdf.parse(model.getSqkssj());
			Date jssj = sdf.parse(model.getSqjssj());
			Date dqsj = new Date();
			
			return dqsj.after(kssj) && dqsj.before(jssj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return DEFAULT_SQKG;
	} 
	
	
	/**
	 * 
	 * @����: �ж��Ƿ�������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��9�� ����3:04:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isAllowAudit(String id){
		
		try {
			CsszModel model = dao.getModel(id);
			
			//δ���ã���Ĭ�Ͽ��ش���
			if (model == null){
				return DEFAULT_SHKG;
			}
			
			//δ�������
			if (Constants.CLOSE.equals(model.getShkg())){
				return false;
			}
			
			//��˿�ʼʱ�䲻Ϊ�գ����ʱ��Ϊ��
			if (!StringUtil.isNull(model.getShkssj()) && StringUtil.isNull(model.getShjssj())){
				Date kssj = sdf.parse(model.getShkssj());
				return new Date().after(kssj);
			}
			
			//��˽���ʱ�䲻Ϊ�գ���ʼʱ��Ϊ��
			if (!StringUtil.isNull(model.getShjssj()) && StringUtil.isNull(model.getShkssj())){
				Date jssj = sdf.parse(model.getShjssj());
				return new Date().before(jssj);
			}
			
			//�Ƚϵ�ǰ�����Ƿ��ڿ�ʼ������ʱ����
			Date kssj = sdf.parse(model.getShkssj());
			Date jssj = sdf.parse(model.getShjssj());
			Date dqsj = new Date();
			
			return dqsj.after(kssj) && dqsj.before(jssj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return DEFAULT_SHKG;
	}
}
