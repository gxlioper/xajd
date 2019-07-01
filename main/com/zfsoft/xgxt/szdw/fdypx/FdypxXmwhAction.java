/**
 * @部门:学工产品事业部
 * @日期：2013-7-5 下午02:35:08 
 */  
package com.zfsoft.xgxt.szdw.fdypx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.szdw.fdyzyhfz.ywxsypx.YwxsypxService;
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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:辅导员培训项目维护
 * @作者： zhangjw
 * @时间： 2013-7-5 下午02:35:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdypxXmwhAction extends SuperAction{
	
	private static final String url = "szdw_fdypxxmwh.do?method=fdypxxmList";
	private YwxsypxService ywxsypxService = new YwxsypxService();

	/**
	 * @描述:辅导员培训项目列表
	 * @作者：zhangjw
	 * @日期：2013-7-24 上午9:54:51
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
	public ActionForward fdypxxmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FdypxXmwhForm myForm = (FdypxXmwhForm) form;
		FdypxXmwhService service = new FdypxXmwhService();
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "szdw_fdypxxmwh.do?method=fdypxxmList");
		return mapping.findForward("list");
	}
	/**
	 * @描述:增加辅导员培训项目
	 * @作者：zhangjw
	 * @日期：2013-7-24 上午11:14:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问思政队伍-辅导员培训-辅导员培训项目维护-增加XMMC:{xmmc},PXDD:{pxdd},SQKG:{sqkg},PXSJ:{pxsj}")
	public ActionForward fdypxxmAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdypxXmwhForm model = (FdypxXmwhForm) form;
		FdypxXmwhService service = new FdypxXmwhService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())){
			model.setFbr(user.getUserName());
			boolean result = service.runInsert(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		List<HashMap<String,String>> bmList = ywxsypxService.getAllBmList();
		request.setAttribute("bmList",bmList);
		logger.debug("***************************************/r/n");
		logger.debug(bmList);
		request.setAttribute("model", model);
		return mapping.findForward("fdypxxmAdd");
	}
	/**
	 * @描述:辅导员培训项目修改
	 * @作者：zhangjw
	 * @日期：2013-7-24 下午2:53:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问思政队伍-辅导员培训-辅导员培训项目维护-修改XMDM:{xmdm},XMMC:{xmmc},PXDD:{pxdd},SQKG:{sqkg},PXSJ:{pxsj}")
	public ActionForward fdypxxmUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdypxXmwhForm myForm = (FdypxXmwhForm) form;
		FdypxXmwhService service = new FdypxXmwhService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			myForm.setFbr(user.getUserName());
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		myForm=service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(myForm));
		request.setAttribute("model", myForm);
		List<HashMap<String,String>> bmList = ywxsypxService.getAllBmList();
		request.setAttribute("bmList",bmList);
		return mapping.findForward("update");
	}
	/**
	 * @描述:辅导员培训项目查看
	 * @作者：zhangjw
	 * @日期：2013-7-24 下午3:13:24
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
	public ActionForward fdypxxmView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdypxXmwhForm myForm = (FdypxXmwhForm) form;
		FdypxXmwhService service = new FdypxXmwhService();
		myForm=service.getModel(myForm);
		request.setAttribute("model", StringUtils.formatData(myForm));
		String view_type = request.getParameter("view_type");
		request.setAttribute("view_type",view_type);
		List<HashMap<String,String>> bmList = ywxsypxService.getAllBmList();
		request.setAttribute("bmList",bmList);
		return mapping.findForward("view");
	}
	/**
	 * @描述:删除辅导员培训项目
	 * @作者：zhangjw
	 * @日期：2013-7-24 下午3:22:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问思政队伍-辅导员培训-辅导员培训项目维护-删除XMDM:{values}")
	public ActionForward fdypxxmDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdypxXmwhService service = new FdypxXmwhService();
		String values = request.getParameter("values");
		String message = null;
		if (!StringUtil.isNull(values)){
			FdypxXmsqService s= new FdypxXmsqService();
			int sqcount = s.getSqCountByPxxm(values.split(","));
			if(sqcount==0){
				int num = service.runDelete(values.split(","));
				boolean result =  num > 0;
				message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
						: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			}else{
				message = MessageUtil.getText("szdw_fdypx_scxmyz");
			}
			
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
		
	}
	/**
	 * @描述:辅导员培训申请选择列表
	 * @作者：zhangjw
	 * @日期：2013-7-26 下午2:32:47
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
	public ActionForward fdypxxmsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FdypxXmwhForm myForm = (FdypxXmwhForm) form;
		FdypxXmwhService service = new FdypxXmwhService();
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "szdw_fdypxxmwh.do?method=fdypxxmList");
		return mapping.findForward("xzxmlist");
	}
	/**
	 * @描述:辅导员培训申请选择查看
	 * @作者：zhangjw
	 * @日期：2013-7-26 下午2:33:09
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
	public ActionForward fdypxxmxzView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdypxXmwhForm myForm = (FdypxXmwhForm) form;
		FdypxXmwhService service = new FdypxXmwhService();
		myForm=service.getModel(myForm);
		request.setAttribute("model", myForm);
		return mapping.findForward("xzview");
	}

    /**
     * 辅导员业务学习与培训新增是选择培训项目显示
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward pxxmList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        FdypxXmwhForm myForm = (FdypxXmwhForm) form;
        FdypxXmwhService service = new FdypxXmwhService();
        User user = getUser(request);
        if (QUERY.equals(myForm.getType())){
            //生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            myForm.setSearchModel(searchModel);

            //查询
            List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        FormModleCommon.commonRequestSet(request);
        request.setAttribute("path", "szdw_fdypxxmwh.do?method=fdypxxmList");
        String gotoPath = "szdw_fdy_ywxxypxjg.do?method=add";
        request.setAttribute("gotoPath",gotoPath);
        return mapping.findForward("pxxmList");
    }
}
