package xgxt.shgz.zjlg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

public class XsgbxxAtion extends DispatchAction {

	/**
	 * 学生干部信息
	 */
	public ActionForward xsgbxxwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgbxxForm myform = (XsgbxxForm) form;
		HttpSession session = request.getSession();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		XsgbxxService service = new XsgbxxService();
//		String userName = session.getAttribute("userName").toString();
//		String userOnline = session.getAttribute("userOnLine").toString();
		String tableName = request.getParameter("tableName");
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
	    String userType = session.getAttribute("userType").toString();
	    String userDep = session.getAttribute("userDep").toString();
		String titleName = "";
		if(userType.equalsIgnoreCase("stu")){
			request.setAttribute("errMsg", "学生无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}	
		if ("xy".equals(userType)) {
			myform.setXydm(userDep);
		}
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			XsgbxxModel model = new XsgbxxModel();
			BeanUtils.copyProperties(model, myform);
			FormModleCommon.formToGBK(model);
			topTr = service.getToptrTitle(tableName);
			rs = service.ser_xlzxsQuery(model, tableName);
		}
		FormModleCommon.formToGBK(myform);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		if(rs==null){
			request.setAttribute("rsNum", "0");
		}else {
			request.setAttribute("rsNum", rs.size());
		}
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("titleName", titleName);
		
		//读写权判断		
//		request.setAttribute("writeAble",(Base.chkUPower(userName,"xsgbxxwh.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		request.setAttribute("writeAble","yes");
		return mapping.findForward("xsgbxxwhManage");
	}

	/**
	 * 学生干部信息增加
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ActionForward xsgbxxwhAdd(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgbxxForm myform = (XsgbxxForm) form;
		XsgbxxModel model = new XsgbxxModel();
		String act = request.getParameter("act");
		String titleName = "";
		String tableName = request.getParameter("tableName");
		HashMap<String, String> map = new HashMap<String, String>();
		String pk = request.getParameter("pk");
		request.setAttribute("titleName", titleName);
		XsgbxxService service = new XsgbxxService();
		String doType = request.getParameter("doType");
		boolean result = false;
		if ("add".equals(act)) {
			BeanUtils.copyProperties(model, myform);
			if (service.serv_isexistsxlwy(model) && "add".equals(doType)) {
				request.setAttribute("existx", "yes");
			}else {
				FormModleCommon.formToGBK(model);
				result = service.ser_xlzxsAdd(model, tableName, request);
				if (result) {
					request.setAttribute("done", "yes");
				} else {
					request.setAttribute("done", "no");
				}
			}
			FormModleCommon.formToGBK(myform);
		}
		if(StringUtils.isNotNull(pk)){
			map = service.ser_idforQuery(pk,tableName);
		}
		request.setAttribute("doType", doType);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.requestSetList(new String[]{"yf"}, request);
		request.setAttribute("rs", map);		
		return mapping.findForward("xsgbxxwhAdd");
	}
	/**
	 * 学生干部信息删除
	 */
	public ActionForward xlzxsDel(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgbxxService service = new XsgbxxService();
		String pks = request.getParameter("pkVStr");
		String tableName = request.getParameter("tableName");
		String whichpk = service.dao_AllDelList(pks,tableName);
		if (StringUtils.isNull(whichpk)) {
			request.setAttribute("done", "yes");
		} else {
			request.setAttribute("done", "no");
		}
		if ("zjlg_gzzjb".equals(tableName)) {
			return gzzjManage(mapping, form, request, response);
		}else if ("zjlg_hdchb".equals(tableName)) {
			return hdchManage(mapping, form, request, response);
		}else{
			return xsgbxxwhManage(mapping, form, request, response);
		}
	}
	/**
	 * 学生干部信息导出数据
	 */
	public ActionForward expData(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgbxxForm myform = (XsgbxxForm) form;
		XsgbxxService service = new XsgbxxService();
		String tableName = request.getParameter("tableName");
		XsgbxxModel model = new XsgbxxModel();
		BeanUtils.copyProperties(model, myform);
		FormModleCommon.formToGBK(model);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.dao_expData(tableName,response,model);
		return mapping.findForward("");
	}

	/**
	 * 学号刷学生信息
	 * 
	 */
	public ActionForward getxsInfo(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		XsgbxxService service = new XsgbxxService();
		String forward = "";
		String forwardname = request.getParameter("forwardname");
		map = service.ser_getxsInfo(xh);
		request.setAttribute("rs", map);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.requestSetList(new String[]{"yf"}, request);
		if ("gzzj".equals(forwardname)) {
			forward = "gzzjAdd";
		}else if("hdch".equals(forwardname)){
			forward = "hdchAdd";
		}else{
			forward = "xsgbxxwhAdd";
		}
		request.setAttribute("doType", "add");
		return mapping.findForward(forward);
	}
	/**
	 * 工作总结
	 */
	public ActionForward gzzjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgbxxForm myform = (XsgbxxForm) form;
		HttpSession session = request.getSession();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		XsgbxxService service = new XsgbxxService();
		String userName = session.getAttribute("userName").toString();
//		String userOnline = session.getAttribute("userOnLine").toString();
		String tableName = request.getParameter("tableName");
		String userDep = session.getAttribute("userDep").toString();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
	    String userType = session.getAttribute("userType").toString();
		String titleName = "";
		if(userType.equalsIgnoreCase("stu")){
//			request.setAttribute("errMsg", "学生无权访问该页面！");
//			return new ActionForward("/errMsg.do", false);
			myform.setXh(userName);
		}	
		if ("xy".equals(userType)) {
			myform.setXydm(userDep);
		}
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			XsgbxxModel model = new XsgbxxModel();
			BeanUtils.copyProperties(model, myform);
			FormModleCommon.formToGBK(model);
			topTr = service.getToptrTitle(tableName);
			rs = service.ser_xlzxsQuery(model, tableName);
		}
		FormModleCommon.formToGBK(myform);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		if(rs==null){
			request.setAttribute("rsNum", "0");
		}else {
			request.setAttribute("rsNum", rs.size());
		}
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("titleName", titleName);
		
		//读写权判断		
//		request.setAttribute("writeAble",(Base.chkUPower(userName,"zjlgXlzxs.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		request.setAttribute("writeAble","yes");
		return mapping.findForward("gzzjManage");
	}

	/**
	 * 工作总结增加
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ActionForward gzzjAdd(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgbxxForm myform = (XsgbxxForm) form;
		XsgbxxModel model = new XsgbxxModel();
		HttpSession session = request.getSession();
		String act = request.getParameter("act");
		String titleName = "";
		String tableName = request.getParameter("tableName");
		HashMap<String, String> map = new HashMap<String, String>();
		String pk = request.getParameter("pk");
		request.setAttribute("titleName", titleName);
		XsgbxxService service = new XsgbxxService();
		String usertype = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		boolean result = false;
		if ("add".equals(act)) {
			BeanUtils.copyProperties(model, myform);
			FormModleCommon.formToGBK(model);
			String tjlx = request.getParameter("tjlx");
			model.setTjlx(tjlx);
			result = service.ser_xlzxsAdd(model, tableName, request);
			if (result) {
				request.setAttribute("done", "yes");
			} else {
				request.setAttribute("done", "no");
			}
			FormModleCommon.formToGBK(myform);
		}
		if(StringUtils.isNotNull(pk)){
			map = service.ser_idforQuery(pk,tableName);
		}
		if ("stu".equals(usertype)) {
			map = service.ser_getxsInfo(userName);
		}
		request.setAttribute("doType", doType);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.requestSetList(new String[]{"yf"}, request);
		request.setAttribute("rs", map);		
		return mapping.findForward("gzzjAdd");
	}
	/**
	 * 活动策划
	 */
	public ActionForward hdchManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgbxxForm myform = (XsgbxxForm) form;
		HttpSession session = request.getSession();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		XsgbxxService service = new XsgbxxService();
		String userName = session.getAttribute("userName").toString();
//		String userOnline = session.getAttribute("userOnLine").toString();
		String tableName = request.getParameter("tableName");
		String userDep = session.getAttribute("userDep").toString();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
	    String userType = session.getAttribute("userType").toString();
		String titleName = "";
		if(userType.equalsIgnoreCase("stu")){
//			request.setAttribute("errMsg", "学生无权访问该页面！");
//			return new ActionForward("/errMsg.do", false);
			myform.setXh(userName);
		}	
		if ("xy".equals(userType)) {
			myform.setXydm(userDep);
		}
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			XsgbxxModel model = new XsgbxxModel();
			BeanUtils.copyProperties(model, myform);
			FormModleCommon.formToGBK(model);
			topTr = service.getToptrTitle(tableName);
			rs = service.ser_xlzxsQuery(model, tableName);
		}
		FormModleCommon.formToGBK(myform);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		if(rs==null){
			request.setAttribute("rsNum", "0");
		}else {
			request.setAttribute("rsNum", rs.size());
		}
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("titleName", titleName);
		
		//读写权判断		
//		request.setAttribute("writeAble",(Base.chkUPower(userName,"zjlgXlzxs.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		request.setAttribute("writeAble","yes");
		return mapping.findForward("hdchManage");
	}

	/**
	 * 活动策划
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ActionForward hdchAdd(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgbxxForm myform = (XsgbxxForm) form;
		XsgbxxModel model = new XsgbxxModel();
		String act = request.getParameter("act");
		String titleName = "";
		String tableName = request.getParameter("tableName");
		HashMap<String, String> map = new HashMap<String, String>();
		String pk = request.getParameter("pk");
		request.setAttribute("titleName", titleName);
		HttpSession session = request.getSession();
		String usertype = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		XsgbxxService service = new XsgbxxService();
		String doType = request.getParameter("doType");
		boolean result = false;
		if ("add".equals(act)) {
			BeanUtils.copyProperties(model, myform);
			FormModleCommon.formToGBK(model);
			String tjlx = request.getParameter("tjlx");
			model.setTjlx(tjlx);
			result = service.ser_xlzxsAdd(model, tableName, request);
			if (result) {
				request.setAttribute("done", "yes");
			} else {
				request.setAttribute("done", "no");
			}
			FormModleCommon.formToGBK(myform);
		}
		if(StringUtils.isNotNull(pk)){
			map = service.ser_idforQuery(pk,tableName);
		}
		if ("stu".equals(usertype)) {
			map = service.ser_getxsInfo(userName);
		}
		request.setAttribute("doType", doType);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.requestSetList(new String[]{"yf"}, request);
		request.setAttribute("rs", map);
		return mapping.findForward("hdchAdd");
	}
	/**
	 * 评定意见
	 */
	public ActionForward pdyjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgbxxForm myform = (XsgbxxForm) form;
		HttpSession session = request.getSession();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		XsgbxxService service = new XsgbxxService();
		String userName = session.getAttribute("userName").toString();
//		String userOnline = session.getAttribute("userOnLine").toString();
		String tableName = request.getParameter("tableName");
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
	    String userType = session.getAttribute("userType").toString();
		String titleName = "";
		String userlx = service.serv_iszxtkhry(userName,userType);
		if(StringUtils.isNull(userlx)){
			request.setAttribute("errMsg", "您无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}	
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			XsgbxxModel model = new XsgbxxModel();
			BeanUtils.copyProperties(model, myform);
			FormModleCommon.formToGBK(model);
			topTr = service.getToptrTitle(tableName);
			rs = service.ser_pdyjQuery(model, tableName);
		}
		FormModleCommon.formToGBK(myform);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		if(rs==null){
			request.setAttribute("rsNum", "0");
		}else {
			request.setAttribute("rsNum", rs.size());
		}
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("titleName", titleName);
		request.setAttribute("userlx", userlx);
		//读写权判断		
		//request.setAttribute("writeAble",(Base.chkUPower(userName,"zjlg_pdyj.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		request.setAttribute("writeAble","yes");
		return mapping.findForward("pdyjManage");
	}

	/**
	 * 评定意见
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ActionForward pdyjAdd(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgbxxForm myform = (XsgbxxForm) form;
		XsgbxxModel model = new XsgbxxModel();
		String act = request.getParameter("act");
		String titleName = "";
		String tableName = request.getParameter("tableName");
		HashMap<String, String> map = new HashMap<String, String>();
		String pk = request.getParameter("pk");
		request.setAttribute("titleName", titleName);
		HttpSession session = request.getSession();
		String usertype = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		XsgbxxService service = new XsgbxxService();
		String doType = request.getParameter("doType");
		String userlx = request.getParameter("userlx");
		boolean result = false;
		if ("add".equals(act)) {
			BeanUtils.copyProperties(model, myform);
			FormModleCommon.formToGBK(model);
			model.setTjlx(userlx);
			result = service.ser_pdyjAdd(model, tableName, request);
			if (result) {
				request.setAttribute("done", "yes");
			} else {
				request.setAttribute("done", "no");
			}
			FormModleCommon.formToGBK(myform);
		}
		if(StringUtils.isNotNull(pk)){
			map = service.ser_idforQuery(pk,tableName);
		}
		if ("stu".equals(usertype)) {
			map = service.ser_getxsInfo(userName);
		}
		request.setAttribute("userlx", userlx);
		request.setAttribute("doType", doType);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.requestSetList(new String[]{"yf"}, request);
		request.setAttribute("rs", map);		
		return mapping.findForward("pdyjAdd");
	}
	/**
	 * 上传照片
	 * 
	 */
	public ActionForward sczp(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgbxxForm myform = (XsgbxxForm) form;
		String act = request.getParameter("act");
		XsgbxxService service = new XsgbxxService();
		String pkValue = request.getParameter("pk");
		if ("add".equals(act)) {

			String xsxh = request.getParameter("pkxsxh");
			request.setAttribute("pkxsxh", xsxh);
			String dir = servlet.getServletContext().getRealPath("/jygl/zgldgx/picture");
			File f = new File(dir);
			if (!f.exists()) {
				f.mkdir();
			}
			FormFile file = myform.getFile();
			if (file == null) {
				return mapping.findForward("false");
			}
			Timestamp date = new Timestamp(System.currentTimeMillis());
			String dateStr = date.toString().substring(0, 19);
			dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
			int size = file.getFileSize();
			String fName=file.getFileName();
			InputStream in = file.getInputStream();
			String filePath = dir + "/" + dateStr+fName;
			String savepath = "/jygl/zgldgx/picture/" + dateStr + fName;
			String[] rst = service.ser_ixexistxzp(pkValue);
			if(StringUtils.isArrayNotNull(rst)){
				String dirpath = servlet.getServletContext().getRealPath(rst[0]);
				new File(dirpath).delete();
			}
			boolean judge = false;
			judge = service.ser_addzp(pkValue, savepath, request);
			if (judge) {
				request.setAttribute("dataImported", "ok");
			} else {
				request.setAttribute("dataImported", "no");
			}
			OutputStream out = new FileOutputStream(filePath);
			int bytesRead = 0;
			byte[] buffer = new byte[size];
			while ((bytesRead = in.read(buffer, 0, size)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			out.close();
			in.close();
		}
		request.setAttribute("pk", pkValue);
		return mapping.findForward("sczp");
	}
}