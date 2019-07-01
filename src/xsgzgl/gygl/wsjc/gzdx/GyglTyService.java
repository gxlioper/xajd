package xsgzgl.gygl.wsjc.gzdx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.dtjs.czxx.dyxx.DyxxModel;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;

public class GyglTyService extends CommService {

	GyglTyDAO dao = new GyglTyDAO();

	/**
	 * 设置所需页面初始化列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(GyglTyForm myForm, HttpServletRequest request,
			String manu) throws Exception {
		
		String lddm = myForm.getLddm();
		lddm = Base.isNull(lddm) ? myForm.getQueryequals_lddm() : lddm;
		String cs = myForm.getCs();
		cs = Base.isNull(cs) ? myForm.getQueryequals_cs() : cs;
		
		// -----------------通		用---------------
		List<HashMap<String, String>> wsjcdjList = dao.getGyglList("hhgxy_wsjcdj", "wsjcdm", "wsjcdj", "", "", "");// 卫生检查
		List<HashMap<String, String>> xqdmList = dao.getGyglList("dm_zju_xq", "dm", "xqmc", "", "", "");// 校区
		List<HashMap<String, String>> ldList = dao.getGyglList("sslddmb", "lddm", "ldmc", "", "", "");// 楼栋
		List<HashMap<String, String>> csList = dao.getCsList(lddm);	//层数
		List<HashMap<String, String>> qsList = dao.getQsList(lddm, cs, "");// 寝室
		List<HashMap<String, String>> bblxList = dao.getSelectList("bblx");//报表类型
		//List<HashMap<String, String>> zcList = dao.getZcList();// 周次
		
		request.setAttribute("wsjcdjList", wsjcdjList);
		request.setAttribute("xqdmList", xqdmList);
		request.setAttribute("ldList", ldList);
		request.setAttribute("csList", csList);
		request.setAttribute("qsList", qsList);
		request.setAttribute("bblxList", bblxList);
		//request.setAttribute("zcList", zcList);
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);// 年级学院专业班级
		FormModleCommon.setNdXnXqList(request);//年度学年学期
		FormModleCommon.requestSetList(new String[] { "bm" }, request);// 自定义(目前：部门代码)
		
		List<HashMap<String, String>> sfList = dao.getSelectList("sflx");// 是否列表
		request.setAttribute("sfList", sfList);
		
		List<HashMap<String, String>> shList = dao.getSelectList("shlx");// 审核列表
		request.setAttribute("shList", shList);
		
		List<HashMap<String, String>> xbList = dao.getSelectList("xblx");// 性别
		request.setAttribute("xbList", xbList);
		// -----------------end---------------
		
		// ======================公寓报修============================
		if ("gybx".equalsIgnoreCase(manu)) {

			// 材料上报
			List<HashMap<String, String>> nrTitleList = dao
					.getSelectList("clsb");
			request.setAttribute("nrTitleList", nrTitleList);

			// 材料类型
			List<HashMap<String, String>> cllxList = dao.getGyglList(
					"gygl_bxcllxb", "dm", "mc", "", "", "");
			request.setAttribute("cllxList", cllxList);

			// 评价类型
			List<HashMap<String, String>> pjList = dao.getGyglList(
					"gygl_bxpjb", "dm", "mc", "", "", "");
			request.setAttribute("pjList", pjList);

			// 统计方式
			List<HashMap<String, String>> tjfsList = dao
					.getSelectList("bxtjfs");
			request.setAttribute("tjfsList", tjfsList);

		}
		// ======================end============================
	}

	/**
	 * 获得学生信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}

	/**
	 * 获得公寓管理信息（列表）
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getGyglList(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getGyglList(tableName, model, colList, other_query);
	}

	/**
	 * 获得公寓管理相关信息（详细）
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 */
	public HashMap<String, String> getGyglInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getGyglInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * 删除公寓管理信息
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @throws Exception
	 */
	public boolean delGygl(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();

		return dao.delDate(form, model);
	}

	/**
	 * 保存公寓管理相关信息（批量）
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param arrzd(批量字段)
	 * @param onezd(单一字段)
	 * @param notnull(非空字段)
	 * 
	 * @throws Exception
	 */
	public boolean saveGygl(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}

	/**
	 * 保存党员信息相关信息（单条）
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param onezd(单一字段)
	 * 
	 * @throws Exception
	 */
	public boolean saveGygl(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}

	/**
	 * 更新公寓管理相关信息
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param onezd(单一字段)
	 * 
	 * @throws Exception
	 */
	public boolean updateGygl(SaveForm form, DyxxModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model);
	}

	/**
	 * @describe 删除所上传文件
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param dzzd(地址字段)
	 * 
	 * @throws Exception
	 */
	public boolean fileDel(String tableName, String dzzd, String pk,
			String pkValue) throws Exception {
		return dao.fileDel(tableName, dzzd, pk, pkValue);
	}
	
	/**
	 * 获得系统当前时间
	 * 
	 * @author luojw
	 */
	public String getNowTime(String lx) {
		DAO dao = DAO.getInstance();
		return dao.getNowTime(lx);
	}
	
	/**
	 * 获得指定表的指定字段
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		return dao.getOneValue(tableName, dm, pk, pkValue);
	}
	
	/**
	 * 结果集分页
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getResultList(ArrayList<String[]> list,
			GyglTyForm model) {

		// 分页
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		if (list != null && list.size() > 0) {

			Pages pages = model.getPages();
			pages.setMaxRecord(list.size());
			int start = pages.getStart();
			int size = pages.getPageSize();

			for (int i = start; i < start + size; i++) {
				if (i < list.size()) {
					rsList.add(list.get(i));
				}
			}
		}

		return rsList;
	}
	
	/**
	 * 是否公寓管理员
	 * 
	 * @author luojw
	 */
	public Boolean isGyfdy(String userName) {
		return dao.isGyfdy(userName);
	}
	
	/**
	 * 公寓辅导员初始化校区楼栋列表
	 * 
	 * @author luojw
	 */
	public void initGyglList(String userName, HttpServletRequest request) {
		// 校区
		List<HashMap<String, String>> xqdmList = dao.getXqLdList("xq", "", "",
				userName);
		// 校区
		List<HashMap<String, String>> ldList = dao.getXqLdList("ld", "", "",
				userName);

		request.setAttribute("xqdmList", xqdmList);
		request.setAttribute("ldList", ldList);
	}
	
  	/**
	 * 是否是值班人员
	 * 
	 * @param userName
	 * @author sjf
	 */
	public Boolean isZbry(String userName) {
		return dao.isZbry(userName);
	}
}
