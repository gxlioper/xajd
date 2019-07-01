package xgxt.xszz.zgdzdx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.utils.CommonQueryDAO;
import xgxt.xszz.zgmsxy.XszzZgmsxyActionForm;
import xgxt.xszz.zgmsxy.XszzZgmsxyDAO;
import xgxt.form.User;

/**
 * Title: 学生工作管理系统
 * Description:中国地质大学学生资助Service
 * Copyright: Copyright (c) 2008
 * Company: zfsoft
 * Author: 周觅
 * Version: 1.0
 * Time: 2008-10-09
 */
public class XszzZgdzdxService {

	XszzZgdzdxDAO dao = null;// 数据操作DAO

	/**
	 * 获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * 获取家庭情况调查信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJtqkdcxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getJtqkdcxx(pkVal);
	}
	
	/**
	 * 得到家庭情况调查申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getJtqkdcSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getJtqkdcSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * 保存家庭情况调查申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveJtqkdcSqxx(JtqkdcModel saveJtqkdcModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveJtqkdcSqxx(saveJtqkdcModel, request);
	}
	
	/**
	 * 得到家庭情况调查申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJtqkdcSqb(JtqkdcModel model,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getJtqkdcSqb(model,request);
	}
	
	/**
	 * 删除家庭情况调查信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delJtqkdcxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delJtqkdcxx(cbVal, request);
	}
	
	/**
	 * 批量修改家庭情况调查审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modJtqkdcxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.modJtqkdcxx(cbVal, shjg, request);
	}
	
	/**
	 * 家庭情况调查查询表头 Jtqkdctit ---- 家庭情况调查查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJtqkdcTit() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getJtqkdcTit();
	}
	
	/**
	 * 家庭情况调查查询结果 Jtqkdcres ---- 家庭情况调查
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJtqkdcRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getJtqkdcRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 家庭情况调查导出 JtqkdcExp ---- 家庭情况调查导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getJtqkdcExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgdzdx_kns_jtqkdc",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgdzdx_kns_jtqkdc");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存家庭情况调查审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveJtqkdcShxx(JtqkdcModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveJtqkdcShxx(model, request);
	}
	
	/**
	 * 获取困难生认定信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrdxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getKnsrdxx(pkVal);
	}
	
	/**
	 * 得到困难生认定申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getKnsrdSqQx(String sUserType,String userDep,String xh,String xn) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getKnsrdSqQx(sUserType,userDep,xh,xn);
	}
	
	/**
	 * 得到困难生认定审核规定时间
	 * 
	 * @return String[]
	 * @throws Exception
	 */
	public String[] getKnsrdShsj(String userDep) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getKnsrdShsj(userDep);
	}
	
	/**
	 * 得到困难生认定审核权限
	 * 
	 * @return 
	 * @throws Exception
	 */
	public String getKnsrdShQx(String userType,String userDep) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getKnsrdShQx(userType,userDep);
	}
	
	/**
	 * 保存困难生认定申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrdSqxx(KnsrdModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveKnsrdSqxx(model, request);
	}
	
	/**
	 * 得到困难生认定申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrdSqb(KnsrdModel model,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getKnsrdSqb(model,request);
	}
	
	/**
	 * 删除困难生认定信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsrdxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delKnsrdxx(cbVal, request);
	}
	
	/**
	 * 删除展期协议信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delZqxyxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delZqxyxx(cbVal, request);
	}
	
	/**
	 * 将信息转到历史信息库
	 * 
	 * @return
	 * @throws Exception
	 */
	public void addGjzxdkLsxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.addGjzxdkLsxx(cbVal, request);
	}
	
	/**
	 * 删除还款协议信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delHkxyxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delHkxyxx(cbVal, request);
	}
	
	/**
	 * 删除展期后还款协议信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delZqhhkxyxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delZqhhkxyxx(cbVal, request);
	}
	
	/**
	 * 批量修改困难生认定审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsrdxx(String cbVal, String shType, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.modKnsrdxx(cbVal, shType, shjg, request);
	}
	
	/**
	 * 批量修改展期协议审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modZqxyxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.modZqxyxx(cbVal, shjg, request);
	}
	
	/**
	 * 批量修改还款协议审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modHkxyxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.modHkxyxx(cbVal, shjg, request);
	}
	
	/**
	 * 批量修改展期后还款协议审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modZqhhkxyxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.modZqhhkxyxx(cbVal, shjg, request);
	}
	
	/**
	 * 困难生认定查询表头 Knsrdtit ----困难生认定查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrdTit() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getKnsrdTit();
	}
	
	/**
	 * 展期协议查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZqxyTit() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZqxyTit();
	}
	
	/**
	 * 还款协议查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getHkxyTit() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getHkxyTit();
	}
	
	/**
	 * 展期后还款协议查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZqhhkxyTit() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZqhhkxyTit();
	}
	
	/**
	 * 困难生认定查询结果 Knsrdres ---- 困难生认定
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrdRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrdRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 展期协议查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZqxyRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getZqxyRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 还款协议查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getHkxyRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getHkxyRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 展期后还款协议查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZqhhkxyRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getZqhhkxyRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 困难生认定导出 KnsrdExp ---- 困难生认定导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getKnsrdExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgdzdx_kns_pksrd",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgdzdx_kns_pksrd");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 展期协议信息导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getZqxyExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgdzdx_zqxy",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgdzdx_zqxy");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 还款协议信息导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getHkxyExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();
		queryModel.setXz("1");

		rs = dao.getExpDate(queryModel, "view_zgdzdx_hkjzqhhkxyxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgdzdx_hkjzqhhkxyxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 展期后还款协议信息导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getZqhhkxyExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();
		queryModel.setXz("2");

		rs = dao.getExpDate(queryModel, "view_zgdzdx_hkjzqhhkxyxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgdzdx_hkjzqhhkxyxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存困难生认定审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrdShxx(KnsrdModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveKnsrdShxx(model, request);
	}
	
	/**
	 * 得到已有合同编号说明
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getHtbh(String xydm) throws Exception {
		dao = new XszzZgdzdxDAO();
		StringBuffer sb = new StringBuffer("");
		String zdhth = "0001";
		
		ArrayList<HashMap<String, String>> list = dao.getHtbh(xydm);

		boolean b = true;
		for (HashMap<String, String> hp : list){
			if (b) {
				sb.append(hp.get("zxhth"));
				sb.append("-");
				b = false;
			} else {
				if (Integer.parseInt(hp.get("zxhth")) != (Integer.parseInt(zdhth)+1)){
					sb.append(zdhth);
					sb.append(",");
					sb.append(hp.get("zxhth"));
					sb.append("-");
				}
			}
			zdhth = hp.get("zdhth");
		}
		sb.append(zdhth);
		return sb.toString();
	}
	
	/**
	 * 得到已有合同编号列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getHtbhList(String xydm) throws Exception {
		dao = new XszzZgdzdxDAO();
		StringBuffer sb = new StringBuffer("!!OneSpile!!");
		
		ArrayList<HashMap<String, String>> list = dao.getHtbh(xydm);
		
		for (HashMap<String, String> hp : list){
			for (int i = Integer.parseInt(hp.get("zxhth")); i <= Integer.parseInt(hp.get("zdhth")); i++){
				sb.append(i);
				sb.append("!!OneSpile!!");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 得到审核结果列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getshList() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		HashMap<String, String> tHA = new HashMap<String, String>();
		tHA.put("shmc", "一般困难");
		HashMap<String, String> tHB = new HashMap<String, String>();
		tHB.put("shmc", "困难");
		HashMap<String, String> tHC = new HashMap<String, String>();
		tHC.put("shmc", "特殊困难");
		HashMap<String, String> tHD = new HashMap<String, String>();
		tHD.put("shmc", "不困难");
		HashMap<String, String> tHE = new HashMap<String, String>();
		tHE.put("shmc", "未审核");
		
		list.add(tHA);
		list.add(tHB);
		list.add(tHC);
		list.add(tHD);
		list.add(tHE);
		
		return list;
	}
	
	/**
	 * 删除生源地贷款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delSyddk(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delSyddk(cbVal, request);
	}
	
	/**
	 * 生源地贷款查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSyddkTit() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getSyddkTit();
	}
	
	/**
	 * 生源地贷款查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSyddkRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getSyddkRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 生源地贷款导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getSyddkExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgdzdx_syddk",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgdzdx_syddk");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 获取国家助学贷款申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getGjzxdkxx(pkVal);
	}
	
	/**
	 * 获取国家助学贷款历史信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdklsxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getGjzxdklsxx(pkVal);
	}
	
	/**
	 * 获取国家助学贷款合同名称
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getHtmc(String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getHtmc(xh);
	}
	
	/**
	 * 获取国家助学贷款审核通过信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdktgxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getGjzxdktgxx(pkVal);
	}
	
	/**
	 * 获取还款协议信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> gethkxyxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.gethkxyxx(pkVal);
	}
	
	/**
	 * 获取展期后还款协议信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getzqhhkxyxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getzqhhkxyxx(pkVal);
	}
	
	/**
	 * 获取展期协议信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getzqxyxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getzqxyxx(pkVal);
	}
	
	
	
	/**
	 * 保存国家助学贷款申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkSqxx(GjzxdkModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveGjzxdkSqxx(model, request);
	}
	
	/**
	 * 保存国家助学贷款详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkxx(GjzxdkModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveGjzxdkxx(model, request);
	}
	
	/**
	 * 保存国家助学贷款历史详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdklsxx(GjzxdkLsModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveGjzxdklsxx(model, request);
	}
	
	/**
	 * 通过学生信息得到国家助学贷款自动生成数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxdkStu(HashMap<String, String> map) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZxdkStu(map);
	}
	
	/**
	 * 通过学号得到学生最大贷款金额
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getDkje(String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZxdkJe(xh);
	}
	
	/**
	 * 通过学号得到学生每年贷款金额
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getMndkje(String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getMndkje(xh);
	}
	
	/**
	 * 得到国家助学贷款申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkSqb(HashMap<String, String> map,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getGjzxdkSqb(map,request);
	}
	
	/**
	 * 删除国家助学贷款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxdkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delGjzxdkxx(cbVal, request);
	}
	
	/**
	 * 国家助学贷款查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkTit() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getGjzxdkTit();
	}
	
	/**
	 * 国家助学贷款查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkTitT() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getGjzxdkTitT();
	}
	
	/**
	 * 国家助学贷款历史查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdklsTit() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getGjzxdklsTit();
	}
	
	/**
	 * 国家助学贷款查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdkRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxdkRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 国家助学贷款查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdkResT(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxdkResT(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 国家助学贷款历史查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdklsRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxdklsRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 国家助学贷款
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getGjzxdkExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zdgzgx_gjzxdk",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zdgzgx_gjzxdk");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 国家助学贷款历史信息
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getGjzxdklsExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "zgdzdx_gjzxdk_ls",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("zgdzdx_gjzxdk_ls");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存国家助学贷款审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkShxx(GjzxdkModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveGjzxdkShxx(model, request);
	}
	
	/**
	 * 得到学院规定分配合同编号列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getXyHtbhList(String xydm) throws Exception {
		dao = new XszzZgdzdxDAO();
		StringBuffer sb = new StringBuffer("!!OneSpile!!");
		
		String[] sT = dao.getXyHtbh(xydm);
		
		if (sT != null) {
			for (int i = Integer.parseInt(sT[0]); i <= Integer.parseInt(sT[1]); i++) {
				sb.append(i);
				sb.append("!!OneSpile!!");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 得到已分配合同编号列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getYyHtbhList(String xh,String nd) throws Exception {
		dao = new XszzZgdzdxDAO();
		StringBuffer sb = new StringBuffer("!!OneSpile!!");
		
		ArrayList<HashMap<String, String>> list = dao.getYyHtbhList(xh,nd);
		
		for (HashMap<String, String> hp : list){
			if (null != hp.get("htbh") && hp.get("htbh").length() == 19){
				sb.append(hp.get("htbh").substring(15));
				sb.append("!!OneSpile!!");
			}
		}
		return sb.toString();
	}
	
	
	
	public List<HashMap<String, String>> getHtList(HashMap<String, String> map) throws Exception {
		dao = new XszzZgdzdxDAO();
		
		String[] sT = dao.getXyHtbh(map.get("xydm"));
		ArrayList<HashMap<String, String>> list = dao.getYyHtbhList(map.get("xh"),map.get("nd"));
		
		ArrayList<String> htList = new ArrayList<String>();
		boolean b = true;
		
		if (sT != null) {
			for (int i = Integer.parseInt(sT[0]); i <= Integer.parseInt(sT[1]); i++) {
				for (Iterator<HashMap<String, String>> it = list.iterator(); it.hasNext() && b; ){
					HashMap<String, String> hp = it.next();
					if (null != hp.get("htbh") && hp.get("htbh").length() == 19 && i == Integer.parseInt(hp.get("htbh").substring(15))) {
						b = false;
					}
				}
				
				if (b){
					String ht = "";
					switch (String.valueOf(i).length()) {
					case 1:
						ht = "000" + String.valueOf(i);
						break;
					case 2:
						ht = "00" + String.valueOf(i);
						break;
					case 3:
						ht = "0" + String.valueOf(i);
						break;
					case 4:
						ht = String.valueOf(i);
						break;
					default:
						break;
					}
					htList.add(ht);
				}
				b = true;
			}
		}
		String[] temp = htList.toArray(new String[] {});
		return DAO.getInstance().arrayToList(temp,temp);
	}
	
	/**
	 * 得到资料确认书申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getZlqrsSqQx(String sUserType,String userDep,String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZlqrsSqQx(sUserType,userDep,xh);
	}
	
	/**
	 * 得到还款协议申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getHkxyQx(String sUserType,String userDep,String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getHkxyQx(sUserType,userDep,xh);
	}
	
	/**
	 * 得到展期后还款协议申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getZqhHkxyQx(String sUserType,String userDep,String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZqhHkxyQx(sUserType,userDep,xh);
	}
	
	/**
	 * 得到展期协议申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getZqxyQx(String sUserType,String userDep,String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZqxyQx(sUserType,userDep,xh);
	}
	
	/**
	 * 保存资料确认书申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveZlqrsSqxx(GjzxdkModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveZlqrsSqxx(model, request);
	}
	
	/**
	 * 得到资料确认书申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZlqrsSqb(HashMap<String, String> map,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZlqrsSqb(map,request);
	}
	
	/**
	 * 保存国家助学贷款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkxx(GjzxdkModel model, String xz,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveGjzxdkxx(model, xz, request);
	}
	
	/**
	 * 保存展期协议信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveZqxyxx(ZqxyModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveZqxyxx(model, request);
	}
	
	/**
	 * 保存模版格式信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean insertTemplate(String topstr, String leftstr, String fontstr, String tmpname,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.insertTemplate(topstr, leftstr, fontstr, tmpname, request);
	}
	
	/**
	 * @author ZhouMi
	 * @describe 得到奖助学金项目列表
	 */
	public synchronized List<HashMap<String, String>> getJzxjxmList() throws Exception{
		dao = new XszzZgdzdxDAO();
		return dao.getJzxjxmList();
	}
	
	/**
	 * @author ZhouMi
	 * @describe 保存奖助学金具体人数
	 */
	public void saveJzxjjtrs(String[] pks, String[] szrs, String[] sfyx,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.saveJzxjjtrs(pks, szrs, sfyx, request);
	}
	
	/**
	 * 得到模版格式信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getTeplateInf(String name) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getTeplateInf(name);
	}
	
	/**
	 * 获取国家助学贷款借据打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkju(String xh,String qs,String jjrq) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getGjzxdkju(xh,qs,jjrq);
	}

	/**
	 * 获取学生奖助学金相关信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJzxjInfo(String pkValue) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getJzxjInfo(pkValue);
	}
	
	/**
	 * 删除综合测评信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delZhcpxx(String[] cbVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delZhcpxx(cbVal);
	}
	
	/**
	 * 综合测评查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZhcpTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk", "xn", "xqmc", "xh", "xm", "nj",
				"xymc", "zymc", "bjmc", "zhszzcj", "zhszpm" };
		String[] cnList = new String[] { "主键", "学年", "学期", "学号", "姓名", "年级",
				Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "综合测评总成绩", "综合测评排名" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 综合测评查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZhcpRes(QueryModel queryModel,
			HttpServletRequest request, XszzZgdzdxActionForm actionForm)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getZhcpRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * 综合测评信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getZhcpResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getZhcpResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * 综合测评导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getZhcpExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_jzxj_zhcpxx", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_jzxj_zhcpxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 获取综合测评详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhcpxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZhcpxx(pkVal);
	}
	
	/**
	 * 保存综合测评详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveZhcpxx(ZhcpxxModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveZhcpxx(model, request);
	}
	
	/**
	 * 获取展期还款详细信息
	 * 
	 * @return
	 * @author luojw
	 */
	public HashMap<String, String> getZqxyInfo(String xh) {
		dao = new XszzZgdzdxDAO();
		return dao.getZqxyInfo(xh);
	}
	
	/**
	 * 初始化新生助学贷款时间设置
	 * @return
	 * @throws Exception
	 */
	public boolean xssjszReset() throws Exception{
		dao = new XszzZgdzdxDAO();
		return dao.xssjszReset();
	}
	

	/**
	 * 新生助学贷款申请批量设置
	 * @return
	 * @throws SQLException
	 */
	public boolean xssjszSave(String[] pkValue,String kssj,String jssj) throws SQLException {
		dao = new XszzZgdzdxDAO();
		
		if (null != pkValue && pkValue.length >0) {
			return dao.xssjszSave(pkValue, kssj, jssj);
		} 
		
		return false;
	}
	
	/**
	 * 判断是否助学贷款时间段
	 * @param xh
	 * @return
	 * @throws Exception 
	 */
	public boolean getGjzxdkSqQx(String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		
		String isXs = dao.isXs(xh);//是否新生
		
		if (Base.isNull(isXs) && !Base.isNull(dao.getLsSqsj(xh))) {//老生
			return true;
		} else if (!Base.isNull(isXs) && !Base.isNull(dao.getXsSqsj(xh))){//新生
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * 获取放款次数
	 * @param xh
	 * @return
	 */
	public String getFkcs(String xh) {
		dao = new XszzZgdzdxDAO();
		String isXs = dao.isXs(xh);
		
		if (!Base.isNull(isXs)) {
			return CommonQueryDAO.getStuInfo(xh).get("xz");//新生放款次数，即学制
		} else {
			String fkcs = dao.getLsFkcs(xh);
			if (!Base.isNull(fkcs) && Integer.valueOf(fkcs)>0) {
				return fkcs;
			}
		}
		
		return null;
	}
	
	
	/**
	 * 国家助学贷款贷款期限
	 * @param xh
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String, String> getDkqx(String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		
		return dao.getDkqx(xh);
	}
	
	/**
	 * 生源地贷款查询表头_武汉铁路
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSyddkTit_whtl() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getSyddkTit_whtl();
	}
	
	/**
	 * 生源地贷款查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSyddkRes_whtl(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
//		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getSyddkRes_whtl(queryModel,request);
//		}
		return resList;
	}
	
	/**
	 * 删除生源地贷款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delSyddk_whtl(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delSyddk_whtl(cbVal, request);
	}
	
	/**
	 * 保存生源地贷款信息_武汉铁路
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveSyddk_whtl(XszzZgdzdxActionForm myForm) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveSyddk_whtl(myForm);
	}
	
	/**
	 * 修改生源地贷款信息_武汉铁路
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateSyddk_whtl(XszzZgdzdxActionForm myForm) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.updateSyddk_whtl(myForm);
	}
	
	/**
	 * 获取单条生源地贷款记录_武汉铁路
	 * 
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getOneSyddk_whtl(XszzZgdzdxActionForm myForm) {
		dao = new XszzZgdzdxDAO();
		return dao.getOneSyddk_whtl(myForm);
	}
	
	/**
	 * 家庭情况调查导出 JtqkdcExp ---- 家庭情况调查导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getJtqkdcExp_whtl(User user,QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(user,queryModel, "view_whtl_syddk",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_whtl_syddk");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 检验学生资助
	 * @param pk
	 * @return
	 */
	public String checkXszz(String pk){
		String num="0";
		DAO dao = DAO.getInstance();
		String sql="select count(1) num from whtl_syddk where nd=? and xh=?";
		num=dao.getOneRs(sql, new String[]{Base.currNd,pk}, "num");
		return num;
	}
}
