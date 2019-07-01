package xgxt.dtjs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import common.Globals;
import xgxt.daoActionLogic.StandardOperation;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ���Ž���ҳ����ת-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * @version 1.0
 */

public class DtjsAction extends DispatchAction {
	
	/**
	 * ֧��������ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward zbglForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {// �㽭��
			forward = "/zjlg_dtjs_zbgl.do";
		} else if (Globals.XXDM_SJXY.equalsIgnoreCase(xxdm)) {// ����ѧԺ
			if ("admin".equalsIgnoreCase(userType)
					|| "xx".equalsIgnoreCase(userType)) {
				forward = "/sjxy_dtjs_zbgl.do";// ����֧
			} else {
				forward = "/sjxy_dtjs_dzb.do";// ��֧��
			}
		} else {
			forward = "/zjlg_dtjs_zbgl.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * �뵳������ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward rdsqForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_SJXY.equalsIgnoreCase(xxdm)) {// ����ѧԺ
			forward = "/sjxy_dtjs_rdsq.do";
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// ������Ϣ
			forward = "/czxx_dtjs_rdsq.do";
		} else {
			forward = "/czxx_dtjs_rdsq.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * �뵳����������ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward jjfzForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {// �㽭��
			forward = "/zjlg_dtjs_jjfz.do";
		} else if (Globals.XXDM_SJXY.equalsIgnoreCase(xxdm)) {// ����ѧԺ
			forward = "/sjxy_dtjs_jjfz.do";
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// ������Ϣ
			forward = "/czxx_dtjs_jjfz.do";
		} else {
			forward = "/czxx_dtjs_jjfz.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ��չ������ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward fzdxForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// ������Ϣ
			forward = "/czxx_dtjs_fzdx.do";
		} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {// �㽭��ý
			//forward = "/dtjs_zjcm.do?method=fzdx";
			forward = "/zjcm_dtjs_fzdx.do";
		} else {
			forward = "/czxx_dtjs_fzdx.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * Ԥ����Ա��ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward ybdyForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {// �㽭��
			forward = "/zjlg_dtjs_ybdy.do";
		} else if (Globals.XXDM_SJXY.equalsIgnoreCase(xxdm)) {// ����ѧԺ
			forward = "/sjxy_dtjs_ybdy.do";
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// ������Ϣ
			forward = "/czxx_dtjs_ybdy.do";
		} else {
			forward = "/czxx_dtjs_ybdy.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ��ʽ��Ա��ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward zsdyForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {// �㽭��
			forward = "/zjlg_dtjs_zsdy.do";
		} else if (Globals.XXDM_SJXY.equalsIgnoreCase(xxdm)) {// ����ѧԺ
			forward = "/sjxy_dtjs_zsdy.do";
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// ������Ϣ
			forward = "/czxx_dtjs_zsdy.do";
		} else {
			forward = "/czxx_dtjs_zsdy.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * ����ͳ����ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward sjtjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_SJXY.equalsIgnoreCase(xxdm)) {// ����ѧԺ
			forward = "/sjxy_dtjs_sjtj.do";
		} else {
			forward = "/sjxy_dtjs_sjtj.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ��ѵ��Ϣ��ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward pxxxForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// ������Ϣ
			forward = "/czxx_dtjs_pxxx.do";
		} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {// �㽭��ý
			forward = "/zjcm_dtjs_pxxx.do";
		} else if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {// �㽭��
			forward = "/zjlg_dtjs_pxxx.do";
		} else if (Globals.XXDM_GDBYXY.equalsIgnoreCase(xxdm)) {// �㶫����
			forward = "/gdby_dtjs_pxxx.do";
		}else {
			forward = "/czxx_dtjs_pxxx.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * ����������ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward dkmdForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// ������Ϣ
			forward = "/czxx_dtjs_dkmd.do";
		}else if(Globals.XXDM_GDBYXY.equalsIgnoreCase(xxdm)){// �㶫����
			forward = "/gdby_dtjs_dkmd.do";
		}else {
			forward = "/czxx_dtjs_dkmd.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * ˼��㱨��ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward sxhbForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// ������Ϣ
			forward = "/czxx_dtjs_sxhb.do";
		} else {
			forward = "/czxx_dtjs_sxhb.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * ��Ա��Ϣ��ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward tyxxForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// ������Ϣ
			forward = "/czxx_dtjs_tyxx.do";
		} else if (Globals.XXDM_NJJS.equalsIgnoreCase(xxdm)) {// �Ͼ���ʦ
			forward = "/njjs_dtjs_tyxx.do";
		} else if(Globals.XXDM_NTZYDX.equalsIgnoreCase(xxdm)) {// ��ְͨҵ
			forward = "/czxx_dtjs_tyxx.do";
		} else {
			forward = "/czxx_dtjs_tyxx.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * ����Ա��ѵ��Ϣ��ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward ftypxForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// ������Ϣ
			forward = "/czxx_dtjs_ftypx.do";
		} else {
			forward = "/czxx_dtjs_ftypx.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * ��Ա��ѵ��ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward typxForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// ������Ϣ
			forward = "/czxx_dtjs_typx.do";
		} else {
			forward = "/czxx_dtjs_typx.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * ����������ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward tntyForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// ������Ϣ
			forward = "/czxx_dtjs_tnty.do";
		} else {
			forward = "/czxx_dtjs_tnty.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * ��Աע����ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward tyzcForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CDTYXY.equalsIgnoreCase(xxdm)) {// �ɶ�����
			forward = "/czxx_dtjs_tyzc.do";
		}  else {
			forward = "/czxx_dtjs_tyzc.do";
		}
		return new ActionForward(forward, false);
	}
}
