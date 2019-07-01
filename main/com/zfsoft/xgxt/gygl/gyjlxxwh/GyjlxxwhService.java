package com.zfsoft.xgxt.gygl.gyjlxxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import xgxt.utils.String.StringUtils;


import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg.ZwzxKqjgForm;

public class GyjlxxwhService extends SuperServiceImpl<GyjlxxwhForm,GyjlxxwhDao>{
	
	GyjlxxwhDao GyjlxxglDAO = new GyjlxxwhDao();
	

	/**
	 * 获得纪律大类list
	 * @param request
	 * @return
	 */
	public List<HashMap<String, String>> getJldlList(HttpServletRequest request){
		return GyjlxxglDAO.getJldlList(request);
	}
	public List<HashMap<String, String>> getWjxsList(GyjlxxwhForm model,String cxzd,String qsxx,String sftq,String xhs){
		String[] xhArr = null;
		if(StringUtils.isNotNull(xhs)){
			xhArr=xhs.split(",");
		}
		return GyjlxxglDAO.getWjxsList(model,cxzd,qsxx,sftq,xhArr);
	}
	/**
	 * 
	 * @描述:保存违纪学生信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-12-3 下午02:29:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveWjxx(GyjlxxwhForm model,List<GyjlxxwhForm> wjxxList){
		boolean result=false;
		List<String[]>param = new ArrayList<String[]>();
		for (GyjlxxwhForm xxwhForm : wjxxList) {
			String[] el = new String[]{xxwhForm.getJldldm(),xxwhForm.getJllbdm(),xxwhForm.getWjsj(),model.getBz(),model.getCzr(),model.getWjxn(),model.getWjxq(),xxwhForm.getXh()};
			param.add(el);
		}
		try {
			result = dao.saveGyjlxx(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return result;
		}
	
	public HashMap<String,String> getOneRsWjxx(GyjlxxwhForm model){
		return dao.getOneRsWjxx(model);
	}
	public boolean isExists(GyjlxxwhForm model){
		return Integer.parseInt(dao.isExists(model))>0;
	}
	
	public List<HashMap<String,String>> getWjxxList(GyjlxxwhForm myForm) throws Exception{
		return dao.getWjxxList(myForm);
	}
	

}
