package xgxt.xszz.shgc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.base.Excel2Oracle;

/**
 * Title: 学生工作管理系统
 * Description: 上海工程学生资助Service
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: 周觅
 * Version: 1.0
 * Time: 2009-06-10
 */
public class XszzShgcService {

	XszzShgcDAO dao = null;// 数据操作DAO

	/**
	 * 获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzShgcDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * 删除困难生历史库信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnslskxx(String[] pk, HttpServletRequest request)
			throws Exception {
		dao = new XszzShgcDAO();
		dao.delKnslskxx(pk, request);
	}
	
	/**
	 * 困难生历史库查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnslskTit() throws Exception {
		dao = new XszzShgcDAO();
		return dao.getKnslskTit();
	}
	
	/**
	 * 困难生历史库查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnslskRes(QueryModel queryModel,HttpServletRequest request,XszzShgcActionForm xszzShgcActionForm)
			throws Exception {
		dao = new XszzShgcDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnslskRes(queryModel,request,xszzShgcActionForm);
		}
		return resList;
	}
	
	/**
	 * 困难生历史库查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnslskResNum(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzShgcDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnslskResNum(queryModel,request);
		}
		return sT;
	}
	
	/**
	 * 困难生历史库信息导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getKnslskExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzShgcDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "SHGC_KNSXX_LSK",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("SHGC_KNSXX_LSK");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 获取困难生历史库信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnslskxx(String pkVal) throws Exception {
		dao = new XszzShgcDAO();
		return dao.getKnslskxx(pkVal);
	}
	
	/**
	 * 保存困难生历史库信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnslsk(KnslskModel model, String act,
			HttpServletRequest request) throws Exception {
		dao = new XszzShgcDAO();
		return dao.saveKnslsk(model, act, request);
	}
}
