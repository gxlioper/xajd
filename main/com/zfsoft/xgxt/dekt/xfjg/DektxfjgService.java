package com.zfsoft.xgxt.dekt.xfjg;

import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class DektxfjgService extends SuperServiceImpl<DektxfjgForm,DektxfjgDao> {
	private DektxfjgDao dao= new DektxfjgDao();
	
	public DektxfjgService(){
		super.setDao(dao);
	 }
	 
	public boolean checkExist(DektxfjgForm form) throws Exception {
		return dao.checkExist(form);
	}
	
	public boolean deleteExist(DektxfjgForm form) throws Exception {
		return dao.deleteExist(form);
	}

	public Map<String, String> getView(DektxfjgForm model) throws Exception {
		return dao.getView(model);
	}
	
	/** 
	 * @描述:获取总学分(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-10 下午04:24:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws 
	 */
	public String getTotalXf(String xh) throws Exception{
		return dao.getTotalXf(xh);
	}
} 


