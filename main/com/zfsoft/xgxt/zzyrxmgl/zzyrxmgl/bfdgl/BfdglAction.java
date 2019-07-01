package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.bfdgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.cssz.JcsszForm;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.cssz.JcszService;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.fdgl.FdglService;

/** 
 * @功能描述：资助育人项目管理-被辅导管理action
 * @author：Lu.Yao 【1271】
 * @date：2017-10-16 下午03:55:26 
 */
public class BfdglAction extends SuperAction {
	
	private static final String url = "zzyrxmgl_bfdgl.do";
	private BfdglService service = new BfdglService();

	/** 
	 * @description：首页
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午11:17:41 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward bfdglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		
		User user = getUser(request);
		
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
			
			resultList = service.getPageList(model, user);
			//查询
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "zzyrxmgl_bfdgl.do";
		request.setAttribute("path", path);
//		FdglService fdglService = new FdglService();
//		boolean sfsq = fdglService.sfksq();
//		request.setAttribute("sfsq", sfsq == true ? "1": "0");
		JcszService jcszService = new JcszService();
		JcsszForm jcszModel = jcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bfdglManage");
	}
	
	/** 
	 * @description：增加
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午11:17:47 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问就业管理-就业管理-毕业去向-增加")
	public ActionForward addBfdgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		List<HashMap<String, String>> xyList = Base.getXyList();
		request.setAttribute("xyList", xyList);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			model.setFdfbid(StringUtils.getGuid());
			boolean result = service.insertFdgl(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		if (!StringUtil.isNull(user.getUserName())&&"stu".equalsIgnoreCase(user.getUserStatus())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(user.getUserName());
			request.setAttribute("jbxx", xsjbxx);
		}
		String path = "zzyrxmglbfdgl.do?method=addBfdgl";
		request.setAttribute("path", path);
		return mapping.findForward("addBfdgl");
	}
	
	
	/** 
	 * @description：修改
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午11:17:52 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问就业管理-就业管理-毕业去向-修改XH:{xh}")
	public ActionForward updateBfdgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		List<HashMap<String, String>> xyList = Base.getXyList();
		request.setAttribute("xyList", xyList);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.updateFdgl(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		if (!StringUtil.isNull(user.getUserName())&&"stu".equalsIgnoreCase(user.getUserStatus())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(user.getUserName());
			request.setAttribute("jbxx", xsjbxx);
			HashMap<String, String> updateForm = service.getModelMap(model);
			BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		}
		request.setAttribute("rs",StringUtils.formatData(model));
		return mapping.findForward("updateBfdgl");
	}
	
	/** 
	 * @description：查询学院list
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午11:17:59 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward kfxydmCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		FdglService fservice = new FdglService();
		List<HashMap<String, String>> resultList = fservice.getKfxydmByid(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/** 
	 * @description：删除
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午11:18:08 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问就业管理-就业管理-毕业去向-删除VALUES:{values}")
	public ActionForward delBfdgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			boolean canDel = service.checkCandel(values);//有人辅导则不能删除
			if(canDel){
				int num = service.runDelete(values);
				boolean result = num > 0;
				String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));				
			}else{
				response.getWriter().print(getJsonMessage("存在已有人辅导的记录，请重新选择！"));
			}
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/** 
	 * @description：查看
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午11:18:16 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward viewBfdgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		if (!StringUtil.isNull(user.getUserName())&&"stu".equalsIgnoreCase(user.getUserStatus())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(user.getUserName());
			request.setAttribute("jbxx", xsjbxx);
		}
		FdglService fservice = new FdglService();
		List<HashMap<String, String>> xyList = fservice.getKfxydmByid(model);
		request.setAttribute("xyList", xyList);
		HashMap<String,String> rs = service.getModelMap(model);
		request.setAttribute("rs", StringUtils.formatData(rs));
		return mapping.findForward("viewBfdgl");
	}
	
	/** 
	 * @description：填写记录
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午11:18:23 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问就业管理-就业管理-毕业去向-修改XH:{xh}")
	public ActionForward addBfdgljl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		FdglService fservice = new FdglService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = fservice.addFdjl(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		List<HashMap<String,String>> fdjlList = fservice.getFdjlList(model);
		HashMap<String, String> updateForm = fservice.getModelMap2(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs",StringUtils.formatData(model));
		request.setAttribute("fdjlList", fdjlList);
		request.setAttribute("size", fdjlList.size());
		return mapping.findForward("addBfdgljl");
	}
	
	/** 
	 * @description：删除辅导记录
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午11:18:34 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问就业管理-就业管理-毕业去向-删除VALUES:{values}")
	public ActionForward delFdjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		FdglService fservice = new FdglService();
		if (!StringUtil.isNull(model.getId())) {
			int num = fservice.deleteFdjl(model);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/** 
	 * @description：填写评价
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午11:18:43 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问就业管理-就业管理-毕业去向-修改XH:{xh}")
	public ActionForward addBfdglpj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		FdglService fservice = new FdglService();
		if (SAVE.equalsIgnoreCase(model.getType())) {
			//boolean canPj = service.checkFdjls(model);//辅导记录2条以上才可评价
			//if(canPj){
				boolean result = service.addFdpj(model);//保存评价
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));				
			//}else{
			//	response.getWriter().print(getJsonMessage("辅导记录填写2次及2次以上方可评价！"));
			//}
			return null;
		}
		HashMap<String, String> updateForm = fservice.getModelMap2(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs",StringUtils.formatData(model));
		return mapping.findForward("addBfdglpj");
	}
	
	/** 
	 * @description：判断能否评价
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-21 下午04:19:51 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward checkCanpj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		boolean canPj = service.checkFdjls(model);//辅导记录2条以上才可评价
		String messageKey = canPj ? "true" : "false";
		response.getWriter().print(getJsonMessage(messageKey));
		return null;
	}
	
}
