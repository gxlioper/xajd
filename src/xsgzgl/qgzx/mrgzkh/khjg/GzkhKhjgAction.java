package xsgzgl.qgzx.mrgzkh.khjg;

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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

public class GzkhKhjgAction extends SuperAction<GzkhKhjgForm, GzkhKhjgService> {
	private final String CJFF="cjff";
	private GzkhKhjgService service = new GzkhKhjgService();
	
	private static final String url = "qgzx_mrgzkh_khjg.do";

	/**
	 * 每日工作考核结果列表
	 */
	@SystemAuth(url = url)
	public ActionForward getKhjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhjgForm model = (GzkhKhjgForm) form;
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
		String path = "qgzx_mrgzkh_khjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("khjgList");
	}
	/**
	 * 每日工作考核结果增加
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addKhjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhjgForm model = (GzkhKhjgForm) form;
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
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CJFF);
		List<HashMap<String, String>> gzsjList = new OptionUtil().getOptions(OptionUtil.GZKH_SJXZ);
		List<HashMap<String,String>> yrdwList = service.getYrbm(model);
		if(yrdwList.size() > 0){
			model.setYrdw(yrdwList.get(0).get("bmdm"));
		}
		List<HashMap<String,String>> gwList = service.getGwxx(model);
		request.setAttribute("gwList", gwList);
		request.setAttribute("yrdwList", yrdwList);
		request.setAttribute("gzsjList", gzsjList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		String path = "mrgzkhKhjg.do?method=addKhjg";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("khjg");
	}
	/**
	 * 考核结果查看
	 */
	@SystemAuth(url = url)
	public ActionForward khjgView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhjgForm myForm = (GzkhKhjgForm) form;
		GzkhKhjgForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CJFF);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("gzkhKhjgXx", StringUtils.formatData(model));
		return mapping.findForward("viewKhjg");
	}
	/**
	 * 考核结果保存
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问勤工助学-勤工每日工作考核管理-每日工作考核结果-增加XH:{xh},YRDW:{yrdw},GS:{gs},GWDM:{gwdm},GZDD:{gzdd},GZRQ:{gzrq},GZKSSJ:{gzkssj},GZJSSJ:{gzjssj},GZNR:{gznr},ID:{id}")
	public ActionForward saveKhjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		GzkhKhjgForm model = (GzkhKhjgForm) form;
		boolean result = false;
		String message=null;
		// 判断当前岗位是否有填写记录
		boolean isExist = service.checkExistForSave(model);
		if (isExist) {
			message = MessageUtil.getText(MessageKey.QGZX_MRGZKH_REPEAT,model.getGzrq());;
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		if ("update".equals(model.getType())){
			if(!"true".equals(service.checkZjeAndGsJGUp(model.getXh(),model.getXn(),model.getGs(),model.getGzrq(),model.getCjbz(),model.getId(),model.getGwdm()))){
				message= service.checkZjeAndGsJGUp(model.getXh(),model.getXn(),model.getGs(),model.getGzrq(),model.getCjbz(),model.getId(),model.getGwdm());
				 response.getWriter().print(getJsonMessage(message));
				 return null;
			}
		}else {
			if(!"true".equals(service.checkZjeAndGsJG(model.getXh(),model.getXn(),model.getGs(),model.getGzrq(),model.getCjbz(),null,model.getGwdm()))){
				message= service.checkZjeAndGsJG(model.getXh(),model.getXn(),model.getGs(),model.getGzrq(),model.getCjbz(),null,model.getGwdm());
				 response.getWriter().print(getJsonMessage(message));
				 return null;
			}
		}
		result = service.saveKhjg(model, getUser(request));
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 每日工作考核结果修改
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editKhjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhjgForm myForm = (GzkhKhjgForm) form;
		GzkhKhjgForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CJFF);
		List<HashMap<String, String>> gzsjList = new OptionUtil().getOptions(OptionUtil.GZKH_SJXZ);
		List<HashMap<String,String>> yrdwList = service.getYrbm(model);
		List<HashMap<String,String>> gwList = service.getGwxx(model);
		request.setAttribute("gwList", gwList);
		request.setAttribute("yrdwList", yrdwList);
		request.setAttribute("gzsjList", gzsjList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("gzkhKhjgXx", StringUtils.formatData(model));
		String path = "mrgzkhKhjg.do?method=editKhjg";
		request.setAttribute("path", path);
		return mapping.findForward("editKhjg");
	}
	/**
	 * 每日工作考核结果删除
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问勤工助学-勤工每日工作考核管理-每日工作考核结果-删除VALUES:{values}")
	public ActionForward delKhjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GzkhKhjgService service = new GzkhKhjgService();
		
		//获得id
		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			// 重新计算薪酬 begin
			GzkhKhjgForm myForm = new GzkhKhjgForm();
			for (String id : ids) {
				myForm.setId(id);
				GzkhKhjgForm model = service.getModel(myForm);
				String[] gwxx = model.getGwdm().split(",");
				model.setGwdm(gwxx[0]);
				model.setXn(gwxx[1]);
				service.xsCjxxJs(model);
			}
			// 重新计算薪酬 end
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
	 * 每日工作考核结果导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GzkhKhjgForm model = (GzkhKhjgForm) form;
		GzkhKhjgService service = new GzkhKhjgService();

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
