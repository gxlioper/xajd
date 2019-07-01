package xsgzgl.pjpy.general.tjcx.tjmdhz;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.zjcm.ZjcmPjpyModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.tjcx.PjpyTjcxDAO;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计分析_获奖金额汇总_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */

public class TjcxTjmdhzDAO extends PjpyTjcxDAO {

	/**
	 * 获取我的评奖本次评奖信息
	 * 
	 * @author qlj
	 */
	public ArrayList<String[]> getTjmdhzList(PjpyGeneralForm myForm,
			TjcxTjmdhzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
//		String[] xn = searchModel.getSearch_tj_xn();
//		if (xn == null || xn.length == 0) {
//			xn = new String[] { jbszForm.getPjxn() };
//		}
//		searchModel.setSearch_tj_xn(xn);
		// ====================过滤条件===================================

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);

		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");

		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(searchModel);

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append(" select rownum r,a.* from(select substr(a.xmmc,5,3)pjdj,a.xn,b.xm,b.xh, ");
		sql.append(" b.nj,b.xydm,b.zydm,b.bjdm,b.bjmc||'('||c.bjrs||'人)' bjrs,nvl(zd2,0) dycj, nvl(zd1,0) zcf,zcfbjpm ,round(pjcj,2)pjcj,zdtyk ");
		sql.append(" from (select a.xn,a.xh,a.xmmc from  xg_pjpy_pjlsxxb a  ");
		sql.append(" union (select a.pjxn,a.xh,b.xmmc from xg_pjpy_pjxmsqb a, ");
		sql.append(" xg_pjpy_pjxmwhb b where a.xmdm=b.xmdm and a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd and ( a.sqjg='tg' or a.sqjg='wxsh')))a ");
		sql.append(" left join xg_view_pjpy_pjryk b on a.xh = b.xh ");
		sql.append(" left join (select count(1)bjrs,bjdm from xg_view_pjpy_pjryk group by bjdm)c on b.bjdm=c.bjdm ");
		sql.append(" left join xg_pjpy_zhcpb d on a.xh=d.xh and a.xn=d.xn  ");
		sql.append(" left join (select xh,xn,avg(cj)pjcj from view_zhhcjb where (kcxz like '%必修课%' or kcxz like '%选修课%') ");
		sql.append(" and kcmc not like '%体育%' group by xh,xn) e on a.xh=e.xh and a.xn=e.xn  ");
		sql.append(" left join (select xh,xn,min(cj)zdtyk from view_zhhcjb where  kcmc  like '%体育%'  and regexp_like(cj,'^\\d{1,10}\\.*\\d{0,10}$')  group by xh,xn) f on a.xh=f.xh and a.xn=f.xn  ");
		sql.append(" where xmmc like '%学院学生一等奖学金%' ");
		sql.append(" or xmmc like '%学院学生二等奖学金%' ");
		sql.append(" or xmmc like '%学院学生三等奖学金%'  ");
		sql.append(" )a ");

		sql.append(query);

		String[] colList = { "r","pjdj", "xm", "xh", "nj", "bjrs", "dycj", "zcf",
				"zcfbjpm", "pjcj", "zdtyk" };
		// ====================SQL拼装 end================================
		ArrayList<String[]> list =new ArrayList<String[]>();
		
		if("exp".equalsIgnoreCase(model.getType())){
			list=(ArrayList<String[]>) CommonQueryDAO
			.commonQueryNotFy(sql.toString(), "", inputV, colList, myForm);
		}else{
			list=(ArrayList<String[]>) CommonQueryDAO
					.commonQuery(sql.toString(), "", inputV, colList, myForm);
		}

		return list;
	}


	/**
	 * 获得指定学年学期的奖学金金额统计列表
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getJxjjeList(String xn, String[] xmmc,
			String[] nj, String[] xydm, String[] zydm, String[] bjdm) {

		DAO dao = DAO.getInstance();
		
		ArrayList<String>colList=new ArrayList<String>();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		
		List<HashMap<String,String>>xmxzList=getXmxzList();
		
		List<HashMap<String,String>>xmmcList=getXmmcBylx("02", xn);
		StringBuffer sql = new StringBuffer();
		StringBuilder maxSql=new StringBuilder();
		StringBuilder caseSql = new StringBuilder();
		StringBuilder searchZd = new StringBuilder();
		

		for(int i=0;i<xmxzList.size();i++){
			HashMap<String,String>xmxzMap=xmxzList.get(i);
			caseSql.append(" ,case when xmxz = '"+xmxzMap.get("xzdm")+"' and xmlx='01' then  xmmc else  ''  end jxjmc_"+i+" ");
			maxSql.append(",max(jxjmc_"+i+")jxjmc_"+i+" ");
			searchZd.append(",a.jxjmc_"+i);
			colList.add("jxjmc_"+i);
		}
		
		for(int i=0;i<xmmcList.size();i++){
			HashMap<String,String>xmmcMap=xmmcList.get(i);
			caseSql.append(" ,case when xmmc = '"+xmmcMap.get("xmmc")+"' and xmlx='02' then  xmmc else  ''  end rychmc_"+i+" ");
			maxSql.append(",max(rychmc_"+i+")rychmc_"+i+" ");
			searchZd.append(",a.rychmc_"+i);
			colList.add("rychmc_"+i);
		}
		

		sql.append("select a.xh,a.xm,a.xymc,a.zymc"+searchZd+",a.je, ");
		sql.append("rank() over(partition by a.xydm order by a.xh desc nulls last) num, ");
		sql.append("b.yhkh,a.xydm||'luojw'||c.zs||'luojw'||nvl(c.zje,0) zj from ");
		sql.append("(select a.xh,b.xm,b.nj,b.xydm,b.xymc, ");
		sql.append("b.zydm,b.zymc,b.bjdm,b.bjmc,a.je ");
		sql.append(searchZd);
		sql.append(" from (  ");

		sql.append(" select xn,xq,xh"+maxSql+",sum(xmje) je ");
		sql.append(" from (select xn,xq,xmlx,xmmc,xh,xmje,hdsj,bz ");
		sql.append(caseSql);
		sql.append(" from xg_pjpy_pjlsxxb a) group by xn, xq, xh ");
		
		sql.append(" ) a,xg_view_pjpy_pjryk b ");
		sql.append("where a.xh = b.xh ");
		//sql.append("and a.xmlx = '01' ");
		sql.append("and a.xn=? ");
		//项目名称
		if (xmmc != null && xmmc.length > 0) {
			sql.append("and a.xmmc in ( ");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		//sql.append("and a.xmmc=? ");
		
		// 年级
		if (nj != null && nj.length > 0) {
			sql.append("and nj in ( ");
			for (int i = 0; i < nj.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(nj[i]);
			}
			sql.append(")");
		}

		// 学院
		if (xydm != null && xydm.length > 0) {
			sql.append("and b.xydm in ( ");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xydm[i]);
			}
			sql.append(")");
		}

		// 专业
		if (zydm != null && zydm.length > 0) {
			sql.append("and b.zydm in ( ");
			for (int i = 0; i < zydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(zydm[i]);
			}
			sql.append(")");
		}

		// 班级
		if (bjdm != null && bjdm.length > 0) {
			sql.append("and b.bjdm in ( ");
			for (int i = 0; i < bjdm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(bjdm[i]);
			}
			sql.append(")");
		}
		
		

		params.add(xn);
		
		
		
		sql.append(") a left join xsxxb b on a.xh = b.xh ");
		sql.append("left join (select xydm,count(1) zs,sum(a.xmje) zje ");
		sql.append("from xg_pjpy_pjlsxxb a, xg_view_pjpy_pjryk b ");
		sql.append("where a.xh = b.xh ");
		sql.append("and a.xn = ? ");
		//sql.append("and a.xmmc = ? ");
		//项目名称
		if (xmmc != null && xmmc.length > 0) {
			sql.append("and a.xmmc in ( ");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		// 年级
		if (nj != null && nj.length > 0) {
			sql.append("and nj in ( ");
			for (int i = 0; i < nj.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(nj[i]);
			}
			sql.append(")");
		}

		// 学院
		if (xydm != null && xydm.length > 0) {
			sql.append("and b.xydm in ( ");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xydm[i]);
			}
			sql.append(")");
		}

		// 专业
		if (zydm != null && zydm.length > 0) {
			sql.append("and b.zydm in ( ");
			for (int i = 0; i < zydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(zydm[i]);
			}
			sql.append(")");
		}

		// 班级
		if (bjdm != null && bjdm.length > 0) {
			sql.append("and b.bjdm in ( ");
			for (int i = 0; i < bjdm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(bjdm[i]);
			}
			sql.append(")");
		}
		sql.append("group by xydm) c on a.xydm = c.xydm");
		
		String[] colArr=new String[] { "xh", "xm", "xymc",
				"zymc",  "je", "yhkh", "num", "zj" };
		
		colArr=dao.unionArray(colArr,colList.toArray(new String[]{}));
		
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(), params
				.toArray(new String[] {}), colArr);

		return list;
	}
	
	/**
	 * 获取项目性质列表
	 * @return
	 */
	public List<HashMap<String,String>>getXmxzList(){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append("   select xzdm,xzmc from xg_pjpy_xmxzb a where exists(select 1 from xg_pjpy_pjxmwhb b where a.xzdm=b.xmxz and b.xmlx<>'02') ");
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xzdm","xzmc"});
	}
	
	/**
	 * 根据项目类型获取项目名称
	 * @return
	 */
	public List<HashMap<String,String>>getXmmcBylx(String xmlx,String xn){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select distinct(xmmc)xmmc from xg_pjpy_pjlsxxb where xmlx=? and xn=? ");
		
		return dao.getList(sql.toString(), new String[]{xmlx,xn}, new String[]{"xmmc"});
	}
}
