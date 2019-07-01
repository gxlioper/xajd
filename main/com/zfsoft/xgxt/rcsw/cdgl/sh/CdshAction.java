/**
 * @部门:学工产品事业部
 * @日期：2014-3-7 上午10:14:17 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.sh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.rcsw.cdgl.jg.CdjgService;
import com.zfsoft.xgxt.rcsw.cdgl.sq.CdsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 场地审核Action 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-4-23 下午04:48:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CdshAction extends SuperAction {
	
	/**
	 * @描述 学生显示信息表单服务
	 */
	private static BdpzService bdpzService = new BdpzService();
	
	/**
	 * @描述 学生信息服务
	 */
	private XsxxService xsxxService = new XsxxService();
	
	/**
	 * @描述 学生表单数据列表
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 * @描述 场地申请服务
	 */
	private CdsqService cdsqService = new CdsqService();
	
	/**
	 * @描述 场地结果服务
	 */
	private CdjgService cdjgService = new CdjgService();
	
	/**
	 * @描述 场地申请审核服务
	 */
	private CdshService service = new CdshService();
	
	/**
	 * @属性： CDGL_BBID 表单配置id
	 */
	private static final String CDGL_BBID = "rcswcdgl"; 
	
	/**
	 *  @属性： PATH 路径
	 */
	public static final String PATH = "rcsw_cdgl_cdshgl.do";
	
	public static final String url = "rcsw_cdgl_cdshgl.do";
	
	/**
	 * @描述 初始化学生信息表单参数列表
	 */
	public CdshAction() {
		super();
		jbxxList = bdpzService.getJbxxpz(CDGL_BBID);
	}

	/**
	 * 
	 * @描述:查询场地申请申请审核列表
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
	public ActionForward cdshCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cdshCx");
	}
	
	/**
	 * 
	 * @描述:检索数据列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:43:04
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
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdshForm model = (CdshForm) form;
		User user = getUser(request);
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
	
	/**
	 * 
	 * @描述:审核场地申请审核
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 下午01:38:49
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
	public ActionForward cdshSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdshForm model  = (CdshForm) form;
		String sqid = model.getSqid();
		String xtgwid = model.getXtgwid();
		String shid = model.getShid();
		
		if(StringUtils.isNotBlank(sqid)){
			CdshForm dataModel = service.getModel(sqid);
			if(null != dataModel){
				BeanUtils.copyProperties(model, xgxt.utils.String.StringUtils.formatData(dataModel));
				model.setShid(shid);
				model.setXtgwid(xtgwid);
			}
			HashMap<String , String> cdsqxx = cdsqService.getCdsqxx(model.getSqid());//获取场地申请信息
			request.setAttribute("cdsqxx", xgxt.utils.String.StringUtils.formatData(cdsqxx));
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}

		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("cdshSh");
	}
	
	/**
	 * 
	 * @描述:查看 场地申请申请
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 下午01:38:49
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
	public ActionForward cdshCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdshForm model  = (CdshForm) form;
		CdshService service = new CdshService();
		
		if(StringUtils.isNotBlank(model.getSqid())){
			CdshForm dataModel = service.getModel(model.getSqid());
			if(null != dataModel){
				BeanUtils.copyProperties(model, dataModel);
			}
			HashMap<String , String> cdsqxx = cdsqService.getCdsqxx(model.getSqid());//获取场地申请信息
			request.setAttribute("cdsqxx", xgxt.utils.String.StringUtils.formatData(cdsqxx));
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(dataModel.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("cdshCk");
	}
	
	/** 
	 * @描述:提交场地申请审核
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 下午02:14:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-场地管理-场地使用申请审核-审核SQID:{sqid}")
	public ActionForward cdshShAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdshForm model  = (CdshForm) form;
		CdshService service = new CdshService();
		User user = getUser(request);
		
		boolean checkConditions = service.checkSqSjd(model);

		String messageKey ;
		
		if(!checkConditions){
			messageKey = MessageKey.RCSW_CDSQ_CHECK_ERROR;
		}else{
			//单个保存审核
			boolean result = service.saveSh(model,user);
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;	
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @描述:撤销场地申请审核
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 下午03:22:24
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
	@SystemLog(description="访问日常事务-场地管理-场地使用申请审核-撤销SQID:{sqid}")
	public ActionForward cancelCdshAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdshForm model  = (CdshForm) form;
		CdshService service = new CdshService();
		CdshForm dataModel = service.getModel(model.getSqid());
		dataModel.setShzt(Constants.YW_SHZ);
		boolean isSuccess = service.runUpdate(dataModel); //更新 Model
		if(isSuccess){
			isSuccess = cdjgService.deleteJgBySqid(dataModel.getSqid()); //删除结果表数据
		}
		String messageKey = isSuccess ? MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS) :  MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
		response.getWriter().print(getJsonMessage(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述:批量保存审核
	 * @作者：cq [工号：785]
	 * @日期：2014-4-28 上午11:52:29
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
	@SystemLog(description="访问日常事务-场地管理-场地使用申请审核-批量保存ID:{id}")
	public ActionForward cdsqPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CdshForm model  = (CdshForm) form;
		CdshService service = new CdshService();
		
		User user = getUser(request);
		
		if("SAVE".equalsIgnoreCase(model.getType())){
			
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		return mapping.findForward("cdsqPlsh");
	}
	
}
