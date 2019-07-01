package xgxt.xszz.zzlgdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;

/**
 * Title: 学生工作管理系统
 * Description: 浙江理工大学Service
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: 周觅
 * Version: 1.0
 * Time: 2009-12-17
 */
public class XszzZzlgdxService {

	XszzZzlgdxDAO dao = null;// 数据操作DAO

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
		dao = new XszzZzlgdxDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * 删除助学贷款信息
	 * 
	 * @param pkT,request
	 * @return
	 * @throws Exception
	 */
	public void delZxdk(String[] pk,HttpServletRequest request){
		dao = new XszzZzlgdxDAO();
		dao.delZxdk(pk, request);
	}
	
	/**
	 * 助学贷款信息查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZxdkTit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "sfzh", "xymc",
				"zymc", "bjmc", "dkhth", "dkje" };
		String[] cnList = new String[] { "主键", "学号", "姓名", "身份证号", Base.YXPZXY_KEY, "专业",
				"班级", "合同号", "贷款金额" };
		return makeTitList(enList, cnList);
	}
	
	/**
	 * 助学贷款信息结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZxdkRes(QueryModel queryModel,
			HttpServletRequest request, XszzZzlgdxActionForm actionForm)
			throws Exception {
		dao = new XszzZzlgdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getZxdkRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * 助学贷款信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getZxdkResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		dao = new XszzZzlgdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getZxdkResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * 获取助学贷款详细信息
	 * 
	 * @param guid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxdkXx(String guid) throws Exception {
		dao = new XszzZzlgdxDAO();
		return dao.getZxdkXx(guid);
	}
	
	/**
	 * 保存助学贷款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] saveZxdkxx(ZxdkModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZzlgdxDAO();
		return dao.saveZxdkxx(model, request);
	}
	
	/**
	 * 助学贷款导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getZxdkExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new XszzZzlgdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_zzlg_zxdk", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_zzlg_zxdk");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
}
