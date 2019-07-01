package xgxt.rcsw.zzlgdx;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SaveForm;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江理工Service</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-11-27</p>
 */
public class RcswZzlgdxService {

	RcswZzlgdxDAO dao = null;// 数据操作DAO

	/**
	 * 获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * 删除在校证明信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delZxzmxx(String[] cbVal)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		dao.delZxzmxx(cbVal);
	}
	
	/**
	 * 在校证明查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZxzmTit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "xb", "sfzh", "nj",
				"xymc", "zymc", "bjmc", "lrsj" };
		return makeTitList(enList, StringUtils.splitStr(
				"主键-学号-姓名-性别-身份证号-年级-学院名称-专业名称-班级名称-录入时间", "-"));
	}
	
	/**
	 * 在校证明查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZxzmRes(QueryModel queryModel,
			HttpServletRequest request, RcswZzlgdxActionForm actionForm)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getZxzmRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * 在校证明信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getZxzmResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getZxzmResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * 在校证明导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getZxzmExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_rcsw_zzlgdx_zxzmxx", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_rcsw_zzlgdx_zxzmxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 获取在校证明信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxzmxx(String pkVal) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.getZxzmxx(pkVal);
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
	 * 保存在校证明信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxzmxx(ZxzmModel model,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.saveZxzmxx(model, request);
	}
	
	/**
	 * 删除出国(境)信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delCgjxx(String[] cbVal)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.delCgjxx(cbVal);
	}
	
	/**
	 * 出国(境)查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCgjTit() throws Exception {
		String[] enList = new String[] { "disabled", "pk", "xh", "xm", "xb", "nj",
				"xymc", "zymc", "bjmc", "sqqx", "sqsj", "xysh", "xxsh" };
		return makeTitList(enList, StringUtils.splitStr(
				"disabled-主键-学号-姓名-性别-年级-学院名称-专业名称-班级名称-申请去向-申请时间-学院审核-学校审核", "-"));
	}
	
	/**
	 * 出国(境)查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCgjRes(QueryModel queryModel,
			HttpServletRequest request, RcswZzlgdxActionForm actionForm,
			boolean b) throws Exception {
		dao = new RcswZzlgdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getCgjRes(queryModel, request, actionForm, b);
		}
		return resList;
	}
	
	/**
	 * 出国(境)信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getCgjResNum(QueryModel queryModel,
			HttpServletRequest request, boolean b) throws Exception {
		dao = new RcswZzlgdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getCgjResNum(queryModel, request, b);
		}
		return sT;
	}
	
	/**
	 * 出国(境)查询表头-学生
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCgjStuTit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "sqqx", "cgrq",
				"fhrq", "sqsj", "xysh", "xyshyj", "xxsh", "xxshyj" };
		return makeTitList(enList, StringUtils.splitStr(
				"主键-学号-姓名-申请去向-出国日期-返回日期-申请时间-学院审核-学院审核意见-学校审核-学校审核意见", "-"));
	}
	
	/**
	 * 出国(境)查询结果-学生
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCgjStuRes(String xh)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		List<String[]> resList = dao.getCgjStuRes(xh);
		return resList;
	}
	
	/**
	 * 出国(境)导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getCgjExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_rcsw_zzlgdx_cgjzm", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_rcsw_zzlgdx_cgjzm");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 出国(境)导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getCgjShExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpShDate(queryModel, "view_rcsw_zzlgdx_cgjzm", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_rcsw_zzlgdx_cgjzm");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 获取出国(境)信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCgjxx(String pkVal) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.getCgjxx(pkVal);
	}
	
	/**
	 * 保存出国(境)申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] saveCgjSqxx(CgjModel model,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.saveCgjSqxx(model, request);
	}
	
	/**
	 * 批量修改出国(境)审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modCgjxx(String[] cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.modCgjxx(cbVal, shjg, request);
	}
	
	/**
	 * 保存出国(境)审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveCgjShxx(CgjModel model,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.saveCgjShxx(model, request);
	}

	/**
	 * 删除请假信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delQjxx(String[] cbVal)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.delQjxx(cbVal);
	}
	
	/**
	 * 请假查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQjTit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "qjlx", "sqsj", "shjg" };
		return makeTitList(enList, StringUtils.splitStr(
				"主键-学号-姓名-性别-年级-学院名称-专业名称-班级名称-请假期限-申请时间-审核结果", "-"));
	}
	
	/**
	 * 请假查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getQjRes(QueryModel queryModel,
			HttpServletRequest request, RcswZzlgdxActionForm actionForm)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getQjRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * 请假信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getQjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getQjResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * 请假查询表头-学生
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQjStuTit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "qjlx", "qjksrq",
				"qjjzrq", "sqsj", "shjg", "shyj" };
		return makeTitList(enList, StringUtils.splitStr(
				"主键-学号-姓名-请假类型-请假开始日期-请假截止日期-申请时间-审核结果-审核意见", "-"));
	}
	
	/**
	 * 请假查询结果-学生
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getQjStuRes(String xh)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		List<String[]> resList = dao.getQjStuRes(xh);
		return resList;
	}
	
	/**
	 * 请假导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getQjExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_rcsw_zzlgdx_qjsqb", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_rcsw_zzlgdx_qjsqb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 获取请假信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getQjxx(String pkVal) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.getQjxx(pkVal);
	}
	
	/**
	 * 保存请假申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] saveQjSqxx(QjModel model,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.saveQjSqxx(model, request);
	}
	
	/**
	 * 批量修改请假审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modQjxx(String[] cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.modQjxx(cbVal, shjg, request);
	}
	
	/**
	 * 保存请假审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveQjShxx(QjModel model,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.saveQjShxx(model, request);
	}

	/**
	 * 删除入伍信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delRwxx(String[] cbVal)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.delRwxx(cbVal);
	}
	
	/**
	 * 入伍查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRwTit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "sqsj", "shjg" };
		return makeTitList(enList, StringUtils.splitStr(
				"主键-学号-姓名-性别-年级-学院名称-专业名称-班级名称-申请时间-审核结果", "-"));
	}
	
	/**
	 * 入伍查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRwRes(QueryModel queryModel,
			HttpServletRequest request, RcswZzlgdxActionForm actionForm)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getRwRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * 入伍信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getRwResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getRwResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * 入伍查询表头-学生
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRwStuTit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "sj", "sqsj",
				"shjg", "shyj" };
		return makeTitList(enList, StringUtils.splitStr(
				"主键-学号-姓名-手机-申请时间-审核结果-审核意见", "-"));
	}
	
	/**
	 * 入伍查询结果-学生
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRwStuRes(String xh)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		List<String[]> resList = dao.getRwStuRes(xh);
		return resList;
	}
	
	/**
	 * 入伍导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getRwExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_rcsw_zzlgdx_rwxx", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_rcsw_zzlgdx_rwxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 获取入伍信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRwxx(String pkVal) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.getRwxx(pkVal);
	}
	
	/**
	 * 保存入伍申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] saveRwSqxx(RwModel model,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.saveRwSqxx(model, request);
	}
	
	/**
	 * 批量修改入伍审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modRwxx(String[] cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.modRwxx(cbVal, shjg, request);
	}
	
	/**
	 * 保存入伍审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveRwShxx(RwModel model,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.saveRwShxx(model, request);
	}
	
	
	public void setList(HttpServletRequest request,String flg) {
		DAO dao = DAO.getInstance();
		
		if ("cxlx".equalsIgnoreCase(flg)) {
			//出险类型
			List<HashMap<String, String>> cxlxList = dao.getWhList("rcsw_cxlx", "dm", "mc", "", "", "");
			request.setAttribute("cxlxList", cxlxList);
		} else {
			
			List<HashMap<String, String>> xmmcList = dao.getWhList("rcsw_zxzm_xmdmb", "dm", "mc", "", "", "");
			request.setAttribute("xmmcList", xmmcList);
			
			List<HashMap<String, String>> shlxList = getSelectList("shlx");
			request.setAttribute("shlxList", shlxList);
			
			List<HashMap<String, String>> shztList = getSelectList("shzt");
			request.setAttribute("shztList", shztList);
		}
		
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
	}
	
	
	
	public List<HashMap<String, String>> getSelectList(String lx) {
		DAO dao = DAO.getInstance();
		String[] dm = null;
		String[] mc = null;
		if ("shlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "院审", "校审" };
			mc = new String[] { "院审", "校审" };
		} else if ("shzt".equalsIgnoreCase(lx)) {
			dm = new String[] { "未审核", "已通过","不通过" };
			mc = new String[] { "未审核", "已通过" ,"不通过"};
		}
		return dao.arrayToList(dm, mc);
	}
	
	
	/**
	 * 获取表单字段
	 * @param realTable
	 * @param myForm
	 */
	public void getBdZd(String realTable, RcswZzlgdxActionForm myForm) {
		dao = new RcswZzlgdxDAO();
		
		List<HashMap<String, String>> list = dao.getBdZd(realTable);
		HashMap<String, ArrayList<HashMap<String, String>>> opslist = dao.getOps(realTable);
		String[] zdyZd = new String[list.size()];
		String[] zdyZdz = new String[list.size()];
		String[] zdyZdlx = new String[list.size()];
		String[] zdyZdcd = new String[list.size()];
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				String zd = map.get("zdid");
				String zdz = map.get("zdmc");
				String zdlx = map.get("zdlx");
				String zdcd = map.get("zdcd");
				zdyZd[i] = zd;
				zdyZdz[i] = zdz;
				zdyZdlx[i] = zdlx;
				zdyZdcd[i] = zdcd;
			}
		}
		myForm.setArrZd(zdyZd);
		myForm.setArrZdz(zdyZdz);
		myForm.setArrZdlx(zdyZdlx);
		myForm.setArrZdcd(zdyZdcd);
		myForm.setOpslist(opslist);
	}
	
	
	public boolean saveData(String realTable, String[] colList, String pkV,
			ZxzmModel model, HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		
		boolean updata = dao.saveData(realTable, colList, model, request);
		
		if (updata) {
			updata = dao.updataZdyDdData(realTable, pkV, request);
		}
		return updata;
	}
	
	
	/**
	 * 获取自定义字段列表
	 * @param tableName
	 * @param xn
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> zdyColList(String tableName) throws SQLException {
		dao = new RcswZzlgdxDAO();
		return dao.zdyColList(tableName);
	}
	
	
	
	/**
	 * 获得自定义字段查询表头
	 * @param tableName
	 * @param colList
	 * @param zdyCol
	 * @param zdyColCN
	 * @return
	 */
	public List<HashMap<String, String>> getZdyTopTr(String tableName,
			String[] colList, String[] zdyCol, String[] zdyColCN) {
		DAO dao = DAO.getInstance();
		int colLen = colList.length;
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		String[] zcolList = new String[colLen + zdyCol.length];
		String[] zcolListCN = new String[colLen + zdyCol.length];
		for (int i = 0; i < colLen; i++) {
			zcolList[i] = colList[i];
		}
		for (int i = 0; i < zdyCol.length; i++) {
			zcolList[colLen + i] = zdyCol[i];
		}
		for (int i = 0; i < colLen; i++) {
			zcolListCN[i] = colListCN[i];
		}
		for (int i = 0; i < zdyCol.length; i++) {
			zcolListCN[colLen + i] = zdyColCN[i];
		}
		List<HashMap<String, String>> topTr = dao.arrayToList(zcolList,
				zcolListCN);
		return topTr;
	}
	
	
	/**
	 * 查询含自定义字段数据
	 * @param tableName
	 * @param model
	 * @param colList
	 * @param zdyCol
	 * @param realTable
	 * @param pk
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getDataList(String tableName, ZxzmModel model,
			String[] colList, String[] zdyCol, String realTable, String[] pk)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		dao = new RcswZzlgdxDAO();
		
		return dao.getZdyQueryList(tableName, model, colList, zdyCol,realTable, pk);
	}
	
	
	/**
	 * 批量删除
	 * @param realTable
	 * @param pkV
	 * @param pk
	 * @return
	 * @throws SQLException
	 */
	public boolean delData(String realTable, String[] pkV, String pk)
			throws SQLException {
		dao = new RcswZzlgdxDAO();
		return dao.delData(realTable, pkV, pk);
	}
	
	
	public boolean updateZxzm(SaveForm form, ZxzmModel model) throws Exception {
		DAO dao = DAO.getInstance();
		
		return dao.updateData(form, model);
	}
	
	
	/**
	 * 单条数据
	 * @param tableName
	 * @param realTable
	 * @param colList
	 * @param zdyzdList
	 * @param pkCol
	 * @param pk
	 * @return
	 */
	public HashMap<String, String> getOneData(String tableName,
			String realTable, String[] colList, String[] zdyzdList,
			String pkCol, String pk) {
		dao = new RcswZzlgdxDAO();
		
		return dao.getOneData(tableName, realTable, colList, zdyzdList, pkCol,
				pk);
	}
	
	
	
	/**
	 * 更新含自定义字段数据
	 * @param realTable
	 * @param pk
	 * @param model
	 * @param pkValue
	 * @param colList
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateData(String realTable, String pk, ZxzmModel model,
			String pkValue, String[] colList, HttpServletRequest request)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		
		String[] inputList = ModelToStrings.modelToStrings(colList, model);
		boolean updata = StandardOperation.updateNolog(realTable, colList,
				inputList, pk, pkValue);
		
		if (updata) {
			updata = dao.updataZdyDdData(realTable, pkValue,	request);
		}
		return updata;
	}
}
