package xgxt.pjpy;

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
 * Description: ��������ҳ����ת-action��
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

public class PjpyTyAction extends DispatchAction {

	/**
	 * ����С��ά����ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward cpxzForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Cpxz(xxdm)) {// �㽭��ý
			forward = "/zjcm_pjpy_cpxz.do";
		} else {
			forward = "/zjcm_pjpy_cpxzz.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ����������ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward tjszForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Tjsz(xxdm)) {// �㽭��ý
			forward = "/zjcm_pjpy_tjsz.do";
		} else {
			forward = "/zjcm_pjpy_tjsz.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ��ѧ����������ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward jdszForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Jdsz(xxdm)) {// �㽭��ý
			forward = "/zjcm_pjpy_jdsz.do";
		} else {
			forward = "/zjcm_pjpy_jdsz.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ������ά����ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward zyfForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Zyf(xxdm)) {// �㽭��ý
			forward = "/zjcm_pjpy_zyf.do";
		}  else if(Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)){
			forward = "/pjpyMjxy.do?method=zyfManage";
		}	else {
			forward = "/zjcm_pjpy_zyf.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * �ۺ����ʷ�ά����ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward zhfForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Zhf(xxdm)) {// �㽭��ý
			forward = "/zjcm_pjpy_zhf.do";
		} else {
			forward = "/zjcm_pjpy_zhf.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ��ѧ������(У��)��ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward jxjsqXnForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Jxjsq(xxdm)) {// �㽭��ý
			forward = "/zjcm_pjpy_jxjsqxn.do";
		} else {
			forward = "/zjcm_pjpy_jxjsqxn.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ��ѧ������(У��)��ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward jxjsqXwForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Jxjsq_xw(xxdm)) {// �㽭��ý
			forward = "/zjcm_pjpy_jxjsqxw.do";
		} else {
			forward = "/zjcm_pjpy_jxjsqxw.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * �����ƺ�������ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward rychsqForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Rychsq(xxdm)) {// �㽭��ý
			forward = "/zjcm_pjpy_rychsq.do";
		} else {
			forward = "/zjcm_pjpy_rychsq.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ��ѧ���걨�����ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward jxjjgForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Jxjjg(xxdm)) {// �㽭��ý
			forward = "/zjcm_pjpy_jxjjg.do";
		} else {
			forward = "/zjcm_pjpy_jxjjg.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * �����ƺ��걨�����ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward rychjgForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Rychjg(xxdm)) {// �㽭��ý
			forward = "/zjcm_pjpy_rychjg.do";
		} else {
			forward = "/zjcm_pjpy_rychjg.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ����ͳ����ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward bbtjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Bbtj(xxdm)) {// �㽭��ý
			forward = "/zjcm_pjpy_bbtj.do";
		} else {
			forward = "/zjcm_pjpy_tjbb.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ���з�ά����ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward cxfwhForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(xxdm)) {// �㽭��ְͨҵ����ѧԺ
			forward = "/zjjt_pjpy_cxfwh.do";
		} else {
			forward = "/zjjt_pjpy_cxfwh.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ���з������ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward cxfshForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(xxdm)) {// �㽭��ְͨҵ����ѧԺ
			forward = "/zjjt_pjpy_cxfsh.do";
		} else {
			forward = "/zjjt_pjpy_cxfsh.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ���зֽ����ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward cxfjgForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(xxdm)) {// �㽭��ְͨҵ����ѧԺ
			forward = "/zjjt_pjpy_cxfjg.do";
		} else {
			forward = "/zjjt_pjpy_cxfjg.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ���з�¼����ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward cxflrForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(xxdm)) {// �㽭��ְͨҵ����ѧԺ
			forward = "/zjjt_pjpy_cxflr.do";
		} else {
			forward = "/zjjt_pjpy_cxflr.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ���зֲ�ѯ��ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward cxfcxForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(xxdm)) {// �㽭��ְͨҵ����ѧԺ
			forward = "/zjjt_pjpy_cxfcx.do";
		} else {
			forward = "/zjjt_pjpy_cxfcx.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ���з�ͳ����ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward cxftjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(xxdm)) {// �㽭��ְͨҵ����ѧԺ
			forward = "/zjjt_pjpy_cxftj.do";
		} else {
			forward = "/zjjt_pjpy_cxftj.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * �������� - ��ѧ��(�����ƺ�) - ��������
	 * 
	 * @return ActionForward
	 */
	public ActionForward rsszForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)) {// ���ݴ�ѧ
			// ѧУ������Ա��
			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				forward = "/guizdx_pjpy_rssz_xx.do";
			}
			// ѧԺ
			else if ("xy".equalsIgnoreCase(userType)) {
				forward = "/guizdx_pjpy_rssz_xy.do";
			}
			// ����
			else {
				request.setAttribute("errMsg", "��û�з���Ȩ�ޣ���ȷ�ϣ�");
				return new ActionForward("/errMsg.do", false);
			}
		} else {
			forward = "/prise_conf_rs.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * �ʾ���� - �ʾ�ά��
	 * 
	 * @return ActionForward
	 */
	public ActionForward wjwhForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (PjpyYw.isWjdcFirst(xxdm)) {// �ж��Ƿ��ʾ����汾1
			forward = "/nbcs_pjpy_wjManage.do";
		} else {
			forward = "/nbcs_pjpy_wjManage.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * �ʾ���� - ����ά��
	 * 
	 * @return ActionForward
	 */
	public ActionForward stwhForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (PjpyYw.isWjdcFirst(xxdm)) {// �ж��Ƿ��ʾ����汾1
			forward = "/nbcs_pjpy_stManage.do";
		} else {
			forward = "/nbcs_pjpy_stManage.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * �ʾ���� - ���ά��
	 * 
	 * @return ActionForward
	 */
	public ActionForward zjwhForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (PjpyYw.isWjdcFirst(xxdm)) {// �ж��Ƿ��ʾ����汾1
			forward = "/nbcs_pjpy_zjManage.do";
		} else {
			forward = "/nbcs_pjpy_zjManage.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * �ʾ���� - �ش��ʾ�
	 * 
	 * @return ActionForward
	 */
	public ActionForward hdwjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (PjpyYw.isWjdcFirst(xxdm)) {// �ж��Ƿ��ʾ����汾1
			forward = "/nbcs_pjpy_hdwjManage.do";
		} else {
			forward = "/nbcs_pjpy_hdwjManage.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * �ʾ���� - �ش�ͳ��
	 * 
	 * @return ActionForward
	 */
	public ActionForward hdtjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (PjpyYw.isWjdcFirst(xxdm)) {// �ж��Ƿ��ʾ����汾1
			forward = "/nbcs_pjpy_hdtjManage.do";
		} else {
			forward = "/nbcs_pjpy_hdtjManage.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * �ʾ���� - �ش�����
	 * 
	 * @return ActionForward
	 */
	public ActionForward hdpjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (PjpyYw.isWjdcFirst(xxdm)) {// �ж��Ƿ��ʾ����汾1
			forward = "/nbcs_pjpy_hdpjManage.do";
		} else {
			forward = "/nbcs_pjpy_hdpjManage.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * �ʾ���� - �ʾ����
	 * 
	 * @return ActionForward
	 */
	public ActionForward wjfpForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (PjpyYw.isWjdcFirst(xxdm)) {// �ж��Ƿ��ʾ����汾1
			forward = "/nbcs_pjpy_wjfpManage.do";
		} else {
			forward = "/nbcs_pjpy_wjfpManage.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ѧ��Ʒ������ - ����˹���
	 * 
	 * @return ActionForward
	 */
	public ActionForward djrForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)) {// ��������ְҵ����ѧԺ
			forward = "/nbcs_pjpy_pxpj_djrManage.do";
		} else {
			forward = "/nbcs_pjpy_pxpj_djrManage.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * ѧ��Ʒ������ - �ʾ����
	 * 
	 * @return ActionForward
	 */
	public ActionForward pxwjfpForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)) {// ��������ְҵ����ѧԺ
			forward = "/nbcs_pjpy_pxpj_wjfpManage.do";
		} else {
			forward = "/nbcs_pjpy_pxpj_wjfpManage.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * ѧ��Ʒ������ - Ʒ������
	 * 
	 * @return ActionForward
	 */
	public ActionForward pxpjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)) {// ��������ְҵ����ѧԺ
			forward = "/nbcs_pjpy_pxpj_pxpjManage.do";
		} else {
			forward = "/nbcs_pjpy_pxpj_pxpjManage.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * ѧ��Ʒ������ - ���۽��
	 * 
	 * @return ActionForward
	 */
	public ActionForward pjjgForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)) {// ��������ְҵ����ѧԺ
			forward = "/nbcs_pjpy_pxpj_pjjgManage.do";
		} else {
			forward = "/nbcs_pjpy_pxpj_pjjgManage.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * ��չ������ - ��չ�����ʷ�¼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fzxszflr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "";
		
		String xxdm = StandardOperation.getXxdm();
		
		if(Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)){
			forward = "/hndx_fzxsz.do?method=fzxszflr";
		}else{
			forward = "/hndx_fzxsz.do?method=fzxszflr";
		}
		
		return new ActionForward(forward);
	}
	
	/**
	 * ��չ������ - ��չ�����ʷֲ�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fzxszfcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "";
		
		String xxdm = StandardOperation.getXxdm();
		
		if(Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)){
			forward = "/hndx_fzxsz.do?method=fzxszfcx";
		}else{
			forward = "/hndx_fzxsz.do?method=fzxszfcx";
		}
		
		return new ActionForward(forward);
	}
	
	public ActionForward zhszfcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "";
		
		String xxdm = StandardOperation.getXxdm();
		
		if(Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)){
			forward = "/hndx_fzxsz.do?method=zhszfcx";
		}else{
			forward = "/hndx_fzxsz.do?method=zhszfcx";
		}
		
		return new ActionForward(forward);
	}
}
