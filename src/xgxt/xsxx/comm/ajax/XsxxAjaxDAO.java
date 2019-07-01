package xgxt.xsxx.comm.ajax;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.xsxx.comm.XsxxCommDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_Ajax_DAO类
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

public class XsxxAjaxDAO extends XsxxCommDAO {

	/**
	 * 根据模块类型取得页签展示内容
	 * 
	 * @author 伟大的骆
	 */
	public String getJspName(String mklx) {

		StringBuilder sql = new StringBuilder();
		sql.append("select b.jspname from xg_xsxx_xspz a, xg_xsxx_xxypzb b ");
		sql.append(" where a.ename = b.ename and a.sfxs = b.xslxdm ");
		sql.append(" and a.ename = ? ");

		DAO dao = DAO.getInstance();

		String jspname = dao.getOneRs(sql.toString(), new String[] { mklx },
				"jspname");

		return jspname;
	}
	
	/**
	 * 获得家庭信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getJtxxInfo(String xh) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select lxdh1,yb,jtszd,jjzk,");
		sql.append(" jtcy1_xm,jtcy1_gx,jtcy1_zy,jtcy1_gzdz,");
		sql.append(" jtcy1_zw,jtcy1_sfzh,jtcy1_lxdh1,jtcy1_lxdh2,");
		sql.append(" jtcy1_nl,jtcy1_mz,jtcy1_zzmm,");
		sql.append(" (select b.mzmc from mzdmb b where a.jtcy1_mz = b.mzdm) jtcy1_mzmc,");
		sql.append(" (select b.zzmmmc from zzmmdmb b where a.jtcy1_zzmm = b.zzmmdm) jtcy1_zzmmmc,");
		
		sql.append(" jtcy2_xm,jtcy2_gx,jtcy2_zy,jtcy2_gzdz,");
		sql.append(" jtcy2_zw,jtcy2_sfzh,jtcy2_lxdh1,jtcy2_lxdh2,");
		sql.append(" jtcy2_nl,jtcy2_mz,jtcy2_zzmm,");
		sql.append(" (select b.mzmc from mzdmb b where a.jtcy2_mz = b.mzdm) jtcy2_mzmc,");
		sql.append(" (select b.zzmmmc from zzmmdmb b where a.jtcy2_zzmm = b.zzmmdm) jtcy2_zzmmmc,");
		
		sql.append(" jtcy3_xm,jtcy3_gx,jtcy3_zy,jtcy3_gzdz,");
		sql.append(" jtcy3_zw,jtcy3_sfzh,jtcy3_lxdh1,jtcy3_lxdh2, ");
		sql.append(" jtcy3_nl,jtcy3_mz,jtcy3_zzmm,");
		sql.append(" (select b.mzmc from mzdmb b where a.jtcy3_mz = b.mzdm) jtcy3_mzmc,");
		sql.append(" (select b.zzmmmc from zzmmdmb b where a.jtcy3_zzmm = b.zzmmdm) jtcy3_zzmmmc");
		
		sql.append(" from xsfzxxb a where xh = ? ");

		DAO dao = DAO.getInstance();

		HashMap<String, String> map = dao.getMap(sql.toString(),
				new String[] { xh }, new String[] { "lxdh1", "yb", "jtszd",
						"jjzk", "jtcy1_xm", "jtcy1_gx", "jtcy1_zy",
						"jtcy1_gzdz", "jtcy1_zw", "jtcy1_sfzh", "jtcy1_lxdh1",
						"jtcy1_lxdh2", "jtcy1_nl", "jtcy1_mz", "jtcy1_zzmm",
						"jtcy2_xm", "jtcy2_gx", "jtcy2_zy", "jtcy2_gzdz",
						"jtcy2_zw", "jtcy2_sfzh", "jtcy2_lxdh1", "jtcy2_lxdh2",
						"jtcy2_nl", "jtcy2_mz", "jtcy2_zzmm", "jtcy3_xm",
						"jtcy3_gx", "jtcy3_zy", "jtcy3_gzdz", "jtcy3_zw",
						"jtcy3_sfzh", "jtcy3_lxdh1", "jtcy3_lxdh2", "jtcy3_nl",
						"jtcy3_mz", "jtcy3_zzmm", "jtcy1_mzmc", "jtcy2_mzmc",
						"jtcy3_mzmc", "jtcy1_zzmmmc", "jtcy2_zzmmmc",
						"jtcy3_zzmmmc" });

		return map;
	}
	
	/**
	 * 获得党员信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getDyxxInfo(String xh) {

		// 党员信息表
		StringBuilder sql = new StringBuilder();
		sql.append(" select xn,xqmc,nd,rdsj,bz ");
		sql.append(" from view_dyxx where xh = ? ");
		sql.append(" and rownum = 1 order by xn desc ");
		
		DAO dao = DAO.getInstance();

		String[] colList = new String[] { "xn", "xqmc", "nd", "rdsj", "bz" };
		
		HashMap<String, String> map = dao.getMap(sql.toString(),
				new String[] { xh }, colList);

		return map;
	}
	
	/**
	 * 获得预备党员信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getYbdyInfo(String xh) {

		// 党员信息表
		StringBuilder sql = new StringBuilder();
		sql.append(" select xn,nd,kssj,jssj,bz,zzzt, ");
		sql.append(" (select xqmc from xqdzb b where a.xq = b.xqdm) xqmc ");
		sql.append(" from ybdyxxb a where xh = ? ");
		sql.append(" and rownum = 1 order by xn desc ");
		
		DAO dao = DAO.getInstance();

		String[] colList = new String[] { "xn", "xqmc", "nd", "kssj", "jssj",
				"bz", "zzzt" };
		
		HashMap<String, String> map = dao.getMap(sql.toString(),
				new String[] { xh }, colList);

		return map;
	}
	
	/**
	 * 获得评奖评优信息
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getPjpyInfo(String xh, String jspName) {

		// 评奖信息表
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.pjxn,a.pjxq,a.xqmc,a.pjnd,a.sqsj,a.xmje,a.xmmc ");
		sql.append(" from xg_view_pjpy_sqjg a where exists ");
		sql.append(" (select 1 from (select splc, max(xh) maxLv ");
		sql.append(" from xg_xtwh_spbz group by splc) b ");
		sql.append(" where a.lcid = b.splc and a.shjb = b.maxlv) ");
		sql.append(" and a.shzt = '通过' and a.xh=?");

		DAO dao = DAO.getInstance();

		String[] colList = new String[] { "pjxn", "pjxq", "xqmc", "pjnd",
				"sqsj", "xmje", "xmmc" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xh }, colList);

		return list;
	}
	
	/**
	 * 获得学生成绩信息
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXscjInfo(String xh, String jspName) {

		// 学生成绩列表
		StringBuilder sql = new StringBuilder();
		sql.append(" select xn,xq,kcmc,cj,");
		sql.append(" (select xqmc from xqdzb b where a.xq=b.xqdm) xqmc ");
		sql.append(" from cjb a where xh = ? ");
		sql.append(" order by xn,xq ");

		DAO dao = DAO.getInstance();

		String[] colList = new String[] { "xn", "xq", "xqmc", "kcmc", "cj" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xh }, colList);

		return list;
	}
	
	/**
	 * 获得勤工岗位信息
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getQggwInfo(String xh, String jspName) {

		// 勤工岗位列表
		StringBuilder sql = new StringBuilder();
		sql.append(" select gwdm,substr(sqsj,0,8) sqsj,substr(gwsbsj,0,8) gwsbsj");
		sql.append(" from view_xsgwxx a where xh = ? and xxyj='通过'");
		sql.append(" order by gwsbsj,sqsj");

		DAO dao = DAO.getInstance();

		String[] colList = new String[] { "gwdm", "sqsj", "gwsbsj" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xh }, colList);

		return list;
	}
	
	/**
	 * 获得酬金发放信息
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getCjffInfo(String xh, String jspName) {

		// 学生成绩列表
		StringBuilder sql = new StringBuilder();
		sql.append(" select gwdm, sqsj, xn, xqmc, xq, nd, yf, cjje, ffsj");
		sql.append(" from view_xscjff a where xh = ? and xxsh='通过'");
		sql.append(" order by gwdm, sqsj, xn, xq, nd, yf");

		DAO dao = DAO.getInstance();

		String[] colList = new String[] { "gwdm", "sqsj", "xn", "xqmc", "xq",
				"nd", "yf", "cjje", "ffsj" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xh }, colList);

		return list;
	}
	
	/**
	 * 获得学生住宿信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getXszsInfo(String xh) {

		// 入住信息
		StringBuilder sql = new StringBuilder();
		sql.append(" select ldmc,cs,qsh,cwh,qsdh,sfbz ");
		sql.append(" from xg_view_gygl_xszsxx where xh = ? ");
		sql.append(" and rownum = 1 ");
		
		DAO dao = DAO.getInstance();

		String[] colList = new String[] { "ldmc", "cs", "qsh", "cwh", "qsdh",
				"sfbz" };
		
		HashMap<String, String> map = dao.getMap(sql.toString(),
				new String[] { xh }, colList);

		return map;
	}
	
	/**
	 * 根据录入获取部门列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBmOption(XsxxAjaxModel model,
			User user) {

		DAO dao = DAO.getInstance();

		String bmjb = model.getBmjb();// 部门级别
		String bmmc = model.getBmmc();// 部门名称
		String nj = model.getNj();// 年级
		String xydm = model.getXydm();// 院系
		String zydm = model.getZydm();// 专业
		String bjdm = model.getBjdm();// 班级
		
		String dm = "";//代码
		String mc = "";//名称
		
		if ("nj".equalsIgnoreCase(bmjb)) {
			dm = "nj";
			mc = "nj";
		} else if ("xydm".equalsIgnoreCase(bmjb)) {
			dm = "xydm";
			mc = "xymc";
		} else if ("zydm".equalsIgnoreCase(bmjb)) {
			dm = "zydm";
			mc = "zymc";
		} else if ("bjdm".equalsIgnoreCase(bmjb)) {
			dm = "bjdm";
			mc = "bjmc";
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select dm,mc from (");
		sql.append(" select distinct ");
		sql.append(dm + " dm,");
		sql.append(mc + " mc ");
		
		sql.append(" from view_njxyzybj a ");
		sql.append(" where 1 = 1 ");
		
		SearchService searchService = new SearchService();
		// 不控制的身份
		HashMap<String, String> notCtrlStatus = new HashMap<String, String>();
		notCtrlStatus.put("gygly", "yes");
		user.setNotCtrlStatus(notCtrlStatus);
		
		String searchTjByUser = searchService.getSearchTjByUser("a", user);

		if(!"nj".equalsIgnoreCase(bmjb)){
			sql.append(Base.isNull(nj) ? "" : " and  nj = '" + nj + "' ");
		}
		
		if(!"xydm".equalsIgnoreCase(bmjb)){
			sql.append(Base.isNull(xydm) ? "" : " and  xydm = '" + xydm + "' ");
		}
		
		if(!"zydm".equalsIgnoreCase(bmjb)){
			sql.append(Base.isNull(zydm) ? "" : " and  zydm = '" + zydm + "' ");
		}
		
		if(!"bjdm".equalsIgnoreCase(bmjb)){
			sql.append(Base.isNull(bjdm) ? "" : " and  bjdm = '" + bjdm + "' ");
		}
		sql.append(Base.isNull(bmmc) ? "" : " and  "+mc+" like '" + bmmc + "%' ");
		sql.append(searchTjByUser);
		sql.append(")");
		sql.append(" where rownum <=10 ");
		sql.append(" order by dm ");

		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc" });
	}
	
	/**
	 * 判断是否存在
	 * 
	 * @author 伟大的骆
	 */
	public Boolean checkIsExists(XsxxAjaxModel model) {

		// 主键
		String[] pk = model.getPk();
		// 主键值
		String pkValue = model.getPkValue();
		// 表名
		String tableName = model.getTableName();

		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from ");
		sql.append(tableName);
		sql.append(" where 1 = 1 ");

		if (pk != null && pk.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < pk.length; i++) {

				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(pk[i]);
				sql.append(" = ");
				sql.append("'" + pkValue + "'");
			}
			sql.append(")");
		}

		DAO dao = DAO.getInstance();

		String num = dao.getOneRs(sql.toString(), new String[] {}, "num");

		boolean flag = false;

		if (!Base.isNull(num) && !"0".equalsIgnoreCase(num)) {
			flag = true;
		}

		return flag;
	}
	
	/**
	 * 检测用户名
	 * 
	 * @author 伟大的骆
	 */
	public String checkUserName(String userName) {

		DAO dao = DAO.getInstance();

		String xhSql = "select xh userName from view_xsjbxx where xh = ?";
		String yhSql = "select yhm userName from yhb where yhm = ?";
		String realName = "";
		String userType = "";

		realName = dao.getOneRs(xhSql, new String[] { userName }, "userName");

		if (Base.isNull(realName)) {
			realName = dao.getOneRs(yhSql, new String[] { userName },
					"userName");

			if (!Base.isNull(realName)) {
				userType = "teacher";
			}
		} else {
			userType = "student";
		}

		return userType;
	}
	
	// ===============以下made by 今天中五百万======================
	/**
	 * 获取学生资助详细信息列表
	 * @author qlj
	 */
	public List<HashMap<String, String>> getZzxxInfo(String xh,String lx) {

		// 党员信息表
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xmdm,a.xmmc,a.sqsj,a.shzt,a.xmzzjb,a.xmzzje,nvl(sqzq,'无')sqzq from( ");
		sql.append(" select a.xmdm,a.xmmc,b.sqsj,xn,xq,nd,case when shjb='一级审核' and shzt1='通过' then '通过' ");
		sql.append("  when shjb='二级审核' and shzt2='通过' then '通过' ");
		sql.append("  when shjb='三级审核' and shzt3='通过' then '通过' ");
		sql.append("  when shjb='无需审核'  then '通过' end shzt,xn||xq||nd sqzq, ");
		sql.append("  nvl(xmzzjb,'无级别')xmzzjb,nvl(xmzzje,'不涉及金额')xmzzje ");
		sql.append("  from xszz_zzxmb a left join xszz_shztb b on a.xmdm=b.xmdm where xh=? )a ");
		sql.append("  where shzt='通过' ");
		
		DAO dao = DAO.getInstance();

		String[] colList = new String[] { "xmmc", "sqsj", "sqzq", "xmzzjb", "xmzzje"};
		
		return dao.getList(sql.toString(), new String[]{xh}, colList);
	}
	
	/**
	 * 获取违纪处分详细信息列表
	 * @author qlj
	 */
	public List<HashMap<String, String>> getWjxxInfo(String xh,String lx) {

		// 党员信息表
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xn, (select xqmc from xqdzb where a.xq=xqdm)xqmc,a.xq, a.nd, b.cflbmc, ");
		sql.append(" c.cfyymc, nvl(a.cfsj,'不详')cfsj, nvl(a.wjsj,'不详')wjsj, ");
		sql.append(" case when a.cxjg is null then '未撤消' else a.cxjg end cxjg ");
		sql.append("  from wjcfb a  left join cflbdmb b on a.cflb = b.cflbdm ");
		sql.append("  left join cfyydmb c on a.cfyy = c.cfyydm where 1=1 and xh=? ");
		sql.append(" and xxsh='通过' ");
		
		DAO dao = DAO.getInstance();

		String[] colList = new String[] { "xn", "xqmc", "nd", "cflbmc", "cfyymc","cfsj","wjsj","cxjg"};
		
		return dao.getList(sql.toString(), new String[]{xh}, colList);
	}
	

	/**
	 * 获取心理测试结果信息
	 * @author qlj
	 */
	public List<HashMap<String, String>> getXlcsInfo(String xh,String lx) {

		// 党员信息表
		StringBuilder sql = new StringBuilder();
		sql.append("  select xh,cssj,xlcsxmmc,xlcsjgmc,'xlcs' xslx from view_xlcsjg where xh=?   ");
		
		DAO dao = DAO.getInstance();

		String[] colList = new String[] { "cssj", "xlcsxmmc", "xlcsjgmc","xslx"};
		
		return dao.getList(sql.toString(), new String[]{xh}, colList);
	}
	
	/**
	 * 获取心理测试结果信息
	 * @author qlj
	 */
	public List<HashMap<String, String>> getTsxsInfo(String xh,String lx) {

		// 党员信息表
		StringBuilder sql = new StringBuilder();
		sql.append("  select xytbgxcs,zxqjzysj,tbgxxslbmc,'tsxs' xslx from view_xytbgxxsxx where xh=?   ");
		
		DAO dao = DAO.getInstance();

		String[] colList = new String[] { "xytbgxcs", "zxqjzysj", "tbgxxslbmc","xslx"};
		
		return dao.getList(sql.toString(), new String[]{xh}, colList);
	}
	
	/**
	 * 获取就业管理(毕业生信息)
	 * @author qlj
	 */
	public HashMap<String, String> getBysInfo(String xh,String lx) {

		// 党员信息表
		StringBuilder sql = new StringBuilder();
		sql.append("  select rxnf,bynf  from view_jy_bysxxb where xh=? and rownum=1  ");
		
		DAO dao = DAO.getInstance();

		String[] colList = new String[] { "rxnf", "bynf"};
		
		return dao.getMap(sql.toString(), new String[]{xh}, colList);
	}
	
	/**
	 * 获取就业管理(就业信息)
	 * @author qlj
	 */
	public HashMap<String, String> getJyxyInfo(String xh,String lx) {

		// 党员信息表
		StringBuilder sql = new StringBuilder();
		sql.append("  select dwmc,dwxzmc,zgdwmc,bdkssj,bdjssj  from view_jy_jyxy where xh=? and rownum=1  ");
		
		DAO dao = DAO.getInstance();

		String[] colList = new String[] { "dwmc", "dwxzmc", "zgdwmc", "bdkssj", "bdjssj"};
		
		return dao.getMap(sql.toString(), new String[]{xh}, colList);
	}
	
	/**
	 * 获取家庭情况信息
	 * @param xh
	 * @return
	 */
	public HashMap<String,String>getJdqkInfo(String xh){
		
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from jtqkdcb x ");
		sql.append(" where exists (select 1 ");
		sql.append(" from (select xh, max(sqsj) sqsj from jtqkdcb group by xh) y ");
		sql.append(" where x.xh = y.xh and x.sqsj = y.sqsj) and xh=? ");
		
		return dao.getMap(sql.toString(), new String[]{xh}, new String[]{
			"sfdb","sfdq","lszn","kzzd1","kzzd2"
				,"kzzd3","kzzd4","kzzd5","kzzd6","kzzd7"});
	}
	// =======================end==========================
	
	/**
	 * 获得学生成绩列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXscjList(String xh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.xn,a.xq,a.kcmc,a.cj,a.kcxz, ");
		sql.append("(select b.xqmc from xqdzb b where a.xq=b.xqdm) xqmc ");
		sql.append("from cjb a where a.xh=?");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xh }, new String[] { "xn", "xqmc", "kcmc",
						"kcxz", "cj" });

		return list;
	}
}
