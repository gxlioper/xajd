/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xszz.bbwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class BbwhService extends SuperServiceImpl<BbwhForm, BbwhDao> {

	private BbwhDao dao = new BbwhDao();

	public BbwhService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @描述:获取报表
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getBb() throws Exception {
		List<HashMap<String, String>> list = dao.getAll();
		return list;
	}

	/**
	 * 
	 * @描述:根据代码查询单条记录
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getDataById(String bbdm) throws Exception {
		if (StringUtil.isNull(bbdm)) {
			logger.error("报表代码为空！");
			return null;
		}
		return dao.getDataById(bbdm);
	}

	
	/**
	 * 
	 * @描述: 查询登记表相关信息
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-5 下午02:11:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBbxxLists(String bblx){
		
		return dao.getBbxxLists(bblx);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-5 下午03:51:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBbxxList(String bbdm) {
		return dao.getBbxxList(bbdm);
	}
}
