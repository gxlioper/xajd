package xsgzgl.gygl.gybxgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;

public class GybxglAction extends SuperAction {
	private static final String BXSQ = "bxsq";
	private static List<HashMap<String, String>> jbxxList = null;
	
	
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(BXSQ);
	}
	/**
	 * 公寓报修管理（管理员）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	
	@SystemLog(description="访问公寓管理-公寓报修-报修申请管理-{doType}PK:{primarykey_checkVal}")
	public ActionForward gybxglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_gybxgl_gybxgl.do");
		GybxglForm myForm = (GybxglForm) form;
		GybxglService service = new GybxglService();
		String doType = request.getParameter("doType");
		
		// 删除操作
		if("del".equalsIgnoreCase(doType)){
			String message = service.delGybx(myForm) ? "删除成功！" : "删除失败！" ;
			request.setAttribute("message", message);
		}
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		
		// 结果集显示字段
		String[] cn = new String[]{"楼栋","寝室","床位","学号","姓名","报修时间","报修内容","维修时间","紧急程度","报修类别","处理状态","满意程度","维修人员","维修费用"};
		String[] en = new String[]{"pk","ldmc","qsh","cwh","xh","xm","bxsj","bxnr","wxsj","jjcd","bxlbmc","clzt","mycd","wxry","wxfy","subbxnr"};

		if("1103202".equals(Base.xxdm)){
			cn = new String[]{"楼栋","寝室","床位","学号","姓名","报修时间","报修内容","维修时间","紧急程度","报修类别","处理状态","维修人员","维修费用"};
		}

		User user = getUser(request);
        myForm.setYhm(user.getUserName());
		// =============== 执行查询操作 ===========
		rsArrList = service.getGybxglInfoList(myForm,en,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("topTr", service.getToplist(cn));
		
		FormModleCommon.commonRequestSet(request);	//显示title
		// ================= end =====================
		
		//学校个性化
		if("10596".equalsIgnoreCase(Base.xxdm)){
			return new ActionForward("/xsgzgl/gygl/gybxgl/xxgxh/gybxglManage_"+Base.xxdm+".jsp",false);
		}
			return mapping.findForward("gybxglManage");
		
	}
	
	/**
	 *  公寓报修管理 自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	
	public ActionForward gybxglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
		GybxglForm model = (GybxglForm) form;
		if("bxfp".equals(model.getFlag())){
			request.setAttribute("path", "gyglnew_gybxgl_gybxgl_bxfp.do");
		}else{
			request.setAttribute("path", "gyglnew_gybxgl_gybxgl.do");
		}
		GybxglService service = new GybxglService();
		
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);		
		User user = getUser(request);
		
		String[] en = new String[]{"pk","ldmc","qsh","cwh","xh","xm","bxsj","bxnr","wxsj","jjcd","bxlbmc","bxlbzxmc","clzt","mycd"};
		List<HashMap<String,String>> resultList = service.getGybxglInfoExportList(model,en,request);
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	
	/**
	 * 公寓报修管理（学生）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	
	@SystemLog(description="访问公寓管理-公寓报修-报修申请管理-{doType}维护PK:{primarykey_checkVal}")
	public ActionForward gybxglStudent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_gybxgl_gybxgl_stu.do");
		GybxglForm myForm = (GybxglForm) form;
		GybxglService service = new GybxglService();
		User user = getUser(request);// 用户对象
		String doType = request.getParameter("doType");
		
		// 评价操作
		if("pj".equalsIgnoreCase(doType)){
			String message = service.pjUpdate(myForm) ? "评价成功！" : "评价失败！" ;
			request.setAttribute("message", message);
		}
		// 删除操作
		if("del".equalsIgnoreCase(doType)){
			String message = service.delGybx(myForm) ? "删除成功！" : "删除失败！" ;
			request.setAttribute("message", message);
		}
		
		List<String[]> rsArrList = new ArrayList<String[]>();
		
		// 结果集显示字段
		String[] cn = new String[]{"报修时间","报修内容","紧急程度","报修类别","报修类别子项","期望维修时间","实际维修时间","处理状态","满意程度"};
		String[] en = new String[]{"pk","xh","bxsj","bxnr","jjcd","bxlbmc","bxlbzxmc","qwwxsj","wxsj","clzt","mycd","subbxnr"};

		if("1103202".equals(Base.xxdm)){
			cn = new String[]{"报修时间","报修内容","紧急程度","报修类别","报修类别子项","期望维修时间","实际维修时间","处理状态"};
		}

		// =============== 执行查询操作 ===========
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // 公寓辅导员数据范围过滤条件
		myForm.setXh(user.getUserName());
		rsArrList = service.getGybxglSelfList(myForm, en, user, searchTjByGyfdy);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());	//补空行
		request.setAttribute("topTr", service.getToplist(cn));
		FormModleCommon.commonRequestSet(request);	//显示title
		// ================= end =====================

		//学校个性化
		if("10596".equalsIgnoreCase(Base.xxdm)){
			return new ActionForward("/xsgzgl/gygl/gybxgl/xxgxh/gybxglStudent_"+Base.xxdm+".jsp",false);
		}
		
		return mapping.findForward("gybxglStudent");
	}
	/**
	 * 
	 * @描述:点击评价按钮弹出窗口样式修改
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-23 下午12:25:07
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
	
	@SystemLog(description="访问公寓管理-公寓报修-报修申请管理-评价PK:{idList}")
	public ActionForward gybxglStudentpj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GybxglForm myForm = (GybxglForm) form;
		GybxglService service = new GybxglService();
		String doType = request.getParameter("doType");
		String idList = request.getParameter("idList");
		idList.substring(0, idList.length()-1);
		request.setAttribute("idList", idList);
		List<HashMap<String,String>> mydList = new OptionUtil().getOptions(OptionUtil.GYBX_MYD); // 满意度
		request.setAttribute("mydList", mydList);
		// 评价操作
		if("pj".equalsIgnoreCase(doType)){
			myForm.setPrimarykey_checkVal(idList.split(","));
			String message = service.pjUpdate(myForm) ? "评价成功！" : "评价失败！" ;
			request.setAttribute("message", message);
		}

		return mapping.findForward("gybxglPj");
	}
	
	
	@SystemLog(description="访问公寓管理-公寓报修-报修申请管理-增加XH:{xh},JJCD:{jjcd},LXDH:{lxdh},BXNR:{bxnr}")
	public ActionForward gybxglAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);// 用户对象
		String doType = request.getParameter("doType");
		GybxglForm myForm = (GybxglForm) form;
		// 依据是否有主键可以判断是增加还是修改
		String pk = myForm.getPk();
		if(StringUtils.isNotNull(pk)){
			request.setAttribute("type", "update");
		}
		
		GybxglService service = new GybxglService();
		String xh = "stu".equals(user.getUserType()) ? user.getUserName() : myForm.getXh();
		if (!StringUtil.isNull(xh) || !StringUtil.isNull(pk)){
			HashMap<String,String> xsjbxx = service.viewXsxx(pk, xh);
			request.setAttribute("jbxx", xsjbxx);
			myForm.setXh(xh);
		}
		
		if(SAVE.equalsIgnoreCase(doType)){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			String messageKey = "";
    		boolean result = false;
    		if(StringUtils.isNull(pk)){
    			result = service.gybxglAdd(myForm);
    		}else if(StringUtils.isNotNull(pk)){
    			result = service.gybxglModi(myForm);
    		}
    		messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		List<HashMap<String, String>> bxlbList = service.getBxlbList();
		request.setAttribute("bxlbList",bxlbList);
		request.setAttribute("bxlbzxList", new ArrayList<HashMap<String,String>>());
		request.setAttribute("path", "gyglnew_gybxgl.do?method=gybxglAdd");
		request.setAttribute("jbxxList", jbxxList);
		this.saveToken(request);
		return mapping.findForward("gybxglAdd");
	}
	
	
	@SystemLog(description="访问公寓管理-公寓报修-报修申请管理-修改PK:{pk},JJCD:{jjcd},LXDH:{lxdh},BXNR:{bxnr}")
	public ActionForward gybxglUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String doType = request.getParameter("doType");
		GybxglForm myForm = (GybxglForm) form;
		User user = getUser(request);
		
		GybxglService service = new GybxglService();
		
		String xh = myForm.getXh();
		String pk = myForm.getPk();
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		
		// 保存操作
		if("save".equalsIgnoreCase(doType)){
			String message = service.gybxglUpdate(myForm) ? "保存成功！" : "保存失败！";
			request.setAttribute("message", message);
		}
	
		request.setAttribute("rs",service.viewXsxx(pk, xh));
		
		return mapping.findForward("gybxglUpdate");
	}
	
	
	public ActionForward gybxglView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String doType = request.getParameter("doType");
		GybxglForm myForm = (GybxglForm) form;
		User user = getUser(request);
		
		GybxglService service = new GybxglService();
		
		String xh = myForm.getXh();
		String pk = myForm.getPk();
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		
		// 保存操作
		if("save".equalsIgnoreCase(doType)){
			String message = service.gybxglUpdate(myForm) ? "修改成功！" : "修改失败！";
			request.setAttribute("message", message);
		}
	
		request.setAttribute("rs",StringUtils.formatData(service.viewXsxx(pk, xh)));
		
		return mapping.findForward("gybxglView");
	}
	
	
	public ActionForward viewXsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);// 用户对象
		String xh = user.getUserName();
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		GybxglService service = new GybxglService();

		Map<String, String> map = service.viewXsxx(xh);
		String json = JSONObject.fromObject(map).toString();	
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/** 
	 * @描述:菜单跳转到指定地址（温州大学定制）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-31 下午03:51:58
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
	
	public ActionForward gybxglMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("gybxglMenu_"+Base.xxdm);
	}
	/**
	 * 
	 * @描述:公寓报修类别子项
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-5 下午01:55:02
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
	
	public ActionForward getBxlbzxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GybxglService service = new GybxglService();
		String bxlb = request.getParameter("bxlb");
		List<HashMap<String,String>> jllbList = service.getBxlbzxList(bxlb);
		String json = JSONArray.fromCollection(jllbList).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
		
	}
	
	@SystemLog(description="访问公寓管理-公寓报修-报修申请分配-删除PK:{primarykey_checkVal}")
	public ActionForward gybxglManageBxfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setAttribute("path", "gyglnew_gybxgl_gybxgl_bxfp.do");
		GybxglForm myForm = (GybxglForm) form;
		GybxglService service = new GybxglService();
		String flag = (String)request.getAttribute("flag");
		String doType = request.getParameter("doType");
		
		// 删除操作
		if("del".equalsIgnoreCase(doType)){
			String message = service.delGybx(myForm) ? "删除成功！" : "删除失败！" ;
			request.setAttribute("message", message);
		}
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		
		// 结果集显示字段
		String[] cn = new String[]{"楼栋","寝室","床位","学号","姓名","分配状态","分配部门","报修时间","维修时间","紧急程度","报修类别"};
		String[] en = new String[]{"pk","ldmc","qsh","cwh","xh","xm","fpztmc","fpbmmc","bxsj","wxsj","jjcd","bxlbmc","mycd","fpzt","clzt"};
        User user = getUser(request);
        myForm.setYhm(user.getUserName());
        myForm.setFlag("bxfp");
		// =============== 执行查询操作 ===========
		rsArrList = service.getGybxglInfoList(myForm,en,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("topTr", service.getToplist(cn));
		
		FormModleCommon.commonRequestSet(request);	//显示title
		// ================= end =====================
		
		//学校个性化
		if("10596".equalsIgnoreCase(Base.xxdm)){
			return new ActionForward("/xsgzgl/gygl/gybxgl/xxgxh/gybxglManage_"+Base.xxdm+".jsp",false);
		}
		return mapping.findForward("gybxglManagebxfp");
	}
	
	@SystemLog(description="访问公寓管理-公寓报修-报修分配管理-修改PK:{pk},JJCD:{jjcd},LXDH:{lxdh},BXNR:{bxnr}")
	public ActionForward gybxglfpUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String doType = request.getParameter("doType");
		GybxglForm myForm = (GybxglForm) form;
		User user = getUser(request);
		
		GybxglService service = new GybxglService();
		
		String xh = myForm.getXh();
		String pk = myForm.getPk();
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		
		// 保存操作
		if("save".equalsIgnoreCase(doType)){
			String message = service.gybxglfpUpdate(myForm) ? "保存成功！" : "保存失败！";
			request.setAttribute("message", message);
		}
		if("12688".equals(Base.xxdm)){
			request.setAttribute("xqlist", service.getfpxqList());
		}
		request.setAttribute("rs",service.viewXsxx(pk, xh));
		request.setAttribute("bmlist", service.getfpbmList());
		
		return mapping.findForward("gybxglfpUpdate");
	}
	
	public ActionForward gybxglfpView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String doType = request.getParameter("doType");
		GybxglForm myForm = (GybxglForm) form;
		User user = getUser(request);
		
		GybxglService service = new GybxglService();
		
		String xh = myForm.getXh();
		String pk = myForm.getPk();
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		
		// 保存操作
		if("save".equalsIgnoreCase(doType)){
			String message = service.gybxglUpdate(myForm) ? "修改成功！" : "修改失败！";
			request.setAttribute("message", message);
		}
	
		request.setAttribute("rs",StringUtils.formatData(service.viewfpXsxx(pk, xh)));
		
		return mapping.findForward("gybxglfpView");
	}
}
