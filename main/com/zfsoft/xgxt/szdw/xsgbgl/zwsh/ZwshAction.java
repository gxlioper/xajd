/**
 * @部门:学工产品事业部
 * @日期：2013-7-25 下午4:13:59 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwsh;

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
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.szdw.xsgbgl.zwlx.ZwlxForm;
import com.zfsoft.xgxt.szdw.xsgbgl.zwlx.ZwlxService;
import com.zfsoft.xgxt.szdw.xsgbgl.zwsq.ZwsqForm;
import com.zfsoft.xgxt.szdw.xsgbgl.zwsq.ZwsqService;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhForm;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部管理职务审核
 * @作者： zhangjw
 * @时间： 2013-8-9 下午5:00:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZwshAction extends SuperAction {
	
	private ZwshService service = TransactionControlProxy.newProxy(new ZwshService());

	private static final String  CJFF = "cjff";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "szdw_zwsh.do?method=zwshList";
	/**
	 * @描述:职务审核列表
	 * @作者：zhangjw
	 * @日期：2013-8-9 下午5:02:02
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
	public ActionForward zwshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZwshForm myForm = (ZwshForm) form;
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
		request.setAttribute("path", "szdw_zwsh.do?method=zwshList");
		return mapping.findForward("list");
	}
	/**
	 * @描述:职务审核
	 * @作者：zhangjw
	 * @日期：2013-8-9 下午5:02:47
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
	public ActionForward zwsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwshForm myForm = (ZwshForm) form;
		ZwsqService sqservice = new ZwsqService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		//获取申请信息
		ZwsqForm model = sqservice.getModel(myForm);

		if (SAVE.equalsIgnoreCase(myForm.getType())){
			//保存审核结果
			myForm.setSplc(model.getSplc());
			myForm.setXh(model.getXh());
			myForm.setZwid(model.getZwid());
			boolean result = service.zwsh(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//设置职务详细信息
		setZwinfoModel(request, model);
		//设置学生信息
		szXsxx(request,myForm.getXh());
		request.setAttribute("myForm", StringUtils.formatData(myForm));
		if("ck".equalsIgnoreCase(myForm.getType())){
			return mapping.findForward("zwck");
		}
		return mapping.findForward("zwsh");
	}
	/**
	 * @描述:设置职务详细信息
	 * @作者：zhangjw
	 * @日期：2013-8-12 上午9:54:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @param model
	 * @throws Exception
	 * void 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	private void setZwinfoModel(HttpServletRequest request,ZwsqForm model) throws Exception{
		//查询职务信息
		ZwwhForm m = new ZwwhForm();
		m.setZwid(model.getZwid());
		ZwwhForm zwmodel= new ZwwhService().getModel(m);
		//查询职务类型
		ZwlxForm zwlx = new ZwlxForm();
		if(zwmodel!=null){
			zwlx.setLxdm(zwmodel.getLxdm());
		}
		ZwlxForm zwlxmodel= new ZwlxService().getModel(zwlx);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("zwmodel", StringUtils.formatData(zwmodel));
		request.setAttribute("zwlxmodel", StringUtils.formatData(zwlxmodel));
				
	}
	
	@SystemAuth(url = url)
	private void szXsxx(HttpServletRequest request,String xh){
		//查询学生信息
		if(xh != null && !"".equals(xh)){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(CJFF);
		request.setAttribute("jbxxList", jbxxList);
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelZwsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZwshForm model = (ZwshForm) form;
		String sqid = request.getParameter("sqid");
	    String shzt = request.getParameter("shzt");
		model.setSqid(sqid);
		model.setShzt(shzt);
		//FdypxXmshService service = new FdypxXmshService();
		//撤销日常行为审核，最后一级。
		boolean isSuccess = service.newCancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述:批量审核保存
	 * @作者：cq [工号：785]
	 * @日期：2014-4-30 上午09:22:22
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
	public ActionForward zwPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwshForm myForm = (ZwshForm) form;
		User user = getUser(request);
		
		if("save".equalsIgnoreCase(myForm.getType())){
			
			String message = service.savePlsh(myForm,user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		return mapping.findForward("zwPlsh");
	}
	
	/**
	 * 
	 * @描述:重写撤销方法
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-31 下午04:39:48
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
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZwshForm model =new ZwshForm();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setShlc(splc);
		model.setShid(shid);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		
		String cancelFlg = service.cxshnew(shxx.get("ywid"),model,user);
		

		// 审核撤销成功
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
}
