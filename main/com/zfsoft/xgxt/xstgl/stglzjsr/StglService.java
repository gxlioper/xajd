package com.zfsoft.xgxt.xstgl.stglzjsr;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xstgl.stgl.stsq.StsqForm;

import xgxt.form.User;
import xsgzgl.qgzx.cjgl.QgzxCjglService;

public class StglService extends SuperServiceImpl<StglForm,StglDao> {
	private StglDao dao= new StglDao();
	
	public StglService(){
		super.setDao(dao);
	 }
	 
	public boolean checkExist(StglForm form) throws Exception {
		return Integer.valueOf(dao.checkExist(form)) > 0;
	}
	
	public List<HashMap<String,String>>getBmList (){
		return new QgzxCjglService().getYrbm(null);
	}
	
	//社团申请增加添加学生，学生选择页面
	public List<HashMap<String, String>> getXsxxList(StglForm model, User user) throws Exception {
		return dao.getXsxxList(model, user);
	}
	
	//社团申请增加添加老师，老师选择页面
	public List<HashMap<String, String>> getTeaxxList(StglForm model, User user) throws Exception {
		return dao.getTeaList(model, user);
	}
	
	public HashMap<String,String> getStgl(StglForm model) throws Exception{
		return dao.getStgl(model);
	}
} 


