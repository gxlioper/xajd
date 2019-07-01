package xsgzgl.rcsw.qjgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ճ�����_��ٹ���_Init��
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

public class RcswQjglInit {

	/**
	 * �������_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initQjlc(RequestForm rForm, RcswQjglForm model,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "rcsw_qjgl_cssz_qjlc.do";
		// ��ͷ
		List<HashMap<String, String>> topTr = getDefaultValue(model, path);
		// ID
		String id = request.getParameter("id");
		model.setId(id);
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
	}

	/**
	 * �ҵ����_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initMyqj(RequestForm rForm, RcswQjglForm model,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "rcsw_qjgl_mygz_stu.do";
		// ��ͷ
		List<HashMap<String, String>> topTr = getDefaultValue(model, path);
		// ID
		String id = request.getParameter("id");
		model.setId(id);
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
	}
	
	/**
	 * �ҵĹ���_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initMygz(RequestForm rForm, RcswQjglForm model,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "rcsw_qjgl_mygz_tea.do";
		// ��ͷ
		List<HashMap<String, String>> topTr = getDefaultValue(model, path);
		// ID
		String id = request.getParameter("id");
		model.setId(id);
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
	}
	

	/**
	 * �ҵ����_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initMysh(RequestForm rForm, RcswQjglForm model,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "rcsw_qjgl_mysh.do";
		// ��ͷ
		List<HashMap<String, String>> topTr = getDefaultValue(model, path);
		// ID
		String id = request.getParameter("id");
		model.setId(id);
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
	}
	
	/**
	 * �����ѯ_��ʼ������
	 * 
	 * @param request
	 * @author ����������LUO
	 * 
	 */
	public void initMycx(RequestForm rForm, RcswQjglForm model,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "rcsw_qjgl_jgcx.do";
		// ��ͷ
		List<HashMap<String, String>> topTr = getDefaultValue(model, path);
		// ID
		String id = request.getParameter("id");
		model.setId(id);
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
	}
	
	private List<HashMap<String, String>> getDefaultValue(RcswQjglForm model,
			String path) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();

		if ("rcsw_qjgl_cssz_qjlc.do".equalsIgnoreCase(path)) {// �������
			map = new HashMap<String, String>();
			map.put("en", "lxmc");
			map.put("cn", "�������");
			topTr.add(map);

//			map = new HashMap<String, String>();
//			map.put("en", "qjlx");
//			map.put("cn", "�������");
//			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "lcmc");
			map.put("cn", "��������");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "qjts");
			map.put("cn", "�������");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "wshwcrs");
			map.put("cn", "δ����������");
			topTr.add(map);

		}else if("rcsw_qjgl_mygz_stu.do".equalsIgnoreCase(path)){//�ҵ����
			map = new HashMap<String, String>();
			map.put("en", "lxmc");
			map.put("cn", "�������");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "qjts");
			map.put("cn", "�������");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sqsj");
			map.put("cn", "����ʱ��");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "sqjg");
			map.put("cn", "���״̬");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "cz");
			map.put("cn", "����");
			topTr.add(map);
			
		}else if("rcsw_qjgl_mygz_tea.do".equalsIgnoreCase(path)){//�ҵĹ���
			map = new HashMap<String, String>();
			map.put("en", "lxmc");
			map.put("cn", "�������");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xshrs");
			map.put("cn", "���������");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "cz");
			map.put("cn", "����");
			topTr.add(map);
		}else if("rcsw_qjgl_mysh.do".equalsIgnoreCase(path)){//�ҵ����
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
			
			map = new HashMap<String, String>();
			map.put("en", "sqts");
			map.put("cn", "��������");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "shzt");
			map.put("cn", "���״̬");
			topTr.add(map);
		}else if("rcsw_qjgl_jgcx.do".equalsIgnoreCase(path)){//�����ѯ
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
			
			map = new HashMap<String, String>();
			map.put("en", "lxmc");
			map.put("cn", "�������");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "sqsj");
			map.put("cn", "����ʱ��");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "sqts");
			map.put("cn", "��������");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "sqjg");
			map.put("cn", "���״̬");
			topTr.add(map);
		}


		return topTr;
	}
}
