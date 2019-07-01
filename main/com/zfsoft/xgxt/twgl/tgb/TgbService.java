/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.twgl.tgb;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @className	： TgbService
 * @description	： TODO(描述这个类的作用)
 * @author 		：lj（1282）
 * @date		： 2018-5-15 下午03:33:11
 * @version 	V1.0 
 */

public class TgbService extends SuperServiceImpl<TgbModel, TgbDao>{
	/**
	 * @description	： 获取职务代码
	 * @author 		： lj（1282）
	 * @date 		：2018-5-15 下午05:26:55
	 * @return
	 */
	public List<HashMap<String,String>> getDmList(){
		return dao.getDmList();
	}
	
	/**
	 * @description	： 获取信息
	 * @author 		： lj（1282）
	 * @date 		：2018-5-16 上午11:44:55
	 * @param t
	 * @return
	 */
	public HashMap<String,String> getInfo(TgbModel t){
		return dao.getInfo(t);
	}
	
	/**
	 * @description	： 是否存在团干部
	 * @author 		： lj（1282）
	 * @date 		：2018-5-16 下午05:52:22
	 * @param zzids
	 * @return
	 */
	public boolean isExistTgb(String[] zzids){
		return dao.countTgb(zzids)>0;
	}
}
