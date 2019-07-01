/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午04:14:33 
 */
package com.zfsoft.xgxt.rcsw.xszbb.xszbblxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常行为 管理模块
 * @类功能描述: 日常行为 代码维护
 * @作者： dlq [工号：995]
 * @时间： 2013-7-30 下午04:14:33
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XszbblxwhDao extends SuperDAOImpl<XszbblxwhForm> {

	/**
	 * 
	 * @描述:TODO(学生证补办类型)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 上午08:42:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBblxPageList(XszbblxwhForm model)
			throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.xszbblxdm,a.xszbblxmc,decode(a.zjlxbs,'1','是','否')zjlxbs, ");
		sql.append(" decode(e.lcxx,'','无需审核',mc || '：' || replace(lcxx, ';', '->')) lcxx ");
		sql.append(" from xg_rcsw_xszbb_bblxwhb a  left join ");
		sql.append(" (select splc, mc, lcxx from (select splc,");
		sql.append(" a.mc,to_char(WM_CONCAT(c.mc) over(partition by splc order by xh)) lcxx,");
		sql.append(" xh,row_number() over(partition by splc order by xh desc) as ddd ");
		sql.append(" from xg_xtwh_splc a,xg_xtwh_spbz b,xg_xtwh_spgw c ");
		sql.append(" where djlx = 'rcsw' and a.id = b.splc and b.spgw = c.id) b ");
		sql.append(" where ddd = 1) e on a.shlc = e.splc");
		sql.append(" where 1 = 1");
		
		
		if (!StringUtil.isNull(model.getXszbblxmc())) {
			params.add(model.getXszbblxmc());
			sql.append(" and a.xszbblxmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(),
				params.toArray(new String[] {}));

	}
	
	
	
	/**
	 * 
	 * @描述:TODO(增加学生证补办类型)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 上午08:43:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean addBblxInfo(XszbblxwhForm model)
	throws Exception {
		boolean b = false;
		String sql;
		sql = "select count(1) num from xg_rcsw_xszbb_bblxwhb where xszbblxmc=?  ";
		String num = dao.getOneRs(sql,
				new String[] {model.getXszbblxmc()}, "num");
		if ("0".equals(num)) {
			sql = "insert into xg_rcsw_xszbb_bblxwhb(xszbblxdm,xszbblxmc,shlc) values(?,?,?)";
			b = dao.runUpdate(sql, new String[] { model.getXszbblxdm(),
					model.getXszbblxmc(),model.getShlc() });
		} else {
			// msg="该学生证补办类型名称已存在！";
			throw new SystemException(MessageKey.RCSW_XSZBB_BBLXMCCZ);
		}
	
		return b;
	}
	
	/**
	 * 
	 * @描述:TODO(修改学生证补办类型)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 上午09:18:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateBblxInfo(XszbblxwhForm model)
	throws Exception {
		boolean b = false;
		String sql1;
		String sql;
		sql1 = "select count(1) num from xg_rcsw_xszbb_bblxwhb where xszbblxmc=? and  xszbblxdm != ? ";
		String num = dao.getOneRs(sql1,
				new String[] {model.getXszbblxmc(), model.getXszbblxdm()}, "num");
		//用相同的补办类型名称和补办类型代码  查找学生证补办类型是否已存在
		//sql = "select xszbblxdm  from xg_rcsw_xszbb_bblxwhb where xszbblxdm = ?  and xszbblxmc=? ";
		//String xszbblxdm = dao.getOneRs(sql, new String[] { model.getXszbblxdm(),model.getXszbblxmc()},"xszbblxdm");
		if ("0".equals(num)) {
			sql = "update xg_rcsw_xszbb_bblxwhb set xszbblxmc=?,shlc=? where xszbblxdm=?";
			b = dao.runUpdate(sql,
					new String[] { model.getXszbblxmc(),model.getShlc(),model.getXszbblxdm() });
		} else {
			// msg="该学生证补办类型名称已存在！";
			throw new SystemException(MessageKey.RCSW_XSZBB_BBLXMCCZ);
		}
		return b;
	}
	
	
	/**
	 * 
	 * @描述:TODO(获取学生证补办类型代码最大序号值)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 上午10:05:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getMaxXszbblxdm() {
		String sql = "select max(to_number(xszbblxdm)) max from xg_rcsw_xszbb_bblxwhb ";
		return dao.getOneRs(sql, new String[]{}, "max");
	}

	
	/**
	 * 
	 * @描述:TODO(修改学生证补办类型 单个查询)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 上午10:56:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param xszbblxdm
	 * @return
	 * @throws Exception
	 * XszbblxwhForm 返回类型 
	 * @throws
	 */
	public XszbblxwhForm getXszbblxwhForm(XszbblxwhForm t, String xszbblxdm)
			throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("select a.* from xg_rcsw_xszbb_bblxwhb a  ");
		sql.append(" where xszbblxdm = ? ");

		return this.getModel(t, sql.toString(), new String[] { xszbblxdm });

	}
	/**
	 * 获得行为大类信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXwdlList(XszbblxwhForm model) throws Exception {
		String searchTj = "";
		List<String> inputV = new ArrayList<String>();
		String sql = "select * from (select a.*,rownum r from xg_rcsw_rcxwdbdlb a order by a.rcxwlbdldm) ";
		String[] output = new String[] { "RCXWLBDLDM", "RCXWLBDLDM",
				"RCXWLBDLMC" };
		return CommonQueryDAO.commonQuery(sql, searchTj,
				inputV.toArray(new String[] {}), output, model);

	}

	/**
	 * 
	 * @描述:TODO(删除学生证补办类型维护)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-20 上午11:21:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int deleteXszbblxwhInfo(String[] values) throws Exception {
		int num = 0;
		String sql = "delete from xg_rcsw_xszbb_bblxwhb where xszbblxdm in ("
				+ values + ")";
		num = dao.runDelete(sql, new String[] {});
		return num;
	}

	/**
	 * 
	 * @描述:TODO(判断补办类型名称是否已经被补办申请 和 补办结果应用)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-20 上午11:21:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] checkXszbbdmForbbsq(String values) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.xszbblxmc from xg_rcsw_xszbb_bblxwhb a left join ");
		sql.append(" xg_rcsw_xszbb_xszbbsqb b on a.xszbblxdm = b.xszbblxdm ");
		sql.append(" left join xg_rcsw_xszbb_xszbbjgb c on a.xszbblxdm = c.xszbblxdm ");
		sql.append(" where a.xszbblxdm  in(" + values + ")  and  ( b.xszbblxdm is not null or  c.xszbblxdm is not null ) " );
		String[] xszbblxmc = dao.getRs(sql.toString(), new String[] {},"xszbblxmc");
		return xszbblxmc;
	}


	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("xg_rcsw_xszbb_bblxwhb");
		super.setKey("xszbblxdm");
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XszbblxwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XszbblxwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-20 下午12:33:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xszbblxdm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isCanDel(String xszbblxdm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct a.xszbblxmc from xg_rcsw_xszbb_bblxwhb a left join ");
		sql.append(" xg_rcsw_xszbb_xszbbsqb b on a.xszbblxdm = b.xszbblxdm ");
		sql.append(" left join xg_rcsw_xszbb_xszbbjgb c on a.xszbblxdm = c.xszbblxdm ");
		sql.append(" where a.xszbblxdm  = ?   and  ( b.xszbblxdm is not null or  c.xszbblxdm is not null ) " );
		Map<String,String> map= dao.getMapNotOut(sql.toString(),new String[]{xszbblxdm});
		String bblxmc = map.get("xszbblxmc");
		//如果未提交才可以提交
		return bblxmc==null?true:false;
	}
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-20 下午12:32:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xszbblxdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getBbsq(String xszbblxdm){
		StringBuffer sb=new StringBuffer();
		sb.append(" select a.xszbblxmc from xg_rcsw_xszbb_bblxwhb a ");
		sb.append(" where  a.xszbblxdm = ? ");
		return dao.getMapNotOut(sb.toString(),new String[]{xszbblxdm});
	}
}
