/**
 * @部门:学工产品事业部
 * @日期：2014-4-29 下午03:07:26 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxcl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xljkwzdx.common.StringTools;
import com.zfsoft.xgxt.xljkwzdx.common.ZtToCnDesc;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxgl.XlzxglForm;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxgl.XlzxglService;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xsyyzx.XsyysqForm;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xsyyzx.XsyysqService;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.yyzxfk.YyzxfkForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询 -心理咨询处理
 * @类功能描述: 
 * @作者：王志刚[工号:1060]
 * @时间： 2014-4-29 下午03:07:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlzxclAction extends SuperAction{
	
	/**
	 * 在读证明-学生信息表单显示数据库配置代码
	 */
	public static final String XLZXCL_BDID = "xljkwzdxxlzxcl";
	/**
	 * 学生显示信息表单服务
	 */
	private static BdpzService bdpzService = new BdpzService();
	
	/**
	 * 学生表单数据列表
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 * @描述 ：无参构造器 
	 * @描述 ：初始化表单参数
	 */
	public XlzxclAction() {
		super();
		jbxxList = bdpzService.getJbxxpz(XLZXCL_BDID);
	}

	private static final String url = "xljk_xljkzx_xlzxcl.do";
	
	/**
	 * 
	 * @描述:查询心理咨询处理（跳转）
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-29 下午03:36:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryXlzxcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//获取操作权限
		request.setAttribute("path", "xljk_xljkzx_xlzxcl.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("queryXlzxcl");
	}
	
	/**
	 * 
	 * @描述:查询心理咨询处理
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-29 下午03:39:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryXlzxclAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclForm model = (XlzxclForm) form;
		XlzxclService service = new XlzxclService();
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		model.setZxs(user.getUserName());
		
		//查询
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:添加心理咨询处理（跳转）
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-29下午04:32:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addXlzxcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclForm model = (XlzxclForm) form;
		
		User user = getUser(request);
		XlzxclService service = new XlzxclService();
		HashMap<String, String> zxsxx = service.getZxsxx(user.getUserName());
		//设置咨询师信息
		request.setAttribute("zxsxx", zxsxx);
		request.setAttribute("jbxxList", jbxxList);
		
		if(!StringUtil.isNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
			
		}
		
		String path = "xljk_xlzxcl.do?method=addXlzxcl";
		request.setAttribute("path", path);
		
		return mapping.findForward("addXlzxcl");
	}
	
	/**
	 * 
	 * @描述:添加心理咨询处理
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-30上午10:30:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addXlzxclAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclForm model  = (XlzxclForm) form;
		XlzxclService service = new XlzxclService();
		
		String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		model.setZxid(uuid);
		model.setZxzt("0"); //默认待咨询
		
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:修改心理咨询处理（跳转）
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-30上午10:20:39 
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
	public ActionForward updateXlzxcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclForm model  = (XlzxclForm) form;
		XlzxclService service = new XlzxclService();
		
		XlzxclForm dataModel = service.getModel(model.getZxid());
		BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
		
		HashMap<String, String> zxsxx = service.getZxsxx(model.getZxs());
		request.setAttribute("zxsxx", zxsxx);
		request.setAttribute("jbxxList", jbxxList);
		//设置不能重新选择
		request.setAttribute("type", "update");
		
		if(!StringUtil.isNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		
		return mapping.findForward("updateXlzxcl");
	}
	
	/**
	 * 
	 * @描述:修改心理咨询处理
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-29上午10:32:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateXlzxclAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclForm model  = (XlzxclForm) form;
		XlzxclService service = new XlzxclService();
		
		boolean isSuccess = service.runUpdate(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:查看学生预约咨询申请及安排咨询信息及咨询师反馈信息
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-5-9上午09:33:39 
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
	public ActionForward viewXlzxcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclForm model = (XlzxclForm) form;
		XlzxclService service = new XlzxclService();
		XsyysqService xsyysqservice = new XsyysqService();
		
		XlzxclForm dataModel = service.getModel(model.getZxid());
		BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
		
		model.setBz(StringTools.StringToText(StringTools.strOutNull(model.getBz())));
		model.setXszxpj(StringTools.StringToText(StringTools.strOutNull(model.getXszxpj())));
		model.setZxzt(ZtToCnDesc.zxztChange(model.getZxzt()));
		model.setSfxyzj(ZtToCnDesc.sfxyzjChange(model.getSfxyzj()));
		model.setSfyyxczx(ZtToCnDesc.sfyyxczxChange(model.getSfyyxczx()));
		
		//设置学生信息显示字段
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", "update");
		
		if(!StringUtil.isNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		
		if(model.getSqid()!=null){
			XsyysqForm xsyysqForm = xsyysqservice.getModel(model.getSqid());
			xsyysqForm.setYyzxzt(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYyzxzt())));
			xsyysqForm.setYyzxxq(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYyzxxq())));
			xsyysqForm.setYysbyy(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYysbyy())));
			request.setAttribute("yyztmc", ZtToCnDesc.yyztChange(xsyysqForm.getYyzt()));
			request.setAttribute("xsyysqForm",StringUtils.formatData(xsyysqForm));
			
			String wtlxarr = xsyysqForm.getWtlx()!=null?xsyysqForm.getWtlx():"";
			wtlxarr = wtlxarr.replaceAll(",", "','");
			wtlxarr = "'"+wtlxarr+"'";
			
			//设置心理问题类型名称String  供前台显示
			List<HashMap<String, String>> xlwtList = xsyysqservice.getXlwtAllListByLxdm(wtlxarr);
			
			StringBuffer wtlxMcStr = new StringBuffer();
			for(int i=0;i<xlwtList.size();i++){
				wtlxMcStr.append(xlwtList.get(i).get("lxmc")).append("；");
			}
			request.setAttribute("wtlxMcStr", wtlxMcStr.toString());
			
			//设置咨询预约说明
			String zxyysm = xsyysqservice.getZxyysm();
			request.setAttribute("zxyysm", StringTools.StringToText(StringTools.strOutNull(zxyysm)));
			
			HashMap<String, String> zxsMap = service.getZxsxx(model.getZxs());
			request.setAttribute("zxsxm", zxsMap.get("xm"));
		}else{
			request.setAttribute("xsyysqForm",null);
		}
		
		//查询心理老师选择的心理问题类型  供前台显示
		String gswtlx = StringTools.strOutNull(model.getGswtlx());
		String[] gswtlxStr = gswtlx.split("###");
		
		String gswtlxarr = gswtlxStr[0];
		gswtlxarr = gswtlxarr.replaceAll(",", "','");
		gswtlxarr = "'"+gswtlxarr+"'";
		
		List<HashMap<String, String>> gsxlwtList = xsyysqservice.getXlwtAllListByLxdm(gswtlxarr);
		
		StringBuffer gswtlxMcStr = new StringBuffer();
		for(int i=0;i<gsxlwtList.size();i++){
			gswtlxMcStr.append(gsxlwtList.get(i).get("lxmc")).append("；");
		}
		String[] qtwtlxStr = (gswtlxStr.length==1?"none@@":gswtlxStr[1]).split("@@");
		String qtwtlxstr = null;
		if(qtwtlxStr.length == 1){
			qtwtlxstr = "";
		}else{
			qtwtlxstr = qtwtlxStr[1];
		}
		gswtlxMcStr.append(qtwtlxstr);
		//设置该生心理问题类型名称String  供前台显示
		request.setAttribute("gswtlxMcStr", gswtlxMcStr);
		
		//接受程度
		String jscd = StringTools.strOutNull(model.getJscd());
		String[] jscdStr = jscd.split("###");
		StringBuffer jscdMcStr = new StringBuffer();
		String[] jscdarr = jscdStr[0].split(",");
		String jscdMc = "";
		for(int i = 0;i <jscdarr.length; i++){
			jscdMc = ZtToCnDesc.jscdChange(jscdarr[i]);
			if(!jscdMc.equals("")){
				jscdMcStr.append(jscdMc).append("；");
			}
		}
		if(jscdStr.length==2){
			jscdMcStr.append(jscdStr[1]);
		}
		//设置咨询师来访者对咨询的接受程度
		request.setAttribute("jscdMcStr", jscdMcStr);
		
		//严重程度评估
		String yzcdpg = StringTools.strOutNull(model.getYzcdpg());
		String[] yzcdpgStr = yzcdpg.split("###");
		StringBuffer yzcdpgMcStr = new StringBuffer();
		String[] yzcdpgarr = yzcdpgStr[0].split(",");
		String yzcdpgMc = "";
		for(int i = 0;i <yzcdpgarr.length; i++){
			yzcdpgMc = ZtToCnDesc.yzcdpgChange(yzcdpgarr[i]);
			if(!yzcdpgMc.equals("")){
				yzcdpgMcStr.append(yzcdpgMc).append("；");
			}
		}
		if(yzcdpgStr.length==2){
			yzcdpgMcStr.append(yzcdpgStr[1]);
		}
		//设置咨询师来访者对咨询的接受程度
		request.setAttribute("yzcdpgMcStr", yzcdpgMcStr);
		
		
		
		
		return mapping.findForward("viewXlzxcl");
	}
	
	/**
	 * 
	 * @描述:删除心理咨询处理
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-23 下午03:23:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward deleteXlzxcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclService service = new XlzxclService();
		
		String zxids = request.getParameter("zxids"); //需删除的心理咨询处理号
		
		int isSuccess = service.runDelete(zxids.split(","));
		
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	/**
	 * 
	 * @描述:心理咨询咨询反馈（跳转）
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-5-5 上午11:05:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewZxFk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XlzxclForm model  = (XlzxclForm) form;
		
		XlzxclService service = new XlzxclService();
		XsyysqService xsyysqService = new XsyysqService();
		
		XlzxclForm dataModel = service.getModel(model.getZxid());
		BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
		if(model.getZxzt().equals("0")){
			model.setZxzt("1");    //设置默认值 为（已咨询）
		}
		if(model.getSfxyzj() == null){
			model.setSfxyzj("1");  //设置是否需要转介默认值 为  (否)
		}
		if(model.getSfyyxczx() == null){
			model.setSfyyxczx("1");//设置是否预约下次咨询默认值 为  (否)
		}
		model.setBz(StringTools.StringToText(StringTools.strOutNull(model.getBz())));
		
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", "update");
		
		if(!StringUtil.isNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		
		//查询学生咨询预约申请信息
		XsyysqForm xsyysqForm = xsyysqService.getModel(model.getSqid());
		xsyysqForm = xsyysqForm != null ? xsyysqForm : new XsyysqForm();
		xsyysqForm.setYyzxzt(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYyzxzt())));
		xsyysqForm.setYyzxxq(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYyzxxq())));
		xsyysqForm.setYysbyy(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYysbyy())));
		
		request.setAttribute("xsyysqForm",StringUtils.formatData(xsyysqForm));
		
		request.setAttribute("yyztmc", ZtToCnDesc.yyztChange(xsyysqForm.getYyzt()));
		
		String wtlxarr = xsyysqForm.getWtlx()!=null?xsyysqForm.getWtlx():"";
		wtlxarr = wtlxarr.replaceAll(",", "','");
		wtlxarr = "'"+wtlxarr+"'";
		
		//设置心理问题类型名称String  供前台显示
		List<HashMap<String, String>> xlwtList = xsyysqService.getXlwtAllListByLxdm(wtlxarr);
		StringBuffer wtlxMcStr = new StringBuffer();
		for(int i=0;i<xlwtList.size();i++){
			wtlxMcStr.append(xlwtList.get(i).get("lxmc"));
			//最后一个分隔符不加
			if(i<xlwtList.size()-1){
				wtlxMcStr.append("；");
			}
		}
		request.setAttribute("wtlxMcStr", wtlxMcStr.toString());
		
		//设置心理问题类型名称String  供前台显示
		List<HashMap<String, String>> allxlwtList = xsyysqService.getXlwtAllList();
		request.setAttribute("xlwtList", allxlwtList);
		
		
		//设置咨询预约说明
		String zxyysm = xsyysqService.getZxyysm();
		request.setAttribute("zxyysm", StringTools.StringToText(StringTools.strOutNull(zxyysm)));
		
		HashMap<String, String> zxsMap = service.getZxsxx(model.getZxs());
		request.setAttribute("zxsxm", zxsMap.get("xm"));
		
		//设置心理老师选择的心理问题类型  供前台显示
		String gswtlx = StringTools.strOutNull(model.getGswtlx());
		String[] gswtlxStr = gswtlx.split("###");
		request.setAttribute("gswtlx", gswtlxStr[0]);
		
		String[] qtwtlxStr = (gswtlxStr.length==1?"none@@":gswtlxStr[1]).split("@@");
		request.setAttribute("qtwtlx", qtwtlxStr[0]);
		String qtwtlxstr = null;
		if(qtwtlxStr.length == 1){
			qtwtlxstr = "";
		}else{
			qtwtlxstr = qtwtlxStr[1];
		}
		request.setAttribute("qtwtlxstr", qtwtlxstr);
		
		String jscd = StringTools.strOutNull(model.getJscd());
		String[] jscdStr = jscd.split("###");
		request.setAttribute("jscd", jscdStr[0]);
		
		request.setAttribute("qtjscd", jscdStr.length==1?"":jscdStr[1]);
		
		String yzcdpg = StringTools.strOutNull(model.getYzcdpg());
		String[] yzcdpgStr = yzcdpg.split("###");
		request.setAttribute("yzcdpg", yzcdpgStr[0]);
		
		request.setAttribute("qtyzcdpg", yzcdpgStr.length==1?"":yzcdpgStr[1]);
		
		return mapping.findForward("viewZxFk");
	}
	
	/**
	 * 
	 * @描述:心理咨询咨询反馈
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-5-5 下午4:05:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward zxFkAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclForm model  = (XlzxclForm) form;
		XlzxclService service = new XlzxclService();
		
		
		//该生问题类型
		String[] gswtlxarr = model.getWtlxarray();
		String gswtlx = org.apache.commons.lang.StringUtils.join(gswtlxarr, ",");
		String[] qtwtlxflag = model.getQtwtlxarray();
		gswtlx+="###";
		if(qtwtlxflag != null){
			gswtlx=gswtlx+qtwtlxflag[0]+"@@"+model.getQtwtlx();
		}else{
			gswtlx=gswtlx+"none@@";
		}
		model.setGswtlx(gswtlx);
		
		//接受程度
		String[] jscdarr = model.getJscdarray();
		String jscd = org.apache.commons.lang.StringUtils.join(jscdarr, ",");
		jscd+="###";
		if(ArrayUtils.contains(jscdarr, "qt")){
			jscd+=model.getQtjscd();
		}
		model.setJscd(jscd);
		
		//严重程度评估
		String[] yzcdpgarr = model.getYzcdpgarray();
		String yzcdpg = org.apache.commons.lang.StringUtils.join(yzcdpgarr, ",");
		yzcdpg+="###";
		if(ArrayUtils.contains(yzcdpgarr, "qt")){
			yzcdpg+=model.getQtyzcdpg();
		}
		model.setYzcdpg(yzcdpg);
		
		if(!StringTools.strOutNull(model.getSfyyxczx()).equals("")){
			if(model.getSfyyxczx().equals("1")){
				model.setXczxsj("");
			}
		}
		
		boolean isSuccess = service.runUpdate(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:导出
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-5-8 下午15:53:39 
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclForm model  = (XlzxclForm) form;
		XlzxclService service = new XlzxclService();

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
		model.setZxs(user.getUserName());
		
		List<HashMap<String,String>> resultList = service.getAllXlzxclList(model);
		HashMap<String,String> mapTemp = null;
		for(int i = 0 ; i<resultList.size();i++){
			mapTemp = resultList.get(i);
			mapTemp.put("yyzxzt", StringTools.TextToString1(StringTools.strOutNull(mapTemp.get("yyzxzt"))));
		}
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}
