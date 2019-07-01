/**
 * @部门:学工产品事业部
 * @日期：2014-3-7 上午10:14:17 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.sh;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.zdzm.comm.ZdzmComm;
import com.zfsoft.xgxt.rcsw.zdzm.cssz.ZdzmCsszForm;
import com.zfsoft.xgxt.rcsw.zdzm.cssz.ZdzmCsszService;
import com.zfsoft.xgxt.rcsw.zdzm.jggl.ZdzmJgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 在读证明审核管理
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-7 上午10:14:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZdzmShAction extends SuperAction {
	
	/**
	 * @描述 学生显示信息表单服务
	 */
	private static BdpzService bdpzService = new BdpzService();
	
	/**
	 * @描述 审核流操作接口
	 */
	private ShlcInterface shlc = new CommShlcImpl();

	/**
	 * @描述 学生表单数据列表
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 * @描述 初始化学生信息表单参数列表
	 */
	public ZdzmShAction() {
		super();
		jbxxList = bdpzService.getJbxxpz(ZdzmComm.ZDZM_BDID);
	}

	private static final String url = ZdzmComm.PATH_SH;
	
	/**
	 * 
	 * @描述:查询在读证明申请审核列表
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
	public ActionForward queryZdzmShList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmShForm model  = (ZdzmShForm) form;
		ZdzmShService service = new ZdzmShService();
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		if (QUERY.equalsIgnoreCase(model.getType())){
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
		
		ZdzmCsszService csszService = new ZdzmCsszService();			
		ZdzmCsszForm csszModel = csszService.getCssz();
		request.setAttribute("csszModel", csszModel);
		
		request.setAttribute("path", ZdzmComm.PATH_SH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("queryZdzmShList");
	}
	
	/**
	 * 
	 * @描述:审核在读证明申请
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
	public ActionForward shZdzmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmShForm model  = (ZdzmShForm) form;
		ZdzmShService service = new ZdzmShService();
		
		String xtgwid = model.getXtgwid();
		String shid = model.getShid();
		
		ZdzmShForm dataModel = service.getModel(model.getZdzmsqid());
		
		if(null != dataModel){
			BeanUtils.copyProperties(model, dataModel);
			model.setShid(shid);
			model.setXtgwid(xtgwid);
		}
		request.setAttribute("jbxxList", jbxxList);
		XsxxService xsxxService = new XsxxService();
		HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		return mapping.findForward("shZdzmsq");
	}
	
	/**
	 * 
	 * @描述:查看 在读证明申请
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
	public ActionForward viewZdzmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmShForm model  = (ZdzmShForm) form;
		ZdzmShService service = new ZdzmShService();
		
		ZdzmShForm dataModel = service.getModel(model.getZdzmsqid());
		
		if(null != dataModel){
			BeanUtils.copyProperties(model, dataModel); //数据拷备
		}
		request.setAttribute("jbxxList", jbxxList);
		XsxxService xsxxService = new XsxxService();
		HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(dataModel.getXh());
		request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		return mapping.findForward("viewZdzmsq");
	}
	
	/**
	 * 
	 * @描述:提交在读证明审核
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
	@SystemLog(description="访问日常事务-在读证明办理-在读证明审核-审核ZDZMSQID:{zdzmsqid}")
	public ActionForward shZdzmsqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		ZdzmShForm model  = (ZdzmShForm) form;
		ZdzmShService service = TransactionControlProxy.newProxy(new ZdzmShService());
		User user = getUser(request);
		
		//单个保存审核
		boolean result = service.saveSh(model,user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @描述:撤销在读证明审核
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
	@SystemLog(description="访问日常事务-在读证明办理-在读证明审核-撤销ZDZMSQID:{zdzmsqid}")
	public ActionForward cancelZdzmsqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmShForm model  = (ZdzmShForm) form;
		ZdzmShService service = new ZdzmShService();
		
		ZdzmShForm dataModel = service.getModel(model.getZdzmsqid());
		
		dataModel.setShzt(Constants.YW_SHZ);
		
		boolean isSuccess = service.runUpdate(dataModel); //更新 Model
		
		if(isSuccess){
			isSuccess = new ZdzmJgService().deleteZdzmJgBySqid(dataModel.getZdzmsqid()); 
		}
		
		String messageKey = isSuccess ? MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS) :  MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
		response.getWriter().print(getJsonMessage(messageKey));
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述:批量审核保存
	 * @作者：cq [工号：785]
	 * @日期：2014-4-25 下午03:57:35
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
	@SystemLog(description="访问日常事务-在读证明办理-在读证明审核-批量审核ID:{id}")
	public ActionForward zdzmPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZdzmShForm model  = (ZdzmShForm) form;
		ZdzmShService service = TransactionControlProxy.newProxy(new ZdzmShService());
		User user = getUser(request);
		
		if("SAVE".equalsIgnoreCase(model.getType())){
			
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		return mapping.findForward("zdzmPlsh");
	}
	
}
