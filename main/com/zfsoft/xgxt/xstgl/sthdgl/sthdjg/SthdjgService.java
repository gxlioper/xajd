package com.zfsoft.xgxt.xstgl.sthdgl.sthdjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import xgxt.form.User;
import xgxt.utils.GetTime;

public class SthdjgService extends SuperServiceImpl<SthdjgForm, SthdjgDao>{
	SthdjgDao dao = new SthdjgDao();
    

	// ����
	public boolean saveSthdjg(SthdjgForm model,User user) throws Exception {
		String id = null;
		if ("save".equalsIgnoreCase(model.getType())) {
			model.setLrr(user.getUserName());
			model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
			id = UniqID.getInstance().getUniqIDHash();
			model.setHdid(id);
			// ��������
			return dao.runInsert(model);
		}
		else{
			return dao.runUpdate(model);
		}
		
	}
	/**
	 * 
	 * @����:��֤�Ƿ��ظ���д
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-30 ����10:13:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkExistForSave(SthdjgForm model){
		return dao.checkExistForSave(model);
	}
	/**
	 * 
	 * @����:��ȡѧ�����Ż�Ǽ���ʷ��Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-30 ����10:11:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> �������� 
	 * @throws
	 */
	public ArrayList<String[]>  getMoreHdjgList(SthdjgForm model)throws Exception {
		
		return dao.getMoreHdjgList(model);
	}
	
	/**
	 *
	 * @����:����ʦ����ѧ־Ը�߽��listѧ����Ϣ�鿴�Զ����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-12-7 ����09:14:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getSthdlist(String xh){
		return dao.getSthdlist(xh);
	}

	public boolean delByFwsj(SthdjgForm t) throws Exception {
		return dao.delByFwsj(t);
	}
}
