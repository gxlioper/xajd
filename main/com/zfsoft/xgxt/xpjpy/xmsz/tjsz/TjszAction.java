/**
 * @部门:学工产品事业部
 * @日期：2013-7-31 下午05:01:23 
 */  
package com.zfsoft.xgxt.xpjpy.xmsz.tjsz;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhService;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 条件设置 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-31 下午05:01:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TjszAction extends SuperAction {
	private TjszService service = new TjszService();
	private String messageKey;

	private static final String urlJxj = "xpj_pjdm.do?method=xmlxList&xmxz=1&sindex=0";
	private static final String urlBz = "xpj_pjdm.do?method=xmlxList&xmxz=2&sindex=1";
	
	/**
	 * 
	 * @描述:基本查询方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录: void 返回类型
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz})
	public ActionForward xmwhTjszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		request.setAttribute("xmdm", xmdm);
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);
		
		
		//是否有学生申请项目
		SqshService sqshService = new SqshService();
		boolean flag = sqshService.isExistsFlowData(xmdm);
		
		String flagpath = request.getParameter("flagpath");
		//是否集体评奖判断
		if(StringUtils.isNotNull(flagpath) && "jtpj".equalsIgnoreCase(flagpath)){
			request.setAttribute("flagpath", request.getParameter("flagpath"));
			xmmc = xmwhService.getNameByIdJtpj(xmdm);
			flag = sqshService.isExistsFlowDatajtpj(xmdm);
		}else{
			request.setAttribute("flagpath","");
		}
		request.setAttribute("xmmc", xmmc);
		request.setAttribute("spzt", flag);
		String xmxz = request.getParameter("xmxz");
		request.setAttribute("xmxz",xmxz);
		String path= null;
		if("2".equals(xmxz))
		{
			path= urlBz;
		}else{
			path= urlJxj;
		}

		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		// 年级学院专业班级
		return mapping.findForward("xmwhTjszCx");
	}

	/**
	 * 
	 * @描述:查询所有记录，返回json格式
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	public ActionForward xmwhTjszSy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		String flagpath = request.getParameter("flagpath");
		TjszViewForm viewForm = new  TjszViewForm();
		if(StringUtils.isNull(flagpath)){
			 viewForm = service.getAll(xmdm);
		}else{
			 viewForm = service.getJtpj(xmdm, "002");
		}
		
		JSONObject json = JSONObject.fromBean(viewForm);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @描述:查询学年学期记录，返回json格式
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	public ActionForward xmwhTjszXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TjszModel myForm = (TjszModel) form;
		if (QUERY.equals(myForm.getType())) {
			String xmdm = request.getParameter("xmdm");
			TjszViewForm viewForm = service.getXn(xmdm);
			JSONObject json = JSONObject.fromBean(viewForm);
			response.getWriter().print(json);
			return null;
		}
		String xnVal = request.getParameter("xnVal");
		request.setAttribute("xnVal", xnVal);
		String zqlx = request.getParameter("zqlx");
		request.setAttribute("zqlx", zqlx);
		CsszDao csszDao = new CsszDao();
		request.setAttribute("pjzq", csszDao.getCsz("pjzq"));
		request.setAttribute("knszq", MessageUtil.getText("xszz.knsrd.sqzq"));
		String tjdm = request.getParameter("tjdm");
		request.setAttribute("tjdm", tjdm);
		String path = "xpj_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhTjszXn");
	}
	
	/**
	 * 
	 * @描述:条件值弹层选择方式
	 * @作者：ligl
	 * @日期：2013-12-31 上午08:54:54
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
	public ActionForward xmwhTjszTjzDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("xmwhTjszTjzDiv");
	}

	/**
	 * 
	 * @描述:修改方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-基本设置-项目设置-条件设置-保存XMDM：{xmdm}")
	public ActionForward xmwhTjszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TjszModel myForm = (TjszModel) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String xmdm = request.getParameter("xmdm");
			String json = request.getParameter("json");
			boolean result = false;
			try {
				List<TjszModel> list = JsonUtil.jsonToList(json,
						TjszModel.class);
				if (list != null && list.size() > 0) {
					result = service.saveOrUpdate(xmdm, list);
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
							: MessageKey.SYS_SAVE_FAIL;
				}else{
					messageKey = MessageKey.SYS_SAVE_SUCCESS;
				}
			} catch (Exception e) {
				e.printStackTrace();// //异常打印。。。。。////////////////
				messageKey = MessageKey.SYS_SAVE_FAIL;
			}
			
			String message =  MessageUtil.getText(messageKey);
			Map<String,String> map = new HashMap<String,String>();
			map.put("message", message);
			map.put("success", String.valueOf(result));
			//保存成功时才会走checkbfh
			if(result&&!checkbfh(xmdm)){
				map.put("bfhlist", "y");
			}
			response.getWriter().print(JSONObject.fromObject(map));
			return null;
		}

		TjszModel model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return null;
	}

	/**
	 * 
	 * @描述:删除方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-基本设置-项目设置-条件设置-删除XMDM：{xmdm}")
	public ActionForward xmwhTjszSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		String tjdm = request.getParameter("tjdm");

		if (!StringUtil.isNull(xmdm) && !StringUtil.isNull(tjdm)) {
			service.delDeal(xmdm,tjdm);//删除所有的关联表
		} 
		return null;

	}
	
	/**
	 * @描述：检查条件修改后是否有学生不符合新条件
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年3月17日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	private boolean checkbfh(String xmdm) throws Exception{
		TjszService service = new TjszService();
		List<String> list=service.getbfhxhList(xmdm);
		return list.isEmpty();
	}
	
	/**
	 * @描述：修改项目条件后审核中的不符合新条件的学生列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年3月16日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward getbfhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		TjszModel model = (TjszModel) form;
		String xmdm=request.getParameter("xmdm");
		if("query".equals(request.getParameter("type"))){
			
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			TjszService service = new TjszService();
			List<String> xhList=service.getbfhxhList(xmdm);
			List<HashMap<String,String>> resultList=service.getbfhList(model, xmdm, xhList);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String xmmc=request.getParameter("xmmc");
		request.setAttribute("path", "xg_xlzx_ybmxslist.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("xmmc", xmmc);
		return mapping.findForward("xmwhbfhList");
	}

}
