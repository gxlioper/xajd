/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.dsgl.dsglsq;

import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.comm.attachupload.model.FileInfo;
import com.zfsoft.xgxt.dekt.dsgl.onehundredbook.OneHundredBookStuService;
import com.zfsoft.xgxt.dekt.dsgl.onehundredbook.SmObject;
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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class DsglsqAction extends SuperAction{
	private static final String DEKT = "dekt";//rcswbxgl
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	private static final String url = "xg_dekt_dsglsq.do";
	private DsglsqService service = new DsglsqService();
	
	
	/**
	 * @description	： 查询
	 * @author 		： CP（1352）
	 * @date 		：2018-1-17 上午10:04:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward dsglSqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DsglsqForm model = (DsglsqForm) form;

		if (QUERY.equalsIgnoreCase(model.getType())){
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

		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);

		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);

		return mapping.findForward("dssqcx");

	}

	@SystemAuth(url = url)
	public ActionForward onehundredBookList(ActionMapping mapping, ActionForm form,
											HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        DsglsqForm t = (DsglsqForm)form;
		User user = getUser(request);
		if(QUERY.equals(t.getType())){

			OneHundredBookStuService service = new OneHundredBookStuService();
			List<SmObject> list = service.getPageList(t,user);

			JSONArray dataList = JSONArray.fromObject(list);
			response.getWriter().print(dataList);
			return null;
		}

		return mapping.findForward("100booklist");
	}

	/*
	*收藏与取消
	 */
	public ActionForward sc(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        OneHundredBookStuService service = new OneHundredBookStuService();

        String ssm = request.getParameter("ssm");
        if(ssm != null){
            ssm = URLDecoder.decode(ssm, "UTF-8");
        }

        String operation = request.getParameter("operation");
        User user = getUser(request);
        Boolean flag = service.sc(ssm,operation,user.getUserName());

		Map<String,String> map = new HashMap<String, String>();
        if(flag){
            map.put("flag","success");
		} else {
            map.put("flag","failed");
		}
        map.put("operation",operation);
        JSONObject result = JSONObject.fromObject(map);
        response.getWriter().print(result);
        return null;
    }

	/**
	 * @description	： 增加
	 * @author 		： CP（1352）
	 * @date 		：2018-1-17 上午10:47:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			DsglsqForm model = (DsglsqForm) form;
			if (!StringUtil.isNull(model.getSsm())) {
				model.setSsm(URLDecoder.decode(model.getSsm(),"UTF-8"));
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
		if (SAVE.equalsIgnoreCase(model.getType())||SUBMIT.equalsIgnoreCase(model.getType())) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setSqsj(df.format(new Date()));
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			boolean isExist = service.isExist(model);
        	if(!isExist){
        		String sqid = UniqID.getInstance().getUniqIDHash();
				model.setSqid(sqid);
        		boolean result = service.saveDssq(model);
        		String messageKey = "";
        		if(SAVE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
        	}
			return null;
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(DEKT);
		request.setAttribute("jbxxList", jbxxList);
		//学年 学期
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		request.setAttribute("ssm", model.getSsm());
		String path = "dekt_dsglsq.do?method=add";
		request.setAttribute("path", path);
		return mapping.findForward("dssqzj");
	}

	
	/**
	 * @description	： 修改
	 * @author 		：CP（1352）
	 * @date 		：2018-1-17 下午04:14:47
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			DsglsqForm model = (DsglsqForm) form;
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
		if (UPDATE.equalsIgnoreCase(model.getType())||SUBMIT.equalsIgnoreCase(model.getType())) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setSqsj(df.format(new Date()));
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			boolean isExist = service.isExist(model);
        	if(!isExist){
        		boolean result = service.updateDssq(model);
        		String messageKey = "";
        		if(UPDATE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
        	}
			return null;
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(DEKT);
		request.setAttribute("jbxxList", jbxxList);
		//学年 学期
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		DsglsqForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("xxdm", Base.xxdm);
		String path = "dekt_dsglsq.do?method=update";
		request.setAttribute("path", path);
		return mapping.findForward("dssqxg");
	}
	
	
	/**
	 * @description	： 删除
	 * @author 		： CP（1352）
	 * @date 		：2018-1-17 下午04:14:32
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteDssq(values.split(","));
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
	 * @description	： 提交
	 * @author 		： CP（1352）
	 * @date 		：2018-1-17 下午04:14:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DsglsqForm model = (DsglsqForm) form;
		String sqid = request.getParameter("values");
		String xh = request.getParameter("xh");
		model.setSqid(sqid);
		model.setXh(xh);
		//判断提交时间段是否开放
		boolean result = service.submitDssq(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	： 撤销
	 * @author 		： CP（1352）
	 * @date 		：2018-1-17 下午04:14:06
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		//只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelDssq(sqid,lcid);
		if(result){
			//更新业务状态为'未提交'
			DsglsqForm model = new DsglsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.updateCxDssq(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * @description	： 查看
	 * @author 		： CP（1352）
	 * @date 		：2018-1-17 下午04:13:53
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DsglsqForm model = (DsglsqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//查询单个行为信息结果
		HashMap<String,String> gfqkMap = service.getDssqInfo(model);
		request.setAttribute("rs", StringUtils.formatData(gfqkMap));
		request.setAttribute("filepath", gfqkMap.get("filepath"));
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(DEKT);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("dssqck");
	}
	
	/**
	 * 
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date 		：2018-1-17 下午05:40:48
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DsglsqForm model = (DsglsqForm) form;
		
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
	 * @描述:4个100学生完成图
	 * @作者：tgj[工号：1075]
	 * @日期：2018-2-11 上午11:01:45
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
	public ActionForward viewDbt(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		request.setAttribute("xh",user.getUserDepName());
		return mapping.findForward("viewDbt");
	}

	/**
	 * @description	： 取学生4个100信息完成图
	 * @author 		： CP（1352）
	 * @date 		：2018-4-12 下午05:19:51
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getviewWct(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		User user = getUser(request);
		String xh = user.getUserName();
		JSONObject dataMapJson = JSONObject.fromObject(service.getViewWct(xh));
		response.getWriter().print(dataMapJson);
		return null;
	}
}