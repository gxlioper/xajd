/**
 * @部门:学工产品事业部
 * @日期：2014-6-24 上午09:38:17 
 */  
package com.zfsoft.xgxt.rcsw.txhd.xmjg;

import java.io.File;
import java.util.ArrayList;
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
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.txhd.dmwh.TxhdDmwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 团学活动活动结果管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-6-24 上午09:38:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TxhdXmjgAction extends SuperAction {
	//定义日常事务中日常行为常量可以从基本信息表中获取学生信息
	private static final String RCSWRCXW = "rcswqjgl";
	private BdpzService bdpzService = new BdpzService();
	private TxhdDmwhService dmwhService = new TxhdDmwhService();
	private List<HashMap<String,String>> jbxxList = null;
	private List<HashMap<String, String>> hdlbList = null;
	
	private static final String url = "rcsw_txhd_xmjgbase.do";
	
	/**
	 * 
	 * @描述:活动结果列表查询显示
	 * @作者：夏夏 [工号：1104]
	 * @日期：2014-6-24 上午10:01:35
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
	public ActionForward xmjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		TxhdXmjgService service = new TxhdXmjgService();
		CommService cs = new CommService();
		TxhdXmjgForm myForm =(TxhdXmjgForm)form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("rcsw_txhd_xmjgbase.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_txhd_xmjgbase.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmjgCx");
	}
	
	/**
	 * 
	 * @描述:活动增加
	 * @作者：夏夏 [工号：1104]
	 * @日期：2014-6-24 下午14:04:35
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
	@SystemLog(description="访问日常事务-团学活动-活动结果-增加")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		TxhdXmjgService service = new TxhdXmjgService();
		TxhdXmjgForm myForm =(TxhdXmjgForm)form;
		User user = getUser(request);
		
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String messageKey=null;
			myForm.setSqr(user.getUserName());
			if(!service.isAdd(myForm))
			{
				messageKey=MessageKey.SYS_SAVE_FAIL;
			}
			else{
			boolean result = service.save(myForm);
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
		
			return null;
	
		}
		//get student detail
		//学生基本信息
		String path = "rcsw_txhd_xmjg.do?method=add";
		request.setAttribute("path", path);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//学年 学期
		myForm.setXq(Base.currXq);
		myForm.setXn(Base.currXn);
		//活动类别
        hdlbList=dmwhService.getLblist();
        request.setAttribute("hdlbList", hdlbList);
		request.setAttribute("xnList", Base.getXnndList2());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("add");
	}

	
	
	/**
	 * 
	 * @描述:活动结果修改
	 * @作者：夏夏 [工号：1104]
	 * @日期：2014-6-24 下午12:50:35
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
	@SystemLog(description="访问日常事务-团学活动-活动结果-修改GUID:{guid}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		TxhdXmjgService service = new TxhdXmjgService();
		TxhdXmjgForm myForm =(TxhdXmjgForm)form;
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.update(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		TxhdXmjgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		
		//学生基本信息
		String path = "rcsw_txhd_xmjg.do?method=update";
		request.setAttribute("path", path);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//学年 学期
		request.setAttribute("xnList", Base.getXnndList2());
		request.setAttribute("xqList", Base.getXqList());
		//活动类别
		hdlbList=dmwhService.getLblist();
        request.setAttribute("hdlbList", hdlbList);
        request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("update");
	}
	/**
	 * 
	 * @描述:显示具体信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-06-24 下午17:18:05
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
		TxhdXmjgService service = new TxhdXmjgService();
		TxhdXmjgForm myForm =(TxhdXmjgForm)form;
		TxhdXmjgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
 		// 学生信息
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", service.getOneHdjgList(model.getGuid()));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("show");
	}
	
	/**
	 * 
	 * @描述:批量删除
	 * @作者：夏夏 [工号：1104]
	 * @日期：2014-06-24 下午15:50:35
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
	@SystemLog(description="访问日常事务-团学活动-活动结果-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXmjgService service = new TxhdXmjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.delete(values.split(","));
			if(null==mess||mess.length!=1){
				String message= MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:导出
	 * @作者：夏夏 [工号：1104]
	 * @日期：2014-06-24 下午18:50:35
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdXmjgService service = new TxhdXmjgService();
		TxhdXmjgForm model =(TxhdXmjgForm)form;
		//根据不同的审核类型 去自定义导出
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
	 * 
	 * @描述:学生活动申报表打印
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-7 上午08:48:13
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
	public ActionForward doPrintHdsbWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXmjgForm model =(TxhdXmjgForm)form;
		File wordFile = getWord(model, request);
		FileUtil.outputWord(response, wordFile);
	return null;
	}
	/**
	 * 
	 * @描述:学生活动申报表批量打印
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-7 上午08:48:13
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
	public ActionForward doPrintHdsbWordZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXmjgForm myForm =(TxhdXmjgForm)form;
		String value = request.getParameter("guid");
		if (StringUtils.isNotNull(value)) {
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0, n = values.length; i < n; i++) {
				myForm.setGuid(values[i]);
				File file = getWord(myForm, request);
				if(null!=file){
				files.add(file);
				}
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}

		return null;
	}
	private File getWord(TxhdXmjgForm myForm, HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		String guid = myForm.getGuid();
		TxhdXmjgService service = new TxhdXmjgService();
		HashMap<String, String> hdjgMap = service.getOneHdjgList(guid);// 活动申报信息
		File file = null;
		if(null==hdjgMap){
			return null;
		}
		String xckssj = StringUtils.isNotNull(hdjgMap.get("xckssj")) ? DateTranCnDate.fomartDateToCn(hdjgMap.get("xckssj"),FomartDateType.day)+hdjgMap.get("kshour"):"";
		String xcjssj = StringUtils.isNotNull(hdjgMap.get("xcjssj")) ? DateTranCnDate.fomartDateToCn(hdjgMap.get("xcjssj"),FomartDateType.day)+hdjgMap.get("kshour"):"";
		hdjgMap.put("xckssj", xckssj);
		hdjgMap.put("xcjssj", xcjssj);
		data.putAll(hdjgMap);
	
		String tbsj = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss");
		data.put("tbsj", DateTranCnDate.fomartDateToCn(tbsj,FomartDateType.day));
		file = FreeMarkerUtil.getWordFile(data,
				"classpath://templates//rcsw", "hdsbspb_12309.xml", hdjgMap.get("xm")
						+ "-" + hdjgMap.get("xmmc"));
		return file;

	}


}
