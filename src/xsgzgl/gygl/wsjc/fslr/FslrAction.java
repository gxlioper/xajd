package xsgzgl.gygl.wsjc.fslr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.gygl.comm.GyglNewInit;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.log.SystemLog;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-28 上午11:29:22
 * </p>
 */
public class FslrAction extends BasicAction {

	/**
	 * 卫生检查，卫生分录入信息的查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fslrCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FslrService service = new FslrService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("gyglnew_wsjc_fslr.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("fslrCx");
	}

	/**
	 * 卫生检查，卫生分录入选中日程对各寝室信息的操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fslrCz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
		FslrService service = new FslrService();
		FslrForm fslrForm = (FslrForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		request.setAttribute("pkValue", pkValue);
		
		HashMap<String,String> jcrc = service.getFslrCz2(null, pkValue);
		
		String jclx = jcrc.get("jclx");
		
		if (StringUtil.isNull(jclx)){
			jclx = GyglNewInit.JFFS;
		}
		
		if (("0").equals(jclx)) {
			request.setAttribute("sfsdj", "0");
		} else {
			request.setAttribute("sfsdj", "1");
		}
		// 检查日程
		HashMap<String, String> jcrcbt = service.getFslrCz2(fslrForm, pkValue);
		String jcrcmc = jcrcbt.get("mc") + "[" + jcrcbt.get("kssj") + "至" + jcrcbt.get("jssj") + "]";
		request.setAttribute("jcrc", jcrcmc);
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("gyglnew_wsjc_fslr.do?searchType=fslrcz");
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_sf(new String[]{"否"});
		searchModel.setSearch_tj_sfrz(new String[]{"是"});
		searchModel.setPath("gyglnew_wsjc_fslr.do?searchType=fslrcz");
		request.setAttribute("searchTj", searchModel);
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("fslrCz");
	}

	/**
	 * 卫生检查，卫生分录入选中日程对各寝室信息保存的查看
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fslrCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FslrService service = new FslrService();
		FslrForm fslrForm = (FslrForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		request.setAttribute("pkValue", pkValue);
		String str = service.unicode2Gbk(request.getParameter("str"));
		String bzStr = request.getParameter("bzStr");
		request.setAttribute("str", str);
		request.setAttribute("bzStr", bzStr);
		
		if(Base.xxdm.equals("33333")){//浙江商业技师学院增加扣分依据个性化保存
			if(null != request.getParameter("kfyjStr")){				
				request.setAttribute("kfyjStr", request.getParameter("kfyjStr"));
			}
		}
		if("11647".equals(Base.xxdm)){//浙江传媒学院是否毕业寝室个性化保存
			if(null != request.getParameter("byqs")){
				request.setAttribute("byqs", request.getParameter("byqs"));
			}
		}
		request.setAttribute("xxdm", Base.xxdm);
		
		HashMap<String,String> jcrc = service.getFslrCz2(null, pkValue);
		
		String jclx = jcrc.get("jclx");
		
		if (StringUtil.isNull(jclx)){
			jclx = GyglNewInit.JFFS;
		}
		
		request.setAttribute("sfsdj", jclx);
		// 检查日程
		HashMap<String, String> jcrcbt = service.getFslrCz2(fslrForm, pkValue);
		String jcrcmc = jcrcbt.get("mc") + "[" + jcrcbt.get("kssj") + "至" + jcrcbt.get("jssj") + "]";
		request.setAttribute("jcrc", jcrcmc);
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("gyglnew_wsjc_fslr.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("fslrCk");
	}
	@SystemLog(description="访问公寓管理-卫生检查-卫生分录入-导入")
	public ActionForward fsdr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FslrService service = new FslrService();
//		FslrForm fslrForm = (FslrForm) form;
//		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		request.setAttribute("pkValue", pkValue);
		String doType=request.getParameter("doType");
		
		HashMap<String,String> jcrc = service.getFslrCz2(null, pkValue);
		
		String jclx = jcrc.get("jclx");
		
		if (StringUtil.isNull(jclx)){
			jclx = GyglNewInit.JFFS;
		}
		
		if("import".equalsIgnoreCase(doType)){
			uploadFile(mapping, form, request, response);
			String back= service.importData(request,response);//导入数据
			String sfdcExcel=(String)request.getAttribute("sfdcExcel");
			if("yes".equals(sfdcExcel)){
				return mapping.findForward("");
			}
			request.setAttribute("back", back);
		}
		request.setAttribute("jclx", jclx);
		return mapping.findForward("importData");
	}

	private ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO 自动生成方法存根
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
		FslrForm hff = (FslrForm) form;
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
	
	/** 
	 * @描述:扣分详情页面(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-1 下午02:49:06
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
	public ActionForward fslrDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
		String lddm = request.getParameter("lddm");
		String jcrcid = request.getParameter("jcrcid");
		String qsh = (String)request.getParameter("qsh");
		request.setAttribute("lddm", lddm);
		request.setAttribute("jcrcid", jcrcid);
		request.setAttribute("qsh", qsh);
		KflrForm kfForm = new KflrForm();
		kfForm.setJcrcid(jcrcid);
		kfForm.setQsh(qsh);
		kfForm.setLddm(lddm);
		KflrService kfService = new KflrService();
		//判断是否为保存
		boolean result = kfService.isBcfs(kfForm);
		request.setAttribute("bcfs", result);
		//获取扣分明细
		List<HashMap<String, String>> list = kfService.getKfmx(kfForm);
		request.setAttribute("mxList", list);		
		return mapping.findForward("fslrDetail");
	}
	
	/**
	 * 
	 * @描述: 浙江传媒卫生分统计查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-3 上午09:32:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward zjcmWsfTjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_nd(new String[] { Base.currXn.substring(5, 9) });
		request.setAttribute("searchTj", searchModel);
		String path = "gyglnew_fslr.do?method=zjcmWsfTjCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "xg_gygl_zjcm_tjcx.do");
		return mapping.findForward("tjcx");
	}
}