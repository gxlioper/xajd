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
	 * ��ü��ɴ���list
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
	 * @����:����Υ��ѧ����Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-12-3 ����02:29:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
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
