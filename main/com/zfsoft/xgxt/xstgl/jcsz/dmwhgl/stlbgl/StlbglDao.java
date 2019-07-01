/**
 * @部门:学工产品事业部
 * @日期：2015-07-31 下午02:33:17 
 */
package com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.stlbgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-07-31 下午02:33:17
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class StlbglDao extends SuperDAOImpl<StlbglForm> {

	@Override
	public List<HashMap<String, String>> getPageList(StlbglForm model) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_stgl_stlb a where 1=1");
		if (!StringUtil.isNull(model.getStlbmc())) {
			params.add(model.getStlbmc());
			sql.append(" and a.stlbmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}

	/**
	 * 获取社团类别列表
	 */
	@Override
	public List<HashMap<String, String>> getPageList(StlbglForm model, User user) throws Exception {
		return null;
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:增加社团类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-07-31 下午03:52:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean addStlb(StlbglForm model) throws Exception {
		boolean flag = false;
		String sql;
		sql = "select count(1) num from xg_stgl_stlb where  stlbdm=?  or  stlbmc = ?";
		String num = dao.getOneRs(sql, new String[] { model.getStlbdm(),model.getStlbmc() }, "num");
		if ("0".equals(num)) {
			flag=runInsert(model);
		} else {
			throw new SystemException(MessageKey.STGL_JCSZ_STLB_REPEAT);
		}

		return flag;

	}
	
	/**
	 *获取社团类别
	 */
	public String getStlbmc(String stlbdm) throws Exception {
		String sql = "select stlbmc from xg_stgl_stlb where stlbdm =?";
		return dao.getOneRs(sql, new String[]{stlbdm}, "stlbmc");

	}
	
	public boolean isExistsXmData(String lbdm) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) num from(select stlbdm from xg_stgl_xmlb where stlbdm=? ");
		sql.append(" union all select stlbdm from  xg_stgl_jtsq where stlbdm=?");
		sql.append(" union all select stlbdm from  xg_stgl_jtjg where stlbdm=?)");
		return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{lbdm,lbdm,lbdm}, "num"))>0;
		
	}
	/**
	 *获取社团类别列表
	 */
	public List<HashMap<String, String>> getStlbList(){
		String sql = "select * from xg_stgl_stlb order by stlbdm asc";
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_stgl_stlb");
		super.setKey("stlbdm");
	}

}
