/**
 * @部门:学工产品事业部
 * @日期：2013-6-8 下午02:25:34 
 */  
package com.zfsoft.xgxt.jygl.zfss;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;

/** 
 * @系统名称: 社区管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： huj
 * @时间： 2013-6-8 下午02:25:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JzygAction extends SuperAction {
	
	private static final String url = "szdw_showJzyg.do";
	
	
	public ActionForward showJzyg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JzygForm model = (JzygForm) form;
		JzygService service = new JzygService();
		
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
		String path = "szdw_showJzyg.do";
		request.setAttribute("path", path);
		return mapping.findForward("showJzyg");
	}

}
