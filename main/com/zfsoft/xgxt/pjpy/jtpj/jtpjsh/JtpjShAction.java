/**
 * @部门:学工产品事业部
 * @日期：2014-5-4 下午02:29:50 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpjsh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.pjpy.jtpj.jtpjsq.JtpjSqForm;
import com.zfsoft.xgxt.pjpy.jtpj.jtpzsz.JtpjSzService;
import com.zfsoft.xgxt.rcsw.lstd.lstdsh.LstdshForm;
import com.zfsoft.xgxt.rcsw.lstd.lstdsh.LstdshService;
import com.zfsoft.xgxt.rcsw.qjgl.qjsh.QjshForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjsh.QjshService;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-5-4 下午02:29:50
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JtpjShAction extends SuperAction {
	
	private static final String url = "jtpjshbase.do";
	
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjShservice service = new JtpjShservice();
		JtpjShForm myForm = (JtpjShForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("jtpjshbase.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "jtpjshbase.do");
		FormModleCommon.commonRequestSet(request);
		//默认当前学年学期
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("jtpjshlist");
	}
	/**
	 * 
	 * @描述:审核
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-11 下午03:35:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward jtpjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjShservice service = new JtpjShservice();
		JtpjShForm myForm = (JtpjShForm) form;
		User user = getUser(request);
		if (SAVE.equals(myForm.getType())) {
			// 保存单个审核
			boolean result = service.saveSh(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		JtpjSqForm model = service.getModel(myForm);
		JtpjSzService jss = new JtpjSzService();
		model.setJxmc(jss.getModel(model.getJxid()).getJxmc());
		request.setAttribute("xqmc", service.getXqmc(model.getSqxq()));
		request.setAttribute("gwid", myForm.getGwid());
		request.setAttribute("data", StringUtils.formatData(model));
		request.setAttribute("xxdm", Base.xxdm);
		if(Base.xxdm.equalsIgnoreCase("10704")){//西安大学个性化审核时增加评定分数
			model.setRdfs(service.getPdfs(myForm));
			myForm.setRdfs(service.getPdfs(myForm));
			if(model.getJtpjdw().equalsIgnoreCase("bj")){
				request.setAttribute("bjpj", "true");
			}else{
				request.setAttribute("bjpj", "false");
			}
		}		
		return mapping.findForward("jtpjsh");
	}
	
	public ActionForward jtpjPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtpjShservice service = new JtpjShservice();
		JtpjShForm model = (JtpjShForm) form;
		User user = getUser(request);
		if("save".equalsIgnoreCase(model.getType())){
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("jtpjPlsh");
		
	}
	
	
	@SystemAuth(url = url)
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjShservice service = new JtpjShservice();
		JtpjShForm myForm = (JtpjShForm) form;
		JtpjSqForm model = service.getModel(myForm);
		JtpjSzService jss = new JtpjSzService();
		model.setJxmc(jss.getModel(model.getJxid()).getJxmc());
		request.setAttribute("xqmc", service.getXqmc(model.getSqxq()));
		request.setAttribute("data", StringUtils.formatData(model));
		return mapping.findForward("jtpjshview");
	}
	/**
	 * 
	 * @描述:撤销审核
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-11 下午03:35:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjShForm myForm = (JtpjShForm) form;
		JtpjShservice service = new JtpjShservice();
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(myForm.getShid());	
		// 业务回滚
		boolean result = service.cancel(myForm.getSplcid(), shxx.get("ywid"));
		// 业务回滚成功
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	
	/**
	 * @description	： 集体评奖获奖名单导出（南京高等职业技术学校）
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-9 下午04:47:49
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtpjmddc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjShForm myForm = (JtpjShForm) form;
		SearchModel searchModel = myForm.getSearchModel();
		
		String[] searchTjXn = searchModel.getSearch_tj_xn();
		String[] searchTjXq = searchModel.getSearch_tj_xq();
		String[] searchTjJxmc = searchModel.getSearch_tj_jxmc();
		String path = searchModel.getPath();
		
		SearchModel searchModel2 = new SearchModel();
		searchModel2.setSearch_tj_xn(searchTjXn);
		searchModel2.setSearch_tj_xq(searchTjXq);
		searchModel2.setSearch_tj_jxmc(searchTjJxmc);
		searchModel2.setPath(path);
		
		myForm.setSearchModel(searchModel2);
		
		JtpjShservice service = new JtpjShservice();
		List<File> files = service.getDcFileList(myForm);//获取获奖班级表彰名单exl文件
		if(files.size()>1){
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);					
		}else if(files.size()==1){
			FileUtil.outputExcel(response, files.get(0));
		} 
		return null;
	}
	
	/**
	 * @description	： 验证集体评奖导出
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-10 下午05:04:34
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yzjtpjmddc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		JtpjShForm myForm = (JtpjShForm) form;
		SearchModel searchModel = myForm.getSearchModel();
		
		String[] searchTjXn = searchModel.getSearch_tj_xn();
		String[] searchTjXq = searchModel.getSearch_tj_xq();
		String[] searchTjJxmc = searchModel.getSearch_tj_jxmc();
		String path = searchModel.getPath();
		
		SearchModel searchModel2 = new SearchModel();
		searchModel2.setSearch_tj_xn(searchTjXn);
		searchModel2.setSearch_tj_xq(searchTjXq);
		searchModel2.setSearch_tj_jxmc(searchTjJxmc);
		searchModel2.setPath(path);
		
		myForm.setSearchModel(searchModel2);
		
		JtpjShservice service = new JtpjShservice();
		boolean result = service.checkJtpjshdc(myForm);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("result", result);
		JSONObject fromBean = JSONObject.fromBean(map);
		response.getWriter().print(fromBean);
		return null;
		
		
	}
}
