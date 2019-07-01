package xgxt.pjpy.comm.zhcp.zcjf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszService;
import xgxt.utils.SearchUtils;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ۺ����ʲ���_�۲�ӷ�_Init��
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
public class ZhcpZcjfInit {

	/**
	 * �۲�ӷ�����_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getZcjfsqRForm(RequestForm rForm, ZhcpZcjfForm model,
			HttpServletRequest request) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// ����ģ��
		String gnmk = "pjpy";
		// ������������
		String menu = "zcjfsq";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "zhcp_zcjf_jfsq.do";

		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ����ѧ������
		String pjxqmc = jbszModel.getPjxqmc();
		// �۲�����
		String zczq = model.getZczq();
		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc", "zczq" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc, zczq };

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

	}

	/**
	 * �۲�ӷ����_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getZcjfshRForm(RequestForm rForm, ZhcpZcjfForm model,
			HttpServletRequest request) {

		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;

		// �۲�����
		String zczq = jbszModel.getZczq();

		if ("xn".equalsIgnoreCase(zczq)) {// �۲�����Ϊѧ��
			initJfshForXn(rForm, model, request);
		} else if ("xq".equalsIgnoreCase(zczq)) {// �۲�����Ϊѧ��
			initJfshForXq(rForm, model, request);
		}
	}

	/**
	 * �۲�ӷ����_��ʼ������(ѧ��)
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initJfshForXn(RequestForm rForm, ZhcpZcjfForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "pjpy";
		// ϵͳ�ֶ�����
		String menu = "zcjfsh";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "zhcp_zcjf_jfsh.do";
		// ���ֵ
		String[] colList = new String[] { "xh", "xm", "nj", "bjmc" };
		// ��Ŀ�б�
		List<HashMap<String, String>> xmList = model.getXmList();
		// ��ͷ
		List<HashMap<String, String>> topTr = setDefaultValueByJfsh(colList,
				xmList);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = String.valueOf(topTr.size() + 1);
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] {  };
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}

	/**
	 * �۲�ӷ����_��ʼ������(ѧ��)
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initJfshForXq(RequestForm rForm, ZhcpZcjfForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "pjpy";
		// ϵͳ�ֶ�����
		String menu = "zcjfsh";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "zhcp_zcjf_jfsh.do";
		// ���ֵ
		String[] colList = new String[] { "xh", "xm", "nj", "bjmc" };
		// ��Ŀ�б�
		List<HashMap<String, String>> xmList = model.getXmList();
		// ��ͷ
		List<HashMap<String, String>> topTr = setDefaultValueByJfsh(colList,
				xmList);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "1";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = "8";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] {};
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}

	/**
	 * �۲�ӷ����_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> setDefaultValueByJfsh(
			String[] colList, List<HashMap<String, String>> xmList) {

		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
				"view_xsjbxx", colList, null);

		HashMap<String, String> map = new HashMap<String, String>();

		if (xmList != null && xmList.size() > 0) {
			for (int i = 0; i < xmList.size(); i++) {
				// ������Ŀ��Ϣ
				HashMap<String, String> lv2Info = xmList.get(i);
				String xmdm = lv2Info.get("xmdm");// ��Ŀ����
				String xmmc = lv2Info.get("xmmc");// ��Ŀ����

				map = new HashMap<String, String>();
				map.put("en", xmdm);
				map.put("cn", xmmc);
				topTr.add(map);
			}
		}

		map = new HashMap<String, String>();
		map.put("en", "shr1");
		map.put("cn", "�����");
		topTr.add(map);
		
		map = new HashMap<String, String>();
		map.put("en", "sftj1");
		map.put("cn", "�Ƿ��ύ");
		topTr.add(map);

		return topTr;
	}
	
	/**
	 * �۲�ӷ���ˣ���ϸ��_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getZcjfshDetailForm(RequestForm rForm, ZhcpZcjfForm model,
			HttpServletRequest request) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// ����ģ��
		String gnmk = "pjpy";
		// ������������
		String menu = "zcjfsq";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "zhcp_zcjf_jfsh.do";

		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ����ѧ������
		String pjxqmc = jbszModel.getPjxqmc();
		// �۲�����
		String zczq = model.getZczq();
		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc", "zczq" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc, zczq };

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

	}
	
	/**
	 * �۲�ӷֲ�ѯ_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getZcjfcxRForm(RequestForm rForm, ZhcpZcjfForm model,
			HttpServletRequest request) {

		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;

		// �۲�����
		String zczq = jbszModel.getZczq();

		if ("xn".equalsIgnoreCase(zczq)) {// �۲�����Ϊѧ��
			initJfcxForXn(rForm, model, request);
		} else if ("xq".equalsIgnoreCase(zczq)) {// �۲�����Ϊѧ��

		}
	}
	
	/**
	 * �۲�ӷֲ�ѯ_��ʼ������(ѧ��)
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initJfcxForXn(RequestForm rForm, ZhcpZcjfForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "pjpy";
		// ϵͳ�ֶ�����
		String menu = "zcjfsh";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "zhcp_zcjf_jfcx.do";
		// ģ������
		String mklx = "jg";
		// ���ֵ
		String[] colList = new String[] { "xh", "xm", "nj", "bjmc" };
		// ��Ŀ�б�
		List<HashMap<String, String>> xmList = model.getXmList();
		// ��ͷ
		List<HashMap<String, String>> topTr = setDefaultValueByJfsh(colList,
				xmList);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = String.valueOf(topTr.size() + 1);
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] {};
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setMklx(mklx);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
}
