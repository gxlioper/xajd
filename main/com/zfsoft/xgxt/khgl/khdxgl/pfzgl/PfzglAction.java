/**
 * @部门:学工产品事业部
 * @日期：2015-8-10 上午11:36:27 
 */
package com.zfsoft.xgxt.khgl.khdxgl.pfzgl;

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
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.khgl.khdxgl.khdxgl.KhdxglService;

/**
 * @系统名称: 工作管理系统
 * @模块名称: 评分组管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-10 上午11:36:27
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class PfzglAction extends SuperAction<PfzglForm, PfzglService> {
	private PfzglService service = new PfzglService();
	private KhdxglService khdxService = new KhdxglService();

	private static final String url = "khgl_khdxgl_pfzgl.do";
	
	/**
	 * 
	 * @描述:查询评分组列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午01:54:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward getPfzglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PfzglForm model = (PfzglForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "khgl_khdxgl_pfzgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getPfzglList");
	}

	/**
	 * 
	 * @描述:评分组增加
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午05:27:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addPfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("khlxList", new OptionUtil().getOptions("khlx"));
		request.setAttribute("khdxList", khdxService.getKhdxList());
		return mapping.findForward("addPfz");
	}

	/**
	 * 
	 * @描述:修改评分组
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午01:55:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updatePfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PfzglForm myForm = (PfzglForm) form;
		String yfpcy = request.getParameter("yfpcy");
		
		PfzglForm PfzglForm = service.getModel(myForm);
		if (null != PfzglForm) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(PfzglForm));
			request.setAttribute("PfzglForm", PfzglForm);
		}
		//已分配评分成员或已被占用则只允许修改评分组名称
		if("1".equals(yfpcy)||service.isUsed(myForm.getPfzid())){
			request.setAttribute("sfkxg", "false");
		}
		request.setAttribute("khlxList", new OptionUtil().getOptions("khlx"));
		request.setAttribute("khdxList", khdxService.getKhdxList());
		return mapping.findForward("updatePfz");
	}

	/**
	 * 
	 * @描述:查看评分组
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午01:55:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward viewPfzgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PfzglForm myForm = (PfzglForm) form;
		HashMap<String, String> sbjgMap = service.getPfzgl(myForm);
		request.setAttribute("rs", StringUtils.formatData(sbjgMap));
		request.setAttribute("path", "xmsbPfzgl.do?method=viewPfzgl");
		return mapping.findForward("viewPfzgl");
	}

	/**
	 * 
	 * @描述:评分组保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午05:28:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问考核管理-考核对象管理-自定义评分组管理-增加或修改保存PFZMC:{pfzmc},PFLX:{pflx},KHDXID:{khdxid},PFZID:{pfzid}")
	public ActionForward savePfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PfzglForm myForm = (PfzglForm) form;
		
		if (service.isHave(myForm)) {
			String message = MessageUtil
					.getText(MessageKey.KHGL_KHDXGL_PFDX_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.editPfz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:删除评分组
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午04:08:06
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问考核管理-考核对象管理-自定义评分组管理-删除VALUES:{values}")
	public ActionForward delPfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//判断评分组是否被使用
			if (service.isUsed(values)) {
				String message = MessageUtil.getText(MessageKey.KHGL_KHDXGL_PFZ_USED);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			String message = num>0 ? MessageUtil.getText(
					MessageKey.SYS_DEL_SUCCESS) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @描述:考核对象选择列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午04:43:08
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
	@SystemAuth(url = url)
	public ActionForward showKhdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzglForm myForm = (PfzglForm) form;
		String forWardUrl=null;
		String path = null;
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getKhdxList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "khgl_khdxgl_pfzgl.do");
		FormModleCommon.commonRequestSet(request);
		if("1".equals(myForm.getKhlx())){ //被考核对象为学生
			path="khglKhdxgl.do?method=viewKhdxOfStuList";//设置路径，只是用于高级查询
			forWardUrl="showStu";
		}else{
			path="khglKhdxgl.do?method=viewKhdxOfTeaList";//设置路径，只是用于高级查询
			forWardUrl="showTea";
		}
		
		request.setAttribute("path", path);
		request.setAttribute("pfzid", myForm.getPfzid());
		request.setAttribute("khdxid", myForm.getKhdxid());
		request.setAttribute("pflx", myForm.getPflx());
		request.setAttribute("khlx", myForm.getKhlx());
		request.setAttribute("xxdm", Base.xxdm);
	
		return mapping.findForward(forWardUrl);
	}
	
	@SystemAuth(url = url)
	public ActionForward showPfcy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzglForm myForm = (PfzglForm) form;
		String forWardUrl=null;
		String path = null;
		String bjfp=request.getParameter("bjfp");//是否按班级分配参评学生
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList=null;
			if(StringUtils.isNotNull(bjfp)&&"y".equals(bjfp)){
				resultList=service.getPfcybjList(myForm);
			}else{
				// 生成高级查询对象
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				myForm.setSearchModel(searchModel);
				User user = getUser(request);
				// 查询
				resultList = service.getPfcyList(myForm, user);
			}
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		if("1".equals(myForm.getPflx())){
			path = "khglPfzgl.do?method=showPfcyStu";//考核对象为学生
			forWardUrl="showPfcyStu";
			if(StringUtils.isNotNull(bjfp)&&"y".equals(bjfp)){
				forWardUrl="showPfcyStuClass";
				request.setAttribute("xm", request.getParameter("xm"));
				request.setAttribute("bmmc", request.getParameter("bmmc"));
			}
		}else{
			path = "khglPfzgl.do?method=showPfcyTea";//考核对象为教师
			forWardUrl="showPfcyTea";
		}
		request.setAttribute("pfzid", myForm.getPfzid());
		request.setAttribute("khdxid", myForm.getKhdxid());
		request.setAttribute("pflx", myForm.getPflx());
		request.setAttribute("xh", myForm.getXh());
		request.setAttribute("zgh", myForm.getZgh());
		request.setAttribute("khlx", myForm.getKhlx());
		request.setAttribute("pflx", myForm.getPflx());
		request.setAttribute("sfqx", request.getParameter("sfqx"));
		request.setAttribute("khdxrs", request.getParameter("khdxrs"));
		request.setAttribute("path", path);
		return mapping.findForward(forWardUrl);
	}
	
	@SystemAuth(url = url)
	public ActionForward viewPfcy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzglForm myForm = (PfzglForm) form;
		String path = null;
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getViewPfcyList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		if("1".equals(myForm.getPflx())){
			//用于高级查询
			path = "khglPfzgl.do?method=showPfcyStu";//考核对象为学生
		}else{
			path = "khglPfzgl.do?method=showPfcyTea";//考核对象为教师
		}
		request.setAttribute("pfzid", myForm.getPfzid());
		request.setAttribute("pflx", myForm.getPflx());
		request.setAttribute("xh", myForm.getXh());
		request.setAttribute("zgh", myForm.getZgh());
		request.setAttribute("khlx", myForm.getKhlx());
		request.setAttribute("pflx", myForm.getPflx());
		request.setAttribute("path", path);
		return mapping.findForward("viewPfcy");
	}
	/**
	 * 
	 * @描述:评分成员保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 上午08:55:42
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward savePfcy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzglForm model = (PfzglForm) form;
		User user = getUser(request);
		// 生成高级查询对象
		String bjfp=request.getParameter("bjfp");//是否按班级分配参评学生
		boolean result=true;
		String value = request.getParameter("values");
		String khdxr = request.getParameter("khdxrs");
		if(StringUtils.isNotNull(bjfp)&&"y".equals(bjfp)){
			result=service.savePfcybj(model,value,khdxr,user);
		}else{
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			result=service.savePfcy(model,value,khdxr,user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @描述:评分组分配保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 上午08:55:42
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward savePfzQxPf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzglForm model = (PfzglForm) form;
		boolean result=true;
		String value = request.getParameter("values");
		result=service.savePfzQxPf(model,value);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * @描述：评分成员导入
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月1日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward pfcydr(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzglForm myForm = (PfzglForm) form;
		request.setAttribute("pfzid", myForm.getPfzid());
		request.setAttribute("zgh", myForm.getZgh());
		request.setAttribute("xm", request.getParameter("xm"));
		return mapping.findForward("pfcydr");
	}
	
	/**
	 * @描述：评分成员导入保存
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月1日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward pfcydrSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PfzglForm model = (PfzglForm) form;
		User user = getUser(request);
		model.setPflx("1");
		boolean result = service.pfcydrSave(model, user);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonResult(messageKey, result));
		return null;
	}
	
	
}
