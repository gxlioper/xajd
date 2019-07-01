/**
 * @部门:学工产品事业部
 * @日期：2015-6-25 下午03:52:37 
 */  
package com.zfsoft.xgxt.szdw.gzjl.lbgl;

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
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-6-25 下午03:52:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzjlLbglDao extends SuperDAOImpl<GzjlLbglForm> {

	@Override
	public List<HashMap<String, String>> getPageList(GzjlLbglForm model) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_gzjlgl_gzlbb a where 1=1");
		if (!StringUtil.isNull(model.getLbmc())) {
			params.add(model.getLbmc());
			sql.append(" and a.lbmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}

	/**
	 * 获取工作记录类别列表
	 */
	@Override
	public List<HashMap<String, String>> getPageList(GzjlLbglForm model, User user) throws Exception {
		return null;
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:增加工作记录类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-2 下午03:52:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean addGzjllb(GzjlLbglForm model) throws Exception {
		boolean flag = false;
		String sql;
		sql = "select count(1) num from xg_gzjlgl_gzlbb where lbdm=? ";
		String num = dao.getOneRs(sql, new String[] { model.getLbdm() }, "num");
		if ("0".equals(num)) {
			sql = "insert into xg_gzjlgl_gzlbb(LBDM,LBMC,LBSM,XSSX) values(?,?,?,?)";
			flag = dao.runUpdate(sql, new String[] { model.getLbdm(), model.getLbmc(), model.getLbsm(),model.getXssx() });
		} else {
			throw new SystemException(MessageKey.GZJL_LBGL_LBYCZ);
		}

		return flag;

	}

	/**
	 *删除
	 */
	public int deleteGzjllb(String values) throws Exception {
		String sql = "delete from xg_gzjlgl_gzlbb where lbdm =?";
		return dao.runDelete(sql, new String[] {values});

	}
	

	/**
	 *获取工作记录类别
	 */
	public String getGzjllbmc(String lbdm) throws Exception {
		String sql = "select lbmc from xg_gzjlgl_gzlbb where lbdm =?";
		return dao.getOneRs(sql, new String[]{lbdm}, "lbmc");

	}
	
	public boolean isExistsXmData(String lbdm) throws Exception{
		String sql ="select count(*) num from (select lbdm from xg_gzjlgl_gzjlsqb where lbdm=? union all";
		sql+=" select lbdm from xg_gzjlgl_gzjljgb where lbdm=?)";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{lbdm,lbdm}, "num"))>0;
		
	}
	
	public List<HashMap<String,String>> getGzjllbList() throws Exception {
		String sql ="select lbdm gzlbdm,lbmc gzlbmc from xg_gzjlgl_gzlbb order by xssx ";
		return dao.getListNotOut(sql, new String[]{});

	}

	@Override
	protected void setTableInfo() {
		super.setClass(GzjlLbglForm.class);
		super.setTableName("xg_gzjlgl_gzlbb");
		super.setKey("lbdm");
	}
}
