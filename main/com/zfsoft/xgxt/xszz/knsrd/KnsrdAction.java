package com.zfsoft.xgxt.xszz.knsrd;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszDao;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszForm;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助管理模块
 * @类功能描述: 学生资助2013版 困难生认定 
 * @作者： Penghui.Qu 
 * @时间： 2013-4-20 上午11:38:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class KnsrdAction extends SuperAction {

	private static final String KNSRD = "knsrd";
	private static final String SQZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> mkxxList = null;
	private List<HashMap<String,String>> jbxxList = null;
	private static final String OPEN = "1";
	private KnsrdService service = new KnsrdService();
	private static final String url = "xszz_knsrd_sq.do";
	
	/**
	 * 
	 * @描述:困难生申请管理
	 * @作者：Penghui.Qu
	 * @日期：2013-4-22 上午10:25:18
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
	public ActionForward knssqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		User user = getUser(request);
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		
		//基础设置认定周期不为空
		if(jcszModel != null && jcszModel.getRdxn() != null) {
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_xn(new String[]{jcszModel.getRdxn()});
			if("xq".equals(SQZQ)){
				searchModel.setSearch_tj_xq(new String[]{jcszModel.getRdxq()});
			}
			request.setAttribute("searchTj", searchModel);
		}
		
		//参数设置已设置
		if(jcszModel != null){
			request.setAttribute("sfysq", service.getExists(model, user.getUserName()));
		}
		
		request.setAttribute("sqzq", SQZQ);
		request.setAttribute("jcszModel", jcszModel);
		String path = "xszz_knsrd_sq.do";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("knssqManage");
	}
	
	
	
	/**
	 * 
	 * @描述:困难生认定--申请
	 * @作者：Penghui.Qu
	 * @日期：2013-4-20 上午11:38:24
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
	public ActionForward knssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		request.setAttribute("type", model.getType());
		
		KnsdcService knsdcSerivce = new KnsdcService();
		List<HashMap<String, String>> knyyList=knsdcSerivce.getKnyyList();
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			JcszService jcszService = new JcszService();
			JcszForm jcszModel = jcszService.getModel();
			if(!"update".equals(model.getType())){
				model.setXn(jcszModel.getRdxn());
			}
			if("xn".equals(SQZQ)){
				model.setXq("on");
			}else{
				if(!"update".equals(model.getType())){
					model.setXq(jcszModel.getRdxq());
				}
			}
			KnsrdForm knsrdModel = service.getModelByXnXq(model);
			
			if (knsrdModel != null){
				BeanUtils.copyProperties(model, StringUtils.formatData(knsrdModel));
			}
			
			if (jcszModel != null && OPEN.equals(jcszModel.getSfwcjtdc()) ){
				JtqkdcService jtqkService = new JtqkdcService();
				JtqkdcForm jtqkForm = new JtqkdcForm();
				jtqkForm.setXh(model.getXh());
				JtqkdcForm jtqkModel = jtqkService.getModel(jtqkForm);
				request.setAttribute("openJtqk", jtqkModel == null);
			}
			request.setAttribute("ishave",service.getExists(model, model.getXh()));
			if("10277".equals(Base.xxdm)) {
				knyyList=new KnsrdService().getKnnyList(model.getYlzd5());	
			}
		}
		//获取困难生基本设置
		JcszService jcszService=new JcszService();
		JcszForm jcszForm=jcszService.getModel();
		request.setAttribute("sqsftxdc", jcszForm.getSqsftxdc());
		request.setAttribute("isopen", jcszForm.getIsopen());
		JcszForm jcszModel = jcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		
		//困难生档次列表
		request.setAttribute("knsdcList", knsdcSerivce.getKnsdcList());
		request.setAttribute("knyyList", knyyList);
		//困难生认定显示配置
		mkxxList = bdpzService.getBdpz(KNSRD);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		String path = "xszz_knsrd.do?method=knssq";
		request.setAttribute("path", path);
		request.setAttribute("mkxxForm", StringUtils.formatData(model));
		if("10742".equals(Base.xxdm)) {
			request.setAttribute("sqlymcList", service.getSqlymcList());		
		}
		if("12389".equals(Base.xxdm)) {
			List<HashMap<String, String>> sqlyList = service.getSqlyList();
			request.setAttribute("sqlyList", sqlyList);
		}
		//杭州师范大学加载家庭困难类型和高档消费品类型 
		if("10346".equals(Base.xxdm)){
			request.setAttribute("jtknlxList", service.getJtknlxList());
			request.setAttribute("gdxfplxList", service.getGdxfpLxList());
		}
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("knssq");
	}
	
	/**
	 * @描述:困难生认定申请改为异步加载时，新增的判断是否已申请的方法
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月21日 下午2:08:53
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
	public ActionForward isHaveKnsrd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//		request.setAttribute("type", model.getType());
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		boolean ishave = service.getExists(model, model.getXh());
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		map.put("ishave", ishave);
		JSONObject data = JSONObject.fromMap(map);
		response.getWriter().print(data);
		
		return null;
	}
	
	/**
	 * 
	 * @描述:困难生认定申请 
	 * @作者：Penghui.Qu
	 * @日期：2013-4-22 上午08:43:38
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
	@SystemLog(description="访问学生资助-困难生认定-困难生申请-增加或修改申请保存-XH:{xh},SQLY:{sqly}")
	public ActionForward saveKnssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		if (!isTokenValid(request)){
//			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//			return null;
//		} else {
//			super.resetToken(request);
//		}
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		boolean result = service.saveKnssq(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-困难生认定-困难生申请-提交VALUES:{values}")
	public ActionForward subBusi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdService service = new KnsrdService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		String xh = request.getParameter("xh");
		String shzt = request.getParameter("shzt");
		
		if(!Constants.YW_YTH.equalsIgnoreCase(shzt)){
			JcszDao jcszDao = new JcszDao();
			JcszForm jcszModel = jcszDao.getModel();
			lcid = jcszModel.getSplc();
		}
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			xh=user.getUserName();
		}
		boolean result = service.submitRecord(values,lcid,xh);
		if(result){
			//更新业务状态为'审核中'
			KnsrdForm model = new KnsrdForm();
			model.setGuid(values);
			model.setShzt(Constants.YW_SHZ);
			model.setShlc(lcid);
			service.updateModel(model);
		}
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-困难生认定-困难生申请-撤销VALUES:{values}")
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdService service = new KnsrdService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = service.cancleRecord(values,lcid);
		if(result){
			//更新业务状态为'未提交'
			KnsrdForm model = new KnsrdForm();
			model.setGuid(values);
			ShlcDao shlcDao = new ShlcDao();
			if(Integer.valueOf(shlcDao.getExistTh(values))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.updateModel(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述:删除困难生申请
	 * @作者：Penghui.Qu
	 * @日期：2013-4-22 下午01:14:49
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
	@SystemLog(description="访问学生资助-困难生认定-困难生申请-删除VALUES:{values}")
	public ActionForward delKnssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdService service = new KnsrdService();
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
	 * @描述:流程跟踪
	 * @作者：Penghui.Qu
	 * @日期：2013-4-22 下午01:53:42
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
	public ActionForward getSplcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		
		List<HashMap<String,String>> infoList = service.getSplcInfo(model);
		
		request.setAttribute("infoList", infoList);
		return mapping.findForward("knsrdLcgz");
	}
	
	
	
	/**
	 * 
	 * @描述:困难生认定审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-20 上午11:39:03
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
	@SystemAuth(url = "xszz_knsrd_sh.do")
	public ActionForward knsshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getShjlList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		//基础设置认定周期不为空
		if(jcszModel != null && jcszModel.getRdxn() != null) {
			
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_xn(new String[]{jcszModel.getRdxn()});
			if("xq".equals(SQZQ)){
				searchModel.setSearch_tj_xq(new String[]{jcszModel.getRdxq()});
			}
			request.setAttribute("searchTj", searchModel);
		}
		
		request.setAttribute("sqzq", SQZQ);
		request.setAttribute("jcszModel", jcszModel);
		request.setAttribute("xxdm", Base.xxdm);
		String path = "xszz_knsrd_sh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("knsshManage");
	}
	
	
	
	/**
	 * 
	 * @描述:困难生认定--单个审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-20 上午11:39:33
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
	@SystemAuth(url = "xszz_knsrd_sh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward knsrdDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		
		String xtgwid = myForm.getXtgwid();
		KnsrdForm model = service.getModel(myForm);
		model.setShid(myForm.getShid());
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String,String>> infoList = service.getSplcInfo(model);
			request.setAttribute("infoList", infoList);
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		
		myForm.setXtgwid(xtgwid);
		request.setAttribute("mkxxForm", StringUtils.formatData(myForm));
		
		//获取困难生基本设置
		JcszService jcszService=new JcszService();
		JcszForm jcszForm=jcszService.getModel();
		request.setAttribute("sqsftxdc", jcszForm.getSqsftxdc());
		
		KnsdcService knsdcSerivce = new KnsdcService();
		
		//困难生档次列表
		request.setAttribute("knsdcList", knsdcSerivce.getKnsdcList());
		//浙大个性化无偿资助金额列表
		request.setAttribute("wczzjeList", knsdcSerivce.getWczzList());
		
		KnsjgService knsjgService = new KnsjgService();
		//加载困生认定记录
		request.setAttribute("rdlsjlList", knsjgService.getKnsInfoList(model.getXh()));		 
		
		//加载最新审核通过困难生档次
		HashMap<String, String> newKnsdc = service.getNewKnsdc(myForm);
		myForm.setRddc(newKnsdc.get("zd2"));
		//江西航空回写困难排序
		if("13871".equals(Base.xxdm)){
			myForm.setKnpx(newKnsdc.get("zd6"));
		}
		//浙江经济职业技术学院 民主评议结果
		if("12866".equals(Base.xxdm)) {
			myForm.setYlzd4(service.getNewBjpjjg(myForm).get("zd5"));
		}
		
//		String knyy = service.getNewKnyy(myForm).get("zd10");
//		myForm.setYlzd10(null==knyy?myForm.getYlzd5():knyy); //取推荐原因
		//上海体育学院个性化，困难原因列表
		if("10277".equals(Base.xxdm)) {
        	String knyydm=myForm.getYlzd10();
			request.setAttribute("knyyList", new KnsrdService().getKnnyList(knyydm));	
		}
		
		//困难生认定显示配置
		mkxxList = bdpzService.getBdpz(KNSRD);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xxdm", Base.xxdm);
		if("11998".equals(Base.xxdm)){
			request.setAttribute("zf",request.getParameter("zf"));
		}
		if("12389".equals(Base.xxdm)){
			if(!StringUtils.isNull(myForm.getYlzd9())){
				String[] sqlyList  = service.getSqlyListByDms(myForm.getYlzd9().split(","));
				request.setAttribute("sqlyList",sqlyList);
			}
		}
		return mapping.findForward("knsrdDgsh");
	}
	
	

	/**
	 * 
	 * @描述:困难生认定单个审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-23 上午11:35:24
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
	@SystemAuth(url = "xszz_knsrd_sh.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-困难生认定-困难生审核-审核保存-GUID:{guid}")
	public ActionForward saveKnssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdForm myForm = (KnsrdForm) StringUtils.formatBean(form);
		KnsrdService service = TransactionControlProxy.newProxy(new KnsrdService());
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
//		myForm.setYlzd10(myForm.getYlzd5()); //推荐原因
		boolean isSuccess = service.saveKnssh(myForm, user);
		String messageKey = isSuccess ? MessageKey.SYS_AUD_SUCCESS : MessageKey.SYS_AUD_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述:撤消困难生审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-24 上午10:22:56
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
	@SystemAuth(url = "xszz_knsrd_sh.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-困难生认定-困难生审核-撤销GUID:{guid}")
	public ActionForward cancelKnssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		//HashMap<String,String> shxx = ShlcUtil.getShxx(myForm.getShid());	
		// 业务回滚
		boolean result = service.cancelKnssh(myForm.getGuid());
		// 业务回滚成功
		//boolean isSuccess = service.cancelKnssh(myForm);
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述:困难生认定--批量审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-24 上午11:41:21
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
	@SystemAuth(url = "xszz_knsrd_sh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward knsrdPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsdcService knsdcSerivce = new KnsdcService();
		//困难生档次列表
		request.setAttribute("knsdcList", knsdcSerivce.getKnsdcList());
		//浙大个性化无偿资助金额列表
		request.setAttribute("wczzjeList", knsdcSerivce.getWczzList());
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("sjdcmc",request.getParameter("sjdcmc"));
		
		return mapping.findForward("knsrdPlsh");
	}
	
	
	
	/**
	 * 
	 * @描述:困难生审核--批量审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-24 下午02:04:34
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
	@SystemAuth(url = "xszz_knsrd_sh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = TransactionControlProxy.newProxy(new KnsrdService());
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		String message = service.savePlsh(myForm, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	
	/**
	 * 
	 * @描述:困难生认定查看
	 * @作者：Penghui.Qu
	 * @日期：2013-4-27 下午02:04:07
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
	public ActionForward knsrdView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		
		KnsrdForm model = service.getModel(myForm);
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String,String>> infoList = service.getSplcInfo(model);
			request.setAttribute("infoList", infoList);
			
			BeanUtils.copyProperties(myForm, model);
		}
		JcszService jcszService=new JcszService();
		JcszForm jcszForm=jcszService.getModel();
		request.setAttribute("sqsftxdc", jcszForm.getSqsftxdc());

		//困难生认定显示配置
		mkxxList = bdpzService.getBdpz(KNSRD);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("mkxxForm", myForm);
		KnsjgService knsjgService = new KnsjgService();
		List<HashMap<String, String>> knsInfoList = knsjgService
				.getKnsInfoList(myForm.getXh());
		request.setAttribute("knsInfoList", knsInfoList);
		if("11998".equals(Base.xxdm)){
			request.setAttribute("zf",request.getParameter("zf"));
		}
		if("12389".equals(Base.xxdm)){
			if(!StringUtils.isNull(myForm.getYlzd9())){
				String[] sqlyList  = service.getSqlyListByDms(myForm.getYlzd9().split(","));
				request.setAttribute("sqlyList",sqlyList);
			}
		}
		return mapping.findForward("knsrdView");
	}
	
	
	
	/**
	 * 
	 * @描述:打印困难生申请信息
	 * @作者：maxh
	 * @日期：2013-4-26 下午02:19:37
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
	public ActionForward printJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdForm myForm = (KnsrdForm) form;
		String guids[]=myForm.getGuid().split(",");
		if(null!=guids&&guids.length==1){//一条数据
			if (myForm != null){
				File file=print(myForm,guids[0],request);
				FileUtil.outputWord(response, file);
			}
		}else{//多条数据
			List<File> files = new ArrayList<File>();
			for(String guid:guids){
				File file=print(myForm,guid,request);
				files.add(file);
				}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	/**
	 * 
	 * @描述:获取打印File
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-20 下午05:33:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File print(KnsrdForm myForm,String guid,HttpServletRequest request) throws Exception{
		myForm.setGuid(guid);
		KnsrdService service = new KnsrdService();
		File file=null;
		KnsrdForm model = service.getModel(myForm);
		if(StringUtils.equals("12389", Base.xxdm)){
			String ylzd9 = model.getYlzd9();
			if(!StringUtils.isNull(ylzd9)){
				String[] temp = ylzd9.split(",");
				String[] sqlyArr = service.getSqlyListByDms(temp);
				String sqly = Arrays.toString(sqlyArr);
				sqly=sqly.substring(1,sqly.lastIndexOf("]"));
				model.setSqly(sqly);
			}
		}
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			//审批信息
			List<HashMap<String,String>> infoList = service.getSplcInfo(model);
			
			BeanUtils.copyProperties(myForm, model);
			 //困难生档次list
			KnsdcService knsdcService = new KnsdcService();
			file=service.printForWord(xsjbxx,infoList,model,knsdcService.getKnsdcList(), model.getRddc(),request);
		}
		return file;
	}
	
	/**
	 * 
	 * @描述: 困难生认定审核情况
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-22 上午10:23:32
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
	public ActionForward knsrdShqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		User user = getUser(request);
		KnsrdService service = new KnsrdService();
		Map<String,Object> shqkInfo = service.getShqkInfo(user);
		
		request.setAttribute("shqkInfo", shqkInfo);
		return mapping.findForward("knsrdShqk");
	}
	

	/**
	 * 
	 * @描述:导出功能
	 * @作者：ligl
	 * @日期：2013-5-22 上午10:44:47
	 * @修改记录: 
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
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		//查询
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
		if(StringUtils.equals("12389", Base.xxdm)){
			for(int i=0;i<resultList.size();i++){
				HashMap<String,String> map = resultList.get(i);
				String ylzd9 = map.get("ylzd9");
				if(!StringUtils.isNull(ylzd9)){
					String[] temp = ylzd9.split(",");
					String[] sqlyArr = service.getSqlyListByDms(temp);
					String sqly = Arrays.toString(sqlyArr);
					sqly=sqly.substring(1,sqly.lastIndexOf("]"));
					map.put("sqly",sqly);
				}
				resultList.set(i,map);
			}
		}
		
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
	 * @描述: 审核导出(自定义导出设置)
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2014-12-15 下午01:53:50
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
	public ActionForward exportDataSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		//根据不同的审核类型 去自定义导出
		String shlx = request.getParameter("shlx");
		model.setShzt(shlx);
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllListSh(model,user);//查询出所有记录，不分页
		if(StringUtils.equals("12389", Base.xxdm)){
			for(int i=0;i<resultList.size();i++){
				HashMap<String,String> map = resultList.get(i);
				String ylzd9 = map.get("ylzd9");
				if(!StringUtils.isNull(ylzd9)){
					String[] temp = ylzd9.split(",");
					String[] sqlyArr = service.getSqlyListByDms(temp);
					String sqly = Arrays.toString(sqlyArr);
					sqly=sqly.substring(1,sqly.lastIndexOf("]"));
					map.put("sqly",sqly);
				}
				resultList.set(i,map);
			}
		}
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
	 * @描述: 按审核情况查询具体学生
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-3 下午04:27:45
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
	public ActionForward getStudentsByShqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getStudentsList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", "xszz_knsrd_shtj.do");
		return mapping.findForward("studentsList");
	}
	
	/**
	 * 
	 * @描述:困难生申请流程图
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-1-19 上午10:41:50
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
	public ActionForward printLct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		File file=print(request);
		FileUtil.outputWord(response, file);
				
		return null;
	}
	
	
	private synchronized File print(HttpServletRequest request) throws Exception{
		return FreeMarkerUtil.getWordFile(null, Constants.TEP_DIR + "xszz", "knssqlct.xml", FreeMarkerUtil.getFileName("申请流程图"));
	}
	
	/**
	 * @描述: 浙大个性化_困难生一级审核通过后各院系特困生与困难生的比例
	 * @作者： 孟威[工号：1186]
	 * @日期：2016-10-19 下午08:18:37
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
	public ActionForward viewYxKnsfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		if (QUERY.equalsIgnoreCase(model.getType())){
			List<HashMap<String,String>> resultList = service.getXyKnsfp (model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		return mapping.findForward("viewYxKnsfp");
	}
	
	/**
	 * 
	 * @描述: 杭师大个性化登记表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-16 上午11:24:18
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
	public ActionForward printDjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdForm myForm = (KnsrdForm) form;
		File wordFile = printWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @描述: 杭师大个性化获取wordb表格
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-16 上午11:39:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File printWord(KnsrdForm myForm,HttpServletRequest request) throws Exception{
		String lx = request.getParameter("lx");
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		KnsrdForm model = service.getModel(myForm);
		data.put("rs",model );
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(model.getXh());
		data.putAll(xsxxMap);
		data.put("nowdate", GetTime.getTimeByFormat("yyyy-mm-dd"));
		try{
			if("bgs".equals(lx)){
				ResourceUtils.getFile(Constants.TEP_DIR+"xszz/jtbgs_10346.xml");
				file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"xszz","jtbgs_10346.xml",FreeMarkerUtil.getFileName(xsxxMap.get("xm")+"_结题报告书"));
			}else if("lxs".equals(lx)){
				ResourceUtils.getFile(Constants.TEP_DIR+"xszz/lxsbs_10346.xml");
				file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"xszz","lxsbs_10346.xml",FreeMarkerUtil.getFileName(xsxxMap.get("xm")+"_立项申报书"));
			}else{
				logger.info("杭师大无此类型登记表！");
				return null;
			}
			
		}catch (Exception e) {
			logger.info(e);
		}
		return file;
	}
	
	/**
	 * 
	 * @描述: 杭师大个性化登记表导出
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-16 上午11:25:37
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
	public ActionForward printDjbTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdForm myForm = (KnsrdForm) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setGuid(values[i]);
				File file = printWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @描述:打印困难生申请信息（浙江同济科技职业学院）
	 * @作者：maxh
	 * @日期：2013-4-26 下午02:19:37
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
	public ActionForward viewPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		KnsrdForm model = service.getModel(myForm);
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("xsxx", xsjbxx);
			request.setAttribute("csrq", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));
			
			//审批信息
			HashMap<String, String> spxxInfo =service.getSpxxInfo(model.getGuid());
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkmodel = jtqkService.getModel(model.getXh());
			request.setAttribute("jtrjsr", jtqkmodel==null ? "" : jtqkmodel.getJtrjsr());
			request.setAttribute("knsmodel", model);
			request.setAttribute("sqrdly", HtmlUtil.xmlZy(model.getSqly()));
			request.setAttribute("spxxInfo", spxxInfo);
			BeanUtils.copyProperties(myForm, model);
		}
		return mapping.findForward("viewPrint");
	}
	
}
