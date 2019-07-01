/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午02:38:21 
 */
package com.zfsoft.xgxt.xpjpy.xmsz.xmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import common.Globals;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 项目维护
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-30 下午02:38:21
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XmwhDao extends SuperDAOImpl<XmwhModel> {

	@Override
	protected void setTableInfo() {
		super.setClass(XmwhModel.class);
		super.setTableName("xg_pjpy_new_pjxmb");
		super.setKey("xmdm");
	}

	/**
	 * 
	 * @描述:高级查询方法
	 * @作者：ligl  TODO
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmwhModel model, User user)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(
				"select a.xmdm,a.lxdm,a.xzdm,a.xmmc,a.sfqy,a.xmje,a.shlc,a.rskzjb,a.jdkzjb,a.rsfpfs,a.rsfpz,a.rsfpnj,a.zcfpm,");		
		sql.append("a.xmsm,a.sqkssj,a.sqjssj,a.sqkg,a.shkssj,a.shjssj,a.shkg,a.kgbz,a.xn,a.xq,a.dybb,to_number(a.xsxh) xsxh,a.xmywmc,");
		sql.append("b.xmlxmc,b.xmlxdm,c.xmxzdm,c.xmxzmc");
		sql.append(",(select case when count(*)>0 then '1' else '0' end  from xg_pjpy_new_jdszb e where a.xmdm=e.xmdm) jdsz");
		sql.append(",(select case when count(*)>0 then '1' else '0' end  from xg_pjpy_new_xmtjb f where a.xmdm=f.xmdm) tjsz ");
		sql.append(",(select case when count(1)>0 then sum(zzme) else 0 end  from xg_pjpy_new_rsszb g where a.xmdm=g.xmdm and g.zzme is not null ");
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)&& "xy".equalsIgnoreCase(user.getUserStatus())){  //浙江大学个性化修改
			sql.append("and bmdm = '" + user.getUserDep() + "'");
		}
		if(null!=model.getCzfs()&&model.getCzfs().length()!=0&&model.getCzfs().equals("xyrssz")&& Globals.XXDM_ZJDX.equals(Base.xxdm)){//浙江大学个性化修改
			sql.append("and zd1 is not null ");
		}
		sql.append(") rssz ");
		sql.append(",(select case when count(*)>0 then '1' else '0' end  from xg_pjpy_new_jxtzsz h where a.xmdm=h.xmdm) shsz ");
		sql.append(" from xg_pjpy_new_pjxmb a, ");
		if("2".equals(model.getXmxz()))
		{//表彰奖励
			sql.append("xg_pjpy_new_xmlx b, ");
		}
		else{//奖学金
			sql.append("xg_pjpy_new_jxjxmlx b, ");
		}

		sql.append("  xg_pjpy_new_xmxz c where b.xmlxdm=a.lxdm and c.xmxzdm=a.xzdm ");
		if("2".equals(model.getXmxz()))
		{//表彰奖励
			sql.append(" and a.xzdm = '2' ");
		}
		else{//奖学金
			sql.append(" and a.xzdm = '1' ");
		}
		String currXn = CsszService.getPjzq().get("xn");// 当前学年
		String currXq = CsszService.getPjzq().get("xq");// 当年学期
		sql.append(" and a.xn='");
		sql.append(currXn);
		sql.append("'");
		sql.append(" and a.xq='");
		sql.append(currXq);
		sql.append("'");
		if (!StringUtil.isNull(model.getXmmc())) {
			params.add(model.getXmmc());
			sql.append(" and xmmc like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getLxdm())) {
			params.add(model.getLxdm());
			sql.append(" and lxdm like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getXzdm())) {
			params.add(model.getXzdm());
			sql.append(" and xzdm like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getSqkg())) {
			String sqkg = model.getSqkg();
			if(sqkg.equals("1")){
				params.add(model.getSqkg());
				sql.append(" and sqkg like '%'||?||'%' and");
				sql.append(" (sysdate between to_date(nvl(a.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
				sql.append(" and to_date(nvl(a.sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi'))");	
			}else{
				params.add(model.getSqkg());
				sql.append(" and (sqkg like '%'||?||'%' or");
				sql.append(" (sysdate not between to_date(nvl(a.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
				sql.append(" and to_date(nvl(a.sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi')))");	
			}
		}
		if(!StringUtil.isNull(model.getCzfs())&&model.getCzfs().equals("xyrssz")){
			sql.append(" and rsfpfs<>'xx'");
			sql.append(" and a.xmdm in (select xmdm from xg_pjpy_new_rsszb where fpbl is not null )");
			if ("xy".equalsIgnoreCase(user.getUserStatus())){
			  sql.append(" and '"+user.getUserDep()+"' in(select bmdm from xg_pjpy_new_rsszb where fpbl is not null)");	
			}
		}
		return getPageList(model, sql.toString(), params
				.toArray(new String[] {}));
	}

	/**
	 * 
	 * @描述: 查询可申请的评奖项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-31 上午08:41:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getKfsqPjxm() {

		StringBuilder sql = new StringBuilder();

		sql
				.append(" select * from xg_pjpy_new_pjxmb t where t.sfqy='1' and sqkg='1' and");
		sql
				.append(" (sysdate between to_date(nvl(sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql
				.append(" and to_date(nvl(sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi'))");
		sql
				.append(" and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1)");

		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	
	public List<HashMap<String, String>> getKfsqPjxm(String xnxq) {

		StringBuilder sql = new StringBuilder();

		sql.append(" select * from xg_pjpy_new_pjxmb t where t.sfqy='1' and sqkg='1' and");
		sql.append(" (sysdate between to_date(nvl(sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi'))");
		sql.append(" and xn||xq=? ");

		return dao.getListNotOut(sql.toString(), new String[] {xnxq});
	}
	
	public List<HashMap<String, String>> getKfsqPjxm(String xnxq,String bjdms) {

		StringBuilder sql = new StringBuilder();
		MessageResources message = MessageResources
						.getMessageResources("config.ApplicationResources");
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		sql.append(" select t1.*,decode(t1.rsfpfs,'xx','学校','xy','"+Base.YXPZXY_KEY+"','njxy','年级+"+Base.YXPZXY_KEY+"','njzy','年级+专业','bj','班级','cpz','参评组') as rsksfsmc, ");
		sql.append(" decode(decode(t1.rsfpfs,'xx',t7.zzme,'xy',t2.xyzzme,'njxy',t3.njxyzzme,'njzy',t4.njzyzzme,'bj',t5.bjzzme,'cpz',t6.cpzzzme),null,'无人数控制',decode(t1.rsfpfs,'xx',t7.zzme,'xy',t2.xyzzme,'njxy',t3.njxyzzme,'njzy',t4.njzyzzme,'bj',t5.bjzzme,'cpz',t6.cpzzzme)) as zzme ");
		sql.append(" from xg_pjpy_new_pjxmb t1 left join xg_pjpy_new_rsszb t7 on t1.xmdm=t7.xmdm and t7.bmdm='xx' left join ( ");
		sql.append(" select a.xmdm,sum(a.zzme) as xyzzme from xg_pjpy_new_rsszb a ");
		sql.append(" where a.bmdm in (select xydm from view_njxyzybj_all where bjdm in ("+bjdms+")) group by a.xmdm ");
		sql.append(" ) t2 on t1.xmdm=t2.xmdm left join ( ");
		sql.append(" select a.xmdm,sum(a.zzme) as njxyzzme from xg_pjpy_new_rsszb a ");
		sql.append(" where (a.nj,a.bmdm) in (select nj,xydm from view_njxyzybj_all where bjdm in ("+bjdms+")) ");
		sql.append(" group by a.xmdm ) t3 on t1.xmdm=t3.xmdm left join ( ");
		sql.append(" select a.xmdm,sum(a.zzme) as njzyzzme from xg_pjpy_new_rsszb a ");
		sql.append(" where (a.nj,a.bmdm) in (select nj,zydm from view_njxyzybj_all where bjdm in ("+bjdms+")) ");
		sql.append(" group by a.xmdm  ) t4 on t1.xmdm=t4.xmdm  left join ( ");
		sql.append(" select a.xmdm,sum(a.zzme) as bjzzme from xg_pjpy_new_rsszb a ");
		sql.append(" where a.bmdm in ("+bjdms+") group by a.xmdm ) t5 on t1.xmdm=t5.xmdm left join ( ");
		sql.append(" select a.xmdm,sum(a.zzme) as cpzzzme from xg_pjpy_new_rsszb a "); 
		sql.append(" left join xg_pjpy_new_pjxmb b on a.xmdm=b.xmdm  where a.bmdm in "); 
		sql.append(" (select c.cpzdm from XG_ZHCP_FSTJJLB c where b.xn=c.xn and b.xq=c.xq and c.bjdm in ("+bjdms+") ) ");
		sql.append(" group by a.xmdm  ) t6 on t1.xmdm=t6.xmdm "); 
		sql.append(" where t1.sfqy='1' and t1.sqkg='1' and (sysdate between to_date(nvl(t1.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') "); 
		sql.append(" and to_date(nvl(t1.sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi')) and t1.xn||t1.xq=? order by to_number(xsxh) asc  ");

		return dao.getListNotOut(sql.toString(), new String[] {xnxq});
	}

	/**
	 * 
	 * @描述:普通查询方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmwhModel model)
			throws Exception {
		return null;
		
	}

	/**
	 * 
	 * @描述: 除当前项目外，其余的项目
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getOthers(String xmdm,String currXn,String currXq)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmdm,xmmc ");
		sb.append(" from  xg_pjpy_new_pjxmb ");
		sb.append(" where ");
		if(!StringUtil.isNull(xmdm)){
			sb.append("xmdm!=? and ");
		}
		sb.append(" xn='");
		sb.append(currXn);
		sb.append("' and xq='");
		sb.append(currXq);
		sb.append("'");
		List<String> input=new ArrayList<String>();
		
		if(!StringUtil.isNull(xmdm)){
			sb.append(" and shlc = (select shlc from xg_pjpy_new_pjxmb where xmdm=?) ");
			input.add(xmdm);
			input.add(xmdm);
		}
		
		sb.append(" order by to_number(nvl(xsxh,0))");
		
		
		return dao.getListNotOut(sb.toString(), input.toArray(new String[]{}));
	}

	public List<HashMap<String, String>> getOthers(String xmdm,String currXn,String currXq,String xzdm)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmdm,xmmc ");
		sb.append(" from  xg_pjpy_new_pjxmb ");
		sb.append(" where ");
		if(!StringUtil.isNull(xmdm)){
			sb.append("xmdm!=? and ");
		}
		sb.append(" xn='");
		sb.append(currXn);
		sb.append("' and xq='");
		sb.append(currXq);
		sb.append("'");
		List<String> input=new ArrayList<String>();

		if(!StringUtil.isNull(xmdm)){
			sb.append(" and shlc = (select shlc from xg_pjpy_new_pjxmb where xmdm=?) ");
			input.add(xmdm);
			input.add(xmdm);
		}
		if("2".equals(xzdm)){
			sb.append(" and xzdm='2' ");
		}else{
			sb.append(" and xzdm <> '2' ");
		}

		sb.append(" order by to_number(nvl(xsxh,0))");


		return dao.getListNotOut(sb.toString(), input.toArray(new String[]{}));
	}

	/**
	 * 
	 * @描述:判断重复数据
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @throws
	 */
	public boolean isRepeat(XmwhModel model) throws Exception {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) count from xg_pjpy_new_pjxmb t ");
		sql.append(" where t.xmmc=?");
		if (model.getXmdm() != null && !model.getXmdm().trim().equals("")) {
			sql.append(" and t.xmdm!='" + model.getXmdm() + "'");
		}
		String currXn = CsszService.getPjzq().get("xn");// 当前学年
		String currXq = CsszService.getPjzq().get("xq");// 当年学期
		sql.append(" and t.xn='");
		sql.append(currXn);
		sql.append("'");
		sql.append(" and t.xq='");
		sql.append(currXq);
		sql.append("'");
		String xmmc = model.getXmmc();
		if (xmmc != null) {
			xmmc = xmmc.trim();
		}
		String[] input = { xmmc };
		String[] output = { "count" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);// ///此方法异常已被捕获掉。，出错无法处理
		// 。。。。。。。////。。。。。。////
		if (oneRs != null && oneRs.length > 0) {
			if (!oneRs[0].equals("0")) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 
	 * @描述:删除关联表数据
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean delRalate(String keys) throws Exception {
		int[] result = null;
		List<String> sqlList = new ArrayList<String>();
		if (keys != null) {
			keys = StringUtils.replace(keys, ",", "','");
			keys = "'" + keys + "'";
		} else {
			return true;
		}
		String sql = "delete from  xg_pjpy_new_rsszb ";// 人数设置表
		sql += " where xmdm in(" + keys + ")";
		sqlList.add(sql);

		sql = "delete from  xg_pjpy_new_xmtjb ";// 条件 设置表
		sql += " where xmdm in(" + keys + ")";
		sqlList.add(sql);

		sql = "delete from  xg_pjpy_new_jdszb ";// 兼得设置表
		sql += " where xmdm in(" + keys + ")";
		sqlList.add(sql);

		sql = "delete from  xg_pjpy_new_jdszb ";// 兼得设置表
		sql += " where bjdxmdm in(" + keys + ")";
		sqlList.add(sql);

		sql = "delete from  xg_pjpy_new_jxtzsz ";// 审核调整奖项目设置
		sql += " where xmdm in(" + keys + ")";
		sqlList.add(sql);

		sql = "delete from  xg_pjpy_new_pjxmb ";// 项目维护表
		sql += " where xmdm in(" + keys + ")";
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
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public String getNameById(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmmc xmmc ");
		sb.append(" from  xg_pjpy_new_pjxmb ");
		sb.append(" where xmdm=?");
		String[] inputValue = { xmdm };
		String[] outputValue = { "xmmc" };
		String[] oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String xmmc = "";
		if (oneRs != null && oneRs.length > 0) {
			xmmc = oneRs[0];
		}
		return xmmc;
	}

	/**
	 * 
	 * @描述:根据项目代码查询记录
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getDataById(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * ");
		sb.append(" from  xg_pjpy_new_pjxmb ");
		sb.append(" where xmdm=?");
		String[] inputValue = { xmdm };
		return dao.getMapNotOut(sb.toString(), inputValue);
	}

	/**
	 * 
	 * @描述:根据项目名称查询单条记录
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getDataByName(String xmmc, String xn, String xq) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * ");
		sb.append(" from  xg_pjpy_new_pjxmb ");
		sb.append(" where xmmc=?");

		sb.append(" and xn='");
		sb.append(xn);
		sb.append("'");
		sb.append(" and xq='");
		sb.append(StringUtil.isNull(xq) ? "on" : xq);
		sb.append("'");

		String[] inputValue = { xmmc };
		return dao.getMapNotOut(sb.toString(), inputValue);
	}

	/**
	 * 
	 * @描述:设置总名额
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @param xmdm
	 * @param zme
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean setZme(String xmdm, String zme, String rsfpnj)
			throws Exception {
		String sql = "update xg_pjpy_new_pjxmb set rsfpz=?,rsfpnj=? where xmdm=? ";
		String[] input = { zme, rsfpnj, xmdm };
		return dao.runUpdate(sql, input);
	}

	/**
	 * 
	 * @描述:通过项目代码获取已经设置的年级,年级以逗号分割
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @param
	 * @return
	 * @throws Exception
	 *             String 返回类型
	 * @throws
	 */
	public String getRsfpnj(String xmdm) throws Exception {
		String rsfpnj = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select rsfpnj from xg_pjpy_new_pjxmb where xmdm=? ");
		String[] input = { xmdm };
		String[] output = { "rsfpnj" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				rsfpnj = oneRs[0];
			}
		}
		return rsfpnj;
	}

	/**
	 * 
	 * @描述:通过项目代码获取培养层次
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @param
	 * @return
	 * @throws Exception
	 *             String 返回类型
	 * @throws
	 */
	public String getPycc(String xmdm) throws Exception {
		String pycc = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select pycc from xg_pjpy_new_pjxmb where xmdm=? ");
		String[] input = { xmdm };
		String[] output = { "pycc" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				pycc = oneRs[0];
			}
		}
		return pycc;
	}

	/**
	 * 
	 * @描述:获取项目性质
	 * @作者：ligl
	 * @日期：2013-8-7 上午11:24:49
	 * @修改记录:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXmxz() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmxzdm dm,xmxzmc mc ");
		sb.append(" from  xg_pjpy_new_xmxz ");
		sb.append(" order by xmxzdm");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}

	/**
	 * 
	 * @描述:获取项目类型
	 * @作者：ligl
	 * @日期：2013-8-7 上午11:24:49
	 * @修改记录:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlx(String xzdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmlxdm dm,xmlxmc mc from ");
		if("2".equals(xzdm)){
			sb.append(" xg_pjpy_new_xmlx ");
		}else{
			sb.append(" xg_pjpy_new_jxjxmlx ");
		}

		sb.append(" order by xmlxdm");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}

	/**
	 * 
	 * @描述:查询可以复制的奖项来源
	 * @作者：ligl
	 * @日期：2013-8-14 上午11:01:07
	 * @修改记录:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getJxfzXnXq(String csz) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct xn,xq ");
		sb.append(" from  xg_pjpy_new_pjxmb ");
		if(CsszService.PJFS_XN.equals(csz)){
			sb.append(" where xq = 'on' ");
		}else{
			sb.append(" where xq <> 'on' ");
		}
		sb.append("order by xn desc,xq desc");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}

	/**
	 * 
	 * @描述:查询可以复制的奖项来源
	 * @作者：ligl
	 * @日期：2013-8-14 上午11:01:07
	 * @修改记录:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getJxfz(String fzXn, String fzXq,
			String currXn, String currXq) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * from xg_pjpy_new_pjxmb  ");
		sb.append("  where xn='");
		sb.append(fzXn);
		sb.append("'");
		sb.append("  and xq='");
		sb.append(fzXq);
		sb.append("'");
		sb
				.append("  and xmmc not in(select xmmc from xg_pjpy_new_pjxmb where xn='");
		sb.append(currXn);
		sb.append("'");
		sb.append("  and xq='");
		sb.append(currXq);
		sb.append("')");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}

	public boolean saveData(List<HashMap<String, String>> list, String currXn,
			String currXq) throws Exception {

		int[] result = null;
		StringBuffer sb = new StringBuffer();
		sb.append("insert into xg_pjpy_new_pjxmb ");
		sb
				.append("(xmdm,jdkzjb,kgbz,lxdm,rsfpfs,rsfpnj,rsfpz,rskzjb,sfqy,shjssj,shkg,shkssj,shlc,sqjssj,sqkg,sqkssj,xmje,xmmc,xmsm,xzdm,zcfpm,xsxh,xn,xq)");
		sb.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'");
		sb.append(currXn);
		sb.append("','");
		sb.append(currXq);
		sb.append("')");

		List<String[]> params = new ArrayList<String[]>();
		String[] param = null;
		for (HashMap<String, String> map : list) {
			param = new String[22];
			param[0] = map.get("id");
			param[1] = map.get("jdkzjb");
			param[2] = map.get("kgbz");
			param[3] = map.get("lxdm");
			param[4] = map.get("rsfpfs");
			param[5] = map.get("rsfpnj");
			param[6] = map.get("rsfpz");
			param[7] = map.get("rskzjb");
			param[8] = map.get("sfqy");
			param[9] = map.get("shjssj");
			param[10] = map.get("shkg");
			param[11] = map.get("shkssj");
			param[12] = map.get("shlc");
			param[13] = map.get("sqjssj");
			param[14] = map.get("sqkg");
			param[15] = map.get("sqkssj");
			param[16] = map.get("xmje");
			param[17] = map.get("xmmc");
			param[18] = map.get("xmsm");
			param[19] = map.get("xzdm");
			param[20] = map.get("zcfpm");
			param[21] = map.get("xsxh");
			params.add(param);
		}
		result = dao.runBatch(sb.toString(), params);
		return true;
	}
	
	/**
	 * 
	 * @描述:查询总共评奖项目
	 * @作者：cq [工号：785]
	 * @日期：2014-1-26 上午11:48:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getPjxm() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct trim(xmmc) xmmc from (select * from xg_pjpy_new_pjxmb order by xn,xq,to_number(xsxh)) ");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	
	public List<HashMap<String, String>> getPjjgxmmc() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct trim(xmmc) xmmc from xg_pjpy_new_pjjgb order by xmmc ");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
		
	
	/**
	 * 
	 * @描述:评奖条件取项目类型
	 * @作者：cq [工号：785]
	 * @日期：2014-9-19 下午12:09:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCountXmlx(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select distinct a.xmlxdm||','||b.lv xmlxdm, b.lv||'次“'||a.xmlxmc||'”及以上' xmlxmc from ");
		sql.append("xg_pjpy_new_xmlx a,(select LEVEL lv from dual CONNECT BY LEVEL <= 5) b order by a.xmlxdm||','||b.lv");

		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:评奖项目序号重复验证
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-11-17 下午02:40:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistXssx(XmwhModel model) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" select count(1) num from xg_pjpy_new_pjxmb ");
		sql.append(" where xn = ? and  xq = ? and xsxh = ? and xzdm=? ");
		params.add(CsszService.getPjzq().get("xn"));
		params.add(CsszService.getPjzq().get("xq"));
		params.add(model.getXsxh());
		params.add(model.getXmxz());
		String xmdm = model.getXmdm();
		if(!StringUtils.isEmpty(xmdm)){
			sql.append(" and xmdm <> ? ");
			params.add(xmdm);
		}
		
		String num = dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");
		return num;
	}
	/**
	 * @描述: 浙江传媒学院【项目性质】除了“国家奖学金”不选中，其余全部默认选中
	 * @作者： 孟威[工号：1186]
	 * @日期： 2015-12-31 下午01:04:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<String> 返回类型 
	 * @throws
	 */
	public List<String> getXmxzmw() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmxzdm,xmxzmc ");
		sb.append(" from  xg_pjpy_new_xmxz ");
		sb.append(" where xmxzmc not like '国家奖学金' ");
		sb.append(" order by xmxzdm");
		String[] input = {};
		return dao.getList(sb.toString(), input,"xmxzdm");
	}
	
	//集体评奖的名称
	public String getNameByIdJtpj(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select jxmc xmmc ");
		sb.append(" from  XG_PJPY_JTPJ_JTJXSZ ");
		sb.append(" where jxid=?");
		String[] inputValue = { xmdm };
		String outputValue = "xmmc";
		String oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String xmmc = "";
		if (xgxt.utils.String.StringUtils.isNotNull(oneRs)) {
			xmmc = oneRs;
		}
		return xmmc;
	}
	
	/** 
	 * @描述:获取体育登记代码名称(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-19 下午02:35:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getTydj(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select pjdjdm dm,pjdjmc mc from xg_pjpy_new_pjdj where pjxmmc = '体能测试成绩' ");

		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @description	： 是否优秀干部（西安科技大学）
	 * @author 		： lj（1282）
	 * @date 		：2018-4-28 下午02:49:58
	 * @param xmdm
	 * @return
	 */
	public String getSfyxgb(String xmdm){
		String sql = "select nvl(sfyxgb,'0') sfyxgb from xg_pjpy_new_pjxmb where xmdm = ?";
		return dao.getOneRs(sql, new String[]{xmdm}, "sfyxgb");
	}
	
	/**
	 * @description	： 保存是否优秀干部（西安科技大学）
	 * @author 		： lj（1282）
	 * @date 		：2018-4-28 下午03:47:01
	 * @param xmdm
	 * @return
	 * @throws Exception 
	 */
	public boolean updateSfYxgb(String xmdm,String sfyxgb) throws Exception{
		String sql = "update xg_pjpy_new_pjxmb set sfyxgb = ? where xmdm = ?";
		return dao.runUpdate(sql, new String[]{sfyxgb,xmdm});
	}

	public List<HashMap<String,String>> getnewXmlx(String xmxz) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmlxdm dm,xmlxmc mc ");
		if("2".equals(xmxz))
		{
			sb.append(" from  xg_pjpy_new_xmlx ");
		}else{
			sb.append(" from  xg_pjpy_new_jxjxmlx ");
		}

		sb.append(" order by xmlxdm");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}

    public String getXsxh(String xzdm) {

		String sql = "SELECT nvl(max(XSXH),0)+1 xsxh FROM xg_pjpy_new_pjxmb WHERE XZDM = ? ";
		return dao.getOneRs(sql,new String [] {xzdm},"xsxh");
    }
}
