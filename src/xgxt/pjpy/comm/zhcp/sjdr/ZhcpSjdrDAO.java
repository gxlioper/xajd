package xgxt.pjpy.comm.zhcp.sjdr;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mortbay.html.Page;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommDAO;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Pages;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 综合素质测评_数据导入_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class ZhcpSjdrDAO extends PjpyCommDAO {

	/**
	 * 获得品德互评列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getPdhpInfoList(ZhcpSjdrForm model, User user,
			List<HashMap<String, String>> xmList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		//综测周期
		String zczq=model.getZczq();
		
		ArrayList<String> colList = new ArrayList<String>();
		colList.add("pk");
		colList.add("pjsj");
		colList.add("bjmc");
		colList.add("pfr");
		colList.add("bpfr");
		
		StringBuilder tableSql = new StringBuilder();
		tableSql.append("(");
		tableSql.append("select a.xn||a.xq||a.nd||a.pfr||a.bpfr pk,a.xn, a.xq, a.nd,");
		if("xn".equalsIgnoreCase(zczq)){//学年
			tableSql.append("a.xn||'学年' pjsj,");
		}else if("xq".equalsIgnoreCase(zczq)){//学期
			tableSql.append("a.xn||'学年'||(select d.xqmc from xqdzb d where d.xqdm = a.xq) pjsj,");
		}else{//年度
			tableSql.append("a.nd||'年度' pjsj,");
		}
		
		tableSql.append("b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm, ");
		tableSql.append("b.bjmc, a.pfr pfrxh,b.xm pfrxm,a.bpfr bpfrxh,c.xm bpfrxm, ");
		tableSql.append("b.xm||'('||a.pfr||')' pfr,c.xm||'('||a.bpfr||')' bpfr, ");
		tableSql.append("a.sftj,a.jsqr,a.sfdr ");
		if (xmList != null && xmList.size() > 0) {
			for (int i = 0; i < xmList.size(); i++) {
				HashMap<String, String> xmInfo = xmList.get(i);
				tableSql.append("," + xmInfo.get("xmdm")+" ");
				colList.add(xmInfo.get("xmdm"));
			}
		}
		tableSql.append("from xg_pjpy_pdbxhpb a ");
		tableSql.append("left join view_xsjbxx b on a.pfr = b.xh ");
		tableSql.append("left join view_xsjbxx c on a.bpfr = c.xh ");
		tableSql.append(")");
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		
		return getRsArrList(tableSql.toString(), query, inputV, colList.toArray(new String[]{}), "", model);
	}
	
	/**
	 * 获得综合分维护列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getZhfwhList(ZhcpSjdrForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		// 综测周期
		String zczq = model.getZczq();
		// 操作项目
		String czxm = model.getCzxm();
		// 项目名称
		String xmmc = model.getXmmc();
		
		ArrayList<String> colList = new ArrayList<String>();
		colList.add("pk");
		colList.add("pjsj");
		colList.add("xh");
		colList.add("xm");
		//colList.add("xb");
		colList.add("nj");
		colList.add("bjmc");
		colList.add("xmdm");
		colList.add("xmmc");
		
		StringBuilder tableSql = new StringBuilder();
		tableSql.append("(");
		tableSql.append("select a.xn||a.xq||a.nd||a.xh pk,a.xn,a.xq,a.nd,");
		if("xn".equalsIgnoreCase(zczq)){//学年
			tableSql.append("a.xn||'学年' pjsj,");
		}else if("xq".equalsIgnoreCase(zczq)){//学期
			tableSql.append("a.xn||'学年'||(select d.xqmc from xqdzb d where d.xqdm = a.xq) pjsj,");
		}else{//年度
			tableSql.append("a.nd||'年度' pjsj,");
		}
		tableSql.append("a.xh,b.xm,");
		tableSql.append("b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,");
		tableSql.append(czxm);
		tableSql.append(" xmdm,");
		tableSql.append("'"+xmmc+"' ");
		tableSql.append(" xmmc,");
		tableSql.append("b.bjdm, b.bjmc from xg_pjpy_zhcpb a ");
		tableSql.append("left join ");
		
		if("ss".equalsIgnoreCase(model.getRyk())){
			tableSql.append(" view_xsjbxx ");
		}else{
			tableSql.append(model.getPjry_sql());
		}
		
		tableSql.append(" b on a.xh = b.xh ");
		tableSql.append(")");
		
		SearchService searchService = new SearchService();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		CommService commService = new CommService();
		ArrayList<String> inputList = new ArrayList<String>();

		if(inputV!=null && inputV.length>0){
			for(int i=0;i<inputV.length;i++){
				inputList.add(commService.unicode2Gbk(inputV[i]));
			}
		}
		
		// 权限控制
		HashMap<String, String> notCtrlStatus = new HashMap<String, String>();
		notCtrlStatus.put("gygly", "yes");
		user.setNotCtrlStatus(notCtrlStatus);
		String searchTjByUser = searchService.getSearchTjByUser("a", user);

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		query += " order by bjdm,xh ";
		
		return getRsArrList(tableSql.toString(), query, inputList
				.toArray(new String[] {}), colList.toArray(new String[] {}),
				"", model);
	}
	
	/**
	 * 保存综合素质相关分数
	 * 
	 * @author 伟大的骆
	 * @throws Exception 
	 */
	public Boolean saveZhcpfXgInfo(ZhcpSjdrForm model, User user)
			throws Exception {

		boolean flag = true;

		CommService commService = new CommService();

		//操作项目
		String czxm = model.getCzxm();
		//主键
		String[] pk = model.getPk();
		//分数
		String[] fs = model.getFs();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_pjpy_zhcpb ");
		sql.append("set ");
		sql.append(czxm);
		sql.append("= ");
		sql.append("? ");
		sql.append("where xn||xq||nd||xh = ? ");

		List<String[]> params = new ArrayList<String[]>();
		String tableName = "xg_pjpy_zhcpb";

		if (fs != null && fs.length > 0) {
			
			for (int i = 0; i < fs.length; i++) {
				
				String[] value = new String[] { fs[i],
						commService.unicode2Gbk(pk[i]) };
				
				params.add(value);
			}

			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;

	}
	
	/**
	 * 同步综合素质相关分数
	 * 
	 * @author 伟大的骆
	 */
	public Boolean tbZhcpfXgInfo(ZhcpSjdrForm model, User user){

		boolean flag = true;

		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		
		// 综测周期
		String zczq = jbszModel.getZczq();
		// 来源表
		String lyb = model.getLyb();
		// 来源表
		String czxm = model.getCzxm();
		// 学年
		String xn = jbszModel.getPjxn();
		// 学期
		String xq = jbszModel.getPjxq();
		// 年度
		String nd = jbszModel.getPjnd();
			
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_pjpy_zhcpb a ");
		sql.append("set ");
		sql.append(czxm);
		sql.append("= ");
		sql.append("(select b.fs from ");
		sql.append(lyb);
		sql.append(" b where 1 = 1 ");
		// 综测周期：学年
		sql.append("xn".equalsIgnoreCase(zczq) ? " and b.xn = '" + xn + "'" : "");
		sql.append("xq".equalsIgnoreCase(zczq) ? " and b.xn = '" + xn + "' and b.xq = '" + xq + "'" : "");
		sql.append("nd".equalsIgnoreCase(zczq) ? " and b.nd = '" + nd + "'" : "");
		sql.append(" and a.xh = b.xh ) where 1 = 1 ");
		// 综测周期：学年
		sql.append("xn".equalsIgnoreCase(zczq) ? " and a.xn = '" + xn + "'" : "");
		sql.append("xq".equalsIgnoreCase(zczq) ? " and a.xn = '" + xn + "' and a.xq = '" + xq + "'" : "");
		sql.append("nd".equalsIgnoreCase(zczq) ? " and a.nd = '" + nd + "'" : "");
		
		DAO dao = DAO.getInstance();
		
		try {
			flag = dao.runUpdate(sql.toString(), new String[]{});
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			flag = false;
		}
		
		return flag;
	}
	
	/**
	 * 获得需导出部门信息
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getExpBmInfo(ZhcpSjdrForm model) {

		// 查询model
		SearchModel searchModel = model.getSearchModel();
		// 年级
		String[] nj = searchModel.getSearch_tj_nj();
		// 学院
		String[] xy = searchModel.getSearch_tj_xy();
		// 专业
		String[] zy = searchModel.getSearch_tj_zy();
		// 班级
		String[] bj = searchModel.getSearch_tj_bj();
		// 导出形式
		String dcxs = model.getDcxs();

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct");
		sql.append("xy".equalsIgnoreCase(dcxs) ? " xydm " : " bjdm ");
		sql.append("bmdm,");
		sql.append("xy".equalsIgnoreCase(dcxs) ? " xymc " : " bjmc ");
		sql.append("bmmc ");
		sql.append("from view_njxyzybj ");
		sql.append("where 1 = 1 ");

		// 年级条件
		if (nj != null && nj.length > 0) {
			sql.append("and( ");
			for (int i = 0; i < nj.length; i++) {
				if (i != 0) {
					sql.append("or ");
				}
				sql.append("nj = '" + nj[i] + "' ");
			}
			sql.append(") ");
		}

		// 学院条件
		if (xy != null && xy.length > 0) {
			sql.append("and( ");
			for (int i = 0; i < xy.length; i++) {
				if (i != 0) {
					sql.append("or ");
				}
				sql.append("xydm = '" + xy[i] + "' ");
			}
			sql.append(") ");
		}

		// 专业条件
		if (zy != null && nj.length > 0) {
			sql.append("and( ");
			for (int i = 0; i < zy.length; i++) {
				if (i != 0) {
					sql.append("or ");
				}
				sql.append("zydm = '" + zy[i] + "' ");
			}
			sql.append(") ");
		}

		// 班级条件
		if (bj != null && bj.length > 0) {
			sql.append("and( ");
			for (int i = 0; i < bj.length; i++) {
				if (i != 0) {
					sql.append("or ");
				}
				sql.append("bjdm = '" + bj[i] + "' ");
			}
			sql.append(") ");
		}

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "bmdm", "bmmc" });

		return list;
	}
	
	/**
	 * 获得综合分学生列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<String[]> getZhfxsList(ZhcpSjdrForm model, User user)
			throws Exception {
		
		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		// 综测周期
		String zczq = model.getZczq();
		// 学年
		String xn = jbszModel.getPjxn();
		// 学期
		String xq = jbszModel.getPjxq();
		// 年度
		String nd = jbszModel.getPjnd();
		// 学院代码
		String xydm = model.getXydm();
		// 班级代码
		String bjdm = model.getBjdm();
		
		ArrayList<String> colList = new ArrayList<String>();
		
		StringBuilder tableSql = new StringBuilder();
		tableSql.append("(");
		tableSql.append("select ");
		if("xn".equalsIgnoreCase(zczq)){//学年
			tableSql.append("'" + xn + "' xn,");
			colList.add("xn");
		}else if("xq".equalsIgnoreCase(zczq)){//学期
			tableSql.append("'" + xn + "' xn,");
			tableSql.append("(select b.xqmc from xqdzb b where b.xqdm = '"+xq+"') xq,");
			colList.add("xn");
			colList.add("xq");
		}else{//年度
			tableSql.append("'" + nd + "' nd,");
			colList.add("nd");
		}
		tableSql.append("a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,");
		tableSql.append("a.xydm,a.zydm,a.bjdm,");
		tableSql.append("a.bjmc,'' zcxm from  ");
		if("ss".equalsIgnoreCase(model.getRyk())){
			
			tableSql.append(" view_xsjbxx ");
		}else{
			
			tableSql.append(model.getPjry_sql());
		}
		tableSql.append(" a ");
		tableSql.append(")");
		
		colList.add("xh");
		colList.add("xm");
		colList.add("xb");
		colList.add("nj");
		colList.add("xymc");
		colList.add("zymc");
		colList.add("bjmc");
		colList.add("zcxm");
		
		String query = " where 1 = 1 ";
		query += Base.isNull(xydm) ? "" : "and xydm = '" + xydm + "'";
		query += Base.isNull(bjdm) ? "" : "and bjdm = '" + bjdm + "'";

		return CommonQueryDAO.commonQueryNotFy(tableSql.toString(), query,
				new String[] {}, colList.toArray(new String[] {}), "", model);
	}
	
	/**
	 * 保存模版生成记录
	 * @param fileList
	 * @return
	 * @throws Exception 
	 */
	public boolean saveStencilLog(List<String[]> fileList) throws Exception{
		
		DAO dao = DAO.getInstance();
		StringBuilder delSQL = new StringBuilder();
		delSQL.append("delete from pjpy_zhcp_mmccb where ");
		for (int i = 0 ; i < fileList.size() ; i++){
			delSQL.append("wjmc = '")
			      .append(fileList.get(i)[0])
			      .append("'");
			if (i != fileList.size()-1){
				delSQL.append(" or ");
			}
		}
		
		boolean flg = dao.runUpdate(delSQL.toString(), new String[]{});
		
		if (flg) {
		
			String sql = "insert into pjpy_zhcp_mmccb (wjmc,bmdm,bmlx,cjr) values(?,?,?,?)";
			try {
				int[] result = dao.runBatch(sql, fileList);
				return dao.checkBatchResult(result);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
