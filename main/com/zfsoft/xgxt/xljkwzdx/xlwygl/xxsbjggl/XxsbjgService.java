/**
 * @部门:学工产品事业部
 * @日期：2014-6-3 上午10:04:09 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbjggl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-6-3 上午10:04:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XxsbjgService extends SuperServiceImpl<XxsbjgForm, XxsbjgDao>{

	/**
	 * 
	 * @描述:根据sbsqid删除结果表记录
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-3 上午10:08:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sbsqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteBySqid(String sbsqid) throws Exception{
		return dao.deleteBySqid(sbsqid);
	}
	
	
	/**
	 * 
	 * @描述:获取查询信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-9 下午03:04:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqjgid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getModelMap(String sqjgid )throws Exception{
		return dao.getModelMap(sqjgid);
	}
	
	/**
	 * 
	 * @描述:检查重复
	 * @作者：1036
	 * @日期：2014-7-11 下午04:46:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param sblx
	 * @param zc
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkExist(String xh , String sblx  , String zc)throws Exception{
		return dao.checkExist(xh, sblx, zc);
	}
	
	public List<HashMap<String, String>> getXnList() {
		List<HashMap<String, String>> list = dao.getXnList();
		return list;
	}
	
	public List<HashMap<String,String>> getZcList(String xn, String xq, String sblx) {
		return dao.getZcList(xn, xq, sblx);
	}
	
	public List<HashMap<String,String>> getQzrq(String xn, String xq, String zbid) {
		return dao.getQzrq(xn, xq, zbid);
	}
	
	public XxsbjgService(){
		setDao(new XxsbjgDao());
	}
	
}
