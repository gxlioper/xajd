/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午02:40:52 
 */
package com.zfsoft.xgxt.xpjpy.xmsz.xmwh;

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
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshService;
import com.zfsoft.xgxt.xpjpy.xmsz.rssz.RsszModel;
import com.zfsoft.xgxt.xpjpy.xmsz.rssz.RsszService;
import com.zfsoft.xgxt.xpjpy.xmsz.tzsz.TzszService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 项目维护
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-30 下午02:40:52
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XmwhAction extends SuperAction {

	private String messageKey;
	private static final String urlJxj = "xpj_xmwh.do?method=xmwhCx&xmxz=1&sindex=0";
	private static final String urlBz = "xpj_xmwh.do?method=xmwhCx&xmxz=2&sindex=1";
	
	/**
	 * 
	 * @描述:基本查询方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录: void 返回类型
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz})
	public ActionForward xmwhCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhModel myForm = (XmwhModel) form;
		XmwhService service = new XmwhService();
		User user = getUser(request);
		String xmxz = request.getParameter("xmxz");
		request.setAttribute("xmxz",xmxz);

		if (QUERY.equals(myForm.getType())){
			xmxz = request.getParameter("xmxz");
			myForm.setXmxz(xmxz);
			List<HashMap<String, String>> resultList = service
					.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		request.setAttribute("currXnXqmc", CsszService.getPjzq().get("pjzqmc")); // 当前学年学期

		String dateFormat = "yyyy-MM-dd HH:mm";
		request.setAttribute("currDate", service.getCurrTime(dateFormat));
		String path=null;
		if("2".equals(xmxz))
		{
			request.setAttribute("czpath",urlBz);
		}else{
			request.setAttribute("czpath",urlJxj);
		}

		if(!StringUtils.isNull(myForm.getCzfs())&&myForm.getCzfs().equals("xyrssz")){
			path = "xpj_xmwh.do?method=xmwhCx&czfs=xyrssz";
			RsszService rsszService = new RsszService();
			//浙大学院人数设置奖学金金额提示
			List<HashMap<String,String>> jxjList = rsszService.getJxjze(new RsszModel(), user);
			request.setAttribute("jxjjeMap", jxjList.get(0));
		}
		
		List<HashMap<String, String>> xmlxList = service.getnewXmlx(xmxz);// 项目类型
//		List<HashMap<String, String>> xmxzList = service.getXmxz();// 项目性质
		request.setAttribute("xmlxList", xmlxList);
//		request.setAttribute("xmxzList", xmxzList);
		request.setAttribute("path", path);
		request.setAttribute("czfs", myForm.getCzfs());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhCx");
	}

	/**
	 * 
	 * @描述:增加、修改方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录: void 返回类型
	 * @throws
	 */
	@SystemAuth( url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-基本设置-项目设置-增加或修改-XMDM：{xmdm},XMMC:{xmmc},LXDM:{lxdm},XZDM:{xzdm},XSXH:{xsxh},SHLC:{shlc},RSKZJB:{rskzjb},RSFPFS:{rsfpfs}")
	public ActionForward xmwhZjXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhModel myForm = (XmwhModel) form;
		XmwhService service = new XmwhService();
		String xmxz = request.getParameter("xmxz");
		request.setAttribute("xmxz",xmxz);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			
			if(service.isExistXssx(myForm)) {// 项目序号重复验证
				messageKey = MessageKey.PJPY_XSXH_EXIST;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			if (service.isRepeat(myForm)) {// 名称重复验证
				messageKey = MessageKey.PJPY_XMMC_EXIST;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			
			myForm.setXn(CsszService.getPjzq().get("xn"));
			myForm.setXq(CsszService.getPjzq().get("xq"));
			myForm.setXzdm(xmxz);
			boolean result = service.runInsert(myForm);
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		} else if (UPDATE.equalsIgnoreCase(myForm.getType())) {
			if (service.isRepeat(myForm)) {// 名称重复验证
				messageKey = MessageKey.PJPY_XMMC_EXIST;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			
			if(service.isExistXssx(myForm)) {// 项目序号重复验证
				messageKey = MessageKey.PJPY_XSXH_EXIST;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			
			if (service.isRalate(myForm)) {// 关联性
				messageKey = MessageKey.PJPY_XM_NOTUPDATE;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}

			String rsfpfsOld = request.getParameter("rsfpfsOld");
			RsszService rsszService = new RsszService();
			String xmdm = myForm.getXmdm();
			if (rsfpfsOld != null && !rsfpfsOld.equals(myForm.getRsfpfs())) {// 人数控制范围修改
				rsszService.runDeleteByXmdm(xmdm);
			}
			
			String shlcOld = request.getParameter("shlcOld");
			TzszService tzszService = new TzszService();
			if(shlcOld !=null && !shlcOld.equals(myForm.getShlc())){
				tzszService.runDeleteByXmdm(xmdm);
			}

			boolean result = service.runUpdate(myForm);
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		if(myForm.getXmdm() != null && !myForm.getXmdm().equals("")){
			// 是否有学生申请项目
			SqshService sqshService = new SqshService();
			boolean flag = sqshService.isExistsFlowData(myForm.getXmdm());
			request.setAttribute("spzt", flag);
		}
				
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("pjpy");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);

		List<HashMap<String, String>> xmlxList = service.getnewXmlx(xmxz);// 项目类型
//		List<HashMap<String, String>> xmxzList = service.getXmxz();// 项目性质
		request.setAttribute("xmlxList", xmlxList);
//		request.setAttribute("xmxzList", xmxzList);
		String path= null;
		if("2".equals(xmxz))
		{
			path= urlBz;
		}else{
			path= urlJxj;
		}

		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		XmwhModel model = service.getModel(myForm);
		
		if(Base.xxdm.equals("10704") && null != model){//西安科技大学增加是否优秀学生干部
			String sfyxgb = service.getSfyxgb(model.getXmdm());
			model.setSfyxgb(sfyxgb);
		}
		//培养层次
		request.setAttribute("pyccList",service.getPyccList());

		if (model != null) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}

		//自动生成序号
		if(StringUtils.isNull(myForm.getXsxh())){
			String xsxh = service.getXsxh(xmxz);
			myForm.setXsxh(xsxh);
		}
		return mapping.findForward("xmwhZjXg");

	}

	/**
	 * 
	 * @描述:根据审核流程，获取审批级别
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @throws
	 */
	public ActionForward xmwhShfw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		if (!StringUtil.isNull(value)) {
			XtwhShlcService xtwhShlc = new XtwhShlcService();
			List<HashMap<String, String>> spjbListByGnmk = xtwhShlc
					.getSpjbListByGnmk(value);// spgwid 为选择的审批流程代码值
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONArray.fromObject(spjbListByGnmk));
		}
		return null;

	}

	/**
	 * 
	 * @描述:删除方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-基本设置-项目设置-删除-VALUES：{values}")
	public ActionForward xmwhSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmwhService service = new XmwhService();

		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			// //判断合法性////
			if (service.isRalate(values)) {// 关联性
				messageKey = MessageKey.PJPY_XM_NOTDEL;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}

			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			if (result) {
				service.delRalate(values);// 删除所有的关联表
			}
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_SUCCESS);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.EXP_SYS_ERROR);
		}
		return null;

	}

	/**
	 * 
	 * @描述:时间开关
	 * @作者：ligl
	 * @日期：2013-8-8 上午11:43:20
	 * @修改记录:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-基本设置-项目设置-申请开关-XMDM：{xmdm}")
	public ActionForward xmwhSjkg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmwhModel myForm = (XmwhModel) form;
		XmwhService service = new XmwhService();
		String xmdm = request.getParameter("xmdm");
		String xmmc = service.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		String xmxz = request.getParameter("xmxz");
		request.setAttribute("xmxz",xmxz);

		if (UPDATE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		List<HashMap<String, String>> isnotList = new OptionUtil()
				.getOptions(OptionUtil.ISNOT);// 是否list
		request.setAttribute("kgList", isnotList);

		List<HashMap<String, String>> onoffList = new OptionUtil()
				.getOptions(OptionUtil.ONOFF);// 开启关闭
		request.setAttribute("onoffList", onoffList);
		String path = null;
		if("2".equals(xmxz))
		{
			path= urlBz;
		}else{
			path= urlJxj;
		}

		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		XmwhModel model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

		return mapping.findForward("xmwhSjkg");
	}

	/**
	 * 
	 * @描述:奖项复制
	 * @作者：ligl
	 * @日期：2013-8-14 上午10:55:14
	 * @修改记录:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-基本设置-项目设置-奖项复制-JXFZND：{jxfznd}")
	public ActionForward xmwhJxfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmwhModel myForm = (XmwhModel) form;
		XmwhService service = new XmwhService();
		String xmxz = request.getParameter("xmxz");
		request.setAttribute("xmxz",xmxz);
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> jxfzList = service.getJxfz();// 奖项复制列表
			JSONArray dataList = JSONArray.fromObject(jxfzList);
			response.getWriter().print(dataList);
			return null;
		} else if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String jxfznd = request.getParameter("jxfznd");
			boolean result = service.runJxfz(jxfznd);
			String messageKey = result ? MessageKey.PJPY_JXFZ_SUCCESS
					: MessageKey.PJPY_JXFZ_NOTJL;
			response.getWriter().print(getJsonResult(messageKey, result));
			return null;
		}

		CsszService csszService = new CsszService();
		String currXnXqmc = csszService.getModel().getZqmc();// 当前学年学期
		request.setAttribute("currXnXqmc", currXnXqmc);
		String path = null;
		if("2".equals(xmxz))
		{
			path= urlBz;
		}else{
			path= urlJxj;
		}

		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("xmwhJxfz");
	}
	
	
	/**
	 * 
	 * @描述:评奖项目导出
	 * @作者：cq [工号：785]
	 * @日期：2015-4-14 上午11:01:57
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
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmwhModel model = (XmwhModel) form;
		XmwhService service = new XmwhService();
		User user = getUser(request);
		String xmxz = request.getParameter("xmxz");
		model.setXzdm(xmxz);
		model.setXmxz(xmxz);
		
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
