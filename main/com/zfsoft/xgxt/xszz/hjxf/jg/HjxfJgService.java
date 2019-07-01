/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-15 ����11:51:44 
 */  
package com.zfsoft.xgxt.xszz.hjxf.jg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xszz.hjxf.sq.HjxfSqDao;
import com.zfsoft.xgxt.xszz.hjxf.sq.HjxfSqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-3-15 ����11:51:44 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjxfJgService extends SuperServiceImpl<HjxfJgForm, HjxfJgDao> {
	HjxfJgDao dao = new HjxfJgDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	/**
	 * 
	 * ������������
	 */
	public boolean saveHjxf(HjxfJgForm model, User user) throws Exception {
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setCzy(user.getUserName());
		boolean flag = dao.runInsert(model);
		return flag;
	}
	/**
	 * 
	 * �����޸�����
	 */
	public boolean saveHjxfUpdate(HjxfJgForm model, User user) throws Exception {
		model.setCzy(user.getUserName());
		boolean flag = dao.runUpdate(model);
		return flag;
	}
	
	public String getKnsdj(HjxfJgForm model){
		return dao.getKnsdj(model);
	}
	
	public boolean delDkjg(String xh,String xn,String xq)throws Exception{
		return dao.delDkjg(xh, xn, xq);
	}
	
	public List<HashMap<String,String>> getJtknXshjList(String xn ,User user ){
		return dao.getJtknXshjList(xn, user);
	}
	
	//���ݻ���
	public HashMap<String, String> getSumHz(String xn,User user){
		return dao.getSumHz(xn, user);
	}
	
	public HashMap<String, String> getJqNdYf(String jqjzsj){
		return dao.getJqNdYf(jqjzsj);
	}
	
	public boolean isNotExists(HjxfJgForm utilform){
		return dao.isNotExists(utilform);
	}
}
