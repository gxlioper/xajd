/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xgxt.gygl.sspy.pysq;

import java.io.File;
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
import xgxt.gygl.sspy.cssz.SspycsszForm;
import xgxt.gygl.sspy.cssz.SspycsszService;
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
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.gygl.ssyd.ydsq.YdsqService;

/**
 * @className	： SspysqAction
 * @description	： 宿舍评优申请Action层
 * @author 		：CP（1352）
 * @date		： 2018-4-27 下午03:11:51
 * @version 	V1.0
 */
public class SspysqAction extends SuperAction{
	private final static String url = "sspy_sq.do"; 
	SspysqService service = new SspysqService();
	
	
	/**
	 * @description	： 查询列表
	 * @author 		： CP（1352）
	 * @date 		：2018-4-27 下午04:17:36
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward pySqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SspysqForm model = (SspysqForm) form;
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
		//基础设置信息
		SspycsszService csszService = new SspycsszService();
		SspycsszForm jcszModel = csszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
	
		return mapping.findForward("pySqList");
	}
	
	/**
	 * @description	： 增加
	 * @author 		： CP（1352）
	 * @date 		：2018-4-27 下午05:51:16
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
			SspysqForm model = (SspysqForm) form;
			User user = getUser(request);
			if ("stu".equals(user.getUserType())){
				model.setXh(user.getUserName());
			}
			//床位信息--取自宿舍异动模块
			YdsqService ydsqService = new YdsqService();
			HashMap<String,String> cwxxMap = ydsqService.getCwxxForXh(model.getXh());
			request.setAttribute("cwxxData",cwxxMap);
			
			List<HashMap<String,String>> pyxmList = service.getPyxmList();
			request.setAttribute("pyxmList", pyxmList);
			request.setAttribute("lddm",cwxxMap.get("lddm"));
			request.setAttribute("qsh",cwxxMap.get("qsh"));
		if (SAVE.equalsIgnoreCase(model.getType())||SUBMIT.equalsIgnoreCase(model.getType())) {
			if (StringUtils.isNull(cwxxMap.get("qsh"))) {
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_GYGL_WRZ));
			}else {
				model.setXn(Base.currXn);
				model.setXq(Base.currXq);
				model.setLddm(cwxxMap.get("lddm"));
				model.setQsh(cwxxMap.get("qsh"));
				boolean isExist = service.isExist(model);
				if(!isExist){
					super.resetToken(request);
					String sqid = UniqID.getInstance().getUniqIDHash();
					model.setSqid(sqid);
					boolean result = service.saveSspy(model);
					String messageKey = "";
					if(SAVE.equalsIgnoreCase(model.getType())){
						messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
					}else{
						messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
					}
					response.getWriter().print(getJsonMessageByKey(messageKey));
				}else{
					response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_GYGL_SSPYCF));
				}
			}
			
			return null;
		}
		//学年 学期
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		String path = "sspysq.do?method=add";
		request.setAttribute("path", path);
		return mapping.findForward("zjpysq");
	}

	
	
	/**
	 * @description	： 获得宿舍人员床位信息
	 * @author 		： CP（1352）
	 * @date 		：2018-4-28 上午09:59:45
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ldqsxx = request.getParameter("ldqsxx");//lddm+qsh
		Map<String, String> rs = service.getQsForPk(ldqsxx);// 寝室详细信息
		List<HashMap<String, String>> list = service.getCwForQs(ldqsxx);//获取寝室学生信息
		request.setAttribute("rs", rs);
		request.setAttribute("list", list);
		JSONArray json = JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * @description	： 修改
	 * @author 		：CP（1352）
	 * @date 		：2018-4-28 上午10:43:09
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
			SspysqForm model = (SspysqForm) form;
			User user = getUser(request);
			if ("stu".equals(user.getUserType())){
				model.setXh(user.getUserName());
			}
			//床位信息--取自宿舍异动模块
			YdsqService ydsqService = new YdsqService();
			HashMap<String,String> cwxxMap = ydsqService.getCwxxForXh(model.getXh());
			request.setAttribute("cwxxData",cwxxMap);
			
			List<HashMap<String,String>> pyxmList = service.getPyxmList();
			request.setAttribute("pyxmList", pyxmList);
			request.setAttribute("lddm",cwxxMap.get("lddm"));
			request.setAttribute("qsh",cwxxMap.get("qsh"));
		if (UPDATE.equalsIgnoreCase(model.getType())||SUBMIT.equalsIgnoreCase(model.getType())) {
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			model.setLddm(cwxxMap.get("lddm"));
			model.setQsh(cwxxMap.get("qsh"));
			boolean isExist = service.isExist(model);
        	if(!isExist){
        		boolean result = service.updatSspy(model);
        		String messageKey = "";
        		if(UPDATE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_GYGL_SSPYCF));
        	}
			return null;
		}
		//学年 学期
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		SspysqForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("sqid", updateForm.getSqid());
		String path = "sspysq.do?method=update";
		request.setAttribute("path", path);
		return mapping.findForward("xgpysq");
	}
	/**
	 * @description	： 查看
	 * @author 		： CP（1352）
	 * @date 		：2018-5-3 下午04:52:35
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward view (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		SspysqForm model = (SspysqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//床位信息--取自宿舍异动模块
		YdsqService ydsqService = new YdsqService();
		HashMap<String,String> cwxxMap = ydsqService.getCwxxForXh(model.getXh());
		request.setAttribute("cwxxData",cwxxMap);
		
		List<HashMap<String,String>> pyxmList = service.getPyxmList();
		request.setAttribute("pyxmList", pyxmList);
		request.setAttribute("lddm",cwxxMap.get("lddm"));
		request.setAttribute("qsh",cwxxMap.get("qsh"));
		//学年 学期
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		HashMap<String,String> rs = service.getInfoBySqid(model.getSqid());
		request.setAttribute("rs", rs);
		request.setAttribute("filepath", rs.get("filepath"));
		return mapping.findForward("ckpysq");
	}
	/**
	 * @description	： 删除
	 * @author 		： CP（1352）
	 * @date 		：2018-4-28 上午10:42:58
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
			String[] mess = service.deleteSspy(values.split(","));
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
	 * @date 		：2018-4-28 上午11:37:59
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
		SspysqForm model = (SspysqForm) form;
		String sqid = request.getParameter("values");
		String xh = request.getParameter("xh");
		model.setSqid(sqid);
		model.setXh(xh);
		//判断提交时间段是否开放
		boolean result = service.submitSspy(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	： 撤销
	 * @author 		： CP（1352）
	 * @date 		：2018-4-28 上午11:37:45
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
		boolean result = service.cancelSspy(sqid,lcid);
		if(result){
			//更新业务状态为'未提交'
			SspysqForm model = new SspysqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.updateSspysq(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	： 导出
	 * @author 		： CP（1352）
	 * @date 		：2018-5-3 下午05:19:16
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SspysqForm model = (SspysqForm) form;
		
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
}
