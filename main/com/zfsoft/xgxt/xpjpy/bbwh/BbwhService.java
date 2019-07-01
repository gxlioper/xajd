/**
 * @部门:学工产品事业部
 * @日期：2013-11-26 下午05:35:29 
 */  
package com.zfsoft.xgxt.xpjpy.bbwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-11-26 下午05:35:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BbwhService extends  SuperServiceImpl<BbwhForm, BbwhDao> {

	private BbwhDao dao = new BbwhDao();

	public BbwhService() {
		super.setDao(dao);
	}
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-11-27 上午11:26:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBbxxList(){
		
		return dao.getBbxxList();
	}
	
	/**
	 * @描述：获取报表信息
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月12日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbdm
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getBbxx(String bbdm){
		return dao.getBbxx(bbdm);
	}
	
	/**
	 * @描述：获取报表列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月12日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bblx
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getBbxxList(String bblx){
		return dao.getBbxxList(bblx);
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-11-28 上午09:48:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbdm
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getDataById(String bbdm){
		return dao.getDataById(bbdm);
	}
	
}
