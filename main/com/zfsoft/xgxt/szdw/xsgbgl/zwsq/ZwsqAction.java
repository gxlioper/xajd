/**
 * @部门:学工产品事业部
 * @日期：2013-7-24 下午4:15:34 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwsq;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;


/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部职务申请
 * @作者： zhangjw
 * @时间： 2013-8-8 下午2:30:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZwsqAction extends SuperAction {
	
	private ZwsqService service = new ZwsqService();
	private static final String  CJFF = "cjff";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "szdw_zwsq.do?method=zwsqList";
	/**
	 * @描述:职务申请列表
	 * @作者：zhangjw
	 * @日期：2013-8-8 下午2:31:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward zwsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZwsqForm myForm = (ZwsqForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "szdw_zwsq.do?method=zwsqList");
		return mapping.findForward("list");
	}
	/**
	 * @描述:职务申请
	 * @作者：zhangjw
	 * @日期：2013-8-8 下午2:32:12
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
	@SystemLog(description = "访问思政队伍-学生干部管理-学生干部职务申请-增加XH:{xh},LXDM:{lxdm},ZWID:{zwid}")
	public ActionForward zwSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwsqForm myForm = (ZwsqForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())||SUBMIT.equalsIgnoreCase(myForm.getType())){
			myForm.setSqr(user.getUserName());
			boolean result = service.zwsq(myForm);
			String messageKey = "";
			if(SUBMIT.equalsIgnoreCase(myForm.getType())){
				 messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			}else{
				messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			}
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//设置学生基本信息
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			szXsxx(request,user.getUserName());
			request.setAttribute("type", "update");
		}else{
			szXsxx(request,myForm.getXh());
		}
		String path = "szdw_zwsq.do?method=zwSq";
		request.setAttribute("path", path);
		request.setAttribute("model", StringUtils.formatData(myForm));
		return mapping.findForward("add");
	}
	
	@SystemAuth(url = url)
	private void szXsxx(HttpServletRequest request,String xh){
		//查询学生信息
		if(xh != null && !"".equals(xh)){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(CJFF);
		request.setAttribute("jbxxList", jbxxList);
	}
	/**
	 * @描述:验证职务申请
	 * @作者：zhangjw
	 * @日期：2013-8-8 下午2:32:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward yzZwsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZwsqForm myForm = (ZwsqForm) form;
		response.getWriter().print(service.yzZwsq(myForm));
		return null;
	}
	/**
	 * @描述:批量取消申请
	 * @作者：zhangjw
	 * @日期：2013-8-8 下午2:33:27
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
	public ActionForward qxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/*String sqids = request.getParameter("sqids");
		String[]sqid =null;
		if(sqids!=null && !"".equals(sqids)){
			sqid = sqids.split(",");
		}*/
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		
		//boolean result = service.qxsq(sqid);
		
		boolean result = service.cancelRecord(sqid,lcid);
		//boolean result = service.fdypxqxsq(sqid);
		if(result){
			//更新业务状态为'未提交'
			ZwsqForm model = new ZwsqForm();
			model.setSqid(sqid);
			ShlcDao splcdao = new ShlcDao();
			if(Integer.valueOf(splcdao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			model.setSplc(lcid);
			service.updateZwsq(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:删除
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-5 上午10:58:59
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
	@SystemLog(description = "访问思政队伍-学生干部管理-学生干部职务申请-删除SQIDS:{sqids}")
	public ActionForward deleteZwsqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqids = request.getParameter("sqids"); //带删除的sqids
		int isSuccess = 0 ; 
		if(StringUtils.isNotNull(sqids))
			isSuccess = service.runDelete(sqids.split(","));
		
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	/**
	 * 
	 * @描述:TODO(提交功能)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-11 下午02:22:51
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
	public ActionForward submitZwsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZwsqForm model = (ZwsqForm) form;
		String sqid = request.getParameter("values");
		String xh = request.getParameter("xh");
		String splcid = request.getParameter("splcid");
		model.setSqid(sqid);
		model.setXh(xh);
		model.setSplc(splcid);
		boolean result = service.submitZwsq(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:TODO(学生干部职务申请修改)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-2 下午05:25:14
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
	@SystemLog(description = "访问思政队伍-学生干部管理-学生干部职务申请-修改SQID:{sqid},XH:{xh},LXDM:{lxdm},ZWID:{zwid}")
	public ActionForward zwsqXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwsqForm model = (ZwsqForm) form;
		User user = getUser(request);
		if (UPDATE.equalsIgnoreCase(model.getType())||SUBMIT.equalsIgnoreCase(model.getType())){
			model.setSqr(user.getUserName());
			boolean result = service.zwsqXg(model);
			String messageKey = "";
			if(SUBMIT.equalsIgnoreCase(model.getType())){
				 messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			}else{
				 messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			}
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		
		//设置学生基本信息
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			szXsxx(request,user.getUserName());
			request.setAttribute("type", "update");
		}else{
			szXsxx(request,model.getXh());
		}
		
		String path = "szdw_zwsq.do?method=zwsqXg";
		request.setAttribute("path", path);
		ZwsqForm updatemyForm = service.getModel(model);
		request.setAttribute("model", StringUtils.formatData(updatemyForm));
		request.setAttribute("oldzwid", updatemyForm.getZwid());
		BeanUtils.copyProperties(model, StringUtils.formatData(updatemyForm));
		return mapping.findForward("zwsqXg");
		
	}
	
	/**
	 * @描述:下载备案表
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-13 下午02:55:37
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
	public ActionForward printXsgbbab(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DwwhService dwwhService = new DwwhService();
		PjjgService pjjgService = new PjjgService();
		XsxxService xsxxService = new XsxxService();
		XsxxglService xsxxglService = new XsxxglService();
		ZwsqForm myForm = (ZwsqForm) form;
		if(StringUtils.isNotNull(myForm.getXh())){
			List<File> files = new ArrayList<File>();
			String[] xhs = myForm.getXh().split(",");
			// 去除重复的学号
			Set<String> xhSet = new HashSet<String>();
			for (String xh : xhs) {
				xhSet.add(xh);
			}
			// 循环生成打印表
			for (String xh : xhSet) {
				//加载学生基本信息
				HashMap<String,String> xsjbxx = xsxxglService.getXsxxByXh(xh);
				Map<String, Object> data = new HashMap<String, Object>();
				
				// 当前担任的所有职务
				List<HashMap<String , String>> zwList = dwwhService.getAllZwListByXh(xh);
				for (HashMap<String, String> zwMap : zwList) {
					String lxmc = zwMap.get("lxmc");
					String zwmc = zwMap.get("zwmc");
					if("校级".equals(lxmc)){
						xsjbxx.put("xjzwmc", zwmc);
					}else if("院级".equals(lxmc)){
						xsjbxx.put("yjzwmc", zwmc);
					}else if("班级".equals(lxmc)){
						xsjbxx.put("bjzwmc", zwmc);
					}
				}
				//出生日期
				String csny = DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month);
				csny = (csny == null) ? "" : csny;
				xsjbxx.put("csny_num", csny.replaceAll("年", ".").replaceAll("月", ""));// 1988.9
				// 家庭成员信息
				List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
				for (HashMap<String, String> jtcyxxMap : jtcyxxList) {
					String jtcygxmc = jtcyxxMap.get("jtcygxmc"); // 与本人关系
					String jtcyxm = jtcyxxMap.get("jtcyxm"); // 姓名
					String zzmmmc = jtcyxxMap.get("zzmmmc"); // 政治面貌
					String jtcygzdz = jtcyxxMap.get("jtcygzdz"); // 工作单位及地址
					if("父亲".equals(jtcygxmc)){
						xsjbxx.put("fqjtcyxm", jtcyxm);
						xsjbxx.put("fqzzmmmc", zzmmmc);
						xsjbxx.put("fqjtcygzdz", jtcygzdz);
					}else if("母亲".equals(jtcygxmc)){
						xsjbxx.put("mqjtcyxm", jtcyxm);
						xsjbxx.put("mqzzmmmc", zzmmmc);
						xsjbxx.put("mqjtcygzdz", jtcygzdz);
					}
				}
//				// 学历社会经历信息 【修改：暂时替换成xsxxb.grjlcxxtq个人简历（从小学填起）】
//				List<HashMap<String, String>> xlshjlxxList = xsxxglService.getXlshjlList(xh);
//				data.put("xlshjlxxList", xlshjlxxList);
//				data.put("xlshjlxxListSize", xlshjlxxList.size());
				// ===================处理 个人简历（从小学填起），在word能换行 begin===================
				String zd6 = xsjbxx.get("zd6");
				List<String> zd6List = new ArrayList<String>();
				if(zd6 != null){
					String[] zd6Arr = zd6.split("\r\n");
					zd6List = Arrays.asList(zd6Arr);
				}
				data.put("zd6List", zd6List);
				// ===================处理 个人简历（从小学填起），在word能换行 end===================
				// 根据学号查询获奖情况
				List<HashMap<String, String>> hjqkList = pjjgService.getHjqkList(xh);
				StringBuffer hjqkBuffer = new StringBuffer();
				for (HashMap<String, String> hjqkMap : hjqkList) {
					hjqkBuffer.append(hjqkMap.get("xmmc")).append(" ");
				}
				xsjbxx.put("hjqkmcs", hjqkBuffer.toString());
				//学生照片
				InputStream is = xsxxService.getPhotoStream(xh);
				File photoFile = FileUtil.inputstreamToFile(is, "doc");
				String photo = FileUtil.getBASE64(photoFile);
				xsjbxx.put("photo", photo);
				
				data.putAll(xsjbxx);
				File file = service.getWord(data);
				files.add(file);
			}
			if(files!=null && files.size()>1){
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
			}else{
				FileUtil.outputWord(response, files.get(0));
			 }
		}
		return null;
	}

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZwsqForm model = (ZwsqForm) form;
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
