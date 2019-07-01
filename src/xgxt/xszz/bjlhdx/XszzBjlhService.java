package xgxt.xszz.bjlhdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.base.Excel2Oracle;
/**
 * Title: 学生工作管理系统
 * Description: 北京联合大学学生资助Service
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: 周觅
 * Version: 1.0
 * Time: 2009-04-23
 */
public class XszzBjlhService {

	XszzBjlhDAO dao = null;// 数据操作DAO

	/**
	 * 获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzBjlhDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * 获取国家助学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjxx(String pkVal) throws Exception {
		dao = new XszzBjlhDAO();
		return dao.getGjzxjxx(pkVal);
	}
	
	/**
	 * 得到国家助学金申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjzxjSqQx(String sUserType,String userDep,String xh,String xn) throws Exception {
		dao = new XszzBjlhDAO();
		return dao.getGjzxjSqQx(sUserType,userDep,xh,xn);
	}
	
	/**
	 * 保存国家助学金申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjSqxx(GjzxjModel gjzxjModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzBjlhDAO();
		return dao.saveGjzxjSqxx(gjzxjModel, request);
	}
	
	/**
	 * 得到国家助学金申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjSqb(GjzxjModel gjzxjModel,HttpServletRequest request) throws Exception {
		dao = new XszzBjlhDAO();
		return dao.getGjzxjSqb(gjzxjModel,request);
	}
	
	/**
	 * 删除国家助学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzBjlhDAO();
		dao.delGjzxjxx(cbVal, request);
	}
	
	/**
	 * 批量修改国家助学金审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzBjlhDAO();
		dao.modGjzxjxx(cbVal, shjg, request);
	}
	
	/**
	 * 国家助学金查询表头 gjzxjtit ---- 国家助学金查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxjTit() throws Exception {
		dao = new XszzBjlhDAO();
		return dao.getGjzxjTit();
	}
	
	/**
	 * 国家助学金查询结果 gjzxjres ---- 国家助学金
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzBjlhDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 国家助学金导出 gjzxjExp ---- 国家助学金导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getGjzxjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzBjlhDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_bjlhdx_gjzxj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_bjlhdx_gjzxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存国家助学金审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjShxx(GjzxjModel gjzxjModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzBjlhDAO();
		return dao.saveGjzxjShxx(gjzxjModel, request);
	}
	
	/**
	 * 获得国家助学金列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getGjzxjList() throws Exception {
		dao = new XszzBjlhDAO();
		return dao.getGjzxjList();
	}
	
}
