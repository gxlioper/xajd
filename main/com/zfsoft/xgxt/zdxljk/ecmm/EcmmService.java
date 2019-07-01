/**
 * @部门:学工产品事业部
 * @日期：2015-2-11 上午09:12:58 
 */  
package com.zfsoft.xgxt.zdxljk.ecmm;

import xgxt.base.Encrypt;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @类功能描述: 浙大心理健康--特别关心学生 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-2-11 上午09:11:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class EcmmService extends SuperServiceImpl<EcmmModel, EcmmDao> {

	
	public EcmmModel getModelByLogin(String zgh ,String ecmm) throws Exception{
		
		Encrypt e = new Encrypt();
		
		EcmmModel model = new EcmmModel();
		model.setZgh(zgh);
		model.setEcmm(e.encrypt(ecmm));
		
		return dao.getModelByLogin(model);
	}
	
	
	public boolean initEcmm(String[] zgh,String ecmm) throws Exception{
		
		Encrypt e = new Encrypt();
		
		return dao.initEcmm(zgh, e.encrypt(ecmm));
	}
}
