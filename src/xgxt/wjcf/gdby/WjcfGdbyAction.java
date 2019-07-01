
package xgxt.wjcf.gdby;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 广东白云学院违纪处分Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-15</p>
 */
public class WjcfGdbyAction extends DispatchAction {

	String xydm = "";
	String zydm = "";
	String nj = "";
	CommonAction commonAction = new CommonAction();//公用获取加载的LIST属性
	
	/**
	 * 违纪处分审核默认页面
	 * wjcfshdefault ---- 违纪处分审核默认页面 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfshDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGdbyActionForm gdbyForm = (WjcfGdbyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();//用户类型
		String bmdm     = session.getAttribute("userDep").toString();//用户所在部门
		String xydm = "";
		//用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			gdbyForm.setXydm(xydm);
		}
		commonAction.appendProperties(request, xydm, zydm, nj);//加载页面LIST
		commonAction.appendCflbCfyy(request);//加载处分类别，处分原因列表
		return mapping.findForward("wjcfshdefault");
	}
	
	/**
	 * 违纪处分审核查询
	 * wjcfshqry ---- 违纪处分审核查询 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfshQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfGdbyActionForm gdbyForm = (WjcfGdbyActionForm) form;
		WjcfGdbyService service = new WjcfGdbyService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();//用户类型
		String bmdm     = session.getAttribute("userDep").toString();//用户所在部门
		String userName = session.getAttribute("userName").toString();
		String xydm = "";
		//用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			gdbyForm.setXydm(xydm);
		}
		WjcfShModel wjcfModel = new WjcfShModel();//违纪处分审核MODEL
		BeanUtils.copyProperties(wjcfModel, gdbyForm);
		List<HashMap<String, String>> topList = service.getWjcfshTitle(userType, userName);//查询表头
		List<String[]> resList = service.getWjcfshResult(userType, userName, wjcfModel);//查询结果
		commonAction.appendResult(request, topList, resList);//加载查询表头，结果
		commonAction.appendCflbCfyy(request);//加载处分类别，处分原因列表
		commonAction.appendProperties(request, xydm, zydm, nj);//加载页面LIST
		return mapping.findForward("wjcfshdefault");
	}
	
	/**
	 * 违纪处分审核显示详细信息
	 * wjcfshview ---- 违纪处分审核显示详细信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfshView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfGdbyActionForm gdbyForm = (WjcfGdbyActionForm) form;
		WjcfGdbyService service = new WjcfGdbyService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();//用户类型
		String userName = session.getAttribute("userName").toString();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		HashMap<String, String> resMap = service.getWjcfShView(userType, userName, pkValue);
		if (resMap != null) {
			gdbyForm.setCfsj(resMap.get("cfsj"));
			gdbyForm.setCfwh(resMap.get("cfwh"));
			gdbyForm.setCflb(resMap.get("cflb"));
			gdbyForm.setCfyy(resMap.get("cfyy"));
			gdbyForm.setSh(resMap.get("sh"));
			gdbyForm.setClyj(resMap.get("clyj"));
		}
		if (!StringUtils.isNull(userType) && StringUtils.isEqual(userType, "admin")) {
			request.setAttribute("updated", "no");//管理员不具备审核权限
		} else {
			request.setAttribute("updated", "yes");
		}
		commonAction.appendCflbCfyy(request);//加载处分类别，处分原因列表
		commonAction.appendProperties(request, xydm, zydm, nj);//加载页面LIST
		commonAction.appendChkList(request);//获取审核列表
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("wjcfshview");
	}
	
	/**
	 * 违纪处分单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfGdbyActionForm gdbyForm = (WjcfGdbyActionForm) form;
		WjcfGdbyService service = new WjcfGdbyService();
		HttpSession session = request.getSession();
		ShResultModel shModel = new ShResultModel();//单个审核MODEL
		BeanUtils.copyProperties(shModel, gdbyForm);
		shModel.setXh(request.getParameter("xh"));
		shModel.setPkValue(request.getParameter("pkValue"));
		String userType = session.getAttribute("userType").toString();//用户类型
		String userName = session.getAttribute("userName").toString();
		boolean bChk = service.chkDataExists(shModel, request.getParameter("pkValue"));//保存前先检查数据是否重复
		if (bChk) {
			request.setAttribute("inserted", "exists");
		} else {
			boolean bFlag = service.shResult(userType, userName, shModel, request);//单个审核
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		gdbyForm.setClyj(DealString.toGBK(gdbyForm.getClyj()));
		gdbyForm.setCfwh(DealString.toGBK(gdbyForm.getCfwh()));
		commonAction.appendCflbCfyy(request);//加载处分类别，处分原因列表
		commonAction.appendChkList(request);//获取审核列表
		request.setAttribute("rs", new HashMap<String, String>());
		return mapping.findForward("wjcfshview");
	}
}
