/**
 * @部门:学工产品事业部
 * @日期：2018年5月22日 下午5:59:02 
 */  
package xsgzgl.gyjc.jcsd;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018年5月22日 下午5:59:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CcrcAction extends SuperAction<CcrcForm, CcrcService> {
	private static final String url = "xg_gyjc_ccrcsz.do";
	private CcrcService service = new  CcrcService();
	private static final String FWQXTSY = "此菜单只供校级用户和院级用户使用！";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private String[] lddms ;
	private String[] qshs ;
	
	public ActionForward getCcrcList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		User user = getUser(request);
		if(!"xx".equals(user.getUserStatus()) && ! "xy".equals(user.getUserStatus())){
			request.setAttribute("message",FWQXTSY);
			return mapping.findForward("error");
		}
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		//将用户身份置回页面
		request.setAttribute("userType", user.getUserStatus());
		request.setAttribute("xydm",user.getUserDep());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("search");
	}
	
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		String currXn = Base.currXn;
		String currXq = Base.currXq;

		model.setXn(currXn);
		model.setXqdm(currXq);
		request.setAttribute("xn", currXn);
		request.setAttribute("xqmc", Base.getXqmcForXqdm(currXq));
		request.setAttribute("xq", currXq);
		/**
		 * 日期维护最小值
		 */
		request.setAttribute("minDate", GetTime.getTimeByFormat(DATE_FORMAT));
		return mapping.findForward("add");
	}
	
	
	public ActionForward saveCcRc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		/**
		 * 判断日期是否和其他日程有重叠，重叠不允许保存
		 */
		boolean rs = service.checkIfRqIntersection(model.getJcrq());
		if(!rs){
			response.getWriter().print(getJsonMessageByKey(MessageKey.XG_GYJC_DEL_RQ_REPEAT_TS));
			return null;
		}
		CcrcService service = new CcrcService();
		 rs = service.saveCcRc(model);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		User user = getUser(request);
		CcrcForm myForm = service.getModel(model);
		if(myForm != null){
			BeanUtils.copyProperties(model, myForm);
		}else{
			request.setAttribute("message", "当前选择修改的日程已被其他用户删除！");
			return mapping.findForward("error");
		}
		request.setAttribute("xqmc", Base.getXqmcForXqdm(myForm.getXqdm()));
		request.setAttribute("xn", myForm.getXn());
		return mapping.findForward("update");
	}
	
	public ActionForward saveCcrcUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		User user = getUser(request);
		boolean rs = service.checkIfRqIntersection(model.getJcrq());
		if(!rs){
			response.getWriter().print(getJsonMessageByKey(MessageKey.XG_GYJC_DEL_RQ_REPEAT_TS));//日期重复
			return null;
		}
		rs = service.runUpdate(model);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	
	public ActionForward delCcrc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {//判断要删除的日程是否存在数据
		
		String rcid = request.getParameter("rcid");
		//检查该日程是否已经安排抽查任务
		boolean rs = service.checkIfExistTj(rcid);
		if(!rs){
			response.getWriter().print(getJsonMessageByKey(MessageKey.XG_GYJC_DEL_TJ_TS));
			return null;
		}
//	
		CcrcService tranService = new CcrcService();
		rs = tranService.delCcrcTran(rcid,true); 
		String messageKey = rs ? MessageKey.SYS_DEL_SUCCESS:MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	public ActionForward fenpei(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xg_gyjc_ccry.do?method=fenpei");
		//将用户身份置回页面
		request.setAttribute("userType", user.getUserStatus());
		request.setAttribute("xydm",user.getUserDep());
		request.setAttribute("ccid", model.getCcid());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fenpei");

	}
	
	
	
	public ActionForward fpPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getRyfpList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xg_gyjc_ccry.do?method=fpPerson");
		lddms =model.getLddms().split(",");
		qshs = model.getQshs().split(",");
		//将用户身份,学院代码，检查类型置回页面
		request.setAttribute("userType", user.getUserStatus());
		request.setAttribute("ccid", model.getCcid());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("searchRyfp");
	}
	
	
	
	public ActionForward saveRyFp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm)form;
		CcrcService jcsdService = TransactionControlProxy.newProxy(new CcrcService());
		boolean rs = jcsdService.saveRyFp(model,lddms,qshs);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	public ActionForward insertQs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm)form;
		String[] lddm = model.getLds();
		String[] qsh = model.getQss();
		CcrcService jcsdService = TransactionControlProxy.newProxy(new CcrcService());
		boolean rs = jcsdService.saveRyFp(model,lddm,qsh);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	public ActionForward cancelRyfp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm)form;
		User user = getUser(request);
		CcrcService jcsdService = TransactionControlProxy.newProxy(new CcrcService());
		boolean rs = jcsdService.cancelRyfp(model, user.getUserStatus());
		String messageKey = rs ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_OPERATE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	
	public ActionForward updateRyFp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm)form;
		CcrcService jcsdService = TransactionControlProxy.newProxy(new CcrcService());
		boolean rs = jcsdService.updateRyFp(model);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	public void todayCc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.gettodayCcList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
		}
	

	}
	
	public ActionForward updateCcrList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getRyfpList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xg_gyjc_ccry.do?method=updateRy");
		//将用户身份,学院代码，检查类型置回页面
		request.setAttribute("userType", user.getUserStatus());
		request.setAttribute("ccid", model.getCcid());
		request.setAttribute("xgzgh", model.getXgzgh());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("updateRyfp");
	}
	
	public ActionForward updateQsList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
				List<HashMap<String, String>> resultList = service.getList(model, user);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			if("yfp".equalsIgnoreCase(model.getType()))
			{
				List<HashMap<String, String>> resultList = service.getqsfpList(model, user);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			
			
		
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xg_gyjc_ccry.do?method=updateQs");
		//将用户身份置回页面
		request.setAttribute("userType", user.getUserStatus());
		request.setAttribute("xydm",user.getUserDep());
		request.setAttribute("ccid", model.getCcid());
		request.setAttribute("zgh", model.getZgh());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("updateQs");

	}
	
	
	

}
