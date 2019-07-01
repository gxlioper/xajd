/**
 * @部门:学工产品事业部
 * @日期：2015-7-6 上午09:52:08 
 */
package xsgzgl.jxgl.general.jxqjgl;

import java.io.File;
import java.util.ArrayList;
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
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.jxgl.general.jxxxwh.JxglJxxxwhService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.qjgl.qjlx.QjlxService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-7-6 上午09:52:08
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JxqjjgAction extends SuperAction<JxqjjgForm, JxqjjgService> {
	private static final String RCSWRCXW = "rcswqjgl";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	private JxglJxxxwhService  jxxxwhService = new JxglJxxxwhService();
	
	private static final String url = "jxgl_jxqjgl_qjjggl.do";
	
	/**
	 * 
	 * @描述:军训请假列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-6 上午10:11:42
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
	@SystemAuth(url = url)
	public ActionForward jxqjjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		JxqjjgService service = new JxqjjgService();
		CommService cs = new CommService();
		JxqjjgForm myForm = (JxqjjgForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("jxgl_jxqjgl_qjjggl.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "jxgl_jxqjgl_qjjggl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jxqjjgList");
	}
	/**
	 * 
	 * @描述:军训请假结果删除
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-6 上午11:32:48
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
	@SystemLog(description = "访问军训管理-军训请假管理-军训请假结果-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxqjjgService service = new JxqjjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
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
	 * 请假结果修改
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问军训管理-军训请假管理-军训请假结果-修改QJID:{qjid},XH:{xh},QJTS:{qjts},QJLX:{qjlx},QJKSSJ:{qjkssj},QJJSSJ:{qjjssj},QJSY:{qjsy}")
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxqjjgService service = new JxqjjgService();
		JxqjjgForm myForm = (JxqjjgForm) form;

		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		JxqjjgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

		request.setAttribute("qjlxList", service.getQjlx());
		
		// 学生基本信息
		String path = "jxqjjg.do?method=update";
		request.setAttribute("path", path);

		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String,String> jxxxMap = jxxxwhService.getJxxxwhModel();
		request.setAttribute("jxxxMap", StringUtils.formatData(jxxxMap));
		
		String jzmc = service.getJzmc(service.getJzid(), myForm.getXh());
		if(!(jzmc == "")) {
			request.setAttribute("jzmc", jzmc);
		}else {
			request.setAttribute("jzmc", "未分配");
		}
		
		// 学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("update");
	}
	/**
	 * 
	 * @描述:请假结果增加
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-7 上午09:59:32
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
	@SystemLog(description = "访问军训管理-军训请假管理-军训请假结果-增加XH:{xh},QJTS:{qjts},QJLX:{qjlx},QJKSSJ:{qjkssj},QJJSSJ:{qjjssj},QJSY:{qjsy}")
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxqjjgService service = new JxqjjgService();
		JxqjjgForm myForm = (JxqjjgForm) form;

		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("qjlxList", service.getQjlx());
		// get student detail
		// 学生基本信息
		String path = "jxqjjg.do?method=add";
		request.setAttribute("path", path);
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		// 学年 学期
		HashMap<String,String> jxxxMap = jxxxwhService.getJxxxwhModel();
		request.setAttribute("jxxxMap", jxxxMap);
		
		String jzmc = service.getJzmc(service.getJzid(), myForm.getXh());
		if(!(jzmc == "")) {
			request.setAttribute("jzmc", jzmc);
		}else {
			request.setAttribute("jzmc", "未分配");
		}
		
		myForm.setXq(Base.currXq);
		myForm.setXqmc(Base.getDqxqmc());
		myForm.setXn(Base.currXn);
		return mapping.findForward("add");
	}
	/**
	 * 
	 * @描述:请假结果查看
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-7 上午09:59:49
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
	@SystemAuth(url = url)
	public ActionForward showView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxqjjgService service = new JxqjjgService();
		JxqjjgForm myForm = (JxqjjgForm) form;
		JxqjjgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		// 学生信息
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 请假类型
		QjlxService qjlx = new QjlxService();
		request.setAttribute("qjlxmc", qjlx.getModel(model.getQjlx()).getQjlxmc());
		
		String jzmc = service.getJzmc(service.getJzid(), myForm.getXh());
		if(!(jzmc == "")) {
			request.setAttribute("jzmc", jzmc);
		}else {
			request.setAttribute("jzmc", "未分配");
		}
		
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", StringUtils.formatData(model));
		return mapping.findForward("show");
	}
	/**
	 * 
	 * @描述:结果导出
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-7 上午10:00:37
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxqjjgForm model = (JxqjjgForm) form;
		// 根据不同的审核类型 去自定义导出
		JxqjjgService service = new JxqjjgService();
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model, user);// 查询出所有记录，不分页

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
	 * @描述:请假结果单打印
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-7 上午10:00:55
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
	public ActionForward printQjjgb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxqjjgService service = new JxqjjgService();
		JxqjjgForm myForm = (JxqjjgForm) form;
		XsxxService xsxxService = new XsxxService();
		if (StringUtils.isNotNull(myForm.getQjid())) {
			List<File> files = new ArrayList<File>();
			String[] qjjgids = myForm.getQjid().split(",");
			for (String id : qjjgids) {
				myForm.setQjid(id);
				HashMap<String, String> qjjgMap = service.getQjjgForPrint(id);
				// 加载学生基本信息
				String jzmc = service.getJzmc1(qjjgMap.get("xh"));
				qjjgMap.put("jzmc", jzmc);
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(qjjgMap.get("xh"));
				File file = service.printForWord(xsjbxx, qjjgMap);
				files.add(file);
			}
			if (files != null && files.size() > 1) {
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			} else {
				FileUtil.outputWord(response, files.get(0));
			}
		}
		return null;
	}
	
	/*
	 * 病假单
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printBjd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxqjjgService service = new JxqjjgService();
		JxqjjgForm myForm = (JxqjjgForm) form;
		XsxxService xsxxService = new XsxxService();
		if (StringUtils.isNotNull(myForm.getQjid())) {
			List<File> files = new ArrayList<File>();
			String[] qjjgids = myForm.getQjid().split(",");
			for (String id : qjjgids) {
				myForm.setQjid(id);
				HashMap<String, String> qjjgMap = service.getQjjgForPrint(id);
				// 加载学生基本信息
				String jzmc = service.getJzmc1(qjjgMap.get("xh"));
				qjjgMap.put("jzmc", jzmc);
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(qjjgMap.get("xh"));

				File file = service.printForWordBjd(xsjbxx, qjjgMap);
				files.add(file);
			}
			if (files != null && files.size() > 1) {
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			} else {
				FileUtil.outputWord(response, files.get(0));
			}
		}
		return null;
	}
	
	

}
