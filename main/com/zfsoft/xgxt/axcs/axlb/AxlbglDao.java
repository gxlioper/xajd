/**
 * @部门:学工产品事业部
 * @日期：2014-12-2 下午02:33:17 
 */
package com.zfsoft.xgxt.axcs.axlb;

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
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-2 下午02:33:17
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class AxlbglDao extends SuperDAOImpl<AxlbglForm> {

	@Override
	public List<HashMap<String, String>> getPageList(AxlbglForm model) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xszz_axcslbb a where 1=1");
		if (!StringUtil.isNull(model.getMc())) {
			params.add(model.getMc());
			sql.append(" and a.mc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}

	/**
	 * 获取爱心类别列表
	 */
	@Override
	public List<HashMap<String, String>> getPageList(AxlbglForm model, User user) throws Exception {
		return null;
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:增加爱心类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-2 下午03:52:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean addAxlb(AxlbglForm model) throws Exception {
		boolean flag = false;
		String sql;
		sql = "select count(1) num from xg_xszz_axcslbb where dm=? ";
		String num = dao.getOneRs(sql, new String[] { model.getDm() }, "num");
		if ("0".equals(num)) {
			sql = "insert into xg_xszz_axcslbb(DM,MC,SM) values(?,?,?)";
			flag = dao.runUpdate(sql, new String[] { model.getDm(), model.getMc(), model.getSm() });
		} else {
			throw new SystemException(MessageKey.AXCS_AXLB_AXLBYCZ);
		}

		return flag;

	}

	/**
	 *删除
	 */
	public int deleteAxlb(String values) throws Exception {
		String sql = "delete from xg_xszz_axcslbb where dm =?";
		return dao.runDelete(sql, new String[] {values});

	}
	

	/**
	 *获取爱心类别
	 */
	public String getAxlbmc(String lbdm) throws Exception {
		String sql = "select mc axlbmc from xg_xszz_axcslbb where dm =?";
		return dao.getOneRs(sql, new String[]{lbdm}, "axlbmc");

	}
	
	public boolean isExistsXmData(String lbdm) throws Exception{
		String sql ="select count(*) num from xg_xszz_axcsxmb where xmlb=? ";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{lbdm}, "num"))>0;
		
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_axcslbb");
		super.setKey("dm");

	}

}
