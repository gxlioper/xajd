/**
 * @部门:学工产品事业部
 * @日期：2017-2-10 下午04:58:34 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.zcsh;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.dtjs.zzgxzc.zcsq.ZcsqSERVICE;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-2-10 下午04:58:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcshDO extends SuperAction<ZcshForm, ZcshSERVICE> {
	ZcshSERVICE service = new ZcshSERVICE();
	private final String ZCSQ ="zcsq";
	
	private static final String url = "dtjs_dzzgxsh.do";	//审核列表：dtjs_dzzgxsh.do
	
	/**
	 * 
	 * @描述: 党组织关系转出审核查询
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-13 上午09:14:15
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
	public ActionForward zcshCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZcshForm zcshform = (ZcshForm)form;
		if (QUERY.equalsIgnoreCase(zcshform.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcshform.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(zcshform, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "dtjs_dzzgxsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zcshcx");
	}
	
	/**
	 * 
	 * @描述: 单个审核
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-13 上午10:30:30
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
	@SystemLog(description="访问党团建设-组织关系转出-信息审核-审核XH:{xh},SZDZB:{szdzb},SFSN:{sfsn},JSDZZ:{jsdzz},SQDW:{sqdw},DFJZRQ:{dfjzrq},"
			+ "SFKJHYZM:{sfkjhyzm},SHJG:{shjg},JSXBH:{jsxbh},SHYJ:{shyj}")
	public ActionForward Dgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZcshForm zcshform = (ZcshForm)form;
		
		//保存
		if(SAVE.equalsIgnoreCase(zcshform.getType())){
			User user = getUser(request);
			ZcshSERVICE shService = TransactionControlProxy.newProxy(new ZcshSERVICE());
			//保存单个审核
			boolean result = false;
			try {
				result = shService.saveSh(zcshform, user);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				throw e;
			
			}
			return null;
		}
		
		//根据岗位id等判断是否显示介绍信编号，审核岗位为非最后一级显示，如果审核流程只经一个岗位，则第一级显示
		boolean isJsxbhShow = service.isJsxbhShow(zcshform);
		
		if (!StringUtil.isNull(zcshform.getXh()) && !SAVE.equalsIgnoreCase(zcshform.getType())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(zcshform.getXh());
			request.setAttribute("jbxx", xsjbxx);
			ZcshForm model = service.getModel(zcshform);
			BeanUtils.copyProperties(zcshform, model);
		}
		
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("shid", request.getParameter("shid"));
		request.setAttribute("dzbList",new ZcsqSERVICE().getDzbList());
		ZcsqSERVICE sqService = new ZcsqSERVICE();
		request.setAttribute("rs", sqService.ckZcsq(zcshform.getXh()));
		request.setAttribute("isJsxbhShow", isJsxbhShow);
		return mapping.findForward("dgsh");
	}
	
	
	/**
	 * 
	 * @描述: 撤销审核
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-13 上午10:47:20
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
	@SystemLog(description="访问党团建设-组织关系转出-信息审核-撤销（审核）SQID:{sqid},SHZT{shzt}")
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZcshForm model = (ZcshForm) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSqid(sqid);
		// 最后一级撤销
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 撤销审核
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-13 上午10:54:25
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
	@SystemLog(description="访问党团建设-组织关系转出-信息审核-撤销（审核）SHID:{shid},SPLC{splc}")
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcshForm model = (ZcshForm) form;
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setShlc(splc);
		model.setShid(shid);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		
		String cancelFlg = service.cxshnew(shxx.get("ywid"),model,user);
		// 审核撤销成功
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;

	}
	
	/**
	 * 
	 * @描述: 导出审核信息
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午04:01:20
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcshForm myForm = (ZcshForm)form;
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(myForm,
				user);// 查询出所有记录，不分页
		
		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}
