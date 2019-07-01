package xgxt.dtjs.jhzy.yydx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import org.apache.struts.upload.FormFile;

public class JhzyYydxAction extends BaseAction {
	/**
	 * 日课程安排查询页面
	 * @throws Exception 
	 */
	JhzyYydxUnit myUnit= new JhzyYydxUnit();
	
	public ActionForward jhzyYydxkcap (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep  = request.getSession().getAttribute("userDep").toString();
		String userName  = request.getSession().getAttribute("userName").toString();
		String userOnline  = request.getSession().getAttribute("userOnLine").toString();
		JhzyYydxForm myForm = (JhzyYydxForm)form;
		JhzyYydxService service = new JhzyYydxService();
		String xydm = myForm.getXydm();
		String xn  = myForm.getXn();
		String xq  = myForm.getXq();
		RckApModel model = new RckApModel();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		ArrayList<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
//		if(userType.equalsIgnoreCase("stu")){//学生无操作权限
//			request.setAttribute("errMsg", "学生用户无权访问该页面！");
//			return new ActionForward("/errMsg.do", false);
//		}
		if(xn==null){
			myForm.setXn(Base.currXn);
		}
		if(xq==null){
			myForm.setXq(Base.currXq);
		}
		if("xy".equalsIgnoreCase(userType) || "stu".equalsIgnoreCase(userType)){
			xydm = userDep;
			myForm.setXydm(xydm);
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getkcapTitle();	
			rs    = service.serv_kcapSearch(model);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("xyList",Base.getXyList());
		request.setAttribute("userType", userType);
		//读写权判断		 			
		request.setAttribute("writeAble", (Base.chkUPower(userName,"jhzyYydxkcap.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		return mapping.findForward("kcapManage");
	}
	
		/**
	 * 日课程安排增加
	 */
	public ActionForward xkcapAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep  = request.getSession().getAttribute("userDep").toString();
		JhzyYydxForm myForm = (JhzyYydxForm)form;	
		RckApModel model = new RckApModel();
		JhzyYydxService service = new JhzyYydxService();
		String doType = request.getParameter("doType");
		String xydm = myForm.getXydm();
		String xn  = myForm.getXn();
		String xq  = myForm.getXq();
		myForm.setKczy(DealString.toGBK(myForm.getKczy()));
		myForm.setJtap(DealString.toGBK(myForm.getJtap()));
		myForm.setBz(DealString.toGBK(myForm.getBz()));
		if(xn==null){
			myForm.setXn(Base.currXn);
		}
		if(xq==null){
			myForm.setXq(Base.currXq);
		}
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
			myForm.setXydm(xydm);
		}
		if("save".equalsIgnoreCase(doType)){
			
			boolean done = true;
//			处理文件上传
			FormFile file = myForm.getUploadFile();
			String filePath = "";
			String fName = "";
			if (file != null) {
				String dir = "/upload/dtjs";
				File f = new File(dir);
				if (!f.exists()) {
					f.mkdirs();
				}
				Timestamp date = new Timestamp(System.currentTimeMillis());
				String dateStr = date.toString().substring(0, 19);
				dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "")
						.replaceAll(":", "");
				int size = file.getFileSize();
				if (size < 10485760 && size != 0) {
					fName = dateStr + file.getFileName();
					InputStream in = file.getInputStream();
					filePath = dir + "/" + fName;
					OutputStream out = new FileOutputStream(filePath);
					int bytesRead = 0;
					byte[] buffer = new byte[size];
					while ((bytesRead = in.read(buffer, 0, size)) != -1) {
						out.write(buffer, 0, bytesRead);
					}
					out.close();
					in.close();
				} else if (size == 0) {
					filePath = "";
				} else {
					done = false;
					String msg = "文件过大，请确认！！";
					request.setAttribute("msg", msg);
					request.setAttribute("done", done);
				}
			}
			
			if (done) {
				myForm.setScdz(filePath);
				BeanUtils.copyProperties(model, myForm);
				done = service.serv_xkcapAdd(model);
				request.setAttribute("done", done);
			}
		}
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("xyList",Base.getXyList());
		request.setAttribute("userType", userType);
		return mapping.findForward("xkcapAdd");
	}
	
	/**
	 * 业余党校申请
	 */
	public ActionForward jhzyYydxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String userType = request.getSession().getAttribute("userType").toString();
		String userName  = request.getSession().getAttribute("userName").toString();
		JhzyYydxService service = new JhzyYydxService();
		String xh  = request.getParameter("xh");
		JhzyYydxForm myForm = (JhzyYydxForm)form; 
		HashMap<String, String> rs = new HashMap<String,String>();
		
		if(userType.equalsIgnoreCase("stu")){
			xh = userName;
		}
		if(xh!=null&&!xh.equalsIgnoreCase("")){
			rs = service.getXsSqxx(xh);
			if(rs==null||rs.size()==0){
				request.setAttribute("stuExists", "no");
			}else{
				request.setAttribute("rs", rs);
			}
		}
		
		request.setAttribute("rs", rs);
		myUnit.setDqXnXq(myForm);
		myForm.setSqly(DealString.toGBK(myForm.getSqly()));
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("xyList",Base.getXyList());
		return mapping.findForward("jhzyYydxsq");
	}
	
	/**
	 * 业余党校申请保存
	 */
	public ActionForward yydxsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		JhzyYydxService service = new JhzyYydxService();
		JhzyYydxModel myModel = new JhzyYydxModel();
		JhzyYydxForm myForm = (JhzyYydxForm)form; 
		myUnit.setDqXnXq(myForm);
		BeanUtils.copyProperties(myModel, myForm);
		boolean update = service.yydxUpdate("",myModel, request);
		if (update) {
			request.setAttribute("done", "true");
		} else {
			request.setAttribute("done", "false");
		}
		return jhzyYydxsq(mapping,form,request,response);
	}
	
	/**
	 *日课程安排信息删除
	 */
	public ActionForward xkcapDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pkStr = request.getParameter("delPk");
		JhzyYydxService service = new JhzyYydxService();
		service.serv_kcapDel(pkStr);
		return jhzyYydxkcap(mapping, form, request, response);
	}
	/**
	 * 日课程安排修改
	 */
	public ActionForward xkcapUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		JhzyYydxForm myForm = (JhzyYydxForm)form;	
		RckApModel model = new RckApModel();
		JhzyYydxService service = new JhzyYydxService();		
		myForm.setKczy(DealString.toGBK(myForm.getKczy()));
		myForm.setJtap(DealString.toGBK(myForm.getJtap()));
		myForm.setBz(DealString.toGBK(myForm.getBz()));
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model, myForm);
			boolean done = service.serv_xkcapUpdate(model,pkValue);
			request.setAttribute("done",done);
		}
		HashMap<String,String> map = service.serv_getXsInfo(pkValue);
		String dkjs = map.get("dkjs").replace("第","");
		dkjs = dkjs.replace("期","");
		map.put("dkjs",dkjs);		
		String kssjH = map.get("kssj");
		map.put("kssjH",kssjH.substring(9,11));
		String kssjM=map.get("kssj");;
		map.put("kssjM",kssjM.substring(12,14));
		String kssjS = map.get("kssj");
		map.put("kssjS",kssjS.substring(15,17));	
		String kssj = map.get("kssj");
		map.put("kssj",kssj.substring(0,8));
		String jssjH = map.get("jssj");
		map.put("jssjH",jssjH.substring(9,11));
		String jssjM=map.get("jssj");;
		map.put("jssjM",jssjM.substring(12,14));
		String jssjS = map.get("jssj");
		map.put("jssjS",jssjS.substring(15,17));	
		String jssj = map.get("jssj");
		map.put("jssj",jssj.substring(0,8));
		request.setAttribute("rs",map);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("xyList",Base.getXyList());
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("doType",doType);
		return mapping.findForward("xkcapUpdate");
	}
	
	/**
	 *  业余党校审核查询主页
	 */
	public ActionForward jhzyYydxsh (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		JhzyYydxService service = new JhzyYydxService();
		JhzyYydxForm myForm = (JhzyYydxForm) form;
		JhzyYydxModel model = new JhzyYydxModel();
		JhzyYydxUnit pjpyUnit   = new JhzyYydxUnit(); 
		
		String userName = request.getSession().getAttribute("userName").toString();		
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		String userType  = request.getSession().getAttribute("userType").toString();
		String userDep   = request.getSession().getAttribute("userDep").toString();
		myUnit.setDqXnXq(myForm);
		myForm.setXh(DealString.toGBK(myForm.getXh()));//学号中文转换
		myForm.setXm(DealString.toGBK(myForm.getXm()));//姓名中文转换
		myForm.setYesNo(DealString.toGBK(myForm.getYesNo()));//审核状态中文转换
		if(userType.equalsIgnoreCase("stu")){//学生无操作权限
			request.setAttribute("errMsg", "学生无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}	
		
		if("admin".equalsIgnoreCase(userType)||"xx".equalsIgnoreCase(userType)){
			userType = "xx";
		}
		
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		ArrayList<String[]>  rs = null;
		
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getYydxTopTr();
			rs    = service.getYydxList(model,userType);
		}		
		
		request.setAttribute("rsNum", rs != null ? rs.size():0);
		request.setAttribute("rs",rs);
		request.setAttribute("topTr",topTr);
		
		request.setAttribute("xqList",Base.getXqList());
		pjpyUnit.setNjXyZyBjList(request, myForm);
		request.setAttribute("chkList",service.serv_getChkList());
		request.setAttribute("userType",userType);
//		读写权判断		 			
		request.setAttribute("writeAble",(Base.chkUPower(userName,"jhzyYydxsh.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		return mapping.findForward("jhzyYydxsh");
	}
	/**
	 * 业余党校审核查看
	 * @throws Exception 
	 */
	public ActionForward jhzyYydxView (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		JhzyYydxService service = new JhzyYydxService();
		String pkValue = request.getParameter("pkValue");
		String view = request.getParameter("view");
		String select = request.getParameter("select");		
		HashMap<String, String>  rs = service.getJhzyYydxForXh(pkValue);
		request.setAttribute("rs",rs);//获取学生相关基本信息
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList()); 
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("view",view);
		request.setAttribute("select",select);
		request.setAttribute("sel",request.getParameter("sel"));
		request.setAttribute("rsCjList", service.getCjList(rs.get("xh"),rs.get("xn")));
		return mapping.findForward("jhzyYydxshOne");
	}
	/**
	 * 业余党校审核
	 */
	public ActionForward yydxCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkVStr = request.getParameter("pkVStr");
		JhzyYydxService service = new JhzyYydxService();
		String userType = request.getSession().getAttribute("userType").toString();
		String check = request.getParameter("check");
		boolean updata = true;
		check = "yes".equalsIgnoreCase(check)?"通过":"不通过";
		updata = service.yydxCk(pkVStr, userType, check);
		if(updata){
			request.setAttribute("update", "yes");
		}else{
			request.setAttribute("update", "no");
		}
		return jhzyYydxsh(mapping,form,request,response);
	}
	
	/**
	 * 业余党校成绩录入
	 */
	public ActionForward jhzyYydxcjlr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		JhzyYydxService service = new JhzyYydxService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		JhzyYydxForm myForm = (JhzyYydxForm) form;
		JhzyYydxModel model = new JhzyYydxModel();
		JhzyYydxUnit pjpyUnit   = new JhzyYydxUnit(); 
		myUnit.setDqXnXq(myForm);
		String tableName = "view_jhzy_yydxsq";
		String realTable = "jhzy_yydxsq";
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		myForm.setXh(DealString.toGBK(myForm.getXh()));
		myForm.setQj(DealString.toGBK(myForm.getQj()));
		BeanUtils.copyProperties(model, myForm);

		if (request.getParameter("go") != null
				&& !request.getParameter("go").equalsIgnoreCase("")) {
			rs = service.getYydxcjList(model, tableName);
			topTr = service.getYydxcjTopTr(tableName);
		}
		
		// 存放访问路径求取读写权
		request.setAttribute("path", "jhzyYydxcjlr.do");
		request.setAttribute("xqList",Base.getXqList()); 
		pjpyUnit.setNjXyZyBjList(request, myForm);
		myUnit.commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("jhzyYydxcjlr");
	}
	
	/**
	 * 业余党校成绩录入
	 */
	public ActionForward yydxcjbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		JhzyYydxService service = new JhzyYydxService();
		boolean updata = true;
		JhzyYydxForm myForm = (JhzyYydxForm) form;
		JhzyYydxModel model = new JhzyYydxModel();
		
		BeanUtils.copyProperties(model, myForm);
		updata = service.yydxcjSave(model);
		if(updata){
			request.setAttribute("update", "yes");
		}else{
			request.setAttribute("update", "no");
		}
		return jhzyYydxcjlr(mapping,form,request,response);
	}
	
	/**
	 * 业余党校成绩查询
	 */
	public ActionForward jhzyYydxcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		JhzyYydxService service = new JhzyYydxService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		JhzyYydxForm myForm = (JhzyYydxForm) form;
		JhzyYydxModel model = new JhzyYydxModel();
		JhzyYydxUnit pjpyUnit   = new JhzyYydxUnit(); 
		
		String tableName = "view_jhzy_yydxsq";
		String realTable = "jhzy_yydxsq";
		myForm.setXysh(DealString.toGBK(myForm.getXysh()));
		myForm.setXxsh(DealString.toGBK(myForm.getXxsh()));
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		myForm.setXh(DealString.toGBK(myForm.getXh()));
		BeanUtils.copyProperties(model, myForm);

		if (request.getParameter("go") != null
				&& !request.getParameter("go").equalsIgnoreCase("")) {
			rs = service.getYydxCxList(model, tableName);
			topTr = service.getYydxCxTopTr(tableName);
		}
		
		// 存放访问路径求取读写权
		request.setAttribute("path", "jhzyYydxkcap.do");
		request.setAttribute("xqList",Base.getXqList()); 
		request.setAttribute("chkList",service.serv_getChkList());
		pjpyUnit.setNjXyZyBjList(request, myForm);

		myUnit.setDqXnXq(myForm);
		myUnit.commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("jhzyYydxcx");
	}
	
	/**
	 * 业余党校申请批量删除
	 */
	public ActionForward yydxPlsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkVStr = request.getParameter("pkVStr");
		JhzyYydxService service = new JhzyYydxService();
		boolean updata = true;
		updata = service.delYydx(pkVStr);
		if(updata){
			request.setAttribute("update", "yes");
		}else{
			request.setAttribute("update", "no");
		}
		return jhzyYydxcx(mapping,form,request,response);
	}
	
	/**
	 * 批量转换
	 */ 
	public ActionForward djxxPlzh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		JhzyYydxService service = new JhzyYydxService();
		boolean updata = true;
		JhzyYydxForm myForm = (JhzyYydxForm) form;
		JhzyYydxModel model = new JhzyYydxModel();
		BeanUtils.copyProperties(model, myForm);
		String realTable = request.getParameter("realTable");
		String zhsj = request.getParameter("zhsj");
		updata = service.plzhSave(model,realTable,zhsj);
		if(updata){
			request.setAttribute("result", "ok");
		}else{
			request.setAttribute("result", "no");
		}
		if(realTable.equalsIgnoreCase("rdjjfzxxb")){
			return new ActionForward("/data_search.do?act=active&go=go");
		}else{
			return new ActionForward("/data_search.do?act=prepare&go=go");
		}
	}
	
}
