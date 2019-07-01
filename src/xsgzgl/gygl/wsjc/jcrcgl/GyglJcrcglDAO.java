package xsgzgl.gygl.wsjc.jcrcgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.comm.BasicModel;
import xsgzgl.gygl.comm.GyglNewInit;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: qlj
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-14 下午03:45:37
 * </p>
 */
public class GyglJcrcglDAO extends CommDAO {
	
	/**
	 * 卫生检查检查日程管理
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJcrcglList(GyglJcrcglForm model) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 用户对象
		User user = model.getUser();
		String[] colList = null;
		
		if (GyglNewInit.WSJC_XJQS){
			if("18180".equals(Base.xxdm)){
				colList = new String[] { "guid", "xn", "xqmc","jcyf", "mc", "lxmc","kssj", "jssj", "bzInfo", "bz", "sfwh","tjztInfo" };
			}else{
			colList = new String[] { "guid", "xn", "xqmc","mc", "lxmc","kssj", "jssj", "bzInfo", "bz", "sfwh","tjztInfo" };
			}
		} else {
			colList = new String[] { "guid", "xn", "xqmc", "mc", "kssj", "jssj", "bzInfo", "bz", "sfwh","tjztInfo" };
		}
		if("12688".equals(Base.xxdm)){
			colList = new String[] { "guid", "xn", "xqmc", "mc", "lxmc","kssj", "jssj", "bzInfo", "bz", "sfwh","tjztInfo","pfjbmc" };
		}
		// 用户属性
		String userType = user.getUserType();
		// ====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append("select a.*,rownum r from ( select a.guid,a.xn,a.xq,a.jcyf,a.mc,a.lxmc,");
		sql.append("a.jclx,a.kssj,a.jssj,a.bz,a.xqmc,a.tjzt,a.tjztInfo,a.bzinfo,a.sfwh " );
		if("12688".equals(Base.xxdm))
			sql.append(" ,a.pfjbmc");
		sql.append(" from(  ");
		sql.append(" select a.*,decode(a.jclx,'0','分数','1','等级','2','星级') lxmc,b.xqmc,case when length(bz)>15  ");
		sql.append(" then substr(bz,0,15)||'...' else bz end bzInfo, ");
		sql.append(" case when tjzt='1' then '已提交' else '未提交' end tjztInfo, ");
		sql.append("(select a.guid from (select guid from XG_GYGL_NEW_WSJC_QSFSB group by guid) where a.guid=guid) sfwh ");
		if("12688".equals(Base.xxdm))
			sql.append(" , decode(a.pfjb, 'xj', '校级', 'yj', '院级') pfjbmc ");
		sql.append(" from xg_gygl_new_wsjc_jcrcb a,xqdzb b where a.xq=b.xqdm)a ");
		sql.append(query);
		sql.append(" order by kssj desc ) a");
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(), "", inputV, colList, model);
		return list;
	}

	/**
	 * 查询单条卫生检查日程信息
	 * 
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJcrcglMap(GyglJcrcglForm model) {
		DAO dao = DAO.getInstance();
		String guid = model.getGuid();
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.guid,a.xn,a.xq,a.mc,a.jcyf,a.jclx,a.kssj,a.jssj,a.bz,b.xqmc from xg_gygl_new_wsjc_jcrcb a,xqdzb b where a.xq=b.xqdm and guid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { guid });
	}

	/**
	 * 日程名称不可重复的检查
	 * 
	 * @param model
	 * @param Mc
	 * @return
	 * @throws IllegalArgumentException
	 *             , SecurityException, IllegalAccessException,
	 *             InvocationTargetException, NoSuchMethodException
	 */
	public boolean findInfo(BasicModel model, String Mc)
			throws IllegalArgumentException, SecurityException,IllegalAccessException, InvocationTargetException,NoSuchMethodException {
		DAO dao = DAO.getInstance();
		String rs = dao.getOneRs("select count(*) cnt from XG_GYGL_NEW_WSJC_JCRCB where mc =?",new String[] { Mc }, "cnt");
		if (!rs.equals("0")) {
			return true;
		}
		return false;
	}

	/**
	 * 起止时间不可重叠的检查
	 * 
	 * @param model
	 * @param Mc
	 * @return
	 * @throws IllegalArgumentException , SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
	 */
	public String findQzsj(BasicModel model, String Kssj, String Jssj,String Xn, String Xq, String jclx) 
			throws IllegalArgumentException,SecurityException, IllegalAccessException,InvocationTargetException, NoSuchMethodException {
		String pk=model.getPkValue()[0];
		long kssj1 = Long.parseLong(Kssj);
		long jssj1 = Long.parseLong(Jssj);
		long XnKs = Long.parseLong(Xn.substring(0, 4));
		long XnJs = Long.parseLong(Xn.substring(5, 9));
		long KssjXn = Long.parseLong(Kssj.substring(0, 4));
		long JssjXn = Long.parseLong(Jssj.substring(0, 4));
		if (kssj1 > jssj1) {
			return "开始时间不能大于结束时间";
		}
		if (KssjXn < XnKs || JssjXn > XnJs) {
			return "起止时间不在学年范围内";
		}
		
		StringBuffer sql = new StringBuffer("select count(1) count from xg_gygl_new_wsjc_jcrcb where (? between kssj and jssj or ? between kssj and jssj) ");

		List<String> params = new ArrayList<String>();
		params.add(Kssj);
		params.add(Jssj);
		
		if(!StringUtils.isBlank(jclx)) {
			sql.append(" and jclx = ?");
			params.add(jclx);
		}
		
		if(!StringUtils.isBlank(pk)){
			sql.append(" and guid<> ?");
			params.add(pk);
		}
		
		DAO dao = DAO.getInstance();
		String count = dao.getOneRs(sql.toString(),  params.toArray(new String[]{}), "count");
		//判断是否是天津市经济贸易学校，若是，直接放开日期重复查询
		 if (!count.equals("0") && !Base.xxdm.equals("1103202") && !Base.xxdm.equals("13011")) {
			return "起止时间不能重叠";
		}
		
//		String sql = "select guid,Kssj,Jssj,rownum r from XG_GYGL_NEW_WSJC_JCRCB where xn=? and xq=?";
//		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql, "", new String[] { Xn, Xq }, new String[] {"guid","Kssj", "Jssj" }, model);
//		for (String[] val : list) {
//			//张昌路 不判断自身，如果是自身直接返回
//			if(pk.equals(val[0])){
//				continue;
//			}
//			
//			long kssj2 = Long.parseLong(val[1]);
//			long jssj2 = Long.parseLong(val[2]);
//			if (kssj1 > kssj2 && kssj1 < jssj2) {
//				return "起止时间不能重叠";
//			} else if (jssj1 > kssj2 && jssj1 < jssj2) {
//				return "起止时间不能重叠";
//			} else if (kssj2 > kssj1 && kssj2 < jssj1) {
//				return "起止时间不能重叠";
//			} else if (jssj2 > kssj1 && jssj2 < jssj1) {
//				return "起止时间不能重叠";
//			}
//		}
		return "起止时间可用！";
	}
	
	/**
	 * 检查日程管理 自定义设置
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJcrcglExportList(GyglJcrcglForm model,User user) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		
		String[] colList = new String[] { "guid", "xn", "xqmc", "mc", "kssj", "jssj", "bzInfo", "bz", "sfwh" };
		// 用户属性
		String userType = user.getUserType();
		// ====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.guid,a.lxmc,a.xn,a.xq,a.mc,a.kssj,a.jssj,a.bz,a.xqmc,a.bzinfo,a.sfwh, ");
		sql.append(" case nvl(tjzt,0) when '1' then '已提交' else '未提交' end tjzt,rownum r from( ");
		sql.append(" select a.*,decode(a.jclx,'0','分数','1','等级','2','星级') lxmc,b.xqmc,case when length(bz)>15  ");
		sql.append(" then substr(bz,0,15)||'...' else bz end bzInfo, ");
		sql.append("(select a.guid from (select guid from XG_GYGL_NEW_WSJC_QSFSB group by guid) where a.guid=guid) sfwh ");
		sql.append(" from xg_gygl_new_wsjc_jcrcb a,xqdzb b where a.xq=b.xqdm)a ");
		sql.append(query);
		sql.append(" order by kssj desc");
		// ====================SQL拼装 end================================
		
//		sql.append("select rownum r , k.* from VIEW_NEW_DC_GYGL_JCRCGL k ").append(query);
		
		List<HashMap<String, String>> list =   CommonQueryDAO.commonQueryforExportList(sql.toString(), "", inputV, colList, model);
		return list;
	}

	/**
	 * @throws SQLException  
	 * @描述:修改检查日程管理为提交状态
	 * @作者：cq [工号：785]
	 * @日期：2013-8-29 上午10:13:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean tjJcrcgl(String pkStr) {

		DAO dao = DAO.getInstance();

		String[] pkValue = pkStr.split("!!array!!");

		StringBuffer sql = new StringBuffer();
		sql.append(" update xg_gygl_new_wsjc_jcrcb ");
		sql.append(" set tjzt='1' ");
		sql.append(" where guid=? ");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < pkValue.length; i++) {
			String[] val = new String[1];
			val[0] = pkValue[i];
			params.add(val);
		}
		try {
			dao.runBatch(sql.toString(), params);
			return true;		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;		
		}

	}
	
	/**
	 * 
	 * @描述: 提交同步更新到XG_QSFMX表（上海戏剧个性化）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-1-5 下午04:12:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pkStr
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSubmit(String pkStr) {

		DAO dao = DAO.getInstance();

		String[] pkValue = pkStr.split("!!array!!");

		StringBuffer sql = new StringBuffer();
		sql.append(" insert into XG_QSFMX(xh,xn,xq,rcid,lddm,qsh,cwh,qsfs,cwfs,zfs) ");
		sql.append(" select a.xh, a.xn, a.xq, a.guid, lddm, qsh, cwh, sum(a.qsfs) qsfs, sum(cwfs) as cwfs, sum(zfs) as zfs ");		
		sql.append(" from view_new_gygl_qswsffs a ");
		sql.append(" where a.guid = ? ");
		sql.append(" group by a.xh, a.xn, a.xq, a.guid, lddm, qsh, cwh ");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < pkValue.length; i++) {
			String[] val = new String[1];
			val[0] = pkValue[i];
			params.add(val);
		}
		try {
			dao.runBatch(sql.toString(), params);
			return true;		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;		
		}

	}
	
	/**
	 * 
	 * @描述: 取消提交同步删除对应数据XG_QSFMX表（上海戏剧个性化）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-1-5 下午04:12:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pkStr
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delCancel(String pkStr) {

		DAO dao = DAO.getInstance();

		String[] pkValue = pkStr.split("!!array!!");

		StringBuffer sql = new StringBuffer();
		sql.append(" delete from XG_QSFMX ");
		sql.append(" where rcid = ? ");
		
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < pkValue.length; i++) {
			String[] val = new String[1];
			val[0] = pkValue[i];
			params.add(val);
		}
		try {
			dao.runBatch(sql.toString(), params);
			return true;		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;		
		}

	}
	
	
	/**
	 * 
	 * @描述:保存学生卫生分记录
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-11-28 上午11:30:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pkStr
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean bcXsWsf(List<HashMap<String,String>> xswsf) {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into XG_GYGL_NEW_WSJC_XSFSB (jcrcid,xn,xq,xh,fs,djbz) ");
		sql.append(" values(?,?,?,?,?,?) ");
		List<String[]> params = new ArrayList<String[]>();
		if("10344".equals(Base.xxdm)){
			for (int i = 0; i < xswsf.size(); i++) {
				String[] val = new String[6];
				val[0] = xswsf.get(i).get("guid");
				val[1] = xswsf.get(i).get("xn");
				val[2] = xswsf.get(i).get("xq");
				val[3] = xswsf.get(i).get("xh");
				val[4] = xswsf.get(i).get("djs");
				val[5] = xswsf.get(i).get("pfbz");
				params.add(val);
			}
		}else{
			for (int i = 0; i < xswsf.size(); i++) {
				String[] val = new String[6];
				val[0] = xswsf.get(i).get("guid");
				val[1] = xswsf.get(i).get("xn");
				val[2] = xswsf.get(i).get("xq");
				val[3] = xswsf.get(i).get("xh");
				val[4] = xswsf.get(i).get("xsfs");
				val[5] = xswsf.get(i).get("pfbz");
				params.add(val);
			}
		}
		try {
			dao.runBatch(sql.toString(), params);
			return true;		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;		
		}

	}
	/**
	 * 
	 * @描述:取消卫生检查时删除学生卫生分记录
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-11-28 下午02:05:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xswsf
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean scXsWsf(String pkStr) {
        DAO dao = DAO.getInstance();
		String[] pkValue = pkStr.split("!!array!!");
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from XG_GYGL_NEW_WSJC_XSFSB where jcrcid=?");	
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < pkValue.length; i++) {
			String[] val = new String[1];
			val[0] = pkValue[i];
			params.add(val);
		}
		try {
			dao.runBatch(sql.toString(), params);
			return true;		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;		
		}

	}
	
	/**
	 * 
	 * @描述:获取寝室入住学生卫生分
	 * @作者：xiaxia [工号:1104]
	 * @日期：2014-11-28 上午11:15:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guids
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXsfs(String pkStr){
		String[] guids = pkStr.split("!!array!!");
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		
		//浙江中医药 准毕业生床位不打分
		if("10344".equals(Base.xxdm)){
			sql.append(" select m.*,case when (to_number(nvl(n.nj,0))+to_number(nvl(n.xz,0))) = to_number(Substr(m.jcrq, 0, 4)) then 'N' else m.dj end djs from ( ");
			sql.append(" select a.*,d.cwh,d.xh,b.xn,b.xq,(select b.fssx from XG_GYGL_NEW_WSJC_DJFSB b where b.dj=a.dj ) xsfs");
			sql.append(" from xg_gygl_new_wsjc_qsfsb a");
			sql.append(" left join xg_gygl_new_wsjc_jcrcb b on a.guid = b.guid left join xg_gygl_new_cwxxb d");
			sql.append(" on a.lddm = d.lddm and a.qsh = d.qsh where a.guid in (");
			for (int i = 0; i < guids.length; i++) {
				sql.append("?");
				if(i!=guids.length-1){
					sql.append(",");
				}
			}
			sql.append(") and d.sfbl='否' and d.xh is not null");
			sql.append(" ) m left join xsxxb n on m.xh = n.xh ");
		} else {
			sql.append("select a.*,d.cwh,d.xh,b.xn,b.xq,(select b.fssx from XG_GYGL_NEW_WSJC_DJFSB b where b.dj=a.dj ) xsfs");
			sql.append(" from xg_gygl_new_wsjc_qsfsb a");
			sql.append(" left join xg_gygl_new_wsjc_jcrcb b on a.guid = b.guid left join xg_gygl_new_cwxxb d");
			sql.append(" on a.lddm = d.lddm and a.qsh = d.qsh where a.guid in (");
			for (int i = 0; i < guids.length; i++) {
				sql.append("?");
				if(i!=guids.length-1){
					sql.append(",");
				}
			}
			sql.append(") and d.sfbl='否' and d.xh is not null");
		}
		
		return dao.getListNotOut(sql.toString(), guids);
	}
	
	/**
	 * 
	 * @描述:取消提交状态
	 * @作者：cq [工号：785]
	 * @日期：2013-8-29 上午11:45:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pkStr
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean qxtjJcrcgl(String pkStr){
		
		DAO dao = DAO.getInstance();
		
		String[] pkValue = pkStr.split("!!array!!");

		StringBuffer sql = new StringBuffer();
		sql.append(" update xg_gygl_new_wsjc_jcrcb ");
		sql.append(" set tjzt='' ");
		sql.append(" where guid=? ");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < pkValue.length; i++) {
			String[] val = new String[1];
			val[0] = pkValue[i];
			params.add(val);
		}
		try {
			dao.runBatch(sql.toString(), params);
			return true;		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;		
		}
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-10 上午10:17:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateByqsForZjcm(String[] pkValue) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" update XG_GYGL_NEW_WSJC_QSFSB t");
		sql.append(" set t.BYQS =(select t1.byqs from");
		sql.append(" view_zjcm_xg_gygl_byqs t1 ");
		sql.append(" where t1.lddm || t1.qsh = t.lddm || t.qsh");
		sql.append(" and t.guid in(");
		for (int i = 0; i < pkValue.length; i++) {
			sql.append("?");
			if(i != pkValue.length-1){
				sql.append(",");
			}
			paraList.add(pkValue[i]);
		}
		sql.append(" ))");
		sql.append("  where exists(select 1 from  ");
		sql.append("  view_zjcm_xg_gygl_byqs t1  where t1.lddm || t1.qsh = t.lddm || t.qsh");
		sql.append("  and t.guid in (");
		for (int i = 0; i < pkValue.length; i++) {
			sql.append("?");
			if(i != pkValue.length-1){
				sql.append(",");
			}
			paraList.add(pkValue[i]);
		}
		sql.append(" ))");
		return DAO.getInstance().runUpdate(sql.toString(), paraList.toArray(new String[]{}));
	}
}