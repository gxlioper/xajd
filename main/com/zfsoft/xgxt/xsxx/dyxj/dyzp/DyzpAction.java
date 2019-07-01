/**
 * @部门:学工产品事业部
 * @日期：2015-6-19 上午11:10:58 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.dyzp;

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
import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xsxx.dyxj.cssz.CsszModel;
import com.zfsoft.xgxt.xsxx.dyxj.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.dyxj.dmwh.ZpdjModel;
import com.zfsoft.xgxt.xsxx.dyxj.dmwh.ZpdjService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @类功能描述: 德育自评 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-19 上午11:10:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DyzpAction extends SuperAuditAction<DyzpModel, DyzpService> {

	
	protected DyzpAction (String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}

	public DyzpAction(){
		this("xsxx_dyzp", "xsxx_dyxj_pysq.do", "xsxx_dyxj_pysh.do");
	}
	
	
	/**德育自评查询**/
	@SystemAuth(url = "xsxx_dyxj_pysq.do")
	public ActionForward zpsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CsszService csszService = new CsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		SearchModel searchModel=new SearchModel();
		CsszModel csszModle =  csszService.getModel();
		searchModel.setSearch_tj_xn(new String[]{csszModle.getSqxn()});
		searchModel.setSearch_tj_xq(new String[]{csszModle.getSqxq()});
		request.setAttribute("searchTj", searchModel);
		
		request.setAttribute("path", "xsxx_dyxj_pysq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zpsqList");
	}
	
	
	/**德育自评查询**/
	@SystemAuth(url = "xsxx_dyxj_pysq.do")
	public ActionForward getZpsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DyzpModel model = (DyzpModel) form;
		DyzpService service = new DyzpService();
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	/**德育自评申请**/
	@SystemAuth(url = "xsxx_dyxj_pysq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward zpsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DyzpModel model = (DyzpModel) form;
		User user = getUser(request);
		CsszService csszService = new CsszService();
		CsszModel csszModle =  csszService.getModel();
		
		model.setXn(csszModle.getSqxn());
		model.setXq(csszModle.getSqxq());
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		request.setAttribute("cssz", csszModle);
		
		ZpdjService zpdjService = new ZpdjService();
		List<HashMap<String,String>> zpdjList = zpdjService.getAllList(new ZpdjModel());
		
		request.setAttribute("zpdjList", zpdjList);
		request.setAttribute("path", "xsxxDyxjDyzp.do?method=zpsq");
		request.setAttribute("xn", csszModle.getSqxn());
		request.setAttribute("xq", ("01").equals(csszModle.getSqxq()) ? "第一学期":"第二学期");
		this.saveToken(request);
		return mapping.findForward("zpsq");
	}
	
	
	/**德育自评修改**/
	@SystemAuth(url = "xsxx_dyxj_pysq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DyzpModel myForm = (DyzpModel) form;
		DyzpService service = new DyzpService();
		
		DyzpModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			request.setAttribute("xn",model.getXn());
			request.setAttribute("xq", ("01").equals(model.getXq()) ? "第一学期":"第二学期");
			if (!StringUtil.isNull(model.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		ZpdjService zpdjService = new ZpdjService();
		List<HashMap<String,String>> zpdjList = zpdjService.getAllList(new ZpdjModel());
		request.setAttribute("zpdjList", zpdjList);
		
		return mapping.findForward("xgsq");
	}
	
	
	/**取消申请**/
	@SystemAuth(url = "xsxx_dyxj_pysq.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-德育小结-取消申请Id:{id}")
	public ActionForward qxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DyzpModel myForm = (DyzpModel) form;
		DyzpService service = new DyzpService();
		
		boolean result = service.runDelete(new String[]{myForm.getId()}) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	/**德育自评审核**/
	@SystemAuth(url = "xsxx_dyxj_pysh.do")
	public ActionForward zpshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SearchModel searchModel=new SearchModel();
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getSqxn()});
		searchModel.setSearch_tj_xq(new String[]{csszModel.getSqxq()});
		request.setAttribute("searchTj", searchModel);
		
		request.setAttribute("path", "xsxx_dyxj_pysh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zpshList");
	}
	
	
	/**德育自评审核**/
	@SystemAuth(url = "xsxx_dyxj_pysh.do")
	public ActionForward getZpshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DyzpModel model = (DyzpModel) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//查询
		List<HashMap<String,String>> resultList = getService().getAudingList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**德育自评审核**/
	@SystemAuth(url = "xsxx_dyxj_pysh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward zpsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DyzpModel myForm = (DyzpModel) form;
		DyzpService service = new DyzpService();
		DyzpModel model = service.getModel(myForm.getId());
         
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			myForm.setPddjdm(myForm.getDjdm());
			
			if (!StringUtil.isNull(model.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		
		ZpdjService zpdjService = new ZpdjService();
		List<HashMap<String,String>> zpdjList = zpdjService.getAllList(new ZpdjModel());
		request.setAttribute("zpdjList", zpdjList);
		String xtgwid =request.getParameter("xtgwid");
		myForm.setXtgwid(xtgwid);
		List<HashMap<String,String>> infoList = ShlcUtil.getShlcInfo(myForm.getId());
		String zd2 = "";
		if(infoList!=null && infoList.size()>0){
			for(HashMap<String,String> info:infoList){
				if(info.get("zd2")!=null&& !"".equalsIgnoreCase(info.get("zd2"))){
					zd2 = info.get("zd2");
					myForm.setPddjdm(zd2);
				}
			}
		}
		//优秀审核人数上限
		request.setAttribute("yxsx", service.isOverLimit(model.getXn(), model.getXq(), model.getXh(),"1"));		
		return mapping.findForward("zpsh");
	}
	
	
	/**查看申请*/
	public ActionForward cksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DyzpModel myForm = (DyzpModel) form;
		DyzpService service = new DyzpService();
		
		DyzpModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("cksq");
	}
	
	
	/**查询是否已申请**/
	public ActionForward getCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DyzpModel myForm = (DyzpModel) form;
		DyzpService service = new DyzpService();
		
		String count = service.getCount(myForm);
		response.getWriter().print(count);
		return null;
	}
	
	/**
	 * 
	 * @描述: 批量审核
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-11-3 下午02:45:48
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
	@SystemAuth(url = "xsxx_dyxj_pysh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward dyzpPlSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DyzpModel model = (DyzpModel) form;
		DyzpService service = new DyzpService();
		User user = getUser(request);
		if("SAVE".equalsIgnoreCase(model.getType())){
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		DyzpModel myForm = service.getModel(model.getId());
		
		if (model != null){
			BeanUtils.copyProperties(model, myForm);
			model.setPddjdm(model.getDjdm());
			
		}
		
		List<HashMap<String,String>> infoList = ShlcUtil.getShlcInfo(model.getId());
		String zd2 = "";
		if(infoList!=null && infoList.size()>0){
			for(HashMap<String,String> info:infoList){
				if(info.get("zd2")!=null&& !"".equalsIgnoreCase(info.get("zd2"))){
					zd2 = info.get("zd2");
					model.setPddjdm(zd2);
				}
			}
		}
		
		ZpdjService zpdjService = new ZpdjService();
		List<HashMap<String,String>> zpdjList = zpdjService.getAllList(new ZpdjModel());
		request.setAttribute("zpdjList", zpdjList);
		return mapping.findForward("dyzpPlSh");
	}
	
	public ActionForward saveDyzpsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DyzpModel model = (DyzpModel) StringUtils.formatBean(form);
		DyzpService service = new DyzpService();
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		boolean isSuccess = service.saveSh(model, user);
		String messageKey = isSuccess ? MessageKey.SYS_AUD_SUCCESS : MessageKey.SYS_AUD_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 重写父类审核流撤销方法
	 */
	public ActionForward cancelAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DyzpModel t  = (DyzpModel) form;
		DyzpService service = getService();
		User user = getUser(request);
		
		//更新 申请记录对应审核状态为“审核中”
		t.setShzt(Constants.YW_SHZ);
		DyzpModel model = service.getModel(t);
		
		boolean isSuccess = new CommShlcImpl().runCancel(user.getUserName(), t.getId(), model.getSplcid(), t.getGwid());
		t.setPddjdm("");
		if(isSuccess){
			isSuccess = service.runUpdate(t); 
			service.deleteResult(t); 
		}
		
		String messageKey = isSuccess ? MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS) :  MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
		response.getWriter().print(getJsonMessage(messageKey));
		
		return null;
	}
	
}
