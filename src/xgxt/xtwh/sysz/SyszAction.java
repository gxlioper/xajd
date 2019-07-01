package xgxt.xtwh.sysz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.xszz.comm.XszzCommService;
import xgxt.xtwh.XtwhInit;
import xsgzgl.xtwh.general.news.NewQxfwForm;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.basic.BasicService;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.SearchUtil;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_首页设置_action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SyszAction extends BasicAction {
	// =========== 初始化 begin ============
	private List<HashMap<String, String>> radioList = new ArrayList<HashMap<String, String>>();
	{
		addRadioList("全校", "师生", "teastu", radioList);
		addRadioList("教师", "教师", "tea", radioList);
		addRadioList("学生", "学生", "stu", radioList);
	}

	private void addRadioList(String pName, String cName, String toWho, List<HashMap<String, String>> radioList){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("pName", pName);
		map.put("cName", cName);
		map.put("toWho", toWho);
		radioList.add(map);
	}
	// =========== 初始化 end ============
	/**
	 * 首页- Ajax加载下载专区文件信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadFilesInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getFilesInfoList(myForm,user);
		
		JSONArray filesInfoList = JSONArray.fromObject(resultList);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(filesInfoList);
		
		return null;
	}
	
	

	/**
	 * 系统维护_首页设置_下载专区管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问系统维护-首页设置-下载专区维护:删除values:{primarykey_checkVal}")
	public ActionForward xzzqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();
		XtwhInit init = new XtwhInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getXtwhXzzqInit(rForm, myForm, request);

		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();

		// 是否查询操作————不判断该字段，点击菜单默认执行查询操作--20121225--zhanghui
//		boolean search = Boolean.parseBoolean(rForm.getSearch());

		// 结果集显示字段
		String[] colList = rForm.getColList();

		// 结果列表
		ArrayList<String[]> rsArrList = null;
		// =================== end ===================

		// ============= 执行删除操作 ============
		if ("del".equalsIgnoreCase(doType)) {

			String[] checkVal = myForm.getPrimarykey_checkVal();

			if (checkVal != null && checkVal.length > 0) {

				boolean flag = service.delXzzq(myForm, user, request);

				String message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
						: MessageInfo.MESSAGE_DEL_FALSE;

				rForm.setMessage(message);
			}

		}
		// =================== end ==============

		// =============== 执行查询操作 ===========
//		if (search) {————不判断该字段，点击菜单默认执行查询操作--20121225--zhanghui
			// 结果集
			rsArrList = service.getXzzqRsList(myForm, user, colList);
//		} else {
//			myForm.getPages().setMaxPage(1);
//		}

		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setXtwhOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("xzzqManage");
	}

	/**
	 * 系统维护_首页设置_显示项目维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问系统维护-首页设置-下载专区维护:增加或保存NEWSID:{newsId},FILEMC:{filemc},FILELX:{filelx},FILESS:{filess},UPDATEFILE:{updateFile}")
	public ActionForward xzzqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();
		XtwhInit init = new XtwhInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getXtwhXzzqInit(rForm, myForm, request);

		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		// =================== end ===================
		myForm.setFilemc(StringEscapeUtils.escapeHtml4(myForm.getFilemc()));
		myForm.setFilesm(StringEscapeUtils.escapeHtml4(myForm.getFilesm()));
		myForm.setBz(StringEscapeUtils.escapeHtml4(myForm.getBz()));
		request.setAttribute("filelx",myForm.getFilelx());
		// ============= 执行保存操作 ============
		if ("save".equalsIgnoreCase(doType)) {

			String filepath = myForm.getFilepath();

			if (Base.isNull(filepath)) {
				FormFile file = myForm.getUploadFile();

				// 上传路径
				filepath = service.upLoadFile(request, file, "xzzq");

			}

			if (!Base.isNull(filepath)) {
				myForm.setFilepath(filepath);
				boolean flag = service.saveXzzq(myForm, user, request);
				String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
						: MessageInfo.MESSAGE_SAVE_FALSE;
				rForm.setMessage(message);
				rForm.setPk(myForm.getNewsid());
			}
		}else if("modi".equalsIgnoreCase(doType)){

			FormFile file = myForm.getUpdateFile();
			String path="";
			if (!"".equalsIgnoreCase(file.getFileName())) {
				path = service.upLoadFile(request, file, "xzzq");
				if(!StringUtils.isEmpty(path)){
					//删除文件
					service.delUpLoadFile(new String[]{myForm.getFilepath()});
					//删除记录
					service.deleteFile(myForm.getFilepath());
					myForm.setFilepath(path);
				}
			}
			//if (!Base.isNull(path)) {
			boolean flag = service.saveXzzq(myForm, user, request);
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
			rForm.setPk(myForm.getNewsid());
			//}
		}
		// =================== end ==============

		// =============== 初始化页面显示值 ===========
		HashMap<String, String> rs = new HashMap<String, String>();
		if ("add".equalsIgnoreCase(doType)) {// 增加
//			rs.put("xzdx", "全部");
			rs.put("newsid", "");
		} else {
			myForm.setNewsid(rForm.getPk());
			//rForm.setPk(myForm.getFilepath());
			rs = service.getXzzqInfo(myForm);
			if (!"view".equalsIgnoreCase(doType)) {
				String bz = rs.get("bz")!=null?rs.get("bz"):"";
				String filesm = rs.get("filesm")!=null?rs.get("filesm"):"";
				rs.put("bz",bz.replaceAll("<br/>", "\\\n"));
				rs.put("filesm",filesm.replaceAll("<br/>", "\\\n"));	
			}
		}
		rForm.setRs(rs);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setXtwhOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		request.setAttribute("doType", doType);
		service.setRequestValue(rForm, user, request);
		request.setAttribute("radioList", radioList);
		if ("add".equalsIgnoreCase(doType)) {// 增加
			
		} else {
			NewQxfwForm nqf = SearchUtil.getInstance().getNewQxfwForm(rForm.getPk());
			request.setAttribute("search", nqf);
			request.setAttribute("selectTj", SearchUtil.getInstance().getSelectTj(nqf)); 
		}
		// =================== end ===================
		if ("view".equalsIgnoreCase(doType)) {// 增加
			return mapping.findForward("viewXzzq");
		} else{
			return mapping.findForward("xzzqUpdate");
		}
	}

	/**
	 * 系统维护_首页_下载专区(more)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xzzqView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();
		XtwhInit init = new XtwhInit();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		request.setAttribute("writeAble", "no");//隐藏 操作按钮
		RequestForm rForm = new RequestForm();
		init.getXtwhXzzqInit(rForm, myForm, request);
		String[] colList = rForm.getColList();
		ArrayList<String[]> rsArrList = service.getXzzqMoreList(myForm, user, colList);
		rForm.setRsArrList(rsArrList);
		service.setXtwhOptionList(myForm, rForm, request);
		service.setRequestValue(rForm, user, request);
		
		return mapping.findForward("xzzqMore");
	}

	/**
	 * 系统维护_首页设置_首页调查（管理）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sydcManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();
		XtwhInit init = new XtwhInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getXtwhSydcInit(rForm, myForm, request);

		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();

		// 是否查询操作————不判断该字段，点击菜单默认执行查询操作--20121225--zhanghui
//		boolean search = Boolean.parseBoolean(rForm.getSearch());

		// 结果集显示字段
		String[] colList = rForm.getColList();

		// 结果列表
		ArrayList<String[]> rsArrList = null;

		// =================== end ===================

		// ============= 执行删除操作 ============
		if ("del".equalsIgnoreCase(doType)) {

			String[] checkVal = myForm.getPrimarykey_checkVal();

			if (checkVal != null && checkVal.length > 0) {

				boolean flag = service.delSydc(myForm, user, request);

				String message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
						: MessageInfo.MESSAGE_DEL_FALSE;

				rForm.setMessage(message);
			}

		}
		// =================== end ==============

		// ============= 执行保存是否启用操作 ============
		if ("save".equalsIgnoreCase(doType)) {

			// 调查是否启用
			String dcqy = myForm.getDcqy();
			myForm.setDcid(dcqy);

			boolean flag = service.saveSydcSfqy(myForm, user);

			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;

			rForm.setMessage(message);
		}
		// =================== end ==============

		// =============== 执行查询操作 ===========
//		if (search) {————不判断该字段，点击菜单默认执行查询操作--20121225--zhanghui
			// 结果集
			rsArrList = service.getSydcRsList(myForm, user, colList);
//		}
		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setXtwhOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("sydcManage");
	}

	/**
	 * 系统维护_首页设置_首页调查（维护）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sydcUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();
		XtwhInit init = new XtwhInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getXtwhSydcInit(rForm, myForm, request);

		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		// =================== end ===================

		// ============= 执行保存操作 ============
		if ("save".equalsIgnoreCase(doType)) {

			boolean flag = service.saveSydc(myForm, user, request);
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);

		}
		// =================== end ==============

		// =============== 初始化页面显示值 ===========
		HashMap<String, String> rs = new HashMap<String, String>();
		if (Base.isNull(doType)) {// 增加
			rs.put("sfqy", "是");
		} else {
			myForm.setDcid(rForm.getPk());
			rs = service.getSzdcInfo(myForm);
		}
		rForm.setRs(rs);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setXtwhOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("sydcUpdate");
	}

	/**
	 * 系统维护_首页设置_首页调查（查看统计）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sydcView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();
		XtwhInit init = new XtwhInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getXtwhSydcInit(rForm, myForm, request);
		// =================== end ===================

		// =============== 初始化页面显示值 ===========
		// 调查ID
		String dcid = request.getParameter("dcid");

		// 调查内容
		myForm.setDcid(dcid);
//		String dcnr = service
//				.getOneValue("xg_xtwh_sydcb", "dcnr", "dcid", dcid);
		myForm.setDcid(dcid);
		request.setAttribute("dcMap", service.getSydcInfo(myForm));
		
		// 调查内容
		List<HashMap<String, String>> rsList = service.getSydcTjList(myForm,
				user);
		rForm.setRsList(rsList);
	
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setXtwhOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		//request.setAttribute("dcnr", dcnr);
		// =================== end ===================

		return mapping.findForward("sydcView");
	}

	// =======================以上made by 伟大的骆=====================

	/**
	 * 学生申请信息 author 潇洒的裘
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward xssqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		SyszForm myForm = (SyszForm) form;
		BasicService basicService = new BasicService();
		SyszService service = new SyszService();

		String userName = session.getAttribute("userName").toString();
		myForm.setUserName(userName);

		// 查询
		request.setAttribute("rs", service.getXssqInfo(myForm));

		// 初始化下拉列表
		service.getXssqInit(request,myForm);
		request.setAttribute("path", "newsInfo.do");
		
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		
		request.setAttribute("xmdm", myForm.getXmdm());
		request.setAttribute("mklx", myForm.getMklx());
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("topTr", basicService.getTopTr("xg_sysz_tpszb",
				new String[] { "项目名称", "申请周期", "申请结果" }));
		
		return mapping.findForward("xssqInfo");
	}

	/**
	 * 待办事项信息 author 潇洒的裘
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward dbsxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();

		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		boolean fdyQx=false;
		boolean bzrQx=false;
		try{
			bzrQx=(Boolean)session.getAttribute("bzrQx");
			fdyQx=(Boolean)session.getAttribute("fdyQx");
		}catch (Exception e) {
			// TODO: handle exception
		}
		myForm.setUserDep(userDep);
		myForm.setUserName(userName);
		myForm.setUserType(userType);
		myForm.setFdyQx(fdyQx);
		myForm.setBzrQx(bzrQx);

		// 查询结果
		request.setAttribute("rs", service.getDbsxInfo(myForm));

		// 初始化下拉列表
		service.getInitList(request,myForm,"dbsx");
		// 表头
		request.setAttribute("path", "newsInfo.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("xmdm", myForm.getXmdm());
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		//获取topTr(表头)
		service.getTop(request, myForm);
		request.setAttribute("mklx", myForm.getMklx());
		request.setAttribute("xmList", service.getDbsxXmList(myForm));
		return mapping.findForward("dbsxInfo");
	}

	/**
	 * 显示项目信息修改(首页 友情链接和联系方式维护) author 潇洒的裘
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问系统维护-系统设置-联系方式-修改XMDM:{xmdm},XMMC:{xmmc},XMNR:{xmnr},SFXS:{sfxs}")
	public ActionForward xsxmUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SyszService service = new SyszService();
		XszzCommService xszzComm = new XszzCommService();
		SyszForm myForm = (SyszForm) form;

		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String tabName = "xg_sysz_xsxmb";
		// 非增加操作 查询记录
		if (!"add".equalsIgnoreCase(doType)) {
			this.selectPageDataByOne(request, tabName, tabName, pkValue);
		} else {
			request.setAttribute("xmdm", xszzComm.getXmbh(tabName, "xmdm"));
		}

		if ("save".equalsIgnoreCase(doType)) {
			this.insertOperation(request, tabName);
		}

		if ("modi".equalsIgnoreCase(doType)) {
			this.updateOperation(request, tabName);
		}

		// 操作方式
		request.setAttribute("doType", doType);
		// 初始化下拉列表
		service.getInitList(request,myForm,"xsxm");
		// 路径
		request.setAttribute("path", "xtwh_xsxmwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsxmUpdate");
	}

	/**
	 * 显示项目信息(首页 友情链接和联系方式) author 潇洒的裘
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SystemLog(description="访问系统维护-系统设置-联系方式-增加XMDM:{xmdm},XMMC:{xmmc},XMNR:{xmnr},SFXS:{sfxs},或删除VALUES:{primarykey_}")
	public ActionForward xsxmManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BasicService basicService = new BasicService();
		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();
		String doType = request.getParameter("doType");
		String tabName = "xg_sysz_xsxmb";

		// 批量保存（是否显示）
		if ("save".equalsIgnoreCase(doType)) {
			// 返回操作结果
			request.setAttribute("result", service.updateSfxs(myForm));
		}

		// 删除操作
		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, tabName);
		}

		// 查询
//		if ("query".equalsIgnoreCase(doType)) {————不判断该字段，点击菜单默认执行查询操作--20121225--zhanghui
		
		//2013-1-10 去掉友情链接  qph
			String[] outputColumn = { "pkValue", "xmmc", "xmnr", "xsfs",
					"sfxs", "xssx" };
			this.selectPageDataByPagination(request, form, tabName, tabName,
					outputColumn);
//		}
		
		
		request.setAttribute("topTr",basicService.getTopTr(tabName,new String[]{"编号","项目名称","项目内容","是否显示","显示顺序"}));
		// 初始化列表
		service.getInitList(request,myForm,"xsxm");
		request.setAttribute("path", "xtwh_xsxmwh.do");
		//
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsxmManage");
	}

	// =======================以上made by 潇洒的裘=====================
}