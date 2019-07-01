/**
 * @部门:学工产品事业部
 * @日期：2015-6-19 上午09:32:32 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl.jg;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.extend.service.ExtendService;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.rcsw.zdzm.jggl.ZdzmJgService;
import com.zfsoft.xgxt.rcsw.zdzm.sh.ZdzmShForm;
import com.zfsoft.xgxt.rcsw.zdzm.sh.ZdzmShService;
import com.zfsoft.xgxt.xsxx.kzxxgl.sh.XsxxKzxxglShForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-19 上午09:32:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxxKzxxglJgAction extends SuperAction<XsxxKzxxglJgForm, XsxxKzxxglJgService> {
	
	private static final String url = "xsxx_kzxxgl_jggl.do";

	private XsxxKzxxglJgService service = new XsxxKzxxglJgService();
	
	private ExtendService extendService = new ExtendService();
	
	public static final String DATA_EXTEND_MODULE = "XSXX";
	/**
	 * 
	 * @描述:列表
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-4 下午04:44:38
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
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxKzxxglJgForm model  = (XsxxKzxxglJgForm) form;
		User user = getUser(request);
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		if (QUERY.equalsIgnoreCase(model.getActionType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xsxx_kzxxgl_jggl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jgManage");
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-19 下午05:47:33
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
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxKzxxglJgForm model  = (XsxxKzxxglJgForm) form;
		XsxxKzxxglJgForm dataModel = service.getModel(model.getJgid());
		if(null != dataModel){
			BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
		}
		model.setExendDateModuleId(DATA_EXTEND_MODULE);
		return mapping.findForward("ck");
	} 
	
}
