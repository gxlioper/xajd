/**
 * @部门:学工产品事业部
 * @日期：2015-9-10 上午08:47:43 
 */  
package com.zfsoft.xgxt.zxdk.rwfbybc.dcjg;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
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
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.zxdk.dkbc.sqsh.DkbcService;
import com.zfsoft.xgxt.zxdk.rwfbybc.sqsh.RwfbysqshService;

public class RwfbydcjgAction extends SuperAction<RwfbydcjgModel, RwfbydcjgService> {
	private RwfbydcjgService service = new RwfbydcjgService();
	
	private static final String url = "zxdk_rwfby_dcjg.do";

	@SystemAuth(url = url)
	public ActionForward dcjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "zxdk_rwfby_dcjg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dcjgList");

	}

	@SystemAuth(url = url)
	public ActionForward getDcjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbydcjgModel model = (RwfbydcjgModel) form;
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		List<HashMap<String, String>> resultList = service.getPageList(model,
				user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dcjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbydcjgModel model = (RwfbydcjgModel) form;
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
			request.setAttribute("rxrq", xsjbxx.get("rxrq"));
		}

		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService
				.getJbxxpz("rwfbydc");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("jbxxList", jbxxList);
		DkbcService dkbcservice = new DkbcService();
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("yhlist", dkbcservice.getYhList());
		request.setAttribute("dclblist", dkbcservice.getDclbList());
		request.setAttribute("yjxflist", dkbcservice.getXfList());
		request.setAttribute("path", "rwfby_dcjg.do?method=dcjgAdd");
		this.saveToken(request);
		return mapping.findForward("dcjgAdd");
	}

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dcjgEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbydcjgModel model = (RwfbydcjgModel) form;
		RwfbydcjgModel myForm = service.getModel(model.getId());

		if (myForm != null) {
			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		}

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

		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService
				.getJbxxpz("rwfbydc");
		request.setAttribute("jbxxList", jbxxList);
		DkbcService dkbcservice = new DkbcService();
		request.setAttribute("yhlist", dkbcservice.getYhList());
		request.setAttribute("dclblist", dkbcservice.getDclbList());
		request.setAttribute("yjxflist", dkbcservice.getXfList());
		request.setAttribute("dclb", model.getDclb());
		request.setAttribute("rwqsfsqdc", model.getRwqsfsqdc());
		
		//浙江大学发放次数个性化字段
		if("10335".equals(Base.xxdm)){
			List<HashMap<String,String>> ffcsList = service.getDcjgffcsList(model.getXh(),model.getXn());
			Integer ffcsCount = ffcsList.size();
			request.setAttribute("ffcsList", ffcsList);
			request.setAttribute("ffcsCount", ffcsCount);
		}
		
		return mapping.findForward("dcjgEdit");
	}

	@SystemAuth(url = url)
	public ActionForward dcjgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbydcjgModel model = (RwfbydcjgModel) form;
		RwfbydcjgModel myForm = service.getModel(model.getId());

		if (myForm != null) {
			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		}

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

		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService
				.getJbxxpz("rwfbydc");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("dclblist", new DkbcService().getDclbList());
		return mapping.findForward("dcjgView");
	}

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问助学贷款-入伍服兵役补偿代偿-代偿结果-删除values:{ids}")
	public ActionForward dcjgDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		String [] idArr = ids.split(",");
		if (!StringUtil.isNull(ids)) {
			boolean result = true;
			//浙江大学个性化发放册数信息删除
			if("10335".equals(Base.xxdm)){
				List<HashMap<String,String>> xhxnList = service.getXhxnList(idArr);
				result = service.deleteDcjgffcs(xhxnList);
			}
			
			int num = 0;
			if(result){
				num = service.runDelete(idArr);
				result = num > 0;
			}
			
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}

		return null;
	}

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RwfbydcjgModel model = (RwfbydcjgModel) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		List<HashMap<String, String>> resultList = null;
		if("10335".equals(Base.xxdm)){
			resultList = service.getDcListForZD(model,user); //查询所有记录含发放次数，对浙大
		}else{
			resultList = service.getAllList(model,user);// 查询出所有记录，不分页
		}
		
		
		

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
	 * @描述:验证是否已申请
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-9-7 下午06:16:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return boolean 返回类型
	 * @throws
	 */
	public ActionForward isExists(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbydcjgModel model = (RwfbydcjgModel) form;
		boolean flag = service.isExists(model);
		response.getWriter().print(flag);
		return null;
	}
	
	/**
	 * @描述:保存
	 * @作者：XuWen[工号：1426]
	 * @日期：2016-1-12 下午02:16:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		//保存代偿基本信息
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		RwfbydcjgModel rwfbydcjgModel = (RwfbydcjgModel)form;
		RwfbydcjgService rwfbydcjgService = new RwfbydcjgService();
		boolean dcjgSaveResult = rwfbydcjgService.runInsert(rwfbydcjgModel);
		
		//浙江大学个性化字段
		if("10335".equals(Base.xxdm)){
			//保存发放次数信息
			if(dcjgSaveResult){
				List<DcjgffcsModel> ffcsList = rwfbydcjgModel.getFfcsList();
				if(ffcsList.size()!=0){
					dcjgSaveResult = false;
					dcjgSaveResult = rwfbydcjgService.saveDcjgffcs(rwfbydcjgModel.getXh(),rwfbydcjgModel.getXn(),ffcsList);
				}
			}
		}
		
		String messageKey = dcjgSaveResult ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述:更新
	 * @作者：XuWen[工号：1426]
	 * @日期：2016-1-12 下午02:16:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		//更新代偿基本信息
		RwfbydcjgModel rwfbydcjgModel = (RwfbydcjgModel)form;
		RwfbydcjgService rwfbydcjgService = new RwfbydcjgService();
		boolean dcjgUpdateResult = rwfbydcjgService.runUpdate(rwfbydcjgModel);
		
		//浙江大学个性化字段
		if("10335".equals(Base.xxdm)){
			//更新发放次数信息
			if(dcjgUpdateResult){
				List<DcjgffcsModel> ffcsList = rwfbydcjgModel.getFfcsList();
				if(ffcsList.size()!=0){
					dcjgUpdateResult = false;
					dcjgUpdateResult = rwfbydcjgService.updateDcjgffcs(rwfbydcjgModel.getXh(),rwfbydcjgModel.getXn(),ffcsList);
				}
			}
		}
		
		String messageKey = dcjgUpdateResult ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	//=================个性化导入导出，自定义多表=====================
	/**
	 * @描述:浙江大学个性化导入，含发放次数信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月17日 下午4:25:35
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
	public ActionForward importForZD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		//获取导入模块代码
		String drmkdm = request.getParameter("drmkdm");
		//查询模版信息
		HashMap<String,String> drmbxx = service.getDrmbxx(drmkdm);
		//查询导入规则信息
		List<HashMap<String,String>>  drgzxxList =  service.getDrgzxxList(drmkdm);
		
		request.setAttribute("drmbxx", drmbxx);
		request.setAttribute("drgzxxList", drgzxxList);
		return mapping.findForward("dcjgImport");
	}
	
	/**
	 * @描述:自定义模版下载（浙江大学个性化）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月18日 下午2:52:55
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
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		//获取导入模块代码
		String drmkdm = request.getParameter("drmkdm");
		
		//获得模版路径
		String path = servlet.getServletContext().getRealPath("/temp/importTemp/");
		
		//生成excel模版文件
		File file = service.getExcelTemplate(path,drmkdm);
		
		//响应下载
		response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(file.getName(),"utf-8")); 
		FileUtil.outputFile(response, file);
		
		//下载完成后删除生成的模版
		if(file.exists()){
			file.delete();
		}
		return null;
	}
	
	/**
	 * @throws SQLException 
	 * @描述:保存导入
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月19日 下午12:00:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward saveImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		//获取导入模块代码
		String drmkdm = request.getParameter("drmkdm");
		
		//得到FormFile对象，读取上传的Excel文件
		RwfbydcjgModel rwfbydcjgModel = (RwfbydcjgModel)form;
		FormFile formFile = rwfbydcjgModel.getImportFile();
		
		//返回保存结果：模版有误、导入成功信息、导入失败信息
		String path = servlet.getServletContext().getRealPath("/temp/importTemp/");
		HashMap<String,Object> resultMap = service.saveImport(formFile.getInputStream(),path,drmkdm);
		
		JSONObject resultJson = JSONObject.fromObject(resultMap); 
	    response.getWriter().print(resultJson);
		return null;
	}
	
	/**
	 * @描述:下载导入错误文件
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月19日 下午12:32:08
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
	public ActionForward downloadImportError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//得到tomcat/webapp/temp/importTemp下错误文件的路径
		String filename = request.getParameter("filename");
		String path = servlet.getServletContext().getRealPath("/temp/importTemp/");
		File file = new File(path,filename);
		if (file.exists()){
			response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(filename,"utf-8")); 
			FileUtil.outputFile(response, file);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:登记表下载
	 * @作者：lgx[工号:1553]
	 * @日期：2018-5-15 下午17:33:55
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
	
	public ActionForward getDjbWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbydcjgModel modelForm = (RwfbydcjgModel) form;
		RwfbydcjgModel model = service.getModel(modelForm.getId());
		File wordFile = getWord(model);
		FileUtil.outputWord(response, wordFile);
		
		return null;
	}
	
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		RwfbydcjgService service = new RwfbydcjgService();
		RwfbydcjgModel model = null;
		RwfbydcjgModel modelForm = null;
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				modelForm = new RwfbydcjgModel();
				modelForm.setId(values[i]);
				model = service.getModel(modelForm);
				File file = getWord(model);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	
	//填充模版数据生成word文件
	private File getWord(RwfbydcjgModel rwfbydcjgModel)
			throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		RwfbydcjgModel model = rwfbydcjgModel;
		String xh = model.getXh();
		//基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
		JtqkdcService jtqkService = new JtqkdcService();
		RwfbysqshService service = new RwfbysqshService();
		List<HashMap<String, String>> jtcyxxList= jtqkService.getJtcyList(xh);
		String fq = "";
		String mq = "";
		String qtcy = "";
		for (HashMap<String, String> map : jtcyxxList) {
			if("父亲".equals(map.get("cygxmc"))){
				fq = map.get("cyxm")+" " ;
				fq += (map.get("cylxdh") == null ? "" : map.get("cylxdh"));
			}else if("母亲".equals(map.get("cygxmc"))){
				mq = map.get("cyxm") + " ";
				mq += (map.get("cylxdh") == null ? "": map.get("cylxdh"));
			}else{
				qtcy +=  map.get("cyxm") + " " ;
				qtcy += map.get("cylxdh") == null ? "" : map.get("cylxdh");
				qtcy += "   ";
			}
		}
		HashMap< String, String> syddk = service.getSyddkxx(model.getId());
		data.put("syddk", syddk);
		HashMap< String, String> xyddk = service.getXyddkxx(model.getId());
		data.put("xyddk", xyddk);
		data.put("fq", fq);
		data.put("mq", mq);
		data.put("qtcy", qtcy);
		data.put("yx",Base.xxmc);
		data.put("xsjbxx", xsjbxx);
		data.put("model", model);
		File wordFile = null;
		wordFile = FreeMarkerUtil.getWordFile(data,  Constants.TEP_DIR + "yzrw","yzrwxfbcsqb_12688.xml", model.getXh() +"["+xsjbxx.get("xm")+"]" + "-");
		return wordFile;
		
	}
}
