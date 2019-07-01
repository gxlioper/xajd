package com.zfsoft.xgxt.comm.zdybd.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.comm.zdybd.service.FlszService;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 自定义表单
 * @类功能描述: 分类设置
 * @作者： ligl
 * @时间： 2013-11-26 下午03:56:07
 * @版本： V1.0
 * @修改记录:
 */
public class FlszAction extends SuperAction {
	private String messageKey;

	/**
	 * 
	 * @描述:根据功能代码获取分类列表数据
	 * @作者：ligl
	 * @日期：2013-11-26 下午04:07:46
	 * @修改记录:
	 * @param gndm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public ActionForward getFlszList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FlszService service = new FlszService();
		String gndm = request.getParameter("gndm");
		List<HashMap<String, String>> list = service.getListByGndm(gndm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(list));
		return null;
	}

}
