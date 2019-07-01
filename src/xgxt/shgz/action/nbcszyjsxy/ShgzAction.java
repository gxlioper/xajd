/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2010-2-3 上午10:23:38</p>
 */
package xgxt.shgz.action.nbcszyjsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.shgz.form.StglForm;
import xgxt.shgz.model.nbcszysjxy.ShsjModel;
import xgxt.shgz.server.nbcszyjsxy.ShgzService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;

public class ShgzAction extends DispatchAction {	
	public ActionForward shsjManage(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		StglForm myForm              =    (StglForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userDep   = request.getSession().getAttribute("userDep").toString();
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}

		ShgzService service = new ShgzService(); 
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			ShsjModel model = new ShsjModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getShsjTopTr();
			rs = service.serv_shsjXxSearch(model);
		}		
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		if(rs==null){
			request.setAttribute("rsNum", rs);
		}else {
			request.setAttribute("rsNum", rs.size());
		}
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);

//		读写权判断
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "data_search.do?act=moveInfo", userOnLine
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");	
		request.setAttribute("realTable","nbcs_knsshhdxxb");
		request.setAttribute("tableName","view_nbcsknsshhdxx");
		return mapping.findForward("shsjManage");
	}
	public ActionForward shsjAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		StglForm myForm              =    (StglForm) form;
		String xh = request.getParameter("xh");
		HashMap<String, String> map = CommonQueryDAO.getStuInfo(xh);
		String doType = request.getParameter("doType");
		ShgzService service = new ShgzService(); 
		String  pkValue = DealString.toString(request.getParameter("pkValue"));
		String  act = request.getParameter("act");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		if(Base.isNull(pkValue)){
			if(Base.isNull(xn)){
				map.put("xn",Base.currXn);	
			}else{
				map.put("xn",xn);
			}
			if(Base.isNull(xq)){
				map.put("xq",Base.currXq);
			}else{
				map.put("xq",xq);
			}
		}else{
			map = service.serv_ShsjXx(pkValue);
		}
		if("save".equalsIgnoreCase(doType)){
			ShsjModel model = new ShsjModel();
			BeanUtils.copyProperties(model, myForm);
			boolean done = service.serv_shsjXxSave(model, pkValue);		    
			request.setAttribute("done",done);
			map.put("xn",model.getXn());
			map.put("xq",model.getXq());
			map.put("hdxm",model.getHdxm());
			map.put("hdnr",model.getHdnr());
			map.put("hdxz",model.getHdxz());		    
			map.put("khjg",model.getKhjg());
			map.put("sjts",model.getSjts());
			map.put("shry",model.getShry());
		}
		request.setAttribute("shhdxzList",CommonQueryDAO.dao_getInfotoList("shhdxzdmb", new String[] { "shhdxzdm", "shhdxzmc" }, ""));
		request.setAttribute("rs",map);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("act",act);
		return mapping.findForward("shsjAdd");
	}
	public ActionForward shsjDel(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		ShgzService service = new ShgzService(); 
		String pkVStr = request.getParameter("pkVStr");
		service.serv_shsjXxDel(pkVStr);
		return shsjManage(mapping, form, request, response);
	}
}
