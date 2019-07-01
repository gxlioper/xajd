package com.zfsoft.xgxt.xszy.xsxxgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����֮��
 * @�๦������: ������Ϣ����
 */
public class XszyXsxxService extends SuperServiceImpl<XszyXsxxForm, XszyXsxxDao> {
	
	private XszyXsxxDao dao = new XszyXsxxDao();
	
	public XszyXsxxService() {
		super.setDao(dao);
	}
	
	/**
	 * �޸�
	 */
	public boolean updateXszyXsxx(XszyXsxxForm model) throws Exception {
		boolean updateResult =true;
		XszyXsxxForm myForm = dao.getModel(model.getXh());
		if(null!=myForm){
			updateResult = super.runUpdate(model);
		}else{
			updateResult = super.runInsert(model);
		}
		  
		return updateResult;
	}
	
	/** 
	 * ����
	 */
	public List<HashMap<String, String>> queryMzList() throws Exception {
		return dao.queryMzList();
	}
	
}
