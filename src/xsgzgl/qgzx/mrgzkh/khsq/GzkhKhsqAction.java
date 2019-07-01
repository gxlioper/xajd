/**
 * @部门:学工产品事业部
 * @日期：2015-1-6 下午02:42:06 
 */
package xsgzgl.qgzx.mrgzkh.khsq;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.qgzx.mrgzkh.jcsz.GzkhJcszService;
import xsgzgl.qgzx.mrgzkh.khsh.GzkhKhshForm;
import xsgzgl.qgzx.mrgzkh.khsh.GzkhKhshService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 每日工作考核管理模块
 * @类功能描述: 每日工作考核-考核申请
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-6 下午02:42:06
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class GzkhKhsqAction extends SuperAction<GzkhKhsqForm, GzkhKhsqService> {
	private final String CJFF="cjff";
	private GzkhKhsqService service = new GzkhKhsqService();
	
	private static final String url = "qgzx_mrgzkh_khsq.do";

	/**
	 * 
	 * @描述:每日工作考核申请列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-6 下午03:04:28
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
	public ActionForward getKhsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhsqForm model = (GzkhKhsqForm) form;
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
		GzkhJcszService  jcszService = new GzkhJcszService();
		String[] sqshkg = jcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		String path = "qgzx_mrgzkh_khsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("khsqList");
	}
	/**
	 * 
	 * @描述:每日工作考核申请增加
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-6 下午04:06:41
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
	public ActionForward addKhsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhsqForm model = (GzkhKhsqForm) form;
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
		service.setXsGwxx(request,model);
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CJFF);
		List<HashMap<String, String>> gzsjList = new OptionUtil().getOptions(OptionUtil.GZKH_SJXZ);
		request.setAttribute("gzsjList", gzsjList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("rq", DateUtils.getCurrDate());
		String path = "mrgzkhKhsq.do?method=addKhsq";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("khsq");
	}
	/**
	 * 
	 * @描述:每日工作考核申请修改
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-6 下午04:07:06
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
	public ActionForward editKhsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhsqForm myForm = (GzkhKhsqForm) form;
		GzkhKhsqForm model = service.getModel(myForm);
		if(null!=model){
			service.setXsGwxx(request,model);
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
		request.setAttribute("gzsjList", gzsjList);
		request.setAttribute("jbxxList", jbxxList);
		String path = "mrgzkhKhsq.do?method=editKhsq";
		request.setAttribute("path", path);
		GzkhJcszService  jcszService = new GzkhJcszService();
		String[] sqshkg = jcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		return mapping.findForward("editKhsq");
	}
	/**
	 * 
	 * @描述:考核申请查看
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 上午11:32:02
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
	public ActionForward khsqView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhsqForm model = (GzkhKhsqForm) form;
		GzkhKhshService khShservice = new GzkhKhshService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		// 加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		GzkhKhshForm shForm = new GzkhKhshForm();
		shForm.setSqid(model.getSqid());
		request.setAttribute("rs", StringUtils.formatData(khShservice.getKhshInfo(shForm)));
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CJFF);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("shzt", model.getShzt());
		return mapping.findForward("viewKhsq");

	}
	/**
	 * 
	 * @描述:考核申请保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 上午10:18:49
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
	@SystemLog(description = "访问勤工助学-勤工每日工作考核管理-每日工作考核填写-增加-XH:{xh},YRDW:{yrdw},GS:{gs},GWDM:{gwdm},GZDD:{gzdd},GZRQ:{gzrq},gzkssj:{gzkssj},GZJSSJ:{gzjssj},GZNR:{gznr}")
	public ActionForward saveKhsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhsqForm model = (GzkhKhsqForm) form;
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		if (service.isHaveSqJl(model,"add")) {// 关联性
			String message = MessageUtil.getText(MessageKey.QGZX_MRGZKH_REPEAT,model.getGzrq());;
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		String message = service.checkGs(model.getXh(),model.getXn(),model.getGs(),model.getGzrq(),model.getCjbz(),model.getSqid(),model.getGwdm());
		if(!"true".equals(message)){
			 response.getWriter().print(getJsonMessage(message));
			 return null;
		}
		boolean result = service.saveKhsq(model, getUser(request));
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:考核修改保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 下午02:33:27
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
	@SystemLog(description = "访问勤工助学-勤工每日工作考核管理-每日工作考核填写-增加-XH:{xh},YRDW:{yrdw},GS:{gs},GWDM:{gwdm},GZDD:{gzdd},GZRQ:{gzrq},gzkssj:{gzkssj},GZJSSJ:{gzjssj},GZNR:{gznr},SQID:{sqid}")
	public ActionForward saveEditKhsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhsqForm model = (GzkhKhsqForm) form;
		String message=null;
		if (!"tj".equalsIgnoreCase(model.getType())&&service.isHaveSqJl(model,"update")) {// 关联性
			message = MessageUtil.getText(MessageKey.QGZX_MRGZKH_REPEAT,model.getGzrq());;
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
			
		if(!"tj".equalsIgnoreCase(model.getType())&&!"true".equals(service.checkGs(model.getXh(),model.getXn(),model.getGs(),model.getGzrq(),model.getCjbz(),model.getSqid(),model.getGwdm()))){
			message= service.checkGs(model.getXh(),model.getXn(),model.getGs(),model.getGzrq(),model.getCjbz(),model.getSqid(),model.getGwdm());
			 response.getWriter().print(getJsonMessage(message));
			 return null;
		}
		boolean result = service.saveEditKhsq(request,model);
		String messageKey = null;
		if("tj".equalsIgnoreCase(model.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}else {
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:删除申请
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 下午02:35:38
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
	@SystemLog(description = "访问勤工助学-勤工每日工作考核管理-每日工作考核填写-删除VALUES:{values}")
	public ActionForward delKhsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// 删除
			int num = service.runDelete(values.split(","));
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", num + "");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 
	 * @描述:撤销申请
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 下午03:03:21
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
	public ActionForward cancelKhsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			GzkhKhsqForm model = new GzkhKhsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:加载岗位
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 上午10:47:51
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
	public ActionForward getGwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhsqForm model = (GzkhKhsqForm)form;
		List<HashMap<String, String>> list = service.getGwxx(model);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	/**
	 * 
	 * @描述:获取在岗学生
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 上午10:14:34
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
	public ActionForward showGzkhStudents(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		GzkhKhsqForm model = (GzkhKhsqForm) form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getGzkhStuList(
				model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	/**
	 * 
	 * @描述:数据导出
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-9 上午11:18:26
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
		GzkhKhsqForm model = (GzkhKhsqForm) form;

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
