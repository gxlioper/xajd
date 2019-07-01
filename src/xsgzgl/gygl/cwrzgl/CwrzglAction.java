package xsgzgl.gygl.cwrzgl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xsgzgl.gygl.comm.GyglNewInit;

public class CwrzglAction extends BasicAction {
	/**
	 * 床位入住管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward cwrzglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwrzgl_cwrz.do");
		HttpSession session=request.getSession();
		boolean isFdy=false;
		boolean isBzr=false;
		if(session.getAttribute("fdyQx")!=null){
			isFdy=Boolean.parseBoolean(session.getAttribute("fdyQx").toString());
		}
		if(session.getAttribute("bzrQx")!=null){
			isBzr=Boolean.parseBoolean(session.getAttribute("bzrQx").toString());
		}
		if(("bjdm".equals(GyglNewInit.CWFPDX)||"zydm".equals(GyglNewInit.CWFPDX))&&(isFdy||isBzr)){//三级分配，辅导员或班主任
			return cwrzglManage_xy(mapping, form, request, response);
		}else if("xydm".equals(GyglNewInit.CWFPDX)&&(isFdy||isBzr)){//三级分配，非辅导员或班主任
			 String msg = "您没有本模块的操作权限，请确认！";
			   request.setAttribute("yhInfo", msg);
			   return new ActionForward("/yhInfo.do", false);
		}
		CwrzglForm myForm = (CwrzglForm) form;
		CwrzglService service = new CwrzglService();
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		// ============= 初始化各变量的值 ============
		// 获得公寓设置相关信息
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);

		// 结果集显示字段
		String[] colList = new String[]{"年级",Base.YXPZXY_KEY,"性别","总人数","已入住人数","未入住人数","总床位数","已入住床位数","未入住床位数"};

		// =============== 执行查询操作 ===========
		
//		myForm.getSearchModel().setSearch_tj_nj(new String[]{Base.currNd});
		
		rsArrList = service.getCwrzglInfoList(myForm,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("topTr", service.getToplist(colList));
		
		FormModleCommon.commonRequestSet(request);
		// ================= end =====================

		return mapping.findForward("success");
	}
	/**
	 * 床位入住手动分配	
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-住宿管理-床位入住管理-{doType}维护CWH:{checkbox_cwh}")
	public ActionForward cwrzglSdfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwfpgl_cwfp.do");
		HttpSession session=request.getSession();
		boolean isFdy=false;
		boolean isBzr=false;
		if(session.getAttribute("fdyQx")!=null){
			isFdy=Boolean.parseBoolean(session.getAttribute("fdyQx").toString());
		}
		if(session.getAttribute("bzrQx")!=null){
			isBzr=Boolean.parseBoolean(session.getAttribute("bzrQx").toString());
		}
		if("bjdm".equals(GyglNewInit.CWFPDX)&&(isFdy||isBzr)){//辅导员或班主任
			return cwrzglSdfp_xy(mapping, form, request, response);
		}else if(("xydm".equals(GyglNewInit.CWFPDX)&&(isFdy||isBzr))){//三级分配，非辅导员或班主任
			 String msg = "您没有本模块的操作权限，请确认！";
			   request.setAttribute("yhInfo", msg);
			   return new ActionForward("/yhInfo.do", false);
		}else if("zydm".equals(GyglNewInit.CWFPDX)&&(isFdy||isBzr)){//辅导员或班主任
			return cwrzglSdfp_zy(mapping, form, request, response);
		}
		
//		HttpSession session = request.getSession();
		String rzsj=(String)session.getAttribute("temp_gygl_cwrz_rzsj");//入住时间
		CwrzglForm myForm = (CwrzglForm) form;
		if(Base.isNull(myForm.getRzsj())){
			myForm.setRzsj(rzsj);
			myForm.setTsyy((String)session.getAttribute("temp_gygl_cwrz_tsyy"));
			myForm.setBz((String)session.getAttribute("temp_gygl_cwrz_bz"));
		}else{
			session.setAttribute("temp_gygl_cwrz_rzsj", myForm.getRzsj());
			session.setAttribute("temp_gygl_cwrz_tsyy", myForm.getTsyy());
			session.setAttribute("temp_gygl_cwrz_bz", myForm.getBz());
		}
		
		CwrzglService service = new CwrzglService();
		// ============= 初始化各变量的值 ============
		// 获得公寓设置相关信息
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);
		
		
		//参数的获取与设置
		String primarykey_checkVal=request.getParameter("primarykey_checkVal");//主键"学院_年级_性别"
		if(!Base.isNull(primarykey_checkVal)){
			String[] temp=primarykey_checkVal.split("_");
			myForm.setXydm(temp[0]);
			myForm.setNj(temp[1]);
			myForm.setXb(temp[2]);
			myForm.setRzqk("未入住");
			myForm.setCwzt("未入住");
		}
		
		//楼栋列表
		List<HashMap<String,String>> ldlist=service.getLdList(myForm);
		if(ldlist!=null&&ldlist.size()==1){//若只有一个分配的楼栋，则直接选中查询
			myForm.setLddm(ldlist.get(0).get("lddm"));
		}
		
		
		//寝室床位分配的保存与取消
		boolean isKqxrz=service.isKqxrz(myForm);//是否可取消入住
		String doType=request.getParameter("doType");
		if("save".equals(doType)){//保存学生入住信息
			boolean b=service.saveXsrzfpxx(request, myForm);
			request.setAttribute("back", b);
			
		}else if("qxfp".equals(doType)&&isKqxrz){//取消寝室床位分配
			boolean b=service.qxXsrzfpxx(request, myForm);
			request.setAttribute("back", b);
		}
		
		// =============== 执行查询操作 ===========
		if(!Base.isNull(myForm.getLddm())){//楼栋不为空时进行查询
			service.tjldFpxx(request, myForm);
		}else{
			request.setAttribute("ldjbxx", new HashMap<String,String>());
			request.setAttribute("ldlcxx", new ArrayList<HashMap<String,String>>());
			request.setAttribute("ldlcqscwxxb", new ArrayList<HashMap<String,String>>());
			request.setAttribute("xsxxlist", new ArrayList<String[]>());
		}
		
		// ================= end =====================
		request.setAttribute("ldlist", ldlist);//楼栋列表
		HashMap<String,String> rs=new HashMap<String, String>();
		rs.put("lddm", myForm.getLddm());
		rs.put("nj", myForm.getNj());
		rs.put("xydm", myForm.getXydm());
		rs.put("xb", myForm.getXb());
		request.setAttribute("rs", rs);
		request.setAttribute("tsyylist", service.getTsyyList());
		request.setAttribute("rzyylist", service.getRzyyList());
		request.setAttribute("xynj_tjxx", service.getCwfpglInfo(myForm));//学院年级人数及分配入住数据
		request.setAttribute("xynjxbld_tjxx", service.getXynjxbLdfpxx(myForm));//学院年级性别楼栋统计情况
		
		FormModleCommon.setNjXyZyBjList(request);
//		request.setAttribute("njList", Base.getNjList()) ;//年级
//		request.setAttribute("xyList", Base.getXyList()) ;//学院
//		request.setAttribute("zyList", Base.getZyMap().get(myForm.getXydm()));
//		request.setAttribute("bjList", Base.getBjMap().get(myForm.getXydm()+"!!"+myForm.getZydm()+"!!"+myForm.getNj()));
		request.setAttribute("isKqxrz", isKqxrz);
		request.setAttribute("act", request.getParameter("act"));

		// 学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		
		request.setAttribute("xxdm", Base.xxdm);
		
		//新疆医科大学厚博学院个性化
		if("13560".equalsIgnoreCase(Base.xxdm)){
			request.setAttribute("yzlbList", service.getYzlbList());
		}
		
		return mapping.findForward("sdfp");
	}
	
	/** 
	 * @描述:(床位分配到专业时，辅导员用户分配学生入住功能)
	 * @作者：cmj[工号：913]
	 * @日期：2013-9-5 下午03:20:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemLog(description="访问公寓管理-住宿管理-床位入住管理-{doType}维护CWH:{checkbox_cwh}")
	private ActionForward cwrzglSdfp_zy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("path", "gyglnew_cwfpgl_cwfp.do");
		HttpSession session = request.getSession();
		String rzsj=(String)session.getAttribute("temp_gygl_cwrz_rzsj");//入住时间
		CwrzglForm myForm = (CwrzglForm) form;
		if(Base.isNull(myForm.getRzsj())){
			myForm.setRzsj(rzsj);
			myForm.setTsyy((String)session.getAttribute("temp_gygl_cwrz_tsyy"));
			myForm.setBz((String)session.getAttribute("temp_gygl_cwrz_bz"));
		}else{
			session.setAttribute("temp_gygl_cwrz_rzsj", myForm.getRzsj());
			session.setAttribute("temp_gygl_cwrz_tsyy", myForm.getTsyy());
			session.setAttribute("temp_gygl_cwrz_bz", myForm.getBz());
		}
		
		CwrzglService service = new CwrzglService();
		// ============= 初始化各变量的值 ============
		// 获得公寓设置相关信息
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);
		
		
		//参数的获取与设置
		String primarykey_checkVal=request.getParameter("primarykey_checkVal");//主键"学院_年级_性别"
		if(!Base.isNull(primarykey_checkVal)){
			String[] temp=primarykey_checkVal.split("_");
			myForm.setXydm(temp[0]);
			myForm.setNj(temp[1]);
			myForm.setBjdm(temp[3]);
			myForm.setXb(temp[4]);
			myForm.setRzqk("未入住");
			myForm.setCwzt("未入住");
		}else{
			myForm.setNj(request.getParameter("hidden_nj"));
			myForm.setXydm(request.getParameter("hidden_xydm"));
		}
		
		//楼栋列表
		List<HashMap<String,String>> ldlist=service.getLdList_zy(myForm);
		if(ldlist!=null&&ldlist.size()==1){//若只有一个分配的楼栋，则直接选中查询
			myForm.setLddm(ldlist.get(0).get("lddm"));
		}
		
		
		//寝室床位分配的保存与取消
		boolean isKqxrz=service.isKqxrz(myForm);//是否可取消入住
		String doType=request.getParameter("doType");
		if("save".equals(doType)){//保存学生入住信息
			boolean b=service.saveXsrzfpxx(request, myForm);
			request.setAttribute("back", b);
			
		}else if("qxfp".equals(doType)&&isKqxrz){//取消寝室床位分配
			boolean b=service.qxXsrzfpxx(request, myForm);
			request.setAttribute("back", b);
		}
		
		// =============== 执行查询操作 ===========
		if(!Base.isNull(myForm.getLddm())){//楼栋不为空时进行查询
			service.tjldFpxx_zy(request, myForm);
		}else{
			request.setAttribute("ldjbxx", new HashMap<String,String>());
			request.setAttribute("ldlcxx", new ArrayList<HashMap<String,String>>());
			request.setAttribute("ldlcqscwxxb", new ArrayList<HashMap<String,String>>());
			request.setAttribute("xsxxlist", new ArrayList<String[]>());
		}
		
		// ================= end =====================
		request.setAttribute("ldlist", ldlist);//楼栋列表
		HashMap<String,String> rs=new HashMap<String, String>();
		rs.put("lddm", myForm.getLddm());
		rs.put("nj", myForm.getNj());
		rs.put("xydm", myForm.getXydm());
		rs.put("bjdm", myForm.getBjdm());
		rs.put("xb", myForm.getXb());
		request.setAttribute("rs", rs);
		request.setAttribute("tsyylist", service.getTsyyList());
		request.setAttribute("rzyylist", service.getRzyyList());
		request.setAttribute("xynj_tjxx", service.getCwfpglInfo_xy(myForm));//学院年级人数及分配入住数据
		request.setAttribute("xynjxbld_tjxx", service.getXynjxbLdfpxx_zy(myForm));//学院年级性别楼栋统计情况
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
//		request.setAttribute("njList", Base.getNjList()) ;//年级
//		request.setAttribute("xyList", Base.getXyList()) ;//学院
//		request.setAttribute("zyList", Base.getZyMap().get(myForm.getXydm()));
//		request.setAttribute("bjList", Base.getBjMap().get(myForm.getXydm()+"!!"+myForm.getZydm()+"!!"+myForm.getNj()));
		request.setAttribute("isKqxrz", isKqxrz);
		request.setAttribute("user", getUser(request));
		request.setAttribute("act", request.getParameter("act"));
		boolean isFdy=(Boolean)session.getAttribute("isFdy");
		boolean isBzr=(Boolean)session.getAttribute("isBzr");
		request.setAttribute("isFdy", isFdy);
		request.setAttribute("isBzr", isBzr);
		
		return mapping.findForward("sdfp_zy");
	}
	/** 
	 * 转到数据检测和导入页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@SystemLog(description="访问公寓管理-住宿管理-床位入住管理-导入TABLENAME:{tableName}")
	public ActionForward importData(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		String tableName = request.getParameter("tableName");//视图名
		String realTable = request.getParameter("realTable");//表名
		
		request.setAttribute("dzyDdTab", request.getParameter("dzyDdTab"));
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		
		String act=request.getParameter("act");
		//导入数据
		if("import".equals(act)){
			uploadFile(mapping, form, request, response);
			
			CwrzglService service = new CwrzglService();
			String back= service.importData(request,response);//导入数据
			String sfdcExcel=(String)request.getAttribute("sfdcExcel");
			if("yes".equals(sfdcExcel)){
				return mapping.findForward("");
			}
			request.setAttribute("back", back);
			
		}
		return mapping.findForward("importData");
	}
	
	/**
	 * 文件上传 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	@SystemLog(description="访问公寓管理-住宿管理-床位入住管理-上传FNAME:{userName}")
	private ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//该处需要验证超级管理员权限
		String dir = servlet.getServletContext().getRealPath("/upload");
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
		HttpSession session = request.getSession();
		String fName = (String) session.getAttribute("userName");
		String tabName = request.getParameter("tabName");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		fName += date.toString().substring(0, 19);
		fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		CwrzglForm hff = (CwrzglForm) form;
		FormFile file = hff.getImpFilePath();	
//		if (file == null || (file.getFileName() == null || file.getFileName().trim().equals(""))) {
//			file = hff.getCheckFilePath();
			if(file == null){
				return mapping.findForward("false");
			}
//		}
		int size = file.getFileSize();
		InputStream in = file.getInputStream();
		String filePath = dir + "/" + fName + ".xls";
		OutputStream out = new FileOutputStream(filePath);
		int bytesRead = 0;
		byte[] buffer = new byte[size];
		while ((bytesRead = in.read(buffer, 0, size)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		out.close();
		in.close();
		request.setAttribute("tabName", tabName);
		request.setAttribute("filepath", filePath);
		request.setAttribute("moditag", request.getParameter("moditag"));
		return mapping.findForward("success");
	}
	
	//#############################################以下为学院部分(暂未修改)
	/**
	 * 床位入住管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward cwrzglManage_xy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwrzgl_cwrz.do");
		CwrzglForm myForm = (CwrzglForm) form;
		CwrzglService service = new CwrzglService();
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		// ============= 初始化各变量的值 ============
		// 获得公寓设置相关信息
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);

		// 结果集显示字段
		String[] colList = new String[]{"年级","班级","性别","总人数","已入住人数","未入住人数","总床位数","已入住床位数","未入住床位数"};

		// =============== 执行查询操作 ===========
				
		rsArrList = service.getCwfpglInfoList_xy(request,myForm);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("topTr", service.getToplist(colList));
		
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "gyglnew_cwrzgl_cwrz_xy.do");
		// ================= end =====================

		return mapping.findForward("success_xy");
	}
	/**
	 * 床位入住手动分配	
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-住宿管理-床位入住管理（学院）-{doType}维护CWH:{checkbox_cwh}")
	public ActionForward cwrzglSdfp_xy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwfpgl_cwfp.do");
		HttpSession session = request.getSession();
		String rzsj=(String)session.getAttribute("temp_gygl_cwrz_rzsj");//入住时间
		CwrzglForm myForm = (CwrzglForm) form;
		if(Base.isNull(myForm.getRzsj())){
			myForm.setRzsj(rzsj);
			myForm.setTsyy((String)session.getAttribute("temp_gygl_cwrz_tsyy"));
			myForm.setBz((String)session.getAttribute("temp_gygl_cwrz_bz"));
		}else{
			session.setAttribute("temp_gygl_cwrz_rzsj", myForm.getRzsj());
			session.setAttribute("temp_gygl_cwrz_tsyy", myForm.getTsyy());
			session.setAttribute("temp_gygl_cwrz_bz", myForm.getBz());
		}
		
		CwrzglService service = new CwrzglService();
		// ============= 初始化各变量的值 ============
		// 获得公寓设置相关信息
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);
		
		
		//参数的获取与设置
		String primarykey_checkVal=request.getParameter("primarykey_checkVal");//主键"学院_年级_性别"
		if(!Base.isNull(primarykey_checkVal)){
			String[] temp=primarykey_checkVal.split("_");
			myForm.setXydm(temp[0]);
			myForm.setNj(temp[1]);
			myForm.setBjdm(temp[3]);
			myForm.setXb(temp[4]);
			myForm.setRzqk("未入住");
			myForm.setCwzt("未入住");
		}else{
			myForm.setNj(request.getParameter("hidden_nj"));
			myForm.setXydm(request.getParameter("hidden_xydm"));
		}
		
		//楼栋列表
		List<HashMap<String,String>> ldlist=service.getLdList_xy(myForm);
		if(ldlist!=null&&ldlist.size()==1){//若只有一个分配的楼栋，则直接选中查询
			myForm.setLddm(ldlist.get(0).get("lddm"));
		}
		
		
		//寝室床位分配的保存与取消
		boolean isKqxrz=service.isKqxrz(myForm);//是否可取消入住
		String doType=request.getParameter("doType");
		if("save".equals(doType)){//保存学生入住信息
			boolean b=service.saveXsrzfpxx(request, myForm);
			request.setAttribute("back", b);
			
		}else if("qxfp".equals(doType)&&isKqxrz){//取消寝室床位分配
			boolean b=service.qxXsrzfpxx(request, myForm);
			request.setAttribute("back", b);
		}
		
		// =============== 执行查询操作 ===========
		if(!Base.isNull(myForm.getLddm())){//楼栋不为空时进行查询
			service.tjldFpxx_xy(request, myForm);
		}else{
			request.setAttribute("ldjbxx", new HashMap<String,String>());
			request.setAttribute("ldlcxx", new ArrayList<HashMap<String,String>>());
			request.setAttribute("ldlcqscwxxb", new ArrayList<HashMap<String,String>>());
			request.setAttribute("xsxxlist", new ArrayList<String[]>());
		}
		
		// ================= end =====================
		request.setAttribute("ldlist", ldlist);//楼栋列表
		HashMap<String,String> rs=new HashMap<String, String>();
		rs.put("lddm", myForm.getLddm());
		rs.put("nj", myForm.getNj());
		rs.put("xydm", myForm.getXydm());
		rs.put("bjdm", myForm.getBjdm());
		rs.put("xb", myForm.getXb());
		request.setAttribute("rs", rs);
		request.setAttribute("tsyylist", service.getTsyyList());
		request.setAttribute("rzyylist", service.getRzyyList());
		request.setAttribute("xynj_tjxx", service.getCwfpglInfo_xy(myForm));//学院年级人数及分配入住数据
		request.setAttribute("xynjxbld_tjxx", service.getXynjxbLdfpxx_xy(myForm));//学院年级性别楼栋统计情况
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
//		request.setAttribute("njList", Base.getNjList()) ;//年级
//		request.setAttribute("xyList", Base.getXyList()) ;//学院
//		request.setAttribute("zyList", Base.getZyMap().get(myForm.getXydm()));
//		request.setAttribute("bjList", Base.getBjMap().get(myForm.getXydm()+"!!"+myForm.getZydm()+"!!"+myForm.getNj()));
		request.setAttribute("isKqxrz", isKqxrz);
		request.setAttribute("user", getUser(request));
		request.setAttribute("act", request.getParameter("act"));
		boolean isFdy=(Boolean)session.getAttribute("isFdy");
		boolean isBzr=(Boolean)session.getAttribute("isBzr");
		request.setAttribute("isFdy", isFdy);
		request.setAttribute("isBzr", isBzr);
		
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		
		return mapping.findForward("sdfp_xy");
	}
}
