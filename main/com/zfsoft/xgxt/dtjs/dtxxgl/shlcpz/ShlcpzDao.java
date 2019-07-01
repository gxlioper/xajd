/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:07:04 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团信息管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:07:04
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ShlcpzDao extends SuperDAOImpl<ShlcpzForm> {

	@Override
	public List<HashMap<String, String>> getPageList(ShlcpzForm t, User user)
			throws Exception {
		return null;
	}

	@Override
	protected void setTableInfo() {
		this.setKey("jddm");
		this.setTableName("XG_DTJS_JBSZB");
		this.setClass(ShlcpzForm.class);
	}

	@Override
	public List<HashMap<String, String>> getPageList(ShlcpzForm t)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select d.jddm,d.jdmc,");
		// 查询审核流程
		sql
				.append(" decode(e.lcxx, '', '无需审核', mc || '：' || replace(lcxx, ';', '->')) lcxx,");
		sql.append(" decode(d.ksqkg,'1','开启','关闭') ksqkgxx,");
		sql.append(" case ");
		sql.append("   when d.ksqkg <> '1' then '' ");
		sql.append("   when d.ksqjssj is not null then d.ksqkssj || ' ～ '|| d.ksqjssj ");
		sql.append("   when d.ksqkssj is not null then d.ksqkssj||  ' ～ ' ");
		sql.append("   else '' end qzsj ");
		sql.append(" from XG_DTJS_JBSZB d left join (select splc, mc, lcxx ");
		sql
				.append(" from (select splc,a.mc,to_char(WM_CONCAT(c.mc) over (partition by splc order by xh )) lcxx, xh, ");
		sql.append(" row_number() over(partition by splc order by xh desc) as ddd");
		sql
				.append(" from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c where djlx = ? and a.id = b.splc ");
		sql
				.append(" and b.spgw = c.id) b where ddd = 1 ) e on d.splc = e.splc where 1 = 1 ");
		sql.append(" and d.splc is not null");
		if (!StringUtils.isNull(t.getJdmc())) {
			sql.append(" and jdmc like '%" + t.getJdmc() + "%'");
		}
		if (StringUtils.equals("13431", Base.xxdm)) {
			sql.append(" order by jdsx*1");
		}
		return this.getPageList(t, sql.toString(), new String[] {ShlcpzAction._DTXX_SPLC_NAME});
	}

	/**
	 * 
	 * @描述:获得可以增加阶段代码
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-22 下午05:12:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getJdList() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select d.jddm,d.jdmc");
		sql
				.append(" from XG_DTJS_JBSZB d where d.SFKPZSHL=? and d.splc is null order by to_number(d.jdsx) asc");
		return dao.getListNotOut(sql.toString(),
				new String[] { Constants.SHLC_SFKPZSHLC_KEYPZ });
	}

	/**
	 * 
	 * @描述:获取个人阶段相关信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-28 上午10:15:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getGrJdxx(String xh) throws Exception {
		StringBuffer sql = new StringBuffer();
		List<String> param = new ArrayList<String>();
		sql.append(" select b.xh,a.jddm,a.jdmc,a.splc,b.grxj,b.zd1,b.zd2,b.zd3,b.zd5,b.zd8,b.zd9,b.zd10,b.kssj,b.xh||b.jddm dtxxjgid,a.ksqkg,a.ksqkssj,a.ksqjssj,b.sjly");
		sql
				.append(" from XG_DTJS_JBSZB a left join (select * from XG_DTJS_XSDTXXJLB where 1=1");
		if (StringUtils.isNotNull(xh)) {
			sql.append("and xh=?");
			param.add(xh);
		}else{
			xh="";
			sql.append("and xh=?");
			param.add(xh);
		}
		sql.append(") b on a.jddm=b.jddm order by to_number(a.jdsx)");
		return dao.getListNotOut(sql.toString(), param.toArray(new String[]{}));
	}
}
