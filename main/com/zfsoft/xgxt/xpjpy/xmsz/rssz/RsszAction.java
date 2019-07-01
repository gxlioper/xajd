/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:07:42
 */
package com.zfsoft.xgxt.xpjpy.xmsz.rssz;

import java.text.DateFormat;
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
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshModel;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhService;
import common.Globals;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 项目维护-人数设置
 * @作者： ligl
 * @日期：2013-8-5 上午11:07:42
 * @版本： V1.0
 * @修改记录:
 */
public class RsszAction extends SuperAction {

	private static final String urlJxj = "xpj_xmwh.do?method=xmwhCx&xmxz=1&sindex=0";
	private static final String urlBz = "xpj_xmwh.do?method=xmwhCx&xmxz=2&sindex=1";
	
	/**
	 * 
	 * @描述:基本查询方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: void 返回类型
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz})
	public ActionForward xmwhRsszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszModel myForm = (RsszModel) form;
		User user = getUser(request);
		RsszService service = new RsszService();
		String xmdm = request.getParameter("xmdm");
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		String rsfpnj = xmwhService.getRsfpnj(xmdm);
		request.setAttribute("rsfpnj", rsfpnj);//人数控制年级
		List<String>  njList = service.getNj();//得到所有包含学生的年级
		request.setAttribute("njList", njList);
		request.setAttribute("njArrList", njList);
//		List<HashMap<String, String>> xyList = Base.getXyList();
//		request.setAttribute("xyList", xyList);
		// 年级学院专业班级
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		if (QUERY.equals(myForm.getType())) {
			if(Base.xxdm.equals("10704")){//西安科技大学增加是否优秀学生干部
				String sfyxgb = xmwhService.getSfyxgb(myForm.getXmdm());
				myForm.setSfyxgb(sfyxgb);
			}
			List<HashMap<String, String>> resultList = service
					.getRsszList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//是否有学生申请项目
		SqshService sqshService = new SqshService();
		boolean flag = sqshService.isExistsFlowData(xmdm);
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
		
		CsszService csszService = new CsszService();
		request.setAttribute("rsjsfs", csszService.getCsz("rsjsfs"));
		request.setAttribute("xmje", request.getParameter("xmje"));
		//操作方式
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("czfs", request.getParameter("czfs"));
		
		//浙大-学院人数设置金额上限提醒（个性化）
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhRsszCx");
	}

	/**
	 * 
	 * @描述:修改方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录:
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-基本设置-项目设置-人数设置-保存XMDM：{xmdm}")
	public ActionForward xmwhRsszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszModel myForm = (RsszModel) form;
		RsszService service = new RsszService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String messageKey = null;
			User user = getUser(request);
			if(myForm.getRsfpfs() != null && myForm.getRsfpfs().equals(Constants.RSKZFW_XX)){//学校
				messageKey = service.setZzrsForXx(myForm,user);
			}else{
				messageKey = service.setZzrs(myForm,user);
			}
//			messageKey = "保存成功！";
			response.getWriter().print(getJsonMessage(messageKey));
			
			return null;
		}
		
		if("xyrssz".equals(myForm.getCzfs()) && Base.xxdm.equals(Globals.XXDM_ZJDX)){  //浙江大学个性化 保存操作人和时间
			User user = getUser(request);
			myForm.setZd1(user.getUserName());
			DateFormat date = DateFormat.getDateTimeInstance();//精确到时分秒
			myForm.setZd2(date+"");
		}

		RsszModel model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return null;
	}

	/**
	 * 
	 * @描述:比例设置方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录:
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-基本设置-项目设置-人数设置-分配设置-保存XMDM：{xmdm}")
	public ActionForward xmwhRsszBlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszModel myForm = (RsszModel) form;
		User user = getUser(request);
		RsszService service = new RsszService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String json = request.getParameter("json");
			List<RsszModel> list = JsonUtil.jsonToList(json,
					RsszModel.class);
			String messageKey;
			String fpfs = request.getParameter("fpfs");
			String zme = null;
			if (fpfs != null && fpfs.equals("zme")) {//总名额方式
				zme = request.getParameter("zme");
			}
			String rsfpnj = request.getParameter("rsfpnj");//人数控制年级
			if(Base.xxdm.equals("10704")){//西安科技大学增加是否优秀学生干部
				String sfyxgb = new XmwhService().getSfyxgb(myForm.getXmdm());
				myForm.setSfyxgb(sfyxgb);
			}
			messageKey = service.fpsz(myForm, list, zme,rsfpnj,user);
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}

		RsszModel model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return null;
	}

	/**
	 * 
	 * @描述:删除方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录:
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-基本设置-项目设置-人数设置-删除XMDM：{xmdm}")
	public ActionForward xmwhRsszSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		RsszService service = new RsszService();

		if (!StringUtil.isNull(xmdm)) {
			service.runDeleteByXmdm(xmdm);
		} else {
			throw new SystemException(MessageKey.EXP_SYS_ERROR);
		}
		return null;
	}

	/**
	 * 
	 * @描述:查询项目已设置人数
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
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
	public ActionForward xmwhRsszYszrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszModel myForm = (RsszModel) form;
		RsszService service = new RsszService();
		int yszrs = service.getYszrs(myForm);
		XmwhService xmwhService = new XmwhService();
		String zme = "";
		if (myForm.getXmdm() != null) {
			HashMap<String, String> hashMap = xmwhService.getDataById(myForm
					.getXmdm());
			if (hashMap != null) {
				zme = hashMap.get("rsfpz");
			}
		}

		response.setContentType("text/html;charset=gbk");
		Map<String, String> map = new HashMap<String, String>();
		map.put("yszrs", yszrs + "");
		map.put("zme", zme);
		response.getWriter().print(JSONObject.fromMap(map));
		return null;
	}

	/**
	 * 
	 * @描述:历年名额
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
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
	public ActionForward xmwhRsszLnme(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszModel myForm = (RsszModel) form;
		RsszService service = new RsszService();
		String xmdm = request.getParameter("xmdm");
		request.setAttribute("xmdm", xmdm);
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);

		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> xyList = service.getSyxy();// 所有学院信息
			PjjgService pjjgService = new PjjgService();
			List<HashMap<String, String>> tjrsList = pjjgService.tjrs(xmmc);
			HashMap<String, List<HashMap<String, String>>> map = new HashMap<String, List<HashMap<String, String>>>();
			map.put("xyList", xyList);
			map.put("tjrsList", tjrsList);
			map.put("xqList", Base.getXqList());
			response.getWriter().print(JSONObject.fromMap(map));
			return null;
		}

		String path = "xpj_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);

		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhRsszLnme");
	}
	
	
	
	/**
	 * 
	 * @描述:根据项目代码查询控制人数
	 * @作者：cq[工号：785]
	 * @日期：2014-1-17 下午02:52:15
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
	public ActionForward xmwhRsszCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszModel myForm = (RsszModel) form;
		User user = getUser(request);
		RsszService service = new RsszService();
		String xmdm = request.getParameter("xmdm");
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		String rsfpnj = xmwhService.getRsfpnj(xmdm);
		request.setAttribute("rsfpnj", rsfpnj);//人数控制年级
		List<String>  njList = service.getNj();//得到所有包含学生的年级
		request.setAttribute("njList", njList);
		request.setAttribute("njArrList", njList);
		// 年级学院专业班级
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service
					.getRsszList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//是否有学生申请项目
		SqshService sqshService = new SqshService();
		boolean flag = sqshService.isExistsFlowData(xmdm);
		request.setAttribute("spzt", flag);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhRsszCk");
	}
    /**
     * 
     * @描述:ajax验证人数上限和金额上限
     * @作者：xiaxia[工号：1104]
     * @日期：2015-3-19 上午10:52:04
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
	public ActionForward rsszCheckAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszModel myForm = (RsszModel) form;
		User user = getUser(request);
		RsszService service = new RsszService();
			List<HashMap<String, String>> resultList = service
					.getJxjze(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		
	}
}
