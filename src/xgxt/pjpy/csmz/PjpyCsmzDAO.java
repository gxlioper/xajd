
package xgxt.pjpy.csmz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 长沙民政学院评奖评优DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-06</p>
 */
public class PjpyCsmzDAO extends DAO{
	DAO dao = DAO.getInstance();
	List<String> values = new ArrayList<String>();//查询条件值
	
	/**
	 * 获取学生相关信息（班级，专业，学院，性别，年级,姓名，学号）
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		HashMap<String, String> stuList = new HashMap<String,String>();
		String sql = "select xh,xm,xb,xymc,bjmc,zymc,nj from view_xsjbxx where xh = ?";
		String[] colList = new String[]{"xh","xm","xb","xymc","bjmc","zymc","nj"};
		String[] sLen = dao.getOneRs(sql, new String[]{xh}, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i].toString());
			}//end for
		}//end if
		return stuList;
	}
	
	/**
	 * 获取奖学金相关信息(奖学金代码，名称，金额，类别)
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjInfo(HashMap<String, String> jxjMap) throws Exception {
		String sql = "select jxjdm,jxjmc,jxjlb,jlje from jxjdmb where jxjmc='社会奖学金'";
		String[] colList = new String[]{"jxjdm","jxjmc","jxjlb","jlje"};
		String[] jxjList = dao.getOneRs(sql, new String[]{}, colList);
		if (jxjList != null) {
			for (int i = 0; i < colList.length; i++) {
				jxjMap.put(colList[i].toString(), jxjList[i].toString());
			}//end for
		}//end if
		return jxjMap;
	}
	
	/**
	 * 判断数据是否重复，重复返回TRUE，反之返回FALSE
	 * isdatacf ---- 数据是否重复
	 * @param xh
	 * @param jxjdm
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean isDataCf(String xh, String jxjdm, String xn) throws Exception {
		boolean bFlag = false;
		String sql = "select xh,jxjdm,xn,sqsj from xsjxjb where xh = ? and jxjdm = ? and xn = ?";
		HashMap<String, String> resMap = dao.getMapNotOut(sql, new String[]{xh, jxjdm, xn});
		if (resMap != null && resMap.size() > 0) {
			bFlag = true;
		}//end if
		return bFlag;
	}
	
	/**
	 * 保存社会奖学金，成功返回TRUE并保存其它奖学金信息及学生辅助信息，反之返回FALSE
	 * issaveshjxj ---- 保存社会奖学金 
	 * @param shjxjModel (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean isSaveShJxj(SaveShJxjModel shjxjModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xh = shjxjModel.getXh();
		String jxjdm = shjxjModel.getJxjdm();
		String xn = Base.currXn;
		String nd = Base.currNd;
		String xq = Base.currXq;
		String drzw = DealString.toGBK(shjxjModel.getDrzw());
		String xxjl = DealString.toGBK(shjxjModel.getXxjl());
		String jxj1 = DealString.toGBK(shjxjModel.getJxj1());
		String jxj2 = DealString.toGBK(shjxjModel.getJxj2());
		String jxj3 = DealString.toGBK(shjxjModel.getJxj3());
		String jxj4 = DealString.toGBK(shjxjModel.getJxj4());
		String shyg1 = DealString.toGBK(shjxjModel.getShyg1());
		String shyg2 = DealString.toGBK(shjxjModel.getShyg2());
		String shyg3 = DealString.toGBK(shjxjModel.getShyg3());
		String shyg4 = DealString.toGBK(shjxjModel.getShyg4());
		String ddj1 = DealString.toGBK(shjxjModel.getDdj1());
		String ddj2 = DealString.toGBK(shjxjModel.getDdj2());
		String ddj3 = DealString.toGBK(shjxjModel.getDdj3());
		String ddj4 = DealString.toGBK(shjxjModel.getDdj4());
		String bxkpjcj1 = DealString.toGBK(shjxjModel.getBxkpjcj1());
		String bxkpjcj2 = DealString.toGBK(shjxjModel.getBxkpjcj2());
		String bxkpjcj3 = DealString.toGBK(shjxjModel.getBxkpjcj3());
		String bxkpjcj4 = DealString.toGBK(shjxjModel.getBxkpjcj4());
		String bjcjpx1 = DealString.toGBK(shjxjModel.getBjcjpx1());
		String bjcjpx2 = DealString.toGBK(shjxjModel.getBjcjpx2());
		String bjcjpx3 = DealString.toGBK(shjxjModel.getBjcjpx3());
		String bjcjpx4 = DealString.toGBK(shjxjModel.getBjcjpx4());
		String zycjpx1 = DealString.toGBK(shjxjModel.getZycjpx1());
		String zycjpx2 = DealString.toGBK(shjxjModel.getZycjpx2());
		String zycjpx3 = DealString.toGBK(shjxjModel.getZycjpx3());
		String zycjpx4 = DealString.toGBK(shjxjModel.getZycjpx4());
		String tyhgbz1 = DealString.toGBK(shjxjModel.getTyhgbz1());
		String tyhgbz2 = DealString.toGBK(shjxjModel.getTyhgbz2());
		String tyhgbz3 = DealString.toGBK(shjxjModel.getTyhgbz3());
		String tyhgbz4 = DealString.toGBK(shjxjModel.getTyhgbz4());
		String wysp = DealString.toGBK(shjxjModel.getWysp());
		String sjhm = DealString.toGBK(shjxjModel.getSjhm());
		boolean bDel = StandardOperation.delete("xsjxjb", "xh||jxjdm||xn", xh + jxjdm + xn, request);
		if (bDel) {
			String sql = "insert into xsjxjb (xh, jxjdm, xn, nd, sqsj, ffbj, ffsj, ffwjh, xq, drzw, xxjl)"
					+ " values (?,?,?,?,to_char(sysdate,'yyyymmddhh24miss'),?,?,?,?,?,?)";
			bFlag = dao.runUpdate(sql, new String[] { xh, jxjdm, xn, nd, "0", "",
					"", xq, drzw, xxjl });
			if (bFlag) {// 保存成功则继续保存学生其它信息
				boolean bTemp = StandardOperation.delete("xsjxjxxb",
						new String[] { "xh" }, new String[] { xh }, request);// 避免数据重复先删后增
				if (bTemp) {
					StandardOperation.insert("xsjxjxxb", new String[] { "xh",
							"jxj1", "shyg1", "ddj1", "bxkpjcj1", "bjcjpx1",
							"zycjpx1", "tyhgbz1", "jxj2", "shyg2", "ddj2",
							"bxkpjcj2", "bjcjpx2", "zycjpx2", "tyhgbz2",
							"jxj3", "shyg3", "ddj3", "bxkpjcj3", "bjcjpx3",
							"zycjpx3", "tyhgbz3", "jxj4", "shyg4", "ddj4",
							"bxkpjcj4", "bjcjpx4", "zycjpx4", "tyhgbz4" },
							new String[] { xh, jxj1, shyg1, ddj1, bxkpjcj1,
									bjcjpx1, zycjpx1, tyhgbz1, jxj2, shyg2,
									ddj2, bxkpjcj2, bjcjpx2, zycjpx2, tyhgbz2,
									jxj3, shyg3, ddj3, bxkpjcj3, bjcjpx3,
									zycjpx3, tyhgbz3, jxj4, shyg4, ddj4,
									bxkpjcj4, bjcjpx4, zycjpx4, tyhgbz4 },
							request);
					sql = "select count(*) num from xsfzxxb where xh = ?";
					String sNum = dao.getOneRs(sql, new String[] { xh },
							new String[] { "num" })[0];// 检验数据是否存在
					sql = "select count(*) num from xsfzxxb where xh=?";
					sql = "update xsfzxxb set sjhm=?,wysp=? where xh=?";
					if (StringUtils.isEqual(sNum, "0")) {
						sql = "insert into xsfzxxb(sjhm,wysp,xh) values(?,?,?)";
					}// end if
					dao.runUpdate(sql, new String[] { sjhm, wysp, xh });//保存学生手机，外语水平信息
				}//end if
			}//end if
		}//end if
		return bFlag;
	}

	/**
	 * 社会奖学金查询表头
	 * shjxjtit ---- 社会奖学金表头 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getShJxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[] {"xh||jxjdm||xn", "nd", "xn", "xh", "xm", "bjmc", "jxjdm",
				"jxjmc", "fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "主键", "年度", "学年", "学号", "姓名", "班级名称", "奖学金代码",
				"奖学金名称", "辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * 社会奖学金查询结果
	 * shjxjres ---- 社会奖学金结果 
	 * @param shjxjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getShJxjRes(QueryShJxjModel shjxjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select jxjdm from jxjdmb where jxjmc = ? and rownum = 1";//社会奖学金代码
		String sJxjdm = dao.getOneRs(sql, new String[]{"社会奖学金"}, new String[]{"jxjdm"})[0];
		sql = "select xh||jxjdm||xn,nd,xn,xh,xm,bjmc,jxjdm,jxjmc,fdysh,xysh,xxsh from view_xsjxjb where jxjdm = '" + sJxjdm + "' ";
		String[] colList = new String[]{"xh||jxjdm||xn", "nd", "xn", "xh", "xm", "bjmc", "jxjdm", "jxjmc", "fdysh", "xysh", "xxsh"};
		StringBuffer whereSql = getWhereSql(shjxjModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 通过主键获取社会奖学金信息
	 * shjxjbyPkval ---- 通过主键获取社会奖学金
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getShJxjByPkVal(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,jxjmc,jxjdm,jxjlb,jlje,fdysh,xysh,xxsh,fdyyj,xyshyj,xxshyj from view_xsjxjb where xh||jxjdm||xn = ?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue.trim()});
		return resMap;
	}
	
	/**
	 * 公用方法：获取查询条件
	 * @param shjxjModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(QueryShJxjModel shjxjModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = shjxjModel.getXn();
		String xh = DealString.toGBK(shjxjModel.getXh());
		String xydm = shjxjModel.getXydm();
		String zydm = shjxjModel.getZydm();
		String bjdm = shjxjModel.getBjdm();
		String nj = shjxjModel.getNj();
		String xq = shjxjModel.getXq();
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}//end if
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(xh);
		}//end if
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}//end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}//end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}//end if
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}//end if
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}//end if
		return whereSql;
	}

	/**
	 * 公用方法：批量删除
	 * @param keys 主键
	 * @return
	 * @throws Exception
	 */
	public String delInfoByPk(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i].trim());// 得到主键
			sql = "delete from xsjxjb where xh||jxjdm||xn = '" + whichxh
					+ "'";
			// 把主键组合成sql语句
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}// end if
		}// end for
		return whichpk;
	}
	
	/**
	 * 社会奖学金审核查询表头
	 * shjxjtit ---- 社会奖学金表头 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getShJxjTit2(String sUserType, String sIsFdy) throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[] {"xh||jxjdm||xn", "bgcolor", "nd", "xn", "xh", "xm", "bjmc",
				"jxjmc", "sh" };
		String[] cnList = null;
		if (StringUtils.isEqual(sIsFdy, "true")) {
			cnList = new String[] { "主键","bgcolor", "年度", "学年", "学号", "姓名", "班级名称",
					"奖学金名称", "辅导员审核"};
		}//end if 辅导员用户
		if (StringUtils.isEqual(sUserType, "xy") && !(StringUtils.isEqual(sIsFdy, "true"))) {
			cnList = new String[] { "主键", "bgcolor","年度", "学年", "学号", "姓名", "班级名称",
					"奖学金名称", Base.YXPZXY_KEY+"审核"};
		}//end if 学院用户
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			cnList = new String[] { "主键", "bgcolor","年度", "学年", "学号", "姓名", "班级名称",
					"奖学金名称", "学校审核"};
		}// end if 学校用户,管理员
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * 社会奖学金审核查询结果
	 * shjxjres ---- 社会奖学金审核结果
	 * @param shjxjModel
	 * @param sUserType
	 * @param sIsFdy
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getShJxjRes2(QueryShJxjModel shjxjModel,
			String sUserType, String sIsFdy) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select jxjdm from jxjdmb where jxjmc = ? and rownum = 1";// 社会奖学金代码
		String sJxjdm = dao.getOneRs(sql, new String[] { "社会奖学金" },
				new String[] { "jxjdm" })[0];
		String[] opList = new String[] { "xh||jxjdm||xn","bgcolor", "nd", "xn", "xh",
				"xm", "bjmc", "jxjmc", "sh" };
		if (StringUtils.isEqual(sIsFdy, "true")) {
			sql = "select xh||jxjdm||xn,(case when(fdysh='未审核') then '#DDDDDD' else '#FFFFFF' end) bgcolor,xn,nd,xh,xm,bjmc,jxjmc,fdysh sh from view_xsjxjb where jxjdm='"
					+ sJxjdm + "' ";
		}// end if 辅导员
		if (StringUtils.isEqual(sUserType, "xy")
				&& !(StringUtils.isEqual(sIsFdy, "true"))) {
			sql = "select xh||jxjdm||xn,(case when(xysh='未审核') then '#DDDDDD' else '#FFFFFF' end) bgcolor,xn,nd,xh,xm,bjmc,jxjmc,xysh sh from view_xsjxjb where jxjdm='"
					+ sJxjdm + "' and fdysh='通过'";
		}// end if 学院
		if (StringUtils.isEqual(sUserType, "xx")
				|| StringUtils.isEqual(sUserType, "admin")) {
			sql = "select xh||jxjdm||xn,(case when(xxsh='未审核') then '#DDDDDD' else '#FFFFFF' end) bgcolor,xn,nd,xh,xm,bjmc,jxjmc,xxsh sh from view_xsjxjb where jxjdm='"
					+ sJxjdm + "' and fdysh='通过' and xysh='通过'";
		}// end if 学校
		StringBuffer whereSql = getWhereSql(shjxjModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return resList;
	}
	
	/**
	 * 辅导员审核
	 * shjxjbyfdysh ---- 辅导员审核社会奖学金
	 * @param shjxjModel
	 * @param keys 主键
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] shjxjByFdySh(ShShJxjModel shjxjModel, String[] keys,
			HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String fdysh = shjxjModel.getShjg();//辅导员审核
		String fdyyj = DealString.toGBK(shjxjModel.getShyj());//辅导员意见
		fdysh = "tg".equalsIgnoreCase(fdysh) ? "通过" : ("btg".equalsIgnoreCase(fdysh) ? "不通过" : "未审核");
		for (int i = 0; i < keys.length; i++ ) {
			boolean bTemp = StandardOperation.update("xsjxjb",
					new String[] { "fdysh", "fdyyj" }, new String[] { fdysh,
							fdyyj }, "xh||jxjdm||xn",
					DealString.toGBK(keys[i].trim()), request);
			result[i] = bTemp;
		}
		return result;
	}
	
	/**
	 * 学院审核
	 * shjxjbyXysh ---- 学院审核社会奖学金
	 * @param shjxjModel
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] shjxjByXySh(ShShJxjModel shjxjModel, String[] keys,
			HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String xysh = shjxjModel.getShjg();//学院审核
		String xyyj = DealString.toGBK(shjxjModel.getShyj());//学院意见
		xysh = "tg".equalsIgnoreCase(xysh) ? "通过" : ("btg".equalsIgnoreCase(xysh) ? "不通过" : "未审核");
		for (int i = 0; i < keys.length; i++ ) {
			boolean bTemp = StandardOperation.update("xsjxjb",
					new String[] { "xysh", "xyshyj" }, new String[] { xysh,
							xyyj }, "xh||jxjdm||xn",
					DealString.toGBK(keys[i].trim()), request);
			result[i] = bTemp;
		}
		return result;
	}

	/**
	 * 学校审核
	 * shjxjbyXxsh ---- 学校审核社会奖学金
	 * @param shjxjModel
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] shjxjByXxSh(ShShJxjModel shjxjModel, String[] keys,
			HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String xxsh = shjxjModel.getShjg();//学校审核
		String xxyj = DealString.toGBK(shjxjModel.getShyj());//学校意见
		xxsh = "tg".equalsIgnoreCase(xxsh) ? "通过" : ("btg".equalsIgnoreCase(xxsh) ? "不通过" : "未审核");
		for (int i = 0; i < keys.length; i++ ) {
			boolean bTemp = StandardOperation.update("xsjxjb",
					new String[] { "xxsh", "xxshyj" }, new String[] { xxsh,
							xxyj }, "xh||jxjdm||xn",
					DealString.toGBK(keys[i].trim()), request);
			result[i] = bTemp;
		}
		return result;
	}
	
	/**
	 * 通过主键获取社会奖学金信息
	 * shjxjbyPkval ---- 通过主键获取社会奖学金
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getShJxjByPkVal2(String sql, String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * 辅导员审核
	 * shjxjbyfdysh ---- 辅导员审核社会奖学金
	 * @param shjxjModel
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean shjxjByFdySh1(ShShJxjModel shjxjModel, String pkValue,
			HttpServletRequest request) throws Exception {
		boolean bResult = false;
		String sJg = DealString.toGBK(shjxjModel.getYesNo());//审核结果
		String sYj = DealString.toGBK(shjxjModel.getShyj());//审核意见
		bResult = StandardOperation.update("xsjxjb", new String[] { "fdysh",
				"fdyyj" }, new String[] { sJg, sYj }, "xh||jxjdm||xn", pkValue,
				request);
		return bResult;
	}
	
	/**
	 * 学院审核
	 * shjxjbyXysh ---- 学院审核社会奖学金
	 * @param shjxjModel
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean shjxjByXySh1(ShShJxjModel shjxjModel, String pkValue,
			HttpServletRequest request) throws Exception {
		boolean bResult = false;
		String sJg = DealString.toGBK(shjxjModel.getYesNo());//审核结果
		String sYj = DealString.toGBK(shjxjModel.getShyj());//审核意见
		bResult = StandardOperation.update("xsjxjb", new String[] { "xysh",
				"xyshyj" }, new String[] { sJg, sYj }, "xh||jxjdm||xn", pkValue,
				request);
		return bResult;
	}
	
	/**
	 * 学校审核
	 * shjxjbyXxsh ---- 学校审核社会奖学金
	 * @param shjxjModel
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean shjxjByXxSh1(ShShJxjModel shjxjModel, String pkValue,
			HttpServletRequest request) throws Exception {
		boolean bResult = false;
		String sJg = DealString.toGBK(shjxjModel.getYesNo());//审核结果
		String sYj = DealString.toGBK(shjxjModel.getShyj());//审核意见
		bResult = StandardOperation.update("xsjxjb", new String[] { "xxsh",
				"xxshyj" }, new String[] { sJg, sYj }, "xh||jxjdm||xn", pkValue,
				request);
		return bResult;
	}
	
	/**
	 *审核列表
	 * @param iType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChkList(int iType) {
		return dao.getChkList(iType);
	}
	
	/**
	 * 奖学金类别列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjLb() throws Exception {
		List<HashMap<String, String>> jxjlbList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"院级", "系级"};
		String[] cnList = new String[]{"院级", "系级"};
		for ( int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("en", enList[i]);
			tmpMap.put("cn", cnList[i]);
			jxjlbList.add(tmpMap);
		}
		return jxjlbList;
	}
	
	/**
	 * 保存前检查数据是否重复
	 * @param pk
	 * @param pkValue
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataExists(String pk, String pkValue, String tableName) throws Exception {
		boolean bExists = false;
		String sql = "select count(*) num from " + tableName + " where " + pk + " = " + pkValue;
		String[] tmp = dao.getOneRs(sql, new String[]{}, new String[]{"num"});
		if (tmp != null && tmp.length > 0 && tmp[0] != "0") {
			bExists = true;
		}
		return bExists;
	}
	
	/**
	 * 奖学金代码保存
	 * @param jxjdmModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjdm(JxjdmModel jxjdmModel, HttpServletRequest request) throws Exception{
		String jxjlb = DealString.toGBK(jxjdmModel.getJxjlb());
		return StandardOperation.insert("jxjdmb", new String[] { "jxjdm",
				"jxjmc", "jxjlb", "jxjjb", "jlje", "szjdbz", "xydm" ,"sztzxfbz","bmmc", "fbr"},
				new String[] { jxjdmModel.getXydm() + DealString.toGBK(jxjdmModel.getJxjdm()),
						DealString.toGBK(jxjdmModel.getJxjmc()),
						jxjlb, jxjdmModel.getJxjjb(),
						jxjdmModel.getJlje(), jxjdmModel.getSzjdbz(),
						jxjdmModel.getXydm() , jxjdmModel.getSztzxfbz(),dao.getOneRs("select bmmc from zxbz_xxbmdm where bmdm='"+jxjdmModel.getXydm()+"'", new String[]{}, "bmmc"),dao.getOneRs("select xm from yhb where yhm=?", new String[]{request.getSession().getAttribute("userName").toString()}, "xm")}, request);
	}
	
	/**
	 * 荣誉称号代码保存
	 * @param rychdmModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveRychdm(RychdmModel rychdmModel, HttpServletRequest request) throws Exception {
		String rychlb = DealString.toGBK(rychdmModel.getRychlb());
		return StandardOperation.insert("rychdmb", new String[] { "rychdm",
				"rychmc", "rychlb", "xydm" ,"bmmc","fbr"}, new String[] {
				DealString.toGBK(rychdmModel.getXydm() + rychdmModel.getRychdm()), DealString.toGBK(rychdmModel.getRychmc()), rychlb,
				rychdmModel.getXydm(),dao.getOneRs("select bmmc from zxbz_xxbmdm where bmdm='"+rychdmModel.getXydm()+"'", new String[]{}, "bmmc"),dao.getOneRs("select xm from yhb where yhm=?", new String[]{request.getSession().getAttribute("userName").toString()}, "xm") }, request);
	}
	
	/**
	 * 军训奖项保存
	 * @param jxjxdmModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjxdm(JxjxdmModel jxjxdmModel, HttpServletRequest request) throws Exception{
		return StandardOperation.insert("jxjxdmb", new String[] { "jxdm",
				"jxmc" }, new String[] { jxjxdmModel.getJxdm(),
				DealString.toGBK(jxjxdmModel.getJxmc()) }, request);
	}
	
	/**
	 * 奖学金代码删除
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean delJxjdm(String pkValue, HttpServletRequest request) throws Exception {
		return StandardOperation.delete("jxjdmb", "jxjdm", pkValue, request);
	}
	
	/**
	 * 荣誉称号代码删除
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean delRychdm(String pkValue, HttpServletRequest request) throws Exception {
		return StandardOperation.delete("rychdmb", "rychdm", pkValue, request);
	}
	
	/**
	 * 军训代码删除
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean delJxjxdm(String pkValue, HttpServletRequest request) throws Exception {
		return StandardOperation.delete("jxjxdmb", "jxdm", pkValue, request);
	}
	
	/**
	 * 全部删除 
	 * @param tableName
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean delDm(String tableName, HttpServletRequest request) throws Exception{
		String sql = "delete from " + tableName;
		return dao.runUpdate(sql, new String[]{});
	}
	
	/**
	 * 获取奖学金代码信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjdmInfo(String pkValue) throws Exception {
		String sql = "select jxjdm,jxjmc,jxjlb,jxjjb,jlje,szjdbz,xydm,sztzxfbz from jxjdmb where jxjdm=? and rownum=1";
		return dao.getMapNotOut(sql, new String[]{pkValue});
	}
	
	/**
	 * 获取荣誉称号代码信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRychdmInfo(String pkValue) throws Exception {
		String sql = "select rychdm,rychmc,rychlb,xydm from rychdmb where rychdm=? and rownum=1";
		return dao.getMapNotOut(sql, new String[]{pkValue});
	}
	
	/**
	 * 获取军训代码信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjxdmInfo(String pkValue) throws Exception {
		String sql = "select jxdm,jxmc from jxjxdmb where jxdm=? and rownum=1";
		return dao.getMapNotOut(sql, new String[]{pkValue});
	}
	
	/**
	 * 获取学工部代码
	 * @return
	 * @throws Exception
	 */
	public String getXgbdm() throws Exception {
		String sql = "select xgbdm from xtszb where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[]{}, new String[]{"xgbdm"});
		if (tmp != null && tmp.length > 0) {
			return tmp[0];
		} else {
			return "";
		}
	}
	
	/**
	 * 奖学金代码保存
	 * @param jxjdmModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjdm1(JxjdmModel jxjdmModel, HttpServletRequest request) throws Exception{
		String jxjlb = DealString.toGBK(jxjdmModel.getJxjlb());
		return StandardOperation.insert("jxjdmb", new String[] { "jxjdm",
				"jxjmc", "jxjlb", "jxjjb", "jlje", "szjdbz", "xydm" ,"sztzxfbz", "bmmc", "fbr"},
				new String[] {DealString.toGBK(jxjdmModel.getJxjdm()),
						DealString.toGBK(jxjdmModel.getJxjmc()),
						jxjlb, jxjdmModel.getJxjjb(),
						jxjdmModel.getJlje(), jxjdmModel.getSzjdbz(),
						jxjdmModel.getXydm(), jxjdmModel.getSztzxfbz(),dao.getOneRs("select bmmc from zxbz_xxbmdm where bmdm = ?", new String[]{jxjdmModel.getXydm()}, "bmmc"),dao.getOneRs("select xm from yhb where yhm=?", new String[]{request.getSession().getAttribute("userName").toString()}, "xm") }, request);
	}
	
	/**
	 * 荣誉称号代码保存
	 * @param rychdmModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveRychdm1(RychdmModel rychdmModel, HttpServletRequest request) throws Exception {
		String rychlb = DealString.toGBK(rychdmModel.getRychlb());
		return StandardOperation.insert("rychdmb", new String[] { "rychdm",
				"rychmc", "rychlb", "xydm" ,"bmmc", "fbr"}, new String[] {
				DealString.toGBK( rychdmModel.getRychdm()), DealString.toGBK(rychdmModel.getRychmc()), rychlb,
				rychdmModel.getXydm(),dao.getOneRs("select bmmc from zxbz_xxbmdm where bmdm = ?", new String[]{rychdmModel.getXydm()}, "bmmc"),dao.getOneRs("select xm from yhb where yhm=?", new String[]{request.getSession().getAttribute("userName").toString()}, "xm") }, request);
	}
	
	/**
	 * 军训奖项保存
	 * @param jxjxdmModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjxdm1(JxjxdmModel jxjxdmModel, HttpServletRequest request) throws Exception{
		return StandardOperation.insert("jxjxdmb", new String[] { "jxdm",
				"jxmc" }, new String[] { jxjxdmModel.getJxdm(),
				DealString.toGBK(jxjxdmModel.getJxmc()) }, request);
	}
	
	/**
	 * 检查用户是否有权修改此信息
	 * @param xydm
	 * @param dm
	 * @param pkValue
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public boolean chkUserWritable(String xydm, String dm, String pkValue, String tableName) throws Exception {
		String sql = "select xydm from "+ tableName +" where " + dm + " = " + pkValue;
		String[] tmp = dao.getOneRs(sql, new String[]{}, new String[]{"xydm"});
		if (tmp != null && tmp.length > 0 && tmp[0].equalsIgnoreCase(xydm)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 辅导员获取对应班级列表
	 * @param fdyName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> fdyGetBjList(String fdyName) throws Exception {
		List<HashMap<String, String>> bjList = new ArrayList<HashMap<String,String>>();
		String sql = "select bjdm from fdybjb where zgh=?";
		String[] bjdmList = dao.getRs(sql, new String[]{fdyName}, "bjdm");
		if (bjdmList != null && bjdmList.length > 0) {
			String[] bjmcList = new String[bjdmList.length];
			for (int i = 0; i < bjdmList.length; i++) {
				bjmcList[i] = dao.getOneRs("select bjmc from view_njxyzybj where bjdm = ?", new String[]{bjdmList[i]}, "bjmc");
			}
			bjList = dao.arrayToList(bjdmList, bjmcList);
		}
		return bjList;
	}
	
	/**
	 * 辅导员获取对应专业列表
	 * @param fdyName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> fdyGetZyList(String fdyName) throws Exception {
		List<HashMap<String, String>> zyList = new ArrayList<HashMap<String,String>>();
		String sql = "select bjdm from fdybjb where zgh=?";
		String[] bjdmList = dao.getRs(sql, new String[]{fdyName}, "bjdm");
		if (bjdmList != null && bjdmList.length > 0) {
			StringBuffer strSql = new StringBuffer("select zydm from view_njxyzybj where bjdm in (");
			for (int i=0;i<bjdmList.length;i++) {
				strSql.append("'");
				strSql.append(bjdmList[i]);
				strSql.append("',");
			}
			sql = strSql.substring(0, strSql.length()-1).toString() + ") group by zydm";
			String[] zydmList = dao.getRs(sql, new String[]{}, "zydm");
			String[] zymcList = null;
			if (zydmList != null && zydmList.length>0) {
				zymcList = new String[zydmList.length];
				for (int i=0;i<zydmList.length;i++) {
					zymcList[i] = dao.getOneRs("select zymc from view_njxyzybj where zydm=?", new String[]{zydmList[i]}, "zymc");
				}
			}
			zyList = dao.arrayToList(zydmList, zymcList);
		}
		return zyList;
	}
	
	/**
	 * 奖学金报表打印
	 * @param xh
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjexpData(String xh, String jxjdm) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String[] jxjsqxnnd = dao.getOneRs("select jxjsqxn,jxjsqnd from xtszb where rownum=1", new String[]{}, new String[]{"jxjsqxn", "jxjsqnd"}); 
		String[] opList = new String[]{"xh", "xn", "xm", "nd", "nj", "xb", "xymc", "zymc", "bjmc", "jxjmc", "jxjlb", "jlje", "drzw", "dnshjxj", "xxjl", "kycg", "sqly"};
		resMap = dao.getMap("select a.xh,a.xn,a.xm,a.nd,a.nj,a.xb,a.xymc,a.zymc,a.bjmc,a.jxjmc,a.drzw,a.dnshjxj,a.xxjl,a.kycg,a.sqly,b.jxjlb,b.jlje from view_xsjxjb a left join jxjdmb b on a.jxjdm=b.jxjdm where a.xh=? and a.xn=? and a.nd=? and a.jxjdm=?", new String[]{xh,jxjsqxnnd != null ? jxjsqxnnd[0] : "", jxjsqxnnd != null ? jxjsqxnnd[1]: "",jxjdm}, opList);
		String[] xsfzxx = dao.getOneRs("select sjhm,wysp from xsfzxxb where xh=?", new String[]{xh}, new String[]{"sjhm", "wysp"});
		if (xsfzxx != null && xsfzxx.length == 2) {
			resMap.put("sjhm", xsfzxx[0]);
			resMap.put("wysp", xsfzxx[1]);
		}
		return resMap;
	}
	
	public ArrayList<String[]> getJxjexpData1(String xh) throws Exception {
		String[] opList = new String[]{"jxj1", "shyg1", "ddj1", "bxkpjcj1", "bjcjpx1", "zycjpx1", "tyhgbz1","jxj2", "shyg2", "ddj2", "bxkpjcj2", "bjcjpx2", "zycjpx2", "tyhgbz2","jxj3", "shyg3", "ddj3", "bxkpjcj3", "bjcjpx3", "zycjpx3", "tyhgbz3","jxj4", "shyg4", "ddj4", "bxkpjcj4", "bjcjpx4", "zycjpx4", "tyhgbz4", };
		return dao.rsToVator("select * from xsjxjxxb where xh=?", new String[]{xh}, opList);
	}
	
	/**
	 * 自动获取奖学金，荣誉称号代码
	 * @return
	 * @throws Exception
	 */
	public String getAutoJxjId() throws Exception {
		return dao.getOneRs("select autojxjid.nextval num from dual", new String[]{}, "num");
	}
	
	/**
	 * 奖学金修改信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewJxjxx(String pkValue) throws Exception {
		HashMap<String, String> rsMap = new HashMap<String, String>();
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc," +
				"(select b.jxjlb from jxjdmb b where a.jxjdm=b.jxjdm) jxjlb," +
				"a.jxjdm,(select b.jlje from jxjdmb b where a.jxjdm=b.jxjdm) jlje," +
				"a.xn,a.nd,a.dnshjxj,a.drzw,a.xxjl,a.kycg,a.sqly,(select b.sjhm from" +
				" xsfzxxb b where a.xh=b.xh) sjhm,(select b.wysp from xsfzxxb b where" +
				" a.xh=b.xh) wysp,b.* from view_xsjxjb a left join xsjxjxxb b on a.xh=b.xh where a.xn||a.nd||a.xh||a.jxjdm = ?";
		rsMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return rsMap;
	}
	
	/**
	 * 奖学金列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjListN() throws Exception {
		return dao.getList("select jxjdm,jxjmc", new String[] {}, new String[] {	
				"jxjdm", "jxjmc" });
	}
	
	public boolean stujxjDel(String pkValue, HttpServletRequest request) throws Exception {
		return StandardOperation.delete("xsjxjb", "xn||nd||xh||jxjdm", pkValue, request);
	}
	
	public boolean sturychDel(String pkValue, HttpServletRequest request) throws Exception {
		return StandardOperation.delete("xsrychb", "xn||nd||rychdm||xh", pkValue, request);
	}
	
	/**
	 * 学校选择学院后过滤奖学金条件
	 * @param xydm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xyJxjList(String xydm) throws Exception {
		List<HashMap<String, String>> jxjList = new ArrayList<HashMap<String,String>>();
		String sql = "select jxjdm,jxjmc from jxjdmb where 1=1 ";
		String[] opList = new String[]{"jxjdm", "jxjmc"};
		if (StringUtils.isNull(xydm)) {
			jxjList = dao.getList(sql, new String[]{}, opList);
		} else {
			jxjList = dao.getList(sql + " and xydm='"+xydm +"'", new String[]{}, opList);
		}
		return jxjList;
	}
	
	/**
	 * 学校选择学院后过滤荣誉称号条件
	 * @param xydm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xyRychList(String xydm) throws Exception {
		List<HashMap<String, String>> jxjList = new ArrayList<HashMap<String,String>>();
		String sql = "select rychdm,rychmc from rychdmb where 1=1 ";
		String[] opList = new String[]{"rychdm", "rychmc"};
		if (StringUtils.isNull(xydm)) {
			jxjList = dao.getList(sql, new String[]{}, opList);
		} else {
			jxjList = dao.getList(sql + " and xydm='"+xydm +"'", new String[]{}, opList);
		}
		return jxjList;
	}
	
	/**
	 * 宁波理工违纪处分文号检查
	 * @param xh
	 * @param cfwh
	 * @return
	 * @throws Exception
	 */
	public boolean chkStucfwh(String xh, String cfwh) throws Exception {
		String num = dao.getOneRs(
				"select count(*) num from wjcfb where xh=? and cfwh=?",
				new String[] { xh, cfwh }, "num");
		if (!StringUtils.isNull(num) && !"0".equalsIgnoreCase(num)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 检查学生是否是毕业生
	 * @param xh
	 * @return
	 */
	public boolean chkStuIsBys(String xh) {
		return StandardOperation.isBys(StringUtils.isNull(xh) ? "" : xh.trim());
	}
}
