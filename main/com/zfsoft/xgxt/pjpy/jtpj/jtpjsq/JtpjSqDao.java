package com.zfsoft.xgxt.pjpy.jtpj.jtpjsq;

/**
 * @部门:学工产品事业部
 * @日期：2014-4-28 上午10:54:25 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-4-28 上午10:54:25
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JtpjSqDao extends SuperDAOImplExtend<JtpjSqForm> {
	@Override
	public List<HashMap<String, String>> getPageList(JtpjSqForm t)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();

		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	@Override
	public List<HashMap<String, String>> getPageList(JtpjSqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		// 用户类型
		String userType = user.getUserType();
		// jxpdzq
		// shlcmc
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select d.* ,n.pdxn,n.pdxq,n.jxmc,(select xqmc from xqdzb where xqdm=n.pdxq) pdxqmc ");
		sql.append(" from xg_pjpy_jtpj_jtjxsqb d ");			
		sql.append(" left join xg_pjpy_jtpj_jtjxsz n");
		sql.append(" on d.jxid=n.jxid)a ");
		sql.append(" where 1 = 1");
		sql.append(searchTj);
		if(!"admin".equalsIgnoreCase(userType)) {
			sql.append(" and a.sqr='"+user.getUserName()+"'");
		}
		return this.getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述: 获取班级寝室信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-6 下午02:44:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param bjdm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPageListForQsxx(JtpjSqForm t,
			String bjdm) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.pylbmc,t1.ldmc || ' '|| t1.qsh qs,t2.rs,t2.bjdm,t1.xn || t1.xq xnxq from VIEW_XG_GYGL_PYXXWHB t1 ");
		sql.append(" left join  ");
		sql.append(" (select count(xh) rs , lddm, ch,qsh,bjdm ");
		sql.append("  from view_xg_gygl_new_cwxx ");
		sql.append("  group by  lddm, ch,qsh,bjdm ");
		sql.append("  ) ");
		sql.append("  t2 ");
		sql.append(" on t1.lddm = t2.lddm ");
		sql.append(" and t1.qsh = t2.qsh ");
		sql.append(" and t1.ch = t2.ch ");
		sql.append(" where t2.bjdm=?");
		
		return this.getPageList(t, sql.toString(), new String[] { bjdm });
	}
	
	public List<HashMap<String, String>> getQsxsxxList(JtpjSqForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.lddm,t1.qsh,t1.cwh,t1.xh,t2.xm,t2.bjmc,t2.zymc,t2.xymc  ");
		sql.append("from xg_gygl_new_cwxxb t1 left join view_xsbfxx t2 on t1.xh=t2.xh ");
		sql.append("where t1.xh is not null and t2.xm is not null and t1.lddm=? and t1.qsh=? ");
		return this.getPageList(t, sql.toString(), new String[] {t.getLddm(),t.getQsh()});
	}

	/**
	 * 
	 * @描述: 班级人数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 上午11:30:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return String 返回类型
	 */
	public String getBjrs(String bjdm) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select count(xh) rs,bjdm from view_xsbfxx a group by bjdm) where bjdm=?");
		return dao.getOneRs(sql.toString(), new String[] { bjdm }, "rs");
	}

	/**
	 * 
	 * @描述: 学院人数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 下午01:41:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xydm
	 * @return String 返回类型
	 */
	public String getXyrs(String xydm) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select count(xh) rs,xydm from view_xsbfxx a group by xydm) where xydm=?");
		return dao.getOneRs(sql.toString(), new String[] { xydm }, "rs");
	}

	/**
	 * 
	 * @描述: 学院寝室数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 下午01:41:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xydm
	 * @return String 返回类型
	 */
	public String getXyQss(String xydm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (");
		sql.append("  select count(qs) qss, xydm");
		sql.append("   from (select distinct lddm || '&' || qsh qs, xydm");
		sql.append("   from VIEW_XG_GYGL_NEW_CWXX) a");
		sql.append("   group by xydm");
		sql.append(" ) where xydm=?");
		return dao.getOneRs(sql.toString(), new String[] { xydm }, "qss");
	}

	/**
	 * 
	 * @描述: 班级寝室数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 上午11:29:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return String 返回类型
	 */
	public String getQss(String bjdm) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select count(qs) qss, bjdm");
		sql.append(" from (select distinct lddm || '&' || qsh qs, bjdm");
		sql.append(" from VIEW_XG_GYGL_NEW_CWXX) a");
		sql.append(" group by bjdm )");
		sql.append(" where bjdm=?");
		return dao.getOneRs(sql.toString(), new String[] { bjdm }, "qss");
	}

	/**
	 * 
	 * @描述: 班级班主任信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 上午11:29:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getBzrxx(String bjdm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from BZRBBB a left join FDYXXB b on a.zgh=b.zgh where bjdm=?");
		return dao.getListNotOut(sql.toString(), new String[] { bjdm });
	}

	/**
	 * 
	 * @描述: 班级辅导员信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 上午11:29:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getFdyxx(String bjdm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from FDYBJB a left join FDYXXB b on a.zgh=b.zgh where bjdm=?");
		return dao.getListNotOut(sql.toString(), new String[] { bjdm });
	}

	@Override
	protected void setTableInfo() {
		setKey("sqid");
		setTableName("xg_pjpy_jtpj_jtjxsqb");
		setClass(JtpjSqForm.class);
	}

	/** 
	 * @描述:验证是否可提交
	 * @作者：qlm
	 * @日期：2014-5-26 上午11:25:35
	 * @param xjlbdm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String checkSfktj(String jxid) {		
		StringBuilder sql = new StringBuilder();
		String num = "";
		sql.append(" select count(1) num   ");
		sql.append("  from xg_pjpy_jtpj_jtjxsz t2 ");
		sql.append("  where t2.sfkfsq = '1'       ");
		sql.append("    and ((t2.SQKFKSSJ is not null and ");
		sql.append("        t2.SQKFKSSJ <= to_char(sysdate, 'yyyy-mm-dd')) or t2.SQKFKSSJ is null) ");		
		sql.append("    and ((t2.SQKFJSSJ is not null and ");
		sql.append("        t2.SQKFJSSJ >= to_char(sysdate, 'yyyy-mm-dd')) or t2.SQKFJSSJ is null) ");
		sql.append("    and t2.jxid = ? ");
		num = dao.getOneRs(sql.toString(), new String[] { jxid }, "num");
		return num;	
	}
	
	/**
	 * @描述: 打印Word登记表（上海电机学院）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-4 上午11:30:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 */
	public HashMap<String, String> getDjbInfo(String sqid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.sqid,a.sqxn, ");
		sql.append(" decode(a.jtpjdw,'xy',a.pjjtmc,'bj',b.xymc,'') xymc, ");
		sql.append(" decode(a.jtpjdw,'bj',a.pjjtmc,'') bjmc, ");
		sql.append(" decode(a.jtpjdw,'bj',c.xm,'') bzrxm, ");
		sql.append(" a.jtpjdw,a.pjjtmc,a.sqly,d.jxmc from xg_pjpy_jtpj_jtjxsqb a ");		
		sql.append(" left join VIEW_NEW_DC_XSXX_JCSJWH_BJ b on a.pjjtmc=b.bjmc ");
		sql.append(" left join ( ");
		sql.append(" select wm_concat(b.xm) xm,a.bjdm from BZRBBB a left join FDYXXB b on a.zgh=b.zgh group by a.bjdm ");
		sql.append(" ) c on a.pjjtid=c.bjdm ");
		sql.append(" left join xg_pjpy_jtpj_jtjxsz d on a.jxid=d.jxid ");
		sql.append(" where a.sqid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { sqid });
	}

	/**
	 * @描述: 打印Word登记表（河北工业大学）
	 * @作者：刘禹昕
	 * @日期：2018-7-24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 */
	public HashMap<String, String> getjtpjInfo(String sqid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.sqid,a.sqxn,a.pjjtid as bjdm,t4.mbmc, ");
		sql.append(" decode(a.jtpjdw,'xy',a.pjjtmc,'bj',b.xymc,'') xymc, ");
		sql.append(" decode(a.jtpjdw,'bj',a.pjjtmc,'') bjmc, ");
		sql.append(" decode(a.jtpjdw,'bj',c.xm,'') bzrxm, t3.xm as fdyxm,  (select count(*) from view_xsxxb y where y.BJDM = a.pjjtid)as bjrs ,");
		sql.append("( select x.xm from VIEW_NEW_DC_SZDW_XSDWWH x where x.BJDM= a.pjjtid and x.ZWMC='班长')as bzmc, ");
		sql.append(" a.jtpjdw,a.pjjtmc,a.sqly,d.jxmc from xg_pjpy_jtpj_jtjxsqb a ");
		sql.append(" left join VIEW_NEW_DC_XSXX_JCSJWH_BJ b on a.pjjtmc=b.bjmc ");
		sql.append(" left join ( ");
		sql.append(" select wm_concat(b.xm) xm,a.bjdm from BZRBBB a left join FDYXXB b on a.zgh=b.zgh group by a.bjdm ");
		sql.append(" ) c on a.pjjtid=c.bjdm ");
		sql.append(" left join xg_pjpy_jtpj_jtjxsz d on a.jxid=d.jxid  ");
		sql.append("left join XG_PJPY_JTPJ_JTJXBBDMB t4  on d.dybbid=t4.bbdm ");
		sql.append(" left join (select t1.*,t2.xm from  fdybjb t1  left join fdyxxb t2 on t1.zgh = t2.zgh) t3 on a.pjjtid = t3.bjdm");
		sql.append(" where a.sqid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { sqid });
	}
	
	public HashMap<String, String> getDjb(String sqid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select  a.*, b.jxmc, b.dybbid, b.pdxn, c.bbmc, c.mbmc from XG_PJPY_JTPJ_JTJXSQB a ");
		sql.append(" left join XG_PJPY_JTPJ_JTJXSZ b on a.jxid = b.jxid ");
		sql.append("left join XG_PJPY_JTPJ_JTJXBBDMB c  on b.dybbid=c.bbdm ");
		sql.append(" where a.sqid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { sqid });
	}
	
	public String getBjmc(String bjdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select bjmc from view_njxyzybj where bjdm = ?");
		return dao.getOneRs(sql.toString(), new String[]{bjdm}, "bjmc");
	}
	
	/**
	 * 
	 * @描述:检验是否不存在申请记录，不存在返回true
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-16 上午11:20:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jxid
	 * @param jtpjid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExists(String jxid,String pjjtid,String pjjtmc,String sqid,String flag){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		String tablename = "";
		if("sq".equals(flag)){
			tablename = "xg_pjpy_jtpj_jtjxsqb";
		}else{
			tablename = "xg_pjpy_jtpj_jtpjjgb";
		}
		sql.append(" select count(1) num from "+ tablename +" t where jxid = ? ");
		paralist.add(jxid);
		if(StringUtils.isNotNull(pjjtid)){
			sql.append(" and pjjtid = ? ");
			paralist.add(pjjtid);
		}
		if(StringUtils.isNotNull(pjjtmc)){
			sql.append(" and pjjtmc = ? ");
			paralist.add(pjjtmc);
		}
		//如果申请id不为空，表示是修改保存，必须加入下面这行sql
		if("sq".equals(flag) && StringUtils.isNotNull(sqid) ){
			sql.append(" and sqid != ?");
			paralist.add(sqid);
		}
		if("jg".equals(flag) && StringUtils.isNotNull(sqid) ){
			sql.append(" and jgid != ?");
			paralist.add(sqid);
		}
		String numstr = dao.getOneRs(sql.toString(), paralist.toArray(new String[]{}), "num");
		if("0".equals(numstr)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @描述: 获取以班级为评奖单位的评奖集体其他单位信息（学院，年级等）
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-17 上午11:10:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjmc
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getBjxgxx(String bjmc){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct * from view_njxyzybj t where t.bjmc = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{bjmc});
	}

	public List<HashMap<String,String>> getbjgbInfo(String bjdm) {
		StringBuilder sql = new StringBuilder();
		sql.append("  select a.xm,a.zwmc from VIEW_NEW_DC_SZDW_XSDWWH a where a.zwmc!='班长' and a.bjdm = ? ");
		return dao.getListNotOut(sql.toString(), new String[] { bjdm });
	}
}
