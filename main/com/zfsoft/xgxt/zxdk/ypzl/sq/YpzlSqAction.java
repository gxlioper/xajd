/**
 * @部门:学工产品事业部
 * @日期：2016-2-23 下午01:31:25 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.sq;

import java.io.File;
import java.text.SimpleDateFormat;
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
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.zxdk.ypzl.comm.YpzlUtil;
import com.zfsoft.xgxt.zxdk.ypzl.cssz.CsszService;
import com.zfsoft.xgxt.zxdk.ypzl.jg.YpzljgForm;
import com.zfsoft.xgxt.zxdk.ypzl.jg.YpzljgService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：柳俊[工号:1282]
 * @时间： 2016-2-23 下午01:31:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YpzlSqAction extends SuperAction<YpzlSqForm, YpzlSqService>{
	private YpzlSqService service = new YpzlSqService();
	private CsszService csszService = new CsszService();
	private YpzljgService ypzljgService = new YpzljgService();
	private static final String YPZLSQ = "ypzlsq";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(YPZLSQ);
	}
	
	private static final String url = "zxdk_ypzl_ypzlsq.do";
	
	
	
	/** 
	 * @描述:查询
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-23 下午02:20:41
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
	public ActionForward getYpzlsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YpzlSqForm model = (YpzlSqForm) form;
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
		//searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String[] sqshkg = csszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg == null ? "0" : sqshkg[0]);
		String path = "zxdk_ypzl_ypzlsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("gnmkmc", new YpzlUtil().getAutoGnmkmc(url));
		return mapping.findForward("getYpzlsqList");
	}
	
	/** 
	 * @描述:增加
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-23 下午03:13:42
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
	public ActionForward addYpzlsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YpzlSqForm model = (YpzlSqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学年 学期list
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);		
		String path = "ypzl_sq.do?method=addYpzlsq";
		request.setAttribute("path", path);
		request.setAttribute("xnsr", Base.currXn);
		request.setAttribute("xqsr", service.getXqmc(Base.currXq));
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("sqr", user.getUserName());
		//华中师范个性化
		if("10511".equals(Base.xxdm)){
			request.setAttribute("lbList", service.getYtlbList());
		}
		return mapping.findForward("addYpzlsq");
	}
	
	/** 
	 * @描述:保存结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-23 下午03:27:18
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
	public ActionForward saveSqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YpzlSqForm model = (YpzlSqForm) form;
		boolean result = false;
	    User user = getUser(request);
 		if(model.getType().equals("save")||model.getType().equals("submit")){
 			if("10511".equals(Base.xxdm)){
 				String sqrq = GetTime.getTimeByFormat("yyyy-mm-dd");
 				if(new YpzlUtil().checkIsExistsSameDate(sqrq, model.getXh(),"qb")){
 					String messageKey = MessageKey.PJPY_YPZL_REPEAT_HSD;
 	 				response.getWriter().print(getJsonMessageByKey(messageKey));
 	 				return null;
 				}
 			}else{
 				if(service.isHaveRecord(model)){
 	 				String messageKey = MessageKey.PJPY_YPZL_REPEAT;
 	 				response.getWriter().print(getJsonMessageByKey(messageKey));
 	 				return null;
 	 			}
 			}
 			
			result = service.saveSqjg(model, user);
		}else if(model.getType().equals("update")||model.getType().equals("updatesubmit")){
			result = service.saveSqjgUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @描述:提交
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-23 下午05:05:43
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
	public ActionForward submitYpzlsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YpzlSqForm model = (YpzlSqForm) form;
		String values = request.getParameter("values");
		model.setSqid(values);
		boolean result = service.submitYpzlsq(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-23 下午06:31:56
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
	public ActionForward editYpzlsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YpzlSqForm myForm = (YpzlSqForm) form;
		User user = getUser(request);
		YpzlSqForm model = service.getModel(myForm);
		if (null != model) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("jbxxList", jbxxList);
		// 加载项目奖项信息
		request.setAttribute("xnsr", model.getXn());
		request.setAttribute("xqsr", service.getXqmc(model.getXq()));
		request.setAttribute("sqje", model.getSqje());
		if("10511".equals(Base.xxdm)){
			request.setAttribute("lbList", service.getYtlbList());
		}
		String path = "ypzl_sq.do?method=editYpzlsq";
		request.setAttribute("path", path);
		return mapping.findForward("editYpzlsq");
	}
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-24 上午08:32:20
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
	public ActionForward delYpzlsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	 * @描述:撤销
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-24 上午08:36:14
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
	public ActionForward cancelYpzlsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			YpzlSqForm model = new YpzlSqForm();
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
	 * @描述:导出
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-24 上午09:31:16
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
		YpzlSqForm model = (YpzlSqForm) form;
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
	
	/** 
	 * @描述:查看
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-24 上午09:47:46
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
	public ActionForward viewYpzlsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YpzlSqForm model = (YpzlSqForm) form;
		request.setAttribute("jbxxList", jbxxList);
		YpzlSqForm viewForm = service.getModel(model);
		BeanUtils.copyProperties(model, viewForm);
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("rs", model);
		request.setAttribute("xqsr", service.getXqmc(model.getXq()));
		if("10511".equals(Base.xxdm)){
			request.setAttribute("ytmc", service.getYtmc(model.getYtlb()));
		}		
		return mapping.findForward("viewYpzlsq");

	}
	
	/** 
	 * @描述:申请表打印
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-26 下午03:14:19
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
	public ActionForward printypzlsqb (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxService xsxxService = new XsxxService();
		YpzlSqForm model = (YpzlSqForm) form;
		if(StringUtils.isNotNull(model.getSqid())){
			List<File> files = new ArrayList<File>();
			String[] ids = model.getSqid().split(",");
			for (String id : ids) {
				model.setSqid(id);
				YpzlSqForm myForm = service.getModel(model);
				//加载学生基本信息
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
				String[] nfs = myForm.getXn().split("-");
				String ksnf = nfs[0];
				String jsnf = nfs[1];				
				String je = myForm.getSqje() + ".00";
				String nj = xsjbxx.get("nj") + "级";
				xsjbxx.put("ksnf", ksnf);
				xsjbxx.put("jsnf", jsnf);
				xsjbxx.put("je", je);
				xsjbxx.put("nj", nj);
				xsjbxx.put("jtdz", HtmlUtil.xmlZy(xsjbxx.get("jtdz")));
				
				/**如果申请时间是1月到6月份的话，那么这里是当年的4月到下一年的1月。如果申请时间是7月到12月，
				那么这里是当年的11月到下一年的8月。判别标准是学生提交的时间。**/
				int sqy = Integer.parseInt(DateUtils.getMonth(myForm.getSqsj()));	//申请月份
				if(sqy>=1&&sqy<=6){
					xsjbxx.put("ksy","4");
					xsjbxx.put("jsy","1");
				}else{
					xsjbxx.put("ksy","11");
					xsjbxx.put("jsy","8");
				}
				
				//加载家庭成员
				JtqkdcService jtqkdcService = new JtqkdcService();
				List<HashMap<String, String>> jtcyxxList = jtqkdcService.getJtcyList(myForm.getXh());
				if(jtcyxxList.size()>0 && null != jtcyxxList) {
					HashMap<String,String> jtcyxx = new HashMap<String, String>();
					String cyxm1 = jtcyxxList.get(0).get("cyxm");
					String cyzy1 = jtcyxxList.get(0).get("cyzy");
					String cysfz1 = jtcyxxList.get(0).get("ylzd3");
					String cydw1 = jtcyxxList.get(0).get("cyxxdw");
					jtcyxx.put("cyxm1", cyxm1);
					jtcyxx.put("cyzy1", cyzy1);
					jtcyxx.put("cysfz1", cysfz1);
					jtcyxx.put("cydw1", HtmlUtil.xmlZy(cydw1));
					if(jtcyxxList.size() >= 2 ){
						String cyxm2 = jtcyxxList.get(1).get("cyxm");
						String cyzy2 = jtcyxxList.get(1).get("cyzy");
						String cysfz2 = jtcyxxList.get(1).get("ylzd3");
						String cydw2 = jtcyxxList.get(1).get("cyxxdw");
						jtcyxx.put("cyxm2", cyxm2);
						jtcyxx.put("cyzy2", cyzy2);
						jtcyxx.put("cysfz2", cysfz2);
						jtcyxx.put("cydw2", HtmlUtil.xmlZy(cydw2));
					}
					xsjbxx.putAll(jtcyxx);						
				}	
				File file = service.printForWord(xsjbxx);
				files.add(file);
			}
			if(files!=null &&files.get(0) != null && files.size()>1){ //此处代码不严谨，略作修改
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
			}else if(files.get(0) != null){
				FileUtil.outputWord(response, files.get(0));
			 }else{
				 return null;
			 }
		}else{
			List<File> files = new ArrayList<File>();
			String[] jgids = model.getYlzd1().split(",");
			YpzljgForm ypzljgForm = new YpzljgForm();
			for (String jgid : jgids) {
				ypzljgForm.setJgid(jgid);
				ypzljgForm = ypzljgService.getModel(ypzljgForm);
				//加载学生基本信息
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(ypzljgForm.getXh());
				String[] nfs = ypzljgForm.getXn().split("-");
				String ksnf = nfs[0];
				String jsnf = nfs[1];				
				String je = ypzljgForm.getSqje() + ".00";
				String nj = xsjbxx.get("nj") + "级";
				xsjbxx.put("ksnf", ksnf);
				xsjbxx.put("jsnf", jsnf);
				xsjbxx.put("je", je);
				xsjbxx.put("nj", nj);
				xsjbxx.put("jtdz", HtmlUtil.xmlZy(xsjbxx.get("jtdz")));
				
				/**如果申请时间是1月到6月份的话，那么这里是当年的4月到下一年的1月。如果申请时间是7月到12月，
				那么这里是当年的11月到下一年的8月。判别标准是学生提交的时间。**/
				int sqy = Integer.parseInt(DateUtils.getMonth(ypzljgForm.getSqsj()));	//申请月份
				if(sqy>=1&&sqy<=6){
					xsjbxx.put("ksy","4");
					xsjbxx.put("jsy","1");
				}else{
					xsjbxx.put("ksy","11");
					xsjbxx.put("jsy","8");
				}
				
				
				//加载家庭成员
				JtqkdcService jtqkdcService = new JtqkdcService();
				List<HashMap<String, String>> jtcyxxList = jtqkdcService.getJtcyList(ypzljgForm.getXh());
				if(jtcyxxList.size()>0 && null != jtcyxxList) {
					HashMap<String,String> jtcyxx = new HashMap<String, String>();
					String cyxm1 = jtcyxxList.get(0).get("cyxm");
					String cyzy1 = jtcyxxList.get(0).get("cyzy");
					String cysfz1 = jtcyxxList.get(0).get("ylzd3");
					String cydw1 = jtcyxxList.get(0).get("cyxxdw");
					jtcyxx.put("cyxm1", cyxm1);
					jtcyxx.put("cyzy1", cyzy1);
					jtcyxx.put("cysfz1", cysfz1);
					jtcyxx.put("cydw1", HtmlUtil.xmlZy(cydw1));
					if(jtcyxxList.size() >= 2 ){
						String cyxm2 = jtcyxxList.get(1).get("cyxm");
						String cyzy2 = jtcyxxList.get(1).get("cyzy");
						String cysfz2 = jtcyxxList.get(1).get("ylzd3");
						String cydw2 = jtcyxxList.get(1).get("cyxxdw");
						jtcyxx.put("cyxm2", cyxm2);
						jtcyxx.put("cyzy2", cyzy2);
						jtcyxx.put("cysfz2", cysfz2);
						jtcyxx.put("cydw2", HtmlUtil.xmlZy(cydw2));
					}
					xsjbxx.putAll(jtcyxx);						
				}	
				File file = service.printForWord(xsjbxx);
				files.add(file);
			}
			if(files!=null &&files.get(0) != null && files.size()>1){//此处代码不严谨，略作修改
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
			}else if(files.get(0) != null){
				FileUtil.outputWord(response, files.get(0));
			 }else{
				 return null;
			 }
		}
		return null;
	}
		
}
