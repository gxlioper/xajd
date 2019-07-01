/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.twgl.tzz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @className	： TzzService
 * @description	： TODO(描述这个类的作用)
 * @author 		：lj（1282）
 * @date		： 2018-5-14 下午02:45:44
 * @version 	V1.0 
 */

public class TzzService extends SuperServiceImpl<TzzModel, TzzDao>{
	TzzDao dao = new TzzDao();
	
	/**
	 * @description	： 是否存在同名
	 * @author 		： lj（1282）
	 * @date 		：2018-5-15 上午10:12:09
	 * @param model
	 * @return
	 */
	public boolean isMcExist(TzzModel model){
		return dao.countMc(model) > 0;
	}
}
