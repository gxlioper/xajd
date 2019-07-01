package com.zfsoft.xgxt.gygl.gypynew.gypysq;

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
import com.zfsoft.xgxt.gygl.gypynew.cssz.CsszService;

public class GypySqService extends SuperServiceImpl<GypySqForm, GypySqDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * @����: ��ȡ¥������List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-25 ����05:55:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLddmList(User user,String gyglyQx){
		 return dao.getLddmList(user, gyglyQx);
	}
	
	/**
	 * 
	 * @����: ��ȡ���List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-26 ����02:01:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param gyglyQx
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getChList(User user,String gyglyQx){
		return dao.getChList(user, gyglyQx);
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-26 ����02:37:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param gyglyQx
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQshList(User user,String gyglyQx){
		return dao.getQshList(user, gyglyQx);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-27 ����03:39:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSq(GypySqForm model,User user) throws Exception{
		boolean rs = true;
		//�ж��ظ���
		if(!dao.checkIsNotRepeat(model)){
			throw new SystemException(MessageKey.XG_GYPY_SQ_REPEAT);
		}
		model.setSplc(new CsszService().getSplc().get("splc"));
		if("submit".equals(model.getSaveType())){
			model.setShzt(Constants.YW_SHZ);
		}else{
			model.setShzt(Constants.YW_WTJ);
		}
		if(StringUtils.isNotNull(model.getSqid())){
			rs = dao.runUpdate(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			model.setSqid(UniqID.getInstance().getUniqIDHash().toUpperCase());
			rs = dao.runInsert(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		if("submit".equals(model.getSaveType())){
			rs = shlc.runSubmit(model.getSqid(),model.getSplc(),user.getUserName(), "gygl_gypynew_gypysh.do", "gygl_gypynew_gypysq.do");
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	//�ύ
	public boolean submitBusi(GypySqForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		String splc =new CsszService().getSplc().get("splc");
		model.setSplc(splc);
		boolean flag = dao.runUpdate(model);
		if(flag){
			 shlc.runSubmit(model.getSqid(), model.getSplc(),user.getUserName(), "gygl_gypynew_gypysh.do", "gygl_gypynew_gypysq.do");
		}
		return flag;
	}
	
	//����
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * 
	 * @����: ��ȡ������Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-28 ����02:25:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getQshxx(GypySqForm model){
		return dao.getQshxx(model);
	}
	
	/**
	 * 
	 * @����: ��ȡ�Ǽ��������������Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-4 ����11:04:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXjqsSqJbxx(GypySqForm model){
		return dao.getXjqsSqJbxx(model);
	}
	
	/**
	 * 
	 * @����: ��ȡΥ�͸���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-4 ����11:50:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getWjNum(GypySqForm model){
		return dao.getWjNum(model);
	}
	
	/**
	 * 
	 * @����: ��ȡ���������༶���п����Ƕ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-4 ����11:55:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public String getQsssbj(GypySqForm model){
		return dao.getQsssbj(model);
	}
	
	/**
	 * 
	 * @����: ��ȡΥ����Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-4 ����01:53:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getWjxx(GypySqForm model){
		return dao.getWjxx(model);
	}
	
}
