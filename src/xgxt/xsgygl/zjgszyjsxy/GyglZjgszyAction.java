/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-10-30 ����01:57:26</p>
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
		// TODO �Զ����ɷ������
		HttpSession session = request.getSession();
		try {
			//�ж��û���дȨ	
			String writeAble = Base.getWriteAble(request);
			String dxq = request.getParameter("writeAble");
			if(!"".equalsIgnoreCase(dxq) && dxq != null){
				writeAble = dxq;
			}
			/** ���߼�� */
			int i = Base.chkTimeOut(session);
			if (i <= 2) {
				request.setAttribute("errMsg", "��½��ʱ�������µ�½��");
				return new ActionForward("/login.jsp", false);
			}
			request.setAttribute("writeAble", writeAble);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "���������Թ��ϣ�" + e.toString());
			return new ActionForward("/errMsg.do", false);
		}
		request.setAttribute("method",request.getParameter("method"));
		return super.execute(mapping, form, request, response);
	}
	/**
	 * ��Ԣ����֧�����ѯĬ��ҳ��
	 */
	public ActionForward gyTzzManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		gyglDao gyDao = new gyglDao();		
		request.setAttribute("ldList",gyDao.getGyLdList());//��Ԣ¥���б�
 		request.setAttribute("qshList",gyDao.getQshList());//��Ԣ���Һ��б� 
 		request.setAttribute("xiaoquList", gyDao.getXiaoQuList());
 		request.setAttribute("tableName","view_gytzzxx");
 		request.setAttribute("realTable","tytzzxx");
		return mapping.findForward("tzzDefault");
	}
	/**
	 * ��Ԣ����֧�����ѯִ��
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
	 * ��Ԣ����֧��Ϣ���
	 */
	public ActionForward gyTzzAdd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		gyglDao gyDao = new gyglDao();
		HashMap<String,String> map = new  HashMap<String,String>();
		String xh = DealString.toGBK(request.getParameter("xh"));		
		map = gyDao.getStuInfo(xh);//��ȡ������ѧ�������Ϣ
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
		request.setAttribute("ldList",gyDao.getGyLdList());//��Ԣ¥���б�
		return mapping.findForward("tzzxxAdd");
	}
	/**
	 * ��Ԣ����֧��Ϣ�޸�
	 */
	public ActionForward gyTzzModi(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		gyglDao gyDao = new gyglDao();
		GyglZjgszyService myservice   = new GyglZjgszyService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String doType = request.getParameter("doType");	
		HashMap<String,String> map = new  HashMap<String,String>();//����޸�ǰ��Ϣ
		map = myservice.serv_getTzzXx(pkValue);
		if(!Base.isNull(doType)&&"save".equalsIgnoreCase(doType)){			
			boolean done = false;				
			GyglZjgszyForm myform        = (GyglZjgszyForm)form;
			GyTzzModel tzzModel          = new GyTzzModel();
			BeanUtils.copyProperties(tzzModel, myform);
			done = myservice.serv_gyTzzModi(tzzModel,pkValue);	
			if(done){//�޸ĳɹ�
				pkValue = tzzModel.getLddm()+tzzModel.getXh()+tzzModel.getZw().trim()+tzzModel.getRzrq();
				map = myservice.serv_getTzzXx(pkValue);
			}else{//�޸�ʧ��			
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
		request.setAttribute("ldList",gyDao.getGyLdList());//��Ԣ¥���б�
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("tzzxxModi");
	}
	/**
	 * ��Ԣ����֧��Ϣɾ��
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
	 * ���������Ϣ�����ѯĬ��ҳ�� 
	 */
	public ActionForward mjqsManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		gyglDao gyDao = new gyglDao();		
		request.setAttribute("ldList",gyDao.getGyLdList());//��Ԣ¥���б�
 		request.setAttribute("qshList",gyDao.getQshList());//��Ԣ���Һ��б� 
 		request.setAttribute("xiaoquList", gyDao.getXiaoQuList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
 		return mapping.findForward("mjqsDefault");
	}
	/**
	 * ���������Ϣ�����ѯִ��
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
	 * ���������Ϣ���
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
		request.setAttribute("ldList",myDao.getGyLdList());//��Ԣ¥���б�
 		request.setAttribute("qshList",GyglShareDAO.GetSsbhList(myform.getLddm()));//��Ԣ���Һ��б� 
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());		
		return mapping.findForward("mjqsAdd");
	}
	/**
	 * ���������Ϣ�޸�
	 */
	public ActionForward mjqsModi(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		gyglDao gyDao = new gyglDao();
		GyglZjgszyForm myform        = (GyglZjgszyForm)form;
		String lddm = myform.getLddm();
		GyglZjgszyService myservice   = new GyglZjgszyService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String doType = request.getParameter("doType");	
		HashMap<String,String> map = new  HashMap<String,String>();
		map = myservice.serv_getMjqsXx(pkValue); ////����޸�ǰ��¼��Ϣ

		if(!Base.isNull(doType)&&"save".equalsIgnoreCase(doType)){
			boolean done = false;	
			MjqsModel mjqsModel           = new MjqsModel();
			BeanUtils.copyProperties(mjqsModel, myform);
			done = myservice.serv_mjqsModi(mjqsModel,pkValue);			
			if(done){//�޸ĳɹ�
			    pkValue = mjqsModel.getXn()+mjqsModel.getXq()+mjqsModel.getSsbh();						
			    map = myservice.serv_getMjqsXx(pkValue);
			}else{//�޸�ʧ��
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
		request.setAttribute("ldList",gyDao.getGyLdList());//��Ԣ¥���б�
		request.setAttribute("pkValue", pkValue);
 		request.setAttribute("qshList",GyglShareDAO.GetSsbhList(lddm));//��Ԣ���Һ��б� 
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());		
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("mjqsModi");		
	}
	/**
	 * ���������Ϣɾ��
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
