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
	
	
	
	// 保存
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
	 * @描述:批量等级考核
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-8-8 下午06:41:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
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
	 * @描述:获取学生行为考核信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-8-8 上午11:11:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
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
