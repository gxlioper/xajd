package xsgzgl.gygl.cwfpgl;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewInit;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;

public class CwfpglAction extends BasicAction {
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward cwfpglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwfpgl_cwfp.do");
		User user = getUser(request);// 用户对象
		HttpSession session=request.getSession();
		boolean isFdy=false;
		boolean isBzr=false;
		if(session.getAttribute("fdyQx")!=null){
			isFdy=Boolean.parseBoolean(session.getAttribute("fdyQx").toString());
		}
		if(session.getAttribute("bzrQx")!=null){
			isBzr=Boolean.parseBoolean(session.getAttribute("bzrQx").toString());
		}
	
		// 分配到学院
		if(isFdy||isBzr|| ("xydm".equals(GyglNewInit.CWFPDX)&&"xy".equals(user.getUserType()))){//若是学院账号则没有权限
			 String msg = null;
			 if("xydm".equals(GyglNewInit.CWFPDX)){//只分配到学院
				 msg=Base.YXPZXY_KEY+"、班主任和辅导员是没有该操作权限的，请确认！";
			 }else{//分配到班级
				 msg="班主任和辅导员是没有该操作权限的，请确认！";
			 }
			 request.setAttribute("yhInfo", msg);
			 return new ActionForward("/yhInfo.do", false);
		}
		CwfpglForm myForm = (CwfpglForm) form;
		CwfpglService service = new CwfpglService();
		RequestForm rForm = new RequestForm();
		// 获取显示结果集(学院:xyli,专业：zyli,班级：bjli)
		String resultSet = request.getParameter("resultSet")== null? "":request.getParameter("resultSet").toString();
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		// ============= 初始化各变量的值 ============
		// 获得公寓设置相关信息(床位分配对象)
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);
		
		//默认显示
		String[] colList = null;
		
		//学校用户 或者 床位分配对象为：学院，默认显示学院列表
		// 学院用户切换学院结果集时显示
		if("xydm".equals(cwfpdx) || ("".equalsIgnoreCase(resultSet) && ("xx".equals(user.getUserType())
				||"admin".equals(user.getUserType())))
				|| "xyli".equalsIgnoreCase(resultSet)){

			colList = new String[]{"年级",Base.YXPZXY_KEY,"性别","总人数","已分配床位数","已分配寝室数","已分配楼栋数"};
			
			// 取得年级学院统计
			rsArrList = service.getCwfpglInfoList(myForm,request);
			// 设置切换结果集初始值
			request.setAttribute("resultSet", "xyli");
			// 设置床位分配对象
			request.setAttribute("cwfpdx", cwfpdx);
			
			//床位分配对象为：专业 且为学院用户
			//切换结果集为专业
		}else if(("zydm".equals(cwfpdx) && "".equalsIgnoreCase(resultSet) && "xy".equalsIgnoreCase(user.getUserType()))
				|| "zyli".equalsIgnoreCase(resultSet)){

			// 学院用户设置学院代码
			if("xy".equalsIgnoreCase(user.getUserType())){
				String xydm=user.getUserDep();//xydm
				myForm.setXydm(xydm);
			}
			
			colList = new String[]{"年级",Base.YXPZXY_KEY,"专业","性别","总人数","已分配床位数","已分配寝室数","已分配楼栋数"};

			// 取得年级学院专业统计
			rsArrList = service.getCwfpglInfoList_zy(myForm);
			// 设置切换结果集初始值
			request.setAttribute("resultSet", "zyli");
			// 设置床位分配对象
			request.setAttribute("cwfpdx", cwfpdx);
			
			//床位分配对象为：班级 且为学院用户
			//切换结果集为班级
		}else if(("bjdm".equals(cwfpdx) && "".equalsIgnoreCase(resultSet) && "xy".equalsIgnoreCase(user.getUserType()))
				|| "bjli".equalsIgnoreCase(resultSet)){
			
			// 学院用户设置学院代码
			if("xy".equalsIgnoreCase(user.getUserType())){
				String xydm=user.getUserDep();//xydm
				myForm.setXydm(xydm);
			}
			
			colList = new String[]{"年级",Base.YXPZXY_KEY,"专业","班级","性别","总人数","已分配床位数","已分配寝室数","已分配楼栋数"};
			
			// 取得年级学院专业班级统计
			rsArrList = service.getCwfpglInfoList_xy(myForm);
			// 设置切换结果集初始值
			request.setAttribute("resultSet", "bjli");
			// 设置床位分配对象
			request.setAttribute("cwfpdx", cwfpdx);
		}
		// =============== 执行查询操作 ===========
		
//		myForm.getSearchModel().setSearch_tj_nj(new String[]{Base.currNd});

		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("topTr", service.getToplist(colList));
		request.setAttribute("userType", user.getUserType());
		service.setRequestValue(rForm, user,request);
		
		FormModleCommon.commonRequestSet(request);
		// ================= end =====================

		return mapping.findForward("success");
	}
	
	/**
	 * 床位分配管理--手动分配
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-住宿管理-床位分配管理-{resultSet}[{doType}]维护QSH:{checkbox_qsh}")
	public ActionForward cwfpglSdfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwfpgl_cwfp.do");
		User user = getUser(request);// 用户对象
		
//		if("bjdm".equals(GyglNewInit.CWFPDX)&&"xy".equals(user.getUserType())){//学院用户
//			return cwfpglSdfp_xy(mapping, form, request, response);
//		}else if("xydm".equals(GyglNewInit.CWFPDX)&&"xy".equals(user.getUserType())){//若是学院账号则没有权限
//			 String msg = "本模块只能由学校用户进行操作，请确认！";
//			 request.setAttribute("yhInfo", msg);
//			 return new ActionForward("/yhInfo.do", false);
//		}
		
		if("xydm".equals(GyglNewInit.CWFPDX)&&"xy".equals(user.getUserType())){//若是学院账号则没有权限
			 String msg = "本模块只能由学校用户进行操作，请确认！";
			 request.setAttribute("yhInfo", msg);
			 return new ActionForward("/yhInfo.do", false);
		}

		CwfpglForm myForm = (CwfpglForm) form;
		//楼栋
		myForm.setLddm(request.getParameter("lddm"));
		//取得性別
		String xb = request.getParameter("xb");
		if(StringUtils.isNotNull(xb)){
			if("1".equalsIgnoreCase(xb)){
				myForm.setXb("男");
			}else if ("2".equalsIgnoreCase(xb)){
				myForm.setXb("女");
			}
		}
		//取得年级
		String nj = request.getParameter("nj");
		myForm.setNj(nj);
		//取得学院
		String xydm = request.getParameter("xydm");
		myForm.setXydm(xydm);
		
		//取得性別
		String xbdm = request.getParameter("xbdm");
		if (xbdm != null && !"".equalsIgnoreCase(xbdm)) {
			if("1".equalsIgnoreCase(xbdm)){
				myForm.setXb("男");
				myForm.setLddm(null);
			}else if ("2".equalsIgnoreCase(xbdm)){
				myForm.setXb("女");
				myForm.setLddm(null);
			}
		}
		
		CwfpglService service = new CwfpglService();
		// ============= 初始化各变量的值 ============
		// 获得公寓设置相关信息
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);

		// 获取显示结果集(学院:xyli,专业：zyli,班级：bjli)
		String resultSet = request.getParameter("resultSet")== null? "xyli":request.getParameter("resultSet").toString();
		
		// 参数的获取与设置
		String act = request.getParameter("act");
		
		// 获取主键
		// 学院结果集：主键"学院_年级_性别"；
		// 专业结果集：主键"学院_年级_专业_性别"；
		// 班级结果集：主键"学院_年级_专业_班级_性别"
		String primarykey_checkVal = request
				.getParameter("primarykey_checkVal");
		
		if(!Base.isNull(primarykey_checkVal)){
			String[] temp = primarykey_checkVal.split("_");
			
			// 学院结果集
			if("xyli".equalsIgnoreCase(resultSet)){
				myForm.setXydm(temp[0]);
				myForm.setNj(temp[1]);
				myForm.setXb(temp[2]);
				
				// 专业结果集
			}else if("zyli".equalsIgnoreCase(resultSet)){				
				myForm.setXydm(temp[0]);
				myForm.setNj(temp[1]);
				myForm.setZydm(temp[2]);
				myForm.setXb(temp[3]);
				
				// 班级结果集
			}else if("bjli".equalsIgnoreCase(resultSet)){				
				myForm.setXydm(temp[0]);
				myForm.setNj(temp[1]);
				myForm.setZydm(temp[2]);
				myForm.setBjdm(temp[3]);
				myForm.setXb(temp[4]);
			}
			
			if("to_fp".equals(act)){
				myForm.setCwzt("未分配");
			}else{
				myForm.setCwzt("已分配");	
			}
		}
		String pageqh=request.getParameter("pageqh");
		if("pageqh".equals(pageqh)){
			if("to_fp".equals(act)){
				myForm.setCwzt("未分配");
			}else{
				myForm.setCwzt("已分配");	
			}
		}
		
		//寝室床位分配的保存与取消
		String doType=request.getParameter("doType");
		if("save".equals(doType)){//保存寝室床位分配
			boolean b = false;
			
			// 学院结果集
			if ("xyli".equalsIgnoreCase(resultSet)) {

				// 按学院保存
				b = service.saveQscwfpxx(request, myForm);
				// 专业结果集
			} else if ("zyli".equalsIgnoreCase(resultSet)) {

				// 按專業保存
				b = service.saveQscwfpxx_zy(request, myForm);

				// 班级结果集
			} else if ("bjli".equalsIgnoreCase(resultSet)) {

				// 按班级保存
				b = service.saveQscwfpxx_xy(request, myForm);
			}
			
			request.setAttribute("back", b);
			
		}else if("qxfp".equals(doType)){//取消寝室床位分配
			boolean b = false;
			// 学院结果集
			if ("xyli".equalsIgnoreCase(resultSet)) {

				// 按学院保存
				b = service.qxQscwfpxx(request, myForm);
				// 专业结果集
			} else if ("zyli".equalsIgnoreCase(resultSet)) {

				// 按專業保存
				b = service.qxQscwfpxx_zy(request, myForm);

				// 班级结果集
			} else if ("bjli".equalsIgnoreCase(resultSet)) {

				// 按班级保存
				b = service.qxQscwfpxx_xy(request, myForm);
			}
			
			request.setAttribute("back", b);
		}
		
		// =============== 执行查询操作 ===========
		if(!Base.isNull(myForm.getLddm())){//楼栋不为空时进行查询
			// 学院结果集
			if("xyli".equalsIgnoreCase(resultSet)){

				service.tjldFpxx(request, myForm);
				
				// 专业结果集
			}else if("zyli".equalsIgnoreCase(resultSet)){
				
				service.tjldFpxx_zy(request, myForm);
	
				// 班级结果集
			}else if("bjli".equalsIgnoreCase(resultSet)){
				
				service.tjldFpxx_xy(request, myForm);
			}
		}else{
			//楼栋基本信息（楼栋代码、楼栋名称）
			request.setAttribute("ldjbxx", new HashMap<String,String>());
			//楼栋楼层信息（楼层统计信息：寝室数/床位数； 未分配床位数； 分配当前床位数）
			request.setAttribute("ldlcxx", new ArrayList<HashMap<String,String>>());
			//楼栋楼层寝室床位信息（）
			request.setAttribute("ldlcqscwxxb", new ArrayList<HashMap<String,String>>());
		}
		
		// ================= end =====================
		//学院结果集条件下楼栋列表
		if("xyli".equalsIgnoreCase(resultSet)){

			request.setAttribute("ldlist", service.getLdListByGyfdy(request,myForm.getXb()));//楼栋列表
			
			// 专业结果集或班级结果集条件下楼栋列表
		}else if("zyli".equalsIgnoreCase(resultSet)||"bjli".equalsIgnoreCase(resultSet)){
			
			request.setAttribute("ldlist", service.getCwfpLdListByGyfdy(request,myForm.getXb()));//楼栋列表
		}
		
		
		HashMap<String,String> rs=new HashMap<String, String>();
		rs.put("lddm", myForm.getLddm());
		rs.put("nj", myForm.getNj());
		rs.put("xydm", myForm.getXydm());
		rs.put("zydm", myForm.getZydm());
		rs.put("bjdm", myForm.getBjdm());
		rs.put("xb", myForm.getXb());
		request.setAttribute("rs", rs);
		
		// 统计信息
		HashMap<String,String> xynj_tjxx = new HashMap<String,String>();
		
		// 楼栋统计信息
		List<HashMap<String,String>> xynjxbld_tjxx = new ArrayList<HashMap<String,String>>();
		
		// 设置切换结果集初始值
		request.setAttribute("resultSet", resultSet);

		// 设置床位分配对象
		request.setAttribute("cwfpdx", cwfpdx);
		
		// 床位分配对象为学院  或者 结果集为学院的时候
		if ("xydm".equalsIgnoreCase(cwfpdx) || 
				("xyli".equalsIgnoreCase(resultSet)&& ("xx".equals(user.getUserType()) || "admin".equals(user.getUserType())))) {
			xynj_tjxx = service.getCwfpglInfo(myForm);
			xynjxbld_tjxx = service.getXynjxbLdfpxx(myForm);
		}else 
		
		// 床位分配对象为专业
		if ("zydm".equalsIgnoreCase(cwfpdx) || "zyli".equalsIgnoreCase(resultSet)) {
			xynj_tjxx = service.getCwfpglInfo_zy(myForm);
			xynjxbld_tjxx = service.getXynjxbLdfpxx_zy(myForm);
		} else
		
		// 床位分配对象为班级
		if ("bjdm".equalsIgnoreCase(cwfpdx) || "bjli".equalsIgnoreCase(resultSet)) {
			xynj_tjxx = service.getCwfpglInfo_xy(myForm);
			xynjxbld_tjxx = service.getXynjxbLdfpxx_xy(myForm);
		}
		request.setAttribute("xynj_tjxx", xynj_tjxx);			//人数及分配入住数据
		request.setAttribute("xynjxbld_tjxx", xynjxbld_tjxx);	//性别楼栋统计情况
		FormModleCommon.setCwfpNjXyZyBjList(request,resultSet);				
		request.setAttribute("userType", user.getUserType());			
		request.setAttribute("act", act);
		
		return mapping.findForward("sdfp");
	}
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：xiaxia
	 * @日期：2014-9-12 下午03:21:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward cwfpglInitLdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CwfpglService service = new CwfpglService();
		List<HashMap<String,String>> ldList = service.cwfpglInitLdList(request);
		try {
			response.setContentType("text/html;charset=gbk");
			JSONArray dataList = JSONArray.fromObject(ldList);
			response.getWriter().print(dataList);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward cwfpglCwfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("cwfp"); 
	}
	
	/**
	 * jquery加载楼栋列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadLdlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CwfpglForm myForm = (CwfpglForm) form;
		CwfpglService service = new CwfpglService();
		
		StringBuffer sb=new StringBuffer();
		List<HashMap<String,String>> ldlist=service.loadLdlist(myForm);
		HashMap<String,String> m=null;
		for(int i=0;i<ldlist.size();i++){
			m=ldlist.get(i);
			sb.append(m.get("lddm")+",");
			sb.append(m.get("ldmc")+"");
			if(i<ldlist.size()-1){
				sb.append(";");
			}
		}
		
		response.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();
		pw.write(sb.toString());
		pw.close();
		return null;
	}
	
	/**
	 * 床位分配管理（学院）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cwfpglManage_xy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwfpgl_cwfp.do");
		CwfpglForm myForm = (CwfpglForm) form;
		CwfpglService service = new CwfpglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		
		String xydm=user.getUserDep();//xydm
		myForm.setXydm(xydm);
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		// ============= 初始化各变量的值 ============
		// 获得公寓设置相关信息
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);

		// 结果集显示字段
		String[] colList = new String[]{"专业","班级","性别","总人数","已入住人数","未入住人数","总床位数","已入住床位数","未入住床位数"};

		// =============== 执行查询操作 ===========
		
//		myForm.getSearchModel().setSearch_tj_nj(new String[]{Base.currNd});
		
		rsArrList = service.getCwfpglInfoList_xy(myForm);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("topTr", service.getToplist(colList));
		service.setRequestValue(rForm, user,request);
		
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "gyglnew_cwfpgl_cwfp_xy.do");
		// ================= end =====================

		return mapping.findForward("success_xy");
	}
	
	/**
	 * 床位分配管理--手动分配(学院)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-住宿管理-床位分配管理(学院)-{doType}维护CWH:{checkbox_cwh}")
	public ActionForward cwfpglSdfp_xy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwfpgl_cwfp.do");
		CwfpglForm myForm = (CwfpglForm) form;
		CwfpglService service = new CwfpglService();
		// ============= 初始化各变量的值 ============
		// 获得公寓设置相关信息
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);
		
		//参数的获取与设置
		String act=request.getParameter("act");
		String primarykey_checkVal=request.getParameter("primarykey_checkVal");//主键"学院_年级_专业_班级_性别"
		if(!Base.isNull(primarykey_checkVal)){
			String[] temp=primarykey_checkVal.split("_");
			myForm.setXydm(temp[0]);
			myForm.setNj(temp[1]);
			myForm.setZydm(temp[2]);
			myForm.setBjdm(temp[3]);
			myForm.setXb(temp[4]);
			if("to_fp".equals(act)){
				myForm.setCwzt("未分配");
			}else{
				myForm.setCwzt("已分配");	
			}
		}
		myForm.setXydm(getUser(request).getUserDep());
		String pageqh=request.getParameter("pageqh");
		if("pageqh".equals(pageqh)){
			if("to_fp".equals(act)){
				myForm.setCwzt("未分配");
			}else{
				myForm.setCwzt("已分配");
			}
		}
		
		//寝室床位分配的保存与取消
		String doType=request.getParameter("doType");
		if("save".equals(doType)){//保存寝室床位分配
			boolean b=service.saveQscwfpxx_xy(request, myForm);
			request.setAttribute("back", b);
			
		}else if("qxfp".equals(doType)){//取消寝室床位分配
			boolean b=service.qxQscwfpxx_xy(request, myForm);
			request.setAttribute("back", b);
		}
		
		// =============== 执行查询操作 ===========
		if(!Base.isNull(myForm.getLddm())){//楼栋不为空时进行查询
			service.tjldFpxx_xy(request, myForm);
		}else{
			request.setAttribute("ldjbxx", new HashMap<String,String>());
			request.setAttribute("ldlcxx", new ArrayList<HashMap<String,String>>());
			request.setAttribute("ldlcqscwxxb", new ArrayList<HashMap<String,String>>());
		}
		
		// ================= end =====================
		request.setAttribute("ldlist", service.getLdList_xy(myForm));//楼栋列表
		HashMap<String,String> rs=new HashMap<String, String>();
		rs.put("lddm", myForm.getLddm());
		rs.put("nj", myForm.getNj());
		rs.put("xydm", myForm.getXydm());
		rs.put("zydm", myForm.getZydm());
		rs.put("bjdm", myForm.getBjdm());
		rs.put("xb", myForm.getXb());
		request.setAttribute("rs", rs);
		
		request.setAttribute("xynj_tjxx", service.getCwfpglInfo_xy(myForm));//学院年级人数及分配入住数据
		request.setAttribute("xynjxbld_tjxx", service.getXynjxbLdfpxx_xy(myForm));//学院年级性别楼栋统计情况
		FormModleCommon.setAllNjXyZyBjList(request);
//		request.setAttribute("njlist", Base.getNjList()) ;//年级
//		request.setAttribute("xylist", Base.getXyList()) ;//学院
		request.setAttribute("act", act);
		
		return mapping.findForward("sdfp_xy");
	}
	
	/**
	 * jquery加载楼栋列表（学院）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadLdlist_xy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CwfpglForm myForm = (CwfpglForm) form;
		CwfpglService service = new CwfpglService();
		
		StringBuffer sb=new StringBuffer();
		List<HashMap<String,String>> ldlist=service.loadLdlist_xy(myForm);
		HashMap<String,String> m=null;
		for(int i=0;i<ldlist.size();i++){
			m=ldlist.get(i);
			sb.append(m.get("lddm")+",");
			sb.append(m.get("ldmc")+"");
			if(i<ldlist.size()-1){
				sb.append(";");
			}
		}
		
		response.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();
		pw.write(sb.toString());
		pw.close();
		return null;
	}
}
