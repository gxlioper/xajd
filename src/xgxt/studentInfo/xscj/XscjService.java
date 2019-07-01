package xgxt.studentInfo.xscj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.ahjg.PjpyAhjgActionForm;
import xgxt.pjpy.ahjg.PjpyAhjgDAO;
import xgxt.pjpy.ahjg.PjpyAhjgXscjQryModel;
import xgxt.pjpy.comm.zhcp.zczf.ZhcpZczfForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;


/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description: 池州学院学生成绩维护service
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: honglin
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2012-03-14
 * </p>
 */
public class XscjService extends CommService{
	
	private XscjDAO dao = null;//数据库操作DAO
	/**
	 * 查询教务版本 1为学年,0为过渡,空则为其他公司教务
	 * @return
	 */
	public String getJwFlag() {
		dao = new XscjDAO();
		return dao.getJwFlag();
	}
	
	/**
	 * 获得学生考试性质列表
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getKsxzList() {
		dao = new XscjDAO();
		return dao.getKsxzList();
	}
	
	public List<HashMap<String, String>> getDjksmc() {
		dao = new XscjDAO();
		return dao.getDjksmc();
	}

	/**
	 * 通过传入TYPE来选择不同的页面查询TITLE
	 * @param enCol
	 * @param cnCol
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSearchTitle(int iType) throws Exception {
		dao = new XscjDAO();
		List<HashMap<String, String>> listTopTr = new ArrayList<HashMap<String,String>>();
		String[] enCol = null;
		String[] cnCol = null;
		if (iType == 1) {//如果是1则输出学生成绩查询表头
			enCol = new String[]{"xn", "xq", "xh", "xm", "nj", "xymc", "bjmc", "kcsbm","kcxz", "cj"};
			cnCol = new String[]{"学年","学期","学号","姓名","年级",  Base.YXPZXY_KEY+"名称","班级名称","课程名称","课程性质","成绩"};
		}//end if
		else if (iType == 2) {//如果是2则输出班级补考率查询表头
			enCol = new String[]{"xn||bjdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "bzxm" , "xsrs", "bzr", "zccql"};
			cnCol = new String[]{"主键", "bgcolor", "行号", "学年",  Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "班长姓名", "学生人数", "班主任", "班级补考率"};
		}//end if
		else if (iType == 3) {//如果是3则输出早操出勤率查询表头
			enCol = new String[]{"xn||bjdm", "bgcolor", "rownum", "xn", "xq", "xymc", "zymc", "bjmc", "bzxm" , "xsrs", "bzr", "zccql"};
			cnCol = new String[]{"主键", "bgcolor", "行号", "学年", "学期",  Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "班长姓名", "学生人数", "班主任", "早操出勤率"};
		}//end if
		else if (iType == 4) {//如果是4则输出先进集体评比分配表头
			enCol = new String[]{"rownum","rychdm","rychmc","pxbl","bjzs"};
			cnCol = new String[]{"行号","荣誉称号代码","荣誉称号名称","评选比例", "评选班级总数"};
		}
		else if (iType == 5) {
			enCol = new String[]{"rownum","rychdm","rychmc","pxbl","bjzs"};
			cnCol = new String[]{"行号","荣誉称号代码","荣誉称号名称","评选比例", "评选人数或所占比例"};
		}
		else if (iType == 6) {
			enCol = new String[]{"xn", "xq", "xh", "xm", "nj", "xymc", "bjmc", "djksmc", "ksrq", "cj"};
			cnCol = new String[]{"学年","学期","学号","姓名","年级",  Base.YXPZXY_KEY+"名称","班级名称","等级考试名称", "考试日期" , "成绩"};
		}
		else if (iType == 7) {//如果是1则输出学生成绩查询表头
			enCol = new String[]{"xn", "xq", "xh", "xm", "xymc", "bjmc", "kcsbm","kcxz","khfs","cj","xf","pjf"};
			cnCol = new String[]{"学年","学期","学号","姓名", Base.YXPZXY_KEY+"名称","班级名称","课程名称","课程性质","考核方式","成绩","学分", "总科平均分"};
		}
		else if (iType == 8) {//如果是1则输出学生成绩查询表头
			enCol = new String[]{"xn", "xq", "xh", "xm", "xymc","zymc", "bjmc", "kcsbm","khfs","cj","pjf"};
			cnCol = new String[]{"学年","学期","学号","姓名", Base.YXPZXY_KEY+"名称","专业名称","班级名称","课程名称","考核方式","成绩", "总科平均分"};
		} else if (iType==9) {
			enCol = new String[]{"xn", "xq", "xh", "xm", "xymc", "bjmc", "kcsbm","kcxz", "cj", "kscj", "kccj"};
			cnCol = new String[]{"学年","学期","学号","姓名", Base.YXPZXY_KEY+"名称","班级名称","课程名称","课程性质","成绩", "考试课平均成绩", "考查课平均成绩"};
		} else if (iType == 10) {
			enCol = new String[] { "xn", "xqmc", "xh", "xm", "xymc", "bjmc",
					"kcmc", "kcxz", "xf", "cj", "bkcx" };
			cnCol = new String[] { "学年", "学期", "学号", "姓名",  Base.YXPZXY_KEY+"名称", "班级名称",
					"课程名称", "课程性质", "学分", "成绩", "补考或重修" };
			// TODO
		}
		listTopTr = dao.getSearchTitle(enCol, cnCol);
		return listTopTr;
	}
	/**
	 * 等级考试成绩查询结果
	 * @param xscjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKscjResult(
			XscjModel xscjModel,
			XscjActionForm dataSearchForm,User user,String type) throws Exception {
		dao = new XscjDAO();
		String[] colList= severTop(getToptr(dataSearchForm,user,type),"en");
		return dao.getKscjResult(xscjModel, dataSearchForm,user,colList);
	}
	/**
	 * 等级考试成绩查询结果数
	 * @param xscjModel
	 * @return
	 * @throws Exception
	 */
	public int getKscjResultNum(XscjModel xscjModel) throws Exception {
		dao = new XscjDAO();
		return dao.getKscjResultNum(xscjModel);
	}
	/**
	 * 学生成绩查询结果
	 * @param xscjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXscjResult(
			XscjModel xscjModel,
			XscjActionForm dataSearchForm, User user,String type) throws Exception {
		dao = new XscjDAO();
		String[] colList= severTop(getToptr(dataSearchForm,user,type),"en");
		List<String[]> listRs = dao.getXscjResult(xscjModel,
				dataSearchForm,user,colList);
		return listRs;
	}
	/**
	 * 分离top列表 根据获取类型获取（en,cn）
	 * 
	 * @param topList
	 * @param hqlx
	 * @return List<HashMap<String, String>>
	 */
	public String[] severTop(List<HashMap<String, String>> topList, String hqlx) {

		List<String> outList = new ArrayList<String>();
		for (int i = 0; i < topList.size(); i++) {
			HashMap<String, String> topMap = topList.get(i);
			//System.out.println(topMap.get(hqlx));
			outList.add(topMap.get(hqlx));
		}
		return outList.toArray(new String[] {});
	}
	
	/**
	 * 学生成绩查询结果数
	 * 
	 * @param xscjModel
	 * @param inCol
	 * @param opCol
	 * @return
	 * @throws Exception
	 */
	public int getXscjResultNum(
			XscjModel xscjModel, String userType, String userName) throws Exception {
		dao = new XscjDAO();
		return dao.getXscjResultNum(xscjModel,userType,userName);
	}
	
	/**
	 * 获取列
	 * @param model
	 * @param user
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getKindChoose(XscjActionForm model, User user,String type) {
		DAO commDao=DAO.getInstance();
		//字段代码（COLLIST）
		List<String>colArr=new ArrayList<String>();
		//字段名称（TOP）
		List<String>topArr=new ArrayList<String>();
		
		if(type=="1"){
			colArr.add("xn");
			topArr.add("学年");
			
			colArr.add("xqmc");
			topArr.add("学期");
			
			colArr.add("xh");
			topArr.add("学号");
			
			colArr.add("xm");
			topArr.add("姓名");
			
			colArr.add("nj");
			topArr.add("年级");
			
			colArr.add("xymc");
			topArr.add(Base.YXPZXY_KEY+"名称");
			
			colArr.add("bjmc");
			topArr.add("班级名称");
			
			colArr.add("kcmc");
			topArr.add("课程名称");
			
			colArr.add("kcxz");
			topArr.add("课程性质");
			
			colArr.add("cj");
			topArr.add("成绩");
		}else{//成绩等级
			
			colArr.add("xn");
			topArr.add("学年");
			
			colArr.add("xqmc");
			topArr.add("学期");
			
			colArr.add("xh");
			topArr.add("学号");
			
			colArr.add("xm");
			topArr.add("姓名");
			
			colArr.add("nj");
			topArr.add("年级");
			
			colArr.add("xymc");
			topArr.add(Base.YXPZXY_KEY+"名称");
			
			colArr.add("bjmc");
			topArr.add("班级名称");
			
			colArr.add("djksmc");
			topArr.add("等级考试名称");
			
			colArr.add("ksrq");
			topArr.add("考试日期");
			
			colArr.add("cj");
			topArr.add("成绩");
			
		}
		
		return commDao.arrayToList(colArr.toArray(new String[]{}), topArr.toArray(new String[]{}));
	}

	/**
	 * 保存列选
	 * @param model
	 * @param user
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean saveKindChoose(XscjActionForm model,User user,String type) throws Exception{
		
		DAO commDao=DAO.getInstance();
		dao = new XscjDAO();
		String yhm=user.getUserName();
		String path="xsxx_xscjQuery.do";
		String yhlx="tea";
		StringBuilder xszd=new StringBuilder();
		String []bxszd={"xh","xm"};
		String []xszdArr=null;
		if("stu".equalsIgnoreCase(user.getUserType())){
			yhlx="stu";
		}
		xszdArr=commDao.unionArray(bxszd, model.getXszdArr());
		for(int i=0;i<xszdArr.length;i++){
			if(i!=0){
				xszd.append(",");
			}
			xszd.append(xszdArr[i]);
		}
		
		return checkBoolean(dao.saveKindChoose(yhm, yhlx, xszd.toString(), path,type));
	}
	/**
	 * 判断批量操作是否成功
	 * @param returnV
	 * @return
	 */
	public boolean checkBoolean(int []returnV){
		boolean blog=true;
		for(int i=0;i<returnV.length;i++){
			if(returnV[i]==0){
				blog=false;
			}
		}
		return blog;	
	}
	/**
	 * 获取指定用户已设置显示列
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getCheckKind(XscjActionForm model, User user,String type) {
		// 获取指定用户选中的列
		dao = new XscjDAO();
		HashMap<String, String> kind = dao.getCheckKind(model, user,type);
		List<HashMap<String, String>> checkKind = new ArrayList<HashMap<String, String>>();
		// 需显示字段
		String xszd = kind.get("xxszd");
		String[] xszdArr = null;
		if (!Base.isNull(xszd)) {
			xszdArr = xszd.split(",");
			for (int i = 0; i < xszdArr.length; i++) {
				HashMap<String, String> kindMap = new HashMap<String, String>();
				kindMap.put("zd", xszdArr[i]);
				checkKind.add(kindMap);
			}
		}
		return checkKind;
	}
	/**
	 * 设置Request 的值
	 * 
	 * @param form
	 * @param request
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
	 * 获取表头显示字段
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getToptr(XscjActionForm model, User user,String type) {
		//获取指定用户已设置显示列
		List<HashMap<String, String>> kindList = getCheckKind(model, user,type);
		List<HashMap<String, String>> top=getTop(model,user,type);
		List<HashMap<String, String>> topTr=new ArrayList<HashMap<String,String>>();
		HashMap<String, String>map=new  HashMap<String, String>();
//		map.put("en", "pkValue");
//		map.put("cn", "主键");
//		topTr.add(map);
		//取交集
		if (kindList != null && kindList.size() > 0) {
			for (int i = 0; i < kindList.size(); i++) {
				HashMap<String, String> kindMap = kindList.get(i);
				HashMap<String, String> topTrMap = new HashMap<String, String>();
				for (int j = 0; j < top.size(); j++) {
					HashMap<String, String> topMap = top.get(j);

					if (kindMap.get("zd").equalsIgnoreCase(topMap.get("en"))) {
						topTrMap.put("en", topMap.get("en"));
						topTrMap.put("cn", topMap.get("cn"));
						topTr.add(topTrMap);
						break;
					}
				}
				
			}
			
		}else{
			return top;
		}
		
		if(Globals.XXDM_GDJSZYJSXY.equalsIgnoreCase(Base.xxdm)
				&& !Base.isNull(model.getKcmc())){
			
			HashMap<String, String> topMap = new HashMap<String, String>();
			
			topMap.put("en","pm");
			topMap.put("cn","课程排名");
			topTr.add(topMap);
		}
		return topTr;
	}
	/**
	 * 获取综测总分表头字段
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getTop(XscjActionForm model, User user,String type) {
		DAO commDao=DAO.getInstance();
		//字段代码（COLLIST）
		List<String>colArr=new ArrayList<String>();
		//字段名称（TOP）
		List<String>topArr=new ArrayList<String>();
		if(type=="1"){
			colArr.add("xn");
			topArr.add("学年");
			
			colArr.add("xqmc");
			topArr.add("学期");
			
			colArr.add("xh");
			topArr.add("学号");
			
			colArr.add("xm");
			topArr.add("姓名");
			
			colArr.add("nj");
			topArr.add("年级");
			
			colArr.add("xymc");
			topArr.add(Base.YXPZXY_KEY+"名称");
			
			colArr.add("bjmc");
			topArr.add("班级名称");
			
			colArr.add("kcmc");
			topArr.add("课程名称");
			
			colArr.add("kcxz");
			topArr.add("课程性质");
			
			colArr.add("cj");
			topArr.add("成绩");
		}else{//成绩等级
			
			colArr.add("xn");
			topArr.add("学年");
			
			colArr.add("xqmc");
			topArr.add("学期");
			
			colArr.add("xh");
			topArr.add("学号");
			
			colArr.add("xm");
			topArr.add("姓名");
			
			colArr.add("nj");
			topArr.add("年级");
			
			colArr.add("xymc");
			topArr.add(Base.YXPZXY_KEY+"名称");
			
			colArr.add("bjmc");
			topArr.add("班级名称");
			
			colArr.add("djksmc");
			topArr.add("等级考试名称");
			
			colArr.add("ksrq");
			topArr.add("考试日期");
			
			colArr.add("cj");
			topArr.add("成绩");
			
		}
		
		
		if(Globals.XXDM_GDJSZYJSXY.equalsIgnoreCase(Base.xxdm)
				&& !Base.isNull(model.getKcmc())){
			colArr.add("pm");
			topArr.add("课程排名");
		}
		
		return commDao.arrayToList(colArr.toArray(new String[]{}), topArr.toArray(new String[]{}));
	}
	
	/**
	 * 获取某学年、某学期xx班级课程
	 */
	public List<HashMap<String,String>>getBjkcInfo(XscjActionForm model){
		dao = new XscjDAO();
		return dao.getBjkcInfo(model);
	}
	
//	 -----------------------2012.4.9 qlj begin --------------------------
	/**
	 * 学生成绩查询结果(广东建设职业技术学院个性化)
	 * @param xscjModel
	 * @param inCol
	 * @param opCol
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXscjList(
			XscjModel xscjModel,
			XscjActionForm dataSearchForm, User user,String type) throws Exception {
		dao = new XscjDAO();
		String[] colList= severTop(getToptr(dataSearchForm,user,type),"en");
		return dao.getXscjList(xscjModel, dataSearchForm, user, colList);
	}
}
