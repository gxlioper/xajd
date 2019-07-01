/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.qgzx.bjlhdx;

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
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.form.CommanForm;


public class BjlhQgzxAction extends DispatchAction {
	
	/** 
	 * 显示数据导入页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward dataUpload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return mapping.findForward("import");
	}
	
	/** 
	 * 显示数据导入页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward dataImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return mapping.findForward("success");
	}
	
	public ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String dir = servlet.getServletContext().getRealPath("/upload");
		String id = request.getParameter("id");
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
		HttpSession session = request.getSession();
		String fName = session.getAttribute("userName").toString();
		String tabName = request.getParameter("tabName");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		fName += date.toString().substring(0, 19);
		fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(":",
				"");
		CommanForm hff = (CommanForm) form;
		FormFile file = null;
		if(id!=null && id.equalsIgnoreCase("file")){
			file = hff.getFile();
		}else if(id != null && id.equalsIgnoreCase("uploadFile")){
			file = hff.getUploadFile();
		}else if(id != null && id.equalsIgnoreCase("file2")){
			file = hff.getFile2();
		}
		if (file == null) {
			return mapping.findForward("false");
		}
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
		return mapping.findForward("loadSuccess");
	}
	
	/**
	 * 岗位信息修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception 
	 * */
	public ActionForward modiGwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommanForm model = (CommanForm) form;
		BjlhQgzxService service = new BjlhQgzxService();
//		String writeAble = Base.getWriteAble(request);
		
		request.setAttribute("result", service.modiGwxx(model,request));
		request.setAttribute("gwxzList", service.getGwxzList());
		request.setAttribute("rs", new CommanForm());
		request.setAttribute("writeAble", "yes");//writeAble
		return mapping.findForward("gwxx");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception  
	 * */
	public ActionForward changeWorkLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BjlhQgzxService service = new BjlhQgzxService();
		HttpSession session = request.getSession();
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		String userType = session.getAttribute("userType").toString();
		String nd = request.getParameter("nd");
		String yf = request.getParameter("yf");
		String[] colList = service.getWorkLogTitle();
		String pk = "xh||gwdm||sqsj";
		String xh = "";
		
		HashMap<String, String> rsMap = new HashMap<String, String>();
		HashMap<String, String> workMap = new HashMap<String, String>();
		HashMap<String, String> paraMap = new HashMap<String, String>();
		List sjList = new ArrayList();
		
		paraMap = service.getQgzxParameter();//参数信息
		rsMap = service.getStuPost(pk, pkVal);//学生岗位信息
		sjList = service.getSjList();//获取时间下拉列表数据
		
		xh = rsMap.get("xh");
		nd = nd == null  ? "" : nd;
		yf = yf == null ? "" : yf;
		yf = String.valueOf(Integer.parseInt(yf));
		
		String time = nd + yf;
		workMap = service.getStuWorkLog(xh,time);//学生按年度月份的工作记录信息
	
		

		//北京联合大学可任意修改每天的记录信息
		for(int i=1; i<colList.length; i++){
			request.setAttribute(colList[i], "on");
		}
		
		rsMap.put("nd", nd);
		rsMap.put("yf", yf);
		rsMap.putAll(workMap);
			
		paraMap.put("mxsbc", paraMap.get("mxsbc")==null ? "" : paraMap.get("mxsbc"));
		request.setAttribute("mxsbc", paraMap.get("mxsbc"));
		request.setAttribute("myzgxs", paraMap.get("myzgxs"));
		request.setAttribute("sjList", sjList);
		request.setAttribute("rs", rsMap);
		request.setAttribute("userType", userType);	
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("yfList", service.getYfList());
		request.setAttribute("gzjl", "gzjl");
		request.setAttribute("doType", "gzjl");
		return mapping.findForward("workLog");
	}
}