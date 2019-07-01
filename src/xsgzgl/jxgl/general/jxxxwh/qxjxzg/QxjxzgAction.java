/**
 * @部门:学工产品事业部
 * @日期：2016-8-30 下午01:36:32 
 */  
package xsgzgl.jxgl.general.jxxxwh.qxjxzg;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.jxgl.general.jxxxwh.JxglJxxxwhService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 军训管理
 * @类功能描述: 取消军训资格  
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-8-30 下午01:36:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QxjxzgAction extends SuperAction<QxjxzgForm, QxjxzgService>{
	
	private static final String RCSWRCXW = "rcswqjgl";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	private QxjxzgService service = new QxjxzgService();
	
	private static final String url = "jxgl_jxxxwh_qxjxzg.do";
	
	/**
	 * 
	 * @描述: 查询
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-31 上午10:14:58
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
	public ActionForward qxjxzgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		QxjxzgForm model = (QxjxzgForm) form;
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
		
		String path = "jxgl_jxxxwh_qxjxzg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("qxjxzgList");
		
		
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-31 上午10:14:52
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
	public ActionForward viewJxxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		QxjxzgForm myForm = (QxjxzgForm) form;
		QxjxzgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		// 学生信息
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		JxglJxxxwhService  jxxxwhService = new JxglJxxxwhService();
		HashMap<String,String> jxxxMap = jxxxwhService.getJxxxwhModel();
		request.setAttribute("jxxxMap", StringUtils.formatData(jxxxMap));
		request.setAttribute("data", StringUtils.formatData(model));
		
		return mapping.findForward("viewJxxx");
	}
	
	/**
	 * 
	 * @描述: 导出
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-31 上午10:50:08
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
		
		QxjxzgForm model = (QxjxzgForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//查询出所有记录，不分页
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
