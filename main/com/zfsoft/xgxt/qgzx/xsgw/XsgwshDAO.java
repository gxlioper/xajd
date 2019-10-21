/**
 * @部门:学工产品事业部
 * @日期：2013-6-18 上午10:50:51 
 */
package com.zfsoft.xgxt.qgzx.xsgw;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.export.util.DateUtils;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.qgzx.xsgw.XsgwshForm;
import common.Globals;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO
 * @作者： zhangjw
 * @时间： 2013-6-18 上午10:50:51
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XsgwshDAO extends SuperDAOImpl<XsgwshForm> {
	/**
	 * 数据来源（申请）
	 */
	public static final String _SJLY_SQ = "1";
	/**
	 * 数据来源（手动维护）
	 */
	public static final String _SJLY_WH = "0";
	/**
	 * 
	 * @描述:获取流程中数据
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-27 上午10:44:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getSplcing(String splc){
		StringBuffer sb=new StringBuffer();
		sb.append("select sqbh as ywid from xg_qgzx_xsgwsqb where shzt in(?,?) and splc=?");
		return dao.getListNotOut(sb.toString(), new String[]{Constants.YW_SHZ,Constants.YW_YTH,splc});
	}
	
	public List<HashMap<String, String>> getSplcing(String splc,String type){
		StringBuffer sb=new StringBuffer();
		sb.append("select sqbh as ywid from xg_qgzx_xsgwsqb a " +
				" left join (select b.gwdm,c.bmlb from XG_QGZX_GWXXB b left join ZXBZ_XXBMDM c on b.yrdwdm = c.bmdm ) c "+
				" on a.gwdm=c.gwdm where c.bmlb=? and shzt in(?,?) and splc=? ");
		return dao.getListNotOut(sb.toString(), new String[]{type,Constants.YW_SHZ,Constants.YW_YTH,splc});
	}
	/**
	 * 
	 * @描述: 获取学生此岗位的在岗的数量
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-27 下午03:02:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param gwdm
	 * @return
	 * Integer 返回类型 
	 */
	public Integer getXstgxx(String xh,String gwdm){
		StringBuffer sb=new StringBuffer();
		sb.append("select count(1) rs from xg_qgzx_xsgwxxb c where  xh = ? and zgzt = ? and gwdm=?");
		String jgkZjrs=dao.getOneRs(sb.toString(), new String[]{xh,"zg",gwdm}, "rs");
		return StringUtils.paseStringToInteger(jgkZjrs);
	}
	/*
	 * 描述:
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XsgwshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XsgwshForm model, User user)
			throws Exception {
		WdgwsqDAO wd = new WdgwsqDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		map = wd.getCsszb();
		String qxfw = map.get("qxfw");
		String yjqxfw = map.get("yjqxfw");
		String splc = map.get("xsgwsqsplc");
//		String yjsplc = map.get("xsyjgwsqsplc");
		String yjsplc = splc;
		SearchModel searchmodel = new SearchModel();
		searchmodel.setSearch_tj_qgrq(model.getSearchModel().getSearch_tj_qgrq());
		searchmodel.setSearch_tj_qgmx(model.getSearchModel().getSearch_tj_qgmx());
		if(null !=  searchmodel.getSearch_tj_qgrq() && searchmodel.getSearch_tj_qgrq().length != 0){
			model.getSearchModel().setSearch_tj_qgrq(null);
		}
		if(null !=  searchmodel.getSearch_tj_qgmx() && searchmodel.getSearch_tj_qgmx().length != 0){
			model.getSearchModel().setSearch_tj_qgmx(null);
		}
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b",
				"xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "b",
				"xydm", "bjdm");

		// String yrdwTj=SearchService.getSearchYrdwTjByUser(user, "c");

		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct ");
		sql.append(" SQBH,");
		sql.append(" XH,");
		sql.append(" XM,");
		sql.append(" NJ,");
		sql.append(" ZYDM,");
		sql.append(" XB,");
		sql.append(" SQSJ,");
		sql.append(" SPLC,");
		sql.append(" YRDWMC,YRDWDM,");
		sql.append(" SHZTS,");
		sql.append(" GWMC,");
//		sql.append(" GWXZMC,");
		sql.append(" SHSJ,");
		sql.append(" GWID,");
		sql.append(" SHID,");
		sql.append(" SHZT,");
		sql.append(" GWZ,");
		sql.append(" RN,");
		sql.append(" XYDM,");
		sql.append(" BJDM,");
		sql.append(" XN,");
		sql.append(" XYMC,");
		sql.append(" ZYMC,");
		sql.append(" BJMC,");
		sql.append(" xq,zybj,zybjmc,pycc");
		sql.append(" from (");
		sql.append("select distinct b.* from (select b.* from (select a.sqbh,b.xh,b.xm,b.nj,b.pycc,g1.zydm,");
		sql.append("(case b.xb when '1' then '男' when '2' then '女' else b.xb end) xb,");
		sql.append("a.sqsj,a.splc,nvl(w.yrdwmc,w1.bmmc) yrdwmc,nvl(w.xydm,w.id) yrdwdm,d.shzt shzts,c.gwmc,d.shsj,d.gwid,d.guid shid,");
		sql.append("f.mc ||'['|| decode(d.shzt,0,'待审核',1,'通过',2,'不通过',3,'退回',4,'需重审',5,'审核中','其它')||']' shzt,f.gwz,");
		sql.append(" row_number()over(partition by a.sqbh order by d.shsj desc) rn, ");
		sql.append(" g1.xydm,g.bjdm,c.xn,c.xq,g1.xymc,g1.zymc,g1.bjdm zybj,g1.bjmc zybjmc,g.bjmc from xg_qgzx_xsgwsqb a ");
		sql.append(" left join xg_qgzx_gwxxb c on a.gwdm = c.gwdm  ");
//		sql.append(" left join XG_QGZX_GWXZDMB c1 on c.gwlb = c1.gwxzdm ");
		sql.append(" left join xsxxb b on a.xh = b.xh  ");
		sql.append(" left join xg_xtwh_shztb d on a.sqbh = d.ywid ");
		String shlx = model.getShlx();
		/*
		 * if(!shlx.equals("dsh")){ sql.append(" and d.shzt<>0  "); }else{
		 * sql.append(" and d.shzt=0 "); }
		 */
		if (!shlx.equals("dsh")) {
			sql.append(" and (d.shzt<>0 and d.shzt<>4 )  ");
		} else {
			sql.append(" and ( d.shzt=0 or d.shzt=4 )  ");
		}
		sql.append(" left join xg_xtwh_spgwyh e on d.gwid = e.spgw  left join  xg_xtwh_spgw f on d.gwid = f.id ");
		sql.append(" left join view_njxyzybj_all g on b.bjdm=g.bjdm ");
		sql.append(" left join view_njxyzybj_all g1 on b.zybj=g1.bjdm ");
		sql.append(" left join XG_QGZX_YRDWDMB w on w.id = c.yrdwid ");
		sql.append(" left join ZXBZ_XXBMDM w1 on w1.bmdm=w.xydm ");
		sql.append(" where e.spyh = '" + user.getUserName() + "' ");

		sql.append(" ) b ");

		sql.append(" ) b");

		sql.append(" where 1=1 ");
		sql.append(" and rn = 1 "); // 取出最後一級
		sql.append(searchTj);

		sql.append(shgwzByUser);
		//sql.append(searchTjByUser);
		//sql.append(searchTj);
//		sql.append(getQxfwSql(splc,yjsplc, qxfw,yjqxfw, user.getUserName(),searchTjByUser));

		sql.append(" order by sqsj desc");
		sql.append(") v ");
		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:获取调岗记录
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-17 下午06:34:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqbh
	 * @param nowGwdm
	 * @return String 返回类型
	 */
	public HashMap<String, String> getOldShxx(String userId, String lcid,
			String sqbh, String nowGwdm) {
		return ShlcDao.getDqjbByCondition(sqbh, userId, lcid, "xsgwsh");
		/*
		 * ShlcDao sd=new ShlcDao(); //查处更改前的信息 StringBuffer sb=new
		 * StringBuffer();sb.append(
		 * "select * from xg_xtwh_shztb a left join xg_xtwh_spbz b on a.lcid=b.splc and a.gwid=b.spgw and a.ywid=? and zd2 <> ? order by xh desc"
		 * ); return dao.getListNotOut(sb.toString(), new
		 * String[]{sqbh,nowGwdm});
		 */
	}
	/**
	 * 
	 * @描述: 获取上级调换审核信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-18 下午07:26:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param nowGwdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 */
	public HashMap<String, String> getSjTzShxx(String ywid,String nowGwdm) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select t.*,");
		sb.append(" lead(t.gwid,1,null) over(partition by t.ywid order by t.shsj desc) as sjgwid");
		sb.append(" from xg_xtwh_shztb t where t.ywid=? and zd2<> ? and zd2 is not null");
		return dao.getMapNotOut(sb.toString(), new String[]{ywid,nowGwdm});
	}

	/**
	 * 
	 * @描述:根据申请id删除结果数据
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-12 上午09:55:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqbh
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean delJgForSq(String sqbh) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("delete xg_qgzx_new_xsgwxxb where sqbh=?");
		int i = dao.runDelete(sb.toString(), new String[] { sqbh });
		return i >= 0 ? true : false;
	}
	public boolean isUserQxfw(String userName){
		ShlcDao sd=new ShlcDao();
		StringBuffer sb=new StringBuffer();
		sb.append("select spgw from xg_xtwh_spgwyh where spyh=?");
		List<HashMap<String, String>> list=dao.getListNotOut(sb.toString(), new String[]{userName});
		return false;
	}
	/**
	 * 
	 * @描述:设置部门权限范围
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-9 上午10:44:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splc
	 * @param qxfw
	 * @param userName
	 * @return String 返回类型
	 */
	public String getQxfwSql(String splc,String yjsplc, String qxfw,String yjqxfw, String userName,String searchTjByUser) {
		// 设置部门权限范围
		StringBuffer sql = new StringBuffer();
		if(StringUtils.isNull(qxfw) && StringUtils.isNull(yjqxfw)){
			sql.append(searchTjByUser);
			return sql.toString();
		}
		// 获取设置审核流程的审核岗位
		List<HashMap<String, String>> list = ShlcUtil.getSpbzBySplc(splc);
		List<HashMap<String, String>> yjlist = ShlcUtil.getSpbzBySplc(yjsplc);
/*		List<String> shgws = new ArrayList<String>();
		for (HashMap<String, String> hm : list) {
			shgws.add(hm.get("spgw"));
		}*/
		
		sql.append(" and ( ");
		/*if(StringUtils.isNotNull(qxfw)){
			int i = 0;
			String qxfws[] = qxfw.split(",");
			String shgw = null;
			if(null!=list&&list.size()!=0){

				sql.append(" (( ");
				for (HashMap<String, String> hm : list) {
					shgw = hm.get("spgw");
					for (String qxgw : qxfws) {
						if (i > 0) {
							sql.append(" or ");
						}
						sql.append("(");
						// 如果审核流程岗位是设置的控制岗位
						if (shgw.equals(qxgw)) {
							sql.append(" yrdwdm = ( select szbm from yhb where yhm = '"
									+ userName + "') and ");
							sql.append(" gwid = '" + shgw + "')");
						}else{
							sql.append(" gwid = '" + shgw + "' ");
							sql.append(searchTjByUser);
							sql.append(")");
						}
						//sql.append(" gwid = '" + shgw + "' ");
						//sql.append(searchTjByUser);
						//sql.append(searchTj);
						i++;
					}
				}
				sql.append(") and bmlbdm = '1' ) ");
			}
		}*/

		if(StringUtils.isNotNull(yjqxfw)){
			
			int z = 0;
			String yjqxfws[] = yjqxfw.split(",");
			String yjshgw = null;
			if(null!=yjlist&&yjlist.size()!=0){

//				sql.append(" or ( ");
				sql.append(" ( ");

				for (HashMap<String, String> hm : yjlist) {
					yjshgw = hm.get("spgw");
					for (String yjqxgw : yjqxfws) {
						if (z > 0) {
							sql.append(" or ");
						}
						sql.append("(");
						// 如果审核流程岗位是设置的控制岗位
						if (yjshgw.equals(yjqxgw)) {
							sql.append(" yrdwdm = ( select szbm from yhb where yhm = '"
									+ userName + "') and ");
							sql.append(" gwid = '" + yjshgw + "')");
						}else{
							sql.append(" gwid = '" + yjshgw + "' ");
							sql.append(searchTjByUser);
							sql.append(")");
						}
						z++;
					}
				}
//				sql.append(") and bmlbdm = '5' ) ");
				sql.append(") ");
			}	
		}
		/*else {
			sql.append(" or (1=1 and bmlbdm = '5' )");
		}*/
		
		sql.append(" ) ");
		return sql.toString();
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_qgzx_xsgwsqb");
		super.setKey("sqbh");
		super.setClass(XsgwshForm.class);
	}

	/**
	 * @描述:保存审批通过的学生进入岗位
	 * @作者：zhangjw
	 * @日期：2013-6-26 下午02:15:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gwdm
	 * @param xh
	 * @return
	 * @throws SQLException boolean 返回类型
	 */
	public boolean bcGwxs(String gwdm, String xh, String sqbh)
			throws SQLException {
		String sgsj="yyyy-MM-dd HH24:mm:ss";
		String sql = " insert into xg_qgzx_new_xsgwxxb(gwdm,xh,sgsj,zgzt,sjly,sqbh)values(?,?,to_char(sysdate,'"+sgsj+"'),'zg',?,?)";
		return dao.insertNotCommit(sql, new String[] { gwdm, xh, _SJLY_SQ, sqbh });
	}
	//手动维护数据进库
	public boolean zjXsgw(String gwdm, String xh) throws SQLException {
		String sgsj="yyyy-MM-dd HH24:mm:ss";
		String sql = " insert into xg_qgzx_new_xsgwxxb(gwdm,xh,sgsj,zgzt,sjly)values(?,?,to_char(sysdate,'"+sgsj+"'),'zg',?)";
		return dao.insert(sql, new String[] { gwdm, xh, _SJLY_WH });
	}

	public boolean updateGwxs(String gwdm, String xh,String tgly) throws Exception {
		String sgsj="yyyy-MM-dd HH24:mm:ss";
		String sql = "update xg_qgzx_new_xsgwxxb set zgzt=?,tgsj=to_char(sysdate,'"+sgsj+"'),tgyy=? where gwdm =? and xh =? ";
		return dao.runUpdate(sql,new String[]{"tg",tgly,gwdm,xh});
	}
	public boolean updateCancelTgGwxs(String gwdm, String xh) throws Exception {
		String sql = "update xg_qgzx_new_xsgwxxb set zgzt=?,tgsj=?,tgyy=? where gwdm =? and xh =? ";
		return dao.runUpdate(sql,new String[]{"zg","","",gwdm,xh});
	}
	/**
	 * @描述:TODO获取学生申请信息
	 * @作者：zhangjw
	 * @日期：2013-6-26 下午02:20:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqbh
	 * @return
	 * @throws SQLException
	 *             HashMap<String,String> 返回类型
	 */
	public HashMap<String, String> getXsSqxx(String sqbh) throws SQLException {
		String sql = " select * from xg_qgzx_xsgwsqb b where b.sqbh = ?";
		return dao.getMapNotOut(sql, new String[] { sqbh });
	}
	
	/**
	 * 
	 * @描述:自定义拼接sql
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-18 下午05:16:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param searchmodel
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZdysql(SearchModel searchmodel){
		StringBuilder sql = new StringBuilder();
		String[] qgrqArr = searchmodel.getSearch_tj_qgrq();
		String[] qgmxArr = searchmodel.getSearch_tj_qgmx();
		if(qgrqArr == null && qgmxArr == null){
			return "  ";
		}
		if(qgrqArr != null && qgmxArr == null){
			sql.append(" and (");
				for(int i = 0;i<qgrqArr.length;i++){
					sql.append(" qgrq = '"+qgrqArr[i]+"'");
					if(i != qgrqArr.length-1){
						sql.append(" or ");
					}
				}
			sql.append(")");
			sql.append(" and (qgmx is not null and qgmx !='') ");
		}
		
		if(qgrqArr == null && qgmxArr != null){
			sql.append(" and (");
				for(int i = 0;i<qgmxArr.length;i++){
					sql.append(" qgmx like '%"+qgmxArr[i]+"%'");
					if(i != qgmxArr.length-1){
						sql.append(" or ");
					}
				}
			sql.append(") ");
		}
		
		if(qgrqArr != null && qgmxArr != null){
			sql.append(" and (");
			for(int i = 0;i<qgrqArr.length;i++){
				sql.append(" qgrq = '"+qgrqArr[i]+"'");
				if(i != qgrqArr.length-1){
					sql.append(" or ");
				}
			}
		    sql.append(")");
			sql.append(" and (");
				for(int i = 0;i<qgmxArr.length;i++){
					sql.append(" qgmx like '%"+qgmxArr[i]+"%'");
					if(i != qgmxArr.length-1){
						sql.append(" or ");
					}
				}
			sql.append(") ");
			
		}
		return sql.toString();
	}

	public List<HashMap<String,String>> getZgxsList(XsgwshForm t, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
//				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();

		sql.append("select * from (");
		sql.append(" select t.xh,t5.xm,t5.pycc,t5.bjdm,t5.zybj,t5.bjmc,t5.zybjmc,t.gwdm,t1.gwmc ");
		sql.append(" ,nvl(t3.xydm,t3.id) yrdwdm,nvl(t3.yrdwmc,t4.bmmc) yrdwmc,nvl(bgdh,t3.lxdh) lxdh,t.sgsj");
		sql.append(" from XG_QGZX_NEW_XSGWXXB t");
		sql.append(" left join XG_QGZX_GWXXB t1 on t.gwdm = t1.gwdm ");
		sql.append(" left join XG_QGZX_YRDWDMB t3 on t1.yrdwid = t3.id");
		sql.append(" left join ZXBZ_XXBMDM t4 on t3.xydm = t4.bmdm ");
		sql.append(" left join view_xsjbxx t5 on t.xh = t5.xh ");
		sql.append(" where t.zgzt = 'zg'");
		sql.append(" ) a where 1=1 ");
		sql.append(searchTj);

		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//获取负责的单位
		List<HashMap<String,String>> bmlist = getYrbmOfUser(user);
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			if(bmlist.size()>0){
				sql.append(" and yrdwdm = '" + bmlist.get(0).get("bmdm") + "' ");
			}else {
				sql.append(" and yrdwdm = '" + user.getUserDep() + "' ");
			}
		}
//		sql.append(searchTjByUser);
		return getPageList(t,sql.toString(),inputV);
	}

	public List<HashMap<String, String>> getYrbmOfUser(User user) {
		String sql = " select distinct t1.bmdm,t1.bmmc from xg_qgzx_yrdwdmb t ";
		sql+=" left join ZXBZ_XXBMDM t1 on t.xydm = t1.bmdm ";
		sql+=" where t.zgh=?";
		return dao.getList(sql, new String[]{user.getUserName()}, new String[]{"bmdm","bmmc"});
	}

	public HashMap<String,String> getZgxx(String gwdm,String xh){

		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,nvl(t3.yrdwmc,t4.bmmc) yrdwmc,nvl(t3.bgdh,t3.lxdh) dwlxdh");
		sql.append(" ,t.sgsj,t.tgsj,t.tgyy,t.zgzt,t3.bgdd,t5.gwxzmc,t.gwdm||'-'||t.xh pk ");
		sql.append(" from XG_QGZX_NEW_XSGWXXB t");
		sql.append(" left join XG_QGZX_GWXXB t1 on t.gwdm = t1.gwdm ");
		sql.append(" left join XG_QGZX_YRDWDMB t3 on t1.yrdwid = t3.id");
		sql.append(" left join ZXBZ_XXBMDM t4 on t3.xydm = t4.bmdm ");
		sql.append(" left join XG_QGZX_GWXZDMB t5 on t1.gwlb = t5.gwxzdm ");
		sql.append(" where t.gwdm = ? and t.xh = ? and zgzt = 'zg'");
		return dao.getMapNotOut(sql.toString(),new String[]{gwdm,xh});
	}

	public boolean insertLzxx(XsgwshForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into XG_QGZX_XSLZSQB(sqbh,gwdm,xh,sqsj,sqly,shzt,splc)");
		sql.append(" values(?,?,?,?,?,?,?)");
		String[] input = {t.getSqbh(),t.getGwdm(),t.getXh(),t.getSqsj(),t.getSqly(),t.getShzt(),t.getSplc()};
		return dao.insert(sql.toString(),input);
	}

	public List<HashMap<String,String>> getLzxsList(XsgwshForm t,User user)throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String shgwzByUser = SearchService.getShgwzByUser(user, "b",
				"xydm", "bjdm");
		StringBuilder sql = new StringBuilder();

		sql.append("select distinct ");
		sql.append(" SQBH,");
		sql.append(" XH,");
		sql.append(" XM,");
		sql.append(" NJ,");
		sql.append(" ZYDM,");
		sql.append(" XB,");
		sql.append(" SQSJ,");
		sql.append(" SPLC,");
		sql.append(" YRDWMC,");
		sql.append(" SHZTS,");
		sql.append(" GWMC,");
		sql.append(" SHSJ,");
		sql.append(" GWID,");
		sql.append(" SHID,");
		sql.append(" SHZT,");
		sql.append(" GWZ,");
		sql.append(" RN,");
		sql.append(" XYDM,");
		sql.append(" BJDM,");
		sql.append(" XN,");
		sql.append(" XYMC,");
		sql.append(" ZYMC,");
		sql.append(" BJMC,");
		sql.append(" xq,zybj,zybjmc,pycc");
		sql.append(" from (");
		sql.append("select distinct b.* from (select b.* from (select a.sqbh,b.xh,b.xm,b.nj,b.pycc,g1.zydm,");
		sql.append("(case b.xb when '1' then '男' when '2' then '女' else b.xb end) xb,");
		sql.append("a.sqsj,a.splc,nvl(w.yrdwmc,w1.bmmc) yrdwmc,d.shzt shzts,c.gwmc,d.shsj,d.gwid,d.guid shid,");
		sql.append("f.mc ||'['|| decode(d.shzt,0,'待审核',1,'通过',2,'不通过',3,'退回',4,'需重审',5,'审核中','其它')||']' shzt,f.gwz,");
		sql.append(" row_number()over(partition by a.sqbh order by d.shsj desc) rn, ");
		sql.append(" g1.xydm,g.bjdm,c.xn,c.xq,g1.xymc,g1.zymc,g1.bjdm zybj,g1.bjmc zybjmc,g.bjmc from XG_QGZX_XSLZSQB a ");
		sql.append(" left join xg_qgzx_gwxxb c on a.gwdm = c.gwdm  ");
		sql.append(" left join xsxxb b on a.xh = b.xh  ");
		sql.append(" left join xg_xtwh_shztb d on a.sqbh = d.ywid ");
		String shlx = t.getShlx();
		if (!shlx.equals("dsh")) {
			sql.append(" and (d.shzt<>0 and d.shzt<>4 )  ");
		} else {
			sql.append(" and ( d.shzt=0 or d.shzt=4 )  ");
		}
		sql.append(" left join xg_xtwh_spgwyh e on d.gwid = e.spgw  left join  xg_xtwh_spgw f on d.gwid = f.id ");
		sql.append(" left join view_njxyzybj_all g on b.bjdm=g.bjdm ");
		sql.append(" left join view_njxyzybj_all g1 on b.zybj=g1.bjdm ");
		sql.append(" left join XG_QGZX_YRDWDMB w on w.id = c.yrdwid ");
		sql.append(" left join ZXBZ_XXBMDM w1 on w1.bmdm=w.xydm ");
		sql.append(" where e.spyh = '" + user.getUserName() + "' ");
		sql.append(" ) b ");
		sql.append(" ) b");
		sql.append(" where 1=1 ");
		sql.append(" and rn = 1 "); // 取出最後一級
		sql.append(searchTj);
		sql.append(shgwzByUser);
		sql.append(" order by sqsj desc");
		sql.append(") v ");
		return getPageList(t,sql.toString(),inputV);
	}
	public HashMap<String,String> getLzModel(XsgwshForm model){
		StringBuilder sql = new StringBuilder();
		sql.append("select t.sqbh,t.sqsj,t.sqly,t.splc,t1.*,nvl(t2.yrdwmc,t3.bmmc) yrdwmc ");
		sql.append(" from XG_QGZX_XSLZSQB t");
		sql.append(" left join XG_QGZX_GWXXB t1 on t.gwdm = t1.gwdm ");
		sql.append(" left join XG_QGZX_YRDWDMB t2 on t2.id = t1.yrdwid ");
		sql.append(" left join ZXBZ_XXBMDM t3 on t3.bmdm=t2.xydm ");

		sql.append(" where t.sqbh = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{model.getSqbh()});
	}

	public boolean updateLzSh(XsgwshForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("update XG_QGZX_XSLZSQB set shzt = ? where sqbh = ?");
		return dao.runUpdate(sql.toString(),new String[]{t.getShzt(),t.getSqbh()});
	}

	public XsgwshForm getLzModel(String id) throws Exception {
		String sql = "select * from XG_QGZX_XSLZSQB where sqbh = ?";
		return getModel(sql,new String[]{id});
	}

	public List<HashMap<String,String>> getGzscList(XsgwshForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.gzrq,t.gzkssj || '-' || t.gzjssj gzsd ");
		sql.append(" ,t.gs,t4.gwxzmc gwlb,t1.gwmc,nvl(t2.yrdwmc,t3.bmmc) yrdwmc ");
		sql.append(" from XG_QGZX_MRKH_JGB t");
		sql.append(" left join XG_QGZX_GWXXB t1 on t.gwdm = t1.gwdm ");
		sql.append(" left join XG_QGZX_YRDWDMB t2 on t2.id = t1.yrdwid ");
		sql.append(" left join ZXBZ_XXBMDM t3 on t2.xydm = t3.bmdm ");
		sql.append(" left join XG_QGZX_GWXZDMB t4 on t1.gwlb = t4.gwxzdm ");
		sql.append(" where t.xh=? and t.gwdm=? ");
		return getPageList(model,sql.toString(),new String[]{model.getXh(),model.getGwdm()});
	}
}
