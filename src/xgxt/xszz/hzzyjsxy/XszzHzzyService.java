package xgxt.xszz.hzzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.base.Excel2Oracle;

/**
 * Title: 学生工作管理系统
 * Description: 杭州职业技术学院学生资助Service
 * Copyright: Copyright (c) 2008
 * Company: zfsoft
 * Author: 周觅
 * Version: 1.0
 * Time: 2008-07-08
 */
public class XszzHzzyService {

	XszzHzzyDAO dao = null;// 数据操作DAO

	/**
	 * 在校学生贷款查询表头 zxxsdktit ---- 在校学生贷款信息表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZxxsDkxxTit() throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getZxxsDkxxTit();
	}

	/**
	 * 在校学生贷款查询结果 zxxsdkres ---- 在校学生贷款结果
	 * 
	 * @param queryZxxsdkModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getShJxjRes(QueryModel queryZxxsdkModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzHzzyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryZxxsdkModel.getGo())) {
			resList = dao.getZxxsDkxxRes(queryZxxsdkModel,request);
		}
		return resList;
	}

	/**
	 * 获取在校学生贷款详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getDkxx(String pkVal) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getDkxx(pkVal);
	}

	/**
	 * 获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getStu(xh);
	}

	/**
	 * 保存贷款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveDkxx(SaveZxxsdkModel saveZxxsdkModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.saveDkxx(saveZxxsdkModel, request);
	}

	/**
	 * 删除贷款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delDkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzHzzyDAO();
		dao.delDkxx(cbVal, request);
	}

	/**
	 * 在校学生贷款导出 zxxsdkExp ---- 在校学生贷款导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getZxxsDkxxExp(QueryModel queryZxxsdkModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzHzzyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryZxxsdkModel, "view_xszz_hzzyjsxy_zxxsdkxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_hzzyjsxy_zxxsdkxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 获取困难生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsxx(String pkVal) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getKnsxx(pkVal);
	}
	
	/**
	 * 得到困难生申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getKnsSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzHzzyDAO();
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
		dao = new XszzHzzyDAO();
		return dao.saveKnsSqxx(saveKnsModel, request);
	}
	
	/**
	 * 得到困难生申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsSqb(KnsModel knsModel,HttpServletRequest request) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getKnsSqb(knsModel,request);
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
		dao = new XszzHzzyDAO();
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
		dao = new XszzHzzyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 删除困难生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzHzzyDAO();
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
		dao = new XszzHzzyDAO();
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
	public void getKnsExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzHzzyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_hzzy_knssqb",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_hzzy_knssqb");
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
		dao = new XszzHzzyDAO();
		return dao.saveKnsShxx(saveKnsModel, request);
	}
	
	/**
	 * 获取国家奖学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjjxjxx(String pkVal) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getGjjxjxx(pkVal);
	}
	
	/**
	 * 得到国家奖学金申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjjxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getGjjxjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * 保存国家奖学金申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjjxjSqxx(GjjxjModel gjjxjModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.saveGjjxjSqxx(gjjxjModel, request);
	}
	
	/**
	 * 得到国家奖学金申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjjxjSqb(GjjxjModel gjjxjModel,HttpServletRequest request) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getGjjxjSqb(gjjxjModel,request);
	}
	
	/**
	 * 删除国家奖学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjjxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzHzzyDAO();
		dao.delGjjxjxx(cbVal, request);
	}
	
	/**
	 * 批量修改国家奖学金审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjjxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzHzzyDAO();
		dao.modGjjxjxx(cbVal, shjg, request);
	}
	
	/**
	 * 国家奖学金查询表头 gjjxjtit ---- 国家奖学金查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjjxjTit() throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getGjjxjTit();
	}
	
	/**
	 * 国家奖学金查询结果 gjjxjres ---- 国家奖学金
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjjxjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzHzzyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjjxjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 国家奖学金导出 gjjxjExp ---- 国家奖学金导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getGjjxjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzHzzyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_hzzy_xszz_gjjxj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_hzzy_xszz_gjjxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存国家奖学金审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjjxjShxx(GjjxjModel gjjxjModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.saveGjjxjShxx(gjjxjModel, request);
	}
	
	/**
	 * 获取国家助学金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjxx(String pkVal) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getGjzxjxx(pkVal);
	}
	
	/**
	 * 得到国家助学金申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjzxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzHzzyDAO();
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
		dao = new XszzHzzyDAO();
		return dao.saveGjzxjSqxx(gjzxjModel, request);
	}
	
	/**
	 * 得到国家助学金申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjSqb(GjzxjModel gjzxjModel,HttpServletRequest request) throws Exception {
		dao = new XszzHzzyDAO();
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
		dao = new XszzHzzyDAO();
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
		dao = new XszzHzzyDAO();
		dao.modGjzxjxx(cbVal, shjg, request);
	}
	
	/**
	 * 国家助学金查询表头 gjjxjtit ---- 国家助学金查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxjTit() throws Exception {
		dao = new XszzHzzyDAO();
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
		dao = new XszzHzzyDAO();
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
		dao = new XszzHzzyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_hzzy_xszz_gjzxj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_hzzy_xszz_gjzxj");
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
		dao = new XszzHzzyDAO();
		return dao.saveGjzxjShxx(gjzxjModel, request);
	}
}
