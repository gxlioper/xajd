package xsgzgl.pjpy.szgyyq.mypj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.RequestForm;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuService;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaForm;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����_Init��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyMypjInit {

	/**
	 * �ҵ�����_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initMypj(RequestForm rForm, PjpyMypjForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "szgyyq";
		// ϵͳ�ֶ�����
		String menu = "pjxmsb";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "pjpy_szgyyq_mypj.do";
		// ��ͷ
		List<HashMap<String, String>> topTr = getDefaultValue(model);

		// �û�����
		String yhlx = model.getYhlx();
		// ������Ŀ
		String czxm = model.getCzxm();
		czxm = Base.isNull(czxm) ? "szyq_dshdjzb" : czxm;

		// �����ֶ�
		String[] qtzd = new String[] { "yhlx", "czxm" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { yhlx, czxm };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setTopTr(topTr);

	}

	private List<HashMap<String, String>> getDefaultValue(PjpyMypjForm model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		// �û�����
		String yhlx = model.getYhlx();

		HashMap<String, String> map = new HashMap<String, String>();

		if ("stu".equalsIgnoreCase(yhlx) || "bz".equalsIgnoreCase(yhlx)) {// ѧ��
			map = new HashMap<String, String>();
			map.put("en", "xmmc");
			map.put("cn", "��Ŀ����");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sqfs");
			map.put("cn", "�������");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "shqk");
			map.put("cn", "������");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "zzfs");
			map.put("cn", "���շ���");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "cz");
			map.put("cn", "����");
			topTr.add(map);
		}

		return topTr;
	}

	/**
	 * �ҵ�����_��ʼ������(������Ϣ��ϸ)
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initSqxxDetail(RequestForm rForm, PjpyStuForm model,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		PjpyStuService service = new PjpyStuService();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");

		// �û�����
		String yhlx = (String) session.getAttribute("yhlx");
		model.setYhlx(yhlx);
		
		// ѧ��
		String xn = request.getParameter("xn");
		model.setXn(xn);

		// ѧ��
		String xq = request.getParameter("xq");
		model.setXq(xq);

		// ѧ������
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);

		// ѧ��
		String xh = request.getParameter("xh");
		model.setXh(xh);

		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);

		// ��Ŀ����
		String xmmc = getXmmc(xmdm);

		// ��ͷ
		List<HashMap<String, String>> topTr = getSqxxTopTr(model);

		// �����ֶ�
		String[] qtzd = new String[] { "xn", "xq", "xqmc", "xh", "xmdm", "xmmc" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xn, xq, xqmc, xh, xmdm, xmmc };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTopTr(topTr);
	}

	/**
	 * �����Ŀ�����Ӧ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	private String getXmmc(String xmdm) {

		// ��Ŀ����
		String xmmc = "";

		if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {// ����
			xmmc = "����";
		} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {// ���Ա��
			xmmc = "���Ա��";
		} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {// IVT��̳
			xmmc = "IVT��̳";
		} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {// ����
			xmmc = "����";
		} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {// ��֯�
			xmmc = "��֯�";
		} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// ���ʵ��
			xmmc = "���ʵ��";
		}

		return xmmc;
	}

	/**
	 * ���������Ŀ��ϸ�ı�ͷ
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	private List<HashMap<String, String>> getSqxxTopTr(PjpyStuForm model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// �û�����
		String yhlx = model.getYhlx();
		
		HashMap<String, String> map = new HashMap<String, String>();
		if ("szyc_5sb".equalsIgnoreCase(xmdm)) {// 5s
			map = new HashMap<String, String>();
			map.put("en", "fzxm");
			map.put("cn", "��ֵ��Ŀ");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "jjf");
			map.put("cn", "�Ӽ���");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "fz");
			map.put("cn", "��ֵ");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "jfrq");
			map.put("cn", "����");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "yy");
			map.put("cn", "ԭ��");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xyshf");
			map.put("cn", Base.YXPZXY_KEY+"��˷�");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xxshf");
			map.put("cn", "ѧУ��˷�");
			topTr.add(map);

			if ("stu".equalsIgnoreCase(yhlx) || "bz".equalsIgnoreCase(yhlx)) {
				map = new HashMap<String, String>();
				map.put("en", "cz");
				map.put("cn", "����");
				topTr.add(map);
			}

		} else if ("szgy_zhszcphzlsb".equalsIgnoreCase(xmdm)) {
			
			map = new HashMap<String, String>();
			map.put("en", "mkmc");
			map.put("cn", "��Ŀ��");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sqf");
			map.put("cn", "�����");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "bzrshf");
			map.put("cn", "��������˷�");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "xyshf");
			map.put("cn", Base.YXPZXY_KEY+"��˷�");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "xxshf");
			map.put("cn", "ѧУ��˷�");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "mkf");
			map.put("cn", "��ֵ");
			topTr.add(map);
			
		} else {

			if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {// ����
				map = new HashMap<String, String>();
				map.put("en", "dsmc");
				map.put("cn", "����");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "dsrq");
				map.put("cn", "��������");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "dsxd");
				map.put("cn", "�����ĵ�");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "sfhj");
				map.put("cn", "�Ƿ��");
				topTr.add(map);

			} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {// ���Ա��
				map = new HashMap<String, String>();
				map.put("en", "yybdnr");
				map.put("cn", "���Ա������");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "xthdrq");
				map.put("cn", "����");
				topTr.add(map);

			} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {// IVT��̳
				map = new HashMap<String, String>();
				map.put("en", "jztm");
				map.put("cn", "������Ŀ");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "xthdrq");
				map.put("cn", "����");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "jcdj");
				map.put("cn", "�����Ǽ�");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "xthdrq");
				map.put("cn", "�����Ǽ�");
				topTr.add(map);

			} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {// ����
				map = new HashMap<String, String>();
				map.put("en", "hdnr");
				map.put("cn", "�����");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "xthdrq");
				map.put("cn", "����");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "jldj");
				map.put("cn", "�����ȼ�");
				topTr.add(map);

			} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {// ��֯�
				map = new HashMap<String, String>();
				map.put("en", "hdzt");
				map.put("cn", "�����");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "hdrq");
				map.put("cn", "�����");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "hddj");
				map.put("cn", "��ȼ�");
				topTr.add(map);

			} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// ���ʵ��
				map = new HashMap<String, String>();
				map.put("en", "hddd");
				map.put("cn", "��ص�");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "hdrq");
				map.put("cn", "�����");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "hdnr");
				map.put("cn", "�����");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "hdsj");
				map.put("cn", "�ʱ��");
				topTr.add(map);
			}

			map = new HashMap<String, String>();
			map.put("en", "sqf");
			map.put("cn", "�����");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "bzrshf");
			map.put("cn", "��������˷�");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xyshf");
			map.put("cn", "Ժϵ��˷�");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xxshf");
			map.put("cn", "ѧУ��˷�");
			topTr.add(map);

			if ("stu".equalsIgnoreCase(yhlx) || "bz".equalsIgnoreCase(yhlx)) {
				map = new HashMap<String, String>();
				map.put("en", "cz");
				map.put("cn", "����");
				topTr.add(map);
			}
		}
		return topTr;
	}

	/**
	 * �ҵ�����_��ʼ������(�����ѯ)
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initJgcx(RequestForm rForm, PjpyStuForm model,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		PjpyStuService service = new PjpyStuService();
		
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");

		// �û�����
		String yhlx = (String) session.getAttribute("yhlx");
		model.setYhlx(yhlx);

		// ѧ��
		String xn = Base.currXn;
		model.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		model.setXq(xq);

		// ѧ������
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);

		// ѧ��
		String xh = request.getParameter("xh");
		model.setXh(xh);

		// ��Ŀ����
		String xmdm = Base.isNull(request.getParameter("xmdm")) ? model
				.getXmdm() : request.getParameter("xmdm");
		model.setXmdm(xmdm);

		// ��Ŀ����
		String xmmc = getXmmc(xmdm);

		// ��ͷ
		List<HashMap<String, String>> topTr = getJgcxTopTr(model);

		// �����ֶ�
		String[] qtzd = new String[] { "xn", "xq", "xqmc", "xh", "xmdm", "xmmc" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xn, xq, xqmc, xh, xmdm, xmmc };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTopTr(topTr);

		// =================���Ի��߼���ѯ========================
		// ѧ��
		List<HashMap<String, String>> xnList = Base.getXnndList();
		request.setAttribute("xnTjList", xnList);

		// ѧ��
		List<HashMap<String, String>> xqList = Base.getXqList();
		request.setAttribute("xqTjList", xqList);

		// ����
		List<HashMap<String, String>> czList = getSelectList("stu_jgcx_czlx");
		request.setAttribute("czTjList", czList);

	}

	/**
	 * ��ý����ѯ�ı�ͷ
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	private List<HashMap<String, String>> getJgcxTopTr(PjpyStuForm model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		// ��Ŀ����
		String xmdm = model.getXmdm();

		HashMap<String, String> map = new HashMap<String, String>();

		// �û�����
		String yhlx = model.getYhlx();

		map = new HashMap<String, String>();
		map.put("en", "xh");
		map.put("cn", "ѧ��");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "xm");
		map.put("cn", "����");
		topTr.add(map);
		if ("szgy_zhszcphzlsb".equalsIgnoreCase(xmdm)) {
			
				map = new HashMap<String, String>();
				map.put("en", "zhszf");
				map.put("cn", "�ۺ����ʷ�");
				topTr.add(map);
				
				map = new HashMap<String, String>();
				map.put("en", "zhszfpm");
				map.put("cn", "�ۺ���������");
				topTr.add(map);
			 
		} else {
			if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {// ����
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "�����");
				topTr.add(map);
			} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {// ���Ա��
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "�����");
				topTr.add(map);
			} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {// IVT��̳
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "�����");
				topTr.add(map);
			} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {// ����
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "�����");
				topTr.add(map);
			} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {// ��֯�
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "�����");
				topTr.add(map);
			} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// ���ʵ��
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "�����");
				topTr.add(map);
			} else if ("szyc_5sb".equalsIgnoreCase(xmdm)) {// ���ʵ��
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "¼���");
				topTr.add(map);
			}

			map = new HashMap<String, String>();
			map.put("en", "zzf");
			map.put("cn", "���շ�");
			topTr.add(map);

			
		}
		
		map = new HashMap<String, String>();
		map.put("en", "cz");
		map.put("cn", "����");
		topTr.add(map);
		return topTr;
	}

	/**
	 * �������ά��������ֵ
	 * 
	 * @author luojw
	 */
	private List<HashMap<String, String>> getSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("stu_jgcx_czlx".equalsIgnoreCase(lx)) {// �������ͣ�ѧ��_�����ѯ��
			dm = new String[] { "�鿴", "��Ͷ��", "��Ͷ��", "�Ѵ���" };
			mc = new String[] { "�鿴", "��Ͷ��", "��Ͷ��", "�Ѵ���" };
		} else if ("stu_myss_sszt".equalsIgnoreCase(lx)) {// ����״̬��ѧ��_�ҵ����ߣ�
			dm = new String[] { "δ����", "�Ѵ���" };
			mc = new String[] { "δ����", "�Ѵ���" };
		} else if ("stu_myss_ssxm".equalsIgnoreCase(lx)) {// ������Ŀ��ѧ��_�ҵ����ߣ�
			dm = new String[] { "szyq_dshdjzb", "szyq_yybdjzb", "szyq_ivtltb",
					"szyq_xthddjb", "szyc_zznlfzb", "szyc_shsjfzb","szyc_5sb" };
			mc = new String[] { "����", "���Ա��", "IVT��̳", "����", "��֯����", "���ʵ��", "5S"  };
		} else if ("stu_myss_tsxm".equalsIgnoreCase(lx)) {// Ͷ����Ŀ��ѧ��_�ҵ�Ͷ�ߣ�
			dm = new String[] { "szyq_dshdjzb", "szyq_yybdjzb", "szyq_ivtltb",
					"szyq_xthddjb", "szyc_zznlfzb", "szyc_shsjfzb", "szyc_5sb" };
			mc = new String[] { "����", "���Ա��", "IVT��̳", "����", "��֯����",
					"���ʵ��", "5S" };
		} else if ("fssh_shzt".equalsIgnoreCase(lx)) {// ���״̬��
			dm = new String[] { "ͨ��", "��ͨ��", "δ���", "�˻�", "������" };
			mc = new String[] { "ͨ��", "��ͨ��", "δ���", "�˻�", "������" };
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * �ҵ�����_��ʼ������(�ҵ�����)
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initMySs(RequestForm rForm, PjpyStuForm model,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		PjpyStuService service = new PjpyStuService();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");

		// �û�����
		String yhlx = (String) session.getAttribute("yhlx");
		model.setYhlx(yhlx);

		// ѧ��
		String xn = Base.currXn;
		model.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		model.setXq(xq);

		// ѧ��
		String xh = request.getParameter("xh");
		model.setXh(xh);

		// ��ͷ
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap<String, String>> topTr = getOtherTopTr(map, "myss");

		// �����ֶ�
		String[] qtzd = new String[] { "xn", "xq", "xh" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xn, xq, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTopTr(topTr);

		// =================���Ի��߼���ѯ========================
		// ѧ��
		List<HashMap<String, String>> xnList = Base.getXnndList();
		request.setAttribute("xnTjList", xnList);

		// ѧ��
		List<HashMap<String, String>> xqList = Base.getXqList();
		request.setAttribute("xqTjList", xqList);

		// ����״̬
		List<HashMap<String, String>> ssztList = getSelectList("stu_myss_sszt");
		request.setAttribute("lxTjList", ssztList);

		// ������Ŀ
		List<HashMap<String, String>> xmdmList = getSelectList("stu_myss_ssxm");
		request.setAttribute("xmdmTjList", xmdmList);

	}

	/**
	 * �ҵ�����_��ʼ������(�ҵ�Ͷ��)
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initMyTs(RequestForm rForm, PjpyStuForm model,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		PjpyStuService service = new PjpyStuService();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");

		// �û�����
		String yhlx = (String) session.getAttribute("yhlx");
		model.setYhlx(yhlx);

		// ѧ��
		String xn = Base.currXn;
		model.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		model.setXq(xq);

		// ѧ��
		String xh = request.getParameter("xh");
		model.setXh(xh);

		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);

		// ��ͷ
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap<String, String>> topTr = getOtherTopTr(map, "myts");

		// �����ֶ�
		String[] qtzd = new String[] { "xn", "xq", "xh" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xn, xq, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTopTr(topTr);

		// =================���Ի��߼���ѯ========================
		// ѧ��
		List<HashMap<String, String>> xnList = Base.getXnndList();
		request.setAttribute("xnTjList", xnList);

		// ѧ��
		List<HashMap<String, String>> xqList = Base.getXqList();
		request.setAttribute("xqTjList", xqList);

		// Ͷ��״̬
		List<HashMap<String, String>> tsztList = getSelectList("stu_myss_sszt");
		request.setAttribute("lxTjList", tsztList);

		// Ͷ����Ŀ
		List<HashMap<String, String>> xmdmList = getSelectList("stu_myss_tsxm");
		request.setAttribute("xmdmTjList", xmdmList);

	}

	/**
	 * �ҵ�����_��ʼ������(5S��ά��)
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initFiveS(RequestForm rForm, PjpyTeaForm model,
			HttpServletRequest request) {

		PjpyTeaService service = new PjpyTeaService();

		HttpSession session = request.getSession();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");

		// �û�����
		rForm.setPath("pjpy_szgyyq_fives.do");
		String yhlx = (String) session.getAttribute("yhlx");
		model.setYhlx(yhlx);

		// ѧ��
		String xn = Base.currXn;
		model.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		model.setXq(xq);

		// ѧ������
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);

		// ѧ��
		String xh = request.getParameter("xh");
		model.setXh(xh);

		// ��ͷ
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap<String, String>> topTr = getOtherTopTr(map, "5s");

		// �����ֶ�
		String[] qtzd = new String[] { "xn", "xq", "xh" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xn, xq, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTopTr(topTr);

		// =================���Ի��߼���ѯ========================
		// ѧ��
		List<HashMap<String, String>> xnList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> xnMap = new HashMap<String, String>();
		xnMap.put("xn", xn);
		xnList.add(xnMap);

		xnMap = new HashMap<String, String>();
		xnMap.put("xn", xn);
		xnList.add(xnMap);

		request.setAttribute("xnTjList", xnList);

		// ѧ��
		List<HashMap<String, String>> xqList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> xqMap = new HashMap<String, String>();
		xqMap.put("xqdm", xq);
		xqMap.put("xqmc", xqmc);
		xqList.add(xqMap);

		request.setAttribute("xqTjList", xqList);

		// ����״̬
		List<HashMap<String, String>> ssztList = getSelectList("stu_myss_sszt");
		request.setAttribute("lxTjList", ssztList);

		// ������Ŀ
		List<HashMap<String, String>> xmdmList = getSelectList("stu_myss_ssxm");
		request.setAttribute("xmdmTjList", xmdmList);

	}

	/**
	 * �ҵ�����_��ʼ������(5S����ϸ)
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initFiveSDetail(RequestForm rForm, PjpyTeaForm model,
			HttpServletRequest request) {

		PjpyTeaService service = new PjpyTeaService();

		HttpSession session = request.getSession();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");

		// �û�����
		String yhlx = (String) session.getAttribute("yhlx");
		model.setYhlx(yhlx);

		// ѧ��
		String xn = Base.currXn;
		model.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		model.setXq(xq);

		// ѧ��
		String xh = request.getParameter("xh");
		model.setXh(xh);

		// ��ͷ
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap<String, String>> topTr = getOtherTopTr(map, "5s_xx");

		// �����ֶ�
		String[] qtzd = new String[] { "xn", "xq", "xh" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xn, xq, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTopTr(topTr);

		// �ӷ�ԭ��
		List<HashMap<String, String>> yyList = service.getYyList();
		request.setAttribute("yyList", yyList);
	}

	/**
	 * ��ý����ѯ�ı�ͷ
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	private List<HashMap<String, String>> getOtherTopTr(
			HashMap<String, String> value, String lx) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();

		if ("myss".equalsIgnoreCase(lx)) {// �ҵ�����
			map = new HashMap<String, String>();
			map.put("en", "xmmc");
			map.put("cn", "��Ŀ����");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "ssnr");
			map.put("cn", "��������");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sssj");
			map.put("cn", "����ʱ��");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "clyj");
			map.put("cn", "�������");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "clr");
			map.put("cn", "������");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "lx");
			map.put("cn", "����״̬");
			topTr.add(map);
		} else if ("myts".equalsIgnoreCase(lx)) {// �ҵ�Ͷ��
			map = new HashMap<String, String>();
			map.put("en", "xmmc");
			map.put("cn", "��Ŀ����");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xh");
			map.put("cn", "��Ͷ����ѧ��");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xm");
			map.put("cn", "��Ͷ��������");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "ssnr");
			map.put("cn", "Ͷ������");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sssj");
			map.put("cn", "Ͷ��ʱ��");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "clr");
			map.put("cn", "������");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "lx");
			map.put("cn", "Ͷ��״̬");
			topTr.add(map);
		} else if ("5s".equalsIgnoreCase(lx)) {// 5S��ά��
			map = new HashMap<String, String>();
			map.put("en", "xh");
			map.put("cn", "ѧ��");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xm");
			map.put("cn", "����");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sqf");
			map.put("cn", "������¼���");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xyshf");
			map.put("cn", Base.YXPZXY_KEY+"��˷�");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xxshf");
			map.put("cn", "ѧУ��˷�");
			topTr.add(map);
		} else if ("5s_xx".equalsIgnoreCase(lx)) {// 5S����ϸ

			map = new HashMap<String, String>();
			map.put("en", "fzxm");
			map.put("cn", "��Ŀ");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "jjf");
			map.put("cn", "�Ӽ���");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sqf");
			map.put("cn", "��ֵ");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "jfrq");
			map.put("cn", "����");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "yy");
			map.put("cn", "ԭ��");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xyshf");
			map.put("cn", Base.YXPZXY_KEY+"��˷�");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xxshf");
			map.put("cn", "ѧУ��˷�");
			topTr.add(map);
		}

		return topTr;
	}

	/**
	 * �ҵ�����_��ʼ������(�������)
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initFssh(RequestForm rForm, PjpyTeaForm model,
			HttpServletRequest request) {

		PjpyTeaService service = new PjpyTeaService();

		HttpSession session = request.getSession();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");

		// �û�����
		String yhlx = (String) session.getAttribute("yhlx");
		rForm.setPath("pjpy_szgyyq_fssh.do");
		model.setYhlx(yhlx);

		// ѧ��
		String xn = Base.currXn;
		model.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		model.setXq(xq);

		// ѧ������
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);

		// ѧ��
		String xh = request.getParameter("xh");
		model.setXh(xh);

		// ��ͷ
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap<String, String>> topTr = getFsshTopTr(model);

		String xmdm = request.getParameter("xmdm");
		
		// �����ֶ�
		String[] qtzd = new String[] { "xn", "xq", "xh", "czxm" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xn, xq, xh, xmdm };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTopTr(topTr);

		// =================���Ի��߼���ѯ========================
		// ѧ��
		List<HashMap<String, String>> xnList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> xnMap = new HashMap<String, String>();
		xnMap.put("xn", xn);
		xnList.add(xnMap);

		xnMap = new HashMap<String, String>();
		xnMap.put("xn", xn);
		xnList.add(xnMap);

		request.setAttribute("xnTjList", xnList);

		// ѧ��
		List<HashMap<String, String>> xqList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> xqMap = new HashMap<String, String>();
		xqMap.put("xqdm", xq);
		xqMap.put("xqmc", xqmc);
		xqList.add(xqMap);

		request.setAttribute("xqTjList", xqList);

		// ���״̬
		List<HashMap<String, String>> shztList = getSelectList("fssh_shzt");
		request.setAttribute("shztTjList", shztList);
	}

	/**
	 * �ҵ�����_��ʼ������(���������ϸ)
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initFsshDetail(RequestForm rForm, PjpyTeaForm model,
			HttpServletRequest request) {

		PjpyTeaService service = new PjpyTeaService();

		HttpSession session = request.getSession();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");

		// �û�����
		String yhlx = (String) session.getAttribute("yhlx");
		model.setYhlx(yhlx);

		// ѧ��
		String xn = Base.currXn;
		model.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		model.setXq(xq);

		// ѧ������
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);

		// ѧ��
		String xh = request.getParameter("xh");
		model.setXh(xh);

		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		
		// ��Ŀ����
		String xmmc = getXmmc(xmdm);
		
		// ��ͷ
		List<HashMap<String, String>> topTr = getShxxTopTr(model);
		
		// �����ֶ�
		String[] qtzd = new String[] { "xn", "xq", "xqmc", "xmdm", "xmmc", "xh" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xn, xq, xqmc, xmdm, xmmc, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTopTr(topTr);
	}

	/**
	 * ��÷�����˵ı�ͷ
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	private List<HashMap<String, String>> getFsshTopTr(PjpyTeaForm model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// �û�����
		String yhlx = model.getYhlx();

		HashMap<String, String> map = new HashMap<String, String>();
		
		if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {// �����λ�೤
			map = new HashMap<String, String>();
			map.put("en", "xh");
			map.put("cn", "ѧ��");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xm");
			map.put("cn", "����");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "bjmc");
			map.put("cn", "�༶����");
			topTr.add(map);

			if(!"szyc_5sb".equals(xmdm)){
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "�����");
				topTr.add(map);
				
				map = new HashMap<String, String>();
				map.put("en", "bzrshf");
				map.put("cn", "��������˷�");
				topTr.add(map);
			}

			map = new HashMap<String, String>();
			map.put("en", "shzt");
			map.put("cn", "���״̬");
			topTr.add(map);
		} else if ("xy".equalsIgnoreCase(yhlx)) {// ѧԺ
			map = new HashMap<String, String>();
			map.put("en", "xh");
			map.put("cn", "ѧ��");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xm");
			map.put("cn", "����");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "bjmc");
			map.put("cn", "�༶����");
			topTr.add(map);
			
			if(!"szyc_5sb".equals(xmdm)){
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "�����");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "bzrshf");
				map.put("cn", "��������˷�");
				topTr.add(map);
			}else {
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "¼���");
				topTr.add(map);
			}

			map = new HashMap<String, String>();
			map.put("en", "bzrshf");
			map.put("cn", Base.YXPZXY_KEY+"��˷�");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "shzt");
			map.put("cn", "���״̬");
			topTr.add(map);
			
			if (!"szyc_5sb".equalsIgnoreCase(xmdm)) {
				map = new HashMap<String, String>();
				map.put("en", "xsts");
				map.put("cn", "Ͷ��");
				topTr.add(map);
			}
			
		} else if ("xx".equalsIgnoreCase(yhlx)) {// ѧУ
			map = new HashMap<String, String>();
			map.put("en", "xh");
			map.put("cn", "ѧ��");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xm");
			map.put("cn", "����");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "bjmc");
			map.put("cn", "�༶����");
			topTr.add(map);

			if(!"szyc_5sb".equals(xmdm)){
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "�����");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "bzrshf");
				map.put("cn", "������<br/>��˷�");
				topTr.add(map);
			}else {
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "¼���");
				topTr.add(map);
			}
			
			map = new HashMap<String, String>();
			map.put("en", "xyshf");
			map.put("cn", Base.YXPZXY_KEY+"<br/>��˷�");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xxshf");
			map.put("cn", "ѧУ<br/>��˷�");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "shzt");
			map.put("cn", "���״̬");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "xsts");
			map.put("cn", "Ͷ��");
			topTr.add(map);
		}

		return topTr;
	}
	
	/**
	 * ��������Ϣ��ϸ�ı�ͷ
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	private List<HashMap<String, String>> getShxxTopTr(PjpyTeaForm model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// �û�����
		String yhlx = model.getYhlx();
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {// ����
			map = new HashMap<String, String>();
			map.put("en", "dsmc");
			map.put("cn", "����");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "dsrq");
			map.put("cn", "��������");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "dsxd");
			map.put("cn", "�����ĵ�");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sfhj");
			map.put("cn", "�Ƿ��");
			topTr.add(map);

		} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {// ���Ա��
			map = new HashMap<String, String>();
			map.put("en", "yybdnr");
			map.put("cn", "���Ա������");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "xthdrq");
			map.put("cn", "����");
			topTr.add(map);
		} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {// IVT��̳
			map = new HashMap<String, String>();
			map.put("en", "jztm");
			map.put("cn", "������Ŀ");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "xthdrq");
			map.put("cn", "����");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "jcdj");
			map.put("cn", "�����Ǽ�");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "ccdj");
			map.put("cn", "�����Ǽ�");
			topTr.add(map);
		} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {// ����
			map = new HashMap<String, String>();
			map.put("en", "hdnr");
			map.put("cn", "�����");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "xthdrq");
			map.put("cn", "����");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "jldj");
			map.put("cn", "�����ȼ�");
			topTr.add(map);
		} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {// ��֯�
			map = new HashMap<String, String>();
			map.put("en", "hdzt");
			map.put("cn", "�����");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "hdrq");
			map.put("cn", "�����");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "hddj");
			map.put("cn", "��ȼ�");
			topTr.add(map);
		} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// ���ʵ��
			map = new HashMap<String, String>();
			map.put("en", "hddd");
			map.put("cn", "��ص�");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "hdrq");
			map.put("cn", "�����");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "hdnr");
			map.put("cn", "�����");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "hdsj");
			map.put("cn", "�ʱ��");
			topTr.add(map);
		}else if ("szyc_5sb".equalsIgnoreCase(xmdm)) {// 5S
			map = new HashMap<String, String>();
			map.put("en", "fzxm");
			map.put("cn", "��ֵ��Ŀ");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "jjf");
			map.put("cn", "�Ӽ���");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "fz");
			map.put("cn", "��ֵ");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "rq");
			map.put("cn", "����");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "yy");
			map.put("cn", "ԭ��");
			topTr.add(map);
		}
		
		if (!"szyc_5sb".equalsIgnoreCase(xmdm)) {
			map = new HashMap<String, String>();
			map.put("en", "sqf");
			map.put("cn", "�����");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "bzrshf");
			map.put("cn", "��������˷�");
			topTr.add(map);
		}

		if("xy".equalsIgnoreCase(yhlx)){
			map = new HashMap<String, String>();
			map.put("en", "xyshf");
			map.put("cn", "Ժϵ��˷�");
			topTr.add(map);
		}

		if("xx".equalsIgnoreCase(yhlx)){
			map = new HashMap<String, String>();
			map.put("en", "xyshf");
			map.put("cn", "Ժϵ��˷�");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "xxshf");
			map.put("cn", "ѧУ��˷�");
			topTr.add(map);
		}

		map = new HashMap<String, String>();
		map.put("en", "shzt");
		map.put("cn", "���״̬");
		topTr.add(map);

		if (!"szyc_5sb".equalsIgnoreCase(xmdm)) {
			if ("xy".equalsIgnoreCase(yhlx) || "xx".equalsIgnoreCase(yhlx)) {
				map = new HashMap<String, String>();
				map.put("en", "xsss");
				map.put("cn", "����");
				topTr.add(map);
			}
		} else {
			if ("xx".equalsIgnoreCase(yhlx)) {
				map = new HashMap<String, String>();
				map.put("en", "xsss");
				map.put("cn", "����");
				topTr.add(map);
			}
		}
		
		return topTr;
	}
}
