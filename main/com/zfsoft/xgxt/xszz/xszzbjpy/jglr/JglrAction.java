package com.zfsoft.xgxt.xszz.xszzbjpy.jglr;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xszz.xszzbjpy.jcsz.JcszForm;
import com.zfsoft.xgxt.xszz.xszzbjpy.jcsz.JcszService;

public class JglrAction extends SuperAction {
	
	private static final String url = "xszz_xszzbjpy_jglr.do";
	
	/**
	 * 查询资助班级评议结果录入
	 */
	@SystemAuth(url = url)
	public ActionForward jglrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JglrForm model = (JglrForm) form;
		JglrService service = new JglrService();
		User user = getUser(request);
		String xh = user.getUserName();
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
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		if(jcszModel == null){
			String msg = "基本设置未初始化，请联系管理员！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("jcszModel", jcszModel);
		
		HashMap<String,String> bjpyxzcyMap = service.queryBjpyxzcy(xh);
		if(bjpyxzcyMap.get("tjzt") == null){
			String msg = "该模块只允许班级评议小组成员访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else if("0".equals(bjpyxzcyMap.get("tjzt"))){
			String msg = "班级评议小组尚未提交，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		List<HashMap<String,String>> pyjgList = new OptionUtil().getOptions(OptionUtil.PYJG);
		request.setAttribute("pyjgList", pyjgList);
		
		HashMap<String,String> xsxxMap = service.queryXsxx(xh);
		request.setAttribute("xsxxMap", xsxxMap);
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		if(!StringUtil.isNull(Base.currXq)){
			searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		}
		request.setAttribute("searchTj", searchModel);
		
		String path = "xszz_xszzbjpy_jglr.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jglrManage");
	}

	/**
	 * 保存资助班级评议结果录入
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="学生资助-资助班级评议管理-班级评议结果录入-保存VALUES:{values}")
	public ActionForward bcJglr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JglrForm model = (JglrForm) form;
		JglrService service = new JglrService();
		User user = getUser(request);
		boolean result = service.bcJglr(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

}
