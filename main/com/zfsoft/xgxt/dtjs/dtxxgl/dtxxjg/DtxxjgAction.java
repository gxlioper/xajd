/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:03:38 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz.ShlcpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.Globals;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.dtjs.dtxxgl.DtxxglService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团信息管理模块
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:03:38
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class DtxxjgAction extends SuperAction {
	// 定义日常事务中日常行为常量可以从基本信息表中获取学生信息
	private static final String RCSWRCXW = "dtxxXsxxpz";
	private static List<HashMap<String, String>> jbxxList = null;
	
	private static final String url = "dtxxjgbase.do";

	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
	}

	/**
	 * 
	 * @描述:党团信息列表查询显示
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
	 */
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxjgService service = new DtxxjgService();
		CommService cs = new CommService();
		DtxxjgForm myForm = (DtxxjgForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("dtxxjgbase.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "dtxxjgbase.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dtxxjglb");
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
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxjgService service = new DtxxjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.delete(values.split(","));
			if (null == mess || mess.length != 2) {
				String message = MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", mess[0]);
			map.put("nodel", mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 
	 * @描述:同步党团信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-31 上午11:14:12
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
	public ActionForward tbdtxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxglService dtxxglService = new DtxxglService();
		String message = dtxxglService.tbgxzzmm() ? "同步更新成功！" : "同步更新失败！";
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		return null;
	}
	/**
	 * 
	 * @描述: 党团信息模块
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
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxjgService service = new DtxxjgService();
		DtxxjgForm myForm = (DtxxjgForm) form;
		DtxxjgForm model = service.getModel(myForm);
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMoreForDtgl(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String message = service.save(myForm);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		List<HashMap<String, String>> jdlist = service.getGrJdxx(myForm.getXh());
		request.setAttribute("jdlist", jdlist);
		// 页面不好获取size 且页面循环从0开始故-1
		request.setAttribute("size", jdlist.size() - 1);
		// 学生基本信息
		String path = "dtxxjg.do?method=update";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("dtxxjgxg");
	}

	/**
	 * 
	 * @描述:增加
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-17 上午10:49:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxjgService service = new DtxxjgService();
		DtxxjgForm myForm = (DtxxjgForm) form;

		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String messageKey = service.save(myForm);
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		List<HashMap<String, String>> jdlist = service.getGrJdxx(myForm.getXh());
		request.setAttribute("jdlist", jdlist);
		// 页面不好获取size 且页面循环从0开始故-1
		request.setAttribute("size", jdlist.size() - 1);
		// 学生基本信息
		String path = "dtxxjg.do?method=add";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("dtxxjgzj");
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
	 */
	@SystemAuth(url = url)
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxjgService service = new DtxxjgService();
		DtxxjgForm myForm = (DtxxjgForm) form;
		DtxxjgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		// 学生信息
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		List<HashMap<String, String>> jdlist = service.getGrJdxx(myForm.getXh());
		request.setAttribute("jdlist", jdlist);
		// 页面不好获取size 且页面循环从0开始故-1
		request.setAttribute("size", jdlist.size() - 1);
		// 学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", StringUtils.formatData(model));
		return mapping.findForward("dtxxjgck");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxjgForm model = (DtxxjgForm) form;
		// 根据不同的审核类型 去自定义导出
		DtxxjgService service = new DtxxjgService();
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList=null;
		String dcclbh = request.getParameter("dcclbh");
		if("13871".equals(Base.xxdm)){
			resultList = service.getExportList_13871(model,user);
		}else if("10698".equals(Base.xxdm)){
		    //西安交大导出4种模板
		    if(dcclbh.equals("dtxxjg_jjfz.do")){
                resultList = service.getJjfzList(model,user);
            }else if(dcclbh.equals("dtxxjg_fzdx.do")){
                resultList = service.getFzdxList(model,user);
            }else if(dcclbh.equals("dtxxjg_ybdy.do")){
                resultList = service.getYbdyList(model,user);
            }else if(dcclbh.equals("dtxxjg_zsdy.do")){
                resultList = service.getZsdyList(model,user);
            }
        }
		else{
			resultList = service.getAllList(model,user);// 查询出所有记录，不分页
		}
		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}

	/**
	 * 
	 * @描述:编辑或查看
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-28 下午12:39:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	
	public ActionForward editorView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxjgForm model = (DtxxjgForm) form;
		ShlcpzService ss=new ShlcpzService();
		boolean ishave=ss.isHaveSPlc(model.getJddm());
		if("13022".equals(Base.xxdm)&&("06".equals(model.getJddm())||"10".equals(model.getJddm()))){
			request.setAttribute("dxjy", "true");
		}
		
		//浙江商业个性化
		if(Globals.XXDM_ZJSYZYXY.equals(Base.xxdm)
				&&"05".equals(model.getJddm())){
			request.setAttribute("zjsydxjy", "true");
		}
		
		request.setAttribute("ishave",ishave);
		request.setAttribute("data",StringUtils.formatData(model));
		if (model.getType().equals("view")) {
			return mapping.findForward("view");
		}
		return mapping.findForward("edit");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward mcexport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxjgForm model = (DtxxjgForm) form;
		// 根据不同的审核类型 去自定义导出
		DtxxjgService service = new DtxxjgService();
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		File file = service.getExportFile(model, user);// 生成导出文件
		FileUtil.outputExcel(response, file);
		file.delete();
		return null;
	}
	
	/**
	 * 
	 * @描述: 重庆三峡医高专-预审发展对象情况介绍表
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-24 上午09:14:55
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
	public ActionForward getYsqs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		DtxxjgService service = new DtxxjgService();
		Map<String,Object> data = new HashMap<String,Object>();
		
		String xymc = request.getParameter("value");
		List<HashMap<String,String>> resultList = service.getDtxxYsfzList(xymc);
		data.put("dtxsxxList",resultList);
		data.put("xymc",xymc);
		// 当前年月日时分秒
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(new java.util.Date());
        data.put("y", time.trim().substring(0, 4));
        data.put("m", time.trim().substring(5, 7));
        data.put("d", time.trim().substring(8, 10));
		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//dtxx", "ysqs_14008.xml", "预审发展对象情况介绍表" );
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @描述: 重庆三峡医高专-预备党员
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-24 下午02:23:59
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
	public ActionForward getXsyb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		DtxxjgService service = new DtxxjgService();
		Map<String,Object> data = new HashMap<String,Object>();
		
		String xymc = request.getParameter("value");
		List<HashMap<String,String>> resultList = service.getDtxxYbdyList(xymc);
		data.put("dtxsxxList",resultList);
		data.put("xymc",xymc);
		// 当前年月日时分秒
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(new java.util.Date());
        data.put("y", time.trim().substring(0, 4));
        data.put("m", time.trim().substring(5, 7));
        data.put("d", time.trim().substring(8, 10));
		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//dtxx", "xsyb_14008.xml", "吸收预备党员情况介绍表" );
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @描述: 重庆三峡医高专-预备党员转正（正式党员）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-24 下午02:23:59
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
	public ActionForward getYbzz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		DtxxjgService service = new DtxxjgService();
		Map<String,Object> data = new HashMap<String,Object>();
		
		String xymc = request.getParameter("value");
		List<HashMap<String,String>> resultList = service.getDtxxZsdyList(xymc);
		data.put("dtxsxxList",resultList);
		data.put("xymc",xymc);
		// 当前年月日时分秒
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(new java.util.Date());
        data.put("y", time.trim().substring(0, 4));
        data.put("m", time.trim().substring(5, 7));
        data.put("d", time.trim().substring(8, 10));
		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//dtxx", "ybzz_14008.xml", "预备党员转正情况介绍表" );
		FileUtil.outputWord(response, wordFile);
		return null;
	}
}
