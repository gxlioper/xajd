/**
 * @部门:学工产品事业部
 * @日期：2013-12-26 上午11:07:18 
 */  
package com.zfsoft.xgxt.comm.bbdmpz.service;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.comm.bbdmpz.dao.BbdmDao;
import com.zfsoft.xgxt.comm.bbdmpz.model.BbdmModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-26 上午11:07:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BbdmService extends SuperServiceImpl<BbdmModel, BbdmDao> {
	
	{
		super.dao = new BbdmDao();
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-27 下午01:00:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mkdm
	 * @return
	 * @throws SystemException
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>>  getBbdmList(String mkdm) throws SystemException{
		return dao.getBbdmList(mkdm);
	}
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-27 下午01:00:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * @throws SystemException
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getBbdm(String guid) throws SystemException{
		return dao.getBbdm(guid);
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-27 下午01:00:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * @throws SystemException
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getBbmbInfo(String guid) throws SystemException{
		return dao.getBbmbInfo(guid);
	}

	/**
	 * 
	 */
	public BbdmModel getModel(String mkdm) throws Exception{
		return dao.getModel(mkdm);
	}
	
	public BbdmModel getModelByGuid(String guid) throws Exception{
		return dao.getModelByGuid(guid);
	}
	
	/**
	 * 
	 * 获取模块相关参数
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-27 下午12:59:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mkdm
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getBbdmCs(String mkdm) throws Exception{
		return dao.getBbdmCs(mkdm);
	}
	
	/**
	 * 
	 * @描述:获取当前选中的报表ID
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-27 下午05:02:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mkdm
	 * @param pk
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getDybb(String mkdm , String pk)throws Exception{
		return dao.getDybb(mkdm, pk);
	}
	
	/**
	 * 
	 * @描述:设置报表
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-27 下午05:04:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mkdm
	 * @param pk
	 * @param guid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean setupDybb(String mkdm , String pk , String guid) throws Exception{
		return dao.setupDybb(mkdm, pk, guid);
	}
	
	
}
