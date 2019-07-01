package xgxt.pjpy.zjcm.xnjxj;

import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.zjcm.ZhszcpDAO;
import xgxt.pjpy.zjcm.rych.RychModel;
import xgxt.pjpy.zjcm.xwjxj.XwjxjDAO;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;

public class XnjxjDAO extends ZhszcpDAO {

	/**
	 * 获得综合分列表
	 * @author luo
	 */
	public ArrayList<String[]> getZhfList(String tableName, XnjxjModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		DAO dao = new DAO();

		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
		String xn = DealString.toGBK(model.getXn());
		// 学期
		String xq = DealString.toGBK(model.getXq());
		String xqmc = dao.getOneRs("select xqmc from xqdzb where xqdm = ?",
				new String[] { xq }, "xqmc");
		// 学号
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";

		StringBuffer query = new StringBuffer();

		query.append(" where zhf is not null");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='" + xydm
				+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='" + zydm
				+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='" + bjdm
				+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='" + nj + "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='" + xn + "'");
		query.append(Base.isNull(xqmc) ? " and 1=1" : " and xq='" + xqmc + "'");
		query.append(" and xh like ?");
		query.append(" and xm like ?");
		String[] inPutList = new String[] { xh, xm};

		StringBuffer sql = new StringBuffer();

		sql.append("select * from (select t.*,rownum r from (select t.*,b.jxjmc from (select xn,xq,xh,xm,xb,bjdm,");
		sql.append("bjmc,nvl(zyf,0) zyf,zhf,zhpm from view_zjcm_zhf ");
		sql.append(query + " order by bjdm,to_number(zhpm),zyf desc nulls last) t ");
		sql.append("left join (select t.xh,max(ltrim(sys_connect_by_path((t.jxjmc) || '', '、'), ");
		sql.append("'、')) jxjmc from (select a.xh,c.jxjmc, ");
		sql.append("row_number() over(partition by b.xh order by a.xh) pno, ");
		sql.append("row_number() over(partition by b.xh order by a.xh) - 1 sno ");
		sql.append("from zjcm_jxjsq a, zjcm_jxjsq b, jxjdmb c where a.xh = b.xh ");
		sql.append("and a.jxjdm = c.jxjdm and a.xn = '"+xn+"' and a.xq = '"+xq+"' and c.jxjlb = '校' ");
		sql.append("group by a.xh, b.xh, c.jxjmc) t start with pno = 1 ");
		sql.append("connect by prior pno = sno and prior t.xh = t.xh ");
		sql.append("group by t.xh order by t.xh) b on t.xh = b.xh order by to_number(zhpm)) t )");
		sql.append("where r > "+ model.getPages().getStart()+ " and r <= ");
		sql.append(model.getPages().getStart() + model.getPages().getPageSize());

		ArrayList<String[]> list =  new ArrayList<String[]>();
		list.addAll(dao.rsToVator(sql.toString(), inPutList, colList));
		// System.out.println(sql);

//		List<HashMap<String, String>> mapList = dao.getList(sql.toString(),
//				inPutList, colList);
//		ArrayList<String[]> list = new ArrayList<String[]>();
//		if (mapList != null && mapList.size() > 0) {
//			int pm = 0;
//			for (int i = 0; i < mapList.size(); i++) {
//				HashMap<String, String> map1 = mapList.get(i);
//				if (i > 0) {
//					HashMap<String, String> map2 = mapList.get(i - 1);
//					if (map2.get("pm").equalsIgnoreCase(map1.get("pm"))) {
//						if (!map2.get("zyf").equalsIgnoreCase(map1.get("zyf"))) {
//							pm++;
//							map1.put("newpm", String.valueOf(Integer
//									.parseInt(map1.get("pm"))
//									+ pm));
//						} else {
//							map1.put("newpm", String.valueOf(Integer
//									.parseInt(map1.get("pm"))
//									+ pm));
//						}
//					} else {
//						pm = 0;
//						map1.put("newpm", String.valueOf(Integer.parseInt(map1
//								.get("pm"))));
//					}
//					String[] arr = new String[] { map1.get("xh"),
//							map1.get("xm"), map1.get("xb"), map1.get("bjmc"),
//							map1.get("xn"), map1.get("xq"), map1.get("zyf"),
//							map1.get("zhf"), map1.get("newpm") };
//					list.add(arr);
//				} else {
//					String[] arr = new String[] { map1.get("xh"),
//							map1.get("xm"), map1.get("xb"), map1.get("bjmc"),
//							map1.get("xn"), map1.get("xq"), map1.get("zyf"),
//							map1.get("zhf"), map1.get("pm") };
//					list.add(arr);
//				}
//			}
//		}
//		end = end > list.size() ? list.size() : end;
//		ArrayList<String[]> arrlist = new ArrayList<String[]>();
//		for (int i = start; i < end; i++) {
//			arrlist.add(list.get(i));
//		}
		// 分页
		StringBuffer sql2 = new StringBuffer();
		sql2.append("select count(*) num from view_zjcm_zhf " + query);

		model.getPages().setMaxRecord(
				Integer.parseInt(dao
						.getOneRs(sql2.toString(), inPutList, "num")));

		return list;
	}

	/**
	 * 
	 * 判断是否测评小组成员
	 */
	public boolean isCpzCy(String xh, String xydm) {
		DAO dao = new DAO();
		boolean flg = false;
		String xn = Base.getJxjsqxn();
		String sql = "select count(*) num from view_bjgbxx a where exists (select 1 "
				+ "from zjcm_cpz b where a.bjgbdm = b.zwdm and xydm = ? and xn = ?) and xh = ?";
		String count = dao.getOneRs(sql, new String[] { xydm, xn, xh }, "num");
		if (!"0".equalsIgnoreCase(count)) {
			flg = true;
		}
		return flg;
	}

	/**
	 * 判断登陆时间是否在所设置的时间范围内
	 */
	public boolean inTime(String xydm) {
		DAO dao = new DAO();
		boolean flg = false;
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();

		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) num from (select distinct(lrkssj),lrjssj,sbkssj,sbjssj from ");
		sql.append("zjcm_cpz where xn=? and xq=? and xydm=? ) where sbkssj <= to_char(sysdate, 'yyyymmdd') ");
		sql.append("and sbjssj >= to_char(sysdate, 'yyyymmdd')");

		String num = dao.getOneRs(sql.toString(),
				new String[] { xn, xq, xydm }, "num");

		if (!"0".equalsIgnoreCase(num)) {
			flg = true;
		}
		return flg;
	}

	/**
	 * 获得学生基本信息
	 */
	public static HashMap<String, String> getStuInfo(String xh) {
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc" };
		return commonQueryOne("view_xsjbxx", colList, "xh", xh);
	}

	/**
	 * 获得学期列表
	 */
	public List<HashMap<String, String>> getXqList() {
		return commonQueryforList("xqdzb", "", new String[] {}, new String[] {
				"xqmc", "xqdm" }, "");
	}

	/**
	 * 获得学期名称
	 * 
	 * @throws Exception
	 */
	public String getXqmc(String xqdm) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select xqmc from xqdzb where xqdm=?",
				new String[] { xqdm }, "xqmc");
	}
	
	/**
	 * 获得学期代码
	 * 
	 * @throws Exception
	 */
	public String getXqdm(String xqmc) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select xqdm from xqdzb where xqmc=?",
				new String[] { xqmc }, "xqdm");
	}
	
	/**
	 * 获得申报者基本信息
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> getSbzXx(String xh, String xn, String xq) {
		DAO dao = DAO.getInstance();
		return dao.getMap(
				"select * from view_zjcm_zhf where xh=? and xn=? and xq=?",
				new String[] { xh, xn, xq }, new String[] { "xn", "xq", "xh",
						"xm", "xb", "nj", "xymc", "zymc", "bjmc", "zhf", "dyf",
						"zyf", "tyf", "nlf" });

	}
	
	/**
	 * 获得申报者旷课次数
	 * 
	 * @throws Exception
	 */
	public String getKkcs(String xh, String xn, String xq) {
		DAO dao = DAO.getInstance();
		String sql = "select sum(wjcs) wjcs from pjpy_xfjs_xsjljcb where wjlxdm = '001' and xh = ? and xn = ? and xq = ?";
		return dao.getOneRs(sql, new String[] { xh, xn, getXqdm(xq) }, "wjcs");

	}

	/**
	 * 获得英语等级考试列表
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYyList() {
		DAO dao = DAO.getInstance();
		return dao
				.getList(
						"select djksdm dm, djksmc mc from djksdmb where djksmc like '%CET%' or djksmc like '%英语%'",
						new String[] {}, new String[] { "dm", "mc" });

	}

	/**
	 * 获得奖学金列表
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList(String jxjlb) {
		DAO dao = DAO.getInstance();
		if(Base.isNull(jxjlb)){
			jxjlb="%";
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select '' dm ,'---请选择---'mc from dual union ");
		sql.append("select jxjdm dm, jxjmc mc from jxjdmb where jxjlb like ? order by dm nulls first");
		return dao.getList(sql.toString(), new String[] { jxjlb },
				new String[] { "dm", "mc" });

	}

	/**
	 * 获得计算机等级列表
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJsjList() {
		DAO dao = DAO.getInstance();
		return dao.getList("select djksdm dm, djksmc mc from djksdmb where djksmc like '%计算机%'",
						new String[] {}, new String[] { "dm", "mc" });

	}

	/**
	 * 获得奖学金类别列表
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjLbList() {
		DAO dao = DAO.getInstance();
		return dao.getList("select distinct (jxjlb) dm, jxjlb mc from jxjdmb order by dm desc",
						new String[] {}, new String[] { "dm", "mc" });

	}
	
	/**
	 * 获得违纪处分列表
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWjcfList(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select * from view_wjcf where xh = ? and xxsh = '通过'";
		List<HashMap<String, String>> list = dao.getList(sql,
				new String[] { xh }, new String[] {"xn","xqmc","cflbmc"});
		return list;
	}
	
	/**
	 * 保存奖学金申请
	 * @author luo
	 * @throws Exception
	 */
	public boolean saveJxjsq(XnjxjModel model, HttpServletRequest request)
			throws Exception {

		String xh = model.getXh();
		String xn = model.getXn();
		String xq = model.getXq();
		String jxjdm = model.getJxjdm();// 奖学金代码;
		String hgjjxjqk = DealString.toGBK(model.getHgjjxjqk());// 获国家奖学金情况;
		String bjgkms = model.getBjgkms();// 不及格科目数;
		bjgkms = Base.isNull(bjgkms) ? "0" : bjgkms;
		String yygjqk = model.getYygjqk();// 英语过级情况;
		String jsjgjqk = model.getJsjgjqk();// 计算机过级情况;
		String tqxf = model.getTqxf();// 拖欠学费;
		String zhfpm = model.getPm();// 拖欠学费;

		String[] columns = new String[] { "xh", "xn", "xq", "jxjdm",
				"hgjjxjqk", "bjgkms", "yygjqk", "jsjgjqk", "tqxf", "zhfpm" };
		String[] values = new String[] { xh, xn, xq, jxjdm, hgjjxjqk, bjgkms,
				yygjqk, jsjgjqk, tqxf, zhfpm };
		boolean flg = StandardOperation.delete("zjcm_jxjsq",
				"xn||xq||xh||jxjdm", xn + xq + xh + jxjdm, request);
		if (flg) {
			flg = StandardOperation.insert("zjcm_jxjsq", columns, values,
					request);
		}
		return flg;
	}

	/**
	 * 获得奖学金申请列表
	 */
	public ArrayList<String[]> getJxjSqList(String tableName, XnjxjModel model,
			String[] colList, String userType, String doType)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

//		DAO dao =DAO.getInstance();
		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
		String xn = DealString.toGBK(model.getXn());
		// 学期
		String xq = DealString.toGBK(model.getXq());
		// 学号
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		// 奖学金代码
		String jxjdm = model.getJxjdm();
		// 辅导员审核
		String fdysh = DealString.toGBK(model.getFdysh());
		// 学院审核
		String xysh = DealString.toGBK(model.getXysh());
		// 学校审核
		String xxsh = DealString.toGBK(model.getXxsh());
		
		StringBuffer query = new StringBuffer();

		query.append(" where zhf is not null");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='" + xydm
				+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='" + zydm
				+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='" + bjdm
				+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='" + nj + "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='" + xn + "'");
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='" + xq + "'");
		query.append(Base.isNull(jxjdm) ? " and 1=1" : " and jxjdm='" + jxjdm + "'");
		query.append(Base.isNull(fdysh) ? " and 1=1" : " and fdysh='" + fdysh + "'");
		query.append(Base.isNull(xysh) ? " and 1=1" : " and xysh='" + xysh + "'");
		query.append(Base.isNull(xxsh) ? " and 1=1" : " and xxsh='" + xxsh + "'");
		
		query.append(" and xh like ?");
		query.append(" and xm like ?");
		
		if (Base.isNull(doType)) {
			if ("xy".equalsIgnoreCase(userType)) {
				
				//query.append(" and fdysh = '通过'");
			} else if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
//				if(!Base.isNull(jxjdm)){
//					String jxjmc = dao.getOneRs("select jxjmc from jxjdmb where jxjdm=?", new String[]{jxjdm}, "jxjmc");	
//					if(jxjmc.contains("二等")){
//						query.append(" and (xysh = '通过' and jxjdm='" + jxjdm + "')");
//						query.append(" or ( jxjmc = '"+jxjmc.replace("二等", "一等")+"' and xysh='未通过')");
//					}else if(jxjmc.contains("三等")){
//						query.append(" and (xysh = '通过' and jxjdm='" + jxjdm + "')");
//						query.append(" or ( jxjmc = '"+jxjmc.replace("三等", "二等")+"' and xysh='未通过')");
//					}else{
//						query.append(" and xysh = '通过'");
//						query.append(Base.isNull(jxjdm) ? " and 1=1" : " and jxjdm='" + jxjdm + "'");
//					}
//				}else{
					//query.append(" and xysh = '通过'");
					//query.append(Base.isNull(jxjdm) ? " and 1=1" : " and jxjdm='" + jxjdm + "'");
//			}
				
			}
		}else{		
			String jxjlb = DealString.toGBK(model.getJxjlb());
			if(!Base.isNull(model.getXwlb())){
				jxjlb = DealString.toGBK(model.getXwlb());
			}
			query.append(Base.isNull(jxjdm) ? " and 1=1" : " and jxjdm='" + jxjdm + "'");
			query.append(Base.isNull(jxjlb) ? " and 1=1" : " and jxjlb='" + jxjlb + "'");
		}

		String[] inPutList = new String[] { xh, xm };

		ArrayList<String[]> list = commonQuery(tableName, query.toString(),
				inPutList, colList, "", model);
		//System.out.println(query);
		return list;
	}

	/**
	 * 获得申报奖学金信息
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> getSbJxjXx(String pk) {
		String[] colList = new String[] { "xn", "xq", "xh", "xm", "xb", "nj",
				"xymc", "zymc", "bjmc", "zhf", "dyf", "zyf", "tyf", "nlf",
				"jxjdm", "jxjmc", "hgjjxjqk", "bjgkms", "yygjqk", "jsjgjqk",
				"zhfpm", "tqxf", "fdysh", "xysh", "xxsh", "fdyshsj", "xyshsj",
				"xxshsj", "fdyyj", "xyyj", "xxyj" };
		return commonQueryOne("view_zjcm_jxjsq", colList, "pk", pk);
	}

	/**
	 * 保存奖学金审核
	 */
	public boolean saveJxjsh(XnjxjModel model, String pk, String shzt,
			String userType, HttpServletRequest request) throws Exception {

		String[] col = null;
		String[] value = null;
		String sh = "tg".equalsIgnoreCase(shzt) ? "通过" : "未通过";

		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String time = f.format(new Date());

		if ("fdy".equalsIgnoreCase(userType)) {
			col = new String[] { "fdyyj", "fdyshsj", "fdysh" };
			value = new String[] { DealString.toGBK(model.getFdyyj()), time, sh };
		} else if ("xy".equalsIgnoreCase(userType)) {
			col = new String[] { "xyyj", "xyshsj", "xysh" };
			value = new String[] { DealString.toGBK(model.getXyyj()), time, sh };
		} else {
			col = new String[] { "xxyj", "xxshsj", "xxsh" };
			value = new String[] { DealString.toGBK(model.getXxyj()), time, sh };
		}

		boolean flg = StandardOperation.update("zjcm_jxjsq", col, value,
				"xh||xn||xq||jxjdm", pk, request);

		return flg;
	}
	
	/**
	 * 保存次级奖学金审核
	 */
	public boolean saveCjJxjsh(XnjxjModel model, String pk, String shzt,
			String userType) throws Exception {

		DAO dao=DAO.getInstance();
		boolean flg = false;
		String sh = "tg".equalsIgnoreCase(shzt) ? "通过" : "未通过";

		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String time = f.format(new Date());

		String sql = "select jxjmc,jxjlb from jxjdmb where jxjdm =(select "
				+ "jxjdm from zjcm_jxjsq where xh || xn || xq || jxjdm = ?)";
		HashMap<String,String> map = dao.getMap(sql, new String[] { pk }, new String[]{"jxjmc","jxjlb"});
		
		String jxjmc = map.get("jxjmc");
		String jxjlb = map.get("jxjlb");
		
		if (jxjmc.contains("一等")) {
			jxjmc = jxjmc.replace("一等","二等");
		} else if (jxjmc.contains("二等")) {
			jxjmc = jxjmc.replace("二等","三等");
		}
		
		sql = "update zjcm_jxjsq set xxsh = '转入次级' where xh || xn || xq || jxjdm =?";
		dao.runUpdate(sql, new String[]{pk});
		
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) num from zjcm_jxjsq where ");
		sb.append("xh||xn||xq||jxjdm=(select xh||xn||xq||");
		sb.append("(select jxjdm from jxjdmb where jxjmc = ? and jxjlb = ? and rownum = 1) ");
		sb.append("from zjcm_jxjsq where xh||xn||xq||jxjdm=?)");
		
		String num = dao.getOneRs(sb.toString(), new String[]{jxjmc,jxjlb,pk}, "num");
		
		if("0".equalsIgnoreCase(num)){
			sb = new StringBuffer();
			sb.append("insert into zjcm_jxjsq(xh,xn,xq,jxjdm,zhfpm,hgjjxjqk,bjgkms,yygjqk, ");
			sb.append("jsjgjqk,tqxf,fdysh,xysh,xxsh,fdyshsj,xyshsj,xxshsj,xxyj) select xh,xn,xq, ");
			sb.append("(select jxjdm from jxjdmb where jxjmc = '"+jxjmc+"' and rownum = 1), ");
			sb.append("zhfpm,hgjjxjqk,bjgkms,yygjqk,jsjgjqk,tqxf,?,?,?, ");
			sb.append("?,?,?,? from zjcm_jxjsq where xh||xn||xq||jxjdm = '"+ pk+ "'");
			flg=dao.runUpdate(sb.toString(), new String[]{sh,sh,sh,time,time,time,DealString.toGBK(model.getXxyj())});
		}else{
			sb = new StringBuffer();
			sb.append("update zjcm_jxjsq set xxsh=?,xxyj=?,xxshsj=? where ");
			sb.append("xh||xn||xq||jxjdm=(select xh||xn||xq||");
			sb.append("(select jxjdm from jxjdmb where jxjmc = '"+jxjmc+"' and rownum = 1) ");
			sb.append("from zjcm_jxjsq where xh||xn||xq||jxjdm='"+pk+"')");
			flg=dao.runUpdate(sb.toString(), new String[]{sh,DealString.toGBK(model.getXxyj()),time});
		}
		return flg;
	}
	
	/**
	 * 保存奖学金审核
	 */
	public String saveJxjsh(String[] key, String jxjdm, String shzt,
			String userType) throws Exception {

		DAO dao = new DAO();
		boolean flg = false;
		
		String sh = "tg".equalsIgnoreCase(shzt) ? "通过" : "未通过";
		StringBuffer sb = new StringBuffer();
		String sql="";
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String time = f.format(new Date());
		String shr = "";
		String shsj = "";
		String msg = "";
		
		if ("fdy".equalsIgnoreCase(userType)) {
			shr="fdysh";
			shsj="fdyshsj";
		} else if ("xy".equalsIgnoreCase(userType)) {
			shr="xysh";
			shsj="xyshsj";
		} else {
			shr="xxsh";
			shsj="xxshsj";
		}

		for (int i = 0; i < key.length; i++) {
			String pk = key[i];
			if (!("tg".equalsIgnoreCase(shzt) && hadJxj(pk))) {
			sql = "update zjcm_jxjsq set " + shr + " = '" + sh + "',"
						+ shsj + "='" + time + "' where xh||xn||xq||jxjdm ='"
						+ pk + "'";
				sb.append(sql);
				sb.append("!!#!!");
			}else{
				if ("tg".equalsIgnoreCase(shzt)) {
					msg = "存在奖学金兼得情况，无法通过审核，请确认!!";
					return msg;
				}
			}
		}
		
		String[] inssql = sb.toString().split("!!#!!");

		int[] res = dao.runBatch(inssql);
		
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return msg;
	}
	
	/**
	 * 保存次级奖学金审核
	 */
	public String saveCjJxjsh(String[] key, String jxjdm, String shzt) throws Exception {
		//TODO
		DAO dao = new DAO();
		boolean flg = false;
		
		String sh = "tg".equalsIgnoreCase(shzt) ? "通过" : "未通过";
		StringBuffer sb = new StringBuffer();
		String sql="";
		String num = "";
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String time = f.format(new Date());
		String msg = "";
		
		for (int i = 0; i < key.length; i++) {
			String pk = key[i];

			sql = "select jxjmc,jxjlb from jxjdmb where jxjdm =(select "
					+ "jxjdm from zjcm_jxjsq where xh || xn || xq || jxjdm = ?)";
			HashMap<String, String> map = dao.getMap(sql, new String[] { pk },
					new String[] { "jxjmc", "jxjlb" });

			String jxjmc = map.get("jxjmc");
			String jxjlb = map.get("jxjlb");

			if (jxjmc.contains("一等")) {
				jxjmc = jxjmc.replace("一等", "二等");
			} else if (jxjmc.contains("二等")) {
				jxjmc = jxjmc.replace("二等", "三等");
			}
			StringBuffer count = new StringBuffer();
			count.append("select count(*) num from zjcm_jxjsq where ");
			count.append("xh||xn||xq||jxjdm=(select xh||xn||xq||");
			count.append("(select jxjdm from jxjdmb where jxjmc = ? and jxjlb = ? and rownum = 1) ");
			count.append("from zjcm_jxjsq where xh||xn||xq||jxjdm=?)");
			
			num = dao.getOneRs(count.toString(), new String[]{jxjmc,jxjlb,pk}, "num");
		
			sb.append("update zjcm_jxjsq set xxsh = '转入次级' where xh || xn || xq || jxjdm = '"+pk+"'");
			sb.append("!!#!!");
			
			if (!("tg".equalsIgnoreCase(shzt) && hadJxj(pk))) {
				if ("0".equalsIgnoreCase(num)) {
					sb.append("insert into zjcm_jxjsq(xh,xn,xq,jxjdm,zhfpm,hgjjxjqk,bjgkms,yygjqk, ");
					sb.append("jsjgjqk,tqxf,fdysh,xysh,xxsh,fdyshsj,xyshsj,xxshsj) select xh,xn,xq, ");
					sb.append("(select jxjdm from jxjdmb where jxjmc = '"+jxjmc+"' and rownum = 1), ");
					sb.append("zhfpm,hgjjxjqk,bjgkms,yygjqk,jsjgjqk,tqxf,'"+sh+"','"+sh+"','"+sh+"', ");
					sb.append("'"+time+"','"+time+"','"+time+"' from zjcm_jxjsq where xh||xn||xq||jxjdm = '"+ pk+ "'");
					sb.append("!!#!!");
				} else {
					sb.append("update zjcm_jxjsq set xxsh='"+sh+"',xxshsj='"+time+"' where ");
					sb.append("xh||xn||xq||jxjdm=(select xh||xn||xq||");
					sb.append("(select jxjdm from jxjdmb where jxjmc = '"+jxjmc+"' and rownum = 1) ");
					sb.append("from zjcm_jxjsq where xh||xn||xq||jxjdm='"+pk+"')");
					sb.append("!!#!!");
				}
			} else {
				if ("tg".equalsIgnoreCase(shzt)) {
					msg = "存在奖学金兼得情况，无法通过审核，请确认!!";
					return msg;
				}
			}
		}
		
		String[] inssql = sb.toString().split("!!#!!");

		int[] res = dao.runBatch(inssql);
		
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return msg;
	}
	
	/**
	 * 删除奖学金
	 */
	public boolean delJxjSq(String[] key,String userType) throws Exception {

		DAO dao = new DAO();
		boolean flg = false;

		StringBuffer sb = new StringBuffer();
		String sql = "";

		for (int i = 0; i < key.length; i++) {
			String pk = key[i];
			sql = "delete from zjcm_jxjsq where xh||xn||xq||jxjdm ='" + pk
					+ "'";
			sb.append(sql);
			if("cpz".equalsIgnoreCase(userType)){
				sb.append(" and fdysh = '未审核' and xysh = '未审核' and xxsh = '未审核'");
			}else if("fdy".equalsIgnoreCase(userType)){
				sb.append(" and xysh = '未审核' and xxsh = '未审核'");
			}else if("xy".equalsIgnoreCase(userType)){
				sb.append(" xxsh = '未审核'");
			}
			sb.append("!!#!!");
		}

		String[] delsql = sb.toString().split("!!#!!");

		int[] res = dao.runBatch(delsql);

		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * 判断奖学金条件
	 */
	public String Jxjtj(XnjxjModel model,String zhpm) {
		DAO dao = new DAO();
		String msg = "";
		
		String xh = model.getXh();
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		String jxjdm = model.getJxjdm();
		String bjgkms = model.getBjgkms();
		
		String sql = "select * from zjcm_pjpy_tjsz where szlx='jxj' and jxjdm = ? and xn = ? and xq = ?";
		List<HashMap<String, String>> list = dao.getList(sql, new String[] {
				jxjdm, xn, xq }, new String[] { "tjzd", "tjlx", "tjz" });
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				if ("zhpm".equalsIgnoreCase(map.get("tjzd"))) {
					String tjlx = map.get("tjlx");
					float tjpm = Float.parseFloat(getTjpm(xh, map.get("tjz")));
					float pm = Float.parseFloat(zhpm);
					if (">".equalsIgnoreCase(tjlx)) {
						if (pm < tjpm) {
							continue;
						} else {
							msg = "该学生综合素质排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if (">=".equalsIgnoreCase(tjlx)) {
						if (pm <= tjpm) {
							continue;
						} else {
							msg = "该学生综合素质排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("=".equalsIgnoreCase(tjlx)) {
						if (pm == tjpm) {
							continue;
						} else {
							msg = "该学生综合素质排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("<".equalsIgnoreCase(tjlx)) {
						if (pm > tjpm) {
							continue;
						} else {
							msg = "该学生综合素质排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("<=".equalsIgnoreCase(tjlx)) {
						if (pm >= tjpm) {
							continue;
						} else {
							msg = "该学生综合素质排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					}
				} else if ("dypm".equalsIgnoreCase(map.get("tjzd"))) {
					String tjlx = map.get("tjlx");
					float tjpm = Float.parseFloat(getTjpm(xh, map.get("tjz")));
					float pm = Float.parseFloat(getPm(xh, xn, xq, "dyf"));
					if (">".equalsIgnoreCase(tjlx)) {
						if (pm < tjpm) {
							continue;
						} else {
							msg = "该学生德育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if (">=".equalsIgnoreCase(tjlx)) {
						if (pm <= tjpm) {
							continue;
						} else {
							msg = "该学生德育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("=".equalsIgnoreCase(tjlx)) {
						if (pm == tjpm) {
							continue;
						} else {
							msg = "该学生德育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("<".equalsIgnoreCase(tjlx)) {
						if (pm > tjpm) {
							continue;
						} else {
							msg = "该学生德育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("<=".equalsIgnoreCase(tjlx)) {
						if (pm >= tjpm) {
							continue;
						} else {
							msg = "该学生德育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					}
				} else if ("zypm".equalsIgnoreCase(map.get("tjzd"))) {
					String tjlx = map.get("tjlx");
					float tjpm = Float.parseFloat(getTjpm(xh, map.get("tjz")));
					float pm = Float.parseFloat(getPm(xh, xn, xq, "zyf"));
					if (">".equalsIgnoreCase(tjlx)) {
						if (pm < tjpm) {
							continue;
						} else {
							msg = "该学生智育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if (">=".equalsIgnoreCase(tjlx)) {
						if (pm <= tjpm) {
							continue;
						} else {
							msg = "该学生智育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("=".equalsIgnoreCase(tjlx)) {
						if (pm == tjpm) {
							continue;
						} else {
							msg = "该学生智育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("<".equalsIgnoreCase(tjlx)) {
						if (pm > tjpm) {
							continue;
						} else {
							msg = "该学生智育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("<=".equalsIgnoreCase(tjlx)) {
						if (pm >= tjpm) {
							continue;
						} else {
							msg = "该学生智育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					}
				} else if ("bjgkm".equalsIgnoreCase(map.get("tjzd"))) {
					if (Base.isNull(bjgkms)) {
						continue;
					} else {
						if (Integer.parseInt(bjgkms) > 0) {
							msg = "该学生有不及格科目,不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					}
				} else if ("zyfjb".equalsIgnoreCase(map.get("tjzd"))) {
					String xqmc = getXqmc(xq);
					String qxn = "";
					String qxq = "";
					if("春".equalsIgnoreCase(xqmc)){
						qxn=xn;
						qxq=getXqdm("秋");	
					}else if("秋".equalsIgnoreCase(xqmc)){
						String[] arrXn = xn.split("-");
						qxn=String
								.valueOf((Integer.parseInt(arrXn[0]) - 1))
								+ "-"
								+ String.valueOf((Integer.parseInt(arrXn[1]) - 1));
						qxq=getXqdm("春");	
					}
					String dqpm = getPm(xh, xn, xq, "zyf");
					String qpm = getPm(xh, qxn, qxq, "zyf");
					if(!Base.isNull(dqpm) && !Base.isNull(qpm)){
						float tjpm = Float.parseFloat(getTjpm(xh, map.get("tjz")));
						float now = Float.parseFloat(dqpm);
						float perior = Float.parseFloat(qpm);
						float pm = perior-now;
						String tjlx = map.get("tjlx");
						if (">".equalsIgnoreCase(tjlx)) {
							if (pm > tjpm) {
								continue;
							} else {
								msg = "该学生智育分进步条件不满足本奖学金的申请条件,请确认！！！";
								return msg;
							}
						} else if (">=".equalsIgnoreCase(tjlx)) {
							if (pm >= tjpm) {
								continue;
							} else {
								msg = "该学生智育分进步条件不满足本奖学金的申请条件,请确认！！！";
								return msg;
							}
						} else if ("=".equalsIgnoreCase(tjlx)) {
							if (pm == tjpm) {
								continue;
							} else {
								msg = "该学生智育分进步条件不满足本奖学金的申请条件,请确认！！！";
								return msg;
							}
						} else if ("<".equalsIgnoreCase(tjlx)) {
							if (pm < tjpm) {
								continue;
							} else {
								msg = "该学生智育分进步条件不满足本奖学金的申请条件,请确认！！！";
								return msg;
							}
						} else if ("<=".equalsIgnoreCase(tjlx)) {
							if (pm <= tjpm) {
								continue;
							} else {
								msg = "该学生智育分进步条件不满足本奖学金的申请条件,请确认！！！";
								return msg;
							}
						}				
					}
				}
			}
		}
		return msg;
	}
	
	/**
	 * 获得条件排名
	 */
	public String getTjpm(String xh, String tjz) {
		DAO dao = new DAO();
		StringBuffer sql = new StringBuffer();
		sql.append("select num * " + tjz + " / 100 tjpm from (select count(*) num ");
		sql.append("from view_xsjbxx where bjdm = (select bjdm from view_xsjbxx where xh = ?))");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "tjpm");
	}
	
	/**
	 * 获得评奖学年学期排名
	 */
	public String getPm(String xh,String xn,String xq,String pm) {
		DAO dao = new DAO();
		xq = getXqmc(xq);
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select xh,(rank() over(partition by xn,xq,bjdm order by to_number("+pm+") ");
		sql.append("desc nulls last)) pm from view_zjcm_zhf where xn = ? and xq = ?) where xh = ?");   
		return dao.getOneRs(sql.toString(), new String[]{xn,xq,xh}, "pm");
	}
	
	/**
	 * 获得进步率
	 */
	public String getJvl(String xh,String xn,String xq,String qxn,String qxq) {
		DAO dao = new DAO();
		xq = getXqmc(xq);
		StringBuffer sql = new StringBuffer();
		
		return dao.getOneRs(sql.toString(), new String[]{xn,xq,xh}, "pm");
	}
	
	/**
	 * 保存奖学金审核
	 */
	public boolean saveXwjxjSb(String[] key,String jxjdm) throws Exception{

		DAO dao = DAO.getInstance();
		
		StringBuffer sql = new StringBuffer();
		StringBuffer del = new StringBuffer();
		StringBuffer query = new StringBuffer();
		String xxsh ="";
		boolean flg = false;
		
		sql.append("insert into zjcm_jxjsq (xh, xn, xq, jxjdm, zhfpm,");
		sql.append("sbsj, hgjjxjqk, bjgkms, yygjqk, jsjgjqk, tqxf)");
		sql.append("select xh,xn,xq,'"+jxjdm+"' jxjdm,zhfpm,to_char(sysdate, 'yyyyMMdd') sbsj,");
		sql.append("hgjjxjqk,bjgkms,yygjqk,jsjgjqk,tqxf  from view_zjcm_jxjsq ");
		for (int i = 0; i < key.length; i++) {
			xxsh = dao.getOneRs("select xxsh from view_zjcm_jxjsq where pk = ?", new String[]{key[i]}, "xxsh");
			if(!"通过".equalsIgnoreCase(xxsh)){
				continue;
			}
			
			StringBuffer pkSql = new StringBuffer();
			pkSql.append("select xxsh from zjcm_jxjsq where xh||xn||xq||jxjdm = (select xh||xn||xq||'");
			pkSql.append(jxjdm+"' pk from view_zjcm_jxjsq where pk=?)");
	
			xxsh=dao.getOneRs(pkSql.toString(), new String[]{key[i]}, "xxsh");
			
			if("通过".equalsIgnoreCase(xxsh)){
				continue;
			}
			if (i == 0) {
				query.append("where pk = '" + key[i] + "' ");
			} else {
				if(query== null || query.length() == 0){
					query.append("where pk = '" + key[i] + "' ");
				}else{
					query.append("or pk = '" + key[i] + "' ");
				}
			}
			del.append("delete from zjcm_jxjsq where xh||xn||xq||jxjdm = (select xh||xn||xq||'");
			del.append(jxjdm + "' pk from view_zjcm_jxjsq where pk='" + key[i] + "')");
			del.append("!!#!!");
		}
		

		String[] inssql = del.toString().split("!!#!!");
		int[] res = dao.runBatch(inssql);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		if (flg && (query != null && query.length() > 0)) {
			flg=dao.runUpdate(sql.toString() + query.toString(), new String[]{});
		}

		return flg;
	}
	
	/**
	 * 判断奖学金兼得情况
	 */
	public boolean hadJxj(String pk) {
		DAO dao = DAO.getInstance();
		boolean flg = false;
		StringBuffer sql =new StringBuffer();
		sql.append("select count(*) num from view_zjcm_jxjsq where xh || xn || xq = ");
		sql.append("(select xh || xn || xq from zjcm_jxjsq where xh || xn || xq || jxjdm = ?) ");
		sql.append("and xxsh = '通过' and jxjlb = '校' and pk <> ? ");

		String jxjmc=dao.getOneRs("select jxjmc from view_zjcm_jxjsq where pk = ?", new String[]{pk}, "jxjmc");
		if(!jxjmc.contains("一等")&&!jxjmc.contains("二等")&&!jxjmc.contains("三等")){
			sql.append("and (jxjmc like '%一等%' or jxjmc like '%二等%' or jxjmc like '%三等%')");
		}
		
		String num = dao.getOneRs(sql.toString(), new String[]{pk,pk}, "num");
		//System.out.print(sql+"@@"+pk);
		if(!"0".equalsIgnoreCase(num)){
			flg = true;
		}
		return flg;
	}
	
	/**
	 * 获得奖学金类别
	 */
	public String getJxjlb(String jxjdm) {
		DAO dao = DAO.getInstance();
		String sql = "select jxjlb from jxjdmb where jxjdm = ?";
		return dao.getOneRs(sql, new String[] { jxjdm }, "jxjlb");
	}
	
	/**
	 * 获得奖学金人上限数
	 */
	public String getJxjRs(String jxjdm, String bjdm) {
		DAO dao = DAO.getInstance();
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		String sql = "select jxjrs from xyjxjrs where jxjdm=? and xn =? and xqdm =? and bmlb='bjdm' and bmdm=? and key='jxj'";
		return dao.getOneRs(sql, new String[] { jxjdm, xn, xq, bjdm }, "jxjrs");
	}
	
	/**
	 * 获得奖学金人数
	 */
	public String getHdJxjRs(String jxjdm,String bjdm) {
		DAO dao = DAO.getInstance();
		String sql = "select count(*) num from view_zjcm_jxjsq where jxjdm = ? and bjdm=? and xxsh = '通过'";
		return dao.getOneRs(sql, new String[] { jxjdm,bjdm }, "num");
	}
	
	/**
	 * 判断次级人数
	 */
	public String getCjJxjRs(String jxjdm, String bjdm, String num) {
		DAO dao = DAO.getInstance();
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		String msg = "";
		String sql = "select jxjmc from jxjdmb where jxjdm = ?";
		String jxjmc = dao.getOneRs(sql, new String[] { jxjdm }, "jxjmc");

		if (jxjmc.contains("一等")) {
			jxjmc = jxjmc.replace("一等", "二等");
		} else if (jxjmc.contains("二等")) {
			jxjmc = jxjmc.replace("二等", "三等");
		}

		sql = "select jxjdm from jxjdmb where jxjmc like ?";
		String cjdm = dao.getOneRs(sql, new String[] { jxjmc }, "jxjdm");

		String zrs = getJxjRs(cjdm, bjdm);
		sql = "select count(*) num from view_zjcm_jxjsq where xn = ? and xq = ? and bjdm = ? and jxjdm = ? and xxsh = '通过'";
		String tgrs = dao.getOneRs(sql, new String[] { xn, xq, bjdm,cjdm }, "num");
		
		if(Integer.parseInt(num)>Integer.parseInt(zrs)-Integer.parseInt(tgrs)){
			msg = "审核人数超过次级奖学金剩余人数，请确认！！";
		}

		return msg;
	}
	
	/**
	 * 校内奖学金列表
	 * @param jxjlb
	 * @returnR
	 */
	public List<HashMap<String, String>> getXnjxjList(String jxjlb) {
		DAO dao = DAO.getInstance();
		return dao
				.getList(
						"select jxjdm dm,jxjmc mc from jxjdmb a where exists " +
						"(select 1 from jxjlbdmb b where jxjlbmc like ? and a.jxjlb=b.jxjlbdm) order by jxjdm",
						new String[] { jxjlb }, new String[] { "dm", "mc" });
	}
	
	/**
	 * DWR调用 检测奖学金审核结果
	 * @param pkValue
	 * @param userType
	 * @param isFdy
	 * @return
	 */
	public String checkJxjshjg(String pkValue, String userType, String isFdy, String sh) {
		//如果审核结果不为通过或是学校用户进行审核就不用检测
		if (!"通过".equalsIgnoreCase(sh) || !"xy".equalsIgnoreCase(userType)) {
			return "true";
		}
		
		DAO dao = DAO.getInstance();
		//查询学生详细获奖信息
		String  zd = UserTypePd.isXy(userType) ? 
				(UserTypePd.isFdy(isFdy) ? "fdysh" : "xysh") : "xxsh";
		HashMap<String, String> rs = dao.getMapNotOut("select xh,xn,xq,jxjdm,bjdm,nj,"+zd+" from view_pjpy_zjcm_xsjxjb where xh||jxjdm||xn||xq = ?", new String[]{pkValue});
		
		if ("通过".equalsIgnoreCase(rs.get(zd))) {
			return "true";
		}
		
		//所在班级通过人数		
		String bjtgrs = dao
				.getOneRs(
						"select nvl(count(*),0) num from view_pjpy_zjcm_xsjxjb where xn=? and xq=? and bjdm=? and jxjdm=? and "
								+ zd + "='通过'",
						new String[] { rs.get("xn"), rs.get("xq"),
								rs.get("bjdm"), rs.get("jxjdm") }, "num");
		//学校设置名额
		String me = dao
				.getOneRs(
						"select sum(jxjrs) jxjrs from xyjxjrs where xn=? and xqdm=? and jxjdm=? and bmdm=? and key='jxj' and nj=?",
						new String[] { rs.get("xn"), rs.get("xq"),
								rs.get("jxjdm"), rs.get("bjdm"), rs.get("nj") },
						"jxjrs");
		int iMe = StringUtils.isNotNull(me) ? Integer.parseInt(me) : 0;
		int iBjtgrs = StringUtils.isNotNull(bjtgrs) ? Integer.parseInt(bjtgrs) : 0;
		if (iMe >0 && iMe == iBjtgrs) {
			return "false";
		}
		return "true";
	}
	
	/**
	 * DWR调用荣誉称号单个审核检测结果
	 * @param pkValue
	 * @param userType
	 * @param isFdy
	 * @param sh
	 * @param cpfw
	 * @return
	 */
	public String checkRychshjg(String pkValue, String userType, String isFdy, String sh, String cpfw) {
		//如果审核结果不为通过或是学校用户进行审核就不用检测
		if (!"通过".equalsIgnoreCase(sh) || !"xy".equalsIgnoreCase(userType)) {
			return "true";
		}
		
		DAO dao = DAO.getInstance();
		//查询学生详细获奖信息
		String  zd = UserTypePd.isXy(userType) ? 
				(UserTypePd.isFdy(isFdy) ? "fdysh" : "xysh") : "xxsh";
		HashMap<String, String> rs = dao.getMapNotOut("select xh,xn,xq,rychdm,bjdm,zydm,xydm,nj,"+zd+" from view_pjpy_zjcm_xsrychb where xh||rychdm||xn||xq = ?",
				                                      new String[]{pkValue});
		if ("通过".equalsIgnoreCase(rs.get(zd))) {
			return "true";
		}
		String bmdm = getBmdm(rs, cpfw);
		
		String appendSql = "";
		if (StringUtils.isNotNull(cpfw)) {
			appendSql = " and " + cpfw + "='" + bmdm + "'";
		}
		
		//所在部门通过人数		
		String bjtgrs = dao
				.getOneRs(
						"select nvl(count(*),0) num from view_pjpy_zjcm_xsrychb where xn=? and xq=? "+appendSql+" and rychdm=? and "
								+ zd + "='通过'",
						new String[] { rs.get("xn"), rs.get("xq"), rs.get("rychdm") }, "num");
		
		//学校设置名额
		String me = dao
				.getOneRs(
						"select sum(jxjrs) jxjrs from xyjxjrs where xn=? and xqdm=? and jxjdm=? and bmdm=? and key='rych'",
						new String[] { rs.get("xn"), rs.get("xq"),
								rs.get("rychdm"), bmdm },
						"jxjrs");
		
		int iMe = StringUtils.isNotNull(me) ? Integer.parseInt(me) : 0;
		int iBjtgrs = StringUtils.isNotNull(bjtgrs) ? Integer.parseInt(bjtgrs) : 0;
		if (iMe >0 && iMe == iBjtgrs) {
			return "false";
		}
		return "true";
	}
	
	private String getBmdm(HashMap<String, String> rs, String bmlb) {
		String bmdm = "xydm".equalsIgnoreCase(bmlb) ? rs.get("xydm") : ("zydm"
				.equalsIgnoreCase(bmlb) ? rs.get("zydm") : ("bjdm"
				.equalsIgnoreCase(bmlb) ? rs.get("bjdm") : ""));
		return bmdm;
	}
	
	/**
	 * 校外奖学金审核检测
	 * @param pkValue
	 * @return
	 */
	public String checkXwjxjShxx(String pkValue) {
		DAO dao = DAO.getInstance();
		HashMap<String, String> rs = dao.getMapNotOut("select pjxh,jxjdm,xn,xq,xxsh from zjcm_jxjsq where pjxh||jxjdm||xn||xq=?", new String[]{pkValue});
		if ("通过".equalsIgnoreCase(rs.get("xxsh"))) {
			return "true";
		}
		XwjxjDAO mydao = new XwjxjDAO();
		String me = mydao.getJxjRs(rs.get("jxjdm"));
		String bjtgrs = mydao.getHdJxjRs(rs.get("jxjdm"));
		int iMe = StringUtils.isNotNull(me) ? Integer.parseInt(me) : 0;
		int iBjtgrs = StringUtils.isNotNull(bjtgrs) ? Integer.parseInt(bjtgrs) : 0;
		if (iMe >0 && iMe == iBjtgrs) {
			return "false";
		}
		return "true";
	}
}
