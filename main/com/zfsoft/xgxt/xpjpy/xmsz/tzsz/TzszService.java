/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:11:11
 */
package com.zfsoft.xgxt.xpjpy.xmsz.tzsz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 项目维护-审核调整奖项设置
 * @作者： ligl
 * @日期：2013-8-5 上午11:11:11
 * @版本： V1.0
 * @修改记录:
 */
public class TzszService extends
		SuperServiceImpl<TzszModel, TzszDao> {

	private TzszDao dao = new TzszDao();

	public TzszService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:兼得设置
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: 
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean shsz(String xmdm,String xmdms) throws Exception{
		return dao.runShsz(xmdm,xmdms);
	}
	
	/**
	 * 
	 * @描述:根据xmdm查询设置的记录
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getByXmdm(String xmdm,String currXn, String currXq) throws Exception{
		return dao.getByXmdm(xmdm,currXn,currXq);		
	}
	
	/**
	 * 
	 * @描述:根据项目代码，获取已设置的可调整项目
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getKshxm(String xmdm) throws Exception{
		return dao.getKshxm(xmdm);
	}

	
	/**
	 * 
	 * @描述: 查询可调整的评奖项目列表 
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-5 下午02:26:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getKtzPjxmList(String xmdm){
		if (StringUtil.isNull(xmdm)){
			return null;
		}
		return dao.getKtzPjxmList(xmdm);
	}
	
	/**
	 * 
	 * @描述:根据项目代码删除记录
	 * @作者：cq [工号：785]
	 * @日期：2013-12-23 下午05:23:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean runDeleteByXmdm(String xmdm) throws Exception {
		return dao.runDeleteByXmdm(xmdm);
	}

}
