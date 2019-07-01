/**
 * @部门:学工产品事业部
 * @日期：2014-3-4 下午04:19:29 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.sq;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 在读证明申请-SERVICE类 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-4 下午04:19:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZdzmSqService extends SuperServiceImpl<ZdzmSqForm, ZdzmSqDao> {

	public ZdzmSqService(){
		super.setDao(new ZdzmSqDao());
	}
	
	/**
	 * 
	 * @描述:根据学号获取申请记录
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-6 上午09:43:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getZdzmListByXh(String xh) throws Exception{
		return dao.getZdzmListByXh(xh);
	}
	
	/**
	 * 
	 * @描述:根据学号获取在审核流中的申请记录
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-6 上午10:00:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getZdzmInShlcByXh(String xh) throws Exception{
		return dao.getZdzmInShlcByXh(xh);
	}
	
	/**
	 * 
	 * @描述:保存在读证明
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-6 上午10:18:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zdzmsq
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveZdzmSq(ZdzmSqForm zdzmsq) throws Exception{
		return dao.runInsert(zdzmsq);
	}
	
	/**
	 * 
	 * @描述:更新在读证明申请
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-6 上午10:22:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zdzmsq
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateZdzmSq(ZdzmSqForm zdzmsq) throws Exception{
		return dao.runUpdate(zdzmsq);
	}
	
	/**
	 * 
	 * @描述:删除在读证明申请
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-6 上午10:24:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zdzmsqids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int deleteZdzmSqs(String[] zdzmsqids) throws Exception{
		return dao.runDelete(zdzmsqids);
	}
	
	/**
	 * 
	 * @描述:提交在读证明至审核流
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-6 上午10:19:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zdzmsq
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitZdzmSq(ZdzmSqForm zdzmsq) throws Exception{
		return false;
	}
	
	/**
	 * 
	 * @描述:撤销在读证明申请
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-6 上午10:20:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelZdzmSq(String zdzmsqid) throws Exception {
		return false;
	}
}
