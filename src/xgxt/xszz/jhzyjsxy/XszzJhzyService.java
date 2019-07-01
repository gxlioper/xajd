package xgxt.xszz.jhzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.base.Excel2Oracle;

/**
 * Title: 学生工作管理系统
 * Description: 金华职业技术学院学生资助Service
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: 周觅
 * Version: 1.0
 * Time: 2009-09-25
 */
public class XszzJhzyService {

	XszzJhzyDAO dao = null;// 数据操作DAO

	/**
	 * 获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getStu(xh);
	}

	/**
	 * 获取困难生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsxx(String pkVal) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getKnsxx(pkVal);
	}

	/**
	 * 得到困难生申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getKnsSqQx(String zzType, String sUserType, String userDep,
			String xh, String nd) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getKnsSqQx(zzType, sUserType, userDep, xh, nd);
	}

	/**
	 * 保存困难生申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsSqxx(KnssqbModel saveKnsModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.saveKnsSqxx(saveKnsModel, request);
	}

	/**
	 * 得到困难生申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsSqb(KnssqbModel knsModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getKnsSqb(knsModel, request);
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
		dao = new XszzJhzyDAO();
		return dao.getKnsTit();
	}

	/**
	 * 困难生查询结果 knsres ---- 困难生
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsRes(QueryModel queryModel,
			HttpServletRequest request, XszzJhzyjsxyActionForm actionForm)
			throws Exception {
		dao = new XszzJhzyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsRes(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * 困难生信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnsResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * 删除困难生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsxx(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		dao.delKnsxx(cbVal, request);
	}

	/**
	 * 批量修改困难生审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsxx(String[] cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		dao.modKnsxx(cbVal, shjg, request);
	}

	/**
	 * 困难生导出 knsExp ---- 困难生导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getKnsExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_jhzy_kns", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_jhzy_kns");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存困难生审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsShxx(KnssqbModel saveKnsModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.saveKnsShxx(saveKnsModel, request);
	}

	/**
	 * 获取困难生登记表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsdjbxx(String pkVal) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getKnsdjbxx(pkVal);
	}

	/**
	 * 保存困难生登记表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsdjbxx(KnsdjbModel saveKnsModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.saveKnsdjbxx(saveKnsModel, request);
	}

	/**
	 * 得到困难生登记表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsdjbdy(KnsdjbModel knsModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getKnsdjbdy(knsModel, request);
	}

	/**
	 * 删除困难生登记表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsdjb(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		dao.delKnsdjb(cbVal, request);
	}

	/**
	 * 困难生登记表查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsdjbTit() throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getKnsdjbTit();
	}

	/**
	 * 困难生登记表
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsdjbRes(QueryModel queryModel,
			HttpServletRequest request, XszzJhzyjsxyActionForm actionForm)
			throws Exception {
		dao = new XszzJhzyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsdjbRes(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * 困难生登记表查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsdjbResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnsdjbResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * 困难生登记表导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getKnsdjbExp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xg_zz_knsdjb", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xg_zz_knsdjb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 获取国家助学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjxx(String pkVal) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getGjzxjxx(pkVal);
	}

	/**
	 * 保存国家助学金申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjSqxx(GjzxjModel model, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		return dao.saveGjzxjSqxx(model, request);
	}

	/**
	 * 得到国家助学金申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjSqb(GjzxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getGjzxjSqb(model, request);
	}

	/**
	 * 删除国家助学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxj(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		dao.delGjzxj(cbVal, request);
	}

	/**
	 * 批量修改国家助学金审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxj(String[] cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		dao.modGjzxj(cbVal, shjg, request);
	}

	/**
	 * 国家助学金查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxjTit() throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getGjzxjTit();
	}

	/**
	 * 国家助学金查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxjRes(QueryModel queryModel,
			HttpServletRequest request,
			XszzJhzyjsxyActionForm jhzyjsxyActionForm) throws Exception {
		dao = new XszzJhzyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxjRes(queryModel, request, jhzyjsxyActionForm);
		}
		return resList;
	}

	/**
	 * 国家助学金查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjzxjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjzxjResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * 国家助学金导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getGjzxjExp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_jhzy_gjzxj", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_jhzy_gjzxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存国家助学金审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjShxx(GjzxjModel model, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		return dao.saveGjzxjShxx(model, request);
	}

	/**
	 * 得到国家助学金列表
	 * 
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getGjzxjList() throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getGjzxjList();
	}

	/**
	 * 得到帮困助学基金申请权限
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getBkzxjjSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getBkzxjjSqQx(sUserType, userDep, xh, nd);
	}

	/**
	 * 是否困难生
	 * 
	 * @throws Exception
	 */
	public boolean isKns(String xh) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.isKns(xh);
	}

	/**
	 * 有不及格科目
	 * 
	 * @throws Exception
	 */
	public boolean isBjgkm(String nd, String xh) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.isBjgkm(nd, xh);
	}

	/**
	 * 国家励志奖学金
	 * 
	 * @throws Exception
	 */
	public void serv_saveGjlzjxjSq(XszzGjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		dao.saveGjlzjxjSq(model, request);
	}

	/**
	 * 获取国家励志奖学金相关信息
	 */
	public HashMap<String, String> serv_getGjlzJxjInfo(String pkVlaue) {
		dao = new XszzJhzyDAO();
		String querry = " where nd||xh='" + pkVlaue + "' ";
		return dao.dao_getInfo("xszz_jhzy_gjlzjxj", querry);
	}

	/**
	 * 国家励志奖学金
	 */
	public List<HashMap<String, String>> ser_getGjlzJxjTit() throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getGjlzJxjTit();
	}

	/**
	 * 国家励志奖学金查询结果
	 */
	public List<String[]> serv_getGjlzJxjRes(QueryModel queryModel,
			HttpServletRequest request,
			XszzJhzyjsxyActionForm jhzyjsxyActionForm) throws Exception {
		dao = new XszzJhzyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao
					.getGjlzJxjRes(queryModel, request, jhzyjsxyActionForm);
		}
		return resList;
	}

	/**
	 * 国家励志奖学金查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjlzJxjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjlzJxjResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * 得到国家励志奖学金申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjSqb(XszzGjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getGjlzjxjSqb(model, request);
	}

	/**
	 * 批量修改 国家励志奖学金审核结果
	 */
	public void serv_modGjlzJxjsxx(String[] cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		dao.modGjlzjxjxx(cbVal, shjg, request);
	}

	/**
	 * 删除困难生信息
	 */
	public void serv_delGjlzjxjxx(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		dao.delGjlzjxjxx(cbVal, request);
	}

	/**
	 * 国家励志奖学金审核
	 * 
	 * @throws Exception
	 */
	public boolean serv_saveGjlzjxjSh(XszzGjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.saveGjlzjxjSh(model, request);
	}

	/**
	 * 国家励志奖学金申请表打印
	 */
	public HashMap<String, String> serv_getGjlzJxjb(XszzGjlzjxjModel model) {
		dao = new XszzJhzyDAO();
		return dao.getGjlzJxjb(model);
	}

	/**
	 * 资助等级列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZzdjList() throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getZzdjList();
	}

	/**
	 * 保存帮困助学基金申请信息，成功返回TRUE，反之返回FALSE
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveBkzxjjSqxx(BkzxjjModel model, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		return dao.saveBkzxjjSqxx(model, request);
	}

	/**
	 * 获取帮困助学基金详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBkzxjjXx(String pkVal) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getBkzxjjXx(pkVal);
	}

	/**
	 * 帮困助学基金查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBkzzjjRes(QueryModel queryModel,
			HttpServletRequest request,
			XszzJhzyjsxyActionForm jhzyjsxyActionForm) throws Exception {
		dao = new XszzJhzyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getBkzzjjRes(queryModel, request, jhzyjsxyActionForm);
		}
		return resList;
	}

	/**
	 * 帮困助学基金查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getBkzzjjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getBkzzjjResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * 批量修改帮困助学审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modBkzxjjxx(String[] cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		dao.modBkzxjjxx(cbVal, shjg, request);
	}

	/**
	 * 删除帮困助学信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delBkzxjj(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		dao.delBkzxjj(cbVal, request);
	}

	/**
	 * 保存困难生审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveBkzxjjShxx(BkzxjjModel model, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		return dao.saveBkzxjjShxx(model, request);
	}

	public String getZxjjXx(String zxjjdm) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getZxjjXx(zxjjdm);
	}
}
