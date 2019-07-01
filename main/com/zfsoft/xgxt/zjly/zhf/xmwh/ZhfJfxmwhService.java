/**
 * @部门:学工产品事业部
 * @日期：2016-6-27 下午05:08:33 
 */  
package com.zfsoft.xgxt.zjly.zhf.xmwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 综合分管理模块
 * @类功能描述: 计分项目(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-6-27 下午05:08:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfJfxmwhService extends SuperServiceImpl<ZhfJfxmwhForm, ZhfJfxmwhDao>{
	private ZhfJfxmwhDao dao = new ZhfJfxmwhDao();
	
	/** 
	 * @描述:验证是否存在(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-27 下午05:20:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExist(ZhfJfxmwhForm t){
		return dao.count(t)>0;
	}
	
	/**
	 * @throws Exception  
	 * @描述:计分项目授权(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-28 下午03:42:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bmdms
	 * @param jfxms
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean jfxmSq(String[]bmdms,String[]jfxms) throws Exception{
		boolean result = dao.deljfxmSq(bmdms, jfxms);//先删除授权
		result = dao.jfxmSq(bmdms, jfxms);//再插入授权
		return result;
	}
	/**
	 * @throws Exception  
	 * @描述:计分项目取消授权(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-1-16 下午02:29:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bmdms
	 * @param jfxms
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean jfxmQx(String[]bmdms,String[]jfxms) throws Exception{
		boolean result = dao.deljfxmSq(bmdms, jfxms);//先删除授权
		return result;
	}
	
	/** 
	 * @描述:获取部门列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-28 下午04:07:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cxzd
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBmList(String cxzd){
		List<HashMap<String, String>> bmList = dao.getBmList(cxzd);
		return bmList;
	}
	
	/** 
	 * @描述:获取部门列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-29 上午09:23:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPageListForSq(ZhfJfxmwhForm t) throws Exception{
		return dao.getPageListForSq(t);
	}
	
	/** 
	 * @描述:兼得设置(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-29 上午09:26:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jfxmmcs
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean jdsz(String[] jfxmmcs) throws Exception{
		List<HashMap<String, String>> list = dao.getJfxmList(jfxmmcs);
		if(null != list && list.size()>1){
			return dao.jdsz(list);
		}else{
			return false;
		}
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-1-16 下午02:58:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getnewjfxmdm() {
		return dao.getnewjfxmdm();
	}
}
