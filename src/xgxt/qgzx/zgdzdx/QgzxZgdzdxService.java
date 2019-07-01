package xgxt.qgzx.zgdzdx;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.hngydx.service.HngydxXsqgzxService.saveFreeTime;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

import common.Globals;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description: 中国地质大学勤工助学Service
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: 李容
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2008-09-23
 * </p>
 */
public class QgzxZgdzdxService {
	QgzxZgdzdxDAO dao = new QgzxZgdzdxDAO();

	/**
	 * 查询勤工助学黑名单信息
	 * 
	 * @param model
	 * @return List
	 */
	public List getHmdInfo(QgzxZgdzdxForm model) {
		return dao.getHmdInfo(model);
	}

	/**
	 * 查询勤工助学黑名单表头信息
	 * 
	 * @return List
	 */
	public List getHmdTopTr() {
		String[] input = { "xh", "xm", "nj", "xymc", "zymc", "bjmc","jrsj" };
		String[] colCN = dao.getColumnNameCN(input, "view_qgzx_zgdzdx_hmdb");

		return dao.arrayToList(input, colCN);
	}

	/**
	 * 查询单个学生黑名单信息
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getOneHmd(String pkValue) {

		return dao.getOneHmd(pkValue);
	}

	/***************************************************************************
	 * 保存黑名单信息
	 * 
	 * @param model
	 * @return boolean
	 * @throws Exception
	 **************************************************************************/
	public boolean saveHmd(QgzxZgdzdxForm model, HttpServletRequest request)
			throws Exception {
		boolean flag = false;
		String tableName = "qgzx_zgdzdx_hmdb";
		String xh = model.getXh();
		String bz = DealString.toGBK(model.getBz());
		String[] columns = { "xh", "bz" };
		String[] values = { xh, bz };
		int num = dao.getCount(model);

		if (num > 0) {
			// update
			flag = StandardOperation.update(tableName, columns, values, "xh",
					xh, request);
		} else {
			// insert
			flag = StandardOperation
					.insert(tableName, columns, values, request);
		}
		return flag;
	}

	/**
	 * 删除黑名单信息
	 * 
	 * @param values
	 * @param request
	 * @return int
	 */
	public int deleteHmd(String[] values, HttpServletRequest request) {
		String tableName = "qgzx_zgdzdx_hmdb";
		for (int i = 0; i < values.length; i++) {
			try {
				StandardOperation.delete(
						"delete from qgzx_zgdzdx_hmdb where xh='" + values[i]
								+ "'", tableName, request);
			} catch (Exception e) {
				e.printStackTrace();
				return i;
			}
		}
		return 0;
	}

	/**
	 * 校外勤工助学岗位信息保存
	 * 
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws SQLException 
	 */
	public boolean saveGwxx(QgzxZgdzdxForm model, HttpServletRequest request) throws SQLException {
		
		DAO dao=DAO.getInstance();
		
		boolean flag = true;
		String tableName = "qgzx_xwgwxxb";

		String gwmc = model.getGwmc();
		String sqsj = model.getSqsj();
		String sqdwmc = model.getSqdwmc();
		String sqdwdz = model.getSqdwdz();
		String gzkssj = model.getGzkssj();
		String gzjssj = model.getGzjssj();
		String jcfs = model.getJcfs();
		String jcbz = model.getJcbz();
		String lxdh = model.getLxdh();
		String xyrs = model.getXyrs();
		String xyknsrs = model.getXyknsrs();
		String gzsj = model.getGzsj();
		String gznr = model.getGznr();
		String gzyq = model.getGzyq();
		String bz = model.getBz();
		String xn = Base.currXn;
		String nd = Base.currNd;
		String xq = Base.currXq;
		
//		String[] columns = { "gwmc", "sqdwmc", "sqdwdz", "gzkssj", "gzjssj",
//				"xyrs", "xyknsrs", "jcfs", "jcbz", "lxdh", "gzsj", "gznr",
//				"gzyq", "bz", "xn", "nd", "xq" };
//		String[] values = { gwmc, sqdwmc, sqdwdz, gzkssj, gzjssj, xyrs,
//				xyknsrs, jcfs, jcbz, lxdh, gzsj, gznr, gzyq, bz, xn, nd, xq };
//		if (sqsj == null || sqsj.equals("")) {
//			// insert
//			flag = StandardOperation.insert(tableName, columns, values, request);
//		} else {
//			// update
//			try {
//				flag = StandardOperation.update(tableName, columns, values, "gwmc||sqsj", gwmc + sqsj, request);
//			} catch (Exception e) {
//				// TODO 自动生成 catch 块
//				flag = false;
//				e.printStackTrace();
//			}
//		}
		String[]sqlArr=new String[2];
		sqlArr[0]=" delete from qgzx_xwgwxxb where gwmc||sqsj= '"+gwmc + sqsj+"'";
		StringBuilder sql=new StringBuilder();
		sql.append(" insert into qgzx_xwgwxxb(sqsj,gwmc, sqdwmc, sqdwdz, gzkssj, gzjssj,xyrs, xyknsrs, jcfs, jcbz, lxdh, gzsj, gznr,gzyq, bz, xn, nd, xq) ");
		sql.append(" values ('"+sqsj+"','"+gwmc+"','"+sqdwmc+"','"+sqdwdz+"','"+gzkssj+"','"+gzjssj+"','"+xyrs+"','"+xyknsrs+"','"+jcfs+"','"+jcbz+"','"+lxdh+"','"+gzsj+"','"+gznr+"','"+gzyq+"','"+bz+"','"+xn+"','"+nd+"','"+xq+"')  ");
		sqlArr[1]=sql.toString();
		int[]bool=dao.runBatch(sqlArr);
		if(bool[1]==0){
				flag=false;
		}
		return flag;
	}

	/**
	 * 查询单个校外勤工助学岗位信息
	 * 
	 * @param model
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getXwgwxx(QgzxZgdzdxForm model) {
		return dao.getXwgwxx(model);
	}

	/**
	 * 获取校外单位列表
	 * 
	 * @return List
	 */
	public List getXwsqdwList() {
		return dao.getXwsqdwList();
	}

	/**
	 * 校外岗位信息查询
	 * 
	 * @param model
	 * @return List
	 */
	public List searchXwgw(QgzxZgdzdxForm model) {
		return dao.searchXwgw(model);
	}

	/**
	 * 获取校外岗位信息表头
	 * 
	 * @return List
	 */
	public List<HashMap<String,String>> getXwgwToptr() {
		String[] input = { "主键", "xn", "nd", "xq", "gwmc", "sqdwmc", "xyrs","xyknsrs", "sqsj", "gzsj", "gznr" };
		String[] colCN = dao.getColumnNameCN(input, "qgzx_xwgwxxb");
		return dao.arrayToList(input, colCN);
	}

	/**
	 * 删除校外岗位信息
	 * 
	 * @param value
	 * @param request
	 * @return int
	 */
	public int deleteXwGwxx(String[] value, HttpServletRequest request) {
		int num = 0;
		for (int i = 0; i < value.length; i++) {
			try {
				StandardOperation.delete("qgzx_xwgwxxb", "gwmc||sqsj",DealString.toGBK(value[i]), request);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
				return i;
			}
		}
		return num;
	}

	/**
	 * 获取校外岗位信息列表
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getXwgwList() {
		return dao.getXwgwList();
	}
	
	/**
	 * 获取校外岗位信息列表
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getXwgwByOld() {
		return dao.getXwgwByOld();
	}

	/**
	 * 获取学生基本信息
	 * 
	 * @param xh
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return dao.getStuInfo(xh);
	}

	/**
	 * 判断学生是否被列人黑名单
	 * 
	 * @param xh
	 * @return boolean
	 */
	public boolean isHmdMember(String xh) {
		return dao.isHmdMember(xh);
	}

	/**
	 * 保存学生申请校外岗位信息
	 * 
	 * @param model
	 * @param request
	 * @return boolean
	 */
	public boolean saveGwsq(QgzxZgdzdxForm model, HttpServletRequest request)
			throws Exception {
		boolean flag = false;
		String tableName = "xsxwgwxxb";
		String xh = model.getXh();
		String gwmc = DealString.toGBK(model.getGwmc());// 包括了gwmc 和sqsj
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String kcjqgzxsj = DealString.toGBK(model.getKcjqgzxsj());
		String lxdh = model.getLxdh();
		String sqly = DealString.toGBK(model.getSqly());
		String bz = DealString.toGBK(model.getBz());
		HashMap<String, String> gwmap = dao.getGwmap(gwmc);

		String vGwmc = gwmap.get("gwmc");
		String vGwsqsj = gwmap.get("sqsj");

		String[] input = { "xh", "xn", "nd", "xq", "kcjqgzxsj", "lxdh", "sqly", "bz", "gwmc", "gwsqsj" };
		String[] value = { xh, xn, nd, xq, kcjqgzxsj, lxdh, sqly, bz, vGwmc, vGwsqsj };
		if (dao.xwgwsqExists(xh, gwmc)) {
			// update
			flag = StandardOperation.update(tableName, input, value, "xh||gwmc||gwsqsj", xh + gwmc, request);
		} else {
			// insert
			flag = StandardOperation.insert(tableName, input, value, request);
		}

		return flag;
	}
	
	/**
	 * 保存岗位分配信息
	 * 
	 * @param model
	 * @param request
	 * @return boolean
	 */
	public boolean saveGwfp(QgzxZgdzdxForm model, HttpServletRequest request)
			throws Exception {
		HashMap<String, String> gwMap = dao.getGwInfo(model.getGwdm());
		HashMap<String, String> time = dao.getGwsqsjInfo();

		boolean flag = false;
		String pk = model.getPk();
		String[] pkValue = pk.split("!!");
		String tableName = "xsgwxxb";
		int syrs = dao.getGwSyrs(model);

		if (syrs < pkValue.length - 1) {
			request.setAttribute("msg", "此岗还剩" + syrs + "个名额，请按此人数进行提交!");
			flag = false;
		} else {
			for (int i = 0; i < pkValue.length; i++) {
				if (pkValue[i] != null && !"".equalsIgnoreCase(pkValue[i])) {
					if (dao.checkExists(tableName, "xh||gwdm||'-'||gwsbsj", pkValue[i] + model.getGwdm())) {// 修改
						flag = StandardOperation.update(tableName,
								new String[] { "xxyj" },
								new String[] { "通过" },
								"xh||gwdm||'-'||gwsbsj", pkValue[i] + model.getGwdm(), request);

					} else {// 插入

						flag = StandardOperation.insert(tableName,
								new String[] { "xh", "gwdm", "gwsbsj", "xn", "nd", "xq", "xyyj", "xxyj" },
								new String[] { pkValue[i],
										gwMap.get("gwmc"),
										gwMap.get("gwsbsj"),
										time.get("xn"), time.get("nd"),
										time.get("xq"), "通过", "通过" },
								request);
						/**中国地质大学勤工助学直接分配岗位成功后其他岗位申请记录全部改为审核不通过*/
						if(flag){
							StandardOperation.updateXsgwxx(new String[] { pkValue[i], time.get("xn"), time.get("nd"), time.get("xq")});
						}
					}
				}
			}

		}

		return flag;
	}

	/**
	 * 保存学生申请勤工助学之星信息
	 * 
	 * @param model
	 * @param request
	 * @return boolean
	 */
	public boolean saveQgzxzx(QgzxZgdzdxForm model, HttpServletRequest request)
			throws Exception {
		boolean flag = false;
		String tableName = "qgzxzxsqb";
		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String zwjd = model.getZwjd();

		String[] input = { "xh", "xn", "nd", "xq", "zwjd" };
		String[] value = { xh, xn, nd, xq, zwjd };
		if (dao.checkExists("xsgwxxb", "xh||xn||nd||xq||xxyj", xh + xn + nd + xq + "通过")) {// 判断是否是校内勤工助学学生
			if (dao.checkExists(tableName, "xh||xn||nd||xq", xh + xn + nd + xq)) {
				// update
				flag = StandardOperation.update(tableName, input, value, "xh||xn||nd||xq", xh + xn + nd + xq, request);
			} else {
				// insert
				flag = StandardOperation.insert(tableName, input, value, request);
			}
		} else {
			request.setAttribute("noPass", "yes");
		}
		return flag;
	}

	/**
	 * 获取学生岗位申请结果查询表头
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getXwgwsqToptr() {
		String[] input = { "主键", "xn", "nd", "xq", "xh", "xm", "xymc", "bjmc", "gwmc", "xxsh", "zpsj", "zpdz" };
		String[] colCN = dao.getColumnNameCN(input, "view_xsxwgwxxb");
		return dao.arrayToList(input, colCN);
	}

	/**
	 * 获取学生勤工助学之星申请结果查询表头
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getQgzxzxToptr() {
		String[] input = { "主键", "xn", "nd", "xq", "xh", "xm", "bjmc", "sqsj", "yrdwsh", "xysh", "xxsh" };
		String[] colCN = dao.getColumnNameCN(input, "view_qgzxzx");
		return dao.arrayToList(input, colCN);
	}

	public List<HashMap<String, String>> getQgzxzxShTopTr() {
		String[] input = { "color", "主键", "xn", "nd", "xq", "xh", "xm", "bjmc", "sqsj", "yrdwsh", "xysh", "xxsh" };
		String[] colCN = dao.getColumnNameCN(input, "view_qgzxzx");
		return dao.arrayToList(input, colCN);
	}

	/**
	 * 学生岗位申请结果查询
	 * 
	 * @param model
	 * @return List
	 */
	public List<String[]> getXwGwsq(QgzxZgdzdxForm model) {
		return dao.getXwGwsq(model);
	}

	public List<String[]> qgzxzxSearch(QgzxZgdzdxForm model) {
		return dao.qgzxzxSearch(model);
	}

	/**
	 * 根据主键获取学生申请岗位的信息
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getGwsqInfo(String pkValue) {
		return dao.getGwsqInfo(pkValue);
	}

	/**
	 * 删除学生申请岗位信息
	 * 
	 * @param value
	 * @param request
	 * @return int
	 */
	public int deleteXwGwsq(String[] value, HttpServletRequest request) {
		int result = 0;
		String tableName = "xsxwgwxxb";
		String pk = "xn||nd||xq||xh||gwmc||gwsqsj";
		for (int i = 0; i < value.length; i++) {
			try {
				StandardOperation.delete(tableName, pk, DealString
						.toGBK(value[i]), request);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
				return i;
			}
		}
		return result;
	}

	/**
	 * 查询学校审核结果
	 * 
	 * @param model
	 * @return List
	 */
	public List<String[]> getXwGwsqSh(QgzxZgdzdxForm model) {
		return dao.getXwGwsqSh(model);
	}

	/**
	 * 获取学生岗位申请审核查询表头
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getXwgwsqShToptr() {
		String[] input = { "color", "主键", "xn", "nd", "xq", "xh", "xm", "xymc",
				"bjmc", "gwmc", "xxsh" };
		String[] colCN = dao.getColumnNameCN(input, "view_xsxwgwxxb");
		return dao.arrayToList(input, colCN);
	}

	/**
	 * 岗位申请审核信息查询
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getGwsqshInfo(String pkValue) {
		return dao.getGwsqshInfo(pkValue);
	}

	/**
	 * 获取审核列表
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getChkList() {
		return dao.getChkList(3);
	}

	/**
	 * 学生申请校外岗位审核保存
	 */
	public boolean saveXwgwsqsh(QgzxZgdzdxForm model, HttpServletRequest request) {
		boolean flag = false;
		String xxsh = DealString.toGBK(model.getXxsh());
		String zpsj = DealString.toGBK(model.getZpsj());
		String zpdz = DealString.toGBK(model.getZpdz());

		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String gwmc = DealString.toGBK(model.getGwmc());
		String sqsj = model.getGwsqsj();
		try {
			flag = StandardOperation.update("xsxwgwxxb", new String[] { "xxsh",
					"zpsj", "zpdz" }, new String[] { xxsh, zpsj, zpdz },
					"xh||xn||nd||xq||gwmc||gwsqsj", xh + xn + nd + xq + gwmc
							+ sqsj, request);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 查询校外岗位详细信息
	 * 
	 * @return pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getGwxxDetail(String pkValue) {
		return dao.getGwxxDetail(pkValue);
	}

	/**
	 * 查询学院人数分配人数
	 * 
	 * @param model
	 * @return List
	 */
	public List<String[]> getQgzxxy(QgzxZgdzdxForm model) {
		return dao.getQgzxxy(model);
	}

	/**
	 * 获取学院人数分配查询的表头
	 */
	public List<HashMap<String, String>> getTopTrOfDisperson() {
		String xxdm = StandardOperation.getXxdm();
		String[] input = { "部门代码", "学年", "年度", "学期", "部门名称", "分配人数" };
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm) || Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)){
			//广州大学
			input = new String[]{ "部门代码", "学年", "年度", "学期", "岗位名称","部门名称", "分配人数" };
		}
		return dao.arrayToList(input, input);
	}

	/**
	 * 保存学院人数分配的结果
	 * 
	 * @param model
	 * @param request
	 * @return boolean
	 */
	public boolean saveDistribute(QgzxZgdzdxForm model,
			HttpServletRequest request) {
		boolean flag = false;
		String xxdm = StandardOperation.getXxdm();
		String xn = model.getXn().trim();
		String nd = model.getNd().trim();
		String xq = model.getXq().trim();
		String fprs = model.getFprs();
		String xydm = model.getXydm();
		String gwdm = model.getGwdm();
		String gwsbsj = model.getGwsbsj();
		String tableName = "qgzx_xyrsb";
		String[] input = new String[] { "xn", "nd", "xq", "xydm", "fprs" };
		String[] value = new String[] { xn, nd, xq, xydm, fprs };
		String pk = "xn||nd||xq||xydm";
		String pkValue = xn+nd+xq+xydm;
		
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm) || Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)){
			//广州大学
			pk = "xn||nd||xq||xydm||gwdm||gwsbsj";
			pkValue = xn+nd+xq+xydm+gwdm+gwsbsj;
			input = new String[]{ "xn", "nd", "xq", "xydm", "fprs", "gwdm", "gwsbsj" };
			value = new String[]{ xn, nd, xq, xydm, fprs, gwdm, gwsbsj };
		}
		if (dao.checkExists(tableName, pk, pkValue)) {
			try {
				flag = StandardOperation.update(tableName, new String[] { "fprs" }, new String[] { fprs }, pk, pkValue, request);
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		} else {
			flag = StandardOperation.insert(tableName, input, value, request);
		}
		return flag;
	}

	/**
	 * 保存勤工助学申请
	 * 
	 * @param model
	 * @param reqeust
	 * @return boolean
	 */
	public boolean saveQgzxsq(QgzxZgdzdxForm model, HttpServletRequest request) {
		boolean flag = false;		
		String xh = model.getXh();
		String lxdh = model.getLxdh();
		String kcjqgzxsj = DealString.toGBK(model.getKcjqgzxsj());
		String sqly = DealString.toGBK(model.getSqly());
		String bz = DealString.toGBK(model.getBz());
		String yhtc = DealString.toGBK(model.getYhtc());
		String zzmmdm = DealString.toGBK(model.getZzmmdm());
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String kcsgz = model.getKcsgz();
		String jjqk = model.getJjqk();
		String gwxzdm = model.getGwxzdm();
		gwxzdm = StringUtils.isNull(gwxzdm) ? "##" : gwxzdm;
		
		String tableName = "qgzxsqb";
		if (dao.isExistsQgzxsq(model)) {//数据已经存在，进行修改操作
			try {
				flag = StandardOperation.update(tableName, new String[] {
						"lxdh", "kcjqgzxsj", "sqly", "bz" ,"zzmmdm","yhtc","kcsgz","jjqk","gwxzdm"}, new String[] {
						lxdh, kcjqgzxsj, sqly, bz ,zzmmdm, yhtc,kcsgz,jjqk,gwxzdm}, "xh||xn||nd||xq", xh + xn
						+ nd + xq, request);
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		} else {//数据不存在，进行插入操作
			flag = StandardOperation.insert(tableName, new String[] { "xh",
					"xn", "nd", "xq", "lxdh", "kcjqgzxsj", "sqly", "bz","zzmmdm", "yhtc", "kcsgz","jjqk", "gwxzdm" },
					new String[] { xh, xn, nd, xq, lxdh, kcjqgzxsj, sqly, bz ,zzmmdm, yhtc, kcsgz, jjqk, gwxzdm},
					request);
		}
		if(flag){//保存学生可参加勤工助学时间信息
			saveFreeTime sft = new saveFreeTime(request,xh,4,5);
			new Thread(sft).start();
		}
		return flag;
	}

	/**
	 * 保存勤工助学申请(广州大学)
	 * 
	 * @author luojw
	 * @param model
	 * @param reqeust
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean saveQgzxsqGzdx(QgzxZgdzdxForm model,
			HttpServletRequest request) throws SQLException {
		
		DAO dao = DAO.getInstance();
		
		boolean flag = false;
		
		String[] checkVal = model.getCheckVal();
		String xh = model.getXh();
		String lxdh = model.getLxdh();
		String kcjqgzxsj = DealString.toGBK(model.getKcjqgzxsj());
		String sqly = DealString.toGBK(model.getSqly());
		String bz = DealString.toGBK(model.getBz());
		String yhtc = DealString.toGBK(model.getYhtc());
		String zzmmdm = DealString.toGBK(model.getZzmmdm());
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String kcsgz = model.getKcsgz();
		String jjqk = model.getJjqk();
		String gwxzdm = model.getGwxzdm();

		String tableName = "qgzxsqb";

		StringBuffer sql = new StringBuffer();

		if (checkVal != null && checkVal.length > 0) {
			
			String[] exec = new String[checkVal.length + 1];
			int n = 0;
			
			sql.append(" delete from ");
			sql.append(tableName);
			sql.append(" where xn = '" + xn + "' ");
			sql.append(" and xq = '" + xq + "' ");
			sql.append(" and nd = '" + nd + "' ");
			sql.append(" and xh = '" + xh + "' ");
			exec[n] = sql.toString();
			n++;

			for (int i = 0; i < checkVal.length; i++) {
				
				String pk = "xn||xq||nd||xh||gwxzdm";
				String pkValue = xn + xq + nd + xh + checkVal[i];
				String shSql = "select xysh,xxsh from " + tableName + " where " + pk
						+ " = ?";
				String[] sh = dao.getOneRs(shSql, new String[] { pkValue },
						new String[] { "xysh", "xxsh" });
				
				String xysh = "未审核"; 
				String xxsh = "未审核"; 
				
				if (sh != null && sh.length > 1) {
					xysh = sh[0];
					xxsh = sh[1];
				}
				
				sql = new StringBuffer();
				sql.append(" insert into ");
				sql.append(tableName);
				sql.append("(xh, xn, nd, xq, lxdh, kcjqgzxsj, sqly,");
				sql.append(" bz,zzmmdm, yhtc, kcsgz,jjqk,xysh,xxsh, gwxzdm)");
				sql.append(" values(");
				sql.append("'" + xh + "'");
				sql.append(",'" + xn + "'");
				sql.append(",'" + nd + "'");
				sql.append(",'" + xq + "'");
				sql.append(",'" + lxdh + "'");
				sql.append(",'" + kcjqgzxsj + "'");
				sql.append(",'" + sqly + "'");
				sql.append(",'" + bz + "'");
				sql.append(",'" + zzmmdm + "'");
				sql.append(",'" + yhtc + "'");
				sql.append(",'" + kcsgz + "'");
				sql.append(",'" + jjqk + "'");
				sql.append(",'" + xysh + "'");
				sql.append(",'" + xxsh + "'");
				sql.append(",'" + checkVal[i] + "'");
				sql.append(")");
				exec[n] = sql.toString();
				n++;
			}

			int[] res = dao.runBatch(exec);

			for (int i = 0; i < res.length; i++) {
				flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
				if (!flag)
					break;
			}
		}

		if (flag) {// 保存学生可参加勤工助学时间信息
			saveFreeTime sft = new saveFreeTime(request, xh, 4, 5);
//			new Thread(sft).start();
			sft.run();
		}
		
		return flag;
	}
	
	/**
	 * 判断上一学年主课的挂课
	 * 数是否大于两门
	 * 2010.9.14 qlj
	 */
	public boolean checkGks(String xh){
		List<HashMap<String,String>>list=dao.checkGks(xh);
		if(list.size()>0){
			HashMap<String,String>hashMap=list.get(0);
			int sxnkks=Integer.parseInt(hashMap.get("sxnkks"));
			if(sxnkks>=2){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
	
	/**
	 * 判断学生是否有
	 * 违纪情况
	 * 2010.9.14 qlj
	 */
	public boolean checkWjcf(String xh){
		List<HashMap<String,String>>list=dao.checkWjqk(xh);
		if(list.size()>0){
			HashMap<String,String>hashMap=list.get(0);
			int wjcfcs=Integer.parseInt(hashMap.get("wjcfcs"));
			if(wjcfcs>0){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
	
	/**
	 * 广州大学
	 * 确认是否有资格
	 * 申请校内勤工助学
	 * 2010.9.14 qlj
	 */
	public boolean checkXnqgzxsq(String xh,String[] gwxzdm){
		QgzxZgdzdxService service=new QgzxZgdzdxService();
		//校内
		boolean blog=true;
		for(int i=0;i<gwxzdm.length;i++){
			if("001".equalsIgnoreCase(gwxzdm[i])){
				if(!service.checkGks(xh) || !service.checkWjcf(xh)){
					blog =false;
				}
			}
		}
		return blog;
	}
	
	/**
	 * 广州大学
	 * 审核时增加辅导员姓名
	 */
	public String getFdyXm(String zgh){
		List<HashMap<String,String>>list=dao.getFdyXm(zgh);
		String xm="";
		if(list.size()>0){
			HashMap<String,String>hashMap=list.get(0);
			xm=hashMap.get("xm");
		}else{
			xm="无姓名";
		}
		return xm;
	}
	
	/**
	 * 查询勤工助学申请信息
	 * 
	 * @param model
	 * @return List
	 */
	public List<String[]> selectQgzxsqshInfo(QgzxZgdzdxForm model) {
		return dao.selectQgzxsqshInfo(model);
	}
	
	/**
	 * 查询勤工助学申请信息
	 * 
	 * @param model
	 * @return List
	 */
	public List<String[]> queryQgzxsqb(QgzxZgdzdxForm model) {
		return dao.selectQgzxsqb(model);
	}

	/**
	 * 获取勤工助学申请信息表头
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getQgzxsqTopTr() {
		String xxdm = StandardOperation.getXxdm();
		String[] input = { "color", "主键", "xn", "nd", "xqmc", "xh", "xm", "xymc",
				"bjmc", "lxdh", "xysh", "xxsh" };
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//广州大学
			input = new String[]{ "color", "主键", "xn", "nd", "xqmc", "xh", "xm", "xymc",
					"bjmc", "gwxzmc", "lxdh", "xysh", "xxsh" };
		}
		String[] colCN = dao.getColumnNameCN(input, "view_qgzxsqb");
		return dao.arrayToList(input, colCN);
	}

	/**
	 * 获取勤工助学申请详细信息
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getQgzxsqInfo(QgzxZgdzdxForm model) {
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getQgzxsqInfo(model); 
		return map;
	}

	/**
	 * 获取勤工助学申请详细信息
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 * @author luo
	 */
	public HashMap<String, String> getQgzxsqZjfp(QgzxZgdzdxForm model) {
		return dao.getQgzxsqZjfp(model);
	}

	/**
	 * 保存学院审核勤工助学申请信息
	 * 
	 * @param model
	 * @param request
	 * @return boolean
	 */
	public boolean saveQgzxsqsh(QgzxZgdzdxForm model, HttpServletRequest request) {
		boolean flag = false;
		String tableName = "qgzxsqb";
		String yesNo = DealString.toGBK(model.getYesNo());
		String[] columns = { "xysh" };
		String[] values = { yesNo };
		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();

		String userType = model.getUserType();

		if (userType != null
				&& ("xx".equalsIgnoreCase(userType) || "admin"
						.equalsIgnoreCase(userType))) {
			columns = new String[] { "xxsh" };
		}

		try {
			if (yesNo != null && yesNo.equalsIgnoreCase("通过")) {
				if (this.isHmdMember(xh)) {
					request.setAttribute("hmdMember", "true");
					return false;
				}
				if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
					if (dao.checkPostCount(model)) {
						request.setAttribute("mes", "人数已经超过分配人数，操作失败！");
						return false;
					}
				}
			}
			flag = StandardOperation.update(tableName, columns, values,
					"xh||xn||nd||xq", xh + xn + nd + xq, request);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 保存勤工助学审核信息 
	 * @param model
	 * @param request
	 * @return boolean
	 */
	public boolean saveQgzxsqshByGzdx(QgzxZgdzdxForm model, HttpServletRequest request) {
		boolean flag = false;
		String tableName = "qgzxsqb";
		String yesNo = DealString.toGBK(model.getYesNo());		
		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String fdyname=model.getFdyname();
		String[] xmdm = model.getXmdm().split("!!");
		String gwshColumn = "xxyj";
		String gwdm = model.getGwdm();
		String gwdmgwsbsj = model.getXmdm();
		String isFdy=model.getIsFdy();
		
		String[] columns = {"zdgwdm", "zdgwdmgwsbsj", "xysh"};
		String[] values = { gwdm, gwdmgwsbsj, yesNo };
		
		if("true".equalsIgnoreCase(isFdy)){
			 columns =new String[] {"zdgwdm", "zdgwdmgwsbsj", "xysh","fdyname"};
			 values =new String[]  { gwdm, gwdmgwsbsj, yesNo,fdyname};
			
		}
		
		String userType = model.getUserType();

		if (userType != null && ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType))) {
			columns = new String[] {"zdgwdm", "zdgwdmgwsbsj", "xxsh" };
		}

		try {
			if (yesNo != null && yesNo.equalsIgnoreCase("通过")) {	
				for(int  i=0; i<xmdm.length; i++){
					String tmp = xmdm[i];
					if(!StringUtils.isNull(tmp)){
						model.setGwdm(tmp.split("-")[0]);
						model.setGwsbsj(tmp.split("-")[1]);
//						if(userType != null && "xy".equalsIgnoreCase(userType)){
//							//学院用户
//							if (dao.checkPostCountByGzdx(model)) {
//								request.setAttribute("mes", "'" + model.getGwdm() + "'人数已经超过分配人数，操作失败！");
//								return false;
//							}
//							gwshColumn = "xyyj";
//						}
//						//将学生岗位信息插入或更新到岗位信息表中
//						String pk = "xh||gwdm||gwsbsj";
//						String pkValue = xh+model.getGwdm()+model.getGwsbsj();
//						if(dao.checkExists("xsgwxxb", pk,pkValue)){
//							//记录已经存在，进行修改操作。
//							StandardOperation.update("xsgwxxb", new String[]{gwshColumn,"sfyx"}, new String[]{"通过","1"}, pk, pkValue, request);//sfyx='1' 有效
//						}else{
//							//记录不存在，进行插入操作。
//							StandardOperation.insert("xsgwxxb",new String[]{"xh","xn","nd","xq","gwdm","gwsbsj",gwshColumn,"sfyx"}, new String[]{xh,xn,nd,xq,model.getGwdm(),model.getGwsbsj(),"通过","1"}, request);////sfyx='1' 有效
//						}
						
						// ---------2010/5/17 edit by luojw -----------
						String pk = "xh||gwdm||gwsbsj";
						String pkValue = xh + model.getGwdm()
								+ model.getGwsbsj();

						HashMap<String, String> map = dao.getGwsqXx(pkValue);
						
						String xyyj = Base.isNull(map.get("xyyj")) ? "未审核"
								: map.get("xyyj");
						String xxyj = "通过";
						
						flag = StandardOperation.delete("xsgwxxb", pk, pkValue,
								request);

						if (flag) {

							if (userType != null
									&& "xy".equalsIgnoreCase(userType)) {
								// 学院用户
								if (dao.checkPostCountByGzdx(model)) {
									request.setAttribute("mes", "'"
											+ model.getGwdm()
											+ "'人数已经超过分配人数，操作失败！");
									return false;
								}
								gwshColumn = "xyyj";
								xyyj = "通过";
								// 记录不存在，进行插入操作。
								StandardOperation.insert("xsgwxxb", new String[] {
										"xh", "xn", "nd", "xq", "gwdm", "gwsbsj",
										"xyyj", "sfyx" }, new String[] { xh,
										xn, nd, xq, model.getGwdm(),
										model.getGwsbsj(), xyyj,"1" }, request);// sfyx='1'
																					// 有效
							}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
							
							// 记录不存在，进行插入操作。
							StandardOperation.insert("xsgwxxb", new String[] {
									"xh", "xn", "nd", "xq", "gwdm", "gwsbsj",
									"xxyj", "sfyx" }, new String[] { xh,
									xn, nd, xq, model.getGwdm(),
									model.getGwsbsj(),xxyj, "1" }, request);// sfyx='1'
																				// 有效
							}
						}

						// ---------end -----------
					}
				}					
			}
			//flag = StandardOperation.update(tableName, columns, values, "xh||xn||nd||xq", xh + xn + nd + xq, request);
			String pk = "xh||xn||nd||xq||gwxzdm";
			String pkValue = xh + xn + nd + xq + model.getGwxzdm();
			flag = StandardOperation.update(tableName, columns, values, pk,
					pkValue, request);
			DAO dao=DAO.getInstance();
			StringBuilder sql = new StringBuilder();
			if("xy".equalsIgnoreCase(userType) 
					&& !"通过".equalsIgnoreCase(yesNo)){
				sql = new StringBuilder();
				sql.append("update xsgwxxb a set xyyj='"+yesNo+"' where exists ");
				sql.append(" (select * from view_qgzxsqb b where  ");
				sql.append(pk + "='" + pkValue + "'");
				sql.append(" and a.gwdm||'-'||a.gwsbsj=b.zdgwdmgwsbsj and a.xh=b.xh) ");
				dao.runUpdate(sql.toString(), new String[]{});
			}else if(("xx".equalsIgnoreCase(userType)|| "admin".equalsIgnoreCase(userType))
					&& !"通过".equalsIgnoreCase(yesNo)){
				sql = new StringBuilder();
				sql.append("update xsgwxxb a set xxyj='"+yesNo+"' where exists ");
				sql.append(" (select * from view_qgzxsqb b where  ");
				sql.append(pk + "='" + pkValue + "'");
				sql.append(" and a.gwdm||'-'||a.gwsbsj=b.zdgwdmgwsbsj and a.xh=b.xh) ");
				dao.runUpdate(sql.toString(), new String[]{});
			}	
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 判断是否在申请时间内
	 * 
	 * @return boolean
	 */
	public boolean checkTime() throws SQLException {
		return dao.checkTime();
	}

	/**
	 * 判断学生是否通过学院推荐
	 * 
	 * @param xh
	 * @return boolean
	 */
	public boolean checkStuPass(String xh) {
		return dao.checkStuPass(xh);
	}

	/**
	 * 获取岗位信息列表
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getGwList() {
		return dao.getGwList();
	}
	
	/**
	 * 获取岗位信息列表
	 * @param String userName 
	 * @return List
	 */
	public List<HashMap<String, String>> getGwList(String userName) {
		return dao.getGwList(userName);
	}
	
	/**
	 * 获取岗位信息列表
	 * 
	 * @param String
	 *            userName
	 * @return List
	 */
	public List<HashMap<String, String>> getRsfpgwList(String userName,
			String gwxzmc, HttpServletRequest request) {
		String xxdm = StandardOperation.getXxdm();
		List<HashMap<String, String>> list = dao.getRsfpgwList(userName,
				gwxzmc, request);
		if (Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)) {
			list = dao.getRsfpgwListOfNbtyxy(userName);
		}
		return list;
	}

	/**
	 * 获取已安排人数列表
	 * 
	 * @return List
	 * @author luo
	 */
	public List<HashMap<String, String>> getApList() {
		return dao.getGwApList();
	}

	/**
	 * 获取审批使用人数列表
	 * 
	 * @return List
	 * @author luo
	 */
	public List<HashMap<String, String>> getSqList() {
		return dao.getGwSqList();
	}
	
	/**
	 * 查询勤工助学之星申请详细信息
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getQgzxzxInfo(String pkValue) {
		return dao.getQgzxzxInfo(pkValue);
	}

	/**
	 * 删除勤工助学之星信息
	 * 
	 * @param String[]
	 *            values
	 * @param request
	 * @return int
	 */
	public int deleteQgzxzx(String[] values, HttpServletRequest request) {
		String tableName = "qgzxzxsqb";
		for (int i = 0; i < values.length; i++) {
			try {
				StandardOperation.delete(
						"delete from qgzxzxsqb where xh||xn||nd||xq='"
								+ values[i] + "'", tableName, request);
			} catch (Exception e) {
				e.printStackTrace();
				return i;
			}
		}
		return 0;
	}

	/**
	 * 勤工助学之星审核查询
	 * 
	 * @param model
	 * @param userType
	 * @param userName
	 * @return List
	 */
	public List<String[]> getQgzxzxSh(QgzxZgdzdxForm model, String userType,
			String userName) {
		return dao.getQgzxzxSh(model, userType, userName);
	}
	
	/**
	 * 判断是否是用人单位用户
	 * @param String userName
	 * @return boolean
	 * */
	public boolean isYrdwUser(String userName){
		return dao.checkExists("yrdwdmb", "dlm", userName);
	}
	
	/**
	 * 查询勤工助学之星审核详细信息
	 * 
	 * @param pkValue
	 * @param userType
	 * @param userName
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getQgzxzxShInfo(String pkValue,
			String userType, String userName) {
		return dao.getQgzxzxShInfo(pkValue, userType, userName);
	}

	/**
	 * 勤工助学之星审核保存
	 * 
	 * @param model
	 * @param userType
	 * @param userName
	 * @param request
	 * @return boolean
	 */
	public boolean saveQgzxzxsh(QgzxZgdzdxForm model, String userType,
			String userName, HttpServletRequest request) throws Exception {
		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String szbmyj = DealString.toGBK(model.getSzbmyj());
		String xyyj = DealString.toGBK(model.getXyyj());
		String xxyj = DealString.toGBK(model.getXxyj());
		String yesNo = DealString.toGBK(model.getYesNo());
		String[] values = { szbmyj, xyyj, xxyj, yesNo };
		String[] columns = null;

		if (userType != null && userType.equalsIgnoreCase("xy")) {
			columns = new String[] { "xyyj", "xysh" };
			values = new String[] { xyyj, yesNo };
		} else {
			columns = new String[] { "xxyj", "xxsh" };
			values = new String[] { xxyj, yesNo };
		}
		if (dao.checkExists("yrdwdmb", "dlm", userName)) {
			columns = new String[] { "szbmyj", "yrdwsh" };
			values = new String[] { szbmyj, yesNo };
		}
		return StandardOperation.update("qgzxzxsqb", columns, values,
				"xh||xn||nd||xq", xh + xn + nd + xq, request);
	}

	/**
	 * 勤工助学之星批量审核
	 * 
	 * @param String[]
	 *            values
	 * @param userType
	 * @param userName
	 * @param yesNo
	 * @param request
	 * @return boolean
	 */
	public boolean batchQgzxzxsh(String[] values, String userType,
			String userName, String yesNo, HttpServletRequest request)
			throws Exception {
		boolean flag = false;
		String[] columns = null;
		if (userType != null && userType.equalsIgnoreCase("xy")) {
			columns = new String[] { "xysh" };
		} else {
			columns = new String[] { "xxsh" };
		}
		if (dao.checkExists("yrdwdmb", "dlm", userName)) {
			columns = new String[] { "yrdwsh" };
		}
		for (int i = 0; i < values.length; i++) {
			flag = StandardOperation.update("qgzxzxsqb", columns,
					new String[] { yesNo }, "xh||xn||nd||xq", values[i],
					request);
		}
		return flag;
	}

	public boolean saveWorkPayAudit(HttpServletRequest request) {
		QgzxDao qgzxDao = new QgzxDao();
		boolean flag = false;
		String mes = "";
		String count = request.getParameter("count");
		// String nd = request.getParameter("nd");
		// String yf = request.getParameter("yf");
		String gwdm = DealString.toGBK(request.getParameter("gwdm"));
		String gwsbsj = DealString.toGBK(request.getParameter("gwsbsj"));
		count = count == null || "".equalsIgnoreCase(count) ? "0" : count;
		String myzgbc = qgzxDao.getSqsjInfo().get("myzgbc");

		// yf = yf == null || "".equalsIgnoreCase(yf) ? "0" : yf;
		// yf = String.valueOf(Integer.parseInt(yf));
		// 判断是否有报酬高于设定值
		if (myzgbc != null && !myzgbc.equalsIgnoreCase("")) {
			for (int i = 0; i < Integer.parseInt(count); i++) {
				String cjje = request.getParameter("cjje" + i);
				cjje = cjje == null || "".equalsIgnoreCase(cjje) ? "0" : cjje;
				if (Integer.parseInt(cjje) > Integer.parseInt(myzgbc)) {
					mes = request.getParameter("xh" + i)
							+ "酬金金额高于设定的每月最高报酬额，请确认！";
					request.setAttribute("mes", mes);
					return flag;
				}
			}
		}
		// 报酬审核信息
		for (int i = 0; i < Integer.parseInt(count); i++) {
			String xh = request.getParameter("xh" + i);
			String cjje = request.getParameter("cjje" + i);
			String xxsh = request.getParameter("xxsh" + i);
			String nd = request.getParameter("nd" + i);
			String yf = request.getParameter("yf" + i);
			xxsh = xxsh != null && xxsh.equalsIgnoreCase("1") ? "通过" : "不通过";
			try {
				flag = StandardOperation.update("xscjffb", new String[] {
						"cjje", "xxsh" }, new String[] { cjje, xxsh },
						"xh||nd||yf||gwdm||sqsj", xh + nd + yf + gwdm + gwsbsj,
						request);
			} catch (Exception e) {
				mes += xh + "审核失败！\n";
				e.printStackTrace();
			}
		}
		request.setAttribute("mes", mes);
		return flag;
	}

	/**
	 * 打印酬金报表
	 * 
	 * @param WritableWorkbook
	 *            wwb
	 * @param String
	 *            nd
	 * @param yf
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void printWorkPay(WritableWorkbook wwb, String nd, String yf) {
		nd = nd == null || "".equalsIgnoreCase(nd) ? Base.currNd : nd;
		yf = yf == null || "".equalsIgnoreCase(yf) ? dao.currYf() : yf;
		HashMap<String, String> map = dao.getWorkPayMap(nd, yf);
		String count = map.get("count");
		String zje = map.get("zje");
		String ygzzxs = map.get("gzxs");
		zje = zje == null ? "" : zje;
		ygzzxs = ygzzxs == null ? "" : ygzzxs;
		List cjList = dao.getCjffInfo(nd, yf);

		WritableSheet ws = wwb.getSheet(0);
		WritableCellFormat wcfTytle = new WritableCellFormat();

		try {
			// 表头
			wcfTytle.setAlignment(Alignment.CENTRE);
			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(20);
			wcfTytle.setFont(wfTytle);
			ws.addCell(new Label(0, 0, String.valueOf("(" + nd + ")年(" + yf
					+ ") 月勤工助学劳务报酬发放表"), wcfTytle));

			// 附标题
			wcfTytle = new WritableCellFormat();
			wcfTytle.setAlignment(Alignment.LEFT);
			wfTytle = new WritableFont(WritableFont.COURIER);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);
			ws.addCell(new Label(0, 2, String.valueOf("总人数:" + count + "      总金额: " + zje + "     "
							+ "负责老师签名盖章:                负责老师联系电话:         "), wcfTytle));
			// 数据
			wcfTytle = new WritableCellFormat();
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
			for (int i = 0; i < cjList.size(); i++) {
				HashMap<String, String> tmp = (HashMap<String, String>) cjList.get(i);
				ws.addCell(new Label(0, 4 + i, String.valueOf(i + 1),wcfTytle));
				ws.addCell(new Label(1, 4 + i,String.valueOf(tmp.get("dwmc") == null ? "" : tmp.get("dwmc")), wcfTytle));
				ws.addCell(new Label(2, 4 + i,
						String.valueOf(tmp.get("bmmc") == null ? "" : tmp
								.get("bmmc")), wcfTytle));
				ws.addCell(new Label(3, 4 + i,
						String.valueOf(tmp.get("bjmc") == null ? "" : tmp
								.get("bjmc")), wcfTytle));
				ws.addCell(new Label(4, 4 + i, String
						.valueOf(tmp.get("xh") == null ? "" : tmp.get("xh")),
						wcfTytle));
				ws.addCell(new Label(5, 4 + i, String
						.valueOf(tmp.get("xm") == null ? "" : tmp.get("xm")),
						wcfTytle));
				ws.addCell(new Label(6, 4 + i,
						String.valueOf(tmp.get("gzsj") == null ? "" : tmp
								.get("gzsj")), wcfTytle));
				ws.addCell(new Label(7, 4 + i,
						String.valueOf(tmp.get("cjje") == null ? "" : tmp
								.get("cjje")), wcfTytle));
				ws.addCell(new Label(8, 4 + i,
						String.valueOf(tmp.get("lxdh") == null ? "" : tmp
								.get("lxdh")), wcfTytle));
				ws.addCell(new Label(9, 4 + i, String.valueOf(""), wcfTytle));
				ws.addCell(new Label(10, 4 + i, String.valueOf(""), wcfTytle));
			}

			// 小计
			ws.addCell(new Label(0, 4 + cjList.size(), String.valueOf("小计"),
					wcfTytle));
			ws.addCell(new Label(1, 4 + cjList.size(), String.valueOf(""),
					wcfTytle));
			ws.addCell(new Label(2, 4 + cjList.size(), String.valueOf(""),
					wcfTytle));
			ws.addCell(new Label(3, 4 + cjList.size(), String.valueOf(""),
					wcfTytle));
			ws.addCell(new Label(4, 4 + cjList.size(), String.valueOf(""),
					wcfTytle));
			ws.addCell(new Label(5, 4 + cjList.size(), String.valueOf(""),
					wcfTytle));
			ws.addCell(new Label(6, 4 + cjList.size(), String.valueOf(ygzzxs),
					wcfTytle));
			ws.addCell(new Label(7, 4 + cjList.size(), String.valueOf(zje),
					wcfTytle));
			ws.addCell(new Label(8, 4 + cjList.size(), String.valueOf(""),
					wcfTytle));
			ws.addCell(new Label(9, 4 + cjList.size(), String.valueOf(""),
					wcfTytle));
			ws.addCell(new Label(10, 4 + cjList.size(), String.valueOf(""),
					wcfTytle));

		} catch (RowsExceededException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 检测是否在校外学生岗位申请时间之内
	 * 
	 * @return boolean
	 */
	public boolean checkOutTime() {
		return dao.checkOutTime();
	}

	/**
	 * 检测是否在可直接分配岗位的时间范围内
	 * 
	 * @param model
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean checkAllow(QgzxZgdzdxForm model) throws SQLException {
		boolean flag = false;
		String userName = model.getUserName();
		String userType = model.getUserType();
		boolean isYrdw = dao.isYrdw(userName); 

		if (isYrdw && checkTime()) {
			flag = false;
		} else if (userType != null && !isYrdw
				&& ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)) && !checkTime()) {
			// 学校直接分配岗位的时间在可申请岗位时间范围之外
			flag = false;
		} else {
			flag = true;
		}

		return flag;
	}

	/**
	 * 获取用人单位列表
	 * 
	 * @return List
	 */
	public List getYrdwList() {
		return dao.getYrdwList();
	}

	public List getGwzjfpTopTr() {
		// ================ begin 骆嘉伟 2009/3/25 ==============
		String[] outputValue = { "主键", "xn", "nd", "xq", "xh", "xm", "xb", "nj", 
				                 "bjmc", "sfygw", "gwdm", "ssbh", "zzmmmc", "lxdh", 
				                 "xymc","gwzydj" };
		String[] colCN = { "主键", "学年", "年度", "学期", "学号", "姓名", "性别", 
				           "年级", Base.YXPZXY_KEY+"名称", "班级名称", "是否有岗位", "岗位名称", 
				           "宿舍编号", "政治面貌名称", "联系电话","岗位志愿等级" };
		// ================ end 骆嘉伟 2009/3/25 ==============
		return dao.arrayToList(outputValue, colCN);
	}

	/**
	 * 直接分配岗位信息查询
	 * 
	 * @param model
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getGwzjfpList(QgzxZgdzdxForm model) {
		List<HashMap<String, String>> rs = null;
		rs = dao.selectGwzjfpXxList(model);

		return rs;
	}
	
	/**
	 * 岗位直接分配用人单位用户查询学生信息总记录数
	 * 
	 * @param model
	 * @return List
	 */
	public int selectGwzjfpXxNum(QgzxZgdzdxForm model) {
		return dao.selectGwzjfpXxNum(model);
	}

	/**
	 * 获取可分配的岗位列表
	 * 
	 * @param model
	 * @return List
	 */
	public List getKfpgwList(QgzxZgdzdxForm model) {
		String userName = model.getUserName();
		List rs = null;
		// String userType = model.getUserType();
		String yrdwdm = dao.checkUserIsYrdw(userName);
		if (yrdwdm != null && !"".equalsIgnoreCase(yrdwdm)) {// 用人单位
			rs = dao.getYrdwKfpgw(yrdwdm);
		} else {
			rs = dao.getGwList();
		}
		return rs;
	}

	/**
	 * 获取勤工助学参数设置信息
	 * 
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getGwsqsjInfo() {
		return dao.getGwsqsjInfo();
	}

	/**
	 * 勤工助学申请批量审核判断岗位人数
	 * 
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	public boolean saveQgzxshBatch(QgzxZgdzdxForm model, String yesNo,
			HttpServletRequest request) throws Exception {
		boolean flag = false;
		String pkString = model.getPk();
		String[] pkValue = pkString.split("!!SplitOneSplit!!");
		String userType = model.getUserType();
		String message = "";
		String tableName = "qgzxsqb";
		String[] columns = { "xysh" };
		String[] values = { yesNo };

		if (userType != null
				&& ("admin".equalsIgnoreCase(userType) || "xx"
						.equalsIgnoreCase(userType))) {
			columns = new String[] { "xxsh" };
		}

		for (int i = 0; i < pkValue.length; i++) {
			if (pkValue[i] != null && !"".equalsIgnoreCase(pkValue[i])) {
				if (yesNo != null && "通过".equalsIgnoreCase(yesNo) && !"xy".equalsIgnoreCase(userType)) {
					if (!dao.checkPostNumber(pkValue[i], userType)) {
						HashMap<String, String> map = dao .getQgzxsqStuInfo(pkValue[i]);
						message += map.get("xm") + "审核失败," + map.get("xymc")
								+ map.get("xn") + "学年" + map.get("nd") + "年度"
								+ map.get("xqmc") + "学期" + "审核通过人数已经达到分配人数!\n";
					} else {
						flag = StandardOperation.update(tableName, columns,
								values, "xh||xn||nd||xq", pkValue[i], request);
					}
				} else {
					flag = StandardOperation.update(tableName, columns, values,
							"xh||xn||nd||xq", pkValue[i], request);
				}
			}
		}
		request.setAttribute("mes", message);
		return flag;
	}
	
	/**
	 * 广州大学勤工助学申请批量审核
	 * 
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	public boolean saveQgzxshBatchByGzdx(QgzxZgdzdxForm model, String yesNo,
			HttpServletRequest request) throws Exception {
		boolean flag = false;
		String pkString = model.getPk();
		String[] pkValue = pkString.split("!!SplitOneSplit!!");
		String userType = model.getUserType();
		String isFdy=model.getIsFdy();
		String message = "";
		String[] xmdm = StringUtils.isNull(model.getXmdm()) ? null : model
				.getXmdm().split("!!");
		String tableName = "qgzxsqb";
		String[] columns = { "zdgwdm", "zdgwdmgwsbsj", "xysh" };
		String xmdmStr=model.getXmdm()==null ? "" :model.getXmdm();
		String[] values = { model.getGwdm(), xmdmStr.replace("!!",""), yesNo };
		String gwshColumn = "xxyj";
		String xxdm=Base.xxdm;
		String gwxzdm = request.getParameter("gwxzdm");
		if ("true".equalsIgnoreCase(isFdy)){
			columns = new String[] { "zdgwdm", "zdgwdmgwsbsj", "xysh","fdyname" };
		    values = new String[]{ model.getGwdm(), xmdmStr.replace("!!", ""),yesNo, model.getFdyname() };
		}
		if (userType != null
				&& ("admin".equalsIgnoreCase(userType) || "xx"
						.equalsIgnoreCase(userType))) {
			columns = new String[] { "zdgwdm", "zdgwdmgwsbsj", "xxsh" };
		}
		// TODO
		// ---------2010/5/17 edit by luojw -----------
		int n = 0;
		model.setPlczrs(pkValue.length);
		if (pkValue != null && pkValue.length > 0) {

			String[] exec = null;
			if("通过".equalsIgnoreCase(yesNo) 
					&& "001".equals(gwxzdm) 
					&& ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType))){
				exec = new String[pkValue.length * 2];
			} else if ("通过".equalsIgnoreCase(yesNo)) {
				exec = new String[pkValue.length * (xmdm.length + 1)];
			} else if(Globals.XXDM_GZDX.equals(xxdm)){
				exec = new String[pkValue.length * 2];
			}else{
				exec = new String[pkValue.length];
			}
			StringBuffer sql = new StringBuffer();

			for (int i = 0; i < pkValue.length; i++) {

				if (pkValue[i] != null && !"".equalsIgnoreCase(pkValue[i])) {
					HashMap<String, String> map = new HashMap<String, String>();
					QgzxZgdzdxForm form = new QgzxZgdzdxForm();
					form.setPkValue(pkValue[i]);
					map = dao.selectQgzxsqInfoOne(form);

					model.setXn(map.get("xn"));
					model.setNd(map.get("nd"));
					model.setXq(map.get("xq"));
					model.setXh(map.get("xh"));
					if (null != yesNo && yesNo.equalsIgnoreCase("通过")
							&& null != xmdm ) {
						for (int j = 0; j < xmdm.length; j++) {
							if(!"".equalsIgnoreCase(xmdm[j])){
							String tmp = xmdm[j];
							if (!StringUtils.isNull(tmp)) {
								model.setGwdm(tmp.split("-")[0]);
								model.setGwsbsj(tmp.split("-")[1]);
								if (userType != null
										&& "xy".equalsIgnoreCase(userType)) {
									// 学院用户
									if (dao.checkPostCountByGzdx(model)) {
										request.setAttribute("mes", "'"
												+ model.getGwdm()
												+ "'人数已经超过分配人数，操作失败！");
										return false;
									}
									gwshColumn = "xyyj";
								}
								// 将学生岗位信息插入或更新到岗位信息表中
								String pk = "xh||gwdm||gwsbsj";
								String pkV = map.get("xh") + model.getGwdm()
										+ model.getGwsbsj();

								if (dao.checkExists("xsgwxxb", pk, pkV)) {
									// 记录已经存在，进行修改操作。
									sql = new StringBuffer();
									sql.append("update xsgwxxb set ");
									sql.append(gwshColumn);
									sql.append("='通过',sfyx = '1' where ");
									sql.append(pk + "='" + pkV + "'");

									exec[n] = sql.toString();
									n++;

								} else {
									// 记录不存在，进行插入操作。

									sql = new StringBuffer();
									sql.append("insert into xsgwxxb ");
									sql.append("(xh, xn, nd, xq, gwdm");
									sql.append(",gwsbsj, " + gwshColumn
											+ ", sfyx)");
									sql.append("values('" + map.get("xh") + "'");
									sql.append(",'" + map.get("xn") + "'");
									sql.append(",'" + map.get("nd") + "'");
									sql.append(",'" + map.get("xq") + "'");
									sql.append(",'" + model.getGwdm() + "'");
									sql.append(",'" + model.getGwsbsj() + "'");
									sql.append(",'通过'");
									sql.append(",'1')");

									exec[n] = sql.toString();
									n++;

								}
							}
						}
						}
						sql = new StringBuffer();
						sql.append("update " + tableName);
						for (int k = 0; k < columns.length; k++) {
							if (k == 0) {
								sql.append(" set " + columns[k] + "='" + values[k]
										+ "' ");
							} else {
								sql.append(", " + columns[k] + "='" + values[k]
										+ "' ");
							}
						}
						sql.append(" where xh||xn||nd||xq||gwxzdm = '" + pkValue[i]
								+ "'");
						exec[n] = sql.toString();
						n++;
					}else if( yesNo.equalsIgnoreCase("不通过") 
									||yesNo.equalsIgnoreCase("未审核")){
						String shzd="xxsh";
						if (userType != null
								&& "xy".equalsIgnoreCase(userType)) {
							shzd = "xysh";
							gwshColumn="xyyj";
						}
						
						String pk = " b.xh||b.xn||b.nd||b.xq||b.gwxzdm ";
						String pkV = form.getPkValue();
							sql = new StringBuffer();
							sql.append("update  xsgwxxb a set "+gwshColumn+"='"+yesNo+"' where exists ");
							sql.append(" (select * from view_qgzxsqb b where  ");
							sql.append(pk + "='" + pkV + "'");
							sql.append(" and a.gwdm||'-'||a.gwsbsj=b.zdgwdmgwsbsj and a.xh=b.xh) ");
							exec[n] = sql.toString();
							n++;
							
						sql = new StringBuffer();
						sql.append("update " + tableName);
						sql.append(" set " + shzd + "='"+yesNo+"' ");
						sql.append(" where xh||xn||nd||xq||gwxzdm = '" + pkValue[i]
								+ "'");
						exec[n] = sql.toString();
						n++;
					}else {
						String pk = " b.xh||b.xn||b.nd||b.xq||b.gwxzdm ";
						String pkV = form.getPkValue();
						sql = new StringBuffer();
						sql.append("update xsgwxxb a set xxyj='通过' where exists ");
						sql.append(" (select * from view_qgzxsqb b where  ");
						sql.append(pk + "='" + pkV + "'");
						sql.append(" and a.gwdm||'-'||a.gwsbsj=b.zdgwdmgwsbsj and a.xh=b.xh) ");
						exec[n] = sql.toString();
						n++;
						
						String shzd="xxsh";
						if (userType != null
								&& "xy".equalsIgnoreCase(userType)) {
							shzd = "xysh";
						}
						sql = new StringBuffer();
						sql.append("update " + tableName);
						sql.append(" set " + shzd + "='通过' ");
						sql.append(" where xh||xn||nd||xq||gwxzdm = '" + pkValue[i]
								+ "'");
						exec[n] = sql.toString();
						n++;
					}
					
				}
			}
			int[] res = dao.runBatch(exec);

			for (int i = 0; i < res.length; i++) {
				flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
				if (!flag)
					break;
			}
		}
		// ---------end -----------

		request.setAttribute("mes", message);
		return flag;
	}
	
	/**
	 * 获取查询条件
	 * @param models
	 * @return String
	 */
	public String getExportGwzjfpSql(QgzxZgdzdxForm model) {
		return dao.getWhereExportSql(model).toString();
	}

	/**
	 * 获取可导出的数据列
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getColumn2ExportGwzjfp() {
		String[] col = { "xn", "nd", "xq", "xh", "xm", "xb", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "lxdh","是否有岗位", "岗位名称" };
		String[] colCN = { "学年", "年度", "学期", "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY+"代码", Base.YXPZXY_KEY+"名称", "专业代码",
				"专业名称", "班级代码", "班级名称","联系电话", "是否有岗位", "岗位名称" };
		return dao.arrayToList(col, colCN);
	}

	/**
	 * 组合查询表字段的查询语句
	 * 
	 * @param tableName
	 * @param key
	 * @param form
	 * @return String
	 */
	public String getColumnSql2Exp(String[] key, QgzxZgdzdxForm form) {
		String userName = form.getUserName();
		String sql = "select ";
		StringBuffer column = new StringBuffer();// 查询字段
		String tableName = "";

		if (dao.isYrdw(userName)) {
			String yrdwdm = dao.checkUserIsYrdw(userName);
			form.setYrdwdm(yrdwdm);
			form.setUserType("yrdw");

			tableName = dao.getTableSql(form);
		} else {
			form.setUserType("xx");
			tableName = dao.getTableSql(form);
		}

		for (int i = 0; i < key.length; i++) {// 组合要导出的具体字段
			column.append(key[i]);
			column.append(",");
		}
		column.deleteCharAt(column.length() - 1);// 去除最后的","号
		sql += column;
		sql += " from (" + tableName + ") ";
		return sql;
	}
	
	/**
	 * 获取政治面貌代码列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getZzmmList(){
		return dao.getZzmmList();
	}
	
	/**
	 * 查询单个学院人数分配信息
	 * @param QgzxZgdzdxForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryXyrsfpxx(QgzxZgdzdxForm model){
		return dao.selectXyrsfpxx(model);
	}
	
	/**
	 * 根据主键查询勤工助学申请信息
	 * @param QgzxZgdzdxForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getQgzxsqInfoOne(QgzxZgdzdxForm model){
		return dao.selectQgzxsqInfoOne(model);
	}
	
	/**
	 * 删除勤工助学申请信息
	 * @param QgzxZgdzdxForm model
	 * @return HashMap<String, String>
	 * */
	public boolean delQgzxsq(QgzxZgdzdxForm model){
		return dao.deleteQgzxsq(model);
	}
	
	/**
	 * 根据表字段获取字段注释
	 * @param String[] input
	 * @param String tableName
	 * @return String[]
	 * */
	public String[] getColumnNameCN(String[] input,String tableName){
		return dao.getColumnNameCN(input, tableName);
	}
	
	public List<String[]> queryXsqgzxsqForExport(QgzxZgdzdxForm model,String[] colList){
		return dao.selectXsqgzxsqForExp(model,colList);
	}
	
	/**
	 * 获得所申请岗位剩余人数
	 * 
	 * @author luojw
	 */
	public String getGwsyrs(QgzxZgdzdxForm model) {

		return dao.getGwsyrs(model);
	}
	
	/**
	 * 获得临时岗位列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String,String>> getLsgwList() {

		return dao.getLsgwList();
	}
	
	/**
	 * 保存临时岗位信息
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param arrzd(批量字段)
	 * @param onezd(单一字段)
	 * @param notnull(非空字段)
	 * 
	 * @throws Exception
	 */
	public boolean saveLsgw(QgzxZgdzdxForm model) throws Exception {
		
		DAO tyDAO = DAO.getInstance();
		
		String[] pkValue = model.getCbv();// 主键

		List<HashMap<String, String>> qgzxsqInfoList = dao
				.getQgzxsqInfoList(pkValue);

		boolean flag = false;
		
		if (qgzxsqInfoList != null && qgzxsqInfoList.size() > 0) {
			
			StringBuffer sql = new StringBuffer();
			String[] exec = new String[qgzxsqInfoList.size()*4];
			int n = 0;
			
			String gwxzdm = dao.getOneValue("gwxzdmb", "gwxzdm", "gwxzmc", "临时岗位");
			String fdysh = "通过";
			String xysh = "通过";
			String xxsh = "通过";
			String zdgwdm = model.getLsgw();// 临时岗位
			String gwsbsj = dao.getOneValue("gwxxb", "gwsbsj", "gwxz||gwdm",
					gwxzdm + zdgwdm);
			String sqsj = tyDAO.getNowTime("2");
			String sfyx = "1";
			
			for (int i = 0; i < qgzxsqInfoList.size(); i++) {
				
				HashMap<String, String> map = qgzxsqInfoList.get(i);
				
				String xn = map.get("xn");
				String xq = map.get("xq");
				String nd = map.get("nd");
				String xh = map.get("xh");
				String lxdh = map.get("lxdh");
				lxdh = Base.isNull(lxdh) ? "" : lxdh;
				String kcjqgzxsj = map.get("kcjqgzxsj");
				kcjqgzxsj = Base.isNull(kcjqgzxsj) ? "" : kcjqgzxsj;
				String sqly = map.get("sqly");
				sqly = Base.isNull(sqly) ? "" : sqly;
				String bz = map.get("bz");
				bz = Base.isNull(bz) ? "" : bz;
				String yhtc = map.get("yhtc");
				yhtc = Base.isNull(yhtc) ? "" : yhtc;
				String zzmmdm = map.get("zzmmdm");
				zzmmdm = Base.isNull(zzmmdm) ? "" : zzmmdm;
				String kcsgz = map.get("kcsgz");
				kcsgz = Base.isNull(kcsgz) ? "" : kcsgz;
				String jjqk = map.get("jjqk");
				jjqk = Base.isNull(jjqk) ? "" : jjqk;
				
				sql = new StringBuffer();
				sql.append("delete from qgzxsqb ");
				sql.append("where xn = '" + xn + "' ");
				sql.append("and xq = '" + xq + "' ");
				sql.append("and nd = '" + nd + "' ");
				sql.append("and xh = '" + xh + "' ");
				sql.append("and gwxzdm = '" + gwxzdm + "' ");
				exec[n] = sql.toString();
				n++;
				
				sql = new StringBuffer();
				sql.append(" insert into qgzxsqb ");
				sql.append("(xh, xn, nd, xq, lxdh, kcjqgzxsj, sqly,");
				sql.append(" bz,zzmmdm, yhtc, kcsgz,jjqk,xysh,xxsh,gwxzdm,zdgwdm)");
				sql.append(" values(");
				sql.append("'" + xh + "'");
				sql.append(",'" + xn + "'");
				sql.append(",'" + nd + "'");
				sql.append(",'" + xq + "'");
				sql.append(",'" + lxdh + "'");
				sql.append(",'" + kcjqgzxsj + "'");
				sql.append(",'" + sqly + "'");
				sql.append(",'" + bz + "'");
				sql.append(",'" + zzmmdm + "'");
				sql.append(",'" + yhtc + "'");
				sql.append(",'" + kcsgz + "'");
				sql.append(",'" + jjqk + "'");
				sql.append(",'" + xysh + "'");
				sql.append(",'" + xxsh + "'");
				sql.append(",'" + gwxzdm + "'");
				sql.append(",'" + zdgwdm + "'");
				sql.append(")");
				exec[n] = sql.toString();
				n++;
				
				sql = new StringBuffer();
				sql.append("delete from xsgwxxb ");
				sql.append("where xh = '" + xh + "' ");
				sql.append("and gwdm = '" + zdgwdm + "' ");
				sql.append("and gwsbsj = '" + gwsbsj + "' ");
				exec[n] = sql.toString();
				n++;
				
				sql = new StringBuffer();
				sql.append(" insert into xsgwxxb ");
				sql.append("(xh, gwdm,gwsbsj, sqsj, fdyyj,xyyj, xxyj, xn, xq,nd,sfyx)");
				sql.append(" values(");
				sql.append("'" + xh + "'");
				sql.append(",'" + zdgwdm + "'");
				sql.append(",'" + gwsbsj + "'");
				sql.append(",'" + sqsj + "'");
				sql.append(",'" + fdysh + "'");
				sql.append(",'" + xysh + "'");
				sql.append(",'" + xxsh + "'");
				sql.append(",'" + xn + "'");
				sql.append(",'" + xq + "'");
				sql.append(",'" + nd + "'");
				sql.append(",'" + sfyx + "'");
				sql.append(")");
				exec[n] = sql.toString();
				n++;
			}
			
			int[] res = dao.runBatch(exec);

			for (int i = 0; i < res.length; i++) {
				flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
				if (!flag)
					break;
			}
		}
		
		return flag;
	}
	
	public HashMap<String, String> qgzxzsPrint(HashMap<String, String> rs) {
		return dao.qgzxzsPrint(rs);
	}
	
	/**
	 * 获得勤工助学申请信息
	 * 
	 * @author luojw
	 * 
	 */
	public HashMap<String, String> getQgzxsqInfo(String pkValue) {
		String tableName = "view_qgzxsqb";
		String pk = "xh||xn||nd||xq||gwxzdm";
		String[] colList = new String[] { "kcjqgzxsj", "yhtc", "sqly", "bz" };
		return CommonQueryDAO.commonQueryOne(tableName, colList, pk, pkValue);
	}
	
	/**
	 * 获得申请了勤工助学的学生
	 * @param model
	 * @return
	 */
	public List<String[]> queryQgzxStu(CommanForm model){
		List<String[]> list = new ArrayList<String[]>();
		MakeQuery makeQuery = new MakeQuery();
		
		String[] colLikeList = new String[]{"xh", "xm"};
		String[] colList = new String[]{"nd", "xn", "xq", "nj", "xydm", "zydm", "bjdm"};
		
		try {
			makeQuery.makeQuery(colList, colLikeList, model);
			
			String[] outputs = new String[]{"xh","nd","xn","xqmc","xm","xymc","zymc","bjmc"};
			
			String sql = "select rownum r, a.* from view_qgzxsqb a" + makeQuery.getQueryString();
			String query = " and xxsh='通过'";
			
			String[] inputs = makeQuery.getInputList();
			list = CommonQueryDAO.commonQuery(sql, query, inputs, outputs, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Map<String, String> getQgzxInfo(){
		String sql = "select xn,nd,xq,knsbl from gwsqsjb where rownum=1";
		
		return dao.getMapNotOut(sql, new String[]{});
	}
}
