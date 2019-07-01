package xgxt.xszz.nblg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.base.Excel2Oracle;
import xgxt.szdw.utils.MakeQuery;
import xgxt.utils.ExcelMethods;

/**
 * Title: 学生工作管理系统
 * Description: 宁波理工学生资助Service
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: 周觅
 * Version: 1.0
 * Time: 2009-01-13
 */
public class XszzNblgService {

	XszzNblgDAO dao = null;// 数据操作DAO

	/**
	 * 获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * 获取困难生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsxx(String pkVal) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getKnsxx(pkVal);
	}
	
	/**
	 * 得到困难生申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getKnsSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getKnsSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * 保存困难生申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsSqxx(KnsModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveKnsSqxx(model, request);
	}
	
	/**
	 * 得到困难定申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsSqb(KnsModel model,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getKnsSqb(model,request);
	}
	
	/**
	 * 删除困难生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.delKnsxx(cbVal, request);
	}
	
	/**
	 * 困难生查询表头 Knstit ----困难生查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsTit() throws Exception {
		dao = new XszzNblgDAO();
		return dao.getKnsTit();
	}
	
	/**
	 * 困难生查询结果 Knsres ---- 困难生
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 批量修改困难生审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsxx(String cbVal, String shType, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.modKnsxx(cbVal, shType, shjg, request);
	}
	
	/**
	 * 困难生导出 KnsExp ---- 困难生导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getKnsExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nblg_kns",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nblg_kns");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存困难生审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsShxx(KnsModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveKnsShxx(model, request);
	}
	
	/**
	 * 获取临时困难补助信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsknbzxx(String pkVal) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getLsknbzxx(pkVal);
	}
	
	/**
	 * 得到临时困难补助申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getLsknbzSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getLsknbzSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * 保存临时困难补助申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsknbzSqxx(LsknbzModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveLsknbzSqxx(model, request);
	}
	
	/**
	 * 得到临时困难补助申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsknbzSqb(LsknbzModel model,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getLsknbzSqb(model,request);
	}
	
	/**
	 * 删除临时困难补助信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delLsknbzxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.delLsknbzxx(cbVal, request);
	}
	
	/**
	 * 批量修改临时困难补助审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modLsknbzxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.modLsknbzxx(cbVal, shjg, request);
	}
	
	/**
	 * 临时困难补助查询表头 Lsknbztit ----临时困难补助查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsknbzTit() throws Exception {
		dao = new XszzNblgDAO();
		return dao.getLsknbzTit();
	}
	
	/**
	 * 临时困难补助查询结果 Lsknbzres ---- 临时困难补助
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsknbzRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getLsknbzRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 临时困难补助导出 LsknbzExp ---- 临时困难补助导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getLsknbzExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nblg_lsknbz",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nblg_lsknbz");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存临时困难补助审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsknbzShxx(LsknbzModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveLsknbzShxx(model, request);
	}
	
	/**
	 * 获取助学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxjxx(String pkVal) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getZxjxx(pkVal);
	}
	
	/**
	 * 得到助学金申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getZxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getZxjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * 保存助学金申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxjSqxx(ZxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveZxjSqxx(model, request);
	}
	
	/**
	 * 得到助学金申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxjSqb(ZxjModel model,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getZxjSqb(model,request);
	}
	
	/**
	 * 删除助学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delZxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.delZxjxx(cbVal, request);
	}
	
	/**
	 * 批量修改助学金审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modZxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.modZxjxx(cbVal, shjg, request);
	}
	
	/**
	 * 助学金查询表头 Zxjtit ----助学金查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZxjTit() throws Exception {
		dao = new XszzNblgDAO();
		return dao.getZxjTit();
	}
	
	/**
	 * 助学金查询结果 Zxjres ---- 助学金
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZxjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getZxjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 助学金导出 ZxjExp ---- 助学金导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getZxjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nblg_zxj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nblg_zxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存助学金审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxjShxx(ZxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveZxjShxx(model, request);
	}
	
	/**
	 * 获取国家助学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjxx(String pkVal) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjzxjxx(pkVal);
	}
	
	/**
	 * 得到国家助学金申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjzxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjzxjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * 保存国家助学金申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjSqxx(GjzxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveGjzxjSqxx(model, request);
	}
	
	/**
	 * 得到国家助学金申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjSqb(GjzxjModel model,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjzxjSqb(model,request);
	}
	
	/**
	 * 删除国家助学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
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
		dao = new XszzNblgDAO();
		dao.modGjzxjxx(cbVal, shjg, request);
	}
	
	/**
	 * 国家助学金查询表头 Gjzxjtit ----国家助学金查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxjTit() throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjzxjTit();
	}
	
	/**
	 * 国家助学金查询结果 Gjzxjres ---- 国家助学金
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 国家助学金导出 GjzxjExp ---- 国家助学金导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getGjzxjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nblg_gjzxj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nblg_gjzxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存国家助学金审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjShxx(GjzxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveGjzxjShxx(model, request);
	}
	
	/**
	 * 获取国家励志奖学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjxx(String pkVal) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjlzjxjxx(pkVal);
	}
	
	/**
	 * 得到国家励志奖学金申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjlzjxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjlzjxjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * 保存国家励志奖学金申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxjSqxx(GjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveGjlzjxjSqxx(model, request);
	}
	
	/**
	 * 得到国家励志奖学金申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjSqb(GjlzjxjModel model,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjlzjxjSqb(model,request);
	}
	
	/**
	 * 删除国家励志奖学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjlzjxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
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
		dao = new XszzNblgDAO();
		dao.modGjlzjxjxx(cbVal, shjg, request);
	}
	
	/**
	 * 国家励志奖学金查询表头 Gjlzjxjtit ----国家励志奖学金查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjlzjxjTit() throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjlzjxjTit();
	}
	
	/**
	 * 国家励志奖学金查询结果 Gjlzjxjres ---- 国家励志奖学金
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjlzjxjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjlzjxjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 国家励志奖学金导出 GjlzjxjExp ---- 国家励志奖学金导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getGjlzjxjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nblg_gjlzjxj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nblg_gjlzjxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存国家励志奖学金审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxjShxx(GjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveGjlzjxjShxx(model, request);
	}

	/**
	 * 获取彩虹慈善助学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getChzxjxx(String pkVal) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getChzxjxx(pkVal);
	}
	
	/**
	 * 得到彩虹慈善助学金申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getChzxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getChzxjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * 保存彩虹慈善助学金申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveChzxjSqxx(ChzxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveChzxjSqxx(model, request);
	}
	
	/**
	 * 得到彩虹慈善助学金申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getChzxjSqb(ChzxjModel model,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getChzxjSqb(model,request);
	}
	
	/**
	 * 删除彩虹慈善助学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delChzxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.delChzxjxx(cbVal, request);
	}
	
	/**
	 * 批量修改彩虹慈善助学金审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modChzxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.modChzxjxx(cbVal, shjg, request);
	}
	
	/**
	 * 彩虹慈善助学金查询表头 Chzxjtit ----彩虹慈善助学金查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChzxjTit() throws Exception {
		dao = new XszzNblgDAO();
		return dao.getChzxjTit();
	}
	
	/**
	 * 彩虹慈善助学金查询结果 Chzxjres ---- 彩虹慈善助学金
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getChzxjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getChzxjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 彩虹慈善助学金导出 ChzxjExp ---- 彩虹慈善助学金导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getChzxjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nblg_chzxj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nblg_chzxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 彩虹慈善助学金打印报表  printChcszxj
	 * author 裘力俊
	 *data 2010-07-15
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public void printChcszxj(QueryModel queryModel,HttpServletRequest request,
			WritableWorkbook wwb) throws IllegalArgumentException, SecurityException, 
			IllegalAccessException, InvocationTargetException, NoSuchMethodException{	
		
		dao = new XszzNblgDAO();
		String[]colList={"xn","xh","xysh","xydm","xxsh","nj","zydm","bjdm"};
		StringBuffer strBuffer=MakeQuery.makeQuery(colList,queryModel);
		
		List<Map<String,String>>list=dao.getChcszxj(strBuffer);
		
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(24);
		 wcfTytle.setFont(wfTytle);
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
//		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 for(int i=0; i<list.size(); i++){
			 Map<String, String> map  =  list.get(i);
			 ws.addCell(new Label(0,3+i,String.valueOf(i+1),wcfTytle));
			 ws.addCell(new Label(1,3+i,map.get("xm"),wcfTytle));
			 ws.addCell(new Label(2,3+i,map.get("xb"),wcfTytle));
			 ws.addCell(new Label(3,3+i,map.get("nl"),wcfTytle));
			 ws.addCell(new Label(4,3+i,map.get("jtzz"),wcfTytle));
			 ws.addCell(new Label(5,3+i,map.get("sqly"),wcfTytle));
			 ws.addCell(new Label(6,3+i,map.get("xymc")+map.get("nj")+map.get("zymc"),wcfTytle));
			 ws.addCell(new Label(7,3+i,"",wcfTytle));
		 }
		 ws.mergeCells(0, list.size()+3+1, 7, list.size()+3+1);
		 
		 WritableFont wfTytlexx = new WritableFont(WritableFont.ARIAL);
		 WritableCellFormat wcfTytlexx = new WritableCellFormat();
		 wfTytlexx.setBoldStyle(WritableFont.NO_BOLD);
		 wcfTytlexx.setBorder(Border.NONE, BorderLineStyle.THIN);
		 wcfTytlexx.setFont(wfTytlexx);
		 wcfTytlexx.setVerticalAlignment(vag);
		 wcfTytlexx.setAlignment(alignMent);
		 
		 ws.addCell(new Label(0,list.size()+3+1,"　推荐单位：宁波天一职业技术学院　　　",wcfTytlexx));
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 保存彩虹慈善助学金审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveChzxjShxx(ChzxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveChzxjShxx(model, request);
	}
	
	/**
	 * 获取慈善总会高校在校生助学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxszxjxx(String pkVal) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getZxszxjxx(pkVal);
	}
	
	/**
	 * 得到慈善总会高校在校生助学金申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getZxszxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getZxszxjSqQx(sUserType,userDep,xh,nd);
	}
	
	
	
	/**
	 * 慈善总会助学金打印报表  printCszhzxj
	 * author 裘力俊
	 *data 2010-07-15
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public void printCszhzxj(QueryModel queryModel,HttpServletRequest request,WritableWorkbook wwb) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{	
		
		dao = new XszzNblgDAO();
		String[]colList={"xn","xh","xysh","xydm","xxsh","nj","zydm","bjdm"};
		StringBuffer strBuffer=MakeQuery.makeQuery(colList,queryModel);
		List<Map<String,String>>list=dao.getCszhzxj(strBuffer);
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(24);
		 wcfTytle.setFont(wfTytle);
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 for(int i=0; i<list.size(); i++){
			 Map<String, String> map  =  list.get(i);
			 ws.addCell(new Label(0,3+i,String.valueOf(i+1),wcfTytle));
			 ws.addCell(new Label(1,3+i,map.get("xm"),wcfTytle));
			 ws.addCell(new Label(2,3+i,map.get("xb"),wcfTytle));
			 ws.addCell(new Label(3,3+i,map.get("nl"),wcfTytle));
			 ws.addCell(new Label(4,3+i,map.get("jtzz"),wcfTytle));
			 ws.addCell(new Label(5,3+i,map.get("xymc")+map.get("nj")+map.get("zymc"),wcfTytle));
			 ws.addCell(new Label(6,3+i,map.get("sqly"),wcfTytle));
			 ws.addCell(new Label(7,3+i,map.get("zxjje"),wcfTytle));
			 ws.addCell(new Label(8,3+i,map.get("sfzh"),wcfTytle));
		 }
		
		 WritableFont wfTytlexx = new WritableFont(WritableFont.ARIAL);
		 WritableCellFormat wcfTytlexx = new WritableCellFormat();
		 wfTytlexx.setBoldStyle(WritableFont.NO_BOLD);
		 wcfTytlexx.setBorder(Border.NONE, BorderLineStyle.THIN);
		 wcfTytlexx.setFont(wfTytlexx);
		 wcfTytlexx.setVerticalAlignment(vag);
		 wcfTytlexx.setAlignment(alignMent);
		 
		 ws.mergeCells(0, list.size()+3+1, 8, list.size()+3+1);
		 ws.addCell(new Label(0,list.size()+3+1,"　　填报单位：宁波天一职业技术学院　　",wcfTytlexx));
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * 保存慈善总会高校在校生助学金申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxszxjSqxx(ZxszxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveZxszxjSqxx(model, request);
	}
	
	/**
	 * 得到慈善总会高校在校生助学金申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxszxjSqb(ZxszxjModel model,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getZxszxjSqb(model,request);
	}
	
	/**
	 * 删除慈善总会高校在校生助学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delZxszxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.delZxszxjxx(cbVal, request);
	}
	
	/**
	 * 批量修改慈善总会高校在校生助学金审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modZxszxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.modZxszxjxx(cbVal, shjg, request);
	}
	
	/**
	 * 慈善总会高校在校生助学金查询表头 Zxszxjtit ----慈善总会高校在校生助学金查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZxszxjTit() throws Exception {
		dao = new XszzNblgDAO();
		return dao.getZxszxjTit();
	}
	
	/**
	 * 慈善总会高校在校生助学金查询结果 Zxszxjres ---- 慈善总会高校在校生助学金
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZxszxjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getZxszxjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 慈善总会高校在校生助学金导出 ZxszxjExp ---- 慈善总会高校在校生助学金导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getZxszxjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nblg_zxszxj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nblg_zxszxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存慈善总会高校在校生助学金审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxszxjShxx(ZxszxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveZxszxjShxx(model, request);
	}
	
	/**
	 * 获取国家助学贷款金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkxx(String pkVal) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjzxdkxx(pkVal);
	}
	
	/**
	 * 得到国家助学贷款金申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjzxdkSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjzxdkSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * 保存国家助学贷款金申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkSqxx(GjzxdkModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveGjzxdkSqxx(model, request);
	}
	
	/**
	 * 得到国家助学贷款金申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkSqb(GjzxdkModel model,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjzxdkSqb(model,request);
	}
	
	/**
	 * 删除国家助学贷款金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxdkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.delGjzxdkxx(cbVal, request);
	}
	
	/**
	 * 批量修改国家助学贷款金审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxdkxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.modGjzxdkxx(cbVal, shjg, request);
	}
	
	/**
	 * 国家助学贷款金查询表头 Gjzxdktit ----国家助学贷款金查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkTit() throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjzxdkTit();
	}
	
	/**
	 * 国家助学贷款金查询结果 Gjzxdkres ---- 国家助学贷款金
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdkRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxdkRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 国家助学贷款金导出 GjzxdkExp ---- 国家助学贷款金导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getGjzxdkExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nblg_gjzxdk",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nblg_gjzxdk");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存国家助学贷款金审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkShxx(GjzxdkModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveGjzxdkShxx(model, request);
	}
}
