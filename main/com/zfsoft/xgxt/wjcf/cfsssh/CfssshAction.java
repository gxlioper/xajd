/**
 * @部门:学工产品事业部
 * @日期：2013-10-30 上午09:24:48 
 */  
package com.zfsoft.xgxt.wjcf.cfsssh;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.wjcf.jcsz.WjcfJcszServices;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.wjcf.cfsh.CfshForm;
import com.zfsoft.xgxt.wjcf.cfsh.CfshService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (申诉审核) 
 * @作者：陈敏杰[工号:913]
 * @时间： 2013-10-30 上午09:23:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfssshAction extends SuperAction {
	
	BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	private final static String CFSBZDPZ="cfsbzdpz";
	
	private static final String url = "wjcf_cfsssh.do?method=cxCfssshList";
	
	@SystemAuth(url = url)
	public ActionForward cxCfssshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfssshForm model=(CfssshForm)form;
		CfssshService service=new CfssshService();
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "wjcf_cfsssh.do?method=cxCfssshList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("cancelPath", "wjcf_cfsssh.do?method=qxsh");
		
		return mapping.findForward("cxCfssshList");
	}
	
	/**
	 * 申诉审核
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward sssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfssshForm model=(CfssshForm)form;
		CfssshService service=TransactionControlProxy.newProxy(new CfssshService());
		CfshService cfshService=new CfshService();
		
		if(SAVE.equalsIgnoreCase(model.getType())){
			//保存审核结果
			User user = getUser(request);
			boolean result = service.sssh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		//是否是最后一级审核
		CfshForm myForm=new CfshForm();
		myForm.setSplcid(model.getSplcid());
		myForm.setYwid(model.getCfid());
		myForm.setGwid(model.getGwid());
		boolean isZhgw=cfshService.isZhgw(myForm);
		//获取违纪信息
		HashMap<String, String> map=cfshService.getCfsbxxForjg(myForm);
		request.setAttribute("map", StringUtils.formatData(map));
		//查询学生数据
		if(StringUtils.isNotNull(map.get("xh"))){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("isZhgw", String.valueOf(isZhgw));
		request.setAttribute("type", UPDATE);
		//申诉信息
		request.setAttribute("cfssshForm", StringUtils.formatData(model));
		CfssshForm f = service.getModel(model); 
		request.setAttribute("ssxx", f);
		
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());//违纪处分类别
		
		
		return mapping.findForward("sssh");
		
	}
	/**
	 * 审核查看
	 */
	@SystemAuth(url = url)
	public ActionForward ssshCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CfssshForm model=(CfssshForm)form;
		CfssshService service=new CfssshService();
		CfshService cfshService=new CfshService();
		
		CfshForm myForm=new CfshForm();
		myForm.setSplcid(model.getSplcid());
		myForm.setYwid(model.getCfid());
		myForm.setGwid(model.getGwid());
		//获取违纪信息
		HashMap<String, String> map=cfshService.getCfsbxxForjg(myForm);
		request.setAttribute("map", StringUtils.formatData(map));
		//查询学生数据
		if(StringUtils.isNotNull(map.get("xh"))){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", UPDATE);
		//申诉信息
		request.setAttribute("cfssshForm", model);
		CfssshForm f = service.getModel(model); 
		request.setAttribute("ssxx", f);
		
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());//违纪处分类别
		return mapping.findForward("ssshCk");
	}
	
	/**
	 * 取消审核
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward qxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfssshForm model=(CfssshForm)form;
		CfssshService service=new CfssshService();
		
		HashMap<String, String> shxx=ShlcUtil.getShxx(model.getShid());
		model.setYwid(shxx.get("ywid"));
		boolean result = service.cancel(model);
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	
	/**
	 * 
	 * @描述:(附件下载)
	 * @作者：cmj[工号：982]
	 * @日期：2013-10-31 上午09:46:11
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
	public ActionForward fjxz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfssshForm model=(CfssshForm)form;
		CfssshService service=new CfssshService();
		
		byte b[] = new byte[500];
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(request.getParameter("ssfjmc")));
		InputStream in = service.fjCx(model);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
		
	}
	
	
	/**
	 * 
	 * @描述:批量审核保存(由于最后一步审核需填写处分文号和时间,取消批量审核)
	 * @作者：cq [工号：785]
	 * @日期：2014-4-24 下午12:00:21
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
	public ActionForward ssplsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CfssshForm model=(CfssshForm)form;
		CfssshService service=TransactionControlProxy.newProxy(new CfssshService());
		User user = getUser(request);
		
		if("SAVE".equalsIgnoreCase(model.getType())){
			
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		return mapping.findForward("ssplsh");
		
	}

}
