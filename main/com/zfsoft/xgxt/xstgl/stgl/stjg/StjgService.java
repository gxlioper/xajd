/**
 * @部门:学工产品事业部
 * @日期：2015-8-4 上午11:40:07 
 */
package com.zfsoft.xgxt.xstgl.stgl.stjg;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-4 上午11:40:07
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class StjgService extends SuperServiceImpl<StjgForm, StjgDao> {
	//private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private StjgDao dao = new StjgDao();

	public boolean isHaveSbjg(StjgForm model) {
		return dao.isHaveSbjg(model);
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:社团结果增加保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-4 上午09:54:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean editStjg(StjgForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String stid = UniqID.getInstance().getUniqIDHash();
			model.setStid(stid);
			result = dao.runInsert(model);
			//插入指导教师表
			boolean aa =dao.saveZdls(stid, model.getStid(), model.getXhs());
		} else {
			result = dao.runUpdate(model);
			//插入指导教师表
			boolean aa =dao.saveZdls(model.getStid(), model.getStid(), model.getXhs());
			
		}
		return result;
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:获取社团结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-4 下午04:40:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String,String> getStjg(StjgForm t) throws Exception {
		return dao.getStjg(t);
	}
	public boolean isHaveSqJl(String values) throws Exception {
		if(values != null){
			values = StringUtils.replace(values, ",", "','");
			values = "'" + values + "'";
		}
		return dao.isHaveSqJl(values);
	}
	
	//社团综合维护维护团内状态时更新cysl
	public boolean update_stcysl(String cysl,String stid) throws Exception{
		return dao.update_stcysl(cysl, stid);
	}
	public List<HashMap<String, String>> getStxxCylist(StjgForm stjg,User user){
		return dao.getStxxCylist(stjg, user);
	}
	public List<HashMap<String, String>> getBbdmlist(){
		return dao.getBbdmlist();
	}

	public List<HashMap<String,String>> getZdlsInfo(StjgForm myForm) {
		return dao.getZdlsInfo(myForm);
	}

    public List<HashMap<String,String>> getJgAll(StjgForm model, User user) throws Exception{
		return dao.getJgAll(model,user);
    }
}
