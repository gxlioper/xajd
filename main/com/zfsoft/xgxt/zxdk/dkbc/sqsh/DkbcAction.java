/**
 * @部门:学工产品事业部
 * @日期：2014-11-12 上午09:36:20 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.sqsh;

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
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.check.CheckCondition;
import com.zfsoft.xgxt.base.check.impl.CheckStudentCondition;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.zxdk.dkbc.cssz.DkbcCsszService;
import com.zfsoft.xgxt.zxdk.dkbc.tjsz.TjszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 贷款补偿
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-11-12 上午09:36:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DkbcAction extends SuperAuditAction<DkbcModel, DkbcService> {

	protected DkbcAction (String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}

	public DkbcAction(){
		this("zxdk_dkbc", "zxdk_jcjy_bcsq.do", "zxdk_jcjy_bcsh.do");
	}
	
	
	/**贷款补偿页面**/
	@SystemAuth(url = "zxdk_jcjy_bcsq.do")
	public ActionForward dkbcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DkbcCsszService csszService = new DkbcCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("path", "zxdk_jcjy_bcsq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dkbcList");
	}
	
	
	/**贷款补偿查询**/
	@SystemAuth(url = "zxdk_jcjy_bcsq.do")
	public ActionForward getDkbcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DkbcModel model = (DkbcModel) form;
		DkbcService service = new DkbcService();
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	/**贷款补偿申请**/
	@SystemAuth(url = "zxdk_jcjy_bcsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward bcsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DkbcModel model = (DkbcModel) form;
		User user = getUser(request);
		DkbcService service = new DkbcService();
		String xh = "stu".equals(user.getUserType()) ? user.getUserName() : model.getXh();
		
		if (!StringUtil.isNull(xh)){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			model.setXh(xh);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("jcjybcdc");
		request.setAttribute("jbxxList", jbxxList);
		
		DkbcCsszService csszService = new DkbcCsszService();
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("cssz", csszService.getModel());
		request.setAttribute("yhlist",service.getYhList());
		request.setAttribute("yjxflist", service.getXfList());
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("path", "dkbc_sqsh.do?method=bcsq");
		request.setAttribute("dclblist", service.getDclbList());
		this.saveToken(request);
		return mapping.findForward("bcsq");
	}
	
	/**
	 * 
	 * @描述: 贷款条件设置判断
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-8 下午04:17:56
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
	public ActionForward getXmsqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xmdm = "JCJYDKBC";
		DkbcModel myForm = (DkbcModel) form;
		String xh = myForm.getXh();
		if(!StringUtil.isNull(xh)) {
			TjszService tjszService = new TjszService();
			List<HashMap<String, String>> conditions = tjszService.getTjszGl(xmdm,xh);	
			//校验条件，返回校验结果
			CheckCondition check = new CheckStudentCondition();
			List<HashMap<String, String>> results = check.checkCondition(xh, conditions);
			JSONArray dataList = JSONArray.fromObject(results);
			response.getWriter().print(dataList);
		}
		
		return null;
	}
	
	/**贷款补偿修改**/
	@SystemAuth(url = "zxdk_jcjy_bcsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DkbcModel djjdForm = (DkbcModel) form;
		DkbcService service = new DkbcService();
		DkbcModel model = service.getModel(djjdForm);
		
		if (model != null){
			BeanUtils.copyProperties(djjdForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("jcjybcdc");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("yhlist",service.getYhList());
		request.setAttribute("yjxflist", service.getXfList());
		request.setAttribute("dclblist", service.getDclbList());
		this.saveToken(request);
		return mapping.findForward("xgsq");
	}
	
	
	/**取消申请**/
	@SystemAuth(url = "zxdk_jcjy_bcsq.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问助学贷款-基层就业-补偿申请-删除values:{id}")
	public ActionForward qxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DkbcModel djjdForm = (DkbcModel) form;
		DkbcService service = new DkbcService();
		
		boolean result = service.runDelete(new String[]{djjdForm.getId()}) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述: 贷款审核列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-25 下午03:37:30
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
	@SystemAuth(url = "zxdk_jcjy_bcsh.do")
	public ActionForward bcshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "zxdk_jcjy_bcsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bcshList");
	}
	
	
	/**
	 * 
	 * @描述: ajax加载贷款审核列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-25 下午03:39:06
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
	@SystemAuth(url = "zxdk_jcjy_bcsh.do")
	public ActionForward getBcshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DkbcModel model = (DkbcModel) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//查询
		List<HashMap<String,String>> resultList = getService().getAudingList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**贷款补偿审核**/
	@SystemAuth(url = "zxdk_jcjy_bcsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward bcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DkbcModel djjdForm = (DkbcModel) form;
		DkbcService service = new DkbcService();
		DkbcModel model = service.getModel(djjdForm.getId());
		String shid = request.getParameter("shid");
		if (model != null){
			BeanUtils.copyProperties(djjdForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("jcjybcdc");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("dclblist", service.getDclbList());
		request.setAttribute("shid", shid);
		return mapping.findForward("bcsh");
	}
	
	
	/**查看申请*/
	public ActionForward cksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DkbcModel djjdForm = (DkbcModel) form;
		DkbcService service = new DkbcService();
		
		DkbcModel model = service.getModel(djjdForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(djjdForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("jcjybcdc");
		request.setAttribute("dclblist", service.getDclbList());
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("cksq");
	}
	
	/**打印申请表**/
	public ActionForward printSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String[] ids = request.getParameter("ids").split(",");
			
			if(null!=ids && ids.length == 1){//一条数据
				File file=print(ids[0],request);
				FileUtil.outputWord(response, file);
			}else{//多条数据
				List<File> files = new ArrayList<File>();
				for(String id : ids){
					File file=print(id,request);
					files.add(file);
				}
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			}
			return null;
	}
	
	private synchronized File print(String id,HttpServletRequest request) throws Exception{
		String mbmc = "zxdk_dcsqb.xml";
		Map<String, Object> data = new HashMap<String, Object>();
		
		DkbcService service = new DkbcService();
		DkbcModel model = service.getModel(id);
		
		data.put("m_byny", DateTranCnDate.fomartDateToCn(model.getBysj(),FomartDateType.month));// 毕业日期
		data.put("m", model);
		data.put("date", DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.day));// 毕业日期
		
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		data.put("j", xsjbxx);
		String xh = xsjbxx.get("xh");
		
		if("10264".equals(Base.xxdm)){ //上海水产大学
			mbmc = "zxdk_dcsqb_10264.xml";
			// 家庭情况
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkdcForm = new JtqkdcForm();
			jtqkdcForm.setXh(xh);
			JtqkdcForm jtqkmodel = jtqkService.getModel(jtqkdcForm); 
			if (jtqkmodel == null) {
				jtqkmodel = new JtqkdcForm();
			}
			data.put("jtqkmodel", jtqkmodel);
//			// 获奖情况
//			PjjgService pjjgService = new PjjgService();
//			List<HashMap<String, String>> pjjgList =  pjjgService.getPjpyInfoMap(xh);
//			pjjgService.addBlankList(pjjgList, 2 - pjjgList.size());
//			data.put("pjjgList", pjjgList);
			// 加载学生困难认定情况
			KnsjgService knsjgService = new KnsjgService();
			HashMap<String, String> knsjg = knsjgService.getXsknsjg(xh, "", "");
			String rddcmc = knsjg.get("dcmc")==null?"":knsjg.get("dcmc");
			data.put("rddcmcFlag", String.valueOf(rddcmc.matches(".*特.*困.*")));// 是否特困
		}
		
		return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", mbmc, FreeMarkerUtil.getFileName(xsjbxx.get("xh") +"["+xsjbxx.get("xm")+"]"));
	}
	
	
	/**按学号学年查询是否有申请**/
	public ActionForward getCountByXhAndXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DkbcModel djjdForm = (DkbcModel) form;
		DkbcService service = new DkbcService();
		
		String count = service.getCountByXhAndXn(djjdForm);
		response.getWriter().print(count);
		return null;
	}
	
	//贷款类型改变
	public ActionForward dklxChange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xh = request.getParameter("xh");
		String dklx = request.getParameter("dklx");
		String xn = request.getParameter("xn");
		DkbcService service = new DkbcService();
		HashMap<String, String> datamap = service.produceDataMap(xh, dklx, xn);
		
		JSONObject json = JSONObject.fromObject(datamap);
		response.getWriter().print(json);
		return null;
		
	}
	
	/**
	 * 
	 * @描述: 表单新增保存操作重写
	 * @作者： yxy[工号:1206]
	 * @日期：2014年6月9日 上午9:51:00
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
	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		DkbcModel model = (DkbcModel) form;
		DkbcService service = getService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述: 表单提交
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-3 下午02:26:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnid
	 * @param id
	 * @param squrl
	 * @param shurl
	 * @return
	 * @throws Exception
	 * JSONObject 返回类型 
	 * @throws
	 */
	@Override
	public ActionForward saveAndSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		DkbcModel model = (DkbcModel) form;
		DkbcService service = getService();
		boolean isSuccess = false;
		//设置申请记录对应的审核状态“审核中”
		model.setShzt(Constants.YW_SHZ);
		
		//保证申请ID和审核ID的一致性，由系统生成唯一ID
		if (StringUtil.isNull(model.getId())){
			String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			model.setId(uuid);
			
			//保存申请记录
			isSuccess = service.runInsert(model);
		} else {
			isSuccess = service.runUpdate(model);
		}
		
		JSONObject message = null;
		
		if (isSuccess){
			//提交申请到审核流程
			message = submit("zxdk_dkbc" , model.getId(), "zxdk_jcjy_bcsq.do", "zxdk_jcjy_bcsh.do");
		} else {
			message = getJsonMessageByKey(MessageKey.SYS_SUBMIT_FAIL);
		}
		
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述: 表单提交
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-3 下午02:26:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnid
	 * @param id
	 * @param squrl
	 * @param shurl
	 * @return
	 * @throws Exception
	 * JSONObject 返回类型 
	 * @throws
	 */
	private JSONObject submit(String gnid,String id,String squrl,String shurl)
	throws Exception {

		ShlcInterface shlc = new CommShlcImpl();
		
		DkbcService service = getService();
		//查询申请记录
		DkbcModel model = service.getModel(id);
		String splcid = model.getSplcid();
		//提交申请流程
		boolean isSuccess = shlc.runSubmit(id, splcid, model.getXh(), shurl, squrl);
		
		if(isSuccess){
			//更新申请记录状态
			model.setShzt(Constants.YW_SHZ);
			//model.setSplcid(splcid);
			isSuccess = service.runUpdate(model);
		}
		
		String message = isSuccess ? 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_SUCCESS) : 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_FAIL);
		
		return getJsonMessage(message);
	}
}
