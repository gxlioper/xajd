package xsgzgl.gygl.wsjc.fscx;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewInit;
import xsgzgl.gygl.comm.GyglNewService;

import com.zfsoft.utils.StringUtil;
import common.Globals;

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
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-28 上午11:29:22
 * </p>
 */
public class FscxDAO extends CommDAO {

	/**
	 * 卫生检查，获得卫生分信息
	 * 
	 * @param myForm
	 * @return
	 * @throws IllegalArgumentException, SecurityException, IllegalAccessException,InvocationTargetException, NoSuchMethodException
	 */
	public ArrayList<String[]> getFscxCx(FscxForm myForm,HttpServletRequest request)
			throws IllegalArgumentException, SecurityException,IllegalAccessException, InvocationTargetException,NoSuchMethodException {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		String[] bjtjList = searchModel.getSearch_tj_bj();
		searchModel.setSearch_tj_bj(null);
		// 用户对象
		User user = myForm.getUser();
		String[] colList = new String[] {};
		// 用户属性
		String userType = user.getUserType();
		// ====================过滤条件===================================
		//不清楚原来为什么要将usertype置入usersatus,现在考虑到天津经贸的要求，将这行代码单独屏蔽
		if(!Globals.XXDM_TJJM.equals(Base.xxdm)){
			user.setUserStatus(userType);
		}
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		
		//权限控制
		String searchTjQx="";
		
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		}
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "c", "xydm", "bjdm");
		
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj+searchTjQx;
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();
		//浙江理工个性化页面（分数、等级都要显示，等级是根据分数计算出来的）
		if(Globals.XXDM_ZJLG.equals(Base.xxdm) || "1".equals(GyglNewInit.FSCX_XSFS)){
			colList = new String[] { "guid", "ldmc","ch","qsh", "shnj", "shxy", "cws", "rzrs", "jcrc", "fz", "dj","lddm" };
			sql.append(" select a.*,rownum r from (select distinct b.guid,b.ldmc,b.lddm,b.ch,b.qsh,b.shnj,b.shxy xydm,");
			sql.append("(select bmmc from zxbz_xxbmdm b where b.bmdm = b.shxy ) shxy,b.cws,b.rzrs,b.jcrc,");
			sql.append("b.fz,b.dj,b.xn,b.xq,b.jclx,b.kssj,b.jssj from VIEW_XG_GYGL_NEW_WSJC_QSFSB b");
		}else{
			if (("0").equals(GyglNewInit.JFFS)) {
				if(Base.xxdm.equals("33333")){
					colList = new String[] { "guid", "ldmc", "ch", "qsh", "shnj", "shxy", "cws", "rzrs", "jcrc", "fz", "kfyj" , "pfbz", "lddm" };
				}else if("11647".equals(Base.xxdm)){
					colList = new String[] { "guid", "ldmc", "ch", "qsh", "shnj", "shxy", "cws", "rzrs", "jcrc", "fz","dj", "lddm" };
				}else{					
					colList = new String[] { "guid", "ldmc", "ch", "qsh", "shnj", "shxy", "cws", "rzrs", "jcrc", "fz", "lddm" };
				}
				sql.append(" select a.*,rownum r from (select distinct b.guid,b.ldmc,b.lddm,b.ch,b.qsh,b.shnj,b.shxy xydm,");
				sql.append("(select bmmc from zxbz_xxbmdm b where b.bmdm = b.shxy ) shxy,b.cws,b.rzrs,b.jcrc,");
				if(Base.xxdm.equals("33333")){
					sql.append("b.fz,b.xn,b.xq,b.jclx,b.kfyj,b.pfbz,b.kssj,b.jssj from view_xg_gygl_new_wsjc_qskfb b");
				}else if("11647".equals(Base.xxdm)){
					sql.append(" b.fz,b.xn,b.xq,b.jclx,b.kssj,b.jssj,case when b.fz < 70 then '不合格' when  b.fz >= 70 and b.fz < 85 then '合格' else '优秀' end  dj  from VIEW_XG_GYGL_NEW_WSJC_QSFSB b");
				}else{					
					sql.append("b.fz,b.xn,b.xq,b.jclx,b.kssj,b.jssj from VIEW_XG_GYGL_NEW_WSJC_QSFSB b");
				}
			} else {
				colList = new String[] { "guid", "ldmc", "ch", "qsh", "shnj", "shxy", "cws", "rzrs", "jcrc", "dj", "lddm" };
				sql.append(" select a.*,rownum r from (select distinct b.guid,b.ldmc,b.lddm,b.ch,b.qsh,b.shnj,b.shxy xydm,");
				sql.append("(select bmmc from zxbz_xxbmdm b where b.bmdm = b.shxy ) shxy,b.cws,b.rzrs,b.jcrc,");
				sql.append("b.dj,b.xn,b.xq,b.jclx,b.kssj,b.jssj from VIEW_XG_GYGL_NEW_WSJC_QSFSB b");
			}
		}
		sql.append(" left join (select a.lddm,a.qsh,a.xh,b.bjdm,b.zydm,b.xydm,b.nj from xg_gygl_new_cwxxb a left join view_xsbfxx b on a.xh=b.xh where a.xh is not null) c on b.lddm=c.lddm and b.qsh=c.qsh ");
		sql.append("where 1=1 ");
		/**
		 * 天津经贸个性化需求
		 */
		if(Globals.XXDM_TJJM.equals(Base.xxdm)){
			if(bjtjList != null && bjtjList.length >0){
				sql.append(" and c.bjdm in(");
				for (int i = 0; i < bjtjList.length; i++) {
					sql.append("'"+bjtjList[i]+"'");
					if(i != bjtjList.length-1){
						sql.append(",");
					}
				}
				sql.append(") ");
			}
		}
		sql.append(" "+searchTjByUser);

		/**
		 * 天津经贸个性化需求
		 */
		if(Globals.XXDM_TJJM.equals(Base.xxdm)){
			sql.append(" order by to_number(fz),lddm,ch,qsh");
		}
		
		sql.append(") a ");
		sql.append(query);
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(), "", inputV, colList,myForm);
		return list;
	}

	/**
	 * 卫生检查，获得卫生分检查日程
	 * 
	 * @param fscxForm
	 * @param pkValue
	 * @return
	 * @throws
	 */
	public HashMap<String, String> getFscxCz2(FscxForm fscxForm, String pkValue) {
		DAO dao = DAO.getInstance();
		String sql = "select guid,mc,kssj,jssj,jclx,rownum r from XG_GYGL_NEW_WSJC_JCRCB where guid=?";
		return dao.getMapNotOut(sql.toString(), new String[] { pkValue });
	}

	/**
	 * 卫生检查，获得卫生分修改前得信息
	 * 
	 * @param fscxForm
	 * @return
	 * @throws
	 */
	public HashMap<String, String> getFscxMap(FscxForm fscxForm) {
		DAO dao = DAO.getInstance();
		String guid = fscxForm.getGuid();
		String lddm = fscxForm.getLddm();
		String qsh = fscxForm.getQsh();
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.guid,a.lddm,a.qsh,a.jcrq,a.jcbm,a.jcry,a.fs,a.dj,a.bz,a.lrr,a.lrsj,pfbz  ");
		if(Globals.XXDM_TJJM.equals(Base.xxdm)){
			sql.append(",a.pfbzname kfmc,a.fjpath");
		}
		sql.append(" from XG_GYGL_NEW_WSJC_QSFSB a ");
		/*
		//天津经贸
		if(Globals.XXDM_TJJM.equals(Base.xxdm)){
			sql.append(" left join xg_gygl_wsflrbz b on a.pfbz = b.kfdm");
		}*/
		sql.append(" ");
		sql.append(" where guid=? and lddm=? and qsh=?");
		return dao.getMapNotOut(sql.toString(),new String[] { guid, lddm, qsh });
	}
	
	public HashMap<String, String> getFscxAllMap(FscxForm fscxForm) {
		DAO dao = DAO.getInstance();
		String guid = fscxForm.getGuid();
		String lddm = fscxForm.getLddm();
		String qsh = fscxForm.getQsh();
		StringBuilder sql = new StringBuilder();
		sql.append(" select b.guid,b.ldmc,b.lddm,b.ch,b.qsh,b.shnj,(select bmmc from zxbz_xxbmdm b where b.bmdm = b.shxy ) shxy,b.cws,b.rzrs,b.jcrc,b.dj,rownum r from VIEW_XG_GYGL_NEW_WSJC_QSFSB b where guid=? and lddm=? and qsh=?");
		return dao.getMapNotOut(sql.toString(),new String[] { guid, lddm, qsh });
	}

	/**
	 * 卫生检查，对卫生分信息的修改
	 * 
	 * @param myForm
	 * @param pkValue
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public boolean fscxXg(FscxForm myForm, String pkValue, String username)throws Exception {
		boolean flag = false;
		DAO dao = DAO.getInstance();
		String lddm = myForm.getLddm();
		String qsh = myForm.getQsh();
		String fz = myForm.getFz();
		String dj = myForm.getDj();
		String jcrq = myForm.getJcrq();
		String jcbm = myForm.getJcbm();
		String jcry = myForm.getJcry();
		String bz = myForm.getBz();
		String sql = "delete from XG_GYGL_NEW_WSJC_QSFSB where guid=? and lddm=? and qsh=?";
		flag = dao.runUpdate(sql.toString(),new String[] { pkValue, lddm, qsh });
		if (flag == true) {
			if (("0").equals(GyglNewInit.JFFS)) {
				sql = "insert into XG_GYGL_NEW_WSJC_QSFSB (GUID,LDDM,QSH,JCRQ,JCBM,JCRY,FS,DJ,BZ,LRR,LRSJ) values (?,?,?,?,?,?,?,'',?,?,?)";
				flag = dao.runUpdate(sql.toString(), new String[] { pkValue,lddm, qsh, jcrq, jcbm, jcry, fz, bz, username, jcrq });
			} else {
				sql = "insert into XG_GYGL_NEW_WSJC_QSFSB (GUID,LDDM,QSH,JCRQ,JCBM,JCRY,FS,DJ,BZ,LRR,LRSJ) values (?,?,?,?,?,?,'',?,?,?,?)";
				flag = dao.runUpdate(sql.toString(), new String[] { pkValue,lddm, qsh, jcrq, jcbm, jcry, dj, bz, username, jcrq });
			}
		}
		return flag;
	}

	/**
	 * 卫生检查，对卫生分信息的删除
	 * 
	 * @param myForm
	 * @param valArr
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public boolean fscxSc(FscxForm myForm, String[] valArr, String username)
			throws SQLException {
		List<String[]> sqlList = new ArrayList<String[]>();
		String sql = "delete from XG_GYGL_NEW_WSJC_QSFSB where guid||lddm||qsh=?";
		String[] val = null;
		for (String threeVal : valArr) {
			val = new String[1];
			val[0] = threeVal.replaceAll("!@", "");
			sqlList.add(val);
		}
		DAO dao = DAO.getInstance();
		boolean flag = dao.checkBatchResult(dao.runBatch(sql, sqlList));
		return flag;
	}

	/**
	 * 卫生检查，获得等级下拉列表框
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Object getDjList(HttpServletRequest request) {
		String sql = "select dj,dj from XG_GYGL_NEW_WSJC_DJFSB where lx='0'";
		return new DAO().getList(sql, new String[] {}, new String[] { "dj","dj" });
	}
	
	public Object getXjList(HttpServletRequest request) {
		String sql = "select dj,dj from XG_GYGL_NEW_WSJC_DJFSB where lx='1'";
		return new DAO().getList(sql, new String[] {}, new String[] { "dj","dj" });
	}
	
	/**
	 * 卫生分查询自定义导出
	 * 
	 * @param myForm
	 * @return
	 * @throws IllegalArgumentException, SecurityException, IllegalAccessException,InvocationTargetException, NoSuchMethodException
	 */
	public List<HashMap<String, String>> getFscxExportCx(FscxForm myForm,HttpServletRequest request,User user)
			throws IllegalArgumentException, SecurityException,IllegalAccessException, InvocationTargetException,NoSuchMethodException {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		//User user = myForm.getUser();
		String[] colList = new String[] {};
		// 用户属性
		String userType = user.getUserType();
		// ====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "c", "xydm", "bjdm");
		
		//权限控制
		String searchTjQx="";
		
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		}
		
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj+searchTjQx;
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();
		if(Globals.XXDM_ZJJDZYJSXY.equalsIgnoreCase(Base.xxdm)){
			colList = new String[] { "guid", "ldmc", "ch", "qsh", "shnj", "shxy","bjmc","cws", "rzrs", "jcrc", "fz", "lddm" ,"jcrq" ,"jcbm" ,"jcry" };
		    sql.append(" select a.*, rownum r from ( select * from view_xg_gygl_new_wsjc_zjjddc where 1=1 ");
		    sql.append(searchTjByUser);
		    sql.append(" order by lddm,qsh,ch  ) a ");
		}else{
			sql.append("select a.*, rownum r from ( ");
			//浙江理工
			if(Globals.XXDM_ZJLG.equalsIgnoreCase(Base.xxdm)  || "1".equals(GyglNewInit.FSCX_XSFS)){
				//浙江理工的sql组装
				colList = new String[] { "guid", "ldmc", "ch", "qsh", "shnj", "shxy", "cws", "rzrs", "jcrc", "fz", "lddm" ,"jcrq" ,"jcbm" ,"jcry" };
				sql.append(" select distinct b.guid,b.ldmc,b.lddm,b.ch,b.qsh,b.shnj,(select bmmc from zxbz_xxbmdm b where b.bmdm = b.shxy ) shxy,b.cws,b.rzrs,b.jcrc,b.fz,b.dj,b.xn,b.xq,b.jcrq,b.jcbm,b.jcry from VIEW_XG_GYGL_NEW_WSJC_QSFSB b");
			}else{
				//通用的sql组装
				if (("0").equals(GyglNewInit.JFFS)) {
					if("11647".equals(Base.xxdm)){
						colList = new String[] { "ldmc", "ch", "qsh", "shnj", "shxy", "cws", "rzrs", "jcrc", "fz", "lddm" ,"jcrq" ,"jcbm" ,"jcry","dj" };
						sql.append(" select distinct b.guid,b.ldmc,b.lddm,b.ch,b.qsh,b.shnj,(select bmmc from zxbz_xxbmdm b where b.bmdm = b.shxy ) shxy,b.cws,b.rzrs,b.jcrc,b.fz,b.xn,b.xq,b.jcrq,b.jcbm,b.jcry,case when b.fz < 70 then '不合格' when b.fz >= 70 and b.fz < 85 then '合格' else '优秀'	end dj from VIEW_XG_GYGL_NEW_WSJC_QSFSB b");
					}else if(Globals.XXDM_TJJM.equals(Base.xxdm)){
						colList = new String[] { "ldmc", "ch", "qsh", "shnj", "shxy", "cws", "rzrs", "jcrc", "fz", "lddm" ,"jcrq" ,"jcbm" ,"jcry","pfbzname" };
						sql.append(" select distinct b.guid,b.ldmc,b.lddm,b.ch,b.qsh,b.shnj,(select bmmc from zxbz_xxbmdm b where b.bmdm = b.shxy ) shxy,b.cws,b.rzrs,b.jcrc,b.fz,b.xn,b.xq,b.jcrq,b.jcbm,b.jcry,b.pfbzname from VIEW_XG_GYGL_NEW_WSJC_QSFSB b");
					}else{
						colList = new String[] { "ldmc", "ch", "qsh", "shnj", "shxy", "cws", "rzrs", "jcrc", "fz", "lddm" ,"jcrq" ,"jcbm" ,"jcry" };
						sql.append(" select distinct b.guid,b.ldmc,b.lddm,b.ch,b.qsh,b.shnj,(select bmmc from zxbz_xxbmdm b where b.bmdm = b.shxy ) shxy,b.cws,b.rzrs,b.jcrc,b.fz,b.xn,b.xq,b.jcrq,b.jcbm,b.jcry from VIEW_XG_GYGL_NEW_WSJC_QSFSB b");
					}
				} else {
					colList = new String[] { "guid", "ldmc", "ch", "qsh", "shnj", "shxy", "cws", "rzrs", "jcrc", "dj", "lddm" ,"jcrq" ,"jcbm" ,"jcry" };
					sql.append(" select distinct b.guid,b.ldmc,b.lddm,b.ch,b.qsh,b.shnj,(select bmmc from zxbz_xxbmdm b where b.bmdm = b.shxy ) shxy,b.cws,b.rzrs,b.jcrc,b.dj,b.xn,b.xq,b.jcrq,b.jcbm,b.jcry from VIEW_XG_GYGL_NEW_WSJC_QSFSB b");
				}
			}
			
			sql.append(" left join (select a.lddm, a.qsh, a.xh, b.bjdm, b.zydm, b.xydm, b.nj from xg_gygl_new_cwxxb a left join view_xsbfxx b on a.xh = b.xh where a.xh is not null) c on b.lddm = c.lddm and b.qsh = c.qsh ");
			sql.append(" where 1=1 ");
			sql.append(searchTjByUser);
			sql.append(" order by lddm,qsh,ch,kssj ) a ");
		}
		
		sql.append(query);
		
		
		// ====================SQL拼装 end================================
		List<HashMap<String, String>> list =  CommonQueryDAO.commonQueryforExportList(sql.toString(), "", inputV, colList,myForm);
		return list;
	}
	
	
	/**
	 * 卫生分查询不及格名单导出
	 * 
	 * @param myForm
	 * @return
	 * @throws IllegalArgumentException, SecurityException, IllegalAccessException,InvocationTargetException, NoSuchMethodException
	 */
	public List<HashMap<String, String>> getbjgmdExportCx(FscxForm myForm,HttpServletRequest request,User user)
			throws IllegalArgumentException, SecurityException,IllegalAccessException, InvocationTargetException,NoSuchMethodException {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		//User user = myForm.getUser();
		String[] colList = new String[] {};
		// 用户属性
		String userType = user.getUserType();
		// ====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "e", "xydm", "bjdm");
		
		//权限控制
		String searchTjQx="";
		
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		}
		
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " ";
		query += searchTj+searchTjQx;
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();
		//通用的sql组装
		sql.append("select t.* ,rownum r from ( select a.xn,a.xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,a.mc,a.kssj,a.jssj,a.bz rcbz,");
		sql.append(" (select ldmc from xg_gygl_new_ldxxb a where a.lddm=b.lddm)ldmc,(select lddm from xg_gygl_new_ldxxb a where a.lddm=b.lddm)lddm, ");
		sql.append(" b.qsh,b.jcrq,b.jcbm,b.jcry,b.fs,b.bz,b.lrr,b.lrsj,b.pfbz,");
		sql.append(" c.xh,d.xm,d.nj,d.xydm,d.xymc,d.zydm,d.zymc,d.bjdm,d.bjmc,c.cwh,c.rzsj ");
		sql.append(" from xg_gygl_new_wsjc_jcrcb a left join xg_gygl_new_wsjc_qsfsb b on a.guid=b.guid");
		sql.append(" left join xg_gygl_new_cwxxb c on b.lddm=c.lddm and b.qsh=c.qsh ");
		sql.append(" left join view_xsjbxx d on c.xh=d.xh ");
		sql.append(" left join (select a.lddm, a.qsh, a.xh, b.bjdm, b.zydm, b.xydm, b.nj ");
		sql.append("from xg_gygl_new_cwxxb a left join view_xsbfxx b on a.xh = b.xh ");
		sql.append("where a.xh is not null) e on b.lddm=e.lddm and b.qsh=e.qsh and d.xh=e.xh ");
		sql.append("where b.fs < 60 and b.fs is not null and c.xh is not null )t where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(query);
		
		
		// ====================SQL拼装 end================================
		List<HashMap<String, String>> list =  CommonQueryDAO.commonQueryforExportList(sql.toString(), "", inputV, colList,myForm);
		return list;
	}
	
	/**
	 * 
	 * 卫生检查日程列表  未提交的数据
	 * @作者：易江东[工号：781]
	 * @日期：2013-8-30 下午05:43:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJcrcListByWtj(FscxForm myForm,HttpServletRequest request,User user)
			throws Exception {
		DAO dao=new DAO();
		
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		//User user = myForm.getUser();
		String[] colList = new String[] {"guid","ldmc","lddm","ch","qsh","shnj","shxy","cws","rzrs","jcrc","fz","dj","xn","xq","tjzt"};
		// 用户属性
		String userType = user.getUserType();
		// ====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		
		//权限控制
		String searchTjQx="";
		
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		}
		
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where (a.tjzt != '1' or a.tjzt is null)  ";
		query += searchTj+searchTjQx;
		
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();
		
		sql.append("  select * from (select b.guid,b.ldmc,b.lddm, b.ch, b.qsh,b.shnj,(select bmmc from zxbz_xxbmdm b where b.bmdm = b.shxy) shxy, b.cws,b.rzrs,b.jcrc, b.fz, b.dj,b.xn,b.xq,c.tjzt ");
		sql.append(" from VIEW_XG_GYGL_NEW_WSJC_QSFSB b left join xg_gygl_new_wsjc_jcrcb c  on b.guid=c.guid ) a ");
		
		//拼装高级查询
		sql.append(query);
		// ====================SQL拼装 end================================
		List<HashMap<String, String>> list =  dao.getList(sql.toString(), inputV, colList);
		return list;
	}
	
	/**
	 * 
	 * @描述:寝室等级批量修改
	 * @作者：易江东[工号：781]
	 * @日期：2013-8-29 下午01:59:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dataList
	 * @param qsfszqList 寝室分周期列表
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean qsdjPlXg(List<String[]> dataList,List<String[]> qsfszqList)throws Exception {
		if(dataList == null || dataList.size() == 0){
			return false;
		}
		boolean flag = false;
		DAO dao = DAO.getInstance();
		
		StringBuffer sql = null;
//		StringBuffer where=new StringBuffer();
		String pk="";
		
//		sql.append("update xg_gygl_new_wsjc_qsfsb set  ");
//		sql.append(" dj = case  ");
//		
//		//此处使用sql凭借，该业务数据内容数据处理不存在sql注入
//		for (int i = 0; i < dataList.size(); i++) {
//			for (int j = 1; j < dataList.get(i).length; j++){
//				//获取pk
//				pk=qsfszqList.get(j-1)[1]+dataList.get(i)[0];
//				
//				sql.append(" when guid||lddm||qsh = '");
//				sql.append(pk);
//				sql.append("' then '");
//				sql.append(dataList.get(i)[j]);
//				sql.append("' ");
//				
//				if("".equals(where.toString())){
//					where.append(" where ");
//				}else{
//					where.append(" or ");
//				}
//				where.append(" guid||lddm||qsh = '");
//				where.append(pk);
//				where.append("' ");
//				
//				
//			}
//		}
//		sql.append(" end ");
//		sql.append(where.toString());
		
		//批量执行数据    若出现内存益处情况  请适当的条件 提交数，但是这样会牺牲时间
		int commitNum=5000;
		//执行sql集合
		String[] sqls=new String[commitNum];
		//当前sql条数  当前sql条数从0开始
		int currNum=0;
		//运行总次数  总次数是从0开始
		int sumNum=0;
		//总执行次数  最终修改的数据总数
		int countNum=dataList.size() * (dataList.get(0).length -1);
		//返回结果
		int[] results = null;
		
		//此处使用sql凭借，该业务数据内容数据处理不存在sql注入
		for (int i = 0; i < dataList.size(); i++) {
			for (int j = 1; j < dataList.get(i).length; j++){
				//获取pk
				pk=qsfszqList.get(j-1)[1]+dataList.get(i)[0];
				sumNum=(i*(dataList.get(i).length-1))+(j-1);
				currNum=sumNum % commitNum;
				
				//设置新的数据集
				if(currNum == 0){
					sqls=new String[commitNum];
				}
				
				sql = new StringBuffer();
				sql.append(" update xg_gygl_new_wsjc_qsfsb set ");
				sql.append(" dj='");
				sql.append(dataList.get(i)[j]);
				sql.append("' where guid||lddm||qsh ='");
				sql.append(pk);
				sql.append("' ");
				
				sqls[currNum]=sql.toString();
				//sql集合已满  或者  最后一次
				if(((currNum+1 )== commitNum) || ((sumNum+1) == countNum)){
					//System.out.println("批量更新数据---------------------------");
					results = dao.runBatch(sqls);
					
					//线程休眠
					Thread.sleep(100);
				}
			}
		}
		
//		flag = dao.runUpdate(sql.toString(),new String[]{});
		return dao.checkBatch(results);
	}
	
	/**
	 * 
	 * @描述: 卫生分评分查询
	 * @作者：易江东[工号：781]
	 * @日期：2013-8-29 下午02:02:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @param user
	 * @param qsfszqList 寝室分周期列表
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<String[]> getFscxPfCx(FscxForm myForm,HttpServletRequest request,User user,
			List<String[]> qsfszqList)
			throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		//User user = myForm.getUser();
		String[] colList = new String[] {};
		// 用户属性
		String userType = user.getUserType();
		// ====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		
		//权限控制
		String searchTjQx="";
		
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		}
		
		// 高级查询输入值
		//String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String[] inputV = new String[]{};
		String query = " where 1 = 1 ";
		//query += searchTj+searchTjQx;
		//此处不获取所有的高级查询条件，只获取本模块关心的值
		query += getZzQsfscxTjSql(searchModel, new String[]{"xn"});
		
		//若没有周期分数数据  ，
		if(qsfszqList == null || qsfszqList.size() == 0){
			return null;
		}
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();
		
		//输出列名
		colList = new String[] {"guid"};
		//临时列名
		String[] tempStr=new String[qsfszqList.size()];
		
		
		sql.append(" select a.lddm || a.qsh guid, ");
		
        for (int i = 0; i < qsfszqList.size(); i++) {
        	sql.append(" nvl(sum(decode(a.kssj, '");
        	sql.append(qsfszqList.get(i)[0]);
        	sql.append("', trunc(a.fs), '0')),'0') as week");
        	sql.append(i);
        	sql.append(" ");
        	if(qsfszqList.size() != (i+1)){
        		sql.append(", ");
        	}
        	
        	//加入临时名
        	tempStr[i]="week"+i;
		}
        sql.append(" from (select a.guid, a.qsh, a.fs, b.kssj,a.lddm ");
        sql.append(" from xg_gygl_new_wsjc_qsfsb a ");
        sql.append(" left join xg_gygl_new_wsjc_jcrcb b ");
        sql.append(" on a.guid = b.guid ");
	            
        sql.append(query);
        
	    sql.append(" and  b.tjzt='1') a group by a.lddm || a.qsh "); 
        sql.append(" order by a.lddm || a.qsh asc ");
		
        //合并输出列名
        colList=uniteArrays(colList,tempStr);
		// ====================SQL拼装 end================================
        DAO dao=DAO.getInstance();
		return dao.rsToVator(sql.toString(), inputV, colList);
	}
	
	
	/**
	 * 
	 * @描述: 获取寝室分数周期列表根据学年学期
	 * @作者：易江东[工号：781]
	 * @日期：2013-8-29 下午02:58:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @param user
	 * @return
	 * List<String[]> 返回类型 
	 * @throws
	 */
	public List<String[]> getQsfszqListByXnXq(FscxForm myForm,HttpServletRequest request,User user)
			throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		//User user = myForm.getUser();
		String[] colList = new String[] {};
		// 用户属性
		String userType = user.getUserType();
		// ====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		
		//权限控制
		String searchTjQx="";
		
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		}
		
		// 高级查询输入值
		//String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String[] inputV = new String[]{};
		String query = " where 1=1 ";
		//query += searchTj+searchTjQx;
		//此处不获取所有的高级查询条件，只获取本模块关心的值
		query += getZzQsfscxTjSql(searchModel, new String[]{"xq","xn"});
		
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();
		
		colList = new String[] { "kssj","guid" };
		sql.append(" select distinct a.guid,a.kssj from (select a.guid,b.xn, b.xq, a.lddm, b.kssj from xg_gygl_new_wsjc_qsfsb a  ");
		sql.append(" left join xg_gygl_new_wsjc_jcrcb b on a.guid = b.guid where b.tjzt='1') a  ");
		
		sql.append(query);
		
		sql.append(" order by a.kssj asc ");
		// ====================SQL拼装 end================================
		DAO dao=DAO.getInstance();
		System.out.println(sql.toString());
		System.out.println("----------------------------");
		return dao.rsToVator(sql.toString(), inputV, colList);
	}
	
	/**
	 * 
	 * @描述: 修改学年学期
	 * @作者：易江东[工号：781]
	 * @日期：2013-10-10 下午01:52:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateQsdjByXnxq(FscxForm myForm) throws Exception{
		if(myForm == null){
			return false;
		}
		
		StringBuffer sql=new StringBuffer();
		// ====================SQL拼装================================
		sql.append(" update xg_gygl_new_wsjc_qsfsb a set a.dj=? where exists ");
        sql.append(" (select guid from xg_gygl_new_wsjc_jcrcb b where b.xn=? and b.xq=? and a.guid= b.guid) ");
		
		// ====================SQL拼装end================================
		
		String[] input = new String[]{myForm.getDj(),myForm.getXn(),myForm.getXq()};
		
		DAO dao=DAO.getInstance();
		return  dao.runUpdate(sql.toString(), input);
	}
	
	
	/**
	 * 
	 * @描述: 获取组装寝室分数查询条件
	 * <li>该方法只提供给寝室分数查询功能</li>
	 * @作者：易江东[工号：781]
	 * @日期：2013-8-30 上午08:47:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param searchModel
	 * @param zdmc  String[],例：['xh','xm'],查询字段必须存在与SearchModel
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	private String getZzQsfscxTjSql(SearchModel searchModel,String[] zdmc)
		throws Exception{
		if(zdmc == null || zdmc.length ==0 || searchModel == null){
			return null;
		}
		StringBuffer where=new StringBuffer(" and ");//where 条件
		String[] tjz=null;//条件值
		for (int i = 0; i < zdmc.length; i++) {
			tjz = getQsfscxTj(searchModel, zdmc[i]);
			
			if(tjz != null){
				if(i != 0){
					where.append(" and ");
				}
				where.append(" ( ");
				for (int j = 0; j < tjz.length; j++) {
					if(j != 0){
					   where.append(" or ");
					}
					where.append(zdmc[i]);
					where.append(" ='");
					where.append(tjz[j]);
					where.append("' ");
				}
				where.append(" ) ");
			}
		}
		
		return where.toString();
	}
	
	/**
	 * @描述: 获取请示分数查询条件
	 * @作者：易江东[工号：781]
	 * @日期：2013-8-30 上午08:44:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param zdmc
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	private String[] getQsfscxTj(SearchModel searchModel, String zdmc)
		throws Exception{
		if(StringUtil.isNull(zdmc) || searchModel == null){
			return null;
		}
		//获取查询model
		Class myClass=searchModel.getClass();
		
		String methodName = "getSearch_tj_" + zdmc;

		String[] sT = (String[]) myClass.getMethod(methodName,
				(Class[]) null).invoke(searchModel, (Object[]) null);
		
		return sT;
	}
	
	/**
	 * 合并数组
	 * @param arrays
	 * @return
	 * @author sjf
	 */
	private String[] uniteArrays(String[]...arrays){
		int length = 0;
		for (int i=0; i<arrays.length; i++){
			length += arrays[i].length;
		}
		
		String[] strs = new String[length];
		
		int count = 0;
		for(String[] array : arrays){
			for(int j=0; j<array.length; j++){
				strs[count++] = array[j];
			}
		}
		
		return strs; 
	}
	/**
	 * 
	 * @描述:获取寝室学期卫生分（温大）
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-11-29 上午11:48:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @param user
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
   public List<HashMap<String,String>> getWsfTjList(FscxForm myForm,HttpServletRequest request,User user)
			throws IllegalArgumentException, SecurityException,IllegalAccessException, InvocationTargetException,NoSuchMethodException {
	   DAO dao=DAO.getInstance();
	   // 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户属性
		String userType = user.getUserType();
		// ====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj_xn = searchModel.getSearch_tj_xn()[0];
		String searchTj_xq = searchModel.getSearch_tj_xq()[0];
		String searchTj_lddm =searchModel.getSearch_tj_ld()[0];
		String[] inputV =new String[3];
		inputV[0]=searchTj_xn;
		inputV[1]=searchTj_xq;
		inputV[2]=searchTj_lddm;
		//权限控制
		String searchTjQx="";
		
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		}
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "c", "xydm", "bjdm");
		
		// 高级查询输入值
	//	String[] inputV = SearchService.getTjInput(myForm.getSearchModel().get);
		String query = " where 1 = 1 ";
		query += "and xn=? and xq=? and lddm=?"+searchTjQx;
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.*, " );
		sql.append(" (80+to_number(nvl(a.month3,0))+to_number(nvl(a.month4,0))+to_number(nvl(a.month5,0))+to_number(nvl(a.month6,0))) count1,");
		sql.append(" (80+to_number(nvl(a.month9,0))+to_number(nvl(a.month10,0))+to_number(nvl(a.month11,0))+to_number(nvl(a.month12,0))) count2");
		sql.append(" from (select c.xn,c.xq,c.lddm,c.ldmc,c.qsh,max(decode(c.mc, '3月', c.qsfs)) month3,");
		sql.append("  max(decode(c.mc, '4月', c.qsfs)) month4,max(decode(c.mc, '5月', c.qsfs)) month5, ");
		sql.append("  max(decode(c.mc, '6月', c.qsfs)) month6,max(decode(c.mc, '9月', c.qsfs)) month9,");
		sql.append(" max(decode(c.mc, '10月', c.qsfs)) month10,max(decode(c.mc, '11月', c.qsfs)) month11,");
		sql.append(" max(decode(c.mc, '12月',c.qsfs)) month12 from (select a.*,b.xn,b.xq,b.mc,(select ldmc from xg_gygl_new_ldxxb c where c.lddm = a.lddm) ldmc,");
		sql.append(" (select b.fssx from XG_GYGL_NEW_WSJC_DJFSB b where b.dj=a.dj ) qsfs from xg_gygl_new_wsjc_qsfsb a");
		sql.append(" left join xg_gygl_new_wsjc_jcrcb b on a.guid=b.guid) c");
		sql.append(" where 1=1 "+searchTjByUser+" group by c.xn,c.xq,c.lddm,c.ldmc,c.qsh");
		sql.append(") a ");
		sql.append(query);
		sql.append("");
		// ====================SQL拼装 end================================
		return dao.getListNotOut(sql.toString(), inputV);
		
   }
   
	   /** 
	 * @描述:获取周统计列表数据(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-6 下午04:11:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @param user
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
   public List<HashMap<String,String>> getZTjList(FscxForm myForm,HttpServletRequest request,User user)
		throws IllegalArgumentException, SecurityException,IllegalAccessException, InvocationTargetException,NoSuchMethodException {
		DAO dao=DAO.getInstance();
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户属性
		String userType = user.getUserType();
		// ====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String[] searchTj_lddm = searchModel.getSearch_tj_ld();
		String searchTj_kssj_1 = searchModel.getSearch_tj_kssj()[0];
		String searchTj_kssj_2 = searchModel.getSearch_tj_jssj()[0];
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		
		StringBuilder query = new StringBuilder(); 
		query.append(" where 1 = 1");
		query.append(" and b.jclx = '0' and b.kssj >= ? and b.kssj <= ?");
		if(null != searchTj_lddm && searchTj_lddm.length>0 ){
			query.append(" and a.lddm in (");
			for(int i = 0;i<searchTj_lddm.length;i++){
				query.append("?");
				if(i != searchTj_lddm.length-1){
					query.append(",");
				}
			}
			query.append(")");
		}
		
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();
		sql.append(" select bjmc,bzr,fs from (select xydm,bjdm,xymc,bjmc,bzr,");
		sql.append(" (case when instr(to_char((100-sum(fs))),'.') < 1 then to_char((100-sum(fs))) || '.00' when instr(to_char(100-sum(fs)),'.') + 1 = length(to_char(100-sum(fs))) then to_char((100-sum(fs))) || '0' else to_char(trunc(100-sum(fs),2)) end) fs from");
		sql.append(" (select * from (select a.xydm,a.bjdm,a.xymc,a.bjmc,b.lddm,b.qsh,b.ch,c.fs,c.kssj,d.bzr from view_njxyzybj a" );
		sql.append(" left join (select distinct lddm, qsh,ch,bjdm from (select a.lddm, a.qsh, b.bjdm,c.ch from xg_gygl_new_cwxxb a");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join xg_gygl_new_qsxxb c on a.lddm = c.lddm and a.qsh = c.qsh where a.xh is not null)");
		sql.append(" group by lddm,qsh,ch,bjdm)b on  a.bjdm = b.bjdm");
		sql.append(" left join (select a.lddm, a.qsh, a.fs, b.kssj from xg_gygl_new_wsjc_qsfsb a");
		sql.append(" left join xg_gygl_new_wsjc_jcrcb b");
		sql.append(" on a.guid = b.guid ");
		sql.append(query);
		sql.append(" ) c on b.lddm = c.lddm and b.qsh = c.qsh ");
		sql.append(" left join (select a.bjdm,replace(wm_concat(b.xm),';',',') bzr from bzrbbb a left join fdyxxb b on a.zgh = b.zgh group by bjdm) d on a.bjdm = d.bjdm");
		sql.append(" )a");
		sql.append(" where 1=1"+searchTjByUser);
		sql.append(" ) ");
		
		sql.append(" group by xydm,xymc,bjdm,bjmc,bzr order by xydm,bjdm asc)");
		// ====================SQL拼装 end================================
		return dao.getListNotOut(sql.toString(), StringUtils.joinStrArr(new String[]{searchTj_kssj_1,searchTj_kssj_2},searchTj_lddm));
   	} 
   
   /**
 * @description	： 获取楼栋列表
 * @author 		： 柳俊（1282）
 * @date 		：2017-11-17 上午11:43:09
 * @return
 */
   public List<HashMap<String,String>> getLdList(){
	   String sql = "select lddm,ldmc from XG_GYGL_NEW_LDXXB";
	   return DAO.getInstance().getListNotOut(sql, new String[]{});
   }
   
	 /**
	 * @description	： 获取楼栋月卫生平均分列表数据
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-17 下午02:38:37
	 * @return
	 */
   public List<HashMap<String,String>> getLdYwsfPjfList(FscxForm model){
	   String nd = model.getNd();
	   String yf = model.getYf();
	   String ld = model.getLddm();
	   String ndyf = nd + yf;
	   StringBuilder sb = new StringBuilder();
	   sb.append(" select a.lddm, a.qsh, avg(a.fs) pjf, b.ldmc, c.bjmc");
	   sb.append(" from");
	   sb.append(" (select a.lddm, a.qsh, b.fs");
	   sb.append(" from xg_gygl_new_qsxxb a");
	   sb.append(" left join XG_GYGL_NEW_WSJC_QSFSB b");
	   sb.append(" on a.qsh = b.qsh and a.lddm = b.lddm");
	   sb.append(" left join xg_gygl_new_wsjc_jcrcb c on b.guid = c.guid");
	   sb.append(" where a.lddm = ?");
	   sb.append(" and substr(b.jcrq, 0, 6) = ? and c.tjzt = '1') a");
	   sb.append(" left join xg_gygl_new_ldxxb b");
	   sb.append(" on a.lddm = b.lddm");
	   sb.append(" left join");
	   sb.append(" (select distinct a.lddm, a.qsh, b.bjmc");
	   sb.append(" from xg_gygl_new_cwxxb a");
	   sb.append(" left join view_xsbfxx b on a.xh = b.xh  where a.xh is not null)c");
	   sb.append(" on a.qsh = c.qsh and a.lddm = c.lddm group by a.lddm,a.qsh,b.ldmc,c.bjmc order by to_number(qsh) asc");
	   return DAO.getInstance().getListNotOut(sb.toString(), new String[]{ld,ndyf});
   }
   
   /**
 * @description	： 楼栋月卫生分列表
 * @author 		： 柳俊（1282）
 * @date 		：2017-11-17 下午04:53:36
 * @param model
 * @return
 */
   public List<HashMap<String,String>> getLdYwsfList(FscxForm model){
	   String nd = model.getNd();
	   String yf = model.getYf();
	   String ld = model.getLddm();
	   String ndyf = nd + yf;
	   StringBuilder sb = new StringBuilder();
	   sb.append(" select * from (select a.lddm,a.qsh,a.fs,a.jcrq,b.ldmc,c.bjmc,(case when substr(jcrq,7,1) = '0' then substr(jcrq,8,1) else substr(jcrq,7,2) end) rq,row_number() over(partition by a.lddm,b.ldmc,a.qsh,c.bjmc,a.fs order by a.jcrq asc) rn");
	   sb.append(" from ");
	   sb.append(" (select a.lddm, a.qsh, b.fs, b.jcrq");
	   sb.append(" from xg_gygl_new_qsxxb a");
	   sb.append(" left join XG_GYGL_NEW_WSJC_QSFSB b");
	   sb.append(" on a.qsh = b.qsh and a.lddm = b.lddm");
	   sb.append(" left join xg_gygl_new_wsjc_jcrcb c on b.guid = c.guid");
	   sb.append(" where a.lddm = ?");
	   sb.append(" and substr(b.jcrq, 0, 6) = ? and c.tjzt = '1') a");
	   sb.append(" left join xg_gygl_new_ldxxb b");
	   sb.append(" on a.lddm = b.lddm");
	   sb.append(" left join");
	   sb.append(" (select distinct a.lddm, a.qsh, b.bjmc");
	   sb.append(" from xg_gygl_new_cwxxb a");
	   sb.append(" left join view_xsbfxx b on a.xh = b.xh  where a.xh is not null)c");
	   sb.append(" on a.qsh = c.qsh and a.lddm = c.lddm group by a.lddm,a.qsh,b.ldmc,a.jcrq,a.fs,c.bjmc)");
	   return DAO.getInstance().getListNotOut(sb.toString(), new String[]{ld,ndyf});
   } 
}