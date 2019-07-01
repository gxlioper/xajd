/**
 * @部门:学工产品事业部
 * @日期：2016-4-20 下午06:31:00 
 */  
package com.zfsoft.xgxt.xszz.qxknszg;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;



/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 管理模块
 * @类功能描述: TODO(取消困难生资格) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-4-21 上午08:35:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class QxknszgAction extends SuperAction<QxknszgForm, QxknszgService> {
	
	
	//private static final String QXKNSZG = "qxknszg";
	//private static final String SQZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	//private BdpzService bdpzService = new BdpzService();
	//private List<HashMap<String,String>> jbxxList = null;	
	private static final String url = "xszz_qxknszg_cx.do";
	
	/**
	 * 
	 * @描述:TODO(取消困难生资格)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-27 上午09:21:41
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
	@SystemAuth(url = url)
	@SystemLog(description="学生资助(新)-困难生认定-取消困难生资格跳转页面")
	public ActionForward qxKnszgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		QxknszgForm model = (QxknszgForm) form;
		QxknszgService service = new QxknszgService();
		
		if (QUERY.equals(model.getType())){			
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
		
		String path = "xszz_qxknszg_cx.do";		
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qxKnszgManage");
		
	}
	
	/**
	 * 
	 * @描述:TODO(学生资助(新)-困难生认定-取消困难生资格)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-25 下午03:34:13
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
	@SystemAuth(url = url)
	@SystemLog(description="学生资助(新)-困难生认定-取消困难生资格-取消")
	public ActionForward cancelKnsrdzg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		
			QxknszgForm model = (QxknszgForm) form;
			QxknszgService service = new QxknszgService();
			User user = getUser(request);				
			
			if (SAVE.equalsIgnoreCase(model.getType())){				
				// 唯一性判断（学号，学年，学期）
				boolean isExist = false;				
				String qxyy = request.getParameter("qxyy");
				qxyy = java.net.URLDecoder.decode(qxyy,"UTF-8");
				model.setQxyy(qxyy);
				if (!isExist) {	
					
					boolean result = service.cancelKnsrdzg(model,user);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;	
					
				} else {				
					response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
					return null;					
				}
			}			
			request.setAttribute("countNum", model.getCountNum()); 													
			return mapping.findForward("cancelKnsrdzg");			
	}
	
	/**
	 * 
	 * @描述:TODO(获取)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-27 上午09:37:43
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
	@SystemAuth(url = url)
	@SystemLog(description="学生资助(新)-困难生认定-取消困难生资格-获取要取消的困难生人数")
	public ActionForward getCountNum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		QxknszgForm model = (QxknszgForm) form;
		QxknszgService service = new QxknszgService();		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);				
		User user = getUser(request);
		String countNum =  service.getCountNum(model,user);		
		response.getWriter().print(countNum);		
		return null;
		
	}
	
	
	

}
