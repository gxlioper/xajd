/**
 * @部门:学工产品事业部
 * @日期：2016-7-1 上午09:56:31 
 */  
package com.zfsoft.xgxt.xlzx.zxzxthjl;

import java.io.File;
import java.net.URLDecoder;
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
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

	/** 
	 * @系统名称: 学生工作管理系统
	 * @模块名称: 心理健康咨询
	 * @类功能描述: 心里咨询健康类
	 * @作者： 孟威[工号:1186]
	 * @时间： 2016-7-1 上午09:56:31 
	 * @版本： V1.0
	 * @修改记录: 类修改者-修改日期-修改说明  
	 */
	public class ZxzxthjlAction extends SuperAction{
		private static final String url = "xlzx_thjl_zxzxthjl.do";
	/**
	 * @描述: 查询页面
	 * @作者： 孟威[工号：1186]
	 * @日期：2016-7-1 下午02:38:02
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
		public ActionForward zxzxthjlList(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			ZxzxthjlForm model = (ZxzxthjlForm) form;
			ZxzxthjlService service = new ZxzxthjlService();
			if (QUERY.equals(model.getType())){
				//生成高级查询对象
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				model.setSearchModel(searchModel);
				User user = getUser(request);
				//查询
				List<HashMap<String,String>> resultList = service.getPageList(model,user);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			String path = "xlzx_thjl_zxzxthjl.do";
			request.setAttribute("path", path);
			request.setAttribute("realTable", "xlzx_thjl_zxzxthjl");
			FormModleCommon.commonRequestSet(request);//title显示
			return mapping.findForward("zxzxthjlList");
		}
		
		
	/**
	 * @描述: 增加
	 * @作者： 孟威[工号：1186]
	 * @日期：2016-7-5 上午10:48:26
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
		public ActionForward zxzxthjlAdd(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			ZxzxthjlForm myForm = (ZxzxthjlForm) form;
			ZxzxthjlService service = new ZxzxthjlService();
			ZxzxthjlForm model = (ZxzxthjlForm) form;
			User user = getUser(request);
			HashMap<String, String> jsInfo = new HashMap<String,String>();
			// 职工号信息
 			if(StringUtils.isNotNull(user.getUserName())){
 				jsInfo = service.getInfoByZgh(user.getUserName());
 			}
 			if (!StringUtil.isNull(myForm.getXh())){
 				//加载学生基本信息
 				XsxxService xsxxService = new XsxxService();
 				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
 				request.setAttribute("jbxx", xsjbxx);
 			}
 			if (SAVE.equalsIgnoreCase(myForm.getType())) {
 				myForm.setYbwtlb(URLDecoder.decode(myForm.getYbwtlb(),"UTF-8"));
 				myForm.setZajb(URLDecoder.decode(myForm.getZajb(),"UTF-8"));
 				// 唯一性判断
 				boolean isExist = service.addCheck(myForm);
 				if (!isExist) {
 					boolean result = service.runInsert(myForm);
 					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
 					response.getWriter().print(getJsonMessageByKey(messageKey));
 				} else {
 					response.getWriter().print(getJsonResult(MessageKey.XLZX_ZXZXTHJL_EXIST, false));//同一天同一个学生不允许有多次谈话记录！
 				}
 				return null;
 			}
 			String path = "xlzx_thjl_zxzxthjlgl.do?method=zxzxthjlAdd";
 			request.setAttribute("path", path);
 			request.setAttribute("jsInfo", jsInfo);//职工信息
 			request.setAttribute("ybxlwtlbList", service.getYblxwtlbList());//一般心理问题类别
 			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
			return mapping.findForward("zxzxthjlAdd");
		}
		
		
	/**
	 * @描述: 修改
	 * @作者： 孟威[工号：1186]
	 * @日期：2016-7-6 下午02:45:33
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
		public ActionForward zxzxthjlUpdate(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			ZxzxthjlService service = new ZxzxthjlService();
			ZxzxthjlForm model = (ZxzxthjlForm) form;
			ZxzxthjlForm myForm = service.getModel(model);
			if (!StringUtil.isNull(myForm.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			User user = getUser(request);
			HashMap<String, String> jsInfo = new HashMap<String,String>();
			// 职工号信息
 			if(StringUtils.isNotNull(user.getUserName())){
 				jsInfo = service.getInfoByZgh(user.getUserName());
 			}
 			request.setAttribute("jsInfo", jsInfo);//职工信息
			if (SAVE.equalsIgnoreCase(model.getType())){
				model.setYbwtlb(URLDecoder.decode(model.getYbwtlb(),"UTF-8"));
				model.setZajb(URLDecoder.decode(model.getZajb(),"UTF-8"));
				boolean isExist = service.addCheck(model);
				if (!isExist) {
					boolean result = service.runUpdate(model);
					String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(message));
				} else {
					response.getWriter().print(
							getJsonResult(MessageKey.XLZX_ZXZXTHJL_EXIST, false));
				}
				return null;
			}
 			request.setAttribute("ybxlwtlbList", service.getYblxwtlbList());//一般心理问题类别
			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
			return mapping.findForward("zxzxthjlUpdate");
		}
		
		
	/**
	 * @描述: 查看
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-6 上午11:01:13
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
		public ActionForward zxzxthjlView(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			ZxzxthjlService service = new ZxzxthjlService();
			ZxzxthjlForm model = (ZxzxthjlForm) form;
			ZxzxthjlForm myForm = service.getModel(model);
			if (!StringUtil.isNull(myForm.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			User user = getUser(request);
			HashMap<String, String> jsInfo = new HashMap<String,String>();
 			if(StringUtils.isNotNull(user.getUserName())){
 				jsInfo = service.getInfoByZgh(user.getUserName());
 			}
 			HashMap<String,String> thjlxx = service.getThjlxx(myForm.getId());
 			request.setAttribute("thjlxx", StringUtils.formatData(thjlxx));
 			request.setAttribute("jsInfo", jsInfo);//职工信息
			return mapping.findForward("zxzxthjlView");
			}
		
		
	/**
	 * @描述: 删除方法
	 * @作者： 孟威[工号：1186]
	 * @日期：2016-7-4 上午10:34:03
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
		public ActionForward zxzxthjlDelete(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			ZxzxthjlService service = new ZxzxthjlService();
			String ZxzxThjl = request.getParameter("ZxzxThjl");
			String[] id = ZxzxThjl.split(",");
			int count = 0;
			try {
				count = service.delZxzxthjlId(id);
				response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.SYS_DEL_NUM,count)));
			} catch (Exception e) {
				e.printStackTrace();
				throw new SystemException(MessageKey.SYS_DEL_FAIL);
			}
			return null;
		}
		
		
	/**
	 * @描述: 导出查询结果
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-4 上午09:23:29
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
			ZxzxthjlForm model = (ZxzxthjlForm) form;
			ZxzxthjlService service = new ZxzxthjlService();
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
		 * @描述: 单个打印在线咨询约谈记录表
		 * @作者： 孟威[工号：1186]
		 * @日期：2016-7-13 下午02:33:08
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
		public ActionForward getZxzxthjl(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			ZxzxthjlForm myForm = (ZxzxthjlForm) form;
			File wordFile = getZxzxthjlWord(myForm,request);
			FileUtil.outputWord(response, wordFile);
			return null;
		}
		/**
		 * @描述: 批量打印在线咨询约谈记录表
		 * @作者：孟威[工号：1186]
		 * @日期：2016-7-13 下午02:29:34
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
		public ActionForward getZxzxthjlZip(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			ZxzxthjlForm myForm = (ZxzxthjlForm) form;
			String value = request.getParameter("value");
			if (StringUtils.isNotNull(value)){
				String[] values = value.split(",");
				List<File> files = new ArrayList<File>();
				for (int i = 0 , n = values.length ; i < n ; i++){
					myForm.setId(values[i]);
					File file = getZxzxthjlWord(myForm,request);
					files.add(file);
				}
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
				for(int j=0;j<files.size();j++){
					files.get(j).delete();
				}
			}
			return null;
		}
		/**
		 * 填充模版数据生成word文件
		 */
		private File getZxzxthjlWord (ZxzxthjlForm myForm,HttpServletRequest request) throws Exception{
			ZxzxthjlService service = new ZxzxthjlService();
			XsxxService xsxxService = new XsxxService();
			Map<String,Object> data = new HashMap<String,Object>();
			HashMap<String,	String> thjlList = service.getThjl(myForm.getId());
			// 加载学生基本信息
			HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(thjlList.get("xh"));
			data.putAll(xsxxMap);
			data.put("bz", HtmlUtil.xmlZy(thjlList.get("bz")));
			data.put("jbqkms", HtmlUtil.xmlZy(thjlList.get("jbqkms")));
			data.put("cbpgjg", HtmlUtil.xmlZy(thjlList.get("cbpgjg")));
			data.put("thjl",thjlList);
			data.put("thsj",DateTranCnDate.fomartDateToCn(thjlList.get("thsj"),FomartDateType.day));//谈话时间以XXXX年XX月XX日展现出来
			
			//分割一般心理问题类别
			List<HashMap<String, String>> ybxlwtlbList = service.getYblxwtlbList();
			Map<String,Boolean> ybwtlbMap = new HashMap<String,Boolean>();
			String[] ybxlwtlb = new String[ybxlwtlbList.size()];
			for(int i=0;i < ybxlwtlbList.size();i++){
				ybxlwtlb[i] = ybxlwtlbList.get(i).get("mc");
				ybwtlbMap.put("ybwtlb"+i, false);
			}
			String[] ybwtlbs=new String[]{};
			String ybwtlb = service.getYbwtlb(myForm.getId());
			if(StringUtils.isNotNull(ybwtlb)){
				ybwtlbs = ybwtlb.split(",");
				for(int i=0;i < ybwtlbs.length;i++){
					for(int j=0;j < ybxlwtlb.length;j++){
						if(ybxlwtlb[j].equals(ybwtlbs[i])){
							ybwtlbMap.put("ybwtlb"+j, true);
						}
					}
				}
			}
			data.put("ybwtlbMap", ybwtlbMap);
			//心理障碍和精神疾病
			Map<String,Boolean> zajbMap = new HashMap<String,Boolean>();
			zajbMap.put("zajb1", false);
			zajbMap.put("zajb2", false);
			zajbMap.put("zajb3", false);
			String[] zajbs=new String[]{};
			String zajb = service.getZajb(myForm.getId());
			if(StringUtils.isNotNull(zajb)){
				zajbs = zajb.split(",");
				for(int i=0;i < zajbs.length;i++){
					if("初步评估".equals(zajbs[i])){
						zajbMap.put("zajb1", true);
					}
					if("建议咨询".equals(zajbs[i])){
						zajbMap.put("zajb2", true);
					}
					if("其他建议".equals(zajbs[i])){
						zajbMap.put("zajb3", true);
					}
				}
			}
			data.put("zajbMap", zajbMap);
			File file = null;
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xlzx","zxzxthjl.xml",FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			return file;
		}
		
	}
