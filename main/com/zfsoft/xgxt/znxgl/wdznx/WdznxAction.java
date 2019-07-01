/**
 * @部门:学工产品事业部
 * @日期：2015-8-27 下午06:55:42 
 */  
package com.zfsoft.xgxt.znxgl.wdznx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.znxgl.fxbgl.FxbForm;
import com.zfsoft.xgxt.znxgl.sxbgl.SxbForm;
import com.zfsoft.xgxt.znxgl.sxbgl.SxbService;
import com.zfsoft.xgxt.znxgl.znxgl.ZnxglForm;
import com.zfsoft.xgxt.znxgl.znxgl.ZnxglService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2015-8-27 下午06:55:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WdznxAction extends SuperAction<WdznxForm, WdznxService> {
	WdznxService service = new WdznxService();
    private SxbService sxbservcie = new SxbService();
    private ZnxglService znxglservice = new ZnxglService();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	private static final String url = "znxgl_wdznx.do";
	
	@SystemAuth(url = url)
	public ActionForward getSjxlist(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		    WdznxForm model = (WdznxForm) form;
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
			String path = "znxgl_wdznx.do";
			request.setAttribute("path", path);
			FormModleCommon.commonRequestSet(request);
		    return mapping.findForward("getSjxList");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getFjxlist(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		    WdznxForm model = (WdznxForm) form;
			if (QUERY.equalsIgnoreCase(model.getType())) {
				// 生成高级查询对象
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				model.setSearchModel(searchModel);
				User user = getUser(request);
				// 查询
				List<HashMap<String, String>> resultList = service.getFjxList(model, user);
				
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			// 默认高级查询条件
			SearchModel searchModel = new SearchModel();
			request.setAttribute("searchTj", searchModel); 
			//为获取读取权限，将发件箱的path暂时置为和收件箱一样
			String path = "znxgl_wdznx.do";;
			request.setAttribute("path", path);
			FormModleCommon.commonRequestSet(request);
			//重新置path,获取页面高级查询条件
			path = "wdznx.do?method=getFjxlist";
			request.setAttribute("path", path);
		    return mapping.findForward("getFjxList");
	}
	
	//我的站内信 -写信（分为老师和学生,用usertype进行身份判断）
	public ActionForward xx(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		    WdznxForm model = (WdznxForm) form;
		    String usertype = getUser(request).getUserType();
		   //登陆身份是学生的，需要向页面置主题类别
		    if(usertype.equalsIgnoreCase("stu")){
		    	//获取主题类别list
		    	 List<HashMap<String, String>> ztlblist = service.getZtlbList();
		    	 request.setAttribute("ztlblist", ztlblist);
		    	 return mapping.findForward("xsxx");
		    }
		    return mapping.findForward("teaxx");
	}
	
	//发信
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveXX(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		    WdznxForm model = (WdznxForm) form;
		    //获取发送人编号，即为当前系统登录人员
		    User user = getUser(request);
		    String username =user.getUserName();
		    //获取副文本编辑器中的内容并进行转码
		    String flag = request.getParameter("flag");
			String fsnr = DealString.toGBK(request.getParameter("editorid"));
			model.setFsrbh(username);
			model.setFsnr(fsnr);
			//获取系统当前时间作为发送时间
			model.setFssj(GetTime.getTimeByFormat(DATE_FORMAT));
			boolean result = true;
			//判读登陆用户是老师还是学生，以此调用不同的发信方法
			if(!user.getUserType().equalsIgnoreCase("stu")){
				//获取接收人编号并用数组接受
				String[] jsrbh = request.getParameterValues("jsrbh");
				model.setJsrbhs(jsrbh);
				result = service.saveteaXX(model);
			}else{
				result = service.savestuXX(model);
			}
			
			
			
			//send jiguang   message  
			if (result && "1".equalsIgnoreCase(Base.getInitProperties().get("isOpen"))) {//open
				JgxxtsService js = new JgxxtsService();
				HashMap<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("content", "您收到一条关于'"+model.getXjzt()+"'的消息.");
				paramMap.put("newsCount", "1");
				paramMap.put("webUrl", Base.getInitProperties().get("znxdz"));
				paramMap.put("webName", "学工站内信");
				paramMap.put("tsry", username);
				String[] jsrbh = request.getParameterValues("jsrbh");
				for (String account : jsrbh) {
					paramMap.put("account", account);
					String msg = js.sendMsg(paramMap);
				}
				
			}
			
			
			String messageKey = result ? MessageKey.ZNXGL_XXFSCG
					: MessageKey.ZNXGL_XXFSSB;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		    return null;
	}
	

	//获取学生列表，用于收件人选择
	public ActionForward getStu(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		    WdznxForm model = (WdznxForm) form;
		    String xhArr = request.getParameter("xhArr");
		    if (QUERY.equalsIgnoreCase(model.getType())) {
				// 生成高级查询对象
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				model.setSearchModel(searchModel);
				model.setXharr(xhArr.split(","));
				User user = getUser(request);
				if("0".equals(request.getParameter("sffy"))){
				model.getPages().setPageSize(Integer.MAX_VALUE);
				}
				// 查询
				List<HashMap<String, String>> resultList = service.getXsxxList(model, user);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			SearchModel searchModel = new SearchModel();
			request.setAttribute("searchTj", searchModel); 
			request.setAttribute("xhArr", xhArr);
			String path = "wdznx.do?method=getStu";
			request.setAttribute("path", path);
			FormModleCommon.commonRequestSet(request);
		    return mapping.findForward("getStu");
	}
	
	//获取教师列表，用于收件人选择
	public ActionForward getTea(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		    WdznxForm model = (WdznxForm) form;
		    String teaArr = request.getParameter("teaArr");
		    if (QUERY.equalsIgnoreCase(model.getType())) {
				// 生成高级查询对象
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				model.setSearchModel(searchModel);
				model.setTeaarr(teaArr.split(","));
				User user = getUser(request);
				if("0".equals(request.getParameter("sffy"))){
					model.getPages().setPageSize(Integer.MAX_VALUE);
					}
				// 查询
				List<HashMap<String, String>> resultList = service.getTeaxxList(model, user);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			SearchModel searchModel = new SearchModel();
			request.setAttribute("searchTj", searchModel); 
			String path = "wdznx.do?method=getTea";
			request.setAttribute("path", path);
			request.setAttribute("teaArr", teaArr);
			FormModleCommon.commonRequestSet(request);
		    return mapping.findForward("getTea");
	}
	
	//收件箱查看
	public ActionForward sjxCk(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		    WdznxForm model = (WdznxForm) form;
		    boolean result = true;
		    if(model.getType().equals("wd")){
		        SxbForm sxb = new SxbForm();
		    	BeanUtils.copyProperties(sxb, StringUtils.formatData(model));
	    	    SxbForm sf =  sxbservcie.getModel(sxb);
			    sf.setJsrydbj("1");
			    result = sxbservcie.runUpate_sxbckjl(sf);
		    }
		    if(!result){
		    	String messageKey = MessageKey.ZNXGL_XJCKSB;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
		    }
		    HashMap<String, String>  XjckMap = service.XjckMap(model);
		    request.setAttribute("ztlb", XjckMap.get("ztlb"));
		    request.setAttribute("fsrxm", request.getParameter("fsrxm"));
		    request.setAttribute("xjzt", XjckMap.get("xjzt"));
		    request.setAttribute("jsrbh", model.getJsrbh());
		    request.setAttribute("xjbh", model.getXjbh());
		    request.setAttribute("fsnr", znxglservice.getFsnr_Clob(model.getXjbh()));
		    request.setAttribute("flag", "sjx");
		    return mapping.findForward("sjxck");
	}
	
	//发件箱查看
	public ActionForward fjxCk(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		    WdznxForm model = (WdznxForm) form;
		    HashMap<String, String>  XjckMap = service.XjckMap(model);
		    request.setAttribute("ztlb", XjckMap.get("ztlb"));
		    request.setAttribute("jsrxm", request.getParameter("jsrxm"));
		    request.setAttribute("xjzt", XjckMap.get("xjzt"));
		    request.setAttribute("fsnr", znxglservice.getFsnr_Clob(model.getXjbh()));
		    request.setAttribute("flag", "fjx");
		    return mapping.findForward("fjxck");
	}
	
	//删除收件箱记录
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delScSjxjl(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		    WdznxForm model = (WdznxForm) form;
		    String[] xjbhs =  request.getParameter("values").split(",");
		    User user = getUser(request);
			String username = user.getUserName();
		    int num = xjbhs.length;
		    boolean result = true;
		    if(xjbhs != null){
				    for(int i=0;i<num;i++){
				    	SxbForm sxb = new SxbForm();
				    	sxb.setJsrbh(username);
				    	sxb.setXjbh(xjbhs[i]);
				    	SxbForm sf =  sxbservcie.getModel(sxb);
				    	sf.setJsrscbj("1");
				    	result = sxbservcie.runUpate_sxbscjl(sf);
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
	
	//删除发件箱记录
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delScFjxjl(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		  WdznxForm model = (WdznxForm) form;
		  String[] xjbhs = request.getParameter("values").split(",");
		  User user = getUser(request);
		  String username = user.getUserName();
		  int num = xjbhs.length;
		  boolean result = true;
		    if(xjbhs != null){
				    for(int i=0;i<num;i++){
				    	WdznxForm fxb = new WdznxForm();
				    	fxb.setXjbh(xjbhs[i]);
				    	fxb.setFsrbh(username);
				    	WdznxForm wf =  service.getModel(fxb);
				    	wf.setFsrscbj("1");
				    	result = service.runUpdate(wf);
				    }
				    if(!result){
						response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.SYS_DEL_FAIL)));
						return null;
			    	}
		    }
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		  return null;
	}
	
	//信件回复
	public ActionForward xjHf(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		WdznxForm model = (WdznxForm) form;
		HashMap<String, String>  XjckMap = service.XjckMap(model);
		if(model.getJsrydbj().equals("0")){
			  SxbForm sxb = new SxbForm();
		      BeanUtils.copyProperties(sxb, StringUtils.formatData(model));
    	      SxbForm sf =  sxbservcie.getModel(sxb);
		      sf.setJsrydbj("1");
		      sxbservcie.runUpate_sxbckjl(sf);
		}
	    request.setAttribute("jsrxm", request.getParameter("fsrxm"));
	    request.setAttribute("fsrxm", request.getParameter("fsrxm"));
	    request.setAttribute("jsrbh", XjckMap.get("fsrbh"));
	    request.setAttribute("xjzt", XjckMap.get("xjzt"));
	    request.setAttribute("ztlb",  XjckMap.get("ztlb"));
	    request.setAttribute("fssj", XjckMap.get("fssj"));
	    request.setAttribute("fsnr", znxglservice.getFsnr_Clob(model.getXjbh()));
		return mapping.findForward("wdznxhf");
	}
	
	//回复保存
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward savexjHf(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		WdznxForm ff = (WdznxForm) form;
		String username = getUser(request).getUserName();
		ff.setFsrbh(username);
		String fsnr = DealString.toGBK(request.getParameter("editorid"));
		ff.setFsnr(fsnr);
		ff.setFssj(GetTime.getTimeByFormat(DATE_FORMAT));
		ff.setXjzt(ff.getXjzt());
		ff.setZtlb(ff.getZtlb());
		String xjbh = UniqID.getInstance().getUniqIDHash();
		xjbh = xjbh.toUpperCase();
		ff.setXjbh(xjbh);
	    boolean result = service.save(ff);
	    if(!result){
	    	response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.ZNXGL_XXHFSB)));
			return null;
	    }
	    SxbForm sxb = new SxbForm();
	    sxb.setXjbh(xjbh);
	    sxb.setJsrbh(ff.getJsrbh());
	    result = sxbservcie.save(sxb);
		String message = result ? MessageUtil.getText(
				MessageKey.ZNXGL_XXHFCG) : MessageUtil
				.getText(MessageKey.ZNXGL_XXHFSB);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}

	
	
}
