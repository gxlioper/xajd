/**
 * @部门:学工产品事业部
 * @日期：2014-1-27 上午10:24:54 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-27 上午10:24:54
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglGzdmService extends
		SuperServiceImpl<FbglGzdmForm, FbglGzdmDao> {
	FbglGzdmDao dao = new FbglGzdmDao();

	public FbglGzdmService() {
		this.setDao(dao);
	}

	public List<HashMap<String, String>> getGzList() {
		return dao.getGzList();
	}
	/**
	 * 
	 * @描述: 通过规则代码获取条件规则
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-27 下午02:50:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjgzIds
	 * @return
	 * List<FbglTjgzForm> 返回类型 
	 */
	public List<HashMap<String, String>> getTjGzForGzdm(String gzdm){
		try {
			FbglGzdmForm fgf=dao.getModel(gzdm);
			FbglTjgzService fts=new FbglTjgzService();
			return fts.getTjlx(fgf.getTjgzid());
		} catch (Exception e) {
			throw new RuntimeException("获取对应条件规则失败!"+e.getMessage());
		}
	}
	public String getGzmc(String gzdm){
		return dao.getGzmc(gzdm);
	}
}
