package com.zfsoft.xgxt.rcsw.xsxwkh.pddj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class PddjService extends SuperServiceImpl<PddjForm, PddjDao>{
	PddjDao dao = new PddjDao();
	
	
	
	// ����
	public boolean savePddj(PddjForm model, User user) throws Exception {
		boolean flag=true;
		if (StringUtils.isNull(model.getId())) {
			flag=dao.runInsert(model);
		}else{
			flag=dao.runUpdate(model);
		}
	    return flag;
	}
	/**
	 * 
	 * @����:�����ȼ�����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-8-8 ����06:41:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public String savePddjPl(PddjForm myForm) throws Exception {
		String[] xhs = myForm.getXhs();
		String[] xns = myForm.getXns();
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = xhs.length; i < n; i++) {
			PddjForm model = new PddjForm();
			model.setXh(xhs[i]);
			model.setXn(xns[i]);
			model.setPddj(myForm.getPddj());
			model.setXf(myForm.getXf());
			boolean isSuccess = dao.savePddjPl(model);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
		} else {
			return MessageUtil.getText(MessageKey.SYS_SAVE_FAIL, json.toString());
		}
	}
	
	
	/**
	 * 
	 * @����:��ȡѧ����Ϊ������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-8-8 ����11:11:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXsxwKhxx(String jbfid,String xn){
		return dao.getXsxwKhxx(jbfid, xn);
	}
	
	public List<HashMap<String,String>> getJlfList(String xh,String xn){
		return dao.getJlfList(xh, xn);
	}
	public List<HashMap<String,String>> getCffList(String xh,String xn){
		return dao.getCffList(xh, xn);
	}
	public List<HashMap<String,String>> getFjfList(String xh,String xn){
		return dao.getFjfList(xh, xn);
	}
	
}
