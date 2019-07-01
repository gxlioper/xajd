package xgxt.xszz.nbzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.utils.String.StringUtils;

/**
 * Title: 学生工作管理系统
 * Description: 宁波职业技术学院学生资助Service
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: 周觅
 * Version: 1.0
 * Time: 2009-07-13
 */
public class XszzNbzyjsxyService {
	
	private static String JKRXX_TIT = "主键-姓名-角色-身份证号-单位-部门-捐款总金额";
	private static String JKRXYTJXX_TIT = Base.YXPZXY_KEY+"-捐款人数-捐款人次-捐款总金额";
	private static String JKRZYTJXX_TIT = "专业-捐款人数-捐款人次-捐款总金额";
	private static String JKRJSTJXX_TIT = "角色-捐款人数-捐款人次-捐款总金额";
	private static String SPLIT_TYPE = "-";

	XszzNbzyjsxyDAO dao = null;// 数据操作DAO
	
	/**
	 * 获取学生信息
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String pkVal) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.getStu(pkVal);
	}
	
	/**
	 * 获取捐款人为学生或用户相关信息
	 * 
	 * @param xhyhm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJkrxxByXhyhm(String xhyhm) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.getJkrxxByXhyhm(xhyhm);
	}
	
	/**
	 * 获取捐款人相关信息
	 * 
	 * @param guid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJkrxxByGuid(String guid) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.getJkrxxByGuid(guid);
	}
	
	/**
	 * 获取捐款人相关信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJkrxxByModel(JkrxxModel model) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.getJkrxxByModel(model);
	}
	
	/**
	 * 删除捐款人信息
	 * 
	 * @param pkT,request
	 * @return
	 * @throws Exception
	 */
	public void delJkrxx(String[] pkT,HttpServletRequest request){
		dao = new XszzNbzyjsxyDAO();
		dao.delJkrxx(pkT, request);
	}

	/**
	 * 捐款人信息查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJkrTit() throws Exception {
		String[] enList = new String[] { "pkT", "xm", "jsmc",
				"sfzh", "dw", "bm", "jkje" };
		return makeTitList(enList, StringUtils.splitStr(JKRXX_TIT,SPLIT_TYPE));
	}
	
	/**
	 * 捐款人信息统计表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJkrTjTit(String tjlx) throws Exception {
		String[] enList = new String[] { "bm", "rs", "cs", "zje" };
		String titleStr = null;
		//"部门-捐款人数-捐款人次-捐款总金额"
		if ("1".equalsIgnoreCase(tjlx)) {//学院
			titleStr = JKRXYTJXX_TIT;
		} else if ("2".equalsIgnoreCase(tjlx)) {//角色
			titleStr = JKRJSTJXX_TIT;
		} else if ("3".equalsIgnoreCase(tjlx)) {//专业
			titleStr = JKRZYTJXX_TIT;
		} else {
			//占坑
		}
		return makeTitList(enList, StringUtils.splitStr(titleStr,SPLIT_TYPE));
	}

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
	 * 捐款人信息结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJkrRes(QueryModel queryModel,HttpServletRequest request,XszzNbzyjsxyActionForm actionForm)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.getJkrRes(queryModel,request,actionForm);
	}
	
	/**
	 * 捐款人统计结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJkrTjRes(QueryModel queryModel)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getJkrTjRes(queryModel);
		}
		return resList;
	}
	
	/**
	 * 捐款人信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getJkrxxResNum(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getJkrxxResNum(queryModel,request);
		}
		return sT;
	}
	
	/**
	 * 捐款人信息导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getJkrxxExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nbzy_syjj_jkrxxb",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nbzy_syjj_jkrxxb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 捐款人捐款记录结果
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJkjlList(String pkVal) {
		dao = new XszzNbzyjsxyDAO();
		return dao.getJkjlList(pkVal);
	}
	
	/**
	 * 保存捐款人信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveJkrxx(JkrxxModel model, String act,
			HttpServletRequest request) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.saveJkrxx(model, act, request);
	}
	
	/**
	 * 保存捐款记录
	 * @param param
	 * @param guid
	 * @return
	 * @throws Exception
	 */
	public boolean saveJklu(List<HashMap<String, String>> param,
			String guid) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.saveJklu(param, guid);
	}
	
	/**
	 * 获取思源基金信息
	 * 
	 * @param guid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getSyjjxxByGuid(String guid) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.getSyjjxxByGuid(guid);
	}
	
	/**
	 * 保存思源基金申请信息
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveSyjjxx(SyjjModel model, HttpServletRequest request)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.saveSyjjxx(model, request);
	}
	
	/**
	 * 获取思源基金信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getSyjjxxByModel(SyjjModel model) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.getSyjjxxByModel(model);
	}
	
	/**
	 * 删除思源基金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXyjj(String[] pkT, HttpServletRequest request)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		dao.delXyjj(pkT, request);
	}
	
	/**
	 * 批量修改思源基金审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXyjj(String[] pkT, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		dao.modXyjj(pkT, shjg, request);
	}
	
	/**
	 * 思源基金信息查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXyjjTit() throws Exception {
		String[] enList = new String[] { "bgcolor", "pkT", "xh", "xm", "sqje",
				"sqsj", "spje", "bjrsh", "xysh", "xxsh", "jkcs", "jkzje", "jkye" };
		return makeTitList(enList, StringUtils.splitStr(
								"bgcolor-主键-学号-姓名-申请金额-申请时间-审批金额-保荐人审核-系审核-学校审核-捐款次数-捐款总金额-捐款余额",
								SPLIT_TYPE));
	}
	
	/**
	 * 思源基金信息统计表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXyjjTjTit(String userType) throws Exception {
		String[] enList = new String[] { "bm", "zs", "zje" };
		String titleStr = null;
		if ("bjr".equalsIgnoreCase(userType)) {
			titleStr = "班级-获得人次-总金额";
		} else if ("xy".equalsIgnoreCase(userType)) {
			titleStr = "专业-获得人次-总金额";
		} else {
			titleStr = Base.YXPZXY_KEY+"-获得人次-总金额";
		}
		return makeTitList(enList, StringUtils.splitStr(titleStr, SPLIT_TYPE));
	}
	
	/**
	 * 思源基金结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXyjjRes(QueryModel queryModel,HttpServletRequest request,XszzNbzyjsxyActionForm actionForm)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXyjjRes(queryModel,request,actionForm);
		}
		return resList;
	}
	
	/**
	 * 思源基金统计结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXyjjTjRes(String userType,String userDep,ArrayList<String> userBj)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.getXyjjTjRes(userType,userDep,userBj);
	}
	
	/**
	 * 思源基金查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXyjjResNum(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getXyjjResNum(queryModel,request);
		}
		return sT;
	}
	
	/**
	 * 思源基金信息导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getSyjjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nbzyjsxy_syjj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nbzyjsxy_syjj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存思源基金审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveSyjjShxx(SyjjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.saveSyjjShxx(model, request);
	}

	/**
	 * 获得学生获得思源基金记录
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSyjjList(String xh) {
		dao = new XszzNbzyjsxyDAO();
		return dao.getSyjjList(xh);
	}
}
