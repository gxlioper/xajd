package xgxt.xszz.zjjdzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.base.Excel2Oracle;
/**
 * Title: 学生工作管理系统
 * Description: 浙江机电职业技术学院学生资助Service
 * Copyright: Copyright (c) 2008
 * Company: zfsoft
 * Author: 周觅
 * Version: 1.0
 * Time: 2008-07-22
 */
public class XszzZjjdService {

	XszzZjjdDAO dao = null;// 数据操作DAO

	/**
	 * 获取困难生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsxx(String pkVal) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getKnsxx(pkVal);
	}
	
	/**
	 * 获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * 获取学生获得资助等信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuZzqk(String xh) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getStuZzqk(xh);
	}
	
	/**
	 * 得到困难生申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getKnsSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getKnsSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * 保存困难生申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsSqxx(KnsModel saveKnsModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveKnsSqxx(saveKnsModel, request);
	}
	
	/**
	 * 得到困难生申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsSqb(KnsModel knsModel,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getKnsSqb(knsModel,request);
	}
	
	/**
	 * 删除困难生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.delKnsxx(cbVal, request);
	}
	
	/**
	 * 批量修改困难生审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.modKnsxx(cbVal, shjg, request);
	}
	
	/**
	 * 困难生查询表头 knstit ---- 困难生查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsTit() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getKnsTit();
	}
	
	/**
	 * 困难生查询结果 knsres ---- 困难生
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 困难生导出 knsExp ---- 困难生导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getKnsExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zjjd_knsxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zjjd_knsxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存困难生审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsShxx(KnsModel saveKnsModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveKnsShxx(saveKnsModel, request);
	}
	
	/**
	 * 获取国家助学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjxx(String pkVal) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjzxjxx(pkVal);
	}
	
	/**
	 * 得到国家助学金申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjzxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjzxjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * 保存国家助学金申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjSqxx(GjzxjModel gjzxjModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveGjzxjSqxx(gjzxjModel, request);
	}
	
	/**
	 * 得到国家助学金申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjSqb(GjzxjModel gjzxjModel,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
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
		dao = new XszzZjjdDAO();
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
		dao = new XszzZjjdDAO();
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
		dao = new XszzZjjdDAO();
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
		dao = new XszzZjjdDAO();
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
		dao = new XszzZjjdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zjjd_gjzxjsqb",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zjjd_gjzxjsqb");
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
		dao = new XszzZjjdDAO();
		return dao.saveGjzxjShxx(gjzxjModel, request);
	}
	
	/**
	 * 获得国家助学金列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getGjzxjList() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjzxjList();
	}
	
	/**
	 * 获取国家励志奖学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjxx(String pkVal) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjlzjxjxx(pkVal);
	}
	
	/**
	 * 得到国家励志奖学金申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjlzjxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjlzjxjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * 保存国家励志奖学金申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxjSqxx(GjlzjxjModel gjlzjxjModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveGjlzjxjSqxx(gjlzjxjModel, request);
	}
	
	/**
	 * 得到国家励志奖学金申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjSqb(GjlzjxjModel gjlzjxjModel,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjlzjxjSqb(gjlzjxjModel,request);
	}
	
	/**
	 * 删除国家励志奖学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjlzjxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.delGjlzjxjxx(cbVal, request);
	}
	
	/**
	 * 批量修改国家励志奖学金审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjlzjxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.modGjlzjxjxx(cbVal, shjg, request);
	}
	
	/**
	 * 国家励志奖学金查询表头 gjlzjxjtit ---- 国家励志奖学金查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjlzjxjTit() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjlzjxjTit();
	}
	
	/**
	 * 国家励志奖学金查询结果 gjlzjxjres ---- 国家励志奖学金
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjlzjxjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjlzjxjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 国家励志奖学金导出 gjlzjxjExp ---- 国家励志奖学金导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getGjlzjxjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_zjjd_gjlzjxj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_zjjd_gjlzjxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存国家励志奖学金审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxjShxx(GjlzjxjModel gjlzjxjModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveGjlzjxjShxx(gjlzjxjModel, request);
	}
	
	/**
	 * 获取临时困难补助信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsbzxx(String pkVal) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getLsbzxx(pkVal);
	}
	
	/**
	 * 得到临时困难补助申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getLsbzSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getLsbzSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * 获得临时困难补助列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getLsbzList() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getLsbzList();
	}
	
	/**
	 * 保存临时困难补助申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsbzSqxx(LsbzModel lsbzModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveLsbzSqxx(lsbzModel, request);
	}
	
	/**
	 * 得到临时困难补助申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsbzSqb(LsbzModel lsbzModel,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getLsbzSqb(lsbzModel,request);
	}
	
	/**
	 * 删除临时困难补助信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delLsbzxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.delLsbzxx(cbVal, request);
	}
	
	/**
	 * 批量修改临时困难补助审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modLsbzxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.modLsbzxx(cbVal, shjg, request);
	}
	
	/**
	 * 临时困难补助查询表头 Lsbztit ---- 临时困难补助查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsbzTit() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getLsbzTit();
	}
	
	/**
	 * 临时困难补助查询结果 Lsbzres ---- 临时困难补助
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbzRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getLsbzRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 临时困难补助导出 LsbzExp ---- 临时困难补助导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getLsbzExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_zjjd_lsbz",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_zjjd_lsbz");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 临时困难补助审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsbzShxx(LsbzModel lsbzModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveLsbzShxx(lsbzModel, request);
	}
	
	/**
	 * 获取国家助学贷款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkxx(String pkVal) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjzxdkxx(pkVal);
	}
	
	/**
	 * 得到国家助学贷款申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjzxdkSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjzxdkSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * 保存国家助学贷款申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkSqxx(GjzxdkModel gjzxdkModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveGjzxdkSqxx(gjzxdkModel, request);
	}
	
	/**
	 * 得到国家助学贷款申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkSqb(GjzxdkModel gjzxdkModel,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjzxdkSqb(gjzxdkModel,request);
	}
	
	/**
	 * 删除国家助学贷款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxdkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.delGjzxdkxx(cbVal, request);
	}
	
	/**
	 * 批量修改国家助学贷款审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxdkxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.modGjzxdkxx(cbVal, shjg, request);
	}
	
	/**
	 * 国家助学贷款查询表头 gjzxdktit ---- 国家助学贷款查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkTit() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjzxdkTit();
	}
	
	/**
	 * 国家助学贷款查询结果 gjzxdkres ---- 国家助学贷款
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdkRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxdkRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 国家助学贷款导出 gjzxdkExp ---- 国家助学贷款导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getGjzxdkExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_zjjd_gjzxdk",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_zjjd_gjzxdk");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存国家助学贷款审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkShxx(GjzxdkModel gjzxdkModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveGjzxdkShxx(gjzxdkModel, request);
	}
	
	/**
	 * 获取学费减免信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfjmxx(String pkVal) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfjmxx(pkVal);
	}
	
	/**
	 * 得到学费减免申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getXfjmSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfjmSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * 获得学费减免列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getXfjmList() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfjmList();
	}
	
	/**
	 * 保存学费减免申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjmSqxx(XfjmModel xfjmModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveXfjmSqxx(xfjmModel, request);
	}
	
	/**
	 * 得到学费减免申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfjmSqb(XfjmModel xfjmModel,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfjmSqb(xfjmModel,request);
	}
	
	/**
	 * 删除学费减免信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXfjmxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.delXfjmxx(cbVal, request);
	}
	
	/**
	 * 批量修改学费减免审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXfjmxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.modXfjmxx(cbVal, shjg, request);
	}
	
	/**
	 * 学费减免查询表头 Xfjmtit ---- 学费减免查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfjmTit() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfjmTit();
	}
	
	/**
	 * 学费减免查询结果 Xfjmres ---- 学费减免
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjmRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfjmRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 学费减免导出 XfjmExp ---- 学费减免导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getXfjmExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_zjjd_xfjm",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_zjjd_xfjm");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 学费减免审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjmShxx(XfjmModel xfjmModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveXfjmShxx(xfjmModel, request);
	}
	
	/**
	 * 获取学费缓缴信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhjxx(String pkVal) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfhjxx(pkVal);
	}
	
	/**
	 * 得到学费缓缴申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getXfhjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfhjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * 保存学费缓缴申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhjSqxx(XfhjModel saveXfhjModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveXfhjSqxx(saveXfhjModel, request);
	}
	
	/**
	 * 得到学费缓缴申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhjSqb(XfhjModel xfhjModel,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfhjSqb(xfhjModel,request);
	}
	
	/**
	 * 删除学费缓缴信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXfhjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.delXfhjxx(cbVal, request);
	}
	
	/**
	 * 批量修改学费缓缴审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXfhjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.modXfhjxx(cbVal, shjg, request);
	}
	
	/**
	 * 学费缓缴查询表头 xfhjtit ---- 学费缓缴查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfhjTit() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfhjTit();
	}
	
	/**
	 * 学费缓缴查询结果 xfhjres ---- 学费缓缴
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfhjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 学费缓缴导出 xfhjExp ---- 学费缓缴导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getXfhjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_zjjd_xfhj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_zjjd_xfhj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存学费缓缴审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhjShxx(XfhjModel saveXfhjModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveXfhjShxx(saveXfhjModel, request);
	}
	
	/**
	 * 删除放款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delFkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.delFkxx(cbVal, request);
	}
	
	/**
	 *放款信息查询表头 fkxxtit ---- 放款信息表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFkxxTit() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getFkxxTit();
	}
	
	/**
	 * 放款信息查询结果 fkxxres ---- 放款信息结果
	 * 
	 * @param queryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getFkxxRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getFkxxRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 获取放款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getFkxx(String pkVal) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getFkxx(pkVal);
	}
	
	/**
	 * 保存放款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveFkxx(FkxxModel fkxxModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveFkxx(fkxxModel, request);
	}
	
	/**
	 * 放款信息导出 fkxxExp ---- 放款信息导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getFkxxExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zjjd_zxdk_fkxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zjjd_zxdk_fkxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
}
