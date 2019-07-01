package xgxt.xszz.gzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;

/**
 * Title: 学生工作管理系统
 * Description: 广州大学学生资助Service
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: 周觅
 * Version: 1.0
 * Time: 2009-12-24
 */
public class XszzGzdxService {

	XszzGzdxDAO dao = null;// 数据操作DAO

	private List<HashMap<String, String>> makeTitList(String[] enList, String[] cnList) {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * 删除贷款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXsdkxx(String[] cbVal)
			throws Exception {
		dao = new XszzGzdxDAO();
		dao.delXsdkxx(cbVal);
	}
	
	/**
	 * 贷款信息查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXsdkxxTit() throws Exception {
		String[] enList = new String[] { "pk", "xn", "dkcs", "xh", "xm", "xb",
				"sfzh", "xymc", "zymc", "bjmc", "dknj", "dkze", "htbh" };
		String[] cnList = new String[] { "主键", "学年", "贷款次数", "学号", "姓名", "性别", "身份证号",
				Base.YXPZXY_KEY, "专业", "班级","贷款年级",  "贷款总额", "合同编号" };
		return makeTitList(enList, cnList);
	}
	
	/**
	 * 贷款信息查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXsdkxxRes(QueryModel queryModel,
			HttpServletRequest request, XszzGzdxActionForm actionForm)
			throws Exception {
		dao = new XszzGzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXsdkxxRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * 贷款信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXsdkxxResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		dao = new XszzGzdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getXsdkxxResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * 贷款信息导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getXsdkxxExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new XszzGzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_gzdx_xsdkxxb", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_gzdx_xsdkxxb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 获取贷款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsdkxx(String pkVal) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.getXsdkxx(pkVal);
	}
	
	/**
	 * 获取贷款总信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsdkzxx(String pkVal) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.getXsdkzxx(pkVal);
	}
	
	/**
	 * 保存贷款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXsdkxx(XsdkxxbModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.saveXsdkxx(model, request);
	}
	
	/**
	 * 删除就业信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXsjyxx(String[] cbVal)
			throws Exception {
		dao = new XszzGzdxDAO();
		dao.delXsjyxx(cbVal);
	}
	
	/**
	 * 就业信息查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXsjyxxTit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "xb",
				"sfzh", "xymc", "zymc", "bjmc" };
		String[] cnList = new String[] { "主键", "学号", "姓名", "性别", "身份证号",
				Base.YXPZXY_KEY, "专业", "班级" };
		return makeTitList(enList, cnList);
	}
	
	/**
	 * 就业信息查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXsjyxxRes(QueryModel queryModel,
			HttpServletRequest request, XszzGzdxActionForm actionForm)
			throws Exception {
		dao = new XszzGzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXsjyxxRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * 就业信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXsjyxxResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		dao = new XszzGzdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getXsjyxxResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * 就业信息导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getXsjyxxExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new XszzGzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_gzdx_dkxsjyxxb", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_gzdx_dkxsjyxxb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 获取就业信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsjyxx(String pkVal) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.getXsjyxx(pkVal);
	}
	
	/**
	 * 保存就业信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXsjyxx(XsjyxxbModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.saveXsjyxx(model, request);
	}
	
	/**
	 * 删除还款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXshkxx(String[] cbVal)
			throws Exception {
		dao = new XszzGzdxDAO();
		dao.delXshkxx(cbVal);
	}
	
	/**
	 * 还款信息查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXshkxxTit() throws Exception {
		String[] enList = new String[] { "pk", "htbh", "xn", "dkcs", "dkze",
				"sfqbhqdk" };
		String[] cnList = new String[] { "主键", "合同编号", "贷款学年", "贷款次数", "贷款总额",
				"是否提前还款" };
		return makeTitList(enList, cnList);
	}
	
	/**
	 * 还款信息查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXshkxxRes(QueryModel queryModel,
			HttpServletRequest request, XszzGzdxActionForm actionForm)
			throws Exception {
		dao = new XszzGzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXshkxxRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * 还款信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXshkxxResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		dao = new XszzGzdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getXshkxxResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * 还款信息导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getXshkxxExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new XszzGzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_gzdx_hkxxb", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_gzdx_hkxxb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 获取还款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXshkxx(String pkVal) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.getXshkxx(pkVal);
	}
	
	/**
	 * 获取贷款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsdkxxByHtbh(String pkVal) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.getXsdkxxByHtbh(pkVal);
	}
	
	/**
	 * 保存还款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXshkxx(XshkxxbModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.saveXshkxx(model, request);
	}
	
	/**
	 * 删除临时补贴信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delLsbt(String[] cbVal)
			throws Exception {
		dao = new XszzGzdxDAO();
		dao.delLsbt(cbVal);
	}
	
	/**
	 * 临时补贴信息查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsbtTit() throws Exception {
		String[] enList = new String[] { "pk", "btsj", "xh", "xm", "xb",
				"sfzh", "xymc", "zymc", "bjmc", "nj", "btje" };
		String[] cnList = new String[] { "主键", "补贴时间", "学号", "姓名", "性别",
				"身份证号", Base.YXPZXY_KEY, "专业", "班级", "年级", "补贴金额" };
		return makeTitList(enList, cnList);
	}
	
	/**
	 * 临时补贴信息查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbtRes(QueryModel queryModel,
			HttpServletRequest request, XszzGzdxActionForm actionForm)
			throws Exception {
		dao = new XszzGzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getLsbtRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * 临时补贴信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getLsbtResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		dao = new XszzGzdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getLsbtResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * 临时补贴信息导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getLsbtExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new XszzGzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_gzdx_lsbt", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_gzdx_lsbt");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 获取临时补贴信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsbt(String pkVal) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.getLsbt(pkVal);
	}
	
	/**
	 * 保存临时补贴信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsbt(LsbtModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.saveLsbt(model, request);
	}
}
