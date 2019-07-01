package xsgzgl.gygl.cssz;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;

import com.zfsoft.xgxt.base.log.SystemLog;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理_参数设置action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author sjf
 * @version 1.0
 */
public class CsszAction extends BasicExtendAction{
	
	/**
	 * 时间开关参数设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public ActionForward sjszManage(ActionMapping mapping, ActionForm form, 
				HttpServletRequest request, HttpServletResponse response) throws Exception{
		CsszService service = new CsszService();
		
		// 操作
		String doType = request.getParameter("doType");
		
		CsszForm myForm = (CsszForm)form;
		myForm.getPrimarykey_cbv();
		CsszModel model = new CsszModel();
		
		BeanUtils.copyProperties(model, myForm);
		
		if("xg".equalsIgnoreCase(doType)){
			String message = service.saveKqzt(model) ? "操作成功！" : "操作失败！"; 
			/*String message = flag ? "修改成功!":"修改失败!";
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);*/
			
			request.setAttribute("message", message);
			//return null;
		}
		
		List<String[]> rs = service.querySjsz(model);
		request.setAttribute("searchTj", myForm.getSearchModel());
		
		setWriteAbleAndTitle(request, "gyglnew_cssz_sjsz.do");
		
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("sjsz"));
		
		return mapping.findForward("sjszManage");
	}
	
	/**
	 * 
	 * @描述:修改启用弹出层
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-22 下午01:26:11
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
	@SystemLog(description="访问公寓管理-参数设置-操作时间设置-修改IDLIST:{idList}")
	public ActionForward qySjsz(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		CsszService service = new CsszService();
		
		// 操作
		String doType = request.getParameter("doType");
		String idList = request.getParameter("idList");
		CsszForm myForm = (CsszForm)form;
		idList.substring(0, idList.length()-1);
		request.setAttribute("idList", idList);
		myForm.getPrimarykey_cbv();
		CsszModel model = new CsszModel();
		BeanUtils.copyProperties(model, myForm);
		if("xg".equalsIgnoreCase(doType)){
			model.setPrimarykey_cbv(idList.split(","));
			String message = service.saveKqzt(model) ? "操作成功！" : "操作失败！"; 
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
			return null;
		}
		
		
	
		return mapping.findForward("qySjsz");
	}
	/**
	 * 操作参数设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@SystemLog(description="访问公寓管理-参数设置-操作时间设置-保存")
	public ActionForward czcsszManage(ActionMapping mapping, ActionForm form, 
				HttpServletRequest request, HttpServletResponse response) throws Exception{
		CsszForm myForm = (CsszForm)form;
		CsszService service = new CsszService();
		String doType=request.getParameter("doType");
		if("save".equalsIgnoreCase(doType)){
			boolean flag=service.saveSjfw(myForm);
			request.setAttribute("result", flag);
		}
		HashMap<String, String> rs=new HashMap<String, String>();
		rs=service.getSjfw();
		request.setAttribute("rs", rs);
		setWriteAbleAndTitle(request, "gyglnew_cssz_czcssz.do");
		return mapping.findForward("czcsszManage");
	}
}
