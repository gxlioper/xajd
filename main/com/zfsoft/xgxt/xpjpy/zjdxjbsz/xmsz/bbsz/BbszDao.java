/**
 * @部门:学工产品(1)部
 * @日期：2017-4-20 上午09:16:01 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.bbsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-基本设置-项目设置-报表设置
 * @类功能描述: 登记表、上报表设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-4-20 上午09:16:35 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BbszDao extends SuperDAOImpl<BbszForm> {
	protected void setTableInfo() {
		super.setTableName("XG_PJPY_NEW_XMSZBBDMB");
		super.setKey("bbdm");
		super.setClass(BbszForm.class);
	}
	
	/**
	 * 普通查询
	 */
	@Override
	public List<HashMap<String, String>> getPageList(BbszForm t)
			throws Exception {
		return null;
	}
	
	/**
	 * 高级查询
	 */
	@Override
	public List<HashMap<String, String>> getPageList(BbszForm t, User user)
			throws Exception {
		return null;
	}

	/**
	 * @描述: 查询登记表相关信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-20 上午10:30:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bblx
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBbxxList(String bblx){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.bbdm,a.bbmc,a.mblj,a.mbmc,a.bblx,b.bblj,b.dyym from xg_pjpy_new_xmszbbdmb a ");
		sql.append("left join xg_pjpy_new_bbdytpb b on a.bbdm = b.bbdm ");
		sql.append("where b.dyym ='1' and a.bblx = ? ");
		sql.append("order by a.mbmc ");
		String[] input = {bblx};
		return dao.getListNotOut(sql.toString(), input);
	}
	
	/**
	 * @描述: 报表预览
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-20 下午03:39:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBbxxYlList(String bbdm) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.bblj from xg_pjpy_new_xmszbbdmb t1 ");
		sql.append("left join xg_pjpy_new_bbdytpb t2 on t1.bbdm = t2.bbdm where t1.bbdm = ? order by t2.dyym ");
		String[] input = new String[]{bbdm};
		return dao.getListNotOut(sql.toString(), input);
	}
}
