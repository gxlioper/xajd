package xgxt.xszz.zgkydx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;

import xgxt.base.Excel2Oracle;

/**
 * Title: 学生工作管理系统
 * Description: 中国矿业大学学生资助Service
 * Copyright: Copyright (c) 2008
 * Company: zfsoft
 * Author: 周觅
 * Version: 1.0
 * Time: 2008-08-13
 */
public class XszzZgkdService {

	XszzZgkdDAO dao = null;// 数据操作DAO

	/**
	 * 获取困难生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsxx(String pkVal) throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getKnsxx(pkVal);
	}
	
	/**
	 * 获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * 得到困难生申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getKnsSqQx(String sUserType,String userDep,String xh) throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getKnsSqQx(sUserType,userDep,xh);
	}
	
	/**
	 * 保存困难生申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsSqxx(KnsModel saveKnsModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgkdDAO();
		return dao.saveKnsSqxx(saveKnsModel, request);
	}
	
	/**
	 * 得到困难生申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsSqb(KnsModel knsModel,HttpServletRequest request) throws Exception {
		dao = new XszzZgkdDAO();
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
		dao = new XszzZgkdDAO();
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
		dao = new XszzZgkdDAO();
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
		dao = new XszzZgkdDAO();
		return dao.getKnsTit();
	}
	
	/**
	 * 困难生查询结果 knsres ---- 困难生
	 * 
	 * @param queryModel,request,form
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsRes(QueryModel queryModel,HttpServletRequest request,ActionForm form)
			throws Exception {
		dao = new XszzZgkdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsRes(queryModel,request,form);
		}
		return resList;
	}
	
	/**
	 * 困难生查询结果记录数 knsresNum ---- 困难生记录数
	 * 
	 * @param queryModel,request,form
	 * @return
	 * @throws Exception
	 */
	public String getKnsResNum(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		String num = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			num = dao.getKnsResNum(queryModel,request);
		}
		return num;
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
		dao = new XszzZgkdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgkydx_knsxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgkydx_knsxx");
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
		dao = new XszzZgkdDAO();
		return dao.saveKnsShxx(saveKnsModel, request);
	}
	
	/**
	 * 特殊人群设置页面表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTsrqszTit() throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getTsrqszTit();
	}
	
	/**
	 * 特殊人群设置 - 查询困难生
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getTsrqKnsRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getTsrqKnsRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 得到特殊人群列表
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTsrqList()
			throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getTsrqList();
	}
	
	/**
	 * 保存特殊人群设置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void tsrqSave(String cbVal, String tsrqdm, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		dao.tsrqSave(cbVal, tsrqdm, request);
	}
	
	/**
	 * 特殊人群查询页面表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTsrqcxTit() throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getTsrqcxTit();
	}
	
	/**
	 * 特殊人群记录
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getTsrqRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getTsrqRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 删除特殊人群记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delTsrqxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		dao.delTsrqxx(cbVal, request);
	}
	
	/**
	 * 特殊人群导出 tsrqExp ---- 特殊人群导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getTsrqExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgkdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_zgkd_tsrq",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_zgkd_tsrq");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 获得还款维护详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getHkxxmoreinfo(String pkValue, HttpServletRequest request)
			throws Exception {
		
		HashMap<String, String> map = new HashMap<String, String>();
		dao = new  XszzZgkdDAO();	
		map= dao.getHkxxmoreinfo(pkValue);
		return map;
	}
	
	/**
	 * 还款信息修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean  hkxxUpdate(String[] columns,String[] values,String pk,String pkValue, HttpServletRequest request)
			throws Exception {
		
       boolean judge = false;
       dao = new XszzZgkdDAO();
       judge = dao.updateHkxxinfo(columns, values, pk, pkValue, request);     
       return judge;
	}
	
	/**
	 * 还款信息删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean  hkxxDel(String pk,String pkValue, HttpServletRequest request)
			throws Exception {
		
       boolean judge = false;
       dao = new XszzZgkdDAO();
       judge = dao.delHkxxinfo(pk, pkValue, request);     
       return judge;
	}
	
	/**
	 * 还款信息全部清空
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean  hkxxDelall(String tableName)
			throws Exception {
		
       boolean judge = false;
       dao = new XszzZgkdDAO();
       judge = dao.delallHkxxinfo(tableName);  
       return judge;
	}
	
	/**
	 * 还款信息列表查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>>  getHkxxList(HashMap<String, String> map,ActionForm form,HttpServletRequest request)
			throws Exception {
		
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
 
		
		StringBuffer query = new StringBuffer();
		String xh = map.get("xh");
		String xm = map.get("xm");
		String nj = map.get("nj");
		String xydm = map.get("xydm");
		String zydm = map.get("zydm");
		String bjdm = map.get("bjdm");
		String hth = map.get("hth");


		if (!("".equals(xh))) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%'");
		}
		if (!("".equals(xm))) {
			query.append(" and xm like '%");
			query.append(xm);
			query.append("%'");
		}
		if (!("".equals(nj))) {
			query.append(" and nj='");
			query.append(nj);
			query.append("'");
		}
		if (!("".equals(xydm))) {
			query.append(" and xydm= '");
			query.append(xydm);
			query.append("'");
		}
		if (!("".equals(zydm))) {
			query.append(" and zydm='");
			query.append(zydm);
			query.append("'");
		}
		if (!("".equals(bjdm))) {
			query.append(" and bjdm='");
			query.append(bjdm);
			query.append("'");
		}
		if (!("".equals(hth))) {
			query.append(" and hth like '%");
			query.append(hth);
			query.append("%'");
		}
		
		//String query1 = query.toString();
		XszzZgkdDAO dao1 = new XszzZgkdDAO();
		rs = dao1.getHkxsxx(query, form, request);
		
		
       return rs;
	}
	
	
	/**
	 * 特殊人群与资助项目设置页面表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTsrqxmTit() throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getTsrqxmTit();
	}
	
	/**
	 * 特殊人群项目设置
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getTsrqxmRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getTsrqxmRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 得到资助项目记录
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZzxmList() throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getZzxmList();
	}
	
	/**
	 * 删除特殊人群与资助项目记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delTsrqxmcxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		dao.delTsrqxmcxx(cbVal, request);
	}
	
	/**
	 * 保存特殊人群与资助项目设置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean tsrqxmSave(String zzxmdm, String tsrqdm, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		return dao.tsrqxmSave(zzxmdm, tsrqdm, request);
	}
}
