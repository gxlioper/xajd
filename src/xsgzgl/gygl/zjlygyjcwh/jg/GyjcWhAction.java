/**
 * @部门:学工产品事业部
 * @日期：2016-4-28 下午04:26:03 
 */  
package xsgzgl.gygl.zjlygyjcwh.jg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import xsgzgl.gygl.gyjldmgl.GyjldmglForm;
import xsgzgl.gygl.gyjldmgl.ZjlyGyglDao;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.jg.XyzsglService;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-4-28 下午04:26:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyjcWhAction extends SuperAction<GyjcWhForm, GyjcWhService> {
	private final String JBXXPZ ="zjlygygldmwh";
	private GyjcWhService service = new  GyjcWhService();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	private static final String url = "gygl_zjly_jcxxwh.do";
	
	/**
	 * 查询
	 */
	@SystemAuth(url = url)
	public ActionForward getGyjcWhCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjcWhForm model = (GyjcWhForm) form;
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
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 增加
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjcWhForm model = (GyjcWhForm) form;
	    User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JBXXPZ);
		request.setAttribute("jbxxList", jbxxList);
		String path = "zjly_jcxxwh.do?method=add";
		request.setAttribute("path", path);
		List<HashMap<String, String>> jclblist  = service.getJclbList();
		request.setAttribute("jclblist", jclblist);
		request.setAttribute("xnlist", Base.getXnndList());
		request.setAttribute("xqlist", Base.getXqList());
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		//其他信息配置
		return mapping.findForward("add");
	}
	
	/**
	 * 结果保存
	 */
	
	public ActionForward savejg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjcWhForm model = (GyjcWhForm) form;
		
		boolean result = false;
		String message=null;
	    User user = getUser(request);
	    GyjldmglForm gyjlform = new ZjlyGyglDao().getModel(model.getJcdm());
		model.setFz(gyjlform.getFz());
		if(model.getType().equals("save")){
			result = service.runInsert(model);
		}else if(model.getType().equals("update")){
			result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 结果查看
	 */
	@SystemAuth(url = url)
	public ActionForward jgView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjcWhForm myForm = (GyjcWhForm) form;
		GyjcWhForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JBXXPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("jg", model);
		GyjldmglForm gyjlform = new ZjlyGyglDao().getModel(model.getJcdm());
		request.setAttribute("jcdmxx", gyjlform);
		request.setAttribute("jkf", gyjlform.getLb().equalsIgnoreCase("jf")?"加分项":"扣分项");
		for (HashMap<String, String> hashMap : Base.getXqList()) {
			if(hashMap.get("xqdm").equals(model.getXq())){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		return mapping.findForward("view");
	}
	
	/**
	 * 结果修改
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjcWhForm myForm = (GyjcWhForm) form;
		GyjcWhForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JBXXPZ);
		request.setAttribute("jbxxList", jbxxList);
		String path = "zjly_jcxxwh.do?method=editJg";
		request.setAttribute("path", path);
		List<HashMap<String, String>> jclblist  = service.getJclbList();
		request.setAttribute("jclblist", jclblist);
		request.setAttribute("xn", model.getXn());
		for (HashMap<String, String> hashMap : Base.getXqList()) {
			if(hashMap.get("xqdm").equals(model.getXq())){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		return mapping.findForward("edit");
	}
	
	/**
	 * 结果删除
	 */
	public ActionForward deljg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 结果导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GyjcWhForm model = (GyjcWhForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// 查询出所有记录，不分页

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
	
}
