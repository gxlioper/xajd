/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午04:14:33 
 */
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 医疗保险补办类型管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：Dlq[工号:995]
 * @时间： 2014-1-7 上午08:53:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YlbxwhDao extends SuperDAOImpl<YlbxwhForm> {

	/**
	 * 
	 * @描述:TODO(财政全额补助人员身份维护)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 下午01:48:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCzqebzlxPageList(YlbxwhForm model)
			throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select czqebzdm,czqebzmc from xg_rcsw_ylbx_czqebzlxb a where 1 = 1 ");
		if (!StringUtil.isNull(model.getCzqebzmc())) {
			params.add(model.getCzqebzmc());
			sql.append(" and a.czqebzmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(),params.toArray(new String[] {}));

	}
	
	/**
	 * 
	 * @描述:TODO(参保状况维护列表)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 下午03:29:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCbzklxPageList(YlbxwhForm model)
	throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select cbzkdm,cbzkmc from xg_rcsw_ylbx_cbzklxb a where 1 = 1 ");
		if (!StringUtil.isNull(model.getCbzkmc())) {
			params.add(model.getCbzkmc());
			sql.append(" and a.cbzkmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(),params.toArray(new String[] {}));
	
	}
	
	/**
	 * 
	 * @描述:TODO(增加财政全额补助人员身份)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 下午03:27:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean addCzqebzlxInfo(YlbxwhForm model)
	throws Exception {
		boolean b = false;
		String sql;
		sql = "select count(1) num from xg_rcsw_ylbx_czqebzlxb where czqebzmc=?  ";
		String num = dao.getOneRs(sql,
				new String[] {model.getCzqebzmc()}, "num");
		if ("0".equals(num)) {
			sql = "insert into xg_rcsw_ylbx_czqebzlxb(czqebzdm,czqebzmc) values(?,?)";
			b = dao.runUpdate(sql, new String[] { model.getCzqebzdm(),model.getCzqebzmc() });
		} else {
			// msg="补助类型已存在！";
			throw new SystemException(MessageKey.RCSW_DXSYLBX_BZLXCZ);
		}
	
		return b;
	}
	
	/**
	 * 
	 * @描述:TODO(修改财政全额补助人员身份)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 下午03:28:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateCzqebzlxInfo(YlbxwhForm model)
	throws Exception {
		boolean b = false;
		String sql1;
		String sql;
		sql1 = "select count(1) num from xg_rcsw_ylbx_czqebzlxb where czqebzmc=?  ";
		String num = dao.getOneRs(sql1,
				new String[] { model.getCzqebzmc() }, "num");
		//用相同的财政全额补助人员身份名称和财政全额补助人员身份代码  查找财政全额补助人员身份是否已存在
		sql = "select czqebzdm  from xg_rcsw_ylbx_czqebzlxb where czqebzdm = ?  and czqebzmc=? ";
		String czqebzdm = dao.getOneRs(sql, new String[] { model.getCzqebzdm(),model.getCzqebzmc()},"czqebzdm");
		if ((czqebzdm.equals(model.getCzqebzdm())) || "0".equals(num)) {
			sql = "update xg_rcsw_ylbx_czqebzlxb set czqebzmc=? where czqebzdm=?";
			b = dao.runUpdate(sql,
					new String[] { model.getCzqebzmc(),model.getCzqebzdm() });
		} else {
			// msg="补助类型已存在！";
			throw new SystemException(MessageKey.RCSW_DXSYLBX_BZLXCZ);
		}
		return b;
	}
	
	
	/**
	 * 
	 * @描述:TODO(获取财政全额补助人员身份代码)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 上午10:05:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getMaxCzqelxdm() {
		String sql = "select max(to_number(czqebzdm)) max from xg_rcsw_ylbx_czqebzlxb ";
		return dao.getOneRs(sql, new String[]{}, "max");
	}

	
	/**
	 * 
	 * @描述:TODO(获取财政全额补助人员身份)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 下午04:04:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param xszbblxdm
	 * @return
	 * @throws Exception
	 * YlbxwhForm 返回类型 
	 * @throws
	 */
	public YlbxwhForm getCzqebzlxForm(YlbxwhForm t, String czqebzdm)
			throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("select a.* from xg_rcsw_ylbx_czqebzlxb a  ");
		sql.append(" where czqebzdm = ? ");

		return this.getModel(t, sql.toString(), new String[] { czqebzdm });

	}
	
	
	/**
	 * 
	 * @描述:TODO(增加参保状况类型)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-7 上午08:41:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean addCbzklxInfo(YlbxwhForm model)
	throws Exception {
		boolean b = false;
		String sql;
		sql = "select count(1) num from xg_rcsw_ylbx_cbzklxb where cbzkmc=?  ";
		String num = dao.getOneRs(sql,
				new String[] {model.getCbzkmc()}, "num");
		if ("0".equals(num)) {
			
			sql = "insert into xg_rcsw_ylbx_cbzklxb(cbzkdm,cbzkmc) values(?,?)";
			b = dao.runUpdate(sql, new String[] { model.getCbzkdm(),model.getCbzkmc() });
			
		} else {
			throw new SystemException(MessageKey.RCSW_DXSYLBX_CBZKLXCZ);//参保状况类型已存在！
		}
	
		return b;
	}
		
	
	/**
	 * 
	 * @描述:TODO(修改参保状况类型)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 下午03:28:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateCbzklxInfo(YlbxwhForm model)
	throws Exception {
		boolean b = false;
		String sql1;
		String sql;
		sql1 = "select count(1) num from xg_rcsw_ylbx_cbzklxb where cbzkmc=?  ";
		String num = dao.getOneRs(sql1,
				new String[] { model.getCbzkmc() }, "num");
		//用相同的参保状况类型名称和参保状况类型代码  查找参保状况类型是否已存在
		sql = "select cbzkdm  from xg_rcsw_ylbx_cbzklxb where cbzkdm = ?  and cbzkmc=? ";
		String cbzkdm = dao.getOneRs(sql, new String[] { model.getCbzkdm(),model.getCbzkmc()},"cbzkdm");
		if ((cbzkdm.equals(model.getCbzkdm())) || "0".equals(num)) {
			sql = "update xg_rcsw_ylbx_cbzklxb set cbzkmc=? where cbzkdm=?";
			b = dao.runUpdate(sql,new String[] {model.getCbzkmc(),model.getCbzkdm()});
		} else {
			throw new SystemException(MessageKey.RCSW_DXSYLBX_CBZKLXCZ);//参保状况类型已存在！
		}
		return b;
	}
	
	
	
	/**
	 * 
	 * @描述:TODO(获取参保状况代码)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 上午10:05:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getMaxCbzklxdm() {
		String sql = "select max(to_number(cbzkdm)) max from xg_rcsw_ylbx_cbzklxb  ";
		return dao.getOneRs(sql, new String[]{}, "max");
	}
	
	
	
	
	
	
	
	/**
	 * 
	 * @描述:TODO(获取参保状况)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-7 上午09:56:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param czqebzdm
	 * @return
	 * @throws Exception
	 * YlbxwhForm 返回类型 
	 * @throws
	 */
	public YlbxwhForm getCbzklxForm(YlbxwhForm t, String cbzkdm)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.* from xg_rcsw_ylbx_cbzklxb a  ");
		sql.append(" where cbzkdm = ? ");
		return this.getModel(t, sql.toString(), new String[] { cbzkdm });
	}
	
	
	
	
	/**
	 * 获得行为大类信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXwdlList(YlbxwhForm model) throws Exception {
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


	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("xg_rcsw_ylbx_czqebzlxb");
		super.setKey("czqebzdm");
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlbxwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YlbxwhForm t)
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
	public boolean isCanDel(String czqebzdm){
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) num from ( ");
		sql.append(" select xh  from xg_rcsw_ylbx_ylbxsqb where czqebzdm like '%"+czqebzdm+"%' union all ");
		sql.append(" select xh from xg_rcsw_ylbx_ylbxjgb where czqebzdm like '%"+czqebzdm+"%' ) ");
		String num = dao.getOneRs(sql.toString(), new String[] {}, "num");
		return num.equals("0")?true:false;
		
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
	public HashMap<String, String> getCzqebzdm(String czqebzdm){
		StringBuffer sb=new StringBuffer();
		sb.append(" select a.czqebzmc from xg_rcsw_ylbx_czqebzlxb a ");
		sb.append(" where  a.czqebzdm = ? ");
		return dao.getMapNotOut(sb.toString(),new String[]{czqebzdm});
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
	public HashMap<String, String> getCbzkdm(String cbzkdm){
		StringBuffer sb=new StringBuffer();
		sb.append(" select a.cbzkmc from xg_rcsw_ylbx_cbzklxb a ");
		sb.append(" where  a.cbzkdm = ? ");
		return dao.getMapNotOut(sb.toString(),new String[]{cbzkdm});
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-16 下午05:06:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cbzkdm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cbzkdmIsCanDel(String cbzkdm){
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) num from ( ");
		sql.append(" select xh  from xg_rcsw_ylbx_ylbxsqb where cbzkdm like '%"+cbzkdm+"%' union all ");
		sql.append(" select xh from xg_rcsw_ylbx_ylbxjgb where cbzkdm like '%"+cbzkdm+"%' ) ");
		String num = dao.getOneRs(sql.toString(), new String[] {}, "num");
		return num.equals("0")?true:false;
		
	}
	
	/**
	 * 
	 * @描述:TODO(删除参保状况类型)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-2-21 下午05:08:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int  deleteCbzklxb (String[] values) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" delete from xg_rcsw_ylbx_cbzklxb where cbzkdm in  ");
		sql.append(" ( ");
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("?");
			params.add(values[i]);
			if(i != n-1){
				sql.append(",");
			}
		}
		sql.append(" ) ");
		return dao.runDelete(sql.toString(), params.toArray(new String[]{}) );
	}
	

}
