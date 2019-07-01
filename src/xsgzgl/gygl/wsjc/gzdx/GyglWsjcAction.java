package xsgzgl.gygl.wsjc.gzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.log.SystemLog;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ͨ�ð汾��Ԣ����-�������-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * 12 Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * @version 1.0
 */

public class GyglWsjcAction extends BasicExtendAction {

	/**
	 * ��������
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	@SystemLog(description="���ʹ�Ԣ����-�������-��������-{doType}")
	public ActionForward csszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		User user = getUser(request);// �û�����

		// ================= ����ֵ ==================
		// ����·��
		String path = "gzdx_gygl_wsjc_cssz.do";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����ģ��
		String gnmk = "gygl";
		// �˵�
		String menu = "wsjc_cssz";
		// ��ʾ��Ϣ
		String message = "";
		// =================end==================

		// =================ִ�б������==================;
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveCssz(myForm, user, request);
			message = flag ? "�����ɹ�" : "����ʧ��";
		}
		// =================end==================

		// =================��ò�����������==================
		HashMap<String, String> rs = service.getCsszInfo();
		// =================end==================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setRs(rs);
		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, user, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("csszManage");
	}

	/**
	 * �հױ���
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward kbbbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		User user = getUser(request);// �û�����
		GyglWsjcModel wsjcModel = service.getWsjc();// ����������

		// ================= ����ֵ ==================
		// ����·��
		String path = "gzdx_gygl_wsjc_kbbb.do";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����ģ��
		String gnmk = "gygl";
		// �˵�
		String menu = "wsjc_kbbb";
		// ��ʾ��Ϣ
		String message = "";
		// �������
		String jczq = wsjcModel.getJczq();
		// ��Ԣ�汾
		String edition = myForm.getEdition();
		// �Ƿ�ѧԺ
		boolean isxy = service.isXy(user);
		// =================end==================

		// ================= δ���ò������� ==================
		if (!service.isSz()) {
			return errForward(request);
		}
		// ================= end ==================

		// ================= �ж��Ƿ�ѧԺ ==================
		isXy(myForm, user, isxy);
		// ================= end ==================
		
		// =================ִ�е�������==================;
		if ("exp".equalsIgnoreCase(doType)) {

			myForm.setUserName(user.getUserName());
			
			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.expWsjcKbbb(myForm, wsjcModel, response.getOutputStream());

			return null;
		}
		// =================end==================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "jczq", "isxy", "gyglEdition" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { jczq, String.valueOf(isxy), edition };

		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setUserName(user.getUserName());

		service.setRequestValue(rForm, user, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("kbbbManage");
	}

	/**
	 * ������¼��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	@SystemLog(description="���ʹ�Ԣ����-�������-������¼��-{doType}PK:{primarykey_checkVal}")
	public ActionForward fslrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		User user = getUser(request);// �û�����
		GyglWsjcModel wsjcModel = service.getWsjc();// ����������

		// ================= ����ֵ ==================
		// ����·��
		String path = "gzdx_gygl_wsjc_fslr.do";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����ģ��
		String gnmk = "gygl";
		// �˵�
		String menu = "wsjc_fs";
		// ��ʾ��Ϣ
		String message = "";
		// ��Ԣ�汾
		String edition = myForm.getEdition();
		// �������
		String jczq = wsjcModel.getJczq();
		// ¼����ʽ
		String lrxs = wsjcModel.getLrxs();
		// �Ƿ�����ȼ�
		String gldj = wsjcModel.getGldj();
		// �Ƿ��������
		String glfs = wsjcModel.getGlfs();
		// ����ܴ�
		String jczc = myForm.getJczc();
		// ���ʱ��
		String jcsj = myForm.getSearchModel().getSearch_tj_kssj()==null? "":myForm.getSearchModel().getSearch_tj_kssj()[0];
		if (Base.isNull(jcsj)) {
			jcsj = service.getNowTime("YYYYMMDD");
			myForm.getSearchModel().setSearch_tj_kssj(new String[]{jcsj});
		}
		myForm.setJcsj(jcsj);
		
		// ��ǰ�ܴ�
		String dqzc = "";
		if (Base.isNull(jczc) && "��".equalsIgnoreCase(jczq)) {
			dqzc = service.getNowZc(jcsj, wsjcModel);
			myForm.setJczc(dqzc);
		}
		// ����ѧ��
		String xn = Base.currXn;
		myForm.setXn(xn);
		// ����ѧ��
		String xq = Base.currXq;
		myForm.setXq(xq);
		// �������
		String nd = Base.currNd;
		myForm.setNd(nd);
		// �ȼ��б�
		List<HashMap<String, String>> wsfdjList = wsjcModel.getWsdjList();
		int djNum = (wsfdjList != null && wsfdjList.size() > 0) ? wsfdjList
				.size() : 0;
		// �Ƿ�ѧԺ
		boolean isxy = service.isXy(user);
		// ��ͷ
		List<HashMap<String, String>> topTr = null;
		// ����б�
		ArrayList<String[]> rsArrList = null;
		// =================end==================

		// ================= δ���ò������� ==================
		if (!service.isSz()) {
			return errForward(request);
		}
		// ================= end ==================

		// ================= �ж��Ƿ�ѧԺ ==================
		isXy(myForm, user, isxy);
		// ================= end ==================
		
		// =================ִ�б������==================;
		if ("save".equalsIgnoreCase(doType)) {
			// ���¥��
			String[] jcld = myForm.getJcld();

			if (jcld != null && jcld.length > 0) {
				boolean flag = service.saveWsf(myForm, wsjcModel, user);
				message = flag ? "�����ɹ�" : "����ʧ��";
			} 
		} else if ("update".equalsIgnoreCase(doType)) {
			boolean flag = service.updateWsf(myForm, wsjcModel, user);
			message = flag ? "�����ɹ�" : "����ʧ��";
		}
		// =================end==================

		// =================ִ�в�ѯ����==================;
		// ��ͷ
		topTr = service.getWsflrTop(wsjcModel);
		// �����
		rsArrList = service.getWsflrList(myForm, wsjcModel);
		// =================end==================
		
		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "isxy", "jczq", "jczc", "lrxs", "gldj",
				"glfs", "djNum","gyglEdition" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { String.valueOf(isxy), jczq, jczc, lrxs,
				gldj, glfs, String.valueOf(djNum), edition };

		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setUserName(user.getUserName());

		service.setRequestValue(rForm, user, request);
		request.setAttribute("wsfdjList", wsfdjList);
		// ===================end ====================
		
		// ===================��ʼ���б�ֵ ======================
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		service.setList(model, rForm, request);
		// =================end ===================

		request.setAttribute("searchTj", myForm.getSearchModel());
		// write��titile
		setWriteAbleAndTitle(request, path);
		request.setAttribute("path", path);
		request.setAttribute("jcsj", jcsj);
		request.setAttribute("jcry", request.getParameter("jcry"));

		return mapping.findForward("fslrManage");
	}

	/**
	 * ��ʾ������Ϣ
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward showQsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		User user = getUser(request);// �û�����
		GyglWsjcModel wsjcModel = service.getWsjc();// ����������

		// ================= ����ֵ ==================
		// ����ģ��
		String gnmk = "gygl";
		// �˵�
		String menu = "wsjc_kbbb";
		// ��ʾ��Ϣ
		String message = "";
		// ��Ԣ�汾
		String edition = myForm.getEdition();
		// ����
		String pk = request.getParameter("pk");
		myForm.setPkValue(pk);
		// ����ܴ�
		String jczc = request.getParameter("jczc");
		myForm.setJczc(jczc);
		// ���ʱ��
		String jcsj = request.getParameter("jcsj");
		// �������
		String jczq = wsjcModel.getJczq();
		// ¼����ʽ
		String lrxs = wsjcModel.getLrxs();
		myForm.setJcsj(jcsj);
		// �������������
		HashMap<String, String> rs = service.getQsWsfInfo(myForm, wsjcModel);
		ArrayList<String[]> rsArrList = service.getQsrzInfo(myForm);
		// ================= end ==================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "jczq", "lrxs", "gyglEdition" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { jczq, lrxs, edition };

		rForm.setRs(rs);
		rForm.setRsArrList(rsArrList);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setUserName(user.getUserName());

		service.setRequestValue(rForm, user, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("qsInfo");

	}

	/**
	 * �����ֲ�ѯ
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	@SystemLog(description="���ʹ�Ԣ����-�������-������¼��-{doType}PK:{primarykey_checkVal}")
	public ActionForward fscxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		User user = getUser(request);// �û�����
		GyglWsjcModel wsjcModel = service.getWsjc();// ����������

		// ================= ����ֵ ==================
		// ����·��
		String path = "gzdx_gygl_wsjc_fscx.do";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ������
		String realTable = "gygl_wsjc_wsfwhb";
		// ����ģ��
		String gnmk = "gygl";
		// �˵�
		String menu = "wsjc_fs";
		// ��ʾ��Ϣ
		String message = "";
		// �������
		String jczq = wsjcModel.getJczq();
		// ¼����ʽ
		String lrxs = wsjcModel.getLrxs();
		// �Ƿ�����ȼ�
		String gldj = wsjcModel.getGldj();
		// �Ƿ��������
		String glfs = wsjcModel.getGlfs();
		// ��Ԣ�汾
		String edition = myForm.getEdition();
		// ����
		String lx = Base.isNull(myForm.getLx()) ? "qs" : myForm.getLx();
		myForm.setLx(lx);
		// ���ʱ��
		String jcsj = myForm.getJcsj();
		if (Base.isNull(jcsj)) {
			jcsj = service.getNowTime("YYYYMMDD");
			myForm.setJcsj(jcsj);
		}
		// �����ܴ�
		String jszc = myForm.getJszc();
		// ��ǰ�ܴ�
		String dqzc = "";
		if (Base.isNull(jszc) && "��".equalsIgnoreCase(jczq)) {
			dqzc = service.getNowZc(jcsj, wsjcModel);
			myForm.setJszc(dqzc);
		}
		// �ȼ��б�
		List<HashMap<String, String>> wsfdjList = wsjcModel.getWsdjList();
		int djNum = (wsfdjList != null && wsfdjList.size() > 0) ? wsfdjList
				.size() : 0;
		// �Ƿ�ѧԺ
		boolean isxy = service.isXy(user);
		// ��ͷ
		List<HashMap<String, String>> topTr = null;
		// ����б�
		ArrayList<String[]> rsArrList = null;
		
		// =================end==================

		// ================= δ���ò������� ==================
		if (!service.isSz()) {
			return errForward(request);
		}
		// ================= end ==================

		// ================= �ж��Ƿ�ѧԺ ==================
		isXy(myForm, user, isxy);
		// ================= end ==================
		
		// =================ִ��ɾ������==================;
		if ("del".equalsIgnoreCase(doType)) {
			boolean flag = service.delWsf(myForm, user);
			message = flag ? "�����ɹ�" : "����ʧ��";
		}
		// =================end==================
		
		// =================ִ�е�������==================;
		if ("exp".equalsIgnoreCase(doType)) {
			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.wsjcQsExp(myForm, wsjcModel, response.getOutputStream());

			return null;
		}
		// =================end==================
		
		// =================ִ�в�ѯ����==================;		
		// ��ͷ
		topTr = service.getWsflrTop(wsjcModel);
		// �����
		rsArrList = service.getWsjcJgList(myForm, wsjcModel);
		
		int size = myForm.getPages().getMaxRecord();

		if (rsArrList != null && rsArrList.size() > 0
				&& size < rsArrList.size()) {
			rsArrList = service.getResultList(rsArrList, myForm);
		}		
		// =================end==================
		
		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "isxy", "jczq", "lrxs", "gldj", "glfs",
				"djNum", "lx", "gyglEdition" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { String.valueOf(isxy), jczq, lrxs, gldj,
				glfs, String.valueOf(djNum), lx, edition };

		rForm.setMessage(message);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setUserName(user.getUserName());

		service.setRequestValue(rForm, user, request);
		request.setAttribute("wsfdjList", wsfdjList);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================
				
		request.setAttribute("searchTj", myForm.getSearchModel());
		// write��titile
		setWriteAbleAndTitle(request, path);
		request.setAttribute("path", path);

		return mapping.findForward("fscxManage");
	}
	
	/**
	 * �������޸�
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	@SystemLog(description="���ʹ�Ԣ����-�������-������¼��-�޸�PK:{pkValue}")
	public ActionForward fscxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		User user = getUser(request);// �û�����
		GyglWsjcModel wsjcModel = service.getWsjc();// ����������

		// ================= ����ֵ ==================
		// ����·��
		String path = "gzdx_gygl_wsjc_fscx.do";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����
		String pk = request.getParameter("pk");
		myForm.setPkValue(pk);
		// ����ģ��
		String gnmk = "gygl";
		// �˵�
		String menu = "wsjc_fs";
		// ��ʾ��Ϣ
		String message = "";
		// �������
		String jczq = wsjcModel.getJczq();
		// ¼����ʽ
		String lrxs = wsjcModel.getLrxs();
		// �Ƿ�����ȼ�
		String gldj = wsjcModel.getGldj();
		// �Ƿ��������
		String glfs = wsjcModel.getGlfs();
		// ����
		String lx = request.getParameter("lx");
		// �������������
		HashMap<String, String> rs = service.getWsfjgInfo(myForm, wsjcModel);
		// ס���б�
//		String pkValue = rs.get("lddm") + rs.get("cs") + rs.get("qsh");
		String pkValue = rs.get("lddm") + rs.get("qsh");
		myForm.setPkValue(pkValue);
		ArrayList<String[]> rsArrList = service.getQsrzInfo(myForm);
		//ҳ����ת
		String forward = "qs".equalsIgnoreCase(lx) ? "qsInfo" : "xsInfo";
		// =================end==================

		// =================ִ�б������==================;
		if ("save".equalsIgnoreCase(doType)) {

			myForm.setPkValue(pk);

			boolean flag = service.editWsf(myForm, request);
			message = flag ? "�����ɹ�" : "����ʧ��";
			
			myForm.setLx("qs");
			rs = service.getWsfjgInfo(myForm, wsjcModel);
		}
		// =================end==================
		
		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "jczq", "lrxs", "gldj", "glfs", "lx",
				"pk" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { jczq, lrxs, gldj, glfs, lx, pk };

		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setRs(rs);
		rForm.setRsArrList(rsArrList);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setUserName(user.getUserName());

		service.setRequestValue(rForm, user, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward(forward);
	}

	/**
	 * ������ͳ��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward fstjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		User user = getUser(request);// �û�����
		GyglWsjcModel wsjcModel = service.getWsjc();// ����������

		// ================= ����ֵ ==================
		// ����·��
		String path = "gygl_wsjc_fstj.do";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����ģ��
		String gnmk = "gygl";
		// �˵�
		String menu = "wsjc_fs";
		// ��Ԣ�汾
		String edition = myForm.getEdition();
		// ��ʾ��Ϣ
		String message = "";
		// �������
		String jczq = wsjcModel.getJczq();
		// ¼����ʽ
		String lrxs = wsjcModel.getLrxs();
		// �Ƿ�����ȼ�
		String gldj = wsjcModel.getGldj();
		// �Ƿ��������
		String glfs = wsjcModel.getGlfs();
		// ���ʱ��
		String jcsj = myForm.getJcsj();
		if (Base.isNull(jcsj)) {
			jcsj = service.getNowTime("YYYYMMDD");
			myForm.setJcsj(jcsj);
		}
		// �����ܴ�
		String jszc = myForm.getJszc();
		// ��ǰ�ܴ�
		String dqzc = "";
		if (Base.isNull(jszc) && "��".equalsIgnoreCase(jczq)) {
			dqzc = service.getNowZc(jcsj, wsjcModel);
			myForm.setJszc(dqzc);
		}
		// �ȼ��б�
		List<HashMap<String, String>> wsfdjList = wsjcModel.getWsdjList();
		int djNum = (wsfdjList != null && wsfdjList.size() > 0) ? wsfdjList
				.size() : 0;
		// �Ƿ�ѧԺ
		boolean isxy = service.isXy(user);
		// ��ͷ
		List<HashMap<String, String>> topTr = null;
		// ����б�
		ArrayList<String[]> rsArrList = null;
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ================= δ���ò������� ==================
		if (!service.isSz()) {
			return errForward(request);
		}
		// ================= end ==================

		// ================= �ж��Ƿ�ѧԺ ==================
		isXy(myForm, user, isxy);
		// ================= end ==================
		
		// ================= δ���ò������� ==================
		String dj = service.getOneValue("gygl_wsjc_wsfdjb", "wsfdj", "1", "1");
		if(Base.isNull(dj)){
			String msg = "��ģ��ͳ�ƻ��������ֵȼ����в��������η���¼���޵ȼ�����ģ���޷���������ȷ�ϣ�";

			request.setAttribute("yhInfo", msg);

			return new ActionForward("/yhInfo.do", false);
		}
		// ================= end ==================
		
		// =================ִ�е�������==================;
		if ("exp".equalsIgnoreCase(doType)) {
			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.wsjcTjExp(myForm, wsjcModel, response.getOutputStream(),
					topTr, request);

			return null;
		}
		// =================end==================
		
		// =================ִ�в�ѯ����==================;
		if (search) {
			
			service.tbWsfAndDj(myForm, wsjcModel);
			// ��ͷ
			topTr = service.getWsfTjTop(myForm, wsjcModel);
			// �����
			rsArrList = service.getResultList(service.getWsfTjList(myForm,
					wsjcModel, topTr, request), myForm);
		}
		// =================end==================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "isxy", "jczq", "lrxs", "gldj", "glfs",
				"djNum","gyglEdition" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { String.valueOf(isxy), jczq, lrxs, gldj,
				glfs, String.valueOf(djNum),edition };

		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setUserName(user.getUserName());

		service.setRequestValue(rForm, user, request);
		request.setAttribute("wsfdjList", wsfdjList);
		
		if (wsfdjList != null && wsfdjList.size() > 0) {
			String wsfdj = wsfdjList.get(0).get("wsfdj");
			request.setAttribute("wsfdj", wsfdj);
		}
		// ===================end ====================

		// =================== ��ʼ���б�ֵ ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("fstjManage");
	}
	
	/**
	 * δ���ò������õ���ת
	 * 
	 * @param request
	 * @return
	 */
	private ActionForward errForward(HttpServletRequest request) {

		String msg = "�������Ĳ���������δ�����ã���ȷ�ϣ�";

		request.setAttribute("yhInfo", msg);

		return new ActionForward("/yhInfo.do", false);
	}
	
	/**
	 * @param myForm
	 * @param user
	 * @param isxy
	 */
	private void isXy(GyglTyForm myForm, User user, boolean isxy) {
		if (isxy) {
			String userDep = user.getUserDep();
			myForm.setXydm(userDep);
		}
	}
		
	/**
	 * ѧ������¼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author sjf
	 */
	public ActionForward xsfslrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		User user = getUser(request);// �û�����
		GyglWsjcModel wsjcModel = service.getWsjc();// ����������

		// ================= ����ֵ ==================
		// ����·��
		String path = "gygl_wsjc_xsfslr.do";
		// ����ģ��
		String gnmk = "gygl";
		// �˵�
		String menu = "wsjc_fs";
		// ��ʾ��Ϣ
		String message = "";
		// �������
		String jczq = wsjcModel.getJczq();
		myForm.setJczq(jczq);
		// ����ܴ�
		String jczc = myForm.getJczc();
		// ���ʱ��
		String jcsj = myForm.getJcsj();
		
		if (Base.isNull(jcsj)) {
			jcsj = service.getNowTime("YYYYMMDD");
			myForm.setJcsj(jcsj);
		}
		// ��ǰ�ܴ�
		String dqzc = "";
		if (Base.isNull(jczc) && "��".equalsIgnoreCase(jczq)) {
			dqzc = service.getNowZc(jcsj, wsjcModel);
			myForm.setJczc(dqzc);
		}
		// ����ѧ��
		String xn = Base.currXn;
		myForm.setXn(xn);
		// ����ѧ��
		String xq = Base.currXq;
		myForm.setXq(xq);
		// �������
		String nd = Base.currNd;
		myForm.setNd(nd);
		// �ȼ��б�
		List<HashMap<String, String>> wsfdjList = wsjcModel.getWsdjList();
		int djNum = (wsfdjList != null && wsfdjList.size() > 0) ? wsfdjList
				.size() : 0;
		// �Ƿ�ѧԺ
		boolean isxy = service.isXy(user);
		// ��ͷ
		List<HashMap<String, String>> topTr = null;
		// ����б�
		ArrayList<String[]> rsArrList = null;
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ================= δ���ò������� ==================
		if (!service.isSz()) {
			return errForward(request);
		}
		// ================= end ==================

		// ================= �ж��Ƿ�ѧԺ ==================
		isXy(myForm, user, isxy);
		// ================= end ==================
		
		// =================ִ�в�ѯ����==================;
		if(search){
			topTr = service.getXslrfTop(wsjcModel, "lr");
			rsArrList = service.getXslrfInfo(myForm);
		
		}
		// =================end==================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "isxy", "jczq", "jczc", "djNum" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { String.valueOf(isxy), jczq, jczc,String.valueOf(djNum) };

		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setUserName(user.getUserName());

		service.setRequestValue(rForm, user, request);
		request.setAttribute("wsfdjList", wsfdjList);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("xsfslrManage");
	}
	
	/**
	 * ����¼��ѧ���ӷּ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author sjf
	 */
	@SystemLog(description="���ʹ�Ԣ����-�������-ѧ��������¼��-�޸�XH:{xh}")
	public ActionForward xsfslrUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		// User user = getUser(request);
	
		GyglWsjcModel wsjcModel = service.getWsjc();// ����������
		GyglWsjcXslrfModel lrfModel = new GyglWsjcXslrfModel();// ѧ��¼��ֶ���
		
		String xn = Base.currXn;
		String xq = Base.currXq;
		String nd = Base.currNd;
		String xh = request.getParameter("xh");
		String doType = request.getParameter("doType");
		
		// ����ѧ�����ҼӼ�����Ϣ
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(lrfModel, myForm);
			String message = service.saveXslrf(lrfModel, wsjcModel.getJczq()) ? "����ɹ���" : "����ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		// ��ȡѧ��ס����Ϣ
		if(StringUtils.isNotNull(xh)){
			selectPageDataByOne(request, "xszsxxb", "view_xszsxx", xh);
		}
	
		// ================= ����ֵ ==================
		// �������
		String jczq = wsjcModel.getJczq();
	
		// ����ܴ�
		String jczc = myForm.getJczc();
		// ���ʱ��
		String jcsj = myForm.getJcsj();
	
		String title = "��Ԣ����-�������-ѧ������������ά��";
			
		request.setAttribute("title", title);
		request.setAttribute("jczq", jczq);
		request.setAttribute("jcsj", jcsj);
		request.setAttribute("jczc", jczc);
		request.setAttribute("doType", doType);
		request.setAttribute("xn", xn);
		request.setAttribute("xq", xq);
		request.setAttribute("xqmc", BasicExtendService.xqMap.get(xq));
		request.setAttribute("nd", nd);
		request.setAttribute("lrsj", DateUtils.getTime());
		request.setAttribute("addXmList", service.getWsjcInfo("�ӷ�"));
		request.setAttribute("reduceXmList", service.getWsjcInfo("����"));
		return mapping.findForward("xsfslrUpdate");
	}
	
	/**
	 * ѧ�������鿴���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsfsckManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		
		User user = getUser(request);// �û�����
		GyglWsjcModel wsjcModel = service.getWsjc();// ����������
		
		// �Ƿ���Ҫͳ�ƣ���Ϊ��������Ϊ��ʱ��Ҫͳ��
		boolean sftj = "��".equalsIgnoreCase(wsjcModel.getJczq()) ? true : false;
		
		myForm.setJczq(wsjcModel.getJczq());

		// ================= ����ֵ ==================
		// ����·��
		String path = "gygl_wsjc_xsfsck.do";
		// ����ģ��
		String gnmk = "gygl";
		// �˵�
		String menu = "wsjc_fs";
		// ��ʾ��Ϣ
		String message = "";

		// �Ƿ�ѧԺ
		boolean isxy = service.isXy(user);
		// ��ͷ
		List<HashMap<String, String>> topTr = null;
		// ����б�
		ArrayList<String[]> rsArrList = null;
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ================= δ���ò������� ==================
		if (!service.isSz()) {
			return errForward(request);
		}
		// ================= end ==================

		// ================= �ж��Ƿ�ѧԺ ==================
		isXy(myForm, user, isxy);
		// ================= end ==================
		
		// =================ִ�в�ѯ����==================;
		if(search){
			topTr = service.getXslrfTop(wsjcModel, "ck");
			
			rsArrList = service.getXsxqlrfInfo(myForm);
		
		}
		// =================end==================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();
		
		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);
		rForm.setUserName(user.getUserName());
		
		service.setRequestValue(rForm, user, request);
		request.setAttribute("isxy", isxy);
		request.setAttribute("sftj", sftj);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
	
		// =================end ===================
		return mapping.findForward("xsfsckManage");
	}
	
	/**
	 * ����¼��ѧ��ûѧ�ڼӷּ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author sjf
	 */
	public ActionForward xsfsckUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglWsjcService service = new GyglWsjcService();
		// User user = getUser(request);
	
		GyglWsjcModel wsjcModel = service.getWsjc();// ����������
		
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String xqmc = request.getParameter("xqmc");
		String jczq = wsjcModel.getJczq();
		
		// ��ȡѧ��ס����Ϣ
		if(StringUtils.isNotNull(xh)){
			selectPageDataByOne(request, "xszsxxb", "view_xszsxx", xh);
		}
		
		// ��Ÿ�ѧ��ѧ�����мӼ�����Ŀ
		
		List<HashMap<String, String>> xsfsInfo = service.getXsfsInfo(xh, xn, xq, jczq);
	 
		// ================= ����ֵ ==================
	
		String title = "��Ԣ���� - ������� - ѧ�����������ֲ鿴";
			
		request.setAttribute("title", title);
		request.setAttribute("xn", xn);
		request.setAttribute("xq", xq);
		request.setAttribute("jczq", jczq);
		request.setAttribute("xsfsInfo", xsfsInfo);
		request.setAttribute("xqmc", xqmc);
		return mapping.findForward("xsfsckUpdate");
	}
	
	/**
	 * ѧ������ͳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsfsTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		
		if("print".equalsIgnoreCase(doType)){
			response.setContentType("application/vnd.ms-excel");
			service.xswsfTj(myForm, response.getOutputStream());
			return mapping.findForward("");
		}
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		return mapping.findForward("xsfsTj");
	}
}
