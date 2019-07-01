package xgxt.wjcf.nblg;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;
import xgxt.wjcf.zjcm.WjcfZjcmService;

public class WjcfNbzyAction extends CommonAction {

	private WjcfNblgService service = WjcfNblgService.getInstance();

	private String xydm = "";;
	private String zydm = "";
	private String nj = "";
	/**
	 * ���ֱ��ӡ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		String pk = request.getParameter("pk");
		String type = request.getParameter("typ");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.printInfo(xh);
		}
		String pkValue = request.getParameter("pkValue");
		
		if (!StringUtils.isNull(pkValue)) {
			if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(Base.xxdm)) {
				WjcfZjcmService service = new WjcfZjcmService();
				rs = service.queryCfbPrintxx(pkValue);
			} else {
				rs = service.printwjInfo(pkValue);
			}
			
		}
		if (!StringUtils.isNull(pk)) {
			rs = service.printWjcf(pk);
		}
		
		if(rs.get("jg")!=null){
	    	rs.put("jg", new CommService().getSydmc(rs.get("jg"), "/", "/"));
	    }
		
		request.setAttribute("rs", rs);
		if ("1".equalsIgnoreCase(type)) {
			return mapping.findForward("cfprint2");
		}
		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(Base.xxdm)) {
			return mapping.findForward("zjcmcfbprint");
		}
		return mapping.findForward("cfprint");
	}

	/**
	 * �������ֱ����ӡ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		HashMap<String, String> rs = new HashMap<String, String>();
		String pk = request.getParameter("pk");
		String pks = request.getParameter("pks");
		if (!StringUtils.isNull(xh)) {
			rs = service.printInfo(xh);
		}
		String pkValue = request.getParameter("pkValue");
		if (!StringUtils.isNull(pkValue)) {
			rs = service.printwjInfo1(pkValue);
		}
		if (!StringUtils.isNull(pk)) {
			rs = service.printJcjkq(DealString.toGBK(pk));
		}
		if (!StringUtils.isNull(pks)) {
			rs = service.printwjInfo2(pks);
		} 
			
		request.setAttribute("rs", rs);
		return mapping.findForward("cxprint");
	}

	/**
	 * ίԱ�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wyhjdWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfNblgActionForm nblgForm = (WjcfNblgActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				: session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep") == null ? "" : session
				.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep; 
		} else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		String act = request.getParameter("act");
		List<HashMap<String, String>> titList = new ArrayList<HashMap<String,String>>();
		List<String[]> rsList = new ArrayList<String[]>();
		if (!StringUtils.isNull(act) && "query".equalsIgnoreCase(act)) {
			WjcfNblgModel model = new WjcfNblgModel();
			BeanUtils.copyProperties(model, nblgForm);
			titList = service.titie();
			rsList = service.result(model);
		}
		appendQryResult(request, titList, rsList);
		nblgForm.setXm(DealString.toGBK(nblgForm.getXm()));
		appendTableXx(request, "view_wjcf_xsssxx", "wjcf_xsssb");
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("wyhjdpage");
	}
	
	/**
	 * ���־����޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfjdUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfNblgActionForm nblgForm = (WjcfNblgActionForm) form;
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		String act = request.getParameter("act");
		
		if (!StringUtils.isNull(act) && "save".equalsIgnoreCase(act)) {
			WjcfNblgModel model = new WjcfNblgModel();
			BeanUtils.copyProperties(model, nblgForm);
			boolean bFlag = service.updateJd(model, pkValue, request);
			if (bFlag) {
				request.setAttribute("done", "yes");
			} else {
				request.setAttribute("done", "no");
			}
			nblgForm.setJd("");
			nblgForm.setJdly("");
			nblgForm.setJdsj("");
			nblgForm.setJdwh("");
			nblgForm.setGgcflbdm("");
		}
		if (!StringUtils.isNull(pkValue) ) {
			rs = service.getJdInfo(pkValue);
			nblgForm.setJd(rs.get("jd"));
			nblgForm.setJdly(rs.get("jdly"));
			nblgForm.setJdsj(rs.get("jdsj"));
			nblgForm.setJdwh(rs.get("jdwh"));
			nblgForm.setGgcflbdm(rs.get("ggcflbdm"));
		} 
		List<HashMap<String, String>> jdList = service.getJdlist();
		List rswj = service.getFileList(rs.get("xh") + rs.get("cfwh") + rs.get("cfsj"));
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("jdList", jdList);
		appendCflbCfyy(request);
		request.setAttribute("rswj", rswj);
		return mapping.findForward("cfjdpage");
	}
	
	
	/**
	 * ���ݴ�ѧѧ��Υ�ʹ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzdxCfPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		
		HashMap<String, String> rs = new HashMap<String, String>();
		
		if (!StringUtils.isNull(pkValue)) {
			
			rs = service.printwjInfo(pkValue);
		}
		
		request.setAttribute("rs", rs);
		return mapping.findForward("gzdxCfPrint");
	}
	
	
	/**
	 * ���ݴ�ѧѧ��Υ�ʹ��ֽ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzdxCfjcPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		
		HashMap<String, String> rs = new HashMap<String, String>();
		
		if (!StringUtils.isNull(pkValue)) {
			
			rs = service.lxckPrint(pkValue);
		}
		
		request.setAttribute("rs", rs);
		return mapping.findForward("gzdxCfjcPrint");
	}
	
	
	/**
	 * �ɶ�����Υ�ͳ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cdtyWjcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue=request.getParameter("pkValue");
		
		WjcfNblgService service=new WjcfNblgService();
		
		request.setAttribute("rs", service.getCxcfInfo(pkValue));
		return mapping.findForward("cdty_jccfdjb");
	}
}
