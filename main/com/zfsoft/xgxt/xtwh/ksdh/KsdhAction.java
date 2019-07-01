/**
 * @部门:学工产品事业部
 * @日期：2015-10-8 上午09:26:47 
 */  
package com.zfsoft.xgxt.xtwh.ksdh;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.JsonUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 快速导航类 
 * @作者： yxy[工号:1206]
 * @时间： 2015-10-8 上午09:26:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KsdhAction extends SuperAction<KsdhForm, KsdhService> {
	private KsdhService service = new KsdhService();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	/**
	 * 
	 * @描述：编辑我的应用
	 * @作者：yxy[工号：1206]
	 * @日期：2015-10-8 上午11:48:02
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
	public ActionForward getAppView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		User user = getUser(request);
		//判断是否有设置值，如果没有就插入默认值
		if(!service.checkExistSz(user)){
			service.insertInto_mrz(user);
		}
		//功能菜单查询
		List<HashMap<String, String>> gncdszlist = service.getGncdSzList(user);
		JSONArray json_gncdszlist = JsonUtil.ListToJSON(gncdszlist);
		//分类查询
		List<HashMap<String, String>> flheadlist = service.getFlcx_head(user);
		JSONArray json_flheadlist = JsonUtil.ListToJSON(flheadlist);
		//分类内容查询
		List<HashMap<String, String>> flContentlist = service.getFlcx(user.getUserType(), user.getUserName());
		JSONArray json_flContentlist = JsonUtil.ListToJSON(flContentlist);
		HashMap<String, JSONArray> datamap = new HashMap<String, JSONArray>();
		datamap.put("gncd", json_gncdszlist);
		datamap.put("flhead", json_flheadlist);
		datamap.put("flContent", json_flContentlist);
		JSONObject JsonObj = JSONObject.fromObject(datamap);
		response.getWriter().print(JsonObj);
		return null;
	}
	
	

	
	/**
	 * 
	 * @描述:@gnmkdm 分类dm,@gnmkmc 名称,查询
	 * @作者：yxy[工号：1206]
	 * @日期：2015-10-12 下午03:15:17
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
    public ActionForward QueryData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	//String gnmkdm = request.getParameter("gnmkdm");
    	KsdhForm ksdh = (KsdhForm)form;
		String gnmkmc = URLDecoder.decode(URLDecoder.decode(ksdh.getGnmkmc(),"UTF-8"),"UTF-8");
		String mkfldm = URLDecoder.decode(URLDecoder.decode(ksdh.getMkfldm(),"UTF-8"),"UTF-8");
		String[] htmlgnmkdm =ksdh.getGnmkms();
		User user = getUser(request);
		List<HashMap<String, String>> flContentlist = service.QueryData(user.getUserType(), gnmkmc, mkfldm, user.getUserName(), htmlgnmkdm);
		JSONArray json_flContentlist = JsonUtil.ListToJSON(flContentlist);
		response.getWriter().print(json_flContentlist);
		return null;
	}
    
    public ActionForward close_saveData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	User user = getUser(request);
    	boolean result = service.del_originalData(user);
    	if(result){
    		KsdhForm ksdh = (KsdhForm)form;
    		String[] gnmkdms = ksdh.getGnmkms();
    		String[] xssxs = ksdh.getXssxs();
    		if(xssxs != null && xssxs.length > 0 && gnmkdms != null && gnmkdms.length >0 ){
    			for (int i = 0; i < xssxs.length; i++) {
        			KsdhForm saveform = new KsdhForm();
        			saveform.setCzsj(GetTime.getTimeByFormat(DATE_FORMAT));
        			saveform.setYhm(user.getUserName());
        			saveform.setCdid(gnmkdms[i]);
        			saveform.setXssx(xssxs[i]);
        			saveform.setYhlx(user.getUserType());
        			try {
        				result = service.runInsert(saveform);
					} catch (Exception e) {
						// TODO: handle exception
						System.out.print(e.getStackTrace());
						response.getWriter().print("设置失败！");
						return null;
					}
        			
    			}
    			if(result){
    				response.getWriter().print("设置成功！");
    				return null;
    			}
    		}
    		
    	}else{
    		response.getWriter().print("设置失败！");
    		return null;
    	}
    	return null;
    	
    }
    
    /**
     * 
     * @描述:首页我的应用入口刷新
     * @作者：yxy[工号：1206]
     * @日期：2015-10-21 上午09:44:43
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
    public ActionForward refreshIndexHTML(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	User user = getUser(request);
    	List<HashMap<String, String>> gncdszlist = service.getGncdSzList(user);
		JSONArray json_gncdszlist = JsonUtil.ListToJSON(gncdszlist);
		response.getWriter().print(json_gncdszlist);
    	return null;
    }
}
