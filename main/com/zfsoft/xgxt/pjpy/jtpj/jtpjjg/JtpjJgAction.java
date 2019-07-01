/**
 * @部门:学工产品事业部
 * @日期：2014-5-5 上午10:33:39 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpjjg;

import java.io.File;
import java.util.ArrayList;
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
import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.gygl.sdftj.sdfTjService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.pjpy.jtpj.jtpjsq.JtpjSqService;
import com.zfsoft.xgxt.pjpy.jtpj.jtpzsz.JtpjSzService;
import common.Globals;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-5-5 上午10:33:39
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JtpjJgAction extends SuperAction {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");
	private static final String template_dir = "classpath://templates//jtpj";//模版路径
	
	private static final String url = "jtpjjgbase.do";
	
	/**
	 * 
	 * @描述:集体评奖列表查询显示
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
	 * @修改记录: 修改者名字-修改日期-修改内容
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
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		JtpjJgForm myForm = (JtpjJgForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("jtpjjgbase.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "jtpjjgbase.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jtpjjglist");
	}

	/**
	 * 
	 * @描述:批量删除
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
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
	@SystemLog(description = "访问评奖评优-集体评奖-集体评奖结果-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			Map<String, Object> map = service.delete(values.split(","));
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @描述: 集体评奖模块
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
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
	@SystemLog(description = "访问评奖评优-集体评奖-集体评奖结果-修改JGID:{jgid},SQXN:{sqxn},SQXQ:{sqxq},JXID:{jxid},SQLY:{sqly}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		JtpjJgForm myForm = (JtpjJgForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			JtpjSqService jtpjSqService=new JtpjSqService();
			//重复判断
			
			if(!("qt").equals(myForm.getJtpjdw())&&!jtpjSqService.checkIsNotExists(myForm.getJxid(), myForm.getPjjtid(),null,myForm.getJgid(),"jg")){
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_JTPJ_SQ_REPEAT));
				return null;
			}
			if(("qt").equals(myForm.getJtpjdw())&&!jtpjSqService.checkIsNotExists(myForm.getJxid(),null,myForm.getPjjtmc(),myForm.getJgid(),"jg")){
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_JTPJ_SQ_REPEAT));
				return null;
			}
			myForm.setSqr(user.getUserName());
			JtpjSqService jss=new JtpjSqService();
			myForm.setSqrylx(jss.getSqrlx(user.getUserType()));
			if(Base.xxdm.equals("10704")){
				myForm.setRdfs(myForm.getRdfs().trim());
			}
			boolean result = service.update(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		// 学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		myForm = service.getModel(myForm.getJgid());
		request.setAttribute("xqmc", service.getXqmc(myForm.getSqxq()));
		request.setAttribute("data", StringUtils.formatData(myForm));
		JtpjSzService jss = new JtpjSzService();
		request.setAttribute("jxList", jss.getJxList(myForm.getSqxn(),myForm.getSqxq(), "0",user));
		request.setAttribute("xxdm", Base.xxdm);
		if(Base.xxdm.equalsIgnoreCase("10704")){//西安科技大学个性化
			if(myForm.getJtpjdw().equalsIgnoreCase("bj")){
				request.setAttribute("bjpj", "true");
			}else{
				request.setAttribute("bjpj", "false");
			}
			request.setAttribute("fs",myForm.getRdfs());
		}
		return mapping.findForward("jtpjjgupdate");
	}

	/**
	 * 
	 * @描述: 获取修改默认信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 下午05:32:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward loadUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		String jgid = request.getParameter("jgid");
		JtpjJgForm myForm = service.getModel(jgid);
		response.getWriter().print(JSONObject.fromObject(myForm));
		return null;
	}

	/**
	 * 
	 * @描述:增加
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-17 上午10:44:10
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
	@SystemLog(description = "访问评奖评优-集体评奖-集体评奖结果-增加SQXN:{sqxn},SQXQ:{sqxq},JXID:{jxid},SQLY:{sqly}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		JtpjJgForm myForm = (JtpjJgForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			JtpjSqService jtpjSqService=new JtpjSqService();
			//重复判断
			if(!("qt").equals(myForm.getJtpjdw())&&!jtpjSqService.checkIsNotExists(myForm.getJxid(), myForm.getPjjtid(), null,null,"jg")){
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_JTPJ_SQ_REPEAT));
				return null;
			}
			if(("qt").equals(myForm.getJtpjdw())&&!jtpjSqService.checkIsNotExists(myForm.getJxid(),null,myForm.getPjjtmc(),null,"jg")){
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_JTPJ_SQ_REPEAT));
				return null;
			}
			myForm.setSqr(user.getUserName());
			JtpjSqService jss=new JtpjSqService();
			myForm.setSqrylx(jss.getSqrlx(user.getUserType()));
			if(Base.xxdm.equals("10704")){
				if(StringUtils.isNotNull(myForm.getRdfs())){
					myForm.setRdfs(myForm.getRdfs().trim());
				}
			}
			boolean result = service.save(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		myForm.setSqxn(Base.currXn);
		myForm.setSqxq(Base.currXq);
		
		// 学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("data", myForm);
		request.setAttribute("xxdm", Base.xxdm);
		JtpjSzService jss = new JtpjSzService();
		request.setAttribute("jxList", jss.getJxList(myForm.getSqxn(),myForm.getSqxq(), "0",user));
		this.saveToken(request);
		return mapping.findForward("jtpjjgadd");
	}

	/**
	 * 
	 * @描述:显示具体信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-17 下午05:23:05
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
	@SystemAuth(url = url)
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		JtpjJgForm myForm = (JtpjJgForm) form;
		myForm = service.getModel(myForm.getJgid());
		JtpjSzService jss = new JtpjSzService();
		myForm.setJxmc(jss.getModel(myForm.getJxid()).getJxmc());
		request.setAttribute("sqxqmc", service.getXqmc(myForm.getSqxq()));
		request.setAttribute("pdxqmc", service.getXqmc(myForm.getPdxq()));
		request.setAttribute("data", StringUtils.formatData(myForm));
		request.setAttribute("xxdm", Base.xxdm);
		if("10704".equalsIgnoreCase(Base.xxdm)){			
			if(myForm.getJtpjdw().equalsIgnoreCase("bj")){
				request.setAttribute("bjpj", "true");
			}else{
				request.setAttribute("bjpj", "false");
			}
		}
		return mapping.findForward("jtpjjgview");
	}

	/**
	 * 
	 * @描述: 加载班级评奖信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 下午01:57:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward loadBjpjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		User user = getUser(request);
		String bjdm = request.getParameter("bjdm");
		List<HashMap<String, String>> bjlist = service.getBjList(user);
		request.setAttribute("bjList", bjlist);
		// StringUtils.isNull(bjdm) 空不赋默认值
		if ("stu".equals(user.getUserType())) {
			if(null != bjlist && bjlist.size()>0){
				bjdm = bjlist.get(0).get("bjdm");
			}
		}
		JtpjSqService jss=new JtpjSqService();
		request.setAttribute("bjdm", bjdm);
		if(StringUtils.isNotNull(bjdm)){
			request.setAttribute("bjmc", new JtpjSqService().getBjmc(bjdm));
		}else{
			request.setAttribute("bjmc", "");
		}
		request.setAttribute("qss", jss.getQss(bjdm));
		request.setAttribute("bjrs", jss.getBjrs(bjdm));
		request.setAttribute("bzrList", jss.getBzrxx(bjdm));
		request.setAttribute("fdyList", jss.getFdyxx(bjdm));
		//是否温州大学
		request.setAttribute("iswzdx", Globals.XXDM_WZDX.equals(Base.xxdm)?"1":"0");
		if (VIEW.equals(request.getParameter("type"))) {
			String jgid = request.getParameter("jgid");
			JtpjJgForm myForm = service.getModel(jgid);
			request.setAttribute("data", myForm);
			return mapping.findForward("bjview");
		}
		return mapping.findForward("bj");
	}

	/**
	 * 
	 * @描述: 加载学院相关信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 下午01:56:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward loadXypjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		User user = getUser(request);
		String xydm = request.getParameter("xydm");
		List<HashMap<String, String>> xylist = service.getXyList(user);
		request.setAttribute("xyList", xylist);
		if (StringUtils.isNull(xydm) && null != xylist && xylist.size() > 0) {
			xydm = xylist.get(0).get("xydm");
		}
		request.setAttribute("xydm", xydm);
		JtpjSqService jss=new JtpjSqService();
		request.setAttribute("qss", jss.getXyQss(xydm));
		request.setAttribute("xylable", message.getMessage("lable.xb"));
		request.setAttribute("xyrs", jss.getXyrs(xydm));
		if (VIEW.equals(request.getParameter("type"))) {
			String jgid = request.getParameter("jgid");
			JtpjJgForm myForm = service.getModel(jgid);
			request.setAttribute("data", myForm);
			return mapping.findForward("xyview");
		}
		return mapping.findForward("xy");
	}
	
	public ActionForward loadld(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		sdfTjService sdfTjService=new sdfTjService();
		if (VIEW.equals(request.getParameter("type"))) {
			JtpjJgService service = new JtpjJgService();
			String jgid = request.getParameter("jgid");
			JtpjJgForm myForm = service.getModel(jgid);
			String lddm=myForm.getPjjtid().split("@@")[0];
			request.setAttribute("ldmc", sdfTjService.getLdmc(lddm));
			String qsh=myForm.getPjjtid().split("@@")[1];
			request.setAttribute("qsh", qsh);
			return mapping.findForward("qsview");
		}
		List<HashMap<String, String>> ldlist = sdfTjService.getLddmList();
		request.setAttribute("ldlist", ldlist);
		return mapping.findForward("qs");
	}
	
	public ActionForward loadOtherPjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		if (VIEW.equals(request.getParameter("type"))) {
			String jgid = request.getParameter("jgid");
			JtpjJgForm myForm = service.getModel(jgid);
			request.setAttribute("data", StringUtils.formatData(myForm));
			return mapping.findForward("qtview");
		}
		return mapping.findForward("qt");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgForm model = (JtpjJgForm) form;
		//根据不同的审核类型 去自定义导出
		JtpjJgService service = new JtpjJgService();
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
		
		
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
	
	/**
	 * @描述: 打印Word登记表（上海电机学院）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-4 上午11:30:24
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
	public ActionForward getDjbWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgForm model = (JtpjJgForm) form;
		File wordFile = getWord(model.getJgid());
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	//填充模版数据生成word文件（上海电机学院）
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	private File getWord(String jgid) throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		JtpjJgService jtpjJgService = new JtpjJgService();
		HashMap<String, String> djbMap = null;
		if ("11458".equals(Base.xxdm)){
			djbMap=jtpjJgService.getDjbInfo(jgid);
			if (djbMap != null){
				djbMap.put("sqly", HtmlUtil.xmlZy(djbMap.get("sqly")));
				djbMap.put("sqxn", djbMap.get("sqxn").replace("-", "/"));
				data.putAll(djbMap);
				String templateXmlPath = "xjjtdjb_11458.xml";
				File wordFile = FreeMarkerUtil.getWordFile(data, template_dir, templateXmlPath, djbMap.get("pjjtmc") + "-" + djbMap.get("jxmc"));
				return wordFile;
			}
		}else if ("11067".equals(Base.xxdm)){
			djbMap=jtpjJgService.getDjbInfo(jgid);
			if (djbMap != null){
				djbMap.put("sqly", HtmlUtil.xmlZy(djbMap.get("sqly")));
				djbMap.put("sqxn", djbMap.get("sqxn").replace("-", "/"));
				data.putAll(djbMap);
				String templateXmlPath = "xjbjtdjb_11067.xml";
				File wordFile = FreeMarkerUtil.getWordFile(data, template_dir, templateXmlPath, djbMap.get("pjjtmc") + "-" + djbMap.get("jxmc"));
				return wordFile;
			}
		}
		else if("10080".equals(Base.xxdm)){
			//河北工业大学个性化
			djbMap=jtpjJgService.gethbgydxDjb(jgid);//基本信息
			if (djbMap != null){
				djbMap.put("sqly", HtmlUtil.xmlZy(djbMap.get("sqly")));
				String bjdm = djbMap.get("bjdm");
				data.putAll(djbMap);
				File wordFile = FreeMarkerUtil.getWordFile(data, template_dir, djbMap.get("mbmc"), djbMap.get("pjjtmc") + "-" + djbMap.get("jxmc"));
				return wordFile;
			}
		}else{
			//登记表设置后进入此方法
			djbMap= jtpjJgService.getDjb(jgid);
			if(djbMap!=null){
				//登记表自动填充所需数据
				data.putAll(jtpjJgService.fillData(djbMap));
				djbMap.put("sqly", HtmlUtil.xmlZy(djbMap.get("sqly")));
				data.put("currY", DateUtils.getYear());//当前年
				data.put("currM", DateUtils.getMonth());//当前月
				data.put("currD",DateUtils.getDayOfMonth());//当前日
				String[] xnArr = djbMap.get("sqxn").split("-");
				data.put("qsxn", xnArr[0]);
				data.put("zhxn", xnArr[1]);
				//获取其他单位信息
				HashMap<String, String> dwMap = new JtpjSqService().getBjxgxx(djbMap.get("pjjtmc"));
				if("10876".equals(Base.xxdm)){
					data.put("xymc", dwMap.get("xymc"));
				}
				if(StringUtils.isNull(djbMap.get("mbmc"))){
					throw new SystemException(MessageKey.PJPY_BBDY_FAIL);
				}
				File wordFile = FreeMarkerUtil.getWordFile(data, template_dir, djbMap.get("mbmc"), djbMap.get("pjjtmc") + "-" + djbMap.get("jxmc"));
				return wordFile;
			}
		}
		return null;
	}
	
	/**
	 * @描述: 登记 表ZIP （上海电机学院）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-4 上午11:30:24
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
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getWord(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	//山东畜牧兽医职业学院个性化需求（先进班集体推荐表）
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getSdxmWord_xjbjt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		String[] values = value.split(",");
		JtpjJgService pjjgserice = new JtpjJgService();
		File wordFile = pjjgserice.getXmGxhDy_12947_xjbjt(values);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @描述:填充模版数据生成word文件（山东畜牧兽医职业学院）
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-6-5 下午04:51:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgid
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getWord_12947(String jgid) throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		JtpjJgService jtpjJgService = new JtpjJgService();
		HashMap<String, String> djbMap = jtpjJgService.getDjbInfo(jgid);
		if (djbMap != null){
			djbMap.put("sqly", HtmlUtil.xmlZy(djbMap.get("sqly")));
			djbMap.put("sqxn", djbMap.get("sqxn").replace("-", "/"));
			
			HashMap<String, String> rxrqMap = jtpjJgService.getRxrq(jgid);
			String rxrq = rxrqMap.get("rxrq");
			djbMap.put("rxrq", rxrq);
			data.putAll(djbMap);
			String templateXmlPath = "sdxmsy_12947_xjbjt.xml";
			File wordFile = FreeMarkerUtil.getWordFile(data, template_dir, templateXmlPath, djbMap.get("pjjtmc") + "-" + djbMap.get("jxmc"));
			return wordFile;
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:山东畜牧批量导出先进班集体登记表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-6-5 下午04:53:42
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
	public ActionForward getDjbZip_12947(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getWord_12947(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 山东畜牧导出先进班集体登记表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-6-5 下午05:19:29
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
	public ActionForward getDjbWord_12947(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgForm model = (JtpjJgForm) form;
		File wordFile = getWord_12947(model.getJgid());
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/** 
	 * @描述:是否为班级奖项(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-20 下午03:11:38
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
	public ActionForward isBjjx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgForm model = (JtpjJgForm) form;
		JtpjJgService service = new JtpjJgService();
		boolean result = service.isBjjx(model.getJxid());
		response.getWriter().print(result);
		return null;
	}
	
	
}
