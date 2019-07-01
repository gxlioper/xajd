/**
 * @部门:学工产品事业部
 * @日期：2015-8-27 下午06:54:00 
 */  
package com.zfsoft.xgxt.znxgl.znxgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ActionMap;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.znxgl.fxbgl.FxbForm;
import com.zfsoft.xgxt.znxgl.fxbgl.FxbService;
import com.zfsoft.xgxt.znxgl.sxbgl.SxbForm;
import com.zfsoft.xgxt.znxgl.wdznx.WdznxForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：  yxy[工号:1206]
 * @时间： 2015-8-27 下午06:54:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZnxglAction extends SuperAction<ZnxglForm, ZnxglService> {
	ZnxglService service = new ZnxglService();
	private FxbService fxbservice = new FxbService();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	private static final String url = "znxgl_znxgl.do";
	
	//站内信管理查询
	@SystemAuth(url = url)
	public ActionForward getZnxglList(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		   ZnxglForm model = (ZnxglForm) form;
			if (QUERY.equalsIgnoreCase(model.getType())) {
				// 生成高级查询对象
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				model.setSearchModel(searchModel);
				User user = getUser(request);
				// 查询
				List<HashMap<String, String>> resultList = service.getPageList(model, user);
				
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			// 默认高级查询条件
			SearchModel searchModel = new SearchModel();
			request.setAttribute("searchTj", searchModel); 
			String path = "znxgl_znxgl.do";
			request.setAttribute("path", path);
			FormModleCommon.commonRequestSet(request);
		    return mapping.findForward("getZnxglList");
	}
	
	
	//收件箱查看
	public ActionForward glyxjCk(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		    ZnxglForm model = (ZnxglForm) form;
		    boolean result = true;
		    if(model.getType().equals("wd")){
		    	 ZnxglForm wf = service.getModel(model);
				  wf.setJsrydbj("1");
				  result = service.runUpate_sxbckjl(wf);
		    }
		    if(!result){
		    	String messageKey = MessageKey.ZNXGL_XJCKSB;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
		    }
		    HashMap<String, String>  glyXjckMap = service.glyXjckMap(model);
		    request.setAttribute("ztlb", glyXjckMap.get("ztlb"));
		    request.setAttribute("fsrxm", glyXjckMap.get("fsrxm"));
		    request.setAttribute("xjzt", glyXjckMap.get("xjzt"));
		    request.setAttribute("jsrbh", model.getJsrbh());
		    request.setAttribute("xjbh", model.getXjbh());
		    request.setAttribute("fsnr", service.getFsnr_Clob(model.getXjbh()));
		    return mapping.findForward("glyxjCk");
	}
	
	//删除收件箱记录
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delScSjxjl(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		    String[] xjbhs = request.getParameter("values").split(",");
		    int num = xjbhs.length;
		    boolean result = true;
		    if(xjbhs != null){
				    for(int i=0;i<num;i++){
				    	ZnxglForm sxb = new ZnxglForm();
				    	sxb.setJsrbh("系统管理员");
				    	sxb.setXjbh(xjbhs[i]);
				    	ZnxglForm sf =  service.getModel(sxb);
				    	sf.setJsrscbj("1");
				    	result = service.runUpate_sxbscjl(sf);
				    	if(!result){
							response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.SYS_DEL_FAIL)));
							return null;
				    	}
				    }
		    }
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		    return null;
	}
	
	//站内信管理员信件分配
	public ActionForward XjFp(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		    ZnxglForm model = (ZnxglForm) form;
		    model.setJsrbh("系统管理员");
		    HashMap<String, String>  glyXjckMap = service.glyXjckMap(model);
		    request.setAttribute("ztlb", glyXjckMap.get("ztlb"));
		    request.setAttribute("fsrxm", glyXjckMap.get("fsrxm"));
		    request.setAttribute("xjzt", glyXjckMap.get("xjzt"));
		    request.setAttribute("fsnr", service.getFsnr_Clob(model.getXjbh()));
		    request.setAttribute("xjbh", glyXjckMap.get("xjbh"));
		    return mapping.findForward("znxfp");
	}
	
	//站内信管理员信件分配保存
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveXjFp(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		    ZnxglForm model = (ZnxglForm) form;
		    String xjbh = model.getXjbh();
		    boolean result = true;
		    //先删除站内信接收表原先管理员的接收记录
		    if(xjbh != null && !xjbh.equals("")){
		    			model.setJsrbh("系统管理员");
				    	result = service.delFprJsjl(model);
				    	if(!result){
							response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.ZNXGL_XJFBSB)));
							return null;
				    	}
		    }
		    //然后在接收表中插入分配记录（分配人编号为系统管理员）
		    String[] jsrbh = request.getParameterValues("jsrbh");
		    if(jsrbh != null && !jsrbh[0].equals("")){
		    	for(int i=0;i<jsrbh.length;i++){
		    		ZnxglForm sf = new ZnxglForm();
			    	sf.setFprbh("系统管理员");
			    	sf.setJsrbh(jsrbh[i]);
			    	sf.setXjbh(xjbh);
			    	result = service.save(sf);
			    	if(!result){
			    		response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.ZNXGL_XJFBSB)));
						return null;
			    	}
			    }
		    }
		    
			String message = result ? MessageUtil.getText(
					MessageKey.ZNXGL_XJFBCG) : MessageUtil
					.getText(MessageKey.ZNXGL_XJFBSB);
			response.getWriter().print(getJsonMessage(message));
		    return null;
	}
	
	//站内信管理信件回复
	public ActionForward xjHf(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		ZnxglForm model = (ZnxglForm) form;
		model.setJsrbh("系统管理员");
	    if(model.getJsrydbj().equals("0")){
	    	 ZnxglForm wf = service.getModel(model);
			  wf.setJsrydbj("1");
			  service.runUpate_sxbckjl(wf);
	    }
		HashMap<String, String>  glyXjckMap = service.glyXjckMap(model);
	    request.setAttribute("jsrxm", glyXjckMap.get("fsrxm"));
	    request.setAttribute("fsrxm", glyXjckMap.get("fsrxm"));
	    request.setAttribute("fssj", glyXjckMap.get("fssj"));
	    request.setAttribute("jsrbh", glyXjckMap.get("fsrbh"));
	    request.setAttribute("xjzt", glyXjckMap.get("xjzt"));
	    request.setAttribute("ztlb",  glyXjckMap.get("ztlb"));
	    request.setAttribute("xjbh", glyXjckMap.get("xjbh"));
	    request.setAttribute("fsnr", service.getFsnr_Clob(model.getXjbh()));
		return mapping.findForward("znxhf");
	}
	
	//站内信回复保存
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward savexjHf(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		ZnxglForm model = (ZnxglForm) form;
		User user = getUser(request);
		/* 业务更改代码，站内信管理回复保存时更新原信件的接收人编号为当前操作人真实账号* 2015-10-16*/
		 ZnxglForm znxform = new ZnxglForm();
		 znxform.setJsrbh(user.getUserName());
		 znxform.setXjbh(model.getXjbh());
		 boolean result = service.runUpdate_znxsjx(znxform);
		/*结束 */
		FxbForm ff = new FxbForm();
		ff.setFsrbh(user.getUserName());
		String fsnr = DealString.toGBK(request.getParameter("editorid"));
		ff.setFsnr(fsnr);
		ff.setFssj(GetTime.getTimeByFormat(DATE_FORMAT));
		ff.setXjzt(model.getXjzt());
		ff.setZtlb(model.getZtlb());
		String xjbh = UniqID.getInstance().getUniqIDHash();
		xjbh = xjbh.toUpperCase();
		ff.setXjbh(xjbh);
	    result = fxbservice.save(ff);
	    if(!result){
	    	response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.ZNXGL_XXHFSB)));
			return null;
	    }
	    model.setXjbh(xjbh);
	    result = service.save(model);
		String message = result ? MessageUtil.getText(
				MessageKey.ZNXGL_XXHFCG) : MessageUtil
				.getText(MessageKey.ZNXGL_XXHFSB);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}

}
