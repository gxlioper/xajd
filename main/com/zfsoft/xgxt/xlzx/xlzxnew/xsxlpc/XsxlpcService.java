package com.zfsoft.xgxt.xlzx.xlzxnew.xsxlpc;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class XsxlpcService extends SuperServiceImpl<XsxlpcForm,XsxlpcDao> {
	/**
	 * 
	 * @描述:
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-8 下午05:16:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getJtqkMap(String xh){
		return dao.getJtqkMap(xh);
	}
	
	/**
	 * 
	 * @描述:验证是否存在
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-8 下午06:52:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExists(String xh){
		return dao.checkIsNotExists(xh);
	}
	
	/**
	 * 
	 * @描述:关注取消关注
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-9 下午05:03:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @param sfgz
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean sz(String[] ids,String sfgz) throws Exception{
		return dao.sz(ids, sfgz);
	}
}
