/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.rssz;

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
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshService;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhService;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助
 * @类功能描述: 项目维护-人数设置
 * @作者： ligl
 * @时间： 2013-4-18 下午02:42:37
 * @版本： V1.0
 * @修改记录:
 */
public class XmwhRsszAction extends SuperAction {
	private XmwhRsszService service = new XmwhRsszService();
	
	private static final String url = "xszz_xmwh.do?method=xmwhCx";

	/**
	 * 
	 * @描述:基本查询方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xmwhRsszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhRsszForm myForm = (XmwhRsszForm) form;
		String xmdm = request.getParameter("xmdm");
		String xslb = request.getParameter("xslb");
		XmwhService xmwhService = new XmwhService();
		HashMap<String, String> zzxmMap = xmwhService.getDataById(xmdm);
		request.setAttribute("xmmc", zzxmMap.get("xmmc"));
		String rskznj = xmwhService.getRskznj(xmdm);
		request.setAttribute("rskznj", rskznj);//人数控制年级
		List<String>  njList = service.getNj();//得到所有包含学生的年级
		request.setAttribute("njArrList", njList);
		request.setAttribute("xslb", xslb);

		// 年级学院专业班级
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		myForm.setXn(Base.currXn);// 当前学年
		myForm.setXq(Base.currXq);// 当年学期
		myForm.setPdxn(zzxmMap.get("pdxn"));
		myForm.setPdxq(zzxmMap.get("pdxq"));
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service
					.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//是否有学生申请项目
		XszzSqshService xszzSqshService = new XszzSqshService();
		boolean flag = xszzSqshService.isExistShlcData(xmdm);
		request.setAttribute("spzt", flag);
		request.setAttribute("xslb", xslb);
		String path = "xszz_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);

		FormModleCommon.commonRequestSet(request);
		List<HashMap<String, String>> syList = service.getSyList();//书院list
		request.setAttribute("syList",syList);
		return mapping.findForward("xmwhRsszCx");
	}

	/**
	 * 
	 * @描述:修改方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-资助管理-资助项目设置-人数设置-修改GUIDS:{guids}")
	public ActionForward xmwhRsszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhRsszForm myForm = (XmwhRsszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String messageKey;
			messageKey = service.setZzrs(myForm);
//			messageKey = "保存成功！";
			response.getWriter().print(getJsonMessage(messageKey));
			
			return null;
		}

		XmwhRsszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		return null;
	}

	/**
	 * 
	 * @描述:比例设置方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-资助管理-资助项目设置-人数设置-设置比例JSON:{json}")
	public ActionForward xmwhRsszBlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhRsszForm myForm = (XmwhRsszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String json = request.getParameter("json");
			List<XmwhRsszForm> list = JsonUtil.jsonToList(json,
					XmwhRsszForm.class);
			String messageKey;
			String fpfs = request.getParameter("fpfs");
			String zme = null;
			if (fpfs != null && fpfs.equals("zme")) {//总名额方式
				zme = request.getParameter("zme");
			}
			String rskznj = request.getParameter("rskznj");//人数控制年级
			messageKey = service.fpsz(myForm, list, zme,rskznj);
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}

		XmwhRsszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		return null;
	}

	/**
	 * 
	 * @描述:删除方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-资助管理-资助项目设置-人数设置-删除XMDM:{xmdm}")
	public ActionForward xmwhRsszSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");

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
	 * @日期：2013-5-28 上午11:38:30
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
		XmwhRsszForm myForm = (XmwhRsszForm) form;
		int yszrs = service.getYszrs(myForm);
		XmwhService xmwhService = new XmwhService();
		String zme = "";
		if (myForm.getXmdm() != null) {
			HashMap<String, String> hashMap = xmwhService.getDataById(myForm
					.getXmdm());
			if (hashMap != null) {
				zme = hashMap.get("zme");
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
	 * @日期：2013-5-28 下午03:11:46
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
		XmwhRsszForm myForm = (XmwhRsszForm) form;
		String xmdm = request.getParameter("xmdm");
		request.setAttribute("xmdm", xmdm);
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);

		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> xyList = service.getSyxy();// 所有学院信息
			ZzxmjgService zzxmjgService = new ZzxmjgService();
			List<HashMap<String, String>> tjrsList = zzxmjgService.tjrs(xmmc);
			HashMap<String, List<HashMap<String, String>>> map = new HashMap<String, List<HashMap<String, String>>>();
			map.put("xyList", xyList);
			map.put("tjrsList", tjrsList);
			map.put("xqList", Base.getXqList());
			response.getWriter().print(JSONObject.fromMap(map));
			return null;
		}

		String path = "xszz_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);

		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhRsszLnme");
	}
	
	
	/**
	 * 
	 * @描述:根据项目代码查询控制人数
	 * @作者：cq [工号：785]
	 * @日期：2014-4-22 下午01:54:24
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
		
		XmwhRsszForm myForm = (XmwhRsszForm) form; 
		XmwhService xmwhService = new XmwhService();
		
		String xmdm = request.getParameter("xmdm");
		String xmmc = xmwhService.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		
		
		// 年级学院专业班级
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		myForm.setXn(Base.currXn);// 当前学年
		myForm.setXq(Base.currXq);// 当年学期
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service
					.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//是否有学生申请项目
		XszzSqshService xszzSqshService = new XszzSqshService();
		boolean flag = xszzSqshService.isExistShlcData(xmdm);
		request.setAttribute("spzt", flag);
		
		FormModleCommon.commonRequestSet(request);

		List<HashMap<String, String>> syList = service.getSyList();//书院list
		request.setAttribute("syList",syList);
		return mapping.findForward("xmwhRsszCk");
	}

}
