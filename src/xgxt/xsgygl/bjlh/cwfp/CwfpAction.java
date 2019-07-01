package xgxt.xsgygl.bjlh.cwfp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.GetDataInfo;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.bjlh.BjlhGyglDAO;
import xgxt.xsgygl.bjlh.BjlhGyglForm;
import xgxt.xsgygl.dao.GyglShareDAO;

public class CwfpAction extends DispatchAction {
	
	/**
	 * ��λ����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cwfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		CwfpService service = new CwfpService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;

		if (userType.equalsIgnoreCase("xy")) {
			myForm.setXydm(userDep);
		}
		String fpbj = "qrz";
		if (!StringUtils.isNull(myForm.getFpbj())) {
			fpbj = myForm.getFpbj();
		} else {
			fpbj = userDep;
		}

		if (fpbj.equalsIgnoreCase(BjlhGyglDAO.CJDM)) {
			myForm.setFplx("�ɽ���");
			myForm.setLx("�ɽ���");
		} else if (fpbj.equalsIgnoreCase(BjlhGyglDAO.KYDM)) {
			myForm.setFplx("�о���");
			myForm.setLx("�о���");
		} else if (fpbj.equalsIgnoreCase(BjlhGyglDAO.TWDM)) {
			myForm.setFplx("��ί��");
			myForm.setLx("��ί��");
		} else if (fpbj.equalsIgnoreCase(BjlhGyglDAO.TYDM)) {
			myForm.setFplx("������");
			myForm.setLx("������");
		} else {
			myForm.setFplx("ȫ����");
		}
		myForm.setFpbj(fpbj);
		request.setAttribute("fplx", myForm.getFplx());

		String[] tmp = service.getCwFpZsData(myForm.getXydm(), myForm.getZydm(), myForm.getBjdm(), myForm.getNj(), fpbj);// δ��������(��)Ĭ��Ϊȫ����ѧ��
		if (tmp != null) {
			request.setAttribute("total", tmp[0]);
			request.setAttribute("boy", tmp[1]);
			request.setAttribute("girl", tmp[2]);
		} else {
			request.setAttribute("total", "0");
			request.setAttribute("boy", "0");
			request.setAttribute("girl", "0");
		}
		String[] tmpT = service.getCwFpYhfcws(myForm.getXydm(), myForm.getZydm(), myForm.getBjdm(), myForm.getNj(), myForm.getLddm(), myForm.getCs(), fpbj);// �ѻ�����(��)λ��(��):
		if (tmpT != null) {
			request.setAttribute("totalBed", tmpT[0]);
			request.setAttribute("boyBed", tmpT[1]);
			request.setAttribute("girlBed", tmpT[2]);
			request.setAttribute("xlBed", tmpT[3]);
		} else {
			request.setAttribute("totalBed", "0");
			request.setAttribute("boyBed", "0");
			request.setAttribute("girlBed", "0");
			request.setAttribute("xlBed", "0");
		}
		tmp = service.getCwFpWhfcws(myForm.getXydm(), myForm.getLddm(), myForm.getCs(), fpbj);// δ������(��)λ��(��):
		if (tmp != null) {
			request.setAttribute("totalBedF", tmp[0]);
			request.setAttribute("boyBedF", tmp[1]);
			request.setAttribute("girlBedF", tmp[2]);
			request.setAttribute("bgBedF", tmp[3]);
		} else {
			request.setAttribute("totalBedF", "0");
			request.setAttribute("boyBedF", "0");
			request.setAttribute("girlBedF", "0");
			request.setAttribute("bgBedF", "0");
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "bjlh_gygl_cwfp.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		service.setList(myForm, request);
		// У���б�
		List xiaoqquList = GyglShareDAO.getXiaoQuList(userName);
		request.setAttribute("xiaoqquList", xiaoqquList);
		GetDataInfo getDataIF = new GetDataInfo();
		request.setAttribute("ldList", getDataIF.getSsFpLdList(
				myForm.getXqdm(), myForm.getXb()));// ¥���б�
		request.setAttribute("lcList", getDataIF
				.getSsFpCsList(myForm.getLddm()));// ¥���б�
		request.setAttribute("fpbjList", service.getFpbjList());// �������б�
		request.setAttribute("commanForm", myForm);
		request.setAttribute("cwxxList", service.getCwFpCwXxList(myForm
				.getXqdm(), myForm.getXb(), myForm.getLddm(), myForm.getCs(),
				fpbj));// δ���䴲λ��Ϣ�б�
		request.setAttribute("xsxxList", service.getCwFpXsXxList(myForm
				.getXydm(), myForm.getZydm(), myForm.getBjdm(), myForm.getNj(),
				myForm.getXb(), fpbj));//δ����ѧ����Ϣ�б�
		request.setAttribute("yfpxsxxList", service.getCwFpYfpXsXxList(myForm
				.getXydm(), myForm.getZydm(), myForm.getBjdm(), myForm.getNj(),
				myForm.getXb(), myForm.getLddm(), myForm.getCs(), fpbj));// �ѷ���ѧ����Ϣ�б�
		
		return mapping.findForward("cwfpManage");
	}
	
	/**
	 * �������������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCwfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		
		String fpbj = myForm.getFpbj();
		String fplx = "";

		if (fpbj.equalsIgnoreCase(BjlhGyglDAO.CJDM)) {
			fplx = "�ɽ���";
		} else if (fpbj.equalsIgnoreCase(BjlhGyglDAO.KYDM)) {
			fplx = "�о���";
		} else if (fpbj.equalsIgnoreCase(BjlhGyglDAO.TWDM)) {
			fplx = "��ί��";
		} else if (fpbj.equalsIgnoreCase(BjlhGyglDAO.TYDM)) {
			fplx = "������";
		} else {
			fplx = "ȫ����";
		}
		
		//Ҫɾ�����ѻ�������SQL�ַ���
		String oldMappingItems = myForm.getOldCondiSqlValue();
		//�ѻ�������SQL�ַ���
		String newMappingItems = myForm.getConditionSqlValue();
		CwfpService service = new CwfpService();
		boolean result = service.saveCwfpxx(oldMappingItems, newMappingItems, fplx);
		if (result) {
			oldMappingItems=newMappingItems;//���ľ�ֵ
			request.setAttribute("doFlag", "ok");
		} else {
			request.setAttribute("doFlag", "no");
		}		
		request.setAttribute("oldMappingItems", oldMappingItems);
		return cwfpManage(mapping, form, request, response);
	}
	
	/**
	 * �����������������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXlCwfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		
		String cwList = myForm.getConditionXlValue();
		CwfpService service = new CwfpService();
		boolean result = service.saveXlCwfpxx(cwList);
		if (result) {
			request.setAttribute("doFlag", "ok"); 
		} else {
			request.setAttribute("doFlag", "no");
		}
		return cwfpManage(mapping, form, request, response);
	}
}
