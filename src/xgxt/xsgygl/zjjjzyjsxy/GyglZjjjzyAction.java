/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-12-5 ����11:14:19</p>
 */
package xgxt.xsgygl.zjjjzyjsxy;

import java.lang.reflect.InvocationTargetException;

import java.sql.SQLException;
import java.util.ArrayList;
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

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.xsgygl.dao.GyglShareDAO;

public class GyglZjjjzyAction extends DispatchAction {
	/**¥����Ϣ��ѯ*/
	public ActionForward lzManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{	
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		if (userType.equalsIgnoreCase("stu")) {// ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ���û���Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		String userOnline   = session.getAttribute("userOnLine").toString();
		String userName     = session.getAttribute("userName").toString();
		
		GyglZjjjzyForm lzform      = (GyglZjjjzyForm)form; 
		GyglZjjjzyService service  = new GyglZjjjzyService();
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			LzModel  model             = new LzModel();
			BeanUtils.copyProperties(model,lzform);
			ArrayList<HashMap<String, String>> topTr = service.getLzMTitle();
			ArrayList<String[]> rs    = service.getLzMResult(model);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
		}
		lzform.setSfzz(DealString.toGBK(lzform.getSfzz()));
		lzform.setLz(DealString.toGBK(lzform.getLz()));
		lzform.setXm(DealString.toGBK(lzform.getXm()));
		request.setAttribute("tableName","view_lzxx");
		//��дȨ�ж�		 			
		request.setAttribute("writeAble", (Base.chkUPower(userName,"lzManage.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		request.setAttribute("ldList",GyglShareDAO.getLdList());		
		return mapping.findForward("lzM");		
	}
	/**¥������*/
	public ActionForward lzAdd (ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String doType = request.getParameter("doType");
		String xh     = DealString.toGBK(request.getParameter("lz"));		
		HashMap<String,String> map = new HashMap<String,String>();
		GyglZjjjzyForm lzform      = (GyglZjjjzyForm)form; 
		GyglZjjjzyService service  = new GyglZjjjzyService();
		LzModel  model             = new LzModel();
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model,lzform);
			boolean done = service.serv_lzSave(model); 	
			request.setAttribute("done",done);
		}
		map = service.serv_getLzInfo(xh);		
		if(!Base.isNull(map.get("lddm"))){
			lzform.setLddm(map.get("lddm"));
		}
		request.setAttribute("rs",map);
		request.setAttribute("ldList",GyglShareDAO.getLdList());		
		return mapping.findForward("lzAdd");
	}
	/**¥���޸�*/
	public ActionForward lzModi(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String doType = request.getParameter("doType");
		HashMap<String,String> map = new HashMap<String,String>();
		GyglZjjjzyForm lzform      = (GyglZjjjzyForm)form; 
		GyglZjjjzyService service  = new GyglZjjjzyService();
		String pkValue            = DealString.toGBK(request.getParameter("pkValue"));
		String isview             = DealString.toGBK(request.getParameter("view"));
		LzModel  model             = new LzModel();
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model,lzform);           			
			boolean done = service.serv_lzModi(model,pkValue); 
			request.setAttribute("done", done);			
		}		
		map = service.serv_lzXx(pkValue);		
		request.setAttribute("rs",map);
		request.setAttribute("ldList",GyglShareDAO.getLdList());
		request.setAttribute("isview", isview);
		request.setAttribute("pkValue",pkValue);
		return mapping.findForward("lzModi");
	}
	/**¥��ɾ��
	 * @throws Exception */
	public ActionForward lzDel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pkValue     = DealString.toGBK(request.getParameter("pkValue"));
		GyglZjjjzyService service  = new GyglZjjjzyService();		
		boolean done = service.serv_lzDel(pkValue);
		request.setAttribute("done",done);
		return new ActionForward("/zjjjzy_Gygl.do?method=lzManage",false);

	}
	/**�㳤��Ϣ��ѯ */
	public ActionForward czManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		if (userType.equalsIgnoreCase("stu")) {// ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ���û���Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		String userOnline   = session.getAttribute("userOnLine").toString();
		String userName     = session.getAttribute("userName").toString();
		GyglZjjjzyForm czform      = (GyglZjjjzyForm)form; 
		GyglZjjjzyService service  = new GyglZjjjzyService();
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			CzModel  model             = new CzModel();
			BeanUtils.copyProperties(model,czform);
			ArrayList<HashMap<String, String>> topTr = service.getCzMTitle();
			ArrayList<String[]> rs    = service.getCzMResult(model);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
		}
		czform.setSfzz(DealString.toGBK(czform.getSfzz()));
		czform.setCz(DealString.toGBK(czform.getCz()));
		czform.setXm(DealString.toGBK(czform.getXm()));
		request.setAttribute("tableName","view_czxx");
		//��дȨ�ж�		 			
		request.setAttribute("writeAble", (Base.chkUPower(userName,"czManage.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		request.setAttribute("ldList",GyglShareDAO.getLdList());		

		return mapping.findForward("czM");
	}
	/**�㳤����*/
	public ActionForward czAdd (ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String doType = request.getParameter("doType");
		String xh     = DealString.toGBK(request.getParameter("cz"));
		String lddm   = DealString.toGBK(request.getParameter("lddm"));
		HashMap<String,String> map = new HashMap<String,String>();
		GyglZjjjzyForm lzform      = (GyglZjjjzyForm)form; 
		GyglZjjjzyService service  = new GyglZjjjzyService();
		CzModel  model             = new CzModel();
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model,lzform);
			boolean done = service.serv_czSave(model); 	
			request.setAttribute("done",done);
		}
		map = service.serv_getCzInfo(xh);
		if(map.size()>0){
			lddm=map.get("lddm");
			lzform.setLddm(lddm);
			lzform.setLc(map.get("cs"));
		}
		request.setAttribute("rs",map);
		request.setAttribute("ldList",GyglShareDAO.getLdList());
		request.setAttribute("lcList",GyglShareDAO.getLcList2(lddm));
		return mapping.findForward("czAdd");
	}
	/**�㳤�޸�*/
	public ActionForward czModi(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String doType = request.getParameter("doType");
		HashMap<String,String> map = new HashMap<String,String>();
		GyglZjjjzyForm lzform      = (GyglZjjjzyForm)form; 
		GyglZjjjzyService service  = new GyglZjjjzyService();
		String pkValue             = DealString.toGBK(request.getParameter("pkValue"));
		String isview              = DealString.toGBK(request.getParameter("view"));
		CzModel  model             = new CzModel();
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model,lzform);
			boolean done = service.serv_czModi(model,pkValue); 
			request.setAttribute("done", done);
		}
		map = service.serv_czXx(pkValue);		
		request.setAttribute("rs",map);
		request.setAttribute("ldList",GyglShareDAO.getLdList());
		request.setAttribute("isview", isview);
		request.setAttribute("pkValue",pkValue);
		return mapping.findForward("czModi");
	}
	/**�㳤ɾ��
	 * @throws Exception */
	public ActionForward czDel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pkValue     = DealString.toGBK(request.getParameter("pkValue"));
		GyglZjjjzyService service  = new GyglZjjjzyService();		
		boolean done = service.serv_czDel(pkValue);
		request.setAttribute("done",done);
		return new ActionForward("/zjjjzy_Gygl.do?method=czManage",false);

	}
	/**ÿ�ܼ� */
	public ActionForward aqjbManage(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String doType = request.getParameter("doType");
		GyglZjjjzyService service  = new GyglZjjjzyService();
		String title    = DealString.toGBK(request.getParameter("title"));
		String content  = DealString.toGBK(request.getParameter("content1"));
		String userName = DealString.toGBK(request.getSession().getAttribute("userName").toString()); 
		String isModi = request.getParameter("isModi");
		String documentId = request.getParameter("documentId");
		if("save".equalsIgnoreCase(doType)){
	    	service.serv_aqjbSave(title,content,userName,isModi,documentId);
		}
		if(!Base.isNull(documentId)){
			String documentTit  = service.serv_getJbTitle(documentId);
			String documentcont = service.serv_getJbContent(documentId);
			request.setAttribute("documentcont", documentcont);
			request.setAttribute("documenttit", documentTit);
			request.setAttribute("isModi", "yes");
			request.setAttribute("documentId", documentId);
		}else{
			request.setAttribute("documentcont", "");
			request.setAttribute("documenttit", "");
			request.setAttribute("isModi", "no");
			request.setAttribute("documentId", "");
		}
			
		List rs = service.serv_getJbMResult(userName);
		request.setAttribute("writeAble", "1");
		request.setAttribute("rs",rs);
		return mapping.findForward("aqjbM");
	}
	/**����ʾ
	 * @throws SQLException */
	public ActionForward aqjbShow(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws SQLException {
		GyglZjjjzyService service  = new GyglZjjjzyService();
		String documentId = request.getParameter("documentId");
	    String [] jbContent = service.serv_aqjbShow(documentId);
		request.setAttribute("jbtitle", jbContent[0]);//����
		request.setAttribute("jbpubtime", jbContent[1]);//����ʱ��
		request.setAttribute("jbpubername", jbContent[2]);//������
		request.setAttribute("jbcontent", jbContent[3]);//��������
		return mapping.findForward("aqjbShow");
	}
	
	/**��ɾ��
	 * @throws Exception */
	public ActionForward aqjbDel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglZjjjzyService service  = new GyglZjjjzyService();
		String documentId = request.getParameter("documentId");
		service.serv_aqjbDel(documentId);
		return new ActionForward("/zjjjzy_Gygl.do?method=aqjbManage&documentId=",false);
	}
	
    /**����˲�ѯ*/
	public ActionForward aqjbShManage(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		String userOnline   = session.getAttribute("userOnLine").toString();
		String userName     = session.getAttribute("userName").toString();
	
		GyglZjjjzyService service  = new GyglZjjjzyService();
		GyglZjjjzyForm aqjbForm    = (GyglZjjjzyForm)form; 
		String yesNo               = DealString.toGBK(aqjbForm.getYesNo());
		String rzrq                = aqjbForm.getRzrq();
		String lzrq                = aqjbForm.getLzrq();
		String title               = aqjbForm.getTitle();
		aqjbForm.setYesNo(yesNo);
		aqjbForm.setTitle(title);
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			ArrayList<HashMap<String, String>> topTr = service.getMzjbTitle();
			ArrayList<String[]> rs    = service.getMzjbShResult(yesNo, rzrq, lzrq,title);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
		}
		//��дȨ�ж�		 			
		request.setAttribute("writeAble", (Base.chkUPower(userName,"aqjbShManage.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		return mapping.findForward("aqjbShM");
	}
	/**�����
	 * @throws Exception */
	public ActionForward aqjbSh(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String documentId = request.getParameter("documentId");
		String shType     = request.getParameter("shType");
		GyglZjjjzyService service  = new GyglZjjjzyService();
		boolean done = service.serv_aqjbSh(documentId, shType);
	    request.setAttribute("done",done);
		return new ActionForward("/zjjjzy_Gygl.do?method=aqjbShManage",false);
	}
}
