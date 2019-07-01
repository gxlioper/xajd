/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-10-30 下午01:57:26</p>
 */
package xgxt.xsgygl.zjgszyjsxy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.xsgygl.dao.GyglShareDAO;
import xgxt.xsgygl.dao.gyglDao;

public class GyglZjgszyAction extends DispatchAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		HttpSession session = request.getSession();
		try {
			//判断用户读写权	
			String writeAble = Base.getWriteAble(request);
			String dxq = request.getParameter("writeAble");
			if(!"".equalsIgnoreCase(dxq) && dxq != null){
				writeAble = dxq;
			}
			/** 在线检测 */
			int i = Base.chkTimeOut(session);
			if (i <= 2) {
				request.setAttribute("errMsg", "登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
			request.setAttribute("writeAble", writeAble);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "出现灾难性故障，" + e.toString());
			return new ActionForward("/errMsg.do", false);
		}
		request.setAttribute("method",request.getParameter("method"));
		return super.execute(mapping, form, request, response);
	}
	/**
	 * 公寓团总支管理查询默认页面
	 */
	public ActionForward gyTzzManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		gyglDao gyDao = new gyglDao();		
		request.setAttribute("ldList",gyDao.getGyLdList());//公寓楼栋列表
 		request.setAttribute("qshList",gyDao.getQshList());//公寓寝室号列表 
 		request.setAttribute("xiaoquList", gyDao.getXiaoQuList());
 		request.setAttribute("tableName","view_gytzzxx");
 		request.setAttribute("realTable","tytzzxx");
		return mapping.findForward("tzzDefault");
	}
	/**
	 * 公寓团总支管理查询执行
	 */
	public ActionForward gyTzzQuery(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		GyglZjgszyForm myform        = (GyglZjgszyForm)form;
		GyTzzModel mymodel           = new GyTzzModel();
		GyglZjgszyService myservice  = new GyglZjgszyService();
		ArrayList<HashMap<String, String>> topTr = myservice.getTzzSearchTitle();
		BeanUtils.copyProperties(mymodel, myform);
		ArrayList<String[]> rs  = myservice.serv_gyTzzQuery(mymodel);		
		request.setAttribute("rsNum", rs != null ? rs.size():0);
		request.setAttribute("rs",rs);
		request.setAttribute("topTr",topTr);
		return new ActionForward("/zjgszy_gygl.do?method=gyTzzManage");
	}
	/**
	 * 公寓团总支信息添加
	 */
	public ActionForward gyTzzAdd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		gyglDao gyDao = new gyglDao();
		HashMap<String,String> map = new  HashMap<String,String>();
		String xh = DealString.toGBK(request.getParameter("xh"));		
		map = gyDao.getStuInfo(xh);//获取所操作学生相关信息
		String doType = request.getParameter("doType");		
		GyglZjgszyForm myform        = (GyglZjgszyForm)form;
		GyTzzModel tzzModel           = new GyTzzModel();
		if(!Base.isNull(doType)&&"save".equalsIgnoreCase(doType)){
			boolean done = false;			
			GyglZjgszyService myservice   = new GyglZjgszyService();
			BeanUtils.copyProperties(tzzModel, myform);
			done = myservice.serv_gyTzzAdd(tzzModel);
			request.setAttribute("done",done);
		}		
		request.setAttribute("xh",xh);
		request.setAttribute("rs",map);
		request.setAttribute("ldList",gyDao.getGyLdList());//公寓楼栋列表
		return mapping.findForward("tzzxxAdd");
	}
	/**
	 * 公寓团总支信息修改
	 */
	public ActionForward gyTzzModi(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		gyglDao gyDao = new gyglDao();
		GyglZjgszyService myservice   = new GyglZjgszyService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String doType = request.getParameter("doType");	
		HashMap<String,String> map = new  HashMap<String,String>();//填充修改前信息
		map = myservice.serv_getTzzXx(pkValue);
		if(!Base.isNull(doType)&&"save".equalsIgnoreCase(doType)){			
			boolean done = false;				
			GyglZjgszyForm myform        = (GyglZjgszyForm)form;
			GyTzzModel tzzModel          = new GyTzzModel();
			BeanUtils.copyProperties(tzzModel, myform);
			done = myservice.serv_gyTzzModi(tzzModel,pkValue);	
			if(done){//修改成功
				pkValue = tzzModel.getLddm()+tzzModel.getXh()+tzzModel.getZw().trim()+tzzModel.getRzrq();
				map = myservice.serv_getTzzXx(pkValue);
			}else{//修改失败			
				map.put("lddm",tzzModel.getLddm());
				map.put("zw",tzzModel.getZw());
				map.put("rzrq",tzzModel.getRzrq());
				map.put("bm", tzzModel.getBm());
				map.put("rzrq",tzzModel.getRzrq());  
				map.put("lzrq", tzzModel.getLzrq());
				map.put("lxdh",tzzModel.getLxdh());
				map.put("bz", tzzModel.getBz());
			}
			request.setAttribute("done",done);
		}
		
		request.setAttribute("rs",map);
		request.setAttribute("ldList",gyDao.getGyLdList());//公寓楼栋列表
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("tzzxxModi");
	}
	/**
	 * 公寓团总支信息删除
	 * 
	 */
	public ActionForward gyTzzDel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		GyglZjgszyService myservice   = new GyglZjgszyService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		boolean done = false;	
		done = myservice.serv_gyTzzDel(pkValue);
		request.setAttribute("done",done);
		return new ActionForward("/zjgszy_gygl.do?method=gyTzzQuery");
	}
	/**
	 * 免检寝室信息管理查询默认页面 
	 */
	public ActionForward mjqsManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		gyglDao gyDao = new gyglDao();		
		request.setAttribute("ldList",gyDao.getGyLdList());//公寓楼栋列表
 		request.setAttribute("qshList",gyDao.getQshList());//公寓寝室号列表 
 		request.setAttribute("xiaoquList", gyDao.getXiaoQuList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
 		return mapping.findForward("mjqsDefault");
	}
	/**
	 * 免检寝室信息管理查询执行
	 */
	public ActionForward mjqsQuery(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		GyglZjgszyForm myform        = (GyglZjgszyForm)form;
		MjqsModel mymodel           = new MjqsModel();
		GyglZjgszyService myservice  = new GyglZjgszyService();
		ArrayList<HashMap<String, String>> topTr = myservice.getmjqsSearchTitle();
		BeanUtils.copyProperties(mymodel, myform);
		ArrayList<String[]> rs  = myservice.serv_mjqsQuery(mymodel);		
		request.setAttribute("rsNum", rs != null ? rs.size():0);
		request.setAttribute("rs",rs);
		request.setAttribute("topTr",topTr);
		return new ActionForward("/zjgszy_gygl.do?method=mjqsManage");		
	}
	/**
	 * 免检寝室信息添加
	 */
	public ActionForward mjqsAdd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		GyglZjgszyForm myform        = (GyglZjgszyForm)form;
		myform.setXn(Base.currXn);
		myform.setXq(Base.currXq);		
		gyglDao myDao = new gyglDao();	
		String doType = request.getParameter("doType");
		GyglZjgszyService myservice   = new GyglZjgszyService();
		MjqsModel   mjsqModel         = new MjqsModel();
		if("save".equalsIgnoreCase(doType)){
			boolean done = false;
			BeanUtils.copyProperties(mjsqModel, myform);
			done = myservice.serv_mjqsAdd(mjsqModel);
			request.setAttribute("done",done);
		}
		request.setAttribute("ldList",myDao.getGyLdList());//公寓楼栋列表
 		request.setAttribute("qshList",GyglShareDAO.GetSsbhList(myform.getLddm()));//公寓寝室号列表 
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());		
		return mapping.findForward("mjqsAdd");
	}
	/**
	 * 免检寝室信息修改
	 */
	public ActionForward mjqsModi(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		gyglDao gyDao = new gyglDao();
		GyglZjgszyForm myform        = (GyglZjgszyForm)form;
		String lddm = myform.getLddm();
		GyglZjgszyService myservice   = new GyglZjgszyService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String doType = request.getParameter("doType");	
		HashMap<String,String> map = new  HashMap<String,String>();
		map = myservice.serv_getMjqsXx(pkValue); ////填充修改前记录信息

		if(!Base.isNull(doType)&&"save".equalsIgnoreCase(doType)){
			boolean done = false;	
			MjqsModel mjqsModel           = new MjqsModel();
			BeanUtils.copyProperties(mjqsModel, myform);
			done = myservice.serv_mjqsModi(mjqsModel,pkValue);			
			if(done){//修改成功
			    pkValue = mjqsModel.getXn()+mjqsModel.getXq()+mjqsModel.getSsbh();						
			    map = myservice.serv_getMjqsXx(pkValue);
			}else{//修改失败
				map.put("xn",mjqsModel.getXn());
				map.put("xq",mjqsModel.getXq());
				map.put("lddm",mjqsModel.getLddm());
				map.put("ssbh",mjqsModel.getSsbh());
				map.put("rq",mjqsModel.getRq());
				map.put("bz",mjqsModel.getBz());
			}
			request.setAttribute("done",done);
		}
		lddm = map.get("lddm");
		request.setAttribute("rs",map);
		request.setAttribute("ldList",gyDao.getGyLdList());//公寓楼栋列表
		request.setAttribute("pkValue", pkValue);
 		request.setAttribute("qshList",GyglShareDAO.GetSsbhList(lddm));//公寓寝室号列表 
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());		
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("mjqsModi");		
	}
	/**
	 * 免检寝室信息删除
	 */
	public ActionForward mjqsDel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		GyglZjgszyService myservice   = new GyglZjgszyService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		boolean done = false;	
		done = myservice.serv_mjqsDel(pkValue);
		request.setAttribute("done",done);
		return new ActionForward("/zjgszy_gygl.do?method=mjqsQuery");
	}
}
