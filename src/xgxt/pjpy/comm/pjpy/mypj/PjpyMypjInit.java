package xgxt.pjpy.comm.pjpy.mypj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_Init��
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
	 * �ҵ�����_��ʼ�����ݣ���ʦ�棩
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getMypjForTeaRForm(RequestForm rForm, PjpyMypjForm model,
			HttpServletRequest request) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		// ����ģ��
		String gnmk = "pjpy";
		// ϵͳ�ֶ�����
		String menu = "pjxmsb";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "pjpy_mypj_tea.do";
		// ��ͷ
		List<HashMap<String, String>> topTr =getDefaultValue(model);

		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ����ѧ������
		String pjxqmc = jbszModel.getPjxqmc();
		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setTopTr(topTr);

	}
	
	/**
	 * �ҵ�����_��ʼ�����ݣ�ѧ���棩
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getMypjForStuRForm(RequestForm rForm, PjpyMypjForm model,
			HttpServletRequest request) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		// ����ģ��
		String gnmk = "pjpy";
		// ϵͳ�ֶ�����
		String menu = "pjxmsb";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "pjpy_mypj_stu.do";
		// ��ͷ
		List<HashMap<String, String>> topTr =getDefaultValue(model);

		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ����ѧ������
		String pjxqmc = jbszModel.getPjxqmc();
		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc };

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

		//�汾����
		String bblx = model.getBblx();
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		
		if ("tea".equalsIgnoreCase(bblx)) {//��ʦ��
			map = new HashMap<String, String>();
			map.put("en", "xmmc");
			map.put("cn", "��Ŀ����");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sbrs");
			map.put("cn", "���ϱ�������");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "shrs");
			map.put("cn", "������˵�����");
			topTr.add(map);
		}if ("stu".equalsIgnoreCase(bblx)) {// ѧ����
			map = new HashMap<String, String>();
			map.put("en", "xmmc");
			map.put("cn", "���������Ŀ");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "cz");
			map.put("cn", "Ŀǰ���״̬");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "cz");
			map.put("cn", "����");
			topTr.add(map);
		}
		
		return topTr;
	}
}
