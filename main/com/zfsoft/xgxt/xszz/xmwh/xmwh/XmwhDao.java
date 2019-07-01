/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.xmwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助
 * @类功能描述: 项目维护
 * @作者： ligl
 * @时间： 2013-4-18 下午02:42:37
 * @版本： V1.0
 * @修改记录:
 */
public class XmwhDao extends SuperDAOImpl<XmwhForm> {

	/**
	 * 
	 * @描述:普通查询方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmwhForm model)
			throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(
				"select a.xslb,dybb,jdkg,jdkzjb,je,knsbddc,knsbdxn,knsbdxq,rskg,rskzfw,rskzjb,sfkns,splc,sqjssj,sqkg,sqkssj,sqxn,sqxq,tjkg,tjkzjb,xmdm,xmmc,a.jesfxssq,b.lbmc,b.lbdm");
		sql.append(",(select case when count(*)>0 then '1' else '0' end  from xg_xszz_new_zzxmjdszb e where a.xmdm=e.xmdm) jdsz");
		sql.append(",(select case when count(*)>0 then '1' else '0' end  from xg_xszz_new_zzxmtjb f where a.xmdm=f.xmdm) tjsz ");
		sql.append(",(select case when count(*)>0 then '1' else '0' end  from xg_xszz_new_zzxmrsszb g where a.xmdm=g.xmdm and g.zzrs is not null ");
		sql.append(" and g.xn='");
		sql.append(Base.currXn);
		sql.append("' and g.xq='");
		sql.append(Base.currXq);
		sql.append("'");
		sql.append(") rssz ");
		sql.append(", case when exists (select 1 from xg_xszz_new_xmfz t2 where a.xmdm=t2.xmdm ) then '1' else '0' end jfsz");
		sql.append(",(select case when count(*)>0 then '1' else '0' end  from xg_xszz_new_zzxmshtzb h where a.xmdm=h.xmdm) shsz ");
		sql	.append(" from xg_xszz_new_zzxmdmb a,xg_xszz_new_zzxmlbb b where a.lbdm=b.lbdm and a.sqxn = '"+model.getSqxn()+"' and a.sqxq = '"+model.getSqxq()+"' ");

		if (!StringUtil.isNull(model.getXmmc())) {
			params.add(model.getXmmc());
			sql.append(" and xmmc like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getLbdm())) {
			params.add(model.getLbdm());
			sql.append(" and a.lbdm like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params
				.toArray(new String[] {}));
	}

	/**
	 * 
	 * @描述: 除当前项目外，其余的项目
	 * @作者：ligl
	 * @日期：2013-5-4 上午09:35:17
	 * @修改记录: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getOthers(String xmdm)
			throws Exception {
		List<String> input=new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmdm,xmmc,case when exists ");
		sb.append("(select 1 from xg_xszz_new_xmfz t2 where t1.xmdm=t2.xmdm ");
		sb.append(" and t2.zdm not in (select zdm from xg_xszz_new_xmfz where xmdm=?)");
		input.add(xmdm);
		sb.append(" ) then '1' else '0' end fzqk ");
		sb.append(" from  xg_xszz_new_zzxmdmb t1 ");
		sb.append(" where");
		if(!StringUtil.isNull(xmdm)){
			sb.append(" xmdm!=?  and");
			input.add(xmdm);
		}
		sb.append(" sqxn='");
		sb.append(Base.currXn);
		sb.append("' and sqxq='");
		sb.append(Base.currXq);
		sb.append("'");
		
		if(!StringUtil.isNull(xmdm)){
			sb.append(" and splc = (select splc from xg_xszz_new_zzxmdmb where xmdm=? )");
			input.add(xmdm);
		}
		return dao.getListNotOut(sb.toString(), input.toArray(new String[]{}));
	}
	
	
	/**
	 * 
	 * @描述:获取报表代码及名称
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getBb() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select bbdm,bbmc ");
		sb.append(" from  xg_xszz_new_zzxmbbdmb ");
		return dao.getListNotOut(sb.toString(), null);
	}

	/**
	 * 
	 * @描述:判断重复数据
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	public boolean isRepeat(XmwhForm model,String currXn, String currXq) throws Exception {
		boolean flag = false;
		String sql = "select count(*) count from xg_xszz_new_zzxmdmb t ";
		sql += " where t.xmmc=? and sqxn='"+currXn+"' and sqxq='"+currXq+"'";
		if(model.getLbdm() != null){
			sql += " and t.lbdm = '"+model.getLbdm()+"'";
		}
		String xmmc = model.getXmmc();
		if(xmmc!= null){
			xmmc = xmmc.trim();
		}
		String[] input = {xmmc};
		String[] output = {"count"};
		String[] oneRs = dao.getOneRs(sql,input,output);/////此方法异常已被捕获掉。，出错无法处理 。。。。。。。////。。。。。。////
		if(oneRs != null && oneRs.length > 0){
			if(!oneRs[0].equals("0")){
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:可否修改删除验证
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	public boolean isRalate(XmwhForm model) throws Exception {
		String sql = "select count(*)  from xg_xszz_new_zzxmsqb  ";
		sql += " where xmdm='"+model.getXmdm()+"'";
		int result = dao.getOneRsint(sql);/////此方法异常已被捕获掉，出错无法处理 。。。。。。。////
		return result > 0;
	}

	/**
	 * 
	 * @描述:可否修改删除验证
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	public boolean isRalate(String values) throws Exception {
		if(values != null){
			values = StringUtils.replace(values, ",", "','");
			values = "'" + values + "'";
		}
		String sql = "select count(*)  from xg_xszz_new_zzxmsqb  ";
		sql += " where xmdm in("+values+")";
		int result = dao.getOneRsint(sql);/////此方法异常已被捕获掉，出错无法处理 。。。。。。。////
		return result > 0;
	}
	
	/**
	 * 
	 * @描述:删除关联表数据
	 * @作者：ligl
	 * @日期：2013-4-27 下午05:02:47
	 * @修改记录: 
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delRalate(String keys) throws Exception{
		int[] result = null;
		List<String> sqlList = new ArrayList<String>();
		if(keys != null){
			keys = StringUtils.replace(keys, ",", "','");
			keys = "'" + keys + "'";
		}else{
			return true;
		}
		String sql = "delete from  xg_xszz_new_zzxmrsszb ";//人数设置表
		sql += " where xmdm in("+keys+")";
		sqlList.add(sql);
		
		sql = "delete from  xg_xszz_new_zzxmtjb ";//条件 设置表
		sql += " where xmdm in("+keys+")";
		sqlList.add(sql);
		
		sql = "delete from  xg_xszz_new_zzxmjdszb ";//兼得设置表
		sql += " where xmdm in("+keys+")";
		sqlList.add(sql);
		
		sql = "delete from  xg_xszz_new_zzxmjdszb ";//兼得设置表
		sql += " where kjddm in("+keys+")";
		sqlList.add(sql);
		
		sql = "delete from  xg_xszz_new_zzxmshtzb ";//审核调整奖项目设置
		sql += " where xmdm in("+keys+")";
		sqlList.add(sql);
		
		sql = "delete from  xg_xszz_new_zzxmdmb ";//项目维护表
		sql += " where xmdm in("+keys+")";
		sqlList.add(sql);
		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}
	
	/**
	 * 
	 * @描述:根据项目代码查询项目名称
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public String getNameById(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmmc xmmc ");
		sb.append(" from  xg_xszz_new_zzxmdmb ");
		sb.append(" where xmdm=?");
		String[] inputValue = {xmdm};
		String[] outputValue = {"xmmc"};
		String[] oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String xmmc = "";
		if(oneRs != null && oneRs.length > 0){
			xmmc = oneRs[0];
		}
		return xmmc;
	}
	
	/**
	 * 
	 * @描述:根据项目代码查询记录
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getDataById(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select rskzjb,rskzfw,xmdm,rskg,jdkg,zme,rskznj,pdxn,nvl(pdxq,'on')pdxq,xmmc ");
		sb.append(" from  xg_xszz_new_zzxmdmb ");
		sb.append(" where xmdm=?");
		String[] inputValue = {xmdm};
		return  dao.getMapNotOut(sb.toString(), inputValue);
	}
	
	
	/**
	 * 
	 * @描述:根据项目名称查询单条记录
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getDataByName(String xmmc, String xn, String xq) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * ");
		sb.append(" from  xg_xszz_new_zzxmdmb ");
		sb.append(" where xmmc=? and sqxn=? and sqxq=?");
		String[] inputValue = {xmmc,xn,xq};
		return  dao.getMapNotOut(sb.toString(), inputValue);
	}
	
	/**
	 * 
	 * @描述:高级查询方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmwhForm model, User user)
			throws Exception {
		return null;
	}
	
	/**
	 * 
	 * @描述:根据项目名称查询报表代码
	 * @作者：honglin
	 * @日期：2013-5-4
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXszzdm(String xmmc, String xn, String xq){
		
		String sql = "select bbdm from xg_xszz_new_zzxmbbdmb where bbdm =(select dybb from xg_xszz_new_zzxmdmb where xmmc=? and sqxn= ? and sqxq= ?)";
		return dao.getOneRs(sql, new String[]{xmmc,xn,xq}, "bbdm");
	}
	
	/**
	 * 
	 * @描述:设置总名额
	 * @作者：ligl
	 * @日期：2013-5-29 上午11:45:00
	 * @修改记录: 
	 * @param xmdm
	 * @param zme
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean setZme(String xmdm,String zme,String rskznj) throws Exception {
		String sql = "update xg_xszz_new_zzxmdmb set zme=?,rskznj=? where xmdm=? ";
		String[] input = {zme,rskznj,xmdm};
		return dao.runUpdate(sql, input);
	}
	
	/**
	 * 
	 * @描述:通过项目代码获取已经设置的年级,年级以逗号分割
	 * @作者：ligl
	 * @日期：2013-6-9 下午03:42:06
	 * @修改记录:
	 * @param dm
	 * @return
	 * @throws Exception
	 *             String 返回类型
	 * @throws
	 */
	public String getRskznj(String xmdm) throws Exception {
		String rskznj = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select rskznj from XG_XSZZ_NEW_ZZXMDMB where xmdm=? ");
		String[] input = { xmdm };
		String[] output = { "rskznj" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				rskznj = oneRs[0];
			}
		}
		return rskznj;
	}

	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_zzxmdmb");
		super.setKey("xmdm");
		super.setClass(XmwhForm.class);
	}

	/** 
	 * @描述:获取项目维护当中的周期List
	 * @作者：cq [工号：785]
	 * @日期：2014-1-21 上午11:55:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXmzqList() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct sqxn,sqxq from (select sqxn,sqxq from xg_xszz_new_zzxmdmb where sqxn is not null union all ");
		sb.append(" select dqxn,dqxq from xtszb) a order by sqxn||sqxq desc ");
		return dao.getListNotOut(sb.toString(), null);
	}

	/** 
	 * @描述:查询可复制的奖项
	 * @作者：程强 [工号：785]
	 * @日期：2014-1-21 下午04:28:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fzXn
	 * @param fzXq
	 * @param currXn
	 * @param currXq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXmfz(String fzXn, String fzXq,
			String currXn, String currXq) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * from xg_xszz_new_zzxmdmb  ");
		sb.append("  where sqxn='");
		sb.append(fzXn);
		sb.append("'");
		sb.append("  and sqxq='");
		sb.append(fzXq);
		sb.append("'");
		sb
				.append("  and xmmc not in(select xmmc from xg_xszz_new_zzxmdmb where sqxn='");
		sb.append(currXn);
		sb.append("'");
		sb.append("  and sqxq='");
		sb.append(currXq);
		sb.append("')");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}

	/**
	 * @throws SQLException  
	 * @描述:保存复制奖项
	 * @作者：cq [工号：785]
	 * @日期：2014-1-21 下午04:32:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmfzList
	 * @param currXn
	 * @param currXq
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveData(List<HashMap<String, String>> xmfzList,
			String currXn, String currXq) throws SQLException {
		
		int[] result = null;
		StringBuffer sb = new StringBuffer();
		sb.append("insert into xg_xszz_new_zzxmdmb ");
		sb
				.append("(xmmc,lbdm,je,dybb,dysbbb,splc,sfkns,sqxn,sqxq)");
		sb.append(" values(?,?,?,?,?,?,?,'");
		sb.append(currXn);
		sb.append("','");
		sb.append(currXq);
		sb.append("')");

		List<String[]> params = new ArrayList<String[]>();
		String[] param = null;
		for (HashMap<String, String> map : xmfzList) {
			param = new String[7];
			param[0] = map.get("xmmc");
			param[1] = map.get("lbdm");
			param[2] = map.get("je");
			param[3] = map.get("dybb");
			param[4] = map.get("dysbb");
			param[5] = map.get("splc");
			param[6] = map.get("sfkns");
			params.add(param);
		}

		result = dao.runBatch(sb.toString(), params);
		return true;
	}
	
	
	/**
	 * 
	 * @描述: 查询在同一个项目组下的其它项目
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-21 下午03:53:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getSameGroupXmList(String xmdm){
		
		String sql = "select * from xg_xszz_new_xmfz where zdm=(select zdm from xg_xszz_new_xmfz where xmdm=?) and xmdm <> ?";
		
		return dao.getListNotOut(sql, new String[]{xmdm,xmdm});
	}

	
	/**
	 * 
	 * @描述: 保存项目分组
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-22 上午09:13:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXmfz(List<String[]> params) throws SQLException{
		
		String sql = "insert into xg_xszz_new_xmfz(zdm,xmdm) values (?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 
	 * @描述: 保存学院经费
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-22 上午09:21:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXyjf(List<String[]> params) throws SQLException{
		
		String sql = "insert into xg_xszz_new_xmjf(zdm,xydm,je) values (?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 
	 * @描述: 查询所在组的学院经费列表 
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-22 上午10:43:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXyjfList(String xmdm){
		
		String sql = "select xydm , je from xg_xszz_new_xmjf where zdm=(select zdm from xg_xszz_new_xmfz where xmdm=?)";
		return dao.getListNotOut(sql, new String[]{xmdm});
	}
	
	
	/**
	 * 
	 * @描述: 删除学院经费
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-22 下午01:43:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delXyjf(String xmdm) throws Exception{
		String sql = "delete from xg_xszz_new_xmjf where zdm=(select zdm from xg_xszz_new_xmfz where xmdm=?)";
		return dao.runUpdate(sql, new String[]{xmdm});
	}
	
	
	/**
	 * 
	 * @描述: 删除项目分组
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-22 下午01:44:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delXmfz(String xmdm) throws Exception{
		String sql = "delete from xg_xszz_new_xmfz where zdm = (select zdm from xg_xszz_new_xmfz where xmdm=?)";
		return dao.runUpdate(sql, new String[]{xmdm});
	}
	
	
	/**
	 * 
	 * @描述: 资助所有奖项
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-10-23 下午04:13:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZzxm() throws Exception {
	
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select distinct trim(xmmc) xmmc from (select * from xg_xszz_new_zzxmjgb order by xn,xq) ");
	
		return dao.getListNotOut(sql.toString(), new String[] {});
	
	}
	
	/**
	 * 
	 * @描述:获取项目类别
	 * @作者：Huang Chenji
	 * @日期：2015-10-27 上午11:26:33
	 * @修改记录:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlb() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select lbdm dm,lbmc mc ");
		sb.append(" from  xg_xszz_new_zzxmlbb ");
		sb.append(" order by lbdm");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}
	
	/**
	 * 浙大个性化功能
	 */
	public HashMap<String, String> showXmxx_10335(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_xszz_new_zzxmdmb where xmdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xmdm});
	}

}
