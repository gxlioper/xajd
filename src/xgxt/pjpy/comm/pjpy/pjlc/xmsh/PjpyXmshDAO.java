package xgxt.pjpy.comm.pjpy.pjlc.xmsh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommDAO;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖流程_项目审核_DAO类
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

public class PjpyXmshDAO extends PjpyCommDAO {

	/**
	 * 获得审核项目列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXhxmList(PjpyXmshForm model,
			User user) {

		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		String xmdm = model.getXmdm();// 项目代码
		String xmmc = model.getXmmc();// 项目名称
		String ywmc = model.getYwmc();// 英文名称
		String xmxz = model.getXmxz();// 项目性质
		String xmfw = model.getXmfw();// 项目范围
		String xmlx = model.getXmlx();// 项目类型
		
		// 评奖项目表
		String tableName = "xg_pjpy_pjxmwhb a";
		// 条件
		StringBuilder query = new StringBuilder();
		query.append(" where 1 = 1 ");
		query.append(" and pjxn = ? ");
		query.append(" and pjxq = ? ");
		query.append(" and pjnd = ? ");
		query.append(" and sfqy = '是' ");
		query.append(Base.isNull(xmdm) ? "" : " and xmdm = '" + xmdm + "' ");
		query.append(Base.isNull(xmmc) ? "" : " and xmmc like '%' || '" + xmmc + "' || '%' ");
		query.append(Base.isNull(ywmc) ? "" : " and ywmc like '%' || '" + ywmc + "'|| '%' ");
		query.append(Base.isNull(xmxz) ? "" : " and xmxz = '" + xmxz + "' ");
		query.append(Base.isNull(xmfw) ? "" : " and xmfw = '" + xmfw + "' ");
		query.append(Base.isNull(xmlx) ? "" : " and xmlx = '" + xmlx + "' ");
		
		//审核时间控制开关
		query.append(" and not exists (select 1 from (select * from xg_pjpy_sjszb where ")
		     .append("(sysdate not between to_date(shkssj,'yyyy-mm-dd') ")
		     .append(" and to_date(shjssj,'yyyy-mm-dd')+1) or shkzkg<>'0'")
		     .append(" ) b where a.pjxn=b.pjxn ")
		     .append(" and a.pjxq=b.pjxq and a.pjnd=b.pjnd and a.xmdm=b.xmdm)");
		
		query.append(" order by to_number(xssx) ");

		String[] colList = new String[] { "xmdm", "xmmc", "lcid" };

		return getRsList(tableName, query.toString(), new String[] {pjxn,pjxq,pjnd}, colList,
				"");
	}
	
	/**
	 * 获得项目审核学生列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getXmshXsList(PjpyXmshForm model, User user,
			String[] colList, HttpServletRequest request)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		SearchService searchService = new SearchService();

		PjpyXmszModel xmszModel = model.getXmszModel();

		// 申请周期
		String sqzq = xmszModel.getSqzq();
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// 审核级别
		String shjb = model.getShjb();
		// 审核岗位
		String spgw = model.getSpgw();

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// 权限控制
		String searchTjByUser = searchService.getSearchTjByUser(request, "a",
				"xydm", "bjdm");
		searchTj += searchTjByUser;

		// 审核项目
		String xmdm = model.getShxm();

		StringBuilder sql = new StringBuilder();
		// 下级审核
		String nextShjb = String.valueOf(Integer.parseInt(shjb) + 1);
		
		sql.append("select rownum r,a.*, ");
		sql.append("nvl((select d.shzt from xg_pjpy_pjxmshb d, xg_xtwh_spbz e ");
		sql.append(" where d.xtgwid = e.spgw and a.xmdm = d.xmdm ");
		sql.append(" and a.pjxn = d.pjxn and a.pjxq = d.pjxq ");
		sql.append(" and a.pjnd = d.pjnd and a.xh = d.xh and e.splc = a.lcid");
		sql.append(" and e.xh = '"+nextShjb+"'),'未审核') sjzt from xg_view_pjpy_xmsh a ");
		sql.append(" where 1 = 1 ");
		sql.append(Base.isNull(xmdm) ? "" : " and xmdm = '" + xmdm + "'");
		sql.append(Base.isNull(spgw) ? "" : " and spgw = '" + spgw + "'");
		sql.append(Base.isNull(shjb) ? "" : " and shjb = '" + shjb + "'");
		
		
		//控制项目申请周期
		if("xn".equalsIgnoreCase(sqzq)){
			sql.append(" and pjxn = '" + pjxn + "'");
		}else if("nd".equalsIgnoreCase(sqzq)){
			sql.append(" and pjnd = '" + pjnd + "'");
		}else if("xq".equalsIgnoreCase(sqzq)){
			sql.append(" and pjxn = '" + pjxn + "'");
			sql.append(" and pjxq = '" + pjxq + "'");
		}
		
		// 第一级审核
		if ("1".equalsIgnoreCase(shjb)) {

		} else {
			// 上级审核
			String preShjb = String.valueOf(Integer.parseInt(shjb) - 1);
			sql.append(" and exists (select 1 from xg_xtwh_spbz b,xg_pjpy_pjxmshb c ");
			sql.append(" where b.splc = a.lcid ");
			sql.append(" and a.xh = c.xh ");
			sql.append(" and a.xmdm = c.xmdm ");
			sql.append(" and a.pjxn = c.pjxn ");
			sql.append(" and a.pjxq = c.pjxq ");
			sql.append(" and a.pjnd = c.pjnd ");	
			sql.append(" and b.spgw = c.xtgwid ");
			sql.append(" and b.xh = '" + preShjb + "' ");
			sql.append(" and (c.shzt = '通过')");
			sql.append(" ) ");
		}
		
		sql.append(searchTj);
		ArrayList<String[]> list = commonQuery(sql.toString(), "", inputV, colList, model);

		return list;
	}
	
	/**
	 * 获得需要退回操作的项目主键
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String[] getXmthPk(PjpyXmshForm model) throws Exception {

		// 主键值
		String[] pkValue = model.getPrimarykey_checkVal();
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xmdm||a.pjxn||a.pjxq||a.pjnd||a.xh||a.xtgwid pk ");
		sql.append(" from xg_view_pjpy_xmsh a where exists (select 1 ");
		sql.append(" from (select a.xmdm,a.pjxn,a.pjxq,a.pjnd,a.xh, ");
		sql.append(" (a.shjb - 1) shjb from xg_view_pjpy_xmsh a ");
		sql.append(" where 1 = 1");

		if (pkValue != null && pkValue.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < pkValue.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.pk = ? ");
			}
			sql.append(" ) ");
		}
		sql.append(" ) b ");
		sql.append(" where a.xmdm = b.xmdm and a.pjxn = b.pjxn ");
		sql.append(" and a.pjxq = b.pjxq and a.pjnd = b.pjnd ");
		sql.append(" and a.xh = b.xh and a.shjb = b.shjb) ");

		DAO dao = DAO.getInstance();

		String[] rs = dao.getRs(sql.toString(), pkValue, "pk");

		return rs;
	}
	
	/**
	 * 获得重申项目的项目主键
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String[] getXmcsPk(PjpyXmshForm model) throws Exception {

		// 主键值
		String[] pkValue = model.getPrimarykey_checkVal();
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xmdm||a.pjxn||a.pjxq||a.pjnd||a.xh||a.xtgwid pk ");
		sql.append(" from xg_view_pjpy_xmsh a where exists (select 1 ");
		sql.append(" from (select a.xmdm,a.pjxn,a.pjxq,a.pjnd,a.xh, ");
		sql.append(" (a.shjb + 1) shjb from xg_view_pjpy_xmsh a ");
		sql.append(" where 1 = 1");

		if (pkValue != null && pkValue.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < pkValue.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.pk = ? ");
			}
			sql.append(" ) ");
		}
		sql.append(" ) b ");
		sql.append(" where a.xmdm = b.xmdm and a.pjxn = b.pjxn ");
		sql.append(" and a.pjxq = b.pjxq and a.pjnd = b.pjnd ");
		sql.append(" and a.xh = b.xh and a.shjb = b.shjb) ");

		DAO dao = DAO.getInstance();

		String[] rs = dao.getRs(sql.toString(), pkValue, "pk");

		return rs;
	}
	
	/**
	 * 获得项目审核级别（最终）
	 * 
	 * @author 伟大的骆
	 */
	public String getXmzzshjb(PjpyXmshForm model) {

		// 项目代码
		String xmdm = model.getShxm();
		// 评奖学年
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append(" select MAX(b.xh) shjb from xg_pjpy_pjxmwhb a ");
		sql.append(",xg_xtwh_spbz b where 1 = 1 ");
		sql.append(" and a.lcid = b.splc ");
		sql.append(" and a.xmdm = ? ");
		sql.append(" and a.pjxn = ? ");
		sql.append(" and a.pjxq = ? ");
		sql.append(" and a.pjnd = ? ");

		DAO dao = DAO.getInstance();

		String zzShjb = dao.getOneRs(sql.toString(), new String[] { xmdm, pjxn,
				pjxq, pjnd }, "shjb");

		return zzShjb;
	}
	
	/**
	 * 获得项目被审核人
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXmshBshr(PjpyXmshForm model)
			throws Exception {

		// 项目设置对象
		PjpyXmszModel xmszModel = model.getXmszModel();

		// 项目代码
		String xmdm = model.getShxm();
		// 申请周期
		String sqzq = xmszModel.getSqzq();
		// 评奖学年
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// 主键值
		String[] pkValue = model.getPrimarykey_checkVal();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm, ");
		sql.append(" a.zymc,a.bjdm,a.bjmc from xg_view_pjpy_xmsh a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and a.xmdm = '" + xmdm + "' ");

		// 控制项目申请周期
		if ("xn".equalsIgnoreCase(sqzq)) {
			sql.append(" and a.pjxn = '" + pjxn + "'");
		} else if ("nd".equalsIgnoreCase(sqzq)) {
			sql.append(" and a.pjnd = '" + pjnd + "'");
		} else if ("xq".equalsIgnoreCase(sqzq)) {
			sql.append(" and a.pjxn = '" + pjxn + "'");
			sql.append(" and a.pjxq = '" + pjxq + "'");
		}

		if (pkValue != null && pkValue.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < pkValue.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.pk = ? ");
			}
			sql.append(" ) ");
		}

		sql.append(" order by a.xydm,a.zydm,a.bjdm ");
		
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				pkValue, new String[] { "xh", "xm", "nj", "xydm", "zydm",
						"bjdm", "xymc", "zymc", "bjmc" });

		return list;
	}
	
	/**
	 * 获得被审核人所在部门
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String[] getBshrbm(List<HashMap<String, String>> bshrList,
			String kzfw) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		
		//控制范围
		if ("nj".equalsIgnoreCase(kzfw)) {
			sql.append(" distinct nj bmdm");
		} else if ("xy".equalsIgnoreCase(kzfw)) {
			sql.append(" distinct xydm bmdm");
		} else if ("zy".equalsIgnoreCase(kzfw)) {
			sql.append(" distinct nj||zydm bmdm ");
		} else {
			sql.append(" distinct bjdm bmdm ");
		}
		
		sql.append(" from view_njxyzybj a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and exists (select 1 from view_xsjbxx b ");
		sql.append(" where a.bjdm = b.bjdm  ");
		
		if (bshrList != null && bshrList.size() > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < bshrList.size(); i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" b.xh = '" + bshrList.get(i).get("xh") + "' ");
			}
			sql.append(" ) ");
		}
		sql.append(" ) ");
		
		DAO dao = DAO.getInstance();

		String[] bmdm = dao.getRs(sql.toString(), new String[]{}, "bmdm");

		return bmdm;
	}
	
	/**
	 * 获得项目人数限制
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXmshrs(PjpyXmshForm model,
			String[] bmdm) {

		// 项目设置对象
		PjpyXmszModel xmszModel = model.getXmszModel();
		// 控制范围
		String kzfw = xmszModel.getKzfw();
		// 项目代码
		String xmdm = model.getShxm();
		// 审核级别
		String shjb = model.getShjb();
		// 申请周期
		String sqzq = xmszModel.getSqzq();
		// 评奖学年
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// 主键值
		String[] pkValue = model.getPrimarykey_checkVal();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.bmdm, a.bmrs, nvl(b.ytgrs, 0) ytgrs from ");
		
		sql.append(" (select a.bmdm,nvl(a.qdrs,0) bmrs from xg_pjpy_rsszb a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and a.xmdm = ? ");
		sql.append(" and a.szfw = ? ");
		if (bmdm != null && bmdm.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < bmdm.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.bmdm = '" + bmdm[i] + "' ");
			}
			sql.append(" ) ");
		}
		sql.append(") a");

		String dm = "";
		// 控制范围
		if ("nj".equalsIgnoreCase(kzfw)) {
			dm = "nj";
		} else if ("xy".equalsIgnoreCase(kzfw)) {
			dm = "xydm";
		} else if ("zy".equalsIgnoreCase(kzfw)) {
			dm = "nj||zydm";
		} else {
			dm = "bjdm";
		}
		
		sql.append(" left join (select b."+dm+" bmdm, count(1) ytgrs ");
		sql.append(" from xg_view_pjpy_xmsh b ");
		sql.append(" where 1 = 1 ");
		sql.append(" and b.xmdm = ? ");
		sql.append(" and b.shjb = ? ");
		// 控制项目申请周期
		if ("xn".equalsIgnoreCase(sqzq)) {
			sql.append(" and b.pjxn = '" + pjxn + "'");
		} else if ("nd".equalsIgnoreCase(sqzq)) {
			sql.append(" and b.pjnd = '" + pjnd + "'");
		} else if ("xq".equalsIgnoreCase(sqzq)) {
			sql.append(" and b.pjxn = '" + pjxn + "'");
			sql.append(" and b.pjxq = '" + pjxq + "'");
		}
		sql.append(" and b.shzt = '通过' ");
		
		//被审核人
		if (pkValue != null && pkValue.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < pkValue.length; i++) {
				if (i != 0) {
					sql.append(" and ");
				}
				sql.append(" b.pk <> '"+pkValue[i]+"' ");
			}
			sql.append(" ) ");
		}
		sql.append(" group by "+dm+") b on a.bmdm = b.bmdm ");
		sql.append(" order by  a.bmdm");
	              
	              
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm, kzfw, xmdm, shjb }, new String[] { "bmdm",
						"bmrs", "ytgrs" });

		return list;
	}

	/**
	 * 获得非兼得项目列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXmjdList(PjpyXmshForm model) {
		
		// 项目代码
		String xmdm = model.getShxm();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xmdm,a.fjddm,b.xmmc fjdmc from xg_pjpy_jdszb a ");
		sql.append(" ,xg_pjpy_pjxmwhb b where a.fjddm=b.xmdm and a.xmdm = ? ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao
				.getList(sql.toString(), new String[] { xmdm }, new String[] {
						"xmdm", "fjddm", "fjdmc" });

		return list;
	}
	
	/**
	 * 获得以获得奖学金列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getYhdjxjList(PjpyXmshForm model,
			List<HashMap<String, String>> bshrList) {
		
		// 项目代码
		String xmdm = model.getShxm();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh, a.xm, a.xmdm, a.pjxn, a.pjxq, a.pjnd,b.sqzq from ");
		sql.append(" (select a.*,b.shzt from ");
		sql.append(" (select a.xh, a.xm, a.xmdm, a.pjxn, a.pjxq, a.pjnd, ");
		sql.append(" a.lcid,max(a.shjb) shjb from xg_view_pjpy_xmsh a ");
		sql.append(" where 1 = 1 ");	
		sql.append(" and a.xmdm <> ? ");		
		
		// 被审核人
		if (bshrList != null && bshrList.size() > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < bshrList.size(); i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.xh = '" + bshrList.get(i).get("xh") + "' ");
			}
			sql.append(" ) ");
		}
		sql.append(" group by a.xh, a.xm, a.xmdm, a.pjxn, a.pjxq, a.pjnd,a.lcid) a, ");
		sql.append(" xg_pjpy_pjxmshb b,xg_xtwh_spbz c ");
		sql.append(" where a.xh = b.xh and a.xmdm = b.xmdm and a.pjxn = b.pjxn ");
		sql.append(" and a.pjxq = b.pjxq and a.pjnd = b.pjnd and b.xtgwid = c.spgw ");
		sql.append(" and a.lcid = c.splc and c.xh = a.shjb ");
		sql.append(" )a, xg_pjpy_pjxmwhb b where a.xmdm = b.xmdm and a.shzt = '通过' ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm }, new String[] { "xmdm", "xh", "xm",
						"pjxn", "pjxq", "pjnd","sqzq" });

		return list;
	}

	/**
	 * 获得项目审核信息列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXmshInfoList(PjpyXmshForm model) {

		//主键值
		String pkValue = model.getPkValue();

		StringBuilder sql = new StringBuilder();

		sql.append(" select a.xh,a.xm,a.pjxn,a.pjxq,a.pjnd,a.mc,a.shjb,a.shzt,a.shyj,a.shsj ");
		sql.append(" from xg_view_pjpy_xmsh a where 1 = 1 ");
		sql.append(" and exists (select 1 from xg_view_pjpy_xmsh b ");
		sql.append(" where a.xmdm = b.xmdm ");
		sql.append(" and a.xh = b.xh ");
		sql.append(" and a.pjxn = b.pjxn ");
		sql.append(" and a.pjxq = b.pjxq ");
		sql.append(" and b.pk = ?) ");

		DAO dao = DAO.getInstance();
		System.out.println("主键值:....."+pkValue);
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pkValue }, new String[] { "xh", "xm", "pjxn",
						"pjxq", "pjnd", "mc", "shjb", "shzt", "shyj", "shsj" });

		return list;
	}
	
	/**
	 * 获得项目调整列表列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getSyxmList(PjpyXmshForm model) {

		// 项目代码
		String xmdm = model.getXmdm();
		// 评奖学年
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.tzxmdm xmdm,b.xmmc from xg_pjpy_pjfwtzb a ");
		sql.append(" ,xg_pjpy_pjxmwhb b where a.tzxmdm = b.xmdm ");
		sql.append(" and a.xmdm = ? ");
		sql.append(" and b.pjxn = ? ");
		sql.append(" and b.pjxq = ? ");
		sql.append(" and b.pjnd = ? ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm,pjxn,pjxq,pjnd }, new String[] { "xmdm", "xmmc" });

		return list;
	}
	
	/**
	 * 获得项目调整列表列表
	 * 
	 * @author 伟大的骆
	 */
	public Boolean getKfsy(PjpyXmshForm model, PjpyXmszModel xmszModel) {

		// 项目代码
		String xmdm = model.getSyxm();
		// 学号
		String xh = model.getXh();
		// 评奖学年
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// 申请周期
		String sqzq = xmszModel.getSqzq();

		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_pjpy_pjxmsqb a ");
		sql.append("where 1 = 1 ");
		sql.append(" and a.xmdm = '" + xmdm + "'");
		sql.append(" and a.xh = '" + xh + "'");
		// 控制项目申请周期
		if ("xn".equalsIgnoreCase(sqzq)) {
			sql.append(" and a.pjxn = '" + pjxn + "'");
		} else if ("nd".equalsIgnoreCase(sqzq)) {
			sql.append(" and a.pjnd = '" + pjnd + "'");
		} else if ("xq".equalsIgnoreCase(sqzq)) {
			sql.append(" and a.pjxn = '" + pjxn + "'");
			sql.append(" and a.pjxq = '" + pjxq + "'");
		}

		DAO dao = DAO.getInstance();

		String num = dao.getOneRs(sql.toString(), new String[] {}, "num");

		boolean flag = false;
		
		if (Base.isNull(num) || "0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}
}
