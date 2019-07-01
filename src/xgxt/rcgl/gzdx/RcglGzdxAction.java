package xgxt.rcgl.gzdx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.GetTime;

public class RcglGzdxAction  extends DispatchAction {
	
	//��ԴԤԼ����
	public ActionForward zyyysq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcglGzdxForm  myForm = (RcglGzdxForm) form;
		RcglGzdxService service = new RcglGzdxService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep") == null ? ""
				:session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String xxdm = Base.xxdm;
		String act = request.getParameter("act");
		String flg=request.getParameter("flg");
		flg =Base.isNull(request.getParameter("flg"))|| flg.equalsIgnoreCase("") ? "cd": flg;
		if("stu".equals(userType)){
			request.setAttribute("view", "no");
		}else{
			if(!"admin".equals(userType)){
				myForm.setBm(userDep);
			}
			if("save".equals(myForm.getDoType())){
				myForm.setSqsj(GetTime.getNowTime2());
				if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){//���ݴ�ѧ
					if("cd".equalsIgnoreCase(flg) && service.checkCdkj(myForm)){
						request.setAttribute("result", service.saveZyyysq_ser(myForm,userType,userName));	
					}else if(flg.equalsIgnoreCase("sb") && service.checkSbkj(myForm)){
						request.setAttribute("result", service.saveZyyysq_ser(myForm,userType,userName));	
					}else {
						request.setAttribute("bnjc","true" );//�жϳ��غ��豸�Ƿ��ܱ���
					}
				}else{
					request.setAttribute("result", service.saveZyyysq_ser(myForm,userType,userName));	
				}
			}
			if(null != flg && "sb".equals(flg)){
				request.setAttribute("sbList", service.getZyyysb_ser());
			}else{
				request.setAttribute("cdList", service.getZyyycd_ser());
			}
			request.setAttribute("act", act);
			request.setAttribute("yysb", myForm.getYysb()==null?"":myForm.getYysb());
			request.setAttribute("bmList", service.getXxbm_ser());
			request.setAttribute("hourList", service.getHours_ser());
			request.setAttribute("minList", service.getMinutes_ser());
		}
		request.setAttribute("flg", flg);
		return mapping.findForward("zyyysq");
	}
	
	//��ԴԤԼ���
	public ActionForward zyyysh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcglGzdxForm  myForm = (RcglGzdxForm) form;
		RcglGzdxService service = new RcglGzdxService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
		String pkvals = request.getParameter("pkVStr");
		String flag = request.getParameter("flag");
		String shpf = myForm.getShpf();
		String xxdm=Base.xxdm;
		if("admin".equals(userType)){
			if("sqsh".equals(myForm.getDoType())){
				if(Globals.XXDM_GZDX.equals(xxdm)){//���ݴ�ѧ������������ֶ� 2010.9.20 qlj
					request.setAttribute("result", service.zyyySh_gzdx(pkvals, flag,shpf));	
				}else{
					request.setAttribute("result", service.zyyySh_ser(pkvals, flag));			
				}
			}
			if("query".equals(myForm.getDoType())){
				request.setAttribute("topTr", service.getTableTop("yysh"));
				request.setAttribute("rs", service.zyyyShQuery_ser(myForm));			
			}
			request.setAttribute("bmList", service.getXxbm_ser());
			request.setAttribute("cdList", service.getZyyycd_ser());
			request.setAttribute("sbList", service.getZyyysb_ser());
		}else{
			request.setAttribute("view", "no");
		}
		return mapping.findForward("zyyysh");
	}
	
	//��ԴԤԼ�鿴���޸�
	public ActionForward viewYyxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcglGzdxForm  myForm = (RcglGzdxForm) form;
		RcglGzdxService service = new RcglGzdxService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep") == null ? ""
				:session.getAttribute("userDep").toString();
		String pkval = request.getParameter("pkVStr");
		String act = request.getParameter("act");
		if("admin".equals(userType)){
			request.setAttribute("yhqx", "yes");
		}else{
			myForm.setBm(userDep);
		}
		if("update".equals(act)){
			request.setAttribute("act", act);	
		}
		if("update".equals(myForm.getDoType())){
			request.setAttribute("result", service.updateOneYyxx_ser(pkval, myForm));	
		}
		HashMap<String , String> map = service.getOneYyxx_ser(pkval);
		if(null != map.get("yysb")){
			request.setAttribute("sbList", service.getZyyysb_ser());
		}
		if(null != map.get("cddm")){
			request.setAttribute("cdList", service.getZyyycd_ser());
		}
		request.setAttribute("pkval", pkval);
		request.setAttribute("rs", map);
		request.setAttribute("bmList", service.getXxbm_ser());
		request.setAttribute("hourList", service.getHours_ser());
		request.setAttribute("minList", service.getMinutes_ser());
		return mapping.findForward("zyyyupdate");
	}
	
	//��ԴԤԼ�����ѯ
	public ActionForward zyyyshcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcglGzdxForm  myForm = (RcglGzdxForm) form;
		RcglGzdxService service = new RcglGzdxService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep") == null ? ""
				:session.getAttribute("userDep").toString();
		String pkvals = request.getParameter("pkVStr");
		List<HashMap<String ,String >> rs = null;
		if(!"admin".equals(userType)){
			myForm.setBm(userDep);
		}
		if("delete".equals(myForm.getDoType())){
			request.setAttribute("result", service.deleteYyxx_ser(pkvals));
			myForm.setDoType("query");
		}
		if("query".equals(myForm.getDoType())){
			request.setAttribute("topTr", service.getTableTop("yysh"));
			rs = service.zyyyShQuery_ser(myForm);
			request.setAttribute("rs", rs);			
		}
		request.setAttribute("realTable", "zyyysqb");
		request.setAttribute("tableName", "zyyysqb");
		request.setAttribute("bmList", service.getXxbm_ser());
		request.setAttribute("cdList", service.getZyyycd_ser());
		return mapping.findForward("zyyyshcx");
	}
	//ֵ����Ա����
	public ActionForward zbrygl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcglGzdxForm  myForm = (RcglGzdxForm) form;
		RcglGzdxService service = new RcglGzdxService();
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String pkvals = request.getParameter("pkVStr");
		String pk = request.getParameter("pk");
		String doType2 = request.getParameter("doType2");
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName") == null ? ""
				:session.getAttribute("userName").toString();
		String doType = request.getAttribute("doType") == null ? ""
				:request.getAttribute("doType").toString();
		if ("stu".equals(userType)) {
			String isgly = service.isXsgly_ser(userName);
			if ("no".equals(isgly)) {
				request.setAttribute("view", "no");
				return mapping.findForward("zbrygl");
			} else if ("wqy".equals(isgly)) {
				request.setAttribute("view", "wqy");
				return mapping.findForward("zbrygl");
			}

		}
		if("delete".equals(myForm.getDoType())){
			request.setAttribute("result", service.deleteZbry_ser(pkvals));
			myForm.setDoType("query");
		}
		if("query".equals(myForm.getDoType())){
			request.setAttribute("topTr", service.getTableTop("zbry"));
			List<HashMap<String,String>> list = service.queryZbry_ser(myForm);
			request.setAttribute("rs", list);
			request.setAttribute("rsNum", list.size());
		}
		if((!Base.isNull(doType)&&"zbsz".equals(myForm.getDoType()))||"zbsz".equals(doType2)){
			request.setAttribute("doType2", "zbsz");
		}
		request.setAttribute("pk", pk);	
		request.setAttribute("realTable", "zbryk");
		request.setAttribute("tableName", "zbryk");
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("zyList", dao.getZyList(myForm.getXydm()));
		request.setAttribute("bjList", dao.getBjList(myForm.getXydm(), myForm.getZydm()));
		return mapping.findForward("zbrygl");
	}
	
	//���ӻ��޸�ֵ����Ա��Ϣ
	public ActionForward operateZbryxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcglGzdxForm  myForm = (RcglGzdxForm) form;
		RcglGzdxService service = new RcglGzdxService();
		HashMap<String,String> map = new HashMap<String,String>();
		String act = request.getParameter("act");
		String pkvals = request.getParameter("pkVStr");
		if("update".equals(myForm.getDoType())){
			request.setAttribute("result", service.updateOneZbryxx_ser(myForm));
		}
		if("add".equals(myForm.getDoType())){
			request.setAttribute("result", service.addOneZbryxx_ser(myForm));
			act = "update";
			pkvals = myForm.getXh();
		}
		if("add".equals(act)){
			map = service.getOneXsxx_ser(pkvals);
		}
		if("update".equals(act)){
			map = service.getOneZbryxx_ser(pkvals);
		}
		request.setAttribute("pkvals", pkvals);
		request.setAttribute("act", act);
		request.setAttribute("rs", map);
		return mapping.findForward("operationzbryxx");
	}
	
	//ֵ������
	public ActionForward zbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcglGzdxForm  myForm = (RcglGzdxForm) form;
		RcglGzdxService service = new RcglGzdxService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName") == null ? ""
				:session.getAttribute("userName").toString();
		String pkvals = request.getParameter("pkVStr");
		String pk = request.getParameter("pk");
		if("stu".equals(userType)){
			String isgly = service.isXsgly_ser(userName);
			if("no".equals(isgly)){
				request.setAttribute("view", "no");
				return mapping.findForward("zbsz");
			}else if("wqy".equals(isgly)){
				request.setAttribute("view", "wqy");
				return mapping.findForward("zbsz");
			}
		}
		if("zbsz".equals(myForm.getDoType())){
			request.setAttribute("doType", myForm.getDoType());
			return zbrygl(mapping, form, request, response);
		}
		if("save".equals(myForm.getDoType())){
			request.setAttribute("result", service.zbrysz_ser(pkvals, pk));
			myForm.setDoType("query");
		}
		if("query".equals(myForm.getDoType())){
			request.setAttribute("topTr", service.getTableTop("zbsz"));
			request.setAttribute("rs", service.queryZbrysz_ser(myForm));			
		}
		request.setAttribute("bmList", service.getXxbm_ser());
		request.setAttribute("cdList", service.getZyyycd_ser());
		return mapping.findForward("zbsz");
	}
	
	//ѧ������Ա����
	public ActionForward xsgly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcglGzdxForm  myForm = (RcglGzdxForm) form;
		RcglGzdxService service = new RcglGzdxService();
		DAO dao = DAO.getInstance();
		String pkvals = request.getParameter("pkVStr");
		if("delete".equals(myForm.getDoType())){
			request.setAttribute("result", service.deleteXsgly_ser(pkvals));
			myForm.setDoType("query");
		}
		if("query".equals(myForm.getDoType())){
			request.setAttribute("topTr", service.getTableTop("xsgly"));
			List<HashMap<String,String>> list = service.queryXsgly_ser(myForm);
			request.setAttribute("rs", list);
			request.setAttribute("rsNum", list.size());
		}
		request.setAttribute("realTable", "xsglyb");
		request.setAttribute("tableName", "xsglyb");
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("zyList", dao.getZyList(myForm.getXydm()));
		request.setAttribute("bjList", dao.getBjList(myForm.getXydm(), myForm.getZydm()));
		return mapping.findForward("xsgly");
	}
	
	//���ӻ��޸�ѧ������Ա��Ա��Ϣ
	public ActionForward operateXsglyxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcglGzdxForm  myForm = (RcglGzdxForm) form;
		RcglGzdxService service = new RcglGzdxService();
		HashMap<String,String> map = new HashMap<String,String>();
		String act = request.getParameter("act");
		String pkvals = request.getParameter("pkVStr");
		if("update".equals(myForm.getDoType())){
			request.setAttribute("result", service.updateOneXsglyxx_ser(myForm));
		}
		if("add".equals(myForm.getDoType())){
			request.setAttribute("result", service.addOneXsglyxx_ser(myForm));
			act = "update";
			pkvals = myForm.getXh();
		}
		if("add".equals(act)){
			map = service.getOneXsxx_ser(pkvals);
		}
		if("update".equals(act)){
			map = service.getOneXsglyxx_ser(pkvals);
		}
		request.setAttribute("pkvals", pkvals);
		request.setAttribute("act", act);
		request.setAttribute("rs", map);
		return mapping.findForward("operationxsgly");
	}
	
	/** 
	 * ֪ͨ������ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward viewtzgg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RcglGzdxService service = new RcglGzdxService();
		request.setAttribute("info",service.getHkxz_ser("tzgg"));
		return mapping.findForward("viewtzgg");
	}
}
