package xgxt.jxgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.Globals;

import xgxt.DAO.DAO;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;

public class JxglTyService{

	JxglTyDAO dao = new JxglTyDAO();
	
	/**
	 * 设置所需页面初始化列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(JxglTyForm myForm, HttpServletRequest request,
			String manu) throws Exception {

		// =====================通用=============================
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);//年级学院专业班级
		FormModleCommon.setNdXnXqList(request);//年度学年学期
		FormModleCommon.requestSetList(new String[]{"bm"}, request);//自定义(目前：部门代码)
		
		List<HashMap<String, String>> xbList = dao.getSelectList("xblx");//性别
		request.setAttribute("xbList", xbList);
		
		// =====================军训成绩=============================
		if ("jxcj".equalsIgnoreCase(manu)) {
			DAO tyDao = DAO.getInstance();
			//学年列表
			List<HashMap<String, String>> xnList = tyDao.getXnndList();
			xnList.remove(0);
			request.setAttribute("xnList", xnList);
		}
		// =====================入伍报名=============================
		else if ("rwbm".equalsIgnoreCase(manu)) {
			
			// 登记类型列表
			List<HashMap<String, String>> djlxList = dao.getWhList(
					"jxgl_rwzblxb", "dm", "mc", "", "", "");
			request.setAttribute("djlxList", djlxList);

			// 户口所在信息
			StuInfoDAO stuDao = new StuInfoDAO();
			HashMap<String, String> map = new HashMap<String, String>();
			String xh = myForm.getXh();
			String[] colList = new String[] { "hkshen", "hkshi", "hkxian",
					"syd" };
			map = CommonQueryDAO
					.commonQueryOne("view_xsxxb", colList, "xh", xh);

			// 省列表
			List<HashMap<String, String>> ssList = stuDao.getSsList();
			request.setAttribute("ssList", ssList);

			// 户口所在市列表
			List<HashMap<String, String>> hkshiList = stuDao.getShiList(
					map.get("hkshen")).get("shiList");
			request.setAttribute("hkshiList", hkshiList);

			// 户口所在县列表
			List<HashMap<String, String>> hkxianList = stuDao.getShiList(
					map.get("hkshi")).get("xianList");
			request.setAttribute("hkxianList", hkxianList);

			// 生源地
			String syd = map.get("syd");
			if (!Base.isNull(syd)) {
				String[] arrDq = syd.split("/");
				map.put("syshen", (arrDq.length >= 1) ? arrDq[0] : "");
				map.put("syshi", (arrDq.length >= 2) ? arrDq[1] : "");
				map.put("syxian", (arrDq.length >= 3) ? arrDq[2] : "");
			}

			// 生源市列表
			List<HashMap<String, String>> syshiList = stuDao.getShiList(
					map.get("syshen")).get("shiList");
			request.setAttribute("syshiList", syshiList);

			// 生源县列表
			List<HashMap<String, String>> syxianList = stuDao.getShiList(
					map.get("syshen")).get("xianList");
			request.setAttribute("syxianList", syxianList);
		}
		//	=====================免缓训=============================
		else if ("mhx".equalsIgnoreCase(manu)) {
			
			//	籍贯
			StuInfoDAO stuDao = new StuInfoDAO();
			HashMap<String, String> map = new HashMap<String, String>();
			String xh = myForm.getXh();
			String[] colList = new String[] { "jg"};
			map = CommonQueryDAO.commonQueryOne("xsxxb", colList, "xh", xh);
			
			String jg = map.get("jg");
			if (!Base.isNull(jg)) {
				String[] arrDq = jg.split("/");
				map.put("jgshen", (arrDq.length >= 1) ? arrDq[0] : "");
				map.put("jgshi", (arrDq.length >= 2) ? arrDq[1] : "");
				map.put("jgxian", (arrDq.length >= 3) ? arrDq[2] : "");
			}
			
			// 省列表
			List<HashMap<String, String>> ssList = stuDao.getSsList();
			request.setAttribute("ssList", ssList);
			
			// 市列表
			List<HashMap<String, String>> jgshiList = stuDao.getShiList(
					map.get("jgshen")).get("shiList");
			request.setAttribute("jgshiList", jgshiList);

			// 县列表
			List<HashMap<String, String>> jgxianList = stuDao.getShiList(
					map.get("jgshi")).get("xianList");
			request.setAttribute("jgxianList", jgxianList);
			
			// 申请类型
			List<HashMap<String, String>> mhlxList = dao.getWhList(
					"jxgl_mhxlxb", "dm", "mc", "", "", "");
			request.setAttribute("mhlxList", mhlxList);
			
			//审核状态列表
			List<HashMap<String, String>> shList =dao.getSelectList("shlx");
			request.setAttribute("shList", shList);
		}
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
	 * 获得学生信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDetStuInfo(String xh) {
		return CommonQueryDAO.getDetStuInfo(xh);
	}

	/**
	 * 获得军训管理信息（列表）
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getJxglList(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getJxglList(tableName, model, colList, other_query);
	}

	/**
	 * 获得军训管理相关信息（详细）
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 */
	public HashMap<String, String> getJxglInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getJxglInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * 删除军训管理信息
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @throws Exception
	 */
	public boolean delJxgl(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();

		return dao.delDate(form, model);
	}

	/**
	 * 保存军训管理相关信息（批量）
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
	public boolean saveJxgl(SaveForm form, Object model) throws Exception {
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
	public boolean saveJxgl(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}

	/**
	 * 更新军训管理相关信息
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
	public boolean updateJxgl(SaveForm form, Object model) throws Exception {
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
	 * 获得无需维护下拉框值
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("djlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "是", "否" };
			mc = new String[] { "是", "否" };
		}

		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * 设置Request 的值
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setRequestValue(RequestForm rForm, HttpServletRequest request) {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String writeAble = request.getParameter("writeAble");
		String title = rForm.getTitle();

		// 视图名
		String tableName = rForm.getTableName();
		if (!Base.isNull(tableName)) {
			request.setAttribute("tableName", tableName);
		}

		// 表名
		String realTable = rForm.getRealTable();
		if (!Base.isNull(realTable)) {
			request.setAttribute("realTable", realTable);
		}

		// 模块路径
		String path = rForm.getPath();
		if (!Base.isNull(path)) {
			request.setAttribute("path", path);
		}

		// 用户类型
		userType = Base.isNull(userType) ? rForm.getUserType() : userType;
		if (!Base.isNull(userType)) {
			request.setAttribute("userType", userType);
		}

		// 用户名
		userName = Base.isNull(userName) ? rForm.getUserName() : userName;
		if (!Base.isNull(path)) {
			request.setAttribute("userName", userName);
		}

		// 用户所在部门
		userDep = Base.isNull(userDep) ? rForm.getUserDep() : userDep;
		if (!Base.isNull(path)) {
			request.setAttribute("userDep", userDep);
		}

		// 操作类型
		doType = Base.isNull(doType) ? rForm.getDoType() : doType;
		if (!Base.isNull(doType)) {
			request.setAttribute("doType", doType);
		}

		// 读写权相关
		if (Base.isNull(writeAble)) {
			String[] message = FormModleCommon.getWriteAbleAndTitle(request);
			writeAble = message != null && message.length >= 1 ? message[0]
					: "";
			if (Base.isNull(title)) {
				title = message != null && message.length >= 2 ? message[1]
						: "";
			}
		}
		request.setAttribute("writeAble", writeAble);

		// 模块标题
		if (!Base.isNull(title)) {
			request.setAttribute("title", title);
		}

		// 主键
		String pk = rForm.getPk();
		if (!Base.isNull(pk)) {
			request.setAttribute("pk", pk);
		}

		// 模塊類型
		String mklx = rForm.getMklx();
		if (!Base.isNull(mklx)) {
			request.setAttribute("mklx", mklx);
		}

		// 详细信息
		HashMap<String, String> rs = rForm.getRs();
		if (rs != null && rs.size() > 0) {
			request.setAttribute("rs", rs);
		}

		// 详细列表信息
		List<HashMap<String, String>> rsList = rForm.getRsList();
		if (rsList != null && rsList.size() > 0) {
			request.setAttribute("rsList", rsList);
		}

		// 其他字段
		String[] qtzd = rForm.getQtzd();
		// 其他字段值
		String[] qtzdz = rForm.getQtzdz();

		if (qtzd != null && qtzdz != null && (qtzd.length == qtzdz.length)) {
			for (int i = 0; i < qtzd.length; i++) {
				request.setAttribute(qtzd[i], qtzdz[i]);
			}
		}

	}
}
