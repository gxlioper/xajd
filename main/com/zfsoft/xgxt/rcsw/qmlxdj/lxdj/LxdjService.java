/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-11 ����08:57:27 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.lxdj;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qmlxdj.cssz.CsszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-11 ����08:57:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public final class LxdjService extends SuperServiceImpl<LxdjForm, LxdjDao> {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-11 ����02:28:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkNotExist(String xh,String xn,String xq,String flag){
		return dao.checkNotExist(xh, xn, xq, flag);
	}
	
	/**
	 * 
	 * @����: dmList
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-11 ����03:15:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDmList(){
		return dao.getDmList();
	}
	public List<HashMap<String, String>> getLxlxList(){
		return dao.getLxlxList();
	}
	/**
	 * 
	 * @����: ��ȡ�鿴��Ϣmap
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-11 ����03:45:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getCkxx(String id){
		return dao.getCkxx(id);
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-11 ����04:20:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lxdjform
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSq(LxdjForm lxdjform,User user) throws Exception {
		CsszService csszService = new CsszService();
		String splcid = csszService.getModel().getSplc();
		lxdjform.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		lxdjform.setSplcid(splcid);
		String sqid = lxdjform.getSqid();
		String type = lxdjform.getType();
		if(StringUtils.isNull(sqid)){
			sqid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		}
		boolean flag = true;
		if(type.indexOf("submit") != -1){
			lxdjform.setShzt(Constants.YW_SHZ);
		}else{
			lxdjform.setShzt(Constants.YW_WTJ);
			
		}
		if(StringUtils.isNotNull(lxdjform.getSqid())){
			flag = dao.runUpdate(lxdjform);
		}else{
			lxdjform.setSqid(sqid);
			flag = dao.runInsert(lxdjform);
		}
		 
		if(type.indexOf("submit") != -1){
			if (flag) {
				flag = shlc.runSubmit(sqid, splcid, lxdjform.getXh(), "rcsw_qmlxsh.do", "rcsw_qmlxdj.do");
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:�ύ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-11 ����04:48:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitBusi(LxdjForm model, User user)  throws Exception {
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			CsszService csszService = new CsszService();
			String splcid = csszService.getModel().getSplc();
			model.setSplcid(splcid);
		}
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		if(flag){
			 shlc.runSubmit(model.getSqid(), model.getSplcid(), model.getXh(), "rcsw_qmlxsh.do", "rcsw_qmlxdj.do");
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-11 ����04:48:24
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
