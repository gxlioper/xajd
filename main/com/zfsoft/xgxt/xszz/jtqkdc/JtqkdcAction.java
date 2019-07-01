package com.zfsoft.xgxt.xszz.jtqkdc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import com.zfsoft.xgxt.xszz.jtqkdcjcsz.JtqkdcJcszForm;
import com.zfsoft.xgxt.xszz.jtqkdcjcsz.JtqkdcJcszService;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助管理模块
 * @类功能描述: 学生资助2013版之家庭情况调查
 * @作者： Penghui.Qu 
 * @时间： 2013-4-18 下午05:52:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class JtqkdcAction extends SuperAction {

	private static final String JTCY = "jtcy";
	private static final String JTQKDC = "jtqkdc";
	private static final String QTXX = "qtxx";
	private static final String FJXX = "fjxx";
	private static final String YXJTJJQK = "yxjtjjqk";
	private static final int CYSIZE = 6;//家庭成员最大支持数量
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> qtxxList = null;
	private List<HashMap<String,String>> jtcyList = null;
	private List<HashMap<String,String>> mkxxList = null;
	private List<HashMap<String,String>> jbxxList = null;
	private List<HashMap<String,String>> yxjtjjzkList = null;
	
	private static final String url = "xszz_jtqkdc_sq.do";
	
	/**
	 * 
	 * @描述:访问填写家庭情况调查页面
	 * @作者：Penghui.Qu
	 * @日期：2013-4-18 下午05:51:42
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
	public ActionForward dcxxModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcJcszService jtszService = new JtqkdcJcszService();
		JtqkdcJcszForm jcszModel = jtszService.getModel();
		
		//跳转不需要控制权限(困难生认定)
		String writeAble = request.getParameter("writeAble");
		if (!"yes".equals(writeAble)&&(jcszModel== null||(jcszModel != null && !Boolean.valueOf(jcszModel.getIsOpen())))){
			request.setAttribute("message", "现在未开放家庭情况填写。");
			return mapping.findForward("error");
		}
		
		JtqkdcForm model = (JtqkdcForm) form;
		JtqkdcService service = new JtqkdcService();
		
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//按学号加载成员列表 
			List<HashMap<String,String>> cyList = service.getJtcyList(model.getXh());
			request.setAttribute("cyList", cyList);
			
			//家庭成员列表空行
			int blankSize = (CYSIZE - cyList.size()) < 0 ? 0 : (CYSIZE - cyList.size());
			request.setAttribute("blankList", service.getBlankList(blankSize));
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			request.setAttribute("type", model.getType());
			//家庭情况调查信息加载
			JtqkdcForm jtqkModel = service.getModel(model.getXh());
			
			if (jtqkModel != null){
				BeanUtils.copyProperties(model, StringUtils.formatData(jtqkModel));
			}else{
				model.setJtszdssxdm(xsjbxx.get("jtdzx"));
				model.setJtdz(xsjbxx.get("jtdz"));
			}
		} else {
			//家庭成员列表空行
			request.setAttribute("blankList", service.getBlankList(CYSIZE));
		}

		//家庭成员显示配置
		jtcyList = bdpzService.getBdpz(JTCY);
		request.setAttribute("jtcyList", jtcyList);
		
		//其他信息显示配置
		qtxxList = bdpzService.getBdpz(QTXX);

		//家庭情况显示配置
		mkxxList = bdpzService.getBdpz(JTQKDC);

		//影响家庭经济状况有关详细信息
		yxjtjjzkList = bdpzService.getBdpz(YXJTJJQK);
		
		//附件信息显示配置（是否必填配置）
		List<HashMap<String,String>> fjxxList = bdpzService.getBdpz(FJXX);
		if(null != fjxxList && fjxxList.size()>0){			
			HashMap<String,String> fjxx = fjxxList.get(0);
			request.setAttribute("fjxx", fjxx);
		}
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("qtxxList", qtxxList);
		request.setAttribute("yxjtjjzkList", yxjtjjzkList);
		

		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(JTQKDC);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xxdm", Base.xxdm);
		
		String path = "xszz_jtqkdc_sq.do";
		request.setAttribute("path", path);
		request.setAttribute("mkxxForm", model);
		request.setAttribute("jtrjysrsx", MessageUtil.getText("jtrjysrsx"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dcxxModify");
	}
	
	
	
	
	/**
	 * 
	 * @描述:家庭情况调查单个显示
	 * @作者：Penghui.Qu
	 * @日期：2013-4-19 上午11:14:37
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
	public ActionForward dcxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcForm model = (JtqkdcForm) form;
		JtqkdcService service = new JtqkdcService();
		
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		JtqkdcForm jtqkModel = service.getModel(model);
		
		if (jtqkModel != null){
			//按学号加载成员列表 
			List<HashMap<String,String>> cyList = service.getJtcyList(model.getXh());
			request.setAttribute("cyList", cyList);
			
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//基本情况当中的其他信息
			qtxxList = bdpzService.getBdpz(QTXX);
			

			//家庭成员显示配置
			jtcyList = bdpzService.getBdpz(JTCY);

			//家庭情况显示配置
			mkxxList = bdpzService.getBdpz(JTQKDC);

			//影响家庭经济状况有关详细信息
			yxjtjjzkList = bdpzService.getBdpz(YXJTJJQK);

					//学生基本信息显示配置
			jbxxList = bdpzService.getJbxxpz(JTQKDC);
			request.setAttribute("jtcyList", jtcyList);
			request.setAttribute("mkxxList", mkxxList);
			request.setAttribute("qtxxList", qtxxList);
			request.setAttribute("yxjtjjzkList", yxjtjjzkList);
			
			request.setAttribute("jbxxList", jbxxList);
			request.setAttribute("mkxxForm", model);
			request.setAttribute("xxdm", Base.xxdm);
			request.setAttribute("xh", model.getXh());
			BeanUtils.copyProperties(model, StringUtils.formatData(jtqkModel));
			return mapping.findForward("dcxxView");
		} else {
			return dcxxModify(mapping, form, request, response);
		}
		
	}
	
	
	
	
	/**
	 * 
	 * @描述:保存家庭情况调查信息
	 * @作者：Penghui.Qu
	 * @日期：2013-4-18 下午05:52:35
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
	@SystemLog(description="访问学生资助-家庭情况-填写家庭情况-增加或修改保存-XH:{xh},SFDQ:{sfdq},LSZN:{lszn}," +
			"SFGC:{sfgc},JTDZ:{jtdz},JTDH:{jtdh},JTYB:{jtyb},JTRJSR:{jtrjsr},JTNZSR:{jtnzsr},YHZZQK:{yhzzqk}," +
			"JTSYQK:{jtsyqk},CJNMQK:{cjnmqk},JTSZQK:{jtszqk},JTQZQK:{jtqzqk},JTQTQK:{jtqtqk},TFSJQK:{tfsjqk},JTHK:{jthk}")
	public ActionForward saveDcxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcForm model = (JtqkdcForm) form;
		JtqkdcService service = new JtqkdcService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		boolean result = service.runInsert(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	
	
	/**
	 * 
	 * @描述:家庭情况调查结果查询
	 * @作者：Penghui.Qu
	 * @日期：2013-4-19 上午11:13:59
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
	@SystemAuth(url = "xszz_jtqkdc_cx.do")
	public ActionForward dcxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcForm model = (JtqkdcForm) form;
		JtqkdcService service = new JtqkdcService();
		
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
		
		String path = "xszz_jtqkdc_cx.do";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dcxxManage");
	}
	
	
	
	/**
	 * 
	 * @描述:家庭情况调查删除
	 * @作者：Penghui.Qu
	 * @日期：2013-4-19 下午01:47:24
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
	@SystemLog(description="访问学生资助-家庭情况-填写家庭情况-删除-VALUES:{values}")
	public ActionForward dcxxDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcService service = new JtqkdcService();
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	

	/**
	 * 
	 * @描述:家庭情况显示--提供给其它页面嵌入
	 * @作者：Penghui.Qu
	 * @日期：2013-4-20 下午03:24:13
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
	public ActionForward jtqkInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcForm model = (JtqkdcForm) form;
		JtqkdcService service = new JtqkdcService();
		
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		JtqkdcForm jtqkModel = service.getModel(model);
		
		//按学号加载成员列表 
		List<HashMap<String,String>> cyList = service.getJtcyList(model.getXh());
		request.setAttribute("cyList", cyList);

		//家庭成员显示配置
		jtcyList = bdpzService.getBdpz(JTCY);

		//家庭情况显示配置
		mkxxList = bdpzService.getBdpz(JTQKDC);


			
		request.setAttribute("jtcyList", jtcyList);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("mkxxForm", StringUtils.formatData(model));
		
		if (jtqkModel != null){
			BeanUtils.copyProperties(model, StringUtils.formatData(jtqkModel));
		}
		
		
		return mapping.findForward("jtqkInfo");
	}
	/**
	 * 家庭情况显示--提供给其它页面嵌入（班级评议代表查看其他学生信息【学生资助-困难生认定-班级评议结果查询】）
	 */
	@SystemAuth(url = url)
	public ActionForward jtqkInfoForStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		JtqkdcForm model = (JtqkdcForm) form;
		JtqkdcService service = new JtqkdcService();
		
		JtqkdcForm jtqkModel = service.getModel(model);
		
		//按学号加载成员列表 
		List<HashMap<String,String>> cyList = service.getJtcyList(model.getXh());
		request.setAttribute("cyList", cyList);
		
		//家庭成员显示配置
		jtcyList = bdpzService.getBdpz(JTCY);
		
		//家庭情况显示配置
		mkxxList = bdpzService.getBdpz(JTQKDC);
		
		request.setAttribute("jtcyList", jtcyList);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("mkxxForm", StringUtils.formatData(model));
		
		if (jtqkModel != null){
			BeanUtils.copyProperties(model, StringUtils.formatData(jtqkModel));
		}
		
		
		return mapping.findForward("jtqkInfo");
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
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward getJtqkdcWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcForm myForm = (JtqkdcForm) form;
		
		String xh = myForm.getXh();
		File wordFile = getJtqkdcWord(xh);
		FileUtil.outputWord(response, wordFile);
		
		return null;
	}


	
	//填充模版数据生成word文件
	private File getJtqkdcWord(String xh)
			throws Exception {
		String xxdm = Base.xxdm;
		JtqkdcService service = new JtqkdcService();
		Map<String,Object> data = new HashMap<String,Object>();
		//学生照片
		XsxxService xsxxService = new XsxxService();
		InputStream is = xsxxService.getPhotoStream(xh);
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		if (!StringUtils.isNull(xh)){
			//家庭情况
			JtqkdcForm model = service.getModel(xh);
			//按学号加载成员列表 
			List<HashMap<String,String>> cyList = service.getJtcyList(model.getXh());
			
			List<HashMap<String, String>> cyList4line = service.getJtcyList(xh,4);
			List<HashMap<String, String>> cyList5line = service.getJtcyList(xh,5);
			data.put("cyList4line", cyList4line);
			data.put("cyList5line", cyList5line);

			//基本信息
			//XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			//if(xxdm.equals("13871")||xxdm.equals("10279")) {
			if(StringUtils.isNotNull(xsjbxx.get("csrq"))){
				data.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month).replaceAll("年", ".").replaceAll("月", ""));// 出生年月
			}
			//}
			data.putAll(xsjbxx);
			data.put("xxmc", Base.xxmc);
			data.put("jtqk", model);
			data.put("cyList", cyList);
			//家庭成员列表空行
			int blankSize = (CYSIZE - cyList.size()) < 0 ? 0 : (CYSIZE - cyList.size());
			data.put("blankList", service.getBlankList(blankSize));
			File wordFile=null;
			try{
				ResourceUtils.getFile("classpath://templates//xszz//jtqkdc_"+Base.xxdm+".xml");
				wordFile = FreeMarkerUtil.getWordFile(data,"classpath://templates//xszz","jtqkdc_"+xxdm+".xml",xh+"-"+xsjbxx.get("xm"));
			}catch (Exception e) {
				wordFile = FreeMarkerUtil.getWordFile(data,"classpath://templates//xszz","jtqkdc.xml",xh+"-"+xsjbxx.get("xm"));
			}
			return wordFile;
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 下载ZIP
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-21 下午05:27:29
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
	public ActionForward getJtqkdcZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getJtqkdcWord(values[i]);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	


	/**
	 * 
	 * @描述: 导出
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2014-11-21 下午03:49:27
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
		JtqkdcForm model = (JtqkdcForm) form;
		JtqkdcService service = new JtqkdcService();
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		//查询
		List<HashMap<String,String>> resultList = service.getAllList(model,user);
		
		
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
