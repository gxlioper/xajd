/**
 * @部门:学工产品事业部
 * @日期：2016-3-8 上午10:57:51 
 */
package xsgzgl.rcsw.wsbz.yyrq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： CP[工号:1352]
 * @时间： 2016-3-8 上午10:57:51
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class YyrqDao extends SuperDAOImpl<YyrqForm> {
	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(YyrqForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from yyrqb");
		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YyrqForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("yyrqb");
		super.setKey("id");
	}

	/**
	 * @描述: 增加
	 */
	public boolean addLxInfo(YyrqForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into yyrqb(yyrq) values(?)");
		return dao.runUpdate(sql.toString(), new String[] { model.getYyrq() });
	}

	/**
	 * @描述:修改
	 */
	public boolean updateLxInfo(YyrqForm model) throws Exception {

			StringBuilder sql = new StringBuilder();
			sql.append("update yyrqb set yyrq=? where id=?");
			return dao.runUpdate(sql.toString(), new String[] { model.getYyrq(),model.getId() });
		
	}

	/** 
	 * @描述:验证唯一性
	 */
	public boolean isExist(YyrqForm model) {
		String sql = "select count(1) num from yyrqb where yyrq=?  " ;
		String num = dao.getOneRs(sql, new String[]{ model.getYyrq()}, "num");
		return Integer.valueOf(num)>0;
	}
	public boolean isxgExist(YyrqForm model) {
		String sql = "select count(1) num from yyrqb where yyrq=? and id<>?" ;
		String num = dao.getOneRs(sql, new String[]{ model.getYyrq(),model.getId() }, "num");
		return Integer.valueOf(num)>0;
	}

}