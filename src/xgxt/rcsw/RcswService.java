package xgxt.rcsw;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import xgxt.DAO.DAO;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.utils.FormModleCommon;

public class RcswService {

	RcswDAO dao = new RcswDAO();

	/**
	 * 设置所需页面初始化列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(RcswForm myForm, HttpServletRequest request, String manu)
			throws Exception {

		DAO tyDao = DAO.getInstance();
		
		// ---------------学生留言----------------
		if ("lyfb".equalsIgnoreCase(manu)) {

			// 留言类型列表
			List<HashMap<String, String>> lylxList = getRcglList("rcsw_lylxb",
					"dm", "mc", "", "", "");
			request.setAttribute("lylxList", lylxList);

			// 提问者身份列表
			List<HashMap<String, String>> lxList = getSelectList("shlx");
			request.setAttribute("lxList", lxList);
			
			// 留言评价列表
			List<HashMap<String, String>> lypjList = getRcglList("rcsw_lypjb",
					"dm", "mc", "", "", "");
			request.setAttribute("lypjList", lypjList);
			
			// 职位列表
			List<HashMap<String, String>> zwList = getRcglList("zwb", "zwdm",
					"zwmc", "", "", "");
			//南宁职业 心理咨询老师
			
			request.setAttribute("zwList", zwList);
		}

		// ---------------实物发放----------------
		if ("swff".equalsIgnoreCase(manu)) {

			// 发放项目列表
			List<HashMap<String, String>> xmlxList = getRcglList(
					"rcsw_swfflxb", "dm", "mc", "", "", "");
			request.setAttribute("xmlxList", xmlxList);

			// 发放人群列表
			List<HashMap<String, String>> ffrqList = getRcglList(
					"sxjy_bjgbzlb", "bjgbmc", "bjgbmc", "", "", "");// 班干部
			List<HashMap<String, String>> otherRqList = getSelectList("ffrq");// 其他人群
			if (otherRqList != null && otherRqList.size() > 0) {
				for (int i = 0; i < otherRqList.size(); i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("dm", otherRqList.get(i).get("en"));
					map.put("mc", otherRqList.get(i).get("cn"));
					ffrqList.add(map);
				}
			}
			if (ffrqList != null && ffrqList.size() > 0) {
				request.setAttribute("rqnum", ffrqList.size());
			}
			request.setAttribute("ffrqList", ffrqList);

			// 职位列表
			List<HashMap<String, String>> zwList = getRcglList("zwb", "zwdm",
					"zwmc", "", "", "");
			request.setAttribute("zwList", zwList);
			
			// 身份列表
			List<HashMap<String, String>> ffList = getSelectList("isff");
			request.setAttribute("ffList", ffList);
		}
		
		if("dxtz".equalsIgnoreCase(manu)){
			//发放项目列表
			List<HashMap<String, String>> xmlxList = getRcglList(
					"rcsw_swfflxb", "dm", "mc", "", "", "");
			request.setAttribute("xmlxList", xmlxList);

		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);// 年级学院专业班级
		FormModleCommon.setNdXnXqList(request);// 年度学年学期
		FormModleCommon.requestSetList(new String[] { "bm" }, request);// 自定义(目前：部门代码)

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
			if(Base.isNull(title)){
				title = message != null && message.length >= 2 ? message[1] : "";
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

	/**
	 * 获得日常事务相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getRcswList(String tableName, Object model,
			String[] colList, String other_query, String[] orderBy)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getRcswList(tableName, model, colList, other_query, orderBy);
	}

	/**
	 * 获得日常事务相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getRcswInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getRcswInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * 保存日常事务相关信息（批量）
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
	public boolean saveRcsw(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}
	
	/**
	 * 保存日常事务相关信息（单条）
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
	public boolean saveRcsw(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}

	/**
	 * 更新日常事务相关信息
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
	public boolean updateRcsw(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model);
	}
	
	/**
	 * 删除日常事务信息
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @throws Exception
	 */
	public boolean delRcsw(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();

		return dao.delDate(form, model);
	}
	
	/**
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * @param msg(显示信息)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getRcglList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getWhList(tableName, dm, mc,
				msg, pk, pkValue);

		return list;
	}
	
	/**
	 * 获取心理咨询咨询师
	 */
	public List<HashMap<String,String>>getXljkZxs(){
		return dao.getXljkZxs();
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

		if ("sflx".equalsIgnoreCase(lx)) {
			dm = new String[] { "是", "否" };
			mc = new String[] { "是", "否" };
		} else if ("yesorno".equalsIgnoreCase(lx)) {
			dm = new String[] { "yes", "no" };
			mc = new String[] { "有", "无" };
		} else if ("ywlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "有", "无" };
			mc = new String[] { "有", "无" };
		} else if ("shlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "学生", "老师", "管理者" };
			mc = new String[] { "学生", "老师", "管理者" };
		} else if ("ffrq".equalsIgnoreCase(lx)) {
			dm = new String[] { "班级", "辅导员", "班主任" };
			mc = new String[] { "班级", "辅导员", "班主任" };
		}else if ("isff".equalsIgnoreCase(lx)) {
			dm = new String[] { "发放", "未发放" };
			mc = new String[] { "发放", "未发放" };
		} else if ("shzt".equalsIgnoreCase(lx)){
			dm = new String[] { "通过", "退回","不通过" };
			mc = dm;
		} else if ("shjg".equalsIgnoreCase(lx)){
			dm = new String[] { "通过", "退回","不通过","需重审","未审核" };
			mc = dm;
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * 修改时间类型
	 * 
	 * @author luojw
	 */
	public String changDateLx(String sj, String lx) {

		StringBuffer newSj = new StringBuffer();

		if (Base.isNull(sj)) {
			return sj;
		}

		if ("YYYYMMDDHH24MISS".equalsIgnoreCase(lx) && sj.length() == 14) {
			newSj.append(sj.substring(0, 4) + "年");
			newSj.append(sj.substring(4, 6) + "月");
			newSj.append(sj.substring(6, 8) + "日");
			newSj.append(" ");
			newSj.append(sj.substring(8, 10) + "时");
			newSj.append(sj.substring(10, 12) + "分");
			newSj.append(sj.substring(12, 14) + "秒");
		}

		return Base.isNull(newSj.toString()) ? sj : newSj.toString();
	}
	
	/**
	 * 检查两数组重复数据，删去重复部分,返回不重复部分
	 * 
	 * @author luojw
	 */
	public String[] checkCfsj(String[] first, String[] second) {

		if (first != null && first.length > 0 && second != null
				&& second.length > 0) {
			String[] arr = null;
			List<String> fir = new ArrayList<String>(Arrays.asList(first));
			List<String> sec = Arrays.asList(second);

			fir.removeAll(sec);

			List<String> list = new ArrayList<String>(fir);

			if (list != null && list.size() > 0) {
				arr = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					arr[i] = list.get(i);
				}
			}
			return arr;
		} else {
			return first;
		}
	}
	
	/**
	 * 获得指定表的指定字段
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		DAO dao = DAO.getInstance();
		return dao.getOneValue(tableName, dm, pk, pkValue);
	}
	
	
	
	/**
	 * 车次列表
	 * @author quph
	 */
	public List<HashMap<String, String>> getCcList() {
		
		return dao.getCcList();
	}
	
	
	
	/**
	 * 车站列表
	 * @author quph
	 */
	public List<HashMap<String, String>> getCzList() {
		
		return dao.getCzList();
	}
	
	/**
	 *  根据查询条件查询心理咨询师
	 * 
	 */
	public List<HashMap<String,String>>getXlzxsByTj(String bmdm,String zgh,String xm){
		return dao.getXlzxsByTj(bmdm, zgh, xm);
	}
}
