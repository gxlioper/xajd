/**
 * @部门:学工产品事业部
 * @日期：2016-3-9 上午10:47:03 
 */  
package xsgzgl.szdw.jtff.util;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2016-3-9 上午10:47:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JtffUtilService extends SuperServiceImpl<JtffUtilForm, JtffUtilDao> {
	public HashMap<String, String> getJsjbxx(String zgh){

		if (StringUtil.isNull(zgh)) {
			logger.error("zgh is null !");
			throw new NullPointerException();
		}

		try {
			return dao.getJsjbxx(zgh);
		} catch (Exception e) {
			throw new NullPointerException();
		}
	}
	
	public List<HashMap<String, String>> getJsxxList(JtffUtilForm t, User user)
	  throws Exception{
		return dao.getJsxxList(t, user);
	}
	
	/**正常津贴人员增加时重复判断
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2016-3-9 下午03:45:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExists(String zgh){
		return dao.isExists(zgh);
	}
	
	/**固定津贴人员增加时重复判断
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2016-3-9 下午03:45:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExists1(String zgh){
		
		return dao.isExists1(zgh);
	}
	
	/**
	 * 根据职工号获取人员维护信息
	 */
	public HashMap<String, String> getRywhxx(String zgh){
		return dao.getRywhxx(zgh);
	}
}

