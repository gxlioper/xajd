package xgxt.audit.spjl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

import xgxt.form.User;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 审批记录Action</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: zhuang</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-5-26</p>
 */
public class SpjlAction extends BasicAction {
	
	
	//审批记录无需Manage
	public ActionForward spjlManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SpjlService service = new SpjlService();
		SpjlForm myForm = (SpjlForm) form;
		String doType = request.getParameter("doType");
		String id = request.getParameter("id");
		boolean result = false;
		HashMap<String, String> spjlMap = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(doType)) {
			result = service.addSpjl(myForm);
			request.setAttribute("result", result);
		} else if ("modi".equalsIgnoreCase(doType)) {
			myForm.setId(id);
			result = service.modiSpjl(myForm);
			request.setAttribute("result", result);
		} else if ("update".equalsIgnoreCase(doType)) {
			myForm.setId(id);
			spjlMap = service.getSpjl(myForm);
		}

		request.setAttribute("rs", spjlMap);
		request.setAttribute("doType", doType);
		
		return mapping.findForward("spjlwh");
	}
	
	/**
	 * 功能: 审批记录——审核
	 * @return
	 * @author 庄敏伟
	 * Jul 2, 2010
	 */
	public ActionForward spjlAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//String beanName; 
		//由于审核流程的公用性，所以需要用beanName来作区分,也可以在申请单号规则中设置
		SpjlService service = new SpjlService();
		SpjlForm myForm = (SpjlForm) form;
		String doType = request.getParameter("doType");
		String id = request.getParameter("id");
		boolean result = false;
		List<String[]> rs  = null;
		List<HashMap<String,String>> spbzList = new ArrayList<HashMap<String,String>>();
		List<String[]> spjlList = null;
		
		//String sqdh = request.getParameter("sqdh");
		//String splc = request.getParameter("splc");
		String spgw = request.getParameter("spgw");
		//String name = request.getParameter("name");
		//name = java.net.URLDecoder.decode(name,"UTF-8");
		//String actionName = request.getParameter("actionname");
		int spbzxh = 0; 
		String spbz = "",nspgw = "";
		try {
			spjlList  = service.getSpjlList(myForm);
			//myForm.setDjlx(sybSplc.getDjlx()); //注入单据类型
			spbzList = service.getSpbzList(myForm);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		boolean thtag = true;
		List<HashMap<String,String>> thbzList = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map = new HashMap<String,String>();
		for (int i = 0; i < spbzList.size(); i++) {
			map = spbzList.get(i);
			if(spgw.equals(map.get("spgw"))){
				thtag = false;
				spbz = map.get("id");
				if(i<spbzList.size()-1){
					map = spbzList.get(i+1);
					//spbzxh = Integer.valueOf(map.get("xh"));
					nspgw = map.get("spgw");
				}else{
					nspgw = "3"; //已经是最后审批岗位，通过审批则审批完成
				}
				break;
			}
			if(thtag){
				thbzList.add(map);
			}
		}
		//有点小危险
		map = new HashMap<String,String>();
		map.put("spgw", "1");
		map.put("spgwmc", "退回申请人");
		thbzList.add(map);
		
		request.setAttribute("rs", myForm);
		request.setAttribute("spbzList", spbzList);
		request.setAttribute("thbzList", thbzList);
		request.setAttribute("spjlList", spjlList);
		request.setAttribute("spbz", spbz);
		request.setAttribute("spgw", spgw);
		request.setAttribute("nspgw", nspgw);
		request.setAttribute("name", myForm.getName());
		request.setAttribute("actionname", myForm.getActionName());
		return mapping.findForward("audit");
	}
	
	
	/**
	 * 功能: 审批流程——取消审核
	 * 取消审核需要判断审批状态时候是该岗位的下一岗位
	 * 
	 * @return
	 * @throws IOException
	 * @author 庄敏伟
	 * Jul 2, 2010
	 */
	public ActionForward spjlCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SpjlService service = new SpjlService();
		SpjlForm myForm = (SpjlForm) form;
		String doType = request.getParameter("doType");
		String id = request.getParameter("id");
		boolean result = false;
		//sqdh = request.getParameter("sqdh");
		//String splc = request.getParameter("splc");
		String spgw = request.getParameter("spgw"); //页面记录审批岗位
		String actionName = request.getParameter("actionname");
		String spzt = new String(); //需要过去审批流程前一步骤
		List<HashMap<String,String>> spbzList = new ArrayList<HashMap<String,String>>();
		try {
			
			spbzList = service.getSpbzList(myForm);
			HashMap<String,String> map = new HashMap<String,String>();
			for (int i = 0; i < spbzList.size(); i++) {
				map = spbzList.get(i);
				if(spgw.equals(map.get("spgw"))){
					if(i>0){
						map = spbzList.get(i-1);
						spzt = map.get("spgw");
					}else{
						spzt = spgw; //正常情况下应该不会出现这种情况
					}
					break;
				}
			}
			/****
			String serviseName = actionName.substring(actionName.indexOf("_")+1, actionName.length())+"Service";
			BillService billService = DynamicBeanFactory.getBillService(serviseName);
			billService.saveAuditFlow(sqdh, spzt, null);
			***/
			result = true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			result = false;
		}
		request.setAttribute("result", result);
		return mapping.findForward("audit");
	}
	
	
	
	/**
	 * 功能: 审批流程——保存（审核）
	 * 同时需要修改申请记录，采用动态加载bean方式
	 * @return
	 * @throws IOException
	 * @author 庄敏伟
	 * Jul 2, 2010
	 */

	public ActionForward spjlSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SpjlService service = new SpjlService();
		SpjlForm myForm = (SpjlForm) form;
		String doType = request.getParameter("doType");
		String tid = request.getParameter("id");
		boolean result = false;
		String sqdh = myForm.getDjh();
		String spbz = request.getParameter("spbz");
		String nspgw = request.getParameter("nspgw");
		String actionName = request.getParameter("actionname");
		String spzt = new String();
		User user = getUser(request);// 用户对象
		try {
			Date date = new Date();
			SimpleDateFormat dft = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
			myForm.setSpbz(spbz);
			myForm.setSpr(user.getUserName());//审批人设置
			myForm.setSprq(date);
			if(tid != null && !"".equals(tid)){
				service.modiSpjl(myForm); 
			}else{
				service.addSpjl(myForm);
			}
			//判断是否通过审核，不通过则需要以下处理
			if("0".equals(myForm.getSftg())){ //通过
				spzt = nspgw;
			}else{
				spzt = myForm.getThgw();
			}
			//此处需要动态加载ServiceBean，同时需要获取下一岗位，修改相应申请单记录
			/***
			String serviseName = actionName.substring(actionName.indexOf("_")+1, actionName.length())+"Service";
			BillService billService = DynamicBeanFactory.getBillService(serviseName);
			billService.saveAuditFlow(sqdh, spzt, map);
			***/
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		request.setAttribute("result", result);
		return mapping.findForward("audit");
	}

}
