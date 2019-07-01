package com.zfsoft.xgxt.dtjs.dxbmgl.shlcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @功能描述：党校报名审核流程设置service
 * @author：杨珩 【1346】
 * @date：2017-11-1 上午09:25:37 
 */
public class DxbmshlcszService extends SuperServiceImpl<DxbmshlcszForm, DxbmshlcszDao> {
	/** 
	 * @功能描述：获取当前审核流
	 * @author：杨珩 【1346】
	 * @date：2017-11-3 上午10:17:53 
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws 
	 */
	public String dxbmshlcszCx() throws Exception{
		return dao.dxbmshlcszCx();
	}
	/** 
	 * @功能描述：保存审批流   先删后插
	 * @author：杨珩 【1346】
	 * @date：2017-11-3 上午09:55:32 
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean dxbmshlcszBc(DxbmshlcszForm model) throws Exception{
		if(dao.dxbmshlcszSc()){
			if(model==null){
				return false;
			}
			return dao.dxbmshlcszBc(model);
		}
		return false;
	}
}
