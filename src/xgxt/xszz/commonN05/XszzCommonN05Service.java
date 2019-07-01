package xgxt.xszz.commonN05;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
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
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.szdw.utils.MakeQuery;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

/**
 * Title: 学生工作管理系统 Description: N05学生资助Service Copyright: Copyright (c) 2009
 * Company: zfsoft Author: 周觅 Version: 1.0 Time: 2009-10-13
 */
public class XszzCommonN05Service {

	XszzCommonN05DAO dao = null;// 数据操作DAO

	private List<HashMap<String, String>> makeTitList(String[] enList,
			String[] cnList) {
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
	 * 获取列表数据
	 * 
	 * @param int
	 *            num
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getChkList(int num) {
		DAO dao = DAO.getInstance();
		return dao.getChkList(num);
	}

	/**
	 * 获取项目审核级别
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getShjb(String xmb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getShjb(xmb);
	}

	/**
	 * 获取项目是否必须困难生才能申请
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getSfkns(String xmb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getSfkns(xmb);
	}

	/**
	 * 获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getStu(xh);
	}

	/**
	 * 获取xszz_com_xfjm1减免次数
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getXfjm1cs(String xh) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXfjm1cs(xh);
	}

	/**
	 * 获取学生xszz_com_knsrdb1困难等级
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getKns1Rdqk(String xh, String xn) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKns1Rdqk(xh, xn);
	}

	/**
	 * 得到申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getSqQx(String zzType, String sUserType, String xh)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getSqQx(zzType, sUserType, xh);
	}

	/**
	 * 项目设置查询表头 getXmszTit ---- 项目设置查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXmszTit() throws Exception {
		String[] enList = new String[] { "pk", "xmmc", "shjb", "sfkns" };
		String[] cnList = new String[] { "主键", "项目", "审核级别", "是否必须为困难生" };
		return makeTitList(enList, cnList);
	}

	/**
	 * 项目设置查询结果 Xmszres ---- 项目设置
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXmszRes(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXmszRes(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * 得到项目列表
	 * 
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getXmList() throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXmList();
	}

	/**
	 * 获取项目设置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXmszxx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXmszxx(pkVal);
	}

	/**
	 * 删除外设助学金项目信息
	 * 
	 * @param pkT,request
	 * @return
	 * @throws Exception
	 */
	public void delWszxjxm(String[] pk, HttpServletRequest request) {
		dao = new XszzCommonN05DAO();
		dao.delWszxjxm(pk, request);
	}

	/**
	 * 外设助学金项目信息查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWszxjxmTit() throws Exception {
		String[] enList = new String[] { "pk", "xmdm", "xmmc" };
		String[] cnList = new String[] { "主键", "项目代码", "项目名称" };
		return makeTitList(enList, cnList);
	}

	/**
	 * 外设助学金项目信息结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxjxmRes(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getWszxjxmRes(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * 获取外设助学金项目详细信息
	 * 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWszxjxmByXmdm(String xmdm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getWszxjxmByXmdm(xmdm);
	}

	/**
	 * 外设助学金项目金额记录结果
	 * 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWszxjXmjeList(String xmdm) {
		dao = new XszzCommonN05DAO();
		return dao.getWszxjXmjeList(xmdm);
	}

	/**
	 * 保存外设助学金项目信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxjxm(String xmdm, String xmmc,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveWszxjxm(xmdm, xmmc, request);
	}

	/**
	 * 保存外设助学金金额记录
	 * 
	 * @param param
	 * @param xmdm
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxjje(List<HashMap<String, String>> param, String xmdm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveWszxjje(param, xmdm);
	}

	/**
	 * 获取家庭情况调查1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJtqkdc1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getJtqkdc1xx(pkVal);
	}

	/**
	 * 保存家庭情况调查1申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveJtqkdc1Sqxx(Jtqkdc1Model model,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveJtqkdc1Sqxx(model, request);
	}

	/**
	 * 删除家庭情况调查1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delJtqkdc1xx(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delJtqkdc1xx(cbVal, request);
	}

	/**
	 * 家庭情况调查1查询表头 Jtqkdc1tit ---- 家庭情况调查1查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJtqkdc1Tit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "txsj" };
		String[] cnList = new String[] { "主键", "学号", "姓名", "性别", "身份证号",
				Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "填写时间" };
		return makeTitList(enList, cnList);
	}

	/**
	 * 家庭情况调查1查询结果 Jtqkdc1res ---- 家庭情况调查1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJtqkdc1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getJtqkdc1Res(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * 家庭情况调查1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getJtqkdc1ResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getJtqkdc1ResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * 家庭情况调查1导出 Jtqkdc1Exp ---- 家庭情况调查1导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getJtqkdc1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_jtqkdc1", request, true);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_jtqkdc1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 获取困难生认定1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd1xx(pkVal);
	}

	/**
	 * 获取困难生认定1意见信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd1yjxx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd1yjxx(pkVal);
	}

	/**
	 * 保存困难生认定1意见信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd1Yj(String yj, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnsrd1Yj(yj, request);
	}

	/**
	 * 保存困难生认定1申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd1Sqxx(Knsrdb1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnsrd1Sqxx(model, request, shjb);
	}

	/**
	 * 删除困难生认定1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsrd1xx(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delKnsrd1xx(cbVal, request);
	}

	/**
	 * 批量修改困难生认定1审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd1xx(String[] cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modKnsrd1xx(cbVal, shjg, request);
	}

	/**
	 * 困难生认定1查询表头 Knsrd1tit ---- 困难生认定1查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd1Tit() throws Exception {
		String[] enList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "disabled", "bgcolor", "主键", "学年",
				"学号", "姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间",
				"辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		return makeTitList(enList, cnList);
	}

	/**
	 * 困难生认定1查询结果 Knsrd1res ---- 困难生认定1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd1Res(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * 困难生认定1查询结果 getKnsrd1ResByFdy ---- 困难生认定1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd1ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd1ResByFdy(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * 困难生认定1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsrd1ResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnsrd1ResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * 困难生认定1导出 Knsrd1Exp ---- 困难生认定1导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getKnsrd1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_knsrdb1", request, true);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_knsrdb1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存困难生认定1审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd1Shxx(Knsrdb1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnsrd1Shxx(model, request);
	}

	/**
	 * 困难生认定1查询表头 getKnsrd1TitForDM ---- 困难生认定1查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd1TitForDM(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "bjmc",
					"rs", "tjr", "tjsj", "xyshjg", "xxshjg" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "班级名称",
					"人数", "提交人", "提交时间", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xymc",
					"rs", "tjr", "tjsj", "xxshjg" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", Base.YXPZXY_KEY+"名称",
					"人数", "提交人", "提交时间", "学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 困难生认定1查询表头 getKnsrd1TitForStush ---- 困难生认定1查询表头(按部门审核模式)
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd1TitForStush(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};

		enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc",
				"sqsj", "fdysh", "xyshjg", "xxshjg" };
		cnList = new String[] { "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称",
				"申请时间", "辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };

		return makeTitList(enList, cnList);
	}

	/**
	 * 获取困难生认定1审核提交信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd1shtjjg(String pkVal,
			String userType, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd1shtjjg(pkVal, userType, shjb);
	}

	/**
	 * 获取困难生认定1审核提交信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd1Stush(String pkVal, String userType,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd1Stush(pkVal, userType, shjb);
	}

	/**
	 * 批量修改困难生认定1审核结果(按部门审核)
	 * 
	 * @return
	 * @throws Exception
	 */
	public void audiKnsrd1ForDep(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modKnsrd1xxBybj(model, request);// 修改学生审核信息
		dao.modKnsrd1Bmshtjb(model, request);// 修改部门审核信息
	}

	/**
	 * 提交学院审核结果
	 * 
	 * @param String
	 *            userDep
	 * @param String
	 *            userName
	 * @param String
	 *            tjxm
	 * @param String
	 *            zj
	 * @param String
	 *            zjz
	 * @return boolean
	 */
	public boolean commitResult(String userDep, String userName, String tjxm,
			String zj, String zjz) {
		dao = new XszzCommonN05DAO();
		return dao.modXytjshjg(userDep, userName, tjxm, zj, zjz);
	}

	/**
	 * 获取学院提交状态
	 * 
	 * @param String
	 *            userDep
	 * @param String
	 *            zjz
	 * @param String
	 *            tjxm
	 * @return String
	 */
	public String queryXyshjg(String userDep, String zjz, String tjxm) {
		dao = new XszzCommonN05DAO();
		return dao.selectXyshjg(userDep, zjz, tjxm);
	}

	/**
	 * 批量修改困难生认定1审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd1xxByDepModel(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modKnsrd1xxByDepModel(cbVal, shjg, request, shjb);
	}

	/**
	 * 批量修改困难生认定1审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean addKnsrd1shtj(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		String[] columns = { "zj", "zjz", "dm", "bm", "tjxm", "tjzt", "tjr" };
		String[] values = { "xn", model.getXn(), model.getBjdm(), "bj",
				"knsrd1", "1", model.getUserName() };
		StandardOperation.delete("xszz_com_bmshtjb", "zjz||dm||bm||tjxm", model
				.getXn()
				+ model.getBjdm() + "bjknsrd1", request);
		return StandardOperation.insert("xszz_com_bmshtjb", columns, values,
				request);
	}

	/**
	 * 困难生认定1查询结果 getKnsrd1ResForDM ---- 困难生认定1(按部门模式查询)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd1ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd1ResForDM(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * 获取困难生认定3信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd3xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd3xx(pkVal);
	}

	/**
	 * 获取困难生认定3意见信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd3yjxx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd3yjxx(pkVal);
	}

	/**
	 * 保存困难生认定3意见信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd3Yj(String yj, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnsrd3Yj(yj, request);
	}

	/**
	 * 保存困难生认定3申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd3Sqxx(Knsrdb3Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnsrd3Sqxx(model, request);
	}

	/**
	 * 删除困难生认定3信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsrd3xx(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delKnsrd3xx(cbVal, request);
	}

	/**
	 * 批量修改困难生认定3审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd3xx(String[] cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modKnsrd3xx(cbVal, shjg, request);
	}

	/**
	 * 批量评定为困难生
	 * 
	 * @return
	 * @throws Exception
	 */
	public void plPdkns(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		dao.plPdkns(cbVal, request);
	}

	/**
	 * 困难生认定3查询表头 Knsrd3tit ---- 困难生认定3查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd3Tit() throws Exception {
		String[] enList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "disabled", "bgcolor", "主键", "学年",
				"学号", "姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间",
				"民主评议结果", "民主认定结果", "学校审核" };
		return makeTitList(enList, cnList);
	}

	/**
	 * 困难生认定3公示表头 Knsrd3gstit ---- 困难生认定3公示表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd3gsTit() throws Exception {
		String[] enList = new String[] { "xn", "xh", "xm", "xb", "xymc",
				"zymc", "bjmc", "sqsj", "xxsh" };
		String[] cnList = new String[] { "学年", "学号", "姓名", "性别", Base.YXPZXY_KEY+"名称",
				"专业名称", "班级名称", "申请时间", "评定结果" };
		return makeTitList(enList, cnList);
	}

	/**
	 * 困难生认定3意见表头 Knsrd3yjtit ---- 困难生认定3意见表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd3yjTit() throws Exception {
		String[] enList = new String[] { "yj", "txsj" };
		String[] cnList = new String[] { "意见", "填写时间" };
		return makeTitList(enList, cnList);
	}

	/**
	 * 困难生认定3查询结果 Knsrd3res ---- 困难生认定3
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd3Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd3Res(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * 困难生认定3公示结果 Knsrd3gsres ---- 困难生认定3
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd3gsRes(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd3gsRes(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * 困难生认定3意见结果 Knsrd3yjres ---- 困难生认定3意见
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd3yjRes(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd3yjRes(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * 困难生认定3信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsrd3ResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnsrd3ResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * 困难生认定3公示查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsrd3gsResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnsrd3gsResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * 困难生认定3意见查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsrd3yjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnsrd3yjResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * 困难生认定3导出 Knsrd3Exp ---- 困难生认定3导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getKnsrd3Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_knsrdb3", request, true);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_knsrdb3");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存困难生认定3审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd3Shxx(Knsrdb3Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnsrd3Shxx(model, request);
	}

	/**
	 * 获取困难生认定2信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd2xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd2xx(pkVal);
	}

	/**
	 * 保存困难生认定2申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd2Sqxx(Knsrdb1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnsrd2Sqxx(model, request);
	}

	/**
	 * 删除困难生认定2信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsrd2xx(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delKnsrd2xx(cbVal, request);
	}

	/**
	 * 批量修改困难生认定2审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd2xx(String[] cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modKnsrd2xx(cbVal, shjg, request);
	}

	/**
	 * 获取困难生认定2审核提交信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd2shtjjg(String pkVal, String userType)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd2shtjjg(pkVal, userType);
	}

	/**
	 * 获取困难生认定2审核提交信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd2Stush(String pkVal, String userType,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd2Stush(pkVal, userType, shjb);
	}

	/**
	 * 批量修改困难生认定2审核结果(按部门审核)
	 * 
	 * @return
	 * @throws Exception
	 */
	public void audiKnsrd2ForDep(String[] cbVal, String shjg, String shyj,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modKnsrd2xxBybj(cbVal, shjg, request, shjb);// 修改学生审核信息
		dao.modKnsrd2Bmshtjb(cbVal, shjg, shyj, request, shjb);// 修改部门审核信息
	}

	/**
	 * 批量修改困难生认定2审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd2xxByDepModel(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modKnsrd2xxByDepModel(cbVal, shjg, request, shjb);
	}

	/**
	 * 批量修改困难生认定2审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean addKnsrd2shtj(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		String[] columns = { "zj", "zjz", "dm", "bm", "tjxm", "tjzt" };
		String[] values = { "xn", model.getXn(), model.getBjdm(), "bj",
				"knsrd2", "1" };
		StandardOperation.delete("xszz_com_bmshtjb", "zjz||dm||bm||tjxm", model
				.getXn()
				+ model.getBjdm() + "bjknsrd2", request);
		return StandardOperation.insert("xszz_com_bmshtjb", columns, values,
				request);
	}

	/**
	 * 困难生认定2查询表头 getKnsrd2TitForStush ---- 困难生认定2查询表头(按部门审核模式)
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd2TitForStush(String userType)
			throws Exception {
		String[] enList = new String[] { "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "sqsj", "fdysh", "xyshjg", "xxshjg" };
		String[] cnList = new String[] { "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY+"名称",
				"专业名称", "班级名称", "申请时间", "辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };

		return makeTitList(enList, cnList);
	}

	/**
	 * 困难生认定2查询表头 Knsrd2tit ---- 困难生认定2查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd2Tit() throws Exception {
		String[] enList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "disabled", "bgcolor", "主键", "学年",
				"学号", "姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间",
				"辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		return makeTitList(enList, cnList);
	}

	/**
	 * 困难生认定2查询表头 Knsrd2tit ---- 困难生认定2查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd2TitForDM(String userType)
			throws Exception {
		String[] enList = null;
		String[] cnList = null;
		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "bjmc",
					"xyshjg" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "班级名称",
					Base.YXPZXY_KEY+"审核结果" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "zymc",
					"xxshjg" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "专业名称",
					"学校审核结果" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 困难生认定2查询结果 Knsrd2res ---- 困难生认定2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd2Res(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * 困难生认定2查询结果 getKnsrd2ResByFdy ---- 困难生认定2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd2ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd2ResByFdy(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * 困难生认定2查询结果 getKnsrd2ResForDM ---- 困难生认定2(按部门模式查询)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd2ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd2ResForDM(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * 困难生认定2信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsrd2ResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnsrd2ResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * 困难生认定2导出 Knsrd2Exp ---- 困难生认定2导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getKnsrd2Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_knsrdb1", request, true);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_knsrdb1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存困难生认定2审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd2Shxx(Knsrdb1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnsrd2Shxx(model, request);
	}

	/**
	 * 获取困难生认定4信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getknsrd4xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getknsrd4xx(pkVal);
	}

	/**
	 * 保存困难生认定4申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveknsrd4Sqxx(Knsrdb1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveknsrd4Sqxx(model, request);
	}

	/**
	 * 删除困难生认定4信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delknsrd4xx(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delknsrd4xx(cbVal, request);
	}

	/**
	 * 批量修改困难生认定4审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modknsrd4xx(String[] cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modknsrd4xx(cbVal, shjg, request);
	}

	/**
	 * 困难生认定4查询表头 knsrd4tit ---- 困难生认定4查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getknsrd4Tit() throws Exception {
		String[] enList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "disabled", "bgcolor", "主键", "学年",
				"学号", "姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间",
				"辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		return makeTitList(enList, cnList);
	}

	/**
	 * 困难生认定4查询结果 knsrd4res ---- 困难生认定4
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getknsrd4Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getknsrd4Res(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * 困难生认定4信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getknsrd4ResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getknsrd4ResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * 困难生认定4导出 knsrd4Exp ---- 困难生认定4导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getknsrd4Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_knsrdb1", request, true);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_knsrdb1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存困难生认定4审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveknsrd4Shxx(Knsrdb1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveknsrd4Shxx(model, request);
	}

	/**
	 * 获取国家奖学金1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjjxj1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjjxj1xx(pkVal);
	}

	/**
	 * 保存国家奖学金1申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjjxj1Sqxx(Gjjxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjjxj1Sqxx(model, request, shjb);
	}

	/**
	 * 删除国家奖学金1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjjxj1xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delGjjxj1xx(cbVal, request, shjb);
	}

	/**
	 * 批量修改国家奖学金1审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjjxj1xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjjxj1xx(cbVal, shjg, request, shjb);
	}

	/**
	 * 国家奖学金1查询表头 Gjjxj1tit ---- 国家奖学金1查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjjxj1Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间",
					"辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "xysh",
					"xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间", Base.YXPZXY_KEY+"审核",
					"学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 国家奖学金1查询结果 Gjjxj1res ---- 国家奖学金1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjjxj1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjjxj1Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * 国家奖学金1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjjxj1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjjxj1ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * 国家奖学金1查询表头 getGjjxj1TitForDM ---- 国家奖学金1查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjjxj1TitForDM(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "bjmc",
					"rs", "yjjl", "xjjl", "sjjl", "zje", "tjr", "tjsj", "xysh",
					"xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "班级名称",
					"人数", "院级奖励", "校级奖励", "省级奖励", "总金额", "提交人", "提交时间", Base.YXPZXY_KEY+"审核",
					"学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xymc",
					"rs", "yjjl", "xjjl", "sjjl", "zje", "tjr", "tjsj", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", Base.YXPZXY_KEY+"名称",
					"人数", "院级奖励", "校级奖励", "省级奖励", "总金额", "提交人", "提交时间", "学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 国家奖学金1查询表头 getGjjxj1TitForStush ---- 国家奖学金1查询表头(按部门审核模式)
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjjxj1TitForStush(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "yjjl", "xjjl", "sjjl", "zje", "xysh",
					"xxsh" };
			cnList = new String[] { "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY+"名称", "专业名称",
					"班级名称", "申请时间", "院级奖励", "校级奖励", "省级奖励", "总金额", Base.YXPZXY_KEY+"审核",
					"学校审核" };
		} else {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "yjjl", "xjjl", "sjjl", "zje", "xxsh" };
			cnList = new String[] { "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY+"名称", "专业名称",
					"班级名称", "申请时间", "院级奖励", "校级奖励", "省级奖励", "总金额", "学校审核" };
		}

		return makeTitList(enList, cnList);
	}

	/**
	 * 国家奖学金1查询结果 getGjjxj1ResByFdy ---- 国家奖学金1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjjxj1ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjjxj1ResByFdy(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * 获取国家奖学金1审核提交信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjjxj1shtjjg(String pkVal,
			String userType, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjjxj1shtjjg(pkVal, userType, shjb);
	}

	/**
	 * 获取国家奖学金1审核提交信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjjxj1Stush(String pkVal, String userType,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjjxj1Stush(pkVal, userType, shjb);
	}

	/**
	 * 批量修改国家奖学金1审核结果(按部门审核)
	 * 
	 * @return
	 * @throws Exception
	 */
	public void audiGjjxj1ForDep(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjjxj1xxBybj(model, request);// 修改学生审核信息
		dao.modGjjxj1Bmshtjb(model, request);// 修改部门审核信息
	}

	/**
	 * 批量修改国家奖学金1审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjjxj1xxByDepModel(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjjxj1xxByDepModel(cbVal, shjg, request, shjb);
	}

	/**
	 * 批量修改国家奖学金1审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean addGjjxj1shtj(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		String[] columns = { "zj", "zjz", "dm", "bm", "tjxm", "tjzt", "tjr" };
		String[] values = { "xn", model.getXn(), model.getBjdm(), "bj",
				"gjjxj1", "1", model.getUserName() };
		StandardOperation.delete("xszz_com_bmshtjb", "zjz||dm||bm||tjxm", model
				.getXn()
				+ model.getBjdm() + "bjgjjxj1", request);
		return StandardOperation.insert("xszz_com_bmshtjb", columns, values,
				request);
	}

	/**
	 * 国家奖学金1查询结果 getGjjxj1ResForDM ---- 国家奖学金1(按部门模式查询)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjjxj1ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjjxj1ResForDM(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * 国家奖学金1导出 Gjjxj1Exp ---- 国家奖学金1导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getGjjxj1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_gjjxj1", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_gjjxj1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存国家奖学金1审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjjxj1Shxx(Gjjxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjjxj1Shxx(model, request, shjb);
	}

	/**
	 * 获取国家助学金1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxj1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjzxj1xx(pkVal);
	}

	/**
	 * 保存国家助学金1申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj1Sqxx(Gjzxj1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjzxj1Sqxx(model, request);
	}

	/**
	 * 删除国家助学金1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxj1xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delGjzxj1xx(cbVal, request, shjb);
	}

	/**
	 * 批量修改国家助学金1审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxj1xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjzxj1xx(cbVal, shjg, request, shjb);
	}

	/**
	 * 国家助学金1查询表头 Gjzxj1tit ---- 国家助学金1查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxj1Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"zxjdj", "zxjje", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间", "等级",
					"金额", "辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"zxjdj", "zxjje", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间", "等级",
					"金额", Base.YXPZXY_KEY+"审核", "学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 国家助学金1查询结果 Gjzxj1res ---- 国家助学金1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxj1Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * 国家助学金1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjzxj1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjzxj1ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * 国家助学金1导出 Gjzxj1Exp ---- 国家助学金1导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getGjzxj1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_gjzxj1", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_gjzxj1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存国家助学金1审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj1Shxx(Gjzxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjzxj1Shxx(model, request, shjb);
	}

	/**
	 * 获取国家助学金2信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxj2xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjzxj2xx(pkVal);
	}

	/**
	 * 保存国家助学金2申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj2Sqxx(Gjzxj1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjzxj2Sqxx(model, request);
	}

	/**
	 * 删除国家助学金2信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxj2xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delGjzxj2xx(cbVal, request, shjb);
	}

	/**
	 * 批量修改国家助学金2审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxj2xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjzxj2xx(cbVal, shjg, request, shjb);
	}

	/**
	 * 国家助学金2查询表头 Gjzxj2tit ---- 国家助学金2查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxj2Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"zxjdj", "zxjje", "rdqk", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间", "等级",
					"金额", "认定情况", "辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"zxjdj", "zxjje", "rdqk", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间", "等级",
					"金额", "认定情况", Base.YXPZXY_KEY+"审核", "学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 国家助学金2查询结果 Gjzxj2res ---- 国家助学金2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxj2Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * 国家助学金2信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjzxj2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjzxj2ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * 国家助学金2导出 Gjzxj2Exp ---- 国家助学金2导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getGjzxj2Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_gjzxj2", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_gjzxj2");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存国家助学金2审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj2Shxx(Gjzxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjzxj2Shxx(model, request, shjb);
	}

	/**
	 * 获取国家助学金3信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxj3xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjzxj3xx(pkVal);
	}

	/**
	 * 保存国家助学金3申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj3Sqxx(Gjzxj3Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjzxj3Sqxx(model, request, shjb);
	}

	/**
	 * 删除国家助学金3信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxj3xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delGjzxj3xx(cbVal, request, shjb);
	}

	/**
	 * 批量修改国家助学金3审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxj3xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjzxj3xx(cbVal, shjg, request, shjb);
	}

	/**
	 * 国家助学金3查询表头 Gjzxj3tit ---- 国家助学金3查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxj3Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"zxjdj", "zxjje", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间", "等级",
					"金额", "辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"zxjdj", "zxjje", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间", "等级",
					"金额", Base.YXPZXY_KEY+"审核", "学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 国家助学金3查询结果 Gjzxj3res ---- 国家助学金3
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj3Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxj3Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * 国家助学金3信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjzxj3ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjzxj3ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * 国家助学金3查询表头 getGjzxj3TitForDM ---- 国家助学金3查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxj3TitForDM(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "bjmc",
					"rs", "zxjje", "tjr", "tjsj", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "班级名称",
					"人数", "金额", "提交人", "提交时间", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xymc",
					"rs", "zxjje", "tjr", "tjsj", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", Base.YXPZXY_KEY+"名称",
					"人数", "金额", "提交人", "提交时间", "学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 国家助学金3查询表头 getGjzxj3TitForStush ----国家助学金3查询表头(按部门审核模式)
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxj3TitForStush(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "zxjdj", "zxjje", "xysh", "xxsh" };
			cnList = new String[] { "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY+"名称", "专业名称",
					"班级名称", "申请时间", "等级", "金额", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "zxjdj", "zxjje", "xxsh" };
			cnList = new String[] { "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY+"名称", "专业名称",
					"班级名称", "申请时间", "等级", "金额", "学校审核" };
		}

		return makeTitList(enList, cnList);
	}

	/**
	 * 国家助学金3查询结果 getGjzxj3ResByFdy ---- 国家助学金3
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj3ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxj3ResByFdy(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * 获取国家助学金3审核提交信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxj3shtjjg(String pkVal,
			String userType, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjzxj3shtjjg(pkVal, userType, shjb);
	}

	/**
	 * 获取国家助学金3审核提交信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj3Stush(String pkVal, String userType,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjzxj3Stush(pkVal, userType, shjb);
	}

	/**
	 * 批量修改国家助学金3审核结果(按部门审核)
	 * 
	 * @return
	 * @throws Exception
	 */
	public void audiGjzxj3ForDep(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjzxj3xxBybj(model, request);// 修改学生审核信息
		dao.modGjzxj3Bmshtjb(model, request);// 修改部门审核信息
	}

	/**
	 * 批量修改国家助学金3审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxj3xxByDepModel(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjzxj3xxByDepModel(cbVal, shjg, request, shjb);
	}

	/**
	 * 批量修改国家助学金3审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean addGjzxj3shtj(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		String[] columns = { "zj", "zjz", "dm", "bm", "tjxm", "tjzt", "tjr" };
		String[] values = { "xn", model.getXn(), model.getBjdm(), "bj",
				"gjzxj3", "1", model.getUserName() };
		StandardOperation.delete("xszz_com_bmshtjb", "zjz||dm||bm||tjxm", model
				.getXn()
				+ model.getBjdm() + "bjgjzxj3", request);
		return StandardOperation.insert("xszz_com_bmshtjb", columns, values,
				request);
	}

	/**
	 * 国家助学金3查询结果 getGjzxj3ResForDM ---- 国家助学金3(按部门模式查询)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj3ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxj3ResForDM(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * 国家助学金3导出 Gjzxj3Exp ---- 国家助学金3导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getGjzxj3Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_gjzxj3", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_gjzxj3");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存国家助学金3审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj3Shxx(Gjzxj3Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjzxj3Shxx(model, request, shjb);
	}

	/**
	 * 得到国家助学金列表
	 * 
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getGjzxjList() throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjzxjList();
	}

	/**
	 * 获取国家励志奖学金1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxj1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjlzjxj1xx(pkVal);
	}

	/**
	 * 保存国家励志奖学金1申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxj1Sqxx(Gjlzjxj1Model model,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjlzjxj1Sqxx(model, request);
	}

	/**
	 * 删除国家励志奖学金1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjlzjxj1xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delGjlzjxj1xx(cbVal, request, shjb);
	}

	/**
	 * 批量修改国家励志奖学金1审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjlzjxj1xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjlzjxj1xx(cbVal, shjg, request, shjb);
	}

	/**
	 * 国家励志奖学金1查询表头 Gjlzjxj1tit ---- 国家励志奖学金1查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjlzjxj1Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间",
					"辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "xysh",
					"xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间", Base.YXPZXY_KEY+"审核",
					"学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 国家励志奖学金1查询结果 Gjlzjxj1res ---- 国家励志奖学金1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjlzjxj1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjlzjxj1Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * 国家励志奖学金1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjlzjxj1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjlzjxj1ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * 国家励志奖学金1导出 Gjlzjxj1Exp ---- 国家励志奖学金1导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getGjlzjxj1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_gjlzjxj1", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_gjlzjxj1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存国家励志奖学金1审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxj1Shxx(Gjlzjxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjlzjxj1Shxx(model, request, shjb);
	}

	/**
	 * 获取国家励志奖学金2信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxj2xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjlzjxj2xx(pkVal);
	}

	/**
	 * 保存国家励志奖学金2申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxj2Sqxx(Gjlzjxj2Model model,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjlzjxj2Sqxx(model, request);
	}

	/**
	 * 删除国家励志奖学金2信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjlzjxj2xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delGjlzjxj2xx(cbVal, request, shjb);
	}

	/**
	 * 批量修改国家励志奖学金2审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjlzjxj2xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjlzjxj2xx(cbVal, shjg, request, shjb);
	}

	/**
	 * 国家励志奖学金2查询表头 Gjlzjxj2tit ---- 国家励志奖学金2查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjlzjxj2Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间",
					"辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "xysh",
					"xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间", Base.YXPZXY_KEY+"审核",
					"学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 国家励志奖学金2查询结果 Gjlzjxj2res ---- 国家励志奖学金2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjlzjxj2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjlzjxj2Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * 国家励志奖学金2信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjlzjxj2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjlzjxj2ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * 国家励志奖学金2导出 Gjlzjxj2Exp ---- 国家励志奖学金2导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getGjlzjxj2Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_gjlzjxj2", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_gjlzjxj2");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存国家励志奖学金2审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxj2Shxx(Gjlzjxj2Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjlzjxj2Shxx(model, request, shjb);
	}

	/**
	 * 获取学费减免1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfjm1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXfjm1xx(pkVal);
	}

	/**
	 * 保存学费减免1申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjm1Sqxx(Xfjm1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveXfjm1Sqxx(model, request);
	}

	/**
	 * 删除学费减免1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXfjm1xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delXfjm1xx(cbVal, request, shjb);
	}

	/**
	 * 批量修改学费减免1审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXfjm1xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modXfjm1xx(cbVal, shjg, request, shjb);
	}

	/**
	 * 学费减免1查询表头 Xfjm1tit ---- 学费减免1查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfjm1Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间",
					"辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "xysh",
					"xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间", Base.YXPZXY_KEY+"审核",
					"学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 学费减免1查询结果 Xfjm1res ---- 学费减免1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjm1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfjm1Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * 学费减免1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXfjm1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getXfjm1ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * 学费减免1导出 Xfjm1Exp ---- 学费减免1导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getXfjm1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_xfjm1", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_xfjm1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存学费减免1审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjm1Shxx(Xfjm1Model model, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveXfjm1Shxx(model, request, shjb);
	}

	/**
	 * 获取学费减免2信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfjm2xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXfjm2xx(pkVal);
	}

	/**
	 * 获取学费减免2审核提交信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfjm2shtjjg(String pkVal,
			String userType, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXfjm2shtjjg(pkVal, userType, shjb);
	}

	/**
	 * 获取学费减免2审核提交信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjm2Stush(String pkVal, String userType,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXfjm2Stush(pkVal, userType, shjb);
	}

	/**
	 * 保存学费减免2申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjm2Sqxx(Xfjm2Model model, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveXfjm2Sqxx(model, request, shjb);
	}

	/**
	 * 删除学费减免2信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXfjm2xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delXfjm2xx(cbVal, request, shjb);
	}

	/**
	 * 批量修改学费减免2审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXfjm2xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modXfjm2xx(cbVal, shjg, request, shjb);
	}

	/**
	 * 批量修改学费减免2审核结果(按部门审核)
	 * 
	 * @return
	 * @throws Exception
	 */
	public void audiXfjm2ForDep(XszzCommonN05ActionForm actionForm,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modXfjm2xxBybj(actionForm, request);// 修改学生审核信息
		dao.modXfjm2Bmshtjb(actionForm, request);// 修改部门审核信息
	}

	/**
	 * 批量修改学费减免2审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXfjm2xxByDepModel(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modXfjm2xxByDepModel(cbVal, shjg, request, shjb);
	}

	/**
	 * 批量修改学费减免2审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean addXfjs2shtj(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		String[] columns = { "zj", "zjz", "dm", "bm", "tjxm", "tjzt", "tjr" };
		String[] values = { "xn||xq", model.getXn() + model.getXq(),
				model.getBjdm(), "bj", "xfjm2", "1", model.getUserName() };
		StandardOperation.delete("xszz_com_bmshtjb", "zjz||dm||bm||tjxm", model
				.getXn()
				+ model.getXq() + model.getBjdm() + "bjxfjm2", request);
		return StandardOperation.insert("xszz_com_bmshtjb", columns, values,
				request);
	}

	/**
	 * 学费减免2查询表头 Xfjm2tit ---- 学费减免2查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfjm2Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc",
					"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqjmje",
					"sqsj", "pzjmje", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学期",
					"学号", "姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请减免金额",
					"申请时间", "批准减免金额", "辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc",
					"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqjmje",
					"sqsj", "pzjmje", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学期",
					"学号", "姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请减免金额",
					"申请时间", "批准减免金额", Base.YXPZXY_KEY+"审核", "学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 学费减免2查询表头 getXfjm2TitForDM ---- 学费减免2查询表头(按部门审核模式)
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfjm2TitForDM(String shjb,
			String userType) throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("xy".equalsIgnoreCase(userType)) {// 学院用户
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc",
					"bjmc", "rs", "pzjmje", "tjr", "tjsj", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学期",
					"班级名称", "人数", "批准金额", "提交人", "提交时间", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {// 学校用户
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc",
					"xymc", "rs", "pzjmje", "tjr", "tjsj", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学期",
					Base.YXPZXY_KEY+"名称", "人数", "批准金额", "提交人", "提交时间", "学校审核" };
		}

		return makeTitList(enList, cnList);
	}

	/**
	 * 学费减免2查询表头 getXfjm2TitForStush ---- 学费减免2查询表头(按部门审核模式)
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfjm2TitForStush(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};

		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "sqje", "pzje", "xysh" };
			cnList = new String[] { "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY+"名称", "专业名称",
					"班级名称", "申请时间", "申请金额", "批准金额", Base.YXPZXY_KEY+"审核" };
		} else {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "sqje", "pzje", "xxsh" };
			cnList = new String[] { "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY+"名称", "专业名称",
					"班级名称", "申请时间", "申请金额", "批准金额", "学校审核" };
		}

		return makeTitList(enList, cnList);
	}

	/**
	 * 学费减免2查询结果 Xfjm2res ---- 学费减免2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjm2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfjm2Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * 学费减免2查询结果 getXfjm2ResByFdy ---- 学费减免2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjm2ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfjm2ResByFdy(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * 学费减免2查询结果 getXfjm2ResForDM ---- 学费减免2(按部门模式查询)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjm2ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfjm2ResForDM(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * 学费减免2信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXfjm2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getXfjm2ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * 学费减免2导出 Xfjm2Exp ---- 学费减免2导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getXfjm2Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_xfjm2", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_xfjm2");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存学费减免2审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjm2Shxx(Xfjm2Model model, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveXfjm2Shxx(model, request, shjb);
	}

	/**
	 * 获取学费缓交1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhj1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXfhj1xx(pkVal);
	}

	/**
	 * 保存学费缓交1申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhj1Sqxx(Xfhj1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveXfhj1Sqxx(model, request);
	}

	/**
	 * 删除学费缓交1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXfhj1xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delXfhj1xx(cbVal, request, shjb);
	}

	/**
	 * 批量修改学费缓交1审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXfhj1xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modXfhj1xx(cbVal, shjg, request, shjb);
	}

	/**
	 * 学费缓交1查询表头 Xfhj1tit ---- 学费缓交1查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfhj1Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "hjje", "hjqx",
					"sqsj", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "缓交金额", "缓交期限",
					"申请时间", "辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "hjje", "hjqx",
					"sqsj", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "缓交金额", "缓交期限",
					"申请时间", Base.YXPZXY_KEY+"审核", "学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 学费缓交1查询结果 Xfhj1res ---- 学费缓交1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhj1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfhj1Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * 学费缓交1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXfhj1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getXfhj1ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * 学费缓交1导出 Xfhj1Exp ---- 学费缓交1导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getXfhj1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_xfhj1", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_xfhj1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存学费缓交1审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhj1Shxx(Xfhj1Model model, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveXfhj1Shxx(model, request, shjb);
	}

	/**
	 * 获取学费缓交2信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhj2xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXfhj2xx(pkVal);
	}

	/**
	 * 获取广州大学欠费信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getGzdxQfxx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGzdxQfxx(pkVal);
	}

	/**
	 * 设置广州大学欠费相关信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getXsqfInfo(String xh, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		String[] colList = new String[] { "yjxfje", "sjjfje", "qxfje",
				"yjzsfje", "sjjzsfje", "qzsfje", "yjqtfyje", "sjqtfyje",
				"qjqtfyje" };
		
		List<HashMap<String, String>> list = dao.getGzdxQfxx(xh, colList);
		float sum=0.0f;
		for(int i=0;i<colList.length;i++){
			sum=0.0f;
			for(int j=0;j<list.size();j++){
				HashMap<String,String>hashMap=list.get(j);
				if(!Base.isNull(hashMap.get(colList[i]))){
					sum+=Float.parseFloat(hashMap.get(colList[i]));
				}
			}
			request.setAttribute(colList[i], sum);
		}
	}

	/**
	 * 保存学费缓交2申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhj2Sqxx(Xfhj2Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveXfhj2Sqxx(model, request);
	}

	/**
	 * 删除学费缓交2信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXfhj2xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delXfhj2xx(cbVal, request, shjb);
	}

	/**
	 * 批量修改学费缓交2审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXfhj2xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modXfhj2xx(cbVal, shjg, request, shjb);
	}

	/**
	 * 学费缓交2查询表头 Xfhj2tit ---- 学费缓交2查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfhj2Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "hjxf",
					"hjzsf", "hjxzfqx", "sqsj", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "缓缴学费",
					"缓缴住宿费", "缓交期限", "申请时间", "辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "hjxf",
					"hjzsf", "hjxzfqx", "sqsj", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "缓缴学费",
					"缓缴住宿费", "缓交期限", "申请时间", Base.YXPZXY_KEY+"审核", "学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 学费缓交2查询结果 Xfhj2res ---- 学费缓交2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhj2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfhj2Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * 学费缓交2信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXfhj2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getXfhj2ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * 学费缓交2导出 Xfhj2Exp ---- 学费缓交2导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getXfhj2Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_xfhj2", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_xfhj2");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 学费缓交2无故欠费名单表头 getXfhj2WgqfTit ---- 学费缓交2无故欠费名表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfhj2WgqfTit() throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		enList = new String[] { "xymc", "zymc", "xh", "xm", "qxfqj", "qxfzje" };
		cnList = new String[] { Base.YXPZXY_KEY, "专业", "学号", "姓名", "欠学费区间", "欠学费金额" };
		return makeTitList(enList, cnList);
	}

	/**
	 * 学费缓交2无学费缓缴及无故欠费统计表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfhj2HjtjTit() throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		enList = new String[] { "xh", "xy", "tgsqzrs", "tghjsqdfknsrs",
				"wgqfrs", "qfrs", "bz" };
		cnList = new String[] { "序号", Base.YXPZXY_KEY, "通过申请总人数", "通过缓缴申请的非困难生人数",
				"无故欠费人数", "欠费人数", "备注" };
		return makeTitList(enList, cnList);
	}

	/**
	 * 学费缓交2无故欠费名单结果 Xfhj2Wgqfres ---- 学费缓交2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhj2WgqfRes(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = dao.getXfhj2WgqfRes(queryModel, request,
				actionForm, shjb);
		return resList;
	}

	/**
	 * 学费缓交2无学费缓缴及无故欠费统计结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhj2HjtjRes(String xn) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = dao.getXfhj2HjtjRes(xn);
		return resList;
	}

	/**
	 * 保存学费缓交2审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhj2Shxx(Xfhj2Model model, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveXfhj2Shxx(model, request, shjb);
	}

	/**
	 * 获取困难生临时补助1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnslsbz1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnslsbz1xx(pkVal);
	}

	/**
	 * 保存困难生临时补助1申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnslsbz1Sqxx(Knslsbz1Model model,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnslsbz1Sqxx(model, request);
	}

	/**
	 * 打印困难生临时补助1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void knslsbz1Dy(WritableWorkbook wwb, String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<HashMap<String, String>> list = dao.getKnslsbz1Dyxx(pkVal);

		WritableSheet ws = wwb.getSheet(0);
		Label titleCell = new Label(0, 2, StandardOperation.getXxmc()
				+ "困难学生临时补助审批表");
		WritableCellFormat wcFormat = new WritableCellFormat();
		WritableFont font = new WritableFont(WritableFont.ARIAL, 18);
		wcFormat.setFont(font);
		wcFormat.setAlignment(Alignment.CENTRE);
		wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		titleCell.setCellFormat(wcFormat);
		ws.addCell(titleCell);

		wcFormat = new WritableCellFormat();
		font = new WritableFont(WritableFont.ARIAL, 10);
		wcFormat.setFont(font);
		wcFormat.setWrap(true);
		wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcFormat.setAlignment(Alignment.CENTRE);
		wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

		String[] valKay = { "xymc", "bjmc", "xm", "xh", "kndj" };
		int i = 4;
		int sqzje = 0;
		for (HashMap<String, String> hs : list) {
			for (int j = 0; j < valKay.length; j++) {
				titleCell = new Label(j, i, hs.get(valKay[j]));
				titleCell.setCellFormat(wcFormat);
				ws.addCell(titleCell);
			}

			titleCell = new Label(5, i, hs.get("sqly"));
			titleCell.setCellFormat(wcFormat);
			ws.addCell(titleCell);
			ws.mergeCells(5, i, 6, i);

			titleCell = new Label(7, i, hs.get("sqje"));
			titleCell.setCellFormat(wcFormat);
			ws.addCell(titleCell);
			ws.mergeCells(7, i, 8, i);

			i++;
			sqzje += Integer.parseInt(hs.get("sqje"));
		}

		wcFormat = new WritableCellFormat();
		font = new WritableFont(WritableFont.ARIAL, 11);
		wcFormat.setFont(font);
		wcFormat.setWrap(true);
		wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcFormat.setAlignment(Alignment.CENTRE);
		wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

		titleCell = new Label(0, i, "合计金额");
		titleCell.setCellFormat(wcFormat);
		ws.addCell(titleCell);

		titleCell = new Label(1, i, sqzje + "    元");
		titleCell.setCellFormat(wcFormat);
		ws.addCell(titleCell);
		ws.mergeCells(1, i, 3, i);

		String[] sT = { Base.YXPZXY_KEY+"意见", "学生资助管理     中心复核意见", "学工部领导审核", "学工部领导审批" };
		int h = 0;
		for (int n = 0; n < sT.length; n++) {
			titleCell = new Label(0, i + h + 1, sT[n]);
			titleCell.setCellFormat(wcFormat);
			ws.addCell(titleCell);
			ws.mergeCells(0, i + h + 1, 0, i + h + 2);
			h += 2;
		}

		wcFormat = new WritableCellFormat();
		font = new WritableFont(WritableFont.ARIAL, 11);
		wcFormat.setFont(font);
		wcFormat.setWrap(true);
		wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcFormat.setAlignment(Alignment.LEFT);
		wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

		titleCell = new Label(
				4,
				i,
				"经办人：                                                                                  年        月        日");
		titleCell.setCellFormat(wcFormat);
		ws.addCell(titleCell);
		ws.mergeCells(4, i, 8, i);

		wcFormat = new WritableCellFormat();
		font = new WritableFont(WritableFont.ARIAL, 11);
		wcFormat.setFont(font);
		wcFormat.setWrap(true);
		wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcFormat.setAlignment(Alignment.CENTRE);
		wcFormat.setVerticalAlignment(VerticalAlignment.BOTTOM);

		for (int n = 1; n < 8;) {
			titleCell = new Label(
					1,
					i + n,
					"                                                                                                                                          年        月        日");
			titleCell.setCellFormat(wcFormat);
			ws.addCell(titleCell);
			ws.mergeCells(1, i + n, 8, i + n + 1);
			n += 2;
		}

		wcFormat = new WritableCellFormat();
		font = new WritableFont(WritableFont.ARIAL, 9);
		wcFormat.setFont(font);
		wcFormat.setWrap(true);
		wcFormat.setBorder(Border.TOP, BorderLineStyle.THIN);
		wcFormat.setAlignment(Alignment.LEFT);
		wcFormat.setVerticalAlignment(VerticalAlignment.BOTTOM);

		titleCell = new Label(0, i + 9, "           备注：各"+Base.YXPZXY_KEY+"可根据具体人数调整表格中学生信息栏的行数");
		titleCell.setCellFormat(wcFormat);
		ws.addCell(titleCell);
		ws.mergeCells(0, i + 9, 8, i + 9);

		ExcelMethods.submitWritableWorkbookOperations(wwb);// 输出到客户端
	}

	/**
	 * 删除困难生临时补助1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnslsbz1xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delKnslsbz1xx(cbVal, request, shjb);
	}

	/**
	 * 批量修改困难生临时补助1审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnslsbz1xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modKnslsbz1xx(cbVal, shjg, request, shjb);
	}

	/**
	 * 困难生临时补助1查询表头 Knslsbz1tit ---- 困难生临时补助1查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnslsbz1Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "kndj", "sqje",
					"sqsj", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "困难等级",
					"申请补助额度", "申请时间", "辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "kndj", "sqje",
					"sqsj", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "困难等级",
					"申请补助额度", "申请时间", Base.YXPZXY_KEY+"审核", "学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 困难生临时补助1查询结果 Knslsbz1res ---- 困难生临时补助1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnslsbz1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnslsbz1Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * 困难生临时补助1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnslsbz1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnslsbz1ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * 困难生临时补助1导出 Knslsbz1Exp ---- 困难生临时补助1导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getKnslsbz1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_knslsbz1", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_knslsbz1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存困难生临时补助1审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnslsbz1Shxx(Knslsbz1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnslsbz1Shxx(model, request, shjb);
	}

	/**
	 * 获取临时补助2信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsbz2xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getLsbz2xx(pkVal);
	}

	/**
	 * 保存临时补助2申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsbz2Sqxx(Lsbz2Model model, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveLsbz2Sqxx(model, request, shjb);
	}

	/**
	 * 删除临时补助2信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delLsbz2xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delLsbz2xx(cbVal, request, shjb);
	}

	/**
	 * 批量修改临时补助2审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modLsbz2xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modLsbz2xx(cbVal, shjg, request, shjb);
	}

	/**
	 * 临时补助2查询表头 Lsbz2tit ---- 临时补助2查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsbz2Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc",
					"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqje",
					"sqsj", "pzje", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学期",
					"学号", "姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请金额",
					"申请时间", "批准金额", "辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc",
					"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqje",
					"sqsj", "pzje", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学期",
					"学号", "姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请金额",
					"申请时间", "批准金额", Base.YXPZXY_KEY+"审核", "学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 临时补助2查询表头 Lsbz2tit ---- 临时补助2查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsbz2TitForDM(String shjb,
			String userType) throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("xy".equalsIgnoreCase(userType)) {
			if ("3".equalsIgnoreCase(shjb)) {
				enList = new String[] { "disabled", "bgcolor", "pk", "xn",
						"xqmc", "bjmc", "rs", "pzje", "tjr", "tjsj", "xysh",
						"xxsh" };
				cnList = new String[] { "disabled", "bgcolor", "主键", "学年",
						"学期", "班级名称", "人数", "批准金额", "提交人", "提交时间", Base.YXPZXY_KEY+"审核",
						"学校审核" };
			} else {
				enList = new String[] { "disabled", "bgcolor", "pk", "xn",
						"xqmc", "bjmc", "rs", "pzje", "tjr", "tjsj", "xysh",
						"xxsh" };
				cnList = new String[] { "disabled", "bgcolor", "主键", "学年",
						"学期", "班级名称", "人数", "批准金额", "提交人", "提交时间", Base.YXPZXY_KEY+"审核",
						"学校审核" };
			}
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc",
					"xymc", "rs", "pzje", "tjr", "tjsj", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学期",
					Base.YXPZXY_KEY+"名称", "人数", "批准金额", "提交人", "提交时间", "学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 临时补助2查询表头 getLsbz2TitForStush ---- 临时补助2查询表头(按部门审核模式)
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsbz2TitForStush(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};

		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "sqje", "pzje", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY+"名称", "专业名称",
					"班级名称", "申请时间", "申请金额", "审批金额", "辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "sqje", "pzje", "xysh", "xxsh" };
			cnList = new String[] { "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY+"名称", "专业名称",
					"班级名称", "申请时间", "申请金额", "审批金额", Base.YXPZXY_KEY+"审核", "学校审核" };
		}

		return makeTitList(enList, cnList);
	}

	/**
	 * 获取临时补助2审核提交信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsbz2shtjjg(String pkVal,
			String userType, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getLsbz2shtjjg(pkVal, userType, shjb);
	}

	/**
	 * 获取临时补助2审核提交信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbz2Stush(String pkVal, String userType,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getLsbz2Stush(pkVal, userType, shjb);
	}

	/**
	 * 批量修改临时补助2审核结果(按部门审核)
	 * 
	 * @return
	 * @throws Exception
	 */
	public void audiLsbz2ForDep(XszzCommonN05ActionForm actionForm,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modLsbz2xxBybj(actionForm, request);// 修改学生审核信息
		dao.modLsbz2Bmshtjb(actionForm, request);// 修改部门审核信息
	}

	/**
	 * 批量修改临时补助2审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modLsbz2xxByDepModel(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modLsbz2xxByDepModel(cbVal, shjg, request, shjb);
	}

	/**
	 * 批量修改临时补助2审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean addLsbz2shtj(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		String[] columns = { "zj", "zjz", "dm", "bm", "tjxm", "tjzt", "tjr" };
		String[] values = { "xn||xq", model.getXn() + model.getXq(),
				model.getBjdm(), "bj", "lsbz2", "1", model.getUserName() };
		StandardOperation.delete("xszz_com_bmshtjb", "zjz||dm||bm||tjxm", model
				.getXn()
				+ model.getXq() + model.getBjdm() + "bjlsbz2", request);
		return StandardOperation.insert("xszz_com_bmshtjb", columns, values,
				request);
	}

	/**
	 * 临时补助2查询结果 getXfjm2ResForDM ---- 临时补助2(按部门模式查询)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbz2ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getLsbz2ResForDM(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * 临时补助2查询结果 Lsbz2res ---- 临时补助2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbz2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getLsbz2Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * 临时补助2查询结果 getLsbz2ResByFdy ---- 临时补助2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbz2ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getLsbz2ResByFdy(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * 临时补助2信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getLsbz2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getLsbz2ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * 临时补助2导出 Lsbz2Exp ---- 临时补助2导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getLsbz2Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_knslsbz2", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_knslsbz2");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存临时补助2审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsbz2Shxx(Lsbz2Model model, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveLsbz2Shxx(model, request, shjb);
	}

	/**
	 * 根据项目代码得到外设助学金项目名称
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getWszxjmc(String xmdm) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getWszxjmc(xmdm);
	}

	/**
	 * 获取外设助学金1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWszxj1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getWszxj1xx(pkVal);
	}

	/**
	 * 保存外设助学金1申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxj1Sqxx(Wszxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveWszxj1Sqxx(model, request, shjb);
	}

	/**
	 * 删除外设助学金1信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delWszxj1xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delWszxj1xx(cbVal, request, shjb);
	}

	/**
	 * 批量修改外设助学金1审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modWszxj1xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modWszxj1xx(cbVal, shjg, request, shjb);
	}

	/**
	 * 外设助学金1查询表头 Wszxj1tit ---- 外设助学金1查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWszxj1Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqje", "sqsj",
					"pzje", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请金额", "申请时间",
					"批准金额", "辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"zxjdj", "zxjje", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请金额", "申请时间",
					"批准金额", Base.YXPZXY_KEY+"审核", "学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 外设助学金1查询结果 Wszxj1res ---- 外设助学金1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxj1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getWszxj1Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * 外设助学金1查询结果 Wszxj1res ---- 外设助学金1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxj1ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getWszxj1ResByFdy(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * 外设助学金1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getWszxj1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getWszxj1ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * 外设助学金1查询表头 getWszxj1TitForDM ---- 外设助学金1查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWszxj1TitForDM(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "bjmc",
					"rs", "pzje", "tjr", "tjsj", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "班级名称",
					"人数", "批准金额", "提交人", "提交时间", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xymc",
					"rs", "pzje", "tjr", "tjsj", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", Base.YXPZXY_KEY+"名称",
					"人数", "批准金额", "提交人", "提交时间", "学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 外设助学金1查询表头 getWszxj1TitForStush ---- 外设助学金1查询表头(按部门审核模式)
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWszxj1TitForStush(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};

		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "sqje", "pzje", "xysh", "xxsh" };
			cnList = new String[] { "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY+"名称", "专业名称",
					"班级名称", "申请时间", "申请金额", "批准金额", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "sqje", "pzje", "xxsh" };
			cnList = new String[] { "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY+"名称", "专业名称",
					"班级名称", "申请时间", "申请金额", "批准金额", "学校审核" };
		}

		return makeTitList(enList, cnList);
	}

	/**
	 * 获取外设助学金1审核提交信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWszxj1shtjjg(String pkVal,
			String userType, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getWszxj1shtjjg(pkVal, userType, shjb);
	}

	/**
	 * 获取外设助学金1审核提交信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxj1Stush(String pkVal, String userType,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getWszxj1Stush(pkVal, userType, shjb);
	}

	/**
	 * 批量修改外设助学金1审核结果(按部门审核)
	 * 
	 * @return
	 * @throws Exception
	 */
	public void audiWszxj1ForDep(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modWszxj1xxBybj(model, request);// 修改学生审核信息
		dao.modWszxj1Bmshtjb(model, request);// 修改部门审核信息
	}

	/**
	 * 批量修改外设助学金1审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modWszxj1xxByDepModel(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modWszxj1xxByDepModel(cbVal, shjg, request, shjb);
	}

	/**
	 * 批量修改外设助学金1审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean addWszxj1shtj(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		String[] columns = { "zj", "zjz", "dm", "bm", "tjxm", "tjzt", "tjr" };
		String[] values = { "xn||zxjdm", model.getXn() + model.getZxjdm(),
				model.getBjdm(), "bj", "wszxj1", "1", model.getUserName() };
		StandardOperation.delete("xszz_com_bmshtjb", "zjz||dm||bm||tjxm", model
				.getXn()
				+ model.getZxjdm() + model.getBjdm() + "bjwszxj1", request);
		return StandardOperation.insert("xszz_com_bmshtjb", columns, values,
				request);
	}

	/**
	 * 外设助学金1查询结果 getWszxj1ResForDM ---- 外设助学金1(按部门模式查询)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxj1ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getWszxj1ResForDM(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * 外设助学金1导出 Wszxj1Exp ---- 外设助学金1导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getWszxj1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_wszxj1", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_wszxj1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 得到外设助学金金额列表
	 * 
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getWszxjJeList(String zxjdm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getWszxjJeList(zxjdm);
	}

	/**
	 * 保存外设助学金1审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxj1Shxx(Wszxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveWszxj1Shxx(model, request, shjb);
	}

	/**
	 * 获取外设助学金2信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWszxj2xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getWszxj2xx(pkVal);
	}

	/**
	 * 保存外设助学金2申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxj2Sqxx(Wszxj2Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveWszxj2Sqxx(model, request);
	}

	/**
	 * 删除外设助学金2信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delWszxj2xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delWszxj2xx(cbVal, request, shjb);
	}

	/**
	 * 批量修改外设助学金2审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modWszxj2xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modWszxj2xx(cbVal, shjg, request, shjb);
	}

	/**
	 * 外设助学金2查询表头 Wszxj2tit ---- 外设助学金2查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWszxj2Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqje", "sqsj",
					"pzje", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请金额", "申请时间",
					"批准金额", "辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"zxjdj", "zxjje", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "主键", "学年", "学号",
					"姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请金额", "申请时间",
					"批准金额", Base.YXPZXY_KEY+"审核", "学校审核" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * 外设助学金2查询结果 Wszxj2res ---- 外设助学金2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxj2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getWszxj2Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * 外设助学金2信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getWszxj2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getWszxj2ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * 外设助学金2导出 Wszxj2Exp ---- 外设助学金2导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getWszxj2Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_wszxj2", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_wszxj2");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * 保存外设助学金2审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxj2Shxx(Wszxj2Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveWszxj2Shxx(model, request, shjb);
	}

	/**
	 * 获取辅导员审核提交表信息
	 * 
	 * @param String
	 *            tableName
	 * @param XszzCommonN05ActionForm
	 *            model
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getFdyshtjbxx(String xm, String bjdm,
			String zjz) {
		dao = new XszzCommonN05DAO();
		return dao.selectFdyshtjb(xm, bjdm, zjz);
	}

	/**
	 * 获得学生欠费列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXsqfxxList(String userType,
			XszzCommonN05ActionForm form, String[] colList) {
		dao = new XszzCommonN05DAO();
		return dao.getXsqfxxList(userType, form, colList);
	}

	/**
	 * 获取审核查询中要自定义的字段
	 * 
	 * @param yhlx
	 * @return String
	 */
	public String getCustomAudiColumn(String yhlx) {
		StringBuilder sb = new StringBuilder();
		if (yhlx.equalsIgnoreCase("xy")) {
			sb
					.append(" (case when xxsh='未审核' then '' else 'disabled' end) disabled,");
			sb
					.append(" (case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
		} else if (yhlx.equalsIgnoreCase("fdy")) {
			sb
					.append(" (case when xysh='未审核' then '' else 'disabled' end) disabled, ");
			sb
					.append(" (case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
		} else {
			sb.append(" '' disabled, ");
			sb
					.append(" (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
		}

		return sb.toString();
	}

	/**
	 * 判断是否是困难生
	 * 
	 * @param xh
	 * @return boolean
	 */
	public boolean isKns(String xh) {
		DAO dao = DAO.getInstance();
		return dao.isKns(xh);
	}

	/**
	 * 获取学生在校的所有学年
	 * 
	 * @param xh
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getStuZxxn(String xh) {
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		XsxxglService service = new XsxxglService();
		HashMap<String, String> xsxx = service.selectStuinfo(xh);
		String rxsj = xsxx.get("rxrq");

		rxsj = StringUtils.isNotNull(rxsj) ? rxsj.substring(0, 4) : xsxx
				.get("nj");
		int length = 3;// 最多三学年
		for (int i = 0; i < length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			if (StringUtils.isNotNull(rxsj)) {
				map.put("xn", StringUtils.joinStr(rxsj, "-", (Integer
						.parseInt(rxsj) + 1)
						+ ""));
				rs.add(map);
				rxsj = (Integer.parseInt(rxsj) + 1) + "";
			}
		}

		return rs;
	}

	/**
	 * 宁波天一职业技术学院勤工助学工资报表打印
	 * 
	 * @param wwb
	 * @return void
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public void printNbtyKnsrd2(QueryModel queryModel,
			HttpServletRequest request, WritableWorkbook wwb)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		dao = new XszzCommonN05DAO();
		String[] colList = { "xn", "xm", "xh", "xb", "sfzh", "xysh", "xydm",
				"xxsh", "nj", "zydm", "bjdm" };
		StringBuffer strBuffer = MakeQuery.makeQuery(colList, queryModel);
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			strBuffer.append("and xydm=" + userDep);
		}
		List<Map<String, String>> list = dao.getKnsrd2(strBuffer);
		try {
			// 创建xls中SHEET对象
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			// 设置对齐方式
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
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
//			 设置表格边框
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = list.get(i);
				ws.addCell(new Label(0, 3 + i, String.valueOf(i + 1),
								wcfTytle));
				ws.addCell(new Label(1, 3 + i, map.get("xm"), wcfTytle));
				ws.addCell(new Label(2, 3 + i, map.get("xb"), wcfTytle));
				ws.addCell(new Label(3, 3 + i, map.get("zymc") + map.get("nj")
						+ map.get("bjmc"), wcfTytle));
				if ("特殊困难".equalsIgnoreCase(map.get("xxsh"))) {
					ws.addCell(new Label(4, 3 + i, "√", wcfTytle));
					ws.addCell(new Label(5, 3 + i, "", wcfTytle));
				} else if ("困难".equalsIgnoreCase(map.get("xxsh"))) {
					ws.addCell(new Label(4, 3 + i, "", wcfTytle));
					ws.addCell(new Label(5, 3 + i, "√", wcfTytle));
					ws.addCell(new Label(6, 3 + i, "", wcfTytle));
				} else if ("一般困难".equalsIgnoreCase(map.get("xxsh"))) {
					ws.addCell(new Label(4, 3 + i, "", wcfTytle));
					ws.addCell(new Label(5, 3 + i, "", wcfTytle));
					ws.addCell(new Label(6, 3 + i, "√", wcfTytle));
				} else {
					ws.addCell(new Label(4, 3 + i, "", wcfTytle));
					ws.addCell(new Label(5, 3 + i, "", wcfTytle));
					ws.addCell(new Label(6, 3 + i, "", wcfTytle));
				}
				ws.addCell(new Label(7, 3 + i, "", wcfTytle));
				ws.addCell(new Label(8, 3 + i, "", wcfTytle));
				ws.addCell(new Label(9, 3 + i, "", wcfTytle));
				ws.addCell(new Label(10, 3 + i, "", wcfTytle));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 宁波天一职业技术学院国家奖学金1
	 * 
	 * @param wwb
	 * @return void
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public void printNbtyGjjxj1(QueryModel queryModel,
			HttpServletRequest request, WritableWorkbook wwb)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		dao = new XszzCommonN05DAO();
		String[] colList = { "xn","xh","xm","sfzh", "nj", "xb", "zydm", "xydm", "bjdm"};
		StringBuffer strBuffer = MakeQuery.makeQuery(colList, queryModel);
		List<Map<String, String>> list = dao.getGjjxj1(strBuffer);
		try {
			// 创建xls中SHEET对象
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			// 设置对齐方式
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(16);
			wcfTytle.setFont(wfTytle);
			ws.mergeCells(1, 0,1, 9);
			String xhx="";
			if("".equalsIgnoreCase(queryModel.getXn())){
				xhx="_________";
			}
			ws.addCell(new Label(0,1,queryModel.getXn()+xhx+"学年度普通高等学校国家奖学金获奖学生初审名单表",wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setWrap(true);
//			 设置表格边框
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = list.get(i);
				ws.addCell(new Label(0, 4 + i, String.valueOf(i + 1),
								wcfTytle));
				ws.addCell(new Label(1, 4 + i, map.get("xm"), wcfTytle));
				ws.addCell(new Label(2, 4 + i, map.get("sfzh"), wcfTytle));
				ws.addCell(new Label(3, 4 + i, map.get("xymc"),wcfTytle));
				ws.addCell(new Label(4, 4 + i, map.get("zymc"), wcfTytle));
				ws.addCell(new Label(5, 4 + i, map.get("xh"), wcfTytle));
				ws.addCell(new Label(6, 4 + i, map.get("xb"), wcfTytle));
				ws.addCell(new Label(7, 4 + i,map.get("mzmc"), wcfTytle));
				ws.addCell(new Label(8, 4 + i,map.get("rxrq"), wcfTytle));
			}
			WritableCellFormat endwcfTytle = new WritableCellFormat();
			WritableFont wf=new WritableFont(WritableFont.ARIAL);
			wf.setBoldStyle(WritableFont.BOLD);
			endwcfTytle.setFont(wf);
			ws.mergeCells(0, 4+list.size(), 3, 4+list.size());
			ws.addCell(new Label(0,4+list.size(),"（注：此表供高校填写）",endwcfTytle));
			endwcfTytle = new WritableCellFormat();
			wf=new WritableFont(WritableFont.ARIAL);
			endwcfTytle.setFont(wf);
			ws.mergeCells(0, 5+list.size(), 1, 5+list.size());
			ws.addCell(new Label(0,5+list.size(),"经办人:",endwcfTytle));
			ws.mergeCells(2, 5+list.size(), 3, 5+list.size());
			ws.addCell(new Label(2,5+list.size(),"联系电话：",endwcfTytle));
			ws.mergeCells(4, 5+list.size(), 5, 5+list.size());
			ws.addCell(new Label(4,5+list.size(),"传真：",endwcfTytle));
			ws.mergeCells(6, 5+list.size(), 7, 5+list.size());
			ws.addCell(new Label(6,5+list.size(),"电子邮箱：",endwcfTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 宁波天一职业技术学院国家奖学金1汇总
	 * 2010.9.21 qlj
	 * @param wwb
	 * @return void
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public void printNbtyGjjxj1hz(QueryModel queryModel,
			HttpServletRequest request, WritableWorkbook wwb)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		dao = new XszzCommonN05DAO();
		String[] colList = { "xn","xh","xm","sfzh", "nj", "xb", "zydm", "xydm", "bjdm"};
		StringBuffer strBuffer = MakeQuery.makeQuery(colList, queryModel);
		List<Map<String, String>> list = dao.getGjjxj1(strBuffer);
		try {
			// 创建xls中SHEET对象
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			// 设置对齐方式
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(18);
			wcfTytle.setFont(wfTytle);
			ws.mergeCells(1, 0, 1, 9);
			String xhx="";
			if("".equalsIgnoreCase(queryModel.getXn())){
				xhx="_________";
			}
			ws.addCell(new Label(0,1,queryModel.getXn()+xhx+"学年度普通高等学校国家奖学金获奖学生初审名单汇总表",wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setWrap(true);
//			 设置表格边框
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = list.get(i);
				ws.addCell(new Label(0, 4 + i, String.valueOf(i + 1),
								wcfTytle));
				ws.addCell(new Label(1, 4 + i, map.get("xm"), wcfTytle));
				ws.addCell(new Label(2, 4 + i, map.get("sfzh"), wcfTytle));
				ws.addCell(new Label(3, 4 + i, map.get("xymc"),wcfTytle));
				ws.addCell(new Label(4, 4+ i, map.get("zymc"), wcfTytle));
				ws.addCell(new Label(5, 4 + i, map.get("bjmc"), wcfTytle));
				ws.addCell(new Label(6, 4 + i, map.get("xh"), wcfTytle));
				ws.addCell(new Label(7, 4 + i, map.get("xb"), wcfTytle));
				ws.addCell(new Label(8, 4+ i,map.get("mzmc"), wcfTytle));
				ws.addCell(new Label(9, 4 + i,map.get("rxrq"), wcfTytle));
			}
			WritableCellFormat endwcfTytle = new WritableCellFormat();
			WritableFont wf=new WritableFont(WritableFont.ARIAL);
			wf.setBoldStyle(WritableFont.BOLD);
			endwcfTytle.setFont(wf);
			ws.mergeCells(0, 4+list.size(), 5, 4+list.size());
			ws.addCell(new Label(0,4+list.size(),"注：此表供中央有关部门、各省（自治区、直辖市）、计划单列市教育部门填写",endwcfTytle));
			 endwcfTytle = new WritableCellFormat();
			wf=new WritableFont(WritableFont.ARIAL);
			endwcfTytle.setFont(wf);
			ws.mergeCells(0, 5+list.size(), 1, 5+list.size());
			ws.addCell(new Label(0,5+list.size(),"经办人:",endwcfTytle));
			ws.mergeCells(2, 5+list.size(), 3, 5+list.size());
			ws.addCell(new Label(2,5+list.size(),"联系电话：",endwcfTytle));
			ws.mergeCells(4, 5+list.size(), 5, 5+list.size());
			ws.addCell(new Label(4,5+list.size(),"传真：",endwcfTytle));
			ws.mergeCells(6, 5+list.size(), 7, 5+list.size());
			ws.addCell(new Label(6,5+list.size(),"电子邮箱：",endwcfTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 查询学生学年项目金额
	 * 
	 * @param xnList
	 * @param xh
	 * @param xmmc
	 * @return Map<String, String>
	 */
	public Map<String, String> queryXnje(List<HashMap<String, String>> xnList,
			String xh, String xmmc) {
		XszzCommonN05DAO dao = new XszzCommonN05DAO();
		return dao.selectXnje(xnList, xh, xmmc);
	}

	public List<HashMap<String, String>> getGjzxjdj() {
		String[] output = new String[] { "zxjdm", "zxjdj" };
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				"xszz_com_gjzxj1dmb", "", new String[] {}, output, "");
//		List<LabelValueBean> options = new ArrayList<LabelValueBean>();
//		
//		if(list != null){
//			for(int i=0;i<list.size();i++){
//				HashMap<String, String> map = list.get(i);
//				options.add(new LabelValueBean(map.get("zxjdj"),map.get("zxjdj")));
//			}
//		}
		
		return list;
	}
	
	/**
	 * 获得历年资助信息列表
	 * 
	 * @author luojw
	 */
	public void getLnZzInfoList(XszzCommonN05ActionForm model,
			HttpServletRequest request) {
		List<HashMap<String, String>> list = dao.getLnZzInfoList(model);

		request.setAttribute("lnzzInfo", list);
	}
	
	/**
	 * 资助申请条件验证
	 * 
	 * @author luojw
	 * @throws SQLException 
	 */
	public String zzsqTjyz(XszzCommonN05ActionForm model) throws SQLException {

		dao = new XszzCommonN05DAO();
		
		// 返回信息
		String message = "";
		// 项目代码
		String xmdm = model.getXmdm();
		// 学号
		String xh = model.getXh();
		// 学年
		String xn = model.getXn();

		String tableName = "xszz_zztjb";

		String[] colList = new String[] { "tjzd", "tjlx", "tjz" };
		String query = " where xmdm = ? ";

		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				tableName, query, new String[] { xmdm }, colList, "");

		if (list != null && list.size() > 0) {
			
			for (int i = 0; i < list.size(); i++) {
				
				HashMap<String, String> map = list.get(i);
				// 条件字段
				String tjzd = map.get("tjzd");
				// 条件类型
				String tjlx = map.get("tjlx");
				// 条件值
				String tjz = map.get("tjz");

				// 无违纪情况
				if ("nowj".equalsIgnoreCase(tjzd) && "是".equalsIgnoreCase(tjz)) {
					boolean isWj = dao.isWj(model);

					if (isWj) {
						message = "申请人本学年有违纪情况，无法申请，请确认。";
						return message;
					}
				}

				// 无不及格科目
				if ("nobjg".equalsIgnoreCase(tjzd) && "是".equalsIgnoreCase(tjz)) {

					boolean isbjg = dao.isBjg(model);

					if (isbjg) {
						message = "申请人本学年有不及格科目，无法申请，请确认。";
						return message;
					}
				}
				
				// 平均成绩
				if ("avgcj".equalsIgnoreCase(tjzd)) {

					String avgCj = dao.getAvgCj(model);

					if (!Base.isNull(avgCj)) {

						float tjcj = Float.parseFloat(tjz);
						float cj = Float.parseFloat(avgCj);
						boolean flag = false;

						if (">".equalsIgnoreCase(tjlx) && cj <= tjcj) {
							flag = true;
						} else if (">=".equalsIgnoreCase(tjlx) && cj < tjcj) {
							flag = true;
						} else if ("=".equalsIgnoreCase(tjlx) && cj != tjcj) {
							flag = true;
						} else if ("<".equalsIgnoreCase(tjlx) && cj >= tjcj) {
							flag = true;
						} else if ("<=".equalsIgnoreCase(tjlx) && cj > tjcj) {
							flag = true;
						}

						if (flag) {
							message = "申请人本学年平均成绩未达到要求，无法申请，请确认。";
							return message;
						}
					}else{
						message = "申请人本学年无课程成绩，无法申请，请确认。";
						return message;
					}
				}
				
				// 每一科成绩
				if ("mincj".equalsIgnoreCase(tjzd)) {

					List<String> cjList = dao.getXsCj(model);

					if (cjList != null && cjList.size() > 0) {

						for (int j = 0; j < cjList.size(); j++) {
							
							float tjcj = Float.parseFloat(tjz);
							float cj = Float.parseFloat(cjList.get(j));
							boolean flag = false;

							if (">".equalsIgnoreCase(tjlx) && cj <= tjcj) {
								flag = true;
							} else if (">=".equalsIgnoreCase(tjlx) && cj < tjcj) {
								flag = true;
							} else if ("=".equalsIgnoreCase(tjlx) && cj != tjcj) {
								flag = true;
							} else if ("<".equalsIgnoreCase(tjlx) && cj >= tjcj) {
								flag = true;
							} else if ("<=".equalsIgnoreCase(tjlx) && cj > tjcj) {
								flag = true;
							}

							if (flag) {
								message = "申请人本学年课程成绩未达到要求，无法申请，请确认。";
								return message;
							}
						}
					}else{
						message = "申请人本学年无课程成绩，无法申请，请确认。";
						return message;
					}
				}
				
				// 综测排名
				if ("zcpm".equalsIgnoreCase(tjzd)) {

					DAO tyDao = DAO.getInstance();

					// 班级排名
					String bjpm = tyDao.getOneValue("view_gzdx_zhszcp", "bjpm",
							"xh||xn", xh + xn);
					
					if (!Base.isNull(bjpm)) {
						// 班级人数
						String bjrs = dao.getBjrs(model);
						// 设置排名
						float szpm = Math.round(Float.parseFloat(bjrs)
								* Float.parseFloat(tjz) / 100);
						// 真实排名
						float pm = Base.isNull(bjpm) ? 0 : Float
								.parseFloat(bjpm);
						boolean flag = true;

						if (">".equalsIgnoreCase(tjlx) && pm < szpm) {
							flag = false;
						} else if (">=".equalsIgnoreCase(tjlx) && pm <= szpm) {
							flag = false;
						} else if ("=".equalsIgnoreCase(tjlx) && pm == szpm) {
							flag = false;
						} else if ("<".equalsIgnoreCase(tjlx) && pm > szpm) {
							flag = false;
						} else if ("<=".equalsIgnoreCase(tjlx) && pm >= szpm) {
							flag = false;
						}

						if (flag) {
							message = "申请人本学年综测班级排名未达到要求，无法申请，请确认。";
							return message;
						}
					}else{
						message = "申请人本学年综测排名，无法申请，请确认。";
						return message;
					}
				}
			}
		}

		return message;
	}
	
	/**
	 * 检测项目限制条件
	 * @param model
	 * @return String
	 * @author lr (2010.11.29)
	 * */
	public String checkXmxztj(XszzCommonN05ActionForm model){
		dao = new XszzCommonN05DAO();
		
		// 返回信息
		String message = "";
		// 项目代码
		String xmdm = model.getXmdm();
		boolean xmkns = dao.checkZzxmIsKnsxm(xmdm);
		if(xmkns){
			DAO pDao = DAO.getInstance();
			if(!pDao.isKns(model.getXh())){
				message = "该项目必须是困难生才可申请！";
			}
		}
		return message;
	}
}
