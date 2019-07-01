package xgxt.wjsc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.sql.CLOB;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.FileManage;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjsc.wjff.WjffModel;
import xgxt.wjsc.wjff.WjffService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.basic.BasicService;

public class WjscOperactionAction  extends BasicAction{

	
	/**
	 * 文件发放查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public  ActionForward fileQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WjffModel model = new WjffModel();
		WjffService service = new WjffService();
		CommanForm commanForm = (CommanForm) form;
		
		String doType = request.getParameter("doType");
		List<HashMap<String, String>> rs = null;
		
		if ("query".equals(doType)){
			User user = getUser(request);
			BeanUtils.copyProperties(model, commanForm);
			//查询文件发放
			rs = service.queryFile(model, user);
		}
		
		
		request.setAttribute("rs", rs);
		request.setAttribute("path", "fileManager.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		return mapping.findForward("fileQuery");
	}


	/**
	 * 抽原方法里的计算，没空去看懂他想干吗
	 * @param yhzList
	 * @param yhList
	 * @param yhzyhlist
	 * @param allyhlist
	 * @param jsrlist
	 */
	private void setYhzAndYhList(List<HashMap<String, String>> yhzList,
			List<HashMap<String, String>> yhList,
			List<HashMap<String, String>> yhzyhlist,
			List<HashMap<String, Object>> allyhlist,
			List<HashMap<String, String>> jsrlist) {
		String zdm1 = "";
		if(yhList !=null && yhList.size()>0){
			zdm1 = yhList.get(0).get("zdm");
		}
		
		for(int i=0;i<yhList.size()+1;i++){
			String zdm2 = "";
			if(i<yhList.size()){
				zdm2 = yhList.get(i).get("zdm");
			}
			if(!zdm1.equals(zdm2)){
				HashMap<String,Object> map =new HashMap<String,Object>();
				map.put("yhzyhmap", yhzyhlist);
				map.put("zdm", zdm1);
				allyhlist.add(map);
				zdm1 = zdm2;
				yhzyhlist = new ArrayList<HashMap<String,String>>();			
			}
			if(i<yhList.size()){
				yhzyhlist.add(yhList.get(i));
			}
		}
		if(yhzList != null && yhzList.size()>0){
			for(int j=0;j<yhzList.size();j++){
				HashMap<String,String> map =new HashMap<String,String>();
				map.put("yhm", "");
				map.put("xm", "");
				jsrlist.add(map);
			}
		}
	}
	
	
	/**
	 * 文件发放页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjffAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = Base.xxdm;
		String tableName = "wjsc_scwjxxb";
		String viewName = "view_scwjxx";
		WjffService service = new WjffService();
		String pkValue = request.getParameter("pkValue");
		
		
		//用户组列表
		List<HashMap<String, String>> yhzList = service.getYhzList(xxdm);
		//用户列表
		List<HashMap<String, String>> yhList = service.getYhList(xxdm);
		
		List<HashMap<String,String>> yhzyhlist = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,Object>> allyhlist = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String,String>> jsrlist = new ArrayList<HashMap<String,String>>();
		
		//抽原方法里的计算，没空去看懂他想干吗
		setYhzAndYhList(yhzList, yhList, yhzyhlist, allyhlist, jsrlist);
		
		request.setAttribute("allyhlist", allyhlist);
		request.setAttribute("jsrlist", jsrlist);
		request.setAttribute("yhzList", yhzList);
		
		if (StringUtils.isNotNull(pkValue)){
			selectPageDataByOne(request, tableName, viewName, pkValue);
			return mapping.findForward("wjffUpdate");
		}
		return mapping.findForward("wjffAdd");
	}
	
	
	
	// TODO 文件上传下载
	public static ActionForward PutFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		List rs = null;
		String sql = "";
		HashMap<String, String> rs1 = new HashMap<String, String>();
		String rsNum = "0";
		DAO dao = DAO.getInstance();
		String yhm = request.getSession().getAttribute("userName").toString();
		String xxdm = StandardOperation.getXxdm();
		sql = "select xm from yhb where yhm=? ";
		String xm = dao.getOneRs(sql, new String[] { yhm }, "xm");

		String query = "";
		if ("zf01".equalsIgnoreCase(yhm) || "admin".equalsIgnoreCase(yhm)) {
			query = " 1=1 ";
		} else {
			query = " ffr like '%" + xm + "%' ";
		}

		// 分页
		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from view_scwjxx a where " + query;

		rsNum = dao.getOneRs(sql, new String[] {}, "count");
		dataSearchForm.getPages().setMaxRecord(Integer.parseInt(rsNum));

		sql = "select * from (select a.*,rownum r from (select distinct a.wjh,a.wjm,a.bmmc,a.ffr,a.wjffsj from view_scwjxx a where 1=1 and "
				+ query
				+ " order by wjffsj desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by wjffsj desc";

		String[] colList = new String[] { "wjh", "wjm", "bmmc", "ffr", "wjffsj" };

		request.setAttribute("rsNum", rsNum);
		rs = dao.getList(sql, new String[] {}, colList);
				
		List yhzList = new ArrayList<HashMap<String,String>>();
		
		List<HashMap<String,String>> yhlist = new ArrayList<HashMap<String,String>>();
		//"10290".equals(xxdm)
		if("10290".equals(xxdm)){
			sql = "select zdm,zmc from yhzb a where a.zdm<>'6727' and not exists (select zdm from yhzb where zmc like '%用人单位%' and zdm = a.zdm) order by to_number(xssx) nulls last";
			yhzList = dao.getList(sql, new String[]{}, new String[]{"zdm","zmc"});
			sql = "select yhm,xm,a.zdm from yhb a,( select rownum rid,zdm, zmc,xssx from yhzb a where a.zdm <> '6727' and not exists (select zdm from yhzb " +
					"where zmc like '%用人单位%' and zdm = a.zdm) order by to_number(xssx) nulls last) b where a.zdm = b.zdm order by b.rid,to_number(a.xssx) nulls last";
		}else{
			yhzList = WjscDataAccessAction.getYhzList();
			sql = "select yhm,xm,zdm from yhb where zdm<>'6727' order by zdm ";
		}
		yhlist = dao.getList(sql, new String[]{}, new String[]{"yhm","xm","zdm"});
		String zdm1 = "";
		if(yhlist !=null && yhlist.size()>0){
			zdm1 = yhlist.get(0).get("zdm");
		}
		List<HashMap<String,String>> yhzyhlist = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,Object>> allyhlist = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String,String>> jsrlist = new ArrayList<HashMap<String,String>>();
		for(int i=0;i<yhlist.size()+1;i++){
			String zdm2 = "";
			if(i<yhlist.size()){
				zdm2 = yhlist.get(i).get("zdm");
			}
			if(!zdm1.equals(zdm2)){
				HashMap<String,Object> map =new HashMap<String,Object>();
				map.put("yhzyhmap", yhzyhlist);
				map.put("zdm", zdm1);
				allyhlist.add(map);
				zdm1 = zdm2;
				yhzyhlist = new ArrayList<HashMap<String,String>>();			
			}
			if(i<yhlist.size()){
				yhzyhlist.add(yhlist.get(i));
			}
		}
		if(yhzList != null && yhzList.size()>0){
			for(int j=0;j<yhzList.size();j++){
				HashMap<String,String> map =new HashMap<String,String>();
				map.put("yhm", "");
				map.put("xm", "");
				jsrlist.add(map);
			}
		}
		request.setAttribute("yhzList", yhzList);
		request.setAttribute("allyhlist", allyhlist);
		request.setAttribute("jsrlist", jsrlist);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", rs1);
		return mapping.findForward("success");

	}

	// 文件接收
	public static ActionForward InceptFile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		List rs = null;
		String yhm = request.getSession().getAttribute("userName").toString();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "select zdm from yhb where yhm=? ";
		@SuppressWarnings("unused")
		String yhzdminfo = dao.getOneRs(sql, new String[] { yhm }, "zdm");
		String query = "";

		if ("stu".equals(userType)) {
			sql = "select wjh,wjm,bmmc,wjffsj from view_scwjxx where wjjszdm='6727' or wjjszdm='0000'"
					+ " order by wjffsj desc";
			rs = dao.getList(sql, new String[] {}, new String[] { "wjh", "wjm",
					"bmmc", "wjffsj" });
		} else {
			query = " ','||jsr||',' like '%," + yhm + ",%' or (jsr is null and wjjszdm='0000' ) ";
			sql = "select wjh,wjm,bmmc,wjffsj from view_scwjxx where " + query
					+ " order by wjffsj desc";
			rs = dao.getList(sql, new String[] {}, new String[] { "wjh", "wjm",
					"bmmc", "wjffsj" });
		}

		request.setAttribute("rs", rs);
		return mapping.findForward("success");	
	}

	// 文件上传(保存)
	public static ActionForward SaveFile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		WjscForm wjscform = (WjscForm) form;
		WjscDao dao = new WjscDao();;
		String ffr = DealString.toGBK(request.getSession().getAttribute(
				"userName").toString());
		String wjh = request.getParameter("wjh");
		String wjm = request.getParameter("wjm");
		String wjscsm = request.getParameter("wjscsm");
		String wjffbm = request.getSession().getAttribute("userDep").toString();
		String jsrxm = request.getParameter("jsrxm");
		String wdrxm = request.getParameter("yhms");
		String yhms = request.getParameter("yhms");
		String wjffsj = WjscDataAccessAction.GetSysTime();
		String zdms = request.getParameter("zdms");
		String zmcs = request.getParameter("zmcs");
		String xxdm = StandardOperation.getXxdm();
//		String alluser = request.getParameter("alluser");
		String filePath = "";
		String message = "";
		String dir = "/upload";
		ffr = dao.getOneRs("select xm from yhb where yhm=?", new String []{ffr}, "xm");
		
		
		WjffService service = new WjffService();
		
		//大师要修改，整个先删除后增
		if (StringUtils.isNotNull(wjh)){
			
			if (StringUtils.isNotNull(wjscform.getUploadFile().getFileName())){
				
				service.delFiles(new String[]{wjh},true);
			} else {//如果修改时没上传附件就用原来的附件OK
				filePath = dao.getOneRs("select wjsclj from wjsc_scwjxxb where wjh=?", new String[]{wjh}, "wjsclj");
				service.delFiles(new String[]{wjh},false);
			}
		}
		
	    File f = new File(dir);
	    if (!f.exists()) {
		   f.mkdir();
	    }
	    Timestamp date = new Timestamp(System.currentTimeMillis());
	    String dateStr = date.toString().substring(0, 19);
	    dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "").replaceAll(":","");
	    FormFile file = wjscform.getUploadFile();
	    
	    if (StringUtils.isNull(filePath)){
		    if(StringUtils.isNull(file.getFileName())){
			   message = "请选择文件";
			   request.setAttribute("message", message);
			   request.setAttribute("inserted","no");
			   return mapping.findForward("success");
		    }
		   else{
			   int size = file.getFileSize();
			   if(size<10485760){
			   String fName = dateStr+file.getFileName();
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
			   } else {
				   	//============2010/8/2 edit by luojw==========
					request.setAttribute("wjh", wjh);
					request.setAttribute("wjm", wjm);
					request.setAttribute("wjscsm", wjscsm);
					request.setAttribute("jsrxm", jsrxm);
				 	//============end=====================
					request.setAttribute("alert", "cannot");
					return mapping.findForward("success");
				}
		   }
	    }
	    if(yhms != null && yhms.length()>0){
	    	String[] yh = yhms.split(",");
	    	String sql = "";
	    	if("10290".equals(xxdm)){
	    		sql = "select count(*) count from yhb a where a.zdm<>'6727' and not exists (select zdm from yhzb where zmc like '%用人单位%' and zdm = a.zdm)";
	    	}else{
	    		sql = "select count(*) count from yhb where zdm<>'6727'";
	    	}
		    String count = dao.getOneRs(sql, new String[]{}, "count");
		    if(yh.length == Integer.parseInt(count)){
		    	zdms = "0000";
		    	zmcs = "全体";
		    }
	    } 
	    
	    String [] setpara = {wjh,wjm,wjscsm,filePath,wjffsj,wjffbm, ffr, yhms,jsrxm,wdrxm,zdms,zmcs};
	    boolean judge= false;    
		judge=dao.saveFile(setpara);
		if(judge){
		request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "reinsert");
		}
		return new ActionForward("/wjffManage.do?method=wjffAdd&pkValue="+wjh,false);
	}

	// 文件下载 (显示)
	public static ActionForward ViewFile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		String xxdm = StandardOperation.getXxdm();
		HashMap<String, String> map = new HashMap<String, String>();
		String wjh = DealString.toGBK(request.getParameter("wjh"));
		map = WjscDataAccessAction.GetWjscInfo(wjh);
		request.setAttribute("rs", map);
		//应得人姓名
		String []jsrArr=map.get("jsr").split(",");
		map.put("jsrxm", WjscDataAccessAction.getYhxm(jsrArr));
		//应得人姓名
		String []ydrArr=map.get("ydrxm").split(",");
		map.put("ydrxm", WjscDataAccessAction.getYhxm(ydrArr));
		//未得人姓名
		String []wdrArr=map.get("wdrxm").split(",");
		map.put("wdrxm", WjscDataAccessAction.getYhxm(wdrArr));
		
		String huizhi = request.getParameter("huizhi");
		String yd = request.getParameter("yd");
		StringBuffer yd1 = new StringBuffer();
		StringBuffer wd = new StringBuffer();
		WjscDao dao = new WjscDao();
		HttpSession session = request.getSession();
		String yhm = (String) session.getAttribute("userName");
		//判断是否已度
			if (yd == "yes" || "yes".equals(yd)) {
				
				boolean bool = false;
//				在判断指定文件接收人是否阅读文件时不应该用姓名判断 qlj 修改为YHM(2011.3.24)
				//			//查出接受人姓名	   
//				String sql1 = "select xm from yhb where yhm=?";
//				String[] inputValue1 = { yhm };
//				String outputValue1 = "xm";
//				List<String> zsxm = dao
//						.getList(sql1, inputValue1, outputValue1);
//				String yhxm = zsxm.get(0);
				//查出接受人
				String sql = "select ydrxm,wdrxm from wjsc_scwjxxb where wjh=?";
				String ydrxm, wdrxm;
				CLOB clob1 = dao.getOneClob(sql, new String[]{wjh}, "ydrxm");
				//应得人
				ydrxm = clob1 == null ? "":(clob1.getSubString(1L, (int) clob1.length()).trim());
				CLOB clob2 = dao.getOneClob(sql, new String[]{wjh}, "wdrxm");
				//未得人
				wdrxm = clob2 == null ? "":clob2.getSubString(1L, (int) clob2.length());
				String[] ydxm1 = ydrxm.split(",");
				for (String ydrxm2 : ydxm1) {
					if(!"".equals(ydrxm)){
						// 这里不应该用用户姓名做判断 改成用户名
						if (yhm.equals(ydrxm2)) {
							bool = true;
						} else {
							yd1.append(ydrxm2 + ",");
						}
					}						
				}
				String[] wd1 = wdrxm.split(",");
				if(!bool){
					for (String wd2 : wd1) {
						if (!(yhm.equals(wd2))) {
							wd.append(wd2 + ",");
						} else {
							yd1.append(wd2 + ",");
						}
					}
				}	
				String sqlb = "select ydrxm,wdrxm from wjsc_scwjxxb where wjh=? for update";
				if (!bool) {				
					String[] inputValue3 = new String[]{ yd1.toString(), wd.toString(), wjh };	
					dao.saveOneFileModi(sqlb, inputValue3,new String[]{wjh});
				} 
			}
		if ("1".equals(huizhi)) {
			return mapping.findForward("huizhi");
		} else {
			return mapping.findForward("success");
		}
	}

	// 文件下载
	public static ActionForward DownLoadFile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		byte b[] = new byte[500];
		String dir = DealString.toGBK(request.getParameter("wjsclj"));
		String filename = dir.substring(22, dir.length());
		
		FileManage.downLoadFile(dir, filename, response);
		
		return null;
	}

	// 文件管理
	public static ActionForward ManagerFile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		WjscForm wjform = (WjscForm) form;
		
		String userName=session.getAttribute("userName").toString();
//		String xxdm = StandardOperation.getXxdm();
		StringBuffer querry = new StringBuffer();
		String wjm = DealString.toGBK(request.getParameter("wjm"));
		String jsr = DealString.toGBK(request.getParameter("jsr"));
		querry = WjscDataAccessAction.getQuerry(wjm, jsr);
		String tableName = "view_scwjxx";
		String realTable = "wjsc_scwjxxb";
		List<String[]> list = new ArrayList<String[]>();
		List topTr = null;
		String[] colList  ;
//		if (xxdm == "10290" || "10290".equals(xxdm)) {
//			colList = new String[]{ "wjh", "wjm", "jsrxm", "wjffsj" };
//		}else{
//			colList = new String[]{"wjh","wjm","wjjszmc","jsrxm","ffr","wjffsj"};
//		}
		colList = new String[]{"wjh","wjm","wjjszmc","jsrxm","ffr","wjffsj"};
		String rsNum = "";
		String go = request.getParameter("go");
		topTr = WjscDataAccessAction.getTheadList(colList, tableName);
		if ("go".equalsIgnoreCase(go)) {
			list = WjscDataAccessAction.getFileInfo(tableName, realTable,
					new String[]{"wjh","wjm","wjjszmc","ffr","wjffsj"}, querry.toString(),userName);
			request.setAttribute("rs", list);
			rsNum = (list == null) ? "0" : String.valueOf(list.size());
		}
		wjform.setWjm(wjm);
		wjform.setJsr(jsr);
		request.setAttribute("realTable", realTable);
		request.setAttribute("tableName", tableName);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("success");
	}

	
	/**
	 * 文件发放删除操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjffDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WjffService service = new WjffService();
		String[] pkValues = request.getParameterValues("primarykey_cbv");
		
		boolean result = service.delFiles(pkValues,true);
		
		request.setAttribute("result", result);
		return new ActionForward("/wjffManage.do?method=fileQuery&doType=query",false);
	}
	
	/**
	 * 更多公文
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws InvocationTargetException 
	 * @throws Exception 
	 */
	public static ActionForward gwjsMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		WjscForm myForm = (WjscForm)form;
		WjscModel model = new WjscModel();
		BeanUtils.copyProperties(model, myForm);
		
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName") != null ? session.getAttribute("userName").toString() : "";
		
		String query =  "where jsr like '%" + userName + "%' or (jsr is null and wjjszdm='0000')  order by wjffsj desc";
		List<HashMap<String, String>> list = CommonQueryDAO.dao_getInfotoListFy("VIEW_SCWJXX", new String[]{"wjh", "wjm", "wjffsj"}, query, model);
		request.setAttribute("rs", list);
		return mapping.findForward("success");
	}
	
	/**
	 * 公文接收
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static ActionForward gwjsView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		return new ActionForward("/gwjs.jsp");
	}
	
	/**
	 * 文件导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwjsSjdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session=request.getSession();
		CommService commService=new CommService();
		BasicService basicService=new BasicService();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		
		String userName = session.getAttribute("userName").toString();
		String tableName = "view_scwjxx";
		String realTable = "wjsc_scwjxxb";
		String[]colList ={"wjh","wjm","wjjszmc","ffr","wjffsj"};
		
		CommanForm commanForm = (CommanForm) form;
		WjffModel model = new WjffModel();
		BeanUtils.copyProperties(model, commanForm);
		WjffService service = new WjffService();
		User user = getUser(request);
		HashMap<String, Object> map = service.getQueryFileSql(model, user);
		
		ArrayList<String[]>list=(ArrayList<String[]>)WjscDataAccessAction.getWjxxList(colList, (String[])map.get("input"),map.get("sql").toString(),userName);
		String[]topTr=new String[]{"文件号","文件名","文件发放部门","文件接收部门",
				"接收人","保存科室","管理人","移交人","文件发放时间","文件接收时间",
				"文件上传路径","文件上传说明","发放人","文件接收组代码","部门名称","申报人",
				"接收人姓名","接收组名称"};
		commService.expToExcel("文件导出", basicService.getTopTr("",topTr), list, response.getOutputStream());
		
		return mapping.findForward("");
	}
}
