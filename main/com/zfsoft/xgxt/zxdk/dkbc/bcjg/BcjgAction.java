/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:49:48 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.bcjg;

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
import xgxt.utils.date.DateUtils;

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
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.zxdk.dkbc.sqsh.DkbcService;

/** 
 * @类功能描述:贷款补偿
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-10-29 上午09:49:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BcjgAction extends SuperAction<BcjgModel, BcjgService> {
	
	private static final String url = "zxdk_jcjy_bcjg.do";

	/**补偿结果列表*/
	@SystemAuth(url = url)
	public ActionForward bcjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "zxdk_jcjy_bcjg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bcjgList");
	}
	
	/**补偿结果列表*/
	@SystemAuth(url = url)
	public ActionForward getBcjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BcjgService service = getService();
		BcjgModel model = (BcjgModel) form;
		
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**补偿结果增加*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward bcjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BcjgModel model = (BcjgModel) form;
		
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
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("jcjybcdc");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("jbxxList", jbxxList);
		DkbcService dkbcservice = new DkbcService();
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("yhlist",dkbcservice.getYhList());
		request.setAttribute("dclblist", dkbcservice.getDclbList());
		request.setAttribute("yjxflist", dkbcservice.getXfList());
		request.setAttribute("path", "dkbc_bcjg.do?method=bcjgAdd");
		this.saveToken(request);
		return mapping.findForward("bcjgAdd");
	}
	
	/**补偿结果修改*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward bcjgEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BcjgService service = getService();
		BcjgModel myForm = (BcjgModel) form;
		
		BcjgModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		
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
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("jcjybcdc");
		request.setAttribute("jbxxList", jbxxList);
		DkbcService dkbcservice = new DkbcService();
		request.setAttribute("yhlist",dkbcservice.getYhList());
		request.setAttribute("dclblist", dkbcservice.getDclbList());
		request.setAttribute("yjxflist", dkbcservice.getXfList());
		return mapping.findForward("bcjgEdit");
	}
	
	@SystemAuth(url = url)
	public ActionForward bcjgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BcjgService service = getService();
		BcjgModel myForm = (BcjgModel) form;
		
		BcjgModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String,String>> bfxxList = service.getBfxxList(model.getXh());
			request.setAttribute("bfxxList", bfxxList);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("jcjybcdc");
		request.setAttribute("jbxxList", jbxxList);
		DkbcService dkbcservice = new DkbcService();
		request.setAttribute("dclblist", dkbcservice.getDclbList());
		return mapping.findForward("bcjgView");
	}
	
	/**补偿结果删除*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问助学贷款-基层就业-补偿结果-删除values:{ids}")
	public ActionForward bcjgDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		BcjgService service = getService();
		
		if (!StringUtil.isNull(ids)){
			int num = service.runDelete(ids.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BcjgService service = getService();
		BcjgModel model = (BcjgModel) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// 查询出所有记录，不分页

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
	
	
	/**打印申请表**/
	@SystemAuth(url = url)
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
		
		BcjgService service = getService();
		BcjgModel model = service.getModel(id);
		data.put("m_byny", DateTranCnDate.fomartDateToCn(model.getBysj(),FomartDateType.month));// 毕业日期
		data.put("m", model);
		
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		data.put("j", xsjbxx);
		String xh = xsjbxx.get("xh");
		data.put("date", DateUtils.getLyr());
		
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
	
	
	public ActionForward getCountByXhAndXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			BcjgModel model = (BcjgModel) form;
			BcjgService service = getService();
			String count = service.getCountByXhAndXn(model);
			response.getWriter().print(count);
			return null;
	}
	
	/**
	 * 
	 * @描述: 新增表单保存方法重写
	 * @作者： yxy[工号:1206]
	 * @日期：2014年6月9日 上午9:51:00
	 * @修改记录: 修改者名字-修改日期-修改内容
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
		BcjgModel model = (BcjgModel) form;
		BcjgService service = getService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
}
