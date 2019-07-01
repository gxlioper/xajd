package xgxt.wjcf.shgc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description: 上海工程技术大学违纪处分DAO继承公用DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: 李涛
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2008-05-19
 * </p>
 */
public class WjcfShgcDAO  {
	DAO dao = DAO.getInstance(); 
	ArrayList<String> values = new ArrayList<String>();// 查询条件值
    
	public List<HashMap<String, String>> getChkList(int type) {
		return dao.getChkList(type);
	}
	
	
	
	
	/**
	 * 获取处分类别列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCflbList() throws Exception {
		List<HashMap<String, String>> cflbList = new ArrayList<HashMap<String, String>>();
		String sql = "select cflbdm,cflbmc from cflbdmb";// 处分类别
		cflbList = dao.getList(sql, new String[] {}, new String[] { "cflbdm",
				"cflbmc" });
		return cflbList;
	}

	/**
	 * 获取处分原因列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCfyyList() throws Exception {
		List<HashMap<String, String>> cfyyList = new ArrayList<HashMap<String, String>>();
		String sql = "select cfyydm,cfyymc from cfyydmb";// 处分原因
		cfyyList = dao.getList(sql, new String[] {}, new String[] { "cfyydm",
				"cfyymc" });
		return cfyyList;
	}

	/**
	 * 获取学生信息
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getQryStuInfo(String xh, String userType)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from view_xsjbxx where xh = ?";
		String[] colList = dao.getColumnName("select * from view_xsjbxx where 1=2");// 输出列名
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
			map.put("stuExists", "yes");// 学号存在
		} else {
			map.put("stuExists", "no");// 学号无存在
		}// end if
		sql = "select dqxn,dqnd from xtszb where rownum=1";
		String tmp[] = dao.getOneRs(sql, new String[] {}, new String[] { "dqxn",
				"dqnd" });
		map.put("xn", tmp[0]);// 当前学年
		map.put("nd", tmp[1]);// 当前年度
		map.put("userType", userType);
		return map;
	}

	/**
	 * 保存学院申报信息
	 * 
	 * @param shgcxysbModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveXysbXx(WjcfShgcXysbModel shgcxysbModel, String xn,
			String nd) throws Exception {
		boolean flag = false;
		DAO dao=DAO.getInstance();
		String xxdm=dao.getXxdm();
		String xh = shgcxysbModel.getXh();
		String cflbdm = shgcxysbModel.getCflb();
		String cfyydm = shgcxysbModel.getCfyy();
		String xxyj=shgcxysbModel.getXxyj();
		String xyyj=shgcxysbModel.getXyyj();
		String xgcyj=shgcxysbModel.getXgcyj();
		String bzryj=shgcxysbModel.getBzryj();
		String jtwjsy = DealString.toGBK(shgcxysbModel.getJtwjsy());
		String zacfqk = DealString.toGBK(shgcxysbModel.getZacfqk());
		String qtcfqk = DealString.toGBK(shgcxysbModel.getQtcfqk());
		String bz = DealString.toGBK(shgcxysbModel.getBz());
		String sql="";
		String[]input=null;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NNZYJSXY)){
			sql = "insert into wjcfb (xh,xn,nd,cflb,cfyy,jtwjsy,zacfqk,qtcfqk,bz,xyyj,xxyj,bzryj,xgcyj,sbsj,xxsh,xq) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,'未审核',?)";
			input=new String[] { xh, xn, nd, cflbdm, cfyydm,jtwjsy, zacfqk,qtcfqk, bz,xyyj,xxyj,bzryj,xgcyj,shgcxysbModel.getSbsj(),shgcxysbModel.getXq()};
		}else{
			sql = "insert into wjcfb (xh,xn,nd,cflb,cfyy,jtwjsy,zacfqk,qtcfqk,bz,sbsj,xxsh,xq) values (?,?,?,?,?,?,?,?,?,?,'未审核',?)";
			input=new String[] { xh, xn, nd, cflbdm, cfyydm,jtwjsy, zacfqk, qtcfqk,bz, shgcxysbModel.getSbsj(),shgcxysbModel.getXq()};
		}
		flag = dao.runUpdate(sql, input);// 保存信息
		return flag;
	}

	/**
	 * 获取当前学年年度列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getXnNdList() throws Exception {
		String[] xnndList = null;
		String sql = "select dqxn,dqnd from xtszb where rownum=1";
		xnndList = dao.getOneRs(sql, new String[] {},
				new String[] { "dqxn", "dqnd" });
		return xnndList;
	}

	/**
	 * 获取查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getSearchTitle() throws Exception {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] enCol = { "xh||xn||nd||sbsj", "bgcolor", "rownum", "xn", "nd",
				"xh", "xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "xxsh" };
		String[] cnCol = { "主键", "bgcolor", "行号", "学年", "年度", "学号", "姓名",
				"班级名称", "处分类别", "处分原因", "申报时间", "学校审核" };
		for (int i = 0; i < enCol.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", enCol[i]);
			map.put("cn", cnCol[i]);
			result.add(map);
			map = null;
		}// end for
		return result;
	}

	/**
	 * 获取审核查询结果
	 * 
	 * @param shgcxxshqryModel
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getSearchResult(
			WjcfShgcXxshQryModel shgcxxshqryModel) throws Exception {
		ArrayList<String[]> result = new ArrayList<String[]>();
		String sql = "select xh||xn||nd||sbsj 主键,(case nvl(xxsh,'未审核') when '通过' then '#FFFFFF' when '未审核' then '#DDDDDD' else '#BBBBBB' end) bgcolor,rownum 行号,xn,nd,xh,xm,bjmc,cflbmc,cfyymc,sbsj,xxsh from view_wjcf where sbsj is not null ";
		String[] opCol = new String[] { "主键", "bgcolor", "行号", "xn", "nd",
				"xh", "xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "xxsh" };
		StringBuffer whereSql = getWhereSql(shgcxxshqryModel);
		result = dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);
		return result;
	}

	/**
	 * 获取学校审核的单个学生信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXxShInfo(String pkVal) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from view_wjcf where xh||xn||nd||sbsj = ?";
		map = dao.getMapNotOut(sql, new String[] { pkVal });
		return map;
	}

	/**
	 * 获取WHERE语句公用方法
	 * 
	 * @param shgcxxshqryModel
	 * @return
	 */
	public StringBuffer getWhereSql(WjcfShgcXxshQryModel shgcxxshqryModel) {
		StringBuffer whereSql = new StringBuffer();
		String xn = shgcxxshqryModel.getXn();
		String nd = shgcxxshqryModel.getNd();
		String nj = shgcxxshqryModel.getNj();
		String xydm = shgcxxshqryModel.getXydm();
		String cflb = shgcxxshqryModel.getCflb();
//		String xm = shgcxxshqryModel.getXm();
		String zydm = shgcxxshqryModel.getZydm();
		String xh = shgcxxshqryModel.getXh();
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}// end if
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}// end if
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}// end if
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}// end if
		if (!StringUtils.isNull(cflb)) {
			whereSql.append(" and cflb = ?");
			values.add(cflb);
		}// end if
		
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}// end if
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(DealString.toGBK(xh));
		}// end if
		if (!StringUtils.isNull(shgcxxshqryModel.getXm())) {
			whereSql.append(" and xm like ?");
			values.add("%" + DealString.toGBK(shgcxxshqryModel.getXm()) + "%");
		}
		if (!StringUtils.isNull(shgcxxshqryModel.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(shgcxxshqryModel.getBjdm());
		}
		return whereSql;
	}

	/**
	 * 违纪处分学校审核保存
	 * 
	 * @param shgcxxshModel
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public boolean saveXxshInfo(WjcfShgcXxshModel shgcxxshModel, String pkVal)
			throws Exception {
		boolean flag = false;
		String yesNo = DealString.toGBK(shgcxxshModel.getYesNo());
		String jtwjsy = DealString.toGBK(shgcxxshModel.getJtwjsy());
		String xxclyj = DealString.toGBK(shgcxxshModel.getXxclyj());
		String sql = "update wjcfb set xxsh = ?,jtwjsy = ?,xxclyj = ? where xh||xn||nd||sbsj = ?";
		flag = dao.runUpdate(sql, new String[] { yesNo, jtwjsy, xxclyj, pkVal });
		return flag;
	}

	/**
	 * 批量删除违纪处分信息
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delxxshInfo(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = keys[i];// 得到主键
			sql = "delete from wjcfb where xh||xn||nd||sbsj = '" + whichxh
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
	 * 通过主键得到学生信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getwjcfysbInfo(String pkVal, String xh, String cflb, String cfyy)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,a.xb,a.jtwjsy,a.zacfqk,a.qtcfqk,a.bz,b.rxrq,b.sfzh from view_wjcf a,view_xsxxb b where a.xh=b.xh and a.xh||a.xn||a.nd||a.sbsj = ?";
		if (StringUtils.isNull(pkVal)) {
			sql = "select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,a.xb,a.rxrq,a.sfzh from view_xsxxb a where a.xh = ?";
			map = dao.getMapNotOut(sql, new String[]{xh});
		} else {
			map = dao.getMapNotOut(sql, new String[] { pkVal });
		}
		return map;
	}

	/**
	 * 日期和文号维护查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getSearchTitle1()
			throws Exception {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] enCol = { "xh||xn||nd||sbsj", "bgcolor", "rownum", "xn", "nd",
				"xh", "xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "cfsj", "cfwh",
				"xxsh" };
		String[] cnCol = { "主键", "bgcolor", "行号", "学年", "年度", "学号", "姓名",
				"班级名称", "处分类别", "处分原因", "申报时间", "处分日期", "处分文号", "学生处审核" };
		for (int i = 0; i < enCol.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", enCol[i]);
			map.put("cn", cnCol[i]);
			result.add(map);
			map = null;
		}// end for
		return result;
	}

	/**
	 * 日期和文号维护查询结果
	 * 
	 * @param shgcxxshqryModel
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getSearchResult1(
			WjcfShgcXxshQryModel shgcxxshqryModel) throws Exception {
		ArrayList<String[]> result = new ArrayList<String[]>();
		String sql = "select xh||xn||nd||sbsj 主键,(case nvl(xxsh,'未审核') when '通过' then '#FFFFFF' when '未审核' then '#DDDDDD' else '#BBBBBB' end) bgcolor,rownum 行号,xn,nd,xh,xm,bjmc,cflbmc,cfyymc,sbsj,cfsj,cfwh,xxsh from view_wjcf where sbsj is not null and xxsh = '通过'";
		String[] opCol = new String[] { "主键", "bgcolor", "行号", "xn", "nd",
				"xh", "xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "cfsj", "cfwh",
				"xxsh" };
		StringBuffer whereSql = getWhereSql(shgcxxshqryModel);
		result = dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);
		return result;
	}

	/**
	 * 日期和文号维护
	 * 
	 * @param pkVal
	 * @param cfsj
	 * @param cfwh
	 * @return
	 * @throws Exception
	 */
	public boolean wjcfrqwhsh(String pkVal, String cfsj, String cfwh, String cflb, String cfyy)
			throws Exception {
		boolean flag = false;
		String xxdm = Base.xxdm;
		String sql = "";
		if (Globals.XXDM_HYGXY.equalsIgnoreCase(xxdm)) {
			sql = "update wjcfb set cfsj=?,cfwh=?,cflb=?,cfyy=? where xh||xn||nd||sbsj = ?";
			flag = dao.runUpdate(sql, new String[] { cfsj, cfwh,cflb,cfyy, pkVal });
		} else {
			sql = "update wjcfb set cfsj=?,cfwh=? where xh||xn||nd||sbsj = ?";
			flag = dao.runUpdate(sql, new String[] { cfsj, cfwh, pkVal });
		}
		return flag;
	}

	/**
	 * 获取处分原因
	 * 
	 * @param cfyy
	 * @return
	 * @throws Exception
	 */
	public String getCfYy(String cfyy) throws Exception {
		String cfyydm = "";
		String sql = "select cfyydm,cfyymc from cfyydmb where cfyymc = ?";
		cfyydm = dao.getOneRs(sql, new String[] { cfyy }, "cfyydm");
		return cfyydm;
	}

	/**
	 * 考试作弊数据维护查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSearchTitle2() throws Exception {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] enCol = { "xh||xn||nd||sbsj", "rownum", "xn", "nd", "xh",
				"xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "cfsj", "cfwh" };
		String[] cnCol = { "主键", "行号", "学年", "年度", "学号", "姓名", "班级名称", "处分类别",
				"处分原因", "申报时间", "处分日期", "处分文号" };
		for (int i = 0; i < enCol.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", enCol[i]);
			map.put("cn", cnCol[i]);
			result.add(map);
			map = null;
		}// end for
		return result;
	}

	/**
	 * 考试作弊数据维护查询结果
	 * 
	 * @param shgcxxshqryModel
	 * @param cfyydm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSearchResult2(
			WjcfShgcXxshQryModel shgcxxshqryModel, String cfyydm)
			throws Exception {
		ArrayList<String[]> result = new ArrayList<String[]>();
		String sql = "select xh||xn||nd||sbsj 主键,rownum 行号,xn,nd,xh,xm,bjmc,cflbmc,cfyymc,sbsj,cfsj,cfwh from view_wjcf where sbsj is not null and cfyymc='考试作弊' and cfyy='"
				+ cfyydm + "'";
		String[] opCol = new String[] { "主键", "行号", "xn", "nd", "xh", "xm",
				"bjmc", "cflbmc", "cfyymc", "sbsj", "cfsj", "cfwh" };
		StringBuffer whereSql = getWhereSql(shgcxxshqryModel);
		result = dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);
		return result;
	}

	/**
	 * 通过PK，PKVAL，TABLENAME获取学生信息公用方法
	 * 
	 * @param pk
	 * @param pkVal
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getxxinfo(String pk, String pkVal,
			String tableName) throws Exception { 
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select a.*,b.csrq,b.sfzh from " + tableName
				+ " a left join view_xsxxb b on a.xh=b.xh" + " where " + pk
				+ " = ?";
		map = dao.getMapNotOut(sql, new String[] { pkVal });
		return map;
	}

	/**
	 * 获取学生年龄
	 * 
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public String getxxNn(HashMap<String, String> rs) throws Exception {
		String nn = "";
		String csrq = rs.get("csrq");
		String sfzh = rs.get("sfzh");
		if (StringUtils.isNull(csrq)) {
			csrq = !StringUtils.isNull(sfzh) && sfzh.length() > 15 ? sfzh.substring(6, 14) : "";
		}
		if (!StringUtils.isNull(csrq)) {
			String sql = "select floatToInt((months_between(to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'),to_date('"
					+ csrq + "','yyyy-mm-dd')))/12) nn from dual";
			nn = dao.getOneRs(sql, new String[] {},"nn" );// 获取年龄
			
		}// end if
		return nn;
	}

	/**
	 * 未通过审核信息表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSearchTitle3() throws Exception {
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] enCol = { "xh||sbsj", "rownum", "xn", "nd", "xh", "xm",
				"bjmc", "cflbmc", "cfyymc", "sbsj", "xxsh" };
		String[] cnCol = { "主键", "行号", "学年", "年度", "学号", "姓名", "班级名称", "处分类别",
				"处分原因", "申报时间", "学校审核" };
		for (int i = 0; i < enCol.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", enCol[i]);
			map.put("cn", cnCol[i]);
			result.add(map);
			map = null;
		}// end for
		return result;
	}

	/**
	 * 未通过审核信息查询结果
	 * 
	 * @param shgcxxshqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSearchResult3(WjcfShgcXxshQryModel shgcxxshqryModel)
			throws Exception {
		List<String[]> result = new ArrayList<String[]>();
		StringBuffer whereSql = getWhereSql(shgcxxshqryModel);
		String[] colList = new String[] { "bgcolor", "主键", "行号", "xn", "nd",
				"xh", "xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "xxsh" };
		String sql = "select xh||sbsj 主键,rownum 行号,(case nvl(a.xxsh,'未审核') when '通过' then '#FFFFFF' when '未审核' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
				+ " a.* from view_wjcf a where 1=1 "
				+ " and sbsj is not null and xxsh='不通过' ";
		result = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		System.out.println(sql + whereSql);
		return result;
	}

	/**
	 * 未通过审核信息批量删除
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String deleteWtgWjxx(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = keys[i];// 得到主键
			sql = "delete from wjcfb where xh||sbsj = '" + whichxh + "'";
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
	 * 公用方法通过PK，PKVAL，tableName，COLLIST获取查询出来的数据
	 * 
	 * @param pk
	 * @param pkVal
	 * @param sql
	 * @param colList
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getFindResult(String pk, String pkVal,
			String tableName, String[] colList) throws Exception {
		HashMap<String, String> result = new HashMap<String, String>();
		String sql = "select * from " + tableName + " where " + pk + " = '"
				+ pkVal + "'";
		String rs[] = dao.getOneRs(sql, new String[] {}, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				result.put(colList[i].toLowerCase(), rs[i]);
			}// end for
		}// end if
		return result;
	}

	// 获取文件上传列表
	public List<HashMap<String, String>> getFileList(String pkValue) {
		pkValue = "%" + pkValue + "%";
		String sql = "select length(xh||cfwh||cfsj)len, cfwh,cflbmc,cfyymc,sssj,wjsclj from wjcf_xsssb where xh||cfwh||cfsj like ? and wjsclj is not null";
		List<HashMap<String, String>> rs = dao.getList(sql,
				new String[] { pkValue }, new String[] { "len", "cfwh",
						"cflbmc", "cfyymc", "sssj", "wjsclj" });
		return rs;
	}

	/**
	 * 通过INT PARAM 来输出不同的列表
	 */
	public List<HashMap<String, String>> getChkList1(int type) {
		String[] sl = null;
		if (type == 1) {
			sl = new String[] { "等待受理", "已受理", "不受理" };
		}// end if
		if (type == 2) {
			sl = new String[] { "等待复查", "复查结束", "本人撤诉", "更改处分", "维持原处分" };
		}// end if
		return dao.arrayToList(sl, sl);
	}

	/**
	 * 保存申诉受理信息
	 * 
	 * @param shgcssslModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveSsSlXx(WjcfShgcSsSlModel shgcssslModel, String pkVal,
			HttpServletRequest request) throws Exception {
		boolean flag = false;
		String slqk = DealString.toGBK(shgcssslModel.getSlqk());
		String slrq = shgcssslModel.getSlrq();
		String sltzs = DealString.toGBK(shgcssslModel.getSltzs());
		flag = StandardOperation.update("wjcf_xsssb", new String[] { "slqk",
				"slrq", "sltzs" }, new String[] { slqk, slrq, sltzs },
				"xh||cfwh||cfsj", pkVal, request);// 调用STANDARDOPERATION类的方法将自动写入日志
		return flag;
	}

	/**
	 * 保存申诉决定信息
	 * 
	 * @param shgcssjdModel
	 * @param pkVal
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveSsJdXx(WjcfShgcSsJdModel shgcssjdModel, String pkVal,
			HttpServletRequest request) throws Exception {
		boolean flag = false;
		String fcrq = shgcssjdModel.getFcrq();
		String mqzt = DealString.toGBK(shgcssjdModel.getMqzt());
		String csqk = DealString.toGBK(shgcssjdModel.getCsqk());
		String fcqk = DealString.toGBK(shgcssjdModel.getFcqk());
		String fctzs = DealString.toGBK(shgcssjdModel.getFctzs());
		String jdsj = shgcssjdModel.getJdsj();
		String jdwh = DealString.toGBK(shgcssjdModel.getJdwh());
		flag = StandardOperation.update("wjcf_xsssb", new String[] { "fcrq",
				"mqzt", "csqk", "fcqk", "fctzs" , "jdsj", "jdwh"}, new String[] { fcrq, mqzt,
				csqk, fcqk, fctzs, jdsj, jdwh }, "xh||cfwh||cfsj", pkVal, request);
		//如果插入成功则更新WJCFB表申诉信息
		if (flag) {
			if (!StringUtils.isNull(jdwh)) {
				StandardOperation.update("wjcfb", new String[] { "cxsj", "cxwh",
				"ssjg" }, new String[] { jdsj, jdwh, mqzt },
				"xh||cfwh||cfsj", pkVal, request);
			}//end if
		}//end if
		return flag;
	}
	
	/**
	 * 考试违纪查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> kswjTitle() throws Exception {
		String[] enList = new String[] { "pk", "rownum", "xh", "xm", "bjmc",
				"xn", "xq", "cfyymc", "cfsj", "cfwh" };
		String[] cnList = new String[] { "pk", "行号", "学号", "姓名", "班级名称", "学年",
				"学期", "处分原因", "处分时间", "处分文号" };
		return dao.arrayToList(enList, cnList);
	}
	
	public List<String[]> kswjQryRes(KswjModel model) throws Exception {
		StringBuffer whereSql = getWhereSql1(model);
		String sql = "select xh||xn||xq||sbsj pk,rownum,xh,xm,bjmc,xn,(select b.xqmc from xqdzb b where b.xqdm=view_kswjcf.xq) xq,cfyymc,cfsj,cfwh" +
				" from view_kswjcf where 1=1 ";
		String[] opList = new String[] { "pk", "rownum", "xh", "xm", "bjmc",
				"xn", "xq", "cfyymc", "cfsj", "cfwh" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public StringBuffer getWhereSql1(KswjModel model) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and xm like ?");
			values.add("%" + DealString.toGBK(model.getXm()) + "%");
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getNd())) {
			whereSql.append(" and nd = ?");
			values.add(model.getNd());
		}
		if (!StringUtils.isNull(model.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(model.getXq());
		}
		if (!StringUtils.isNull(model.getCfyy())) {
			whereSql.append(" and cfyy = ?");
			values.add(model.getCfyy());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(model.getNj());
		}
		return whereSql;
	}
	
	/**
	 * 考试违纪处分原因列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKscfyyList() throws Exception {
		return dao.getList("select cfyydm, cfyymc from ks_cfyydmb", 
				new String[]{}, new String[]{"cfyydm", "cfyymc"});
	}
	
	/**
	 * 考试违纪处分类别列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKscflbList() throws Exception {
		return dao.getList("select cflbdm, cflbmc from ks_cflbdmb", 
				new String[]{}, new String[]{"cflbdm", "cflbmc"});
	}
	
	public HashMap<String, String> getStuDetails(String xh) throws Exception {
		return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where" +
				" xh=?", new String[]{xh});
	}
	
	public boolean savekswjInfo(KswjModel model, HttpServletRequest request) throws Exception {
		return StandardOperation.insert("kswjcfb", new String[]{"XN","ND","XH","CFLB",
				"CFYY","CFSJ","CFWH","XQ","XYCLYJ","JTWJSY","ZACFQK","QTCFQK","XXCLYJ"},
				new String[] { model.getXn(),
				model.getNd(), model.getXh(), model.getCflb(), model.getCfyy(),
				model.getCfsj(), DealString.toGBK(model.getCfwh()), model.getXq(), 
				DealString.toGBK(model.getXyclyj()), DealString.toGBK(model.getJtwjsy()), 
				DealString.toGBK(model.getZacfqk()), DealString.toGBK(model.getQtcfqk()) ,DealString.toGBK(model.getXxclyj())}, request);
	}
	
	public String kswjDel(String[] keys, HttpServletRequest request) throws Exception {
		String sDel = "";
		for (int i = 0; i < keys.length; i++) {
			boolean bFlag = StandardOperation.delete("kswjcfb", "xh||xn||xq||sbsj", keys[i], request);
			if (!bFlag) {//记录删除失败的数据行
				sDel += (i+1);
				sDel += ",";
			}
		}
		return StringUtils.isNull(sDel) ? "" : sDel.substring(0, sDel.length() - 1);
	}
	
	public HashMap<String, String> kswjModi(String pkValue) throws Exception {
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,bjmc,zymc,xn,nd,cflb,cfyy,cfsj," +
						"cfwh,xq,xyclyj,jtwjsy,zacfqk,qtcfqk,xxclyj from view_kswjcf where xh||xn||xq||sbsj=?",
						new String[] { pkValue });
	}
	
	public boolean kswjModisave(String pkValue, KswjModel model, HttpServletRequest request) throws Exception {
		return StandardOperation.update("kswjcfb", new String[]{"XN","ND","XH","CFLB",
				"CFYY","CFSJ","CFWH","XQ","XYCLYJ","JTWJSY","ZACFQK","QTCFQK", "XXCLYJ"}, 
				new String[]{model.getXn(),
				model.getNd(), model.getXh(), model.getCflb(), model.getCfyy(),
				model.getCfsj(), DealString.toGBK(model.getCfwh()), model.getXq(), 
				DealString.toGBK(model.getXyclyj()), DealString.toGBK(model.getJtwjsy()), 
				DealString.toGBK(model.getZacfqk()), DealString.toGBK(model.getQtcfqk()),
				DealString.toGBK(model.getXxclyj())}, "xh||xn||xq||sbsj", pkValue, request);
	}
	
	public List<HashMap<String, String>> kswjgzjyTitle() throws Exception {
		String[] enList = new String[]{"pk", "rownum", "xh", "xm", "xn", "xq", 
				"bjmc","cfyymc", "cfwh", "cfsj", "zt"};
		String[] cnList = new String[]{"pk", "行号", "学号", "姓名", "学年", "学期",
				"班级", "处分原因", "处分文号", "处分时间", "教育状态"};
		return dao.arrayToList(enList, cnList);
	}
	
	public List<String[]> kswjgzjyResult(KswjModel model) throws Exception {
		StringBuffer whereSql = getWhereSql1(model);
		String sql = "select xh||xn||xq||sbsj pk,rownum,xh,xm,xn,(select b.xqmc from " +
				"xqdzb b where b.xqdm=view_kswjcf.xq) xq,bjmc,cfyymc,cfwh,cfsj,(case when(months_between(to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd'),to_date(cfsj,'yyyymmdd')))>=12 then 'pass' else 'nopass' end) zt " +
				"from view_kswjcf where 1=1 ";
		String[] opList = new String[]{"pk", "rownum", "xh", "xm", "xn", "xq", 
				"bjmc","cfyymc", "cfwh", "cfsj", "zt"};
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public HashMap<String, String> viewKswjgzjyxx(String pkValue) throws Exception {
		return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,(select b.xqmc " +
				"from xqdzb b where b.xqdm=view_kswjcf.xq) xq,cfwh,cfsj,cflbmc,cfyymc," +
				"xsbx1,xsbx2,xsbx3,xsbx4,xyclyj from view_kswjcf where xh||xn||xq||sbsj=?", new String[]{pkValue});
	}
	
	public boolean kswjGzjysaveres(String pkValue, KswjModel model, HttpServletRequest request) throws Exception {
		return StandardOperation.update("kswjcfb", new String[]{"xsbx1", "xsbx2", 
				"xsbx3", "xsbx4", "xyclyj" }, new String[] {
				DealString.toGBK(model.getXsbx1()),
				DealString.toGBK(model.getXsbx2()),
				DealString.toGBK(model.getXsbx3()),
				DealString.toGBK(model.getXsbx4()),
				DealString.toGBK(model.getXyclyj()) }, "xh||xn||xq||sbsj",
				pkValue, request);
	}
	
	public String wjshres(String[] keys, HttpServletRequest request, String ress) throws Exception {
		String res = "";
		ress = StringUtils.isNull(ress) ? "未审核" : (StringUtils.isEqual("tg", ress) ? "通过" : "不通过");
		for (String s :keys) {
			dao.runUpdate("update wjcfb set xxsh=? where xh||xn||nd||sbsj=?", new String[]{ress, s});
		}
		return res;
	}
	
	public String sjzy(String[] keys) throws Exception {
		String xxdm = StandardOperation.getXxdm();
		for (String s : keys) {
			String sql = "";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) || Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
				sql = "insert into wjcflsb (XN,ND,XH,CFLB,CFYY,CFSJ,CFWH,SSJG,BZ,XQ,CXWH,CXSJ,XYCLYJ,XXCLYJ"
						+ " ,CFNX,KCSJ,XSBX1,XSBX2,XSBX3,XSBX4,XYYJ,SBSJ,XXSH,LXDH,WJSJ,JTWJSY,ZACFQK,QTCFQK,"
						+ " CXJG,XYSH,XNDSH,XNDCLYJ) select XN,ND,XH,CFLB,CFYY,CFSJ,"
						+ " CFWH,SSJG,BZ,XQ,CXWH,CXSJ,XYCLYJ,XXCLYJ,CFNX,KCSJ,XSBX1,XSBX2,XSBX3,"
						+ " XSBX4,XYYJ,SBSJ,XXSH,LXDH,WJSJ,JTWJSY,ZACFQK,QTCFQK,CXJG,XYSH,XNDSH,XNDCLYJ "
						+ " from wjcfb where xh||xn||nd||sbsj=?";
			} else if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
				sql = "insert into wjcflsb (XN,ND,XH,CFLB,CFYY,CFSJ,CFWH,SSJG,BZ,XQ,CXWH,CXSJ,XYCLYJ,XXCLYJ"
					+ " ,CFNX,KCSJ,XSBX1,XSBX2,XSBX3,XSBX4,XYYJ,SBSJ,XXSH,LXDH,WJSJ,JTWJSY,ZACFQK,QTCFQK,"
					+ " CXJG,XYSH,XNDSH,XNDCLYJ) select XN,ND,XH,CFLB,CFYY,CFSJ,"
					+ " CFWH,SSJG,BZ,XQ,CXCLWH,CXCLSJ,XYCLYJ,XXCLYJ,CFNX,KCSJ,XSBX1,XSBX2,XSBX3,"
					+ " XSBX4,XYYJ,SBSJ,XXSH,LXDH,WJSJ,JTWJSY,ZACFQK,QTCFQK,CXJG,XYSH,XNDSH,XNDCLYJ "
					+ " from wjcfb where xh||cfwh||cfsj=?";

			}else {
				sql = "insert into wjcflsb (XN,ND,XH,CFLB,CFYY,CFSJ,CFWH,SSJG,BZ,XQ,CXWH,CXSJ,XYCLYJ,XXCLYJ"
						+ " ,CFNX,KCSJ,XSBX1,XSBX2,XSBX3,XSBX4,XYYJ,SBSJ,XXSH,LXDH,WJSJ,JTWJSY,ZACFQK,QTCFQK,"
						+ " CXJG,XYSH,XNDSH,XNDCLYJ,ycflb,sfjw) select XN,ND,XH,CFLB,CFYY,CFSJ,"
						+ " CFWH,SSJG,BZ,XQ,CXWH,CXSJ,XYCLYJ,XXCLYJ,CFNX,KCSJ,XSBX1,XSBX2,XSBX3,"
						+ " XSBX4,XYYJ,SBSJ,XXSH,LXDH,WJSJ,JTWJSY,ZACFQK,QTCFQK,CXJG,XYSH,XNDSH,XNDCLYJ,ycflb,sfjw "
						+ " from wjcfb where xh||cfwh||cfsj=?";

			}
			boolean bFlag = dao.runUpdate(sql, new String[] { DealString.toGBK(s) });
			if (bFlag) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)
						|| Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
					sql = "delete from wjcfb where xh||xn||nd||sbsj=?";
				} else {
					sql = "delete from wjcfb where xh||cfwh||cfsj=?";
				}
				dao.runUpdate(sql, new String[] { DealString.toGBK(s) });
			}
		}
		return "";
	}
	
	public HashMap<String, String> wtgview(String pkValue) throws Exception {
		return dao.getMapNotOut("select * from view_wjcf where xh||sbsj=?", new String[]{pkValue});
	}
	
	public static void main(String[] args) throws Exception {
		String[] keys = { "30422112522007-2008200820080505093921",
				"30308110922007-2008200820080505151901" };
		WjcfShgcDAO dao = new WjcfShgcDAO();
		String pk = dao.delxxshInfo(keys);
		System.out.println(pk);
	}
}
