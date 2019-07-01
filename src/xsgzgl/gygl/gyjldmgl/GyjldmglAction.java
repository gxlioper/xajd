package xsgzgl.gygl.gyjldmgl;

import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.newp.StringUtil;

public class GyjldmglAction extends BasicAction{
	/**
	 * 公寓纪律大类代码维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-公寓纪律-公寓纪律代码维护-纪律大类-{doType}维护JLDLDM:{jldldm},JLDLMC:{jldlmc}")
	public ActionForward gyjldlManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		// 操作
		request.setAttribute("path", "gyglnew_gyjldmgl_gyjldmgl.do");
		String doType = request.getParameter("doType");
		GyjldmglForm myForm = (GyjldmglForm) form;
		GyjldmglService service = new GyjldmglService();
		
		if (!Base.isNull(doType)) {
			String message = "参数错误！";
			if ("add".equals(doType)) {// 增加
				message = service.saveJldlInfo(myForm, "add");
				//String message = flag ? "修改成功!":"修改失败!";
				Map<String,String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
				return null;

				
			} else if ("update".equals(doType)) {// 修改
				message = service.saveJldlInfo(myForm, "update");
				Map<String,String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
				return null;
			} else if ("del".equalsIgnoreCase(doType)) {// 删除
				message = service.deleteJldlInfo(myForm);
			} 
			request.setAttribute("message", message);
		}

		List<String[]> rs = service.getJldlList(myForm);

		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("jldl"));
		FormModleCommon.commonRequestSet(request);
		/*浙江旅游个性化功能*/
		if(Base.xxdm.equals("12867")){
			return mapping.findForward("zjlydmwh");
		}
		return mapping.findForward("gyjldlManage");
	}
	/**
	 * 
	 * @描述:增加公寓纪律类别代码维护弹出层
	 * @作者：dlq[工号：995]
	 * @日期：2013-8-26 上午10:50:15
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
	public ActionForward gyjldlZjxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// 操作
		String doType = request.getParameter("doType");
		request.setAttribute("doType", doType);
		if(UPDATE.equalsIgnoreCase(doType)){
			String jldldm = URLDecoder.decode(request.getParameter("jldldm"),"utf-8");
			String jldlmc = URLDecoder.decode(request.getParameter("jldlmc"),"utf-8");
			request.setAttribute("jldldm", jldldm);
			request.setAttribute("jldlmc", jldlmc);
		}

		return mapping.findForward("gyjldlZj");
	}
	
	/**
	 * 公寓纪律类别代码维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-公寓纪律-公寓纪律代码维护-纪律类别-{doType}维护JLLBDM:{jllbdm},JLLBMC:{jllbmc},JLLBKF:{jllbkf},JLDLDM:{jldldm}")
	public ActionForward gyjllbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// 操作
		request.setAttribute("path", "gyglnew_gyjldmgl_gyjldmgl.do");
		String doType = request.getParameter("doType");
		GyjldmglForm myForm = (GyjldmglForm) form;
		GyjldmglService service = new GyjldmglService();

		if (!Base.isNull(doType)) {
			String message = "参数错误！";
			if ("add".equals(doType)) {// 增加
				message = service.saveJllbInfo(myForm, "add");
				Map<String,String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
				return null;
			} else if ("update".equals(doType)) {// 修改
				message = service.saveJllbInfo(myForm, "update");
				Map<String,String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
				return null;
			} else if ("del".equalsIgnoreCase(doType)) {// 删除
				message = service.deleteJllbInfo(myForm);
			} 
			request.setAttribute("message", message);
		}

		List<String[]> rs = service.getJllbList(myForm);

		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("jllb"));
		request.setAttribute("jldlList", service.getJllbListMap(myForm));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gyjllbManage");
	}
	/**
	 * 
	 * @描述:增加公寓纪律类别代码维护弹出层
	 * @作者：dlq[工号：995]
	 * @日期：2013-8-26 上午10:50:15
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
	public ActionForward gyjllbZjxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyjldmglForm myForm = (GyjldmglForm) form;
		GyjldmglService service = new GyjldmglService();
		// 操作
		String doType = request.getParameter("doType");
		request.setAttribute("doType", doType);
		if(UPDATE.equalsIgnoreCase(doType)){
			String jllbdm = request.getParameter("jllbdm");
			String jllbmc = request.getParameter("jllbmc");
			String jldldm = request.getParameter("jldldm");
			String jllbkf = request.getParameter("jllbkf");
			request.setAttribute("jllbdm", jllbdm);
			request.setAttribute("jllbmc", jllbmc);
			request.setAttribute("jldldm", jldldm);
			request.setAttribute("jllbkf", jllbkf);
			
		}
		request.setAttribute("topTr", service.getTopTr("jllb"));
		request.setAttribute("jldlList", service.getJllbListMap(myForm));
		
		return mapping.findForward("gyjllbZj");
	}
	
	/**
	 * 公寓处分类别代码维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-公寓纪律-公寓纪律代码维护-处分类别-{doType}维护CFLBDM:{cflbdm},CFLBMC:{cflbmc}")
	public ActionForward gyjlcfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// 操作
		request.setAttribute("path", "gyglnew_gyjldmgl_gyjldmgl.do");
		String doType = request.getParameter("doType");
		GyjldmglForm myForm = (GyjldmglForm) form;
		GyjldmglService service = new GyjldmglService();

		if (!Base.isNull(doType)) {
			String message = "参数错误！";
			if ("add".equals(doType)) {// 增加
				message = service.saveCflbInfo(myForm, "add");
				Map<String,String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
				return null;
			} else if ("update".equals(doType)) {// 修改
				message = service.saveCflbInfo(myForm, "update");
				Map<String,String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
				return null;
			} else if ("del".equalsIgnoreCase(doType)) {// 删除
				message = service.saveCflbInfo(myForm,"delete");
			} 
			request.setAttribute("message", message);
		}

		List<String[]> rs = service.getCflbList(myForm);

		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("cflb"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gyjlcfManage");
	}
	/**
	 * 
	 * @描述:增加公寓处分类别代码维护弹出层
	 * @作者：dlq[工号：995]
	 * @日期：2013-8-26 上午10:50:15
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
	public ActionForward gyjlcfZJxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 操作
		String doType = request.getParameter("doType");
		request.setAttribute("doType", doType);
		if(UPDATE.equalsIgnoreCase(doType)){
			String cflbdm = request.getParameter("cflbdm");
			String cflbmc = request.getParameter("cflbmc");
			request.setAttribute("cflbdm", cflbdm);
			request.setAttribute("cflbmc", cflbmc);
		}
		
		return mapping.findForward("gyjlcfZj");
	}
	
	/*浙江旅游个性化start*/
	/**
	 * 
	 * @描述:浙江旅游个性化查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-4-28 上午08:55:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward gyglDmwhcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GyjldmglForm modle = (GyjldmglForm)form;
		User user = getUser(request);
		List<HashMap<String, String>> resultlist = new ZjlyGyglDao().getPageList(modle, user);
		JSONArray datalist = JSONArray.fromObject(resultlist);
		response.getWriter().print(datalist);
		return null;
	}
	
	public ActionForward addZjlydm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		return mapping.findForward("add");
	}
	
	public ActionForward updateZjlydm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		GyjldmglForm myForm = (GyjldmglForm) form;
		ZjlyGyglDao dao = new ZjlyGyglDao(); 
		GyjldmglForm model = dao.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		request.setAttribute("dm", model.getGyjllbdldm());
		request.setAttribute("jkf", model.getLb().equals("jf") ? "加分项":"扣分项");
		return mapping.findForward("update");
	}
	
	/**
	 * 
	 * @描述:查看
	 * @作者：yxy[工号：1206]
	 * @日期：2016-4-28 下午02:48:27
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
	public ActionForward ckZjlydm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		GyjldmglForm myForm = (GyjldmglForm) form;
		ZjlyGyglDao dao = new ZjlyGyglDao(); 
		GyjldmglForm model = dao.getModel(myForm);
		request.setAttribute("jg", model);
		request.setAttribute("jkf", model.getLb().equals("jf") ? "加分项":"扣分项");
		return mapping.findForward("ck");
	}
	
	/**
	 * 
	 * @描述:保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-4-28 下午02:47:47
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
	public ActionForward saveZjlydm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		GyjldmglForm modle = (GyjldmglForm)form;
	    ZjlyGyglDao dao = new ZjlyGyglDao(); 
	    boolean flag = dao.checkIsExists(modle);
	    String message = "";
	    HashMap<String, String> map = new HashMap<String, String>();
		if(modle.getType().equals("add")){
			if(flag){
				message = "代码或名称已存在，请确认！";
			    response.getWriter().print(getJsonMessage(message));
			    return null;
			}
			 flag = dao.runInsert(modle);
			 message = flag ? "保存成功！" :"保存失败！";
			 response.getWriter().print(getJsonMessage(message));
			 return null;
		}else{
			if(flag){
				message = "代码或名称已存在，请确认！";
			    response.getWriter().print(getJsonMessage(message));
			    return null;
			}
			 flag = dao.runUpdate(modle);
			 message = flag ? "保存成功！" :"保存失败！";
			 response.getWriter().print(getJsonMessage(message));
			 return null;
			
		}
	}
	
	/**
	 * 
	 * @描述:删除
	 * @作者：yxy[工号：1206]
	 * @日期：2016-4-28 下午02:47:16
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
	public ActionForward delZjlydm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		//获得id
		String values = request.getParameter("values");
		String message = "";
		ZjlyGyglDao dao = new ZjlyGyglDao(); 
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			if(dao.checkIsUsingNow(ids)){
				message = "被删除的项目正在被使用！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			int num = dao.runDelete(ids);
			boolean result = num > 0;
			 message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	protected JSONObject getJsonMessage(String message){
		Map<String,String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map); 
		return json;
	}
	/*浙江旅游个性化end*/
}
