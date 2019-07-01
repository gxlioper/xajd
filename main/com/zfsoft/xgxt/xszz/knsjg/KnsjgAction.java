/**
 * @部门:学工产品事业部
 * @日期：2013-4-20 下午01:12:13 
 */  
package com.zfsoft.xgxt.xszz.knsjg;

import java.io.File;
import java.net.URLEncoder;
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
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsrd.KnsrdForm;
import com.zfsoft.xgxt.xszz.knsrd.KnsrdService;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsrdbjpy.KnsrdbjpyForm;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsrdbjpy.KnsrdbjpyService;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgForm;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;

import common.Globals;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 困难生查询管理模块
 * @类功能描述: Action 
 * @作者： maxh
 * @时间： 2013-4-20 下午01:12:13 
 * @版本： V1.0
 */
public class KnsjgAction extends SuperAction{
	
	
	private static final String KNSRD = "knsrd";
	private static final String SQZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "xszz_knsjg_cx.do";
	
	/**
	 * 
	 * @描述:困难生查询
	 * @作者：maxh
	 * @日期：2013-4-20 下午03:10:33
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
	public ActionForward getKnsjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();
		
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
		
		if(!Globals.XXDM_ZJDX.equals(Base.xxdm)){
			SearchModel searchModel=new SearchModel();
			//武昌首义个性化
			if("12309".equals(Base.xxdm)){
				searchModel.setSearch_tj_sfzx(new String[]{"在校"});
			}else{
				searchModel.setSearch_tj_xn(new String[]{Base.currXn});
				if("xq".equals(SQZQ)){
					searchModel.setSearch_tj_xq(new String[]{Base.currXq});
				}
			}
			request.setAttribute("searchTj", searchModel);
		}
		String xq=Base.currXq;
		if("xn".equals(SQZQ)){
			xq="on";
		}
		List<HashMap<String, String>> list=service.getKnsjgZqListNotIsHave(Base.currXn + "," + xq);
		request.setAttribute("iscanCopy", null==list||list.size()<=0?"0":"1");
		String path = "xszz_knsjg_cx.do";
		request.setAttribute("sqzq", SQZQ);
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("knsjg");
	}
	/**
	 * 
	 * @描述:增加困难生认定信息
	 * @作者：maxh
	 * @日期：2013-4-22 上午10:49:48
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
	@SystemLog(description="访问学生资助-困难生认定-困难生查询-增加-XH:{xh},XN:{xn},RDDC:{rddc}")
	public ActionForward addKnsjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsjgForm model = (KnsjgForm) form;
		KnsdcService knsdcService = new KnsdcService();
		KnsjgService service = new KnsjgService();
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
	
        if (SAVE.equalsIgnoreCase(model.getType())){
        	if (!isTokenValid(request)){
    			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
    			return null;
    		} else {
    			super.resetToken(request);
    		}
        	//唯一性判断（学号，学年，学期）
        	boolean isExist=service.isExistByKnsjg(model,SAVE);
        	if(!isExist){
	        	//添加困难生认定信息
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		String message = MessageUtil.getText(MessageKey.XSZZ_KNSJG_RESULT_REPEAT_XN,model.getXn());
        		 if("xq".equals(SQZQ)){
        			 message = MessageUtil.getText(MessageKey.XSZZ_KNSJG_RESULT_REPEAT_XQ,new String[]{model.getXn(),model.getXq()});
        		}
        		response.getWriter().print(getJsonMessage(message));
				return null;
        	}
		}
        model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
        model.setXn(Base.currXn);
        if("xq".equals(SQZQ)){
        	 model.setXq(Base.currXq);
		}
        //困难生档次list
		request.setAttribute("knsdcList", knsdcService.getKnsdcList());
		//浙大个性化无偿资助金额列表
		request.setAttribute("wczzjeList", knsdcService.getWczzList());
		request.setAttribute("xxdm", Base.xxdm);
		//学年list
		request.setAttribute("xnList", Base.getXnndList());
		//学期list
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("knyyList", knsdcService.getKnyyList());//困难原因
		String path = "xszz_knsjg.do?method=addKnsjg";
		request.setAttribute("path", path);
		request.setAttribute("mkxxForm", StringUtils.formatData(model));

		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("sqzq", SQZQ);
		request.setAttribute("jbxxList", jbxxList);
		if("10742".equals(Base.xxdm)) {
			KnsrdService knsrdService = new KnsrdService();
			request.setAttribute("sqlymcList", knsrdService.getSqlymcList());		
		}
		//杭州师范大学加载家庭困难类型和高档消费品类型 
		if("10346".equals(Base.xxdm)){
			KnsrdService knsrdService = new KnsrdService();
			request.setAttribute("jtknlxList", knsrdService.getJtknlxList());
			request.setAttribute("gdxfplxList", knsrdService.getGdxfpLxList());
		}
		this.saveToken(request);
		return mapping.findForward("addknsjg");
	}
	/**
	 * 
	 * @描述:修改困难生认定信息
	 * @作者：maxh
	 * @日期：2013-4-22 下午04:49:36
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
	@SystemLog(description="访问学生资助-困难生认定-困难生查询-修改GUID:{guid},XH:{xh},XN:{xn},RDDC:{rddc}")
	public ActionForward updateKnsjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsjgForm model = (KnsjgForm) form;
		KnsdcService knsdcService = new KnsdcService();
		KnsjgService service = new KnsjgService();
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
	
        if (UPDATE.equalsIgnoreCase(model.getType())){
        	if (!isTokenValid(request)){
    			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
    			return null;
    		} else {
    			super.resetToken(request);
    		}
        	//唯一性判断（学号，学年，学期）
        	boolean isExist=service.isExistByKnsjg(model,UPDATE);
        	if(!isExist){
				//修改困难生认定信息
	        	boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		response.getWriter().print(getJsonMessage(null));
				return null;
        		
        	}
		}
        //困难生档次list
		request.setAttribute("knsdcList", knsdcService.getKnsdcList());
		//浙大个性化无偿资助金额列表
		request.setAttribute("wczzjeList", knsdcService.getWczzList());
		request.setAttribute("xxdm", Base.xxdm);
		//学年list
		request.setAttribute("xnList", Base.getXnndList());
		//学期list
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("mkxxForm", StringUtils.formatData(model));
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("jbxxList", jbxxList);
		if("10742".equals(Base.xxdm)) {
			KnsrdService knsrdService = new KnsrdService();
			request.setAttribute("sqlymcList", knsrdService.getSqlymcList());		
		}
		request.setAttribute("type", UPDATE);
		request.setAttribute("sqzq", SQZQ);
		KnsjgForm updateForm = service.getModel(model);
		//困难原因list
        if("10277".equals(Base.xxdm)) {
        	String knyydm=updateForm.getYlzd5();
			request.setAttribute("knyyList", new KnsrdService().getKnnyList(knyydm));	
		}
		
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		//杭州师范大学加载家庭困难类型和高档消费品类型 
		if("10346".equals(Base.xxdm)){
			KnsrdService knsrdService = new KnsrdService();
			request.setAttribute("jtknlxList", knsrdService.getJtknlxList());
			request.setAttribute("gdxfplxList", knsrdService.getGdxfpLxList());
		}
		this.saveToken(request);
		return mapping.findForward("updateKnsjg");
	}
	/**
	 * 
	 * @描述:删除困难生认定信息
	 * @作者：maxh
	 * @日期：2013-4-22 下午05:24:04
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
	@SystemLog(description="访问学生资助-困难生认定-困难生查询-删除VALUES:{values}")
	public ActionForward delKnsjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsjgService service = new KnsjgService();
		
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			
			if (result){
				KnsrdService knsrdService = new KnsrdService();
				knsrdService.delKnssqFromKnsjg(values.split(","));
			}
			
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
	 * @描述:保存困难生认定结果复制
	 * @作者：陶钢军 [1075]
	 * @日期：2014-6-27 下午05:42:17
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
	@SystemLog(description="访问学生资助-困难生认定-困难生查询-复制保存LYXN:{lyxn}")
	public ActionForward savecopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		KnsjgService service = new KnsjgService();
		String lyxn=request.getParameter("lyxn");
		String lyxq=request.getParameter("lyxq");
		String xn=request.getParameter("mbxn");
		String xq=request.getParameter("mbxq");
		
		String msg = service.copy(lyxn,lyxq,xn,xq);
		response.getWriter().print(getJsonMessage(msg));
		return null;
	}
	/**
	 * 
	 * @描述:困难生认定结果复制
	 * @作者：陶钢军 [1075]
	 * @日期：2014-6-27 下午05:43:16
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
	public ActionForward knsjgcopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjgService service = new KnsjgService();
		String xq=Base.currXq;
		if("xn".equals(SQZQ)){
			xq="on";
		}
		request.setAttribute("knsjgzqList", service.getKnsjgZqListNotIsHave(Base.currXn +","+ xq));
		request.setAttribute("xn",Base.currXn);
		request.setAttribute("xq",xq);
		request.setAttribute("xqmc",service.getXqmc(xq));
		this.saveToken(request);
		return mapping.findForward("knsjgcopy");
	}
	/**
	 * 
	 * @描述:困难生结果单个显示
	 * @作者：maxh
	 * @日期：2013-4-23 下午02:56:11
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
	public ActionForward knsjgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		request.setAttribute("xxdm", Base.xxdm);
		if (model == null){
			return updateKnsjg(mapping, form, request, response);
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		//查询单个困难生结果
		Map<String,String> map=service.getOneKnsjgList(model.getGuid());
		if("10277".equals(Base.xxdm)){//上海体育学院个性化
			String knyy=new KnsrdService().getKnyymc(map.get("ylzd5"));
			map.remove("yymc");
			map.put("yymc", knyy);
		}
		request.setAttribute("rs",map );
		
		//浙江大学获取取消困难生资格
		if(Globals.XXDM_ZJDX.equalsIgnoreCase(Base.xxdm)){				
		request.setAttribute("knsqxjlrs", service.getOneKnsqxjlList(model.getGuid()));			
		}
		
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("sqzq", SQZQ);
		return mapping.findForward("viewKnsjg");
		
	}
	
	/**
	 * 
	 * @描述:打印困难生申请信息
	 * @作者：honglin
	 * @日期：2013-5-3 
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
	public ActionForward printJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			KnsjgForm myForm = (KnsjgForm) form;
			String guids[]=myForm.getGuid().split(",");
			//String xhs[]=myForm.getXh().split(",");
			if(null!=guids&&guids.length==1){//一条数据
				File file=print(myForm,guids[0],request);
				FileUtil.outputWord(response, file);
			}else{//多条数据
				List<File> files = new ArrayList<File>();
				for(String guid:guids){
					myForm.setGuid(guid);
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
	 * @描述:获取对应打印File数据
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-20 下午05:42:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param guid
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File print(KnsjgForm myForm,String guid,HttpServletRequest request) throws Exception{
		myForm.setGuid(guid);
		KnsjgService service = new KnsjgService();
		XsxxService xsxxService = new XsxxService();
		KnsdcService knsdcService = new KnsdcService();

		KnsjgForm kf=service.getModel(myForm);
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(kf.getXh());
		//重用困难申请中的打印
		KnsrdService kservice = new KnsrdService();
		KnsrdbjpyService sjservice = new KnsrdbjpyService();
		KnsrdForm printForm=new KnsrdForm();
		KnsrdbjpyForm printForm1 = new KnsrdbjpyForm();
		//设置申请理由
		printForm.setSqly(kf.getSqly());

		printForm.setGuid(guid);
		printForm.setJtrjnsr(kf.getJtrjnsr());
		printForm1.setSqly(kf.getSqly());//设置申请理由
		printForm1.setGuid(guid);
		File file = null;
		if("10718".equals(Base.xxdm)) {
			file=sjservice.printjgForWord(xsjbxx,null,printForm1,knsdcService.getKnsdcList(), kf.getRddc(),request);
		}else {
			file=kservice.printjgForWord(xsjbxx,null,printForm,knsdcService.getKnsdcList(), kf.getRddc(),request);
		}	
		return file;
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
		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();

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
	 * @描述:困难生统计导出（贵州大学）
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-20 下午03:06:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward knsrdTjExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		KnsjgForm exporModel = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(exporModel,user);//查询出所有记录，不分页
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.knsrdTjExport(resultList, response.getOutputStream(), user);
		return null;
	}
	
	/**
	 * 
	 * @描述:困难生统计导出（浙大）
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-5-7 下午03:06:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward knsExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		KnsjgForm exporModel = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(exporModel,user);//查询出所有记录，不分页
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.knsExport(resultList, response.getOutputStream(), user);
		return null;
	}
	/**
	 * @description	：江西陶瓷困难生汇总表
	 * @author 		： CP（1352）
	 * @date 		：2017-12-1 下午02:21:26
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward rdhzbExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		KnsjgForm exporModel = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(exporModel,user);//查询出所有记录，不分页
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("困难学生统计汇总.xls","utf-8")); 
		if ("12309".equals(Base.xxdm)) {
			service.rdhzbExport_12309(resultList, response.getOutputStream(), user);
		}
		else if("10098".equals(Base.xxdm)) {
			service.rdhzbExport_10098(resultList, response.getOutputStream(), user);
		}else {
			service.rdhzbExport(resultList, response.getOutputStream(), user);
		}
		return null;
	}
	/**
	 * @描述: 浙江大学个性化！困难生结果的困难与特困生比例按钮
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-11-21 上午08:56:31
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
	public ActionForward knstksPercent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();
		if (QUERY.equalsIgnoreCase(model.getType())){
			List<HashMap<String,String>> resultList = service.knstksPercent(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		return mapping.findForward("knstksPercent");
	}
	
	/**
	 * @描述：导出 档案数据库 认定排序表 辽宁职业个性化
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月30日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward ExportKnsjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		KnsjgForm exporModel = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();
		String expType=request.getParameter("expType");
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		List<File> files = new ArrayList<File>();
		List<HashMap<String,String>>bjlist=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>knsjglist=new ArrayList<HashMap<String,String>>();
		if("rdpxb".equals(expType)){
			bjlist=service.getKnsjgBj(exporModel,user);
			for(HashMap<String,String> map:bjlist){
				knsjglist=service.getKnsjgListBybj(exporModel,user,map.get("bjdm"));
				if(null==knsjglist||0==knsjglist.size()){
					continue;
				}
				File file=service.printFile(knsjglist, map.get("bjmc")+"["+map.get("bjdm")+"]", "knsrdpxb_12898.xml");
				files.add(file);
			}
			if(0==files.size()){
				knsjglist.add(new HashMap<String,String>());
				files.add(service.printFile(knsjglist, "该查询条件下无困难学生", "knsrdpxb_12898.xml"));
			}
		}else{
			bjlist=service.getKnsjgXy(exporModel,user);
			for(HashMap<String,String> map:bjlist){
				knsjglist=service.getKnsjgListByxy(exporModel,user,map.get("xydm"));
				if(null==knsjglist||0==knsjglist.size()){
					continue;
				}
				File file=service.printFile(knsjglist, map.get("xymc")+"["+map.get("xydm")+"]", "knsdasjk_12898.xml");
				files.add(file);
			}
			if(0==files.size()){
				knsjglist.add(new HashMap<String,String>());
				files.add(service.printFile(knsjglist, "该查询条件下无困难学生", "knsdasjk_12898.xml"));
			}
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
		FileUtil.outputZip(response, zipFile);
		return null;
	}
	
	public ActionForward export_12688(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();
		// ============= 初始化各变量的值 ============
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("knstj.xls".getBytes(), "GBK") + "\"");
		service.gwcjffDc(response.getOutputStream(),model);
		
		return null;
	}
	
	/**
	 * 
	 * @描述: 徐州工程个性化导出
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-26 上午11:27:02
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
	public ActionForward exportConfigXzyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.getXzyyExportData(model,user);//查询出所有记录，不分页
		
		
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
	 * @描述: 浙江中医药个性化导出
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-26 上午11:27:02
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
	public ActionForward exportConfigZjzyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.getZjzyyExportData(model,user);//查询出所有记录，不分页
		
		
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
	 * @描述: 杭师大个性化
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-16 下午01:12:44
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
	public ActionForward printHzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();
		Map<String,Object> data = new HashMap<String,Object>();
		File file = null;
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		int zs = 10;
		int dataLine = 0;
		//查询
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
		if(resultList != null && resultList.size() >= 1){
			dataLine = resultList.size();
		}
		if(zs - dataLine >= 1){
			for (int i = 0; i < zs - dataLine; i++) {
				HashMap<String,String> tmpMap = new HashMap<String, String>();
				tmpMap.put("xh", "");
				tmpMap.put("xm", "");
				tmpMap.put("sjhm", "");
				resultList.add(tmpMap);
			}
		}
		data.put("rs", resultList);
		data.put("year", GetTime.getTimeByFormat("yyyy"));
		data.put("mon", GetTime.getTimeByFormat("mm"));
		data.put("day", GetTime.getTimeByFormat("dd"));
		String xymc = service.getXymc(request.getParameter("xydm"));
		data.put("xymc",xymc);
		ResourceUtils.getFile(Constants.TEP_DIR+"xszz/hzbhsd_10346.xml");
		file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"xszz","hzbhsd_10346.xml",FreeMarkerUtil.getFileName(xymc+"_信息汇总表"));
		FileUtil.outputWord(response, file);
		return null;
	}
	
	/**
	 * @description	： 困难生评定导出
	 * @author 		： lj（1282）
	 * @date 		：2018-4-24 下午04:53:50
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knspddc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		User user = getUser(request);
		
		List<HashMap<String, String>> resultList = null;
		
		// 查询
		String dcclbh = request.getParameter("dcclbh");
		//高校本专科困难学生认定管理
		if(dcclbh.equalsIgnoreCase("xg_knsrd_knsrddc.do")){//困难生认定导出			
			resultList = service.knsrddc(model,user);// 查询出所有记录，不分页
		}else if(dcclbh.equalsIgnoreCase("kns_jtjjkn_jtxx_ahjzdx.do")){//困难生家庭情况
			resultList = service.jtxxdc(model, user);
		}else if(dcclbh.equalsIgnoreCase("kns_knsjg_knsjdlkdc.do")){//困难生建档立卡导出
			resultList = service.knsjdlkdc(model, user);
		}
		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(dcclbh);// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}
