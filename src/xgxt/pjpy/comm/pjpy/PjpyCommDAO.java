package xgxt.pjpy.comm.pjpy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.pjpy.pjlc.jgcx.PjpyJgcxForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_通用_DAO类
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
public class PjpyCommDAO extends CommDAO {

	/**
	 * 获得项目信息(前台DWR)
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getXmInfoList(String[] xmInfo,
			String userStatus, String userName, String userDep) {

		String xmdm = xmInfo[0];// 项目代码

		String xmmc = xmInfo[1];// 项目名称

		String ywmc = xmInfo[2];// 英文名称

		String xmxz = xmInfo[3];// 项目性质

		String xmfw = xmInfo[4];// 项目范围

		String xmlx = xmInfo[5];// 项目类型

		// 评奖项目表
		String tableName = "xg_pjpy_pjxmwhb a";

		ArrayList<String> inPutList = new ArrayList<String>();

		StringBuilder query = new StringBuilder();
		query.append(" where 1 = 1 ");

		if (!Base.isNull(xmdm)) {
			query.append(" and xmdm = ? ");
			inPutList.add(xmdm);
		}

		if (!Base.isNull(xmmc)) {
			query.append(" and xmmc like '%' || ? || '%' ");
			inPutList.add(xmmc);
		}

		if (!Base.isNull(ywmc)) {
			query.append(" and ywmc like '%' || ? || '%' ");
			inPutList.add(ywmc);
		}

		if (!Base.isNull(xmxz)) {
			query.append(" and xmxz = ? ");
			inPutList.add(xmxz);
		}

		if (!Base.isNull(xmfw)) {
			query.append(" and xmfw = ? ");
			inPutList.add(xmfw);
		}

		if (!Base.isNull(xmlx)) {
			query.append(" and xmlx = ? ");
			inPutList.add(xmlx);
		}

		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();

		query.append(" and pjxn = '" + pjxn + "' ");
		query.append(" and pjxq = '" + pjxq + "' ");
		query.append(" and pjnd = '" + pjnd + "' ");
		query.append(" and sfqy = '是' ");

		query
				.append(
						" and not exists (select 1 from (select * from xg_pjpy_sjszb where ")
				.append("(sysdate not between to_date(shkssj,'yyyy-mm-dd') ")
				.append(" and to_date(shjssj,'yyyy-mm-dd')+1) or shkzkg<>'0'")
				.append(" ) b where a.pjxn=b.pjxn ")
				.append(
						" and a.pjxq=b.pjxq and a.pjnd=b.pjnd and a.xmdm=b.xmdm)");

		query.append(" order by to_number(xssx) ");

		// 输出值
		String[] colList = new String[] { "xmdm", "xmmc", "lcid" };

		List<HashMap<String, String>> xmList = getRsList(tableName, query
				.toString(), inPutList.toArray(new String[] {}), colList, "");

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (xmList != null && xmList.size() > 0) {

			PjpyCommService service = new PjpyCommService();

			for (int i = 0; i < xmList.size(); i++) {

				HashMap<String, String> map = xmList.get(i);
				// 项目代码
				xmdm = map.get("xmdm");

				String pkValue = pjxn + pjxq + pjnd + xmdm;

				PjpyXmszModel xmszModel = service.getXmszForXmdm(pkValue);

				// 流程id
				String lcid = xmszModel.getLcid();

				boolean flag = false;

				if (!Base.isNull(lcid)) {
					// 流程信息
					List<HashMap<String, String>> lcInfoList = getLcInfo(lcid);
					// 岗位信息
					List<HashMap<String, String>> gwInfoList = getGwInfo(
							userName, lcid);

					if (lcInfoList != null && lcInfoList.size() > 0) {
						for (int j = 0; j < lcInfoList.size(); j++) {
							String spgw = lcInfoList.get(j).get("spgw");
							if (gwInfoList != null && gwInfoList.size() > 0) {
								for (int k = 0; k < gwInfoList.size(); k++) {
									String yhgw = gwInfoList.get(k).get("spgw");
									if (spgw.equalsIgnoreCase(yhgw)) {
										flag = true;
										break;
									}
								}
							}
							if (flag) {
								break;
							}
						}
					}
				}

				if (flag) {
					list.add(map);
				}
			}

		}

		return list;
	}

	/**
	 * 获得项目信息(前台DWR)
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getXmszInfo(String xmdm,
			String userName) {

		DAO dao = DAO.getInstance();

		ArrayList<HashMap<String, String>> rsList = new ArrayList<HashMap<String, String>>();

		PjpyCommService service = new PjpyCommService();

		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		String pkValue = pjxn + pjxq + pjnd + xmdm;

		PjpyXmszModel xmszModel = service.getXmszForXmdm(pkValue);

		// 流程ID
		String lcid = xmszModel.getLcid();

		if (!Base.isNull(lcid)) {
			// 流程信息
			List<HashMap<String, String>> lcInfoList = getLcInfo(lcid);
			// 岗位信息
			List<HashMap<String, String>> gwInfoList = getGwInfo(userName, lcid);

			if (lcInfoList != null && lcInfoList.size() > 0) {
				for (int j = 0; j < lcInfoList.size(); j++) {
					String spgw = lcInfoList.get(j).get("spgw");
					if (gwInfoList != null && gwInfoList.size() > 0) {
						for (int k = 0; k < gwInfoList.size(); k++) {
							String yhgw = gwInfoList.get(k).get("spgw");
							if (spgw.equalsIgnoreCase(yhgw)) {
								rsList.add(gwInfoList.get(k));
							}
						}
					}

				}
			}
		}

		return rsList;
	}

	/**
	 * 获得流程信息
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getLcInfo(String lcid) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.spgw,a.gwmc from (");
		sql.append(" select a.id, a.mc, b.spgw, c.mc gwmc, b.xh ");
		sql.append(" from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c ");
		sql.append(" where a.id = b.splc and b.spgw = c.id");
		sql.append(" ) a where a.id = ? order by to_number(a.xh)");

		String[] outputValue = new String[] { "spgw", "gwmc" };
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { lcid }, outputValue);

		return list;
	}

	/**
	 * 获得用户岗位信息
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getGwInfo(String userName, String lcid) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.spgw, b.yhm, c.mc gwmc,d.xh shjb ");
		sql.append(" from xg_xtwh_spgwyh a, yhb b, xg_xtwh_spgw c, ");
		sql.append(" xg_xtwh_spbz d where a.spyh = b.yhm  and a.spgw = c.id ");
		sql.append(" and d.spgw = a.spgw and b.yhm = ? and d.splc = ? ");

		String[] outputValue = new String[] { "spgw", "gwmc", "shjb" };
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { userName, lcid }, outputValue);

		return list;
	}

	/**
	 * 获得项目基本信息列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getXmjbInfoList(PjpyJgcxForm model) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		// 学号
		String xh = model.getXh();
		// 项目名称
		String xmmc = model.getXmmc();
		// 评奖周期
		String pjxn = jbszModel.getPjxn();
		String pjxq = jbszModel.getPjxq();
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append(" select shyj, shzt from xg_view_pjpy_xmsh ");
		sql.append(" where 1=1 ");
		sql.append(" and xh=? ");
		sql.append(" and xmmc=? ");
		sql.append(" and ( ");
		sql.append(" pjxn = ? ");
		sql.append(" or (pjxn = ? and pjxq = ?) ");
		sql.append(" or pjnd = ? ");
		sql.append(" )  order by shjb ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xh, xmmc, pjxn, pjxn, pjxq, pjnd },
				new String[] { "shyj", "shzt" });

		return list;
	}

	/**
	 * 获得项目基本信息列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public HashMap<String, String> getXmjbInfo(PjpyJgcxForm model) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		// 学号
		String xh = model.getXh();
		// 项目代码
		String xmmc = model.getXmmc();
		// 评奖周期
		String pjxn = jbszModel.getPjxn();
		String pjxq = jbszModel.getPjxq();
		String pjnd = jbszModel.getPjnd();
		// 综测排名
		String zcpm = model.getZcpm();
		// 综测排名字段
		String zcpmzd = "";
		if ("1".equalsIgnoreCase(zcpm)) {
			zcpmzd = "zcfnjxypm";
		} else if ("2".equalsIgnoreCase(zcpm)) {
			zcpmzd = "zcfnjzypm";
		} else {
			zcpmzd = "zcfbjpm";
		}

		StringBuilder sql = new StringBuilder();

		sql.append("select a.xmmc,a.xmje,a.xmxz,b.zcpm,xmlxmc from( ");
		sql.append("select a.xmmc,a.xmje, ");
		sql.append("(select b.xzmc from xg_pjpy_xmxzb b where a.xmxz=b.xzdm) xmxz, ");
		sql.append(" case when xmlx='01' then '奖学金' else '荣誉称号' end xmlxmc ");
		sql.append("from xg_pjpy_pjxmwhb a ");
		sql.append("where 1=1 ");
		sql.append("and a.xmmc=? ");
		sql.append("and a.pjxn=? ");
		sql.append("and a.pjxq=? ");
		sql.append("and a.pjnd=? ");
		sql.append(") a left join ( ");
		sql.append("select " + zcpmzd + " zcpm from xg_pjpy_zhcpb b ");
		sql.append("where 1=1 ");
		sql.append("and b.xh=? ");
		sql.append("and b.xn=? ");
		sql.append(") b on 1 = 1 ");

		DAO dao = DAO.getInstance();
		
		HashMap<String, String> map = dao.getMap(sql.toString(), new String[] {
				xmmc, pjxn, pjxq, pjnd, xh, pjxn }, new String[] { "xmmc",
				"xmje", "xmxz", "zcpm", "xmlxmc" });

		return map;
	}

	/**
	 * 获取学生成绩信息(根据学生学号,学年)
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	public List<HashMap<String, String>> getXscjList(PjpyJgcxForm model,
			HashMap<String, String> map) {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xn,a.xq,a.kcmc,a.cj,b.xqjb from cjb a  ");
		sql.append(" left join xqdzb b on a.xq=b.xqdm where xh=? and  xn=? ");
		sql.append(" order by a.xq,a.kcmc ");

		String[] colList = { "xn", "xq", "kcmc", "cj", "xqjb" };
		return dao.getList(sql.toString(), new String[] { map.get("xh"),
				map.get("pjxn") }, colList);
	}

	/**
	 * 补考科目数
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	public String getBkks(PjpyJgcxForm model, HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1)bkks from cjb where xn=? and xh=? ");
		sql.append("  and bkcj is not null and cxcj is not null  group by xh ");
		return dao.getOneRs(sql.toString(), new String[] { map.get("pjxn"),
				map.get("xh") }, "bkks");
	}

	
	/**
	 * 未取得学分科目数
	 * @param xh
	 * @return
	 */
	public String getWdxfkms(String xh){
		DAO dao = DAO.getInstance();
		String sql = "select count(1) kms from cjb where xh = ? and xf='' and xf='0'";
		return dao.getOneRs(sql, new String[]{xh}, "kms");
	}
	
	
	
	/**
	 * 计算评奖学年平均分及平均分排名
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	public HashMap<String, String> getPjfInfo(PjpyJgcxForm model,
			HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select xh,pjf,(rank() over( order by to_number(pjf) desc nulls last)) pjfpm from( ");
		sql.append(" select xh,round(zcj/kcs,1) pjf from( ");
		sql.append(" select a.xh,nvl(sum(cj),0)zcj,count(1)kcs from( ");
		sql.append(" select xh from view_xsjbxx  where bjdm=( ");
		sql.append(" select bjdm from view_xsjbxx where xh=?))a left join  ");
		sql.append(" cjb b on a.xh=b.xh where b.xn=?  group by a.xh)) where xh=? ");
		return dao.getMap(sql.toString(), new String[] { map.get("xh"),
				map.get("pjxn"), map.get("xh") }, new String[] { "xh", "pjf",
				"pjfpm" });
	}

	/**
	 * 获取是否违纪
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	public String getSfwj(PjpyJgcxForm model, HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select case when sfwj<>'0' then '是' else '否' end sfwj from ( ");
		sql.append(" select count(1)sfwj from wjcfb where xh=? and xxsh='通过') ");
		return dao.getOneRs(sql.toString(), new String[] { map.get("xh") },
				"sfwj");
	}

	/**
	 * 获取综测总分,班级排名
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	public HashMap<String, String> getZczfBjpm(PjpyJgcxForm model,
			HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select zd1,zd2,zd4,zd3,zyfnjzypm,zcfbjpm from xg_pjpy_zhcpb where xn=? and xh=? ");
		return dao.getMap(sql.toString(), new String[] { map.get("pjxn"),
				map.get("xh") }, new String[] { "zd1", "zcfbjpm", "zd3","zd2","zd4",
				"zyfnjzypm" });
	}

	/**
	 * 获取班级干部
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	public String getBjgbInfo(PjpyJgcxForm model, HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("  select xh, to_char(WM_CONCAT(bjgbmc)) bjgbxx  from (");
		sql.append(" select distinct(bjgbmc) bjgbmc,xh  from sxjy_bjgbxxb a, ");
		sql.append(" sxjy_bjgbzlb b where xh=? and a.bjgbdm=b.bjgbdm ");
		sql.append(")   group by xh ");
		return dao.getOneRs(sql.toString(), new String[] { map.get("xh") },
				"bjgbxx");
	}

	/**
	 * 获取班级人数(评奖人员)
	 */
	public String getBjrsByPjry(PjpyJgcxForm model, HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1)pjbjrs from xg_view_pjpy_pjryk where bjdm=  ");
		sql.append(" (select bjdm from xg_view_pjpy_pjryk where xh=?  )");
		return dao.getOneRs(sql.toString(), new String[] { map.get("xh"),
				}, "pjbjrs");
	}

	/**
	 * 获取评奖评优审核意见
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	public List<HashMap<String, String>> getPjpyShyj(PjpyJgcxForm model,
			HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from( ");
		sql.append(" select shyj,shjb,sqly,b.xm, ");
		sql.append(" case when shsj is not null then substr(shsj,0,4) end nian, ");
		sql.append(" case when shsj is not null then substr(shsj,5,2) end yue, ");
		sql.append(" case when shsj is not null then substr(shsj,7,2) end ri ");
		sql.append(" from (");
		
		sql.append(" select a.*, b.xmmc,c.sqly,d.xh shjb ");
		sql.append(" from xg_pjpy_pjxmshb_backup a, ");
		sql.append(" xg_pjpy_pjxmwhb b, ");
		sql.append(" xg_pjpy_pjxmsqb_backup c, ");
		sql.append(" xg_xtwh_spbz d ");
		sql.append(" where a.xmdm = b.xmdm ");
		sql.append(" and a.xh = c.xh ");
		sql.append(" and a.pjxn = c.pjxn ");
		sql.append(" and a.pjxq = c.pjxq ");
		sql.append(" and a.pjnd = c.pjnd ");
		sql.append(" and a.xmdm = c.xmdm ");
		sql.append(" and b.lcid=d.splc ");
		sql.append(" and a.xtgwid=d.spgw ");
		
		sql.append(") a left join yhb b on a.shr=b.yhm  ");
		sql.append(" where xmmc=? and xh=? ");
		
		if ("xn".equalsIgnoreCase(model.getZczq())) {
			sql.append(" and pjxn=(select pjxn from xg_pjpy_xtszb) ");
		}
		if ("xq".equalsIgnoreCase(model.getZczq())) {
			sql.append(" and pjxn=(select pjxn from xg_pjpy_xtszb) ");
			sql.append(" and pjxq=(select pjxq from xg_pjpy_xtszb) ");
		}
		if ("nd".equalsIgnoreCase(model.getZczq())) {
			sql.append(" and pjnd=(select pjnd from xg_pjpy_xtszb) ");
		}
		
		sql.append(" order by shjb desc )where rownum<=2");

		return dao.getList(sql.toString(), new String[] { model.getXmmc(),
				map.get("xh") }, new String[] { "shyj", "shjb","nian","yue","ri","xm"});
	}
	
	/**
	 * 获取评奖评优审核意见
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	public HashMap<String, String> getPjpyInfo(PjpyJgcxForm model,
			HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("  select sqly from xg_view_pjpy_jgcx ");
		sql.append(" where xmmc=? and xh=? ");
		
		if ("xn".equalsIgnoreCase(model.getZczq())) {
			sql.append(" and pjxn=(select pjxn from xg_pjpy_xtszb) ");
		}
		if ("xq".equalsIgnoreCase(model.getZczq())) {
			sql.append(" and pjxn=(select pjxn from xg_pjpy_xtszb) ");
			sql.append(" and pjxq=(select pjxq from xg_pjpy_xtszb) ");
		}
		if ("nd".equalsIgnoreCase(model.getZczq())) {
			sql.append(" and pjnd=(select pjnd from xg_pjpy_xtszb) ");
		}
		

		return dao.getMap(sql.toString(), new String[] { model.getXmmc(),
				map.get("xh") }, new String[] { "sqly"});
	}
	
	/**
	 * 获取学校审核意见（贵州大学一类无需审核流程）
	 * @param model
	 * @param map
	 * @return
	 */
	public HashMap<String, String> getShxx(PjpyJgcxForm model,
			HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select xysh,xxsh,xyshyj,xxshyj, ");
		sql.append(" (select xm from yhb b where a.xyshr=b.yhm )xyshr, ");
		sql.append(" (select xm from yhb b where a.xxshr=b.yhm )xxshr,xyshsj,xxshsj  ");
		sql.append(" from xg_pjpy_jxjsqb a ,xg_pjpy_pjxmwhb b ");
		sql.append(" where xmmc=? and xh=? ");
		
		if ("xn".equalsIgnoreCase(model.getZczq())) {
			sql.append(" and pjxn=(select pjxn from xg_pjpy_xtszb) ");
			sql.append(" and a.xn=b.pjxn  ");
		}
		if ("xq".equalsIgnoreCase(model.getZczq())) {
			sql.append(" and pjxn=(select pjxn from xg_pjpy_xtszb) ");
			sql.append(" and pjxq=(select pjxq from xg_pjpy_xtszb) ");
			sql.append(" and a.xn=b.pjxn and a.xq=b.pjxq ");
		}
		sql.append(" and a.xmdm=b.xmdm ");
		
		return dao.getMap(sql.toString(), new String[] { model.getXmmc(),
				map.get("xh") }, new String[] { "xysh", "xxsh","xyshsj","xxshsj","xyshyj","xxshyj","xyshr","xxshr" });
	}
	
	/**
	 * 贵州大学平均成绩（加权平均分/加权平均绩点）
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	public HashMap<String,String> getPjcj(PjpyJgcxForm model, HashMap<String, String> map) {
        DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh,jqpjf,jqpjjd from( ");
		sql.append(" select xh,case when jqpjf is null then  0 else ");
		sql.append(" to_number(jqpjf) end jqpjf, ");
		sql.append(" case when jqpjjd is null then  0  else  to_number(jqpjjd) ");
        sql.append(" end jqpjjd from xg_view_gzdx_jqpjxfjd where xn=(select pjxn from xg_pjpy_xtszb)) ");
        sql.append(" where xh=? ");
        return dao.getMap(sql.toString(), new String[]{map.get("xh")}, new String[]{"jqpjf","jqpjjd"});
	}
	
	/**
	 * 贵州大学平均成绩（加权平均分/加权平均绩点）
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	public List<HashMap<String,String>> getPjInfo(PjpyJgcxForm model, HashMap<String, String> map) {
        DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select pjxn||pjxq||pjnd||'获得'||xmmc||';' pjinfo from(");
		sql.append(" select case when pjxn<>'无' then pjxn||'学年' else '' end pjxn,xmmc,");
		sql.append(" case when pjxq <>'无' then (select xqmc from xqdzb b ");
		sql.append(" where a.pjxq=b.xqdm and rownum =1 )||'学期' else '' end pjxq,");
		sql.append(" case when pjnd <>'无' then pjnd||'年度' else '' end pjnd  ");
		sql.append(" from xg_view_pjpy_xstgjl a where xh = ? ) ");
        System.out.println(sql);
        return dao.getList(sql.toString(), new String[]{map.get("xh")}, new String[]{"pjinfo"});
	}

	public HashMap<String, String> getHzsfSqly(PjxtszModel jbszModel, String xmmc, String xh) {
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select sqly  from (select sqly  from xg_pjpy_pjxmsqb ");
		sql.append(" where xmdm = (select xmdm  from xg_pjpy_pjxmwhb ");
		sql.append(" where pjxn =?  and pjxq =?  and pjnd =? and xmmc =?) ");
		sql.append(" and pjxn =? and pjxq =? and pjnd = ?  and xh = ? ");
		
		sql.append(" union ");
		sql.append(" select bz from xg_pjpy_pjlsxxb  where xn =?   and xq =? ");
		sql.append(" and xmmc = ?  and xh =?  ) ");
		
		return dao.getMap(sql.toString(), new String[] {jbszModel.getPjxn(),jbszModel.getPjxq(),jbszModel.getPjnd(),xmmc,
			jbszModel.getPjxn(),jbszModel.getPjxq(),jbszModel.getPjnd(),xh,
			jbszModel.getPjxn(),jbszModel.getPjxq(),xmmc,xh,
			}, new String[] { "sqly"});
	}

}
