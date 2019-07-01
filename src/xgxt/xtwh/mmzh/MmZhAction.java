/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-27 ����04:07:14 
 */  
package xgxt.xtwh.mmzh;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.IPRequest;
import common.newp.StringUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.DAO.DAO;
import xgxt.base.Encrypt;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xgxt.utils.util.Tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-7-27 ����04:07:14 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class MmZhAction extends SuperAction<MmZhForm,MmZhService > {
	MmZhService service = new MmZhService();
	DAO dao = DAO.getInstance();
	
	public ActionForward checkYh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		HttpSession session = request.getSession();
		session.setAttribute("mmbh", "false");
		String type = request.getParameter("type");
		if(StringUtil.isNull(type)){
			//return mapping.findForward("");
			return new ActionForward("/login.jsp", false);
		}else if(type.equals("check")){
			MmZhForm mmzhform = (MmZhForm)form;
			String message = null;
			String role = "student";
			String yhm = mmzhform.getYhm();
			if(!service.checkXsmmbExits(yhm) && !service.checkYhbExits(yhm)){
				message = MessageUtil.getText(MessageKey.XTWH_MMZH_WYHM);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}else if(!service.checkMbwtExits(yhm)){
				message = MessageUtil.getText(MessageKey.XTWH_MMZH_WMMWT);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}else{
				message = MessageUtil.getText(MessageKey.XTWH_MMZH_ZHYZ);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
		}
		return mapping.findForward("viewcheck");
	}

	/**
	 * ѡ���һط�ʽ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhfs(ActionMapping mapping, ActionForm form,
							   HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String type = request.getParameter("type");
		MmZhForm mmzhform = (MmZhForm)form;
		if("check".equals(type)){
			//��ȡ�绰���������˺�
			String zhfszh = service.getZhfs(mmzhform.getYhm(),request.getParameter("zhfs"));
			if(StringUtils.isNull(zhfszh)){
				response.getWriter().print(getJsonMessageResult("��֤ʧ�ܣ�",false));
			} else {
				session.setAttribute("zhfszh",zhfszh);
				response.getWriter().print(getJsonMessageResult("��֤�ɹ���",true));
			}
			return null;
		}
		List<HashMap<String,String>> list = service.getzhfs();
		request.setAttribute("yhm",mmzhform.getYhm());
		request.setAttribute("list",list);
		return mapping.findForward("viewzhfs");
	}

	/**
	 * ��֤��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yzxx(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String type = request.getParameter("type");
		MmZhForm mmzhform = (MmZhForm)form;
		if("check".equals(type)){
			//�ж�������˺������ݿ��Ƿ�һ��
			if(mmzhform.getZhfszh().equals(session.getAttribute("zhfszh"))){
				//������֤����û�
				String yzm = String.valueOf(Tool.getRandNum());
				boolean flag = service.sendMessage(yzm,mmzhform.getZhfs(),mmzhform.getZhfs());
				if(flag){
					session.setAttribute("yzm",yzm);
					session.setAttribute("yzmTime",new Date());
					response.getWriter().print(getJsonMessageResult("���ͳɹ���",true));
				} else {
					response.getWriter().print(getJsonMessageResult("����ʧ�ܣ�",false));
				}
			} else {
				response.getWriter().print(getJsonMessageResult("�����˺���Ԥ���˺Ų�һ�£����������룡",false));
			}
			return null;
		}

		if("checkYzm".equals(type)){
			if(session.getAttribute("yzm") != null){
				//��֤���Ƿ�ʧЧ
				if(DateUtils.getDiffMin((Date)session.getAttribute("yzmTime"),new Date())<15){
					if(session.getAttribute("yzm").equals(mmzhform.getCaptcha())){
						response.getWriter().print(getJsonMessageResult("��֤�ɹ���",true));
					} else {
						response.getWriter().print(getJsonMessageResult("��֤�벻��ȷ��",false));
					}
				} else {
					response.getWriter().print(getJsonMessageResult("��֤��ʧЧ�������»�ȡ��",false));
				}
			} else {
				response.getWriter().print(getJsonMessageResult("��֤�벻��ȷ��",false));
			}
			return null;
		}

		request.setAttribute("zhfs",request.getParameter("zhfs"));
		request.setAttribute("yhm",mmzhform.getYhm());
		return mapping.findForward("viewyzxx");
	}

	/**
	 * �޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xgmm(ActionMapping mapping, ActionForm form,
								   HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MmZhForm mmzhform = (MmZhForm)form;
		String type = request.getParameter("type");
		if("update".equals(type)){
			Encrypt ept = new Encrypt();
			String yhm = mmzhform.getYhm();
			String newmm = ept.encrypt(mmzhform.getNewmm());
			boolean result = true;
			String message = null;
			String sql = "";
			if(service.checkXsmmbExits(yhm)){
				sql = "update xsmmb set mm=? where xh=? ";
				result = dao.runUpdate(sql,new String[]{newmm,yhm});
			}else if(service.checkYhbExits(yhm)){
				sql = "update yhb set kl=? where yhm=? ";
				result = dao.runUpdate(sql,new String[]{newmm,yhm});
			}else{
				message = MessageUtil.getText(MessageKey.XTWH_MMZH_WYHM);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			if(result){
				message = MessageUtil.getText(MessageKey.XTWH_MMZH_MMGXCG);
			}else{
				message = MessageUtil.getText(MessageKey.XTWH_MMZH_MMGXSB);
			}
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		request.setAttribute("yhm", mmzhform.getYhm());
		return mapping.findForward("viewgxmm");
	}


	public ActionForward checkMmbh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		HttpSession session = request.getSession();
		String type = request.getParameter("type");
		MmZhForm mmzhform = (MmZhForm)form;
		if(type.equals("check")){
			String message = null;
			String yhm = mmzhform.getYhm();
			String wtda = mmzhform.getWtda();
			String wtid = mmzhform.getWtid();
			String yzm = mmzhform.getYzm();
			String sYzm = (String) session.getAttribute("yzm");
			String host = IPRequest.getIpAddress(request);
			if(StringUtil.isNull(yzm) || StringUtil.isNull(sYzm) || StringUtil.isNull(host)||
					(!yzm.equalsIgnoreCase(sYzm))){
				session.setAttribute("mmbh", "false");
				session.setAttribute("yzm", "");
				response.getWriter().print(getJsonMessage("��֤�����"));
				return null;
			}
			if(!StringUtil.isNull(service.getmbjl(yhm).get("wtda")) 
					&& (wtda.trim()).equals(service.getmbjl(yhm).get("wtda").trim())){
				session.setAttribute("mmbh", "true");
				message = MessageUtil.getText(MessageKey.XTWH_MMZH_MMWTYZ);;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}else{
				session.setAttribute("mmbh", "false");
				message = MessageUtil.getText(MessageKey.XTWH_MMZH_MMWTYZSB);;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		session.setAttribute("yhm", mmzhform.getYhm());
		request.setAttribute("mbList", service.getMbList());
		request.setAttribute("yhm", mmzhform.getYhm());
		request.setAttribute("wtid",service.getMbmap(mmzhform.getYhm()).get("wtid"));
		request.setAttribute("mbwt",service.getMbmap(mmzhform.getYhm()).get("mbwt"));
		return mapping.findForward("viewmmbh");
	}
	
	public ActionForward Updatemm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		HttpSession session = request.getSession();
		String type = request.getParameter("type");
		MmZhForm mmzhform = (MmZhForm)form;
		if(!session.getAttribute("yhm").equals( mmzhform.getYhm())||!"true".equals(session.getAttribute("mmbh"))){
			request.setAttribute("yhm", mmzhform.getYhm());
			session.setAttribute("yhm", mmzhform.getYhm());
			session.setAttribute("mmbh", "false");
			request.setAttribute("mbList", service.getMbList());
			request.setAttribute("yhm", mmzhform.getYhm());
			request.setAttribute("wtid",service.getMbmap(mmzhform.getYhm()).get("wtid"));
			request.setAttribute("mbwt",service.getMbmap(mmzhform.getYhm()).get("mbwt"));
			return mapping.findForward("viewcheck");
		}
		if(type.equals("update")){
			Encrypt ept = new Encrypt();
			String yhm = mmzhform.getYhm();
			String newmm = ept.encrypt(mmzhform.getNewmm());
			boolean result = true;
			String message = null;
			String sql = "";
			if(service.checkXsmmbExits(yhm)){
				sql = "update xsmmb set mm=? where xh=? ";
				result = dao.runUpdate(sql,new String[]{newmm,yhm});
			}else if(service.checkYhbExits(yhm)){
				sql = "update yhb set kl=? where yhm=? ";
				result = dao.runUpdate(sql,new String[]{newmm,yhm});
			}else{
				message = MessageUtil.getText(MessageKey.XTWH_MMZH_WYHM);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			if(result){
				message = MessageUtil.getText(MessageKey.XTWH_MMZH_MMGXCG);
			}else{
				message = MessageUtil.getText(MessageKey.XTWH_MMZH_MMGXSB);
			}
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		request.setAttribute("yhm", mmzhform.getYhm());
		return mapping.findForward("viewgxmm");
	}
	
	public ActionForward MbSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String type = request.getParameter("type");
		String yhm = getUser(request).getUserName();
		MmZhForm mmzhform = (MmZhForm)form;
		request.setAttribute("mbList", service.getMbList());
		if(type.equals("sz")){
			mmzhform.setYhm(yhm);
			boolean result = true;
			String message = null;
		    if(service.checkMbwtExits(yhm)){
		    	result = service.runUpdate(mmzhform);
		    }else{
		    	result = service.runInsert(mmzhform);
		    }
		    
			if(result){
				message = MessageUtil.getText(MessageKey.XTWH_MMZH_MBSZCG);
			}else{
				message = MessageUtil.getText(MessageKey.XTWH_MMZH_MBSZCG);
			}
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		request.setAttribute("wtid", service.getmbjl(yhm).get("wtid"));
		request.setAttribute("wtda", service.getmbjl(yhm).get("wtda"));
		return mapping.findForward("viewmbsz");
	}

	/**
	 * �����һط�ʽ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mmzhfs(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String type = request.getParameter("type");
		if("bc".equals(type)){
			MmZhForm mmZhForm = (MmZhForm)form;
			boolean flag = service.updateZhfs(mmZhForm);
			String message = null;
			if(flag){
				message = MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
			}else{
				message = MessageUtil.getText(MessageKey.SYS_SAVE_FAIL);
			}
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		List<HashMap<String,String>> l = service.getzt();
		for(HashMap<String,String> hm : l){
				request.setAttribute(hm.get("zhfs"),hm.get("qyzt"));
		}
		return mapping.findForward("viewmmzhfs");
	}
}
