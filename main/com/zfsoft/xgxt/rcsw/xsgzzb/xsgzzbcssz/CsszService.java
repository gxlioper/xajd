/**
 * @部门:学工产品事业部
 * @日期：2015-11-10 下午04:56:36 
 */  
package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbcssz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2015-11-10 下午04:56:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszService extends SuperServiceImpl<CsszForm, CsszDao> {
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-11 上午09:34:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveWjlx(CsszForm t) throws Exception{
		return dao.saveWjlx(t);
	}
	
	//验证文件类型名称是否在用
	public boolean isExistsWjlxmc_user(String wjlxdm){
		return dao.isExistsWjlxmc_user(wjlxdm);
	}
	
	//获取人员类别名称
	public String getWjlxmc(String wjlxdm){
		return dao.getWjlxmc(wjlxdm);
	}
	
	//验证数据库中是否有同名的人员类别名称
	public boolean isExistsSameWjlxmc(String wjlxmc,String wjlxdm){
		return dao.isExistsSameWjlxmc(wjlxmc, wjlxdm);
	}
	
	//获取文件类型名称list
	public List<HashMap<String,String>> getWjlxList(){
		return dao.getWjlxList();
	}
	
	//已上传附件信息list
	public List<HashMap<String, String>> getYscfjList(String sqid){
		return dao.getYscfjList(sqid);
	}
}
