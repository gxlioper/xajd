/**
 * @部门:学工产品事业部
 * @日期：2013-5-27 下午02:02:35 
 */
package com.zfsoft.xgxt.jygl.zfss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 社区管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： huj
 * @时间： 2013-5-27 下午02:02:35
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZfssDao extends SuperDAOImpl<ZfssForm> {

	/**
	 * 走访登记列表
	 */
	public List<HashMap<String, String>> getPageList(ZfssForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(" SELECT A.*,B.XM,  decode(b.xb, '1', '男', '2', '女', '男', '男', '女', '女') as xb,(select bmmc from zxbz_xxbmdm where bmdm=b.bmdm) bmmc,");
		sql.append(" C.YQMC,D.LDMC FROM HQ_JYGL_FDYZFDJ A LEFT JOIN FDYXXB B ON A.ZGH=B.ZGH ");
		sql.append(" LEFT JOIN ZXBZ_SSYQDM C ON A.BFWYQ= C.YQDM LEFT JOIN XG_GYGL_NEW_LDXXB D ON A.BFWLD = D.LDDM WHERE 1=1 ");
		if (!StringUtil.isNull(t.getZgh())) {
			params.add(t.getZgh());
			sql.append(" AND A.ZGH LIKE '%'||?||'%' ");
		}
		if (!StringUtil.isNull(t.getJrsjks())) {
			params.add(t.getJrsjks());
			sql
					.append(" AND TO_DATE(A.JRSJ,'yyyy-mm-dd HH24:MI:SS') >= TO_DATE(?||' 00:00:00','yyyy-mm-dd HH24:MI:SS') ");
		}
		if (!StringUtil.isNull(t.getJrsjjz())) {
			params.add(t.getJrsjjz());
			sql
					.append(" AND TO_DATE(A.JRSJ,'yyyy-mm-dd HH24:MI:SS') <= TO_DATE(?||' 23:59:59','yyyy-mm-dd HH24:MI:SS') ");
		}
		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
	}

	public List<HashMap<String, String>> getPageList(ZfssForm t, User user)
			throws Exception {
		return null;
	}

	protected void setTableInfo() {
		super.setTableName("HQ_JYGL_FDYZFDJ");
		super.setKey("djid");// 主键一定要和FORM中的set方法的属性一致,即大小写
		super.setClass(ZfssForm.class);
	}
	
	public ZfssForm getModel(ZfssForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,b.xm as zgxm ,decode(b.xb, '1', '男', '2', '女', '男', '男', '女', '女') as xb,(select bmmc from zxbz_xxbmdm where bmdm=b.bmdm) bmmc from HQ_JYGL_FDYZFDJ t left join fdyxxb b on t.zgh=b.zgh where t.djid=?");
		return super.getModel(t, sql.toString(), new String[]{t.getDjid()});
	}

	/**
	 * 
	 * @描述:走访登记汇总
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-30 下午02:21:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZfssCountList(ZfssForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(
				" SELECT B.XM,COUNT(*) AS CS FROM HQ_JYGL_FDYZFDJ A LEFT JOIN FDYXXB B ON A.ZGH=B.ZGH " +
				" WHERE 1=1 ");
		if (!StringUtil.isNull(t.getZgh())) {
			params.add(t.getZgh());
			sql.append(" AND B.XM LIKE '%'||?||'%' ");
		}
		if (!StringUtil.isNull(t.getJrsjks())) {
			params.add(t.getJrsjks());
			sql.append(" AND TO_DATE(A.JRSJ,'yyyy-mm-dd HH24:MI:SS') >= TO_DATE(?||' 00:00:00','yyyy-mm-dd HH24:MI:SS') ");
		}
		if (!StringUtil.isNull(t.getJrsjjz())) {
			params.add(t.getJrsjjz());
			sql.append(" AND TO_DATE(A.JRSJ,'yyyy-mm-dd HH24:MI:SS') <= TO_DATE(?||' 23:59:59','yyyy-mm-dd HH24:MI:SS') ");
		}
		sql.append(" GROUP BY B.XM ");
		//return dao.getListNotOut(sql.toString(), params.toArray(new String[] {}));
		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
	}

}
