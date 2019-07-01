/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-27 ����03:12:55 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcsq;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dagl.xzmzdx.cssz.DazccsszDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ����ģ��
 * @�๦������: ����ת��-����
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-27 ����03:13:09 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DazcsqService extends SuperServiceImpl<DazcsqForm,DazcsqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * @����: �����������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-8 ����11:57:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getSplc(){
		DazccsszDao dazccsszDao = new DazccsszDao();
		return dazccsszDao.getSplc();
	}
	
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-9 ����01:56:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveFormDazcsq(DazcsqForm model,User user) throws Exception{
		boolean rs = true;
		
		/*����Ψһ��ʶ��*/
		String sqid = UniqID.getInstance().getUniqIDHash();
		/*ȡ��������*/
		String splcid = this.getSplc().get("splc");
		
		/*�ж�Ψһ����ѧ��(xh)*/
		if(!this.checkIsNotRepeat(model)){
			throw new SystemException(MessageKey.SZTZ_XMSB_REPEAT);
		}
		
		/*��ǰ������Ա�û�������*/
		model.setSjlrr(user.getUserName());
		
		/*��ȡ��ǰ����ʱ��������У���ϲ����˹��ŷ�ֹ��ʦˣ��*/
		String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
		model.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
		
		/*�����������״̬����*/
		if("save".equals(model.getSaveFlag())){
			/*��� ����ݸ� ��ť���״̬Ϊδ�ύ��0��*/
			model.setShzt(Constants.YW_WTJ);
		}else{
			/*��� �ύ���� ��ť���״̬Ϊ����С�5��*/
			model.setShzt(Constants.YW_SHZ);
		}
		
		/*�жϸ������Ƿ�Ϊ�޸�����*/
		if(StringUtils.isNotNull(model.getSqid())){
			
			/*��ת����ʽΪ���ʼġ���ʱ���޸Ĳ���Ҫ���Դ���ŵ��ֵ��յ�*/
			if("1".equals(model.getZcfs())){
				model.setZddacn("");
			}
			
			rs = dao.runUpdate(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			/*����ΨһID*/
			model.setSqid(sqid);
			model.setSplcid(splcid);
			rs = dao.runInsert(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		
		/*�����ύʱ����sqid,�������̡���������.do�����������*/
		if("submit".equals(model.getSaveFlag())){
			rs = shlc.runSubmit(model.getSqid(),splcid,model.getXh(), "xsxx_dagl_dazcsh.do", "xsxx_dagl_dazcsq.do");
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	/**
	 * @����: ��֤Ψһ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-9 ����01:59:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotRepeat(DazcsqForm model){
		return dao.checkIsNotRepeat(model);
	}
	
	/**
	 * @����: ��������ID���ѧ��������Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-9 ����05:46:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getInfoBySqid(String sqid) throws Exception{
		return dao.getInfoBySqid(sqid);
	}
	
	/**
	 * @����: ɾ��ʱ�������״̬���е���������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-9 ����01:59:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delShztbData(String[] sqids) throws Exception{
		return dao.delShztbData(sqids);
	}
	
	/**
	 * @����: ����sqid��ȡѧ�������Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-9 ����02:36:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqidArr
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getInfoBySqid(String[] sqidArr)throws Exception{
		return dao.getInfoBySqid(sqidArr);
	}
	
	/**
	 * @����: �����ύ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-9 ����02:37:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @param splcid
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean plSubmit(String sqid,String splcid,String xh) throws Exception{
		boolean flag = false;
		DazcsqForm model = new DazcsqForm();
		model.setShzt(Constants.YW_SHZ);
		model.setSplcid(splcid);
		model.setSqid(sqid);
		flag = dao.runUpdate(model);
		if(flag){
			shlc.runSubmit(sqid,splcid,xh, "xsxx_dagl_dazcsh.do", "xsxx_dagl_dazcsq.do");
		}
		return flag;
	}
	
	/**
	 * @����: ��������еļ�¼
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-9 ����02:46:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

}
