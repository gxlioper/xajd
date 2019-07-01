/**
 * @部门:学工产品事业部
 * @日期：2018-12-25 下午04:05:11 
 */  
package com.zfsoft.xgxt.zhdj.xsdzbhdygl;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018-12-25 下午04:05:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DzbzwwhService extends SuperServiceImpl<DzbzwwhForm, DzbzwwhDao>{
	
    public boolean delZw(DzbzwwhForm model) throws Exception {
    	String zt = null;
    	if(dao.checkDzbDm(model)){
			zt="1";
		}else if(dao.checkDzzDm(model)){
			zt="2";
		}
        return dao.delZw(model,zt);
    }

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-12-26 上午09:00:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveDm(DzbzwwhForm model) throws Exception {
		// TODO 自动生成方法存根
	    boolean rs = dao.checkRepeatDm(model);//代码是否重复
	    if (!rs) {
	        throw new SystemException(MessageKey.SYS_DMWH_MC_EXISTS);
	    }
		boolean rsv = false;
	    if(model.getZwss().equals("党总支")){
			rsv = dao.insertDzzZw(model);
		}else {
			rsv = dao.insertDzb(model);
		}
	    if(!rsv){
			throw new SystemException(MessageKey.SYS_OPERATE_FAIL);
	    }
	    return dao.runInsert(model);
    }
	
    public List<HashMap<String, String>> getZwList(DzbzwwhForm model) throws Exception {
        return dao.getZwList(model);
    }

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-12-26 上午10:03:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateSava(DzbzwwhForm model) throws Exception {
		// TODO 自动生成方法存根
       return dao.runUpdate(model);
	}
}
