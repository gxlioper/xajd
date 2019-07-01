package xgxt.xsgygl.bjlh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

public class BjlhGyglDAO extends CommonQueryDAO {
	

	public static final String TWDM = "0202";//团委部门代码
	public static final String TYDM = "0405";//体育教学部部门代码
	public static final String KYDM = "0110";//科研部门代码
	public static final String CJDM = "0117";//成教部门代码
	
	
	
	//团委未分配学生总数SQL
//	public static String QUERY_TW_WFPXSZS = "select nvl(count(xh),'0') rs,xb from (select twsxh xh,(select xb from view_xsjbxx b where a.twsxh=b.xh) xb,(select xydm from view_xsjbxx b where a.twsxh=b.xh) xydm,(select zydm from view_xsjbxx b where a.twsxh=b.xh) zydm,(select bjdm from view_xsjbxx b where a.twsxh=b.xh) bjdm,(select nj from view_xsjbxx b where a.twsxh=b.xh) nj from bjlh_twxsb a where sftws='是') a where not exists(select 1 from bjlh_xszsxxb c where a.xh=c.xh and c.zzbj='yes' and c.lx='其他'";
//	//体育未分配学生总数SQL
//	public static String QUERY_TY_WFPXSZS = "select nvl(count(xh),'0') rs,xb from (select tysxh xh,(select xb from view_xsjbxx b where a.twsxh=b.xh) xb,(select xydm from view_xsjbxx b where a.twsxh=b.xh) xydm,(select zydm from view_xsjbxx b where a.twsxh=b.xh) zydm,(select bjdm from view_xsjbxx b where a.twsxh=b.xh) bjdm,(select nj from view_xsjbxx b where a.twsxh=b.xh) nj from bjlh_tyxsb a where sftys='是') a where not exists(select 1 from bjlh_xszsxxb c where a.xh=c.xh and c.zzbj='yes' and c.lx='其他'";
//	//科研未分配学生总数SQL
//	public static String QUERY_KY_WFPXSZS = "select nvl(count(xh),'0') rs,xb from view_bjlh_yjsxx a where not exists(select 1 from bjlh_xszsxxb c where a.xh=c.xh  and c.lx='研究生' and c.zzbj='yes'";
//	//成教未分配学生总数SQL
//	public static String QUERY_CJ_WFPXSZS = "select nvl(count(xh),'0') rs,xb from view_bjlh_cjsxx a where not exists(select 1 from bjlh_xszsxxb c where a.xh=c.xh  and c.lx='成教生' and c.zzbj='yes'";;
//	//已划分床位总数SQL
//	public static String QUERY_CWZS = "select sum(cws) rs,xbxd from (select a.xydm,a.ssbh,a.cws,b.xbxd from ssfpb a,view_bjlh_ssxx b where a.ssbh=b.pk and b.fbbj ='一般') where 1=1";
//	//未划分宿舍列表SQL
//	public static String QUERY_WHFSS = "select pk||'/'||cws dm,ldmc||'/'||cs||'/'||qsh||'/'||cws mc from view_bjlh_ssxx a where fbbj='一般' and not exists (select 1 from ssfpb b where a.pk=b.ssbh) ";
//	//已划分宿舍列表SQL
//	public static String QUERY_YHFSS = "select xydm||'/'||ssbh||'/'||cws dm,xymc||'/'||ldmc||'/'||cs||'/'||qsh||'/'||cws mc from (select distinct a.xydm,(case a.xydm when '0202' then '团委' when '0405' then '体育教学部' when '0110' then '科研处' when '0117' then '成人教育处' else (select xymc from view_njxyzybj b where a.xydm=b.xydm and rownum=1) end) xymc,a.ssbh,a.cws,b.xqdm,b.xqmc,b.lddm,b.ldmc,b.cs,b.qsh from ssfpb a left join view_bjlh_ssxx b on a.ssbh=b.pk and b.fbbj='一般')";
	//已分配床位总数SQL
	String QUERY_FPCWZS = "select COUNT(xh) rs,xb from (select a.xh,a.lx,(SELECT b.xydm FROM view_bjlh_xsxx b WHERE a.xh=b.xh AND a.lx=b.lx) xydm,(SELECT b.zydm FROM view_bjlh_xsxx b WHERE a.xh=b.xh AND a.lx=b.lx) zydm,(SELECT b.bjdm FROM view_bjlh_xsxx b WHERE a.xh=b.xh AND a.lx=b.lx) bjdm,(SELECT b.nj FROM view_bjlh_xsxx b WHERE a.xh=b.xh AND a.lx=b.lx) nj,(SELECT b.xb FROM view_bjlh_xsxx b WHERE a.xh=b.xh AND a.lx=b.lx) xb,a.lddm,a.cs from bjlh_xszsxxb a WHERE zzbj='yes') where 1=1 ";
	//已分配行李床位总数SQL
	String QUERY_FPXLCWZS = "SELECT count(cwh) rs FROM (SELECT a.cwh,b.xydm,a.lddm,a.cs FROM bjlh_xlcwb a left join ssfpb b on b.ssbh = a.lddm || a.cs || a.qsh) WHERE 1=1 ";
	//未分配床位总数SQL
	String QUERY_WFPCWZS = "select sum(cws) rs,xbxd from (select a.xydm,a.ssbh,(to_number(a.cws)-(SELECT COUNT(*) FROM bjlh_xszsxxb c WHERE c.zzbj='yes' AND a.ssbh=c.lddm||c.cs||c.qsh)-(SELECT COUNT(*) FROM bjlh_xlcwb d WHERE  a.ssbh=d.lddm||d.cs||d.qsh)) cws,b.xbxd,(SELECT c.lddm FROM bjlh_ssxxb c WHERE c.lddm||c.cs||c.qsh=a.ssbh) lddm,(SELECT c.cs FROM bjlh_ssxxb c WHERE c.lddm||c.cs||c.qsh=a.ssbh) cs from ssfpb a,view_bjlh_ssxx b where a.ssbh=b.pk and b.fbbj ='一般') where 1=1 ";
	//未分配床位列表SQL
	String QUERY_WFPCW = "SELECT b.lddm||'/'||b.cs||'/'||b.qsh||'/'||a.cwh dm,b.qsh||'/'||b.cws||'/'||a.cwh mc FROM cwxxb a,view_bjlh_ssxx b,ssfpb c WHERE NOT EXISTS(SELECT 1 FROM bjlh_xszsxxb c WHERE a.ssbh=c.lddm||c.cs||c.qsh AND a.cwh=c.cwh and c.zzbj = 'yes') AND NOT EXISTS(SELECT 1 FROM bjlh_xlcwb d WHERE a.ssbh=d.lddm||d.cs||d.qsh AND a.cwh=d.cwh) AND a.ssbh=b.lddm||b.cs||b.qsh AND a.ssbh=c.ssbh ";
	//未分配学生列表SQL
	String QUERY_WFPXS = "SELECT a.xh dm,a.xh||'/'||a.xm||'/'||a.xb mc FROM view_bjlh_xsxx a WHERE NOT EXISTS(SELECT 1 FROM bjlh_xszsxxb b WHERE a.xh=b.xh AND b.zzbj='yes') ";
	//已分配学生列表SQL
	String QUERY_YFPXS = "SELECT a.xh||'/'||a.lddm||'/'||a.cs||'/'||a.qsh||'/'||a.cwh||'/'||a.rzrq dm, a.xh||'/'||b.xm||'/'||b.xb||'/'||a.qsh||'/'||(SELECT c.cws FROM bjlh_ssxxb c WHERE a.lddm||a.cs||a.qsh=c.lddm||c.cs||c.qsh)||'/'||a.cwh||'/'||a.rzrq mc FROM bjlh_xszsxxb a,view_bjlh_xsxx b WHERE a.xh=b.xh AND a.zzbj='yes' ";
	 
	//团委未分配学生总数SQL
	String QUERY_TW_WFPXSZS = "select nvl(count(xh),'0') rs,xb from (select twsxh xh,(select xb from view_xsjbxx b where a.twsxh=b.xh) xb,(select xydm from view_xsjbxx b where a.twsxh=b.xh) xydm,(select zydm from view_xsjbxx b where a.twsxh=b.xh) zydm,(select bjdm from view_xsjbxx b where a.twsxh=b.xh) bjdm,(select nj from view_xsjbxx b where a.twsxh=b.xh) nj from bjlh_twxsb a where sftws='是') a where not exists(select 1 from bjlh_xszsxxb c where a.xh=c.xh and c.zzbj='yes' and c.lx='其他') ";
	//体育未分配学生总数SQL
	String QUERY_TY_WFPXSZS = "select nvl(count(xh),'0') rs,xb from (select tysxh xh,(select xb from view_xsjbxx b where a.tysxh=b.xh) xb,(select xydm from view_xsjbxx b where a.tysxh=b.xh) xydm,(select zydm from view_xsjbxx b where a.tysxh=b.xh) zydm,(select bjdm from view_xsjbxx b where a.tysxh=b.xh) bjdm,(select nj from view_xsjbxx b where a.tysxh=b.xh) nj from bjlh_tyxsb a where sftys='是') a where not exists(select 1 from bjlh_xszsxxb c where a.xh=c.xh and c.zzbj='yes' and c.lx='其他') ";
	//科研未分配学生总数SQL
	String QUERY_KY_WFPXSZS = "select nvl(count(fqrzxh),'0') rs,xb from view_bjlh_yjsxx a where not exists(select 1 from bjlh_xszsxxb c where a.fqrzxh=c.xh  and c.lx='研究生' and c.zzbj='yes') ";
	//成教未分配学生总数SQL
	String QUERY_CJ_WFPXSZS = "select nvl(count(fqrzxh),'0') rs,xb from view_bjlh_cjsxx a where not exists(select 1 from bjlh_xszsxxb c where a.fqrzxh=c.xh  and c.lx='成教生' and c.zzbj='yes') ";
	//已划分床位总数SQL
	String QUERY_CWZS = "select sum(cws) rs,xbxd from (select a.xydm,a.ssbh,a.cws,b.xbxd from ssfpb a,view_bjlh_ssxx b where a.ssbh=b.pk and b.fbbj ='一般') where 1=1";
	//未划分宿舍列表SQL
	String QUERY_WHFSS = "select pk||'/'||cws dm,ldmc||'/'||cs||'/'||qsh||'/'||cws mc from view_bjlh_ssxx a where fbbj='一般' and not exists (select 1 from ssfpb b where a.pk=b.ssbh) ";
	//已划分宿舍列表SQL
	String QUERY_YHFSS = "select xydm||'/'||ssbh||'/'||cws dm,xymc||'/'||ldmc||'/'||cs||'/'||qsh||'/'||cws mc from (select distinct a.xydm,(case a.xydm when '0202' then '团委' when '0405' then '体育教学部' when '0110' then '科研处' when '0117' then '成人教育处' else (select xymc from view_njxyzybj b where a.xydm=b.xydm and rownum=1) end) xymc,a.ssbh,a.cws,b.xqdm,b.xqmc,b.lddm,b.ldmc,b.cs,b.qsh from ssfpb a left join view_bjlh_ssxx b on a.ssbh=b.pk and b.fbbj='一般')";
	
	//宿舍分配查询SQL
	StringBuffer QUERY_SSFP = new StringBuffer("select ssbh pk,rownum r,a.*,(select xqmc from dm_zju_xq b where a.xqdm=b.dm) xqmc from (")
	                                  .append("select a.*,(select xqdm from sslddmb b where a.lddm=b.lddm) xqdm,")
	                                  .append("(select xbxd from sslddmb b where a.lddm=b.lddm) xb,")
	                                  .append("(select ldmc from sslddmb b where a.lddm=b.lddm) ldmc,")
	                                  .append("(case when xydm='0202' then '团委' when xydm='0405' then '体育教学部' when xydm='0110' then '科研处' when xydm='0117' then '成人教育处' else (select xymc from view_njxyzybj b where a.xydm=b.xydm and rownum=1) end) xymc from (")
	                                  .append("select xydm,ssbh,cws,(select lddm from bjlh_ssxxb b where a.ssbh=b.lddm||b.cs||b.qsh) lddm,")
	                                  .append("(select cs from bjlh_ssxxb b where a.ssbh=b.lddm||b.cs||b.qsh) cs,")
	                                  .append("(select qsh from bjlh_ssxxb b where a.ssbh=b.lddm||b.cs||b.qsh) qsh from ssfpb a) a) a");
	
	//	=======================万恶的分割线（以下luojw）===================================
	/**
	 * 修改房源信息，同步床位信息表
	 * 
	 * @author luojw
	 */
	public boolean createCwxx() {
		DAO dao = DAO.getInstance();
		try {
			dao.runProcuder("{call update_bjlhcwxx()}", new String[] {});// 验证床位信息是否正确
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 获得无需维护下拉框值
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {
		String[] dm = null;
		String[] mc = null;
		if ("sfqk".equalsIgnoreCase(lx)) {
			dm = new String[] { "是", "否" };
			mc = new String[] { "是", "否" };
		} else if ("shlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "未审核", "已通过", "未通过" };
			mc = new String[] { "未审核", "已通过", "未通过" };
		} else if ("xblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "男", "女" };
			mc = new String[] { "男", "女" };
		} else if ("bjlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "一般", "保留" };
			mc = new String[] { "一般", "保留" };
		} else if ("xslx".equalsIgnoreCase(lx)) {
			dm = new String[] { "全日制", "团委生", "体优生", "研究生", "成教生" };
			mc = new String[] { "全日制", "团委生", "体优生", "研究生", "成教生" };
		} else if ("zzbj".equalsIgnoreCase(lx)) {
			dm = new String[] { "yes", "no" };
			mc = new String[] { "住宿中", "已退宿"};
		} else if ("fplx".equalsIgnoreCase(lx)) {
			dm = new String[] { "yes", "no" };
			mc = new String[] { "已分配(根据所属"+Base.YXPZXY_KEY+")", "未分配(不属于该"+Base.YXPZXY_KEY+")"};
		} else if ("fpbj".equalsIgnoreCase(lx)) {
			dm = new String[] { "qrz", TWDM, TYDM, KYDM, CJDM };
			mc = new String[] { "全日制", "团委", "体育教学部", "科研处", "成人教育处" };
		} else if ("errlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "lxbf", "cwcf", "ssfp", "twty", "xbyc" };
			mc = new String[] { "住宿学生类型与现有学生类型不符", "同一床位有多人住", "所住宿舍未分配", "同时为团委体优生", "学生性别与所属楼栋性别有异义" };
		}
		DAO dao = DAO.getInstance();
		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String tableName,
			String dm, String mc) {

		StringBuffer sql = new StringBuffer();
		sql.append("select '' dm, '----请选择-----'mc from dual union");
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc + " mc");
		sql.append(" from " + tableName);
		sql.append(" order by dm nulls first");
		DAO dao = DAO.getInstance();
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc" });
	}
	
	/**
	 * 得到部门代码
	 * 
	 * @author luojw
	 */
	public String getBmdm(String bmmc) {
		String bmdm = "";
		if ("团委".equalsIgnoreCase(bmmc) || "团委生".equalsIgnoreCase(bmmc)) {
			bmdm = TWDM;
		} else if ("体育教学部".equalsIgnoreCase(bmmc) || "体优生".equalsIgnoreCase(bmmc)) {
			bmdm = TYDM;
		} else if ("科研处".equalsIgnoreCase(bmmc) || "研究生".equalsIgnoreCase(bmmc)) {
			bmdm = KYDM;
		} else if ("成人教育处".equalsIgnoreCase(bmmc) || "成教生".equalsIgnoreCase(bmmc)) {
			bmdm = CJDM;
		}
		return bmdm;
	}
	
	/**
	 * 得到部门代码
	 * 
	 * @author luojw
	 */
	public String getXslx(String bmdm) {
		
		if (TWDM.equalsIgnoreCase(bmdm)) {
			bmdm = "团委生";
		} else if (TYDM.equalsIgnoreCase(bmdm)) {
			bmdm = "体优生";
		} else if (KYDM.equalsIgnoreCase(bmdm)) {
			bmdm = "研究生";
		} else if (CJDM.equalsIgnoreCase(bmdm)) {
			bmdm = "成教生";
		} else {
			bmdm = "全日制";
		}
		return bmdm;
	}
	
	/**
	 * 获得年级列表
	 * @param xslx(学生类型:研究生，成教生)
	 * @author luojw
	 */
	public List<HashMap<String, String>> getNjList(String lx) {
		String tableName = Base.isNull(lx) ? "view_qrz_njxyzybj" : "bjlh_fqrzbjb";
		lx = Base.isNull(lx) ? "全日制" : lx;
		StringBuffer sql = new StringBuffer();
		sql.append(" select '' dm, '----请选择-----'mc from dual union ");
		sql.append(" select distinct(to_char(nj)) dm,to_char(nj) mc from ");
		sql.append(tableName);
		sql.append(" where lx= ? order by dm nulls first");
		DAO dao = DAO.getInstance();
		return dao.getList(sql.toString(), new String[] { lx }, new String[] {
				"dm", "mc" });
	}
	
	/**
	 * 获得学院专业班级列表
	 * @param bmlx(部门类型:xy,zy,bj)
	 * @param xslx(学生类型:研究生，成教生)
	 * @param nj(年级)
	 * @param xydm(学院代码)
	 * @param zydm(专业代码)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXyList(String lx) {
		
		DAO dao = DAO.getInstance();
		
		String tableName = Base.isNull(lx) ? "view_qrz_njxyzybj"
				: "view_fqrz_njxyzybj";
		
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		StringBuffer sql = new StringBuffer();

		//sql.append(" select '' dm, '----请选择-----'mc from dual union");
		sql.append(" select dm,mc,lx from ");
		sql.append(" (select distinct(xydm) dm,xymc mc,'xy' lx from "+tableName);
		sql.append(" union select distinct(zydm) dm,zymc mc,'zy' lx from "+tableName);
		sql.append(" union select distinct(bjdm) dm,bjmc mc,'bj' lx from "+tableName +")");
		sql.append(" order by lx,dm");
		

		list = dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc", "lx" });
		
		return list;
	}
	
	/**
	 * 获得学院专业班级列表
	 * @param bmlx(部门类型:xy,zy,bj)
	 * @param xslx(学生类型:研究生，成教生)
	 * @param nj(年级)
	 * @param xydm(学院代码)
	 * @param zydm(专业代码)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXyZyBjList(String bmlx,
			String xslx, String nj, String xydm, String zydm) {

		nj = Base.isNull(nj) ? "%" : nj;
		xydm = Base.isNull(xydm) ? "%" : xydm;
		zydm = Base.isNull(zydm) ? "%" : zydm;

		String dm = "";
		String mc = "";
		
		String tableName = Base.isNull(xslx) ? "view_qrz_njxyzybj" : "view_fqrz_njxyzybj";
		xslx = Base.isNull(xslx) ? "全日制" : xslx;
		
		if ("xy".equalsIgnoreCase(bmlx)) {
			dm = "xydm";
			mc = "xymc";
			xydm = "%";
		} else if ("zy".equalsIgnoreCase(bmlx)) {
			dm = "zydm";
			mc = "zymc";
			zydm = "%";
		} else if ("bj".equalsIgnoreCase(bmlx)) {
			dm = "bjdm";
			mc = "bjmc";
		}

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		StringBuffer sql = new StringBuffer();
		String[] col = new String[] { xslx, nj, xydm, zydm };

		sql.append("select '' dm, '----请选择-----'mc,''jb from dual union");
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc +" mc,");
		sql.append("全日制".equalsIgnoreCase(xslx)?"jb":"''jb ");
		sql.append(" from " + tableName + " where ");
		sql.append(" lx = ? and nj like ? and xydm like ? and zydm like ?");
		sql.append(" order by jb desc,dm nulls first");
		DAO dao = DAO.getInstance();
		list = dao.getList(sql.toString(), col, new String[] { "dm", "mc" });

		return list;
	}
	
	/**
	 * 获得公寓管理相关信息
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * @author luojw
	 */
	public HashMap<String, String> getGyglInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}
	
	/**
	 * 获得校区楼栋列表
	 * @param lx(类型:xq,ld)
	 * @param xqdm(校区代码)
	 * @param lddm(楼栋代码)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXqLdList(String lx,
			String xqdm, String lddm) {
		xqdm = Base.isNull(xqdm) ? "%" : xqdm;
		lddm = Base.isNull(lddm) ? "%" : lddm;

		String dm = "";
		String mc = "";

		if ("ld".equalsIgnoreCase(lx)) {
			dm = "lddm";
			mc = "ldmc";
		} else if ("xq".equalsIgnoreCase(lx)) {
			dm = "xqdm";
			mc = "xqmc";
		} 

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		StringBuffer sql = new StringBuffer();
		String[] col = new String[] { xqdm, lddm };

		sql.append("select '' dm, '----请选择-----'mc from dual union");
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc +" mc");
		sql.append(" from view_bjlh_ldxx where ");
		sql.append(" xqdm like ? and lddm like ?");
		sql.append(" order by dm nulls first");
		DAO dao = DAO.getInstance();
		list = dao.getList(sql.toString(), col, new String[] { "dm", "mc" });
		//System.out.println(sql);
		return list;
	}
	
	/**
	 * 获得楼栋层数列表
	 * @param lddm(楼栋代码)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getCsList(String lddm) {
		DAO dao = DAO.getInstance();
		String sql = "select cs from view_bjlh_ldxx where lddm = ?";
		String cs = dao.getOneRs(sql, new String[] { lddm }, "cs");
		
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "");
		map.put("mc", "----请选择----");
		list.add(map);
		
		if(!Base.isNull(cs)){
			for (int i = 1; i <= Integer.parseInt(cs); i++) {
				map = new HashMap<String, String>();
				map.put("dm", String.valueOf(i));
				map.put("mc", "第" + String.valueOf(i) + "层");
				list.add(map);
			}
		}
		
		return list;
	}
	
	/**
	 * 获得楼栋寝室列表
	 * 
	 * @param lddm(楼栋代码)
	 * @param cs(层数)
	 * @author luojw
	 */
	public List<HashMap<String, String>> getQsList(String lddm, String cs,
			String fplx, String xydm,String tableName) {
		lddm = Base.isNull(lddm) ? "%" : lddm;
		cs = Base.isNull(cs) ? "%" : cs;

		StringBuffer sql = new StringBuffer();
		sql.append("select '' dm, '----请选择-----'mc from dual union ");
		sql.append("select qsh dm,qsh mc  from view_bjlh_ssxx a ");
		sql.append("where lddm like ? and cs like ? ");
		if("view_bjlh_ssxx".equalsIgnoreCase(tableName)){
			sql.append(" and fbbj <> '保留' ");
		}
		if (!Base.isNull(fplx)&&!Base.isNull(xydm)) {
			if ("yes".equalsIgnoreCase(fplx)) {
				sql.append(" and exists");
			} else {
				sql.append(" and not exists");
			}
			sql.append("(select 1 from ssfpb b where b.ssbh=a.pk and b.xydm = '"+xydm+"')");
		}
		
//		if("isFullQs".equalsIgnoreCase(tableName)){
//		sql.append(" and not exists (select 1 from (select a.ssbh, a.yzrs, a.cws,"); 
//		sql.append(" nvl(c.xlcws, 0) xlcws from (select a.ssbh, a.yzrs, b.cws");
//		sql.append(" from (select count(xh) yzrs, lddm || cs || qsh ssbh");
//		sql.append(" from bjlh_xszsxxb where zzbj = 'yes' group by lddm, cs, qsh) a,");
//		sql.append(" ssfpb b where a.ssbh = b.ssbh) a left join (select count(cwh) xlcws,"); 
//		sql.append(" lddm || cs || qsh ssbh from bjlh_xlcwb group by lddm, cs, qsh) c on a.ssbh ");
//		sql.append(" = c.ssbh) c where a.pk = c.ssbh and c.cws <= (c.yzrs + c.xlcws))");
//		}
		
		sql.append("order by dm nulls first");
		DAO dao = DAO.getInstance();
		//System.out.println(sql+lddm+cs);
		return dao.getList(sql.toString(), new String[] { lddm, cs },
				new String[] { "dm", "mc" });
	}

	/**
	 * 获得寝室床位列表
	 * @param lddm(楼栋代码)
	 * @param cs(层数)
	 * @param qsh(寝室号)
	 * 
	 * @author luojw
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getCwList(String lddm,
			String cs, String qsh,String xh) throws Exception {
		DAO dao = DAO.getInstance();
		String[] colList = new String[] { lddm, cs, qsh };
		String sql = "select cws from bjlh_ssxxb where lddm = ? and cs = ? and qsh = ?";
		String cws = dao.getOneRs(sql, colList, "cws");

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "");
		map.put("mc", "----请选择楼栋,层数,寝室----");
		list.add(map);
		if (!Base.isNull(cws) && !"0".equalsIgnoreCase(cws)) {
			sql = "select cwh from bjlh_xszsxxb where lddm = ? and cs = ? and qsh = ? and xh <> ? and zzbj = 'yes' ";
			sql+="union select cwh from bjlh_xlcwb where lddm = ? and cs = ? and qsh = ?";
			String[] yzcw = dao.getRs(sql, new String[] { lddm, cs, qsh, xh, lddm, cs, qsh }, "cwh");
			for (int i = 1; i <= Integer.parseInt(cws); i++) {
				boolean flag = true;
				if (yzcw != null && yzcw.length > 0) {
					for (int j = 0; j < yzcw.length; j++) {
						if (String.valueOf(i).equalsIgnoreCase(yzcw[j])) {
							flag = false;
							break;
						}
					}
				}
				if (flag) {
					map = new HashMap<String, String>();
					map.put("dm", String.valueOf(i));
					map.put("mc", String.valueOf(i) + "号床");
					list.add(map);
				}
			}
		}

		return list;
	}
	
	/**
	 * 判断某值是否存在
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @author luojw
	 */
	public Boolean isCz(String tableName, String pk, String pkValue) {
		boolean flag = false;
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) num from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(pk);
		sql.append("=?");
		String num = dao.getOneRs(sql.toString(), new String[] { pkValue },
				"num");
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 设置所需列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception 
	 */
	public  void setList(BjlhGyglForm myForm,HttpServletRequest request) throws Exception {
		
		String tableName = request.getParameter("tableName");
		String lx = myForm.getLx();
//		String nj = myForm.getNj();
		String xh = myForm.getXh();
		String xydm = myForm.getXydm();
//		String zydm = myForm.getZydm();
		String lddm = myForm.getLddm();
		String cs = myForm.getCs();
		String qsh = myForm.getQsh();
		String fplx = myForm.getFplx();
		String bmdm = getBmdm(lx);
		
		if("研究生".equalsIgnoreCase(lx) || "成教生".equalsIgnoreCase(lx)){
			lx = myForm.getLx();
		}else{
			lx = "";
		}
		
		if (TWDM.equalsIgnoreCase(bmdm)) {// 分配标记为团委
			xydm = "";
//			zydm = "";
		} else if (TYDM.equalsIgnoreCase(bmdm)) {// 分配标记为体育教学部
			xydm = "";
//			zydm = "";
		} else if (KYDM.equalsIgnoreCase(bmdm)) {// 分配标记为科研处
			xydm = "";
//			zydm = "";
		} else if (CJDM.equalsIgnoreCase(bmdm)) {// 分配标记为成人教育部
			xydm = "";
//			zydm = "";
		}
		
		if("view_bjlh_fpjg".equalsIgnoreCase(tableName)||"view_bjlh_tjjbxx".equalsIgnoreCase(tableName)){
			lx="";
		}
		
		List<HashMap<String, String>> njList = getNjList(lx);
		List<HashMap<String, String>> xyzylxList = getXyList(lx);
//		List<HashMap<String, String>> xyList = getXyZyBjList("xy", lx, nj, xydm, zydm);
//		List<HashMap<String, String>> zyList = getXyZyBjList("zy", lx, nj, xydm, zydm);
//		List<HashMap<String, String>> bjList = getXyZyBjList("bj", lx, nj, xydm, zydm);
		
		List<HashMap<String, String>> xyList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> zyList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "");
		map.put("mc", "----请选择-----");
		
		xyList.add(map);
		zyList.add(map);
		bjList.add(map);
		
		for (int i = 0; i < xyzylxList.size(); i++) {
			HashMap<String, String> map1 = xyzylxList.get(i);
			HashMap<String, String> map2 = new HashMap<String, String>();
			if ("xy".equalsIgnoreCase(map1.get("lx"))) {
				map2.put("dm", map1.get("dm"));
				map2.put("mc", map1.get("mc"));
				xyList.add(map2);
			}
			if ("zy".equalsIgnoreCase(map1.get("lx"))) {
				map2.put("dm", map1.get("dm"));
				map2.put("mc", map1.get("mc"));
				zyList.add(map2);
			}
			if ("bj".equalsIgnoreCase(map1.get("lx"))) {
				map2.put("dm", map1.get("dm"));
				map2.put("mc", map1.get("mc"));
				bjList.add(map2);
			}
		}
		
		List<HashMap<String, String>> xqList = getSelectList("dm_zju_xq", "dm", "xqmc");
		List<HashMap<String, String>> ldList = getSelectList("sslddmb", "lddm", "ldmc");
		List<HashMap<String, String>> csList = getCsList(lddm);
		
		if (TWDM.equalsIgnoreCase(bmdm)) {// 分配标记为团委
			xydm = TWDM;
		} else if (TYDM.equalsIgnoreCase(bmdm)) {// 分配标记为体育教学部
			xydm = TYDM;
		} else if (KYDM.equalsIgnoreCase(bmdm)) {// 分配标记为科研处
			xydm = KYDM;
		} else if (CJDM.equalsIgnoreCase(bmdm)) {// 分配标记为成人教育部
			xydm = CJDM;
		}
		
		List<HashMap<String, String>> qsList = getQsList(lddm, cs,fplx,xydm,tableName);
		List<HashMap<String, String>> cwList = getCwList(lddm, cs, qsh, xh);
		List<HashMap<String, String>> blList = getSelectList("bjlx");
		List<HashMap<String, String>> sfList = getSelectList("sfqk");
		List<HashMap<String, String>> xbList = getSelectList("xblx");
		List<HashMap<String, String>> xsList = getSelectList("xslx");
		List<HashMap<String, String>> zzList = getSelectList("zzbj");
		List<HashMap<String, String>> fpList = getSelectList("fplx");
		List<HashMap<String, String>> errList = getSelectList("errlx");
		
		request.setAttribute("njList", njList);
		request.setAttribute("xyList", xyList);// 学院列表
		request.setAttribute("zyList", zyList);// 专业列表
		request.setAttribute("bjList", bjList);// 班级列表
		request.setAttribute("xqList", xqList);// 校区列表
		request.setAttribute("ldList", ldList);// 楼栋列表
		request.setAttribute("csList", csList);// 层数列表
		request.setAttribute("qsList", qsList);// 寝室列表
		request.setAttribute("cwList", cwList);// 寝室列表
		request.setAttribute("blList", blList);// 保留列表
		request.setAttribute("sfList", sfList);//是否情况列表
		request.setAttribute("xbList", xbList);// 性别列表
		request.setAttribute("xsList", xsList);// 学生类型列表
		request.setAttribute("zzList", zzList);// 在住标记列表
		request.setAttribute("fpList", fpList);// 宿舍分配类型列表
		request.setAttribute("errList", errList);// 数据异常类型列表
	}
	
	/**
	 * 
	 * @param lddm
	 *            楼栋代码
	 * @return 获取宿舍划分中楼层列表
	 */
	public List<HashMap<String, String>> getSsFpCsList(String lddm) {
		String sql = "";
		sql = "select ''dm , '--请选择--'mc from dual union all select dm,'第'||mc||'层'  from"
				+ " (select distinct cs dm,cs mc from bjlh_ssxxb where lddm=? order by to_number(cs))";
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> csList = dao.getList(sql,
				new String[] { lddm }, new String[] { "dm", "mc" });
		return csList;
	} 
	
	//=======================万恶的分割线（以上luojw）===================================
	
	/**
	 * 未分配学生总人数（这里针对的是学生人数）
	 * @param xydm
	 * @param fpbj 全日制，团委，体育教学部，科研处，成人教育处 默认为全日制
	 * @return
	 */
	public String[] getCwFpZsDatas(String xydm, String fpbj) {
		String[] data = new String[] { "0", "0", "0" };
		List<String[]> rs = null;
		String sql = "";
		StringBuffer querry = new StringBuffer();
		if (TWDM.equalsIgnoreCase(fpbj)) {//分配记为团委
			sql = QUERY_TW_WFPXSZS ;
			sql += " group by xb";
		} else if (TYDM.equalsIgnoreCase(fpbj)) {//分配标记为体育教学部
			sql = QUERY_TY_WFPXSZS ;
			sql += " group by xb";
		} else if (KYDM.equalsIgnoreCase(fpbj)) {//分配标记为科研处
			sql = QUERY_KY_WFPXSZS ;
			sql += " group by xb";
		} else if (CJDM.equalsIgnoreCase(fpbj)) {//分配标记为成人教育部
			sql = QUERY_CJ_WFPXSZS ;
			sql += " group by xb";
		} else {//全日制本科生
			querry.append(Base.isNull(xydm) ? " where 1=1 " : " where xydm='"
					+ xydm + "' ");
			sql = "select xb,nvl(count(xh),'0') rs from view_xsjbxx a "
					+ querry.toString()
					+ " and not exists (select 1 from (select twsxh xh from bjlh_twxsb where sftws='是' union select tysxh xh from  bjlh_tyxsb where sftys='是') b where a.xh=b.xh)"
					+ " and not exists (select 1 from bjlh_xszsxxb c where a.xh=c.xh and c.lx='其他' and c.zzbj='yes') group by xb";
		}
		DAO dao = DAO.getInstance();
		rs = dao.rsToVator(sql, new String[] {}, new String[] { "xb", "rs" });
		int boy = 0;
		int girl = 0;
		if (rs != null) {
			for (int i = 0; i < rs.size(); i++) {
				String[] oneData = rs.get(i);
				if (oneData != null && oneData.length == 2) {
					if ("男".equalsIgnoreCase(oneData[0])) {//男生未分配总人数
						data[1] = oneData[1];
						boy = StringUtils.isNotNull(oneData[1]) ? Integer
								.parseInt(oneData[1]) : 0;
					} else if ("女".equalsIgnoreCase(oneData[0])) {//女生未分配总人数
						girl = StringUtils.isNotNull(oneData[1]) ? Integer
								.parseInt(oneData[1]) : 0;
						data[2] = oneData[1];
					}
				}
			}
		}
		//未分配总人数,男,女生之和
		data[0] = (boy + girl) > 0 ? String.valueOf(boy + girl) : "0";
		return data;
	}
	
	/**
	 * 未分配学生总人数（这里针对的是学生人数）
	 * @param xydm
	 * @param fpbj 全日制，团委，体育教学部，科研处，成人教育处 默认为全日制
	 * @return
	 */
	public String[] getCwFpZsData(String xydm, String zydm, String bjdm, String nj, String fpbj) {
		String[] data = new String[] { "0", "0", "0" };
		List<String[]> rs = null;
		String sql = "";
		StringBuffer querry = new StringBuffer();
		if (TWDM.equalsIgnoreCase(fpbj)) {//分配记为团委
			sql = QUERY_TW_WFPXSZS;
			sql += Base.isNull(xydm) ? "" : " and xydm = '" + xydm+ "' ";
			sql += Base.isNull(zydm) ? "" : " and zydm = '" + zydm+ "' ";
			sql += Base.isNull(bjdm) ? "" : " and bjdm = '" + bjdm+ "' ";
			sql += Base.isNull(nj) ? "" : " and nj = '" + nj+ "' ";
			sql += " group by xb";
		} else if (TYDM.equalsIgnoreCase(fpbj)) {//分配标记为体育教学部
			sql = QUERY_TY_WFPXSZS;
			sql += Base.isNull(xydm) ? "" : " and xydm = '" + xydm+ "' ";
			sql += Base.isNull(zydm) ? "" : " and zydm = '" + zydm+ "' ";
			sql += Base.isNull(bjdm) ? "" : " and bjdm = '" + bjdm+ "' ";
			sql += Base.isNull(nj) ? "" : " and nj = '" + nj+ "' ";
			sql += " group by xb";
		} else if (KYDM.equalsIgnoreCase(fpbj)) {//分配标记为科研处
			sql = QUERY_KY_WFPXSZS;
			sql += Base.isNull(xydm) ? "" : " and xydm = '" + xydm+ "' ";
			sql += Base.isNull(zydm) ? "" : " and zydm = '" + zydm+ "' ";
			sql += Base.isNull(bjdm) ? "" : " and bjdm = '" + bjdm+ "' ";
			sql += Base.isNull(nj) ? "" : " and nj = '" + nj+ "' ";
			sql += " group by xb";
		} else if (CJDM.equalsIgnoreCase(fpbj)) {//分配标记为成人教育部
			sql = QUERY_CJ_WFPXSZS;
			sql += Base.isNull(xydm) ? "" : " and xydm = '" + xydm+ "' ";
			sql += Base.isNull(zydm) ? "" : " and zydm = '" + zydm+ "' ";
			sql += Base.isNull(bjdm) ? "" : " and bjdm = '" + bjdm+ "' ";
			sql += Base.isNull(nj) ? "" : " and nj = '" + nj+ "' ";
			sql += " group by xb";
		} else {//全日制本科生
			querry.append(Base.isNull(xydm) ? "" : " and xydm='"
					+ xydm + "' ");
			querry.append(Base.isNull(zydm) ? "" : " and zydm='"
					+ zydm + "' ");
			querry.append(Base.isNull(bjdm) ? "" : " and bjdm='"
					+ bjdm + "' ");
			querry.append(Base.isNull(nj) ? "" : " and nj='"
					+ nj + "' ");
			sql = "select xb,nvl(count(xh),'0') rs from view_xsjbxx a where 1=1"
					+ querry.toString()
					+ " and not exists (select 1 from (select twsxh xh from bjlh_twxsb where sftws='是' union select tysxh xh from  bjlh_tyxsb where sftys='是') b where a.xh=b.xh)"
					+ " and not exists (select 1 from bjlh_xszsxxb c where a.xh=c.xh and c.lx='其他' and c.zzbj='yes') group by xb";
		}
		DAO dao = DAO.getInstance();
		rs = dao.rsToVator(sql, new String[] {}, new String[] { "xb", "rs" });
		int boy = 0;
		int girl = 0;
		if (rs != null) {
			for (int i = 0; i < rs.size(); i++) {
				String[] oneData = rs.get(i);
				if (oneData != null && oneData.length == 2) {
					if ("男".equalsIgnoreCase(oneData[0])) {//男生未分配总人数
						data[1] = oneData[1];
						boy = StringUtils.isNotNull(oneData[1]) ? Integer
								.parseInt(oneData[1]) : 0;
					} else if ("女".equalsIgnoreCase(oneData[0])) {//女生未分配总人数
						girl = StringUtils.isNotNull(oneData[1]) ? Integer
								.parseInt(oneData[1]) : 0;
						data[2] = oneData[1];
					}
				}
			}
		}
		//未分配总人数,男,女生之和
		data[0] = (boy + girl) > 0 ? String.valueOf(boy + girl) : "0";
		return data;
	}
	
	/**
	 * 已划分床位总数（这里针对的是床位总数） 
	 * @param xydm
	 * @param fpbj 全日制，团委，体育教学部，科研处，成人教育处  默认为全日制
	 * @return
	 */
	public String[] getSsFpYhfcws(String xydm, String fpbj) {
		StringBuffer query = new StringBuffer();
		String[] data = new String[] { "0", "0", "0", "0" };
		if (TWDM.equalsIgnoreCase(fpbj)) {// 分配标记为团委
			query.append(" and xydm='");
			query.append(TWDM);
			query.append("'");
		} else if (TYDM.equalsIgnoreCase(fpbj)) {// 分配标记为体育教学部
			query.append(" and xydm='");
			query.append(TYDM);
			query.append("'");
		} else if (KYDM.equalsIgnoreCase(fpbj)) {// 分配标记为科研处
			query.append(" and xydm='");
			query.append(KYDM);
			query.append("'");
		} else if (CJDM.equalsIgnoreCase(fpbj)) {// 分配标记为成人教育部
			query.append(" and xydm='");
			query.append(CJDM);
			query.append("'");
		} else {//全日制本科生
			if (StringUtils.isNotNull(xydm)) {//学院代码不为空时
				query.append(" and xydm='");
				query.append(xydm);
				query.append("'");
			} else {//学院未空时,只输出全日制本科生数据
				query.append(" and xydm not in ('");
				query.append(TWDM);
				query.append("','");
				query.append(TYDM);
				query.append("','");
				query.append(KYDM);
				query.append("','");
				query.append(CJDM);
				query.append("') ");
			}
		}
		query.append(" group by xbxd");
		DAO dao = DAO.getInstance();
		List<String[]> rs = dao.rsToVator(QUERY_CWZS + query.toString(),
				new String[] {}, new String[] { "xbxd", "rs" });
		int boy = 0;
		int girl = 0;
		int hh = 0;
		if (rs != null) {
			for (int i = 0; i < rs.size(); i++) {
				String[] oneData = rs.get(i);
				if (oneData != null && oneData.length == 2) {
					if ("男".equalsIgnoreCase(oneData[0])) {//男生宿舍床位数
						data[1] = oneData[1];
						boy = StringUtils.isNotNull(oneData[1]) ? Integer
								.parseInt(oneData[1]) : 0;
					} else if ("女".equalsIgnoreCase(oneData[0])) {//女生宿舍床位数
						girl = StringUtils.isNotNull(oneData[1]) ? Integer
								.parseInt(oneData[1]) : 0;
						data[2] = oneData[1];
					} else if ("混合".equalsIgnoreCase(oneData[0])) {//混合宿舍床位数
						hh = StringUtils.isNotNull(oneData[1]) ? Integer
								.parseInt(oneData[1]) : 0;
						data[3] = oneData[1];
					}
				}
			}
		}
		//已划分床位总数
		data[0] = (boy + girl + hh) > 0 ? String.valueOf(boy + girl + hh) : "0";
		return data;
	}
	 
	/**
	 * 未划分宿舍列表
	 * 
	 * @param xqdm
	 * @param xb
	 * @param lddm
	 * @param cs
	 * @return
	 */
	public List<HashMap<String, String>> getSsFpSsXxList(String xqdm,
			String xb, String lddm, String cs) {
		List<HashMap<String, String>> ssList = null;
		StringBuffer querry = new StringBuffer();
		querry.append(Base.isNull(lddm) ? " and 1=2 " : " and lddm = '" + lddm
				+ "' ");
		querry
				.append(Base.isNull(cs) ? " and 1=1 " : " and cs = '" + cs
						+ "' ");
		querry.append(Base.isNull(xqdm) ? " and 1=1 " : " and xqdm = '" + xqdm
				+ "' ");
		querry.append(Base.isNull(xb) ? " and 1=1 " : " and xbxd = '" + xb
				+ "' ");
		querry.append(" order by xqdm,lddm,cs,qsh");
		DAO dao = DAO.getInstance();
		ssList = dao.getList(QUERY_WHFSS + querry.toString(), new String[] {},
				new String[] { "dm", "mc" });
		return ssList;
	}

	/**
	 * 返回已划分宿舍信息列表
	 * 
	 * @param lddm
	 *            楼栋代码
	 * @param xqdm
	 *            校区代码
	 * @param xydm
	 *            学院代码
	 * @param bjdm
	 *            班级代码
	 * @return
	 */
	public List<HashMap<String, String>> getSsFpFpSsXxList(String lddm,
			String cs, String xydm, String fpbj) {
		xydm = TWDM.equalsIgnoreCase(fpbj) ? TWDM : (TYDM
				.equalsIgnoreCase(fpbj) ? TYDM
				: (KYDM.equalsIgnoreCase(fpbj) ? KYDM : (CJDM
						.equalsIgnoreCase(fpbj) ? CJDM : xydm)));
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(lddm) ? " and 1=2 " : " and lddm = '" + lddm
				+ "' ");
		querry.append(Base.isNull(xydm) ? " and 1=1 " : " and xydm = '" + xydm
				+ "' ");
		querry
				.append(Base.isNull(cs) ? " and 1=1 " : " and cs = '" + cs
						+ "' ");
		if ("qrz".equalsIgnoreCase(fpbj) && StringUtils.isNull(xydm)) {
			querry.append(" and xydm not in ('");
			querry.append(TWDM);
			querry.append("','");
			querry.append(TYDM);
			querry.append("','");
			querry.append(KYDM);
			querry.append("','");
			querry.append(CJDM);
			querry.append("') ");
		}
		querry.append(" order by xydm,lddm,cs,qsh");
		DAO dao = DAO.getInstance();
		return dao.getList(QUERY_YHFSS + querry.toString(), new String[] {},
				new String[] { "dm", "mc" });
	}
	
	/**
	 * 获得校区楼栋列表
	 * @param lx(类型:xq,ld)
	 * @param xqdm(校区代码)
	 * @param lddm(楼栋代码)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getxbXqLdList(String lx,
			String xqdm, String lddm, String xb) {
		xqdm = Base.isNull(xqdm) ? "%" : xqdm;
		lddm = Base.isNull(lddm) ? "%" : lddm;

		String dm = "";
		String mc = "";

		if ("ld".equalsIgnoreCase(lx)) {
			dm = "lddm";
			mc = "ldmc";
		} else if ("xq".equalsIgnoreCase(lx)) {
			dm = "xqdm";
			mc = "xqmc";
		} 

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		StringBuffer sql = new StringBuffer();
		String[] col = new String[] { xqdm, lddm,xb };

		sql.append("select '' dm, '----请选择-----'mc from dual union");
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc +" mc");
		sql.append(" from view_bjlh_ldxx where ");
		sql.append(" xqdm like ? and lddm like ?");
		sql.append(" and xbxd like ?");
		sql.append(" order by dm nulls first");
		DAO dao = DAO.getInstance();
		list = dao.getList(sql.toString(), col, new String[] { "dm", "mc" });

		return list;
	}
	
	/**
	 * 已分配床位总数（这里针对的是床位总数） 
	 * @param xydm
	 * @param fpbj 全日制，团委，体育教学部，科研处，成人教育处  默认为全日制
	 * @return
	 */
	public String[] getCwFpYhfcws(String xydm, String zydm, String bjdm, String nj, String lddm, String cs, String fpbj) {
		StringBuffer query = new StringBuffer();
		StringBuffer queryXl = new StringBuffer();
		String[] data = new String[] { "0", "0", "0", "0" };
		if (TWDM.equalsIgnoreCase(fpbj)) {// 分配标记为团委
			query.append(" and lx='团委生'");
			
			queryXl.append(" and xydm='");
			queryXl.append(TWDM);
			queryXl.append("'");
		} else if (TYDM.equalsIgnoreCase(fpbj)) {// 分配标记为体育教学部
			query.append(" and lx='体优生'");
			
			queryXl.append(" and xydm='");
			queryXl.append(TYDM);
			queryXl.append("'");
		} else if (KYDM.equalsIgnoreCase(fpbj)) {// 分配标记为科研处
			query.append(" and lx='研究生'");
			
			queryXl.append(" and xydm='");
			queryXl.append(KYDM);
			queryXl.append("'");
		} else if (CJDM.equalsIgnoreCase(fpbj)) {// 分配标记为成人教育部
			query.append(" and lx='成教生'");
			
			queryXl.append(" and xydm='");
			queryXl.append(CJDM);
			queryXl.append("'");
		} else {//全日制本科生
			if (StringUtils.isNotNull(xydm)) {//学院代码不为空时
				query.append(" and xydm='");
				query.append(xydm);
				query.append("'");
				
				queryXl.append(" and xydm='");
				queryXl.append(xydm);
				queryXl.append("'");
			} else {
				queryXl.append(" and xydm not in ('");
				queryXl.append(TWDM);
				queryXl.append("','");
				queryXl.append(TYDM);
				queryXl.append("','");
				queryXl.append(KYDM);
				queryXl.append("','");
				queryXl.append(CJDM);
				queryXl.append("') ");
			}
			query.append(" and lx='全日制'");
		}
		
		query.append(Base.isNull(lddm) ? "" : " and lddm = '" + lddm
				+ "' ");
		query.append(Base.isNull(cs) ? "" : " and cs = '" + cs
				+ "' ");
		query.append(Base.isNull(zydm) ? "" : " and zydm = '" + zydm
				+ "' ");
		query.append(Base.isNull(bjdm) ? "" : " and bjdm = '" + bjdm
				+ "' ");
		query.append(Base.isNull(nj) ? "" : " and nj = '" + nj
				+ "' ");
		
		queryXl.append(Base.isNull(lddm) ? "" : " and lddm = '" + lddm
				+ "' ");
		queryXl.append(Base.isNull(cs) ? "" : " and cs = '" + cs
				+ "' ");
		
		query.append(" group by xb");
		DAO dao = DAO.getInstance();
		List<String[]> rs = dao.rsToVator(QUERY_FPCWZS + query.toString(),
				new String[] {}, new String[] { "xb", "rs" });
		String rsxl = dao.getOneRs(QUERY_FPXLCWZS + queryXl.toString(),
				new String[] {}, "rs");
		int boy = 0;
		int girl = 0;
		if (rs != null) {
			for (int i = 0; i < rs.size(); i++) {
				String[] oneData = rs.get(i);
				if (oneData != null && oneData.length == 2) {
					if ("男".equalsIgnoreCase(oneData[0])) {//男生宿舍床位数
						data[1] = oneData[1];
						boy = StringUtils.isNotNull(oneData[1]) ? Integer
								.parseInt(oneData[1]) : 0;
					} else if ("女".equalsIgnoreCase(oneData[0])) {//女生宿舍床位数
						girl = StringUtils.isNotNull(oneData[1]) ? Integer
								.parseInt(oneData[1]) : 0;
						data[2] = oneData[1];
					}
				}
			}
		}
		data[3] = rsxl;
		//已划分床位总数
		data[0] = (boy + girl) > 0 ? String.valueOf(boy + girl) : "0";
		return data;
	}
	
	/**
	 * 未分配床位总数（这里针对的是床位总数） 
	 * @param xydm
	 * @param fpbj 全日制，团委，体育教学部，科研处，成人教育处  默认为全日制
	 * @return
	 */
	public String[] getCwFpWhfcws(String xydm, String lddm, String cs, String fpbj) {
		StringBuffer query = new StringBuffer();
		String[] data = new String[] { "0", "0", "0", "0" };
		if (TWDM.equalsIgnoreCase(fpbj)) {// 分配标记为团委
			query.append(" and xydm='");
			query.append(TWDM);
			query.append("'");
		} else if (TYDM.equalsIgnoreCase(fpbj)) {// 分配标记为体育教学部
			query.append(" and xydm='");
			query.append(TYDM);
			query.append("'");
		} else if (KYDM.equalsIgnoreCase(fpbj)) {// 分配标记为科研处
			query.append(" and xydm='");
			query.append(KYDM);
			query.append("'");
		} else if (CJDM.equalsIgnoreCase(fpbj)) {// 分配标记为成人教育部
			query.append(" and xydm='");
			query.append(CJDM);
			query.append("'");
		} else {//全日制本科生
			if (StringUtils.isNotNull(xydm)) {//学院代码不为空时
				query.append(" and xydm='");
				query.append(xydm);
				query.append("'");
			} else {//学院未空时,只输出全日制本科生数据
				query.append(" and xydm not in ('");
				query.append(TWDM);
				query.append("','");
				query.append(TYDM);
				query.append("','");
				query.append(KYDM);
				query.append("','");
				query.append(CJDM);
				query.append("') ");
			}
		}
		
		query.append(Base.isNull(lddm) ? "" : " and lddm = '" + lddm
				+ "' ");
		query.append(Base.isNull(cs) ? "" : " and cs = '" + cs
				+ "' ");
		
		query.append(" group by xbxd");
		DAO dao = DAO.getInstance();
		List<String[]> rs = dao.rsToVator(QUERY_WFPCWZS + query.toString(),
				new String[] {}, new String[] { "xbxd", "rs" });
		int boy = 0;
		int girl = 0;
		int hh = 0;
		if (rs != null) {
			for (int i = 0; i < rs.size(); i++) {
				String[] oneData = rs.get(i);
				if (oneData != null && oneData.length == 2) {
					if ("男".equalsIgnoreCase(oneData[0])) {//男生宿舍床位数
						data[1] = oneData[1];
						boy = StringUtils.isNotNull(oneData[1]) ? Integer
								.parseInt(oneData[1]) : 0;
					} else if ("女".equalsIgnoreCase(oneData[0])) {//女生宿舍床位数
						girl = StringUtils.isNotNull(oneData[1]) ? Integer
								.parseInt(oneData[1]) : 0;
						data[2] = oneData[1];
					} else if ("混合".equalsIgnoreCase(oneData[0])) {//混合宿舍床位数
						hh = StringUtils.isNotNull(oneData[1]) ? Integer
								.parseInt(oneData[1]) : 0;
						data[3] = oneData[1];
					}
				}
			}
		}
		//已划分床位总数
		data[0] = (boy + girl + hh) > 0 ? String.valueOf(boy + girl + hh) : "0";
		return data;
	}
	
	/**
	 * 未分配床位列表
	 * 
	 * @param xqdm
	 * @param xb
	 * @param lddm
	 * @param cs
	 * @return
	 */
	public List<HashMap<String, String>> getCwFpCwXxList(String xqdm,
			String xb, String lddm, String cs, String fplx) {
		List<HashMap<String, String>> ssList = null;
		StringBuffer querry = new StringBuffer();
		DAO dao = DAO.getInstance();
		//String fpdm = getBmdm(fplx);
		if (!"qrz".equalsIgnoreCase(fplx)) {
			querry.append(" and c.xydm='"+fplx+"'");
		} else {//学院未空时,只输出全日制本科生数据
			querry.append(" and xydm not in ('");
			querry.append(TWDM);
			querry.append("','");
			querry.append(TYDM);
			querry.append("','");
			querry.append(KYDM);
			querry.append("','");
			querry.append(CJDM);
			querry.append("') ");
		}
		
		querry.append(Base.isNull(lddm) ? " and 1=2 " : " and b.lddm = '" + lddm
				+ "' ");
		querry.append(Base.isNull(cs) ? " and 1=1 " : " and b.cs = '" + cs
				+ "' ");
		querry.append(Base.isNull(xqdm) ? " and 1=1 " : " and b.xqdm = '" + xqdm
				+ "' ");
		querry.append(Base.isNull(xb) ? " and 1=1 " : " and b.xbxd = '" + xb
				+ "' ");
		querry.append(" order by b.xqdm,b.lddm,b.cs,b.qsh,a.cwh");
		ssList = dao.getList(QUERY_WFPCW + querry.toString(), new String[] {},
				new String[] { "dm", "mc" });
		return ssList;
	}
	
	/**
	 * 未分配学生列表
	 * 
	 * @param xqdm
	 * @param xb
	 * @param lddm
	 * @param cs
	 * @return
	 */
	public List<HashMap<String, String>> getCwFpXsXxList(String xydm,
			String zydm, String bjdm, String nj, String xb, String fplx) {
		List<HashMap<String, String>> ssList = null;
		StringBuffer querry = new StringBuffer();
		
		querry.append(" and lx='"+getXslx(fplx)+"'");
		
		querry.append(Base.isNull(xydm) ? " and 1=2 " : " and xydm = '" + xydm
				+ "' ");
		querry.append(Base.isNull(zydm) ? " and 1=1 " : " and zydm = '" + zydm
				+ "' ");
		querry.append(Base.isNull(bjdm) ? " and 1=1 " : " and bjdm = '" + bjdm
				+ "' ");
		querry.append(Base.isNull(nj) ? " and 1=1 " : " and nj = '" + nj
				+ "' ");
		querry.append(Base.isNull(xb) || "混合".equalsIgnoreCase(xb) ? " and 1=1 " : " and xb = '" + xb
				+ "' ");
		querry.append(" order by xydm,zydm,bjdm,xb,xh");
		DAO dao = DAO.getInstance();
		ssList = dao.getList(QUERY_WFPXS + querry.toString(), new String[] {},
				new String[] { "dm", "mc" });
		return ssList;
	}
	
	/**
	 * 已分配学生列表
	 * 
	 * @param xqdm
	 * @param xb
	 * @param lddm
	 * @param cs
	 * @return
	 */
	public List<HashMap<String, String>> getCwFpYfpXsXxList(String xydm,
			String zydm, String bjdm, String nj, String xb, String lddm,
			String cs, String fplx) {
		List<HashMap<String, String>> ssList = null;
		StringBuffer querry = new StringBuffer();
		DAO dao = DAO.getInstance();
		querry.append(" and a.lx='"+getXslx(fplx)+"'");
		
		querry.append(Base.isNull(xydm) ? " and 1=2 " : " and b.xydm = '" + xydm
				+ "' ");
		querry.append(Base.isNull(zydm) ? " and 1=1 " : " and b.zydm = '" + zydm
				+ "' ");
		querry.append(Base.isNull(bjdm) ? " and 1=1 " : " and b.bjdm = '" + bjdm
				+ "' ");
		querry.append(Base.isNull(nj) ? " and 1=1 " : " and b.nj = '" + nj
				+ "' ");
		querry.append(Base.isNull(lddm) ? " and 1=1 " : " and a.lddm = '" + lddm
				+ "' ");
		querry.append(Base.isNull(cs) ? " and 1=1 " : " and a.cs = '" + cs
				+ "' ");
		querry.append(Base.isNull(xb) || "混合".equalsIgnoreCase(xb) ? " and 1=1 " : " and b.xb = '" + xb
				+ "' ");
		querry.append(" ORDER BY a.lddm,a.cs,a.qsh,a.cwh");
		ssList = dao.getList(QUERY_YFPXS + querry.toString(), new String[] {},
				new String[] { "dm", "mc" });
		return ssList;
	}
}

