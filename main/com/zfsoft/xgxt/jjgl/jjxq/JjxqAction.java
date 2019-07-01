/**
 * @部门:学工产品事业部
 * @日期：2014-8-19 下午02:24:09 
 */  
package com.zfsoft.xgxt.jjgl.jjxq;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjService;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkService;
import com.zfsoft.xgxt.jjgl.jjzg.JjzgForm;
import com.zfsoft.xgxt.jjgl.jjzg.JjzgService;
import com.zfsoft.xgxt.jjgl.xqwh.XqwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @类功能描述: 家教需求
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-8-19 下午02:24:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JjxqAction extends SuperAction<JjxqForm, JjxqService> {
	
	private static final String url = "xsjj_jjsc.do";

	/**
	 * 
	 * @描述: 家教市场
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-8-19 下午02:31:22
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
	public ActionForward jjsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		JjzgService service = new JjzgService();
		
		//加载学生家教信息
		JjzgForm model = service.getModel(user.getUserName());
		
		if (model == null){
			request.setAttribute("message", "未获得家教资格，无权访问此页面！");
			return mapping.findForward("error");
		}
		
		//家教年级列表
		JjnjService jjnjService = new JjnjService();
		List<HashMap<String,String>> jjnjList = jjnjService.getJjnjList();
		request.setAttribute("jjnjList", jjnjList);
		
		//家教学科列表
		JjxkService jjxkService = new JjxkService();
		List<HashMap<String,String>> jjxkList = jjxkService.getJjxkList();
		request.setAttribute("jjxkList", jjxkList);
		
		request.setAttribute("path", "xsjj_jjsc.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jjsc");
	}
	
	
	/**
	 * 
	 * @描述: 查询家教需求
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-8-19 下午02:36:45
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
	public ActionForward getJjxqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		JjxqForm model = (JjxqForm) form;
		//查询
		List<HashMap<String,String>> resultList = getService().getJjxqList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	

	/**
	 * 
	 * @描述: 我的家教列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-8-20 上午11:25:14
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
	@SystemAuth(url = "xsjj_wdjj.do")
	public ActionForward wdjjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		JjzgService service = new JjzgService();
		
		//加载学生家教信息
		JjzgForm jjzgModel = service.getModel(user.getUserName());
		
		if (jjzgModel == null){
			request.setAttribute("message", "未获得家教资格，无权访问此页面！");
			return mapping.findForward("error");
		}
		
		//家教年级列表
		JjnjService jjnjService = new JjnjService();
		List<HashMap<String,String>> jjnjList = jjnjService.getJjnjList();
		request.setAttribute("jjnjList", jjnjList);
		
		//家教学科列表
		JjxkService jjxkService = new JjxkService();
		List<HashMap<String,String>> jjxkList = jjxkService.getJjxkList();
		request.setAttribute("jjxkList", jjxkList);
	
		request.setAttribute("path", "xsjj_wdjj.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("wdjjList");
	}
	
	
	/**ajax加载我的家教**/
	@SystemAuth(url = url)
	public ActionForward getWdjjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JjxqForm model = (JjxqForm) form;
		User user = getUser(request);
		//查询
		List<HashMap<String,String>> resultList = getService().getPageList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}

	/**
	 * 跳转到学生申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward toXssqPage(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JjxqForm jjxqForm = (JjxqForm) form;
		String xqid = jjxqForm.getXqid();
		Map<String,String> xqwhMap = new XqwhService().getXqwhMap(xqid);
		request.setAttribute("xqwhMap",xqwhMap);

		return mapping.findForward("xsjjsq");
	}

	/**
	 * 
	 * @描述: 保存学生需求申请
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-8-27 下午01:47:38
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
	public ActionForward xssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JjxqForm jjxqForm = (JjxqForm) form;
		User user = getUser(request);

		JjxqService jjxqService = getService();
		//保存前需验证改家教是否已经被申请 根据xqid和jjcz
		boolean isJjczExist = jjxqService.isJjczExist(jjxqForm);
		if (isJjczExist) {
			response.getWriter().print(getJsonMessage("你要进行的家教操作已存在"));
			return null;
		}
		
		boolean result = jjxqService.saveXssq(jjxqForm, user);
		String messageKey = result?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 结束家教
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-8-27 下午03:19:15
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
	public ActionForward jsjj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JjxqForm jjxqForm = (JjxqForm) form;
		String xqid = jjxqForm.getXqid();
		Map<String,String> xqwhMap = new XqwhService().getXqwhMap(xqid);
		request.setAttribute("xqwhMap",xqwhMap);

		return mapping.findForward("jsjj");
	}
	
	
	/**
	 * 
	 * @描述: 结束家教
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-8-27 下午04:08:54
	 * @修改记录: 结束家教需要审核
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
	public ActionForward saveJsjj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JjxqForm jjxqForm = (JjxqForm) form;
		User user = getUser(request);

		JjxqService jjxqService = getService();
		//保存前需根据pjjsqid、jjcz判断是否已经存在相应的家教操作数据
		boolean isJjczExist = jjxqService.isJjczExist(jjxqForm);
		if (isJjczExist) {
			response.getWriter().print(getJsonMessage("你要进行的家教操作已存在"));
			return null;
		}

		boolean result = jjxqService.saveJsjj(jjxqForm, user);
		String messageKey = result?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 查看我的家教申请
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-2 下午04:12:53
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
	public ActionForward cksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JjxqForm jjxqForm = (JjxqForm) form;
		HashMap<String,String> map = getService().getXssqInfo(jjxqForm.getSqid());
		
		String xqid = map.get("xqid");
		
		if(StringUtils.isNotBlank(xqid)){
			HashMap<String , String> xqModelMap = new JjxqService().getModelMap(xqid);
			request.setAttribute("xqModelMap", xgxt.utils.String.StringUtils.formatData(xqModelMap));
		}
		request.setAttribute("jjxx", map);
		
		return mapping.findForward("cksq");
	}

	/**
	 * 家教评价（对家长）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward jjpj(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JjxqForm jjxqForm = (JjxqForm) form;

		HashMap<String , String> pjxxMap = new JjxqService().getPjxxMap(jjxqForm.getXqid());
		pjxxMap.put("xqid",jjxqForm.getXqid());
		request.setAttribute("pjxx", pjxxMap);

		return mapping.findForward("jjpj");
	}

	/**
	 * 保存家教评价（对家长）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward jjpjSave(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String pjid = request.getParameter("pjid");
		String xqid = request.getParameter("xqid");
		String pjzs = request.getParameter("pjzs");
		String py = request.getParameter("py");
		JjxqService jjxqService = getService();
		boolean result = jjxqService.jjpjSave(pjid,xqid, pjzs,py);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 家教时长维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward jjgswh(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JjxqForm jjxqForm = (JjxqForm) form;
		List<HashMap<String,String>> gsxxList = new JjxqService().getJjgsxxList(jjxqForm.getXqid());
		request.setAttribute("gsxxList", gsxxList);
		request.setAttribute("jjbh",jjxqForm.getXqid());

		return mapping.findForward("jjgswh");
	}

	/**
	 * 保存家教工时
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward jjgsSave(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String jjbh = request.getParameter("jjbh");
		String jjny = request.getParameter("jjny");
		String jjgs = request.getParameter("jjgs");
		JjxqService jjxqService = getService();
		//保存前验证同月同编号是否重复
		boolean isJjgsExist = jjxqService.isJjgsExist(jjbh,jjny);
		if (isJjgsExist) {
			response.getWriter().print(getJsonMessage("该编号家教该月工时记录已存在"));
			return null;
		}

		boolean result = jjxqService.jjgsSave(jjbh,jjny, jjgs);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}


	/***************************************************家教操作审核**************************************************/

    /**
     * 跳转到家教审核列表页面.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = "jjgl_jjczsh.do")
    public ActionForward jjczshList(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        request.setAttribute("path","jjgl_jjczsh.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("jjczshList");
    }

    /**
     * 获取家教审核列表JSON数据
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = "jjgl_jjczsh.do")
    public ActionForward getJjczshListData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JjxqForm jjxqForm = (JjxqForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		jjxqForm.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String, String>> resultList = getService().getJjczshList(jjxqForm, user);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    /**
     * 跳转到家教操作审核页面
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = "jjgl_jjczsh.do",rewritable=ReadWrite.WRITEABLE)
    public ActionForward jjczsh(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {

		JjxqForm jjxqForm = (JjxqForm) form;
		Map<String,String> jjxqMap = null;
		//申请信息
		if("1".equals(jjxqForm.getJjcz())){
			//派家教申请信息
			jjxqMap = getService().getJjxqMap(jjxqForm.getSqid());
		}else{
			//退家教关闭家教申请信息
			jjxqMap = getService().getTjjxqMap(jjxqForm.getSqid());
		}

		jjxqForm.setSplc(jjxqMap.get("splc"));
//		jjxqForm.setJjcz(jjxqMap.get("jjcz"));
		jjxqForm.setJjczmc(jjxqMap.get("jjczmc"));
		jjxqForm.setSqly(jjxqMap.get("sqly"));
		jjxqForm.setXqid(jjxqMap.get("xqid"));

		JjzgService jjzgService = new JjzgService();
		if (!StringUtil.isNull(jjxqForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(jjxqForm.getXh());
			request.setAttribute("jbxx", xsjbxx);

			//加载学生家教信息
			JjzgForm jjzgForm = jjzgService.getModel(jjxqForm.getXh());
			request.setAttribute("jjzgForm",jjzgForm);

			//家教经历
			List<HashMap<String, String>> jjjlList = jjzgService.getJJjlList(jjxqForm.getXh());
			request.setAttribute("jjjlList", jjjlList);

		}

        return mapping.findForward("jjczsh");
    }

    /**
     * 保存家教操作审核
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = "jjgl_jjczsh.do",rewritable=ReadWrite.WRITEABLE)
    public ActionForward jjczshSave(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

		JjxqForm jjxqForm = (JjxqForm) form;
		User user = getUser(request);
		// 保存单个审核
		boolean result = getService().jjczshSave(jjxqForm, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
    }

    /**
     * 家教操作审核最后一级的撤销
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = "jjgl_jjczsh.do",rewritable=ReadWrite.WRITEABLE)
    public ActionForward cancelShLast(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

		JjxqForm jjxqForm = (JjxqForm) form;
		// 最后一级撤销
		boolean isSuccess = getService().cancelShLast(jjxqForm);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
    }

	/**
	 * 家教安全协议书页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward jjaqxys(ActionMapping mapping, ActionForm form, HttpServletRequest request,
									  HttpServletResponse response) throws Exception {
		JjxqForm jjxqForm = (JjxqForm) form;
		return mapping.findForward("jjaqxys");
	}

}
