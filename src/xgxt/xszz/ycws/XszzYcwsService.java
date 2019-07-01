package xgxt.xszz.ycws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.base.Excel2Oracle;

/**
 * Title: 学生工作管理系统
 * Description: 盐城卫生学生资助Service
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: 周觅
 * Version: 1.0
 * Time: 2009-06-09
 */
public class XszzYcwsService {

	XszzYcwsDAO dao = null;// 数据操作DAO

	/**
	 * 获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzYcwsDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * 删除困难认定信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnrdxx(String[] pk, HttpServletRequest request)
			throws Exception {
		dao = new XszzYcwsDAO();
		dao.delKnrdxx(pk, request);
	}
	
	/**
	 * 困难认定查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnrdTit() throws Exception {
		dao = new XszzYcwsDAO();
		return dao.getKnrdTit();
	}
	
	/**
	 * 困难认定查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnrdRes(QueryModel queryModel,HttpServletRequest request,XszzYcwsActionForm xszzYcwsActionForm)
			throws Exception {
		dao = new XszzYcwsDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnrdRes(queryModel,request,xszzYcwsActionForm);
		}
		return resList;
	}
	
	/**
	 * 困难认定查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnrdResNum(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzYcwsDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnrdResNum(queryModel,request);
		}
		return sT;
	}
	
	/**
	 * 得到开关值
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKg() throws Exception {
		dao = new XszzYcwsDAO();
		return dao.getKg();
	}
	
	/**
	 * 困难认定信息导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getKnsrdExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzYcwsDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_ycws_knrdxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_ycws_knrdxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 获取困难认定信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrdxx(String pkVal) throws Exception {
		dao = new XszzYcwsDAO();
		return dao.getKnsrdxx(pkVal);
	}
	
	/**
	 * 保存困难认定信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnrd(KnrdModel model, String act,
			HttpServletRequest request) throws Exception {
		dao = new XszzYcwsDAO();
		return dao.saveKnrd(model, act, request);
	}
	
	/**
	 * 删除困难资助项目信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnzzxmxx(String[] pk, HttpServletRequest request)
			throws Exception {
		dao = new XszzYcwsDAO();
		dao.delKnzzxmxx(pk, request);
	}
	
	/**
	 * 困难资助项目查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnzzxmTit() throws Exception {
		dao = new XszzYcwsDAO();
		return dao.getKnzzxmTit();
	}
	
	/**
	 * 困难资助项目查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnzzxmRes(QueryModel queryModel,HttpServletRequest request,XszzYcwsActionForm xszzYcwsActionForm)
			throws Exception {
		dao = new XszzYcwsDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnzzxmRes(queryModel,request,xszzYcwsActionForm);
		}
		return resList;
	}
	
	/**
	 * 困难资助项目查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnzzxmResNum(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzYcwsDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnzzxmResNum(queryModel,request);
		}
		return sT;
	}
	
	/**
	 * 困难资助项目信息导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getKnzzxmExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzYcwsDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_ycws_knzzxm",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_ycws_knzzxm");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 获取困难资助项目信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnzzxmxx(String pkVal) throws Exception {
		dao = new XszzYcwsDAO();
		return dao.getKnzzxmxx(pkVal);
	}
	
	/**
	 * 保存困难资助项目信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnzzxm(KnzzxmModel model, String act,
			HttpServletRequest request) throws Exception {
		dao = new XszzYcwsDAO();
		return dao.saveKnzzxm(model, act, request);
	}
}
