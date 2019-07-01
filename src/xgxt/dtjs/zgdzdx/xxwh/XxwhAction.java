package xgxt.dtjs.zgdzdx.xxwh;

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
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.dtjs.zgdzdx.ZgdzdxDtjsForm;
import xgxt.dtjs.zgdzdx.ZgdzdxDtjsUnit;
import xgxt.utils.String.StringUtils;

public class XxwhAction extends DispatchAction {

	public ActionForward sxjyhdManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 思想教育活动主页面
		XxwhService service = new XxwhService();
		XxwhModel myModel = new XxwhModel();
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		String tableName = "view_dtjsZgdzdxZtjyhd";
		String realTable = "dtjsZgdzdxZtjyhdb";
		ZgdzdxDtjsUnit myUnit = new ZgdzdxDtjsUnit();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		BeanUtils.copyProperties(myModel, myForm);

		if (request.getParameter("go") != null
				&& !request.getParameter("go").equalsIgnoreCase("")) {
			rs = service.getSxjyhdList(myModel, tableName);
			topTr = service.getSxjyhdTopTr(tableName);
		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "zgdzdxSxjyhd.do");

		request.setAttribute("xyList", Base.getXyList());
		myUnit.commonRequestSet(request, tableName, realTable, rs, topTr);	

		// 用户信息
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		request.setAttribute("userType", userType);
		
		return mapping.findForward("sxjyhdManage");
	}

	public ActionForward sxjyhdUpdata(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 思想教育活动单个查看
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		HashMap<String, String> rs = service.getSxjyhdOne(pk);

		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs", rs);
		return mapping.findForward("sxjyhdUpdata");
	}

	public ActionForward sxjyhdSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 思想教育活动单个查看
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		XxwhModel myModel = new XxwhModel();
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		BeanUtils.copyProperties(myModel, myForm);
		boolean update = service.SxjyhdUpdate(pk, myModel, request);
		if (update) {
			request.setAttribute("update", "yes");
		} else {
			request.setAttribute("update", "no");
		}
		return mapping.findForward("sxjyhdUpdata");
	}

	public ActionForward sxjyhdDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 思想教育活动删除
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		boolean update = service.SxjyhdDelete(pk, request);
		if (update) {
			request.setAttribute("update", "yes");
		} else {
			request.setAttribute("update", "no");
		}
		return sxjyhdManage(mapping, form, request, response);
	}
	
	public ActionForward fblwManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			//发表论文主页面
			XxwhService service = new XxwhService();
			XxwhModel myModel = new XxwhModel();
			ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
			String tableName = "view_dtjsZgdzdxLwxx";
			String realTable = "dtjsZgdzdxLwxxb";
			ZgdzdxDtjsUnit myUnit= new ZgdzdxDtjsUnit();
			ArrayList<String[]> rs =new ArrayList<String[]>();
			List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
			
			BeanUtils.copyProperties(myModel, myForm);
			
			if (request.getParameter("go") != null&&!request.getParameter("go").equalsIgnoreCase("")){
				rs = service.getFblwList(myModel,tableName);
				topTr = service.getFblwTopTr(tableName);
			}
			//存放访问路径求取读写权
			request.setAttribute("path","zgdzdxJyyjxx.do");
			
			request.setAttribute("fblwlbList",service.getFblwlbList());
			request.setAttribute("bmList",service.getBmList());
			ZgdzdxDtjsUnit.formToGBK(myForm);
			myUnit.commonRequestSet(request, tableName, realTable, rs, topTr);
			return mapping.findForward("fblwManage");
	}
	
	public ActionForward fblwUpdata(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 发表论文单个查看
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		HashMap<String, String> rs = service.getFblwOne(pk);
		
		request.setAttribute("xyList",Base.getXyList());
		request.setAttribute("rs", rs);
		
		String type = request.getParameter("type"); 
		if(type!=null&&!type.equalsIgnoreCase("")){
			request.setAttribute("type", type);
		}
		request.setAttribute("bmList",service.getBmList());
		request.setAttribute("fdyList",service.getFdyList(rs));
		request.setAttribute("fblwlbList",service.getFblwlbList());
		
		
		return mapping.findForward("fblwUpdata");
	}
	
	public ActionForward fblwSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 发表论文单个查看保存
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		XxwhModel myModel = new XxwhModel();
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		
//		处理文件上传
		FormFile file = myForm.getUploadFile();
		String filePath = ""; 
		String fName    = "";
		if(file!=null){
		String dir = "/upload/dtjs/dzdx";
		File f = new File(dir);
	    if (!f.exists()) {
	    	f.mkdirs();
	    }
	    Timestamp date = new Timestamp(System.currentTimeMillis());
	    String dateStr = date.toString().substring(0, 19);
	    dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "").replaceAll(":","");
	    int size = file.getFileSize();
	    if(size<10485760&&size!=0){
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
	    }
		}
		
		myForm.setXzlj(filePath);
		BeanUtils.copyProperties(myModel, myForm);
		boolean update  = service.fblwUpdate(pk,myModel,request);
		if(update){
			request.setAttribute("update", "yes");
		}else{
			request.setAttribute("update", "no");
		}
		
		return mapping.findForward("fblwUpdata");
	}
	
	public ActionForward fblwDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 发表论文活动删除
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		boolean update  = service.fblwDelete(pk,request);
		if(update){
			request.setAttribute("update", "yes");
		}else{
			request.setAttribute("update", "no");
		}
		return fblwManage(mapping,form,request,response);
	}
	
	public ActionForward kyxmManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			//科研项目主页面
			XxwhService service = new XxwhService();
			XxwhModel myModel = new XxwhModel();
			ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
			String tableName = "view_dtjsZgdzdxKyxm";
			String realTable = "dtjsZgdzdxKyxmb";
			ZgdzdxDtjsUnit myUnit= new ZgdzdxDtjsUnit();
			ArrayList<String[]> rs =new ArrayList<String[]>();
			List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
			
			BeanUtils.copyProperties(myModel, myForm);
			
			if (request.getParameter("go") != null&&!request.getParameter("go").equalsIgnoreCase("")){
				rs = service.getKyxmList(myModel,tableName);
				topTr = service.getKyxmTopTr(tableName);
			}
			//存放访问路径求取读写权
			request.setAttribute("path","zgdzdxJyyjxx.do");
			
			request.setAttribute("xmjbList",service.getXmjbList());
			request.setAttribute("bmList",service.getBmList());
			request.setAttribute("xmjbList",service.getXmjbList());
			ZgdzdxDtjsUnit.formToGBK(myForm);
			myUnit.commonRequestSet(request, tableName, realTable, rs, topTr);
			return mapping.findForward("kyxmManage");
	}
	
	public ActionForward kyxmUpdata(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 科研项目单个查看
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		HashMap<String, String> rs = service.getKyxmOne(pk);
		
		request.setAttribute("bmList",service.getBmList());
		request.setAttribute("fdyList",service.getFdyList(rs));
		request.setAttribute("xmjbList",service.getXmjbList());
		request.setAttribute("rs", rs);
		return mapping.findForward("kyxmUpdata");
	}
	
	public ActionForward kyxmSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 科研项目单个查看保存
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		XxwhModel myModel = new XxwhModel();
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		BeanUtils.copyProperties(myModel, myForm);
		boolean update  = service.kyxmUpdate(pk,myModel,request);
		if(update){
			request.setAttribute("update", "yes");
		}else{
			request.setAttribute("update", "no");
		}
		return mapping.findForward("kyxmUpdata");
	}
	
	public ActionForward kyxmDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 科研项目删除
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		boolean update  = service.kyxmDelete(pk,request);
		if(update){
			request.setAttribute("update", "yes");
		}else{
			request.setAttribute("update", "no");
		}
		return kyxmManage(mapping,form,request,response);
	}
	
	public ActionForward fdyzzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			//辅导员著作主页面
			XxwhService service = new XxwhService();
			XxwhModel myModel = new XxwhModel();
			ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
			String tableName = "view_dtjsZgdzdxfdyzz";
			String realTable = "dtjsZgdzdxfdyzzb";
			ZgdzdxDtjsUnit myUnit= new ZgdzdxDtjsUnit();
			ArrayList<String[]> rs =new ArrayList<String[]>();
			List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
			
			BeanUtils.copyProperties(myModel, myForm);
			
			if (request.getParameter("go") != null&&!request.getParameter("go").equalsIgnoreCase("")){
				rs = service.getFdyzzList(myModel,tableName);
				topTr = service.getFdyzzTopTr(tableName);
			}
			//存放访问路径求取读写权
			request.setAttribute("path","zgdzdxJyyjxx.do");
			
			request.setAttribute("bmList",service.getBmList());
			ZgdzdxDtjsUnit.formToGBK(myForm);
			myUnit.commonRequestSet(request, tableName, realTable, rs, topTr);
			return mapping.findForward("fdyzzManage");
	}
	
	public ActionForward fdyzzUpdata(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 辅导员著作单个查看
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		HashMap<String, String> rs = service.getFdyzzOne(pk);
		
		request.setAttribute("bmList",service.getBmList());
		request.setAttribute("fdyList",service.getFdyList(rs));
		
		request.setAttribute("rs", rs);
		return mapping.findForward("fdyzzUpdata");
	}
	
	public ActionForward fdyzzSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 辅导员著作单个查看保存
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		XxwhModel myModel = new XxwhModel();
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		BeanUtils.copyProperties(myModel, myForm);
		boolean update  = service.fdyzzUpdate(pk,myModel,request);
		if(update){
			request.setAttribute("update", "yes");
		}else{
			request.setAttribute("update", "no");
		}
		return mapping.findForward("fdyzzUpdata");
	}
	
	public ActionForward fdyzzDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 辅导员著作删除
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		boolean update  = service.fdyzzDelete(pk,request);
		if(update){
			request.setAttribute("update", "yes");
		}else{
			request.setAttribute("update", "no");
		}
		return fdyzzManage(mapping,form,request,response);
	}
	
	public ActionForward yjzlManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			//辅导员著作主页面
			XxwhService service = new XxwhService();
			XxwhModel myModel = new XxwhModel();
			ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
			String tableName = "dtjsZgdzdxYjzlb";
			String realTable = "dtjsZgdzdxYjzlb";
			ZgdzdxDtjsUnit myUnit= new ZgdzdxDtjsUnit();
			ArrayList<String[]> rs =new ArrayList<String[]>();
			List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
			
			BeanUtils.copyProperties(myModel, myForm);
			
			if (request.getParameter("go") != null&&!request.getParameter("go").equalsIgnoreCase("")){
				rs = service.getYjzlList(myModel,tableName);
				topTr = service.getYjzlTopTr(tableName);
			}
			//存放访问路径求取读写权
			request.setAttribute("path","zgdzdxJyyjxx.do");
			ZgdzdxDtjsUnit.formToGBK(myForm);
			myUnit.commonRequestSet(request, tableName, realTable, rs, topTr);
			return mapping.findForward("yjzlManage");
	}
	
	public ActionForward yjzlUpdata(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 辅导员著作单个查看
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		HashMap<String, String> rs = service.getYjzlOne(pk);
		
		request.setAttribute("rs", rs);
		return mapping.findForward("yjzlUpdata");
	}
	
	public ActionForward yjzlSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 辅导员著作单个查看保存
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		XxwhModel myModel = new XxwhModel();
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		BeanUtils.copyProperties(myModel, myForm);
		boolean update  = service.yjzlUpdate(pk,myModel,request);
		if(update){
			request.setAttribute("update", "yes");
		}else{
			request.setAttribute("update", "no");
		}
		return mapping.findForward("yjzlUpdata");
	}
	
	public ActionForward yjzlDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 辅导员著作删除
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		boolean update  = service.yjzlDelete(pk,request);
		if(update){
			request.setAttribute("update", "yes");
		}else{
			request.setAttribute("update", "no");
		}
		return yjzlManage(mapping,form,request,response);
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
	
	
	/**
	 * 党员活动室管理主页.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public ActionForward dyhdsgl_Index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		XxwhService service = new XxwhService();
		DyhdsglModel model = new DyhdsglModel();
		String realTable = "";
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		int rsNum = 0;
		String usertype = (String) request.getSession().getAttribute("userType");
		if("xx".equals(usertype) || "admin".equals(usertype)){
			usertype = "xx";
		}else if("xy".equals(usertype)){
			usertype = "xy";
		}

		// 选项卡判断
		String xxk = request.getParameter("xxk");
		if (xxk == null) {
			xxk = "cdsbsy";
		}

		if (request.getParameter("go") != null
				&& !request.getParameter("go").equalsIgnoreCase("")) {
			BeanUtils.copyProperties(model, myForm);
			if ("cdsbsy".equalsIgnoreCase(xxk)) {
				// 场地设备使用申请
				realTable = "zgdx_cdsbsy";
			} else if ("zcgl".equalsIgnoreCase(xxk)) {
				// 资产管理
				realTable = "zgdx_zcgl";
			} else if ("xxzl".equalsIgnoreCase(xxk)) {
				// 学习资料
				realTable = "zgdx_xxzl";
			} else if ("zljy".equalsIgnoreCase(xxk)) {
				// 资料借阅
				realTable = "zgdx_zkjy";
			}
			ZgdzdxDtjsUnit.formToGBK(model);
			rs = service.dyhdsgl_query(xxk,model,myForm);
			topTr = service.get_dyhdsglTopTr(realTable);
			int count = service.get_count(model,realTable);
			myForm.getPages().setMaxRecord(count);// 设置最大的记录数
			if(rs!=null){
				rsNum = rs.size();
			}
		}
		ZgdzdxDtjsUnit.formToGBK(myForm);
		request.setAttribute("usertype", usertype);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		if (xxk.equalsIgnoreCase("zljy")) {
			return mapping.findForward("zljy_index");
		} else if (xxk.equalsIgnoreCase("zcgl")) {
			return mapping.findForward("zcgl_index");
		} else if (xxk.equalsIgnoreCase("xxzl")) {
			return mapping.findForward("xxzl_index");
		} else {
			return mapping.findForward("cdsbsy_index");
		}
	}
	/**
	 * 党员活动室增加.
	 */
	public ActionForward dyhdsgl_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		DyhdsglModel model = new DyhdsglModel();
		XxwhService service = new XxwhService();
		String realTable = null ;
		String czlx = request.getParameter("czlx");
		String act = request.getParameter("act");
		String pk = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		// 选项卡判断
		String xxk = request.getParameter("xxk");
		if (xxk == null) {
			xxk = "cdsbsy";
		}
		BeanUtils.copyProperties(model, myForm);
		if ("save".equals(czlx)) {
			request.setAttribute("xslx", "add");
			if (xxk.equalsIgnoreCase("cdsbsy")) {
				// 场地设备使用申请
				realTable = "zgdx_cdsbsy";
			} else if (xxk.equalsIgnoreCase("zcgl")) {
				// 资产管理
				realTable = "zgdx_zcgl";
			} else if (xxk.equalsIgnoreCase("xxzl")) {
				// 学习资料
				realTable = "zgdx_xxzl";
			} else if (xxk.equalsIgnoreCase("zljy")) {
				// 资料借阅
				realTable = "zgdx_zkjy";
			}
			if("save".equals(act)){
				boolean bool = service.dyhdsgl_save(pk, model, request, czlx, xxk);
				if(bool){
					request.setAttribute("save", "ok");
				}else{
					request.setAttribute("save", "no");
				}
			}
		}
		if ("update".equals(czlx)) {
			request.setAttribute("xslx", "update");
			if (xxk.equalsIgnoreCase("cdsbsy")) {
				// 场地设备使用申请
				realTable = "zgdx_cdsbsy";
			} else if (xxk.equalsIgnoreCase("zcgl")) {
				// 资产管理
				realTable = "zgdx_zcgl";
			} else if (xxk.equalsIgnoreCase("xxzl")) {
				// 学习资料
				realTable = "zgdx_xxzl";
			} else if (xxk.equalsIgnoreCase("zljy")) {
				// 资料借阅
				realTable = "zgdx_zkjy";
			}
			if("update".equals(act)){
				boolean bool = service.dyhdsgl_save(pk, model, request, czlx, xxk);
				if(bool){
					request.setAttribute("save", "ok");
				}else{
					request.setAttribute("save", "no");
				}
			}
			map = service.get_viewRs(pk, realTable,xxk);
		}
		if ("view".equals(czlx)) {
			request.setAttribute("xslx", "view");
			if (xxk.equalsIgnoreCase("cdsbsy")) {
				// 场地设备使用申请
				realTable = "zgdx_cdsbsy";
			} else if (xxk.equalsIgnoreCase("zcgl")) {
				// 资产管理
				realTable = "zgdx_zcgl";
			} else if (xxk.equalsIgnoreCase("xxzl")) {
				// 学习资料
				realTable = "zgdx_xxzl";
			} else if (xxk.equalsIgnoreCase("zljy")) {
				// 资料借阅
				realTable = "zgdx_zkjy";
			}
			map = service.get_viewRs(pk, realTable,xxk);
		}
		request.setAttribute("rs", map);
		ZgdzdxDtjsUnit.formToGBK(myForm);
		if (xxk.equalsIgnoreCase("zljy")) {
			return mapping.findForward("zljy_add");
		} else if (xxk.equalsIgnoreCase("zcgl")) {
			return mapping.findForward("zcgl_add");
		} else if (xxk.equalsIgnoreCase("xxzl")) {
			return mapping.findForward("xxzl_add");
		} else {
			return mapping.findForward("cdsbsy_add");
		}	
	}
	/**
	 * 党员活动室删除.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public ActionForward dyhdsgl_del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		XxwhService service = new XxwhService();
		String realTable = null ;
		String czlx = request.getParameter("czlx");
		String pks = request.getParameter("pkstring");
		// 选项卡判断
		String xxk = request.getParameter("xxk");
		if (xxk == null) {
			xxk = "cdsbsy";
		}
		if ("del".equals(czlx)) {
			request.setAttribute("xslx", "update");
			if (xxk.equalsIgnoreCase("cdsbsy")) {
				// 场地设备使用申请
				realTable = "zgdx_cdsbsy";
			} else if (xxk.equalsIgnoreCase("zcgl")) {
				// 资产管理
				realTable = "zgdx_zcgl";
			} else if (xxk.equalsIgnoreCase("xxzl")) {
				// 学习资料
				realTable = "zgdx_xxzl";
			} else if (xxk.equalsIgnoreCase("zljy")) {
				// 资料借阅
				realTable = "zgdx_zkjy";
			}
		}
		String whichpk = service.get_del(pks,realTable,request);
		if(StringUtils.isNull(whichpk)){ 
			request.setAttribute("delall", "ok");
		}else{
			request.setAttribute("whichpk", whichpk);
			request.setAttribute("delall", "no");
		}
		ZgdzdxDtjsUnit.formToGBK(myForm);
		if (xxk.equalsIgnoreCase("zljy")) {
			return mapping.findForward("zljy_index");
		} else if (xxk.equalsIgnoreCase("zcgl")) {
			return mapping.findForward("zcgl_index");
		} else if (xxk.equalsIgnoreCase("xxzl")) {
			return mapping.findForward("xxzl_index");
		} else {
			return mapping.findForward("cdsbsy_index");
		}
	}
	
	/**
	 * 党员活动室审核.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public ActionForward dyhdsgl_sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		DyhdsglModel model = new DyhdsglModel();
		XxwhService service = new XxwhService();
		String realTable = null ;
		String czlx = request.getParameter("czlx");
		String act = request.getParameter("act");
		String pk = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		// 选项卡判断
		String xxk = request.getParameter("xxk");
		if (xxk == null) {
			xxk = "cdsbsy";
		}
		BeanUtils.copyProperties(model, myForm);
		request.setAttribute("xslx", "sh");
		if("sh".equals(act)){
			boolean result = service.dyhdsgl_sh(pk, model, request, czlx, xxk);
			request.setAttribute("save", result ? "ok" : "no");
		}
		map = service.get_viewRs(pk, realTable,xxk);

		request.setAttribute("rs", map);
		ZgdzdxDtjsUnit.formToGBK(myForm);
		if (xxk.equalsIgnoreCase("zljy")) {
			return mapping.findForward("zljy_add");
		} else if (xxk.equalsIgnoreCase("zcgl")) {
			return mapping.findForward("zcgl_add");
		} else if (xxk.equalsIgnoreCase("xxzl")) {
			return mapping.findForward("xxzl_add");
		} else {
			return mapping.findForward("cdsbsy_add");
		}	
	}
	
	@SuppressWarnings("static-access")
	public ActionForward dyjhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 调研计划主页面
		XxwhService service = new XxwhService();
		XxwhModel myModel = new XxwhModel();
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		String tableName = "dtjsZgdzdxDyjhb";
		String realTable = "dtjsZgdzdxDyjhb";
		ZgdzdxDtjsUnit myUnit = new ZgdzdxDtjsUnit();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		BeanUtils.copyProperties(myModel, myForm);

		if (request.getParameter("go") != null
				&& !request.getParameter("go").equalsIgnoreCase("")) {
			rs = service.getDyjhList(myModel, tableName);
			topTr = service.getDyjhTopTr(tableName);
		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "zgdzdxDyjh.do");

		request.setAttribute("xyList", Base.getXyList());
		myUnit.commonRequestSet(request, tableName, realTable, rs, topTr);
		myUnit.formToGBK(myForm);
		return mapping.findForward("dyjhManage");
	}

	public ActionForward dyjhUpdata(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 调研计划单个查看
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		HashMap<String, String> rs = service.getDyjhOne(pk);

		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs", rs);
		return mapping.findForward("dyjhUpdata");
	}

	public ActionForward dyjhSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 调研计划单个查看
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		XxwhModel myModel = new XxwhModel();
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		BeanUtils.copyProperties(myModel, myForm);
		boolean update = service.dyjhUpdate(pk, myModel, request);
		if (update) {
			request.setAttribute("update", "yes");
		} else {
			request.setAttribute("update", "no");
		}
		return mapping.findForward("dyjhUpdata");
	}

	public ActionForward dyjhDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 调研计划删除
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		boolean update = service.dyjhDelete(pk, request);
		if (update) {
			request.setAttribute("update", "yes");
		} else {
			request.setAttribute("update", "no");
		}
		return dyjhManage(mapping, form, request, response);
	}
	
	public ActionForward xgxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 学工信息主页面
		XxwhService service = new XxwhService();
		XxwhModel myModel = new XxwhModel();
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		String tableName = "view_dtjsDdxgxx";
		String realTable = "dtjsDdxgxxb";
		ZgdzdxDtjsUnit myUnit = new ZgdzdxDtjsUnit();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		BeanUtils.copyProperties(myModel, myForm);

		if (request.getParameter("go") != null
				&& !request.getParameter("go").equalsIgnoreCase("")) {
			rs = service.getXgxxList(myModel, tableName);
			topTr = service.getXgxxTopTr(tableName);
		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "zgdzdxXgxx.do");

		request.setAttribute("bmList", service.getBmList());
		myUnit.commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("xgxxManage");
	}

	public ActionForward xgxxUpdata(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 学工信息单个查看
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		HashMap<String, String> rs = service.getXgxxOne(pk,request);
		request.setAttribute("xgxxlbList", service.getXgxxlbList());
		request.setAttribute("rs", rs);
		return mapping.findForward("xgxxUpdata");
	}

	public ActionForward xgxxSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 学工信息单个查看
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		XxwhModel myModel = new XxwhModel();
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		BeanUtils.copyProperties(myModel, myForm);
		boolean update = service.xgxxUpdate(pk, myModel, request);
		if (update) {
			request.setAttribute("update", "yes");
		} else {
			request.setAttribute("update", "no");
		}
		return mapping.findForward("xgxxUpdata");
	}

	public ActionForward xgxxDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 学工信息删除
		String pk = DealString.toGBK(request.getParameter("pk"));
		XxwhService service = new XxwhService();
		boolean update = service.xgxxDelete(pk, request);
		if (update) {
			request.setAttribute("update", "yes");
		} else {
			request.setAttribute("update", "no");
		}
		return xgxxManage(mapping, form, request, response);
	}
	
	public ActionForward xgxxTjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 学工信息统计主页面
		XxwhService service = new XxwhService();
		XxwhModel myModel = new XxwhModel();
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		ZgdzdxDtjsUnit myUnit = new ZgdzdxDtjsUnit();
		ArrayList<String[]> rs = new ArrayList<String[]>();

		BeanUtils.copyProperties(myModel, myForm);

		if (request.getParameter("go") != null
				&& !request.getParameter("go").equalsIgnoreCase("")) {
			rs = service.getXgxxTjList(myModel);
		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "zgdzdxXgxxTj.do");

		request.setAttribute("bmList", service.getBmList());
		myUnit.commonRequestSet(request, "", "", rs, null);
		return mapping.findForward("xgxxTjManage");
	}
}
