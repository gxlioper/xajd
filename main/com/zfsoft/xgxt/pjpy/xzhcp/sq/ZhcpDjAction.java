package com.zfsoft.xgxt.pjpy.xzhcp.sq;

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
import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.pjpy.xzhcp.ZhcpForm;
import com.zfsoft.xgxt.pjpy.xzhcp.ZhcpService;
import com.zfsoft.xgxt.pjpy.xzhcp.jg.ZhcpJgForm;
import com.zfsoft.xgxt.pjpy.xzhcp.jg.ZhcpJgService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

public class ZhcpDjAction extends SuperAction<ZhcpDjForm,ZhcpDjService> {
	private static final String url = "pjpy_xzhcp_zcdj.do";
	private ZhcpDjService service = new ZhcpDjService();
	private static final String XZHCP = "xzhcp";
	/**
	 * 
	 * @描述: 查询跳转页面 
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-30 上午09:43:22
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
	public ActionForward getZhcpDjList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		CsszModel cssz = new CsszService().getModel();
		searchModel.setSearch_tj_xn(new String[] {cssz.getXn() });
		searchModel.setSearch_tj_xq(new String[]{cssz.getXq()});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		ZhcpForm kgModel = new ZhcpService().getModel();
		boolean sqkg = (kgModel == null || StringUtils.isNull(kgModel.getSplc())) ? false :true;
		request.setAttribute("sqkg", sqkg);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述: 查询综合测评登记
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-30 上午09:44:29
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
	public ActionForward searchForZhcpDj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhcpDjForm zpForm = (ZhcpDjForm)form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zpForm.setSearchModel(searchModel);  
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(zpForm, user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述: 增加
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-30 上午10:17:08
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
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhcpDjForm zpForm = (ZhcpDjForm)form;
		User user = getUser(request);
		CsszModel cssz = new CsszService().getModel();
		if ("stu".equals(user.getUserType())) {
			zpForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(zpForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(zpForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XZHCP);
		request.setAttribute("jbxxList", jbxxList);
		String path = "xzhcp_zcdj.do?method=add";
		request.setAttribute("xn",cssz.getXn());
		request.setAttribute("xqmc",Base.getXqmcForXqdm(cssz.getXq()));
		request.setAttribute("path", path);
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @描述: 修改
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-30 上午10:17:08
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
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhcpDjForm zpForm = (ZhcpDjForm)form;
		ZhcpDjForm zpModel = service.getModel(zpForm.getSqid());
		if(zpModel != null){
			BeanUtils.copyProperties(zpForm,StringUtils.formatData(zpModel) );
		}
		if (!StringUtil.isNull(zpForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(zpForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XZHCP);
		request.setAttribute("jbxxList", jbxxList);
		String path = "xzhcp_zcdj.do?method=update";
		request.setAttribute("path", path);
		request.setAttribute("xn",zpModel.getXn());
		request.setAttribute("xqmc",Base.getXqmcForXqdm(zpModel.getXq()));
		return mapping.findForward("update");
	}
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-30 上午10:17:08
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
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhcpDjForm zpForm = (ZhcpDjForm)form;
		boolean rs = true;
		try {
			rs = service.save(zpForm);
		}catch (SystemException e) {
			// TODO 自动生成 catch 块
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-30 上午10:31:34
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
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhcpDjForm zpForm = (ZhcpDjForm)form;
		ZhcpDjForm zpModel = service.getModel(zpForm.getSqid());
		if(zpModel != null){
			BeanUtils.copyProperties(zpForm, zpModel);
		}
		if (!StringUtil.isNull(zpForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(zpForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XZHCP);
		request.setAttribute("jbxxList", jbxxList);
		String path = "xzhcp_zcdj.do?method=view";
		request.setAttribute("xn", zpModel.getXn());
		request.setAttribute("xqmc",Base.getXqmcForXqdm(zpModel.getXq()));
		request.setAttribute("path", path);
		return mapping.findForward("view");
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-30 上午11:28:06
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
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = new ZhcpDjService().runDelete(ids);
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
	 * 
	 * @描述: 撤销申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-31 上午11:36:41
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
	public ActionForward cancelSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ywid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		boolean rs = service.cancelSq(ywid, lcid);
		if (rs) {
			// 更新业务状态为'未提交'
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			ZhcpDjForm myForm = new ZhcpDjForm();
			myForm.setSqid(ywid);
			if (Integer.valueOf(shlcdao.getExistTh(ywid)) > 0) {
				myForm.setShzt(Constants.YW_YTH);
			} else {
				myForm.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(myForm);
		}
		String messageKey = rs ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-31 下午02:03:12
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
	public ActionForward submitBusi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String value = request.getParameter("values");
		ZhcpDjForm model = service.getModel(value);
		User user = getUser(request);
		boolean result = service.submitBusiness(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//导出
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZhcpDjForm model = (ZhcpDjForm) form;

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
	
	public ActionForward getSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZhcpDjForm myForm = (ZhcpDjForm) form;
		File wordFile = getWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getWord(ZhcpDjForm myForm,HttpServletRequest request) throws Exception{

		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		ZhcpDjForm sqForm = new ZhcpDjForm();
		if(request.getParameter("flag").equals("sq")){
			 sqForm = service.getModel(myForm);
		}else{
			ZhcpJgForm jgForm = new ZhcpJgService().getModel(myForm.getSqid());
			BeanUtils.copyProperties(sqForm, jgForm);
		}
		HashMap<String, String> xsjbxx = service.getXsjbxx(sqForm.getXh());
		data.putAll(xsjbxx);
		data.put("xqmc",Base.getXqmcForXqdm(sqForm.getXq()));
		data.put("xn", sqForm.getXn());
		data.put("bjrs",service.getBjrs(xsjbxx.get("bjdm")));
		data.put("grpc",HtmlUtil.xmlZy(sqForm.getGrpc()));
		data.put("zdf",service.getDkZdf(sqForm.getXh(), sqForm.getXn(), sqForm.getXq()));
		List<HashMap<String,String>> shyjList = new ShlcDao().getShyjListByYwid(sqForm.getSqid());
		for (int i = 0; i < shyjList.size(); i++) {
			data.put("shyj"+(i+1), shyjList.get(i).get("shyj"));
		}
		List<HashMap<String,String>> pmfsList = service.getZcfsForDjb(sqForm.getXh(), sqForm.getXn(), sqForm.getXq());
		for (int i = 0; i < pmfsList.size(); i++) {
			HashMap<String, String> temp = pmfsList.get(i);
			String xmmc = temp.get("xmmc");
			if("综测总分".equals(xmmc)){
				data.put("zf", temp.get("fs"));
				data.put("zpm", temp.get("bjpm"));
			}else if("基础测评".equals(xmmc)){
				data.put("jcdf", temp.get("fs"));
			}else if("德育加分".equals(xmmc)){
				data.put("dyjif", temp.get("fs"));
			}else if("德育减分".equals(xmmc)){
				data.put("dyjf", temp.get("fs"));
			}else if("德育测评".equals(xmmc)){
				data.put("dycp", temp.get("fs"));
			}else if("课程学习成绩测评".equals(xmmc)){
				data.put("xxcj", temp.get("fs"));
				data.put("cjpm", temp.get("bjpm"));
			}else if("体育测评".equals(xmmc)){
				data.put("tycj", temp.get("fs"));
			}else if("体育课成绩".equals(xmmc)){
				data.put("tykcj", temp.get("fs"));
			}else if("体育加分".equals(xmmc)){
				data.put("tyjiaf", temp.get("fs"));
			}else if("体育减分".equals(xmmc)){
				data.put("tyjf", temp.get("fs"));
			}
		}
		ResourceUtils.getFile(Constants.TEP_DIR+"pjpy/nmgdzzhcp"+".xml");
		file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"pjpy","nmgdzzhcp.xml",FreeMarkerUtil.getFileName(xsjbxx.get("xm")+"_综合素质测评登记表"));
		return file;
	}
	
	public ActionForward getSqbTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZhcpDjForm myForm = (ZhcpDjForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setSqid(values[i]);
				File file = getWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	
	
	
}
