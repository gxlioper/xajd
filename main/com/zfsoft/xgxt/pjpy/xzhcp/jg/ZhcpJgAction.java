package com.zfsoft.xgxt.pjpy.xzhcp.jg;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.*;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.pjpy.xzhcp.sq.ZhcpDjService;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZhcpJgAction extends SuperAction<ZhcpJgForm,ZhcpJgService> {
	private static final String url = "pjpy_xzhcp_zcjg.do";
	private ZhcpJgService service = new ZhcpJgService();
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
	public ActionForward getZhcpJgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		CsszModel cssz = new CsszService().getModel();
		searchModel.setSearch_tj_xn(new String[] {cssz.getXn() });
		searchModel.setSearch_tj_xq(new String[]{cssz.getXq()});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
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
	public ActionForward searchForZhcpJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhcpJgForm zpForm = (ZhcpJgForm)form;
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
		ZhcpJgForm zpForm = (ZhcpJgForm)form;
		CsszModel cssz = new CsszService().getModel();
		zpForm.setXn(cssz.getXn());
		zpForm.setXq(cssz.getXq());
		User user = getUser(request);
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
		String path = "xzhcp_zcjg.do?method=add";
		request.setAttribute("path", path);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
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
		ZhcpJgForm zpForm = (ZhcpJgForm)form;
		ZhcpJgForm zpModel = service.getModel(zpForm.getSqid());
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
		String path = "xzhcp_zcjg.do?method=update";
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
		ZhcpJgForm zpForm = (ZhcpJgForm)form;
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
		ZhcpJgForm zpForm = (ZhcpJgForm)form;
		ZhcpJgForm zpModel = service.getModel(zpForm.getSqid());
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
		String path = "xzhcp_zcjg.do?method=view";
		request.setAttribute("xn", zpModel.getXn());
		request.setAttribute("xqmc",Base.getXqmcForXqdm(zpModel.getXq()));
		request.setAttribute("path",path);
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
	
	
	//导出
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZhcpJgForm model = (ZhcpJgForm) form;

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
	
	/**
	 * 
	 * @描述: 安徽农业申请表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-5 上午11:47:07
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
	public ActionForward getSqbAhny(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZhcpJgForm myForm = (ZhcpJgForm) form;
		File wordFile = getWordAhny(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @描述: 安徽农业申请表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-5 上午11:48:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getWordAhny(ZhcpJgForm myForm,HttpServletRequest request) throws Exception{
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		ZhcpDjService serviceSq = new ZhcpDjService();
		myForm = service.getModel(myForm.getSqid());
		HashMap<String, String> xsjbxx = serviceSq.getXsjbxx(myForm.getXh());
		data.putAll(xsjbxx);
		data.put("xn", myForm.getXn());
		data.put("bjrs",serviceSq.getBjrs(xsjbxx.get("bjdm")));
		data.put("grcp",HtmlUtil.xmlZy(myForm.getGrpc()));
		data.put("pjlist",serviceSq.getListByHjjg(myForm.getXh(),myForm.getXn()));
		data.put("zwmc",new DwwhService().getZwForXh(myForm.getXh()));
		List<HashMap<String,String>> shyjList = new ShlcDao().getShyjListByYwid(myForm.getSqid());
		for (int i = 0; i < shyjList.size(); i++) {
			data.put("shyj"+(i+1), shyjList.get(i).get("shyj"));
		}
		List<HashMap<String,String>> pmfsList = serviceSq.getZcfsForDjb(myForm.getXh(), myForm.getXn(), myForm.getXq());
		for (int i = 0; i < pmfsList.size(); i++) {
			HashMap<String, String> temp = pmfsList.get(i);
			String xmmc = temp.get("xmmc");
			if("总积分S".equals(xmmc)){
				data.put("zf", temp.get("fs"));
				data.put("zpm", temp.get("bjpm"));
			}else if("素质拓展N".equals(xmmc)){
				data.put("szn", temp.get("fs"));
				data.put("pmn", temp.get("bjpm"));
			}else if("学习成绩Z".equals(xmmc)){
				data.put("kcf", temp.get("fs"));
				data.put("kcpm", temp.get("bjpm"));
			}else if("基本素质D".equals(xmmc)){
				data.put("szd", temp.get("fs"));
				data.put("pmd", temp.get("bjpm"));
			}else if("创新创业C".equals(xmmc)){
				data.put("cyc", temp.get("fs"));
				data.put("pmc", temp.get("bjpm"));
			}
		}
		ResourceUtils.getFile(Constants.TEP_DIR+"pjpy/ahnyzhcpdj"+".xml");
		file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"pjpy","ahnyzhcpdj.xml",FreeMarkerUtil.getFileName(xsjbxx.get("xm")+"_综合素质测评登记表"));
		return file;
	}
	
	/**
	 * 
	 * @描述: 安徽农业批量下载
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-5 上午11:49:37
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
	public ActionForward getSqbTyAhny(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZhcpJgForm myForm = (ZhcpJgForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setSqid(values[i]);
				File file = getWordAhny(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
}
