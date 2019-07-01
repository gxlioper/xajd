/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-27 ����02:53:53 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcjg;

import java.util.HashMap;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ����ģ��
 * @�๦������: ����ת��-���
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-27 ����02:54:21 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DazcjgService extends SuperServiceImpl<DazcjgForm,DazcjgDao>{
	
	/**
	 * @����: ������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-10 ����04:02:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveFormDazcjg(DazcjgForm model,User user) throws Exception{
		boolean rs = true;
		
		/*����Ψһ��ʶ��*/
		String guid = UniqID.getInstance().getUniqIDHash();
		
		/*�ж�Ψһ����ѧ��(xh),��Ŀ����(xmmc),����ʱ��(cysj)*/
		if(!this.checkIsNotRepeat(model)){
			throw new SystemException(MessageKey.SZTZ_XMSB_REPEAT);
		}
		
		/*��ǰ������Ա�û�������*/
		model.setSjlrr(user.getUserName());
		
		/*��ȡ��ǰ����ʱ��������У���ϲ����˹��ŷ�ֹ��ʦˣ��*/
		String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
		model.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
		
//		/*
//		 * �������ʱ������ת����Ϣ(dazcxx)ֱ�Ӳ塾1:�ѵǼǡ�
//		 * 
//		 * �������ʱ������ת����Ϣ(dazcxx)Ϊ��2:��ת����������:
//		 * 1��ת����ʽΪ�ʼģ����ʼ�״̬Ϊ���ʼ�
//		 * 2��ת����ʽΪ�Դ�����ʵ���ᵵ�˲�Ϊ��
//		 * 
//		 * �������ʱ������ת����Ϣ(dazcxx)Ϊ��1:�ѵǼǡ�������:
//		 * 1��ת����ʽΪ�ʼģ����ʼ�״̬Ϊδ�ʼ�
//		 */
//		if("1".equals(model.getZcfs()) && "1".equals(model.getYjzt())){
//			model.setDazcxx("2");
//		}else if("1".equals(model.getZcfs()) && "2".equals(model.getYjzt())){
//			model.setDazcxx("1");
//		}else if("2".equals(model.getZcfs()) && StringUtils.isNotNull(model.getSjtdr())){
//			model.setDazcxx("2");
//		}else{
//			model.setDazcxx("");
//		}
//		
		/*
		 * ����ת����Ϣ��1:�ѵǼǡ�2:��ת����3:δ�Ǽǡ�
		 * 1����ת����ʽΪ���ʼġ����ʼ�״̬Ϊ�����ʼġ�������ת����ϢΪ����ת����
		 * 2����ת����ʽΪ���ʼġ����ʼ�״̬Ϊ��δ�ʼġ�������ת����ϢΪ���ѵǼǡ�
		 * 3����ת����ʽΪ���Դ�����ʵ���ᵵ��Ϊ��δ�������ת����ϢΪ���ѵǼǡ�
		 * 4����ת����ʽΪ���Դ�����ʵ���ᵵ��Ϊ�����������ת����ϢΪ����ת����
		 */
		if("1".equals(model.getZcfs()) && "1".equals(model.getYjzt())){
			model.setDazcxx("2");
		}else if("1".equals(model.getZcfs()) && "2".equals(model.getYjzt())){
			model.setDazcxx("1");
		}else if("2".equals(model.getZcfs()) && StringUtils.isNull(model.getSjtdr())){
			model.setDazcxx("1");
		}else if("2".equals(model.getZcfs()) && StringUtils.isNotNull(model.getSjtdr())){
			model.setDazcxx("2");
		}else{
			model.setDazcxx("");
		}
		
		
		/*�жϸ������Ƿ�Ϊ�޸�����*/
		if(StringUtils.isNotNull(model.getGuid())){
			
			/*��ת����ʽΪ���ʼġ���ʱ���޸Ĳ���Ҫ���Դ���ŵ��ֵ��յ�*/
			if("1".equals(model.getZcfs())){
				model.setZddacn("");
			}else{
				model.setYjzt("");
			}
			
			rs = dao.runUpdate(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			/*����ΨһID*/
			model.setGuid(guid);
			/*1:������ˡ�2:������ӡ�3:����*/
			model.setSjly("2");
			rs = dao.runInsert(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	/**
	 * @����: ��֤Ψһ�ԣ�ѧ��(xh)
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-10 ����04:04:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotRepeat(DazcjgForm model){
		return dao.checkIsNotRepeat(model);
	}
	
	/**
	 * @����: ͨ��guid�鿴ѧ������ת�������Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-10 ����05:21:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getInfoByGuid(String xh) throws Exception{
		return dao.getInfoByGuid(xh);
	}
	
	/**
	 * @����: ����ѧ�Ų鿴������Ƿ�������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-14 ����09:19:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getDazcjgRs(String xh) throws Exception{
		return dao.getDazcjgRs(xh);
	}
}
