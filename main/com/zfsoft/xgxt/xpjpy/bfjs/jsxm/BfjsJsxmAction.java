/**
 * @部门:学工产品事业部
 * @日期：2016-4-1 下午02:46:50 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.jsxm;

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

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xpjpy.bfjs.cssz.BfjsCsszModel;
import com.zfsoft.xgxt.xpjpy.bfjs.cssz.BfjsCsszService;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 班风竞赛竞赛项目管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-4-1 上午10:28:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BfjsJsxmAction extends SuperAction {
	private BfjsCsszService csszService = new BfjsCsszService();
	
	public ActionForward viewBfjsJsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("viewBfjsJsxm");
	}
	
	
	/**
	 * 
	 * @描述: 获取竞赛项目结构
	 * @作者：xiaxia 1104
	 * @日期：2016-4-1 下午06:53:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward getBfjsJsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BfjsJsxmService service = new BfjsJsxmService();
		Map<String,List<HashMap<String,String>>> map = service.getBfjsJsxm();
		
		JSONObject json = JSONObject.fromMap(map);
		response.getWriter().print(json);
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 请求增加资助项目页面
	 * @作者：xiaxia [工号:1104]
	 * @日期：2016-4-1 上午09:32:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward addBfjsJsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsJsxmModel BfjsJsxmForm = (BfjsJsxmModel) form;
		BfjsJsxmForm.setXmlx(BfjsJsxmService.XMLX_PLUS);
		
		OptionUtil optionUtil = new OptionUtil();
		//加载项目类型
		List<HashMap<String,String>> xmlxList = optionUtil.getOptions(OptionUtil.BFJS_XMLX);
		
		request.setAttribute("xmlxList", xmlxList);
		return mapping.findForward("addBfjsJsxm");
	}
	
	
	/**
	 * 
	 * @描述: 请求修改竞赛项目页面
	 * @作者：xiaxia [工号:1104]
	 * @日期：2016-4-1 上午09:50:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward editBfjsJsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsJsxmModel BfjsJsxmForm = (BfjsJsxmModel) form;
		BfjsJsxmService service = new BfjsJsxmService();
		BfjsJsxmModel model = service.getModel(BfjsJsxmForm);
		boolean isUpdate = false;
		if (model != null){
			BeanUtils.copyProperties(BfjsJsxmForm, StringUtils.formatData(model));
		}
		
		OptionUtil optionUtil = new OptionUtil();
		//加载项目类型
		List<HashMap<String,String>> xmlxList = optionUtil.getOptions(OptionUtil.BFJS_XMLX);
		BfjsCsszModel csszModel = csszService.getModel();
		
		
		
		request.setAttribute("csszModel", csszModel);
		request.setAttribute("xmlxList", xmlxList);
		request.setAttribute("isUpdate", isUpdate);
		return mapping.findForward("editBfjsJsxm");
	}
	
	
	/**
	 * 
	 * @描述: 增加竞赛项目
	 * @作者：xiaxia [工号:1104]
	 * @日期：2016-4-1 上午10:05:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward saveBfjsJsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BfjsJsxmModel BfjsJsxmForm = (BfjsJsxmModel) form;
		BfjsJsxmService service = new BfjsJsxmService();
		BfjsCsszService csszService = new BfjsCsszService();
		BfjsCsszModel csszModel = csszService.getModel();
		
		BfjsJsxmForm.setXn(csszModel.getXn());
		BfjsJsxmForm.setXq(csszModel.getXq());
		
		//判断当前学期名称是否已存在
		if(service.xmmcExist(BfjsJsxmForm)){
			response.getWriter().print(getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			return null;
			
		}else{
			boolean result = service.runInsert(BfjsJsxmForm);
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		
	}
	
	
	/**
	 * 
	 * @描述: 修改竞赛项目
	 * @作者：xiaxia [工号:1104]
	 * @日期：2016-4-1 上午10:05:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward updateBfjsJsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BfjsJsxmModel BfjsJsxmForm = (BfjsJsxmModel) form;
		BfjsJsxmService service = new BfjsJsxmService();
		BfjsJsxmModel myForm = service.getModel(BfjsJsxmForm);
		
		//判断当前学期名称是否已存在
		if(!BfjsJsxmForm.getXmmc().equalsIgnoreCase(myForm.getXmmc())&&service.xmmcExist(BfjsJsxmForm)){
			response.getWriter().print(getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			return null;
		}
		
		boolean result = service.runUpdate(BfjsJsxmForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 删除竞赛项目
	 * @作者：xiaxia [工号:1104]
	 * @日期：2016-4-1 上午10:42:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward delBfjsJsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BfjsJsxmModel BfjsJsxmForm = (BfjsJsxmModel) form;
		BfjsJsxmService service = new BfjsJsxmService();
		
		boolean result = service.delBfjsJsxm(BfjsJsxmForm.getXmdm());
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}



	
	/**
	 * 
	 * @描述: 按年级显示综测比例
	 * @作者：xiaxia[1104]
	 * @日期：2016-4-1 下午02:43:41
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
	public ActionForward showXxbl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsJsxmService BfjsJsxmService = new BfjsJsxmService();
		//可设详细比例的竞赛项目
		List<HashMap<String,Object>> BfjsJsxmList = BfjsJsxmService.getBfjsJsxmListByXxbl();
		BfjsCsszModel csszModel = csszService.getModel();
		
		request.setAttribute("csszModel", csszModel);
		request.setAttribute("BfjsJsxmList", BfjsJsxmList);
		return mapping.findForward("showXxbl");
	}






}
