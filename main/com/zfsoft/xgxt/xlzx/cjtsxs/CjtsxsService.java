/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.xlzx.cjtsxs;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @className	： CjtsxsService
 * @description	： 春季特殊学生service(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2017-11-7 下午02:01:31
 * @version 	V1.0 
 */

public class CjtsxsService extends SuperServiceImpl<CjtsxsForm, CjtsxsDao>{
	/**
	 * @description	： 是否存在该学生（根据学年、学期进行判断）
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-7 下午02:40:25
	 * @return
	 */
	public boolean isExist(CjtsxsForm form){
		return dao.isExist(form);
	}
}
