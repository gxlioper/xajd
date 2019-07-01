/**
 * @部门:学工产品事业部
 * @日期：2015-7-7 下午07:29:38 
 */  
package xsgzgl.jxgl.general.jxkqgl.jxkqjg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-7-7 下午07:29:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JxkqjgService extends SuperServiceImpl<JxkqjgForm, JxkqjgDao> {
	/**
	 * 
	 * @描述:获取考勤类型列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-6 上午10:23:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKqlx() {
		return dao.getKqlx();
	}
	/**
	 * 
	 * @描述:获取考勤类别列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-8 上午10:49:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKqlb() {
		return dao.getKqlb();
	}
	/**
	 * 
	 * @描述:获取考勤信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-8 上午11:39:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param kqid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getKqxx(String kqid)
	throws Exception {
	return dao.getKqxx(kqid);
	}
	
	public List<HashMap<String, String>> getJxxsList(JxkqjgForm model,User user) throws Exception {
		return dao.getJxxsList(model,user);
	}

}
