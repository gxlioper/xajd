/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 下午04:39:45 
 */
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjg;

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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsh.YlbxshService;
import com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsq.YlbxsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 医疗保险结果管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-18 下午04:05:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YlbxjgAction extends SuperAction {
	//定义日常事务中学生证补办常量可以从基本信息表中获取学生信息
	private static final String DXSYLBX = "dxsylbx";
	private static List<HashMap<String, String>> jbxxList = null;

	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(DXSYLBX);
		
	}
	
	private static final String url = "rcsw_dxsylbx_ylbxjg.do";

	/**
	 * 
	 * @描述:TODO(查询获取医疗保险结果数据)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-10 下午02:01:56
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
	public ActionForward ylbxjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxjgForm model = (YlbxjgForm) form;
		YlbxjgService service = new YlbxjgService();
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询获取医疗保险结果数据
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_dxsylbx_ylbxjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ylbxjgManage");
		
		
	}

	/**
	 * 
	 * @增加医疗保险结果数据
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-8 上午11:42:40
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
	@SystemLog(description="访问日常事务-大学生医疗保险-医疗保险结果-增加XH:{xh},XN:{xn},XQ:{xq},CZQEBZ:{czqebz},CBZK:{cbzk},SQLY:{sqly}")
	public ActionForward addYlbxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxjgForm model = (YlbxjgForm) form;
		YlbxjgService service = new YlbxjgService();
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}
			// 唯一性判断（学号）
		    boolean isExist=service.isExistByYlbxjg(model,SAVE);
		    model.setCbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			if (!isExist) {
				super.resetToken(request);
				// 添加学生证补办结果
				boolean result = service.saveYlbxjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_DXSYLBX_YLBXJGCZ));
				return null;
			}
		}
		String path = "rcsw_dxsylbx_ylbxjggl.do?method=addYlbxjg";
		request.setAttribute("path", path);
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		//model.setXn(Base.currXn);
		//model.setXq(Base.currXq);
		//学年集合
		request.setAttribute("xnList", Base.getXnndList());
		//学期集合
		request.setAttribute("xqList", Base.getXqList());
		model.setXqmc(service.getCurrentXqmc(model));
		
		YlbxsqService ylbxsqService = new YlbxsqService();
		request.setAttribute("czqebzryList", ylbxsqService.getCzqebzryList());
		request.setAttribute("cbzkList", ylbxsqService.getCbzkList());
		this.saveToken(request);
		return mapping.findForward("addYlbxjg");
		
	}
	

	/**
	 * 
	 * @描述:TODO(修改医疗保险结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-13 上午11:22:57
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
	@SystemLog(description="访问日常事务-大学生医疗保险-医疗保险结果-修改YLJGID:{yljgid},XN:{xn},XQ:{xq},CZQEBZ:{czqebz},CBZK:{cbzk},SQLY:{sqly}")
	public ActionForward updateYlbxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		YlbxjgForm model = (YlbxjgForm) form;
		YlbxjgService service = new YlbxjgService();
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(model.getType())) {
				model.setCbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
				// 修改学生证补办结果
				boolean result = service.updateYlbxjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			
		}
		String path = "rcsw_dxsylbx_ylbxjggl.do?method=updateYlbxjg";
		request.setAttribute("path", path);
		
		request.setAttribute("jbxxList", jbxxList);
		YlbxjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		model.setXqmc(service.getCurrentXqmc(model));
		
		YlbxsqService ylbxsqService = new YlbxsqService();
		request.setAttribute("czqebzryList", ylbxsqService.getCzqebzryList());
		request.setAttribute("cbzkList", ylbxsqService.getCbzkList());
		request.setAttribute("qtcbzkval",model.getQtcbzkval());
		
		 //学年集合
		request.setAttribute("xnList", Base.getXnndList());
		//学期集合
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("updateYlbxjg");
		
		
	}


	
	/**
	 * 
	 * @描述:TODO(删除医疗保险结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-13 下午01:43:33
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
	@SystemLog(description="访问日常事务-大学生医疗保险-医疗保险结果-删除VALUES:{values}")
	public ActionForward delYlbxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxjgService service = new YlbxjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteYlbxjg(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
		
	}
	
	/**
	 * 
	 * @描述:TODO(查看医疗保险结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-13 下午02:43:58
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
	public ActionForward viewYlbxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxjgForm model = (YlbxjgForm) form;
		YlbxjgService service = new YlbxjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//查看医疗保险结果
		request.setAttribute("rs", StringUtils.formatData(service.viewOneYlbxjgList(model.getYljgid())));
		
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		model = service.getModel(model);
		YlbxshService ylbxshService = new YlbxshService();
		if(!"".equals(model.getCzqebzdm())&& model.getCzqebzdm() != null){
			String[] czqebzdms = model.getCzqebzdm().split(",");
			request.setAttribute("czqebzrymcsList", ylbxshService.getCzqebzrymcList(czqebzdms));
		}
		if(!"".equals(model.getCbzkdm()) && model.getCbzkdm() != null){
			String[] cbzkdms = model.getCbzkdm().split(",");
			request.setAttribute("cbzkmcsList", ylbxshService.getCbzkdmcsList(cbzkdms));
		}
		request.setAttribute("qtcbzkval",model.getQtcbzkval());
		
		return mapping.findForward("viewYlbxjg");
		
	}
	
	/**
	 * 
	 * @描述:TODO(查看参保状况)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-14 下午04:27:08
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
	public ActionForward viewCbzk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xm = request.getParameter("xm");
		if("null".equals(xm)){
			xm = "";
		}
		YlbxjgForm model = (YlbxjgForm) form;
		YlbxjgService service = new YlbxjgService();
		model = service.getModel(model);
		YlbxshService ylbxshService = new YlbxshService();
		if(!"".equals(model.getCzqebzdm())&& model.getCzqebzdm() != null){
			String[] czqebzdms = model.getCzqebzdm().split(",");
			request.setAttribute("czqebzrymcsList", ylbxshService.getCzqebzrymcList(czqebzdms));
		}
		if(!"".equals(model.getCbzkdm()) && model.getCbzkdm() != null){
			String[] cbzkdms = model.getCbzkdm().split(",");
			request.setAttribute("cbzkmcsList", ylbxshService.getCbzkdmcsList(cbzkdms));
		}
		request.setAttribute("qtcbzkval",model.getQtcbzkval());
		request.setAttribute("xm",xm);
		request.setAttribute("model",StringUtils.formatData(model));
		return mapping.findForward("viewCbzk");
		
	}
	
	/**
	 * 
	 * @描述:TODO(自定义导出设置)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:22:51
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
		
		YlbxjgForm model = (YlbxjgForm) form;
		YlbxjgService service = new YlbxjgService();
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
	 * 
	 * @描述:TODO(大学生医疗保险打包)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-16 上午11:01:37
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
	public ActionForward getDxsylbxZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getDxsylbxWord(values[i]);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
		
	}
	
	
	/**
	 * 
	 * @描述: 打印Word
	 * @作者：Penghui.Qu
	 * @日期：2013-5-16 下午02:22:14
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
	public ActionForward getDxsylbxWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxjgForm myForm = (YlbxjgForm) form;
		//String xh = myForm.getXh();
		String yljgid = myForm.getYljgid();
		File wordFile = getDxsylbxWord(yljgid);
		FileUtil.outputWord(response, wordFile);
		return null;
		
	}

	// 填充模版数据生成word文件
	private File getDxsylbxWord(String yljgid) throws Exception {
  
		YlbxjgService service = new YlbxjgService();
		Map<String, Object> data = new HashMap<String, Object>();
		YlbxjgForm model = new YlbxjgForm();
		model.setYljgid(yljgid);
		model = service.getModel(model);
		String xh = model.getXh();
		if (!StringUtils.isNull(xh)) {
			// 基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			data.put("xxmc", Base.xxmc);
			data.putAll(xsjbxx);
			
			YlbxsqService ylbxsqService = new YlbxsqService();
			if(StringUtils.isNull(model.getQtcbzkval())){
				data.put("qtcbzkval", "");
			}else{
				data.put("qtcbzkval", model.getQtcbzkval());
			}
			List<HashMap<String, String>> dataCzqebzryMap = ylbxsqService.getCzqebzryList();
			data.put("czqebzryList",fomatCzqebzryData(model.getCzqebzdm(), dataCzqebzryMap));
			List<HashMap<String, String>> dataCbzkMap = ylbxsqService.getCbzkList();
			data.put("cbzkList", fomatCbzkdmData(model.getCbzkdm(), dataCbzkMap));
			data.put("model",model);
			File wordFile = FreeMarkerUtil.getWordFile(data,
					"classpath://templates//rcsw", "dxsylbx.xml", xh + "-"
							+ xsjbxx.get("xm"));
			return wordFile;
		}
		return null;
		
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-16 上午09:32:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param str
	 * @param data
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	private List<HashMap<String, String>> fomatCzqebzryData(String str,
			List<HashMap<String, String>> data) {
		
		List<HashMap<String, String>> newData=new ArrayList<HashMap<String,String>>();
		if(str.length() >0){
			for(HashMap<String,String> hm:data){
					if (str.indexOf(hm.get("czqebzdm"))>=0) {
						hm.put("isExsit", "1");
					} else {
						hm.put("isExsit", "0");
					}
					newData.add(hm);
				}
		}else{
			for(HashMap<String,String> hm:data){
				hm.put("isExsit","0");
				newData.add(hm);
			}
		}
		return newData;
		
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-16 上午09:32:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param str
	 * @param data
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	private List<HashMap<String, String>> fomatCbzkdmData(String str,List<HashMap<String, String>> data) {
		
		List<HashMap<String, String>> newData=new ArrayList<HashMap<String,String>>();
		if(str.length() >0){
			for(HashMap<String,String> hm:data){
				if (str.indexOf(hm.get("cbzkdm"))>=0) {
					hm.put("isExsit", "1");
				} else {
					hm.put("isExsit", "0");
				}
			   newData.add(hm);
			}
		}else{
			for(HashMap<String,String> hm:data){
				hm.put("isExsit","0");
				newData.add(hm);
			}
		}
		return newData;
		
	}
	
}
