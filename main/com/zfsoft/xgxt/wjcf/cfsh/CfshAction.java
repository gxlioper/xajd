/**
 * @部门:学工产品事业部
 * @日期：2013-10-28 上午10:51:41 
 */  
package com.zfsoft.xgxt.wjcf.cfsh;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;
import xsgzgl.wjcf.jcsz.WjcfJcszServices;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.wjcf.cfjg.CfjgForm;
import com.zfsoft.xgxt.wjcf.cfjg.CfjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分上报审核) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-28 上午10:51:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfshAction extends SuperAction {
	private final static String CFSBZDPZ="cfsbzdpz1";
	BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "wjcf_cfsh.do?method=cxCfshList";
	
	@SystemAuth(url = url)
	public ActionForward cxCfshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfshForm model=(CfshForm)form;
		CfshService service=new CfshService();
		
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
		String path = "wjcf_cfsh.do?method=cxCfshList";
		request.setAttribute("cancelPath", "wjcf_cfsh.do?method=cancel");
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxCfshList");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfshForm model=(CfshForm)form;
		CfshService service=new CfshService();
		CfjgService cfjgService =new CfjgService();
		CfjgForm  cfjgForm = new CfjgForm();
		
		//是否是最后一级审核
		boolean isZhgw=service.isZhgw(model);
		
		HashMap<String, String> map=service.getCfsbxx(model);	//处分上报信息
		//处分发文权限判断 0：未填写，1：等级低于权限岗位，2：等级等于权限岗位，3等级高于权限岗位
		int cffwqxPd = service.cffwqxPd(map.get("kzzd4"),model.getSplcid(),model.getGwid());
		
		//发文权限：未填写且已是最后一级，或是权限岗位
		if((isZhgw&&cffwqxPd==0)||cffwqxPd==2){
			//苏州工业最后一级审核生成处分文号
			if("12686".equals(Base.xxdm)){
				cfjgForm.setXn(map.get("xn"));
				cfjgForm.setXq(map.get("xq"));
				String cflsh = cfjgService.getLsh2(cfjgForm);
				String cfwh =  MessageUtil.getText(MessageKey.WJCF_CFWH_FORMAT, new String[] {Base.currNd,cflsh});
				
				map.put("kzzd2", cfwh);
			}
			
			//青岛酒店管理职业技术学院，生成处分文号
			if("13011".equals(Base.xxdm)){
				String cfwh = cfjgService.getDefaultCfwhFor13011();
				map.put("kzzd2", cfwh);
			}
		}
		
		//最后一级
		if(isZhgw){
			//苏州工业最后一级审核生成处分文号
			if("12686".equals(Base.xxdm)){
				cfjgForm.setXn(map.get("xn"));
				cfjgForm.setXq(map.get("xq"));
				String cflsh = cfjgService.getLsh2(cfjgForm);
				model.setCflsh(cflsh);
			}
		}
		
		if(SAVE.equalsIgnoreCase(model.getType())){
			//保存审核结果
 			User user = getUser(request);
			boolean result = service.ydsh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		//已受违纪处分
		WjcfCfsbService wjcfCfsbService=new WjcfCfsbService();
		request.setAttribute("yscfqkList", wjcfCfsbService.getYscfqk(map.get("xh")));
		//查询学生数据
		if(StringUtils.isNotNull(map.get("xh"))){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMoreWithZSXX(map.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		request.setAttribute("jbxxList", jbxxList);
		
		model.setCflbdm(map.get("cflbdm"));
		
		request.setAttribute("map", map);
		request.setAttribute("cflbList", new WjcfJcszServices().cflbdmCx());//违纪处分类别
		request.setAttribute("cffwqxPd", cffwqxPd);
		
		request.setAttribute("cfshForm", StringUtils.formatData(model));
		request.setAttribute("isZhgw", String.valueOf(isZhgw));
		
		request.setAttribute("type", UPDATE);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("cfsh");
	}
	/**
	 * 审核查看
	 */
	@SystemAuth(url = url)
	public ActionForward cfshCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CfshForm model=(CfshForm)form;
		CfshService service=new CfshService();
		
		HashMap<String, String> map=service.getCfsbxx(model);
		request.setAttribute("map", StringUtils.formatData(map));
		
		//已受违纪处分
		WjcfCfsbService wjcfCfsbService=new WjcfCfsbService();
		request.setAttribute("yscfqkList", wjcfCfsbService.getYscfqk(map.get("xh")));
		//查询学生数据
		if(StringUtils.isNotNull(map.get("xh"))){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMoreWithZSXX(map.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("cfshForm", StringUtils.formatData(model));
		request.setAttribute("type", UPDATE);
		return mapping.findForward("cfshCk");
	}
	
	//是否可以撤销审核
	public ActionForward canCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfshForm model=(CfshForm)form;
		CfshService service=new CfshService();
		boolean result=service.canCancel(model);
		if(!result){
			String messageKey = MessageKey.WJCF_SBCX_BKCX;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "");
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
		
	
	//最后一级撤销审核
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfshForm model=(CfshForm)form;
		CfshService service=new CfshService();
		HashMap<String, String> shxx=ShlcUtil.getShxx(model.getShid());
		model.setSplcid(shxx.get("lcid"));
		model.setGwid(shxx.get("gwid"));
		boolean result = service.cancel(model, shxx.get("ywid"));
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
		CfshForm model=(CfshForm)form;
		CfshService service=new CfshService();
		
		byte b[] = new byte[500];
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(model.getFjmc()));
		InputStream in = service.fjCx(model);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
		
	}
	
	
	/**
	 * 
	 * @描述:处分批量审核
	 * @作者：cq [工号：785]
	 * @日期：2014-4-23 下午03:16:22
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
	public ActionForward cfplsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfshForm model= (CfshForm) form;
		CfshService service=new CfshService();
		String cffwqx = request.getParameter("cffw");
		String cflbdm = request.getParameter("cflbdm");
		if (SAVE.equals(model.getType())) {
			String cfwh = request.getParameter("cfwh");
			String cfsj = request.getParameter("cfsj");
			String cfdqsj = request.getParameter("cfdqsj");
			String ggqlb = request.getParameter("ggqlb");//更改前的
			String gghlb = request.getParameter("gghlb");//更改后的
			if ("1".equals(cffwqx)) {
				model.setCfwh(cfwh);
				model.setCfsj(cfsj);
				model.setCfdqsj(cfdqsj);
				model.setKzzd1(ggqlb);//原本的
				model.setCflbdm(gghlb);//更改后的
			}else {
				model.setKzzd1(ggqlb);//原本的
				model.setCflbdm(ggqlb);//更改后的
			}
			User user = getUser(request);
			// 批量审核
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		request.setAttribute("cflbList", new WjcfJcszServices().cflbdmCx());//违纪处分类别
		request.setAttribute("cffwqx", cffwqx);
		request.setAttribute("cflbdm", cflbdm);
		return mapping.findForward("cfplsh");

	}
	
	/**
	 * @描述:获得处分到期时间默认值，处分时间+处分期限
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月25日 下午2:14:32
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
	public ActionForward defaultCfdqsj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfshForm model= (CfshForm) form;
		CfshService service=new CfshService();
		String defaultCfdqsj = service.defaultCfdqsj(model);
		response.getWriter().print(getJsonMessage(defaultCfdqsj));
		return null;
	}

}
