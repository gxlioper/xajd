/**
 * @部门:学工产品事业部
 * @日期：2016-11-24 上午11:22:25 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxxmsz;

import java.util.ArrayList;
import java.util.List;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述:留校项目设置
 * @作者： yxy[工号:1206]
 * @时间： 2016-11-24 上午11:22:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxxmszService extends SuperServiceImpl<LxxmszForm, LxxmszDao> {
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存项目设置结果
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-24 下午03:16:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSzjg(LxxmszForm model) throws Exception{
		boolean rs = true;
		if("save".equals(model.getType())){
			rs = dao.runInsert(model);
		}else if("update".equals(model.getType())){
			rs = dao.runUpdate(model);
		}else{
			return false;
		}
		return rs;
	}
	
	/**
	 * 
	 * @描述: 项目名称是否可用
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-24 下午03:36:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExist(String xmid,String xmmc){
	    return dao.checkIsNotExist(xmid, xmmc);
	}
	
	/**
	 * 
	 * @描述: 判断是否可删除，判断依据：xg_cqsx_jqlx_mdwh中是否有用到被删除的项目
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-24 下午03:52:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean ifCanDel(String[] xmids){
		return dao.ifCanDel(xmids);
	}
}
