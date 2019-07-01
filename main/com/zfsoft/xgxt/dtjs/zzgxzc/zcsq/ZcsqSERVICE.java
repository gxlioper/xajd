/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-2-8 ����10:08:35 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.zcsq;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dtjs.zzgxzc.jcsz.JcszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-2-8 ����10:08:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcsqSERVICE extends SuperServiceImpl<ZcsqForm, ZcsqDAO> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * @����: ��֧������List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-8 ����05:15:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDzbList(){
		return dao.getDzbList();
	}
	
	/**
	 * 
	 * @����: �鿴ת������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-9 ����02:05:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> ckZcsq(String xh){
		return dao.ckZcsq(xh);
	}
	
	/**
	 * 
	 * @����: ������������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-9 ����03:05:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSq(ZcsqForm model,User user) throws Exception {
		//���ѽ������ڴ���תΪÿ�µ����һ�죩
		model.setDfjzrq(DateUtils.getLastDayOfMonth(model.getDfjzrq()));
		
		JcszService csszService = new JcszService();
		String splcid = csszService.getModel().getSplc();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplcid(splcid);
		model.setSqr(user.getUserName());
		String sqid = model.getSqid();
		String type = model.getType();
		if(StringUtils.isNull(sqid)){
			sqid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		}
		boolean flag = true;
		if(type.indexOf("submit") != -1){
			model.setShzt(Constants.YW_SHZ);
		}else{
			model.setShzt(Constants.YW_WTJ);
			
		}
		if(StringUtils.isNotNull(model.getSqid())){
			flag = dao.runUpdate(model);
		}else{
			model.setSqid(sqid);
			flag = dao.runInsert(model);
		}
		 
		if(type.indexOf("submit") != -1){
			if (flag) {
				flag = shlc.runSubmit(sqid, splcid, model.getXh(), "dtjs_dzzgxsh.do", "dtjs_dzzgxsq.do");
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
	public boolean submitBusi(ZcsqForm model, User user)  throws Exception {
		JcszService csszService = new JcszService();
		String splcid = csszService.getModel().getSplc();
		model.setSplcid(splcid);
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		if(flag){
			 shlc.runSubmit(model.getSqid(), model.getSplcid(), model.getXh(), "dtjs_dzzgxsh.do", "dtjs_dzzgxsq.do");
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
	
	/**
	 * 
	 * @����: �Ƿ񲻴�������ͽ������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-9 ����03:21:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean IsNotExist(ZcsqForm model){
		return dao.IsNotExist(model);
	}
	
	/**
	 * 
	 * @����: ��֤���ʸ�ҳ���ѧ���Ƿ�Ϊ��Ա
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-10 ����09:35:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param username
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean IsDy(String username){
		return dao.IsDy(username);
	}
}
