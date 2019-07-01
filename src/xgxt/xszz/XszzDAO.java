package xgxt.xszz;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import common.Globals;
import common.XszzXmdm;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.xszz.comm.XszzCommService;
import xgxt.xszz.commonN05.XszzCommonN05ActionForm;

public class XszzDAO extends CommonQueryDAO {

	/**
	 * 获得学生资助相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXszzListInfo(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"xq", "xb", "szbm" };
		String[] queryLikeList = new String[] { "xh", "xm", "zgh" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();

		other_query = Base.isNull(other_query) ? "" : other_query;

		if (!Base.isNull(query)) {
			query += " " + other_query;
		} else {
			query = other_query;
		}

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}

	/**
	 * 获得学生资助相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getXszzInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}
	
	/**
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * @param msg(显示信息)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWhList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();

		return dao.getWhList(tableName, dm, mc, msg, pk, pkValue);
	}

	/**
	 * 删除所上传文件
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public boolean fileDel(String tableName, String dzzd, String pk,
			String pkValue) throws Exception {
		DAO dao = new DAO();

		boolean flag = dao.fileDel(tableName, dzzd, pk, pkValue);
		
		return flag;
	}

	/**
	 * 获得系统当前时间
	 * 
	 * @author luojw
	 */
	public String getNowTime(String lx) {
		
		DAO dao = DAO.getInstance();
		
		String now = dao.getNowTime(lx);
		
		return now;
	}

	/**
	 * 获得指定表的指定字段
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		
		DAO dao = DAO.getInstance();

		String value = dao.getOneValue(tableName, dm, pk, pkValue);
		
		return value;
	}
	
	/**
	 * 批量提交
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String[] sql)
			throws Exception {

		DAO dao = new DAO();
		
		boolean flag = dao.saveArrDate(sql);
		
		return flag;
	}
	
	/**
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * @param msg(显示信息)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXszzList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();
			
		List<HashMap<String, String>> list = dao.getWhList(tableName, dm, mc, msg, pk, pkValue);
		
		return list ;
	}
	
	/**
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * @param msg(显示信息)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXszzxmList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc + " mc");
		sql.append(" ,mrxm,xmmc from " + tableName +" where 1=1 ");
		if (!Base.isNull(pk)) {
			sql.append(" and " + pk + " = '" + pkValue + "' ");
		}
		sql.append(" order by mrxm desc,xmmc,dm nulls first ");
		return dao.getList(sql.toString(), new String[] {}, new String[] { "dm",
				"mc" });
	}
	
	/**
	 * 获得需审核下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * @param msg(显示信息)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getShxmList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc + " mc");
		sql.append(" from " + tableName +" where 1=1 ");
		if (!Base.isNull(pk)) {
			sql.append(" and " + pk + " = '" + pkValue + "' ");
		}
		sql.append(" and shjb <> '无需审核' ");
		sql.append(" order by dm nulls first");
		return dao.getList(sql.toString(), new String[] {}, new String[] { "dm",
				"mc" });
	}
	
	/**
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * @param msg(显示信息)
	 * @param pk(主键)
	 * @param bsf(标识符)
	 * @param pkValue(主键值)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXszzLikeList(String tableName,
			String dm, String mc, String msg, String pk,String bsf, String pkValue) {

		DAO dao = DAO.getInstance();
		
		List<HashMap<String, String>> list = dao.getWhList(tableName, dm, mc, msg, pk, bsf, pkValue);
		
		return list ;
	}
	
	/**
	 * 获得无需维护下拉框值
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("xblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "男", "女" };
			mc = new String[] { "男", "女" };
		} else if ("sflx".equalsIgnoreCase(lx)) {
			dm = new String[] { "是", "否" };
			mc = new String[] { "是", "否" };
		} else if ("shlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "未审核", "通过", "不通过" };
			mc = new String[] { "未审核", "通过", "不通过" };
		} else if ("shjb".equalsIgnoreCase(lx)) {
			dm = new String[] { "无需审核", "一级审核", "两级审核", "三级审核" };
			mc = new String[] { "无需审核", "一级审核", "两级审核", "三级审核" };
		} else if ("rsjb".equalsIgnoreCase(lx)) {
			dm = new String[] { "不需要", "需要" };
			mc = new String[] { "不需要", "需要" };
		} else if ("kzjb".equalsIgnoreCase(lx)) {
			dm = new String[] { "学院", "专业", "班级" };
			mc = new String[] { "学院", "专业", "班级" };
		} else if ("szsflx".equalsIgnoreCase(lx)) {
			dm = new String[] { "无限制", "是" };
			mc = new String[] { "无限制", "是" };
		} else if ("sqzq".equalsIgnoreCase(lx)) {
			dm = new String[] { "无周期", "学年", "年度", "学期" };
			mc = new String[] { "无限制", "学年", "年度", "学期" };
		} else if ("sfje".equalsIgnoreCase(lx)) {
			dm = new String[] { "需要", "不需要" };
			mc = new String[] { "需要", "不需要" };
		} else if ("sffj".equalsIgnoreCase(lx)) {
			dm = new String[] { "分级", "不分级" };
			mc = new String[] { "分级", "不分级" };
		} else if ("jelx".equalsIgnoreCase(lx)) {
			dm = new String[] { "确定值", "区间" };
			mc = new String[] { "确定值", "区间" };
		} else if ("kgzt".equalsIgnoreCase(lx)) {
			dm = new String[] { "开放申请", "关闭申请", "项目关闭" };
			mc = new String[] { "开放申请", "关闭申请", "项目关闭" };
		} else if ("pdsj".equalsIgnoreCase(lx)) {// 评定时间
			dm = new String[] { "本次", "前次" };
			mc = new String[] { "本次", "前次" };
		} else if ("jtcyzd".equalsIgnoreCase(lx)) {
			String xxdm = Base.xxdm;
			dm = getJtcyzd(xxdm);
			mc = getJtcyzd(xxdm);
		} else if ("tjlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "", ">", ">=", "=", "<=", "<" };
			mc = new String[] { "-----请选择-----", ">", ">=", "=", "<=", "<" };
		} else if ("zhftj".equalsIgnoreCase(lx)) {
			dm = new String[] { "", ">", "=", "<" };
			mc = new String[] { "", ">", "=", "<" };
		} else if ("xmlb".equalsIgnoreCase(lx)) {
			dm = new String[] { "奖学金", "荣誉称号","助学金", "困难补助", "其他" };
			mc = new String[] { "奖学金", "荣誉称号","助学金", "困难补助", "其他" };
		}else if ("xmlb_pj".equalsIgnoreCase(lx)) {
			dm = new String[] { "奖学金", "荣誉称号" };
			mc = new String[] { "奖学金", "荣誉称号" };
		}else if ("xmlb_zz".equalsIgnoreCase(lx)) {
			dm = new String[] {  "助学金", "困难补助", "其他" };
			mc = new String[] {  "助学金", "困难补助", "其他" };
		}

		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * 获得家庭成员维护标题
	 * 
	 * @author luojw
	 */
	private String[] getJtcyzd(String xxdm) {

		String[] title = null;

		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {
			title = new String[] { "", "姓名", "年龄", "关系", "工作（学习）单位", "职业",
					"联系电话", "所在邮编", "年收入", "月收入", "健康状况" };
		} else if (Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)) {
			title = new String[] { "", "姓名", "年龄", "关系", "工作（学习）单位", "职业",
					"联系电话", "年收入", "年支出", "健康状况" };
		} else if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {
			title = new String[] { "", "姓名", "年龄", "关系", "工作（学习）单位", "职业",
					 "年收入", "健康状况" };
		} else {
			title = new String[] { "", "姓名", "年龄", "关系", "工作（学习）单位", "职业",
					"联系电话", "年收入", "健康状况" };
		}
		
		return title;
	}
	
	/**
	 * 获得已经通过审核该级别困难生的学院人数（海南大学）
	 * 
	 * @author luojw
	 */
	public String getJjknsRs_Hndx(String xn, String xydm, String knsjb,
			String shzd,String xh) {
		
		DAO dao = DAO.getInstance();

		StringBuilder sql = new 	StringBuilder ();
		sql.append("select count(xh) num from view_hddx_jjknssq a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xydm = ? ");
		sql.append("and a.knsjb = ? ");
		sql.append("and "+shzd+" = '通过' ");
		sql.append("and a.xh <> ? ");
		
		String[] inputValue = new String[]{xn,xydm,knsjb,xh};
		
		String num = dao.getOneRs(sql.toString(), inputValue, "num");
		
		return num;
	}
	
	/**
	 * 获得指定表的字段
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] getTableZd(String tableName) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct zd from (select lower(COLUMN_NAME) zd ");
		sql.append(",length(lower(COLUMN_NAME)) cd ");
		sql.append("from user_tab_cols where table_name ");
		sql.append("in (upper('" + tableName + "'), upper('" + tableName
				+ "'))) order by cd");

		String[] zd = dao.getRs(sql.toString(), new String[] {}, "zd");

		return zd;
	}
	
	/**
	 * 获得班级人数
	 * 
	 * @author luojw
	 */
	public String getBjrs(HashMap<String, String> map) {

		DAO dao = DAO.getInstance();

		// 学号
		String xh = map.get("xh");

		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from view_xsjbxx a ");
		sql.append("where a.bjdm = ");
		sql.append("(select b.bjdm from view_xsjbxx b where b.xh = ?) ");

		String[] inputValue = new String[] { xh };
		String num = dao.getOneRs(sql.toString(), inputValue, "num");

		return num;

	}

	/**
	 * 设置moedel值
	 * 
	 * @author luojw
	 */
	public void setModelValue(XszzTyForm model) {

		// 年级
		String nj = model.getQueryequals_nj();
		// 学院
		String xydm = model.getQueryequals_xydm();
		// 专业
		String zydm = model.getQueryequals_zydm();
		// 班级
		String bjdm = model.getQueryequals_bjdm();
		// 年度
		String nd = model.getQueryequals_nd();
		// 月份
		String yf = model.getQueryequals_yf();
		// 补助类型
		String bzlx = model.getQueryequals_bzlx();
		// 学号
		String xh = model.getQuerylike_xh();
		// 姓名
		String xm = model.getQuerylike_xm();

		model.setNj(nj);
		model.setXydm(xydm);
		model.setZydm(zydm);
		model.setBjdm(bjdm);
		model.setNd(nd);
		model.setYf(yf);
		model.setBzlx(bzlx);
		model.setXh(xh);
		model.setXm(xm);
		
	}
	
	/**
	 * 获得学生历年资助信息
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getLnXszzList(String[] xmb,
			String[] xmdm, String[] xmmc, String[] sqzq, String[] shjb,
			String xh) {

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = null;

		if (xmb != null && xmb.length > 0) {

			StringBuilder sql = new StringBuilder();

			for (int i = 0; i < xmb.length; i++) {
				if (i != 0) {
					sql.append(" union all ");
				}
				sql.append(" select '" + xmmc[i] + "' xmmc,a.xmdm,");
				sql.append(" '" + sqzq[i] + "' sqzq,");
				sql.append("(select b.xqdm from xqdzb b where a.xq = b.xqdm) xqmc,");
				sql.append(" a.xn,a.xq,a.nd,a.sqsj,a.xmzzjb,a.xmzzje from ");
				sql.append(xmb[i]);
				sql.append(" a where a.xh = '" + xh + "' ");
				sql.append(" and a.xmdm = '" + xmdm[i] + "' ");
				if ("一级审核".equalsIgnoreCase(shjb[i])) {
					sql.append(" and a.shzt1 = '通过' ");
				} else if ("两级审核".equalsIgnoreCase(shjb[i])) {
					sql.append(" and a.shzt2 = '通过' ");
				} else if ("三级审核".equalsIgnoreCase(shjb[i])) {
					sql.append(" and a.shzt3 = '通过' ");
				}
			}
			
			String[] outputValue = new String[] { "xmdm", "xmmc", "sqzq", "xn",
					"xq", "xqmc", "nd", "sqsj", "xmzzjb","xmzzje" };
			list = dao.getList(sql.toString(), new String[] {}, outputValue);
		}

		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				// 项目代码
				String dm = map.get("xmdm");
				// 申请周期
				String zq = map.get("sqzq");
				// 学年
				String xn = map.get("xn");
				// 学期
				String xq = map.get("xqmc");
				// 年度
				String nd = map.get("nd");
				//项目级别
				String xmzzjb = map.get("xmzzjb");
				// 项目金额
				String xmzzje = map.get("xmzzje");
				
				if (!XszzXmdm.XSZZ_JTQKDC.equalsIgnoreCase(dm)
						&& !XszzXmdm.XSZZ_QTXX.equalsIgnoreCase(dm)) {
					
					String zzsj = "无申请周期";
				
					if ("学年".equalsIgnoreCase(zq)) {
						zzsj = xn + "学年";
					} else if ("学期".equalsIgnoreCase(zq)) {
						zzsj = xn + "学年" + xq + "名称";
					} else if ("年度".equalsIgnoreCase(zq)) {
						zzsj = nd + "年度";
					}
					
					if (Base.isNull(xmzzje)) {
						xmzzje = "不涉及金额";
					}
					if (Base.isNull(xmzzjb)) {
						xmzzjb = "不涉及级别";
					}
					map.put("xmzzjb", xmzzjb);
					map.put("xmzzje", xmzzje);
					map.put("zzsj", zzsj);
					
					resultList.add(map);
				}
			}
		}
		
		return resultList;

	}
	
	/**
	 * 判断是否困难生（N32）
	 * 
	 * @author luojw
	 */
	public Boolean isKns(HashMap<String, String> map) {

		DAO dao = DAO.getInstance();

		boolean flag = false;

		// 学号
		String xh = map.get("xh");
		// 学年
		String xn = map.get("xn");
		// 学期
		String xq = map.get("xq");
		// 年度
		String nd = map.get("nd");
		// 评奖学年
		String pjxn = map.get("pjxn");
		// 评奖学期
		String pjxq = map.get("pjxq");
		// 评奖年度
		String pjnd = map.get("pjnd");
		// 困难生项目代码
		String xmdm = XszzXmdm.XSZZ_KNS;
		// 困难生评奖周期
		String sqzq = map.get("sqzq");
		// 困难生审核级别
		String shjb = map.get("shjb");

		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xszz_shztb where 1 = 1 ");
		sql.append(" and xmdm = '" + xmdm + "' ");
		sql.append(" and xh = '" + xh + "'");

		sql.append("学年".equalsIgnoreCase(sqzq) ? " and xn = '" + xn + "'" : "");
		sql.append("学期".equalsIgnoreCase(sqzq) ? " and xn = '" + xn + "'" : "");
		sql.append("学期".equalsIgnoreCase(sqzq) ? " and xq = '" + xq + "'" : "");
		sql.append("年度".equalsIgnoreCase(sqzq) ? " and nd = '" + nd + "'" : "");

		sql.append("一级审核".equalsIgnoreCase(shjb) ? " and shzt1 = '通过'" : "");
		sql.append("两级审核".equalsIgnoreCase(shjb) ? " and shzt2 = '通过'" : "");
		sql.append("三级审核".equalsIgnoreCase(shjb) ? " and shzt3 = '通过'" : "");

		sql.append("and xmzzjb not like '不%' ");
//		sql.append("and xmzzjb not like '非%' ");
		
		String num = dao.getOneRs(sql.toString(), new String[] {}, "num");

		if (!Base.isNull(num) && !"0".equalsIgnoreCase(num)) {
			flag = true;
		}

		return flag;
	}
	// ======================以上made by 伟大的luo===============================
	
	/**
	 * 贷款年限全部初始化
	 * @return
	 * @throws Exception 
	 */
	public boolean dknxCsh() throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "delete from zgdzdx_xsdknxb";
		
		boolean result = dao.runUpdate(sql, new String[] {});
		
		if (result) {
			sql = "insert into zgdzdx_xsdknxb(bjdm) select bjdm from view_njxyzybj group by bjdm";
			result = dao.runUpdate(sql, new String[] {});
		}
		
		return result;
	}
	
	/**
	 * 贷款年限部分初始化
	 * @param bjdm
	 * @return
	 * @throws Exception
	 */
	public boolean dknxCsh(String[] bjdm) throws Exception {
		DAO dao = DAO.getInstance();
		boolean result = false;
		
		String[] sql = new String[bjdm.length];
		StringBuilder delSql = new StringBuilder();
		
		delSql.append("delete from zgdzdx_xsdknxb where ");
		
		for (int i = 0 ; i < bjdm.length ; i++) {
			delSql.append("bjdm=?");
			
			if ( i != bjdm.length-1) {
				delSql.append(" or ");
			}
			sql[i] = "insert into zgdzdx_xsdknxb(bjdm,dknx,dkkssj) values ('"+bjdm[i]+"','0','')";
			
		}
		result = dao.runUpdate(delSql.toString(), bjdm);
		if (result) {
			int[] checkResult = dao.runBatch(sql);
			result = dao.checkBatch(checkResult);
		}
		
		return result;
	}
	
	
	/**
	 * 根据资助项目代码获取资助项目详细信息
	 * 供DWR调用
	 * @author qph
	 * @param xmdm
	 * @return
	 */
	public HashMap<String,String> getXszzInfoByXmdm(String xmdm) {
		DAO dao = DAO.getInstance();
		String sql = "select * from xszz_zzxmb where xmdm=?";
		
		return dao.getMapNotOut(sql, new String[] {xmdm});
		
	}
	
	/**
	 * 根据项目类别获取项目信息
	 * @param xmlb
	 * @return
	 */
	public List<HashMap<String,String>>getXmxxByRssz(String xmlb,String query){
		DAO dao = DAO.getInstance();
		String sql=" select xmdm dm,xmmc mc from xszz_zzxmb where xmlb like ? and rskz='需要' and kgzt<>'项目关闭' "+query;
		return dao.getList(sql, new String[]{xmlb}, new String[]{"dm","mc"});
	}
	
	
	/**
	 * 根据项目类别获取项目信息
	 * @param xmlb
	 * @return
	 */
	public List<HashMap<String,String>>getXmdmByLb(String query){
		DAO dao = DAO.getInstance();
		String sql=" select xmdm dm,xmmc mc,xmlb lb from xszz_zzxmb "+query+" and rskz='需要' and kgzt<>'项目关闭' ";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc","lb"});
	}
	
	/**
	 * @describe 删除所上传文件
	 * @author luojw
	 * @throws Exception
	 */
	public boolean filePathDel(String xh,String sqsj)
			throws Exception {
		XszzCommService service = new XszzCommService();
		boolean flag = false;
		String pk = "xh||sqsj";
		String pkValue = xh+sqsj;
		String realTable = "xszz_knsb";
		try {
		if (!Base.isNull(pkValue)) {
			service.fileDel(realTable, "scdz", pk, pkValue);
			flag= true;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
