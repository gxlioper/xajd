package xgxt.szdw.action.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.utils.GetWriteAbleAndTitle;
import xgxt.szdw.form.common.WdglForm;
import xgxt.szdw.server.common.WdglService;
import xgxt.wjsc.WjscDataAccessAction;

public class WdglAction extends DispatchAction{
	
	/**
	* <p>Title: 学工管理系统</p>
	* <p>Description: 学生信息管理思政队伍-思政队伍-思政文档管理action类</p>
	* <p>Copyright: Copyright (c) 2009</p>
	* <p>Company: zfsoft</p>
	* @author 鲁宁
	* @version 1.0
	*/
	
	//文档文件管理 
	public ActionForward wdgl(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
			WdglService myService = new WdglService();
			
			//获取一个月内的发布信息
			ArrayList<String[]> rs = myService.getWdxx();
			//获取文档的类型列表
			List<HashMap<String, String>> lxrs = myService.getWdlxList(); 
			List<HashMap<String, ArrayList>> allrs = new ArrayList<HashMap<String, ArrayList>>();
			for(int i = 0;i<lxrs.size();i++){
				HashMap<String, ArrayList> temp = new HashMap<String, ArrayList>();
				String wdzldm = lxrs.get(i).get("wdzldm");
				String wdzlmc = lxrs.get(i).get("wdzlmc");
				ArrayList<String[]> rsTmp = new ArrayList<String[]>();
				for(int j = 0;j<rs.size();j++){
					String[] detmp = rs.get(j);
					if(detmp[0].equalsIgnoreCase(wdzldm)){
						rsTmp.add(detmp);
					}
				}
				ArrayList<String> tmpwd = new ArrayList<String> ();
				tmpwd.add(wdzldm);
				tmpwd.add(wdzlmc);
				temp.put("wdzlrs", tmpwd);
				temp.put("wdrs", rsTmp);
				allrs.add(temp);
			}
			String writeAble  = request.getParameter("writeAble");
			String title      = DealString.toGBK(request.getParameter("title"));
			request.setAttribute("title", title);
			request.setAttribute("writeAble", writeAble);
			request.setAttribute("lxrs", lxrs);
			request.setAttribute("allrs", allrs);
			return mapping.findForward("wdgl");			
	}
	
	//	文档发放查看
	public ActionForward wdff(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
			WdglService myService = new WdglService();
			
			//获取一个月内的发布信息
			ArrayList<String[]> rs = myService.getWdxx();
			//获取文档的类型列表
			List<HashMap<String, String>> lxrs = myService.getWdlxList(); 
			List<HashMap<String, ArrayList>> allrs = new ArrayList<HashMap<String, ArrayList>>();
			for(int i = 0;i<lxrs.size();i++){
				HashMap<String, ArrayList> temp = new HashMap<String, ArrayList>();
				String wdzldm = lxrs.get(i).get("wdzldm");
				String wdzlmc = lxrs.get(i).get("wdzlmc");
				ArrayList<String[]> rsTmp = new ArrayList<String[]>();
				for(int j = 0;j<rs.size();j++){
					String[] detmp = rs.get(j);
					if(detmp[0].equalsIgnoreCase(wdzldm)){
						rsTmp.add(detmp);
					}
				}
				ArrayList<String> tmpwd = new ArrayList<String> ();
				tmpwd.add(wdzldm);
				tmpwd.add(wdzlmc);
				temp.put("wdzlrs", tmpwd);
				temp.put("wdrs", rsTmp);
				allrs.add(temp);
			}
			String writeAble  = request.getParameter("writeAble");
			String title      = DealString.toGBK(request.getParameter("title"));
			request.setAttribute("title", title);
			request.setAttribute("writeAble", writeAble);
			request.setAttribute("lxrs", lxrs);
			request.setAttribute("allrs", allrs);
			return mapping.findForward("wdff");			
	}
	
	public ActionForward wdffzldd(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
			WdglService myService = new WdglService();
			
			String wdzl = request.getParameter("wdzldm");
			ArrayList<String[]> rs = myService.getWdxxList(wdzl);
			request.setAttribute("rs", rs);
			request.setAttribute("wdzldm", wdzl);
			return mapping.findForward("dzwdffgl");	
	}
	
	public ActionForward wdwjSave(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
			WdglForm myform = (WdglForm)form;
			WdglService myService = new WdglService();
			String message = "";
			String filePath = ""; 
			String fName    = "";
			boolean inserted = false;
			
			String title = request.getParameter("title");
			String wjlx = DealString.toGBK(request.getParameter("wjlx"));
			FormFile file = myform.getUploadFile();
			String dir = "/upload/szdw";
			File f = new File(dir);
		    if (!f.exists()) {
			   f.mkdir();
		    }
		    Timestamp date = new Timestamp(System.currentTimeMillis());
		    String dateStr = date.toString().substring(0, 19);
		    dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "").replaceAll(":","");
		    if(file==null){
				   message = "文件上传失败!";
				   request.setAttribute("message", message);
				   request.setAttribute("inserted","no");
				   return mapping.findForward("success");
			}else{
				   int size = file.getFileSize();
				   if(size<10485760){
					   fName = dateStr+file.getFileName();
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
				   }else{
					   request.setAttribute("alert", "cannot");
					   return mapping.findForward("success");
				   }
			}
		    inserted = myService.saveWdff(title,wjlx,filePath,dateStr,fName,request);
		    if(inserted){
				request.setAttribute("inserted", "ok");
			}else{
				request.setAttribute("inserted", "reinsert");
			}
			return wdgl(mapping,form,request,response);	
	}
	
	//文件删除
	public ActionForward delFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "szdw_wdffb";
		String iswin = request.getParameter("isWin");
		String sclj = DealString.toGBK(request.getParameter("dir"));
		String wjsclj = WjscDataAccessAction.getFilePath(sclj);
		if ((new File(wjsclj).exists())) {
			try {
				new File(wjsclj).delete();
			} catch (SecurityException e) {
				System.out.println("不能删除该文件!");
			}
		}
		Boolean del = StandardOperation.delete(tableName, "sclj", sclj, request);
		if(del){
			request.setAttribute("del", "yes");
		}else {
			request.setAttribute("del", "no");
		}
		if(iswin!=null){
			return wdzldd(mapping,form,request,response);	
		}else{
			return wdgl(mapping,form,request,response);	
		}
	}
	
	public ActionForward wdzldd(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
			WdglService myService = new WdglService();
			
			String wdzl = request.getParameter("wdzldm");
			ArrayList<String[]> rs = myService.getWdxxList(wdzl);
			request.setAttribute("rs", rs);
			request.setAttribute("wdzldm", wdzl);
			return mapping.findForward("dzwdgl");	
	}
	
	public ActionForward downLoadFile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		byte b[] = new byte[500];
		String dir = DealString.toGBK(request.getParameter("dir"));
		String filename = dir.substring(27, dir.length());
		File fileload = new File(dir);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(filename));
		InputStream in = new FileInputStream(fileload);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
	}
	
	@SuppressWarnings("unused")
	private void commonRequestSet(HttpServletRequest request, String tableName, String realTable, ArrayList<String[]> rs, List topTr) {
		// Request存值的通用方法2 区别是title从数据库里取
		String writeAble  = request.getParameter("writeAble");
		String title      = DealString.toGBK(request.getParameter("title"));
		if(Base.isNull(writeAble)) {
			String [] message = GetWriteAbleAndTitle.getWriteAbleAndTitle(request);
			writeAble = message[0];
			title     = message[1];
		}
		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
	}
}
