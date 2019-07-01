/**
 * @部门:学工产品事业部
 * @日期：2016-11-18 上午08:53:03 
 */  
package com.zfsoft.xgxt.xpjpy.zyzgk;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-11-18 上午08:53:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-11-18 上午10:03:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class ZyzgkService extends SuperServiceImpl<ZyzgkModel, ZyzgkDao>{
	private ZyzgkDao dao = new ZyzgkDao();
	
	/**
	 * @throws Exception  
	 * @描述:插入专业主干课(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-18 上午09:01:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveZgk(ZyzgkModel model,String zyCond) throws Exception{
		dao.deleteZyzgk(model.getPjxn(),model.getXydm(),zyCond);
		return dao.insertZgk(model);
	}
	
	/** 
	 * @描述:按学年获取专业主干课设置(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-18 上午09:56:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getZyzgkszList(ZyzgkModel t){
		return dao.getZyzgkszList(t);
	}
	
	/** 
	 * @描述:根据学年按专业获取主干课程列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-18 上午10:27:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getzgkListByXnZy(ZyzgkModel t){
		return dao.getzgkListByXnZy(t);
	}
	
	/** 
	 * @描述:根据学年获取已设置主干课(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-18 下午05:27:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getYszZgkModel(String xn){
		return dao.getYszZgkModel(xn);
	}
	
	public List<HashMap<String,String>> getzgkList(String xn,String zydm,String xydm){
		return dao.getzgkList(xn, zydm, xydm);
	}
	
	public List<HashMap<String,String>> getXyList(){
		return dao.getXyList();
	}
	
	public List<HashMap<String,String>> getZyList(String xydm){
		return dao.getZyList(xydm);
	}
}
