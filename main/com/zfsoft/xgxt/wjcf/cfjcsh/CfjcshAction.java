/**
 * @部门:学工产品事业部
 * @日期：2013-10-30 下午06:36:19 
 */  
package com.zfsoft.xgxt.wjcf.cfjcsh;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.wjcf.cfsh.CfshForm;
import com.zfsoft.xgxt.wjcf.cfsh.CfshService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分解除审核) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-30 下午06:36:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfjcshAction extends SuperAction {

	BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	private final static String CFSBZDPZ="cfsbzdpz";
	
	private static final String url = "wjcf_cfjcsh.do?method=cxCfjcshList";
	
	@SystemAuth(url = url)
	public ActionForward cxCfjcshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfjcshForm model=(CfjcshForm)form;
		CfjcshService service=new CfjcshService();
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
		
		String path = "wjcf_cfjcsh.do?method=cxCfjcshList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("cancelPath", "wjcf_cfjcsh.do?method=qxsh");
		
		return mapping.findForward("cxCfjcshList");
	}
	
	/**
	 * 申诉审核
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward jcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfjcshForm model=(CfjcshForm)form;
		CfjcshService service=TransactionControlProxy.newProxy(new CfjcshService());
		CfshService cfshService=new CfshService();
		
		if(SAVE.equalsIgnoreCase(model.getType())){
			//保存审核结果
			User user = getUser(request);
			boolean result = service.jcsh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		
		//是否是最后一级审核
		CfshForm myForm=new CfshForm();
		myForm.setSplcid(model.getSplcid());
		myForm.setGwid(model.getGwid());
		boolean isZhgw=cfshService.isZhgw(myForm);
		//获取违纪信息
		myForm.setYwid(model.getCfid());
		HashMap<String, String> map=cfshService.getCfsbxxForjg(myForm);
		request.setAttribute("map", map);
		//查询学生数据
		if(StringUtils.isNotNull(map.get("xh"))){
			List<HashMap<String, String>> hjqkList = new PjjgService().getHjqkList(map.get("xh"));// 根据学号查询获奖情况
			request.setAttribute("hjqkList", hjqkList);
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("isZhgw", String.valueOf(isZhgw));
		request.setAttribute("type", UPDATE);
		//解除信息
		//苏州工业最后一级审核生成处分文号
		if(isZhgw&&"12686".equals(Base.xxdm)){
			model.setXn(map.get("xn"));
			model.setXq(map.get("xq"));
			String jclsh = service.getJcLsh(model);
			String jcwh =  MessageUtil.getText(MessageKey.WJCF_CFJCWH_FORMAT, new String[] {Base.currNd,jclsh});
			model.setJcwh(jcwh);
		}
		request.setAttribute("cfjcshForm", StringUtils.formatData(model));
		CfjcshForm f = service.getModel(model); 
		request.setAttribute("jcxx", StringUtils.formatData(f));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("jcsh");
		
	}
	/**
	 * 审核查看
	 */
	@SystemAuth(url = url)
	public ActionForward jcshCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CfjcshForm model=(CfjcshForm)form;
		CfjcshService service=new CfjcshService();
		CfshService cfshService=new CfshService();
		
		CfshForm myForm=new CfshForm();
		myForm.setSplcid(model.getSplcid());
		myForm.setGwid(model.getGwid());
		//获取违纪信息
		myForm.setYwid(model.getCfid());
		HashMap<String, String> map=cfshService.getCfsbxxForjg(myForm);
		request.setAttribute("map", map);
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
		//解除信息
		request.setAttribute("cfjcshForm", StringUtils.formatData(model));
		CfjcshForm f = service.getModel(model); 
		request.setAttribute("jcxx", StringUtils.formatData(f));
		return mapping.findForward("jcshCk");
	}
	
	/**
	 * 取消审核
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward qxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfjcshForm model=(CfjcshForm)form;
		CfjcshService service=new CfjcshService();
		
		HashMap<String, String> shxx=ShlcUtil.getShxx(model.getShid());
		model.setYwid(shxx.get("ywid"));
		boolean result = service.cancel(model);
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	/**
	 * 
	 * @描述:验证解除文号是否存在
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-30 上午09:52:44
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
	public ActionForward checkExistJcwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfjcshForm model=(CfjcshForm)form;
		CfjcshService service=new CfjcshService();
		// 取得是否存在验证
		boolean isExistJcwh = service.checkExistJcwh(model);
		response.getWriter().print(isExistJcwh);
		return null;
	}
}
