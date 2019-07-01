/**
 * @部门:学工产品事业部
 * @日期：2015-8-4 上午11:40:07 
 */
package com.zfsoft.xgxt.ystgl.jtgl.ystwh;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-4 上午11:40:07
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class YstwhService extends SuperServiceImpl<YstwhForm, YstwhDao> {
	//private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private YstwhDao dao = new YstwhDao();

	public boolean isHaveYstxx(YstwhForm model) {
		return dao.isHaveYstxx(model);
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:艺术团结果增加保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-4 上午09:54:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean editYstwh(YstwhForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String ystid = UniqID.getInstance().getUniqIDHash();
			model.setYstid(ystid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
			
		}
		return result;
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:获取艺术团结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-4 下午04:40:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String,String> getYstwh(YstwhForm t) throws Exception {
		return dao.getYstwh(t);
	}
	public boolean isHaveSqJl(String values) throws Exception {
		if(values != null){
			values = StringUtils.replace(values, ",", "','");
			values = "'" + values + "'";
		}
		return dao.isHaveSqJl(values);
	}
	
	//艺术团综合维护维护团内状态时更新cysl
	public boolean update_stcysl(String cysl,String stid) throws Exception{
		return dao.update_stcysl(cysl, stid);
	}
	
	
	

}
