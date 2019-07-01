package com.zfsoft.xgxt.dekt.xmwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import xsgzgl.qgzx.cjgl.QgzxCjglService;

public class DektxmwhService extends SuperServiceImpl<DektxmwhForm,DektxmwhDao> {
	private DektxmwhDao dao= new DektxmwhDao();
	
	public DektxmwhService(){
		super.setDao(dao);
	 }
	 
//	public boolean checkExist(DektxmwhForm form) throws Exception {
//		return Integer.valueOf(dao.checkExist(form)) > 0;
//	}
	
	public List<HashMap<String, String>> getLx(String xmdl) throws Exception{
		return dao.getLx(xmdl);
	}
	
	public List<HashMap<String, String>> getRdxm(String lx) throws Exception{
		return dao.getRdxm(lx);
	}
	
	public List<HashMap<String, String>> getRdnrbz(String lx,String rdxm) throws Exception{
		return dao.getRdnrbz(lx, rdxm);
	}
	
	public List<HashMap<String, String>> getDj(String lx,String rdxm,String rdnrbz) throws Exception{
		return dao.getDj(lx, rdxm, rdnrbz);
	}

} 


