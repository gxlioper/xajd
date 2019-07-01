package com.zfsoft.xgxt.xszz.xszzbjpy.xzsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xszz.xszzbjpy.jcsz.JcszForm;
import com.zfsoft.xgxt.xszz.xszzbjpy.jcsz.JcszService;

public class XzszAction extends SuperAction {
	
	private static final String url = "xszz_xszzbjpy_xzsz.do";
	
	/**
	 * 查询资助班级评议小组设置
	 */
	@SystemAuth(url = url)
	public ActionForward xzszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XzszForm model = (XzszForm) form;
		XzszService service = new XzszService();
		User user = getUser(request);
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
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		
		String path = "xszz_xszzbjpy_xzsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xzszManage");
	}

	/**
	 * 增加资助班级评议小组设置
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addXzsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XzszForm model = (XzszForm) form;
		XzszService service = new XzszService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getAddXzszList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("rs", model);
		
		String path = "xszz_xszzbjpy_addxzsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("addXzsz");
	}
	
	/**
	 * 保存增加资助班级评议小组设置
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="学生资助-资助班级评议管理-班级评议小组设置-增加人员VALUES:{values}")
	public ActionForward addXzszBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XzszService service = new XzszService();
		String values = request.getParameter("values");
		String bjdm = request.getParameter("bjdm");
		User user = getUser(request);
		boolean result = service.addXzszBc(values.split(","), bjdm, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 修改资助班级评议小组设置
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateXzsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XzszForm model = (XzszForm) form;
		XzszService service = new XzszService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getUpdateXzszList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("rs", model);
		
		String path = "xszz_xszzbjpy_updatexzsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("updateXzsz");
	}
	
	/**
	 * 删除资助班级评议小组设置
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="学生资助-资助班级评议管理-班级评议小组设置-删除人员VALUES:{values}")
	public ActionForward xzszDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XzszService service = new XzszService();
		String values = request.getParameter("values");
		String bjdm = request.getParameter("bjdm");
		if (!StringUtil.isNull(values)) {
			int num = service.xzszDel(values.split(","));
			boolean result = num > 0;
			if(result){
				result = service.sftjNotAll(bjdm);
			}
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 保存设置学生代表
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="学生资助-资助班级评议管理-班级评议小组设置-保存学生代表VALUES:{values}")
	public ActionForward xsdbBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XzszService service = new XzszService();
		String values = request.getParameter("values");
		String bjdm = request.getParameter("bjdm");
		boolean result = service.xsdbBc(values.split(","), bjdm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 提交资助班级评议小组设置
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="学生资助-资助班级评议管理-班级评议小组设置-提交BJDM:{bjdm}")
	public ActionForward submitXzsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XzszForm model = (XzszForm) form;
		User user = getUser(request);
		XzszService service = new XzszService();
		String bjrs = request.getParameter("bjrs");
		int xzrs = Integer.parseInt(request.getParameter("xzrs"));
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		String xzrsxx = jcszModel.getXzrsxx();
		String xzrssfbxwjs = jcszModel.getXzrssfbxwjs();
		int xzrsTemp = (int)Math.round(Double.parseDouble(bjrs) * Double.parseDouble(xzrsxx) / 100);
		if("1".equals(xzrssfbxwjs) && xzrsTemp % 2 == 0){// 小组下限人数是否必须为奇数
			xzrsTemp = xzrsTemp + 1;
		}
		if(xzrs >= xzrsTemp){ // 检测小组人数下限
			if("1".equals(xzrssfbxwjs) && xzrs % 2 == 0){// 小组人数是否必须为奇数
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_BJPYXZSZ_XZRSXX_XSRS_FAIL));
				return null;
			}
			boolean result = service.sftjYesAll(model.getBjdm(), user);
			String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			String message = MessageUtil.getText(MessageKey.XSZZ_BJPYXZSZ_XZRSXX_FAIL, xzrsTemp);
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
	}

}
