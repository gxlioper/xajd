package xsgzgl.qgzx.tjcx;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

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
 * Time:2012-7-23 下午14:19:22
 * </p>
 */

public class QgzxTjcxDAO extends CommDAO{

	/**
	 * 查询月度酬金发放统计首页面数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getYdcjfftjCx(QgzxTjcxForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		String[] colList = new String[] { "bmmc", "yf", "qgrs", "zcj" };
		// 用户属性
		// ====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append(" select bmmc, yf, qgrs, zcj, rownum r from VIEW_XG_QGZX_CJTJCX_YDCJFFTJ ");
		sql.append(query);
		sql.append(" order by bmmc,yf ");
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQueryNotFy(sql.toString(), "", inputV, colList, myForm);
		return list;
	}

	/**
	 * 查询年度酬金发放统计首页面数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getNdcjfftjCx(QgzxTjcxForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		String[] colList = new String[] { "yrdw", "agws", "arc", "aje", "bgws", "brc", "bje","cgws", "crc", "cje","dgws", "drc", "dje","egws", "erc", "eje","fgws", "frc", "fje","ggws", "grc", "gje","hgws", "hrc", "hje","igws", "irc", "ije","jgws", "jrc", "jje","kgws", "krc", "kje","lgws", "lrc", "lje","mgws", "mrc", "mje" };
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* ");
		sql.append(" from (select (select bmmc from zxbz_xxbmdm where bmdm=yrdw) yrdw,sum(aje) aje,sum(agws) agws,sum(arc) arc,sum(bje) bje,sum(bgws) bgws,sum(brc) brc,sum(cje) cje,sum(cgws) cgws,sum(crc) crc,sum(dje) dje,sum(dgws) dgws,sum(drc) drc,sum(eje) eje,sum(egws) egws,sum(erc) erc,sum(fje) fje,sum(fgws) fgws,sum(frc) frc,sum(gje) gje,sum(ggws) ggws,sum(grc) grc,sum(hje) hje,sum(hgws) hgws,sum(hrc) hrc,sum(ije) ije,sum(igws) igws,sum(irc) irc,sum(jje) jje,sum(jgws) jgws,sum(jrc) jrc,sum(kje) kje,sum(kgws) kgws,sum(krc) krc,sum(lje) lje,sum(lgws) lgws,sum(lrc) lrc,sum(mje) mje,sum(mgws) mgws,sum(mrc) mrc");
		sql.append(" from (select yrdw,");
		sql.append(" case when ffyf='01' then zje else 0 end as aje,case when ffyf='01' then zgws else 0 end as agws,case when ffyf='01' then zrc else 0 end as arc,case when ffyf='02' then zje else 0 end as bje,case when ffyf='02' then zgws else 0 end as bgws,case when ffyf='02' then zrc else 0 end as brc,case when ffyf='03' then zje else 0 end as cje,case when ffyf='03' then zgws else 0 end as cgws,");
		sql.append(" case when ffyf='03' then zrc else 0 end as crc,case when ffyf='04' then zje else 0 end as dje,case when ffyf='04' then zgws else 0 end as dgws,case when ffyf='04' then zrc else 0 end as drc,case when ffyf='05' then zje else 0 end as eje,case when ffyf='05' then zgws else 0 end as egws,");
		sql.append(" case when ffyf='05' then zrc else 0 end as erc,case when ffyf='06' then zje else 0 end as fje,case when ffyf='06' then zgws else 0 end as fgws,case when ffyf='06' then zrc else 0 end as frc,");
		sql.append(" case when ffyf='07' then zje else 0 end as gje,case when ffyf='07' then zgws else 0 end as ggws,case when ffyf='07' then zrc else 0 end as grc,case when ffyf='08' then zje else 0 end as hje,case when ffyf='08' then zgws else 0 end as hgws,case when ffyf='08' then zrc else 0 end as hrc,case when ffyf='09' then zje else 0 end as ije,case when ffyf='09' then zgws else 0 end as igws,case when ffyf='09' then zrc else 0 end as irc,");
		sql.append(" case when ffyf='10' then zje else 0 end as jje,case when ffyf='10' then zgws else 0 end as jgws,case when ffyf='10' then zrc else 0 end as jrc,case when ffyf='11' then zje else 0 end as kje,case when ffyf='11' then zgws else 0 end as kgws,case when ffyf='11' then zrc else 0 end as krc,case when ffyf='12' then zje else 0 end as lje,case when ffyf='12' then zgws else 0 end as lgws,case when ffyf='12' then zrc else 0 end as lrc,case when ffyf='hj' then zje else 0 end as mje,case when ffyf='hj' then zgws else 0 end as mgws,case when ffyf='hj' then zrc else 0 end as mrc"); 
		sql.append(" from ");
		sql.append(" (select yrdw,case when ffyf is null then 'hj' else ffyf end as ffyf, zje,zgws,zrc from ");
		sql.append(" (select yrdw,substr(yf,6,6) ffyf,sum(je) zje,sum(gws) zgws,sum(rc) zrc from VIEW_XG_QGZX_CJTJCX_NDCJFFTJ ");
		sql.append(query);
		sql.append(" group by rollup(yrdw,substr(yf,6,6)))) where yrdw is not null) group by yrdw) a ");
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQueryNotFy(sql.toString(), "", inputV, colList, myForm);
		return list;
	}

	/**
	 * 查询年度酬金发放统计首页面数据合计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getNdcjfftjCxAll(QgzxTjcxForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		String[] colList = new String[] { "agws", "arc", "aje", "bgws", "brc", "bje","cgws", "crc", "cje","dgws", "drc", "dje","egws", "erc", "eje","fgws", "frc", "fje","ggws", "grc", "gje","hgws", "hrc", "hje","igws", "irc", "ije","jgws", "jrc", "jje","kgws", "krc", "kje","lgws", "lrc", "lje","mgws", "mrc", "mje" };
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append(" select sum(aje) aje,sum(agws) agws,sum(arc) arc,sum(bje) bje,sum(bgws) bgws,sum(brc) brc,sum(cje) cje,sum(cgws) cgws,sum(crc) crc,sum(dje) dje,sum(dgws) dgws,sum(drc) drc,sum(eje) eje,sum(egws) egws,sum(erc) erc,sum(fje) fje,sum(fgws) fgws,sum(frc) frc,sum(gje) gje,sum(ggws) ggws,sum(grc) grc,sum(hje) hje,sum(hgws) hgws,sum(hrc) hrc,sum(ije) ije,sum(igws) igws,sum(irc) irc,sum(jje) jje,sum(jgws) jgws,sum(jrc) jrc,sum(kje) kje,sum(kgws) kgws,sum(krc) krc,sum(lje) lje,sum(lgws) lgws,sum(lrc) lrc,sum(mje) mje,sum(mgws) mgws,sum(mrc) mrc ");
		sql.append(" from (select sum(aje) aje,sum(agws) agws,sum(arc) arc,sum(bje) bje,sum(bgws) bgws,sum(brc) brc,sum(cje) cje,sum(cgws) cgws,sum(crc) crc,sum(dje) dje,sum(dgws) dgws,sum(drc) drc,sum(eje) eje,sum(egws) egws,sum(erc) erc,sum(fje) fje,sum(fgws) fgws,sum(frc) frc,sum(gje) gje,sum(ggws) ggws,sum(grc) grc,sum(hje) hje,sum(hgws) hgws,sum(hrc) hrc,sum(ije) ije,sum(igws) igws,sum(irc) irc,sum(jje) jje,sum(jgws) jgws,sum(jrc) jrc,sum(kje) kje,sum(kgws) kgws,sum(krc) krc,sum(lje) lje,sum(lgws) lgws,sum(lrc) lrc,sum(mje) mje,sum(mgws) mgws,sum(mrc) mrc");
		sql.append(" from (select ");
		sql.append(" case when ffyf='01' then zje else 0 end as aje,case when ffyf='01' then zgws else 0 end as agws,case when ffyf='01' then zrc else 0 end as arc,case when ffyf='02' then zje else 0 end as bje,case when ffyf='02' then zgws else 0 end as bgws,case when ffyf='02' then zrc else 0 end as brc,case when ffyf='03' then zje else 0 end as cje,case when ffyf='03' then zgws else 0 end as cgws,");
		sql.append(" case when ffyf='03' then zrc else 0 end as crc,case when ffyf='04' then zje else 0 end as dje,case when ffyf='04' then zgws else 0 end as dgws,case when ffyf='04' then zrc else 0 end as drc,case when ffyf='05' then zje else 0 end as eje,case when ffyf='05' then zgws else 0 end as egws,");
		sql.append(" case when ffyf='05' then zrc else 0 end as erc,case when ffyf='06' then zje else 0 end as fje,case when ffyf='06' then zgws else 0 end as fgws,case when ffyf='06' then zrc else 0 end as frc,");
		sql.append(" case when ffyf='07' then zje else 0 end as gje,case when ffyf='07' then zgws else 0 end as ggws,case when ffyf='07' then zrc else 0 end as grc,case when ffyf='08' then zje else 0 end as hje,case when ffyf='08' then zgws else 0 end as hgws,case when ffyf='08' then zrc else 0 end as hrc,case when ffyf='09' then zje else 0 end as ije,case when ffyf='09' then zgws else 0 end as igws,case when ffyf='09' then zrc else 0 end as irc,");
		sql.append(" case when ffyf='10' then zje else 0 end as jje,case when ffyf='10' then zgws else 0 end as jgws,case when ffyf='10' then zrc else 0 end as jrc,case when ffyf='11' then zje else 0 end as kje,case when ffyf='11' then zgws else 0 end as kgws,case when ffyf='11' then zrc else 0 end as krc,case when ffyf='12' then zje else 0 end as lje,case when ffyf='12' then zgws else 0 end as lgws,case when ffyf='12' then zrc else 0 end as lrc,case when ffyf='hj' then zje else 0 end as mje,case when ffyf='hj' then zgws else 0 end as mgws,case when ffyf='hj' then zrc else 0 end as mrc"); 
		sql.append(" from ");
		sql.append(" (select case when ffyf is null then 'hj' else ffyf end as ffyf, zje,zgws,zrc from ");
		sql.append(" (select substr(yf,6,6) ffyf,sum(je) zje,sum(gws) zgws,sum(rc) zrc from VIEW_XG_QGZX_CJTJCX_NDCJFFTJ ");
		sql.append(query);
		sql.append(" group by rollup(substr(yf,6,6)))))) a ");
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQueryNotFy(sql.toString(), "", inputV, colList, myForm);
		return list;
	}
	
	/**
	 * 查询部门酬金发放统计首页面数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getBmcjfftjCx(QgzxTjcxForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		String[] colList = new String[] { "xh", "xm", "nj", "xymc", "zymc", "bjmc", "zcj", "bmmc", "yf" };
		// 用户属性
		// ====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append("select bmmc,yf,xh,xm,nj,xymc,zymc,bjmc,zcj,rownum r from VIEW_XG_QGZX_CJTJCX_BMCJFFTJ ");
		sql.append(query);
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQueryNotFy(sql.toString(), "", inputV, colList, myForm);
		return list;
	}

	/**
	 * 查询个人酬金发放统计首页面数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGrcjfftjCx(QgzxTjcxForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		String[] colList = new String[] { "xh", "xm", "xymc", "aje", "bje", "cje", "dje","eje", "fje", "gje","hje","ije","jje","kje","lje","mje" };
		// 用户属性
		// ====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.xm,b.xymc from ");
		sql.append(" (select xh,sum(aje) aje,sum(bje) bje,sum(cje) cje,sum(dje) dje,sum(eje) eje,sum(fje) fje,sum(gje) gje, ");
		sql.append(" sum(hje) hje,sum(ije) ije,sum(jje) jje,sum(kje) kje,sum(lje) lje,sum(mje) mje from ");
		sql.append(" (select xh,case when ffyf='01' then ybc else 0 end as aje, ");
		sql.append(" case when ffyf='02' then ybc else 0 end as bje, ");
		sql.append(" case when ffyf='03' then ybc else 0 end as cje, ");
		sql.append(" case when ffyf='04' then ybc else 0 end as dje, ");
		sql.append(" case when ffyf='05' then ybc else 0 end as eje, ");
		sql.append(" case when ffyf='06' then ybc else 0 end as fje, ");
		sql.append(" case when ffyf='07' then ybc else 0 end as gje, ");
		sql.append(" case when ffyf='08' then ybc else 0 end as hje, ");
		sql.append(" case when ffyf='09' then ybc else 0 end as ije, ");
		sql.append(" case when ffyf='10' then ybc else 0 end as jje, ");
		sql.append(" case when ffyf='11' then ybc else 0 end as kje, ");
		sql.append(" case when ffyf='12' then ybc else 0 end as lje, ");
		sql.append(" case when ffyf='hj' then ybc else 0 end as mje from "); 
		sql.append(" (select xh,case when ffyf is null and xh is null then 'xj' when ffyf is null then 'hj' else ffyf end as ffyf, ybc from ");
		sql.append(" (select xh,substr(yf,6,6) ffyf,sum(je) ybc from VIEW_XG_QGZX_CJTJCX_GRCJFFTJ  ");
		sql.append(query);
		sql.append(" group by rollup(xh,substr(yf,6,6)))) where xh is not null) group by xh) a left join view_xsjbxx b on a.xh = b.xh ");
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQueryNotFy(sql.toString(), "", inputV, colList, myForm);
		return list;
	}

	/**
	 * 查询个人酬金发放统计首页面数据合计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGrcjfftjCxAll(QgzxTjcxForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		String[] colList = new String[] { "aje", "bje", "cje", "dje","eje", "fje", "gje","hje","ije","jje","kje","lje","mje" };
		// 用户属性
		// ====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append(" select sum(aje) aje,sum(bje) bje,sum(cje) cje,sum(dje) dje,sum(eje) eje,sum(fje) fje,sum(gje) gje, ");
		sql.append(" sum(hje) hje,sum(ije) ije,sum(jje) jje,sum(kje) kje,sum(lje) lje,sum(mje) mje from ");
		sql.append(" (select sum(aje) aje,sum(bje) bje,sum(cje) cje,sum(dje) dje,sum(eje) eje,sum(fje) fje,sum(gje) gje, ");
		sql.append(" sum(hje) hje,sum(ije) ije,sum(jje) jje,sum(kje) kje,sum(lje) lje,sum(mje) mje from ");
		sql.append(" (select xh,case when ffyf='01' then ybc else 0 end as aje, ");
		sql.append(" case when ffyf='02' then ybc else 0 end as bje, ");
		sql.append(" case when ffyf='03' then ybc else 0 end as cje, ");
		sql.append(" case when ffyf='04' then ybc else 0 end as dje, ");
		sql.append(" case when ffyf='05' then ybc else 0 end as eje, ");
		sql.append(" case when ffyf='06' then ybc else 0 end as fje, ");
		sql.append(" case when ffyf='07' then ybc else 0 end as gje, ");
		sql.append(" case when ffyf='08' then ybc else 0 end as hje, ");
		sql.append(" case when ffyf='09' then ybc else 0 end as ije, ");
		sql.append(" case when ffyf='10' then ybc else 0 end as jje, ");
		sql.append(" case when ffyf='11' then ybc else 0 end as kje, ");
		sql.append(" case when ffyf='12' then ybc else 0 end as lje, ");
		sql.append(" case when ffyf='hj' then ybc else 0 end as mje from "); 
		sql.append(" (select xh,case when ffyf is null and xh is null then 'xj' when ffyf is null then 'hj' else ffyf end as ffyf, ybc from ");
		sql.append(" (select xh,substr(yf,6,6) ffyf,sum(je) ybc from VIEW_XG_QGZX_CJTJCX_GRCJFFTJ  ");
		sql.append(query);
		sql.append(" group by rollup(xh,substr(yf,6,6)))) where xh is not null) group by xh) a ");
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQueryNotFy(sql.toString(), "", inputV, colList, myForm);
		return list;
	}

	/**
	 * 查询部门酬金发放统计首页面部门名称
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBmmc(String dqbm) {
		DAO dao = DAO.getInstance();
		String sql = "select bmmc from zxbz_xxbmdm where bmdm=?";
		return dao.getMapNotOut(sql.toString(), new String[] { dqbm });
	}
}