package xgxt.comm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts.upload.FormFile;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.szdw.nbzy.BjtsxmDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicDAO;
import com.zfsoft.utils.StringUtil;

public class CommService {

	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");
	private long maxszie = Long.valueOf(resource.getString("filesys.maxsize"));
	
	CommDAO dao = new CommDAO();

	/**
	 * 获得学生基本信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}
	
	/**
	 * 设置所需页面初始化列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(CommForm myForm, HttpServletRequest request, String manu)
			throws Exception {

		// =====================通用=============================
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);// 年级学院专业班级
		FormModleCommon.setNdXnXqList(request);// 年度学年学期
		FormModleCommon.requestSetList(new String[] { "bm" }, request);// 自定义(目前：部门代码)

	}

	/**
	 * 设置所需页面初始化列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(CommForm model, RequestForm rForm,
			HttpServletRequest request) throws Exception {
		CommList commList = new CommList();
		commList.setList(model, rForm, request);
	}
	
	/**
	 * 设置年级学院专业班级列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setAllList(CommForm model, RequestForm rForm,
			HttpServletRequest request) throws Exception {
		CommList commList = new CommList();
		commList.setAllList(model, rForm, request);
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

		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = (String) session.getAttribute("userName");
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
//		if (Base.isNull(writeAble)) {
			FormModleCommon.commonRequestSet(request);
//			String[] message = FormModleCommon.getWriteAbleAndTitle(request);
//			writeAble = message != null && message.length >= 1 ? message[0]
//					: "";
//			if (Base.isNull(title)) {
//				title = message != null && message.length >= 2 ? message[1]
//						: "";
//			}
//		}

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

		// 表头信息
		List<HashMap<String, String>> topTr = rForm.getTopTr();
		if (topTr != null && topTr.size() > 0) {
			request.setAttribute("topTr", topTr);
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

		// 详细列表信息
		ArrayList<String[]> rsArrList = rForm.getRsArrList();
		if (rsArrList != null && rsArrList.size() > 0) {
			request.setAttribute("rsArrList", rsArrList);
			request.setAttribute("rsNum", rsArrList.size());
		}

		// 提示信息
		String message = rForm.getMessage();
		if (!Base.isNull(message)) {
			request.setAttribute("message", message);
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
	 * 设置Request 的值
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setRequestValue(RequestForm rForm, User user,
			HttpServletRequest request) {

		DAO dao = DAO.getInstance();

		String userType = user.getUserType();
		String userDep = user.getUserDep();
		String gyglyQx = user.getGyglyQx();
		String userName = user.getUserName();
		String userStatus = user.getUserStatus();
		String doType = request.getParameter("doType");
		String writeAble = (String)request.getAttribute("writeAble");
		if(Base.isNull(writeAble)){
			 writeAble = request.getParameter("writeAble");
		}
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

		// 学生
		if ("stu".equalsIgnoreCase(userType)) {

			String sql = "select xm from view_xsjbxx where xh = ? ";
			request.setAttribute("xh", userName);
			request.setAttribute("xm", dao.getOneRs(sql,
					new String[] { userName }, "xm"));
		}

		// 用户名
		userName = Base.isNull(userName) ? rForm.getUserName() : userName;
		if (!Base.isNull(path)) {
			request.setAttribute("userName", userName);
		}

		// 用户所在部门
		userDep = Base.isNull(userDep) ? rForm.getUserDep() : userDep;
		if (!Base.isNull(userDep)) {
			request.setAttribute("userDep", userDep);
		}

		// 用户身份
		userStatus = Base.isNull(userStatus) ? rForm.getUserStatus()
				: userStatus;
		if (!Base.isNull(userStatus)) {
			request.setAttribute("userStatus", userStatus);
		}

		// 公寓管理员权限
		if (!Base.isNull(gyglyQx)) {
			request.setAttribute("gyglyQx", gyglyQx);
		}
		
		// 操作类型
		doType = Base.isNull(doType) ? rForm.getDoType() : doType;
		if (!Base.isNull(doType)) {
			request.setAttribute("doType", doType);
		}
		String[] message = FormModleCommon.getWriteAbleAndTitle(request);
		// 读写权相关
		if (Base.isNull(writeAble)) {
			writeAble = message != null && message.length >= 1 ? message[0]: "";
		}
		if (Base.isNull(title)) {
			title = message != null && message.length >= 2 ? message[1]: "";
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

		// 表头信息
		List<HashMap<String, String>> topTr = rForm.getTopTr();
		if (topTr != null && topTr.size() > 0) {
			request.setAttribute("topTr", topTr);
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
			request.setAttribute("rsNum", rsList.size());
		}

		// 详细列表信息
		ArrayList<String[]> rsArrList = rForm.getRsArrList();
		if (rsArrList != null && rsArrList.size() > 0) {
			request.setAttribute("rsArrList", rsArrList);
			request.setAttribute("rsNum", rsArrList.size());
		}

		// 提示信息
		String tsmessage = rForm.getMessage();
		if (!Base.isNull(tsmessage)) {
			request.setAttribute("message", tsmessage);
		}

		// 是否查询
		String search = rForm.getSearch();
		if (!Base.isNull(search)) {
			request.setAttribute("search", search);
		}

		// 查询类型
		String searchType = rForm.getSearchType();
		if (!Base.isNull(searchType)) {
			request.setAttribute("searchType", searchType);
		}
		
		// ================分页==================
		Pages pages = rForm.getPages();
		//request.setAttribute("pageSize", pages.getPageSize());
		request.setAttribute("pObj", pages);
		// ================分页 end==================

		// ===============通用设置=================
		CommSetting commSetting = rForm.getCommSetting();

		// 结果集名称
		String rsName = commSetting.getRsName();
		if (!Base.isNull(rsName)) {
			request.setAttribute("rsName", rsName);
		}

		// 是否需要checkbox
		String isCheckBox = commSetting.getIsCheckBox();
		if (!Base.isNull(isCheckBox)) {
			request.setAttribute("isCheckBox", isCheckBox);
		}

		// 显示的起始列
		String startNum = commSetting.getStartNum();
		if (!Base.isNull(startNum)) {
			request.setAttribute("startNum", startNum);
		}

		// 显示的数量
		String showNum = commSetting.getShowNum();
		if (!Base.isNull(showNum)) {
			request.setAttribute("showNum", showNum);
		}

		String[] colList = rForm.getColList();
		if (!Base.isNull(rsName)) {
			if ("rsArrList".equalsIgnoreCase(rsName)) {
				// 是否为空
				if (rsArrList != null && rsArrList.size() > 0) {
					String hadRs = "yes";
					request.setAttribute("hadRs", hadRs);
				} else {
					rsArrList = setDefaultLIst(colList, pages);
					String hadRs = "no";
					request.setAttribute("hadRs", hadRs);
				}
			} else if ("rsList".equalsIgnoreCase(rsName)) {
				// 是否为空
				if (rsList != null && rsList.size() > 0) {
					String hadRs = "yes";
					request.setAttribute("hadRs", hadRs);
				} else {
					rsList = setDefaultListForMap(colList, pages);
					String hadRs = "no";
					request.setAttribute("hadRs", hadRs);
				}
			}
		}
		// ===============通用设置 end =================

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
	 * 判断是否学院
	 * 
	 * @param user
	 * @param request
	 * @author luojw
	 */
	public Boolean isXy(User user) {

		String userType = user.getUserType();
		boolean fdyQx = Boolean.parseBoolean(user.getFdyQx());
		boolean bzrQx = Boolean.parseBoolean(user.getBzrQx());
		boolean isXy = false;

		if ("xy".equalsIgnoreCase(userType) && !fdyQx && !bzrQx) {
			// 学院用户
			isXy = true;
		}

		return isXy;
	}

	/**
	 * 保存相关信息（单条）
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
	public boolean saveInfoToDb(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {
		return dao.submitData(form, model, request);
	}

	/**
	 * 保存相关信息（批量）
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
	public boolean saveInfoToDb(SaveForm form, Object model, User user)
			throws Exception {
		return dao.saveData(form, model, user);
	}

	/**
	 * 更新相关信息
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
	public boolean updateInfoInDb(SaveForm form, Object model, User user)
			throws Exception {
		return dao.updateData(form, model, user);
	}

	/**
	 * 批量删除DB中的数据
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception
	 */
	public boolean delInfoInDb(SaveForm form, Object model, User user)
			throws Exception {
		return dao.delDate(form, model, user);
	}

	/**
	 * 批量提交
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String[] sql) throws Exception {
		return dao.saveArrDate(sql);
	}

	/**
	 * 批量提交
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String sql, List<String[]> params,
			String tableName, User user) throws Exception {
		return dao.saveArrDate(sql, params, tableName, user);
	}
	
	/**
	 * 数据查询（HashMap）
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getRsInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getRsInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * 数据查询（ArrayList<String[]>）
	 * 
	 * @param tableName(表名)
	 * @param query(条件)
	 * @param inPutList(输入值)
	 * @param colList(输出值)
	 * @param sql(个性化sql)
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getRsArrList(String tableName, String query,
			String[] inPutList, String[] colList, String sql, Object model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getRsArrList(tableName, query, inPutList, colList, sql,
				model);
	}

	/**
	 * 数据查询（List<HashMap>）
	 * 
	 * @param tableName(表名)
	 * @param query(条件)
	 * @param inPutList(输入值)
	 * @param colList(输出值)
	 * @param sql(个性化sql)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getRsList(String tableName,
			String query, String[] inPutList, String[] colList, String sql) {
		return dao.getRsList(tableName, query, inPutList, colList, sql);
	}

	//===================表结构相关 begin===============================
	/**
	 * 查询表的字段信息
	 * 
	 * @param tableName
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> selectTableInfo(String tableName) {
		BasicDAO dao = new BasicDAO();
		return dao.selectTableInfo(tableName);
	}
	
	/**
	 * 查询指定表的Comment
	 * 
	 * @param tableName
	 * @return List<HashMap<String, String>>
	 */
	public String[] getTableComment(String tableName, String[] zd) {
		List<HashMap<String, String>> list = dao.getTableInfo(tableName);
		ArrayList<String> comment = new ArrayList<String>();

		if (zd != null && zd.length > 0) {
			for (int i = 0; i < zd.length; i++) {
				
				boolean flag = true;
				
				for (int j = 0; j < list.size(); j++) {
					
					HashMap<String, String> map = list.get(j);
					
					if (zd[i].equalsIgnoreCase(map.get("zdm"))) {
						
						flag = false;
						
						if (Base.isNull(map.get("zwm"))) {
							comment.add("");
							break;
						} else {
							comment.add(map.get("zwm"));
							break;
						}
					}
				}
				
				if(flag){
					comment.add("");
				}
			}
		}

		return comment.toArray(new String[] {});
	}
	//===================表结构相关 end===============================
	
	/**
	 * 获得假分页
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getResultList(
			List<HashMap<String, String>> list, Pages pages) {

		// 分页
		List<HashMap<String, String>> rsList = new ArrayList<HashMap<String, String>>();

		if (list != null && list.size() > 0) {

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
	 * 获得假分页
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getResultList(ArrayList<String[]> list,
			Pages pages) {

		// 分页
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		if (list != null && list.size() > 0) {

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
	public List<HashMap<String, String>> getWhList(String tableName, String dm,
			String mc, String msg, String pk, String pkValue) {
		DAO dao = DAO.getInstance();
		return dao.getWhList(tableName, dm, mc, msg, pk, pkValue);
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
	 * @pararm isnull（是否需要显示请选择）
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWhList(String tableName, String dm,
			String mc, String msg, String pk, String pkValue, boolean isnull) {
		DAO dao = DAO.getInstance();
		return dao.getWhList(tableName, dm, mc, msg, pk, pkValue, isnull);
	}
	
	/**
	 * 获得指定表的字段
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] getTableZd(String tableName) throws Exception {
		return dao.getTableZd(tableName);
	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param myForm
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String upLoadFile(HttpServletRequest request, FormFile file,
			String mklx) throws FileNotFoundException, IOException {
		return upLoadFile(request, file, mklx, maxszie);
	}
	public String upLoadFile(HttpServletRequest request, FormFile file,
			String mklx,long maxSize) throws FileNotFoundException, IOException {
		String filelx = (String)request.getAttribute("filelx");

		String filePath = "";
		String fName = "";
		if (file != null) {
			String dir = "/upload/" + mklx;
			File f = new File(dir);
			if (!f.exists()) {
				f.mkdirs();
			}
			Timestamp date = new Timestamp(System.currentTimeMillis());
			String dateStr = date.toString().substring(0, 19);
			dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "")
					.replaceAll(":", "");
			int size = file.getFileSize();
			if (filelx.equalsIgnoreCase("003"))
			{
				maxSize = 520971520;
			}
			if (size < maxSize) {

				fName = dateStr + file.getFileName();
				InputStream in = file.getInputStream();
				filePath = dir + "/" + fName;//"e:" + dir + "/" + fName;
				OutputStream out = new FileOutputStream(filePath);
				int bytesRead = 0;
				byte[] buffer = new byte[size];
				while ((bytesRead = in.read(buffer, 0, size)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				out.close();
				in.close();
			} else {
				String message = "文件过大，上传失败";
				request.setAttribute("message", message);
			}
		}
		return filePath;
	}
	/**
	 * @describe 删除所上传文件
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean delUpLoadFile(String[] filepath) {

		boolean flag = true;

		if (filepath != null && filepath.length > 0) {

			for (int i = 0; i < filepath.length; i++) {

				File file = new File(filepath[i]);

				if (file.exists()) {

					flag = file.delete();

					if (!flag) {

						return flag;

					}
				}
			}
		}

		return flag;
	}

	/**
	 * 输出EXCEL
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expToExcel(String title, List<HashMap<String, String>> topTr,
			ArrayList<String[]> list, OutputStream os) throws Exception {

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet(title, 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充表头
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.addCell(new Label(i, 0, map.get("cn"), wcf2));
			}
		}

		// 填充内容
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				String[] info = list.get(i);

				if (info != null && info.length > 0) {

					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 1, info[j], wcf2));
					}
				}
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 输出EXCEL
	 * 
	 * @param title
	 *            显示的sheet名称
	 * @param topTr
	 *            excel的第一行中文列描述
	 * @param list
	 *            导出内容
	 * @param os
	 *            流
	 * @param hbzt
	 *            合并状态（hb：合并，需要合并的列；bhb：不合并的列，其他都要合并；可以为空，不合并所有单元格）
	 * @param hbwz
	 *            合并位置
	 * @author luo
	 * 
	 * @throws Exception
	 */
	public void expToExcel(String title, List<HashMap<String, String>> topTr,
			ArrayList<String[]> list, OutputStream os, String hbzt, int[] hbwz)
			throws Exception {

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet(title, 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.TOP, true, Border.ALL);

		// 填充表头
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.addCell(new Label(i, 0, map.get("cn"), wcf2));
			}
		}

		// 填充内容
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				String[] info = list.get(i);

				if (info != null && info.length > 0) {

					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 1, info[j], wcf2));
					}
				}
			}

			if (hbwz != null && hbwz.length > 0) {
				// 合并单元格
				if ("hb".equalsIgnoreCase(hbzt)) {// 合并所录入的位置
					hbdyg(list, hbwz, ws);
				} else if ("bhb".equalsIgnoreCase(hbzt)) {// 不合并所录入的位置（其他都要合并）
					// 表头文件
					if (topTr != null && topTr.size() > 0
							&& topTr.size() > hbwz.length) {

						int[] newWz = new int[topTr.size() - hbwz.length];
						int n = 0;
						for (int i = 0; i < topTr.size(); i++) {

							boolean flag = true;

							for (int j = 0; j < hbwz.length; j++) {
								if (i == hbwz[j]) {
									flag = false;
								}
							}

							if (flag) {
								newWz[n] = i;
								n++;
							}
						}
						hbdyg(list, newWz, ws);
					}
				} else {// 不处理

				}
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 合并单元格 list 查询结果 hbwz 需要合并的行的标号 int[] hbwz={0,2,4}(合并0,2,4行中相同的数据)
	 * 
	 * @author qlj
	 * @throws WriteException
	 * @throws RowsExceededException
	 * @throws Exception
	 */
	public void hbdyg(List<String[]> list, int[] hbwz, WritableSheet ws)
			throws RowsExceededException, WriteException {
		if (hbwz != null && hbwz.length > 0) {
			int len = 1;
			for (int i = 0; i < hbwz.length; i++) {
				int wz = hbwz[i];
				int hb[] = getLines(list, len, wz);

				for (int j = 0; j < hb.length; j++) {
					if (hb.length == 1) {
						ws.mergeCells(wz, hb[j], wz, 2 + list.size() - 1);
					} else if (j == hb.length - 1) {
						ws.mergeCells(wz, hb[j], wz, hb[j]);
					} else {
						ws.mergeCells(wz, hb[j], wz, hb[j + 1] - 1);
					}
				}
			}
		}
	}

	/**
	 * 合并单元格 list 查询结果 hbwz 需要合并的行的标号 int[] hbwz={0,2,4}(合并0,2,4行中相同的数据) qsh
	 * 需要从第几行开始合并单元格
	 * 
	 * @author qlj
	 * @throws WriteException
	 * @throws RowsExceededException
	 * @throws Exception
	 */
	public void hbdyg(List<String[]> list, int[] hbwz, int qsh, WritableSheet ws)
			throws RowsExceededException, WriteException {
		if (hbwz != null && hbwz.length > 0 && list!=null && list.size()>0) {
			int len = 1;
			for (int i = 0; i < hbwz.length; i++) {
				int wz = hbwz[i];
				int hb[] = getLines(list, len, wz);

				for (int j = 0; j < hb.length; j++) {
					if (hb.length == 1) {
						ws.mergeCells(wz, hb[j], wz, qsh + list.size() - 1);
					} else if (j == hb.length - 1) {
						ws.mergeCells(wz, hb[j], wz, hb[j]);
					} else {
						ws.mergeCells(wz, qsh+hb[j]-2, wz,qsh+hb[j + 1] -3);
					}
				}
			}
		}
	}

	/**
	 * 获得合并信息
	 * 
	 * @author qlj
	 * @throws Exception
	 */
	public int[] getLines(List<String[]> list, int len, int wz) {
		int[] length = new int[list.size() + 1];
		int m = len;
		int n = 0;
		length[n] = m;
		n++;
		if (list.size() > 1) {
			for (int i = 0; i < list.size() - 1; i++) {
				String[] wzsi = list.get(i);//
				String[] wzsj = list.get(i + 1);
				if (i == list.size() - 1) {
					length[n] = ++m;
				} else {
					if (wzsi[wz].equals(wzsj[wz])) {
						length[n] = ++m;
					} else {
						length[n] = ++m;
						n++;
					}
				}
			}
		}

		int[] getLen = new int[n + 1];
		for (int i = 0; i < n; i++) {
			getLen[i] = length[i];
		}
		getLen[n] = list.size() + len;
		return getLen;
	}

	/**
	 * 获得周次列表
	 * 
	 * @author luojw
	 */
	public static List<HashMap<String, String>> getZcList(String tableName,
			String zczd) {

		DAO dao = DAO.getInstance();
		String zzc = dao.getOneValue(tableName, zczd, "rownum", "1");// 总周次

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (!Base.isNull(zzc)) {
			for (int i = 1; i <= Integer.parseInt(zzc); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("dm", String.valueOf(i));
				map.put("mc", "第" + String.valueOf(i) + "周");
				list.add(map);
			}
		}

		return list;
	}

	/**
	 * 构造导出报表表头
	 * 
	 * @author luoj
	 */
	public List<HashMap<String, String>> getTopList(String[] topName) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		if (topName != null && topName.length > 0) {

			for (int i = 0; i < topName.length; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("cn", topName[i]);
				topTr.add(map);
			}
		}
		return topTr;
	}

	/**
	 * 构造输出值
	 * 
	 * @author luoj
	 */
	public String[] getOutValue(String[] outValue) {

		String[] outZd = null;

		if (outValue != null && outValue.length > 0) {

			int n = 0;

			for (int i = 0; i < outValue.length; i++) {
				if (!outValue[i].contains("sys_nc")) {
					n++;
				}
			}

			outZd = new String[n];

			int m = 0;
			for (int i = 0; i < outValue.length; i++) {
				if (!outValue[i].contains("sys_nc")) {
					outZd[m] = outValue[i];
					m++;
				}
			}
		}
		return outZd;
	}

	/**
	 * 构造输出值
	 * 
	 * @author luoj
	 */
	public ArrayList<String[]> setDefaultLIst(String[] colList, Pages pages) {

		int size = pages.getPageSize();

		String[] arr = new String[colList.length];

		for (int i = 0; i < colList.length; i++) {
			arr[i] = "";
		}

		ArrayList<String[]> list = new ArrayList<String[]>();

		for (int i = 0; i < size; i++) {
			list.add(arr);
		}

		return list;
	}

	/**
	 * 构造输出值
	 * 
	 * @author luoj
	 */
	public  List<HashMap<String, String>>  setDefaultListForMap(String[] colList, Pages pages) {

		int size = pages.getPageSize();



		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();

		for (int i = 0; i < size; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			list.add(map);
		}

		return list;
	}
	
	/**
	 * 获取用户相关信息
	 * 
	 * @param request
	 * @return User
	 */
	public User getUser(HttpServletRequest request) {

		User user = new User();
		HttpSession session = request.getSession();

		user.setUserName(session.getAttribute("userName") != null ? session.getAttribute("userName").toString():"");
		user.setUserType(session.getAttribute("userType") != null ? session.getAttribute("userType").toString():"");
		user.setIsFdy(session.getAttribute("isFdy") != null ? session.getAttribute("isFdy").toString():"");
		user.setUserDep(session.getAttribute("userDep") != null ? session.getAttribute("userDep").toString() :"");
		user.setUserMac(session.getAttribute("userMac") != null ? session.getAttribute("userMac").toString():"");

		// 用户类型
		String userType = session.getAttribute("userType") != null ? session.getAttribute("userType").toString() :"";
		// 辅导员权限
		String fdyQx = session.getAttribute("fdyQx") != null ? session.getAttribute("fdyQx").toString():"";
		// 班主任权限
		String bzrQx = session.getAttribute("bzrQx") != null ? session.getAttribute("bzrQx").toString():"";
		// 用户身份
		String userStatus = "";

		if (Boolean.parseBoolean(bzrQx) && Boolean.parseBoolean(fdyQx)) {
			userStatus = "jd";// 班主任兼辅导员
		} else if (Boolean.parseBoolean(fdyQx)) {
			userStatus = "fdy";// 辅导员
		} else if (Boolean.parseBoolean(bzrQx)) {
			userStatus = "bzr";// 班主任
		} else if ("xy".equalsIgnoreCase(userType)) {
			userStatus = "xy";// 学院
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			userStatus = "xx";// 学校用户（管理员）
		} else {
			userStatus = "stu";// 学生
		}

		String gyglyQx = session.getAttribute("gyglyQx") == null ? 
									dao.getGyglyQx(user.getUserName()) : (String)session.getAttribute("gyglyQx");
		
		user.setFdyQx(fdyQx);
		user.setBzrQx(bzrQx);
		user.setGyglyQx(gyglyQx);
		user.setUserStatus(userStatus);

		user.setHost(request.getRemoteHost());
		user.setIp(request.getRemoteAddr());

		String userRoles = session.getAttribute("userRoles") != null ? session.getAttribute("userRoles").toString() : "";
		if(!Base.isNull(userRoles)){
			user.setUserRoles(userRoles.split("!!"));
		}
		
		return user;
	}

	/**
	 * 创建结果集
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	public void createRs(SearchRsModel rsModel, Pages pages,
			HttpServletResponse response) throws IOException {
		
		// 多选框有无
		String checkBox = rsModel.getCheckBox();
		// 多选框有无(结果集)
		String checkBoxRs = rsModel.getCheckBoxRs();
		// 标题
		List<HashMap<String, String>> topTr = rsModel.getTopTr();
		// 结果集
		ArrayList<String[]> rsArrList = rsModel.getRsArrList();
		// 构建语句
		String spHtml = rsModel.getSpHtml();
		
		//是否增加“查询结果”行
		String showTitle  = rsModel.getShowTitle();
		
		response.setContentType("text/html;charset=gbk");
		
		StringBuilder html = new StringBuilder();
		
		if("yes".equalsIgnoreCase(showTitle)){
			html.append("<h3 class=\"datetitle_01\">");
			html.append("<span id=\"span_note\" width=\"100%\">");
			html.append("查询结果&nbsp;&nbsp;");		
			html.append("</span>");
			html.append("</h3>");
		}
		
		html.append("<div class=\"con_overlfow\" style=\"width:100%;overflow-x:auto;overflow-y:hidden;\">");
		html.append("<span class=\"formbox\">");
		html.append("<table class=\"dateline\" width=\"100%\" id=\"table_rs\" style=\"\">");
		// =========================标题===========================
		html.append("<thead>");
		html.append("<tr>");	

		if ("yes".equalsIgnoreCase(checkBox)) {
			html.append("<td width=\"5px\">");
			html.append("<input type=\"checkbox\" id=\"selall\" ");
			html.append("name=\"selall\" onclick=\"selAll()\" />");
			html.append("</td>");
		}
		
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				
				html.append("<td  nowrap=\"nowrap\" ");
//				if(i == 0){
//					html.append("<td width=\"5px\" nowrap=\"nowrap\" ");
//				}else{
//					html.append("<td style=\"width:"+(100/(topTr.size()-1))+"%\"  nowrap=\"nowrap\" ");
//				}			
				
				//排序
				String arrange = rsModel.getArrange();
				if("yes".equalsIgnoreCase(arrange)){
					html.append("id=\""+topTr.get(i).get("en")+"\"");
					html.append("onclick=\"arrangeRs(this)\"");
				}
				html.append(">");
				html.append(topTr.get(i).get("cn"));
				html.append("</td>");
			}
		}

		html.append("</tr>");
		html.append("</thead>");
		// =========================标题 end===========================
		
		// =========================结果集 =========================
		html.append("<tbody>");
		if (!Base.isNull(spHtml)) {
			html.append(spHtml);
		} else {
			if (rsArrList != null && rsArrList.size() > 0) {
				for (int i = 0; i < rsArrList.size(); i++) {
					String[] rs = rsArrList.get(i);
					html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

					if (Base.isNull(checkBoxRs)) {
						html.append("<td align=\"left\" width=\"5px\">");
						html.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
						html.append("value=\"" + rs[0] + "\"/>");
						html.append("</td>");
					}

					for (int j = 1; j < rs.length; j++) {
						html.append("<td align=\"left\" style=\"width:"+(100/(topTr.size()-1))+"%\"  nowrap=\"nowrap\" >");
						html.append(rs[j]);
						html.append("</td>");
					}
					html.append("</tr>");
				}
			}
		}
		
		// 行数
		int rows = 0;

		if (rsArrList != null && rsArrList.size() > 0) {
			rows = rsArrList.size();
		} else if (!Base.isNull(rsModel.getRows())) {
			rows= Integer.parseInt(rsModel.getRows());
		}
		
		int spaceRow = pages.getPageSize();
		
		spaceRow = spaceRow - rows;
		
		String noSpace = rsModel.getNoSpace();
		
		// 补空行
		if (spaceRow != 0 && !"no".equalsIgnoreCase(noSpace)) {

			for (int i = 0; i < spaceRow; i++) {
				html.append("<tr>");

				if ("yes".equalsIgnoreCase(checkBox)) {
					html.append("<td width=\"5px\">");
//					html.append("<input type=\"checkbox\" id=\"selall\" ");
//					html.append("name=\"selall\" onclick=\"selAll()\" />");
					html.append("</td>");
				}

				if (topTr != null && topTr.size() > 0) {

					// IE版本
					String ie = rsModel.getIe();

					for (int j = 0; j < topTr.size(); j++) {

						html.append("<td width=\"5px\"");
						// IE8
						if ("5.8".equalsIgnoreCase(ie)) {
							html.append("height=\"28.5px\"");
						} else {
							html.append("height=\"29px\"");
						}

						html.append(">");
						html.append("&nbsp;");
						html.append("</td>");
					}
				}

				html.append("</tr>");
			}
		}
		
		html.append("</tbody>");
		// =========================结果集 end=========================
							
		html.append("</table>");
		html.append("</span>");
		html.append("</div>");
		
		html.append("<input type=\"hidden\" id=\"ajax_max_record\" value=\""+pages.getMaxRecord()+"\"/>");
		html.append("<input type=\"hidden\" id=\"ajax_max_page\" value=\""+pages.getMaxPage()+"\"/>");
		html.append("<input type=\"hidden\" id=\"ajax_max_current\" value=\""+pages.getCurrentPage()+"\"/>");
		html.append("<input type=\"hidden\" id=\"ajax_page_size\" value=\""+pages.getPageSize()+"\"/>");
		html.append("<input type=\"hidden\" id=\"ajax_page_maxpage\" value=\""+pages.getMaxPage()+"\"/>");
		
		response.getWriter().print(html.toString());
	}
	
	
	
	/**
	 * 获取高级查询Model重构（原方法setSearchModel）
	 * @param request
	 * @return
	 */
	public SearchModel getSearchModel(HttpServletRequest request){
		RequestForm rForm = new RequestForm();
		String path = request.getParameter("path");
		
		try {
			rForm.setPath(path);
			return setSearchModel(rForm,request);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	
	
	/**
	 * 设置高级查询Model
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	public SearchModel setSearchModel(RequestForm rForm,
			HttpServletRequest request) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, UnsupportedEncodingException {

		SearchModel searchModel = new SearchModel();

		// 访问路径
		String path = rForm.getPath();
		searchModel.setPath(path);
		//批量查询
		String plcx_xh = request.getParameter("plcx_xh");
		String plcx_xm = request.getParameter("plcx_xm");
		if(!Base.isNull(plcx_xh)){ 
			searchModel.setPlcx_xh(plcx_xh);
		}
		if(!Base.isNull(plcx_xm)){ 
			searchModel.setPlcx_xm(plcx_xm);
		}
		// 模糊查询
		String input_mhcx = request.getParameter("input_mhcx");
		String mhcx_lx = request.getParameter("mhcx_lx");
		if(!Base.isNull(input_mhcx)){ 
			input_mhcx = unicode2Gbk(input_mhcx);
		}
		
		searchModel.setInput_mhcx(input_mhcx);
		searchModel.setMhcx_lx(mhcx_lx);
		
		// 排序
		String arrange = request.getParameter("arrange");
		String fashion = request.getParameter("fashion");
		searchModel.setArrange(arrange);
		searchModel.setFashion(fashion);
		
		
		String searchLxs = request.getParameter("searchLx");
		String searchTjs = request.getParameter("searchTj");
		String searchTjzs = request.getParameter("searchTjz");
		
		// 过滤类型
		String[] searchLx = StringUtil.isNull(searchLxs) ? new String[]{} : searchLxs.split("!!@@!!");
		String[] searchTj = StringUtil.isNull(searchTjs) ? new String[]{} : searchTjs.split("!!@@!!");
		String[] searchTjz = StringUtil.isNull(searchTjzs) ? new String[]{} : searchTjzs.split("!!@@!!");

		if (searchTj != null && searchTj.length > 0) {

			for (int i = 0; i < searchLx.length; i++) {

				ArrayList<String> valueList = new ArrayList<String>();
				String lx = "search_tj_" + searchLx[i];

				for (int j = 0; j < searchTj.length; j++) {
					
					String tj = searchTj[j];
					String tjz = unicode2Gbk(searchTjz[j]);
					tjz=tjz.replace("!!DDBGG!!", "");
					if (lx.equalsIgnoreCase(tj.replace("searchModel.", ""))) {
						valueList.add(tjz);
					}
					
				}

				Class myClass = searchModel.getClass();

				String setMethod = "setS" + lx.substring(1, lx.length());
				String[] newValue = valueList.toArray(new String[] {});

				if (newValue != null && newValue.length > 0) {

					Method methodName = myClass.getMethod(setMethod,
							new Class[] { String[].class });

					methodName.invoke(searchModel, (Object) newValue);

				}
			}
		}

		return searchModel;
	}
	
	/**
	 * 设置分页Model
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public Pages setPages(String defaultSize,HttpServletRequest request) {

		Pages pages = new Pages();

		// 结果集显示字段
		String editPageSize = request.getParameter("editPageSize");
		// 结果集显示字段
		String pagesize = request.getParameter("pagesize");

		if(Base.isNull(defaultSize)){
			defaultSize = "11";
		}
		
		if (!"yes".equalsIgnoreCase(editPageSize)) {
			pages.setPageSize(Integer.parseInt(defaultSize));
			request.setAttribute("autoPageSize", defaultSize);
		} else {
			pages.setPageSize(Integer.parseInt(pagesize));
			request.setAttribute("autoPageSize", pagesize);
		}

		// 当前页
		String currentPage = request.getParameter("currentPage");
		pages.setCurrentPage(Integer.parseInt(currentPage));

		return pages;
	}
	
	// 乱码字符
	private String[] lmzf = new String[] { "%20", "%21", "%25", "%5E", "%26",
			"%28", "%29", "%7B", "%7D", "%5B", "%5D", "%3C", "%3E", "%3F",
			"%0A", "%3B", "%3D","%2C","%27","%5C","%23","%22","%7E","%24","%7C","%0D","%3A"};

	// 转码字符
	private String[] zmzf = new String[] { " ", "!", "%", "^", "&", "(", ")",
			"{", "}", "[", "]", "<", ">", "?", " ", ";", "=","，","’","＼","#","“","~","＄","|","<br/>",":"};

	/**
	 * 转码
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public String unicode2Gbk(String receiveData) {
		StringBuffer rtn = new StringBuffer();
		while (receiveData!=null && receiveData.length() >= 1) {
			if (receiveData.startsWith("%u")) {
				// 如果开头的是汉字
				String temp = receiveData.substring(0, 6);
				// 截取前六位进行处理
				temp = temp.replace("%u", "");
				// 将编码类型u和web url 传递转换符%去掉
				int j = Integer.parseInt(temp, 16);
				// 将十六进制的字符串转化成整形
				rtn.append((char) j);
				// 将整形强制转成字符串，添加到返回队列里
				receiveData = receiveData.substring(6, receiveData.length());
				// 将剩余的字符串取出来进行下一步处理
			} else {// 如果开头的不是汉字（英文或数字）
				rtn.append(receiveData.substring(0, 1));
				// 直接添加到返回字符队列中
				receiveData = receiveData.substring(1, receiveData.length());
				// 将剩余的字符串取出来进行下一步处理
			}
		}
		
		String reslut = rtn.toString();

		for(int i=0;i<lmzf.length;i++){
			try {
				reslut = reslut.replaceAll(lmzf[i], zmzf[i]);
			} catch (Exception e) {
				// TODO: handle exception
				//System.out.println(zmzf[i]);
			}
		}
		
		return reslut;
	}
	
	/**
	 * 判断是否班长
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public boolean isBz(String xh) {
		BjtsxmDAO dao = new BjtsxmDAO();
		return dao.isBz(xh);
	}
	
	/**
	 * 判断是否存在
	 * 
	 * @author 伟大的路
	 * @throws Exception
	 */
	public Boolean isExists(String table, String pk, String pkValue) {

		boolean flag = false;

		try {
			flag = dao.isExists(table, pk, pkValue);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 执行补零操作
	 * 
	 * @author 伟大的路
	 * @throws Exception
	 */
	public String fillZero(String value) {

		if (!Base.isNull(value)) {

			// 首字母
			String first = value.substring(0, 1);

			if (".".equalsIgnoreCase(first)) {
				value = "0" + value;
			}
		}

		return value;
	}
	// ====================数组操作==============================
	
	/**
	 * 返回数组中的重复部分
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public String[] getRepeatArrValue(String[] arr1, String[] arr2) {

		ArrayList<String> list = new ArrayList<String>();

		if (arr1 != null && arr1.length > 0 && arr2 != null && arr2.length > 0) {

			for (int i = 0; i < arr1.length; i++) {

				for (int j = 0; j < arr2.length; j++) {

					if (arr1[i].equalsIgnoreCase(arr2[j])) {
						list.add(arr1[i]);
						break;
					}

				}
			}
		}

		return list.toArray(new String[] {});
	}

	/**
	 * 返回数组中的不重复部分
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public String[] getNoRepeatArrValue(String[] arr1, String[] arr2) {

		ArrayList<String> list = new ArrayList<String>();

		if (arr1 != null && arr1.length > 0 && arr2 != null && arr2.length > 0) {

			for (int i = 0; i < arr1.length; i++) {

				boolean flag = true;

				for (int j = 0; j < arr2.length; j++) {

					if (arr1[i].equalsIgnoreCase(arr2[j])) {
						flag = false;
						break;
					}				
				}
				
				if (flag) {
					list.add(arr1[i]);
				}
			}
		}

		return list.toArray(new String[] {});
	}
	
	/**
	 * 提供字符是否存在于数组中
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public Boolean isExistInArr(String[] arr, String variable) {

		boolean flag = false;

		if (arr != null && arr.length > 0) {

			for (int i = 0; i < arr.length; i++) {

				if (variable.equalsIgnoreCase(arr[i])) {

					flag = true;

					break;
				}
			}
		}

		return flag;
	}
	
	// 将两个数组和为一个数组
	public String[] unionArray(String[] array1, String[] array2) {
		if (array1 != null) {
			if (array2 != null) {
				String array[] = new String[array1.length + array2.length];
				copyArray(array1, array);
				for (int i = 0; i < array2.length; i++) {
					array[array1.length + i] = array2[i];
				}
				return array;
			} else {
				return array1;
			}
		} else {
			return array2;
		}
	}

	// 将一个数组copy到另一数组中
	public String[] copyArray(String[] fromArray, String[] toArray2) {
		if (fromArray != null && toArray2 != null) {
			int min = fromArray.length <= toArray2.length ? fromArray.length
					: toArray2.length;
			for (int i = 0; i < min; i++) {
				toArray2[i] = fromArray[i];
			}
			return toArray2;
		} else {
			if (toArray2 == null) {
				return fromArray;
			} else {
				return toArray2;
			}
		}
	}
	// ====================数组操作 end==============================
	
	/**
	 * 获得指定表的字段
	 * 
	 * @author qlj
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTableZdList(String tableName)
			throws Exception {
		return dao.getTableZdList(tableName);
	}

	/**
	 * 获取指定学生历年资助信息
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getXszzList(String xh){
		
		String tableName = "xszz_zzxmb";
		String[] colList = new String[]{"xmdm","xmmc","xmb","sqzq","shjb","xmlb"};
		List<HashMap<String, String>> zzInfo = dao.getTableListInfo(tableName, colList);
		
		if (null != zzInfo && zzInfo.size() > 0){
			int size = zzInfo.size();
			String[] xmdm = new String[size];
			String[] xmmc = new String[size]; 
			String[] xmb = new String[size]; 
			String[] sqzq = new String[size]; 
			String[] shjb = new String[size]; 
			String[] xmlb = new String[size];
			
			for (int i = 0 ; i < size ; i++){
				xmdm[i] = zzInfo.get(i).get("xmdm");
				xmmc[i] = zzInfo.get(i).get("xmmc");
				xmb[i] = zzInfo.get(i).get("xmb");
				sqzq[i] = zzInfo.get(i).get("sqzq");
				shjb[i] = zzInfo.get(i).get("shjb");
				xmlb[i] = zzInfo.get(i).get("xmlb");
			}
			
			return dao.getLnXszzList(xmb, xmdm, xmmc, sqzq, shjb,xmlb,xh);
		}
		
		return null;
	}
	
	/**
	 * 根据生源地代码获取生源地名称<br>
	 * 基本能满足学生基本信息里存储的省/市/县的转换
	 * @param syd 生源地代码  省/市/县
	 * @param splitFlg 传入的生源地代码按什么分割的？ '/'?
	 * @param returnSplitFlg 返回的生源地名称需要分割么，不需要传空就行。
	 * 						  别来null,分割符传的不对我也不甩你~
	 * @return String
	 */
	public String getSydmc(String syd,String splitFlg,String returnSplitFlg){
		
		if (StringUtils.isNotNull(syd) && syd.split(splitFlg) != null
				&& syd.split(splitFlg).length == 3) {
			return dao.getSydmc(syd, splitFlg, returnSplitFlg);
		}
		return "";
	}
	
	/**
	 * 将request中的数据转换为map
	 * @param request
	 * @param prefix 属性前缀
	 * */
	public HashMap<String, String> getValueMap(HttpServletRequest request){
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		if(request.getParameterMap() != null){
			paramMap.putAll(request.getParameterMap() );
		}		
		//解决类型为multipart/form-data 获取不到数据的问题
		String content_type = request.getContentType();
		if (content_type != null && content_type.indexOf("multipart/form-data") != -1) {
			Enumeration<String> enu = request.getParameterNames();
			String str = "";
			while (enu.hasMoreElements()){
				str = enu.nextElement();
				paramMap.put(str, request.getParameter(str));
			}
		}
		
		HashMap<String, String> valueMap = new HashMap<String, String>();
		
		
		for(Map.Entry<String, Object> entry : paramMap.entrySet()){
			String paramName = entry.getKey();
			Object value = entry.getValue();
			
				valueMap.put(paramName, value.getClass().isArray() 
					      ? (String)Array.get(value, 0) 
					      : value.toString());//字段值
				
									
		}
		
		return valueMap;
	}
	
	/**
	 * 根据表名，获取request中表字段的值
	 * @param request
	 * @param table 表名
	 * */
	public HashMap<String, String> getValueMap(HttpServletRequest request,String table){
		
		//根据表名获取表字段名
		List<HashMap<String,String>>tableInfo=selectTableInfo(table);
		//获取从request中取到的map信息
		HashMap<String, String> paramMap =(HashMap<String, String>)getValueMap(request);
		
		HashMap<String, String> valueMap = new HashMap<String, String>();
		//根据表字段从map中获取所需字段
		for(int i=0;i<tableInfo.size();i++){
			
			HashMap<String,String>tableMap=tableInfo.get(i);
			//获取表字段
			String comments=tableMap.get("name").toLowerCase();
			//根据表字段获取值
			String commentsValue=paramMap.get(comments);
			
			//值非空时才加载
			if(!Base.isNull(commentsValue)){
				valueMap.put(comments, commentsValue);
			}
		}
		return valueMap;
	}
	
	/**
	 * 根据表名，获取request中表字段的值
	 * @param request
	 * @param table 表名
	 * */
	public HashMap<String, String> getValueMap(HttpServletRequest request,String[]zd){
		
		
		//获取从request中取到的map信息
		HashMap<String, String> paramMap =(HashMap<String, String>)getValueMap(request);
		
		HashMap<String, String> valueMap = new HashMap<String, String>();
		//根据表字段从map中获取所需字段
		for(int i=0;i<zd.length;i++){
			
			//获取表字段
			String comments=zd[i].toLowerCase();
			//根据表字段获取值
			String commentsValue=paramMap.get(comments);
			
			//值非空时才加载
			if (commentsValue != null ) { 
				valueMap.put(comments, commentsValue);
			}
			
		}
		return valueMap;
	}
	
	/**
	 * 根据表名，获取request中表字段的值(值为Object)
	 * @param request
	 * @param table 表名
	 * */
	public HashMap<String, Object> getValueMapByObj(HttpServletRequest request,String[]zd){
		
		//获取从request中取到的map信息
		HashMap<String, String> paramMap =(HashMap<String, String>)getValueMap(request);
		
		HashMap<String, Object> valueMap = new HashMap<String, Object>();
		
		//根据表字段从map中获取所需字段
		for(Map.Entry<String, String> entry : paramMap.entrySet()){
			
			//获取键值
			String comments=entry.getKey();
			//获取值
			String commentsValue=entry.getValue();
			
			if(zd!=null && zd.length>0){
				
				setValueMap(valueMap, comments, commentsValue, zd);
				
			}else {
				
				setValueMap(valueMap, comments, commentsValue);
				
			}
			
		}
		return valueMap;
	}
	
	public void setValueMap(HashMap<String, Object> valueMap,String comments,String commentsValue,String[] zd){

		
		//获取第一个下划线的位置
		int firstIndex=comments.indexOf("_");
		//判断是否存在 “_” 仅在有下划线时才进行封装操作
		if(firstIndex!=-1){
				
			//下划线前表示类型的部分
			String format=comments.substring(0,firstIndex);
				
			//除去下划线以及下划线前的部分
			comments=comments.replace(format+"_","");
			for(int i=0;i<zd.length;i++){
					
				if(comments.equalsIgnoreCase(zd[i])){
					//根据类型判断 封装model的值
					if("str".equalsIgnoreCase(format)){
						String value=commentsValue;
						value=unicode2Gbk(value);
						valueMap.put(comments, commentsValue);
						
						valueMap.put("str_"+comments, commentsValue);
					}else if("array".equalsIgnoreCase(format)){
						
						String values=commentsValue;
						values=unicode2Gbk(values);
						String[]value=values.split("!!array!!");
						valueMap.put(comments, value);
						valueMap.put("array_"+comments, value);
					}
				}
			}
		}
		
	}
	
	
	public void setValueMap(HashMap<String, Object> valueMap,String comments,String commentsValue){

		
		//获取第一个下划线的位置
		int firstIndex=comments.indexOf("_");
		//判断是否存在 “_” 仅在有下划线时才进行封装操作
		if(firstIndex!=-1){
				
			//下划线前表示类型的部分
			String format=comments.substring(0,firstIndex);
				
			//除去下划线以及下划线前的部分
			comments=comments.replace(format+"_","");	
				
			//根据类型判断 封装model的值
			if("str".equalsIgnoreCase(format)){
				String value=commentsValue;
				value=unicode2Gbk(value);
				valueMap.put(comments, commentsValue);
				
			}else if("array".equalsIgnoreCase(format)){
				
				String values=commentsValue;
				values=unicode2Gbk(values);
				String[]value=values.split("!!array!!");
				valueMap.put(comments, value);
			}
				
		}
		
	}
	
	/**
	 * 根据表名，获取request中表字段的值(值为Object)
	 * @param request
	 * @param table 表名
	 * */
	public HashMap<String, Object> getValueMapByObj(HttpServletRequest request){
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		if(request.getParameterMap() != null){
			paramMap.putAll(request.getParameterMap() );
		}		
		//解决类型为multipart/form-data 获取不到数据的问题
		String content_type = request.getContentType();
		if (content_type != null && content_type.indexOf("multipart/form-data") != -1) {
			Enumeration<String> enu = request.getParameterNames();
			String str = "";
			while (enu.hasMoreElements()){
				str = enu.nextElement();
				paramMap.put(str, request.getParameter(str));
			}
		}
		
		HashMap<String, Object> valueMap = new HashMap<String, Object>();
		
		
		for(Map.Entry<String, Object> entry : paramMap.entrySet()){


			getValueByFormat(entry, valueMap);
									
		}
		
		return valueMap;
	}
	
	/**
	 * 根据指定的格式获取对象
	 * @return
	 */
	public void getValueByFormat(Map.Entry<String, Object>entry,
			HashMap<String, Object> valueMap){
		//键名
		String key = entry.getKey();
		//值
		Object value = entry.getValue();
		
		int firstIndex=key.indexOf("_");
		//判断是否存在 “_” 
		if(firstIndex!=-1){
			
				valueMap.put(key, value.getClass().isArray() 
					      ? (String)Array.get(value, 0) 
					      : value.toString());//字段值
				
			
		}
		
	}
		
	/**
	 * 将map中的值封装到model中
	 * map（将request 中的值封装为map）
	 * 需要封装的model类型
	 * 注:暂时只支持 字符串类型与字符类型
	 * （字符串：array_,字符:str_）
	 * @author qlj
	 * @throws Exception
	 */
	public void getModelValue(Object model,HashMap<String,Object>map)
			throws Exception {

		for(Map.Entry<String, Object> entry : map.entrySet()){
			
			//获取键值
			String key=entry.getKey();
			//获取值
			Object obj=entry.getValue();
			
			//获取第一个下划线的位置
			int firstIndex=key.indexOf("_");
			//判断是否存在 “_” 仅在有下划线时才进行封装操作
			if(firstIndex!=-1){
				
				//下划线前表示类型的部分
				String format=key.substring(0,firstIndex);
				
				//除去下划线以及下划线前的部分
				key=key.replace(format+"_","");
				
				//根据类型判断 封装model的值
				if("str".equalsIgnoreCase(format)){
					String value=(String)obj;
					value=unicode2Gbk(value);
					String methodName="set"+key.substring(0,1).toUpperCase()+key.substring(1,key.length());
					model.getClass().getMethod(methodName,
							(Class[]) new Class[] { String.class }).invoke(model,value);
					
				}else if("array".equalsIgnoreCase(format)){
					String values=(String)obj;
					values=unicode2Gbk(values);
					String methodName="set"+key.substring(0,1).toUpperCase()+key.substring(1,key.length());
					String[]value=values.split("!!array!!");
					model.getClass().getMethod(methodName, new Class[] { String[].class })
					.invoke(model, (Object) value);
				}
				
			}
			
		}
	}
	
	/**
	 * 将map中的值封装到model中
	 * map（将request 中的值封装为map）
	 * 需要封装的model类型
	 * 注:暂时只支持 字符串类型与字符类型
	 * （字符串：array_,字符:str_）
	 * @author qlj
	 * @throws Exception
	 */
	public void getModel(Object model,HashMap<String,String>map)
			throws Exception {

		for(Map.Entry<String, String> entry : map.entrySet()){
			
			//获取键值
			String key=entry.getKey();
			//获取值
			String str=entry.getValue();
				
			String methodName="set"+key.substring(0,1).toUpperCase()+key.substring(1,key.length());
			model.getClass().getMethod(methodName,
					(Class[]) new Class[] { String.class }).invoke(model,str);			
		}
	}
	
	/**
	 * 将request中的值封装到指定的model中
	 * 注：需封装属性必须指定类型；
	 * 字符串str_,字符串数组array_
	 * @param model
	 * @param request
	 */
	public void getModelValue(Object model,HttpServletRequest request){
		
		HashMap<String,Object>map=getValueMapByObj(request);
		
		try {
			getModelValue(model, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化 page、searchModel、user对象
	 * 
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void commInit(RequestForm rForm, Object model,
			HttpServletRequest request, User user) throws SecurityException,
			IllegalArgumentException, UnsupportedEncodingException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {

		// ==================分页 begin===================
		Pages pages = setPages("", request);
		// ==================分页 end========================

		// ==================高级查询 begin========================
		SearchModel searchModel = setSearchModel(rForm, request);
		// ==================高级查询 end=======================

		// ---------------------将 page、searchModel、user对象放入model中
		// begin-------------------
		model.getClass().getMethod("setPages",
				(Class[]) new Class[] { Pages.class }).invoke(model, pages);

		model.getClass().getMethod("setSearchModel",
				(Class[]) new Class[] { SearchModel.class }).invoke(model,
				searchModel);

		model.getClass().getMethod("setUser",
				(Class[]) new Class[] { User.class }).invoke(model, user);
		// ---------------------将 page、searchModel、user对象放入model中 end-------------------
	}
	
	//==================牛B begin==========================
	/**
	 * 【牛B】保存相关信息
	 * 
	 * @author luojw
	 * 
	 * @param model
	 * @param saveMap
	 * @param user
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean saveTable(Object model, HashMap<String, Object> saveMap,
			User user) throws Exception {

		Class myClass = model.getClass();

		// 操作表
		String tableName = (String) myClass.getMethod("getSave_table",
				(Class[]) null).invoke(model, (Object[]) null);
		// 主键
		String[] primary_key = (String[]) myClass.getMethod("getPrimary_key",
				(Class[]) null).invoke(model, (Object[]) null);
		// 单一字段
		String[] save_string_zd = (String[]) myClass.getMethod(
				"getSave_string_zd", (Class[]) null).invoke(model,
				(Object[]) null);
		// 批量字段
		String[] save_array_zd = (String[]) myClass.getMethod(
				"getSave_array_zd", (Class[]) null).invoke(model,
				(Object[]) null);

		//==================判断是否存在 begin============================
		StringBuilder pk = new StringBuilder();
		if (primary_key != null && primary_key.length > 0) {
			for (int i = 0; i < primary_key.length; i++) {
				if (i != 0) {
					pk.append("||");
				}
				pk.append(primary_key[i]);
			}
		}

		StringBuilder pkValue = new StringBuilder();
		if (primary_key != null && primary_key.length > 0) {
			for (int i = 0; i < primary_key.length; i++) {
				if (save_string_zd != null && save_string_zd.length > 0) {
					for (int j = 0; j < save_string_zd.length; j++) {
						if (primary_key[i].equalsIgnoreCase(save_string_zd[j])) {
							String key = "str_" + save_string_zd[j];
							String value = (String)saveMap.get(key);
							pkValue.append(unicode2Gbk(value));
						}
					}
				}
			}
		}

		//判断是否存在
		boolean flag = isExists(tableName, pk.toString(), pkValue.toString());
		
		//==================判断是否存在 end============================
		
		// ==================构建保存参数 begin============================
		ArrayList<String> value = new ArrayList<String>();
		if (save_string_zd != null && save_string_zd.length > 0) {
			for (int i = 0; i < save_string_zd.length; i++) {
				String key = "str_" + save_string_zd[i];
				value.add(unicode2Gbk((String) saveMap.get(key)));
			}
		}
		
		String[] key = unionArray(save_string_zd, save_array_zd);
		// ==================构建保存参数 end============================
		
		if (flag) {
			// 修改
			flag = dao.updateTable(tableName, key, value
					.toArray(new String[] {}), pk.toString(), pkValue
					.toString(), user);
		} else {
			// 增加
			flag = dao.insertTable(tableName, key, value
					.toArray(new String[] {}), user);
		}

		return flag;
	}
	
	/**
	 * 【牛B】保存相关信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getDetail(Object model)
			throws Exception {

		Class myClass = model.getClass();

		// 查询表
		String tableName = (String) myClass.getMethod("getSearch_table",
				(Class[]) null).invoke(model, (Object[]) null);
		// 显示字段
		String[] key = (String[]) myClass.getMethod("getDetail_zd",
				(Class[]) null).invoke(model, (Object[]) null);

		// 主键值
		String[] pkValue = (String[]) myClass.getMethod("getPkValue",
				(Class[]) null).invoke(model, (Object[]) null);

		HashMap<String, String> map = dao.getDetail(tableName, key, pkValue[0]);

		return map;
	}
	
	/**
	 * 【牛B】删除相关信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean deleteTable(Object model, User user) throws Exception {

		Class myClass = model.getClass();

		// 操作表
		String tableName = (String) myClass.getMethod("getSave_table",
				(Class[]) null).invoke(model, (Object[]) null);

		// 主键
		String[] primary_key = (String[]) myClass.getMethod("getPrimary_key",
				(Class[]) null).invoke(model, (Object[]) null);

		// 主键值
		String[] pkValue = (String[]) myClass.getMethod("getPkValue",
				(Class[]) null).invoke(model, (Object[]) null);

		StringBuilder pk = new StringBuilder();
		if (primary_key != null && primary_key.length > 0) {
			for (int i = 0; i < primary_key.length; i++) {
				if (i != 0) {
					pk.append("||");
				}
				pk.append(primary_key[i]);
			}
		}

		boolean flag = dao.deleteTable(tableName, pk.toString(), pkValue, user);

		return flag;
	}
	
	/**
	 * 【牛B】获取Request地址
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String getRequestURL(HttpServletRequest request) {
		if (request == null) {
			return "";
		}
		String url = "";
		url = request.getContextPath();
		url = url + request.getServletPath();

		java.util.Enumeration names = request.getParameterNames();
		int i = 0;
		if (!"".equals(request.getQueryString())
				|| request.getQueryString() != null) {
			url = url + "?" + request.getQueryString();
		}

		if (names != null) {
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				if (i == 0) {
					url = url + "?";
				} else {
					url = url + "&";
				}
				i++;

				String value = request.getParameter(name);
				if (value == null) {
					value = "";
				}

				url = url + name + "=" + value;
				try {
					// java.net.URLEncoder.encode(url, "ISO-8859");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		try {
			// String enUrl = java.net.URLEncoder.encode(url, "utf-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return url;
	}
	//==================牛B end============================
}