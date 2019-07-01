package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbjg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class XsgzzbjgService extends SuperServiceImpl<XsgzzbjgForm, XsgzzbjgDao> {

	private XsgzzbjgDao dao = new XsgzzbjgDao();
	
	public XsgzzbjgService() {
		super.setDao(dao);
	}

	@Override
	public List<HashMap<String, String>> getAllList(XsgzzbjgForm t, User user)
			throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		String gzzblx = t.getGzzblx();
		if("bj".equals(gzzblx)){
			return dao.getPageListBj(t, user);
		}
		return dao.getPageList(t, user);
	}

	/**
	 * 班级周报
	 */
	public List<HashMap<String, String>> getPageListBj(XsgzzbjgForm t, User user)
			throws Exception {
		return dao.getPageListBj(t, user);
	}

	/**
	 * 班级周报
	 */
	public XsgzzbjgForm getModelBj(XsgzzbjgForm t) throws Exception {
		return dao.getModelBj(t);
	}
	
}
