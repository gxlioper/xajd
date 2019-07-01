package xgxt.xszz.zgmsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommDAO;
import xgxt.form.RequestForm;


/**
 * Title: 学生工作管理系统
 * Description:中国美术学院学生资助Service
 * Copyright: Copyright (c) 2008
 * Company: zfsoft
 * Author: 周觅
 * Version: 1.0
 * Time: 2008-12-16
 */
public class XszzZgmsxyService {

	XszzZgmsxyDAO dao = null;// 数据操作DAO

	/**
	 * 获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * 学生申请-获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStudent(String xh) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getStudent(xh);
	}
	/**
	 * 获取困难生认定信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrdxx(String pkVal) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getKnsrdxx(pkVal);
	}
	
	/**
	 * 得到困难生认定申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getKnsrdSqQx(String sUserType,String userDep,String xh) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getKnsrdSqQx(sUserType,userDep,xh);
	}
	
	/**
	 * 得到困难生认定审核规定时间
	 * 
	 * @return String[]
	 * @throws Exception
	 */
	public String[] getKnsrdShsj(String userDep) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getKnsrdShsj(userDep);
	}
	
	/**
	 * 得到困难生认定审核权限
	 * 
	 * @return 
	 * @throws Exception
	 */
	public String getKnsrdShQx(String userType,String userDep) throws Exception {
		dao = new XszzZgmsxyDAO();
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
		dao = new XszzZgmsxyDAO();
		return dao.saveKnsrdSqxx(model, request);
	}
	
	/**
	 * 得到困难生认定申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrdSqb(KnsrdModel model,HttpServletRequest request) throws Exception {
		dao = new XszzZgmsxyDAO();
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
		dao = new XszzZgmsxyDAO();
		dao.delKnsrdxx(cbVal, request);
	}
	
	/**
	 * 批量修改困难生认定审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsrdxx(String cbVal, String shType, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		dao.modKnsrdxx(cbVal, shType, shjg, request);
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
		dao = new XszzZgmsxyDAO();
		return dao.getKnsrdTit();
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
		dao = new XszzZgmsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrdRes(queryModel,request);
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
		dao = new XszzZgmsxyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgmsxy_knsxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgmsxy_knsxx");
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
		dao = new XszzZgmsxyDAO();
		return dao.saveKnsrdShxx(model, request);
	}
	
	/**
	 * 得到审核结果列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getshList() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		HashMap<String, String> tHB = new HashMap<String, String>();
		tHB.put("shmc", "困难");
		HashMap<String, String> tHC = new HashMap<String, String>();
		tHC.put("shmc", "特殊困难");
		HashMap<String, String> tHD = new HashMap<String, String>();
		tHD.put("shmc", "不困难");
		HashMap<String, String> tHE = new HashMap<String, String>();
		tHE.put("shmc", "未审核");
		
		list.add(tHB);
		list.add(tHC);
		list.add(tHD);
		list.add(tHE);
		
		return list;
	}
	
	/**
	 * 删除国家助学贷款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxdkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
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
		dao = new XszzZgmsxyDAO();
		return dao.getGjzxdkTit();
	}
	
	/**
	 * 国家助学贷款上报信息查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkSbxxTit() throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getGjzxdkSbxxTit();
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
		dao = new XszzZgmsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxdkRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 2011.9.6 qlj(修改:带分页)重载方法
	 * 国家助学贷款查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdkRes(XszzZgmsxyActionForm model,
			QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxdkRes(model,queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 国家助学贷款上报信息查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdkSbxxRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxdkSbxxRes(queryModel,request);
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
		dao = new XszzZgmsxyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgmsxy_gjzxdk",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgmsxy_gjzxdk");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 国家助学贷款上报信息导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void gjzxdksbExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgmsxyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgmsxy_gjzxdksbxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgmsxy_gjzxdksbxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 毕业生信息
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getBysxxExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgmsxyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgmsxy_gjzxdkgrxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgmsxy_gjzxdkgrxx");
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
		dao = new XszzZgmsxyDAO();
		return dao.saveGjzxdkShxx(model, request);
	}
	
	/**
	 * 批量修改国家助学贷款审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxdkxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		dao.modGjzxdkxx(cbVal, shjg, request);
	}
	
	/**
	 * 毕业生信息
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBysxxRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getBysxxRes(queryModel,request);
		}
		return resList;
	}
	
	
	/**
	 * 2011.9.6 qlj(修改:毕业生信息模块加分页功能)
	 * 毕业生信息
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBysxxRes(XszzZgmsxyActionForm model,
			QueryModel queryModel, HttpServletRequest request) throws Exception {
		dao = new XszzZgmsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getBysxxRes(model,queryModel, request);
		}
		return resList;
	}
	
	/**
	 * 毕业生信息
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getBysxxTit() throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getBysxxTit();
	}
	/**
	 * 删除毕业生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delBysxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		dao.delBysxx(cbVal, request);
	}
	/**
	 * 批量修改毕业生信息审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modBysxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		dao.modBysxx(cbVal, shjg, request);
	}
	/**
	 * 获取毕业生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBysxx(String pkVal) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getBysxx(pkVal);
	}
	/**
	 * 保存国家助学金审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean savBysShxx(GjzxdkModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.saveGjzxjShxx(model, request);
	}
	
	/**
	 * 获取国家助学贷款申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkxx(String pkVal) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getGjzxdkxx(pkVal);
	}

	/**
	 * 得到国家助学贷款申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjzxdkSqQx(String sUserType, String userDep, String xh)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getGjzxdkSqQx(sUserType, userDep, xh);
	}

	/**
	 * 保存国家助学贷款申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkSqxx(GjzxdkModel model, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.saveGjzxdkSqxx(model, request);
	}

	/**
	 * 保存学生申请-学生申请信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public boolean saveXsSqxx(GjzxdkModel model, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.saveXsSqxx(model, request);
	}
	
	/**
	 * 保存学生申请-学生申请审核信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public boolean saveXsSqshxx(GjzxdkModel model, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.saveXsSqshxx(model, request);
	}
	/**
	 * 得到毕业生信息采集申请权限
	 * 
	 * @param sUserType,userDep,xh
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getGjzxdkSqQx(String sUserType, String xh)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getBysxxcjSqQx(sUserType, xh);
	}
	
	/**
	 * 获取毕业生申请信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBysxxcjMap(String pkValue) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getBysxxcjMap(pkValue);
	}
	
	/**
	 * 保存毕业生申请信息
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public boolean saveByscjxx(QueryModel model, HttpServletRequest request) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.saveByscjxx(model, request);
	}
	

	public List<String[]>getSyddkList(XszzZgmsxyActionForm myForm) throws Exception{
		dao = new XszzZgmsxyDAO();
		return dao.getSyddkList(myForm);
	}
	
	/**
	 * 保存生源地贷款信息(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveSyddk(XszzZgmsxyActionForm myForm) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.saveSyddk(myForm);
	}
	
	public List<HashMap<String, String>> getTopTr(String lx,RequestForm rForm) {

		DAO dao = DAO.getInstance();
		
		String[]en=null;
		String[]cn=null;
		if("syddk".equalsIgnoreCase(lx)){
			 en =new String[] { "xn", "xh", "xm", "nj", "xymc", "zymc", "bjmc", "xfbz",
				"sqje","ffje" };
			 cn =new String[] { "学年", "学号", "姓名", "年级", Base.YXPZXY_KEY, "专业", "班级", "学费标准",
				"申请金额","发放金额" };
		}else if("gjzxdkff".equalsIgnoreCase(lx)){
			
			en =new String[] { "xn", "xh", "xm","hth", "nj", "xymc", "zymc", "bjmc", "ffje"};
		 cn =new String[] { "学年", "学号", "姓名","合同号", "年级", Base.YXPZXY_KEY, "专业", "班级", "发放金额" };
		}
		rForm.setColList(en);
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 删除生源地贷款信息(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delSyddk(XszzZgmsxyActionForm myForm) throws Exception {

		dao = new XszzZgmsxyDAO();
		return dao.delSyddk(myForm);
	}
	
	/**
	 * 获取单条生源地贷款记录
	 * 
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getOneSyddk(XszzZgmsxyActionForm myForm) {
		dao = new XszzZgmsxyDAO();
		return dao.getOneSyddk(myForm);
	}
	
	/**
	 * 修改生源地贷款信息(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateSyddk(XszzZgmsxyActionForm myForm) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.updateSyddk(myForm);
	}
	
	/**
	 * 获取生源地贷款信息列表(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getZxdkFfList(XszzZgmsxyActionForm myForm)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getZxdkFfList(myForm);
	}
	
	/**
	 * 删除助学贷款发放信息(2011.11.9 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delZxdkFf(XszzZgmsxyActionForm myForm) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.delZxdkFf(myForm);
	}
	
	/**
	 * 学生申请-学生申请查询表头
	 * 
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public List<HashMap<String, String>> getXssqTit() throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getXssqTit();
	}
	/**
	 *  学生申请-学生申请查询结果(修改:带分页)重载方法
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public List<String[]> getXssqRes(XszzZgmsxyActionForm model,
			QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXssqRes(model,queryModel,request);
		}
		return resList;
	}
	
	/**
	 *  学生申请-学生申请审核查询结果(修改:带分页)重载方法
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public List<String[]> getXssqshRes(XszzZgmsxyActionForm model,
			QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXssqshRes(model,queryModel,request);
		}
		return resList;
	}
	/**
	 * 批量修改学生申请-学生申请审核结果
	 * 
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public void modXssqxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		dao.modXssqxx(cbVal, shjg, request);
	}
	/**
	 * 删除学生申请-学生申请信息
	 * 
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public void delXssqxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		dao.delXssqxx(cbVal, request);
	}
	
	/**
	 * 获取学生申请信息
	 * 
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public HashMap<String, String> getXssqxx(String pkVal) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getXssqxx(pkVal);
	}

}
