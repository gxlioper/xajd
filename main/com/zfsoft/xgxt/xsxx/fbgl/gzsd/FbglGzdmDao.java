/**
 * @部门:学工产品事业部
 * @日期：2014-1-27 上午10:24:45 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-27 上午10:24:45
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglGzdmDao extends SuperDAOImpl<FbglGzdmForm> {

	@Override
	public List<HashMap<String, String>> getPageList(FbglGzdmForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(FbglGzdmForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	protected void setTableInfo() {
		this.setKey("gzdm");
		this.setTableName("xg_xsxx_fbbxhgl_gzdmb");
		this.setClass(FbglGzdmForm.class);
	}

	public List<HashMap<String, String>> getGzList() {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from xg_xsxx_fbbxhgl_gzdmb");
		return dao.getList(sb.toString(), new String[] {}, new String[] {
				"gzdm", "gzmc" });
	}
	/**
	 * 
	 * @描述: 获取规则名称
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-18 下午01:58:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gzdm
	 * @return
	 * String 返回类型 
	 */
	public String getGzmc(String gzdm){
		StringBuffer sb = new StringBuffer();
		sb.append("select gzmc from xg_xsxx_fbbxhgl_gzdmb where gzdm=?");
		return dao.getOneRs(sb.toString(), new String[]{gzdm}, "gzmc");
	}
}
