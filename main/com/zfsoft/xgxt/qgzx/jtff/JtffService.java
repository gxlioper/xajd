package com.zfsoft.xgxt.qgzx.jtff;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import xsgzgl.qgzx.cjgl.QgzxCjglService;

public class JtffService extends SuperServiceImpl<JtffForm,JtffDao> {
	private JtffDao dao= new JtffDao();
	
	public JtffService(){
		super.setDao(dao);
	 }
	 
	public boolean checkExist(JtffForm form) throws Exception {
		return Integer.valueOf(dao.checkExist(form)) > 0;
	}
	
	public List<HashMap<String,String>>getBmList (){
		return dao.getBmList();
	}

} 


